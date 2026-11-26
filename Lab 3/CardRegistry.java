import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<String, TravelCard> issuedCards = new HashMap<>();
    private int counter = 1000;

    public TravelCard issueTimeCard(CardCategory category, PassType passType, LocalDate validUntil) {
        if (passType != PassType.MONTHLY && passType != PassType.TEN_DAYS) {
            throw new IllegalArgumentException("Невірний тип картки для терміну дії.");
        }
        String id = String.valueOf(++counter);
        TravelCard card = new TravelCard(id, category, passType, validUntil, 0, 0.0);
        issuedCards.put(id, card);
        return card;
    }

    public TravelCard issueRideCard(CardCategory category, PassType passType) {
        int rides;
        if (passType == PassType.FIVE_RIDES) {
            rides = 5;
        } else if (passType == PassType.TEN_RIDES) {
            rides = 10;
        } else {
            throw new IllegalArgumentException("Невірний тип картки для кількості поїздок.");
        }
        String id = String.valueOf(++counter);
        TravelCard card = new TravelCard(id, category, passType, null, rides, 0.0);
        issuedCards.put(id, card);
        return card;
    }

    public TravelCard issueAccumulativeCard(CardCategory category, double initialBalance) {
        if (category != CardCategory.REGULAR) {
            throw new IllegalArgumentException("Накопичувальна картка доступна лише для звичайного типу.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Початковий баланс не може бути від'ємним.");
        }
        String id = String.valueOf(++counter);
        TravelCard card = new TravelCard(id, category, PassType.ACCUMULATIVE, null, 0, initialBalance);
        issuedCards.put(id, card);
        return card;
    }

    public boolean isRegistered(String cardId) {
        return issuedCards.containsKey(cardId);
    }
}