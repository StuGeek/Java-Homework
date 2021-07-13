import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SimpleTextReader extends JFrame{
    JFrame jframe = this;
    final JTextArea textArea = new JTextArea();
    final JScrollPane scrollPane = new JScrollPane();
    final JMenuBar menuBar = new JMenuBar();
    final JMenu menu = new JMenu("文件");
    final JMenuItem open = new JMenuItem("打开");
    final JMenuItem close = new JMenuItem("关闭");
    final JMenuItem exit = new JMenuItem("退出");
    boolean isFileOpen = false;

    void init(){
        //窗口的初始化大小为800x600
        setSize(800, 600);
        //窗口的标题为"SimpleTXTReader"
        setTitle("SimpleTXTReader");
        //文章能自动换行
        textArea.setLineWrap(true);
        //文章是只读模式
        textArea.setEditable(false);
        //设置字体
        textArea.setFont(new Font("粗体", Font.PLAIN, 15));
        //只能纵向滚动、不能横向滚动
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(textArea);

        //关闭一开始不能点击
        close.setEnabled(false);

        //设置打开的鼠标点击事件
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    final JFileChooser fileChooser = new JFileChooser();
                    //文件类型为.txt的才能被选择
                    fileChooser.setFileFilter(new FileNameExtensionFilter("TXT File", "txt"));
                    String filePath = "";
                    if (fileChooser.showOpenDialog(jframe) == JFileChooser.APPROVE_OPTION ){
                        filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    }
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String oneLine;
                    //一行一行读取文件内容
                    while((oneLine = bufferedReader.readLine())!=null){
                        if(!isFileOpen){
                            textArea.setText(oneLine);
                            isFileOpen = true;
                        }
                        else textArea.setText(textArea.getText()+ "\n" + oneLine);
                    }
                    isFileOpen = false;
                    //关闭菜单项目设为可以点击
                    close.setEnabled(true);
                    bufferedReader.close();
                }catch(FileNotFoundException err){
                    err.printStackTrace();
                }catch(IOException err){
                    err.printStackTrace();
                }
            }
        });

        //设置关闭菜单项目的鼠标点击事件，点击后清空显示文章，菜单项目设为不可以被点击
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                close.setEnabled(false);
            }
        });

        //设置退出菜单项目的鼠标点击事件，直接关闭窗口
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    void add(){
        //将菜单项目添加到菜单
        menu.add(open);
        menu.add(close);
        //在关闭和退出之间设置分隔线
        menu.addSeparator();
        menu.add(exit);
        //将菜单添加到菜单栏
        menuBar.add(menu);
        //将菜单栏添加到窗口
        setJMenuBar(menuBar);
        //将滚动条添加到窗口
        add(scrollPane);
    }

    public SimpleTextReader(){
        init();
        add();
        setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTextReader simpleTextReader = new SimpleTextReader();
    }
}
