//ID         :
//SCREEN NAME:Collection - LAKDL_AF_MISF_Collection_Movement_Report_Age
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 26-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Collection_Movement_Report_Age extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;

  public ResultSet rs,rs2,rs1;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String header_name = m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username; //added by nuwan de silva 19-09-07------------------


      res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			//Orient Data -------------------------------------------------------------------------
			String m_orient_name="",m_orient_add1="",m_orient_add2="",m_orient_city_name="",m_orient_tel_no="",m_orient_fax_no="",m_orient_vat_rate="";
			//--------------------------------------------------------------------------------------
			String data="",m_status="",m_print="";
			int m_cum_value=0;
			double m_precentage=0;
			double m_tot_nil=0;
			double m_tot_arreas=0;
			double to_million=1000000;
	
			
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
		 
		 String m_generate = req.getParameter("generate");
		 //String m_print=req.getParameter("print");
		 String m_month="";	
			
			if(req.getParameter("date")!=null){
		  m_month=req.getParameter("date");			
			}
			
		 stmt = conn.createStatement ();
			
		String m_month_2="";
		String m_Letter_date_2="";
		String m_Letter_date_month="";
		String m_Letter_date_month_2="";
		double m_sub_total_arrears=0;
		double arr_precentage=0;
		double total_nil=0;
		double total_one_month_arr=0;
		double total_two_month_arr=0;
		double total_three_month_arr=0;
		double total_four_month_arr=0;
		double total_six_month_arr=0;
		int total_contracts=0;
		double total=0;
		double m_cum=0;
				
				rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

						boolean	 more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_orient_vat_rate=rs.getString(7);			
											}
											
			rs.close();						
		 if(m_generate.trim().equals("VIEW_REPORT")){	
			
		String m_date="",m_team_head="",m_officer="",m_prev_date="";
		String m_prev_month="",m_curr_month="",m_curr_date="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		if(req.getParameter("team_head")!=null ){
		m_team_head=req.getParameter("team_head").trim();
		}
		if(req.getParameter("officer")!=null ){
		m_officer=req.getParameter("officer").trim();
		}
		
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			                        ", TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
															", TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			                        ", TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
															", TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
															" FROM DUAL ");          
			   more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				m_prev_date=rs.getString(2);	
				m_prev_month=rs.getString(3);	
				m_curr_date=rs.getString(4);	
				m_curr_month=rs.getString(5);	
				}
				rs.close();
				
		String Sql_data="SELECT"+
		" SELECT A.TEAM_HEAD, A.COLLECTION_OFFICER,COUNT(*) NO_OF_CONTRACT, CEIL(A.AGE_ARREAS), SUM(A.CAPITAL_OUTSTANDING)"+
		" FROM "+m_schema_name+".AF_RE_TBD_COLL_MOVE_RPT_AGE a "+
		" GROUP BY A.TEAM_HEAD,A.COLLECTION_OFFICER,(A.AGE_ARREAS) ORDER BY TEAM_HEAD  ";
		

		
		
		
