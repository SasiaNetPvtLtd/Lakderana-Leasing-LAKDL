// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 2011-09-12 10:45:15 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LAKDL_AF_RE_PRO_drill_downs_five.java

import java.io.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

public class LAKDL_AF_RE_PRO_drill_downs_five extends HttpServlet {
	
	// commented by udara 19-05-2017
	/*
	LAKDL_AF_CO_conn_methods LAKDL_af_co_conn_methods;
	
	Connection connection;
	CallableStatement callableStatement;
	String sql;
	
	String m_schema_name;
	String m_username;
	String m_servlet_client_url;
	String m_html_client_url;
	String m_client_name;
	
	String application_no;
	String finance_no;
	String client_code;
	
	double contract_balance = 0.0D;
	
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt_invoice;
	Statement stmt3;
	NumberFormat nf;
	NumberFormat nf1;
	Math a;
	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;
	public ResultSet rs_invoice;
	public ResultSet rs3;
	public String m_chksql;
	*/
	
	
	//public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {   // commented by udara 19-05-2017
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException { // added by udara 19-05-2017
		
		
		// added by udara 19-05-2017
		
		LAKDL_AF_CO_conn_methods LAKDL_af_co_conn_methods;
		
		Connection connection = null;
		CallableStatement callableStatement= null;
		String sql= null;
		
		String m_schema_name= null;
		String m_username= null;
		String m_servlet_client_url= null;
		String m_html_client_url= null;
		String m_client_name= null;
		
		String application_no= null;
		String finance_no= null;
		String client_code= null;
		
		double contract_balance = 0.0D;
		
		Statement stmt= null;
		Statement stmt1= null;
		Statement stmt2= null;
		Statement stmt_invoice= null;
		Statement stmt3= null;
		NumberFormat nf= null;
		NumberFormat nf1= null;
		Math a= null;
		ResultSet rs= null;
		ResultSet rs1= null;
		ResultSet rs2= null;
		ResultSet rs_invoice= null;
		ResultSet rs3= null;
		String m_chksql= null;
		
		// end by udara 19-05-20174
		
		
		
		connection = null;
		callableStatement = null;
		
		m_schema_name = "";
		m_username = "";
		m_servlet_client_url = "";
		m_html_client_url = "";
		m_client_name = "";
		
		sql = null;
		
		try {
			
			LAKDL_af_co_conn_methods = new LAKDL_AF_CO_conn_methods();
			connection = LAKDL_af_co_conn_methods.met_user_validate(httpServletRequest);
			
			m_schema_name = LAKDL_af_co_conn_methods.schema_name.trim();
			//String m_client_name = LAKDL_af_co_conn_methods.client_name.trim(); // commented by udara 19-05-2017
			m_client_name = LAKDL_af_co_conn_methods.client_name.trim(); // added by udara 19-05-2017
			m_servlet_client_url = LAKDL_af_co_conn_methods.servlet_client_url.trim() + ":" + LAKDL_af_co_conn_methods.client_t3_port.trim();
			String s3 = LAKDL_af_co_conn_methods.html_client_url.trim();
			m_username = LAKDL_af_co_conn_methods.username;
			m_html_client_url = LAKDL_af_co_conn_methods.html_client_url.trim();
			String s6 = "";
			if(httpServletRequest.getParameter("url") != null)
				s6 = httpServletRequest.getParameter("url");
			if(s6.equals("http://www.lakdac.lk")) {
				s3 = "http://www.lakdac.lk";
				m_servlet_client_url = "http://www.lakdac.lk:/myserver/servlet";
			} else {
				s3 = LAKDL_af_co_conn_methods.html_client_url.trim();
			}
			nf = NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			nf1 = NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			httpServletResponse.setStatus(200);
			httpServletResponse.setContentType("text/html");
			httpServletResponse.setHeader("Cache-Control", "No-Cache");
			httpServletResponse.setDateHeader("Expires", 0L);
			ServletOutputStream servletoutputstream1 = httpServletResponse.getOutputStream();
			m_chksql = httpServletRequest.getParameter("chksql");
			stmt1 = connection.createStatement();
			stmt = connection.createStatement();
			stmt_invoice = connection.createStatement();
			stmt3 = connection.createStatement();
			stmt2 = connection.createStatement();
			if(m_chksql.trim().equals("idle"))
				servletoutputstream1.println("idle");
			else if(m_chksql.equals("SHOW_RENT_DETAIL_INVOICED_DRILL")) {
				int i = 0;
				String s13 = "";
				String s48 = httpServletRequest.getParameter("application_no");
				double d2 = 0.0D;
				double d7 = 0.0D;
				double d21 = 0.0D;
				double d32 = 0.0D;
				double d41 = 0.0D;
				double d48 = 0.0D;
				boolean flag45 = false;
				String s142 = " SELECT   A.INVOICE_NO,   A.NET_AMOUNT,   A.VAT_AMOUNT,   A.TOTAL_AMOUNT,   A.SETTELE_AMOUNT,   A.BALANCE_TO_BE_RECEIVED,   TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') ,   NVL(A.ADJUSTED_AMOUNT,0),   B.INVOICE_DESC  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A ," + m_schema_name + ".AF_CO_MAS_INVOICE_RECEIPT_ORD B" + " WHERE  A.INVOICE_TYPE = B.INVOICE_TYPE_CODE " + " AND A.ACTIVE_STATUS='Y' AND " + " FINANCE_NO IN   " + " (SELECT " + "  FINANCE_NO " + "  FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + "  WHERE  APPLICATION_NO=UPPER('" + s48 + "') AND " + "         APPLICATION_STATUS <>'CANCEL') " + "  ORDER BY A.VALUE_DATE ";
				rs = stmt.executeQuery(s142);
				boolean flag48 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE>Rent Details - Application No: " + s48 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Rent Details - Application No: " + s48 + "</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag48) {
					flag45 = true;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Application No: " + s48 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag48) {
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoiced Date</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Invoiced Net</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Adjusted Amount</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<br>");
				}
				for(; flag48; flag48 = rs.next()) {
					i++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(5)) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(8)) + "</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'>" + nf.format(rs.getDouble(6)) + "</td>");
					servletoutputstream1.println("</tr>");
					d2 += rs.getDouble(2);
					d7 += rs.getDouble(3);
					d21 += rs.getDouble(4);
					d32 += rs.getDouble(5);
					d41 += rs.getDouble(6);
					d48 += rs.getDouble(8);
				}
				
				if(!flag45) {
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Total</b> </td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d2) + "</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d7) + "</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d21) + "</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d32) + "</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d48) + "</b></td>");
					servletoutputstream1.println("<td width='11%' class=div_input align='right'><b>" + nf.format(d41) + "</b></td>");
					servletoutputstream1.println("</tr>");
				}
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_RENT_DETAIL_SETTLED_DRILL")) {
				int j = 0;
				String s14 = "";
				String s49 = httpServletRequest.getParameter("application_no");
				double d3 = 0.0D;
				double d8 = 0.0D;
				double d22 = 0.0D;
				double d33 = 0.0D;
				double d42 = 0.0D;
				boolean flag43 = false;
				String s140 = " SELECT   A.INVOICE_NO,   A.NET_AMOUNT,   A.VAT_AMOUNT,   A.TOTAL_AMOUNT,   A.SETTELE_AMOUNT,   A.BALANCE_TO_BE_RECEIVED ,  TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),   B.INVOICE_DESC  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_MAS_INVOICE_RECEIPT_ORD B " + " WHERE  A.INVOICE_TYPE = B.INVOICE_TYPE_CODE " + " AND A.ACTIVE_STATUS='Y' AND " + " FINANCE_NO IN   " + " (SELECT " + "  FINANCE_NO " + "  FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + "  WHERE  APPLICATION_NO=UPPER('" + s49 + "') AND " + "         APPLICATION_STATUS<>'CANCEL' AND SETTELE_AMOUNT >0 ) " + "  ORDER BY A.VALUE_DATE ";
				rs = stmt.executeQuery(s140);
				boolean flag46 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE>Rent Details - Application No: " + s49 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Rent Details - Application No: " + s49 + "</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag46) {
					flag43 = true;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Application No: " + s49 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag46) {
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoiced Date</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Net</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<br>");
				}
				for(; flag46; flag46 = rs.next()) {
					j++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(5)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(6)) + "</td>");
					servletoutputstream1.println("</tr>");
					d3 += rs.getDouble(2);
					d8 += rs.getDouble(3);
					d22 += rs.getDouble(4);
					d33 += rs.getDouble(5);
					d42 += rs.getDouble(6);
				}
				
				if(!flag43) {
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input><b>Total</b> </td>");
					servletoutputstream1.println("<td width='15%' class=div_input><b>&nbsp;</b> </td>");
					servletoutputstream1.println("<td width='15%' class=div_input><b>&nbsp;</b> </td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d3) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d8) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d22) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d33) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d42) + "</b></td>");
					servletoutputstream1.println("</tr>");
				}
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_RENT_DETAIL_BAL_TO_BE_RECEIVED_DRILL")) {
				int k = 0;
				String s15 = "";
				String s50 = httpServletRequest.getParameter("application_no");
				double d4 = 0.0D;
				double d9 = 0.0D;
				double d23 = 0.0D;
				double d34 = 0.0D;
				double d43 = 0.0D;
				boolean flag44 = false;
				String s141 = " SELECT   INVOICE_NO,   NET_AMOUNT,   VAT_AMOUNT,   TOTAL_AMOUNT,   SETTELE_AMOUNT,   BALANCE_TO_BE_RECEIVED,  TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),   B.INVOICE_DESC  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_MAS_INVOICE_RECEIPT_ORD B " + " WHERE  A.INVOICE_TYPE = B.INVOICE_TYPE_CODE " + " AND ACTIVE_STATUS='Y' AND " + " FINANCE_NO IN   " + " (SELECT " + "  FINANCE_NO " + "  FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + "  WHERE  APPLICATION_NO=UPPER('" + s50 + "') AND " + "         APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED >0 )  ORDER BY VALUE_DATE";
				rs = stmt.executeQuery(s141);
				boolean flag47 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE>Rent Details - Application No: " + s50 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Rent Details - Application No: " + s50 + "</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag47) {
					flag44 = true;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Application No: " + s50 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag47) {
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'><b>Invoiced Date</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Net</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<br>");
				}
				for(; flag47; flag47 = rs.next()) {
					k++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(5)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(6)) + "</td>");
					servletoutputstream1.println("</tr>");
					d4 += rs.getDouble(2);
					d9 += rs.getDouble(3);
					d23 += rs.getDouble(4);
					d34 += rs.getDouble(5);
					d43 += rs.getDouble(6);
				}
				
				if(!flag44) {
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input><b>Total</b> </td>");
					servletoutputstream1.println("<td width='15%' class=div_input>&nbsp;</td>");
					servletoutputstream1.println("<td width='15%' class=div_input>&nbsp;</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d4) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d9) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d23) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d34) + "</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d43) + "</b></td>");
					servletoutputstream1.println("</tr>");
				}
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			}
			
			else if(m_chksql.equals("SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL")) {
				
				application_no = httpServletRequest.getParameter("application_no");
				
				int l = 0;
				String s16 = "";
				String s121 = "";
				String s129 = "";
				String s133 = "";
				String s135 = "";
				String s138 = "";
				double d46 = 0.0D;
				double d50 = 0.0D;
				double d53 = 0.0D;
				double d56 = 0.0D;
				double d59 = 0.0D;
				double d61 = 0.0D;
				double d63 = 0.0D;
				double d65 = 0.0D;
				double d67 = 0.0D;
				double d69 = 0.0D;
				double d71 = 0.0D;
				double d73 = 0.0D;
				boolean flag51 = false;
				double d74 = 0.0D;
				
				sql = " " +
					"   SELECT FINANCE_NO, " +
					"          CLIENT_CODE " +
					"   FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + 
					"   WHERE  APPLICATION_NO = UPPER('" + application_no + "') " +
					" ";
				
				rs1 = stmt1.executeQuery(sql);
				if (rs1.next()) {
					finance_no = rs1.getString("FINANCE_NO");
					client_code = rs1.getString("CLIENT_CODE");
				}
				
				
				//sql = " " +
				//	"   SELECT " + m_schema_name + ".AF_CO_TBD_CONT_BAL_ODI_OTHER('" + client_code + "','" + finance_no + "',  TO_CHAR(SYSDATE, 'DD-MM-YYYY')) " +
				//	"   FROM   DUAL " +
				//	" ";
				
				//rs1 = stmt1.executeQuery(sql);
				//if (rs1.next()) {
				//	contract_balance = rs1.getDouble(1);
				//}
				
				/*
				sql = " " +
					"   SELECT TO_CHAR(A.ODI_DATE, 'DD-MM-YYYY'), " +
					"          TO_CHAR(A.ODI_DATE, 'Mon-YYYY'), " +
					// "          SUM(A.ODI_CAL_AMOUNT + " + m_schema_name + ".AF_CO_GET_TERMINATE_ODI('" + finance_no + "')), " +
					"          SUM(A.ODI_CAL_AMOUNT), " +
					"          SUM(A.ODI_BAL_AMOUNT), " +
					// "          SUM(A.ODI_SETTLED_AMOUNT + " + m_schema_name + ".AF_CO_GET_TERMINATE_ODI('" + finance_no + "')), " +
					"          SUM(A.ODI_SETTLED_AMOUNT), " +
					"          SUM(A.ADJUSTED_AMOUNT), " +
					"          " + m_schema_name + ".AF_CO_TBD_CONT_BAL_ODI_OTHER('" + client_code + "','" + finance_no + "',  TO_CHAR(A.ODI_DATE, 'DD-MM-YYYY')) , " +
					"          ( " +
					"               SELECT SUM(" + m_schema_name + ".AF_CO_GET_LEGAL_ODI('" + finance_no + "')) " +
					"               FROM   DUAL " +
					"          ) " +
					"   FROM   " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY A " +
					"   WHERE  A.INVOICE_NO IN ( " +
					"       SELECT INVOICE_NO " +
					"       FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE " +
					"       WHERE  FINANCE_NO IN ( " +
					"           SELECT FINANCE_NO " +
					"           FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " +
					"           WHERE  UPPER(APPLICATION_NO) = UPPER('" + application_no + "') " +
					"           AND    APPLICATION_STATUS <> 'CANCEL' " +
					"       ) " +
					"   ) " +
					"   AND    A.ODI_DATE >= TO_DATE('31-03-2008', 'DD-MM-YYYY') " +
					"   AND    A.ODI_CAL_AMOUNT <> 0 " +
					"   GROUP BY A.ODI_DATE " +
					"   ORDER BY A.ODI_DATE " +
					" ";
				*/
				sql =   " SELECT   TO_CHAR(A.ODI_DATE, 'DD-MM-YYYY'), "+
					"          TO_CHAR(A.ODI_DATE, 'Mon-YYYY'),                "+
					"          SUM(A.ODI_CAL_AMOUNT),                          "+
					"          SUM(A.ODI_BAL_AMOUNT),                          "+
					"          SUM(A.ODI_SETTLED_AMOUNT),                      "+
					"          SUM(A.ADJUSTED_AMOUNT) ,                        "+
					"          'View', "+
					"		   SUM(" + m_schema_name + ".AF_CO_GET_LEGAL_ODI(B.FINANCE_NO)) "+
					" FROM    " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY A ,       "+
					"         " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B         "+
					" WHERE   A.FIN_NO = B.FINANCE_NO                          "+
					" AND     B.APPLICATION_NO ='" + application_no + "'       "+
					" AND     B.APPLICATION_STATUS IN ('ACTIVATED','LEGAL','TERMI','REPOSSESS','TERMINATED')    "+ // added REPOSSESS by udara on 22-06-2018
					" AND     A.ODI_CAL_AMOUNT <> 0                            "+
					" GROUP BY A.ODI_DATE,B.FINANCE_NO ORDER BY A.ODI_DATE     ";
				try{
					rs = stmt.executeQuery(sql);
				}catch(Exception ex){
					servletoutputstream1.println("SQL "+ex.toString()+": "+sql);
				}
				
				servletoutputstream1.println("<html>");
				servletoutputstream1.println("  <head>");
				servletoutputstream1.println("      <title>YYY Rent Details - Application No: " + application_no + " </title>");
				servletoutputstream1.println("      <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
				
				servletoutputstream1.println("      <script type=\"text/javascript\">");
				servletoutputstream1.println("function load_rec_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=REC_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_user_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=USER_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_odi_all_invoice(m_fin_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=ODI_ALL_INVOICE&FINANCE_NO=\"+m_fin_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin3\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_odi_all_invoice1(m_fin_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_seven?chksql=ODI_ALL_INVOICE&FINANCE_NO=\"+m_fin_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin3\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_odi_details_invoice(m_fin_no,m_odi_date){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=ODI_DETAILS_INVOICE&ODI_DATE=\"+m_odi_date+\"&FINANCE_NO=\"+m_fin_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin2\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				
				//added by kanishka dilshan on 30-06-2017
				servletoutputstream1.println("function load_odi_month_end_balance(m_odi_date,m_application_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_MONTH_END_BALANCE&odi_date=\"+m_odi_date+\"&application_no=\"+m_application_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin2\",\"status=0,menubar=0,scrollbars=0,height=100,width=400,resizable=0\");");
				servletoutputstream1.println(" } ");
				// end by kanishka dilshan on 30-06-2017
				
				// added by udara on 22-10-2013
				servletoutputstream1.println("function load_odi_info_deial(m_fin_no,m_odi_date){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=ODI_INFO_DETAIL&ODI_DATE=\"+m_odi_date+\"&FINANCE_NO=\"+m_fin_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin2\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				// end by udara on 22-10-2013
				
				
				servletoutputstream1.println("      </script>");
				servletoutputstream1.println("  </head>");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Rent ODI Details - Application No: " + application_no + "</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				boolean flag52 = rs.next();
				if(!flag52) {
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag52) {
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					//servletoutputstream1.println("<tr>");
					//servletoutputstream1.println("<td width='100%' style='text-align:left'><b>Outstanding Contract Balance: &nbsp;</b>" + nf.format(contract_balance) + "</td>");
					//servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='12%' class=div_input ><b>Month</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>Month End Balance</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>ODI Calculate Amount</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>ODI Settled Amount</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>ODI Adjusted Amount</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>Legal ODI</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>ODI Balance Amount</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input align='right'><b>Cumulative Balance</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				//for(; flag52; flag52 = rs.next()) {
				while(flag52){
					l++;
					d74 += rs.getDouble(4);
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='12%' class=div_input >" + rs.getString(2) + "</td>");
					//servletoutputstream1.println("<td width='12%' class=div_input align='right'>" + nf.format(rs.getDouble(7)) + "</td>");
					servletoutputstream1.println("<td width='12%' class='div_input' align='center' style='cursor:hand' onclick=\"load_odi_month_end_balance('"+rs.getString(1)+"','"+application_no+"');\"><u>" + rs.getString(7) + "</u></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right'>" + nf.format(rs.getDouble(5)) + "</td>");
					//servletoutputstream1.println("<td width='12%' class=div_input align='right'>" + nf.format(rs.getDouble(6)) + "</td>"); // commented by udara on 22-10-2013
					servletoutputstream1.println("<td width='12%' class=div_input align='right' style='cursor:hand' onclick=\"load_odi_info_deial('" + finance_no + "','" + rs.getString(1) + "')\"><u>" + nf.format(rs.getDouble(6)) + "</u></td>"); // added by udara on 22-10-2013
					servletoutputstream1.println("<td width='12%' class=div_input align='right'>" + nf.format(rs.getDouble(8)) + "</td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='right' style='cursor:hand' onclick=\"load_odi_details_invoice('" + finance_no + "','" + rs.getString(1) + "')\"><u>" + nf.format(rs.getDouble(4)) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input align='right'>" + nf.format(d74) + "</td>");
					servletoutputstream1.println("</tr>");
					//d46 += rs.getDouble(7);
					d50 += rs.getDouble(3);
					d56 += rs.getDouble(5);
					d59 += rs.getDouble(6);
					d53 += rs.getDouble(4);
					d63 += rs.getDouble(8);
					
					flag52 = rs.next();
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='12%' class=div_input >&nbsp;</td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>" + nf.format(d50) + "</b></td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>" + nf.format(d56) + "</b></td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>" + nf.format(d59) + "</b></td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>" + nf.format(d63) + "</b></td>");
				servletoutputstream1.println("<td width='12%' class=div_input align='right'><b>" + nf.format(d53) + "</b></td>");
				servletoutputstream1.println("<td width='20%' class=div_input align='right'  style='cursor:hand' onclick=\"load_odi_all_invoice1('" + finance_no + "')\"><b><u>" + nf.format(d74) + "</u></b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			}
			
			else if(m_chksql.equals("ODI_DETAILS_INVOICE")) {
				String s7 = httpServletRequest.getParameter("FINANCE_NO");
				String s17 = httpServletRequest.getParameter("ODI_DATE");
				String s52 = "";
				String s84 = "";
				String s103 = "";
				String s122 = "";
				String s130 = "";
				String s134 = "";
				boolean flag41 = false;
				callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_CO_SAVE_ODI_DETAILS(:1,:2,:3);END;");
				callableStatement.setString(1, s7);
				callableStatement.setString(2, s17);
				callableStatement.setString(3, m_username);
				callableStatement.execute();
				rs = stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('" + s17 + "','DD-MM-YYYY'),'Month YYYY'),'01-'||TO_CHAR(TO_DATE('" + s17 + "','DD-MM-YYYY'),'MM-YYYY') FROM DUAL");
				if(rs.next())
				{
					s52 = rs.getString(1);
					String s85 = rs.getString(2);
				}
				rs = stmt.executeQuery(" SELECT DISTINCT INVOICE_NO,INVOICE_TYPE_DESC,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),NVL(INVOICE_AMT,0), NVL(INV_SETTLED_AMT,0),NVL(TO_CHAR(INV_SETTLED_DATE,'DD-MM-YYYY'),'-'),NVL(INVOICE_BALANCE,0),NVL(NO_OF_DAYS,0),NVL(ODI_CAL_AMT,0),NVL(ODI_SETTLED_AMT,0),  NVL(ODI_WAVED_OFF,0),NVL(ODI_BALANCE_AMT,0),ENT_USER,ENT_DATE,INV_SETTLED_DATE,VALUE_DATE,OPENING_BALANCE,LEGAL_ODI  FROM " + m_schema_name + ".AF_CO_TBD_ODI_DETAILS " + " WHERE ENT_USER='" + m_username + "' ORDER BY VALUE_DATE,INV_SETTLED_DATE ASC ");
				servletoutputstream1.println("<HTML>");
				servletoutputstream1.println("<HEAD>");
				servletoutputstream1.println("<TITLE>Od Interest Details(Monthly)</TITLE>");
				servletoutputstream1.println("</HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + s3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<SCRIPT language=\"JavaScript\">");
				servletoutputstream1.println("function load_rec_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=REC_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_user_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=USER_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("</script>");
				servletoutputstream1.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
				servletoutputstream1.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Invoice Base for the " + s52 + "</td></tr>");
				servletoutputstream1.println("</table>");
				double d35 = 0.0D;
				double d44 = 0.0D;
				double d49 = 0.0D;
				double d52 = 0.0D;
				double d55 = 0.0D;
				double d58 = 0.0D;
				boolean flag49 = rs.next();
				if(!flag49)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag49)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%' ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Value Date</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Settled Date</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Opening Balance</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Settled Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Closing Balance</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>No Of Days</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Calculate Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Settled Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Adjusted Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Legal ODI</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					for(; flag49; flag49 = rs.next())
					{
						String s123 = rs.getString(1);
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='10%' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('" + rs.getString(1) + "');\"><u>" + rs.getString(1) + "</u></td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(2) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(3) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(6) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(4)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(17)) + "</td>");
						if(rs.getDouble(5) < 0.0D)
							servletoutputstream1.println("<td width='10%' style='text-align:right'>0.00</td>");
						else
							servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(5)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(7)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(8) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right;cursor:hand;' >" + nf.format(rs.getDouble(9)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right;cursor:hand;' >" + nf.format(rs.getDouble(10)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(11)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(18)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(12)) + "</td>");
						servletoutputstream1.println("</tr>");
						d35 += rs.getDouble(9);
						d44 += rs.getDouble(12);
						d49 += rs.getDouble(10);
						d52 += rs.getDouble(11);
						d58 += rs.getDouble(7);
						d55 += rs.getDouble(18);
					}
					
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d58) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d35) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d49) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d52) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d55) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d44) + "</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/float_1.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/validate.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</body>");
				servletoutputstream1.println("</html>");
			} 
			else if(m_chksql.equals("SHOW_RENT_DETAIL_ODI_MONTH_END_BALANCE")) {//Added By Kanishka Dilshan on 30-Jun-2017
				
				try{
					
					application_no = httpServletRequest.getParameter("application_no");
					String odi_date       = httpServletRequest.getParameter("odi_date");
					
					sql = 	" " +
						"   SELECT FINANCE_NO, " +
						"          CLIENT_CODE " +
						"   FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + 
						"   WHERE  APPLICATION_NO = UPPER('" + application_no + "') " +
						" ";
					
					rs1 = stmt1.executeQuery(sql);
					if (rs1.next()) {
						finance_no = rs1.getString("FINANCE_NO");
						client_code = rs1.getString("CLIENT_CODE");
					}
					
					
					sql = " " +
						"   SELECT " + m_schema_name + ".AF_CO_TBD_CONT_BAL_ODI_OTHER('" + client_code + "','" + finance_no + "',  '" + odi_date + "') " +
						"   FROM   DUAL " +
						" ";
					
					rs1 = stmt1.executeQuery(sql);
					if (rs1.next()) {
						contract_balance = rs1.getDouble(1);
					}
					
					servletoutputstream1.println("<html>");
					servletoutputstream1.println("  <head>");
					servletoutputstream1.println("      <title>YYY Rent Details - Application No: " + application_no + " </title>");
					servletoutputstream1.println("      <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
					servletoutputstream1.println("  </head>");
					servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
					servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					servletoutputstream1.println("<TR><TD><CENTER><B>Rent ODI Details - Application No: " + application_no + "</B></TD></TR>");
					servletoutputstream1.println("</TABLE>");
					servletoutputstream1.println("<BR><BR>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='100%' style='text-align:left'><b>Outstanding Contract Balance as at "+odi_date+": &nbsp;</b>" + nf.format(contract_balance) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("</form>");
					//servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
					servletoutputstream1.println("</BODY></HTML>");
					
				}catch(Exception ex){
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					ex.printStackTrace(new PrintStream(ostr));
					servletoutputstream1.println(ostr.toString());
				}
				
			}
			
			// added by udara on 22-10-2013
			
			else if(m_chksql.equals("ODI_INFO_DETAIL")) {
				
				String m_finance_no = httpServletRequest.getParameter("FINANCE_NO");
				
				
				rs = stmt.executeQuery(" SELECT DISTINCT INVOICE_NO,INVOICE_TYPE_DESC,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),NVL(INVOICE_AMT,0), NVL(INV_SETTLED_AMT,0),NVL(TO_CHAR(INV_SETTLED_DATE,'DD-MM-YYYY'),'-'),NVL(INVOICE_BALANCE,0),NVL(NO_OF_DAYS,0),NVL(ODI_CAL_AMT,0),NVL(ODI_SETTLED_AMT,0),  NVL(ODI_WAVED_OFF,0),NVL(ODI_BALANCE_AMT,0),ENT_USER,ENT_DATE,INV_SETTLED_DATE,VALUE_DATE,OPENING_BALANCE,LEGAL_ODI  FROM " + m_schema_name + ".AF_CO_TBD_ODI_DETAILS " + " WHERE ENT_USER='" + m_username + "' ORDER BY VALUE_DATE,INV_SETTLED_DATE ASC ");
				servletoutputstream1.println("<HTML>");
				servletoutputstream1.println("<HEAD>");
				servletoutputstream1.println("<TITLE>Od Details(Monthly)</TITLE>");
				servletoutputstream1.println("</HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + s3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<SCRIPT language=\"JavaScript\">");
				
				servletoutputstream1.println("</script>");
				servletoutputstream1.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				
				
				servletoutputstream1.println("<br>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Adjusted Amount</b></td>");
				servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Status</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Adjusted Date</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Adjusted User</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Adjusted Remark</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Approved User 1</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Approved Remark1</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Approved User 2</b></td>");
				servletoutputstream1.println("<td width='10%' style='text-align:left'><b>Approved Remark2</b></td>");
				servletoutputstream1.println("</tr>");
				
				double total_adj = 0;
				
				/*
				rs = stmt.executeQuery("  "+
						" SELECT ADJUSTED_AMOUNT, "+
						" (CASE WHEN ADJUSTED_AMOUNT < 0 THEN 'DECREASED' "+
						" ELSE  "+
						" 'INCREASED' "+
						" END) INC_DEC_STAT, "+
						" ADJUSTED_DATE,ADJUSTED_USER,ENT_USER,MOD_USER "+
											" FROM   " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY A  "+
											" WHERE  A.INVOICE_NO IN (  "+
											" SELECT INVOICE_NO  "+
												" FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE  "+
												" WHERE  FINANCE_NO IN ( "+
												" SELECT FINANCE_NO  "+
														" FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS  "+
														" WHERE    APPLICATION_STATUS <> 'CANCEL'  "+
										                " AND      UPPER(FINANCE_NO) = UPPER('"+m_finance_no+"')  "+
														" )  "+
													" )  "+
											" AND    A.ODI_DATE >= TO_DATE('31-03-2008', 'DD-MM-YYYY')  "+
											" AND    A.ADJUSTED_AMOUNT <> 0 "+
											"  ");
				
				
				*/
				
				/*
				rs = stmt.executeQuery("  "+
							" SELECT A.ADJUSTED_AMOUNT, "+ // 1
							" (CASE WHEN A.ADJUSTED_AMOUNT < 0 THEN 'DECREASED' "+
							" ELSE  "+
							" 'INCREASED' "+
							" END) INC_DEC_STAT, "+	// 2		
							" B.ENT_DATE, "+ // 3
							" NVL(A.ADJUSTED_USER,'-'), "+ // 4
							" NVL(B.APPROVED1_BY,'-'), "+ // 5
							" NVL(B.APPROVED2_BY,'-') "+ // 6
								" FROM   " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY A , " + m_schema_name + ".AF_CO_PRO_ODI_AJUSTMENT_DET B "+
				             	" WHERE A.INVOICE_NO = B.INVOICE_NO "+
								" AND  A.INVOICE_NO IN (  "+
									       " SELECT A.INVOICE_NO  "+
									       " FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE  "+
									       " WHERE  FINANCE_NO IN (  "+
									           " SELECT FINANCE_NO  "+
									           " FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS  "+
									           " WHERE  APPLICATION_STATUS <> 'CANCEL'  "+
				               				   " AND    UPPER(FINANCE_NO) = UPPER('"+m_finance_no+"') "+
									       " )  "+
								" )  "+
								" AND    A.ODI_DATE >= TO_DATE('31-03-2008', 'DD-MM-YYYY')  "+
								" AND    A.ADJUSTED_AMOUNT <> 0  "+
								" ");
				*/
				
				rs = stmt.executeQuery("  "+
					" SELECT  "+
					" B.AJUSTED_AMOUNT, "+
					
					// commented by udara 23-12-2013
					/*
					" (CASE WHEN B.AJUSTED_AMOUNT < 0 THEN 'DECREASED' "+
					" ELSE  "+
					" 'DECREASED' "+
					" END) INC_DEC_STAT, "+
					*/
					
					//" DECODE(INC_DEC_STAT,'INC','Increase','DEC','Decrease'), "+ // added by udara 23-12-2013
					
					// added by udara 23-12-2013
					" NVL(DECODE(INC_DEC_STAT,'INC','Increase','DEC','Decrease'), "+
					" (CASE WHEN B.AJUSTED_AMOUNT < 0 THEN 'Decrease' "+
					" ELSE  "+
					" 'Increase' "+
					" END) "+
					") INC_DEC_STAT, "+
					// end by udara 23-12-2013
					
					
					//" DECODE(ODI_TYPE,'I','Increase','D','Decrease'), "+ // added by udara 05-11-2013
					
					" TO_CHAR(B.ENT_DATE,'DD-MM-YYYY HH:MI:SS'), "+
					" NVL(B.ENT_USER,'-'), "+
					" NVL(B.APPROVED1_BY,'-'), "+
					" NVL(B.APPROVED2_BY,'-'), "+
					
					" NVL(B.ENT_REMARKS,'-'), "+
					" NVL(B.APPROVED1_REMARKS,'-'), "+
					" NVL(B.APPROVED2_REMARKS,'-') "+
					
					" FROM   " + m_schema_name + ".AF_CO_PRO_ODI_AJUSTMENT_DET B, " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY A "+
					" WHERE  B.INVOICE_NO IN (  "+
					" SELECT INVOICE_NO  "+
					" FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE  "+
					" WHERE  FINANCE_NO IN (  "+
					" SELECT FINANCE_NO  "+
					" FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE    APPLICATION_STATUS <> 'CANCEL'  "+
					" AND     FINANCE_NO = UPPER('"+m_finance_no+"')  "+
					" )  "+
					" )  "+
					" AND A.INVOICE_NO = B.INVOICE_NO "+
					
					" AND    B.AJUSTED_AMOUNT <> 0  "+
					" AND    A.ODI_DATE >= TO_DATE('31-03-2008', 'DD-MM-YYYY')  "+
					" AND    A.ADJUSTED_AMOUNT <> 0  "+
					
					" ");
				
				
				while(rs.next())
				{
					total_adj = total_adj + rs.getDouble(1);
					
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(1)) + "</td>");
					servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(6) + "</td>");					
					servletoutputstream1.println("<td width='10%' style='text-align:left'>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("</tr>");
					
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='10%' style='text-align:right'><b> Total :  "+nf.format(total_adj)+" </b></td>");
				servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
				servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
				servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
				servletoutputstream1.println("<td width='1%' style='text-align:left'> &nbsp; </td>");
				servletoutputstream1.println("</tr>");
				
				servletoutputstream1.println("</table>");
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/float_1.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/validate.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</body>");
				servletoutputstream1.println("</html>");
				
			} 
			
			// end by udara on 22-10-2013
			
			else if(m_chksql.equals("ODI_ALL_INVOICE"))
			{
				String s8 = httpServletRequest.getParameter("FINANCE_NO");
				String s18 = "";
				String s53 = "";
				String s86 = "";
				String s104 = "";
				String s124 = "";
				String s131 = "";
				boolean flag37 = false;
				String s136 = "";
				callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_CO_SAVE_ODI_DETAILS(:1,:2,:3);END;");
				callableStatement.setString(1, s8);
				callableStatement.setString(2, "");
				callableStatement.setString(3, m_username);
				callableStatement.execute();
				rs = stmt.executeQuery(" SELECT DISTINCT INVOICE_NO,INVOICE_TYPE_DESC,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),NVL(INVOICE_AMT,0), NVL(INV_SETTLED_AMT,0),NVL(TO_CHAR(INV_SETTLED_DATE,'DD-MM-YYYY'),'-'),NVL(INVOICE_BALANCE,0),NVL(NO_OF_DAYS,0),NVL(ODI_CAL_AMT,0),NVL(ODI_SETTLED_AMT,0),  NVL(ODI_WAVED_OFF,0),NVL(ODI_BALANCE_AMT,0),ENT_USER,ENT_DATE,INV_SETTLED_DATE,VALUE_DATE,LEGAL_ODI  FROM " + m_schema_name + ".AF_CO_TBD_ODI_DETAILS " + " WHERE ENT_USER='" + m_username + "' ORDER BY INVOICE_NO,INV_SETTLED_DATE ASC ");
				servletoutputstream1.println("<HTML>");
				servletoutputstream1.println("<HEAD>");
				servletoutputstream1.println("<TITLE>Od Interest Details(Monthly)</TITLE>");
				servletoutputstream1.println("</HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + s3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<SCRIPT language=\"JavaScript\">");
				servletoutputstream1.println("function load_rec_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=REC_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("function load_user_details(m_inv_no){ ");
				servletoutputstream1.println("m_url=\"" + m_servlet_client_url + "/" + m_client_name + "AF_RE_PRO_drill_downs_five?chksql=USER_DETAILS&INVOICE_NO=\"+m_inv_no;");
				servletoutputstream1.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
				servletoutputstream1.println(" } ");
				servletoutputstream1.println("</script>");
				servletoutputstream1.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
				servletoutputstream1.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Invoice Base</td></tr>");
				servletoutputstream1.println("</table>");
				int i8 = 0;
				double d40 = 0.0D;
				double d47 = 0.0D;
				double d51 = 0.0D;
				double d54 = 0.0D;
				double d57 = 0.0D;
				double d60 = 0.0D;
				double d62 = 0.0D;
				double d64 = 0.0D;
				double d66 = 0.0D;
				double d68 = 0.0D;
				double d70 = 0.0D;
				double d72 = 0.0D;
				boolean flag50 = rs.next();
				if(!flag50)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag50)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%' ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Value Date</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Settled Date</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Settled Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Invoice Balance</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>No Of Days</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Calculate Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Settled Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Adjusted Amount</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>Legal ODI</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>ODI Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					while(flag50) 
					{
						String s105 = rs.getString(1);
						if(i8 > 0 && !s136.equals(rs.getString(1)))
						{
							servletoutputstream1.println("<tr>");
							servletoutputstream1.println("<td colspan=6 style='text-align:left'><b>" + s136 + " Sub Total</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d70) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d62) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d66) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d68) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d72) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d64) + "</b></td>");
							servletoutputstream1.println("</tr>");
							d62 = 0.0D;
							d64 = 0.0D;
							d66 = 0.0D;
							d70 = 0.0D;
							d68 = 0.0D;
							d72 = 0.0D;
							i8 = 0;
						}
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='10%' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('" + rs.getString(1) + "');\"><u>" + rs.getString(1) + "</u></td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(2) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(3) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(6) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(4)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(5)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(7)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + rs.getString(8) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right;cursor:hand;' >" + nf.format(rs.getDouble(9)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right;cursor:hand;' >" + nf.format(rs.getDouble(10)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(11)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(17)) + "</td>");
						servletoutputstream1.println("<td width='10%' style='text-align:right'>" + nf.format(rs.getDouble(12)) + "</td>");
						servletoutputstream1.println("</tr>");
						s136 = rs.getString(1);
						if(i8 == 0)
						{
							d62 = rs.getDouble(9);
							d64 = rs.getDouble(12);
							d66 = rs.getDouble(10);
							d68 = rs.getDouble(11);
							d70 = rs.getDouble(7);
							d72 = rs.getDouble(17);
						} else
						{
							d62 += rs.getDouble(9);
							d64 += rs.getDouble(12);
							d66 += rs.getDouble(10);
							d68 += rs.getDouble(11);
							d70 += rs.getDouble(7);
							d72 += rs.getDouble(17);
						}
						d40 += rs.getDouble(9);
						d47 += rs.getDouble(12);
						d51 += rs.getDouble(10);
						d54 += rs.getDouble(11);
						d57 += rs.getDouble(7);
						d60 += rs.getDouble(17);
						i8++;
						flag50 = rs.next();
						if(!flag50)
						{
							servletoutputstream1.println("<tr>");
							servletoutputstream1.println("<td colspan=6 style='text-align:left'><b>" + s136 + " Sub Total</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d70) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d62) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d66) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d68) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d72) + "</b></td>");
							servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d64) + "</b></td>");
							servletoutputstream1.println("</tr>");
						}
					}
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d40) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d51) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d54) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d60) + "</b></td>");
					servletoutputstream1.println("<td width='10%' style='text-align:right'><b>" + nf.format(d47) + "</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/float_1.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/validate.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</body>");
				servletoutputstream1.println("</html>");
			} else if(m_chksql.equals("REC_DETAILS"))
			{
				String s9 = httpServletRequest.getParameter("INVOICE_NO");
				servletoutputstream1.println("<HTML>");
				servletoutputstream1.println("<HEAD>");
				servletoutputstream1.println("<TITLE>Od Interest Details(Monthly)</TITLE>");
				servletoutputstream1.println("</HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + s3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<SCRIPT language=\"JavaScript\">");
				servletoutputstream1.println("</script>");
				servletoutputstream1.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
				servletoutputstream1.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Receipt Details</td></tr>");
				servletoutputstream1.println("</table>");
				rs = stmt.executeQuery(" SELECT A.RECEIPT_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),A.SETTELED_AMOUNT  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A " + " WHERE A.INVOICE_NO='" + s9 + "'");
				boolean flag1 = rs.next();
				if(!flag1)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag1)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='30%' ><b>Receipt No</b></td>");
					servletoutputstream1.println("<td width='30%' ><b>Value Date</b></td>");
					servletoutputstream1.println("<td width='30%' style='text-align:right'><b>Allocated Amount</b></td>");
					servletoutputstream1.println("</tr>");
					for(; flag1; flag1 = rs.next())
					{
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
						servletoutputstream1.println("<td width='30%' STYLE='{cursor:hand;}'>" + rs.getString(1) + "</td>");
						servletoutputstream1.println("<td width='30%' STYLE='{cursor:hand;}'>" + rs.getString(2) + "</td>");
						servletoutputstream1.println("<td width='30%' style='text-align:right' >" + nf.format(rs.getDouble(3)) + "</td>");
						servletoutputstream1.println("</tr>");
					}
					
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/float_1.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/validate.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</body>");
				servletoutputstream1.println("</html>");
			} else if(m_chksql.equals("USER_DETAILS"))
			{
				String s10 = httpServletRequest.getParameter("INVOICE_NO");
				servletoutputstream1.println("<HTML>");
				servletoutputstream1.println("<HEAD>");
				servletoutputstream1.println("<TITLE>Od Interest Details(Monthly)</TITLE>");
				servletoutputstream1.println("</HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + s3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<SCRIPT language=\"JavaScript\">");
				servletoutputstream1.println("</script>");
				servletoutputstream1.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
				servletoutputstream1.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>User Details</td></tr>");
				servletoutputstream1.println("</table>");
				rs = stmt.executeQuery(" SELECT NVL(" + m_schema_name + ".AF_CO_GET_USER_LOCATION(A.ENT_USER),'-'),NVL(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.ENT_USER,'-'),NVL(A.SETTELED_AMOUNT,0) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A " + " WHERE A.INVOICE_NO='" + s10 + "'");
				boolean flag2 = rs.next();
				if(!flag2)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag2)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' border=0>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='5%'>&nbsp;</td>");
					servletoutputstream1.println("<td width='25%' ><b>Branch</b></td>");
					servletoutputstream1.println("<td width='20%' ><b>Date</b></td>");
					servletoutputstream1.println("<td width='30%' ><b>User</b></td>");
					servletoutputstream1.println("<td width='20%' style='text-align:right'><b>Amount</b></td>");
					servletoutputstream1.println("</tr>");
					for(; flag2; flag2 = rs.next())
					{
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='5%'>&nbsp;</td>");
						servletoutputstream1.println("<td width='25%' >" + rs.getString(1) + "</td>");
						servletoutputstream1.println("<td width='20%' >" + rs.getString(2) + "</td>");
						servletoutputstream1.println("<td width='30%' >" + rs.getString(3) + "</td>");
						servletoutputstream1.println("<td width='20%' style='text-align:right' >" + nf.format(rs.getDouble(4)) + "</td>");
						servletoutputstream1.println("</tr>");
					}
					
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/float_1.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/validate.js'></SCRIPT>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</body>");
				servletoutputstream1.println("</html>");
			} else if(m_chksql.equals("SHOW_RENT_DETAIL_ODI_BREAKUP"))
			{
				String s11 = httpServletRequest.getParameter("inv_no");
				double d = 0.0D;
				double d5 = 0.0D;
				double d10 = 0.0D;
				double d24 = 0.0D;
				servletoutputstream1.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>ODI BreakUp</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				rs = stmt.executeQuery(" SELECT INVOICE_NO,  ODI_CAL_AMOUNT,  ODI_BAL_AMOUNT,  ODI_SETTLED_AMOUNT,  ADJUSTED_AMOUNT  FROM " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY " + " WHERE INVOICE_NO='" + s11 + "' ");
				servletoutputstream1.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
				servletoutputstream1.println("<tr class=pdn_txtpos2>");
				servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>ODI Cal Amount</b></td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>ODI Settled Amount</b></td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>ODI Adjusted Amount</b></td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>ODI Balance Amount</b></td>");
				servletoutputstream1.println("</tr>");
				while(rs.next()) 
				{
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='15%' class=div_input align='left'>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(5)) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("</tr>");
					d += rs.getDouble(2);
					d5 += rs.getDouble(4);
					d10 += rs.getDouble(5);
					d24 += rs.getDouble(3);
				}
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Total</td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d) + "</td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d5) + "</td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d10) + "</td>");
				servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>" + nf.format(d24) + "</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
			} else if(m_chksql.equals("SHOW_SETTLE_RECEIPT_DRILL"))
			{
				int i1 = 0;
				String s19 = "";
				String s54 = httpServletRequest.getParameter("receipt_no");
				String s87 = "-";
				String s106 = "-";
				String s125 = "-";
				String s132 = "-";
				if (s54.substring(0,2).equals("SR") ) {
					//rs = stmt1.executeQuery(" SELECT   A.REC_NO,  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return','CAD','Cancel'),'-'),  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),  NVL(RECON_BY,'-'),  NVL(SUS_REF_NO,'-'),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(REC_AMOUNT,0)," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(BRANCH_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')," + "  NVL(ACC_NO,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(REC_AMOUNT_CURR,0)," + "  NVL(EXCHANGE_RATE_BANK,0)," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(EXCHANGE_GAIN_LOSS,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)," + "  NVL(CHEQUE_NO,'-')," + "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TENDER_AMOUNT,0)," + "  NVL(RETURN_AMOUNT,0)," + "  NVL(RENTAL_OTER_INVOICE,0)," + "  NVL(INSURANCE,0)," + "  NVL(LUXURY_TAX,0)," + "  NVL(REVANUE_LICENCE,0)," + "  NVL(RMV_CHARGES,0)," + "  A.ENT_DATE," + "  A.ENT_USER, " + "  A.MOD_DATE," + "  NVL(A.MOD_USER,'-'), " + "  NVL(B.COMMENTS,'-'), " + "  NVL(B.ENT_USER,'-') ," + "  NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.SUB_REC_NO,'-') " + "  FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A ," + m_schema_name + ".AF_CO_PRO_RECEIPT_CANCEL B " + "  WHERE A.REC_NO = B.REC_NO(+) " + "  AND A.REC_NO='" + s54 + "' "); // commented by udara 12-01-2018
					rs = stmt1.executeQuery(" SELECT   A.REC_NO,  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return','CAD','Cancel'),'-'),  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),  NVL(RECON_BY,'-'),  NVL(SUS_REF_NO,'-'),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(REC_AMOUNT,0)," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(BRANCH_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')," + "  NVL(ACC_NO,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(REC_AMOUNT_CURR,0)," + "  NVL(EXCHANGE_RATE_BANK,0)," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(EXCHANGE_GAIN_LOSS,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)," + "  NVL(CHEQUE_NO,'-')," + "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TENDER_AMOUNT,0)," + "  NVL(RETURN_AMOUNT,0)," + "  NVL(RENTAL_OTER_INVOICE,0)," + "  NVL(INSURANCE,0)," + "  NVL(LUXURY_TAX,0)," + "  NVL(REVANUE_LICENCE,0)," + "  NVL(RMV_CHARGES,0)," + "  A.ENT_DATE," + "  A.ENT_USER, " + "  A.MOD_DATE," + "  NVL(" + m_schema_name + ".AF_RECEIPT_STATUS_USER(A.REC_NO,A.STATUS),'-'), " + "  NVL(B.COMMENTS,'-'), " + "  NVL(B.ENT_USER,'-') ," + "  NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.SUB_REC_NO,'-') " + "  FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A ," + m_schema_name + ".AF_CO_PRO_RECEIPT_CANCEL B " + "  WHERE A.REC_NO = B.REC_NO(+) " + "  AND A.REC_NO='" + s54 + "' "); // added by udara 12-01-2018
					
				} else {
					//rs = stmt1.executeQuery(" SELECT   A.REC_NO,  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return','CAD','Cancel'),'-'),  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),  NVL(RECON_BY,'-'),  NVL(SUS_REF_NO,'-'),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(REC_AMOUNT,0)," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(BRANCH_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')," + "  NVL(ACC_NO,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(REC_AMOUNT_CURR,0)," + "  NVL(EXCHANGE_RATE_BANK,0)," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(EXCHANGE_GAIN_LOSS,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)," + "  NVL(CHEQUE_NO,'-')," + "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TENDER_AMOUNT,0)," + "  NVL(RETURN_AMOUNT,0)," + "  NVL(RENTAL_OTER_INVOICE,0)," + "  NVL(INSURANCE,0)," + "  NVL(LUXURY_TAX,0)," + "  NVL(REVANUE_LICENCE,0)," + "  NVL(RMV_CHARGES,0)," + "  A.ENT_DATE," + "  A.ENT_USER, " + "  A.MOD_DATE," + "  NVL(A.MOD_USER,'-'), " + "  NVL(B.COMMENTS,'-'), " + "  NVL(B.ENT_USER,'-') ," + "  NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.SUB_REC_NO,'-') " + "  FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A ," + m_schema_name + ".AF_CO_PRO_RECEIPT_CANCEL B " + "  WHERE A.REC_NO = B.REC_NO(+) " + "  AND A.SUB_REC_NO='" + s54 + "' "); // commented by udara 12-01-2018
					rs = stmt1.executeQuery(" SELECT   A.REC_NO,  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return','CAD','Cancel'),'-'),  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),  NVL(RECON_BY,'-'),  NVL(SUS_REF_NO,'-'),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(REC_AMOUNT,0)," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(BRANCH_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')," + "  NVL(ACC_NO,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(REC_AMOUNT_CURR,0)," + "  NVL(EXCHANGE_RATE_BANK,0)," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(EXCHANGE_GAIN_LOSS,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)," + "  NVL(CHEQUE_NO,'-')," + "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TENDER_AMOUNT,0)," + "  NVL(RETURN_AMOUNT,0)," + "  NVL(RENTAL_OTER_INVOICE,0)," + "  NVL(INSURANCE,0)," + "  NVL(LUXURY_TAX,0)," + "  NVL(REVANUE_LICENCE,0)," + "  NVL(RMV_CHARGES,0)," + "  A.ENT_DATE," + "  A.ENT_USER, " + "  A.MOD_DATE," + "  NVL(" + m_schema_name + ".AF_RECEIPT_STATUS_USER(A.REC_NO,A.STATUS),'-'), " + "  NVL(B.COMMENTS,'-'), " + "  NVL(B.ENT_USER,'-') ," + "  NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.SUB_REC_NO,'-') " + "  FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A ," + m_schema_name + ".AF_CO_PRO_RECEIPT_CANCEL B " + "  WHERE A.REC_NO = B.REC_NO(+) " + "  AND A.SUB_REC_NO='" + s54 + "' "); // added by udara 12-01-2018
					
					
				}
				//rs = stmt1.executeQuery(" SELECT   A.REC_NO,  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return','CAD','Cancel'),'-'),  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),  NVL(RECON_BY,'-'),  NVL(SUS_REF_NO,'-'),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(REC_AMOUNT,0)," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(BRANCH_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')," + "  NVL(ACC_NO,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(REC_AMOUNT_CURR,0)," + "  NVL(EXCHANGE_RATE_BANK,0)," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(EXCHANGE_GAIN_LOSS,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)," + "  NVL(CHEQUE_NO,'-')," + "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-')," + "  NVL(TENDER_AMOUNT,0)," + "  NVL(RETURN_AMOUNT,0)," + "  NVL(RENTAL_OTER_INVOICE,0)," + "  NVL(INSURANCE,0)," + "  NVL(LUXURY_TAX,0)," + "  NVL(REVANUE_LICENCE,0)," + "  NVL(RMV_CHARGES,0)," + "  A.ENT_DATE," + "  A.ENT_USER, " + "  A.MOD_DATE," + "  NVL(A.MOD_USER,'-'), " + "  NVL(B.COMMENTS,'-'), " + "  NVL(B.ENT_USER,'-') ," + "  NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-'),NVL(A.SUB_REC_NO,'-') " + "  FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A ," + m_schema_name + ".AF_CO_PRO_RECEIPT_CANCEL B " + "  WHERE A.REC_NO = B.REC_NO(+) " + "  AND A.SUB_REC_NO='" + s54 + "' ");
				boolean flag38 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Settlement Receipt Details - Receipt No : " + s54 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Settlement Receipt Details - Receipt No : " + s54 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag38)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Receipt No  " + s54 + "  </b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag38)
				{
					i1++;
					s54 = rs.getString(1);
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Receipt No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Sub Receipt No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(44) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					
					rs1 = stmt2.executeQuery("SELECT TEMP_REC_NO  FROM " + m_schema_name + ".AF_CO_PRO_SET_TMRECEIPT " + " WHERE RECEIPT_NO='" + s54 + "' ");
					if(rs1.next())
					{
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='1%'></td>");
						servletoutputstream1.println("<td width='30%' class=div_input><b>Temp Receipt No</b></td>");
						servletoutputstream1.println("<td width='50%' class=div_input>" + rs1.getString(1) + "</td>");
						servletoutputstream1.println("<td width='*%'></td>");
						servletoutputstream1.println("</tr>");
					}
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Receipt Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(2) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Recon Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Recon Date</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Recon By</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Generate Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(37) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Generate User</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(38) + "</td>");
					servletoutputstream1.println("</tr>");
					rs3 = stmt3.executeQuery(" SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY') ,ENT_USER     FROM  " + m_schema_name + ".AF_CO_PRO_RETURN_DETAILS " + "  WHERE  RECEIPT_NO='" + rs.getString(1) + "' ");
					if(rs3.next())
					{
						s132 = rs3.getString(2);
						s125 = rs3.getString(1);
					}
					String s107 = rs.getString(40);
					String s88 = rs.getString(39);
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Modify Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + s88 + "</td>");
					//servletoutputstream1.println("<td width='20%' class=div_input><b>Modify User</b></td>"); // commented by udara 31-12-2013
					//servletoutputstream1.println("<td width='*%' class=div_input>" + s107 + "</td>"); // commented by udara 31-12-2013
					
					servletoutputstream1.println("<td width='20%' class=div_input><b> Last Status / User</b></td>"); // added by udara 31-12-2013
					servletoutputstream1.println("<td width='*%' class=div_input> "+rs.getString(2)+"  /  " + s107 + "</td>"); // added by udara 31-12-2013
					
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Return Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + s125 + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Return User</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + s132 + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Cancel Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(43) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Cancel User</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(42) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Cancellation Comment</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(41) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>&nbsp;</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Settle Mode</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Payer Branch Name</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Payer Acc. No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Entry Type</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(11) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Receipt Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(12)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(13) + "')><u>" + rs.getString(14) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(16) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Account No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(17) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Other Comments</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(18) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>EFF Value Date</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(19) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(20) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Currency</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(21) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Receipt Amount Current</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(22)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Ex. Rate Bank</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + nf.format(rs.getDouble(23)) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Ex. Rate Reporting Curr.</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(24)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Ex. Gain Loss</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + nf.format(rs.getDouble(25)) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Receipt Amount Reporting Curr.</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(26)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(27) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Cheque Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(28) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Banked Date</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(29) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Tender Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(30)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Return Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(31)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Rental Invoices</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(32)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Other Invoices</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(33)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					boolean flag39 = rs.next();
				}
				rs = stmt1.executeQuery("SELECT NVL(SUM(SETTELED_AMOUNT),0)  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS " + " WHERE RECEIPT_NO = '" + s54 + "' ");
				if(rs.next())
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Alocated Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(1)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				//String s137 = "SELECT  a.finance_no, a.rec_amount, a.app_rec_amount,  a.bal_tobe_receive, a.allocated_amount  FROM " + m_schema_name + ".af_co_pro_settl_rec_app_bal a " + "\tWHERE UPPER(A.rec_no)=UPPER('" + s54 + "') ";
				String s137 = "SELECT  a.finance_no, a.rec_amount, a.app_rec_amount,  a.bal_tobe_receive, a.allocated_amount  FROM " + m_schema_name + ".af_co_pro_settl_rec_app_bal a " + "\tWHERE A.rec_no=UPPER('" + s54 + "') ";
				rs = stmt1.executeQuery(s137);
				boolean flag42 = rs.next();
				if(flag42)
				{
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<hr color='black'>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>Contract Allocation Receipt No  " + s54 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left' ><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right' ><b>Receipt Amount&nbsp</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input align='right' ><b>Receipt Amount For This Contract&nbsp</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right' ><b>Settled Amount&nbsp</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right' ><b>Balance Amount&nbsp</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					for(; flag42; flag42 = rs.next())
					{
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='1%'></td>");
						servletoutputstream1.println("<td width='12%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
						servletoutputstream1.println("<td width='15%' class=div_input align='right' >" + nf.format(rs.getDouble(2)) + "&nbsp;</td>");
						servletoutputstream1.println("<td width='20%' class=div_input align='right' >" + nf.format(rs.getDouble(3)) + "&nbsp;</td>");
						servletoutputstream1.println("<td width='15%' class=div_input align='right' >" + nf.format(rs.getDouble(5)) + "&nbsp;</td>");
						servletoutputstream1.println("<td width='15%' class=div_input align='right' >" + nf.format(rs.getDouble(4)) + "&nbsp;</td>");
						servletoutputstream1.println("</tr>");
					}
					
					servletoutputstream1.println("</table>");
				}
				//String s139 = "\tSELECT   A.RECEIPT_NO,   A.INVOICE_NO,   A.ALLOCATION_NO,   TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),   A.INVOICED_AMOUNT,   A.SETTELED_AMOUNT,   B.SETTELE_AMOUNT,   B.BALANCE_TO_BE_RECEIVED   ,B.FINANCE_NO\tFROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_INVOICE B " + "\tWHERE UPPER(A.RECEIPT_NO)=UPPER('" + s54 + "') " + "\tAND A.INVOICE_NO=B.INVOICE_NO " + " UNION " + " SELECT " + " A.RECEIPT_NO,  " + " A.INVOICE_NO,  " + " A.ALLOCATION_NO,  " + " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  " + " A.INVOICED_AMOUNT,  " + " A.SETTELED_AMOUNT, " + " C.SETTELE_AMOUNT, " + " C.BALANCE_TO_BE_RECEIVED  " + " ,C.FINANCE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY B ," + m_schema_name + ".AF_CO_PRO_INVOICE C  " + " WHERE UPPER(A.RECEIPT_NO)=UPPER('" + s54 + "')  " + " AND B.INVOICE_NO=C.INVOICE_NO " + " AND A.INVOICE_NO=B.ODI_REF_NO "; // commented by udara on 04-01-2012
				
				//String s139 = "\tSELECT   A.RECEIPT_NO,   A.INVOICE_NO,   A.ALLOCATION_NO,   TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),   A.INVOICED_AMOUNT,   A.SETTELED_AMOUNT,   B.SETTELE_AMOUNT,   B.BALANCE_TO_BE_RECEIVED   ,B.FINANCE_NO, NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_DESC(B.INVOICE_TYPE),B.INVOICE_TYPE)\tFROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_INVOICE B " + "\tWHERE UPPER(A.RECEIPT_NO)=UPPER('" + s54 + "') " + "\tAND A.INVOICE_NO=B.INVOICE_NO " + " UNION " + " SELECT " + " A.RECEIPT_NO,  " + " A.INVOICE_NO,  " + " A.ALLOCATION_NO,  " + " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  " + " A.INVOICED_AMOUNT,  " + " A.SETTELED_AMOUNT, " + " C.SETTELE_AMOUNT, " + " C.BALANCE_TO_BE_RECEIVED  " + " ,C.FINANCE_NO " + ",NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),C.INVOICE_TYPE) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY B ," + m_schema_name + ".AF_CO_PRO_INVOICE C  " + " WHERE UPPER(A.RECEIPT_NO)=UPPER('" + s54 + "')  " + " AND B.INVOICE_NO=C.INVOICE_NO " + " AND A.INVOICE_NO=B.ODI_REF_NO "; // added by udara on 04-01-2012
				// removed the upper section in where clasie by ns on 20-05-2017
				String s139 = "\tSELECT   A.RECEIPT_NO,   A.INVOICE_NO,   A.ALLOCATION_NO,   TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),   A.INVOICED_AMOUNT,   A.SETTELED_AMOUNT,   B.SETTELE_AMOUNT,   B.BALANCE_TO_BE_RECEIVED   ,B.FINANCE_NO, NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_DESC(B.INVOICE_TYPE),B.INVOICE_TYPE)\tFROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_INVOICE B " + "\tWHERE A.RECEIPT_NO=UPPER('" + s54 + "') " + "\tAND A.INVOICE_NO=B.INVOICE_NO " + " UNION " + " SELECT " + " A.RECEIPT_NO,  " + " A.INVOICE_NO,  " + " A.ALLOCATION_NO,  " + " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  " + " A.INVOICED_AMOUNT,  " + " A.SETTELED_AMOUNT, " + " C.SETTELE_AMOUNT, " + " C.BALANCE_TO_BE_RECEIVED  " + " ,C.FINANCE_NO " + ",NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),C.INVOICE_TYPE) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY B ," + m_schema_name + ".AF_CO_PRO_INVOICE C  " + " WHERE A.RECEIPT_NO=UPPER('" + s54 + "')  " + " AND B.INVOICE_NO=C.INVOICE_NO " + " AND A.INVOICE_NO=B.ODI_REF_NO "; // added by udara on 04-01-2012
				
				rs = stmt1.executeQuery(s139);
				flag42 = rs.next();
				servletoutputstream1.println("<br>");
				servletoutputstream1.println("<hr color='black'>");
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='80%' class=div_input><b>Receipt Allocation Details</b></td>");
				servletoutputstream1.println("<td width='*%'></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				if(!flag42)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No allocation data for Receipt No  " + s54 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag42)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left'><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left'><b>Invoice Type</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left' ><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left' ><b>Allocation Date </b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount&nbsp</b></td>");
					servletoutputstream1.println("<td width='10%' class=div_input align='right' ><b>Allo Amount from this Rec&nbsp</b></td>");
					servletoutputstream1.println("<td width='10%' class=div_input align='right' ><b>Total Settled Amount&nbsp</b></td>");
					servletoutputstream1.println("<td width='10%' class=div_input align='right' ><b>Balance Amount&nbsp</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d45 = 0.0D;
				for(; flag42; flag42 = rs.next())
				{
					i1++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(2) + "') ><u>" + rs.getString(2) + "</u></td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left'  >" + rs.getString(10) + "</td>"); // added by udara on 04-01-2012
					servletoutputstream1.println("<td width='12%' class=div_input align='left'  >" + rs.getString(9) + "</td>");
					servletoutputstream1.println("<td width='12%' class=div_input align='left' >" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right' >" + nf.format(rs.getDouble(5)) + "&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' bgcolor='grey' class=div_input align='right' >" + nf.format(rs.getDouble(6)) + "&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' class=div_input align='right' >" + nf.format(rs.getDouble(7)) + "&nbsp;</td>");
					servletoutputstream1.println("<td width='10%' class=div_input align='right' >" + nf.format(rs.getDouble(8)) + "&nbsp;</td>");
					servletoutputstream1.println("</tr>");
					d45 += rs.getDouble(6);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'>&nbsp;</td>");
				servletoutputstream1.println("<td width='12%'>&nbsp;</td>");
				servletoutputstream1.println("<td width='12%'>&nbsp;</td>"); // added by udara on 04-01-2012
				servletoutputstream1.println("<td width='12%'>&nbsp;</td>");
				servletoutputstream1.println("<td width='12%'>&nbsp;</td>");
				servletoutputstream1.println("<td width='15%' align='right'><B>Total&nbsp;</td>");
				servletoutputstream1.println("<td width='10%' bgcolor='grey' align='right'><b>" + nf.format(d45) + "&nbsp;</td>");
				servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
				servletoutputstream1.println("<td width='10%'>&nbsp;</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("show_receipt_remarks"))
			{
				boolean flag = false;
				String s20 = "";
				String s55 = httpServletRequest.getParameter("receipt_no");
				rs = stmt1.executeQuery(" SELECT   REC_NO,  NVL(OTH_COMMENTS,'-')   FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT " + "   WHERE REC_NO='" + s55 + "'");
				boolean flag3 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Settlement Receipt Details - Receipt No : " + s55 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Settlement Receipt Details - Receipt No : " + s55 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag3)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Receipt No  " + s55 + "  </b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='20%' class=div_input><b>Rec No</b></td>");
				servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(1) + "</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='20%' class=div_input><b>Remarks</b></td>");
				servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(2) + "</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_POD_CHEQUE_DRILL"))
			{
				int j1 = 0;
				String s21 = "";
				String s56 = httpServletRequest.getParameter("pod_ref_no");
				rs = stmt1.executeQuery(" SELECT   POD_REF_NO,  NVL(DECODE(STATUS,'INV','Invoice','ENT','Enter','WIT','Withdraw','REC','Receipt'),'-'),  NVL(FINANCE_NO,'-'),  NVL(REC_NO,'-'),  NVL(SUS_REF_NO,'-'),  NVL(CHEQUE_NO,'-'),  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),  NVL(CHEQUE_AMOUNT,0),  NVL(SETTLE_MODE,'-'),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-')," + "  NVL(PAYER_ACC_NO,'-')," + "  NVL(CLIENT_CODE,'-')," + "  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-')," + "  NVL(ENTRY_TYPE,'-')," + "  NVL(OTH_COMMENTS,'-')," + "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-')," + "  NVL(CURR_CODE,'-')," + "  NVL(EXCHANGE_RATE_REP_CURR,0)," + "  NVL(REC_AMOUNT_REP_CURR,0)" + "FROM " + m_schema_name + ".AF_RE_PRO_POD_CHEQUES " + "WHERE POD_REF_NO='" + s56 + "'");
				boolean flag4 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> POD Cheque Details - POD Ref No : " + s56 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> POD Cheque Details - POD Ref No : " + s56 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag4)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for POD Ref No  " + s56 + "  </b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag4)
				{
					j1++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> POD Ref No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>POD Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(2) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(4) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(5) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Cheque No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Cheque Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(8)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Settle Mode</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Payer Branch Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(11) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Payer Account No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(12) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(13) + "')><u>" + rs.getString(13) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(13) + "')><u>" + rs.getString(14) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Entry Type</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(15) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Other Comments</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(16) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(17) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Currency</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(18) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Exchange Rate Reporting Currency</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(19)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Reporting Currency Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(20)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					boolean flag5 = rs.next();
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_SET_DRILL"))
			{
				int k1 = 0;
				String s22 = "";
				String s57 = httpServletRequest.getParameter("user_id");
				String s89 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Collection Details - Collection Officer : " + s57 + " &nbsp;as at :" + s89 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Collection Details - Collection Officer : " + s57 + " &nbsp;as at :" + s89 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s108 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s89 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s57 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s89 + "',FINANCE_NO),0) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s108);
				boolean flag22 = rs.next();
				if(!flag22)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s57 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag22)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Collected Amount </b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d11 = 0.0D;
				for(; flag22; flag22 = rs.next())
				{
					k1++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_inv_collect_drill('" + s57 + "','" + s89 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d11 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d11) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_BAL_DRILL"))
			{
				int l1 = 0;
				String s23 = "";
				String s58 = httpServletRequest.getParameter("user_id");
				String s90 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Balance Details - Collection Officer : " + s58 + " &nbsp;as at :" + s90 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Balance Details - Collection Officer : " + s58 + " &nbsp;as at :" + s90 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s109 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'" + s90 + "',FINANCE_NO),0) - NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s90 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s58 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND (NVL(" + m_schema_name + ".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'" + s90 + "',FINANCE_NO),0) - NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s90 + "',FINANCE_NO),0)) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s109);
				boolean flag23 = rs.next();
				if(!flag23)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s58 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag23)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance Amount </b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d12 = 0.0D;
				for(; flag23; flag23 = rs.next())
				{
					l1++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_inv_bal_drill('" + s58 + "','" + s90 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d12 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d12) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_INVOICED_DRILL"))
			{
				int i2 = 0;
				String s24 = "";
				String s59 = httpServletRequest.getParameter("user_id");
				String s91 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Invoiced Details Current Month - Collection Officer : " + s59 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Invoiced Details Current Month - Collection Officer : " + s59 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s110 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s91 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s59 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s91 + "',FINANCE_NO),0) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s110);
				boolean flag24 = rs.next();
				if(!flag24)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s59 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag24)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Invoiced Amount </b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d13 = 0.0D;
				for(; flag24; flag24 = rs.next())
				{
					i2++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_drill('" + s59 + "','" + s91 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d13 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d13) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_INVOICED_SET_DRILL"))
			{
				int j2 = 0;
				String s25 = "";
				String s60 = httpServletRequest.getParameter("user_id");
				String s92 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Collection Details Current Month - Collection Officer : " + s60 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Collection Details Current Month - Collection Officer : " + s60 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s111 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s92 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s60 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s92 + "',FINANCE_NO),0) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s111);
				boolean flag25 = rs.next();
				if(!flag25)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s60 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag25)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Collected Amount </b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d14 = 0.0D;
				for(; flag25; flag25 = rs.next())
				{
					j2++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_settled_drill('" + s60 + "','" + s92 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d14 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d14) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_INVOICED_SET_BAL_DRILL"))
			{
				int k2 = 0;
				String s26 = "";
				String s61 = httpServletRequest.getParameter("user_id");
				String s93 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Invoiced Balace Details Current Month - Collection Officer : " + s61 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Invoiced Balance Details Current Month - Collection Officer : " + s61 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s112 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s93 + "',FINANCE_NO),0)-NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s93 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s61 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND (NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s93 + "',FINANCE_NO),0)-NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s93 + "',FINANCE_NO),0)) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s112);
				boolean flag26 = rs.next();
				if(!flag26)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s61 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag26)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d15 = 0.0D;
				for(; flag26; flag26 = rs.next())
				{
					k2++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_drill('" + s61 + "','" + s93 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d15 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d15) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_TOT_INV_DRILL"))
			{
				int l2 = 0;
				String s27 = "";
				String s62 = httpServletRequest.getParameter("user_id");
				String s94 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Total Invoiced Details - Collection Officer : " + s62 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Total Invoiced Details - Collection Officer : " + s62 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s113 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'" + s94 + "',FINANCE_NO),0) + NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s94 + "',FINANCE_NO),0)" + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s62 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND (NVL(" + m_schema_name + ".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'" + s94 + "',FINANCE_NO),0) + NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s94 + "',FINANCE_NO),0)) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s113);
				boolean flag27 = rs.next();
				if(!flag27)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s62 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag27)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Invoiced</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d16 = 0.0D;
				for(; flag27; flag27 = rs.next())
				{
					l2++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_invoiced_drill('" + s62 + "','" + s94 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d16 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d16) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_TOT_INV_SET_DRILL"))
			{
				int i3 = 0;
				String s28 = "";
				String s63 = httpServletRequest.getParameter("user_id");
				String s95 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Total Collection Details - Collection Officer : " + s63 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Total Collection Details - Collection Officer : " + s63 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s114 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s95 + "',FINANCE_NO),0) +NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s95 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s63 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " AND (NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s95 + "',FINANCE_NO),0) +NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s95 + "',FINANCE_NO),0)) >0 " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s114);
				boolean flag28 = rs.next();
				if(!flag28)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s63 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag28)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Collection</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d17 = 0.0D;
				for(; flag28; flag28 = rs.next())
				{
					i3++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_settled_drill('" + s63 + "','" + s95 + "','" + rs.getString(4) + "') ><u>" + nf.format(rs.getDouble(5)) + "</u></td>");
					servletoutputstream1.println("</tr>");
					d17 += rs.getDouble(5);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d17) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_TOT_INV_BAL_DRILL"))
			{
				int j3 = 0;
				String s29 = "";
				String s64 = httpServletRequest.getParameter("user_id");
				String s96 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Total Balance Details - Collection Officer : " + s64 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Total Balance Details - Collection Officer : " + s64 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s115 = " SELECT  CLIENT_CODE,  COLLECTION_OFFICER,  APPLICATION_NO,  FINANCE_NO,  NVL(" + m_schema_name + ".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'" + s96 + "',FINANCE_NO),0) + NVL(" + m_schema_name + ".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'" + s96 + "',FINANCE_NO),0)," + " NVL(" + m_schema_name + ".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'" + s96 + "',FINANCE_NO),0) +NVL(" + m_schema_name + ".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'" + s96 + "',FINANCE_NO),0) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE COLLECTION_OFFICER=UPPER('" + s64 + "') " + " AND APPLICATION_STATUS='ACTIVATED' " + " ORDER BY CLIENT_CODE ";
				rs = stmt1.executeQuery(s115);
				boolean flag29 = rs.next();
				if(!flag29)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s64 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag29)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Application No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Balance</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d18 = 0.0D;
				double d28 = 0.0D;
				for(; flag29; flag29 = rs.next())
				{
					j3++;
					double d29 = rs.getDouble(5) - rs.getDouble(6);
					if(d29 > 0.0D)
					{
						servletoutputstream1.println("<tr>");
						servletoutputstream1.println("<td width='1%'></td>");
						servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('" + rs.getString(3) + "') ><u>" + rs.getString(3) + "</u></td>");
						servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(1) + "</td>");
						servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
						servletoutputstream1.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_balance_drill('" + s64 + "','" + s96 + "','" + rs.getString(4) + "') ><u>" + nf.format(d29) + "</u></td>");
						servletoutputstream1.println("</tr>");
					}
					d18 += d29;
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
				servletoutputstream1.println("<td width='25%' class=div_input><b>Total</b></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d18) + "</b></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_COLL_OFFICER_CONTRACT_DRILL"))
			{
				int k3 = 0;
				String s30 = "";
				String s65 = httpServletRequest.getParameter("user_id");
				String s97 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Contract Details - Collection Officer : " + s65 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Contract Details - Collection Officer : " + s65 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s116 = " SELECT  FINANCE_NO,  " + m_schema_name + ".AF_CO_GET_USER_NAME(MK_OFFICER), " + " " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), " + " CLIENT_CODE, " + " NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-') ACTIVATED_DATE, " + " APPLICATION_STATUS, " + " COLLECTION_OFFICER, " + " NVL(TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),'-') ASSIGN_DATE " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A," + m_schema_name + ".AF_MK_PRO_INQUIRY B " + " WHERE COLLECTION_OFFICER=UPPER('" + s65 + "') AND APPLICATION_STATUS='ACTIVATED' " + " AND  A.INQUARY_NO=B.INQUIRY_CODE " + " ORDER BY FINANCE_NO ";
				rs = stmt1.executeQuery(s116);
				boolean flag30 = rs.next();
				if(!flag30)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s65 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag30)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Activated Date</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Officer Assign Date</b></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Mkt.Officer</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d19 = 0.0D;
				double d30 = 0.0D;
				for(; flag30; flag30 = rs.next())
				{
					k3++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_client('" + rs.getString(4) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("</tr>");
				}
				
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_COLL_OFFICER_UN_ALL_REC_DRILL"))
			{
				int l3 = 0;
				String s31 = "";
				String s66 = httpServletRequest.getParameter("user_id");
				String s98 = httpServletRequest.getParameter("m_date");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Un Allocated Receipt Details - Collection Officer : " + s66 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Un Allocated Receipt Details - Collection Officer : " + s66 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s117 = " SELECT  B.CONTRACT_NO, C.CLIENT_CODE, " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE)," + " B.REC_NO," + " NVL(SUM(BAL_TOBE_RECEIVE),0)   " + " FROM  " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT_BAL A, " + m_schema_name + "DATA.AF_CO_PRO_RECEIPT_DET B,  " + " " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " + " WHERE B.REC_NO=A.REC_NO    " + " AND   B.CONTRACT_NO=C.FINANCE_NO   " + " AND   C.APPLICATION_STATUS='ACTIVATED'  " + " AND   C.COLLECTION_OFFICER=UPPER('" + s66 + "') " + " GROUP BY B.CONTRACT_NO,C.CLIENT_CODE,B.REC_NO ";
				rs = stmt1.executeQuery(s117);
				boolean flag31 = rs.next();
				if(!flag31)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s66 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag31)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Receipt No</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d20 = 0.0D;
				double d31 = 0.0D;
				for(; flag31; flag31 = rs.next())
				{
					l3++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_client('" + rs.getString(2) + "') ><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(4) + "') ><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(5)) + "</td>");
					servletoutputstream1.println("</tr>");
				}
				
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_DETAIL_INV_OS_DRILL"))
			{
				int i4 = 0;
				String s32 = "";
				String s67 = httpServletRequest.getParameter("user_id");
				String s99 = httpServletRequest.getParameter("m_date");
				String s118 = httpServletRequest.getParameter("finance_no");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : " + s67 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : " + s67 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s126 = " SELECT  A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE  FROM  (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT  FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B " + " WHERE A.FINANCE_NO=B.FINANCE_NO " + " AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s99 + "','DD-MM-YYYY'),-1))   " + " AND   B.COLLECTION_OFFICER=UPPER('" + s67 + "') " + " AND   B.FINANCE_NO=UPPER('" + s118 + "') " + " AND   B.APPLICATION_STATUS='ACTIVATED' " + " AND   A.ACTIVE_STATUS='Y'  " + " GROUP BY A.INVOICE_NO)A, " + " (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT " + " FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS C  " + " WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s99 + "','DD-MM-YYYY'),-1))   " + " AND   C.INVOICE_NO IN ( " + " SELECT INVOICE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B " + " WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s99 + "','DD-MM-YYYY'),-1))   " + " AND   A.FINANCE_NO=B.FINANCE_NO " + " AND   B.APPLICATION_STATUS='ACTIVATED' " + " AND   B.COLLECTION_OFFICER=UPPER('" + s67 + "') " + " AND   B.FINANCE_NO=UPPER('" + s118 + "') " + " AND   A.ACTIVE_STATUS='Y' ) " + " GROUP BY C.INVOICE_NO )B " + " WHERE A.INVOICE_NO=B.INVOICE_NO(+) ";
				rs = stmt1.executeQuery(s126);
				boolean flag33 = rs.next();
				if(!flag33)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s67 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag33)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d25 = 0.0D;
				double d36 = 0.0D;
				for(; flag33; flag33 = rs.next())
				{
					i4++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("</tr>");
					d36 += rs.getDouble(4);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d36) + "</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_DETAIL_INV_COLLECT_DRILL"))
			{
				int j4 = 0;
				String s33 = "";
				String s68 = httpServletRequest.getParameter("user_id");
				String s100 = httpServletRequest.getParameter("m_date");
				String s119 = httpServletRequest.getParameter("finance_no");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : " + s68 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : " + s68 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s127 = " SELECT  A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE  FROM  (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT  FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B " + " WHERE A.FINANCE_NO=B.FINANCE_NO " + " AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s100 + "','DD-MM-YYYY'),-1))   " + " AND   B.COLLECTION_OFFICER=UPPER('" + s68 + "') " + " AND   B.FINANCE_NO=UPPER('" + s119 + "') " + " AND   B.APPLICATION_STATUS='ACTIVATED' " + " AND   A.ACTIVE_STATUS='Y'  " + " GROUP BY A.INVOICE_NO)A, " + " (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT " + " FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS C  " + " WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('" + s100 + "','DD-MM-YYYY'),-1))+1)   " + " AND   C.ALLOCATED_DATE<=TO_DATE('" + s100 + "','DD-MM-YYYY') " + " AND   C.INVOICE_NO IN ( " + " SELECT INVOICE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B " + " WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s100 + "','DD-MM-YYYY'),-1))   " + " AND   A.FINANCE_NO=B.FINANCE_NO " + " AND   B.APPLICATION_STATUS='ACTIVATED' " + " AND   B.COLLECTION_OFFICER=UPPER('" + s68 + "') " + " AND   B.FINANCE_NO=UPPER('" + s119 + "') " + " AND   A.ACTIVE_STATUS='Y' ) " + " GROUP BY C.INVOICE_NO )B " + " WHERE A.INVOICE_NO=B.INVOICE_NO ";
				rs = stmt1.executeQuery(s127);
				boolean flag34 = rs.next();
				if(!flag34)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s68 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag34)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d26 = 0.0D;
				double d37 = 0.0D;
				for(; flag34; flag34 = rs.next())
				{
					j4++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("</tr>");
					d37 += rs.getDouble(3);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d37) + "</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_DETAIL_INV_BAL_DRILL"))
			{
				int k4 = 0;
				String s34 = "";
				String s69 = httpServletRequest.getParameter("user_id");
				String s101 = httpServletRequest.getParameter("m_date");
				String s120 = httpServletRequest.getParameter("finance_no");
				servletoutputstream1.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : " + s69 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : " + s69 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				String s128 = " SELECT  XX.INVOICE_NO, XX.TOTAL, NVL(XX.SETT,0)+NVL(YY.SETTELED_AMOUNT,0), NVL(XX.BALANCE,0) - NVL(YY.SETTELED_AMOUNT,0)   FROM   (SELECT A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE   FROM  (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT   FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B  " + " WHERE A.FINANCE_NO=B.FINANCE_NO  " + " AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s101 + "','DD-MM-YYYY'),-1))    " + " AND   B.COLLECTION_OFFICER=UPPER('" + s69 + "')  " + " AND   B.FINANCE_NO=UPPER('" + s120 + "')  " + " AND   B.APPLICATION_STATUS='ACTIVATED'  " + " AND   A.ACTIVE_STATUS='Y'   " + " GROUP BY A.INVOICE_NO)  A,  " + " (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT  " + " FROM  " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS C   " + " WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s101 + "','DD-MM-YYYY'),-1))     " + " AND   C.INVOICE_NO IN (  " + " SELECT INVOICE_NO  " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B  " + " WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s101 + "','DD-MM-YYYY'),-1))     " + " AND   A.FINANCE_NO=B.FINANCE_NO  " + " AND   B.APPLICATION_STATUS='ACTIVATED'  " + " AND   B.COLLECTION_OFFICER=UPPER('" + s69 + "')  " + " AND   B.FINANCE_NO=UPPER('" + s120 + "')  " + " AND   A.ACTIVE_STATUS='Y' " + " )GROUP BY C.INVOICE_NO  )B " + " WHERE A.INVOICE_NO=B.INVOICE_NO(+) )XX, " + " (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT  " + " FROM  LAKDL.AF_CO_PRO_INVOICE_DETAILS C   " + " WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('" + s101 + "','DD-MM-YYYY'),-1))+1)    " + " AND   C.ALLOCATED_DATE<=TO_DATE('" + s101 + "','DD-MM-YYYY')  " + " AND   C.INVOICE_NO IN (  " + " SELECT INVOICE_NO  " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B  " + " WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('" + s101 + "','DD-MM-YYYY'),-1))     " + " AND   A.FINANCE_NO=B.FINANCE_NO  " + " AND   B.APPLICATION_STATUS='ACTIVATED'  " + " AND   B.COLLECTION_OFFICER=UPPER('" + s69 + "')  " + " AND   B.FINANCE_NO=UPPER('" + s120 + "')  " + " AND   A.ACTIVE_STATUS='Y' ) " + " GROUP BY C.INVOICE_NO )YY  " + " WHERE XX.INVOICE_NO=YY.INVOICE_NO(+)  ";
				rs = stmt1.executeQuery(s128);
				boolean flag35 = rs.next();
				if(!flag35)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Collection Officer " + s69 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag35)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				double d27 = 0.0D;
				double d38 = 0.0D;
				for(; flag35; flag35 = rs.next())
				{
					k4++;
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(2)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(3)) + "</td>");
					servletoutputstream1.println("<td width='25%' align='right' class=div_input>" + nf.format(rs.getDouble(4)) + "</td>");
					servletoutputstream1.println("</tr>");
					d38 += rs.getDouble(4);
				}
				
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>Total</td>");
				servletoutputstream1.println("<td width='25%' align='right' class=div_input><b>" + nf.format(d38) + "</td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_FINANCE_DETAIL_DRILL"))
			{
				int l4 = 0;
				String s35 = "";
				String s70 = httpServletRequest.getParameter("finance_no");
				rs = stmt1.executeQuery(" SELECT  A.APPLICATION_NO,  NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'),  NVL(A.CLIENT_CODE,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), " + " NVL(A.CLIENT_NO,0), " + " NVL(A.INQUARY_NO,'-'), " + " NVL(A.FINANCE_NO,'-'), " + " NVL(A.CO_APPLICANT,'-'), " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-'), " + " NVL(A.FACILITY_NO,'-'), " + " NVL(A.TOTAL_FINANCE_AMOUNT,0), " + " NVL(A.CURRENT_FINANCE_AMOUNT,0), " + " NVL(A.CURRENCY_CODE,'-'), " + " NVL(A.TRANSACTION_TYPE,'-'), " + " NVL(B.DESCRIPTION,'-'), " + " NVL(A.ALLO_STATUS,'-'), " + " NVL(A.ALLOCATED_TO,'-'), " + " NVL(A.COLLECTION_OFFICER,'-'), " + " NVL(TO_CHAR(A.ASSIGN_DATE,'DD-MM-YYYY'),'-'), " + " NVL(A.TER_STATUS,'-'), " + " NVL(A.TER_TYPE,'-'), " + " NVL(C.TERMINATION_DESC,'-'), " + " NVL(DECODE(A.CLIENT_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.GUARANTO_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.PRICING_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.VALUATION_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.PRO_FORMA_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.ASSET_STATUS,'Y','Yes'),'-'), " + " NVL(A.TERMINATION_NO,'-'), " + " NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), " + " NVL(DECODE(A.PAYMENT_STATUS,'Y','Yes'),'-'), " + " NVL(A.INSURANCE_DONE_BY,'-'), " + " NVL(A.POSTED_NAME,'-'), " + " NVL(A.POST_ADDRESS1,'-'), " + " NVL(A.POST_ADDRESS2,'-'), " + " NVL(A.TELEPHONE,'-'), " + " NVL(TO_CHAR(A.POSTED_DATE,'DD-MM-YYYY'),'-')," + " NVL(A.PREVIOUS_STATUS,'-'), " + " NVL(A.PRIORITY,'-') " + ",\tNVL(" + m_schema_name + ".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),'-') " + "FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A," + m_schema_name + ".AF_CO_MAS_TRANSACTION_TYPE B," + m_schema_name + ".AF_CO_MAS_TERMINATION_TYPE C  " + "WHERE A.TRANSACTION_TYPE=B.TRAN_CODE(+) AND A.TER_TYPE=C.TERMINATION_TYPE(+) " + "AND A.FINANCE_NO='" + s70 + "' ");
				boolean flag6 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Finance Details - Finance No : " + s70 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Finance Details - Finance No : " + s70 + "  </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag6)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Finance No  " + s70 + "  </b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag6)
				{
					l4++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Application No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('" + rs.getString(1) + "')><u>" + rs.getString(1) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Application Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(2) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(3) + "')><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(3) + "')><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input onClick=\"\" style='cursor:hand' >" + rs.getString(7) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Co-Applicant Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(8) + "')><u>" + rs.getString(8) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Co-Applicant Name</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(8) + "')><u>" + rs.getString(9) + "</u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Facility No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Total Finance Amount</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + nf.format(rs.getDouble(11)) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Current Finance Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(12)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Currency</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(13) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Transaction Type</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(15) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Allocation Status</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(16) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Allocated To</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(17) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Collection Officer</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(18) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Assigned Date</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(19) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Branch Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(40) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input>&nbsp;</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>&nbsp;</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='*%'><u><b>Termination Details</b></u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Termination No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(29) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Termination Status</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(20) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Termination Type</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(22) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(23) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Guarantor Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_guarantor('" + rs.getString(1) + "') ><u>" + rs.getString(24) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Pricing Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_pricing('" + rs.getString(1) + "')><u>" + rs.getString(25) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Valuation Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_valuation('" + rs.getString(1) + "')><u>" + rs.getString(26) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Proforma Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma('" + rs.getString(1) + "')><u>" + rs.getString(27) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Asset Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset('" + rs.getString(1) + "')><u>" + rs.getString(28) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Payment Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_payment('" + rs.getString(3) + "')><u>" + rs.getString(31) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Activated Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(30) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Insurance Done By</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(32) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Posted Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(33) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Posted Address</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(34) + ", " + rs.getString(35) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Posted Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(37) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Tel No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(36) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Previous Status</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(38) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Priority</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(39) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					boolean flag7 = rs.next();
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_APPLICATION_DETAIL_DRILL"))
			{
				int i5 = 0;
				String s36 = "";
				String s71 = httpServletRequest.getParameter("application_no");
				rs = stmt1.executeQuery(" SELECT  A.APPLICATION_NO,  " + m_schema_name + ".AF_CO_GET_APP_STATUS(APPLICATION_NO), " + " NVL(A.CLIENT_CODE,'-'), " +
					" NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), " + 
					" NVL(A.CLIENT_NO,0), " + " NVL(A.INQUARY_NO,'-'), " + " NVL(A.FINANCE_NO,'-'), " + 
					" NVL(A.CO_APPLICANT,'-'), " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-'), " +
					" NVL(A.FACILITY_NO,'-'), " + " NVL(A.TOTAL_FINANCE_AMOUNT,0), " + " NVL(A.CURRENT_FINANCE_AMOUNT,0), " +
					" NVL(A.CURRENCY_CODE,'-'), " + " NVL(A.TRANSACTION_TYPE,'-'), " + " NVL(B.DESCRIPTION,'-'), " + 
					" NVL(A.ALLO_STATUS,'-'), " + " NVL(A.ALLOCATED_TO,'-'), " + " NVL(A.COLLECTION_OFFICER,'-'), " + 
					" NVL(TO_CHAR(A.ASSIGN_DATE,'DD-MM-YYYY'),'-'), " + " NVL(A.TER_STATUS,'-'), " + 
					" NVL(A.TER_TYPE,'-'), " + " NVL(C.TERMINATION_DESC,'-'), " + 
					" NVL(DECODE(A.CLIENT_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.GUARANTO_STATUS,'Y','Yes'),'-'), " +
					" NVL(DECODE(A.PRICING_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.VALUATION_STATUS,'Y','Yes'),'-'), " + 
					" NVL(DECODE(A.PRO_FORMA_STATUS,'Y','Yes'),'-'), " + " NVL(DECODE(A.ASSET_STATUS,'Y','Yes'),'-'), " +
					" NVL(A.TERMINATION_NO,'-'), " + " NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), " + 
					" NVL(DECODE(A.PAYMENT_STATUS,'Y','Yes'),'-'), " +  
					" NVL(DECODE(A.INSURANCE_DONE_BY,'LICENSEE','COMPANY','BROKER','BROKER','CLIENT','CLIENT'),'-'), " +  //" NVL(A.INSURANCE_DONE_BY,'-'), " + Modified by Kanchana for issue  #19461
					" NVL(A.POSTED_NAME,'-'), " + " NVL(A.POST_ADDRESS1,'-'), " + 
					" NVL(A.POST_ADDRESS2,'-'), " + " NVL(A.TELEPHONE,'-'), " +
					" NVL(TO_CHAR(A.POSTED_DATE,'DD-MM-YYYY'),'-')," + 
					" NVL(A.PREVIOUS_STATUS,'-'), " + " NVL(A.PRIORITY,'-') " + 
					" ,NVL(" + m_schema_name + ".AF_CO_GET_REMARK(A.APPLICATION_NO),'-')   "+ // 40 added by udara 29-04-2015
					" FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A," + m_schema_name + ".AF_CO_MAS_TRANSACTION_TYPE B," + m_schema_name + ".AF_CO_MAS_TERMINATION_TYPE C  " + "WHERE A.TRANSACTION_TYPE=B.TRAN_CODE(+) AND A.TER_TYPE=C.TERMINATION_TYPE(+) " + "AND A.APPLICATION_NO='" + s71 + "' ");
				boolean flag8 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Application  Details - Application No : " + s71 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Application  Details - Application No : " + s71 + "  </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag8)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Application No  " + s71 + "  </b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag8)
				{
					i5++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Application No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input onClick=\"\" style='cursor:hand' >" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Application Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input><b>" + rs.getString(2) + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(3) + "')><u>" + rs.getString(3) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(3) + "')><u>" + rs.getString(4) + "</u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_inquiry_drill('" + rs.getString(6) + "')><u>" + rs.getString(6) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Finance No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input onclick=show_finance_detail_drill('" + rs.getString(7) + "') style=cursor:hand;cursor-color:blue><u>" + rs.getString(7) + "</u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Co-Applicant Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(8) + "')><u>" + rs.getString(8) + "</u></td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Co-Applicant Name</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('" + rs.getString(8) + "')><u>" + rs.getString(9) + "</u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Facility No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Total Finance Amount</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + nf.format(rs.getDouble(11)) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Current Finance Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(12)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Currency</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(13) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Transaction Type</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(15) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Allocation Status</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(16) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Allocated To</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(17) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Collection Officer</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(18) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Assigned Date</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(19) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='*%'><u><b>Termination Details</b></u></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Termination No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(29) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Termination Status</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(20) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Termination Type</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(22) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Client Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(23) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Guarantor Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_guarantor('" + rs.getString(1) + "') ><u>" + rs.getString(24) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Pricing Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_pricing('" + rs.getString(1) + "')><u>" + rs.getString(25) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Valuation Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_valuation('" + rs.getString(1) + "')><u>" + rs.getString(26) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Proforma Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma('" + rs.getString(1) + "')><u>" + rs.getString(27) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Asset Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset('" + rs.getString(1) + "')><u>" + rs.getString(28) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Payment Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_payment('" + rs.getString(3) + "')><u>" + rs.getString(31) + "</u></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Sanction Letter</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=load_data_report('" + rs.getString(1) + "')><u>View</u></td>");
					servletoutputstream1.println("</tr>");
					
					// added by udara 16-10-2013
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Documents Required</b></td>");
					servletoutputstream1.println("<td width='50%' class=  div_input style=cursor:hand;cursor-color:blue onclick=load_documents_required('" + rs.getString(1) + "','" + rs.getString(3) + "')><u>View</u></td>");
					servletoutputstream1.println("</tr>");
					// end by udara 16-10-2013
					
					// added by udara 03-04-2014
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Running Contracts</b></td>");
					servletoutputstream1.println("<td width='50%' class=  div_input style=cursor:hand;cursor-color:blue onclick=show_run_con_det('" + rs.getString(1) + "') ><u>View</u></td>");
					servletoutputstream1.println("</tr>");
					// end by udara 03-04-2014
					
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Activated Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(30) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Insurance Done By</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(32) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Posted Name</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(33) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Posted Address</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(34) + ", " + rs.getString(35) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Posted Date</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(37) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Tel No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(36) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Previous Status</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(38) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Priority</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(39) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					
					// added by udara 29-04-2015
					
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Comments</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(40) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b></b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					
					// end by udara 29-04-2015
					
					servletoutputstream1.println("</table>");
					boolean flag9 = rs.next();
				}
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_FOLLOWUP_CATEGORY_DRILL"))
			{
				int j5 = 0;
				String s37 = "";
				String s72 = httpServletRequest.getParameter("cat_code");
				rs = stmt1.executeQuery(" SELECT  CATEGORY_CODE,  NVL(CATEGORY_NAME,'-'),  DECODE(DEFAULT_VALUE,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_FOLLOWUP_CATEGORY " + "WHERE CATEGORY_CODE='" + s72 + "' AND ACTIVE_STATUS='Y' ");
				boolean flag10 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> FollowUp Category  Details - Category Code : " + s72 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> FollowUp Category  Details - Category Code : " + s72 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag10)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Category Code " + s72 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag10; flag10 = rs.next())
				{
					j5++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Category Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Category Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_LAWYER_DRILL"))
			{
				int k5 = 0;
				String s38 = "";
				String s73 = httpServletRequest.getParameter("lawyer_code");
				rs = stmt1.executeQuery(" SELECT  LAWYER_CODE,  NVL(FIRST_NAME,'-'),  NVL(LAST_NAME,'-'),  NVL(NAME_WITH_INITIALS,'-'),  NVL(ADDRESS1,'-'),  NVL(ADDRESS2,'-'),  NVL(CITY_CODE,'-'),  NVL(TEL_NO,'-'),  NVL(OFFICE_TEL_NO,'-'),  NVL(FAX_NO,'-'),  NVL(OFFICE_FAX_NO,'-'),  NVL(MOBILE_NO,'-'),  NVL(FEE_PER_CASE,0),  NVL(MONTHLY_FEE,0) FROM " + m_schema_name + ".AF_CO_MAS_LAWYER " + "WHERE LAWYER_CODE='" + s73 + "' AND ACTIVE_STATUS='Y' ");
				boolean flag11 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Lawyer  Details - Lawyer Code : " + s73 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Lawyer  Details - Lawyer Code : " + s73 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag11)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Lawyer Code " + s73 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag11; flag11 = rs.next())
				{
					k5++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Lawyer Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>First Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Last Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Name with Initials</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br><br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b><u>Contact Details</u></b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Address</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(5) + "," + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b> Tel No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>City</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Office Tel No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Fax No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b>Office Fax No</b></td>");
					servletoutputstream1.println("<td width='*%' class=div_input>" + rs.getString(11) + "</td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Mobile No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(12) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Fee Per Case</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(13)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Monthly Fee</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(14)) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%' class=div_input></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_DISCOUNT_RATE_DRILL"))
			{
				int l5 = 0;
				String s39 = "";
				String s74 = httpServletRequest.getParameter("rate");
				rs = stmt1.executeQuery(" SELECT  NVL(RATE,0),  NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),'-') FROM " + m_schema_name + ".AF_CO_MAS_DISCOUNT_RATE " + "WHERE RATE=" + s74 + " ");
				boolean flag12 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Discount Rate  Details - Discount Rate : " + s74 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Discount Rate  Details - Discount Rate : " + s74 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag12)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Rate " + s74 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag12; flag12 = rs.next())
				{
					l5++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Discount Rate</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + nf.format(rs.getDouble(1)) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>From Date</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_EARLY_TERMINATION_CHARGE_DRILL"))
			{
				int i6 = 0;
				String s40 = "";
				String s75 = httpServletRequest.getParameter("termi_type");
				rs = stmt1.executeQuery("  SELECT  TERMINATION_TYPE,  NVL(DESCRIPTION,'-'),  NVL(AMOUNT,0) FROM " + m_schema_name + ".AF_CO_MAS_EARLY_TERMI_CHARGE " + "WHERE TERMINATION_TYPE='" + s75 + "' AND ACTIVE_STATUS='Y' ");
				boolean flag13 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Early Termination Charge  Details - Termination Type : " + s75 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Early Termination Charge  Details - Termination Type : " + s75 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag13)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Termination Type " + s75 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag13; flag13 = rs.next())
				{
					i6++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Termination Type</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Description</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Amount</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + nf.format(rs.getDouble(3)) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_RMV_AGENTS_DRILL"))
			{
				int j6 = 0;
				String s41 = "";
				String s76 = httpServletRequest.getParameter("agent_code");
				rs = stmt1.executeQuery(" SELECT  RMV_AGENT_CODE,  NVL(NAME,'-'),  NVL(ADDRESS1,'-'),  NVL(ADDRESS2,'-'),  NVL(CITY_CODE,'-'),  NVL(MOBILE_NO,'-'),  NVL(TEL_NO,'-'),  NVL(MONTHLY_FEE,0),  NVL(FEE_FOR_CASE,0),  DECODE(DEFAULT_VALUE,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_RMV_AGENTS " + "WHERE RMV_AGENT_CODE='" + s76 + "' AND ACTIVE_STATUS='Y' ");
				boolean flag14 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> RMV Agent  Details - Agent Code : " + s76 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> RMV Agent  Details - Agent Code : " + s76 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag14)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Agent Code " + s76 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag14; flag14 = rs.next())
				{
					j6++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> RMV Agent Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Address</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "," + rs.getString(4) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> City</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Mobile No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Tel No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Monthly Fee</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + nf.format(rs.getDouble(8)) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Fee For Case</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + nf.format(rs.getDouble(9)) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_REPAYMENT_METHOD_DRILL"))
			{
				int k6 = 0;
				String s42 = "";
				String s77 = httpServletRequest.getParameter("method_code");
				rs = stmt1.executeQuery(" SELECT  REPAYMENT_TYPE,  NVL(DESCRIPTION,'-'),  DECODE(DEFAULT_VALUE,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_REPAYMENT_METHOD " + "WHERE REPAYMENT_TYPE='" + s77 + "' AND ACTIVE_STATUS='Y' ");
				boolean flag15 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Repayment Method  Details - Repayment Type : " + s77 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Repayment Method  Details - Repayment Type : " + s77 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag15)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Repayment Type " + s77 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag15; flag15 = rs.next())
				{
					k6++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='35%' class=div_input><b> Repayment Method Code </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='35%' class=div_input><b> Repayment Method Desc.</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='35%' class=div_input><b> Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_VENDOR_DRILL"))
			{
				int l6 = 0;
				String s43 = "";
				String s78 = httpServletRequest.getParameter("vendor_code");
				rs = stmt1.executeQuery(" SELECT  A.VENDOR_CODE,  NVL(A.NAME,'-'),  NVL(A.CATEGORY,'-'),  NVL(B.DESCRIPTION,'-'),  NVL(A.TYPE,'-'),  DECODE(A.DEFAULT_VALUE,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_VENDORS A," + m_schema_name + ".AF_CO_MAS_ITEM_CATEGORY B " + "WHERE A.CATEGORY=B.ITEM_CAT_CODE(+) " + "AND A.VENDOR_CODE='" + s78 + "' ");
				boolean flag16 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Vendor  Details - Vendor Code : " + s78 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Vendor  Details - Vendor Code : " + s78 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag16)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Vendor Code " + s78 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag16)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Vendor Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Category</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Vendor Type</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				rs = stmt1.executeQuery("  SELECT  NVL(A.BRANCH,'-'),  NVL(A.LOCATION_CODE,'-'),  NVL(B.LOCATION_DESC,'-'),  NVL(A.TITLE,'-'),  NVL(A.FIRST_NAME,'-'),  NVL(A.LAST_NAME,'-'),  NVL(A.ID_NO,'-'),  NVL(A.ADDRESS,'-'),  NVL(INITCAP(A.CITY_CODE),'-'),  NVL(DECODE(A.DEFAULT_VALUE,'Y','Yes','No'),'-') FROM " + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION A," + m_schema_name + ".AF_CO_MAS_LOCATION B " + "WHERE A.LOCATION_CODE=B.LOCATION_CODE " + "AND A.VENDOR_CODE='" + s78 + "' ");
				boolean flag21 = rs.next();
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				servletoutputstream1.println("<tr>");
				servletoutputstream1.println("<td width='1%'></td>");
				servletoutputstream1.println("<td width='80%' class=div_input><b><u>Vendor Location Details</u></b></td>");
				servletoutputstream1.println("<td width='*%'></td>");
				servletoutputstream1.println("</tr>");
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("<br>");
				for(; flag21; flag21 = rs.next())
				{
					l6++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input>" + l6 + ".<b> Branch </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Location</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>ID No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(7) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Address</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(8) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>City </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(9) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Default Value(Location)</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(10) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_SUB_CHARGE_DRILL"))
			{
				int i7 = 0;
				String s44 = "";
				String s79 = httpServletRequest.getParameter("sub_type_code");
				rs = stmt1.executeQuery(" SELECT  A.SUB_TYPE_CODE,  NVL(A.DESCRIPTION,'-'),  NVL(A.TYPE_CODE,'-'),  NVL(B.DESCRIPTION,'-'),  DECODE(A.DEFAULT_VALUE,'Y','Yes','No'),  DECODE(A.MAINTENANCE_STATUS,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_SUB_CHARGES A," + m_schema_name + ".AF_CO_MAS_CHARGES B " + "WHERE A.TYPE_CODE=B.TYPE_CODE " + "AND A.SUB_TYPE_CODE='" + s79 + "' AND A.ACTIVE_STATUS='Y' ");
				boolean flag17 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Sub Charge  Details - Sub Type Code : " + s79 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Sub Charge  Details - Sub Type Code : " + s79 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag17)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Sub Type Code " + s79 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag17; flag17 = rs.next())
				{
					i7++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Sub Type Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Description</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Type Code</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Maintenance Status</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(6) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_REPAYMENT_INTERVAL_DRILL"))
			{
				int j7 = 0;
				String s45 = "";
				String s80 = httpServletRequest.getParameter("duration");
				rs = stmt1.executeQuery(" SELECT  DURATION,  NVL(DESCRIPTION,'-'),  NVL(DURATION_TYPE,'-'),  DECODE(DEFAULT_VALUE,'Y','Yes','No') FROM " + m_schema_name + ".AF_CO_MAS_REPAYMENT_INTERVAL " + "WHERE DURATION=" + s80 + " AND ACTIVE_STATUS='Y' ");
				boolean flag18 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Repayment  Details - Duration : " + s80 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Repayment  Details - Duration : " + s80 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag18)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Duration " + s80 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag18; flag18 = rs.next())
				{
					j7++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Duration </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Repayment Desc.</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Duration Type</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_BROKER_DRILL"))
			{
				int k7 = 0;
				String s46 = "";
				String s81 = httpServletRequest.getParameter("broker_code");
				rs = stmt1.executeQuery(" SELECT  A.BROKER_CODE,  NVL(INITCAP(A.TITLE),'-'),  NVL(A.FIRST_NAME,'-'),  NVL(A.LAST_NAME,'-'),  NVL(A.ID_NO,'-'),  NVL(A.ADDRESS1,'-'),  NVL(A.ADDRESS2,'-'),  NVL(A.LOCATION_CODE,'-'),  NVL(B.LOCATION_DESC,'-'),  NVL(A.CITY_CODE,'-'),  NVL(A.POSTAL_CODE,'-'),  NVL(A.CONTACT_NO,'-'),  NVL(A.MOBILE_NO,'-'),  NVL(A.FAX_NO,'-'),  NVL(A.SECTOR_CODE,'-'),  NVL(A.COMMISSION_RATE,0),  NVL(A.COMMISSION_AMOUNT,0),  NVL(A.COMMENTS,'-'),  NVL(DECODE(A.BROKER_STATUS,'Y','Yes','No'),'-') FROM " + m_schema_name + ".AF_CO_MAS_BROKER A, " + m_schema_name + ".AF_CO_MAS_LOCATION B " + "WHERE A.BROKER_CODE='" + s81 + "' AND A.ACTIVE_STATUS='Y' " + "AND A.LOCATION_CODE=B.LOCATION_CODE ");
				boolean flag19 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Broker  Details - Broker Code : " + s81 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B> Broker  Details - Broker Code : " + s81 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag19)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Broker Code " + s81 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag19; flag19 = rs.next())
				{
					k7++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Broker Code </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Broker Name</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> ID No</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(5) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Address </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(6) + ", " + rs.getString(7) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Location </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(9) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> City </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(10) + " </td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Postal Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(11) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b> Contact No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(12) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Mobile No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(13) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b> Fax No</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(14) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Sector Code</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(15) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b> Comm. Rate</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(16) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Comments</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(18) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input><b> Comm. Amount</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + nf.format(rs.getDouble(17)) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b>Broker Status</b></td>");
					servletoutputstream1.println("<td width='25%' class=div_input>" + rs.getString(19) + "</td>");
					servletoutputstream1.println("<td width='20%' class=div_input></td>");
					servletoutputstream1.println("<td width='25%' class=div_input></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_LEAD_SOURCE_DRILL"))
			{
				int l7 = 0;
				String s47 = "";
				String s82 = httpServletRequest.getParameter("src_code");
				rs = stmt1.executeQuery(" SELECT  CODE,  NVL(DESCRIPTION,'-'),  DECODE(DEFAULT_VALUE,'Y','Yes','No'),  NVL(TO_CHAR(CREATED_DATE,'DD-MM-YYYY'),'-') FROM " + m_schema_name + ".AF_MK_MAS_LEAD_SOURCE " + "WHERE ACTIVE_STATUS='Y' AND CODE='" + s82 + "' ");
				boolean flag20 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE> Lead Source  Details - Lead Source Code : " + s82 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Lead Source Details - Lead Source Code : " + s82 + " </B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag20)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='80%' class=div_input><b>No records found for Lead Source Code " + s82 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				for(; flag20; flag20 = rs.next())
				{
					l7++;
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Lead Source Code </b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Lead Source Desc.</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='1%'></td>");
					servletoutputstream1.println("<td width='30%' class=div_input><b> Lead Source Date</b></td>");
					servletoutputstream1.println("<td width='50%' class=div_input>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else if(m_chksql.equals("SHOW_ARREARS_DETAIL_DRILL"))
			{
				String s12 = httpServletRequest.getParameter("lease_no");
				double d1 = 0.0D;
				double d6 = 0.0D;
				rs = stmt.executeQuery(" SELECT SUM(BALANCE_TO_BE_RECEIVED)  FROM " + m_schema_name + ".AF_CO_PRO_INVOICE " + " WHERE  FINANCE_NO='" + s12 + "' " + " AND ACTIVE_STATUS<>'CANCEL' AND TOTAL_AMOUNT<>0 ");
				boolean flag32 = rs.next();
				if(flag32)
					d6 = rs.getDouble(1);
				rs = stmt.executeQuery(" SELECT SUM(A.GROSS_AMOUNT)  FROM " + m_schema_name + ".AF_CO_PRO_APP_PRICING A, " + " " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B " + " WHERE A.PRICING_NO = B.PRICING_NO AND " + " A.APPLICATION_NO = B.APPLICATION_NO AND " + " A.PRO_INVOICE_NO = B.INVOICE_NO AND " + " B.APPLICATION_NO IN (SELECT APPLICATION_NO " + " FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS " + " WHERE  FINANCE_NO='" + s12 + "') AND " + " B.ACTIVE_STATUS='Y' ");
				boolean flag36 = rs.next();
				if(flag36)
					d1 = rs.getDouble(1);
				rs = stmt.executeQuery(" SELECT NVL(B.INVOICE_NO,'-'),NVL(B.CHASSIS_NO,'-'),NVL(B.REG_NO,'-'),  NVL(" + m_schema_name + ".AF_CO_GET_MODEL_DESC(B.MODEL_CODE),'-'),A.GROSS_AMOUNT " + " FROM " + m_schema_name + ".AF_CO_PRO_APP_PRICING A, " + " " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B " + " WHERE A.PRICING_NO = B.PRICING_NO AND " + " A.APPLICATION_NO = B.APPLICATION_NO AND " + " A.PRO_INVOICE_NO = B.INVOICE_NO AND " + " B.APPLICATION_NO IN (SELECT APPLICATION_NO " + " FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS " + " WHERE  FINANCE_NO='" + s12 + "') AND " + " B.ACTIVE_STATUS='Y' ");
				boolean flag40 = rs.next();
				servletoutputstream1.println("<HTML><HEAD><TITLE>Arrears Details - Finance No: " + s12 + " </TITLE></HEAD>");
				servletoutputstream1.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				servletoutputstream1.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				servletoutputstream1.println("<FORM NAME='Form1' method='post'>");
				servletoutputstream1.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				servletoutputstream1.println("<TR><TD><CENTER><B>Arrears Details - Finance No: " + s12 + "</B></TD></TR>");
				servletoutputstream1.println("</TABLE>");
				servletoutputstream1.println("<BR><BR>");
				if(!flag40)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='5%'></td>");
					servletoutputstream1.println("<td width='75%' class=div_input><b>No records found for Finance No: " + s12 + "</b></td>");
					servletoutputstream1.println("<td width='*%'></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
				}
				if(flag40)
				{
					servletoutputstream1.println("<table align='center' width='100%' class='table' >");
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='5%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Chassis No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Reg No</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input ><b>Model</b></td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'><b>Arrears As Proportion</b></td>");
					servletoutputstream1.println("</tr>");
					servletoutputstream1.println("</table>");
					servletoutputstream1.println("<br>");
				}
				servletoutputstream1.println("<table align='center' width='100%' class='table' >");
				for(; flag40; flag40 = rs.next())
				{
					servletoutputstream1.println("<tr>");
					servletoutputstream1.println("<td width='5%'></td>");
					servletoutputstream1.println("<td width='15%' class=div_input>" + rs.getString(1) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input>" + rs.getString(2) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input>" + rs.getString(3) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input>" + rs.getString(4) + "</td>");
					servletoutputstream1.println("<td width='15%' class=div_input align='right'>" + nf.format((rs.getDouble(5) * d6) / d1) + "</td>");
					servletoutputstream1.println("</tr>");
				}
				
				servletoutputstream1.println("</table>");
				servletoutputstream1.println("</form>");
				servletoutputstream1.println("<SCRIPT language1.2='JavaScript' src='" + s3 + "/leasing_drill_down.js'></SCRIPT>");
				servletoutputstream1.println("</BODY></HTML>");
			} else
			{
				servletoutputstream1.println("Undefined");
			}
			servletoutputstream1.close();
			connection.close();
			destroy();
			
		}
		catch(Exception exception)
		{
			try
			{
				connection.close();
			}
			catch(Exception exception1) { }
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			exception.printStackTrace(new PrintWriter(bytearrayoutputstream));
			ServletOutputStream servletoutputstream = httpServletResponse.getOutputStream();
			servletoutputstream.println(bytearrayoutputstream.toString());
			servletoutputstream.close();
		}
		
		// added by udara 02-05-2019
		finally{
			
			//if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(connection!=null){try{connection.close();  }catch(Exception e){}}
		}
		// end by udara 02-05-2019
		
		
	}
	
}
