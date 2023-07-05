package cellAdjustment;

import cellAdjustment.model.Parameters;
import cellAdjustment.service.CellService;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)  {
        JFrame frame = new JFrame("Cell adjustment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);

        Panel panel = new Panel();
        JButton button = new JButton("Choose parameters");
        panel.add(button); // Adds Button to content pane of frame
        JTextField controlForParametersFilename = new JTextField("Any Text");
        controlForParametersFilename.setPreferredSize(new Dimension(150,25));;
        controlForParametersFilename.setEditable(false);
        panel.add(controlForParametersFilename);
        OpenFile openParameters=new OpenFile(frame,controlForParametersFilename,new Parameters());
        button.addActionListener(openParameters);
        frame.add(panel, BorderLayout.NORTH);

        Panel panel2 = new Panel();
        JButton button2 = new JButton("Choose csv with cell's coordinates");
        panel2.add(button2); // Adds Button to content pane of frame
        JTextField controlForParametersFilename2 = new JTextField("Any Text");
        controlForParametersFilename2.setPreferredSize(new Dimension(150,25));;
        controlForParametersFilename2.setEditable(false);
        panel2.add(controlForParametersFilename2);
        OpenFile openCoordinates=new OpenFile(frame,controlForParametersFilename2,new CellService());

        button2.addActionListener(openCoordinates);
        frame.add(panel2, BorderLayout.CENTER);

        Panel panel3 = new Panel();
        JButton button3 = new JButton("Calculate");
        Calculation calculation=new Calculation((Parameters)openParameters.getOutput(),(CellService)openCoordinates.getOutput());
        button3.addActionListener(calculation);
        panel3.add(button3); // Adds Button to content pane of frame
        frame.add(panel3, BorderLayout.SOUTH);

        frame.validate();
        frame.setVisible(true);
    }
}
