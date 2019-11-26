package com.fh.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fh.util.PageData;

@Repository("daoSupport")
//DAO类
public class DaoSupport implements DAO {

	@Resource(name = "sqlSessionTemplate")
     private SqlSessionTemplate sqlSessionTemplate;    
	                  
 
	 
 
	@Resource(name = "r13SqlSession")
	private SqlSessionTemplate r13SqlSession;

	private String r13DataSourceName = "R13"; // 重载时R13名称。用于判断

	/**
	 * 保存对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object save(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}

	public Object save(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.insert(str, obj);
		} else {
			return save(str, obj);
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchSave(String str, List objs) throws Exception {
		return sqlSessionTemplate.insert(str, objs);
	}
	
	public Object batchSave(String ds, String str, List objs) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.insert(str, objs);
		} else {
			return batchSave(str, objs);
		}
	}

	/**
	 * 修改对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}
	
	public Object update(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.update(str, obj);
		} else {
			return update(str, obj);
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchUpdate(String str, List<PageData> objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// 批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					sqlSession.insert(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
	}
	
	public void batchUpdate(String ds, String str, List objs) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			SqlSessionFactory sqlSessionFactory = r13SqlSession.getSqlSessionFactory();
			// 批量执行器
			SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			try {
				if (objs != null) {
					for (int i = 0, size = objs.size(); i < size; i++) {
						sqlSession.update(str, objs.get(i));
					}
					sqlSession.flushStatements();
					sqlSession.commit();
					sqlSession.clearCache();
				}
			} finally {
				sqlSession.close();
			}
		} else {
			batchUpdate(str,objs);
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchDelete(String str, List objs) throws Exception {
		return sqlSessionTemplate.delete(str, objs);
	}
	
	public Object batchDelete(String ds, String str, List objs) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.delete(str, objs);
		} else {
			return batchDelete(str, objs);
		}
	}

	/**
	 * 删除对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}
 
	public Object delete(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.delete(str, obj);
		} else {
			return delete(str, obj);
		}
	}

	/**
	 * 查找对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}
  
	public Object findForObject(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.selectOne(str, obj);
		} else {
			return findForObject(str, obj);
		}
	}

	/**
	 * 查找对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}
 
	public Object findForList(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.selectList(str, obj);
		} else {
			return findForList(str, obj);
		}
	}

	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
		return sqlSessionTemplate.selectMap(str, obj, key);
	}
 
	public Object findForMap(String ds, String str, Object obj, String key, String value) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.selectMap(str, obj, key);
		} else {
			return findForMap(str, obj, key, null);
		}
	}
	
	/**
	 * 批量导入
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchImport(String str,ArrayList<PageData> objs) throws Exception {
		Long start = System.currentTimeMillis();
		
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// 批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		/*//从池中获取连接
		Connection conn = (Connection)sqlSession.getConnection();
		Statement stmt = (Statement)conn.createStatement();

		for (int i = 0; i < objs.size(); i++) {
			//加入批处理
			stmt.addBatch(objs.get(i).toString());
		}
		stmt.executeBatch(); //执行批处理
		stmt.close();
		//sqlSession.freeConnection(conn); //连接归池
		sqlSession.close();//连接归池
		*/
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					
					sqlSession.insert(str,objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
		Long end = System.currentTimeMillis();
		System.out.println("批量执行" + objs.size() + "条Insert操作，共耗时：" + (end - start) / 1000f + "秒！"); 

	}
	
	
	/**
	 * 执行存储过程
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void getAllFaxTaskCallable(String str, PageData pd) throws Exception {
		 sqlSessionTemplate.selectOne(str, pd);
	}
	public Object getAllFaxTaskCallable(String ds, String str, Object obj) throws Exception {
		if (r13DataSourceName.equals(ds.trim())) {
			return r13SqlSession.selectOne(str, obj);
		} else {
			return findForObject(str, obj);
		}
	}
	
	
}
