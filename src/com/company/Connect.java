package com.company;
import java.sql.*;
import java.lang.*;
import java.util.*;

public class Connect {
    public static void main(String[] args) {

        Connection con = null;

        try {
            //Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost:3306/bdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String passwd = "";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

            Statement stmt = conn.createStatement();
            Administrateur admin = new Administrateur("Louis","Potterie","M");

            int type_user = connexion(stmt,conn);

            if (type_user == 1)
            {

            }

            if (type_user == 2)
            {

            }

            if (type_user == 3)
            {

            }



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

            //Creation cours
            /*
            admin.creationCours(stmt,conn);
            */


            /* Creation etudiant
            System.out.println(" ~~ Création du responsable de l'etudiant ~~ \n");

            int adresse = admin.creationAdresse(stmt,conn);
            int coordonnee = admin.creationCoordonnee(stmt,conn,adresse);
            int id = admin.creationIdentite(stmt,conn);

            System.out.println("L'etudiant et le responsable ont ils la meme adresse ? (1 pour oui) / (2 pour non) \n");

            Scanner kb = new Scanner(System.in);

            int choix = kb.nextInt();

            if (choix == 2)
            {
                adresse =0;
                adresse=admin.creationAdresse(stmt,conn);
            }

            System.out.println(" \n ~~ Création du de l'etudiant ~~ \n");

            int coordonnee_id = admin.creationCoordonnee(stmt,conn,adresse);
            int identite_id = admin.creationIdentite(stmt,conn);


            int responsable_id = admin.creationResponsable(stmt,conn,coordonnee,id);
            admin.creationEtudiant(stmt,conn, coordonnee_id, identite_id, responsable_id);

            */

            //admin.associationProfesseurCours(stmt,conn);



            /*Administrateur  admin = new Administrateur("Louis","Potterie","M");
            //admin.creationEvaluation(stmt,conn,1,15,"DE",60,1);
            //admin.updateEvaluation(stmt,conn,1);
            admin.creationReleveNote(stmt,conn,1);
            */

            stmt.close();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int connexion(Statement stmt, Connection conn)
    {
        Scanner kb = new Scanner(System.in);
        Scanner kb2 = new Scanner(System.in);
        int correct = 1;
        int type_user =0;
        do {

            System.out.println("Id : ");
            int id = kb.nextInt();

            System.out.println("Mot de passe : ");

            String password = kb2.nextLine();

            try {
                PreparedStatement requete = conn.prepareStatement("SELECT * FROM utilisateur WHERE EXISTS (SELECT id, password, type_user FROM utilisateur WHERE id = ? AND password = ?)");
                requete.setInt(1, id);
                requete.setString(2, password);
                ResultSet rs = requete.executeQuery();
                while (rs.next())
                {
                    type_user = rs.getInt("type_user");
                }

                if (type_user == 1 || type_user == 2 || type_user ==3)
                {
                    correct = 0;
                }



            } catch (Exception e) {
                e.printStackTrace();
            }


        } while(correct!=0);

        return type_user;
    }
}