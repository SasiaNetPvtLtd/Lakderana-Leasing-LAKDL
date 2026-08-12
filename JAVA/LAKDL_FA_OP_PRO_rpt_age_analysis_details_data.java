import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_OP_PRO_rpt_age_analysis_details_data extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
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
		/*---------THIS PART IS MODIFIED BY ASHINI ON 10-10-2007-----------*/
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_date_category=req.getParameter("date_range");
			

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
			
			out.println(" function show_invoice_details_99m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_invoice_details_7m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_INVOICE_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
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

			
			m_report_heading=m_report_heading+" against Due Date ";
			
			rs1=stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2	
			" A.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			//"	(TO_DATE('"+m_eff_date+"','DD-MM-YYYY')-A.DUE_DATE) NO_DAYS	"+//6
			"	(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
			" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT >0 "+
			" ORDER BY A.CLIENT_CODE,A.INVOICE_DATE ");

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			m_facility_no_temp="";
			
			double m_rd7m=0;
			double m_rd7=0;
			double m_r0=0;
			double m_r7=0;
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			
			double m_pd7m=0;
			double m_pd7=0;
			double m_p0=0;
			double m_p7=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			
			double m_r=0;
			double m_p=0;
			
			double m_tpd7m=0;
			double m_tpd7=0;
			double m_tp0=0;
			double m_trd7m=0;
			double m_trd7=0;
			double m_tr0=0;
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
				if(m_date_category.equals("1")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due for more than 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("2")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due for last 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("3")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due Today</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("4")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("5")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 30 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("6")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 60 days</U><B></TD></TR></TABLE>");
				}
				
				if(m_date_category.equals("7")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 90 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("8")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in next 90 days</U><B></TD></TR></TABLE>");
				}

				if(m_date_category.equals("9")){
				out.println("<TABLE width='115%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Total Due Invoice</U><B></TD></TR></TABLE>");
				}
				
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='18%'>Facility No</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='60%' colspan=\"18\">Invoice Age(in days)</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='20%'></TD>");
  			out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='18%'></TD>");
				if(m_date_category.equals("1")){
				
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				
				}
				if(m_date_category.equals("2")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");
				}
				if(m_date_category.equals("3")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				}
				if(m_date_category.equals("4")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				}
				if(m_date_category.equals("5")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				}
				if(m_date_category.equals("6")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				}
				if(m_date_category.equals("7")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				}
				if(m_date_category.equals("8")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				}
				if(m_date_category.equals("9")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due Invoice NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due Invoice SUM</TD>");
				}
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
			
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}
						else if(m_num_days==0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
					else{
					
						m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;

						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;
						m_tp0=m_tp0+m_p0;
						m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				   if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						}
				   if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");
						}
				   if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
					  }
				   if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						}
				   if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						}
				   if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						}
				   if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						}
				   if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						}
				   if(m_date_category.equals("9")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf.format(m_r)+"</TD>");
           }						
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r7=0;m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p7=0;m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						m_rd7m=0;m_rd7=0;m_r0=0;m_pd7m=0;m_pd7=0;m_p0=0;
						
						int m_num_days=rs1.getInt(6);
						
						if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}
						else if(m_num_days==0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
						m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;
						m_tp0=m_tp0+m_p0;
						m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				    if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						}
				    if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");
						}
				    if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						}
	  		    if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
					  }
				    if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						}
				    if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						}
				    if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						}
				    if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						}
				    if(m_date_category.equals("9")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_invoice_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						}
						out.println("</TR>");		
					}
				}
			
			
			
		    if(m_date_category.equals("1")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");

  			out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("2")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("3")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("4")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("5")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("6")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("7")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("8")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("</TR>");				
				}
		
			
			
			
		    if(m_date_category.equals("9")){

				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");
				}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Dineth on 2009-02-17
			else if(m_chksql.equals("LOAD_PD_AGE_ANALYSIS_REPORT_DUE_DATE")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_date_category=req.getParameter("date_range");
			

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
			
			out.println(" function show_pod_details_99m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_pod_details_7m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_pod_details_7(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_pod_details_30(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_pod_details_60(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_pod_details_90(m_client_code,m_facility_no,m_date,m_range,m_date_cat){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_pod_details_99(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_POD_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println("</SCRIPT>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="PD Age Analysis Report as at "+m_eff_date;

			
			m_report_heading=m_report_heading+" against Due Date ";
			
			/*rs1=stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2	
			" A.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			//"	(TO_DATE('"+m_eff_date+"','DD-MM-YYYY')-A.DUE_DATE) NO_DAYS	"+//6
			"	(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
			" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT >0 "+
			" ORDER BY A.CLIENT_CODE,A.INVOICE_DATE ");*/
			rs1=stmt1.executeQuery(" SELECT  DISTINCT NVL(A.CLIENT_CODE,'-') CLIENT_CODE , "+
					                       " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+
																 " NVL(A.FACILITY_NO,'-') FACILITY_NO , "+
																 " TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+
																 " NVL(A.CHEQUE_AMOUNT,0), "+
																 " (A.PD_REALISE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS, "+	
                                 " NVL(A.CHEQUE_NO,'-') "+
																 //" ,NVL(B.INVOICE_NO,'-') "+
																 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B  "+  
                                 " WHERE A.CHEQUE_AMOUNT>0 AND A.POD_STATUS='N' "+
																 " AND A.POD_REF_NO=B.POD_REF_NO "+
                                 " ORDER BY CLIENT_CODE,FACILITY_NO ");
					

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			m_facility_no_temp="";
			
			double m_rd7m=0;
			double m_rd7=0;
			double m_r0=0;
			double m_r7=0;
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			
			double m_pd7m=0;
			double m_pd7=0;
			double m_p0=0;
			double m_p7=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			
			double m_r=0;
			double m_p=0;
			
			double m_tpd7m=0;
			double m_tpd7=0;
			double m_tp0=0;
			double m_trd7m=0;
			double m_trd7=0;
			double m_tr0=0;
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
				if(m_date_category.equals("1")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due for more than 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("2")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due for last 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("3")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due Today</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("4")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("5")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 30 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("6")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 60 days</U><B></TD></TR></TABLE>");
				}
				
				if(m_date_category.equals("7")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in 90 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("8")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due in next 90 days</U><B></TD></TR></TABLE>");
				}

				if(m_date_category.equals("9")){
				out.println("<TABLE width='115%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Total Due PD Cheques</U><B></TD></TR></TABLE>");
				}
				
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='18%'>Facility No</TD>");
				
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='60%' colspan=\"14\">PD Age(in days)</TD>");
				
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='20%'></TD>");
  			out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='18%'></TD>");
		
				if(m_date_category.equals("1")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				
				}
				if(m_date_category.equals("2")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");
				}
				if(m_date_category.equals("3")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				}
				if(m_date_category.equals("4")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				}
				if(m_date_category.equals("5")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				}
				if(m_date_category.equals("6")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				}
				if(m_date_category.equals("7")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				}
				if(m_date_category.equals("8")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				}
				if(m_date_category.equals("9")){
				/*out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");*/
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due PD NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due PD Cheque Amt</TD>");
				}
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
			
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						/*
						if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}*/
						if(m_num_days<=0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
					else{
					
						m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;

						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;
						m_tp0=m_tp0+m_p0;
						m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				   if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						}
				   if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");
						}
				   if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
					  }
				   if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						}
				   if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						}
				   if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						}
				   if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						}
				   if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						}
				   if(m_date_category.equals("9")){
						/*out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");*/
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf.format(m_r)+"</TD>");
           }						
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r7=0;m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p7=0;m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						m_rd7m=0;m_rd7=0;m_r0=0;m_pd7m=0;m_pd7=0;m_p0=0;
						
						int m_num_days=rs1.getInt(6);
						
						/*if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}*/
						if(m_num_days<=0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
						//m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						//m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;
						m_r=m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						/*m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;*/
						m_tp0=m_tp0+m_p0;
						/*m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;*/
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				    if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						}
				    if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");
						}
				    if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						}
	  		    if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
					  }
				    if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						}
				    if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						}
				    if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						}
				    if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						}
				    if(m_date_category.equals("9")){
						/*out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");*/
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_pod_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						}
						out.println("</TR>");		
					}
				}
			
			
			
		    if(m_date_category.equals("1")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");

  			out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("2")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("3")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("4")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("5")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("6")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("7")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("8")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("</TR>");				
				}
		
			
			
			
		    if(m_date_category.equals("9")){

				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				/*out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");*/
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");
				}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}

			
      //End by Dineth on 2009-02-17
		  //Added by Dineth on 2009-02-18
		  			else if(m_chksql.equals("LOAD_RECEIPT_AGE_ANALYSIS_REPORT_DUE_DATE")){
			
			String m_string="";				
			String m_sql="";	
			String m_eff_date=req.getParameter("eff_date");
			String m_date_category=req.getParameter("date_range");
			

			String m_client_code="";
			String m_client_name="";
			String m_client_code_temp="";
			String m_facility_no="";
			String m_facility_no_temp="";
			
			int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
			int count=0;
			
			out.println("<HTML><HEAD><TITLE>Receipt Age Analysis Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<SCRIPT language1.2='JavaScript' >"); 
			
			out.println(" function show_receipt_details_99m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_receipt_details_7m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_receipt_details_7(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_receipt_details_30(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println(" function show_receipt_details_60(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_receipt_details_90(m_client_code,m_facility_no,m_date,m_range,m_date_cat){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println("	 window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");

			out.println(" function show_receipt_details_99(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_age_analysis_detail?chksql=LOAD_RECEIPT_DETAILS&client_code=\"+m_client_code+\"&facility_no=\"+m_facility_no+\"&eff_date=\"+m_date+\"&date_range=\"+m_range+\"&date_category=\"+m_date_cat;");
			out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println(" }");
			
			out.println("</SCRIPT>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
						
			String m_report_heading="Receipt Age Analysis Report as at "+m_eff_date;

			
			m_report_heading=m_report_heading+" against Due Date ";
			
			/*rs1=stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+//1
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2	
			" A.FACILITY_NO,"+//3
			" A.INVOICE_DATE, "+//4
			" NVL(A.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+//5
			//"	(TO_DATE('"+m_eff_date+"','DD-MM-YYYY')-A.DUE_DATE) NO_DAYS	"+//6
			"	(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS	"+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
			" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT >0 "+
			" ORDER BY A.CLIENT_CODE,A.INVOICE_DATE ");*/
			/*rs1=stmt1.executeQuery(" SELECT NVL(A.CLIENT_CODE,'-'), "+
					                       " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+
																 " NVL(A.FACILITY_NO,'-'), "+
																 " A.CHEQUE_DATE, "+
																 " NVL(A.CHEQUE_AMOUNT,0), "+
                                 " (A.PD_REALISE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS "+	
                                 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B  "+  
                                 " WHERE A.POD_STATUS='N' AND A.CHEQUE_AMOUNT>0 "+
																 " AND A.POD_REF_NO=B.POD_REF_NO "+
                                 " ORDER BY A.CLIENT_CODE,A.FACILITY_NO ");*/
																	
			rs1=stmt1.executeQuery(" SELECT NVL(A.CLIENT_CODE,'-'), "+ 
                                 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+ 
																 " NVL(A.FACILITY_NO,'-'), "+ 
                                 " A.CHEQUE_DATE, "+ 
                                 " NVL(A.REC_AMOUNT,0), "+ 
                                 " (A.CHEQUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')) NO_DAYS "+	
                                 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO B "+    
                                 " WHERE A.REC_STATUS='N' AND A.REC_AMOUNT>0 "+  
                                 " AND A.RECEIPT_NO=B.RECEIPT_NO "+ 
                                 " ORDER BY A.CLIENT_CODE,A.FACILITY_NO ");
					
					

			String m_port_name="";
			String m_port_name_temp="";
			m_facility_no="";
			m_facility_no_temp="";
			
			double m_rd7m=0;
			double m_rd7=0;
			double m_r0=0;
			double m_r7=0;
			double m_r30=0;
			double m_r60=0;
			double m_r90=0;
			double m_r90m=0;
			
			double m_pd7m=0;
			double m_pd7=0;
			double m_p0=0;
			double m_p7=0;
			double m_p30=0;
			double m_p60=0;
			double m_p90=0;
			double m_p90m=0;
			
			double m_r=0;
			double m_p=0;
			
			double m_tpd7m=0;
			double m_tpd7=0;
			double m_tp0=0;
			double m_trd7m=0;
			double m_trd7=0;
			double m_tr0=0;
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
				if(m_date_category.equals("1")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due to be settled for more than 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("2")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Already due to be settled for last 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("3")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled Today</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("4")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled in 7 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("5")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled in 30 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("6")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled in 60 days</U><B></TD></TR></TABLE>");
				}
				
				if(m_date_category.equals("7")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled in 90 days</U><B></TD></TR></TABLE>");
				}
				if(m_date_category.equals("8")){
				out.println("<TABLE width='100%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Due to be settled in next 90 days</U><B></TD></TR></TABLE>");
				}

				if(m_date_category.equals("9")){
				out.println("<TABLE width='115%' ><TR class=pdn_txtpos2 ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>Total Due Resceipts</U><B></TD></TR></TABLE>");
				}
				
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE width='100%' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'>Client Name</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='18%'>Facility No</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='60%' colspan=\"18\">Receipt Age(in days)</TD>");
				out.println("</TR>");
				out.println("<TR class=pdn_txtpos2>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='20%'></TD>");
  			out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='18%'></TD>");
				if(m_date_category.equals("1")){
				
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				
				}
				if(m_date_category.equals("2")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");
				}
				if(m_date_category.equals("3")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				}
				if(m_date_category.equals("4")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				}
				if(m_date_category.equals("5")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				}
				if(m_date_category.equals("6")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				}
				if(m_date_category.equals("7")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				}
				if(m_date_category.equals("8")){
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				}
				if(m_date_category.equals("9")){
				/*out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for more than 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Already due for last 7 days SUM</TD>");*/
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due Today SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 7 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 30 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 60 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Due in next 90 days SUM</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due Receipt NO</TD>");
				out.println("<TD STYLE='{font: 7pt arial; text-align:center;}' width='6%'>Total Due Receipt Amt</TD>");
				}
				out.println("</TR> ");		
				
				m_port_name_temp=rs1.getString(1);
				m_facility_no_temp=rs1.getString(3);
				m_client_name=rs1.getString(2);
				
				while(more1){
			
					m_port_name=rs1.getString(1);
					m_facility_no=rs1.getString(3);
					
					if(m_port_name_temp.equals(m_port_name) && m_facility_no_temp.equals(m_facility_no) ){
					
						int m_num_days=rs1.getInt(6);
						
						/*if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}*/
						if(m_num_days<=0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
					else{
					
						m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;

						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;
						m_tp0=m_tp0+m_p0;
						m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				   if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						}
				   if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");
						}
				   if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
					  }
				   if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						}
				   if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						}
				   if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						}
				   if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						}
				   if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						}
				   if(m_date_category.equals("9")){
						/*out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_rd7)+"</u></TD>");*/
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','30','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf.format(m_r90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' width='6%'>"+nf.format(m_r)+"</TD>");
           }						
						out.println("</TR>");		
						
						m_port_name_temp=rs1.getString(1);
						m_facility_no_temp=rs1.getString(3);
						m_client_name=rs1.getString(2);
						
						m_r7=0;m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
						m_p7=0;m_p30=0;m_p60=0;m_p90=0;m_p90m=0;
						m_r=0;m_p=0;
						m_rd7m=0;m_rd7=0;m_r0=0;m_pd7m=0;m_pd7=0;m_p0=0;
						
						int m_num_days=rs1.getInt(6);
						
						/*if(m_num_days<-7){
						m_rd7m=m_rd7m+rs1.getDouble(5);
						m_pd7m=m_pd7m+1;
						}
						else if(m_num_days>=-7 && m_num_days<0){
						m_rd7=m_rd7+rs1.getDouble(5);
						m_pd7=m_pd7+1;
						}*/
						if(m_num_days<=0){
						m_r0=m_r0+rs1.getDouble(5);
						m_p0=m_p0+1;
						}
						else if(m_num_days>0 && m_num_days<=7){
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
						m_r=m_rd7m+m_rd7+m_r0+m_r7+m_r30+m_r60+m_r90+m_r90m;
						m_p=m_pd7m+m_pd7+m_p0+m_p7+m_p30+m_p60+m_p90+m_p90m;
						
						m_tr=m_tr+m_r;
						m_tp=m_tp+m_p;

						m_tpd7m=m_tpd7m+m_pd7m;
						m_tpd7=m_tpd7+m_pd7;
						m_tp0=m_tp0+m_p0;
						m_trd7m=m_trd7m+m_rd7m;
						m_trd7=m_trd7+m_rd7;
						m_tr0=m_tr0+m_r0;
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
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:left; cursor: hand;}' onClick=\"show_client('"+m_port_name_temp+"')\" width='20%'><u>"+m_client_name+"</u></TD>");
						out.println("<TD STYLE='{font: bold 7pt arial; text-align:center; cursor: hand;}' width='18%' onClick=\"show_facility('"+m_facility_no_temp+"')\"  ><u>"+m_facility_no_temp+"</u></TD>");
				    if(m_date_category.equals("1")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						}
				    if(m_date_category.equals("2")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");
						}
				    if(m_date_category.equals("3")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						}
	  		    if(m_date_category.equals("4")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
					  }
				    if(m_date_category.equals("5")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						}
				    if(m_date_category.equals("6")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						}
				    if(m_date_category.equals("7")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						}
				    if(m_date_category.equals("8")){
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						}
				    if(m_date_category.equals("9")){
						/*out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','99m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7m)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_pd7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_rd7)+"</TD>");*/
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7m('"+m_port_name_temp+"','"+m_facility_no_temp+"','"+m_eff_date+"','7m','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p0)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r0)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_7('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','7','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p7)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r7)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_30('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','30','"+m_date_category+"')\"  width='6%'><u>"+nf1.format(m_p30)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_60('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','60','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p60)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_90('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','90','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right; cursor: hand;}' onClick=\"show_receipt_details_99('"+m_port_name_temp+"','"+m_facility_no+"','"+m_eff_date+"','99','"+m_date_category+"')\" width='6%'><u>"+nf1.format(m_p90m)+"</u></TD>");
						out.println("<TD STYLE='{font: 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf1.format(m_p)+"</TD>");
						out.println("<TD STYLE='{font:  bold 7pt arial; text-align:right;}' width='6%'>"+nf.format(m_r)+"</TD>");
						}
						out.println("</TR>");		
					}
				}
			
			
			
		    if(m_date_category.equals("1")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");

  			out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("2")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("3")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("4")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("5")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("6")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("7")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("</TR>");				
				}
		    if(m_date_category.equals("8")){
				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("</TR>");				
				}
		
			
			
			
		    if(m_date_category.equals("9")){

				out.println("<TR>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='20%'><B>Total</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:center;}' width='17%'><B></TD>");
				/*out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tpd7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_trd7)+"</TD>");*/
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr0)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr7)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr30)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr60)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr90m)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf1.format(m_tp)+"</TD>");
				out.println("<TD STYLE='{font: bold 7pt arial; text-align:right;}' width='6%'><B>"+nf.format(m_tr)+"</TD>");
				out.println("</TR>");
				}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
		  
			//End by Dineth on 2009-02-18
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


