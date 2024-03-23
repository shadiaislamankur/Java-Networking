import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AddClient {
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

            // Create BufferedReader to read data from the console
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Get the count value from the user
            System.out.print("Enter the count value: ");
            int count = Integer.parseInt(consoleReader.readLine());

            // Send the count value to the server
            writer.println(count);

            // Receive and display the sum from the server
            String serverResponse = reader.readLine();
            System.out.println("Sum received from the server: " + serverResponse);

            // Close the socket and streams
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
