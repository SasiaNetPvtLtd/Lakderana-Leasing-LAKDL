//--
//SCREEN NAME:SAVE PURCHASE ORDER PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Purchase_Order extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2,callstmt3,callstmt4,callstmt5,callstmt6;
	String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	int m_chk_status=0;
	int sum_chk_status_doc=0;
	int sum_chk_status_entity=0;
	int k=0;
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				
				//PrintStream out = new PrintStream(res.getOutputStream());
				//  out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_client_name = m_sn_methods.client_name.trim();
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url=m_sn_methods.html_client_url;
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				
				//String m_html_client_url;
				//String m_class_url;
				String m_class_name_save;
				String m_save_procedure_name;
				String m_screen_name="";
				String m_app_no="";
				String m_vendor_code="";
				String m_pur_ord_no1="";
				String m_status="";
				String m_branch_code="";
				String m_followup_num="";
				String m_invoice_no="";
				String m_act_date="";
				double m_charge_to_be=0;
				
				conn.setAutoCommit(false);
				
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				m_msg = "'Information saved successfully'";
				m_url = m_class_url;
				
				
				stmt=conn.createStatement();
				
				
				int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
				int m_max_doc        = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec_doc"));
				int m_max_doc_entity = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec_doc_entity"));
				int m_maxentries_con = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec_con"));
				
				//m_followup_num=(String)m_sn_methods.met_formdata(reqstr,"hid_followup_num");
				
				//out.println("follow up"+m_scr_name);
				
				m_app_no     =(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
				m_vendor_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE");
				m_branch_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE");
				m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			
				//added and commented by sh on 21-08-2009
				if(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_CHATGES")==null){
					m_charge_to_be = 0;
				}else if(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_CHATGES").equals("")){
					m_charge_to_be = 0;
				}else{
					m_charge_to_be = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_CHATGES")));//Added By Sandun on 15-06-2009
				}
				//m_charge_to_be = Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_CHATGES")));//Added By Sandun on 15-06-2009
				//end of changed 
				
				//	  out.println("records doc "+m_max_doc);
				//		out.println("records entity "+m_max_doc_entity);
				//	out.println("m_charge_to_be--:"+m_charge_to_be);
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_PUR_ORD(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;"); // added by udara 11-03-2019
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PUR_ORD_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;"); // added by udara 11-03-2019
				
				for (int j = 0; j < m_maxentries; j++) {
					
					
					
					if(j==0){
						
						//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_PUR_ORD(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;"); // commented by udara 11-03-2019
						
						String m_pur_ord_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
						
						
						if(m_pur_ord_no.equals("")){
							callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
						}
						else
						{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO")).trim());
						}
						
						callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
						callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"));
						
						callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_SUM_NET")));
						callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_SUM_VAT")));
						callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt.setString(7,m_username);
						callstmt.registerOutParameter(8,java.sql.Types.CHAR);	
						callstmt.setString(8,"");
						
				
						callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_TXT_CURR_CODE"));
						callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_EXCHANGE_RATE")));
						callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
						
				
						//**************************************************************************
						//****(2007-02-08)**********************************************************
						//****(delanjali)***********************************************************
						callstmt.setString(12,"");							
						callstmt.setString(13,m_scr_name);							
						//callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));							
						//**************************************************************************
						
						
						
						callstmt.execute();
						
						//get the status to check the letter print or not.
						m_status =callstmt.getString(8);
						
						if(m_screen_name.equals("NEW")){
							m_pur_ord_no1 =callstmt.getString(1);
							m_msg = "'"+m_pur_ord_no1+ "-" +"Purchase Order saved successfully.'";
							
						}
						
						else
						{
							m_pur_ord_no1=(String)m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
						}
						
					}
					out.println("9");
					
					String m_num=Integer.toString(j); 
					
					m_invoice_no =(String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j)));
					
					
					//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PUR_ORD_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;"); // commented by udara 11-03-2019
					
					callstmt2.setString(1,m_pur_ord_no1.trim());
					
					callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
					
					
					/*rs = stmt.executeQuery(" SELECT ASSET_ID,ENGINE_NO,CHASSIS_NO FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO='"+m_invoice_no+"' ");
		
		
					boolean more = rs.next();
					if(more){						
					
					callstmt.setString(3,rs.getString(1));
					callstmt.setString(4,rs.getString(2));
					callstmt.setString(5,rs.getString(3));
					}
					*/
					
					
					callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_ASSET_ID"+(Integer.toString(j))));
					callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"+(Integer.toString(j))));
					callstmt2.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CHASSISS_NO"+(Integer.toString(j))));
					
					callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt2.setString(7,m_username);
					callstmt2.setString(8,m_num);
					callstmt2.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CAPITAL"+(Integer.toString(j))));
					callstmt2.setString(10,m_scr_name);
					
					
					
					if  (m_invoice_no.trim().equals("")) {
						break;
					} 
					
					callstmt2.execute();
					
				}
				
				
				
				//Saving The Status Of The Application Number......................................
				
				
				callstmt3=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PUR_ORD_STATUS(:1,:2,:3,:4,:5); END;");
				
				
				
				callstmt3.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				
				
				
				String m_act_dd =m_sn_methods.met_formdata(reqstr,"TXT_ACT_DATE_DD");
				String m_act_mm =m_sn_methods.met_formdata(reqstr,"TXT_ACT_DATE_MM");
				String m_act_yy =m_sn_methods.met_formdata(reqstr,"TXT_ACT_DATE_YY");
				
				
				//String m_act_date="";
				
				if(m_act_dd.equals("") && m_act_mm.equals("") &&  m_act_yy.equals("") )
				{
					m_act_date=m_act_dd+m_act_mm+m_act_yy; 
					
				}
				else
				{
					m_act_date=m_act_dd+"-"+m_act_mm+"-"+m_act_yy;
				}
				
				callstmt3.setString(2,m_act_date);
				
				
				callstmt3.setString(3,m_username);
				callstmt3.setString(4,m_scr_name);
				callstmt3.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				
				
				callstmt3.execute();
				
				
				//------------------------------------------------------------------------------------------
				
				
				
				
				
				
				//Saving Documents.....................................

				callstmt4=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // added by udara 11-03-2019
				
				
				for (int j = 0; j < m_max_doc; j++) {
					
					String m_num=Integer.toString(j);
					
					//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // commented by udara 11-03-2019
					
					callstmt4.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
					
					
					m_invoice_no =(String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j)));
					
					
					callstmt4.setString(2,m_scr_name);
					
					
					String m_code =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+(Integer.toString(j)));
					
					if(m_code.equals("")){
						break;
					}
					
					callstmt4.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+(Integer.toString(j))));
					
					
					String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));
					
					
					
					if(m_chk_required.equals("on")){
						callstmt4.setString(4,"Y");
					}
					else //if(m_chk_required.equals("on"))
					{
						callstmt4.setString(4,"N");
					}
					
					
					
					
					callstmt4.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+(Integer.toString(j))));
					
					
					callstmt4.setString(6,m_pur_ord_no1);
					
					//INSTEAD OF REFERENCE NUMBER
					//callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
					callstmt4.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt4.setString(8,m_username);
					callstmt4.setString(9,m_num);
					callstmt4.setString(10,m_sn_methods.met_formdata(reqstr,"hid_TXT_INV_NO"+(Integer.toString(j))));
					
					
					
					
					
					
					rs= stmt.executeQuery ("SELECT  "+
						
						"	    FOLLOW_UP_NO "+
						"	FROM "+
						"			"+m_schema_name+".AF_CO_PRO_FOLLOW_UP		 "+
						"  WHERE ID_NO=UPPER('"+m_pur_ord_no1+"') ");
					
					
					
					boolean more = rs.next();
					if(more){						
						
						m_followup_num=rs.getString(1);
						
					}
					
					
					
					if(m_followup_num.equals(""))
					{
						
						callstmt4.registerOutParameter(11,java.sql.Types.CHAR);	
					}
					else
					{
						callstmt4.setString(11,m_followup_num);
						
					}
					
					
					
					
					
					callstmt4.setString(12,m_scr_name);
					
					String m_chk_not_applicable =(String)m_sn_methods.met_formdata(reqstr,"CHK_NOT_APPLICABLE"+(Integer.toString(j)));
					
					if(m_chk_not_applicable.equals("on")){
						callstmt4.setString(13,"Y");
					}
					else //if(m_chk_required.equals("on"))
					{
						callstmt4.setString(13,"N");
					}
					
					callstmt4.setString(14,"ASSET");
					
					
					String m_chk_follow_up =(String)m_sn_methods.met_formdata(reqstr,"CHK_FOLLOW_UP_DOC"+(Integer.toString(j)));
					
					if(m_chk_follow_up.equals("on")){
						callstmt4.setString(15,"Y");
					}
					else //if(m_chk_required.equals("on"))
					{
						callstmt4.setString(15,"N");
					}
					
					callstmt4.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_REMARK_DOC"+(Integer.toString(j))));
					
					
					callstmt4.execute();
					
					
					
					
				}
				
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				callstmt5=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // added by udara 11-03-2019
				
				for (int j = 0; j < m_max_doc_entity; j++) {
					
					
					
					//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16); END;"); // commented by udara 11-03-2019
					
					String m_num=Integer.toString(j);
					callstmt5.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
					
					String m_code_entity =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE_ENTITY"+(Integer.toString(j)));
					
					//		out.println("m_code"+m_code_entity);
					if(m_code_entity.equals("")){;
						break;
					};
					
					
					callstmt5.setString(2,m_scr_name);
					
					
					
					callstmt5.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE_ENTITY"+(Integer.toString(j))));
					
					String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED_ENTITY"+(Integer.toString(j)));
					
					if(m_chk_required.equals("on")){
						callstmt5.setString(4,"Y");
					}
					else// if(m_chk_required.equals("on"))
					{
						callstmt5.setString(4,"N");
					}
					
					callstmt5.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_ENTITY"+(Integer.toString(j))));
					
					callstmt5.setString(6,m_pur_ord_no1);
					
					//INSTEAD OF REFERENCE NUMBER
					//callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
					callstmt5.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt5.setString(8,m_username);
					callstmt5.setString(9,m_num);
					//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
					callstmt5.setString(10,"");
					
					rs= stmt.executeQuery ("SELECT  "+
						
						"	    FOLLOW_UP_NO "+
						"	FROM "+
						"			"+m_schema_name+".AF_CO_PRO_FOLLOW_UP		 "+
						"  WHERE ID_NO=UPPER('"+m_pur_ord_no1+"') ");
					
					
					
					boolean more = rs.next();
					if(more){						
						
						m_followup_num=rs.getString(1);
						
					}
					
					
					
					if(m_followup_num.equals(""))
					{
						
						callstmt5.registerOutParameter(11,java.sql.Types.CHAR);	
					}
					else
					{
						callstmt5.setString(11,m_followup_num);
						
					}
					
					
					
					
					
					callstmt5.setString(12,m_scr_name);
					
					String m_chk_not_applicable_entity =(String)m_sn_methods.met_formdata(reqstr,"CHK_NOT_APPLICABLE_ENTITY"+(Integer.toString(j)));
					if(m_chk_not_applicable_entity.equals("on")){
						callstmt5.setString(13,"Y");
					}
					else //if(m_chk_required.equals("on"))
					{
						callstmt5.setString(13,"N");
					}
					
					callstmt5.setString(14,"CLIENT");
					
					
					
					String m_chk_follow_up =(String)m_sn_methods.met_formdata(reqstr,"CHK_FOLLOW_UP_ENTITY"+(Integer.toString(j)));
					
					if(m_chk_follow_up.equals("on")){
						callstmt5.setString(15,"Y");
					}
					else //if(m_chk_required.equals("on"))
					{
						callstmt5.setString(15,"N");
					}
					
					callstmt5.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_REMARK_ENTITY"+(Integer.toString(j))));
					
					
					callstmt5.execute();
					
					
					
					
					
					
				}
				
				
				callstmt6=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
				callstmt6.registerOutParameter(2,java.sql.Types.CHAR);	
				
				
				for (int j = 0; j < m_maxentries_con; j++) {
					
					callstmt6.setString(1,m_app_no);
					
					callstmt6.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"+(Integer.toString(j))));
					
					
					callstmt6.setString(3,m_screen_name);
					callstmt6.setString(4,m_username);
					callstmt6.setString(5,m_scr_name);
					callstmt6.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j))));
					callstmt6.setString(7,"COLLE_DOC");//MODIFIED NUWAN DE SILVA
					callstmt6.setString(8,"AF");
					callstmt6.setString(9,"PENDING");
					callstmt6.setString(10,"CREDIT");
					
					String m_condition=(String)m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
					
					if(m_condition.equals("")){
						continue;
					}
					
					
					callstmt6.execute();
				}
				
				
				
				
				
				
				
				
				String m_next_dd =m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_DD");
				String m_next_mm =m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_MM");
				String m_next_yy =m_sn_methods.met_formdata(reqstr,"TXT_NEXT_DATE_YY");
				
				
				String m_next_date="";
				
				if(m_next_dd.equals("") && m_next_mm.equals("") &&  m_next_yy.equals("") )
				{
					m_next_date=m_next_dd+m_next_mm+m_next_yy; 
				}
				else
				{
					m_next_date=m_next_dd+"-"+m_next_mm+"-"+m_next_yy;
				}
				
				
				// commented by udara 12-08-2016
				/*
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_INST_RENTAL_DA(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
				
				
				
				for (int j = 0; j < m_maxentries; j++) {
					
					
					callstmt.setString(1,m_app_no);
					m_invoice_no =(String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j)));
					
					rs = stmt.executeQuery(" SELECT A.PRICING_NO,B.PAYMENT_INTERVAL "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
						" WHERE A.INVOICE_NO=B.PRO_INVOICE_NO AND A.INVOICE_NO='"+m_invoice_no+"' ");
					String  m_pricing_no="";
					String m_duration="";
					
					
					boolean more = rs.next();
					if(more){						
						
						m_pricing_no=rs.getString(1);
						m_duration=rs.getString(2);
					}
					
					//out.println("date"+m_next_date);
					//out.println("invoice"+m_pricing_no);
					//out.println("duration"+m_duration);
					
					callstmt.setString(2,m_invoice_no);
					callstmt.setString(3,m_pricing_no);
					callstmt.setString(4,m_next_date);
					callstmt.setString(5,m_screen_name);
					callstmt.setString(6,m_username);
					callstmt.setString(7,m_scr_name);
					callstmt.setString(8,m_duration);
					callstmt.setString(9,m_act_date);//ADDED BY NUWAN DE SILVA
					
					
					if(m_next_date.equals("")){
						break;
					}
					
					callstmt.execute();
				}	
				*/
				
				callstmt.close();
				callstmt2.close();
				callstmt3.close();
				callstmt4.close();
				callstmt5.close();
				callstmt6.close();
				
				// conn.commit(); //added by nuwan de silva on 03-09-07---------------------- // commented by udara 13-02-2018
				

				//[Added Udara 13-02-2018 for check when purchase order delete level purchase order det table record not deleted siuation ]
				if(m_screen_name.equals("DEL")){
					callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_CHK_PURORD_AFTER_DEL(:1); END;");	
					callstmt1.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO")).trim());	
					callstmt1.execute();
				}
				//callstmt1.close();
				conn.commit(); 
				//[End Udara 13-02-2018]
				
				
				////////////////////////////////////////////////////////////////////////////////////////
				
				//  conn.setAutoCommit(true);
				
				//Added by Chandana on 14/06/2007
				String m_client_code="";
				String m_trn_type   ="";
				
				rs = stmt.executeQuery(" SELECT APPLICATION_NO,CLIENT_CODE,TRANSACTION_TYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO='"+m_app_no+"' ");
				
				boolean	more=rs.next();						
				
				if(more){
					m_client_code = rs.getString(2);
					m_trn_type	   = rs.getString(3);
				}												
				//End Chandana on 14/06/2007	
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				out.println("m_scr_name='"+m_screen_name+"'");
				out.println("m_status='"+m_status+"'");
				
				out.println("if(m_scr_name!='DEL' && m_status=='VERIFY'){");
				out.println("window.close();");
				
				if(m_charge_to_be == 0 ){//Added By Sandun on 15-06-2009
					if(m_trn_type.equals("HIREPURCH")){
						//	out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_application_no+"&document_code=HP_DELOD&print=TRUE&client_code="+m_client_code+"';"); 
						out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&pur_ord_no="+m_pur_ord_no1+"&application_no="+m_app_no+"&document_code=HP_DELOD&print=TRUE&client_code="+m_client_code+"','displayWindow2','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0');	");				
					}else{
						out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no="+m_pur_ord_no1+"&app_no="+m_app_no+"&status=ORIGINAL&print=TRUE&vendor_code="+m_vendor_code+"&branch_code="+m_branch_code+"','displayWindow2','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0');	");				
					}
				}
				
				//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order';");
				out.println("window.opener.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';");
				
				out.println("}else");
				out.println("if(m_scr_name!='DEL' && (m_status=='ENT' || m_status=='ENT_N' || m_status=='ENT_D') ){"); //modofied by nuwan de silva on 12-11-07
				out.println("window.close();");
				//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order';}");
				out.println("window.opener.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';}");
				
				out.println("else");
				out.println("if(m_scr_name=='DEL'){");
				out.println("window.close();");
				out.println("window.opener.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=delete_page';");
				
				
				out.println("}");
				
				//out.println("window.opener.load_data();");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				//	conn.setAutoCommit(true);
				//	conn.close();
				out.flush();
				out.close();
			}
			
			
			/* catch (Exception E) {
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
		
				}catch(Exception e){}
		
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}*/
			catch (Exception E) { //added by nuwan de silva on 03-09-07
				try{conn.rollback();}catch(Exception e){}
				out.println("ERROR:"+E.toString());
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Error when Saving');");
				//out.println("window.history.back();"); // commented by udara 02-10-2015
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
				
			}
			finally{ //added by nuwan de silva on 03-09-07
				try{conn.setAutoCommit(true);}catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
			
			
			
		}
	}
	
	
}
