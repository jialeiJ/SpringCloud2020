package com.vienna.jaray.service.system.impl;

import com.baomidou.kaptcha.Kaptcha;
import com.google.code.kaptcha.Constants;
import com.vienna.jaray.common.HttpStatus;
import com.vienna.jaray.common.ResponseResult;
import com.vienna.jaray.entity.system.SysUser;
import com.vienna.jaray.entity.system.SysUserToken;
import com.vienna.jaray.mapper.system.SysUserMapper;
import com.vienna.jaray.security.JwtAuthenticatioToken;
import com.vienna.jaray.service.system.KaptchaService;
import com.vienna.jaray.service.system.SystemService;
import com.vienna.jaray.utils.JwtTokenUtil;
import com.vienna.jaray.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jaray
 * @date 2020年09月12日 13:58
 * @description: 系统服务实现类
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KaptchaService kaptchaService;

    @Override
    public ResponseResult login(HttpServletRequest request, String username, String password, String captcha, HttpSession session) {
        ResponseResult result = ResponseResult.fail();
        boolean validResult = kaptchaService.validDefaultTime(captcha);
        
        log.info("用户名[{}], session验证码[{}], 输入验证码[{}]",username, captcha);

        if(!validResult){
            return ResponseResult.fail().setCode(HttpStatus.CAPTCHA_ERROR.getStatusCode()).setMsg(HttpStatus.CAPTCHA_ERROR.getStatusDesc());
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtil.login(request, username, password, authenticationManager);

        SysUser sysUser = sysUserMapper.findByName(username);
        SysUserToken sysUserToken = new SysUserToken();
        if(sysUser != null){
            sysUserToken.setUserId(sysUser.getId());
            sysUserToken.setName(sysUser.getName());
            sysUserToken.setToken(token.getToken());
            sysUserToken.setExpireTime(token.getExpireTime());
            sysUserToken.setCreateBy(sysUser.getCreateBy());
            sysUserToken.setCreateTime(sysUser.getCreateTime());
        }
        if(token.getToken() != null){
            result = ResponseResult.success().add("sysUserToken",sysUserToken);
        }else{
            result.setMsg(HttpStatus.NAME_OR_PASSWORD_ERROR.getStatusDesc()).setCode(HttpStatus.NAME_OR_PASSWORD_ERROR.getStatusCode());
        }
        return result;
    }

    @Override
    public ResponseResult reLogin(HttpServletRequest request, SysUserToken sysUserToken) {
        // 生成令牌并返回给客户端
        String token = JwtTokenUtil.refreshToken(sysUserToken.getToken());
        sysUserToken.setToken(token);
        sysUserToken.setExpireTime(JwtTokenUtil.tokenExpireTime(token));

        return ResponseResult.success().add("sysUserToken",sysUserToken);
    }

    @Override
    public ResponseResult updatePassword(String id, String password) {
        ResponseResult responseResult = ResponseResult.fail();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(passwordEncoder.encode(password));
        int result = sysUserMapper.updateById(sysUser);
        if(result > 0){
            responseResult = ResponseResult.success().add("sysUser", sysUser);
        }
        return responseResult;
    }
}
