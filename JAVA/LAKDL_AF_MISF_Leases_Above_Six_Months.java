//ID         :
//SCREEN NAME:Collection - Portfolio Quality Statement
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 26-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Leases_Above_Six_Months extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_partner,stmt2,stmt_sys_date;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_partner,rs2,rs_sys_date;
 	

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

      res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
		// out.println("conn"+conn);
		
			
			//Orient Data -------------------------------------------------------------------------
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			//--------------------------------------------------------------------------------------
			
			String data="";
			String m_status="";
			int m_cum_value=0;
			double m_precentage=0;
			double m_tot_nil=0;
			double m_tot_arreas=0;
			double to_million=1000000;
			
			/*double m_tot_arrears_1=0;
			double m_tot_repayable=0;
			double m_tot_arrears_2=0;
			double m_tot_arrears_3=0;
			double m_tot_arrears_4=0;
			double m_tot_arrears_6=0;
			double m_tot_demand=0;
			double m_tot_months=0;
			*/
			
			
			
		
		/*double m_pre_0=0;
		  double m_pre_1=0;
			double m_pre_2=0;
			double m_pre_3=0;
			double m_pre_4=0;
			double m_pre_5=0;
			double m_pre_tot=100;
			
			double m_arrears_0=0;
			double m_arrears_1=0;
			double m_arrears_2=0;
			double m_arrears_3=0;
			double m_arrears_4=0;
			double m_arrears_5=0;
			
			double m_tot_month1=0;
			double m_tot_month2=0;
			double m_tot_month3=0;
			double m_tot_month4=0;
			double m_tot_month6=0;
			*/
			
			
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
		 
		 String m_generate = req.getParameter("generate");
		 String m_print=req.getParameter("print");
		 String m_month="";	
			
			if(req.getParameter("month")!=null){
		  m_month=req.getParameter("month");			
			}
		 stmt = conn.createStatement ();
	   stmt2 = conn.createStatement ();
		stmt_sys_date = 	conn.createStatement ();
		
		String m_month_2="";
		String m_Letter_date_2="";
		String m_Letter_date_month="";
		String m_Letter_date_month_2="";
		
		String m_date_dd="";
		String m_date_mm="";
		String m_date_yy="";
		String m_val_date="";

				
		double net_total=0;
		double total_1=0;
		double total_2=0;
		double	net_negot=0;
		double	net_arbit=0;
		double	net_resch=0;
		double	net_repos=0;
		double	net_rebon=0;
		
		
		int net_total_count=0;
		int total_count_2=0;
		int total_count_1=0;
		int	net_count_negot=0;
		int	net_count_arbit=0;
		int	net_count_resch=0;
		int	net_count_repos=0;
		int	net_count_rebon=0;

		

		
				rs = stmt.executeQuery ("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+m_month+"','DD-MM-YYYY'),-1),'DD-MM-YYYY') ,TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-1),'fmddth Month yyyy') LETTER_DATE, TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-1),'MONTH') FROM DUAL ");          
				boolean  more = rs.next();
				if(more){
				m_month_2=rs.getString(1);
				m_Letter_date_2=rs.getString(2);
				m_Letter_date_month_2=rs.getString(3);
				}

	
	String Sql_main_query= " SELECT "+
	            													
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('NEGOT','"+m_month_2+"') ,  "+ //1
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('ARBIT','"+m_month_2+"') ,  "+ //2
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('RESCH','"+m_month_2+"') ,  "+ //3
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('REPOS','"+m_month_2+"') ,  "+ //4
							            " "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('REBON','"+m_month_2+"') ,  "+ //5
													
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('NEGOT','"+m_month+"') ,  "+ //6
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('ARBIT','"+m_month+"') ,  "+ //7
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('RESCH','"+m_month+"') ,  "+ //8
													" "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('REPOS','"+m_month+"') ,  "+ //9
							            " "+m_schema_name+".AF_CO_GET_NET_INV_LEASE('REBON','"+m_month+"') , "+ //10
													
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('NEGOT','"+m_month_2+"') ,  "+ //11
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('ARBIT','"+m_month_2+"') ,  "+ //12
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('RESCH','"+m_month_2+"') ,  "+ //13
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('REPOS','"+m_month_2+"') ,  "+ //14
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('REBON','"+m_month_2+"') ,  "+  //15
													
													
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('NEGOT','"+m_month+"') ,  "+ //16
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('ARBIT','"+m_month+"') ,  "+ //17
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('RESCH','"+m_month+"') ,  "+ //18
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('REPOS','"+m_month+"') ,  "+ //19
													" "+m_schema_name+".AF_CO_GET_COUNT_APP_NIL('REBON','"+m_month+"')   "+  //20
													"FROM DUAL ";
													
					//============================= Add By Indika ==================================								
												
				rs_sys_date = stmt_sys_date.executeQuery ("SELECT "+
					" TO_CHAR(SYSDATE,'DD'), "+
					" TO_CHAR(SYSDATE,'MM'), "+
					" TO_CHAR(SYSDATE,'YYYY') "+
					" FROM DUAL ");
				
				if(rs_sys_date.next()){
					m_date_dd=rs_sys_date.getString(1);
					m_date_mm=rs_sys_date.getString(2);
					m_date_yy=rs_sys_date.getString(3);
				}
													
								
					//================================End by Indika ================================================
				rs = stmt.executeQuery ("SELECT TO_CHAR(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),'fmddth Month yyyy') LETTER_DATE ,TO_CHAR(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),'MONTH')   FROM DUAL ");          
			  more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				m_Letter_date_month=rs.getString(2);	
				}
				rs.close();
				
				
				
				rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							  more = rs.next();		
											
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
		 if(m_generate.trim().equals("letter")){	
			
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
		out.println("<script>");
			
			out.println("function save_data(){");
			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		 // out.println(" window.location.href=m_url;"); 
			
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
			
			
		  out.println("function load_drill(m_type){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Leases_Above_Six_Months?chksql=main_page&generate=drill_down&m_date="+m_month+"&m_type='+m_type+'';"); 
			out.println("window.open(m_url,'displayWindow2','left=110,top=60,width=690,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			
      out.println("}");
			
			
			
			
			out.println("</script>");
			
			
			
			
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
			out.println("<body bgcolor='white'><br>");
				
			
				
			out.println("<form name='Form1'>");
				
							
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
       	
			
						
			
			out.println("<font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		  out.println("</table>");

			out.println("</font></p>");	
			
			
			//&&&&&&&&&&&&&&&&&&&&&&new main report &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
      
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>Leases Above Six Months AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	
			

      out.println("<br><br>");
			/*out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='right' ><b>Amount in millions</td></tr>");
			out.println("</table>");	
			*/
			rs = stmt.executeQuery(Sql_main_query);

			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println(" <tr class=pdn_txtpos2 > ");
			out.println("<td  width=\"10%\">&nbsp;</td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>UNDER NEGOTIATIONS </td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>REFERED TO ARBITRATION </td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>RESCHEDULED</td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>VEHICLE REPOSSED </td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>IN COURTS </td> ");
			out.println("<td  width=\"15%\" STYLE='{font:  8pt bold arial; text-align:center;}' colspan=\"2\"><b>TOTAL</td> ");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2> ");
			out.println("<td>&nbsp;</td> ");
			out.println("<td width=\"5%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> ");
			out.println("<td width=\"10%\" STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> "); 
			out.println("<td width=\"5%\" STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> "); 
			out.println("<td width=\"10%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> ");
			out.println("<td width=\"5%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> ");
			out.println("<td width=\"10%\" STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> ");
			out.println("<td width=\"5%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> ");
			out.println("<td width=\"10%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> ");
			out.println("<td width=\"5%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> ");
			out.println("<td width=\"10%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> ");
			out.println("<td width=\"5%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>No</td> ");
			out.println("<td width=\"10%\"  STYLE='{font:  8pt bold arial; text-align:center;}' ><b>Amount</td> ");
			out.println("</tr> ");
			
			more=rs.next();
			if(more){
			out.println("<tr> ");
			out.println("<td>O/balance</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(11)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(rs.getDouble(1))+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(12)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(rs.getDouble(2))+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(13)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(rs.getDouble(3))+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(14)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(rs.getDouble(4))+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(15)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(rs.getDouble(5))+"</td> ");
			total_1=rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(4)+rs.getDouble(5);
			total_count_1=rs.getInt(11)+rs.getInt(12)+rs.getInt(13)+rs.getInt(14)+rs.getInt(15);
			
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >&nbsp;</td> "); 
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(total_1)+"</td> ");
			out.println("</tr> ");
			
			net_negot=rs.getDouble(6)-rs.getDouble(1);
			net_arbit=rs.getDouble(7)-rs.getDouble(2);
			net_resch=rs.getDouble(8)-rs.getDouble(3);
			net_repos=rs.getDouble(9)-rs.getDouble(4);
			net_rebon=rs.getDouble(10)-rs.getDouble(5);
			
			net_count_negot=rs.getInt(16)-rs.getInt(11);
			net_count_arbit=rs.getInt(17)-rs.getInt(12);
			net_count_resch=rs.getInt(18)-rs.getInt(13);
			net_count_repos=rs.getInt(19)-rs.getInt(14);
			net_count_rebon=rs.getInt(20)-rs.getInt(15);
			
			total_2=rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(8)+rs.getDouble(9)+rs.getDouble(10);
			total_count_2=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20);
			net_total=total_2-total_1;
			net_total_count=total_count_2 -total_count_1;
			
			out.println("<tr> ");
			out.println("<td>Net Movement</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_count_negot+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_negot)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_count_arbit+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_arbit)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_count_resch+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_resch)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_count_repos+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_repos)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_count_rebon+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_rebon)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+net_total_count+"</td> "); 
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+nf.format(net_total)+"</td> ");
			out.println("</tr> ");
			
			out.println("<tr> ");
			out.println("<td>B.C/F</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(16)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;cursor :hand;}' onClick=\"load_drill('NEGOT')\"  ><u>"+nf.format(rs.getDouble(6))+"</u></td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(17)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;cursor :hand;}' onClick=\"load_drill('ARBIT')\"  ><u>"+nf.format(rs.getDouble(7))+"</u></td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(18)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;cursor :hand;}' onClick=\"load_drill('RESCH')\"  ><u>"+nf.format(rs.getDouble(8))+"</u></td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+rs.getInt(19)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;cursor :hand;}' onClick=\"load_drill('REPOS')\"  ><u>"+nf.format(rs.getDouble(9))+"</u></td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right; }' >"+rs.getInt(20)+"</td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;cursor :hand;}' onClick=\"load_drill('REPON')\"  ><u>"+nf.format(rs.getDouble(10))+"</u></td> ");
			out.println("<td STYLE='{font:  8pt arial; text-align:right;}' >"+total_count_2+"</td> "); 
			out.println("<td STYLE='{font:  8pt arial; text-align:right}'    >"+nf.format(total_2)+"</td> ");
			out.println("</tr> ");
			
			}
			
			out.println("</table>");
			
			
		  out.println("</form></body></html>");
			}
			//view report-----------------------------------------------------------------------------------------------
			else  if(m_generate.trim().equals("view")){	
			
			
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
		out.println("<script>");
			
			out.println("function save_data(){");
			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			
			out.println("m_table.innerHTML=\"\" ");
					
		
			out.println("window.print();");
			
			
			
			
			out.println("}");
			
		
		 /* out.println("function add_button(){");
			
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
			*/
				out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Lease_Above_six_months\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Leases Above Six Months - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Leases Above Six Months - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   if(objDD.value!='' &&  objMM.value!='' && objYY.value!=''){");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("   document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("}");
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
			
			
			out.println("function help_button_View() {");
			
			out.println("if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" ){");
	    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Leases_Above_Six_Months?chksql=main_page&generate=letter&print=TRUE&month='+document.Form1.hid_date.value+'';"); 
			out.println("window.open(m_url,'displayWindow3','left=30,top=110,width=950,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");		
			out.println("else");		
			out.println("{");		
			out.println("  VDATE.style.color='red';");
			out.println("}");		
		
			out.println("}");
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Leases_Above_Six_Months?chksql=main_page&generate=view';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function assign_system_date(){	"); 
			out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
			out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
			out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
				m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
			out.println("document.Form1.hid_date.value='"+m_val_date+"'");
			out.println("}	"); 
			
			
			out.println("</script>");
			
									
				
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\">");  
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_MISF_COLLECTION_LEASES_ABOVE_SIX_MONTHS' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
						
				
				  out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Leases Above Six Months</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//		out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='but_input' style='width:150'; font-size: 20px; onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Pending Receipts\");'  onclick='View_pending_receipts()' value=\"View Pending Receipts\"></td>"); 
					 //out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='INVOICE_LINK_BUT' value=\"Pro Forma Invoice\" onClick=\"load_invoice()\" disabled></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
					
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
									  
				out.println("<tr class=tr_input>");
				out.println("<td width='30%'ID=VDATE>Month *</td>");
				out.println("<td width='40%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='BUT_RUN_QUALITY' STYLE=\"{width:110px;}\" value=\"View Report\" onClick=\"help_button_View()\"></td>"); 
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");						
					
				out.println("<hr>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
			  out.println("<tr>");  
			  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			
			  out.println("</table>");
					
					
					
				
			out.println("</form>"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body>"); 
			out.println("</html>"); 
				
			
			}
			
			
			
			else  if(m_generate.trim().equals("drill_down")){	
			
		 String m_date = req.getParameter("m_date");
		 String m_app_type=req.getParameter("m_type");
		 String m_desc="";
		 String m_code="";
			

			out.println("<html><head>"); 
			out.println("<title>Leases Above Six Months</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			String Sql_ref=" SELECT STATUS_DESC,DECODE(STATUS_CODE,'NEGOT','A','ARBIT','B','RESCH','C','REPOS','D','REBON','E') "+
               " FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+
							 " WHERE UPPER(STATUS_CODE)=UPPER('"+m_app_type+"') ";	
								
			rs = stmt.executeQuery(Sql_ref);
			more=rs.next();
			if(more){
			m_desc=rs.getString(1);
			m_code=rs.getString(2);
			}
			rs.close();

			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B> "+m_desc+" </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
			
			
			String Sql_drill=" SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),SUM(NIL) ,"+m_schema_name+".AF_CO_GET_NIL_ARR(FINANCE_NO,'"+m_app_type+"','"+m_date+"')"+
			" FROM "+
			" ( "+
			" SELECT  B.FINANCE_NO FINANCE_NO,B.APPLICATION_NO APPLICATION_NO,B.CLIENT_CODE CLIENT_CODE ,NVL(SUM(A.CAPITAL_AMOUNT),0) NIL   "+
			" FROM  "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B ,"+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE C "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND   A.INVOICE_NO IS NULL  "+
			" AND   (B.FINANCE_NO , STATUS ) IN ( "+
			" SELECT FINANCE_NO,MAX(STATUS) "+
			" FROM  "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE  "+
			" WHERE EFF_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			" AND   APPROVE_STATUS='Y' "+
			" AND   STATUS='"+m_app_type+"' "+
			" GROUP BY FINANCE_NO) "+
			" GROUP BY B.FINANCE_NO,B.APPLICATION_NO,B.CLIENT_CODE "+
			
			" UNION ALL "+
			
			" SELECT  B.FINANCE_NO FINANCE_NO,B.APPLICATION_NO APPLICATION_NO,B.CLIENT_CODE CLIENT_CODE ,NVL(SUM(A.CAPITAL_AMOUNT),0) NIL   "+
			" FROM  "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B ,"+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE C "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND   B.FINANCE_NO=C.FINANCE_NO "+
			" AND   (B.FINANCE_NO , STATUS ) IN ( "+
			" SELECT FINANCE_NO,MAX(STATUS) "+
			" FROM  "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE "+
			" WHERE EFF_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			" AND   APPROVE_STATUS='Y' "+
			" AND   STATUS='"+m_app_type+"' "+
			" GROUP BY FINANCE_NO) "+
			" AND   A.INVOICE_NO IN ( "+ 
			 
			" SELECT A.INVOICE_NO  "+
			" FROM "+
			" (SELECT INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE  "+
			" WHERE  /* ADD_MONTHS(DUE_DATE,6) <=  LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
			" AND  */ ACTIVE_STATUS='Y' "+
			" AND   INVOICE_TYPE='INV_GENER' "+
			" AND   FINANCE_NO IN ( "+
			" SELECT DISTINCT A.FINANCE_NO   "+
			"  FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE C "+
			 " WHERE "+
			"           A.FINANCE_NO=B.FINANCE_NO  "+
			"      AND  B.FINANCE_NO=C.FINANCE_NO "+
			"      AND   (B.FINANCE_NO , STATUS ) IN ( "+
			"      SELECT FINANCE_NO,MAX(STATUS) "+
			"      FROM  "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE "+
			"      WHERE EFF_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			"      AND   APPROVE_STATUS='Y' "+
			"      AND   STATUS='"+m_app_type+"' "+
			"      GROUP BY FINANCE_NO)  "+
			 "     AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
			//" AND  ADD_MONTHS(DUE_DATE,6) <=  LAST_DAY(TO_DATE('25-09-2007','DD-MM-YYYY'))
			" AND  A.ACTIVE_STATUS='Y' "+
			" AND  A.INVOICE_TYPE='INV_GENER' "+
			" ) "+
			" GROUP BY INVOICE_NO) A, "+
			 
			" (SELECT INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0)  SETTELED_AMOUNT "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS  "+
			" WHERE "+
			" /*ADD_MONTHS(ALLOCATED_DATE,6) <=   LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
			" AND */   INVOICE_NO IN  "+
			" (SELECT DISTINCT INVOICE_NO  "+
			" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE "+
			" WHERE "+
			" INVOICE_TYPE='INV_GENER' "+
			" AND  ACTIVE_STATUS='Y' "+
			" AND  FINANCE_NO IN ( "+
			" SELECT DISTINCT A.FINANCE_NO   "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE C "+
			"    WHERE "+
			"           A.FINANCE_NO=B.FINANCE_NO  "+
			"      AND  B.FINANCE_NO=C.FINANCE_NO "+
			"     AND   (B.FINANCE_NO , STATUS ) IN ( "+
			" SELECT FINANCE_NO,MAX(STATUS) "+
			" FROM  "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE "+
			" WHERE EFF_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			" AND   APPROVE_STATUS='Y' "+
			" AND   STATUS='"+m_app_type+"' "+
			" GROUP BY FINANCE_NO) "+
			    
			" AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
			//      --AND  ADD_MONTHS(DUE_DATE,6) <=  LAST_DAY(TO_DATE('25-09-2007','DD-MM-YYYY'))
			" AND  A.ACTIVE_STATUS='Y' "+
			" AND  A.INVOICE_TYPE='INV_GENER' "+
			" )) "+
			 
			" GROUP BY INVOICE_NO) B "+
			" WHERE A.INVOICE_NO=B.INVOICE_NO "+
			" AND   A.TOTAL_AMOUNT - SETTELED_AMOUNT > 0  )"+
			" GROUP BY B.FINANCE_NO,B.APPLICATION_NO,B.CLIENT_CODE "+
			" ) "+
			" WHERE NIL >0 "+
			" GROUP BY  FINANCE_NO,APPLICATION_NO,CLIENT_CODE ";

			
			out.println("<table align='center' width='100%'  BORDER=\"1\" cellspacing=\"0\"  class='table'>"); 
			
			out.println("<tr>");  
			
			out.println("<td width='5%' ><b>No</td>"); 
			out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='15%' ><b>Finance No</td>"); 
			out.println("<td width='35%' ><b>Client Name </td>");  
			out.println("<td width='15%' align='right' ><b>NIL</td>");  
			out.println("<td width='15%' align='right' ><b>ARR</td>");  
			out.println("</tr>"); 
			
			rs = stmt.executeQuery(Sql_drill);
      more=rs.next();
			int i=1;
			double total_nil=0;
			double total_arr=0;
      while(more){
			
			out.println("<tr>");  
			out.println("<td width='5%' ><b>"+i+"</td>"); 
			out.println("<td width='5%' >"+m_code+"</td>"); 
			out.println("<td width='15%' style=\"{cursor :hand;}\" onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td width='35%' style=\"{cursor :hand;}\" onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4)+"</u></td>");  
			out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(5))+"</td>");  
			out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");  
			out.println("</tr>"); 
			i=i+1;
			total_nil+=rs.getDouble(5);
			total_arr+=rs.getDouble(6);
			more=rs.next();
			}

			out.println("<tr>");  
			out.println("<td width='5%'  >&nbsp;</td>"); 
			out.println("<td width='5%'  >&nbsp;</td>"); 
			out.println("<td width='15%' >&nbsp;</td>"); 
			out.println("<td width='35%' >&nbsp;</td>");  
			out.println("<td width='15%' align='right'><b>"+nf.format(total_nil)+"</td>");  
			out.println("<td width='15%' align='right'><b>"+nf.format(total_arr)+"</td>");  
			out.println("</tr>"); 
			
			out.println("</table>");  
			out.println("<br><br>");  
			out.println("<table align='left' width='100%'  BORDER=\"0\" cellspacing=\"0\"  class='table'>"); 
			out.println("<tr>");  
      out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='5%'  >A</td>");
			out.println("<td width='30%' >NEGOTIATIONS</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
      out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='5%'  >B</td>");
			out.println("<td width='30%' >REFERRED TO ARBITRATION</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
      out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='5%'  >C</td>");
			out.println("<td width='30%' >RE-SCHEDULED</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
      out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='5%'  >D</td>");
			out.println("<td width='30%' >REPOSSED VEHICLES</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
      out.println("<td width='5%' >&nbsp;</td>"); 
			out.println("<td width='5%'  >E</td>");
			out.println("<td width='30%' >VEHICLES RELEASED UNDER BONDS</td>"); 
			out.println("</tr>");  
			out.println("</table>");  
	
				
			out.println("</form>"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 


			out.println("</body>"); 
			out.println("</html>"); 
				
			
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
