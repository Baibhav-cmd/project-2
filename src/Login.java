import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login  extends JFrame {
    JLabel jluname, jlpassword,jlchoice;
    JTextField jtuname,jtpassword;
    Choice Role;
    JButton jblogin,jbcancel,jbSingup;
    Login(){
        super("Login");
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jluname=new JLabel("UserName:");
        jluname.setFont(new Font("arial",Font.BOLD,16));
        jluname.setBounds(400,50,150,20);
        add(jluname);

        jtuname=new JTextField();
        jtuname.setBounds(500,50,180,20);
        add(jtuname);

        jlpassword=new JLabel("Password:");
        jlpassword.setFont(new Font("arial",Font.BOLD,16));
        jlpassword.setBounds(400,100,150,20);
        add(jlpassword);

        jtpassword=new JTextField();
        jtpassword.setBounds(500,100,180,20);
        add(jtpassword);

        jlchoice=new JLabel("Log in as:");
        jlchoice.setFont(new Font("arial",Font.BOLD,14));
        jlchoice.setBounds(400,150,130,20);
        add(jlchoice);

        Role=new Choice();
        Role.add("User");
        Role.add("Admin");
        Role.setBounds(530,150,150,20);
        add(Role);


        ImageIcon l1=new ImageIcon(ClassLoader.getSystemResource("login.png"));
        Image i1=l1.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon l2= new ImageIcon(i1);
        jblogin=new JButton("LOGIN",l2);
        jblogin.setBounds(420,190,100,30);
        jblogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              String Uname=jtuname.getText();
              String Password=jtpassword.getText();
              String role=Role.getSelectedItem();
                Con dbcon=new Con();
                try {
                    PreparedStatement ps = dbcon.connection.prepareStatement("select * from login where Uname=? AND password=? AND arole=?");
                    ps.setString(1,Uname);
                    ps.setString(2,Password);
                    ps.setString(3,role);

                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        String meter=rs.getString("Meterno");
                        JOptionPane.showMessageDialog(null,"welcome"+Uname+"ot the dash board");
                        setVisible(false);
                        new Project(role,meter);
                     }
                    else {
                        JOptionPane.showMessageDialog(null,"wrong details");
                        jtuname.setText("");
                        jtpassword.setText("");
                    }
                    ps.close();
                    dbcon.connection.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        add(jblogin);

        ImageIcon e1=new ImageIcon(ClassLoader.getSystemResource("Cancel.jpg"));
        Image i2=e1.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon e2=new ImageIcon(i2);
        jbcancel=new JButton("Cancel",e2);
        jbcancel.setBounds(550,190,100,30);
        jbcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);


            }
        });
        add(jbcancel);

        ImageIcon s1=new ImageIcon(ClassLoader.getSystemResource("signup.png"));
        Image i3=s1.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon e3=new ImageIcon(i3);
        jbSingup=new JButton("Singup",e3);
        jbSingup.setBounds(470,230,100,30);
        jbSingup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Singup();
            }
        });
        add(jbSingup);


        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("second.jpg"));
        Image i5=i4.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
        JLabel bac=new JLabel(new ImageIcon(i5));
        bac.setBounds(0,0,300,300);
        add(bac);

    }

    public static void main(String[] args) {
        new Login();
    }
}
