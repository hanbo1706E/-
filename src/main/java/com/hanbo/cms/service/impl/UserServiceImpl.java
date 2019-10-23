package com.hanbo.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbo.cms.dao.UserMapper;
import com.hanbo.cms.entity.User;
import com.hanbo.cms.service.UserService;
import com.mmcro.utils.Md5Utils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	

	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.findByName(user.getUsername());
		if(existUser!=null) {
			return -1;// 用户已经存在
		}
		//设置密码密文
		user.setPassword(
				Md5Utils.md5(user.getPassword(),user.getUsername()));
		
		return userMapper.add(user);
		
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		// 获取密码密文
		String pwdStr = Md5Utils.md5(user.getPassword(),user.getUsername());
		//根据用户名称查找用户
		User loginUser =  userMapper.findByName(user.getUsername());
		//判断数据库中密码密文与与计算所得的密文是否相同
		if(loginUser!=null && pwdStr.equals(loginUser.getPassword())) {
			//登录成功
			return loginUser;
		}
		//登录失败
		return null;
	}

	@Override
	public boolean checkUserExist(String username) {
		// TODO Auto-generated method stub
		return null!=userMapper.findByName(username);
		
	}

}
