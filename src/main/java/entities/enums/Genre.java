package entities.enums;

public enum Genre {
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science_fiction"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    HORROR("Horror");
    private final String value;

    Genre(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
