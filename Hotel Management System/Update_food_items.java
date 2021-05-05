
package bootathan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class Update_food_items {

    Update_food_items()
    {
    JFrame frame=new JFrame();
    frame.setTitle("UPDATE ITEM");
    frame.setBounds(300,50,500,650);
    frame.getContentPane().setBackground(new Color(250, 250, 12));
            
    JLabel l_update_items,l1_item_name,l2_original_cost,l3_cost,l4_quantity;
    JTextField t2_original_cost,t3_cost,t4_quantity;
    JComboBox t1_item_name;
    JButton b1_update_more_item,b2_back,b3_update;
            
           l_update_items=new JLabel("Update items");
          
           l1_item_name=new JLabel("Item name");
           l2_original_cost=new JLabel("Original cost");
           l3_cost=new JLabel("Cost");
           l4_quantity=new JLabel("Available quantity");
         
           t1_item_name=new JComboBox();
           t2_original_cost=new JTextField();
           t3_cost=new JTextField();
           t4_quantity=new JTextField();
           
        l_update_items.setBounds(180,60,300,30);
        l_update_items.setFont(new Font("Times new Roman",Font.BOLD,30));
        
        l1_item_name.setBounds(100,160,180,30);
        l1_item_name.setFont(new Font("Times new Roman",Font.BOLD,20));
        l2_original_cost.setBounds(100,240,180,30);
        l2_original_cost.setFont(new Font("Times new Roman",Font.BOLD,20));
        l3_cost.setBounds(100,320,180,30);
        l3_cost.setFont(new Font("Times new Roman",Font.BOLD,20));
        l4_quantity.setBounds(100,400,180,30);
        l4_quantity.setFont(new Font("Times new Roman",Font.BOLD,20));
        
        t1_item_name.setBounds(320,160,120,30);
        t2_original_cost.setBounds(320,240,120,30);
        t3_cost.setBounds(320,320,120,30);
        t4_quantity.setBounds(320,400,120,30);
        
            b3_update=new JButton("Update");
            b3_update.setBounds(120,460,120,30);
            b3_update.setFont(new Font("Times New Roman",Font.BOLD,25));
            b2_back=new JButton("Back");
            b2_back.setBounds(275,460,100,30);
            b2_back.setFont(new Font("Times New Roman",Font.BOLD,25));
            b1_update_more_item=new JButton("Update more item");
            b1_update_more_item.setBounds(140,530,240,30);
            b1_update_more_item.setFont(new Font("Times New Roman",Font.BOLD,25));
            
            
            try
            {
              Connection con=Bean.getConnection();
              Statement st=con.createStatement();
              ResultSet r=st.executeQuery("select item_name from food");
              while(r.next())
              {
                  t1_item_name.addItem(r.getString("item_name"));
              }
            }
            catch(Exception ee)
            {
            }
            
             //when back button is clicked
             b2_back.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            frame.dispose();
            new Admin_choice();
        }
    });
             //when update more item button is clicked
             b1_update_more_item.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            t1_item_name.setSelectedIndex(0);            
            t2_original_cost.setText("");
            t3_cost.setText("");
            t4_quantity.setText("");
        }
    });
             //when update is clicked
            b3_update.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
          //getting item name
           String s1=(String)t1_item_name.getSelectedItem();
           //getting original cost
           String s2=t2_original_cost.getText();
           //getting selling cost
           String s3=t3_cost.getText();
           //getting available quantity
           String s4=t4_quantity.getText();
             int flag=0;
           
            
            if(s1.equals(""))
            {
               JOptionPane.showMessageDialog(frame,"Please enter the Item name","ALERT", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
            try
            {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            System.out.println("Connection successful");
            //selecting all items from food table 
            String query="select * from food";
            ResultSet res=st.executeQuery(query);
              
            while(res.next())
            {
                if(s1.equals(res.getString("item_name")))
                { 
                    flag=1;
                }
            }
            if(flag==1)
            {
                if(!s4.equals("") && !s3.equals("") && !s2.equals(""))
                {
                    try
                    {
                        Connection conn=Bean.getConnection();
                        Statement stt=conn.createStatement();
                        System.out.println("Inside");
                        String quer="select avail_quantity from food where item_name='"+s1+"' ";
                        ResultSet rs1=stt.executeQuery(quer);
                        System.out.println("Result set");
                        String qua="";
                        if(rs1.next())
                        {
                            qua=rs1.getString("avail_quantity");
                        }
                        int i_qua=Integer.parseInt(qua);
                        System.out.println("i_qua "+i_qua);
                        int i_qua1=Integer.parseInt(s4);
                        System.out.println("i_qua1"+i_qua1);
                        int t=i_qua+i_qua1;
                        System.out.println("t is: "+t);
                        String up=Integer.toString(t);
                        //updating cost and quantity fields in food table
                    String query3="update food set original_cost='"+s2+"',cost='"+s3+"',avail_quantity='"+up+"' where item_name='"+s1+"'";
                    //The query is executed and stored in resultset 
                    int rs3=st.executeUpdate(query3);
                    JOptionPane.showMessageDialog(frame,"Updated successfully");
                    }
                    catch(Exception ee)
                    {
                        
                    }
                }
                else if(!s2.equals("") && !s3.equals(""))
                {
                    //updating cost alone in food table
                    String query2="update food set original_cost='"+s2+"',cost='"+s3+"' where item_name='"+s1+"'";
                    //The query is executed and stored in resultset 
                    int rs2=st.executeUpdate(query2);
                    JOptionPane.showMessageDialog(frame,"Updated successfully");
                }
                else if(!s4.equals(""))
                {
                    
                    try
                    {
                        Connection conn=Bean.getConnection();
                        Statement stt=conn.createStatement();
                        System.out.println("Inside");
                        String quer="select avail_quantity from food where item_name='"+s1+"' ";
                        ResultSet rs1=stt.executeQuery(quer);
                        System.out.println("Result set");
                        String qua="";
                        if(rs1.next())
                        {
                            qua=rs1.getString("avail_quantity");
                        }
                        int i_qua=Integer.parseInt(qua);
                        System.out.println("i_qua "+i_qua);
                        int i_qua1=Integer.parseInt(s4);
                        System.out.println("i_qua1"+i_qua1);
                        int t=i_qua+i_qua1;
                        System.out.println("t is: "+t);
                        String up=Integer.toString(t);
                        //updating quantity alone in food table
                    String query1="update food set avail_quantity='"+up+"' where item_name='"+s1+"'";
                    //The query is executed and stored in resultset 
                    int rs=st.executeUpdate(query1);
                    JOptionPane.showMessageDialog(frame,"Updated successfully");
                    }
                    catch(Exception ee)
                    {
                        
                    }
                    
                          
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Insufficient data");
                }
            }

            }
            catch(Exception e1)
            {
               e1.printStackTrace();
            }
           }
            }
        });
            frame.add(l_update_items);
            frame.add(l1_item_name);
            frame.add(t1_item_name);
            frame.add(l2_original_cost);
            frame.add(t2_original_cost);
            frame.add(l3_cost);
            frame.add(t3_cost);
            frame.add(l4_quantity);
            frame.add(t4_quantity);
            frame.add(b1_update_more_item);
            frame.add(b2_back);
            frame.add(b3_update);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(3);
            frame.setVisible(true);  
}
    public static void main(String args[]){
        new Update_food_items();
    }
}