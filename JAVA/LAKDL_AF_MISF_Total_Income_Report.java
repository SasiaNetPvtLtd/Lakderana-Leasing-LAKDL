// DEVELOP BY : NUWAN FOR OFSCL 
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.math.BigDecimal;


public class LAKDL_AF_MISF_Total_Income_Report extends javax.servlet.http.HttpServlet { 
    
    ServletOutputStream out = null;
    Connection conn;
    java.text.NumberFormat nf,nf1;
    Statement stmt,stmt1,stmt2;
    CallableStatement callstmt1 =null;
    public ResultSet rs,rs1,rs2;
    java.lang.Math a;
    
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
        
        Statement statement = null;
        Statement statement_1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet_1 = null;
        String sql = null;
        
        try { 
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
            conn = m_sn_methods.met_user_validate(req); 
            String m_html_client_url=m_sn_methods.html_client_url.trim(); 
            String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
            String m_fschema_name=m_sn_methods.client_name.trim();
            String m_schema_name=m_sn_methods.schema_name;
            String m_username=m_sn_methods.username;
            //m_username="OFSCLALL";
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setContentType("text/html"); 
            out = res.getOutputStream(); 
            
            nf = java.text.NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(0);
            nf.setMaximumFractionDigits(0);
            
            nf1 = java.text.NumberFormat.getInstance(Locale.US);
            nf1.setMinimumFractionDigits(2);
            nf1.setMaximumFractionDigits(2);
            
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            
            String m_chksql=req.getParameter("chksql");
            
            if(m_chksql.equals("run_report")){ 
                
                String m_date=req.getParameter("date");
                String m_date_to=req.getParameter("date_to");
                //String m_client_code=req.getParameter("client_code");
                //String m_coll_officer=req.getParameter("coll_officer");
                
                try{
                    callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_TOTAL_INCOME(:1,:2,:3);END;");
                    callstmt1.setString(1,m_date);
                    callstmt1.setString(2,m_date_to);
                    //callstmt1.setString(2,m_client_code);
                    //callstmt1.setString(3,m_coll_officer);
                    callstmt1.setString(3,m_username);
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
                out.println("<TITLE>Finance - Total Income Report</TITLE>"); 
                out.println("</HEAD>"); 
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
                out.println("<SCRIPT language=\"JavaScript\">"); 
                out.println("var timerID;");
                out.println("var durationID=0;");
                
                out.println("function set_timer_actions() {");
                out.println("   durationID=durationID+1;");
                out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
                out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
                out.println("}");
                
                out.println("function load_sysdate(){	"); 
                rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
                if(rs1.next()){
                    //out.println("document.Form1.VAL_DAY.value='"+rs1.getString(1)+"';");
                    //out.println("document.Form1.VAL_MONTH.value='"+rs1.getString(2)+"';");
                    //out.println("document.Form1.VAL_YEAR.value='"+rs1.getString(3)+"';");
                    
                    
                    out.println("document.Form1.VAL_DAY_TO.value='"+rs1.getString(1)+"';");
                    out.println("document.Form1.VAL_MONTH_TO.value='"+rs1.getString(2)+"';");
                    out.println("document.Form1.VAL_YEAR_TO.value='"+rs1.getString(3)+"';");
                    
                    out.println("document.Form1.hid_SYS_DATE_DD.value='"+rs1.getString(1)+"'");
                    out.println("document.Form1.hid_SYS_DATE_MM.value='"+rs1.getString(2)+"'");
                    out.println("document.Form1.hid_SYS_DATE_YY.value='"+rs1.getString(3)+"'");
                    
                    rs1= stmt1.executeQuery(
                        " SELECT TO_CHAR(A.TO_DATE+1,'DD') , TO_CHAR(A.TO_DATE+1,'MM'), TO_CHAR(A.TO_DATE+1,'YYYY') "+
                        " FROM "+m_schema_name+".AF_CO_MAS_SYS_BRANCH_ACC_DATE A ");
                    if(rs1.next()){
                        out.println("document.Form1.VAL_DAY.value='"+rs1.getString(1)+"';");
                        out.println("document.Form1.VAL_MONTH.value='"+rs1.getString(2)+"';");
                        out.println("document.Form1.VAL_YEAR.value='"+rs1.getString(3)+"';");
                        
                    }
                    
                    
                }
                out.println("}"); 
                
                out.println("function load_lock(){	"); 
                //out.println("		document.oncontextmenu=new Function(\"return false\");"); 
                out.println("}"); 
                
                out.println("function clear_window(){	"); 
                out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Income_Report?chksql=main_page';"); 
                out.println("		}"); 
                out.println("}"); 
                
                out.println("function new_window(){	"); 
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Income_Report?chksql=main_page';"); 
                out.println("}"); 
                
                out.println("function load_help_msg() {"); 
                out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
                out.println("    HelpBox_msg(m_help_message);"); 
                out.println("}"); 	
                
                out.println("function HelpBox_msg(m_help_message) {"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
                out.println("  \"&help_message_in=\"+m_help_message);"); 
                out.println("}"); 
                
                out.println("function load_roll_value(m_val){"); 
                out.println("		help_box.innerHTML=\" Finance - Capital Balance OutStanding Report - \"+m_val;"); 
                out.println("}"); 
                
                out.println("function load_roll_out_value(){");
                out.println("}");
                
                out.println("function MyDialog(){"); 
                out.println("    this.valout=new Array(10);"); 
                out.println("}");
                
                out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
                out.println("	oBj = new MyDialog();"); 
                out.println(" oBj.valout[1]=\" \";"); 
                out.println(" oBj.valout[2]=\" \";"); 
                out.println(" oBj.valout[3]=\" \";"); 
                out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("		if(oBj.valout[1]!=\"Next\"){"); 
                out.println("			if(IfCount==\"1\"){"); 
                out.println("			client_assign(oBj);"); 
                out.println("			}"); 
                out.println("			if(IfCount==\"3\"){"); 
                out.println("			help_value_assign_collection(oBj);"); 
                out.println("			}"); 
                out.println("		}");
                out.println("		else{"); 
                out.println("			Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("			return false;"); 
                out.println("		}"); 
                out.println("	}");
                out.println("	else{"); 
                out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("	}"); 
                out.println("	}");
                out.println("	}	");
                out.println("}"); 
                
                out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
                out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
                out.println("}"); 
                
                out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
                out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
                out.println("}"); 
                
                out.println("function client_help(){");
                out.println("	Crit=document.Form1.CLIENT_CODE.value+\"@\";");
                out.println(" document.Form1.hid_help_type.value='1';");
                out.println("	HelpBox('1','10','0',Crit,'ClientSql','1');");
                out.println("}");		
                
                out.println("function client_assign(oBj){");
                out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
                out.println("}");
                
                /*out.println("function help_button_collection_officer() {"); 
                out.println(" document.Form1.hid_help_type.value='3';");
                out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.hid_save_status.value+\"@Y@\";"); 
                out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
                out.println("}"); 
                */
                
                out.println("function help_button_collection_officer() {"); 
                out.println(" document.Form1.hid_help_type.value='3';");
                out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
                out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
                out.println("}"); 
                
                out.println("function help_value_assign_collection(oBj) {"); 
                out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
                out.println("}"); 
                
                out.println("function check_Date(objDD,objMM,objYY) {");
                out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
                out.println("if(checkMonthLength(objDD,objMM,objYY)){");
                out.println("validate_date(objDD,objMM,objYY,document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY)");
                out.println("}");
                out.println("}");
                
                out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
                
                out.println("if(!chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY)){");
                out.println("FROM_DD.value=document.Form1.hid_SYS_DATE_DD.value;");
                out.println("FROM_MM.value=document.Form1.hid_SYS_DATE_MM.value;");
                out.println("FROM_YY.value=document.Form1.hid_SYS_DATE_YY.value;");
                out.println("}");
                
                out.println("}");
                
                out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
                out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
                out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
                out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
                out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
                out.println(" if(((parseInt(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
                out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
                out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
                out.println("}");
                out.println("else if(((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value)))&&");
                out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
                out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
                out.println("      alert('To Date Should Less than System Date');");
                out.println("return false;"); 
                out.println("     } ");
                out.println("}");
                out.println("else{");
                out.println("      alert('To Date Should Less than System Date');");
                out.println("return false;"); 
                out.println("}");
                out.println(" }");
                out.println(" else{");
                out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
                out.println("      alert('To Date Should Less than System Date');");
                out.println("return false;"); 
                out.println("   }");
                out.println("   else{");
                out.println("   } ");
                out.println(" }");
                out.println("}");
                out.println("else{");
                out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
                out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
                out.println(" }");
                out.println(" else{");
                out.println("      alert('To Date Should Less than System Date');");
                out.println("return false;"); 
                out.println(" }");
                out.println("}");
                out.println("else{");
                out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
                out.println("    }");
                out.println("  else{");
                out.println("      alert('To Date Should Less than System Date');");
                out.println("return false;"); 
                out.println("  }");
                out.println(" }");
                out.println("}");
                //	out.println("TO_DD.focus();");
                out.println("return true;");
                out.println("}");
                out.println("}");
                
                out.println("function run_report() {");
                out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
                out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
                out.println("		m_date_to=document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value;");
                out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Income_Report?chksql=run_report&date=\"+m_date+\"&date_to=\"+m_date_to;"); 
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
                out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
                out.println("		m_date_to=document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value;");
                out.println("		clearTimeout(timerID);");
                out.println("		m_table.innerHTML=\"\";");
                out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
                out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Income_Report?chksql=print_report&date=\"+m_date+\"&date_to=\"+m_date_to;"); 
                out.println("			window.open(m_url);");
                out.println("	}");
                out.println("}");
                
                out.println("</script>"); 
                out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MISF_CAPITAL_OS_REPORT\">"); 
                
                out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_DD' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_MM' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_YY' VALUE=\"\">");
                
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
                out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Total Income Report</td>"); 
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
                out.println("<table align='center' width='100%' class='table' border='0'>"); 
                out.println("<tr class=tr_input>");
                out.println("<td width='10%'ID=VDATE>From *</td>");
                out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)  disabled > "); //disabled
                out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)  disabled >"); //disabled
                out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)  disabled >"); //disabled
                out.println("</td>");
                out.println("<td width='10%'ID=VDATE_TO>To *</td>");
                out.println("<td width='15%' ><input name=\"VAL_DAY_TO\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO)  > ");
                out.println("<input name=\"VAL_MONTH_TO\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO)  >");
                out.println("<input name=\"VAL_YEAR_TO\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO) >");
                out.println("</td>");
                out.println("<td width='*%'>");
                //out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
                out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
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
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</body>"); 
                out.println("</html>"); 
                out.flush();
            }
            
            else if(m_chksql.equals("print_report")) {
                
                String dateFrom     = req.getParameter("date");
                String dateTo       = req.getParameter("date_to");
                
                String reportDateFrom       = "";
                String reportDateTo         = "";
                String systemDate           = "";
                
                
                // Get The Dates For The Report
                sql = " " +
                    "   SELECT TO_CHAR(TO_DATE('" + dateFrom + "', 'DD-MM-YYYY'), 'dd-Month-YYYY') FROM_DATE, " +
                    "          TO_CHAR(TO_DATE('" + dateTo + "', 'DD-MM-YYYY'), 'dd-Month-YYYY') TO_DATE, " +
                    "          TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI:SS') SYSTEM_DATE " +
                    "   FROM   DUAL " +
                    " ";
                
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                
                if (resultSet.next()) {
                    reportDateFrom      = resultSet.getString("FROM_DATE");
                    reportDateTo        = resultSet.getString("TO_DATE");
                    systemDate          = resultSet.getString("SYSTEM_DATE");
                }
                
                resultSet.close();
                statement.close();
                
                
                
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Finance - Total Income Report</title>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javaScript\">");
                
                out.println("           function befor_submit() {");
                out.println("               if (confirm('Are you sure you want to save?')) {");
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_MISF_Save_Branch_Level_Acc';");
                out.println("                   document.Form1.submit();");
                out.println("               }");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" value=\"AF_MISF_BRANCH_LEVEL_ACC\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date\" value=\"" + reportDateFrom + "\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date_to\" value=\"" + reportDateTo + "\" />");
                
                out.println("           <br /><br />");
                
                out.println("           <table width=\"100%\" class=\"factoring-letter-body\">");
                out.println("               <tr>");
                out.println("                   <td align=\"center\" class=\"factoring-letter-body\"><b>Finance - Total Income for the period " + reportDateFrom + " To " + reportDateTo + "</b></td>");
                out.println("               </tr>");
                out.println("           </table>");
                
                out.println("           <table width=\"100%\" class=\"factoring-letter-body\">");
                out.println("               <tr>");
                out.println("                   <td align=\"center\" class=\"factoring-letter-body\"><b>Asset Finance</b></td>");
                out.println("               </tr>");
                out.println("           </table>");
                
                out.println("           <br />");
                
                
                
                
                int m_count_map_account = 0;
                
                sql = " " +
                    "   SELECT COUNT(*) ROW_COUNT " +
                    "   FROM   LAKDAC.WEBAC_REF_MAP_BRANCH_ACC A " +
                    "   WHERE  A.ACTYPE IS NULL " +
                    "   AND    A.ACODE1 IS NULL " +
                    "   AND    A.ACODE2 IS NULL " +
                    "   AND    A.ACODE4 IS NULL " +
                    " ";
                
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                
                if (resultSet.next()) {
                    m_count_map_account = resultSet.getInt("ROW_COUNT");
                }
                
                resultSet.close();
                statement.close();
                
                
                
                
                // Vector vectorLocationCodes = new Vector();
                // Vector vectorLocationDescriptions = new Vector();
                ArrayList arrayListLocationCodes = new ArrayList();
                // Map<String, String> mapLocations = new HashMap<String, String>();
                
                // sql = " " +
                    // "   SELECT B.LOCATION_CODE, " +
                    // "          B.LOCATION_DESC " +
                    // "   FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A, " +
                    // "          " + m_schema_name + ".AF_CO_MAS_LOCATION B " +
                    // "   WHERE  A.LOCATION_CODE = B.LOCATION_CODE " +
                    // "   GROUP BY B.LOCATION_CODE, B.LOCATION_DESC " +
                    // "   ORDER BY UPPER(B.LOCATION_DESC) " +
                    // " ";
                
                sql = " " +
                    "   SELECT A.LOCATION_CODE " +
                    "   FROM   " + m_schema_name + ".AF_CO_MAS_LOCATION A " +
                    "   WHERE  A.ACTIVE_STATUS = 'Y' " +
                    "   ORDER BY A.LOCATION_CODE " +
                    " ";
                
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    arrayListLocationCodes.add(resultSet.getString("LOCATION_CODE"));
                }
                
                resultSet.close();
                statement.close();
                
                
                out.println("   <table width=\"90%\" class=\"factoring-letter-body\">");
                out.println("       <tr>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"10%\"><b>User ID</b></td>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"1%\"><b>:</b></td>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"*%\"><b>" + m_username + "</b></td>");
                out.println("       </tr>");
                out.println("       <tr>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"10%\"><b>Date / Time</b></td>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"1%\"><b>:</b></td>");
                out.println("           <td align=\"left\" class=\"factoring-letter-body\" width=\"*%\"><b>" + systemDate + "</b></td>");
                out.println("       </tr>");
                out.println("   </table>");
                
                out.println("   <br />");
                
                out.println("   <table width=\"90%\" class=\"factoring-letter-body\">");
                if (m_count_map_account > 0) {
                    out.println("   <tr>");
                    out.println("       <td align=\"right\" class=\"factoring-letter-body\" width=\"*%\">");
                    out.println("           <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onclick=\"befor_submit();\" style=\"width: 150px;\" disabled=\"disabled\" />");
                    out.println("       </td>");
                    out.println("   </tr>");
                }
                else {
                    out.println("   <tr>");
                    out.println("       <td align=\"right\" class=\"factoring-letter-body\" width=\"*%\">");
                    out.println("           <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onclick=\"befor_submit();\" style=\"width: 150px;\" />");
                    out.println("       </td>");
                    out.println("   </tr>");
                }
                out.println("   </table>");
                
                
                sql = " " +
                    "   SELECT DISTINCT A.PRODUCT_CODE " +
                    "   FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A " +
                    " ";
                
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                
                String m_product_code = "";
                
                // Loop Through All The Products
                while (resultSet.next()) {
                    
                    m_product_code = resultSet.getString(1);
                    
                    out.println("   <table width=\"90%\" class=\"factoring-letter-body\">");
                    out.println("       <tr>");
                    out.println("           <td align=\"center\" class=\"factoring-letter-body\" width=\"*%\"><b>" + m_product_code + "</b>");
                    out.println("       </tr>");
                    out.println("   </table>");
                    
                    
                    out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    out.println("   <tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    out.println("       <td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    out.println("       <td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>"); // " + Header + "
                    for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                        out.println("       <td width='10%' align='right'><DIV class=factoring-letter-body><b>" + arrayListLocationCodes.get(index) + "</b><DIV></td>");
                    }
                    out.println("       <td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                    out.println("   </tr>");
                    
                    
                    String accountTypeCode = null;
                    String accountTypeDescription = null;
                    ArrayList arrayListLocationAmountAllocation = new ArrayList();
                    ArrayList arrayListLocationAmountTotals = new ArrayList();
                    ArrayList arrayListLocationAccountNumbers = new ArrayList();
                    BigDecimal locationAmountGrandTotal = new BigDecimal("0");
                    
                    // Initialzes Location Amount Totals For The Specific Product
                    for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                        arrayListLocationAmountTotals.add(new BigDecimal("0"));
                    }
                    
                    
                    sql = " " +
                        "   SELECT A.PRODUCT_CODE, " +
                        "          A.ACC_TYPE_CODE, " +
                        "          B.ACC_TYPE_DESC, " +
                        "          NVL(A.LOCATION_CODE, '-') LOCATION_CODE, " +
                        "          SUM(DECODE(DRCR_STATUS, 'CR', A.TRN_AMOUNT, 'DR', (-1 * A.TRN_AMOUNT))) TRN_AMOUNT " +
                        "   FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A, " +
                        "          " + m_schema_name + ".CO_FN_MAS_ACCOUNT_CODE B " +
                        "   WHERE  A.ACC_TYPE_CODE = B.ACC_TYPE_CODE " +
                        "   AND    A.PRODUCT_CODE  = '" + m_product_code + "' " +
                        "   GROUP BY A.LOCATION_CODE, " +
                        "            A.PRODUCT_CODE, " +
                        "            A.ACC_TYPE_CODE, " +
                        "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        "   ORDER BY A.PRODUCT_CODE, " +
                        "            A.ACC_TYPE_CODE, " +
                        "            A.LOCATION_CODE, " +
                        "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        " ";
                    
                    rs1 = stmt1.executeQuery(sql);
                    
                    while (rs1.next()) {
                        
                        // For Change of Account To A New Account
                        if ((accountTypeCode != null) && (!rs1.getString("ACC_TYPE_CODE").equals(accountTypeCode))) {
                            
                            out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                            out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeCode + "<div></td>");
                            out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeDescription + "<div></td>");
                            
                            BigDecimal accountAmount = null;
                            BigDecimal accountTotal = new BigDecimal("0");
                            for (int index = 0; index < arrayListLocationAmountAllocation.size(); index++) {
                                accountAmount = (BigDecimal)arrayListLocationAmountAllocation.get(index);
                                out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + nf.format(accountAmount) + "<div></td>");
                                accountTotal = accountTotal.add(accountAmount);
                            }
                            
                            out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(accountTotal) + "<div></td>");
                            out.println("</tr>");
                            
                        }
                        
                        // For New Account
                        if ((accountTypeCode == null) ||
                                ((accountTypeCode != null) && (!rs1.getString("ACC_TYPE_CODE").equals(accountTypeCode)))) {
                            
                            accountTypeCode = rs1.getString("ACC_TYPE_CODE");
                            accountTypeDescription = rs1.getString("ACC_TYPE_DESC");
                            arrayListLocationAmountAllocation = new ArrayList();
                            arrayListLocationAccountNumbers = new ArrayList();
                            
                            for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                                arrayListLocationAmountAllocation.add(new BigDecimal("0"));
                                arrayListLocationAccountNumbers.add(new String("-"));
                            }
                            
                        }
                        
                        // Set Location Amounts For The Specific Account
                        int locationIndex = arrayListLocationCodes.indexOf(rs1.getString("LOCATION_CODE"));
                        if (locationIndex > -1) {
                            arrayListLocationAmountAllocation.set(locationIndex, rs1.getBigDecimal("TRN_AMOUNT"));
                            arrayListLocationAmountTotals.set(locationIndex, (((BigDecimal)arrayListLocationAmountTotals.get(locationIndex)).add(rs1.getBigDecimal("TRN_AMOUNT"))));
                        }
                        
                    }
                    
                    
                    // For The Last Account of The Product
                    out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                    out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeCode + "<div></td>");
                    out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeDescription + "<div></td>");
                    
                    BigDecimal accountAmount = null;
                    BigDecimal accountTotal = new BigDecimal("0");
                    for (int index = 0; index < arrayListLocationAmountAllocation.size(); index++) {
                        accountAmount = (BigDecimal)arrayListLocationAmountAllocation.get(index);
                        out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + nf.format(accountAmount) + "<div></td>");
                        accountTotal = accountTotal.add(accountAmount);
                    }
                    
                    out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(accountTotal) + "<div></td>");
                    out.println("</tr>");
                    
                    
                    
                    
                    // Print The Product Total Row
                    out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                    out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">&nbsp;<div></td>");
                    out.println("   <td width=\"20%\" align=\"left\"><div class=\"factoring-letter-body\"><b>Total</b><div></td>");
                    for (int index = 0; index < arrayListLocationAmountTotals.size(); index++) {
                        out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format((BigDecimal)arrayListLocationAmountTotals.get(index)) + "</b><div></td>");
                        locationAmountGrandTotal = locationAmountGrandTotal.add((BigDecimal)arrayListLocationAmountTotals.get(index));
                    }
                    
                    out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(locationAmountGrandTotal) + "</b><div></td>");
                    out.println("</tr>");
                    
                    
                    out.println("</table>");
                    
                    
                    out.println("<br /><br />");
                    
                    
                    
                    
                    out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    out.println("   <tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    out.println("       <td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    out.println("       <td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>"); // " + Header + "
                    for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                        out.println("       <td width='10%' align='right'><DIV class=factoring-letter-body><b>" + arrayListLocationCodes.get(index) + "</b><DIV></td>");
                    }
                    // out.println("       <td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                    out.println("   </tr>");
                    
                    
                    
                    
                    sql = " " +
                        "   SELECT DISTINCT A.DIVISION_CODE, A.LOCATION_CODE, A.REF_LOCATION, " +
                        "                   A.ACC_TYPE_CODE, " +
                        "                   A.ACTYPE || '-' || A.ACODE1 || '-' || A.ACODE2 || '-' || A.ACODE3 || '-' || A.ACODE4 FULL_ACCOUNT, " +
                        "                   A.PRODUCT_CODE, " +
                        "                   B.ACC_TYPE_DESC, " +
                        "                   NVL(" + m_schema_name + ".AF_CO_GET_BRANCH_ACC_STATUS(A.DIVISION_CODE, A.LOCATION_CODE, A.ACC_TYPE_CODE, A.PRODUCT_CODE), 0) TRN_AMOUNT " +
                        "   FROM   LAKDAC.WEBAC_REF_MAP_BRANCH_ACC A, " +
                        "          " + m_schema_name + ".CO_FN_MAS_ACCOUNT_CODE B " +
                        "   WHERE  A.ACC_TYPE_CODE = B.ACC_TYPE_CODE " +
                        "   AND    A.PRODUCT_CODE = '" + m_product_code + "' " +
                        "   ORDER BY A.ACC_TYPE_CODE, " +
                        "            A.LOCATION_CODE " +
                        " ";
                    
                    rs1 = stmt1.executeQuery(sql);
                    
                    String accountNumber = null;
                    accountTypeCode = null;
                    // arrayListLocationAmountTotals = new ArrayList();
                    
                    // Initialzes Location Amount Totals For The Specific Product
                    for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                        // arrayListLocationAmountTotals.add(new BigDecimal("0"));
                    }
                    
                    boolean has_rows = false;
                    
                    while (rs1.next()) {
                        
                        has_rows = true;
                        
                        // For Change of Account To A New Account
                        if ((accountTypeCode != null) && (!rs1.getString("ACC_TYPE_CODE").equals(accountTypeCode))) {
                            
                            out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                            out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeCode + "<div></td>");
                            out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeDescription + "<div></td>");
                            
                            accountAmount = null;
                            accountNumber = null;
                            // accountTotal = new BigDecimal("0");
                            for (int index = 0; index < arrayListLocationAmountAllocation.size(); index++) {
                                accountAmount = (BigDecimal)arrayListLocationAmountAllocation.get(index);
                                accountNumber = (String)arrayListLocationAccountNumbers.get(index);
                                // out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + nf.format(accountAmount) + "<div></td>");
                                if (accountAmount.compareTo(new BigDecimal("0")) != 0) {
                                    out.println("   <td width=\"10%\" bgcolor=\"FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\"><font color=\"red\">" + accountNumber + "</font><div></td>");
                                }
                                else {
                                    out.println("   <td width=\"10%\" bgcolor=\"FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + accountNumber + "<div></td>");
                                }
                                // accountTotal = accountTotal.add(accountAmount);
                            }
                            
                            // out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(accountTotal) + "<div></td>");
                            out.println("</tr>");
                            
                        }
                        
                        // For New Account
                        if ((accountTypeCode == null) ||
                                ((accountTypeCode != null) && (!rs1.getString("ACC_TYPE_CODE").equals(accountTypeCode)))) {
                            
                            accountTypeCode = rs1.getString("ACC_TYPE_CODE");
                            accountTypeDescription = rs1.getString("ACC_TYPE_DESC");
                            arrayListLocationAmountAllocation = new ArrayList();
                            arrayListLocationAccountNumbers = new ArrayList();
                            
                            for (int index = 0; index < arrayListLocationCodes.size(); index++) {
                                arrayListLocationAmountAllocation.add(new BigDecimal("0"));
                                arrayListLocationAccountNumbers.add(new String("-"));
                            }
                            
                        }
                        
                        // Set Location Amounts For The Specific Account
                        int locationIndex = arrayListLocationCodes.indexOf(rs1.getString("LOCATION_CODE"));
                        if (locationIndex > -1) {
                            arrayListLocationAmountAllocation.set(locationIndex, rs1.getBigDecimal("TRN_AMOUNT"));
                            arrayListLocationAccountNumbers.set(locationIndex, rs1.getString("FULL_ACCOUNT"));
                            // arrayListLocationAmountTotals.set(locationIndex, (((BigDecimal)arrayListLocationAmountTotals.get(locationIndex)).add(rs1.getBigDecimal("TRN_AMOUNT"))));
                        }
                        
                    }
                    
                    
                    if (has_rows) {
                        
                        // For The Last Account of The Product
                        out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                        out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeCode + "<div></td>");
                        out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">" + accountTypeDescription + "<div></td>");
                        
                        accountAmount = null;
                        accountTotal = new BigDecimal("0");
                        for (int index = 0; index < arrayListLocationAmountAllocation.size(); index++) {
                            accountAmount = (BigDecimal)arrayListLocationAmountAllocation.get(index);
                            accountNumber = (String)arrayListLocationAccountNumbers.get(index);
                            // out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + nf.format(accountAmount) + "<div></td>");
                            if (accountAmount.compareTo(new BigDecimal("0")) != 0) {
                                out.println("   <td width=\"10%\" bgcolor=\"FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\"><font color=\"red\">" + accountNumber + "</font><div></td>");
                            }
                            else {
                                out.println("   <td width=\"10%\" bgcolor=\"FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\">" + accountNumber + "<div></td>");
                            }
                            // accountTotal = accountTotal.add(accountAmount);
                        }
                        
                        // out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(accountTotal) + "<div></td>");
                        out.println("</tr>");
                        
                        
                        
                        
                        // Print The Product Total Row
                        // out.println("<tr class=\"factoring-letter-body\" bgcolor=\"#C0C0C0\">");
                        // out.println("   <td width=\"10%\" align=\"left\"><div class=\"factoring-letter-body\">&nbsp;<div></td>");
                        // out.println("   <td width=\"20%\" align=\"left\"><div class=\"factoring-letter-body\"><b>Total</b><div></td>");
                        // for (int index = 0; index < arrayListLocationAmountTotals.size(); index++) {
                            // out.println("   <td width=\"10%\" bgcolor=\"#FFFFCC\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format((BigDecimal)arrayListLocationAmountTotals.get(index)) + "</b><div></td>");
                            // locationAmountGrandTotal = locationAmountGrandTotal.add((BigDecimal)arrayListLocationAmountTotals.get(index));
                        // }
                        
                        // out.println("   <td width=\"10%\" align=\"right\"><div class=\"factoring-letter-body\"><b>" + nf.format(locationAmountGrandTotal) + "</b><div></td>");
                        // out.println("</tr>");
                        
                    }
                    
                    
                    out.println("</table>");
                    
                    
                    out.println("<br /><br />");
                    
                }
                
                if (m_count_map_account > 0) {
                    out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    out.println("<TR><TD align='Left'  class=factoring-letter-body width='*%'><font color='#FF6600'><B>*** - Accounts Not Mapped </font> ");
                    out.println("</TD></TR>");
                    out.println("</TABLE>");
                    
                }
                else{
                    out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    out.println("<TR><TD align='Left' class=factoring-letter-body width='*%'><B>*** - Accounts Mapped ");
                    out.println("</TD></TR>");
                    out.println("</TABLE>");
                    
                }
                
                
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</BODY></HTML>");
                
            }
            
            else if(m_chksql.equals("print_report_old_2")) {
                
                String m_date       = req.getParameter("date");
                String m_date_to    = req.getParameter("date_to");
                
                String m_report_date        = "";
                String m_report_date_to     = "";
                String m_sys_date           = "";
                
                rs1 = stmt1.executeQuery(" " +
                    " SELECT TO_CHAR(TO_DATE('" + m_date + "', 'DD-MM-YYYY'), 'dd-Month-YYYY'), " +
                    "        TO_CHAR(TO_DATE('" + m_date_to + "', 'DD-MM-YYYY'), 'dd-Month-YYYY'), " +
                    "        TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI:SS') " +
                    " FROM DUAL " +
                    " ");
                
                if (rs1.next()) {
                    m_report_date       = rs1.getString(1);
                    m_report_date_to    = rs1.getString(2);
                    m_sys_date          = rs1.getString(3);
                }
                
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Finance - Total Income Report</title>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javaScript\">");
                
                out.println("           function befor_submit() {");
                out.println("               if (confirm('Are you sure you want to save?')) {");
                out.println("                   document.Form1.Hid_date.value = '" + m_report_date + "';");
                out.println("                   document.Form1.Hid_date_to.value = '" + m_report_date_to + "';");
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_MISF_Save_Branch_Level_Acc';");
                out.println("                   document.Form1.submit();");
                out.println("               }");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" value=\"AF_MISF_BRANCH_LEVEL_ACC\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date_to\" />");
                
                
                out.println("<br><br>");
                
                out.println("<TABLE  WIDTH=\"100%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"Center\" class=factoring-letter-body><B>Finance - Total Income for the period " + m_report_date + " To " + m_report_date_to + "</B></TD></TR>");
                out.println("</TABLE>");
                
                out.println("<TABLE  WIDTH=\"100%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"Center\" class=factoring-letter-body><B>Asset Finance</B></TD></TR>");
                out.println("</TABLE>");
                
                out.println("<br>");
                
                int  m_count_map_account=0;
                
                rs1= stmt1.executeQuery(
                    " SELECT COUNT(*) "+
                    " FROM   LAKDAC.WEBAC_REF_MAP_BRANCH_ACC A "+
                    " WHERE  A.ACTYPE IS NULL AND  "+
                    " A.ACODE1 IS NULL AND "+
                    " A.ACODE2 IS NULL AND "+
                    " A.ACODE4 IS NULL ");
                
                if(rs1.next()){
                    m_count_map_account=rs1.getInt(1);
                }
                
                //m_count_map_account=0;
                out.println("<TABLE  WIDTH=\"90%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"left\" class=factoring-letter-body width=\"10%\"><B>User ID</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"1%\"><B>:</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"*%\"><B>"+m_username+"</TD>");
                out.println("</TR>");
                out.println("<TR><TD align=\"left\" class=factoring-letter-body width=\"10%\"><B>Date/Time</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"1%\"><B>:</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"*%\"><B>"+m_sys_date+"</TD>");
                out.println("</TR>");
                out.println("</TABLE>");
                
                out.println("<BR>");
                
                out.println("<TABLE  WIDTH=\"90%\" class=\"factoring-letter-body\">");
                if (m_count_map_account > 0  ) {
                    out.println("<TR><TD align=\"right\" class=factoring-letter-body width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onClick=\"befor_submit()\" style=\"{width=150px}\" disabled >");
                    out.println("</TD></TR>");
                }
                else{
                    out.println("<TR><TD align=\"right\" class=factoring-letter-body width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onClick=\"befor_submit()\" style=\"{width=150px}\">");
                    out.println("</TD></TR>");
                }
                out.println("</TABLE>");
                
                
                // statement = conn.createStatement();
                // sql = " DROP TABLE " + m_schema_name + ".TEMP ";
                // statement.executeUpdate(sql);
                // statement.close();
                
                
                statement = conn.createStatement();
                
                sql = " " +
                // "   INSERT INTO " + m_schema_name + ".TEMP " +
                "       SELECT A.PRODUCT_CODE, " +
                "              B.ACC_TYPE_CODE, " +
                "              B.ACC_TYPE_DESC, " +
                "              C.LOCATION_CODE, " +
                "              C.LOCATION_DESC, " +
                "              GROUPING(A.PRODUCT_CODE) G_PRODUCT_CODE, " +
                "              GROUPING(B.ACC_TYPE_CODE) G_ACC_TYPE_CODE, " +
                "              GROUPING(C.LOCATION_CODE) G_LOCATION_CODE, " +
                "              GROUPING_ID (A.PRODUCT_CODE, B.ACC_TYPE_CODE, C.LOCATION_CODE) G_ID, " +
                "              SUM(DECODE(DRCR_STATUS, 'CR', A.TRN_AMOUNT, 'DR', (-1 * A.TRN_AMOUNT))) TRN_AMOUNT " +
                "       FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A, " +
                "              " + m_schema_name + ".CO_FN_MAS_ACCOUNT_CODE B, " +
                "              " + m_schema_name + ".AF_CO_MAS_LOCATION C " +
                "       WHERE  A.ACC_TYPE_CODE = B.ACC_TYPE_CODE " +
                "       AND    A.LOCATION_CODE = C.LOCATION_CODE " +
                "       GROUP BY CUBE(A.PRODUCT_CODE, (B.ACC_TYPE_CODE, B.ACC_TYPE_DESC), (C.LOCATION_CODE, C.LOCATION_DESC)) " +
                "       ORDER BY A.PRODUCT_CODE, B.ACC_TYPE_CODE, C.LOCATION_CODE " +
                " ";
                
                // statement.executeUpdate(sql);
                // statement.close();
                
                
                // statement = conn.createStatement();
                
                // sql = " " +
                // "   SELECT A.* " +
                // "   FROM   " + m_schema_name + ".TEMP A, " +
                // "   ORDER BY A.PRODUCT_CODE, B.ACC_TYPE_CODE, C.LOCATION_CODE " +
                // " ";
                
                resultSet = statement.executeQuery(sql);
                
                String productCode = null;
                String accountCode = null;
                
                // out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>");//"+Header+"
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                // out.println("</tr>");
                
                while (resultSet.next()) {
                    
                    // New Product
                    if ((productCode == null) || 
                            ((productCode != null) && (resultSet.getString("PRODUCT_CODE") != null) && (!resultSet.getString("PRODUCT_CODE").equals(productCode)))) {
                        
                        productCode = resultSet.getString("PRODUCT_CODE");
                        accountCode = null;
                        
                        // Header Table
                        out.println("<table  width='90%' class='factoring-letter-body'>");
                        out.println("<tr><td align='center' class=factoring-letter-body width='*%'><B>"+productCode+"");
                        out.println("</td></tr>");
                        out.println("</table>");
                        
                        
                        out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                        out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                        out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>");//"+Header+"
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>delanjali location 1<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>BM<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Galle<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Head Office<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>KANDY1<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>KURUNEGALA<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kadawatha<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara <DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Office<DIV></td>");
                        out.println("</tr>");
                        
                    }
                    
                    // New Account Within A Product
                    if ((accountCode == null) || 
                            ((accountCode != null) && (resultSet.getString("ACC_TYPE_CODE") != null) && (!resultSet.getString("ACC_TYPE_CODE").equals(accountCode)))) {
                        
                        accountCode = resultSet.getString("ACC_TYPE_CODE");
                        
                        
                        // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                        // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>");//"+Header+"
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                        
                        out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>"+accountCode+"<DIV></td>");
                        out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>"+resultSet.getString("ACC_TYPE_DESC")+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>0<DIV></td>");
                        
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kandy)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kurunegala)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_gampaha)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_matara)+"<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_total_charge)+"<DIV></td>");
                        out.println("</tr>");
                        
                    }
                    
                }
                
                out.println("</table>");
                
                
                // rs2 = stmt2.executeQuery("SELECT DISTINCT A.PRODUCT_CODE  "+
                    // " FROM "+m_schema_name+".AF_CO_TBD_TOTAL_INCOME A ");
                
                // boolean more_product=rs2.next();
                // String m_product_code="";
                // while(more_product){
                    
                    // m_product_code=rs2.getString(1);
                    // out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    // out.println("<TR><TD align='center' class=factoring-letter-body width='*%'><B>"+rs2.getString(1)+"");
                    // out.println("</TD></TR>");
                    // out.println("</TABLE>");
                    
                    
                    // sql = " " +
                        // "   SELECT NVL(A.LOCATION_CODE, '-') LOCATION_CODE, " +
                        // "          A.ACC_TYPE_CODE, " +
                        // "          SUM(DECODE(DRCR_STATUS, 'CR', A.TRN_AMOUNT, 'DR', (-1 * A.TRN_AMOUNT))) TRN_AMOUNT, " +
                        // "          B.ACC_TYPE_DESC, " +
                        // "          A.PRODUCT_CODE " +
                        // "   FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A, " +
                        // "          " + m_schema_name + ".CO_FN_MAS_ACCOUNT_CODE B " +
                        // "   WHERE  A.ACC_TYPE_CODE = B.ACC_TYPE_CODE " +
                        // "   AND    A.PRODUCT_CODE  = '" + m_product_code + "' " +
                        // "   GROUP BY A.LOCATION_CODE, " +
                        // "            A.PRODUCT_CODE, " +
                        // "            A.ACC_TYPE_CODE, " +
                        // "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        // "   ORDER BY A.PRODUCT_CODE, " +
                        // "            A.ACC_TYPE_CODE, " +
                        // "            A.LOCATION_CODE, " +
                        // "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        // " ";
                    
                    // rs1 = stmt1.executeQuery(sql);
                    
                    // boolean more_income=rs1.next();
                    // String m_sub_charge="";
                    // String m_sub_charge_desc="";
                    
                    // double m_charge_colombo=0;
                    // double m_charge_kandy=0;
                    // double m_charge_kurunegala=0;
                    // double m_charge_gampaha=0;
                    // double m_charge_matara=0;
                    
                    // double m_total_charge=0;
                    // double m_gross=0;
                    
                    // double m_charge_colombo_tot=0;
                    // double m_charge_kandy_tot=0;
                    // double m_charge_kurunegala_tot=0;
                    // double m_charge_gampaha_tot=0;
                    // double m_charge_matara_tot=0;
                    
                    
                    
                    // out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>");//"+Header+"
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                    // out.println("</tr>");
                    
                    // while(more_income){
                        
                        // m_sub_charge=rs1.getString(2);
                        // m_sub_charge_desc=rs1.getString(4);
                        // m_charge_colombo=0;
                        // m_charge_kurunegala=0;
                        // m_charge_kandy=0;
                        // m_total_charge=0;
                        // m_charge_gampaha=0;
                        // m_charge_matara=0;
                        // while(m_sub_charge.trim().equals(rs1.getString(2))){
                            
                            // if (rs1.getString(1).equals("COLOMBO")){
                                // m_charge_colombo+=rs1.getDouble(3);
                                // m_charge_colombo_tot+=rs1.getDouble(3);
                            // }
                            // else if (rs1.getString(1).equals("KANDY")){
                                // m_charge_kandy+=rs1.getDouble(3);
                                // m_charge_kandy_tot+=rs1.getDouble(3);
                            // }
                            // else if (rs1.getString(1).equals("KURUNEGALA")){
                                // m_charge_kurunegala+=rs1.getDouble(3);
                                // m_charge_kurunegala_tot+=rs1.getDouble(3);
                            // }
                            // else if (rs1.getString(1).equals("GAMPAHA")){
                                // m_charge_gampaha+=rs1.getDouble(3);
                                // m_charge_gampaha_tot+=rs1.getDouble(3);
                            // }
                            // else if (rs1.getString(1).equals("MATARA")){
                                // m_charge_matara+=rs1.getDouble(3);
                                // m_charge_matara_tot+=rs1.getDouble(3);
                            // }
                            // else {
                                // m_charge_matara+=rs1.getDouble(3);
                                // m_charge_matara_tot+=rs1.getDouble(3);
                            // }
                            
                            // more_income=rs1.next();
                            // if(!more_income){
                                // break;
                            // }
                            
                        // }
                        // m_total_charge=m_charge_colombo+m_charge_kandy+m_charge_kurunegala+m_charge_gampaha+m_charge_matara;
                        // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>"+m_sub_charge+"<DIV></td>");
                        // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>"+m_sub_charge_desc+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_colombo)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kandy)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kurunegala)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_gampaha)+"<DIV></td>");
                        // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_matara)+"<DIV></td>");
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_total_charge)+"<DIV></td>");
                        // out.println("</tr>");
                        
                    // }
                    
                    // m_gross=m_charge_colombo_tot+m_charge_kandy_tot+m_charge_kurunegala_tot+m_charge_gampaha_tot+m_charge_matara_tot;
                    // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>&nbsp;<DIV></td>");
                    // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>Total<DIV></td>");
                    // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_colombo_tot)+"<DIV></td>");
                    // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_kandy_tot)+"<DIV></td>");
                    // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_kurunegala_tot)+"<DIV></td>");
                    // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_gampaha_tot)+"<DIV></td>");
                    // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_matara_tot)+"<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_gross)+"<DIV></td>");
                    // out.println("</tr>");
                    
                    
                    // out.println("</table>");
                    
                    // rs1= stmt1.executeQuery(
                        // " SELECT DISTINCT A.DIVISION_CODE,A.LOCATION_CODE, A.REF_LOCATION, "+
                        // " A.ACC_TYPE_CODE,"+
                        // " A.ACTYPE||'-'||A.ACODE1||'-'|| A.ACODE2||'-'|| A.ACODE3||'-'||A.ACODE4  "+
                        // " ,A.PRODUCT_CODE "+
                        // " ,B.ACC_TYPE_DESC  "+
                        // " ,"+m_schema_name+".AF_CO_GET_BRANCH_ACC_STATUS(A.DIVISION_CODE,A.LOCATION_CODE,A.ACC_TYPE_CODE,A.PRODUCT_CODE) "+
                        // " FROM OFSCLAC.WEBAC_REF_MAP_BRANCH_ACC A , "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
                        // " WHERE A.ACC_TYPE_CODE  = B.ACC_TYPE_CODE "+
                        // " AND   A.PRODUCT_CODE='"+m_product_code+"' "+
                        // " ORDER BY ACC_TYPE_CODE ");
                    
                    // out.println("<br><br>");
                    
                    // out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Map<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                    // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><DIV></td>");
                    // out.println("</tr>");
                    
                    // String m_acc_colombo="";
                    // String m_acc_kandy="";
                    // String m_acc_kurunegala="";
                    // String m_acc_gampaha="";
                    // String m_acc_matara="";
                    // String m_account_code="";
                    // String m_account_desc="";
                    
                    // double m_acc_colombo_amt=0;
                    // double m_acc_kandy_amt=0;
                    // double m_acc_kurunegala_amt=0;
                    // double m_acc_gampaha_amt=0;
                    // double m_acc_matara_amt=0;
                    
                    // boolean more_acc=rs1.next();
                    
                    // while(more_acc){
                        
                        // m_account_code=rs1.getString(4);
                        // m_account_desc=rs1.getString(7);
                        // m_acc_colombo="";
                        // m_acc_kandy="";
                        // m_acc_kurunegala="";
                        // m_acc_gampaha="";
                        // m_acc_matara="";
                        
                        // m_acc_colombo_amt=0;
                        // m_acc_kandy_amt=0;
                        // m_acc_kurunegala_amt=0;
                        // m_acc_gampaha_amt=0;
                        // m_acc_matara_amt=0;
                        
                        // while(m_account_code.trim().equals(rs1.getString(4))){
                            
                            // if (rs1.getString(2).equals("COLOMBO")){
                                // m_acc_colombo=rs1.getString(5);
                                // m_acc_colombo_amt=rs1.getDouble(8);
                            // }
                            // else if (rs1.getString(2).equals("KANDY")){
                                // m_acc_kandy=rs1.getString(5);
                                // m_acc_kandy_amt=rs1.getDouble(8);
                            // }
                            // else if (rs1.getString(2).equals("KURUNEGALA")){
                                // m_acc_kurunegala=rs1.getString(5);
                                // m_acc_kurunegala_amt=rs1.getDouble(8);
                            // }
                            // else if (rs1.getString(2).equals("GAMPAHA")){
                                // m_acc_gampaha=rs1.getString(5);
                                // m_acc_gampaha_amt=rs1.getDouble(8);
                            // }
                            // else if (rs1.getString(2).equals("MATARA")){
                                // m_acc_matara=rs1.getString(5);
                                // m_acc_matara_amt=rs1.getDouble(8);
                            // }
                            
                            // more_acc=rs1.next();
                            // if(!more_acc){
                                // break;
                            // }
                            
                        // }
                        
                        // out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        // out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>"+m_account_code+"<DIV></td>");
                        // out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>"+m_account_desc+"<DIV></td>");
                        // if (m_acc_colombo_amt > 0 ) {
                            // out.println("<td width='10%' bgcolor='#FF6600'  align='right'><DIV class=factoring-letter-body>"+m_acc_colombo+"<DIV></td>");
                        // }else{
                            // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_colombo+"<DIV></td>");
                        // }
                        // if (m_acc_kandy_amt > 0 ) {
                            // out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_kandy+"<DIV></td>");
                        // }else{
                            // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_kandy+"<DIV></td>");
                        // }
                        // if (m_acc_kurunegala_amt > 0 ) {
                            // out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_kurunegala+"<DIV></td>");
                        // }else{
                            // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_kurunegala+"<DIV></td>");
                        // }
                        // if (m_acc_gampaha_amt > 0 ) {
                            // out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_gampaha+"<DIV></td>");
                        // }else{
                            // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_gampaha+"<DIV></td>");
                        // }
                        // if (m_acc_matara_amt > 0 ) {
                            // out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_matara+"<DIV></td>");
                        // }else{
                            // out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_matara+"<DIV></td>");
                        // }
                        // out.println("<td width='10%' align='right'><DIV class=factoring-letter-body>-<DIV></td>");
                        // out.println("</tr>");
                    // }
                    // out.println("</table>");
                    
                    // more_product=rs2.next();
                    
                    
                    // out.println("<br><br>");
                // }
                
                // if (m_count_map_account>0 ) {
                    // out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    // out.println("<TR><TD align='Left'  class=factoring-letter-body width='*%'><font color='#FF6600'><B>*** - Accounts Not Mapped </font> ");
                    // out.println("</TD></TR>");
                    // out.println("</TABLE>");
                    
                // }
                // else{
                    // out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    // out.println("<TR><TD align='Left' class=factoring-letter-body width='*%'><B>*** - Accounts Mapped ");
                    // out.println("</TD></TR>");
                    // out.println("</TABLE>");
                    
                // }
                
                
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</BODY></HTML>");
                
            }
            
            else if(m_chksql.equals("print_report_old")) {
                
                String m_date       = req.getParameter("date");
                String m_date_to    = req.getParameter("date_to");
                
                String m_report_date        = "";
                String m_report_date_to     = "";
                String m_sys_date           = "";
                
                rs1 = stmt1.executeQuery(" " +
                    " SELECT TO_CHAR(TO_DATE('" + m_date + "', 'DD-MM-YYYY'), 'dd-Month-YYYY'), " +
                    "        TO_CHAR(TO_DATE('" + m_date_to + "', 'DD-MM-YYYY'), 'dd-Month-YYYY'), " +
                    "        TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI:SS') " +
                    " FROM DUAL " +
                    " ");
                
                if (rs1.next()) {
                    m_report_date       = rs1.getString(1);
                    m_report_date_to    = rs1.getString(2);
                    m_sys_date          = rs1.getString(3);
                }
                
                
                // if(m_report_type.equals("ALL")) {
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Finance - Total Income Report</title>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javaScript\">");
                
                out.println("           function befor_submit() {");
                out.println("               if (confirm('Are you sure you want to save?')) {");
                out.println("                   document.Form1.Hid_date.value = '" + m_report_date + "';");
                out.println("                   document.Form1.Hid_date_to.value = '" + m_report_date_to + "';");
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_MISF_Save_Branch_Level_Acc';");
                out.println("                   document.Form1.submit();");
                out.println("               }");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" value=\"AF_MISF_BRANCH_LEVEL_ACC\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_date_to\" />");
                
                
                out.println("<br><br>");
                
                out.println("<TABLE  WIDTH=\"100%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"Center\" class=factoring-letter-body><B>Finance - Total Income for the period " + m_report_date + " To " + m_report_date_to + "</B></TD></TR>");
                out.println("</TABLE>");
                
                out.println("<TABLE  WIDTH=\"100%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"Center\" class=factoring-letter-body><B>Asset Finance</B></TD></TR>");
                out.println("</TABLE>");
                
                out.println("<br>");
                
                /*rs1= stmt1.executeQuery("SELECT DISTINCT A.LOCATION_CODE "+
                    " FROM "+m_schema_name+".AF_CO_TBD_TOTAL_INCOME A ");
                    String Header="";
                    
                    
                    while(rs1.next()){
                    Header+="<td width=\"10%\" align=\"right\"><DIV class=factoring-letter-body><b>"+rs1.getString(1)+"<DIV></td>";
                    }
                    
                    */
                int  m_count_map_account=0;
                
                rs1= stmt1.executeQuery(
                    " SELECT COUNT(*) "+
                    " FROM   LAKDAC.WEBAC_REF_MAP_BRANCH_ACC A "+
                    " WHERE  A.ACTYPE IS NULL AND  "+
                    " A.ACODE1 IS NULL AND "+
                    " A.ACODE2 IS NULL AND "+
                    " A.ACODE4 IS NULL ");
                
                if(rs1.next()){
                    m_count_map_account=rs1.getInt(1);
                }
                
                //m_count_map_account=0;
                out.println("<TABLE  WIDTH=\"90%\" class=\"factoring-letter-body\">");
                out.println("<TR><TD align=\"left\" class=factoring-letter-body width=\"10%\"><B>User ID</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"1%\"><B>:</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"*%\"><B>"+m_username+"</TD>");
                out.println("</TR>");
                out.println("<TR><TD align=\"left\" class=factoring-letter-body width=\"10%\"><B>Date/Time</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"1%\"><B>:</TD>");
                out.println("<TD align=\"left\" class=factoring-letter-body width=\"*%\"><B>"+m_sys_date+"</TD>");
                out.println("</TR>");
                out.println("</TABLE>");
                
                out.println("<BR>");
                
                out.println("<TABLE  WIDTH=\"90%\" class=\"factoring-letter-body\">");
                if (m_count_map_account > 0  ) {
                    out.println("<TR><TD align=\"right\" class=factoring-letter-body width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onClick=\"befor_submit()\" style=\"{width=150px}\" disabled >");
                    out.println("</TD></TR>");
                }
                else{
                    out.println("<TR><TD align=\"right\" class=factoring-letter-body width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Run Journal\" onClick=\"befor_submit()\" style=\"{width=150px}\">");
                    out.println("</TD></TR>");
                }
                out.println("</TABLE>");
                
                
                rs2= stmt2.executeQuery("SELECT DISTINCT A.PRODUCT_CODE  "+
                    " FROM "+m_schema_name+".AF_CO_TBD_TOTAL_INCOME A ");
                
                boolean more_product=rs2.next();
                String m_product_code="";
                while(more_product){
                    
                    m_product_code=rs2.getString(1);
                    out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    out.println("<TR><TD align='center' class=factoring-letter-body width='*%'><B>"+rs2.getString(1)+"");
                    out.println("</TD></TR>");
                    out.println("</TABLE>");
                    
                    
                    sql = " " +
                        "   SELECT NVL(A.LOCATION_CODE, '-') LOCATION_CODE, " +
                        "          A.ACC_TYPE_CODE, " +
                        "          SUM(DECODE(DRCR_STATUS, 'CR', A.TRN_AMOUNT, 'DR', (-1 * A.TRN_AMOUNT))) TRN_AMOUNT, " +
                        "          B.ACC_TYPE_DESC, " +
                        "          A.PRODUCT_CODE " +
                        "   FROM   " + m_schema_name + ".AF_CO_TBD_TOTAL_INCOME A, " +
                        "          " + m_schema_name + ".CO_FN_MAS_ACCOUNT_CODE B " +
                        "   WHERE  A.ACC_TYPE_CODE = B.ACC_TYPE_CODE " +
                        "   AND    A.PRODUCT_CODE  = '" + m_product_code + "' " +
                        "   GROUP BY A.LOCATION_CODE, " +
                        "            A.PRODUCT_CODE, " +
                        "            A.ACC_TYPE_CODE, " +
                        "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        "   ORDER BY A.PRODUCT_CODE, " +
                        "            A.ACC_TYPE_CODE, " +
                        "            A.LOCATION_CODE, " +
                        "            B.ACC_TYPE_DESC " + // ,A.PROC_DESC
                        " ";
                    
                    rs1 = stmt1.executeQuery(sql);
                    
                    boolean more_income=rs1.next();
                    String m_sub_charge="";
                    String m_sub_charge_desc="";
                    
                    double m_charge_colombo=0;
                    double m_charge_kandy=0;
                    double m_charge_kurunegala=0;
                    double m_charge_gampaha=0;
                    double m_charge_matara=0;
                    
                    double m_total_charge=0;
                    double m_gross=0;
                    
                    double m_charge_colombo_tot=0;
                    double m_charge_kandy_tot=0;
                    double m_charge_kurunegala_tot=0;
                    double m_charge_gampaha_tot=0;
                    double m_charge_matara_tot=0;
                    
                    
                    
                    out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Branch<DIV></td>");//"+Header+"
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Total<DIV></td>");
                    out.println("</tr>");
                    
                    while(more_income){
                        
                        m_sub_charge=rs1.getString(2);
                        m_sub_charge_desc=rs1.getString(4);
                        m_charge_colombo=0;
                        m_charge_kurunegala=0;
                        m_charge_kandy=0;
                        m_total_charge=0;
                        m_charge_gampaha=0;
                        m_charge_matara=0;
                        while(m_sub_charge.trim().equals(rs1.getString(2))){
                            
                            if (rs1.getString(1).equals("COLOMBO")){
                                m_charge_colombo+=rs1.getDouble(3);
                                m_charge_colombo_tot+=rs1.getDouble(3);
                            }
                            else if (rs1.getString(1).equals("KANDY")){
                                m_charge_kandy+=rs1.getDouble(3);
                                m_charge_kandy_tot+=rs1.getDouble(3);
                            }
                            else if (rs1.getString(1).equals("KURUNEGALA")){
                                m_charge_kurunegala+=rs1.getDouble(3);
                                m_charge_kurunegala_tot+=rs1.getDouble(3);
                            }
                            else if (rs1.getString(1).equals("GAMPAHA")){
                                m_charge_gampaha+=rs1.getDouble(3);
                                m_charge_gampaha_tot+=rs1.getDouble(3);
                            }
                            else if (rs1.getString(1).equals("MATARA")){
                                m_charge_matara+=rs1.getDouble(3);
                                m_charge_matara_tot+=rs1.getDouble(3);
                            }
                            else {
                                m_charge_matara+=rs1.getDouble(3);
                                m_charge_matara_tot+=rs1.getDouble(3);
                            }
                            
                            more_income=rs1.next();
                            if(!more_income){
                                break;
                            }
                            
                        }
                        m_total_charge=m_charge_colombo+m_charge_kandy+m_charge_kurunegala+m_charge_gampaha+m_charge_matara;
                        out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>"+m_sub_charge+"<DIV></td>");
                        out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>"+m_sub_charge_desc+"<DIV></td>");
                        out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_colombo)+"<DIV></td>");
                        out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kandy)+"<DIV></td>");
                        out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_kurunegala)+"<DIV></td>");
                        out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_gampaha)+"<DIV></td>");
                        out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+nf.format(m_charge_matara)+"<DIV></td>");
                        out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_total_charge)+"<DIV></td>");
                        out.println("</tr>");
                        
                    }
                    
                    m_gross=m_charge_colombo_tot+m_charge_kandy_tot+m_charge_kurunegala_tot+m_charge_gampaha_tot+m_charge_matara_tot;
                    out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>&nbsp;<DIV></td>");
                    out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>Total<DIV></td>");
                    out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_colombo_tot)+"<DIV></td>");
                    out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_kandy_tot)+"<DIV></td>");
                    out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_kurunegala_tot)+"<DIV></td>");
                    out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_gampaha_tot)+"<DIV></td>");
                    out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_charge_matara_tot)+"<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>"+nf.format(m_gross)+"<DIV></td>");
                    out.println("</tr>");
                    
                    
                    out.println("</table>");
                    
                    rs1= stmt1.executeQuery(
                        " SELECT DISTINCT A.DIVISION_CODE,A.LOCATION_CODE, A.REF_LOCATION, "+
                        " A.ACC_TYPE_CODE,"+
                        " A.ACTYPE||'-'||A.ACODE1||'-'|| A.ACODE2||'-'|| A.ACODE3||'-'||A.ACODE4  "+
                        " ,A.PRODUCT_CODE "+
                        " ,B.ACC_TYPE_DESC  "+
                        " ,"+m_schema_name+".AF_CO_GET_BRANCH_ACC_STATUS(A.DIVISION_CODE,A.LOCATION_CODE,A.ACC_TYPE_CODE,A.PRODUCT_CODE) "+
                        " FROM LAKDAC.WEBAC_REF_MAP_BRANCH_ACC A , "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
                        " WHERE A.ACC_TYPE_CODE  = B.ACC_TYPE_CODE "+
                        " AND   A.PRODUCT_CODE='"+m_product_code+"' "+
                        " ORDER BY ACC_TYPE_CODE ");
                    
                    out.println("<br><br>");
                    
                    out.println("<table width='90%' class='table' border='1'  align='center' cellspacing='1' cellspacing='1' >");
                    out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                    out.println("<td width='10%' align='left'><DIV class=factoring-letter-body><b>Account Code<DIV></td>");
                    out.println("<td width='20%' align='left'><DIV class=factoring-letter-body><b>Charge/Map<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Colombo<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kandy<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Kurunegala<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Gampaha<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><b>Matara<DIV></td>");
                    out.println("<td width='10%' align='right'><DIV class=factoring-letter-body><DIV></td>");
                    out.println("</tr>");
                    
                    String m_acc_colombo="";
                    String m_acc_kandy="";
                    String m_acc_kurunegala="";
                    String m_acc_gampaha="";
                    String m_acc_matara="";
                    String m_account_code="";
                    String m_account_desc="";
                    
                    double m_acc_colombo_amt=0;
                    double m_acc_kandy_amt=0;
                    double m_acc_kurunegala_amt=0;
                    double m_acc_gampaha_amt=0;
                    double m_acc_matara_amt=0;
                    
                    boolean more_acc=rs1.next();
                    
                    while(more_acc){
                        
                        m_account_code=rs1.getString(4);
                        m_account_desc=rs1.getString(7);
                        m_acc_colombo="";
                        m_acc_kandy="";
                        m_acc_kurunegala="";
                        m_acc_gampaha="";
                        m_acc_matara="";
                        
                        m_acc_colombo_amt=0;
                        m_acc_kandy_amt=0;
                        m_acc_kurunegala_amt=0;
                        m_acc_gampaha_amt=0;
                        m_acc_matara_amt=0;
                        
                        while(m_account_code.trim().equals(rs1.getString(4))){
                            
                            if (rs1.getString(2).equals("COLOMBO")){
                                m_acc_colombo=rs1.getString(5);
                                m_acc_colombo_amt=rs1.getDouble(8);
                            }
                            else if (rs1.getString(2).equals("KANDY")){
                                m_acc_kandy=rs1.getString(5);
                                m_acc_kandy_amt=rs1.getDouble(8);
                            }
                            else if (rs1.getString(2).equals("KURUNEGALA")){
                                m_acc_kurunegala=rs1.getString(5);
                                m_acc_kurunegala_amt=rs1.getDouble(8);
                            }
                            else if (rs1.getString(2).equals("GAMPAHA")){
                                m_acc_gampaha=rs1.getString(5);
                                m_acc_gampaha_amt=rs1.getDouble(8);
                            }
                            else if (rs1.getString(2).equals("MATARA")){
                                m_acc_matara=rs1.getString(5);
                                m_acc_matara_amt=rs1.getDouble(8);
                            }
                            
                            more_acc=rs1.next();
                            if(!more_acc){
                                break;
                            }
                            
                        }
                        
                        out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
                        out.println("<td width='10%' align='left'><DIV class=factoring-letter-body>"+m_account_code+"<DIV></td>");
                        out.println("<td width='20%' align='left'><DIV class=factoring-letter-body>"+m_account_desc+"<DIV></td>");
                        if (m_acc_colombo_amt > 0 ) {
                            out.println("<td width='10%' bgcolor='#FF6600'  align='right'><DIV class=factoring-letter-body>"+m_acc_colombo+"<DIV></td>");
                        }else{
                            out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_colombo+"<DIV></td>");
                        }
                        if (m_acc_kandy_amt > 0 ) {
                            out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_kandy+"<DIV></td>");
                        }else{
                            out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_kandy+"<DIV></td>");
                        }
                        if (m_acc_kurunegala_amt > 0 ) {
                            out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_kurunegala+"<DIV></td>");
                        }else{
                            out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_kurunegala+"<DIV></td>");
                        }
                        if (m_acc_gampaha_amt > 0 ) {
                            out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_gampaha+"<DIV></td>");
                        }else{
                            out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_gampaha+"<DIV></td>");
                        }
                        if (m_acc_matara_amt > 0 ) {
                            out.println("<td width='10%' bgcolor='#FF6600' align='right'><DIV class=factoring-letter-body>"+m_acc_matara+"<DIV></td>");
                        }else{
                            out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV class=factoring-letter-body>"+m_acc_matara+"<DIV></td>");
                        }
                        out.println("<td width='10%' align='right'><DIV class=factoring-letter-body>-<DIV></td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    
                    more_product=rs2.next();
                    
                    
                    out.println("<br><br>");
                }
                
                if (m_count_map_account>0 ) {
                    out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    out.println("<TR><TD align='Left'  class=factoring-letter-body width='*%'><font color='#FF6600'><B>*** - Accounts Not Mapped </font> ");
                    out.println("</TD></TR>");
                    out.println("</TABLE>");
                    
                }
                else{
                    out.println("<TABLE  WIDTH='90%' class='factoring-letter-body'>");
                    out.println("<TR><TD align='Left' class=factoring-letter-body width='*%'><B>*** - Accounts Mapped ");
                    out.println("</TD></TR>");
                    out.println("</TABLE>");
                    
                }
                
                
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</BODY></HTML>");
                
            }
            
            else if(m_chksql.equals("drill_down_transaction_tot")){
                
                out.println("<HTML><HEAD><TITLE> Capital Balance OutStanding </TITLE></HEAD>");
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
                out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
                out.println("<TR><TD><CENTER><B> Capital Balance OutStanding </B></TD></TR>");
                out.println("</TABLE>");
                out.println("<BR><BR>");
                
                
                String Sql_invoice=" SELECT a.application_no,"+ //1
                    " a.finance_no, "+ //2
                    " a.client_code, "+ //3
                    " a.client_full_name, "+ //4
                    " a.capital_outstanding, "+ //5
                    " a.ent_user, "+ //6
                    " a.ent_date,  "+ //7
                    " a.division_code,  "+ //8
                    " a.branch_code,  "+ //9
                    " a.future_receivable, "+ //10
                    " a.arr_capital_portion ,"+ //11
                    " NVL(a.future_receivable,0) +   NVL(a.arr_capital_portion,0) ,"+ //12
                    " NVL(a.interest,0)"+ //13
                    " FROM "+m_schema_name+".af_re_tbd_app_capital_os a "+
                    " where a.ent_user='"+m_username+"' ";
                
                
                rs=stmt1.executeQuery(Sql_invoice);
                boolean  more_inv =rs.next();
                double sum=0,sum_2=0,sum_3=0,sum_int=0;
                int i=1;
                out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td width='1%' class=div_input>No</td>");
                out.println("<td width='10%' class=div_input>Finance No</td>");
                out.println("<td width='25%' class=div_input>Client Name</td>");
                out.println("<td width='10%' align='right' class=div_input>Arrears Capital</td>");
                out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
                out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
                out.println("<td width='10%' align='right' class=div_input>Income</td>");
                
                out.println("</tr>");
                
                while(more_inv){
                    out.println("<tr bgcolor=\"#FCEBC5\" >");
                    out.println("<td width='1%' class=div_input>"+i+"</td>");
                    out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
                    out.println("<td width='25%' class=div_input style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4)+"</u></td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
                    
                    
                    out.println("</tr>");
                    sum+=rs.getDouble(12);
                    sum_2+=rs.getDouble(11);
                    sum_3+=rs.getDouble(10);
                    sum_int+=rs.getDouble(13);
                    
                    more_inv =rs.next();
                    i=i+1;
                }
                
                out.println("<tr >");
                out.println("<td width='1%' class=div_input>&nbsp;</td>");
                out.println("<td width='10%' class=div_input><b>Total</td>");
                out.println("<td width='15%' class=div_input>&nbsp;</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_3)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_int)+"</td>");
                
                out.println("</tr>");
                
                
                
                out.println("</table>");
                out.println("</body>");
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</html>");
                
                
            }
            
            else if(m_chksql.equals("drill_down_transaction")){
                String m_branch_code=req.getParameter("branch_code");		
                String m_transaction_type=req.getParameter("transaction_type");		
                String m_division_code=req.getParameter("division_code");		
                out.println("<HTML><HEAD><TITLE> Capital Balance OutStanding </TITLE></HEAD>");
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
                out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
                out.println("<TR><TD><CENTER><B> Capital Balance OutStanding </B></TD></TR>");
                out.println("</TABLE>");
                out.println("<BR><BR>");
                
                
                String Sql_invoice=" SELECT a.application_no,"+ //1
                    " a.finance_no, "+ //2
                    " a.client_code, "+ //3
                    " a.client_full_name, "+ //4
                    " a.capital_outstanding, "+ //5
                    " a.ent_user, "+ //6
                    " a.ent_date,  "+ //7
                    " a.division_code,  "+ //8
                    " a.branch_code,  "+ //9
                    " a.future_receivable, "+ //10
                    " a.arr_capital_portion ,"+ //11
                    " NVL(a.future_receivable,0) +   NVL(a.arr_capital_portion,0) ,"+ //12
                    " NVL(a.interest,0)"+ //13
                    " FROM "+m_schema_name+".af_re_tbd_app_capital_os a "+
                    " where a.branch_code='"+m_branch_code+"' "+
                    " and a.division_code='"+m_division_code+"' "+
                    " and a.ent_user='"+m_username+"' "+
                    " and a.transaction_type='"+m_transaction_type+"' ";
                
                rs=stmt1.executeQuery(Sql_invoice);
                boolean  more_inv =rs.next();
                double sum=0,sum_2=0,sum_3=0,sum_int=0;
                int i=1;
                out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td width='1%' class=div_input>No</td>");
                out.println("<td width='10%' class=div_input>Finance No</td>");
                out.println("<td width='25%' class=div_input>Client Name</td>");
                out.println("<td width='10%' align='right' class=div_input>Arrears Capital</td>");
                out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
                out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
                out.println("<td width='10%' align='right' class=div_input>Income</td>");
                
                out.println("</tr>");
                
                while(more_inv){
                    out.println("<tr bgcolor=\"#FCEBC5\" >");
                    out.println("<td width='1%' class=div_input>"+i+"</td>");
                    out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
                    out.println("<td width='25%' class=div_input style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4)+"</u></td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
                    out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
                    
                    
                    out.println("</tr>");
                    sum+=rs.getDouble(12);
                    sum_2+=rs.getDouble(11);
                    sum_3+=rs.getDouble(10);
                    sum_int+=rs.getDouble(13);
                    
                    more_inv =rs.next();
                    i=i+1;
                }
                
                out.println("<tr >");
                out.println("<td width='1%' class=div_input>&nbsp;</td>");
                out.println("<td width='10%' class=div_input><b>Total</td>");
                out.println("<td width='15%' class=div_input>&nbsp;</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_3)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
                out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_int)+"</td>");
                
                out.println("</tr>");
                
                
                
                out.println("</table>");
                out.println("</body>");
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</html>");
                
                
            }
            
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try{out.println("Error:"+ex.toString());}catch(Exception e){}
        }
        finally{
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
    }
}
