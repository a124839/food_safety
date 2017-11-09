package com.ichinait.food.shiro;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.UserExample;
import com.ichinait.food.db.mapper.UserMapper;
import com.ichinait.food.db.mapper.UserRoleMappingMapper;
import com.ichinait.food.db.mapper.plus.RoleFunctionMappingMapperPlus;
import com.ichinait.food.db.mapper.plus.RoleMapperPlus;
import com.ichinait.food.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(MyRealm.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapperPlus roleMapperPlus;
	@Resource
	private UserRoleMappingMapper userRoleMappingMapper;
	
	@Resource
	private UserService userService;

    @Resource
	private RoleFunctionMappingMapperPlus roleFunctionMappingMapperPlus;
	@Resource
	private MemoryConstrainedCacheManager cacheManager;

	@Resource
	private SessionDAO sessionDAO;
	/**
	 * 为当前登录的Subject授予角色和权限
	 * 
	 * 经测试:本例中该方法的调用时机为需授权资源被访问时
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = (String)principals.getPrimaryPrincipal();
		logger.debug("====================DEBUG======================为{}设置权限",account);
		UserExample example = new UserExample();
		example.createCriteria().andLoginAccountEqualTo(account).andStatusEqualTo(Constant.DATA_VALID_STATE);
		List<User> list = userMapper.selectByExample(example);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		
//		Role role = roleMapperPlus.selectRoleByUserId(list.get(0).getId());
//		if(role != null){
		//为当前用户设置角色
//			simpleAuthorInfo.addRole(role.getShiroName());
//			SecurityUtils.getSubject().getSession().setAttribute("role", role);
//		}
		// 获取当前用户的授权信息
        logger.info("获取用户( " + list.get(0).getId() + " )的授权信息");
		List<String> authorities = roleFunctionMappingMapperPlus.selectFunctionsByUserId(list.get(0).getId());
		if(authorities.size() > 0){
			simpleAuthorInfo.addStringPermissions(authorities);
			SecurityUtils.getSubject().getSession().setAttribute("authorities", authorities);
		}

		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		try {

			// 获取基于用户名和密码的令牌
			// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
			UsernamePasswordToken token = (UsernamePasswordToken) arg0;
			logger.debug("====================DEBUG======================{}登录",token.getUsername());
			//先清除一下缓存
			Cache cache = cacheManager.getCache(token.getUsername());
			if(cache != null){
				cache.clear();
			}
			User user = userService.login(token.getUsername(), new String(token.getPassword()));
			if(user != null){
				Role role = roleMapperPlus.selectRoleByUserId(user.getId());
				if(role == null)
					return null;

				// 一个帐号只允许在同一个地方登录  addBy xiao 2016-06-29
				Collection<Session> allSession = sessionDAO.getActiveSessions();
				for(Session session : allSession) {
					User sessionUser = (User)session.getAttribute("user");
					if (sessionUser!=null && sessionUser.getLoginAccount().equals(user.getLoginAccount())) {
						session.setTimeout(0);//设置session立即失效，即将其踢出系统
						//System.out.println( session.getId() +"---------------"+ user.getLoginAccount());
						logger.debug("设置session立即失效, 将其提出系统, userId={}, loginAccount={}" + user.getId(), user.getLoginAccount());
						break;
					}
				}
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getLoginAccount(),user.getPassword(),getName());
				SecurityUtils.getSubject().getSession().setAttribute("user", user);
				return authcInfo;
			}
		} catch (InvalidSessionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
		return null;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}


}