/*		String Sql_data="SELECT  "+
"			DISTINCT A.COLLECTION_OFFICER COLLECTION_OFFICER, "+ //1
"			TEAM_HEAD, "+ //2
"			"+m_schema_name+".AF_CO_GET_EMP_NAME(TEAM_HEAD) TEAM_HEAD_NAME, "+ //3
"			"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //4

"     "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //5
"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //6
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //7
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //8
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //9
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //10

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //11
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) , "+ //12
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //13
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //14
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //15

"     "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //16
"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //17
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //18
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //19
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //20
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //21

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //22
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) , "+ //23
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //24
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //25
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //26
"     "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC("+m_schema_name+".AF_CO_GET_DESIGNATION_CODE(A.COLLECTION_OFFICER)), "+ //27

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //28
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) , "+ //29
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //30
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //31
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //32

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //33
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) , "+ //34
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //35
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //36
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER)   "+ //37


"			FROM "+m_schema_name+".AF_CO_MAS_TEAMS X, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS Y,"+
"     "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
"			WHERE  A.APPLICATION_STATUS='ACTIVATED' "+
"			AND A.COLLECTION_OFFICER=Y.USER_ID "+
"			AND X.TEAM_ID=Y.TEAM_ID "+
"			AND X.ACTIVE_STATUS='Y' "+
"			AND Y.ACTIVE_STATUS='Y' "+
"			AND UPPER(A.COLLECTION_OFFICER) LIKE UPPER('"+m_officer+"%') "+ 
"			AND UPPER(TEAM_HEAD) LIKE UPPER('"+m_team_head+"%') "+ 
"     GROUP BY A.COLLECTION_OFFICER,TEAM_HEAD"+ //,Z.EMP_ID,LOCATION_CODE 
"     ORDER BY TEAM_HEAD,A.COLLECTION_OFFICER ";
*/
		
			
			  out.println("<html><head>"); 
				out.println("<title>Collection Movement Report - With Ageing </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			out.println("var timerID;");
			out.println("var durationID=0;");

			
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("}");
		
		  out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
						
			out.println("}");
			
			
			//added by nuwan de silva 17-09-07--------------------------------------------------------
			out.println(" function load_contract_details(m_team_id,m_collection_officer,m_date){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=LOAD_INVOICE_AGE_ANALYSIS_REPORT_DRILL&collection_officer=\"+m_collection_officer+\"&team_id=\"+m_team_id+\"&date=\"+m_date;");
			out.println(" window.open(m_url,'popupwin2','status=0,menubar=0,scrollbars=1,height=500,width=1200');");
			out.println(" }");
			
					
			out.println("</script>");
			
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");//add_button()
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<body bgcolor='white'><br>");
				
			out.println("<form name='Form1'>");
							
				 /*out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			  */
			   out.println("</table>");
       	
			out.println("<font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		  out.println("</table>");
			out.println("</font></p>");	
						
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
      out.println("<br>");
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			out.println("<tr class=pdn_txtpos2 ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total Arr. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			
			out.println("<tr bgcolor='lightgrey' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			 Sql_data="";
			
      //--------------------------------------------------------------------------------------------------
		  rs = stmt.executeQuery(Sql_data);
			int total_count=0;
			int tot_without_zero=0;
			more =rs.next();
			
			
			while(more){
			m_team_head=rs.getString(1);
			
			while(!m_team_head.equals(rs.getString(1))){
			String m_col_officer=rs.getString(2);
			
			while(!m_col_officer.equals(rs.getString(2))){
		  
			}
			
			}
			
			
			}
			
			
			
			
		//	double total=0;
			while (more){
			
			total=rs.getDouble(11)+rs.getDouble(12)+rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			total_count=rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)+rs.getInt(10);
			tot_without_zero=total_count-rs.getInt(5);
			//Previos Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" onClick=\"show_employee_drill('"+rs.getString(1)+"')\" class='rep-body' align='left'><u>"+rs.getString(4)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>"+rs.getString(27)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(11))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(12))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(13))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(15))+"</td>");
		  out.println("</tr>");
			//End Previos Month ---------------------------------------------------------------------------
			total=rs.getDouble(22)+rs.getDouble(23)+rs.getDouble(24)+rs.getDouble(25)+rs.getDouble(26);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(22))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(23))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(24))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(25))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(26))+"</td>");
		  out.println("</tr>");
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			total=rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30)+rs.getDouble(31)+rs.getDouble(32);
			total_count=rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)+rs.getInt(10);
			tot_without_zero=total_count-rs.getInt(5);
			//Current Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(28))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(29))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(30))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(31))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(32))+"</td>");
		  out.println("</tr>");
			//-------------------------------------------------------------------------------------------
			
			total=rs.getDouble(33)+rs.getDouble(34)+rs.getDouble(35)+rs.getDouble(36)+rs.getDouble(37);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			//Previous Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(33))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(34))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(35))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(36))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(37))+"</td>");
		  out.println("</tr>");

			
			//End Capital Out Standing --------------------------------------------------------------------------------------------------

			out.println("<tr bgcolor='#993300' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			more=rs.next();
			}
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			out.println("</table>");
      out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");													
		  out.println("</form></body></html>");
			}
			
			
		if(m_generate.trim().equals("print_report")){	
			
		String m_date="",m_team_head="",m_officer="",m_prev_date="";
		String m_prev_month="",m_curr_month="",m_curr_date="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		if(req.getParameter("team_head")!=null ){
		m_team_head=req.getParameter("team_head").trim();
		}
		if(req.getParameter("officer")!=null ){
		m_officer=req.getParameter("officer").trim();
		}
		
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			                        ", TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
															", TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			                        ", TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
															", TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
															" FROM DUAL ");          
			   more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				m_prev_date=rs.getString(2);	
				m_prev_month=rs.getString(3);	
				m_curr_date=rs.getString(4);	
				m_curr_month=rs.getString(5);	
				}
				rs.close();

		
		
		
		String Sql_data="SELECT  "+
