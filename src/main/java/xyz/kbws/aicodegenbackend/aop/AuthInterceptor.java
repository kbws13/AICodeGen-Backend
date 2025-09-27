package xyz.kbws.aicodegenbackend.aop;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.kbws.aicodegenbackend.annotation.AuthCheck;
import xyz.kbws.aicodegenbackend.exception.BusinessException;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;
import xyz.kbws.aicodegenbackend.model.entity.User;
import xyz.kbws.aicodegenbackend.model.enums.UserRoleEnum;
import xyz.kbws.aicodegenbackend.service.UserService;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Aspect
@Component
public class AuthInterceptor {
    
    @Resource
    private UserService userService;
    
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return joinPoint.proceed();
    }
}
