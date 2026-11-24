import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CuratorRecord> journal = new ArrayList<>();

        while (true) {
            System.out.println("\n1 - Додати запис у журнал");
            System.out.println("2 - Показати всі записи");
            System.out.println("0 - Вихід");
            System.out.print("Вибір: ");
            String choice = scanner.nextLine().trim();

            if ("2".equals(choice)) {
                if (journal.isEmpty()) {
                    System.out.println("Журнал порожній.");
                } else {
                    for (int i = 0; i < journal.size(); i++) {
                        System.out.println((i + 1) + ". " + journal.get(i));
                    }
                }
            } else if ("0".equals(choice)) {
                break;
            }
        }
    }
}