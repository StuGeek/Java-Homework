package GUI;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class SimpleBrowser {
    public static void main(String[] args) throws Exception {

        // html鏄剧ず缁勪欢
        final JEditorPane jep = new JEditorPane();
        jep.setEditable(false);
        // 璁剧疆涓婚〉
        jep.setContentType("text/html;charset=utf-8");
        try {
            jep.setPage("http://inpluslab.com/java2020");
        } catch (IOException e) {
            jep.setText("<html>Error! Could not load page</html>");
        }
        // 甯︽粦鍔ㄦ潯鐨勭粍浠� 鐢ㄤ簬瀛樻斁鏄剧ずhtml鐨刯ep缁勪欢
        JScrollPane scrollpane = new JScrollPane(jep);

        // 杈撳叆妗� 杈撳叆URL
        final JTextField jtf = new JTextField(40);
        jtf.setText("http://inpluslab.com/java2020");

        // 鎸夐挳
        final JButton goBtn = new JButton("鐐规垜璁块棶缃戦〉");

        // 涓婃柟鑿滃崟鐩掑瓙
        JPanel menuBox = new JPanel();
        menuBox.add(jtf);
        menuBox.add(goBtn);

        // 娣诲姞瓒呴摼鎺ョ偣鍑讳簨浠跺洖璋冨嚱鏁� 骞跺皢JEditorPane鐨勯〉闈㈡敼涓鸿秴閾炬帴鐨勯〉闈�
        jep.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
                if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        jep.setPage(event.getURL());
                    } catch (IOException e) {
                        jep.setText("<html>Error! Could not load page</html>");
                    }
                }
            }
        });

        // 缁戝畾璁块棶鎸夐挳鐐瑰嚮浜嬩欢 浠嶫TextField杈撳叆妗嗚幏鍙朥RL骞朵笖璁块棶
        goBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jep.setPage(jtf.getText());
                } catch (IOException e1) {
                    jep.setText("<html>Error! Could not load page</html>");
                }
            }
        });

        // 缁戝畾杈撳叆妗嗗洖杞︽寜閿簨浠�
        jtf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(event.getKeyChar()==KeyEvent.VK_ENTER) {
                    goBtn.doClick();	// 鎸変笅鍥炶溅绛変簬鐐瑰嚮鎸夐挳
                }
            }
        });

        JFrame jf = new JFrame("SimpleBrowser");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setSize(1200, 700);
        jf.add(menuBox, BorderLayout.NORTH);		// 蹇呴』璁剧疆鏂逛綅涓篘orth鎵嶈兘鍦ㄤ笂鏂规樉绀�
        jf.add(scrollpane, BorderLayout.CENTER);	// 璁剧疆璁块棶涓篶enter
        jf.setVisible(true);
    }
}