package org.example.entities;

public class User {
    private int id  ;
    private String email  ;
    private String roles ;
    private String password ;
    private String name ;
    private String prenom ;
    private int tel ;
    private int is_banned ;

    public User() {
    }

    public User(int id, String email, String roles, String password, String name, String prenom, int tel, int is_banned) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.tel = tel;
        this.is_banned = is_banned;
    }

    public User(String email, String roles, String password, String name, String prenom, int tel, int is_banned) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.tel = tel;
        this.is_banned = is_banned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public User(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
