// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:22-12-2006

    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_OP_save_debtor_creation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
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
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
      String m_client_code="",m_facility_code="",m_debtor_code="",m_client_code_hid="";
			String m_date="";
			int m_num=0;
			int m_tnum=0;
			
			m_msg = "'Information saved successfully'";

			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_MAS_DEBTOR_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54); END;");
			
			m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			if (m_client_code.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_client_code);
      }
      
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_RELATIONSHIP"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FULL_NAME"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_GROUP"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CRIB"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CRIB_COMMENT"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_STATUS"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_ADDRESS1"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_ADDRESS2"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CITY"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_COLLECTION_AREA"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_REFERENCE"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_TELEPHONE"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FAXNO"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_OFFICE_TELEPHONE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_EMAIL"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_MOBILE"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_KEY_DECISION_MAKER"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CONT_PERSON"));		
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_DESIG_PAYMENT"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_STATUS"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_ADDRESS1"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_ADDRESS2"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_CONPERSON"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_TELEPHONE"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_FAX"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_EMAIL"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_STATUS"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_HOMEADDRESS1"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_HOMEADDRESS2"));
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_CITY"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_TELEPHONE"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_FAX"));
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_EMAIL"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_LEGAL_ST_BUSINESS"));
			callstmt.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_BUSINESS_CERTIFICATE_NO"));
			callstmt.setString(38,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL")));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_YY");
			callstmt.setString(39,m_date);
			callstmt.setString(40,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_NO"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_YY");
			callstmt.setString(41,m_date);
			callstmt.setString(42,m_sn_methods.met_formdata(reqstr,"TXT_WITH_HOLDING_TAX"));
			callstmt.setString(43,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_IDNO"));
			callstmt.setString(44,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_PASSPORT"));
			callstmt.setString(45,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TITLE"));
			callstmt.setString(46,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FNAME"));
			callstmt.setString(47,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_SNAME"));
			callstmt.setString(48,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_INITIALS"));
			callstmt.setString(49,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_ONAME"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_YY");
			callstmt.setString(50,m_date);
			callstmt.setString(51,m_sn_methods.met_formdata(reqstr,"TXT_GENDER"));
			callstmt.setString(52,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(53,m_username);
			callstmt.setString(54,m_sn_methods.met_formdata(reqstr,"TXT_3RD_PARTY_DEBTOR_DET"));
			callstmt.execute();
			
			if (m_client_code.equals("")){
				m_client_code=callstmt.getString(1);
			}
			
			callstmt.close();

			//-----------------Added by Mahela on 26-12-2006 --------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_DEBT_ASSIGN(:1,:2,:3,:4,:5); END;"); 

			m_facility_code=(String)m_sn_methods.met_formdata(reqstr,"hid_FACILITY_NO");
			m_client_code_hid=(String)m_sn_methods.met_formdata(reqstr,"hid_CLIENT_CODE");
			m_debtor_code=m_client_code;
			

			callstmt.setString(1,m_facility_code);
			callstmt.setString(2,m_client_code_hid);
			callstmt.setString(3,m_debtor_code);
			callstmt.setString(4,m_username);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.execute();
			callstmt.close();
			
			//-----------------------------------------------------------------------------------------------------------------------------------

			
			m_msg = "'Information saved successfully, Debtor Code "+m_client_code+"'";
			
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_display_debtor_creation';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

