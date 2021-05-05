

package bootathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Front_Form extends JFrame implements ActionListener
{
    ImageIcon icon=new ImageIcon("E:\\back2.jpg");
    JButton b1_admin,b2_user;
    public Front_Form() 
    {
        setTitle("FRONT FORM");
        setContentPane(new JLabel(icon));
        b1_admin=new JButton("ADMIN");
        b2_user=new JButton("USER");
        
        //ADMIN BUTTON
        b1_admin.setFont(new Font("verdana",Font.BOLD,20));
        
        //USER BUTTON
        b2_user.setFont(new Font("verdana",Font.BOLD,20));
        
        
        b1_admin.setBounds(350, 200, 250, 50);
        b2_user.setBounds(350, 300, 250, 50);
        
        add(b1_admin);
        add(b2_user);
        
        b1_admin.addActionListener(this);
        b2_user.addActionListener(this);
        
        setLayout(null);
        setBounds(100, 100, icon.getIconWidth(),icon.getIconHeight());
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Front_Form();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1_admin)
        {
            setVisible(false);
            new Admin_Login();
        }
        else if(e.getSource()==b2_user)
        {
            setVisible(false);
            new Customer_Login_form();
        }
    }
}
