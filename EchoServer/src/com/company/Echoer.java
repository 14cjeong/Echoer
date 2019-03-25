package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;



//Creating the multi-threads!
//We're adding this to solve the multi-threaded problem.
//which is that when we have multiple clients
//the server gets blocked on the accept call

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                System.out.println("Received client input: " + echoString);
                if (echoString.equals("exit")) {
                    break;
                }

                /*try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted.");
                }*/
                output.println(echoString);
            }
        } catch(SocketTimeoutException e) {
            System.out.println("The socket timed out.");
        } catch(IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try{
                socket.close();
            } catch(IOException e) {
                System.out.println("Oh, well!");
            }
        }
    }
}
