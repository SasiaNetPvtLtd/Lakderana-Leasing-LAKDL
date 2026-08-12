/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//=====CREATED BY DINETH MEEMANAGE
//=====ON 2008-09-20
//=====FOR OFSCL FACTORING COLLECTION REPORT

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
    
public class LAKDL_FA_RPT_display_collection_due_report_improved extends javax.servlet.http.HttpServlet { 

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
			
			// String debtor_code_string = "";
							
			if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			m_order_by=req.getParameter("order_by");
			m_sort_by=req.getParameter("sort_by");
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
			
			out.println("function print_data(){");
			out.println(" m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	   out.println("m_writedata+'</table>';");
			out.println("}");

			
			out.println("</script>"); 
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
				" ,TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.DUE_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+//edit by malik on 5-9-2008
				//" AND B.TOLARENCE_END_DATE<=TO_DATE('"+m_report_date+"','DD-MM-YYYY') "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND A.CLIENT_CODE LIKE '"+m_client_code+"%' "+
				" AND B.DEBTOR_CODE LIKE '"+m_debtor+"%' "+ //ADD BY MALIK ON 8-9-2008
				" AND "+m_schema_name+".FA_GET_CLIENT_COLL_OFFICER(A.CLIENT_CODE) LIKE '"+m_coll_officer+"%' "+
				//" ORDER BY "+m_order_by+" "+m_sort_by+" "); // commented by udara on 18-10-2010
				" ORDER BY B.DEBTOR_CODE DESC "); // Added by Udara on 18-10-2010
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
				" ,TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
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
				" ,TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
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
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"add_button()\">"); 
			out.println("<FORM NAME='Form1' method='post'>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			int j=0;

			boolean more=rs.next();
			if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
			}	
			
			
			// Added by Udara Somathilake on 18-10-2010
			if (!m_client_code.equals("")){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<td align='left' style='height: 18px' ><b>Client Code</b> :"+m_client_code+"</td></tr>"); 
						out.println("</TABLE>");
			}
			
			// End by Udara
			
			
			
			
			while(more){
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>");
         out.println("<tr>");
			out.println("<td align='left' style='height: 18px' ><b>Client Name</b> :"+rs.getString(3)+"</td></tr>"); // commented by udara somathilake on 18-10-2010

			
			out.println("<tr>");
			out.println("<td align='left' style='height: 18px' ><b>Debtor Name</b> :"+rs.getString(7)+"</td></tr>"); 

			out.println("<tr>");
			//out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' >Invoice Collection Due Report as at "+m_report_date+"</td></tr>"); 
			out.println("<td align='left'  style='height: 18px' ><b>Outstanding Invoices as at "+rs.getString(27)+"</b></td></tr>"); 

			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			
			out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client' onclick=sort_data('3') >CLIENT NAME</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Batch No' onclick=sort_data('5') >BATCH NO</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Debtor' onclick=sort_data('7') >DEBTOR NAME </td>"); 
			//out.println("<td width='10%' class='txt_report_column' >DEBTOR OTHER DETAILS</td>"); 
			out.println("<td width='10%' class='txt_report_column' >Inv no</td>"); 
			//out.println("<td width='10%' class='txt_report_column' >Inv Amt</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('13')>SETTLE AMOUNT</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('14')>BALANCE AMOUNT</td>"); 
			out.println("<td width='10%' class='txt_report_column' >Inv Date</td>");
			out.println("<td width='10%' class='txt_report_column' >Due Date</td>");
			out.println("<td width='10%' class='txt_report_column' >Inv Amt</td>");
			out.println("<td width='10%' class='txt_report_column' >Settled Amt</td>"); 
			out.println("<td width='10%' class='txt_report_column' >Balance Amt</td>"); 
			out.println("<td width='10%' class='txt_report_column' >P.D.Chqs</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due Date'    onclick=sort_data('16') >DUE DATE</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Tolerance Date'    onclick=sort_data('17') >TOLERANCE-END DATE</td>"); 
			//out.println("<td width='10%' class='txt_report_column' >POD STATUS</td>"); 
			//--------------------------------ADDED BY ASHINI--------------------------------------
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Pending Receipt Amount'  onclick=sort_data('25')>PENDING RECEIPT AMOUNT</td>"); 
			out.println("<td width='10%' class='txt_report_column' >Pending bank Realization</td>");
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Due in days'    onclick=sort_data('24') >DUE IN(DAYS)</td>"); 
			out.println("</tr >"); 
			
			
			
			
			String m_client_code_1=rs.getString(2); 
			String debtor_code_string = rs.getString(6); 
						
			double Total_1=0;
			double Total_2=0;
			double Total_3=0;
			double Total_4=0;
			double Total_5=0;
			
			//while(m_client_code_1.equals(rs.getString(2))){ // commented by udara
			while(debtor_code_string.equals(rs.getString(6))){
			
				if(rs.getDouble(24)<=0){
					if(j>0 && j%2==1){
					//out.println("<tr bgcolor='#FF0066'>");
					out.println("<tr bgcolor='#FFFFFF'>");
					}
					else{
					//out.println("<tr bgcolor='#FF0066'>");
					out.println("<tr bgcolor='#C1C1C1'>");
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

				//out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"')>"+rs.getString(3)+"</TD>");
				//out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_batch_details('"+rs.getString(5)+"')>"+rs.getString(5)+"</TD>");
				//out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(6)+"')>"+rs.getString(7)+"</TD>");
				//out.println("<TD class='txt_report_data' align='left'>"+rs.getString(8)+"</TD>");
				out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_details_ref_no('"+rs.getString(10)+"')>"+rs.getString(9)+"</TD>");
				//out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
				//out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
				//out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(15)+"</TD>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(16)+"</TD>");
				out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
				out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
				out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
				out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");

				//out.println("<TD class='txt_report_data' align='left'>"+rs.getString(16)+"</TD>");
				//out.println("<TD class='txt_report_data' align='left'>"+rs.getString(17)+"</TD>");
				//out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(23))+"</TD>");
				 //--------------------------------ADDED BY ASHINI--------------------------------------
				//out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(25))+"</TD>");
				out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(26))+"</TD>");
				
				//if(rs.getDouble(24)<0){
				//out.println("<TD class='txt_report_data' align='center'>DUE FOR LAST "+(rs.getDouble(24)*-1)+"</TD>");
				//}
				//else if(rs.getDouble(24)==0){
				//out.println("<TD class='txt_report_data' align='center'>DUE TODAY</TD>");
				//}
				//else{
				//out.println("<TD class='txt_report_data' align='center'>DUE IN NEXT "+rs.getString(24)+"</TD>");
				//}
				out.println("</tr >"); 
				
				
				j=j+1;
				Total_1+=rs.getDouble(11);
				Total_2+=rs.getDouble(13);
				Total_3+=rs.getDouble(14);
				Total_4+=rs.getDouble(23);
				Total_5+=rs.getDouble(26);
				more=rs.next(); 
				if(!more){
							break;
							}
				}
				
				//TOTAL ROW GOES HERE ---------------------------------------
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' colspan='3' align='left' ><b>Total</b></TD>");
				out.println("<TD class='txt_report_data'             align='right'><b>"+nf.format(Total_1)+"</b></TD>");
				out.println("<TD class='txt_report_data'             align='right'><b>"+nf.format(Total_2)+"</b></TD>");
				out.println("<TD class='txt_report_data'             align='right'><b>"+nf.format(Total_3)+"</b></TD>");
				out.println("<TD class='txt_report_data'             align='right'><b>"+nf.format(Total_4)+"</b></TD>");
				out.println("<TD class='txt_report_data'             align='right'><b>"+nf.format(Total_5)+"</b></TD>");

            out.println("</tr >"); 
				//-----------------------------------------------------------
				out.println("</table>");
				out.println("   <p style=\"page-break-after:always\"></p>");	
			} 
			

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



