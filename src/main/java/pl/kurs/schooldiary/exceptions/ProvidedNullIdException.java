package pl.kurs.schooldiary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProvidedNullIdException extends IdException {
    public ProvidedNullIdException(String message, Class<?> entityType) {
        super(message, entityType);
    }
}

