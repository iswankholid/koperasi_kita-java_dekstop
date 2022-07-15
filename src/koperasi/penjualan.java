/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import java.io.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sun.misc.Cleaner;

/**
 *
 * @author Siswanto
 */
public class penjualan extends javax.swing.JInternalFrame {
String headerjual [] ={"Nama Barang","Jumlah","Total"};
String headerbarang [] ={"Id Barang","Nama","Stock","Harga","Kategori"};
Connection con;
Statement st;
koneksi kon=new koneksi();
private DefaultTableModel tabMode1;
private DefaultTableModel tabMode2;
String query;
    /**
     * Creates new form penjualan
     */
    public penjualan() {
        initComponents();
        user();
        nilaifaktur();
        tampilDataTabelBarang();
    }

public void removeTable1(){
    try {
        for (int t=tabMode1.getRowCount();t>0;t--)
        {tabMode1.removeRow(0);}
    } catch (Exception ex) {
    System.out.println(ex);
    }
}
    
public void tampilDataTabelBarang(){
removeTable1();
            try {
                con=kon.open();
                st=con.createStatement();
                query="select*from barang";
                ResultSet rs= st.executeQuery(query);
                while(rs.next()){
                String a=rs.getString("idbarang");
                String b =rs.getString("namabarang");
                String c=rs.getString("stock");
                String d=rs.getString("harga");
                String e=rs.getString("kategori");
                String data[]={a,b,c,d,e};
                tabMode1.addRow(data);
                }
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Data gagal masuk tabel"+sqle);
            }
}
    
    void nilaifaktur(){
    try {
        con = kon.open();
        st = con.createStatement();
        query = "select max(idpenjualan) nilai from masterpenjulan";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
       String kode = rs.getString("nilai");
       int a,b;
       a = Integer.parseInt(kode);
       b = a+1;
     txtfaktur.setText(String.valueOf(b));
        }
                } catch (SQLException sqle) { JOptionPane.showMessageDialog(rootPane, "Data Gagal di Simpan"+sqle.getMessage());
        }
}
    
void total(){
    try {
        con = kon.open();
        st = con.createStatement();
       ResultSet rs = st.executeQuery("select sum(total) nilai from masterpenjulan where idpenjualan= "+txtfaktur.getText());
        while(rs.next()){
        String total = rs.getString("nilai");
       lbltotal.setText(total);
        }
    } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Data gagal masuk tabel"+sqle);
            }
}

void oke(){
    
 try {
kon.QUERY("insert into penjualan (idpenjualan,user,total) values ('"+txtfaktur.getText()+"','"+
                                                                          txtuser.getText()+"','"+
                                                                     lbltotal.getText()+"')","simpan");     
    } catch (Exception sqle) {
        JOptionPane.showMessageDialog(rootPane, "Data Gagal di Simpan"+sqle.getMessage());
    }
}

void cancel(){
txtalamat.setText("");
txtnamapel.setText("");
txtnamapel.requestFocus();
{
try {
       kon.QUERY("delete from masterpenjulan where idpenjualan='"+txtfaktur.getText()+"' ","hapus");
      tampiljual();      
            } catch (Exception sqle) {
        JOptionPane.showMessageDialog(rootPane,"data gagal dihapus"+sqle.getMessage());
    }
}
}

 void user(){
   {
    File file = new File("d:/myfile.txt");
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    
    try{
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        while (dis.available()!=0){
        txtuser.setText(dis.readLine());
        }
        fis.close();
        bis.close();
        dis.close();
    }
    catch (FileNotFoundException e){
    e.printStackTrace();
    }
    catch (IOException e){
        e.printStackTrace();
    }
}
    }
 public void removeTable2(){
    try {
        for (int t=tabMode2.getRowCount();t>0;t--)
        {tabMode2.removeRow(0);}
    } catch (Exception ex) {
    System.out.println(ex);
    }
}

