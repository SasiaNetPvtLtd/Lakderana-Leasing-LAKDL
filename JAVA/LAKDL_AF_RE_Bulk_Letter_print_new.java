//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Bulk_Letter_print_new extends javax.servlet.http.HttpServlet { 
	
	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2,stmt1,stmt_view_let,stmt_download_pdf;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs2,rs1,rs_view_file,rs_download_pdf;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException {
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn = null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		Statement stmt=null,stmt2=null,stmt1=null;
		ResultSet rs=null,rs2=null,rs1=null;
		
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;

			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "COLLECTION_OFFICER";	
			String m_order_by_type = "ASC";
            String Count_query="";
			String m_count="";
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			
			
			if(m_chksql.equals("LOAD_TERMINATION_LETTER"))
			{
				
				try
				{
					
					String m_string="";				
					String m_finance_no=req.getParameter("finance_no");
					String m_letter_type="";
					String client_code=req.getParameter("CLIENT_CODE");
					String m_letter_category=req.getParameter("LETTER_CATEGORY");
					String m_row = req.getParameter("row");
					//String m_branch = req.getParameter("branch"); // added by udara 24-04-2019
					
					String m_branch = "";
					
					if(req.getParameter("branch")!=null ){
						m_branch=req.getParameter("branch").trim();
					}
					
					//out.println("m_row="+m_row);
					String m_row_filter="";
					
					if (!m_row.equals("")){
				     //m_row_filter=" AND ROWNUM<='"+m_row+"'";	// commented by udara 23-04-2019
						
						m_row_filter=" WHERE RNO <= '"+m_row+"'"; // added by udara 23-04-2019
						
					// m_broker_filter =" AND A.BROKER_CODE='"+m_broker_code+"'";		
					// " AND ROWNUM <= '"+m_row+"' ;	
				    }
					
					/*
					stmt1=conn.createStatement();
					stmt2=conn.createStatement();
					stmt=conn.createStatement();
					*/
					
					String query="";
					
					
					/*
					if(m_letter_category.equals("1STREIM") || m_letter_category.equals("FINREIM")){
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE , "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" A.ARR_AMOUNT, "+
							" A.AGE_NEW,  "+
							" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							" RENTAL_AMOUNT "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
								" ORDER BY A.AGE_NEW DESC ";
					}
					else{
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" B.NA_AMOUNT ARR_AMOUNT,  "+
							" ROUND(A.AGE_NEW) AGE_NEW, "+
							" TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							" DUE_RENTAL_AMOUNT RENTAL_AMOUNT "+
							      " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							      " WHERE A.CLIENT_CODE = '"+client_code+"' "+
								  " AND A.FINANCE_NO = B.FINANCE_NO "+
							      " AND B.NA_AMOUNT > 0 "+
							      " AND A.TOTAL_AMOUNT > 0 ";
					}
					*/
					
	// Record Count 
	
	if(m_letter_category.equals("NOTTERM1")){
		
	/*	Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
							//	m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 ";
	 */
	
	   Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B, "+
							   " ( "+
			                   "  SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
			                   " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
			                   " WHERE FIN_NO = '"+m_finance_no+"' "+
			                   " GROUP BY FIN_NO  "+
			                   " ) TABLE_ODI	  "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+) "+
							   " ";
		
	 }	
	 else{
			
			
		Count_query  = " SELECT COUNT(*) "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
							//	" AND ROWNUM <= 50 "+ 
							//	m_row_filter+
							    " ";
			
		}
		
		rs2= stmt2.executeQuery(Count_query);
		out.println("<!--Record Cound LOAD_TERMINATION_LETTER Onload ="+Count_query+"-->");
		
		if(rs2.next()){
			
			 m_count=rs2.getString(1);
			
			//out.println("m_count="+m_count);
		
		}
	 // End Record Count 
		
	if(m_letter_category.equals("NOTTERM1")){
						
				/*	   query = " SELECT A.FINANCE_NO, "+
							   " A.CLIENT_CODE, "+
							   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							   " B.NA_AMOUNT ARR_AMOUNT,  "+
							   " ROUND(A.AGE_NEW) AGE_NEW, "+
							   " TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							   " DUE_RENTAL_AMOUNT RENTAL_AMOUNT, "+
								" TO_CHAR(ENT_DATE,'DD-MM-YYYY')  "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
								m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 ";
				  */
				    query = "   SELECT A.FINANCE_NO,                                               "+
                            "    A.CLIENT_CODE,                                                    "+
                            "     "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),                      "+
                            "    NVL(B.NA_AMOUNT,0) + NVL(TABLE_ODI.ODI_BAL_AMOUNT,0) ARR_AMOUNT,  "+
                            "    ROUND(A.AGE_NEW) AGE_NEW,         "+
                            "    TO_CHAR(SYSDATE,'DD-MM-YYYY'),    "+
                            "    DUE_RENTAL_AMOUNT RENTAL_AMOUNT,  "+ 
                            "      TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
							"    "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
							"    "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
                            "    FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B , "+

                            " ( "+
                            " SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
                            " FROM AF_CO_PRO_OD_INTEREST_MONTHLY                "+
                            " WHERE FIN_NO = '"+m_finance_no+"'                 "+
                            " GROUP BY FIN_NO                                   "+
                            " ) TABLE_ODI                                       "+ 

                            "    WHERE A.CLIENT_CODE = '"+client_code+"'        "+          
                            "    AND A.FINANCE_NO = B.FINANCE_NO                "+
                            "    AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+)         "+ 
			                " ";
						
					}	
					else{
						
						// commented by udara 23-04-2019
						/*
						
					    query = " SELECT A.FINANCE_NO, "+
							    " A.CLIENT_CODE , "+
							    " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							    " A.ARR_AMOUNT, "+
							    " A.AGE_NEW,  "+
							    " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							    " RENTAL_AMOUNT, "+
								" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY')  "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
							//	" AND ROWNUM <= 50 "+ 
								m_row_filter+
							    //" ORDER BY A.ENT_DATE DESC,A.AGE_NEW DESC,A.ARR_AMOUNT DESC "; // commented by udara 23-04-2019
								" ORDER BY A.AGE_NEW DESC,A.ARR_AMOUNT DESC, A.ENT_DATE DESC "; // added by udara 23-04-2019
				
								*/
						
								// added by udara 23-04-2019
						
								query = " SELECT FINANCE_NO, "+
									 " CLIENT_CODE, "+       
									 " CLIENT_NAME,  "+      
									 " ARR_AMOUNT,    "+     
									 " AGE_NEW,  "+          
									 " DUE_DATE,   "+        
									 " RENTAL_AMOUNT,   "+   
									 " ENT_DATE,   "+   
									 " CLIENT_ADDRESS, "+ // 9 added by udara 19-06-2019
									 " CO_APPLICANT_STATUS "+ // 10 added by udara 19-06-2019
												  
											" FROM "+
						                    
						                    " (select rownum RNO,main_table.* from((select A.FINANCE_NO FINANCE_NO,  "+                
												             " A.CLIENT_CODE CLIENT_CODE, "+                       
												             " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)CLIENT_NAME,  "+
												             " A.ARR_AMOUNT ARR_AMOUNT,  "+                                          
												             " A.AGE_NEW AGE_NEW,   "+                                                
												             " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')DUE_DATE,  "+                         
												             " A.RENTAL_AMOUNT RENTAL_AMOUNT,    "+                                  
												             " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,    "+  
															 " "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
												             " "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
												              
						                        " FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A   "+
												            " WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
												            " AND A.REMINDER_STATUS = 'N'  "+                   
												            " AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'    "+  
												            " AND A.CLIENT_CODE LIKE '%"+client_code+"%' "+
															" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
						                        " ORDER BY A.ENT_DATE DESC, A.AGE_NEW DESC, ARR_AMOUNT DESC "+
						                        " ) main_table) "+
						                        " )   "+  
												      m_row_filter;

					    }

					
					//out.println(query);
					
					rs1= stmt1.executeQuery(query);
					
					out.println("<!--LOAD_TERMINATION_LETTER="+query+"-->");
					
				/*	out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' align='center' ><B>BY REGISTERED POST</B></tr>");
					out.println("</table>");
				*/	
					//<input type="button" value="Click me" onclick="msg()">
					
					int m_actual_count = Integer.parseInt(m_count);
					int m_temp_count = 50;
					
					if(m_actual_count<50)
						m_temp_count = m_actual_count;
						
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print Count : "+m_temp_count+" of "+m_count+" </b></DIV></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' >";
				/*	m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row+"');\"  >&nbsp;&nbsp;&nbsp;";
					m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0 To "+m_row+"  </td>";
				*/	
				    m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row+"');\"  >&nbsp;&nbsp;&nbsp;";
					m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1 To "+m_row+"  </td>";
				
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='1%' ><DIV class=div_input><b>No.</b></DIV></td>";
					m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Finance No</b></DIV></td>";//1
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Address</b></DIV></td>";// added by udara 19-06-2019
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><B>Statement Date</b></DIV></td>";//4
					m_string=m_string+"<td width='5%' ><DIV class=div_input align=right ><b>Age</b></DIV></td>"; //5
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Total&nbsp;Arrears</b></DIV></td>";//6	
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Rental&nbsp;Amount</b></DIV></td>";//7
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><b>Co-App Status</b></DIV></td>"; // added by udara 19-06-2019
					/*
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV></td>";//8
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Cancel</b></DIV></td>";//9
					*/
					
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV> <input type='checkbox' name='CHK_ALL' value ='ON' checked onclick='check_all();' > </td>";//8
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Cancel</b></DIV> <input type='checkbox' name='CHK_ALL_DISSAP' value ='ON'  onclick='check_all_dissaprove();' > </td>";//9
					
					if(m_letter_category.equals("NOTTERM1")){
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV></td>";	//10
					}	
					
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					int chk_nums=0;
					int j=0;
					String M_NOT_STATUS="N";
					String M_LOT_STATUS="N";

					while(rs1.next())
					{	
						chk_nums++;												
						
						if(j==0)
						{
							m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
							j=1;
						}
						else
						{
							m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
							j=0;
						}
						
						
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='1%' class=div_input style=\"text-align:right\" > "+chk_nums+" </td>";
						//m_string=m_string+"<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='10%' class=div_input onClick=\"show_transaction_info('','"+rs1.getString(1)+"');\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:left\" > "+rs1.getString(9)+" </td>"; // added by udara 19-06-2019
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(8)+" </td>";
						m_string=m_string+"<td width='5%' class=div_input style=\"text-align:right\" > "+rs1.getString(5)+" </td>";
						m_string=m_string+"<td width='12%' class=div_input onClick=\"\" align=right ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\"\">"+nf.format(rs1.getDouble(4))+"</td>";
						//m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(16)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(16)+"\"><u>"+rs1.getString(17)+"</u></td>";
						
						
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:right\" > "+nf.format(rs1.getDouble(7))+" </td>";
						
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(10)+" </td>"; // added by udara 19-06-2019
						
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_APPROVE_TYPE_"+chk_nums+"'     name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_app('"+chk_nums+"');\" checked >";
						
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";
							
						}
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' name='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_dissap('"+chk_nums+"');\"  >";
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";	
						}	
						
						if(m_letter_category.equals("NOTTERM1")){
                        
						//m_string=m_string+"<td  class=div_input style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"window.opener.print_report('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf1.format(rs1.getDouble(7))+"','"+rs1.getString(10)+"','"+product_type+"','"+rs1.getString(6)+"','"+rs1.getString(12)+"','"+rs1.getString(14)+"');\"></td>";
						m_string=m_string+"<td  class=div_input style='cursor:hand' style=\"text-align:center\" ><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_letter('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf.format(rs1.getDouble(4))+"');\"></td>";
					    }
						
						//m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						
					}
					m_string=m_string+"<INPUT TYPE='HIDDEN' ID='NUM_CHKS' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
					
					//out.println("   	Page.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.hid_amount2.value)+'\" DISABLED > ' ; ");
					
				/*	
				rs1.close();
			    stmt1.close();	
				rs2.close();
			    stmt2.close();	
				*/
				
				}catch(Exception e)
				{
					e.printStackTrace();
					
				}	
				
			}
			
			else if(m_chksql.equals("LOAD_TERMINATION_LETTER_NEXT"))
			{
				
				try
				{
					
					String m_string="";				
					String m_finance_no=req.getParameter("finance_no");
					String m_letter_type="";
					String client_code=req.getParameter("CLIENT_CODE");
					String m_letter_category=req.getParameter("LETTER_CATEGORY");
					String m_row = req.getParameter("row_pre");
					String m_row_next = req.getParameter("row_next");
					
					//String m_branch = req.getParameter("branch"); // added by udara 24-04-2019
					String m_branch = "";
					
					if(req.getParameter("branch")!=null ){
						m_branch=req.getParameter("branch").trim();
					}
					
					//out.println("m_row="+m_row);
					//out.println("m_row_next="+m_row_next);
					String m_row_filter="";
					
					if (!m_row.equals("")&& !m_row_next.equals("")){
				   //  m_row_filter=" AND ROWNUM<='"+m_row+"'";	
					   //m_row_filter=" WHERE RNO >= '"+m_row+"' RNO AND <= '"+m_row_next+"' ";	
						m_row_filter=" WHERE RNO >= "+m_row+"  AND RNO <= "+m_row_next+" ";	
					// m_broker_filter =" AND A.BROKER_CODE='"+m_broker_code+"'";		
					// " AND ROWNUM <= '"+m_row+"' ;	
				    }
					
					/*
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					stmt2=conn.createStatement();
					*/
					
					String query="";
					
					/*
					if(m_letter_category.equals("1STREIM") || m_letter_category.equals("FINREIM")){
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE , "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" A.ARR_AMOUNT, "+
							" A.AGE_NEW,  "+
							" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							" RENTAL_AMOUNT "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
								" ORDER BY A.AGE_NEW DESC ";
					}
					else{
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" B.NA_AMOUNT ARR_AMOUNT,  "+
							" ROUND(A.AGE_NEW) AGE_NEW, "+
							" TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							" DUE_RENTAL_AMOUNT RENTAL_AMOUNT "+
							      " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							      " WHERE A.CLIENT_CODE = '"+client_code+"' "+
								  " AND A.FINANCE_NO = B.FINANCE_NO "+
							      " AND B.NA_AMOUNT > 0 "+
							      " AND A.TOTAL_AMOUNT > 0 ";
					}
					*/
					
	// Record Count 
	
	if(m_letter_category.equals("NOTTERM1")){
		
	/*	Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
							//	m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 "; */
	
	   Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B, "+
							   " ( "+
			                   "  SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
			                   " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
			                   " WHERE FIN_NO = '"+m_finance_no+"' "+
			                   " GROUP BY FIN_NO  "+
			                   " ) TABLE_ODI	  "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+) "+
							   " ";
		
	 }	
	 else{
			
			
		Count_query  = " SELECT COUNT(*) "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
							//	" AND ROWNUM <= 50 "+ 
							//	m_row_filter+
							    " ";
			
		}
		
		rs2= stmt2.executeQuery(Count_query);
		//out.println("<!--LOAD_TERMINATION_LETTER_NEXT Record Count ="+Count_query+" ");
		
		if(rs2.next()){
			
			 m_count=rs2.getString(1);
			
			//out.println("m_count="+m_count);
		
		}
	 // End Record Count 

					
	if(m_letter_category.equals("NOTTERM1")){
						
			/*		   query = " SELECT A.FINANCE_NO, "+
							   " A.CLIENT_CODE, "+
							   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							   " B.NA_AMOUNT ARR_AMOUNT,  "+
							   " ROUND(A.AGE_NEW) AGE_NEW, "+
							   " TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							   " DUE_RENTAL_AMOUNT RENTAL_AMOUNT, "+
								" TO_CHAR(ENT_DATE,'DD-MM-YYYY')  "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
							//	m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 ";
			 */				
					query = "   SELECT A.FINANCE_NO,                                               "+
                            "    A.CLIENT_CODE,                                                    "+
                            "    "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),                      "+
                            "    NVL(B.NA_AMOUNT,0) + NVL(TABLE_ODI.ODI_BAL_AMOUNT,0) ARR_AMOUNT,  "+
                            "    ROUND(A.AGE_NEW) AGE_NEW,         "+
                            "    TO_CHAR(SYSDATE,'DD-MM-YYYY'),    "+
                            "    DUE_RENTAL_AMOUNT RENTAL_AMOUNT,  "+ 
                            "    TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
							"    "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
							"    "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
                            "    FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B , "+

                            " ( "+
                            " SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
                            " FROM AF_CO_PRO_OD_INTEREST_MONTHLY                "+
                            " WHERE FIN_NO = '"+m_finance_no+"'                 "+
                            " GROUP BY FIN_NO                                   "+
                            " ) TABLE_ODI                                       "+ 

                            "    WHERE A.CLIENT_CODE = '"+client_code+"'        "+          
                            "    AND A.FINANCE_NO = B.FINANCE_NO                "+
                            "    AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+)         "+ 
			                " ";
			             
					
					
						
					}	
					else{
						
				/*	    query = " SELECT A.FINANCE_NO, "+
							    " A.CLIENT_CODE , "+
							    " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							    " A.ARR_AMOUNT, "+
							    " A.AGE_NEW,  "+
							    " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							    " RENTAL_AMOUNT, "+
								" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY')  "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
								" AND ROWNUM <= 50 "+ 
							//	m_row_filter+
							    " ORDER BY A.ENT_DATE,A.AGE_NEW DESC ";
					 */	
				
				//out.println("    m_row_filter    " + m_row_filter); 
				
						// commented by udara 23-04-2019
						 /*
						
						  query = " SELECT FINANCE_NO,  "+
						         " CLIENT_CODE,        "+
						         " CLIENT_NAME,        "+
						         " ARR_AMOUNT,         "+
						         " AGE_NEW,            "+
						         " DUE_DATE,           "+
						         " RENTAL_AMOUNT,      "+
						         " ENT_DATE            "+
						  
						       " FROM (select A.FINANCE_NO FINANCE_NO,                  "+
						       "      A.CLIENT_CODE CLIENT_CODE,                        "+
						       "      "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)CLIENT_NAME,  "+
							 //  "      '-' CLIENT_NAME,  "+
						       "      A.ARR_AMOUNT ARR_AMOUNT,                                            "+
						       "      A.AGE_NEW AGE_NEW,                                                  "+ 
						       "      TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')DUE_DATE,                           "+
						       "      A.RENTAL_AMOUNT RENTAL_AMOUNT,                                      "+
						       "      TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,                          "+ 
						             
						       "        ROWNUM RNO FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A   "+
						       "     WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
						       "     AND A.REMINDER_STATUS = 'N'                     "+
						       "     AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'      "+
						       "     AND A.CLIENT_CODE LIKE '%"+client_code+"%')     "+
						       m_row_filter +
						       //" ORDER BY ENT_DATE DESC,AGE_NEW DESC,ARR_AMOUNT DESC ";	// commented by udara 23-04-2019
								" ORDER BY AGE_NEW DESC, ARR_AMOUNT DESC, ENT_DATE DESC ";	// added by udara 23-04-2019
								//query = query + m_row_filter;
								//query = query + " ORDER BY ENT_DATE,AGE_NEW DESC ";
								
								*/
							
							query = " SELECT FINANCE_NO, "+
									 " CLIENT_CODE, "+       
									 " CLIENT_NAME,  "+      
									 " ARR_AMOUNT,    "+     
									 " AGE_NEW,  "+          
									 " DUE_DATE,   "+        
									 " RENTAL_AMOUNT,   "+   
									 " ENT_DATE,   "+
									 " CLIENT_ADDRESS, "+ // added by udara 19-06-2019
										" CO_APPLICANT_STATUS "+ // 10 added by udara 19-06-2019
												  
											" FROM "+
						                    
						                    " (select rownum RNO,main_table.* from((select A.FINANCE_NO FINANCE_NO,  "+                
												             " A.CLIENT_CODE CLIENT_CODE, "+                       
												             " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)CLIENT_NAME,  "+
												             " A.ARR_AMOUNT ARR_AMOUNT,  "+                                          
												             " A.AGE_NEW AGE_NEW,   "+                                                
												             " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')DUE_DATE,  "+                         
												             " A.RENTAL_AMOUNT RENTAL_AMOUNT,    "+                                  
												             " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,    "+      
															 " "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
																"    "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
												             
												              
						                        " FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A   "+
												            " WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
												            " AND A.REMINDER_STATUS = 'N'  "+                   
												            " AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'    "+  
												            " AND A.CLIENT_CODE LIKE '%"+client_code+"%' "+
															" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
						                        " ORDER BY A.ENT_DATE DESC, A.AGE_NEW DESC, ARR_AMOUNT DESC "+
						                        " ) main_table) "+
						                        " )   "+  
												      m_row_filter; 
								
								
						

					    }
					
					//out.println("    query    " + query); 

					
					//out.println(query);
					
					rs1= stmt1.executeQuery(query);

					
					//out.println("<!--LOAD_TERMINATION_LETTER_NEXT= "+query+" ");
					
				/*	out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' align='center' ><B>BY REGISTERED POST</B></tr>");
					out.println("</table>");
				*/	
					//<input type="button" value="Click me" onclick="msg()">
			/*		m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print </b></DIV> </td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
			*/		
			
					int m_actual_count = Integer.parseInt(m_count);
					int m_temp_count = 50;
					
					if(m_actual_count<50)
						m_temp_count = m_actual_count;
			
			
			        m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print Count : "+m_temp_count+" of "+m_count+" </b></DIV></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' >";
				/*	m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;";
					m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+m_row+" To "+m_row_next+" </td>";
				*/	
				    m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;";
					m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+(Integer.parseInt(m_row)+1)+" To "+m_row_next+" </td>";
					
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='1%' ><DIV class=div_input><b>No.</b></DIV></td>";
					m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Finance No</b></DIV></td>";//1
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Address</b></DIV></td>";
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><B>Statement Date</b></DIV></td>";//4
					m_string=m_string+"<td width='5%' ><DIV class=div_input align=right ><b>Age</b></DIV></td>"; //5
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Total&nbsp;Arrears</b></DIV></td>";//6	
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Rental&nbsp;Amount</b></DIV></td>";//7
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><b>Co-App Status</b></DIV></td>"; // added by udara 19-06-2019
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV><input type='checkbox' name='CHK_ALL' value ='ON' checked onclick='check_all();' ></td>";//8
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Cancel</b></DIV> <input type='checkbox' name='CHK_ALL_DISSAP' value ='ON'  onclick='check_all_dissaprove();' > </td>";//9
					if(m_letter_category.equals("NOTTERM1")){
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV></td>";	//10
					}	
					
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					int chk_nums=0;
					int j=0;
					String M_NOT_STATUS="N";
					String M_LOT_STATUS="N";

					while(rs1.next())
					{	
						chk_nums++;												
						
						if(j==0)
						{
							m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
							j=1;
						}
						else
						{
							m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
							j=0;
						}
						
						
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='1%' class=div_input style=\"text-align:right\" > "+chk_nums+" </td>";
						//m_string=m_string+"<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='10%' class=div_input onClick=\"show_transaction_info('','"+rs1.getString(1)+"');\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:left\" > "+rs1.getString(9)+" </td>"; // added by udara 19-06-2019
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(8)+" </td>";
						m_string=m_string+"<td width='5%' class=div_input style=\"text-align:right\" > "+rs1.getString(5)+" </td>";
						m_string=m_string+"<td width='12%' class=div_input onClick=\"\" align=right ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\"\">"+nf.format(rs1.getDouble(4))+"</td>";
						//m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(16)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(16)+"\"><u>"+rs1.getString(17)+"</u></td>";
						
						
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:right\" > "+nf.format(rs1.getDouble(7))+" </td>";
						
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(10)+" </td>"; // added by udara 19-06-2019
						
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_APPROVE_TYPE_"+chk_nums+"'     name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_app('"+chk_nums+"');\" checked >";
						
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";
							
						}
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' name='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_dissap('"+chk_nums+"');\"  >";
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";	
						}	
						
						if(m_letter_category.equals("NOTTERM1")){
                        
						//m_string=m_string+"<td  class=div_input style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"window.opener.print_report('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf1.format(rs1.getDouble(7))+"','"+rs1.getString(10)+"','"+product_type+"','"+rs1.getString(6)+"','"+rs1.getString(12)+"','"+rs1.getString(14)+"');\"></td>";
						m_string=m_string+"<td  class=div_input style='cursor:hand' style=\"text-align:center\" ><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_letter('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf.format(rs1.getDouble(4))+"');\"></td>";
					    }
						
						//m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						
					}
					m_string=m_string+"<INPUT TYPE='HIDDEN' ID='NUM_CHKS' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
					
					//out.println("   	Page.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.hid_amount2.value)+'\" DISABLED > ' ; ");
					
				/*	
				rs1.close();
			    stmt1.close();	
				rs2.close();
			    stmt2.close();
				*/
				}catch(Exception e)
				{
					out.println(e.toString());
					e.printStackTrace();
					
				}	
				
			}
			
			else if(m_chksql.equals("LOAD_TERMINATION_LETTER_PREVIOUS"))
			{
				
				try
				{
					
					String m_string="";				
					String m_finance_no=req.getParameter("finance_no");
					String m_letter_type="";
					String client_code=req.getParameter("CLIENT_CODE");
					String m_letter_category=req.getParameter("LETTER_CATEGORY");
					String m_row = req.getParameter("row_pre");
					String m_row_next = req.getParameter("row_next");
					//String m_branch = req.getParameter("branch");
					String m_branch = "";
					
					if(req.getParameter("branch")!=null ){
						m_branch=req.getParameter("branch").trim();
					}
					
					//out.println("m_row="+m_row);
					//out.println("m_row_next="+m_row_next);
					String m_row_filter="";
					
					if (!m_row.equals("")&& !m_row_next.equals("")){
				   //  m_row_filter=" AND ROWNUM<='"+m_row+"'";	
					//   m_row_filter=" AND ROWNUM BETWEEN '"+m_row_next+"' AND '"+m_row+"' ";
					   m_row_filter=" WHERE RNO >= "+m_row+"  AND RNO <= "+m_row_next+" ";		
					// m_broker_filter =" AND A.BROKER_CODE='"+m_broker_code+"'";		
					// " AND ROWNUM <= '"+m_row+"' ;	
				    }
					
					
				/*	stmt=conn.createStatement();
					stmt1=conn.createStatement();
					stmt2=conn.createStatement();
				*/	
					
					String query="";
					
					/*
					if(m_letter_category.equals("1STREIM") || m_letter_category.equals("FINREIM")){
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE , "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" A.ARR_AMOUNT, "+
							" A.AGE_NEW,  "+
							" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							" RENTAL_AMOUNT "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
								" ORDER BY A.AGE_NEW DESC ";
					}
					else{
						query = " SELECT A.FINANCE_NO, "+
							" A.CLIENT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							" B.NA_AMOUNT ARR_AMOUNT,  "+
							" ROUND(A.AGE_NEW) AGE_NEW, "+
							" TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							" DUE_RENTAL_AMOUNT RENTAL_AMOUNT "+
							      " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							      " WHERE A.CLIENT_CODE = '"+client_code+"' "+
								  " AND A.FINANCE_NO = B.FINANCE_NO "+
							      " AND B.NA_AMOUNT > 0 "+
							      " AND A.TOTAL_AMOUNT > 0 ";
					}
					*/
		// Record Count 
	
	if(m_letter_category.equals("NOTTERM1")){
		
	/*	Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
							//	m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 ";
	 */
	
	    Count_query = " SELECT COUNT(*) "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B, "+
							   " ( "+
			                   "  SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
			                   " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
			                   " WHERE FIN_NO = '"+m_finance_no+"' "+
			                   " GROUP BY FIN_NO  "+
			                   " ) TABLE_ODI	  "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+) "+
							   " ";
		
	 }	
	 else{
			
			
		Count_query  = " SELECT COUNT(*) "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
							//	" AND ROWNUM <= 50 "+ 
							//	m_row_filter+
							    " ";
			
		}
		
		rs2= stmt2.executeQuery(Count_query);
		
		out.println("<!--LOAD_TERMINATION_LETTER_PREVIOUS= "+Count_query+" ");
		
		if(rs2.next()){
			
			 m_count=rs2.getString(1);
			
			//out.println("m_count="+m_count);
		
		}
	 // End Record Count 			
					
					
	if(m_letter_category.equals("NOTTERM1")){
						
				/*	   query = " SELECT A.FINANCE_NO, "+
							   " A.CLIENT_CODE, "+
							   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							   " B.NA_AMOUNT ARR_AMOUNT,  "+
							   " ROUND(A.AGE_NEW) AGE_NEW, "+
							   " TO_CHAR(SYSDATE,'DD-MM-YYYY'),  "+
							   " DUE_RENTAL_AMOUNT RENTAL_AMOUNT, "+
								" TO_CHAR(ENT_DATE,'DD-MM-YYYY')  "+
							   " FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B "+
							   " WHERE A.CLIENT_CODE = '"+client_code+"' "+
							   " AND A.FINANCE_NO = B.FINANCE_NO "+
							   " AND B.NA_AMOUNT > 0 "+
							//	m_row_filter+
							   " AND A.TOTAL_AMOUNT > 0 ";
					*/
				 		
				    query = "   SELECT A.FINANCE_NO,                                               "+
                            "    A.CLIENT_CODE,                                                    "+
                            "     "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),                      "+
                            "    NVL(B.NA_AMOUNT,0) + NVL(TABLE_ODI.ODI_BAL_AMOUNT,0) ARR_AMOUNT,  "+
                            "    ROUND(A.AGE_NEW) AGE_NEW,         "+
                            "    TO_CHAR(SYSDATE,'DD-MM-YYYY'),    "+
                            "    DUE_RENTAL_AMOUNT RENTAL_AMOUNT,  "+ 
                            "      TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
							"    "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
							"    "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
                            "    FROM "+m_schema_name+".AF_MASTER_ARR_RPT_FINAL A, "+m_schema_name+".RECOVERY_CONTRACT_BALANCE B , "+

                            " ( "+
                            " SELECT FIN_NO, SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
                            " FROM AF_CO_PRO_OD_INTEREST_MONTHLY                "+
                            " WHERE FIN_NO = '"+m_finance_no+"'                 "+
                            " GROUP BY FIN_NO                                   "+
                            " ) TABLE_ODI                                       "+ 

                            "    WHERE A.CLIENT_CODE = '"+client_code+"'        "+          
                            "    AND A.FINANCE_NO = B.FINANCE_NO                "+
                            "    AND A.FINANCE_NO = TABLE_ODI.FIN_NO(+)         "+ 
			                " ";	
						
					}	
					else{
						
					/*    query = " SELECT A.FINANCE_NO, "+
							    " A.CLIENT_CODE , "+
							    " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
							    " A.ARR_AMOUNT, "+
							    " A.AGE_NEW,  "+
							    " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							    " RENTAL_AMOUNT, "+
								" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY')  "+
								" FROM  "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A "+
								" WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
								" AND A.REMINDER_STATUS = 'N'  "+
								" AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'  "+
								" AND A.CLIENT_CODE LIKE '%"+client_code+"%'  "+
								//" ORDER BY A.FINANCE_NO, A.DUE_DATE ";
							//	" AND ROWNUM <= 50 "+ 
								m_row_filter+
							    " ORDER BY A.ENT_DATE,A.AGE_NEW DESC ";
						*/	
					
					    
						// commented by udara 23-04-2019
						/*
					
					     query = " SELECT FINANCE_NO,  "+
						         " CLIENT_CODE,        "+
						         " CLIENT_NAME,        "+
						         " ARR_AMOUNT,         "+
						         " AGE_NEW,            "+
						         " DUE_DATE,           "+
						         " RENTAL_AMOUNT,      "+
						         " ENT_DATE            "+
						  
						       " FROM (select A.FINANCE_NO FINANCE_NO,                  "+
						       "      A.CLIENT_CODE CLIENT_CODE,                        "+
						       "      "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)CLIENT_NAME,  "+
							 //  "      '-' CLIENT_NAME,  "+
						       "      A.ARR_AMOUNT ARR_AMOUNT,                                            "+
						       "      A.AGE_NEW AGE_NEW,                                                  "+ 
						       "      TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')DUE_DATE,                           "+
						       "      A.RENTAL_AMOUNT RENTAL_AMOUNT,                                      "+
						       "      TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,                          "+ 
						             
						       "        ROWNUM RNO FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A   "+
						       "     WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
						       "     AND A.REMINDER_STATUS = 'N'                     "+
						       "     AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'      "+
						       "     AND A.CLIENT_CODE LIKE '%"+client_code+"%')     "+
						       m_row_filter +
						       //" ORDER BY ENT_DATE,AGE_NEW,ARR_AMOUNT DESC ";	// commented by udara 23-04-2019
								" ORDER BY AGE_NEW DESC ,ARR_AMOUNT DESC , ENT_DATE DESC ";	// added by udara 23-04-2019
								//query = query + m_row_filter;
								//query = query + " ORDER BY ENT_DATE,AGE_NEW DESC ";
								
								
								*/
								
								// added by udara 23-04-2019
								query = " SELECT FINANCE_NO, "+
									 " CLIENT_CODE, "+       
									 " CLIENT_NAME,  "+      
									 " ARR_AMOUNT,    "+     
									 " AGE_NEW,  "+          
									 " DUE_DATE,   "+        
									 " RENTAL_AMOUNT,   "+   
									 " ENT_DATE,   "+ 
									 " CLIENT_ADDRESS, "+ // added by udara 19-06-2019
										" CO_APPLICANT_STATUS "+ // 10 added by udara 19-06-2019
												  
											" FROM "+
						                    
						                    " (select rownum RNO,main_table.* from((select A.FINANCE_NO FINANCE_NO,  "+                
												             " A.CLIENT_CODE CLIENT_CODE, "+                       
												             " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)CLIENT_NAME,  "+
												             " A.ARR_AMOUNT ARR_AMOUNT,  "+                                          
												             " A.AGE_NEW AGE_NEW,   "+                                                
												             " TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')DUE_DATE,  "+                         
												             " A.RENTAL_AMOUNT RENTAL_AMOUNT,    "+                                  
												             " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,    "+                      
												             " "+m_schema_name+".AF_CO_GET_CLIENT_ADDRESS(A.CLIENT_CODE) CLIENT_ADDRESS, "+ // added by udara 19-06-2019
																"    "+m_schema_name+".AF_GET_CO_APPLICANT_STATUS(A.FINANCE_NO) CO_APPLICANT_STATUS "+ // added by udara 19-06-2019
												              
						                        " FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A   "+
												            " WHERE A.REMINDER_TYPE = '"+m_letter_category+"' "+
												            " AND A.REMINDER_STATUS = 'N'  "+                   
												            " AND A.FINANCE_NO LIKE '%"+m_finance_no+"%'    "+  
												            " AND A.CLIENT_CODE LIKE '%"+client_code+"%' "+
															" AND A.LOCATION_CODE LIKE '"+m_branch+"%'  "+ // added by udara 24-04-2019
						                        " ORDER BY A.ENT_DATE DESC, A.AGE_NEW DESC, ARR_AMOUNT DESC "+
						                        " ) main_table) "+
						                        " )   "+  
												      m_row_filter; 

					    }

					
					out.println("<!--LOAD_TERMINATION_LETTER_PREVIOUS= "+query+" ");
					
					rs1= stmt1.executeQuery(query);
					//out.println("query="+query);
					
				/*	out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' align='center' ><B>BY REGISTERED POST</B></tr>");
					out.println("</table>");
				*/	
					//<input type="button" value="Click me" onclick="msg()">
			/*		m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print </b></DIV> </td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
			*/		
			      //  out.println("check now");
					
					int m_actual_count = Integer.parseInt(m_count);
					int m_temp_count = 50;
					
					if(m_actual_count<50)
						m_temp_count = m_actual_count;
					
			        m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print Count : "+m_temp_count+" of "+m_count+"  </b></DIV></td>"; 
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
			
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' >";
			//		m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;";
			//		m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+m_row_next+" To "+m_row+" </td>";
					m_string=m_string+"<INPUT TYPE='button' id='TXT_PREVIOUS' name='TXT_PREVIOUS' VALUE=\"Previous\" onClick=\"load_termination_detail_previous('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;";
					//m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+m_row+" To "+m_row_next+" </td>"; 
					m_string=m_string+"<INPUT TYPE='button' id='TXT_NEXT' name='TXT_NEXT' VALUE=\"Next\" onClick=\"load_termination_detail_next('"+m_row_next+"');\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+(Integer.parseInt(m_row)+1)+" To "+m_row_next+" </td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='1%' ><DIV class=div_input><b>No.</b></DIV></td>";
					m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Finance No</b></DIV></td>";//1
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
					m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Address</b></DIV></td>";
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><B>Statement Date</b></DIV></td>";//4
					m_string=m_string+"<td width='5%' ><DIV class=div_input align=right ><b>Age</b></DIV></td>"; //5
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Total&nbsp;Arrears</b></DIV></td>";//6	
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Rental&nbsp;Amount</b></DIV></td>";//7
					m_string=m_string+"<td width='10%' ><DIV class=div_input align=center ><b>Co-App Status</b></DIV></td>"; // added by udara 19-06-2019
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV><input type='checkbox' name='CHK_ALL' value ='ON' checked onclick='check_all();' ></td>";//8
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Cancel</b></DIV> <input type='checkbox' name='CHK_ALL_DISSAP' value ='ON'  onclick='check_all_dissaprove();' > </td>";//9
					if(m_letter_category.equals("NOTTERM1")){
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>Print</b></DIV></td>";	//10
					}	
					
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					int chk_nums=0;
					int j=0;
					String M_NOT_STATUS="N";
					String M_LOT_STATUS="N";

					while(rs1.next())
					{	
						chk_nums++;												
						
						if(j==0)
						{
							m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
							j=1;
						}
						else
						{
							m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
							j=0;
						}
						
						
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='1%' class=div_input style=\"text-align:right\" > "+chk_nums+" </td>";
					//	m_string=m_string+"<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='10%' class=div_input onClick=\"show_transaction_info('','"+rs1.getString(1)+"');\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"> <INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DUE_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"> <u>"+rs1.getString(1)+"</u></td>";
					    m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:left\" > "+rs1.getString(9)+" </td>"; // added by udara 19-06-2019
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(8)+" </td>";
						m_string=m_string+"<td width='5%' class=div_input style=\"text-align:right\" > "+rs1.getString(5)+" </td>";
						m_string=m_string+"<td width='12%' class=div_input onClick=\"\" align=right ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\"\">"+nf.format(rs1.getDouble(4))+"</td>";
						//m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(16)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(16)+"\"><u>"+rs1.getString(17)+"</u></td>";
						
						
						m_string=m_string+"<td width='12%' class=div_input style=\"text-align:right\" > "+nf.format(rs1.getDouble(7))+" </td>";
						
						m_string=m_string+"<td width='10%' class=div_input style=\"text-align:center\" > "+rs1.getString(10)+" </td>"; // added by udara 19-06-2019
						
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_APPROVE_TYPE_"+chk_nums+"'     name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_app('"+chk_nums+"');\" checked >";
						
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";
							
						}
						if(!m_letter_category.equals("NOTTERM1")){
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' name='TXT_DISSAPPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_select_dissap('"+chk_nums+"');\"  >";
						m_string=m_string+"</td>";
						}
						else
						{
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";	
						m_string=m_string+"</td>";	
						}	
						
						if(m_letter_category.equals("NOTTERM1")){
                        
						//m_string=m_string+"<td  class=div_input style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"window.opener.print_report('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf1.format(rs1.getDouble(7))+"','"+rs1.getString(10)+"','"+product_type+"','"+rs1.getString(6)+"','"+rs1.getString(12)+"','"+rs1.getString(14)+"');\"></td>";
						m_string=m_string+"<td  class=div_input style='cursor:hand' style=\"text-align:center\" ><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_letter('"+rs1.getString(2)+"','"+rs1.getString(1)+"','"+nf.format(rs1.getDouble(4))+"');\"></td>"; 
					    }
						
						//m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						
					}
					m_string=m_string+"<INPUT TYPE='HIDDEN' ID='NUM_CHKS' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
					
					//out.println("   	Page.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.hid_amount2.value)+'\" DISABLED > ' ; ");
					
				/*	
				rs1.close();
			    stmt1.close();	
				rs2.close();
			    stmt2.close();	
				*/
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}	
				
			}
			
			else if(m_chksql.equals("main_page"))
			{
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection Process - Bulk Print Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				//out.println("       <SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/bulk_print_generation.js'></SCRIPT>");  //based on the tab this js file will be replaced either by bulk_print_generation --> bulk_print_generation_view
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function load_main_bulk_print_view()");
				out.println("{ ");
				out.println("   document.Form1.hid_num.value=3;");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=main_printed_file_view_page\";");
				out.println("   load_interface(m_url,'NORM');");
				out.println("} ");
				
				
				
				out.println("function load_main_bulk_print()");	
				out.println("{  ");
				out.println("   document.Form1.hid_num.value=13;");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=Bulk_print_letter_generation\";");
				out.println("   load_interface(m_url,'NORM');");
				
				out.println("} ");
				
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value==\"G4\"){");
				out.println("              help_client();");
				out.println("			}");
				out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_assig.value==\"G6\"){");
				out.println("               help_finance_no();");
				out.println("			}");
				out.println("			else if(data_vec.length==0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_assig.value=='M_CLIENT' ){");
				out.println("     			team_help();");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_assig.value=='M_CLIENT' ){");
				out.println("				document.Form1.TEAM_HEAD.value=data_vec[0]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_assig.value=='M_USER' ){");
				out.println("     			help_collection_officer(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_assig.value=='M_USER' ){");
				out.println("				document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_assig.value=='M_SYS_DATE'  ){");
				out.println("				document.Form1.VAL_DAY.value=data_vec[0];");
				out.println("				document.Form1.VAL_MONTH.value=data_vec[1];");
				out.println("				document.Form1.VAL_YEAR.value=data_vec[2];");
				out.println("			}");
				
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data)");
				out.println("{ ");
				
				out.println("		if(document.Form1.hid_num.value==\"3\"){");
				out.println("               document.getElementById(\"View_bulk_print\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				
				out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"13\"){");
				out.println("				document.getElementById(\"Main_bulk_print\").innerHTML=m_data;");
				out.println("				document.Form1.hid_num.value=99;");
				
				out.println("               load_main_bulk_print_view(); "); //load the two tabs while loading the page at ONload event
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"23\")");
				out.println("       { ");
				out.println("			if(m_data==\"OK\") ");
				out.println("			{ ");
				out.println("				print_report(); ");
				out.println("			} ");
				out.println("			else ");
				out.println("			{ ");
				out.println("				alert('Error when generating Report...'+m_data); ");
				out.println("			} ");
				out.println("		} ");
				out.println("		if(document.Form1.hid_num.value==\"33\"){");
				out.println("               document.getElementById(\"Leter_div\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				out.println("       } ");
				out.println("		if(document.Form1.hid_num.value==\"43\"){");
				out.println("               document.getElementById(\"Leter_generated_files\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				out.println("       } ");
				
				out.println("} ");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("function dynamically_unload_js(old_js_file_name,new_replacement_file_name)");
				out.println("{");
				out.println("   alert('Unload-xxx-1');");
				out.println("   var allsuspects=document.getElementsByTagName(\"SCRIPT\"); ");
				out.println("	for (var i=allsuspects.length; i>=0; i--) "); // //search backwards within nodelist for matching elements to remove
				out.println("   {   ");
				out.println("   alert('Unload-xxx-12');");
				out.println("		if (allsuspects[i] && allsuspects[i].getAttribute(\"src\")!=null) ");
				out.println("       { ");
				out.println("   		 alert('Unload-xxx-1'+old_js_file_name+'allsuspects[i]'+allsuspects[i].getAttribute(\"src\"));");
				out.println("            if(allsuspects[i].getAttribute(\"src\")==old_js_file_name) ");
				out.println("            {  ");
				out.println("                   alert('xxx');");
				out.println("					allsuspects[i].parentNode.replaceChild(new_replacement_file_name,allsuspects[i]);  "); //replace the allallsuspects[i] by new_replacement_file_name 
				out.println("            }  ");
				
				out.println("		}else{ ");
				
				out.println("       }");
				out.println("   }");
				out.println("}");
				
				
				out.println("function dynamically_load_js(Option)");
				out.println("{ ");
				out.println("   if(Option==\"View\")");
				out.println("   {");
				out.println("        new_filename='"+m_html_client_url+"/bulk_print_generation_view.js'; ");
				out.println("        unload_filename='"+m_html_client_url+"/bulk_print_generation.js'; ");
				out.println("        New_file_reference=document.createElement(\"SCRIPT\"); ");
				out.println("        New_file_reference.setAttribute(\"type\",\"text/javascript\"); ");
				out.println("	     New_file_reference.setAttribute(\"src\",new_filename); ");
				out.println("        if (typeof New_file_reference!=\"undefined\") ");
				out.println("     	 {  ");
				out.println("            alert('xxx-1');");
				
				out.println("           dynamically_unload_js(unload_filename,New_file_reference);");
				out.println("        }  ");
				
				out.println("   }else if(Option==\"Generate\")");
				out.println("   {");
				out.println("        new_filename='"+m_html_client_url+"/bulk_print_generation.js'; ");
				out.println("        unload_filename='"+m_html_client_url+"/bulk_print_generation_view.js'; ");
				out.println("        New_file_reference=document.createElement(\"SCRIPT\"); ");
				out.println("        New_file_reference.setAttribute(\"type\",\"text/javascript\"); ");
				out.println("	     New_file_reference.setAttribute(\"src\",new_filename); ");
				out.println("        if (typeof file_reference!=\"undefined\") ");
				out.println("     	 {  ");
				out.println("                    alert('xxx-2');");
				
				out.println("           dynamically_unload_js(unload_filename,New_file_reference);");
				out.println("        }  ");
				
				
				
				out.println("   } ");
				out.println("} ");
				
				
				out.println("function display_bulk_print()");
				out.println("{ ");
				
				out.println("   document.getElementById('Main_bulk_print').style.display = 'inline'; ");
				out.println("	document.getElementById('View_bulk_print').style.display = 'none'; ");
				//out.println("	document.Form1.button_view_bulk_print.style.fontWeight = 'normal'; ");
				//out.println("	document.Form1.button_bulk_print.style.fontWeight = 'bold';  ");
				
				out.println("} ");  
				
				out.println("function display_view_bulk_print() ");
				out.println("{ ");
				
				out.println(" document.getElementById('Main_bulk_print').style.display = 'none'; ");
				out.println(" document.getElementById('View_bulk_print').style.display = 'inline'; ");
			//	out.println(" document.Form1.button_view_bulk_print.style.fontWeight = 'bold'; ");
			//	out.println(" document.Form1.button_bulk_print.style.fontWeight = 'normal'; ");
				
				out.println("} "); 
				
				
				
				out.println("function makeRequest1(obj) ");
				out.println("{ m_url=\"\"; ");
				out.println(" if(document.Form1.hid_assig.value==\"G4\")");
				out.println(" {");
				out.println("       ");
				out.println("  		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" }");
				out.println(" else if(document.Form1.hid_assig.value==\"G6\")");
				out.println(" {");
				out.println("       ");
				out.println("  		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" }");
				out.println(" else if(document.Form1.hid_assig.value=='M_CLIENT' )");
				out.println(" { ");
				out.println("       ");
				out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" } ");
				out.println(" else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println(" { ");
				out.println("      ");
				out.println("   	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_team_User&data_val=\"+obj.value+\"&ac_status=Y\";");	
				out.println(" } ");
				out.println(" if(m_url!=\"\") ");
				out.println(" {");	
				out.println(" 		load_interface(m_url,'XML');");
				out.println(" } ");
				out.println(" else ");
				out.println(" { ");
				out.println("    alert('url is not defined');");
				out.println(" } ");
				out.println("}");
				
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\")"); 
				out.println("       { ");
				out.println("			collection_officer_assign();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("			team_assign();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("			sub_team_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("			client_help_value_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
				out.println("			help_value_assign_5()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
				out.println("			finance_no_help_value_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
				out.println("			help_value_assign_7()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
				out.println("			help_value_assign_8()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"9\"){"); 
				out.println("			help_value_assign_9()");
				out.println("		}"); 
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{");
				out.println("clear()");
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("clear()");
				out.println("	}	"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function clear(){");			
				out.println("if(document.Form1.hid_help_type.value==\"4\")");
				out.println("{");
				out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\"; "); 
				out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\"; ");
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"6\"){");
				out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"2\")");
				out.println("{ ");
				out.println("  document.Form1.TEAM_HEAD.value=\"\"; ");
				out.println("  document.Form1.TEAM_DESC.value=\"\"; ");
				out.println("}");
				
				// added by udara 24-04-2019
				out.println("if(document.Form1.hid_help_type.value==\"9\")");
				out.println("{ ");
				out.println("  document.Form1.TXT_LOCATION_CODE.value=\"\"; ");
				out.println("}");
				// end by udara 24-04-2019
				
				out.println("}");
				
				out.println("function assignState(val) "); 
				out.println("{");
				out.println("  document.Form1.hid_assig.value=val");
				out.println("}");
				
				// client code help
				out.println("function help_client() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				
				out.println("    m_sql = \"bulkprintClient_Sql\"; ");
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function help_finance_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  ");
				
				//out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_sql\";"); 
				out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_1_sql\";"); 
				out.println("    } else { ");
				
			//	out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_sql2\";");
			    out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_1_sql2\";");
				out.println("    }  ");	
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				// added by udara 24-04-2019
				out.println("function help_update_branch() {"); 
				out.println("    document.Form1.hid_help_type.value=\"9\";"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				// end by udara 24-04-2019
				
				out.println("function team_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    m_sql = \"m_help_TXT_BULKPRINT_TEAM_ID_sql\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function sub_team_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    m_sql = \"m_help_txt_bulk_print_sub_team_sql\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				
				out.println("function help_collection_officer() ");
				out.println("{ ");
				out.println("    document.Form1.hid_help_type.value='1' ");
				out.println("    m_sql = \"m_help_collection_officer\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@\"+document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("} "); 
				
				
				out.println("function client_help_value_assign() ");
				out.println("{ ");
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];");
				out.println("} ");
				
				out.println("function finance_no_help_value_assign() ");
				out.println("{ ");
				out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[6];"); 
				out.println("} ");
				
				// added by udara 24-04-2019
				out.println("function help_value_assign_9() ");
				out.println("{ ");
				out.println("   document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("} ");
				// end by udara 24-04-2019
				
				
				out.println("function team_assign(){");
				out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[2]");
				out.println(" document.Form1.TEAM_DESC.value =oBj.valout[3]");
				out.println("}");
				
				out.println("function sub_team_assign(){");
				out.println(" document.Form1.SUB_TEAM_HEAD.value =oBj.valout[2]");
				out.println(" document.Form1.SUB_TEAM_DESC.value =oBj.valout[3]");
				out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[4]");
				out.println(" document.Form1.TEAM_DESC.value =oBj.valout[5]");
				out.println("}");
				
				out.println("function collection_officer_assign() ");
				out.println("{");
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
				out.println("    document.Form1.SUB_TEAM_HEAD.value=oBj.valout[4];"); 
				out.println("    document.Form1.SUB_TEAM_DESC.value=oBj.valout[5];"); 
				out.println("    document.Form1.TEAM_HEAD.value=oBj.valout[6];"); 
				out.println("    document.Form1.TEAM_DESC.value=oBj.valout[7];"); 
				out.println("}"); 
				
				
				
				
				
				out.println("function MyDialog()");
				out.println("{      ");
				out.println("   this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				
				
				
				out.println(" function clear_window()	");
				out.println(" { ");
				out.println("   if(confirm(\"Are you sure you want to clear the screen? \")) "); 
				out.println("   { ");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=main_page';"); 
				out.println("   }"); 
				out.println(" }"); 
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function get_system_date() ");
				out.println("{ ");
				out.println("       assignState('M_SYS_DATE') ;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("} ");
				
				out.println("function load_termination_detail() ");
				out.println("{ ");
				
				out.println("	if(document.Form1.TXT_LETETR_CATEGORY.value=='NOTTERM1'){ ");
				
				out.println("  		if(document.Form1.TXT_FINANCE_NO.value!=\"\") ");
				out.println("  		{ ");
				out.println("   		document.Form1.hid_num.value=\"33\"; ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row=\"+document.Form1.TXT_ROW.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
			//    out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row=\"+document.Form1.TXT_ROW.value+\"&Total_Arrears=\"+document.Form1.TXT_FACILITY_NO_1.value;");
				out.println("			load_interface(m_url,'NO'); ");
				out.println("  		}  ");
				out.println("  		else ");
				out.println("  		{ ");
				out.println("     		alert('Please Select a Finance No'); ");
				out.println("  		} ");
				
				out.println(" 	} ");
				out.println(" 	else{ ");
				out.println("   	document.Form1.hid_num.value=\"33\"; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row=\"+document.Form1.TXT_ROW.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		load_interface(m_url,'NO'); ");
				out.println(" 	} ");
				
				out.println("} ");
				
				out.println("function load_termination_detail_next(m_row_count) ");
				out.println("{ ");
				out.println("document.Form1.TXT_PREVIOUS.disabled=false;"); 
				//out.println(" var row_count=Number(m_row_count)+Number(50);");
				//out.println(" document.Form1.hid_pre_row.value=50;");
				
				out.println(" var pre_row_count=document.Form1.hid_pre_row.value;");
				out.println(" var curent_row_count=Number(m_row_count)+Number(50);");
				
				//out.println(" var pre_row_count=Number(m_row_count)+Number(pre_row_count);");
				
				out.println(" document.getElementById('hid_pre_row_1').value = pre_row_count; ");
				out.println(" document.getElementById('hid_curent_row').value = curent_row_count; ");
				//out.println("alert(document.Form1.hid_curent_row.value);");
				
				//out.println("alert('curent_row_count='+curent_row_count);");
				out.println(" document.Form1.hid_pre_row.value=curent_row_count;");
				
				//out.println("alert(document.Form1.hid_pre_row.value);");
				
				out.println("	if(document.Form1.TXT_LETETR_CATEGORY.value=='NOTTERM1'){ ");
				
				out.println("  		if(document.Form1.TXT_FINANCE_NO.value!=\"\") ");
				out.println("  		{ ");
				out.println("   		document.Form1.hid_num.value=\"33\"; ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER_NEXT&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row_pre=\"+document.Form1.hid_pre_row_1.value+\"&row_next=\"+document.Form1.hid_curent_row.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
			//	out.println(" window.open(m_url)");
				out.println("			load_interface(m_url,'NO'); ");
				out.println("  		}  ");
				out.println("  		else ");
				out.println("  		{ ");
				out.println("     		alert('Please Select a Finance No'); ");
				out.println("  		} ");
				
				out.println(" 	} ");
				out.println(" 	else{ ");
				out.println("   	document.Form1.hid_num.value=\"33\"; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER_NEXT&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row_pre=\"+document.Form1.hid_pre_row_1.value+\"&row_next=\"+document.Form1.hid_curent_row.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
			//	out.println(" window.open(m_url)");
				out.println("		load_interface(m_url,'NO'); ");
				out.println(" 	} ");
				
				out.println("} ");
				
				
			/*	out.println("   	document.Form1.hid_num.value=\"33\"; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value; ");
				out.println("		load_interface(m_url,'NO'); ");
			
				out.println("} ");
			*/
			
			    out.println("function load_termination_detail_previous(m_row_count) ");
				out.println("{ ");
				//out.println(" var row_count=Number(m_row_count)+Number(50);");
				//out.println(" document.Form1.hid_pre_row.value=50;");
				
				//out.println("alert('m_row_count='+m_row_count);");
				
				out.println(" var pre_row_count=document.Form1.hid_pre_row.value-Number(100);");
				out.println(" var curent_row_count=Number(m_row_count)-Number(50);");
				
				//out.println(" var pre_row_count=Number(m_row_count)+Number(pre_row_count);");
				//out.println("alert('pre_row_count='+pre_row_count);");
				//out.println("alert('curent_row_count='+curent_row_count);");
				
				out.println("   if(m_row_count==\"50\"){");
				
				out.println("document.Form1.TXT_PREVIOUS.disabled=true;"); 
				
				out.println("  		} else{");
				
				out.println(" document.getElementById('hid_pre_row_1').value = pre_row_count; ");
				out.println(" document.getElementById('hid_curent_row').value = curent_row_count; ");
			//	out.println("alert(document.Form1.hid_curent_row.value);");
				
			//	out.println("alert('curent_row_count='+curent_row_count);");
				out.println(" document.Form1.hid_pre_row.value=curent_row_count;");
				
			//	out.println("alert(document.Form1.hid_pre_row.value);");
			
			    out.println("  		}");
			
			    out.println("	if(document.Form1.hid_curent_row.value=='0'){ ");
				
				out.println("alert('curent_row_count='+curent_row_count);");
			
				//out.println("   load_termination_detail(); ");
				
				out.println("  		}else{ ");
				
				out.println("	if(document.Form1.TXT_LETETR_CATEGORY.value=='NOTTERM1'){ ");
				
				out.println("  		if(document.Form1.TXT_FINANCE_NO.value!=\"\") ");
				out.println("  		{ ");
				out.println("   		document.Form1.hid_num.value=\"33\"; ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER_NEXT&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row_pre=\"+document.Form1.hid_pre_row_1.value+\"&row_next=\"+document.Form1.hid_curent_row.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
				//out.println(" window.open(m_url)");
				out.println("			load_interface(m_url,'NO'); ");
				out.println("  		}  ");
				out.println("  		else ");
				out.println("  		{ ");
				out.println("     		alert('Please Select a Finance No'); ");
				out.println("  		} ");
				
				out.println(" 	} ");
				out.println(" 	else{ ");
				out.println("   	document.Form1.hid_num.value=\"33\"; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_TERMINATION_LETTER_NEXT&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&row_pre=\"+document.Form1.hid_pre_row_1.value+\"&row_next=\"+document.Form1.hid_curent_row.value+\"&branch=\"+document.Form1.TXT_LOCATION_CODE.value;");
			//	out.println(" window.open(m_url)");
				out.println("		load_interface(m_url,'NO'); ");
				out.println(" 	} ");
				
				out.println("} ");
				
				out.println("} ");
				
				
				
				out.println("function print_report()");
				out.println("{ ");
				out.println("   AsAtdate=document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value ");
				out.println("   document.Form1.hid_num.value=\"43\"; ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=LOAD_GENERATED_LETTERS&TEAM_ID=\"+document.Form1.TEAM_HEAD.value+\"&SUBTEAM_ID=\"+document.Form1.SUB_TEAM_HEAD.value+\"&USER=\"+document.Form1.TXT_USER.value+\"&date=\"+AsAtdate; ");
				out.println("	load_interface(m_url,'NO'); ");
				out.println("} ");
				
				/*--------------- Print Report Notice of Termination Final ------------------------------------------------------------------------------------------------*/
				
				out.println("function print_letter(client_code,app_no,total_arr)");
				out.println("{ ");
				//out.println("alert('total_arr'+total_arr)");
				
				out.println("   letter_category=document.Form1.TXT_LETETR_CATEGORY.value; ");
				// Notice of Termination Final
				out.println(" 		if(letter_category==\"NOTTERM1\"  ){ ");
			//	out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_NOT_Reminder?chksql=main_page&document_code=LO_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
			    out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_NOT_Reminder?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println("} ");
				
				out.println("function open_pdf(finance_no,letter_category_id,letter_id,file_path)");
				out.println("{ ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print_new?chksql=DOWNLOAD_PDF&FINANCE_NO=\"+finance_no+\"&LETTER_CATEGORY_ID=\"+letter_category_id+\"&LETTER_ID=\"+letter_id; ");
				out.println("	document.Form1.action = m_url; ");
				out.println("   document.Form1.submit(); ");
				
				out.println("} ");
				
				
				out.println("function before_submit()");
				out.println("{   ");
				out.println("    atleast_one=false;  ");
				out.println("    if(document.Form1.NUM_CHKS)" );
				out.println("    {   ");
				out.println("    	for(i=1;i<=parseInt(document.Form1.NUM_CHKS.value);i++)");
				out.println("    	{   ");
				out.println("           ");
				out.println("        if(document.getElementById(\"TXT_APPROVE_TYPE_\"+i).checked==true)");
				out.println("        {   ");
				out.println("            atleast_one=true; ");
				out.println("        }   ");
				
				out.println("        if(document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+i).checked==true)");
				out.println("        {   ");
				out.println("            atleast_one=true; ");
				out.println("        }   ");
				
				
				out.println("    	}   ");
				out.println("    	if(atleast_one)");
				out.println("    	{   ");
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_Print_Letters_New_Save';");  //added by nuwan de silva
				out.println("			document.Form1.submit();	"); 
				out.println("        ");
				out.println("    	}else   ");
				out.println("       {   ");
				out.println("         alert('Please Select Atleast One Record');   ");
				out.println("       }   ");
				out.println("     }else   ");
				out.println("     {   ");
				out.println("    	alert('No record Available To Generate the Letters');   ");
				out.println("     }   ");
				out.println("}  ");
				
				
				// added by udara 09-05-2019
				out.println("function check_all(){ "); 
				//out.println(" alert('check_all'); "); 
				out.println(" var row_count = document.Form1.NUM_CHKS.value; "); 
				//out.println(" alert(row_count); ");
					
				out.println("   if(document.getElementById('CHK_ALL').checked==true){  ");
				//out.println("         alert('checked'); ");
				out.println("         document.getElementById('CHK_ALL_DISSAP').checked=false; ");
				out.println("         for (i = 1; i <= row_count; i++) { ");
				out.println("              document.getElementById('TXT_APPROVE_TYPE_'+i).checked = true; ");
				out.println("              document.getElementById('TXT_DISSAPPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById(\"TXT_APPROVE_TYPE_\"+i).value = 'Y'; ");
				out.println("              document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("         }  ");
				out.println("   } "); 
				out.println("   else{ "); 
				//out.println("         alert('uncheck'); ");
				out.println("         for (i = 1; i <= row_count; i++) { ");
				out.println("              document.getElementById('TXT_APPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById('TXT_DISSAPPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById(\"TXT_APPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("              document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("         }  ");
				out.println("   } "); 

				out.println("} "); 
				// end by udara 09-05-2019
				
				out.println(" function load_letter_category()");
				out.println(" { ");
				out.println("   document.getElementById(\"Leter_div\").innerHTML=\"\"; ");
				out.println("   if(document.Form1.NUM_CHKS)");
				out.println("   { ");
				out.println("      document.Form1.NUM_CHKS.value=\"0\"; ");
				out.println("   } ");
				//out.println("   load_termination_detail(); ");
				out.println(" } ");
				
				out.println("function check_select_app(row_id)");
				out.println("{ ");
				//out.println("  alert('check_select_app');  ");
				out.println("  if(document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).checked==true){ ");
				//out.println("      alert('tick');  ");
				out.println("      document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).value = 'Y'; ");
				out.println("      document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).checked = false; ");
				out.println("      document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).value = 'N'; ");
				out.println("  } ");
				out.println("  else if(document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).checked==false){ ");
				//out.println("      alert('un tick');  ");
				out.println("      document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).value = 'N'; ");
				out.println("  } ");
				
				//out.println("  alert(document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).value);  ");
				
				out.println("} ");
				
				out.println("function check_next()");
				out.println("{ ");
				out.println("alert('check');");
				out.println("} ");
				
				out.println("function check_previous()");
				out.println("{ ");
				
				out.println("} ");
				
				
				// added by udara 09-05-2019
				out.println("function check_all_dissaprove(){ "); 
				//out.println(" alert('check_all'); "); 
				out.println(" var row_count = document.Form1.NUM_CHKS.value; "); 
				//out.println(" alert(row_count); ");
					
				out.println("   if(document.getElementById('CHK_ALL_DISSAP').checked==true){  ");
				//out.println("         alert('checked'); ");
				out.println("         document.getElementById('CHK_ALL').checked=false; ");
				out.println("         for (i = 1; i <= row_count; i++) { ");
				out.println("              document.getElementById('TXT_DISSAPPROVE_TYPE_'+i).checked = true; ");
				out.println("              document.getElementById('TXT_APPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+i).value = 'Y'; ");
				out.println("              document.getElementById(\"TXT_APPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("         }  ");
				out.println("   } "); 
				out.println("   else{ "); 
				//out.println("         alert('uncheck'); ");
				out.println("         for (i = 1; i <= row_count; i++) { ");
				out.println("              document.getElementById('TXT_APPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById('TXT_DISSAPPROVE_TYPE_'+i).checked = false; ");
				out.println("              document.getElementById(\"TXT_APPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("              document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+i).value = 'N'; ");
				out.println("         }  ");
				out.println("   } "); 

				out.println("} "); 
				// end by udara 09-05-2019
				
				
				out.println("function check_select_dissap(row_id)");
				out.println("{ ");
				out.println("  if(document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).checked==true){ ");
				out.println("      document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).value = 'Y'; ");
				out.println("      document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).checked = false; ");
				out.println("      document.getElementById(\"TXT_APPROVE_TYPE_\"+row_id).value = 'N'; ");
				out.println("  } ");
				out.println("  else if(document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).checked==false){ ");
				out.println("      document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).value = 'N'; ");
				out.println("  } ");
				
				//out.println("  alert(document.getElementById(\"TXT_DISSAPPROVE_TYPE_\"+row_id).value);  ");

				out.println("} ");
				
				/*
				out.println("function check_select_dissap()");
				out.println("{ ");
				
				out.println("  } ");
				*/
				
				
				out.println("</SCRIPT>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_main_bulk_print();display_bulk_print()\"> ");// //load_main_bulk_print();display_bulk_print() //()  //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>");
				
				
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">"); // this is for team tab(View Tab)
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_MOVEMENT_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_num' VALUE=\"99\">");	
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
				out.println("<input type=hidden id='hid_pre_row'  name='hid_pre_row' value=\"50\">");
				out.println("<input type=hidden id='hid_pre_row_1'  name='hid_pre_row_1' value=\"50\">");
				out.println("<input type=hidden id='hid_curent_row' name='hid_curent_row' value=\"\">");
				
				
				
				
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 327px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Bulk Letter Print -  </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'><input type=\"button\" class='mainbut'  onClick='before_submit();' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  onClick='test_load_bulk_print();' value=\"Help\"></td>");   //onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onclick='clear_window()' value=\"Cancel\"></td>");   // onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'   onclick='close_window()' value=\"Close\"></td>");  //onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				/*
				out.println("<TABLE CELLPADDING=\"2\" > ");
				out.println("<TR> ");
				out.println("<TD id=\"change\"> ");
				out.println("	<INPUT TYPE=\"BUTTON\"  class='but_input' NAME=\"button_bulk_print\" style='width:240px; font-weight:bold' ALIGN=\"CENTER\" SIZE=\"25\"  VALUE=\"Bulk Print\" onclick='display_bulk_print()' > ");
				out.println("</TD>");
				out.println("<TD id=\"change1\"> ");
				out.println("   <INPUT TYPE=\"BUTTON\" class='but_input' NAME=\"button_view_bulk_print\" style='width:240px'  ALIGN=\"CENTER\"  VALUE=\"View Print Report\"  onclick=\"display_view_bulk_print()\">");
				out.println("</TD>");
				out.println("</TR> ");
				out.println("</TABLE> ");
				*/
				
				
				out.println("<div id=\"Main_bulk_print\" >");
				out.println("</div>");
				out.println("<div id=\"View_bulk_print\" >");
				out.println("</div>");
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
				
			}
			
			else if(m_chksql.equals("Bulk_print_letter_generation"))
			{
				
				stmt1=conn.createStatement();
				stmt=conn.createStatement();
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assignState('G4'),makeRequest1(document.Form1.TXT_CLIENT_CODE)\">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" ... \" onClick=\"help_client()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 		
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style='{width:350}' maxlength='15' size='15' disabled >"); 
				out.println("</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 	
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" ... \" onClick=\"help_finance_no()\">"); 
				out.println("</td>"); 
				out.println("</tr>"); 
				
				// added by udara 24-04-2019
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch Code</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='15' size='15' onblur=\"\" >"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_LOCATION_CODE' value=\" ... \" onClick=\"help_update_branch();\" >"); 
				out.println("</td>"); 
				out.println("</tr>"); 
				// end by udara 24-04-2019
				
				out.println("<tr style='display:none'>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_ROW'  class=div_input>Row No</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ROW' maxlength='15' value='50' size='15' onblur=\"assignState('G6')\">"); 
				out.println("</td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>"); 
				out.println("<td width='40%' >");
				
				rs=stmt.executeQuery("SELECT LETTER_ID,LETTER_DESCRIPTION FROM "+m_schema_name+".AF_CO_LETTER_CATEGORY_NEW ORDER BY ORDER_ID ");
				
				out.println("    <SELECT class='txt_input' name='TXT_LETETR_CATEGORY' id='TXT_LETETR_CATEGORY' onchange=\"load_letter_category();\" >  ");
				while(rs.next())
				{
					out.println("<OPTION value='"+rs.getString(1)+"'  >");
					out.println(rs.getString(2));
					out.println("</OPTION>");
				}	
				out.println("    </SELECT> "); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>"); 
				out.println("</tr>"); 
				
				// added by udara 25-04-2019
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRINTER'  class=div_input>Printer</DIV></td>"); 
				out.println("<td width='40%' >");

				rs = stmt.executeQuery(	"  SELECT "+
					"  PRINTER,PRINTER_PATH "+					
					"  FROM "+m_schema_name+".REF_USER_PRINTER "+
					"  WHERE USER_NAME='"+m_username+"' and sel_type='Y' ");
				
				out.println("    <SELECT class='txt_input' name='TXT_PRINTER' id='TXT_PRINTER'  style=\"width:180px;\" >  ");
				while(rs.next())
				{					
					out.println("<OPTION value=\""+rs.getString(2)+"\">"+rs.getString(1)+"</option>");					
				}	
				out.println("    </SELECT> "); 
				
				out.println("</tr>"); 
				// end by udara 25-04-2019
				
				out.println("<tr >"); 
				out.println("<td colspan='3' >");
				out.println("  <div id='Leter_div'> </div> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				
				out.println("</table>"); 
				out.println("<br>"); 
				
				

				
			}
			
			
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
			
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
