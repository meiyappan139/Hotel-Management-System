
package bootathan;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Customer_Login_form 
{
   JLabel l0_login_form_lbl,l1_email_id_lbl,l2_password_lbl;
   JTextField t1_email_id_txt;
   JPasswordField t2_password_txt;
   JButton b1_sign_in_btn,b2_sign_up_btn,b3_guest_btn;
   ImageIcon icon1=new ImageIcon("E:\\back5.jpg");
    public Customer_Login_form() 
    {
        JFrame frame=new JFrame();
        frame.setTitle("LOGIN FORM");
        frame.setContentPane(new JLabel(icon1));
        l0_login_form_lbl=new JLabel("CUSTOMER LOGIN FORM");
        //l0_login_form_lbl.setForeground(Color.white);
        l1_email_id_lbl=new JLabel("EMAIL-ID    :");
        l2_password_lbl=new JLabel("PASSWORD :");
        
       t1_email_id_txt=new JTextField();
       t2_password_txt=new JPasswordField();
       
       b1_sign_in_btn=new JButton("SIGN IN");
       b2_sign_up_btn=new JButton("SIGN UP");
       b3_guest_btn=new JButton("SIGN AS GUEST");
       
       //LOGIN LABEL
       l0_login_form_lbl.setBounds(200,20,300,30);
       l0_login_form_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //EMAIL ID LABEL
       l1_email_id_lbl.setBounds(100, 100, 150, 30);
       l1_email_id_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //PASSWORD LABEL
       l2_password_lbl.setBounds(100, 160, 150, 30);
       l2_password_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //EMAIL ID TEXTFIELD
       t1_email_id_txt.setBounds(260, 100, 250, 30);
       t1_email_id_txt.setFont(new Font("verdana", Font.BOLD, 15));
       
       //PASSWORD TESTFIELD
       t2_password_txt.setBounds(260, 160, 250, 30);
       t2_password_txt.setFont(new Font("verdana", Font.BOLD, 15));
       
       //SHOW PASSWORD CHECKBOX
       JCheckBox show_password_ckb = new JCheckBox("Show password");
        
        show_password_ckb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(show_password_ckb.isSelected())
				{
					t2_password_txt.setEchoChar((char)0);
				}
				if(!show_password_ckb.isSelected())
				{
			
					t2_password_txt.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				}
			}
		});
		show_password_ckb.setFont(new Font("Tahoma", Font.BOLD, 14));
		show_password_ckb.setBounds(520, 160, 150, 30);
		frame.getContentPane().add(show_password_ckb);
       
       //SIGN IN BUTTON         
       b1_sign_in_btn.setBounds(200, 230, 150, 30);
       b1_sign_in_btn.setFont(new Font("verdana", Font.BOLD, 20));
       
       //SIGN UP BUTTON
       b2_sign_up_btn.setBounds(450, 230, 150, 30);
       b2_sign_up_btn.setFont(new Font("verdana", Font.BOLD, 20));
       
       //GUEST BUTTON
       b3_guest_btn.setBounds(400, 450, 250, 30);
       b3_guest_btn.setFont(new Font("verdana", Font.BOLD, 20));
       
       b1_sign_in_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               String s1=t1_email_id_txt.getText();
               String s2=t2_password_txt.getText();
               
               if(s1.isEmpty())
               {
                   JOptionPane.showMessageDialog(frame, "Please enter your mail id", "ALERT", JOptionPane.WARNING_MESSAGE);
               }
               else if(s2.isEmpty())
               {
                   JOptionPane.showMessageDialog(frame, "Please enter your password", "ALERT", JOptionPane.WARNING_MESSAGE);
               }
               else
               {
                   try
                   {
                       Connection con=Bean.getConnection();
                       Statement st=con.createStatement();
                       String query="select * from customer_details where email_id='"+s1+"' ";
                       ResultSet rs=st.executeQuery(query);
                       if(rs.next())
                       {
                         String s=rs.getString("password");
                         String h1=rs.getString("visited_count");
                         String h6=rs.getString("email_id");
                         if(s2.equals(s))
                         {
                             
                             frame.setVisible(false);
                             
                             
                             int h2=Integer.parseInt(h1);
                             h2=h2+1;
                             
                             String visi=Integer.toString(h2);
                             try
                             {
                                 Connection co1=Bean.getConnection();
                                 Statement sst=co1.createStatement();
                                 
                                 String query1="update customer_details set visited_count='"+visi+"' where email_id='"+h6+"' ";
                                 int r4=sst.executeUpdate(query1);
                             }
                             catch(Exception eee)
                             {
                                 System.out.println("eee: "+eee);
                             }
                             
                             
                             new Login_order_form(rs.getString("name"),visi);
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(frame, "Please enter the correct password", "ALERT", JOptionPane.WARNING_MESSAGE);
                         }
                       }
                       else
                       {
                          JOptionPane.showMessageDialog(frame, "Invalid username or password", "ALERT", JOptionPane.WARNING_MESSAGE); 
                       }
                   }
                   catch(Exception e1)
                   {
                       JOptionPane.showMessageDialog(frame, "Inside catch", "ALERT", JOptionPane.WARNING_MESSAGE);
                       System.out.println(e1.getMessage());
                   }
               }
            }
        });
       
       b2_sign_up_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Customer_register_form();
            }
        });
       
       b3_guest_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                frame.setVisible(false);
                new Guest_order_form();
            }
        });
       
        
       frame.add(l0_login_form_lbl);
       frame.add(l1_email_id_lbl);
       frame.add(l2_password_lbl);
       frame.add(t1_email_id_txt);
       frame.add(t2_password_txt);
       frame.add(b1_sign_in_btn);
       frame.add(b2_sign_up_btn);
       frame.add(b3_guest_btn);
       
        frame.setLayout(null);
        frame.setBounds(100, 100, 700, 600);
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
       new Customer_Login_form();
    }
}