"			DISTINCT A.COLLECTION_OFFICER COLLECTION_OFFICER, "+ //1
"			TEAM_HEAD, "+ //2
"			"+m_schema_name+".AF_CO_GET_EMP_NAME(TEAM_HEAD) TEAM_HEAD_NAME, "+ //3
"			"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //4

"     "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //5
"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //6
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //7
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //8
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //9
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(0,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //10

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //11
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) , "+ //12
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //13
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //14
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(1,'"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //15

"     "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //16
"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //17
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //18
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //19
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //20
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(0,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //21

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //22
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) , "+ //23
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //24
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //25
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF(1,'"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //26
"     "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC("+m_schema_name+".AF_CO_GET_DESIGNATION_CODE(A.COLLECTION_OFFICER)), "+ //27

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //28
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) , "+ //29
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //30
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //31
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF('CAP','"+m_prev_date+"',A.COLLECTION_OFFICER) ,  "+ //32

"     "+m_schema_name+".AF_CO_GET_ONE_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //33
"     "+m_schema_name+".AF_CO_GET_TWO_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) , "+ //34
"     "+m_schema_name+".AF_CO_GET_THR_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //35
"     "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR_COL_OF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER) ,  "+ //36
"     "+m_schema_name+".AF_CO_GET_SIX_MON_ARR_COL_OFF('CAP','"+m_curr_date+"',A.COLLECTION_OFFICER)   "+ //37


