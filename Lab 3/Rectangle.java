public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Малювання прямокутника (" + width + "x" + height + ", колір: " + shapeColor + ")");
    }

    @Override
    public String toString() {
        return "Прямокутник [ширина=" + width + ", висота=" + height + ", " + super.toString() + "]";
    }
}