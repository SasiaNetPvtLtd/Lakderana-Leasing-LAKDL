import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_AF_RE_rpt_age_analysis extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt1;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		/*	else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_date_category=req.getParameter("date_category");
			String m_client_code="";
			String m_client_name="";
			String m_client_code_temp="";
			String m_facility_no="";
			String m_facility_no_temp="";
			
			int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
			int count=0;
			
			out.println("<HTML><HEAD><TITLE>Invoice Age Analysis Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<SCRIPT language1.2='JavaScript' >"); 
			
			out.println(" function show_invoice_details_7(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_30(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_60(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_invoice_details_90(m_client_code,m_facility_no,m_date,m_range,m_date_cat){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_invoice_details_99(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println("</SCRIPT>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="Invoice Age Analysis Report as at "+m_eff_date;

			/*if(m_date_category.equals("DUE_DATE")){
			m_report_heading=m_report_heading+" against Due Date ";
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			"	(TO_DATE(A.DUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");
			}*/
			
		/*	if(m_date_category.equals("DUE_DATE")){
		
		  /*m_report_heading=m_report_heading+" against Due Date ";
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			"	(TO_DATE(A.DUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");
			}*/
			
		/*	out.println("test");
			
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
				"AF_RE_SAVE_INV_AGE_ANALYST_RPT(:1);END;");
				
				
				
				callstmt1.setString(1 ,m_eff_date);
								
				callstmt1.execute();
			
			}
			
			*/
			
		/*	else if(m_date_category.equals("TOL_DATE")){
			m_report_heading=m_report_heading+" against Tolerance Date ";
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			"	(TO_DATE(A.TOLARENCE_END_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");
			}
			else if(m_date_category.equals("REFACT_DATE")){
			m_report_heading=m_report_heading+" against Refactoring Date ";
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			"	(TO_DATE(A.REFACT_TOLARENCE_END_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT<>0 AND A.REFACTOR_COUNT>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");
			}
			else{
			m_report_heading=m_report_heading+" against Invoice Date ";
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			"	(TO_DATE(A.INVOICE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");
			}
			*/

		/*	String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			m_facility_no_temp="";
			
			double m_r7=0;
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			
			double m_p7=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			double m_r=0;
			double m_p=0;
			
			double m_tr7=0;
			double m_tp7=0;
			double m_tr30=0;
			double m_tp30=0;
			double m_tr60=0;
			double m_tp60=0;
			double m_tr90=0;
			double m_tp90=0;
			double m_tr90m=0;
			double m_tp90m=0;
			
			double m_tr=0;
			double m_tp=0;
			double m_tnetr=0;
			double m_tnetp=0;
			
			boolean	 more1 = rs1.next();
		
			if(more1){
				out.println("<br>");
				out.println("<TABLE width='100%'><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'>Facility No</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='60%' colspan=\"12\">Invoice Age</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-7 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-7 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Sum</TD>");
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
					
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=7){
						m_r7=m_r7+rs1.getDouble(5);
						m_p7=m_p7+1;
						}
						else if(m_num_days>7 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){ //Modified by Mahela
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					}
					else{
					
						m_r=m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_p7+m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;
						
						m_tr7=m_tr7+m_r7;
						m_tp7=m_tp7+m_p7;
						m_tr30=m_tr30+m_r30;
						m_tp30=m_tp30+m_p30;
						m_tr60=m_tr60+m_r60;
						m_tp60=m_tp60+m_p60;
						m_tr90=m_tr90+m_r90;
						m_tp90=m_tp90+m_p90;
						m_tr90m=m_tr90m+m_r90m;
						m_tp90m=m_tp90m+m_p90m;
						
						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r7=0;m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p7=0;m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=7){
						m_r7=m_r7+rs1.getDouble(5);
						m_p7=m_p7+1;
						}
						else if(m_num_days>7 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					
					}
					more1=rs1.next();
					
					
					if(!more1){
					m_r=m_r7+m_r30+m_r60+m_r90+m_r90m;
					m_p=m_p7+m_p30+m_p60+m_p90+m_p90m;
					
					m_tr=m_tr+m_r;
					m_tp=m_tp+m_p;
					
					m_tr7=m_tr7+m_r7;
					m_tp7=m_tp7+m_p7;
					m_tr30=m_tr30+m_r30;
					m_tp30=m_tp30+m_p30;
					m_tr60=m_tr60+m_r60;
					m_tp60=m_tp60+m_p60;
					m_tr90=m_tr90+m_r90;
					m_tp90=m_tp90+m_p90;
					m_tr90m=m_tr90m+m_r90m;
					m_tp90m=m_tp90m+m_p90m;

						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
					}
				}
				
				/*m_tr=m_tr+m_r;
				m_tp=m_tp+m_p;
				m_tr30=m_tr30+m_r30;
				m_tp30=m_tp30+m_p30;
				m_tr60=m_tr60+m_r60;
				m_tp60=m_tp60+m_p60;
				m_tr90=m_tr90+m_r90;
				m_tp90=m_tp90+m_p90;
				m_tr90m=m_tr90m+m_r90m;
				m_tp90m=m_tp90m+m_p90m;*/
				
			/*	out.println("<TR>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Total</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr7)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");	
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			*/
			
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_date_category=req.getParameter("date_category");
			String m_client_code="";
			String m_client_name="";
			String m_client_code_temp="";
			String m_facility_no="";
			String m_facility_no_temp="";
			String m_officer_name="";
			
			int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
			int count=0;
			
			out.println("<HTML><HEAD><TITLE>Invoice Age Analysis Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<SCRIPT language1.2='JavaScript' >"); 
			
			out.println(" function show_invoice_details_99m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
		//	out.println("  window.open(m_url)");
			out.println(" }");
			
			out.println(" function show_invoice_details_7m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_7(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_30(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_60(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_invoice_details_90(m_client_code,m_facility_no,m_date,m_range,m_date_cat){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_invoice_details_99(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println("</SCRIPT>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="Invoice Age Analysis Report as at "+m_eff_date;

			if(m_date_category.equals("DUE_DATE")){
			m_report_heading=m_report_heading+" against Due Date ";
								
							rs1=stmt1.executeQuery(" SELECT "+ 
							" B.CLIENT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
							" B.FINANCE_NO, "+
							" A.INVOICE_NO, "+
							" A.BALANCE, "+
							" B.PERIOD, "+
							" B.COLLECTION_OFFICER "+
							
							" FROM "+
							"  (SELECT "+
							"  INVOICE_NO, "+
							"  SUM(INVOICED_AMOUNT) - SUM(SETTELED_AMOUNT) BALANCE "+
							"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
							"  GROUP BY INVOICE_NO) A , "+
							
							"    (SELECT "+
							"     X.FINANCE_NO, "+
							"     X.CLIENT_CODE, "+
							"     X.INVOICE_NO, "+
							"     TO_DATE(X.DUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') PERIOD, "+
							"     Y.COLLECTION_OFFICER "+
							"     FROM "+m_schema_name+".AF_CO_PRO_INVOICE X,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
							"     WHERE TO_DATE(DUE_DATE)<=TO_DATE('"+m_eff_date+"','DD-MM-YYYY') AND "+
							"           X.FINANCE_NO=Y.FINANCE_NO AND "+
							"           ACTIVE_STATUS='Y' "+
							"    )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO  AND"+
							" A.BALANCE <> 0 "+
							" ORDER BY B.CLIENT_CODE,B.FINANCE_NO ");
							
			
			}

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			m_facility_no_temp="";
			
			double m_r99m=0;
			double m_r7m=0;
			double m_r7=0;
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			
			double m_p99m=0;
			double m_p7m=0;
			double m_p7=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			double m_r=0;
			double m_p=0;
			
			double m_tr99m=0;
			double m_tp99m=0;
			double m_tr7m=0;
			double m_tp7m=0;
			double m_tr7=0;
			double m_tp7=0;
			double m_tr30=0;
			double m_tp30=0;
			double m_tr60=0;
			double m_tp60=0;
			double m_tr90=0;
			double m_tp90=0;
			double m_tr90m=0;
			double m_tp90m=0;
			
			double m_tr=0;
			double m_tp=0;
			double m_tnetr=0;
			double m_tnetp=0;
			
			boolean	 more1 = rs1.next();
		
			if(more1){
				out.println("<br>");
				out.println("<TABLE width='115%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'>Finance No</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'>Collection Officer</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='60%' colspan=\"16\">Invoice Age</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'> >(-7) Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'> >(-7) Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-(-7) Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-(-7) Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-7 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-7 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Sum</TD>");
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				m_officer_name=rs1.getString(7);
				while(more1){
					
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days<-7){
						m_r99m=m_r99m+rs1.getDouble(5);
						m_p99m=m_p99m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_r7m=m_r7m+rs1.getDouble(5);
						m_p7m=m_p7m+1;
						}
						else if(m_num_days>=0 && m_num_days<=7){
						m_r7=m_r7+rs1.getDouble(5);
						m_p7=m_p7+1;
						}
						else if(m_num_days>7 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){ //Modified by Mahela
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					}
					else{
					
						m_r=m_r99m+m_r7m+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_p99m+m_p7m+m_p7+m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;
						
						m_tr99m=m_tr99m+m_r99m;
						m_tp99m=m_tp99m+m_p99m;
						m_tr7m=m_tr7m+m_r7m;
						m_tp7m=m_tp7m+m_p7m;
						m_tr7=m_tr7+m_r7;
						m_tp7=m_tp7+m_p7;
						m_tr30=m_tr30+m_r30;
						m_tp30=m_tp30+m_p30;
						m_tr60=m_tr60+m_r60;
						m_tp60=m_tp60+m_p60;
						m_tr90=m_tr90+m_r90;
						m_tp90=m_tp90+m_p90;
						m_tr90m=m_tr90m+m_r90m;
						m_tp90m=m_tp90m+m_p90m;
						
						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_finance_detail_drill('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"\"  >"+m_officer_name+"</TD>");
						
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p99m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r99m)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7m)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r99m=0;m_r7m=0;m_r7=0;m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p99m=0;m_p7m=0;m_p7=0;m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days<-7){
						m_r99m=m_r99m+rs1.getDouble(5);
						m_p99m=m_p99m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_r7m=m_r7m+rs1.getDouble(5);
						m_p7m=m_p7m+1;
						}
						else if(m_num_days>=0 && m_num_days<=7){
						m_r7=m_r7+rs1.getDouble(5);
						m_p7=m_p7+1;
						}
						else if(m_num_days>7 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					
					}
					more1=rs1.next();
					
					
					if(!more1){
					m_r=m_r99m+m_r7m+m_r7+m_r30+m_r60+m_r90+m_r90m;
					m_p=m_p99m+m_p7m+m_p7+m_p30+m_p60+m_p90+m_p90m;
					
					m_tr=m_tr+m_r;
					m_tp=m_tp+m_p;
					
					m_tr99m=m_tr99m+m_r99m;
					m_tp99m=m_tp99m+m_p99m;
					m_tr7m=m_tr7m+m_r7m;
					m_tp7m=m_tp7m+m_p7m;
					m_tr7=m_tr7+m_r7;
					m_tp7=m_tp7+m_p7;
					m_tr30=m_tr30+m_r30;
					m_tp30=m_tp30+m_p30;
					m_tr60=m_tr60+m_r60;
					m_tp60=m_tp60+m_p60;
					m_tr90=m_tr90+m_r90;
					m_tp90=m_tp90+m_p90;
					m_tr90m=m_tr90m+m_r90m;
					m_tp90m=m_tp90m+m_p90m;

						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_officer_name+"')\"  ><u>"+m_officer_name+"</u></TD>");
						
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p99m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r99m)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7m)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
					}
				}
				
				/*m_tr=m_tr+m_r;
				m_tp=m_tp+m_p;
				m_tr30=m_tr30+m_r30;
				m_tp30=m_tp30+m_p30;
				m_tr60=m_tr60+m_r60;
				m_tp60=m_tp60+m_p60;
				m_tr90=m_tr90+m_r90;
				m_tp90=m_tp90+m_p90;
				m_tr90m=m_tr90m+m_r90m;
				m_tp90m=m_tp90m+m_p90m;*/
				
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Total</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='18%'></TD>");
				
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp99m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr99m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr7)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='6%'>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");	
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela
	/*		else if(m_chksql.equals("LOAD_POD_AGE_ANALYSIS_REPORT")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_client_code="";
			String m_client_name="";
			String m_client_code_temp="";
			String m_facility_no="";
			String m_facility_no_temp="";
			
			int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
			int count=0;
			
			out.println("<HTML><HEAD><TITLE>PD Age Analysis Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<SCRIPT language1.2='JavaScript' >"); 
			
			out.println(" function show_pod_details_30(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700'); ");	
			out.println(" } ");

			out.println(" function show_pod_details_60(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700'); ");
			out.println(" } ");

			out.println(" function show_pod_details_90(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" } ");

			out.println(" function show_pod_details_99(m_client_code,m_facility_no,m_date,m_range){");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println("</SCRIPT>");

			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="PD Age Analysis Report as at "+m_eff_date;

			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2	
			" B.FACILITY_NO,"+//3
			" A.CHEQUE_DATE, "+//4
			" NVL(A.CHEQUE_AMOUNT,0) CHEQUE_AMOUNT, "+//5
			"	(TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A, "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
			" WHERE A.POD_REF_NO=B.POD_REF_NO AND A.POD_STATUS='N' AND A.CHEQUE_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			double m_r=0;
			double m_p=0;
			
			double m_tr30=0;
			double m_tp30=0;
			double m_tr60=0;
			double m_tp60=0;
			double m_tr90=0;
			double m_tp90=0;
			double m_tr90m=0;
			double m_tp90m=0;
			
			double m_tr=0;
			double m_tp=0;
			double m_tnetr=0;
			double m_tnetp=0;
			
			boolean	 more1 = rs1.next();
		
			if(more1){
				out.println("<br>");
				out.println("<TABLE width='100%'><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Facility No</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='60%' colspan=\"10\">PD Age</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Sum</TD>");
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
					
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					}
					else{
					
						m_r=m_r30+m_r60+m_r90+m_r90m;
						m_p=m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;
						
						m_tr30=m_tr30+m_r30;
						m_tp30=m_tp30+m_p30;
						m_tr60=m_tr60+m_r60;
						m_tp60=m_tp60+m_p60;
						m_tr90=m_tr90+m_r90;
						m_tp90=m_tp90+m_p90;
						m_tr90m=m_tr90m+m_r90m;
						m_tp90m=m_tp90m+m_p90m;
						
						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='10%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='10%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30')\"  width='10%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60')\" width='10%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90')\" width='10%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99')\" width='10%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					
					}
					more1=rs1.next();
					
					
					if(!more1){
					m_r=m_r30+m_r60+m_r90+m_r90m;
					m_p=m_p30+m_p60+m_p90+m_p90m;
					
					m_tr=m_tr+m_r;
					m_tp=m_tp+m_p;
					
					m_tr30=m_tr30+m_r30;
					m_tp30=m_tp30+m_p30;
					m_tr60=m_tr60+m_r60;
					m_tp60=m_tp60+m_p60;
					m_tr90=m_tr90+m_r90;
					m_tp90=m_tp90+m_p90;
					m_tr90m=m_tr90m+m_r90m;
					m_tp90m=m_tp90m+m_p90m;

						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='10%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='10%' onClick=\"show_facility('"+m_facility_no+"')\"  ><u>"+m_facility_no+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30')\"  width='10%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60')\" width='10%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90')\" width='10%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99')\" width='10%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
					}
				}
				
				/*m_tr=m_tr+m_r;
				m_tp=m_tp+m_p;
				m_tr30=m_tr30+m_r30;
				m_tp30=m_tp30+m_p30;
				m_tr60=m_tr60+m_r60;
				m_tp60=m_tp60+m_p60;
				m_tr90=m_tr90+m_r90;
				m_tp90=m_tp90+m_p90;
				m_tr90m=m_tr90m+m_r90m;
				m_tp90m=m_tp90m+m_p90m;*/
				
	/*			out.println("<TR>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='10%'>Total</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='10%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");	
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			*/
			
			//Added by Mahela
	/*		else if(m_chksql.equals("LOAD_CHEQUE_RETURN_AGE_ANALYSIS_REPORT")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_client_code="";
			String m_client_name="";
			String m_client_code_temp="";
			String m_facility_no="";
			String m_facility_no_temp="";
			
			int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
			int count=0;
			
			out.println("<HTML><HEAD><TITLE>Cheque Return Age Analysis Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<SCRIPT language1.2='JavaScript' >"); 

			out.println(" function show_cheque_return_details_30(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_CHEQUE_RETURN_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_cheque_return_details_60(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_CHEQUE_RETURN_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700'); ");
			out.println(" } ");

			out.println(" function show_cheque_return_details_90(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_CHEQUE_RETURN_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700'); ");		
			out.println(" } ");

			out.println(" function show_cheque_return_details_99(m_client_code,m_facility_no,m_date,m_range){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_CHEQUE_RETURN_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range; ");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700'); ");
			out.println(" } ");
			
			out.println("</SCRIPT>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="Cheque Return Age Analysis Report as at "+m_eff_date;
			
			rs1=stmt1.executeQuery(" SELECT B.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+ //2
			" B.FACILITY_NO,"+//3
			" A.REALIZE_DATE,"+ //4
			" NVL(A.DEPOSIT_AMOUNT,0) DEPOSIT_AMOUNT, "+//5
			"	(TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A, "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
			" WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' AND A.DEPOSIT_AMOUNT<>0 "+
			" ORDER BY B.CLIENT_CODE,B.FACILITY_NO ");

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			double m_r=0;
			double m_p=0;
			
			double m_tr30=0;
			double m_tp30=0;
			double m_tr60=0;
			double m_tp60=0;
			double m_tr90=0;
			double m_tp90=0;
			double m_tr90m=0;
			double m_tp90m=0;
			
			double m_tr=0;
			double m_tp=0;
			double m_tnetr=0;
			double m_tnetp=0;
			
			boolean	 more1 = rs1.next();
		
			if(more1){
				out.println("<br>");
				out.println("<TABLE width='100%'><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'>Facility No</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='60%' colspan=\"10\">Cheque Return Age</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='20%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>0-30 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>30-60 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>60-90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>>90 Sum</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Count</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='6%'>Total Sum</TD>");
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
					
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					}
					else{
					
						m_r=m_r30+m_r60+m_r90+m_r90m;
						m_p=m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;
						
						m_tr30=m_tr30+m_r30;
						m_tp30=m_tp30+m_p30;
						m_tr60=m_tr60+m_r60;
						m_tp60=m_tp60+m_p60;
						m_tr90=m_tr90+m_r90;
						m_tp90=m_tp90+m_p90;
						m_tr90m=m_tr90m+m_r90m;
						m_tp90m=m_tp90m+m_p90m;
						
						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='10%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='10%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30')\"  width='10%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60')\" width='10%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90')\" width='10%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99')\" width='10%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days>=0 && m_num_days<=30){
						m_r30=m_r30+rs1.getDouble(5);
						m_p30=m_p30+1;
						}
						else if(m_num_days>30 && m_num_days<=60){
						m_r60=m_r60+rs1.getDouble(5);
						m_p60=m_p60+1;
						}
						else if(m_num_days>60 && m_num_days<=90){
						m_r90=m_r90+rs1.getDouble(5);
						m_p90=m_p90+1;
						}
						else if(m_num_days>90){
						m_r90m=m_r90m+rs1.getDouble(5);
						m_p90m=m_p90m+1;
						}
					
					}
					more1=rs1.next();
					
					
					if(!more1){
					m_r=m_r30+m_r60+m_r90+m_r90m;
					m_p=m_p30+m_p60+m_p90+m_p90m;
					
					m_tr=m_tr+m_r;
					m_tp=m_tp+m_p;
					
					m_tr30=m_tr30+m_r30;
					m_tp30=m_tp30+m_p30;
					m_tr60=m_tr60+m_r60;
					m_tp60=m_tp60+m_p60;
					m_tr90=m_tr90+m_r90;
					m_tp90=m_tp90+m_p90;
					m_tr90m=m_tr90m+m_r90m;
					m_tp90m=m_tp90m+m_p90m;

						out.println("<TR>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='10%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 8pt arial; text-align:center; cursor: hand;}' width='10%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30')\"  width='10%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60')\" width='10%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90')\" width='10%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right; cursor: hand;}' onClick=\"show_cheque_return_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99')\" width='10%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_r)+"</TD>");
						out.println("</TR>");		
					}
				}
				
				/*m_tr=m_tr+m_r;
				m_tp=m_tp+m_p;
				m_tr30=m_tr30+m_r30;
				m_tp30=m_tp30+m_p30;
				m_tr60=m_tr60+m_r60;
				m_tp60=m_tp60+m_p60;
				m_tr90=m_tr90+m_r90;
				m_tp90=m_tp90+m_p90;
				m_tr90m=m_tr90m+m_r90m;
				m_tp90m=m_tp90m+m_p90m;*/
				
		/*		out.println("<TR>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='10%'>Total</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:center;}' width='10%'></TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='10%'>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");	
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			*/

			else {
			    out.println("Undefined");
			}

      out.close();
			conn.close();
			this.destroy();
			
			
			}
			catch (Exception e) {
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}


