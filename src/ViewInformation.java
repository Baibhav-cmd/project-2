import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewInformation extends JFrame {
JLabel heading,name,meternumber,address,state,phoneNumber,email;
JButton jcancel;
    ViewInformation(String meter){
    setBounds(100,100,800,500);
    setVisible(true);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.white);

    heading=new JLabel("View Customer Details");
    heading.setBounds(250,0,200,30);
    heading.setFont(new Font("Arial",Font.PLAIN,20));
    add(heading);

    ImageIcon ic12=new ImageIcon(ClassLoader.getSystemResource("viewcustomer.jpg"));
    Image image=ic12.getImage().getScaledInstance(300,400,Image.SCALE_SMOOTH);
    JLabel img=new JLabel(new ImageIcon(image));
        img.setBounds(400,100,300,400);
        add(img);
    name =new JLabel("Name:");
    name.setBounds(20,40,100,20);
    add(name);

    JLabel jtname=new JLabel("");
    jtname.setBounds(130,40,150,20);
    add(jtname);

    meternumber=new JLabel("meterNumber");
    meternumber.setBounds(20,70,100,20);
    add(meternumber);

    JLabel jtmeternumber=new JLabel("");
    jtmeternumber.setBounds(130,70,150,20);
    add(jtmeternumber);

    address=new JLabel("Adress:");
    address.setBounds(20,100,100,20);
    add(address);

    JLabel jtaddress=new JLabel("");
    jtaddress.setBounds(130,100,150,20);
    add(jtaddress);

    email=new JLabel("Email");
    email.setBounds(20,130,100,20);
    add(email);

    JLabel jtemail=new JLabel("");
    jtemail.setBounds(130,130,150,20);
    add(jtemail);

    phoneNumber=new JLabel("Phone Number");
    phoneNumber.setBounds(20,160,100,20);
    add(phoneNumber);

    JLabel jtphonenumber=new JLabel("");
    jtphonenumber.setBounds(230,160,150,30);
    add(jtphonenumber);

    jcancel=new JButton("Cancel");
    jcancel.setBounds(80,200,100,20);
    jcancel.setBackground(Color.black);
    jcancel.setForeground(Color.white);
    jcancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Project("",meter);

        }
    });
    add(jcancel);


    try{
        Con db=new Con();
        PreparedStatement ps=db.connection.prepareStatement("select * from Customer where meternumber=?");
        ps.setString(1,meter);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            jtname.setText(rs.getString("name").toString());
            jtemail.setText(rs.getString("email").toString());
            jtmeternumber.setText(rs.getString("meternumber").toString());
            jtphonenumber.setText(rs.getString("phone").toString());
            jtaddress.setText(rs.getString("address").toString());
        }
        ps.close();
        db.connection.close();


        }
    catch (Exception e)
    {
        e.printStackTrace();
    }

}

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
