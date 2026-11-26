import java.time.LocalDate;

public class TravelCard {
    private final String id;
    private final CardCategory category;
    private final PassType passType;
    private final LocalDate validUntil;
    private int remainingRides;
    private double balance;
    private boolean readable;

    public TravelCard(String id, CardCategory category, PassType passType, LocalDate validUntil, int remainingRides, double balance) {
        this.id = id;
        this.category = category;
        this.passType = passType;
        this.validUntil = validUntil;
        this.remainingRides = remainingRides;
        this.balance = balance;
        this.readable = true;
    }

    public String getId() {
        return id;
    }

    public CardCategory getCategory() {
        return category;
    }

    public PassType getPassType() {
        return passType;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public int getRemainingRides() {
        return remainingRides;
    }

    public void decrementRides() {
        if (remainingRides > 0) {
            remainingRides--;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    @Override
    public String toString() {
        return "Картка #" + id + " [" + category.getTitle() + ", " + passType.getTitle() + "]";
    }
}