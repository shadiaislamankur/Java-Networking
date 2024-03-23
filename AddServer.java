import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AddServer {
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

                // Create BufferedReader to read data from the client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Create PrintWriter to send data to the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the count value from the client
                int count = Integer.parseInt(reader.readLine());

                // Calculate the sum of integers from 1 to the count value
                int sum = calculateSum(count);

                // Send the sum back to the client
                writer.println(sum);

                // Close the socket and streams
                reader.close();
                writer.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int calculateSum(int count) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += i;

        }
        return sum;
    }
}
