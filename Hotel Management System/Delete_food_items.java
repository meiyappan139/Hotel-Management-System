package bootathan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class Delete_food_items 
{
    JComboBox t1_itemname;
    Delete_food_items()
    {
        JFrame j_deleteframe=new JFrame();
        j_deleteframe.setTitle("food delete");
        j_deleteframe.setBounds(200,30,450,400);
        j_deleteframe.getContentPane().setBackground(new Color(250, 250, 12));
        
        JLabel l_heading=new JLabel("Food Delete");  
        l_heading.setBounds(150,50,150,30);
        l_heading.setFont(new Font("Times New Roman",Font.BOLD,25));
        
        JLabel l1_itemname=new JLabel("Enter Item Name");  
        l1_itemname.setBounds(50,130,200,30);
        l1_itemname.setFont(new Font("Times New Roman",Font.BOLD,25));
        t1_itemname=new JComboBox();
        t1_itemname.setBounds(260,130,150,30);
        t1_itemname.addItem("SELECT");
        
        JButton b1_delete=new JButton("Delete");
        b1_delete.setBounds(90,220,100,30);
        b1_delete.setFont(new Font("Times New Roman",Font.BOLD,25));
        //b1_delete.setBackground(Color.pink);
        JButton b2_back=new JButton("Back");
        b2_back.setBounds(230,220,100,30);
        b2_back.setFont(new Font("Times New Roman",Font.BOLD,25));
        //b2_back.setBackground(Color.pink);
        JButton b3_deletemore=new JButton("Delete more item");
        b3_deletemore.setBounds(100,280,220,30);
        b3_deletemore.setFont(new Font("Times New Roman",Font.BOLD,25));
        //b3_deletemore.setBackground(Color.pink);
        
        try
            {
              Connection con=Bean.getConnection();
              Statement st=con.createStatement();
              ResultSet r=st.executeQuery("select item_name from food");
              while(r.next())
              {
                  t1_itemname.addItem(r.getString("item_name"));
              }
            }
            catch(Exception ee)
            {
            }
        
        //this method is implemented when we click on the delete button
        b1_delete.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            //String s1 stores the value of itemname enetered in the textfield
            String s1=(String)t1_itemname.getSelectedItem();
            //thi flag variable is used in the process of checking whether an item is available or not
            int flag=0;
            
            if(s1.equals("SELECT"))
            {
                JOptionPane.showMessageDialog(j_deleteframe,"select item to be deleted");
            }
            else
            {
            try
            {
             //  we are establishing connection between java application and database   
            Connection con=Bean.getConnection();
            //creates a statement object that can be used to execute SQL query
            Statement st=con.createStatement();
            System.out.println("Connection successful");
             //below given is the select query which shows all the data in the food table
             String query="select * from food";
             //the given select query is exceuted and stored in the ResultSet obeject
             ResultSet res=st.executeQuery(query);
              
                while(res.next())
                {
                    if(s1.equals(res.getString("item_name")))
                    {
                        //below given is the delete query which deletes the specific data in the food table using where condition
                          String query1="delete from food where item_name='"+s1+"'";
                          //the given delete query is updated and stored in rs
                          int rs=st.executeUpdate(query1);
                          t1_itemname.removeItem(s1);
                          JOptionPane.showMessageDialog(j_deleteframe,"Deleted successfully");
                          t1_itemname.setSelectedIndex(0);
                           flag=1;
                    }
                }
                //flag value equal to 0 indicates that the item does not exist in the menu
                if(flag==0)
                {
                    JOptionPane.showMessageDialog(j_deleteframe,"Item unavailable","ALERT", JOptionPane.WARNING_MESSAGE);
                }
             }
             catch(Exception e1)
             {
             }
           }
        }
    });
        //this method is implemented when we click on the back button
         b2_back.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            //the current frame gets closed when we use dispose() method
            j_deleteframe.dispose();
            //shows the Admin_choice frame
            new Admin_choice();
        }
    });
         //this method is implemented when we click on the delete more items button
         b3_deletemore.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            //the textfield corresponding to the itemname has been set empty
            t1_itemname.setSelectedIndex(0);
        }
    });
        
        j_deleteframe.add(l_heading);
        j_deleteframe.add(l1_itemname);
        j_deleteframe.add(t1_itemname);
        j_deleteframe.add(b1_delete);
        j_deleteframe.add(b2_back);
        j_deleteframe.add(b3_deletemore);
        j_deleteframe.setLayout(null);
        j_deleteframe.setVisible(true);
    }
    public static void main(String[]args){
        new Delete_food_items();
    }
}
    

