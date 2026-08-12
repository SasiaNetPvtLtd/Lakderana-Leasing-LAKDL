//ID         :
//SCREEN NAME:Invoice Letters
//CREATED BY :Nuwan De Silva	
//DATE/TIME  :
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Invoice_Letters extends javax.servlet.http.HttpServlet { 

ServletOutputStream out = null;
	
	
Connection conn;
Statement stmt,stmt1,stmt2,stmt3;
//	CallableStatement callstmt1;
java.text.NumberFormat nf;
	
public ResultSet rs;
public ResultSet rs1,rs2,rs3;
	

public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_invoice_no,m_client_no,m_no_of_due_date,m_finance_no,m_print,m_inv_type,m_inv_no,m_vat_reg_no,m_vat_reg_date,m_value_date;
public double m_amount_due;
public double m_gross_rent;
public String m_LAKDL_vat_no="";
public String m_vat_precentage="";
	
	
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
try { 
		
	//	 	BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
		//	reqstr = input.readLine();   	
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			int m_count=0;
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
		//	m_LAKDL_vat_no="134010762-7000";
		//	m_vat_precentage="15";
			
			
	//	 out.println(reqstr);
						
			m_chksql=req.getParameter("chksql");
			m_invoice_no	  =req.getParameter("invoice_no");		
			m_client_no	      =req.getParameter("client_no");		
			m_finance_no	=req.getParameter("finance_no");		
			m_print=req.getParameter("print");
			m_inv_type=req.getParameter("inv_type");
			m_value_date=req.getParameter("value_date");
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();			
			
			 rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE, "+
							" VAT_REG_NO "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							boolean  more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_vat_precentage=rs.getString(7);			
											m_LAKDL_vat_no=rs.getString(8);			
											}


										
				if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
								
			else if (m_chksql.trim().equals("main_page")) {
			
						
				
			  out.println("<html><head>"); 
				out.println("<title>Invoice Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  	
			
			out.println("<script>");
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_code+"&no_of_due_date="+m_no_of_due_date+"&scr_name=AF_RE_RPT_COLLECTION_DUE;); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Invoice_Letters?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_no+"&scr_name=AF_RE_INVOICE_LETTERS&finance_no="+m_finance_no+"&inv_type="+m_inv_type+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			
			//out.println("m_table.innerHTML=\"\" ");
		
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
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
				out.println("<body bgcolor='white'><br>");
				
				
				out.println("<form name='Form1'>");
							
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
       
					
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
				more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
			
				
		rs1 = stmt1.executeQuery (	" SELECT "+
    " CLIENT_CODE, "+
    " FULL_NAME, "+
    " ADDRESS1, "+
    " ADDRESS2, "+
    " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_NAME ,"+
		" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
		" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
    " WHERE CLIENT_CODE='"+m_client_no+"' ");
		
		

					
			more = rs1.next();
			if(more){
			m_c_code=rs1.getString(1);
			m_name=rs1.getString(2);
			m_add1=rs1.getString(3);
			m_add2=rs1.getString(4);
			m_city_desc=rs1.getString(5);
			m_vat_reg_no=rs1.getString(6);
			
			}
			
			rs1 = stmt1.executeQuery (	" SELECT "+
  	" COUNT(VAT_REG_NO) "+
		" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
    " WHERE CLIENT_CODE='"+m_client_no+"' AND TO_DATE((TO_CHAR(VAT_REG_DATE,'DD-MM-YYYY')),'DD-MM-YYYY')<=TO_DATE('"+m_value_date+"','DD-MM-YYYY')  ");
			
			more = rs1.next();
			
			if(more){
			m_count=rs1.getInt(1);
			}
			
		
		
			if (m_vat_reg_no.trim().equals("-") || m_count==0 ) {
			
						
   		out.println("<font size=3><p style='text-align:	center' class='rep-body' >");				
						
			out.println("<table border='0' width='100%' class='table' style='text-align:	center'>"); 		
			out.println("<tr><td width='*%' class='rep-body'><b>Invoice</b></td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
			
			out.println("<font size=2><p style='text-align:left' class='rep-body'>");				
						
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>Date &nbsp; "+m_Letter_date+"</td></tr>");
			
			out.println("<tr><td width='60%' class='rep-body' >"+m_name+"</td><td width='20%' class='rep-body' >OFSCL VAT No &nbsp;&nbsp;</td><td width='20%' align='right' class='rep-body' >"+m_LAKDL_vat_no+"</td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' >"+m_add1+",</td><td width='20%' class='rep-body'>Invoice No   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='20%' align='right' class='rep-body' >"+m_invoice_no+"</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
			out.println("<br><br><br>");
				
			
		rs2 = stmt2.executeQuery (" SELECT "+
    " INVOICE_NO, "+
    " TO_CHAR(DUE_DATE,'DD/MM/YYYY'), "+
		" NET_AMOUNT, "+
		" VAT_AMOUNT ,"+
    " TOTAL_AMOUNT, "+
		" TO_CHAR(DUE_DATE,'MON YYYY') "+
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE GROUP_INV_NO=UPPER('"+m_invoice_no+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' ");
   		
			more = rs2.next();
			
			out.println("<font size=2><p style='text-align:left' class='rep-body'>");
				
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='50%'class='rep-body'>Rental Due For the Month </td><td width='*%'class='rep-body'>-&nbsp;&nbsp;  "+rs2.getString(6)+"  </td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body' style='text-align:right'>(Amount In Rupees)</td></tr>");			
			out.println("</tr>");
			out.println("</table>");
			
						
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>---------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'>S1No</td><td width='17%' class='rep-body'>Finance No</td><td width='16%' class='rep-body'>Invoice No</td><td width='10%' class='rep-body'>Due Date</td><td width='9%' class='rep-body' style='text-align:right' >Net Rent</td><td width='8%' class='rep-body' style='text-align:right'>VAT %</td><td width='10%' class='rep-body' style='text-align:right'>VAT</td><td width='10%' class='rep-body' style='text-align:right'>Gross Rent</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>---------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			
			int i=1;
			
			double sum_gross=0;
			double sum_net_rent=0;
			double sum_vat=0;
			

			while(more)
			{
			
			out.println("<table border='0' width='100%' class='table'>");	
		//	out.println("<tr><td width='5%' class='rep-body'>"+i+"</td><td width='20%' class='rep-body'>"+rs2.getString(1)+"</td><td width='20%' class='rep-body'>"+rs2.getString(2)+"</td><td width='20%' class='rep-body' >&nbsp;</td><td width='15%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(3))+"</td></tr>");
			out.println("<tr><td width='5%' class='rep-body'>"+i+"</td><td width='17%' class='rep-body'>"+m_finance_no+"</td><td width='16%' class='rep-body'>"+rs2.getString(1)+"</td><td width='10%' class='rep-body'>"+rs2.getString(2)+"</td><td width='9%' class='rep-body' style='text-align:right' >"+nf.format(rs2.getDouble(3))+"</td><td width='8%' class='rep-body' style='text-align:right'>"+m_vat_precentage+" %</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(4))+"</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(5))+"</td></tr>");
			out.println("</table>");
			
			i=i+1;
			sum_net_rent =sum_net_rent+rs2.getDouble(3);
			sum_vat =sum_vat+rs2.getDouble(4);
			sum_gross =sum_gross+rs2.getDouble(5);
     	more=rs2.next();
			}
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='*%' class='rep-body' align='right'>---------------------------------------------------------</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='10%' class='rep-body'>Total</td><td width='9%' class='rep-body' style='text-align:right' >"+nf.format(sum_net_rent)+"</td><td width='8%' class='rep-body' style='text-align:right'></td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_vat)+"</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_gross)+"</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='*%' class='rep-body' align='right'>---------------------------------------------------------</td></tr>");
			out.println("</table>");

			

			out.println("</font></p>");		
			
			
			
			
			out.println("<font size=2><p style='text-align:	center' class='rep-body'>");				
						
			out.println("<table border='0' width='100%' class='table' style='text-align:center'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>TIMELY PAYMENT WOULD BE APPRECIATED</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
						
			
		String data="Cheque payments should be made only in favour of Lakderana  Investments  Limited "+
                " - Crossed Account payee only.<br>";
      
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
			
             data="If the payment is made by a third party cheque, it will be accepted by us "+
                  "only if it is endorsed by the lessee.<br>";
									
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");						
			
            data="In the event you are settling the payment in cash, please ensure that you "+
			           "obtain receipt immediately from our officer.<br>";
     
			
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
		         data="Official receipts for cheque/cash payments will be posted within 7 days. If "+
                  "not received, please bring this to the notice of Senior Manager Finance.<br>";
	
	    out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
				
		out.println("</font></p></blockquote>");		
		
			}
			
			else if(m_count==1) {
		
		 out.println("<font size=2><p style='text-align:	center' class='rep-body'>");				
						
			out.println("<table border='0' width='100%' class='table' style='text-align:	center'>"); 		
			out.println("<tr><td width='*%'class='rep-body'><b>Tax Invoice<b></td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
			
			out.println("<font size=2><p style='text-align:left'>");				
			
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>Date &nbsp; "+m_Letter_date+"</td></tr>");
			//out.println("<br><br>");
			out.println("<tr><td width='60%' class='rep-body' >"+m_name+"</td><td width='20%' class='rep-body' >OFSCL VAT No &nbsp;&nbsp;</td><td width='20%' align='right' class='rep-body' >"+m_LAKDL_vat_no+"</td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' >"+m_add1+",</td><td width='20%' class='rep-body'>Invoice No   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='20%' align='right' class='rep-body' >"+m_invoice_no+"</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
			out.println("<br><br><br>");
			
							
			out.println("<font size=2><p style='text-align:left'>");		
			
		rs2 = stmt2.executeQuery (" SELECT "+
    " INVOICE_NO, "+
    " TO_CHAR(DUE_DATE,'DD/MM/YYYY'), "+
		" NET_AMOUNT, "+
		" VAT_AMOUNT ,"+
    " TOTAL_AMOUNT, "+
		" TO_CHAR(DUE_DATE,'MON YYYY') "+
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE GROUP_INV_NO=UPPER('"+m_invoice_no+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' ");
		
   		
			more = rs2.next();
		
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='40%'class='rep-body'>Customer's VAT No.       </td><td width='*%'class='rep-body'>-&nbsp;&nbsp;  "+m_vat_reg_no+"  </td>");
			out.println("<tr><td width='40%'class='rep-body'>Rental Due For the Month </td><td width='*%'class='rep-body'>-&nbsp;&nbsp;  "+rs2.getString(6)+"  </td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body' style='text-align:right'>(Amount In Rupees)</td></tr>");			
			out.println("</tr>");
			out.println("</table>");
			
			
		
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>---------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'>S1No</td><td width='17%' class='rep-body'>Finance No</td><td width='16%' class='rep-body'>Invoice No</td><td width='10%' class='rep-body'>Due Date</td><td width='9%' class='rep-body' style='text-align:right' >Net Rent</td><td width='8%' class='rep-body' style='text-align:right'>VAT %</td><td width='10%' class='rep-body' style='text-align:right'>VAT</td><td width='10%' class='rep-body' style='text-align:right'>Gross Rent</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>---------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			
	
			int i=1;
			
			double sum_gross=0;
			double sum_net_rent=0;
			double sum_vat=0;
			
			while(more)
			{
			
			out.println("<table border='0' width='100%' class='table'>");	
		//	out.println("<tr><td width='5%' class='rep-body'>"+i+"</td><td width='20%' class='rep-body'>"+rs2.getString(1)+"</td><td width='20%' class='rep-body'>"+rs2.getString(2)+"</td><td width='20%' class='rep-body' >&nbsp;</td><td width='15%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(3))+"</td></tr>");
			out.println("<tr><td width='5%' class='rep-body'>"+i+"</td><td width='17%' class='rep-body'>"+m_finance_no+"</td><td width='16%' class='rep-body'>"+rs2.getString(1)+"</td><td width='10%' class='rep-body'>"+rs2.getString(2)+"</td><td width='9%' class='rep-body' style='text-align:right' >"+nf.format(rs2.getDouble(3))+"</td><td width='8%' class='rep-body' style='text-align:right'>"+m_vat_precentage+" %</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(4))+"</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(5))+"</td></tr>");
			out.println("</table>");
			
			i=i+1;
			sum_net_rent =sum_net_rent+rs2.getDouble(3);
			sum_vat =sum_vat+rs2.getDouble(4);
			sum_gross =sum_gross+rs2.getDouble(5);
     	more=rs2.next();
			}
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='*%' class='rep-body' align='right'>---------------------------------------------------------</td></tr>");
			out.println("</table>");

			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='10%' class='rep-body'>Total</td><td width='9%' class='rep-body' style='text-align:right' >"+nf.format(sum_net_rent)+"</td><td width='8%' class='rep-body' style='text-align:right'></td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_vat)+"</td><td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_gross)+"</td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='17%' class='rep-body'></td><td width='16%' class='rep-body'></td><td width='*%' class='rep-body' align='right'>---------------------------------------------------------</td></tr>");
			out.println("</table>");

	
			out.println("</font></p></blockquote>");		
				
			out.println("<font size=2><p style='text-align:	center' class='rep-body'>");				
						
			out.println("<table border='0' width='100%' class='table' style='text-align:	center'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>TIMELY PAYMENT WOULD BE APPRECIATED</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
						
			
				out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
		//	out.println("<table border='0' width='90%' class='table' style='text-align:	justify'>"); 		
					
		
		String data="Cheque payments should be made only in favour of Lakderana Investments Limited "+
                "- Crossed Account payee only.<br>";
      
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
			
             data="If the payment is made by a third party cheque, it will be accepted by us "+
                  "only if it is endorsed by the lessee.<br>";
									
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");						
			
            data="In the event you are settling the payment in cash, please ensure that you "+
			           "obtain receipt immediately from our officer.<br>";
     
			
			out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
		         data="Official receipts for cheque/cash payments will be posted within 7 days. If "+
                  "not received, please bring this to the notice of Senior Manager Finance.<br>";
	
	    out.println("<table border='0' width='100%' class='table' style='text-align:justify'>"); 		
			out.println("<tr><td width='2%'class='rep-body'>*</td>");
			out.println("<td width='*%'class='rep-body'>"+data+"</td></tr>");
			out.println("</table>");
			
	/*	out.println("* Cheque payments should be made only in favour of Orient Financial Services "+
                    " Corporation Ltd - Crossed &nbsp;&nbsp;&nbsp;Account payee only.<br>");
                  
     out.println("<br>* If the payment is made by a third party cheque, it will be accepted by us "+
                  "only if it is endorsed by the &nbsp;&nbsp;&nbsp;lessee.<br>");
									
     out.println("<br>* In the event you are settling the payment in cash, please ensure that you "+
			            "obtain receipt immediately &nbsp;&nbsp;&nbsp;from our officer.<br>");
     
		 out.println("<br>* Official receipts for cheque/cash payments will be posted within 7 days. If "+
                  "not received, please bring this &nbsp;&nbsp;&nbsp;to the notice of Senior Manager Finance.<br>");
	*/
				
		out.println("</font></p>");	
			
			
			
			}
			
											
		  out.println("</form></body></html>");
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
