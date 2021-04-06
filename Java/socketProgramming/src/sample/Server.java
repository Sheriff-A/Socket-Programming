package sample;


import java.net.*;
import java.io.*;

// Server Side Code
public class Server{

    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;

    public Server (int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server is Live on Port: " + port);

            System.out.println("Waiting for Client");

            socket = serverSocket.accept();
            System.out.println("Client Accepted!");

            // Takes Input from the Client Socket
            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );

            String line = "", msg = "";
            while(!msg.toLowerCase().equals("over")){
                try {
                    line = input.readUTF();
                    msg = line.split(":")[1].strip();
                    System.out.println(line);
                }
                catch(IOException i) {
                    System.err.println("IO Error 2: " + i);
                    break;
                }
            }
            System.out.println("Closing Connection");

            // Close Connection
            socket.close();
            input.close();
        } catch (IOException e) {
            System.err.println("IO Error 1: " + e);
        }
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
