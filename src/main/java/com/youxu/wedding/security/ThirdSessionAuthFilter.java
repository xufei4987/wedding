package com.youxu.wedding.security;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.youxu.wedding.mapper.UserMapper;
import com.youxu.wedding.response.RespMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class ThirdSessionAuthFilter extends OncePerRequestFilter {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头部分的Authorization
        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
        //如果请求路径为微信通知后台支付结果则不需要token（之后会在具体的controller中，对双方签名进行验证防钓鱼）
        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (url.equals("/auth") || url.equals("/test")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if (null == authHeader || !authHeader.startsWith("Bearer")) {
            writeException(httpServletResponse,"401","非法访问用户");
            logger.error("非法访问用户");
            return;
        }
        // The part after "Bearer "
        final String thirdSessionId = authHeader.substring(tokenHead.length());
        String wxSession = stringRedisTemplate.opsForValue().get(thirdSessionId);
        if (StringUtils.isEmpty(wxSession)) {
            writeException(httpServletResponse,"402","用户身份已过期");
            logger.error("用户身份已过期");
            return;
        }

        // 设置当前登录用户
        try (AppContext appContext = new AppContext(wxSession.substring(wxSession.indexOf("#") + 1))) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void writeException(HttpServletResponse httpServletResponse, String errorCode, String errorMsg) throws IOException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(400);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONUtil.toJsonStr(new RespMsg(false,errorCode,errorMsg)));
        writer.flush();
        writer.close();
    }
}
