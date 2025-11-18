package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first java graphical interface");

    private SimpleGUI(final Controller ctrl){
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();

        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try{
                    ctrl.save(text.getText());
                } catch (final IOException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel1.add(text, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);

        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 5, sh / 5);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }



    public static void main(final String... args) {
       new SimpleGUI(new Controller()).display();
    }
}