// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_invoice_enter extends HttpServlet {

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

			String m_date="";
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			    
			m_msg = "'Information saved successfully for Invoice Batch No:";
			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_INVOICE_ENTER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;"); 

			String m_facility_code=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			String m_batch_no=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_NO");
			String m_batch_serial_no=m_sn_methods.met_formdata(reqstr,"TXT_BATCH_SERIAL_NO");//add by indika 10/06/08
			
			if (m_batch_no.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_batch_no);
      }
			  
			callstmt.setString(1,m_batch_no);
			callstmt.setString(2,m_facility_code);
			callstmt.setString(3,m_client_code);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_COUNT"));
			callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_AMOUNT")));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_DATE_DD")+"-"+
									 m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_DATE_MM")+"-"+
									 m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_DATE_YY");
			callstmt.setString(6,m_date);
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_BATCH_NO"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_BATCH_SERIAL_NO"));
			callstmt.execute();
			
			if (m_batch_no.equals("")){
				m_batch_no=callstmt.getString(1);
			}
		
			callstmt.close();
			//------------------------------------------------------------------
	
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_INVOICE_DETAIL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19); END;");//add 17 by indika 03/09/08
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_invoice_count");
			m_num=Integer.parseInt(m_scr_num);

			m_tnum=0;
 
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					m_tnum++;
					callstmt1.setString(1,m_facility_code);
					callstmt1.setString(2,m_client_code);
					callstmt1.setString(3,m_batch_no);
					callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
					callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO_"+i).trim());
					callstmt1.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT_"+i)));
					m_date=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_DD_"+i).trim()+"-"+
								 m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_MM_"+i).trim()+"-"+
								 m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_YY_"+i).trim();
					callstmt1.setString(7,m_date);
					callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_COMMENTS_"+i).trim());
					callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CURRENCY_CODE_"+i).trim());
					callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE_"+i)));
					callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_RPT_CURR_AMT_"+i)));
					callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt1.setString(13,m_username);
					callstmt1.setInt(14,m_tnum);
					callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_SEQ_NO_"+i).trim());
					callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_EDIT_STATUS_"+i).trim());
					callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE_NO_"+i).trim());//add by indika 04/09/08
					callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_DISPUTE_CODE_"+i).trim());//add by indika 04/09/08
					callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DISPUTE_AMOUNT_"+i).trim()));//add by indika 04/09/08
					//callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_SERIAL_NO_"+i).trim());//add by indika 03/09/08
					callstmt1.execute();
				}
			}
			callstmt1.close();
	
			//-------------------------------------------------------------------------------------------

			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CHECK_BATCH(:1,:2,:3,:4); END;"); 

			
			//out.println(m_batch_no);
			callstmt2.setString(1,m_batch_no);
			//callstmt2.setString(1,"123");
			callstmt2.setString(2,m_facility_code);
			callstmt2.setString(3,m_client_code);
			callstmt2.setString(4,m_username);
			callstmt2.execute();
			callstmt2.close();
			//------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_batch_no+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_enter';");
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

