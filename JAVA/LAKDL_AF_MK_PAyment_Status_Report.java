//Created by AH
//Payment Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MK_PAyment_Status_Report extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
  //public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public  void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		Connection conn=null;
	Statement stmt=null;
	java.text.NumberFormat nf=null,nf1=null;
	 ResultSet rs=null;
	 String m_chksql=null;
	ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page")){	
			
			String m_sys_date_dd="";
			String m_sys_date_mm="";
			String m_sys_date_yy="";
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0){");
			out.println("			document.Form1.FROM_DAY.value=data_vec[0];");
			out.println("			document.Form1.FROM_MONTH.value=data_vec[1];");
			out.println("			document.Form1.FROM_YEAR.value=data_vec[2];");
			out.println("			document.Form1.TO_DAY.value=data_vec[0];");
			out.println("			document.Form1.TO_MONTH.value=data_vec[1];");
			out.println("			document.Form1.TO_YEAR.value=data_vec[2];");
			out.println("		}");
			out.println("}");
	
			
			
			/*out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");*/
									
			/*out.println("function chk_sizer_code(){");
			out.println("assignState('M1');");
      out.println(" m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=get_SeizerCode&seizer_code=\"+document.Form1.TXT_SEIZER_CODE.value;");
      out.println("load_interface(m_url,'XML');");
			out.println("  ");
      out.println("}");
						
			out.println("function chk_repossession_no(){");
			out.println("assignState('M2');");
			out.println(" m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=get_Repossess_no&repossess_no=\"+document.Form1.TXT_FINANCE_NO.value;");
      out.println("load_interface(m_url,'XML');");
			out.println("}");*/
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MISF_repossession_details_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Payment Status Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Payment Status Report \";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			/*out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); */
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
			
			/*out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			
			out.println("if(document.Form1.TXT_TYPE.value==\"ALL\") {");
			out.println("    m_sql = \"m_help_TXT_REPOSSESSION_NO_sql_report\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
			out.println("}");
			out.println("else {");
			out.println("    m_sql = \"m_help_TXT_REPOSSESSION_NO_sql_report_2\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_SEIZER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("}");
						
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			
			out.println("}");*/
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_RECIEPT_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			//Seizer
			/*out.println("function Seizer_Help(Start,End,Hid_No,Sql,IfCount){");
			out.println("Crit=document.Form1.TXT_SEIZER_CODE.value+\"@\";");
			out.println("HelpBox('1','10','0',Crit,'SeizerCodeSql','3');");
			out.println("}");		
	
			out.println("function Seizer_assign(){");
			out.println(" document.Form1.TXT_SEIZER_CODE.value =oBj.valout[2]");
			out.println(" document.Form1.TXT_SEIZER_NAME.value =oBj.valout[3]"); 
		  out.println("}");		*/



			
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			//out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
		//	out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			//out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			//out.println("		Seizer_assign();"); 
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
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
		//	out.println("		alert('here');");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Report?chksql=main_page';"); 
			out.println("}");
			//===============================Commented By Dineth on 05-08-2008
			/*out.println("function load_data_frame(){ ");
			out.println("     document.Form1.hid_from_date.value=document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;"); 
			out.println("     document.Form1.hid_to_date.value=document.Form1.TO_DAY.value+'-'+document.Form1.TO_MONTH.value+'-'+document.Form1.TO_YEAR.value;");  
      out.println("if(document.Form1.TXT_TYPE.value==\"V\") {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&pay_no=\"+document.Form1.TXT_RECIEPT_NO.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
		  out.println("}");
			out.println("else {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&pay_no=\"+document.Form1.TXT_RECIEPT_NO.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println(" } ");*/
			//===============================End Comment
			out.println("function load_data_frame(){ ");
			out.println("     document.Form1.hid_from_date.value=document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;"); 
			out.println("     document.Form1.hid_to_date.value=document.Form1.TO_DAY.value+'-'+document.Form1.TO_MONTH.value+'-'+document.Form1.TO_YEAR.value;");
			out.println("m_status  = document.Form1.STATUS_SELECT.value;");
			out.println("m_pay_cat = document.Form1.PAY_CAT_SELECT.value;");
      //out.println("if(document.Form1.TXT_TYPE.value==\"V\") {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&pay_no=\"+document.Form1.TXT_RECIEPT_NO.value+\" \";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&app_status=\"+m_status+\"&pay_cat=\"+m_pay_cat+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\" \";");
			//out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
		  //out.println("}");
			//out.println("else {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&pay_no=\"+document.Form1.TXT_RECIEPT_NO.value+\" \";");
			//out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			//out.println("}");
			
			out.println(" } ");
			
					
			/*out.println("function change_type(obj) { ");
			out.println("if(obj.value=='SEIZER'){");
			out.println("m_table_main.innerHTML=\"\";");
			out.println("add_sizer_data();");
			out.println("}");
			out.println("else {");
			out.println("m_table_main.innerHTML=\"\";");
			out.println("}");
			out.println("}");*/
			
			/*out.println("function add_sizer_data(){");
			out.println("m_table_main.innerHTML=\"\";");
			out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"  cellspacing=\"0\" >'+");									
			out.println("'<tr>'+"); 
			out.println("'<td width=\"20%\" ><DIV id=DIV_TXT_SEIZER_CODE  class=div_input>Seizer Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_SEIZER_CODE maxlength=10 size=10 onblur=\"chk_sizer_code()\">'+"); 
			out.println("'<input class=but_input type=button name=BUT_TXT_SEIZER_CODE value=\"Help\" onClick=\"Seizer_Help()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<td width=\"20%\" ><DIV id=DIV_TXT_SEIZER_NAME  class=div_input>Seizer Name</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_SEIZER_NAME style={width=200px;} maxlength=10 size=10 disabled></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr></table>';"); 
      out.println("}");*/
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_SYS_PAYMENT_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_RECIEPT_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
			
			out.println("     document.Form1.FROM_DAY.value=v_dd;");
			out.println("     document.Form1.FROM_MONTH.value=v_mm;");
			out.println("     document.Form1.FROM_YEAR.value=v_yy;");
			out.println("     document.Form1.hid_from_date.value=document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;"); //added by nuwan de silva on 05-11-07
			out.println("   if(document.Form1.TO_DAY.value!=\"\" && document.Form1.TO_MONTH.value!=\"\" && document.Form1.TO_YEAR.value!=\"\"){");
			out.println("   chk_validity(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
			out.println("  }");				
			out.println("  }");				
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.TO_DAY.value=v_dd;");
			out.println("     document.Form1.TO_MONTH.value=v_mm;");
			out.println("     document.Form1.TO_YEAR.value=v_yy;");
			out.println("     document.Form1.hid_to_date.value=document.Form1.TO_DAY.value+'-'+document.Form1.TO_MONTH.value+'-'+document.Form1.TO_YEAR.value;"); //added by nuwan de silva on 05-11-07
			out.println("chk_validity(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
			out.println("  }");				
			
			out.println("}");		
			
			
			out.println("function chk_validity(Obj_From_DD,Obj_From_MM,Obj_From_YYYY,Obj_To_DD,Obj_To_MM,Obj_To_YYYY){ ");
			out.println("b_val_date=0;");
			out.println("if((parseFloat(Obj_From_DD.value))>=(parseFloat(Obj_To_DD.value))){ ");
			out.println("if((parseFloat(Obj_From_MM.value))<=(parseFloat(Obj_To_MM.value))){ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))<=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("   if(((parseFloat(Obj_From_DD.value))==(parseFloat(Obj_To_DD.value)))&& ");
			out.println("    ((parseFloat(Obj_From_MM.value))==(parseFloat(Obj_To_MM.value)))&& ");
			out.println("   ((parseFloat(Obj_From_YYYY.value))==(parseFloat(Obj_To_YYYY.value)))){ ");
			out.println("    } ");
			out.println("   else if(((parseFloat(Obj_From_DD.value))>=(parseFloat(Obj_To_DD.value)))&& ");
			out.println("   ((parseFloat(Obj_From_MM.value))==(parseFloat(Obj_To_MM.value)))&& ");
			out.println("    ((parseFloat(Obj_From_YYYY.value))==(parseFloat(Obj_To_YYYY.value)))){ ");
			out.println("      alert(\"'From Date' should be greater than 'To Date'\"); ");
			out.println("b_val_date=1");
			out.println("        } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("      alert(\"'From Date' should be greater than 'To Date'\"); ");
			out.println("b_val_date=1");
			out.println("  } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))>=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("      alert(\"'From Date' should be greater than 'To Date'\"); ");
			out.println("b_val_date=1");
			out.println("   } ");
			out.println("    else{ ");
			out.println("   }  ");
			out.println("  } ");
			out.println(" } ");
			out.println(" else{ ");
			out.println("  if((parseFloat(Obj_From_MM.value))<=(parseFloat(Obj_To_MM.value))){ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))<=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("      alert(\"'From Date' should be greater than 'To Date'\"); ");
			out.println("b_val_date=1");
			out.println("  } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))<(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("        } ");
			out.println("   else{ ");
			out.println("      alert(\"'From Date' should be greater than 'To Date'\"); ");
			out.println("b_val_date=1");
			out.println("   } ");
			out.println("  } ");
			out.println(" } ");
			
			
			out.println(" }");


			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");

			
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" ONLOAD=\"get_system_date();\" >");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">"); 
			
			
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box> Payment Status Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<td width='6%'></td>");  
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
			out.println("<table align='center' width='100%' class='table'>"); 
			//======================Commented by Dineth on 05-08-2008
			/*
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_RECIEPT_NO'  class=div_input>Payment Settlement No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_RECIEPT_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_RECIEPT_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			//========================Commented by Dineth on 05-08-2008
			/*out.println("<tr >"); 
			out.println("<td width='20%' >Type</td>");*/ 
		//	out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_TYPE' maxlength='1 size='1' onChange=\"change_type(this)\" >");  
			/*out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_TYPE' maxlength='1 size='1' >");  
			out.println("<option value='V'   selected >Value Date</option>");		
			out.println("<option value='E'         >Enter Date</option>");			
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			//========================end comment
			out.println("</table>"); 
			
			/*out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"  cellspacing='0' ><tr>");
			out.println("<td width='*%' ><div id=m_table_main></div></td>");
			out.println("<tr></table>"); */

			
			/*out.println("<table align='center' width='100%' class='table'>"); 
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Repossession No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"chk_repossession_no()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");*/
			
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
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
			out.println("<td width='20%' ><DIV id='DIV_TXT_FROM_DATE'  class=div_input>From Date </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='FROM_DAY' maxlength='2' size='2'   value='"+m_sys_date_dd+"' >");
			out.println("                 <input class='txt_input5' type='text' name='FROM_MONTH' maxlength='2' size='2' value='"+m_sys_date_mm+"' >");
			out.println("                 <input class='txt_input5' type='text' name='FROM_YEAR' maxlength='4' size='4'  value='"+m_sys_date_yy+"' onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)  ></td>"); 
			out.println("<td width='8%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>To Date </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TO_DAY' maxlength='2' size='2'   value='"+m_sys_date_dd+"' >");
			out.println("                 <input class='txt_input5' type='text' name='TO_MONTH' maxlength='2' size='2' value='"+m_sys_date_mm+"' >");
			out.println("                 <input class='txt_input5' type='text' name='TO_YEAR' maxlength='4' size='4'  value='"+m_sys_date_yy+"' onchange=check_Date(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR) ></td>"); 
			//out.println("<td width='10*%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");//Added By Sandun on 09-12-2008
			out.println("<td width='20%'>Payment Category</td>");
			out.println("<td width='20%'><select name='PAY_CAT_SELECT' style='{width:130px}' class='txt_input' >");
			out.println("<option value='VEN_PAY'>Vendor Payments</option>");
			out.println("<option value='OTR_PAY'>Other Payments</option>");
			out.println("</select></td>");
			out.println("</tr>");
			
			out.println("<tr>");//Added By Sandun on 23-10-2008
			out.println("<td width='20%'>Status</td>");
			out.println("<td width='20%'><select name='STATUS_SELECT' style='{width:130px}' class='txt_input' >");
			out.println("<option value='DISBRS'>Cheque Disbursement</option>");
			out.println("<option value='OTHER'>Other</option>");
			out.println("<option value='ALL' selected>All</option>");
			out.println("</select></td>");
			out.println("<td width='8%'>&nbsp;</td>"); 
			out.println("<td width='20%' ><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
      out.println("</table>");
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</html>");

					
			}

			
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
