package com.jdbc.example.repository;

import com.jdbc.example.domain.Product;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Product> findProducts() {
        List<Product> products = null;
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, name, prise, category_id FROM Product";
            ResultSet rs = stmt.executeQuery(sql);

            products = new ArrayList<>();

            while (rs.next()) {
                products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("prise"),
                                rs.getInt("category_id")
                        )
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong whit DB execution !", e);
        }

        return products;
    }
}