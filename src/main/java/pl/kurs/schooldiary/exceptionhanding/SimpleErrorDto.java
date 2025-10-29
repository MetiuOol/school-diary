package pl.kurs.schooldiary.exceptionhanding;

public class SimpleErrorDto extends AbstractErrorDto {
    private String errorType;

    public SimpleErrorDto(String message, String errorType) {
        super(message);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
