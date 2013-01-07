package com.amaker.dao;

import com.amaker.bean.User;

public interface UserDao {

	/**
	*检查用户是否存在
	 */
	boolean isLoginIdExists(String loginid);
/***
 * 检查邮箱是否存在
 */
	boolean isEmailExists(String email);

	/**
	 * 注册用户
	 */
	int addUsers(User u);

	/**
	 * 登陆
	 */
	User getUserByIdAndPwd(String loginid, String password);

	/**
     *判断邮箱是否存在
	 */
	boolean isEmailExists(User u);

	/**
	 *判断邮箱是否存在
	 */
	int modifyUserByLoginid(User u);

	/**
	 *判断原密码是否错误
	 */
	boolean isOldPasswordError(String loginid, String oldpwd);


    /**修改密码**/
	int modifyUserPassword(String loginid, String newpwd);
}
