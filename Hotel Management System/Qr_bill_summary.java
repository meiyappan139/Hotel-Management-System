
package bootathan;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import java.text.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class Qr_bill_summary
{
    JFrame frame;
    JPanel panel;
    JTable table;
    JLabel l0_bill_summary_lbl,l1_customer_name_lbl,l2_table_no,l3_sub_total,l4_net_total_lb1,l5_amount_recieved_lbl ,l6_due_amount;
    JTextField t1_customer_name,t2_table_no,t3_sub_total,t4_net_total,t5_amount_recieved,t6_due_amount;
    JLabel l7_date_lbl,l8_time_lbl;
    JButton b1_pay_now;
    DefaultTableModel model;
    String columns[]={"itemname","quantity","amount"};
    int total=0;
    String date="",time="",da="DATE : ",ti="TIME : ";
    
    public Qr_bill_summary(String name,String tabl) 
    {
        
        frame=new JFrame();
        frame.setTitle("QR BILL SUMMARY");
        panel=new JPanel(new BorderLayout());
        
        l0_bill_summary_lbl=new JLabel("BILL SUMMARY");
        l1_customer_name_lbl=new JLabel("CUSTOMER NAME:");
        l2_table_no=new JLabel("TABLE NO:");
        l3_sub_total=new JLabel("SUB TOTAL:");
        l4_net_total_lb1=new JLabel("NET TOTAL:");
        l7_date_lbl=new JLabel();
        l8_time_lbl=new JLabel();
        
        t1_customer_name=new JTextField();
        t2_table_no=new JTextField();
        t3_sub_total=new JTextField();
        t4_net_total=new JTextField();
        
        b1_pay_now=new JButton("PAY NOW");
        
        //BILL SUMMARY LABEL
        l0_bill_summary_lbl.setBounds(300,30,200,30);
        l0_bill_summary_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //CUSTOMER NAME LABEL
        l1_customer_name_lbl.setBounds(40, 100, 250, 30);
        l1_customer_name_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //CUSTOMER NAME TEXTFIELD
        t1_customer_name.setBounds(300,100,200,30);
        t1_customer_name.setFont(new Font("Verdana",Font.BOLD,20));
        
        t1_customer_name.setText(name);
        t2_table_no.setText(tabl);
        
        //DATE LABEL
        l7_date_lbl.setBounds(550, 30, 300, 30);
        l7_date_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //TIME LABEL
        l8_time_lbl.setBounds(550, 60, 300, 30);
        l8_time_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        t1_customer_name.setEditable(false);
        t2_table_no.setEditable(false);
        
        //TABEL NO LABEL
        l2_table_no.setBounds(40, 150, 250, 30);
        l2_table_no.setFont(new Font("Verdana",Font.BOLD,20));
        
        //TABEL NO TEXTFIELD
        t2_table_no.setBounds(300, 150, 200, 30);
        t2_table_no.setFont(new Font("Verdana",Font.BOLD,20));
        
        //PANEL
        panel.setBounds(60, 200, 600, 200);
        
        //MODEL
        model=new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //return compare[column];
                return false;
            }
            
        };
        
        //ADDING COLOUMN NAME TOLABEL
         model.setColumnIdentifiers(columns);
         
        table=new JTable();
        table.setBounds(60,200,600,200);
        
        //INITIALIZE SCROLLPANE
        JScrollPane scroll=new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //INITIALIZE TABLE TO MODEL
        table.setModel(model);
        //INITIALIZE TABLE TO AUTO RESIZE ALL COLUMNS 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        //SET TABLE GRID COLOUR 
        table.setGridColor(Color.red);
        
        //SUB TOTAL LABEL
        l3_sub_total.setBounds(500,430,150,30);
        l3_sub_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //SUB TOTAL TEXTFIELD
        t3_sub_total.setBounds(660,430,100,30);
        t3_sub_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //NET TOTAL LABEL
        l4_net_total_lb1.setBounds(500, 470, 150, 30);
        l4_net_total_lb1.setFont(new Font("Verdana",Font.BOLD,20));
        
        //NET TOTAL TEXTFIELD
        t4_net_total.setBounds(660, 470, 100, 30);
        t4_net_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //PAY NOW BUTTON
        b1_pay_now.setBounds(550, 540, 150, 30);
        b1_pay_now.setFont(new Font("Verdana",Font.BOLD,20));
        
        total=0;
        
        try
        {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            String query1="select * from "+tabl+" ";
            ResultSet rs=st.executeQuery(query1);
            while(rs.next())
            {
                String item_name=rs.getString("item_name");
                String quantity=rs.getString("served_quantity");
                String rate=rs.getString("rate");
                
                int i_quantity=Integer.parseInt(quantity);
                int i_rate=Integer.parseInt(rate);
                
                int amount=i_quantity*i_rate;
                total+=amount;
                //ADDING VALUES TO THE TABLE
                model.addRow(new Object[]{item_name,quantity,amount});
            }
            String s1=Integer.toString(total);
            int am=(int)(total*0.09);
            int t=total+am;
            String s2=Integer.toString(t);
            
            t3_sub_total.setText(s1);
            t4_net_total.setText(s2);
        }
        catch(Exception ee)
        {
            System.out.println(ee);
        }
        
        b1_pay_now.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                
                new Qr_image(name,tabl);
            }
        });
        
        //FOR DATE AND TIME
        SimpleDateFormat sdformat=new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat sdformat1=new SimpleDateFormat("hh:mm:ss a");
        java.util.Date date1=new java.util.Date();
        
        da=da+sdformat.format(date1);
        ti+=sdformat1.format(date1);
        
        l7_date_lbl.setText(da);
        l8_time_lbl.setText(ti);
        
        frame.getContentPane().setBackground(Color.yellow);
        frame.add(l0_bill_summary_lbl);
        frame.add(l1_customer_name_lbl);
        frame.add(l2_table_no);
        frame.add(l3_sub_total);
        frame.add(l4_net_total_lb1);
        frame.add(l7_date_lbl);
        frame.add(l8_time_lbl);
        frame.add(t1_customer_name);
        frame.add(t2_table_no);
        frame.add(t3_sub_total);
        frame.add(t4_net_total);
        panel.add(scroll);
        frame.add(panel);
        frame.add(b1_pay_now);
        frame.setLayout(null);
        frame.setBounds(100,100,800,700);
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new Qr_bill_summary("mvp","a1");
    }
}
