package com.learn.corejava.VolumeⅡ.ch4.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado,
 * and prints the time hat the server sends
 * @author HP
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        }
    }
}
