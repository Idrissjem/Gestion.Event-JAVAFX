package org.example.entities;

public class Event {
    private int id  ;
    private String nom  ;
    private String description ;
    private String image ;
    private String lieu ;
    private String date ;

    public Event() {
    }

    public Event(int id, String nom, String description, String image, String lieu, String date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.date = date;
    }

    public Event(String nom, String description, String image, String lieu, String date) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
