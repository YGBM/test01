package com.conn;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.net.DatagramPacket;

public class Client {

    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket(6666);
        for(;;){
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.next();
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length,new InetSocketAddress("localhost", 8888));
        client.send(packet);
        }

        // client.close();
    }
}
