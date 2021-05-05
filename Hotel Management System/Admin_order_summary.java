

package bootathan;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Admin_order_summary 
{
    JFrame frame;
    JPanel panel;
    JTable table;
    JButton home_btn;
    JLabel l0_order_summary;
    DefaultTableModel model;
    String columns[]={"item name","sold quantity"};
    public Admin_order_summary() 
    {
        frame=new JFrame();
        frame.setTitle("ADMIN ORDER SUMMARY");
        
        //ORDERED SUMMARY LABEL
        l0_order_summary=new JLabel("ORDERED SUMMARY");
        l0_order_summary.setBounds(200,40,300,30);
        l0_order_summary.setFont(new Font("verdana",Font.BOLD,20));
        
        //BACK BUTTON
        home_btn=new JButton("BACK");
        home_btn.setBounds(250, 450, 150, 30);
        home_btn.setFont(new Font("verdana",Font.BOLD,20));
        
        //PANEL
        panel=new JPanel(new BorderLayout());
        panel.setBounds(100,100,400,300);
        
        //MODEL
         model=new DefaultTableModel()
         {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        //SETTING COLOUMN NAME FOR MODEL
        model.setColumnIdentifiers(columns);
        
       //TABLE INITIALIZATION 
       table=new JTable();
       table.setBounds(100,100,400,300);
       
       //SCROLLPANE INITIALIZATION
       JScrollPane scroll=new JScrollPane(table);
       scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
       //SET TABLE TO MODEL
       table.setModel(model);
       
       //AUTO RESIZING ALL COLOUMNS
       table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       table.setFillsViewportHeight(true);
       
       //SETTING GRID COLOUR
       table.setGridColor(Color.red);
               
        try
        {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            
            //SELECTING ALL VALUES FROM ORDER SUMMARY TABLE
            String query="select * from order_summary";
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
                //S1 IS USED TO STORE ITEM NAME
                String s1=rs.getString("item_name");
                //S2 IS USED TO STORE SOLD QUANTITY
                String s2=rs.getString("sold_quantity");
                //ADDING DATA INTO JTABLE
                model.addRow(new Object[]{s1,s2});                        
            }
            con.close();
        }
        catch(Exception e)
        {
            
        }
        
        home_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin_choice();
                frame.setVisible(false);
            }
        });
        
        frame.add(l0_order_summary);
        frame.add(home_btn);
        
        panel.add(scroll);
        frame.add(panel);
        
        frame.setLayout(null);
        frame.setBounds(100, 100, 700, 600);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        frame.setVisible(true);
        
    }
    public static void main(String[] args) 
    {
        new Admin_order_summary();
    }
    
}
