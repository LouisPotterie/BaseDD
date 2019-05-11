package com.company;
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

    public void creationProfesseur(Statement stmt, Connection conn, int matricule_professeur_id, int coordonnee_id, int identite_id)
    {
        try {
            System.out.println("Création d'un nouveau professeur");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO professeur (matricule_professeur_id,coordonnée_id,identité_id) VALUES (?,?,?)");
            requete.setInt(1,matricule_professeur_id);
            requete.setInt(2,coordonnee_id);
            requete.setInt(3,identite_id);


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

    public void creationEvaluation(Statement stmt, Connection conn, int eval_id,int note, String type_eval, int pourcentage, int cours_id  )
    {
        try {
            System.out.println("Création d'une evaluation ");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO evaluation (evaluation_id, note, type_evaluation, pourcentage, cours_id) VALUES (?,?,?,?,?)");
            requete.setInt(1,eval_id);
            requete.setInt(2,note);
            requete.setString(3,type_eval);
            requete.setInt(4,pourcentage);
            requete.setInt(5,cours_id);


            requete.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


}
