package org.example.entities;

public class Participation {
    private int id  ;
    private int event_id   ;
    private int user_id   ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", event_id=" + event_id +
                ", user_id=" + user_id +
                '}';
    }

    public Participation(int id, int event_id, int user_id) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public Participation(int event_id, int user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }
}
