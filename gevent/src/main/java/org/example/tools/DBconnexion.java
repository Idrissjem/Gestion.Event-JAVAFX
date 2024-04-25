package org.example.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  DBconnexion {
    public String url="jdbc:mysql://localhost:3306/fitconnect" ;

    public String login="root";
    public String pwd="" ;
    Connection cnx ;
    public static DBconnexion instance ;
    private DBconnexion() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public Connection getCnx(){
        return cnx ;
    }

    public static DBconnexion getInstance(){
        if (instance==null){
            instance=new DBconnexion() ;
        }
        return instance ;



    }
}




