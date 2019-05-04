package com.jdbc.example;

import com.jdbc.example.repository.ProductRepository;
import com.jdbc.example.service.IProductService;
import com.jdbc.example.service.ProductService;

import java.sql.*;

public class JdbcExampleApplication {


    public static void main(String[] args) throws SQLException {
        String[] dbParams = getArgs(args);
        DBManager.init(dbParams[0], dbParams[1], dbParams[2]);
        Connection connection = DBManager.getInstance().getConnection();

        IProductService productService = new ProductService(new ProductRepository(connection));


        System.out.println(productService.getAllProducts());

    }

    private static String[] getArgs(String...args){
        if(args == null || args.length <3){
            throw new RuntimeException("Some required parameters were not configured !!!");
        }
        return args;
    }
}
