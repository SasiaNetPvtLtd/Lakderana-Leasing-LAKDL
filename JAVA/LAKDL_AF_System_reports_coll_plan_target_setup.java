import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_System_reports_coll_plan_target_setup extends javax.servlet.http.HttpServlet { 

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
					out.println("			if(data_vec.length>0  &&  document.Form1.hid_val.value=='t2'){");
					out.println("			   document.Form1.TXT_TO_BE_COLLECTED.value = data_vec[0]; ");
					out.println("			}");
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
					
					out.println("function set_percentage_val(obj) {");
					out.println("      document.Form1.hid_val.value = 't2'; ");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_set_percentage_val&data_val=\"+obj.value;");
					//out.println("      window.open(m_url);  ");
					out.println("      load_interface(m_url,'XML');");
					out.println("}");
		

		
					out.println("function before_submit(){ ");
					
					out.println("		if( checkMonthLength(document.Form1.TXT_EFFECTIVE_DATE_DD,document.Form1.TXT_EFFECTIVE_DATE_MM,document.Form1.TXT_EFFECTIVE_DATE_YY)){"); // added by Chatura Jayawardena

					out.println("           if(document.Form1.TXT_TYPE.value==''){      "); 
					
					out.println("		        alert('Please select the type'); ");
						
					out.println("		    }");
					
					out.println("           else if(document.Form1.TXT_TO_BE_COLLECTED.value==''){      "); 
					
					out.println("		        alert('Please enter the to be collected percentage'); ");
					
					out.println("		    }");
					
					out.println("		    else{");
					
					out.println("		          if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
					
					//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
					//out.println("document.Form1.elements[i].disabled=false;");
					//out.println("}");

					out.println("		               document.Form1.action='"+m_class_url+"/LAKDL_AF_Save_coll_plan_target_setup';");  
					out.println("		               document.Form1.submit();	"); 

					out.println("		          }"); 
					
					out.println("		     }"); 
					
					out.println("		}"); 

		
					out.println("} "); 
		
					out.println("function load_lock(){	"); 
					out.println(" if('"+m_close_status+"'=='B'){");
					out.println("document.Form1.hid_close_status.value='Y'");
					out.println("}"); 
					//out.println("document.oncontextmenu=new Function(\"return false\");"); 
					out.println("}	"); 
		
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					
					out.println("if(document.Form1.hid_close_status.value=='Y'){");			
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_coll_plan_target_setup?chksql=main_page';"); 
					out.println("}else {"); 			
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_coll_plan_target_setup?chksql=main_page';"); 
					out.println("}"); 
					
					//out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_System_reports_coll_plan_target_setup';"); 
					out.println("		}"); 
					out.println("}"); 
		
					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_System_reports_coll_plan_target_setup?chksql=main_page';");
					out.println("}"); 
		
		
					out.println("function save_window(){	"); 
					out.println("  before_submit();"); 
					out.println("}"); 
					
					out.println(""); 
		
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_LAKDL_AF_System_reports_coll_plan_target_setup\";"); 
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
					

					
					// Modified by Thamali Jayatunga on 2009.10.15, Modified function check_number
					out.println("function check_number(obj,size){");
					out.println("if(obj.value!='')"); 
					out.println("if(isnumberok(obj,size)){"); 
					out.println("format_number(obj,size)"); 
					out.println("}"); 
					out.println("else{");
					out.println("if(obj.value >100){");
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

					
					out.println("function load_dynamic() {");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_System_reports_coll_plan_target_setup?chksql=load_dynamic&veh_cat_code=\"+document.Form1.TXT_VEHICLE_CATEGORY.value;"); 
					out.println("      load_interface(m_url,'NORM');");
					out.println("}");

		
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),load_sysdate();\">"); 
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
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='new_window()' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  

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
					out.println("<td width='20%' ><DIV id='DIV_TXT_TYPE'  class=div_input>Type</DIV></td>"); 
					out.println("<td width='40%' >"); 
					out.println("<select class='txt_input' name='TXT_TYPE' onchange='set_percentage_val(this);' >");  
					out.println("      <OPTION value='' ></OPTION>");
					out.println("      <OPTION value='ARREARS' >Arrears</OPTION>");
					out.println("      <OPTION value='RENTAL'  >Rental</OPTION>");
					out.println("      <OPTION value='OVERALL' >Overall</OPTION>");
					out.println("</select>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_TO_BE_COLLECTED'  class=div_input>To be collected %</DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TO_BE_COLLECTED' maxlength='10' size='10' onblur=\"check_number(this,2);\">"); 
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					
					out.println("</table>"); 
					out.println("<br>"); 
					
					
					
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
			
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
