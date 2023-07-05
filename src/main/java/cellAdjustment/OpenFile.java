package cellAdjustment;

import cellAdjustment.model.StringListInputNeeded;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class OpenFile implements ActionListener {
   private JFrame frame;
   private JTextField fieldToWriteFileName;
   private FileHandler fileHandler;
   private StringListInputNeeded output;

    public OpenFile(JFrame frame, JTextField fieldToWriteFileName,StringListInputNeeded output) {
        this.frame = frame;
        this.fieldToWriteFileName = fieldToWriteFileName;
        this.output=output;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        File file=fc.getSelectedFile();
        fileHandler=new FileHandler(file);
        fieldToWriteFileName.setText(fileHandler.getFileName());
        output.process(fileHandler.getAbsolutePath(),fileHandler.readFromFile());
    }

    public StringListInputNeeded getOutput() {
        return output;
    }
}
