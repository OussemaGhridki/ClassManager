package service;

import DbConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class GroupService {

    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public GroupService(){
        connection= MyConnection.getInstance().getCnx();
    }

    public void createTableGroup(){
        Statement statement;
        try{
            String query="create table groupe (idGroup SERIAL,nameGroup varchar(200),nbStudents int,emailGroup varchar(100),levelStudy varchar(100),primary key(idGroup));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table crée avec succée");
        }catch (Exception e){
            System.out.println("Table existe deja");
        }
    }


}
