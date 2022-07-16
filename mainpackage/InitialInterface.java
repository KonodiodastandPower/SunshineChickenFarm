package mainpackage;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;

public class InitialInterface extends JFrame{
    public InitialInterface(){
        setBounds(100,100,270,400);
        setTitle("阳光养鸡场");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Container c = getContentPane();

        JButton jb1 = new JButton("新的游戏"),jb2 = new JButton("加载存档");
        jb1.setBounds(5,10,120,80);
        jb2.setBounds(125,10,120,80);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyFrame(new Record(1000,1,0,1));
                setVisible(false);
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("C:\\SunshineChickenFarm.obj");
                try {
                    InputStream in = new FileInputStream(file);
                    ObjectInputStream objin = new ObjectInputStream(in);
                    Object obj = objin.readObject();
                    Record record = (Record)obj;
                    new MyFrame(record);
                    setVisible(false);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        c.add(jb1);
        c.add(jb2);

        URL url = InitialInterface.class.getResource("cxk.jpg");
        Icon icon = new ImageIcon(url);
        JLabel jl = new JLabel(icon);
        jl.setBounds(25,110,200,350);
        c.add(jl);

        setVisible(true);
    }
}
