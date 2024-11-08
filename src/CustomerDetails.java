import javax.swing.*;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDetails  extends JFrame {
    JTable table;
    JScrollPane scrollPane;
    JButton jbprint;
    CustomerDetails(){
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
setLayout(null);
        table=new JTable();
        try{
            Con db=new Con();
            PreparedStatement ps=db.connection.prepareStatement("select * from Customer");
            ResultSet rs=ps.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));

            ps.close();
            db.connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,0,700,600);
        add(scrollPane);


        jbprint=new JButton("print");
        jbprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jbprint.setBounds(300,600,100,20);

        add(jbprint);




    }
    public static void main(String[] args) {
     new CustomerDetails();
    }
}
