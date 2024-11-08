import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;
import net.proteanit.sql.DbUtils;

public class Depositdetails extends JFrame implements ActionListener {
    Choice c;
    JButton jbSearch,jbprint;
    JTable table;
    JScrollPane scrollPane;
    Depositdetails() {
        super("Deposit Dteails");
        setLayout(null);
        setBounds(100, 100, 700, 500);
        setVisible(true);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel merternumber = new JLabel("Search by meter number:");
        merternumber.setBounds(10, 10, 150, 20);
        add(merternumber);

        c = new Choice();
        c.setBounds(165, 10, 120, 20);
        add(c);
        // putting the value in database
        c.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {

            }
        });
        try {
            Con db = new Con();
            PreparedStatement ps = db.connection.prepareStatement("select * from bill");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c.add(rs.getString("meter_no"));
            }
            ps.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        jbSearch = new JButton("Search");
        jbSearch.setBounds(300, 10, 100, 20);
        jbSearch.setForeground(Color.white);
        jbSearch.setBackground(Color.BLACK);
        jbSearch.addActionListener(this);
        add(jbSearch);

       jbprint=new JButton("Print");
       jbprint.setBounds(410,10,100,20);
       jbprint.setBackground(Color.black);
       jbprint.setForeground(Color.white);
       jbprint.addActionListener(this);
       add(jbprint);



        table = new JTable();
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,100,700,350);
        add(scrollPane);

    }
@Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == jbSearch) {
        try {
            Con db = new Con();
            PreparedStatement ps = db.connection.prepareStatement("select * from bill where meter_no=?");
            ps.setString(1,c.getSelectedItem().toString());
            ResultSet rs = ps.executeQuery();


                table.setModel(DbUtils.resultSetToTableModel(rs));

            ps.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    else{
        try {
            table.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }

}

    public static void main(String[] args) {
        new Depositdetails();
    }
}
