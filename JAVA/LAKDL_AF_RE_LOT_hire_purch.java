//SCREEN NAME:LETTER OF TERMINATION HP
//DEVELOPED BY MAHELA FOR LEASING

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

   

public class LAKDL_AF_RE_LOT_hire_purch extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_name,m_city_desc,m_client_no,m_print,m_activated_date="";

      //orient data----------			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_LAKDL_vat_no="",m_LAKDL_reg_no="";
			String m_vat_precentage="";
			
		  String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_nic_no="";


  public String m_d_code,m_d_add1,m_d_add2,m_d_name,m_d_city_desc,m_app_no,m_gurantor_data;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		           
		try { 
				
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
			String m_contact_person="-",m_contact_desig="-";
			String m_d_contact_person="-",m_d_contact_desig="-",m_not_date="-";
			double m_chq_amount=0,m_tot_arrears=0,m_other_arrears=0,m_odi_arrears=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_facility_no=req.getParameter("finance_no");
			m_client_no=req.getParameter("client_code");			
			m_print=req.getParameter("print");
			//m_debtor_code=req.getParameter("debtor_code");			
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Letter of Termination</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Termination_Letter_save?finance_no="+m_facility_no+"&client_code="+m_client_no+"&letter_type=LOT_HP&print="+m_print+"\";");  
			out.println("  window.location.href=m_url;"); 
			out.println("	 m_table.innerHTML=\"\" ");
			out.println("	 window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<br>");
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'Month DD,YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
				
											//added by nuwan de silva on 08-11-07
							rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE, "+
							" VAT_REG_NO "+
							" ,REG_NO"+
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
											m_vat_precentage=rs.getString(7);			
											m_LAKDL_vat_no=rs.getString(8);	
											m_LAKDL_reg_no=rs.getString(9);			

											}
						rs.close();
         //end orient data ---------------------------------------------


					
				/*rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'), "+//6
						" NVL(DESIGNATION_PAYMENT,'-') "+//7
  				  " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs1.next();
					if(more){
						m_d_code=rs1.getString(1);
						m_d_name=rs1.getString(2);
						m_d_add1=rs1.getString(3);
						m_d_add2=rs1.getString(4);
						m_d_city_desc=rs1.getString(5);
						m_d_contact_person = rs1.getString(6);
						m_d_contact_desig = rs1.getString(7);
					}*/
	
				 /*rs1= stmt1.executeQuery("SELECT X.FINANCE_NO,Y.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(Y.CLIENT_CODE), "+
 				  " SUM(A.BALANCE),Y.TRANSACTION_TYPE,Y.APPLICATION_NO "+
 					" FROM (SELECT INVOICE_NO, SUM(INVOICED_AMOUNT) - SUM(SETTELED_AMOUNT) BALANCE "+
 				  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS GROUP BY INVOICE_NO) A , "+
  				" "+m_schema_name+".AF_CO_PRO_INVOICE X, "+
  				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
  				" WHERE X.FINANCE_NO=Y.FINANCE_NO AND A.INVOICE_NO=X.INVOICE_NO AND "+
  			  " TO_DATE(X.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
  			  " X.ACTIVE_STATUS='Y' AND "+
  			  " A.BALANCE <> 0 "+
  			  " AND X.FINANCE_NO ='"+m_facility_no+"' "+
  				" GROUP BY X.FINANCE_NO,Y.CLIENT_CODE,Y.TRANSACTION_TYPE,Y.APPLICATION_NO ");
					*/
					
					
					//added by nuwan de silva on 08-11-07--------------------------------
					rs1= stmt1.executeQuery("SELECT A.FINANCE_NO,A.CLIENT_CODE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) ,SUM(A.BALANCE_TO_BE_RECEIVED) BALANCE, "+
					" B.TRANSACTION_TYPE,B.APPLICATION_NO,TO_CHAR(B.ACTIVATED_DATE,'DD/MM/YYYY') , "+
					//" "+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(B.APPLICATION_NO) "+
					//" NVL("+m_schema_name+".af_co_get_app_cap_outstanding1(B.APPLICATION_NO,SYSDATE),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(A.FINANCE_NO,SYSDATE),0) "+ // ADDED BY NUWAN DE SILVA
					" NVL("+m_schema_name+".AF_CO_GET_APP_LOT_BALANCE(B.APPLICATION_NO,A.FINANCE_NO),0) "+
     			" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
					" AND  TO_DATE(A.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND A.ACTIVE_STATUS='Y' "+
					" AND A.FINANCE_NO ='"+m_facility_no+"' "+
					" AND A.BALANCE_TO_BE_RECEIVED >0 "+
					" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,B.TRANSACTION_TYPE,B.APPLICATION_NO,B.ACTIVATED_DATE  ");
     

					more=rs1.next();
					if(more){
					//m_tot_arrears=rs1.getDouble(4);
					m_app_no=rs1.getString(6);
					m_client_no=rs1.getString(2);
					m_client_name = rs1.getString(3);
					m_activated_date=rs1.getString(7);
					m_tot_arrears=rs1.getDouble(8);

				  }
					
					
					/*rs1= stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE BALANCE_TO_BE_RECEIVED<>0 "+
					" AND INVOICE_TYPE<>'INV_GENER' "+
					" AND ACTIVE_STATUS<>'C' "+
					" AND FINANCE_NO='"+m_facility_no+"' ");
										
					more=rs1.next();
					if(more){
					m_other_arrears=rs1.getDouble(1);
					}
					
					rs1= stmt1.executeQuery(" SELECT NVL(SUM(ODI_BAL_AMOUNT),0)  "+
 					" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
 					" WHERE  A.INVOICE_NO=B.INVOICE_NO "+
					" AND    B.FINANCE_NO='"+m_facility_no+"' "+
 					" AND  TO_DATE(A.ODI_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
						
					more=rs1.next();
					if(more){
					m_odi_arrears=rs1.getDouble(1);
				  }
          */
				
					/*rs2 = stmt2.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }
					*/
			    rs2 = stmt2.executeQuery (" SELECT "+
  				 " TO_CHAR(ENT_DATE,'Month DD,YYYY') "+
 					 " FROM "+m_schema_name+".AF_RE_PRO_TERMINATION_LETTER "+
 					 " WHERE  FINANCE_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_no+"' AND LETTER_NAME='NOT_FL' ");
						
					more = rs2.next();
					if(more){
					 m_not_date=rs2.getString(1);
					}
					
								String Client_Data=" SELECT  "+
			" 'CLIENT', "+ //1
			" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || INITCAP(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
			" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
			" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
			" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //2
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =UPPER('"+m_client_no+"') ";
			
			rs = stmt.executeQuery(Client_Data);
			more = rs.next();		

			if(more){	
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_nic_no=rs.getString(6);
			}				

					
			out.println("<blockquote><font size=2><p style='text-align:left'>");										
			out.println("<br><br><br><br><br><br><br>");
			/*out.println("<table border='0' width='100%' class='table'>");  		
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_person+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_desig+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
	  	out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_name+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add1+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add2+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
      out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_city_desc+".</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("</TABLE>");*/
			//out.println("<br>");
			
			/*out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><br>BY REGISTERED POST</td></tr>");
			out.println("</TABLE><br>");		
			
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='70%' class='rep-body'  style='{font:10px;text-align:left;}'></td><td width='30%' class='rep-body'  style='{font:10px;text-align:left;}' ><br>"+m_Letter_date+"</td></tr>");
			out.println("</TABLE><br>");		
			
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_city_name+"</td></tr>");
			out.println("</table><br><br>");	
      */
			
		  out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><br><b><u>BY REGISTERED POST</u></b></td></tr>");
			out.println("</TABLE>");		
			
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='70%' class='rep-body'  style='{font:10px;text-align:left;}'></td><td width='30%' class='rep-body'  style='{font:10px;text-align:left;}' ></td></tr>");//"+m_LAKDL_reg_no+"
			out.println("</TABLE>");		
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td></tr>");
			out.println("</table><br>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_city_name+"</td></tr>");
			out.println("</table><br>");	


			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><br>Dear Sir/Madam,</td></tr>");
			out.println("</TABLE><br>");
			
			/*out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><b>Termination of Hire Purchase </b></td><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><b> : "+m_client_name+" </b></td></tr>");
			out.println("<tr ><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><b>Agreement No </b></td><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><b> : "+m_facility_no+" </b></td></tr>");
			out.println("<tr ><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><b><u>Description of Vehicle/Equipment </u></b></td><td width='50%' class='rep-body'  style='{font:10px;text-align:left;}'><u><b> : - </b></u></td></tr>");
			out.println("</TABLE><br>");		
			*/
			
      out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><b><u>Termination of Hire Purchase  - </u></b></td></tr>");
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><b><u>Hire Purchase Agreement No. &nbsp;&nbsp "+m_facility_no+" &nbsp;&nbsp;Dated:&nbsp; "+m_activated_date+" </b></u></tr>");
			out.println("</TABLE><br>");		

			

			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We refer to the Notice of Termination of Hire Purchase dated <b>"+m_not_date+"</b> sent to you in "+
									" accordance with the terms of Hire Purchase Agreement notwithstanding the said notice, you have failed and "+
									" neglected to remedy the default/breach/Act specified in the said notice within the time limit stipulated therein. "+
									//" Lease Purchase rentals due on the above Agreement totaling <b>Rs "+nf.format(m_tot_arrears)+"/= </b> in terms of the conditions in the Agreement. "+
									" ");
			out.println("</td></tr></table>");						
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We therefore, declare that the Hire Purchase in terms of the aforesaid Hire Purchase Agreement is "+
									" terminated with immediate effect. We further inform you that our property/vehicle hired Leased to you is no longer in "+
									" your possession with our permission and consent. Therefore, your possession of the properly/vehicle is unlawful. "+
									" ");
			out.println("</td></tr></table>");						
			String data="In the circumstances, you should either deliver the propetity to our office at 46,48, Dr. N. M. Perera Mawatha Colombo 08, "+
			            "during our normal business hours or pay at once the sum <b>Rs "+nf.format(m_tot_arrears)+"/= </b> of being the full balance due from you as at  "+m_Letter_date+" in terms of "+
									"the above Hire Purchase agreement. Should you fail to do so, we will proceed with the necessary action "+
									"to safeguard our interest without further warning.";
									
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(""+data+"");
			out.println("</td></tr></table>");
			
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" In the circumstances, you will remain fully liable under the conditions of the Hire Purchase Agreement "+
									" entered into and executed by you. ");
			out.println("</td></tr></table>");						
			out.println("<br>");						
			
			/*out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Yours faithfully,<br>");
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			//out.println("Orient Financial Services Corporation Ltd<br>");	
			out.println(""+m_orient_name+""); //added by nuwan de silva on 08-11-07
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("<br><br><br><br>");				
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" .....................................................................");
			out.println("</td></tr> ");
			out.println("</table>");						
			*/
			
			/*out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Yours faithfully,");
			out.println("</td></tr> ");
			out.println("</table><br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(""+m_orient_name+""); 
			out.println("</td></tr> ");
			out.println("</table>");						
      out.println("<br><br><br><br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" .....................................................................");
			out.println("</td></tr> ");
			out.println("</table>");	
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
    	out.println(" Authorized Signatory ");	
			out.println("</td></tr></table>");	
			out.println("<br><br><br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("<b>Assistant General Manager Recoveries</b> ");
			out.println("</td></tr></table>");	
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("<b> * A copy of this letter is being forwarded to the guarantor/s for their information and necessary action. </b>");
			out.println("</td></tr></table>");	
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("(Guarantor/s)");
			out.println("</td></tr></table>");	
			
			
			rs1 = stmt1.executeQuery (" SELECT "+
			  "  A.APPLICATION_NO, "+
			  "  A.GUARANTOR_CODE, "+
			  "  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+
			  "  UPPER(NVL(B.ADDRESS1,'-')), "+
			  "  UPPER(NVL(B.ADDRESS2,'-')), "+
			  "  UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')) "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ");
			
			while(rs1.next()){
			m_gurantor_data=m_gurantor_data+""+rs1.getString(3)+"    of    "+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+"<br>";
			//m_gurantor_data1=m_gurantor_data1+"Name<br>NIC No./BRC No./PVS No.<br>Address<br>";
			}
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(""+m_gurantor_data+"");
			out.println("</td></tr></table>");	
			
			*/
			
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>Yours faithfully</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("</table>");						
      out.println("<br><br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" .....................................................................");
			out.println("</td></tr> ");
			out.println("</table>");	
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
    	out.println(" <b>Manager Recoveries</b> ");	
			out.println("</td></tr></table>");	
			
			out.println("</td></tr></table>");	
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("<b> * A copy of this letter is being forwarded to the guarantor/s for their information and necessary action. </b>");
			out.println("</td></tr></table>");	
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("(Guarantor/s)");
			out.println("</td></tr></table>");	
			
			
			String sql_gur=" SELECT "+
			  "  A.APPLICATION_NO, "+
			  "  A.GUARANTOR_CODE, "+
			  "  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+
			  "  INITCAP(NVL(B.ADDRESS1,'-')), "+
			  "  INITCAP(NVL(B.ADDRESS2,'-')), "+
			  "  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')) "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ORDER BY A.GUAR_ID,A.GUARANTOR_CODE";
			
			Vector gur_name=new Vector();
			Vector gur_add1=new Vector();
			Vector gur_add2=new Vector();
			rs1 = stmt1.executeQuery (sql_gur);
			int count=0;
			int i=0,j=1;
			while(rs1.next()){
			gur_name.addElement(rs1.getString(3));
			gur_add1.addElement(rs1.getString(4));
			gur_add2.addElement(rs1.getString(5));
			count=count+1;
			}
			
			if(count==1){
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_name.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'></td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'></td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'></td></tr>");
			out.println("</table>");
			}
			else{
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_name.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add1.get(j).toString()+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
			out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add2.get(j).toString()+"</td></tr>");
			out.println("</table>");
			}

			
			//guarantors
			out.println("</font></p></blockquote>");		
		  out.println("</form></body></html>");
			}//End of main page
			else  {
			out.println("idle");
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
