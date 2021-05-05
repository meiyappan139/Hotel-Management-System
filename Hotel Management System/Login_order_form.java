
package bootathan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Login_order_form
{
    JLabel l0_visited_count,l1_table_no,l2_customer_name,l3_item_name,l4_quantity,l5_subtotal,l6_net_total,l7_recieved_amount,l8_due_amount,l9_remove_lbl;
    JTextField t0_date_time,t2_customer_name,t5_subtotal,t6_net_total,t7_recieved_amount,t8_due_amount;
    JButton b1_add_item_btn,b2_order_now,b3_bill,b4_remove;
    JComboBox c1_food_items,c0_table_no;
    JTable table;
    DefaultTableModel model;
    int val=-1;
    int r=-2;
    int ok=0;
    
    String selected_food="";
    String selected_food_price="";
    String coloumn_Names[]={"ITEM NO","ITEM NAME","QUANTITY","RATE","AMOUNT"};
    String table_no;
    String customer_name;
    JFrame frame;
     JPanel panel;
     
    boolean compare[]={false,false,false,false,false};

    public Login_order_form(String name,String visit)
    {
        frame=new JFrame();
        frame.setTitle("LOGIN ORDER FORM");
        panel=new JPanel(new BorderLayout());
        
        ok=0;
        
        frame.getContentPane().setBackground(Color.YELLOW);
        l0_visited_count=new JLabel();
        l1_table_no=new JLabel("TABLE NO:");
        l2_customer_name=new JLabel("CUSTOMER NAME:");
        l3_item_name=new JLabel("ITEM NAME:");
        l4_quantity=new JLabel("QUANTITY:");
        
        l5_subtotal=new JLabel("SUB TOTAL:");
        l6_net_total=new JLabel("NET TOTAL:");
        l7_recieved_amount=new JLabel("ENTER RECIEVED AMOUNT:");
        l8_due_amount=new JLabel("AMOUNT DUE:");
        l9_remove_lbl=new JLabel("select the food item from the table if you want to remove");
        
        t2_customer_name=new JTextField();
        t2_customer_name.setText(name);
        t2_customer_name.setEditable(false);
        
        t5_subtotal=new JTextField();
        t6_net_total=new JTextField();
        t7_recieved_amount=new JTextField();
        t8_due_amount=new JTextField();
        
        b1_add_item_btn=new JButton();
        b2_order_now=new JButton();
        b3_bill=new JButton();
        b4_remove=new JButton();
        
        c1_food_items=new JComboBox();
       
        try
        {
            Connection con=Bean.getConnection();
            Statement st=con.createStatement();
            String query="select * from food";
            ResultSet rs=st.executeQuery(query);
            int i_quan=0;
            
            while(rs.next())
            {
                String quan=rs.getString("avail_quantity");
                i_quan=Integer.parseInt(quan);
                if(i_quan>0)
                {
                    c1_food_items.addItem(rs.getString("item_name"));
                }
                
            }
            con.close();
        }
        catch(Exception e1)
        {
            System.out.println(e1.getMessage());
            System.out.println("Error in adding the food items");
        }
        
        
        //INSERTING TABLE NUMBER INTO COMBOBOX
         c0_table_no=new JComboBox();
         String arr[]={"a1","a2","a3","a4","a5","a6","a7","a8","a9","a10"};
         c0_table_no.addItem("SELECT");
         for(int i=0;i<arr.length;i++)
         {
             c0_table_no.addItem(arr[i]);
         }
 
        //INSERT BUTTON 
        b1_add_item_btn.setText("INSERT");
        b2_order_now.setText("ORDER NOW");
        //b3_bill.setText("BILL");
        b4_remove.setText("REMOVE ITEM");

         //CUSTOMER NAME LABEL
        l2_customer_name.setBounds(20, 30, 220, 30);
        l2_customer_name.setFont(new Font("verdana",Font.BOLD,20));
        
        String h1="you are visiting our hotel ";
        String h2=h1+visit;
        String h3=h2+" times";
        
        l0_visited_count.setText(h3);
        l0_visited_count.setBounds(450,30,400,30);
        l0_visited_count.setFont(new Font("verdana",Font.BOLD,20));
        
        //CUSTOMER NAME TEXTFIELD  
        t2_customer_name.setBounds(240, 30, 150, 30);
        t2_customer_name.setFont(new Font("verdana",Font.BOLD,15));
        
        
        
        //TABLE NUMBER LABEL
        l1_table_no.setBounds(20, 70, 210, 30);
        l1_table_no.setFont(new Font("Verdana", Font.BOLD, 20));
        
        
        //TABLE NUMBER COMBOBOX
        c0_table_no.setBounds(180, 70, 150, 30);
        c0_table_no.setFont(new Font("verdana",Font.BOLD,15));
        
        //ITEM NAME LABEL
        l3_item_name.setBounds(20, 110, 200, 30);
        l3_item_name.setFont(new Font("verdana",Font.BOLD,20));
        
        //FOOD ITEM LIST COMBOBOX 
        c1_food_items.setBounds(180, 110, 200, 30);
        
        //QUANTITY LABEL
        l4_quantity.setBounds(430, 110, 200, 30);
        l4_quantity.setFont(new Font("verdana",Font.BOLD,20));
        
        //QUANTITY SPINNER
        SpinnerModel value = new SpinnerNumberModel(1,1,50,1);
        JSpinner spinner = new JSpinner(value);
        spinner.setFont(new Font("verdana",Font.BOLD,15));
        spinner.setBounds(580, 110, 80, 30);
        
         //INSERT FOOD BUTTON
         b1_add_item_btn.setBounds(710, 110, 120, 30);
         b1_add_item_btn.setFont(new Font("verdana",Font.BOLD,20));
        
         //PANEL
         panel.setBounds(20, 150, 850, 300);
        
         //TABLE
         table=new JTable();
         table.setBounds(20, 150, 850, 300);
        
         //SCROLLPANE
         JScrollPane scroll=new JScrollPane(table);
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         
         //SETTING TABLE GRID COLOUR
         table.setGridColor(Color.red);
        
       
         //SUB TOTAL LABEL
         l5_subtotal.setBounds(575, 470, 200, 30);
         l5_subtotal.setFont(new Font("verdana", Font.BOLD, 20));
         
         //SUB TOTAL TEXTFIELD
         t5_subtotal.setText("0");
         t5_subtotal.setBounds(730, 470, 140, 30);
         t5_subtotal.setFont(new Font("verdana", Font.BOLD, 20));
         t5_subtotal.setEditable(false);
         
         //NET TOTAL LABEL
         l6_net_total.setBounds(575,510,200,30);
         l6_net_total.setFont(new Font("verdana", Font.BOLD, 20));
         
         //NET TOTAL TEAXTFIELD
         t6_net_total.setText("0");
         t6_net_total.setBounds(730, 510, 140, 30);
         t6_net_total.setFont(new Font("verdana", Font.BOLD, 20));
         t6_net_total.setEditable(false);
         
         
         //ORDER NOW BUTTON
         b2_order_now.setBounds(650, 560, 200, 30);
         b2_order_now.setFont(new Font("verdana", Font.BOLD, 20));
         
         //REMOVE LABEL      
         l9_remove_lbl.setBounds(30, 650, 550, 30);
         l9_remove_lbl.setFont(new Font("verdana", Font.BOLD, 17));
         
         //REMOVE BUTTON
         b4_remove.setBounds(600, 650, 200, 30);
         b4_remove.setFont(new Font("verdana", Font.BOLD, 20));
        
         // TABLE _NO COMBOBOX action listener
         c0_table_no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                table_no=(String)c0_table_no.getSelectedItem();
                String customer_name=t2_customer_name.getText();
                int table_found=0;
                if(customer_name.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "please enter your name", "Alert",JOptionPane.WARNING_MESSAGE);
                    c0_table_no.setSelectedIndex(0);
                }
                else
                {
                try
                {
                   
                    Connection con1=Bean.getConnection();
                    Statement st2=con1.createStatement();
                    //QUERY7 IS USED TO SELECT TABLE_NO FROM ORDERED TABLE NO
                    String query7="select table_no from ordered where table_no='"+table_no+"' ";
                    ResultSet rs2=st2.executeQuery(query7);
                    table_found=0;
                    
                    if(rs2.next())
                    {
                        table_found=1;
                    }
                    //IF TABLEFOUND=0 CREATE NEW TABLE
                    if(table_found==0)
                    {
                        String query3="create table "+table_no+" (item_no varchar(10) primary key ,item_name varchar(50),quantity varchar(50),rate varchar(10),amount varchar(10),served_quantity int(5) default 0);";
                        String query4="insert into ordered values('"+customer_name+"' , '"+table_no+"')";
                        // CREATING DYNAMIC TABLE FOR TABLE NUMBER
                        int u2=st2.executeUpdate(query3);
                        System.out.println("query 3");
                        // INSERTING NAME AND TABLE NUMBER IN ORDERED TABLE
                        int u1=st2.executeUpdate(query4);
                        System.out.println("query 4");
                        
                        System.out.println("Table succesfully created");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Oops! this table is occupied.\n Please select another table", "Alert",JOptionPane.WARNING_MESSAGE);
                        c0_table_no.setSelectedIndex(0);
                    }
                    con1.close();
                    
                }
                catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(frame, "ISSUE in creating table");
                    System.out.println(ee.getMessage());
                }
            }
            }
        });
         
         
         //INSERT BUTTON ACTION LISTINER
         b1_add_item_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //STR IS USED TO FOOD ITEMS
                String str=(String)c1_food_items.getSelectedItem();
                val=-1;
                //QUANTITY IS USED TO STORE THE QUANTITY
                int quantity=(int)spinner.getValue();
                //TABLE NO IS USED TO STORE THE TABLE NUMBER
                table_no=(String)c0_table_no.getSelectedItem();
                //CUSTOMER NAME IS USED TO STORE CUSTOMER NAME
                customer_name=t2_customer_name.getText();
                if(table_no.equals("SELECT"))
                {
                    JOptionPane.showMessageDialog(frame, "please select the table no", "Alert",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                try
                {
                        Connection con=Bean.getConnection();
                        Statement st=con.createStatement();
                        //QUANT IS USED TO STORE THE QUANTITY
                        String quant=Integer.toString(quantity);
                        //QUERY1 IS USED TO SELECT ALL VALUES FROM FOOD FOR PATICULAR ITEM     
                        String query1="select * from food where item_name='"+str+"' ";
                        ResultSet rs1=st.executeQuery(query1);
                        
                        while(rs1.next())
                        {
                            String s=rs1.getString("cost");
                            int co=Integer.parseInt(s);
                            int rat=quantity*co;
                            //TOTAL AMOUNT OF PARTICULAR PRODUCT FOR THE SELECTED QUANTITY
                            String s1=Integer.toString(rat);
                            String b1=rs1.getString("id");
                            String b2=rs1.getString("item_name");
                            String b3=rs1.getString("cost");
                            // AVAILABLE QUANTITY IN DATABASE
                            String avail=rs1.getString("avail_quantity");
                            System.out.println("avail: "+avail);
                            
                            int no1=Integer.parseInt(avail);
                            int no=no1-quantity;
                            //AVAILABLE QUANTITY IN DATABASE AFTER PURCHASE
                            String s2=Integer.toString(no);
                            
                            
                            int sub1=0;
                            
                            if(no<0)
                            {
                                String str4="insufficient quantiy\n available quantity is "+no1;
                                JOptionPane.showMessageDialog(frame,str4,"Alert",JOptionPane.WARNING_MESSAGE);
                                
                            }
                            else                               
                            {
                                int x=0;
                                String num="";
                                String numb="";
                                try
                                {
                                  Connection conne=Bean.getConnection();
                                  Statement stmt=conne.createStatement();
                                  String str2="select * from "+table_no;
                                  ResultSet rs=stmt.executeQuery(str2);
                                  while(rs.next())

                                  {
                                        if(b2.equals(rs.getString("item_name")))  
                                        {
                                            x=1;
                                            //NUM IS USED TO STORE QUANTITY
                                            num=rs.getString("quantity");
                                            //NUMB IS USED TO STORE AMOUNT
                                            numb=rs.getString("amount");
                                            break;
                                        }
                                  }
                                }
                                catch(Exception ae)
                                {
                                    
                                }
                                if(x==0)
                                {
                                    ok++;
                                String query5="insert into "+table_no+"(item_no,item_name,quantity,rate,amount) values('"+b1+"','"+b2+"','"+quant+"','"+b3+"','"+s1+"'); ";
                                int u3=st.executeUpdate(query5);
                                System.out.println("Inside 1");
                                fun(val);
                                //model.addRow(new Object[]{b1,b2,quant,b3,s1});
                                }
                                else
                                {
                                    
                                   int sum1=Integer.parseInt(num);
                                   int sum2=Integer.parseInt(quant);
                                   int sum3=sum1+sum2;
                                   String res=Integer.toString(sum3);
                                   
                                   int su1=Integer.parseInt(numb);
                                   int su2=Integer.parseInt(s1);
                                   int su3=su1+su2;
                                   String res1=Integer.toString(su3);
                                   
                                    String query5="update "+table_no+" set quantity='"+res+"' where item_name='"+b2+"'";
                                    String query6="update "+table_no+" set amount='"+res1+"' where item_name='"+b2+"'";
                                    
                                    int u4=st.executeUpdate(query5);
                                    int u5=st.executeUpdate(query6);
                                    
                                    fun(val);
                                }
                                sub1+=Integer.parseInt(s1);
                                //GET IS USED TO GET THE SUB TOTAL
                                String get=t5_subtotal.getText();
                                int res=Integer.parseInt(get);
                                res=res+sub1;
                                String cas=Integer.toString(res);
                                t5_subtotal.setText(cas);
                                double amount1=res*0.09;
                                double n_amount=res+amount1;
                                int net_amount=(int)n_amount;
                                String res1=Integer.toString(net_amount);
                                t6_net_total.setText(res1);
                                if(no==0)
                                {
                                    c1_food_items.removeItem(str);
                                }
                                String query6="update food set avail_quantity='"+s2+"' where item_name='"+b2+"' ";
                                int u4=st.executeUpdate(query6);
                                System.out.println("query 6");
                            
                            }
                            
                            
                            
                        }
                      
                    
                    con.close();
                }
                catch(Exception e2)
                {
                }
            }
            }
        });
         
         
    table.addMouseListener(new MouseAdapter() 
    {
        @Override
        
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("r is before selecting :"+r);
            r=table.getSelectedRow();
            
            selected_food=model.getValueAt(r,1).toString();
            selected_food_price=model.getValueAt(r,4).toString();
            
            System.out.println(model.getValueAt(r,0).toString());
           System.out.println(model.getValueAt(r,1).toString());
           System.out.println(model.getValueAt(r,2).toString());
          
        }
        
    });
        
        
    b4_remove.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 String s5=t5_subtotal.getText();
                 String s6=t6_net_total.getText();
                 if(r==-2)
                 {
                     JOptionPane.showMessageDialog(frame,"please select the row to delete.","ALERT",JOptionPane.WARNING_MESSAGE);
                 }
                 else
                 {
                    //TO REMOVE PATICULAR ROW FROM THE MODEL
                     model.removeRow(r);
                     ok--;
                     int sa=Integer.parseInt(s5);
                     int sa1=Integer.parseInt(selected_food_price);
                     int sa2=sa-sa1;
                     int ta=(int)(sa2*0.09);
                     int ta1=sa2+ta;
                     String s11=Integer.toString(sa2);
                     String s12=Integer.toString(ta1);
                     t5_subtotal.setText(s11);
                     t6_net_total.setText(s12);
                     
                     try
                     {
                         Connection conn1=Bean.getConnection();
                         Statement stm=conn1.createStatement();
                         //QUER1 IS USED TO SELECT ALL VALUES FROM TABLE_NO TABLE FOR PARTICULAR ITEM
                         String quer1="select * from "+table_no+" where item_name='"+selected_food+"' ";
                         ResultSet rss=stm.executeQuery(quer1);
                         String quantity1="",serv_quantity="";
                         int i_quantity1=0,i_serv_quantity=0;
                                 
                         if(rss.next())
                         {
                             //QUANTITY1 IS USED TO GET QUANTITY
                             quantity1=rss.getString("quantity");
                             i_quantity1=Integer.parseInt(quantity1);
                             //SERV_QUANTITY IS USED TO STORE SERVED QUANTITY
                             serv_quantity=rss.getString("served_quantity");
                             i_serv_quantity=Integer.parseInt(serv_quantity);
                         }
                         rss.close();
                         //SELECT ALL VALUES FROM FOOD FOR PARTICULAR ITEM
                         String quer3="select * from food where item_name='"+selected_food+"' ";
                         ResultSet rss1=stm.executeQuery(quer3);
                         String avail_quan="";
                         int i_avail_quan=0;
                         int tot=0,s=0;
                         String update_avail_quan="";
                         
                         if(rss1.next())
                         {
                             //AVAIL_QUANT IS USED TO STORE AVAILABLE QUANTITY
                             avail_quan=rss1.getString("avail_quantity");
                         }
                         i_avail_quan=Integer.parseInt(avail_quan);
                         //delting quantity+existing quantity
                         tot=i_quantity1+i_avail_quan;   
                         
                         if(i_avail_quan==0)
                         {
                             c1_food_items.addItem(selected_food);
                         }
                         
                         //QUER2 IS USED  TO UPDATE AVAILABLE QUANTITY FOR PARTICULAR ITEM IN FOOD TABLE
                         String quer2="Update food set avail_quantity="+tot+" where item_name='"+selected_food+"'";
                         
                         int re2=stm.executeUpdate(quer2);
                         
                         if(i_serv_quantity==0)
                         {    
                             //QUER IS USED TO DELETE PARTICULAR ITEM FROM TABLE NUMBER
                             String quer="delete from "+table_no+" where item_name='"+selected_food+"' ";
                             int re=stm.executeUpdate(quer);
                             fun(-3);
                         }
                         else
                         {
                             //QUE IS USED TO UPDATE QUANTITY FROM TABLE_NO
                             String que="update "+table_no+" set quantity="+s+" where item_name='"+selected_food+"' ";
                             int re1=stm.executeUpdate(que);
                             fun(-3);
                         }
                         rss1.close();
                          
                     }
                     catch(Exception ee)
                     {
                     }
                     
                     r=-2;
                 }
                 
             }
         });
      
        b2_order_now.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) 
             {
                 if(ok==0)
                 {
                    JOptionPane.showMessageDialog(frame,"Please select the food item to order","ALERT",JOptionPane.WARNING_MESSAGE);
                 }
                 else
                 {
                      frame.setVisible(false);
                     new Order_now_form(customer_name,table_no);
                 }
                 
             }
         });
        
         

        frame.add(l0_visited_count);
        frame.add(l1_table_no);
        frame.add(l2_customer_name);
        frame.add(l3_item_name);
        frame.add(l4_quantity);
        frame.add(l5_subtotal);
        frame.add(l6_net_total);
        frame.add(l9_remove_lbl);
        frame.add(t2_customer_name);
        frame.add(t5_subtotal);
        frame.add(t6_net_total);
        frame.add(t7_recieved_amount);
        frame.add(t8_due_amount);
        frame.add(spinner);
        frame.add(b1_add_item_btn);
        frame.add(b2_order_now);
        frame.add(b4_remove);
        frame.add(c1_food_items);
        frame.add(c0_table_no);
        //ADDING SCROLL TO PANEL
        panel.add(scroll);
        //ADDING PANEL TO FRAME
        frame.add(panel);
        frame.setBounds(100, 100, 900, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void fun(int v)
    { 
        model=new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
         model.setColumnIdentifiers(coloumn_Names);
         table.setModel(model);
         table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         table.setFillsViewportHeight(true);
         table.setGridColor(Color.red);
         if(v==-1)
         {
             
         try
         {
             Connection conn=Bean.getConnection();
             Statement stt=conn.createStatement();
             String que="select * from "+table_no+" ";
             ResultSet rs=stt.executeQuery(que);
             while(rs.next())
             {
                 String item_no=rs.getString("item_no");
                 String item_nmae=rs.getString("item_name");
                 String quantity=rs.getString("quantity");
                 String rate=rs.getString("rate");
                 String price=rs.getString("amount");
                 model.addRow(new Object[]{item_no,item_nmae,quantity,rate,price});
                 
                 
             }
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
       }
       else //for remove and print model
       {
             
           try
           {
               Connection conn2=Bean.getConnection();
               Statement stmt=conn2.createStatement();
               //QUE IS USED TO SELECT ALL VALUE FROM TABLE_NO TABLE 
               String quer3="select * from "+table_no+" ";
               ResultSet rs2=stmt.executeQuery(quer3);
               
             while(rs2.next())
             {
                 String item_no=rs2.getString("item_no");
                 String item_nmae=rs2.getString("item_name");
                 String quantity=rs2.getString("quantity");
                 String rate=rs2.getString("rate");
                 String price=rs2.getString("amount");
                 int quan=Integer.parseInt(quantity);
                 if(quan!=0)
                 {
                     model.addRow(new Object[]{item_no,item_nmae,quantity,rate,price});
                 }
             }
           }
           catch(Exception e)
           { 
           }
           
       }
    }
    
    public static void main(String[] args) 
    {
        new Login_order_form("mvp","1");
    }
      
}
