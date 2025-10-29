package pl.kurs.schooldiary.exceptionhanding;

public abstract class AbstractErrorDto {
    private String message;

    public AbstractErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
