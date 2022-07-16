package mainpackage;

import  javax.swing.*;
import java.awt.*;
import java.net.URL;

public class InitialInterface extends JFrame{
    public InitialInterface(){
        setBounds(100,100,270,400);
        setTitle("初始界面");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Container c = getContentPane();

        JButton jb1 = new JButton("新的游戏"),jb2 = new JButton("加载存档");
        jb1.setBounds(5,10,120,80);
        jb2.setBounds(125,10,120,80);
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
