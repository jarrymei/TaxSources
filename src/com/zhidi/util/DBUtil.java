package com.zhidi.util;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtil {
	/**
	 * ����������Ҫ�Ĳ�����������url���˺ţ�����
	 * @param sql ��Ҫִ�е�sql���
	 * @return
	 */
	private static String driverClass = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";
	/**
	 * �ھ�̬������м��أ���ȡ����
	 */
	static{
		Properties prop = new Properties();
		try {
			prop.load(DBUtil.class.getResourceAsStream("/jdbc.properties")); 
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName(driverClass);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return �������Ӷ���
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �رղ���
	 * @param rs ��ѯ�Ľ����
	 * @param stat ��ѯ������
	 * @param conn ��ѯ�����Ӷ���
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stat!=null){
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * ͳһ�Ĳ�ѯ���
	 * @sql sqlִ�е�sqlԤ�������
	 * @args args�ɱ����
	 * @return ��������ص�list����
	 */
	public static List<Map<String, String>> query(String sql , Object...args){
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if(args!=null&&args.length>0){
				for(int i = 0; i<args.length;i++){
					ps.setObject(i+1, args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()){
				Map<String, String> rowsResult = new HashMap<String, String>();
				for(int i = 0 ; i<count ; i++){
					String columnLabel = rsmd.getColumnLabel(i+1);
					rowsResult.put(columnLabel, rs.getString(columnLabel));
				}
				result.add(rowsResult);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		return result;
	}
	
	/**
	 * ����id��ѯָ����¼
	 * @param sql ִ�е�sql���
	 * @param row ָ��id
	 * @return ���ؼ�¼���
	 */
	public static Map<String,String> queryRow(String sql,int row){
		List<Map<String, String>> result = query(sql, row);
		if(result.size()==1){
			return result.get(0);
		}
		return null;
	}
	/**
	 * ����Ψһ�ֶβ�ѯĳһ���Ƿ���ڣ���ѯusername�Ƿ����
	 * @param sql ��Ҫִ�е�sql���
	 * @param row ָ��Ψһ�ֶ�
	 * @return �����Ƿ�������ֶ�
	 */
	public static boolean queryExist(String sql,String row){
		List<Map<String, String>> result = query(sql, row);
		if(result.size()==1){
			return true;
		}
		return false;
	}
	/**
	 * ���²��� ����sql���
	 * @param sql sql���
	 * @param args ���������
	 * @return ������Ӱ������
	 */
	public static int update(String sql,Object...args){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int rows = 0;
		try {
				ps = conn.prepareStatement(sql);
				if(args!=null&&args.length>0){
					for(int i = 0; i<args.length;i++){
						System.out.println(args[i]);
						ps.setObject(i+1, args[i]);
					}
				}
				rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(null,ps,conn);
		}
		return rows;
	}
	
	/**
	 * ��Ӳ��� ���ݶ������
	 * @param obj ��Ҫ��ӵ����ݿ��Ӧ�Ķ���
	 * @param table ����
	 * @return ������Ӱ�������
	 */
	public static int add(Object obj,String table) {
		Connection conn = getConnection();
		Class<?> cls = obj.getClass();
		Field [] fies = cls.getDeclaredFields();
		Statement st = null;
		StringBuilder sql = new StringBuilder("insert into "+table+"(");
		int rows = 0;
		try {
			st = conn.createStatement();
			for(Field f:fies){
				f.setAccessible(true);
				sql.append(f.getName()+",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") values (");
			for(Field f:fies){
				f.setAccessible(true);
					try {
						if(f.getType()==String.class){
							if(f.get(obj)!=null && StringUtils.isNotEmpty(f.get(obj).toString())){
								sql.append("'"+f.get(obj).toString()+"',");
							}else{
								sql.append("null,");
							}
							
						}else{
							if(f.get(obj)!=null && StringUtils.isNotEmpty(f.get(obj).toString())){
								sql.append(f.get(obj).toString()+",");
							}else{
								sql.append("null,");
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			rows = st.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(null,st,conn);
		}
		return rows;
	}
	
	/**
	 * ��ȡ��Ӽ�¼��id
	 * @param obj ��ӵĶ���
	 * @param table ����
	 * @return ������Ӽ�¼��id��������ʧ�ܷ���-1
	 */
	public static int getAddId(Object obj,String table) {
		Connection conn = getConnection();
		Class<?> cls = obj.getClass();
		Field [] fies = cls.getDeclaredFields();
		Statement st = null;
		StringBuilder sql = new StringBuilder("insert into "+table+"(");
		int rows = 0;
		int id = -1;
		try {
			st = conn.createStatement();
			for(Field f:fies){
				f.setAccessible(true);
				sql.append(f.getName()+",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") values (");
			for(Field f:fies){
				f.setAccessible(true);
					try {
						if(f.getType()==String.class){
							if(f.get(obj)!=null){
								sql.append("'"+f.get(obj).toString()+"',");
							}else{
								sql.append("null,");
							}
							
						}else{
							if(f.get(obj)!=null){
								sql.append(f.get(obj).toString()+",");
							}else{
								sql.append("null,");
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			rows = st.executeUpdate(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			if(rows>0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					id = rs.getInt(1);
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(null,st,conn);
		}
		return id;
	}
	
	
	/**
	 * ����
	 * @param obj ��Ҫ��ӵĶ���
	 * @param table ����
	 * @return ��Ӱ�������
	 */
	public static int edit(Object obj,String table,Integer id){
		Connection conn = getConnection();
		Class cls = obj.getClass();
		Field [] fies = cls.getDeclaredFields();
		Statement st = null;
		StringBuilder sql = new StringBuilder("update  "+table+" set ");
		int row = 0;
		try {
			st = conn.createStatement();
			for(Field f:fies){
				f.setAccessible(true);
					try {
						if(f.getType()==String.class){
							if(f.get(obj)!=null){
								sql.append(f.getName()+"='"+f.get(obj)+"',");
							}else{
								sql.append(f.getName()+"=null"+",");
							}	
						}else{
								if(f.get(obj)!=null){
									sql.append(f.getName()+"="+f.get(obj)+",");
								}else{
									sql.append(f.getName()+"=null"+",");
								}	
						}
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(" where id = "+id);
			row = st.executeUpdate(sql.toString());			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(null,st,conn);
		}
		return row;
	}	
}
