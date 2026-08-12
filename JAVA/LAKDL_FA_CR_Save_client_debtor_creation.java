// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_CR_Save_client_debtor_creation extends HttpServlet {
		
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
			
      String m_facility_code="";
			String m_client_code="";
			String m_debtor_code="";

			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			
			m_msg = "'Information saved successfully'";

			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_DEBT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;"); 

			m_facility_code=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			m_debtor_code=m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE");
			

			callstmt.setString(1,m_facility_code);
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_debtor_code);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_MMGR"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CODE"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_FEE_CODE"));
			callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_LIMIT")));
			callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_PERIOD")));
			callstmt.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOL_CREDIT_PERIOD")));
			callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RES_MARGIN")));
			callstmt.setString(11,m_username);
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_DESIG_PAYMENT"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CONT_PERSON"));
			callstmt.execute();
			callstmt.close();
			//------------------------------------------------------------------
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_DEBT_PRODUCT(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_product_count");
			m_num=Integer.parseInt(m_scr_num);	
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_product_code=m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_FEATURE_CODE_"+i);
					if(!m_product_code.equals("")){
						callstmt1.setString(1,m_facility_code);
						callstmt1.setString(2,m_client_code);
						callstmt1.setString(3,m_debtor_code);
						callstmt1.setString(4,m_product_code);
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_FEATURE_PARAM_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(7,m_username);
						callstmt1.setInt(8,m_tnum);
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_DEBT_FEE(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_fee_count");
			m_num=Integer.parseInt(m_scr_num);
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_fee_code=m_sn_methods.met_formdata(reqstr,"TXT_FEE_CODE_"+i);
					if(!m_fee_code.equals("")){
						callstmt1.setString(1,m_facility_code);
						callstmt1.setString(2,m_client_code);
						callstmt1.setString(3,m_debtor_code);
						callstmt1.setString(4,m_fee_code);
						callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_FEE_VALUE_"+i)));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(7,m_username);
						callstmt1.setInt(8,m_tnum);
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_debtor';");
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

