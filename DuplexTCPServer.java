import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DuplexTCPServer {
    public static void main(String[] args) {
        try {
            // Specify the server port
            int serverPort = 12345;

            // Create a ServerSocket
            ServerSocket serverSocket = new ServerSocket(serverPort);

            System.out.println("Server is listening on port " + serverPort);

            // Wait for incoming connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getInetAddress());

            // Create BufferedReader to read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Create PrintWriter to send data to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Create BufferedReader to read data from the console
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Create a separate thread for reading console input and sending to the client
            Thread inputThread = new Thread(() -> {
                try {
                    while (true) {
                        String userInput = consoleReader.readLine();
                        writer.println(userInput);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();

            // Read and display messages received from the client
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
            }

            // Close the sockets and streams
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}