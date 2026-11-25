public class Circle extends Shape {
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Малювання кола (радіус=" + radius + ", колір: " + shapeColor + ")");
    }

    @Override
    public String toString() {
        return "Коло [радіус=" + radius + ", " + super.toString() + "]";
    }
}