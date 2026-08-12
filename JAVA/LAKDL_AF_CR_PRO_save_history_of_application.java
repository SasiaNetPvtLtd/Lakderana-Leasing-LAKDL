
//--
//SCREEN NAME:SAVE HISTORY OF APPLICATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_history_of_application extends HttpServlet {
		
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

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_HISTORY_OF_APPLICATION(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_NET_PRICE"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_ID"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(22,m_username);
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_history_of_application';");
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
