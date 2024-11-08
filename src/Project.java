import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Project extends JFrame  implements ActionListener {
    JMenuItem Calculatebill;
    String User;
    String meter;
    Project(String User,String meter){
        this.meter=meter;
        this.User=User;
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("elect1.jpg"));
        Image l1=i1.getImage().getScaledInstance(1500,1000, Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(l1);
        JLabel image=new JLabel(i2);
        image.setBounds(0,0,1500,1000);
        add(image);

        JMenuBar jmb=new JMenuBar();
        setJMenuBar(jmb);

        JMenu master=new JMenu("Master");


        JMenuItem newCustomer=new JMenuItem("new Customer");
        ImageIcon iv1=new ImageIcon(ClassLoader.getSystemResource("icon1.png"));
        Image ic1=iv1.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        newCustomer.setIcon(new ImageIcon(ic1));
        newCustomer.setMnemonic(KeyEvent.VK_D);
        newCustomer.addActionListener(this);
        newCustomer.setAccelerator(KeyStroke.getKeyStroke('D', InputEvent.CTRL_DOWN_MASK));
        master.add(newCustomer);

        JMenuItem Customerdetails=new JMenuItem("Customer Details");
        ImageIcon icon2=new ImageIcon(ClassLoader.getSystemResource("icon2.png"));
        Image image2=icon2.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Customerdetails.setIcon(new ImageIcon(image2));
        Customerdetails.setMnemonic(KeyEvent.VK_M);
        Customerdetails.setAccelerator(KeyStroke.getKeyStroke('M',InputEvent.CTRL_DOWN_MASK));
        Customerdetails.addActionListener(this);
        master.add(Customerdetails);

        JMenuItem depositDetails=new JMenuItem("Deposit Details");
        ImageIcon icon3=new ImageIcon(ClassLoader.getSystemResource("icon3.png"));
        Image image3=icon3.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        depositDetails.setIcon(new ImageIcon(image3));
        depositDetails.setMnemonic(KeyEvent.VK_N);
        depositDetails.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
       depositDetails.addActionListener(this);
        master.add(depositDetails);

         Calculatebill=new JMenuItem("Calculate Bill");
        ImageIcon icon4=new ImageIcon(ClassLoader.getSystemResource("icon5.png"));
        Image image4=icon4.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Calculatebill.setIcon(new ImageIcon(image4));
        Calculatebill.setMnemonic(KeyEvent.VK_H);
        Calculatebill.addActionListener(this);
        Calculatebill.setAccelerator(KeyStroke.getKeyStroke('H',InputEvent.CTRL_MASK));
        master.add(Calculatebill);

        JMenu infromation=new JMenu("Infromation");


        JMenuItem updateInformation =new JMenuItem("Update Information");
        ImageIcon icon5=new ImageIcon(ClassLoader.getSystemResource("icon4.png"));
        Image image5=icon5.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        updateInformation.setIcon(new ImageIcon(image5));
        updateInformation.setMnemonic(KeyEvent.VK_U);
        updateInformation.setAccelerator(KeyStroke.getKeyStroke('U',InputEvent.CTRL_MASK));
        updateInformation.addActionListener(this);
        infromation.add(updateInformation);

        JMenuItem viewifnormation=new JMenuItem("View Information");
        ImageIcon icon6=new ImageIcon(ClassLoader.getSystemResource("icon6.png"));
        Image image6=icon6.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        viewifnormation.setIcon(new ImageIcon(image6));
        viewifnormation.setMnemonic(KeyEvent.VK_I);
        viewifnormation.setAccelerator(KeyStroke.getKeyStroke('I',InputEvent.CTRL_MASK));
        viewifnormation.addActionListener(this);
        infromation.add(viewifnormation);

        JMenu user=new JMenu("User");


        JMenuItem paybill=new JMenuItem("PayBill");
        ImageIcon icon7=new ImageIcon(ClassLoader.getSystemResource("icon4.png"));
        Image image7=icon7.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        paybill.setIcon(new ImageIcon(image7));
        paybill.setMnemonic(KeyEvent.VK_P);
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke('P',InputEvent.CTRL_MASK));
        user.add(paybill);

        JMenuItem billdetails=new JMenuItem("Bill Details");
        ImageIcon icon8=new ImageIcon(ClassLoader.getSystemResource("icon6.png"));
        Image image8=icon8.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        billdetails.setIcon(new ImageIcon(image8));
        billdetails.setMnemonic(KeyEvent.VK_Y);
        billdetails.setAccelerator(KeyStroke.getKeyStroke('Y',InputEvent.CTRL_MASK));
        billdetails.addActionListener(this);
        user.add(billdetails);

        JMenu report=new JMenu("Report");


        JMenuItem generatebill=new JMenuItem("Generate Bill");
        ImageIcon icon9=new ImageIcon(ClassLoader.getSystemResource("icon7.png"));
        Image image9=icon9.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        generatebill.setIcon(new ImageIcon(image9));
        generatebill.setMnemonic(KeyEvent.VK_Q);
        generatebill.setAccelerator(KeyStroke.getKeyStroke('Q',InputEvent.CTRL_MASK));
        generatebill.addActionListener(this);
        report.add(generatebill);

        JMenu utility=new JMenu("Utility");


        JMenuItem notepad=new JMenuItem("Notepad");
        ImageIcon icon10=new ImageIcon(ClassLoader.getSystemResource("icon12.png"));
        Image image10=icon10.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic(KeyEvent.VK_E);
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke('E',InputEvent.CTRL_MASK));
        utility.add(notepad);

        JMenuItem Calculator=new JMenuItem("Calculator");
        ImageIcon icon11=new ImageIcon(ClassLoader.getSystemResource("icon9.png"));
        Image image11=icon11.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Calculator.setIcon(new ImageIcon(image10));
        Calculator.setMnemonic(KeyEvent.VK_T);
        Calculator.setAccelerator(KeyStroke.getKeyStroke('T',InputEvent.CTRL_MASK));
        Calculator.addActionListener(this);
        utility.add(Calculator);

        JMenu mexit=new JMenu("Exit");


        JMenuItem logout=new JMenuItem("logout");
        ImageIcon icon12=new ImageIcon(ClassLoader.getSystemResource("icon11.png"));
        Image image12=icon12.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        logout.setIcon(new ImageIcon(image12));
        logout.setMnemonic(KeyEvent.VK_W);
        logout.setAccelerator(KeyStroke.getKeyStroke('W',InputEvent.CTRL_MASK));
        logout.addActionListener(this);
        mexit.add(logout);
