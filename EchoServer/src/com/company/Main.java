package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//This uses TCP/IP
public class Main {

    public static void main(String[] args) {
	   //You can use ports 0 - 6535
	    try(ServerSocket serverSocket = new ServerSocket(5000)) {
            //Calling the server socket
	        Socket socket = serverSocket.accept();
	        //accept() method will block until it connects to a server
            System.out.println("Client connected after running Client Application!");
            //When it does connect, the server will use input and output streams
            //to send data to the client and also to receive data from the client
            BufferedReader input = new BufferedReader (
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            //Common practice to wrap the input stream with a buffered reader
            //And to wrap the output stream with the PrintWriter
            //The second parameter "true" specifies whether you want to automatically
            //flush the output stream the printWriter is using.

            //This is the one time, we'll use an infinite loop to keep the
            //conection open
            while(true) {
                //readLine method is from the buffered reader
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;
                }
                output.println("Echo from server: " + echoString);
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }





    }
}
