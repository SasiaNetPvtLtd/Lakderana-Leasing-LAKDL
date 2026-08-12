
//--
//SCREEN NAME:SAVE PAYMENT DETAILS - NEW
//CREATED BY :delanjali	
//DATE/TIME  :25-01-2007
//NOTES      :

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_main_screen_payment_temp1 extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	String m_mesage;


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
			stmt = conn.createStatement();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();

			int sel_stage=0;
			int m_chksql=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count")); 

			
			
			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_PRO_CR_TEMP_PAYMENT1') ");
			while(rs.next()){
			sel_stage=rs.getInt(1);
			
			}

			for (int k=0; k<m_chksql; k++) 
			{
			
			
	
			
			
			String m_pay_no=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_MAIN_TEMP_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APP_NO_"+k));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO_"+k));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+k));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO_TEMP_"+k));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO_TEMP_"+k));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO_TEMP_"+k));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO_"+k));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO_"+k));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO_"+k));
			callstmt.setInt(11,sel_stage);
			callstmt.setString(12,"AF_PRO_CR_TEMP_PAYMENT1");
			callstmt.setString(13,"APPRO1");
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(15,m_username);

			String m_status_select=m_sn_methods.met_formdata(reqstr,"TXT_STATUS"+k);
			
			if(m_status_select.equals("N")){
			callstmt.setString(16,"NEW");
			}
			else if(m_status_select.equals("O")){
			callstmt.setString(16,"OLD");
			}
			callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAID_AMT_"+k)));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_"+k));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_VAL_DATE_"+k));
			callstmt.registerOutParameter(20,java.sql.Types.CHAR);

			
				if  (m_pay_no.trim().equals("")) {
						  break;
				}
				callstmt.execute();
				
				
				
				m_mesage=callstmt.getString(20);
				}		
			
			
				}		

			
			callstmt.close();

			conn.commit();
			
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			if(m_mesage.equals("1")){
			out.println("alert('Approved Payment exsits ,cannot cancel')");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp1?sql=main_page';");
			}
			if(m_mesage.equals("0") || m_mesage==null){
			out.println("alert("+m_msg+");");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp1?sql=main_page';");
			}
			
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
		

