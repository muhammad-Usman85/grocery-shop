package com.self;

import com.self.dao.ProductDao;
import com.self.dao.ShopDao;
import com.self.model.Product;
import com.self.model.Shop;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) throws SQLException {
        char option = '\0';

        ProductDao productDao = new ProductDao();
        ShopDao shopDao = new ShopDao();

        System.out.println("\n\n Main Menu");
        System.out.println("1: Product");
        System.out.println("2: Shop");
        System.out.println("3: Category");

        Scanner scanner = new Scanner(System.in);

        // getting input in option from user
        option = scanner.next().charAt(0);

        switch (option) {
            case '1':
                System.out.println("\n\n1: Get All Products");
                System.out.println("2: Add New Product");
                System.out.println("3: Delete Product");

                char productOption = scanner.next().charAt(0);
                switch (productOption) {
                    case '1':
                        List<Product> products;
                        try {
                            products = productDao.getAllProducts();
                            for (Product product : products) {
                                displayProducts(product);
                                //System.out.println(product);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case '2':
                        Product product = new Product();
                        System.out.print("Enter Product Name : ");
                        product.setProductName(scanner.next());
                        int result = productDao.addNewProduct(product);
                        if (result < 0) {
                            System.out.println("Their is some error while creating product");
                        }
                        System.out.println("Product created successfully");
                        break;
                    case '3':
                        System.out.print("Enter Product Id : ");
                        int productId = scanner.nextInt();
                        productDao.deleteProduct(productId);
                        break;
                    default:
                        System.out.println("Please enter valid option!");
                        break;
                }
                break;
            case '2':
                // switch statement for shop
                Shop shop = new Shop();
                Product product = new Product();
                System.out.print("Enter Shop Name : ");
                shop.setShopName(scanner.next());
                System.out.print("Enter Shop Address : ");
                shop.setShopAddress(scanner.next());
                System.out.print("While you are creating shop you also need to create at least one product");
                System.out.print("Enter Product Name : ");
                product.setProductName(scanner.next());
                int result = productDao.addNewProduct(product);
                shop.setProductId(result);
                int shopKeyResult = shopDao.addNewShop(shop);

                if(shopKeyResult < 0) {
                    System.out.println("Their is some error while creating shop");
                }
                System.out.println("Shop Created");
                break;
            case '3':
                // switch statement for category
                break;
        }
    }

    private static void displayProducts(Product product) {
        System.out.println("Product ID : " + product.getProductId());
        System.out.println("Product Name : " + product.getProductName());
    }

}
