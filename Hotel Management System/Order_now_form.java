

package bootathan;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class Order_now_form 
{
    JFrame frame;
    JLabel l0_kitchen_lbl,l1_customer_name_lbl,l2_table_no,l3_order_summary_lbl;
    JTextField t1_customer_name,t2_table_no;
    JPanel panel;
    JTable table;
    DefaultTableModel model;
    JButton b1_served_btn;
    String coloumns[]={"item name","quantity"};
    public Order_now_form(String name,String table_no)
    {
        System.out.println(name+"  "+table_no);
        frame=new JFrame();
        frame.setTitle("ORDER_NOW_FORM");
        panel=new JPanel(new BorderLayout());
        frame.setBounds(100, 100, 800, 700);
        frame.getContentPane().setBackground(new Color(250, 250, 12));
        
        l0_kitchen_lbl=new JLabel("ADMIN");
        l1_customer_name_lbl=new JLabel("CUSTOMER NAME:");
        l2_table_no=new JLabel("TABLE NO            :");
        l3_order_summary_lbl=new JLabel("ORDER SUMMARY:");
        
        t1_customer_name=new JTextField();
        t2_table_no=new JTextField();
        
        //KITCHEN LABEL
        l0_kitchen_lbl.setBounds(350, 30, 200, 30);
        l0_kitchen_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //CUSTOMER LABEL
        l1_customer_name_lbl.setBounds(40, 100, 250, 30);
        l1_customer_name_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //CUSTOMER NAME TEXTFIELD
        t1_customer_name.setBounds(300, 100, 200, 30);
        t1_customer_name.setFont(new Font("Verdana", Font.BOLD, 20));
        t1_customer_name.setText(name);
        t1_customer_name.setEditable(false);
        
        //TABLE_NO LABEL
        l2_table_no.setBounds(40, 150, 250, 30);
        l2_table_no.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //TABLE_NO TEXTFIELD
        t2_table_no.setBounds(300, 150, 200, 30);
        t2_table_no.setFont(new Font("Verdana", Font.BOLD, 20));
        t2_table_no.setText(table_no);
        t2_table_no.setEditable(false);
        
        //ORDER SUMMARY LABEL
        l3_order_summary_lbl.setBounds(250, 200, 300, 30);
        l3_order_summary_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //SERVED BUTTON
        b1_served_btn=new JButton("SERVED");
        b1_served_btn.setBounds(300,500,150,30);
        b1_served_btn.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //PANEL
        panel.setBounds(100, 250, 500, 200);
        
        //MODEL
        model=new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
       
       //ADDING COLUMN NAMES TO MODEL
       model.setColumnIdentifiers(coloumns);
       
       //JTABLE
       table=new JTable();
       table.setBounds(100, 250, 500, 200);
       
       //SCROLLPANE
       JScrollPane scroll=new JScrollPane(table);
       scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
       //SET TABLE TO MODEL
       table.setModel(model);
       //RESIZE ALL COLUMNS
       table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       table.setFillsViewportHeight(true);
       //SET TABLE GRID BORDER COLOUR
       table.setGridColor(Color.red);
        
        try
        {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            //QUERY IS USED TO SELECT ALL VALUES FROM TABLE NO
            String query="select * from "+table_no+" ";
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
                //S1 IS USED TO STORE ITEM NAME
                String s1=rs.getString("item_name");
                //S2 IS USED TO STORE QUANTITY
                String s2=rs.getString("quantity");
                int y=Integer.parseInt(s2);
                if(y!=0)
                {
                    model.addRow(new Object[]{s1,s2});
                }
                
            }
            con.close();
        }
        catch(Exception e)
        {
            
        }
        
        
        b1_served_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 try
                 {
                   Connection con1=Bean.getConnection();
                   Statement st1=con1.createStatement();
                   String query1="select * from "+table_no;
                   ResultSet rs1=st1.executeQuery(query1);
                   String quantity="",served_quantity="",item_name="";
                   int i_quantity=0,i_served_quantity=0,tot=0;
                   
                   while(rs1.next())
                   {
                       tot=0;
                       //QUANTITY IS USED TO STORE QUANTITY
                       quantity=rs1.getString("quantity");
                       //SERVED QUANTITY IS USED TO STORE SERVED QUANTITY
                       served_quantity=rs1.getString("served_quantity");
                       //ITEM NAME IS USED TO STORE ITEM NAME
                       item_name=rs1.getString("item_name");
                       i_quantity=Integer.parseInt(quantity);
                       i_served_quantity=Integer.parseInt(served_quantity);
                       tot=i_quantity+i_served_quantity;
                       
                       try
                       {
                           Connection conn=Bean.getConnection();
                           Statement stmt=conn.createStatement();
                        String query2="update "+table_no+" set served_quantity="+tot+",quantity='0',amount='0' where item_name='"+item_name+"' ";
                        int w=stmt.executeUpdate(query2);
                        conn.close();
                       }
                       catch(Exception e2)
                       {
                           System.out.println(e2);
                       }
                   }
                   JOptionPane.showMessageDialog( frame,"Served Sucessful");
                   frame.setVisible(false);
                   new Option_page(name,table_no);
                 }
                 catch(Exception e1)
                 {
                     e1.printStackTrace();
                     System.out.println(e1);
                 }
            }
        });
        
        frame.add(l0_kitchen_lbl);
        frame.add(l1_customer_name_lbl);
        frame.add(l2_table_no);
        frame.add(l3_order_summary_lbl);
        frame.add(t1_customer_name);
        frame.add(t2_table_no);
        frame.add(b1_served_btn);
        panel.add(scroll);
        frame.add(panel);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        new Order_now_form("mani", "a1");
    }
    
}
