package service;

import DbConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ModuleService {
    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public ModuleService(){
        connection= MyConnection.getInstance().getCnx();
    }
}
