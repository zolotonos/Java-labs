public class ShapeController {
    private final ShapeModel model;
    private final ShapeView view;

    public ShapeController(ShapeModel model, ShapeView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.printMessage("=== Початковий набір фігур ===");
        view.printShapes(model.getShapes());

        view.printMessage("\n=== Демонстрація draw() ===");
        for (Shape shape : model.getShapes()) {
            shape.draw();
        }

        view.printMessage("\n=== Обчислення площ ===");
        view.printArea("Сумарна площа всіх фігур", model.calcTotalArea());
        view.printArea("Сумарна площа прямокутників", model.calcTotalAreaByType(Rectangle.class));
        view.printArea("Сумарна площа трикутників", model.calcTotalAreaByType(Triangle.class));
        view.printArea("Сумарна площа кіл", model.calcTotalAreaByType(Circle.class));

        view.printMessage("\n=== Сортування за збільшенням площі ===");
        model.sortByArea();
        view.printShapes(model.getShapes());

        view.printMessage("\n=== Сортування за кольором ===");
        model.sortByColor();
        view.printShapes(model.getShapes());
    }
}