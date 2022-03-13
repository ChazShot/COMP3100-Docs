import java.net.*;
import java.io.*;

public class UDPServer {
  public static void main(String args[]) {
    DatagramSocket aSocket = null;
    
    try {
      aSocket = new DatagramSocket(6789);
      System.out.println("Server Started");
      System.out.println("Waiting for client activation");


      byte[] buffer = new byte[1000];

      while (!buffer[].equals("BYE")) {

        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        
        aSocket.receive(request);
        
        DatagramPacket reply = new DatagramPacket(request.getData(),
            request.getLength(), request.getAddress(), request.getPort());
        
            aSocket.send(reply);
      }



    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    } finally {
      if (aSocket != null)
        aSocket.close();
    }
  }
}