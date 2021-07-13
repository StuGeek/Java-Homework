import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SimpleChattingSystemClient extends Thread {
    Socket clientsocket;
    String message;
    Scanner in;
    DataOutputStream outToServer;
    static String IP_Address = "127.0.0.1";
    static int Port = 8000;

    public SimpleChattingSystemClient() throws Exception{
        try {
            //建立客户端服务器
            clientsocket = new Socket(IP_Address, Port);
            //客户端服务器的IP地址和端口
            String address = IP_Address + ":" + clientsocket.getLocalPort();
            //显示客户端服务器的IP地址和端口
            System.out.println("Client->" + address);
            //准备从服务端接收数据
            start();
            //准备向服务端发送数据
            while (true){
                //一开始显示一个箭头
                System.out.print("->");
                //输入内容
                in = new Scanner(System.in);
                message = in.nextLine();
                outToServer = new DataOutputStream(clientsocket.getOutputStream());
                //如果输入内容为空，就不向服务端发送数据
                if(message.equals("")) System.out.println("You can not send message with none characters.");
                //否则以UTF-8的格式向服务端发送数据，可以避免中文乱码
                else outToServer.write((message+'\n').getBytes(StandardCharsets.UTF_8));
            }
        } catch (SocketException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        clientsocket.close();
    }

    public void run() {
        try{
            while (true) {
                Thread.sleep(50);
                //从服务端接收数据
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
                message = inFromServer.readLine();
                //从行首开始覆盖箭头打印内容
                System.out.print("\r" + message + "\n->");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            new SimpleChattingSystemClient();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}