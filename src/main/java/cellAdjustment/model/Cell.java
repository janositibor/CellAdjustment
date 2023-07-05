package cellAdjustment.model;

public class Cell {
    private double volume;
    private double surface;
    private int nbOfObjVoxels;
    private int nbOfSurfVoxels;
    private int intDen;
    private double mean;
    private double stdDev;
    private double median;
    private double min;
    private double max;
    private Coordinates coordinates;
    private double meanDistToSurf;
    private double sdDistToSurf;
    private double medianDistToSurf;
    private double xM;
    private double yM;
    private double zm;
    private int bX;
    private int bY;
    private int bZ;
    private int bWidth;
    private int bHeight;
    private int bDepth;

    public static final String SEPARATOR=",";

    public Cell(double volume, double surface, int nbOfObjVoxels, int nbOfSurfVoxels, int intDen, double mean, double stdDev, double median, double min, double max, double x, double y, double z, double meanDistToSurf, double sdDistToSurf, double medianDistToSurf, double xM, double yM, double zm, int bX, int bY, int bZ, int bWidth, int bHeight, int bDepth) {
        this.volume = volume;
        this.surface = surface;
        this.nbOfObjVoxels = nbOfObjVoxels;
        this.nbOfSurfVoxels = nbOfSurfVoxels;
        this.intDen = intDen;
        this.mean = mean;
        this.stdDev = stdDev;
        this.median = median;
        this.min = min;
        this.max = max;
        coordinates=new Coordinates(x,y,z);
        this.meanDistToSurf = meanDistToSurf;
        this.sdDistToSurf = sdDistToSurf;
        this.medianDistToSurf = medianDistToSurf;
        this.xM = xM;
        this.yM = yM;
        this.zm = zm;
        this.bX = bX;
        this.bY = bY;
        this.bZ = bZ;
        this.bWidth = bWidth;
        this.bHeight = bHeight;
        this.bDepth = bDepth;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return volume +
                SEPARATOR + surface +
                SEPARATOR + nbOfObjVoxels +
                SEPARATOR + nbOfSurfVoxels +
                SEPARATOR + intDen +
                SEPARATOR + mean +
                SEPARATOR + stdDev +
                SEPARATOR + median +
                SEPARATOR + min +
                SEPARATOR + max +
                SEPARATOR + coordinates.toString() +
                SEPARATOR + meanDistToSurf +
                SEPARATOR + sdDistToSurf +
                SEPARATOR + medianDistToSurf +
                SEPARATOR + xM +
                SEPARATOR + yM +
                SEPARATOR + zm +
                SEPARATOR + bX +
                SEPARATOR + bY +
                SEPARATOR + bZ +
                SEPARATOR + bWidth +
                SEPARATOR + bHeight +
                SEPARATOR + bDepth;
    }
}
