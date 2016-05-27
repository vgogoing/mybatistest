package cn.wei.dao;

import java.util.List;

import cn.wei.domain.Employee;
import cn.wei.domain.EmployeeQuery;
import cn.wei.domain.PageList;

public interface IEmployeeDAO {
	void save(Employee em);
	void update(Employee em);
	Employee getById(Long id);
	List<Employee> getEmList();
	void dalete(Long id);
	
	//高级查询 
	List<Employee> select(EmployeeQuery emquery);
	
	//获取一个PageList
	PageList getPageList(EmployeeQuery emquery);
}
