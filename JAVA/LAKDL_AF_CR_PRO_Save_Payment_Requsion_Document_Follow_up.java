//--
//SCREEN NAME:SAVE PAYMENT FOLLOW UP DETAILS
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Payment_Requsion_Document_Follow_up extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt_pay;
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
			conn =m_sn_methods.met_user_validate(req);
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			
			m_html_client_url=m_sn_methods.html_client_url;
		  m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
		  m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			//out.println(reqstr);
			conn.setAutoCommit(false);
		  				      
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			
			//out.println("m_screen_name"+m_screen_name);
			//String m_ref_no=req.getParameter("ref");
			//String m_sus_ref=req.getParameter("sus_ref");
			String m_ref_no ="";
			String m_sus_ref ="";
			int sel_stage=0;
			String m_app_no         = req.getParameter("app_no").trim();
			String m_form           = req.getParameter("form_name").trim();
			String m_client_code    = req.getParameter("client_code").trim();
			String m_status         = req.getParameter("status").trim();
	    			
			int m_invoice_count				= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_no")); 
			//out.println("m_invoice_count"+m_invoice_count);
			int m_asset_doc_count			= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_asset_doc_no")); 
			//out.println("m_asset_doc_count"+m_asset_doc_count);
			int m_client_doc_count		=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_client_doc_no")); 
			//out.println("m_client_doc_count"+m_client_doc_count);
			
			
			String m_payment_no="";
			String m_follow_up_no="";
			String m_follow_up_no1="";
			String m_app_status="N";
			String m_client_app_status="N";

			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('"+m_form+"') ");
			if(rs.next()){
			sel_stage=rs.getInt(1);
			}
			
			//+++++++++++++++++++++++++++++++++++++++++++++++ SAVING ENGINE/CHASSIS /CRBOOK DETAILS +++++++++++++++++++++++++++++++++++++++++++++++//
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_NEW_CRBOOKS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19); END;");
			callstmt_pay=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PAY_SET_BR_DWN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
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

			
			callstmt.setString(1,m_app_no);
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
									
			
			//String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));			
			callstmt_pay.setString(1,m_payment_no);
			callstmt_pay.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(d))));
			callstmt_pay.setString(3,"");
			callstmt_pay.setString(4,"");
			callstmt_pay.setString(5,"");
			//callstmt_pay.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(d))));
			callstmt_pay.setString(6,"");
			callstmt_pay.setString(7,"EDIT");
			callstmt_pay.setString(8,m_username);
			callstmt_pay.setString(9,m_scr_name);
			callstmt_pay.setString(10,"V");
			//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INVOICE_NO"));
			callstmt_pay.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_TAX_INVOICE_NO_"+(Integer.toString(d))));
			
			String m_tax_inv_date=m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_YY"+d);
			if(m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_DD"+d).equals("")  || m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_MM"+d).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_TAX_DATE_YY"+d).equals("")){
			m_tax_inv_date="";
			}
			callstmt_pay.setString(12,m_tax_inv_date);
			callstmt_pay.setString(13,"0");
		  callstmt_pay.execute();

			
			
			}
			
			
		 //+++++++++++++++++++++++++++++++++++++++++++++++ SAVING ASSET DOCUMENT DETAILS +++++++++++++++++++++++++++++++++++++++++++++++//
		  callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_APP_DOC_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
			for (int b=0;b<m_asset_doc_count; b++) 
			{
					
			String m_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARKS_"+b);
			String m_chk_fol_status=m_sn_methods.met_formdata(reqstr,"chk_fol_status_"+b);
			
			if(m_folup_rem.equals("")){
			m_folup_rem="-";
			}
			if(m_chk_fol_status.equals("Y")){
			//out.println("m_chk_fol_status"+m_chk_fol_status);
			//callstmt1.setString(1,m_payment_no);
			//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_INVOICE_NO_"+b).trim());
			//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+b).trim());
			callstmt1.setString(1,m_app_no);
			callstmt1.setInt(2,sel_stage);
			callstmt1.setString(3,m_status);
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(5,m_username);
			callstmt1.setString(6,m_form);
			callstmt1.registerOutParameter(7,java.sql.Types.CHAR); //added by nuwan de silva 08-10-07
			callstmt1.setString(8,m_folup_rem);
			callstmt1.setString(9,m_app_no);
			callstmt1.execute();
			m_follow_up_no=callstmt1.getString(7);
			//out.println("m_follow_up_no"+m_follow_up_no);
			
			}
					

			int m_from_screen=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_FROM_SCREEN_"+b));
			int m_to_screen=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_TXT_TO_SCREEN_"+b));
			
			String m_asset_chk_status=m_sn_methods.met_formdata(reqstr,"chk_status_"+b);
			String m_asset_napp_chk_status=m_sn_methods.met_formdata(reqstr,"chk_napp_status_"+b);
			String m_asset_fol_chk_status=m_sn_methods.met_formdata(reqstr,"chk_fol_status_"+b);
			String m_fol_remarks=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARKS_"+b);
			String m_remarks=m_sn_methods.met_formdata(reqstr,"TXT_REMARKS_"+b);
			
			String m_doc=m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+b);
			//out.println("m_doc"+m_doc);

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
			//callstmt.setString(2,m_payment_no);
			//callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_INVOICE_NO_"+b).trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+b).trim());
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
			//callstmt.setString(19,m_sus_ref);
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+b).trim());
			callstmt.setString(20,m_app_status);
			callstmt.setString(21,m_client_code);
			
			if(m_doc.equals("") || m_doc==null){
			break;
			//continue;

			}
		  callstmt.execute();	
			}
			
			callstmt.close();
			callstmt1.close();
			
			//+++++++++++++++++++++++++++++++++++++++++++++++ SAVING CLIENT DOCUMENT DETAILS +++++++++++++++++++++++++++++++++++++++++++++++//
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_APP_DOC_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_FOLL_PAY_SET(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			for (int a=0; a<m_client_doc_count; a++) 
			{
					
			String m_client_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FOL_REMARKS_"+a);
		  String m_chk_fol_status=m_sn_methods.met_formdata(reqstr,"chk_client_fol_status_"+a);
			
			if(m_client_folup_rem.equals("")){
					m_client_folup_rem="-";
			}
			//out.println("m_chk_fol_status"+m_chk_fol_status);

			if(m_chk_fol_status.equals("Y")){
			//callstmt1.setString(1,m_payment_no);
			//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+a).trim());
			callstmt1.setString(1,m_app_no);
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
			//out.println("m_follow_up_no1"+m_follow_up_no1);

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
			//callstmt.setString(2,m_payment_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+a).trim());
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
			//callstmt.setString(19,m_sus_ref);
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+a).trim());
			callstmt.setString(20,m_client_app_status);
			callstmt.setString(21,m_client_code);

			if(m_client_doc.equals("")|| m_client_doc==null ){
			break;
			//continue;
			}
			callstmt.execute();
			}
				
				callstmt.close();
				callstmt1.close();			
			 
				//conn.setAutoCommit(true);


				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Information saved successfully')");
				//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?sql=main_page&status_new=&status_edit=&screen_type=NEW&chksql=VERIFY&chksql2=R';");
				//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?APP_NO="+m_application_no+"&CURR_CODE="+m_curr_code+"&TOT_SET="+m_total_settle+"&INIT_BAL="+m_initial_bal+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';");   //modified by nwuan de silva on 08-10-07
				out.println("window.close();");
				//out.println("window.opener.get_conditions()"); //added by nuwan de silva 17-10-07
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
				//out.println("window.history.back();"); 
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
				}
				finally{
				try{conn.setAutoCommit(true);}catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
				}
				
				}
				}
			
	
	
	