void tampiljual(){
removeTable2();
            try {
                con=kon.open();
                st=con.createStatement();
                query="select*from masterpenjulan where idpenjualan="+txtfaktur.getText()+"";
                ResultSet rs= st.executeQuery(query);
                while(rs.next()){
                String a =rs.getString("namabarang");
                String b=rs.getString("jumlahbarang");
                String c=rs.getString("total");
               String data[]={a,b,c};
                tabMode2.addRow(data);
                }
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Data gagal masuk tabel"+sqle);
            }
}

void inputbarang(){
String idbarang,namabarang,jumlahbarang,harga;
jumlahbarang = JOptionPane.showInputDialog("Masukan Jumlah Barang");
int jumlah_barang=Integer.parseInt(jumlahbarang);
harga=String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3));
namabarang=String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1));
int jumlah,hargabarang;
hargabarang=Integer.parseInt(harga);
jumlah=jumlah_barang*hargabarang;
{
try {
kon.QUERY("insert into masterpenjulan(idpenjualan,iduser,namabarang,namapembeli,alamatpembeli,jumlahbarang,total) values ('"+txtfaktur.getText()+"','"+
                                                                        txtuser.getText()+"','"+
                                                                       namabarang+"','"+
                                                                        txtnamapel.getText()+"','"+
                                                                       txtalamat.getText()+"','"+
                                                                       jumlah_barang+"','"+
                                                                       jumlah+"')","simpan");
tampiljual();      
    } catch (Exception sqle) {
        JOptionPane.showMessageDialog(rootPane, "Data Gagal di Simpan"+sqle.getMessage());
    }
}
}

void kembalian(){
int ttl,kmb,byr;
ttl=Integer.parseInt(lbltotal.getText());
byr=Integer.parseInt(txtbayar.getText());
kmb=byr-ttl;
lblkembalian.setText(String.valueOf(kmb));
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtuser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtfaktur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnamapel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnok = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblkembalian = new javax.swing.JLabel();
        btnproses = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("MASTER PENJUALAN");

        jLabel2.setText("Nama Admin:");

        txtuser.setText("jLabel3");

        jLabel3.setText("ID Penjualan");

        jLabel4.setText("Nama Pelanggan");

        jLabel5.setText("Alamat");

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabMode1=new DefaultTableModel(null,headerbarang);
        jTable1.setModel(tabMode1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel6.setText("Daftar Nama Barang");

        jLabel7.setText("*Klik Untuk Memilih Barang");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabMode2=new DefaultTableModel(null,headerjual);
        jTable2.setModel(tabMode2);
        jScrollPane3.setViewportView(jTable2);

        btnok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/check.png"))); // NOI18N
        btnok.setText("Transaksi Ok");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/x(1).png"))); // NOI18N
        btncancel.setText("Batalkan Transaksi");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        jLabel8.setText("Total");

        lbltotal.setText("TOTAL");

        jLabel10.setText("Bayar");

        jLabel11.setText("Kembalian");

        lblkembalian.setText("KEMBALIAN");

        btnproses.setText("Proses");
        btnproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprosesActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/exit(2).png"))); // NOI18N
        jButton1.setText("Tutup");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtnamapel)
                            .addComponent(jScrollPane1)
                            .addComponent(txtfaktur))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(41, 41, 41)
                                .addComponent(lblkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnproses, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtuser))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtfaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnamapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(95, 95, 95))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnok)
                            .addComponent(btncancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbltotal))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnproses)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblkembalian))))
                .addGap(15, 15, 15)
                .addComponent(jButton1)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
total();

                // TODO add your handling code here:
    }//GEN-LAST:event_btnokActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
inputbarang();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
cancel();        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprosesActionPerformed
kembalian();
oke();
// TODO add your handling code here:
    }//GEN-LAST:event_btnprosesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
 
    
   


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new penjualan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnok;
    private javax.swing.JButton btnproses;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblkembalian;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtfaktur;
    private javax.swing.JTextField txtnamapel;
    private javax.swing.JLabel txtuser;
    // End of variables declaration//GEN-END:variables
}
