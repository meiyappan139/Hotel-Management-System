

package bootathan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class Admin_Login {
    
    JLabel l0_admin_login_lbl,l1_email_lbl,l2_password_lbl;
    JButton b1_login_btn;
    JTextField t1_email_id;
    JPasswordField p1_password_txt;
    Admin_Login()
    {
        JFrame frame=new JFrame();
        frame.setTitle("Admin_Login form");
        frame.setBounds(300,100,600,500);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        
        l0_admin_login_lbl=new JLabel("ADMIN LOGIN");
        l1_email_lbl=new JLabel("Email id");
        l2_password_lbl=new JLabel("Password");
    
        b1_login_btn=new JButton("Login");
    
        t1_email_id=new JTextField();
        p1_password_txt=new JPasswordField();
        
        //ADMIN LOGIN LABEL
        l0_admin_login_lbl.setBounds(200,30,180,30);
        l0_admin_login_lbl.setFont(new Font("Times New Roman",Font.BOLD,20));
        //EMAIL ID LABEL
        l1_email_lbl.setFont(new Font("Times New Roman",Font.BOLD,25));
        l1_email_lbl.setBounds(50,100,100,30);
        l1_email_lbl.setFont(new Font("Times New Roman",Font.BOLD,20));
        //PASSWORD LABEL
        l2_password_lbl.setBounds(50,150,100,30);
        l2_password_lbl.setFont(new Font("Times New Roman",Font.BOLD,20));
        //LOGIN BUTTON 
        b1_login_btn.setBounds(150,250,100,30);
        b1_login_btn.setFont(new Font("Times New Roman",Font.BOLD,20));
        //EMAIL ID TEXTFIELD
        t1_email_id.setBounds(180,100,170,30);
        //PASSWORD TEXTFIELD
        p1_password_txt.setBounds(180,150,170,30);
    
    JCheckBox show_password_ckb = new JCheckBox("Show password");
        
        show_password_ckb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(show_password_ckb.isSelected())
				{
					p1_password_txt.setEchoChar((char)0);
				}
				if(!show_password_ckb.isSelected())
				{
			
					p1_password_txt.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				}
			}
		});
		show_password_ckb.setFont(new Font("Tahoma", Font.BOLD, 14));
		show_password_ckb.setBounds(360, 150, 150, 30);
		frame.getContentPane().add(show_password_ckb);
    
    b1_login_btn.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {
            String email=t1_email_id.getText();
            String password=p1_password_txt.getText();
       
        if(email.equals("")){
            JOptionPane.showMessageDialog(frame,"Email id is mandatory");  
        }
        else if(password.equals("")){
            JOptionPane.showMessageDialog(frame,"Password is mandatory");
        }
        else{
            try{
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            System.out.println("Connection successful");
            //QUERY IS USED TO SELECT ALL DETAILS FROM THE ADMI DETAILS TABLE
            String query="select * from admin_details where email_id='"+email+"'";
            ResultSet rs=st.executeQuery(query);
         
            String s1="";
         
            while(rs.next()){
                 s1=rs.getString("password");
            }
           
            if(!password.equals(s1)){
                JOptionPane.showMessageDialog(frame,"Invalid User name or Password!","ALERT", JOptionPane.WARNING_MESSAGE);
                }  
            else if(password.equals(s1)){
                JOptionPane.showMessageDialog(frame,"Login successful!");
                new Admin_choice();
                frame.dispose();
               }  
         } 
        
        catch(Exception e1)
        {  
            System.out.println(e1);
        }
        }
    
        }});
    
    
         frame.add(l0_admin_login_lbl);
         frame.add(l1_email_lbl);
         frame.add(t1_email_id);
         frame.add(l2_password_lbl);
         frame.add(p1_password_txt);
         frame.add(b1_login_btn);
    
         frame.setLayout(null);
         frame.setDefaultCloseOperation(3);
         frame.setVisible(true);     
    }
    public static void main(String args[]){
        new Admin_Login();
    }
}


