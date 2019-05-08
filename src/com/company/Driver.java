package com.company;

import java.sql.*;

public class Driver {
    public static void main(String[] args) {
        try {
            //conn to DB


            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_bdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            //Create statement
            Statement stmt = conn.createStatement();
            //SQL Query
            ResultSet res = stmt.executeQuery("select * from nom");
            //Process result
            while(res.next()) {
                System.out.println(res.getString("A")+res.getString("B")+res.getString("C"));
            }

        }
        catch (Exception exc){
            exc.printStackTrace();

        }
    }
}
