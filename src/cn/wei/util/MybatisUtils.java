package cn.wei.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum MybatisUtils {
	INSTANCE;
	static Reader reader ;
	static SqlSession session ;
	static{
		reader = null;
		session =null;
	}
	
	public SqlSession getSession(){
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			session = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
		
	}
	
	public void release(){
		if (null != session){
			session.close();
		}
		if (null != reader){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
