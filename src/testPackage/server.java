package b1;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class server {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876); // Port number
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                int n = Integer.parseInt(received);

                if (n <= 0) {
                    String response = "so nguyen n phai lon hon 0!";
                    sendResponse(socket, packet, response);
                    continue;
                }

                String result = findPrimesDivisibleBy5(n);
                sendResponse(socket, packet, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private static String findPrimesDivisibleBy5(int n) {
        if (n >= 5) {
            return "5";
        } else {
            return "khong co so nguyen to nao nho hon" + n + "chia het cho 5";
        }
    }
    
    private static void sendResponse(DatagramSocket socket, DatagramPacket packet, String response) throws IOException{
        byte[] responseBytes = response.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, packet.getAddress(), packet.getPort());
        socket.send(responsePacket);
    }
}