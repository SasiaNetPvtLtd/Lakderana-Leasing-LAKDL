 
//Created by Nuwan De Silva
//Collection - Due Reports

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Due_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_val;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username = con_method.username;
			String m_fschema_name=con_method.client_name.trim();
			String m_sys_date="";
			
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql         = req.getParameter("chksql");
			m_no_of_due_days = req.getParameter("no_of_days");
			m_val=m_no_of_due_days+" Days Collection Reminder";
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
		//	if (m_chksql.trim().equals("idle")) {
		//		out.println("idle");
	//		}
	
				stmt2 = conn.createStatement ();

			if(m_chksql.trim().equals("main_page")){
			
			String m_sort_column   = "INVOICE_NO";	
			String m_order_by_type = "ASC";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			
			/*
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
			
			boolean more = rs.next();
			if(more){
			m_sys_date=rs.getString(1);
			}
			*/
			
			callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+".AF_RE_COLLECT_DUE_DAYEND(:1,:2,:3);END;");
			//out.println("t4");
			//callstmt1.setString(1 ,m_sys_date); //Modified Nuwan De Silva 18-04-2007  "18-01-2007"
			callstmt1.setString(1,"AF_RE_COLLECTION_DUE_RPT_SELECT");//AF_RE_COLLECTION_DUE_RPT_SELECT AF_RE_RPT_COLLECTION_DUE_SELECT Modified Nuwan De Silva
			callstmt1.setString(2,"NEW");
			callstmt1.setString(3,m_username);
			callstmt1.execute();
			
			
			
			
			
			//(M_DAYEND_DATE,M_SCREEN_NAME,M_OPTION_NAME,M_ENT_USER);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			out.println("var m_to_date=''");
			out.println("var m_from_date=''");
			
			out.println("function befor_end(m_obj) {");
			out.println("   m_obj.focus();");
			out.println("}");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("if('"+m_no_of_due_days+"'=='ALL')");
			out.println("m_val='All Collection Reminders'");
			
			out.println("if('"+m_no_of_due_days+"'=='TODAY')");
			out.println("m_val='Collection Reminder'");
			
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Collection Process - 3 Days Collection Reminder\";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Collection Process - \"+m_val;"); 
			out.println("}");
			out.println("}");
			
			
			/*  out.println("function load_data(m_app_no,m_app_sts) {");
			out.println(" if(m_app_sts=='IP') { ");
			out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=main_page&application_no=\"+m_app_no;"); 
			out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("  }");
			out.println(" else { ");
			out.println("   alert(m_app_no+'  is complete.');"); 
			out.println("  }");
			out.println("}");
			*/
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			//out.println("alert(m_sort_col);");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=request_details&no_of_days="+m_no_of_due_days+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&from_date='\"+m_from_date+\"'&to_date='\"+m_to_date+\"'&invoice_no=\"+document.Form1.TXT_INVOICE_CODE.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;"); 
			// out.println(" window.location.href=m_url;");  
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function clear_data() {");
			out.println(" document.Form1.TXT_FINANCE_NO.value =''");
			out.println("}");

			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		} else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
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
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
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
			
			
			out.println("function check_number(obj,size){");
			
			out.println("if(obj.value!=''){"); 
			
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_noobject_nodecimal1(obj)"); 
			out.println("View_receipts(obj.value);");
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("DAYS.style.color='red';");
			out.println("}"); 
			
			out.println("}"); 
			
			
			
			out.println("function View_receipts(m_val) {");				
			out.println("if(m_val!=\"\"){");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=\"+m_val;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");	
			
			out.println("}");	
			
			
			out.println("function Generate_Letter(m_client_code,m_finance_no,m_no_of_due_date,m_button_clicked) {");
			//out.println("function Generate_Letter(m_client_code,m_no_of_due_date,m_button_clicked) {");
			//--commented by delanjali on 2007-08-31--------------------------------------
			//out.println("if(m_no_of_due_date >=3 && 14 >= m_no_of_due_date){");
			//out.println("m_no_of_due_date=3");
			//out.println("}");
			//out.println("else if(m_no_of_due_date >=15  && 29 >= m_no_of_due_date){");
			//out.println("m_no_of_due_date=15");			
			//out.println("}");
			//out.println("else if(m_no_of_due_date >=30 ){");
			//out.println("m_no_of_due_date=30");
			//out.println("}");
			//------------------------------------------------------------------------------
			//added by delanjali on 2007-08-31----------------------------------------------
			out.println("if(m_button_clicked==3){");
			out.println("m_no_of_due_date=3");
			out.println("}");
			out.println("if(m_button_clicked==15){");
			out.println("m_no_of_due_date=15");			
			out.println("}");
			//------------------------------------------------------------------------------
			
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Letter?chksql=main_page&finance_no=\"+m_finance_no+\"&client_no=\"+m_client_code+\"&print=TRUE&no_of_due_date=\"+m_no_of_due_date;"); 
			
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Letter?chksql=main_page&client_no=\"+m_client_code+\"&print=TRUE&no_of_due_date=\"+m_no_of_due_date;"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=680,height=600,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("}");	
			
			
			
			
			
			out.println("function close_window1(){");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report_Select';"); 
			out.println("}");	
			
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");	
			out.println("else if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=v_yy;");
			out.println("  }");	
			
			out.println("}");		
			
			out.println("function check_date_from(){ ");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" || document.Form1.TXT_FROM_DATE_MM.value!=\"\" || document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");
			out.println("else{");
			out.println(" alert('Please enter From Date')");
			out.println("}");
			
			out.println("}");
			
			out.println("function check_date_to(){ ");
			out.println("if(document.Form1.TXT_TO_DATE_DD.value!=\"\" || document.Form1.TXT_TO_DATE_MM.value!=\"\" || document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY);");
			out.println("}");
			out.println("else{");
			out.println(" alert('Please enter To Date')");
			out.println("}");
			
			out.println("}");
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("}");
			
			out.println("function view(){ ");		
			out.println("if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
			out.println("m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value");
			out.println("}");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			out.println("m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");
			out.println("}");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=request_details&no_of_days="+m_no_of_due_days+"&from_date='\"+m_from_date+\"'&to_date='\"+m_to_date+\"'&invoice_no=\"+document.Form1.TXT_INVOICE_CODE.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"\""); 
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=request_details&no_of_days=ALL&from_date='\"+m_from_date+\"'&to_date='\"+m_to_date+\"'&fin_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\"");   // Modified by Chatura Jayawardena
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			
			out.println("function client_help(){");
			//out.println("alert("+m_no_of_due_days+");");
			out.println("Crit=document.Form1.TXT_FINANCE_NO.value+\"@\";");
			//out.println(" document.Form1.hid_help_type.value='1' ");
			//out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
			out.println("HelpBox('1','10','0',Crit,'m_help_TXT_FinanceSql_sql','1');");
			out.println("}");		
			
			out.println("function client_assign(oBj){");
			//out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
			out.println(" document.Form1.TXT_FINANCE_NO.value =oBj.valout[2]");
			out.println("}");		
			
			out.println("</Script>");
			out.println("<body onload=\"load_roll_value('"+m_val+"')\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" >");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			
			
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
			
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" >   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Process - 3 Days Collection Reminder</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			
			out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			
			String m_from_date1="";
			
			rs2=stmt2.executeQuery("SELECT to_char(SYSDATE,'dd-mm-yyyy') FROM DUAL ");
			boolean more1=rs2.next();
			if(more1){
			m_from_date1=rs2.getString(1);
			more1=rs2.next();
			}
			
			out.println("<br>"); 				
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>"); 
			//out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT'  class=div_input><b>Client Code</b></DIV><input"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT'  class=div_input><b>Finance No</b></DIV><input"); 
			out.println(" class=\"txt_input\" type=\"text\" name=TXT_FINANCE_NO maxlength=\"15\" size=\"15\" >");
			out.println("<input type=button name=cli_help value=Help class=\"but_input\" onclick=\"client_help()\">");
			out.println("</td> ");
			/*out.println("<td width='10%' ><DIV id='DIV_TXT_INVOICE'  class=div_input><b>Invoice No</b></DIV><input"); 
			out.println(" class=\"txt_input\" type=\"text\" name=TXT_INVOICE_CODE maxlength=\"15\" size=\"15\" >");
			out.println("</td> ");
			*/
			out.println("<td width='10%' ><DIV id='DIV_TXT_INVOICE_FROM_DATE'  class=div_input><b> From Date </b></DIV><input"); 
			out.println(" class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onblur=\"check_date_from()\" value=\""+m_from_date1.substring(0,2)+"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"check_date_from()\" value=\""+m_from_date1.substring(3,5)+"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"check_date_from()\" value=\""+m_from_date1.substring(6,10)+"\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(1)><u>   Calendar</u></a>");	
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_INVOICE_TO_DATE'  class=div_input><b> To Date </b></DIV><input "); 
			out.println("class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" onblur=\"check_date_to()\" value=\""+m_from_date1.substring(0,2)+"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"check_date_to()\" value=\""+m_from_date1.substring(3,5)+"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"check_date_to()\" value=\""+m_from_date1.substring(6,10)+"\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(2)><u>   Calendar</u></a>");	
			out.println("</td> ");
			out.println("<td width='5%'>");
			out.println("<input class=\"but_input\" type=\"button\" name=VIEW value=View onclick=\"view()\">");	
			out.println("</td> ");
			out.println("</tr>"); 
			out.println("</table>");
			out.println("<br>"); 
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
			out.println("<tr class=tr_input>");
			out.println("<td valign=top  width=100% Id=Follow_up> ");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
			
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
		
					
					
	
      /*    out.println("<tr class=pdn_txtpos2 align='center'>");
					out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') ><u>Invoice No</u></td>");
          out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Client Code'   onclick=sort_data('CLIENT_CODE') ><u>Client Code</u></td>");
          out.println("<td  width='8%'  style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') ><u>Finance No</u></td>");
          out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Invoice Date'  onclick=sort_data('INVOICE_DATE') ><u>Invoice Date</u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - No of Days Due' onclick=sort_data('NO_OF_DAYS_DUE') ><u>No of Days Due</u></td>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Amount Due'        onclick=sort_data('AMOUNT_DUE') ><u>Amount Due</u></td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Collection Officer'  onclick=sort_data('COLLECTION_OFFICER') ><u>Collection Officer</u></td>");
					
					out.println("</tr>");*/
					 
						
					/* rs = stmt.executeQuery (" SELECT "+
							                        " A.APPLICATION_NO,"+ //1
																			" NVL(A.FACILITY_NO,'-'), "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+ //7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			//"	NVL( (SELECT SUM(C.TOTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C WHERE C.APPLICATION_NO=A.APPLICATION_NO),0) PROFORMA_TOTAL, "+ //
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			" DECODE(APPLICATION_STATUS,'COMPLETED','OK','IP') APP_STS, "+ //12
																			" NVL(CURRENCY_CODE,'-') "+ //13
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																			" WHERE APPLICATION_STATUS NOT IN ('CANCEL') "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
         */
									 
				else if(m_chksql.trim().equals("request_details")){
				int j = 0;   
				
				String m_sort_column   = "INVOICE_NO";	
				String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
				m_sort_column = req.getParameter("sort_column");
				m_order_by_type = req.getParameter("order_by_type");
				}
				
				
				String m_fin_no=req.getParameter("fin_no");
				//String m_client_code=req.getParameter("client_code");
				//String m_invoice_no=req.getParameter("invoice_no");
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				if (m_no_of_due_days.trim().equals("ALL")) {
				
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//	out.println("<td colspan=6 align=right><input type=button name=Generate_Letter value=Generate Letter class=mainbut onclick=Generate_letter(); ></td>");
				out.println("<td colspan=10 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				//out.println("<td  width='11%' align='left' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') >Invoice No</td>");
				out.println("<td  width='11%' align='left' style= cursor:hand; title='Click here to sort by - Client Name'   onclick=sort_data('CLIENT_NAME') >Client Name</td>");
				out.println("<td  width='11%'  align='left' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') >Finance No</td>");
				out.println("<td  width='8%' align='left' style= cursor:hand; title='Click here to sort by - Invoice Date'  onclick=sort_data('INVOICE_DATE') >Invoice Date</td>");
				out.println("<td  width='8%'  align='left' style= cursor:hand; title='Click here to sort by - No of Days Due' onclick=sort_data('NO_OF_DAYS_DUE') >No of Days Due</td>");
				out.println("<td  width='8%' align='right' style= cursor:hand; title='Click here to sort by - Amount Due'        onclick=sort_data('AMOUNT_DUE') >Amount Due</td>");
				out.println("<td  width='8%' align='right' style= cursor:hand; title='Click here to sort by - Receipt Balance'  onclick=sort_data('RECEIPT_BALANCE') >Receipt Balance</td>");
				out.println("<td  width='9%' align='left' style= cursor:hand; title='Click here to sort by - Collection Officer'  onclick=sort_data('COLLECTION_OFFICER') >Officer</td>");
				out.println("<td  width='11%' align='left' style= cursor:hand; title='Click here to sort by - Collection Officer Name'  onclick=sort_data('COLLECTION_OFFICER_NAME') >Officer Name</td>");
				out.println("<td  width='5%'>3 Days</td>");
				out.println("<td  width='2%'>&nbsp;</td>");
				out.println("<td  width='5%'>15 Days</td>");
				out.println("<td  width='2%'>&nbsp;</td>");
				
				out.println("</tr>");
				
				
				
			/*	rs = stmt.executeQuery 
				(" SELECT "+
				" DISTINCT NVL(INVOICE_NO,'-') INVOICE_NO, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
				" NVL(FINANCE_NO,'-') FINANCE_NO, "+
				" NVL(TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),'-') INVOICE_DATE, "+
				" NVL(NO_OF_DAYS_DUE,0) NO_OF_DAYS_DUE, "+
				" NVL(AMOUNT_DUE,0.00) AMOUNT_DUE, "+
				" NVL(RECEIPT_NOT_ALLO,0.00) RECEIPT_BALANCE ,"+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER, "+
				" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER_NAME, "+
				" CLIENT_CODE, "+
				" NVL(DUE_3_LETTER_GEN,'N'), "+
				" NVL(DUE_15_LETTER_GEN,'N') "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
				//	" WHERE RECEIPT_NOT_ALLO > 0 "+
				" WHERE NO_OF_DAYS_DUE >2   "+
				//" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE(TO_CHAR("+m_sys_date+",'DD-MM-YYYY'),'DD-MM-YYYY') "+
				//" AND INVOICE_NO in ('IN20070117-1903','IN20070102-1595','IN20070112-1884','IN20061214-1324','IN20070112-1878','IN20061227-1491','IN20070315-2424','IN20061227-1531') "+
				" AND UPPER(INVOICE_NO) LIKE UPPER('"+m_invoice_no+"%') "+
				" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
				" AND (INVOICE_DATE >= TO_DATE("+m_from_date+",'DD-MM-YYYY') "+
				" AND INVOICE_DATE<= TO_DATE("+m_to_date+",'DD-MM-YYYY')) "+	
				" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				*/
				
				rs = stmt.executeQuery 
				(" SELECT "+
			  " CLIENT_CODE, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,  "+
				" NVL(FINANCE_NO,'-') FINANCE_NO,  "+
				" "+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO), "+
				" ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0), "+
				" NVL(SUM(AMOUNT_DUE),0.00) AMOUNT_DUE,  "+
				" NVL(SUM(RECEIPT_NOT_ALLO),0.00) RECEIPT_BALANCE , "+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER,  "+
				" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER_NAME  "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'3'),'N') "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'15'),'N') "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE  "+
				//" WHERE NO_OF_DAYS_DUE >2    "+
				" WHERE ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0) > 2 "+
				//" AND( UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')  "+
				" AND UPPER(FINANCE_NO) LIKE UPPER('"+m_fin_no+"%') "+//added By Sandun on 06-10-2008 Modified by Dineth on 2008-10-13
				//" OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(UPPER(CLIENT_CODE)) LIKE UPPER('%"+m_client_code+"%') ) "+
				/*" AND (INVOICE_DATE >= TO_DATE("+m_from_date+",'DD-MM-YYYY') "+
				" AND INVOICE_DATE<= TO_DATE("+m_to_date+",'DD-MM-YYYY')) "+*///COMMENTED BY JITHENDRA 01-12-2016		
				" GROUP BY CLIENT_CODE,FINANCE_NO,COLLECTION_OFFICER ");

		
				
				
				while(rs.next()){
				
				if(j>0 && j%2==1){
				out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
				
				//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
				//out.println("<td width='11%' align='left' style= cursor:hand; onClick=\"show_invoice_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td> ");
				out.println("<td width='11%' align='left'  style= cursor:hand; onClick=\"show_client('"+rs.getString(1)+"')\" ><u>"+rs.getString(2) +"</u></td>");
				out.println("<td width='11%' align='left'   style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><u>"+rs.getString(3) +"</u></td>");
				out.println("<td width='8%' align='left'>"+rs.getString(4) +"</td>");
				out.println("<td width='8%' align='left'>"+rs.getInt(5) +"</td>");
				out.println("<td width='8%' align='right'>"+nf.format(rs.getDouble(6)) +"</td>");
				out.println("<td width='8%' align='right'>"+nf.format(rs.getDouble(7)) +"</td>");
				out.println("<td width='9%'  align='left'>"+rs.getString(8) +"</td>");
				out.println("<td width='11%' align='left'>"+rs.getString(9) +"</td>");
				int m_days=0;
				if(rs.getInt(5)>=3 && rs.getInt(5)<15){
				m_days=3;									
				}
				if(rs.getInt(5) >=15){
				m_days=15;									
				}
				//----------------------------------
				
				if(rs.getString(10).equals("Y")){
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+rs.getString(5) +",3)\" name=BUT_LETTER_1_"+j+" value=\"Letter\" disabled></td>"); 
				//  out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"',"+rs.getString(5) +",3)\" name=BUT_LETTER_1_"+j+" value=\"Letter\" disabled></td>"); 
				out.println("<td width='2%' align='center'><input type=\"checkbox\" value=\"Y\" onclick=\"\" name=CHK_LETTER_THIRD_"+j+" checked disabled></td>"); 
				}
				else{
				//out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"',"+rs.getString(5) +",3)\" name=BUT_LETTER_1_"+j+" value=\"Letter\" ></td>"); 
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+rs.getString(5) +",3)\" name=BUT_LETTER_1_"+j+" value=\"Letter\" ></td>"); 
				out.println("<td width='2%' align='center'><input type=\"checkbox\" value=\"N\" onclick=\"\" name=CHK_LETTER_THIRD_"+j+" unchecked></td>"); 
				
				}
				
				///--------------------------
				
				if(rs.getString(11).equals("Y")){
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" disabled></td>"); 
				//out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" disabled></td>"); 
				out.println("<td width='2%' align='center'><input type=\"checkbox\" value=\"Y\"  onclick=\"\" name=CHK_LETTER_FIFNTH_"+j+" checked disabled></td>"); 
				}
				else{
				if(m_days==3){
				//out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" ></td>"); 
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" disabled></td>"); 
				out.println("<td width='2%' align='center'><input type=\"checkbox\" value=\"N\"  onclick=\"\" name=CHK_LETTER_FIFNTH_"+j+" unchecked disabled></td>"); 
				}
				else{
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" ></td>"); 
				//out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"',"+rs.getString(5) +",15)\" name=BUT_LETTER_2_"+j+" value=\"Letter\" ></td>"); 
				out.println("<td width='2%' align='center'><input type=\"checkbox\" value=\"N\"  onclick=\"\" name=CHK_LETTER_FIFNTH_"+j+" unchecked></td>"); 
				}
				}
				//----------------------------------
				
				out.println("</tr>");
				j=j+1;
				}
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=10 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				out.println("<td align=right colspan=9><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				}
				else if (m_no_of_due_days.trim().equals("TODAY")) {
				
				
				out.println("<table class=table border='0' width='100%' >");
				//out.println("<tr class=tr_input>");
				//out.println("<td colspan=1 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				//out.println("<td colspan=2 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("</tr>");
				
				
				/*//commented by delanjali on 2007-08-31--
				out.println("<tr >"); 
				out.println("<td width='30%' ID=DAYS >No of Due Days </td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_DUE_DATES' maxlength='3' size='3' onBlur=\"check_number(document.Form1.TXT_NO_OF_DUE_DATES,5)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_VIEW' value=\"View\" onClick=\"check_number(document.Form1.TXT_NO_OF_DUE_DATES,5)\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				*/
				
				
				/*     out.println("<tr class=pdn_txtpos2 align='center'>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') ><u>Invoice No</u></td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Client Code'   onclick=sort_data('CLIENT_CODE') ><u>Client Code</u></td>");
				out.println("<td  width='8%'  style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') ><u>Finance No</u></td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Invoice Date'  onclick=sort_data('INVOICE_DATE') ><u>Invoice Date</u></td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - No of Days Due' onclick=sort_data('NO_OF_DAYS_DUE') ><u>No of Days Due</u></td>");
				out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Amount Due'        onclick=sort_data('AMOUNT_DUE') ><u>Amount Due</u></td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Collection Officer'  onclick=sort_data('COLLECTION_OFFICER') ><u>Collection Officer</u></td>");
				
				out.println("</tr>");
				
				//rs1=stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY')FROM DUAL");
				//boolean more=rs1.next();
				//if(more)
				//	{
				//	m_sys_date=rs1.getString(1);
				//	out.println("date"+m_sys_date);
				//	}
				
				rs = stmt.executeQuery (" SELECT "+
				" NVL(INVOICE_NO,'-') INVOICE_NO, "+
				" NVL(CLIENT_CODE,'-') CLIENT_CODE, "+
				" NVL(FINANCE_NO,'-') FINANCE_NO, "+
				" NVL(TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),'-') INVOICE_DATE, "+
				" NVL(NO_OF_DAYS_DUE,0) NO_OF_DAYS_DUE, "+
				" NVL(AMOUNT_DUE,0.00) AMOUNT_DUE, "+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
				" WHERE TO_DATE(INVOICE_DATE,'DD-MM-YYYY')=TO_DATE(SYSDATE,'DD-MM-YYYY')  "+//TO_DATE("+m_sys_date+",'DD-MM-YYYY') "+
				" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				while(rs.next()){
				
				if(j>0 && j%2==1){
				out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
				
				//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
				out.println("<td width='12%' align='left'>"+rs.getString(1) +"</td>");
				out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
				out.println("<td width='8%' align='left'>"+rs.getString(3) +"</td>");
				out.println("<td width='5%' align='left'>"+rs.getString(4) +"</td>");
				out.println("<td width='10%' align='left'>"+rs.getInt(5) +"</td>");
				out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6)) +"</td>");
				out.println("<td width='5%'  align='left'>"+rs.getString(7) +"</td>");
				out.println("</tr>");
				j=j+1;
				}
				*/
				
				//		out.println("<tr class=tr_input>");
				// out.println("<td colspan=1 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				// out.println("<td align=right colspan=2><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				
				}
				
				else{	
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//	out.println("<td colspan=5 align=right><input type=button name=Generate_Letter value=Generate Letter class=mainbut onclick=Generate_letter(); ></td>");
				out.println("<td colspan=8 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				//out.println("<td  width='12%' align='left' style= cursor:hand;  title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') >Invoice No</td>");
				out.println("<td  width='15%' align='left' style= cursor:hand;  title='Click here to sort by - Client Name'   onclick=sort_data('CLIENT_NAME') >Client Name</td>");
				out.println("<td  width='12%' align='left' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') >Finance No</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Invoice Date'  onclick=sort_data('INVOICE_DATE') >Invoice Date</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand;   title='Click here to sort by - No of Days Due' onclick=sort_data('NO_OF_DAYS_DUE') >No of Days Due</td>");
				out.println("<td  width='8%'  align='right' style= cursor:hand;   title='Click here to sort by - Amount Due'       onclick=sort_data('AMOUNT_DUE') >Amount Due</td>");
				out.println("<td  width='8%'  align='right' style= cursor:hand;   title='Click here to sort by - Receipt Balance'  onclick=sort_data('RECEIPT_BALANCE') >Receipt Balance</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Collection Officer'  onclick=sort_data('COLLECTION_OFFICER') >Collection Officer</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Collection Officer Name'  onclick=sort_data('COLLECTION_OFFICER_NAME') >Collection Officer Name</td>");
				out.println("<td  width='5%'>&nbsp;</td>");
				
				out.println("</tr>");
				
			/*	rs = stmt.executeQuery (" SELECT "+
				" DISTINCT NVL(INVOICE_NO,'-') INVOICE_NO, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
				" NVL(FINANCE_NO,'-') FINANCE_NO, "+
				" NVL(TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),'-') INVOICE_DATE, "+
				" NVL(NO_OF_DAYS_DUE,0) NO_OF_DAYS_DUE, "+
				" NVL(AMOUNT_DUE,0.00) AMOUNT_DUE, "+
				" NVL(RECEIPT_NOT_ALLO,0.00) RECEIPT_BALANCE ,"+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER, "+
				" "+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER) COLLECTION_OFFICER_NAME, "+
				" CLIENT_CODE "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
				" WHERE NO_OF_DAYS_DUE="+m_no_of_due_days+" "+
				//" AND RECEIPT_NOT_ALLO > 0 "+
				" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				*/

  			if (m_no_of_due_days.trim().equals("3")) {
				rs = stmt.executeQuery 
				(" SELECT "+
			  " CLIENT_CODE, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,  "+
				" NVL(FINANCE_NO,'-') FINANCE_NO,  "+
				" "+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO), "+
				" ROUND(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0), "+
				" NVL(SUM(AMOUNT_DUE),0.00) AMOUNT_DUE,  "+
				" NVL(SUM(RECEIPT_NOT_ALLO),0.00) RECEIPT_BALANCE , "+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER,  "+
				" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER_NAME  "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'3'),'N') "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'15'),'N') "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE  "+
				" WHERE ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0)> ="+m_no_of_due_days+"    "+
				" AND ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0) < 15 "+
				//" AND( UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')  "+//
				" AND UPPER(FINANCE_NO) LIKE UPPER('"+m_fin_no+"%')  "+ ////Mod By Sandun on 06-10-2008 Modified by Dineth on 2008-10-13
				//" OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(UPPER(CLIENT_CODE)) LIKE UPPER('%"+m_client_code+"%') ) "+
				/*" AND (INVOICE_DATE >= TO_DATE("+m_from_date+",'DD-MM-YYYY') "+
				" AND INVOICE_DATE<= TO_DATE("+m_to_date+",'DD-MM-YYYY')) "+*///COMMENTED BY JITHENDRA 01-12-2016	
				" AND NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'3'),'N')='N' "+
				" GROUP BY CLIENT_CODE,FINANCE_NO,COLLECTION_OFFICER ");
				}
				
				
				else if (m_no_of_due_days.trim().equals("15")) {
				
				rs = stmt.executeQuery 
				(" SELECT "+
			  " CLIENT_CODE, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,  "+
				" NVL(FINANCE_NO,'-') FINANCE_NO,  "+
				" "+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO), "+
				" ROUND(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0), "+
				" NVL(SUM(AMOUNT_DUE),0.00) AMOUNT_DUE,  "+
				" NVL(SUM(RECEIPT_NOT_ALLO),0.00) RECEIPT_BALANCE , "+
				" NVL(COLLECTION_OFFICER,'-')  COLLECTION_OFFICER,  "+
				" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER_NAME  "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'3'),'N') "+
				",NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'15'),'N') "+
				" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE  "+
				" WHERE ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0) >="+m_no_of_due_days+"    "+
				" AND ROUND(SYSDATE - TO_DATE("+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO),'DD-MM-YYYY'),0) < 29 "+
				//" AND( UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')  "+
				" AND UPPER(FINANCE_NO) LIKE UPPER('"+m_fin_no+"%')  "+//Mod By Sandun on 06-10-2008 Modified by Dineth on 2008-10-13
			//	" OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(UPPER(CLIENT_CODE)) LIKE UPPER('%"+m_client_code+"%') ) "+
				/*" AND (INVOICE_DATE >= TO_DATE("+m_from_date+",'DD-MM-YYYY') "+
				" AND INVOICE_DATE<= TO_DATE("+m_to_date+",'DD-MM-YYYY')) "+*///COMMENTED BY JITHENDRA 01-12-2016	
				//" AND NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'3'),'N')='Y' "+
				" AND NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'15'),'N')='N' "+
				" GROUP BY CLIENT_CODE,FINANCE_NO,COLLECTION_OFFICER ");
				
        }
				
				while(rs.next()){
				
				if(j>0 && j%2==1){
				out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
				
				//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
				//out.println("<td width='12%' align='left' style= cursor:hand; onClick=\"show_invoice_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td> ");
				out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_client('"+rs.getString(1)+"')\"><u>"+rs.getString(2) +"</u></td>");
				out.println("<td width='12%' align='left'  style= cursor:hand; onClick=\"show_transaction_info('"+rs.getString(3)+"','"+rs.getString(1)+"')\"><u>"+rs.getString(3) +"</u></td>");//Drill Changed to Transaction History by Jithendra 01-12-2016
				out.println("<td width='10%' align='left'>"+rs.getString(4) +"</td>");
				out.println("<td width='10%' align='left'>"+rs.getInt(5) +"</td>");
				out.println("<td width='8%' align='right'>"+nf.format(rs.getDouble(6)) +"</td>");
				out.println("<td width='8%' align='right'>"+nf.format(rs.getDouble(7)) +"</td>");
				out.println("<td width='10%'  align='left'>"+rs.getString(8) +"</td>");
				out.println("<td width='10%' align='right'>"+rs.getString(9) +"</td>");
				out.println("<td width='5%' align='center'><input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"',"+m_no_of_due_days+")\" name=BUT_LETTER value=\"Letter\" ></td>"); 
				out.println("</tr>");
				j=j+1;
				}
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=8 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window1(); ></td>");
				out.println("<td align=right colspan=9><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				
				}
				
				
				/*while(rs.next()){
				
				if(j>0 && j%2==1){
				out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
				
				//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
				out.println("<td width='12%' align='left'>"+rs.getString(1) +"</td>");
				out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
				out.println("<td width='8%' align='left'>"+rs.getString(3) +"</td>");
				out.println("<td width='5%' align='left'>"+rs.getString(4) +"</td>");
				out.println("<td width='10%' align='left'>"+rs.getInt(5) +"</td>");
				out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6)) +"</td>");
				out.println("<td width='5%'  align='left'>"+rs.getString(7) +"</td>");
				out.println("</tr>");
				j=j+1;
				}*/
				
				
				
				
				
				out.println("</tr></table>");
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("</html>");
				}
			
			//=========================================================================================================================			
  	
      //out.close();
			//conn.close();
			//this.destroy();
			
			
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
