//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;  
import java.sql.*; 
import java.util.*; 
import oracle.sql.*; 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class LAKDL_AF_MK_Document_upload_delete extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			CallableStatement callstmt;
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			
			
			/*		
				if(m_chksql.equals("run_report")){ 
				
					String m_date=req.getParameter("date");
					String m_location_id=req.getParameter("location_id");
					String m_user_id=req.getParameter("user_id");
					String m_finance_no = req.getParameter("finance_no");
					
					try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_runn_case_rpt(:1,:2,:3,:4,:5);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.execute();
					out.print("OK"); 
					}
					catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
					}
					
				}
				*/
			
			
			if(m_chksql.equals("save_document")){ 
				res.setContentType("text/html");
				
				
				boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
				if (!isMultipartContent) {
					
					return;
				}
				
				
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				String m_finance_no = "";
				String m_document_name = "";
				String m_file_name = "";
				byte[] m_document = null;
				int length = 0;
				
				try {
					List fields = upload.parseRequest(req);
					
					Iterator it = fields.iterator();
					if (!it.hasNext()) {
						return;
					}
					
					while (it.hasNext()) {
						
						FileItem fileItem = (FileItem)it.next();
						boolean isFormField = fileItem.isFormField();
						if (isFormField) {
							if(fileItem.getFieldName().equals("TXT_FINANCE")){
								m_finance_no = fileItem.getString();
							}
							else if(fileItem.getFieldName().equals("TXT_DOCUMENT_NAME")){
								m_document_name = fileItem.getString();
							}
							
						} else {
							
							if(fileItem.getFieldName().equals("TXT_DOCUMENT")){
								m_file_name = fileItem.getName();
								m_document  = fileItem.get();
								length = fileItem.get().length;
							}
							
						}
						
					}
					
					
					BLOB blob = BLOB.createTemporary(conn, false, BLOB.DURATION_SESSION);
					OutputStream outputStream = blob.setBinaryStream(0L);
					InputStream inputStream = new ByteArrayInputStream(m_document);
					byte[] buffer = new byte[blob.getBufferSize()];
					int byteread = 0;
					while ((byteread = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, byteread);
					}
					outputStream.close();
					inputStream.close();
					
					
					callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_MK_DOCUMENT_SAVE(:1,:2,:3,:4,:5,:6,:7); END;");
					callstmt.registerOutParameter(1,Types.CHAR);
					callstmt.setString(2,m_finance_no); 
					callstmt.setString(3,m_document_name); 
					callstmt.setBlob(4,blob); 
					callstmt.setString(5,m_username);
					callstmt.setString(6,"NEW");
					callstmt.setString(7,m_file_name);
					callstmt.execute();
					
					
					String m_document_no = callstmt.getString(1);
					
					
					conn.commit();
					
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Document uploaded successfully. Document No : "+m_document_no+"');");
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=main_page';");
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'></body>");
					out.println("</html>");
					
					out.flush();
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error When Saving Record..');");
					out.println("window.history.back();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
					out.println("</html>");
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			
			
			
			if(m_chksql.equals("delete_document")){ 
				res.setContentType("text/html");
				
				try {
					String m_document_no = req.getParameter("doc_no");
					String m_table_type = req.getParameter("table_type");
					
					if(m_table_type!=null && !m_table_type.equals("") && m_table_type.equals("OLD")){
						callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_MK_DOCUMENT_SAVE(:1,:2,:3,:4,:5,:6,:7); END;");
						//callstmt.registerOutParameter(1,Types.CHAR);
						callstmt.setString(1,m_document_no); 
						callstmt.setString(2,null); 
						callstmt.setString(3,null); 
						callstmt.setBytes(4,null); 
						callstmt.setString(5,m_username);
						callstmt.setString(6,"DELETE");
						callstmt.setString(7,null);
						callstmt.execute();
					}else if(m_table_type!=null && !m_table_type.equals("") && m_table_type.equals("NEW")){
						callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_MK_DOCUMENT_SAVE_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
						//callstmt.registerOutParameter(1,Types.CHAR);
						callstmt.setString(1,m_document_no); 
						callstmt.setString(2,null); 
						callstmt.setString(3,null); 
						callstmt.setBytes(4,null); 
						callstmt.setString(5,m_username);
						callstmt.setString(6,"DELETE");
						callstmt.setString(7,null);
						callstmt.setString(8,null);
						callstmt.setString(9,null);
						callstmt.execute();
					}
					
					
					
					
					conn.commit();
					out.print("OK");
					
					out.flush();
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					out.print("ERROR "+ex.toString());
					
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			
			//=============================================================================================================================================
			if(m_chksql.equals("delete_New_Upload_Document")){ 
				res.setContentType("text/html");
				
				try {
					String m_document_no = req.getParameter("doc_no");					
					
						callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_MK_DOC_UPLOAD_LOG_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
						
						callstmt.setString(1,null); 
						callstmt.setString(2,m_document_no); 
						callstmt.setString(3,null); 
						callstmt.setString(4,null); 
						callstmt.setString(5,null); 
						callstmt.setString(6,null); 
						callstmt.setString(7,null); 
						callstmt.setString(8,"DELETE"); 
						callstmt.setString(9,m_username); 
						
						callstmt.execute();
					
					
					
					
					
					conn.commit();
					out.print("OK");
					
					out.flush();
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					out.print("ERROR "+ex.toString());
					
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			//=============================================================================================================================================
			if(m_chksql.equals("get_document")){ 
				res.setContentType("APPLICATION/OCTET-STREAM");
				stmt = conn.createStatement ();
				try {
					String m_document_no = req.getParameter("doc_no");
					Blob document = null;
					
					String Sql_data="";
					
					
					Sql_data=
						
						" SELECT	A.DOCUMENT, A.FILE_NAME"+
						"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
						"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document_no+"'";
					
					
					rs=stmt.executeQuery(Sql_data);
					//out.println(""+Sql_data+"");
					boolean more=rs.next();
					
					if(more){
						try{
							res.setHeader("Content-Disposition",
								"attachment;filename="+rs.getString(2)+"");
							document = rs.getBlob(1);
							
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								out.write(buffer, 0, length);
							}
							
							in.close();
							out.flush();
						}
						catch (Exception ex) {
							ex.printStackTrace();
							try{conn.rollback();}catch(Exception e){};
							res.setContentType("text/html");
							out.println("<HTML><HEAD>");
							out.println("<SCRIPT language='JavaScript'>");
							out.println("function displaymsg() {");
							out.println("alert('Error when downloading document....');");
							out.println("window.close();"); 
							out.println("}</SCRIPT></HEAD>");
							out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
							out.println("</html>");
							out.flush();
							out.close();
							
						}
						
						
					}else{
						
						res.setContentType("text/html");
						out.println("<HTML><HEAD>");
						out.println("<SCRIPT language='JavaScript'>");
						out.println("function displaymsg() {");
						out.println("alert('Document Not Found....');");
						out.println("window.close();"); 
						out.println("}</SCRIPT></HEAD>");
						out.println("<body onload='displaymsg();'></body>");
						out.println("</html>");
						out.flush();
						out.close();
					}
					
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					res.setContentType("text/html");
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error when downloading document....');");
					out.println("window.close();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
					out.println("</html>");
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Document Upload Deletion</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("     help_button_user(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("			document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[2]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     help_update(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[0]");
				out.println("			}");
				
				out.println("}");
				
				/* out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
	      out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Marketing_Officer_Performance_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				*/
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");	
				
				
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				out.println("function makeRequest(obj) {");
				
				out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M1' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_LOCATION_CODE.value+\"&ac_status=Y\";");	
				
				//out.println("window.open(m_url);");
				
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=main_page&generate=page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Marketing - Document Upload Deletion - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Marketing - Document Upload Deletion - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				
				//----------------------------------------------------------------------------------------------------------------------------------------
				
				
				/*
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		team_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_user(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("		}"); 
				
				
				
				out.println("	}"); //end next
				
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				
				out.println("	}"); //end prev
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); ///close
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear 
				out.println("	}");
				
				
				out.println("	}	"); //
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				//-----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println(""); 
				
				out.println("function help_button_finance() {"); 
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_finance(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				//out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				//out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
				out.println("}");
				
				*/
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("		clear_data();");
				out.println("		} else "); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("		}"); 
				
				
				
				
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{");
				out.println("	clear_data();");
				out.println("	}");
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				//client Help
				out.println("function help_button_finance(){");
				out.println("Crit=document.Form1.TXT_FINANCE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				//out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt','1');"); //ADDED by nuwan de silva on07-04-2008
				out.println("}");		
				
				out.println("function help_value_assign_finance(oBj){");
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];");
				
				out.println("}");
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				out.println("}");
				
				
				out.println("function get_rental_dates(date,m_client_code,m_officer){");
				
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
				out.println("load_interface(m_url,'NORM');");
				//out.println("window.open(m_url);");
				out.println("}"); 
				
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}"); 
				
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}");	
				
				out.println("function ckeck_new_date(){ "); 
				out.println("b_flag=0;");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_date_selected()){"); 
				out.println("alert('Please enter new date');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println("function count_date_selected(){ ");
				out.println("count=0;");
				out.println("var arr_size=document.Form1.hid_no_rec.value;");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
				out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
				out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
				
				out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				
				out.println("function before_submit(){ ");
				out.println(" m_sav_msg = 'Are you sure you want to Save?'; ");
				
				out.println(" if(validate_data()){");
				
				out.println("if(confirm(m_sav_msg)){ ");
				
				out.println("	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=save_document';");
				out.println("	document.Form1.submit();");
				out.println("}");
				out.println("}");
				out.println("else { ");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("}");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println(" var flag = false;"); 
				out.println("if(document.Form1.TXT_FINANCE.value==\"\"){  "); 
				out.println(" DIV_TXT_FINANCE.style.color='red';");
				out.println("  flag = true;"); 
				out.println("}else{"); 
				out.println(" DIV_TXT_FINANCE.style.color='black';");
				out.println("}"); 
				
				out.println("if(document.Form1.TXT_DOCUMENT.value==\"\"){  "); 
				out.println(" DIV_TXT_DOCUMENT.style.color='red';");
				out.println("  flag = true;"); 
				out.println("}else{"); 
				out.println(" DIV_TXT_DOCUMENT.style.color='black';");
				out.println("}"); 
				
				out.println("if(document.Form1.TXT_DOCUMENT_NAME.value==\"\"){  "); 
				out.println(" DIV_TXT_DOCUMENT_NAME.style.color='red';");
				out.println("  flag = true;"); 
				out.println("}else{"); 
				out.println(" DIV_TXT_DOCUMENT_NAME.style.color='black';");
				out.println("}"); 
				
				out.println("if(flag){"); 
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function view_documents_new(){");
				out.println("	if(document.Form1.TXT_FINANCE.value!=\"\"){");
				
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=view_documents&finance_no=\"+document.Form1.TXT_FINANCE.value;");	
				out.println("			window.open(m_url);");
				out.println("	}else{");
				out.println(" alert('Please enter a finance number.');");
				out.println("}");
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'  > "); //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post' enctype='multipart/form-data'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 327px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing - Document Upload Deletion </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='before_submit()' value=\"Save\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>"); 
				out.println("<td width='10%'></td>");
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				out.println("<br/>");  
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</td>");
				out.println("</tr>");
				*/
				/*
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				*/
				/*
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				*/
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No* </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='20' style='{width=150px}' size='20' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\"><input class='but_input' style='width:100px' type='button' name='BUT_VIEW_DOCUMENTS' value=\"View Documents\" onClick=\"view_documents_new()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				/*
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_DOCUMENT_NAME'  class=div_input>Document Name* </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_DOCUMENT_NAME' maxlength='100' style='{width=150px}' size='20' >"); 
				//out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_DOCUMENT'  class=div_input>Document* </DIV></td>"); 
				out.println("<td width='*%' ><input class=''  type=\"file\" size=\"50\"  name=\"TXT_DOCUMENT\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				*/
				
				
				
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			else if(m_chksql.equals("view_documents")){		//2017-10-03 JB25052017-00154
				
				String m_finance_no ="";
				
				
				
				
				m_finance_no=req.getParameter("finance_no").trim();
				stmt = conn.createStatement ();
				
				
				
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Document Upload Deletion</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				/*
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				*/
				
				
				out.println("function get_document(doc_no){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=get_document&doc_no='+doc_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				
				out.println("function delete_document_new(doc_no){ "); 
				out.println("	if(confirm('Are you sure, you wnat to delete?')){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=delete_New_Upload_Document&doc_no='+doc_no;"); 
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				 
				
				out.println("function delete_document(doc_no,m_table_type) {");
				out.println("	if(confirm('Are you sure, you wnat to delete?')){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=delete_document&doc_no=\"+doc_no+\"&table_type=\"+m_table_type;"); 
				//out.println("		window.open(m_url)");
				//out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Document deleted successfully.');"); 
				out.println("			window.location.href= window.location.href;"); 
				
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when deleting...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				/*
				Sql_data=
					
					"  SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME "+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"'"+
					"  ORDER BY A.DOCUMENT_NO ASC";	 
				*/
				Sql_data=
				 " SELECT * FROM ( "+
				 " SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME,'OLD' TABLE_TYPE "+
				 " FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
				 " WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"' "+
				 " UNION ALL "+
				 " SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME,'NEW' TABLE_TYPE "+
				 " FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD_NEW A "+
				 " WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"' "+
				 " ) ORDER BY DOCUMENT_NO ASC ";

				rs=stmt.executeQuery(Sql_data);
				//out.println(""+Sql_data+"");
				boolean more=rs.next();
				int count=0;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Uploaded Documents</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Finance No: "+m_finance_no+"</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				
				out.println("<tr >");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;}' ><b>No</b></td>"); 			
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'><b>Document Number</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Name</b></td>"); 	
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Name</b></td>"); 
				//out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>&nbsp;</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>&nbsp;</b></td>"); 																																										
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				
				while(more){
					
					out.println("<tr  id=tr_id"+j+"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(2)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(1)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(3)+"</td>");
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='View' onclick='get_document(\""+rs.getString(2)+"\")'></td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='Delete' onclick='delete_document(\""+rs.getString(2)+"\",\""+rs.getString("TABLE_TYPE")+"\")'></td>");
					out.println("</tr>");
					
					
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				
				
				
				
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");		 
				
				//========================================================================================================
				//========================================================================================================
				//JB02012018-02276 NetAsset system documents upload facility inesh 2018-01-18
				
				String m_newDocuments =""+
								 " SELECT  "+								 
								  "  NVL(A.FINANCE_NO,'_') FINANCE_NO, "+
								 "   NVL(A.DOC_ID,'_') DOC_ID, "+
								 "   NVL(B.DOC_NAME,'_') DOC_NAME,   "+
								 "   NVL(A.DOC_NUM,0) DOC_NUM, "+
								 "   NVL(B.DOC_DESCRIPTION,'_') DOC_DESCRIPTION, "+
								 "   NVL(A.DOCUMENT_NAME,'_') DOCUMENT_NAME   "+
								 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES B "+
								 " WHERE a.DOC_TYPE_ID = B.DOC_ID "+
								 " AND A.ACTIVE_STATUS ='Y' "+
								 " AND A.FINANCE_NO = '"+m_finance_no+"' "+
								 " ORDER BY B.DOC_NAME,A.DOC_NUM ";
					
				rs=stmt.executeQuery(m_newDocuments);
				boolean isHeaderPrint = false;
				int docNum =1;
				while(rs.next()){
					if(!isHeaderPrint){
						isHeaderPrint = true;
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Uploaded New Documents</u></td>"); 
						out.println("</tr >");
						out.println("</table >");
						
						out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
						out.println("<tr >");
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;}' ><b>No</b></td>");
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center; }'><b>Document Number</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Name</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Description</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document No</b></td>"); 
						out.println(" 	<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Name</b></td>"); 
						out.println(" 	<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>&nbsp;</b></td>"); 
						out.println("</tr >");
					}
					out.println("<tr >");
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}'  bgcolor='lightblue' >"+ docNum++ +"</td>");//No
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_ID")+"</td>"); //Document Number
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_NAME")+"</td>"); //Document Name
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_DESCRIPTION")+"</td>"); //Document Description
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}' >"+rs.getString("DOC_NUM")+"</td>"); //Document No
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOCUMENT_NAME")+"</td>"); //File Name
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}' ><input type='button' class='but_input' value='Delete' onclick='delete_document_new(\""+rs.getString("DOC_ID")+"\")'></td>"); //
					out.println("</tr >"); 
					
				}
				
				
				//========================================================================================================
				//========================================================================================================
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			else if(m_chksql.equals("view_documents_bk")){		//2017-10-003
				
				String m_finance_no ="";
				
				
				
				
				m_finance_no=req.getParameter("finance_no").trim();
				stmt = conn.createStatement ();
				
				
				
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Document Upload Deletion</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				/*
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				*/
				
				
				out.println("function get_document(doc_no){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=get_document&doc_no='+doc_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function delete_document(doc_no) {");
				out.println("	if(confirm('Are you sure, you wnat to delete?')){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload_delete?chksql=delete_document&doc_no=\"+doc_no;"); 
				//out.println("		window.open(m_url)");
				//out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Document deleted successfully.');"); 
				out.println("			window.location.href= window.location.href;"); 
				
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when deleting...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				
				Sql_data=
					
					" SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME "+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"'"+
					"  ORDER BY A.DOCUMENT_NO ASC";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(""+Sql_data+"");
				boolean more=rs.next();
				int count=0;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Uploaded Documents</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Finance No: "+m_finance_no+"</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				
				out.println("<tr >");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;}' ><b>No</b></td>"); 			
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'><b>Document Number</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Name</b></td>"); 	
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Name</b></td>"); 
				//out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>&nbsp;</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>&nbsp;</b></td>"); 																																										
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				
				while(more){
					
					out.println("<tr  id=tr_id"+j+"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(2)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(1)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(3)+"</td>");
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='View' onclick='get_document(\""+rs.getString(2)+"\")'></td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='Delete' onclick='delete_document(\""+rs.getString(2)+"\")'></td>");
					out.println("</tr>");
					
					
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				
				
				
				
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");		 
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
