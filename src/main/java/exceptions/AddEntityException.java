package exceptions;

import java.sql.SQLException;

public class AddEntityException extends SQLException {
    public AddEntityException(String reason) {
        super(reason);
    }

    @Override
    public String getMessage() {
        return "Ошибка добавления" + super.getMessage();
    }
}
