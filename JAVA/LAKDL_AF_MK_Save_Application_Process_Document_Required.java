
//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_Application_Process_Document_Required extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			//PrintStream out = new PrintStream(res.getOutputStream());
									out = res.getOutputStream();

	  //  out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_screen_name="";
			String m_app_no="";
			String m_client_no="";
			String m_pur_ord_no1="";
			String m_status="";
			String m_core_client="";
			String m_txt_type="";
			String m_followup_num="";
			
			
	
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
		//	out.println(reqstr);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_status_approv=(String)m_sn_methods.met_formdata(reqstr,"hid_scr_approv"); 
			String m_Hid_records=(String)m_sn_methods.met_formdata(reqstr,"Hid_records"); 
			
								conn.setAutoCommit(false);

			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
			stmt=conn.createStatement();

			//out.println("reqstr " +reqstr);
			
			int m_doc_client      = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_of_clinet_doc"));
			int m_doc_core        = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_of_core_doc"));
			int m_doc_gur         = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_gur"));
			int m_doc_invoice     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_invoice_doc"));
			
		
			
			m_app_no     =(String)m_sn_methods.met_formdata(reqstr,"hid_app_no");
			m_client_no=(String)m_sn_methods.met_formdata(reqstr,"hid_client_no");
			m_core_client=(String)m_sn_methods.met_formdata(reqstr,"hid_core_app_code");
			m_followup_num=(String)m_sn_methods.met_formdata(reqstr,"hid_followup_num");
			
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");		
			
		/*	out.println("m_doc_gur"+m_doc_gur);
			out.println("m_client_no"+m_client_no);
			out.println("m_core_client"+m_core_client);
			out.println("m_followup_num"+m_followup_num);
			out.println("m_doc_client"+m_doc_client);
			out.println("m_doc_core"+m_doc_core);
			out.println("m_doc_invoice"+m_doc_invoice);
			*/
		//------------------------------------------------------------------------------------
				
					
				//Saving Documents.....................................
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");

				callstmt.registerOutParameter(10,java.sql.Types.CHAR);	
							
			for (int j = 0; j < m_doc_client; j++) {
				
				String m_num=Integer.toString(j);
				
				
			//	out.println("APP NO"+m_sn_methods.met_formdata(reqstr,"hid_app_no"));
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no"));
				
				callstmt.setString(2,m_scr_name);			
							
												
							String m_code =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE"+(Integer.toString(j)));
						//	out.println("m_code"+m_code);
							if(m_code.equals("")){
							break;
							}
							//out.println("CLI " +m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE"+(Integer.toString(j))));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE"+(Integer.toString(j))));
								
							if(m_status_approv.equals("Y")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+(Integer.toString(j)));
							String m_chk_not_app  =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_NOT_APP"+(Integer.toString(j)));
							
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else if(m_chk_not_app.equals("on"))
							{
							callstmt.setString(4,"A");
							}
							else
							{
							callstmt.setString(4,"N");
							}
							
							
							}
							else if(m_status_approv.equals("N")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+(Integer.toString(j)));
						
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else// if(m_chk_required.equals(""))
							{
							callstmt.setString(4,"N");
							}
							
							}
							
								
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+(Integer.toString(j))));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(7,m_username);
							callstmt.setInt(8,j);
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_client_no"));
							
							/*if(m_followup_num.equals(""))
							{
							}
							else
							{*/
							callstmt.setString(10,m_followup_num);
							
							//}
							
							callstmt.setString(11,m_scr_name);
							
							String m_chk_fol =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_FOLLOWUP"+(Integer.toString(j)));
						//	out.println("m_chk_fol"+m_chk_fol);				
							if(m_chk_fol.equals("on")){
							callstmt.setString(12,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(12,"N");
							}
							
							callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARK"+(Integer.toString(j))));
							callstmt.setString(14,"");
							callstmt.setString(15,"CLIENT");
							

							callstmt.execute();
							
							if(m_followup_num.equals("")){
						 // out.println("test");
					    // m_followup_num = callstmt.getString(10);					  
					    } 
				    
				}
				
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
				callstmt.registerOutParameter(10,java.sql.Types.CHAR);		
						
				for (int j = 0; j < m_doc_core; j++) {
								
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no"));
				
				callstmt.setString(2,m_scr_name);			
							
												
							String m_code =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_CORE"+(Integer.toString(j)));
							
							if(m_code.equals("")){
							break;
							}
							
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_CORE"+(Integer.toString(j))));
							
							
							
							
							if(m_status_approv.equals("Y")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_CORE"+(Integer.toString(j)));
							String m_chk_not_app  =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_CORE_NOT_APP"+(Integer.toString(j)));
							
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else if(m_chk_not_app.equals("on"))
							{
							callstmt.setString(4,"A");
							}
							else
							{
							callstmt.setString(4,"N");
							}
							
							
							}
							else if(m_status_approv.equals("N")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_CORE"+(Integer.toString(j)));
						
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else //if(m_chk_required.equals(""))
							{
							callstmt.setString(4,"N");
							}
							
							}
							
							
							/*String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_CORE"+(Integer.toString(j)));
			
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(4,"N");
							}
							*/
							
								
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_CORE"+(Integer.toString(j))));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(7,m_username);
								callstmt.setInt(8,j);
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_core_app_code"));
							//callstmt.setString(10,"");
							
						/*	if(m_followup_num.equals(""))
							{
							//callstmt.setString(10,"");
							callstmt.registerOutParameter(10,java.sql.Types.CHAR);	
							}
							else
							{*/
							callstmt.setString(10,m_followup_num);
							
						//	}
							
							
							callstmt.setString(11,m_scr_name);
							
							String m_chk_fol =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_FOLLOWUP_CORE"+(Integer.toString(j)));
			
							if(m_chk_fol.equals("on")){
							callstmt.setString(12,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(12,"N");
							}
							
							callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARK_CORE"+(Integer.toString(j))));
							callstmt.setString(14,"");
								callstmt.setString(15,"CLIENT");


							callstmt.execute();
							if(m_followup_num.equals("")){
							//	out.println("test2");
					    //  m_followup_num = callstmt.getString(10);					  
					    } 
			
				}
				
				
				
				///Saving Gurantor Documents
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
				callstmt.registerOutParameter(10,java.sql.Types.CHAR);		
	
					for (int j = 0; j < m_doc_gur; j++) {
				
//out.println("size"+m_doc_gur);
				
			//	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
							
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no"));
		//		out.println("app no"+m_sn_methods.met_formdata(reqstr,"hid_app_no"));
				
				callstmt.setString(2,m_scr_name);			
							
												
							String m_code =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_GUR"+(Integer.toString(j)));
			//	out.println("code"+m_code);			
							if(m_code.equals("")){
							break;
							}
							//out.println("GUR " +m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE"+(Integer.toString(j))));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_GUR"+(Integer.toString(j))));
							
							
							
							if(m_status_approv.equals("Y")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_GUR"+(Integer.toString(j)));
							String m_chk_not_app  =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_GUR_NOT_APP"+(Integer.toString(j)));
							
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else if(m_chk_not_app.equals("on"))
							{
							callstmt.setString(4,"A");
							}
							else
							{
							callstmt.setString(4,"N");
							}
							
							
							}
							else if(m_status_approv.equals("N")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_GUR"+(Integer.toString(j)));
						
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else //if(m_chk_required.equals(""))
							{
							callstmt.setString(4,"N");
							}
							
							}
							
				/*	String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_GUR"+(Integer.toString(j)));
				//out.println("req"+m_chk_required)	;											
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(4,"N");
							}
							*/
							
								
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_GUR"+(Integer.toString(j))));
						//	out.println("remarkcode"+m_sn_methods.met_formdata(reqstr,"TXT_REMARK_GUR"+(Integer.toString(j))));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(7,m_username);
							callstmt.setInt(8,j);
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_gur_code"+(Integer.toString(j))));
							
					//		out.println("gur code"+m_sn_methods.met_formdata(reqstr,"hid_gur_code"+(Integer.toString(j))));												
													
						/*	if(m_followup_num.equals(""))
							{
							callstmt.registerOutParameter(10,java.sql.Types.CHAR);	
							}
							else
							{*/
							callstmt.setString(10,m_followup_num);
							
						//	}
						
							callstmt.setString(11,m_scr_name);
							
							String m_chk_fol =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_FOLLOWUP_GUR"+(Integer.toString(j)));
			
							if(m_chk_fol.equals("on")){
							callstmt.setString(12,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(12,"N");
							}
							
							callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARK_GUR"+(Integer.toString(j))));
							callstmt.setString(14,"");
							callstmt.setString(15,"CLIENT");


							callstmt.execute();
							
							if(m_followup_num.equals("")){
					    //  m_followup_num = callstmt.getString(10);		
							//	out.println("test3");
					    } 
							
				      
				}
				
					///Saving Invoice Documents
				
	
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
				callstmt.registerOutParameter(10,java.sql.Types.CHAR);		
				
					for (int j = 0; j < m_doc_invoice; j++) {
				
				
			//	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_DOC_REQ(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
							
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no"));
				
				callstmt.setString(2,m_scr_name);			
							
												
							String m_code =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_INV_DOC"+(Integer.toString(j)));
							
							if(m_code.equals("")){
							break;
							}
							
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_CODE_INV_DOC"+(Integer.toString(j))));
							
							
							
							
							if(m_status_approv.equals("Y")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_INV_DOC"+(Integer.toString(j)));
							String m_chk_not_app  =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_INV_DOC_NOT_APP"+(Integer.toString(j)));
							
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else if(m_chk_not_app.equals("on"))
							{
							callstmt.setString(4,"A");
							}
							else
							{
							callstmt.setString(4,"N");
							}
							
							
							}
							else if(m_status_approv.equals("N")){
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_INV_DOC"+(Integer.toString(j)));
						
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else // if(m_chk_required.equals(""))
							{
							callstmt.setString(4,"N");
							}
							
							}
							
							
																				
						/*	String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_INV_DOC"+(Integer.toString(j)));
			
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(4,"N");
							}
							*/
								
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_INV_DOC"+(Integer.toString(j))));
									
															
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(7,m_username);
							callstmt.setInt(8,j);
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_client_no"));

							/*if(m_followup_num.equals(""))
							{
							callstmt.registerOutParameter(10,java.sql.Types.CHAR);	
							}
							else
							{*/
							callstmt.setString(10,m_followup_num);
							
							//}
																			//out.println("*@@");

							callstmt.setString(11,m_scr_name);
							
							String m_chk_fol =(String)m_sn_methods.met_formdata(reqstr,"CHK_STATUS_FOLLOWUP_INV_DOC"+(Integer.toString(j)));
			
							if(m_chk_fol.equals("on")){
							callstmt.setString(12,"Y");
							}
							else //if(m_chk_required.equals("on"))
							{
							callstmt.setString(12,"N");
							}
																				//out.println("*-----");

							callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARK_INV_DOC"+(Integer.toString(j))));
							callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"hid_invoice_no"+(Integer.toString(j))));
							callstmt.setString(15,"ASSET");


							callstmt.execute();
							if(m_followup_num.equals("")){
						//	out.println("test4");
					     // m_followup_num = callstmt.getString(10);					  
					    } 
							
				      
				}

	
	
				
				
				
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_status_approv=\""+m_status_approv+"\"");
			//out.println("if(m_status_approv==\"N\"){");
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_MK_Application_Process_Document_Required?chksql=main_page&APP_NO="+m_app_no+"&TXT_TYPE="+m_txt_type+"&CLIENT_CODE="+m_client_no+"&ac_status=Y&CORE_APP_CODE="+m_core_client+"';");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_Application_Process?chksql=main_page&application_no="+m_app_no+"';");
			// out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no=\"+m_app_no;"); 
			out.println("window.close();");
			//out.println("if('"+m_my_screen+"'==''){");
			//out.println("window.opener.chk_totals()");
			//out.println("}");
			//out.println("}");
			//out.println("else {");
			
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Credit_Verifi_app_1?close=1&pre=VERIFY1&appro=VERIFY-M&qry=VERIFY1&applicaton_no="+m_app_no+"';");
			
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&APP_NO="+m_app_no+"&Hid_scr_name="+m_scr_name+"&TXT_TYPE="+m_txt_type+"&CLIENT_CODE="+m_client_no+"&ac_status=Y&scr_approv="+m_status_approv+"&hid_records="+m_Hid_records+"&CORE_APP_CODE="+m_core_client+"';"); //&hid_records=A&scr_approv=Y'
			
			//out.println("}");
			
			//LAKDL_AF_CR_PRO_Credit_Verifi_app_1?pre=VERIFY1&appro=VERIFY-M&qry=VERIFY1&applicaton_no=AP20070110-0272&close=1
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		/*catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
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
	}
	}
}*/
		catch (Exception E) {
		     	//PrintStream out = new PrintStream(res.getOutputStream());

		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }
		finally{
		//PrintStream out = new PrintStream(res.getOutputStream());
		try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
				}
	}
	}

