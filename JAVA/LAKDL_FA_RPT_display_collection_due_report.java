import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_FA_RPT_display_collection_due_report extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_header_name=m_sn_methods.header_name.trim();
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			ServletOutputStream out = res.getOutputStream(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			String m_screen_type=req.getParameter("chksql");
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_report_date=req.getParameter("REPORT_DATE");
			String m_coll_officer=req.getParameter("COLL_OFFICER");
			String m_coll_mode=req.getParameter("COLL_MODE");
			String m_debtor=req.getParameter("DEBTER");//ADD BY MALIK ON 5-9-2008
			
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_order_by="1";	
			String m_sort_by="ASC";
			
			if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
				m_order_by=req.getParameter("order_by");
				m_sort_by=req.getParameter("sort_by");
			}
			
			// Added by Udara on 03-11-2010
			if(req.getParameter("order_by")==null){ 
				m_order_by = "DEBTOR_CODE,INVOICE_DATE";
				//m_sort_by  = "DESC";
			}						
			
			if (m_report_date.equals("")){
				m_report_date="";
			}
			if (m_client_code.equals("")){
				m_client_code="";
			}
			if (m_coll_officer.equals("")){
				m_coll_officer="";
			}
			if (m_coll_mode.equals("")){
				m_coll_mode="";
			}
			//add by malik on 8-9-2008
			if (m_debtor.equals("")){
				m_debtor="";
			}
			//end
			
			if(m_screen_type.equals("MAIN")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Process - Invoice Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_inv_collection_Report_Follow_up?chksql=main_page&INV_SEQ_NO='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=500,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report - \"+m_val;"); 
				out.println("}");
				
				out.println("function load_roll_out_value(){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report  \";"); 
				out.println("}");
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_order_by+"'){");
				out.println("	   if('"+m_sort_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_collection_due_report?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&CLIENT_CODE="+m_client_code+"&REPORT_DATE="+m_report_date+"&COLL_OFFICER="+m_coll_officer+"&COLL_MODE="+m_coll_mode+"&DEBTER="+m_debtor+"\";");
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>");
				
				// Commented by Udara on 13-10-2010
				/*
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Collection Due Report as at "+m_report_date+"</td></tr>"); 
				out.println("</table>");  
				out.println("</BR>");
				out.println("</BR>");
				*/
				
				
				// Commented by Udara SOmathilake on 13-10-2010
				/*
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Facility No' onclick=sort_data('1') >FACILITY NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Batch No' onclick=sort_data('5') >BATCH NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='10%' class='txt_report_column' >DEBTOR OTHER DETAILS</td>"); 
				out.println("<td width='10%' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Net Invoice Amount'    onclick=sort_data('12')>NET INVOICE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('13')>SETTLE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Date'    onclick=sort_data('15') >INVOICE DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due Date'    onclick=sort_data('16') >DUE DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Tolerance Date'    onclick=sort_data('17') >TOLERANCE-END DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' >POD STATUS</td>"); 
				//--------------------------------ADDED BY ASHINI--------------------------------------
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Receipt Amount'  onclick=sort_data('25')>PENDING RECEIPT AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Bank Realization Amount'  onclick=sort_data('26')>PENDING BANK REALIZATION AMOUNT</td>");
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Officer'    onclick=sort_data('22') >COLLECTIO OFFICER</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Area'    onclick=sort_data('21') >COLLECTION AREA</td>"); 
				out.println("<td width='10%' class='txt_report_column' >FOLLOW UP</td>"); 
				out.println("</tr >"); 
				
				if(m_coll_mode.equals("CA")){
				rs=stmt.executeQuery(" SELECT "+
					" A.FACILITY_NO, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
					" A.BATCH_NO, "+//5
					" B.DEBTOR_CODE, "+//6
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
					" B.INVOICE_NO,  "+//9
					" B.INVOICE_SEQ_NO, "+//10
					" B.INVOICE_AMOUNT,  "+//11
					" B.NET_INVOICE_AMOUNT, "+//12
					" B.SETTLE_AMOUNT, "+//13
					" B.BALANCE_AMOUNT, "+//14
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
					" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
					" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
					" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
					" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0 "+
					" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
					//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
					" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
					" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else if(m_coll_mode.equals("CO")){
				rs=stmt.executeQuery(" SELECT "+
					" A.FACILITY_NO, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
					" A.BATCH_NO, "+//5
					" B.DEBTOR_CODE, "+//6
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
					" B.INVOICE_NO,  "+//9
					" B.INVOICE_SEQ_NO, "+//10
					" B.INVOICE_AMOUNT,  "+//11
					" B.NET_INVOICE_AMOUNT, "+//12
					" B.SETTLE_AMOUNT, "+//13
					" B.BALANCE_AMOUNT, "+//14
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
					" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
					" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
					" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
					" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0 "+
					" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
					//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND C.FACILITY_NO=A.FACILITY_NO "+
					" AND C.CLIENT_CODE=A.CLIENT_CODE "+
					" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
					" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
					" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
					" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}     
				else{
				rs=stmt.executeQuery(" SELECT "+
					" A.FACILITY_NO, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
					" A.BATCH_NO, "+//5
					" B.DEBTOR_CODE, "+//6
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
					" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
					" B.INVOICE_NO,  "+//9
					" B.INVOICE_SEQ_NO, "+//10
					" B.INVOICE_AMOUNT,  "+//11
					" B.NET_INVOICE_AMOUNT, "+//12
					" B.SETTLE_AMOUNT, "+//13
					" B.BALANCE_AMOUNT, "+//14
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
					" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
					" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
					" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
					" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
					" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0 "+
					" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
					//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND C.FACILITY_NO=A.FACILITY_NO "+
					" AND C.CLIENT_CODE=A.CLIENT_CODE "+
					" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
					" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
					" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
					" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				
				int j=0;
				
				boolean more=rs.next();
				while(more){
				
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
						out.println("<tr bgcolor='#FF0066'>");
						}
						else{
						out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
						out.println("<tr class=tr_input1>");
						}
						else{
						out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs.getString(1)+"')>"+rs.getString(1)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_batch_details('"+rs.getString(5)+"')>"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(12))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(15)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(16)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(17)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
						//--------------------------------ADDED BY ASHINI--------------------------------------
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(25))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(26))+"</TD>");
					
					if(rs.getDouble(24)<0){
					out.println("<TD class='txt_report_data' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
					out.println("<TD class='txt_report_data' align='center'>DUE TODAY</TD>");
					}
					else{
					out.println("<TD class='txt_report_data' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(22)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(21)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_followup('"+rs.getString(10)+"')><u>Follow up<u></TD>");
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				} 
				out.println("</table>");
				*/
				
				
				// Added by Udara on 13-10-2010
				
				//TABLE FREEZE			
				out.println("<DIV STYLE='{position:absolute; top:50; left:0 cursor: hand;}'>");
				out.println("<br>");
				out.println("<table align='center' width='2400' class='table' border=1 cellspacing=0  bordercolor='black'>"); 
				
				// Add sql & values
				
				if(m_coll_mode.equals("CA")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else if(m_coll_mode.equals("CO")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}     
				else{
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}




				int j=0;
				
				boolean more=rs.next();
				while(more){
					
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
							out.println("<tr bgcolor='#FF0066'>");
						}
						else{
							out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
							out.println("<tr class=tr_input1>");
						}
						else{
							out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD class='txt_report_data' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs.getString(1)+"')>"+rs.getString(1)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_batch_details('"+rs.getString(5)+"')>"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' width='350' align='left'>"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(12))+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left'>"+rs.getString(15)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left'>"+rs.getString(16)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left'>"+rs.getString(17)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
					//--------------------------------ADDED BY ASHINI--------------------------------------
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(25))+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='right'>"+nf.format(rs.getDouble(26))+"</TD>");
					
					if(rs.getDouble(24)<0){
						out.println("<TD class='txt_report_data' width='100' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
						out.println("<TD class='txt_report_data' width='100' align='center'>DUE TODAY</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' width='100' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					out.println("<TD class='txt_report_data' width='100' align='left'>"+rs.getString(22)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left'>"+rs.getString(21)+"</TD>");
					out.println("<TD class='txt_report_data' width='100' align='left' style= cursor:hand;cursor-color:blue onclick=show_followup('"+rs.getString(10)+"')><u>Follow up<u></TD>");
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				} 
				
				
				out.println("</table>"); 
				out.println("</DIV>");
				
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
				out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 2400; height: 30'>");
				
				// New Addition
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Collection Due Report as at "+m_report_date+"</td></tr>"); 
				out.println("</table>"); 
				
				out.println("<table align='center' width='2400' class='table' border=1 cellspacing=0 bordercolor='black' >"); //2000
				
				// Add Titles
				
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Facility No' onclick=sort_data('1') >FACILITY NO</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Batch No' onclick=sort_data('5') >BATCH NO</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='350' class='txt_report_column' >DEBTOR OTHER DETAILS</td>"); 
				out.println("<td width='100' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Net Invoice Amount'    onclick=sort_data('12')>NET INVOICE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('13')>SETTLE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Date'    onclick=sort_data('15') >INVOICE DATE</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due Date'    onclick=sort_data('16') >DUE DATE</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Tolerance Date'    onclick=sort_data('17') >TOLERANCE-END DATE</td>"); 
				out.println("<td width='100' class='txt_report_column' >POD STATUS</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Receipt Amount'  onclick=sort_data('25')>PENDING RECEIPT AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Bank Realization Amount'  onclick=sort_data('26')>PENDING BANK REALIZATION AMOUNT</td>");
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Officer'    onclick=sort_data('22') >COLLECTIO OFFICER</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Area'    onclick=sort_data('21') >COLLECTION AREA</td>"); 
				out.println("<td width='100' class='txt_report_column' >FOLLOW UP</td>"); 
				out.println("</tr >"); 
				
				out.println("</table>");  
				out.println("</div>"); 
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
				//END TABLE FREEZE
				
				
				
				// End Adding by Udara on 13-10-2010
				
				
				
				
				
				
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr><td width='100%' class='note'></td></tr>"); 
				out.println("</table>"); 
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				rs.close();
				conn.close();
				out.flush();
				out.close();
			}
			if(m_screen_type.equals("MAIN_PRINT")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Process - Invoice Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report - \"+m_val;"); 
				out.println("}");
				
				out.println("function load_roll_out_value(){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report  \";"); 
				out.println("}");
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_order_by+"'){");
				out.println("	   if('"+m_sort_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_collection_due_report?chksql=MAIN_PRINT&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&CLIENT_CODE="+m_client_code+"&REPORT_DATE="+m_report_date+"&COLL_OFFICER="+m_coll_officer+"&COLL_MODE="+m_coll_mode+"&DEBTER="+m_debtor+"\";");
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Collection Due Report as at "+m_report_date+"</td></tr>"); 
				out.println("</table>");  
				out.println("</BR>");
				out.println("</BR>");
				
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Batch No' onclick=sort_data('5') >BATCH NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='10%' class='txt_report_column' >DEBTOR OTHER DETAILS</td>"); 
				out.println("<td width='10%' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('13')>SETTLE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Date'    onclick=sort_data('15') >INVOICE DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due Date'    onclick=sort_data('16') >DUE DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Tolerance Date'    onclick=sort_data('17') >TOLERANCE-END DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' >POD STATUS</td>"); 
				//--------------------------------ADDED BY ASHINI--------------------------------------
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Receipt Amount'  onclick=sort_data('25')>PENDING RECEIPT AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Bank Realization Amount'  onclick=sort_data('26')>PENDING BANK REALIZATION AMOUNT</td>");
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				out.println("</tr >"); 
				
				if(m_coll_mode.equals("CA")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else if(m_coll_mode.equals("CO")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}     
				else{
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				
				int j=0;
				
				boolean more=rs.next();
				while(more){
					
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
							out.println("<tr bgcolor='#FF0066'>");
						}
						else{
							out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
							out.println("<tr class=tr_input1>");
						}
						else{
							out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_batch_details('"+rs.getString(5)+"')>"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(15)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(16)+"</TD>");
					out.println("<TD class='txt_report_data' align='left'>"+rs.getString(17)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
					//--------------------------------ADDED BY ASHINI--------------------------------------
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(25))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(26))+"</TD>");
					
					if(rs.getDouble(24)<0){
						out.println("<TD class='txt_report_data' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
						out.println("<TD class='txt_report_data' align='center'>DUE TODAY</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				} 
				out.println("</table>");
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr><td width='100%' class='note'></td></tr>"); 
				out.println("</table>"); 
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				rs.close();
				conn.close();
				out.flush();
				out.close();
			}
			else if(m_screen_type.equals("MAIN_SUMM")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Process - Invoice Collection Report -Summarize</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_inv_collection_Report_Follow_up?chksql=main_page&INV_SEQ_NO='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=500,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report - \"+m_val;"); 
				out.println("}");
				
				out.println("function load_roll_out_value(){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report  \";"); 
				out.println("}");
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_order_by+"'){");
				out.println("	   if('"+m_sort_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_collection_due_report?chksql=MAIN_SUMM&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&CLIENT_CODE="+m_client_code+"&REPORT_DATE="+m_report_date+"&COLL_OFFICER="+m_coll_officer+"&COLL_MODE="+m_coll_mode+"&DEBTER="+m_debtor+"\";");
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function drill_down_sub(m_debtor) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_5&DEBTOR_CODE=\"+m_debtor;");	
				out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='900'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Collection Due Report as at "+m_report_date+" - Summarize</td></tr>"); 
				out.println("</table>");  
				out.println("</BR>");
				out.println("</BR>");
				
				
				if(m_coll_mode.equals("CA")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" NVL("+m_schema_name+".FA_DEBTOR_CLIENT_REMARKS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE),'-') "+//27
						" ,NVL(B.INVOICE_COMMENTS,'-')  "+//28
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else if(m_coll_mode.equals("CO")){
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" NVL("+m_schema_name+".FA_DEBTOR_CLIENT_REMARKS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE),'-') "+//27
						" ,NVL(B.INVOICE_COMMENTS,'-')  "+//28
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}     
				else{
					rs=stmt.executeQuery(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" NVL("+m_schema_name+".FA_DEBTOR_CLIENT_REMARKS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE),'-') "+//27
						" ,NVL(B.INVOICE_COMMENTS,'-')  "+//28
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				
				int j=0;
				boolean more=rs.next();
				
				/*
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>");  
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='10%' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				out.println("<td width='10%' class='txt_report_column' >POD STATUS</td>"); 
				out.println("<td width='10%' class='txt_report_column' >REMARKS</td>"); 
				out.println("<td width='10%' class='txt_report_column' >FOLLOW UP</td>"); 
				out.println("</tr >"); 
				
				while(more){
				
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
						out.println("<tr bgcolor='#FF0066'>");
						}
						else{
						out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
						out.println("<tr class=tr_input1>");
						}
						else{
						out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					if(rs.getDouble(24)<0){
					out.println("<TD class='txt_report_data' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
					out.println("<TD class='txt_report_data' align='center'>DUE TODAY</TD>");
					}
					else{
					out.println("<TD class='txt_report_data' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
					out.println("<TD class='txt_report_data' align='center' style=cursor:hand;cursor-color:blue onclick=drill_down_sub('"+rs.getString(6)+"')>"+rs.getString(28)+"</TD>");//rs.getString(27) //comment ns 01-07-2009
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_followup('"+rs.getString(10)+"')><u>Follow up<u></TD>");
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				} 
				out.println("</table>");
				*/
				
				
				
				//TABLE FREEZE			
				out.println("<DIV STYLE='{position:absolute; top:60; left:0 cursor: hand;}'>");
				out.println("<br>");
				out.println("<table align='center' width='900' class='table' border=1 cellspacing=0  bordercolor='black'>"); 
				while(more){
					
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
							out.println("<tr bgcolor='#FF0066'>");
						}
						else{
							out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
							out.println("<tr class=tr_input1>");
						}
						else{
							out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD width='100' class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					out.println("<TD width='100' class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD width='100' class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					if(rs.getDouble(24)<0){
						out.println("<TD width='100' class='txt_report_data' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
						out.println("<TD width='100' class='txt_report_data' align='center'>DUE TODAY</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
					out.println("<TD width='100' class='txt_report_data' align='center' style=cursor:hand;cursor-color:blue onclick=drill_down_sub('"+rs.getString(6)+"')>"+rs.getString(27)+"</TD>");//rs.getString(28) MOD BY SANDUN ON 22-09-2009---------------//rs.getString(27) //comment ns 01-07-2009
					out.println("<TD width='100' class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_followup('"+rs.getString(10)+"')><u>Follow up<u></TD>");
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				}
				
				out.println("</table>"); 
				out.println("</DIV>");
				
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
				out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 900; height: 30'>");
				
				out.println("<table align='center' width='900' class='table' border=1 cellspacing=0 bordercolor='black' >"); //2000
				out.println("<tr class=pdn_txtpos2>"); 
				
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='100' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='100' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				out.println("<td width='100' class='txt_report_column' >POD STATUS</td>"); 
				out.println("<td width='100' class='txt_report_column' >REMARKS</td>"); 
				out.println("<td width='100' class='txt_report_column' >FOLLOW UP</td>"); 
				out.println("</tr >"); 
				out.println("</table>");  
				out.println("</div>"); 
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
				//END TABLE FREEZE
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr><td width='100%' class='note'></td></tr>"); 
				out.println("</table>"); 
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				rs.close();
				conn.close();
				out.flush();
				out.close();
				
				
			}
			
			
			
			
			
			// Added by Udara Somathilake on 15-10-2010
			else if(m_screen_type.equals("MAIN_V_2")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Process - Invoice Collection Report -Summarize</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_inv_collection_Report_Follow_up?chksql=main_page&INV_SEQ_NO='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=500,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report - \"+m_val;"); 
				out.println("}");
				
				out.println("function load_roll_out_value(){"); 
				out.println("help_box.innerHTML=\"Operation Process - Invoice Collection Report  \";"); 
				out.println("}");
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_order_by+"'){");
				out.println("	   if('"+m_sort_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_collection_due_report?chksql=MAIN_V_2&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&CLIENT_CODE="+m_client_code+"&REPORT_DATE="+m_report_date+"&COLL_OFFICER="+m_coll_officer+"&COLL_MODE="+m_coll_mode+"&DEBTER="+m_debtor+"\";");
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function drill_down_sub(m_debtor) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_5&DEBTOR_CODE=\"+m_debtor;");	
				out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='900'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Collection Due Report as at "+m_report_date+" - Summarize</td></tr>"); 
				out.println("</table>");  
				out.println("</BR>");
				out.println("</BR>");
				
	if(m_order_by.equals("7")){ //thamali 2012.01.30
				if(m_coll_mode.equals("CA")){
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" , B.INVOICE_DATE ");
				}
				else if(m_coll_mode.equals("CO")){
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" , B.INVOICE_DATE ");
				}     
				else{
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" , B.INVOICE_DATE ");
				}
	}		
	else{ // order by columns other than debtor name
					if(m_coll_mode.equals("CA")){
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else if(m_coll_mode.equals("CO")){
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.0' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}     
				else{
					rs=stmt.executeQuery(" SELECT "+
					//out.println(" SELECT "+
						" A.FACILITY_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(A.CLIENT_CODE), "+//4
						" A.BATCH_NO, "+//5
						" B.DEBTOR_CODE, "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//7
						" "+m_schema_name+".FA_GET_CLIENT_OTHER_INFO(B.DEBTOR_CODE), "+//8
						" B.INVOICE_NO,  "+//9
						" B.INVOICE_SEQ_NO, "+//10
						" B.INVOICE_AMOUNT,  "+//11
						" B.NET_INVOICE_AMOUNT, "+//12
						" B.SETTLE_AMOUNT, "+//13
						" B.BALANCE_AMOUNT, "+//14
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//15
						" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//16
						" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','C'), "+//18
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'C','D'), "+//19
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','C'), "+//20
						" "+m_schema_name+".FA_GET_CLIENT_LOCATION(B.DEBTOR_CODE,'A','D'), "+//21
						" "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(B.DEBTOR_CODE), "+//22
						" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO), "+//23
						" B.TOLARENCE_END_DATE-TO_DATE('"+m_report_date+"','DD-MM-YYYY'), "+//24
						" "+m_schema_name+".FA_GET_PENDING_RECEIPT_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO), "+ //25 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" "+m_schema_name+".FA_GET_PENDING_REALIZ_RECE_AMT(B.INVOICE_NO,A.CLIENT_CODE,B.DEBTOR_CODE,A.FACILITY_NO) "+ //26 ADDED BY ASHINI ON 03-10-2007 FOR SR0710-006
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD C"+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
						//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND C.FACILITY_NO=A.FACILITY_NO "+
						" AND C.CLIENT_CODE=A.CLIENT_CODE "+
						" AND C.PRODUCT_FEATURE_CODE='PF-06.0.1' "+
						" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
						" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
						" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}

	}
				int j=0;
				boolean more=rs.next();
				
				
				
				//TABLE FREEZE			
				out.println("<DIV STYLE='{position:absolute; top:60; left:0 ; width : 1250px; cursor: hand;}'>");
				out.println("<br>");
				out.println("<table align='center' width='1250px' class='table' border=1 cellspacing=0  bordercolor='black'>"); 
				while(more){
					
					if(rs.getDouble(24)<=0){
						if(j>0 && j%2==1){
							out.println("<tr bgcolor='#FF0066'>");
						}
						else{
							out.println("<tr bgcolor='#FF0066'>");
						}
					}
					else{
						if(j>0 && j%2==1){
							out.println("<tr class=tr_input1>");
						}
						else{
							out.println("<tr class=tr_input>");
						}
					}
					
					out.println("<TD class='txt_report_data' width='50'  align='left' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs.getString(1)+"')>"+rs.getString(1)+"</TD>");
					out.println("<TD class='txt_report_data' width='50'  align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
					//out.println("<TD class='txt_report_data' width='50' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_batch_details('"+rs.getString(5)+"')>"+rs.getString(5)+"</TD>");  //thamali 2012.01.27
					out.println("<TD class='txt_report_data' width='50' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' width='200' align='left'>"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(12))+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='left'>"+rs.getString(15)+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='left'>"+rs.getString(16)+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='left'>"+rs.getString(17)+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
					//--------------------------------ADDED BY ASHINI--------------------------------------
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(25))+"</TD>");
					out.println("<TD class='txt_report_data' width='50' align='right'>"+nf.format(rs.getDouble(26))+"</TD>");
					
					if(rs.getDouble(24)<0){
						out.println("<TD class='txt_report_data' width='50' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
					}
					else if(rs.getDouble(24)==0){
						out.println("<TD class='txt_report_data' width='50' align='center'>DUE TODAY</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' width='50' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
					}
					//out.println("<TD class='txt_report_data' width='50' align='left'>"+rs.getString(22)+"</TD>");  //thamali 2012.01.27
					//out.println("<TD class='txt_report_data' width='50' align='left'>"+rs.getString(21)+"</TD>");  //thamali 2012.01.27
					out.println("<TD class='txt_report_data' width='50' align='left' style= cursor:hand;cursor-color:blue onclick=show_followup('"+rs.getString(10)+"')><u>Follow up<u></TD>");
					out.println("</tr >"); 
					j=j+1;
					more=rs.next(); 
				} 
				
				out.println("</table>"); 
				out.println("</DIV>");
				
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
				out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1250px; height: 30'>");
				
				out.println("<table align='center' width='1250px' class='table' border=1 cellspacing=0 bordercolor='black' >"); //2000
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Facility No' onclick=sort_data('1') >FACILITY NO</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
				//out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Batch No' onclick=sort_data('5') >BATCH NO</td>");  //thamali 2012.01.27
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
				out.println("<td width='200' class='txt_report_column' >DEBTOR OTHER DETAILS</td>"); 
				out.println("<td width='50' class='txt_report_column' >INVOICE NO</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Amount'    onclick=sort_data('11')>INVOICE AMOUNT</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Net Invoice Amount'    onclick=sort_data('12')>NET INVOICE AMOUNT</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('13')>SETTLE AMOUNT</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice Date'    onclick=sort_data('15') >INVOICE DATE</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due Date'    onclick=sort_data('16') >DUE DATE</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Tolerance Date'    onclick=sort_data('17') >TOLERANCE-END DATE</td>"); 
				out.println("<td width='50' class='txt_report_column' >POD STATUS</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Receipt Amount'  onclick=sort_data('25')>PENDING RECEIPT AMOUNT</td>"); 
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Bank Realization Amount'  onclick=sort_data('26')>PENDING BANK REALIZATION AMOUNT</td>");
				out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
				//out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Officer'    onclick=sort_data('22') >COLLECTION OFFICER</td>");  //thamali 2012.01.27
				//out.println("<td width='50' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Collection Area'    onclick=sort_data('21') >COLLECTION AREA</td>");  //thamali 2012.01.27
				out.println("<td width='50' class='txt_report_column' >FOLLOW UP</td>"); 
				out.println("</tr >"); 
				out.println("</table>");  
				out.println("</div>"); 
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
				//END TABLE FREEZE
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr><td width='100%' class='note'></td></tr>"); 
				out.println("</table>"); 
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				rs.close();
				conn.close();
				out.flush();
				out.close();
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		catch (Exception e) { 
			try { 
				
			}	 
			catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
		}
	}
}


