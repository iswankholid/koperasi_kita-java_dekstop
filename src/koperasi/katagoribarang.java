/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;
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
public class katagoribarang extends javax.swing.JInternalFrame {
String header [] ={"Kategori","Deskripsi"};
Connection con;
Statement st;
koneksi kon=new koneksi();
private DefaultTableModel tabMode;
String query;
    /**
     * Creates new form katagoribarang
     */
    public katagoribarang() {
        initComponents();
        tampilDataTabel();
    }

    void clear(){
txtdeskripsi.setText("");
txtkategori.setText("");
txtkategori.requestFocus();
}

void removeTable(){
    try {
        for (int t=tabMode.getRowCount();t>0;t--)
        {tabMode.removeRow(0);}
    } catch (Exception ex) {
    System.out.println(ex);
    }
}

void tampilDataTabel(){
removeTable();
            try {
                con=kon.open();
                st=con.createStatement();
                query="SELECT*from kategoribarang";
                ResultSet rs= st.executeQuery(query);
                while(rs.next()){
                String a=rs.getString("kategoribarang");
                String b =rs.getString("deskripsibarang");
                String data[]={a,b};
                tabMode.addRow(data);
                }
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Data gagal ditampilkan"+sqle);
            }
}

void input(){
    try {
kon.QUERY("insert into kategoribarang (kategoribarang,deskripsibarang) values ('"+txtkategori.getText()+"','"+
                                                                         txtdeskripsi.getText()+"')","simpan");
clear();
tampilDataTabel();      
    } catch (Exception sqle) {
        JOptionPane.showMessageDialog(rootPane, "Data Gagal di Simpan"+sqle.getMessage());
    }
}

private void ShowDataTabelKeText(){
    txtkategori.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
    txtdeskripsi.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
        txtkategori.setEditable(true);
        txtdeskripsi.setEditable(true);
                       }


void Delete(){
if (JOptionPane.showConfirmDialog
        (this,"Apakah Anda Yakin Akan Menghapus Data Ini???","Konfirmasi",
        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==
        JOptionPane.YES_OPTION)
{
  try {
       kon.QUERY("delete from kategoribarang where kategoribarang='"+txtkategori.getText()+"' ","hapus");
        tampilDataTabel();
        clear();
            } catch (Exception sqle) {
        JOptionPane.showMessageDialog(rootPane,"data gagal dihapus"+sqle.getMessage());
    }
}
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtkategori = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdeskripsi = new javax.swing.JTextArea();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btntutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("DAFTAR KATAGORI BARANG");

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
        tabMode=new DefaultTableModel(null,header);
        jTable1.setModel(tabMode);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Kategori Barang");

        jLabel3.setText("Deskripsi");

        txtdeskripsi.setColumns(20);
        txtdeskripsi.setRows(5);
        jScrollPane2.setViewportView(txtdeskripsi);

        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/1385845227_Plus__Orange.png"))); // NOI18N
        btntambah.setText("Tambahkan");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/x.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btntutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/exit(2).png"))); // NOI18N
        btntutup.setText("Tutup");
        btntutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntutupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btntambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btntutup, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntambah)
                    .addComponent(btnhapus)
                    .addComponent(btntutup))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
input();        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
Delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btntutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntutupActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btntutupActionPerformed

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
            java.util.logging.Logger.getLogger(katagoribarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(katagoribarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(katagoribarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(katagoribarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new katagoribarang().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btntutup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtdeskripsi;
    private javax.swing.JTextField txtkategori;
    // End of variables declaration//GEN-END:variables
}
