package cellAdjustment.model;

import java.util.ArrayList;
import java.util.List;

public class Parameters implements StringListInputNeeded{
    private List<String> input;
    private double[][] fittedParameters;
    public static final String SEPARATOR=";";
    private int numberOfOrderX;
    private int numberOfOrderY;

    @Override
    public void process(String absolutePath,List<String> input) {
        this.input=input;
        defineSizeOfParametersMatrix();
        setParametersMatrix();
    }

    private void setParametersMatrix() {
        for (int i = 0; i < input.size(); i++) {
            String line=input.get(i);
            if(!line.isBlank()) {
                String[] fieldsArray;
                fieldsArray=line.split(SEPARATOR);
                for (int j = 0; j < fieldsArray.length; j++) {
                    fittedParameters[i][j]=Double.parseDouble(fieldsArray[j]);
                }
            }
        }
    }

    private void defineSizeOfParametersMatrix() {
        int y=0;
        int x=0;
        for (int i = 0; i < input.size(); i++) {
            String line=input.get(i);
            if(!line.isBlank()) {
                y++;
                String[] fieldsArray;

                fieldsArray=line.split(SEPARATOR);
                if(y==1){
                    x=fieldsArray.length;
                }
                else{
                    if(x!=fieldsArray.length){
                        throw new IllegalArgumentException("number of parameters are not consistent: "+y+"th row: " + line);
                    }
                }
            }
        }
        numberOfOrderY=y-1;
        numberOfOrderX=x-1;
        fittedParameters=new double[y][x];
    }
    public double getShift(double x, double y){
        double output = 0.0;
        double ytemp;
        for (int polyOrderX = numberOfOrderX; polyOrderX >= 0; --polyOrderX) {
            ytemp = 0.0;
            for (int polyOrderY = numberOfOrderY; polyOrderY >= 0; --polyOrderY) {
                ytemp += fittedParameters[polyOrderY][polyOrderX] * Math.pow(y, polyOrderY);
            }
            output += ytemp * Math.pow(x, polyOrderX);
        }
        return -1.0*output;
    }
    public List<String> getParametersAsStringList(){
        List<String> output = new ArrayList<>();
        StringBuilder row=new StringBuilder();
        for (int polyOrderX = numberOfOrderX; polyOrderX >= 0; --polyOrderX) {
            for (int polyOrderY = numberOfOrderY; polyOrderY >= 0; --polyOrderY) {
                row.append(String.valueOf(fittedParameters[polyOrderY][polyOrderX]));
                if(polyOrderY>0){
                    row.append(";");
                }
            }
            output.add(row.toString());
            row=new StringBuilder();
        }
        return output;
    }
}
