package b1;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("nhap n: ");
        int n = scanner.nextInt();
        scanner.close();

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost"); 
            int port = 9876; 
            String request = String.valueOf(n);
            byte[] buffer = request.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("ket qua: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}