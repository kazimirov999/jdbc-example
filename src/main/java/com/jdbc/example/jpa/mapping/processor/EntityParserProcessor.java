package com.jdbc.example.jpa.mapping.processor;

import com.jdbc.example.jpa.mapping.annotation.Table;
import com.sun.deploy.util.ReflectionUtil;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EntityParserProcessor {

    private final String packageScan;

    public EntityParserProcessor(String packageScan) {
        this.packageScan = packageScan;
    }

    Collection<EntityDescriptor> generateEntityDescriptors() {
        Reflections reflections = new Reflections(packageScan);
        Set<Class<?>> clazzes = reflections.getSubTypesOf(Object.class);

        if (clazzes.isEmpty()) {
            return null;
        }

        List<EntityDescriptor> descriptors = new ArrayList<>();

        return null;
    }

    private String getTableName(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            return table.value();
        }
        return clazz.getSimpleName();
    }


}
