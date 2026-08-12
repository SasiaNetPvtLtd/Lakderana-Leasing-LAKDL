/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//Created by Dineth on 24-07-2009
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
public class LAKDL_FA_MISF_Cr_limit_zero_clients extends javax.servlet.http.HttpServlet { 


  Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
  public ResultSet rs1,rs4,rs5,rs6;
	public String m_chksql;
	
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 


  try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
      String m_username = m_sn_methods.username;

			
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
      
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			 out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		  else if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Limit Zero Clients Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("var m_sav_msg='';");
			out.println("var timerID;");
			out.println("var durationID=0;");
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MISF_Cr_limit_zero_clients?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MISF_Cr_limit_zero_clients?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_CLIENT_DEBTOR_APPROVAL\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Credit Limit Zero Clients Report  - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Credit Limit Zero Clients Report  \";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			//out.println("	if(confirm(\"Are You Sure\")){ ");
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			//out.println("	}"); 
			out.println("}"); 
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_CR_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_1();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	}"); 
			out.println("}"); 
   
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_num' VALUE=\"0\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Limit Zero Clients Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>"); 
			out.println("<table>"); 
			out.println("<tr >"); 
			out.println("<td width='10%'  class=div_input></td>"); 
			out.println("<td width='*%' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			out.println("<br>"); 
			//out.println("<DIV id='credit_approval_data'  class=div_input></DIV>");
				/*rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(PRE_CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND RELATION_MOVEMENT IN ('CHQENTRY') "+
					" AND CLIENT_CODE IN(SELECT B.CLIENT_CODE "+ 
					" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
					//" AND B.REBANK_STATUS IN('N','R') "+
					" AND (B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO))=0)");
					*/
					
					/*rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(PRE_CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND RELATION_MOVEMENT IN ('CHQENTRY') "+
					" AND CLIENT_CODE IN(SELECT A.CLIENT_CODE "+ 
					" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_LIMIT_ADJ A,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
					" WHERE B.RECEIPT_NO = C.RECEIPT_NO "+
					" AND B.RETURN_NO=A.SOURCE_REF_NO  "+
					" AND A.ACTION_STATUS='Y'  "+
					" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(B.RECEIPT_NO) = 0)  ");
					*/
					
					String m_date="";
					rs1= stmt1.executeQuery(" SELECT  TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
					if(rs1.next()){
					m_date=rs1.getString(1);
					}
					
					rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(PRE_CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND RELATION_MOVEMENT IN ('CHQENTRY') "+
					" AND (FACILITY_NO,CLIENT_CODE,DEBTOR_CODE) IN ( "+
					
					/*" SELECT A.CLIENT_CODE "+ 
					" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_LIMIT_ADJ A,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
					" WHERE B.RECEIPT_NO = C.RECEIPT_NO "+
					" AND B.RETURN_NO=A.SOURCE_REF_NO  "+
					" AND A.ACTION_STATUS='Y'  "+
					" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(B.RECEIPT_NO) = 0
					*/
					
					/*" SELECT  "+
					" DISTINCT FACILITY_NO,CLIENT_CODE,DEBTOR_CODE "+
					" FROM  "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B   "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO   "+
					" AND B.REBANK_STATUS IN('N','R')  "+
					" AND (B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)) =0  "+
					*/
					
					
					" SELECT  DISTINCT FACILITY_NO,CLIENT_CODE,DEBTOR_CODE  "+
					" FROM "+
					" (SELECT   "+
					" DISTINCT FACILITY_NO,CLIENT_CODE,DEBTOR_CODE ,SUM(B.REC_AMOUNT) REC ,SUM("+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)) ALLO "+
					" FROM  "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B   "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO    "+
					" AND   B.REBANK_STATUS IN('N','R')   "+
					" GROUP BY FACILITY_NO,CLIENT_CODE,DEBTOR_CODE "+
					" ) "+
					" WHERE REC-ALLO =0 "+
					
					
					")  ");
					

				out.println("<table align='center' width='100%' class='table' >"); 	
        out.println("<tr >");
				out.println("<td width='15%' ><DIV class=div_input><b>User Name</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>"+m_username+"</b></DIV></td>");
				out.println("<td width='*%' ><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("<tr >");
				out.println("<tr >");
				out.println("<td width='15%' ><DIV class=div_input><b>Date/Time</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>"+m_date+"</b></DIV></td>");
				out.println("<td width='*%' ><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("<tr >");
				out.println("</table>");
				
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				//m_string=m_string+"<td width='1%'></td>"; 
				out.println("<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><B>Client Code</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"); 
				out.println("<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>"); 
				out.println("<td width='15%' align='right' ><DIV class=div_input><b>Previous Debtor Credit Limit</b></DIV></td>"); 
				//out.println("<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"); 
				//out.println("<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>");
				out.println("<td width='*%'></td>");
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
					//m_string=m_string+"<td width='1%'></td>"; 
					out.println("<td width='15%' class=div_input   onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input  onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs1.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(6))+"</td>");
					//out.println("<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>");
					//out.println("<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 150px\" maxlength=\"200\">"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					chk_nums++;
				}
				out.println("<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">");
				out.println("</table>");
				

			

			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();


							
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

