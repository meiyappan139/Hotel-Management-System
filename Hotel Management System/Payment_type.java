

package bootathan;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payment_type 
{
    JFrame frame;
    JLabel l0_payment_lbl;
    JButton b1_qr,b2_offline;
    public Payment_type(String name,String tabl)
    {
        frame=new JFrame();
        frame.setTitle("PAYMENT TYPE");
        l0_payment_lbl=new JLabel("CHOOSE YOUR PAYMENT TYPE:");
        b1_qr=new JButton("QR CODE");
        b2_offline=new JButton("CASH");
        
        //PAYMENT LABEL
        l0_payment_lbl.setBounds(100,40,450,30);
        l0_payment_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //QR BUTTON
        b1_qr.setBounds(175,100,150,30);
        b1_qr.setFont(new Font("Verdana",Font.BOLD,20));
        
        //CASH BUTTON
        b2_offline.setBounds(175, 150, 150, 30);
        b2_offline.setFont(new Font("Verdana",Font.BOLD,20));
        
        
        b1_qr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Qr_bill_summary(name, tabl);
            }
        });
        
        b2_offline.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 frame.setVisible(false);
                new Bill_summary(name, tabl);
            }
        });
        
        
        
        frame.add(l0_payment_lbl);
        frame.add(b1_qr);
        frame.add(b2_offline);
        
        frame.setBounds(100, 100, 600, 300);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new Payment_type("mvp", "a1");
    }
}
