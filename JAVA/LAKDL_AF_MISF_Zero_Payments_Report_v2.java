//--
//SCREEN NAME:
//CREATED BY : KANISHKA DILSHAN
//DATE/TIME  : 24-MAY-2017
//NOTES      : Previous Version can be found LAKDL_AF_MISF_Zero_Payments_Report.java

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Zero_Payments_Report_v2 extends javax.servlet.http.HttpServlet { 
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null;
		
		
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
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date 			= req.getParameter("date");
				String m_location_id 	= req.getParameter("location_id");
				String m_perform_status = req.getParameter("n_perform_status");
				String m_rendate  		= req.getParameter("rendate").trim();
				String m_from_date="";
				String m_to_date="";
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				
				try{
					
					// commented by udara 26-09-2017
					
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ZERO_PAYMENTS_V2(:1,:2,:3,:4,:5,:6,:7);END;");
					callstmt1.setString(1,m_rendate); 
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_username);
					callstmt1.setString(4,m_perform_status); 
					callstmt1.setString(5,m_date); 
					callstmt1.setString(6,m_from_date); 
					callstmt1.setString(7,m_to_date); 
					callstmt1.execute();
					out.print("OK"); 
					
					
					// added by udara 26-09-2017
					/*
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ZERO_PAYMENTS_V3(:1,:2,:3,:4,:5);END;");
					callstmt1.setString(1,m_rendate); 
					callstmt1.setString(2,m_from_date); 
					callstmt1.setString(3,m_to_date); 
					callstmt1.setString(4,m_location_id);
					callstmt1.setString(5,m_username);
					callstmt1.execute();
					out.print("OK");
					*/
					
					
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			/*if(m_chksql.equals("print_report_new")){		
				String m_period = "";
				String m_period_2 = "";
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba="";
				String m_rendate=""; 
				
				String m_from_date="";
				String m_to_date="";
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				
				String m_region = "";
				
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}
				
				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				String m_possitive_status = req.getParameter("possitive_status");
				String m_possitive_status_str = "";
				
				if(m_possitive_status.equals("ALL"))
					m_possitive_status_str = " ";
				else if(m_possitive_status.equals("POSSITIVE"))
					m_possitive_status_str = " AND TOTAL_AMOUNT  >= 0 "; 
				else if(m_possitive_status.equals("NEGATIVE"))
					m_possitive_status_str = " AND TOTAL_AMOUNT  < 0  "; 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Zero Payments Report</TITLE>"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				out.println("function update_contract_detail(val_1){ ");  
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
				
				stmt = conn.createStatement ();
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
				}
				
				String Sql_data="";
				
				Sql_data="  WITH T AS( "+
					" SELECT  "+
					" FINANCE_NO, "+ 	//1
					" CLIENT_CODE, "+ 	//2
					" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
					" VALUE_DATE ,"+ 						//4
					" AGE_NEW, "+ 							//5
					" CLIENT_TEL_NO ,"+	 					//6
					" DUE_RENTAL_AMOUNT ,"+ 				//7
					" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(APPLICATION_NO,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
					" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+ 								   //9 
					" NVL("+m_schema_name+".AF_GET_COLL_OFFICER_2(APPLICATION_NO),'-') COLL_OFICER, "+ 								   //10
					" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') VEHICLE_NO, "+  								   //11
					" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+  //12 
					" TOTAL_AMOUNT "+ 						//13
					" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS "+
					" WHERE ENT_USER='"+m_username+"' ";
				
				if(!(m_officer.equals(""))){  
					Sql_data += " AND COLLECTION_OFFICER = '"+m_officer+"'";
				}
				if(!(m_location.equals(""))){  
					Sql_data += " AND LOCATION_CODE = '"+m_location+"'";
				}
				if(!(m_region.equals("NOT_SELECT"))){   
					Sql_data = Sql_data +"  AND  REGION_CODE = '"+m_region+"' ";    
				}
				
				Sql_data = Sql_data + "  "+m_possitive_status_str+" "+m_active_status_string +
					
					" ) "+
					" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ 
					" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_to_date+"'),'-') "+ 
					" FROM T "+
					" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
					
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
				
				
				
				
				out.println("<!--"+Sql_data+"-->");
				rs=stmt.executeQuery(Sql_data);
				
				more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Zero Payment Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_rendate+"</td>");   // m_date // mod by udara 25-01-2016
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				
				//End by Dineth on 2008-11-17
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
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Status</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_possitive_status+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_from_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_to_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				// end by udara 17-08-2015
				
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
					}
				}
				
				
				out.println("</table>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 03-10-2013
				out.println("<td width=\"7%\"  align='center' >R</td>"); // added by udara on 26-12-2013
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  ' onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '   onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '       onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '          onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '  onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Rental</p></b></td>");  // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Arrears</p></b></td>"); // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Prev Collection Oficer</b></td>");//ADDED BY LALANKA ON 15-07-2009
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Current Collection Oficer</b></td>"); // added by udara on 03-10-2013
				out.println("<td class=factoring-letter-body ><b> Last Payment Date</b></td>"); // added by udara 26-12-2013
				out.println("</tr >");
				
				
				
				
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0,sub_close=0,sub_open=0;
				
				
				
				double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				double  m_total_rental=0,m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				
				String row_colour = "white"; 
				double period_val = 0;
				
				while(more){
					
					period_val = Math.round(rs.getDouble(5));
					
				
				
					
					if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
						m_total_rental =rs.getDouble(14); 
						out.println("<TR >");	
						
						
						
						out.println("<tr  id=tr_id"+j+">"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString(3)+"','"+rs.getString(2)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(2)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(11)+"</td>"); //thamali 2012.03.29
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString(3)+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(4)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString(6)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(12)+"</td>");//Added By Prabash on 09-08-2012
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(7))+"</td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
						
						// thamali 2012.03.29
						if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
							out.println("<td  class=factoring-letter-body    >NR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
							out.println("<td  class=factoring-letter-body    >RR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
							out.println("<td  class=factoring-letter-body    >CV</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 4 ){
							out.println("<td  class=factoring-letter-body   >Z</td>"); 
						}
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(13))+"</td>");			
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble(5))+"</td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>Remarks</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString(2)+"')\"><u>"+rs.getString(8)+"</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(10)+"</td>");//Added By Lalanka on 15-07-2009
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(16)+" </td>"); // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(17)+" </td>"); // added by udara 26-12-2013
						out.println("</tr>");
						total_mon_rental=total_mon_rental+rs.getDouble(7);
						total_open_bal=total_open_bal+rs.getDouble(13);
						j+=1;
					}
					else if( rs.getString(2)==null && rs.getString(1).equals("SUB TOTAL")){
						out.println("<tr>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(14))+" </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(15))+" </td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 26-12-2013
						out.println("</tr>");
						
					}
					
					
					more=rs.next();
					count+=1;
					
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 03-10-2013
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 26-12-2013
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					
					
				}
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}*/
			
			if(m_chksql.equals("print_report_new")){		
				String m_period = "";
				String m_period_2 = "";
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba="";
				String m_rendate=""; 
				
				String m_from_date="";
				String m_to_date="";
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				
				String m_region = "";
				
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}
				
				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				String m_possitive_status = req.getParameter("possitive_status");
				String m_possitive_status_str = "";
				
				if(m_possitive_status.equals("ALL"))
					m_possitive_status_str = " ";
				else if(m_possitive_status.equals("POSSITIVE"))
					m_possitive_status_str = " AND TOTAL_AMOUNT  >= 0 "; 
				else if(m_possitive_status.equals("NEGATIVE"))
					m_possitive_status_str = " AND TOTAL_AMOUNT  < 0  "; 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Zero Payments Report</TITLE>"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				out.println("function update_contract_detail(val_1){ ");  
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
				
				stmt = conn.createStatement ();
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
				}
				
				String Sql_data="";
				
				/*Sql_data="  WITH T AS( "+
					" SELECT  "+
					" FINANCE_NO, "+ 	//1
					" CLIENT_CODE, "+ 	//2
					" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
					" VALUE_DATE ,"+ 						//4
					" AGE_NEW, "+ 							//5
					" CLIENT_TEL_NO ,"+	 					//6
					" DUE_RENTAL_AMOUNT ,"+ 				//7
					" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(APPLICATION_NO,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
					" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+ 								   //9 
					" NVL("+m_schema_name+".AF_GET_COLL_OFFICER_2(APPLICATION_NO),'-') COLL_OFICER, "+ 								   //10
					" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') VEHICLE_NO, "+  								   //11
					" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+  //12 
					" TOTAL_AMOUNT "+ 						//13
					" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS "+
					" WHERE ENT_USER='"+m_username+"' ";
				
				
				
				
				
				if(!(m_officer.equals(""))){  
					Sql_data += " AND COLLECTION_OFFICER = '"+m_officer+"'";
				}
				if(!(m_location.equals(""))){  
					Sql_data += " AND LOCATION_CODE = '"+m_location+"'";
				}
				if(!(m_region.equals("NOT_SELECT"))){   
					Sql_data = Sql_data +"  AND  REGION_CODE = '"+m_region+"' ";    
				}
				
				Sql_data = Sql_data + "  "+m_possitive_status_str+" "+m_active_status_string +
					
					" ) "+
					" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ 
					" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_to_date+"'),'-') "+ 
					" FROM T "+
					" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
					
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    */
				
				Sql_data=" SELECT Q.VALUE_DATE , "+
					" Q.FINANCE_NO, "+
					" Q.CLIENT_CODE, "+
					" Q.APPLICATION_NO, "+
					" Q.DUE_RENTAL_AMOUNT, "+
					" Q.TOTAL_AMOUNT, "+
					" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') VEHICLE_NO, "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','CLIENT_FULL_NAME'), "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','CLIENT_TEL_NO'), "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','CLIENT_TEL_NO'), "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT_AMT(FINANCE_NO,'"+m_username+"','TOTAL_PERIOD'), "+
					" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(APPLICATION_NO,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK, "+
					//" NVL("+m_schema_name+".AF_GET_COLL_OFFICER_2(APPLICATION_NO),'-') COLL_OFICER, "+ // commented by udara 25-09-2017
					" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(APPLICATION_NO),'-') COLL_OFICER, "+ // added by udara 25-09-2017
					" NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_to_date+"'),'-'), "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT_AMT(FINANCE_NO,'"+m_username+"','AGE'), "+					
					" "+m_schema_name+".Get_Values_Zero_Payment(Finance_No,'"+m_username+"','COLLECTION_OFFICER')Col_Officer, "+
					" "+m_schema_name+".Get_Values_Zero_Payment(Finance_No,'"+m_username+"','LOCATION_CODE')Location_Code, "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','REGION_CODE')REGION_CODE, "+
					" "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','ENTUSER')REGION_CODE, "+
					" "+m_schema_name+".Get_Values_Zero_Payment(Finance_No,'"+m_username+"','ARREARSSTATUS')Region_Code "+
					" FROM "+
					" (SELECT P.VALUE_DATE , "+
					" P.FINANCE_NO, "+
					" P.CLIENT_CODE, "+
					" P.APPLICATION_NO, "+
					" P.DUE_RENTAL_AMOUNT, "+
					" P.TOTAL_AMOUNT "+
					" FROM "+
					" (SELECT VALUE_DATE ,"+ 
					" FINANCE_NO,"+ 
					" CLIENT_CODE, "+
					" APPLICATION_NO, "+
					" NVL( SUM (NVL(DUE_RENTAL_AMOUNT,0)),0) DUE_RENTAL_AMOUNT, "+
					" NVL(SUM (NVL(TOTAL_AMOUNT,0)),0) TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS "+
					" WHERE ENT_USER='"+m_username+"' ";
				
				if(!(m_officer.equals(""))){  
					Sql_data += " AND "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(Finance_No,'"+m_username+"','COLLECTION_OFFICER') = '"+m_officer+"'";
				}
				if(!(m_location.equals(""))){  
					Sql_data += " AND "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(Finance_No,'"+m_username+"','LOCATION_CODE') = '"+m_location+"'";
				}
				if(!(m_region.equals("NOT_SELECT"))){   
					Sql_data = Sql_data +" AND  "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','REGION_CODE') = '"+m_region+"' ";    
				}
				if(!m_possitive_status.equals("ALL")){
					
					Sql_data = Sql_data +" AND  "+m_schema_name+".Get_Values_Zero_Payment(Finance_No,'"+m_username+"','ARREARSSTATUS') = '"+m_possitive_status+"' ";    
				}
				
				// added by udara 30-08-2017
				if(!m_active_status.equals("A")){
					Sql_data = Sql_data + " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; 
				}
				// end by udara 30-08-2017
				
				
				Sql_data +=
					" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO, CLIENT_CODE,APPLICATION_NO) "+
					" ) P "+
					" WHERE P.CLIENT_CODE  IS NOT NULL "+
					" AND P.APPLICATION_NO IS NOT NULL "+
					" UNION "+
					" SELECT P.VALUE_DATE , "+
					" P.FINANCE_NO, "+
					" P.CLIENT_CODE, "+
					" P.APPLICATION_NO, "+
					" P.DUE_RENTAL_AMOUNT, "+
					" P.TOTAL_AMOUNT "+
					" FROM "+
					" (SELECT VALUE_DATE , "+
					" FINANCE_NO, "+
					" CLIENT_CODE, "+
					" APPLICATION_NO, "+
					" NVL( SUM (NVL(DUE_RENTAL_AMOUNT,0)),0) DUE_RENTAL_AMOUNT, "+
					" NVL(SUM (NVL(TOTAL_AMOUNT,0)),0) TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS "+
					" WHERE ENT_USER='"+m_username+"' ";
				
				if(!(m_officer.equals(""))){  
					Sql_data += " AND "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(Finance_No,'"+m_username+"','COLLECTION_OFFICER') = '"+m_officer+"'";
				}
				if(!(m_location.equals(""))){  
					Sql_data += " AND "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(Finance_No,'"+m_username+"','LOCATION_CODE') = '"+m_location+"'";
				}
				if(!(m_region.equals("NOT_SELECT"))){   
					Sql_data = Sql_data +" AND  "+m_schema_name+".GET_VALUES_ZERO_PAYMENT(FINANCE_NO,'"+m_username+"','REGION_CODE') = '"+m_region+"' ";    
				}
				if(!m_possitive_status.equals("ALL")){
				  
					Sql_data = Sql_data +" AND  "+m_schema_name+".Get_Values_Zero_Payment(Finance_No,'"+m_username+"','ARREARSSTATUS') = '"+m_possitive_status+"' ";    
				}
				
				// added by udara 30-08-2017
				if(!m_active_status.equals("A")){
					Sql_data = Sql_data + " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; 
				}
				// end by udara 30-08-2017
				
				Sql_data +=
					" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO, CLIENT_CODE,APPLICATION_NO) "+
					" ) P "+
					
					" WHERE P.CLIENT_CODE IS NULL "+
					" AND P.FINANCE_NO    IS NULL "+
					" ) Q "+
					" ORDER BY Q.VALUE_DATE , "+
					" Q.FINANCE_NO, "+
					" Q.CLIENT_CODE, "+
					" Q.Application_No ";
				
				
				
				
				out.println("<!--"+Sql_data+"-->");
				rs=stmt.executeQuery(Sql_data);
				
				more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Zero Payment Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_rendate+"</td>");   // m_date // mod by udara 25-01-2016
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				
				//End by Dineth on 2008-11-17
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
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Status</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_possitive_status+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_from_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_to_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				// end by udara 17-08-2015
				
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
					}
				}
				
				
				out.println("</table>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 03-10-2013
				out.println("<td width=\"7%\"  align='center' >R</td>"); // added by udara on 26-12-2013
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  ' onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '   onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '       onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '          onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '  onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Rental</p></b></td>");  // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Arrears</p></b></td>"); // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Prev Collection Oficer</b></td>");//ADDED BY LALANKA ON 15-07-2009
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Current Collection Oficer</b></td>"); // added by udara on 03-10-2013
				out.println("<td class=factoring-letter-body ><b> Last Payment Date</b></td>"); // added by udara 26-12-2013
				out.println("</tr >");
				
				
				
				
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0,sub_close=0,sub_open=0;
				
				
				
				double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				double  m_total_rental=0,m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				
				String row_colour = "white"; 
				double period_val = 0;
				
				while(more){
					
					//period_val = Math.round(rs.getDouble(11));//5
					
					/*
					if((period_val>=3) && (period_val<6))
						row_colour = "CCFFFF"; // lightblue
					else if(period_val>=6)
						row_colour = "FFCCFF"; //"FF6699" // red
					else
						row_colour = "FFFFFF";
					*/
					
					//if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
					if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
						//m_total_rental =rs.getDouble(14)//; 
						out.println("<TR >");	
						
						
						
						out.println("<tr  id=tr_id"+j+">"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString(3)+"','"+rs.getString(2)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(2)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(7)+"</td>"); //11
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString(3)+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(8)+"</u></td>"); // mod by udara 25-09-2017 by replacing rs.getString(3) 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString(9)+"</td>"); //6
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(11)+"</td>");//12
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(5))+"</td>"); //7
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
						
						// thamali 2012.03.29
						if(Math.round(rs.getDouble(15)) >= 0 && Math.round(rs.getDouble(15)) < 1){//5
							out.println("<td  class=factoring-letter-body    >-</td>"); 
						}
						else if(Math.round(rs.getDouble(15)) >= 1 && Math.round(rs.getDouble(15)) < 2 ){
							out.println("<td  class=factoring-letter-body    >NR</td>"); 
						}
						else if(Math.round(rs.getDouble(15)) >= 2 && Math.round(rs.getDouble(15)) < 3 ){
							out.println("<td  class=factoring-letter-body    >RR</td>"); 
						}
						else if(Math.round(rs.getDouble(15)) >= 3 && Math.round(rs.getDouble(15)) < 4 ){
							out.println("<td  class=factoring-letter-body    >CV</td>"); 
						}
						else if(Math.round(rs.getDouble(15)) >= 4 ){
							out.println("<td  class=factoring-letter-body   >Z</td>"); 
						}
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(6))+"</td>");	//13		
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble(11))+"</td>");//5
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>Remarks</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString(2)+"')\"><u>"+rs.getString(12)+"</u></td>");//8
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(13)+"</td>");//10
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(13)+" </td>"); // 16
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(14)+" </td>"); // 17 
						out.println("</tr>");
						//total_mon_rental=total_mon_rental+rs.getDouble(5);//7
						//total_open_bal=total_open_bal+rs.getDouble(6);//13
						j+=1;
					}
					else if( rs.getString(2)==null && rs.getString(3)==null){
						out.println("<tr>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(5))+" </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(6))+" </td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("</tr>");
						
					}
					
					//total============================
					
					 if(rs.getString(1)==null){
						out.println("<tr>");		
						out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
						out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
						out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(rs.getDouble(5))+"</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(rs.getDouble(6))+"</td>"); 
						out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
						out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 03-10-2013
						out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 26-12-2013
						out.println("</tr>");		
						
						
						//precentage=================================================================================================
						
						
						
					}
					more=rs.next();
					count+=1;
					
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			// added by udara 26-09-2017
			
			else if(m_chksql.equals("print_report_new_logic")){	
				
				String m_period = "";
				String m_period_2 = ""; 
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba=""; 
				String m_rendate=""; 
				String m_active_status = "";
				String m_active_status_string = "";
				String m_possitive_status = "";
				String m_possitive_status_string = "";
				
				String m_ins_level = "";
				if(req.getParameter("ins_level") != null ){
					m_ins_level = req.getParameter("ins_level").trim();
				}
				
				String m_due_month = "";
				if(req.getParameter("due_month") != null ){
					m_due_month = req.getParameter("due_month").trim();
				}
				
				String m_due_year = "";
				if(req.getParameter("due_year") != null ){
					m_due_year = req.getParameter("due_year").trim();
				}
				
				
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				
				String m_region = "";
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
				}
				String cr_officer = "";
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}

				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}

				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}

				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}

				if(req.getParameter("cr_officer")!=null ){
					cr_officer=req.getParameter("cr_officer").trim();
				}
				
				if(req.getParameter("possitive_status")!=null ){
					m_possitive_status=req.getParameter("possitive_status").trim();
				}
				
				if(m_possitive_status.equals("ALL"))
					m_possitive_status_string = " ";
				else if(m_possitive_status.equals("POSSITIVE"))
					m_possitive_status_string = " AND TOTAL_AMOUNT > 0 ";
				else if(m_possitive_status.equals("NEGATIVE"))
					m_possitive_status_string = " AND TOTAL_AMOUNT < 0 ";
				
				

				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				stmt = conn.createStatement ();
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}

				String m_report_run_date = "";
				
				rs1 = stmt.executeQuery(" "+
					" SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
					" FROM "+m_schema_name+".ARREARS_REPORT_RUN_LOG "+
					" WHERE POINT = 'FINISH' ");
				
				if(rs1.next()){
					m_report_run_date = rs1.getString(1);
				}
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
				}
				
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Zero Payment Report</TITLE>"); 
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

				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new_logic&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&period="+m_period+"&period_2="+m_period_2+"&cr_officer="+cr_officer+"&active_status="+m_active_status+"&region="+m_region+"&perform_status="+m_perform_status+"&due_month="+m_due_month+"&due_year="+m_due_year+"&ins_level="+m_ins_level+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				
				out.println("  window.location.href=m_url;"); 
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

				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				
				
				String Sql_data="";
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Zero Payment Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_date+"</td>"); 
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


				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>");  
						out.println("</tr>");
					}
				}
				
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Status</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_possitive_status+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Last Report Run Time</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_report_run_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");

				out.println("</table>");
				
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 03-10-2013
				out.println("<td width=\"7%\"  align='center' >R</td>"); // added by udara on 26-12-2013
				//out.println("<td width=\"7%\"  align='center' >S</td>");//  added by A S Silva 03/-6/2015
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  '   onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '     onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '         onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '            onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'       onclick=sort_data('TOTAL_PERIOD') ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '    onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('DUE_RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    ><b><p>Total Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('TOTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    ><b><p>Total Arrears</p></b></td>");
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_NEW') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body ><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body ><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('CURR_COLL_OFFICER') ><b> Credit Officer </b></td>");//ADDED BY LALANKA ON 15-07-2009  Prev Collection Oficer
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('COLL_OFFICER') ><b>Current Collection Oficer</b></td>"); // added by udara on 03-10-2013
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('LAST_PAY_DD') ><b>Last Payment Date</b></td>"); // added by udara 26-12-2013
				//out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('LAST_PAY_AMT') ><b>Last Paid Amount</b></td>"); // 
				out.println("</tr >");
				
				// set search criterias
				
				String sql_m_slba = " ";
				if((m_slba==null) || (m_slba.equals(""))){
					sql_m_slba = " ";
				}
				else{
					sql_m_slba = " AND TOTAL_PERIOD  = UPPER('"+m_slba+"') ";
				}
				
				String sql_m_period = "  ";
				if((m_period.equals("")) && (m_period_2.equals(""))){
					sql_m_period = " ";
				}
				else if((!m_period.equals("")) && (!m_period_2.equals(""))){
					sql_m_period = " AND ROUND(AGE_NEW) >= '"+m_period+"' AND ROUND(AGE_NEW) <= '"+m_period_2+"' ";
				}
				else if((!m_period.equals("")) && (m_period_2.equals(""))){	
					sql_m_period = " AND ROUND(AGE_NEW) = '"+m_period+"'  "; 
				}
				else if((m_period.equals("")) && (!m_period_2.equals(""))){				
					sql_m_period = " AND ROUND(AGE_NEW) = '"+m_period_2+"'  "; 
				}
				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}
				
				String sql_m_ins_level = "  ";
				if(m_ins_level.equals("FIRST")){
					sql_m_ins_level = " AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK_2(FINANCE_NO,'"+m_due_month+"','"+m_due_year+"') > 0 "; 
				}
				else if(m_ins_level.equals("NOT_FIRST")){
					sql_m_ins_level = " ";
				}
						
				String sql_due_month_year = "  ";
				if((!m_due_month.equals("")) && (!m_due_year.equals(""))){
					sql_due_month_year = " AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month+"','"+m_due_year+"') > 0  ";
				}
				
				String sql_m_rendate = "  ";
				if((!m_rendate.equals("")) && (!m_rendate.equals(""))){
					//sql_m_rendate = 	" AND   NVL(VALUE_DATE,' ') LIKE '%"+m_rendate+"%' ";
					sql_m_rendate = 	" AND   NVL(VALUE_DATE,' ') = '"+m_rendate+"' "; // added by udara 09-08-2017
				}
				
				String sql_cr_officer_1 = "  ";
				String sql_cr_officer_2 = "  ";
				if((!cr_officer.equals(""))){
					sql_cr_officer_2 = " AND NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ') = '"+cr_officer+"' ";
				}
				
				String sql_m_location = "   ";
				if((!m_location.equals("")) && (!m_location.equals(""))){
					sql_m_location = " AND   LOCATION_CODE = '"+m_location+"' ";
				}
				
				String sql_m_officer = "   ";
				if((!m_officer.equals(""))){
					sql_m_officer = " AND   COLL_OFICER = '"+m_officer+"' "; // added by udara 09-08-2017
				}
				
				
				String sql_perform_status = "   ";
				
				if(!m_perform_status.equals("")){
					sql_perform_status = " AND PERFORM_STATUS = '"+m_perform_status+"'  ";
				}
				

				String sort_string = " ORDER BY VALUE_DATE,FINANCE_NO,CLIENT_CODE,FULL_NAME,AGE_NEW,CLIENT_TEL_NO,DUE_RENTAL_AMOUNT,FOL_REMARK,CITY_NAME,COLL_OFICER,VEHICLE_NO,TOTAL_PERIOD	 ";
				
				if(m_sort_column.equals(""))
					m_sort_column = " FINANCE_NO ";
				
				if(m_order_by_type.equals(""))
					m_sort_column = " ASC ";
				
				if(!m_sort_column.equals("VALUE_DATE"))
					sort_string = " ORDER BY  VALUE_DATE, "+m_sort_column+"  "+m_order_by_type+"  ";
				else
					sort_string = " ORDER BY  VALUE_DATE "+m_order_by_type+" ";
				

				
				Sql_data = " "+																
					
						" SELECT "+
						    " FINANCE_NO, "+  // 1
						    " CLIENT_CODE, "+ // 2
						    " FULL_NAME, "+ // 3
						    " AGE_NEW, "+ // 4
						    " CLIENT_TEL_NO, "+ // 5
						    " NVL(FOL_REMARK,'Enter Follow up') FOL_REMARK, "+ // 6
						    " NVL(CITY_NAME,'-') CITY_NAME, "+ // 7
						    " NVL(COLL_OFICER,'-') COLL_OFICER, "+ // 8
						    " NVL(VEHICLE_NO,'-') VEHICLE_NO, "+ // 9
						    " TOTAL_PERIOD, "+ // 10
							" NVL(VALUE_DATE,'-') VALUE_DATE, "+ // 11
						    " DUE_RENTAL_AMOUNT, "+ // 12
						    " TOTAL_AMOUNT, "+ // 13
						    " COLL_OFFICER, "+ // 14
						    " FIN_STATUS, "+ // 15
						    " NVL(TO_CHAR(LAST_PAY_DATE,'DD-MM-YYYY'),'-') LAST_PAY_DATE, "+ // 16
						    " ARR_RPT_REMARK, "+ // 17
						    " NVL(LAST_PAY_AMT,0) LAST_PAY_AMT, "+ // 18
							" NVL(CURR_COLL_OFFICER,'-') CURR_COLL_OFFICER, "+ // 19 
							" LAST_PAY_DATE LAST_PAY_DD "+
						" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS_V3 "+ 
						//" WHERE   TOTAL_AMOUNT > 0 "+
						"  WHERE REPORT_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						"  AND REPORT_DATE <= LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						"  "+sql_m_officer+" "+
						"  "+sql_cr_officer_2+" "+
						"  "+sql_m_location+" "+
						"  "+sql_cr_officer_1+" "+
						"  "+sql_m_slba+" "+
						"  "+sql_m_period+" "+
						"  "+m_active_status_string+" "+
						"  "+sql_m_region+" "+ 
						"  "+sql_m_ins_level+" "+
						"  "+sql_due_month_year+" "+
						"  "+sql_perform_status+"  "+
						"  "+m_possitive_status_string+"  "+
						"  "+sort_string+"  "+ 
						" ";
				

				rs=stmt.executeQuery(Sql_data);
				
				String row_colour = ""; 
				double period_val = 0;
				int j=1;
				
				String rental_date          = "";
				double total_mon_rental = 0;
				double total_open_bal       = 0;
				double sub_total_mon_rental = 0;
				double sub_total_open_bal   = 0;

				while(rs.next()){
							
							if(!rs.getString("VALUE_DATE").equals(rental_date)){
								
								if(j!=1)	{
									
									out.println("<tr>");
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(sub_total_mon_rental)+" </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(sub_total_open_bal)+" </td>");
									out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
									//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  
									out.println("</tr>");
								
								}
								
								rental_date = rs.getString("VALUE_DATE");
								sub_total_mon_rental = 0;
								sub_total_open_bal   = 0;
								
							}
							
							period_val = Math.round(rs.getDouble("AGE_NEW"));
			
							/*
							if((period_val>=3) && (period_val<6))
								row_colour = "CCFFFF"; // lightblue
							else if(period_val>=6)
								row_colour = "FFCCFF"; //"FF6699" // red
							else
								row_colour = "FFFFFF";
							*/
							
							row_colour = "FFFFFF";
	
						
							out.println("<tr  id=tr_id"+j+">"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString("FINANCE_NO")+"</u></td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString("VEHICLE_NO")+"</td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString("CLIENT_CODE")+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString("FULL_NAME")+"</u></td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString("CLIENT_TEL_NO")+"</td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString("TOTAL_PERIOD")+"</td>");
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString("VALUE_DATE")+"</td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble("DUE_RENTAL_AMOUNT"))+"</td>"); 
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
	
							if(rs.getString(15).equals("REPOSSESS")){
								out.println("<td  class=factoring-letter-body  > Seized </td>");
							}
							else{
								
								if(Math.round(rs.getDouble("AGE_NEW")) >= 0 && Math.round(rs.getDouble("AGE_NEW")) < 1){
								out.println("<td  class=factoring-letter-body    >-</td>"); 
								}
								else if(Math.round(rs.getDouble("AGE_NEW")) >= 1 && Math.round(rs.getDouble("AGE_NEW")) < 2 ){
									out.println("<td  class=factoring-letter-body    >NR</td>"); 
								}
								else if(Math.round(rs.getDouble("AGE_NEW")) >= 2 && Math.round(rs.getDouble("AGE_NEW")) < 3 ){
									out.println("<td  class=factoring-letter-body    >RR</td>"); 
								}
								else if(Math.round(rs.getDouble("AGE_NEW")) >= 3 && Math.round(rs.getDouble("AGE_NEW")) < 4 ){
									out.println("<td  class=factoring-letter-body    >CV</td>"); 
								}
								else if(Math.round(rs.getDouble("AGE_NEW")) >= 4 ){
									out.println("<td  class=factoring-letter-body   >Z</td>"); 
								}
							}
							
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble("TOTAL_AMOUNT"))+"</td>");			
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble("AGE_NEW"))+"</td>");
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\"><u> "+rs.getString("ARR_RPT_REMARK")+" </u></td>"); 
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString("FINANCE_NO")+"')\"><u>"+rs.getString("FOL_REMARK")+"</u></td>");
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString("CURR_COLL_OFFICER")+"</td>");
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString("COLL_OFFICER")+" </td>");  
							out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  > "+rs.getString("LAST_PAY_DATE")+" </td>"); 
							//out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  > "+nf1.format(rs.getDouble("LAST_PAY_AMT"))+" </td>"); 
	
							out.println("</tr>");
							
							
							sub_total_mon_rental = sub_total_mon_rental + rs.getDouble("DUE_RENTAL_AMOUNT");
							sub_total_open_bal   = sub_total_open_bal + rs.getDouble("TOTAL_AMOUNT");
						
						
							total_mon_rental = total_mon_rental + rs.getDouble("DUE_RENTAL_AMOUNT");
							total_open_bal   = total_open_bal + rs.getDouble("TOTAL_AMOUNT");
							j+=1;
							
							//more=rs.next();
					
					
				}
				
				out.println("<tr>");
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(sub_total_mon_rental)+" </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(sub_total_open_bal)+" </td>");
				out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
				//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  
				out.println("</tr>");
			
				out.println("<tr>");		
				out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
				out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
				out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
				out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 03-10-2013
				out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); // added by udara 26-12-2013
				//out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); // added by A S Silva 05-06-2015
				out.println("</tr>");
				
				
				
				out.println("</table>");
				
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
				
			}
			
			// end by udara 26-09-2017
			
			
			
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
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

