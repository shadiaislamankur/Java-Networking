import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    public static void main(String[] args) {
        try {
            // Specify the multicast group and port
            InetAddress group = InetAddress.getByName("230.0.0.1");
            int port = 12345;

            // Create a MulticastSocket
            MulticastSocket socket = new MulticastSocket();

            // Message to be sent
            String message = "Hello, multicast world!";

            // Convert the message to bytes
            byte[] sendData = message.getBytes();

            // Create a DatagramPacket to send to the multicast group
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, group, port);

            // Send the packet
            socket.send(sendPacket);

            System.out.println("Message sent to the multicast group.");

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
