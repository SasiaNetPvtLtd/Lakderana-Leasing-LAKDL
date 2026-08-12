// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_MIS_report_generation_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			
			m_msg = "'Information saved successfully'";
			//PDFConversion pdfConversion = new PDFConversion();
			stmt = conn.createStatement();
			
			String file_upload_path             = "D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD\\";
			
			String m_facility_no=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			String m_from_date = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY");
			
			
			String m_to_date = m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_DD")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_MM")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_YY");
			
			String m_today = null;
			rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
			if(rs.next()){
				m_today=rs.getString(1);
			}
			rs.close();
			
			
			String m_filename ="";
			String txtFpath = "";
			String pdfFpath = "";
			String txtFolderpath = "";
			String pdfFolderpath = "";
			
			String content = "";
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_MIS_SAVE_BULK_REPORTS_GEN(:1,:2,:3,:4); END;");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			
			if(m_num>0){
				
				//callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
				for(int i=1;i<=m_num;i++){
					
					String m_status=m_sn_methods.met_formdata(reqstr,"RECEIVED_"+i);
					String m_doc_code=m_sn_methods.met_formdata(reqstr,"REP_ID_"+i);
					
					
					txtFolderpath= file_upload_path + "Client_Statements\\"+m_doc_code+"\\"+m_today+"\\"+m_client_code+"("+m_facility_no+")\\TXT";  
					pdfFolderpath = file_upload_path + "Client_Statements\\"+m_doc_code+"\\"+m_today+"\\"+m_client_code+"("+m_facility_no+")\\PDF";
					
					
					
					if(m_status.equals("on")){
						
						File dir1 = new File(txtFolderpath);
						if (!dir1.exists()) {
							dir1.mkdirs();
						}
						File dir2 = new File(pdfFolderpath);
						if (!dir2.exists()) {
							dir2.mkdirs();
						}
						
						txtFpath = txtFolderpath+"\\"+m_client_code+"-"+m_doc_code+".txt";
						pdfFpath = pdfFolderpath+"\\"+m_client_code+"-"+m_doc_code+".pdf";
						
						/*new FileOutputStream(txtFpath);
						String logFile =txtFpath;*/
						
						
						try{
							LAKDL_MIS_Daily_Reports daily_reports = new LAKDL_MIS_Daily_Reports(); 
							
							daily_reports.Bulk_Gen_Report(req,m_doc_code,m_client_code,m_facility_no,m_from_date,m_to_date,txtFpath,pdfFpath);
							
							
							/*log = new BufferedWriter(new FileWriter(logFile));
							log.write(content);
							log.flush();
							log.close();
							
							
							if (m_doc_code.equals("FA_MIS_CLIENT_SALES_LEG_SUMMARY")) {
								pdfConversion.createPdfLB(txtFpath,pdfFpath);
							}else {
								pdfConversion.createPdfL(txtFpath,pdfFpath);
								
							}*/
							
							
							callstmt.setString(1,m_doc_code);
							callstmt.setString(2,m_client_code);
							callstmt.setString(3,m_facility_no);
							callstmt.setString(4,m_username);
							callstmt.execute();
						}
						catch (Exception ex){
							out.println("err"+ex.toString());
							
							
						}
						
						
					}
				}
			}
			//callstmt.close();
			
			
			conn.commit();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MIS_report_generation';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}

