import java.util.Arrays;
import java.util.Comparator;

public class ShapeModel {
    private final Shape[] shapes;

    public ShapeModel() {
        this.shapes = createPreparedShapes();
    }

    private Shape[] createPreparedShapes() {
        Shape[] prepared = {
            new Rectangle("Червоний", 4.0, 5.0),
            new Circle("Синій", 3.0),
            new Triangle("Зелений", 3.0, 4.0, 5.0),
            new Rectangle("Жовтий", 2.5, 6.0),
            new Circle("Червоний", 2.0),
            new Triangle("Білий", 5.0, 5.0, 6.0),
            new Rectangle("Синій", 7.0, 3.0),
            new Circle("Зелений", 4.5),
            new Triangle("Жовтий", 6.0, 8.0, 10.0),
            new Rectangle("Білий", 3.5, 4.0)
        };
        Shape[] result = new Shape[10];
        for (int i = 0; i < result.length; i++) {
            result[i] = prepared[i % prepared.length];
        }
        return result;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double calcTotalArea() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.calcArea();
        }
        return sum;
    }

    public double calcTotalAreaByType(Class<?> type) {
        double sum = 0;
        for (Shape shape : shapes) {
            if (type.isInstance(shape)) {
                sum += shape.calcArea();
            }
        }
        return sum;
    }

    public void sortByArea() {
        Comparator<Shape> areaComparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.calcArea(), s2.calcArea());
            }
        };
        Arrays.sort(shapes, areaComparator);
    }

    public void sortByColor() {
        Comparator<Shape> colorComparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return s1.getShapeColor().compareTo(s2.getShapeColor());
            }
        };
        Arrays.sort(shapes, colorComparator);
    }
}