//Created by Chandana on 06-03-2007 at  1.24 P.M.
//Repossession_Details_Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Repossession_Details_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
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
			else if(m_chksql.trim().equals("main_page1")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			
			out.println("function get_vector(data_vec) {");
			out.println(" if(document.Form1.TXT_TYPE.value==\"SEIZER\") {");
			out.println("			if(data_vec.length==0 &&  document.Form1.TXT_SEIZER_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'  ){");
			out.println("     Seizer_Help();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_SEIZER_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'  ){");
			out.println("    document.Form1.TXT_SEIZER_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_SEIZER_NAME.value=data_vec[1];"); 
			out.println("			}");
			out.println("			}");
			
			out.println("			if(data_vec.length==0 &&  document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_update();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[0];"); 
			//out.println("    document.Form1.TXT_SEIZER_NAME.value=data_vec[1];"); 
			out.println("			}");
			
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
									
			out.println("function chk_sizer_code(){");
			out.println("assignState('M1');");
      out.println(" m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=get_SeizerCode&seizer_code=\"+document.Form1.TXT_SEIZER_CODE.value;");
      out.println("load_interface(m_url,'XML');");
			out.println("  ");
      out.println("}");
						
			out.println("function chk_repossession_no(){");
			out.println("assignState('M2');");
			out.println(" m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=get_Repossess_no&repossess_no=\"+document.Form1.TXT_FINANCE_NO.value;");
      out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Repossession Details Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Repossession Details Report \";"); 
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
			
			out.println("function help_update() {");
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
			
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			//Seizer
			out.println("function Seizer_Help(Start,End,Hid_No,Sql,IfCount){");
			out.println("Crit=document.Form1.TXT_SEIZER_CODE.value+\"@\";");
			out.println("HelpBox('1','10','0',Crit,'SeizerCodeSql','3');");
			out.println("}");		
	
			out.println("function Seizer_assign(){");
			out.println(" document.Form1.TXT_SEIZER_CODE.value =oBj.valout[2]");
			out.println(" document.Form1.TXT_SEIZER_NAME.value =oBj.valout[3]"); 
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
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		Seizer_assign();"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 
			
			
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page1';"); 
			out.println("}");
			
			//Modified by Mahela on 10-04-2007
			out.println("function load_data_frame(){ ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&REPOSS_NO=\"+document.Form1.TXT_FINANCE_NO.value+\" \");  ");
			
			//comment by nuwan de silva on 05-11-07--------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&REPOSS_NO=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			
			//added by nuwan de silva on 05-11-07----------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&REPOSS_NO=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			
			out.println("if(document.Form1.TXT_TYPE.value==\"ALL\") {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&REPOSS_NO=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			out.println("}");
			
			out.println("else {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&type=\"+document.Form1.TXT_TYPE.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&seizer_code=\"+document.Form1.TXT_SEIZER_CODE.value+\"&REPOSS_NO=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			out.println("}");
			
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
					
			//added by nuwan de silva on 05-11-07-------
			out.println("function change_type(obj) { ");
			out.println("if(obj.value=='SEIZER'){");
			out.println("m_table_main.innerHTML=\"\";");
			out.println("add_sizer_data();");
			out.println("}");
			out.println("else {");
			out.println("m_table_main.innerHTML=\"\";");
			out.println("}");
			out.println("}");
			
			//added by nuwan de silva on 01-11-07-------
			out.println("function add_sizer_data(){");
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


			
			out.println("function check_date(OBJDD,OBJMM,OBJYY){");
			out.println("if(OBJDD.value!=\"\" && OBJMM.value!=\"\" && OBJYY.value!=\"\") { ");
			out.println("if(checkMonthLength(OBJDD,OBJMM,OBJYY)){");
			out.println("chk_validity(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
		  out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box> Repossession Details Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
   //   out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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
			
			//added by nuwan de silva on 05-11-07---------------------------
			out.println("<tr >"); 
			out.println("<td width='20%' >Type</td>"); 
			out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_TYPE' maxlength='1 size='1' onChange=\"change_type(this)\" >");  
			out.println("<option value='ALL'   selected >All</option>");		
			out.println("<option value='SEIZER'         >Seizer</option>");			
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"  cellspacing='0' ><tr>");
			out.println("<td width='*%' ><div id=m_table_main></div></td>");
			out.println("<tr></table>"); 

			
			out.println("<table align='center' width='100%' class='table'>"); 
		/*	out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SEIZER_CODE'  class=div_input>Seizer Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SEIZER_CODE' maxlength='10' size='10' onblur=\"chk_sizer_code()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SEIZER_CODE' value=\"Help\" onClick=\"Seizer_Help()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			
			out.println("</tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SEIZER_CODE'  class=div_input>Seizer Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SEIZER_NAME' style=\"{width=200px;}\"  maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Repossession No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"chk_repossession_no()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
//			out.println("<input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width='20%' ><DIV id='DIV_TXT_FROM_DATE'  class=div_input>From Date </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='FROM_DAY' maxlength='2' size='2'   onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' >");
			out.println("                 <input class='txt_input5' type='text' name='FROM_MONTH' maxlength='2' size='2' onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' >");
			out.println("                 <input class='txt_input5' type='text' name='FROM_YEAR' maxlength='4' size='4'  onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' ><a href style='{cursor:hand; }' onclick=load_calendar('1') >   Calendar</a></td>"); 
			//out.println("<td width='10%'>&nbsp;</td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>To Date </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TO_DAY' maxlength='2' size='2'   onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' >");
			out.println("                 <input class='txt_input5' type='text' name='TO_MONTH' maxlength='2' size='2' onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' >");
			out.println("                 <input class='txt_input5' type='text' name='TO_YEAR' maxlength='4' size='4'  onBlur='check_date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)' ><a href style='{cursor:hand; }' onclick=load_calendar('2') >   Calendar</a></td>"); 
			out.println("<td width='10*%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
      out.println("</table>");
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</html>");

					
			}
			else if(m_chksql.trim().equals("main_page")){
			
				String _m_from_date="";
				String _m_to_date="";
				String _m_type="";
				String _m_seizer_code="";
			  	String m_repos_no = req.getParameter("REPOSS_NO");
				
				if(req.getParameter("type")!=null){
				_m_type = req.getParameter("type");
				}
				if(req.getParameter("from_date")!=null){
				_m_from_date = req.getParameter("from_date");
				}
				if(req.getParameter("to_date")!=null){
				_m_to_date = req.getParameter("to_date");
				}
				if(req.getParameter("seizer_code")!=null){
				_m_seizer_code = req.getParameter("seizer_code");
				}

					   
						  	String m_sort_column="REPOSSESSION_NO";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null){
			          			m_sort_column = req.getParameter("sort_column");
							}
					 		if(req.getParameter("order_by_type")!=null){
								m_order_by_type = req.getParameter("order_by_type");
							}
							
        		out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        		out.println("   m_obj.focus();");
        		out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\" Repossession Details Report \";"); 
			  	out.println("}else{");
				out.println("help_box.innerHTML=\" Repossession Details Report - \"+m_val;"); 
			  	out.println("}");
				out.println("}");
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
			    out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			    out.println("    window.open(m_url); ");
			    out.println(" }");

			  	out.println("function load_details_deposit(deposit_no){");
				//out.println("alert('dePOSIT nO ** '+deposit_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_deposit_details&DEP_NO='+deposit_no;"); 
				out.println("window.open(m_url,'displayWindow4','left=150,top=250,width=800,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
			
				out.println("function load_details_receipt(receipt_no){");
				//out.println("alert('receipt no ** '+receipt_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+receipt_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
				
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
							
				out.println("document.Form1.m_hid_repos_no.value='"+m_repos_no+"'; ");
			
	      		//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&REPOSS_NO=\"+document.Form1.m_hid_repos_no.value;"); 				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&type="+_m_type+"&from_date="+_m_from_date+"&to_date="+_m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&REPOSS_NO="+m_repos_no+" \";");
				out.println(" window.location.href=m_url;"); 
				
				
				
				out.println("}");
			
        		out.println("</Script>");
				
				
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name=\"m_hid_repos_no\" value=\"\"></td>");

					/*out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=6 align=right></td>");
          out.println("<td colspan=7 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); ></td>");
          out.println("</tr>");
					out.println("</table>");*/
					
					out.println("<DIV STYLE='{position:absolute; top:40; left:0 cursor: hand;}'>");
 					int j = 0;      					
	        
					if(_m_type.equals("ALL")){
					
					if( !_m_from_date.equals("") && !_m_to_date.equals("") &&  !m_repos_no.equals("")  ){					
					
													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ // 10 mod by udara 03-09-2014
													*/

													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 // " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT,  "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
													" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
													" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
													" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
													" "+m_schema_name+".AF_CO_MAS_MODEL F "+
													" WHERE A.FINANCE_NO=B.FINANCE_NO "+
													" AND B.APPLICATION_NO=C.APPLICATION_NO "+
													" AND D.CLIENT_CODE=B.CLIENT_CODE "+
													" AND C.MODEL_CODE=F.MODEL_CODE "+ 
													" AND A.REPOSSESSION_NO='"+m_repos_no+"' "+
													" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+_m_from_date+"','DD-MM-YYYY')  "+
													" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+_m_to_date+"','DD-MM-YYYY')  "+
													" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

					
					
          }
					
					else if( !_m_from_date.equals("") && !_m_to_date.equals("") &&  m_repos_no.equals("")  ){					

													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ // 10 mod by udara 03-09-2014
													*/
													
													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT , "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
									" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
									" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
									" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
									" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
									" "+m_schema_name+".AF_CO_MAS_MODEL F "+
									" WHERE A.FINANCE_NO=B.FINANCE_NO "+
									" AND B.APPLICATION_NO=C.APPLICATION_NO "+
									" AND D.CLIENT_CODE=B.CLIENT_CODE "+
									" AND C.MODEL_CODE=F.MODEL_CODE "+ 
									" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+_m_from_date+"','DD-MM-YYYY')  "+
									" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+_m_to_date+"','DD-MM-YYYY')  "+
									" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

					}
					else if( _m_from_date.equals("") && _m_to_date.equals("") &&  m_repos_no.equals("")  ){					
					
													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ //10 // AND ROWNUM=1 added by udara 03-09-2014
													*/
													
													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT , "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
																	" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
																	" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																	" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
																	" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
																	" "+m_schema_name+".AF_CO_MAS_MODEL F "+
																	" WHERE A.FINANCE_NO=B.FINANCE_NO "+
																	" AND B.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND D.CLIENT_CODE=B.CLIENT_CODE "+
																	" AND C.MODEL_CODE=F.MODEL_CODE "+ 
																	" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");


					}
					
					// added by udara 20-10-2014
					
					else if ( _m_from_date.equals("") && _m_to_date.equals("") &&  !m_repos_no.equals("")  ){					
					
													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ //10 // AND ROWNUM=1 added by udara 03-09-2014
													*/
													
													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT , "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
																	" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
																	" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																	" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
																	" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
																	" "+m_schema_name+".AF_CO_MAS_MODEL F "+
																	" WHERE A.FINANCE_NO=B.FINANCE_NO "+
																	" AND B.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND D.CLIENT_CODE=B.CLIENT_CODE "+
																	" AND C.MODEL_CODE=F.MODEL_CODE "+ 
																	" AND A.REPOSSESSION_NO='"+m_repos_no+"' "+
																	" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");


					}
					
					// end by udara 20-10-2014
					
					
					
					}
					else if(_m_type.equals("SEIZER")){
					
					if( !_m_from_date.equals("") && !_m_to_date.equals("") &&  !_m_seizer_code.equals("")  ){			
					
					
													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ // 10 mod by udara 03-09-2014
													*/
													
													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT,  "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
													" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
													" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
													" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
													" "+m_schema_name+".AF_CO_MAS_MODEL F "+
													" WHERE A.FINANCE_NO=B.FINANCE_NO "+
													" AND B.APPLICATION_NO=C.APPLICATION_NO "+
													" AND D.CLIENT_CODE=B.CLIENT_CODE "+
													" AND C.MODEL_CODE=F.MODEL_CODE "+ 
													" AND A.SEIZER_CODE='"+_m_seizer_code+"' "+
													" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+_m_from_date+"','DD-MM-YYYY')  "+
													" AND TO_DATE(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+_m_to_date+"','DD-MM-YYYY')  "+
													" AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('"+m_repos_no+"%') "+
													" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
          
					}
					
					else if( _m_from_date.equals("") && _m_to_date.equals("") &&  !_m_seizer_code.equals("")  ){			
					
					
													rs = stmt.executeQuery (" SELECT "+
													" DISTINCT A.REPOSSESSION_NO REPOSSESSION_NO, "+ //1
													" A.FINANCE_NO FINANCE_NO, "+ //2
													" D.FULL_NAME FULL_NAME, "+  //3
													" NVL(C.VEHICLE_NO,'-') VEHICLE_NO, "+  //4
													" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+  //5
													" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+ //6
													" NVL(F.MODEL_CODE,'-') MODEL_CODE, "+ //7
													" NVL(F.MAKE_CODE,'-')  MAKE_CODE, "+  //8
													" NVL(F.FUEL_TYPE,'-')  FUEL_TYPE, "+  //9
													" /*E.OFFER_VALUE,*/ "+
													
													// commented by udara 27-10-2014
													/*
													"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
													//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
													" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1) VALUE, "+ // 10 mod by udara 03-09-2014
													*/
													
													" NVL("+m_schema_name+".AF_CO_GET_REPOS_VALUATION_VAL(B.APPLICATION_NO),0) VALUE, "+ // added by udara 27-10-2014
													
													" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
													" NVL( TO_CHAR(NVL(A.REPOSSESSED_DATE,A.ENT_DATE),'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ // 12 added by udara 27-10-2014 " NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
													" B.CLIENT_CODE CLIENT_CODE, "+
													" NVL(A.LETTER_RENEWAL_COUNT,0) LETTER_RENEWAL_COUNT , "+
													" NVL(A.INVOICE_AMOUNT,0) INVOICE_AMOUNT  "+
													" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
													" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
													" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
													" "+m_schema_name+".AF_CO_MAS_MODEL F "+
													" WHERE A.FINANCE_NO=B.FINANCE_NO "+
													" AND B.APPLICATION_NO=C.APPLICATION_NO "+
													" AND D.CLIENT_CODE=B.CLIENT_CODE "+
													" AND C.MODEL_CODE=F.MODEL_CODE "+ 
													" AND A.SEIZER_CODE='"+_m_seizer_code+"' "+
													" AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('"+m_repos_no+"%') "+
													" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
          
					}

          
					}
					
					
					
																				
				/*	if(m_repos_no.equals("")){						
																	
																	
					rs = stmt.executeQuery (" SELECT "+
					                        " DISTINCT A.REPOSSESSION_NO, "+ //1
																	" A.FINANCE_NO, "+ //2
																	" D.FULL_NAME, "+  //3
																	" NVL(C.VEHICLE_NO,'-'), "+  //4
																	" NVL(C.ENGINE_NO,'-'), "+  //5
																	" NVL(C.CHASSIS_NO,'-'), "+ //6
																	" NVL(F.MODEL_CODE,'-'), "+ //7
																	" NVL(F.MAKE_CODE,'-'), "+  //8
																	" NVL(F.FUEL_TYPE,'-'), "+  //9
																	"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
																	" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
																	" NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
																	" B.CLIENT_CODE "+
																	" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
																	" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																	" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
																	" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
																	" "+m_schema_name+".AF_CO_MAS_MODEL F "+
																	" WHERE A.FINANCE_NO=B.FINANCE_NO "+
																	" AND B.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND D.CLIENT_CODE=B.CLIENT_CODE "+
																	" AND C.MODEL_CODE=F.MODEL_CODE "+ 
																	" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
						}else{
						
							rs = stmt.executeQuery (" SELECT "+
					                        " DISTINCT A.REPOSSESSION_NO, "+ //1
																	" A.FINANCE_NO, "+ //2
																	" D.FULL_NAME, "+  //3
																	" NVL(C.VEHICLE_NO,'-'), "+  //4
																	" NVL(C.ENGINE_NO,'-'), "+  //5
																	" NVL(C.CHASSIS_NO,'-'), "+ //6
																	" NVL(F.MODEL_CODE,'-'), "+ //7
																	" NVL(F.MAKE_CODE,'-'), "+  //8
																	" NVL(F.FUEL_TYPE,'-'), "+  //9
																	"(SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+ //10
																	" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+ //11
																	" NVL( TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YYYY'),'-') REPOSSESSED_DATE, "+ //12
																	" B.CLIENT_CODE "+
																	" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
																	" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																	" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
																	" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
																	" "+m_schema_name+".AF_CO_MAS_MODEL F "+
																	" WHERE A.FINANCE_NO=B.FINANCE_NO "+
																	" AND B.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND D.CLIENT_CODE=B.CLIENT_CODE "+
																	" AND C.MODEL_CODE=F.MODEL_CODE "+
																	" AND A.REPOSSESSION_NO='"+m_repos_no+"' "+
																	" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
						
						}											
							*/										
								 out.println("<table class=table border='0' width='1525' >");	
              while(rs.next()){

								if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
	}

                  out.println("<td width='100'  style= cursor:hand; onclick=\"show_repossession_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1) +"</u></td>");
				  // out.println("<td width='100'  style= cursor:hand; onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\" ><U>"+rs.getString(2) +"</U></td>"); // commented by udara 27-10-2014
                  out.println("<td width='100'  style= cursor:hand; onclick=\"show_transaction_info('"+rs.getString(13)+"','"+rs.getString(2)+"')\" ><U>"+rs.getString(2) +"</U></td>"); // added by udara 27-10-2014		
				  out.println("<td width='150'  style= cursor:hand; onclick=\"show_client('"+rs.getString(13)+"')\" ><U>"+rs.getString(3) +"</U></td>");
                  out.println("<td width='100'  >"+rs.getString(4)+"</td>");
                  out.println("<td width='100'  >"+rs.getString(5)+"</td>");
                  out.println("<td width='100'  >"+rs.getString(6)+"</td>"); 
							    out.println("<td width='80'  STYLE='{cursor:hand;}' onclick=\"show_model_details_drill('"+rs.getString(7)+"')\"><u>"+rs.getString(7)+"</u></td>");
									out.println("<td width='100'  STYLE='{cursor:hand;}' onclick=\"show_make_details_drill('"+rs.getString(8)+"')\"><u>"+rs.getString(8)+"</u></td>");
									out.println("<td width='80'   >"+rs.getString(9)+"</td>");
									out.println("<td width='100'  align='right' >"+nf.format(rs.getDouble(10))+"</td>");
									out.println("<td width='100'  >"+rs.getString(11)+"</td>");
									out.println("<td width='100'  >"+rs.getString(12)+"</td>");
									out.println("<td width='100'  >"+rs.getString(14)+"</td>");
									out.println("<td width='100'  align='right'>"+nf.format(rs.getDouble(15))+"</td>");
         					out.println("</tr>");
                	j=j+1;
              } 
          
					out.println("</table>");
					out.println("</DIV>");
					
					out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   			  out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1525; height: 15'>");
					out.println("<table class=table border='0' width='1525' >");
          out.println("<tr class=pdn_txtpos2 >");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Repossession No'           onclick=sort_data('REPOSSESSION_NO') >Repossession No</td>");
          out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Finance No'                onclick=sort_data('FINANCE_NO') >Finance No</td>");
          out.println("<td  width='150'  style= cursor:hand; title='Click here to sort by - Client Name'               onclick=sort_data('FULL_NAME') >Client Name</td>");
          out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Vehicle No'                onclick=sort_data('VEHICLE_NO') >Vehicle No</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Engine No'                 onclick=sort_data('ENGINE_NO')>Engine No</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Chassis No'                onclick=sort_data('CHASSIS_NO')>Chassis No</td>");
					out.println("<td  width='80'  style= cursor:hand; title='Click here to sort by -  Model Name'                onclick=sort_data('MODEL_CODE')>Model Name</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Make '                     onclick=sort_data('MAKE_CODE')>Make</td>");
					out.println("<td  width='80'  style= cursor:hand; title='Click here to sort by - Branch Code'                onclick=sort_data('FULL_NAME') >Fuel Type</td>");
					out.println("<td  width='100' align='right'  style= cursor:hand; title='Valuation Value'                     onclick=sort_data('VALUE')>Valuation Value</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Seizer Name'               onclick=sort_data('FULL_NAME') >Seizer Name</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Repossessed Date'          onclick=sort_data('REPOSSESSED_DATE')>Repossessed Date</td>");
					out.println("<td  width='100'  style= cursor:hand; title='Click here to sort by - Renewal Time'              onclick=sort_data('LETTER_RENEWAL_COUNT')>Renewal Time</td>");
					out.println("<td  width='100'  align='right' style= cursor:hand; title='Click here to sort by - Amount'                    onclick=sort_data('LETTER_RENEWAL_COUNT')>Amount</td>");
					out.println("</tr>");
					out.println("</table>"); 
					out.println("</div>"); 
			    out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
					
					/*out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=6 align=right></td>");
          out.println("<td align=right colspan=7><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b); ></td>");
          out.println("</tr></table>");*/


				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("</html>");
      }
			
			//=========================================================================================================================			
			
  		/*else {
				out.println("Undefined");
			}
			*/
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
