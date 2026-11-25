import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

            if ("1".equals(choice)) {
                journal.add(readRecord(scanner));
                System.out.println("Запис додано.");
            } else if ("2".equals(choice)) {
                if (journal.isEmpty()) {
                    System.out.println("Журнал порожній.");
                } else {
                    for (int i = 0; i < journal.size(); i++) {
                        System.out.println((i + 1) + ". " + journal.get(i));
                    }
                }
            } else if ("0".equals(choice)) {
                break;
            } else {
                System.out.println("Невірний вибір.");
            }
        }
    }

    private static CuratorRecord readRecord(Scanner scanner) {
        System.out.print("Прізвище: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Ім'я: ");
        String firstName = scanner.nextLine().trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.print("Дата народження (дд.мм.рррр): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine().trim(), formatter);

        System.out.print("Телефон: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Вулиця: ");
        String street = scanner.nextLine().trim();

        System.out.print("Будинок: ");
        String house = scanner.nextLine().trim();

        System.out.print("Квартира: ");
        int apartment = Integer.parseInt(scanner.nextLine().trim());

        return new CuratorRecord(lastName, firstName, birthDate, phone, new Address(street, house, apartment));
    }
}