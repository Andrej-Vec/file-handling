package com;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static volatile int counter = 0;

    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    while (counter < 240) {
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        String name = Thread.currentThread().getName();
                        String time = getCurrentTime();
                        printWriter.println(name + " " + time + " " + counter);
                    }
                }
            }, "Thread 1");

            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    while (counter < 240) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String name = Thread.currentThread().getName();
                        String time = getCurrentTime();
                        printWriter.println(name + " " + time + " " + counter);
                    }
                }
            }, "Thread 2");

            Thread t3 = new Thread(new Runnable() {
                public void run() {
                    while (counter < 240) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String name = Thread.currentThread().getName();
                        String time = getCurrentTime();
                        printWriter.println(name + " " + time + " " + counter);
                    }
                }
            }, "Thread 3");

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            printWriter.close();
            fileWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        return sdf.format(now);
    }
}
