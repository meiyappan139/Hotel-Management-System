
package bootathan;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

public class Option_page 
{
    
    JFrame frame;
    JButton b1_order_more_btn,b2_pay_bill_btn;
    public Option_page(String name,String table_no) 
    {
        frame=new JFrame();
        frame.setTitle("OPTION PAGE");
        b1_order_more_btn=new JButton("ORDER MORE");
        b2_pay_bill_btn=new JButton("PAY BILL");
        
        //ORDER MORE BUTTON 
        b1_order_more_btn.setBounds(100, 200, 200, 30);
        b1_order_more_btn.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //PAY BILL
        b2_pay_bill_btn.setBounds(350, 200, 200, 30);
        b2_pay_bill_btn.setFont(new Font("Verdana", Font.BOLD, 20));
        
        b1_order_more_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(name+"  "+table_no);
               new Order_more_form(name,table_no);
                frame.setVisible(false);
               
            }
        });
        
        b2_pay_bill_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //new Bill_summary(name,table_no);
                new Payment_type(name, table_no);
                frame.setVisible(false);
            }
        });
        
        
        
        frame.add(b1_order_more_btn);
        frame.add(b2_pay_bill_btn);
        
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        frame.setLayout(null);
        frame.setBounds(100, 100, 700, 500);
        frame.setVisible(true);
        
        
   }
    public static void main(String[] args) 
    {
        new Option_page("mvp","a1");
    }
    
}