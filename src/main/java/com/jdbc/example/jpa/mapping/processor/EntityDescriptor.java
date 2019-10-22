package com.jdbc.example.jpa.mapping.processor;

import java.util.Map;

public class EntityDescriptor {

    private String tableName;
    private Map<String, String> columnNameAgainstType;
    private String primaryKey;

    public EntityDescriptor(String tableName, Map<String, String> columnNameAgainstType, String primaryKey) {
        this.tableName = tableName;
        this.columnNameAgainstType = columnNameAgainstType;
        this.primaryKey = primaryKey;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, String> getColumnNameAgainstType() {
        return columnNameAgainstType;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }
}
