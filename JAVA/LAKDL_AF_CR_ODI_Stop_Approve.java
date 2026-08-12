//nuwan de silva on 05-03-08

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_ODI_Stop_Approve extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
  //public synchronized void service(HttpServletRequest req, HttpServletResponse res) // commented by udara 29-05-2017
	public void service(HttpServletRequest req, HttpServletResponse res) // added by udara 29-05-2017
	{
		
		
		Connection conn = null;
		Statement stmt= null,stmt1= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null;
		String m_chksql= null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			int m_count_payment_no=0;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
					
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("odi_adjustment_details")){
					  // String  m_payee_name = req.getParameter("payee_name").trim();
			    String  m_finance_no = req.getParameter("finance_no").trim();
					out.println("<table class=table border='0' width='100%' >");
					
					//out.println("<tr class=tr_input>");
          //out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("</tr>");
					
					
					//--------sandun on 17-06-2009-------------------------------
					out.println("<td  colspan=6 width='80%' align='center'>&nbsp;</td>");
					out.println("<td width='10%' ><select class=txt_input type=text name=APP_TYPE maxlength=1 size=1  onchange=\"change_all()\"  >");  
					out.println("<option value=\"A\" selected>Approve</option>");
					out.println("<option value=\"D\"  >Disapprove</option>");
					out.println("</select></td>");
					out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQ  VALUE=\"\" onclick=\"change_val()\"  ></td>");			
					out.println("</tr>");			
					//------------------------------------------------------------
					
										
					out.println("<tr class=pdn_txtpos2 align='center'>");
         out.println("<td  width='10%' >Invoice</td>");
          
          out.println("<td  width='10%' align='left'>Allocated Date</td>");
					out.println("<td  width='10%' align='right'>Balance Amount</td>");
					out.println("<td  width='10%' align='right'>Adjusted Amount</td>");
					//out.println("<td  width='10%' align='right'>Percentage</td>");
					out.println("<td  width='10%' align='left'>Adjusment Type</td>");
					out.println("<td  width='8%' align='left'>Enter User</td>");
					//out.println("<td  width='20%' align='center'>Remark</td>");//Commented by Dineth on 2009-02-13
					//out.println("<td  width='20%' align='center'>Entered Remark</td>");//Commented by Dineth on 2009-02-13
					out.println("<td  width='10%' align='center'>Approve</td>");
					out.println("<td  width='5%'  align='center'>Select</td>");
					//out.println("<td  width='5%' align='center'>Detail</td>");		
					out.println("<td  width='15%' align='center'>Remark</td>");		
					out.println("</tr>");
					 
           int j = 0;   
											
							/*rs = stmt.executeQuery (" SELECT "+
							" A.ALLOCATION_NO, "+ //1
							" A.INVOICE_NO, "+ //2
							" A.ODI_NO, "+ //3
							" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+ //4
							" A.AJUSTED_AMOUNT, "+  //5
							" A.ENT_USER, "+ //6
							" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //7
							" DECODE(A.ODI_TYPE,'I','Increase','D','Decrease','-'), "+ //8
							" DECODE(A.ODI_STATUS,'YES','Start','NO','Stop','-') ,"+ //9
							" A.ODI_TYPE ,"+ //10
							" A.ODI_STATUS "+ //11
							" FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO='"+m_finance_no+"' "+  
							" AND A.STATUS='ENT'  ");
							//" AND UPPER(ALLOCATION_NO) LIKE UPPER('"+m_payee_name+"%')  ");
             */
							double m_percentage =0.0;
							
							/*rs = stmt.executeQuery ("SELECT "+ 
							 "'-',"+
							 " A.INVOICE_NO, "+ 
							 " A.ODI_NO,"+  
							 " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  "+
							 " SUM(A.AJUSTED_AMOUNT),  "+
							 " A.ENT_USER, '','','',A.ODI_TYPE,A.ODI_STATUS,"+
							 " SUM(C.ODI_BAL_AMOUNT) "+//12
							 " FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+
							 "      "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
							 "      "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
							 " WHERE A.ODI_NO = C.ODI_REF_NO "+ 
							 " AND A.INVOICE_NO=B.INVOICE_NO  "+
							 " AND B.FINANCE_NO='"+m_finance_no+"' "+  
							 " AND A.STATUS='ENT'  "+
							 " GROUP BY A.INVOICE_NO,A.ODI_NO,A.ALLOCATED_DATE,A.ENT_USER,A.ODI_TYPE,A.ODI_STATUS "); */
             
							rs = stmt.executeQuery ("SELECT "+ 
							 "'-','','', "+//3
							 " A.INVOICE_NO, "+ //4
							 " A.ODI_NO,"+  //5
							 " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  "+//6
							 " SUM(A.AJUSTED_AMOUNT),  "+//7
							 " A.ENT_USER, '','','',A.ODI_TYPE,A.ODI_STATUS,"+
							 " SUM(C.ODI_BAL_AMOUNT), "+//14
							 " NVL(A.REMARKS,'-'), "+//15
								
							// added by udara 23-12-2013
							" NVL(DECODE(INC_DEC_STAT,'INC','Increase','DEC','Decrease'), "+
							" (CASE WHEN SUM(A.AJUSTED_AMOUNT) < 0 THEN 'Decrease' "+
							" ELSE  "+
							" 'Increase' "+
							" END) "+
							") INC_DEC_STAT "+ // 16
							// end by udara 23-12-2013	
								
								
							 " FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+
							 "      "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
							 "      "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
							 " WHERE A.ODI_NO = C.ODI_REF_NO "+ 
							 " AND A.INVOICE_NO=B.INVOICE_NO  "+
							 " AND B.FINANCE_NO='"+m_finance_no+"' "+  
							 " AND A.STATUS='AP1'  "+
							 " GROUP BY A.INVOICE_NO,A.ODI_NO,A.ALLOCATED_DATE,A.ENT_USER,A.ODI_TYPE,A.ODI_STATUS,A.REMARKS "+
								"  ,A.INC_DEC_STAT ");
								
								
              			
							
							while(rs.next()){
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
									m_percentage = rs.getDouble(7)/rs.getDouble(14)*100;								
									out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=show_invoice_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
                  
									out.println("<td width='10%' align='left' >"+rs.getString(6) +"</td>");
									out.println("<td width='10%' align='right'  >"+nf.format(rs.getDouble(14))+"</td>");
                  out.println("<td width='10%' align='right'  >"+nf.format(rs.getDouble(7)*-1)+"</td>");
									//out.println("<td width='10%' align='right'  >"+nf.format(Math.abs(m_percentage))+"%</td>");
									
									// commented by udara 23-12-2013
									/*
									if(rs.getDouble(7)<0)
									out.println("<td width='10%' align='left'   >Increase</td>");
									else
									out.println("<td width='10%' align='left'   >Decrease</td>");
									*/
									
									out.println("<td width='10%' align='left'   >"+rs.getString(16) +"</td>"); // added by udara 23-12-2013
									
									out.println("<td width='10%' align='left'   >"+rs.getString(8) +"</td>");
									//Commented by Dineth on 2009-02-13
									//out.println("<td width='20%' align='center' ><input type='text' name='TXT_REMARK_"+j+"' maxlenght='500' size='20' style='width:200' class='txt_input'></td>");
									//	out.println("<td width='10%' align='left'   >"+rs.getString(15) +"</td>");
									//end comment by Dineth on 2009-02-13
									//out.println("<td width='10%' align='left'   >"+rs.getString(7) +"</td>");
									//out.println("<td width='5%' align='left'   >"+rs.getString(8) +"</td>");
									//out.println("<td width='5%' align='left'   >"+rs.getString(9) +"</td>");
									out.println("<td width='10%' ><select class=txt_input type=text name=APPROVE_TYPE"+j+" maxlength=1 size=1    >");  
					        out.println("<option value=\"A\" selected>Approve</option>");
					        out.println("<option value=\"D\"  >Disapprove</option>");
					        out.println("</select></td>");
									out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
									//out.println("<td width='5%' align='center'   ><input type='button' name='DET_BUT_"+j+"' value='Detail' class='but_input' onClick=\"load_odi_detail('"+m_finance_no+"')\"></td>");
									//out.println("<td width='12%' align='left'  >"+rs.getString(1) +"</td>");
									//out.println("<td width='10%' align='left' >"+rs.getString(3) +"</td>");
									out.println("<td width='10%' align='left' >"+rs.getString(15) +"</td>");
																	
									out.println("<input type=hidden name=ALLOCATION_NO"+j+" value="+rs.getString(1)+" >");
									out.println("<input type=hidden name=INVOICE_NO"+j+" value="+rs.getString(4)+" >");
									out.println("<input type=hidden name=ODI_NO"+j+" value="+rs.getString(5)+" >");
									out.println("<input type=hidden name=AJUSTED_AMOUNT"+j+" value="+rs.getDouble(7)+" >");
									out.println("<input type=hidden name=ODI_TYPE"+j+" value="+rs.getString(12)+" >");
									out.println("<input type=hidden name=ODI_STATUS"+j+" value="+rs.getString(13)+" >");
								  out.println("<input type=hidden name=ODI_BAL"+j+" value="+rs.getDouble(14)+" >");
									out.println("</tr>");
                	j=j+1;
              }
							
					out.println("<input type=hidden name=hid_no_rec_count value="+j+">");
					
					out.println("<tr class=tr_input>");
				 // out.println("<td colspan=8 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr></table>");
          
					out.println("</table>");
			

      }
			else if(m_chksql.trim().equals("odi_details")){
			
			String m_finance_no= req.getParameter("fin_no");
			double m_tot_cal=0.0;
			double m_tot_adj=0.0;
			double m_tot_set=0.0;
			double m_tot_bal=0.0;
			
			out.println("<HTML><HEAD><TITLE>ODI Details</TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<Script>");
			out.println("function show_invoice_breakup(val){");
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop_Approve?chksql=invoce_breakup&invoice_no=\"+val+\"\";");
			//out.println("alert(m_url);");
		  out.println("window.open(m_url,'displayWindow2','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
		  out.println("}"); 
			out.println("</Script>");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B>ODI Breakup Details</B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
      
			/*rs = stmt.executeQuery (" SELECT "+
							" A.ALLOCATION_NO, "+ //1
							" A.INVOICE_NO, "+ //2
							" A.ODI_NO, "+ //3
							" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+ //4
							" A.AJUSTED_AMOUNT, "+  //5							
							" C.ODI_CAL_AMOUNT, "+	//6	
							" C.ODI_BAL_AMOUNT,  "+ //7
							" C.ODI_SETTLED_AMOUNT, "+//8
							" NVL((SELECT D.REMARKS  FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET D WHERE D.ALLOCATION_NO=A.ALLOCATION_NO),'-') "+//9
							" FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+
							"      "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
							"      "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
							" WHERE A.ODI_NO     = C.ODI_REF_NO "+ 
							" AND   A.INVOICE_NO   = B.INVOICE_NO "+
							" AND   C.INVOICE_NO = '"+m_inv_no+"' "+  
							" AND   A.STATUS='ENT'  ");
					*/	
					
			rs = stmt.executeQuery ("SELECT "+ 							 
							 " B.INVOICE_NO, "+ //1
							 " SUM(A.AJUSTED_AMOUNT), "+//2
							 " SUM(C.ODI_BAL_AMOUNT), "+//3
							 " SUM(C.ODI_SETTLED_AMOUNT), "+//4
							 " SUM(C.ODI_CAL_AMOUNT) "+//5
							 " FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+
							 "      "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
							 "      "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
							 " WHERE A.ODI_NO = C.ODI_REF_NO "+ 
							 " AND A.INVOICE_NO=B.INVOICE_NO  "+
							 " AND B.FINANCE_NO='"+m_finance_no+"' "+  
							 " AND A.STATUS='ENT'  "+
							 " GROUP BY B.INVOICE_NO ");

			
			
			
			 int j=1;
			 out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
			 
			 out.println("<tr class=pdn_txtpos2>");
			 out.println("<td width='1%'>No.</td>");				 
			 out.println("<td width='10%' class=div_input align='left'>Invoice No</td>");			
			 out.println("<td width='10%' class=div_input align='right'>ODI Amount</td>");
			 out.println("<td width='10%' class=div_input align='right'>Adjsted Amount</td>");	
			 out.println("<td width='10%' class=div_input align='right'>Settled Amount</td>");
			 out.println("<td width='10%' class=div_input align='right'>Balance Amount</td>");
			 out.println("</tr>");
				
				while(rs.next()){
			 out.println("<tr>");
			 out.println("<td width='1%'>"+j+"</td>");					
			 out.println("<td width='10%' align='left' class=div_input style='cursor:hand' onclick=show_invoice_breakup('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(2)*-1)+"</td>");	
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");	
			 out.println("</tr>");
			 m_tot_cal=m_tot_cal+rs.getDouble(5);
			 m_tot_adj=m_tot_adj+rs.getDouble(2);
			 m_tot_set=m_tot_set+rs.getDouble(4);
			 m_tot_bal=m_tot_bal+rs.getDouble(3);
				j++;
				}
			 out.println("<tr>");						 
			 out.println("<td width='10%' class=div_input colspan=2 align='right'><b>Total</td>");			
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_cal)+"</td>");
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_adj*-1)+"</td>");	
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_set)+"</td>");
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_bal)+"</td>");
			 out.println("</tr>");	
			 
			 out.println("</table>");	
				
			
			}
			
			else if(m_chksql.trim().equals("invoce_breakup")){
			
			String m_invoice_no= req.getParameter("invoice_no");
			double m_tot_cal=0.0;
			double m_tot_adj=0.0;
			double m_tot_set=0.0;
			double m_tot_bal=0.0;
			
			out.println("<HTML><HEAD><TITLE>ODI Details</TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B>ODI Breakup Details - Invoice No. "+m_invoice_no+"</B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
      
			rs = stmt.executeQuery (" SELECT "+
							" A.ODI_NO, "+ //1
							" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+ //2
							" A.AJUSTED_AMOUNT, "+  //3
							" C.ODI_CAL_AMOUNT, "+	//4	
							" C.ODI_BAL_AMOUNT,  "+ //5
							" C.ODI_SETTLED_AMOUNT, "+//6
							" NVL((SELECT D.REMARKS  FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET D WHERE D.ALLOCATION_NO=A.ALLOCATION_NO),'-') "+//7
							" FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+
							"      "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
							"      "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
							" WHERE A.ODI_NO     = C.ODI_REF_NO "+ 
							" AND   A.INVOICE_NO   = B.INVOICE_NO "+
							" AND   C.INVOICE_NO = '"+m_invoice_no+"' "+  
							" AND   A.STATUS='ENT'  ");
				
					
				
			
			 int j=1;
			 out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
			 
			 out.println("<tr class=pdn_txtpos2>");
			 out.println("<td width='1%'>No.</td>");				 
			 out.println("<td width='10%' class=div_input align='left'>ODI No</td>");			
			 out.println("<td width='10%' class=div_input align='right'>ODI Amount</td>");
			 out.println("<td width='10%' class=div_input align='right'>Adjsted Amount</td>");	
			 out.println("<td width='10%' class=div_input align='right'>Settled Amount</td>");
			 out.println("<td width='10%' class=div_input align='right'>Balance Amount</td>");
			 out.println("<td width='30%' class=div_input align='right'>Remark</td>");
			 out.println("</tr>");
				
				while(rs.next()){
			 out.println("<tr>");
			 out.println("<td width='1%'>"+j+"</td>");					
			 out.println("<td width='10%' align='left' class=div_input >"+rs.getString(1)+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(3)*-1)+"</td>");	
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
			 out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");	
			 out.println("<td width='30%' align='left' class=div_input >"+rs.getString(7)+"</td>");
			 out.println("</tr>");
			 m_tot_cal=m_tot_cal+rs.getDouble(4);
			 m_tot_adj=m_tot_adj+rs.getDouble(3);
			 m_tot_set=m_tot_set+rs.getDouble(6);
			 m_tot_bal=m_tot_bal+rs.getDouble(5);
				j++;
				}
			 out.println("<tr>");						 
			 out.println("<td width='10%' class=div_input colspan=2 align='right'><b>Total</td>");			
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_cal)+"</td>");
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_adj*-1)+"</td>");	
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_set)+"</td>");
			 out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_bal)+"</td>");
			 out.println("<td width='30%' class=div_input align='right'>&nbsp;</td>");			
				out.println("</tr>");	
			 
			 out.println("</table>");	
				
			
			}
				else if(m_chksql.trim().equals("main_page")){
				
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
					/*						 rs = stmt.executeQuery (" SELECT "+
																			" COUNT(PAYMENT_NO) "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
																			" WHERE  PROCESS_STATUS='Y' AND  ENTRY_TYPE<>'V' ");

        boolean more=rs.next();
				if(more){
				m_count_payment_no=rs.getInt(1);
				} 	
				*/
				out.println("<Script>");
				
				out.println("function load_odi_detail(val){");//Added By sandun on 21-01-2009
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop_Approve?chksql=odi_details&fin_no=\"+val+\"\";");
			  //out.println("alert(m_url);");
				out.println("window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 				
				
			  out.println("function load_data(num) {");
			  out.println(" if(num!=\"\"){");	
        out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_FINANCE_DETAIL_DRILL&finance_no=\"+num+\"\", \"oBj\",\"left=150,top=100,width=620,height=390,scrollBars=1\");"); 
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");   //SHOW_APPLICATION_DETAIL_DRILL
				out.println(" }");
				out.println("}");
				
				out.println("function change_all(){");
				out.println(" hid_cnt=parseFloat(document.Form1.hid_no_rec_count.value);");
				out.println(" for(i=0;i<hid_cnt;i++){");
				out.println(" document.Form1.elements[\"APPROVE_TYPE\"+i].value = document.Form1.APP_TYPE.value;"); 
				out.println("}");
				out.println("} "); 
				
				out.println("function change_val(){");
				out.println(" hid_cnt=parseFloat(document.Form1.hid_no_rec_count.value);");
				out.println(" if(document.Form1.CHK_REQ.checked==true){");
				out.println(" for(i=0;i<hid_cnt;i++){");
				out.println("document.Form1.elements[\"CHK_REQUIRED\"+i].checked=true; "); 
				out.println("document.Form1.elements[\"CHK_REQUIRED\"+i].value=\"on\"; ");
				out.println("get_total(i);");
				out.println("}");
				out.println("}else if(document.Form1.CHK_REQ.checked==false){");
				out.println(" for(i=0;i<hid_cnt;i++){");
				out.println("document.Form1.elements[\"CHK_REQUIRED\"+i].checked=false; "); 
				out.println("document.Form1.elements[\"CHK_REQUIRED\"+i].value=\"off\"; "); 
				out.println("get_total(i);");
				out.println("}");
				out.println("}");
				out.println("}");
				
				
				out.println("function get_total(j){");//Added By sandun on 21-01-2009
				
				
				out.println(" var sett_amt=0;");
				out.println(" var odi_bal =0;");
				out.println(" var odi_per =0;");		
			
				out.println(" hid_cnt=parseFloat(document.Form1.hid_no_rec_count.value);");
			  out.println(" for(j=0;j<hid_cnt;j++){"); 
				out.println("m_chk_required=\"CHK_REQUIRED\"+j;");			
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println(" sett_amt = sett_amt + parseFloat(unformat_noobject(document.Form1.elements['AJUSTED_AMOUNT'+j].value));");//modified  by SJ on 09-06-2009
				out.println(" odi_bal =  document.Form1.hid_odi_bal.value;");
			  //out.println("alert(odi_bal);");
				//out.println(" odi_bal  = odi_bal  + parseFloat(unformat_noobject(document.Form1.elements['ODI_BAL'+j].value));");
				out.println(" odi_per  = (sett_amt/odi_bal)*100 ;");
				out.println("}");
				out.println("}");				
				out.println("m_adj_amount.innerHTML=\"<b><font color='blue'>Total Adjusted Amount : \"+format_noobject(sett_amt)+\" &nbsp; Percentage : \"+format_noobject(Math.abs(odi_per))+\"%\";");
				out.println("}");
				
				
				//Added by Dineth on 2008-12-05
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 

				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
				out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
				out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				*/
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
		  	out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
		  	out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);"); 
		  	out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		vehicle_assign(oBj);"); 
		  	out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		lease_assign(oBj);"); 
		  	out.println("		}");
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		term_assign(oBj);"); 
		  	out.println("		}");
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 

				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
	
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
			
			//client Help
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				//out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_ODI_APP2','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				
				out.println("}");

			//Lease Help
			  out.println("function lease_help(){");
				out.println("Crit=document.Form1.LEASE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				//out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("HelpBox('1','10','0',Crit,'LeaseSql_ODI_APP2','3');");//mOD bY Sandun on 01-07-2009
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.APPLICATION_NO.value =oBj.valout[3]");
				
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[6]");//Added by Dineth on 2009-02-16
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[4]");//Added by Dineth on 2009-02-16
				out.println("get_payment_details()");
				//out.println(" check_lease(document.Form1.LEASE_NO.value);");//Commented by Dineth on 2008-12-03
				out.println("get_odi_balance();");//Added By sandun on 09-06-2009
				out.println("}");
			//Vehicle Help

				out.println("function get_odi_balance(){"); //Added by Sandun on 2009-06-09
				out.println("document.Form1.hid_asign.value='M1'");
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_get_odi_balace&finance_no=\"+document.Form1.LEASE_NO.value+\"\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_odi_app_count(){"); //Added by Sandun on 01-07-2009
			out.println("document.Form1.hid_asign.value='M2'");
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_get_odi_count_app2\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			  out.println("function get_vector(data_vec) {");//Added by Sandun on 2009-06-09
				out.println("if(data_vec.length>0 && document.Form1.hid_asign.value=='M1'){");
	      out.println("document.Form1.hid_odi_bal.value=data_vec[0]; ");
				out.println("}");
				out.println("else if(data_vec.length>0 && document.Form1.hid_asign.value=='M2'){");
				out.println("document.Form1.hid_odi_app_count.value=data_vec[0]; ");
				out.println("m_inv_count.innerHTML=\"<b>Pending Approvals - \"+document.Form1.hid_odi_app_count.value+\"\";");
				out.println("}");
				//out.println("alert(document.Form1.hid_odi_app_count.value);");
				out.println("}");
				
				
				//End by Dineth on 2008-12-05
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Finance - O D Interest Adjustments Approval 2 \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Finance - O D Interest Adjustments Approval 2 - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				out.println("function change_val_req(row_no){")	;
        out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");
				out.println("get_total(row_no);");
				out.println("}");	
				

				out.println("function validate_data(){"); 
				out.println("return true;"); 
				out.println("}"); 
								
				out.println("function before_submit(){ "); 
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				//out.println("   document.Form1.hid_no_rec.value="+j+";");//Added By Nuwan De Silva
				out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_no_rec_count.value;");//Added By Nuwan De Silva
				
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Save_Stop_Approve';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("} "); 
				
			out.println("function get_payment_details(){");
			//out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop_Approve?chksql=odi_adjustment_details&payee_name=\"+m_payee_name+\"\";");
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop_Approve?chksql=odi_adjustment_details&finance_no=\"+document.Form1.LEASE_NO.value+\"\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			
			
			
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance Process - O D Interest Adjustments Approval 2 - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"\";");  //m_help_msg_LAKDL_AF_PRO_CR_finance_activation
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("	new_window();"); 
					out.println("}");
					out.println("else if(m_val==\"HELP\"){"); 
					out.println(" load_help_msg();");
					out.println("}"); 
					out.println("else{");
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("}else if(m_val==\"DELETE\"){");  
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop_Approve?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
				
				
        	out.println("</Script>");
				
				//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_payment_details()\">"); //load_lock()
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_odi_app_count()\">");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_ODI_ADJUST_APPROVAL\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<INPUT TYPE='Hidden' NAME='hid_odi_bal' VALUE=0>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_odi_app_count' VALUE=0>");////Sandun on 01-07-2009
				out.println("<INPUT TYPE='Hidden' NAME='hid_asign' VALUE=\"\">");//Sandun on 01-07-2009
										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - O D Interest Adjustments Approval 2</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
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
					
					
			/*	out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Payee Name </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='15' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
				out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"get_payment_details(document.Form1.TXT_CLIENT_NAME.value)\"></td>"); //m_help_TXT_APPLICATION_NO
				out.println("<td width='*%'><b>Pending No of Payments Approvals &nbsp;&nbsp;&nbsp; "+m_count_payment_no+"</td>");
				out.println("</tr>"); 
				
				out.println("</table>"); 
      */
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC>Client Code *</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onblur=\"client_help()\" > ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' id=FNO>Finance No *</td>");
				out.println("<td width='40%'><input name=\"LEASE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=lease_help()><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<input type=button name=lea_help value=... class=\"but_input\" onclick=\"lease_help()\" >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_data(document.Form1.LEASE_NO.value)\"></td>"); 
			  out.println("</td>");
				out.println("<td colspan=2><div id ='m_adj_amount'></div></td>");
				 
        //Added by Dineth on 2009-02-13
				 
		 	  out.println("<tr>");  
			  out.println("<td width=\"20%\" valign=top>Remarks</td>");
				out.println("<td width=\"40%\"><textarea name='TXT_REMARK' width=325 height=50 style='width:325;height:50' class='txt_input'></textarea></td>");
				//out.println("<td colspan='2' width=\"*%\">&nbsp;</td>");
				out.println("<td colspan=2><div id ='m_inv_count'></div></td>");
		    out.println("</tr>"); 
		    //end by Dineth on 2009-02-13
			  out.println("</table>");
		    
				
 			  out.println("<table align='center' width='100%' class='table'>"); 

				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
		
			   out.println("</table>");
				  
					out.println("</td></tr><tr>");  
				  out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  	
				  out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
				out.println("</td></tr><tr>");  
				out.println("</tr><tr>");  	
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
			}
						
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
