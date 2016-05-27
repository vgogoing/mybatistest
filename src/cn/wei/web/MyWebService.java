package cn.wei.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.sun.istack.internal.Pool.Impl;

import cn.wei.dao.impl.EmployeeDAOImpl;
import cn.wei.domain.Employee;
import cn.wei.domain.EmployeeQuery;
import cn.wei.domain.PageList;

@WebServlet("/em")
public class MyWebService extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EmployeeDAOImpl daoImpl = new EmployeeDAOImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		System.out.println("req_cmd :"+cmd);
		if ("delete".equals(cmd)){
			this.delete(req, resp);
		}else if ("select".equals(cmd)){
			this.select(req, resp);
		}else if ("editpage".equals(cmd)){
			this.editPage(req, resp);
		}else if ("save".equals(cmd)){
			String id = req.getParameter("id");
			if (StringUtils.isNotBlank(id)){
				this.update(req, resp);
			}else{
				this.save(req, resp);
			}
		}else{
			this.list(req, resp);
		}
		
	}
	
	//高级查询
//	protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("this is select");
//		List<Employee> select_list = daoImpl.select(getEmQuery(req, resp));
//		req.setAttribute("employeeList", select_list);
//		req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
//	}
	//融合
	protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("this is selectALL and page");
		EmployeeQuery emQuery = getEmQuery(req, resp);
		PageList pageList = daoImpl.getPageList(emQuery);
		req.setAttribute("pageList", pageList);
		req.setAttribute("emQuery", emQuery);
		
		req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
	}
	
	public EmployeeQuery getEmQuery(HttpServletRequest req, HttpServletResponse resp){
		EmployeeQuery emquery = new EmployeeQuery();
		String req_name = req.getParameter("name");
		String req_minSalary = req.getParameter("minSalary");
		String req_maxSalary = req.getParameter("maxSalary");
		String req_currentPage = req.getParameter("currentPage");
		if (StringUtils.isNotBlank(req_name)){
			emquery.setName(req_name);
		}
		if (StringUtils.isNotBlank(req_minSalary)){
			emquery.setMinSalary(Double.valueOf(req_minSalary));
		}
		if (StringUtils.isNotBlank(req_maxSalary)){
			emquery.setMaxSalary(Double.valueOf(req_maxSalary));
		}
		
		if (StringUtils.isNotBlank(req_currentPage)){
			emquery.setCurrentPage(Integer.valueOf(req_currentPage));
		}else {
			emquery.setCurrentPage(1);
		}
		return emquery;
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Employee> emList = daoImpl.getEmList();
		req.setAttribute("employeeList", emList);
		req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);;
		
	}
	
	protected void editPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println("id :"+id +"isnotblank :" + StringUtils.isNotBlank(id));
		if(StringUtils.isNotBlank(id)){
			System.out.println("will set employee");
			Employee em = daoImpl.getById(Long.valueOf(id));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String format_date = format.format(em.getBornDate());
			System.out.println("em_get_borndate:"+em.getBornDate());
			System.out.println("formatdate:"+format_date);
			req.setAttribute("employee", em);
		}
		req.getRequestDispatcher("/WEB-INF/views/editpage.jsp").forward(req, resp);
	}
	
	
	protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee em = new Employee();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String salary = req.getParameter("salary");
		String bornDate = req.getParameter("bornDate");
		String dept = req.getParameter("dept");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(bornDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		em.setBornDate(date);
		em.setName(name);
		em.setDept(dept);
		em.setSalary(Double.valueOf(salary));
		em.setPassword(password);
		daoImpl.save(em);
		resp.sendRedirect("/em");
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Employee em = daoImpl.getById(Long.valueOf(id));
		System.out.println("update id:" +em.getId());
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String salary = req.getParameter("salary");
		String bornDate = req.getParameter("bornDate");
		String dept = req.getParameter("dept");
		System.out.println("req_get_bornDate :"+ bornDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(bornDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		em.setBornDate(date);
		em.setName(name);
		em.setDept(dept);
		em.setSalary(Double.valueOf(salary));
		em.setPassword(password);
		daoImpl.update(em);
		resp.sendRedirect("/em");
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println("will delete by id" + id);
		daoImpl.dalete(Long.valueOf(id));
		resp.sendRedirect("/em");		
	}
	
	public Employee getEm(HttpServletRequest req, HttpServletResponse resp){
		Employee em = new Employee();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String salary = req.getParameter("salary");
		String bornDate = req.getParameter("bornDate");
		String dept = req.getParameter("dept");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(bornDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		em.setBornDate(date);
		em.setName(name);
		em.setDept(dept);
		em.setSalary(Double.valueOf(salary));
		em.setPassword(password);
		return em;
	}

}
