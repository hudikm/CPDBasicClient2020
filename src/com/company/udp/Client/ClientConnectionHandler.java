package com.company.udp.Client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ClientConnectionHandler implements Runnable {
    private final DatagramSocket socket;

    public ClientConnectionHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[150], 150);
        while (true) {
            try {
                socket.receive(datagramPacket);
                System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
