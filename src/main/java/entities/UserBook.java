package entities;

public class UserBook {
    private final Long id;
    private final Long idBook;
    private final Long idUser;

    public UserBook(Long id, Long idBook, Long idUser) {
        this.id = id;
        this.idBook = idBook;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public Long getIdBook() {
        return idBook;
    }

    public Long getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBook that = (UserBook) o;

        if (!id.equals(that.id)) return false;
        if (!idBook.equals(that.idBook)) return false;
        return idUser.equals(that.idUser);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + idBook.hashCode();
        result = 31 * result + idUser.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UsersBooks{" +
                "idBook=" + idBook +
                ", idUser=" + idUser +
                '}';
    }
}
