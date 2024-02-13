package pidev.javafx.Controller.User;

import pidev.javafx.Controller.Iservice;
import pidev.javafx.Model.user.Role;
import pidev.javafx.Model.user.User;
import pidev.javafx.utlis.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceUser implements Iservice<User> {

    Connection cnx = ConnectionDB.getInstance().getCnx();




    @Override
    public void ajouterUser(User user) {

        String req = "INSERT INTO `user`(`firstName`,`email`,`password`,`adresse`,`date`,`role`) VALUES (?,?,?,?,?,?)";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1,user.getFirstname());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getAdresse());
            ps.setString(5, String.valueOf(LocalDate.now()));
            ps.setString(6, String.valueOf(user.getRole()));

            ps.executeUpdate();

            System.out.println("Personne added !");
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void ajouteremploye(User user) {

        String req = "INSERT INTO `user`(`firstName`,`lastname`,`email`,`age`,`num`,`password`,`adresse`,`date`,`role`,`cin`,`status`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1,user.getFirstname());
            ps.setString(2,user.getLastname());
            ps.setString(3,user.getEmail());
            ps.setString(4, String.valueOf(user.getAge()));
            ps.setString(5, String.valueOf(user.getNum()));
            ps.setString(6,user.getPassword());
            ps.setString(7,user.getAdresse());
            ps.setString(8, String.valueOf(LocalDate.now()));
            ps.setString(9, String.valueOf(user.getRole()));
            ps.setString(10,user.getCin());
            ps.setString(11,user.getStatus());

            ps.executeUpdate();
            System.out.println("Personne added !");
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void modifier(User user) {
        String firstname=user.getFirstname();
        String email= user.getEmail();


        String req = "UPDATE `user` SET `lastname`=?,`age` = ?, `cin` = ?, `dob` = ?,`num` =? ,`status` = ?  WHERE `firstname` = firstname and `email`=email";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, user.getLastname());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getCin());
            ps.setString(4, user.getDob());
            ps.setInt(5,user.getNum());
            ps.setString(6, user.getStatus());

            ps.executeUpdate();
            System.out.println("User updated !");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }




    @Override
    public void supprimer(int id) {


        String req = "DELETE FROM `user` WHERE `cin`= ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User deleted !");
        }

        catch (SQLException e) {

            System.out.println(e.getMessage());

        }


    }

    @Override
    public void supprimerByEmail(String email) {

        String req = "DELETE FROM `user` WHERE `email`= ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("User deleted !");
        }

        catch (SQLException e) {

            System.out.println(e.getMessage());

        }



    }

    @Override
    public User getOneById(int id) {
        return null;
    }






    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        String req = "SELECT * FROM `user`";

        try {
               Statement stmt = cnx.createStatement();
               ResultSet rs = stmt.executeQuery(req);


               while (((ResultSet) rs).next()) {
                   User user = new User();

                     user.setFirstname(rs.getString("firstName"));
                     user.setLastname(rs.getString("lastname"));
                     user.setEmail(rs.getString("email"));
                   user.setRole(Role.valueOf(rs.getString("role")));

                     users.add(user);
               }

        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return users;

    }




    public boolean chercherParEmail(String email){         //hethi bech nahiha

        String req = "SELECT firstName,lastname,email FROM `user` where email=?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){//au moins 1
                System.out.println(rs.getString("firstName") + " " + rs.getString("lastName") + " " + rs.getString("email"));
                return true;
            }
        }

        catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return false;

    }
    public User findParEmail(String email) {

        String req = "SELECT * FROM `user` WHERE email=?";
        User user = null;
        try {


            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet  rs = ps.executeQuery();


            if (rs.next()) {

                String firstname = rs.getString("firstName");
                String lastName = rs.getString("lastname");
                int age= rs.getInt("age");
                int num = rs.getInt("num");
                String adresse = rs.getString("adresse");
                String dob = rs.getString("dob");
                String cin = rs.getString("cin");
                String role=rs.getString("role");
                String status=rs.getString("status");
                String date = rs.getString("date");


                user = new User(firstname,email,cin,age,num,adresse,dob,lastName,status,date, Role.valueOf(role));


            }
        }

        catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return user;
    }

}
