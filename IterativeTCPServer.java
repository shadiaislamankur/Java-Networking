import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class IterativeTCPServer {
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Specify the server port
            int serverPort = 12345;

            // Create a ServerSocket
            ServerSocket serverSocket = new ServerSocket(serverPort);

            System.out.println("Server is listening on port " + serverPort);

            while (true) {
                // Wait for incoming connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());

                // Create PrintWriter to send data to the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Add the writer to the list of client writers
                clientWriters.add(writer);

                // Create a separate thread for each client to handle messages
                Thread clientThread = new Thread(() -> {
                    try {
                        // Create BufferedReader to read data from the client
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        // Read and broadcast messages from the client
                        String clientMessage;
                        while ((clientMessage = reader.readLine()) != null) {
                            System.out.println("Received from client: " + clientMessage);

                            // Broadcast the message to all connected clients
                            broadcastMessage(clientMessage);
                        }

                        // Remove the writer from the list when the client disconnects
                        clientWriters.remove(writer);

                        // Close the socket and stream
                        reader.close();
                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            try {
                // Send the message to each connected client
                writer.println("Server says: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
