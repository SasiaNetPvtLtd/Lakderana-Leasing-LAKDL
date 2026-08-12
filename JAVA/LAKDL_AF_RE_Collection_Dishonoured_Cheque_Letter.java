import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:16:08:07

public class LAKDL_AF_RE_Collection_Dishonoured_Cheque_Letter extends javax.servlet.http.HttpServlet {
	
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
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();			
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
			stmt2=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
					
			//String m_ack=req.getParameter("ack");
			String m_client_code=req.getParameter("client_code");
			String m_cheque_no=req.getParameter("cheque_no");
			String m_finance_no=req.getParameter("finance_no");
			String m_cheque_date=req.getParameter("cheque_date");
			String m_return_no=req.getParameter("return_no");
			
			
			String Sys_date="";
			String m_name="";
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_orient_email="";
			String m_comments="";
      String Data	="";	
			String m_chq_date ="";
			String m_bank_name  ="";
			String m_branch_name ="";
			String m_return_date ="";
			double m_return_amt =0;
	    double m_hp_amount=0;	
			double m_return_charge=0;
				
					 String		Sql_sys_date=" SELECT TO_CHAR(SYSDATE,'ddth Month YYYY') "+
																 " FROM DUAL ";
																	
					rs1=stmt1.executeQuery(Sql_sys_date);
					boolean more =rs1.next();
					
					if(more){
					Sys_date=rs1.getString(1);
					}
					
					String Sql_client_data="SELECT  "+//1
								" B.CLIENT_CODE, "+//2
								" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //3
								" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),' ')), "+//4 //B.REGISTERED_ADDRESS1
								" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),' ')), "+//5 // B.REGISTERED_ADDRESS2
								" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME "+ //6
								" FROM "+m_schema_name+".AF_CO_MAS_CLIENT B "+
								" WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') ";
																	
																	
					 String		Sql_company_details=" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(UPPER(CITY),' '), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0), "+
							" NVL(EMAIL,' ') "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
							
				  	rs1=stmt1.executeQuery(Sql_company_details);
					  more =rs1.next();


							 											
											if(more)
											{
											m_orient_name=rs1.getString(1);
											m_orient_add1=rs1.getString(2);
											m_orient_add2=rs1.getString(3);
											m_orient_city_name=rs1.getString(4);
											m_orient_tel_no=rs1.getString(5);
											m_orient_fax_no=rs1.getString(6);
											m_orient_vat_rate=rs1.getString(7);	
											m_orient_email=rs1.getString(8);	
											}
											
											
											
											
    /*String sql_return=" SELECT "+
									    " RETURN_NO, "+
									    " NVL(RETURN_CHARGE,0), "+
									    " UPPER(NVL(COMMENTS,' ')) "+
									    " FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS "+
											" WHERE UPPER(RETURN_NO)=UPPER('"+m_return_no+"')";
					rs1.close();						
					rs1=stmt1.executeQuery(sql_return);
					more =rs1.next();
					
					if(more){
					m_return_charge=rs1.getDouble(2);
					m_comments=rs1.getString(3);
					}
					
					*/
				//out.println(
        rs2= stmt2.executeQuery(" SELECT "+
									    " TO_CHAR(CHEQUE_DATE,'ddth Month YYYY') "+ //1
											" ,NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),' ') "+ //2
											" ,NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),' ') "+ //3
											" ,TO_CHAR(REALISED_DATE,'ddth Month YYYY') "+ //4
											" ,NVL(REC_AMOUNT,0) " + //5
											" ,NVL(RETURN_CHARGE,0) "+ //6
									    " ,UPPER(NVL(COMMENTS,' ')) "+ //7
											" ,NVL(RETURN_CHARGE,0) + NVL(REC_AMOUNT,0) "+ //8
									    " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,  "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
											" WHERE a.rec_no=b.receipt_no "+
										  //"  AND UPPER(RETURN_NO)=UPPER('"+m_return_no+"') "+
											"  AND UPPER(CHEQUE_NO)=UPPER('"+m_cheque_no+"')");
					//rs2.close();						
          boolean more2=rs2.next();
					String m_amount_wd="";
	        while(more2){
					m_chq_date=rs2.getString(1);
					m_bank_name =rs2.getString(2);
			    m_branch_name =rs2.getString(3);
          m_return_date=rs2.getString(4);
			    m_return_amt =rs2.getDouble(5);
	        m_hp_amount=rs2.getDouble(5);
					m_return_charge=rs2.getDouble(6);
					m_comments=rs2.getString(7);
					m_amount_wd=rs2.getString(8);
					more2=rs2.next();
			     }

					 String m_val = nf.format(m_hp_amount+m_return_charge);
					 String m_val2 = m_sn_methods.met_unformat_number(m_val);
					 int m_dot=m_val.indexOf(".");
					 int m_length=m_val.length();
					 String m_cents=m_val.substring(m_dot+1,m_length);
					//double m_cents_2=m_return_amt+m_return_charge;
						
