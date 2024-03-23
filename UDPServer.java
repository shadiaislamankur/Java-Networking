import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Specify the server port
            int serverPort = 12345;

            // Create a DatagramSocket
            DatagramSocket socket = new DatagramSocket(serverPort);

            // Buffer to receive incoming data
            byte[] receiveData = new byte[1024];

            // Create a DatagramPacket to receive data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Server is listening on port " + serverPort);

            // Receive the packet
            socket.receive(receivePacket);

            // Extract the received data
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Received from client: " + receivedMessage);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
