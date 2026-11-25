public class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(String shapeColor, double a, double b, double c) {
        super(shapeColor);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calcArea() {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public void draw() {
        System.out.println("Малювання трикутника (" + a + ", " + b + ", " + c + ", колір: " + shapeColor + ")");
    }

    @Override
    public String toString() {
        return "Трикутник [сторони=" + a + ", " + b + ", " + c + ", " + super.toString() + "]";
    }
}