import javax.management.remote.JMXAddressable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.jar.JarEntry;

public class Singup extends JFrame implements ActionListener {
    Choice c;
    JLabel jc,jEmail,jname,jpassword,jaddress,jbmeter;
    JTextField jtEmail,jtname,jtPassword,jtaddress,jtmeter;
    JButton jbcreate,jbback;
    Con dbcon=new Con();
    Singup(){

        getContentPane().setBackground(Color.white);
        setVisible(true);
        setSize(600,500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        JPanel jp=new JPanel();
        jp.setBounds(30,30,550,400);
        jp.setBorder(new TitledBorder(new LineBorder(Color.GREEN),"Singup form"));
        jp.setLayout(null);



        jc=new JLabel("Create Account AS:");
        jc.setBounds(10,30,130,20);


         c=new Choice();
        c.setBounds(140,30,80,20);
        c.add("Admin");
        c.add("User");



        jbmeter=new JLabel("Meter Number:");
        jbmeter.setBounds(10,70,100,20);
        jbmeter.setVisible(false);
        jtmeter=new JTextField();
        jtmeter.setBounds(110,70,150,20);
        jtmeter.setVisible(false);

        jEmail=new JLabel("Enter Email:");
        jEmail.setBounds(10,100,100,20);
        jtEmail=new JTextField();
        jtEmail.setBounds(110,100 ,150,20);

        jname=new JLabel("Enter Name:");
        jname.setBounds(10,130,100,20);
        jtname=new JTextField();
        jtname.setBounds(110,130,150,20);

        jpassword=new JLabel("Enter Password:");
        jpassword.setBounds(10,160,100,20);
        jtPassword=new JTextField();
        jtPassword.setBounds(110,160,150,20);

        jaddress=new JLabel("Enter Address:");
        jaddress.setBounds(10,190,100,20);
        jtaddress=new JTextField();
        jtaddress.setBounds(110,190,150,20);

        jbcreate=new JButton("Create");
        jbcreate.setBounds(50,220,100,20);
        jbcreate.setBackground(Color.BLACK);
        jbcreate.setForeground(Color.white);
        jbcreate.addActionListener(this);


        jbback=new JButton("Back");
        jbback.setBounds(180,220,100,20);
        jbback.setBackground(Color.BLACK);
        jbback.setForeground(Color.white);
        jbback.addActionListener(this);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("signupImage.png"));
        Image l2=i1.getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(l2);
        JLabel image=new JLabel(i2);
        image.setBounds(200,0,400,400);

// event
        jtmeter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                String Name = "";
                try{
                    Con db=new Con();
                    PreparedStatement ps=db.connection.prepareStatement("select * from login where Meterno=?");
                    ps.setString(1,jtmeter.getText().toString());

                    ResultSet rs=ps.executeQuery();
                    while (rs.next()) {
                        Name = rs.getString("Uname");

                    }
                    jtname.setText(Name);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                


            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });



        c.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem().equals("User")){
                    jbmeter.setVisible(true);
                    jtmeter.setVisible(true);




                } else if (e.getItem().equals("Admin")) {
                    jbmeter.setVisible(false);
                    jtmeter.setVisible(false);


                }

            }
        });

        add(jp);
        jp.add(jc);
        jp.add(jEmail);
        jp.add(jtEmail);
        jp.add(jname);
        jp.add(jtname);
        jp.add(jpassword);
        jp.add(jtPassword);
        jp.add(c);
        jp.add(jaddress);
        jp.add(jtaddress);
        jp.add(jbcreate);
        jp.add(jbback);
        jp.add(jbmeter);
        jp.add(jtmeter);
        jp.add(image);



    }
    public void actionPerformed(ActionEvent ae){


        if (ae.getSource()==jbcreate){
            String atype=c.getSelectedItem();
            String  metermumber=jtmeter.getText();
            String Email=jtEmail.getText();
            String Name=jtname.getText();
            String Address=jtaddress.getText();
            String Password=jtPassword.getText();
            if (atype.equals("Admin")) {
                try {
                    PreparedStatement ps = dbcon.connection.prepareStatement("Insert into login values(?,?,?,?,?)");
                    ps.setString(1, atype);
                    ps.setString(2, Email);
                    ps.setString(3, Name);
                    ps.setString(4, Password);
                    ps.setString(5, Address);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "account create sucessfully please Login");
                        setVisible(false);
                        new Login();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something worng please fill again");
                    }
                    ps.close();
                    dbcon.connection.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                PreparedStatement ps1=dbcon.connection.prepareStatement("update login set arole=?, Uname=?,Email=?,password=?,Address=? where Meterno=? ");

                ps1.setString(1,c.getSelectedItem().toString());
                ps1.setString(2,jtname.getText());
                ps1.setString(3,jtEmail.getText());
                ps1.setString(4,jtPassword.getText());
                ps1.setString(5,jtaddress.getText().toString());
                ps1.setString(6,jtmeter.getText());

                int esa=ps1.executeUpdate();
                if (esa>0){
                    JOptionPane.showMessageDialog(null,"sucessfully update");
                    new Login();

                }
                else{
                    JOptionPane.showMessageDialog(null,"not sucess");

                }
                    ps1.close();
                dbcon.connection.close();

            }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        else if(ae.getSource()==jbback){
            setVisible(true);
            new Login();
        }

    }
    public static void main(String[] args) {
        new Singup();
    }
}
