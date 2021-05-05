
package bootathan;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;
import java.sql.*;

public class Customer_register_form 
{
    JLabel l0_login_lb1,l1_name_lbl,l2_email_id_lbl,l3_password_lbl,l4_confirm_password_lbl,l5_phone_lbl;
    JTextField t1_name_txt,t2_email_id_txt,t5_phone_txt;
    JPasswordField t3_password_txt,t4_confirm_password_txt;
    JButton b0_submit_btn,b1_login_btn;
   
    //ImageIcon icon1=new ImageIcon("E:\\back6.png");
    public Customer_register_form() 
    {
        JFrame frame=new JFrame();
        frame.setTitle("CUSTOMER REGISTRATION FORM");
        //frame.setContentPane(new JLabel(icon1));
        frame.getContentPane().setBackground(Color.yellow);
        
        l0_login_lb1=new JLabel("CUSTOMER REGISTERATION FORM");
        //l0_login_lb1.setForeground(Color.red);
        l0_login_lb1.setBounds(190,20,400,30);
        l0_login_lb1.setFont(new Font("verdana", Font.BOLD, 20));
       
       //NAME LABEL 
       l1_name_lbl=new JLabel("NAME                           :");
       l1_name_lbl.setBounds(70, 100, 300, 30);
       //l1_name_lbl.setForeground(Color.red);
       //l1_name_lbl.setBackground(Color.red);
       l1_name_lbl.setFont(new Font("verdana", Font.BOLD, 20));
        
       //NAME TEXTFIELD
       t1_name_txt=new JTextField();
       t1_name_txt.setBounds(370, 100, 200, 30);
       t1_name_txt.setFont(new Font("verdana", Font.BOLD, 20));
       
       //EMAIL ID LABEL
       l2_email_id_lbl=new JLabel("EMAIL-ID                     :");
       l2_email_id_lbl.setBounds(70, 150, 300, 30);
       //l2_email_id_lbl.setForeground(Color.red);
       l2_email_id_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //EMAIL ID TEXTFIELD
       t2_email_id_txt=new JTextField();
       t2_email_id_txt.setBounds(370, 150, 200, 30);
       t2_email_id_txt.setFont(new Font("verdana", Font.BOLD, 20));
        
       //PASSWORD LABEL
       l3_password_lbl=new JLabel("PASSWORD                  :");
       l3_password_lbl.setBounds(70, 200, 300, 30);
       //l3_password_lbl.setForeground(Color.red);
       l3_password_lbl.setFont(new Font("verdana", Font.BOLD, 20));
        
       //PASSWORD TEXTFIELD
       t3_password_txt=new JPasswordField();
       t3_password_txt.setBounds(370, 200, 200, 30);
       t3_password_txt.setFont(new Font("verdana", Font.BOLD, 20));
        
       //CONFIRM PASSWORD LABEL
       l4_confirm_password_lbl=new JLabel("CONFIRM PASSWORD :");
       l4_confirm_password_lbl.setBounds(70,250,300,30);
       //l4_confirm_password_lbl.setForeground(Color.red);
       l4_confirm_password_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //CONFIRM PASSWORD TEXTFIELD
       t4_confirm_password_txt=new JPasswordField();
       t4_confirm_password_txt.setBounds(370, 250, 200, 30);
       t4_confirm_password_txt.setFont(new Font("verdana", Font.BOLD, 20));
       
       //PHONE NO LABEL
       l5_phone_lbl=new JLabel("PHONE NO                   :");
       //l5_phone_lbl.setForeground(Color.red);
       l5_phone_lbl.setBounds(70, 300, 300, 30);
       l5_phone_lbl.setFont(new Font("verdana", Font.BOLD, 20));
       
       //PHONE NO TEXTFIELD
       t5_phone_txt=new JTextField();
       t5_phone_txt.setBounds(370, 300, 200, 30);
       t5_phone_txt.setFont(new Font("verdana", Font.BOLD, 20));
        
       //SUBMIT BUTTON 
       b0_submit_btn=new JButton("SUBMIT");
       b0_submit_btn.setBounds(300, 380, 150, 30);
       b0_submit_btn.setFont(new Font("verdana", Font.BOLD, 20));
        
       //LOGIN BUTTON 
       b1_login_btn=new JButton("SIGN IN");
       b1_login_btn.setBounds(300, 480, 150, 30);
       b1_login_btn.setFont(new Font("verdana", Font.BOLD, 20));
        
       b1_login_btn.addActionListener((ActionEvent e) -> {
            frame.setVisible(false);
            Customer_Login_form customer_Login_form = new Customer_Login_form();
        });
        
        //SHOW PASSWORD CHECKBOX
        JCheckBox show_password_ckb = new JCheckBox("Show password");
        
        show_password_ckb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(show_password_ckb.isSelected())
				{
					t4_confirm_password_txt.setEchoChar((char)0);
				}
				if(!show_password_ckb.isSelected())
				{
			
					t4_confirm_password_txt.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				}
			}
		});
		show_password_ckb.setFont(new Font("Tahoma", Font.BOLD, 14));
		show_password_ckb.setBounds(580, 250, 150, 30);
		frame.getContentPane().add(show_password_ckb);
        
        
        b0_submit_btn.addActionListener((ActionEvent e) -> {
            
            // S1 IS USED TO STORE NAME TEXTFIELD 
            String s1=t1_name_txt.getText();            
            //S2 IS USED TO STORE EMAIL ID
            String s2=t2_email_id_txt.getText();
            //S3 IS USED TO STORE PASSWORD
            String s3=t3_password_txt.getText();
            //S4 IS USED TO STORE CONFIRM PASSWORD
            String s4=t4_confirm_password_txt.getText();
            //S5 IS USED TO STORE PHONE NUMBER
            String s5=t5_phone_txt.getText();
            
            if(s1.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "please enter your name", "ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else if(s2.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "please enter your email id", "ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else if(s3.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "please enter your password", "ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else if(s4.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "please enter your confirm password", "ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else if(s5.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "please enter your phone number", "ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                int flag1=0,flag2=0,flag3=0,flag5=0,f=0;
                
                //REGEX FOR NAME
                String regex1="^[A-Z a-z]*$";
                Pattern p1=Pattern.compile(regex1);
                Matcher m1=p1.matcher(s1);                
                if(m1.matches())
                {
                    //IF FLAG1=1 MEANS NAME IS CORRECT
                    flag1=1;
                }
                
                //REGEX FOR EMAIL ID
                String regex2="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
                Pattern p2=Pattern.compile(regex2);
                Matcher m2=p2.matcher(s2);
                if(m2.matches())
                {
                    //IF FLAG2=1 THEN EMAIL ID IS CORRECT
                    flag2=1;
                }
                
                //REGEX FOR PASSWORD
                String regex3= "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[!@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern p3=Pattern.compile(regex3);
                Matcher m3=p3.matcher(s3);
                if(m3.matches())
                {
                    ///IF FLAG3=1 THEN PASSWORD IS CORRECT
                    flag3=1;
                }
                
                //REGEX FOR PHONE NUMBER
                String regex5="^[6-9]{1}[0-9]{0,9}$";
                Pattern p5=Pattern.compile(regex5);
                Matcher m5=p5.matcher(s5);
                if(m5.matches())
                {
                    //IF FLAG5=1 THEN PHONE NUMBER IS CORRECT
                    flag5=1;
                }
                
                System.out.println(flag1+"  "+flag2+"  "+flag3+"   "+flag5);
                
                try
                {
                    Connection conn=Bean.getConnection();
                    Statement st1=conn.createStatement();
                    
                    //QUERY2 IS USED TO SELECT EMAIL ID FROM CUSTOMER DETAILS TABLE
                    String query2="select email_id from customer_details";
                    ResultSet rs=st1.executeQuery(query2);
                    while(rs.next())
                    {
                        if(s2.equals(rs.getString("email_id")))
                        {
                            f=1;
                            break;
                        }
                    }
                    conn.close();
                }
                catch(Exception ee)
                {
                    
                }
                System.out.println(f);
                if(flag1==0)
                {
                    JOptionPane.showMessageDialog(frame, "Invalid name!\n Name should contain only alphabets", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
                else if(flag2==0)
                {
                    JOptionPane.showMessageDialog(frame, "Invalid Email Id!", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
                else if(flag3==0)
                {
                    JOptionPane.showMessageDialog(frame, "Invalid Password!\n Password must contain \none Uppercase Letter,\none LowercaseLetter,\none Digit,\natleast 8 to 20 charcters.\n it must not contain space.", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
                else if(flag5==0 || s5.length()!=10)
                {
                    JOptionPane.showMessageDialog(frame, "Invalid Mobile Number!", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
                else if(f==1)
                {
                    JOptionPane.showMessageDialog(frame, "mail id already exist;\n please enter other mail id", "ALERT", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if(s3.equals(s4))
                    {
                        try 
                        {
                            //    INSERT INTO THE CUSTOMER TABLE
                            Connection con=Bean.getConnection();
                            Statement st=con.createStatement();
                            
                            //QUERY1 IS USED TO INSERT THE CUSTOMER DETAILS IN CUSTOMER_DETAILS TABLE
                            String query1="insert into customer_details(name,mobile_no,email_id,password) values('"+s1+"','"+s5+"','"+s2+"','"+s3+"')";
                            int r=st.executeUpdate(query1);
                            //TO SHOW THE SUCCESSFULLY REGISTERED MESSAGE
                            JOptionPane.showMessageDialog(frame, "Sucessfully Registered");
                            con.close();
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Password Mismatch!", "ALERT", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
            }
        });
        
        
        
        frame.add(l0_login_lb1);
        frame.add(l1_name_lbl);
        frame.add(l2_email_id_lbl);
        frame.add(l3_password_lbl);
        frame.add(l4_confirm_password_lbl);
        frame.add(l5_phone_lbl);
        
        frame.add(t1_name_txt);
        frame.add(t2_email_id_txt);
        frame.add(t3_password_txt);
        frame.add(t4_confirm_password_txt);
        frame.add(t5_phone_txt);
        
        frame.add(b0_submit_btn);
        frame.add(b1_login_btn);
        
        frame.setBounds(100, 100, 800, 700);
       
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Customer_register_form();
    }

    
}
