//--
//SCREEN NAME	:SAVE ENTER LEASE NO
//CREATED BY	:delanjali
//DATE/TIME		:12-02-2007
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_enter_lease_no extends HttpServlet {
	
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
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_client_code=(String)m_sn_methods.met_formdata(reqstr,"hid_client_code"); 
			conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			int m_chk=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no"));
			
			String	m_screen_type=req.getParameter("m_screen");
			String	m_status=req.getParameter("status");
			
			String m_date="";
			String m_postdate="";
			
			String m_mas_date="";
			String m_agr_date="";
			String m_next_date="";
			String m_transaction_type="";
			
			for(int j=0;j<m_chk;j++){
				
				
				String m_app=m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+j);
				String m_chk_app=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
				
				
				if(m_chk_app.equals("")){
					m_chk_app="N";
				}
				
				if(m_chk_app.equals("Y")){
					
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_ENTER_LEASE_NO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
					
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+j).trim());
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO_"+j).trim());
					m_date=(m_sn_methods.met_formdata(reqstr,"TXT_DATE_DD"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DATE_MM"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DATE_YY"+j));
					m_transaction_type=(String)m_sn_methods.met_formdata(reqstr,"HID_TRANSACTION_TYPE"+j); 
					
					//m_mas_date=(m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_DD"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_MM"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_YY"+j));
					
					String m_next_dd =m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_DD"+j);
					String m_next_mm =m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_MM"+j);
					String m_next_yy =m_sn_methods.met_formdata(reqstr,"TXT_ML_DATE_YY"+j);
					
					
					
					if(m_next_dd.equals("") && m_next_mm.equals("") &&  m_next_yy.equals("") )
					{
						m_mas_date=m_next_dd+m_next_mm+m_next_yy; 
					}
					else
					{
						m_mas_date=m_next_dd+"-"+m_next_mm+"-"+m_next_yy;
					}
					
					
					m_agr_date=(m_sn_methods.met_formdata(reqstr,"TXT_AGR_DATE_DD"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_AGR_DATE_MM"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_AGR_DATE_YY"+j));
					m_next_date=(m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_DD"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_MM"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_YY"+j));
					
					m_postdate=m_sn_methods.met_formdata(reqstr,"TXT_POSTED_DATE_DD_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POSTED_DATE_MM_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POSTED_DATE_YY_"+j);		
					
					callstmt.setString(3,m_date);
					callstmt.setString(4,"NEW");
					callstmt.setString(5,m_username);
					callstmt.setString(6,"AF_CR_PRO_ENTER_LEASE");
					callstmt.setString(7,"VERIFYL");
					callstmt.setString(8,Integer.toString(j));
					
					if(m_status.equals("lease")){
						
						callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_POSTED_TO_"+j));
						//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_POSTED_ADD1_"+j));
						//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_POSTED_ADD2_"+j));
						//callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_POSTED_TEL_"+j));
						//callstmt.setString(13,m_postdate);
						callstmt.setString(10,"");
						callstmt.setString(11,"");
						callstmt.setString(12,"");
						callstmt.setString(13,"");
						
						
						
					}
					else if(m_status.equals("act")){
						callstmt.setString(9,"");
						callstmt.setString(10,"");
						callstmt.setString(11,"");
						callstmt.setString(12,"");
						callstmt.setString(13,"");
						
						
						
						
					}
					
					callstmt.setString(14,m_status);
					callstmt.setString(15,m_screen_type);
					callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_M_FINANCE_NO_"+j).trim());
					callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+j).trim());
					callstmt.setString(18,m_mas_date);
					callstmt.setString(19,m_agr_date);
					
					callstmt.setString(20,m_next_date);
					
					callstmt.setString(21,m_transaction_type);
					
					
					
					
					if(m_app.equals("")){
						break;
					}
					callstmt.execute();
					
				}
			}
			
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("m_status = '"+m_status+"'");
			out.println("alert("+m_msg+");");
			out.println("if(m_status=='act'){");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_display_enter_lease_no?status="+m_status+"&SCREEN_TYPE="+m_screen_type+"&CLIENT_CODE="+m_client_code+"';");
			out.println("}");
			out.println("else {");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_display_enter_lease_no?status="+m_status+"&SCREEN_TYPE="+m_screen_type+"&CLIENT_CODE=';");
			out.println("}");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		/*catch (Exception ex) {
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
			out.close();
			
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}
