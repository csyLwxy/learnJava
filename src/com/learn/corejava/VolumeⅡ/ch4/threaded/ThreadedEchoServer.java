package com.learn.corejava.VolumeⅡ.ch4.threaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This program implements a multithreaded server that listens to port 8189 and echos back all client input
 * @author HP
 */
public class ThreadedEchoServer {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * This class handles the client input for one server socket connection
 */
class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    ThreadEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try (InputStream inputStream = incoming.getInputStream();
            OutputStream outputStream = incoming.getOutputStream()) {

            Scanner in = new Scanner(inputStream, "UTF-8");
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);

            out.println("Hello! Enter BYE to exit.");

            // echo client input
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if ("BYE".equals(line.trim())) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}