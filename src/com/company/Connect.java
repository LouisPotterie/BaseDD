package com.company;
import java.sql.*;

public class Connect {
    public static void main(String[] args) {

        Connection con = null;
        String QUERY_FIND_ADD = "SELECT * FROM groupe ";
        try {
            //Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost:3306/bdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String passwd = "";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ADD);



            Administrateur admin = new Administrateur("Louis","Potterie","M");

            //Creation prof
            /*
            int adresse = admin.creationAdresse(stmt,conn);
            int coordonnee = admin.creationCoordonnee(stmt,conn,adresse);
            int id = admin.creationIdentite(stmt,conn);
            admin.creationProfesseur(stmt,conn,coordonnee,id);
            */

            //Creation Responsable
            /*
            int adresse = admin.creationAdresse(stmt,conn);
            int coordonnee = admin.creationCoordonnee(stmt,conn,adresse);
            int id = admin.creationIdentite(stmt,conn);
            admin.creationResponsable(stmt,conn,coordonnee,id);
            */

            admin.creationCours(stmt,conn);





            /*Administrateur  admin = new Administrateur("Louis","Potterie","M");
            //admin.creationEvaluation(stmt,conn,1,15,"DE",60,1);
            //admin.updateEvaluation(stmt,conn,1);
            admin.creationReleveNote(stmt,conn,1);
            */
            rset.close();
            stmt.close();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}