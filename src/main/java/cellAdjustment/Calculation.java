package cellAdjustment;

import cellAdjustment.model.Parameters;
import cellAdjustment.service.CellService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculation implements ActionListener {
    private Parameters parameters;
    private CellService cellService;

    public Calculation(Parameters parameters, CellService cellService) {
        this.parameters = parameters;
        this.cellService = cellService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(parameters.getParametersAsStringList());
        cellService.correction(parameters);
        cellService.writeCellsIntoOutputFile();

    }
}
