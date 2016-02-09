package kr.ac.skhu.drcode.config;

import java.util.Map;

import kr.ac.skhu.drcode.util.JsonUtil;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            Exception.class,
            RuntimeException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String,Object> processExceptionAndRuntimeException(Exception exception, WebRequest request) {
       
        return JsonUtil.putFailJsonContainer(exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());
    }

}
