import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Generatebill  extends JFrame  implements ActionListener {
    JTextArea jTextArea;
    Choice cmonth;
    JScrollPane pane;
    JButton bill;
    JLabel meternumber;
    String meter;
    Generatebill(String meter){
        this.meter=meter;
        setSize(500,800);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel=new JPanel();
        JLabel heading=new JLabel("Generate bill");
        meternumber=new JLabel("Meter Number");
         cmonth=new Choice();
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("march");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("july");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("November");
        cmonth.add("December");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       jTextArea=new JTextArea(50,15);
       jTextArea.setText("\n\n\t -----------------------------click on the button--------------------------- ");
       jTextArea.setFont(new Font("Arial",Font.BOLD,20));

       pane=new JScrollPane(jTextArea);

       bill= new JButton("Generate bill");
       bill.addActionListener(this);


panel.add(heading);
panel.add(meternumber);
panel.add(cmonth);
add(panel,"North");
add(pane,"Center");
add(bill,"South");

    }

    public static void main(String[] args) {

        new Generatebill("");
    }

    @Override
    public void actionPerformed(ActionEvent we) {
String meter="61878";
        jTextArea.setText("\t nepal Electricity Authorizw \n Generating bii month of\n"+cmonth.getSelectedItem().toString());
        try{
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("select * from Customer where meternumber='"+meter+"'");
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                jTextArea.append("\n Name:" + rs.getString("name"));
                jTextArea.append("\n  Meeter Number:" + rs.getString("meternumber"));
                jTextArea.append("\n Adderss:" + rs.getString("address"));
                jTextArea.append("\n State:" + rs.getString("state"));
                jTextArea.append("\n Email:" + rs.getString("email"));
                jTextArea.append("\nPhonr Number:" + rs.getString("phone"));

                jTextArea.append("\n ===================================================");
            }

                PreparedStatement ps1=db.connection.prepareStatement("select * from meter_info where meter_no='"+meter+"'");
                ResultSet rs1=ps1.executeQuery();
                if(rs1.next()){
                    jTextArea.append("\n Name:"+rs.getString("meter_location "));
                    jTextArea.append("\n  meter_type :"+rs.getString("meter_type "));
                    jTextArea.append("\n passcode"+rs.getString("passcode"));
                    jTextArea.append("\n billtype:"+rs.getString("billtype"));
                    jTextArea.append("\n days:"+rs.getString("days"));


                }
            ps.close();
            db.connection.close();


        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
