package com.lanou.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by dllo on 17/12/1.
 */
public class MyRealm extends AuthorizingRealm{

    /*自定义Realm
    *   系统提供了Realm接口, 但是常用继承AuthorizingRealm
    *   因为同时提供了授权和认证方法
    * */

    /**
     * 方便缓存处理
     * @return
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * 支持哪种token类型
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        /*获得用户此次输入的用户名*/
        String username = (String) authenticationToken.getPrincipal();

        // TODO: 此处应去数据库查询是否存在

        //----模拟 start----
        if (!"wangwu".equals(username)){
           throw new UnknownAccountException("用户名不存在!");
        }
        //----模拟 over----

        /*获得用户此次输入的密码*/
        String password = new String((char[]) authenticationToken.getCredentials());

        // TODO: 此处应去数据库查询是否存在

        //----模拟 start----
        if (!"1234".equals(password)){
            throw new IncorrectCredentialsException("密码错误!");
        }
        //----模拟 over----


        /*返回认证成功信息*/
        return new SimpleAuthenticationInfo(username,password,getName());
    }





}
