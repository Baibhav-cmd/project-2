import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class BillingDetails extends JFrame {
    JTable table;
    JScrollPane jScrollPane;
    JLabel Heading;
    BillingDetails(String meter){
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.white);
        setBounds(200,200,600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Heading=new JLabel("Billing Details");
        Heading.setFont(new Font("arail",Font.BOLD,30));
        Heading.setBounds(200,0,200,40);
       Heading.setForeground(Color.cyan);
        add(Heading);
        table=new JTable();

        try{
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("select * from bill where meter_no=?");
            ps.setString(1,meter);

            ResultSet res=ps.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(res));

            ps.close();
            db.connection.close();;

        }catch(Exception e){
            e.printStackTrace();
        }


        jScrollPane=new JScrollPane(table);
        jScrollPane.setBounds(0,60,600,400);
        add(jScrollPane);

    }

    public static void main(String[] args) {
        new BillingDetails("");
    }
}
