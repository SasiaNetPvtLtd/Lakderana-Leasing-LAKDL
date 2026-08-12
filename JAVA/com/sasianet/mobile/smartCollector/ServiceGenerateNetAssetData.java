package com.sasianet.mobile.smartCollector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*; //added by nishantha on 18-07-2018

//import data.DBManager;

/**
 * 
 */

/**
 * @author 			: Kanishka Dilshan
 * @developed on 	: 21-Aug-2017
 */

public class ServiceGenerateNetAssetData {
	
	private Connection mainConnection = null;
	ServiceGenerateNetAssetData(Connection conn){
		this.mainConnection = conn;
	}
	
	public void generateData() throws Exception{
		
		System.out.println("TEST KANI : SMART COLLECTOR - Data Generation ");
		
		Connection        connection 	= mainConnection;
		
		try{
			CallableStatement callstmt = mainConnection.prepareCall("BEGIN   LAKDL.SC_API.generateFinanceData; LAKDL.SC_API.generateFinanceData; LAKDL.SC_API.generateEmployeeData;   END;");
		} catch (Exception sqlEx) {
			System.out.println("TEST KANI : SMART COLLECTOR - Data Generation ERROR "+sqlEx.toString());
			sqlEx.printStackTrace();
		} finally {
			
			
		}
	}
	
	public String getSMCServiceURL() throws Exception{
		
		System.out.println("TEST KANI : SMART COLLECTOR SERVICE INIT ");
		
		Connection        connection 	= mainConnection;
		Statement 		  statement 	= null;
		ResultSet         result   	 	= null;
		String            data 			= null;
		try{
			
			statement = connection.createStatement();
			
			String sql = " SELECT LOWER(SERVICE_PROTOCOL||'://'||SERVICE_ADDRESS||':'||SERVICE_PORT||'/') FROM LAKDL.SC_WEB_SERVICE_DATA";
			result     = statement.executeQuery(sql);
			
			System.out.println(sql);
			
			if(result.next()) {
				data = result.getString(1);
				//System.out.println("TEST KANI : SMART COLLECTOR SERVICE "+data);
			}else{
				//System.out.println("No Service Details Defined for Smart Collector Application.....");
				throw new Exception("No Service Details Defined for Smart Collector Application.....");
			}
			
			return data;
			
		} catch (Exception sqlEx) {
			System.out.println("TEST KANI : SMART COLLECTOR SERVICE ERROR "+sqlEx.toString());
			sqlEx.printStackTrace();
		} finally {
			
			if (result != null) {
				try {
					result.close();
				} catch (Exception sqlEx) {
					result = null;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception sqlEx) {
					statement = null;
				}
			}
			return data;
		}
	}
	
	
	
	public String getEmployeeData() throws Exception {
		String data 				= null;
		Connection connection 		= null;
		PreparedStatement statement = null;
		ResultSet result 			= null;
		String sql 					= null;
		boolean processStatus       = false;
			try {
				connection = mainConnection;
				
				sql = " SELECT * FROM  LAKDL.SC_SYNC_EMPLOYEE_DATA"
					+ " WHERE SYNC_DATA='N' ";
				
				statement = connection.prepareStatement(sql);
				result    = statement.executeQuery();
				
				JSONObject json = new JSONObject();
				
				JSONArray array = new JSONArray();
				while (result.next()) {
					processStatus = true;
					JSONObject item = new JSONObject();
					item.put("emp_id", result.getString("EMP_ID"));
					item.put("emp_name", result.getString("EMPLOYEE_NAME"));
					item.put("designation", result.getString("DESIGNATION"));
					item.put("user_id", result.getString("USER_ID"));
					item.put("password", result.getString("PASSWORD"));
					item.put("device_id", result.getString("IMEI_NO"));
					array.put(item);
					
				}
				json.put("employee", array);
				data = json.toString();
				
			} catch (Exception sqlEx) {
				sqlEx.printStackTrace();
			} finally {
				
				
				if (result != null) {
					try {
						result.close();
					} catch (Exception sqlEx) {
						result = null;
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception sqlEx) {
						statement = null;
					}
				}
			}
		
		if(processStatus){
			return data;
		}else{
			return "NO-DATA";
		}
	}
	
