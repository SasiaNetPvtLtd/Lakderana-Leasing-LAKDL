
//--
//SCREEN NAME:FOLLOW UP
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_REP_display_follow_up extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Follow Up Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("}");
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_follow_up&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ACTION_TOBE_TAKEN.value==\"\"){  "); 
			out.println("DIV_TXT_ACTION_TOBE_TAKEN.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SCREEN_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_SCREEN_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DIVISION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_DIVISION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ENT_REMARKS.value==\"\"){  "); 
			out.println("DIV_TXT_ENT_REMARKS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			/*out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up';"); 
			out.println("		}"); 
			out.println("}"); */ 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up';"); 
			out.println("		}"); 
			out.println("}"); 
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_follow_up\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Follow Up Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Follow Up Report  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_TOBE_TAKEN.disabled=true;"); 
		//out.println("document.Form1.TXT_EFF_VAL_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_TAKEN.disabled=true;"); 
		//out.println("document.Form1.TXT_ACTION_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_SET_FOR.disabled=true;"); 
			out.println("document.Form1.TXT_SCREEN_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ENT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_ENT_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_PRIORITY.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
		//out.println("document.Form1.hid_status.value=\"New\";"); 
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
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}");
			//Added by Dineth on 2008-10-10
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_assigned_by_assign_10();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"20\"){"); 
			out.println("		help_assigned_to_assign_20();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"30\"){"); 
			out.println("		help_division_assign_30();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"40\"){"); 
			out.println("		help_sub_division_assign_40();"); 
	  	out.println("		}"); 
			//End by Dineth on 2008-10-10
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_screen_name_assign();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_div_code_assign();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_action_set_assign();"); 
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
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_ID_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ID_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ID_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','6');"); 
			out.println("}"); 
			
			
			//Added by Dineth on 2008-10-10
			out.println("function help_assigned_by() {"); 
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_ASSIGNED_BY_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ASSIGNED_BY.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ASSIGNED_BY.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_assigned_by_assign_10() {");
			out.println("    document.Form1.TXT_ASSIGNED_BY.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_assigned_to() {"); 
			out.println("    document.Form1.hid_help_type.value=\"20\";"); 
			out.println("    m_sql = \"m_help_TXT_ASSIGNED_TO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ASSIGNED_TO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ASSIGNED_TO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_assigned_to_assign_20() {");
			out.println("    document.Form1.TXT_ASSIGNED_TO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_division() {"); 
			out.println("    document.Form1.hid_help_type.value=\"30\";"); 
			out.println("    m_sql = \"m_help_TXT_DIVISION_CODE_sql1\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_division_assign_30() {");
			out.println("    document.Form1.TXT_DIVISION.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_sub_division() {"); 
			out.println("    document.Form1.hid_help_type.value=\"40\";"); 
			out.println("    m_sql = \"m_help_TXT_SUB_DIVISION_sql1\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_sub_division_assign_40() {");
			out.println("    document.Form1.TXT_SUB_DIVISION.value=oBj.valout[2];"); 
			out.println("}");
			
			//End by Dineth on 2008-10-10
			
			out.println("function help_scr_name() {"); 
		  out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_SCREEN_NAME_sql_1\";"); 
			out.println("    m_criteria = document.Form1.TXT_SCREEN_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			


      out.println("function help_screen_name_assign() {"); 
			out.println("    document.Form1.TXT_SCREEN_NAME.value=oBj.valout[2];"); 
			out.println("}");


      out.println("function help_div_code() {"); 
		  out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_DIVISION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DIVISION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");


      out.println("function help_div_code_assign() {"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[2];"); 
			out.println("}");
      
			out.println("function help_action_set() {");
		  out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_ACTION_SET_FOR_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ACTION_SET_FOR.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");

  
			
			out.println("function help_action_set_assign() {");
			out.println("    document.Form1.TXT_ACTION_SET_FOR.value=oBj.valout[3];"); 
			out.println("}");


			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[9];"); 
			out.println("}");
			
			
			
			out.println("function befor_close(){");
			out.println(" if(confirm(\"Are You Sure?\")){  ");
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_CO_FollowupAlert?chksql=main_page';"); 
			out.println("		}"); 
		//	LAKDL_AF_CO_FollowupAlert?chksql=main_page
		//out.println("  Form1.reset() ;  ");
		//	out.println(" }  ");
			out.println("}");
			
			
			// Added by Udara Viruwan on 14 / 10 / 2009
			out.println("function validate_date_us(){");
			out.println("  m_from_dd = document.Form1.TXT_EFF_VAL_DD.value ");
   		out.println("  m_from_mm = document.Form1.TXT_EFF_VAL_MM.value ");
   		out.println("  m_from_yy = document.Form1.TXT_EFF_VAL_YY.value ");
   		out.println("  m_to_dd = document.Form1.TXT_EFF_VAL_TO_DD.value ");
   		out.println("  m_to_mm = document.Form1.TXT_EFF_VAL_TO_MM.value ");
   		out.println("  m_to_yy = document.Form1.TXT_EFF_VAL_TO_YY.value ");
   		out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
   		out.println("    if(!checkMonthLength(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)){  "); 
   		out.println("     return false;"); 
   		out.println("     }");
   		out.println("    else {");
   		out.println("    if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
   		out.println("        if(checkMonthLength(document.Form1.TXT_EFF_VAL_TO_DD,document.Form1.TXT_EFF_VAL_TO_MM,document.Form1.TXT_EFF_VAL_TO_YY)){  "); 
   		out.println("         return true;"); 
   		out.println("       }");
   		out.println("        else "); 
   		out.println("         return false; "); 
   		out.println("     }");
   		out.println("   else {");
   		out.println("     alert('To Date cannot be null ')");
   		out.println("     return false;"); 
   		out.println("     }");
   		out.println("    }");
   		out.println("  }");
   		out.println(" else { ");
   		out.println("   alert('From Date cannot be null ')");
   		out.println("   return false;"); 
   		out.println("  }");
			out.println("}"); 
			
			
			
			out.println("function view_report() {");
			
			out.println("if(validate_date_us()==true){"); // if
			//out.println("validate_dade_us();"); 
			out.println("  obj1=document.Form1.TXT_ID_NO ;");
			out.println("  obj2=document.Form1.TXT_STATUS ;");
			
			out.println("  obj3=document.Form1.TXT_EFF_VAL_DD ;");
			out.println("  obj4=document.Form1.TXT_EFF_VAL_MM ;");
			out.println("  obj5=document.Form1.TXT_EFF_VAL_YY ;");
			
			out.println("  obj6=document.Form1.TXT_EFF_VAL_TO_DD ;");
			out.println("  obj7=document.Form1.TXT_EFF_VAL_TO_MM ;");
			out.println("  obj8=document.Form1.TXT_EFF_VAL_TO_YY ;");
			
			out.println("  obj9=document.Form1.TXT_ACT_DATE_DD ;");
			out.println("  obj10=document.Form1.TXT_ACT_DATE_MM ;");
			out.println("  obj11=document.Form1.TXT_ACT_DATE_YY ;");
			
			//Added by Dineth on 2008-10-10
			
			out.println("  obj12=document.Form1.TXT_ASSIGNED_BY;");
			out.println("  obj13=document.Form1.TXT_ASSIGNED_TO;");
			out.println("  obj14=document.Form1.TXT_DIVISION;");
			out.println("  obj15=document.Form1.TXT_SUB_DIVISION;");
			
			//End by Dineth on 2008-10-10
			
		   out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_REP_display_follow_up_report?chksql=MAIN&ID_NUM=\"+obj1.value+\"&STATUS=\"+obj2.value+\""+      
		   "&EFF_FROM_DATE=\"+obj3.value+\"-\"+obj4.value+\"-\"+obj5.value+\""+
		   "&EFF_TO_DATE=\"+obj6.value+\"-\"+obj7.value+\"-\"+obj8.value+\""+
		   "&ACT_DATE=\"+obj9.value+\"-\"+obj10.value+\"-\"+obj11.value+\"&ASSIGNED_BY=\"+obj12.value+\"&ASSIGNED_TO=\"+obj13.value+\"&DIVISION=\"+obj14.value+\"&SUB_DIVISION=\"+obj15.value+\"&sort_column=A.FOLLOW_UP_NO&order_by_type=DESC \" ");
		   out.println(" popupwin= window.open(m_url,'displayWindow2','left=50,top=230,width=930,height=380,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
		   out.println("}"); // if
		   out.println("}");	// End
			
			
			
			// Added function by Udara Somathilake on 14-10-2010 for the View As Date Report Button (Under Action Date)
			
			out.println("function view_report_as_action_date() {");
			//out.println("		if(validate_date_us()==true){"); // if 
			out.println("			if(document.Form1.TXT_ACT_DATE_DD.value != \"\" || document.Form1.TXT_ACT_DATE_MM.value != \"\" || document.Form1.TXT_ACT_DATE_YY.value != \"\" ){");
			out.println("  					obj1=document.Form1.TXT_ID_NO ;");
			out.println("  					obj2=document.Form1.TXT_STATUS ;");
			
			out.println("  					obj3=document.Form1.TXT_EFF_VAL_DD ;");
			out.println("  					obj4=document.Form1.TXT_EFF_VAL_MM ;");
			out.println("  					obj5=document.Form1.TXT_EFF_VAL_YY ;");
			
			out.println("  					obj6=document.Form1.TXT_EFF_VAL_TO_DD ;");
			out.println("  					obj7=document.Form1.TXT_EFF_VAL_TO_MM ;");
			out.println("  					obj8=document.Form1.TXT_EFF_VAL_TO_YY ;");
			
			out.println("  					obj9=document.Form1.TXT_ACT_DATE_DD ;");
			out.println("  					obj10=document.Form1.TXT_ACT_DATE_MM ;");
			out.println("  					obj11=document.Form1.TXT_ACT_DATE_YY ;");
			
			out.println("  					obj12=document.Form1.TXT_ASSIGNED_BY;");
			out.println("  					obj13=document.Form1.TXT_ASSIGNED_TO;");
			out.println("  					obj14=document.Form1.TXT_DIVISION;");
			out.println("  					obj15=document.Form1.TXT_SUB_DIVISION;");
			
		   out.println("    					m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_REP_display_follow_up_report_view_as_action_date?chksql=MAIN"+
													"&ID_NUM=\"+obj1.value+\""+
													"&STATUS=\"+obj2.value+\""+      
												   "&EFF_FROM_DATE=\"+obj3.value+\"-\"+obj4.value+\"-\"+obj5.value+\""+
												   "&EFF_TO_DATE=\"+obj6.value+\"-\"+obj7.value+\"-\"+obj8.value+\""+
												   "&ACT_DATE=\"+obj9.value+\"-\"+obj10.value+\"-\"+obj11.value+\""+
													"&ASSIGNED_BY=\"+obj12.value+\""+
													"&ASSIGNED_TO=\"+obj13.value+\""+
													"&DIVISION=\"+obj14.value+\""+
													"&SUB_DIVISION=\"+obj15.value+\""+
													"&sort_column=A.FOLLOW_UP_NO"+
													"&order_by_type=DESC \" ");
			
		   out.println(" 						popupwin= window.open(m_url,'displayWindow2','left=50,top=230,width=930,height=380,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
		   
			out.println("			}"); // if validate action date
			
			out.println("			else{");
			out.println("					alert('Enter the Action Date to proceed')");
			//out.println("              document.Form1.TXT_ACT_DATE_YY.focus();   ");
			out.println("			}"); // else validate action date
			
			//out.println("		}"); // if validate date
		   out.println("}");	// End Function
		
			// End Function by Udara
						
			
			out.println("function val_eff_date(){ ");
			out.println("if((document.Form1.TXT_EFF_VAL_DD.value !=\"\")&&(document.Form1.TXT_EFF_VAL_MM.value !=\"\")&&(document.Form1.TXT_EFF_VAL_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY);");
			out.println("}");
			out.println("}");
			
      
			out.println("function val_eff_to_date(){ ");
			out.println("if((document.Form1.TXT_EFF_VAL_TO_DD.value !=\"\")&&(document.Form1.TXT_EFF_VAL_TO_MM.value !=\"\")&&(document.Form1.TXT_EFF_VAL_TO_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_EFF_VAL_TO_DD,document.Form1.TXT_EFF_VAL_TO_MM,document.Form1.TXT_EFF_VAL_TO_YY);");
			out.println("}");
			out.println("}");
			
			
			out.println("function val_act_date(){ ");
			out.println("if((document.Form1.TXT_ACTION_DD.value !=\"\")&&(document.Form1.TXT_ACTION_MM.value !=\"\")&&(document.Form1.TXT_ACTION_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_ACTION_DD,document.Form1.TXT_ACTION_MM,document.Form1.TXT_ACTION_YY);");
			out.println("}");
			out.println("}");
			
			out.println("function chk_eff_date() { ");
			out.println("if((document.Form1.TXT_EFF_VAL_DD.value ==\"\")||(document.Form1.TXT_EFF_VAL_MM.value ==\"\")||(document.Form1.TXT_EFF_VAL_YY.value ==\"\")){");
      out.println("alert('Please Enter From Date');");
			out.println("document.Form1.TXT_EFF_VAL_DD.focus();");
     	out.println("}");
			out.println("}");
			
	
			
			
			out.println("function chk_validity(){  ");			
      out.println("if((parseInt(document.Form1.TXT_EFF_VAL_DD.value))>=(parseInt(document.Form1.TXT_EFF_VAL_TO_DD.value))){");
      out.println("if((parseInt(document.Form1.TXT_EFF_VAL_MM.value))<=(parseInt(document.Form1.TXT_EFF_VAL_TO_MM.value))){");
      out.println("if((parseInt(document.Form1.TXT_EFF_VAL_YY.value))<=(parseInt(document.Form1.TXT_EFF_VAL_TO_YY.value))){");
      out.println(" if(((parseInt(document.Form1.TXT_EFF_VAL_DD.value))==(parseInt(document.Form1.TXT_EFF_VAL_TO_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_EFF_VAL_MM.value))==(parseInt(document.Form1.TXT_EFF_VAL_TO_MM.value)))&&");
      out.println("((parseInt(document.Form1.TXT_EFF_VAL_YY.value))==(parseInt(document.Form1.TXT_EFF_VAL_TO_YY.value)))){");
      out.println("}");
      out.println("else if(((parseInt(document.Form1.TXT_EFF_VAL_DD.value))>(parseInt(document.Form1.TXT_EFF_VAL_TO_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_EFF_VAL_MM.value))==(parseInt(document.Form1.TXT_EFF_VAL_TO_MM.value)))&&");
      out.println(" ((parseInt(document.Form1.TXT_EFF_VAL_YY.value))==(parseInt(document.Form1.TXT_EFF_VAL_TO_YY.value)))){");
      out.println("      alert('To Date should be greater than From Date');");
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println(" alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseInt(document.Form1.TXT_EFF_VAL_YY.value))>=(parseInt(document.Form1.TXT_EFF_VAL_TO_YY.value))){");
      out.println("    alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(document.Form1.TXT_EFF_VAL_MM.value))<=(parseInt(document.Form1.TXT_EFF_VAL_TO_MM.value))){");
      out.println("  if((document.Form1.TXT_EFF_VAL_YY.value)<=(document.Form1.TXT_EFF_VAL_TO_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("   alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(document.Form1.TXT_EFF_VAL_YY.value))<(parseInt(document.Form1.TXT_EFF_VAL_TO_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("  alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("document.Form1.TXT_EFF_VAL_TO_DD.focus();");
			out.println("return true;");
      out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=340,height=230\");"); 
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
			out.println("     document.Form1.TXT_EFF_VAL_DD.value=v_dd;");
			out.println("     document.Form1.TXT_EFF_VAL_MM.value=v_mm;");
			out.println("     document.Form1.TXT_EFF_VAL_YY.value=v_yy;");
			out.println("  }else");			
			 out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_EFF_VAL_TO_DD.value=v_dd;");
			out.println("     document.Form1.TXT_EFF_VAL_TO_MM.value=v_mm;");
			out.println("     document.Form1.TXT_EFF_VAL_TO_YY.value=v_yy;");
			out.println("  }else");
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_ACT_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ACT_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ACT_DATE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");			
			
			
			
     
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">");  //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Follow Up Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>");
			
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
		//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
		//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");
		//out.println("<td width='6%'></td>");  
		//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
		//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onClick='close_window()' value=\"Close\"></td>");
			
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


		
		   out.println("<table class='table' border='0'> ");
			out.println("<tr >");
			out.println("<td width='20%' >ID Number </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"?\" onClick=\"help_update()\"></td>");
		   out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
     	   out.println("</tr>"); 
	
			out.println("<tr>");
			out.println("<td class=div_input>Effective From Date</td>");
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("<td class=div_input align=\"left\"  >To Date </td>");
			
			
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_TO_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_TO_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_TO_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			out.println("</td> ");
			
			out.println("<td></td>");
	  	   out.println("</tr>"); 
		
			 
   
	      out.println("<tr >");
			out.println("<td class=div_input>Action Date</td>");
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_ACT_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"checkMonthLength(TXT_ACT_DATE_DD,TXT_ACT_DATE_MM,TXT_ACT_DATE_YY);\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ACT_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"checkMonthLength(TXT_ACT_DATE_DD,TXT_ACT_DATE_MM,TXT_ACT_DATE_YY);\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ACT_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"checkMonthLength(TXT_ACT_DATE_DD,TXT_ACT_DATE_MM,TXT_ACT_DATE_YY);\"><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");	
			out.println("</td> ");
			
			
			out.println("<td ></td>");
			out.println("<td ></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");
	
			out.println("<tr >");
			out.println("<td>Status </td>");
			out.println("<td><select class='txt_input' type='text' name='TXT_STATUS' maxlength='1' size='1'>");
			out.println("<option value='ALL' selected> All </option>");
			out.println("<option value='COMPLETED' > Completed</option>");
			out.println("<option value='INPROGRESS' > Inprogress </option>");
			out.println("<option value='PENDING' > Pending </option>"); // modified by Udara Somathilake on 14-10-2010
			out.println("</select></td>");	
			out.println("<td width='*%' align=\"center\"></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");
		   //	out.println("</table>");
			//Added by Dineth on 2008-10-10
			
			out.println("<tr >");
			out.println("<td width='20%' >Assigned By </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ASSIGNED_BY' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGNED_BY' value=\"?\" onClick=\"help_assigned_by()\"></td>");
		   out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
     	   out.println("</tr>"); 
			
			
			out.println("<tr >");
			out.println("<td width='20%' >Assigned To </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ASSIGNED_TO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGNED_TO' value=\"?\" onClick=\"help_assigned_to()\"></td>");
		   out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
     	   out.println("</tr>"); 
			
			
			out.println("<tr >");
			out.println("<td width='20%' >Division</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DIVISION' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_DIVISION' value=\"?\" onClick=\"help_division()\"></td>");
		   out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
     	   out.println("</tr>");
				
		   out.println("<tr >");
			out.println("<td width='20%' >Sub Division</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_DIVISION' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_SUB_DIVISION' value=\"?\" onClick=\"help_sub_division()\">  <input type=\"button\" class='but_input' onClick='view_report()' value=\"View\">  <input type=\"button\" class='but_input' style='width:100px' onClick='view_report_as_action_date()' value=\"View As Date\">   </td>"); // modified by Udara Somathilake on 14-10-2010
			//out.println("<td width='20%'><input type=\"button\" class='mainbut' onClick='view_report()' value=\"View\"></td>");
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
     	   out.println("</tr>");
         //End by Dineth on 2008-10-10
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
