//--
//SCREEN NAME	:SAVE STANDING ORDERS
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_standing_order extends HttpServlet {
		
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
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_start_date="";
			String m_end_date="";
		  String  m_type=req.getParameter("type");
			
			
			String m_start_dd=m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_DD");
			String m_start_mm=m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_MM");
			String m_start_yy=m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_YY");
			
			String m_end_dd=m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_DD");
			String m_end_mm=m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_MM");
			String m_end_yy=m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_YY");
			
			m_start_date=m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_YY");
			if(m_start_dd.equals("") || m_start_mm.equals("") || m_start_yy.equals("") ){
			m_start_date="";
			}
			m_end_date=m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_YY");
			if(m_end_dd.equals("") || m_end_mm.equals("") || m_end_yy.equals("") ){
			m_end_date="";
			}
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_STANDING_ORDER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_LEASE_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(3,m_start_date);
			callstmt.setString(4,m_end_date);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"));
			callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
			callstmt.setString(8,m_type);
			callstmt.setString(9,m_username);
			callstmt.setString(10,"AF_CR_PRO_STANDING_ORDER");
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_SO_NO"));
			if(m_type.equals("NEW")){
			callstmt.setString(12,"Y");
			}
				else{
			callstmt.setString(12,"CANCEL");
			}	
			callstmt.setInt(13,0);
			
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_display_standing_order?chksql=main_page&type="+m_type+"';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
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
		}

	}
}
