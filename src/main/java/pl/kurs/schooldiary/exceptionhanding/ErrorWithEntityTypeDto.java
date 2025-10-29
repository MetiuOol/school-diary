package pl.kurs.schooldiary.exceptionhanding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorWithEntityTypeDto extends SimpleErrorDto {
//    private Class<?> entityType; //zamieniliśmy to na String ponieważ Class<?> idkrywa nam szczegóły implementacyjne(nazwy pakietów) w warstwie widoku
    private String entityTypeName;


    public ErrorWithEntityTypeDto(String message, String errorType, String entityTypeName) {
        super(message, errorType);
        this.entityTypeName = entityTypeName;
    }

    public String getEntityType() {
        return entityTypeName;
    }
}
