import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Client extends Thread {
    private Socket s;
    private String sentences;
    public Client(){
        try {
             s = new Socket("localhost",8000);
            System.out.println("Client->"+s.getLocalAddress()+":"+s.getLocalPort());
            start();
            while (true){
                // prepare for Data

                System.out.print("->");
                BufferedReader inFromUser =
                        new BufferedReader(new InputStreamReader(System.in));
                sentences = inFromUser.readLine();

                // send request
                DataOutputStream outToServer =
                        new DataOutputStream(s.getOutputStream());

                if(sentences.length() != 0){
                    outToServer.writeBytes(sentences+'\n');
                }else{
                    System.out.println("You can not send message with none characters.");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        String received;
        // get a datagram socket
        try{
            while (true){
                // get response
                BufferedReader inFromServer =
                        new BufferedReader(new
                                InputStreamReader(s.getInputStream()));

                received = inFromServer.readLine();


                // display response
                System.out.println("\n"+received);
                System.out.print("->");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        Client c = new Client();
    }
}