//DEVELOPED BY : SANDUN 
//DATE         : 11 NOV 2008
// OFSCL BALANCE CONFIRMATION LETTER

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Bal_Confirm_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
  java.text.NumberFormat nf;
	
  public ResultSet rs,rs1,rs2;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		  String m_username=m_sn_methods.username;
			
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
			
		 String m_chksql = req.getParameter("chksql");
		 stmt = conn.createStatement ();
		 stmt1 = conn.createStatement ();
		 stmt2 = conn.createStatement ();
		 			
  	 if(m_chksql.trim().equals("main_page")){			
			
			String m_date           = req.getParameter("as_at_date");
			String m_client_code	  = req.getParameter("client_code");	
			String m_finance_no	    = req.getParameter("finance_no");	
			String m_add_1          = req.getParameter("add_1");	
			String m_add_2          = req.getParameter("add_2");	
			String m_add_3          = req.getParameter("add_3");	
			String m_client         = req.getParameter("client_name");	
		 
			String m_address = m_add_1+","+m_add_2+","+m_add_3;
			
			String m_client_full_name   = "";
			String m_client_add1        = "";
			String m_client_add2        = "";
			String m_client_city        = "";
			String m_client_name_q      = "";
			String m_client_name        = "";
			String m_client_add1_q      = "";
			String m_client_add2_q      = "";
			String m_client_city_q      = "";
			String m_client_full_name_q = "";
								
			
			int j=1;
			
			double m_tot_recevable  = 0.0;
			double m_tot_invoiced   = 0.0;
			double m_tot_arrears    = 0.0;
			double m_tot_balance    = 0.0;			
			double m_tot_odi        = 0.0;
			double m_balance        = 0.0;
			double m_tot_other_chrg = 0.0;
			
			// rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD Month YYYY') FROM DUAL ");
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-Mon-YY') FROM DUAL ");
			
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
			rs.close();
			stmt.close();			
			stmt = conn.createStatement ();
			
			rs = stmt.executeQuery(" SELECT "+
				" NVL(UPPER(COMPANY_NAME),' '), "+
				" NVL(UPPER(ADDRESS1),' '), "+
				" NVL(UPPER(ADDRESS2),' '), "+
				" NVL(UPPER(CITY),' '), "+
				" NVL(TEL_NO,' '), "+
				" NVL(FAX_NO,' '),  "+
				" NVL(VAT_RATE,0) "+
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
				
				
				
				rs1 = stmt1.executeQuery(" SELECT UPPER(A.TITLE) || ' ' || UPPER(A.FULL_NAME), "+
													      " UPPER(NVL(A.ADDRESS1,'-')), "+
													      " UPPER(NVL(A.ADDRESS2,'-')), "+
													      " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+
															" UPPER(A.FULL_NAME) "+
													      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A"+
																" WHERE A.CLIENT_CODE = '"+m_client_code+"'");

				if(rs1.next()){
				m_client_full_name   = rs1.getString(1);
				m_client_add1        = rs1.getString(2);
				m_client_add2        = rs1.getString(3);
				m_client_city        = rs1.getString(4);
				m_client_name_q      = rs1.getString(1);
				m_client_add1_q      = rs1.getString(2);
				m_client_add2_q      = rs1.getString(3);
				m_client_city_q      = rs1.getString(4);
				m_client_full_name_q   = rs1.getString(5);
				}		
		//---------------------Mod By Sandun on 07-11-2009--------------------------------------
		
		/*
		if(m_finance_no.equals("")){
	    rs2 = stmt2.executeQuery(" SELECT A.CLIENT_CODE, "+//1
														   " A.FINANCE_NO, "+//2
														   " TO_CHAR(A.ACTIVATED_DATE,'DD-MON-YYYY'), "+ //3
														   //" "+m_schema_name+".AF_CO_GET_ARREARS_BC(A.FINANCE_NO,'"+m_date+"'), "+ //4
															 " "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(A.FINANCE_NO,A.CLIENT_CODE,'"+m_date+"','') ,"+
															 " "+m_schema_name+".AF_CO_GET_INV_TOT_BC(A.APPLICATION_NO,'"+m_date+"'), "+//5
															 " "+m_schema_name+".AF_CO_GET_OTHER_CHARG_BC(A.FINANCE_NO,'"+m_date+"'),"+//6
															 " "+m_schema_name+".AF_CO_GET_TOT_RECEVABLE_BC(A.APPLICATION_NO,'"+m_date+"'), "+	//7
															 //" "+m_schema_name+".AF_CO_GET_ODI_ARR_2(A.FINANCE_NO,'"+m_date+"'), "+//8
															 " "+m_schema_name+".AF_CO_GET_ODI_BAL(A.FINANCE_NO,'"+m_date+"'), "+//8 -- Mod By Sandun on 09-06-2009
															 " "+m_schema_name+".AF_CO_GET_SETTLE_AMT_AGR(A.FINANCE_NO,'"+m_date+"') "+ //9
														   " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
														   " WHERE A.APPLICATION_STATUS ='ACTIVATED' "+
															 " AND A.ACTIVATED_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
														   " AND A.CLIENT_CODE = '"+m_client_code+"' "+
															 " GROUP BY A.CLIENT_CODE,A.FINANCE_NO,A.ACTIVATED_DATE,A.APPLICATION_NO "+
															 " ORDER BY A.ACTIVATED_DATE ");
		}
		else{
		 rs2 = stmt2.executeQuery(" SELECT A.CLIENT_CODE, "+//1
														   " A.FINANCE_NO, "+//2
														   " TO_CHAR(A.ACTIVATED_DATE,'DD-MON-YYYY'), "+ //3
														  // " "+m_schema_name+".AF_CO_GET_ARREARS_BC(A.FINANCE_NO,'"+m_date+"'), "+ //4
															 " "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(A.FINANCE_NO,A.CLIENT_CODE,'"+m_date+"','') ,"+
															 " "+m_schema_name+".AF_CO_GET_INV_TOT_BC(A.APPLICATION_NO,'"+m_date+"'), "+//5
															 " "+m_schema_name+".AF_CO_GET_OTHER_CHARG_BC(A.FINANCE_NO,'"+m_date+"'),"+//6
															 " "+m_schema_name+".AF_CO_GET_TOT_RECEVABLE_BC(A.APPLICATION_NO,'"+m_date+"'), "+	//7
															 //" "+m_schema_name+".AF_CO_GET_ODI_ARR_2(A.FINANCE_NO,'"+m_date+"'), "+//8
																" "+m_schema_name+".AF_CO_GET_ODI_BAL(A.FINANCE_NO,'"+m_date+"'), "+//8 -- Mod By Sandun on 09-06-2009
															 " "+m_schema_name+".AF_CO_GET_SETTLE_AMT_AGR(A.FINANCE_NO,'"+m_date+"') "+ //9
														   " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
														   " WHERE A.APPLICATION_STATUS ='ACTIVATED' "+
															 " AND A.ACTIVATED_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
														   " AND A.CLIENT_CODE = '"+m_client_code+"' "+
															 " AND A.FINANCE_NO  = '"+m_finance_no+"'  "+
															 " GROUP BY A.CLIENT_CODE,A.FINANCE_NO,A.ACTIVATED_DATE,A.APPLICATION_NO "+
															 " ORDER BY A.ACTIVATED_DATE ");
		}
		*/
		
		
		rs2 = stmt2.executeQuery(" SELECT  "+
														 " A.CLIENT_CODE,  "+
														 " A.FINANCE_NO,  "+
														 " TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+
														 " A.ARREARS_AMT, "+
														 " A.INVOICED_AMT, "+
														 " A.OTHER_CHARGES, "+
														 " A.RECEVABLE_AMT,  "+
														 " A.ODI "+
														 " FROM "+m_schema_name+".AF_RE_BAL_CONFIRM_LETTER A "+
														 " WHERE A.ENT_USER='"+m_username+"' "+
														 " AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
														 "	    OR A.TERMI_DATE IS NULL ) ");
		
		
		
		
		//---------------------------------------------------------------------------------------------
		
		
		
		 if(!m_client.toUpperCase().trim().equals(m_client_full_name_q.toUpperCase().trim())){
			m_client_full_name = m_client;
			}
		
		
			if(!m_add_1.equals("") || !m_add_2.equals("") || !m_add_3.equals("")){
			m_client_add1      = m_add_1;
			m_client_add2      = m_add_2;
			m_client_city      = m_add_3;			
			}
			
			if(!m_client_name.equals("")){
			m_client_full_name = m_client_name;
			}
			
			
			
			out.println("<html><head>"); 
			out.println("<title>Balance Confirmation Letter</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			   		
			out.println("<script>");			
									
			out.println("function save_data(){");		
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");			
		  out.println("}");
		
		  out.println("function add_button(){");					
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
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
			out.println("<tr><td width=\"100%\" class='rep-body'><b></b></td></tr>");
		  out.println("</table>");
			out.println("</font></p></blockquote>");			
			out.println("<blockquote style = \"margin:0px 64px;\"><font size=2><p style='text-align:justify' class='rep-body'>");	
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");

			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>"+m_Letter_date+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_full_name+"' size='300' style='width:450px;font-family:Arial; font-size:10pt;'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_add1.toUpperCase()+"' size='300' style='width:450px;font-family:Arial; font-size:10pt;'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_add2.toUpperCase()+"' size='300' style='width:450px;font-family:Arial; font-size:10pt;'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_city+"' size='300' style='width:450px;font-family:Arial; font-size:10pt;'></td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>Dear Sir,</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'><u><b>Confirmation Of Balance As At "+m_date+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>As requested by "+m_client_full_name+" we hereby certified that the below stated "+
			            "balance is outstanding as at "+m_date+".</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			// commented below by udara on 24-03-2011
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' >Name of the client : <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_full_name_q+"' size='300' style='width:300px;font-family:Arial; font-size:10pt;'></td></tr>");
			out.println("</table>");
			
			
			out.println("<br>");
			
			
			
			out.println("<table border='1' width='90%' class='table' cellspacing=0 cellpadding=0 bordercolor='black'>"); 		
			out.println("<tr><td width='2%' class='rep-body'  align='center'>Sl No.</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Agreement No.</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Agreement Date</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Total Receivable (Rs.)</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Invoiced (Rs.)</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Arrears (Rs.)</td>");
			//out.println("<td width='11%' class='rep-body1' align='center'>Other Charges (Rs.)</td>"); // Commented by Udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>Balance (Rs.)</td>");           // Commented by Udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>ODI (Rs.)</td></tr>");
			
			boolean more2 = rs2.next();			
			while(more2){				
			//m_balance = rs2.getDouble(8)+rs2.getDouble(6)+rs2.getDouble(4)+(rs2.getDouble(7)-rs2.getDouble(5));			
			//m_balance = rs2.getDouble(6)+rs2.getDouble(8)+rs2.getDouble(4)+(rs2.getDouble(7)-rs2.getDouble(5));	//Modified by Sandun on 19-06-2009		---Ignore ODI Balance for final balance
			m_balance = rs2.getDouble(7) - rs2.getDouble(5) + rs2.getDouble(4);	

			out.println("<tr><td width='2%' class='rep-body'  align='center'>"+j+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>"+rs2.getString(2)+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'>"+rs2.getString(3)+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(rs2.getDouble(7))+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(rs2.getDouble(5))+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(rs2.getDouble(4))+"</td>");
			//out.println("<td width='11%' class='rep-body1' align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); // commented by udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_balance)+"</td>"); // commented by udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(rs2.getDouble(8))+"</td></tr>");
			
			m_tot_recevable  = m_tot_recevable+rs2.getDouble(7);
			m_tot_odi        = m_tot_odi+rs2.getDouble(8);			
			m_tot_other_chrg = m_tot_other_chrg+rs2.getDouble(6);
			m_tot_invoiced   = m_tot_invoiced+rs2.getDouble(5);
			m_tot_arrears    = m_tot_arrears+rs2.getDouble(4);
			m_tot_balance    = m_tot_balance+m_balance;
			
			more2 = rs2.next();
			j++;
			}
			
			out.println("<td width='24%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='center'colspan=3 >Total</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_tot_recevable)+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_tot_invoiced)+"</td>");
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_tot_arrears)+"</td>");
			//out.println("<td width='11%' class='rep-body' align='right'>"+nf.format(m_tot_other_chrg)+"</td>"); // commented by udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_tot_balance)+"</td>"); // commented by udara on 22-03-2011
			out.println("<td width='11%' class='rep-body' style='font-family:Arial; font-size:10pt;' align='right'>"+nf.format(m_tot_odi)+"</td></tr>");
			
			out.println("</table>");

			
			
			//out.println("<br>");
			//out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			/*
			// Added by Udara on 25-03-2011
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ># The above balance does not include overdue interest</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			*/
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' >Yours Truly,</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' >"+m_orient_name+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");			
			
			if(!m_client.toUpperCase().trim().equals(m_client_full_name_q.toUpperCase().trim())){
			out.println("<table border='0' width='80%' class='table'>"); 	
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;' >.............................................</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>Authorised Signatory</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>Cc.</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>"+m_client_name_q+",<br>"+m_client_add1_q+",<br>"+m_client_add2_q+",<br>"+m_client_city_q+".</td></tr>");
			out.println("</table>");
		  }
		  else{			
			out.println("<table border='0' width='80%' class='table'>"); 	
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>.............................................</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>Authorised Signatory</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Arial; font-size:10pt;'>Cc.</td></tr>");
			out.println("</table>");
			}
			
			out.println("</font></p></blockquote>");		
			
			 
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
