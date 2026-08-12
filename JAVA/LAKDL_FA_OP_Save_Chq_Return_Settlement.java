// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

         
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Chq_Return_Settlement extends HttpServlet {

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
			    
			m_msg = "'Information saved successfully ";
			//------------------------------------------------------------------------------------------
			     
			String m_settle_receipt_no=m_sn_methods.met_formdata(reqstr,"TXT_RECEIPT_NO");

			//------------------------------------------------------------------
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETT_CHQ_RETURN(:1,:2,:3,:4,:5,:6,:7); END;"); 
			String m_redeposit_num=(String)m_sn_methods.met_formdata(reqstr,"hid_redeposit_receipt");
			int m_redeposit_count=Integer.parseInt(m_redeposit_num);
			
			if(m_redeposit_count>0){
				for(int k=1;k<=m_redeposit_count;k++){
				callstmt2.setString(1,m_settle_receipt_no);
				callstmt2.setString(2,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_REC_AMT")));
				callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_NO_"+k));
				callstmt2.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_TOTAL_"+k)));
				callstmt2.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_AMT_"+k)));
				callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt2.setInt(7,k);
				callstmt2.execute();
				}
			}
			callstmt2.close();
			//------------------------------------------------------------------

			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_settle_receipt_no+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Cheque_Return_Settlement';");
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