"			FROM "+m_schema_name+".AF_CO_MAS_TEAMS X, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS Y,"+
//"     "+m_schema_name+".CO_CO_MAS_USER Z,"+
"     "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
"			WHERE  A.APPLICATION_STATUS='ACTIVATED' "+
"			AND A.COLLECTION_OFFICER=Y.USER_ID "+
//"			AND Y.USER_ID=Z.USER_ID "+
"			AND X.TEAM_ID=Y.TEAM_ID "+
"			AND X.ACTIVE_STATUS='Y' "+
"			AND Y.ACTIVE_STATUS='Y' "+
//"			AND Z.ACTIVE_STATUS='Y' "+
"			AND UPPER(A.COLLECTION_OFFICER) LIKE UPPER('"+m_officer+"%') "+ 
"			AND UPPER(TEAM_HEAD) LIKE UPPER('"+m_team_head+"%') "+ 
"     GROUP BY A.COLLECTION_OFFICER,TEAM_HEAD"+ //,Z.EMP_ID,LOCATION_CODE 
"     ORDER BY TEAM_HEAD,A.COLLECTION_OFFICER ";

		
			
			  out.println("<html><head>"); 
				out.println("<title>Collection Movement Report - With Ageing </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("}");
		
		  out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
						
			out.println("}");
			
			
			//added by nuwan de silva 17-09-07--------------------------------------------------------
			out.println(" function load_contract_details(m_team_id,m_collection_officer,m_date){ ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=LOAD_INVOICE_AGE_ANALYSIS_REPORT_DRILL&collection_officer=\"+m_collection_officer+\"&team_id=\"+m_team_id+\"&date=\"+m_date;");
			out.println(" window.open(m_url,'popupwin2','status=0,menubar=0,scrollbars=1,height=500,width=1200');");
			out.println(" }");
			
					
			out.println("</script>");
			
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");//add_button()
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<body bgcolor='white'><br>");
				
			out.println("<form name='Form1'>");
							
				 /*out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			  */
			   out.println("</table>");
       	
			out.println("<font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		  out.println("</table>");
			out.println("</font></p>");	
						
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
      out.println("<br>");
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			out.println("<tr class=pdn_txtpos2 ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total Arr. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			
			out.println("<tr bgcolor='lightgrey' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
      //--------------------------------------------------------------------------------------------------
		  rs = stmt.executeQuery(Sql_data);
			int total_count=0;
			int tot_without_zero=0;
			more =rs.next();
		//	double total=0;
			while (more){
			
			total=rs.getDouble(11)+rs.getDouble(12)+rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			total_count=rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)+rs.getInt(10);
			tot_without_zero=total_count-rs.getInt(5);
			//Previos Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" onClick=\"show_employee_drill('"+rs.getString(1)+"')\" class='rep-body' align='left'><u>"+rs.getString(4)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>"+rs.getString(27)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(11))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(12))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(13))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(15))+"</td>");
		  out.println("</tr>");
			//End Previos Month ---------------------------------------------------------------------------
			total=rs.getDouble(22)+rs.getDouble(23)+rs.getDouble(24)+rs.getDouble(25)+rs.getDouble(26);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(22))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(23))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(24))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(25))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(26))+"</td>");
		  out.println("</tr>");
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			total=rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30)+rs.getDouble(31)+rs.getDouble(32);
			total_count=rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)+rs.getInt(10);
			tot_without_zero=total_count-rs.getInt(5);
			//Current Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(28))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(29))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(30))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(31))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(32))+"</td>");
		  out.println("</tr>");
			//-------------------------------------------------------------------------------------------
			
			total=rs.getDouble(33)+rs.getDouble(34)+rs.getDouble(35)+rs.getDouble(36)+rs.getDouble(37);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			//Previous Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(33))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(34))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(35))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(36))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(37))+"</td>");
		  out.println("</tr>");

			
			//End Capital Out Standing --------------------------------------------------------------------------------------------------

			out.println("<tr bgcolor='#993300' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			more=rs.next();
			}
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			out.println("</table>");
      out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");													
		  out.println("</form></body></html>");
			}
			
			//added by nuwan de silva on 17-09-07---------------------------------
			else if(m_generate.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT_DRILL")){
			
			String m_collection_officer=req.getParameter("collection_officer").trim();
			String m_team_id=req.getParameter("team_id").trim();
			//String m_location=req.getParameter("location").trim();
			String m_date=req.getParameter("date").trim();
				
			stmt1=conn.createStatement();
			
			
		/*	String sql_drill="SELECT  "+
			"	DISTINCT A.COLLECTION_OFFICER COLLECTION_OFFICER, "+ //1
			"	"+m_schema_name+".AF_CO_GET_USER_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //2
			"	TEAM_HEAD, "+ //3
			"	"+m_schema_name+".AF_CO_GET_USER_NAME(TEAM_HEAD) TEAM_HEAD_NAME, "+ //4
			" A.FINANCE_NO ,"+ //5
			" A.CLIENT_CODE ,"+ //6
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)  ,"+ //7
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('0', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('1', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //9
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('2', A.FINANCE_NO,  '"+m_date+"'),0)    , "+  //10
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('3', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //11
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('4', A.FINANCE_NO,  '"+m_date+"'),0)    , "+  //12
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('5', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //13
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('6', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //14
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('7', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //15
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('8', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //16
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('9', A.FINANCE_NO , '"+m_date+"'),0)    , "+ //17
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('10',A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //18
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('11',A.FINANCE_NO,  '"+m_date+"'),0)      "+ //19
			
			"			FROM "+m_schema_name+".AF_CO_MAS_TEAMS X, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS Y,"+m_schema_name+".CO_CO_MAS_USER Z,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			"			WHERE  A.APPLICATION_STATUS='ACTIVATED' "+
			"			AND A.COLLECTION_OFFICER=Z.USER_ID "+
			"			AND Y.USER_ID=Z.USER_ID "+
			"			AND X.TEAM_ID=Y.TEAM_ID "+
			"			AND X.ACTIVE_STATUS='Y' "+
			"			AND Y.ACTIVE_STATUS='Y' "+
			"			AND Z.ACTIVE_STATUS='Y' "+
			"			AND UPPER(A.COLLECTION_OFFICER)=UPPER('"+m_collection_officer+"') "+ 
			"			AND UPPER(TEAM_HEAD)=UPPER('"+m_team_id+"') "+ 
			"     GROUP BY A.COLLECTION_OFFICER,TEAM_HEAD,A.FINANCE_NO,A.CLIENT_CODE  "+ //,Z.EMP_ID,LOCATION_CODE 
			"     ORDER BY TEAM_HEAD,A.COLLECTION_OFFICER ";
			*/
			
			
			String sql_drill="SELECT  "+
			" A.FINANCE_NO,"+ //1
			" A.CLIENT_CODE,"+ //2
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'),"+ //3
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('0', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //4
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('1', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //5
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('2', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //6
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('3', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //7
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('4', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('5', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //9
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('6', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //10
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('7', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //11
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('8', A.FINANCE_NO,  '"+m_date+"'),0)    , "+ //12
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('9', A.FINANCE_NO , '"+m_date+"'),0)    ,  "+ //13
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('10',A.FINANCE_NO,  '"+m_date+"'),0)    ,  "+ //14
			" NVL("+m_schema_name+".AF_CO_GET_INV_AMOUNT('11',A.FINANCE_NO,  '"+m_date+"'),0)       "+ //15
			" FROM "+
			" (SELECT FINANCE_NO,CLIENT_CODE,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
			" WHERE "+
			" INVOICE_TYPE='INV_GENER' "+
			" AND TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
			" AND ACTIVE_STATUS='Y' "+
			" AND FINANCE_NO IN ( "+
			" SELECT DISTINCT A.FINANCE_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE "+
			" A.FINANCE_NO=B.FINANCE_NO "+
			" AND  B.APPLICATION_STATUS='ACTIVATED'      "+
			" AND TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
			" AND  UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_collection_officer+"') "+
			" AND  A.ACTIVE_STATUS='Y' "+
			" AND  A.INVOICE_TYPE='INV_GENER' "+
			" ) "+
			" GROUP BY FINANCE_NO,CLIENT_CODE ) A, "+
			
			
			" (SELECT FINANCE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
			" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B "+
			" WHERE "+
			" A.INVOICE_NO=B.INVOICE_NO "+
			" AND TO_DATE(TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
			" AND    A.INVOICE_NO IN  ( "+
			" SELECT DISTINCT INVOICE_NO "+
			" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE "+
			" WHERE TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
			" AND  ACTIVE_STATUS='Y' "+
			" AND  FINANCE_NO IN ( "+
			
			" SELECT DISTINCT A.FINANCE_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE "+
			" A.FINANCE_NO=B.FINANCE_NO "+
			" AND  B.APPLICATION_STATUS='ACTIVATED'      "+
			" AND  UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_collection_officer+"') "+
			" AND  A.ACTIVE_STATUS='Y' "+
			" AND  A.INVOICE_TYPE='INV_GENER' "+
			" )) "+
			" GROUP BY  FINANCE_NO)B "+
			
			" WHERE A.FINANCE_NO=B.FINANCE_NO(+) "+
			" AND NVL(TOTAL_AMOUNT,0) - NVL(SETTELED_AMOUNT,0) > 0 ";
			

					out.println("<HTML><HEAD><TITLE>Invoice Analysis Details - Collection Officer : "+m_collection_officer+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='1440' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Invoice Analysis Details as at "+m_date+" - Collection Officer : "+m_collection_officer+" </B></TD></TR>");
					//out.println("<TR bgcolor=#FFFF99><TD><CENTER><B>Location : "+m_location+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
	        rs1=stmt1.executeQuery(sql_drill);

					more = rs1.next();			
					double tot=0;
					int i=0;
					double tot_cur_month=0;
					double tot_1_month=0;
					double tot_2_month=0;
					double tot_3_month=0;
					double tot_4_month=0;
					double tot_5_month=0;
					double tot_6_month=0;
					double tot_7_month=0;
					double tot_8_month=0;
					double tot_9_month=0;
					double tot_10_month=0;
					double tot_11_month=0;
					double grand_tot=0;
					if(more){
					out.println("<TABLE width='1440' BORDER=1 CELLPADDING=0 CELLSPACING=1 >");
					out.println("<TR class=pdn_txtpos2 >");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='30'>No</TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='200'>Client Name </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='150'>Agreement No </TD>");
					//out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='50'>O/S</TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>Cur.Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>1 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>2 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80' >3 - Month  </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>4 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>5 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80' >6 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>7 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>8 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80' >9 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>10 - Month </TD>");
					out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='80'>11 >=  </TD>");
          out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='100'>Total</TD>");
					//out.println("<TD STYLE='{font: bold 8pt arial; text-align:left;}' width='100'>Precentage</TD>");
					out.println("</TR>");
					}
					
					while(more){
					tot=0; 
					i=i+1;
					out.println("<TR  >");
					out.println("<TD STYLE='{font:  8pt arial; text-align:left;}' width='30' >"+i+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:left;cursor:hand;}' onClick=\"show_client('"+rs1.getString(2)+"')\"  width='200'><u>"+rs1.getString(3)+"</u></TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:left;cursor:hand;}' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  width='150'><u>"+rs1.getString(1)+"</u></TD>");
					//out.println("<TD STYLE='{font:  8pt arial; text-align:right;cursor:hand;}' width='50' onClick=\"show_rent_balance_to_be_received_drill('"+rs1.getString(22)+"')\"  ><u>"+nf.format(rs1.getDouble(7))+"</u></TD>");
					//out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >&nbsp;</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(4))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(5))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(6))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(7))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(8))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(9))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(10))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(11))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(12))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(13))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(14))+"</TD>");
					out.println("<TD STYLE='{font:  8pt arial; text-align:right;}' width='80' >"+nf.format(rs1.getDouble(15))+"</TD>");
					tot=rs1.getDouble(4)+rs1.getDouble(5)+rs1.getDouble(6)+rs1.getDouble(7)+rs1.getDouble(8)+rs1.getDouble(9)+rs1.getDouble(10)+
					    rs1.getDouble(11)+rs1.getDouble(12)+rs1.getDouble(13)+rs1.getDouble(14)+rs1.getDouble(15);
					
					tot_cur_month+=rs1.getDouble(4);
					tot_1_month  +=rs1.getDouble(5);
					tot_2_month  +=rs1.getDouble(6);
					tot_3_month  +=rs1.getDouble(7);
					tot_4_month  +=rs1.getDouble(8);
					tot_5_month  +=rs1.getDouble(9);
					tot_6_month  +=rs1.getDouble(10);
					tot_7_month  +=rs1.getDouble(11);
					tot_8_month  +=rs1.getDouble(12);
					tot_9_month  +=rs1.getDouble(13);
					tot_10_month +=rs1.getDouble(14);
					tot_11_month +=rs1.getDouble(15);
					grand_tot+=tot;
					
          out.println("<TD STYLE='{font: bold 8pt arial; text-align:right;}' width='100'>"+nf.format(tot)+"</TD>");
					out.println("</TR>");
							
					
					more = rs1.next();			
					}
					
					
					out.println("<TR  >");
					out.println("<TD STYLE='{font:  8pt arial; text-align:left;}' width='30' >&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:left;cursor:hand;}'  width='200'>&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:left;cursor:hand;}'  width='150'>Total</u></TD>");
					//out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='50' >&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_cur_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_1_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_2_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_3_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_4_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_5_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_6_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_7_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_8_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_9_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_10_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format(tot_11_month)+"</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='100'>"+nf.format(grand_tot)+"</TD>");
					out.println("</TR>");
					
					out.println("<TR >");
					out.println("<TD STYLE='{font:  8pt arial; text-align:left;}' width='30' >&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:left;cursor:hand;}'  width='200'>&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:left;cursor:hand;}'  width='150'>Precentage</u></TD>");
					//out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='50' >&nbsp;</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_cur_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_1_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_2_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_3_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_4_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_5_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_6_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_7_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_8_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_9_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_10_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='80' >"+nf.format((tot_11_month/grand_tot)*100)+"%</TD>");
					out.println("<TD STYLE='{font:  bold 8pt arial; text-align:right;}' width='100'>"+nf.format((grand_tot/grand_tot)*100)+"%</TD>");
					out.println("</TR>");
					
					out.println("</TABLE>");
					out.println("</form>"); 
		      out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

					
			
			
			
			}
			
			
			
			//view report-----------------------------------------------------------------------------------------------
			else  if(m_generate.trim().equals("view")){	
			
 	    stmt2 = conn.createStatement ();
			rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");


			///////////////////////
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
				out.println("var timerID;");
				out.println("var durationID=0;");

			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("     team_help();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("			document.Form1.TEAM_HEAD.value=data_vec[0]");
			out.println("			}");
			
		 	out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
			out.println("     help_button_user(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
			out.println("			document.Form1.TXT_USER.value=data_vec[0]");
			out.println("			}");
			out.println("}");
			
			/*  out.println("function sort_data(m_sort_col) {");
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
	      out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
			*/
			
				out.println("function drill_down_asset(m_finance_no) {");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");	
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
		
			out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_team_User&data_val=\"+obj.value+\"&ac_status=Y\";");	
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=view';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=view';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report_Ageing\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		team_assign(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_user(oBj);"); 
	  	out.println("		}"); 
			
								
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear 
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			out.println(""); 
				
			out.println("function team_help() {"); 
			out.println("    m_sql = \"m_help_TXT_TEAM_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
			out.println("}"); 
				
				out.println("function team_assign(oBj){");
				out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[4]");
				out.println(" document.Form1.HID_TEAM_ID.value =oBj.valout[2]");
				out.println("}");
				
			out.println("function help_button_user() {"); 
			out.println(" document.Form1.hid_help_type.value='3' ");
			out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
			//out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_team_user_id_sql','3');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_team_user_id_sql','3');"); 
			
			out.println("}"); 
			

			out.println("function help_value_assign_user(oBj) {"); 
			out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
			out.println("    document.Form1.TEAM_HEAD.value=oBj.valout[5];"); 
			out.println(" document.Form1.HID_TEAM_ID.value =oBj.valout[4]");
			out.println("}"); 
			
			out.println("function clear_data(IfCount) {");
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TEAM_HEAD.value='';");
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("document.Form1.TXT_USER.value='';");
	  	out.println("		}"); 
			out.println("}");
			
     
			out.println("function get_rental_dates(date,m_client_code,m_officer){");
			
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("}"); 
			
			/*out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("document.Form1.BUT_PRINT.disabled=false;");
			
			out.println("}");
			*/
			
			
			 	out.println("function view_report_2() {");
			 	out.println("if(validate_data()){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=VIEW_REPORT&team_head=\"+document.Form1.HID_TEAM_ID.value+\"&officer=\"+document.Form1.TXT_USER.value+\"&date=\"+m_date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}");	


			 out.println("function print_report(date,m_team_head,m_officer) {");
			 	out.println("if(validate_data()){");
			  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=print_report&team_head=\"+m_team_head+\"&officer=\"+m_officer+\"&date=\"+date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}");	
				
				out.println("function get_vector_normal(m_data){");
				out.println("alert('m_data'+m_data);");
				out.println("		if(m_data==\"OK\"){");
				out.println("			view_report_2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");

				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age?chksql=main_page&generate=run_report&date=\"+m_date+\"&team_head=\"+document.Form1.HID_TEAM_ID.value+\"&coll_officer=\"+document.Form1.TXT_USER.value;");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");


			
			out.println("function ckeck_new_date(){ "); 
			out.println("b_flag=0;");
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_date_selected()){"); 
			out.println("alert('Please enter new date');");
			out.println("b_flag=1;");
			out.println("}"); 
						
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			

			out.println("function count_date_selected(){ ");
			out.println("count=0;");
			out.println("var arr_size=document.Form1.hid_no_rec.value;");
		
			out.println("for(i=0;i<arr_size;i++){");
			out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
			out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
			out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
			
			out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
			
					
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
		
		
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
		//	out.println("alert('date'+document.Form1.hid_date.value);");
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			out.println("if(checkMonthLength(objDD,objMM,objYY))");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			
			out.println("}");
			
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
				out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
				out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
				out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
			  out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 

			
				
				
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
			out.println("<input type=hidden name='hid_cal_date' value=\"\">");
			out.println("<input type=hidden name='hid_row_no' value=\"\">");
			out.println("<input type=hidden name='hid_date' value=\"\">");
			out.println("<input type=hidden name='HID_TEAM_ID' value=\"\">");

			
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Movement Report - With Ageing </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			 /*
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
			  out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				//out.println("<td width='*%'>");
				//out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"get_rental_dates(document.Form1.hid_date.value,document.Form1.CLIENT_CODE.value,document.Form1.TXT_USER.value)\">"); 
				//out.println("<td width='*%'><input class='but_input' type='button' name='BUT_PRINT' value=\"View\" onClick=\"print_report(document.Form1.hid_date.value,document.Form1.TEAM_HEAD.value,document.Form1.TXT_USER.value)\" ></td>"); 
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TEAM_HEAD'  class=div_input>Collection Team Head </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TEAM_HEAD' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest(document.Form1.TEAM_HEAD)\">"); 
				out.println("</td>");
				
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TEAM_HEAD' value=\"Help\" onClick=\"team_help()\">"); 
				out.println("</td>");
				
				out.println("</tr>"); 
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Collection Team Officer </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' style=\"{width:110px;}\" value=\"View Report\" onClick=\"print_report(document.Form1.hid_date.value,document.Form1.TEAM_HEAD.value,document.Form1.TXT_USER.value)\" ></td>"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"view_report(document.Form1.hid_date.value,document.Form1.TEAM_HEAD.value,document.Form1.TXT_COLLECTION_OFFICER.value)\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");

				out.println("</td>");
				out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>"); 
			
			*/
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
			out.println("<tr class=tr_input>");
			out.println("<td width='20%'ID=VDATE>Date As At *</td>");
			out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
			out.println("</td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
				
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
      out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			
			////////////////////////
			
			}
			
			
		else if(m_generate.equals("run_report")){ 
		String m_date="";
		String m_team_head="";
		String m_col_officer="";
			
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date");
		}
		if(req.getParameter("team_head")!=null ){
		m_team_head=req.getParameter("team_head");
		}
		if(req.getParameter("coll_officer")!=null ){
		m_col_officer=req.getParameter("coll_officer");
		}
		
		try{
		callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+".AF_RE_SAVE_COLL_MOVE_RPT_AGE(:1,:2,:3,:4);END;");
		callstmt1.setString(1 ,m_date);
		callstmt1.setString(2 ,m_team_head);
		callstmt1.setString(3 ,m_col_officer);
		callstmt1.setString(4 ,m_username);				
		callstmt1.execute();
		out.print("OK"); 
		}
		catch(Exception ex){
		out.println("ERROR"+ex.toString()); 
		}
		}
			
			
			}
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
