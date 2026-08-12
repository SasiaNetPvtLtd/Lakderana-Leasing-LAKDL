package com.sasianet.mobile.smartCollector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 * 
 */

/**
 * @author 			: Kanishka Dilshan
 * @developed on 	: 21-Aug-2017
 */
public class SmartCollectorInvoker {
	
	private static final String SUCCESS = "Success";
	private static final String ERROR 	= "error";
	
	private Connection mainConnection = null;
	private String username             = "";
	private String returnStatus         = "";
	
	public SmartCollectorInvoker(Connection conn,String m_username){
		System.out.println(" TEST KANISHKA SMART COLLECTOR SYNC "+conn.toString());
		mainConnection = conn;
		username       = m_username;
	}
	
	public void processNetAssetSync(String m_collector) throws Exception{
		
		sendEmployeeData();
		sendClientData(m_collector);
		sendInvoiceData(m_collector);
		closeConnection(mainConnection);
		
	}
	
	public void processMobileSync(String m_collector) throws Exception{
		
		downloadReceipts();
		closeConnection(mainConnection);
		
	}
	
	
	private void sendEmployeeData() {
		try {
			
			ServiceGenerateNetAssetData serviceGNAD = new ServiceGenerateNetAssetData(mainConnection);
			String json = serviceGNAD.getEmployeeData();
			//System.out.println(json);
			if(json!= null && !json.equals("NO-DATA")){
				
				try {
					URL url = new URL(serviceGNAD.getSMCServiceURL()+"SMCService/rest/employee/post");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					
					OutputStream os = conn.getOutputStream();
					os.write(json.getBytes());
					os.flush();
					
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						returnStatus = output;
						System.out.println(">>>>**"+output+"**");
					}
					
					if (returnStatus != null && returnStatus.equals(SUCCESS)) {
						// TODO Update SYNC_DATA to Y
						JSONObject myjson    = new JSONObject(json);
						CallableStatement callstmt = mainConnection.prepareCall("BEGIN UPDATE SC_SYNC_EMPLOYEE_DATA SET sync_data = 'Y' WHERE EMP_ID = ? ;  END;");
						
						JSONArray array = myjson.getJSONArray("employee");
						for(int i = 0 ; i < array.length() ; i++){
							//System.out.println("EMP ID = "+array.getJSONObject(i).getString("emp_id"));
							callstmt.setString(1,array.getJSONObject(i).getString("emp_id"));
							callstmt.execute();
						}
						
						mainConnection.commit();
						
						
						
					}else{
						System.out.println("Service Failed...");
					}
					
					conn.disconnect();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}else{
				// No Data To process
				System.out.println("No Employee Details to Sync");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendClientData(String m_collector) {
		try {
			
			ServiceGenerateNetAssetData serviceGNAD = new ServiceGenerateNetAssetData(mainConnection);
			String json = serviceGNAD.getClientData(m_collector);
			//System.out.println(json);
			if(json!= null && !json.equals("NO-DATA")){
				try {
					URL url = new URL(serviceGNAD.getSMCServiceURL()+"SMCService/rest/client/post");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					
					OutputStream os = conn.getOutputStream();
					os.write(json.getBytes());
					os.flush();
					
					BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
					
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						returnStatus = output;
						System.out.println(">>>> " + output);
					}
					
					if (returnStatus != null && returnStatus.toString().equals(SUCCESS)) {
						// TODO Update SYNC_DATA to Y
						JSONObject myjson    = new JSONObject(json);
						CallableStatement callstmt = mainConnection.prepareCall("BEGIN UPDATE SC_SYNC_FINANCE_DATA SET sync_data = 'Y' WHERE FINANCE_NO = ? ; COMMIT; END;");
						
						JSONArray array = myjson.getJSONArray("client");
						for(int i = 0 ; i < array.length() ; i++){
							//System.out.println("EMP ID = "+array.getJSONObject(i).getString("finance_no"));
							callstmt.setString(1,array.getJSONObject(i).getString("finance_no"));
							callstmt.execute();
						}
					}
					
					conn.disconnect();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				// No Data To process
				System.out.println("No Client Details to Sync");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendInvoiceData(String m_collector) {
		try {
			
			ServiceGenerateNetAssetData serviceGNAD = new ServiceGenerateNetAssetData(mainConnection);
			String json = serviceGNAD.getInvoiceData(m_collector);
			//System.out.println(json);
			if(json!= null && !json.equals("NO-DATA")){
				try {
					URL url = new URL(serviceGNAD.getSMCServiceURL()+"SMCService/rest/invoice/post");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					
					OutputStream os = conn.getOutputStream();
					os.write(json.getBytes());
					os.flush();
					
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						returnStatus = output;
						System.out.println(">>>> " + output);
					}
					
					if (returnStatus != null && returnStatus.toString().equals(SUCCESS)) {
						// TODO Update SYNC_DATA to Y
						JSONObject myjson    = new JSONObject(json);
						CallableStatement callstmt = mainConnection.prepareCall("BEGIN UPDATE SC_SYNC_INVOICE_DATA SET sync_data = 'Y' WHERE INVOICE_NO = ? ; COMMIT; END;");
						
						JSONArray array = myjson.getJSONArray("invoice");
						for(int i = 0 ; i < array.length() ; i++){
							//System.out.println("invoice_no = "+array.getJSONObject(i).getString("invoice_no"));
							callstmt.setString(1,array.getJSONObject(i).getString("invoice_no"));
							callstmt.execute();
						}
					}
					
					conn.disconnect();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				// No Data To process
				System.out.println("No Invoice Details to Sync");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void downloadReceipts() throws Exception{
		
		try{
			System.out.println("INIT MOBILE SYNC - Receipts");
			ServiceGenerateNetAssetData serviceGNAD = new ServiceGenerateNetAssetData(mainConnection);
			
			try {
				URL url = new URL(serviceGNAD.getSMCServiceURL()+"SMCService/rest/receipt");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "application/json");
				
				InputStream is = url.openStream();
				
				BufferedReader rd = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));
				
				StringBuilder sb = new StringBuilder();
				int cp;
				while ((cp = rd.read()) != -1) {
					sb.append((char) cp);
				}
				
				System.out.println(sb.toString());
				JSONArray jsonArr = new JSONArray(sb.toString());
				
				String plSql = " BEGIN "+
					" INSERT INTO sc_sync_receipt_data ( "+
					"    client_code, "+
					"    finance_no, "+
					"    settle_mode, "+
					"    cheque_no, "+
					"    cheque_date, "+
					"    rec_no, "+
					"    rec_amount, "+
					"    ent_date, "+
					"    ent_user, "+
					"    sync_date, "+
					"    sync_user "+
					") VALUES ( "+
					"    :v0, "+
					"    :v1, "+
					"    :v2, "+
					"    :v3, "+
					"    :v4, "+
					"    :v5, "+
					"    :v6, "+
					"    :v7, "+
					"    :v8, "+
					"    SYSDATE, "+
					"    :v9 "+
					");"+
					" END; ";
				CallableStatement callstmt = mainConnection.prepareCall(plSql);
				CallableStatement callstmt2 = mainConnection.prepareCall("BEGIN SC_API.generateNetAssetReceipt(?,?,?); END;");
				
				
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					
					String chequeDate 	= jsonObj.getString("chequeDate");
					String recNo 		= jsonObj.getString("recNo");
					String createdDate 	= jsonObj.getString("createdDate");
					
					java.sql.Date chequeSqlDate 	= null;
					java.sql.Date createdDateSqlDate  = null;
					
					if(chequeDate != null && !chequeDate.equals("") && !chequeDate.equals("null")){
						chequeSqlDate = new java.sql.Date(Long.parseLong(chequeDate));
					}
					
					System.out.println("chequeDate  SQL	" + chequeSqlDate);
					
					if(createdDate != null && !createdDate.equals("") && !createdDate.equals("null")){
						createdDateSqlDate = new java.sql.Date(Long.parseLong(createdDate));
					}
					
					System.out.println("createdDate SQL " + createdDateSqlDate);
					
					
					// TODO persit data
					callstmt.setString(1,jsonObj.getString("clientCode"));
					callstmt.setString(2,jsonObj.getString("financeNo"));
					callstmt.setString(3,jsonObj.getString("settleMode"));
					callstmt.setString(4,jsonObj.getString("chequeNo"));
					callstmt.setDate(5,chequeSqlDate);
					callstmt.setString(6,jsonObj.getString("recNo"));
					callstmt.setDouble(7,Double.parseDouble(jsonObj.getString("recAmount")));
					callstmt.setDate(8,createdDateSqlDate);
					callstmt.setString(9,username);
					callstmt.setString(10,jsonObj.getString("createdBy"));
					callstmt.execute();
					
					// TODO update or delete intermediate db receipts and generate NetAsset
					
					//Generate NetAsset Receipt
					callstmt2.setString(1,recNo);
					callstmt2.setString(2,username);
					callstmt2.registerOutParameter(3, java.sql.Types.VARCHAR);
					callstmt2.execute();
					
					if(callstmt2.getString(3) != null && callstmt2.getString(3).equals("SUCCESS")){
						updateReceipts(recNo);
					}
				}
				
				mainConnection.commit();
				try{
					callstmt.close();
					callstmt2.close();
				}catch(Exception ex){
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			throw new Exception("No Service Found "+ex.toString());
		}
	}
	
	private void updateReceipts(String receiptNo)  throws Exception {
		try{
			ServiceGenerateNetAssetData serviceGNAD = new ServiceGenerateNetAssetData(mainConnection);
			
			try {
				URL url = new URL(serviceGNAD.getSMCServiceURL()+"SMCService/rest/receipt/put");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("PUT");
				conn.setRequestProperty("Content-Type", "application/json");
				
				OutputStream os = conn.getOutputStream();
				os.write(receiptNo.getBytes());
				os.flush();
				
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) { 
					returnStatus = output;
					System.out.println(">>>>receipt " + output);
				}
				
				if (returnStatus != null && returnStatus.equals(SUCCESS.toLowerCase())) {
					// TODO Update SYNC_DATA to Y
					CallableStatement callstmt = mainConnection.prepareCall("BEGIN UPDATE sc_sync_receipt_data SET PROCESS_STATUS = 'Y' WHERE REC_NO = ? ; COMMIT; END;");
					callstmt.setString(1,receiptNo);
					callstmt.execute();
					try{callstmt.close();}catch(Exception ex){}
				}
				
				
				conn.disconnect();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			throw new Exception("No Service Found "+ex.toString());
		}
		
	}
	
	
	private void closeConnection(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception sqlEx) {
				conn = null;
			}
		}
	}
	
}
