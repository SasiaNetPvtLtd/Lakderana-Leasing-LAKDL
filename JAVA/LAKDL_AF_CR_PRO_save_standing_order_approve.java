//--
//SCREEN NAME:SAVE STANDING ORDER APPROVE
//CREATED BY :DELANJALI	
//DATE/TIME  :19-02-2007
//NOTES      :

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_standing_order_approve extends HttpServlet {
		
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
		
			int m_chk=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no"));
			for(int j=0;j<m_chk;j++){
			
		
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_STANDING_ORDER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			String m_chk_app=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);

			if(m_chk_app.equals("")){
			m_chk_app="N";
			}
			
			if(m_chk_app.equals("Y")){
			
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO_"+j));
			callstmt.setString(2,"");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_START_DATE_"+j));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_END_DATE_"+j));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO_"+j));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE_"+j));
			callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_"+j)));
			callstmt.setString(8,"EDIT");
			callstmt.setString(9,m_username);
			callstmt.setString(10,"AF_CR_PRO_STANDING_ORDER_APP");
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_SO_NO_"+j));
			callstmt.setString(12,"Y");
			callstmt.setInt(13,0);
		}	
}
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_display_standing_order_approve';");
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
