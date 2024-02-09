package pidev.javafx.Controller.Crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.Model.MarketPlace.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class CrudProd implements CrudInterface<Product> {

    private Connection connect;
    private PreparedStatement prepare;
    private  ResultSet result;
    private static CrudProd instance;

    private CrudProd() {}

    public static CrudProd getInstance() {
        if (instance == null)
            instance = new CrudProd();
        return instance;
    }

    public void addItem(Product prod) {
        String sql = "INSERT INTO product "
                + "(idUser, name, descreption, imgSource, price, quantity, state, type, category)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, 1);
            prepare.setString(2, prod.getName());
            prepare.setString(3, prod.getDescreption());
            prepare.setString(4, getPathAndSaveIMG(prod.getImgSource()));
            prepare.setFloat(5, prod.getPrice());
            prepare.setFloat(6, prod.getQuantity());
            prepare.setString(7, (prod.getState()) ? "1" : "0");
            prepare.setString(8, "BIEN");
            prepare.setString(9, prod.getCategorie().toString());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    public void deleteItem(Product prod) {
        String sql = "DELETE FROM product WHERE id = ?";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, prod.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    public void updateItem(Product prod) {
        String sql = "UPDATE product SET name = ?, descreption = ?, imgSource = ?, price = ?, quantity = ?, state = ?, category = ? WHERE id = ?";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, prod.getName());
            prepare.setString(2, prod.getDescreption());
            prepare.setString(3, getPathAndSaveIMG(prod.getImgSource()));
            prepare.setFloat(4, prod.getPrice());
            prepare.setFloat(5, prod.getQuantity());
            prepare.setString(6, (prod.getState()) ? "1" : "0");
            prepare.setString(7, prod.getCategorie().toString());
            prepare.setInt(8, prod.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    @Override
    public ObservableList<Product> selectItems() {
        Product product = null;
        String sql = "SELECT * FROM product"; // Retrieve all items

        connect = ConnectionDB.connectDb();
        ObservableList<Product> productList = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                product=new Product(result.getInt("idBien"),
                        result.getInt("idUser"),
                        result.getString("name"),
                        result.getString("descreption"),
                        result.getString("imgSource"),
                        result.getFloat("price"),
                        result.getFloat("quantity"),
                        result.getBoolean("state"),
                        result.getTimestamp("timestamp"),
                        result.getString("type"),
                        Categorie.valueOf(result.getString("category")));
                product.setImage(new ImageView(new Image( CrudProd.class.getResourceAsStream(product.getImgSource()),35,35,false,false)));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting items: " + e.getMessage());
        }

        return productList;
    }

    @Override
    public Product selectFirstItems() {
        return null;
    }


//    public Product selectFirstItems() {
//        String sql = "SELECT * FROM product LIMIT 1"; // Adjust the query as needed
//
//        connect = ConnectionDB.connectDb();
//        Product product = null;
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if (result.next()) {
//                product=new Product(result.getInt("idBien"),
//                        result.getInt("idUser"),
//                        result.getString("name"),
//                        result.getString("descreption"),
//                        result.getString("imgSource"),
//                        result.getFloat("price"),
//                        result.getFloat("quantity"),
//                        result.getBoolean("state"),
//                        result.getTimestamp("timestamp"),
//                        result.getTimestamp("type"),
//                        Categorie.valueOf(result.getString("category") ));
//                product.setImage(new ImageView(new Image( CrudProd.class.getResourceAsStream(bien.getImgSource()),35,35,false,false)));
//                listData.add(product);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error selecting first item: " + e.getMessage());
//        }
//
//        return firstProduct;
//    }


    private  String getPathAndSaveIMG(String chosenFilePath){
        Random randomVal=new Random();

        File chosenFile= new File( chosenFilePath );

        String fileName= UUID.randomUUID().toString();
        String path ="/usersImg/"+fileName+".png";

        try {
            BufferedImage bi = ImageIO.read(chosenFile);
            ImageIO.write(bi, "png", new File( "src/main/resources"+path ));
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        return path;
    }
}




























//package pidev.javafx.Controller.Crud;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import pidev.javafx.Model.MarketPlace.Bien;
//import pidev.javafx.Model.MarketPlace.Categorie;
//import pidev.javafx.Model.MarketPlace.Product;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import java.util.Random;
//
//public class CrudProd implements CrudInterface<Product> {
//
//
//    private  Connection connect;
//    private  Statement statement;
//    private  PreparedStatement prepare;
//    private  ResultSet result;
//
//
//
//    public void addItem(Product prod) {
//
//        String sql = "INSERT INTO product "
//                + "(idUser,name,descreption,imgSource,price,quantity,state,type,category)"
//                + "VALUES(?,?,?,?,?,?,?,?)";
//
//        connect = ConnectionDB.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setInt(1, 1);
//            prepare.setString(2, prod.getName());
//            prepare.setString(3, prod.getDescreption());
//            prepare.setString(4, getPathAndSaveIMG(prod.getImgSource()));
//            prepare.setFloat(5, prod.getPrice());
//            prepare.setFloat(6, prod.getQuantity());
//            prepare.setString(7, (prod.getState())?"1":"0");
//            prepare.setString(8, "BIEN");
//            prepare.setString(8, prod.getCategorie().toString());
//            prepare.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public  void deletItem(Product prod) {
//
//    }
//
//
//    public  void updateItem(Product prod) {
//
//    }
//
//    @Override
//    public void deleteItem(Product prod) {
//
//    }
//
//    @Override
//    public void selectItems(Product prod) {
//
//    }
//
//
//    public  Product selectFirstItems() {
//        return null;
//    }
//
//
//    private  String getPathAndSaveIMG(String chosenFilePath){
//        Random randomVal=new Random();
//
//        File chosenFile= new File( chosenFilePath );
//
//        int nameLenght=chosenFile.getName().length();
//
//        String fileName=Long.toString(chosenFile.getPath().length()* randomVal.nextInt(chosenFile.getPath().length())*nameLenght)+chosenFile.getName().substring(0,nameLenght-4);
//
//        String path ="/usersImg/"+fileName+".png";
//
//        try {
//            BufferedImage bi = ImageIO.read(chosenFile);
//            ImageIO.write(bi, "png", new File( "src/main/resources"+path ));
//        } catch (IOException e) {
//            throw new RuntimeException( e );
//        }
//        return path;
//    }
//}
