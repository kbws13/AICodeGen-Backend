package xyz.kbws.aicodegenbackend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import xyz.kbws.aicodegenbackend.annotation.AuthCheck;
import xyz.kbws.aicodegenbackend.common.BaseResponse;
import xyz.kbws.aicodegenbackend.common.DeleteRequest;
import xyz.kbws.aicodegenbackend.common.ResultUtil;
import xyz.kbws.aicodegenbackend.constant.UserConstant;
import xyz.kbws.aicodegenbackend.exception.BusinessException;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;
import xyz.kbws.aicodegenbackend.exception.ThrowUtil;
import xyz.kbws.aicodegenbackend.model.dto.user.*;
import xyz.kbws.aicodegenbackend.model.entity.User;
import xyz.kbws.aicodegenbackend.model.vo.LoginUserVO;
import xyz.kbws.aicodegenbackend.model.vo.UserVO;
import xyz.kbws.aicodegenbackend.service.UserService;

import java.util.List;

/**
 * 用户表 控制层。
 *
 * @author <a href="https://github.com/kbws13">空白无上</a>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册请求
     * @return 用户 ID
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterDto userRegisterDto) {
        ThrowUtil.throwIf(userRegisterDto == null, ErrorCode.PARAMS_ERROR);
        String account = userRegisterDto.getAccount();
        String password = userRegisterDto.getPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        Long res = userService.register(account, password, checkPassword);
        return ResultUtil.success(res);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        ThrowUtil.throwIf(userLoginDto == null, ErrorCode.PARAMS_ERROR);
        String account = userLoginDto.getAccount();
        String password = userLoginDto.getPassword();
        LoginUserVO loginUserVO = userService.login(account, password, request);
        return ResultUtil.success(loginUserVO);
    }

    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtil.success(userService.getLoginUserVO(loginUser));
    }

    @GetMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtil.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.logout(request);
        return ResultUtil.success(result);
    }

    /**
     * 创建用户
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddDto userAddDto) {
        ThrowUtil.throwIf(userAddDto == null, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtil.copyProperties(userAddDto, user);
        // 默认密码 12345678
        final String DEFAULT_PASSWORD = "12345678";
        String encryptPassword = userService.getEncryptPassword(DEFAULT_PASSWORD);
        user.setPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtil.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtil.success(user.getId());
    }

    /**
     * 根据 id 获取用户（仅管理员）
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(long id) {
        ThrowUtil.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtil.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtil.success(user);
    }

    /**
     * 根据 id 获取包装类
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id) {
        BaseResponse<User> response = getUserById(id);
        User user = response.getData();
        return ResultUtil.success(userService.getUserVO(user));
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtil.success(b);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        if (userUpdateDto == null || userUpdateDto.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtil.copyProperties(userUpdateDto, user);
        boolean result = userService.updateById(user);
        ThrowUtil.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtil.success(true);
    }

    /**
     * 分页获取用户封装列表（仅管理员）
     *
     * @param userQueryDto 查询请求参数
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryDto userQueryDto) {
        ThrowUtil.throwIf(userQueryDto == null, ErrorCode.PARAMS_ERROR);
        long pageNum = userQueryDto.getPageNum();
        long pageSize = userQueryDto.getPageSize();
        Page<User> userPage = userService.page(Page.of(pageNum, pageSize),
                userService.getQueryWrapper(userQueryDto));
        // 数据脱敏
        Page<UserVO> userVOPage = new Page<>(pageNum, pageSize, userPage.getTotalRow());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtil.success(userVOPage);
    }


}
