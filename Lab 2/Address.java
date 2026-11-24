public class Address {
    private final String street;
    private final String house;
    private final int apartment;

    public Address(String street, String house, int apartment) {
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "вул. " + street + ", буд. " + house + ", кв. " + apartment;
    }
}