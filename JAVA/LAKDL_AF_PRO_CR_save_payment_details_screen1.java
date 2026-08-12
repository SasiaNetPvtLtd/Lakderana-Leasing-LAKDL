//--
//SCREEN NAME	:SAVE PAYMENT DETAILS
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_payment_details_screen1 extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
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
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			int m_doc,m_cr,m_oth;
			String m_follup="";
			String m_st;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			conn.setAutoCommit(false);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	

			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			m_doc = Integer.parseInt(req.getParameter("number"));
			m_cr = Integer.parseInt(req.getParameter("number1"));
			m_oth = Integer.parseInt(req.getParameter("number2"));
			String m_requ_no=req.getParameter("REQ_NO");	
			String m_fschema_name=m_sn_methods.client_name.trim();

			String remarks_foll[]=new String[100];
			String doc_app[]=new String[100];
			String inv_app[]=new String[100];
			String chk_foll[]=new String[100];
			
			String status[]=new String[100];
			String uap_status[]=new String[100];
			String xxxx="";
			String yyyy="";
			String zzzz="";
			
			String aaaa="";
			String rem_checked="";
			String bbbb="";
			String cccc="";
			String m_chk="";
			String m_chk_napp="";
			int sel_stage=0;
						String m_type="";
			
			m_type=req.getParameter("type");
			
			if(m_type==null){
			m_type="";
			}
			

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
			
			String m_approval_1 = req.getParameter("approval1");//B
			String m_approval = req.getParameter("approval");//VERIFY	
				
			String stclose = req.getParameter("closest");
			
			String m_status = req.getParameter("actst1");//verify
			String m_chksql1 = req.getParameter("actst2");//B

			if (m_approval_1.equals("VERIFY")){
			sel_stage=0;
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



			//********************************************************************************************************
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
	
			String m_pro=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
			
			String m_find="-";
			int h=0;
			
			for(int f=0;f<=m_cr;f++){
					for(int t=0;t<=m_doc;t++){
					yyyy=m_sn_methods.met_formdata(reqstr,"TXT_FOL_REM"+f+"_"+t);
					
				if (yyyy.equals("")){
				continue;
				}
				
					remarks_foll[t]=yyyy;
					h=h+1;
			

					}
			}
			
			for(int s=0;s<=h;s++){
					if(!remarks_foll[s].equals("")){
					remarks_foll[0]=remarks_foll[s];
					break;
					}
			}
			
			m_find=remarks_foll[0];
			if(m_find.equals("")){
			m_find="-";
			
			}
			
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_approval_1);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.setString(6,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.registerOutParameter(7,java.sql.Types.CHAR);
			callstmt.setString(8,m_find);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			if(m_approval_1.equals("B")){
			callstmt.setString(10,"APPRO1");
			}
			else {
			callstmt.setString(10,"");

			}
			
			callstmt.setString(11,"");
			callstmt.setString(12,"");

			if  (!m_pro.trim().equals("")) {
					callstmt.execute();
					m_follup=callstmt.getString(7);

			}
			

		
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
			
			if (m_remark.equals("")){
			m_remark="-";
			}
		
			
			if(m_chk_napp.equals("")){
			m_chk_napp="N";
			}	
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j));
			callstmt.setString(4,m_chk);
			callstmt.setString(5,m_remark);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,m_follup);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+j));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(j));
			callstmt.setString(12,m_chk_napp);
			callstmt.setString(13,"AF_CR_PRO_PAYMENT_MAIN");
		  callstmt.setString(14,"ASSET");
			callstmt.setString(15,"AF");
			callstmt.setString(16,"");
			callstmt.setString(17,"");
			callstmt.setString(18,m_from_screen);
			callstmt.setString(19,m_to_screen);
			
			if  (!m_app.trim().equals("") && !m_app.equals("") ) {
			callstmt.execute();
			}
			
			}
			
			//********************************************************************************************************

			for (int d=0; d<=m_cr; d++) 
			{
		
			String m_pro1=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");

			String m_inv=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d);
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_CRBOOKS_1(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(4,m_username);
			callstmt.setString(5,Integer.toString(d));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(7,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.setString(8,m_follup);
				callstmt.setString(9,"RE-APP");
			callstmt.setString(10,m_requ_no);
			
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

			if(m_chk_folup_oth.equals("")){
			m_chk_folup_oth="N";
			
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

			if(m_chk_nth.equals("Y")){
			m_req1="A";
			}
			
			if(m_chk1.equals("Y")){
			m_req1="Y";
			}


			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a));
			callstmt.setString(4,m_req1);
			callstmt.setString(5,m_rem);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,m_follup);
			callstmt.setString(8,"");
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(a));
			callstmt.setString(12,m_chk_nth);
			callstmt.setString(13,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.setString(14,"CLIENT");
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

						
						
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CR_PRO_PAYMENT_MAIN");
			callstmt.setString(3,doc_app[g]);
			callstmt.setString(4,xxxx);//follow up remarks
			callstmt.setString(5,m_follup);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
		  callstmt.setString(8,"ASSET");
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(10,zzzz);//follw up checked
			callstmt.setString(11,cccc);//invoice no
			//callstmt.setString(12,aaaa);//status checked207-01-18
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
			
      
				
			//**********************

			conn.setAutoCommit(true);
			callstmt.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			if(m_type.equals("")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?chksql=R&cls=1&chksql2=B&st_c=APPLICATION_NO&oby=ASC';");
			}
			if(m_type.equals("H")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Autherization_higher_Pay_1?chksql=R&cls=1&chksql2=B&st_c=APPLICATION_NO&oby=ASC';");
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
		



		
