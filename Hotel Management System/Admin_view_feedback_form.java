
package bootathan;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


public class Admin_view_feedback_form 
{
    JFrame frame;
    JPanel panel;
    JTable table;
    JLabel l0_feedback_lbl;
    JButton home_btn;
    DefaultTableModel model;
    String columns[]={"feedback","ratings"};

    public Admin_view_feedback_form()
    {
        frame=new JFrame();
        frame.setTitle("ADMIN FEEDBACK");
        
        //FEEDBACK LABEL
        l0_feedback_lbl=new JLabel("VIEW FEEDBACK");
        l0_feedback_lbl.setBounds(200,40,300,30);
        l0_feedback_lbl.setFont(new Font("verdana",Font.BOLD,20));
        
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
                //return compare[column];
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
            
           //SELECT ALL VALUES FROM FEEDBACK TABLE
            String query="select * from feedback_table";
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
                //S1 IS USED TO STORE FEEDBACK
                String s1=rs.getString("feedback");                
                //S IS USED TO STORE RATING
                String s=rs.getString("rating");                
                //ADDING DATA INTO JTABLE
                model.addRow(new Object[]{s1,s});                        
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
        
        frame.add(l0_feedback_lbl);
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
        new Admin_view_feedback_form();
    }
}
