import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DuplexTCPClient {
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

            // Create a separate thread for reading server messages and displaying them
            Thread outputThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("Server says: " + serverMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            outputThread.start();

            // Read and send messages to the server
            while (true) {
                String userInput = consoleReader.readLine();
                writer.println(userInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
