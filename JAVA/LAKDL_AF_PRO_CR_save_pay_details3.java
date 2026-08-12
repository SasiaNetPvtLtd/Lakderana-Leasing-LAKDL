//--
//SCREEN NAME:SAVE PAYMENT DETAILS
//CREATED BY :Delanjali
//DATE/TIME  :
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_pay_details3 extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	java.text.NumberFormat nf,nf1;

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
			
			m_html_client_url=m_sn_methods.html_client_url;
		  m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
		
			conn.setAutoCommit(false);
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();

		  m_msg = "'Information saved successfully'";
			m_url = m_class_url;
				      
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
		  int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			
			String m_form=req.getParameter("form_name");
			String m_ref_no=req.getParameter("ref");
			String m_sus_ref=req.getParameter("sus_ref");
			String m_app_no=req.getParameter("app_no");
			int sel_stage=0;
			String m_client_code=req.getParameter("client_code");
			String m_status=req.getParameter("status");//re-app
			String m_value_date = req.getParameter("value_date");
	
			int m_invoice_count=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_no")); 
			int m_asset_doc_count=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_asset_doc_no")); 
			int m_client_doc_count=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_client_doc_no")); 
			String m_payment_no=req.getParameter("pay_no");
			String m_follow_up_no="";
			String m_follow_up_no1="";
		
			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('"+m_form+"') ");
			if(rs.next()){
			sel_stage=rs.getInt(1);
			}
		
			
		
		
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_MAIN_PAY_SETTLE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38); END;");

			if(m_payment_no.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{
			
			callstmt.setString(1,m_payment_no);
			}
			
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sus_ref);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"));	
			callstmt.setString(5,"V");
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT")));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO"));
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,m_status);
			callstmt.setString(12,"");
			callstmt.setString(13,"-");
			callstmt.setString(14,"-");
			callstmt.setString(15,"-");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME"));
			callstmt.setInt(19,0);
			callstmt.setInt(20,0);
			callstmt.setInt(21,0);
			callstmt.setInt(22,0);//for now
			callstmt.setInt(23,0);
			callstmt.setString(24,"");
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(26,m_username);
			callstmt.setString(27,m_form);//"AF_CR_PRO_PAYMENT_REQUSITION_MAIN"
			callstmt.setInt(28,sel_stage);
			callstmt.setString(29,m_ref_no);
			callstmt.setString(30,m_value_date);
			callstmt.setString(31,"");
			callstmt.setString(32,"");
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INVOICE_NO"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INV_DATE"));
      callstmt.setString(35,"");
			//callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(36,"");
			callstmt.setString(37,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_WHT_AMT"))); //added by ashini on 06-03-2008
			callstmt.setString(38,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NET_AMT"))); //added by ashini on 06-03-2008
			
			callstmt.execute();	
			
			if(m_payment_no.equals("")){
			m_payment_no=callstmt.getString(1);
			}
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			callstmt.registerOutParameter(2,java.sql.Types.CHAR);	
			  
		
		  for (int j = 0; j < m_maxentries; j++) {
			callstmt.setString(1,m_app_no);
		  callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"+(Integer.toString(j))));
			callstmt.setString(3,m_screen_name);
		  callstmt.setString(4,m_username);
		  callstmt.setString(5,m_scr_name);
		  callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j))));
				
			String m_condition=(String)m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
		  callstmt.setString(7,"COLLE_DOC");//MODIFIED NUWAN DE SILVA
      callstmt.setString(8,"AF");
      callstmt.setString(9,"PENDING");
      callstmt.setString(10,"CREDIT");


				if(m_condition.equals("")){
				break;
				}
				
				
			  callstmt.execute();
			}		
			conn.setAutoCommit(true);
			callstmt.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Information saved successfully-'+ '"+m_payment_no+"')");
			
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?sql=main_page&status_new=&status_edit=&screen_type=NEW&chksql=B&chksql2=A';");
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
		




