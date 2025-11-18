package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second java graphical interface");

    private SimpleGUIWithFileChooser(final Controller ctrl){
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

        final JTextField filePath = new JTextField(ctrl.getCurrentFilePath());
        filePath.setEditable(false);
        final JButton chooseFile = new JButton("Browse...");
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e){
                final JFileChooser fc = new JFileChooser("Chose where to save");
                fc.setSelectedFile(ctrl.getCurrentFile());
                if(fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                    final File newDest = fc.getSelectedFile();
                    ctrl.setDestination(newDest);
                    filePath.setText(newDest.getPath());
                }
                else if(fc.showSaveDialog(frame) == JFileChooser.CANCEL_OPTION){

                }
                else{
                    JOptionPane.showMessageDialog(frame, fc.showSaveDialog(frame), "Meh!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(filePath, BorderLayout.CENTER);
        panel2.add(chooseFile, BorderLayout.LINE_END);
        panel1.add(panel2, BorderLayout.NORTH);

        frame.setContentPane(panel1);
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
       new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
