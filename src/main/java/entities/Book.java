package entities;

import entities.enums.Genre;

import java.util.Objects;
import java.util.Set;

public class Book {
    private Long id;
    private String name;
    private String description;
    private Set<Genre> genres;
    private Set<Chapter> bookChapters;

    public Book(Long id, String name, String description,Set<Genre> genres, Set<Chapter> bookChapters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.bookChapters = bookChapters;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Chapter> getBookChapters() {
        return bookChapters;
    }

    public void setBookChapters(Set<Chapter> bookChapters) {
        this.bookChapters = bookChapters;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!name.equals(book.name)) return false;
        if (!Objects.equals(description, book.description)) return false;
        return bookChapters.equals(book.bookChapters);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + bookChapters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres.size() +
                ", bookChapters=" + bookChapters +
                '}';
    }
}
