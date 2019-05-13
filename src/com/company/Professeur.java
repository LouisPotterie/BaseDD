package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Professeur {

    public Professeur() {
    }

    public void afficherListeTD2(Statement stmt, Connection conn, int groupe_id)
    {

        ArrayList<String> mat = new ArrayList<String>();
        int stockage =0;

        try {

            PreparedStatement requete = conn.prepareStatement("SELECT etudiant.matricule_etudiant_id, etudiant.identite_id FROM  groupe_etudiant, etudiant WHERE groupe_etudiant.groupe_id = ? AND groupe_etudiant.matricule_etudiant_id = etudiant.matricule_etudiant_id ");

            requete.setInt(1,groupe_id);

            ResultSet rs = requete.executeQuery();
            while (rs.next())
            {
                stockage = rs.getInt("identite_id");
                PreparedStatement requete2 = conn.prepareStatement("SELECT prenom, nom FROM identite WHERE identite_id = ?");
                requete2.setInt(1,stockage);
                ResultSet rs2 = requete2.executeQuery();
                while (rs2.next()) {
                    mat.add(rs2.getString("nom") + rs2.getString("prenom"));
                }
            }

            System.out.println("La liste : ");
            for (String m : mat)
            {
                System.out.println(m);
            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void creationReleveNote(Statement stmt, Connection conn, int etudiant_id) {
        try {
            ResultSet res = stmt.executeQuery("SELECT e.*, c.nom FROM evaluation e, cours c WHERE e.etudiant_id = "+etudiant_id+" and e.cours_id = c.cours_id;");
            System.out.println(" ~~~~~ Bulletin de note ~~~~~ ");
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

    public void creationEvaluation(Statement stmt, Connection conn ,int note, String type_eval, int pourcentage, int cours_id, int etudiant_id)
    {
        try {
            System.out.println("Création d'une evaluation ");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO evaluation (note, type_evaluation, pourcentage, cours_id, etudiant_id) VALUES (?,?,?,?,?)");

            requete.setInt(1,note);
            requete.setString(2,type_eval);
            requete.setInt(3,pourcentage);
            requete.setInt(4,cours_id);
            requete.setInt(5,etudiant_id);

            requete.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
