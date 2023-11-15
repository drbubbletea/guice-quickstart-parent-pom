package net.timeboxing;

import com.formdev.flatlaf.FlatLightLaf;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.swing.*;

public class SwingApp
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new AdapterTestModule());
        injector.getInstance(SomeService.class).doSomething();
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
