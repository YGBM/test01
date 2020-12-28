package com.soconn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

    
        Socket client = new Socket("localhost",9999);

        String str  = 
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
        "<service>\n" +
        "    <APP_HEAD>\n" +
        "        <TranDate>20130707</TranDate>\n" +
        "        <TranSeqNo>000220130707</TranSeqNo>\n" +
        "        <TranTellerNo>800800</TranTellerNo>\n" +
        "        <TranTime>121200</TranTime>\n" +
        "    </APP_HEAD>\n" +
        "    <SYS_HEAD>\n" +
        "        <ConsumerId>000002</ConsumerId>\n" +
        "        <Mac>0000000000000000</Mac>\n" +
        "        <MsgId>570593d9-7198-4cba-b944-3def938b3b50</MsgId>\n" +
        "        <ServiceCode>11001000013</ServiceCode>\n" +
        "        <ServiceScene>02</ServiceScene>\n" +
        "        <SourceSysId>000001</SourceSysId>\n" +
        "    </SYS_HEAD>\n" +
        "</service>\n";

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

    //    while(true){
            dos.writeUTF(str);
            dos.flush();

            String msg = dis.readUTF();
           
            byte[] bytes = new byte[1024];
            int len = 0;
           

            String result = "";

            while((len = dis.read(bytes)) != -1){
                result +=new String(bytes,0,len);
            }
            System.out.println(msg);

        // }

        dos.close();
        dis.close();
        client.close();
       
    }
}
