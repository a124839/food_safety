package com.ichinait.food.controller.user;

import com.google.common.base.Throwables;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.websocket.SystemWebSocketHandler;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.dto.user.UserDTO;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.service.index.IndexService;
import com.ichinait.food.service.user.UserService;
import com.ichinait.food.shiro.MyRealm;
import com.ichinait.food.util.DigestUtil;
import com.ichinait.food.util.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController{
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private IndexService indexService;

    @Resource
    private UserService userService;

    @Resource
    private MemoryConstrainedCacheManager cacheManager;

    @Autowired
    private SessionDAO sessionDAO;

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam(
            value = "loginAccount", required = true) String account,
                                     @RequestParam(value = "password", required = true) String password, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            MyRealm myRealm = (MyRealm) securityManager.getRealms().iterator().next();
            Cache cache = myRealm.getAuthorizationCache();
            cache.keys().stream().forEach(o -> {
                if(o.toString().equals(account)){
                    logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++清除权限缓存：{}",account);
                    cache.remove(o);
                }
            });
            UsernamePasswordToken token = new UsernamePasswordToken(account, DigestUtil.md5(password));
            SecurityUtils.getSubject().login(token);
            result.put("success", Constant.EXEC_SUCCESS);

            SystemWebSocketHandler webSocket = new SystemWebSocketHandler();
            User user= (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            webSocket.sendMessageToUsers(new TextMessage("用户：" + user.getUserName() + " 已上线！"));
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            result.put("success", Constant.EXEC_FAILED);
            if (e instanceof IncorrectCredentialsException) {
                result.put("errorCode", ErrorMessage.ROLE_ERROR.getCode());
                result.put("errorMessage", ErrorMessage.ROLE_ERROR.getMsg());
            } else {
                result.put("errorCode", ErrorMessage.LOGIN_ERROR.getCode());
                result.put("errorMessage", ErrorMessage.LOGIN_ERROR.getMsg());
            }

        } catch (Exception e) {
            result.put("success", Constant.EXEC_ERROR);
            result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
            result.put("errorMessage", ErrorMessage.SYSTEM_ERROR.getMsg());
            logger.error("系统异常：{}", Throwables.getStackTraceAsString(e));

        }
        return result;
    }


    @RequestMapping("signOut")
    @ResponseBody
    public Map<String, Object> signOut(HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        SecurityUtils.getSubject().logout();
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        request.setAttribute("countProject", indexService.countProject());
        request.setAttribute("countAlgorithm", indexService.countAlgorithm());
        request.setAttribute("countSample", indexService.countSample());
        request.setAttribute("countStandards", indexService.countStandards());
        request.setAttribute("countInstrument", indexService.countInstrument());
        request.setAttribute("countUser", indexService.countUser());
        return "index";
    }


    @RequestMapping(value="reg",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(UserDTO userDto){
        logger.info("=========注册用户:{}===============", JsonMapper.nonEmptyMapper().toJson(userDto));
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            boolean flag = userService.reg(userDto);
            if(flag){
                result.put("success", Constant.EXEC_SUCCESS);
            }else{
                result.put("success", Constant.EXEC_FAILED);
            }
        } catch (Exception e) {
            logger.error("注册用户失败：{}",Throwables.getStackTraceAsString(e));
           result.put("success",Constant.EXEC_ERROR);
        }

        return result;
    }

    /**
     * 验证account是否可以注册
     * @param loginAccount
     * @return
     */
    @RequestMapping(value="validateAccount",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryUserByLoginAccount(String loginAccount){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("=========查询用户信息:{}===============",loginAccount);
        try {
            boolean flag = userService.queryUserByAccount(loginAccount);//true表示已被注册
            result.put("success", Constant.EXEC_SUCCESS);
            result.put("flag", !flag);
        } catch (Exception e) {
            logger.error("查询用户失败：{}",Throwables.getStackTraceAsString(e));
            result.put("success",Constant.EXEC_ERROR);
        }
        return result;
    }


    @RequestMapping("index/onlineUser")
    public @ResponseBody List<User> onlineUser(){
        List<User> users = new ArrayList<User>();
        logger.info("===============查询在线用户信息:===============");
        try {
            Collection<Session> allSession = sessionDAO.getActiveSessions();
            for(Session session : allSession) {
                User sessionUser = (User)session.getAttribute("user");
                if(sessionUser != null){
                	users.add(sessionUser);
                }
            }
        } catch (Exception e) {
            logger.error("查询在线用户失败：{}",Throwables.getStackTraceAsString(e));
            }

        return users;
    }
}
