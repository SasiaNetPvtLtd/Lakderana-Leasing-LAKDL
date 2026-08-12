
//--
//SCREEN NAME:SAVE PAYMENT SETTLEMENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_PRO_save_payment_settlement extends HttpServlet {
		
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
			String m_fschema_name=m_sn_methods.client_name.trim();
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_sus_ref="";
			conn.setAutoCommit(false);
			
			//back up in (2007-02-19) folder
			
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_PAYMENT_SETMNT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29); END;");
			
			if(m_sus_ref.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{
			callstmt.setString(1,m_sus_ref);
			}

			callstmt.setString(1,m_sus_ref);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENTRY_TYPE"));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAY_AMOUNT")));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_LIC_BRANCH_CODE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_LIC_ACC_NO"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_BRANCH_CODE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ACC_NO"));
			callstmt.setString(11,"Y");
			callstmt.setString(12,"");
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_RECON_STATUS"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_RECON_DATE"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_RECON_BY"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_EFF_VALDATE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_REALISED_DATE"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME"));
			callstmt.setInt(19,0);
			callstmt.setInt(20,0);
			callstmt.setInt(21,0);
			callstmt.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RPT_AMOUNT")));
			callstmt.setInt(23,0);
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(26,m_username);
			callstmt.setString(27,"1");
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_CURR"));
			callstmt.setString(29,"AF_RE_PRO_PAYMENT_SETTLEMENT");		
			callstmt.execute();
			
			if(m_sus_ref.equals("")){
			m_sus_ref=callstmt.getString(1);
			}



			callstmt.close();

			conn.commit();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_PRO_display_payment_settlement';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
	}
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		


