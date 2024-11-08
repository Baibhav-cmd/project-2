import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Jmen extends JFrame {
    Jmen(){
        setVisible(true);
        setSize(400,300);
        JMenuBar b=new JMenuBar();
        setJMenuBar(b);
        JMenu file=new JMenu("File");
        b.add(file);
        JMenuItem New=new JMenuItem("New");
        file.add(New);
        New.setMnemonic(KeyEvent.VK_N);
        New.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        JMenuItem save =new JMenuItem("Save");
        file.add(save);
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK));
        JMenuItem copy=new JMenuItem("Copy", KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK));
        file.add(copy);



        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
        new Jmen();
    }
}
