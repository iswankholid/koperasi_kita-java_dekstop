/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import java.sql.*;

/**
 *
 * @author Siswanto
 */
public class koneksi {
     public koneksi(){}
    Connection con=null;
    Statement st=null;
    public Connection open(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/koperasi","root","");
        return con;
                } catch (SQLException sqle) {
                    System.out.print("Tidak Ada Koneksi Yang Terbuka");
                    return null;
        }catch (Exception e){
        javax.swing.JOptionPane.showMessageDialog(null, "Koneksi Gagal"+e.getMessage());
        System.out.print("Tidak Bisa Membuka Koneksi"+e.getMessage());
        return null;
        }
      }
    public void QUERY(String data, String option){
        try {
            if (con==null)con=open();
            if (st==null)st=con.createStatement();
            st.executeUpdate(data);
 javax.swing.JOptionPane.showMessageDialog(null, "Data Berhasil"+option);           
            } catch (SQLException sqle) {
sqle.printStackTrace();
javax.swing.JOptionPane.showMessageDialog(null,"Data gagal"+option+"salahnya:"+sqle.getMessage());
            }
    }

    void QUERY(String string) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

        Statement createStatement() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    ResultSet executeQuery(String sql) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
} 