import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class TramMain {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CardRegistry registry = new CardRegistry();
        Turnstile turnstile = new Turnstile(registry);

        TravelCard studentMonth = registry.issueTimeCard(CardCategory.STUDENT, PassType.MONTHLY, LocalDate.now().plusDays(20));
        TravelCard expiredPupil = registry.issueTimeCard(CardCategory.PUPIL, PassType.TEN_DAYS, LocalDate.now().minusDays(1));
        TravelCard fiveRides = registry.issueRideCard(CardCategory.REGULAR, PassType.FIVE_RIDES);
        TravelCard accumulative = registry.issueAccumulativeCard(CardCategory.REGULAR, 12.0);

        try {
            registry.issueAccumulativeCard(CardCategory.STUDENT, 50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Перевірка випуску: " + e.getMessage());
        }

        System.out.println("\n=== Перевірка проходів через турнікет ===");
        turnstile.processPass(studentMonth);
        turnstile.processPass(expiredPupil);
        turnstile.processPass(fiveRides);
        turnstile.processPass(accumulative);
        turnstile.processPass(accumulative);

        accumulative.addBalance(20.0);
        System.out.println("Поповнено накопичувальну картку на 20.0 грн.");
        turnstile.processPass(accumulative);

        TravelCard brokenCard = registry.issueRideCard(CardCategory.PUPIL, PassType.TEN_RIDES);
        brokenCard.setReadable(false);
        turnstile.processPass(brokenCard);
        turnstile.processPass(null);

        turnstile.printTotalStats();
        turnstile.printStatsByType();
    }
}