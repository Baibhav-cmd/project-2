import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CalculateBill extends JFrame  implements ActionListener {
    JLabel Heading,Name,Address,Unitconusmed,Month,meternumber,jtname,jtaddress;
    Choice c1,c2;
    JTextField jtunitConsumed;
    JButton jbsubmit,jbcancel;
    String  meter;
    CalculateBill(){


        getContentPane().setBackground(Color.white);
        setVisible(true);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel jPanel=new JPanel();
        jPanel.setBackground(Color.cyan);
        jPanel.setBounds(300,0,600,500);
        jPanel.setLayout(null);
        add(jPanel,BorderLayout.CENTER);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hicon2.jpg"));
        Image img1=i1.getImage().getScaledInstance(150,380,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(img1);
        JLabel image=new JLabel(i2);
        image.setBounds(20,30,150,380);
        add(image,BorderLayout.WEST);

        Heading=new JLabel("Calculating Bill");
        Heading.setBounds(200,10,200,30);
        Heading.setForeground(Color.BLACK);
        Heading.setFont(new Font("Arial",Font.BOLD,20));
        jPanel.add(Heading);

        meternumber=new JLabel("Meter No:");
        meternumber.setBounds(10,40,100,20);
        jPanel.add(meternumber);

        c1=new Choice();
        try{
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("Select * from Customer");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                meter= rs.getString("meternumber");
                c1.add(meter);
            }
            ps.close();
            db.connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        c1.setBounds(110,40,150,20);
        jPanel.add(c1);

        Name=new JLabel("Name:");
        Name.setBounds(10,70,100,20);
        jPanel.add(Name);

        jtname=new JLabel();
        jtname.setBounds(110,70,100,20);
        jPanel.add(jtname);

        Address=new JLabel("Address:");
        Address.setBounds(10,100,100,20);
        jPanel.add(Address);

        jtaddress=new JLabel();
        jtaddress.setBounds(110,100,100,20);
        jPanel.add(jtaddress);

        Unitconusmed=new JLabel("Unit consumed");
        Unitconusmed.setBounds(10,130,100,20);
        jPanel.add(Unitconusmed);

        jtunitConsumed=new JTextField();
        jtunitConsumed.setBounds(110,130,150,20);
        jPanel.add(jtunitConsumed);


        Month=new JLabel("Month:");
        Month.setBounds(10,160,100,20);
        jPanel.add(Month);



        c2=new Choice();
        c2.add("January");
        c2.add("February");
        c2.add("march");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("july");
        c2.add("August");
        c2.add("September");
        c2.add("November");
        c2.add("December");
        c2.setBounds(110,160,150,20);
        jPanel.add(c2);


        jbsubmit=new JButton("Submit");
        jbsubmit.setBackground(Color.darkGray);
        jbsubmit.setForeground(Color.white);
        jbsubmit.addActionListener(this);
        jbsubmit.setBounds(30,270,90,20);
        jPanel.add(jbsubmit);

        jbcancel=new JButton("Cancel");
        jbcancel.setBackground(Color.darkGray);
        jbcancel.setForeground(Color.white);
        jbcancel.setBounds(150,270,90,20);
        jbcancel.addActionListener(this);
        jPanel.add(jbcancel);

        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                try{
                    Con db=new Con();
                    PreparedStatement ps=db.connection.prepareStatement("select * from Customer where meternumber=?");
                    ps.setString(1, c1.getSelectedItem());
                    ResultSet rs=ps.executeQuery();
                    while (rs.next()){

                        jtname.setText( rs.getString("name"));
                        jtaddress.setText(rs.getString("address"));
                    }
                    ps.close();
                    db.connection.close();


                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void actionPerformed(ActionEvent ae) {
        String meterNumber = c1.getSelectedItem();
        String month = c2.getSelectedItem();
        String Name = jtname.getText();
        String Addreas = jtaddress.getText();
        String unit = jtunitConsumed.getText();
        int total = 0;
        int unitCousmer = Integer.parseInt(unit);

        if (ae.getSource() == jbsubmit) {
            try {
                Con db = new Con();
                PreparedStatement ps = db.connection.prepareStatement("select * from tax");
                ResultSet rs = ps.executeQuery();
                PreparedStatement ps1 = null;
                while (rs.next()) {

                    total += unitCousmer * Integer.parseInt(rs.getString("cost_per_unit"));
                    total += Integer.parseInt(rs.getString("meter_rent"));
                    total += Integer.parseInt(rs.getString("service_charge"));
                    total += Integer.parseInt(rs.getString("service_charge"));
                    total += Integer.parseInt(rs.getString("fixed_tax"));


                    ps1 = db.connection.prepareStatement("insert into bill values(?,?,?,?)");
                    ps1.setString(1, meterNumber);
                    ps1.setString(2, month);
                    ps1.setString(3, unit);
                    ps1.setInt(4, total);

                    int res = ps1.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "bill has been sucessfully update");
                        setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "not update sucess");
                    }


                }
                ps1.close();
                db.connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}
