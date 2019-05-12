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

    public Administrateur() {
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

    public int creationAdresse(Statement stmt,Connection conn)
    {
        System.out.println("Vous allez ajouter une adresse \n\n Saisir son numero de rue :");
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int numero_rue = sc.nextInt();
        System.out.println("Nom de rue : ");
        String nom_rue = sc1.nextLine();
        System.out.println("Code Postal : ");
        int code_postal= sc.nextInt();
        System.out.println("Ville: ");
        String ville= sc1.nextLine();
        int adresse_id = 0;

        try {
            PreparedStatement requete = conn.prepareStatement("INSERT INTO adresse (numero_rue,nom_rue,code_postal,ville) VALUES (?,?,?,?)");
            requete.setInt(1,numero_rue);
            requete.setString(2,nom_rue);
            requete.setInt(3, code_postal);
            requete.setString(4, ville);
            requete.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {


            ResultSet result = stmt.executeQuery("SELECT COUNT(adresse_id) FROM adresse");
            result.next();
            adresse_id = result.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adresse_id;

    }

    public int creationCoordonnee(Statement stmt, Connection conn, int adresse_id)
    {
        System.out.println("Vous allez ajouter une Coordonnee  \n\n Saisir son numero de telephone:");
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int numero_telephone = sc.nextInt();
        System.out.println("mail : ");
        String mail = sc1.nextLine();

        //int adresse_id = creationAdresse(stmt,conn);
        int coordonnee_id =0;


        try {
            PreparedStatement requete = conn.prepareStatement("INSERT INTO coordonnee (adresse_id,numero_telephone,mail) VALUES (?,?,?)");
            requete.setInt(1,adresse_id);
            requete.setInt(2,numero_telephone);
            requete.setString(3, mail);
            requete.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {


            ResultSet result = stmt.executeQuery("SELECT COUNT(coordonnee_id) FROM coordonnee");
            result.next();
            coordonnee_id = result.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return coordonnee_id;


    }



    public static int NouveauMatricule(Statement stmt, Connection conn) throws SQLException{


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

    public void creationEtudiant(Statement stmt, Connection conn, int coordonnee_id, int identite_id, int responsable_id)
    {
        try {
            System.out.println("Création d'un nouveau etudiant");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            int matricule_etudiant_id = NouveauMatricule(stmt,conn);

            System.out.println("Matricule " + matricule_etudiant_id);


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

    public void creationProfesseur(Statement stmt, Connection conn,int coordonne_id, int id )
    {
        //int coordonne_id = creationCoordonnee(stmt,conn);
        //int id =creationIdentite(stmt,conn);

        System.out.println("Coordonnee : " + coordonne_id);
        System.out.println("Id :" + id);

        try {
            System.out.println("Création d'un nouveau professeur");




            PreparedStatement requete = conn.prepareStatement("INSERT INTO professeur (coordonnée_id,identité_id) VALUES (?,?)");

            requete.setInt(1, coordonne_id);
            requete.setInt(2, id);
            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }





    public int creationResponsable(Statement stmt, Connection conn, int coordonnee_id, int identite_id)
    {
        try {
            System.out.println("Création d'un nouveau responsable");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");


            PreparedStatement requete = conn.prepareStatement("INSERT INTO responsable (coordonnee_id, identite_id) VALUES (?,?)");

           requete.setInt(1,coordonnee_id);
           requete.setInt(2,identite_id);

            requete.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int responsable_id = 0;

        try {


            ResultSet result = stmt.executeQuery("SELECT COUNT(responsable_id) FROM responsable");
            result.next();
            responsable_id = result.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responsable_id;
    }

    public void creationCours(Statement stmt, Connection conn)
    {


        System.out.println("Vous allez ajouter un cours \n\n Saisir le nom du cours :");
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        String nom = sc.nextLine();
        System.out.println("Description : ");
        String description = sc1.nextLine();
        System.out.println("Annee : ");
        int annee = sc.nextInt();
        System.out.println(" Coefficient : ");
        int coefficient = sc1.nextInt();
        System.out.println(" Pourcentage DE : ");
        int pourcentage_DE = sc1.nextInt();
        System.out.println(" Pourcentage TP : ");
        int pourcentage_TP = sc1.nextInt();
        System.out.println(" Pourcentage projet : ");
        int pourcentage_Projet = sc1.nextInt();


        try {
            System.out.println("Création d'un nouveau cours ");
            //stmt.executeUpdate("INSERT INTO groupe VALUES ('')");

            PreparedStatement requete = conn.prepareStatement("INSERT INTO cours (nom, description, année, coefficient_cours, pourcentage_DE, pourcentage_TP, pourcentage_Projet) VALUES (?,?,?,?,?,?,?)");

            requete.setString(1,nom);
            requete.setString(2,description);
            requete.setInt(3,annee);

            requete.setInt(4,coefficient);
            requete.setInt(5,pourcentage_DE);
            requete.setInt(6,pourcentage_TP);
            requete.setInt(7,pourcentage_Projet);

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

    public void afficherListeTD(Statement stmt, Connection conn, int groupe_id) {
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

    public void associationEtudiantGroupe (Statement stmt, Connection conn)
    {
        int stockage =0;
        int stockage2=0;

        try {
            System.out.println("Choisir un etudiant : ");
            Scanner kb = new Scanner(System.in);

            int matricule_etudiant_id = kb.nextInt();

            PreparedStatement etudiant = conn.prepareStatement("SELECT matricule_etudiant_id FROM etudiant WHERE matricule_etudiant_id = ?");
            etudiant.setInt(1,matricule_etudiant_id);
            ResultSet rs = etudiant.executeQuery();
           while (rs.next())
           {
               stockage = rs.getInt("matricule_etudiant_id");
           }

           System.out.println(stockage);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Choisir un groupe : ");
            Scanner kb = new Scanner(System.in);

            String nom_groupe = kb.nextLine();

            PreparedStatement groupe = conn.prepareStatement("SELECT groupe_id FROM groupe WHERE nom_groupe = ?");
            groupe.setString(1,nom_groupe);
            ResultSet rs = groupe.executeQuery();
            while (rs.next())
            {
                stockage2 = rs.getInt("groupe_id");
            }

            System.out.println(stockage2);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Assignation d'un etudiant à son groupe ");


            PreparedStatement requete = conn.prepareStatement("INSERT INTO groupe_etudiant (groupe_id,matricule_etudiant_id) VALUES (?,?)");

            requete.setInt(1,stockage2);
            requete.setInt(2,stockage);

            requete.execute();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void associationProfesseurCours (Statement stmt, Connection conn)
    {
        int stockage =0;
        int stockage2=0;

        try {
            System.out.println("Choisir un professeur par son nom : ");
            Scanner kb = new Scanner(System.in);

            String nom = kb.nextLine();

            PreparedStatement etudiant = conn.prepareStatement("SELECT matricule_professeur_id FROM professeur INNER JOIN identite ON professeur.identité_id = identite.identite_id WHERE identite.nom = ?");
            etudiant.setString(1,nom);
            ResultSet rs = etudiant.executeQuery();
            while (rs.next())
            {
                stockage = rs.getInt("matricule_professeur_id");
            }

            System.out.println(stockage);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Choisir un cours par son nom : ");
            Scanner kb = new Scanner(System.in);

            String nom_cours = kb.nextLine();

            PreparedStatement cours = conn.prepareStatement("SELECT cours_id FROM cours WHERE nom = ?");
            cours.setString(1,nom_cours);
            ResultSet rs = cours.executeQuery();
            while (rs.next())
            {
                stockage2 = rs.getInt("cours_id");
            }

            System.out.println(stockage2);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Assignation d'un professeur à un cours ");


            PreparedStatement requete = conn.prepareStatement("INSERT INTO professeur_cours (matricule_professeur_id, cours_id) VALUES (?,?)");

            requete.setInt(1,stockage);
            requete.setInt(2,stockage2);

            requete.execute();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
