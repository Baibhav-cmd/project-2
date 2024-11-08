import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    Random random;
    JLabel Heading,Customername,meternumber,Address,Email,Phonenumber,State,jtMeternumber;
    JTextField jtcustomername,jtAddress,jtEmail,jtphone,jtstate;
    JButton jbenxt,jbcancel;
    NewCustomer(){
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);




        JPanel jPanel=new JPanel();
        jPanel.setBackground(Color.cyan);
        jPanel.setBounds(300,0,600,500);
        jPanel.setLayout(null);
        add(jPanel);

        Heading=new JLabel("New Customer");
        Heading.setBounds(100,10,200,30);
        Heading.setForeground(Color.BLACK);
        Heading.setFont(new Font("Arial",Font.BOLD,20));
        jPanel.add(Heading);

        Customername=new JLabel("Customer Name");
        Customername.setBounds(10,40,120,20);
        jPanel.add(Customername);

        jtcustomername=new JTextField();
        jtcustomername.setBounds(110,40,150,20);
        jPanel.add(jtcustomername);

        meternumber =new JLabel("Meter Number");
        meternumber.setBounds(10,70,100,20);
        jPanel.add(meternumber);

        random=new Random();
   String merter= String.valueOf(random.nextInt(1000000));
        jtMeternumber=new JLabel();
        jtMeternumber.setBounds(110,70,100,20);
        jtMeternumber.setText(merter);
        jPanel.add(jtMeternumber);

        Address=new JLabel("Address:");
        Address.setBounds(10,130,100,20);
        jPanel.add(Address);

        jtAddress=new JTextField();
        jtAddress.setBounds(110,130,150,20);
        jPanel.add(jtAddress);

        State= new JLabel("State");
        State.setBounds(10,160,100,20);
        jPanel.add(State);

        jtstate=new JTextField();
        jtstate.setBounds(110,160,150,20);
        jPanel.add(jtstate);

        Email=new JLabel("Email");
        Email.setBounds(10,190,100,20);
        jPanel.add(Email);

        jtEmail=new JTextField();
        jtEmail.setBounds(110,190,150,20);
        jPanel.add(jtEmail);

        Phonenumber=new JLabel("Phone-Number");
        Phonenumber.setBounds(10,220,100,20);
        jPanel.add(Phonenumber);

        jtphone=new JTextField();
        jtphone.setBounds(110,220,150,20);
        jPanel.add(jtphone);

        jbenxt=new JButton("Next");
        jbenxt.setBackground(Color.black);
        jbenxt.setForeground(Color.white);
        jbenxt.addActionListener(this);
        jbenxt.setBounds(30,270,70,20);
        jPanel.add(jbenxt);

        jbcancel=new JButton("Cancel");
        jbcancel.setBackground(Color.BLUE);
        jbcancel.setForeground(Color.white);
        jbcancel.setBounds(150,270,90,20);
        jbcancel.addActionListener(this);
        jPanel.add(jbcancel);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hicon1.jpg"));
        Image img1=i1.getImage().getScaledInstance(150,380,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(img1);
        JLabel image=new JLabel(i2);
        image.setBounds(20,30,150,380);
        add(image);

    }
    public void actionPerformed(ActionEvent ae){
        String Name=jtcustomername.getText();
        String meternumber=jtMeternumber.getText().toString();
        String Address=jtAddress.getText();
        String State=jtstate.getText();
        String Email=jtEmail.getText();
        String Phone=jtphone.getText();

        if(ae.getSource()==jbenxt){
            try{
                Con db =new Con();
               PreparedStatement ps= db.connection.prepareStatement("insert into Customer values(?,?,?,?,?,?)");
                ps.setString(1,Name);
                ps.setString(2,meternumber);
                ps.setString(3,Address);
                ps.setString(4,State);
                ps.setString(5,Email);
                ps.setString(6,Phone);
            int res =ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null,"Sucessfull");

            }
            else {
                JOptionPane.showMessageDialog(null,"didnot insert");
            }
            ps.close();
            db.connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try{
                Con db=new Con();
                PreparedStatement ps=db.connection.prepareStatement("insert into login (Uname,meterno) values (?,?)");
                ps.setString(1,Name);
                ps.setString(2,meternumber);
                int rs=ps.executeUpdate();
                if(rs>0){
                    JOptionPane.showMessageDialog(null,"sucessfully insert");
                }
                else {
                JOptionPane.showMessageDialog(null,"unsucessfull");
                }
                ps.close();
                db.connection.close();
                }catch(Exception e){
                e.printStackTrace();
            }
            new MeterInfor(meternumber);

        }
        else {
            setVisible(false);
            new Project("","");
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }


}
