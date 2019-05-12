package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Etudiant {

    public Etudiant() {
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
                    mat.add(rs2.getString("nom") + rs2.getString(" prenom"));
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
}
