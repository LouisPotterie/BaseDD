package com.company;
import com.mysql.cj.protocol.x.StatementExecuteOkMessageListener;

import java.sql.*;
import java.util.Scanner;

public class Administrateur {

    private String prenom;
    private String nom;
    private String sexe;

    public Administrateur(String prenom, String nom, String sexe) {
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int creationIdentite(Statement stmt,Connection conn)
    {
        System.out.println("Vous allez ajouter une identite \n\nSaisir son nom :");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        System.out.println("Son prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Sexe : ");
        String sexe = sc.nextLine();
        int identite_id = 0;

        try {
            PreparedStatement requete = conn.prepareStatement("INSERT INTO identite (prenom,nom,sexe) VALUES (?,?,?)");
            requete.setString(1,prenom);
            requete.setString(2,nom);
            requete.setString(3,sexe);
            requete.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {


            ResultSet result = stmt.executeQuery("SELECT COUNT(identite_id) FROM identite");
            result.next();
            identite_id = result.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return identite_id;

    }



    public static int NouveauMatricule(Statement stmt, Connect conn) throws SQLException{


        int Matricule;

        ResultSet result_count_Matricule = stmt.executeQuery("SELECT COUNT(matricule_etudiant_id) FROM etudiant");
        result_count_Matricule.next();
        int nbr_eleve_m = result_count_Matricule.getInt(1);
        if (nbr_eleve_m == 0) {
            return 2019001;
        }
        else {
            ResultSet result_Matricule = stmt.executeQuery("SELECT * FROM etudiant");
            result_Matricule.next();
            int nbr_matricule = result_Matricule.getInt(1);
            Matricule = nbr_matricule + nbr_eleve_m;
        }

        return Matricule;
    }



    public void creationGroupe(Statement stmt, Connection conn, int a)
    {
        try {
            System.out.println("Création d'un nouveau groupe");
           //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");
           PreparedStatement requete = conn.prepareStatement("INSERT INTO groupe VALUES (?)");
           requete.setInt(1,a);
           requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationEtudiant(Statement stmt, Connection conn, int matricule_etudiant_id, int coordonnee_id, int identite_id, int responsable_id)
    {
        try {
            System.out.println("Création d'un nouveau etudiant");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO etudiant (matricule_etudiant_id, coordonnee_id, identite_id, responsable_id) VALUES (?,?,?,?)");
            requete.setInt(1,matricule_etudiant_id);
            requete.setInt(2,coordonnee_id);
            requete.setInt(3,identite_id);
            requete.setInt(4,responsable_id);

            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationProfesseur(Statement stmt, Connection conn, int coordonne_id ,int id)
    {
        try {
            System.out.println("Création d'un nouveau professeur");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            int matricule_professeur_id = 1 + (int)(Math.random() * ((1000 - 1) + 1));;

            PreparedStatement requete = conn.prepareStatement("INSERT INTO professeur (matricule_professeur_id,coordonnée_id,identité_id) VALUES (?,?,?)");
            requete.setInt(1,matricule_professeur_id);
            requete.setInt(2, coordonne_id);
            requete.setInt(3,id);


            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationResponsable(Statement stmt, Connection conn, int responsable_id, int coordonnee_id, int identite_id)
    {
        try {
            System.out.println("Création d'un nouveau responsable");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO responsable (responsable_id, coordonnee_id, identite_id) VALUES (?,?,?)");
            requete.setInt(1,responsable_id);
            requete.setInt(2,coordonnee_id);
            requete.setInt(3,identite_id);

            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationCours(Statement stmt, Connection conn, int cours_id, String nom, String description, int annee, int evaluation_id, int matricule_professeur_id, int coefficient, int pourcentage_DE, int pourcentage_TP, int pourcentage_Projet)
    {
        try {
            System.out.println("Création d'un nouveau cours ");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO cours (cours_id, nom, description, année, coefficient_cours, evaluation_id, matricule_professeur_id, pourcentage_DE, pourcentage_TP, pourcentage_Projet) VALUES (?,?,?,?,?,?,?,?,?,?)");
            requete.setInt(1,cours_id);
            requete.setString(2,nom);
            requete.setString(3,description);
            requete.setInt(4,annee);
            requete.setInt(5,evaluation_id);
            requete.setInt(6, matricule_professeur_id);
            requete.setInt(7,coefficient);
            requete.setInt(8,pourcentage_DE);
            requete.setInt(9,pourcentage_TP);
            requete.setInt(10,pourcentage_Projet);

            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationEvaluation(Statement stmt, Connection conn, int eval_id,int note, String type_eval, int pourcentage, int cours_id, int etudiant_id  )
    {
        try {
            System.out.println("Création d'une evaluation ");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO evaluation (evaluation_id, note, type_evaluation, pourcentage, cours_id, etudiant_id) VALUES (?,?,?,?,?,?)");
            requete.setInt(1,eval_id);
            requete.setInt(2,note);
            requete.setString(3,type_eval);
            requete.setInt(4,pourcentage);
            requete.setInt(5,cours_id);
            requete.setInt(6,etudiant_id);

            requete.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEvaluation(Statement stmt, Connection conn, int eval_id) {
        try {
            int choix;
            int note;
            int pourcentage;
            String type_eval;
            Scanner sc = new Scanner(System.in);
            System.out.println("Que voulez-vous modifier");
            System.out.println("1. Note\n2. type_eval\n3. pourcentage\nChoix :");
            choix = sc.nextInt();
            switch(choix){
                case 1:
                    System.out.println("Inserez la nouvelle note :");
                    note = sc.nextInt();
                    PreparedStatement requete_note = conn.prepareStatement("UPDATE evaluation SET note = "+ note +" WHERE evaluation_id = "+eval_id+";");
                    requete_note.execute();
                    break;
                case 2:
                    System.out.println("Inscrivez le nouveau type d'evaluation :");
                    type_eval = sc.nextLine();
                    PreparedStatement requete_type = conn.prepareStatement("UPDATE evaluation SET type_evaluation = \""+type_eval+"\" WHERE evaluation_id = "+eval_id+";");
                    requete_type.execute();
                    break;
                case 3:
                    System.out.println("Inserez le nouveau pourcentage :");
                    pourcentage = sc.nextInt();
                    PreparedStatement requete_pourcentage = conn.prepareStatement("UPDATE evaluation SET pourcentage = "+ pourcentage +" WHERE evaluation_id = "+eval_id+";");
                    requete_pourcentage.execute();
                    break;

                default:
                    System.out.println("choix non valide");
                    break;
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationReleveNote(Statement stmt, Connection conn, int etudiant_id) {
        try {
            ResultSet res = stmt.executeQuery("SELECT e.*, c.nom FROM evaluation e, cours c WHERE e.etudiant_id = "+etudiant_id+" and e.cours_id = c.cours_id;");
            while(res.next()) {
                System.out.println("\n");
                System.out.println("Cours :"+res.getString("c.nom"));
                System.out.println("Note :"+res.getString("e.note"));
                System.out.println("Type d'evaluation :"+res.getString("e.type_evaluation"));
                System.out.println("% :"+res.getString("e.pourcentage"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficherListeTD(Statement stmt, Connection conn, int groupe_id,) {
        try {
            ResultSet res = stmt.executeQuery("SELECT g.*, e.* FROM groupe_etudiant g, etudiant e  WHERE g.groupe_id = "+groupe_id+" and g.matricule_etudiant_id = e.matricule_etudiant_id;");
            while(res.next()) {
                System.out.println("\n");

            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
