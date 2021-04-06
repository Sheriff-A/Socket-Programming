package sample;

import java.net.*;
import java.io.*;

// Client Code
public class Client {

    public Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;

    public Client (String name, String address, int port) {
        // Establish A Connection
        try {
            socket = new Socket(address, port);
            System.out.println(name + " Connected!");
            System.out.println("Address: " + address);
            System.out.println("Port: " + port);

            // Takes Input From Terminal
            input = new BufferedReader(new InputStreamReader(System.in));

            // Sends Output Via Socket
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u){
            System.err.println("Unknown Host Error: " + u);
        } catch (IOException i){
            System.err.println("IO Error: " + i);
        }

        // String to Read Message from Input
        String line = "";

        // Keep Reading Until "Over" is Input
        while (!line.toLowerCase().equals("over")){
            try {
                line = input.readLine();
                output.writeUTF(name + ": " + line);
            }catch (IOException i){
                System.err.println("IO Error: " + i);
                break;
            }
        }

        // Close the connection
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException i){
            System.err.println("IO Error: " + i);
        }

    }

    public static void main (String[] args){
        new Client("Client 1", "127.0.0.1", 5000);
    }

}
