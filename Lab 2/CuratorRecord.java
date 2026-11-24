import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CuratorRecord {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthDate;
    private final String phone;
    private final Address address;

    public CuratorRecord(String lastName, String firstName, LocalDate birthDate, String phone, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return lastName + " " + firstName +
                " | Дата народження: " + birthDate.format(formatter) +
                " | Тел.: " + phone +
                " | Адреса: " + address;
    }
}