/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    public Connection connection;

    public DBContext() {
        try {
            String user = "root";
<<<<<<< HEAD
            String pass = "01111996";
=======
            String pass = "Tuong0907@";
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
            String url = "jdbc:mysql://localhost:3306/swp391";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< HEAD
    public static void main(String[] args) {
        DBContext db= new DBContext();
    
        Connection con = db.connection;
        if (con != null){
            System.out.println("ket noi thanh cong");
        }else{
            System.out.println("ket noi that bai");
        }
}
=======
//    public static void main(String[] args) {
//        DBContext db= new DBContext();
//    
//        Connection con = db.connection;
//        if (con != null){
//            System.out.println("ket noi thanh cong");
//        }else{
//            System.out.println("ket noi that bai");
//        }
//}
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
}
