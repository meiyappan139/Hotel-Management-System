
package bootathan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_choice 
{
    JLabel l_admin_choice;
    JButton b_new_admin,b1_insert,b2_update,b3_delete,b4_order_summary,b5_profit_table,b6_feedback,b7_exit;
    
    Admin_choice()
    {
         JFrame frame=new JFrame();
        
        frame.setTitle("Food Print");
        frame.setBounds(200,50,800,500);
        frame.getContentPane().setBackground(new Color(44, 163, 139));
        
        l_admin_choice=new JLabel("ADMIN CHOICE");
        b_new_admin=new JButton("New Admin Signup");
        b1_insert=new JButton("Insert Item");
        b2_update=new JButton("Update Item");
        b3_delete=new JButton("Delete Item");
        b4_order_summary=new JButton("View Order Summary");
        b5_profit_table=new JButton("View Profit table");
        b6_feedback=new JButton("View Customer feedback");
        b7_exit=new JButton("Exit");
        
        l_admin_choice.setBounds(300,50,200,20);
        l_admin_choice.setFont(new Font("Times new Roman",Font.BOLD,25));
        b_new_admin.setBounds(50,150,200,25);
        b_new_admin.setFont(new Font("Times new Roman",Font.BOLD,15));
        b_new_admin.setBackground(Color.GREEN);
        b1_insert.setBounds(280,150,200,25);
        b1_insert.setFont(new Font("Times new Roman",Font.BOLD,15));
        b1_insert.setBackground(Color.GREEN);
        b2_update.setBounds(510,150,200,25);
        b2_update.setFont(new Font("Times new Roman",Font.BOLD,15));
        b2_update.setBackground(Color.GREEN);
        b3_delete.setBounds(50,250,200,25);
        b3_delete.setFont(new Font("Times new Roman",Font.BOLD,15));
        b3_delete.setBackground(Color.GREEN);
        b4_order_summary.setBounds(280,250,200,25);
        b4_order_summary.setFont(new Font("Times new Roman",Font.BOLD,15));
        b4_order_summary.setBackground(Color.GREEN);
        b5_profit_table.setBounds(510,250,200,25);
        b5_profit_table.setFont(new Font("Times new Roman",Font.BOLD,15));
        b5_profit_table.setBackground(Color.GREEN);
        b6_feedback.setBounds(50,350,200,25);
        b6_feedback.setFont(new Font("Times new Roman",Font.BOLD,15));
        b6_feedback.setBackground(Color.GREEN);
        b7_exit.setBounds(280,350,200,25);
        b7_exit.setFont(new Font("Times new Roman",Font.BOLD,15));
        b7_exit.setBackground(Color.GREEN);
        
        //admin registration portal is opened when b_new_admin is clicked
         b_new_admin.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new Admin_registration();
            frame.dispose();
        }
    });
         //Item inserting frame is opened when b1_insert is clicked
        b1_insert.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Insert_food_items();
            frame.dispose();
        }
    });
        //Item updating frame is opened when b2_update is clicked
         b2_update.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Update_food_items();
            frame.dispose();
        }
    });
         //Item deleting frame is opened when b3_delete is clicked
         b3_delete.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Delete_food_items();
            frame.dispose();
        }
    });
         //order summary is viewed through clicking this button
        b4_order_summary.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Admin_order_summary();
            frame.dispose();
        }
    });
        //profit table is viewed through clicking this button
        b5_profit_table.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Admin_profit_table();
            frame.dispose();
        }
    });
         //feed back is viewed through clicking this button
        b6_feedback.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Admin_view_feedback_form();
            frame.dispose();
        }
    });
        //particular frame is disposed when clicking exit
        b7_exit.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            //System.exit(3);
            frame.setVisible(false);
            new Front_Form();
        }
    });
        frame.add(l_admin_choice);
        frame.add(b_new_admin);
        frame.add(b1_insert);
        frame.add(b2_update);
        frame.add(b3_delete);
        frame.add(b4_order_summary);
        frame.add(b5_profit_table);
        frame.add(b6_feedback);
        frame.add(b7_exit);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String args[]){
        new Admin_choice();
    }
}

