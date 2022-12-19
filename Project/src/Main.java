import entities.*;
import service.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Preparation de la base de donnée ... ");
        SessionService sessionService=new SessionService();
        sessionService.createTableSession();
        AbsenceService absenceService =new AbsenceService();
        absenceService.createTableAbsence();
        GroupService groupService=new GroupService();
        groupService.createTableGroup();
        StudentService studentService=new StudentService();
        studentService.createTableStudent();
        TeacherService teacherService=new TeacherService();
        teacherService.createTableTeacher();
        System.out.println("-------------------------------------------------------------- ");
        boolean login = false ;
        System.out.println("Bienvenue , veuillez entrer vos coordonnées");
        while(!login){
        Scanner scannerName = new Scanner(System.in);
        System.out.print("Nom utilisateur : ");
        String userLoginName = scannerName.next();
        System.out.print("Mot de passe : ");
        String userLoginPass = scannerName.next();
        if (userLoginPass.equals("admin") && userLoginName.equals("admin")){
            System.out.println("Login succées");
            login = true;
        } else {
            System.out.println("Login echouée , veuillez verifier vos coordonnées");
            login = false ;
        }}

        Menu.startMenu();




















//        AbsenceService absenceService=new AbsenceService();
//        absenceService.createTableAbsence();
//        Absence absence= new Absence();
//        absence.setMotif("Updated");
//        absence.setJustification("e lta9s lbara bered");
//        absence.setDate("01/01/2023");
//
//        absenceService.getAllAbsence();






//
//        SessionService sessionService=new SessionService();
//        Session sess =new Session();
//        sess.setEndTime(Time.valueOf("02:25:21"));
//        sess.setStartTime(Time.valueOf("21:05:21"));
//        List<Tools> tools =new ArrayList<>();
//        tools.add(Tools.Hardware);
//
//        sess.setToolsList(tools);
//        sess.setGoal("aaaaaaaaaaaaazeaeae");
//
//        sessionService.updateSession(1,sess);
//        sessionService.deleteSession(3);
//        sessionService.getAllSessions();















//
//        /******** partie student ********/
//        StudentService ss= new StudentService();
//        Student s =new Student();
//        s.setPhoto("url mta3 taswira");
//        s.setNameStudent("ahmed");
//        s.setFamilyName("zakareya");
//        s.setBirthday("2022/05/01");
//        s.setSituation(Situation.derogatory);
//        s.setState(StateStudent.present);
//        ss.addStudent(s);
//        ss.getAllStudents();














//        /******* partie teacher ****/
//
//        TeacherService ts =new TeacherService();
//        Teacher teacher=new Teacher();
//        teacher.setDueTeacher(8);
//        teacher.setNameTeacher("oussema");
//        teacher.setPhotoTeacher("url");
//        teacher.setFamilyName("UPDATED");
//        teacher.setPersonalEmail("personal@personal");
//        teacher.setWorkEmail("work@work");
//        ts.deleteTeacher(3);
//        ts.getAllTeacher();










        //        /**** partie absence ****/
//        AbsenceService as = new AbsenceService();
//
//        as.deleteAbsence(1);
//        as.getAllAbsence();
//
//        as.createTableAbsence();
//        Absence ab= new Absence();
//        as.addAbsence(ab);
//        as.getAllAbsence();
//
//
//
//        Absence abUpdate = new Absence();
//
//        abUpdate.setJustification("aaeae");
//        abUpdate.setMotif(null);
//        as.updateAbsence(1,abUpdate);






    }
}