package com.company.tcp.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            System.out.println("TCP client: %s:%d".formatted(hostName,portNumber));
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                    new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(echoSocket.getInputStream()));


            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            SocketListener socketListener = new SocketListener(in);
            Thread thread = new Thread(socketListener);
            thread.start();

            // Echo
            String inputLine;
            while ((inputLine = stdIn.readLine()) != null) {
                out.println(inputLine);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
