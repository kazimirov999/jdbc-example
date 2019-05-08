package com.jdbc.example.jpa.mapping.processor;

import com.jdbc.example.jpa.mapping.annotation.Column;
import com.jdbc.example.jpa.mapping.annotation.Entity;
import com.jdbc.example.jpa.mapping.annotation.Id;
import com.jdbc.example.jpa.mapping.annotation.Table;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public final class EntityParserProcessor {

    private EntityParserProcessor() {
    }

    public static Collection<EntityDescriptor> generateEntityDescriptors(String packageScan) {

        Set<Class<?>> clazzes = new Reflections(packageScan).getSubTypesOf(Object.class)
                .stream()
                .filter(clazz -> clazz.getAnnotation(Entity.class) != null)
                .collect(Collectors.toSet());
        if (clazzes.isEmpty()) {
            return null;
        }
        List<EntityDescriptor> descriptors = new ArrayList<>();
        return clazzes.stream()
                .map(c -> new EntityDescriptor(getTableName(c), getColumsAgainstTypes(c), getPrimaryKey(c))
                ).collect(Collectors.toSet());
    }

    private static String getTableName(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            return table.value();
        }
        return clazz.getSimpleName();
    }

    private static Map<String, String> getColumsAgainstTypes(Class<?> clazz) {
        Set<Field> fields = ReflectionUtils.getAllFields(clazz);

        Map<String, String> result = new HashMap<>();
        result.putAll(fields.stream()
                .filter(field -> field.getAnnotation(Column.class) != null)
                .collect(Collectors.toMap(
                        field -> field.getAnnotation(Column.class).name(),
                        field -> field.getType().getName())
                ));
        result.putAll(fields.stream()
                .filter(field -> field.getAnnotation(Column.class) == null)
                .collect(Collectors.toMap(
                        Field::getName,
                        field -> field.getType().getName())
                ));

        return result;
    }

    private static String getPrimaryKey(Class<?> clazz) {
        Set<Field> fields = new Reflections(clazz).getFieldsAnnotatedWith(Id.class);
        if (fields == null || fields.size() != 1) {
            throw new RuntimeException("Entity must have one @Id");
        }
        Field field = fields.stream().findFirst().get();
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            return column.name();
        }
        return field.getName();
    }
}
