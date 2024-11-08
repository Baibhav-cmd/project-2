import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class MeterInfor extends JFrame implements ActionListener {
    JLabel Heading,jlmeternumber,meterlocation,metertype,phasecode,billtype,days,note,jtdays,jtnote,jtMeternumber;
    Choice clocation,ctype,cphase,cbilltype;
    JButton jbsubmit;
    String meternumber;
    MeterInfor(String meternumber) {
        this.meternumber=meternumber;
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

        Heading=new JLabel("Meter Information");
        Heading.setBounds(100,10,200,30);
        Heading.setForeground(Color.BLACK);
        Heading.setFont(new Font("Arial",Font.BOLD,20));
        jPanel.add(Heading);

        jlmeternumber=new JLabel("Meter Number");
        jlmeternumber.setBounds(10,40,120,20);
        jPanel.add(jlmeternumber);

        jtMeternumber=new JLabel(meternumber);
        jtMeternumber.setBounds(110,40,100,20);
        jPanel.add(jtMeternumber);

        meterlocation =new JLabel("Meter Location");
        meterlocation.setBounds(10,70,100,20);
        jPanel.add(meterlocation);

        clocation=new Choice();
        clocation.setBounds(110,70,100,20);
        clocation.add("Outside");
        clocation.add("Inside");
        jPanel.add(clocation);

        metertype=new JLabel("meter Type");
        metertype.setBounds(10,100,100,20);
        jPanel.add(metertype);
        ctype=new Choice();
        ctype.add("Electric");
        ctype.add("Solar");
        ctype.add( "Smart");
        ctype.setBounds(110,100,100,20);
        jPanel.add(ctype);

        phasecode= new JLabel("phase code");
        phasecode.setBounds(10,130,100,20);
        jPanel.add(phasecode);

        cphase=new Choice();
        cphase.add("011");
        cphase.add("022");
        cphase.add( "033");
        cphase.add("044");
        cphase.add("055");
        cphase.add( "066");
        cphase.add("077");
        cphase.add("088");
        cphase.add( "099");
        cphase.setBounds(110,130,100,20);
        jPanel.add(cphase);

        billtype=new JLabel("Bill type");
        billtype.setBounds(10,160,100,20);
        jPanel.add(billtype);

        cbilltype=new Choice();
        cbilltype.add("Normal");
        cbilltype.add("Industial");
        cbilltype.setBounds(110,160,100,20);
        jPanel.add(cbilltype);

        days=new JLabel("Days");
        days.setBounds(10,190,100,20);
        jPanel.add(days);

        jtdays=new JLabel("30 days");
        jtdays.setBounds(110,190,100,20);
        jPanel.add(jtdays);

        note=new JLabel("Note:");
        note.setBounds(10,220,100,20);
        jPanel.add(note);

        jtnote=new JLabel("By Default bill is calculate for 30 days");
        jtnote.setBounds(110,220,350,20);
        jPanel.add(jtnote);

        jbsubmit=new JButton("Submit");
        jbsubmit.setBackground(Color.blue);
        jbsubmit.setForeground(Color.white);
        jbsubmit.addActionListener(this);
        jbsubmit.setBounds(70,270,90,20);
        jPanel.add(jbsubmit);



        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hicon1.jpg"));
        Image img1=i1.getImage().getScaledInstance(150,380,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(img1);
        JLabel image=new JLabel(i2);
        image.setBounds(20,30,150,380);
        add(image);

    }

@Override
    public void actionPerformed(ActionEvent ae) {
    String meter = meternumber;
    String location = clocation.getSelectedItem();
    String Metertype = ctype.getSelectedItem();
    String Phasecode = cphase.getSelectedItem();
    String BillType = cbilltype.getSelectedItem();
    String days = jtdays.getText();
    if (ae.getSource() == jbsubmit) {
    }
    try {
        Con db = new Con();
        PreparedStatement ps = db.connection.prepareStatement("insert into meter_info Values(?,?,?,?,?,?)");
        ps.setString(1, meter);
        ps.setString(2,location);
        ps.setString(3,Metertype);
        ps.setString(4,Phasecode);
        ps.setString(5,BillType);
        ps.setString(6,days);

        int res=ps.executeUpdate();
        if (res>0){
            JOptionPane.showMessageDialog(null,"sucess");
            setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null,"unsucess");
        }
        ps.close();
        db.connection.close();

    } catch (Exception e){
        e.printStackTrace();
    }
}
    public static void main(String[] args) {
        new MeterInfor("");
    }
}