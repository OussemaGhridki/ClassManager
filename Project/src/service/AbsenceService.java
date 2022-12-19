package service;

import DbConnection.MyConnection;
import entities.Absence;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AbsenceService {
    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public AbsenceService(){
        connection= MyConnection.getInstance().getCnx();
    }
    public void createTableAbsence(){
        Statement statement;
        try{
            String query="create table absence (idAbsence SERIAL,motif varchar(200),justification varchar(200)" +
                    ",date varchar(100),sessId int REFERENCES session(idsession), \n" +
                    "    CONSTRAINT SESS_ABS UNIQUE (sessId),primary key(idAbsence));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table crée avec succée");
        }catch (Exception e){
            System.out.println("Table existe deja");
        }
    }


    public void addAbsence(Absence absence){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        absence.setDate(dtf.format(now));
        Statement statement;
        try {
            String query=String.format("insert into absence(motif,justification,date) values('%s','%s','%s');",absence.getMotif(),absence.getJustification(),absence.getDate());
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Ajout effectué");
        }catch (Exception e){
            System.out.println("Une erreur lors d'ajout de l'absence : "+e);
        }
    }


    public void getAllAbsence(){

        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from absence");
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            System.out.printf("--------------------------------------------------------------------%n");
            System.out.printf("                            LES ABSENCES                            %n");
            System.out.printf("--------------------------------------------------------------------%n");
            System.out.printf("| %-5s | %-25s | %-25s | %-12s |%n", "ID", "MOTIF" , "JUSTIFICATION" , "DATE");
            System.out.printf("--------------------------------------------------------------------%n");
            while(rs.next()){
                System.out.printf("| %-5s | %-25s | %-25s | %-12s |%n", rs.getString("idabsence")
                        , rs.getString("motif") ,
                        rs.getString("justification")
                        , rs.getString("date"));
//                System.out.print(rs.getString("idabsence")+" || ");
//                System.out.print(rs.getString("motif")+" || ");
//                System.out.print(rs.getString("justification")+" || ");
//                System.out.println(rs.getString("date")+" || ");
                System.out.printf("--------------------------------------------------------------------%n");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Absence getAbsenceById(int id) {
        Statement statement;
        ResultSet rs = null;
        Absence abs = null;
        try {
            String query = String.format("select * from absence where idabsence= %s", id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            System.out.printf("--------------------------------------------------------------------%n");
            System.out.printf("                         L'ABSENCE SELECTIONNE                      %n");
            System.out.printf("--------------------------------------------------------------------%n");
            System.out.printf("| %-5s | %-25s | %-25s | %-12s |%n", "ID", "MOTIF" , "JUSTIFICATION" , "DATE");
            System.out.printf("--------------------------------------------------------------------%n");
            while (rs.next()) {
                System.out.printf("| %-5s | %-25s | %-25s | %-12s |%n", rs.getString("idabsence")
                        , rs.getString("motif") ,
                        rs.getString("justification")
                        , rs.getString("date"));
                int idabs = rs.getInt("idabsence");
                String motif = rs.getString("motif");
                String justification = rs.getString("justification");
                String date = rs.getString("date");
                abs = new Absence(idabs, date, motif, justification, null, null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return abs;
    }


    public void updateAbsence(int idabsence,Absence absence) throws SQLException {
        Absence updatedABS = new Absence();
        Absence oldAbs = this.getAbsenceById(idabsence);
        if (oldAbs.getMotif() != absence.getMotif() && !absence.getMotif().isEmpty()){
            updatedABS.setMotif(absence.getMotif());
        } else {
            updatedABS.setMotif(oldAbs.getMotif());
        }
        if (oldAbs.getJustification() != absence.getJustification() && !absence.getJustification().isEmpty()){
            updatedABS.setJustification(absence.getJustification());
        } else {
            updatedABS.setJustification(oldAbs.getJustification());
        }
        if (oldAbs.getDate() != absence.getDate() && !absence.getDate().isEmpty()){
            updatedABS.setDate(absence.getDate());
        } else {
            updatedABS.setDate(oldAbs.getDate());
        }
        Statement statement;
        try {
            String query=String.format("update absence set motif='%s', justification='%s', date='%s' where idabsence='%s'",updatedABS.getMotif(),updatedABS.getJustification(),updatedABS.getDate(),idabsence);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Absence mise à jour");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void deleteAbsence(int id){
        Statement statement;
        try{
            String query=String.format("delete from absence where idabsence= %s",id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Absence supprimé");
        }catch (Exception e){
            System.out.println(e);
        }
    }




}
