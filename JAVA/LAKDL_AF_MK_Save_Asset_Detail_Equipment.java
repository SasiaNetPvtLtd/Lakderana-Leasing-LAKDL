
//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_Asset_Detail_Equipment extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();


			//PrintStream out = new PrintStream(res.getOutputStream());
	   // out.println(reqstr);
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
	//		m_html_client_url=SCREEN_METHODS.html_client_url;
		//	m_class_url=SCREEN_METHODS.servlet_client_url.trim()+":"+SCREEN_METHODS.client_t3_port.trim();
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
	//		out.println(reqstr);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			m_url = m_class_url;
			
		
			
			
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			String m_appNo=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"); 
			
			
			String m_my_screen="";
			String m_inqNo=""; //added by nuwan de silva 26-06-07
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
			m_my_screen="";
		  }
			
			//added by nuwan de silva 26-06-07
			if(req.getParameter("INQ_NO")!=null){
			m_inqNo=req.getParameter("INQ_NO");
			}
			
			
			// added by udara 10-12-2014
			String m_brk_status    = "";
			String m_direct_status = "";
			
			if(req.getParameter("brk_status")!=null){ 
			    m_brk_status=req.getParameter("brk_status");
			}
			
			if(req.getParameter("direct_status")!=null){ 
			    m_direct_status=req.getParameter("direct_status");
			}			
			// end by udara 10-12-2014
			
			
			
			//--------------------------------------
			
			
			for (int j = 0; j < m_maxentries; j++) {
			
				
			        String m_num=Integer.toString(j); 
			        int m_count=m_maxentries-1;
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_ASSET_EQP(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
								
							//String m_model=(String)m_sn_methods.met_formdata(reqstr,"TXT_MODEL"+(Integer.toString(j))).trim();
               String m_model=(String)m_sn_methods.met_formdata(reqstr,"HID_TXT_MODEL"+(Integer.toString(j))).trim();
							  String sub_model=(String)m_sn_methods.met_formdata(reqstr,"HID_TXT_SUB_MODEL"+(Integer.toString(j))).trim();
								
						 // out.println("m_model"+m_model);	
						//	out.println("sub_model"+sub_model);	
								
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_NO"+(Integer.toString(j)))).trim());
				    
						  //======MODIFIED NUWAN DE SILVA 21-05-07----------------------------------------------------
						  //callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_MODEL"+(Integer.toString(j))));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"HID_TXT_MODEL"+(Integer.toString(j))));
							
							
							//======MODIFIED NUWAN DE SILVA 21-05-07----------------------------------------------------
							//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL"+(Integer.toString(j))));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUB_MODEL"+(Integer.toString(j))));
							
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_STATUS"+(Integer.toString(j))));
							//callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_SUPPLIER"+(Integer.toString(j))));
							callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_QUANTITY"+(Integer.toString(j)))));
							//callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_COST"+(Integer.toString(j)))));
							callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_PURPOSE"+(Integer.toString(j))));
							//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION"+(Integer.toString(j))));
							//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CITY"+(Integer.toString(j))));
							//callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+(Integer.toString(j)))));
							callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(9,m_username);
							callstmt.setString(10,m_num);
							callstmt.setString(11,m_scr_name); //Added by Nuwan De Silva 
														
									
							
							if  (m_model.trim().equals("")) {
						  break;
					    } 
				   
							callstmt.execute();
							
				}
			
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------
			//conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_MK_Asset_Detail_Equipment?APP_NO="+m_appNo+"';");
			//out.println("window.close();");
		//	out.println("close_window();");
			//out.println("window.location.href='"+servlet_client_url+"\:\"+client_t3_port+"/"+client_name+"AF_CO_FollowupAlert?chksql=main_page"; ");
			//out.println("alert('location'+top.frames[1].location)");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no="+m_appNo+"';");
			
			out.println("if('"+m_my_screen+"'==''){");
			
			out.println("		if(confirm(\"Are you sure you want to add a pricing?\")){ "); 
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO="+m_appNo+"&INQ_NO="+m_inqNo+"&TER_NO=&TER_TYPE=';"); // commented by udara 10-12-2014
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO="+m_appNo+"&INQ_NO="+m_inqNo+"&TER_NO=&TER_TYPE=&brk_status="+m_brk_status+"&direct_status="+m_direct_status+"';"); // added by udara 10-12-2014
			out.println("	}");
			
			out.println("else {");
			out.println("window.close();");
			//out.println("window.opener.chk_totals()");
		  out.println("}");
			
			//out.println("window.close();");
			out.println("window.opener.chk_totals()");
			
			out.println("}");
			out.println("if('"+m_my_screen+"'!=''){");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Asset_Detail_Equipment?APP_NO="+m_appNo+"&screen=G&my_screen_name=CV';");
			out.println("}");
			
			//href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no="+m_appNo+"';");
			//top.frames[1].location
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body></html>");

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
		}*/
		
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
}
