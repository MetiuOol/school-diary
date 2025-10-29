package pl.kurs.schooldiary.exceptionhanding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.schooldiary.exceptions.IdException;
import pl.kurs.schooldiary.exceptions.IdNotMatchException;
import pl.kurs.schooldiary.exceptions.IllegalEntityStateException;
import pl.kurs.schooldiary.exceptions.ProvidedNullIdException;
import pl.kurs.schooldiary.exceptions.RequestedEntityNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestedEntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleRequestedEntityNotFoundException(RequestedEntityNotFoundException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(new ErrorWithEntityTypeDto(e.getMessage(), e.getClass().getSimpleName(), e.getEntityType().getSimpleName())),
                "NOT_FOUND",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDto);
    }

    @ExceptionHandler({IdNotMatchException.class, ProvidedNullIdException.class})
    public ResponseEntity<ExceptionResponseDto> handleIdException(IdException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(new ErrorWithEntityTypeDto(e.getMessage(), e.getClass().getSimpleName(), e.getEntityType().getSimpleName())),
                "BAD_REQUEST",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }
    @ExceptionHandler(IllegalEntityStateException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalEntityStateException(IllegalEntityStateException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(new ErrorWithEntityTypeDto(e.getMessage(), e.getClass().getSimpleName(), e.getEntityType().getSimpleName())),
                "BAD_REQUEST",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(new SimpleErrorDto(e.getMessage(), e.getClass().getSimpleName())),
                "BAD_REQUEST",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }

}



