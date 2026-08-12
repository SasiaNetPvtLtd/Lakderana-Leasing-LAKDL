// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
           
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_settlement_schedule_enter extends HttpServlet {

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
			
			m_msg = "'Information saved successfully for Settlement Schedule Reference No: ";
			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTLEMENT_SCHEDULE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17); END;"); 

			String m_pod_cheque_refno=m_sn_methods.met_formdata(reqstr,"TXT_POD_REF_NO");
			String m_allo_type=m_sn_methods.met_formdata(reqstr,"TXT_ALLO_TYPE");
			String m_receipt_no="";
			
			if (m_pod_cheque_refno.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_pod_cheque_refno);
      }

			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_ACC_NO"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_DD")+"-"+
						 m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_MM")+"-"+
						 m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_YY");
			callstmt.setString(5,m_date);
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_AMOUNT")));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_COMMENTS"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CURRENCY_CODE"));
			callstmt.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE")));
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_RPT_CURR_AMT")));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_MODE"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_TEMP_RECEIPT_NO"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_COLL_OFFICER"));
			callstmt.registerOutParameter(17,java.sql.Types.CHAR);
			callstmt.execute();
			
			if (m_pod_cheque_refno.equals("")){
				m_pod_cheque_refno=callstmt.getString(1);
			}
			if(m_receipt_no.equals("")){
				m_receipt_no=callstmt.getString(17);
			}
			callstmt.close();
			//------------------------------------------------------------------
			if(m_allo_type.equals("IA")){
				m_num=1;
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTL_SCHEDULE_ALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
				m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
				m_num=Integer.parseInt(m_scr_num);
				int tnum=0;
				
				if(m_num>0){
					for(int i=0;i<m_num;i++){
						
						String m_option=m_sn_methods.met_formdata(reqstr,"TXT_POD_STATUS_"+i).trim();
						
						if(m_option.equals("on")){
							tnum++;
							callstmt1.setString(1,m_pod_cheque_refno);
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_BATCH_NO_"+i).trim());
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO_"+i).trim());
							callstmt1.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INV_ALLO_"+i)));
							callstmt1.setString(8,m_username);
							callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setInt(10,tnum);
							callstmt1.execute();
						}
					}
				}
				callstmt1.close();
			}
			//-----------------------------------------------------------------
			/*callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTL_SCH_CHARGES(:1,:2,:3,:4); END;"); 
			callstmt2.setString(1,m_pod_cheque_refno);
			callstmt2.setString(2,m_receipt_no);
			callstmt2.setString(3,m_username);
			callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt2.execute();
			callstmt2.close();*/
			//------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_pod_cheque_refno+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_settlement_schedule_enter';");
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

