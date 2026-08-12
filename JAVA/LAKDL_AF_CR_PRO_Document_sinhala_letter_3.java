// CREATED BY SAJITH MENDIS ON 25/11/2013

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_sinhala_letter_3 extends javax.servlet.http.HttpServlet { 
	
	// commented by udara 09-01-2018
	/*
	ServletOutputStream out = null;
	
	Connection conn;
	Statement stmt,stmt2,stmt_doc_charges,stmt_make,stmt_rental;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs2,rs_doc_charges,rs_make,rs_anx_status,rs_rental;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // commented by udara 09-01-2018
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // added by udara 09-01-2018
		
		
		ServletOutputStream out = null;
	
		Connection conn= null;
		Statement stmt= null,stmt2= null,stmt_doc_charges= null,stmt_make= null,stmt_rental= null;

		java.text.NumberFormat nf= null;
		
		ResultSet rs= null,rs2= null,rs_doc_charges= null,rs_make= null,rs_anx_status= null,rs_rental= null;
		
		
		String reqstr= null,m_Letter_date= null,m_c_code= null,m_name= null,m_city_desc= null,m_due_date= null,m_no_of_due_date= null,m_finance_no= null;
		double m_amount_due=0;
		String rec_count= null;
		
		
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			// out.println("conn"+conn);
			int m_data_count=0;
			String m_status ="";
			
			
			//Decaring variables
			
			String part1 = ""; // 004
			String part2 = ""; 
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_vehicle_num="";
			String arreas_period="";
			
			String finance_no = "";
						double tot_arrears =0.00;
		    double m_rental_amt  =0.00;
			double m_rental_amt_future  =0.00;
			double m_bal_arrears  =0.00;
			double m_odi_amt     =0.00;
			double m_other_amt   =0.00;
			double m_other_amt_future   =0.00;
			double m_total_amt   =0.00;
			double m_total_amt_future =0.00;
			double m_odi_amt_future=0.00;
			double m_excess_receipt_amount =0.00; 

			
			
			String m_chksql = req.getParameter("chksql");
			String m_application_no = req.getParameter("application_no");
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
			if(m_chksql.trim().equals("main_page")){
				//stmt = conn.createStatement ();
				stmt_doc_charges = conn.createStatement ();
				stmt_make = conn.createStatement ();
				stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
				
				//String m_application_no = req.getParameter("application_no");
				//String m_client_code	  =req.getParameter("client_code");		
				String m_client_code="";
				String m_document_code="";
				String m_print="";
				if(req.getParameter("client_code")!=null){
					m_client_code	  =req.getParameter("client_code");		
				}
				if(req.getParameter("document_code")!=null){
					m_document_code	=req.getParameter("document_code");	
				}
				if(req.getParameter("print")!=null){
					m_print=req.getParameter("print");
				}
				
				//String m_document_code	=req.getParameter("document_code");	
				//String m_print=req.getParameter("print");
				m_document_code = "SIN_LETT_3"; // added by udara 09-01-2018
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
			
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				
				
				
				stmt = conn.createStatement ();
			
				rs = stmt.executeQuery (" SELECT  "+
											"NVL(CLIENT_CODE,'-'),NVL(FULL_NAME,'-'), NVL(address1,'-'), NVL(address2,'-'),NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(city_code),'-'), NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO('"+m_application_no+"'),'-')   "+
											"FROM   "+
											""+m_schema_name+".AF_CO_MAS_CLIENT	  "+
										  "WHERE CLIENT_CODE = '"+m_client_code+"'  ");
				
				boolean more1 = rs.next();
				if(more1){
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_vehicle_num = rs.getString(6);
				}
				rs.close();
				stmt.close();
				
				
				
				
				stmt = conn.createStatement ();
			
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') FROM DUAL");
				
				boolean more4 = rs.next();
				if(more4){
					finance_no=rs.getString(1);
				}
				rs.close();
				stmt.close();
				
				// commented by udara 16-01-2018
				/*
				stmt = conn.createStatement ();
				rs = stmt.executeQuery (" SELECT NVL("+m_schema_name+".AF_CO_GET_ARREAS_PERIOD('"+finance_no+"','"+m_username+"'),0) FROM DUAL");
				
				 more4 = rs.next();
				if(more4){
					arreas_period=rs.getString(1);
				}
				rs.close();
				stmt.close();
				*/
				
				
				// commented by udara 09-01-2018
				/*
				
				stmt = conn.createStatement ();
			
				rs = stmt.executeQuery ("SELECT "+
				//out.println("SELECT "+
									         " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
									         "  NVL((SELECT REG_NO FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO AND REG_NO IS NOT NULL AND ROWNUM=1 ),'-') REG_NO,  "+  
									          " "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'),NULL) DUE_AMOUNT, "+  
									          " "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO),   "+    
									          " "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), "+ 
									          " "+m_schema_name+".AF_CO_CLOSING_RATE(A.APPLICATION_NO),   "+
									         "  NVL(( SELECT  SUM(NVL(CAPITAL_AMOUNT,0))  "+
									                "    FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
									                  "  WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO  "+
									                     "     FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
									                       "   WHERE  APPLICATION_NO =   A.APPLICATION_NO   "+
									                      "    AND       ACTIVE_STATUS  =   'Y'  "+
									                       "   )  "+
									                       "   AND INVOICE_NO IS NULL   "+
									                        "  AND APPLICATION_NO = A.APPLICATION_NO   "+
									          "   ),0) OUTS_CAPITAL, "+
									         "   NVL((  "+
									          "   SELECT   "+
									          "   SUM(BALANCE_TO_BE_RECEIVED)  "+
									          "   FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
									          "   WHERE     ACTIVE_STATUS='Y'  "+
									          "   AND  A.FINANCE_NO = '"+finance_no+"'  "+
									           "  AND  VALUE_DATE > TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')   "+
									          "   AND FINANCE_NO IN     "+
									           "     (SELECT  "+
									            "        FINANCE_NO  "+
									               "     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
									               "     WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO)  "+
									              "      AND APPLICATION_STATUS<>'CANCEL')   "+
									          "   ),0) DUE_AMOUNT_INV,   "+
									       "    NVL((  "+
									        "   SELECT  SUM(ODI_BAL_AMOUNT) FROM (  "+
									            "            SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT  "+
									               "          FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
									                "             "+m_schema_name+".AF_CO_PRO_INVOICE B,   "+
									                  "           "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C  "+
									                     "        WHERE A.FINANCE_NO ='"+finance_no+"' AND   "+
									                         "    A.FINANCE_NO = B.FINANCE_NO AND   "+
									                         "    B.ACTIVE_STATUS='Y' AND   "+
									                         "    B.INVOICE_NO = C.INVOICE_NO    "+
									                    "   )  "+
									        "   ),0) ODI,  "+
									         "  NVL((           "+
									         "  SELECT   "+
									              "   SUM(BALANCE_TO_BE_RECEIVED)  "+
									              "   FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
									               "  WHERE     ACTIVE_STATUS='Y'  "+
									               "  AND  A.FINANCE_NO =  '"+finance_no+"'  "+
									          "  AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+
									         "  ),0) INSURANCE_CHARGE,  "+
									        "   NVL((           "+
									        "   SELECT   "+
									         "        SUM(BALANCE_TO_BE_RECEIVED)  "+
									         "        FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
									           "      WHERE     ACTIVE_STATUS='Y'  "+
									            "     AND  A.FINANCE_NO =  '"+finance_no+"'  "+
									             "    AND INVOICE_TYPE = 'VISIT'   "+
									        "   ),0) VISIT_CHARGES,  "+
									        "   NVL((   "+
									          " SELECT   "+
									            "     SUM(BALANCE_TO_BE_RECEIVED)  "+
									            "     FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
									             "    WHERE     ACTIVE_STATUS='Y'  "+
									             "    AND  A.FINANCE_NO =  '"+finance_no+"'  "+
									            "     AND INVOICE_TYPE = 'CEASEINGC'   "+
									       "    ),0) CEASEINGC,  "+
									        "   "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS('"+finance_no+"',"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+finance_no+"')),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'m_username+') EXCESS_AMNT,  "+
									        "   NVL((SELECT  SUM(BALANCE_TO_BE_RECEIVED)  "+
									          "          FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
									       "    "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
									         "  WHERE A.FINANCE_NO ='"+finance_no+"' AND  "+
									         "  A.FINANCE_NO = B.FINANCE_NO AND  "+
									         "  B.VALUE_DATE >SYSDATE  AND  "+
									         "  B.ACTIVE_STATUS='Y' AND  "+
									        "   B.INVOICE_TYPE = 'INV_GENER'),0) NEXT_DUE,  "+
									        "    NVL((SELECT SUM(B.BAL_TOBE_RECEIVE)  "+
									          "        FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B   "+
									          "        WHERE  A.REC_NO=B.REC_NO     "+
									           "       AND    A.CLIENT_CODE=B.CLIENT_CODE     "+
									           "       AND    B.FINANCE_NO = '"+finance_no+"'   "+
									           "       AND    B.BAL_TOBE_RECEIVE<>0   "+
									           "       AND    NVL ( A.INSURANCE,0 ) > 0   "+
									           "       AND    STATUS NOT IN ('CAD','RET','CANCLE','C') ),0) INS_EXCESS,  "+
									         "   A.CLIENT_CODE  CLIENT_CODE   "+
									         "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
									          "   WHERE A.FINANCE_NO = '"+finance_no+"'  "); 
				
				boolean more2 = rs.next();
				if(more2){
				//	tot_arrears = rs.getDouble(10) + rs.getDouble(11) + rs.getDouble(12) + rs.getDouble(13);
				}
				
				
					
				rs = stmt.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
		      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' AND "+
					" B.INVOICE_TYPE <> 'INSURANCE' AND "+
                    " B.REMARKS <> 'CHARGES - INSURANCE' "+
					"");
			
			if(rs.next()){
			 m_other_amt_future = rs.getDouble(1);			
			}
			
			
			rs = stmt.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE >SYSDATE  AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER' ");
			
			if(rs.next()){
			 m_rental_amt_future = rs.getDouble(1);			
			}
				 rs = stmt.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED)"+
				// out.println(" SELECT  SUM(BALANCE_TO_BE_RECEIVED)"+		
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE <=SYSDATE  AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER' ");
			
			if(rs.next()){
			 m_rental_amt = rs.getDouble(1);	
				
			}
			
			rs = stmt.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE >SYSDATE  AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER' ");
			
			if(rs.next()){
			 m_rental_amt_future = rs.getDouble(1);			
			}
			
			
		
		 rs = stmt.executeQuery (
				
				" SELECT  SUM(ODI_BAL_AMOUNT) FROM ("+ 
				" SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT"+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					 " "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					 " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					 //" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					 " WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					 " A.FINANCE_NO = B.FINANCE_NO AND "+
					 //" C.odi_date <=SYSDATE AND "+
					 " B.ACTIVE_STATUS='Y' AND "+
					 " B.INVOICE_NO = C.INVOICE_NO  "+
				
				
				")  "+
										
				"");
			
			if(rs.next()){
			 m_odi_amt = rs.getDouble(1);			
			}
			
		rs = stmt.executeQuery (
		//out.println(	
		" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
		" WHERE  A.REC_NO=B.REC_NO   "+
		" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
		//" AND    A.CLIENT_CODE='"+m_client+"' "+
		" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
		" AND    B.BAL_TOBE_RECEIVE<>0 "+
		" AND   NVL ( A.RENTAL_OTER_INVOICE,0 ) > 0 "+
		" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
		"");
		if(rs.next()){
		m_excess_receipt_amount = rs.getDouble(1);	
		}
				
				
				
				
				
				rs.close();
				stmt.close();
				
				*/
				
			
		//=======Added by Prabash on 20-05-2014==============
				rs2 = stmt2.executeQuery (
		//" SELECT NVL("+m_schema_name+".AF_GET_TOTAL_ARREARS('"+finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"'),0) "+
		" SELECT NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR('"+finance_no+"','"+m_client_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"'),0) "+ // added by udara 16-01-2018
		" FROM   DUAL ");
		if(rs2.next()){
		m_bal_arrears = rs2.getDouble(1);			
		}
				
			//rs2.close();
			//stmt2.close();		
	    //===================================================		
		
		
		// added by udara 16-01-2018
		rs2 = stmt2.executeQuery (
		" SELECT NVL("+m_schema_name+".AF_CO_GET_ARREAS_PERIOD_N('"+m_application_no+"','"+m_bal_arrears+"'),0) "+ // added by udara 16-01-2018
		" FROM   DUAL ");
		if(rs2.next()){
		 arreas_period = rs2.getString(1);			
		}
				
		rs2.close();
		stmt2.close();	
		// end by udara 16-01-2018

			
				
				
			m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt) - m_excess_receipt_amount; //mm_insurance_arrears
			m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future); //mm_insurance_future	
			
			tot_arrears=m_total_amt+m_total_amt_future;
			
				
				
			//	String temp1 = tot_arrears+""; //comment by Prabash on 20-05-2014
				String temp1 = nf.format(m_bal_arrears)+"";
				String[] parts = temp1.split("\\.");
				part1 = parts[0]; // 004
				part2 = parts[1]; 
				
				rs.close();
				stmt.close();
				
			
					
				
				
				//=====================================================================
				
				out.println("<html><head>"); 
				out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>First Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");
				
				out.println("function get_annexure(m_application_no){");
				

				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				//added by nuwan de silva on 05-09-07
				
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				
				
				
				out.println("function save_data(){");
				//out.println("get_annexure('"+m_application_no+"')");
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
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
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	
				//out.println("<br><br><br><br><br>");
				//out.println("<br><br><br><br><br><br><br><br>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
				/*
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				*/
				//================================================================================
				
				
				//================================================================================
				
				out.println("<br><br>");

				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 	
				out.println("<td width='95%' colspan='2' class='rep-body1' >");
			      out.println("<font size=3>");
				
				
				
		        out.println("&#3503;&#3538;&#3505;&#3514; :  " +m_Letter_date+""); // out.println("Èkh # / &#3512;&#3524;&#3501;&#3530;&#3512;&#3538;&#3514;&#3499;&#3538;,");
	            out.println("</td>"); 
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 
				 
				out.println("<td width='95%' colspan='2' class='rep-body1' >");
				out.println("<font size=3>");
		        out.println("<u>&#3517;&#3538;&#3514;&#3535;&#3508;&#3503;&#3538;&#3458;&#3488;&#3538; &#3501;&#3536;&#3508;&#3537;&#3517;&#3545;&#3505;&#3538;</u>");
				// ADDRESS
				
				out.println("<br>");
				out.println("<br>");
				
				out.println(""+m_full_name+"");
				out.println("<br>");	
				out.println(""+m_add1+" ");	
				out.println(""+m_add2+" ");
				out.println("<br>");	
				out.println(""+m_city_name+"");
			//	out.println(""+m_bal_arrears+"");

				out.println("<br>");	
				out.println("<br>");
				
		        out.println("&#3512;&#3524;&#3501;&#3530;&#3512;&#3514;&#3535;&#3499;&#3545;&#3505;&#3538; / &#3512;&#3524;&#3501;&#3530;&#3512;&#3538;&#3514;&#3499;&#3538;, "); 
	        
				out.println("<br>");
				out.println("<br>");
				
			
		        out.println("<u><center><font size=4>&#3482;&#3540;&#3517;&#3539; &#3523;&#3538;&#3505;&#3530;&#3505;&#3482;&#3530;&#3482;&#3515;&#3514; &#3461;&#3520;&#3523;&#3505;&#3530; &#3482;&#3538;&#3515;&#3539;&#3512;&#3546; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514;</font ></center></u>"); 
				out.println("<br>");
				out.println("<br>");
				
				out.println("<u><center><font size=4>&#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512;&#3530; &#3461;&#3458;&#3482;&#3514;	 :	"+finance_no+"		&#3520;&#3535;&#3524;&#3505; &#3461;&#3458;&#3482;&#3514;   : "+m_vehicle_num+" </center></font ></u>"); 
				out.println("<br>");
				out.println("<br>");
				
				out.println("&#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512; &#3512;&#3501; &#3512;&#3535;&#3523; "+arreas_period+" &#3482; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517;&#3482;&#3530; &#3484;&#3545;&#3520;&#3538;&#3512;&#3495; &#3463;&#3501;&#3538; &#3510;&#3520; &#3482;&#3515;&#3540;&#3499;&#3535;&#3520;&#3545;&#3505;&#3530; &#3523;&#3536;&#3517;&#3482;&#3515; &#3523;&#3538;&#3495;&#3538;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;. &#3473;&#3482;&#3539; &#3473;&#3482;&#3501;&#3540;&#3520; &#3463;&#3501;&#3538; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517; &#3520;&#3505; &#3515;&#3540;&#3508;&#3538;&#3514;&#3517;&#3530; "+part1+" &#3521;&#3501; "+part2+" 20..................... &#3512;&#3523; .......................... &#3520;&#3536;&#3505;&#3538;&#3503;&#3538;&#3505;&#3495; &#3524;&#3549; &#3466;&#3495; &#3508;&zwj;&#3530;&zwj;&#3515;&#3502;&#3512;&#3514;&#3545;&#3505;&#3530; &#3484;&#3545;&#3520;&#3535; &#3505;&#3538;&#3512; &#3482;&#3515;&#3505; &#3517;&#3545;&#3523; &#3476;&#3510; &#3520;&#3545;&#3501; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514; &#3482;&#3515;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;."); 
			//	out.println("&#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512; &#3512;&#3501; &#3512;&#3535;&#3523; "+arreas_period+" &#3482; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517;&#3482;&#3530; &#3484;&#3545;&#3520;&#3538;&#3512;&#3495; &#3463;&#3501;&#3538; &#3510;&#3520; &#3482;&#3515;&#3540;&#3499;&#3535;&#3520;&#3545;&#3505;&#3530; &#3523;&#3536;&#3517;&#3482;&#3515; &#3523;&#3538;&#3495;&#3538;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;. &#3473;&#3482;&#3539; &#3473;&#3482;&#3501;&#3540;&#3520; &#3463;&#3501;&#3538; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517; &#3520;&#3505; &#3515;&#3540;&#3508;&#3538;&#3514;&#3517;&#3530; "+nf.format(m_bal_arrears)+"  20..................... &#3512;&#3523; .......................... &#3520;&#3536;&#3505;&#3538;&#3503;&#3538;&#3505;&#3495; &#3524;&#3549; &#3466;&#3495; &#3508;&zwj;&#3530;&zwj;&#3515;&#3502;&#3512;&#3514;&#3545;&#3505;&#3530; &#3484;&#3545;&#3520;&#3535; &#3505;&#3538;&#3512; &#3482;&#3515;&#3505; &#3517;&#3545;&#3523; &#3476;&#3510; &#3520;&#3545;&#3501; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514; &#3482;&#3515;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;."); 
				out.println("<br>");
				out.println("<br>");
				
				out.println("&#3512;&#3545;&#3512; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514;&#3495; &#3476;&#3510; &#3520;&#3538;&#3523;&#3538;&#3505;&#3530; &#3508;&#3538;&#3514;&#3520;&#3515;&#3482;&#3530; &#3484;&#3536;&#3505;&#3539;&#3512;&#3495; &#3461;&#3508;&#3548;&#3524;&#3548;&#3523;&#3501;&#3530; &#3520;&#3540;&#3520;&#3524;&#3548;&#3501;&#3530; 20.............................. &#3512;&#3523; ........ &#3520;&#3536;&#3505;&#3538; &#3503;&#3538;&#3505; &#3523;&#3538;&#3495; &#3482;&zwj;&#3530;&zwj;&#3515;&#3538;&#3514;&#3535;&#3501;&#3530;&#3512;&#3482; &#3520;&#3505; &#3508;&#3515;&#3538;&#3503;&#3538; &#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512;&#3546; &#3508;&zwj;&#3530;&zwj;&#3515;&#3482;&#3535;&#3515; &#3482;&#3540;&#3517;&#3539; &#3523;&#3538;&#3505;&#3530;&#3505;&#3482;&#3530;&#3482;&#3515;&#3514; &#3461;&#3520;&#3523;&#3505;&#3530; &#3482;&#3515;&#3505;&#3540; &#3517;&#3510;&#3505; &#3510;&#3520;&#3503; &#3512;&#3545;&#3514;&#3538;&#3505;&#3530; &#3476;&#3510; &#3520;&#3545;&#3501; &#3520;&#3536;&#3497;&#3538; &#3503;&#3540;&#3515;&#3495;&#3501;&#3530; &#3461;&#3508;&#3538; &#3503;&#3505;&#3530;&#3520;&#3535; &#3523;&#3538;&#3495;&#3538;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;."); 
				out.println("<br>");
				out.println("<br>");
			
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				
				out.println("&#3523;&#3530;&#3501;&#3540;&#3501;&#3538;&#3514;&#3538;, <br><br>"+
							"&#3512;&#3545;&#3514;&#3495; &#3520;&#3538;&#3521;&#3530;&#3520;&#3535;&#3523;&#3539; &#3520;&#3540; <br><br>"+
							"&#3517;&#3482;&#3530;&#3503;&#3545;&#3515;&#3499; &#3465;&#3505;&#3530;&#3520;&#3545;&#3523;&#3530;&#3512;&#3505;&#3530;&#3495;&#3530; &#3517;&#3538;&#3512;&#3538;&#3495;&#3486;&#3530; <br><br><br><br>"+
							"&#3510;&#3517;&#3514;&#3517;&#3501;&#3530; &#3505;&#3538;&#3517;&#3504;&#3535;&#3515;&#3538;. <br><br>"+
							"&#3508;&#3538;&#3495;&#3508;&#3501;&#3530;"); 
			
			
				out.println("<br>");
				out.println("<br></font>");
				/*
				out.println("<font size=3>");
				out.println("&#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512; &#3512;&#3501; &#3512;&#3535;&#3523; ****** &#3482; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517;&#3482;&#3530; &#3484;&#3545;&#3520;&#3538;&#3512;&#3495; &#3463;&#3501;&#3538; &#3510;&#3520; &#3482;&#3515;&#3540;&#3499;&#3535;&#3520;&#3545;&#3505;&#3530; &#3523;&#3536;&#3517;&#3482;&#3515; &#3523;&#3538;&#3495;&#3538;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;. &#3473;&#3482;&#3539; &#3473;&#3482;&#3501;&#3540;&#3520; &#3463;&#3501;&#3538; &#3524;&#3538;&#3487; &#3512;&#3540;&#3503;&#3517; &#3520;&#3505; &#3515;&#3540;&#3508;&#3538;&#3514;&#3517;&#3530;************ &#3521;&#3501; ****** 20******&#3512;&#3523; ******&#3520;&#3536;&#3505;&#3538;&#3503;&#3538;&#3505;&#3495; &#3524;&#3549; &#3466;&#3495; &#3508;&zwj;&#3530;&zwj;&#3515;&#3502;&#3512;&#3514;&#3545;&#3505;&#3530; &#3484;&#3545;&#3520;&#3535; &#3505;&#3538;&#3512; &#3482;&#3515;&#3505; &#3517;&#3545;&#3523; &#3476;&#3510; &#3520;&#3545;&#3501; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514; &#3482;&#3515;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;. "+
							"&#3512;&#3545;&#3512; &#3505;&#3538;&#3520;&#3546;&#3503;&#3505;&#3514;&#3495; &#3476;&#3510; &#3520;&#3538;&#3523;&#3538;&#3505;&#3530; &#3508;&#3538;&#3514;&#3520;&#3515;&#3482;&#3530; &#3484;&#3536;&#3505;&#3539;&#3512;&#3495; &#3461;&#3508;&#3548;&#3524;&#3548;&#3523;&#3501;&#3530; &#3520;&#3540;&#3520;&#3524;&#3548;&#3501;&#3530; 20 ******&#3512;&#3523; *** &#3520;&#3536;&#3505;&#3538; &#3503;&#3538;&#3505; &#3523;&#3538;&#3495; &#3482;&zwj;&#3530;&zwj;&#3515;&#3538;&#3514;&#3535;&#3501;&#3530;&#3512;&#3482; &#3520;&#3505; &#3508;&#3515;&#3538;&#3503;&#3538; &#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512;&#3546; &#3508;&zwj;&#3530;&zwj;&#3515;&#3482;&#3535;&#3515; &#3482;&#3540;&#3517;&#3539; &#3523;&#3538;&#3505;&#3530;&#3505;&#3482;&#3530;&#3482;&#3515;&#3514; &#3461;&#3520;&#3523;&#3505;&#3530; &#3482;&#3515;&#3505;&#3540; &#3517;&#3510;&#3505; &#3510;&#3520;&#3503; &#3512;&#3545;&#3514;&#3538;&#3505;&#3530; &#3476;&#3510; &#3520;&#3545;&#3501; &#3520;&#3536;&#3497;&#3538; &#3503;&#3540;&#3515;&#3495;&#3501;&#3530; &#3461;&#3508;&#3538; &#3503;&#3505;&#3530;&#3520;&#3535; &#3523;&#3538;&#3495;&#3538;&#3505;&#3530;&#3505;&#3545;&#3512;&#3540;.");			
				out.println("<font>");
				*/
				
				stmt = conn.createStatement ();
			
				rs = stmt.executeQuery (" SELECT "+ 
									   " INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(A.GUARANTOR_CODE)), "+ // 1
									   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE), "+ // 2
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(A.GUARANTOR_CODE),'-'), "+  // 3 
										" ROWNUM "+
									       " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
									       " WHERE A.APPLICATION_NO = '"+m_application_no+"'  "+
									      // " ORDER BY "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE) ");
										   " ORDER BY ROWNUM ");
				
				int gua_count = 0;
				while(rs.next()){
					gua_count++;
					out.println("<font size=3>");
					out.println("<table border='0' width='90%' class='table'>"); 	
					out.println("<tr>");
					out.println("<td width='2%' class='rep-body1' ><b></b></td>");
					//out.println("<td width='5%' class='rep-body1' ><font size=3>"+gua_count+")</td>");
					if(rs.getString(1) == null){
						
						//out.println("    <td width='*%' class='rep-body1' ><font size=3>Guarantor   - "+rs.getString(2)+" ,"+rs.getString(3)+" </td></tr>"); // added by udara on 02-01-2012
						out.println(""+rs.getString(4)+". "+rs.getString(2)+" ,"+rs.getString(3)+""); 
					}else{
						//out.println("    <td width='*%' class='rep-body1' ><font size=3>Guarantor   - "+rs.getString(1)+" "+rs.getString(2)+"</td></tr>"); // commented by udara on 02-01-2012
						//out.println("    <td width='*%' class='rep-body1' ><font size=3>Guarantor   - "+rs.getString(1)+" "+rs.getString(2)+" ,"+rs.getString(3)+"</td></tr>"); // added by udara on 02-01-2012
						out.println(""+rs.getString(4)+"."+rs.getString(1)+" "+rs.getString(2)+" ,"+rs.getString(3)+"");
					}
					out.println("</table>");
					out.println("</font>");
					
				}
				rs.close();
				stmt.close();
				
				
				
				out.println("</table>");
				
				
				
				
				

				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}