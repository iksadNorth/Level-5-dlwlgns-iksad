package com.sparta.post.exception;

import com.sparta.post.entity.Message;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public Message handlerIllegalArgumentException(HttpServletResponse response, IllegalArgumentException ex) {
        response.setStatus(400);
        String msg = ex.getMessage();
        return new Message(400, msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Message handlerMethodArgumentNotValidException (HttpServletResponse response, MethodArgumentNotValidException  ex) {
        response.setStatus(400);
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new Message(400, msg);
    }
}
