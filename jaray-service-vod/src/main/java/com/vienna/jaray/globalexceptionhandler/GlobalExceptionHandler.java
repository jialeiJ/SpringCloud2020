package com.vienna.jaray.globalexceptionhandler;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.vienna.jaray.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jaray
 * @date 2020年09月10日 22:29
 * @description: 全局异常处理类
 * 控制器增强类，当控制器发生异常且符合类中定义的拦截异常类，将会被拦截。可以定义拦截的控制器所在的包路径
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value =Exception.class)
    public ResponseResult globalException(HttpServletResponse response, Exception e){
        log.info("GlobalExceptionHandled...{}", e);
        String msg = "GlobalExceptionHandler:"+e.getMessage();
        return ResponseResult.fail().setMsg(msg);
    }
    
    @ResponseBody
    @ExceptionHandler(value = KaptchaException.class)
    public ResponseResult kaptchaExceptionHandler(KaptchaException kaptchaException) {
    	if (kaptchaException instanceof KaptchaIncorrectException) {
    		return ResponseResult.fail().setMsg("验证码不正确");
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
        	return ResponseResult.fail().setMsg("验证码未找到");
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
        	return ResponseResult.fail().setMsg("验证码过期");
        } else {
        	return ResponseResult.fail().setMsg("验证码渲染失败");
        }
    }
}
