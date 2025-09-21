package lk.ijse.b72.finalproject.back_end.advisor;

import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHander {


    @ExceptionHandler(Exception.class)
    public ResponceUtil exceptionHandler(Exception ex){

        return new ResponceUtil(500 ,ex.getMessage() ,null);
    }


}
