package de.adesso.termacare.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DependencyInjector {
    private static Map<Class<?>, Object> instances;
    
    public static <T> T getInstance(Class<T> clazz) {
        return (T) instances.get(clazz);
    }
    
    public static void inject(Class<?>... classes) {
        instances = Arrays.stream(classes)
                .collect(toMap(identity(), DependencyInjector::createInstance));
        instances.forEach(DependencyInjector::injectAllFields);
    }
    
    private static void injectAllFields(Class<?> clazz, Object instance) {
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> tryInjectField(instance, field));
    }
    
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
    
    private static Optional<Class<?>> getInjectable(Field field, Set<Class<?>> injectables) {
        List<Class<?>> collect = injectables.stream()
                .filter(injectable -> field.getType().isAssignableFrom(injectable))
                .collect(toList());
        
        if (collect.isEmpty()) return Optional.empty();
        if (collect.size() == 1) return Optional.of(collect.get(0));
        
        throw new IllegalStateException("more than one possible injectable found for field " + field);
    }
    
    private static <T> T createInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void inject(String base, String... classNames) {
        Class<?>[] classes = Arrays.stream(classNames)
                .map(name -> base + '.' + name)
                .map(DependencyInjector::classForName)
                .toArray(Class<?>[]::new);
        
        inject(classes);
    }
    
    private static Class<?> classForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
