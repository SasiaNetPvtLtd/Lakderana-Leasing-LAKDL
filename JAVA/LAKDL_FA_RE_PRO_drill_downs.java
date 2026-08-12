import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
//import oracle.jdbc.driver.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_RE_PRO_drill_downs extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	
	public ResultSet rs,rs1,rs2,rs3,rs4;
	public String m_chksql;
	ServletOutputStream out =null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			out = res.getOutputStream();
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
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
			
			
			
			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("SHOW_INVOICE_BATCH_DRILL")){
				
				String m_string="";				
				String m_batch=req.getParameter("batch_no");
				
				rs1= stmt1.executeQuery(" SELECT  "+
					" A.BATCH_NO,   "+//1
					" A.DEBTOR_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//3
					" A.INVOICE_NO,  "+//4
					" NVL(A.INVOICE_AMOUNT,0),  "+//5
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//6
					" DECODE(A.INVOICE_STATUS,'ENTER','Enter','APPR1','Approve','CANCEL','Disapprove','CONF','Approved','APP_C','Approve Level Cancel','APP_2','Approve Level 1 Approval','APP_C2','Credit Approval Cancelation'), "+//7
					" NVL(A.APPROVAL_COMMENTS,'-'), "+//8
					" A.BALANCE_AMOUNT "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A  "+
					" WHERE A.BATCH_NO='"+m_batch+"'");
				
				//Added by Mahela on 04-01-2007
				rs2= stmt.executeQuery(" SELECT  "+
					" A.BATCH_NO,   "+//1
					" A.CLIENT_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),  "+//3
					" NVL(A.TOTAL_BATCH_AMOUNT,0),  "+//4
					" NVL(A.TOTAL_BATCH_INVOICES,0),  "+//5
					" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),  "+//6
					" DECODE(A.APPROVE_STATUS,'ENTER','Enter','APPR1','Approve','CANCEL','Disapprove','CONF','Approve'), "+//7
					" NVL(A.APPROVAL_COMMENTS,'-') "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A  "+
					" WHERE A.BATCH_NO='"+m_batch+"'");
				
				out.println("<HTML><HEAD><TITLE>Invoice Details - Batch No: "+m_batch+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Invoice Details - Batch No  "+m_batch+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				//Added by Mahela on 04-01-2007
				if(rs2.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Name</td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs2.getString(2)+"')\" style='cursor:hand' ><b><u>"+rs2.getString(3)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Total Batch Amount</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs2.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Total Batch Invoices</td>");
					out.println("<td width='50%' class=div_input>"+rs2.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Invoice Batch Date</td>");
					out.println("<td width='50%' class=div_input>"+rs2.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Approve Status</td>");
					out.println("<td width='50%' class=div_input>"+rs2.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Approval Comment</td>");
					out.println("<td width='50%' class=div_input>"+rs2.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}	
				//End of Addition
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>"); //class=txt_report_column
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
				out.println("<td width='15%' align='right'><DIV class=div_input><b>Invoice Amount</b></DIV></td>"); 
				out.println("<td width='15%' align='right'><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input><b>Invoice Status</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				boolean mflag=true;
				while(rs1.next()){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(2)+"','"+rs1.getString(4)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(9))+"</td>");
					out.println("<td width='10%' class=div_input>"+rs1.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs1.getString(7)+"</td>");
					out.println("<td width='15%' >"+rs1.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_MONYH_INVOICE_DETAIL_FOR_CLIENT")){     //SANJEEWA 2010/07/16
				
				String m_client_code="";
				String m_month="";
				String query="";
				m_client_code = req.getParameter("CLIENT_CODE").trim();																		
				m_month = req.getParameter("MONTH").trim();																		
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Invoice Detail Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'></td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 30px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>");
				out.println("</tr>");
				out.println("</table>"); 
				out.println("</table>"); 
				
				
				
				query="SELECT  a.invoice_no,a.invoice_amount,to_char(due_date,'DD-MM-yyyy') "+
					"FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					"WHERE A.client_code='"+m_client_code+"' "+
					"AND A.invoice_status='CONF' "+
					"AND a.due_date >= trunc (to_date('"+m_month+"','mon-yyyy'), 'mon') "+
					"AND a.due_date <= last_day (to_date('"+m_month+"','mon-yyyy')) ";
				
				rs3 = stmt.executeQuery(query);
				boolean more = rs3.next();
				out.println("<br>");
				out.println("<table border=0 class='table' width='75%'>");
				out.println("<tr class='pdn_txtpos2'>");
				out.println("<td width='15%' align='center'>Due Date  </td>");
				out.println("<td width='15%' align='center'>Invoice No  </td>");
				out.println("<td width='15%' align='center'>Invoice Amount</td>");
				out.println("</tr>");
				
				int j = 0;      					
				while(more){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input >");
					}
					else{
						out.println("<tr class=tr_input1 >");	
						
					}
					
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue >"+rs3.getString(3)+"</td>"); 
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue >"+rs3.getString(1)+"</td>"); 
					out.println("<td width='15%' align='right' style= cursor:hand;cursor-color:blue >"+rs3.getString(2)+"</td>"); 
					out.println("</tr>"); 		
					more = rs3.next();
					j=j+1;
				}
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY>"); 
				out.println("</html>"); 
				out.flush();
				
			}
			else if(m_chksql.equals("SHOW_MONYH_INVOICE_DETAIL_FOR_ALL")){     //SANJEEWA 2010/07/16
				
				String m_client_code="";
				String m_month="";
				String query="";
				//m_client_code = req.getParameter("CLIENT_CODE").trim();																		
				m_month = req.getParameter("MONTH").trim();																		
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Invoice Detail Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'></td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 30px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>");
				out.println("</tr>");
				out.println("</table>"); 
				out.println("</table>"); 
				
				
				
				query="SELECT  a.invoice_no,a.invoice_amount,to_char(due_date,'DD-MM-yyyy'),NVL(a.client_code,'-'),NVL(A.facility_no,'-'),NVL(a.debtor_code,'-') "+
					"FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					"WHERE A.invoice_status='CONF' "+
					"AND a.due_date >= trunc (to_date('"+m_month+"','mon-yyyy'), 'mon') "+
					"AND a.due_date <= last_day (to_date('"+m_month+"','mon-yyyy')) ";
				
				rs4 = stmt.executeQuery(query);
				boolean more = rs4.next();
				out.println("<br>");
				out.println("<table border=0 class='table' width='75%'>");
				out.println("<tr class='pdn_txtpos2'>");
				out.println("<td width='15%' align='center'>Client Code </td>");
				out.println("<td width='15%' align='center'>Facility No  </td>");
				out.println("<td width='15%' align='center'>Debtor Code  </td>");
				out.println("<td width='15%' align='center'>Due Date  </td>");
				out.println("<td width='15%' align='center'>Invoice No  </td>");
				out.println("<td width='15%' align='center'>Invoice Amount</td>");
				out.println("</tr>");
				
				int j = 0;      					
				while(more){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input >");	
					}
					else{
						out.println("<tr class=tr_input1 >");
					}
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs4.getString(4)+"')><u>"+rs4.getString(4)+"</u></td>");
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs4.getString(5)+"')><u>"+rs4.getString(5)+"</u></td>");
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue >"+rs4.getString(6)+"</td>");
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue >"+rs4.getString(3)+"</td>"); 
					out.println("<td width='15%' style= cursor:hand;cursor-color:blue )>"+rs4.getString(1)+"</td>"); 
					out.println("<td width='15%' align='right' style= cursor:hand;cursor-color:blue >"+rs4.getString(2)+"</td>"); 
					out.println("</tr>"); 		
					more = rs4.next();
					j=j+1;
				}
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY>"); 
				out.println("</html>"); 
				out.flush();
				
			}
			
			else if(m_chksql.equals("SHOW_CLIENT_INFORMATION")){
				
				String m_string="";				
				
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" A.CLIENT_TYPE, "+//2
					" DECODE(A.CLIENT_TYPE,'I','Individual','Corporate'), "+//3
					" DECODE(A.FACTORING_TYPE,'C','As a Client','D','As a Debtor','As a Client and Debtor'), "+//4
					" A.FULL_NAME, "+//5
					" NVL(A.REGISTERED_ADDRESS1,'-'), "+//6
					" NVL(A.REGISTERED_ADDRESS2, '-'), "+//7
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CITY_CODE,'C'), "+//8
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.AREA_CODE,'A'),  "+//9
					" NVL(A.REGISTERED_REFERENCE, '-'), "+//10
					" NVL(A.REGISTERED_TEL_NO,'-'), "+//11
					" NVL(A.REGISTERED_FAX_NO,'-'), "+//12
					" NVL(A.REGISTERED_OFFICE_TEL_NO,'-'), "+//13
					" NVL(A.REGISTERED_EMAIL,'-'), "+//14
					" NVL(A.REGISTERED_MOBILE_NO,'-'), "+//15
					" NVL(A.KEY_DECISION_MAKER,'-'), "+//16
					" NVL(A.REGISTERED_CONTACT_PERSON,'-'), "+//17
					" NVL(A.DESIGNATION_PAYMENT,'-'), "+//18
					" DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
					" 'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other'), "+//19
					" NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'C','N'),'-'), "+//20
					" NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'M','N'),'-'), "+//21
					" NVL(A.F_STATUS,'-'),"+//22
					" NVL(A.F_ADDRESS1,'-'),"+ //23
					" NVL(A.F_ADDRESS2,'-'),"+ //24
					" NVL(A.F_CONTACT_PERSON,'-'),"+//25 
					" NVL(A.F_TEL_NO,'-'),"+//26
					" NVL(A.F_FAX_NO,'-'),"+ //27
					" NVL(A.F_EMAIL,'-'),"+ //28
					" NVL(A.CORRES_STATUS,'-'),"+//29 
					" NVL(A.CORRES_ADDRESS1,'-'),"+//30
					" NVL(A.CORRES_ADDRESS2,'-'),"+//31
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CORRES_CITY_CODE,'C'), "+//32
					" NVL(A.CORRES_TEL_NO,'-'),"+//33
					" NVL(A.CORRES_FAX_NO,'-'),"+//34
					" NVL(A.CORRES_EMAIL,'-'), "+//35
					" NVL(A.BUSINESS_SUB_SECTOR,'-'), "+//36
					" NVL(A.EXPOSURE_CODE,'-'), "+//37
					" NVL(A.CLIENT_GROUP,'-'), "+//38
					" NVL(A.CRIB_STATUS,'-'), "+//39
					" NVL(A.CRIB_COMMENT,'-'), "+//40
					" NVL(A.CLIENT_CATEGORY,'-'), "+//41
					" NVL(A.BUSINESS_CERTIFICATE_NO,'-'), "+//42
					" NVL(A.ISSUED_SHARE_CAPITAL,0), "+//43
					" NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'),  "+//44
					" NVL(A.VAT_REG_NO,'-'), "+//45
					" NVL(TO_CHAR(A.VAT_REG_DATE,'DD-MM-YYYY'),'-'), "+//46
					" NVL(A.WITH_HOLDING_TAX,'-'), "+//47
					" NVL(A.NIC_NO,'-'), "+//48
					" NVL(A.PASSPORT_NO,'-'),  "+//49
					" NVL(A.TITLE,'-'), "+//50
					" NVL(A.FIRST_NAME,'-'), "+//51
					" NVL(A.SURNAME,'-'), "+//52
					" NVL(A.INITIALS,'-'), "+//53
					" NVL(A.OTHER_NAME,'-'), "+//54
					" NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-') "+//55
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					" WHERE CLIENT_CODE='"+m_client_code+"'");
				
				out.println("<HTML><HEAD><TITLE>Client Information  - "+m_client_code+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("	function assign_type(){");
				out.println("document.Form1.hid_client_type.value='Corporate' ");
				out.println("	}");
				
				//Added by Mahela on 19-12-2006
				out.println("	function show_other_cont_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"other_client_info\"  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_OTHER_CLIENT_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_third_party_guarantor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"guarantor\"  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_THIRD_PARTY_GUARANTOR_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_director_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"director\"  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_DIRECTOR_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_subsidiary_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"subsidiary\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_SUBSIDIARY_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_bank_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"bank\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_BANK_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_auditor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"auditor\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_AUDITOR_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_credit_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"credit\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_CREDIT_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_prop_security_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"security\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_PROP_SECURITY_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				//Added by Mahela on 20-12-2006
				out.println("	function show_product_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"product\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_PRODUCT_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				//Added by Mahela on 20-12-2006
				out.println("	function show_supplier_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"supplier\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_SUPPLIER_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				//Added by Mahela on 20-12-2006
				out.println("	function show_top_debtor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"debtor\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_TOP_DEBTOR_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				//Added by Mahela on 17-07-2007
				out.println("	function show_client_debtor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"client_debtor\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_drill_downs?chksql=SHOW_CLIENT_DEBTOR_INFO&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("function clear_inner(){");
				out.println("      other_cont_info.innerHTML = \"\";");
				out.println("      third_party_guarantor_info.innerHTML = \"\";");
				out.println("      subsidiary_info.innerHTML = \"\";");
				out.println("      bank_info.innerHTML = \"\";");
				out.println("      auditor_info.innerHTML = \"\";");
				out.println("      credit_info.innerHTML = \"\";");
				out.println("      security_info.innerHTML = \"\";");
				out.println("      product_info.innerHTML = \"\";");
				out.println("      supplier_info.innerHTML = \"\";");
				out.println("      top_debtor_info.innerHTML = \"\";");
				out.println("}");
				
				out.println("	function get_vector_normal(http_response){");
				out.println("  if(document.Form1.hid_link_type.value == \"other_client_info\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Other Client Information </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");	
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"guarantor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Third Party Guarantors Details</u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"director\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Directors/Partners/Shareholders Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"subsidiary\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Subsidiaries & Associated Companies Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"bank\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Accounts & Banking Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"auditor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Auditors Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"credit\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Credit Facilities Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"security\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Proposed Security Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"product\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Product Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"supplier\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Suppliers/Customers Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"debtor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Top Debtors/Clients Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"client_debtor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Client/Debtors Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("	}");
				//End of Addition
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				//Added by Mahela on 19-12-2006
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_director_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_guarantor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_subsidiary_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_auditor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_credit_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_security_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_product_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_supplier_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_debtor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_other_client_info_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_all_info_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_link_type' VALUE=\"\">"); 
				//End of Addition.
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Client Information  - "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Status</td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(19)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Type</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Factoring type</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Manager</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Marketing Executive</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><u><b>Contact Information</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Reference</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>General Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>General FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Office Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Key Decision Maker</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Designation Payment</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_other_cont_info('"+m_client_code+"')\"><u><b>More Client Information &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"other_cont_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("</table>");
					//Added by Mahela on 19-12-2006
					if(rs.getString(3).equals("Corporate")){
						//out.println("assign_type();");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_director_info('"+m_client_code+"')\"><u><b>Directors/Partners/Shareholders Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"director_info\" >");
						out.println("</div>");
					}
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_third_party_guarantor_info('"+m_client_code+"')\"><u><b>Third Party Guarantors Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"third_party_guarantor_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_subsidiary_info('"+m_client_code+"')\"><u><b>Subsidiaries & Associated Companies Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"subsidiary_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_bank_info('"+m_client_code+"')\"><u><b>Accounts & Banking Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"bank_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_auditor_info('"+m_client_code+"')\"><u><b>Auditors Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"auditor_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_credit_info('"+m_client_code+"')\"><u><b>Credit Facilities Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"credit_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_prop_security_info('"+m_client_code+"')\"><u><b>Proposed Security Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"security_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_product_info('"+m_client_code+"')\"><u><b>Product Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"product_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_supplier_info('"+m_client_code+"')\"><u><b>Suppliers/Customers Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"supplier_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_top_debtor_info('"+m_client_code+"')\"><u><b>Top Debtors/Clients Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"top_debtor_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_client_debtor_info('"+m_client_code+"')\"><u><b>Client/Debtors Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br>");
					out.println("<div id=\"label_all\" >");
					out.println("</div>");
					out.println("<div id=\"all_info\" >");
					out.println("</div>");
					//End of Addition.
					//---------------------------  Client Disputes ----------------------------------------------------------------
					//String m_facility_no=req.getParameter("FACILITY_NO");
					
					rs1= stmt1.executeQuery(" SELECT"+
						" TO_CHAR(COMMENT_ENT_DATE,'DD-MM-YYYY / HH:MM:SS') COMMENT_ENT_DATE, "+
						" CLIENT_DISPUTES, "+
						" ENT_USER "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_DISPUTES "+
						" WHERE CLIENT_CODE='"+m_client_code+"' ORDER BY COMMENT_ENT_DATE DESC ");
					
					boolean more =  rs1.next();
					
					out.println("<table align='center' width='100%' class='table' >");
					if(more){
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='*%' align='center' ><DIV class=div_input><b>Client Disputes </b></DIV></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='10%' ><DIV class=div_input><b>Entered User</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Date / Time </b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>Comment</b></DIV></td>");
						out.println("</tr>");
					}
					int j=1;
					
					while(more){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						out.println("<td width='15%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='*%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("</tr>");
						more =  rs1.next();
					}
					out.println("</table>");
				}
				else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			else if(m_chksql.equals("SHOW_OTHER_CLIENT_INFO")){
				
				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" A.CLIENT_TYPE, "+//2
					" DECODE(A.CLIENT_TYPE,'I','Individual','Corporate'), "+//3
					" DECODE(A.FACTORING_TYPE,'C','As a Client','D','As a Debtor','As a Client and Debtor'), "+//4
					" A.FULL_NAME, "+//5
					" NVL(A.REGISTERED_ADDRESS1,'-'), "+//6
					" NVL(A.REGISTERED_ADDRESS2, '-'), "+//7
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CITY_CODE,'C'), "+//8
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.AREA_CODE,'A'),  "+//9
					" NVL(A.REGISTERED_REFERENCE, '-'), "+//10
					" NVL(A.REGISTERED_TEL_NO,'-'), "+//11
					" NVL(A.REGISTERED_FAX_NO,'-'), "+//12
					" NVL(A.REGISTERED_OFFICE_TEL_NO,'-'), "+//13
					" NVL(A.REGISTERED_EMAIL,'-'), "+//14
					" NVL(A.REGISTERED_MOBILE_NO,'-'), "+//15
					" NVL(A.KEY_DECISION_MAKER,'-'), "+//16
					" NVL(A.REGISTERED_CONTACT_PERSON,'-'), "+//17
					" NVL(A.DESIGNATION_PAYMENT,'-'), "+//18
					" DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
					" 'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other'), "+//19
					" NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'C','N'),'-'), "+//20
					" NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'M','N'),'-'), "+//21
					" NVL(A.F_STATUS,'-'),"+//22
					" NVL(A.F_ADDRESS1,'-'),"+ //23
					" NVL(A.F_ADDRESS2,'-'),"+ //24
					" NVL(A.F_CONTACT_PERSON,'-'),"+//25 
					" NVL(A.F_TEL_NO,'-'),"+//26
					" NVL(A.F_FAX_NO,'-'),"+ //27
					" NVL(A.F_EMAIL,'-'),"+ //28
					" NVL(A.CORRES_STATUS,'-'),"+//29 
					" NVL(A.CORRES_ADDRESS1,'-'),"+//30
					" NVL(A.CORRES_ADDRESS2,'-'),"+//31
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CORRES_CITY_CODE,'C'), "+//32
					" NVL(A.CORRES_TEL_NO,'-'),"+//33
					" NVL(A.CORRES_FAX_NO,'-'),"+//34
					" NVL(A.CORRES_EMAIL,'-'), "+//35
					" NVL(A.BUSINESS_SUB_SECTOR,'-'), "+//36
					" NVL(A.EXPOSURE_CODE,'-'), "+//37
					" NVL(A.CLIENT_GROUP,'-'), "+//38
					" NVL(A.CRIB_STATUS,'-'), "+//39
					" NVL(A.CRIB_COMMENT,'-'), "+//40
					" NVL("+m_schema_name+".FA_GET_CLIENT_LEGAL_CAT(A.CLIENT_CATEGORY),'-'), "+//41
					" NVL(A.BUSINESS_CERTIFICATE_NO,'-'), "+//42
					" NVL(A.ISSUED_SHARE_CAPITAL,0), "+//43
					" NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'),  "+//44
					" NVL(A.VAT_REG_NO,'-'), "+//45
					" NVL(TO_CHAR(A.VAT_REG_DATE,'DD-MM-YYYY'),'-'), "+//46
					" NVL(A.WITH_HOLDING_TAX,'-'), "+//47
					" NVL(A.NIC_NO,'-'), "+//48
					" NVL(A.PASSPORT_NO,'-'),  "+//49
					" NVL(A.TITLE,'-'), "+//50
					" NVL(A.FIRST_NAME,'-'), "+//51
					" NVL(A.SURNAME,'-'), "+//52
					" NVL(A.INITIALS,'-'), "+//53
					" NVL(A.OTHER_NAME,'-'), "+//54
					" NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-') "+//55
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					" WHERE CLIENT_CODE='"+m_client_code+"'");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>CRIB Status</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(39)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Comments</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(40)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Business Sub Sector</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Exposure Category</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(37)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					/*out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Group</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='30%' class=div_input></td>");
					out.println("<td width='*%'></td>");*/
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					if(rs.getString(3).equals("Corporate")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Business Certification No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(42)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Legal Category</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(41)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Incorporation</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(44)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Issued share Capital Rs.</b></td>");
						out.println("<td width='30%' class=div_input>"+nf.format(rs.getDouble(43))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>VAT Reg. No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(45)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>VAT Reg. Date</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(46)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>With Holding TAX</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(47)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else{
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Title</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(50)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>First Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(51)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Last Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(52)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Initials</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(53)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Other Names</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(54)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>NIC No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(48)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Passport No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(49)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Birth</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(55)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Factory Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Factory Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(23)+" "+rs.getString(24)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Correspondence Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(30)+" "+rs.getString(31)+" "+rs.getString(32)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(34)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(35)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
				else{
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
			}
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_THIRD_PARTY_GUARANTOR_INFO")){
				
				int count = 0;	
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" A.GUA_NAME,   "+//1
					" NVL(A.GUA_NIC_NO,'-'),  "+//2
					" NVL(A.GUA_ADDRESS,'-'),  "+//3
					" NVL(A.GUA_CONTACT_NO,'-'),  "+//4
					"	NVL(COMMENTS,'-')  "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN A  "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_gua = rs.next();
				if (!more_gua) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_gua){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Guarantor Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Guarantor NIC</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Guarantor Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Guarantor Contact No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//Add by delanjal
					//date	 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");				
					out.println("</table>");
					out.println("<br>");
					more_gua = rs.next();
				}
				
			}
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_DIRECTOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" DIR_NAME,   "+//1
					" NVL(DIR_NIC_NO,'-'),  "+//2
					" NVL(DIR_STAKE,0),  "+//3
					" NVL(DIR_NO_OF_SHARES,0),  "+//4
					" NVL(DIR_VALUE,0),  "+//5
					" NVL(DIR_POSITION,'-'),  "+//6
					" NVL(GUARANTOR_STATUS,'-'),  "+//7
					" NVL(COMMENTS,'-'), "+ // 8
					" NVL(ADDRESS,'-')  "+ // 9 Added by Udara Somathilake on 13-10-2010 to Add the Address 
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Director Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Director NIC</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Stake</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Number of Shares</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Position</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Guarantor Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//ADDED BY DELANJALI ON 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					// Added by Udara Somathilake on 13-10-2010 to Add Address
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}	
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_SUBSIDIARY_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NAME,   "+//1
					" NVL(STAKE,0),  "+//2
					" NVL(VALUE,0),  "+//3
					" NVL(TEL_NO,'-'),  "+//4
					" NVL(OFFICER,'-'),  "+//5
					" NVL(ACTIVITIES,'-'),  "+//6
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_SUBSIDIA  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_comp = rs.next();
				if (!more_comp) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_comp){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Company Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Stake</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No.</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Officer</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Business Activities</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//added by delanjali on 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
					more_comp = rs.next();
				}	
				
			}
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_BANK_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE),   "+//1
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),  "+//2
					" NVL(ACCOUNT_NO,'-'),  "+//3
					" NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),'-'),  "+//4
					" NVL(REFERENCE,'-'),  "+//5
					" NVL(TEL_NO,'-'),  "+//6
					" NVL(FAX_NO,'-'),  "+//7
					" NVL(RELATIONSHIP,0),  "+//8
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_BANKS  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Bank Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Branch Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Account No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>From Date</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Reference</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//ADDED bY DELANJALI ON 2007-08-03
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_AUDITOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NVL(AUDITOR_NAME,'-'),  "+//1
					" NVL(AUDITOR_ADD,'-'),  "+//2
					" NVL(REFERENCE,'-'),  "+//3
					" NVL(TEL_NO,'-'),  "+//4
					" NVL(FAX_NO,'-'),  "+//5
					" NVL(RELATIONSHIP,0),  "+//6
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_AUDITORS  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Auditor Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Auditor Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Reference</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//Added by delanjali on 2007-08-03
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_CREDIT_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NVL(INSTITUTION,'-'),  "+//1
					" NVL(CONTACT_PERSON,'-'),  "+//2
					" DECODE(TYPE_OF_FACILITY,'TL','Term Loan','L','Lease','OD','Over Draft','PL','Pledge Loan','CBD','Check or Bill Discounting','LCF','LC Facility','F','Factoring'),  "+//3
					" NVL(SECURITY,'-'),  "+//4
					" NVL(APPROVED_AMOUNT,0),  "+//5
					" NVL(MONTHLY_RENTAL,0),  "+//6
					" NVL(MONTHS,0),  "+//7
					" NVL(PAYABLE,0),  "+//8
					" NVL(BALANCE_AMOUNT,0),  "+//9
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CREDIT_FACILITIES  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Institute Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Type Of Facility</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Equipment/Security</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Approved Amount</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Monthly Rental</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Period (Mts.)</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Balance Payable (Mts.)</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Balance Outstanding </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//added by delanjali on 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}			
			//Added by Mahela on 19-12-2006
			else if(m_chksql.equals("SHOW_PROP_SECURITY_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" OWNERSHIP,   "+//1
					" NVL(TYPE_OF_SECURITY,'-'),  "+//2
					" NVL(VALUE,0),  "+//3
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_PROP_SECU   "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_gua = rs.next();
				if (!more_gua) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_gua){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Ownership</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Type Of Security</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//added by delanjali on 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_gua = rs.next();
				}
				
			}			
			//Added by Mahela on 20-12-2006
			else if(m_chksql.equals("SHOW_PRODUCT_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" DECODE(a.NATURE_OF_PRODUCT,'G','Goods','S','Services','O','Other'),  "+//1
					" NVL(b.PRODUCT_CATEGORY_DESC,'-'),  "+//2
					" NVL(a.PRODUCT_NAME,'-'),  "+//3
					" NVL(a.PRODUCT_DESC,'-'),  "+//4
					" DECODE(a.ESTABLISH_IN_MKT,'Y','Yes','N','No'),  "+//5
					" NVL(a.ESTABLISH_IN_MKT_COMMENT,'-'),  "+//6
					" DECODE(a.PROT_GROWTH_IN_MKT,'Y','Yes','N','No'),"+//7
					" NVL(a.PROT_GROWTH_IN_MKT_COMMENT,'-'),  "+//8
					" NVL(a.PRESENT_MKT_SHARE,0),  "+//9
					" NVL(a.DETAIL_PRODUCT_MARKET_SHARE,'-'),  "+//10
					" NVL(a.PRODUCT_SUB_SECTOR,'-'),  "+//11
					" DECODE(a.PRODUCT_SEASONAL,'Y','Yes','N','No'),  "+//12
					" NVL(A.COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_PROD a,"+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY b  "+
					" WHERE a.CLIENT_CODE='"+m_client_code+"' AND  a.PRODUCT_CATEGORY=b.PRODUCT_CATEGORY  ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Nature of the Product</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Product Category</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Name/Brand of the Product </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Description of the Product</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Establish in the Market</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Potential to Growth</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Present Market share (%) </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Details Of Present Market share  </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Business Sub Sector </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Sales Trend is Seasonal</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//ADDED BY DELANJALI ON 2007-08-03
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			//Added by Mahela on 20-12-2006
			else if(m_chksql.equals("SHOW_SUPPLIER_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NVL(CUSTOMER_NAME,'-'),  "+//1
					" NVL(ADDRESS,'-'),  "+//2
					" DECODE(TYPE,'SUP-M','Supplier - Manufacturer','SUP-D','Supplier - Distributor','SUP-I','Supplier - Importer','CUST-F','Customer - Foreign','CUST-L','Customer - Local'),  "+//3
					" NVL(TEL_NO,'-'),  "+//4
					" NVL(CONTACT_PERSON,'-'),  "+//5
					" NVL(RELATIONSHIP,0),  "+//6
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_CUSTOMERS  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input> Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Type</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//ADDED BY DELANJALI ON 2007-08-03
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			
			//Added by Mahela on 20-12-2006
			else if(m_chksql.equals("SHOW_TOP_DEBTOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NVL(DEBTOR_NAME,'-'),  "+//1
					" NVL(DEBTOR_ADDRESS,'-'),  "+//2
					" NVL(DEBTOR_TELE_NO,'-'),  "+//3
					" NVL(DEBTOR_FAX_NO,'-'),  "+//4
					" NVL(DEBTOR_CONT_PERSON,'-'),  "+//5
					" NVL(DEBTOR_AVG_SALES,0),  "+//6
					" NVL(DEBTOR_INVOICE_AMT,0),  "+//7
					" NVL(DEBTOR_COMMENTS,'-')  "+//8
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_TOP_DEBT  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Debtor/Client Name </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input> Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Avg. Sales Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Avg. Invoice Amount</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Comments </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}//End of Addition.
			
			//Added by Mahela on 17-07-2007
			else if(m_chksql.equals("SHOW_CLIENT_DEBTOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
				rs= stmt1.executeQuery(" SELECT "+
					"  NVL(NO_OF_DEBTORS,0), "+//1
					"  DECODE(BASIS_OF_CREDIT_GRANT,'Y','Yes','No'), "+//2
					"  NVL(AVG_CREDIT_PERIOD_EXT,0), "+//3
					"  NVL(AVG_CHEQUE_RETURN,0), "+//4
					"  NVL(AVG_DEPOSIT_ON_BANK,0), "+//5
					"  NVL(AVG_GOODS_CR_RETURNS,0), "+//6
					"  NVL(AVG_BILLING_CYCLE,0), "+//7
					"  NVL(AVG_SALES_PER_MONTH,0), "+//8
					"  NVL(AVG_SALES_PER_DEBTOR,0), "+//9
					"  NVL(AVG_INVOICE_SIZE_PER_TOP_DEBT,0), "+//10
					"  NVL(CREDIT_GRANTED_SECURE_DETAIL,'-'), "+//11
					"  NVL(AVG_TOLARENCE_PERIOD,0) "+//12
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "); 
				boolean more_dir = rs.next();
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>No Of Debtors </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input> Basis Of Credit Granted Secure</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Credit Period Extended</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Cheque Return (%)</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Deposits On Bank A/Cs </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Goods Return/CR Notes (%)</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Billing Cycle In days</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Sales Per Month (Rs.) </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Sales Per Debtor </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Invoice Size Per Top Debtor </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Credit Granted Secure Details </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>Average Tolerance Period  </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				
			}//End of Addition.
			
			//Modified by Mahela on 01-01-2007
			else if(m_chksql.equals("SHOW_INVOICE_DETAIL_DRILL")){ 
				
				String m_string="";				
				
				String m_invoice_no=req.getParameter("invoice_no");
				String m_debtor_code=req.getParameter("debtor_no");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" A.BATCH_NO,   "+//1
					" A.DEBTOR_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//3
					" A.INVOICE_NO,  "+//4
					" NVL(A.INVOICE_AMOUNT,0),  "+//5
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//6
					" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+//7
					" TO_CHAR(A.TOLARENCE_END_DATE,'DD-MM-YYYY'),  "+//8
					"	NVL(ADJUSTMENT_AMOUNT,0),"+//9
					"	NVL(NET_INVOICE_AMOUNT,0),"+//10
					"	NVL(SETTLE_AMOUNT,0),"+//11
					"	NVL(CURR_CODE,'-'),"+//12
					"	NVL(EXCHANGE_RATE,0),"+//13
					"	NVL(AMOUNT_RPT_CURR,0),"+//14
					"	NVL(EXCHANGE_GAIN_LOSS,0),"+//15
					" NVL(A.INVOICE_COMMENTS,'-'),  "+//16
					" DECODE(A.INVOICE_STATUS,'ENTER','Enter','APPR1','Approve','CANCEL','Disapprove','CONF','Approved','APP_C','Approve Level Cancel','APP_2','Approve Level 1 Approval','APP_C2','Credit Approval Cancelation'), "+//17
					" NVL(A.ENT_USER,'-'),"+//18
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'),"+//19
					" NVL(A.MOD_USER,'-'),"+//20
					" NVL(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY HH24:MI'),' '),"+//21
					" NVL(A.APP_USER,'-'),"+//22
					" TO_CHAR(A.APP_DATE,'DD-MM-YYYY HH24:MI'),"+//23
					" NVL(A.APPROVAL_COMMENTS,'-'), "+//24
					" INVOICE_SEQ_NO, "+//25
					" BALANCE_AMOUNT, "+//26
					" A.CLIENT_CODE, "+//27 
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),  "+//28
					" NVL(A.REFERENCE_NO,'-') "+//29
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A  "+
					" WHERE A.INVOICE_NO='"+m_invoice_no+"' AND A.DEBTOR_CODE='"+m_debtor_code+"'");
				
				out.println("<HTML><HEAD><TITLE>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				String m_inv_seq_no="";
				String m_batch_no = "";
				if(rs.next()){
					m_batch_no = rs.getString(1);//Added BY Sandun on 08-07-2009
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(27)+" - "+rs.getString(28)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+" - "+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//============================================== add by indika 08/09/08 =============================================================					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reference No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(29)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//============================================== end by indika 08/09/08 =============================================================				
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(17)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Amount Rs.</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Due Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Resource Period</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Adjustment Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Net Invoice Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(26))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comment</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered User</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(18)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered Date/Time</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(19)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(11).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified User</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(20)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified Date/Time</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(21)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					if(!rs.getString(13).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve User</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(22)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve Date/Time</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(23)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve Comments</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(24)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						m_inv_seq_no=rs.getString(25);
						//End of modification on 01-01-2007
					}
					out.println("</table>");
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='*%' class=div_input><b>Settlement Details</b></td>");
					out.println("</tr>");
					out.println("</table>");
					rs.close();
					
					rs= stmt1.executeQuery(" SELECT A.RECEIPT_NO,"+
						" A.RECEIPT_AMOUNT,"+
						" A.ALLOCATED_AMOUNT,"+
						" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY') "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A "+
						" WHERE INVOICE_NO='"+m_inv_seq_no+"' "+
						" ORDER BY A.ALLOCATED_DATE ");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='10%' class=div_input><b>Allo Amount</b></td>");
					out.println("<td width='10%' class=div_input><b>Allo Date</b></td>");
					out.println("</tr>");
					while(rs.next()){
						out.println("<tr>");
						out.println("<td width='15%' onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand'><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("</tr>");				
					}
					out.println("</table>");
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Followup Comments</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs=stmt1.executeQuery(" SELECT INVOICE_SEQ_NO, "+
						" ENT_USER, "+
						" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+
						" NVL(COMMENTS,'-') "+
						" FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
						" WHERE INVOICE_SEQ_NO='"+m_inv_seq_no+"' "+
						" ORDER BY ENT_DATE DESC ");
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input><b>Enter User</b></td>"); 
					out.println("<td width=\"25%\" class=div_input><b>Enter Date/Time</b></td>"); 
					out.println("<td width=\"60%\" class=div_input><b>Comments</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
						out.println("<tr >");
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(2)+"</td>"); 
						out.println("<td width=\"25%\" class=div_input>"+rs.getString(3)+"</td>"); 
						out.println("<td width=\"60%\" class=div_input>"+rs.getString(4)+"</td>"); 
						out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<BR>");
					//Added by Dineth on 2008-09-05
					rs= stmt1.executeQuery(" SELECT A.POD_REF_NO,"+//1
						" A.CLIENT_CODE,"+//2
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//3
						" A.FACILITY_NO,"+//4
						" A.BATCH_NO,"+//5
						" A.DEBTOR_CODE,"+//6
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//7
						" A.INVOICE_NO,"+//8
						" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'), "+//9
						" B.CHEQUE_NO,"+//10
						" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//11
						" TO_CHAR(B.PD_REALISE_DATE,'DD-MM-YYYY'),"+//12
						" B.CHEQUE_AMOUNT,"+//13
						" NVL(DECODE(B.POD_STATUS,'Y','REALISE','N','NOT REALISE','C','CANCEL',B.POD_STATUS),'-'),"+//14
						" C.INVOICE_AMOUNT,"+//15
						" C.BALANCE_AMOUNT,"+//16
						" TO_CHAR(C.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//17
						" NVL(A.ALLO_AMOUNT,0) "+//18
						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
						" WHERE A.POD_REF_NO=B.POD_REF_NO "+
						" AND A.CLIENT_CODE=C.CLIENT_CODE "+
						" AND A.FACILITY_NO=C.FACILITY_NO "+
						" AND A.BATCH_NO=C.BATCH_NO "+
						" AND A.INVOICE_NO=C.INVOICE_NO "+
						//" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_NO='"+m_invoice_no+"' "+
						" AND A.BATCH_NO = '"+m_batch_no+"' "+
						" ORDER BY A.FACILITY_NO,B.CHEQUE_NO,A.DEBTOR_CODE,A.BATCH_NO,B.CHEQUE_DATE ");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>PD Cheque Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					/*
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"12%\" class=div_input><b>PD Ref No</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Cheque No</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Branch Name</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Cheque Date</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Realise Date</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>PD Amount</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Invoice Amount</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Bal. Amount</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
					out.println("<tr >");
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(1)+"</td>"); 
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(10)+"</td>"); 
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(11)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getString(12)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(13)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(15)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(16)+"</td>"); 
					
					out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<BR>");*/
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input><b>PD Ref No</b></td>"); 
					out.println("<td width=\"10%\" class=div_input><b>Cheque No</b></td>"); 
					//out.println("<td width=\"12%\" class=div_input><b>Branch Name</b></td>"); 
					//out.println("<td width=\"12%\" class=div_input><b>Cheque Date</b></td>"); 
					//out.println("<td width=\"13%\" class=div_input><b>Realise Date</b></td>"); 
					out.println("<td width=\"15%\" class=div_input><b>Cheque Amount</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>&nbsp;&nbsp;Allocation Amount</b></td>"); 
					//out.println("<td width=\"5%\" class=div_input>&nbsp;</td>"); 
					out.println("<td width=\"20%\" class=div_input><b>&nbsp;&nbsp;Cheque Date</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Chq Status</b></td>");
					//out.println("<td width=\"13%\" class=div_input><b>Invoice Amount</b></td>"); 
					//out.println("<td width=\"13%\" class=div_input><b>Bal. Amount</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
						out.println("<tr >");
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(1)+"</td>"); 
						out.println("<td width=\"10%\" class=div_input>"+rs.getString(10)+"</td>"); 
						//out.println("<td width=\"12%\" class=div_input>"+rs.getString(9)+"</td>");
						//out.println("<td width=\"12%\" class=div_input>"+rs.getString(11)+"</td>"); 
						//out.println("<td width=\"13%\" class=div_input>"+rs.getString(12)+"</td>"); 
						out.println("<td width=\"15%\" class=div_input style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); 
						out.println("<td width=\"20%\" class=div_input style='text-align:right'>&nbsp;&nbsp;"+nf.format(rs.getDouble(18))+"</td>"); 
						//out.println("<td width=\"5%\" class=div_input>&nbsp</td>");
						out.println("<td width=\"20%\" class=div_input>&nbsp;&nbsp;"+rs.getString(11)+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(14)+"</td>"); 
						//out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(15)+"</td>"); 
						//out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(16)+"</td>"); 
						
						out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<BR>");
					
					//end by Dinethon 2008-09-05
					//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
					//Sandun on 29-06-2009
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Receipts Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					/*rs= stmt1.executeQuery(" SELECT A.RECEIPT_NO,"+
						" A.CHEQUE_NO, "+
						" A.REC_AMOUNT, "+
						" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.INVOICE_NO = '"+m_invoice_no+"' ");
						*/
						
						rs= stmt1.executeQuery(
						" SELECT B.RECEIPT_NO RECEIPT_NO , B.REC_AMOUNT REC_AMOUNT ,SUM(A.ALLO_AMOUNT) ALLO_AMOUNT ,NVL(B.CHEQUE_NO,'-') CHEQUE_NO,B.REC_STATUS REC_STATUS  "+
					    " FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
						" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+ 
						" AND A.BATCH_NO=C.BATCH_NO "+
						" AND A.INVOICE_NO=C.INVOICE_NO "+
						" AND A.DEBTOR_CODE=C.DEBTOR_CODE  "+
						" AND A.BATCH_NO = '"+m_batch_no+"' "+
						" AND A.INVOICE_NO='"+m_invoice_no+"' "+
						" GROUP BY   B.RECEIPT_NO,B.REC_AMOUNT,B.CHEQUE_NO,B.REC_STATUS " );
					
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input><b>Receipt Number</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Rec Amount</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Allocation Amount</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Cheque No</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Rec Status</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
						out.println("<tr >");
						out.println("<td width=\"20%\" class=div_input>"+rs.getString("RECEIPT_NO")+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+rs.getString("REC_AMOUNT")+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+nf.format(rs.getDouble("ALLO_AMOUNT"))+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+rs.getString("CHEQUE_NO")+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+rs.getString("REC_STATUS")+"</td>"); 
						out.println("</tr>");
					}
					out.println("</table>");
					
					out.println("<BR>");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Adjustment Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					rs= stmt1.executeQuery(" SELECT A.ADJUSTMENT_NO, "+
						" A.ADJUST_AMOUNT, "+
						" TO_CHAR(ADJUST_DATE,'DD-MM-RRRR'), "+
						" A.ADJUST_TYPE "+
						" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
						" WHERE A.INVOICE_NO = '"+m_invoice_no+"' "+
						" AND   A.BATCH_NO   = '"+m_batch_no+"' ");
					
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input><b>Ref No</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Adjustment Amount</b></td>"); 
					
					out.println("<td width=\"20%\" class=div_input><b>Adjustment Date</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>Adjustment Type</b></td>"); 
					out.println("<td width=\"20%\" class=div_input><b>&nbsp;</b></td>");
					out.println("</tr>");
					
					while(rs.next()){
						out.println("<tr >");
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(1)+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+nf.format(rs.getDouble(2))+"</td>"); 
						
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(3)+"</td>"); 
						out.println("<td width=\"20%\" class=div_input>"+rs.getString(4)+"</td>"); 
						out.println("<td width=\"20%\" class=div_input><b>&nbsp;</b></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					
					out.println("<BR>");
					out.println("<BR>");
					
					
					//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</B></TD></TR>");
					out.println("</TABLE>");
				}
				//  Added by Dineth on 2008-09-04
				/*rs= stmt1.executeQuery(" SELECT A.POD_REF_NO,"+//1
				" A.CLIENT_CODE,"+//2
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//3
				" A.FACILITY_NO,"+//4
				" A.BATCH_NO,"+//5
				" A.DEBTOR_CODE,"+//6
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//7
				" A.INVOICE_NO,"+//8
				" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'), "+//9
				" B.CHEQUE_NO,"+//10
				" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//11
				" TO_CHAR(B.PD_REALISE_DATE,'DD-MM-YYYY'),"+//12
				" B.CHEQUE_AMOUNT,"+//13
				" DECODE(B.POD_STATUS,'Y','REALISE','N','NOT REALISE','C','CANCEL'),"+//14
				" C.INVOICE_AMOUNT,"+//15
				" C.BALANCE_AMOUNT,"+//16
				" TO_CHAR(C.TOLARENCE_END_DATE,'DD-MM-YYYY') "+//17
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
				" WHERE A.POD_REF_NO=B.POD_REF_NO "+
				" AND A.CLIENT_CODE=C.CLIENT_CODE "+
				" AND A.FACILITY_NO=C.FACILITY_NO "+
				" AND A.BATCH_NO=C.BATCH_NO "+
				" AND A.INVOICE_NO=C.INVOICE_NO "+
				//" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				//" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.INVOICE_NO='"+m_invoice_no+"' "+
				" ORDER BY A.FACILITY_NO,B.CHEQUE_NO,A.DEBTOR_CODE,A.BATCH_NO,B.CHEQUE_DATE ");
			  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"12%\" class=div_input><b>PD Ref No</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Cheque No</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Branch Name</b></td>"); 
					out.println("<td width=\"12%\" class=div_input><b>Cheque Date</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Realise Date</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>PD Amount</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Invoice Amount</b></td>"); 
					out.println("<td width=\"13%\" class=div_input><b>Bal. Amount</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
					out.println("<tr >");
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(1)+"</td>"); 
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(10)+"</td>"); 
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width=\"12%\" class=div_input>"+rs.getString(11)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getString(12)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(13)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(15)+"</td>"); 
					out.println("<td width=\"13%\" class=div_input>"+rs.getDouble(16)+"</td>"); 
					
					out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<BR>");
				
				*/
				//  End by Dineth on 2008-09-04
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_QUOTATION_DETAIL_DRILL")){
				
				String m_string="";				
				
				String m_quotation_no=req.getParameter("quotation_no");
				
				rs= stmt1.executeQuery(" SELECT A.QUOTATION_NO, "+//1
					" A.QUOTATION_STATUS, "+//2
					" DECODE(A.QUOTATION_STATUS,'Y','Approved','C','Disapproved','N','Wait for Approval'), "+//3
					" A.CLIENT_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//5
					" A.INQUERY_NO,  "+//6
					" NVL(A.CREDIT_LIMIT,0), "+//7
					" NVL(A.CREDIT_PERIOD,0), "+//8
					" NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+//9
					" NVL(A.RESERVE_MARGIN,0), "+//10
					" NVL(A.INT_RATE,0), "+//11
					" A.ENT_USER,  "+//12
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'), "+//13
					" NVL(A.APP_USER,'-'), "+//14
					" TO_CHAR(A.APP_DATE,'DD-MM-YYYY HH24:MI'), "+//15
					" NVL(A.APPROVAL_COMMENTS,'-'), "+//16
					" A.FA_PRODUCT_CODE, "+//17
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(A.FA_PRODUCT_CODE), "+//18
					" A.FEE_PACK_CODE, "+//19
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(A.FEE_PACK_CODE) "+//20
					" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION A "+
					" WHERE A.QUOTATION_NO='"+m_quotation_no+"'");
				
				out.println("<HTML><HEAD><TITLE>Quatation Details - Quatation No: "+m_quotation_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Quatation Details - Quatation No: "+m_quotation_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Quatation No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Quatation Status</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input  onClick=\"show_client('"+rs.getString(4)+"')\" style='cursor:hand'><b><u>"+rs.getString(5)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Inquiry No</b></td>");
					out.println("<td width='50%' class=div_input style='cursor:hand'><b><u>"+rs.getString(3)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Quatation Status</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Credit Limit</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Credit Period</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Tolarence Credit Period</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reserve Margin (%)</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Interest Rate (%)</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Enter User/Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(12)+" - "+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(2).equals("N")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approved User/Date/Comments</b></td>");
						out.println("<td width='50%' class=div_input >"+rs.getString(14)+" - "+rs.getString(15)+" - "+rs.getString(16)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Product Package Details - "+rs.getString(17)+"  "+rs.getString(18)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					rs1= stmt.executeQuery(" SELECT A.FA_FEATURE_CODE,B.FA_FEATURE_DESC,NVL(A.PARAMETER_VALUE,'-') "+
						" FROM  "+m_schema_name+".FA_MK_PRO_QUOTATION_PRODUCT A, "+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
						" WHERE A.QUOTATION_NO='"+m_quotation_no+"' AND A.FA_FEATURE_CODE=B.FA_FEATURE_CODE");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Feature Code</b></td>");
					out.println("<td width='40%' class=div_input><b>Description</b></td>");
					out.println("<td width='20%' class=div_input><b>Param Value</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Fee Package Details - "+rs.getString(19)+"  "+rs.getString(20)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					rs1= stmt.executeQuery(" SELECT A.FEE_CODE,B.FEE_DESC,A.FEE_VALUE "+
						" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FEE_CODE=B.FEE_CODE AND A.QUOTATION_NO='"+m_quotation_no+"'");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Fee Code</b></td>");
					out.println("<td width='40%' class=div_input><b>Description</b></td>");
					out.println("<td width='20%' class=div_input><b>Fee Value</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Quotation Details - Quaution No: "+m_quotation_no+" no records found</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_FACILITY_DETAIL_DRILL")){
				String m_string="";				
				
				String m_facility_no=req.getParameter("facility_no");
				
				rs= stmt1.executeQuery("SELECT FACILITY_NO, "+//1
					" CLIENT_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
					" FACILITY_MGR_CODE, "+//4
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'), "+//5
					" FA_PRODUCT_CODE, "+//6
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE), "+//7
					" FEE_PACK_CODE,  "+//8
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+//9
					" CREDIT_LIMIT, "+//10
					" CREDIT_PERIOD,"+//11
					" TOLERANCE_CREDIT_PERIOD, "+//12
					" RESERVE_MARGIN, "+//13
					" INT_RATE, "+//14
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+//15
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY'), "+//16
					" FACILITY_STATUS, "+//17
					" ENT_USER, "+//18
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//19
					" NVL(MOD_USER,'-'), "+//20
					" TO_CHAR(MOD_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//21
					" NVL(APP_USER,'-'), "+//22
					" TO_CHAR(APP_DATE,'DD-MM-YYYY HH24:MI:SS'), "+ //23
					" NVL(APPROVAL_COMMENTS,'-'), "+//24
					" DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated'), "+//25
					" NVL(ENTRY_COMMENTS,'-') "+//26
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+
					" WHERE FACILITY_NO='"+m_facility_no+"'");
				
				
				out.println("<HTML><HEAD><TITLE>Factoring Facility Details - Facility No: "+m_facility_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Factoring Facility Details - Facility No: "+m_facility_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				double m_min_fee_amount=0;
				double m_min_discount_amount=0;
				double m_repay_charge=0;
				double m_invoice_days=0;			
				double m_factor=0;
				
				rs1 = stmt.executeQuery (" SELECT NVL(APPLICABLE_VALUE,0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
					" WHERE FEE_CODE='FEE0003.0' AND FACILITY_NO='"+m_facility_no+"' ");
				
				if(rs1.next()){
					m_min_fee_amount=rs1.getDouble(1);
				}
				
				rs1 = stmt.executeQuery ("	SELECT NVL(APPLICABLE_VALUE,0) "+
					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
					" WHERE FEE_CODE='FEE0002.0' AND FACILITY_NO='"+m_facility_no+"' ");
				if(rs1.next()){
					m_min_discount_amount=rs1.getDouble(1);
				}
				
				
				
				if(rs.next()){
					
					m_repay_charge=100-rs.getDouble(13);
					m_invoice_days=rs.getDouble(11)+rs.getDouble(12);
					
					m_factor=(((m_min_fee_amount/m_repay_charge)*100)*(365/m_invoice_days))+rs.getDouble(14);
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility Status</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(25)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input  onClick=\"show_client('"+rs.getString(2)+"')\" style='cursor:hand'><b><u>"+rs.getString(3)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility Validity Period</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(15)+" - "+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility Manager</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(4)+" - "+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Credit Limit</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Credit Period</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Tolerance Credit Period</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reserve Margin (%)</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Interest Rate (%)</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comments </b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(26)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Expected Yeild</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(m_factor)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Enter User/Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(18)+" - "+rs.getString(19)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(20).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Modified User/Date</b></td>");
						out.println("<td width='50%' class=div_input >"+rs.getString(20)+" - "+rs.getString(21)+" </td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					if(!rs.getString(22).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approved User/Date/Comments</b></td>");
						out.println("<td width='50%' class=div_input >"+rs.getString(22)+" - "+rs.getString(23)+" - "+rs.getString(24)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Product Package Details - "+rs.getString(6)+"  "+rs.getString(7)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.PRODUCT_FEATURE_CODE,B.FA_FEATURE_DESC,NVL(A.PARAMETER_VALUE,'-') "+
						" FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD A, "+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' AND A.PRODUCT_FEATURE_CODE=B.FA_FEATURE_CODE");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Feature Code</b></td>");
					out.println("<td width='40%' class=div_input><b>Description</b></td>");
					out.println("<td width='20%' class=div_input><b>Param Value</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Fee Package Details - "+rs.getString(8)+"  "+rs.getString(9)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.FEE_CODE,B.FEE_DESC,NVL(A.APPLICABLE_VALUE,0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FEE_CODE=B.FEE_CODE AND A.FACILITY_NO='"+m_facility_no+"'");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Fee Code</b></td>");
					out.println("<td width='40%' class=div_input><b>Description</b></td>");
					out.println("<td width='20%' class=div_input><b>Fee Value</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Bank Guarantee/Personal Guarantee</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery("SELECT GURANT_NAME,NVL(GURANT_BANK,'-'),NVL(GURANT_CONT_PERSON,'-'),"+
						" NVL(TO_CHAR(GURANT_START_DATE,'DD-MM-YYYY'),'-'), NVL(TO_CHAR(GURANT_END_DATE,'DD-MM-YYYY'),'-'),"+
						" NVL(GURANT_VALUE,0),NVL(GURANT_COMMENTS,'-') "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_GURNT "+
						" WHERE FACILITY_NO='"+m_facility_no+"'");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Guarantor Name</b></td>");
					out.println("<td width='10%' class=div_input><b>Bank</b></td>");
					out.println("<td width='20%' class=div_input><b>Contact Person</b></td>");
					out.println("<td width='20%' class=div_input><b>Valued Period</b></td>");
					out.println("<td width='10%' class=div_input><b>Value (Rs.)</b></td>");
					out.println("<td width='10%' class=div_input><b>Comments</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(4)+" to "+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input>"+nf.format(rs1.getDouble(6))+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(7)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("</table>");
					//---------------------------  Client Disputes ----------------------------------------------------------------
					//String m_facility_no=req.getParameter("FACILITY_NO");
					
					rs1= stmt1.executeQuery(" SELECT"+
						" TO_CHAR(COMMENT_ENT_DATE,'DD-MM-YYYY / HH:MM:SS') COMMENT_ENT_DATE, "+
						" CLIENT_DISPUTES, "+
						" ENT_USER "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_DISPUTES "+
						" WHERE FACILITY_NO='"+m_facility_no+"' ORDER BY COMMENT_ENT_DATE DESC ");
					
					boolean more =  rs1.next();
					
					out.println("<table align='center' width='100%' class='table' >");
					if(more){
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='*%' align='center' ><DIV class=div_input><b>Client Disputes </b></DIV></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='10%' ><DIV class=div_input><b>Entered User</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Date / Time </b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>Comment</b></DIV></td>");
						out.println("</tr>");
					}
					int j=1;
					
					while(more){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						out.println("<td width='15%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='*%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("</tr>");
						more =  rs1.next();
					}
					out.println("</table>");
					
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Facility Details - Facility No: "+m_facility_no+" not found</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 29-12-2006
			//Modified by Mahela on 24-01-2007
			else if(m_chksql.equals("SHOW_POD_CHEQUE_DETAIL_DRILL")){
				
				String m_string="";				
				
				String m_pod_ref_no=req.getParameter("pod_ref_no");
				
				rs= stmt1.executeQuery(" SELECT A.POD_REF_NO, "+//1
					//" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),  "+//3
					" NVL(A.PAYER_ACC_NO,'-'), "+//4
					" A.CHEQUE_NO, "+//5
					" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+//6
					" NVL(A.CHEQUE_AMOUNT,0),"+//7
					" A.CURR_CODE, "+//8
					" NVL(A.EXCHANGE_RATE_REP_CURR,0), "+//9
					" NVL(A.REC_AMOUNT_CURR,0),"+//10
					" NVL(A.CHEQUE_COMMENTS,'-'), "+//11
					" DECODE(A.POD_STATUS,'E','Wait for approval','N','Wait for realise','Y','Realised','C','Cancel'),"+//12
					" A.ENT_USER,  "+//13
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'), "+//14					
					" TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'), "+//15
					" NVL(REF_NO,'-'), "+//16 Added by Sandun on 10-09-2009
					" "+m_schema_name+".FA_PO_GET_REC_FOR_POD(A.POD_REF_NO), "+//17 Added By Sandun on 18-09-2009
					" NVL(A.CLIENT_CODE,'-'), "+//18
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//19
					" NVL(A.FACILITY_NO,'-'),"+//20
					" NVL(A.DEBTOR_CODE,'-'), "+//21
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') "+//22
					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B"+
					" WHERE A.POD_REF_NO='"+m_pod_ref_no+"' ");//AND A.POD_REF_NO=B.POD_REF_NO 
				
				out.println("<HTML><HEAD><TITLE>Post Dated Cheque Details - POD Ref No: "+m_pod_ref_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Post Dated Cheque Details - POD Ref No: "+m_pod_ref_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(11)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					
					
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client("+rs.getString(2)+")\" style='cursor:hand'><b><u>"+rs.getString(2)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");*/
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>PD Cheque Realise Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comments</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");//Added By Sandun on 17-09-2009
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reference/Tempary</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");//Added By Sandun on 17-09-2009
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(17)+"')\" style='cursor:hand' ><u><b>"+rs.getString(18)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_facility('"+rs.getString(19)+"')\" style='cursor:hand' ><u><b>"+rs.getString(19)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered User</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(12)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(13)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand'><u><b>"+rs.getString(21)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Invoice Details</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO, "+//1
						" NVL(A.BATCH_NO,'-'), "+//2
						" NVL(A.DEBTOR_CODE,'-'),"+//3
						" NVL(A.INVOICE_NO,'-'), "+//4
						" NVL(TO_CHAR(C.INVOICE_DATE,'DD-MM-YYYY'),'-'), "+//5
						" NVL(C.INVOICE_AMOUNT,0), "+//6
						" NVL(ALLO_AMOUNT,0) "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A, "+m_schema_name+".FA_OP_PRO_POD_CHEQUES B, "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
						" WHERE A.POD_REF_NO=B.POD_REF_NO AND A.BATCH_NO=C.BATCH_NO AND A.INVOICE_NO=C.INVOICE_NO AND B.POD_REF_NO='"+m_pod_ref_no+"'");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='15%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allocated Amount</b></td>");//Added By Sandun on 18-09-2009
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>"); // added by udara on 22-11-2011
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					double balanced_amnt = 0;
					double tot_inv_amnt = 0;
					double tot_allo_amnt = 0;
					double tot_bal_amnt = 0;
					
					while(rs.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_invoice_batch_details('"+rs.getString(2)+"')\" style='cursor:hand' ><u>"+rs.getString(2)+"</u></td>");
						out.println("<td width='15%' class=div_input onClick=\"show_invoice_details('"+rs.getString(3)+"','"+rs.getString(4)+"')\" style='cursor:hand'><u>"+rs.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");//Added By Sandun on 18-09-2009
						
						// added by udara on 22-11-2011
						tot_inv_amnt = tot_inv_amnt + rs.getDouble(6);
						tot_allo_amnt = tot_allo_amnt + rs.getDouble(7);
						balanced_amnt = rs.getDouble(6) - rs.getDouble(7);
						tot_bal_amnt = tot_bal_amnt + balanced_amnt;
						
						if(balanced_amnt<0)
							out.println("<td width='15%' class=div_input align='right'>("+nf.format(balanced_amnt*-1)+")</td>");
						else
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(balanced_amnt)+"</td>");
						// end by udara on 22-11-2011
						
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					
					// added by udara on 22-11-2011
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Total</b></td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_inv_amnt)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_allo_amnt)+"</b></td>");
					
					if(balanced_amnt<0)
						out.println("<td width='15%' class=div_input align='right' ><b>("+nf.format(tot_bal_amnt*-1)+")</b></td>"); 
					else
						out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_bal_amnt)+"</b></td>"); 
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					// end by udara on 22-11-2011
					
					
					out.println("</table>");
					
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='50%' class=div_input><b><u>Cheque Return allocation Details</u></b></td>");
					out.println("<td width='50%' class=div_input><b><u>This PD Use to Settled Below Return Chq</u></b></td>");
					
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					//-------
					rs= stmt1.executeQuery
						//out.println
						(" SELECT A.POD_REF_NO,"+//1
						" A.CHEQUE_AMOUNT,"+//2
						" A.RE_BANK_RECEIPT_NO,"+//3
						" A.RE_BANK_REC_AMOUNT, "+//4
						" A.ALLO_REC_AMOUNT, "+//5
						" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) CNAME,"+//6
						" B.SETTLE_MODE || '-'  || NVL(B.CHEQUE_NO,'') SET_DET "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHQ_REBANK A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE "+
						" A.RE_BANK_RECEIPT_NO= B.RECEIPT_NO "+
						" AND A.POD_REF_NO='"+m_pod_ref_no+"' ");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Receipt No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client/Debtor Name</b></td>");
					out.println("<td width='15%' class=div_input><b>Settle Details</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allo Amount</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					while(rs.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_receipt_details('"+rs.getString(3)+"')\" style='cursor:hand' ><u>"+rs.getString(3)+"</u></td>");
						out.println("<td width='25%' class=div_input >"+rs.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Post Dated Cheque Details - POD Ref No: "+m_pod_ref_no+" no records found</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Modified by Mahela on 18-01-2007
			else if(m_chksql.equals("SHOW_ADJUST_DETAIL_DRILL")){
				
				String m_string="";				
				String m_adjust_no=req.getParameter("adjust_no");
				
				rs= stmt1.executeQuery("SELECT ADJUSTMENT_NO, "+//1
					" FACILITY_NO, "+//2
					" CLIENT_CODE, "+//3
					" BATCH_NO, "+//4
					" DEBTOR_CODE, "+//5 
					" INVOICE_NO, "+//6
					" NVL(ADJUST_AMOUNT,0), "+//7
					" TO_CHAR(ADJUST_DATE,'DD-MON-YYYY'), "+//8
					" ADJUST_TYPE, "+//9
					" ADJUST_CATEGORY, "+ //10
					" NVL(ADJUSTMENT_COMMENTS,'-'), "+//11
					" DECODE(INVOICE_STATUS,'Y','Approved','N','Not Approved','C','Disapproved'), "+//12
					" ENT_USER, "+//13
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//14
					" NVL(MOD_USER,'-'), "+//15
					" TO_CHAR(MOD_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//16
					" NVL(APP_USER,'-'), "+//17
					" TO_CHAR(APP_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//18
					" NVL(APPROVAL_COMMENTS,'-'), "+//19
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//20
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//21
					" DECODE(ADJUST_CATEGORY,'INR','Invoice Reassignment','DBA','Debtor Adjustments','FAA','Facility Adjustments','CLA','Client Adjustments'), "+//22
					" NVL(SOURCE_DOCUMENT,'-') "+//23
					" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
					" WHERE ADJUSTMENT_NO='"+m_adjust_no+"'");
				
				out.println("<HTML><HEAD><TITLE>Factoring Adjustment Details - Adjustment No: "+m_adjust_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Factoring Adjustment Details - Adjustment No: "+m_adjust_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment No </b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment Type </b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(22)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment Status</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(12)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<hr>");
					out.println("<br>");
					if(rs.getString(10).equals("INA") || rs.getString(10).equals("INR")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs.getString(2)+"')\" style='cursor:hand'><b><u>"+rs.getString(2)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(3)+"')\" style='cursor:hand'><b><u>"+rs.getString(20)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Invoice Batch No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_invoice_batch_details('"+rs.getString(4)+"')\" style='cursor:hand'><b><u>"+rs.getString(4)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Debtor Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(5)+"')\" style='cursor:hand'><b><u>"+rs.getString(21)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Invoice No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_invoice_details('"+rs.getString(5)+"','"+rs.getString(6)+"')\" style='cursor:hand'><b><u>"+rs.getString(6)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else if(rs.getString(10).equals("DBA")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs.getString(2)+"')\" style='cursor:hand'><b><u>"+rs.getString(2)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(3)+"')\" style='cursor:hand'><b><u>"+rs.getString(20)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Debtor Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(5)+"')\" style='cursor:hand'><b><u>"+rs.getString(21)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else if(rs.getString(10).equals("FAA")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs.getString(2)+"')\" style='cursor:hand'><b><u>"+rs.getString(2)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(3)+"')\" style='cursor:hand'><b><u>"+rs.getString(20)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else if(rs.getString(10).equals("CLA")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(3)+"')\" style='cursor:hand'><b><u>"+rs.getString(20)+"</u></b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					out.println("<br>");
					out.println("<hr>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment Type</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(9)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment Amount Rs. </b></td>");
					out.println("<td width='50%' class=div_input ><b>"+nf.format(rs.getDouble(7))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Effective Date</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(8)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjustment Comment</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(11)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Enter User/Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(13)+" - "+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Source Document</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(23)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(15).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Modified User/Date</b></td>");
						out.println("<td width='50%' class=div_input >"+rs.getString(15)+" - "+rs.getString(16)+" </td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					if(!rs.getString(17).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Approved User/Date/Comments</b></td>");
						out.println("<td width='50%' class=div_input >"+rs.getString(17)+" - "+rs.getString(18)+" - "+rs.getString(19)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Adjustment Details - Adjustment No: "+m_adjust_no+" not found</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 10-01-2007 
			else if(m_chksql.equals("SHOW_RECEIPT_DETAIL_DRILL")){ 
				
				String m_string="";				
				
				String m_receipt_no=req.getParameter("receipt_no");
				
				
				rs= stmt1.executeQuery("SELECT	RECEIPT_NO,"+//1
					"SETTLE_MODE,"+//2
					"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//3
					"NVL(PAYER_ACC_NO,'-'),"+//4
					"NVL(REC_AMOUNT,0),"+//5
					"NVL(BALANCE_AMOUNT,0),"+//6
					"NVL(ALLO_AMOUNT,0),"+//7
					"NVL(RECEIPT_COMMENTS,'-'),"+//8
					"NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//9 effective value date
					"NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//10
					"NVL(CHEQUE_NO,'-'),"+//11
					"NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//12
					"NVL(CLIENT_CODE,'-'),"+//13
					"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//14
					"NVL(FACILITY_NO,'-'),"+//15
					"NVL(BATCH_NO,'-'),"+//16
					"NVL(DEBTOR_CODE,'-'),"+//17
					"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//18
					"NVL(INVOICE_NO,'-'),"+	//19
					"NVL(RECEIPT_TYPE,'-'),"+//20
					"NVL(SUS_REF_NO,'-'),"+//21  POD Cheque Reference No 
					"NVL(CURR_CODE,'-'),"+//22
					"NVL(REC_AMOUNT_CURR,0),"+//23
					"NVL(EXCHANGE_RATE_BANK,0),"+//24
					"NVL(EXCHANGE_RATE_REP_CURR,0),"+//25 Reporting Currency Amount 
					"NVL(EXCHANGE_GAIN_LOSS,0),"+//26
					"NVL(TEMP_RECEIPT_NO,'-'),"+//27
					"NVL(COLL_OFFICER,'-'),"+//28
					"NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//29
					"DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised','D','Receipt Disapproved'),"+//30 // 'D' -Disapproved added by ns on 15-08-2011
					" "+m_schema_name+".FA_CO_GET_REC_APP_DATE(RECEIPT_NO) "+//31
					"FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					"WHERE RECEIPT_NO='"+m_receipt_no+"' ");	
				
				
				out.println("<HTML><HEAD><TITLE>Receipt Details - Receipt No: "+m_receipt_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Receipt Details - Receipt No: "+m_receipt_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(30)+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payer Branch Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt Amount Rs.</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Allocation Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt Comment</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Effective Value Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//----------Added By Sandun on 17-09-2009 for SR/20090916/099------------------------------------------------------------------
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>FR Approved Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque Realised Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");				
					//----------------------------------------------------------------------------
					
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					*/
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(13)+"')\" style='cursor:hand' ><u><b>"+rs.getString(14)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_facility('"+rs.getString(15)+"')\" style='cursor:hand' ><u><b>"+rs.getString(15)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(17)+"')\" style='cursor:hand'><u><b>"+rs.getString(18)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Type</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(20)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>");
					out.println("<td width='20%' class=div_input><b>POD Cheque Reference No</b></td>");
					out.println("<td width='50%' class=div_input  onclick=\"show_pod_cheque_details('"+rs.getString(21)+"')\" style='cursor:hand' ><u><b>"+rs.getString(21)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(22)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(23))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Bank</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Reporting Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(26))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");*/
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Temporay Receipt No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Collection Officer</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Bank Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Pre-Allocation Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.RECEIPT_NO,"+//1
						" A.CLIENT_CODE,"+//2
						" A.FACILITY_NO,"+//3
						" A.BATCH_NO,"+//4
						" A.DEBTOR_CODE,"+//5
						" A.INVOICE_NO,"+//6
						" A.ALLO_TYPE,"+//7
						" A.ALLO_AMOUNT, "+//8
						" TO_CHAR(C.INVOICE_DATE,'DD-MM-YYYY'), "+//9
						" NVL(INVOICE_AMOUNT,0) "+//10
						" FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
						" WHERE A.RECEIPT_NO='"+m_receipt_no+"'"+
						" AND A.INVOICE_NO=C.INVOICE_NO "+
						" AND A.CLIENT_CODE=C.CLIENT_CODE");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='15%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allocated Amount</b></td>");//Added By Sandun on 17-09-2009
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>"); // added by udara on 22-11-2011
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					// added by udara on 22-11-2011
					double balanced_amnt = 0;
					double tot_inv_amnt = 0;
					double tot_allo_amnt = 0;
					double tot_bal_amnt = 0;
					// end by udara on 22-11-2011
					
					while(rs1.next()){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(4)+"','"+rs1.getString(6)+"')\" style='cursor:hand'><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(9)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(8))+"</td>");//Added By Sandun on 17-09-2009
						
						
						// added by udara on 22-11-2011
						tot_inv_amnt = tot_inv_amnt + rs1.getDouble(10);
						tot_allo_amnt = tot_allo_amnt + rs1.getDouble(8);
						balanced_amnt = rs1.getDouble(10) - rs1.getDouble(8);
						tot_bal_amnt = tot_bal_amnt + balanced_amnt;
						
						if(balanced_amnt<0)
							out.println("<td width='15%' class=div_input align='right'>("+nf.format(balanced_amnt*-1)+")</td>");
						else
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(balanced_amnt)+"</td>");
						// end by udara on 22-11-2011
						
						
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					
					// added by udara on 22-11-2011
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Total</b></td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_inv_amnt)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_allo_amnt)+"</b></td>");
					
					if(balanced_amnt<0)
						out.println("<td width='15%' class=div_input align='right' ><b>("+nf.format(tot_bal_amnt*-1)+")</b></td>"); 
					else
						out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_bal_amnt)+"</b></td>"); 
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					// end by udara on 22-11-2011   
					
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Allocation Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.RECEIPT_NO,"+//1
						" A.CLIENT_CODE,"+//2
						" A.FACILITY_NO,"+//3
						" C.BATCH_NO,"+//4
						" C.DEBTOR_CODE,"+//5
						" C.INVOICE_NO,"+//6
						" '',"+//7
						" A.ALLOCATED_AMOUNT, "+//8
						" TO_CHAR(C.INVOICE_DATE,'DD-MM-YYYY'), "+//9
						" C.INVOICE_AMOUNT, "+//10
						" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
						" WHERE A.RECEIPT_NO='"+m_receipt_no+"'"+
						" AND A.INVOICE_NO=C.INVOICE_SEQ_NO ");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='15%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='10%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='10%' class=div_input><b>Allo Date</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allo Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>"); // added by udara on 22-11-2011
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					// added by udara on 22-11-2011
					 balanced_amnt = 0;
					 tot_inv_amnt = 0;
					 tot_allo_amnt = 0;
					 tot_bal_amnt = 0;
					// end by udara on 22-11-2011
					
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(4)+"','"+rs1.getString(6)+"')\" style='cursor:hand'><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(11)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
						
						// added by udara on 22-11-2011
						tot_inv_amnt = tot_inv_amnt + rs1.getDouble(10);
						tot_allo_amnt = tot_allo_amnt + rs1.getDouble(8);
						balanced_amnt = rs1.getDouble(10) - rs1.getDouble(8);
						tot_bal_amnt = tot_bal_amnt + balanced_amnt;
						
						if(balanced_amnt<0)
							out.println("<td width='15%' class=div_input align='right'>("+nf.format(balanced_amnt*-1)+")</td>");
						else
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(balanced_amnt)+"</td>");
						// end by udara on 22-11-2011
						
						
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					
					// added by udara on 22-11-2011
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Total</b></td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input> &nbsp; </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_inv_amnt)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_allo_amnt)+"</b></td>");
					
					if(balanced_amnt<0)
						out.println("<td width='15%' class=div_input align='right' ><b>("+nf.format(tot_bal_amnt*-1)+")</b></td>"); 
					else
						out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(tot_bal_amnt)+"</b></td>"); 
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					// end by udara on 22-11-2011  
					
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='50%' class=div_input><b><u>Cheque Return allocation Details</u></b></td>");
					out.println("<td width='50%' class=div_input><b><u>This Receipt Use to Settled Below Return Chq</u></b></td>");
					
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery
						//out.println
						(" SELECT A.RECEIPT_NO,"+//1
						" A.REC_AMOUNT,"+//2
						" A.RE_BANK_RECEIPT_NO,"+//3
						" A.RE_BANK_REC_AMOUNT, "+//4
						" A.ALLO_REC_AMOUNT, "+//5
						" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) CNAME,"+//6
						" B.SETTLE_MODE || '-'  || NVL(B.CHEQUE_NO,'') SET_DET "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE "+
						" A.RE_BANK_RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.RECEIPT_NO='"+m_receipt_no+"' ");//ADDED BY MADHAWA 2011-04-07 COMMENTED BELOW
					//" AND B.RECEIPT_NO='"+m_receipt_no+"' ");//A.RECEIPT_NO comment by ns on 14-10-2010
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Receipt No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client/Debtor Name</b></td>");
					out.println("<td width='15%' class=div_input><b>Settle Details</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allo Amount</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='25%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(7)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					//////////////////////////////////////
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='50%' class=div_input><b><u>Settlement Details against this Receipt</u></b></td>");
					out.println("<td width='50%' class=div_input><b><u>This Return Chq Settled by Below Receipts</u></b></td>");
					
					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.RECEIPT_NO,"+//1
						" A.REC_AMOUNT,"+//2
						" A.RE_BANK_RECEIPT_NO,"+//3
						" A.RE_BANK_REC_AMOUNT, "+//4
						" A.ALLO_REC_AMOUNT, "+//5
						" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) CNAME,"+//6
						" B.SETTLE_MODE || '-'  || NVL(B.CHEQUE_NO,'') SET_DET "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE "+
						" A.RECEIPT_NO = B.RECEIPT_NO "+
						//" AND B.RECEIPT_NO='"+m_receipt_no+"' ");//ADDED BY MADHAWA 2011-04-07 COMMENTED BELOW
						
						" AND A.RE_BANK_RECEIPT_NO='"+m_receipt_no+"' ");
					//" AND B.RECEIPT_NO='"+m_receipt_no+"' ");//A.RECEIPT_NO comment by ns on 14-10-2010
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Receipt No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client/Debtor Name</b></td>");
					out.println("<td width='15%' class=div_input><b>Settle Details</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allo Amount</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='25%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(7)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(2))+"</td>");//4
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					
					
					
					////////////////////////////////////////
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Cheque Return Transfer Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs1= stmt.executeQuery(" SELECT A.RECEIPT_NO,"+//1
						" A.TRANSFER_AMOUNT,"+//2
						" A.COMMENTS,"+//3
						" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+//4
						" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) CNAME,"+//5
						" B.SETTLE_MODE || '-'  || NVL(B.CHEQUE_NO,'') SET_DET "+//6
						" FROM "+m_schema_name+".FA_OP_PRO_CHQ_RET_ADJUSTMENT A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE "+
						" A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.RECEIPT_NO='"+m_receipt_no+"' ");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Receipt No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client/Debtor Name</b></td>");
					out.println("<td width='15%' class=div_input><b>Settle Details</b></td>");
					out.println("<td width='15%' class=div_input><b>Trans Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Comments</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Transfer Amount</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='25%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Receipt Details - Receipt No: "+m_receipt_no+" </B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 22-01-2007 
			//Modified by Mahela on 24-01-2007
			else if(m_chksql.equals("SHOW_DEPOSIT_DETAIL_DRILL")){ 
				
				String m_string="";							
				String m_deposit_no=req.getParameter("deposit_no");
				
				rs= stmt1.executeQuery("SELECT DEPOSIT_NO,"+//1
					"	TO_CHAR(DEPOSIT_DATE,'DD-MM-YYYY'),"+//2
					"	NVL(DEPOSIT_COMMENTS,'-'),"+//3
					" NVL(ACC_NO,'-'),"+//4
					"	NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'),"+//5
					" DECODE(STATUS,'C','Deposit Returned','Y','Deposit Realised'),"+//6
					" NVL(DEPOSIT_TOTAL,0),"+//7
					"	NVL(DEPOSIT_MODE,'-') "+//8
					" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT "+
					"	WHERE DEPOSIT_NO='"+m_deposit_no+"' ");	
				
				
				out.println("<HTML><HEAD><TITLE>Deposit Details - Deposit No: "+m_deposit_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Deposit Details - Deposit No: "+m_deposit_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='50%' class=div_input ><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(6)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Date</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Comment</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Account No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Total</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Mode</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Receipt Details</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs= stmt1.executeQuery(" SELECT A.RECEIPT_NO, "+//1
						" NVL(A.CHEQUE_NO,'-'),"+//2
						" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.PAYER_BRANCH_CODE),'-'), "+ //3
						" NVL(C.PAYER_ACC_NO,'-'),"+//4
						" NVL(A.DEPOSIT_AMOUNT,0) "+//5
						" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A, "+m_schema_name+".FA_OP_PRO_DEPOSIT B, "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
						" WHERE A.DEPOSIT_NO=B.DEPOSIT_NO AND A.RECEIPT_NO=C.RECEIPT_NO AND A.DEPOSIT_NO='"+m_deposit_no+"' ");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Deposit Amount</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input class=div_input onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("</table>");
					
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Deposit Details - Deposit No: "+m_deposit_no+" </B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 22-01-2007 
			else if(m_chksql.equals("SHOW_RETURN_DETAIL_DRILL")){ 
				
				String m_string="";							
				String m_return_no=req.getParameter("return_no");
				
				rs= stmt1.executeQuery(" SELECT  RETURN_NO,"+//1
					" NVL(DIPOSIT_NO,'-'),"+//2
					" NVL(RECEIPT_NO,'-'),"+//3
					" NVL(DEPOSIT_AMOUNT,0),"+//4
					" NVL(CHEQUE_NO,'-'),"+//5
					" NVL(TO_CHAR(REALIZE_DATE,'DD-MM-YYYY'),'-'),"+//6
					" NVL(ACCOUNT_NO,'-'),"+//7
					"	NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-')"+//8
					" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS "+
					"	WHERE RETURN_NO='"+m_return_no+"' ");			
				
				
				out.println("<HTML><HEAD><TITLE>Return Details - Return No: "+m_return_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Return Details - Return No: "+m_return_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Return No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_deposit_details('"+rs.getString(2)+"')\" style='cursor:hand' ><b><u>"+rs.getString(2)+" </u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_receipt_details('"+rs.getString(3)+"')\" style='cursor:hand' ><b><u>"+rs.getString(3)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Deposit Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(4))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Account No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Return Details - Return No: "+m_return_no+" </B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 22-01-2007 
			else if(m_chksql.equals("SHOW_PAYMENT_DETAIL_DRILL")){ 
				
				String m_string="";				
				
				String m_payment_code=req.getParameter("payment_code");
				
				rs= stmt1.executeQuery("SELECT NVL(PAYMENT_CODE,'-'),"+//1
					" NVL(CLIENT_CODE,'-'),"+//2
					" NVL(FACILITY_NO,'-'),"+//3
					" NVL(PAYMENT_AMOUNT,0),"+//4
					" NVL(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'-'),"+//5
					" NVL(SETTLE_MODE,'-'),"+//6
					" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE),'-'),"+//7
					" NVL(LIC_ACC_NO,'-'),"+//8
					" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYEE_BRANCH_CODE),'-'),"+//9
					" NVL(PAYEE_ACC_NO,'-'),"+//10
					" DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement'),"+//11
					" NVL(PAY_COMMENTS,'-'),"+//12
					" NVL(CHEQUE_NO,'-'),"+//13
					" NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//14
					" NVL(CURR_CODE,'-'),"+//15
					" NVL(REC_AMOUNT_CURR,0),"+//16
					" NVL(EXCHANGE_RATE_BANK,0),"+//17
					" NVL(EXCHANGE_RATE_REP_CURR,0),"+//18
					" NVL(EXCHANGE_GAIN_LOSS,0),"+//19
					" NVL(APP_USER,'-'),"+//20
					" NVL(TO_CHAR(APP_DATE),'-'),"+//21
					" NVL(APP_COMMENTS,'-'), "+//22
					" TO_CHAR(PRINT_DATE,'DD-MM-YYYY'),"+//23
					" NVL(DISB_TO,'-'),"+//24
					" NVL(DISB_USER,'-'),"+//25
					" TO_CHAR(DISB_DATE,'DD-MM-YYYY'), "+//26
					"  DECODE(PAY_3RD_PARTY_STATUS,'Y',PAY_3RD_PARTY_NAME,'-') "+//27
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
					" WHERE PAYMENT_CODE='"+m_payment_code+"' ");	
				
				
				out.println("<HTML><HEAD><TITLE>Payment Details - Payment Code: "+m_payment_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Payment Details - Payment Code: "+m_payment_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payment code</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Pay Status</b></td>");
					out.println("<td width='50%' class=div_input><B>"+rs.getString(11)+"</B></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(2)+"')\" style='cursor:hand' ><b><u>"+rs.getString(2)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs.getString(3)+"')\" style='cursor:hand' ><b><u>"+rs.getString(3)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payment Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(4))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Pay Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Licensee Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Licensee Account No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payee Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Payee Account No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Pay Comments</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Bank</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Reporting Currency</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(18)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(19)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approved User</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(20)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approved Date</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(21)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approval Comment</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Print Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Disbursement Person</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Disbursement User</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Disbursement Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>If Paied to 3rd Party Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Payment Details - Payment Code: "+m_payment_code+" </B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 22-01-2007 
			else if(m_chksql.equals("SHOW_INVOICE_ALLO_DETAIL_DRILL")){ 
				
				String m_string="";								
				String m_allocation_no=req.getParameter("allocation_no");
				
				rs= stmt1.executeQuery("SELECT ALLOCATION_NO,"+//1
					" NVL(RECEIPT_NO,'-'), "+//2
					" NVL(RECEIPT_AMOUNT,0),"+//3
					" NVL(INVOICE_NO,'-'),"+//4 SEQ NO
					" NVL(INVOICED_AMOUNT,0),"+//5
					" NVL(BALANCE_AMOUNT,0),"+//6
					" NVL(ALLOCATED_AMOUNT,0),"+//7
					" NVL(TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'),'-'),"+//8
					" NVL(COMMENTS,'-'),"+//9
					" NVL(CLIENT_CODE,'-'),"+//10
					" NVL(FACILITY_NO,'-'),"+//11
					" NVL(BALANCE_RECEIPT_AMOUNT,0) "+//12
					" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO "+
					" WHERE ALLOCATION_NO='"+m_allocation_no+"' ");	
				
				out.println("<HTML><HEAD><TITLE>Invoice Allocaiton Details - Allocation No: "+m_allocation_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Invoice Allocaiton Details - Allocation No: "+m_allocation_no+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Allocation No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_receipt_details('"+rs.getString(2)+"')\" style='cursor:hand' ><b><u>"+rs.getString(2)+"</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Seq. No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Allocated Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Allocated Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comments</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs.getString(10)+"')\" style='cursor:hand'  ><u>"+rs.getString(10)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs.getString(11)+"')\" style='cursor:hand' ><u>"+rs.getString(11)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Receipt Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Invoice Allocaiton Details - Allocation No: "+m_allocation_no+"</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Mahela on 13-02-2007 
			else if(m_chksql.equals("SHOW_CLIENT_CHARGES_DETAIL_DRILL")){ 
				
				String m_string="";				
				
				String m_charges_ref_no=req.getParameter("charges_ref_no");
				
				
				rs= stmt1.executeQuery("SELECT  CHARGES_REF_NO, "+//1
					"  NVL(SUS_REF_NO,'-'),"+//2
					"  NVL(CLIENT_CODE,'-'),"+//3
					"	 NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//4
					"  NVL(FACILITY_NO,'-'),"+//5
					"  NVL(FEE_CODE,'-'),"+//6
					"  NVL(INITCAP(FEE_DESC),'-'),"+//7
					"  DECODE(DRCR_STATUS,'DR','Debit','Credit'),"+//8
					"  NVL(TO_CHAR(EFF_DATE,'DD-MM-YYYY'),'-'),"+//9
					"  NVL(FEE_CHARGE_AMOUNT,0),"+//10
					"  NVL(CURR_CODE,'-'),"+//11
					"  NVL(REC_AMOUNT_CURR,0),"+//12
					"  NVL(EXCHANGE_RATE_BANK,0),"+//13
					"  NVL(EXCHANGE_RATE_REP_CURR,0),"+//14
					"  NVL(EXCHANGE_GAIN_LOSS,0),"+//15
					"  NVL(DEBTOR_CODE,'-'),"+//16
					"	 NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//17
					"  NVL(BATCH_NO,'-'),"+//18
					"  NVL(INVOICE_NO,'-') "+//19
					"  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES "+
					" WHERE CHARGES_REF_NO='"+m_charges_ref_no+"' ");
				
				out.println("<HTML><HEAD><TITLE>Charges Details - Charges Ref No: "+m_charges_ref_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Charges Details - Charges Ref No: "+m_charges_ref_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Charges Ref NO</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Charge Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(8)+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>SUS Ref NO</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(3)+"')\" style='cursor:hand' ><u><b>"+rs.getString(4)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Facility NO</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_facility('"+rs.getString(5)+"')\" style='cursor:hand'><b>"+rs.getString(5)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Fee Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Fee Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Effective Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Fee Charge Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Bank</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate Reporting Curr.</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_client('"+rs.getString(16)+"')\" style='cursor:hand' ><u><b>"+rs.getString(17)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_invoice_batch_details('"+rs.getString(18)+"')\" style='cursor:hand' ><u><b>"+rs.getString(18)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input onclick=\"show_invoice_details('"+rs.getString(16)+"','"+rs.getString(19)+"')\" style='cursor:hand' ><u><b>"+rs.getString(19)+"</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Charges Details - Charges Ref No: "+m_charges_ref_no+" </B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_INVOICE_DETAIL_REFNO_DRILL")){ 
				
				String m_string="";				
				
				String m_invoice_refno=req.getParameter("invoice_refno");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" A.BATCH_NO,   "+//1
					" A.DEBTOR_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//3
					" A.INVOICE_NO,  "+//4
					" NVL(A.INVOICE_AMOUNT,0),  "+//5
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//6
					" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+//7
					" TO_CHAR(A.TOLARENCE_END_DATE,'DD-MM-YYYY'),  "+//8
					"	NVL(ADJUSTMENT_AMOUNT,0),"+//9
					"	NVL(NET_INVOICE_AMOUNT,0),"+//10
					"	NVL(SETTLE_AMOUNT,0),"+//11
					"	NVL(CURR_CODE,'-'),"+//12
					"	NVL(EXCHANGE_RATE,0),"+//13
					"	NVL(AMOUNT_RPT_CURR,0),"+//14
					"	NVL(EXCHANGE_GAIN_LOSS,0),"+//15
					" NVL(A.INVOICE_COMMENTS,'-'),  "+//16
					" DECODE(A.INVOICE_STATUS,'ENTER','Enter','APPR1','Approve','CANCEL','Disapprove','CONF','Approved','APP_C','Approve Level Cancel','APP_2','Approve Level 1 Approval','APP_C2','Credit Approval Cancelation'), "+//17
					" NVL(A.ENT_USER,'-'),"+//18
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'),"+//19
					" NVL(A.MOD_USER,'-'),"+//20
					" TO_CHAR(A.MOD_DATE,'DD-MM-YYYY HH24:MI'),"+//21
					" NVL(A.APP_USER,'-'),"+//22
					" TO_CHAR(A.APP_DATE,'DD-MM-YYYY HH24:MI'),"+//23
					" NVL(A.APPROVAL_COMMENTS,'-'), "+//24
					" A.BALANCE_AMOUNT "+//25
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A  "+
					" WHERE A.INVOICE_SEQ_NO='"+m_invoice_refno+"'");
				
				out.println("<HTML><HEAD><TITLE>Invoice Details - Invoice Ref No: "+m_invoice_refno+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Invoice Details - Invoice Ref No: "+m_invoice_refno+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+" - "+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(17)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Amount Rs.</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Due Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Tolerance End Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Adjustment Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Net Invoice Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(25))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comment</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered User</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(18)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered Date/Time</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(19)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(11).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified User</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(20)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified Date/Time</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(21)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					if(!rs.getString(13).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve User</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(22)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve Date/Time</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(23)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Approve Comments</b></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(24)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						//End of modification on 01-01-2007
					}
					out.println("</table>");
					
					
					
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Invoice Details - Invoice No: "+m_invoice_refno+"</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b><u>Allocation Details</u></b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				
				rs= stmt1.executeQuery(" SELECT A.RECEIPT_NO,"+
					" A.RECEIPT_AMOUNT,"+
					" A.ALLOCATED_AMOUNT,"+
					" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A "+
					" WHERE INVOICE_NO='"+m_invoice_refno+"' "+
					" ORDER BY A.ALLOCATED_DATE ");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
				out.println("<td width='10%' class=div_input><b>Receipt Amount</b></td>");
				out.println("<td width='10%' class=div_input><b>Allo Amount</b></td>");
				out.println("<td width='10%' class=div_input><b>Allo Date</b></td>");
				out.println("</tr>");
				while(rs.next()){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand'><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("</tr>");				
				}
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b><u>Followup Comments</u></b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				
				rs=stmt1.executeQuery(" SELECT INVOICE_SEQ_NO, "+
					" ENT_USER, "+
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
					" WHERE INVOICE_SEQ_NO='"+m_invoice_refno+"' "+
					" ORDER BY ENT_DATE DESC ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr >");
				out.println("<td width='1%'></td>"); 
				out.println("<td width=\"20%\" class=div_input><b>Enter User</b></td>"); 
				out.println("<td width=\"25%\" class=div_input><b>Enter Date/Time</b></td>"); 
				out.println("<td width=\"60%\" class=div_input><b>Comments</b></td>"); 
				out.println("</tr>");
				
				while(rs.next()){
					out.println("<tr >");
					out.println("<td width='1%'></td>"); 
					out.println("<td width=\"20%\" class=div_input>"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"25%\" class=div_input>"+rs.getString(3)+"</td>"); 
					out.println("<td width=\"60%\" class=div_input>"+rs.getString(4)+"</td>"); 
					out.println("</tr>");
				}
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
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
				out.println("Error: " + e.toString());
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


