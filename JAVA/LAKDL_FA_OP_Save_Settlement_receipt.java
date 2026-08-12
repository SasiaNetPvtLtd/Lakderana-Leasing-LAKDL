// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

         
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Settlement_receipt extends HttpServlet {

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

			    
			m_msg = "'Information saved successfully for Settlement Receipt :";
			//------------------------------------------------------------------------------------------
			     
			String m_settle_receipt_no=m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_RECEIPT_NO");
			String m_settle_type=m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_TYPE");
			String m_facility_no=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTLE_RECEIPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24); END;"); 

			if (m_settle_receipt_no.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_settle_receipt_no);
      }

			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_TYPE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_BATCH_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"));
			String m_date=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_YY");
			callstmt.setString(8,m_date);
			callstmt.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT")));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CURRENCY_CODE"));
			callstmt.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE")));
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_RPT_CURR_AMT")));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_MODE"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_PAYER_ACCOUNT"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_PAYER_BRANCH"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_COMMENTS"));
			callstmt.setString(18,m_username);
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_YY");
			callstmt.setString(20,m_date);
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_TEMP_RECEIPT_NO"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_COLL_OFFICER"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_RECE_SETT_TYPE")); //-ADDED BY ASHINI ON 22-08-2008-
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_RETURN_CHEQUE_SETTLEMENT")); // Added by Udara on 27-05-2011
			
			callstmt.execute();
			
			if (m_settle_receipt_no.equals("")){
				m_settle_receipt_no=callstmt.getString(1);
			}
			callstmt.close();
			//------------------------------------------------------------------
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETT_RECEIPT_REBANK(:1,:2,:3,:4,:5,:6,:7); END;"); 
			String m_redeposit_num=(String)m_sn_methods.met_formdata(reqstr,"hid_redeposit_receipt");
			int m_redeposit_count=Integer.parseInt(m_redeposit_num);
			
			if(m_redeposit_count>0){
				for(int k=1;k<=m_redeposit_count;k++){
				//out.println("xxx"+m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_NO_"+k)); 	
				callstmt2.setString(1,m_settle_receipt_no);
				callstmt2.setString(2,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT")));
				callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_NO_"+k));
				callstmt2.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_TOTAL_"+k)));
				callstmt2.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RE_DEPOSIT_RECEIPT_AMT_"+k)));
				callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt2.setInt(7,k);
				callstmt2.execute();
				}
			}
			else{
				callstmt2.setString(1,m_settle_receipt_no);
				callstmt2.setString(2,"");
				callstmt2.setString(3,"");
				callstmt2.setString(4,"");
				callstmt2.setString(5,"");
				callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt2.setInt(7,0);
				callstmt2.execute();
			}
			callstmt2.close();
			//------------------------------------------------------------------
			if(m_settle_type.equals("IB")){
				int m_num=1;
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_RECEIPT_ALLO_ENTER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
				m_num=Integer.parseInt(m_scr_num);
				int tnum=0;
				
				if(m_num>0){
					for(int i=0;i<m_num;i++){
						
						String m_option=m_sn_methods.met_formdata(reqstr,"TXT_POD_STATUS_"+i).trim();
						
						if(m_option.equals("on")){
							tnum++;
							callstmt1.setString(1,m_settle_receipt_no);
							callstmt1.setString(2,m_client_code);
							callstmt1.setString(3,m_facility_no);
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_BATCH_NO_"+i).trim());
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO_"+i).trim());
							callstmt1.setString(7,m_settle_type);
							callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ALLO_BALANCE_"+i)));
							callstmt1.setString(9,m_username);
							callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setInt(11,tnum);
							callstmt1.execute();
						}
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------

			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_settle_receipt_no+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Settlement_receipt_enter';");
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

