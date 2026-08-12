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

public class LAKDL_AF_PRO_CR_save_pay_details1 extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	java.text.NumberFormat nf,nf1;
	//String m_payment_no="";
  
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
			
			//out.println(reqstr);
				      
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
					 
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_curr_code=req.getParameter("curr_code");
		  String m_form=req.getParameter("form_name");
			String m_ref_no=req.getParameter("ref");
			String m_sus_ref=req.getParameter("sus_ref");
			String m_app_no=req.getParameter("app_no");
			int sel_stage=0;
			String m_client_code=req.getParameter("client_code");
			String m_status=req.getParameter("status");//re-app
			String m_value_date = req.getParameter("value_date");
			String m_screen_type=m_sn_methods.met_formdata(reqstr,"hid_screen_type");
			if(m_screen_type==null){
			m_screen_type="PAY";
			}
	    //out.println("m_screen_type"+m_screen_type);
			
			int m_maxentries     			= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			int m_sus_no_records      = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"sus_no_records"));
			int m_invoice_count				= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_no")); 
			int m_asset_doc_count			= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_asset_doc_no")); 
			int m_client_doc_count		=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_client_doc_no")); 
			
			//added by nuwan de silva on 03-10-07---------------------------------------------------
			int m_no_of_payees=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_of_payees")); 

			String m_payment_no="";
			String m_follow_up_no="";
			String m_follow_up_no1="";
			String m_app_status="N";
			String m_client_app_status="N";
			String m_payee_name="";
			String m_settle_amount="";
			String m_category="Y";

		 // out.println("m_no_of_payees"+m_no_of_payees);
			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('"+m_form+"') ");
			if(rs.next()){
			sel_stage=rs.getInt(1);
			}
		
			String m_letter_date ="";
			/*String m_letter_date=m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY");
			if(m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD").equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM").equals("") || m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY").equals("")){
			m_letter_date="";
			}
						
			String m_tax_inv_date=m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_YY");
			if(m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_DD").equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_MM").equals("") || m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_YY").equals("")){
			m_tax_inv_date="";
			}			
			*/
			
			int _m_paycount_all=0;
			
			for (int sus_rec=0;sus_rec<m_sus_no_records; sus_rec++)  {
			_m_paycount_all		= _m_paycount_all+Integer.parseInt(m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO_COUNT"+sus_rec)); 
			}
			
			
			String _m_payment_type="";
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_MAIN_PAY_SETTLE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38); END;");
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PAY_SET_BR_DWN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			
			
			if (_m_paycount_all > 0) {
			for (int sus_rec=0;sus_rec<m_sus_no_records; sus_rec++)  {
			
			int _m_paycount		= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO_COUNT"+sus_rec)); 
			_m_payment_type   = m_sn_methods.met_formdata(reqstr,"TXT_PARTY"+sus_rec);	
			
			
			if(_m_payment_type.equals("2")){
			m_category="N";
			m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"));
			m_payee_name=m_sn_methods.met_formdata(reqstr,"TXT_PAYER");
			
			String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(sus_rec)));			
			
			if(m_chk_status.equals("on")){
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sus_ref);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"+sus_rec));	
			callstmt.setString(5,"V");
			callstmt.setString(6,m_settle_amount); //added by nuwan de silva on 05-10-07
			callstmt.setString(7,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(8,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,m_status);
			callstmt.setString(12,"");
			callstmt.setString(13,"-");
			callstmt.setString(14,"-");
			callstmt.setString(15,"-");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			callstmt.setString(18,m_payee_name); //added by nuwan de silva on 05-10-07
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
			callstmt.setString(31,m_curr_code);
			callstmt.setString(32,m_letter_date);  
			callstmt.setString(33,"");  
			callstmt.setString(34,"");  
			callstmt.setString(35,m_category);
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setInt(37,0); // added by ashini on 06-03-2008
			callstmt.setInt(38,0);// added by ashini on 06-03-2008
			callstmt.execute();
			m_payment_no=callstmt.getString(1);
		 
			//callstmt.close();
						
			callstmt2.setString(1,m_payment_no);
			callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(sus_rec))));
			callstmt2.setString(3,m_value_date);
		  m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT"+sus_rec));
			
			callstmt2.setString(4,m_settle_amount);
			callstmt2.setString(5,"");
			callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(sus_rec))));
			callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt2.setString(8,m_username);
			callstmt2.setString(9,m_scr_name);
			callstmt2.setString(10,"V");
			callstmt2.setString(11,"");
			callstmt2.setString(12,"");
			callstmt2.setString(13,"0");
		  callstmt2.execute();
			
			}
			
			}	else if(_m_payment_type.equals("1")){
			
			for (int _pay=0;_pay<_m_paycount; _pay++)  {
			
			m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_AMOUNT"+sus_rec+_pay));
			m_payee_name=m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME"+sus_rec+_pay);
			out.println(m_settle_amount+"= ****m_payee_name="+m_payee_name);
			
			String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(sus_rec)));			
			
			if(m_chk_status.equals("on")){
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sus_ref);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"+sus_rec));	
			callstmt.setString(5,"V");
			callstmt.setString(6,m_settle_amount); //added by nuwan de silva on 05-10-07
			callstmt.setString(7,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(8,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,m_status);
			callstmt.setString(12,"");
			callstmt.setString(13,"-");
			callstmt.setString(14,"-");
			callstmt.setString(15,"-");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			callstmt.setString(18,m_payee_name); //added by nuwan de silva on 05-10-07
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
			callstmt.setString(31,m_curr_code);
			callstmt.setString(32,m_letter_date);  
			callstmt.setString(33,"");  
			callstmt.setString(34,"");  
			callstmt.setString(35,m_category);
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setInt(37,0); // added by ashini on 06-03-2008
			callstmt.setInt(38,0);// added by ashini on 06-03-2008
			callstmt.execute();
			m_payment_no=callstmt.getString(1);
		  //callstmt.close();
			
			
			callstmt2.setString(1,m_payment_no);
			callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(sus_rec))));
			callstmt2.setString(3,m_value_date);
			m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_AMOUNT"+sus_rec+_pay));
			callstmt2.setString(4,m_settle_amount);
			callstmt2.setString(5,"");
			callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(sus_rec))));
			callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt2.setString(8,m_username);
			callstmt2.setString(9,m_scr_name);
			callstmt2.setString(10,"V");
			callstmt2.setString(11,"");
			callstmt2.setString(12,"");
			callstmt2.setString(13,"0");
		  callstmt2.execute();
			}
			}
			}
			}
			
			
			}else{ //payment for only one vendor .........................................................................
			m_category="N";
			m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"));
			m_payee_name=m_sn_methods.met_formdata(reqstr,"TXT_PAYER");
			int count=0;
			//String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(sus_rec)));			
			//if(m_chk_status.equals("on")){
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sus_ref);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"+count));	
			callstmt.setString(5,"V");
			callstmt.setString(6,m_settle_amount); //added by nuwan de silva on 05-10-07
			callstmt.setString(7,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(8,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,m_status);
			callstmt.setString(12,"");
			callstmt.setString(13,"-");
			callstmt.setString(14,"-");
			callstmt.setString(15,"-");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			callstmt.setString(18,m_payee_name); //added by nuwan de silva on 05-10-07
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
			callstmt.setString(31,m_curr_code);
			callstmt.setString(32,m_letter_date);  
			callstmt.setString(33,"");  
			callstmt.setString(34,"");  
			callstmt.setString(35,m_category);
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setInt(37,0); // added by ashini on 06-03-2008
			callstmt.setInt(38,0);// added by ashini on 06-03-2008
			callstmt.execute();
			m_payment_no=callstmt.getString(1);
		 
			//callstmt.close();
      
			for (int sus_rec=0;sus_rec<m_sus_no_records; sus_rec++)  {
			
   		String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(sus_rec)));			
			if(m_chk_status.equals("on")){

			callstmt2.setString(1,m_payment_no);
			callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(sus_rec))));
			callstmt2.setString(3,m_value_date);
		  m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT"+sus_rec));
			
			callstmt2.setString(4,m_settle_amount);
			callstmt2.setString(5,"");
			callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(sus_rec))));
			callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt2.setString(8,m_username);
			callstmt2.setString(9,m_scr_name);
			callstmt2.setString(10,"V");
			callstmt2.setString(11,"");
			callstmt2.setString(12,"");
			callstmt2.setString(13,"0");
		  callstmt2.execute();
			}
			}
			
			
			}
			
			
			
			callstmt.close();
			callstmt2.close();
			
			///end payment procedure //////////////////////////////////////////////////////////////////////////////////////
			
			
			/********************************************** COMMENT NS  28-09-2009 THIRD PARTY PAYMENT CHECK ADDED ABOVE SECTION ***********************************************
			
			
			int row_no=0;
			
			String m_party_type=m_sn_methods.met_formdata(reqstr,"TXT_PARTY"+row_no);	//addd by nuwan de silva on 05-10-07---------
			out.println("m_party_type"+m_party_type);
		  if(m_party_type.equals("2")){
			m_no_of_payees=1;
			m_category="N";
			}
			int count_no=0;
		  out.println("m_no_of_payees"+m_no_of_payees);
			
			for (int line=0;line<m_no_of_payees; line++)  {
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_MAIN_PAY_SETTLE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38); END;");
			out.println("m_party_type"+m_party_type);
			
			
		  if(m_party_type.equals("2")){
			 m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"));
			 m_payee_name=m_sn_methods.met_formdata(reqstr,"TXT_PAYER");
			 out.println(m_settle_amount+"=m_payee_name="+m_payee_name);
			
			}
			else if(m_party_type.equals("1")){
			 m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_AMOUNT"+row_no+line));
			 m_payee_name=m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME"+row_no+line);
			 out.println(m_settle_amount+"= ****m_payee_name="+m_payee_name);
				
			}
			

			
			
			//m_payee_name=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYER"));
			//m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"));
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			//callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"));	
			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sus_ref);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"+row_no));	
			callstmt.setString(5,"V");
			//callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"))); //comment by nuwan de silva on 05-10-07
			//callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE").trim()); //added by nuwan de silva on 05-10-07
			//callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO").trim()); //added by nuwan de silva on 05-10-07
			
			callstmt.setString(6,m_settle_amount); //added by nuwan de silva on 05-10-07
			callstmt.setString(7,""); //added by nuwan de silva on 05-10-07
			callstmt.setString(8,""); //added by nuwan de silva on 05-10-07
			
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,m_status);
			callstmt.setString(12,"");
			callstmt.setString(13,"-");
			callstmt.setString(14,"-");
			callstmt.setString(15,"-");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			//callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_PAYER")); //comment by nuwan de silva on 05-10-07
			callstmt.setString(18,m_payee_name); //added by nuwan de silva on 05-10-07
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
			callstmt.setString(31,m_curr_code);
			callstmt.setString(32,m_letter_date);  
			callstmt.setString(33,"");  
			callstmt.setString(34,"");  
			//callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INVOICE_NO"));
			//callstmt.setString(34,m_tax_inv_date);
			callstmt.setString(35,m_category);
			//callstmt.setString(36,);
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setInt(37,0); // added by ashini on 06-03-2008
			callstmt.setInt(38,0);// added by ashini on 06-03-2008
			
			callstmt.execute();
			m_payment_no=callstmt.getString(1);
			
			//out.println("m_payment_no out" +m_payment_no);
		  callstmt.close();
			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PAY_SET_BR_DWN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			for (int j = 0; j < m_sus_no_records; j++) {
			String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));			
			if(m_chk_status.equals("on")){
			callstmt.setString(1,m_payment_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(j))));
			callstmt.setString(3,m_value_date);
			//out.println("m_settle_amount " +m_settle_amount);
	    if(m_party_type.equals("2")){
		  m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT"+j));
			}
			else if(m_party_type.equals("1")){
			 m_settle_amount=(String)m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_AMOUNT"+row_no+count_no));
			
			}
			callstmt.setString(4,m_settle_amount);
			//out.println("m_party_type " +m_party_type);
      //out.println("m_settle_amount " +m_settle_amount);
			
			//callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT"+(Integer.toString(j)))));
			
			callstmt.setString(5,"");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(j))));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,m_scr_name);
			callstmt.setString(10,"V");
			callstmt.setString(11,"");
			callstmt.setString(12,"");
			callstmt.setString(13,"0");
			//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INVOICE_NO"));
			//callstmt.setString(12,m_tax_inv_date);
			
		  callstmt.execute();
			
			}
			}
			
			
			
			count_no=count_no+1;
		  */
			
			
			//////////////////for (int line=0;line<m_no_of_payees; line++)  { --BY NS 28-09-2009
		
			for (int d=0;d<m_invoice_count; d++) 
			{
			String m_print_date=m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_YY"+d).equals("")){
			m_print_date="";
			}
			String m_insurance_date=m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE_YY"+d).equals("")){
			m_insurance_date="";
			}
						String m_luxury_date=m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE_YY"+d).equals("")){
			m_luxury_date="";
			}
						String m_rev_date=m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE_YY"+d).equals("")){
			m_rev_date="";
			}
						String m_dri_date=m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE_YY"+d).equals("")){
			m_dri_date="";
			}

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_NEW_CRBOOKS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19); END;");
			callstmt.setString(1,m_app_no);
			//callstmt.setString(2,m_ref_no); //	comment by nuwan de silva on 25-01-2008
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_REF_NO_"+d).trim());
			callstmt.setString(3,m_payment_no);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ENGIN_NO_"+d).trim());
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_"+d).trim());
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO_"+d).trim());
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CR_BOOK_NO_"+d));
			callstmt.setString(8,m_print_date);
			callstmt.setString(9,m_insurance_date);
			callstmt.setString(10,m_luxury_date);
			callstmt.setString(11,m_rev_date);
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE_"+d).trim());
			callstmt.setString(13,m_dri_date);	
			callstmt.setString(14,m_form);//"AF_CR_PRO_PAYMENT_REQUSITION_MAIN"
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(16,m_username);
			callstmt.setString(17,Integer.toString(d));
			callstmt.setString(18,m_status);
			callstmt.setInt(19,sel_stage);
			callstmt.execute();
			}
			// callstmt.close();
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			for (int b=0;b<m_asset_doc_count; b++) 
			{
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			/*if(b==0) {
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			String m_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARKS_"+b);
			if(m_folup_rem.equals("")){
			m_folup_rem="-";
			}
			callstmt.setString(1,m_payment_no);
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_status);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_form);
			callstmt.registerOutParameter(7,java.sql.Types.CHAR); //added by nuwan de silva 08-10-07
			callstmt.setString(8,m_folup_rem);
			callstmt.setString(9,m_app_no);
			callstmt.execute();
			m_follow_up_no=callstmt.getString(7);
			}*/
			
			String m_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARKS_"+b);
			String m_chk_fol_status=m_sn_methods.met_formdata(reqstr,"chk_fol_status_"+b);
			
			if(m_folup_rem.equals("")){
			m_folup_rem="-";
			}
			if(m_chk_fol_status.equals("Y")){
			//out.println("m_chk_fol_status"+m_chk_fol_status);
			callstmt.setString(1,m_payment_no);
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_status);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_form);
			callstmt.registerOutParameter(7,java.sql.Types.CHAR); //added by nuwan de silva 08-10-07
			callstmt.setString(8,m_folup_rem);
			callstmt.setString(9,m_app_no);
			callstmt.execute();
			m_follow_up_no=callstmt.getString(7);
			}

			// callstmt.close();
			
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//*** TO SAVE ASSET DOCUMENTS *************************************************************************************************
		
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_APP_DOC_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");

			int m_from_screen=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_FROM_SCREEN_"+b));
			int m_to_screen=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_TO_SCREEN_"+b));
			
			
			String m_asset_chk_status=m_sn_methods.met_formdata(reqstr,"chk_status_"+b);
			String m_asset_napp_chk_status=m_sn_methods.met_formdata(reqstr,"chk_napp_status_"+b);
			String m_asset_fol_chk_status=m_sn_methods.met_formdata(reqstr,"chk_fol_status_"+b);
			String m_fol_remarks=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARKS_"+b);
			String m_remarks=m_sn_methods.met_formdata(reqstr,"TXT_REMARKS_"+b);
			
			String m_doc=m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+b);

			if(m_asset_chk_status.equals("")){
			m_asset_chk_status="N";
			}
			if(m_asset_napp_chk_status.equals("")){
			m_asset_napp_chk_status="N";
			}

			if(m_fol_remarks.equals("")){
			m_fol_remarks="-";
			}

			if(m_remarks.equals("")){
			m_remarks="-";
			}
			if(m_asset_fol_chk_status.equals("")){
			m_asset_fol_chk_status="N";
			}

			if(m_asset_napp_chk_status.equals("Y")){
			m_app_status="A";
			}
			if(m_asset_chk_status.equals("Y")){
			m_app_status="Y";
			}
				
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_payment_no);
			//callstmt.setString(3,m_ref_no); //	comment by nuwan de silva on 25-01-2008
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_INVOICE_NO_"+b).trim());
			callstmt.setString(4,m_follow_up_no);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+b).trim());
			callstmt.setInt(6,sel_stage);
			callstmt.setString(7,m_asset_chk_status);
			callstmt.setString(8,m_remarks);
		  callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(b));
			callstmt.setString(12,m_asset_napp_chk_status);
			callstmt.setString(13,m_form);
		  callstmt.setString(14,"ASSET");
			callstmt.setString(15,m_asset_fol_chk_status);
			callstmt.setString(16,m_fol_remarks);
			
  		callstmt.setInt(17,m_from_screen);
			callstmt.setInt(18,m_to_screen);
			callstmt.setString(19,m_sus_ref);
			callstmt.setString(20,m_app_status);
			callstmt.setString(21,m_client_code);
			if(m_doc.equals("") || m_doc==null){
			break;

			}
		  callstmt.execute();	
			}
			// callstmt.close();
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//*** TO SAVE CLIENT DOCUMENTS *************************************************************************************************
			
			for (int a=0; a<m_client_doc_count; a++) 
			{
			/*if(a==0){
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			String m_client_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FOL_REMARKS_"+a);
			if(m_client_folup_rem.equals("")){
					m_client_folup_rem="-";
			}
			callstmt1.setString(1,m_payment_no);
			callstmt1.setInt(2,sel_stage);
			callstmt1.setString(3,m_status);
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(5,m_username);
			callstmt1.setString(6,m_form);
			callstmt1.registerOutParameter(7,java.sql.Types.CHAR); //added by nuwan de silva on 08-10-07
			callstmt1.setString(8,m_client_folup_rem);
			callstmt1.setString(9,m_app_no);
			callstmt1.execute();
			}*/
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			String m_client_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FOL_REMARKS_"+a);
		  String m_chk_fol_status=m_sn_methods.met_formdata(reqstr,"chk_fol_status_"+a);
			
			if(m_client_folup_rem.equals("")){
					m_client_folup_rem="-";
			}
			if(m_chk_fol_status.equals("Y")){
			//out.println("m_chk_fol_status"+m_chk_fol_status);
			callstmt1.setString(1,m_payment_no);
			callstmt1.setInt(2,sel_stage);
			callstmt1.setString(3,m_status);
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(5,m_username);
			callstmt1.setString(6,m_form);
			callstmt1.registerOutParameter(7,java.sql.Types.CHAR); //added by nuwan de silva on 08-10-07
			callstmt1.setString(8,m_client_folup_rem);
			callstmt1.setString(9,m_app_no);
			callstmt1.execute();
			m_follow_up_no1=callstmt1.getString(7); // added by nuwan de silva 
			}


      //callstmt.close();
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_APP_DOC_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
			
			int m_from_screen_client=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_FROM_SCREEN_CLIENT_"+a));
			int m_to_screen_client=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_TO_SCREEN_CLIENT_"+a));


			String m_client_chk_status=m_sn_methods.met_formdata(reqstr,"chk_client_status_"+a);
			String m_client_napp_chk_status=m_sn_methods.met_formdata(reqstr,"chk_client_napp_status_"+a);
			String m_client_fol_chk_status=m_sn_methods.met_formdata(reqstr,"chk_client_fol_status_"+a);
			String m_client_doc=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_DOC_CODE_"+a);

			String m_client_fol_remarks=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FOL_REMARKS_"+a);
			String m_client_remarks=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_REMARKS_"+a);
	
	
		  if(m_client_chk_status.equals("")){
			m_client_chk_status="N";
			}
			
			if(m_client_napp_chk_status.equals("")){
			m_client_napp_chk_status="N";
			}
			
			if(m_client_fol_remarks.equals("")){
			m_client_fol_remarks="-";
			}

			if(m_client_remarks.equals("")){
			m_client_remarks="-";
			}
			if(m_client_fol_chk_status.equals("")){
			m_client_fol_chk_status="N";
			}

			if(m_client_napp_chk_status.equals("Y")){
			m_client_app_status="A";
			}
			if(m_client_chk_status.equals("Y")){
			m_client_app_status="Y";
			}

			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_payment_no);
			callstmt.setString(3,"");
			callstmt.setString(4,m_follow_up_no1);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_DOC_CODE_"+a).trim());
			callstmt.setInt(6,sel_stage);
			callstmt.setString(7,m_client_chk_status);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_REMARKS_"+a));
		  callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
		  callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(a));
			callstmt.setString(12,m_client_napp_chk_status);
			callstmt.setString(13,m_form);
		  callstmt.setString(14,"CLIENT");
			callstmt.setString(15,m_client_fol_chk_status);
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FOL_REMARKS_"+a));
   		callstmt.setInt(17,m_from_screen_client);
			callstmt.setInt(18,m_to_screen_client);
			callstmt.setString(19,m_sus_ref);
			callstmt.setString(20,m_client_app_status);
			callstmt.setString(21,m_client_code);

			if(m_client_doc.equals("")|| m_client_doc==null ){
			break;
			}
			callstmt.execute();
			}
			 callstmt.close();
			//********************************************************************************************************

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
				//m_payment_no="";//added by nuwan de silva 08-10-07

				//////}
				conn.setAutoCommit(true);
				callstmt.close();

		   	out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Information saved successfully-'+ '"+m_payment_no+"')");
				//if(m_screen_type!=null){
				//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_loan_payment_details?sql=main_page&status_new=&status_edit=&screen_type=NEW&chksql=VERIFY&chksql2=R';");
				//}else{
				out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?sql=main_page&status_new=&status_edit=&screen_type=NEW&chksql=VERIFY&chksql2=R';");
				//}
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
			
			/*finally{
			
			try{
				}catch(Exception e){
				
				}
				//if(input     !=null){try{input.close();    }catch(Exception e){}}
				//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}*/
					finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
				//if(input     !=null){try{input.close();    }catch(Exception e){}}
				//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
	
			
			
	
		}
	}
			
	
	
	

