//SCREEN NAME:INSURANCE DETAIL REPORT FOR MANAGEMENT INFORMATION COLLECTION 
//CREATED BY:SANDUN
//DATE/TIME:20/10/2008
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;


public class LAKDL_AF_RE_Collection_Insurnce_Detail_Report extends HttpServlet { 
    
	/*
    Connection conn;
    Statement stmt,stmt1;
    public ResultSet rs,rs1;
    PreparedStatement pstmt;
    java.text.NumberFormat nf;
	*/
    
    public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		Connection conn = null;
	    Statement stmt= null,stmt1= null;
	    ResultSet rs= null,rs1= null;
	    PreparedStatement pstmt= null;
	    java.text.NumberFormat nf= null;
        
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
            
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setContentType("text/html"); 
            
            ServletOutputStream out = res.getOutputStream(); 
            
            
            String m_screen_type= req.getParameter("chksql");			
            
            
            if(m_screen_type.trim().equals("main_page")){	
                
                
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
                out.println("help_box.innerHTML=\"  Collection Prossess - Insurance Detail Report - \"+m_val;"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function load_roll_out_value(){");
                out.println("help_box.innerHTML=\"  Collection Prossess - Insurance Detail Report - \"+document.Form1.hid_status.value;"); 
                out.println("}"); 
                
                out.println("function MyDialog(){"); 
                out.println("    this.valout   = new Array(10);"); 
                out.println("}		"); 
                out.println(""); 
                
                out.println("           function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
                out.println("               oBj = new MyDialog();"); 
                out.println("               oBj.valout[1]  = \" \";"); 
                out.println("               oBj.valout[2]  = \" \";"); 
                out.println("               oBj.valout[3]  = \" \";"); 
                out.println("		        popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                out.println("               if(oBj.valout[1] ==\" \"){"); 
                out.println("                   clear_fields(); ");
                out.println("		        } else ");
                out.println("	            if(oBj.valout[1] !=\" \"){"); 
                out.println("	                if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	                    if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	                        if(oBj.valout[1]!=\"Next\"){"); 
                out.println("	                            if(oBj.valout[1]=='Next')  {");
                out.println("		                            Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
                out.println("	                            }");
                out.println("	                            else if  (oBj.valout[1]=='Prev') {");
                out.println("		                            Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
                out.println("	                            }");
                out.println("	                            else if(oBj.valout[1] == 'Close'){");
                out.println("	                            }");
                out.println("	                            else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
                out.println("	                                if(IfCount=='5'){"); 
                out.println("		                                company_assign(oBj);"); 
                out.println("	                                }");
				
				out.println("	                                if(IfCount=='6'){"); 
                out.println("		                                finance_assign(oBj);"); 
                out.println("	                                }");
				out.println("	                                if(IfCount=='7'){");  //added by kanchana on 2015-12-23
                out.println("		                                branch_assign(oBj);"); 
                out.println("	                                }");
				
                out.println("	                            }"); 
                out.println("	                        }"); 
                out.println("	                        else{"); 
                out.println("		                        Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("		                        return false;"); 
                out.println("	                        } "); 
                out.println("	                    }"); 
                out.println("	                    else{	"); 
                out.println("	                        Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("	                    }	"); 
                out.println("	                }		"); 
                out.println("	                else{	"); 
                out.println("                       clear_fields(); ");
                out.println("	                }	"); 
                out.println("	            }	"); 
                out.println("           }");  
                
                // out.println("function clear_fields(){ ");
                // out.println("if(document.Form1.hid_help_type.value==\"4\"){");
                // out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
                // out.println("}");
                // out.println("}");
                
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
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Insurnce_Detail_Report?chksql=main_page';"); 
                out.println("		}"); 
                out.println("}"); 
                
                out.println("function new_window(){	"); 
                out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Insurnce_Detail_Report?chksql=main_page';"); 
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
                out.println(" document.Form1.hid_cal_date.value=num;"); 
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
                
                out.println("function get_vector_normal(http_response) {");
                out.println(" request_details.innerHTML = ''; ");
                out.println(" request_details.innerHTML = http_response; ");
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
                
                
                out.println("function makeRequest(){");
                out.println("if(main_date_chk()){");
                out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
                out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
                out.println("m_report_type = document.Form1.TXT_REPORT_TYPE.value;");
                out.println("m_insurance_company = document.Form1.COM_NAME.value;");
				out.println("m_active_status = document.Form1.TXT_ACTIVE_STATUS.value;"); 
				out.println("m_premium_status = document.Form1.TXT_PREMIUM_STATUS.value;"); // udara 25-08-2014
				out.println("m_finance_no = document.Form1.TXT_FINANCE_NO.value;"); // udara 21-10-2014
				out.println("m_branch_code = document.Form1.TXT_BRANCH_CODE.value;"); // Added by Kanchana on 2015-12-23
                //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Insurance_Detail_Report_display?chksql=load_details&insurance_company=\"+m_insurance_company+\"&report_type=\"+m_report_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value+\"&active_status=\"+m_active_status;");
                
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Insurance_Detail_Report_display?chksql=load_details&insurance_company=\"+m_insurance_company+\"&report_type=\"+m_report_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value+\"&active_status=\"+m_active_status+\"&premium_status=\"+m_premium_status;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Insurance_Detail_Report_display?chksql=load_details&insurance_company=\"+m_insurance_company+\"&report_type=\"+m_report_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value+\"&active_status=\"+m_active_status+\"&premium_status=\"+m_premium_status+\"&branch_code=\"+m_branch_code+\"&finance_no=\"+m_finance_no;"); // udara 21-10-2014
				
				
				//out.println("load_interface(m_url,'NORM');");
                out.println("window.open(m_url,'popupwin','left=180,top=110,width=800,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1')");
				out.println("}");
                out.println("}");
                
                out.println("function load_insuaance_history(val){");//Added By Sandun on07-01-2009
                //out.println("alert(val);");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_History?chksql=main_page&finace_no=\"+val+\"\";");
                //out.println("window.open(m_url);");
                out.println("window.open(m_url,'popupwin','left=180,top=110,width=600,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0')");
                out.println("} "); 
                
                
                out.println("function company_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"1\";"); 
                out.println("    m_sql = \"m_help_Company\";"); 
                out.println("    m_criteria = document.Form1.COM_NAME.value+\"@\"+\"N@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,5);"); 
                out.println("}");
                
                
                out.println("function clear_fields(){");
                out.println("if(document.Form1.hid_help_type.value==\"1\"){");
                out.println(" document.Form1.COM_NAME.value=\"\";"); 
                out.println("}");
                out.println("}");
                
                out.println("function company_assign(oBj){");			
                out.println("document.Form1.COM_NAME.value=oBj.valout[2];");			
                out.println("}");	
				
				// added by udara 21-10-2014
				
				out.println("function finance_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"2\";"); 
                out.println("    m_sql = \"m_help_TXT_FinanceSql2_sql\";"); 
                out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"; "); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,6);"); 
                out.println("}");
				
				out.println("function clear_fields(){");
                out.println("  if(document.Form1.hid_help_type.value==\"2\"){");
                out.println("    document.Form1.TXT_FINANCE_NO.value=\"\";"); 
                out.println("  }");
                out.println("}");
		
				out.println("function finance_assign(oBj){");			
                out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");			
                out.println("}");	
				

				//Added by Kanchana Karunarathna 2015-12-23
				out.println("function branch_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"3\";"); 
                out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
                out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+\"Y@\"; "); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,7);"); 
                out.println("}");
	
				out.println("function branch_assign(oBj){");			
                out.println("   document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");			
                out.println("}");
				
				// added by udara 21-10-2014
				
                
                
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
                out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Prossess - Insurance Detail Report </td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
                out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
                out.println("<tr>");
                //out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");
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
                out.println("<tr class=tr_input>"); 
                out.println("<td width='9%'>Report Type</td>"); 
                out.println("<td width='15%'><select name='TXT_REPORT_TYPE' class='txt_input' style=\"width:100px;\" onchange=\"\">");
                out.println("<option value=\"ALL\" >All</option>"); // added by udara 11-09-2014
				out.println("<option value=\"NEW\" >New</option>");
                // out.println("<option value=\"RENEW\" >Renewed</option>");
                out.println("<option value=\"RENEWAL\" >Renewed</option>");
                //	out.println("<option value=\"TOBERENEW\" >To Be Renewed</option>");
                out.println("</select>");
                out.println("</td>");
                out.println("</tr>"); 
                
                out.println("<tr class=tr_input>");
                out.println("<td width='7%' ID=VDATE>From</td>");
                out.println("<td width='15%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
                out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
                out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
                out.println("</td>");
                out.println("<td width='2%'></td>"); 
                out.println("<td width='2%' ID=VDATE>To</td>");
                out.println("<td width='15%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
                out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
                out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
                out.println("</td>");
                out.println("<td width='10%'></td>"); 
                out.println("<td width='15%' align='left'><input type=\"button\" class='mainbut'onClick='makeRequest()' value=\"Go\"></td>"); 
                out.println("<td width='10%'></td>"); 
                out.println("</tr>");	
                // out.println("</table>");
                
                // out.println("<table class='table' width='100%'  >"); 
                out.println("<tr>"); 
                out.println("<td width='7%' ><DIV id='DIV_COM_NAME'  class=div_input> Company</DIV></td>"); // <b> Company</b>
                out.println("<td width='15%' ><input class='txt_input' type='text' name='COM_NAME' maxlength='10' size='50' style='width:100' onblur=\"company_help()\" >"); 
                out.println("<input class='but_input' type='button' name='COM_HELP' value=\"Help\" onClick=\"company_help()\" ></td>"); 
              out.println("<td width='2%'></td>"); 
				out.println("<td  width='10%' >Insurance Done By</td>"); 
				out.println("<td ='15%' ><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:80px;\" >");
				//out.println("<option value=\"LICENSEE\" >Licensee</option>");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"LICENSEE\" >Company</option>");
				//out.println("<option value=\"BROKER\" >Broker</option>");
				out.println("<option value=\"CLIENT\" >Lessee</option>");
				
				out.println("</select>");
				out.println("</td>");
                out.println("</tr>"); 
				
				// added by udara 18-07-2014
				
				out.println("<tr>"); 
                
				out.println("<td  width='7%' >Activated Status</td>"); 
				out.println("<td ='15%' ><select name='TXT_ACTIVE_STATUS' class='txt_input' style=\"width:80px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"ACTIVE\" >Activated</option>");
				out.println("<option value=\"TERMI\" >Terminated</option>");
				out.println("<option value=\"NON\" >Not Activated</option>");
				out.println("</select>");
				out.println("</td>");
				
				// commented by udara25-08-2014
				/*
				out.println("<td width='2%' > &nbsp; </td>"); 
                out.println("<td width='10%' > &nbsp; </td>"); 
              	out.println("<td width='15%'></td>"); 
				*/
				
				// added by udara 25-08-2014
				out.println("<td width='2%' > &nbsp; </td>"); 
                out.println("<td width='10%' > Premium Status </td>"); 
              	out.println("<td ='15%' ><select name='TXT_PREMIUM_STATUS' class='txt_input' style=\"width:80px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"PACTIVE\" >Activated</option>");
				out.println("<option value=\"PCANCEL\" >Cancelled</option>");
				out.println("</select>");
				out.println("</td>");
				// end by udara 25-08-2014
				
                out.println("</tr>"); 
				
				// end by udara 18-07-2014
				
				
				// added by udara 21-10-2014
				
				out.println("<tr>"); 
				
				out.println("<td width='7%' ><DIV id='DIV_FINANCE_NO'  class=div_input> Finance No. </DIV></td>"); 
                out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='30' size='50' style='width:100' onblur=\"\" >"); //finance_help()
                out.println("<input class='but_input' type='button' name='HELP_FINANCE_NO' value=\"Help\" onClick=\"finance_help()\" ></td>"); 
				
                out.println("<td width='2%'></td>"); 
				out.println(" &nbsp; ");
				out.println("</td>");
				
				//Added by Kanchana on 2015-12-23
				out.println("<td width='7%' ><DIV id='DIV_BRANCH_CODE'  class=div_input> Branch </DIV></td>"); 
                out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='30' size='50' style='width:100' onblur=\"\" >"); //finance_help()
                out.println("<input class='but_input' type='button' name='HELP_BRANCH_CODE' value=\"Help\" onClick=\"branch_help()\" ></td>"); 	
				
				out.println("</tr>"); 
				
				// end by udara 21-10-2014
				
				
                out.println("</table>");  
                
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                
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
            else if(m_screen_type.equals("load_details")){
                
                String m_from_date          = req.getParameter("from_date");
                String m_to_date            = req.getParameter("to_date");
                String m_report_type        = req.getParameter("report_type");
                String m_insurance_company  = req.getParameter("insurance_company");
                
                if (m_insurance_company.equals("")) {
                    m_insurance_company = "%%";
                }
                
                String sql = null;
                
                sql = " " +
                    "   SELECT TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
                    "          DEBIT_NOTE_NO, " +
                    "          POLICY_NO, " +
                    "          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(" + m_schema_name + ".AF_CO_GET_CLIENT_CODE(" + m_schema_name + ".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))) CLIENT_NAME, " +
                    "          SUM_INSSURED, " +
                    "          PREMIUM, " +
                    "          (RCC + TC) SRCC_TC, " +
                    "          TAX_DUE, " +
                    "          BASIC_PREMIUM, " +
                    "          BASIC_PREMIUM_COMMISION, " +
                    "          RCC_TC_COMMISION, " +
                    "          VAT_ON_TOTAL_COMMISION " +
                    "   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A " +
                    "   WHERE  UPPER(A.BUSINESS_TYPE) = UPPER('" + m_report_type + "') " +
                    "   AND    A.INSUR_COM LIKE '" + m_insurance_company + "' " +
                    "   AND    A.START_DATE >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
                    "   AND    A.START_DATE <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
                    "   ORDER BY A.START_DATE DESC " +
                    " ";
                
                // if(m_report_type.equals("RENEW")){
                    // rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
                        // " NVL(A.ASSET_DESCRIPTION,'-'), "+ //2
                        // " TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //3
                        // " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //4
                        // " NVL(A.SUM_INSSURED,0), "+ //5
                        // " NVL(A.PREMIUM,0), "+  //6
                        // " DECODE(A.INSURED_BY,'LICENSEE','Licensee' ,'BROKER','Broker','CLIENT','Client',A.INSURED_BY), "+ //7
                        // " NVL(A.INSUR_COM,'-') , "+ //8
                        // " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) "+ //9
                        // " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
                        // "      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                        // " WHERE A.FINANCE_NO = B.FINANCE_NO "+
                        // " AND   B.APPLICATION_STATUS='ACTIVATED' "+	
                        // " AND   A.END_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
                        // " AND   A.END_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
                    
                // }
                // else{
                    
                    // rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
                        // " NVL(A.ASSET_DESCRIPTION,'-'), "+ //2
                        // " TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //3
                        // " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //4
                        // " NVL(A.SUM_INSSURED,0), "+ //5
                        // " NVL(A.PREMIUM,0), "+  //6
                        // " DECODE(A.INSURED_BY,'LICENSEE','Licensee' ,'BROKER','Broker','CLIENT','Client',A.INSURED_BY), "+ //7
                        // " NVL(A.INSUR_COM,'-') , "+ //8
                        // " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) "+ //9
                        // " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
                        // "      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                        // " WHERE  A.FINANCE_NO = B.FINANCE_NO "+
                        // " AND    B.APPLICATION_STATUS='ACTIVATED' "+												 
                        // " AND    A.END_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
                        // " AND    A.END_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
                    
                    
                    
                    
                    
                // }
                
                rs = stmt.executeQuery(sql);
                boolean more = rs.next();
                int j=1;
                if (!more) {
                    out.println("<table align = \"center\" width = \"100%\" class = \"table\" border = \"0\">");
                    out.println("   <tr>");
                    out.println("       <td width = \"20%\" align = \"center\"><font color = \"red\">No Data Found...!</font></td>");
                    out.println("   </tr>");
                    out.println("<table>");
                }
                else {
                    out.println("<table align = \"center\" width = \"100%\" class = \"table\" border = \"0\">");
                    out.println("   <tr style = \"font-weight: bold;\">");
                    out.println("       <td width = \"10%\" align = \"center\">C/N Date</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Debit Note No.</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Policy No.</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Name of Insured</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Sum Insured</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Total Premium</td>");
                    out.println("       <td width = \"10%\" align = \"center\">SRCC/TC</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Taxes & Others</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Basic Premium</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Commision on Premium</td>");
                    out.println("       <td width = \"10%\" align = \"center\">SRCC/TC Commision</td>");
                    out.println("       <td width = \"10%\" align = \"center\">Total Commision</td>");
                    out.println("       <td width = \"10%\" align = \"center\">VAT Portion</td>");
                    out.println("   </tr>");
                    // out.println("<table>");
                    
                    
                    // out.println("<hr>"); 
                    // out.println("<table align='center' width='100%' class='table' border=0>"); 
                    // out.println("<tr class=pdn_txtpos2>"); 
                    // out.println("<td width='15%' align ='left'>Finance No.</td>"); 
                    // out.println("<td width='25%' align ='left'>Client Name</td>"); 			
                    // out.println("<td width='20%' align ='left'>Asset Description</td>"); 
                    // out.println("<td width='10%' align ='left'>Start Date</td>"); 
                    // out.println("<td width='10%' align ='left'>End Date</td>");
                    // out.println("<td width='15%' align ='right'>Sum Inssured</td>");
                    // out.println("<td width='15%' align ='right'>Premium</td>");
                    // out.println("<td width='15%' align ='left'>Insured By</td>");
                    // out.println("<td width='20%' align ='left'>Insurance company</td>");
                    // out.println("<td width='10%' align ='center'>Insurance History</td>");
                    // out.println("</tr>"); 
                }
                
                while (more) {
                    if (j % 2 == 1) {
                        out.println("<tr class = \"tr_input\">");
                    }
                    else {
                        out.println("<tr class = \"tr_input1\">");
                    }
                    
                    out.println("<td width = \"10%\" align = \"center\">" + rs.getString("START_DATE") + "</td>");
                    out.println("<td width = \"10%\" align = \"left\">" + rs.getString("DEBIT_NOTE_NO") + "</td>");
                    out.println("<td width = \"10%\" align = \"left\">" + rs.getString("POLICY_NO") + "</td>");
                    out.println("<td width = \"10%\" align = \"left\">" + rs.getString("CLIENT_NAME") + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("SUM_INSSURED")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("PREMIUM")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("SRCC_TC")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("TAX_DUE")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM_COMMISION")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("RCC_TC_COMMISION")) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM_COMMISION").add(rs.getBigDecimal("RCC_TC_COMMISION"))) + "</td>");
                    out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("VAT_ON_TOTAL_COMMISION")) + "</td>");
                    // out.println("<td width='25%' align ='left'>"+rs.getString(9)+"</td>");
                    // out.println("<td width='20%' align ='left'>"+rs.getString(2)+"</td>"); 
                    // out.println("<td width='10%' align ='left'>"+rs.getString(3)+"</td>"); 
                    // out.println("<td width='10%' align ='left'>"+rs.getString(4)+"</td>");
                    // out.println("<td width='15%' align ='right'>"+nf.format(rs.getDouble(5))+"</td>");
                    // out.println("<td width='15%' align ='right'>"+nf.format(rs.getDouble(6))+"</td>");
                    // out.println("<td width='15%' align ='left'>"+rs.getString(7)+"</td>");
                    // out.println("<td width='20%' align ='left'>"+rs.getString(8)+"</td>");
                    // out.println("<td width='10%' align ='center'><input type='button' class='but_input' name='DETAIL_BTT_"+j+"' value='Detail' onClick=load_insuaance_history('"+rs.getString(1)+"')></td>");//Added By Sandun 07-01-2009
                    out.println("</tr>");
                    
                    more = rs.next();
                    j = j + 1;
                }
                out.println("</table>"); 
                
            }
            
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
        }
        
        catch (Exception e) { 
            try { 
                
            }
            catch (Exception eti) {}
            
            ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
            e.printStackTrace(new PrintStream(ostr));
            
            ServletOutputStream out = res.getOutputStream();
            out.println(ostr.toString()); 
            out.close();
            
        }
    }
}
