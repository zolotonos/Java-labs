public enum CardCategory {
    PUPIL("Учнівська"),
    STUDENT("Студентська"),
    REGULAR("Звичайна");

    private final String title;

    CardCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}