
package bootathan;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Qr_image
{
    JFrame frame;
    JLabel l0_scan,l1_code;
    int flag=0;
    int total=0;
    String item="",quant="",sold="";
    ImageIcon icon=new ImageIcon("E:\\back4.png");
    public Qr_image(String name,String tabl)
    {
        frame=new JFrame();
        frame.setTitle("QR CODE_IMAGE");
        
        //SCAN LABEL
        l0_scan=new JLabel("SCAN THE QR CODE");
        l0_scan.setBounds(230, 40, 250, 30);
        l0_scan.setFont(new Font("Verdana", Font.BOLD, 20));
        
        //CODE LABEL
        l1_code=new JLabel(icon);
        l1_code.setBounds(250,100,icon.getIconWidth(),icon.getIconHeight());
        
        l1_code.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                JOptionPane.showMessageDialog(frame, "PAYMENT SUCCESSFUL");
                
                try
                 {
                     Connection con1=Bean.getConnection();
                     Statement st1=con1.createStatement();
                     
                     String query1="select * from "+tabl+" ";
                     ResultSet rs1=st1.executeQuery(query1);
                     item="";
                     quant="";
                     String cost="",orgi_cost="";
                     while(rs1.next())
                     {
                         flag=0;
                         item=rs1.getString("item_name");
                         quant=rs1.getString("served_quantity");
                         System.out.println(rs1.getString("item_name")+"  "+rs1.getString("served_quantity"));
                         
                         try
                         {
                             Connection con2=Bean.getConnection();
                             Statement st2=con2.createStatement();
                             
                             String query2="select * from order_summary";
                             ResultSet rs2=st2.executeQuery(query2);
                             System.out.println("Inside order summary outside while");
                             while(rs2.next())
                             {
                                 String m1=rs2.getString("item_name");
                                 if(m1.equals(item)) 
                                 {
                                     flag=1;
                                     break;
                                 }
                             }
                             
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
                                             String x2=rs4.getString("item_name");
                                             if(x2.equals(item))
                                             {
                                                 cost=rs4.getString("cost");
                                                 orgi_cost=rs4.getString("original_cost");
                                                 break;
                                             }
                                         }
                                        
                                     int i_cost=Integer.parseInt(cost);
                                     int i_orgi_cost=Integer.parseInt(orgi_cost);
                                     
                                     int tota=0;
                                     tota=i_cost-i_orgi_cost;
                                     String s_tota=Integer.toString(tota);
                                     
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
                                    String served=rs1.getString("served_quantity");
                                    
                                    
                              
                                        Statement st6=con5.createStatement();
                                        
                                        String query6="select * from order_summary";
                                        ResultSet rs6=st6.executeQuery(query6);
                                        
                                        while(rs6.next())
                                        {
                                          if(rs6.getString("item_name").equals(item))
                                          {
                                            sold=rs6.getString("sold_quantity");
                                              System.out.println("Inside rs6");
                                            break;
                                          }  
                                        }
                                        
                    
                                    
                                     System.out.println("string  "+served+"  "+sold);
                                     
                                    int i_served=Integer.parseInt(served);
                                     
                                    int i_sold=Integer.parseInt(sold);
                                    System.out.println(i_served+" "+i_sold);
                                    
                                    total=i_served+i_sold;
                                     System.out.println(total);
                                    String res=Integer.toString(total);
                                     
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
                             System.out.println("INSIDE rs2");
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
                         
                         String que="delete from ordered where table_no='"+tabl+"' ";
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
    });
        
        frame.add(l0_scan);
        frame.add(l1_code);
        frame.setLayout(null);
        frame.setBounds(100, 100, 700, 500);
        frame.getContentPane().setBackground(Color.yellow);
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new Qr_image("mvp","a1");
    }
}


