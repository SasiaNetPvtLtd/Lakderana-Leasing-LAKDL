/*
 *  Created By          :   Samitha Kulatilaka
 *  Created Date        :   2009-12-17
 *  
 *  System              :   OFSCL
 *  Menu Hierarchy      :   Management Information > Leasing And Loans > Finance
 *  Screen Name         :   Invoice Report
 *  Screen ID           :   AF_MISF_INVOICE_REPORT
 *  Screen URL          :   AF_MISF_invoice_report
 */


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class LAKDL_AF_MISF_invoice_report_view extends javax.servlet.http.HttpServlet {
    
    ServletOutputStream out = null;
    Connection conn;
    Statement stmt, stmt1, stmt2, stmt3;
    java.text.NumberFormat nf, nf1;
    public ResultSet rs, rs1, rs2, rs3;
    public String m_chksql;
    
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        try {
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
            
            String m_html_client_url  = m_sn_methods.html_client_url.trim();
            String m_schema_name      = m_sn_methods.schema_name;
            String m_class_url        = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
            String m_fschema_name     = m_sn_methods.client_name.trim();
            String m_header_name      = m_sn_methods.header_name.trim();
            
            nf = java.text.NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            nf1 = java.text.NumberFormat.getInstance(Locale.US);
            nf1.setMinimumFractionDigits(2);
            nf1.setMaximumFractionDigits(2);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
            //m_chksql = req.getParameter("chksql");
            
            out    = res.getOutputStream();
            conn   = m_sn_methods.met_user_validate(req);
            stmt   = conn.createStatement();
            stmt1  = conn.createStatement();
            stmt2  = conn.createStatement();
            stmt3  = conn.createStatement();
            
            
            
			
			  String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
				String m_branch_code=req.getParameter("branch_code");
				  // double m_tot_rec=0;
					
		double m_tot_amount=0,m_settle_amt=0,m_bal_amt=0;
		
					rs1= stmt1.executeQuery(" SELECT "+
					  "  FINANCE_NO, "+//1
					  "  INVOICE_NO, "+//2
					  "  NVL(TOTAL_AMOUNT,0), "+//3
					  "  NVL(SETTELE_AMOUNT,0), "+//4
					  "  NVL(BALANCE_TO_BE_RECEIVED,0), "+//5
					  //"  INVOICE_TYPE, "+//6
					  " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(LTRIM(DECODE(INVOICE_TYPE,'INV_OTHER',SUBSTR(REMARKS,INSTR(REMARKS,' - ')+2),INVOICE_TYPE))),DECODE(INVOICE_TYPE,'INV_GENER','General Invoice',INVOICE_TYPE)), "+ //6
					  "  ACTIVE_STATUS "+//7
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" WHERE  TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					//" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					//  "  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					//  "  ENT_USER, "+//4
					//  "  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					//  //"  NVL(CHEQUE_NO,'-'), "+//6
					//  "  CLIENT_CODE, "+//6
					//  "  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					//	" NVL(SUS_REF_NO,'-'), "+//8
					//	" SETTLE_MODE,"+//9
					//	" NVL(OTH_COMMENTS,'-') , "+//10
					//	" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					//	" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), "+
					//	" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
					//	" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
					//	" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_invoice_report_view?from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Invoice Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
						//out.println("<TR><TD align='Center' ><B> Branch Code  "+m_branch_code+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') ><DIV class=div_input ><b>Invoice No</b></DIV></td>"); 
						// out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); Samitha
						out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Total Amount'    onclick=sort_data('TOTAL_AMOUNT') ><DIV class=div_input ><b>Total Amount</b></DIV></td>"); 
						out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Settle Amount'    onclick=sort_data('SETTELE_AMOUNT') ><DIV class=div_input ><b>Settle Amount</b></DIV></td>"); 
						out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Balance Amount'    onclick=sort_data('BALANCE_TO_BE_RECEIVED') ><DIV class=div_input ><b>Balance Amount</b></DIV></td>"); 
						out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Invoice Type'    onclick=sort_data('INVOICE_TYPE') ><DIV class=div_input ><b>Invoice Type</b></DIV></td>"); 
						out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Active Status'    onclick=sort_data('ACTIVE_STATUS') ><DIV class=div_input ><b>Active Status</b></DIV></td>"); 
						//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
						//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
						//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
						//out.println("<td width='12%' ><DIV class=div_input ><b>Rental Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
						//out.println("<td width='12%' ><DIV class=div_input ><b>ODI Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
						//out.println("<td width='12%' ><DIV class=div_input ><b>Other Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
						
						out.println("</tr>"); 
					
					
					int i=1;
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'>"+i+"</td>"); 
							//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
							//out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input align='right'>"+rs1.getString(1)+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+rs1.getString(2)+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+rs1.getString(6)+"</td>");
							out.println("<td width='12%' class=div_input align='right'>"+rs1.getString(7)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
							//out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
							//out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getDouble(15)+"</td>");//Added by Dineth on 11-06-2009
							//out.println("<td width='12%' class=div_input >"+rs1.getDouble(16)+"</td>");//Added by Dineth on 11-06-2009
							//out.println("<td width='12%' class=div_input >"+rs1.getDouble(17)+"</td>");//Added by Dineth on 11-06-2009
							out.println("</tr>");
							// m_tot_rec=m_tot_rec+rs1.getDouble(2); Samitha
							//added by minali on 26-12-2014 for #15198
							m_tot_amount=m_tot_amount+rs1.getDouble(3);
							m_settle_amt=m_settle_amt+rs1.getDouble(4);
							m_bal_amt=m_bal_amt+rs1.getDouble(5);

	
							more = rs1.next();
							i++;
							
							
						}	//added by minali on 26-12-2014 for #15198
							out.println("<tr >");			
							out.println("<td width='40%' colspan= 3 align='right' ><b>Total</td>");
							out.println("<td width='15%' align='right'><b>"+nf.format(m_tot_amount)+"</td>"); 			 
							out.println("<td width='15%' align='right'><b>"+nf.format(m_settle_amt)+"</td>");
							out.println("<td width='15%' align='right'><b>"+nf.format(m_bal_amt)+"</td>");
							
					
						out.println("</tr>");
				}
						// out.println("<tr>");
						// out.println("<td width='1%'></td>"); 
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='20%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						//Added by Dineth on 11-06-2009
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						//End by Dineth on 11-06-2009
						// out.println("</tr>");
						// out.println("<tr>");
					  // out.println("<td width='1%'></td>"); 
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='20%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						//Added by Dineth on 11-06-2009
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						// out.println("<td width='12%' class=div_input ></td>");
						//End by Dineth on 11-06-2009
						
						// out.println("</tr>");
      	 		out.println("</table>");
						// out.println("<br><br><br>"); 
						
						// double m_rec_count=0;
						// double m_rec_amt=0;
						
						// rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					 // " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					 // " WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 // " AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
											// " AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08

					 // " GROUP BY SETTLE_MODE ");
						
						// out.println("<table align='center' width='50%' class='table' border='1'>");						
						// out.println("<tr class=pdn_txtpos2>");
						// out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
						// out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
						// out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
						// out.println("</tr>"); 		
						
						// while(rs1.next()){
						
							// m_rec_count=m_rec_count+rs1.getDouble(3);
							// m_rec_amt=m_rec_amt+rs1.getDouble(2);
							
							// if(mflag){
								// out.println("<tr class=tr_input>");
								// mflag=false;
							// }
							// else{
								// out.println("<tr class=tr_input1>");
								// mflag=true;
							// }
							// out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
							// out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
							// out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
							// out.println("</tr>");
						// }
						// out.println("<tr>");
						// out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
						// out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
						// out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
						// out.println("</tr>");
						// out.println("</table>"); 
						
						// out.println("<br><br><br>"); 
						
						// m_rec_count=0;
						// m_rec_amt=0;
						
						// rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					 // " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					 // " WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 // " AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
											// " AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08

					 // " GROUP BY ENT_USER,SETTLE_MODE "+
					 // " ORDER BY ENT_USER,SETTLE_MODE ");
						
						// out.println("<table align='center' width='50%' class='table' border='1'>");						
						// out.println("<tr class=pdn_txtpos2>");
						// out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
						// out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
						// out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
						// out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
						// out.println("</tr>"); 		
						
						// while(rs1.next()){
							// m_rec_count=m_rec_count+rs1.getDouble(4);
							// m_rec_amt=m_rec_amt+rs1.getDouble(3);
							
							// if(mflag){
								// out.println("<tr class=tr_input>");
								// mflag=false;
							// }
							// else{
								// out.println("<tr class=tr_input1>");
								// mflag=true;
							// }
							// out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
							// out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							// out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
							// out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
							// out.println("</tr>");
						// }
						// out.println("<tr>");
						// out.println("<td width='20%' class=div_input ></td>");
						// out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
						// out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
						// out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
						// out.println("</tr>");
						// out.println("</table>"); 
						
						
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

            }
        
        
        catch(Exception ex) {
            try {
                out.println("Error : " + ex.toString());
            }
            catch(Exception e) {}
        }
        
        
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch(Exception e) {}
            }
        }
        
    }
    
}
