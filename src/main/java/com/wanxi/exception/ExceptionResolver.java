package com.wanxi.exception;

import com.wanxi.result.ResultModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionResolver {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultModel error(Exception e) {
        System.out.println(".......");
        return ResultModel.getModel(1, e.getMessage());
    }
}
