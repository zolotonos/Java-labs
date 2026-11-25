public class ShapeView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape[] shapes) {
        for (int i = 0; i < shapes.length; i++) {
            System.out.println((i + 1) + ". " + shapes[i]);
        }
    }

    public void printArea(String description, double area) {
        System.out.printf("%s: %.2f%n", description, area);
    }
}