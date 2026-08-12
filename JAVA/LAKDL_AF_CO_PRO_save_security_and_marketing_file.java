//--
//SCREEN NAME	:SAVE SECURITY AND MARKETING FILE
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CO_PRO_save_security_and_marketing_file extends HttpServlet {
		
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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int m_chksql_inv=0;
			int m_chksql=0;
			int m_chksql_client=0;
			
			int m_doc;
			String m_request="";
			String m_type="";
			
			m_chksql_inv = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_inv"));
			
			if(m_chksql_inv!=0){
			m_chksql = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));
		
			}
			
			m_chksql_client = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_client"));

			String m_count1 = req.getParameter("m_count");
			

			String m_sc_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_finance=m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO");
	
			

			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SECURY_MOVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(3,"ACTIVATED");
			callstmt.setString(4,"");
			callstmt.setString(5,m_count1);
			callstmt.setString(6,"");
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,Integer.toString(0));
			callstmt.setString(10,"REQ-APP");
			callstmt.setString(11,"AF_CR_SECURITY_MARKETTING_FILE");

			callstmt.execute();
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SECURITY_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
			
			if(m_request.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{
			callstmt.setString(1,m_request);
			}
			
			for (int d=0; d<m_chksql_inv; d++){	
			
			String m_invoice=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_"+d+"");

			for (int j=0; j<=m_chksql; j++) {
			String m_invoice1=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_"+j+"");
			
			
			
			String m_chk=m_sn_methods.met_formdata(reqstr,"chk_app_"+d+"_"+j+"");
			
			String m_doc_code=m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+d+"_"+j+"");
			String m_req_no=m_sn_methods.met_formdata(reqstr,"TXT_REQ");
			
			
			if(m_sc_name.equals("NEW")){
			
				if(m_chk.equals("")){
					m_chk="AVAILABLE";
					}else if(m_chk.equals("Y")){
					m_chk="REQUESTED";
					}else if(m_chk.equals("N")){
					m_chk="AVAILABLE";
					}
			}else if(m_sc_name.equals("EDIT")){
					if(m_chk.equals("")){
					m_chk="REQ-APP";
					}	else if(m_chk.equals("Y")){
					m_chk="RETURNED";
					}
			
					else if(m_chk.equals("N")){
					m_chk="REQ-APP";
					}
			}
			 if(m_sc_name.equals("NEW")){
					
					m_type="REQUESTED";
					m_request=m_request;
					}
					else if(m_sc_name.equals("EDIT")){
					m_type="RETURNED";
					m_request=m_req_no;
					}
			
			
			
			
			callstmt.setString(1,m_request);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(3,m_invoice);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+d+"_"+j+""));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DOC_NAME_"+d+"_"+j+""));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REASON_"+d+"_"+j+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(8,m_chk);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(j));
			callstmt.setString(12,m_type);
			callstmt.setString(13,"REQ-APP");
			callstmt.setString(14,"AF_CR_SECURITY_MARKETTING_FILE");
			callstmt.setString(15,"ASSET");
			if  (m_doc_code.trim().equals("")){
			break;
			}
			
			
				callstmt.execute();
				
				if(m_request.equals("")){
				m_request=callstmt.getString(1);
				}
		}	
	
	}
	
	
	
	
			String m_chk1="";
	
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SECURITY_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
			
				for (int a=0; a<m_chksql_client; a++) 
			{
			
			
			
			String m_chk_C=m_sn_methods.met_formdata(reqstr,"chk_app_C_"+a+"");
			
			String m_doc_code_C=m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_C_"+a+"");
			String m_req_no_C=m_sn_methods.met_formdata(reqstr,"TXT_REQ_C");
			
			

			if(m_sc_name.equals("NEW")){
			
				if(m_chk_C.equals("")){
					m_chk1="AVAILABLE";
					}
				else if(m_chk_C.equals("Y")){
					m_chk1="REQUESTED";
					}
			
				else if(m_chk_C.equals("N")){
					m_chk1="AVAILABLE";
					}
			}

			else if(m_sc_name.equals("EDIT")){
					if(m_chk_C.equals("")){
					m_chk1="REQ-APP";
					}
					else if(m_chk_C.equals("Y")){
					m_chk1="RETURNED";
					}
			
					else if(m_chk_C.equals("N")){
					m_chk1="REQ-APP";
					}
			}
			 if(m_sc_name.equals("NEW")){
					
					m_type="REQUESTED";
					m_request=m_request;
					}
					else if(m_sc_name.equals("EDIT")){
					m_type="RETURNED";
					m_request=m_req_no_C;
					}
			
			
		
			callstmt.setString(1,m_request);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(3,"");
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_C_"+a+""));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DOC_NAME_C_"+a+""));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REASON_C_"+a+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(8,m_chk1);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(a));
			callstmt.setString(12,m_type);
			callstmt.setString(13,"REQ-APP");
			callstmt.setString(14,"AF_CR_SECURITY_MARKETTING_FILE");
			callstmt.setString(15,"CLIENT");
			if  (m_doc_code_C.trim().equals("")){
			break;
			}
			
			
			callstmt.execute();
		}	
	

	
	
	
			callstmt.close();
		
			conn.commit();
			

	 /*	
	 	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_DELETE(:1); END;");
	//		callstmt.setString(1,"");
			callstmt.execute();
			callstmt.close();

	//	 conn.commit();
 */
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

