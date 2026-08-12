// DEVELOP BY : NISHANTHA FOR LAKDL LEASING    DATE:19-11-2015
// THIS FILE RELATED TO LAKDL_AF_RE_receipt_report_new_gui_loc FILE

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_receipt_report_new_gui_loc_active extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2); 
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement(); 
			
			String m_username 	= m_sn_methods.username;

			
			
			
			 if(m_chksql.equals("main_page_active_loc"))
			 {
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				//m_branch_id = ""; 
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code"); 
				
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				
				m_location_code = mm_location_code = "ALL";
				
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND A.ENT_USER = UPPER('"+m_user+"') ";
					m_user_query2 = "AND REC_ENT_BY = UPPER('"+m_user+"') ";
				}
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					
					//location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN_3(A.ENT_USER,A.REC_NO) = '"+m_branch_id+"' ";
					location_query_part = " AND  A.REC_LOC = '"+m_branch_id+"' "; // added by udara 24-07-2018

				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				
				String m_branch_name_2="";
				
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-'),"+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_branch_id+"'),'-')"+ // added by udara on 28-08-2013
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
					m_branch_name_2 = rs1.getString(4);
				}
				
				
				double m_tot_rec=0;
				
				/*added by ns on 12/11/2012*/
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+  //"  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5						
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+ //added by kanishka on 03-07-2013  //14						
						//" ,DECODE(NVL(CLOSING_FLAG,'N'),'Y','Closing','S','Stamp Duty','I','Documentation Charges','R','Refinance',DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental')) N_TYPE "+ // added by udara 14-07-2015
						
						" ,CASE "+
						" 	WHEN " + m_schema_name + ".AF_CO_IS_INITIAL_RECIEPT_C(A.CLIENT_CODE) ='Y' AND SUS_REF_NO IS NULL THEN 'Client Receipt' "+
						" 	WHEN " + m_schema_name + ".AF_CO_IS_INITIAL_RECIEPT(A.SUS_REF_NO) ='Y' THEN 'Initial Receipt' "+
						
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'Y')='Y' THEN 'Closing' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='I' THEN 'Documentation Charges' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges'  "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='R' THEN 'Refinance'  "+
						
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND A.INSURANCE >0 THEN 'Rental / Insurance' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'N')='N' THEN 'Rental'  "+
						
						"   WHEN A.INSURANCE >0  THEN 'Insurance' "+
						" END N_TYPE "+ //2017-09-25
						
						" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO))),'-') REG_NO "+ // 19 added by udara 11-08-2014
						" ,SUBSTR(SUB_REC_NO,1,INSTR(SUB_REC_NO,'/')-1)||LPAD(SUBSTR(SUB_REC_NO,INSTR(SUB_REC_NO, '/')+1), 5, '0') SUB_REC_SORT "+ // added by udara 24-02-2015
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" "+ m_user_query + " "+ // added by udara on 25-07-2013

						" "+ location_query_part + " "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "); // " ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else{
					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5						
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO  FIN_NO, "+ // " NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14						
						" ,DECODE(NVL(CLOSING_FLAG,'N'),'Y','Closing','S','Stamp Duty','I','Documentation Charges','R','Refinance',DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental')) N_TYPE "+ // added by udara 14-07-2015
						
						" ,CASE "+
						" 	WHEN " + m_schema_name + ".AF_CO_IS_INITIAL_RECIEPT_C(A.CLIENT_CODE) ='Y' AND SUS_REF_NO IS NULL THEN 'Client Receipt' "+
						" 	WHEN " + m_schema_name + ".AF_CO_IS_INITIAL_RECIEPT(A.SUS_REF_NO) ='Y' THEN 'Initial Receipt' "+
						
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'Y')='Y' THEN 'Closing' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='I' THEN 'Documentation Charges' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges'  "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'I')='R' THEN 'Refinance'  "+
						
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND A.INSURANCE >0 THEN 'Rental / Insurance' "+
						"   WHEN A.RENTAL_OTER_INVOICE >0 AND NVL(A.CLOSING_FLAG,'N')='N' THEN 'Rental'  "+
						
						"   WHEN A.INSURANCE >0  THEN 'Insurance' "+
						" END N_TYPE "+ //2017-09-25
						
						" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(C.FINANCE_NO)),'-') REG_NO "+ // 19 added by udara 11-08-2014
						" ,SUBSTR(SUB_REC_NO,1,INSTR(SUB_REC_NO,'/')-1)||LPAD(SUBSTR(SUB_REC_NO,INSTR(SUB_REC_NO, '/')+1), 5, '0') SUB_REC_SORT "+ // added by udara 24-02-2015
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + 						
						" "+ location_query_part + " "+ 
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
					
				}
				
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE>");
			
				// added by udara 09-05-2014
				out.println(" <style>");
				out.println(" html {");
				//out.println(" background: url(\""+m_html_client_url+"/vehicle_inspection/doc_1.jpg\") no-repeat center center fixed;");
				out.println(" background: url(\""+m_html_client_url+"/vehicle_inspection/5.jpg\");");
				out.println(" -webkit-background-size: cover;");
				out.println(" -moz-background-size: cover;");
				out.println(" -o-background-size: cover;");
				out.println(" background-size: cover;");

				
				out.println(" }");
				out.println(" </style> ");
				// end by udara 09-05-2014
			    out.println("</HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_location_code='"+mm_location_code+"';"); // added by udara on 03-12-2012
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 	
				out.println("	 m_order_by_type = 'ASC'; ");  
				
				out.println(" if(m_sort_col=='ENT_DATE'){"); //added by kanchana on 2016-07-28
				out.println(" m_sort_col=\"TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')\"; ");
				out.println("    }");//endded by kanchana on 2016-07-28
				
				out.println(" if(m_sort_col=='EFF_VALDATE'){"); //added by kanchana on 2016-07-28
				out.println(" m_sort_col=\"TO_date(EFF_VALDATE,'DD-MM-YYYY')\"; ");
				out.println("    }");//endded by kanchana on 2016-07-28
				
				out.println("	 if(m_sort_col==\""+m_order_by+"\"){");
				out.println("	   if('"+m_sort_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");				
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui_loc_active?chksql=main_page_active_loc&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&user=&location_code=\"+m_location_code+\"&branch_id=\"+m_branch_id;"); // added by udara on 27-02-2013				
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");
				
				// added by udara on 26-08-2013
				out.println("	function show_deposit_slip(m_deposit_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Deposit_Slip?chksql=print_deposit_slip&deposit_no=\"+m_deposit_no;");
				out.println("    window.open(m_url); ");
				out.println("	}");
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				
				out.println("<TABLE  WIDTH='100%'  align='Center'>");
				out.println("<TR><TD align='Center' ><B>LAKDERANA INVESTMENTS LIMITED.</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%'  >");
				out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Location : "+m_branch_name_2+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					
					
					out.println("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width='100%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				
					out.println("<tr class=pdn_txtpos3>"); // udara 12-08-2014
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%'><b>Receipt No</b></td>"); 
					out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_SORT') ><b>Sub Receipt No</b></td>"); // SUB_REC_NO
					out.println("<td width='5%'  style= cursor:hand; onclick=sort_data('SETTLE_MODE') ><b>Settle Mode</b></td>"); // mod by udara on 29-08-2013
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='6%' style= cursor:hand; onclick=sort_data('REG_NO') ><b>Reg No.</b></td>"); // added by udara 11-08-2014
					out.println("<td width='8%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
					out.println("<td width='1%'> &nbsp; </td>"); 
					out.println("<td width='8%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
					out.println("<td width='5%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
					out.println("<td width='3%'  style= cursor:hand; onclick=sort_data('A.STATUS') ><b>Status</b></td>");  // out.println("<td width='3%'  style= cursor:hand; onclick=sort_data('STATUS') ><b>Status</b></td>"); 					
					out.println("<td width='1%' >&nbsp;</td>"); // added by udara 27-05-2024
					out.println("<td width='10%'  ><b>Remarks</b></td>"); // added by udara 20-05-2024
					out.println("</tr>"); 
					
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td >"+i+"</td>"); //width='1%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
					out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
					out.println("<td  class=div_input ><p>"+rs1.getString(19)+"</p></td>"); // added by udara 11-08-2014
					out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
					out.println("<td  class=div_input > &nbsp; </td>"); // udara 19-08-2014
					out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'					
					out.println("<td class=div_input >"+rs1.getString(18)+"</td>"); //added by udara 27-02-2014
					
					
					out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
					out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
					out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
					//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("<td  class=div_input >&nbsp; </td>"); // added by udara 27-05-2024
					out.println("<td class=div_input >"+rs1.getString(10)+"</td>"); // added by udara 20-05-2024
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input ></td>"); // udara 11-08-2014
				out.println("<td  class=div_input align='right' >___________</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>"); // udara 19-08-2014
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara 27-05-2024
				out.println("<td  class=div_input ></td>"); // added by udara 20-05-2024
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input ></td>"); // udara 11-08-2014
				out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>"); // udara 19-08-2014
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara 27-05-2024
				out.println("<td  class=div_input ></td>"); // added by udara 20-05-2024
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				
				// added by udara on 28-08-2013
				
				
				// end by udara on 28-08-2013
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				
				//if (m_location_code.trim().equals("ALL")) {  // added by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" "+ location_query_part + " "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod  by udara on 25-07-2013 for performance						
						" "+ m_user_query + " "+						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}	
				else{
					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" "+ location_query_part + " "+ 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+  m_user_query + 						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}	
				
				out.println("<p style=\"page-break-after:always\"></p>");
				
				//out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A "+  // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+
						" "+ location_query_part + " "+ 						
						" "  +m_user_query + " "+
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
				} else {
					
				
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+
						" "+ location_query_part + " "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + 
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
					
				}
				
				
				
				
				double m_transfer_account_count = 0;
				double m_transfer_account_amt = 0;
				
				while(rs1.next()){
					m_transfer_account_count = rs1.getDouble(3);
					m_transfer_account_amt = rs1.getDouble(2);
					
				}
				
				//out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				
				m_rec_count=0;
				m_rec_amt=0;
			
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT ENT_USER ENT_USER,SETTLE_MODE TYPE,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" "+ location_query_part + " "+ 						
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
				} else{
					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" "+
					//out.println(" "+
						" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT A.ENT_USER ENT_USER,A.SETTLE_MODE TYPE,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+ location_query_part + " "+ 						
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
					
					
				}
				
				
				
				//out.println("<table align='center' width='50%' class='table' border='1'>");
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				
				
				
				// added by udara on 25-07-2013
				if(mm_location_code.trim().equals("ALL")){
					
					rs1= stmt1.executeQuery(" SELECT ENT_USER ,SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+m_user_query +						
						" "+location_query_part+"  "+ 
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
					
				}
				else{
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER ,A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				// end by udara on 25-07-2013
				
				//out.println("<table align='center' width='50%' class='table' border='1'>");
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				
				m_transfer_account_count =0;
				m_transfer_account_amt   =0;
				while(rs1.next()){
					
					m_transfer_account_count=m_transfer_account_count+rs1.getDouble(4);
					m_transfer_account_amt=m_transfer_account_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND STATUS = 'B' "+m_user_query +						
						" "+location_query_part+"  "+ 						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+" "+  						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}
				
				
				// added by udara on 05-08-2013 ===================================================================================
				//out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B>B/B/F</B></U></td>");
				out.println("</tr>");
				
				double tot_count = 0;
				double total_bbf = 0;
				
				double total_collections = 0;
				double total_banked = 0;
				
				// BBF
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" "+
						//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + // " AND STATUS NOT IN ('C','B') "+m_user_query + // //commented by kanishka on 27-09-2013
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+ // comented by udara on 24-09-2013
						
						 // added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						// added by udara 17-12-2013
						" UNION ALL  "+ 
						
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
						" WHERE A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND   A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND   A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND   A.STATUS IN ('C','CAD') "+ 									
						"  "+m_user_query + 
						" "+location_query_part+"  "+
						// end by udara 17-12-2013						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" "+
						//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+ // commented by udara on 24-09-2013
						
						// added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						
						
						// added by udara 17-12-2013
						"  UNION ALL "+	
						
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						" AND A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND   A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND   A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						
						
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+
						// end by udara 17-12-2013						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				
				while(rs1.next()){
					
					tot_count = tot_count + rs1.getDouble(3);
					total_bbf = total_bbf + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				//CANCELLED RECEIPTS [BEFORE REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				double total_bbf_cancelled = 0;
				int cancelled_bbf_count = 0;
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT) FROM ( "+
				
						
						// added by udara 17-12-2013
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
						" WHERE A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND   A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND   A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						" AND A.REC_NO NOT IN ( "+
									" SELECT REC_NO  "+
									" 	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
									" 	WHERE EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+	
									" 	AND   EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" 	AND   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
									" 	AND   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" ) "+
									
						" AND A.REC_NO NOT IN ( "+
									" SELECT REC_NO  "+
									" 	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
									" 	WHERE   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" ) "+			
						
						
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" "+location_query_part+"  "+
						// end by udara 17-12-2013						
						" ) "+
						"");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT) FROM ( "+
						
						
						
						// added by udara 17-12-2013
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO   "+ 
						" AND   A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND   A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND   A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						" AND A.REC_NO NOT IN ( "+
									" SELECT REC_NO  "+
									" 	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
									" 	WHERE EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+	
									" 	AND   EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" 	AND   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
									" 	AND   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" ) "+
									
						" AND A.REC_NO NOT IN ( "+
									" SELECT REC_NO  "+
									" 	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
									" 	WHERE   TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									" ) "+			
									
						
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 
						// end by udara 17-12-2013
						
						" ) "+
						" ");
					
				}
				
				while(rs1.next()){
					
					total_bbf_cancelled = total_bbf_cancelled + rs1.getDouble(1);
					cancelled_bbf_count = cancelled_bbf_count +  rs1.getInt(2);
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >CANCELLED </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(2))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(1))+"</b></td>");
					out.println("</tr>");
				}
				
				total_bbf	= 	total_bbf -  total_bbf_cancelled;
				tot_count   = 	tot_count - cancelled_bbf_count;
				//END CANCELLED RECEIPTS [BEFORE REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL B/B/F</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(tot_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_bbf)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> COLLECTIONS </B></U></td>");
				out.println("</tr>");
				
				// collections
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+						
						"  "+m_user_query + 
						" "+location_query_part+"  "+ 						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 						
						"  "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					
					total_collections = total_collections + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				
				//CANCELLED RECEIPTS [ REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				double total_cancelled = 0;
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					
					
					//added by udara 12-12-2013
					rs1= stmt1.executeQuery(" "+
						" SELECT SUM(REC_AMOUNT),COUNT(REC_NO)FROM ( "+
						
						" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+  //"  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+ //added by kanishka on 03-07-2013  //14						
						" ,DECODE(NVL(CLOSING_FLAG,'N'),'Y','Closing','S','Stamp Duty','I','Documentation Charges','R','Refinance',DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental')) N_TYPE "+ // added by udara 14-07-2015
						" ,SUBSTR(SUB_REC_NO,1,INSTR(SUB_REC_NO,'/')-1)||LPAD(SUBSTR(SUB_REC_NO,INSTR(SUB_REC_NO, '/')+1), 5, '0') SUB_REC_SORT "+ // added by udara 24-02-2015
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  "+
						" EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+	//COMMENTED BY KANISHKA ON 03-09-2013
							" AND EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+	//COMMENTED BY KANISHKA ON 03-09-2013
						" AND TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013						
						" "+ m_user_query + " "+ // added by udara on 25-07-2013						
						"  AND STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013						
						" "+ location_query_part + " "+
						" ) ");					
					
				}
				else {
					
					
					// added by udara 12-12-2013
					rs1= stmt1.executeQuery(" "+
						" SELECT SUM(REC_AMOUNT),COUNT(REC_NO)FROM ( "+
						" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO  FIN_NO, "+ // " NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14						
						" ,DECODE(NVL(CLOSING_FLAG,'N'),'Y','Closing','S','Stamp Duty','I','Documentation Charges','R','Refinance',DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental')) N_TYPE "+ // added by udara 14-07-2015
						" ,SUBSTR(SUB_REC_NO,1,INSTR(SUB_REC_NO,'/')-1)||LPAD(SUBSTR(SUB_REC_NO,INSTR(SUB_REC_NO, '/')+1), 5, '0') SUB_REC_SORT "+ // added by udara 24-02-2015
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ //commented by kanishka on 03-09-2013
						" AND A.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ //commented by kanishka on 03-09-2013
						" AND TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + 
						" AND A.STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013
						" "+ location_query_part + " "+ 
						" ) ");
					
				}
				
				while(rs1.next()){
					
					total_cancelled = total_cancelled + rs1.getDouble(1);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >CANCELLED </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(2))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(1))+"</b></td>");
					out.println("</tr>");
				}
				
				total_collections	= 	total_collections -  total_cancelled;
				//END CANCELLED RECEIPTS [ REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL COLLECTIONS</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b> &nbsp; </b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_collections)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				//CANCELLED RECEIPTS -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				
				
				
				// banked
				
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" "+
						// commented by udara on 18-09-2013
						
						
						// added by udara on 18-09-2013
						
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND A.REC_NO = B.RECEIPT_NO  "+
						" AND B.STATUS = 'Y' "+ 
						" AND A.STATUS = 'B' "+m_user_query +
						" "+location_query_part+"  "+ 						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					
					rs1= stmt1.executeQuery(" "+
					//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						
						// commented by udara on 20-09-2013
						
						
						
						// added by udara on 20-09-2013
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS D"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.REC_NO = D.RECEIPT_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						
						" AND  TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND D.STATUS = 'Y' "+ 

						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+" "+ 
						// end by udara on 20-09-2013						
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}
				
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> BANKED </B></U></td>");
				out.println("</tr>");
				
				while(rs1.next()){
					
					
					total_banked = total_banked +rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>BANKED TOTAL </B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_banked)+"</b></td>");
				out.println("</tr>");
				
				
				// cash in hand added by udara on 14-08-2013
				
				double cash_in_hand = 0;
				cash_in_hand = (total_bbf + total_collections) - total_banked;
				//cash_in_hand = (total_bbf + total_collections) - (total_banked + total_cancelled);//cash in hand modified by kanishka dilshan on 20-09-2013
				
				
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>CASH IN HAND</B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				//out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format((total_bbf + total_collections) - total_banked)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(cash_in_hand)+"</b></td>");
				out.println("</tr>");
				
				
				
				out.println("</table>"); 
				
				out.println("<br><br><br>");
				
				// end by udara on 05-08-2013 ===================================================================================
				
				
				//out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>5000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>2000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>1000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>500.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>100.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>50.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>20.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>10.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Coins</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input ><b>TOTAL</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>PETTY CASH FLOAT</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Excess/Shortage</b></td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				// added by udara on 08-08-2013
				if(!m_branch_id.equals("")){
					
					//out.println("<table align='center' width='50%' class='table' border='0'>");
					out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
					
					out.println("<tr>");
					out.println("<td width='20%' class=div_input ><B> Deposit Numbers </B></td>");
					out.println("<td width='15%' class=div_input align=right ><B> Amount </B></td>");
					out.println("<td width='15%' class=div_input > &nbsp; </td>");
					out.println("</tr>");
					
					
					
					// added by udara on 26-08-2013
					rs1= stmt1.executeQuery(" "+
						" SELECT DISTINCT B.DIPOSIT_NO, SUM(B.AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
						" WHERE A.REC_NO = B.RECEIPT_NO "+ 
						" AND A.STATUS = 'B' "+						
						//" AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN_3(A.ENT_USER,A.REC_NO) = '"+m_branch_id+"' "+  // added by udara 02-12-2013
						"   AND  A.REC_LOC = '"+m_branch_id+"' "+ // added by udara 24-07-2018
						" AND B.STATUS = 'Y' "+ // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ // added by udara on 18-09-2013
						
						" AND A.SETTLE_MODE NOT IN ('DIR_DEP') "+ 
						" GROUP BY B.DIPOSIT_NO "+
						" ");
					
					while(rs1.next()){
						
						out.println("<tr>");
						out.println("<td width='20%' class=div_input style=\"cursor:hand\" onclick=\"show_deposit_slip('"+rs1.getString(1)+"')\" ><u> "+rs1.getString(1)+" </u></td>");
						out.println("<td width='15%' class=div_input align=right > "+nf.format(rs1.getDouble(2))+" </td>");
						out.println("<td width='15%' class=div_input > &nbsp;</td>");
						out.println("</tr>");
						
					}
					
					
					out.println("</table>");
					
					out.println("<br><br><br>"); 
				}
				// end by udara on 08-08-2013
				
				
				//out.println("<table align='center' width='50%' class='table' border='0'>");
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'>.......................</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Cashier</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'><b>Date</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				//out.println("<table align='center' width='50%' class='table' border='0'>");	
				out.println("<table align='center' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width='50%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Checked by</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				// added by udara on 29-08-2013
				
				//out.println("test uv 1"); 
				
				
				if(!m_branch_id.equals("")){
					
					//out.println("test uv 2"); 
					
					rs1= stmt1.executeQuery("  "+
						" SELECT "+
						" A.REC_NO, "+ //1
						" A.REC_AMOUNT, "+ //2
						" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER,  "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+ //5
						" A.CLIENT_CODE, "+ //6
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ //7
						" NVL(A.SUS_REF_NO,'-'), "+ //8
						" A.SETTLE_MODE, "+ //9
						" NVL(A.OTH_COMMENTS,'-') , "+ //10
						" B.FINANCE_NO  FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						" SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+						
						" ,DECODE(NVL(CLOSING_FLAG,'N'),'Y','Closing','S','Stamp Duty','I','Documentation Charges','R','Refinance',DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental')) N_TYPE "+ // added by udara 14-07-2015
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //18 added by udara 13-03-2014
						" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(C.FINANCE_NO)),'-') REG_NO "+ // udara 12-08-2014
						" ,SUBSTR(SUB_REC_NO,1,INSTR(SUB_REC_NO,'/')-1)||LPAD(SUBSTR(SUB_REC_NO,INSTR(SUB_REC_NO, '/')+1), 5, '0') SUB_REC_SORT "+ // added by udara 24-02-2015
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND A.EFF_VALDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_branch_id+"' "+
						//" AND A.STATUS NOT IN ('C','CAD')  "+
						" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) <> '"+m_branch_id+"' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "+
						" ");
					
					//out.println("test uv 3"); 
					
					
					more = rs1.next();
					
					double m_tot_rec_nn = 0;
					
					//out.println("test uv 4"); 
					
					out.println("<br><br><br>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
					out.println("<TR><TD align='Center' ><B> Receipts of Application Location  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
					if(!more){
						
						//out.println("test uv 5"); 
						
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more){
						
						//out.println("test uv 6"); 
						
						//out.println("<table width='100%' class='table' >");	
						out.println("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width='100%' background=\""+m_html_client_url+"/vehicle_inspection/5.jpg\"  >");
						//out.println("<tr class=pdn_txtpos2>");
						out.println("<tr class=pdn_txtpos3>"); // udara 12-08-2014
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%'><b>Receipt No</b></td>"); 
						out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_SORT') ><b>Sub Receipt No</b></td>"); // SUB_REC_NO
						out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('REG_NO') ><b>Reg No.</b></td>"); // udara 12-08-2014
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
						out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
						out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
						out.println("<td width='3%' ><b>Status</b></td>"); 
						out.println("<td width='1%' >&nbsp;</td>"); // added by udara 27-05-2024
						out.println("<td width='10%' ><b>Remarks</b></td>"); // added by udara 20-05-2024
						out.println("</tr>"); 
						
					}
					
					//out.println("test uv 7"); 
					
					int i3=1;
					while(more){
						
						//out.println("test uv 8"); 
						
						if(mflag){
							
							//out.println("test uv 9"); 
							
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							
							//out.println("test uv 10"); 
							
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td >"+i3+"</td>"); //width='1%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(18)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
						out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
						out.println("<td  class=div_input ><p>"+rs1.getString(19)+"</p></td>"); // udara 12-08-2014
						out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
						out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'												
						out.println("<td class=div_input >"+rs1.getString(17)+"</td>"); //added by udara 27-02-2014
						
						out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
						out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
						out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
						//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
						out.println("<td  class=div_input >&nbsp; </td>"); // added by udara 27-05-2024
						out.println("<td class=div_input >"+rs1.getString(10)+"</td>"); // added by udara 20-05-2024 
						out.println("</tr>");
						m_tot_rec_nn=m_tot_rec_nn+rs1.getDouble(2);
						i3++;
						more = rs1.next();
						
						//out.println("test uv 12"); 
						
						
					}	
					
					//out.println("test uv 13"); 
					
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input ></td>"); // udara 12-08-2014
					out.println("<td  class=div_input align='right' >___________</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara 27-05-2024
					out.println("<td  class=div_input ></td>"); // added by udara 20-05-2024
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input ></td>"); // udara 12-08-2014
					out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_nn)+"</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara 27-05-2024
					out.println("<td  class=div_input ></td>"); // added by udara 20-05-2024
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br><br>"); 
				}
				
				// end by udara on 29-08-2013
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
			if(out!=null){
				try{out.close();  
				}catch(Exception e){}
			}
		}
	}
}