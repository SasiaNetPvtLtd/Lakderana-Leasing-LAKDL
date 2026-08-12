import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_System_reports_vehicle_category extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		Statement stmt=null;
		ResultSet rs=null;
		Connection conn=null;
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods  m_sn_methods = new LAKDL_AF_CO_conn_methods ();
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_code="";
			String m_close_status="x";
			m_code= req.getParameter("code");
			m_close_status= req.getParameter("CLSTATUS"); /*Added by Chandana on 16/11/2007*/
			if(m_close_status==null){
			m_close_status="x";
			}
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){
				
			
					stmt = conn.createStatement ();
					rs= stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
								
					 
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>System Administration - Vehicle Category</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("var enter_text=0");//**
					out.println("var enter_text1=0");
					out.println("var item_sub_arry=new Array();");
					out.println("var item_arry=new Array();");
					out.println("var dist_arry=new Array();");
					out.println("var bttn_pres;");
					out.println("var bttn_pres1;");
					out.println("var alert_msg;");
					
					out.println("function load_sysdate(){	"); 
					if(rs.next()){
							out.println("document.Form1.TXT_EFFECTIVE_DATE_DD.value='"+rs.getString(1)+"';");
							out.println("document.Form1.TXT_EFFECTIVE_DATE_MM.value='"+rs.getString(2)+"';");
							out.println("document.Form1.TXT_EFFECTIVE_DATE_YY.value='"+rs.getString(3)+"';");
							
					}
					out.println("}"); 
		
					out.println("function get_vector(data_vec) {");
					out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
					out.println("				alert('Record already exists');");
					//out.println("				new_window();");
					out.println("			}");
					out.println("			else if(data_vec.length>0  &&  document.Form1.hid_val.value=='t2'){");
					out.println("              if(data_vec[0]>0){ ");
					out.println("                 arr_size_bank = parseInt(data_vec[0]); ");
					out.println("                 lineno_bank   = parseInt(data_vec[0]); ");
					out.println("                 load_dynamic(); ");
					out.println("              }");
					out.println("           }");
					out.println("}");
					
					out.println("function assig(val) {");
					out.println("   document.Form1.hid_val.value=val;");
					out.println("}");
		
					
					out.println("function makeRequest(obj) {");
					out.println("   if(document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_vehicle_category_code&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
					out.println("      load_interface(m_url,'XML');");
					out.println("   }");
					out.println("}");
		
		
		
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
					out.println("if(document.Form1.TXT_VEHICLE_CATEGORY.value==\"\"){  "); 
					out.println("DIV_TXT_VEHICLE_CATEGORY.style.color='red';");
					//out.println("alert('Enter Item Sub Category Code..!')");
					out.println("document.Form1.TXT_VEHICLE_CATEGORY.focus()");
					out.println("return false;"); 
					out.println("}"); 
					out.println("else if(document.Form1.TXT_VEHICLE_CATEGORY_DESCRIPTION.value==\"\"){  "); 
					out.println("DIV_TXT_VEHICLE_CATEGORY_DESCRIPTION.style.color='red';");
					//out.println("alert('Enter Item Sub Category Description..!')");
					out.println("document.Form1.TXT_VEHICLE_CATEGORY_DESCRIPTION.focus()");
		
					out.println("return false;"); 
					out.println("}"); 
		
					out.println("else{");
			
			        //out.println(" alert(arr_size_bank); ");
					
					out.println("  m_found = 0; ");
					out.println("  for(i=0; i<arr_size_bank; i++){ ");
					
					out.println("       m_item_sub_cat=\"TXT_ITEM_SUB_CAT\"+i");
					out.println("       m_item_cat=\"TXT_ITEM_CAT_CODE\"+i");
					
					out.println("       if(document.Form1.elements[m_item_sub_cat].value=='' || document.Form1.elements[m_item_sub_cat].value=='-'){");
					out.println("           m_found = m_found + 1;"); 
					out.println("       }"); 
					
					out.println("       if(document.Form1.elements[m_item_cat].value=='' || document.Form1.elements[m_item_cat].value=='-'){");
					out.println("           m_found = m_found + 1;"); 
					out.println("       }");
					
					out.println("  }"); 
					
					out.println("       if(m_found>0){");
					out.println("          alert('Cannot keep blank values');"); 
					out.println("          return false;"); 
					out.println("       }");
					out.println("       else{");
					out.println("          return true;"); 
					out.println("       }");
					
					//out.println("return true;"); 
					
					out.println("}"); 
					out.println("}"); 
		
					out.println("function before_submit(){ ");
					out.println("		if( checkMonthLength(document.Form1.TXT_EFFECTIVE_DATE_DD,document.Form1.TXT_EFFECTIVE_DATE_MM,document.Form1.TXT_EFFECTIVE_DATE_YY)){"); // added by Chatura Jayawardena
					out.println("   if(validate_data()){"); 
					out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
					out.println("		if(validate_data()){");
					out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
					out.println("document.Form1.elements[i].disabled=false;");
					out.println("}");
					
					
					out.println("       document.Form1.element_size.value = arr_size_bank; ");
					out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_Save_system_reports_vehicle_category';");  
					out.println("		document.Form1.submit();	"); 
					out.println("		}"); 
					out.println("		}"); 
					out.println("		}"); 
							out.println("else{");
					out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
					out.println("} "); 
		
					out.println("}} "); 
		
					out.println("function load_lock(){	"); 
					out.println(" if('"+m_close_status+"'=='B'){");
					out.println("document.Form1.hid_close_status.value='Y'");
					out.println("}"); 
					//out.println("document.oncontextmenu=new Function(\"return false\");"); 
					out.println("}	"); 
		
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					
					out.println("if(document.Form1.hid_close_status.value=='Y'){");			
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_vehicle_category?chksql=main_page';"); 
					out.println("}else {"); 			
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_vehicle_category?chksql=main_page';"); 
					out.println("}"); 
					
					//out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_System_reports_vehicle_category';"); 
					out.println("		}"); 
					out.println("}"); 
		
					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_vehicle_category?chksql=main_page';");
					out.println("}"); 
		
		
					out.println("function save_window(){	"); 
					out.println("  before_submit();"); 
					out.println("}"); 
					
					out.println(""); 
		
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_LAKDL_AF_System_reports_vehicle_category\";"); 
					out.println("    HelpBox_msg(m_help_message);"); 
					out.println("}"); 	
					out.println("function HelpBox_msg(m_help_message) {"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
					out.println("  \"&help_message_in=\"+m_help_message);"); 
					out.println("}"); 
		
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" System Administration - Vehicle Category - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" System Administration - Vehicle Category - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
		
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}"); 
					out.println("else if(m_val!=\"EDIT\"){"); 			
					out.println("}"); 
					out.println("else{");
					out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
					
					out.println("document.Form1.TXT_VEHICLE_CATEGORY.disabled=true;");
					
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_save.value=\"Save\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");
					//out.println("document.Form1.TXT_ITEM_SUB_CAT.disabled=true;");
					out.println("document.Form1.TXT_VEHICLE_CATEGORY.disabled=false;");
					out.println("document.Form1.hid_save.value=\"Modify\";");
					
					out.println("}else if(m_val==\"DACT\"){");  
					out.println("document.Form1.hid_status.value=\"Deactivate\";");  
					out.println("document.Form1.hid_save.value=\"Deactivate\";");
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");
					out.println("document.Form1.hid_save.value=\"Reactivate\";");
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
					out.println("		if(document.Form1.hid_help_type.value==\"11\"){"); 
					out.println("		help_update_value_assign_vehicle_category();"); 
			  	    out.println("		}"); 
					out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
					out.println("		help_update_value_assign_99();"); 
			  	    out.println("		}"); 
					out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
					//out.println("		help_update_value_assign_99();"); 
			  	    out.println("		}"); 
		
					out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
					out.println("		help_value_assign_1();"); 
			  	    out.println("		}"); 
					out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
					out.println("		help_value_assign_2();"); 
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
					out.println("	clear_data1();");
					out.println("	}");
					out.println("}");
					out.println("if(oBj.valout[2]==' '){");//**
					//out.println("Close();"); 
					
					out.println("	}	"); 
					out.println("}"); 
					
					
					out.println(" function Close(){");//**
					out.println("clear_data()	");
				    out.println("window.close();");
				    out.println(" }");
				
					out.println("function clear_data1() {");//**
					out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
					out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
					out.println("}");
					
					out.println("function clear_data() {");
					out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
					out.println("          document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
					out.println("          document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
					out.println("          document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
					out.println("       }");
					out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
					out.println("          document.Form1.TXT_ITEM_CAT_CODE.value='';");
					out.println("          document.Form1.TXT_ITEM_CAT_CODE.focus();"); 
					out.println("       }");
					out.println("		if(document.Form1.hid_help_type.value==\"11\"){"); 
					out.println("          document.Form1.TXT_ITEM_SUB_CAT.value='';");
					out.println("          document.Form1.TXT_VEHICLE_CATEGORY_DESCRIPTION.value='';"); 
					out.println("       }");			
					out.println("}");
		
					out.println("function Prev(Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function Next (Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 
					out.println("function help_button_1() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_1() {"); 
					out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
					out.println("}"); 
		
					
					out.println("function help_update_vehicle_category() {"); 
					out.println("bttn_pres=1");
					out.println("    document.Form1.hid_help_type.value=\"11\";"); 
					out.println("    m_sql = \"m_help_TXT_VEHICLE_CATEGORY_sql\";");
					out.println("    m_criteria = document.Form1.TXT_VEHICLE_CATEGORY.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
					out.println("function help_update_value_assign_vehicle_category() {"); 
					out.println("    document.Form1.TXT_VEHICLE_CATEGORY.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_VEHICLE_CATEGORY_DESCRIPTION.value=oBj.valout[3];"); 
					out.println("    bttn_pres=0");
					out.println("    enter_text=0");
					//out.println("    load_dynamic(); ");
					out.println("    load_dynamic_count(); ");
					out.println("}"); 
					
		
					out.println(" var help_row = 0;    ");
					
					out.println("function help_update(row) {"); 
					//out.println("    bttn_pres=1");
					out.println("    help_row=row; ");
					
					out.println("    m_item_sub_cat=\"TXT_ITEM_SUB_CAT\"+help_row");
					
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					//out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";");
					out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_VC_sql\";"); 
					
					//out.println("    alert(document.Form1.elements[m_item_sub_cat].value+'==='+help_row); ");
					
					out.println("    m_criteria = document.Form1.elements[m_item_sub_cat].value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
		
		
					
					out.println("function help_update_desc() {"); 
					out.println("    document.Form1.hid_help_type.value=\"100\";"); 
					out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_sql\";");
					out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
		
					out.println("function help_update_value_assign_99() {"); 
					//out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
					//out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[4];"); 
					//out.println("    bttn_pres=0");
					//out.println("    enter_text=0");
					
					
					out.println("      m_item_sub_cat=\"TXT_ITEM_SUB_CAT\"+help_row");
					out.println("      m_item_cat=\"TXT_ITEM_CAT_CODE\"+help_row");
					
					out.println("      m_item_sub_cat_desc=\"DIV_TXT_ITEM_SUB_CAT\"+help_row");
					out.println("      m_item_cat_desc=\"DIV_TXT_ITEM_CAT_CODE\"+help_row");
					
					out.println("      document.Form1.elements[m_item_sub_cat].value = oBj.valout[2];");
					out.println("      document.Form1.elements[m_item_cat].value = oBj.valout[4];");
					
					out.println("      document.getElementById(m_item_sub_cat_desc).innerHTML = oBj.valout[3];");
					out.println("      document.getElementById(m_item_cat_desc).innerHTML = oBj.valout[10];");
					
					out.println("}"); 
					
					out.println("function HelpView(Start,End,Hid_No,Max) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
					out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
					out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
					out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
					out.println("	"); 
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
					//out.println("		help_update_value_assign_99();"); 
			  	    out.println("		}"); 
					out.println("	}"); 
					out.println("	else{"); 
					out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("		return false;"); 
					out.println("	} "); 
					out.println("	}"); 
					out.println("	else{	"); 
					out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("	}	"); 
					out.println("	}		"); 
					out.println("	}	"); 
					out.println("}"); 
					out.println("");
					
					out.println("function ViewPrev(Start,End,Hid_No){"); 
					out.println("    HelpView(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function ViewNext(Start,End,Hid_No){"); 
					out.println("    HelpView(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println("");
					
					
					out.println("function View_all(){");	
					out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_1\";");
					out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
					out.println("    HelpView('1',50,'0');"); 
					out.println("}"); 
					
					out.println("function check_item_sub() {");
				 	out.println("var keyChar=window.event.keyCode;");
					out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
					out.println("{	");	
					out.println("window.event.keyCode=keyChar;	");	
					out.println("}	");	
					out.println("else	");	
					out.println("{	");	
					out.println("enter_text=1");	
					//out.println("window.event.keyCode='';	");	
					out.println("}	");	
					out.println("}"); 
					
					out.println("function check_item() {");
				 	out.println("var keyChar=window.event.keyCode;");
					out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
					out.println("{	");	
					out.println("window.event.keyCode=keyChar;	");	
					out.println("}	");	
					out.println("else	");	
					out.println("{	");	
					out.println("enter_text1=1");	
					//out.println("window.event.keyCode='';	");	
					out.println("}	");	
					out.println("}"); 
					
					// Modified by Thamali Jayatunga on 2009.10.15, Modified function check_number
					out.println("function check_number(obj,size){");
					out.println("if(obj.value!='')"); 
					out.println("if(isnumberok(obj,size)){"); 
					out.println("format_number(obj,size)"); 
					out.println("}"); 
					out.println("else{");
					out.println("if(obj.value >=100){");
					out.println("alert('Please enter a number less than 100');");
					out.println("}"); 
					out.println("else {");
					out.println("alert('please enter a number');"); 
					out.println("}");
					out.println("obj.value='';"); 
					out.println("obj.focus();"); 
					out.println("}"); 
					out.println("}"); 
					
					out.println("function load_calendar() {");
					out.println("		popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\",\"oBj\",\"left=450,top=200,width=320,height=230\");");
					out.println("}");
					
					out.println("function load_c_date(val) {");
		     		out.println("	v_dd = val.substr(0,val.indexOf('-'));");
		   			out.println("   if(v_dd.length <2)"); 
		   			out.println("  	v_dd = 0+v_dd;");
		  			out.println("   val = val.substr(val.indexOf('-')+1,val.length);");
		     		out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
		  			out.println("   if(v_mm.length <2)");
		   	  		out.println("		v_mm = 0+v_mm;");
		  			out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
		     		out.println("	document.Form1.TXT_EFFECTIVE_DATE_DD.value=v_dd;");
		   			out.println("   document.Form1.TXT_EFFECTIVE_DATE_MM.value=v_mm;");
		   			out.println("   document.Form1.TXT_EFFECTIVE_DATE_YY.value=v_yy;");
		 			out.println("}");
						
					out.println("var lineno_bank=0;");
					out.println("var arr_size_bank=0;");
					
					out.println("var array_banker = new Array();");
					out.println("var array_banker_name = new Array();");
					out.println("var array_chk_reg = new Array();");
					
					out.println("var array_item_sub_cat_desc = new Array();");
					out.println("var array_item_cat_desc = new Array();");
					
					out.println("function header_bank(type){");
					out.println(" lineno_bank=0; ");
					out.println(" e_header_bank.innerHTML +='<table  border=\"0\" align=\"center\"  width=\"100%\" class=\"table\">'+");
					out.println("                            '<TR>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\">Item Sub Category</TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\">Item Category</TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> Registered </TD>'+");
					out.println("                                 '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                                 '<TD WIDTH=\"20%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("                            '</TR>'+");
					out.println("                           '</table>';");
					out.println(" }");
		
					out.println("function add_button_bank(type){");
					out.println("e_add_but_bank.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
					out.println("'<td width=\"20%\"><input class=\"but_input\" onClick=add_row_bank(\"'+type+'\") type=\"button\" name=\"BUT_ADD_BANK\" value=\"Add\" ></td>'+"); 
					out.println("'<td width=\"*%\"></td>'+"); 
					out.println("'</TR></table>';");
					out.println("}");
					
					
					out.println("function add_row_bank(type){"); 
		
					out.println("var b_flag=0;");
					out.println("if(lineno_bank!=0){"); 
					out.println("count=lineno_bank-1;");
					out.println("m_bank=\"TXT_ITEM_SUB_CAT\"+count");
					
					out.println("b_count=0;");
					out.println("tmp_bank=document.Form1.elements[m_bank].value;");
					
					out.println("for(var i=0;i<lineno_bank-1;i++){");
					out.println("    m_tmp_bank=\"TXT_ITEM_SUB_CAT\"+i");
					
					out.println("   if(lineno_bank>=2){");
					
					out.println("      if(document.Form1.elements[m_tmp_bank].value=='' ){");
					out.println("      alert('Item sub category cannot be blank ! ')");
					out.println("      b_flag=1;");
					out.println("      b_count=1};");
					
					out.println("      else if(document.Form1.elements[m_tmp_bank].value=='-' ){");
					out.println("      alert('Please enter item sub category! ')");
					out.println("      b_flag=1;");
					out.println("      b_count=1};");
					
					out.println("      else if(document.Form1.elements[m_tmp_bank].value==tmp_bank ){");
					out.println("      alert('Item sub category cannot be duplicated ! ')");
					out.println("      document.Form1.elements[m_bank].value = '';  ");
					out.println("      b_flag=1;");
					out.println("      b_count=1};");
					
					out.println("     if(b_count==1){");
					out.println("     break;}");
					
					out.println("   } ");
					
					out.println("   }");
					
					out.println("}");
		
					out.println("  if(b_flag==0){");
					out.println("  e_txt_bank.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");
					out.println("  '<TD WIDTH=\"10%\" ><input class=\"txt_input3\" type=\"text\"     name=TXT_ITEM_SUB_CAT'+lineno_bank+'      value=\"\" onblur=\"\" ></TD>'+");
					out.println("  '<TD WIDTH=\"10%\" ><input class=\"but_input\"  type=\"button\"   name=BUT_TXT_ITEM_SUB_CAT'+lineno_bank+'  value=\"...\" onClick=\"help_update('+lineno_bank+')\" style=\"width: 30px\"  ></td>'+");
					out.println("  '<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_SUB_CAT'+lineno_bank+'  class=div_input>  </DIV> </TD>'+");
					out.println("  '<TD WIDTH=\"10%\" ><input class=\"txt_input3\" type=\"text\"     name=TXT_ITEM_CAT_CODE'+lineno_bank+'     value=\"-\" disabled ></TD>'+"); 	
				    out.println("  '<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_CAT_CODE'+lineno_bank+'  class=div_input>  </DIV> </TD>'+");
					out.println("  '<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("  '<TD WIDTH=\"10%\" ><input   type=\"checkbox\"   name=CHK_REG'+lineno_bank+' onClick=\"chk_reg_set('+lineno_bank+')\" ><input   type=\"hidden\"   name=HID_CHK_REG'+lineno_bank+' id=HID_CHK_REG'+lineno_bank+' ></td>'+");
					out.println("  '<TD WIDTH=\"10%\" ><input class=\"but_input\"  type=\"button\"   name=BUT_SUB_CAT_DEL'+lineno_bank+'       value=\"Delete\" onClick=del_row_bank(\"'+lineno_bank+'\",\"'+type+'\")></td>'+");
					out.println("  '<TD WIDTH=\"20%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("  '</tr></table>';");
					
					out.println("  lineno_bank=lineno_bank+1;");
					out.println("  arr_size_bank = arr_size_bank+1;");
					out.println("  }");
					out.println("  add_button_bank(type);");
		
					out.println(" }");
					
					out.println("function del_row_bank(rowNo,type){"); 
		
					out.println("var j=0;");
					out.println("if(arr_size_bank !=1 || type!='C'){");
					out.println("for(var i=0;i<arr_size_bank;i++){");
					
					out.println("m_bank=\"TXT_ITEM_SUB_CAT\"+i");
					out.println("m_bank_name=\"TXT_ITEM_CAT_CODE\"+i");
					out.println("m_reg=\"HID_CHK_REG\"+i");
					
					out.println("if(i==rowNo)");
					out.println("continue;");
		
					out.println("array_banker[j]=document.Form1.elements[m_bank].value;");
					out.println("array_banker_name[j]=document.Form1.elements[m_bank_name].value;");
					out.println("array_chk_reg[j]=document.Form1.elements[m_reg].value;");
					
					out.println("array_item_sub_cat_desc[j]=document.getElementById('DIV_TXT_ITEM_SUB_CAT'+i).innerHTML;");
					out.println("array_item_cat_desc[j]=document.getElementById('DIV_TXT_ITEM_CAT_CODE'+i).innerHTML;");
					
					out.println("j=j+1;");
					out.println("}");
					
					out.println("m_bank=\"TXT_ITEM_SUB_CAT\"+rowNo");
					
					out.println(" if( document.Form1.elements[m_bank].value != ''  ){ ");
					out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
					out.println("		lineno_bank=lineno_bank-1;");
					out.println("		arr_size_bank=arr_size_bank-1;");
					out.println("		write_data_bank(arr_size_bank,type);");
					out.println(" }");
					out.println(" }");
					out.println(" else {");
					out.println("		lineno_bank=lineno_bank-1;");
					out.println("		arr_size_bank=arr_size_bank-1;");
					out.println("		write_data_bank(arr_size_bank,type);");
					out.println(" }");
					out.println("}");
					out.println("}");
					
					out.println("function write_data_bank(size,type){");
					out.println("e_txt_bank.innerHTML=\"\";");
					out.println(" for(var j=0;j<size;j++){");
					
					
					out.println("if(array_banker[j]==\"\" ){");
		
					out.println("e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
					out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\"   name=TXT_ITEM_SUB_CAT'+j+'     value=\"-\"    onblur=\"\" ></TD>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"but_input\"  type=\"button\" name=BUT_TXT_ITEM_SUB_CAT'+j+' value=\"...\"  onClick=\"help_update('+j+')\" style=\"width: 30px\"  ></td>'+");
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_SUB_CAT'+j+'  class=div_input> '+array_item_sub_cat_desc[j]+' </DIV> </TD>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\"   name=TXT_ITEM_CAT_CODE'+j+'    value=\"-\" disabled ></TD>'+"); 
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_CAT_CODE'+j+'  class=div_input> '+array_item_cat_desc[j]+' </DIV> </TD>'+");
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("'<TD WIDTH=\"10%\" ><input   type=\"checkbox\"   name=CHK_REG'+j+' onClick=\"chk_reg_set('+j+')\" ><input   type=\"hidden\"   name=HID_CHK_REG'+j+'  id=HID_CHK_REG'+j+' ></td>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"but_input\"  type=\"button\" name=BUT_SUB_CAT_DEL'+j+'      value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")></td>'+");
					out.println("'<TD WIDTH=\"20%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("'</tr></table>';");
					
					out.println("lineno_bank=j;");
					out.println("continue;");					
					
					out.println("}");
		
					out.println("e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
					out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\"   name=TXT_ITEM_SUB_CAT'+j+'      value=\"'+array_banker[j]+'\"   ></TD>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"but_input\"  type=\"button\" name=BUT_TXT_ITEM_SUB_CAT'+j+'  value=\"...\" onClick=\"help_update('+j+')\" style=\"width: 30px\" ></td>'+");
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_SUB_CAT'+j+'  class=div_input> '+array_item_sub_cat_desc[j]+' </DIV> </TD>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\"   name=TXT_ITEM_CAT_CODE'+j+'     value=\"'+array_banker_name[j]+'\" disabled ></TD>'+");
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> <DIV id=DIV_TXT_ITEM_CAT_CODE'+j+'  class=div_input> '+array_item_cat_desc[j]+' </DIV> </TD>'+");
					out.println("'<TD WIDTH=\"10%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("'<TD WIDTH=\"10%\" ><input   type=\"checkbox\"   name=CHK_REG'+j+' onClick=\"chk_reg_set('+j+')\"  ><input   type=\"hidden\"   name=HID_CHK_REG'+j+' id=HID_CHK_REG'+j+' value=\"'+array_chk_reg[j]+'\" ></td>'+");
					out.println("'<TD WIDTH=\"10%\"><input class=\"but_input\"  type=\"button\" name=BUT_SUB_CAT_DEL'+j+'       value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")></td>'+");
					out.println("'<TD WIDTH=\"20%\" align=\"left\"> &nbsp; </TD>'+");
					out.println("'</tr></table>';");
		
		
					out.println("}");
					out.println(" lineno_bank=j;");
					
					out.println("chk_reg_set_ckecked(); ");
					
					out.println("}");
					
					out.println("function chk_reg_set(row){");
					
					out.println("    m_reg=\"CHK_REG\"+row");
					out.println("    m_hid_reg=\"HID_CHK_REG\"+row");
					
					out.println("    if(document.Form1.elements[m_reg].checked==true){ ");
					out.println("      m_hid_reg = 'Y'; ");
					out.println("    }");
					out.println("    else{");
					out.println("      m_hid_reg = 'N'; ");
					out.println("    }");
					
					out.println("    document.getElementById('HID_CHK_REG'+row).value=m_hid_reg;  ");
		
					out.println("}");
					
					out.println("function load_dynamic() {");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_System_reports_vehicle_category?chksql=load_dynamic&veh_cat_code=\"+document.Form1.TXT_VEHICLE_CATEGORY.value;"); 
					out.println("      load_interface(m_url,'NORM');");
					out.println("}");
					
					out.println("function load_dynamic_count() {");
					out.println("      document.Form1.hid_val.value='t2'; ");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=load_dynamic_count&veh_cat_code=\"+document.Form1.TXT_VEHICLE_CATEGORY.value;");
					out.println("      load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function get_vector_normal(http_response) {");
					out.println(" e_txt_bank.innerHTML = ''; ");
					out.println(" e_txt_bank.innerHTML = http_response; ");
					out.println(" ");
					out.println("}");
					
					
					out.println("function chk_reg_set_ckecked(){");
					
					out.println("  for(i=0; i<arr_size_bank; i++){ ");
					
					out.println("    m_hid_reg=\"HID_CHK_REG\"+i; ");
					out.println("    m_ckh_box=\"CHK_REG\"+i; ");
					
					out.println("    if(document.getElementById('HID_CHK_REG'+i).value=='Y'){  ");
					out.println("       document.Form1.elements[m_ckh_box].checked=true; ");
					out.println("    }");
					
					//out.println("    document.getElementById('DIV_TXT_ITEM_SUB_CAT'+i).innerHTML  = array_item_sub_cat_desc[i]; ");
					//out.println("    document.getElementById('DIV_TXT_ITEM_CAT_CODE'+i).innerHTML = array_item_cat_desc[i]; ");
					
					out.println("  }");
					
					out.println("}");
					

					
		
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),add_button_bank('I'),header_bank('I');add_row_bank('I');load_sysdate();\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_close_status' VALUE=\"N\">");
					out.println("<INPUT TYPE='Hidden' NAME='element_size' VALUE=\"N\">");
		
					//out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">");
					
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Vehicle Category</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  

					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
		
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
		
		
					out.println("<table align='center' width='100%' class='table'>"); 
					
					out.println("<tr >");
					out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_EFFECTIVE_DATE\" class=div_input>Effective Date *</DIV></td>");
					out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_EFFECTIVE_DATE_DD\" maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_EFFECTIVE_DATE_MM\" maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_EFFECTIVE_DATE_YY\" maxlength=\"4\" size=\"4\" ><a href style=\"{cursor:hand;}\" onclick=\"load_calendar()\">   <u>Calendar</u></a></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_VEHICLE_CATEGORY'  class=div_input>Vehicle Category *</DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VEHICLE_CATEGORY' maxlength='10' size='10' onblur=\"assig('t1');makeRequest(this);\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_vehicle_category()\"  disabled ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_VEHICLE_CATEGORY_DESCRIPTION'  class=div_input>Vehicle Category Description *</DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VEHICLE_CATEGORY_DESCRIPTION' maxlength='100' size='10' onblur=\"\">"); 
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					
					out.println("</table>"); 
					out.println("<br>"); 
					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>" );
					out.println("<td width=\"100%\"><DIV ID=e_add_but_bank>  </DIV></td>");				
					out.println("</tr>" );
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>" );
					out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_bank>  </DIV></td>");				
					out.println("</tr>" );
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>" );
					out.println("<td width=\"100%\"><DIV ID=e_txt_bank>  </DIV></td>");				
					out.println("</tr>" );
					out.println("</table>");
					
					out.println("<br>"); 
					
					
					out.println("<table align='center' width='100%'>"); 
					out.println("<tr>"); 
					out.println("<td width='100%' class='note'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();
			
			}
			
			else if(m_chksql.equals("load_dynamic")){
				
				    stmt = conn.createStatement ();
				
					String	m_veh_cat_code  = req.getParameter("veh_cat_code");
					int j = 0;

					rs=stmt.executeQuery(" "+
						" SELECT "+ 
							" ITEM_SUB_CAT, "+
							" ITEM_CAT, "+
							" NVL(REG_STATUS,'N') REG_STATUS, "+
							" "+m_schema_name+".AF_CO_GET_ITEM_SUB_CAT_DESC(ITEM_CAT,ITEM_SUB_CAT) ITEM_SUB_CAT_DESC, "+
							" "+m_schema_name+".AF_CO_GET_ITEM_CAT_DESC(ITEM_CAT) ITEM_CAT_DESC "+
								" FROM "+m_schema_name+".AF_TBL_REG_STATUS "+
								" WHERE VEHICLE_CAT = '"+m_veh_cat_code+"' "+
								" ORDER BY SEQ_NO "+
						" ");

					out.println("<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\">");  
			        
					
					while(rs.next()){
						
						    out.println("  <tr>");
						
							out.println("  <TD WIDTH=\"10%\" ><input class=\"txt_input3\" type=\"text\"     name='TXT_ITEM_SUB_CAT"+j+"'       value='"+rs.getString("ITEM_SUB_CAT")+"'   onblur=\"\" ></TD>");
							out.println("  <TD WIDTH=\"10%\" > <DIV  id='DIV_TXT_ITEM_SUB_CAT"+j+"'    class=div_input> "+rs.getString("ITEM_SUB_CAT_DESC")+" </DIV> </TD>");
							out.println("  <TD WIDTH=\"10%\" ><input class=\"but_input\"  type=\"button\"   name='BUT_TXT_ITEM_SUB_CAT"+j+"'   value=\"...\" onClick=\"help_update('"+j+"')\" style=\"width: 30px\"  ></td>");
							out.println("  <TD WIDTH=\"10%\" ><input class=\"txt_input3\" type=\"text\"     name='TXT_ITEM_CAT_CODE"+j+"'      value='"+rs.getString("ITEM_CAT")+"' disabled > </TD>"); 
					        out.println("  <TD WIDTH=\"10%\" > <DIV  id='DIV_TXT_ITEM_CAT_CODE"+j+"'    class=div_input> "+rs.getString("ITEM_CAT_DESC")+" </DIV> </TD>");
						    out.println("  <TD WIDTH=\"10%\" > &nbsp; </TD>");
							
							String m_reg_status = rs.getString("REG_STATUS");
							
							if(m_reg_status.equals("N")){
								out.println("  <TD WIDTH=\"10%\" ><input   type=\"checkbox\"   name='CHK_REG"+j+"'  onClick=\"chk_reg_set('"+j+"')\"  >");
							}
							else{
								out.println("  <TD WIDTH=\"10%\" ><input   type=\"checkbox\"   name='CHK_REG"+j+"'  onClick=\"chk_reg_set('"+j+"')\" checked >");
							}

					        out.println("  <input   type=\"hidden\"  name='HID_CHK_REG"+j+"'  name='HID_CHK_REG"+j+"'  value='"+rs.getString("REG_STATUS")+"' ></td>");
							out.println("  <TD WIDTH=\"10%\" ><input   type=\"button\"     name='BUT_SUB_CAT_DEL"+j+"'  onClick=del_row_bank('"+j+"','I') class=\"but_input\" value=\"Delete\" ></td>");
							out.println("  <TD WIDTH=\"20%\" > &nbsp; </TD>");
							
							out.println("</tr>");
							
							j = j + 1;
						
						}
					
					
		            out.println("</table>");
		
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
