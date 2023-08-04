package Text_Editor;
import java.awt.*;
import java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Text_Editor extends  JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JButton openButton, saveButton;

    public Text_Editor(){
        super("Text_Editor");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.PAGE_START);

        openButton = new JButton("open");
        openButton.addActionListener(this);
        buttonPanel.add(openButton);

        saveButton = new JButton("save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openButton){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int returnVal = fileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                try{
                    FileReader reader = new FileReader(fileChooser.getSelectedFile());
                    BufferedReader br = new BufferedReader(reader);
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line = br.readLine()) != null){
                        sb.append(line).append("\n");
                    }
                    textArea.setText(sb.toString());

                    br.close();
                    reader.close();

                }catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch(IOException ex){
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }else if(e.getSource() == saveButton){
                JFileChooser fileChooser1 = new JFileChooser();
                fileChooser1.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
                int returnVal1 = fileChooser1.showSaveDialog(this);
                if(returnVal1 == JFileChooser.APPROVE_OPTION){
                    try{
                        FileWriter writer = new FileWriter(fileChooser1.getSelectedFile());
                        writer.write(textArea.getText());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        new Text_Editor();
    }
}
