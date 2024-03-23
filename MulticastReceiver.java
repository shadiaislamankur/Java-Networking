import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    public static void main(String[] args) {
        try {
            // Specify the multicast group and port
            InetAddress group = InetAddress.getByName("230.0.0.1");
            int port = 12345;

            // Create a MulticastSocket
            MulticastSocket socket = new MulticastSocket(port);

            // Join the multicast group
            socket.joinGroup(group);

            // Buffer to receive incoming data
            byte[] receiveData = new byte[1024];

            // Create a DatagramPacket to receive data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive the packet
            socket.receive(receivePacket);

            // Extract the received data
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Received from multicast group: " + receivedMessage);

            // Leave the multicast group
            socket.leaveGroup(group);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
