
package bootathan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class Feedback_form
{
    JFrame frame;
    JLabel l0_feed_lbl,l1_Feedback,l2_rating;
    JTextField t1_Feedback;
    JComboBox combo;
    JButton button;
    public Feedback_form(String name) 
    {
        frame=new JFrame();
        frame.setTitle("FEEDBACK FORM");
        l0_feed_lbl=new JLabel("FEEDBACK FORM");
        l1_Feedback=new JLabel("FEEDBACK:");
        
        
        //FEEDBACK HEADING LABEL
        l0_feed_lbl.setBounds(200,30,250,30);
        l0_feed_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //REEDBACK LABEL
        l1_Feedback.setBounds(40, 100, 150, 30);
        l1_Feedback.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //FEEDBACK TEXTFIELD
        t1_Feedback=new JTextField();
        t1_Feedback.setBounds(200, 100, 200, 30);
        t1_Feedback.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //RATING COMBOBOX
        l2_rating=new JLabel("RATINGS:");
        l2_rating.setBounds(40, 150, 150, 30);
        l2_rating.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //COMBOBOX FOR RATING
        combo=new JComboBox();
        String arr[]={"1","2","3","4","5"};
        combo.addItem("SELECT");
        for(int i=0;i<arr.length;i++)
        {
            combo.addItem(arr[i]);
        }
        
        combo.setBounds(200,150,200,30);
        combo.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //SUBMIT BUTTON
        button=new JButton("SUBMIT");        
        button.setBounds(150, 200, 150, 30);
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //S IS USED TO STORE THE FEEDBACK
                String s=t1_Feedback.getText();
                //S1 IS USED TO STORE THE RATING FROM COMBOBOX
                String s1=(String)combo.getSelectedItem();
                if(s.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "please enter the feedback","ALERT",JOptionPane.WARNING_MESSAGE);
                }
                else if(s1.equals("SELECT"))
                {
                    JOptionPane.showMessageDialog(frame, "please select the rating","ALERT",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                try
                {
                    Connection con=Bean.getConnection();
                    Statement st=con.createStatement();
                    //QUERY IS USED TO INSERT FEEDBACK AND RATING INTO FEEDBACK_TABLE
                    String query="insert into feedback_table(feedback,rating) values('"+s+"',"+s1+"  )";
                    int r=st.executeUpdate(query);
                    JOptionPane.showMessageDialog(frame,"THANK YOU VISIT AGAIN.");
                    frame.setVisible(false);
                    new Front_Form();
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
                }
            }
        });
        
        
        
        frame.add(l0_feed_lbl);
        frame.add(l1_Feedback);
        frame.add(l2_rating);
        
        frame.add(t1_Feedback);
        frame.add(combo);
        
        frame.add(button);
        
        frame.setLayout(null);
        frame.setBounds(100, 100, 600, 400);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        frame.setVisible(true);
        
    }
    public static void main(String[] args)
    {
        new Feedback_form("mani");
    }
}
