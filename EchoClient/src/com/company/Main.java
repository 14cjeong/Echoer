package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       //First parameter: The address of the host we want to connect to
        //Second parameter: port number
        //if "local host" doesn't work, pass "127.0.0.1" with quotations
        //Note that, we are creating the server on our own computer
        //Now, if you're connecting to a server that you haven't created
        //look at the API documentation
        try (Socket socket = new Socket("localhost", 5000)) {
            //Setting a timeout
            //Purpose - prevents a client from blocking forever
            //The following translates to
            //If there is no client activity for 5 seconds
            //it will timeout
            //socket.setSoTimeout(5000);

            BufferedReader echoes = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            //Now, we can hardcode strings that we want to send to the server
            //We're going to use a scanner to read input from a console
            //And then once we have the string, we're going to write it to the
            //socket
            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            //We are using the do, while loop here because we want
            //the following statements to be executed at least once
            //Because we don't have any idea how many times
            //the client will execute
            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);
                if(!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));
            //The while loop continues until we have an exit
            //It'll keep on running statements in the if statement
            //as long as the while loop is on

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }


    }
}
