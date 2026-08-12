import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:31-01-2007
  
public class LAKDL_FA_OP_PRO_rpt_cheque_return_reminder extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1;
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
			stmt1=conn.createStatement();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_INITIAL_CHEQUE_RETURN_REMINDER")){
				
				String m_string="";				
				
					rs1= stmt1.executeQuery("SELECT A.RETURN_NO,"+ //1 
  				 " NVL(B.CLIENT_CODE,'-'),"+//2
					 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'),"+//3
  				 " NVL(B.FACILITY_NO,'-'),"+//4
  				 " NVL(B.DEBTOR_CODE,'-'),"+//5
					 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//6
  				 " A.CHEQUE_NO, "+//7
  				 " NVL(A.DEPOSIT_AMOUNT,0),"+//8
  				 " TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//9
  				 " NVL(A.RETURN_COMMENTS,'-'), "+//10
					 " (SELECT NVL(MAX(LETTER_COUNT),0) FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE RETURN_NO=A.RETURN_NO), "+//11
					 " NVL(A.RECEIPT_NO,'-'), "+//12
           " B.RECEIPT_TYPE "+//13
 					 " FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 					 " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' "+
					 " AND ( A.RETURN_NO NOT IN (SELECT RETURN_NO FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='INITIAL_CHEQ_RETURN_REMINDER' AND LETTER_COUNT <> 0 AND FACILITY_NO=A.FACILITY_NO  ) ) ");

					
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Note : ICRR - Initial Cheque Return Reminder</b></DIV></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>";//1
				m_string=m_string+"<td width='18%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; //7
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"; //8
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Realised Date</b></DIV></td>"; //9
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";//10
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_return_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RETURN_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
				  m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
				  m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REALISED_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" style=\"width: 100px\" maxlength=\"100\">"+rs1.getString(9)+"</td>";
					if(rs1.getString(10).equals("-")){
						m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 100px\" maxlength=\"100\"></td>"; 
					}
					else{
					  m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\""+rs1.getString(10)+"\" style=\"width: 100px\" maxlength=\"100\" disabled ></td>"; 
					}
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LETTER_COUNT_"+chk_nums+"' VALUE=\""+rs1.getInt(11)+"\" >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(5)+"','"+rs1.getString(2)+"','"+rs1.getString(12)+"')\" style='cursor:hand' title='Initial Cheque Return Reminder'  name=BUT_LETTER2 value=\"ICRR\" ></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_FINAL_CHEQUE_RETURN_REMINDER_WITHOUT_REM")){
				
				String m_string="";				
				
				 rs1= stmt1.executeQuery("SELECT DISTINCT A.RETURN_NO, "+//1
  				"  NVL(B.CLIENT_CODE,'-'), "+//2
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'),"+//3
  				"  NVL(B.FACILITY_NO,'-'),"+//4
  				"  NVL(B.DEBTOR_CODE,'-'),"+//5
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//6
  				"  A.CHEQUE_NO, "+//7
  				"  NVL(A.DEPOSIT_AMOUNT,0),"+//8
  				"  TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//9
  				"  NVL(A.RETURN_COMMENTS,'-'), "+//10
					"  NVL(A.RECEIPT_NO,'-'), "+//11
          "  B.RECEIPT_TYPE "+//12
 					"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 					"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' "+
					"  AND ( A.RETURN_NO NOT IN (SELECT RETURN_NO FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='FINAL_CHEQ_RETURN_RM_WITHOUT_R' AND LETTER_COUNT <> 0 AND FACILITY_NO=A.FACILITY_NO  ) ) ");
					
					
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Note : FCRR - Final Cheque Return Reminder Without Remark</b></DIV></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				//m_string=m_string+"<td width='1%'></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>";//1
				m_string=m_string+"<td width='18%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; //7
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"; //8
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Realised Date</b></DIV></td>"; //9
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";//10
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
				  	if(rs1.getString(10).equals("-")){
							chk_nums++;
							if(j==0){
								m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
								j=1;
							}
							else{
								m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
								j=0;
							}
							//m_string=m_string+"<td width='1%'></td>"; 
							m_string=m_string+"<td width='15%' class=div_input onClick=\"show_return_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RETURN_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
  						m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
							m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
							m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
							m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
							m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(8))+"</td>";
							m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REALISED_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" style=\"width: 100px\" maxlength=\"100\">"+rs1.getString(9)+"</td>";
							m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 100px\" maxlength=\"100\"></td>"; 
							m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  >";
							m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2)+"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Final Cheque Return Reminder Without Remark' name=BUT_LETTER2 value=\"FCRR\" ></td>";
							//m_string=m_string+"<td width='*%'></td>";
							m_string=m_string+"</tr>";
						}	
					}
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
				}
				else if(m_chksql.equals("LOAD_FINAL_CHEQUE_RETURN_REMINDER_WITH_REM")){
				
					String m_string="";				
					
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.RETURN_NO, "+//1
  				"  NVL(B.CLIENT_CODE,'-'), "+//2
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'), "+//3
  				"  NVL(B.FACILITY_NO,'-'), "+//4
  				"  NVL(B.DEBTOR_CODE,'-'), "+//5
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//6
  				"  A.CHEQUE_NO, "+//7
  				"  NVL(A.DEPOSIT_AMOUNT,0),"+//8
  				"  TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//9
  				"  NVL(A.RETURN_COMMENTS,'-'), "+//10
					"  NVL(A.RECEIPT_NO,'-'), "+//11
          "  B.RECEIPT_TYPE "+//12
 					"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 					"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' "+
					"  AND ( A.RETURN_NO NOT IN (SELECT RETURN_NO FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='FINAL_CHEQ_RETURN_RM_WITH_R' AND LETTER_COUNT <> 0 AND FACILITY_NO=A.FACILITY_NO   ) ) ");
				
						m_string=m_string+"<table align='center' width='100%' class='table' >";
						m_string=m_string+"<tr class=pdn_txtpos2>";
						m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Note : FCRRW - Final Cheque Return Reminder With Remark</b></DIV></td>";
						m_string=m_string+"</tr>";
						m_string=m_string+"</table>";
						m_string=m_string+"<table align='center' width='100%' class='table' >";
						m_string=m_string+"<tr class=pdn_txtpos2>";
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>";//1
						m_string=m_string+"<td width='18%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
						m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
						m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
						m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; //7
						m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"; //8
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Realised Date</b></DIV></td>"; //9
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";//10
						m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						int chk_nums=0;
						int j=1;
				
						while(rs1.next()){
				  		if(rs1.getString(10).equals("-")){

							}	
							else{
								chk_nums++;							
								if(j==0){
									m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
									j=1;
								}
								else{
									m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
									j=0;
								}
								//m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='15%' class=div_input onClick=\"show_return_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RETURN_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
								m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
								m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
								m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
								m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
								m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(8))+"</td>";
								m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REALISED_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" style=\"width: 100px\" maxlength=\"100\">"+rs1.getString(9)+"</td>";
								m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\""+rs1.getString(10)+"\" style=\"width: 100px\" maxlength=\"100\" disabled ></td>"; 
								m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  >";
								m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2)+"','"+rs1.getString(5)+"','"+rs1.getString(11)+"','"+rs1.getString(10)+"')\" name=BUT_LETTER2 value=\"FCRRW\" style='cursor:hand' title='Final Cheque Return Reminder With Remark' ></td>";
								//m_string=m_string+"<td width='*%'></td>";
								m_string=m_string+"</tr>";
							}
							
						}
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
				}	
				else if(m_chksql.equals("LOAD_FINAL_CHEQUE_RETURN_REMINDER_PERIOD")){
				
					String m_string="";				
					String m_from_date=req.getParameter("from_date");
					String m_to_date=req.getParameter("to_date");
					
					rs1= stmt1.executeQuery("SELECT DISTINCT A.RETURN_NO, "+//--1
  				" NVL(B.CLIENT_CODE,'-'), "+//--2
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'), "+//--3	
  				" NVL(B.FACILITY_NO,'-'), "+//--4
  				" NVL(B.DEBTOR_CODE,'-'), "+//--5
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//--6	
  				" A.CHEQUE_NO, "+//--7
  				" NVL(A.DEPOSIT_AMOUNT,0), "+//--8    
  				" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'), "+//--9
  				" NVL(A.RETURN_COMMENTS,'-'), "+//--10
					" NVL(A.RECEIPT_NO,'-'), "+//--11	
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='INITIAL_CHEQ_RETURN_REMINDER' AND RETURN_NO=A.RETURN_NO AND LETTER_COUNT <> 0) INITIAL_R, "+//--12 Initial count
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='FINAL_CHEQ_RETURN_RM_WITHOUT_R' AND RETURN_NO=A.RETURN_NO AND LETTER_COUNT <> 0) FINAL_WITHOUT_R, "+//--13  final without remark count
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='FINAL_CHEQ_RETURN_RM_WITH_R' AND RETURN_NO=A.RETURN_NO AND LETTER_COUNT <> 0) FINAL_WITH_R, "+//--14 final with remark count
					"  B.RECEIPT_TYPE "+//--15
      		"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 					"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' "+
				  "	AND TO_DATE(TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
					" TO_DATE(TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY  A.RETURN_NO ");
					
				
						m_string=m_string+"<table align='center' width='100%' class='table' >";
						m_string=m_string+"<tr class=pdn_txtpos2>";
						m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Note : ICRR - Initial Cheque Return Reminder / FCRR - Final Cheque Return Reminder Without Remark / FCRRW - Final Cheque Return Reminder With Remark</b></DIV></td>";
						m_string=m_string+"</tr>";
						m_string=m_string+"</table>";
						m_string=m_string+"<table align='center' width='100%' class='table' >";
						m_string=m_string+"<tr class=pdn_txtpos2>";
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>";//1
						m_string=m_string+"<td width='18%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
						m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
						m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
						m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; //7
						m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"; //8
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Realised Date</b></DIV></td>"; //9
						m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";//10
						m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						int chk_nums=0;
						int j=1;
				
						while(rs1.next()){
								chk_nums++;												
								if(j==0){
									m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
									j=1;
								}
								else{
									m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
									j=0;
								}
								//m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='15%' class=div_input onClick=\"show_return_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RETURN_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
								m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
								m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
								m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
								m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
								m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(8))+"</td>";
								m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REALISED_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" style=\"width: 100px\" maxlength=\"100\"> "+rs1.getString(9)+"</td>";
								if(rs1.getString(10).equals("-")){
										m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 100px\" maxlength=\"100\"></td>"; 
								}
								else{
					  				m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\""+rs1.getString(10)+"\" style=\"width: 100px\" maxlength=\"100\" disabled ></td>"; 
								}
								m_string=m_string+"<td width='*%' class=div_input>";
								
								if(rs1.getInt(12)>0){
									  m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change('"+chk_nums+"')\" checked ><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Initial Cheque Return Reminder' name=BUT_LETTER1 value=\"ICRR\" >";
								}
								else{
									  m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Initial Cheque Return Reminder' name=BUT_LETTER1 value=\"ICRR\" >";
								}
								
								
								if(rs1.getInt(14)>0){
									 m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change3('"+chk_nums+"')\" checked><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"','"+rs1.getString(10)+"')\" style='cursor:hand' title='Final Cheque Return Reminder With Remark' name=BUT_LETTER3 value=\"FCRRW\" >";
								}
								else{
								  m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change3('"+chk_nums+"')\"><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"','"+rs1.getString(10)+"')\" style='cursor:hand' title='Final Cheque Return Reminder With Remark' name=BUT_LETTER3 value=\"FCRRW\" >";
								}
								
								if(rs1.getInt(12)>0 && rs1.getInt(14)>0 && rs1.getInt(13)==0 ){
									m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change2('"+chk_nums+"')\" disabled ><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Final Cheque Return Reminder Without Remark' name=BUT_LETTER2 value=\"FCRR\" disabled >";
								}
								else if(rs1.getInt(12)>0 && rs1.getInt(14)>0 && rs1.getInt(13)>0 ){
									m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change2('"+chk_nums+"')\" checked disabled ><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Final Cheque Return Reminder Without Remark' name=BUT_LETTER2 value=\"FCRR\" disabled >";
								}
								else if(rs1.getInt(13)>0){
									m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change2('"+chk_nums+"')\" checked ><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Final Cheque Return Reminder Without Remark' name=BUT_LETTER2 value=\"FCRR\" >";
								}
								else{
								  m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change2('"+chk_nums+"')\"><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(2) +"','"+rs1.getString(5)+"','"+rs1.getString(11)+"')\" style='cursor:hand' title='Final Cheque Return Reminder Without Remark' name=BUT_LETTER2 value=\"FCRR\" >";
								}
								//m_string=m_string+"<td width='*%'></td>";
								m_string=m_string+"</td></tr>";
							
						}
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
				}	
				else if(m_chksql.equals("LOAD_CHEQUE_RETURN_LEGAL_LETTER")){
				 
				String m_string="";				
				
					rs1= stmt1.executeQuery("SELECT DISTINCT A.RETURN_NO, "+//1
  				 " NVL(B.CLIENT_CODE,'-'),"+//2
					 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'),"+//3
  				 " NVL(B.FACILITY_NO,'-'),"+//4
  				 " NVL(B.DEBTOR_CODE,'-'),"+//5
					 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//6
  				 " A.CHEQUE_NO, "+//7
  				 " NVL(A.DEPOSIT_AMOUNT,0),"+//8
  				 " TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//9
  				 " NVL(A.RETURN_COMMENTS,'-'),"+ //10
					 " (SELECT NVL(MAX(LETTER_COUNT),0) FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE RETURN_NO=A.RETURN_NO), "+//11
					 " NVL(A.RECEIPT_NO,'-'), "+//12
           " B.RECEIPT_TYPE "+//13
 					 " FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 					 " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.REC_STATUS='C' "+
					 " AND ( A.RETURN_NO NOT IN (SELECT RETURN_NO FROM "+m_schema_name+".FA_OP_PRO_CHQ_RETURN_LETTER WHERE LETTER_NAME='CHEQ_RETURN_LEGAL_LETTER' AND LETTER_COUNT <> 0 AND (CLIENT_CODE=B.CLIENT_CODE) AND (FACILITY_NO=B.FACILITY_NO) ) ) ");
					
					 
				/*m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Note : ICRR - Initial Cheque Return Reminder</b></DIV></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";*/
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>";//1
				m_string=m_string+"<td width='18%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; //7
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"; //8
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Realised Date</b></DIV></td>"; //9
				//m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";//10
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_return_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RETURN_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REALISED_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" style=\"width: 100px\" maxlength=\"100\">"+rs1.getString(9)+"</td>";
					/*if(rs1.getString(10).equals("-")){
						m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 100px\" maxlength=\"100\"></td>"; 
					}
					else{
					  m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RETURN_COMMENT_"+chk_nums+"' VALUE=\""+rs1.getString(10)+"\" style=\"width: 100px\" maxlength=\"100\" disabled ></td>"; 
					}*/
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LETTER_COUNT_"+chk_nums+"' VALUE=\""+rs1.getInt(11)+"\" >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(5)+"','"+rs1.getString(2)+"','"+rs1.getString(12)+"','"+rs1.getString(4)+"')\" style='cursor:hand' style='width:80' name=BUT_LETTER2 value=\"Legal  Letter\" ></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}      
//########################  Invoice Reminders  #########################################################

			else if(m_chksql.equals("LOAD_PRE_INVOICE_REMINDER")){
			
			String m_string="";				

				rs1= stmt1.executeQuery(" SELECT DISTINCT  "+
  			" NVL(A.CLIENT_CODE,'-'),"+//1
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
			  " NVL(A.FACILITY_NO,'-'),"+//3
			  " NVL(B.DEBTOR_CODE,'-'),"+//4
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
				" "+m_schema_name+".FA_GET_INVOICE_POD(B.INVOICE_SEQ_NO) "+//6
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND B.BALANCE_AMOUNT > 0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND (B.DUE_DATE-TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))=10 "+
				" AND (B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
				" WHERE LETTER_NAME='PRE_INVOICE_REMINDER' "+
				" AND LETTER_COUNT <> 0 "+
				" AND CLIENT_CODE=A.CLIENT_CODE "+
				" AND FACILITY_NO=A.FACILITY_NO) ) ");	

			m_string=m_string+"<table align='center' width='100%' class='table' >";
			m_string=m_string+"<tr class=pdn_txtpos2>";
			m_string=m_string+"<td width='25%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
			m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
			m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
			m_string=m_string+"<td width='25%' ><DIV class=div_input><b></b></DIV></td>";//6 
			
			m_string=m_string+"<td width='*%'></td>";
			m_string=m_string+"</tr>";
			int chk_nums=0;
			int j=1;
			while(rs1.next()){
				chk_nums++;
				if(j==0){
					m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
					j=1;
				}
				else{
					m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
					j=0;
				}
				m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>";
				m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>";
				m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
				m_string=m_string+"<td width='*%' class=div_input>";
				m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Pre Reminder'  name=BUT_LETTER2 value=\"Pre Reminder\" style='width: 100px' ></td>";
				m_string=m_string+"</tr>";
			}
			m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
			m_string=m_string+"</table>";
			out.println(m_string);
		}	
		else if(m_chksql.equals("LOAD_FIRST_INVOICE_REMINDER")){
			
			String m_string="";				

				rs1= stmt1.executeQuery(" SELECT DISTINCT "+
  			" NVL(A.CLIENT_CODE,'-'),"+//1
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
			  " NVL(A.FACILITY_NO,'-'),"+//3
			  " NVL(B.DEBTOR_CODE,'-'),"+//4
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-') "+//5
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE A.BATCH_NO=B.BATCH_NO AND B.BALANCE_AMOUNT > 0 AND B.INVOICE_STATUS='CONF' "+
				" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))<=(-1) "+
				" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))>(-14) "+	
				" AND ( B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='FIRST_INVOICE_REMINDER' AND LETTER_COUNT <> 0 AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO) ) ");		
					

			m_string=m_string+"<table align='center' width='100%' class='table' >";
			m_string=m_string+"<tr class=pdn_txtpos2>";
			//m_string=m_string+"<td width='1%'></td>"; 
			//m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";//1
			m_string=m_string+"<td width='25%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
			m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
			m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
			m_string=m_string+"<td width='*%'></td>";
			m_string=m_string+"</tr>";
			int chk_nums=0;
			int j=1;
			while(rs1.next()){
				chk_nums++;
				if(j==0){
					m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
					j=1;
				}
				else{
					m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
					j=0;
				}
				//<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  >
				m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>";
				m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>";
				m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
				m_string=m_string+"<td width='*%' class=div_input> ";
				m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='1st Reminder'  name=BUT_LETTER2 value=\"1st Reminder\" style='width: 100px' ></td>";
				//m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
			}
			m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
			m_string=m_string+"</table>";
			out.println(m_string);
		}	
			else if(m_chksql.equals("LOAD_SECOND_INVOICE_REMINDER")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT DISTINCT  "+
    			" NVL(A.CLIENT_CODE,'-'),"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-') "+//5
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO AND B.BALANCE_AMOUNT > 0 AND B.INVOICE_STATUS='CONF' "+
					" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))<=(-14) "+
 					" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))>(-30) "+
					" AND ( B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='SECOND_INVOICE_REMINDER' AND LETTER_COUNT <> 0 AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO) ) ");		
					
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
					m_string=m_string+"<td width='*%' class=div_input> ";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='2nd Reminder'  name=BUT_LETTER2 value=\"2nd Reminder\" style='width: 100px' ></td>";
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_FINAL_INVOICE_REMINDER")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT DISTINCT  "+
    			" NVL(A.CLIENT_CODE,'-'),"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-') "+//5
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO AND B.BALANCE_AMOUNT > 0 AND B.INVOICE_STATUS='CONF' "+
 					" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))<=(-30) "+
					" AND ( B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='FINAL_INVOICE_REMINDER' AND LETTER_COUNT <> 0 AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO) ) ");			

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='25%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
					m_string=m_string+"<td width='*%' class=div_input> ";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Final Reminder'  name=BUT_LETTER2 value=\"Final Reminder\" style='width: 110px' ></td>";
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}    
			else if(m_chksql.equals("LOAD_INVOICE_REMINDER_PERIOD")){
				
				String m_string="";				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");

					rs1= stmt1.executeQuery(" SELECT DISTINCT  "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='PRE_INVOICE_REMINDER' AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO AND DEBTOR_CODE=B.DEBTOR_CODE AND LETTER_COUNT <> 0),"+//6  
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='FIRST_INVOICE_REMINDER' AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO AND DEBTOR_CODE=B.DEBTOR_CODE AND LETTER_COUNT <> 0),"+//7  
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='SECOND_INVOICE_REMINDER' AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO AND DEBTOR_CODE=B.DEBTOR_CODE AND LETTER_COUNT <> 0), "+//8
					" (SELECT COUNT(LETTER_NAME) FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER WHERE LETTER_NAME='FINAL_INVOICE_REMINDER' AND CLIENT_CODE=A.CLIENT_CODE AND FACILITY_NO=A.FACILITY_NO AND DEBTOR_CODE=B.DEBTOR_CODE AND LETTER_COUNT <> 0) "+//9
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO AND B.BALANCE_AMOUNT > 0  AND B.INVOICE_STATUS='CONF'  AND "+
					" TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
					" TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY CLIENT_CODE "+
					"	 ");	
 					//" AND (TO_DATE(B.DUE_DATE,'DD-MM-YYYY')-(SELECT TO_DATE(SYSDATE,'DD-MM-YYYY') FROM DUAL))=(-14) ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";//4
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";//6 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					//m_string=m_string+"<td width='1%'></td>"; 
					//m_string=m_string+"<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
					m_string=m_string+"<td width='*%' class=div_input> ";
					
					if(rs1.getInt(6)>0){
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE1_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change1('"+chk_nums+"')\" checked > ";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Pre Reminder'  name=BUT_LETTER1 value=\"Pre Reminder\" style='width: 80px'>";
					}
					else {
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE1_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change1('"+chk_nums+"')\"  > ";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Pre Reminder'  name=BUT_LETTER1 value=\"Pre Reminder\" style='width: 80px' >";
					}
					if(rs1.getInt(7)>0){
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change2('"+chk_nums+"')\" checked >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='1st Reminder'  name=BUT_LETTER2 value=\"1st Reminder\" style='width: 80px'>";
					}
					else {
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE2_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change2('"+chk_nums+"')\"  >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='1st Reminder'  name=BUT_LETTER2 value=\"1st Reminder\" style='width: 80px'>";
					}
					if(rs1.getInt(8)>0){
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change3('"+chk_nums+"')\" checked >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='2nd Reminder'  name=BUT_LETTER3 value=\"2nd Reminder\" style='width: 80px' >";
					}
					else {
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change3('"+chk_nums+"')\"  >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='2nd Reminder'  name=BUT_LETTER3 value=\"2nd Reminder\" style='width: 80px'>";
					}
					if(rs1.getInt(9)>0){
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE4_"+chk_nums+"' VALUE=\"Y\" onClick=\"check_change4('"+chk_nums+"')\" checked >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter4('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Final Reminder'  name=BUT_LETTER4 value=\"Final Reminder\" style='width: 90px'>";
					}
					else {
					m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE4_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change4('"+chk_nums+"')\"  >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter4('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\" style='cursor:hand' title='Final Reminder'  name=BUT_LETTER4 value=\"Final Reminder\" style='width: 90px'>";
					}
					m_string=m_string+"</td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_REMINDER_LETTERS_PERIOD")){
		
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_letter_type=req.getParameter("letter_type");
				
				if(m_letter_type.equals("PR")){
				rs1= stmt1.executeQuery(" SELECT "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" B.INVOICE_SEQ_NO,"+//6
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//7
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
					" B.INVOICE_NO, "+//9
					""+m_schema_name+".FA_GET_INVOICE_POD_STATUS(B.INVOICE_SEQ_NO) "+//10
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0  "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
 					" WHERE LETTER_NAME='PRE_INVOICE_REMINDER' AND INVOICE_SEQ_NO=B.INVOICE_SEQ_NO ) "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')-10>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')-10<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					//" AND (B.DUE_DATE-10)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					"	ORDER BY A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE ");	
				}
				else if(m_letter_type.equals("1R")){
				rs1= stmt1.executeQuery(" SELECT "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" B.INVOICE_SEQ_NO,"+//6
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//7
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
					" B.INVOICE_NO, "+//9
					""+m_schema_name+".FA_GET_INVOICE_POD_STATUS(B.INVOICE_SEQ_NO) "+//10
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0  "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
 					" WHERE LETTER_NAME='FIRST_INVOICE_REMINDER' AND INVOICE_SEQ_NO=B.INVOICE_SEQ_NO ) "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+1>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+1<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					//" AND (B.DUE_DATE+1)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					"	ORDER BY A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE ");	
				}
				else if(m_letter_type.equals("2R")){
				rs1= stmt1.executeQuery(" SELECT "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" B.INVOICE_SEQ_NO,"+//6
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//7
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
					" B.INVOICE_NO, "+//9
					""+m_schema_name+".FA_GET_INVOICE_POD_STATUS(B.INVOICE_SEQ_NO) "+//10
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0  "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
 					" WHERE LETTER_NAME='SECOND_INVOICE_REMINDER' AND INVOICE_SEQ_NO=B.INVOICE_SEQ_NO ) "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					//" AND (B.DUE_DATE+14)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					"	ORDER BY A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE ");	
				}
				else if(m_letter_type.equals("FR")){
				rs1= stmt1.executeQuery(" SELECT "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" B.INVOICE_SEQ_NO,"+//6
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//7
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
					" B.INVOICE_NO, "+//9
					""+m_schema_name+".FA_GET_INVOICE_POD_STATUS(B.INVOICE_SEQ_NO) "+//10
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0  "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
 					" WHERE LETTER_NAME='FINAL_INVOICE_REMINDER' AND INVOICE_SEQ_NO=B.INVOICE_SEQ_NO ) "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+30>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+30<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					//" AND (B.DUE_DATE+30)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					"	ORDER BY A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE ");	
				}
				else if(m_letter_type.equals("ALL")){
				rs1= stmt1.executeQuery(" SELECT "+
    			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,"+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//2
  			  " NVL(A.FACILITY_NO,'-'),"+//3
  			  " NVL(B.DEBTOR_CODE,'-'),"+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//5
					" B.INVOICE_SEQ_NO,"+//6
					" TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'), "+//7
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
					" B.INVOICE_NO, "+//9
					""+m_schema_name+".FA_GET_INVOICE_POD_STATUS(B.INVOICE_SEQ_NO) "+//10
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
 					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND B.BALANCE_AMOUNT>0  "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					"	ORDER BY A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE ");	
				}
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><B>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='7%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='7%' ><DIV class=div_input><b>Due Date</b></DIV></td>");
				out.println("<td width='7%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>");
				out.println("<td width='30%'></td>");
				out.println("<td width='*%'><DIV class=div_input><b>Hold Invoice</b></DIV></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					
					if(rs1.getDouble(10)==0){
						chk_nums++;
						out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>");
						out.println("<td width='7%' class=div_input onClick=\"show_invoice_details_ref_no('"+rs1.getString(6)+"')\" style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\"><u>"+rs1.getString(9)+"</u></td>");
						out.println("<td width='7%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='7%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("<td width='30%' class=div_input> ");
						//if(rs1.getDouble(10)>0){
						//out.println("<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' >");
						//}
						//else{
						out.println("<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' checked>");
						//}
						if(m_letter_type.equals("PR")){
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER1\" value=\"Pre Reminder\">");
						}
						else if(m_letter_type.equals("1R")){
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER1\" value=\"1st Reminder\">");
						}
						else if(m_letter_type.equals("2R")){
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+rs1.getString(6)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER1\" value=\"2nd Reminder\">");
						}
						else if(m_letter_type.equals("FR")){
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter4('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+rs1.getString(6)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER1\" value=\"Final Reminder\">");
						}
						else if(m_letter_type.equals("ALL")){
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER1\" value=\"Reminders\">");
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER2\" value=\"1st Reminder\">");
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+rs1.getString(6)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER3\" value=\"2nd Reminder\">");
						out.println("<input class=\"div_input\" type=\"button\" onclick=\"Generate_Letter4('"+rs1.getString(1)+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"','"+rs1.getString(6)+"','"+m_from_date+"','"+m_to_date+"')\" name=\"BUT_LETTER4\" value=\"Final Reminder\">");
						}
						out.println("</td>");
						out.println("<td width='*%' class=div_input><INPUT TYPE='CHECKBOX' name='TXT_HOLD_INVOICE_"+chk_nums+"'></td>");
						out.println("</tr>");
					}
				}
				out.println("<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">");
				out.println("</table>");
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

