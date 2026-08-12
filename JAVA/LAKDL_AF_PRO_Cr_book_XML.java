//Option Id is 4.2  
//This File was created by SVA on 17-05-2006 
//Collection Invoice
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_PRO_Cr_book_XML extends javax.servlet.http.HttpServlet {
	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		Connection conn = null;
		Statement stmt = null;
		java.text.NumberFormat nf = null;
		ResultSet rs = null;
		 String m_chksql = null;
		ServletOutputStream out = null;
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username			= con_method.username;
			String header_name    = con_method.header_name;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if (m_chksql.trim().equals("get_cr_book_data")) {
				
				String m_branch=req.getParameter("branch");
			    String filter="";
				
				if(!m_branch.equals("")){
				 filter=" AND A.BRANCH_CODE ='"+m_branch+"' "; 
				}
				rs = stmt.executeQuery("SELECT A.FINANCE_NO, " +
										"  A.APPLICATION_NO, " +
										"  NVL(B.REG_NO,'-') REG_NO, " +
										"  B.INVOICE_NO, " +
										//" "+m_schema_name+".AF_CO_GET_CR_BOOK_LABEL(B.INVOICE_NO,A.FINANCE_NO) REG_LABEL, "+ // commented by udara 06-06-2019
										" '-' REG_LABEL, "+ // added by udara 06-06-2019
										//" NVL("+m_schema_name+".GET_APP_REG_STATUS(A.APPLICATION_NO),'-') REG_STATUS, "+ // added by udara 06-06-2019
										" NVL("+m_schema_name+".GET_APP_REG_STATUS(A.APPLICATION_NO),'Unregistered') REG_STATUS, "+ // added by udara 17-06-2019
										" NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC(A.LEAD_SOURCE_CATEGORY),'-') LEAD_SOURCE_DESC, "+ // added by udara 06-06-2019
										" A.LEAD_SOURCE_CATEGORY "+ // added by udara 06-06-2019
										" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, " +
										"  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B " +
										" WHERE A.APPLICATION_NO  =B.APPLICATION_NO " +
										" AND B.ACTIVE_STATUS     ='Y' " +
										" AND A.APPLICATION_STATUS='ACTIVATED' "+
										" AND A.TRANSACTION_TYPE = 'HIREPURCH'   "+ // added by udara 17-06-2019
										//" AND A.ACTIVATED_DATE>='01-APR-2019'"+ // commented by udara 11-06-2019
										" AND A.ACTIVATED_DATE>='01-AUG-2019'"+ // added by udara 12-08-2019
										filter+
										" AND B.INVOICE_NO NOT IN (SELECT PRO_INVOICE_NO FROM "+m_schema_name+".AF_PRO_CR_BOOK WHERE CR_STATUS<>'CANCEL')" );
				boolean more = rs.next();
				if(more){
					out.println("<data>");
					while(more){
						
						out.println("<row>");
						out.println("<financeNo>"+rs.getString("FINANCE_NO")+"</financeNo>");
						out.println("<applicationNo>"+rs.getString("APPLICATION_NO")+"</applicationNo>");
						out.println("<regNo>"+rs.getString("REG_NO")+"</regNo>");
						out.println("<invoiceNo>"+rs.getString("INVOICE_NO")+"</invoiceNo>");
						out.println("<regLabel>"+rs.getString("REG_LABEL")+"</regLabel>");
						out.println("<regStatus>"+rs.getString("REG_STATUS")+"</regStatus>"); // added by udara 06-06-2019
						out.println("<leadSourceDesc>"+rs.getString("LEAD_SOURCE_DESC")+"</leadSourceDesc>"); // added by udara 06-06-2019
						out.println("<leadSourceCat>"+rs.getString("LEAD_SOURCE_CATEGORY")+"</leadSourceCat>"); // added by udara 06-06-2019
						out.println("</row>");
						more = rs.next();	
					}	
					
					out.println("</data>");
					
				}
			}else if (m_chksql.trim().equals("get_cr_book_rmv")) {
				
				String m_branch=req.getParameter("branch");
			    String filter="";
				
				if(!m_branch.equals("")){
				 filter=" AND A.BRANCH_CODE ='"+m_branch+"' "; 
				}
				rs = stmt.executeQuery("SELECT A.FINANCE_NO, " +
										"  A.APPLICATION_NO, " +
										"  NVL(B.REG_NO,'-') REG_NO, " +
										"  B.INVOICE_NO, " +
										"  "+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE) ITEM_SUB_DESC " + // added by udara 21-06-2019
										"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, " +
										"  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B " +
										" WHERE A.APPLICATION_NO  =B.APPLICATION_NO " +
										" AND B.ACTIVE_STATUS     ='Y' " +
										" AND A.APPLICATION_STATUS='ACTIVATED' "+
										filter+
										" AND B.INVOICE_NO IN (SELECT PRO_INVOICE_NO FROM "+m_schema_name+".AF_PRO_CR_BOOK WHERE CR_STATUS='REGISTER' )" );
				boolean more = rs.next();
				if(more){
					
					out.println("<data>");
					while(more){
						
						out.println("<row>");
						out.println("<financeNo>"+rs.getString("FINANCE_NO")+"</financeNo>");
						out.println("<applicationNo>"+rs.getString("APPLICATION_NO")+"</applicationNo>");
						out.println("<regNo>"+rs.getString("REG_NO")+"</regNo>");
						out.println("<invoiceNo>"+rs.getString("INVOICE_NO")+"</invoiceNo>");
						out.println("<itemSubDesc>"+rs.getString("ITEM_SUB_DESC")+"</itemSubDesc>"); // added by udara 21-06-2019
						out.println("</row>");
						more = rs.next();	
					}	
					
					out.println("</data>");
					
				}
			}else if (m_chksql.trim().equals("get_cr_book_safe")) {
				
				String m_branch=req.getParameter("branch");
			    String filter="";
				
				if(!m_branch.equals("")){
				 filter=" AND A.BRANCH_CODE ='"+m_branch+"' "; 
				}
				rs = stmt.executeQuery("SELECT A.FINANCE_NO, " +
										"  A.APPLICATION_NO, " +
										"  NVL(B.REG_NO,'-') REG_NO, " +
										"  B.INVOICE_NO, " +
										"  NVL("+m_schema_name+".GET_APP_REG_STATUS(A.APPLICATION_NO),'Unregistered') REG_STATUS "+ // added by udara 05-08-2019
										"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, " +
										"  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B " +
										" WHERE A.APPLICATION_NO  =B.APPLICATION_NO " +
										" AND B.ACTIVE_STATUS     ='Y' " +
										" AND A.APPLICATION_STATUS='ACTIVATED' "+
										filter+
										" AND B.INVOICE_NO IN (SELECT PRO_INVOICE_NO FROM "+m_schema_name+".AF_PRO_CR_BOOK WHERE CR_STATUS IN ('RMV','REFINANCE') )" );
				boolean more = rs.next();
				if(more){
					
					out.println("<data>");
					while(more){
						
						out.println("<row>");
						out.println("<financeNo>"+rs.getString("FINANCE_NO")+"</financeNo>");
						out.println("<applicationNo>"+rs.getString("APPLICATION_NO")+"</applicationNo>");
						out.println("<regNo>"+rs.getString("REG_NO")+"</regNo>");
						out.println("<invoiceNo>"+rs.getString("INVOICE_NO")+"</invoiceNo>");
						out.println("<regStatus>"+rs.getString("REG_STATUS")+"</regStatus>"); // added by udara 05-08-2019
						out.println("</row>");
						more = rs.next();	
					}	
					
					out.println("</data>");
					
				}
			}else if (m_chksql.trim().equals("get_cr_book_reversal")) {
				
				String m_branch=req.getParameter("branch");
			    String filter="";
				
				if(!m_branch.equals("")){
				 filter=" AND A.FINANCE_NO ='"+m_branch+"' "; 
				}
				rs = stmt.executeQuery("SELECT B.FINANCE_NO FINANCE_NO, " +
										"  B.CR_STATUS CR_STATUS,B.PRO_INVOICE_NO, " +
										"  C.PREVIOUS_STATUS,C.STATUS_ID,C.STATUS_DESC,NVL(B.VEHICLE_NO,'-') VEHICLE_NO,"+
										" NVL((SELECT STATUS_DESC FROM "+m_schema_name+".AF_PRO_CR_BOOK_STATUS WHERE STATUS_ID=C.PREVIOUS_STATUS),'Cancel') PREV_DESC, " +
										"  DECODE(B.CR_STATUS,'REFINANCE','CR book entered','REGISTER','CR book entered',B.CR_STATUS) ACTUAL_DESC "+ // added by udara 27-06-2019
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_PRO_CR_BOOK B,  "+m_schema_name+".AF_PRO_CR_BOOK_STATUS C" +
										"  WHERE A.FINANCE_NO = B.FINANCE_NO AND B.CR_STATUS=C.STATUS_ID "+
										"  AND B.CR_STATUS IN ('REFINANCE','REGISTER','RMV') " +
									    //"  AND B.CR_STATUS ='PLEDGE' " +
										filter+
										" " );
				boolean more = rs.next();
				if(more){
					
					out.println("<data>");
					while(more){
						
						out.println("<row>");
						out.println("<financeNo>"+rs.getString("FINANCE_NO")+"</financeNo>");
						out.println("<vehicleNo>"+rs.getString("VEHICLE_NO")+"</vehicleNo>");
						out.println("<crStatusDesc>"+rs.getString("STATUS_DESC")+"</crStatusDesc>");
						out.println("<crStatusRevDesc>"+rs.getString("PREV_DESC")+"</crStatusRevDesc>");
						out.println("<crStatus>"+rs.getString("CR_STATUS")+"</crStatus>");
						out.println("<crStatusRev>"+rs.getString("PREVIOUS_STATUS")+"</crStatusRev>");
						out.println("<invoiceNo>"+rs.getString("PRO_INVOICE_NO")+"</invoiceNo>");
						out.println("<crActualDesc>"+rs.getString("ACTUAL_DESC")+"</crActualDesc>"); // added by udara 27-06-2019
						out.println("</row>");
						more = rs.next();	
					}	
					
					out.println("</data>");
					
				}

			}
			
			// added by udara 19-07-2019
			else if (m_chksql.trim().equals("get_withdrawal_drop_down")) {				
				
				String withdraw_code = "";
				String withdraw_description = "";
				
				rs = stmt.executeQuery(" "+
					" SELECT CODE,DESCRIPTION  "+
					" FROM  "+m_schema_name+".AF_CR_BOOK_WITHDRAW_REASON "+	
					" ");
				
				while(rs.next()){
					withdraw_code = withdraw_code + "_" + rs.getString(1);
					withdraw_description = withdraw_description + "_" + rs.getString(2);
				}
				
				out.println("<data>");

				out.println("<row>");
				out.println("<code>"+withdraw_code+"</code>");
				out.println("<description>"+withdraw_description+"</description>");
				out.println("</row>");
				out.println("</data>");

			}
			// end by udara 19-07-2019
			
			
			else if (m_chksql.trim().equals("get_cr_book_pledge")) {

				
				
				
				String m_finance=req.getParameter("finance");
				String m_bank=req.getParameter("bank");
				String m_branch=req.getParameter("branch");
				String m_loan=req.getParameter("loan");
				
			    String filter="";
				
				if(!m_finance.equals("")){
				 //filter=" AND A.FINANCE_NO ='"+m_finance+"' "; 
					filter= filter + " AND A.FINANCE_NO ='"+m_finance+"' "; 
				}
				if(!m_bank.equals("")){
				 //filter=" AND (SELECT BANK FROM   "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK WHERE LOAN_ID=B.LOAN_ID)='"+m_bank+"' "; 
					filter= filter + " AND (SELECT BANK FROM   "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK WHERE LOAN_ID=B.LOAN_ID)='"+m_bank+"' "; 
				}
				if(!m_branch.equals("")){
				 //filter=" AND (SELECT BRANCH FROM   "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK WHERE LOAN_ID=B.LOAN_ID)='"+m_branch+"' ";
					filter= filter + " AND (SELECT BRANCH FROM   "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK WHERE LOAN_ID=B.LOAN_ID)='"+m_branch+"' "; 
				}
				if(!m_loan.equals("")){
				 //filter=" AND B.LOAN_ID ='"+m_loan+"' "; 
					filter= filter + " AND B.LOAN_ID ='"+m_loan+"' ";
				}
				rs = stmt.executeQuery("SELECT B.FINANCE_NO FINANCE_NO, " +
										"  B.CR_STATUS CR_STATUS,B.PRO_INVOICE_NO, " +
										"  C.PREVIOUS_STATUS,C.STATUS_ID,C.STATUS_DESC,NVL(B.VEHICLE_NO,'-') VEHICLE_NO,"+
										" NVL((SELECT STATUS_DESC FROM "+m_schema_name+".AF_PRO_CR_BOOK_STATUS WHERE STATUS_ID=C.PREVIOUS_STATUS),'Cancel') PREV_DESC" +
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_PRO_CR_BOOK B,  "+m_schema_name+".AF_PRO_CR_BOOK_STATUS C" +
										"  WHERE A.FINANCE_NO = B.FINANCE_NO AND B.CR_STATUS=C.STATUS_ID "+
										" AND B.CR_STATUS='PLEDGE' "+
										filter+
										" " );
				boolean more = rs.next();
				if(more){
					
					out.println("<data>");
					while(more){
						
						out.println("<row>");
						out.println("<financeNo>"+rs.getString("FINANCE_NO")+"</financeNo>");
						out.println("<vehicleNo>"+rs.getString("VEHICLE_NO")+"</vehicleNo>");
					//	out.println("<crStatusDesc>"+rs.getString("STATUS_DESC")+"</crStatusDesc>");
					//	out.println("<crStatusRevDesc>"+rs.getString("PREV_DESC")+"</crStatusRevDesc>");
						out.println("<crStatus>"+rs.getString("CR_STATUS")+"</crStatus>");
						out.println("<crStatusRev>"+rs.getString("PREVIOUS_STATUS")+"</crStatusRev>");
						out.println("<invoiceNo>"+rs.getString("PRO_INVOICE_NO")+"</invoiceNo>");
						out.println("</row>");
						more = rs.next();	
					}	
					
					out.println("</data>");
					
				}
			}else if (m_chksql.trim().equals("get_batch_no")) {
				
				String m_batch="";
				rs = stmt.executeQuery("SELECT 'CR' " +
										"  ||TO_CHAR(SYSDATE,'YYYY') " +
										"  ||TO_CHAR(SYSDATE,'MM') " +
										"  ||TO_CHAR(SYSDATE,'DD') " +
										"  ||'-' " +
										"  ||LPAD(TO_CHAR(AF_SEQ_CR_BOOK_BACTH.NEXTVAL),4,'0') " +
										"FROM DUAL");
					
				boolean	more = rs.next();
					if(more){
					 m_batch=rs.getString(1);	
					}
					
				out.print(m_batch);
				}
			else if (m_chksql.trim().equals("save_cr_book_entry")) {
				String m_status="";
				CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK(:1,:2,:3,:4,:5,:6,:7); END;");
				try{
				   
				   String chk_status   = req.getParameter("status");
				   String m_finance_no = req.getParameter("financeNo");
				   String m_invoice_no = req.getParameter("invoiceNo");
				   String m_batch_no = req.getParameter("batchNo");
					
				    callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,chk_status);
					callstmt.setString(4,m_batch_no);
					callstmt.setString(5,m_username);
					callstmt.setString(6,"ENTER");
					callstmt.setString(7,"ENTER");
				   callstmt.execute();
			       conn.commit();
				   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
				   out.print(" Data saved "+m_finance_no);
				}catch(Exception e){
				  m_status="An Error Occured "+e.toString();
				  out.print(m_status);		
				}
				
			}else if (m_chksql.trim().equals("save_rmv_status")) {
				String m_status="";
				CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK(:1,:2,:3,:4,:5,:6,:7); END;");
				try{
				   
				   String chk_status   = req.getParameter("status");
				   String m_finance_no = req.getParameter("financeNo");
				   String m_invoice_no = req.getParameter("invoiceNo");
				   String m_batch_no = req.getParameter("batchNo");
					
				    callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,chk_status);
					callstmt.setString(4,m_batch_no);
					callstmt.setString(5,m_username);
					callstmt.setString(6,"RMV");
					callstmt.setString(7,"RMV");
				   callstmt.execute();
			       conn.commit();
				   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
				   out.print(" Data saved "+m_finance_no);
				}catch(Exception e){
				  m_status="An Error Occured "+e.toString();
				  out.print(m_status);		
				}
				
			}else if (m_chksql.trim().equals("save_safe_status")) {
				String m_status="";
				CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK(:1,:2,:3,:4,:5,:6,:7); END;");
				try{
				   
				   String chk_status   = req.getParameter("status");
				   String m_finance_no = req.getParameter("financeNo");
				   String m_invoice_no = req.getParameter("invoiceNo");
				   String m_batch_no = req.getParameter("batchNo");
				   String m_vehicle = req.getParameter("vehicleNo");
					
				    callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,chk_status);
					callstmt.setString(4,m_batch_no);
					callstmt.setString(5,m_username);
					callstmt.setString(6,"SAFE");
					callstmt.setString(7,m_vehicle);
				   callstmt.execute();
			       conn.commit();
				   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
				   out.print(" Data saved "+m_finance_no);
				}catch(Exception e){
				  m_status="An Error Occured "+e.toString();
				  out.print(m_status);		
				}
				
			}else if (m_chksql.trim().equals("save_status_reversal")) {
				String m_status="";
				CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK_STATUS_REV(:1,:2,:3,:4,:5); END;");
				try{
				   
				   String curr_status   = req.getParameter("currentStatus");
				   String m_finance_no = req.getParameter("financeNo");
				   String m_invoice_no = req.getParameter("invoiceNo");
				   String m_prev_status = req.getParameter("prevStatus");
					
				    callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,curr_status);
					callstmt.setString(4,m_prev_status);
					callstmt.setString(5,m_username);
				   callstmt.execute();
			       conn.commit();
				   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
				   out.print(" Data saved "+m_finance_no);
				}catch(Exception e){
				  m_status="An Error Occured "+e.toString();
				  out.print(m_status);		
				}
				
			}else if (m_chksql.trim().equals("save_pledge_reversal")) {
				String m_status="";
				CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK_PLEDGE_REV(:1,:2,:3,:4,:5,:6); END;");
				try{
				   
				   String curr_status   = req.getParameter("currentStatus");
				   String m_finance_no = req.getParameter("financeNo");
				   String m_invoice_no = req.getParameter("invoiceNo");
				   String m_prev_status = req.getParameter("prevStatus");
					String withdrawReason = req.getParameter("withdrawReason"); //  added by udara 19-07-2019
					
				    callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,curr_status);
					callstmt.setString(4,m_prev_status);
					callstmt.setString(5,m_username);
					callstmt.setString(6,withdrawReason); // 6 added by udara 19-07-2019
				   callstmt.execute();
			       conn.commit();
				   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
				   out.print(" Data saved "+m_finance_no);
				}catch(Exception e){
				  m_status="An Error Occured "+e.toString();
				  out.print(m_status);		
				}
				
			}
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}