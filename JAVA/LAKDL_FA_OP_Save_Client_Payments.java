// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

           
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Client_Payments extends HttpServlet {

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
  
			m_msg = "'Information saved successfully for Payment No :";
			//------------------------------------------------------------------------------------------
			String m_payment_no=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO");
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_PAYMENTS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20); END;"); 

			if (m_payment_no.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_payment_no);
      }

			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_AMOUNT")));
			String m_date=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_YY");
			callstmt.setString(5,m_date);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_MODE"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_LICEN_BRANCH"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_LICEN_ACCOUNT"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_PAYER_BRANCH"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_PAYER_ACCOUNT"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_COMMENTS"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_YY");
			callstmt.setString(13,m_date);
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CURRENCY_CODE"));
			callstmt.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE")));
			callstmt.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_RPT_CURR_AMT")));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(18,m_username);
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_3RD_PARTY"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_3RD_PARTY_NAME"));
			
			callstmt.execute();
			
			if (m_payment_no.equals("")){
				m_payment_no=callstmt.getString(1);
			}
		
			callstmt.close();
		
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_payment_no+"');");
			//Added by Dineth on 27-07-2009
			out.println("m_save_msg = \"Do you want to print the Payment Requisition?\";");
			out.println("if(confirm(m_save_msg)){");		
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_OP_Payment_Requisition?chksql=main_page&payment_no="+m_payment_no+"&print=TRUE \";"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
	    out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Client_Payments';");
      out.println("} else{");	
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Client_Payments';");
			out.println("}");
			
			//End by Dineth on 27-07-2009
			//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Client_Payments';");
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
			if(conn!=null){try{conn.close();}catch(Exception e){}}
			if(out!=null){try{out.close();}catch(Exception e){}}
		}

	}
}

