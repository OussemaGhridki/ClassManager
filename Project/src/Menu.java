

import entities.*;
import service.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.*;


public class Menu {
    AbsenceService absenceService =new AbsenceService();
    GroupService groupService=new GroupService();
    SessionService sessionService=new SessionService();
    StudentService studentService=new StudentService();
    TeacherService teacherService=new TeacherService();


    public static void startMenu() throws SQLException {
        System.out.println("************************************************************");
        int userChoice;
        userChoice = Menu.menu();
        while (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4 && userChoice != 5 ){
            userChoice = Menu.menu();
        }

        switch (userChoice) {
            case 1:
                Menu.menuStudent();
                break;
            case 2:
                Menu.menuTeacher();
                break;
            case 3:
                Menu.menuSession();
                break;
            case 4:
                Menu.menuAbsence();
                break;
            case 5:
                System.out.println("Au revoir ♥ ");
                break;
            default:
                System.out.println("Veuillez choisir de la liste ");
        }
    }
    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Veuillez choisir une des opérations : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Operations sur les Etudiants");
        System.out.println("2 - Operations sur les Enseigants");
        System.out.println("3 - Operations sur les Sessions");
        System.out.println("4 - Operations sur les Absences");
        System.out.println("5 - Quit");
        System.out.println("Votre choix : ");
        selection = input.nextInt();

