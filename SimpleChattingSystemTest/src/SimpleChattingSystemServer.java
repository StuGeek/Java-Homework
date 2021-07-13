import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SimpleChattingSystemServer extends Thread {
	String message;
	ServerSocket serverSocket;
	ArrayList <Socket> socketList = new ArrayList<>();
	static String IP_Address = "127.0.0.1";
	static int LocalPort = 8000;
	static int maxNumOfThread = 100;

	public SimpleChattingSystemServer() {
		try{
			//初始化端口
			System.out.println("Initializing Port...");
			serverSocket = new ServerSocket(LocalPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//初始化端口成功
		System.out.println("Listening...");
	}

	public void run() {
		//目前连接的客户端服务器
		Socket connectionSocket;
		String address;
		try {
			Thread.sleep(50);
			while (true) {
				//使用accept方法进行阻塞
				connectionSocket = serverSocket.accept();
				//客户端服务器的IP地址和端口
				address = IP_Address + ":" + connectionSocket.getPort();
				//显示连接到的客户端服务器的IP地址和端口
				System.out.println("Connect to client: " + address);
				//将这个客户端服务器加入服务器列表中
				socketList.add(connectionSocket);
				while (true) {
					try {
						//从客户端读取内容
						BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
						address = IP_Address + ":" + connectionSocket.getPort();
						message = inFromClient.readLine();
						String information = address + " : " + message + '\n';
						DataOutputStream outToClient;
						//将从这个客户端读取的内容广播给非这个用户的其他用户
						for (int i = 0; i < socketList.size(); ++i) {
							Socket socket = socketList.get(i);
							if (socket != connectionSocket) {
								outToClient = new DataOutputStream(socket.getOutputStream());
								outToClient.write((information).getBytes(StandardCharsets.UTF_8));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
				//用户退出后，关闭客户端的服务器，并从服务器列表中移除客户端服务器
				connectionSocket.close();
				socketList.remove(connectionSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		try{
			//建立服务器
			System.out.println("Server->java Server");
			SimpleChattingSystemServer server = new SimpleChattingSystemServer();
			//使用多线程允许多人聊天
			for(int i = 0; i < maxNumOfThread; ++i){
				Thread thread = new Thread(server);
				thread.start();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}