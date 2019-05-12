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


            int type_user = connexion(stmt,conn);
            System.out.println(type_user);

            if (type_user == 1)
            {
                Administrateur admin = new Administrateur();
                int choix=0;
                int exit =0;

                do {
                    System.out.println(" ~~~~~ Menu Administration ~~~~~ \n");
                    System.out.println(" 1) Ajouter un Professeur");
                    System.out.println(" 2) Ajouter un Eleve");
                    System.out.println(" 3) Assigner un Professeur à un cours ");
                    System.out.println(" 4) Assigner un Etudiant à un groupe ");
                    System.out.println(" 5) Ajouter une note ");
                    System.out.println(" 6) Voir les bulletins Eleve");
                    System.out.println(" 7) Voir la liste des etudiants d'un groupe ");
                    System.out.println(" 8) Mettre à jour une note  ");

                    System.out.println(" 9) Quitter ");

                    System.out.println("Que voulez-vous faire ? ");
                    Scanner kb = new Scanner(System.in);
                    choix = kb.nextInt();

                    switch (choix)
                    {
                        case 1:

                            int adresse = admin.creationAdresse(stmt,conn);
                            int coordonnee = admin.creationCoordonnee(stmt,conn,adresse);
                            int id = admin.creationIdentite(stmt,conn);
                            admin.creationProfesseur(stmt,conn,coordonnee,id);
                            System.out.println("Voulez vous assigner le professeur à un cours ?");
                            Scanner key = new Scanner(System.in);
                            int answer = key.nextInt();
                            if (answer == 1 )
                            {
                                admin.associationProfesseurCours(stmt,conn);
                            }

                            break;

                        case 2:

                            System.out.println(" ~~ Création du responsable de l'etudiant ~~ \n");

                            int adresse2 = admin.creationAdresse(stmt,conn);
                            int coordonnee2 = admin.creationCoordonnee(stmt,conn,adresse2);
                            int id2 = admin.creationIdentite(stmt,conn);

                            System.out.println("L'etudiant et le responsable ont ils la meme adresse ? (1 pour oui) / (2 pour non) \n");

                            Scanner kb2 = new Scanner(System.in);

                            int choix2 = kb2.nextInt();

                            if (choix2 == 2)
                            {
                                adresse =0;
                               adresse=admin.creationAdresse(stmt,conn);
                            }

                            System.out.println(" \n ~~ Création du de l'etudiant ~~ \n");

                            int coordonnee_id = admin.creationCoordonnee(stmt,conn,adresse2);
                            int identite_id = admin.creationIdentite(stmt,conn);


                            int responsable_id = admin.creationResponsable(stmt,conn,coordonnee2,id2);
                            admin.creationEtudiant(stmt,conn, coordonnee_id, identite_id, responsable_id);

                            System.out.println("Voulez vous assigner l'etudiant à un cours ?");
                            Scanner key2 = new Scanner(System.in);
                            int answer2 = key2.nextInt();
                            if (answer2 == 1 )
                            {
                                admin.associationEtudiantGroupe(stmt,conn);
                            }

                            break;

                        case 3:

                            admin.associationProfesseurCours(stmt,conn);

                            break;

                        case 4:

                            admin.associationEtudiantGroupe(stmt,conn);

                            break;

                        case 5 :

                            Scanner key5 = new Scanner(System.in);
                            Scanner key3 = new Scanner(System.in);

                            System.out.println("Quelle est la note ? ");
                            int note = key5.nextInt();

                            System.out.println("Quelle est le type d'evaluation DE / TP / PJ  ? ");
                            String type_eval = key3.nextLine();

                            System.out.println("Quel est le pourcentage ? ");
                            int pourcentage = key5.nextInt();

                            System.out.println("Quel est le cours id correspondant ?");

                            int cours_id = key5.nextInt();

                            System.out.println("Quel est l'id de l'etudiant ? ");

                            int etudiant_id = key5.nextInt();

                            admin.creationEvaluation(stmt, conn, note, type_eval, pourcentage,cours_id, etudiant_id);
                            break;

                        case 6:

                            Scanner input = new Scanner(System.in);
                            System.out.println("Quel est l'id de l'etudiant ? ");
                            int student_id = input.nextInt();
                            admin.creationReleveNote(stmt, conn,student_id);

                            break;

                        case 7:

                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Quel est le groupe id du groupe à afficher ? ");
                            int grp_id = input2.nextInt();
                            admin.afficherListeTD2(stmt,conn, grp_id);

                            break;

                        case 8:

                            admin.updateEvaluation(stmt,conn);
                            break;

                        case 9:

                            exit = 1;

                            break;
                    }
                }while(exit!=1);





            }

            if (type_user == 2)
            {
                Professeur prof = new Professeur();

                Administrateur admin = new Administrateur();
                int choix=0;
                int exit =0;

                do {
                    System.out.println(" ~~~~~ Menu Professeur  ~~~~~ \n");
                    System.out.println(" 1) Ajouter une note ");
                    System.out.println(" 2)Voir les bulletins Eleve");
                    System.out.println(" 3) Voir la liste des etudiants d'un groupe ");

                    System.out.println(" 5) Quitter ");

                    System.out.println("Que voulez-vous faire ? ");
                    Scanner kb = new Scanner(System.in);
                    choix = kb.nextInt();

                    switch (choix)
                    {
                        case 1:

                            Scanner key2 = new Scanner(System.in);
                            Scanner key3 = new Scanner(System.in);

                            System.out.println("Quelle est la note ? ");
                            int note = key2.nextInt();

                            System.out.println("Quelle est le type d'evaluation DE / TP / PJ  ? ");
                            String type_eval = key3.nextLine();

                            System.out.println("Quel est le pourcentage ? ");
                            int pourcentage = key2.nextInt();

                            System.out.println("Quel est le cours id correspondant ?");

                            int cours_id = key2.nextInt();

                            System.out.println("Quel est l'id de l'etudiant ? ");

                            int etudiant_id = key2.nextInt();

                            prof.creationEvaluation(stmt, conn, note, type_eval, pourcentage,cours_id, etudiant_id);

                            break;

                        case 2:

                            Scanner input = new Scanner(System.in);
                            System.out.println("Quel est l'id de l'etudiant ? ");
                            int student_id = input.nextInt();
                            prof.creationReleveNote(stmt, conn,student_id);


                            break;

                        case 3:

                            prof.afficherListeTD2(stmt,conn,1);
                            break;

                        case 4:


                            break;

                        case 5 :

                            exit = 1;
                            break;

                    }
                }while(exit!=1);




            }

            if (type_user == 3)
            {
                Etudiant etudiant = new Etudiant();

                int choix = 0;



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
        int exist = 0;

        do {

            System.out.println("Id : ");
            String id = kb.nextLine();

            System.out.println("Mot de passe : ");

            String password = kb2.nextLine();

            try {
                PreparedStatement requete = conn.prepareStatement("SELECT * FROM utilisateur  WHERE EXISTS (SELECT id, password, type_user FROM utilisateur WHERE id = ? AND password = ?)");
                requete.setString(1, id);
                requete.setString(2, password);
                ResultSet rs = requete.executeQuery();
                while (rs.next())
                {
                    exist = rs.getInt("type_user");
                }

                if(exist!=0)
                {
                    PreparedStatement requete2 = conn.prepareStatement("SELECT type_user FROM utilisateur  WHERE id = ? AND password = ?");
                    requete2.setString(1, id);
                    requete2.setString(2, password);
                    ResultSet rs2 = requete2.executeQuery();
                    while (rs2.next())
                    {
                        type_user = rs2.getInt("type_user");

                    }
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