					  //String m_int_val= m_return_amt+m_return_charge+"";	
					//	String m_amount_wd=m_sn_methods.met_unformat_number((nf.format(m_cents_2)));
					// out.println("m_return_amt" +m_return_amt); 	
					// out.println("m_hp_amount" +m_hp_amount); 	
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

									
					// out.println("<br><br>"); 	
					 //out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr><td width='*%' class='rep-body' align='center'><b>Registered Post</td></tr>");
					 out.println("</table>");
					 out.println("<br><br>"); 	
					 out.println("<br><br>"); 	
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr><td width='*%' class='rep-body' align='left'>"+Sys_date+"</td></tr>");
					 out.println("</table>");
					 out.println("<br>"); 	
						
					 rs1.close();
					 rs1=stmt1.executeQuery(Sql_client_data);
					 more =rs1.next();													
				
            if(more){
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr><td width='*%' class='rep-body' align='left'>"+rs1.getString(2)+"</td></tr>");
					 out.println("<tr><td width='*%' class='rep-body' align='left'>"+rs1.getString(3)+"</td></tr>");
					 out.println("<tr><td width='*%' class='rep-body' align='left'>"+rs1.getString(4)+"</td></tr>");
					 out.println("<tr><td width='*%' class='rep-body' align='left'>"+rs1.getString(5)+"</td></tr>");
					 out.println("</table>");
					 }
						
					 out.println("<br>"); 	
					 
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr><td width='*%' class='rep-body' align='left'>Dear Sir,</td></tr>");
					 out.println("</table>");
						
					 out.println("<br>"); 	
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'><b><u>CHEQUE RETURNS</u></b></td>");
	  	     out.println("</tr>");
					 out.println("</table>");	
					 out.println("<br>");
					 out.println("<br>");		
					 Data="Take notice that the cheque bearing on "+m_cheque_no+" dated "+m_chq_date+" drawn on "+m_bank_name+" "+
						    ", "+m_branch_name+" has been returned to us on  "+m_return_date+" dishonored for repayment & with the following endorsement.";
       		 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");		
	  	     out.println("<br><br>");
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='center'><B>"+m_comments+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");		

						
						
					 out.println("<br>");		
						
					/*if(!m_cents.equals("00")) {	
					 Data="This cheque has been issued by "+rs1.getString(2)+"  for the settlement of lease rentals and/or other dues of Rs."+nf.format(m_hp_amount)+"  "+
						    "due from "+rs1.getString(2)+".This is to demand from you the immediate payment of a sum of Rs."+nf.format(m_hp_amount+m_return_charge)+"  ("+m_sn_methods.numbersToChar(m_val2).toUpperCase()+")  "+ 
								"being the amount due upon the aforesaid cheque.";
					}
					else {
					
					 Data="This cheque has been issued by "+rs1.getString(2)+"  for the settlement of lease rentals and/or other dues of Rs."+nf.format(m_hp_amount)+"  "+
						    "due from "+rs1.getString(2)+".This is to demand from you the immediate payment of a sum of Rs."+nf.format(m_hp_amount+m_return_charge)+"  (RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY)  "+ 
								"being the amount due upon the aforesaid cheque.";

          }
					*/
					
					Data="This cheque has been issued by "+rs1.getString(2)+"  for the settlement of lease rentals and/or other dues of Rs."+nf.format(m_hp_amount)+"  "+
					"due from "+rs1.getString(2)+".This is to demand from you the immediate payment of a sum of Rs."+nf.format(m_hp_amount+m_return_charge)+"  (RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY)  "+ 
					"being the amount due upon the aforesaid cheque.";

					
					
           out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");		
					 out.println("<br>");		
					 Data="We are much grateful to you if you could make the above stated payment due to us within 03 days of this letter.";
       		 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");	
					 out.println("<br>");		
					 Data="If you have already paid the said amount you may disregard this letter.";
       		 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");							
					 out.println("<br>");		
					 out.println("<br>");		
					 out.println("<br>");	
					 out.println("<br>");		
					 Data="Yours faithfully,";
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");					
					 Data="LAKDERANA INVESTMENTS LIMITED";
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'><b>"+Data+"</b></td>");
	  	     out.println("</tr>");
					 out.println("</table>");					
					 out.println("<br><br>");	
					 out.println("<br><br>");	
						
					 Data="Authorized signatory";
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>"+Data+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");
						
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 //out.println("<td width='50%' class='rep-body' align='left'>Agreement No:&nbsp"+m_finance_no+"</td>");
					 //out.println("<td width='50%' class='rep-body' align='left'>Date:&nbsp "+Sys_date+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
						out.println("<br><br>"); 	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    }
							
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



