
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - DOCUMENT APPLICABLE
//CREATED BY: Mahela Wickramasekara
//DATE:24-10-2006
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_document_applicable extends HttpServlet {
		
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
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			String m_app_doc_lineno=(String)m_sn_methods.met_formdata(reqstr,"hid_app_doc_lineno"); 	
			int app_doc_lineno = Integer.parseInt(m_app_doc_lineno);
			String m_hid_doc_type=(String)m_sn_methods.met_formdata(reqstr,"hid_doc_type"); 	

			int k=0;
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);

			m_msg = "'Information saved successfully'";
			m_url = m_class_url;



			for(int i=0;i<app_doc_lineno;i++)
			{
						
			  String status = m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+i+"");
				if(status.equals("")){
				status="off";
				}
	
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_DOCUMENT_APLICABLE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				
				// commented by udara 06-08-2015
				/*
				
				if(status.equals("on")){

				callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+"")).trim());
				if(m_hid_doc_type.equals("CLIENT"))
				{
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));	
				callstmt.setString(3,"");	
				}
				else if(m_hid_doc_type.equals("ASSET"))
				{
				callstmt.setString(2,"");	
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_TYPE"));	
				}
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FROM"+i+""));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TO"+i+""));
								
				if(status.equals("on"))
				{
				callstmt.setString(6,"Y");
				}
				else 
				{
				callstmt.setString(6,"N");
				}
				
				callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DIVISION"+i+""));
				callstmt.setString(8,""+k);
				callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(10,m_username);
				callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CODE"));	
				callstmt.execute();
				k=k+1;
				}

				if(status.equals("off") && k==0){

				callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+"")).trim());
				if(m_hid_doc_type.equals("CLIENT"))
				{
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));	
				callstmt.setString(3,"");	
				}
				else if(m_hid_doc_type.equals("ASSET"))
				{
				callstmt.setString(2,"");	
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_TYPE"));	
				}
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FROM"+i+""));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TO"+i+""));
								
				if(status.equals("on"))
				{
				callstmt.setString(6,"Y");
				}
				else 
				{
				callstmt.setString(6,"N");
				}
				callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DIVISION"+i+""));
				callstmt.setString(8,""+i);
				callstmt.setString(9,"DELETE");
				callstmt.setString(10,m_username);
				callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CODE"));	
				callstmt.execute();
				}
				
				*/
				
				
				// added by udara 06-08-2015
				
				//out.println(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE") + "     " + m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+"") + "    "+status); // test
				
				callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+"")).trim());
				if(m_hid_doc_type.equals("CLIENT"))
				{
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));	
				callstmt.setString(3,"");	
				}
				else if(m_hid_doc_type.equals("ASSET"))
				{
				callstmt.setString(2,"");	
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_TYPE"));	
				}
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FROM"+i+""));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TO"+i+""));
								
				if(status.equals("on"))
				{
				callstmt.setString(6,"Y");
				}
				else 
				{
				callstmt.setString(6,"N");
				}
				
				callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DIVISION"+i+""));
				callstmt.setString(8,""+k);
				callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(10,m_username);
				callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CODE"));	
				callstmt.execute();
				k=k+1;
				
				// end by udara 06-08-2015
				
				
				
				}
			conn.setAutoCommit(true);
			callstmt.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_document_applicable';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");


			out.flush();

		}
		catch (Exception ex) {
		try{conn.rollback();}catch(Exception e){}
		try{conn.rollback();}catch(Exception e){}
		out.println("ERROR:"+ex.toString());
		
		//	try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
		/*finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}*/
							finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
				//if(input     !=null){try{input.close();    }catch(Exception e){}}
				//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}


	}
}
