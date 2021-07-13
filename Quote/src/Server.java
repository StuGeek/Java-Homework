import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    ServerSocket ss;
    ArrayList <Socket> connections = new ArrayList<>();
    public Server() throws IOException {
        this("QuoteServerThread");
    }

    public Server(String name) throws IOException {
        super(name);
        System.out.println("Server->java Server");
        System.out.println("Initializing Port...");
        ss = new ServerSocket(8000);
        socket = new DatagramSocket(4445);
        System.out.println("Listening...");
    }

    public void run() {
        String received;
        Socket clientSocket;
        while (moreQuotes) {
            try {

                clientSocket = ss.accept();
                System.out.println("Connect to client:"+
                        clientSocket.getRemoteSocketAddress());
                connections.add(clientSocket);


                byte[] buf = new byte[256];

                while (true) {
                    // receive request
                    BufferedReader inFromClient =
                            new BufferedReader(new
                                    InputStreamReader(clientSocket.getInputStream()));
                    received = inFromClient.readLine();

                    // figure out response

                    System.out.println(clientSocket.getPort()+":"+received);

                    // send the response to the client at "address" and "port"
                    DataOutputStream  outToClient;
                    for(Socket s : connections){
                        if(s.getPort()!=clientSocket.getPort()){
                            outToClient =
                                    new DataOutputStream(s.getOutputStream());
                            System.out.println("Broadcast to " + s.getPort());
                            outToClient.writeBytes(clientSocket.getRemoteSocketAddress()+": "+received+'\n');
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();
    }
}