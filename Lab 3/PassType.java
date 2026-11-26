public enum PassType {
    MONTHLY("На місяць"),
    TEN_DAYS("На 10 днів"),
    FIVE_RIDES("На 5 поїздок"),
    TEN_RIDES("На 10 поїздок"),
    ACCUMULATIVE("Накопичувальна");

    private final String title;

    PassType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}