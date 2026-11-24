import java.time.LocalDate;

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
}