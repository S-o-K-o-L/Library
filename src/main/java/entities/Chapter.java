package entities;

public class Chapter {
    private Long id;
    private String content;
    private Integer number;
    private Long idBook;

    public Chapter(Long id, String content, Integer number, Long idBook) {
        this.id = id;
        this.content = content;
        this.number = number;
        this.idBook = idBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (!id.equals(chapter.id)) return false;
        if (!content.equals(chapter.content)) return false;
        if (!number.equals(chapter.number)) return false;
        return idBook.equals(chapter.idBook);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + idBook.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "content='" + content + '\'' +
                ", number=" + number +
                '}';
    }
}
