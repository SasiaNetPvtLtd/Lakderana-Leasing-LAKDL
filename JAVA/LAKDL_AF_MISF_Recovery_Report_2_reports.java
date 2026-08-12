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


public class LAKDL_AF_MISF_Recovery_Report_2_reports extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
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
				
				String m_region=""; // Added By: Samith Dilshan on 2015-06-02
				
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
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				//if(!m_cr_officer.equals(""))
				//	m_cr_officer_string  =  " AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') ";  //ADDED MILINDA
				
				
				// Added By Samith Dilshan on 2015-06-12
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_2_main?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_2_main?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_2_main?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
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
				
				// added by udara 14-11-2013
				out.println("function print_cr_note_drill(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				// end by udara 14-11-2013
				
				// added by udara 07-04-2014
				out.println("function print_cr_note_drill_rental(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new_cr_rental&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_arrears(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new_cr_arrears&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_other_curr(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new_cr_other_curr&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("			window.open(m_url);");
				out.println("}");
				
				out.println("function print_cr_note_drill_other_prev(){");
				out.println("			var m_to_date  ='"+m_date+"';");
				out.println("			var m_location = '"+m_location+"';");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new_cr_other_prev&location=\"+m_location+\"&to_date=\"+m_to_date;");
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
				
				
				Sql_data=" SELECT  "+
					" NVL( A.TOTAL_PERIOD,0), "+ //1
					" COUNT(*), "+ //2
					//" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013 // commented by udara 05-10-2013
					
					//" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) total_arrears, "+ //3 added by udara 05-10-2013
					" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0))  total_arrears, "+ // added by udara 16-09-2015
					//" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1)),'DD-MM-YYYY'))),0) total_arrears, "+ //3 added by udara 05-10-2013
					
					
					//" NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" SUM( A.RENTAL_AMOUNT) , "+ //4
					" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					" SUM (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL (SUM (A.total_amount), 0), "+ //8
					" NVL (SUM (A.CLOSING_BALANCE), 0), "+ //9
					" NVL (SUM (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL (SUM (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL(SUM(A.FUTURE_RENTAL),0),"+//12
					//" NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),0) EARLY_SETTLE,"+//13
					
					// added below by udara on 29-06-2013
					/*
					" NVL ( "+
						" SUM( "+
						  " DECODE( "+
						      "  SIGN(  ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
						      " ,1 "+
						      " ,RENTAL_AMOUNT, "+
						      " (DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)) - NVL(SETTLED_OTH_FROM_EXCESS,0) "+
						  " ) "+
						
						" ,0) EARLY_SETTLE, "+ // 13
					*/
					" NVL(SUM(A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					
					//" NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT,0)),0) EARLY_SETTLE_NEXT_MONTH,"+//14
					// added below by udara on 29-06-2013
					/*
					" NVL ( "+
					   " SUM(  "+
           				" DECODE(  "+
					       " SIGN( ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
					       " ,1 "+
					       " ,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-NVL(SETTLED_OTH_FROM_EXCESS,0)-NVL(RENTAL_AMOUNT,0),  "+
					       " 0) "+ 
					   " )  "+
					   " ,0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					*/
					" NVL(SUM(A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" SUM( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL(SUM(SETTLED_OTH_FROM_EXCESS),0)"+//16
					" SUM(DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					/*
					" SUM( "+
					" ( "+
					  " CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					    " 0 "+
					   " ELSE   "+ 
					    " NVL ( A.FUTURE_RENTAL, 0) "+
					    " END "+
					" ) "+
					" ) NEW_FUTURE_RENTAL "+ // 18
					*/
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" SUM( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					/*
				" SELECT  "+
				" SUM( "+
				" NVL(A.EARLY_SETT,0) + (CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE "+  
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END) "+
					" ) TOTAL_RENT_NEW "+ // 20
				*/
					
					// added by udara 05-11-2013	
					
					" ,SUM( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					
					//"   NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) "+ // 21 added by udara on 20-11-2013
					
					"   NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 21 added by udara on 05-08-2015
					"   , NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara on 05-08-2015
					
					
					
					
					" FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE A.ENT_USER='"+m_username+"' "+
					//" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // commented by udara 21-08-2014
					" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ // " AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					//" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
					//" AND TRUNC(A.ENT_DATE) = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ // added by udara on 09-05-2013
					" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // ADDED MILINDA
					//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
					" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') ";
					
					
					if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
						Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					}
					
				
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					Sql_data = Sql_data + "    "+m_active_status_string+"   "+
					" GROUP BY A.TOTAL_PERIOD ORDER BY A.TOTAL_PERIOD "; 
				
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
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
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
				
				/*		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						
					
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Executive</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Branch</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_location_desc+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_start_date+"</td>"); 
						out.println("<td width=\"50\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_end_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
					*/	
				
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
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>SLAB</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
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
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				
				
				//double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				//double  m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				//out.println("<table id=mytable align=\"left\" width=1750px border=\"1\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
				
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
					
					//comment by ns on 06-03-2013
					/*
					if (rs.getBigDecimal(8).doubleValue() < 0)
					{
						
						
						early_sett = early_sett.add(rs.getBigDecimal(13));
						early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					}	
					
					else
					{
						early_sett = new BigDecimal(0.00) ;
					}
					*/
					
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
					
					// commented by udara on 01-08-2013
					/*
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(rs.getBigDecimal(13))).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(rs.getBigDecimal(13));
					}
					*/
					
					// =================================
					
					// Rent_Collec = Rent_Collec.add(Other_charges_coll_from_excess); // commented by udara 02-07-2013 // added by udara on 02-05-2013
					
					/*
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){ 
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					*/
					
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					/*
					if(rs.getDouble(4) < (tot_rent.subtract(rs.getBigDecimal(10))).doubleValue()){
						futu_rent = futu_rent.add(new BigDecimal((tot_rent.subtract(rs.getBigDecimal(10))).doubleValue()-rs.getDouble(4)));
					}
					*/
					
					
					
					//futu_rent = futu_rent.add(rs.getBigDecimal(12).subtract(early_sett_next_month)); // commented by udara on 29-07-2013
					//futu_rent = futu_rent.add(rs.getBigDecimal(12).subtract(rs.getBigDecimal(14))); // commented by udara on 01-08-2013
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
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					//futu_rent =  Cash_Total.subtract(closing).subtract(Arrea_Collec).subtract(Rent_Collec);
					
					
					// commented by udara on 29-07-2013
					/*
					if(futu_rent.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					*/
					
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
					// end by udara on 29-07-2013
					
					
					//Cash_Total = Cash_Total.add(rs.getBigDecimal(7));
					
					//closing  = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9));
					//Cash_Total = new BigDecimal(0.00);
					
					//Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent); // commented by udara on 29-07-2013
					
					// added by udara on 29-07-2013
					//if((futu_rent.signum()<0) && (closing.signum()>0)){ // commented by udara on 01-08-2013
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					//Cash_Total = Cash_Total.subtract(rs.getBigDecimal(21)); // commented by udara 03-08-2015 // added by udara on 20-11-2013 // commented by udara 26-11-2013
					//Cash_Total = Cash_Total.add(rs.getBigDecimal(21)); // added by udara 03-08-2015
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					// added by udara on 29-07-2013
					/*
					if((futu_rent.signum()<0) && (closing.signum()>0)){
						BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).subtract(futu_rent);
					}
					else{
						BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;
					}
					*/
					// end by udara on 29-07-2013
					
					// added by udara 04-08-2015
					/*
					if(rs.getBigDecimal(21).signum()>0){
						BCF = BCF.subtract(rs.getBigDecimal(21));
					}
					else if(rs.getBigDecimal(21).signum()<0){
						BCF = BCF.subtract((rs.getBigDecimal(21).multiply(new BigDecimal("-1.00"))));
					}
					*/
					// end by udara 04-08-2015
					
					// added by udara 05-08-2015
					/*
					BCF = BCF.subtract(rs.getBigDecimal(21));
					
					if((rs.getBigDecimal(3)).signum()>=0){
						
						BCF = BCF.add(rs.getBigDecimal(22));
						
					}
					*/
					
					// end by udara 05-08-2015
					
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
					
					//		closing_bal=m_total_due - rs.getDouble(8);
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
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					// commented by udara on 01-07-2013
					/*
					if(total_Arrears.signum()>0){
						total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
					}
					*/
					
					// added by udara on 01-07-2013
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
				
				// commented by udara on 01-07-2013
				/*
				if(total_Arrears_Pres.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
				}
				*/
				
				// added by udara on 01-07-2013
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
				// end by udara on 01-07-2013
				
				// added by udara 19-02-2015
					
					BigDecimal overall_collection_total = new BigDecimal(0.00);
					//if(totalab.signum()>0){
					if(total_AB.signum()>0){ // mod by udara 21-04-2015
						overall_collection_total = (total_Rent.add(total_Arresrs_colle)).multiply(new BigDecimal(100)).divide(total_AB,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(overall_collection_total)+"%</b></td>");  // out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(0)+"%</b></td>");  
					// end by udara 19-02-2015
				
				//total_Future_ren = total_Future_ren.add(m_new_future_rental);
				// commented by udara on 29-07-2013
				/*
				if(total_Future_ren.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren)+"</b></td>"); 
				}
				*/
				
				// added by udara on 29-07-2013
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
				
				//total_cash = total_cash.add(m_new_future_rental); // commented by udara 01-07-2013
				if(total_cash.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
				}
				
				//total_bcf = total_bcf.subtract(m_new_future_rental); // commented by udara 01-07-2013
				if(total_bcf.signum()<0){
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
					
				}else{
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
				}
				
				
				
				out.println("</tr>");		
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	if(count>0){
					
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					
				} // added by udara 31-12-2013
				
				
				// added by udara 31-12-2013
				int count_2 = 0;
				
				Sql_data = "  "+
					" SELECT "+ 
					" COUNT(*)  "+ // 2
					" FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
					" WHERE A.ENT_USER='"+m_username+"'  "+
					" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
					//" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // commented by udara 21-08-2014
					" AND UPPER(A.COLLECTION_OFFICER) like UPPER('%"+m_officer+"%') "+
					" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ // " AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					//" AND A.STATUS IN ('1','2')  "+ // commented by udara 26-03-2014
					" AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					" AND A.ACTIVATED_DATE <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
					" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%'  "+
					" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					" GROUP BY A.TOTAL_PERIOD ORDER BY A.TOTAL_PERIOD "+
					" ";
				
				rs=stmt.executeQuery(Sql_data);
				
				while(rs.next()){
					count_2 = rs.getInt(1);
				}
				
				
				// end by udara 31-12-2013
				
				
				if(count_2>=0){ // added by udara 31-12-2013
					
					// added by udara on 27-05-2013
					
					Sql_data = "  "+
						" SELECT "+ 
						" NVL( A.TOTAL_PERIOD,0),  "+ // 1
						" COUNT(*),  "+ // 2
						//" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (A.OTHER_CHARGES_CURR_MONTH), 0) TOTAL_ARREARS,  "+ // 3
						
						" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) total_arrears, "+ 
						
						" SUM( A.RENTAL_AMOUNT) , "+ // 4
						" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ // 5
						" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ // 6
						" SUM (A.SETTLED_AMOUNT_CUR_MON), "+ // 7   
						" NVL (SUM (A.TOTAL_AMOUNT), 0), "+ // 8
						" NVL (SUM (A.CLOSING_BALANCE), 0), "+ // 9
						" NVL (SUM (A.SETTLED_AMOUNT), 0), "+ // 10
						" NVL (SUM (A.ADJUSTED_AMOUNT), 0), "+ // 11
						" NVL(SUM(A.FUTURE_RENTAL),0), "+ // 12
						
						//" NVL (SUM(DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))),0) EARLY_SETTLE, "+ // 13
						//" NVL (SUM(DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT,0)),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
						
						// added below by udara 21-04-2015
						" NVL(SUM(A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
						" NVL(SUM(A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
						// end below by udara 21-04-2015
						
						" NVL (SUM(DECODE(SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - (A.RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)))), 1 ,(A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))),A.SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+ // 15
						" SUM(DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)) "+ // 16                
						" FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
						" WHERE A.ENT_USER='"+m_username+"'  "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
						//" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // commented by udara 21-08-2014
						" AND UPPER(A.COLLECTION_OFFICER) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ //" AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND A.STATUS IN ('1','2')  "+ // commented by udara 26-03-2014
						" AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND A.PERFORM_STATUS LIKE '"+m_perform_status+"%'  "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						"  "+m_active_status_string+"  "+ // added by udara 20-03-2015
						" GROUP BY A.TOTAL_PERIOD ORDER BY A.TOTAL_PERIOD "+
						" ";
					
					//out.println(Sql_data);
					
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
						Rent_Collec_new = Rent_Collec_new.add(Other_charges_coll_from_excess_new);
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
						
						/*
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears_new.signum()>0){
					      total_Arrears_Pres_new=total_Arrears_Pres_new.add(total_Arresrs_colle_new.multiply(new BigDecimal(100)).divide(total_Arrears_new,BigDecimal.ROUND_HALF_EVEN));
					    }
						*/
						
						closing_new = new BigDecimal(0.00);
						closing_new = closing_new.add(rs.getBigDecimal(9));
						total_Closing_new =total_Closing_new.add(closing_new);
						
						futu_rent_new = new BigDecimal(0.00);
						futu_rent_new = futu_rent_new.add(rs.getBigDecimal(12).subtract(early_sett_next_month_new));
						total_Future_ren_new = total_Future_ren_new.add(futu_rent_new);
						
						Cash_Total_new = new BigDecimal(0.00);
						Cash_Total_new = Cash_Total_new.add(closing_new).add(Arrea_Collec_new).add(Rent_Collec_new).add(futu_rent_new);
						//Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
						
						BCF_new = new BigDecimal(0.00);
						//BCF_new = BCF_new.add(totalab_new).subtract(Cash_Total_new).subtract(early_sett_new).subtract(early_sett_next_month_new).add(closing_new) ; // commented by udara on 08-07-2013
						BCF_new = BCF_new.add(totalab_new).subtract(Cash_Total_new).subtract(early_sett_new).subtract(early_sett_next_month_new) ; //BCF_new = Cash_Total_new.subtract(total_AB_new); // added by udara on 08-07-2013
						total_cash_new = total_cash_new.add(Cash_Total_new);
						total_bcf_new = total_bcf_new.add(BCF_new);
						
					}
					
					/*adding new row nad the total added by ns on 14-06-2013*/
					//total_cash = total_cash.add(total_cash_new); // commented by udara 23-10-2015
					
					// added below by udara on 14-06-2013
					
					
					/*Commented By: Samith Dilshan - #16400 - on 2015-07-27*/
/*					
					total_Arrears      = total_Arrears.add(total_Arrears_new);
					total_Due_Rental   = total_Due_Rental.add(total_Due_Rental_new);
					total_AB           = total_AB.add(total_AB_new);
					total_Ear_Sett     = total_Ear_Sett.add(total_Ear_Sett_new);
					total_early_sett_next_month = total_early_sett_next_month.add(total_early_sett_next_month_new);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(total_Other_charges_coll_from_excess_new);
					total_Rental_colle  = total_Rental_colle.add(total_Rental_colle_new);
					total_Rent          = total_Rent.add(total_Rent_new);
					total_Rental_Pres   = total_Rental_Pres.add(total_Rental_Pres_new);
					total_Arresrs_colle = total_Arresrs_colle.add(total_Arresrs_colle_new);
					//total_Arrears_Pres  = total_Arrears_Pres.add(total_Arrears_Pres_new); // commented by udara 01-07-2013
					total_Future_ren    = total_Future_ren.add(total_Future_ren_new);
					total_Closing       = total_Closing.add(total_Closing_new);
					total_bcf           = total_bcf.add(total_bcf_new);
					// end below by udara on 14-06-2013
*/
					
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
					
					
					// added by udara on 01-07-2013
					
					if(total_Arrears_new.signum()>0){
						total_Arrears_Pres_new= total_Arrears_Pres_new.add((total_Arresrs_colle_new).multiply(new BigDecimal(100)).divide(total_Arrears_new.add(total_AdjAmount_new),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle_new.signum()>0){
						
						total_Arrears_Pres_new= total_Arrears_Pres_new.add((total_Arresrs_colle_new).multiply(new BigDecimal(100)).divide(total_AdjAmount_new,BigDecimal.ROUND_HALF_EVEN));
					}
					
					// end by udara on 01-07-2013
					
					if(total_Arrears_Pres_new.signum()<0){ // 
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres_new.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres_new)+"%</b></td>"); 
					}
					
					// added by udara 19-02-2015
					
			//BigDecimal overall_collection_total = new BigDecimal(0.00);
					//if(totalab.signum()>0){
					if(total_AB_new.signum()>0){ // mod by udara 20-03-2015
						overall_collection_total = (total_Rent_new.add(total_Arresrs_colle_new)).multiply(new BigDecimal(100)).divide(total_AB_new,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}   '><b>"+nf.format(overall_collection_total)+"%</b></td>");  
					// end by udara 19-02-2015
					
					
					//total_Future_ren_new = total_Future_ren_new.add(m_new_future_rental);
					// commented by udara on 29-07-2013
					
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
					
					//total_cash_new = total_cash_new.add(m_new_future_rental);
					
					if(total_cash_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash_new)+"</b></td>"); 
					}
					
					//total_bcf_new = total_bcf_new.subtract(m_new_future_rental);
					if(total_bcf_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf_new)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");	
					
					
					
					
					
					
					// end by udara on 27-05-2013
					
					// commented by udara on 27-05-2013
					/*
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>New</b></td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  // added by udara on 02-05-2013
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >"+nf.format(m_new_future_rental)+"</td>"); 
					}
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >"+nf.format(m_new_future_rental)+"</td>"); 
					}
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental)+")</td>"); 
					}
					
					
					
					out.println("</tr>");	
					*/
					
				} // added by udara 31-12-2013	
				
				
				
				
				
				
				
				
				
				
				
				//precentage=================================================================================================
				
				
				
				//} // commented by udara 31-12-2013
				
				
				
				/*		out.println("<tr>");		
				out.println("<td  colspan=18 >"); 
						out.println("<table align=\"left\" width=300px border=\"0\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  ><b>Months</td>"); 
						out.println("<td  ><b>Targets</td>"); 
						out.println("<td  ><b>Acheivement</td>"); 
						out.println("<td  ><b>+/-</td>"); 
						out.println("</tr>");	
				*/
				
				/*
				
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=0");
						
					if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=1");
						
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\" >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=2");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=3");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=4");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=5");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\"> ");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>");  
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=6");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
					
						out.println("</tr>");		
						}
						
				*/		
				
				/*
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				
				
				
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");	
				
				out.println("<tr> "); 
				out.println("<td width='20'> ");
				out.println("</td> ");
				
				out.println("<td>");
				*/
				Sql_data=" SELECT A.PENALTY, A.INVOICE, A.RENTAL_ARREARS, A.INSURANCE, A.BBF, A.OPEN_CONTRACT_BAL "+
					" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
					" WHERE ENTER_USER='"+m_username+"' "+
					" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				
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
				
				//out.println("<table width='100%'>");
				
				// added by udara 28-11-2013
				
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
					" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+ // added by udara 28-11-2013
					" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
					" AND    A.INVOICE_NO IN "+
					" ( SELECT  INVOICE_NO "+
					" FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO LIKE '%%' "+
					" AND ACTIVE_STATUS = 'Y' "+
					//" AND INVOICE_TYPE <> 'INV_GENER' "+
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
					" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+ // added by udara 28-11-2013
					" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
					" AND    A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO LIKE '%%' "+
					" AND ACTIVE_STATUS = 'Y' "+
					//" AND INVOICE_TYPE <> 'INV_GENER' "+
					" AND ( INVOICE_TYPE <> "+
					"  'INSURANCE' "+
					" AND REMARKS <> 'CHARGES - INSURANCE' ) "+
					
					" AND VALUE_DATE > TO_DATE ('"+m_date+"','DD-MM-YYYY') "+
					" ) "+
					" AND ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_date+"','DD-MM-YYYY' ), -1 ))+ 1 ) "+
					" AND ADJUSTED_DATE <=  TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' )  "+
					" ) "+
					" "; 
				
				
				
				//double cr_note_val  = 0;
				BigDecimal cr_note_val = new BigDecimal(0.00);
				
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					cr_note_val = rs.getBigDecimal(1);//cr_note_val = rs.getDouble(1);
				}
				
				// end by udara 28-11-2013
				
				m_invoice=m_invoice.subtract(cr_note_val); // added by udara 28-11-2013
				
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
				
				
// Added By : Samith Dilshan on 2015-05-29 --------------------------------
/*			
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
				out.println("Early Settlement Next month (New)");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(nf.format(total_early_sett_next_month_new_adjustment));
				out.println("</td>");
				
				out.println("</tr>");
				

*/

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
				// commented by udara on 28-05-2013
				/*
				if(total_Arrears.subtract(m_bbf).signum() >=0 ){
					out.println(nf.format(total_Arrears.subtract(m_bbf)));
				}else{
					out.println("("+nf.format((total_Arrears.subtract(m_bbf)).negate())+")");
				}
				*/
				
				
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
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				/*if((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing)).signum() >=0 ){
					out.println(nf.format(m_bbf.subtract(total_bcf.add(total_Future_ren).add(total_Closing))));
				}else{
					out.println("("+nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).negate())+")");
				}
				*/
				// added by udara on 11-06-2013
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
				// commented by udara on 14-06-2013
				/*
				if(total_Arrears.subtract(m_bbf).signum() != 0){
					if(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN).signum() >=0 ){
						out.println(nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN)));
					}else{
						out.println("("+nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN).negate())+")");
					}
					
				} else{
					out.println("0.00");
				}
				*/
				//out.println(nf.format((m_bbf.subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(m_bbf,BigDecimal.ROUND_HALF_EVEN)));
				
				// added by udara on 14-06-2013
				/*
				if(m_open_con_bal.signum() != 0){
					out.println(nf.format((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).divide(m_open_con_bal,BigDecimal.ROUND_HALF_EVEN)));
				}
				else{
					out.println("0.00");
				}
				*/
				
				// added by udara on 09-07-2013
				
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
				
				// added by udara on 13-11-2013
				
				// commented by udara 28-11-2013
				/*
				Sql_data=" "+
					
					" SELECT SUM(ADJUSTED_AMOUNT) FROM (  "+
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE <> 'INV_GENER' "+
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
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT INVOICE_NO "+
						            " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
						            " WHERE  FINANCE_NO LIKE '%%' "+
						            " AND ACTIVE_STATUS = 'Y' "+
						            " AND INVOICE_TYPE <> 'INV_GENER' "+
						            " AND ( INVOICE_TYPE <> "+
						                            "  'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' ) "+
						           
						            " AND VALUE_DATE > TO_DATE ('"+m_date+"','DD-MM-YYYY') "+
						      " ) "+
						" AND ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_date+"','DD-MM-YYYY' ), -1 ))+ 1 ) "+
						" AND ADJUSTED_DATE <=  TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' )  "+
					" ) "+
                " "; 

				
				
				double cr_note_val  = 0;
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					cr_note_val = rs.getDouble(1);
				}
				
				*/
				
				out.println("<tr style='text-align:left;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0' colspan=4 >");
				out.println("  <b>Credit Notes (All)</b>  ");
				//out.println("</td>");
				//out.println("<td style='border-style:none;z-index: 0'>");
				//out.println("  "+nf.format(cr_note_val)+" ");
				//out.println("&nbsp;</td>"); 
				out.println("</td>");
				out.println("<td STYLE='{text-align:left;cursor:pointer;border-style:none;z-index: 0}' onclick='print_cr_note_drill();' >"); //out.println("<td style='border-style:none;z-index: 0'>");
				out.println("  "+nf.format(cr_note_val)+" ");
				out.println("</td>");
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				
				// end by udara 13-11-2013
				
				
				
				// ======================== added by udara 07-04-2014 =======================================================================
				
				double cr_note_val_rental = 0;
				double cr_note_val_arrears = 0;
				double cr_note_val_other_curr = 0;
				double cr_note_val_other_prev = 0;				
				
				Sql_data=" "+
					" select  "+
					" NVL(GET_DIFF_CR_AMOUNTS('"+m_date+"','RENTAL','"+m_username+"'),0) CR_NOTES_RENTAL, "+
					" NVL(GET_DIFF_CR_AMOUNTS('"+m_date+"','ARREARS','"+m_username+"'),0) CR_NOTES_ARREARS, "+
					" NVL(GET_DIFF_CR_AMOUNTS('"+m_date+"','OTHER_CURR','"+m_username+"'),0) CR_NOTES_OTHER_CURR, "+
					" NVL(GET_DIFF_CR_AMOUNTS('"+m_date+"','OTHER_PREV','"+m_username+"'),0) CR_NOTES_OTHER_PREV "+
					" from DUAL "+
					" ";
				
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
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
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
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
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
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Recovery Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_2_main?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_2_main?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); // commented by udara on 09-05-2013
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
				
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
					Sql_data=" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0), "+ //1
						" 1, "+ //2
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ //3 // commented by udara on 08-07-2013 // commented by udara 05-12-2013
						
						" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) total_arrears, "+ //3 added by udara 05-12-2013
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1)),'DD-MM-YYYY')),0) total_arrears, "+ 
						
						
						//" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0) total_arrears, "+ //3 // added by udara on 08-07-2013
						" NVL (A.RENTAL_AMOUNT, 0), "+ //4
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
						" A.FINANCE_NO, "+ //8
						" NVL ( A.total_amount, 0), "+ //9
						" NVL (A.CLOSING_BALANCE, 0), "+ //10
						" NVL (A.SETTLED_AMOUNT, 0), "+ //11
						" NVL ( A.ADJUSTED_AMOUNT, 0), "+ //12
						" NVL ( A.FUTURE_RENTAL, 0) "+ //13
						" ,A.CLIENT_CODE "+ //14
						" ,DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0), "+ // 15 added by udara on 02-05-2013
						
						// added by udara on 29-06-2013
						/*
						" NVL ( "+
							" DECODE( "+
								" SIGN(  ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
								" ,1 "+
								" ,RENTAL_AMOUNT, "+
								" (DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)) - NVL(SETTLED_OTH_FROM_EXCESS,0) "+
								" ,0) EARLY_SETTLE,  "+ // 16
						
							" NVL ( "+ 
									" DECODE( "+ 
													" SIGN( ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
													" ,1 "+ 
													" ,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0) -NVL(RENTAL_AMOUNT,0),  "+
													" 0)  "+  
												" ,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						*/	
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+ // 16
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount, "+	// 18
						
						"   NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) "+ // 19 added by udara on 20-11-2013
						
						
						
						" FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
						" WHERE A.ENT_USER='"+m_username+"' "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
						//" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // commented by udara 21-08-2014
						" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
						" AND A.ACTIVATED_DATE  >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE  <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						m_active_status_string +
						" ORDER BY A.TOTAL_PERIOD "; 
				}
				else {
					
					Sql_data=" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0), "+ //1
						" 1, "+ //2
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ //3 // commented by udara on 08-07-2013
						
						" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) total_arrears, "+ 
						//" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1)),'DD-MM-YYYY')),0) total_arrears, "+ 
						
						//" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0) total_arrears, "+ //3 // added by udara on 08-07-2013
						" NVL (A.RENTAL_AMOUNT, 0), "+ //4
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
						" A.FINANCE_NO, "+ //8
						" NVL ( A.total_amount, 0), "+ //9
						" NVL (A.CLOSING_BALANCE, 0), "+ //10
						" NVL (A.SETTLED_AMOUNT, 0), "+ //11
						" NVL ( A.ADJUSTED_AMOUNT, 0), "+ //12
						" NVL ( A.FUTURE_RENTAL, 0) "+ //13
						" ,A.CLIENT_CODE "+ //14
						" ,DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0), "+ // 15 added by udara on 02-05-2013
						
						// added by udara on 29-06-2013
						/*
						" NVL ( "+
							" DECODE( "+
								" SIGN(  ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
								" ,1 "+
								" ,RENTAL_AMOUNT, "+
								" (DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)) - NVL(SETTLED_OTH_FROM_EXCESS,0) "+
								" ,0) EARLY_SETTLE,  "+ // 16
								*/
						
						// commented by udara on 02-07-2013
						/*
						" NVL ( "+
							" DECODE(  "+
								" SIGN(  ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0))  "+
								" ,1 "+ 
								" ,RENTAL_AMOUNT + NVL(SETTLED_OTH_FROM_EXCESS,0),  "+
								" (TOTAL_AMOUNT*-1) + NVL(SETTLED_OTH_FROM_EXCESS,0))  "+ 
								" ,0) EARLY_SETTLE, "+ // 16 mod by udara 02-07-2013
						
							" NVL ( "+ 
									" DECODE( "+ 
													" SIGN( ((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-NVL(SETTLED_OTH_FROM_EXCESS,0)) -NVL(RENTAL_AMOUNT,0)) "+
													" ,1 "+ 
													" ,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1) - NVL(SETTLED_OTH_FROM_EXCESS,0) -NVL(RENTAL_AMOUNT,0),  "+
													" 0)  "+  
												" ,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						*/
						
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+ // 16
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						
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
						" ,0) Rental_amount, "+	// 18
						
						"   NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) "+ // 19 added by udara on 20-11-2013
						
						
						
						" FROM "+m_schema_name+".AF_RE_TBD_RECOVERY_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
						" WHERE A.ENT_USER='"+m_username+"' "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
						//" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // commented by udara 21-08-2014
						" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('"+m_location+"%') "+ // " AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						" AND A.TOTAL_PERIOD = '"+m_slab+"' "+
						//" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
						" AND A.ACTIVATED_DATE  < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						////" AND TRUNC(A.ENT_DATE) = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						m_active_status_string +
						" ORDER BY A.TOTAL_PERIOD "; 
					
				}
				
				
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
				
				// end by udara 18-11-2013
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
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
				
				/*		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						
					
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Executive</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Branch</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_location_desc+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_start_date+"</td>"); 
						out.println("<td width=\"50\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_end_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
					*/	
				
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
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2'><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>Finance Number</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"10\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modified by udara on 02-05-2013 from 8 columns to 9
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>SLAB</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Finance Number</b></td>"); 
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
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				
				
				//double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				//double  m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				//out.println("<table id=mytable align=\"left\" width=1750px border=\"1\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013   
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(12)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
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
					
					// commnted by udara on 08-07-2013
					/*
					if (rs.getBigDecimal(9).doubleValue() <0)
					{
						if(rs.getBigDecimal(9).negate().doubleValue() > rs.getDouble(4))
						{ 
							early_sett = early_sett.add(rs.getBigDecimal(4));
							early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(9).negate().subtract(rs.getBigDecimal(4)));
						}
						else
						{
							early_sett = early_sett.add(rs.getBigDecimal(9).negate());
							
						}
					}	
					
					else
					{
						early_sett = new BigDecimal(0.00) ;
					}
					*/
					
					// added by udara on 08-07-2013
					
					early_sett = early_sett.add(rs.getBigDecimal(16));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(17));
					
					
					// commented by udara on 29-06-2013
					/*
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
					*/
					
					// added by udara on 29-06-2013
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
					
					
					
					// added by udara on 02-015-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(15));
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					
					// end by udara on 02-015-2013
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5).subtract(rs.getBigDecimal(11)));
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5));
					
					
					
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					
					//Rent_Collec = Rent_Collec.add(Other_charges_coll_from_excess); // commented by udara on 02-07-2013 // added by udara on 02-05-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 03-07-2013//added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 03-07-2013
					
					
					//if(rs.getDouble(4) < tot_rent.doubleValue()){
					//futu_rent = futu_rent.add( new BigDecimal(tot_rent.doubleValue()-rs.getDouble(4)));
					//futu_rent = futu_rent.add(rs.getBigDecimal(13));
					// futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(early_sett_next_month));  // commented by udara on 03-07-2013/*added by ns on 23/11/2012*/
					//}
					
					futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(rs.getBigDecimal(17)));  // added by udara on 03-07-2013
					
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
					closing = closing.add(rs.getBigDecimal(10));
					Cash_Total = new BigDecimal(0.00);
					//Cash_Total = Cash_Total.add(rs.getBigDecimal(7));
					//futu_rent =  Cash_Total.subtract(closing).subtract(Arrea_Collec).subtract(Rent_Collec);
					
					
					// commented by udara on 29-07-2013
					/*
					if(futu_rent.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					*/
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							closing = closing.add(futu_rent);
							//futu_rent = new BigDecimal(0.00);
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
					// end by udara on 29-07-2013
					
					
					//Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent); // commented by udara on 29-07-2013
					
					// added by udara on 29-07-2013
					//if((futu_rent.signum()<0) && (closing.signum()>0)){ // commented by udara 05-11-2013
					if((futu_rent.signum()<= 0) && (closing.signum()>= 0)){ // added by udara 05-11-2013
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					// end by udara on 29-07-2013
					
					// Cash_Total = Cash_Total.subtract(rs.getBigDecimal(19)); // added by udara on 20-11-2013 // commented by udara 26-11-2013
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(closing).subtract(futu_rent) ;
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing); // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month); // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					// added by udara on 29-07-2013
					/*
					if((futu_rent.signum()<0) && (closing.signum()>0)){
						BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).subtract(futu_rent); 
					}
					else{
						BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month); 
					}
					*/
					
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
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					if(rs.getBigDecimal(3).signum()>=0){
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(12)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(12));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					
					// commented below by udara 29-06-2013
					//total_Ear_Sett = total_Ear_Sett.add(early_sett);
					//total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(early_sett_next_month);
					
					// added below by udara 29-06-2013
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
					
					
					// added by udara on 01-07-2013
					
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
					
					// end by udara on 01-07-2013
					
					
					//if(total_Arrears_Pres.signum()<0){
					if(total_Arrears_pre.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					// commented by udara on 29-07-2013
					/*
					if(total_Future_ren.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren)+"</b></td>"); 
					}
					*/
					
					// added by udara 19-02-2015
					
					BigDecimal overall_collection_total = new BigDecimal(0.00);
					if(totalab.signum()>0){
						overall_collection_total = (total_Rent.add(total_Arresrs_colle)).multiply(new BigDecimal(100)).divide(total_AB,BigDecimal.ROUND_HALF_EVEN);
					}
					else{
						overall_collection_total = new BigDecimal(0.00);
					}
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(overall_collection_total)+"%</b></td>");  
					// end by udara 19-02-2015
					
					// added by udara on 29-07-2013
					
					if(total_Future_ren_show.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_show.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_show)+"</b></td>"); 
					}
					// end by udara on 29-07-2013
					
					
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
				
				
				
				/*		out.println("<tr>");		
				out.println("<td  colspan=18 >"); 
						out.println("<table align=\"left\" width=300px border=\"0\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  ><b>Months</td>"); 
						out.println("<td  ><b>Targets</td>"); 
						out.println("<td  ><b>Acheivement</td>"); 
						out.println("<td  ><b>+/-</td>"); 
						out.println("</tr>");	
				*/
				
				/*
				
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=0");
						
					if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=1");
						
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\" >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=2");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=3");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=4");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=5");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\"> ");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>");  
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=6");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
					
						out.println("</tr>");		
						}
						
				*/		
				
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
			

			
			//}
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
