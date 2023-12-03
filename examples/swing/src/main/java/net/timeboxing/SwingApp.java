package net.timeboxing;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class SwingApp
{
    public static void main( String[] args )
    {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame f = new JFrame();
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
