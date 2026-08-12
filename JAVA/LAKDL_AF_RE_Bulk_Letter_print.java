//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Bulk_Letter_print extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2,stmt1,stmt_view_let,stmt_download_pdf;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs2,rs1,rs_view_file,rs_download_pdf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			
			
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "COLLECTION_OFFICER";	
			String m_order_by_type = "ASC";
			
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_team_id=req.getParameter("team_id");
				String m_sub_team_id=req.getParameter("sub_team_id");
				String m_user_id=req.getParameter("user_id");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_COLL_MOVE_RPT_AGE(:1,:2,:3,:4,:5);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_team_id);
					callstmt1.setString(3,m_sub_team_id);
					callstmt1.setString(4,m_user_id);
					callstmt1.setString(5,m_username);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			else if(m_chksql.equals("DOWNLOAD_PDF"))
			{
				
				
				stmt_download_pdf=conn.createStatement();
				String finance_no=req.getParameter("FINANCE_NO");
				String letter_category_id=req.getParameter("LETTER_CATEGORY_ID");
				String letter_id=req.getParameter("LETTER_ID");
				
				
				rs_download_pdf = stmt_download_pdf.executeQuery ("SELECT FILE_PATH "+
					" FROM "+m_schema_name+".AF_CO_BULK_LETTERS WHERE"+
					" FINANCE_NO='"+finance_no+"' AND LETTER_CATEGORY_ID='"+letter_category_id+"' ");
				//--AND LETTER_ID='"+letter_id+"'");
				String path="";
				String file="";
				if(rs_download_pdf.next())
				{
					path=rs_download_pdf.getString(1);
					//file=rs.getString(2);
				}	
				ServletOutputStream stream = null;
				BufferedInputStream buf = null;
				
				try{
					
					stream = res.getOutputStream();
					File doc = new File(path);
					
					
					
					res.setContentType("application/octet-stream");
					
					
					FileInputStream fileInput = new FileInputStream(doc);
					buf = new BufferedInputStream(fileInput);
					int readBytes = 0;
					byte[] bufArray = new byte[1024]; 	
					
					res.setContentLength( (int) doc.length() );
					res.setHeader("Content-Disposition", "attachment; filename=" + doc.getName());
					res.setHeader("Cache-Control", "cache");
					res.setHeader("Cache-Control", "must-revalidate");	
					res.setHeader( "Pragma", "public" );
					
					
					while((readBytes = buf.read(bufArray,0,bufArray.length)) != -1)
					{
						stream.write(bufArray,0,bufArray.length);
					}
					stream.flush();
					
				} 
				
				catch (FileNotFoundException fnoe)
				{
					
					res.setContentType("text/html"); 	
					
					out.println();
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Attachment not found');");
					// out.println("window.history.back();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<BODY onload='displaymsg();'>Error:"+fnoe.toString()+"</BODY>");
					out.println("</HTML>");
					out.flush();
					
					
				}catch (IOException ioe)
				{
					
					res.setContentType("text/html"); 	
					
					out.println();
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error Occured While Reading The File..');");
					out.println("window.history.back();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<BODY onload='displaymsg();'>Error:"+ioe.toString()+"</BODY>");
					out.println("</HTML>");
					out.flush();
					
					
				} finally 
				{
					
					if(stream != null)
						stream.close();
					if(buf != null)
						buf.close();
				}
				
			}	
			
			
			else if(m_chksql.equals("LOAD_GENERATED_LETTERS"))
			{
				try
				{
					
					String m_date=req.getParameter("date");
					String m_team_id=req.getParameter("TEAM_ID");
					String m_sub_team_id=req.getParameter("SUBTEAM_ID");
					String m_user_id=req.getParameter("user_id");
					
					stmt_view_let=conn.createStatement();
					
					/*			
					a.letter_print_status, a.letter_sent_status, a.generated_date,
					a.print_date, a.file_path, a.date_to_be_generated,
					a.date_to_be_printed, a.finance_no, a.client_code
								
					*/			
					
					rs_view_file=stmt_view_let.executeQuery
						(
						" SELECT A.FINANCE_NO,A.LETTER_CATEGORY_ID,A.LETTER_ID,A.CLIENT_CODE,A.FILE_PATH,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
						" A.LETTER_GENERATED_STATUS,TO_CHAR(A.GENERATED_DATE,'DD-MM-YYYY'),A.LETTER_PRINT_STATUS,"+m_schema_name+".AF_CO_LET_DESC(A.LETTER_CATEGORY_ID) "+
						" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_BULK_LETTERS A "+
						" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
						" AND  A.GENERATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "
						);
					
					
					
					
					String m_view_file="";
					m_view_file=m_view_file+"<table align='center' width='100%' class='table' >";
					m_view_file=m_view_file+"<tr class=pdn_txtpos2>";
					m_view_file=m_view_file+"<td width='*%' ><DIV class=div_input><b>View :Bulk Letter Prints </b></DIV></td>";
					m_view_file=m_view_file+"</tr>";
					m_view_file=m_view_file+"</table>";
					m_view_file=m_view_file+"<table align='center' width='100%' class='table' >";
					m_view_file=m_view_file+"<tr class=pdn_txtpos2>";
					m_view_file=m_view_file+"<td width='15%' ><DIV class=div_input><b>Finance No</b></DIV></td>";//1
					m_view_file=m_view_file+"<td width='15%' ><DIV class=div_input><b>Letter Category</b></DIV></td>";//1
					m_view_file=m_view_file+"<td width='30%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
					m_view_file=m_view_file+"<td width='12%' ><DIV class=div_input align=right ><b>letter Generated Status</b></DIV></td>";//4					
					m_view_file=m_view_file+"<td width='12%' ><DIV class=div_input align=right ><b>Generated Date</b></DIV></td>";//4					
					m_view_file=m_view_file+"<td width='*%' ><DIV class=div_input align=center ><b>Print Status</b></DIV></td>";//5
					m_view_file=m_view_file+"<td width='*%' ><DIV class=div_input align=center ><b>View</b></DIV></td>";//5
					m_view_file=m_view_file+"</tr>";
					
					
					int chk_nums=0;
					int j=0;
					while(rs_view_file.next())
					{
						chk_nums++;
						
						if(j==0)
						{
							m_view_file=m_view_file+"<tr bgcolor=\"#FFFFFF\">";
							j=1;
						}
						else
						{
							m_view_file=m_view_file+"<tr bgcolor=\"#C0C0C0\" >";
							j=0;
						}
						
						
						
						m_view_file=m_view_file+"<td width='15%' ><DIV class=div_input>"+rs_view_file.getString(1)+"</DIV></td>";//1
						m_view_file=m_view_file+"<td width='15%' ><DIV class=div_input>"+rs_view_file.getString(2)+"</DIV></td>";//1
						m_view_file=m_view_file+"<td width='30%' ><DIV class=div_input>"+rs_view_file.getString(6)+"</DIV></td>";//3
						m_view_file=m_view_file+"<td width='12%' ><DIV class=div_input align=right >"+rs_view_file.getString(7)+"</DIV></td>";//4					
						m_view_file=m_view_file+"<td width='12%' ><DIV class=div_input align=right >"+rs_view_file.getString(8)+"</DIV></td>";//4					
						m_view_file=m_view_file+"<td width='*%' ><DIV class=div_input align=center >"+rs_view_file.getString(9)+"</DIV></td>";//5
						m_view_file=m_view_file+"<td width='*%' ><DIV class=div_input align=center ><input type=\"button\" class='but_input' value=\"View\" onclick=\"open_pdf('"+rs_view_file.getString(1)+"','"+rs_view_file.getString(2)+"','"+rs_view_file.getString(3)+"')\" > </b></DIV></td>";//5
						m_view_file=m_view_file+"</tr>";
					}	
					m_view_file=m_view_file+"</table>";
					m_view_file=m_view_file+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS_VIEW_PDF' VALUE="+chk_nums+">";
					out.println(m_view_file);
				}
				catch(Exception e)
				{
					out.println(e.toString());
				}	
				
			}
			else if(m_chksql.equals("LOAD_TERMINATION_LETTER"))
			{
				
				try
				{
					
					String m_string="";				
					String m_finance_no=req.getParameter("finance_no");
					String m_letter_type="";
					String client_code=req.getParameter("CLIENT_CODE");
					String m_letter_category=req.getParameter("LETTER_CATEGORY");
					
					/*
					    if(m_letter_category!=null)
						{	
							if(m_letter_category.equals("LOT"))
							{
								
							}
							else if(m_letter_category.equals("NOT"))
							{
								
							}else if(m_letter_category.equals("LOT_1ST_RE"))
							{
							}
							else if(m_letter_category.equals("LOT_2ND_RE"))
							{
							}
							else if(m_letter_category.equals("LOT_3RD_RE"))
							{
							}	
							else if(m_letter_category.equals("NOT_1ST_RE"))
							{
							}
							else if(m_letter_category.equals("NOT_2ND_RE"))
							{
							}	
							else if(m_letter_category.equals("NOT_3RD_RE"))
							{	
							}	
						}
						
						*/
					
					
					stmt1=conn.createStatement();
					stmt=conn.createStatement();
					
					String query="";
					
					if(m_letter_category!=null)
					{	
						if(m_letter_category.equals("LOT"))
						{

							query="SELECT A.FINANCE_NO,A.CLIENT_CODE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) ,SUM(A.BALANCE_TO_BE_RECEIVED) BALANCE, "+
								" B.TRANSACTION_TYPE  "+
								" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE  A.FINANCE_NO=B.FINANCE_NO ";
							
							if(m_finance_no.equals(""))
							{
								//use clientcode specific applications
								query=query+"AND B.CLIENT_CODE ='"+client_code+"' AND APPLICATION_STATUS = 'ACTIVATED'  ";
								
							}
							
							query=query+" AND  TO_DATE(A.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
								" AND A.ACTIVE_STATUS='Y' "+
								" AND UPPER(A.FINANCE_NO)  LIKE UPPER('%"+m_finance_no+"%') "+
								" AND A.BALANCE_TO_BE_RECEIVED >0 "+
								" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,B.TRANSACTION_TYPE  ";
							
							
						     out.println(query);	
							
							
							
							
						}
						else if(m_letter_category.equals("NOT"))
						{
							query="SELECT A.FINANCE_NO,A.CLIENT_CODE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) ,SUM(A.BALANCE_TO_BE_RECEIVED) BALANCE, "+
								" B.TRANSACTION_TYPE  "+
								" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE  A.FINANCE_NO=B.FINANCE_NO ";
							
							if(m_finance_no.equals(""))
							{
								//use clientcode specific applications
								query=query+"AND B.CLIENT_CODE ='"+client_code+"' AND APPLICATION_STATUS = 'ACTIVATED'  ";
								
							}
							
							query=query+" AND  TO_DATE(A.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
								" AND A.ACTIVE_STATUS='Y' "+
								" AND UPPER(A.FINANCE_NO)  LIKE UPPER('%"+m_finance_no+"%') "+
								" AND A.BALANCE_TO_BE_RECEIVED >0 "+
								" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,B.TRANSACTION_TYPE  ";
							
							//out.println(query);
							
							
						}
						else if(m_letter_category.equals("1ST_RE"))
						{
						}
						else if(m_letter_category.equals("2ND_RE"))
						{
						}
						rs1= stmt1.executeQuery(query);
						
					}
					
					
					
					
					
					
					
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Notice : Bulk Letter Print </b></DIV></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					m_string=m_string+"<table align='center' width='100%' class='table' >";
					m_string=m_string+"<tr class=pdn_txtpos2>";
					m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Finance No</b></DIV></td>";//1
					m_string=m_string+"<td width='30%' ><DIV class=div_input><B>Client Name</b></DIV></td>";//3
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>Total Arrears</b></DIV></td>";//4					
					m_string=m_string+"<td width='*%' ><DIV class=div_input align=center ><b>NOT Letter</b></DIV></td>";//5
					/*
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>1st reminder </b></DIV></td>";//6
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>1st reminder Date </b></DIV></td>";//7
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>2nd reminder </b></DIV></td>";//8	
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>2nd reminder Date </b></DIV></td>";//6
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>3rd reminder </b></DIV></td>";//8	
					m_string=m_string+"<td width='12%' ><DIV class=div_input align=right ><b>3rd reminder Date</b></DIV></td>";//8
					*/
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					int chk_nums=0;
					int j=0;
					String M_NOT_STATUS="N";
					String M_LOT_STATUS="N";
					//boolean more=rs1.next();
					//if(more)
					//{
					while(rs1.next())
					{	
						chk_nums++;												
						
						if(rs1.getString(5).equals("FINLEASE"))
						{
							m_letter_type="NOT_FL";
						}
						else if(rs1.getString(5).equals("HIREPURCH"))
						{
							m_letter_type="NOT_HP";
						}
						else
						{//added by nuwan de silva for reference to the other types tempory use on 08-11-07
							m_letter_type="NOT_FL";
						}
						
						rs= stmt.executeQuery(" SELECT  "+
							" NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS('"+m_finance_no+"','NOT'),'N') ,"+
							" NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS('"+m_finance_no+"','LOT'),'N')  FROM DUAL ");
						
						
						
						boolean more_days=rs.next();
						
						
						boolean lot_flag=false;
						int num_days=0;
						
						if(more_days)
						{
							M_NOT_STATUS=rs.getString(1);
							M_LOT_STATUS=rs.getString(2);
						}
						
						
						
						
						if(j==0)
						{
							m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
							j=1;
						}
						else
						{
							m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
							j=0;
						}
						
						
						//m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
						m_string=m_string+"<td width='30%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
						m_string=m_string+"<td width='12%' class=div_input onClick=\"\" align=right ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\"\">"+nf.format(rs1.getDouble(4))+"</td>";
						//m_string=m_string+"<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(16)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(16)+"\"><u>"+rs1.getString(17)+"</u></td>";
						m_string=m_string+"<td width='*%' class=div_input style=\"text-align:center\" > &nbsp;&nbsp;&nbsp;&nbsp; ";
						
						
						//if(M_NOT_STATUS.equals("N") && M_LOT_STATUS.equals("N")) 
						//{
						/*
						
						m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"\"  ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Notice of Termination' name=BUT_LETTER1 value=\"NOT\"  >";
						m_string=m_string+" &nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX_Sinhala('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand; width:100px;' title='Notice of Termination' name=BUT_LETTER1_S value=\"NOT SINHALA\"  >"; // Added by Udara Somathilake on 13-05-2010
						m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"\" ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterY('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Letter of Termination' name=BUT_LETTER3 value=\"LOT\"  disabled >";							
						*/
						
						m_string=m_string+"<INPUT TYPE='CHECKBOX' id='TXT_APPROVE_TYPE_"+chk_nums+"' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"\"  >";
						//m_string=m_string+" &nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX_Sinhala('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand; width:100px;' title='Notice of Termination' name=BUT_LETTER1_S value=\"NOT SINHALA\"  >"; // Added by Udara Somathilake on 13-05-2010
						//m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"\" ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterY('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Letter of Termination' name=BUT_LETTER3 value=\"LOT\"  disabled >";							
						
						
						
						//} 
						
						
						
						/* temparily comment
						
						if(M_NOT_STATUS.equals("Y") && M_LOT_STATUS.equals("Y")) 
						{
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"\" checked  ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Notice of Termination' name=BUT_LETTER1 value=\"NOT\"  >";
							m_string=m_string+" &nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX_Sinhala('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand; width:100px;' title='Notice of Termination' name=BUT_LETTER1_S value=\"NOT SINHALA\"  >"; // Added by Udara Somathilake on 13-05-2010
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"\" checked ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterY('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Letter of Termination' name=BUT_LETTER3 value=\"LOT\" disabled >";				
							
						} 
						
						
						else if(M_NOT_STATUS.equals("Y") && M_LOT_STATUS.equals("N") ) 
						{
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"'  VALUE=\"Y\" onClick=\"\" checked  ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Notice of Termination' name=BUT_LETTER1 value=\"NOT\"  >";
							m_string=m_string+" &nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX_Sinhala('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand; width:100px;' title='Notice of Termination' name=BUT_LETTER1_S value=\"NOT SINHALA\"  >"; // Added by Udara Somathilake on 13-05-2010
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"\"          ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterY('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Letter of Termination' name=BUT_LETTER3 value=\"LOT\"  >";							
							
						} 
						if(M_NOT_STATUS.equals("N") && M_LOT_STATUS.equals("N")) 
						{
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"Y\" onClick=\"\"  ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Notice of Termination' name=BUT_LETTER1 value=\"NOT\"  >";
							m_string=m_string+" &nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterX_Sinhala('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand; width:100px;' title='Notice of Termination' name=BUT_LETTER1_S value=\"NOT SINHALA\"  >"; // Added by Udara Somathilake on 13-05-2010
							m_string=m_string+"<INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE3_"+chk_nums+"' VALUE=\"Y\" onClick=\"\" ><input class=\"but_input\" type=\"button\" onclick=\"Generate_LetterY('"+rs1.getString(5)+"','"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' title='Letter of Termination' name=BUT_LETTER3 value=\"LOT\"  disabled >";							
							
						} 
						
						*/
						
						m_string=m_string+"</td>";
						//m_string=m_string+"<td width='*%'></td>";
						m_string=m_string+"</tr>";
						
					}
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					out.println(m_string);
				}catch(Exception e)
				{
					e.printStackTrace();
				}	
				
			}
			else if(m_chksql.equals("main_page"))
			{
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection Process - Bulk Print Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				//out.println("       <SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/bulk_print_generation.js'></SCRIPT>");  //based on the tab this js file will be replaced either by bulk_print_generation --> bulk_print_generation_view
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function load_main_bulk_print_view()");
				out.println("{ ");
				out.println("   document.Form1.hid_num.value=3;");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=main_printed_file_view_page\";");
				out.println("   load_interface(m_url,'NORM');");
				out.println("} ");
				
				
				
				out.println("function load_main_bulk_print()");	
				out.println("{  ");
				out.println("   document.Form1.hid_num.value=13;");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=Bulk_print_letter_generation\";");
				out.println("   load_interface(m_url,'NORM');");
				
				out.println("} ");
				
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value==\"G4\"){");
				out.println("              help_client();");
				out.println("			}");
				out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_assig.value==\"G6\"){");
				out.println("               help_finance_no();");
				out.println("			}");
				out.println("			else if(data_vec.length==0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_assig.value=='M_CLIENT' ){");
				out.println("     			team_help();");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_assig.value=='M_CLIENT' ){");
				out.println("				document.Form1.TEAM_HEAD.value=data_vec[0]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_assig.value=='M_USER' ){");
				out.println("     			help_collection_officer(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_assig.value=='M_USER' ){");
				out.println("				document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_assig.value=='M_SYS_DATE'  ){");
				out.println("				document.Form1.VAL_DAY.value=data_vec[0];");
				out.println("				document.Form1.VAL_MONTH.value=data_vec[1];");
				out.println("				document.Form1.VAL_YEAR.value=data_vec[2];");
				out.println("			}");
				
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data)");
				out.println("{ ");
				
				out.println("		if(document.Form1.hid_num.value==\"3\"){");
				out.println("               document.getElementById(\"View_bulk_print\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				
				out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"13\"){");
				out.println("				document.getElementById(\"Main_bulk_print\").innerHTML=m_data;");
				out.println("				document.Form1.hid_num.value=99;");
				
				out.println("               load_main_bulk_print_view(); "); //load the two tabs while loading the page at ONload event
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"23\")");
				out.println("       { ");
				out.println("			if(m_data==\"OK\") ");
				out.println("			{ ");
				out.println("				print_report(); ");
				out.println("			} ");
				out.println("			else ");
				out.println("			{ ");
				out.println("				alert('Error when generating Report...'+m_data); ");
				out.println("			} ");
				out.println("		} ");
				out.println("		if(document.Form1.hid_num.value==\"33\"){");
				out.println("               document.getElementById(\"Leter_div\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				out.println("       } ");
				out.println("		if(document.Form1.hid_num.value==\"43\"){");
				out.println("               document.getElementById(\"Leter_generated_files\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				out.println("       } ");
				
				out.println("} ");
				
				
				out.println("function dynamically_unload_js(old_js_file_name,new_replacement_file_name)");
				out.println("{");
				out.println("   alert('Unload-xxx-1');");
				out.println("   var allsuspects=document.getElementsByTagName(\"SCRIPT\"); ");
				out.println("	for (var i=allsuspects.length; i>=0; i--) "); // //search backwards within nodelist for matching elements to remove
				out.println("   {   ");
				out.println("   alert('Unload-xxx-12');");
				out.println("		if (allsuspects[i] && allsuspects[i].getAttribute(\"src\")!=null) ");
				out.println("       { ");
				out.println("   		 alert('Unload-xxx-1'+old_js_file_name+'allsuspects[i]'+allsuspects[i].getAttribute(\"src\"));");
				out.println("            if(allsuspects[i].getAttribute(\"src\")==old_js_file_name) ");
				out.println("            {  ");
				out.println("                   alert('xxx');");
				out.println("					allsuspects[i].parentNode.replaceChild(new_replacement_file_name,allsuspects[i]);  "); //replace the allallsuspects[i] by new_replacement_file_name 
				out.println("            }  ");
				
				out.println("		}else{ ");
				
				out.println("       }");
				out.println("   }");
				out.println("}");
				
				
				out.println("function dynamically_load_js(Option)");
				out.println("{ ");
				out.println("   if(Option==\"View\")");
				out.println("   {");
				out.println("        new_filename='"+m_html_client_url+"/bulk_print_generation_view.js'; ");
				out.println("        unload_filename='"+m_html_client_url+"/bulk_print_generation.js'; ");
				out.println("        New_file_reference=document.createElement(\"SCRIPT\"); ");
				out.println("        New_file_reference.setAttribute(\"type\",\"text/javascript\"); ");
				out.println("	     New_file_reference.setAttribute(\"src\",new_filename); ");
				out.println("        if (typeof New_file_reference!=\"undefined\") ");
				out.println("     	 {  ");
				out.println("            alert('xxx-1');");
				
				out.println("           dynamically_unload_js(unload_filename,New_file_reference);");
				out.println("        }  ");
				
				out.println("   }else if(Option==\"Generate\")");
				out.println("   {");
				out.println("        new_filename='"+m_html_client_url+"/bulk_print_generation.js'; ");
				out.println("        unload_filename='"+m_html_client_url+"/bulk_print_generation_view.js'; ");
				out.println("        New_file_reference=document.createElement(\"SCRIPT\"); ");
				out.println("        New_file_reference.setAttribute(\"type\",\"text/javascript\"); ");
				out.println("	     New_file_reference.setAttribute(\"src\",new_filename); ");
				out.println("        if (typeof file_reference!=\"undefined\") ");
				out.println("     	 {  ");
				out.println("                    alert('xxx-2');");
				
				out.println("           dynamically_unload_js(unload_filename,New_file_reference);");
				out.println("        }  ");
				
				
				
				out.println("   } ");
				out.println("} ");
				
				
				out.println("function display_bulk_print()");
				out.println("{ ");
				
				out.println("   document.getElementById('Main_bulk_print').style.display = 'inline'; ");
				out.println("	document.getElementById('View_bulk_print').style.display = 'none'; ");
				out.println("	document.Form1.button_view_bulk_print.style.fontWeight = 'normal'; ");
				out.println("	document.Form1.button_bulk_print.style.fontWeight = 'bold';  ");
				
				out.println("} ");  
				
				out.println("function display_view_bulk_print() ");
				out.println("{ ");
				
				out.println(" document.getElementById('Main_bulk_print').style.display = 'none'; ");
				out.println(" document.getElementById('View_bulk_print').style.display = 'inline'; ");
				out.println(" document.Form1.button_view_bulk_print.style.fontWeight = 'bold'; ");
				out.println(" document.Form1.button_bulk_print.style.fontWeight = 'normal'; ");
				
				out.println("} "); 
				
				
				
				out.println("function makeRequest1(obj) ");
				out.println("{ m_url=\"\"; ");
				out.println(" if(document.Form1.hid_assig.value==\"G4\")");
				out.println(" {");
				out.println("       ");
				out.println("  		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" }");
				out.println(" else if(document.Form1.hid_assig.value==\"G6\")");
				out.println(" {");
				out.println("       ");
				out.println("  		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" }");
				out.println(" else if(document.Form1.hid_assig.value=='M_CLIENT' )");
				out.println(" { ");
				out.println("       ");
				out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println(" } ");
				out.println(" else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println(" { ");
				out.println("      ");
				out.println("   	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_team_User&data_val=\"+obj.value+\"&ac_status=Y\";");	
				out.println(" } ");
				out.println(" if(m_url!=\"\") ");
				out.println(" {");	
				out.println(" 		load_interface(m_url,'XML');");
				out.println(" } ");
				out.println(" else ");
				out.println(" { ");
				out.println("    alert('url is not defined');");
				out.println(" } ");
				out.println("}");
				
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\")"); 
				out.println("       { ");
				out.println("			collection_officer_assign();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("			team_assign();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("			sub_team_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("			client_help_value_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
				out.println("			help_value_assign_5()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
				out.println("			finance_no_help_value_assign()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
				out.println("			help_value_assign_7()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
				out.println("			help_value_assign_8()");
				out.println("		}"); 
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{");
				out.println("clear()");
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("clear()");
				out.println("	}	"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function clear(){");			
				out.println("if(document.Form1.hid_help_type.value==\"4\")");
				out.println("{");
				out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\"; "); 
				out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\"; ");
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"6\"){");
				out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"2\")");
				out.println("{ ");
				out.println("  document.Form1.TEAM_HEAD.value=\"\"; ");
				out.println("  document.Form1.TEAM_DESC.value=\"\"; ");
				out.println("}");
				out.println("}");
				
				out.println("function assignState(val) "); 
				out.println("{");
				out.println("  document.Form1.hid_assig.value=val");
				out.println("}");
				
				// client code help
				out.println("function help_client() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				
				out.println("    m_sql = \"bulkprintClient_Sql\"; ");
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function help_finance_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  ");
				
				out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_sql\";"); 
				out.println("    } else { ");
				
				out.println("    m_sql = \"m_help_TXT_BulkPrintFinanceSql_sql2\";");
				out.println("    }  ");	
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function team_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    m_sql = \"m_help_TXT_BULKPRINT_TEAM_ID_sql\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function sub_team_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    m_sql = \"m_help_txt_bulk_print_sub_team_sql\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				
				out.println("function help_collection_officer() ");
				out.println("{ ");
				out.println("    document.Form1.hid_help_type.value='1' ");
				out.println("    m_sql = \"m_help_collection_officer\";"); 
				out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@\"+document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("} "); 
				
				
				out.println("function client_help_value_assign() ");
				out.println("{ ");
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];");
				out.println("} ");
				
				out.println("function finance_no_help_value_assign() ");
				out.println("{ ");
				out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[6];"); 
				out.println("} ");
				
				
				out.println("function team_assign(){");
				out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[2]");
				out.println(" document.Form1.TEAM_DESC.value =oBj.valout[3]");
				out.println("}");
				
				out.println("function sub_team_assign(){");
				out.println(" document.Form1.SUB_TEAM_HEAD.value =oBj.valout[2]");
				out.println(" document.Form1.SUB_TEAM_DESC.value =oBj.valout[3]");
				out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[4]");
				out.println(" document.Form1.TEAM_DESC.value =oBj.valout[5]");
				out.println("}");
				
				out.println("function collection_officer_assign() ");
				out.println("{");
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
				out.println("    document.Form1.SUB_TEAM_HEAD.value=oBj.valout[4];"); 
				out.println("    document.Form1.SUB_TEAM_DESC.value=oBj.valout[5];"); 
				out.println("    document.Form1.TEAM_HEAD.value=oBj.valout[6];"); 
				out.println("    document.Form1.TEAM_DESC.value=oBj.valout[7];"); 
				out.println("}"); 
				
				
				
				
				
				out.println("function MyDialog()");
				out.println("{      ");
				out.println("   this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				
				
				
				out.println(" function clear_window()	");
				out.println(" { ");
				out.println("   if(confirm(\"Are you sure you want to clear the screen? \")) "); 
				out.println("   { ");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=main_page';"); 
				out.println("   }"); 
				out.println(" }"); 
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function get_system_date() ");
				out.println("{ ");
				out.println("       assignState('M_SYS_DATE') ;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("} ");
				
				out.println("function load_termination_detail() ");
				out.println("{ ");
				out.println("  if(document.Form1.TXT_CLIENT_CODE.value!=\"\") ");
				out.println("  { ");
				out.println("   document.Form1.hid_num.value=\"33\"; ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value; ");
				out.println("	load_interface(m_url,'NO'); ");
				out.println("  }  ");
				out.println("  else ");
				out.println("  { ");
				out.println("     alert('Please Select a Client Code'); ");
				out.println("  } ");
				out.println("} ");
				
				
				
				out.println("function print_report()");
				out.println("{ ");
				out.println("   AsAtdate=document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value ");
				out.println("   document.Form1.hid_num.value=\"43\"; ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=LOAD_GENERATED_LETTERS&TEAM_ID=\"+document.Form1.TEAM_HEAD.value+\"&SUBTEAM_ID=\"+document.Form1.SUB_TEAM_HEAD.value+\"&USER=\"+document.Form1.TXT_USER.value+\"&date=\"+AsAtdate; ");
				out.println("	load_interface(m_url,'NO'); ");
				out.println("} ");
				
				
				out.println("function open_pdf(finance_no,letter_category_id,letter_id,file_path)");
				out.println("{ ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Bulk_Letter_print?chksql=DOWNLOAD_PDF&FINANCE_NO=\"+finance_no+\"&LETTER_CATEGORY_ID=\"+letter_category_id+\"&LETTER_ID=\"+letter_id; ");
				out.println("	document.Form1.action = m_url; ");
				out.println("   document.Form1.submit(); ");
				
				out.println("} ");
				
				
				out.println("function before_submit()");
				out.println("{   ");
				out.println("    atleast_one=false;  ");
				out.println("    if(document.Form1.NUM_CHKS)" );
				out.println("    {   ");
				out.println("    	for(i=1;i<=parseInt(document.Form1.NUM_CHKS.value);i++)");
				out.println("    	{   ");
				out.println("           ");
				out.println("        if(document.getElementById(\"TXT_APPROVE_TYPE_\"+i).checked==true)");
				out.println("        {   ");
				out.println("            atleast_one=true; ");
				out.println("        }   ");
				out.println("    	}   ");
				out.println("    	if(atleast_one)");
				out.println("    	{   ");
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Generate_bulk_print';");  //added by nuwan de silva
				out.println("			document.Form1.submit();	"); 
				out.println("        ");
				out.println("    	}else   ");
				out.println("       {   ");
				out.println("         alert('Please Select Atleast One Record');   ");
				out.println("       }   ");
				out.println("     }else   ");
				out.println("     {   ");
				out.println("    	alert('No record Available To Generate the Letters');   ");
				out.println("     }   ");
				out.println("}  ");
				
				out.println(" function load_letter_category()");
				out.println(" { ");
				out.println("   document.getElementById(\"Leter_div\").innerHTML=\"\"; ");
				out.println("   if(document.Form1.NUM_CHKS)");
				out.println("   { ");
				out.println("      document.Form1.NUM_CHKS.value=\"0\"; ");
				out.println("   } ");
				//out.println("   load_termination_detail(); ");
				out.println(" } ");
				
				out.println("</SCRIPT>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_main_bulk_print();display_bulk_print()\"> ");// //load_main_bulk_print();display_bulk_print() //()  //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>");
				
				
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">"); // this is for team tab(View Tab)
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_MOVEMENT_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_num' VALUE=\"99\">");	
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
				
				
				
				
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Bulk Letter Print -  </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'><input type=\"button\" class='mainbut'  onClick='before_submit();' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  onClick='test_load_bulk_print();' value=\"Help\"></td>");   //onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onclick='clear_window()' value=\"Cancel\"></td>");   // onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'   onclick='close_window()' value=\"Close\"></td>");  //onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				
				out.println("<TABLE CELLPADDING=\"2\" > ");
				out.println("<TR> ");
				out.println("<TD id=\"change\"> ");
				out.println("	<INPUT TYPE=\"BUTTON\"  class='but_input' NAME=\"button_bulk_print\" style='width:240px; font-weight:bold' ALIGN=\"CENTER\" SIZE=\"25\"  VALUE=\"Bulk Print\" onclick='display_bulk_print()' > ");
				out.println("</TD>");
				out.println("<TD id=\"change1\"> ");
				out.println("   <INPUT TYPE=\"BUTTON\" class='but_input' NAME=\"button_view_bulk_print\" style='width:240px'  ALIGN=\"CENTER\"  VALUE=\"View Print Report\"  onclick=\"display_view_bulk_print()\">");
				out.println("</TD>");
				out.println("</TR> ");
				out.println("</TABLE> ");
				
				
				
				out.println("<div id=\"Main_bulk_print\" >");
				out.println("</div>");
				out.println("<div id=\"View_bulk_print\" >");
				out.println("</div>");
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
				
			}
			//else if(m_chksql.equals("main_page")){  //COMMENTED PRINTED FILES VIEW OPTION tAB
			else if(m_chksql.equals("main_printed_file_view_page"))
			{	
				
				/*	
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					
				
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("var b_flag=0;");
					
					out.println("var timerID;");
					out.println("var durationID=0;");
					
					out.println("function set_timer_actions() {");
					out.println("   durationID=durationID+1;");
					out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
					out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
					out.println("}");
					
					
					out.println("function run_report() {");
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
					out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=run_report&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("   set_timer_actions();");
					out.println("		load_interface(m_url,'NORM');");
					out.println("	}");
					out.println("}");
					
					out.println("function get_vector_normal(m_data){");
					out.println("		if(m_data==\"OK\"){");
					out.println("			print_report();"); 
					out.println("		}");
					out.println("		else{");
					out.println("			alert('Error when generating Report...'+m_data);");
					out.println("		}");
					out.println("}");
					
					out.println("function print_report(){");
					out.println("		clearTimeout(timerID);");
					out.println("		m_table.innerHTML=\"\";");
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
					out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
					out.println("		m_team_id=document.Form1.TEAM_HEAD.value;");
					out.println("		m_sub_team_id=document.Form1.SUB_TEAM_HEAD.value;");
					out.println("		m_user_id=document.Form1.TXT_USER.value;");
					
					out.println("if(m_team_id!='' && m_sub_team_id!='' && m_user_id!='' ){");
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_user&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_user_new&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("	}");
					out.println("else if(m_team_id!='' && m_sub_team_id!='' && m_user_id=='' ){");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_sub_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_sub_team_new&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("	}");
					out.println("else if(m_team_id!='' && m_sub_team_id=='' && m_user_id=='' ){");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_team_new&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("	}");
					
					out.println("else if(m_team_id=='' && m_sub_team_id=='' && m_user_id=='' ){");
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_all&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=print_report_all_new&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
					
					out.println("	}");
					out.println("			window.open(m_url);");
					out.println("	}");
					out.println("}");
					
					
					out.println("function get_vector(data_vec) {");
					out.println("			if(data_vec.length==0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
					out.println("     team_help();");
					out.println("			}");
					out.println("			else");
					out.println("			if(data_vec.length>0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
					out.println("			document.Form1.TEAM_HEAD.value=data_vec[0]");
					out.println("			}");
					out.println("			else");
					out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
					out.println("     help_button_user(data_vec);");
					out.println("			}");
					out.println("			else");
					out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
					out.println("			document.Form1.TXT_USER.value=data_vec[0]");
					out.println("			}");
					out.println("			else");
					out.println("			if(document.Form1.hid_chk_status.value=='M_SYS_DATE'  ){");
					out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
					out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
					out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
					out.println("			}");
					out.println("}");
					
					
					out.println("function drill_down_asset(m_finance_no) {");
					out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
					out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					out.println("}");	
					
					out.println("function befor_end(m_obj) {");
					out.println("   m_obj.focus();");
					out.println("}");
					
					out.println("function assignState(val){");
					out.println("document.Form1.hid_chk_status.value=val");
					out.println("}");
					
					
					out.println("function makeRequest(obj) {");
					out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
					out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_team_User&data_val=\"+obj.value+\"&ac_status=Y\";");	
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
					out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
					out.println("VDATE.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 
					
					out.println("else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 
					
					out.println("function load_lock(){	"); 
					out.println("document.oncontextmenu=new Function(\"return false\");"); 
					out.println("}	"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=main_page&generate=page';"); 
					out.println("		}"); 
					out.println("}"); 
					
					out.println("function new_window(){	"); 
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=main_page&generate=page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
					out.println("function save_window(){	"); 
					out.println("before_submit();"); 
					out.println("}"); 
					out.println(""); 
					
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
					out.println("    HelpBox_msg(m_help_message);"); 
					out.println("}"); 	
					out.println("function HelpBox_msg(m_help_message) {"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
					out.println("  \"&help_message_in=\"+m_help_message);"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 
					
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
					
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}"); 
					out.println("else if(m_val!=\"EDIT\"){"); 
					out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
					out.println("}"); 
					out.println("}"); 
					out.println("else{");
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_save_status.value=\"Save\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
					out.println("}else if(m_val==\"DEL\"){");  
					out.println("document.Form1.hid_status.value=\"Delete\";");
					out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");
					out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function MyDialog(){"); 
					out.println("    this.valout   = new Array(10);"); 
					out.println("}		"); 
					out.println(""); 
					
					
					//----------------------------------------------------------------------------------------------------------------------------------------
					
					
					
					out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	"); 
					
					out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					
					out.println("	if(oBj.valout[1] ==\" \"){"); 
					out.println("	clear_data(IfCount);");
					out.println("	}else");
					
					
					out.println("	"); 
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					
					out.println("		if(IfCount==\"2\"){"); 
					out.println("		team_assign(oBj);"); 
					out.println("		}"); 
					out.println("		if(IfCount==\"3\"){"); 
					out.println("		help_value_assign_user(oBj);"); 
					out.println("		}"); 
					out.println("		if(IfCount==\"4\"){"); 
					out.println("		sub_team_assign(oBj);"); 
					out.println("		}"); 
					
					
					
					
					out.println("	}"); //end next
					
					out.println("	else{"); 
					out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
					out.println("		return false;"); 
					out.println("	} "); 
					
					out.println("	}"); //end prev
					out.println("	else{	"); 
					out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
					out.println("	}	"); 
					out.println("	}		"); ///close
					
					out.println("	else{");
					out.println("	clear_data(IfCount);");//Added To The Clear 
					out.println("	}");
					
					
					out.println("	}	"); //
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
					
					//-----------------------------------------------------------------------------------------------------------------------------------------
					
					out.println(""); 
					out.println("function team_help() {"); 
					out.println("    m_sql = \"m_help_TXT_TEAM_ID_sql\";"); 
					out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
					out.println("}"); 
					
					out.println("function team_assign(oBj){");
					out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[2]");
					out.println(" document.Form1.TEAM_DESC.value =oBj.valout[3]");
					out.println("}");
					
					out.println("function sub_team_help() {"); 
					out.println("    m_sql = \"m_help_txt_sub_team_sql\";"); 
					out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'4');"); 
					out.println("}"); 
					
					out.println("function sub_team_assign(oBj){");
					out.println(" document.Form1.SUB_TEAM_HEAD.value =oBj.valout[2]");
					out.println(" document.Form1.SUB_TEAM_DESC.value =oBj.valout[3]");
					out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[4]");
					out.println(" document.Form1.TEAM_DESC.value =oBj.valout[5]");
					out.println("}");
					
					
					
					out.println("function help_button_user() {"); 
					out.println(" document.Form1.hid_help_type.value='3' ");
					out.println("    m_sql = \"m_help_team_user_id_sql_new\";"); 
					out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@\"+document.Form1.TXT_USER.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
					out.println("}"); 
					
					
					out.println("function help_value_assign_user(oBj) {"); 
					out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
					out.println("    document.Form1.SUB_TEAM_HEAD.value=oBj.valout[4];"); 
					out.println("    document.Form1.SUB_TEAM_DESC.value=oBj.valout[5];"); 
					out.println("    document.Form1.TEAM_HEAD.value=oBj.valout[6];"); 
					out.println("    document.Form1.TEAM_DESC.value=oBj.valout[7];"); 
					out.println("}"); 
					
					out.println("function clear_data(IfCount) {");
					out.println("		if(IfCount==\"2\"){"); 
					out.println("document.Form1.TEAM_HEAD.value='';");
					out.println("		}"); 
					out.println("		if(IfCount==\"3\"){"); 
					out.println("document.Form1.TXT_USER.value='';");
					out.println("		}"); 
					out.println("}");
					
					
					out.println("function get_rental_dates(date,m_client_code,m_officer){");
					
					out.println("if(validate_data()){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
					out.println("load_interface(m_url,'NORM');");
					//out.println("window.open(m_url);");
					out.println("}"); 
					
					out.println("else{");
					out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
					out.println("} "); 
					out.println("}"); 
					
					out.println("function ckeck_new_date(){ "); 
					out.println("b_flag=0;");
					out.println("if(m_table.innerHTML==\"\"){");
					out.println("alert('No data to save');");
					out.println("b_flag=1;");
					out.println("}"); 
					out.println("else if(!count_date_selected()){"); 
					out.println("alert('Please enter new date');");
					out.println("b_flag=1;");
					out.println("}"); 
					
					out.println("else{");
					out.println("b_flag=0;");
					out.println("}"); 
					
					out.println("}"); 
					
					
					
					
					
					
					out.println("function load_calendar(num) {");
					out.println(" document.Form1.hid_cal_date.value=num;"); 
					out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
					out.println("}");
					
					out.println("function load_c_date(val) {");
					out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
					out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
					out.println("v_date=val.substr(0,val.indexOf('-'));");
					out.println("if(v_date.length<2)");
					out.println("v_date=0+v_date");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("v_month=val.substr(0,val.indexOf('-'));");
					out.println("if(v_month.length<2)");
					out.println("v_month=0+v_month");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("     document.Form1.VAL_DAY.value=v_date;");
					out.println("     document.Form1.VAL_MONTH.value=v_month;");
					out.println("     document.Form1.VAL_YEAR.value=val;");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
					out.println("  }");				
					out.println("}");
					out.println("}");
					
					
					out.println("function check_Date(objDD,objMM,objYY) {");
					out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
					out.println("if(checkMonthLength(objDD,objMM,objYY))");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
					
					out.println("}");
					
					
					out.println("function get_system_date() {");
					out.println("assignState('M_SYS_DATE') ;");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
					out.println("		load_interface(m_url,'XML');");
					out.println("}");
					
					
					
					out.println("</script>"); 
					*/
				//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date()\"> "); //load_lock(), header(),add_row()
				//out.println("<FORM NAME='Form1' method='post'>"); 
				/*
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_MOVEMENT_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Movement Report - With Ageing </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				*/
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" style=\"{width:110px;}\" onClick=\"print_report()\" >"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" style=\"{width:110px;}\" onClick=\"run_report()\" >");
			    out.println("</td>"); 
				out.println("</tr>");
				out.println("</table>");
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TEAM_HEAD'  class=div_input>Team Id </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TEAM_HEAD' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest1(document.Form1.TEAM_HEAD)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TEAM_HEAD' value=\"Help\" onClick=\"team_help()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Team Desc </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TEAM_DESC' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_SUB_TEAM_HEAD'  class=div_input>Sub Team Id </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='SUB_TEAM_HEAD' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest1(document.Form1.TEAM_HEAD)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_SUB_TEAM_HEAD' value=\"Help\" onClick=\"sub_team_help()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Sub Team Desc </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='SUB_TEAM_DESC' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Collection Officer </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onBlur=\"help_collection_officer()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_collection_officer()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Officer Name </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER_NAME' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
				out.println("</tr>"); 
				
				
				
				out.println("<tr >"); 
				out.println("<td colspan='4'>");
				out.println("  <div id='Leter_generated_files'> </div> ");
				out.println("</td>"); 
				out.println("</tr>"); 
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				/*
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				*/
				out.println("</body>"); 
				out.println("</html>"); 
				
				//out.flush();
				
			}	
			else if(m_chksql.equals("Bulk_print_letter_generation"))
			{
				
				stmt1=conn.createStatement();
				stmt=conn.createStatement();
				
				
				
				/*
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Termination Letter</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("var b_flag=0");
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_assig.value==\"G7\"){");
				out.println("help_button_7();");
				out.println("			}");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_assig.value==\"G8\"){");
				out.println("help_button_8();");
				out.println("			}");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_assig.value==\"G2\"){");
				out.println("help_button_2();");
				out.println("			}");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_assig.value==\"G3\"){");
				out.println("help_button_3();");
				out.println("			}");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value==\"G4\"){");
				out.println("help_client();");
				out.println("			}");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_assig.value==\"G6\"){");
				out.println("help_button_6();");
				out.println("			}");
				out.println("}");
				
				out.println("function load_lock(){	"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Termination_Letter?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Termination_Letter?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MISF_display_termination_report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Termination Letter - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Termination Letter - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
				out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
				out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
				out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
				out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
				out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
				out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function makeRequest1(obj) {");
				out.println("if(document.Form1.hid_assig.value==\"G7\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_payment_details_new&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G8\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_sus_ref&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G2\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_application&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G3\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_invoice&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G4\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G5\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inquiry&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("if(document.Form1.hid_assig.value==\"G6\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("}");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("		help_value_assign_1();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("		help_value_assign_2();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("	help_value_assign_3()");
				out.println("		}"); 
				
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("	help_value_assign_4()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
				out.println("	help_value_assign_5()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
				out.println("	help_value_assign_6()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
				out.println("	help_value_assign_7()");
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
				out.println("	help_value_assign_8()");
				out.println("		}"); 
				
				
				
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{");
				out.println("clear()");
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("clear()");
				out.println("	}	"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function clear(){");			
				out.println("if(document.Form1.hid_help_type.value==\"4\"){");
				out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"6\"){");
				out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
				out.println("}");
				out.println("}");
				
				out.println("function assig(val) {"); 
				out.println("document.Form1.hid_assig.value=val");
				out.println("}");
				
				
				out.println("function view() {"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_termination_report1?chksql=MAIN&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sus_ref_no=\"+document.Form1.TXT_SUS_REF_NO.value+\"&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&payment_no=\"+document.Form1.TXT_PAYMENT_NO.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&invoice_no=\"+document.Form1.TXT_INVOICE_NO.value+\"\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				
				
				out.println("function help_client() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				out.println("    m_sql = \"ClientSql1\";"); 
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function help_value_assign_4() {"); 
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];");
				out.println("}");
				
				out.println("function help_button_6() {"); 
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  ");
				out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
				out.println("    } else { ");
				out.println("    m_sql = \"m_help_TXT_FinanceSql_sql2\";");
				out.println("    }  ");	
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				
				
				out.println("function help_value_assign_6() {"); 
				out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[6];"); 
				out.println("}");
				
				out.println("function load_termination_detail() {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Termination_Letter?chksql=LOAD_TERMINATION_LETTER&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
				out.println("		load_interface(m_url,'NO');");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		termination_detail_data.innerHTML=m_data;");
				out.println("}");
				
				
				out.println("function Generate_LetterX(lease_type,finance_no,client_code) {");	
				out.println(" if(lease_type=='FINLEASE'){");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println(" else if(lease_type=='HIREPURCH') {");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_hire_purch?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println(" else{");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println("}");
				
				out.println("function Generate_LetterX_Sinhala(lease_type,finance_no,client_code) {");	
				out.println(" if(lease_type=='FINLEASE'){");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_fin_lease_sinhala?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println(" else if(lease_type=='HIREPURCH') {");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_hire_purch_sinhala?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println(" else{");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_fin_lease_sinhala?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println("}");
				
				
				
				out.println("function Generate_LetterY(lease_type,finance_no,client_code) {");	
				out.println(" if(lease_type=='FINLEASE'){");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_LOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=1,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println(" else if(lease_type=='HIREPURCH') {");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_LOT_hire_purch?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				
				out.println(" else{");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_LOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println(" }");
				out.println("}");
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Termination Letter</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<br>");
				*/
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assignState('G4'),makeRequest1(document.Form1.TXT_CLIENT_CODE)\">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" ... \" onClick=\"help_client()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 		
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style='{width:350}' maxlength='15' size='15' disabled >"); 
				out.println("</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 	
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" ... \" onClick=\"help_finance_no()\">"); 
				out.println("</td>"); 
				out.println("</tr>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>"); 
				out.println("<td width='40%' >");
				
				rs=stmt.executeQuery("SELECT LETTER_ID,LETTER_DESCRIPTION FROM "+m_schema_name+".AF_CO_LETTER_CATEGORY ");
				
				out.println("    <SELECT class='txt_input' name='TXT_LETETR_CATEGORY' id='TXT_LETETR_CATEGORY' onchange=\"load_letter_category();\" >  ");
				while(rs.next())
				{
					out.println("<OPTION value='"+rs.getString(1)+"'  >");
					out.println(rs.getString(2));
					out.println("</OPTION>");
				}	
				out.println("    </SELECT> "); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td colspan='3' >");
				out.println("  <div id='Leter_div'> </div> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				
				out.println("</table>"); 
				out.println("<br>"); 
				
				
				/*
				out.println("<DIV id='termination_detail_data'  class=div_input></DIV>");
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				//out.flush();
				*/
				
			}
			
			
			
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
