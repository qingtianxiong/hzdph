package com.fh.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fh.controller.system.login.LoginController;
import com.fh.dao.ConnectionDao;

public class ConnectionUtil {

	
	public Map<String, Object> get_select_one(final String sql,final Object[] objs) throws InterruptedException, ExecutionException {
		
		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1000); // 绾跨▼
		Future<Map<String, Object>> future = threadpool.submit(new Callable<Map<String, Object>>() {
			@Override
			public Map<String, Object> call() throws Exception {

				Map<String, Object> map = new HashMap<String, Object>();
				ConnectionDao Connection = new ConnectionDao();
				
				try {
					List<Map<String, Object>> queryObjs = Connection.QueryObjs(WX_Parameter.sqlIP, WX_Parameter.sqlDK, WX_Parameter.database, WX_Parameter.username, WX_Parameter.password, sql, objs);
					for (Map<String, Object> map1 : queryObjs) {
						map = map1;
					}

				} catch (SQLException e) {
				 
					e.printStackTrace();
				}
				return map;
			}
		});
		return future.get();
	}

	
	public List<Map<String, Object>> get_select_list(final String sql,final Object[] objs)
			throws InterruptedException, ExecutionException {

		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1000); // 绾跨▼

		Future<List<Map<String, Object>>> future = threadpool.submit(new Callable<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> call() throws Exception {
				List<Map<String, Object>> queryObjs = null;
			 
				ConnectionDao Connection = new ConnectionDao();
				try {
					queryObjs = Connection.QueryObjs(WX_Parameter.sqlIP, WX_Parameter.sqlDK, WX_Parameter.database, WX_Parameter.username, WX_Parameter.password, sql, objs);
				} catch (SQLException e) {
				 
					e.printStackTrace();
				}
				return queryObjs;
			}

		});
		return future.get();

	}

	
	public boolean get_update(final String sql,final Object[] objs)
			throws InterruptedException, ExecutionException {

		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1000); // 绾跨▼

		Future<Boolean> future = threadpool.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {

				boolean s;
				ConnectionDao Connection = new ConnectionDao();
				try {
					Connection.update(WX_Parameter.sqlIP, WX_Parameter.sqlDK, WX_Parameter.database, WX_Parameter.username, WX_Parameter.password, sql, objs);
					s = true;
				} catch (SQLException e) {
					s = false;
					e.printStackTrace();
				}
				return s;
			}
		});
		return future.get();
	}
	
	public boolean get_sava(final String sql,final Object[] objs)
			throws InterruptedException, ExecutionException {

		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1000); // 绾跨▼

		Future<Boolean> future = threadpool.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {

				boolean s;
				ConnectionDao Connection = new ConnectionDao();
				try {
					Connection.save(WX_Parameter.sqlIP, WX_Parameter.sqlDK, WX_Parameter.database, WX_Parameter.username, WX_Parameter.password, sql, objs);
					s = true;
				} catch (SQLException e) {
					s = false;
					e.printStackTrace();
				}
				return s;
			}
		});
		return future.get();
	}
	
	
	public boolean get_delete(final String sql,final Object[] objs)
			throws InterruptedException, ExecutionException {

		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1000); // 绾跨▼

		Future<Boolean> future = threadpool.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {

				boolean s;
				ConnectionDao Connection = new ConnectionDao();
				try {
					Connection.delete(WX_Parameter.sqlIP, WX_Parameter.sqlDK, WX_Parameter.database, WX_Parameter.username, WX_Parameter.password, sql, objs);
					s = true;
				} catch (SQLException e) {
					s = false;
					e.printStackTrace();
				}
				return s;
			}
		});
		return future.get();
	}
	
	
	public String get_Sql_Name(){
		LoginController lg = new LoginController();
		Map<String, String> data = null;
		try {
			data = (Map<String, String>) lg.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String database = data.get("database");
		
		return database;
	}
}
