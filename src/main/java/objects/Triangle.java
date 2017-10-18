package objects;

public class Triangle {
    private int angle1;
    private int angle2;
    private int angle3;

    public static Triangle create(int angle1, int angle2, int angle3) throws IllegalArgumentException {
        if (angle1 + angle2 + angle3 != 180) {
            throw new IllegalArgumentException("The sum of the angles must be equal to 180");
        }
        return new Triangle(angle1, angle2, angle3);
    }

    private Triangle(int angle1, int angle2, int angle3) {
        this.angle1 = angle1;
        this.angle2 = angle2;
        this.angle3 = angle3;
    }

    public int getAngle1() {
        return angle1;
    }

    public int getAngle2() {
        return angle2;
    }

    public int getAngle3() {
        return angle3;
    }

    @Override
    public String toString() {
        return String.format("Triangle: %d, %d, %d", getAngle1(), getAngle2(), getAngle3());
    }
}
