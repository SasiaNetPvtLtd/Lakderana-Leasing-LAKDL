//--
//SCREEN NAME:
//CREATED BY: SANDUN 
//DATE/TIME:02/10/2008 
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.math.BigDecimal;

public class LAKDL_AF_CR_PRO_Commission_Details_Save extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
  	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;


	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false); 

			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			m_client_name = m_sn_methods.client_name.trim();
            String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			
			
            m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			//out.println("conn"+conn);
			//out.println(reqstr);
			
            String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			m_msg = "'Information saved successfully.'";
			m_url = m_class_url;				
			
			String m_cheque_date=(String)m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_DD")+"-"+
								 (String)m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_MM")+"-"+
								 (String)m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_YY");
			
			String m_received_date=(String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIVED_DD")+"-"+
		  					       (String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIVED_MM")+"-"+
							       (String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIVED_YY");
			
     	    callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_IS_PRO_SAVE_ASET_INSUR_COMI(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
						
			callstmt.setString(1,(String)m_sn_methods.met_formdata(reqstr,"TXT_POLICY_NO"));
			callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_DEBIT_NO"));
			callstmt.setString(3,(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setBigDecimal(4,new BigDecimal(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_BASIC"))));
			callstmt.setBigDecimal(5,new BigDecimal(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_RCC"))));
			callstmt.setString(6,(String)m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO"));
			callstmt.setString(7,(String)m_sn_methods.met_formdata(reqstr,"TXT_BANK"));			
			callstmt.setString(8,m_cheque_date);			
			callstmt.setString(9,m_received_date);
			callstmt.setString(10,m_username);
			callstmt.execute();			
		  	callstmt.close();
			
			conn.commit(); 
			conn.close();
				

	   	    out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Commission_Details_Dispaly?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg()'></body>");
			out.println("</html>");

			out.flush();
            out.close();
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
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
		
		
	}
	}
}
