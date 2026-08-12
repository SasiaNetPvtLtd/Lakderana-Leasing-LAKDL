
//DEVELOPED BY ASHINI FOR FACTORING ON 03-03-2008

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

   

public class LAKDL_FA_MISF_Return_cheque_sent_letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt2;
	java.text.NumberFormat nf;
	
   public ResultSet rs;
   public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr;
	
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
		
			nf = java.text.NumberFormat.getInstance(Locale.US);
		   nf.setMinimumFractionDigits(2);
		   nf.setMaximumFractionDigits(2);
						
			String m_chksql=req.getParameter("chksql");
			
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			
			if (m_chksql.trim().equals("main_page")) {
			
			String m_client_no=req.getParameter("client");		
			String m_facility_no=req.getParameter("finance_no");
			String m_ref_no=req.getParameter("ref_no");
			String m_date="";
			
			if (req.getParameter("date")!=null){
			m_date=req.getParameter("date");
			}
			
			
         String m_debtor_code ="";
			
				out.println("<html><head>"); 
				out.println("<title>Return Cheques - Sent Letter</title>");
				
				out.println("</head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");				
				
				out.println("<SCRIPT language=\"JavaScript\">");

				out.println("function print_data(){");
				out.println(" 		m_table.innerHTML    = \"\"; ");
				out.println(" 		window.print();");
				out.println("}");
				
				out.println("</script>");
				
				
				out.println("<body leftmargin='0' topmargin='0' class=body>");	
				out.println("<body bgcolor='white'><br>");				
				out.println("<form name='Form1'>");	
				
				// Get Sysdate
				
				String m_Letter_date="";
				rs1 = stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");											
				if(rs1.next()){
				m_Letter_date=rs1.getString(1);
				}
				
				
  		 
			   // Get Client Details
				
				rs2 = stmt2.executeQuery (" SELECT "+
				" CLIENT_CODE, "+
				" NVL(FULL_NAME,'-') "+
				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE CLIENT_CODE='"+m_client_no+"' ");
				String m_client_name="";
				String m_cont_person="";
				String m_client_add1="";
				String m_client_add2="";
				String m_client_add3="";
				
				String m_ref_type_2 ="";
				
				if(rs2.next()){
				m_client_name = rs2.getString(2);
				}
				
				// Get Reference Number
				
				
				// End Get Ref No
				/*
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			   out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	      out.println("m_writedata+'</table>';");
				*/
					
				out.println("<table border='0' width='100%' class='table'>"); 		
	         out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'><div id= 'm_table'> <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></div> </td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</table>");	
					
				
				
				out.println("<p style='text-align:left'>");										
				out.println("<br><br>");
				/*
				out.println("<table border='0' width='100%' class='table'>"); 		
	         out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'>Co.Reg.No. PB 75</td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</TABLE>");
				
				out.println("<br><br><br><br>");
				
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Date      :- "+m_Letter_date+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				*/
				/*
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Attention :- "+m_cont_person+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				*/
				out.println("</TABLE><br>");
				
				
				
				out.println("<table border='0' width='100%' class='table'>"); 	
			
				// Get Client Address
				
				rs1 = stmt1.executeQuery (	" SELECT "+
					  " NVL(A.CLIENT_CODE,'-'), "+      //1
					  " NVL(UPPER(A.FULL_NAME),' '), "+ //2
					  " NVL(UPPER(A.REGISTERED_ADDRESS1),' '), "+ //3
					  " NVL(UPPER(A.REGISTERED_ADDRESS2),' '), "+ //4
					  " UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ')), "+ //5
					  " NVL(UPPER(B.CONTACT_PERSON),' '),"+ //6
					  " NVL(UPPER(B.DESIGNATION_PAYMENT),' '), "+ //7
					  " A.CLIENT_TYPE, "+ //8
					  " NVL(A.REGISTERED_REFERENCE,'-') "+ // 9
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
					  " WHERE B.CLIENT_CODE=A.CLIENT_CODE "+
					  " AND B.FACILITY_NO='"+m_facility_no+"' "+
					  " AND A.CLIENT_CODE='"+m_client_no+"' "+
					  " ");
				
				while(rs1.next()){

					m_cont_person = rs1.getString(6);
					m_client_add1 = rs1.getString(3);
					m_client_add2 = rs1.getString(4);
					m_client_add3 = rs1.getString(5);
					m_ref_type_2  = rs1.getString(9);
					
				}
				
				out.println("<table border='0' width='100%' class='table'>"); 		
	         out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'>"+m_ref_no+"</td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</TABLE>");
				
				out.println("<br><br><br><br>");
				
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Date      :- "+m_Letter_date+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_cont_person+"</td></tr>"); // Added by Udara SOmathilake on 18-10-2010
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_name+"</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add1+"</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add2+"</td></tr>");
		      out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add3+"</td></tr>");
					
				out.println("<tr><td width='*%' class='factoring-letter-body' height=20px > </td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_facility_no+"</td></tr>"); // Facility No Added by Udara Somathilake on 13-10-2010
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_no+"</td></tr>"); // Client Code
				
				out.println("</TABLE>");
				out.println("</p>");
		
				
				out.println("<p style='text-align:justify'>");				
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:left;}'><br><B><U>Handing over of Dishonored Cheques</B></U></td></tr>");
				out.println("</TABLE><br>")	;		
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:left;}'><br>Dear Sir,</td></tr>");
				out.println("</TABLE>");				
				out.println("</p>");

				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>");
			   out.println(" Following cheque/(s) that you have submitted to \"Orient Factor\" to settle Invoices / outstanding, was / (were) dishonored by the bank. ");
				out.println("</p>");
				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>");
			   out.println(" The dishonored cheque /(s) is / (are) attached herewith and please accept the receipt of same and return the duplicate to us.  ");
				out.println("</p>");
				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>");
				out.println(" Details are as follows.   ");
				out.println("</p>");	
				
				out.println("<p style='text-align:justify' class='factoring-letter-body' >");				
				
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Chq No</u></b></td>");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Bank - Branch</u></b></td> ");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Chq Amt</u></b></td> ");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Chq Date</u></b></td> ");
				out.println("		<td width='25%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Re-Marks</u></b></td> ");
				out.println("</tr>");
				out.println("</TABLE>");					

				
				rs2 = stmt2.executeQuery(
				"SELECT  "+                        
				" NVL(A.CHEQUE_NO,'-'), "+         //1
				" NVL(A.PAYER_BRANCH_CODE,'-'), "+ //2
				" NVL(A.REC_AMOUNT,0), "+          //3
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),  "+ //4
				" NVL(A.RECEIPT_COMMENTS,'-') " +  //5
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
				"      "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B, "+
				"      "+m_schema_name+".FA_OP_PRO_RETURN_CHQ_LETTER C "+
				" WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
				" A.FACILITY_NO=B.FACILITY_NO  AND "+
				" A.RECEIPT_NO=C.RECEIPT_NO AND "+
				" A.FACILITY_NO='"+m_facility_no+"' AND "+
				" A.CLIENT_CODE='"+m_client_no+"' "+
				" AND C.REF_NO='"+m_ref_no+"' "+ 
				//" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') =TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
				" ");
				
				
				/*
				rs2 = stmt2.executeQuery(" "+
							" SELECT  "+ 
								  	 " NVL(A.CHEQUE_NO,'-'), "+         // 1
					             " NVL(A.PAYER_BRANCH_CODE,'-'), "+ // 2
									 " NVL(A.CHEQUE_AMOUNT,0), "+       // 3
									 //" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+ // 4
									 " TO_CHAR(A.CHEQUE_DATE), "+       // 4
									 " NVL(A.CANCEL_COMMENT,'-') "+     // 5
							 				" FROM  "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A, "+
							      				" "+m_schema_name+".FA_OP_PRO_PRINT_POD_CHEQUES B "+
															 " WHERE A.POD_REF_NO=B.POD_REF_NO AND "+ // udara
															 " A.FACILITY_NO = B.FACILITY_NO  AND "+
															 " A.POD_REF_NO  = B.POD_REF_NO AND "+
															 " A.FACILITY_NO = '"+m_facility_no+"' AND "+
															 " A.CLIENT_CODE = '"+m_client_no+"' "+
																// --AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') =TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')
									" ");
					*/

				while(rs2.next()){
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(1)+"</td>");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(2)+"</td> ");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(3))+"</td> ");
				out.println("		<td width='15%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(4)+"</td> ");
				out.println("		<td width='25%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(5)+"</td> ");
				out.println("</tr>");	
				}
				out.println("</TABLE>");
				out.println("</p>");	
				
				out.println("<br><br>");
			
				/*
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
				out.println("Yours faithfully,<br><br><br>");	
				out.println("Executive - Operations <br>");	
				out.println("<I>Orient Factors</I>");	
				out.println("</p>");	
				*/
				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");
				out.println("	<table>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				Yours faithfully,		");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				Factoring Division of		");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				Lakderana Investments Limited		");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("		<tr>");
				out.println("			<td height = 40px>");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("	</table>");	
				out.println("</p>");	
				
				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");
				out.println("	<table>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				................................		");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				Authorized Signatory		");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("		<tr>");
				out.println("			<td>");
				out.println("				Senior Executive - Factoring (Operations)	");
				out.println("			</td>");
				out.println("		</tr>");
				out.println("	</table>");	
				out.println("</p>");	
				
				
				
				out.println("</form></body></html>");
				
			}
			
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
