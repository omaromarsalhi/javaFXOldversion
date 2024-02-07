package pidev.javafx.Model.user;

public class user {
int id;
String firstname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getDroit_acces() {
        return droit_acces;
    }

    public void setDroit_acces(int droit_acces) {
        this.droit_acces = droit_acces;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String lastname;

    public user(int id, String firstname, String lastname, String email, String cin, int age, int num, String adresse, int role, int droit_acces, String password, String date) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.cin = cin;
        this.age = age;
        this.num = num;
        this.adresse = adresse;
        this.role = role;
        this.droit_acces = droit_acces;
        this.password = password;
        this.date = date;
    }

    String email;
String cin;
int age;
int num;
String adresse;
int role;
int droit_acces;
String password;
String date;

    public user(String firstname, String lastname, String email, int num) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.num = num;
    }
}
