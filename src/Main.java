import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main
{
    public static void main (String[] args) {
        //creating frames, labels and buttons for the GUI
        JFrame frame = new JFrame("Max-Min Heap");
        JFrame error = new JFrame("error");
        JFrame printFrame = new JFrame();
        JFrame helpWin = new JFrame("help");
        Button build = new Button("create heap");
        Button exit = new Button("exit");
        Button help = new Button("help");
        Button extractMax = new Button("heap extract max");
        Button extractMin = new Button("heap extract min");
        Button insert = new Button("heap insert");
        Button delete = new Button("heap delete");
        Button print = new Button("print");
        Button sort = new Button("sort");
        Label inputLabel =new Label("Input:");
        Label printLabel = new Label();
        Label err = new Label();
        JLabel helpLabel = new JLabel();
        JTextField textBox = new JTextField();
        sort.setBounds(470, 10, 100, 50);
        extractMax.setBounds(130, 75, 100, 50);
        extractMin.setBounds(240, 75, 100, 50);
        insert.setBounds(350, 75, 100, 50);
        delete.setBounds(460, 75, 100, 50);
        build.setBounds(240, 75, 100, 50);
        exit.setBounds(240, 150, 100, 50);
        help.setBounds(130, 150, 100, 50);
        print.setBounds(10, 75, 100, 50);
        textBox.setBounds(240,20,200,30);
        inputLabel.setBounds(200, 20, 40,30);
        frame.setBounds(500, 300, 600, 300);
        helpWin.setBounds(200,100,600,500);
        printFrame.setBounds(500,233,100000,600);
        printLabel.setFont(new Font("David", Font.PLAIN, 24));
        printFrame.add(printLabel);
        printFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(build);
        frame.add(exit);
        frame.add(help);
        frame.setLayout(null);
        frame.setVisible(true);
        error.add(err);
        error.pack();
        helpLabel.setHorizontalAlignment(JLabel.LEFT);
        error.setBounds(750,450,200,100);
        error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpLabel.setText("<html><br> \"build heap\" - choose txt to build heap. text must be fill with integers seperated by spaces<br><br>" +
                "\"exit\" - exit the program.</html>");
        helpLabel.setFont(new Font("ariel", Font.PLAIN, 16));

        //exit button action
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); // end of exit action

        //help button
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpWin.add(helpLabel,BorderLayout.NORTH);
                helpWin.setVisible(true);

            }
        });

        //build button action
        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==build){
                    JFileChooser fc = new JFileChooser();
                    int val = fc.showOpenDialog(frame);
                    if(val == JFileChooser.APPROVE_OPTION){
                        String path = fc.getSelectedFile().getAbsolutePath();
                        if(path.endsWith(".txt")) {
                            File file = new File(path);
                            //File file = new File("C:\\Users\\Inna\\Desktop\\test.txt"); //-tester file
                            int[] arr = new int[256];
                            Scanner sc = null;

                            //check for valid file
                            try {
                                sc = new Scanner(file);
                                int len = 0;
                                while (sc.hasNextInt()) {
                                    arr[len] = sc.nextInt();
                                    len++;

                                }
                                Heap heap = new Heap(arr, len);

                                //create new menu
                                build.setBounds(10, 10, 100, 50);
                                frame.add(extractMax);
                                frame.add(extractMin);
                                frame.add(insert);
                                frame.add(delete);
                                frame.add(inputLabel);
                                frame.add(textBox);
                                frame.add(print);
                                frame.add(sort);
                                helpLabel.setText(
                                        "<html><br>   \"build heap\" - choose txt to build heap. text must be fill with integers seperated by spaces.<br><br>" +
                                        "\"insert\" - insert the inputted integer to the heap.<br><br>" +
                                        "\"delete\" - delete the number in the inputted index from the heap.<br><br>" +
                                        "\"extract max\" - remove the maximum number from the heap and print it on the screen.<br><br>" +
                                        "\"extract min\" - removes the minimum number from the heap and print it on the screen.<br><br>" +
                                        "\"sort\" - sort the heap.<br><br>" +
                                        "\"print\" - print the heap to the screen<br><br>" +
                                        "\"exit\" - exit the program.</html>");

                                //insert button
                                insert.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String temp = textBox.getText();
                                        textBox.setText("");
                                        //check for valid input
                                        try {
                                            int val = Integer.parseInt(temp);
                                            Heap.insert(heap, val);
                                        } catch (NumberFormatException ex) {
                                            err.setText("ERROR please type numbers only");
                                            error.setVisible(true);
                                        }

                                    }
                                });

                                //delete button
                                delete.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String temp = textBox.getText();
                                        textBox.setText("");
                                        //check for valid input
                                        try {
                                            int index = Integer.parseInt(temp);
                                            if (index >= 0 && index < heap.getLength())
                                                Heap.delete(heap, index);
                                            else {
                                                err.setText("ERROR index out of bound");
                                                error.setVisible(true);
                                            }
                                        } catch (NumberFormatException ex) {
                                            err.setText("ERROR please type numbers only");
                                            error.setVisible(true);
                                        }

                                    }
                                });

                                //extract max button
                                extractMax.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int max = Heap.extractMax(heap);
                                        printLabel.setText("The maximum is: " + max);
                                        printFrame.setVisible(true);
                                        printFrame.pack();

                                    }
                                });

                                //extract min button
                                extractMin.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int min = Heap.extractMin(heap);
                                        printLabel.setText("The minimum is: " + min);
                                        printFrame.setVisible(true);
                                        printFrame.pack();
                                    }
                                });

                                //print button
                                print.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        printLabel.setText(heap.toString());
                                        printFrame.setVisible(true);
                                        printFrame.pack();
                                    }
                                });

                                sort.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Heap.sort(heap);
                                        frame.remove(extractMax);
                                        frame.remove(extractMin);
                                        frame.remove(insert);
                                        frame.remove(delete);
                                        frame.remove(inputLabel);
                                        frame.remove(textBox);
                                    }
                                });

                            } catch (FileNotFoundException ex) {
                                err.setText("ERROR file not found");
                                error.setVisible(true);
                            }
                        }
                        else {
                            err.setText("ERROR Select txt file only");
                            error.setVisible(true);
                            frame.remove(help);
                            frame.remove(extractMax);
                            frame.remove(extractMin);
                            frame.remove(insert);
                            frame.remove(delete);
                            frame.remove(inputLabel);
                            frame.remove(textBox);
                            frame.remove(print);
                            frame.remove(sort);
                            build.setBounds(240, 75, 100, 50);
                        }

                    } //end of file chooser if

                } // end of if




           } //end of action preformed
        }); // end of build action

    } //end of main method
} //end of main class
