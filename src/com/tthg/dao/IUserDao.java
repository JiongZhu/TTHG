package com.tthg.dao;

import java.util.List;

import com.tthg.entity.User;
//用户接口
public interface IUserDao {
	//经理通过权限查询出自己之外的所有用户
	public List<User> getUserByPower(String url);
	//通过用户id删除相对应得用户信息，并返回布尔类型信息
	public boolean deleteUser(int[] ids);
	//通过用户编号查询用户信息，一次来判断改用户是否存在
	//添加用户和删除用户是会用到
	public List<User> getUserByNo(String no);
	//通过用户id查询用户信息
	public List<User> getUserById(Integer id);
	//修改用户信息，因为一对多关系，所以传递参数应包含
	//需要修改的部门id，以此来修改部门与员工的关系
	public boolean updateUser(Integer dmid,User user);
	//添加用户，因为一对多关系，所以传递参数不仅包含用户对象
	//还应包含需添加的部门id，以此来确定相应的部门与员工的关系
	public boolean insertUser(User user,Integer dmid);
	//复合查询
	public List getUserByComposition(User user);
	//根据用户名及登录密码查找用户
	public User getUserByNameAndPwd(User user);
}
