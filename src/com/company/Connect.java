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
                    choix = inputWithOnlyInt();

                    switch (choix)
                    {
                        case 1:

                            int adresse = admin.creationAdresse(stmt,conn);
                            int coordonnee = admin.creationCoordonnee(stmt,conn,adresse);
                            int id = admin.creationIdentite(stmt,conn);
                            admin.creationProfesseur(stmt,conn,coordonnee,id);
                            System.out.println("Voulez vous assigner le professeur à un cours ?");
                            Scanner key = new Scanner(System.in);
                            int answer = inputWithOnlyInt();
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

                            int choix2 = inputWithOnlyInt();

                            if (choix2 == 2)
                            {
                                adresse =0;
                               adresse=admin.creationAdresse(stmt,conn);
                            }

                            System.out.println(" \n ~~ Création du de l'etudiant ~~ \n");

                            int coordonnee_id = admin.creationCoordonnee(stmt,conn,adresse2);
                            int identite_id = admin.creationIdentite(stmt,conn);


                            int responsable_id = admin.creationResponsable(stmt,conn,coordonnee2,id2);
                            int new_etudiant = admin.creationEtudiant(stmt,conn, coordonnee_id, identite_id, responsable_id);

                            System.out.println("Voulez vous assigner l'etudiant à un cours ? 1 pour oui 2 pour non ");
                            Scanner key2 = new Scanner(System.in);
                            int answer2 = inputWithOnlyInt();
                            if (answer2 == 1 )
                            {
                                admin.associationEtudiantGroupe2(stmt,conn, new_etudiant);
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
                            int note = inputWithOnlyInt();

                            System.out.println("Quelle est le type d'evaluation DE / TP / PJ  ? ");
                            String type_eval = key3.nextLine();

                            System.out.println("Quel est le pourcentage ? ");
                            int pourcentage = inputWithOnlyInt();

                            System.out.println("Quel est le cours id correspondant ?");

                            int cours_id = inputWithOnlyInt();

                            System.out.println("Quel est l'id de l'etudiant ? ");

                            int etudiant_id = inputWithOnlyInt();

                            admin.creationEvaluation(stmt, conn, note, type_eval, pourcentage,cours_id, etudiant_id);
                            break;

                        case 6:

                            Scanner input = new Scanner(System.in);
                            System.out.println("Quel est l'id de l'etudiant ? ");
                            int student_id = inputWithOnlyInt();
                            admin.creationReleveNote(stmt, conn,student_id);

                            break;

                        case 7:

                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Quel est le groupe id du groupe à afficher ? ");
                            int grp_id = inputWithOnlyInt();
                            admin.afficherListeTD2(stmt,conn, grp_id);

                            break;

                        case 8:

                            admin.updateEvaluation(stmt,conn);
                            break;

                        case 9:

                            System.out.println("Etes vous sur de fermer l'application ? (1 : oui) (2 : non)");
                            Scanner abc = new Scanner(System.in);
                            int end = inputWithOnlyInt();

                            if (end == 1)
                            {
                                exit = 1;
                            }

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
                    System.out.println(" 2) Voir les bulletins Eleve");
                    System.out.println(" 3) Voir la liste des etudiants d'un groupe ");

                    System.out.println(" 5) Quitter ");

                    System.out.println("Que voulez-vous faire ? ");
                    Scanner kb = new Scanner(System.in);
                    choix = inputWithOnlyInt();

                    switch (choix)
                    {
                        case 1:

                            Scanner key2 = new Scanner(System.in);
                            Scanner key3 = new Scanner(System.in);

                            System.out.println("Quelle est la note ? ");
                            int note = inputWithOnlyInt();

                            System.out.println("Quelle est le type d'evaluation DE / TP / PJ  ? ");
                            String type_eval = key3.nextLine();

                            System.out.println("Quel est le pourcentage ? ");
                            int pourcentage = inputWithOnlyInt();

                            System.out.println("Quel est le cours id correspondant ?");

                            int cours_id = inputWithOnlyInt();

                            System.out.println("Quel est l'id de l'etudiant ? (ex : 2019001) ");

                            int etudiant_id = inputWithOnlyInt();

                            prof.creationEvaluation(stmt, conn, note, type_eval, pourcentage,cours_id, etudiant_id);

                            break;

                        case 2:

                            Scanner input = new Scanner(System.in);
                            System.out.println("Quel est l'id de l'etudiant ? ");
                            int student_id = inputWithOnlyInt();
                            prof.creationReleveNote(stmt, conn,student_id);


                            break;

                        case 3:

                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Quel est le groupe id du groupe à afficher ? ");
                            int grp_id = inputWithOnlyInt();
                            prof.afficherListeTD2(stmt,conn,grp_id);
                            break;

                        case 4:


                            break;

                        case 5 :

                            System.out.println("Etes vous sur de fermer l'application ? (1 : oui) (2 : non)");
                            Scanner abc = new Scanner(System.in);
                            int end = inputWithOnlyInt();

                            if (end == 1)
                            {
                                exit = 1;
                            }

                    }
                }while(exit!=1);




            }

            if (type_user == 3)
            {
                Etudiant etudiant = new Etudiant();
                int choix =0;
                int exit =0;

                do {

                    System.out.println(" ~~~~~ Menu Etudiant  ~~~~~ \n");
                    System.out.println(" 1) Bulletin de note ");
                    System.out.println(" 2) Voir la liste des etudiants d'un groupe ");
                    System.out.println(" 3) Quitter ");

                    System.out.println("Que voulez-vous faire ? ");
                    Scanner kb = new Scanner(System.in);
                    choix = inputWithOnlyInt();

                    switch (choix)
                    {
                        case 1:

                            etudiant.creationReleveNote(stmt,conn);

                            break;

                        case 2:

                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Quel est le groupe id du groupe à afficher ? ");
                            int grp_id = inputWithOnlyInt();
                            etudiant.afficherListeTD2(stmt,conn,grp_id);
                            break;

                        case 3:
                            System.out.println("Etes vous sur de fermer l'application ? (1 : oui) (2 : non)");
                            Scanner abc = new Scanner(System.in);
                            int end = inputWithOnlyInt();

                            if (end == 1)
                            {
                                exit = 1;
                            }
                            break;


                    }
            }while(exit !=1);




            }


            stmt.close();
        }
        catch (Exception e) {
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

            System.out.println("~~~~ Connexion ~~~~");
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


    public static int inputWithOnlyInt()
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("-> ");
        while (!kb.hasNextInt())  //si la selection est différente d'un entier on coninue de demander une saisie
        {
            System.out.print("Veuillez choisir un nombre valide\n-> ");
            kb.next();
        }
        return kb.nextInt();
    }

    public static String inputWithOnlyString()
    {
        Scanner kb2 = new Scanner(System.in);

        System.out.print("-> ");
        while (!kb2.hasNextLine())  //si la selection est différente d'un entier on coninue de demander une saisie
        {
            System.out.print("Veuillez choisir une string valide\n-> ");
            kb2.next();
        }
        return kb2.nextLine();
    }

}