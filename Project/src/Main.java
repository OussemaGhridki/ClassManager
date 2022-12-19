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






    }
}
