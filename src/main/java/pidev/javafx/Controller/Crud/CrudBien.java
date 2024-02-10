package pidev.javafx.Controller.Crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.Model.MarketPlace.Bien;

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

public class CrudBien implements CrudInterface<Bien> {

    private Connection connect;
    private PreparedStatement prepare;
    private  ResultSet result;
    private static CrudBien instance;

    private CrudBien() {}

    public static CrudBien getInstance() {
        if (instance == null)
            instance = new CrudBien();
        return instance;
    }

    public void addItem(Bien bien) {
        String sql = "INSERT INTO products "
                + "(idUser, name, descreption, imgSource, price, quantity, state, type, category)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, 1);
            prepare.setString(2, bien.getName());
            prepare.setString(3, bien.getDescreption());
            prepare.setString(4, getPathAndSaveIMG(bien.getImgSource()));
            prepare.setFloat(5, bien.getPrice());
            prepare.setFloat(6, bien.getQuantity());
            prepare.setString(7, (bien.getState()) ? "1" : "0");
            prepare.setString(8, "BIEN");
            prepare.setString(9, bien.getCategorie().toString());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    public void deleteItem(Bien bien) {
        String sql = "DELETE FROM products WHERE id = ?";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, bien.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    public void updateItem(Bien bien) {
        String sql = "UPDATE products SET name = ?, descreption = ?, imgSource = ?, price = ?, quantity = ?, state = ?, category = ? WHERE id = ?";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, bien.getName());
            prepare.setString(2, bien.getDescreption());
            prepare.setString(3, getPathAndSaveIMG(bien.getImgSource()));
            prepare.setFloat(4, bien.getPrice());
            prepare.setFloat(5, bien.getQuantity());
            prepare.setString(6, (bien.getState()) ? "1" : "0");
            prepare.setString(7, bien.getCategorie().toString());
            prepare.setInt(8, bien.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    @Override
    public ObservableList<Bien> selectItems() {
        Bien bien = null;
        String sql = "SELECT * FROM products"; // Retrieve all items

        connect = ConnectionDB.connectDb();
        ObservableList<Bien> BienList = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                bien=new Bien(result.getInt("idBien"),
                        result.getInt("idUser"),
                        result.getString("name"),
                        result.getString("descreption"),
                        result.getString("imgSource"),
                        result.getFloat("price"),
                        result.getFloat("quantity"),
                        result.getBoolean("state"),
                        result.getTimestamp("timestamp"),
                        Categorie.valueOf(result.getString("category")));
                bien.setImage(new ImageView(new Image( CrudBien.class.getResourceAsStream(bien.getImgSource()),35,35,false,false)));
                BienList.add(bien);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting items: " + e.getMessage());
        }

        return BienList;
    }

    @Override
    public Bien selectFirstItems() {
        return null;
    }



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
//import pidev.javafx.Model.MarketPlace.Bien;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import java.util.Random;
//
//public class CrudBien implements CrudInterface<Bien> {
//
//
//    private  Connection connect;
//    private  Statement statement;
//    private  PreparedStatement prepare;
//    private  ResultSet result;
//
//
//
//    public void addItem(Bien bien) {
//
//        String sql = "INSERT INTO Bien "
//                + "(idUser,name,descreption,imgSource,price,quantity,state,type,category)"
//                + "VALUES(?,?,?,?,?,?,?,?)";
//
//        connect = ConnectionDB.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setInt(1, 1);
//            prepare.setString(2, bien.getName());
//            prepare.setString(3, bien.getDescreption());
//            prepare.setString(4, getPathAndSaveIMG(bien.getImgSource()));
//            prepare.setFloat(5, bien.getPrice());
//            prepare.setFloat(6, bien.getQuantity());
//            prepare.setString(7, (bien.getState())?"1":"0");
//            prepare.setString(8, "BIEN");
//            prepare.setString(8, bien.getCategorie().toString());
//            prepare.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public  void deletItem(Bien bien) {
//
//    }
//
//
//    public  void updateItem(Bien bien) {
//
//    }
//
//    @Override
//    public void deleteItem(Bien bien) {
//
//    }
//
//    @Override
//    public void selectItems(Bien bien) {
//
//    }
//
//
//    public  Bien selectFirstItems() {
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
