package com.self.dao;

import com.self.connection.DatabaseConnection;
import com.self.model.Product;
import com.self.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    int id;
    public int addNewProduct(String productName) {
        Product product = new Product();
        try{
            connection = DatabaseConnection.createConnection();

            String query = "INSERT INTO product(product_name)"
                    + "VALUES(?)";

            preparedStatement = connection.prepareStatement(query , preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("this is product id : " + id);
                return id;
            }
            resultSet.close();
            connection.close();
            return id;

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        connection = DatabaseConnection.createConnection();
        String query = "SELECT * FROM product";
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                product = new Product();

                /*Retrieve one shop details
                and store it in shop object*/
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));

                //add each shop to the list
                list.add(product);

            }
        } finally {
            resultSet.close();

            connection.close();
        }
        return list;
    }

    public void deleteProduct(int id){

        connection = DatabaseConnection.createConnection();
        String sql = "DELETE FROM product WHERE product_id=?";

        try {
            Product product = new Product();
            ResultSet resultSet = null;
            preparedStatement = connection.prepareStatement(sql);
            product.setProductId(resultSet.getInt("product_id"));

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A product was deleted successfully!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
