package pl.kurs.schooldiary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalEntityStateException extends IllegalStateException {
    private Class<?> entityType;

    public IllegalEntityStateException(String message, Class<?> entityType) {
        super(message);
        this.entityType = entityType;

    }

    public Class<?> getEntityType() {
        return entityType;
    }
}
