
package bootathan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Admin_profit_table 
{
    JFrame frame;
    JPanel panel;
    JTable table;
    JButton home_btn;
    JLabel l0_profit_table,l1_overall_profit;
    JTextField t1_overall_profit;
    DefaultTableModel model;
    int tt=0;
    String s_overall_profit="";
    String columns[]={"item name","profit amount"};
    public Admin_profit_table() 
    {
        frame=new JFrame();
        frame.setTitle("PROFIT TABLE");
        
        //PROFIT TABLE LABEL
        l0_profit_table=new JLabel("PROFIT TABLE");
        l0_profit_table.setBounds(200,40,300,30);
        l0_profit_table.setFont(new Font("verdana",Font.BOLD,20));
        
        l1_overall_profit=new JLabel("OVERALL PROFIT:");
        l1_overall_profit.setBounds(280,440,220,30);
        l1_overall_profit.setFont(new Font("verdana",Font.BOLD,20));
        
        t1_overall_profit=new JTextField();
        t1_overall_profit.setBounds(500, 440, 150, 30);
        t1_overall_profit.setFont(new Font("verdana",Font.BOLD,20));
        
        //HOME BUTTON
        home_btn=new JButton("BACK");
        home_btn.setBounds(250, 490, 150, 30);
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
        
        //INITIALIZING TABLE
        table=new JTable();
        table.setBounds(100,100,400,300);
        
        //INITIALIZING SCROLLPANE
        JScrollPane scroll=new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //SETTING TABLE TO THE MODEL
        table.setModel(model);
        
        //AUTO RESIZE ALL COLOUMNS
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        
        //SET GRID COLOUR
        table.setGridColor(Color.red);
        tt=0;
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
                
                //S3 IS USED TO STORE PROFIT
                String s3=rs.getString("profit");
                
                //STRING S2 IS USED TO STORE SOLD QUANTITY
                String s2=rs.getString("sold_quantity");
                
                int i1=Integer.parseInt(s3);
                int i2=Integer.parseInt(s2);
                tt+=(i1*i2);
                //ADDING DATA INTO JTABLE
                model.addRow(new Object[]{s1,(i1*i2)});
                        
            }
            s_overall_profit=Integer.toString(tt);
            t1_overall_profit.setText(s_overall_profit);
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
        
        frame.add(home_btn);
        frame.add(l0_profit_table);
        panel.add(scroll);
        frame.add(panel);
        frame.add(l1_overall_profit);
        frame.add(t1_overall_profit);
        frame.setLayout(null);
        frame.setBounds(100, 100, 700, 600);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new Admin_profit_table();
    }
    
}
