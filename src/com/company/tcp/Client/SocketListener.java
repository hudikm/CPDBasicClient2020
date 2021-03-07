package com.company.tcp.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class SocketListener implements Runnable {
    private BufferedReader input;

    public SocketListener(BufferedReader input) {
        this.input = input;
    }

    @Override
    public void run() {
        String inputString;
        try {
            while ((inputString = input.readLine()) != null) {
                System.out.println(inputString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
