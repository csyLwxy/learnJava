package com.learn.corejava.VolumeⅠ.ch6.anonymousInnerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * This program demonstrates anonymous inner class
 * @author HP
 */
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000,true);

        // keep program running until user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program");
        System.exit(0);
    }
}

class TalkingClock {
    /**
     * Starts the clock
     * @param interval the interval between messages(in milliseconds)
     * @param beep true if the clock should beep
     */
    void start(int interval, boolean beep) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        new Timer(interval, listener).start();
    }
}