

package bootathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class First_Page extends JFrame implements ActionListener
{
   ImageIcon icon1=new ImageIcon("E:\\back3.jpg");
   JButton b1_next;
    public First_Page() 
    {
        setTitle("FIRST PAGE");
        setContentPane(new JLabel(icon1));
        
        
        //NEXT BUTTON
        b1_next=new JButton("NEXT");
        b1_next.setFont(new Font("verdana",Font.BOLD,20));
        b1_next.setBounds(450, 500, 100, 50);
        
        
        setBounds(100, 100, icon1.getIconWidth(), icon1.getIconHeight());
        b1_next.addActionListener(this);
        add(b1_next);
        setLayout(null);
        setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1_next)
        {
            setVisible(false);
            new Front_Form();
        }
    }
    
    public static void main(String[] args)
    {
        new First_Page();
    }

    
}
