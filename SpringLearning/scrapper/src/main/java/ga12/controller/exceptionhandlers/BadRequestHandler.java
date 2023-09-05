package ga12.controller.exceptionhandlers;

import ga12.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class BadRequestHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse serverError(Exception ex){
        return new ApiErrorResponse("Некорректные параметры запроса", HttpStatus.BAD_REQUEST.toString(), ex);
    }
}
