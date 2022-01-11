package com.wanxi.exception;
import com.wanxi.tool.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e) {
        logger.info(String.valueOf(e));
        logger.info("............");
        return CommonResult.failed(e.getMessage());
    }
}
