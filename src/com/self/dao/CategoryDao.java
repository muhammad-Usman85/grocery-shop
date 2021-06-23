package com.self.dao;

import com.self.connection.DatabaseConnection;
import com.self.model.Category;
import com.self.model.Product;
import com.self.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    int id;
    public int addNewCategory(Category category) {

        try{
            connection = DatabaseConnection.createConnection();

            String query = "INSERT INTO category(category_name)"
                    + "VALUES(?)";

            preparedStatement = connection.prepareStatement(query , preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getCategoryName());
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

    public List<Category> getAllCategories() throws SQLException {
        connection = DatabaseConnection.createConnection();
        String query = "SELECT * FROM category";
        List<Category> list = new ArrayList<Category>();
        Category category = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                category = new Category();

                /*Retrieve one shop details
                and store it in shop object*/
                category.setId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));

                //add each shop to the list
                list.add(category);

            }
        } finally {
            resultSet.close();

            connection.close();
        }
        return list;
    }

    public void deleteProduct(int id){

        connection = DatabaseConnection.createConnection();
        String sql = "DELETE FROM category WHERE category_id=?";

        try {
            Shop shop = new Shop();
            ResultSet resultSet = null;
            preparedStatement = connection.prepareStatement(sql);
            shop.getId(resultSet.getInt("category_id"));

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
