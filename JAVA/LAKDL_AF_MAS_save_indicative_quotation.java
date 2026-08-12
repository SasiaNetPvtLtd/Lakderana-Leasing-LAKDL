
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - INDICATIVE QUOTATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_indicative_quotation extends HttpServlet {
		
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

			LAKDL_AF_CO_conn_methods   m_sn_methods = new LAKDL_AF_CO_conn_methods  (); 
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

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_INDICATIVE_QUOTATION(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_QUOTATION_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INQUIRY_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_OPTION_ID"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_QTY"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_GROSS_AMOUNT"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_VAT_AMOUNT"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_GROSS_RENTAL"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_VAT_RENTAL"));
			//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_RESIDUAL_AMOUNT"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION_OF_ASSET"));
			//callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_ITEM_CATEGORY"));
			//callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT_CODE"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE"));
			//callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"));
			//callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_GROSS_AMOUNT"));
			//callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_VAT_AMOUNT"));
			//callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_NET_AMOUNT"));
			//callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_INSTALLMENT_NO"));
			//callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_GRENTAL_AMOUNT"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_NET_RENTAL_AMOUNT"));
			//callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_VAT_RENT_AMOUNT"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(16,m_username);
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_indicative_quotation';");
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
