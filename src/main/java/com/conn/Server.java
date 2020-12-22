package com.conn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {

    public static void main(String[] args) throws IOException {

        DatagramSocket server = new DatagramSocket(8888);

        for(;;){
            byte[] container = new byte[1024];

            DatagramPacket packet = new DatagramPacket(container,container.length);
            server.receive(packet);
            byte[] data = packet.getData();
            int len = packet.getLength();
            System.out.println(new String(data,0,len));
        }
        // server.close(); 

    }
    
}
