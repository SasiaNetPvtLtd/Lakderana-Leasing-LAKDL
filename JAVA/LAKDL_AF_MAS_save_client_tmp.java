
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - CLIENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_client_tmp extends HttpServlet {
		
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

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_TMP(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,:57,:58,:59,:60,:61,:62,:63,:64,:65,:66); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TITLE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FIRST_NAME"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_SURNAME"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INITIALS"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_FULL_NAME_I"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_NAME"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_HOME"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_HOME"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_EMAIL"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_YEARS"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_MONTHS"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_EMP_NAME"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_EMP_REFERENCE"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS1"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS2"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_EMP_RDESIGNATION"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_EMP_TEL_NO"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_EMP_FAX_NO"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_NAME_REL"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_REL"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_REL"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO_REL"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO"));
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_PASSPORT_NO"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_NATIONALITY"));
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_MARITAL_STATUS"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_GENDER"));
			callstmt.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_BA_NATURE_OF_BUSINESS"));
			callstmt.setString(38,m_sn_methods.met_formdata(reqstr,"TXT_BA_PROFESSION"));
			callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_BA_DESIGNATION"));
			callstmt.setString(40,m_sn_methods.met_formdata(reqstr,"TXT_BA_QUALIFICATIONS"));
			//callstmt.setString(41,m_sn_methods.met_formdata(reqstr,"TXT_NATIONALITY"));
			/*callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_BUSINESS_SUB_SECTOR"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CATEGORY"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_EMAIL"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_CAT_TYPE_CODE"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_BUSINESS_CERTIFICATE_NO"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_KEY_DECISION_MAKER"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_DIRECT_TEL_NO"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_FOR_PAYMENT"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION_PAYMENT"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_FACTORY_ADDRESS1"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_FACTORY_ADDRESS2"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_FACTORY_STATUS"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_F_CONTACT_PERSON"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_REGISTERED_ADDRESS1"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_REGISTERED_ADDRESS2"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_REGISTERED_CITY_CODE"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_REGISTERED_STATUS"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_CORRESPONDENCE_STATUS"));
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_F_TEL_NO"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_F_FAX_NO"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_F_EMAIL"));
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION"));
			callstmt.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_NO"));
			callstmt.setString(38,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE"));
			callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_TITLE"));
			callstmt.setString(40,m_sn_methods.met_formdata(reqstr,"TXT_FIRST_NAME"));
			callstmt.setString(41,m_sn_methods.met_formdata(reqstr,"TXT_SURNAME"));
			callstmt.setString(42,m_sn_methods.met_formdata(reqstr,"TXT_INITIALS"));
			callstmt.setString(43,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_NAME"));
			callstmt.setString(44,m_sn_methods.met_formdata(reqstr,"TXT_RESIDENTIAL_STATUS"));
			callstmt.setString(45,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_YEARS"));
			callstmt.setString(46,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_MONTHS"));
			callstmt.setString(47,m_sn_methods.met_formdata(reqstr,"TXT_PASSPORT_NO"));
			callstmt.setString(48,m_sn_methods.met_formdata(reqstr,"TXT_MARITAL_STATUS"));
			callstmt.setString(49,m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH"));
			callstmt.setString(50,m_sn_methods.met_formdata(reqstr,"TXT_NATIONALITY"));
			callstmt.setString(51,m_sn_methods.met_formdata(reqstr,"TXT_GENDER"));
			callstmt.setString(52,m_sn_methods.met_formdata(reqstr,"TXT_BA_NATURE_OF_BUSINESS"));
			callstmt.setString(53,m_sn_methods.met_formdata(reqstr,"TXT_BA_PROFESSION"));
			callstmt.setString(54,m_sn_methods.met_formdata(reqstr,"TXT_BA_QUALIFICATIONS"));
			callstmt.setString(55,m_sn_methods.met_formdata(reqstr,"TXT_BA_DESIGNATION"));
			callstmt.setString(56,m_sn_methods.met_formdata(reqstr,"TXT_EMP_NAME"));
			callstmt.setString(57,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS1"));
			callstmt.setString(58,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS2"));
			callstmt.setString(59,m_sn_methods.met_formdata(reqstr,"TXT_EMP_REFERENCE"));
			callstmt.setString(60,m_sn_methods.met_formdata(reqstr,"TXT_EMP_RDESIGNATION"));
			callstmt.setString(61,m_sn_methods.met_formdata(reqstr,"TXT_EMP_TEL_NO"));
			callstmt.setString(62,m_sn_methods.met_formdata(reqstr,"TXT_EMP_FAX_NO"));
			callstmt.setString(63,m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_CHILDREN"));
			callstmt.setString(64,m_sn_methods.met_formdata(reqstr,"TXT_DEPENDENTS"));*/
			callstmt.setString(65,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(66,m_username);
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_client_tmp';");
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
