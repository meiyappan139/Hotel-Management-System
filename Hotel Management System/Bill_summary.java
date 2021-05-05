

package bootathan;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.*;
import java.util.Date;

public class Bill_summary 
{
    JFrame frame;
    JPanel panel;
    JTable table;
    JLabel l0_bill_summary_lbl,l1_customer_name_lbl,l2_table_no,l3_sub_total,l4_net_total_lb1,l5_amount_recieved_lbl ,l6_due_amount;
    JLabel l7_date_lbl,l8_time_lbl;
    JTextField t1_customer_name,t2_table_no,t3_sub_total,t4_net_total,t5_amount_recieved,t6_due_amount;
    JButton b1_pay_now;
    DefaultTableModel model;
    String columns[]={"itemname","quantity","amount"};
    int total=0;
    int va=0;
    int flag=0;
    String item="",quant="",sold="";
    String date="",time="",da="DATE : ",ti="TIME : ";
    
    public Bill_summary(String name,String tabl) 
    {
        frame=new JFrame();
        frame.setTitle("BILL SUMMARY");
        panel=new JPanel(new BorderLayout());
        
        l0_bill_summary_lbl=new JLabel("BILL SUMMARY");
        l1_customer_name_lbl=new JLabel("CUSTOMER NAME:");
        l2_table_no=new JLabel("TABLE NO:");
        l3_sub_total=new JLabel("SUB TOTAL:");
        l4_net_total_lb1=new JLabel("NET TOTAL:");
        l5_amount_recieved_lbl=new JLabel("GIVEN AMOUNT:");
        l6_due_amount=new JLabel("DUE AMOUNT:");
        l7_date_lbl=new JLabel();
        l8_time_lbl=new JLabel();
        
        t1_customer_name=new JTextField();
        t2_table_no=new JTextField();
        t3_sub_total=new JTextField();
        t4_net_total=new JTextField();
        t5_amount_recieved=new JTextField();
        t6_due_amount=new JTextField();
        
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
        t1_customer_name.setEditable(false);
        t1_customer_name.setText(name);
        
        //DATE LABEL
        l7_date_lbl.setBounds(550, 30, 300, 30);
        l7_date_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //TABLE NO LABEL
        l2_table_no.setBounds(40, 150, 250, 30);
        l2_table_no.setFont(new Font("Verdana",Font.BOLD,20));
        
        //TABLE NO TEXTFIELD
        t2_table_no.setBounds(300, 150, 200, 30);
        t2_table_no.setFont(new Font("Verdana",Font.BOLD,20));
        t2_table_no.setEditable(false);
        t2_table_no.setText(tabl);
        
        //TIME LABEL
        l8_time_lbl.setBounds(550, 60, 300, 30);
        l8_time_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
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
        
        //SETTING COLOUMN NAME FOR MODEL
        model.setColumnIdentifiers(columns);
        
        //INITIALIZING TABLE
        table=new JTable();
        table.setBounds(60,200,600,200);
        
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
        
        //SUB TOTAL LABEL
        l3_sub_total.setBounds(500,430,150,30);
        l3_sub_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //SUB TOTAL LABEL
        t3_sub_total.setBounds(660,430,100,30);
        t3_sub_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //NET TOTAL LABEL
        l4_net_total_lb1.setBounds(500, 470, 150, 30);
        l4_net_total_lb1.setFont(new Font("Verdana",Font.BOLD,20));
        
        //NET LABEL TEXTFIELD
        t4_net_total.setBounds(660, 470, 100, 30);
        t4_net_total.setFont(new Font("Verdana",Font.BOLD,20));
        
        //AMOUNT RECIEVED LABEL
        l5_amount_recieved_lbl.setBounds(450, 510, 250, 30);
        l5_amount_recieved_lbl.setFont(new Font("Verdana",Font.BOLD,20));
        
        //AMOUNT RECIEVED TEXTFIELD
        t5_amount_recieved.setBounds(660,510,100,30);
        t5_amount_recieved.setFont(new Font("Verdana",Font.BOLD,20));
        
        //DUE AMOUNT LABEL
        l6_due_amount.setBounds(472, 550, 250, 30);
        l6_due_amount.setFont(new Font("Verdana",Font.BOLD,20));
        
        //DUE AMOUNT TEXTFIELD
        t6_due_amount.setBounds(660,550,100,30);
        t6_due_amount.setFont(new Font("Verdana",Font.BOLD,20));
        
        //PAY NOW BUTTON
        b1_pay_now.setBounds(550, 610, 150, 30);
        b1_pay_now.setFont(new Font("Verdana",Font.BOLD,20));
        
        total=0;
        
        try
        {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            
            //SELECT ALL VALUES FROM TABLE_NO TABLE
            String query1="select * from "+tabl+" ";
            ResultSet rs=st.executeQuery(query1);
            while(rs.next())
            {
                //ITEM NAME IS USED TO STORE THE ITEM NAME
                String item_name=rs.getString("item_name");
                //QUANTITY IS USED TO STORE SERVED QUANTITY
                String quantity=rs.getString("served_quantity");
                //RATE IS USED TO STORE THE RATE
                String rate=rs.getString("rate");
                
                int i_quantity=Integer.parseInt(quantity);
                int i_rate=Integer.parseInt(rate);
                
                int amount=i_quantity*i_rate;
                total+=amount;
                
                //ADDING DATA INTO JTABLE
                model.addRow(new Object[]{item_name,quantity,amount});
            }
            //S1 IS USED TO STORE THE SUB TOTAL AMOUNT
            String s1=Integer.toString(total);
            int am=(int)(total*0.09);
            int t=total+am;
            //S2 IS USED TO STORE THE NET TOTAL AMOUNT
            String s2=Integer.toString(t);
            
            t3_sub_total.setText(s1);
            t4_net_total.setText(s2);
        }
        catch(Exception ee)
        {
           
        }
       
        t5_amount_recieved.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
               
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                 //STR13 IS USED TO STORE RECIEVED AMOUNT
                 String str13=t5_amount_recieved.getText();
                                
                 if(str13.equals(""))
                 {
                     t6_due_amount.setText("");
                 }
                int no1=Integer.parseInt(str13);
                
                System.out.println("no1 is:"+no1);
                
                //STR14 IS USED TO STORE RECIEVED TOTAL
                String str14=t4_net_total.getText();
                int no2=Integer.parseInt(str14);
                System.out.println("no2 is:"+no2);
                
                int no=no1-no2;
                System.out.println("no is:"+no);
                
                //STR15 IS USED TO STORE DUE AMOUNT
                String str15=Integer.toString(no);
                t6_due_amount.setText(str15);
            }
        });
        
        b1_pay_now.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s=t5_amount_recieved.getText();
                String s1=t6_due_amount.getText();
                System.out.println("s1 is:"+s1);
                va=0;
                if(s.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Please enter the given amount","ALERT",JOptionPane.WARNING_MESSAGE);
                    
                }
                else if(!s.equals(""))
                {
                 va=Integer.parseInt(s1);   
               
                if(va<0)
                {
                    
                    JOptionPane.showMessageDialog(frame, "Insufficient amount","ALERT",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                 JOptionPane.showMessageDialog(frame, "PAYMENT SUCESSFUL");
                 
                 try
                 {
                     Connection con1=Bean.getConnection();
                     Statement st1=con1.createStatement();
                     
                     //SELECT ALL VALUES FROM TABLE_ NO TABLE
                     String query1="select * from "+tabl+" ";
                     ResultSet rs1=st1.executeQuery(query1);
                     item="";
                     quant="";
                     String cost="",orgi_cost="";
                     while(rs1.next())
                     {
                         flag=0;
                         //ITEM STORES TEM NAME
                         item=rs1.getString("item_name");
                         //QUANT IS USED TO STORE SERVED QUANTITY
                         quant=rs1.getString("served_quantity");
                         System.out.println(rs1.getString("item_name")+"  "+rs1.getString("served_quantity"));
                         
                         try
                         {
                             Connection con2=Bean.getConnection();
                             Statement st2=con2.createStatement();
                             
                             //SELECT ALL VALUES FROM ORDER_SUMARY
                             String query2="select * from order_summary";
                             ResultSet rs2=st2.executeQuery(query2);
                             
                             while(rs2.next())
                             {
                                 //M1 IS UDER TO STORE ITEM NAME
                                 String m1=rs2.getString("item_name");
                                 if(m1.equals(item)) 
                                 {
                                     flag=1;
                                     break;
                                 }
                             }
                             //IF FLAG=1 MEANS WE NEED TO UPDATE ELSE WE NEED TO INSERT
                             if(flag==0)
                             {
                                 try
                                 {
                                     Connection con3=Bean.getConnection();
                                     Statement st3=con3.createStatement();
                                     Statement st4=con3.createStatement();
                                     String query4="select * from food";
                                     ResultSet rs4=st4.executeQuery(query4);
                                         
                                         while(rs4.next())
                                         {
                                             //X2 IS IS USED TO STORE ITEM NAME
                                             String x2=rs4.getString("item_name");
                                             if(x2.equals(item))
                                             {
                                                 //COST IS USED TO STORE THE COST
                                                 cost=rs4.getString("cost");
                                                 //ORGI_COST IS USED TO STORE THE ORIGINAL COST
                                                 orgi_cost=rs4.getString("original_cost");
                                                 break;
                                             }
                                         }
                                        
                                     int i_cost=Integer.parseInt(cost);
                                     int i_orgi_cost=Integer.parseInt(orgi_cost);
                                     
                                     int tota=0;
                                     tota=i_cost-i_orgi_cost;
                                     String s_tota=Integer.toString(tota);
                                     
                                     //QUERY3 IS USED TO INSERT IN TO ORDER_SUMMARY TABLE
                                     String query3="insert into order_summary values('"+item+"','"+s_tota+"','"+quant+"');";
                                     int y=st3.executeUpdate(query3);
                                     
                                     con3.close();
                                 }
                                 catch(Exception e3)
                                 {
                                     System.out.println("e3: "+e3);
                                 }
                             }
                             else
                             {
                                 try
                                 {
                                     Connection con5=Bean.getConnection();
                                     Statement st5=con5.createStatement();
                                    
                                     sold="";
                                     //SERVED IS USED TO STORE SERVED QUANTITY
                                     String served=rs1.getString("served_quantity");
                                    
                                     Statement st6=con5.createStatement();
                                     //QUERY6 IS USED TO SELECT ALL VALUE FROM ORDER SUMMARY
                                     String query6="select * from order_summary";
                                     ResultSet rs6=st6.executeQuery(query6);
                                        
                                     while(rs6.next())
                                     {
                                        if(rs6.getString("item_name").equals(item))
                                        {
                                            //SOLD IS USED TO STORE THE SOLD QUANTITY
                                            sold=rs6.getString("sold_quantity");
                                            break;
                                        }  
                                     }
                                                                           
                                    int i_served=Integer.parseInt(served);
                                    int i_sold=Integer.parseInt(sold);
                                    total=i_served+i_sold;
                                    String res=Integer.toString(total);
                                    //QUERY5 IS USED TO UPDATE SOLD_QUANTITY FOR PARTICULAR ITEM IN ORDER SUMMARY TABLE 
                                    String query5="Update order_summary set sold_quantity='"+res+"' where item_name='"+item+"' ";
                                    int r5=st5.executeUpdate(query5);
                                    System.out.println("QUERY EXECUTED");
                                    con5.close();
                                 }
                                 catch(Exception e5)
                                 {
                                     System.out.println("e5: "+e5);
                                 }
                             }
                             con2.close();
                         }
                         catch(Exception e2)
                         {
                             System.out.println("e2: "+e2);
                         }
                         
                     }
                     con1.close();
                     
                     try
                     {
                         Connection co=Bean.getConnection();
                         Statement stt=co.createStatement();
                         //QUE IS USED TO DELETE THE TABLE NUMBER FROM THE ORDERED TABLE
                         String que="delete from ordered where table_no='"+tabl+"' ";
                         //QUE1 IS USED TO DROP TABLE TABLE_NO
                         String que1="drop table "+tabl+" ";
                         
                         int r2=stt.executeUpdate(que);
                         int r3=stt.executeUpdate(que1);
                     }
                     catch(Exception ee)
                     {
                         System.out.println("ee:"+ee);
                     }
                    frame.setVisible(false);
                 }
                 catch(Exception e1)
                 {
                     System.out.println("e1: "+e1);
                 }
                 
                new Feedback_form(name);
                }
                }
                
            }
        });
        
        //TO GET THE SYSTEM DATE AND TIME
        SimpleDateFormat sdformat=new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat sdformat1=new SimpleDateFormat("hh:mm:ss a");
        Date date1=new Date();
        
        da=da+sdformat.format(date1);
        //System.out.println(sdformat.format(date1););
        ti+=sdformat1.format(date1);
        
        
        l7_date_lbl.setText(da);
        
        l8_time_lbl.setText(ti);
        
        
        frame.getContentPane().setBackground(Color.yellow);
        frame.add(l0_bill_summary_lbl);
        frame.add(l1_customer_name_lbl);
        frame.add(l2_table_no);
        frame.add(l3_sub_total);
        frame.add(l4_net_total_lb1);
        frame.add(l5_amount_recieved_lbl);
        frame.add(l6_due_amount);
        frame.add(l7_date_lbl);
        frame.add(l8_time_lbl);
        
        frame.add(t1_customer_name);
        frame.add(t2_table_no);
        frame.add(t3_sub_total);
        frame.add(t4_net_total);
        frame.add(t5_amount_recieved);
        frame.add(t6_due_amount);
        panel.add(scroll);
        frame.add(panel);
        
        frame.add(b1_pay_now);
        
        
        frame.setLayout(null);
        frame.setBounds(100,100,850,730);
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new Bill_summary("mvp","a1");
    }
}
