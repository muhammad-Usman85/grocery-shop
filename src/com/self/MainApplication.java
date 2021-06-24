package com.self;

import com.self.dao.ProductDao;
import com.self.model.Product;

import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        char option = '\0';

        ProductDao productDao = new ProductDao();

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
                        try{
                            products = productDao.getAllProducts();
                            for (Product product : products) {
                                displayProducts(product);
                                //System.out.println(product);
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case '2':
                        System.out.print("Enter Product Name : ");
                        String productName = scanner.nextLine();
                        int result = productDao.addNewProduct(productName);
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
                break;
            case '3':
                // switch statement for category
                break;
        }
    }

    private static void displayProducts(Product product){
        System.out.println("Product ID : " + product.getProductId());
        System.out.println("Product Name : " + product.getProductName());
    }

}
