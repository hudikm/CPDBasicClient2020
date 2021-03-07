package com.company.udp.Client;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {


            String hostName = args[0];
            int portNumber = Integer.parseInt(args[1]);
            System.out.println("UDP client: %s:%d".formatted(hostName,portNumber));

            Scanner sc = new Scanner(System.in);
            DatagramSocket ds = new DatagramSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, portNumber);
            ds.connect(inetSocketAddress);

            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = null;

            new Thread(new ClientConnectionHandler(ds)).start();

            buf = "Connect".getBytes(StandardCharsets.UTF_8);
            ds.send(new DatagramPacket(buf, buf.length, inetSocketAddress));

            while (true) {
                String inp = sc.nextLine();
                buf = inp.getBytes(StandardCharsets.UTF_8);
                ds.send(new DatagramPacket(buf, buf.length, inetSocketAddress));
            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
