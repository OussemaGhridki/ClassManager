package service;
import DbConnection.MyConnection;

import entities.Teacher;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeacherService {
    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public TeacherService(){
        connection= MyConnection.getInstance().getCnx();
    }

    public void createTableTeacher(){
        Statement statement;
        try{
            String query="create table teacher (idTeacher SERIAL,nameTeacher varchar(200),familyName varchar(200),personalEmail varchar(100),workEmail varchar(100),photoTeacher varchar(200),dueTeacher int,primary key(idTeacher));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table crée avec succée");
        }catch (Exception e){
            System.out.println("Table existe deja");
        }
    }

    public void addTeacher(Teacher teacher){

        Statement statement;
        try {
            String query=String.format("insert into teacher(nameteacher,familyname,personalemail,workemail,phototeacher,dueteacher) values('%s','%s','%s','%s','%s','%s');",teacher.getNameTeacher(),
                    teacher.getFamilyName(),teacher.getPersonalEmail(),teacher.getWorkEmail(),teacher.getPhotoTeacher(),teacher.getDueTeacher());
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Ajout effectué");
        }catch (Exception e){
            System.out.println("Une erreur lors d'ajout de teacher : "+e);
        }
    }

    public void getAllTeacher() throws SQLException {
        Statement statement;
        ResultSet rs=null;

        try {
            String query=String.format("select * from teacher");
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("                                        LES ENSEIGNANTS                                         %n");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-10s | %-10s | %-17s | %-17s | %-10s | %-5s |%n",
                    "ID", "NOM" , "PRENOM" , "EMAIL PERSO" ,"EMAIL PRO", "PHOTO" , "HEURES");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            while(rs.next()){

                System.out.printf("| %-2s | %-10s | %-10s | %-17s | %-17s | %-10s | %-5s |%n",
                        rs.getString("idteacher"), rs.getString("nameteacher")
                        , rs.getString("familyname") , rs.getString("personalemail")
                        ,rs.getString("workemail"), rs.getString("phototeacher")
                        , rs.getString("dueteacher"));
                System.out.printf("-----------------------------------------------------------------------------------------------%n");
//                System.out.print(rs.getString("idteacher")+" || ");
//                System.out.print(rs.getString("nameteacher")+" || ");
//                System.out.print(rs.getString("familyname")+" || ");
//                System.out.print(rs.getString("personalemail")+" || ");
//                System.out.print(rs.getString("workemail")+" || ");
//                System.out.print(rs.getString("phototeacher")+" || ");
//                System.out.println(rs.getString("dueteacher")+" || ");
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Teacher getTeacherById(int id) {
        Statement statement;
        ResultSet rs = null;
        Teacher teacher = null;
        try {
            String query = String.format("select * from teacher where idteacher= %s", id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("                                          L'ENSEIGNANT                                          %n");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-10s | %-10s | %-17s | %-17s | %-10s | %-5s |%n",
                    "ID", "NOM" , "PRENOM" , "EMAIL PERSO" ,"EMAIL PRO", "PHOTO" , "HEURES");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            while (rs.next()) {

                System.out.printf("| %-2s | %-10s | %-10s | %-17s | %-17s | %-10s | %-5s |%n",
                        rs.getString("idteacher"), rs.getString("nameteacher")
                        , rs.getString("familyname") , rs.getString("personalemail")
                        ,rs.getString("workemail"), rs.getString("phototeacher")
                        , rs.getString("dueteacher"));
                System.out.printf("-----------------------------------------------------------------------------------------------%n");
//                System.out.print(rs.getString("idteacher")+" || ");
//                System.out.print(rs.getString("nameteacher")+" || ");
//                System.out.print(rs.getString("familyname")+" || ");
//                System.out.print(rs.getString("personalemail")+" || ");
//                System.out.print(rs.getString("workemail")+" || ");
//                System.out.print(rs.getString("phototeacher")+" || ");
//                System.out.println(rs.getString("dueteacher")+" || ");
                int idteacher = rs.getInt("idteacher");
                String nameteacher = rs.getString("nameteacher");
                String familyname = rs.getString("familyname");
                String workemail = rs.getString("workemail");
                String personalemail = rs.getString("personalemail");
                String phototeacher = rs.getString("phototeacher");
                int dueteacher = rs.getInt("dueteacher");
                teacher = new Teacher(idteacher,nameteacher,familyname,personalemail,workemail,phototeacher,dueteacher,null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return teacher;
    }

    public void updateTeacher(int idTeacher,Teacher teacher) throws SQLException {
        Teacher updatedTeacher = new Teacher();
        Teacher oldTeacher = this.getTeacherById(idTeacher);
        if (oldTeacher.getNameTeacher() != teacher.getNameTeacher() && !teacher.getNameTeacher().isEmpty()){
            updatedTeacher.setNameTeacher(teacher.getNameTeacher());
        } else {
            updatedTeacher.setNameTeacher(oldTeacher.getNameTeacher());
        }
        if (oldTeacher.getFamilyName() != teacher.getFamilyName() && !teacher.getFamilyName().isEmpty()){
            updatedTeacher.setFamilyName(teacher.getFamilyName());
        } else {
            updatedTeacher.setFamilyName(oldTeacher.getFamilyName());
        }
        if (oldTeacher.getPersonalEmail() != teacher.getPersonalEmail() && !teacher.getPersonalEmail().isEmpty()){
            updatedTeacher.setPersonalEmail(teacher.getPersonalEmail());
        } else {
            updatedTeacher.setPersonalEmail(oldTeacher.getPersonalEmail());
        }
        if (oldTeacher.getWorkEmail() != teacher.getWorkEmail() && !teacher.getWorkEmail().isEmpty()){
            updatedTeacher.setWorkEmail(teacher.getWorkEmail());
        } else {
            updatedTeacher.setWorkEmail(oldTeacher.getWorkEmail());
        }
        if (oldTeacher.getPhotoTeacher() != teacher.getPhotoTeacher() && !teacher.getPhotoTeacher().isEmpty()){
            updatedTeacher.setPhotoTeacher(teacher.getPhotoTeacher());
        } else {
            updatedTeacher.setPhotoTeacher(oldTeacher.getPhotoTeacher());
        }
        if (oldTeacher.getDueTeacher() != teacher.getDueTeacher() && teacher.getDueTeacher() != 0){
            updatedTeacher.setDueTeacher(teacher.getDueTeacher());
        } else {
            updatedTeacher.setDueTeacher(oldTeacher.getDueTeacher());
        }


        Statement statement;
        try {
            String query=String.format("update teacher set nameteacher='%s', familyname='%s', personalemail='%s', workemail='%s', phototeacher='%s', dueteacher='%s' where idteacher='%s'"
                    ,updatedTeacher.getNameTeacher(),updatedTeacher.getFamilyName(),updatedTeacher.getPersonalEmail(),
                    updatedTeacher.getWorkEmail(),updatedTeacher.getPhotoTeacher(),updatedTeacher.getDueTeacher(),idTeacher);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Teacher mise à jour");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void deleteTeacher(int id){
        Statement statement;
        try{
            String query=String.format("delete from Teacher where idTeacher= %s",id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Teacher supprimé");
        }catch (Exception e){
            System.out.println(e);
        }
    }




}
