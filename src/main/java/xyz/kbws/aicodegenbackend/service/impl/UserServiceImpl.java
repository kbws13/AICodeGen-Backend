package xyz.kbws.aicodegenbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.kbws.aicodegenbackend.constant.UserConstant;
import xyz.kbws.aicodegenbackend.exception.BusinessException;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;
import xyz.kbws.aicodegenbackend.mapper.UserMapper;
import xyz.kbws.aicodegenbackend.model.dto.user.UserQueryDto;
import xyz.kbws.aicodegenbackend.model.entity.User;
import xyz.kbws.aicodegenbackend.model.enums.UserRoleEnum;
import xyz.kbws.aicodegenbackend.model.vo.LoginUserVO;
import xyz.kbws.aicodegenbackend.model.vo.UserVO;
import xyz.kbws.aicodegenbackend.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表 服务层实现。
 *
 * @author <a href="https://github.com/kbws13">空白无上</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Long register(String userAccount, String userPassword, String checkPassword) {
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }

        String encryptPassword = getEncryptPassword(userPassword);

        User user = new User();
        user.setAccount(userAccount);
        user.setPassword(encryptPassword);
        user.setName("无名");
        user.setRole(UserRoleEnum.USER.getValue());
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return user.getId();
    }

    @Override
    public LoginUserVO login(String account, String password, HttpServletRequest request) {
        // 1. 校验
        if (StrUtil.hasBlank(account, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (account.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = getEncryptPassword(password);
        // 查询用户是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.eq("password", encryptPassword);
        User user = this.mapper.selectOneByQuery(queryWrapper);
        // 用户不存在
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        // 4. 获得脱敏后的用户信息
        return this.getLoginUserVO(user);
    }


    @Override
    public String getEncryptPassword(String password) {
        // 盐值，混淆密码
        final String SALT = "kbws13";
        return DigestUtils.md5DigestAsHex((SALT + password).getBytes());
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (userObj == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper getQueryWrapper(UserQueryDto userQueryDto) {
        if (userQueryDto == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryDto.getId();
        String userAccount = userQueryDto.getAccount();
        String userName = userQueryDto.getName();
        String userProfile = userQueryDto.getProfile();
        String userRole = userQueryDto.getRole();
        String sortField = userQueryDto.getSortField();
        String sortOrder = userQueryDto.getSortOrder();
        return QueryWrapper.create()
                .eq("id", id)
                .eq("role", userRole)
                .like("account", userAccount)
                .like("name", userName)
                .like("profile", userProfile)
                .orderBy(sortField, "asc".equals(sortOrder));
    }

}
