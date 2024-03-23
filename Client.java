import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started...........");
        Socket socket = new Socket("127.0.0.1",22222);
        System.out.println("Client connected.........");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        oos.writeObject(message);


        try {
            Object fromServer=ois.readObject();
            System.out.println("From Server: " + (String) fromServer);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
