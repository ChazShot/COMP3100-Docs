import java.net.*;
import java.util.Arrays;
import java.io.*;

public class UDPTestServer {
    public static void main(String args[]) {

        DatagramSocket aSocket = null;
        try {
            try {
                aSocket = new DatagramSocket(6789);
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                try {
                    aSocket.receive(request);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                InetAddress address = request.getAddress();
                int port = request.getPort();

                String str = "BYE";
                byte[] strcheck;

                byte[] serverBuffer = new byte[997];
                strcheck = (str).getBytes();
                byte[] manualpad = {76, 79};

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    outputStream.write(strcheck);
                    //outputStream.write(manualpad);
                    outputStream.write(serverBuffer);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                byte bytestrCheck[] = outputStream.toByteArray();

                String s = "G'DAY";
                byte[] f = (s).getBytes();

                byte[] test = request.getData();

                if (!Arrays.equals(bytestrCheck, test)) {
                    DatagramPacket dp1 = new DatagramPacket(f, f.length, address, port);
                    try {
                        aSocket.send(dp1);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    String str2 = "BYE";
                    byte[] x = (str2).getBytes();
                    DatagramPacket dp2 = new DatagramPacket(x, x.length, address, port);
                    try {
                        aSocket.send(dp2);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    aSocket.close();
                }
            }
        }

        finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }

    }
}
