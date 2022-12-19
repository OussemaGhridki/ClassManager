package service;
import DbConnection.MyConnection;
import entities.Situation;
import entities.StateStudent;
import entities.Student;
import entities.Teacher;

import java.sql.*;

public class StudentService {
    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public StudentService(){
        connection= MyConnection.getInstance().getCnx();
    }

    public void createTableStudent(){
        Statement statement;
        try{
            String query="create table student (idStudent SERIAL,nameStudent varchar(200),familyName varchar(200),birthday varchar(100),photo varchar(100),state varchar(200),situation varchar(50),primary key(idStudent));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table crée avec succée");
        }catch (Exception e){
            System.out.println("Table existe deja");
        }
    }

    public void addStudent(Student student){

        Statement statement;
        try {
            String query=String.format("insert into student(namestudent,familyname,birthday,photo,state,situation) values('%s','%s','%s','%s','%s','%s');",student.getNameStudent(),
                    student.getFamilyName(),student.getBirthday(),student.getPhoto(),student.getState(),student.getSituation());
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Ajout effectué");
        }catch (Exception e){
            System.out.println("Une erreur lors d'ajout de student : "+e);
        }
    }

    public void getAllStudents(){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from student");
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("                                     LES ETUDIANTS                                             %n");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-10s | %-10s | %-15s | %-10s | %-8s | %-8s |%n",
                    "ID", "NOM" , "PRENOM" , "DATE NAISSANCE" ,"PHOTO", "ETAT" , "SITUATION");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            while(rs.next()){
                System.out.printf("| %-2s | %-10s | %-10s | %-15s | %-10s | %-8s | %-8s |%n",
                        rs.getString("idStudent"), rs.getString("namestudent")
                        , rs.getString("familyname") , rs.getString("birthday")
                        ,rs.getString("photo"), rs.getString("state")
                        , rs.getString("situation"));
                System.out.printf("-----------------------------------------------------------------------------------------------%n");
                  //System.out.print(rs.getString("idStudent")+" || ");
//                System.out.print(rs.getString("namestudent")+" || ");
//                System.out.print(rs.getString("familyname")+" || ");
//                System.out.print(rs.getString("birthday")+" || ");
//                System.out.print(rs.getString("photo")+" || ");
//                System.out.print(rs.getString("state")+" || ");
//                System.out.println(rs.getString("situation")+" ");
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Student getStudentById(int id) {
        Statement statement;
        ResultSet rs = null;
        Student student = null;
        try {
            String query = String.format("select * from student where idstudent= %s", id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("                                       L'ETUDIANT                                              %n");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-10s | %-10s | %-15s | %-10s | %-8s | %-8s |%n",
                    "ID", "NOM" , "PRENOM" , "NAISSANCE" ,"PHOTO", "ETAT" , "SITUATION");
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            while (rs.next()) {
                System.out.printf("| %-2s | %-10s | %-10s | %-15s | %-10s | %-8s | %-8s |%n",
                        rs.getString("idStudent"), rs.getString("namestudent")
                        , rs.getString("familyname") , rs.getString("birthday")
                        ,rs.getString("photo"), rs.getString("state")
                        , rs.getString("situation"));
                System.out.printf("-----------------------------------------------------------------------------------------------%n");
//                System.out.print(rs.getString("idStudent")+" || ");
//                System.out.print(rs.getString("namestudent")+" || ");
//                System.out.print(rs.getString("familyname")+" || ");
//                System.out.print(rs.getString("birthday")+" || ");
//                System.out.print(rs.getString("photo")+" || ");
//                System.out.print(rs.getString("state")+" || ");
//                System.out.println(rs.getString("situation")+" || ");
                int idstudent = rs.getInt("idstudent");
                String namestudent = rs.getString("namestudent");
                String familyname = rs.getString("familyname");
                String birthday = rs.getString("birthday");
                String photo = rs.getString("photo");
                String state = rs.getString("state");
                String situation = rs.getString("situation");
                student = new Student(idstudent,namestudent,familyname,birthday,photo, StateStudent.valueOf(state), Situation.valueOf(situation),null,null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return student;
    }


    public void updateStudent(int idStudent,Student student) throws SQLException {
        Student updatedStudent = new Student();
        Student oldStudent = this.getStudentById(idStudent);
        if (oldStudent.getNameStudent() != student.getNameStudent() && !student.getNameStudent().isEmpty()){
            updatedStudent.setNameStudent(student.getNameStudent());
        } else {
            updatedStudent.setNameStudent(oldStudent.getNameStudent());
        }
        if (oldStudent.getFamilyName() != student.getFamilyName() && !student.getFamilyName().isEmpty()){
            updatedStudent.setFamilyName(student.getFamilyName());
        } else {
            updatedStudent.setFamilyName(oldStudent.getFamilyName());
        }
        if (oldStudent.getBirthday() != student.getBirthday() && !student.getBirthday().isEmpty()){
            updatedStudent.setBirthday(student.getBirthday());
        } else {
            updatedStudent.setBirthday(oldStudent.getBirthday());
        }
        if (oldStudent.getPhoto() != student.getPhoto() && !student.getPhoto().isEmpty()){
            updatedStudent.setPhoto(student.getPhoto());
        } else {
            updatedStudent.setPhoto(oldStudent.getPhoto());
        }
        if (oldStudent.getState() != student.getState() && student.getState() != null){
            updatedStudent.setState(student.getState());
        } else {
            updatedStudent.setState(oldStudent.getState());
        }
        if (oldStudent.getSituation() != student.getSituation() && student.getState() != null){
            updatedStudent.setSituation(student.getSituation());
        } else {
            updatedStudent.setSituation(oldStudent.getSituation());
        }


        Statement statement;
        try {
            String query=String.format("update student set namestudent='%s', familyname='%s', birthday='%s', photo='%s', state='%s', situation='%s' where idstudent='%s'"
                    ,updatedStudent.getNameStudent(),updatedStudent.getFamilyName(),updatedStudent.getBirthday(),
                    updatedStudent.getPhoto(),updatedStudent.getState(),updatedStudent.getSituation(),idStudent);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student mise à jour");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void deleteStudent(int id){
        Statement statement;
        try{
            String query=String.format("delete from student where idstudent= %s",id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student supprimé");
        }catch (Exception e){
            System.out.println(e);
        }
    }










}
