

package bootathan;


import java.sql.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;
import javax.swing.*;
class Admin_registration
{
     JFrame frame;
     public Admin_registration()
     {
        frame=new JFrame();
        JLabel l_admin_portal,l1_user_name,l2_password,l3_reenter_password,l4_mobile_no,l5_email_id;
        JButton b1_register;
        JTextField t1_user_name,t4_mobile_no,t5_email_id;
        JPasswordField t2_password,t3_reenter_password;
        
        frame.setTitle("REGISTRATION FORM");
        frame.setBounds(200,100,600,550);
        frame.setBackground(new Color(186, 52, 235));
        l_admin_portal=new JLabel("Admin Portal");
        l1_user_name=new JLabel("USER NAME");
        l2_password=new JLabel("PASSWORD");
        l3_reenter_password=new JLabel("RE-ENTER PASSWORD");
        l4_mobile_no=new JLabel("MOBILE NUMBER");
        l5_email_id=new JLabel("EMAIL ID");
        
        l_admin_portal.setFont(new Font("Times new Roman",Font.BOLD,20));
        l_admin_portal.setBounds(200,50,150,30);
        l1_user_name.setFont(new Font("Times new Roman",Font.BOLD,15));
        l1_user_name.setBounds(50,100,170,30);
        l2_password.setFont(new Font("Times new Roman",Font.BOLD,15));
        l2_password.setBounds(50,150,170,30);
        l3_reenter_password.setFont(new Font("Times new Roman",Font.BOLD,15));
        l3_reenter_password.setBounds(50,200,170,30);
        l4_mobile_no.setFont(new Font("Times new Roman",Font.BOLD,15));
        l4_mobile_no.setBounds(50,250,170,30);
        l5_email_id.setFont(new Font("Times new Roman",Font.BOLD,15));
        l5_email_id.setBounds(50,300,170,30);
        
        t1_user_name=new JTextField();
        t1_user_name.setBounds(270,100,150,30);
        t2_password=new JPasswordField();
        t2_password.setBounds(270,150,150,30);
        t3_reenter_password=new JPasswordField();
        t3_reenter_password.setBounds(270,200,150,30);
        t4_mobile_no=new JTextField();
        t4_mobile_no.setBounds(270,250,150,30);
        t5_email_id=new JTextField();
        t5_email_id.setBounds(270,300,150,30);
        
        b1_register=new JButton("REGISTER");
        b1_register.setBounds(200,400,150,30);
        b1_register.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        
        JCheckBox show_password_ckb = new JCheckBox("Show password");
        
        show_password_ckb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(show_password_ckb.isSelected())
				{
					t3_reenter_password.setEchoChar((char)0);
				}
				if(!show_password_ckb.isSelected())
				{
			
					t3_reenter_password.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				}
			}
		});
		show_password_ckb.setFont(new Font("Tahoma", Font.BOLD, 14));
		show_password_ckb.setBounds(430, 200, 150, 30);
		frame.getContentPane().add(show_password_ckb);

        b1_register.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {
             String name=t1_user_name.getText();
             String password=t2_password.getText();
             String reenter=t3_reenter_password.getText();
             String mobile=t4_mobile_no.getText();
             String email=t5_email_id.getText();
        
        String regex1= "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[!@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
        Pattern p1=Pattern.compile(regex1);
        Matcher m1=p1.matcher(password);  
        
        String regex2="^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
        Pattern p2=Pattern.compile(regex2);
        Matcher m2=p2.matcher(mobile);
        
        String regex3="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        Pattern p3=Pattern.compile(regex3);
        Matcher m3=p3.matcher(email);
        if(name.equals(""))
        {
        JOptionPane.showMessageDialog(frame,"userName is mandatory!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
           
        else if(password.equals(""))
        {
        JOptionPane.showMessageDialog(frame,"Password is mandatory!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(!m1.matches()){
            JOptionPane.showMessageDialog(frame, "Invalid Password!\n Password must contain \none Uppercase Letter,\none LowercaseLetter,\none Digit,\natleast 8 to 20 charcters.\n it must not contain space.", "ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(reenter.equals(""))
        {
        JOptionPane.showMessageDialog(frame,"Please re-enter your password!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(!reenter.equals(password))
        {
        JOptionPane.showMessageDialog(frame,"Password mismatch!","ALERT", JOptionPane.WARNING_MESSAGE);
        }

        else if(mobile.equals(""))
        {
        JOptionPane.showMessageDialog(frame,"Please enter your mobile number!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(!m2.matches())
        {
        JOptionPane.showMessageDialog(frame,"Enter a valid mobile number!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(email.equals(""))
        {
        JOptionPane.showMessageDialog(frame,"Please enter your e-mail!","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else if(!m3.matches()){
                JOptionPane.showMessageDialog(frame,"please enter a valid E-mail id","ALERT", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
        try
        {
           
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            System.out.println("Successful!");
            //selecting all fields from admin_details
            String query="select * from admin_details";
            ResultSet rs=st.executeQuery(query);
            int flag=0;
            while(rs.next())
            {
            if(email.equals(rs.getString("email_id")) || mobile.equals(rs.getString("mobile_no")))
            {
            flag=1;
            JOptionPane.showMessageDialog(frame,"User already exists");
            break;
            }
            }
            if(flag==0)
            {
            //inserting all values into admin_details table
            String que="insert into admin_details(name,password,mobile_no,email_id) values('"+name+"','"+password+"','"+mobile+"','"+email+"')";
            //The query is executed and stored in resultset 
            int res=st.executeUpdate(que);
            JOptionPane.showMessageDialog(frame,"Registered Successfully");
            frame.dispose();
            new Admin_choice();
            }
        }
        catch(Exception f){
            System.out.println("Failed!");
        }
        }
    }
});
        
         frame.add(l_admin_portal);
         frame.add(l1_user_name);
         frame.add(t1_user_name);
         frame.add(l2_password);
         frame.add(t2_password);
         frame.add(l3_reenter_password);
         frame.add(t3_reenter_password);
         frame.add(l4_mobile_no);
         frame.add(t4_mobile_no);
         frame.add(l5_email_id);
         frame.add(t5_email_id);
         frame.add(b1_register);
         frame.setLayout(null);
         frame.getContentPane().setBackground(new Color(250, 250, 12));
         frame.setVisible(true);
         frame.setResizable(false);
     }
public static void main(String args[]){
         Admin_registration admin_registration = new Admin_registration(); 
}
}

