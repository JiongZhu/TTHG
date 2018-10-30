package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.UserServiceImpl;
import com.tthg.dao.IUserDao;
import com.tthg.entity.User;
import com.tthg.service.IUserService;
//用户服务层接口实现类
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDAO;//用户实现接口
	//get和set方法
	public IUserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	//通过用户id删除相对应得用户信息，并返回布尔类型信息
	public boolean deleteUser(int[] ids) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(ids);
	}

	@Override
	//经理通过权限查询出自己之外的所有用户
	public List<User> getUserByPower(String url) {
		// TODO Auto-generated method stub
		return userDAO.getUserByPower(url);
	}

	@Override
	//通过用户id查询用户信息
	public List<User> getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(id);
	}

	@Override
	//添加用户，因为一对多关系，所以传递参数不仅包含用户对象
	//还应包含需添加的部门id，以此来确定相应的部门与员工的关系
	public boolean insertUser(User user,Integer dmid) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(user, dmid);
	}

	@Override
	//修改用户信息，因为一对多关系，所以传递参数应包含
	//需要修改的部门id，以此来修改部门与员工的关系
	public boolean updateUser(Integer dmid,User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(dmid, user);
	}

	@Override
	//通过用户编号查询用户信息，一次来判断改用户是否存在
	//添加用户和删除用户是会用到
	public List<User> getUserByNo(String no) {
		// TODO Auto-generated method stub
		return userDAO.getUserByNo(no);
	}
	//复合查询
	@Override
	public List getUserByComposition(User user) {
		// TODO Auto-generated method stub
		return userDAO.getUserByComposition(user);
	}

	@Override
	public User getUserByNameAndPwd(User user) {
		return userDAO.getUserByNameAndPwd(user);
	}

}
