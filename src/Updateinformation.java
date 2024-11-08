import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Updateinformation extends JFrame
{
    JLabel name ,meter,adress,phonenumber,email,jtname,jtmeter;
    JTextField jtaddress,jtphonenumber,jtemail;
    JButton jbupdate;
    Updateinformation(String meternumber){

        setVisible(true);
        setBounds(100,100,600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        name=new JLabel("Name");
        name.setBounds(10,10,100,20);
        add(name);

        jtname=new JLabel("");
        jtname.setBounds(110,10,150,20);
        add(jtname);

        meter=new JLabel("Meter number:");
        meter.setBounds(10,40,100,20);
        add(meter);

        jtmeter=new JLabel("");
        jtmeter.setBounds(110,40,150,20);
        add(jtmeter);

        email=new JLabel("Email");
        email.setBounds(10,70,100,20);
        add(email);

        jtemail=new JTextField();
        jtemail.setBounds(110,70,150,20);
        add(jtemail);

        phonenumber=new JLabel("Phone Number");
        phonenumber.setBounds(10,100,100,20);
        add(phonenumber);

        jtphonenumber=new JTextField();
        jtphonenumber.setBounds(110,100,150,20);
        add(jtphonenumber);


        adress=new JLabel("Address");
        adress.setBounds(10,130,100,20);
        add(adress);

        jtaddress=new JTextField();
        jtaddress.setBounds(110,130,150,20);
        add(jtaddress);




        try{
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("select * from Customer where  meternumber=?");
            ps.setString(1,meternumber);
            ResultSet res=ps.executeQuery();
            while(res.next()){
                jtname.setText(res.getString("name"));
                jtmeter.setText(res.getString("meternumber"));
            }


            ps.close();
            db.connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


        jbupdate=new JButton("update");
        jbupdate.setBounds(60,180,100,30);
        jbupdate.setForeground(Color.white);
        jbupdate.setBackground(Color.BLACK);
        add(jbupdate);
        jbupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Con db=new Con();

                    PreparedStatement ps1 = db.connection.prepareStatement("update Customer set address=?,email=?,phone=? where name=? and meternumber=?");
                    ps1.setString(1,jtaddress.getText().toString());
                    ps1.setString(2,jtemail.getText().toString());
                    ps1.setString(3,jtphonenumber.getText().toString());
                    ps1.setString(4,jtname.getText().toString());
                    ps1.setString(5,jtmeter.getText().toString());

                int res= ps1.executeUpdate();
                if (res>0){
                    JOptionPane.showMessageDialog(null,"sucess");
                    setVisible(false);

                }
                else {
                    JOptionPane.showMessageDialog(null,"unsucess");
                }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });






    }
    public static void main(String[] args) {
    new Updateinformation("");

    }
}
