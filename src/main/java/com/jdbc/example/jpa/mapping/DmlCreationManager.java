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
                + " not null primary key,";

        String columnsDescription = entityDescriptor.getColumnNameAgainstType().entrySet()
                .stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + ",")
                .collect(Collectors.toList()).toString();

        return primaryKeyColumn + columnsDescription.substring(0, columnsDescription.length() - 1);
    }

    private void initTypes(Map<String, String> javaTypesAgainstSqlTypes) {
        javaTypesAgainstSqlTypes.put("String", "TEXT");
        javaTypesAgainstSqlTypes.put("Integer", "INT");
        javaTypesAgainstSqlTypes.put("Double", "DOUBLE");
        javaTypesAgainstSqlTypes.put("Float", "FLOAT");
    }


}
