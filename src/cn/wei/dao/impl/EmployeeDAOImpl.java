package cn.wei.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wei.dao.IEmployeeDAO;
import cn.wei.domain.Employee;
import cn.wei.domain.EmployeeQuery;
import cn.wei.domain.PageList;
import cn.wei.util.MybatisUtils;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void save(Employee em) {
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		session.insert("cn.wei.domain.EmployeeMapper.save", em);
		session.commit();
		MybatisUtils.INSTANCE.release();
	}

	@Override
	public void update(Employee em) {
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		session.update("cn.wei.domain.EmployeeMapper.update", em);
		session.commit();
		MybatisUtils.INSTANCE.release();
	}

	@Override
	public Employee getById(Long id) {
		Employee em = null;
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		em = (Employee)session.selectOne("cn.wei.domain.EmployeeMapper.getById",id);
		MybatisUtils.INSTANCE.release();
		return em;
	}

	@Override
	public List<Employee> getEmList() {
		List<Employee> list = null;
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		list = session.selectList("cn.wei.domain.EmployeeMapper.getEmList");
		return list;
	}

	@Override
	public void dalete(Long id) {
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		session.delete("cn.wei.domain.EmployeeMapper.delete", id);
		session.commit();
		MybatisUtils.INSTANCE.release();
	}

	@Override
	public List<Employee> select(EmployeeQuery emquery) {
		Double maxSalary = emquery.getMaxSalary();
		Double minSalary = emquery.getMinSalary();
		String name = emquery.getName();
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		List<Employee> list = session.selectList("cn.wei.domain.EmployeeMapper.select", emquery);
		MybatisUtils.INSTANCE.release();
		return list;
	}

	@Override
	public PageList getPageList(EmployeeQuery emquery) {
		SqlSession session = MybatisUtils.INSTANCE.getSession();
		Integer count = (Integer)session.selectOne("cn.wei.domain.EmployeeMapper.getCount", emquery);
		Integer currentPage = emquery.getCurrentPage();
		Integer pageSize = emquery.getPageSize();
		PageList pageList = new PageList(currentPage, count, pageSize);
		
		List<Employee> selectList = session.selectList("cn.wei.domain.EmployeeMapper.select", emquery);
		pageList.setData(selectList);
		
		MybatisUtils.INSTANCE.release();
		return pageList;
	}

	

}
