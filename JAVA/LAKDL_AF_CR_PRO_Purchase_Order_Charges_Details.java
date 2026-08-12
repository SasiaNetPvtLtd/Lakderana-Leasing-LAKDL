import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:27-03-2007

public class LAKDL_AF_CR_PRO_Purchase_Order_Charges_Details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
  java.lang.Math a;

    
  public ResultSet rs,rs_invoice;
	public String m_chksql;
	
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
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
			
			stmt=conn.createStatement();
			
			stmt_invoice=conn.createStatement();
			String chargeble_amount="";
			double m_chargeble_amount=0;
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CHARGES_DETAILS")){
			
			if(req.getParameter("chargeble_amount")!=null){
			chargeble_amount=req.getParameter("chargeble_amount");
			}
			
			m_chargeble_amount=Double.parseDouble(chargeble_amount);
			
			
							
			String m_application_no=null;
			String m_Payement_Mode="";
			
			String m_scr_position="";
			String m_doc_pos="";
			String screen_name="";
			String m_CLOSE="";
			
			String m_Day_dd="";
			String m_Day_mm="";
			String m_Day_yy="";
			String m_Next_Day_dd="";
			String m_Next_Day_mm="";
			String m_Next_Day_yy="";
			double m_Other_Charges=0;
			int m_Count=0;
			double m_NIBSM=0;
			int m_AMI=0;
			int m_PERIOD=0;
			double m_AMI_VALUES=0;
			double m_total_charge=0;
			double m_Invoice_Amount=0;
			double m_amo_charges=0;
			int b_flag_date=0;
			double m_general_amount=0;
			double m_receipt_charges=0;
			double receipt_total=0;
			double m_nibsm_charges=0;
			double tot=0;
			
			
			if(req.getParameter("application_no")!=null)
			{
			m_application_no=req.getParameter("application_no");
			
			}
			
				rs= stmt.executeQuery ("SELECT "+
 				 " PAYMENT_MODE  "+
 				 " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  "+
  			 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
	 			        				
				
			boolean 	more=rs.next();
				if(more)
				{
				m_Payement_Mode=rs.getString(1);
				
				}
			
				   out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<table  width='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<tr><td align='Center' ><B> Charges Details for  Application No - "+m_application_no+"  </b></td></tr>");
					 out.println("</table>");
						
						
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+m_application_no+"')><u>"+m_application_no+"</u></td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payment Mode</b></td>");
					out.println("<td width='50%' class=div_input><b>"+m_Payement_Mode+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
									
      	 	out.println("</table>");
						
			    out.println("<br>"); 
					
					
					out.println("<table align='center' width='100%' class='table' >");
					
										
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Balance To Be Paid</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(a.abs(m_chargeble_amount))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>"); 
  						
				
				rs_invoice= stmt_invoice.executeQuery ("SELECT INVOICE_NO "+
				"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
  			 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
				"  PURCHASE_ORDER_NO IS NULL ");
				
						  
			boolean 	more_invoice=rs_invoice.next();	
			String m_invoice_no="";
			
		
		  while(more_invoice)
			
			{
				
				m_invoice_no=rs_invoice.getString(1);
				
				
				rs= stmt.executeQuery ("SELECT "+
 				" NIBSM, "+
 				" AMI, "+
				" PERIOD "+	
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
					
					
				more=rs.next();
				if(more)
				{
				m_NIBSM=rs.getDouble(1);
				m_AMI=rs.getInt(2);
				m_PERIOD=rs.getInt(3);
				
				}
				
				
				


				
				int count=0;
				
				/*rs= stmt.executeQuery ("SELECT "+
 				"	SUM(AMI_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"')"); 
 				*/
				
				/*if(m_NIBSM>0)
				{
				
				count=(m_PERIOD-m_AMI)-1;
				
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				"	AND INSTALLMENT_NO >("+count+") ");
        }
				else
				{
				count=(m_PERIOD-m_AMI);
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				"	AND INSTALLMENT_NO >=("+count+") ");
				
				}
				*/
				
				if(m_AMI>0){
				count=(m_PERIOD-m_AMI);
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				"	AND INSTALLMENT_NO >=("+count+") ");

				}
				
        more=rs.next();
				if(more)
				{
				m_AMI_VALUES=rs.getDouble(1);
				
				}
				
			

          rs= stmt.executeQuery ("SELECT "+
					"  NVL(SUM(AMOUNT),0) "+
					"  FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
					"  CHARGE_TYPE='INV' ");
										
					more=rs.next();
					if(more)
					{
					m_amo_charges=rs.getDouble(1);
					}
					
					
									
				rs= stmt.executeQuery ("SELECT "+
 				" SUM(GRENTAL_AMOUNT) "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				" AND INSTALLMENT_NO=0 ");
					
					
					more=rs.next();
					if(more)
					{
					m_general_amount=rs.getDouble(1);
					
					}
					
					
					
									
				rs= stmt.executeQuery ("SELECT "+
 				" SUM(NIBSM) "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
 				
								
					more=rs.next();
					if(more)
					{
					m_nibsm_charges=rs.getDouble(1);
					
					}
					
					

					
								
				
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><u><b>Charges</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='20%' class=div_input align='right' style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+m_invoice_no+"')><u>"+m_invoice_no+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
										
					out.println("</table>");
						
					out.println("<table align='center' width='100%' class='table' >");
															
					if(m_nibsm_charges>0){
					
							
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>NIBSM Charges</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_nibsm_charges)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					}
					
					if(m_AMI_VALUES>0){
								
							
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>AMI Charges</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_AMI_VALUES)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
          }
					
					if(m_amo_charges>0){
										
				
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Other Charges</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_amo_charges)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					}
					
					if(m_general_amount>0){
						
								
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>First Instalment</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_general_amount)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");


					}
				out.println("</table>");	
					
					tot=tot+m_NIBSM+m_AMI_VALUES+m_amo_charges+m_general_amount;
					
					more_invoice=rs_invoice.next();
				out.println("<br>");
				
				}
				
				
						
			/*		String Sql_Receipt="SELECT "+
				" REC_NO, "+
				" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order'),"+
 				" NVL(RENTAL_OTER_INVOICE,0) REC_TOTAL "+
        " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
        " WHERE CLIENT_CODE = "+
        " (SELECT CLIENT_CODE  "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) "+
        " AND STATUS NOT IN('RET','C')  ";
			*/	
				
				 String Sql_Receipt="SELECT "+
		 "     DISTINCT REC_NO,  "+
		 "     DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order'), "+
 		 "     NVL(RENTAL_OTER_INVOICE,0) REC_TOTAL  "+
     "     FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B "+
     "     WHERE A.REC_NO=B.RECEIPT_NO AND CLIENT_CODE =  "+
     "     (SELECT CLIENT_CODE   "+
     "     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
     "     WHERE APPLICATION_NO=UPPER('"+m_application_no+"'))  "+
     "     AND STATUS NOT IN('RET','C') AND "+
     "    (B.INVOICED_AMOUNT - B.SETTELED_AMOUNT )>0 AND "+
     "      B.ALLOCATION_NO IS NOT NULL "+
			
		 "UNION "+
			
		 " SELECT  "+
		 "  DISTINCT REC_NO,   "+
		 "  DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order'),  "+
 		 " NVL(RENTAL_OTER_INVOICE,0) REC_TOTAL   "+
     "      FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
     "      WHERE CLIENT_CODE =   "+
     "     (SELECT CLIENT_CODE    "+
     "      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
     "      WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) "+  
     "      AND STATUS NOT IN('RET','C') AND  "+
     "      REC_NO NOT IN ( "+
     "      SELECT RECEIPT_NO  "+
     "      FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS   "+
     "      WHERE (INVOICED_AMOUNT - SETTELED_AMOUNT )>0 AND  "+
     "      ALLOCATION_NO IS NOT NULL ) ";

    
					
					
					
				//	double tot=m_NIBSM+m_AMI_VALUES+m_amo_charges+m_general_amount;
				  out.println("<table align='center' width='100%' class='table' >");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Total</b></td>");
					out.println("<td width='20%' class=div_input align='right'>(<b>"+nf.format(tot)+"<b>)</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
															
      	 	out.println("</table>");
						
						
						
					out.println("</br>");	
					
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><u><b>Receipt Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");					
					
					out.println("</table>");
					
					out.println("<br>");	
						
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Number</b></td>");
					out.println("<td width='30%' class=div_input><b>Settlement Mode</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Amount</b></td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");	
					
					out.println("<table align='center' width='100%' class='table' >");
					
					rs= stmt.executeQuery(Sql_Receipt);

					while(rs.next()){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					receipt_total=receipt_total+rs.getDouble(3);
			  	}
					
					
					
												
      	 out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Receipt Total</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(receipt_total)+"<b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
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



