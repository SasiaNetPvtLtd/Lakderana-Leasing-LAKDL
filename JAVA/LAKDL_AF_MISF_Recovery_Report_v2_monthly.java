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
import java.math.*;  


public class LAKDL_AF_MISF_Recovery_Report_v2_monthly extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a= null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs_drill_new=null;
		
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
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";


			
			if(m_chksql.equals("print_report_new")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_user_name=""; // added by udara 18-11-2013
				String m_sys_date=""; // added by udara 18-11-2013
				String m_cr_off_name=""; // added by udara 18-11-2013
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";  //Added by CJ
				String  m_active_status_string = ""; //Added by CJ
				
				String m_active_status_string_for_cr_notes = ""; // added by udara 18-02-2016
				
				String m_region=""; // Added By: Samith Dilshan on 2015-06-02
				
				String m_finance_no="";
				String m_last_run_date="";
				
				BigDecimal total_early_sett_next_month_new_adjustment = new BigDecimal(0.00); //Added by SDF 
				
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
				}
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
					//m_active_status_string_for_cr_notes = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 18-02-2016
					m_active_status_string = " AND A.YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
					m_active_status_string_for_cr_notes = " AND YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
				}
				
				
				// Added By Samith Dilshan on 2015-06-12
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				if(req.getParameter("finance_no")!=null ){
					m_finance_no=req.getParameter("finance_no").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				// added by udara 26-04-2017
				String m_finance_string = " ";
				String m_officer_string = " ";
				String m_location_string = " ";
				String m_perform_status_string = " ";
				String m_cr_officer_string_sql = " ";
				
				if(!m_finance_no.equals("")){
					m_finance_string = " AND A.FINANCE_NO = '"+m_finance_no+"' ";
				}
				
				if(!m_officer.equals("")){
					m_officer_string = " AND UPPER(A.collection_officer)  = UPPER('"+m_officer+"') ";
				}
				
				if(!m_location.equals("")){
					m_location_string = " AND UPPER(A.LOCATION_CODE)      =  UPPER('"+m_location+"') ";
				}
				
				if(!m_perform_status.equals("")){
					m_perform_status_string = " AND A.PERFORM_STATUS      =  '"+m_perform_status+"' ";
				}
				
				if(!m_cr_officer.equals("")){
					m_cr_officer_string_sql = " AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) = UPPER('"+m_cr_officer+"') ";
				}
				
				
				// end by udara 26-04-2017
				
				// added by udara 15-10-2018
				String m_product_name=req.getParameter("product_name"); 
				// end by udara 15-10-2018
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Recovery Report - Automated - Daily</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_drill(val){ "); 
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"&perform_status="+m_perform_status+"&region="+m_region+"\";");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"&perform_status="+m_perform_status+"&region="+m_region+"&product_name="+m_product_name+"\";");
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara 19-10-2016
				out.println("function show_drill_total(){ "); 
				//out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill_total&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&cr_officer="+m_cr_officer+"\";");	// commented by udara 23-08-2017
				//out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill_total&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&cr_officer="+m_cr_officer+"&perform_status="+m_perform_status+"&region="+m_region+"\";"); // added by udara 23-08-2017
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new_drill_total&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&cr_officer="+m_cr_officer+"&perform_status="+m_perform_status+"&region="+m_region+"&product_name="+m_product_name+"\";");
				out.println("   window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				// end by udara 19-10-2016
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;"); 
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				// added by udara 14-11-2013
				out.println("function print_cr_note_drill(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report_auto?chksql=print_report_new&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				// end by udara 14-11-2013
				
				// added by udara 07-04-2014
				out.println("function print_cr_note_drill_rental(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report_auto?chksql=print_report_new_cr_rental&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_arrears(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report_auto?chksql=print_report_new_cr_arrears&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_other_curr(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report_auto?chksql=print_report_new_cr_other_curr&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_other_prev(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report_auto?chksql=print_report_new_cr_other_prev&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				// end by udara 07-04-2014
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				//Added by Dineth on 2008-11-17
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+ // added by udara 18-11-2013
					" TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI PM'), "+ // added by udara 18-11-2013
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_cr_officer+"'),'-'), "+ // added by udara 18-11-2013
					" (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH:MI:SS AM') FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY)   "+ // added by udara 19-05-2016
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
					m_user_name = rs.getString(5); // added by udara 18-11-2013
					m_sys_date = rs.getString(6); // added by udara 18-11-2013
					m_cr_off_name = rs.getString(7); // added by udara 18-11-2013
					m_last_run_date = rs.getString(8); // added by udara 19-05-2016
				}
				
				String Sql_data="";
				
				// commented by udara 10-11-2015
				
				// ================================================ main section start ========================================================
				
				
				// added by udara 10-11-2015
				Sql_data="  "+
					" SELECT "+
					
					" TOTAL_PERIOD, "+
					" COUNT(*) COUNT, "+
					" SUM(TOTAL_ARREARS), "+
					" SUM(RENTAL_AMOUNT), "+ 
					" SUM(SETTLED_AMOUNT_CUR_MON_RENTAL), "+
					" SUM(SETTLED_AMOUNT_CUR_MON_ARREARS), "+
					" SUM(SETTLED_AMOUNT_CUR_MON), "+
					" SUM(TOTAL_AMOUNT), "+
					" SUM(CLOSING_BALANCE), "+
					" SUM(SETTLED_AMOUNT), "+
					" SUM(ADJUSTED_AMOUNT), "+
					" SUM(FUTURE_RENTAL), "+
					" SUM(EARLY_SETTLE), "+
					" SUM(EARLY_SETTLE_NEXT_MONTH) , "+
					" SUM(SETTL_AMOUNT_CUR_MON_RENTAL_N), "+
					" SUM(SETTLED_OTH_FROM_EXCESS), "+
					" SUM(NEW_CLOSING), "+
					" SUM(NEW_FUTURE_RENTAL), "+
					" SUM(RENTAL_AMOUNT_NEW), "+
					" SUM(NEW_CASH_TOTAL), "+
					" SUM(CR_NOTE_1), "+
					" SUM(CR_NOTE_2)  "+
					
					" FROM ( "+
					
					
					
					
					" SELECT NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
					" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+
					" A.RENTAL_AMOUNT RENTAL_AMOUNT, "+
					" A.SETTLED_AMOUNT_CUR_MON_RENTAL SETTLED_AMOUNT_CUR_MON_RENTAL, "+
					" A.SETTLED_AMOUNT_CUR_MON_ARREARS SETTLED_AMOUNT_CUR_MON_ARREARS, "+
					" A.SETTLED_AMOUNT_CUR_MON SETTLED_AMOUNT_CUR_MON, "+
					" NVL (A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
					" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
					" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
					" NVL (A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT, "+
					" NVL(A.FUTURE_RENTAL,0) FUTURE_RENTAL, "+
					" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
					" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
					" NVL ( DECODE( SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - (A.RENTAL_AMOUNT- DECODE( SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) ) )), 1 , (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), SETTLED_AMOUNT_CUR_MON_RENTAL )  ,0) SETTL_AMOUNT_CUR_MON_RENTAL_N, "+
					" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) ) NEW_CLOSING, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) NEW_FUTURE_RENTAL, "+
					
					" ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) RENTAL_AMOUNT_NEW , "+
					
					" ( "+
					" CASE "+
					" WHEN ( ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) <= 0 ) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) >= 0 ) ) "+
					" THEN ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) ) "+
					" ELSE ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) + ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) "+
					" END ) NEW_CASH_TOTAL, "+
					
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_1 , "+
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_2 "+
					" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					//" WHERE A.ENT_USER='LAKDLALL' "+
					" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+ 
					//" AND A.FINANCE_NO LIKE '"+m_finance_no+"%'   "+ // commented by udara 27-04-2017
					//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+ // commented by udara 27-04-2017
					//" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ // commented by udara 27-04-2017 
					" AND A.TOTAL_PERIOD > 0 "+
					//" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+ // commented by udara 27-04-2017
					//" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%' "+  // commented by udara 27-04-2017
					//" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "; // commented by udara 27-04-2017
					
					// added by udara 27-04-2017
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "; 
				Sql_data = Sql_data + m_finance_string + m_officer_string + m_location_string + m_perform_status_string + m_cr_officer_string_sql;									
				// end by udara 27-04-2017
				
				if(!(m_region.equals("NOT_SELECT"))){                 
					Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
				}
				
				// added by udara 15-10-2018
				if(!(m_product_name.equals("NOT_SELECT"))){                 
					Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 15-10-2018
				
				Sql_data = Sql_data + "    "+m_active_status_string+"   "+
					" AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					
					
					" ) "+    
					
					" GROUP BY TOTAL_PERIOD "+
					" ORDER BY TOTAL_PERIOD ";
				
				// end by udara 10-11-2015
				
				
				//out.println(Sql_data);
				
				out.println("<!--"+Sql_data+"-->");
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				stmt3 = conn.createStatement ();
				
				// added by udara 18-11-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Officer :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Credit Officer :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cr_off_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Date :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_sys_date+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				// added by udara 17-08-2015
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(m_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(m_perform_status.equals("NPERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Non Perform</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active/Yard Vehicles :- </td>"); 
				
				if(m_active_status.equals("A"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_active_status.equals("Y"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active</td>");
				else if(m_active_status.equals("N"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Yard Vehicles</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				// end by udara 17-08-2015
				
				
				
				
				// added by udara 19-05-2016
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Last updated Date/Time </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_last_run_date+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				// end by udara 19-05-2016
				
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr >");
						out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Region :- </td>"); 
						out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width='*%' > &nbsp; </td>"); 
						out.println("</tr >");
					}
				}
				
				out.println("</table >");
				out.println("<br>");
				
				// end by udara 18-11-2013
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT - AUTOMATED - DAILY</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				out.println("<br>");
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				//	out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>No. of Cases</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"10\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modifeid by udara on 02-05-2013 colspan=\"8\" to colspan=\"9\" 
				out.println("<td width=\"5%\"  align='center'ROWSPAN='2'  ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Rental Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Overall Collection %</b></td>"); // added by udara 19-02-2015
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;cursor:pointer;}' onclick= \"show_drill('"+rs.getString(1)+"')\" ><u>"+nf1.format(rs.getDouble(2))+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					if(early_sett_next_month.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett_next_month.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett_next_month)+"</td>");
					}
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					// end by udara on 02-05-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(21)); // added by udara 26-11-2013
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					// added by udara 19-02-2015
					BigDecimal overall_collection = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection = (tot_rent.add(Arrea_Collec)).multiply(new BigDecimal(100)).divide(totalab,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection = new BigDecimal(0.00);
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(overall_collection)+"%</td>"); 
					// end by udara 19-02-2015
					
					closing = new BigDecimal(0.00);
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					BCF = new BigDecimal(0.00);
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				
				out.println("<tr>");		
				out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
				//out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); // commented by udara 19-10-2016
				out.println("<td class=factoring-letter-body  STYLE='{text-align:right;cursor:pointer;}'  onclick= 'show_drill_total()' ><b><u>Total</u></b></td>"); // added by udara 19-10-2016
				if(total_Cases.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
				}
				
				
				if(total_Arrears.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
				}
				
				
				
				
				
				if(total_Due_Rental.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
				}
				
				
				if(total_AB.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
				}
				
				
				if(total_Ear_Sett.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
				}
				
				if(total_early_sett_next_month.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_early_sett_next_month.negate())+")</b></td>");  
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_early_sett_next_month)+"</b></td>");  
				}
				
				
				// added by udara on 02-05-2013
				if(total_Other_charges_coll_from_excess.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
				}
				// end by udara on 02-05-2013
				
				if(total_Rental_colle.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
				}
				
				
				if(total_Rent.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
				}
				
				
				if(total_Rental_Pres.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
				}
				
				
				if(total_Arresrs_colle.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
				}
				
				
				if(total_Arrears.signum()>0){
					total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
				}else if(total_Settle.signum()>0){
					
					total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
				}
				
				if(total_Arrears_Pres.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
				}
				
				
				BigDecimal overall_collection_total = new BigDecimal(0.00);
				//if(totalab.signum()>0){
				if(total_AB.signum()>0){ // mod by udara 21-04-2015
					overall_collection_total = (total_Rent.add(total_Arresrs_colle)).multiply(new BigDecimal(100)).divide(total_AB,BigDecimal.ROUND_HALF_EVEN);
				}
				else{
					overall_collection_total = new BigDecimal(0.00);
				}
				
				out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(overall_collection_total)+"%</b></td>");  // out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(0)+"%</b></td>");  
				
				if(total_Future_ren_show.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_show.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_show)+"</b></td>"); 
				}	
				
				
				if(total_Closing.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
				}
				
				if(total_cash.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
				}
				
				if(total_bcf.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
				}
				
				
				
				out.println("</tr>");	
				
				// ============================================== main section end ================================================================
				
				
				
				
				
				
				// ============================================== query 2 section start ===========================================================
				int count_2 = 0;
				
				
				
				// added by udara 10-11-2015
				Sql_data = "  "+
					" SELECT COUNT(FINANCE_NO) FROM ( "+
					" SELECT   "+
					" A.FINANCE_NO FINANCE_NO "+
					" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+ 
					//" WHERE A.ENT_USER='LAKDLALL'   "+
					" WHERE A.FINANCE_NO LIKE '"+m_finance_no+"%'   "+ 
					" AND A.APPLICATION_NO = B.APPLICATION_NO   "+
					//" AND UPPER(A.COLLECTION_OFFICER) like UPPER('%"+m_officer+"%')  "+
					" AND UPPER(A.COLLECTION_OFFICER) like UPPER('"+m_officer+"%')  "+
					" AND UPPER(A.LOCATION_CODE) like UPPER('"+m_location+"%')  "+ 
					" AND A.TOTAL_PERIOD > 0  "+
					" AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM')  "+
					" AND A.ACTIVATED_DATE <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD')  "+
					" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%'   "+
					" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%')  "+
					
					// added by udara 11-11-2015
					" ";
				if(!(m_region.equals("NOT_SELECT"))){                
					Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
				}
				
				// added by udara 15-10-2018
				if(!(m_product_name.equals("NOT_SELECT"))){                 
					Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 15-10-2018
				
				Sql_data = Sql_data + "    "+m_active_status_string+"   "+
					" AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					// end by udara 11-11-2015
					
					
					
					" ) "; 
				
				// end by udara 10-11-2015
				
				rs=stmt.executeQuery(Sql_data);
				
				while(rs.next()){
					count_2 = rs.getInt(1);
				}
				
				// ============================================== query 2 section end ===========================================================
				
				
				
				// ============================================== query 3 section start ===========================================================
				if(count_2>=0){ // added by udara 31-12-2013
					
					
					
					
					// added by udara 10-11-2015
					Sql_data = "  "+
						" SELECT "+
						" TOTAL_PERIOD, "+
						" COUNT(*), "+
						" SUM(TOTAL_ARREARS), "+
						" SUM(RENTAL_AMOUNT), "+
						" SUM(SETTLED_AMOUNT_CUR_MON_RENTAL), "+
						" SUM(SETTLED_AMOUNT_CUR_MON_ARREARS), "+
						" SUM(SETTLED_AMOUNT_CUR_MON), "+
						" SUM(TOTAL_AMOUNT), "+
						" SUM(CLOSING_BALANCE), "+
						" SUM(SETTLED_AMOUNT), "+
						" SUM(ADJUSTED_AMOUNT), "+
						" SUM(FUTURE_RENTAL), "+
						" SUM(EARLY_SETTLE), "+
						" SUM(EARLY_SETTLE_NEXT_MONTH), "+
						" SUM(SETTL_AMOUNT_CUR_MON_RENTAL_N), "+
						" SUM(SETTLED_OTH_FROM_EXCESS), "+
						" SUM(RENTAL_AMOUNT_NEW) "+ // added by udara 03-01-2018
						" FROM ( "+
						
						
						
						" SELECT  "+
						" NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+ 				
						//" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) TOTAL_ARREARS,  "+	// commented by udara 03-01-2018
						" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0)  TOTAL_ARREARS,  "+ // added by udara 03-01-2018	
						" A.RENTAL_AMOUNT RENTAL_AMOUNT,  "+
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL SETTLED_AMOUNT_CUR_MON_RENTAL, "+
						" A.SETTLED_AMOUNT_CUR_MON_ARREARS SETTLED_AMOUNT_CUR_MON_ARREARS,  "+
						" A.SETTLED_AMOUNT_CUR_MON SETTLED_AMOUNT_CUR_MON,    "+
						" NVL (A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+  
						" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE,   "+
						" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+ 
						" NVL (A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT,  "+
						" NVL(A.FUTURE_RENTAL,0) FUTURE_RENTAL,  "+
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE,  "+
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH,  "+
						" NVL (DECODE(SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - (A.RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)))), 1 ,(A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))),A.SETTLED_AMOUNT_CUR_MON_RENTAL),0) SETTL_AMOUNT_CUR_MON_RENTAL_N,  "+
						" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS,   "+ 
						
						// added by udara 03-01-2018
														" ( ( "+
													        " CASE "+
													          " WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
													          " AND ( ( "+
													            " CASE "+
													              " WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
													              " AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
													              " THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
													              " ELSE NVL(A.CLOSING_BALANCE,0) "+
													            " END ) > 0 ) ) "+
													          " THEN 0 "+
													          " ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
													        " END ) ) NEW_FUTURE_RENTAL, "+
													        
													        " ( "+
													        " CASE "+
													          " WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
													          " THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
													          " ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
													        " END ) RENTAL_AMOUNT_NEW "+
															// end by udara 03-01-2018
						
						" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+
						//" WHERE A.ENT_USER='LAKDLALL'  "+
						" WHERE A.FINANCE_NO LIKE '"+m_finance_no+"%'   "+ 
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ 
						//" AND UPPER(A.COLLECTION_OFFICER) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.COLLECTION_OFFICER) like UPPER('"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ 
						" AND A.TOTAL_PERIOD > 0 "+
						" AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%'  "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						"  "+m_active_status_string+"  "+ 
						
						// added by udara 11-11-2015
						" ";
					if(!(m_region.equals("NOT_SELECT"))){                
						Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					}
					
					// added by udara 15-10-2018
					if(!(m_product_name.equals("NOT_SELECT"))){                 
						Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
					}
					// end by udara 15-10-2018
					
					Sql_data = Sql_data + "  "+
						" AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						// end by udara 11-11-2015
						
						" ) "+
						" GROUP BY TOTAL_PERIOD ORDER BY TOTAL_PERIOD ";
					
					
					
					// end by udara 10-11-2015
					
					BigDecimal total_Cases_new      = new BigDecimal(0.00); 
					BigDecimal total_Arrears_new    = new BigDecimal(0.00); 
					BigDecimal total_Due_Rental_new = new BigDecimal(0.00);
					BigDecimal totalab_new          = new BigDecimal(0.00);
					BigDecimal total_AB_new         = new BigDecimal(0.00);
					BigDecimal early_sett_new       = new BigDecimal(0.00);
					BigDecimal total_Ear_Sett_new   = new BigDecimal(0.00);
					BigDecimal early_sett_next_month_new = new BigDecimal(0.00);
					BigDecimal total_early_sett_next_month_new = new BigDecimal(0.00);
					BigDecimal Other_charges_coll_from_excess_new = new BigDecimal(0.00);
					BigDecimal total_Other_charges_coll_from_excess_new = new BigDecimal(0.00);
					BigDecimal Rent_Collec_new  = new BigDecimal(0.00);
					BigDecimal total_Rental_colle_new = new BigDecimal(0.00);
					BigDecimal tot_rent_new = new BigDecimal(0.00);
					BigDecimal total_Rent_new = new BigDecimal(0.00);
					BigDecimal total_Rental_Pres_new = new BigDecimal(0.00);
					BigDecimal Arrea_Collec_new = new BigDecimal(0.00);
					BigDecimal total_Arresrs_colle_new = new BigDecimal(0.00);
					BigDecimal total_Arrears_Pres_new = new BigDecimal(0.00);
					BigDecimal closing_new = new BigDecimal(0.00);
					BigDecimal total_Closing_new = new BigDecimal(0.00);
					BigDecimal total_Future_ren_new = new BigDecimal(0.00);
					BigDecimal total_cash_new = new BigDecimal(0.00);
					BigDecimal total_bcf_new = new BigDecimal(0.00);
					BigDecimal futu_rent_new = new BigDecimal(0.00);
					BigDecimal BCF_new = new BigDecimal(0.00);
					BigDecimal Cash_Total_new = new BigDecimal(0.00);
					
					BigDecimal total_AdjAmount_new = new BigDecimal(0.00); // added by udara on 01-07-2013
					BigDecimal total_Settle_new = new BigDecimal(0.00); // added by udara on 01-07-2013
					
					//out.println(Sql_data);
					
					
					rs=stmt.executeQuery(Sql_data);
					
					while(rs.next()){
						
						total_Settle_new = total_Settle_new.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
						total_AdjAmount_new = total_AdjAmount_new.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
						
						
						total_Cases_new =total_Cases_new.add(rs.getBigDecimal(2));
						
						if(rs.getBigDecimal(3).signum()>=0){   
							total_Arrears_new = total_Arrears_new.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
						}else{
							total_Arrears_new = total_Arrears_new.add(rs.getBigDecimal(11));
						}
						
						total_Due_Rental_new = total_Due_Rental_new.add(rs.getBigDecimal(4));
						
						totalab_new = new BigDecimal(0.00) ;
						if((rs.getBigDecimal(3)).signum()>=0){
							totalab_new = totalab_new.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
						}else{
							totalab_new  = totalab_new.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
						}
						total_AB_new = total_AB_new.add(totalab_new);
						
						early_sett_new = new BigDecimal(0.00) ;
						early_sett_new = early_sett_new.add(rs.getBigDecimal(13));
						total_Ear_Sett_new = total_Ear_Sett_new.add(early_sett_new);
						
						early_sett_next_month_new       = new BigDecimal(0.00);
						early_sett_next_month_new       = early_sett_next_month_new.add(rs.getBigDecimal(14));
						total_early_sett_next_month_new = total_early_sett_next_month_new.add(early_sett_next_month_new);
						
						total_early_sett_next_month_new_adjustment = total_early_sett_next_month_new;
						
						
						Other_charges_coll_from_excess_new       = new BigDecimal(0.00);
						Other_charges_coll_from_excess_new       = Other_charges_coll_from_excess_new.add(rs.getBigDecimal(16)); 
						Other_charges_coll_from_excess_new       = Other_charges_coll_from_excess_new.subtract(Other_charges_coll_from_excess_new);  // added by udara 20-04-2015
						total_Other_charges_coll_from_excess_new = total_Other_charges_coll_from_excess_new.add(Other_charges_coll_from_excess_new);
						
						Rent_Collec_new = new BigDecimal(0.00);
						//Rent_Collec_new = Rent_Collec_new.add(Other_charges_coll_from_excess_new); // commented by udara 03-01-2018
						Rent_Collec_new = Rent_Collec_new.add(rs.getBigDecimal(17));  // added by udara 03-01-2018
						total_Rental_colle_new = total_Rental_colle_new.add(Rent_Collec_new);
						
						
						tot_rent_new   = new BigDecimal(0.00);
						//tot_rent_new   = tot_rent.add(early_sett_new).add(Rent_Collec_new).subtract(Other_charges_coll_from_excess_new); 
						tot_rent_new   = tot_rent_new.add(early_sett_new).add(Rent_Collec_new).subtract(Other_charges_coll_from_excess_new);
						total_Rent_new = total_Rent_new.add(tot_rent_new);
						
						total_Rental_Pres_new  = new BigDecimal(0.00);
						if(total_Due_Rental_new.signum()>0){
							total_Rental_Pres_new= total_Rental_Pres_new.add(total_Rent_new.multiply(new BigDecimal(100)).divide(total_Due_Rental_new,BigDecimal.ROUND_HALF_EVEN));
						}
						
						Arrea_Collec_new  = new BigDecimal(0.00);
						Arrea_Collec_new = Arrea_Collec_new.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));
						total_Arresrs_colle_new = total_Arresrs_colle_new.add(Arrea_Collec_new);
						
						closing_new = new BigDecimal(0.00);
						closing_new = closing_new.add(rs.getBigDecimal(9));
						total_Closing_new =total_Closing_new.add(closing_new);
						
						futu_rent_new = new BigDecimal(0.00);
						futu_rent_new = futu_rent_new.add(rs.getBigDecimal(12).subtract(early_sett_next_month_new));
						total_Future_ren_new = total_Future_ren_new.add(futu_rent_new);
						
						Cash_Total_new = new BigDecimal(0.00);
						Cash_Total_new = Cash_Total_new.add(closing_new).add(Arrea_Collec_new).add(Rent_Collec_new).add(futu_rent_new);
						
						BCF_new = new BigDecimal(0.00);
						BCF_new = BCF_new.add(totalab_new).subtract(Cash_Total_new).subtract(early_sett_new).subtract(early_sett_next_month_new) ; //BCF_new = Cash_Total_new.subtract(total_AB_new); // added by udara on 08-07-2013
						total_cash_new = total_cash_new.add(Cash_Total_new);
						total_bcf_new = total_bcf_new.add(BCF_new);
						
					}
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>New</b></td>"); 
					if(total_Cases_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b><u>("+nf1.format(total_Cases_new.negate())+")</u></b></td>"); 					
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}' STYLE='{text-align:right;cursor:pointer;}' onclick= \"show_drill('NEW')\"  ><b><u>"+nf1.format(total_Cases_new)+"</u></b></td>"); 
					}
					
					if(total_Arrears_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_new)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental_new)+"</b></td>"); 
					}
					
					
					if(total_AB_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB_new.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB_new)+"</b></td>");
					}
					
					
					if(total_Ear_Sett_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_new.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_new)+"</b></td>");  
					}
					
					if(total_early_sett_next_month_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_early_sett_next_month_new.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_early_sett_next_month_new)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess_new)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle_new)+"</b></td>"); 
					}
					
					
					if(total_Rent_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent_new.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent_new)+"</b></td>");
					}
					
					
					if(total_Rental_Pres_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres_new.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres_new)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle_new)+"</b></td>"); 
					}
					
					
					if(total_Arrears_new.signum()>0){
						total_Arrears_Pres_new= total_Arrears_Pres_new.add((total_Arresrs_colle_new).multiply(new BigDecimal(100)).divide(total_Arrears_new.add(total_AdjAmount_new),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle_new.signum()>0){
						
						total_Arrears_Pres_new= total_Arrears_Pres_new.add((total_Arresrs_colle_new).multiply(new BigDecimal(100)).divide(total_AdjAmount_new,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres_new.signum()<0){ // 
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres_new.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres_new)+"%</b></td>"); 
					}
					
					
					if(total_AB_new.signum()>0){ // mod by udara 20-03-2015
						overall_collection_total = (total_Rent_new.add(total_Arresrs_colle_new)).multiply(new BigDecimal(100)).divide(total_AB_new,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}   '><b>"+nf.format(overall_collection_total)+"%</b></td>");  
					
					total_Future_ren_show = total_Future_ren_show.add(total_Future_ren_new); // added by udara on 16-08-2013
					
					if(total_Future_ren_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_new)+"</b></td>"); 
					}
					
					
					if(total_Closing_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing_new)+"</b></td>"); 
					}
					
					
					if(total_cash_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash_new)+"</b></td>"); 
					}
					
					if(total_bcf_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf_new)+"</b></td>"); 
					}
					
					
					out.println("</tr>");	
					
					
				} // added by udara 31-12-2013	
				
				// ============================================== query 3 section end ===========================================================
				
				
				
				// ============================================== query 4 section start ===========================================================
				
				
				
				// added by udara 16-11-2015
				Sql_data="  "+	
					" SELECT  "+
					" SUM(ODI_AMOUNT_COL), "+
					" SUM(INVOICE), "+
					" SUM(RENTAL_ARREARS_NEW), "+
					" SUM(INSURANCE), "+
					" SUM(BBF), "+
					" SUM(OPEN_CONTRACT_BAL) "+
					" FROM ( "+
					
					
					" SELECT NVL(SUM(A.ODI_AMOUNT_COL),0) ODI_AMOUNT_COL, "+
					" NVL(SUM(A.INVOICE),0)+NVL(SUM(A.ADJ_FOR_SUMMARY),0)+NVL(SUM(A.RENTAL_ARREARS),0)-NVL(SUM(A.RENTAL_ARREARS_NEW),0) INVOICE, "+ // invoice 
					" NVL(SUM(A.RENTAL_ARREARS_NEW),0) RENTAL_ARREARS_NEW, "+ // rental arrears
					" NVL(SUM(A.INSURANCE),0) INSURANCE,  "+
					" NVL(SUM(A.PRE_BALANCE),0)+NVL(SUM(A.OTHER_CHARGES_CURR_MONTH),0) BBF, "+ // bbf
					" NVL(SUM(A.OPEN_CONTRACT_BAL),0) OPEN_CONTRACT_BAL "+
					" FROM "+m_schema_name+".AF_RECOV_RPT_SUM_MONTHLY_ALL A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					//" WHERE ENT_USER='"+m_username+"' "+
					//" WHERE ENT_USER='LAKDLALL' "+
					" WHERE  A.FINANCE_NO = B.FINANCE_NO "+
					" AND A.FINANCE_NO LIKE '"+m_finance_no+"%'   "+ 
					//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+ // commented by udara 07-03-2016
					//" AND NVL(UPPER(A.collection_officer),' ') like UPPER('%"+m_officer+"%') "+ // added by udara 07-03-2016
					" AND NVL(UPPER(A.collection_officer),' ') like UPPER('"+m_officer+"%') "+
					" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ 
					" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ 
					" ";
				if(!(m_region.equals("NOT_SELECT"))){                
					Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
				}
				
				// added by udara 15-10-2018
				if(!(m_product_name.equals("NOT_SELECT"))){                 
					Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 15-10-2018
				
				Sql_data = Sql_data + "    "+m_active_status_string+"   "+
					" AND REPORT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY')"+					
					" ) "+			
					
					" ";
				
				// end by udara 16-11-2015						
				
				
				//out.println(Sql_data);
				rs=stmt.executeQuery(Sql_data);
				
				//out.println("uv test 2");
				
				BigDecimal m_panalty = new BigDecimal(0.00);
				BigDecimal m_invoice = new BigDecimal(0.00);
				BigDecimal m_rental = new BigDecimal(0.00);
				BigDecimal m_insurance = new BigDecimal(0.00);
				BigDecimal m_bbf = new BigDecimal(0.00);
				
				BigDecimal m_open_con_bal = new BigDecimal(0.00); // added by udara on 28-05-2013
				
				while(rs.next()){
					m_panalty = rs.getBigDecimal(1);
					m_invoice = rs.getBigDecimal(2);
					m_rental = rs.getBigDecimal(3);
					m_insurance = rs.getBigDecimal(4);
					m_bbf = rs.getBigDecimal(5);
					m_open_con_bal = rs.getBigDecimal(6); // added by udara on 28-05-2013
				}
				
				//out.println("uv test 3");
				
				//out.println("<table width='100%'>");
				
				// added by udara 28-11-2013
				
				
				
				
				// added by udara 18-02-2016
				Sql_data=" "+
					
					" SELECT NVL(SUM(ADJUSTED_AMOUNT),0) FROM (  "+
					
					" SELECT "+
					" A.FINANCE_NO, "+
					" A.INVOICE_NO, "+
					" A.ADJUSTED_AMOUNT ADJUSTED_AMOUNT, "+
					" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
					" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE  A.FINANCE_NO LIKE '%%' "+
					" AND    A.FINANCE_NO = B.FINANCE_NO "+
					" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY WHERE ENT_USER = 'LAKDLALL' AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ m_active_status_string_for_cr_notes +" ) "+  
					" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
					" AND    A.INVOICE_NO IN "+
					" ( SELECT  INVOICE_NO "+
					" FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO LIKE '%"+m_finance_no+"%' "+ 
					" AND ACTIVE_STATUS = 'Y' "+
					" AND ( INVOICE_TYPE <> 'INSURANCE' "+
					" AND REMARKS <> 'CHARGES - INSURANCE' )  "+            
					
					" AND VALUE_DATE <= TO_DATE ( '"+m_date+"','DD-MM-YYYY' )  "+
					" ) "+
					" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
					" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' ) "+
					
					" UNION ALL "+
					
					" SELECT "+
					" A.FINANCE_NO, "+
					" A.INVOICE_NO, "+
					" A.ADJUSTED_AMOUNT ADJUSTED_AMOUNT, "+
					" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
					" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE  A.FINANCE_NO LIKE '%%' "+
					" AND    A.FINANCE_NO = B.FINANCE_NO "+
					" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY WHERE ENT_USER = 'LAKDLALL' AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ m_active_status_string_for_cr_notes +" ) "+  
					" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
					" AND    A.INVOICE_NO IN "+
					" ( SELECT  INVOICE_NO "+
					" FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO LIKE '%"+m_finance_no+"%' "+ 
					" AND ACTIVE_STATUS = 'Y' "+
					" AND ( INVOICE_TYPE <> 'INSURANCE' "+
					" AND REMARKS <> 'CHARGES - INSURANCE' )  "+            
					
					" AND VALUE_DATE > TO_DATE ( '"+m_date+"','DD-MM-YYYY' )  "+
					" ) "+
					" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
					" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' ) "+
					
					
					
					" ) "+
					" "; 
				// end by udara 18-02-2016
				
				
				//double cr_note_val  = 0;
				BigDecimal cr_note_val = new BigDecimal(0.00);
				
				
				//out.println(Sql_data);
				rs=stmt.executeQuery(Sql_data);
				
				//out.println("uv test 4");
				
				if(rs.next()){
					cr_note_val = rs.getBigDecimal(1);//cr_note_val = rs.getDouble(1);
				}
				
				// end by udara 28-11-2013
				
				//out.println("uv test 5");
				
				//out.println("uv test 5.1 " + m_invoice);
				//out.println("uv test 5.2 " + cr_note_val);
				
				m_invoice=m_invoice.subtract(cr_note_val); // added by udara 28-11-2013
				
				//out.println("uv test 6");
				
				out.println("<tr >"); 
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udar on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				
				
				out.println("<tr  >"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("<u>* Recovered from B/B/F</u>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("<u>ADJUSTMENTS</u>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr  style='border-top-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr  style='border-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udsara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Penalty");
				out.println("</td>");
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_panalty));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Insurance");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_insurance));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr  style='border-top-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Invoice");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_invoice));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Closing");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(total_Closing));
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0;text-align:left'>");
				out.println("Rental Arrears");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_rental));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Future Rentals");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");	
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(total_Future_ren));
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' COLSPAN='2' align='left'>");
				out.println("Early Settlment Next Month");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(nf.format(total_early_sett_next_month));
				out.println("</td>");
				
				out.println("</tr>");
				
				
				//--------------------------------------------------------------------------
				
				
				
				
				
				
				out.println("<tr style='text-align:right'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // adde4d by udara on 02-05-2013
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				out.println("<B>"+nf.format(m_panalty.add(m_invoice).add(m_rental))+"</B>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				out.println("<B>"+nf.format(total_cash.add(m_insurance))+"</B>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udarea on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				//out.println("<td style='border-style:none;z-index: 0'>");
				//out.println("&nbsp;</td>");
				out.println("<td style='text-align:left;border-style:none;z-index: 0' COLSPAN='2' >"); 
				out.println("<B>B/B/F</B></td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				
				
				if(m_open_con_bal.signum() >=0 ){
					out.println(nf.format(m_open_con_bal));
				}else{
					out.println("("+nf.format(m_open_con_bal.negate())+")");
				}
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				//out.println(nf.format(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month).add(total_early_sett_next_month_new_adjustment))); //.add(total_early_sett_next_month_new_adjustment) - Added By: SDF 
				out.println(nf.format(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))); // added by udara 21-09-2015
				
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:right'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:right'>"); 
				
				out.println("<td style='text-align:letf;border-style:none;z-index: 0' COLSPAN='2'>"); 
				out.println("During the Month Arrears</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				
				
				out.println(nf.format(total_Arrears.subtract(m_open_con_bal))); // out.println(nf.format(m_bbf)); // udara 01-07-2013
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Net[Increase]/Decrease");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				
				if((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).signum()>=0){
					out.println(nf.format(m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))));
				}
				else{
					out.println(nf.format((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).negate()));
				}
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				//out.println("<td style='border-style:none;z-index: 0'>");
				//out.println("</td>");
				out.println("<td style='text-align:left;border-style:none;z-index: 0' COLSPAN='2'>"); 
				out.println("Total Arears</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>");
				out.println(nf.format(total_Arrears));
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>");
				
				
				if(m_open_con_bal.signum() != 0){ // 
					out.println( nf.format((m_open_con_bal.subtract((total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month)))).multiply(new BigDecimal(100)).divide(m_open_con_bal,BigDecimal.ROUND_HALF_EVEN)) ); // out.println( nf.format(((total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month)).subtract(m_open_con_bal)).multiply(new BigDecimal(100)).divide(m_open_con_bal,BigDecimal.ROUND_HALF_EVEN)) );
				}
				else{
					out.println("0.00");
				}
				
				
				out.println("%</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes (All)</b>  ");
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill();' >"); //out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val)+" ");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				// ======================== added by udara 07-04-2014 =======================================================================
				
				double cr_note_val_rental = 0;
				double cr_note_val_arrears = 0;
				double cr_note_val_other_curr = 0;
				double cr_note_val_other_prev = 0;				
				
				
				
				// added by udara 18-01-2016
				Sql_data=" "+
					" select  "+
					" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','RENTAL','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_RENTAL, "+
					" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','ARREARS','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_ARREARS, "+
					" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','OTHER_CURR','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_CURR, "+
					" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','OTHER_PREV','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_PREV "+
					" from DUAL "+
					" ";
				
				if(!m_finance_no.equals("")){	
					Sql_data=" "+
						" select  "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_2('"+m_date+"','RENTAL','LAKDLALL','"+m_finance_no+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_RENTAL, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_2('"+m_date+"','ARREARS','LAKDLALL','"+m_finance_no+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_ARREARS, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_2('"+m_date+"','OTHER_CURR','LAKDLALL','"+m_finance_no+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_CURR, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_2('"+m_date+"','OTHER_PREV','LAKDLALL','"+m_finance_no+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_PREV "+
						" from DUAL "+
						" ";	
				}
				
				
				else if(!m_location.equals("")){	
					
					Sql_data=" "+
						" select  "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_3('"+m_date+"','RENTAL','LAKDLALL','"+m_location+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_RENTAL, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_3('"+m_date+"','ARREARS','LAKDLALL','"+m_location+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_ARREARS, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_3('"+m_date+"','OTHER_CURR','LAKDLALL','"+m_location+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_CURR, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH_3('"+m_date+"','OTHER_PREV','LAKDLALL','"+m_location+"','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_PREV "+
						" from DUAL "+
						" ";
					
				}
				
				else{
					Sql_data=" "+
						" select  "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','RENTAL','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_RENTAL, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','ARREARS','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_ARREARS, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','OTHER_CURR','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_CURR, "+
						" NVL(GET_CR_AMNT_RECOV_AUTO_MONTH('"+m_date+"','OTHER_PREV','LAKDLALL','"+m_perform_status+"','"+m_active_status+"'),0) CR_NOTES_OTHER_PREV "+
						" from DUAL "+
						" ";
				}
				
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					cr_note_val_rental = rs.getDouble(1);
					cr_note_val_arrears = rs.getDouble(2);
					cr_note_val_other_curr = rs.getDouble(3);
					cr_note_val_other_prev = rs.getDouble(4);
				}				
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes - Rental</b>  ");
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill_rental();' >"); //out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val_rental)+" ");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes - Arrears</b>  ");
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill_arrears();' >"); //out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val_arrears)+" ");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes Other - Previous</b>  ");
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill_other_prev();' >"); //  out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val_other_prev)+" "); 
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes Other - Current Month</b>  ");
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill_other_curr();' >"); //out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val_other_curr)+" ");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				// added by udara 19-02-2015
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(" &nbsp; ");
				out.println("</td>");
				// end by udara 19-02-2015
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				// ======================== end by udara 07-04-2014 ==========================================================================
				
				
				out.println("</table>");	
				
				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			
			
			
			else if(m_chksql.equals("print_report_new_drill")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slab="";
				String m_date_format="";
				String m_user_name = "";
				String m_sys_date = "";
				String m_cr_off_name = ""; 
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_active_status_string_for_cr_notes = ""; // added by udara 18-02-2016
				
				// added by udara 24-10-2017
				String m_perform_status = "";

				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
				}
				
				String m_perform_status_string = " ";
				
				if(!m_perform_status.equals("")){
					m_perform_status_string = " AND A.PERFORM_STATUS      =  '"+m_perform_status+"' ";
				}
				// end by udara 24-10-2017
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
					//m_active_status_string_for_cr_notes = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 18-02-2016
					m_active_status_string = " AND A.YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
					m_active_status_string_for_cr_notes = " AND YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
				}
				
				String m_cr_officer = ""; // udara 22-10-2013
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// udara 22-10-2013
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				m_slab = req.getParameter("slab").trim();
				
				String m_region   = req.getParameter("region");
				
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				String m_product_name=req.getParameter("product_name"); // added by udara 15-10-2018
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Recovery Report - Automated - Daily</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); // commented by udara on 09-05-2013
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				out.println("function show_followup(val){ ");
				out.println("	m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;");
				out.println("	window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
				}
				
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+ // added by udara 18-11-2013
					" TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI PM'), "+ // added by udara 18-11-2013
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_cr_officer+"'),'-') "+ // added by udara 18-11-2013
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
					m_user_name = rs.getString(5); // added by udara 18-11-2013
					m_sys_date = rs.getString(6); // added by udara 18-11-2013
					m_cr_off_name = rs.getString(7); // added by udara 18-11-2013
				}
				
				String Sql_data="";
				
				if (m_slab.equals("NEW") ){
					
					// commented by udara 10-11-2015
					
					
					Sql_data="   "+
						
						
						" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
						" 1,  "+
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) TOTAL_ARREARS,  "+ // commented by udara 28-08-2017
						" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ // added by udara 28-08-2017
						" NVL (A.RENTAL_AMOUNT, 0) RENTAL_AMOUNT,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0) SETTLED_AMOUNT_CUR_MON_RENTAL,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0) SETTLED_AMOUNT_CUR_MON_ARREARS,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0) SETTLED_AMOUNT_CUR_MON,  "+
						" A.FINANCE_NO FINANCE_NO, "+
						" NVL ( A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
						" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
						" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
						" NVL ( A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT,  "+
						" NVL ( A.FUTURE_RENTAL, 0) FUTURE_RENTAL, "+ 
						" A.CLIENT_CODE CLIENT_CODE, "+
						" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)- NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount, 	"+
						
						//" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE "+ // commented by udara 28-08-2017
						" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0)  CR_NOTE, "+ // added by udara 28-08-2017
						
						// added by udara 28-08-2017
						" ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) NEW_FUTURE_RENTAL, "+ // 20
						
						
						
						" ( ( "+
						" CASE "+ 
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) ) NEW_CLOSING, "+ // 21
						
						
						
						" ( "+
						" CASE "+
						" WHEN ( ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) <= 0 ) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) >= 0 ) ) "+
						" THEN ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) ) "+
						" ELSE ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)> 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) + ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)                                 > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) "+
						" END ) NEW_CASH_TOTAL "+ // 22
						// end by udara 28-08-2017	
						
						
						" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+
						//" WHERE A.ENT_USER='LAKDLALL' "+
						" WHERE TRUNC(A.REPORT_DATE)  = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ 
						//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.collection_officer) like UPPER('"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND A.TOTAL_PERIOD = '"+m_slab+"'  "+
						" AND A.ACTIVATED_DATE  >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE  <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						m_active_status_string +
						m_perform_status_string; // added by udara 24-10-2017
					
						if(!(m_region.equals("NOT_SELECT"))){                
							Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
						}
						
						// added by udara 15-10-2018
				       if(!(m_product_name.equals("NOT_SELECT"))){                 
					      Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				       }
				       // end by udara 15-10-2018
						
						Sql_data = Sql_data +" ORDER BY TOTAL_PERIOD ";
					
					
					
				}
				else {
					
					// commented by udara 10-11-2015
					
					
					// added by udara 10-11-2015
					
					Sql_data="   "+
						
						
						" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
						" 1,  "+
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) TOTAL_ARREARS,  "+ // commented by udara 28-08-2017
						" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ // added by udara 28-08-2017
						" NVL (A.RENTAL_AMOUNT, 0) RENTAL_AMOUNT,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0) SETTLED_AMOUNT_CUR_MON_RENTAL,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0) SETTLED_AMOUNT_CUR_MON_ARREARS,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0) SETTLED_AMOUNT_CUR_MON,  "+
						" A.FINANCE_NO FINANCE_NO, "+
						" NVL ( A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
						" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
						" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
						" NVL ( A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT,  "+
						" NVL ( A.FUTURE_RENTAL, 0) FUTURE_RENTAL, "+ 
						" A.CLIENT_CODE CLIENT_CODE, "+
						" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)- NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount, 	"+
						
						//" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE "+ 
						" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0)  CR_NOTE, "+ // added by udara 
						
						// added by udara 28-08-2017
						" ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) NEW_FUTURE_RENTAL, "+ // 20
						
						
						
						" ( ( "+
						" CASE "+ 
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) ) NEW_CLOSING, "+ // 21
						
						
						
						" ( "+
						" CASE "+
						" WHEN ( ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) <= 0 ) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) >= 0 ) ) "+
						" THEN ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) ) "+
						" ELSE ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)> 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) + ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)                                 > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) "+
						" END ) NEW_CASH_TOTAL "+ // 22
						// end by udara 28-08-2017	
						
						
						" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+
						//" WHERE A.ENT_USER='LAKDLALL' "+
						" WHERE A.TOTAL_PERIOD = '"+m_slab+"' "+
						" AND TRUNC(A.REPORT_DATE)  = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ 
						//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.collection_officer) like UPPER('"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						" AND A.TOTAL_PERIOD = '"+m_slab+"' "+
						" AND A.ACTIVATED_DATE  < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						m_active_status_string +
						m_perform_status_string; // added by udara 24-10-2017
					
						if(!(m_region.equals("NOT_SELECT"))){                
							Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
						}
						
						// added by udara 15-10-2018
				       if(!(m_product_name.equals("NOT_SELECT"))){                 
					      Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				       }
				       // end by udara 15-10-2018
						
						Sql_data = Sql_data +" ORDER BY TOTAL_PERIOD ";
					
					// end by udara 10-11-2015
					
				}
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00); 
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				
				BigDecimal total_AdjAmount  = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Arrears_pre = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				// added by udara 18-11-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >User :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Credit Officer :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cr_off_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Date :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_sys_date+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("</table >");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT - AUTOMATED - DAILY</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Slab "+m_slab+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2'><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>Finance Number</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"10\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modified by udara on 02-05-2013 from 8 columns to 9
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>");  // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Retal Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Overall Collection %</b></td>"); // added by udara 19-02-2015
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013   
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(12)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString(8)+"\")'  ><u>"+rs.getString(8)+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(12)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add((rs.getBigDecimal(12))))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab  = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add((rs.getBigDecimal(12)))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add((rs.getBigDecimal(12))));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					early_sett = new BigDecimal(0.00);
					futu_rent = new BigDecimal(0.00);
					early_sett_next_month = new BigDecimal(0.00);
					
					early_sett = early_sett.add(rs.getBigDecimal(16));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(17));
					
					
					if(rs.getBigDecimal(16).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(16).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(16))+"</td>");
					}
					
					
					if(rs.getBigDecimal(17).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(17).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(17))+"</td>");
					}
					
					
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(15));
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5));
					
					
					
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					
					tot_rent = new BigDecimal(0.00);
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 03-07-2013
					
					//futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(rs.getBigDecimal(17)));  // added by udara on 03-07-2013 // commented by udara 28-08-2017
					futu_rent = futu_rent.add(rs.getBigDecimal(20)); // added by udara 28-08-2017
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					try{
						rental_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(4).signum()>0){
							rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6)).add(rs.getBigDecimal(11));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(19)); // added by udara 26-11-2013
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					try{
						Arrears_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(3).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(12)),BigDecimal.ROUND_HALF_EVEN));
						}else if(rs.getBigDecimal(11).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(12),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					BigDecimal overall_collection = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection = (tot_rent.add(Arrea_Collec)).multiply(new BigDecimal(100)).divide(totalab,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection = new BigDecimal(0.00);
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(overall_collection)+"%</td>"); 
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(10));
					closing = closing.add(rs.getBigDecimal(21)); // added by udara 28-08-2017
					Cash_Total = new BigDecimal(0.00);
					
					
					
					
					// added by udara 28-08-2017
					if(futu_rent.signum()<0){								
						if((futu_rent.signum()<0) && (closing.signum()>0)){		
							//closing = closing.add(futu_rent);	// udara
							//futu_rent = new BigDecimal(0.00);	
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);	
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");	
						}		
						else{		
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);	
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");	
						}		
					}else{			
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);		
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");		
					}			
					// end by udara 28-08-2017
					
					
					if((futu_rent.signum()<= 0) && (closing.signum()>= 0)){ // added by udara 05-11-2013
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					Cash_Total = rs.getBigDecimal(22); // added by udara 28-08-2017
					
					BCF = new BigDecimal(0.00);
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month); // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					out.println("</tr>");
					
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					if(rs.getBigDecimal(3).signum()>=0){
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(12)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(12));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					
					total_Ear_Sett = total_Ear_Sett.add(rs.getBigDecimal(16));
					total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(rs.getBigDecimal(17));
					
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					total_Rent = total_Rent.add(tot_rent);
					
					try{
						
						total_Rental_Pres = new BigDecimal(0.00);
						if(total_Due_Rental.signum()>0){
							total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					
					try{
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Ear_Sett_next_month.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_next_month.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_next_month)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					try{
						total_Arrears_pre  = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
						}else if(total_Settle.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						total_Arrears_pre  = new BigDecimal(0.00);
					}
					
					if(total_Arrears_pre.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					BigDecimal overall_collection_total = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection_total = (total_Rent.add(total_Arresrs_colle)).multiply(new BigDecimal(100)).divide(total_AB,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(overall_collection_total)+"%</b></td>");  
					
					if(total_Future_ren_show.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_show.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_show)+"</b></td>"); 
					}
					
					
					if(total_Closing.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
					}
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_bcf.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");	
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");		
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// added by udara 19-10-2016
			else if(m_chksql.equals("print_report_new_drill_total")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slab="";
				String m_date_format="";
				String m_user_name = "";
				String m_sys_date = "";
				String m_cr_off_name = ""; 
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_active_status_string_for_cr_notes = ""; // added by udara 18-02-2016
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
					//m_active_status_string_for_cr_notes = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 18-02-2016
					m_active_status_string = " AND A.YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
					m_active_status_string_for_cr_notes = " AND YARD_STATUS = '"+m_active_status+"' "; // added by udara 31-07-2018
				}
				
				String m_cr_officer = ""; // udara 22-10-2013
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// udara 22-10-2013
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				m_slab = "";
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				String m_product_name=req.getParameter("product_name"); 
				
				
				// added by udara 23-08-2017
				String m_perform_status = "";
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
				}
				
				String m_perform_status_string = " ";
				
				if(!m_perform_status.equals("")){
					m_perform_status_string = " AND A.PERFORM_STATUS      =  '"+m_perform_status+"' ";
				}
				
				
				String m_region   = req.getParameter("region");
				
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				// end by udara 23-08-2017
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Recovery Report - Automated - Daily</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_v2_monthly?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); // commented by udara on 09-05-2013
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				out.println("function show_followup(val){ ");
				out.println("	m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;");
				out.println("	window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
				}
				
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+ // added by udara 18-11-2013
					" TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI PM'), "+ // added by udara 18-11-2013
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_cr_officer+"'),'-') "+ // added by udara 18-11-2013
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
					m_user_name = rs.getString(5); // added by udara 18-11-2013
					m_sys_date = rs.getString(6); // added by udara 18-11-2013
					m_cr_off_name = rs.getString(7); // added by udara 18-11-2013
				}
				
				String Sql_data="";
				
				if (m_slab.equals("NEW") ){
					
					
					
					Sql_data="   "+
						
						
						" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
						" 1,  "+
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) TOTAL_ARREARS,  "+ // commented by udara 28-08-2017
						" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ // added by udara 28-08-2017
						" NVL (A.RENTAL_AMOUNT, 0) RENTAL_AMOUNT,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0) SETTLED_AMOUNT_CUR_MON_RENTAL,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0) SETTLED_AMOUNT_CUR_MON_ARREARS,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0) SETTLED_AMOUNT_CUR_MON,  "+
						" A.FINANCE_NO FINANCE_NO, "+
						" NVL ( A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
						" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
						" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
						" NVL ( A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT,  "+
						" NVL ( A.FUTURE_RENTAL, 0) FUTURE_RENTAL, "+ 
						" A.CLIENT_CODE CLIENT_CODE, "+
						" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)- NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount, 	"+
						
						//" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE "+ // added by udara 28-08-2017
						" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0)  CR_NOTE, "+ // added by udara 28-08-2017
						
						// added by udara 28-08-2017
						" ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) NEW_FUTURE_RENTAL, "+ // 20
						
						" ( ( "+
						" CASE "+ 
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) ) NEW_CLOSING, "+
						
						" ( "+
						" CASE "+
						" WHEN ( ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) <= 0 ) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) >= 0 ) ) "+
						" THEN ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) ) "+
						" ELSE ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)> 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) + ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)                                 > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) "+
						" END ) NEW_CASH_TOTAL "+ // 22
						// end by udara 28-08-2017
						
						
						" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+
						//" WHERE A.ENT_USER='LAKDLALL' "+
						" WHERE TRUNC(A.REPORT_DATE)  = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ 
						//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.collection_officer) like UPPER('"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND A.TOTAL_PERIOD = '"+m_slab+"'  "+
						" AND A.ACTIVATED_DATE  >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE  <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						m_active_status_string +
						
						// added by udara 23-08-2017
						m_perform_status_string; 
					
					if(!(m_region.equals("NOT_SELECT"))){                
						Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					}
					// end by udara 23-08-2017
					
					// added by udara 15-10-2018
				   if(!(m_product_name.equals("NOT_SELECT"))){                 
					   Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				   }
				   // end by udara 15-10-2018
					
					//" ORDER BY TOTAL_PERIOD "; // commented by udara 23-08-2017
					Sql_data = Sql_data + " ORDER BY TOTAL_PERIOD ";
					
					
					
				}
				else {
					
					
					
					Sql_data="   "+
						
						
						" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
						" 1,  "+
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (A.OTHER_CHARGES_CURR_MONTH, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) TOTAL_ARREARS,  "+
						" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ // added by udara 28-08-2017
						" NVL (A.RENTAL_AMOUNT, 0) RENTAL_AMOUNT,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0) SETTLED_AMOUNT_CUR_MON_RENTAL,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0) SETTLED_AMOUNT_CUR_MON_ARREARS,  "+
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0) SETTLED_AMOUNT_CUR_MON,  "+
						" A.FINANCE_NO FINANCE_NO, "+
						" NVL ( A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
						" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
						" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
						" NVL ( A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT,  "+
						" NVL ( A.FUTURE_RENTAL, 0) FUTURE_RENTAL, "+ 
						" A.CLIENT_CODE CLIENT_CODE, "+
						" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)- NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount, 	"+
						
						//" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE "+ 
						" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0)  CR_NOTE, "+ // added by udara 28-08-2017
						
						// added by udara 28-08-2017
						" ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) NEW_FUTURE_RENTAL, "+ // 20
						
						" ( ( "+
						" CASE "+ 
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0))  "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) ) NEW_CLOSING, "+
						
						" ( "+
						" CASE "+
						" WHEN ( ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) <= 0 ) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) >= 0 ) ) "+
						" THEN ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) ) "+
						" ELSE ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)> 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
						" CASE "+
						" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
						" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
						" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
						" END ) + ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND ( ( "+
						" CASE "+
						" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
						" AND (NVL(A.CLOSING_BALANCE,0)                                 > 0)) "+
						" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
						" ELSE NVL(A.CLOSING_BALANCE,0) "+
						" END ) > 0 ) ) "+
						" THEN 0 "+
						" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
						" END ) ) "+
						" END ) NEW_CASH_TOTAL "+ // 22
						// end by udara 28-08-2017
						
						
						" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_MONTHLY A, "+m_schema_name+".af_co_pro_application_details B  "+
						//" WHERE A.ENT_USER='LAKDLALL' "+
						//" WHERE A.TOTAL_PERIOD = '"+m_slab+"' "+
						" WHERE TRUNC(A.REPORT_DATE)  = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ 
						//" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.collection_officer) like UPPER('"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND A.TOTAL_PERIOD = '"+m_slab+"' "+
						" AND A.ACTIVATED_DATE  < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						m_active_status_string +
						
						// added by udara 23-08-2017
						m_perform_status_string; 
					
					if(!(m_region.equals("NOT_SELECT"))){                
						Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					}
					// end by udara 23-08-2017
					
					// added by udara 15-10-2018
				   if(!(m_product_name.equals("NOT_SELECT"))){                 
					   Sql_data = Sql_data +"    AND   B.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				   }
				   // end by udara 15-10-2018
					
					//" ORDER BY TOTAL_PERIOD "; // commented by udara 23-08-2017
					Sql_data = Sql_data + " ORDER BY TOTAL_PERIOD "; // added by udara 23-08-2017
					
					// end by udara 10-11-2015
					
				}
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00); 
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				
				BigDecimal total_AdjAmount  = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Arrears_pre = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				// added by udara 18-11-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >User :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Credit Officer :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cr_off_name+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Date :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_sys_date+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("</table >");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT - AUTOMATED - DAILY</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Slab "+m_slab+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2'><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>Finance Number</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"10\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modified by udara on 02-05-2013 from 8 columns to 9
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>");  // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Retal Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Overall Collection %</b></td>"); // added by udara 19-02-2015
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013   
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(12)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString(8)+"\")'  ><u>"+rs.getString(8)+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(12)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add((rs.getBigDecimal(12))))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab  = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add((rs.getBigDecimal(12)))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add((rs.getBigDecimal(12))));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					early_sett = new BigDecimal(0.00);
					futu_rent = new BigDecimal(0.00);
					early_sett_next_month = new BigDecimal(0.00);
					
					early_sett = early_sett.add(rs.getBigDecimal(16));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(17));
					
					
					if(rs.getBigDecimal(16).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(16).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(16))+"</td>");
					}
					
					
					if(rs.getBigDecimal(17).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(17).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(17))+"</td>");
					}
					
					
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(15));
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5));
					
					
					
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					
					tot_rent = new BigDecimal(0.00);
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 03-07-2013
					
					//futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(rs.getBigDecimal(17)));  // added by udara on 03-07-2013
					futu_rent = futu_rent.add(rs.getBigDecimal(20)); // added by udara 28-08-2017
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					try{
						rental_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(4).signum()>0){
							rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6)).add(rs.getBigDecimal(11));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(19)); // added by udara 26-11-2013
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					try{
						Arrears_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(3).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(12)),BigDecimal.ROUND_HALF_EVEN));
						}else if(rs.getBigDecimal(11).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(12),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					BigDecimal overall_collection = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection = (tot_rent.add(Arrea_Collec)).multiply(new BigDecimal(100)).divide(totalab,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection = new BigDecimal(0.00);
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(overall_collection)+"%</td>"); 
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(10));
					closing = closing.add(rs.getBigDecimal(21)); // added by udara 28-08-2017
					Cash_Total = new BigDecimal(0.00);
					
					
					
					
					// added by udara 28-08-2017
					if(futu_rent.signum()<0){								
						if((futu_rent.signum()<0) && (closing.signum()>0)){		
							//closing = closing.add(futu_rent);	// udara
							//futu_rent = new BigDecimal(0.00);	
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);	
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");	
						}		
						else{		
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);	
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");	
						}		
					}else{			
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);		
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");		
					}			
					// end by udara 28-08-2017
					
					
					
					if((futu_rent.signum()<= 0) && (closing.signum()>= 0)){ // added by udara 05-11-2013
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					Cash_Total = rs.getBigDecimal(22); // added by udara 28-08-2017
					
					BCF = new BigDecimal(0.00);
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month); // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					out.println("</tr>");
					
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					if(rs.getBigDecimal(3).signum()>=0){
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(12)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(12));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					
					total_Ear_Sett = total_Ear_Sett.add(rs.getBigDecimal(16));
					total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(rs.getBigDecimal(17));
					
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					total_Rent = total_Rent.add(tot_rent);
					
					try{
						
						total_Rental_Pres = new BigDecimal(0.00);
						if(total_Due_Rental.signum()>0){
							total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					
					try{
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Ear_Sett_next_month.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_next_month.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_next_month)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					try{
						total_Arrears_pre  = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
						}else if(total_Settle.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						total_Arrears_pre  = new BigDecimal(0.00);
					}
					
					if(total_Arrears_pre.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					BigDecimal overall_collection_total = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection_total = (total_Rent.add(total_Arresrs_colle)).multiply(new BigDecimal(100)).divide(total_AB,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(overall_collection_total)+"%</b></td>");  
					
					if(total_Future_ren_show.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_show.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_show)+"</b></td>"); 
					}
					
					
					if(total_Closing.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
					}
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_bcf.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");	
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");		
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			// end by udara 19-10-2016
			
			
			
			
			
			//}
			
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(rs_drill_new!=null){try{rs_drill_new.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
