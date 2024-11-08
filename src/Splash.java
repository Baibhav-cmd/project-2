import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Splash extends JFrame implements Runnable {
    Thread T;
    Splash(){
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("elect.jpg"));
        Image ii=i1.getImage().getScaledInstance(1000,1000,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(ii);
        JLabel image=new JLabel(i2);
        image.setBounds(0,0,1000,1000);
        add(image);

        for(int i=0;i<1000;i+=2){
            setSize(i,i);
            setLocationRelativeTo(null);

            try{
                Thread.sleep(10);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
         T=new Thread(this);
        T.start();


    }

    @Override
    public void run() {
        try{
            Thread.sleep(4000);
            setVisible(false);
            new Login();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Splash();
    }
}
