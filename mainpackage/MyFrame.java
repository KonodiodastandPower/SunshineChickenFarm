package mainpackage;

import com.sun.org.apache.xpath.internal.objects.XObject;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class MyFrame extends JFrame{
    Random random = new Random();
    public  MyFrame(Record record){
        setBounds(100,100,300,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("阳光养鸡场");
        setLayout(null);

        Container c = getContentPane();

        Record re = record;

        MyTextArea jta = new MyTextArea();
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setBounds(40,20,200,200);
        c.add(jsp);

        JTextField jtf = new JTextField();
        JScrollPane jsp2 = new JScrollPane(jtf);
        jsp2.setBounds(40,250,200,40);
        c.add(jsp2);

        jtf.addKeyListener(new KeyListener() {//键盘监听
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(c == KeyEvent.VK_ENTER){
                    String command = jtf.getText();
                    jtf.setText("");
                    List<String> list = section(command);
                    docommand(list,jta,re);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        printnow(jta,re);
        jta.append("提示：输入help查看帮助");


        setVisible(true);
    }
    public void sleep(double second){//暂停运行，单位:s
        try {Thread.sleep((int)(second * 1000));}catch (Exception e){e.printStackTrace();}
    }
    public void printnow(MyTextArea jta,Record record){//输出现状
        jta.oldappend("今天是第"+record.day+"天");
        jta.append("你现在有"+record.money+"块钱");
        jta.append("你现在有"+record.CxkNumber+"只cxk");
        jta.append("你现在有"+record.egg+"个蛋");
    }
    public java.util.List<String> section(String ns){
        java.util.List<String> list = new ArrayList<>();
        Integer First_Slice_position = 0,Last_Slice_position = 0;
        for(;Last_Slice_position != ns.length();) {
            Last_Slice_position = ns.indexOf(" ",First_Slice_position);
            if(Last_Slice_position == -1) {
                Last_Slice_position = ns.length();
            }
            list.add(ns.substring(First_Slice_position,Last_Slice_position));
            First_Slice_position = Last_Slice_position + 1;
        }
        return list;
    }
    public void docommand(List<String> list,MyTextArea my,Record re){
        String command = list.get(0);
        if(command.equals("buy")){
            try{
                my.append("--------------buy--------------");
                String string2 = list.get(1);
                int num = Integer.valueOf(string2);
                if(100*num <= re.money){
                    re.money = re.money - 100*num;
                    re.CxkNumber = re.CxkNumber + num;

                    my.append("你买了"+num+"只坤坤,现有"+re.CxkNumber+"只");
                    my.append("花费了"+num*100+"元");
                    my.append("你还剩下"+re.money+"元");
                }else{
                    my.append("你买不起");
                }
            }catch (Exception e){
                e.printStackTrace();
                my.append("参数错误");
            }
        }
        if(command.equals("next")){
            my.append("--------------next--------------");
            my.append("第"+re.day+"天结束");

            re.day = re.day + 1;

            my.append("第"+re.day+"天开始");

            int neweggs = re.CxkNumber + (int)(Math.random() * 4 * re.CxkNumber);

            re.egg = re.egg + neweggs;

            my.append("坤坤为你下了"+neweggs+"个蛋");
            my.append("你现在有"+re.egg+"个蛋");

            if(re.money < 0){
                my.append("你破产了，坤坤都跑了");
                re.CxkNumber = 0;
            }
        }
        if(command.equals("help")){
            my.append("--------------帮助--------------");
            my.append("buy [数量]  购买一定量的坤坤，每只100块");
            my.append("sold [数量]  卖出一定量的蛋，每个10块左右");
            my.append("next 进入下一天");
            my.append("show  查看所有信息");
            my.append("help  查看帮助");
        }
        if(command.equals("show")){
            my.append("--------------信息--------------");
            my.oldappend("\n");
            printnow(my,re);
        }
        if(command.equals("sold")){
            try{
                my.append("--------------sold--------------");
                String string3 = list.get(1);
                int num = Integer.valueOf(string3);
                if(num > re.egg){
                    throw new CyxMotherHasDeadException();
                }

                re.egg = re.egg - num;
                int newmoney = 5*num + (int)(Math.random()*10*num);

                re.money = re.money + newmoney;

                my.append("你卖掉了"+num+"个蛋，还剩"+re.egg+"个");
                my.append("你赚了"+newmoney+"块钱");
                my.append("你总共有"+re.money+"块钱");
            }catch (Exception e){
                e.printStackTrace();
                my.append("参数错误");
            }
        }
        if(command.equals("save")){
            try{
                File file = new File("C:\\SunshineChickenFarm.obj");
                if(!file.exists()){
                    file.createNewFile();
                }

                    OutputStream out = new FileOutputStream(file);
                    ObjectOutputStream objout = new ObjectOutputStream(out);
                    objout.writeObject(re);
                    objout.close();

                    my.append("--------------保存成功--------------");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }
}
