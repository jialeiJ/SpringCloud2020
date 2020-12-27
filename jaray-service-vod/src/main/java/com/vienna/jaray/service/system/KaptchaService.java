package com.vienna.jaray.service.system;

import java.io.IOException;

/**
 * @author Jaray
 * @date 2020年09月10日 22:29
 * @description: 验证码接口类
 */
public interface KaptchaService {
    /**
     * 验证码接口
     * @throws IOException IOException
     */
    void kaptchaImage() throws IOException;
    
    /**
     * 验证码校验
     * @param code 验证码
     * @return
     */
    boolean validDefaultTime(String code);
    
    /**
     * 验证码校验，自定义事件
     * @param code 验证码
     * @return
     */
    boolean validCustomTime(String code);
}
