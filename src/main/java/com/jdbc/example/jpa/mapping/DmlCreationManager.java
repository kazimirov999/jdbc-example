package com.jdbc.example.jpa.mapping;

import com.jdbc.example.jpa.mapping.processor.EntityDescriptor;
import com.jdbc.example.jpa.mapping.processor.EntityParserProcessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DmlCreationManager {

    private final String packageScan;
    private final String dataBaseName;
    private final Connection connection;
    private final Map<String, String> javaTypesAgainstSqlTypes = new HashMap<>();

    public DmlCreationManager(String packageScan, String dataBaseName, Connection connection) {
        this.packageScan = packageScan;
        this.dataBaseName = dataBaseName;
        this.connection = connection;
        initTypes(this.javaTypesAgainstSqlTypes);

    }

    public void createDataBaseDml() {
        generateSqlForTables().forEach(sql -> {
            try {
                connection.createStatement().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> generateSqlForTables() {
        Collection<EntityDescriptor> descriptors = EntityParserProcessor.generateEntityDescriptors(packageScan);
        if (descriptors == null) {
            throw new RuntimeException("Entities are absent !");
        }
        return descriptors.stream().map(this::createTableSql).collect(Collectors.toList());
    }

    private String createTableSql(EntityDescriptor entityDescriptor) {
        return "CREATE TABLE " + entityDescriptor.getTableName() + " ( " + createColumns(entityDescriptor) + " );";
    }

    private String createColumns(EntityDescriptor entityDescriptor) {
        String primaryKeyColumn = entityDescriptor.getPrimaryKey() + " "
                + entityDescriptor.getColumnNameAgainstType().get(entityDescriptor.getPrimaryKey())
                + " not null primary key";

        List<String> columnsDescriptions = entityDescriptor.getColumnNameAgainstType().entrySet()
                .stream()
                .filter(stringStringEntry -> !stringStringEntry.getKey().equals(entityDescriptor.getPrimaryKey()))
                .map(entry -> entry.getKey() + " " + this.javaTypesAgainstSqlTypes.get(entry.getValue()))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder(primaryKeyColumn);
        columnsDescriptions.forEach(s -> {
            sb.append(", ");
            sb.append(s);
        });

        return sb.toString();
    }

    private void initTypes(Map<String, String> javaTypesAgainstSqlTypes) {
        javaTypesAgainstSqlTypes.put("java.lang.String", "TEXT");
        javaTypesAgainstSqlTypes.put("int", "INT");
        javaTypesAgainstSqlTypes.put("double", "DOUBLE");
        javaTypesAgainstSqlTypes.put("float", "FLOAT");
    }


}