        return selection;
    }

    public static int menuTeacher() throws SQLException {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Veuillez choisir une des opérations pour L'enseignant: ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher tous les enseigants");
        System.out.println("2 - Rechercher un enseignant");
        System.out.println("3 - Ajouter un nouveau Enseignant");
        System.out.println("4 - Modifier un Enseignant");
        System.out.println("5 - Supprimer un Enseignant");
        System.out.println("6 - Retour au menu principale");
        System.out.println("Votre choix : ");
        selection = input.nextInt();
        if (selection != 1 &&selection != 2 &&selection != 3 &&selection != 4 &&selection != 5 &&selection != 6)
        {
            System.out.println("Votre choix n'est pas dans le Menu");
            menuTeacher();
        }
        TeacherService teacherService=new TeacherService();
        switch (selection) {
            case 1:
               teacherService.getAllTeacher();
               Scanner scan =new Scanner(System.in);
                System.out.println("Retour au menu ? (oui/non)");
                String rep = scan.next();
                while (!Objects.equals(rep, "oui")){
                    teacherService.getAllTeacher();
                    System.out.println("Retour au menu ? (oui/non)");
                     rep = scan.next();
                }
                Menu.menuTeacher();

                break;
            case 2:
                System.out.println("Veuillez entrer ID de l'enseigant souhaité ");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                while (id == 0 || id <0 ){
                    System.out.println("Veuillez entrer ID de l'enseigant souhaité ");
                     id = scanner.nextInt();
                }
                teacherService.getTeacherById(id);
                System.out.println("Retour au menu ? (oui/non)");
                Scanner scan2 = new Scanner(System.in);
                 rep = scan2.next();
                while (!Objects.equals(rep, "oui")){
                    teacherService.getTeacherById(id);
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan2.next();
                }
                Menu.menuTeacher();
                break;
            case 3:
                System.out.println("Veuillez completer les information de l'enseignant ");
                Teacher teacher=new Teacher();
                Scanner scan3 = new Scanner(System.in);
                System.out.print("Nom de l'enseignant : ");
                String nameTeacher = scan3.nextLine();
                while (nameTeacher == null ){
                    System.out.println("Veuillez entrer Le nom de l'enseigant souhaité ");
                    nameTeacher = scan3.nextLine();
                }
                System.out.print("Nom de la famille de l'enseignant : ");
                String familyTeacher = scan3.nextLine();
                while (familyTeacher == null ){
                    System.out.println("Veuillez entrer Le nom de famille de l'enseigant souhaité ");
                    familyTeacher = scan3.nextLine();
                }
                System.out.print("Email personnelle de l'enseignant : ");
                String emailPersonnel = scan3.nextLine();
                while (emailPersonnel == null ){
                    System.out.println("Veuillez entrer l'email personnelle de l'enseigant souhaité ");
                    emailPersonnel = scan3.nextLine();
                }
                System.out.print("Email profesionnel de l'enseignant : ");
                String emailPro = scan3.nextLine();
                while (emailPro == null ){
                    System.out.println("Veuillez entrer l'email pro de l'enseigant souhaité ");
                    emailPro = scan3.nextLine();
                }
                System.out.print("photo de l'enseignant (url) : ");
                String photo = scan3.nextLine();
                while (photo == null ){
                    System.out.println("Veuillez entrer l'url de la photo de l'enseigant souhaité ");
                    photo = scan3.nextLine();
                }
                System.out.print("Nombre heures de l'enseignant : ");
                int heure = scan3.nextInt();
                while (heure == 0 || heure < 0 ){
                    System.out.println("Veuillez entrer le nombre d'heure de l'enseigant souhaité correctement ");
                    heure = scan3.nextInt();
                }
                teacher.setNameTeacher(nameTeacher);
                teacher.setFamilyName(familyTeacher);
                teacher.setPhotoTeacher(photo);
                teacher.setDueTeacher(heure);
                teacher.setPersonalEmail(emailPersonnel);
                teacher.setWorkEmail(emailPro);
                teacherService.addTeacher(teacher);
                Menu.menuTeacher();
                break;
            case 4:
                System.out.println("Veuillez completer les information de l'enseignant à modifier");
                Teacher teacherNew=new Teacher();
                 scan3 = new Scanner(System.in);
                System.out.print("ID de l'enseignant à modifier : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'enseigant souhaité correctement ");
                    id = scan3.nextInt();
                }

                nameTeacher = scan3.nextLine();
                if (nameTeacher.isEmpty() ){
                    System.out.println("Veuillez entrer Le nom de l'enseigant souhaité ");
                    nameTeacher = scan3.nextLine();
                }

                 familyTeacher = scan3.nextLine();
                if (familyTeacher.isEmpty()){
                    System.out.println("Veuillez entrer Le nom de famille de l'enseigant souhaité ");
                    familyTeacher = scan3.nextLine();
                }

                 emailPersonnel = scan3.nextLine();
                if (emailPersonnel.isEmpty()){
                    System.out.println("Veuillez entrer l'email personnelle de l'enseigant souhaité ");
                    emailPersonnel = scan3.nextLine();
                }

                 emailPro = scan3.nextLine();
                if (emailPro.isEmpty()){
                    System.out.println("Veuillez entrer l'email pro de l'enseigant souhaité ");
                    emailPro = scan3.nextLine();
                }

                 photo = scan3.nextLine();
                if (photo.isEmpty()){
                    System.out.println("Veuillez entrer l'url de la photo de l'enseigant souhaité ");
                    photo = scan3.nextLine();
                }
                System.out.print("Nombre heures de l'enseignant : ");
                 heure = scan3.nextInt();
                while (heure == 0 || heure < 0 ){
                    System.out.println("Veuillez entrer le nombre d'heure de l'enseigant souhaité correctement ");
                    heure = scan3.nextInt();
                }
                teacherNew.setNameTeacher(nameTeacher);
                teacherNew.setFamilyName(familyTeacher);
                teacherNew.setPhotoTeacher(photo);
                teacherNew.setDueTeacher(heure);
                teacherNew.setPersonalEmail(emailPersonnel);
                teacherNew.setWorkEmail(emailPro);
                teacherService.updateTeacher(id,teacherNew);
                Menu.menuTeacher();
                break;
            case 5:
                System.out.println("Veuillez completer les information de l'enseignant à supprimer");
                scan3 = new Scanner(System.in);
                System.out.print("ID de l'enseignant à supprimer : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'enseigant souhaité correctement ");
                    id = scan3.nextInt();
                }
                teacherService.deleteTeacher(id);
                Menu.menuTeacher();
                break;
            case 6:
                Menu.startMenu();
                break;
            default:
                System.out.println("Veuillez choisir de la liste");
        }
        return selection;
    }

    public static int menuStudent() throws SQLException {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Veuillez choisir une des opérations pour L'étudiant: ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher tous les étudiants");
        System.out.println("2 - Rechercher un étudiant");
        System.out.println("3 - Ajouter un nouveau étudiant");
        System.out.println("4 - Modifier un étudiant");
        System.out.println("5 - Supprimer un étudiant");
        System.out.println("6 - Retour au menu principale");
        System.out.println("Votre choix : ");
        selection = input.nextInt();
        if (selection != 1 &&selection != 2 &&selection != 3 &&selection != 4 &&selection != 5 &&selection != 6)
        {
            System.out.println("Votre choix n'est pas dans le Menu");
            menuStudent();
        }
        StudentService studentService=new StudentService();
        switch (selection) {
            case 1:
                studentService.getAllStudents();
                Scanner scan =new Scanner(System.in);
                System.out.println("Retour au menu ? (oui/non)");
                String rep = scan.next();
                while (!Objects.equals(rep, "oui")){
                    studentService.getAllStudents();
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan.next();
                }
                Menu.menuStudent();

                break;
            case 2:
                System.out.println("Veuillez entrer ID de l'etudiant souhaité ");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                while (id == 0 || id <0 ){
                    System.out.println("Veuillez entrer ID de l'etudiant souhaité ");
                    id = scanner.nextInt();
                }
                studentService.getStudentById(id);
                System.out.println("Retour au menu ? (oui/non)");
                Scanner scan2 = new Scanner(System.in);
                rep = scan2.next();
                while (!Objects.equals(rep, "oui")){
                    studentService.getStudentById(id);
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan2.next();
                }
                Menu.menuStudent();
                break;
            case 3:
                System.out.println("Veuillez completer les information de l'etudiant ");
                Student student=new Student();
                Scanner scan3 = new Scanner(System.in);
                System.out.print("Nom de l'etudiant : ");
                String nameStudent = scan3.nextLine();
                while (nameStudent == null ){
                    System.out.println("Veuillez entrer Le nom de l'etudiant souhaité ");
                    nameStudent = scan3.nextLine();
                }
                System.out.print("Nom de la famille de l'etudiant : ");
                String familyStudent = scan3.nextLine();
                while (familyStudent == null ){
                    System.out.println("Veuillez entrer Le nom de famille de l'etudiant souhaité ");
                    familyStudent = scan3.nextLine();
                }
                System.out.print("photo de l'etudiant (url) : ");
                String photo = scan3.nextLine();
                while (photo == null ){
                    System.out.println("Veuillez entrer l'url de la photo de l'etudiant souhaité ");
                    photo = scan3.nextLine();
                }
                System.out.print("Date de naissance de l'etudiant (yyyy/MM/dd) : ");
                String birthday = scan3.next();
                while (!birthday.matches("\\d{4}/\\d{2}/\\d{2}") ){
                    System.out.println("Veuillez entrer la date de naissance de l'etudiant valide (yyyy/MM-dd)");
                    birthday = scan3.next();
                }

                System.out.print("Etat : present , absent , delayed , excluded? (veuillez respectez le format) ");
                String etat = scan3.next();
                while (!etat.matches("present" )&&!etat.matches("absent") &&!etat.matches("delayed") &&!etat.matches("excluded") ){
                    System.out.println("Veuillez choisir l'etat de l'etudiant valide (present , absent , delayed , excluded) ");
                    etat = scan3.next();
                }
                System.out.print("Situation : New, repeating  , derogatory , other (veuillez respectez le format) ");
                String situation = scan3.next();
                while (!situation.matches("New") &&!situation.matches("repeating") &&!situation.matches("derogatory") &&!situation.matches("other")){
                    System.out.println("Veuillez choisir la situation de l'etudiant valide (New, repeating  , derogatory , other ) ");
                    situation = scan3.next();
                }

                student.setNameStudent(nameStudent);
                student.setFamilyName(familyStudent);
                student.setPhoto(photo);
                student.setBirthday(birthday);
                student.setState(StateStudent.valueOf(etat));
                student.setSituation(Situation.valueOf(situation));
                studentService.addStudent(student);
                Menu.menuStudent();
                break;
            case 4:
                System.out.println("Veuillez completer les information de l'etudiant à modifier");
                Student studentNew=new Student();
                scan3 = new Scanner(System.in);
                System.out.print("ID de l'etudiant à modifier : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'etudiant souhaité correctement ");
                    id = scan3.nextInt();
                }

                nameStudent = scan3.nextLine();
                if (nameStudent.isEmpty() ){
                    System.out.println("Veuillez entrer Le nom de l'etudiant souhaité ");
                    nameStudent = scan3.nextLine();
                }

                familyStudent = scan3.nextLine();
                if (familyStudent.isEmpty()){
                    System.out.println("Veuillez entrer Le nom de famille de l'etudiant souhaité ");
                    familyStudent = scan3.nextLine();
                }
                photo = scan3.nextLine();
                if (photo.isEmpty()){
                    System.out.println("Veuillez entrer l'url de la photo de l'etudiant souhaité ");
                    photo = scan3.nextLine();
                }
                birthday = scan3.nextLine();
                if (birthday.isEmpty()){
                    System.out.println("Veuillez entrer la date de naissance de l'enseigant souhaité ");
                    birthday = scan3.nextLine();
                }

                String state = scan3.nextLine();
                if (state.isEmpty()){
                    System.out.println("Veuillez entrer l'etat de l'etudiant souhaité ");
                    System.out.println("Respectez la format : present , absent , delayed , excluded");
                    state = scan3.nextLine();
                }
                String sit = scan3.nextLine();
                if (sit.isEmpty()){
                    System.out.println("Veuillez entrer la situation de l'etudiant souhaité ");
                    System.out.println("Respectez la format : New, repeating  , derogatory , other");
                    sit = scan3.nextLine();
                }



                studentNew.setNameStudent(nameStudent);
                studentNew.setFamilyName(familyStudent);
                studentNew.setPhoto(photo);
                studentNew.setBirthday(birthday);
                if (state.isEmpty()){ studentNew.setState(null);}
                if (!state.isEmpty()){ studentNew.setState(StateStudent.valueOf(state));}
                if (sit.isEmpty()){ studentNew.setState(null);}
                if (!sit.isEmpty()){ studentNew.setState(StateStudent.valueOf(sit));}
                studentService.updateStudent(id,studentNew);
                Menu.menuStudent();
                break;
            case 5:
                System.out.println("Veuillez completer les information de l'etudiant à supprimer");
                scan3 = new Scanner(System.in);
                System.out.print("ID de l'etudiant à supprimer : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'etudiant souhaité correctement ");
                    id = scan3.nextInt();
                }
                studentService.deleteStudent(id);
                Menu.menuStudent();
                break;
            case 6:
                Menu.startMenu();
                break;
            default:
                System.out.println("Veuillez choisir de la liste");
        }
        return selection;
    }

    public static int menuSession() throws SQLException {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Veuillez choisir une des opérations pour La session: ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher tous les sessions");
        System.out.println("2 - Rechercher une session");
        System.out.println("3 - Ajouter une nouvelle session");
        System.out.println("4 - Modifier une session");
        System.out.println("5 - Supprimer une session");
        System.out.println("6 - Retour au menu principale");
        System.out.println("Votre choix : ");
        selection = input.nextInt();
        if (selection != 1 &&selection != 2 &&selection != 3 &&selection != 4 &&selection != 5 &&selection != 6)
        {
            System.out.println("Votre choix n'est pas dans le Menu");
            menuSession();
        }

        SessionService sessionService=new SessionService();
        switch (selection) {
            case 1:
                sessionService.getAllSessions();
                Scanner scan =new Scanner(System.in);
                System.out.println("Retour au menu ? (oui/non)");
                String rep = scan.next();
                while (!Objects.equals(rep, "oui")){
                    sessionService.getAllSessions();
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan.next();
                }
                Menu.menuSession();

                break;
            case 2:
                System.out.println("Veuillez entrer ID de la session souhaité ");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                while (id == 0 || id <0 ){
                    System.out.println("Veuillez entrer ID de la session souhaité ");
                    id = scanner.nextInt();
                }
                sessionService.getSessionById(id);
                System.out.println("Retour au menu ? (oui/non)");
                Scanner scan2 = new Scanner(System.in);
                rep = scan2.next();
                while (!Objects.equals(rep, "oui")){
                    sessionService.getSessionById(id);
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan2.next();
                }
                Menu.menuSession();
                break;
            case 3:
                System.out.println("Veuillez completer les information de la session ");
                Session session=new Session();
                Scanner scan3 = new Scanner(System.in);

                System.out.println("Objectif de la session :  ");
                String goal = scan3.nextLine();
                while (goal == null ){
                    System.out.println("Veuillez choisir un objectif valide ");
                    goal = scan3.nextLine();
                }
                System.out.println("Un petit sommaire de la session :  ");
                String summary = scan3.nextLine();
                while (summary == null ){
                    System.out.println("Veuillez choisir un sommaire valide ");
                    summary = scan3.nextLine();
                }
                System.out.println("Etat : running , achieved , canceled , delayed (veuillez respectez le format) ");
                String etat = scan3.next();
                while (!etat.matches("running") &&!etat.matches("achieved") &&!etat.matches("canceled") &&!etat.matches("delayed")){
                    System.out.println("Veuillez choisir l'etat de la session valide (running , achieved , canceled , delayed ) ");
                    etat = scan3.next();
                }
                System.out.println("Type : normal , catching_up  , training (veuillez respectez le format) ");
                String type = scan3.next();
                while (!type.matches("normal") &&!type.matches("catching_up") &&!type.matches("training")){
                    System.out.println("Veuillez choisir le type de la session valide (normal , catching_up  , training) ");
                    type = scan3.next();
                }
                System.out.println("La session est-elle en ligne ? (oui/non) ");
                String online = scan3.next();
                while (!online.matches("oui") && !online.matches("non")){
                    System.out.println("Veuillez répondre par oui/non , La session est-elle en ligne ? (oui/non) ");
                    online = scan3.next();
                }
                String reponse ="oui" ;
                List<Tools> tools =new ArrayList<>();

                String tool ="";

                while (!tool.matches("hardware") && !tool.matches("software") || reponse.matches("oui")){
                    System.out.println("Les outils sont software/hardware  ?Veuillez répondre par (software/hardware) ");
                    tool = scan3.next();
                    if (tool.matches("hardware") || tool.matches("software")){
                        tools.add(Tools.valueOf(tool));
                        System.out.println("Veuillez ajouter un autre outil ? oui/non");
                        reponse= scan3.next();
                        while (!reponse.matches("oui") && !reponse.matches("non")){
                            System.out.println("Veuillez répondre par oui/non , Veuillez ajouter un autre outil ? (oui/non) ");
                            reponse = scan3.next();
                        } }
                }
                System.out.println("Quand la session commence ? exemple 14:00");
                String starttime = scan3.next();
                while(! starttime.matches("(?:[0-1][0-9]|2[0-4]):[0-5]\\d")){
                    System.out.println("Veuillez choisir la format de l'heure valide (exemple 14:00)");
                    starttime = scan3.next();
                }
                System.out.println("Quand la session se termine ?? exemple 14:00");
                String endtime = scan3.next();
                while(! endtime.matches("(?:[0-1][0-9]|2[0-4]):[0-5]\\d")){
                    System.out.println("Veuillez choisir la format de l'heure valide (exemple 14:00)");
                    endtime = scan3.next();
                }

                System.out.println("Le numero de la salle :  ");
                int nbrroom = scan3.nextInt();
                while (nbrroom == 0 ){
                    System.out.println("Veuillez entrer un numero de salle valide ");
                    nbrroom = scan3.nextInt();
                }
                System.out.println(tools);
                session.setGoal(goal);
                session.setStartTime(starttime+":00");
                session.setEndTime(endtime+":00");
                session.setSummary(summary);
                session.setState(StateSession.valueOf(etat));
                session.setClaasroomNbr(nbrroom);
                session.setType(Type.valueOf(type));
                session.setToolsList(tools);
                if (online.matches("oui")){session.setOnline(true);}
                if (online.matches("non")){session.setOnline(false);}
                sessionService.addSession(session);
                Menu.menuSession();
                break;
            case 4:
                System.out.println("Veuillez completer les information de la session à modifier");
                Session sessionNew=new Session();
                scan3 = new Scanner(System.in);
                System.out.print("ID de la session à modifier : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de la session souhaité correctement ");
                    id = scan3.nextInt();
                }

                starttime = scan3.nextLine();
                if (starttime.isEmpty() ){
                    System.out.println("Veuillez entrer Le temps quand commence de la session  ");
                    System.out.println("Veuillez respectez la format (14:00)  ");
                    starttime = scan3.nextLine();
                }

                endtime = scan3.nextLine();
                if (endtime.isEmpty()){
                    System.out.println("Veuillez entrer Le temps quand se termine la session  ");
                    System.out.println("Veuillez respectez la format (14:00)  ");
                    endtime = scan3.nextLine();
                }
                String nbrroomUP = scan3.nextLine();
                if (nbrroomUP.isEmpty()){
                    System.out.println("Veuillez entrer le numero de la salle ");
                    nbrroomUP = scan3.nextLine();
                }
                goal = scan3.nextLine();
                if (goal.isEmpty()){
                    System.out.println("Veuillez entrer l'objectif de la session ");
                    goal = scan3.nextLine();
                }

                summary = scan3.nextLine();
                if (summary.isEmpty()){
                    System.out.println("Veuillez entrer un sommaire pour la session souhaité ");
                    summary = scan3.nextLine();
                }
                etat = scan3.nextLine();
                if (etat.isEmpty()){
                    System.out.println("Veuillez entrer l'etat de la session souhaité ");
                    System.out.println("Respectez la format : running , achieved , canceled , delayed");
                    etat = scan3.nextLine();
                }
                type = scan3.nextLine();
                if (type.isEmpty()){
                    System.out.println("Veuillez entrer le type de la session souhaité ");
                    System.out.println("Respectez la format : normal , catching_up  , training");
                    type = scan3.nextLine();
                }
                online = scan3.nextLine();
                if (online.isEmpty()){
                    System.out.println("La session est elle en ligne ??");
                    System.out.println("Veuillez repondre seulement par oui / non");
                    online = scan3.nextLine();
                }



                sessionNew.setStartTime(starttime);
                sessionNew.setEndTime(endtime);
                sessionNew.setSummary(summary);
                sessionNew.setGoal(goal);

                if (!etat.isEmpty()){ sessionNew.setState(StateSession.valueOf(etat));}
                
                if (!type.isEmpty()){ sessionNew.setType(Type.valueOf(type));}
                if (online.isEmpty()){sessionNew.setOnline(false);}
                if (online.matches("oui")){sessionNew.setOnline(true);}
                if (online.matches("non")){sessionNew.setOnline(false);}
                if (nbrroomUP.isEmpty()){sessionNew.setClaasroomNbr(0);}
                if (!nbrroomUP.isEmpty()){sessionNew.setClaasroomNbr(Integer.parseInt(nbrroomUP));}

                sessionService.updateSession(id,sessionNew);
                Menu.menuSession();
                break;
            case 5:
                System.out.println("Veuillez completer les information de la session à supprimer");
                scan3 = new Scanner(System.in);
                System.out.print("ID de la session à supprimer : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de la session souhaité correctement ");
                    id = scan3.nextInt();
                }
                sessionService.deleteSession(id);
                Menu.menuSession();
                break;
            case 6:
                Menu.startMenu();
                break;
            default:
                System.out.println("Veuillez choisir de la liste");
        }


        return selection;
    }

    public static int menuAbsence() throws SQLException {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Veuillez choisir une des opérations pour Les absences : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher tous les absences");
        System.out.println("2 - Rechercher une absence");
        System.out.println("3 - Ajouter une nouvelle absence");
        System.out.println("4 - Modifier une absence");
        System.out.println("5 - Supprimer une absence");
        System.out.println("6 - Retour au menu principale");
        System.out.println("Votre choix : ");
        selection = input.nextInt();
        if (selection != 1 &&selection != 2 &&selection != 3 &&selection != 4 &&selection != 5 &&selection != 6)
        {
            System.out.println("Votre choix n'est pas dans le Menu");
            menuAbsence();
        }
        AbsenceService absenceService=new AbsenceService();
        switch (selection) {
            case 1:
                absenceService.getAllAbsence();
                Scanner scan =new Scanner(System.in);
                System.out.println("Retour au menu ? (oui/non)");
                String rep = scan.next();
                while (!Objects.equals(rep, "oui")){
                    absenceService.getAllAbsence();
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan.next();
                }
                Menu.menuAbsence();

                break;
            case 2:
                System.out.println("Veuillez entrer ID de l'absence souhaité ");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                while (id == 0 || id <0 ){
                    System.out.println("Veuillez entrer ID de l'absence souhaité ");
                    id = scanner.nextInt();
                }
                absenceService.getAbsenceById(id);
                System.out.println("Retour au menu ? (oui/non)");
                Scanner scan2 = new Scanner(System.in);
                rep = scan2.next();
                while (!Objects.equals(rep, "oui")){
                    absenceService.getAbsenceById(id);
                    System.out.println("Retour au menu ? (oui/non)");
                    rep = scan2.next();
                }
                Menu.menuAbsence();
                break;
            case 3:
                System.out.println("Veuillez completer les information de l'absence ");
                Absence student=new Absence();
                Scanner scan3 = new Scanner(System.in);
                System.out.print("Motif de l'absence : ");
                String motifAbsence = scan3.nextLine();
                while (motifAbsence.isEmpty() ){
                    System.out.println("Veuillez entrer Le motif de l'absence souhaité ");
                    motifAbsence = scan3.nextLine();
                }
                System.out.print("Justification de l'absence : ");
                String justificationAbsence = scan3.nextLine();
                while (justificationAbsence.isEmpty() ){
                    System.out.println("Veuillez entrer La Justification de l'absence souhaité ");
                    justificationAbsence = scan3.nextLine();
                }
                System.out.print("Date l'absence (yyyy/MM-dd) : ");
                String dateAbsence = scan3.next();
                while (!dateAbsence.matches("\\d{4}/\\d{2}/\\d{2}") ){
                    System.out.println("Veuillez entrer la date de l'absence valide (yyyy/MM-dd)");
                    dateAbsence = scan3.next();
                }

                student.setMotif(motifAbsence);
                student.setJustification(justificationAbsence);
                student.setDate(dateAbsence);
                absenceService.addAbsence(student);
                Menu.menuAbsence();
                break;
            case 4:
                System.out.println("Veuillez completer les information de l'absence à modifier");
                Absence studentNew=new Absence();
                scan3 = new Scanner(System.in);
                System.out.print("ID de l'absence à modifier : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'absence souhaité correctement ");
                    id = scan3.nextInt();
                }

                motifAbsence = scan3.nextLine();
                if (motifAbsence.isEmpty() ){
                    System.out.println("Veuillez entrer Le motif de l'absence souhaité ");
                    motifAbsence = scan3.nextLine();
                }

                justificationAbsence = scan3.nextLine();
                if (justificationAbsence.isEmpty()){
                    System.out.println("Veuillez entrer La justificarion de l'absence souhaité ");
                    justificationAbsence = scan3.nextLine();
                }
                dateAbsence = scan3.nextLine();
                if (dateAbsence.isEmpty()){
                    System.out.println("Veuillez entrer la date de l'absence souhaité ");
                    dateAbsence = scan3.nextLine();
                }




                studentNew.setDate(dateAbsence);
                studentNew.setMotif(motifAbsence);
                studentNew.setJustification(justificationAbsence);
                absenceService.updateAbsence(id,studentNew);
                Menu.menuAbsence();
                break;
            case 5:
                System.out.println("Veuillez completer les information de l'absence à supprimer");
                scan3 = new Scanner(System.in);
                System.out.print("ID de l'absence à supprimer : ");
                id = scan3.nextInt();
                while (id == 0 || id < 0 ){
                    System.out.println("Veuillez entrer ID de l'absence souhaité correctement ");
                    id = scan3.nextInt();
                }
                absenceService.deleteAbsence(id);
                Menu.menuAbsence();
                break;
            case 6:
                Menu.startMenu();
                break;
            default:
                System.out.println("Veuillez choisir de la liste");
        }
        return selection;
    }




}
