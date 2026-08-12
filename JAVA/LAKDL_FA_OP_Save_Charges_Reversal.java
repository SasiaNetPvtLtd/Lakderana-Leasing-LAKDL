// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Charges_Reversal extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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

			String m_date="";
			    
			m_msg = "'Information saved successfully ";

			String facNo = m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO").trim();
			String feeChAmnt = m_sn_methods.met_formdata(reqstr,"hid_fee_charge_amount").trim();
			String cliCode = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE").trim();
			String dates = m_sn_methods.met_formdata(reqstr,"TXT_ADJ_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_YY");	
			//out.println(facNo+"   "+feeChAmnt+"    "+cliCode+"   "+dates+"   "+m_username);
			/*
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_CLIENT_CHARGES_REVERSE(:1,:2,:3); END;");
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_ADJ_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_YY");
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_REVERSAL_NO").trim());
			callstmt1.setString(2,m_date.trim());
			callstmt1.setString(3,m_username);
			callstmt1.execute();
			
			callstmt1.close();
	  		*/
			// Modified by Udara Somathilake on 31-03-2010
			String m_charge_ref_no="";
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CHARGE_REVERSAL_ADD(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			//callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_REVERSAL_NO").trim());
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO").trim());
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE").trim());
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_fee_charge_amount").trim());
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_ADJ_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_ADJ_YY");
			callstmt1.setString(5,m_date.trim());
			callstmt1.setString(6,m_username);
			callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"hid_fee_code").trim());
			callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"hid_fee_desc").trim());
			callstmt1.execute();
			
			//if (m_charge_ref_no.equals("")){
			//	m_charge_ref_no=callstmt1.getString(1);
			//}
		
			callstmt1.close();	
			
			//-------------------------------------------------------------------------------------------
			conn.commit();
			
			

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Charges_Reversal';");
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