	public String getClientData(String m_collector) throws Exception {
		String data 				= null;
		Connection connection 		= null;
		PreparedStatement statement = null;
		ResultSet result 			= null;
		String sql 					= null;
		boolean processStatus       = false;
		try {
			connection = mainConnection;
			
			if(m_collector != null && !m_collector.equals("")){
				sql = " SELECT * FROM  LAKDL.SC_SYNC_FINANCE_DATA"
					+ " WHERE SYNC_DATA='N' AND collection_officer_id = '"+m_collector+"' ";
			}else{
				sql = " SELECT * FROM  LAKDL.SC_SYNC_FINANCE_DATA"
					+ " WHERE SYNC_DATA='N' ";
			}
			
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();
			
			JSONObject json = new JSONObject();
			
			JSONArray array = new JSONArray();
			while (result.next()) {
				processStatus   = true;
				JSONObject item = new JSONObject();
				item.put("client_code", result.getString("CLIENT_CODE"));
				item.put("client_name", result.getString("CLIENT_NAME"));
				item.put(
					"address1",
					result.getString("ADDRESS_1") != null ? result
					.getString("ADDRESS_1") : "");
				
				item.put(
					"address2",
					result.getString("ADDRESS_2") != null ? result
					.getString("ADDRESS_2") : "");
				
				item.put(
					"city",
					result.getString("CITY") != null ? result
					.getString("CITY") : "");
				item.put(
					"tel_no",
					result.getString("TEL_NO") != null ? result
					.getString("TEL_NO") : "");
				item.put(
					"mobile_no",
					result.getString("MOBILE_NO") != null ? result
					.getString("MOBILE_NO") : "");
				item.put("finance_no", result.getString("FINANCE_NO"));
				item.put("col_officer_id",
					result.getString("COLLECTION_OFFICER_ID"));
				item.put("col_officer",
					result.getString("COLLECTION_OFFICER_NAME"));
				item.put("arrears", result.getString("ARREARS_AMOUNT"));
				item.put("odi", result.getString("ODI_OUTSTANDING"));
				
				array.put(item);
				
			}
			json.put("client", array);
			data = json.toString();
			
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			
			
			if (result != null) {
				try {
					result.close();
				} catch (Exception sqlEx) {
					result = null;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception sqlEx) {
					statement = null;
				}
			}
		}
		
		if(processStatus){
			return data;
		}else{
			return "NO-DATA";
		}
	}
	
	public String getInvoiceData(String m_collector) throws Exception {
		String data 				= null;
		Connection connection 		= null;
		Statement statement   		= null;
		ResultSet result      		= null;
		String sql            		= null;
		boolean processStatus       = false;
		try {
			connection = mainConnection;
			
			sql = " SELECT * FROM  LAKDL.SC_SYNC_INVOICE_DATA"
				+ " WHERE SYNC_DATA='N' "+
				//" AND COLLECTION_OFFICER_ID = '"+m_collector+"' "+
				"";
			
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			JSONObject json = new JSONObject();
			
			JSONArray array = new JSONArray();
			while (result.next()) {
				processStatus   = true;
				JSONObject item = new JSONObject();
				item.put("invoice_no", result.getString("INVOICE_NO"));
				item.put("invoice_type", result.getString("INVOICE_TYPE"));
				item.put("invoice_date", result.getString("INVOICE_DATE"));
				item.put("due_date", result.getString("DUE_DATE"));
				item.put("invoice_amount", result.getString("INVOICE_AMOUNT"));
				item.put("settled_amount", result.getString("SETTLED_AMOUNT"));
				item.put("balance_amount", result.getString("BALANCE_AMOUNT"));
				item.put("finance_no", result.getString("FINANCE_NO"));
				
				array.put(item);
				
			}
			json.put("invoice", array);
			data = json.toString();
			
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			
			if (result != null) {
				try {
					result.close();
				} catch (Exception sqlEx) {
					result = null;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception sqlEx) {
					statement = null;
				}
			}
		}
		
		if(processStatus){
			return data;
		}else{
			return "NO-DATA";
		}
	}
	
}
