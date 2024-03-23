import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // Specify the server address and port
            String serverAddress = "localhost";
            int serverPort = 12345;

            // Create a Socket to connect to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Create BufferedReader to read data from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Create PrintWriter to send data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Message to be sent to the server
            String message = "Hello, server! This is a TCP connection.";

            // Send the message to the server
            writer.println(message);

            // Read the response from the server
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            // Close the socket and streams
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
