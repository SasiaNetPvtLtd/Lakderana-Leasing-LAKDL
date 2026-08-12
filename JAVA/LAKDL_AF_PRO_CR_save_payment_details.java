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

public class LAKDL_AF_PRO_CR_save_payment_details extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
		Statement stmt;
	public ResultSet rs;
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
			String m_save_procedure_name;
			String m_follup1="";
			String m_mpay_status="";
			String m_requ="";

			stmt=conn.createStatement();
			int m_doc,m_cr,m_oth;
			String m_st;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			conn.setAutoCommit(false);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
	//		String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			m_doc = Integer.parseInt(req.getParameter("number"));
			m_cr = Integer.parseInt(req.getParameter("number1"));
			m_oth = Integer.parseInt(req.getParameter("number2"));
			
			String m_form=req.getParameter("form_name");
			String m_ref_no=req.getParameter("ref");
			String m_sus_ref=req.getParameter("sus_ref");

			int sel_stage=0;
		
			String remarks_foll[]=new String[100];
			String doc_app[]=new String[100];
			String inv_app[]=new String[100];
			String chk_foll[]=new String[100];
			
			String status[]=new String[100];
			String uap_status[]=new String[100];
			String xxxx="-";
			String yyyy="-";
			String zzzz="";
			
			String aaaa="";
			String rem_checked="";
			String bbbb="";
			String cccc="";
			String m_chk="";
			String m_chk_napp="";
			String m_find="-";
			int h=0;

			
			


			for(int y=0;y<m_cr;y++){

				for(int g=0;g<m_doc;g++){
				xxxx=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REM"+y+"_"+g);
				zzzz=m_sn_methods.met_formdata(reqstr,"CHK_FOLL"+y+"_"+g);
				
				aaaa=m_sn_methods.met_formdata(reqstr,"chkstatus"+y+"_"+g);
				bbbb=m_sn_methods.met_formdata(reqstr,"CHK_NAPP"+y+"_"+g);
				rem_checked=m_sn_methods.met_formdata(reqstr,"TXT_REMARK_NEW"+y+"_"+g);

		
				
				if (zzzz.equals("")){
				zzzz="N";
				}
				
				if(aaaa.equals("")){
				aaaa="N";
				}
				
				if(bbbb.equals("")){
				bbbb="N";
				}
				
				remarks_foll[g]=xxxx;
				chk_foll[g]=zzzz;
				
				}
			}
		
				

			
				
			if(m_oth==0)
			{ 
			
			m_oth=1;
			}
			
			
			String stclose = req.getParameter("closest");
			String m_status = req.getParameter("actst1");//B
			String m_chksql1 = req.getParameter("actst2");//A
			String m_approval_1 = req.getParameter("approval1");//A
			String m_approval = req.getParameter("approval");//B	
			

			if (m_approval_1.equals("VERIFY")){
			sel_stage=01;
			}
			else if(m_approval_1.equals("R")){
			sel_stage=1;
			}
			else if(m_approval_1.equals("B")){
			sel_stage=2;
			}
			else if(m_approval_1.equals("A")){
			sel_stage=3;
			}

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");

			String m_pro=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
			
			for(int f=0;f<m_cr;f++){

					for(int t=0;t<m_doc;t++){

					yyyy=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REM"+f+"_"+t);
					
				if (yyyy.equals("")){
				continue;
				}
				
					remarks_foll[t]=yyyy;
					h=h+1;
			

					}
			}

			for(int s=0;s<h;s++){

					if(!remarks_foll[s].equals("")){
					remarks_foll[0]=remarks_foll[s];
					break;
					}
			}
			
					
			m_find=remarks_foll[0];
				
			if(m_find==null || m_find.equals("")){

			m_find="-";
			
			}

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_approval_1);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.setString(6,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.registerOutParameter(7,java.sql.Types.CHAR);
			callstmt.setString(8,m_find);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			
			if(m_approval_1.equals("A")){
			callstmt.setString(10,"APPRO2");
			}
			else {
			callstmt.setString(10,"");

			}
			callstmt.registerOutParameter(11,java.sql.Types.CHAR);
			callstmt.setString(12,"");

			if  (!m_pro.trim().equals("")) {

					callstmt.execute();
					
				m_follup1=callstmt.getString(7);
				m_requ=callstmt.getString(11);

			}


			
				String m_po_no=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
					
				double m_sett=0;
		
				rs = stmt.executeQuery ("SELECT SUM(INVOICE_SET_AMT) FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
				""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER F "+
				","+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET G "+
				"WHERE UPPER(A.PURCHASE_ORDER_NO)=UPPER('"+m_po_no+"') "+
				"AND E.APPLICATION_NO            =C.APPLICATION_NO "+
				"AND B.REF_NO                    =C.INVOICE_NO "+
				"AND F.APPLICATION_NO            =E.APPLICATION_NO "+
				"AND G.REQU_NO									 =A.REQU_NO "+
				"AND F.PURCHASE_ORDER_NO         =A.PURCHASE_ORDER_NO ");


				if(rs.next()){
				m_sett=rs.getDouble(1);
				}
			
								
				double m_set1=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT")));
					
				if(m_set1==0){
				m_set1=0;
				}

				double m_tot1=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL")));

			 	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_SETTLE_AMOUNT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");

				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(3,m_username);
				String m_settle_amt=m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_AMOUNT"));
				
				if(m_settle_amt==null){
				m_settle_amt="0";
				}
				
				
				callstmt.setString(4,m_settle_amt);
				
				callstmt.setString(5,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
				
				m_sett=m_sett+m_set1;

				if(m_tot1==m_sett){
				
				m_mpay_status="COM";//com for complete
				}
	  		else{
				m_mpay_status="BAL";//for balance
				}

				callstmt.setString(6,m_mpay_status);
				callstmt.setString(7,m_requ);
				callstmt.setString(8,Integer.toString(0));
				callstmt.setString(9,m_approval);
				callstmt.setString(10,m_ref_no);
				callstmt.setString(11,m_sus_ref);
				callstmt.setString(12,m_settle_amt);

				
				
				callstmt.execute();



//*** TO SAVE ASSET DOCUMENTS *************************************************************************************************
			for (int j=0; j<=m_doc; j++) 
			{
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19); END;");

			String m_app=m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j);
			
			
			String m_from_screen=m_sn_methods.met_formdata(reqstr,"hid_TXT_FROM_SCREEN"+j);
			String m_to_screen=m_sn_methods.met_formdata(reqstr,"hid_TXT_TO_SCREEN"+j);
		
			String m_remark="";
			doc_app[j]=m_app;
			inv_app[j]=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+j);

					
			if(m_chk.equals("")){
			m_chk="N";

			}
			if(m_chk_napp.equals("")){
			m_chk_napp="N";

			}
			
			if (m_remark.equals("")){
			m_remark="-";
			
			}
			
						
			if(m_chk_napp.equals("")){
			m_chk_napp="N";
			}	
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j));
			callstmt.setString(4,m_chk);
			callstmt.setString(5,m_remark);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,m_follup1);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+j));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(j));
			callstmt.setString(12,m_chk_napp);
			callstmt.setString(13,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
		  callstmt.setString(14,"ASSET");
			callstmt.setString(15,"AF");
			callstmt.setString(16,"");
			callstmt.setString(17,"");

			callstmt.setString(18,m_from_screen);
			callstmt.setString(19,m_to_screen);
			if  (m_app.trim().equals("")) {
			break;
			}
		  callstmt.execute();

			}
			
			//********************************************************************************************************

				for (int d=0; d<=m_cr; d++) 
			{
		

			String m_pro1=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");

			String m_inv=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d);
			
			String m_print_date=m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_DD"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_MM"+d)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_PRINT_DATE_YY"+d);
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_CRBOOKS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26); END;");
			
			String m_ins=m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE"+d);
			
			String m_supplier=m_sn_methods.met_formdata(reqstr,"TXT_NAME");
			String m_party=m_sn_methods.met_formdata(reqstr,"TXT_PARTY");
			

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_ENGIN_DOCS"+d));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CHASI_DOCS"+d));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO"+d));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(d));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE"+d));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE"+d));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE"+d));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE"+d));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE"+d));
			callstmt.setString(15,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO"));			
			callstmt.setString(18,m_follup1);
			callstmt.setString(19,m_approval_1);
			callstmt.setString(20,m_approval);
			callstmt.setInt(21,sel_stage);
			callstmt.setString(22,m_print_date);
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_CR_BOOK"+d));
			
			
			if(m_party.equals("1")){

			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_PAYER"));

			}
			else{
			callstmt.setString(24,m_supplier);

			}
			

			String m_date=m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY");
		
			
			if(m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD").equals("") || m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM").equals("") || m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY").equals("")){
			m_date="";
			}
		
			callstmt.setString(25,m_date);
			callstmt.setString(26,m_requ);
	
			
			if  (m_inv.trim().equals("")) {
						  break;
			}
			
			callstmt.execute();
			
			}
				
				
				
			//***TO SAVE CLIENT DOCUMENTS ***************************************************************************************************

				for (int a=0; a<=m_oth; a++) 
			{
	

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19); END;");

			String m_app_oth=m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a);
			String m_chk1=m_sn_methods.met_formdata(reqstr,"CHK_ST"+a);
			
			String m_chk_nth=m_sn_methods.met_formdata(reqstr,"CHK_NAPP_OTH"+a);
			String m_chk_folup_oth=m_sn_methods.met_formdata(reqstr,"CHK_FOLLUP_OTH"+a);
			String m_chk_folup_rem=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REMARK_OTH"+a);

			String m_rem=m_sn_methods.met_formdata(reqstr,"TXT_OTH_REMARK"+a);

			String m_from_oth_screen=m_sn_methods.met_formdata(reqstr,"hid_TXT_FROM_OTH_SCREEN"+a);
			String m_to_oth_screen=m_sn_methods.met_formdata(reqstr,"hid_TXT_TO_OTH_SCREEN"+a);
			String m_req1="N";
			
			if(m_chk_folup_rem.equals("")){
			m_chk_folup_rem="-";
			}
			if (m_rem.equals("")){
						 m_rem="-";
			
			}
			if(m_chk1.equals("")){
			m_chk1="N";

			}
			if(m_chk_nth.equals("")){
			m_chk_nth="N";

			}
			if(m_chk_folup_oth.equals("")){
			m_chk_folup_oth="N";
			}
			
			if(m_chk_nth.equals("Y")){
			m_req1="A";
			}
			if(m_chk1.equals("Y")){
			m_req1="Y";
			}

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a));
			callstmt.setString(4,m_req1);
			callstmt.setString(5,m_rem);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,m_follup1);
			callstmt.setString(8,"");
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(a));
			callstmt.setString(12,m_chk_nth);
			callstmt.setString(13,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.setString(14,"");
			callstmt.setString(15,"AF");
			callstmt.setString(16,m_chk_folup_oth);
			callstmt.setString(17,m_chk_folup_rem);
			callstmt.setString(18,m_from_oth_screen);
			callstmt.setString(19,m_to_oth_screen);

			if  (m_app_oth.trim().equals("")) {
								

						  break;
			}
			callstmt.execute();
		 } 
				

	
			//*** Update Applicant Document*****************
			for(int y=0;y<m_cr;y++){
			for(int g=0;g<m_doc;g++){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_UPDATE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");

			xxxx=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REM"+y+"_"+g);
			zzzz=m_sn_methods.met_formdata(reqstr,"CHK_FOLL"+y+"_"+g);
			aaaa=m_sn_methods.met_formdata(reqstr,"chkstatus"+y+"_"+g);
			bbbb=m_sn_methods.met_formdata(reqstr,"CHK_NAPP"+y+"_"+g);
			cccc=m_sn_methods.met_formdata(reqstr,"TXT_INV"+y+"_"+g);
			rem_checked=m_sn_methods.met_formdata(reqstr,"TXT_REMARK_NEW"+y+"_"+g);

			String m_req="N";
			
				if (zzzz.equals("")){
				zzzz="N";
				}
				if(aaaa.equals("")){
				aaaa="N";
				}
				
				if(bbbb.equals("")){
				bbbb="N";
				}
				
				if(bbbb.equals("Y")){
				m_req="A";
				}
				
				if(aaaa.equals("Y")){
				m_req="Y";
				}
						
					if(xxxx.equals("")){
					xxxx="-";
					}
					
					
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_REQUSITION_MAIN");
			callstmt.setString(3,doc_app[g]);
			callstmt.setString(4,xxxx);//follow up remarks
			callstmt.setString(5,m_follup1);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
		  callstmt.setString(8,"ASSET");
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(10,zzzz);//follw up checked
			callstmt.setString(11,cccc);//invoice no
			callstmt.setString(12,m_req);
			callstmt.setString(13,bbbb);//not app checked
			callstmt.setString(14,rem_checked);//remark
			callstmt.execute();


			}
			}
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6); END;");
			callstmt.registerOutParameter(2,java.sql.Types.CHAR);	
			  
		
			  for (int j = 0; j < m_maxentries; j++) {
				
			 callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				
			  callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"+(Integer.toString(j))));
		
				
				callstmt.setString(3,m_screen_name);
			  callstmt.setString(4,m_username);
			  callstmt.setString(5,m_scr_name);
			  callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j))));
				
				String m_condition=(String)m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
				
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
			out.println("alert("+m_msg+");");
			
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?chksql="+m_approval+"&cls=1&chksql2="+m_approval_1+"&st_c=APPLICATION_NO&oby=ASC';");
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
		
		try{
			}catch(Exception e){
			
			}
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
		

	}
}
		




