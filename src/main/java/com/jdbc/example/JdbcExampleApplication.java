package com.jdbc.example;

import com.jdbc.example.jpa.mapping.DdlCreationManager;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcExampleApplication {


    public static void main(String[] args) throws SQLException {
        String[] dbParams = getArgs(args);
        DBManager.init(dbParams[0], dbParams[1], dbParams[2]);
        Connection connection = DBManager.getInstance().getConnection();

        DdlCreationManager creationManager =
                new DdlCreationManager("com.jdbc.example.domain", "jdbc-example", connection);
        creationManager.createDataBaseDdl();

    }

    private static String[] getArgs(String...args){
        if(args == null || args.length <3){
            throw new RuntimeException("Some required parameters were not configured !!!");
        }
        return args;
    }
}
