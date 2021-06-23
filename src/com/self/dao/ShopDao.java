package com.self.dao;

import com.self.connection.DatabaseConnection;
import com.self.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    int id;
    public int addNewShop(Shop shop) {

        try{
            connection = DatabaseConnection.createConnection();

            String query = "INSERT INTO shop(shop_name, shop_address)"
                    + "VALUES(?, ?)";

            preparedStatement = connection.prepareStatement(query , preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getShopAddress());
            preparedStatement.setInt(3, shop.getProductId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("this is shop id : " + id);
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

    public List<Shop> getAllShops() throws SQLException {
        connection = DatabaseConnection.createConnection();
        String query = "SELECT * FROM shop";
        List<Shop> list = new ArrayList<Shop>();
        Shop shop = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                shop = new Shop();

                /*Retrieve one shop details
                and store it in shop object*/
                shop.setId(resultSet.getInt("id"));
                shop.setShopName(resultSet.getString("shop_name"));
                shop.setShopAddress(resultSet.getString("shop_address"));

                //add each shop to the list
                list.add(shop);

            }
        } finally {
            resultSet.close();

            connection.close();
        }
        return list;
    }

    public void deleteShop(int id){

        connection = DatabaseConnection.createConnection();
        String sql = "DELETE FROM shop WHERE id=?";

        try {
            Shop shop = new Shop();
            ResultSet resultSet = null;
            preparedStatement = connection.prepareStatement(sql);
            shop.getId(resultSet.getInt("id"));

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
