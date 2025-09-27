package xyz.kbws.aicodegenbackend.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import xyz.kbws.aicodegenbackend.model.dto.user.UserQueryDto;
import xyz.kbws.aicodegenbackend.model.entity.User;
import xyz.kbws.aicodegenbackend.model.vo.LoginUserVO;
import xyz.kbws.aicodegenbackend.model.vo.UserVO;

import java.util.List;

/**
 * 用户表 服务层。
 *
 * @author <a href="https://github.com/kbws13">空白无上</a>
 */
public interface UserService extends IService<User> {

    Long register(String account, String password, String checkPassword);
    
    LoginUserVO login(String account, String password, HttpServletRequest request);

    String getEncryptPassword(String password);
    
    LoginUserVO getLoginUserVO(User user);
    
    User getLoginUser(HttpServletRequest request);

    Boolean logout(HttpServletRequest request);

    UserVO getUserVO(User user);
    
    List<UserVO> getUserVOList(List<User> userList);

    QueryWrapper getQueryWrapper(UserQueryDto userQueryDto);
}
