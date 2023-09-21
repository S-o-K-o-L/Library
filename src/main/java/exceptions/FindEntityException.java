package exceptions;

import java.sql.SQLException;

public class FindEntityException extends SQLException {
    public FindEntityException(String reason) {
        super(reason);
    }

    @Override
    public String getMessage() {
        return "Ошибка добавления" + super.getMessage();
    }
}
