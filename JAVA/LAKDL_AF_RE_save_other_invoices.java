//--
//SCREEN NAME:SAVE COLLECTION - OTHER INVOICES
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME: 2006:11:13
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_save_other_invoices extends HttpServlet {
	public ResultSet rs ;		
	Statement stmt;	 			
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();			
			
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			conn.setAutoCommit(false); //added by nuwan de silva on 22-11-2007
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_OTHER_INVOICES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
			
			String m_invoice_type= (String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_TYPE");//Added By Sandun On 24-12-2008
			String m_payee       = (String)m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE");
			String m_acc_type  ="";
			
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_DEBIT_NOTE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // commented by udara 23-07-2014
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_DEBIT_NOTE_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // added by udara 23-07-2014 // commented by udara 06-06-2019
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_DEBIT_NOTE_CONTROL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // added by udara 06-06-2019
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			String val_dd = m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_DD");
			String val_mm = m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_MM");
			String val_yy = m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_YY");
			String val_date = val_dd+"-"+val_mm+"-"+val_yy;
			callstmt.setString(3,val_date);
			
			callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NET_AMOUNT")));
			callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT_AMOUNT")));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT")));
			
			String due_dd = m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_DD");
			String due_mm = m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_MM");
			String due_yy = m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_YY");
			String due_date = "" ;
			if(due_yy != "" && due_mm!="" && due_dd!="" ) {
				due_date = due_dd+"-"+due_mm+"-"+due_yy ;
			}
			
			callstmt.setString(7,due_date);
			//callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_TO_BE_RECEIVED")));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			//callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); //__ added by nuwan de silva on 05-12-2007
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CURRENCY_CODE"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_TYPE"));
			//out.println("type "+m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_TYPE"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(14,m_username);
			callstmt.setString(15,m_scr_name); //______ added by nuwan de silva on 05-12-2007 ___________
			//callstmt.setString(16,m_scr_name); 
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE")); //______ added by nuwan de silva on 05-12-2007 ___________
			
			callstmt.execute();
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 05-12-2007___
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");			
			
			stmt=conn.createStatement();
			
			rs = stmt.executeQuery (" SELECT  ACCOUNT_TYPE "+
				" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
				" WHERE SUB_TYPE_CODE='"+m_invoice_type+"' AND ACTIVE_STATUS='Y' ");
			
			if(rs.next()){
				m_acc_type=rs.getString(1);
			}
			
			if(m_scr_name.equals("AF_RE_OTHER_INVOICES")){//Added by Sandun on 23-09-2009
				if(m_acc_type.equals("L")){
					
					out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_display_other_invoices?inv_type="+m_invoice_type+"&payee="+m_payee+" ';");
				}
				else{
					out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_display_other_invoices';");
				}
			}else{
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Clear_Credit_Bal_Termi_Contracts';");//Added by Sandun on 23-09-2009
			}
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		/*	catch (Exception ex) {
				try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}*/
		
		
		// added by nuwan de silva 05-12-2007
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
		
	}
}
