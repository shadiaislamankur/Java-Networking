import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // Specify the server address and port
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Create a DatagramSocket
            DatagramSocket socket = new DatagramSocket();

            // Message to be sent
            String message = "Hello, server! This is a UDP packet.";

            // Convert the message to bytes
            byte[] sendData = message.getBytes();

            // Create a DatagramPacket to send to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Send the packet
            socket.send(sendPacket);

            System.out.println("Packet sent to server.");

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
