//DEVELOPED BY : SANDUN JAYATHILAKE
//DATE         : 11 NOV 2008
//OFSCL BALANCE CONFIRMATION LETTER

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Bal_Confirmation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	java.text.NumberFormat nf,nf1;
	public String m_chksql;
	CallableStatement callstmt1 =null;	

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
 
			
			stmt=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			
			String m_schema_name = m_sn_methods.schema_name;
			
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			if(m_chksql.equals("run_document")){ 
			
				String m_date        = req.getParameter("as_at_date");
				String m_client_code = req.getParameter("client_code");
				String m_finance_no  = req.getParameter("finance_no");
				
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_BAL_CONFIRM_LETTER(:1,:2,:3,:4);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_client_code);
				callstmt1.setString(3,m_finance_no);
				callstmt1.setString(4,m_username);
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			
			else if(m_chksql.equals("main_page")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Balance Confirmation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			out.println("var m_client_name=\"\"; ");
			out.println("var m_client_name_1=\"\";");
			out.println("var id=0;");
								
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Balance Confirmation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Balance Confirmation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
						
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
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("	help_value_assign_4()");
	  	out.println("		}");
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("	help_value_assign_5()");
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
			out.println("}");
		
				

			out.println("function help_button_4() {");
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"ClientSql_Balance_Confim\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_BC\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_4() {"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("document.Form1.TXT_CLIENT_NMAE.value=oBj.valout[4];");
			out.println("document.Form1.TXT_CLIENT_ADD.value=oBj.valout[6]+','+oBj.valout[7]+','+oBj.valout[12];");
			//out.println("m_client_name=oBj.valout[4];");
			out.println("}");
			
			out.println("function help_value_assign_5() {"); 
			out.println("document.Form1.TXT_FINANCE.value=oBj.valout[2];");
			out.println("document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];");
			out.println("document.Form1.TXT_CLIENT_NMAE.value=oBj.valout[5];");
			out.println("document.Form1.TXT_CLIENT_ADD.value=oBj.valout[6]+','+oBj.valout[7]+','+oBj.valout[8];");
			//out.println("m_client_name=oBj.valout[5];");
			out.println("}");
		
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Bal_Confirmation?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Bal_Confirmation?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
			
							
			out.println("function load_c_date(val) {");			
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			
      out.println("  if(document.Form1.hid_cal_date.value=='1'){");			
			out.println("     document.Form1.AT_DAY.value=v_dd;");
			out.println("     document.Form1.AT_MONTH.value=v_mm;");
			out.println("     document.Form1.AT_YEAR.value=v_yy;");
			out.println("  }");				
			out.println("}");	
			
			//out.println("foundAtStartPos = ast_des.indexOf('@');");
			//out.println("foundAtEndPos = ast_des.lastIndexOf('@');");
			out.println("function chk_address(val){");
			out.println("if(val!=\"\"){");
			out.println("if(val.charAt(val.length)!=\".\"){");
			out.println("val = val + \".\";");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			out.println("function part_client_add(val){");
			out.println("if(val!=\"\"){");
			out.println("add_pos_1 = val.indexOf(',');");
			out.println("add_1 = val.substring(0,add_pos_1);");
			out.println("alert(add_1);");
			out.println("add_2 = val.substring(add_pos_1+1,val.lastIndexOf(','));");
			out.println("alert(add_2);");
			out.println("add_3 = val.substring(val.lastIndexOf(',')+1,val.indexOf('.'));");
			out.println("alert(add_3);");
			out.println("}");
			out.println("}");
			
			out.println("function load_document() {"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){");
			out.println("DIV_TXT_CLIENT.style.color='red';");
			out.println("}");
			out.println("else{");
			out.println("m_client_add  = document.Form1.TXT_CLIENT_ADD.value;");
			out.println("val=m_client_add;");
			//----check client address
			out.println("if(val!=\"\"){");
			out.println("if(val.charAt(val.length)!=\".\"){");
			out.println("val = val + \"@\";");
			out.println("}");
			out.println("else{");
			out.println("val = val + \"@\";");
			out.println("}");
			//--partition client address
			out.println("add_pos_1 = val.indexOf(',');");
			out.println("add_1 = val.substring(0,add_pos_1);");
			out.println("add_2 = val.substring(add_pos_1+1,val.lastIndexOf(','));");
			out.println("add_3 = val.substring(val.lastIndexOf(',')+1,val.indexOf('@'));");
			out.println("}");				
			//---load Balance Confirmation Letter			
			out.println("as_at_date = document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Bal_Confirm_Letter?chksql=main_page&add_1=\"+add_1+\"&add_2=\"+add_2+\"&add_3=\"+add_3+\"&client_name=\"+document.Form1.TXT_CLIENT_NMAE.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE.value+\"&as_at_date=\"+as_at_date+\" \";");
			//out.println("alert(m_url);");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=0,width=700,height=900,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=0');");
			out.println("}");
			out.println("}");		
			
			out.println("function run_document() {"); //Added by Sandun on 07-11-2009
			out.println("as_at_date = document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Bal_Confirmation?chksql=run_document&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE.value+\"&as_at_date=\"+as_at_date+\" \";");
			out.println(" load_interface(m_url,'NORM');");
			out.println("}");	
			
			out.println("function get_vector_normal(m_data){");//Added by Sandun on 07-11-2009
			out.println("		if(m_data==\"OK\"){");
			out.println("			load_document();"); 
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Balance Confiremation letter...'+m_data);");
			out.println("		}");
			out.println("}");
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_at_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Balance Confirmation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			String m_sys_date_dd="";
			String m_sys_date_mm = "";
			String m_sys_date_yy = "";
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
					boolean more1 = rs.next();
						
							if (more1){
              			
										m_sys_date_dd=rs.getString(1);
										m_sys_date_mm=rs.getString(2);
										m_sys_date_yy=rs.getString(3);
										}
			out.println("<tr>");  
			out.println("<td width='10%' ><DIV id='DIV_TXT_DATE'  class=div_input>As At Date </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='AT_DAY' maxlength='2' size='2'   value='"+m_sys_date_dd+"' onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)>");
			out.println("<input class='txt_input5' type='text' name='AT_MONTH' maxlength='2' size='2' value='"+m_sys_date_mm+"' onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)>");
			out.println("<input class='txt_input5' type='text' name='AT_YEAR' maxlength='4' size='4'  value='"+m_sys_date_yy+"' onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='15' onblur=help_button_4()>"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" Help \" onClick=\"help_button_4()\"></td>"); 
	    out.println("<td width='*%'>&nbsp;</td>");
		  out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='10%'>Client Name</td>");
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_NMAE' style='width=200' ></td>");
		  out.println("<td width='5%'>Address</td>");
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_ADD' style='width=350'></td>");
			out.println("<td width='*%'>&nbsp;</td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='15' size='15' onblur=help_button_5()>"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" Help \" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='10%'><input type='button' name='VIEW_BTT' value='View' class='but_input' onclick=run_document()></td>");
			out.println("<td width='*%'></td>");
		  out.println("</tr>");
			
		  out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
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
