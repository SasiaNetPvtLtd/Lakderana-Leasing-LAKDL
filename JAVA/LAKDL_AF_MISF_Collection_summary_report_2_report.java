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



public class LAKDL_AF_MISF_Collection_summary_report_2_report extends javax.servlet.http.HttpServlet { 
	
	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		
		ServletOutputStream out = null;
		Connection conn = null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null, stmt2=null, stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null, rs3=null;
		
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
				
				stmt3 = conn.createStatement ();
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_region="";
				
				String  m_active_status = ""; // added by udara 07-04-2015
				String  m_active_status_string = ""; // added by udara 07-04-2015
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara 07-04-2015
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				// end by udara 07-04-2015
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				/*added by ns on 07-01-2012*/
				//out.println("function show_branch_level_drill_down(mm_location){ ");  // commented by udara 13-08-2015
				out.println("function show_branch_level_drill_down(mm_location,mm_region){ "); // added by udara 13-08-2015
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location;"); // commented by udara 07-04-2015
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location+'&active_status="+m_active_status+"';");  // added by udara 07-04-2015
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location+'&active_status="+m_active_status+"'+'&m_region='+mm_region;"); 
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
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				//Added by Dineth on 2008-11-17
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				
				//End by Dineth on 2008-11-17
				

				
				String Sql_data="";
				
				
				Sql_data = "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS ),"+
					"            A.LOCATION  LOCATION, "+
					"            SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ), "+
					"            NVL (A.REGIONS_CODE, '-') REGIONS_CODE "+ // Added By: Samith Dilshan on 2015-06-08 for Region Code
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"' ";
					
					
					if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
						Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
					}
					
					Sql_data = Sql_data + "    "+m_active_status_string+"   "+ // added by udara 07-04-2015
					" GROUP BY   A.LOCATION , REGIONS_CODE  ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') ";
					
					
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				
				//out.print(Sql_data);
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<br>");
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: 9pt arial; text-align:laft;}'   >Region : "+rs3.getString(1)+"</td>"); 
						out.println("</tr >");
						out.println("</table >");
					}
				}
				
				out.println("<br>");
				out.println("<br>");
				

				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}else{
					
					out.println("<table  cellspacing=0 > "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> "); 
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>BRANCH</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // Added by udara 09-04-2014
					
					// Added By: Samith Dilshan
					//out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>REGION</b></td>"); 
					
					out.println("</tr >");
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 09-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 09-04-2014
					
					// Added By: Samith Dilshan
					//out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						//out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('"+rs.getString("LOCATION")+"')\" ><b>"+rs.getString(1)+"</b></td>"); // commented by udara 13-08-2015
						out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('"+rs.getString("LOCATION")+"','"+m_region+"')\" ><b>"+rs.getString(1)+"</b></td>");  // added by udara 13-08-2015
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;  }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
							/*.add(rs.getBigDecimal(4))
							.add(rs.getBigDecimal(6))
							.add(rs.getBigDecimal(8))
							.add(rs.getBigDecimal(10))
							.add(rs.getBigDecimal(12))
							.add(rs.getBigDecimal(14))
							.add(rs.getBigDecimal(16))
							.add(rs.getBigDecimal(18));*/
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21))+"</td>"); // total
						m_total = m_total.add(rs.getBigDecimal(22));
							/*.add(rs.getBigDecimal(5))
							.add(rs.getBigDecimal(7))
							.add(rs.getBigDecimal(9))
							.add(rs.getBigDecimal(11))
							.add(rs.getBigDecimal(13))
							.add(rs.getBigDecimal(15))
							.add(rs.getBigDecimal(17))
							.add(rs.getBigDecimal(19));*/
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); // total to date
					
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 09-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 09-04-2014
						
						// Added By: Samith Dilshan on 2015-06-08
						//out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' ><b>"+rs.getString(23)+"</b></td>");
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					BigDecimal m_total_to_date_new = new BigDecimal(0.00); // added by udara obn 10-07-2013
					m_total_to_date_new = m_total_to_date_new.add(m_total_penalty_to_date).add(m_total_invoice_to_date).add(m_total_ins_invoice_to_date).add(m_total_ren_invoice_to_date).add(m_total_insurence_to_date).add(m_total_arrears_to_date).add(m_total_closing_to_date).add(m_total_rental_to_date).add(m_total_excess_to_date);
					
					// added by udara 09-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date_new.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 09-04-2014
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); // commented by udara on 10-07-2013
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new)+"</b></td>");  // added by udara on 10-07-2012
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 09-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 09-04-2014
					
					out.println("</tr >");
					

					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
				}
				
				
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
				else if(m_chksql.equals("print_report_new_drill_level_01")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String mm_location="";
				
				String m_active_status = ""; // added by udara 07-04-2015
				String m_active_status_string = ""; // added by udara 07-04-2015
				
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				/*if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				*/
				
				// added by udara 07-04-2015
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				// end by udara 07-04-2015
				
				// added by udara 13-08-2015
				String m_region = "";
				String m_region_string = "";
				if(req.getParameter("m_region")!=null ){
					m_region = req.getParameter("m_region").trim();
				}
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
						m_region_string = "    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				// end by udara 13-08-2015
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				if(req.getParameter("location")!=null ){
					mm_location=req.getParameter("location").trim();
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				//Added by Dineth on 2008-11-17
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
				String Sql_data="";
				
				
				Sql_data="  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS )"+
					"            ,FINANCE_NO ,CLIENT_CODE, "+
						"         SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ) "+
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					"    AND     A.LOCATION = '"+mm_location+"' "+
					"     "+m_active_status_string+"       "+ // added by udara 07-04-2015
					"    "+m_region_string+"  "+ // added by udara 13-08-2015
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') "+
					""+
					"";
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report Branch - "+mm_location+"  </u></td>"); 
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
				}else{
					
					out.println("<table  cellspacing=0 > "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> "); 
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Finance No</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // added by udara 10-09-2014
					
					
					out.println("</tr >");
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 10-04-2014
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						out.println("<td class=factoring-letter-body onClick=\"show_transaction_history_new('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\"  STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString("FINANCE_NO")+"</b></td>"); 
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); 
						m_total = m_total.add(rs.getBigDecimal(23));
							
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23))+"</td>"); 
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 10-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 10-04-2014
						
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					// added by udara 10-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 10-04-2014
					
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					
					out.println("</tr >");
					
					
					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
				}
				
				
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			//}
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
