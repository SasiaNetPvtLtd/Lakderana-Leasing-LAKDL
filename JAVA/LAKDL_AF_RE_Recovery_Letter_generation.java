//SCREEN NAME : COLLECTION - RECOVERY LETTER GENERATION
//CREATED BY  : ISHANI
//DATE/TIME   : 
//NOTES       :
//MODIFIED BY : KANISHKA DILSHAN ON 21-04-2014
//DATE/TIME   : 21-04-2014
//NOTES       :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Recovery_Letter_generation extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2,stmt1,stmt_view_let,stmt_download_pdf,stmt_sys;
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
			String m_sys_date_dd="",m_sys_date_mm="",m_sys_date_yy="";
			
			
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
			
			else if(m_chksql.equals("LOAD_RECOVERY_LETTER_PREVIOUS"))
			{
				
				String m_string				= "";				
				String m_letter_type		= "";
				String m_date 				= req.getParameter("DATE");
				String fin_no 			= req.getParameter("fin_no");
				String app_no 			= req.getParameter("app_no");
				String product_type				= req.getParameter("product_type");
				//String m_coll_offi			= req.getParameter("COLL_OFFI");
				String m_letter_category	= req.getParameter("LETTER_CATEGORY");
				
				stmt1=conn.createStatement();
				stmt2=conn.createStatement();
				stmt=conn.createStatement();
				
				String query="";
				
				if(m_letter_category!=null)
				{	
					
					query = " "+
						" SELECT A.FINANCE_NO,A.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),A.CONTRACT_AGE, "+
						" A.CLOSING_AGE,TO_CHAR(A.LAST_INVOICE_DATE,'DD-MM-YYYY'),A.BAL_AS_AT_LETTER_DATE,A.TRANSACTION_TYPE,A.APPLICATION_NO, "+
						" A.LETTER_CODE,"+m_schema_name+".AF_RL_GET_LETTER_DOC_NAME(A.LETTER_CODE,'"+product_type+"')  "+
						" ,(SELECT MAX(LETTER_SENT_DATE) FROM "+m_schema_name+".AF_RL_LETTER_SENT_DETAILS "+ //Last date
						"  WHERE FINANCE_NO = A.FINANCE_NO AND LETTER_ID = A.LETTER_CODE )"+
						" ,(SELECT COUNT(*) FROM "+m_schema_name+".AF_RL_LETTER_SENT_DETAILS "+//Letter Count
						"  WHERE FINANCE_NO = A.FINANCE_NO AND LETTER_ID = A.LETTER_CODE ),"+
						" TO_CHAR(A.LETTER_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR(A.LAST_INVOICE_DATE,'DD-MM-YYYY'), "+//--14 ADDED MILINDA FOR ##14507 LAST INVICE DATE 2015-01-13
						" NVL(MOD_USER,'-'),NVL(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') "+// 16,17
						" FROM "+m_schema_name+".AF_RL_GENERATED_RECOVRY_LETTER A "+
						" WHERE  TRUNC(A.LETTER_DATE,'DD') <= TRUNC(SYSDATE,'DD') "+   
						" AND A.FINANCE_NO = '"+fin_no+"' "+
						" AND A.LETTER_STATUS = 'PRINTED' "+
						" AND A.TRANSACTION_TYPE = '"+product_type+"' "+
						" AND A.APPLICATION_NO ='"+app_no+"' "+
						
						//" AND A.MK_OFFICER LIKE '%"+m_coll_offi+"%' "+
						"";
					//out.println(query);
					
					rs1= stmt1.executeQuery(query);
					
				}
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection Process - Recovery Letter Generation</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> ");// //load_main_bulk_print();display_bulk_print() //()  //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>");
				
				
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='*%' align='center'><DIV class=div_input ><b> Recovery Letter Generation  </b></DIV></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input ><b>Contract No</b></DIV></td>";//1
				m_string=m_string+"<td width='15%' ><DIV class=div_input ><B>Client Name</b></DIV></td>";//2
				m_string=m_string+"<td width='8%' ><DIV  class=div_input ><b>Contract age</b></DIV></td>";//3					
				m_string=m_string+"<td width='8%' ><DIV  class=div_input ><b> Due date</b></DIV></td>";//4
				m_string=m_string+"<td width='8%' ><DIV  class=div_input ><b>Month end age</b></DIV></td>";//5					
				m_string=m_string+"<td width='8%' ><DIV  class=div_input  ><b>Total Due amount As at Letter Date </b></DIV></td>";//6
				m_string=m_string+"<td width='8%' ><DIV  class=div_input  ><b>Print</b></DIV></td>";//7
				m_string=m_string+"<td width='8%' ><DIV  class=div_input  ><b>Letter Category</b></DIV></td>";//8
				m_string=m_string+"<td width='10%' ><DIV class=div_input  ><b>Print user</b></DIV></td>";//9
				m_string=m_string+"<td width='10%' ><DIV class=div_input  ><b>Print Date</b></DIV></td>";//10
				
				int chk_nums=0;
				while(rs1.next())
				{
					chk_nums++;		
					m_string=m_string+"<tr class=pdn_txtpos1>";
					m_string=m_string+"<td  class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td  class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td  class=div_input align=right >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td  class=div_input >"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td  class=div_input >"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td  class=div_input >"+nf1.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td  class=div_input style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"window.opener.print_report('"+rs1.getString(2)+"','"+rs1.getString(9)+"','"+nf1.format(rs1.getDouble(7))+"','"+rs1.getString(10)+"','"+product_type+"','"+rs1.getString(6)+"','"+rs1.getString(12)+"','"+rs1.getString(14)+"');\"></td>";
					m_string=m_string+"<td  class=div_input style='cursor:hand'>"+rs1.getString(11)+" </td>";
					m_string=m_string+"<td  class=div_input style='cursor:hand'>"+rs1.getString(16)+" </td>";
					m_string=m_string+"<td  class=div_input style='cursor:hand'>"+rs1.getString(17)+" </td></tr>";
				}	
				
				m_string=m_string+"</table>";
				
				//System.out.println("AAAA-"+m_string);
				
				
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
				
				
			}
			else if(m_chksql.equals("LOAD_TERMINATION_LETTER"))
			{
				
				String m_string				= "";				
				String m_letter_type		= "";
				String m_date 				= req.getParameter("DATE");
				String m_product 			= req.getParameter("PRODUCT");
				String m_branch				= req.getParameter("BRANCH");
				String m_coll_offi			= req.getParameter("COLL_OFFI");
				String m_letter_category	= req.getParameter("LETTER_CATEGORY");
				
				stmt1=conn.createStatement();
				stmt2=conn.createStatement();
				stmt=conn.createStatement();
				
				String query="";
				
				if(m_letter_category!=null)
				{	
					
					
					//new query added Below by Kanishka Dilshan on 24-04-2014
					query = " "+
						" SELECT A.FINANCE_NO,A.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),A.CONTRACT_AGE, "+
						" A.CLOSING_AGE,TO_CHAR(A.LAST_INVOICE_DATE,'DD-MM-YYYY'),A.BAL_AS_AT_LETTER_DATE,A.TRANSACTION_TYPE,A.APPLICATION_NO,  "+
						" B.LAST_SENT_LETTER, "+
						" NVL((SELECT NVL(TO_CHAR(MAX(LETTER_SENT_DATE),'DD-MM-YYYY'),'-') "+
						"   FROM "+m_schema_name+".AF_RL_LETTER_SENT_DETAILS "+
						"   WHERE LETTER_ID = a.LETTER_CODE "+
						"   AND LETTER_SENT_STATUS = 'Y' "+
						"   AND FINANCE_NO = B.FINANCE_NO "+
						" ),'-') LAST_SENT_DATE ,"+
						" TO_CHAR(A.LETTER_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR(A.LAST_INVOICE_DATE,'DD-MM-YYYY'), "+	//--13 ADDED MILINDA FOR ##14507 LAST INVICE DATE 2015-01-13
						" "+m_schema_name+".AF_CO_GET_CAPITAL_OUTSTANG(A.APPLICATION_NO,'"+m_date+"'), "+//CAPITAL
						" ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(a.FINANCE_NO,a.CLIENT_CODE,'"+m_date+"','SASIANET')+"+m_schema_name+".AF_CO_GET_CAPITAL_OUTSTANG(A.APPLICATION_NO,'"+m_date+"')+"+m_schema_name+".AF_CO_GET_ODI_BAL_Q(a.FINANCE_NO,'"+m_date+"'))"+
						" FROM "+m_schema_name+".AF_RL_GENERATED_RECOVRY_LETTER A,"+m_schema_name+".AF_RL_LETTER_SENT_HEADER B "+
						" WHERE  TRUNC(A.LETTER_DATE,'DD') <= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD') "+ // < Than added by KD on 19-11-2014 on MRF Request..
						" AND A.FINANCE_NO = B.FINANCE_NO(+) "+
						" AND A.LETTER_STATUS = 'PENDING' "+						
						//" AND A.SINHALA_STATUS = 'PENDING' "+  
						" AND A.LETTER_CODE  LIKE '"+m_letter_category+"' "+
						" AND A.BRANCH_CODE LIKE '%"+m_branch+"%' "+
						" AND A.MK_OFFICER LIKE '%"+m_coll_offi+"%' "+
						"";
					//out.println(query);
					
					rs1= stmt1.executeQuery(query);
					
				}
				
				
				
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='*%' align='center'><DIV class=div_input ><b> Recovery Letter Generation  </b></DIV></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"";
				m_string=m_string+"</table>";
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input ><b>Contract No</b></DIV></td>";//1
				m_string=m_string+"<td width='25%' ><DIV class=div_input ><B>Client Name</b></DIV></td>";//2
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>Contract age</b></DIV></td>";//3 <!--style= cursor:hand; title='Click here to sort by - Contract age'    onclick=sort_data('CONTRACT_AGE')  -->					
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b> Due date</b></DIV></td>";//4
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>Month end age</b></DIV></td>";//5 <!--style= cursor:hand; title='Click here to sort by - Month end age'    onclick=sort_data('MONTH_END') -->					
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>Total Due amount As at Letter Date</b></DIV></td>";//6
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>Print</b></DIV></td>";//7
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>previous letter generated date</b></DIV></td>";//8
				m_string=m_string+"<td width='8%' ><DIV class=div_input  ><b>letter generated date</b></DIV><INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE=''></td>";//9
				m_string=m_string+"</tr>";
				//m_string=m_string+"</table>";
				
				int chk_nums=0;
				
				//m_string=m_string+"<table align='center' width='100%' class='table' >";
				
				while(rs1.next())
				{
					m_date=rs1.getString(12);
					chk_nums++;		
					m_string=m_string+"<tr class=pdn_txtpos1>";
					m_string=m_string+"<td class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_LEASE_TYPE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FINANCE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td class=div_input onClick=\"\" align=right  >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td class=div_input onClick=\"\" align=center >"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td class=div_input onClick=\"\" align=right  >"+rs1.getString(5)+"</td>";
					if(m_letter_category.equals("LES_LETE")){
						m_string=m_string+"<td class=div_input onClick=\"\" align=right  >"+nf1.format(rs1.getDouble(15))+"</td>";
					}else{
						m_string=m_string+"<td class=div_input onClick=\"\" align=right  >"+nf1.format(rs1.getDouble(7))+"</td>";
					}
					if(m_letter_category.equals("LES_LETE")){
						m_string=m_string+"<td class=div_input onClick=\"\" align=center style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_report('"+rs1.getString(2)+"','"+rs1.getString(9)+"','"+nf1.format(rs1.getDouble(7)+rs1.getDouble(14))+"','"+rs1.getString(12)+"','"+rs1.getString(13)+"');\"></td>";//12 CHANGE MILINDA FOR ##14507
						
					}else{
						m_string=m_string+"<td class=div_input onClick=\"\" align=center style='cursor:hand'><INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_report('"+rs1.getString(2)+"','"+rs1.getString(9)+"','"+nf1.format(rs1.getDouble(7))+"','"+rs1.getString(12)+"','"+rs1.getString(13)+"');\"></td>";//12 CHANGE MILINDA FOR ##14507
					}
					if(rs1.getString(11) != null && !rs1.getString(11).equals("-"))
						
						m_string=m_string+"<td class=div_input ALIGN='CENTER' onClick=\"print_previous_letter('"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+m_letter_category+"','"+m_date+"','"+m_product+"')\" style='cursor:hand'><U>"+rs1.getString(11)+" </U></td>";
					
					else
						m_string=m_string+"<td class=div_input align=center onClick=\"print_previous_letter('"+rs1.getString(1)+"','"+rs1.getString(9)+"','"+m_letter_category+"','"+m_date+"','"+m_product+"')\" style='cursor:hand'><U> n/a </u></td>";
					
					m_string=m_string+"<td class=div_input align=center >"+rs1.getString(12)+"</td>";
					m_string=m_string+"</tr>";
				}	
				m_string=m_string+"</table>";
				
				
				//System.out.println("AAAA-"+m_string);
				
				
				out.println(m_string);
				
				
				
				
			}
			else if(m_chksql.equals("main_page"))
			{
				
				stmt_sys = conn.createStatement ();
				rs = stmt_sys.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL "); 
				if(rs.next()){
					m_sys_date_dd=rs.getString(1);m_sys_date_mm=rs.getString(2);m_sys_date_yy=rs.getString(3);
				}
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection Process - Recovery Letter Generation</TITLE>"); 
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
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=main_printed_file_view_page\";");
				out.println("   load_interface(m_url,'NORM');");
				out.println("} ");
				
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=main_page&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				
				
				out.println("function load_main_bulk_print()");	
				out.println("{  ");
				out.println("   document.Form1.hid_num.value=13;");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=Bulk_print_letter_generation\";");
				out.println("   load_interface(m_url,'NORM');");
				
				
				out.println("               assign_system_date(); ");// here view Tab is having a calander must loaded with current time
				//out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				out.println("} ");
				
				
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(document.Form1.hid_assig.value=='M_SYS_DATE'  ){");
				///////	out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				// out.println("				document.Form1.VAL_DAY.value=data_vec[0];");
				//out.println("				document.Form1.VAL_MONTH.value=data_vec[1];");
				//out.println("				document.Form1.VAL_YEAR.value=data_vec[2];");
				out.println("			}");
				out.println("			else if(document.Form1.hid_assig.value=='M_LETTER'  ){");
				//out.println("m_size=4; ");
				//	out.println("if(m_size>0) {  "); 
				out.println("j= 0;	"); 
				out.println("var select = document.getElementById(\"TXT_LETETR_CATEGORY\");"); 
				out.println(" select.options.length=j;");
				out.println("for(j=0;j<data_vec.length;j++){	"); 
				out.println("select.options[select.options.length] = new Option(data_vec[j+1],data_vec[j]);"); 
				out.println("j=j+1;"); 
				out.println("}"); 
				//out.println("}"); 
				out.println("			}");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data)");
				out.println("{ ");
				//	out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				out.println("		if(document.Form1.hid_num.value==\"3\"){");
				out.println("               document.getElementById(\"View_bulk_print\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				
				//out.println("               get_system_date(); ");// here view Tab is having a calander must loaded with current time
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"13\"){");
				out.println("				document.getElementById(\"Main_bulk_print\").innerHTML=m_data;");
				out.println("				document.Form1.hid_num.value=99;");
				
				//out.println("               load_main_bulk_print_view(); "); //load the two tabs while loading the page at ONload event
				out.println("		}");
				out.println("		if(document.Form1.hid_num.value==\"23\")");
				out.println("       { ");
				out.println("			if(m_data==\"OK\") ");
				out.println("			{ ");
				//	out.println("				print_report(); ");
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
				//out.println("	document.Form1.button_view_bulk_print.style.fontWeight = 'normal'; ");
				//out.println("	document.Form1.button_bulk_print.style.fontWeight = 'bold';  ");
				
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
				out.println(" else if(document.Form1.hid_assig.value=='M_LETTER')");
				out.println(" { ");
				out.println("      ");
				out.println("   	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations4?chksql=m_get_chk_LAKDL_get_recovery_letter&data_val=\"+document.Form1.TXT_PRODUCT_CODE.value+\"&ac_status=Y\";");
				out.println(" } ");
				out.println(" if(m_url!=\"\") ");
				out.println(" {");	
				out.println(" 		load_interface(m_url,'XML');");
				//out.println(" 		window.open(m_url);");
				out.println(" } ");
				out.println(" else ");
				out.println(" { ");
				out.println("    alert('url is not defined');");
				out.println(" } ");
				out.println("}");
				
				out.println("function help_value_assign_finance(oBj) {"); 
				//out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_PRODUCT_CODE.value=oBj.valout[2];"); 
				out.println("     document.Form1.hid_assig.value='M_LETTER'; "); 
				out.println("    makeRequest1(document.Form1.TXT_PRODUCT_CODE);");
				
				//out.println("   	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_get_chk_LAKDL_get_recovery_letter&data_val=\"+document.Form1.TXT_PRODUCT_CODE.value+\"&ac_status=Y\";");
				//out.println(" 		load_interface(m_url,'XML');");
				//out.println(" 		window.open(m_url);");
				
				//out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				//out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
				out.println("}");
				
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>"); 
				out.println("<td width='40%' >");
				out.println("  <div id='DIV_TXT_LETTER_CAT'> </div> ");
				rs=stmt.executeQuery("SELECT DOCUMENT_ID,DOCUMENT_NAME FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS ");
				out.println("    <SELECT class='txt_input' name='TXT_LETETR_CATEGORY' id='TXT_LETETR_CATEGORY' onchange=\"load_letter_category();\" >  ");
				while(rs.next())
				{
					out.println("<OPTION value='"+rs.getString(1)+"'  >");
					out.println(rs.getString(2));
					out.println("</OPTION>");
				}	
				out.println("    </SELECT> "); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>"); 
				out.println("</tr>"); */
				
				
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				
				
				
				
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				//out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	");
				
				//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				//out.println("		alert(\"ASAS\");"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("		}"); 
				
				out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("			team_assign();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("			help_value_assign_user(oBj)");
				out.println("		}"); 
				
				
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				
				out.println("			help_value_assign_finance(oBj)");
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
				/*out.println("function help_client() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				out.println("    m_sql = \"bulkprintClient_Sql\"; ");
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");*/
				
				out.println("function help_update() {"); 
				//out.println("alert(\"AAAA\");");
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				//out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				
				out.println("function help_update_product() {"); 
				///out.println("alert(document.Form1.TXT_PRODUCT_CODE.value+\"****document.Form1.TXT_PRODUCT_CODE.value\");");
				
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_PRODUCT_CODE.value+\"@\"+\"Y@\";");
				out.println("    HelpBox('1','10','0');"); 
				//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_TRAN_CODE_sql','4');"); 
				out.println("}");
				
				
				
				
				out.println("function help_button_user() {"); 
				//out.println("alert(document.Form1.TXT_USER.value+\"document.Form1.TXT_USER.value\");");
				
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				//out.println("    m_sql = \"m_help_collection_officer_colection_rec\";"); 
				out.println("    m_sql = \"m_help_marketing_officer\";"); //Change By Kanishka Dilshan On 19-11-2014
				out.println("    m_criteria =  document.Form1.TXT_USER.value+\"@\"+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				// out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=main_page';"); 
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
				out.println("v_date=0+v_date;");
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
				
				
				/*out.println("function get_system_date() ");
				out.println("{ ");
				out.println("       assignState('M_SYS_DATE') ;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				
				out.println("} ");*/
				
				
				
				out.println("function load_termination_detail() ");
				out.println("{ ");
				out.println("  if(document.Form1.TXT_PRODUCT_CODE.value==\"\") {");
				out.println("  alert(\"Select Product Code\"); ");
				out.println("   }else{ ");
				out.println("   document.Form1.hid_num.value=\"33\"; ");
				out.println("   m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=LOAD_TERMINATION_LETTER&LETTER_CATEGORY=\"+document.Form1.TXT_LETETR_CATEGORY.value+\"&DATE=\"+m_date+\"&PRODUCT=\"+document.Form1.TXT_PRODUCT_CODE.value+\"&COLL_OFFI=\"+document.Form1.TXT_USER.value+\"&BRANCH=\"+document.Form1.TXT_LOCATION_CODE.value; ");
				//out.println("   window.open(m_url); ");//temporary kanishka
				out.println("	load_interface(m_url,'NO'); ");
				out.println("   } ");
				out.println("} ");
				
				
				/*------------------------------------------------------------------------------------------------------*/
				
				// try
				/*out.println("function load_Sinhala() ");
				out.println("{ ");
				out.println("   document.Form1.hid_num.value=\"533\"; ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation_sinhala");
				//out.println("   window.open(m_url); ");//temporary kanishka
				out.println("	load_interface(m_url,'NORM'); ");
				out.println("} ");*/
				
				
				
				out.println("function print_previous_letter(fin_no,app_no,lett_category,m_date,product_type) ");
				out.println("{ ");
				out.println("   document.Form1.hid_num.value=\"33\"; ");
				//out.println("   m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=LOAD_RECOVERY_LETTER_PREVIOUS&LETTER_CATEGORY=\"+lett_category+\"&DATE=\"+m_date+\"&fin_no=\"+fin_no+\"&product_type=\"+product_type+\"&app_no=\"+app_no; ");
				//out.println("   window.open(m_url); ");//temporary kanishka
				//out.println("	load_interface(m_url,'NO'); ");
				out.println("	popupwin = window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1'); ");
				out.println("} ");
				
				
				
				
				
				/*--------------- Print Report Original ------------------------------------------------------------------------------------------------*/
				
				out.println("function print_report(client_code,app_no,due_amount,contract_age,m_date,m_last_invoice_date)");
				out.println("{ ");
				out.println("   letter_category=document.Form1.TXT_LETETR_CATEGORY.value; ");
				out.println("   product_code=document.Form1.TXT_PRODUCT_CODE.value; ");		
				/*For Sinhala-*/out.println("	var lfckv = document.getElementById('sinhala_chq').checked; ");
				out.println("	if (lfckv==true){ ");
				//-----leas1st_reminder
				out.println(" 		if(letter_category==\"LES_1STREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas1_1streminder_Sinhala?chksql=main_page&document_code=LES_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&last_invoice_date=\"+m_last_invoice_date; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				//-----leas2st_reminder
				out.println(" 		if(letter_category==\"LES_2NDREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas2_2ndreminder_Sinhala?chksql=main_page&document_code=LES_2NDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				//-----leas3st_reminder
				out.println(" 		if(letter_category==\"LES_3RDREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas3_3rdreminder_Sinhala?chksql=main_page&document_code=LES_3RDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				//-----leas4final_reminder
				out.println("  		else if(letter_category==\"LES_FINREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas4_finalreminder_Sinhala?chksql=main_page&document_code=LES_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//-----leas3_subs_faliuer
				out.println("		 else if(letter_category==\"LES_SUSF\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas5_substantial_failuer_Sinhala?chksql=main_page&document_code=LES_SUSF&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//-----leas4_accelerated_payment
				out.println(" 		else if(letter_category==\"ACC_PAY\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas6_accelerated_payment_Sinhala?chksql=main_page&document_code=ACC_PAY&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//----leas5_letterof_termi
				out.println(" 		else if(letter_category==\"LES_LETE\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas7_letter_of_termi_Sinhala?chksql=main_page&document_code=LES_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Hire Purchase-----hp1_1st_reminder
				out.println("		 else if(letter_category==\"HP_1STREM\"){ ");
				//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_1st_reminder?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_1st_reminder_Sinhala?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; "); //Added by Nishantha on 11-04-2016 for #19970
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Hire Purchase-----hp1_2nd_reminder
				
				//Added by Nishantha on 11-04-2016 for #19970
				out.println("		 else if(letter_category==\"HP_2STREM\"){ ");				
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_2nd_reminder_Sinhala?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; "); 
				//out.println("           window.open(m_url);");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Hire Purchase-----hp1_3rd_reminder
				out.println("		 else if(letter_category==\"HP_3STREM\"){ ");				
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_3rd_reminder_Sinhala?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; "); 
				//out.println("           window.open(m_url);");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} "); //HP_4STREM
				
				//Hire Purchase-----hp1_4th_reminder
				out.println("		 else if(letter_category==\"HP_4STREM\"){ ");				
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_4th_reminder_Sinhala?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; "); 
				//out.println("           window.open(m_url);");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//End
				
				//----hp2_final_riminder
				out.println("		 else if(letter_category==\"HP_FINREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP2_final_reminder?chksql=main_page&document_code=HP_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---hp3_notice_of_termi
				out.println(" 		else if(letter_category==\"HP_NOTE\" ){ ");
				//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP3_notice_of_termi?chksql=main_page&document_code=HP_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; "); //Commented by Nishantha on 11-04-2016 for #19970
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP3_notice_of_termi_sinhala?chksql=main_page&document_code=HP_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; "); //Added by Nishantha on 11-04-2016 for #19970
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---hp4_letter_of_termi
				out.println("		 else if(letter_category==\"HP_LETE\"  ){ ");
				//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP4_letter_of_termi?chksql=main_page&document_code=HP_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; "); //Commented by Nishantha on 11-04-2016 for #19970
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP4_letter_of_termi_sinhala?chksql=main_page&document_code=HP_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; "); //Added by Nishantha on 11-04-2016 for #19970
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				/*-----------------------------------------------------------------------------------------*/		
				//---Loan1_1st_remin 
				out.println(" 		else if(letter_category==\"LO_1STREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan1_1st_reminder_Sinhala?chksql=main_page&document_code=LO_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println(" 		else if(letter_category==\"LO_2NDREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan2_2st_reminder_Sinhala?chksql=main_page&document_code=LO_2NDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				out.println(" 		else if(letter_category==\"LO_3RDREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan3_3rd_reminder_Sinhala?chksql=main_page&document_code=LO_3RDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println(" 		else if(letter_category==\"LO_4THREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan4_4th_reminder_Sinhala?chksql=main_page&document_code=LO_4THREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---Loan2_final_riminder
				out.println(" 		else if(letter_category==\"LO_FINREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan5_5th_reminder_Sinhala?chksql=main_page&document_code=LO_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---Loan3_notice_of_termi
				out.println(" 		else if(letter_category==\"LO_NOTE\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan6_final_reminder_Sinhala?chksql=main_page&document_code=LO_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Loan4_letter_of_termi 
				out.println(" 		else if(letter_category==\"LO_LETE\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan7_letter_of_termi_Sinhala?chksql=main_page&document_code=LO_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				
				//-------For English	-----------	
				out.println("} "); // close if checked sinhala
				out.println(" else{ ");
				
				out.println(" 		if(letter_category==\"LES_1STREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas1_1streminder?chksql=main_page&document_code=LES_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&last_invoice_date=\"+m_last_invoice_date; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//-----leas2st_reminder
				out.println(" 		if(letter_category==\"LES_2NDREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas2_2ndreminder?chksql=main_page&document_code=LES_2NDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				//-----leas3st_reminder
				out.println(" 		if(letter_category==\"LES_3RDREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas3_3rdreminder?chksql=main_page&document_code=LES_3RDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				//-----leas4final_reminder
				out.println("  		else if(letter_category==\"LES_FINREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas4_finalreminder?chksql=main_page&document_code=LES_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//-----leas3_subs_faliuer
				out.println("		 else if(letter_category==\"LES_SUSF\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas5_substantial_failuer?chksql=main_page&document_code=LES_SUSF&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				//-----leas4_accelerated_payment
				out.println(" 		else if(letter_category==\"ACC_PAY\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas6_accelerated_payment?chksql=main_page&document_code=ACC_PAY&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				//----leas5_letterof_termi
				out.println("		 else if(letter_category==\"LES_LETE\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Leas7_letter_of_termi?chksql=main_page&document_code=LES_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Hire Purchase-----hp1_1st_reminder
				out.println(" 		else if(letter_category==\"HP_1STREM\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP1_1st_reminder?chksql=main_page&document_code=HP_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/				
				//Hire Purchase-----   2 TO 4
				out.println(" 		else if(letter_category==\"HP_2STREM\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP2_2nd_Reminder?chksql=main_page&document_code=HP_2STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println(" 		else if(letter_category==\"HP_3STREM\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP3_3d_Reminder?chksql=main_page&document_code=HP_3STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println(" 		else if(letter_category==\"HP_4STREM\"){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP4_4th_Reminder?chksql=main_page&document_code=HP_4STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount+\"&contract_age=\"+contract_age; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/				
				
				//----hp2_final_riminder
				out.println(" 		else if(letter_category==\"HP_FINREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP2_final_reminder?chksql=main_page&document_code=HP_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---hp3_notice_of_termi
				out.println(" 		else if(letter_category==\"HP_NOTE\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP3_notice_of_termi?chksql=main_page&document_code=HP_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---hp4_letter_of_termi
				out.println(" 		else if(letter_category==\"HP_LETE\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_HP4_letter_of_termi?chksql=main_page&document_code=HP_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				//---Loan1_1st_remin 
				out.println("		 else if(letter_category==\"LO_1STREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan1_1st_reminder?chksql=main_page&document_code=LO_1STREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				out.println("		 else if(letter_category==\"LO_2NDREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan2_2st_reminder?chksql=main_page&document_code=LO_2NDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println(" 		else if(letter_category==\"LO_3RDREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan3_3rd_reminder?chksql=main_page&document_code=LO_3RDREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				out.println("		 else if(letter_category==\"LO_4THREM\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan4_4th_reminder?chksql=main_page&document_code=LO_4THREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				//---Loan2_final_riminder
				out.println(" 		else if(letter_category==\"LO_FINREM\"  ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan5_5th_reminder?chksql=main_page&document_code=LO_FINREM&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//---Loan3_notice_of_termi
				out.println(" 		else if(letter_category==\"LO_NOTE\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan6_final_reminder?chksql=main_page&document_code=LO_NOTE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println(" 		} ");
				
				//Loan4_letter_of_termi 
				out.println("		 else if(letter_category==\"LO_LETE\" ){ ");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RL_DOCS_Recovery_Letter_Loan7_letter_of_termi?chksql=main_page&document_code=LO_LETE&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&date=\"+m_date+\"&product_code=\"+product_code+\"&due_amount=\"+due_amount; ");
				out.println("			popupwin = window.open(m_url); ");
				out.println("		 } ");
				
				
				out.println("  } ");
				out.println("} ");
				
				
				
				
				
				
				out.println("function open_pdf(finance_no,letter_category_id,letter_id,file_path)");
				out.println("{ ");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Recovery_Letter_generation?chksql=DOWNLOAD_PDF&FINANCE_NO=\"+finance_no+\"&LETTER_CATEGORY_ID=\"+letter_category_id+\"&LETTER_ID=\"+letter_id; ");
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
				
				out.println("function assign_system_date(){");
				//  out.println("load_main_bulk_print();");
				//out.println("display_bulk_print();");
				out.println("     document.Form1.VAL_DAY.value='"+m_sys_date_dd+"';");
				out.println("     document.Form1.VAL_MONTH.value='"+m_sys_date_mm+"';");
				out.println("     document.Form1.VAL_YEAR.value='"+m_sys_date_yy+"';");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				
				
				
				out.println("}");
				
				
				
				out.println("</SCRIPT>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date();\"> ");// //load_main_bulk_print();display_bulk_print() //()  //load_lock(), header(),add_row()
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Recovery Letter Genaration  </td>"); 
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
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); */
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='15' style='{width=150px}' size='15' onblur='help_update()' \">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\"Help\" onClick=\"help_update()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style='{width:350}' maxlength='15' size='15' disabled >"); 
				out.println("</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); */
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT_CODE'  class=div_input>Product Code *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PRODUCT_CODE' maxlength='10' style='{width=150px}' size='10' onblur='help_update_product()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_product()\">"); 
				out.println("</td>");
				out.println("</tr>");
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
				out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" ... \" onClick=\"help_finance_no()\">"); 
				out.println("</td>"); 
				out.println("</tr>");*/
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10'   onblur=\"help_button_user() \">");  //,makeRequest(document.Form1.TXT_USER)
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Letter Date </td>");
				out.println("<td width='40%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				
				//out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>"); 
				out.println("</tr>");
				
				
				
				
				
				
				
				/*---------------------------------------SAMITH---------------------------------------------------------------------------------------*/				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>");
				out.println("	<DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>"); 
				
				out.println("<td width='40%' >");
				out.println("   <div id='LET_IN_SINHALA'>Sinhala <input type='checkbox' name='sinhala_chq' id='sinhala_chq' > </div> <div id='DIV_TXT_LETTER_CAT'> </div> ");
				out.println("   <DIV id='DIV_TXT_LETTER_CAT'> </div> ");
				
				out.println("    <SELECT class='txt_input' name='TXT_LETETR_CATEGORY' id='TXT_LETETR_CATEGORY' onchange=\"load_letter_category();\" >  ");
				
				while(rs.next())
				{
					out.println("<OPTION value=\"NA\" SELECTED> N/A</OPTION>");
					out.println("</OPTION>");
				}	
				out.println("    </SELECT> "); 
				
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>"); 
				
				out.println("</tr>");
				
				
				
				/*out.println("<tr>");
				out.println("<td width='20%' >");
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Try Sinhala\" onClick=\"function load_Sinhala()\"> </td>"); 
				out.println("</td>");
				out.println("<td width='40%' >ttttertetet</td>");
				out.println("</tr>"); */
				
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LETTER_CAT'  class=div_input>Letter Category</DIV></td>"); 
				//out.println("<td width='15%' ><DIV id='DIV_TXT_LETTER_CAT_2'  class=div_input>Sinhala <input type='checkbox' name='sinhala_chq' ></DIV></td>");
				// For sinhala check box
				out.println("<td width='20%' >"); // do 40%
				out.println("   <div id='LET_IN_SINHALA'>Sinhala <input type='checkbox' name='sinhala_chq' > </div> <div id='DIV_TXT_LETTER_CAT'> </div> ");
				
				out.println("    <SELECT class='txt_input' name='TXT_LETETR_CATEGORY' id='TXT_LETETR_CATEGORY' onchange=\"load_letter_category();\" >  ");
				
				while(rs.next())
				{
					out.println("<OPTION value=\"NA\" SELECTED> N/A</OPTION>");
					out.println("</OPTION>");
				}	
				out.println("    </SELECT> "); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show\" onClick=\"load_termination_detail()\"> </td>");

			// demo
			    out.println("<td width='20%' >");
				out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Try Sinhala\" onClick=\"load_termination_detailss()\"> </td>");
			
				out.println("</tr>"); 
				*/
				
				
				
				
				out.println("<tr >"); 
				out.println("<td colspan='3' >");
				out.println("  <div id='Leter_div'> </div> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				
				out.println("</table>"); 
				out.println("<br>"); 
				
				
				
				
				
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				
				/*out.println("<TABLE CELLPADDING=\"2\" > ");
				out.println("<TR> ");
				out.println("<TD id=\"change\"> ");
				out.println("	<INPUT TYPE=\"BUTTON\"  class='but_input' NAME=\"button_bulk_print\" style='width:240px; font-weight:bold' ALIGN=\"CENTER\" SIZE=\"25\"  VALUE=\"Bulk Print\" onclick='display_bulk_print()' > ");
				out.println("</TD>");
				out.println("<TD id=\"change1\"> ");
				out.println("   <INPUT TYPE=\"BUTTON\" class='but_input' NAME=\"button_view_bulk_print\" style='width:240px'  ALIGN=\"CENTER\"  VALUE=\"View Print Report\"  onclick=\"display_view_bulk_print()\">");
				out.println("</TD>");
				out.println("</TR> ");
				out.println("</TABLE> ");*/
				
				
				
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
				
				
				
				/*out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At </td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" style=\"{width:110px;}\" onClick=\"print_report()\" >"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" style=\"{width:110px;}\" onClick=\"run_report()\" >");
				out.println("</td>"); 
				out.println("</tr>");
				out.println("</table>");*/
				
				
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

