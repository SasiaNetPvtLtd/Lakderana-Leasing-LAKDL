


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Confirmation_Report_Detail_Snap_Save extends HttpServlet {
	
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
			
			
			//String m_finance_no=req.getParameter("FIN_NO").trim();
			String m_view_status = req.getParameter("view_status").trim();
			String m_finance_no="";
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			String m_noise=(String)m_sn_methods.met_formdata(reqstr,"Noises_ok"); 
			
			String m_snap_position=(String)m_sn_methods.met_formdata(reqstr,"hid_snap_position");
		
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

				
				out.println("Test 1");
				
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CONFIRMATION_REPORT("+
																								
																								":1,:2,:3,:4,:5,:6,:7,:8,:9,:10,"+
																								":11,:12,:13,:14,:15,:16,:17,:18,:19,:20,"+
																								":21,:22,:23,:24,:25,:26,:27,:28,:29,:30,"+
																								":31,:32,:33,:34,:35,:36,:37,:38,:39,:40,"+
																								":41,:42,:43,:44,:45,:46,:47,:48,:49,:50,"+
																								":51,:52,:53,:54,:55,:56,:57,:58,:59,:60,"+
																								":61,:62,:63,:64,:65,:66,:67,:68,:69,:70,"+
																								":71,:72,:73,:74,:75,:76,:77,:78,:79,:80,"+
																								":81,:82,:83,:84,:85,:86,:87,:88,:89,:90, "+
																								//":91,:92 "+ // commented by udara 09-06-2017
																								":91,:92,:93,:94, "+ // added by udara 09-06-2017
																								":95,:96,:97,:98,:99,:100,:101 "+ // added by udara 11-07-2017
																								"); END;");
					
				String  text_dd=m_sn_methods.met_formdata(reqstr,"VAL_DAY");
				String	text_mm=m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
				String	text_yy=m_sn_methods.met_formdata(reqstr,"VAL_YEAR");

				String Date_con=text_dd+"-"+text_mm+"-"+text_yy;

				if(Date_con.equals("--")){
					Date_con="";
				}
				
				m_finance_no = m_sn_methods.met_formdata(reqstr,"hid_finance_no");
				
			    callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
		        callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_EXISTING_CUSTOMER"));  // TXT_EXSIT_CUST
				callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_BRANCH"));
				callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE"));
				callstmt1.setString(5,Date_con);
				callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_NAME_1_HIRER_CODE"));
				callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_NAME_1_HIRER"));
				//callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_NO_HIRE")); // commented by udara 23-03-2015
				callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"hid_finance_no")); // added by udara 23-03-2015
				callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_NAME_2_HIRER_CODE"));
				callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_NAME_2_HIRER"));
				
				
				callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_VEHICAL_NO_HIRER"));
				callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO_1_HIRER"));
				callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_NUMBER_HIRER"));
				callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO_2_HIRER")); 
				callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS_HIRER"));
				callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_PROVINCE_HIRER"));
				callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_INS_DETAILS")); 
				callstmt1.setString(18,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MARKET"))); 
				callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_FORCED_SALE_INS_DETAILS")));  
				callstmt1.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_VEHICAL_NO_2_INS_DETAILS"));
				
				
				callstmt1.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_GRADE_INS_DETAILS"));
				callstmt1.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO_INS_DETAILS"));
				callstmt1.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR_INS_DETAILS"));
				callstmt1.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO_INS_DETAILS"));
				callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_HIRER_AGREE"));	
				callstmt1.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_NIC_COPY_1_AGREE"));
				callstmt1.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_NAME_VENDOR_CODE"));
				callstmt1.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_NAME_VENDOR"));
				callstmt1.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_NIC_COPY_VENDOR"));
				callstmt1.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO_1_VENDOR"));

				callstmt1.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_SIGNATOR_VENDOR"));
				callstmt1.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_INTRODUCER_VENDOR"));
				callstmt1.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_CR_BOOK_DOCS"));
				callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_COPY_1_DOCS"));
				callstmt1.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_MTA_6_DOCS"));
				callstmt1.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_PHOTOS_DOCS"));
				callstmt1.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_DELETION_DOCS"));
				callstmt1.setString(38,m_sn_methods.met_formdata(reqstr,"TXT_COPY_2_DOCS"));
				callstmt1.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_MTA_8_DOCS"));
				callstmt1.setString(40,m_sn_methods.met_formdata(reqstr,"TXT_VIC_DOCS"));
				

				callstmt1.setString(41,m_sn_methods.met_formdata(reqstr,"TXT_LICENSE_DOCS"));
				callstmt1.setString(42,m_sn_methods.met_formdata(reqstr,"TXT_COPY_3_DOCS"));
				callstmt1.setString(43,m_sn_methods.met_formdata(reqstr,"TXT_MTA_3_DOCS"));
				callstmt1.setString(44,m_sn_methods.met_formdata(reqstr,"TXT_DUP_KEY_DOCS"));
				
				callstmt1.setString(45,m_sn_methods.met_formdata(reqstr,"TXT_REFINANCE_CASE_INSURENCE"));   
				
				callstmt1.setString(46,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INITAL_CHARGES_INSURENCE"))); 
				callstmt1.setString(47,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAID_1_INSURENCE")));
				callstmt1.setString(48,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BAL_1_INSURENCE"))); 
				callstmt1.setString(49,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAMP_DUTY_INSURENCE")));  
				callstmt1.setString(50,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAID_2_INSURENCE")));   
				
				callstmt1.setString(51,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BAL_2_INSURENCE"))); 
				callstmt1.setString(52,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INSURENCE_CHARGES_INSU"))); 
				callstmt1.setString(53,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAID_3_INSURENCE")));  
				callstmt1.setString(54,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BAL_3_INSURENCE")));
				callstmt1.setString(55,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BAL_4_INSURENCE"))); 
				callstmt1.setString(56,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SUM_INSURED_INSU")));   
				callstmt1.setString(57,m_sn_methods.met_formdata(reqstr,"TXT_INSURENCE_COM_SELECTION"));
				callstmt1.setString(58,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_UPDATE_INSU"));
				callstmt1.setString(59,m_sn_methods.met_formdata(reqstr,"TXT_COVER_NOTE_INSU"));
				callstmt1.setString(60,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RENTAL_INSURENCE"))); 
				
				callstmt1.setString(61,m_sn_methods.met_formdata(reqstr,"TXT_RENTAL_LETTER_ISSUED_INSU"));
				callstmt1.setString(62,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CAPITAL_INSURANCE")));  
				callstmt1.setString(63,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SLAB_INSURANCE")));   
				callstmt1.setString(64,m_sn_methods.met_formdata(reqstr,"TXT_INSPECTION_APPROVALS"));
				callstmt1.setString(65,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CAPITAL_APPROVALS"))); 
				callstmt1.setString(66,m_sn_methods.met_formdata(reqstr,"TXT_CR_BOOK_NO_APPROVALS"));
				callstmt1.setString(67,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INT_CHARGES_APPROVALS"))); 
				callstmt1.setString(68,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CLOSING_STAT_APPROVALS"))); 
				callstmt1.setString(69,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_LESS_FIRST_RENT_APPROVAL"))); 
				callstmt1.setString(70,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_F_RENTAL_APPROVALS"))); 
				
				callstmt1.setString(71,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CLS_STAT_APPROVAL"))); 
				callstmt1.setString(72,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_APPROVAL"))); 
				callstmt1.setString(73,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_CHARGES_APPROVAL"))); 
				callstmt1.setString(74,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAMP_DUTY_APPROVAL"))); 
				callstmt1.setString(75,m_sn_methods.met_formdata(reqstr,"TXT_CR_APPROVALS"));
				callstmt1.setString(76,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ENTRY_APPROVAL")));  
				callstmt1.setString(77,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INSU_CHARGES_APPROVALS"))); 
				callstmt1.setString(78,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TO_BE_PAID_APPROVALS"))); 
				callstmt1.setString(79,m_sn_methods.met_formdata(reqstr,"TXT_RMV_PAPER_APPROVAL"));
				callstmt1.setString(80,m_sn_methods.met_formdata(reqstr,"TXT_CHECKED_BY_APPROVALS"));
				
				callstmt1.setString(81,m_sn_methods.met_formdata(reqstr,"TXT_TEL_APPROVAL"));
				callstmt1.setString(82,m_sn_methods.met_formdata(reqstr,"TXT_CASE_CANVASSED_BY"));
				callstmt1.setString(83,m_sn_methods.met_formdata(reqstr,"TXT_NAME_CASE"));
				callstmt1.setString(84,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_CAPITALS_CREDIT_CO"))); 
				callstmt1.setString(85,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_NAME_CREDIT_CO"));
				callstmt1.setString(86,m_sn_methods.met_formdata(reqstr,"TXT_SIGANTURE_CREDIT_CO"));
				

				callstmt1.setString(87,m_username);
				callstmt1.setString(88,m_sn_methods.met_formdata(reqstr,"TXT_LEAD_SOURCE"));
				callstmt1.setString(89,m_sn_methods.met_formdata(reqstr,"hid_generate_status")); // added by udara 19-05-2015
				callstmt1.setString(90,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DONE_BY")); // added by udara 16-06-2015
				
				callstmt1.setString(91,m_sn_methods.met_formdata(reqstr,"TXT_CONF_RPT_COMMENT")); // added by udara 30-06-2015
				//callstmt1.setString(92,m_sn_methods.met_formdata(reqstr,"TXT_BROKER_COMMISSION")); // added by udara 14-09-2015
				callstmt1.setString(92,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BROKER_COMMISSION"))); 
				//callstmt1.setString(61,m_username);
				
				callstmt1.setString(93,m_sn_methods.met_formdata(reqstr,"TXT_ITEM_CATEGORY")); // added by udara 09-06-2017
				callstmt1.setString(94,m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CATEGORY")); // added by udara 09-06-2017
				
				callstmt1.setString(95,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PRICING_RATE"))); // added by udara 11-07-2017
				callstmt1.setString(96,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CAPITALIZATION_AMOUNT"))); // added by udara 11-07-2017
				callstmt1.setString(97,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DOWN_PAYMENT"))); // added by udara 11-07-2017
				callstmt1.setString(98,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ACTUAL_CAPITAL"))); // added by udara 11-07-2017
				callstmt1.setString(99,m_sn_methods.met_formdata(reqstr,"TXT_CASE_CANVASSED_BY_NAME")); // added by udara 11-07-2017
				callstmt1.setString(100,m_snap_position);
				callstmt1.setString(101,m_sn_methods.met_formdata(reqstr,"hid_application_no"));
				
				callstmt1.execute();
				
				callstmt1.close();
				
				out.println("Test 2");
				
				out.println(" m_guarantor_count " + m_sn_methods.met_formdata(reqstr,"hid_guarantor_count"));
				
				int m_guarantor_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_guarantor_count")); 
				
				out.println("Test 3");
				
				out.println(m_guarantor_count);
				
				out.println("Test 4");
				
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CONFIRMATION_RPT_GURA_SAVE("+
											":1,:2,:3,:4,:5,:6,:7,:8"+													
											"); END;");
				
				for(int i=1; i<=m_guarantor_count; i++){
					callstmt2.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
			        callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CODE_AGREE_"+i));
					callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_GUARANTOR_AGREE_"+i)); // added by udara 20-05-2015
					//callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_NIC_COPY_AGREE_"+i));
					callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_NAME_AGREE_"+i));
					callstmt2.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_NUMBER_AGREE_"+i));
					callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_NIC_COPY_AGREE_"+i)); 
					callstmt2.setString(7,m_username);
					callstmt2.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_AGREE_ORDER_"+i)); // added by udara 02-11-2015
					callstmt2.execute();
				}
				
				callstmt2.close();
				conn.commit();
				
				out.println("Test 5");
				
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				
				if(m_view_status.equals("view")){
					out.println(" window.close(); ");
				}
				else{
					//out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_Confirmation_Report_Detail_View?chksql=main_page&finance_no="+m_finance_no+"';");
					out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=Y&compare_status=N&generate_status=N&view_only_status=Y&snap_position=VIEW&finance_no="+m_finance_no+"';");
				}
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				
			}
			catch (Exception ex) {
				try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
			
		}
	}
