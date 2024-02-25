package br.com.libdolf.starwarsplanetsapi.exceptions.handler;

import br.com.libdolf.starwarsplanetsapi.exceptions.BadRequestException;
import br.com.libdolf.starwarsplanetsapi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Attribute not found!")
                        .details(notFoundException.getMessage())
                        .developerMessage(notFoundException.getClass().getName())
                        .build(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request!")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request!")
                        .details(regex(methodArgumentNotValidException.getMessage()))
                        .developerMessage(methodArgumentNotValidException.getClass().getName())
                        .build(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handlerHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request!")
                        .details(httpMessageNotReadableException.getMessage())
                        .developerMessage(httpMessageNotReadableException.getClass().getName())
                        .build(),HttpStatus.BAD_REQUEST);
    }



    public String regex(String input) {
        String regex = "default message \\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String defaultMessage = null;
        while (matcher.find()) {
            defaultMessage = matcher.group(1);
        }
        return "Default message: " + defaultMessage;
    }

}