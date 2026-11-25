import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        List<CuratorRecord> journal = new ArrayList<>();

        while (true) {
            System.out.println("\n1 - Додати запис у журнал");
            System.out.println("2 - Показати всі записи");
            System.out.println("0 - Вихід");
            System.out.print("Вибір: ");
            String choice = scanner.nextLine().trim();

            if ("1".equals(choice)) {
                journal.add(readRecord(scanner));
                System.out.println("Запис успішно додано.");
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
        String lastName = promptUntilValid(
                scanner,
                "Прізвище (з великої літери): ",
                "Некоректне прізвище. Спробуйте ще раз.",
                s -> s.matches("^[А-ЯІЇЄҐA-Z][а-яіїєґa-zА-ЯІЇЄҐA-Z'-]{1,49}$")
        );

        String firstName = promptUntilValid(
                scanner,
                "Ім'я (з великої літери): ",
                "Некоректне ім'я. Спробуйте ще раз.",
                s -> s.matches("^[А-ЯІЇЄҐA-Z][а-яіїєґa-zА-ЯІЇЄҐA-Z'-]{1,49}$")
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        String birthDateRaw = promptUntilValid(
                scanner,
                "Дата народження (дд.мм.рррр): ",
                "Некоректна дата. Введіть у форматі дд.мм.рррр.",
                s -> {
                    try {
                        LocalDate date = LocalDate.parse(s, formatter);
                        return !date.isBefore(LocalDate.of(1950, 1, 1)) && !date.isAfter(LocalDate.now());
                    } catch (DateTimeParseException e) {
                        return false;
                    }
                }
        );
        LocalDate birthDate = LocalDate.parse(birthDateRaw, formatter);

        String phone = promptUntilValid(
                scanner,
                "Телефон (+380XXXXXXXXX або 0XXXXXXXXX): ",
                "Некоректний номер телефону.",
                s -> s.matches("^(\\+380|0)\\d{9}$")
        );

        String street = promptUntilValid(
                scanner,
                "Вулиця: ",
                "Некоректна назва вулиці.",
                s -> s.matches("^[А-ЯІЇЄҐA-Zа-яіїєґa-z0-9\\s.-]{2,60}$")
        );

        String house = promptUntilValid(
                scanner,
                "Будинок: ",
                "Некоректний номер будинку.",
                s -> s.matches("^[1-9]\\d{0,3}([/-]?[А-ЯІЇЄҐA-Zа-яіїєґa-z0-9]{1,3})?$")
        );

        String apartmentRaw = promptUntilValid(
                scanner,
                "Квартира: ",
                "Номер квартири має бути числом більше 0.",
                s -> s.matches("^[1-9]\\d{0,4}$")
        );
        int apartment = Integer.parseInt(apartmentRaw);

        return new CuratorRecord(lastName, firstName, birthDate, phone, new Address(street, house, apartment));
    }

    private static String promptUntilValid(Scanner scanner, String prompt, String errorMsg, Predicate<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            }
            System.out.println(errorMsg);
        }
    }
}