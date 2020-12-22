package com.soconn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();
        while(true){
        DataInputStream dis  = new DataInputStream(client.getInputStream());
        String msg = dis.readUTF();

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("服务器-->> "+msg);
        dos.flush();            
        }

    }
}
