package pl.kurs.schooldiary.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestedEntityNotFoundException extends EntityNotFoundException {

    private Class<?> entityType;

    public RequestedEntityNotFoundException(String message, Class<?> entityType) {
        super(message);
        this.entityType = entityType;
    }

    public Class<?> getEntityType() {
        return entityType;
    }
}
