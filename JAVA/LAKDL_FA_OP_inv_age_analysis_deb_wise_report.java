import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:11-03-2008
// INVOICE AGE ANALYSIS REPORT DEBTOR WISE            

public class LAKDL_FA_OP_inv_age_analysis_deb_wise_report extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE_OLD")){
				
				String m_string="";				
				String m_sql="";	
				String m_eff_date=req.getParameter("eff_date");
				String m_date_category=req.getParameter("date_category");
				String m_client_code="";
				String m_client_name="";
				//String m_client_code_temp="";
				String m_facility_no="";
				String m_facility_no_temp="";
				String m_bebotr_code="";
				String m_bebotr_code_temp="";
				
				String mm_client_code=req.getParameter("client_code");
				String mm_facility_no=req.getParameter("facility_no");
				String report_type = req.getParameter("report_type");//Added By Sandun on 22-09-2009
				String mm_temp_client_code="";
				int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
				int count=0;
				
				out.println("<HTML><HEAD><TITLE>Invoice Age Analysis Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language1.2='JavaScript' >"); 
				
				/*out.println(" function show_invoice_details_99m(m_client_code,m_facility_no,m_date,m_range,m_date_cat){ ");
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
				out.println(" }");*/
				
				out.println(" function show_inv_age_ana_debtor_data(m_no,m_date,m_client_code,m_debtor_code,m_facility_no){ ");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_inv_age_analysis_deb_wise_details_data?chksql=LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE&eff_date=\"+m_date+\"&client_code=\"+m_client_code+\"&debtor_code=\"+m_debtor_code+\"&facility_no=\"+m_facility_no+\"&date_range=\"+m_no;");
				out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println(" }");
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				String m_report_heading="Invoice Age Analysis Debtor Report as at "+m_eff_date;
				
				if(m_date_category.equals("DUE_DATE")){
					
					m_report_heading=m_report_heading+" against Due Date ";
					
					/*	rs1=stmt1.executeQuery("SELECT DISTINCT "+
												" A.CLIENT_CODE, "+ //1
												" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
												" A.FACILITY_NO, "+ //3
												" SUM("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')))), "+ //4
												" SUM("+m_schema_name+".FA_GET_INV_0_30_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')))), "+ //5
												" SUM("+m_schema_name+".FA_GET_INV_30_60_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')))), "+ //6
												" SUM("+m_schema_name+".FA_GET_INV_60_90_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')))), "+ //7
												" SUM("+m_schema_name+".FA_GET_INV_MORE_90_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,(A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')))), "+ //8
												" A.DEBTOR_CODE, "+ //9
												" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
												" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
												" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
												" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
												" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%' GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE  ");
																		*/
					
					if(report_type.equals("ALL")){
						rs1=stmt1.executeQuery("SELECT DISTINCT "+
							" A.CLIENT_CODE, "+ //1
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
							" A.FACILITY_NO, "+ //3
							" ("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //4
							" ("+m_schema_name+".FA_GET_INV_0_30_AMT       (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //5
							" ("+m_schema_name+".FA_GET_INV_30_60_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //6
							" ("+m_schema_name+".FA_GET_INV_60_90_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //7
							" ("+m_schema_name+".FA_GET_INV_MORE_90_AMT    (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //8
							" A.DEBTOR_CODE, "+ //9
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
							" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
							" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
							" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%'  "+
							" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE  ");
						
					}
					else{
						rs1=stmt1.executeQuery("SELECT DISTINCT "+															
							//out.println("SELECT DISTINCT "+															
							" A.CLIENT_CODE, "+ //1
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
							" A.FACILITY_NO, "+ //3
							" ("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //4
							" ("+m_schema_name+".FA_GET_INV_0_30_AMT       (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //5
							" ("+m_schema_name+".FA_GET_INV_30_60_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //6
							" ("+m_schema_name+".FA_GET_INV_60_90_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //7
							" ("+m_schema_name+".FA_GET_INV_MORE_90_AMT    (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //8
							" A.DEBTOR_CODE, "+ //9
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
							" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
							" AND "+m_schema_name+".FA_OP_GET_EXIST_PFC(A.CLIENT_CODE,A.FACILITY_NO)= '"+report_type+"' "+//Added By Sandun on22-09-2009
							" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
							" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%'  "+
							" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE  ");
						
					}
					
				}                      
				
				
				
				double m_rd7m=0;
				double m_rd7=0;
				double m_r0=0;
				double m_r7=0;
				double m_r30=0;
				double m_r60=0;
				double m_r90=0;
				double m_r90m=0;
				
				double m_r=0;
				double m_p=0;
				
				boolean	 more1 = rs1.next();
				
				out.println("<br>");
				
				out.println("<TABLE width='100%'  ><TR class=pdn_txtpos2 width='100%'  ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				
				out.println("<TABLE width='100%' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
				out.println("<TR class=pdn_txtpos2 >");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  ><B>Client Name</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  ><B>Debtor Code</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='15%'  ><B>Debtor Name</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>Not Yet Due</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>0-30 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>30-60 Days </TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>60-90 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B> > 90 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%'  ><B>Total Due</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%'  ><B>Total Balance</TD>");
				out.println("</TR></TABLE>");
				
				out.println("<TABLE width='100%' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
				
				int j=1;
				double m_4=0;
				double m_5=0;
				double m_6=0;
				double m_7=0;
				double m_8=0;
				
				//Added by Dineth on 2008-10-13
				double tot_0=0;
				double tot_30=0;
				double tot_60=0;
				double tot_90=0;
				double tot_90m=0;
				double tot_m_r=0;
				double tot_m_p=0;
				//End by Dineth on 2008-10-13
				while(more1){
					
					if(j==0){
						out.println("<tr  bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					
					
					m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
					m_r=0;
					m_p=0;
					m_r0=0;
					
					m_5=0;
					
					if(rs1.getDouble(4)>=0){
						m_r0=m_r0+rs1.getDouble(4);
						tot_0=tot_0+rs1.getDouble(4);
					}		
					
					if(rs1.getDouble(5)>=0){
						m_r30=m_r30+rs1.getDouble(5);
						tot_30=tot_30+rs1.getDouble(5);
					}
					
					if(rs1.getDouble(6)>=0){
						m_r60=m_r60+rs1.getDouble(6);
						tot_60=tot_60+rs1.getDouble(6);
					}
					if(rs1.getDouble(7)>=0){
						m_r90=m_r90+rs1.getDouble(7);
						tot_90=tot_90+rs1.getDouble(7);
					}
					if(rs1.getDouble(8)>=0){
						m_r90m=m_r90m+rs1.getDouble(8);
						tot_90m=tot_90m+rs1.getDouble(8);
					}
					
					m_r=m_r30+m_r60+m_r90+m_r90m;
					tot_m_r=tot_m_r+m_r;
					m_p=m_r+m_r0;
					tot_m_p=tot_m_p+m_p;
					out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  >"+rs1.getString(2)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  >"+rs1.getString(9)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='15%'  >"+rs1.getString(10)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('1','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r0)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('2','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r30)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('3','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r60)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('4','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('5','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90m)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%' >"+nf.format(m_r)+"</TD>"); 
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='10%'  onClick=\"show_inv_age_ana_debtor_data('6','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_p)+"</TD>");
					out.println("</TR>");	
					
					more1=rs1.next();
				}
				//Added by Dineth on 2008-10-13
				
				out.println("<tr bgcolor=\"#C0C0C0\" >");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' ></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' ></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' ><b>Total</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_0)+"</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_30)+"</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_60)+"</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_90)+"</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_90m)+"</b></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' ><b>"+nf.format(tot_m_r)+"</b></TD>"); 
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_m_p)+"</b></TD>");
				out.println("</TR>");	
				
				
				
				//End by Dineth on 2008-10-13
				
				out.println("</TABLE>");		
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE")){
				
				String m_string="";				
				String m_sql="";	
				String m_eff_date=req.getParameter("eff_date");
				String m_date_category=req.getParameter("date_category");
				String m_client_code="";
				String m_client_name="";
				//String m_client_code_temp="";
				String m_facility_no="";
				String m_facility_no_temp="";
				String m_bebotr_code="";
				String m_bebotr_code_temp="";
				
				String mm_client_code=req.getParameter("client_code");
				String mm_facility_no=req.getParameter("facility_no");
				String report_type = req.getParameter("report_type");//Added By Sandun on 22-09-2009
				String mm_temp_client_code="";
				int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
				int count=0;
				
				out.println("<HTML><HEAD><TITLE>Invoice Age Analysis Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language1.2='JavaScript' >"); 
				
				
				out.println(" function show_inv_age_ana_debtor_data(m_no,m_date,m_client_code,m_debtor_code,m_facility_no){ ");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_inv_age_analysis_deb_wise_details_data?chksql=LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE&eff_date=\"+m_date+\"&client_code=\"+m_client_code+\"&debtor_code=\"+m_debtor_code+\"&facility_no=\"+m_facility_no+\"&date_range=\"+m_no;");
				out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println(" }");
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				String m_report_heading="Invoice Age Analysis Debtor Report as at "+m_eff_date;
				
				if(m_date_category.equals("DUE_DATE")){
					
					m_report_heading=m_report_heading+" against Due Date ";
					
					
					if(report_type.equals("ALL")){
						rs1=stmt1.executeQuery("SELECT DISTINCT "+
							" A.CLIENT_CODE, "+ //1
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
							" A.FACILITY_NO, "+ //3
							"NVL(("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //4
							"NVL(("+m_schema_name+".FA_GET_INV_0_30_AMT       (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //5
							"NVL(("+m_schema_name+".FA_GET_INV_30_60_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //6
							"NVL(("+m_schema_name+".FA_GET_INV_60_90_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //7
							"NVL(("+m_schema_name+".FA_GET_INV_MORE_90_AMT    (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //8
							" A.DEBTOR_CODE, "+ //9
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
							" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
							" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
							" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%'  "+
							" GROUP BY FACILITY_NO,CLIENT_CODE,DEBTOR_CODE  "+
							" ORDER BY FACILITY_NO ASC ");
						
					}
					else{
						rs1=stmt1.executeQuery("SELECT DISTINCT "+															
							//out.println("SELECT DISTINCT "+															
							" A.CLIENT_CODE, "+ //1
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
							" A.FACILITY_NO, "+ //3
							" NVL(("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //4
							" NVL(("+m_schema_name+".FA_GET_INV_0_30_AMT       (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //5
							" NVL(("+m_schema_name+".FA_GET_INV_30_60_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //6
							" NVL(("+m_schema_name+".FA_GET_INV_60_90_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //7
							" NVL(("+m_schema_name+".FA_GET_INV_MORE_90_AMT    (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )),0), "+ //8
							" A.DEBTOR_CODE, "+ //9
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
							" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
							" AND "+m_schema_name+".FA_OP_GET_EXIST_PFC(A.CLIENT_CODE,A.FACILITY_NO)= '"+report_type+"' "+//Added By Sandun on22-09-2009
							" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
							" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%'  "+
							" GROUP BY FACILITY_NO,CLIENT_CODE,DEBTOR_CODE  "+
							" ORDER BY FACILITY_NO ASC"); //added by sn on 17-05-2010  //change by disnaka on 2011-09-06 cliet code to facility no
						
					}
					
				}                      
				
				
				
				double m_rd7m=0;
				double m_rd7=0;
				double m_r0=0;
				double m_r7=0;
				double m_r30=0;
				double m_r60=0;
				double m_r90=0;
				double m_r90m=0;
				
				double m_r=0;
				double m_p=0;
				
				boolean	 more1 = rs1.next();
				
				
				
				
				out.println("<br>");
				
				out.println("<TABLE width='100%'  ><TR class=pdn_txtpos2 width='100%'  ><TD  width='100%' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				
				
				

				
				out.println("<TABLE width='100%' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
				
				int j=1;
				double m_4=0;
				double m_5=0;
				double m_6=0;
				double m_7=0;
				double m_8=0;
				
				//Added by Dineth on 2008-10-13
				double tot_0=0;
				double tot_30=0;
				double tot_60=0;
				double tot_90=0;
				double tot_90m=0;
				double tot_m_r=0;
				double tot_m_p=0;
				
				//String m_client_code="";
				//End by Dineth on 2008-10-13
				while(more1){
					
					m_r30=0;
					m_r60=0;
					m_r90=0;
					m_r90m=0;
					m_r=0;
					m_p=0;
					m_r0=0;
					m_5=0;
					
					//m_client_code = rs1.getString(1);
					m_facility_no = rs1.getString(3);
					out.println("<TR >");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  ><B>Facility No</TD>");
					out.println("<TD COLSPAN='3' STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  ><B>"+rs1.getString(3)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  ><B>Client Name</TD>");
					out.println("<TD COLSPAN='4' STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  ><B>"+rs1.getString(2)+"</TD>");
					
					out.println("</TR>");
					
					//out.println("<TABLE width='100%' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
					out.println("<TR class=pdn_txtpos2 >");
					//out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  ><B>Client Name</TD>");
					//out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  ><B>Facility No</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  ><B>Debtor Code</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='15%'  ><B>Debtor Name</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>Not Yet Due</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>0-30 Days</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>30-60 Days </TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B>60-90 Days</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='7%'  ><B> > 90 Days</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%'  ><B>Total Due</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%'  ><B>Total Balance</TD>");
					out.println("</TR>");//</TABLE>
					
					
					tot_0=0;
					tot_30=0;
					tot_60=0;
					tot_90=0;
					tot_90m=0;
					tot_m_r=0;
					tot_m_p=0;
					
					
					while(m_facility_no.equals(rs1.getString(3))){ 
						
					//while(m_client_code.equals(rs1.getString(1))){ 	
						m_r0=0;
						m_r30=0;
						m_r60=0;
						m_r90=0;
						m_r90m=0;
						m_r=0;
						m_p=0;
						
						
						if(rs1.getDouble(4)>=0){
							m_r0=m_r0+rs1.getDouble(4);
							tot_0=tot_0+rs1.getDouble(4);
						}		
						
						if(rs1.getDouble(5)>=0){
							m_r30=m_r30+rs1.getDouble(5);
							tot_30=tot_30+rs1.getDouble(5);
						}
						
						if(rs1.getDouble(6)>=0){
							m_r60=m_r60+rs1.getDouble(6);
							tot_60=tot_60+rs1.getDouble(6);
						}
						if(rs1.getDouble(7)>=0){
							m_r90=m_r90+rs1.getDouble(7);
							tot_90=tot_90+rs1.getDouble(7);
						}
						if(rs1.getDouble(8)>=0){
							m_r90m=m_r90m+rs1.getDouble(8);
							tot_90m=tot_90m+rs1.getDouble(8);
						}
						
						m_r=m_r30+m_r60+m_r90+m_r90m;
						tot_m_r=tot_m_r+m_r;
						m_p=m_r+m_r0;
						tot_m_p=tot_m_p+m_p;
						out.println("<TR >");
						//out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='15%'  >"+rs1.getString(3)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='10%'  >"+rs1.getString(9)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='15%'  >"+rs1.getString(10)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('1','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r0)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('2','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r30)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('3','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r60)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('4','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='7%' onClick=\"show_inv_age_ana_debtor_data('5','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90m)+"</TD>");
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='10%' >"+nf.format(m_r)+"</TD>"); 
						out.println("<TD STYLE='{font: 9pt arial; text-align:right;   cursor:hand; cursor-color:blue;}' width='10%'  onClick=\"show_inv_age_ana_debtor_data('6','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_p)+"</TD>");
						out.println("</TR>");	
						
						more1=rs1.next();
						if(!more1){	break;}
						
					}
					
					
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					//out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' ></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' ></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' ><b>Total</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_0)+"</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_30)+"</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_60)+"</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_90)+"</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_90m)+"</b></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' ><b>"+nf.format(tot_m_r)+"</b></TD>"); 
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}'  ><b>"+nf.format(tot_m_p)+"</b></TD>");
					out.println("</TR>");	
					
					out.println("<tr  >");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' ></TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' >&nbsp;</TD>");
					out.println("</TR>");	
					
				}
			
				
				out.println("</TABLE>");		
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE_PRINT")){
				
				String m_string="";				
				String m_sql="";	
				String m_eff_date=req.getParameter("eff_date");
				String m_date_category=req.getParameter("date_category");
				String m_client_code="";
				String m_client_name="";
				//String m_client_code_temp="";
				String m_facility_no="";
				String m_facility_no_temp="";
				String m_bebotr_code="";
				String m_bebotr_code_temp="";
				
				String mm_client_code=req.getParameter("client_code");
				String mm_facility_no=req.getParameter("facility_no");
				String mm_temp_client_code="";
				int m_day_count1=0,m_day_count2=0,m_day_count3=0,m_day_count4=0;
				int count=0;
				
				out.println("<HTML><HEAD><TITLE>Invoice Age Analysis Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language1.2='JavaScript' >"); 
				
				
				out.println(" function show_inv_age_ana_debtor_data(m_no,m_date,m_client_code,m_debtor_code,m_facility_no){ ");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_inv_age_analysis_deb_wise_details_data?chksql=LOAD_INVOICE_AGE_ANALYSIS_REPORT_DUE_DATE&eff_date=\"+m_date+\"&client_code=\"+m_client_code+\"&debtor_code=\"+m_debtor_code+\"&facility_no=\"+m_facility_no+\"&date_range=\"+m_no;");
				out.println(" window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println(" }");
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				String m_report_heading="Invoice Age Analysis Debtor Report as at "+m_eff_date;
				
				if(m_date_category.equals("DUE_DATE")){
					
					m_report_heading=m_report_heading+" against Due Date ";
					
					
					
					rs1=stmt1.executeQuery("SELECT DISTINCT "+
						" A.CLIENT_CODE, "+ //1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //2
						" A.FACILITY_NO, "+ //3
						" ("+m_schema_name+".FA_GET_INV_NOT_YET_DUE_AMT(A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //4
						" ("+m_schema_name+".FA_GET_INV_0_30_AMT       (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //5
						" ("+m_schema_name+".FA_GET_INV_30_60_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //6
						" ("+m_schema_name+".FA_GET_INV_60_90_AMT      (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //7
						" ("+m_schema_name+".FA_GET_INV_MORE_90_AMT    (A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE, '"+m_eff_date+"' )), "+ //8
						" A.DEBTOR_CODE, "+ //9
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+ //10
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A   "+
						" WHERE A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0  "+
						" AND A.CLIENT_CODE LIKE '"+mm_client_code+"%' "+
						" AND A.FACILITY_NO LIKE '"+mm_facility_no+"%'  "+
						" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE  ");
					
				}                      
				
				
				
				double m_rd7m=0;
				double m_rd7=0;
				double m_r0=0;
				double m_r7=0;
				double m_r30=0;
				double m_r60=0;
				double m_r90=0;
				double m_r90m=0;
				
				double m_r=0;
				double m_p=0;
				
				boolean	 more1 = rs1.next();
				
				out.println("<br>");
				
				out.println("<TABLE width='1200'  ><TR class=pdn_txtpos2 width='1200'  ><TD  width='1200' STYLE='{font: 10pt arial; text-align:center;}'><B><U>"+m_report_heading+"</U><B></TD></TR></TABLE>");
				out.println("<br>");
				out.println("<br>");
				out.println("<TABLE width='1200' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
				out.println("<TR class='pdn_txtpos2' width='1200' ><TD   STYLE='{font: 9pt arial; text-align:left; }' width='200'><B>Client Name</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='100' ><B>Debtor Code</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='200' ><B>Debtor Name</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>Not Yet Due</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>0-30 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>30-60 Days </TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>60-90 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B> > 90 Days</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>Total Due</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100' ><B>Total Balance</TD>");
				out.println("</TR></TABLE>");
				
				out.println("<TABLE width='1200' BORDER=0 CELLPADDING='2' CELLSPACING='2' >");
				
				int j=1;
				double m_4=0;
				double m_5=0;
				double m_6=0;
				double m_7=0;
				double m_8=0;
				
				//Added by Dineth on 2008-10-13
				double tot_0=0;
				double tot_30=0;
				double tot_60=0;
				double tot_90=0;
				double tot_90m=0;
				double tot_m_r=0;
				double tot_m_p=0;
				//End by Dineth on 2008-10-13
				while(more1){
					
					if(j==0){
						out.println("<tr width='1200' bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr width='1200' bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					
					
					m_r30=0;m_r60=0;m_r90=0;m_r90m=0;
					m_r=0;
					m_p=0;
					m_r0=0;
					
					m_5=0;
					
					if(rs1.getDouble(4)>=0){
						m_r0=m_r0+rs1.getDouble(4);
						tot_0=tot_0+rs1.getDouble(4);
					}		
					
					if(rs1.getDouble(5)>=0){
						m_r30=m_r30+rs1.getDouble(5);
						tot_30=tot_30+rs1.getDouble(5);
					}
					
					if(rs1.getDouble(6)>=0){
						m_r60=m_r60+rs1.getDouble(6);
						tot_60=tot_60+rs1.getDouble(6);
					}
					if(rs1.getDouble(7)>=0){
						m_r90=m_r90+rs1.getDouble(7);
						tot_90=tot_90+rs1.getDouble(7);
					}
					if(rs1.getDouble(8)>=0){
						m_r90m=m_r90m+rs1.getDouble(8);
						tot_90m=tot_90m+rs1.getDouble(8);
					}
					
					m_r=m_r30+m_r60+m_r90+m_r90m;
					tot_m_r=tot_m_r+m_r;
					m_p=m_r+m_r0;
					tot_m_p=tot_m_p+m_p;
					out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='200'>"+rs1.getString(2)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='100'>"+rs1.getString(9)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='200'>"+rs1.getString(10)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('1','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r0)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('2','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r30)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('3','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r60)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('4','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('5','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_r90m)+"</TD>");
					out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100'>"+nf.format(m_r)+"</TD>"); 
					out.println("<TD STYLE='{font: 9pt arial; text-align:right; cursor:hand; cursor-color:blue;}' width='100' onClick=\"show_inv_age_ana_debtor_data('6','"+m_eff_date+"','"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+rs1.getString(3)+"')\">"+nf.format(m_p)+"</TD>");
					out.println("</TR>");	
					
					more1=rs1.next();
				}
				//Added by Dineth on 2008-10-13
				out.println("<tr width='1200' bgcolor=\"#C0C0C0\" >");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='200'></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='100'></TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='200'>Total</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_0)+"</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_30)+"</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_60)+"</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_90)+"</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_90m)+"</TD>");
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100'>"+nf.format(tot_m_r)+"</TD>"); 
				out.println("<TD STYLE='{font: 9pt arial; text-align:right;  cursor-color:blue;}' width='100' >"+nf.format(tot_m_p)+"</TD>");
				out.println("</TR>");	
				//End by Dineth on 2008-10-13
				
				out.println("</TABLE>");		
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			/*---------END MODIFICATIONS DONE BY ASHINI ON 10-10-2007-----------*/
			
			
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


