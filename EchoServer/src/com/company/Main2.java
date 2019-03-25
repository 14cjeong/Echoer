package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//A Main2 was created to create a multi-threaded server
//Main (original) can only accept one client
//All you have to do is put the server making code in the while loop

public class Main2 {

    public static void main(String[] args) {
        //You can use ports 0 - 6535
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            //Kicking off a new thread everytime we accept a new connection
            //using the Echoer Class


            while(true) {
               new Echoer(serverSocket.accept()).start();
                System.out.println("Connection has been made!");
               //The above is the same thing as this.
                // Socket socket = serverSocket.accept();
                // Echoer echoer = new Echoer(socket);
                // echoer.start();
                //the only different is that the new instance
                //is not stored in a variable




            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }





    }
}
