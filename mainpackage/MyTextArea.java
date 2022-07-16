package mainpackage;

import javax.swing.*;

public class MyTextArea extends JTextArea {
    @Override
    public void append(String str) {
        super.append("\n"+str);
    }
    public void oldappend(String str){
        super.append(str);
    }

}
