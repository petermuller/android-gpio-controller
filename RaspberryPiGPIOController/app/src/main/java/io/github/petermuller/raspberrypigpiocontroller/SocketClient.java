package io.github.petermuller.raspberrypigpiocontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Peter on 1/1/2015.
 */
public class SocketClient {

    /*
    * Class Members
    *
    * s - socket connection
    * out - output writer
    * in - input reader
    * */
    Socket s;
    PrintWriter out;
    BufferedReader in;

    public SocketClient() {}
    public SocketClient(String dest, int port) throws UnknownHostException, IOException{
        s = new Socket(dest,port);
        out = new PrintWriter(s.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public void sendMessage(String message){
        if (message.length() > 10){ //Avoid overflowing the server buffer.
            message = message.substring(0,9);
        } else {
            while (message.length() < 10){
                message += ' ';
            }
        } //Message is now exactly 10 characters long
        out.print(message);
    }
}
