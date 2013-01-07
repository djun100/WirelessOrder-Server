package com.amaker.dao;

import com.amaker.bean.User;

public interface UserDao {

	/**
	*����û��Ƿ����
	 */
	boolean isLoginIdExists(String loginid);
/***
 * ��������Ƿ����
 */
	boolean isEmailExists(String email);

	/**
	 * ע���û�
	 */
	int addUsers(User u);

	/**
	 * ��½
	 */
	User getUserByIdAndPwd(String loginid, String password);

	/**
     *�ж������Ƿ����
	 */
	boolean isEmailExists(User u);

	/**
	 *�ж������Ƿ����
	 */
	int modifyUserByLoginid(User u);

	/**
	 *�ж�ԭ�����Ƿ����
	 */
	boolean isOldPasswordError(String loginid, String oldpwd);


    /**�޸�����**/
	int modifyUserPassword(String loginid, String newpwd);
}
