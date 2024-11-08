import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Paybill extends JFrame {
    JLabel heading,name,meter,month,units,totalbills,jtname,jtmeternumber,jtunits,jttotalbills;
    Choice Cmonth;
    JButton jbBack;
    Paybill(String pmeter){
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setBounds(100,100,600,500);

        heading=new JLabel("Pay bill");
        heading.setBounds(200,10,150,30);
        add(heading);

        name=new JLabel("Name");
        name.setBounds(20,50,100,20);
        add(name);

        jtname=new JLabel("");
        jtname.setBounds(130,50,159,20);
        add(jtname);

        meter=new JLabel("Meter Number");
        meter.setBounds(30,80,100,20);
        add(meter);

        jtmeternumber=new JLabel("");
        jtmeternumber.setBounds(130,80,150,20);
        add(jtmeternumber);

        month=new JLabel("Month");
        month.setBounds(30,110,100,20);
        add(month);

        Cmonth=new Choice();
        Cmonth.add("january");
        Cmonth.add("january");
        Cmonth.add("February");
        Cmonth.add("March");
        Cmonth.add("April");
        Cmonth.add("May");
        Cmonth.add("June");
        Cmonth.add("july");
        Cmonth.add("August");
        Cmonth.add("September");
        Cmonth.add("October");
        Cmonth.add("November");
        Cmonth.add("December");
        Cmonth.setBounds(130,110,150,20);

        add(Cmonth);

        units=new JLabel("Unit");
        units.setBounds(30,140,100,20);
        add(units);

        jtunits=new JLabel("");
        jtunits.setBounds(130,140,150,20);
        add(jtunits);

        totalbills=new JLabel("Total bills");
        totalbills.setBounds(30,170,100,20);
        add(totalbills);

        jttotalbills=new JLabel("");
        jttotalbills.setBounds(130,170,150,20);
        add(jttotalbills);

        jbBack=new JButton("Back");
        jbBack.setBounds(70,210,100,20);
        jbBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Project("","");
            }
        });
        jbBack.setBackground(Color.BLACK);
        jbBack.setForeground(Color.white);

        add(jbBack);





        Cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
          try{
              Con db=new Con();
              PreparedStatement ps1=db.connection.prepareStatement("select * from bill where meter_no='"+jtmeternumber.getText()+"' And month='"+Cmonth.getSelectedItem()+"'");
              ResultSet res=ps1.executeQuery();

              while (res.next()) {
                  jttotalbills.setText(res.getString("totalbill"));
                  jtunits.setText(res.getString("units"));

              }
              ps1.close();
              db.connection.close();


          }   catch (Exception e){
              e.printStackTrace();
          }
            }
        });


        try {
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("select * from Customer where meternumber=?");
            ps.setString(1,pmeter);

            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jtname.setText(rs.getString("name"));
                jtmeternumber.setText(rs.getString("meternumber"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }





    }

    public static void main(String[] args) {
        new Paybill("");
    }
}
