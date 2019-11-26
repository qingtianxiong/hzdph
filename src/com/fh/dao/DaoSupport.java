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
//DAO��
public class DaoSupport implements DAO {

	@Resource(name = "sqlSessionTemplate")
     private SqlSessionTemplate sqlSessionTemplate;    
	                  
 
	 
 
	@Resource(name = "r13SqlSession")
	private SqlSessionTemplate r13SqlSession;

	private String r13DataSourceName = "R13"; // ����ʱR13���ơ������ж�

	/**
	 * �������
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
	 * ��������
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
	 * �޸Ķ���
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
	 * ��������
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchUpdate(String str, List<PageData> objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// ����ִ����
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
			// ����ִ����
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
	 * ��������
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
	 * ɾ������
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
	 * ���Ҷ���
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
	 * ���Ҷ���
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
	 * ��������
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchImport(String str,ArrayList<PageData> objs) throws Exception {
		Long start = System.currentTimeMillis();
		
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// ����ִ����
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		/*//�ӳ��л�ȡ����
		Connection conn = (Connection)sqlSession.getConnection();
		Statement stmt = (Statement)conn.createStatement();

		for (int i = 0; i < objs.size(); i++) {
			//����������
			stmt.addBatch(objs.get(i).toString());
		}
		stmt.executeBatch(); //ִ��������
		stmt.close();
		//sqlSession.freeConnection(conn); //���ӹ��
		sqlSession.close();//���ӹ��
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
		System.out.println("����ִ��" + objs.size() + "��Insert����������ʱ��" + (end - start) / 1000f + "�룡"); 

	}
	
	
	/**
	 * ִ�д洢����
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
