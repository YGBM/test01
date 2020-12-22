package com.fuzs;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import static java.lang.System.out;

public class InetDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] ips = InetAddress.getAllByName("www.baidu.com");
        out.println(Arrays.toString(ips));
        InetAddress localHost = InetAddress.getLocalHost();
        out.println(localHost);
    }
}
