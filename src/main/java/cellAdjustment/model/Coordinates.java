package cellAdjustment.model;

public class Coordinates {
    private double x;
    private double y;
    private double z;

    public Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void shiftZ(double shift){
        z+=shift;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return Cell.SEPARATOR + x +
                Cell.SEPARATOR + y +
                Cell.SEPARATOR + z;
    }
}