if(User.equals("Admin")) {
    jmb.add(master);
}
else {
    jmb.add(report);
    jmb.add(user);
    jmb.add(infromation);
}
        jmb.add(utility);
        jmb.add(mexit);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("new Customer")){
            new NewCustomer();
        } else if (ae.getSource()==Calculatebill) {
            new CalculateBill();

        } else if (ae.getActionCommand().equals("Deposit Details")){
            new Depositdetails();

        } else if ((ae.getActionCommand().equals("Customer Details"))) {
            new CustomerDetails();

        }else if(ae.getActionCommand().equals("View Information")){
            new ViewInformation(meter);
            setVisible(true);

        } else if (ae.getActionCommand().equals("Update Information")) {
            new Updateinformation(meter);
            setVisible(true);

        }

        else if(ae.getActionCommand().equals("Bill Details")){
            new BillingDetails(meter);
        }
        else if (ae.getActionCommand().equals("PayBill")){
            new Paybill(meter);
        }

        else if(ae.getActionCommand().equals("Generate Bill")){
            new Generatebill(meter);
        }
        else if(ae.getActionCommand().equals("Notepad")){
            try {
                Runtime.getRuntime().exec("Notepad.exe");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(ae.getActionCommand().equals("Calculator")){
            try {
                Runtime.getRuntime().exec("Calc.exe");

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        new Project("","");
    }
}
