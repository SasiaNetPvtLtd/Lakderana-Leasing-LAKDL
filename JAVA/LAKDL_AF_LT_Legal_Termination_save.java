// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_LT_Legal_Termination_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmtDet;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
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
			
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			
			m_msg = "'Information saved successfully'";
			
			String m_status;
			String m_finance_no;	
			String m_invoice_no;
			String m_client_code;
			String m_inv_type;
			String m_termination_no;
			
			double m_tot_amt;
			double m_set_amt;
			double m_bal_amt;
			double m_wave_amt;
			double m_tot_wave_amt;
			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_LT_SAVE_LEGAL_TERMINATION(:1,:2,:3,:4,:5); END;");
			callstmtDet=conn.prepareCall("BEGIN "+m_schema_name+".AF_LT_SAVE_LEG_TERMINATION_DET(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			m_finance_no=m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO");
			m_tot_wave_amt = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_wave_amt")));
			m_client_code = m_sn_methods.met_formdata(reqstr,"hid_client_code");
			
			
			if(m_num>0){
				
				callstmt.registerOutParameter(1, java.sql.Types.CHAR);
				callstmt.setString(2,m_finance_no);
				callstmt.setString(3,m_client_code);
				callstmt.setDouble(4,m_tot_wave_amt);
				callstmt.setString(5,m_username);
				callstmt.execute();
				
				
				m_termination_no = callstmt.getString(1);
				for(int i=1;i<=m_num;i++){
					
					m_status     = m_sn_methods.met_formdata(reqstr,"received_"+i);
					m_invoice_no = m_sn_methods.met_formdata(reqstr,"invNo_"+i);
					m_tot_amt = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"totAmt_"+i)));
					m_set_amt = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"setAmt_"+i)));
					m_bal_amt = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"balAmtId_"+i)));
					m_wave_amt = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"waveAmtId_"+i)));
					m_inv_type = m_sn_methods.met_formdata(reqstr,"invType_"+i);
					
					
					if(m_status.equals("on")){
						
						
						callstmtDet.setString(1,m_termination_no);
						callstmtDet.setString(2,m_invoice_no);
						callstmtDet.setDouble(3,m_tot_amt);
						callstmtDet.setDouble(4,m_set_amt);
						callstmtDet.setDouble(5,m_bal_amt);
						callstmtDet.setDouble(6,m_wave_amt);
						callstmtDet.setString(7,m_inv_type);
						callstmtDet.setString(8,m_username);
						callstmtDet.execute();
						
						
						
						
						
					}
				}
			}
			//callstmt.close();
			
			
			//conn.commit();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination';");
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

