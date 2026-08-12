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

public class LAKDL_AF_RE_Portfolio_Quality_Statement extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_partner,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_partner,rs2;
 	

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
		  nf.setMinimumFractionDigits(0);
		  nf.setMaximumFractionDigits(0);
			
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
			//double to_million=1000000;
			double to_million=1;

			
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
		 String m_division_code="";	
			if(req.getParameter("month")!=null){
		  m_month=req.getParameter("month");			
			}
			
			if(req.getParameter("division_code")!=null){
		  m_division_code=req.getParameter("division_code");			
			}
			
		 stmt = conn.createStatement ();
	   stmt2 = conn.createStatement ();
			
		
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

	
	
	String Sql_main_query= " SELECT "+
	                        " 'One contracts' ,"+ //1
													" "+m_schema_name+".AF_CO_GET_NIL(1,'"+m_month+"','"+m_division_code+"') ,  "+ //2
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(0,'"+m_month+"','"+m_division_code+"') ,  "+ //3
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(1,'"+m_month+"','"+m_division_code+"') ,  "+ //4
													
													" 'Two contracts' ,"+ //5
													" "+m_schema_name+".AF_CO_GET_NIL(2,'"+m_month+"','"+m_division_code+"') ,  "+ //6
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(0,'"+m_month+"','"+m_division_code+"')  , "+ //7
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(1,'"+m_month+"','"+m_division_code+"')  , "+ //8
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(2,'"+m_month+"','"+m_division_code+"')  , "+ //9
													
													" 'Three contracts' ,"+ //10
													" "+m_schema_name+".AF_CO_GET_NIL(3,'"+m_month+"','"+m_division_code+"') ,  "+ //11
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(0,'"+m_month+"','"+m_division_code+"') ,  "+ //12
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(1,'"+m_month+"','"+m_division_code+"') ,  "+ //13
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(2,'"+m_month+"','"+m_division_code+"') ,  "+ //14
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(3,'"+m_month+"','"+m_division_code+"') ,  "+ //15
													
													" 'Four to Five contracts' ,"+ //16
													" "+m_schema_name+".AF_CO_GET_NIL(4,'"+m_month+"','"+m_division_code+"') ,  "+ //17
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(0,'"+m_month+"','"+m_division_code+"') ,  "+ //18
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(1,'"+m_month+"','"+m_division_code+"') ,  "+ //19
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(2,'"+m_month+"','"+m_division_code+"') ,  "+ //20
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(3,'"+m_month+"','"+m_division_code+"') ,  "+ //21
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(4,'"+m_month+"','"+m_division_code+"') ,  "+ //22
													
													" 'Over Six' ,"+ //23
													" "+m_schema_name+".AF_CO_GET_NIL(6,'"+m_month+"','"+m_division_code+"') ,  "+ //24
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(0,'"+m_month+"','"+m_division_code+"')  , "+ //25
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(1,'"+m_month+"','"+m_division_code+"')  , "+ //26
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(2,'"+m_month+"','"+m_division_code+"')  , "+ //27
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(3,'"+m_month+"','"+m_division_code+"')  , "+ //28
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(4,'"+m_month+"','"+m_division_code+"')  , "+ //29
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(6,'"+m_month+"','"+m_division_code+"')  , "+ //30
													
													" "+m_schema_name+".AF_CO_GET_NIL(0,'"+m_month+"','"+m_division_code+"') ,  "+ //31
													" "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR(0,'"+m_month+"','"+m_division_code+"')   "+ //32
													
													"FROM DUAL ";
													
													
				rs = stmt.executeQuery ("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+m_month+"','DD-MM-YYYY'),-1),'DD-MM-YYYY') ,TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-1),'fmddth Month yyyy') LETTER_DATE, TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-1),'MONTH') FROM DUAL ");          
				boolean  more = rs.next();
				if(more){
				m_month_2=rs.getString(1);
				m_Letter_date_2=rs.getString(2);
				m_Letter_date_month_2=rs.getString(3);
				}
													
		String Sql_main_query_2= " SELECT "+
	                        " 'One contracts' ,"+ //1
													" "+m_schema_name+".AF_CO_GET_NIL(1,'"+m_month_2+"','"+m_division_code+"') ,  "+ //2
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"') ,  "+ //3
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(1,'"+m_month_2+"','"+m_division_code+"') ,  "+ //4
													
													" 'Two contracts' ,"+ //5
													" "+m_schema_name+".AF_CO_GET_NIL(2,'"+m_month_2+"','"+m_division_code+"') ,  "+ //6
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"')  , "+ //7
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(1,'"+m_month_2+"','"+m_division_code+"')  , "+ //8
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(2,'"+m_month_2+"','"+m_division_code+"')  , "+ //9
													
													" 'Three contracts' ,"+ //10
													" "+m_schema_name+".AF_CO_GET_NIL(3,'"+m_month_2+"','"+m_division_code+"') ,  "+ //11
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"') ,  "+ //12
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(1,'"+m_month_2+"','"+m_division_code+"') ,  "+ //13
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(2,'"+m_month_2+"','"+m_division_code+"') ,  "+ //14
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(3,'"+m_month_2+"','"+m_division_code+"') ,  "+ //15
													
													" 'Four to Five contracts' ,"+ //16
													" "+m_schema_name+".AF_CO_GET_NIL(4,'"+m_month_2+"','"+m_division_code+"') ,  "+ //17
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"') ,  "+ //18
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(1,'"+m_month_2+"','"+m_division_code+"') ,  "+ //19
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(2,'"+m_month_2+"','"+m_division_code+"') ,  "+ //20
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(3,'"+m_month_2+"','"+m_division_code+"') ,  "+ //21
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(4,'"+m_month_2+"','"+m_division_code+"') ,  "+ //22
													
													" 'Over Six' ,"+ //23
													" "+m_schema_name+".AF_CO_GET_NIL(6,'"+m_month_2+"','"+m_division_code+"') ,  "+ //24
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"')  , "+ //25
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(1,'"+m_month_2+"','"+m_division_code+"')  , "+ //26
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(2,'"+m_month_2+"','"+m_division_code+"')  , "+ //27
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(3,'"+m_month_2+"','"+m_division_code+"')  , "+ //28
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(4,'"+m_month_2+"','"+m_division_code+"')  , "+ //29
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(6,'"+m_month_2+"','"+m_division_code+"')  , "+ //30
													
												  " "+m_schema_name+".AF_CO_GET_NIL(0,'"+m_month_2+"','"+m_division_code+"') ,  "+ //31
													" "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR(0,'"+m_month_2+"','"+m_division_code+"')   "+ //32

													
													"FROM DUAL ";
													
								
					//================================================================================
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
				out.println("<title>Portfolio Quality Statement</title></head>");
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
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b><u>"+m_orient_name.toUpperCase()+"</u></td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT - "+m_division_code+"</td></tr>");
			out.println("</table>");	
			out.println("<br><br>");
			
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='20%' class='rep-body' align='left' >To</td>");
			out.println("<td width='*%' class='rep-body' align='left' >The Board of Directors</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Through</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Director/CEO</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >From</td>");
			out.println("<td width='*%' class='rep-body' align='left' >AGM - Recoveries</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Subject</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Portfolio Quality</td></tr>");
			
			out.println("</table>");	
			
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	


			
			
      out.println("<br><br>");
			/*out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='right' ><b>Amount in millions</td></tr>");
			out.println("</table>");
			*/
      out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" > "); 
			out.println("<tr class=pdn_txtpos2> ");
			out.println("<td width=\"16%\" STYLE='{font:  8pt bold arial; text-align:center;}'>&nbsp;</td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>No Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>1 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>2 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>3 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>4-5 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'> <b>>=6 Months Arrears </td> ");
			out.println(" </tr>  ");
			out.println(" <tr class=pdn_txtpos2> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' >&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println(" </tr> ");
		  
			rs2 = stmt2.executeQuery(Sql_main_query_2);
			
		boolean	more2=rs2.next();
			if(more2){
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance B/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(32)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(31)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(3)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(2)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(7)+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(6)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(12)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(11)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(18)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(17)/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs2.getInt(25)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs2.getDouble(24)/to_million)+"</td> ");
			out.println(" </tr> ");
			}
			
			rs = stmt.executeQuery(Sql_main_query);
			more=rs.next();
			
			if(more){
			
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_contracts      =rs.getInt(3)+rs.getInt(7)+rs.getInt(12)+rs.getInt(18)+rs.getInt(25)+rs.getInt(32);
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Net Movement</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(32)-rs2.getInt(32))+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(31)-rs2.getDouble(31))/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(3)-rs2.getInt(3))+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(2)-rs2.getDouble(2))/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(7)-rs2.getInt(7))+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(6)-rs2.getDouble(6))/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(12)-rs2.getInt(12))+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(11)-rs2.getDouble(11))/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(18)-rs2.getInt(18))+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(17)-rs2.getDouble(17))/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(rs.getInt(25)-rs2.getInt(25))+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(24)-rs2.getDouble(24))/to_million)+"</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance C/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
				
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears contracts as % of total reveivalbe</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(31)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(2)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(6)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(11)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(17)/total_nil)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(24)/total_nil)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears as % of total contracts</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(32)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(3)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(7)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(12)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(18)/total_contracts)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(25)/total_contracts)*100)+"%</td> ");
			out.println(" </tr> ");
			
			//cumulative value
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><B>CUMULATIVE VALUE % - "+m_Letter_date_month+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			m_cum=rs.getDouble(31);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE VALUE % - "+m_Letter_date_month_2+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17)+rs2.getDouble(24)+rs2.getDouble(31);
			m_cum=rs2.getDouble(31);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17)+rs2.getDouble(24);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			
			//comulative contract 
						
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><B>CUMULATIVE CONTRACT % - "+m_Letter_date_month+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18)+rs.getDouble(25)+rs.getDouble(32);
			m_cum=rs.getDouble(32);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18)+rs.getDouble(25);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE CONTRACT % - "+m_Letter_date_month_2+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18)+rs2.getDouble(25)+rs2.getDouble(32);
			m_cum=rs2.getDouble(32);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18)+rs2.getInt(25);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NO OF CONTRACTS</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NET INVESTMENT IN LEASE</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			}
			out.println(" </table> ");
			
			rs.close();
			rs2.close();
			
			///&&&&&&&&&&&&&&&&&&&&&&&end new main report&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//New Report 
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			/*out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='right' ><b>Amount in millions</td></tr>");
			out.println("</table>");
			*/
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			rs = stmt.executeQuery(Sql_main_query);
			more=rs.next();

     if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value+=rs.getInt(32);
	
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			
			//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");
			
			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//New Report  previous month
			
		
													
			
			out.println("<br>");
			out.println("<hr color='black'>");
			out.println("<br>");
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			//out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_2+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			
			/*out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='right' ><b>Amount in millions</td></tr>");
			out.println("</table>");	
			*/
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			rs = stmt.executeQuery(Sql_main_query_2);
			more=rs.next();

    if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value+=rs.getInt(32);
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");

			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//New Report  previous month
			String m_month_3="";
			String m_Letter_date_3="";
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+m_month+"','DD-MM-YYYY'),-2),'DD-MM-YYYY') ,TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-2),'fmddth Month yyyy') LETTER_DATE  FROM DUAL ");          
				 more = rs.next();
				if(more){
				m_month_3=rs.getString(1);
				m_Letter_date_3=rs.getString(2);
				}
				rs.close();									
		String Sql_main_query_3= " SELECT "+
	                        " 'One contracts' ,"+ //1
													" "+m_schema_name+".AF_CO_GET_NIL(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //2
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //3
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //4
													
													" 'Two contracts' ,"+ //5
													" "+m_schema_name+".AF_CO_GET_NIL(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //6
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')  , "+ //7
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"')  , "+ //8
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"')  , "+ //9
													
													" 'Three contracts' ,"+ //10
													" "+m_schema_name+".AF_CO_GET_NIL(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //11
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //12
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //13
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //14
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //15
													
													" 'Four to Five contracts' ,"+ //16
													" "+m_schema_name+".AF_CO_GET_NIL(4,'"+m_month_3+"','"+m_division_code+"') ,  "+ //17
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //18
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //19
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //20
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //21
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(4,'"+m_month_3+"','"+m_division_code+"') ,  "+ //22
													
													" 'Over Six' ,"+ //23
													" "+m_schema_name+".AF_CO_GET_NIL(6,'"+m_month_3+"','"+m_division_code+"') ,  "+ //24
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')  , "+ //25
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"')  , "+ //26
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"')  , "+ //27
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"')  , "+ //28
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(4,'"+m_month_3+"','"+m_division_code+"')  , "+ //29
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(6,'"+m_month_3+"','"+m_division_code+"')  , "+ //30
													
													" "+m_schema_name+".AF_CO_GET_NIL(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //31
													" "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')   "+ //32
													
													"FROM DUAL ";
													
			
			out.println("<br>");
			out.println("<hr color='black'>");
			out.println("<br>");
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			//out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_3+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			
			/*out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='right' ><b>Amount in millions</td></tr>");
			out.println("</table>");	
			*/
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			
			rs = stmt.executeQuery(Sql_main_query_3);
			more=rs.next();

    if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value=rs.getInt(32);

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
					//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");
	
		
		
			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
													
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
			out.println("    m_help_message = \"m_help_msg_AF_RE_Portfolio_Quality_Statement\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Portfoliio Quality Statement - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Portfoliio Quality Statement - \"+document.Form1.hid_status.value;"); 
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
			
			//out.println("alert('tets'+m_month);");
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("}"); 
			out.println("else {"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement?chksql=main_page&generate=letter&print=TRUE&division_code='+document.Form1.TXT_DIVISION_CODE.value+'&month='+document.Form1.hid_date.value+'';"); 
			out.println("window.open(m_url,'displayWindow3','left=30,top=110,width=950,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
		  out.println("}"); 
			
			out.println("}");
			
			out.println("function help_button_View_Test() {");
			//out.println("alert('tets'+m_month);");
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("}"); 
			out.println("else {"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement_New?chksql=main_page&generate=run_report&print=TRUE&division_code='+document.Form1.TXT_DIVISION_CODE.value+'&month='+document.Form1.hid_date.value+'';"); 
			out.println("window.open(m_url,'displayWindow3','left=30,top=110,width=950,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		  out.println("}"); 
			
			out.println("}");

			
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement?chksql=main_page&generate=view';"); 
			out.println("		}"); 
			out.println("}"); 
			
			
			
			out.println("</script>");
			
									
				
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">");  
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='' > ");
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Portfoliio Quality Statement</td>"); 
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
				out.println("<td width='20%'ID=VDATE>Date as at *</td>"); //Month 
				out.println("<td width='40%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("    <input class='but_input' type='button' name='BUT_RUN_QUALITY' value=\"View Report\" onClick=\"help_button_View()\" style=\"{width:110px;}\">"); 
				out.println("    <input class='but_input' type='button' name='BUT_RUN_QUALITY' value=\"View Report Test\" onClick=\"help_button_View_Test()\" style=\"{width:110px;}\"></td>"); 
				out.println("<td width='*%'>");
				//out.println("<input class='but_input' type='button' name='BUT_RUN_QUALITY' value=\"View\" onClick=\"help_button_View()\"></td>"); 
				out.println("</td>");
				out.println("</tr>");
				
				//added by nwuan de silva on 30-10-07-------------------------------------------------------------
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Division Code</DIV></td>"); 
				out.println("<td width='40%' ><SELECT onchange=\"\" name=\"TXT_DIVISION_CODE\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"AF\" SELECTED>Other</OPTION>");
				out.println("<OPTION value=\"BD\">Bike</OPTION>");
				out.println("</SELECT></td>");
				out.println("<td width='*%'></td>"); 
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
