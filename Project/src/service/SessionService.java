package service;
import DbConnection.MyConnection;
import entities.*;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SessionService {
    private Statement ste;
    private PreparedStatement pst;

    private Connection connection;
    public SessionService(){
        connection= MyConnection.getInstance().getCnx();
    }



    public void createTableSession(){
        Statement statement;
        try{
            String query="create table session (idSession SERIAL,startTime varchar(100),endTime varchar(100),classroomNbr int,goal varchar(100),summary varchar(200),state varchar(50),module varchar(50),type varchar(50),toolslist varchar(200),online BIT,primary key(idSession));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table crée avec succée");
        }catch (Exception e){
            System.out.println("Table existe deja");
        }
    }



    public void addSession(Session session){

        Statement statement;
        try {
            int online = 0;
            if (session.getOnline()){ online = 1;}
            if (!session.getOnline()){ online = 0;}

            String query=String.format("insert into session(startTime,endTime,classroomNbr,goal,summary,state,module,type,toolslist,online)" +
                            " values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');"
                    ,session.getStartTime(),session.getEndTime(),session.getClaasroomNbr(),session.getGoal(),
                    session.getSummary(),session.getState(),session.getModule(),session.getType(),session.getToolsList(),online);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Ajout effectué");
        }catch (Exception e){
            System.out.println("Une erreur lors d'ajout de session : "+e);
        }
    }


    public void getAllSessions(){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from session");
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("                                                 LES SESSIONS                                                 %n");
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-8s | %-8s | %-5s | %-20s | %-20s | %-7s | %-10s | %-12s | %-3s |%n",
                    "ID", "DEBUTE" , "FIN" , "SALLE","OBJECTIF", "SOMMAIRE" , "ETAT" , "TYPE","OUTILS", "ENLIGNE" );
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            while(rs.next()){
                String online = "oui";
                if (rs.getByte("online") == 1){online = "oui";}
                if (rs.getByte("online") == 0){online = "non";}
                System.out.printf("| %-2s | %-8s | %-8s | %-5d | %-20s | %-20s | %-7s | %-10s | %-12s | %-3s |%n",
                        rs.getString("idSession"), rs.getString("starttime")
                        , rs.getString("endtime") , rs.getInt("classroomnbr")
                        ,rs.getString("goal"), rs.getString("summary")
                        , rs.getString("state") , rs.getString("type")
                        ,rs.getString("toolslist"), online);
                System.out.printf("--------------------------------------------------------------------------------------------------------------%n");


//                System.out.print(rs.getString("idSession")+" || ");
//                System.out.print(rs.getString("starttime")+" || ");
//                System.out.print(rs.getString("endtime")+" || ");
//                System.out.print(rs.getInt("classroomnbr")+" || ");
//                System.out.print(rs.getString("goal")+" || ");
//                System.out.print(rs.getString("summary")+" || ");
//                System.out.print(rs.getString("state")+" || ");
//                System.out.print(rs.getString("module")+" || ");
//                System.out.print(rs.getString("type")+" || ");
//                System.out.print(rs.getString("toolslist")+" || ");
//                System.out.println(rs.getByte("online")+" || ");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }



    public Session getSessionById(int id) {
        Statement statement;
        ResultSet rs = null;
        Session session = null;
        try {
            String query = String.format("select * from session where idsession= %s", id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("                                                 LA SESSION                                                   %n");
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-2s | %-8s | %-8s | %-5s | %-20s | %-20s | %-7s | %-10s | %-12s | %-3s |%n",
                    "ID", "DEBUTE" , "FIN" , "SALLE","OBJECTIF", "SOMMAIRE" , "ETAT" , "TYPE","OUTILS", "ENLIGNE" );
            System.out.printf("--------------------------------------------------------------------------------------------------------------%n");
            while (rs.next()) {
                String onlineTab = "oui";
                if (rs.getByte("online") == 1){onlineTab = "oui";}
                if (rs.getByte("online") == 0){onlineTab = "non";}
                System.out.printf("| %-2s | %-8s | %-8s | %-5d | %-20s | %-20s | %-7s | %-10s | %-12s | %-3s |%n",
                        rs.getString("idSession"), rs.getString("starttime")
                        , rs.getString("endtime") , rs.getInt("classroomnbr")
                        ,rs.getString("goal"), rs.getString("summary")
                        , rs.getString("state") , rs.getString("type")
                        ,rs.getString("toolslist"), onlineTab);
                System.out.printf("--------------------------------------------------------------------------------------------------------------%n");

//                System.out.print(rs.getString("idSession")+" || ");
//                System.out.print(rs.getString("starttime")+" || ");
//                System.out.print(rs.getString("endtime")+" || ");
//                System.out.print(rs.getInt("classroomnbr")+" || ");
//                System.out.print(rs.getString("goal")+" || ");
//                System.out.print(rs.getString("summary")+" || ");
//                System.out.print(rs.getString("state")+" || ");
//                System.out.print(rs.getString("module")+" || ");
//                System.out.print(rs.getString("type")+" || ");
//                System.out.print(rs.getString("toolslist")+" || ");
//                System.out.println(rs.getByte("online")+" || ");
                int idsession = rs.getInt("idSession");
                String starttime = rs.getString("starttime");
                String endtime = rs.getString("endtime");
                int classroomnbr = rs.getInt("classroomnbr");
                String goal = rs.getString("goal");
                String summary = rs.getString("summary");
                String state = rs.getString("state");
                String module = rs.getString("module");
                String type = rs.getString("type");
                String toolslist = rs.getString("toolslist");
                int online = rs.getByte("online");
                boolean isonline = false;
                if (online == 1){isonline = true;}
                if (online == 0){isonline = false;}

                String stringWithNoBrackets = toolslist.substring(1, toolslist.length() - 1);
                List<Tools> toolsFromStream = Arrays.asList(stringWithNoBrackets.split(",\\s+"))
                        .stream()
                        .map(Tools::valueOf)
                        .collect(Collectors.toList());

                session = new Session(idsession,starttime,endtime,classroomnbr,goal,summary, StateSession.valueOf(state),null,Type.valueOf(type),toolsFromStream,isonline,null);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return session;
    }

    public void updateSession(int idSession,Session session) throws SQLException {
        Session updatedSession = new Session();
        Session oldSession = this.getSessionById(idSession);
        if (oldSession.getStartTime() != session.getStartTime() && !session.getStartTime().isEmpty()){
            updatedSession.setStartTime(session.getStartTime());
        } else {
            updatedSession.setStartTime(oldSession.getStartTime());
        }
        if (oldSession.getEndTime() != session.getEndTime() && !session.getEndTime().isEmpty()){
            updatedSession.setEndTime(session.getEndTime());
        } else {
            updatedSession.setEndTime(oldSession.getEndTime());
        }
        if (oldSession.getClaasroomNbr() != session.getClaasroomNbr() && session.getClaasroomNbr() != 0){
            updatedSession.setClaasroomNbr(session.getClaasroomNbr());
        } else {
            updatedSession.setClaasroomNbr(oldSession.getClaasroomNbr());
        }
        if (oldSession.getGoal() != session.getGoal() && !session.getGoal().isEmpty()){
            updatedSession.setGoal(session.getGoal());
        } else {
            updatedSession.setGoal(oldSession.getGoal());
        }
        if (oldSession.getState() != session.getState() && session.getState() != null){
            updatedSession.setState(session.getState());
        } else {
            updatedSession.setState(oldSession.getState());
        }
        if (oldSession.getSummary() != session.getSummary() && !session.getSummary().isEmpty()){
            updatedSession.setSummary(session.getSummary());
        } else {
            updatedSession.setSummary(oldSession.getSummary());
        }
        if (oldSession.getModule() != session.getModule() && session.getModule() != null){
            updatedSession.setModule(session.getModule());
        } else {
            updatedSession.setModule(oldSession.getModule());

        }if (oldSession.getType() != session.getType() && session.getType() != null){
            updatedSession.setType(session.getType());
        } else {
            updatedSession.setType(oldSession.getType());

        }if (oldSession.getToolsList() != session.getToolsList() && session.getToolsList() != null){
            updatedSession.setToolsList(session.getToolsList());
        } else {
            updatedSession.setToolsList(oldSession.getToolsList());

        }if (oldSession.getOnline() != session.getOnline() && session.getOnline() != null){
            updatedSession.setOnline(session.getOnline());
        } else {
            updatedSession.setOnline(oldSession.getOnline());
        }


        Statement statement;
        try {
            int online = 0;
            if (session.getOnline()){ online = 1;}
            if (!session.getOnline()){ online = 0;}

            String query=String.format("update session set starttime='%s', endtime='%s', classroomnbr='%s', goal='%s', summary='%s', state='%s', module='%s', type='%s', toolslist='%s', online='%s' where idsession='%s'"
                    ,updatedSession.getStartTime(),updatedSession.getEndTime(),updatedSession.getClaasroomNbr()
                    ,updatedSession.getGoal(),updatedSession.getSummary(),updatedSession.getState(),updatedSession.getModule()
                    ,updatedSession.getType(),updatedSession.getToolsList(),online,idSession);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Session mise à jour");
        }catch (Exception e){
            System.out.println(e);
        }
    }



    public void deleteSession(int id){
        Statement statement;
        try{
            String query=String.format("delete from session where idsession= %s",id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Session supprimé");
        }catch (Exception e){
            System.out.println(e);
        }
    }








}
