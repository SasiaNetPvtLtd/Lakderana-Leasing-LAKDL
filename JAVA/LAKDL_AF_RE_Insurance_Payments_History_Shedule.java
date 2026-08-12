//Created by Minal for #14286 on 10-10-2014

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class LAKDL_AF_RE_Insurance_Payments_History_Shedule extends javax.servlet.http.HttpServlet {
  
    public  void service(HttpServletRequest req, HttpServletResponse res) throws IOException { // synchronized
		
		ServletOutputStream out = null;
	    Connection conn = null;
	    Statement stmt = null, stmt1 = null, stmt2 = null, stmt3 = null;
	    java.text.NumberFormat nf= null, nf1= null;
	    ResultSet rs= null, rs1= null, rs2= null, rs3= null;
	    String m_chksql = null ;
		CallableStatement callstmt1 =null;
        
        try {
            
            nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();			
			
			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;	
			String m_username = con_method.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			
			out = res.getOutputStream(); 
		
			m_chksql= req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date   = req.getParameter("to_date");
				String m_branch_code   = req.getParameter("branch_code");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_PAYMENT_HIS_RPT(:1,:2,:3);END;"); 
					callstmt1.setString(1,m_from_date);
					callstmt1.setString(2,m_to_date);
					callstmt1.setString(3,m_username);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
            
			else if(m_chksql.equals("main_page")){
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					
					out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Insurance Payment History Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Insurance Payment History Report - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='4'){"); 
				out.println("		finance_assign(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='5'){"); 
				out.println("		company_assign(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='7'){");  //Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("		branch_assign(oBj);"); 
				out.println("	}");
				out.println("	}"); 
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
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}");  
				
				out.println("function clear_fields(){ ");
				out.println("if(document.Form1.hid_help_type.value==\"4\"){");
				out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
				out.println("}");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}");
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=main_page';"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println(" if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function check_date(objdd,objmm,objyy) {");						
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");
				//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("}");
				out.println("}");
				

				out.println("function load_calendar(num) {");
				out.println("   document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.VAL_DAY1.value=v_date;");
				out.println("     document.Form1.VAL_MONTH1.value=v_month;");
				out.println("     document.Form1.VAL_YEAR1.value=val;");
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_from_date.value=date1");
				//	out.println("alert(document.Form1.hid_from_date.value);");
				out.println("}");
				
				out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.VAL_DAY2.value=v_date;");
				out.println("     document.Form1.VAL_MONTH2.value=v_month;");
				out.println("     document.Form1.VAL_YEAR2.value=val;");
				out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("document.Form1.hid_to_date.value=date2");
				out.println("}");
				
				out.println("}");			
				
				out.println("function main_date_chk(){");
				
				out.println("if(document.Form1.VAL_DAY1.value==\"\" || document.Form1.VAL_MONTH1.value==\"\" || document.Form1.VAL_YEAR1.value==\"\"){;");
				out.println("alert('Enter valid From Date...!');");
				out.println("return false;");
				out.println("}else");	
				
				out.println("if(document.Form1.VAL_DAY2.value==\"\" || document.Form1.VAL_MONTH2.value==\"\" || document.Form1.VAL_YEAR2.value==\"\"){;");
				out.println("alert('Enter valid To Date...!');");	
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");	
				out.println("}");
				
				out.println("function finance_help(){");
				out.println("Sql=\"m_help_Finance_Is_History\"; ");
				out.println("Crit=document.Form1.FIN_NO.value+'@';");
				out.println("HelpBox(1,10,0,Crit,Sql,4)");
				out.println("}");
				
				out.println("function finance_assign(oBj){");			
				out.println("document.Form1.FIN_NO.value=oBj.valout[2];");			
				out.println("}");	
				
				out.println("function company_help(){");
				out.println("Sql=\"m_help_Company\"; ");
				out.println("Crit=document.Form1.COM_NAME.value+'@';");
				out.println("HelpBox(1,10,0,Crit,Sql,5)");
				out.println("}");
				
				out.println("function company_assign(oBj){");			
				out.println("document.Form1.COM_NAME.value=oBj.valout[3];");			
				out.println("}");
			
			    out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("       durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("function run_report() {");
				out.println("       var m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value; ");
				//out.println("       var m_from_date = ''; ");
				out.println("       var m_to_date   = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value; ");
				out.println("       m_branch_code=document.Form1.TXT_BRANCH_CODE.value;"); //Added by Kanchana on 2015-12-29 for issue no 18429
				
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=run_report&branch_code=\"+m_branch_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date;"); 
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function view_report(){");
				
				out.println("   if(main_date_chk()){");
				
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				
				out.println("       m_business_type = document.Form1.TXT_BUSINESS_TYPE.value;   "); // udara 12-11-2014
				
				out.println("       m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				//out.println("       m_from_date='';");
				out.println("       m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("       m_branch_code=document.Form1.TXT_BRANCH_CODE.value;"); //Added by Kanchana on 2015-12-29 for issue no 18429
				
				out.println("       chkSql = 'view_report';");			
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=\"+chkSql+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date;");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=\"+chkSql+\"&branch_code=\"+m_branch_code+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date+\"&business_type=\"+m_business_type;"); // udara 12-11-2014
				out.println("       popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=600,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
				
				
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Insurance Payments History Report is generated. Use View Report button to get the view.');"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				
				//Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("function branch_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"3\";"); 
                out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
                out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+\"Y@\"; "); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,7);"); 
                out.println("}");
	
				out.println("function branch_assign(oBj){");			
                out.println("   document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");			
                out.println("}");
				
				
				out.println("</Script>");
			
				out.println("<body onload=\"load_sysdate()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Insurance Payment History Report </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				
				out.println("<td width='6%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				out.println("</table>");
				out.println("<BR>");
				
				out.println("<table align='center' width='100%' class='table' border='0'>");	
				
				// added by udara 12-11-2014
				out.println("<tr>"); ; 
				out.println("<td  width='5%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input> Business Type </DIV></td>"); 
				out.println("<td ='40%' ><select name='TXT_BUSINESS_TYPE' class='txt_input' style=\"width:100px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"NEW\" >New</option>");
				out.println("<option value=\"RENEW\" >Renewal</option>");				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				// end by udara 12-11-2014
				
				out.println("<tr class=tr_input>");
				out.println("<td width='5%' ID=VDATE>From</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='5%' ID=VDATE>To Date</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				
				out.println("<input class='but_input' type='button'  value=\"View Report\" onClick=\"view_report()\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button'  value=\"Run Report\"  onClick=\"run_report()\"  style='{width=150px}'>");
				
				out.println("</td>");			
				out.println("</tr>");
				
				//Added by Kanchana on 2015-12-29 for issue no 18429
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=DIV_BRANCH_CODE>Branch</td>");
				out.println("<td width='40%' ><input name=\"TXT_BRANCH_CODE\"   type=\"text\" maxlength=\"30\" style=\"width: 100px\" class=\"txt_input\" > ");
                out.println("<input class='but_input' type='button' name='HELP_BRANCH_CODE' value=\"Help\" onClick=\"branch_help()\" ></td>"); 	
				out.println("</td>");
				out.println("<td width='*%' > </td>");
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
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");

			}
			
			else if(m_chksql.equals("view_report")){ 
				
					String m_to_date   = req.getParameter("to_date");
					String m_from_date   = req.getParameter("from_date");
					String m_business_type   = req.getParameter("business_type"); // udara 12-11-2014
					String m_branch_code = req.getParameter("branch_code"); //Added by Kanchana on 2015-12-29 for issue no 18429
					String m_branch_code_sql ="";

					if(m_business_type.equals("ALL"))
						m_business_type = " ";
					else if(m_business_type.equals("NEW"))
						m_business_type = " AND BUSINESS_TYPE = 'NEW' ";
					else if(m_business_type.equals("RENEW"))
						m_business_type = " AND BUSINESS_TYPE = 'RENEWAL' ";
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE> Insurance Payment History Report - Drill </TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
	
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("	}");
					
					
					out.println("function show_drill_window(m_val_date,m_receiver,m_payee,m_pay_no,m_type){");
					out.println(" var m_branch_code='"+m_branch_code+"'; ");
					
					out.println("       chkSql   = 'show_drill_window';");			
					out.println("       m_url    = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_History_Shedule?chksql=\"+chkSql+\"&valDate=\"+m_val_date+\"&receiver=\"+m_receiver+\"&payee=\"+m_payee+\"&type=\"+m_type+\"&pay_no=\"+m_pay_no+\"&branch_code=\"+m_branch_code;");
					out.println("       window.open(m_url,'displayWindow5','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1'); ");
					out.println("}"); // popupwin = 
					
					out.println("function load_details(val) {"); 
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+val+\"&print=TRUE;\"");
					out.println("window.open(m_url,'popupwin','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0')");
					out.println("} "); 
					
					
					
					out.println("</script>"); 
					
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
					out.println("<form name='Form1'>");
					out.println("<br>");	
					out.println("<br>");	
					
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr >");
					out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Insurance Payment History Report </u></td>"); 
					out.println("</tr >");
					out.println("</table >");
					out.println("<br>");
					out.println("<br>");
					
					int rec_count = 1;
					String  Sql_data="";
					boolean more;
	
					// commented by udara 03-12-2014
					/*
					Sql_data = " "+
							" SELECT "+
								" TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'), "+
								" PAYMENT_NO, "+
								" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER),'-'), "+
								" SUM(SETTELED_AMOUNT), "+
								" COUNT(SETTELED_AMOUNT), "+
								" RECEIVER "+
								    " FROM "+m_schema_name+".AF_PAYMENT_HIS_RPT "+
									"  "+m_business_type+"  "+
										" GROUP BY ROLLUP(RECEIVER,PAYMENT_NO,EFF_VAL_DATE) "+
										" ORDER BY RECEIVER "+
						" ";
					*/
					
					if(!m_branch_code.equals("")){ //Added by Kanchana on 2015-12-29 for issue no 18429
				
						m_branch_code_sql =	" AND "+m_schema_name+".AF_CO_GET_BRANCH_CODE(FINANCE_NO) LIKE '"+m_branch_code+"%'";				
					}else{
						m_branch_code_sql ="";
					}
						
					
					// added by udara 03-12-2014
					Sql_data = " "+
							" SELECT "+
								" TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'), "+
								" PAYMENT_NO, "+
								//" INVOICE_NO, "+
								" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER),'-'), "+
								" SUM(SETTELED_AMOUNT), "+
								" COUNT(SETTELED_AMOUNT), "+
								" RECEIVER "+
								    " FROM "+m_schema_name+".AF_PAYMENT_HIS_RPT "+  
									"  WHERE ENT_USER = '"+m_username+"'  "+
									"  "+m_business_type+"  "+
									" "+m_branch_code_sql+" "+ //Added by Kanchana on 2015-12-29 for issue no 18429
										//" GROUP BY ROLLUP(RECEIVER,PAYMENT_NO,INVOICE_NO,EFF_VAL_DATE) "+
										" GROUP BY ROLLUP(RECEIVER,PAYMENT_NO,EFF_VAL_DATE) "+
										" ORDER BY RECEIVER "+
						" ";
					//	" AND "+m_schema_name+".AF_CO_GET_BRANCH_CODE_HIST_RPT(FINANCE_NO)  LIKE '%"+m_branch_code+"%' "+
					//	" AND "+m_schema_name+".AF_CO_GET_BRANCH_CODE(FINANCE_NO)  LIKE '%"+m_branch_code+"' "+
					//out.println(Sql_data);
					
					rs=stmt.executeQuery(Sql_data);
					more=rs.next();
					
					if(!more){
						out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
						out.println("</tr >");
						out.println("</table >");
					}
					
					double m_tot = 0.00;
					String m_td_color=null;
					int num_row=0;
					
					out.println("<table id=mytable align=\"left\" width=\"100%\" border=\"0\" class=\"table\"  cellspacing=0 > "); // udara 28-10-2014
					out.println("<tr>");// udara 28-10-2014
					//out.println("<td width=\"5%\" > &nbsp; </td>");// udara 28-10-2014
					out.println("<td width=\"*%\" align='left' >");// udara 28-10-2014

					out.println("<table id=mytable align=\"center\" width=\"100%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
					out.println("<tr>");
					out.println("<td width='5%'  class=div_input  align='left' bgcolor='lightblue' ><B> Disbursed Date </B></td>");
					out.println("<td width='10%'  class=div_input  align='left' bgcolor='lightblue' ><B> Payment Number </B></td>");
					//out.println("<td width='10%'  class=div_input  align='left' bgcolor='lightblue' ><B> Invoice </B></td>");
					out.println("<td width='10%' class=div_input  align='left' bgcolor='lightblue' ><B> Insurance Provider </B></td>");
					out.println("<td width='10%' class=div_input  align='right' bgcolor='lightblue' ><B> Payment Amount </B></td>");
					out.println("<td width='10%'  class=div_input  align='right' bgcolor='lightblue' ><B> No. Of Cases </B></td>");
					out.println("<td width='10%'  class=div_input  align='right' bgcolor='lightblue' ><B> &nbsp; </B></td>");
					out.println("</tr >");
					while(more){
						m_td_color="white";
						//if(num_row%2==0){
						//	m_td_color="#C9EEFF";
						//}
						
						// commented by udara 03-12-2014
						/*
						if( (rs.getString(1)!=null) && (rs.getString(2)!=null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(1)+" </td>"); // EFF_VAL_DATE
							out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_payment_drill('"+rs.getString(2)+"');\"  ><u>"+rs.getString(2)+"</u></td>"); // PAYMENT_NO
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+rs.getString(3)+" </td>"); // RECEIVER
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(4))+" </td>"); 
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rs.getInt(5)+" </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(6)+"','"+rs.getString(2)+"','single');\" ><u> "+nf.format(rs.getDouble(4))+" </u></td>"); // SETTELED_AMOUNT SUM
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(6)+"','"+rs.getString(2)+"','single');\" ><u> "+rs.getInt(5)+" </u></td>"); // SETTELED_AMOUNT COUNT
							out.println("</tr >");
						}
						else if( (rs.getString(1)==null) && (rs.getString(2)==null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(6)+"','"+rs.getString(2)+"','group');\" ><b><u> "+nf.format(rs.getDouble(4))+" </u></b></td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(6)+"','"+rs.getString(2)+"','group');\" ><b><u> "+rs.getInt(5)+" </u></b></td>"); 
							out.println("</tr >");
							
							out.println("<tr><td colspan=5 > &nbsp; </td></tr>");
							
						}
						*/
						
						if( (rs.getString(1)!=null) && (rs.getString(2)!=null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(1)+" </td>"); // EFF_VAL_DATE
							out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_payment_drill('"+rs.getString(2)+"');\"  ><u>"+rs.getString(2)+"</u></td>"); // PAYMENT_NO
							//out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_invoice_info_2('"+rs.getString(3)+"');\"  ><u>"+rs.getString(3)+"</u></td>");
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+rs.getString(3)+" </td>"); // RECEIVER
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(4))+" </td>"); 
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rs.getInt(5)+" </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(1)+"','"+rs.getString(3)+"','"+rs.getString(6)+"','"+rs.getString(2)+"','single');\" ><u> "+nf.format(rs.getDouble(4))+" </u></td>"); // SETTELED_AMOUNT SUM
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(1)+"','"+rs.getString(3)+"','"+rs.getString(6)+"','"+rs.getString(2)+"','single');\" ><u> "+rs.getInt(5)+" </u></td>"); // SETTELED_AMOUNT COUNT
							out.println("<td align ='center'><input type='button' name='DETA_BUTTON_"+rec_count+"' value='Detail' class='but_input' onClick=load_details('"+rs.getString(2)+"')></td>");
							out.println("</tr >");
						}
						else if( (rs.getString(1)==null) && (rs.getString(2)==null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							//out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(1)+"','"+rs.getString(3)+"','"+rs.getString(6)+"','"+rs.getString(2)+"','group');\" ><b><u> "+nf.format(rs.getDouble(4))+" </u></b></td>"); // commented by udara 30-11-2016
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('SETTLE_AMOUNT','"+rs.getString(3)+"','"+rs.getString(6)+"','"+rs.getString(2)+"','group');\" ><b><u> "+nf.format(rs.getDouble(4))+" </u></b></td>"); // added by udara 30-11-2016
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('SETTLE_AMOUNT','"+rs.getString(3)+"','"+rs.getString(6)+"','"+rs.getString(2)+"','group');\" ><b><u> "+rs.getInt(5)+" </u></b></td>"); 
							//out.println("<td align ='center'><input type='button' name='DETA_BUTTON_"+rec_count+"' value='Detail' class='but_input' onClick=load_details('"+rs.getString(2)+"')></td>");
							out.println("<td class=div_input  align='right'  ><B> &nbsp; </B></td>");
							out.println("</tr >");
							
							out.println("<tr><td colspan=5 > &nbsp; </td></tr>");
							
						}
						
						// added by udara 03-12-2014
						/*
						Commented by samith dilshan on 04-03-2015 (#15846)
						if( (rs.getString(1)!=null) && (rs.getString(2)!=null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(1)+" </td>"); // EFF_VAL_DATE
							out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_payment_drill('"+rs.getString(2)+"');\"  ><u>"+rs.getString(2)+"</u></td>"); // PAYMENT_NO
							out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_invoice_info_2('"+rs.getString(3)+"');\"  ><u>"+rs.getString(3)+"</u></td>");
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+rs.getString(4)+" </td>"); // RECEIVER
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(4))+" </td>"); 
							//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rs.getInt(5)+" </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(7)+"','"+rs.getString(2)+"','single');\" ><u> "+nf.format(rs.getDouble(5))+" </u></td>"); // SETTELED_AMOUNT SUM
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(7)+"','"+rs.getString(2)+"','single');\" ><u> "+rs.getInt(6)+" </u></td>"); // SETTELED_AMOUNT COUNT
							out.println("<td align ='center'><input type='button' name='DETA_BUTTON_"+rec_count+"' value='Detail' class='but_input' onClick=load_details('"+rs.getString(2)+"')></td>");
							out.println("</tr >");
						}
						else if( (rs.getString(1)==null) && (rs.getString(2)==null) ){
							out.println("<tr>");	
							out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > &nbsp; </td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(7)+"','"+rs.getString(2)+"','group');\" ><b><u> "+nf.format(rs.getDouble(5))+" </u></b></td>"); 
							out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_window('"+rs.getString(7)+"','"+rs.getString(2)+"','group');\" ><b><u> "+rs.getInt(6)+" </u></b></td>"); 
							//out.println("<td align ='center'><input type='button' name='DETA_BUTTON_"+rec_count+"' value='Detail' class='but_input' onClick=load_details('"+rs.getString(2)+"')></td>");
							out.println("<td class=div_input  align='right'  ><B> &nbsp; </B></td>");
							out.println("</tr >");
							
							out.println("<tr><td colspan=5 > &nbsp; </td></tr>");
							
						}
						*/
						
						//m_tot = m_tot + rs.getDouble(3);
						num_row++;
						more=rs.next();
						
						rec_count++;
					}

					out.println("</table>");
					

					out.println("</td>");// udara 28-10-2014
					out.println("</tr>");// udara 28-10-2014
					out.println("</table>"); // udara 28-10-2014
					
					
					
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
				
			}
			
			
			else if(m_chksql.equals("show_drill_window")){		
				
				String m_payee = "";
				String m_type = "";
				String m_pay_no = "";
				String m_val_date = "";
				String m_receiver = "";
				String m_branch_code = req.getParameter("branch_code"); //Added by Kanchana on 2015-2-9 for issue no 18429
				String m_branch_code_sql="";
				
				if(!m_branch_code.equals("")){ //Added by Kanchana on 2015-02-9 for issue no 18429
				
						m_branch_code_sql =	" AND "+m_schema_name+".AF_CO_GET_BRANCH_CODE(FINANCE_NO) LIKE '%"+m_branch_code+"'";				
				}else{
						m_branch_code_sql ="";
					}
				
				if(req.getParameter("type")!=null ){
					m_type = req.getParameter("type").trim();
				}
				
				if(req.getParameter("pay_no")!=null ){
					m_pay_no = req.getParameter("pay_no").trim();
				}
				
			
				
				if(req.getParameter("valDate").equals("SETTLE_AMOUNT") ){
					m_val_date = "";
				}
				else if(req.getParameter("valDate")!=null ){
					m_val_date = " AND TO_DATE(EFF_VAL_DATE,'DD-MM-YYYY') LIKE TO_DATE('"+req.getParameter("valDate").trim()+"','DD-MM-YYYY')  ";
				}
				//out.println(m_val_date);
				
				
				
				if(req.getParameter("receiver")!=null ){
					m_receiver = req.getParameter("receiver").trim();
				}
				
				
				
				if(m_type.equals("group")){
					m_type = " WHERE PAYMENT_NO LIKE '%%' ";
				}
				else if(m_type.equals("single")){
					m_type = " WHERE PAYMENT_NO = '"+m_pay_no+"' ";
				}
				
				
				
				
				if(req.getParameter("payee")!=null ){
					//m_payee = req.getParameter("payee").trim();
					//m_payee = " WHERE RECEIVER = '"+m_payee+"' ";
					m_payee = req.getParameter("payee").trim();
					
					if(m_payee.equals("") || m_payee==null || m_payee.equals("null") ){
						m_payee = " ";
					}
					else{
						m_payee = " AND RECEIVER = '"+m_payee+"' ";
					}
					
					
				}
				else{
					m_payee = " ";
				}
			
				//if(m_payee.equals("") || m_payee==null || m_payee.equals("null") ){
				//	m_payee = " ";
				//}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Insurance Payment History Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Insurance Payment History Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;

				
				Sql_data = " "+
						" SELECT "+
							" FINANCE_NO, "+
							" PAYMENT_NO, "+
							" INVOICE_NO, "+
							" NVL(SETTELED_AMOUNT,0) "+
							    " FROM "+m_schema_name+".AF_PAYMENT_HIS_RPT "+
									//"  WHERE RECEIVER = '"+m_payee+"' "+
									" "+m_type+" "+
									" "+m_payee+" "+
									" "+m_val_date+" "+
									"  AND ENT_USER = '"+m_username+"'  "+
									"  "+m_branch_code_sql+" "+ //Added by Kanchana on 2015-2-9 for issue no 18429
									"  ORDER BY PAYMENT_NO, FINANCE_NO   "+
					" ";
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='1%' class=div_input  align='left' bgcolor='lightblue' > &nbsp; </td>");
				out.println("<td width='5%' class=div_input  align='left' bgcolor='lightblue' ><B> Finance No </B></td>");
				out.println("<td width='20%' class=div_input align='left' bgcolor='lightblue' ><B> Payment No </B></td>");
				out.println("<td width='20%' class=div_input align='left' bgcolor='lightblue' ><B> Invoice No </B></td>");
				out.println("<td width='20%' class=div_input align='right' bgcolor='lightblue' ><B> Payment Amount. </B></td>");
				out.println("</tr >");
				
				int counts = 0;
				
				while(more){
					m_td_color="white";
					//if(num_row%2==0){
					//	m_td_color="#C9EEFF";
					//}
					
						counts = counts + 1;
					
						m_tot = m_tot + rs.getDouble(4);
							
						out.println("<tr>");
						out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+counts+" </td>");
						out.println("<td class='factoring-letter-body' STYLE='text-align:left;   cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+rs.getString(2)+" </td>");
				        out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_invoice_info_2('"+rs.getString(3)+"');\"  ><u>"+rs.getString(3)+"</u></td>");
						//out.println("<td class='factoring-letter-body' STYLE='text-align:left;'  bgcolor='"+m_td_color+"' > "+rs.getString(3)+" </td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(4))+" </td>"); 
						out.println("</tr >");
					
					
					
					//m_tot = m_tot + rs.getDouble(3);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			// end drilldown
			
			
			
			
			
			
			
			
        
		}     catch(Exception ex) {
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