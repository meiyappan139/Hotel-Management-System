package bootathan;

import bootathan.Admin_choice;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class Insert_food_items {
    //this flag variable is used in the process of checking an existing itemin the menu
    int flag=0; 
    Insert_food_items()
    {
        JFrame j1_insertframe=new JFrame();
        j1_insertframe.setBounds(300,50,500,600);
        j1_insertframe.setTitle("INSET FOOD ITEMS");
        j1_insertframe.getContentPane().setBackground(new Color(250, 250, 12));
            
        JLabel l_heading,l0_itemid,l1_itemname,l2_prepcost,l3_sellcost,l4_quantity;
        JTextField t_itemid,t1_itemname,t2_prepcost,t3_sellcost,t4_quantity;
        JButton b1_insertmore,b2_back,b3_insert;
            
        l_heading=new JLabel("INSERT ITEMS");
        l0_itemid=new JLabel("Item id");
        l1_itemname=new JLabel("Item name");
        l2_prepcost=new JLabel("Preparing cost");
        l3_sellcost=new JLabel("Selling cost");
        l4_quantity=new JLabel("Available quantity");
        t_itemid=new JTextField();
        t1_itemname=new JTextField();
        t2_prepcost=new JTextField();
        t3_sellcost=new JTextField();
        t4_quantity=new JTextField();
           
        l_heading.setBounds(150,20,300,30);
        l_heading.setFont(new Font("Times new Roman",Font.BOLD,30));
        l0_itemid.setBounds(100,70,180,30);
        l0_itemid.setFont(new Font("Times new Roman",Font.BOLD,20));
        l1_itemname.setBounds(100,140,180,30);
        l1_itemname.setFont(new Font("Times new Roman",Font.BOLD,20));
        l2_prepcost.setBounds(100,210,180,30);
        l2_prepcost.setFont(new Font("Times new Roman",Font.BOLD,20));
        l3_sellcost.setBounds(100,280,180,30);
        l3_sellcost.setFont(new Font("Times new Roman",Font.BOLD,20));
        l4_quantity.setBounds(100,350,180,30);
        l4_quantity.setFont(new Font("Times new Roman",Font.BOLD,20));
        
        t_itemid.setBounds(320,70,120,30);
        t1_itemname.setBounds(320,140,120,30);
        t2_prepcost.setBounds(320,210,120,30);
        t3_sellcost.setBounds(320,280,120,30);
        t4_quantity.setBounds(320,350,120,30);
        
        b3_insert=new JButton("Insert");
        b3_insert.setBounds(100,450,130,30);
        b3_insert.setFont(new Font("Times New Roman",Font.BOLD,25));
        b2_back=new JButton("Back");
        b2_back.setBounds(280,450,130,30);
        b2_back.setFont(new Font("Times New Roman",Font.BOLD,25));
        b1_insertmore=new JButton("Insert more item");
        b1_insertmore.setBounds(140,500,250,30);
        b1_insertmore.setFont(new Font("Times New Roman",Font.BOLD,25));
        
     
     // this method is implemented when we click the insert button      
     b3_insert.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            // String s stores the value of itemid entered in the textfield
            String s=t_itemid.getText();  
            // String s1 stores the value of itemname entered in the textfield
             String s1=t1_itemname.getText();
            // String s2 stores the value of prepcost entered in the textfield
             String s2=t2_prepcost.getText();  
            // String s3 stores the value of sellcost entered in the textfield
            String s3=t3_sellcost.getText(); 
            // String s4 stores the value of quantity entered in the textfield
            String s4=t4_quantity.getText(); 
           
           if(s.equals(""))
           {
               JOptionPane.showMessageDialog(j1_insertframe,"Item id is mandatory","ALERT", JOptionPane.WARNING_MESSAGE);
           }
           else if(s1.equals(""))
           {
               JOptionPane.showMessageDialog(j1_insertframe,"Item name is mandatory","ALERT", JOptionPane.WARNING_MESSAGE);
           }
           else if(s2.equals(""))
           {
               JOptionPane.showMessageDialog(j1_insertframe,"peparing cost is mandatory","ALERT", JOptionPane.WARNING_MESSAGE);
           }
           else if(s3.equals(""))
           {
               JOptionPane.showMessageDialog(j1_insertframe,"selling cost is mandatory","ALERT", JOptionPane.WARNING_MESSAGE);
           }
           else if(s4.equals(""))
           {
               JOptionPane.showMessageDialog(j1_insertframe,"quantity is mandatory","ALERT", JOptionPane.WARNING_MESSAGE);
           }
           else
           {
                
                System.out.println("s: "+s);
                System.out.println("s1: "+s1);
                System.out.println("s2: "+s2);
                System.out.println("s3: "+s3);
                System.out.println("s4:"+s4);
                
                
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
                        if(s1.equals(res.getString("item_name")) || s.equals(res.getString("id")))
                        {
                            //flag value becomes 1 when an item already exists in the menu
                            flag=1; 
                            break;
                        }
                    }
                    
                    if(flag==0) 
                    {
                        //below given is the insert query in which the data is being inserted into the food table
                        String query1="insert into food(id,item_name,original_cost,cost,avail_quantity) values ('"+s+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"')"; 
                        // the insert query is updated and stored in the object rs1
                        int rs1=st.executeUpdate(query1); 
                        JOptionPane.showMessageDialog(j1_insertframe,"inserted successfully");
                    }
                    else if(flag==1)
                    {
                       flag=0; 
                       JOptionPane.showMessageDialog(j1_insertframe,"Item already available","ALERT", JOptionPane.WARNING_MESSAGE);
                    }
               
                 }
                 catch(Exception e1)
                 {
                     System.out.println(e1);
                 }
            }
        }
     });
     // this method is implemented when we click the back button
      b2_back.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {   
            //the current frame gets closed when we use dispose() method
            j1_insertframe.dispose(); 
            //shows the Admin_choice frame
            new Admin_choice(); 
        }
      });
      
      //this method is implemented when we click on insert more items button
       b1_insertmore.addActionListener(new ActionListener(){ 
        @Override 
        public void actionPerformed(ActionEvent e)
        {  
           //the textfield corresponding to the itemname has been set empty
          t1_itemname.setText(""); 
          //the textfield corresponding to the prepcost has been set empty
          t2_prepcost.setText(""); 
          //the textfield corresponding to the sellcost has been set empty
          t3_sellcost.setText("");  
          //the textfield corresponding to the quantity has been set empty
          t4_quantity.setText("");  
          //the textfield corresponding to the quantity has been set empty
          t_itemid.setText("");  
        }});
          
            j1_insertframe.add(l_heading);
            j1_insertframe.add(l0_itemid);
            j1_insertframe.add(t_itemid);
            j1_insertframe.add(l1_itemname);
            j1_insertframe.add(t1_itemname);
            j1_insertframe.add(l2_prepcost);
            j1_insertframe.add(t2_prepcost);
            j1_insertframe.add(l3_sellcost);
            j1_insertframe.add(t3_sellcost);
            j1_insertframe.add(l4_quantity);
            j1_insertframe.add(t4_quantity);
            j1_insertframe.add(b1_insertmore);
            j1_insertframe.add(b2_back);
            j1_insertframe.add(b3_insert);
           
            j1_insertframe.setLayout(null);
            j1_insertframe.setDefaultCloseOperation(3);
            j1_insertframe.setVisible(true);  
}
    public static void main(String args[]){
        new Insert_food_items();
    }
}