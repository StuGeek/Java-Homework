import jdk.nashorn.internal.scripts.JD;
//import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    private ServerSocket ss;
    private Storage storage;

    public Server() throws IOException {
        this("QuoteServerThread");
    }

    public Server(String name) throws IOException {
        super(name);
        System.out.println("Server->java Server");
        System.out.println("[Server] Connect to JDBC successfully");
        System.out.println("[Server] Initializing Port...");
        ss = new ServerSocket(8000);
        socket = new DatagramSocket(4445);
        System.out.println("[Server] Listening on port 8000");
    }

    public void run() {
        String received;
        String request;
        Socket clientSocket;
        String information;
        int id;
        String password;
        while (true) {
            try {
                clientSocket = ss.accept();
                System.out.println("Connect to client:"+
                        clientSocket.getRemoteSocketAddress());

                BufferedReader InFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                received= InFromClient.readLine();
                System.out.println("Get "+received);
                request = received.substring(0,received.indexOf("?"));
                if(request.equals("login")){
                    System.out.println("login request");
                    information = received.substring(received.indexOf("?")+1);
                    id = Integer.valueOf(information.split("&&")[0]);
                    password = information.split("&&")[1];
                    Student s = storage.queryStudentByID(id);
                    System.out.println(s.getId());
                    //加密
                    // System.out.println(DigestUtils.md5Hex("123456"));
                }
                if(request.equals("register")){
                    System.out.println("register request");
                    information = received.substring(received.indexOf("?")+1);
                    String[] StudentInfos = information.split("&&");
                    id = Integer.valueOf(StudentInfos[0]);
                    password = StudentInfos[1];
                    String name = StudentInfos[2];
                    String department = StudentInfos[3];
                    storage.insertStudent(new Student(id,password,name,department));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.start();
        t2.start();
    }
}
