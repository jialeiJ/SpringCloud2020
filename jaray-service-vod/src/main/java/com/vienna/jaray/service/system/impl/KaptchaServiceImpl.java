package com.vienna.jaray.service.system.impl;

import com.baomidou.kaptcha.Kaptcha;
import com.vienna.jaray.service.system.KaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Jaray
 * @date 2020年09月12日 13:58
 * @description: 验证码服务实现类
 */
@Service
public class KaptchaServiceImpl implements KaptchaService {

    @Autowired
    private Kaptcha kaptcha;

    @Override
    public void kaptchaImage() throws IOException {
        kaptcha.render();
    }
    
    @Override
    public boolean validDefaultTime(String code) {
      //default timeout 900 seconds
      return kaptcha.validate(code);
    }

    @Override
    public boolean validCustomTime(String code) {
    	return kaptcha.validate(code, 60);
    }
}
