package de.adesso.termacare.util;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@SuppressWarnings("unchecked")
public class DependencyInjector {
    private static Map<Class<?>, Object> instances;
    
    /**
     * Get the created instance of the specified class
     *
     * @param clazz Class object for passing the type T
     * @param <T>   type of class to get instance of
     * @return The instance
     */
    public static <T> T getInstance(Class<? super T> clazz) {
        return (T) getInjectable(clazz, instances.keySet()).map(instances::get).orElse(null);
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
        log.trace("injecting fields into {}", instance.getClass().getSimpleName());
        Arrays.stream(instance.getClass().getDeclaredFields()).forEach(field -> tryInjectField(instance, field));
    }
    
    /**
     * Checks if the provided field is injectable
     * If it is inject the fitting instance into the field of the provided instance
     *
     * @param instance The instance to inject into
     * @param field    The field to inject into if possible
     */
    private static void tryInjectField(Object instance, Field field) {
        try {
            log.trace("field '{}': loading for injection", field.getName());
            
            field.setAccessible(true);
            
            if (field.get(instance) != null) {
                log.trace("field '{}': aborting injection: field is already set", field.getName());
                return;
            }
            
            Optional<Object> inject = getInjectable(field.getType(), instances.keySet()).map(instances::get);
            
            if (inject.isPresent()) {
                log.trace("field '{}': injecting object of type '{}'", field.getName(), inject.get().getClass().getSimpleName());
                field.set(instance, inject.get());
            } else {
                log.trace("field '{}': aborting injection: no field of type '{}' found", field.getName(), field.getType().getSimpleName());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get the correct injectable instance for a given field
     *
     * @param type        Type of injectable to search for
     * @param injectables The classes that are available to inject
     * @return correct injectable instance or Optional.empty() if none or multiple exist
     */
    private static Optional<Class<?>> getInjectable(Class<?> type, Set<Class<?>> injectables) {
        List<Class<?>> collect = injectables.stream()
                .filter(type::isAssignableFrom)
                .collect(toList());
        
        if (collect.isEmpty()) return Optional.empty();
        if (collect.size() == 1) return Optional.of(collect.get(0));
        
        throw new IllegalStateException("more than one possible injectable found for type " + type);
    }
    
    /**
     * Creates an instance of the given class
     * <p>
     * If a factory method named 'getInstance' is available this method is used for creation
     * Else a parameterless constructor is expected to exist
     *
     * @param clazz The class to create
     * @param <T>   The class type
     * @return An instance of the given class
     */
    private static <T> T createInstance(Class<T> clazz) {
        try {
            try {
                Method factoryMethod = clazz.getDeclaredMethod("getInstance");
                log.trace("creating instance of {} using getInstance method", clazz.getSimpleName());
                return (T) factoryMethod.invoke(null);
            } catch (NoSuchMethodException e) {
                Constructor<T> constructor = clazz.getConstructor();
                log.trace("creating instance of {} using constructor", clazz.getSimpleName());
                return constructor.newInstance();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
