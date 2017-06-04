package de.adesso.termacare.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
public class DependencyInjector {
    private static Map<Class<?>, Object> instances;
    
    public static <T> T getInstance(Class<T> clazz) {
        return (T) instances.get(clazz);
    }
    
    /**
     * Create an instance of all specified classes
     * For each field of these classes if there exists an object for it write it into that field
     * This way the implementations for the classes field mustn't be provided manually
     * but are instead automatically resolved across the entire application
     *
     * @param classes The classes to create and inject into
     */
    public static void inject(Class<?>... classes) {
        instances = Arrays.stream(classes)
                .collect(toMap(identity(), DependencyInjector::createInstance));
        instances.values().forEach(DependencyInjector::injectAllFields);
    }
    
    /**
     * Take an instance and iterate over its fields injecting available instances on the way
     *
     * @param instance The instance to inject into
     */
    private static void injectAllFields(Object instance) {
        Arrays.stream(instance.getClass().getDeclaredFields()).forEach(field -> tryInjectField(instance, field));
    }
    
    /**
     * Checks if the provided field is injectable
     * If it is inject the fitting instance into the field of the provided instance
     *
     * @param instance The instance to inject into
     * @param field The field to inject into if possible
     */
    private static void tryInjectField(Object instance, Field field) {
        try {
            field.setAccessible(true);
            
            if (field.get(instance) != null)
                return;
            
            Optional<Object> inject = getInjectable(field, instances.keySet()).map(instances::get);
            
            if (inject.isPresent())
                field.set(instance, inject.get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get the correct injectable instance for a given field
     *
     * @param field The field to inject into
     * @param injectables The classes that are available to inject
     * @return correct injectable instance or Optional.empty() if none or multiple exist
     */
    private static Optional<Class<?>> getInjectable(Field field, Set<Class<?>> injectables) {
        List<Class<?>> collect = injectables.stream()
                .filter(injectable -> field.getType().isAssignableFrom(injectable))
                .collect(toList());
        
        if (collect.isEmpty()) return Optional.empty();
        if (collect.size() == 1) return Optional.of(collect.get(0));
        
        throw new IllegalStateException("more than one possible injectable found for field " + field);
    }
    
    /**
     * Creates an instance of the given class
     *
     * If a factory method named 'getInstance' is available this method is used for creation
     * Else a parameterless constructor is expected to exist
     *
     * @param clazz The class to create
     * @param <T> The class type
     * @return An instance of the given class
     */
    private static <T> T createInstance(Class<T> clazz) {
        try {
            try {
                Method factoryMethod = clazz.getDeclaredMethod("getInstance");
                return (T) factoryMethod.invoke(null);
            } catch (NoSuchMethodException e) {
                Constructor<T> constructor = clazz.getConstructor();
                return constructor.newInstance();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
