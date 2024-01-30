package cellAdjustment.service;

import cellAdjustment.model.Cell;
import cellAdjustment.model.Coordinates;
import cellAdjustment.model.Parameters;
import cellAdjustment.model.StringListInputNeeded;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CellService implements StringListInputNeeded {
    private List<String> input;
    private String header;
    private List<Cell> cellsList=new ArrayList<>();
    private String originalAbsolutePathAndName;
    private String correctedAbsolutePathAndName;

    public CellService() {
    }

    @Override
    public void process(String absolutePath, List<String> input) {
        this.input=input;
        setHeader();
        setCellsList();
        setOriginalAbsolutePathAndName(absolutePath);
    }

    private void setCellsList() {
        String line;
        Cell inputCell;
        cellsList=new ArrayList<>();

        for (int i = 1; i < input.size(); i++) {
            line=input.get(i);
            if(!line.isBlank()) {
                inputCell=lineProcess(line);
                cellsList.add(inputCell);
            }
        }
    }

    private Cell lineProcess(String line) {
        String[] fieldsArray=line.split(Cell.SEPARATOR);

        double volume=Double.parseDouble(fieldsArray[0]);
        double surface=Double.parseDouble(fieldsArray[1]);
        int nbOfObjVoxels=Integer.parseInt(fieldsArray[2]);
        int nbOfSurfVoxels=Integer.parseInt(fieldsArray[3]);
        int intDen=Integer.parseInt(fieldsArray[4]);
        double mean=Double.parseDouble(fieldsArray[5]);
        double stdDev=Double.parseDouble(fieldsArray[6]);
        double median=Double.parseDouble(fieldsArray[7]);
        double min=Double.parseDouble(fieldsArray[8]);
        double max=Double.parseDouble(fieldsArray[9]);
        double x=Double.parseDouble(fieldsArray[10]);
        double y=Double.parseDouble(fieldsArray[11]);
        double z=Double.parseDouble(fieldsArray[12]);
        double meanDistToSurf=Double.parseDouble(fieldsArray[13]);
        double sdDistToSurf=Double.parseDouble(fieldsArray[14]);
        double medianDistToSurf=Double.parseDouble(fieldsArray[15]);
        double xM=Double.parseDouble(fieldsArray[16]);
        double yM=Double.parseDouble(fieldsArray[17]);
        double zm=Double.parseDouble(fieldsArray[18]);
        int bX=Integer.parseInt(fieldsArray[19]);
        int bY=Integer.parseInt(fieldsArray[20]);
        int bZ=Integer.parseInt(fieldsArray[21]);
        int bWidth=Integer.parseInt(fieldsArray[22]);
        int bHeight=Integer.parseInt(fieldsArray[23]);
        int bDepth=Integer.parseInt(fieldsArray[24]);

        return new Cell(volume, surface, nbOfObjVoxels, nbOfSurfVoxels, intDen, mean, stdDev, median, min, max, x, y, z, meanDistToSurf, sdDistToSurf, medianDistToSurf, xM, yM, zm, bX, bY, bZ, bWidth, bHeight, bDepth);
        }

    private void setHeader(){
        header=input.get(0);
    }

    public void setOriginalAbsolutePathAndName(String originalAbsolutePathAndName) {
        this.originalAbsolutePathAndName = originalAbsolutePathAndName;
        setCorrectedAbsolutePathAndName();
    }

    public void setCorrectedAbsolutePathAndName() {
        int indexOfLastDot=originalAbsolutePathAndName.lastIndexOf(".");
        String extension=originalAbsolutePathAndName.substring(indexOfLastDot+1);
        String fileNameWithoutExtension= originalAbsolutePathAndName.substring(0,indexOfLastDot);
        fileNameWithoutExtension+="_corrected";
        correctedAbsolutePathAndName = fileNameWithoutExtension+"."+extension;
        System.out.println("corrected file: "+correctedAbsolutePathAndName);
    }

    public void correction(Parameters parameters){
        Cell cell;
        for (int i = 0; i < cellsList.size() ; i++) {
            cell=cellsList.get(i);
//            System.out.println("correction - x: "+cell.getCoordinates().getX()+" y: "+cell.getCoordinates().getY()+" shift: "+parameters.getShift(cell.getCoordinates().getX(),cell.getCoordinates().getY()));
            cell.getCoordinates().shiftZ(parameters.getShift(cell.getCoordinates().getX(),cell.getCoordinates().getY()));;
        }
    }

    public void writeCellsIntoOutputFile(){
        List<String> output=new ArrayList<>();
        output.add(header);
        for (int i = 0; i < cellsList.size(); i++) {
            output.add(cellsList.get(i).toString());
        }
        try {
            Files.write(Paths.get(correctedAbsolutePathAndName), output);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


}
