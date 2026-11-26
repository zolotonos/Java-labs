import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class Turnstile {
    private static final double FARE = 8.0;
    private final CardRegistry registry;
    private int totalAllowed;
    private int totalDenied;
    private final Map<PassType, Integer> allowedByType = new EnumMap<>(PassType.class);
    private final Map<PassType, Integer> deniedByType = new EnumMap<>(PassType.class);

    public Turnstile(CardRegistry registry) {
        this.registry = registry;
        for (PassType type : PassType.values()) {
            allowedByType.put(type, 0);
            deniedByType.put(type, 0);
        }
    }

    public boolean processPass(TravelCard card) {
        if (card == null || !card.isReadable() || !registry.isRegistered(card.getId())) {
            totalDenied++;
            if (card != null && card.getPassType() != null) {
                deniedByType.put(card.getPassType(), deniedByType.get(card.getPassType()) + 1);
            }
            System.out.println("ВІДМОВА: не вдалося зчитати дані картки.");
            return false;
        }

        PassType type = card.getPassType();

        if (type == PassType.MONTHLY || type == PassType.TEN_DAYS) {
            if (LocalDate.now().isAfter(card.getValidUntil())) {
                recordDeny(type);
                System.out.println("ВІДМОВА (" + card + "): термін дії закінчився " + card.getValidUntil());
                return false;
            }
            recordAllow(type);
            System.out.println("ДОЗВІЛ (" + card + "): дійсна до " + card.getValidUntil());
            return true;
        }

        if (type == PassType.FIVE_RIDES || type == PassType.TEN_RIDES) {
            if (card.getRemainingRides() <= 0) {
                recordDeny(type);
                System.out.println("ВІДМОВА (" + card + "): не залишилося поїздок.");
                return false;
            }
            card.decrementRides();
            recordAllow(type);
            System.out.println("ДОЗВІЛ (" + card + "): залишилося поїздок - " + card.getRemainingRides());
            return true;
        }

        if (type == PassType.ACCUMULATIVE) {
            if (card.getBalance() < FARE) {
                recordDeny(type);
                System.out.println("ВІДМОВА (" + card + "): недостатньо коштів (баланс: " + card.getBalance() + " грн).");
                return false;
            }
            card.deductBalance(FARE);
            recordAllow(type);
            System.out.println("ДОЗВІЛ (" + card + "): знято " + FARE + " грн, залишок: " + card.getBalance() + " грн");
            return true;
        }

        recordDeny(type);
        return false;
    }

    private void recordAllow(PassType type) {
        totalAllowed++;
        allowedByType.put(type, allowedByType.get(type) + 1);
    }

    private void recordDeny(PassType type) {
        totalDenied++;
        deniedByType.put(type, deniedByType.get(type) + 1);
    }

    public void printTotalStats() {
        System.out.println("\n=== Сумарна статистика турнікету ===");
        System.out.println("Дозволено проходів: " + totalAllowed);
        System.out.println("Відмовлено у проході: " + totalDenied);
    }

    public void printStatsByType() {
        System.out.println("\n=== Статистика за типами проїзних квитків ===");
        for (PassType type : PassType.values()) {
            System.out.println(type.getTitle() + " -> дозволено: " + allowedByType.get(type) + ", відмов: " + deniedByType.get(type));
        }
    }
}