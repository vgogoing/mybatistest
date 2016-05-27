package cn.wei;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.wei.dao.impl.EmployeeDAOImpl;
import cn.wei.domain.Employee;
import cn.wei.domain.EmployeeQuery;

public class TestQuery {

	@Test
	public void getOne() {
		EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
		Employee em = daoImpl.getById(1L);
		System.out.println(em);
		
	}
	
	@Test
	public void getAll(){
		EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
		List<Employee> list = daoImpl.getEmList();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void insert(){
		EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
		Employee em = new Employee();
		em.setDept("技术部");
		em.setSalary(100D);
		em.setPassword("testpwd");
		em.setName("test2name");
		System.out.println(new Date());
		em.setBornDate(new Date());
		
		daoImpl.save(em);
	}
	
	@Test 
	public void update(){
		EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
		Employee em = daoImpl.getById(1L);
		em.setDept("市场部");
		em.setSalary(100D);
		em.setPassword("updatepwd");
		em.setName("update2name");
		System.out.println(new Date());
		em.setBornDate(new Date());
		
		daoImpl.update(em);
	}
	
	@Test
	public void highSelect(){
		EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
		EmployeeQuery emquery = new EmployeeQuery();
		emquery.setMinSalary(100D);
		List<Employee> list = daoImpl.select(emquery);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
}
