/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Siswanto
 */
public class background extends JPanel{
    private Image image;

    public background(){
image=new ImageIcon(getClass().getResource("/gambar/aufa.jpg")).getImage();
}
    @Override

protected void
paintComponent(Graphics grphcs){
super.paintComponent(grphcs);

Graphics2D gd = (Graphics2D)grphcs.create();
gd.drawImage(image,0,0,getWidth(),getHeight(),null);
gd.dispose();

        }
}