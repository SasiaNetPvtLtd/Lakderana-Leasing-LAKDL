//--
//SCREEN NAME:OFSCL CRIB DATA TRANFER
//CREATED BY:CHANDANA
//DATE/TIME:14/11/2007
//NOTES: 

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Genaration_of_Crib_Data extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs_cf,rs_cs,rs_ss,rs_gs,rs_rs;
	
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods  m_sn_methods = new LAKDL_AF_CO_conn_methods (); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
						
			m_chksql=req.getParameter("chksql");
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			/*if(m_chksql.equals("run_report")){ 
			
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".CO_SAVE_CRIB_DATA(:1,:2,:3,:4);END;");
				callstmt1.setString(1,m_from_date);
				callstmt1.setString(2,m_username);
				callstmt1.setString(3,m_username);
				callstmt1.setString(4,m_rpt_type);
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}*/
			
 if(m_chksql.equals("main_page")){
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - CRIB Data File Generate</TITLE>"); 
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
			
			out.println("var m_date_fromat_dd;");
			out.println("var m_date_fromat_mon;");
			out.println("var m_date_fromat_yyyy;");

			out.println("function get_vector(data_vec) {");
			//out.println("alert('asd'+document.Form1.SCREEN_NAME.value+document.Form1.hid_val.value);");
						
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ITEM_SUB_CAT.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("help_update();");
			//out.println("item_sub_arry=data_vec");
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ITEM_SUB_CAT.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value=data_vec[0];"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DESCRIPTION.value=data_vec[2];"); 
				out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value=data_vec[3];");  
			out.println("document.Form1.TXT_VAT_AMOUNT.value=data_vec[4];"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[5];");
			out.println("document.Form1.TXT_CAP_ALLOWANCE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_DD.value=data_vec[7].substring(0,2);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_MM.value=data_vec[7].substring(3,5);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_YY.value=data_vec[7].substring(6,10);");
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='t3'){");
			out.println("help_update_desc()");
			out.println("	}");
			out.println("else if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_ITEM_CAT_CODE.value!=''){");
			out.println("help_button_2()");
			out.println("	}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_val.value=='M1'){");
			out.println("assign_date(data_vec)");
			out.println("	}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_val.value=='M_DATE'){");
			out.println("assign_date_format(data_vec)");
			out.println("}");
			
			
			
			//out.println("alert(data_vec.length+document.Form1.SCREEN_NAME.value+document.Form1.hid_val.value+document.Form1.TXT_DESCRIPTION.value)");
		
			out.println("}");
			
			
			out.println("function assig(val) {");
			
			//out.println("if(document.Form1.TXT_ITEM_SUB_CAT.value!='' || document.Form1.TXT_DESCRIPTION.value!='' ){");
			out.println("document.Form1.hid_val.value=val;");
			//out.println("}");
			//out.println("else{");
			//out.println("document.Form1.hid_val.value=pre");
			//out.println("}");
			
			//out.println("alert(document.Form1.hid_val.value)");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			//out.println("var pre=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			//out.println("if(document.Form1.hid_val.value=='t1'){");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_"+m_schema_name+"_AF_MAS_display_item_sub_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.SCREEN_NAME.value!=\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_"+m_schema_name+"_AF_MAS_display_item_sub_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
		//	out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_r&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			//out.println("}");
			//out.println("if(document.Form1.hid_val.value=='t3'){");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_desc&data_val=\"+obj.value;");

			//out.println("}");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
				
			out.println("}");

			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest2(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_desc1&data_val=\"+obj.value;");
			out.println(" ");
			
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ITEM_SUB_CAT.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_SUB_CAT.style.color='red';");
			//out.println("alert('Enter Item Sub Category Code..!')");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			//out.println("alert('Enter Item Sub Category Description..!')");
			out.println("document.Form1.TXT_DESCRIPTION.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ITEM_CAT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_CAT_CODE.style.color='red';");
			//out.println("alert('Enter Item Category Code..!')");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.focus()");

			out.println("return false;"); 
			out.println("}"); 
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");

			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_MAS_save_item_sub_category';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
					out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 

			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MAS_Genaration_of_Crib_Data?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MAS_Genaration_of_Crib_Data?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			//out.println("	alert(validate_data())");
			//out.println("	if(validate_data()==false){");
			//out.println("alert('Please enter all required fields marked with a '*' on the screen')"); 
			//out.println("		}"); 
			//out.println("	if(validate_data()==true){");
		
			//out.println("	alert(validate_data())");
			out.println("before_submit();"); 
			//out.println("}"); 
			out.println("}"); 
			
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_item_sub_category\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit - CRIB Data File Generate - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit - CRIB Data File Generate - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;");
			out.println("document.Form1.BUT_ITEM_CAT_CODE.disabled=true;");
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.disabled=true;");
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;");
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
			
			out.println("document.Form1.TXT_ITEM_CAT_CODE.disabled=true;");
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			//out.println("document.Form1.TXT_ITEM_SUB_CAT.disabled=true;");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.disabled=false;");
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
			//out.println("alert(document.Form1.hid_val.value)");
			out.println("clear_data()	");
      out.println("window.close();");
      out.println(" }");
		
			out.println("function clear_data1() {");//**
			//out.println("alert('1234');");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
			//out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
			//out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			//out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';");
			//out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value='';"); 
			//out.println("document.Form1.TXT_VAT_AMOUNT.value='';"); 
			out.println("}");
			
			out.println("function clear_data() {");//**
			//out.println("alert(enter_text)");
			//out.println("alert('bttn_pres'+bttn_pres)");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 

			///out.println("if(enter_text1!=1 && item_sub_arry.length==0 && document.Form1.hid_val.value=='t1' || document.Form1.hid_val.value=='t3') {");//**
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
			out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';");
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value='';"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.value='';"); 
			out.println("}");
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 

			//out.println("if(enter_text1==1 && enter_text==0 && document.Form1.TXT_ITEM_CAT_CODE.value!='' && item_arry.length==0) {");//**
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.focus();"); 
			out.println("}");
			
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
			
			

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";"); 
			//out.println("var item=document.Form1.TXT_ITEM_CAT_CODE.value.substring(0,1)");
			
			//out.println("if(enter_text1!=1){");
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT_CODE.value+\"@Y@\";"); 
			//out.println("}");
			//out.println("if(enter_text1==1){");
			//out.println("    m_criteria = item+\"@Y@\";"); 
			//out.println("}");
			out.println("    HelpBox('1','10','1');"); 
			out.println("}");
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			

			out.println("function help_update() {"); 
			out.println("bttn_pres=1");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";");
			//out.println("var sub=document.Form1.TXT_ITEM_SUB_CAT.value.substring(0,1) ");
			
		//	out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			///out.println("    help_Item_Sub();");
			
		//	out.println("    } ");
			
			//out.println("    if(enter_text!=1){ ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"N@\";}");
			/*out.println("    } ");
			out.println("    if(enter_text==1){ ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = sub+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = sub+\"@\"+\"N@\";}");
			out.println("    } ");
			*/

			
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_sql\";");
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value=oBj.valout[5];"); 
			out.println("format_number(document.Form1.TXT_VAT_APPLICABLE_AMOUNT,6);");
			out.println("document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[6];"); 
			out.println("format_number(document.Form1.TXT_VAT_AMOUNT,6);");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE.value=oBj.valout[8];"); 
			out.println("format_number(document.Form1.TXT_CAP_ALLOWANCE,6);");
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_DD.value=oBj.valout[9].substring(0,2);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_MM.value=oBj.valout[9].substring(3,5);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_YY.value=oBj.valout[9].substring(6,10);");
			out.println("bttn_pres=0");
			out.println("enter_text=0");
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
			
	
			
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;");
			out.println("		popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\",\"oBj\",\"left=450,top=200,width=320,height=230\");");
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
     	out.println("	 v_dd = val.substr(0,val.indexOf('-'));");
   		out.println("  if(v_dd.length <2)"); 
   		out.println("  	v_dd = 0+v_dd;");
  		out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
  		out.println("  if(v_mm.length <2)");
   	  out.println("		v_mm = 0+v_mm;");
  		out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("		document.Form1.TXT_DATA_PREPERATION_DD.value=v_dd;");
   		out.println("   document.Form1.TXT_DATA_PREPERATION_MM.value=v_mm;");
   		out.println("   document.Form1.TXT_DATA_PREPERATION_YY.value=v_yy;");
 			out.println("}");

      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
     	out.println("	 v_dd = val.substr(0,val.indexOf('-'));");
   		out.println("  if(v_dd.length <2)"); 
   		out.println("  	v_dd = 0+v_dd;");
  		out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
  		out.println("  if(v_mm.length <2)");
   	  out.println("		v_mm = 0+v_mm;");
  		out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("		document.Form1.TXT_DATA_REPORTING_DD.value=v_dd;");
   		out.println("   document.Form1.TXT_DATA_REPORTING_MM.value=v_mm;");
   		out.println("   document.Form1.TXT_DATA_REPORTING_YY.value=v_yy;");
 			out.println("}");
			out.println("}");
			
			
      out.println("function load_date(){");			
			out.println("document.Form1.hid_val.value=\"M1\";"); 
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_get_sysdate_and_time\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_date_format(val){");			
			out.println("document.Form1.hid_val.value=\"M_DATE\";"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=get_date_format&date=\"+val;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			
			
			
			
			
			out.println("function assign_date(data_vec){");
			out.println("document.Form1.TXT_DATA_PREPERATION_DD.value=data_vec[0];"); 
			out.println("document.Form1.TXT_DATA_PREPERATION_MM.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DATA_PREPERATION_YY.value=data_vec[2];"); 

      out.println("document.Form1.TXT_DATA_REPORTING_DD.value=data_vec[0];"); 
			out.println("document.Form1.TXT_DATA_REPORTING_MM.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DATA_REPORTING_YY.value=data_vec[2];");  

      out.println("document.Form1.TXT_DATA_REPORTING_HH.value=data_vec[3];"); 
			out.println("document.Form1.TXT_DATA_REPORTING_MI.value=data_vec[4];"); 
			out.println("document.Form1.TXT_DATA_REPORTING_SS.value=data_vec[5];");

			out.println("}");
			
			out.println("function assign_date_format(data_vec){");
			
			out.println("m_date_fromat_dd=data_vec[0];"); 
			out.println("m_date_fromat_mon=data_vec[1];"); 
			out.println("m_date_fromat_yyyy=data_vec[2];"); 
			out.println("}");
			
			
				out.println("var b_flag=0;");
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");

				
				out.println("function run_report() {");
							    			
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_CRIB_File?chksql=run_report"+
				//"&data_prov_id=\"+document.Form1.TXT_DATA_PROVIDER_ID.value+\""+
				//"&data_point_id=\"+document.Form1.TXT_DATA_SUBMISSION_ID.value+\""+
				"&from_date=\"+document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value+\""+
				"&to_date=\"+document.Form1.TXT_DATA_REPORTING_DD.value+\"-\"+document.Form1.TXT_DATA_REPORTING_MM.value+\"-\"+document.Form1.TXT_DATA_REPORTING_YY.value+\""+
				//"&data_report_time=\"+document.Form1.TXT_DATA_REPORTING_HH.value+\"\"+document.Form1.TXT_DATA_REPORTING_MI.value+\"\"+document.Form1.TXT_DATA_REPORTING_SS.value+\""+
				"&rpt_type=\"+document.Form1.TXT_SUBJECT_TYPE.value+\"\";");
				//out.println("window.open(m_url);");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('The File Generated Successfully');");  //added by ns on 29-04-2010
				out.println("		    clearTimeout(timerID);");
				out.println("		    m_table.innerHTML=\"\";");
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function view_text_file(){");
				out.println("get_date_format(date);");
				//out.println("alert('Generate File');"); //Commented by Chatura Jayawardena
				out.println("		if(confirm(\"Are you sure you want to view the CRIB File ? \")){ "); 
				out.println("date=document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value");
				out.println("  write_data_members();");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_CRIB_File?chksql=text_file"+
				"&data_prov_id=\"+document.Form1.TXT_DATA_PROVIDER_ID.value+\""+
				"&data_point_id=\"+document.Form1.TXT_DATA_SUBMISSION_ID.value+\""+
				"&data_prepar_date=\"+document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value+\""+
				"&data_report_date=\"+document.Form1.TXT_DATA_REPORTING_DD.value+\"-\"+document.Form1.TXT_DATA_REPORTING_MM.value+\"-\"+document.Form1.TXT_DATA_REPORTING_YY.value+\""+
				"&data_report_time=\"+document.Form1.TXT_DATA_REPORTING_HH.value+\"\"+document.Form1.TXT_DATA_REPORTING_MI.value+\"\"+document.Form1.TXT_DATA_REPORTING_SS.value+\""+
				"&data_cf_type=\"+document.Form1.TXT_SUBJECT_TYPE.value+\"\";");
				
 		   	    out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=210,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}");		
				out.println("}");
				
				
						

			
			
			out.println("function generate_file(){ ");
			out.println("date=document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value");
			out.println("get_date_format(date);");
			out.println("alert('Generate File');"); //Commented by Chatura Jayawardena
			out.println("document.Form1.BUT_GENARATE.disabled=true;");			
			
			//out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MAS_Genaration_of_Crib_Data?chksql=main_page';"); 
			//out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
			
			//text_file
			/*out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Genaration_of_Crib_Data?chksql=text_file_factoring"+
			"&data_prov_id=\"+document.Form1.TXT_DATA_PROVIDER_ID.value+\""+
			"&data_point_id=\"+document.Form1.TXT_DATA_SUBMISSION_ID.value+\""+
			"&data_prepar_date=\"+document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value+\""+
			"&data_report_date=\"+document.Form1.TXT_DATA_REPORTING_DD.value+\"-\"+document.Form1.TXT_DATA_REPORTING_MM.value+\"-\"+document.Form1.TXT_DATA_REPORTING_YY.value+\""+
			"&data_report_time=\"+document.Form1.TXT_DATA_REPORTING_HH.value+\"\"+document.Form1.TXT_DATA_REPORTING_MI.value+\"\"+document.Form1.TXT_DATA_REPORTING_SS.value+\""+
			"&data_cf_type=\"+document.Form1.TXT_SUBJECT_TYPE.value+\"\";");*/// COMMENTED BY DISNAKA 2011-09-16
			
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Genaration_of_Crib_Data?chksql=text_file"+
			"&data_prov_id=\"+document.Form1.TXT_DATA_PROVIDER_ID.value+\""+
			"&data_point_id=\"+document.Form1.TXT_DATA_SUBMISSION_ID.value+\""+
			"&data_prepar_date=\"+document.Form1.TXT_DATA_PREPERATION_DD.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_MM.value+\"-\"+document.Form1.TXT_DATA_PREPERATION_YY.value+\""+
			"&data_report_date=\"+document.Form1.TXT_DATA_REPORTING_DD.value+\"-\"+document.Form1.TXT_DATA_REPORTING_MM.value+\"-\"+document.Form1.TXT_DATA_REPORTING_YY.value+\""+
			"&data_report_time=\"+document.Form1.TXT_DATA_REPORTING_HH.value+\"\"+document.Form1.TXT_DATA_REPORTING_MI.value+\"\"+document.Form1.TXT_DATA_REPORTING_SS.value+\""+
			"&data_cf_type=\"+document.Form1.TXT_SUBJECT_TYPE.value+\"\";");
			
				
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=210,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
						
			//out.println("}");	
			
			out.println("write_data_members();");
			out.println("}");
			
						
			  out.println("function write_data_members(){");
								
				
				out.println("var m_type='';");
				out.println("var M_DD=document.Form1.TXT_DATA_PREPERATION_DD.value;");
				out.println("var M_MM=document.Form1.TXT_DATA_PREPERATION_MM.value;");
				out.println("var M_YY=document.Form1.TXT_DATA_PREPERATION_YY.value;");
				
				out.println(" if(document.Form1.TXT_SUBJECT_TYPE.value=='I') {");
				out.println("m_type='CON';");
				out.println("}");
				out.println("else {");
				out.println("m_type='COM';");
				out.println("}");
				
			    //out.println("m_file_name =	document.Form1.TXT_DATA_PROVIDER_ID.value+document.Form1.TXT_DATA_SUBMISSION_ID.value+'-'+document.Form1.TXT_DATA_REPORTING_HH.value+document.Form1.TXT_DATA_REPORTING_MI.value+document.Form1.TXT_DATA_REPORTING_SS.value+'.DLT'; ");//comment by nuwan de silva
				//out.println("m_file_name =	document.Form1.TXT_DATA_PROVIDER_ID.value+'-'+m_type+'-'+M_DD+'-'+M_MM+'-'+M_YY+'-'+document.Form1.TXT_DATA_REPORTING_HH.value+document.Form1.TXT_DATA_REPORTING_MI.value+document.Form1.TXT_DATA_REPORTING_SS.value+'.DLT'; ");
				
				
				out.println("m_file_name =	document.Form1.TXT_DATA_PROVIDER_ID.value+'-'+m_type+'-'+m_date_fromat_dd+'-'+m_date_fromat_mon+'-'+m_date_fromat_yyyy+'-'+document.Form1.TXT_DATA_REPORTING_HH.value+document.Form1.TXT_DATA_REPORTING_MI.value+document.Form1.TXT_DATA_REPORTING_SS.value+'.DLT'; ");
				//out.println("m_file_name =	document.Form1.TXT_DATA_PROVIDER_ID.value+'-'+m_type+'-'+m_date_fromat_dd+'-'+m_date_fromat_mon+'-'+m_date_fromat_yyyy+'-'+document.Form1.TXT_DATA_REPORTING_HH.value+document.Form1.TXT_DATA_REPORTING_MI.value+document.Form1.TXT_DATA_REPORTING_SS.value+'.DLT'; ");
				
				///out.println("alert('m_file_name'+m_file_name);");				
				
			//	1A3L22158-CON-DD-MM-YYYY-HH-MM-SS.DLT
			 out.println("m_table_members.innerHTML=\"\";");
			 
		   out.println("m_table_members.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID>'+");									
			 out.println("'<TD WIDTH=\"40%\"  align=\"left\"><b>Data File Name</b></TD>'+");
			 out.println("'<TD WIDTH=\"40%\"  align=\"left\"><B>'+m_file_name+'</B><TD>'+");
			 out.println("'<TD WIDTH=\"*%\"</TD>'");	 
			 out.println("'</tr></table>';");
			 out.println("}");
			
			
			
				
			
			
		  out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_date(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - CRIB Data File Generate</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'  border='0'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_DATA_PROVIDER_ID'  class=div_input>Data Provider ID *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATA_PROVIDER_ID' maxlength='10' size='10' value=\"1A3L22158\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='40%' ><DIV id='DIV_TXT_DATA_SUBMISSION_ID'  class=div_input>Data Submission Point ID*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATA_SUBMISSION_ID' maxlength='50' size='20' value=\"HO\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_DATA_PREPERATION_DATE\" class=div_input>Data Preparation Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_PREPERATION_DD\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_PREPERATION_DD,document.Form1.TXT_DATA_PREPERATION_MM,document.Form1.TXT_DATA_PREPERATION_YY)\" >");
			
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_PREPERATION_MM\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_PREPERATION_DD,document.Form1.TXT_DATA_PREPERATION_MM,document.Form1.TXT_DATA_PREPERATION_YY)\" >");
			//modified by madhawa 2009-10-12 added onblur event-call to checkMonthLength
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_PREPERATION_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_PREPERATION_DD,document.Form1.TXT_DATA_PREPERATION_MM,document.Form1.TXT_DATA_PREPERATION_YY)\"><a href style=\"{cursor:hand;}\" onclick=\"load_calendar('1')\">   <u>Calendar</u></a></td>");
			//modified by madhawa 2009-10-12 added onblur event-call to checkMonthLength
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

      out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_DATA_REPORTING_DATE\" class=div_input>Data Reporting Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_DD\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_REPORTING_DD,document.Form1.TXT_DATA_REPORTING_MM,document.Form1.TXT_DATA_REPORTING_YY)\" >");
			//modified by madhawa 2009-10-12 added onblur event-call to checkMonthLength
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_MM\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_REPORTING_DD,document.Form1.TXT_DATA_REPORTING_MM,document.Form1.TXT_DATA_REPORTING_YY)\" >");
			//modified by madhawa 2009-10-12 added onblur event-call to checkMonthLength
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_DATA_REPORTING_DD,document.Form1.TXT_DATA_REPORTING_MM,document.Form1.TXT_DATA_REPORTING_YY)\"><a href style=\"{cursor:hand;}\" onclick=\"load_calendar('2')\">   <u>Calendar</u></a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");			
			
     
			out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_DATA_REPORTING_TIME\" class=div_input>Data Reporting Time[HH:MI:SS] *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_HH\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_MI\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATA_REPORTING_SS\" maxlength=\"2\" size=\"2\" onblur=\"\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");			
		
			out.println("<tr >"); 
			out.println("<td width='30%' >Type Of Subject *</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_SUBJECT_TYPE' maxlength='1' size='1'>");  
			out.println("<option value='I' selected>Individual</option>");			
			out.println("<option value='C' >Corporate</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ></td>");
			out.println("<td width='40%' ></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_members'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			
			out.println("<table align='center' width='100%' class='table'  border='0'>");
			out.println("<tr >"); 
			out.println("<td width='20%' >&nbsp&nbsp</td>"); 
			out.println("<td width='*%' ><input type=\"button\" class='mainbut' style='{width:150;}'  name='BUT_GENARATE' onClick='generate_file()' value=\"Generate Data File\">"); 
			out.println("                 <input type=\"button\" class='mainbut' style='{width:150;}'  name='BUT_GENARATE2' onClick='run_report()' value=\"Run CRIB File\" >"); 
			out.println("                 <input type=\"button\" class='mainbut' style='{width:150;}'  name='BUT_GENARATE3' onClick='view_text_file()' value=\"View CRIB Data File\" ></td>"); 
			//out.println("<td width='20%'></td>"); 
			//out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
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
			//out.flush();
		 } 
			
			
			
			else if(m_chksql.equals("text_file")){
			
			String m_data_prov_id=req.getParameter("data_prov_id");
			String m_data_point_id=req.getParameter("data_point_id"); 
			String m_data_prepar_date=req.getParameter("data_prepar_date");
			String m_data_report_date=req.getParameter("data_report_date"); 
			String m_data_report_time=req.getParameter("data_report_time"); 
			String m_data_cf_type=req.getParameter("data_cf_type");
			
			
			String m_prepar_date="";
			String m_report_date="";
			int    m_data_count =0; 
			int    m_data_count_fac =0; 
			int    m_data_count_tot =0; 
			String m_finance_no ="";
			String m_application_no=""; 
			String m_cf_details ="";
			String m_cs_details ="";
			String m_ss_details ="";
			String m_gs_details ="";
			String m_rs_details ="";
			String m_facility_no ="";
			
			/*StringBuffer str_m_cf_details = new StringBuffer();
			StringBuffer str_m_cs_details = new StringBuffer();
			StringBuffer str_m_ss_details = new StringBuffer();
			StringBuffer str_m_gs_details = new StringBuffer();
			StringBuffer str_m_rs_details = new StringBuffer();
      */
						
			
			rs = stmt.executeQuery (" SELECT TO_CHAR(TO_DATE('"+m_data_prepar_date+"','DD-MM-YYYY'),'DD-MON-YYYY'), "+
			                        " TO_CHAR(TO_DATE('"+m_data_report_date+"','DD-MM-YYYY'),'DD-MON-YYYY') "+
															" FROM DUAL ");
			
			boolean more = rs.next();
						
			if(more){
			m_prepar_date = rs.getString(1); 
			m_report_date = rs.getString(2);
			}
			
						
			
			rs = stmt.executeQuery (" SELECT COUNT(APPLICATION_NO) "+
			                        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE FINANCE_NO <> 'NULL' AND FINANCE_NO IS NOT NULL "+
															" AND APPLICATION_STATUS IN ('ACTIVATED','TERMINATE','TERMINATED','SETTLED','TERMI','TERM_TO','NORM_TERMI','CANCEL_PO','LEGAL','WRITE_OFF','LG_SETTLED') "+ //added legal status
															" AND "+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE) ='"+m_data_cf_type+"' "+
															" AND TO_DATE(ACTIVATED_DATE,'DD-MM-YY') < TO_DATE('"+m_data_report_date+"','DD-MM-YY') "+
															" AND FINANCE_NO IN ( "+
																" SELECT FINANCE_NO "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND  APPLICATION_STATUS IN ('TERMI','WRITE_OFF','LG_SETTLED') "+
																" AND   B.CLIENT_TYPE='"+m_data_cf_type+"' ) "+
															
															"");
			
			boolean more1 = rs.next();
						
			if(more1){
			m_data_count = rs.getInt(1); 
			}
			
			
			if(m_data_cf_type.equals("I")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|001"); 
      }
			else if(m_data_cf_type.equals("C")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|002"); 
      } 
			
						
						
			rs = stmt.executeQuery (" SELECT FINANCE_NO, "+
			                        " CLIENT_CODE, "+
															" APPLICATION_NO, "+
															" PRE_APPLICATION_NO "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE FINANCE_NO <> 'NULL' AND FINANCE_NO IS NOT NULL  "+
															" AND APPLICATION_STATUS IN ('ACTIVATED','TERMINATE','TERMINATED','SETTLED','TERMI','TERM_TO','NORM_TERMI','CANCEL_PO','LEGAL','WRITE_OFF','LG_SETTLED') "+//added legal status
															" AND "+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE) ='"+m_data_cf_type+"' "+
															" AND TO_DATE(ACTIVATED_DATE,'DD-MM-YY') < TO_DATE('"+m_data_report_date+"','DD-MM-YY') "+
															" AND FINANCE_NO IN ( "+
																" SELECT FINANCE_NO "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND  APPLICATION_STATUS IN ('TERMI','WRITE_OFF','LG_SETTLED') "+
																" AND   B.CLIENT_TYPE='"+m_data_cf_type+"' ) "+
																
															"");

			boolean more2 = rs.next();
			

		  while(more2){
			m_finance_no = rs.getString(1);
			m_application_no = rs.getString(3);
			
			if(m_data_cf_type.equals("I")){
			

			rs_cf = stmt1.executeQuery (" SELECT 'CNCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_NUMBER||'|'||PRI_CARD_NUM||'|'||PRI_CARD_LIM||'|'||PRE_CF_NUM||'|'|| "+
			         " PRE_CARD_NUM||'|'||CF_APPR_DATE||'|'||AMNT_GRANT||'|'||HI_CR_AMNT||'|'||CURRENCY||'|'||OWNERSIP||'|'||TRANSACTION_TYPE||'|'|| "+
							 " PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'|| "+
							 " INT_OUT_BAL||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'||LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'|| "+
							 " CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'||SEC_TYPE||'|'||SEC_COVER||'|'||GURAN_AMT||'|'||TRN_TYPE_CODE "+
							 " ||'|'||DISPUT_ID "+
							 " FROM(SELECT X.FINANCE_NO  CF_NUMBER , "+
							 " DECODE(SUBSTR(X.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							 " '' PRI_CARD_NUM, "+
							 " '' PRI_CARD_LIM, "+
							 " '' PRE_CF_NUM, "+
							 " '' PRE_CARD_NUM, "+
							 //" TO_CHAR(X.ACTIVATED_DATE,'DD-MON-YYYY') CF_APPR_DATE, "+
								" '' CF_APPR_DATE, "+
							 " "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO) AMNT_GRANT, "+
							 " '' HI_CR_AMNT, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_CURRENCY(X.CURRENCY_CODE) CURRENCY, "+
							 " DECODE(NVL(X.CO_APPLICANT,'-'),'-','001','null','001','002') OWNERSIP, "+		 /*ADDED BY CHANDANA ON 10/03/2008*/						
							 " /*DECODE(NVL(X.CO_APPLICANT,'001'),'001','001','002') OWNERSIP,*/ "+	
							 " "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE(X.TRANSACTION_TYPE) TRANSACTION_TYPE, "+
							 " NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001')  PURPOSE_OF_CF, "+
							 " "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO) NUM_OF_INSTALMENT, "+
							 " "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO) INSTALMENT_AMT, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_RE_PAYMNT_TYPE(X.APPLICATION_NO) RE_PAYMNT_TYPE, "+
							 " TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_FIRST_DISB_DATE(X.APPLICATION_NO),'DD-MM-YYYY'),'DD-MON-YYYY') FST_DISB_DATE, "+
							 //" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_REANTAL_ARREARS(X.APPLICATION_NO,SYSDATE),0),0)) CURRENT_BALANCE, "+
								" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005','0','008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_CRIB(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0) + NVL("+m_schema_name+".AF_CO_GET_FUTURE_OUT(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"'),0),0)) CURRENT_BALANCE, "+
							 " DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(X.FINANCE_NO,SYSDATE),0),0) ) INT_OUT_BAL, "+
							 //" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_BAL_TOBE_RESIVED(X.FINANCE_NO,'"+m_data_report_date+"'),0),0) ) AMT_IN_AREAS, "+
							 " DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_CRIB(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0),0) ) AMT_IN_AREAS, "+
							 " DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0, NVL("+m_schema_name+".AF_CO_GET_NUM_OF_ARREARS_DATE(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0) ) NUM_OF_DATES, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO) CF_STATUS, "+
							 " "+m_schema_name+".AF_CO_GET_LAST_PAY_DATE(X.FINANCE_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+
							 " DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'006',"+m_schema_name+".AF_CO_GET_CRIB_CF_RESTR_DATE(X.APPLICATION_NO),'') CF_RESTR_DATE, "+
							 " '' AMT_WR_OFF, "+
							 " "+m_schema_name+".AF_CO_GET_CF_CLOSE_DATE(X.APPLICATION_NO,'"+m_data_report_date+"') CF_CLOSE_DATE, "+
							 " '' LEAG_ACT_STAT, "+
							 " '' DATE_OF_FILED, "+
							 //" '' DATE_ORD_ISSU, "+
							 //" "+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)  SEC_COVER, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_SEC_TYPE(X.FINANCE_NO,X.TRANSACTION_TYPE) SEC_TYPE, "+ //DATE_ORD_ISSU //this has benn renamed as secutity type as per the new requirements from the CRIB
							 " '001' SEC_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)
							 " "+m_schema_name+".AF_GET_CRIB_GURA_STATUS(X.APPLICATION_NO) GURAN_AMT, "+
							 " '001' TRN_TYPE_CODE, "+
							 " '' DISPUT_ID "+
							 " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+
							 " "+m_schema_name+".AF_CO_MAS_CLIENT Y "+
							 " WHERE X.FINANCE_NO = '"+m_finance_no+"' "+
							 " AND X.CLIENT_CODE = Y.CLIENT_CODE ) ");
			
			boolean more3 = rs_cf.next();
			if(more3){
			//str_m_cf_details.append(rs_cf.getString(1));
			m_cf_details = rs_cf.getString(1);
			}
			
								
								
			rs_cs = stmt1.executeQuery (" SELECT  'CNCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'|| "+
			        " SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||NIC_NO||'|'||PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'|| "+
							" SALUTION||'|'||SUBJECT_NAME||'|'||PRE_SUBJECT_NAME||'|'||EMPLOYMENT||'|'||PROFESSION||'|'||EMP_NAME||'|'||BUSSINES_NAME||'|'|| "+
							" BUSINESS_CERTI_NO||'|'||REG_DATE||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'|| "+
							" MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3 "+
							" ||'|'||PERMENT_CITY||'|'||PERMNT_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE||'|'||TEL_NO||'|'||MOBILE_NO||'|'|| "+
							" EMAIL||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'||MARITAL_STATUS||'|'||SUPOSE_NAME "+
						  " FROM (SELECT B.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" B.SUBJECT_ID SUBJECT_ID, "+
							" B.PRE_SUBJECT_ID PRE_SUBJECT_ID, "+
							" NVL(A.NIC_NO,'') NIC_NO, "+
							" '' PRE_NIC , "+
							" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+ //modified by nuwan de silva on 06-11-08
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,'-'),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTION, "+
							" NVL(UPPER(FULL_NAME),'') SUBJECT_NAME, "+
							" '' PRE_SUBJECT_NAME, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_CLIENT_EMPLYMNT(A.CLIENT_CODE),'') EMPLOYMENT, "+
							" NVL(UPPER(EMP_RDESIGNATION),'') PROFESSION, "+
							" NVL(UPPER(EMP_NAME),'') EMP_NAME, "+
							" NVL(UPPER(BA_NATURE_OF_BUSINESS),'') BUSSINES_NAME, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTI_NO, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') REG_DATE, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
						  " SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+
							//" NVL(CITY_CODE,'') PERMENT_CITY, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							" '' PERMNT_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(MOBILE_NO,'') MOBILE_NO, "+
							//" NVL(UPPER(EMAIL),'') EMAIL, "+
							" '' EMAIL, "+
							//" TO_CHAR(DATE_OF_BIRTH,'DD-MON-YYYY') DATE_OF_BIRTH, "+
							" '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" DECODE(NVL(MARITAL_STATUS,''),'SINGLE','002','MARRIED','001') MARITAL_STATUS, "+
							" '' SUPOSE_NAME "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
							" (SELECT '' DATA_PRO_ID, "+
							" '' DATA_PRO_BRANCH, "+
							" Z.FINANCE_NO, "+
							" '' BUSI_REG, "+
							" Z.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(Z.CO_APPLICANT,'-') CO_APP, "+
							" Z.CLIENT_CODE  CLIENT_CODE "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Z "+
							" WHERE Z.FINANCE_NO = '"+m_finance_no+"') B "+
							" WHERE B.CLIENT_CODE = A.CLIENT_CODE	"+
							
							" UNION ALL "+
							
							" SELECT B.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" B.SUBJECT_ID SUBJECT_ID, "+
							" B.PRE_SUBJECT_ID PRE_SUBJECT_ID, "+
							" NVL(A.NIC_NO,'') NIC_NO, "+
							" '' PRE_NIC , "+
							" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,'-'),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTION, "+
							" NVL(UPPER(FULL_NAME),'') SUBJECT_NAME, "+
							" '' PRE_SUBJECT_NAME, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_CLIENT_EMPLYMNT(A.CLIENT_CODE),'') EMPLOYMENT, "+
							" NVL(UPPER(EMP_RDESIGNATION),'') PROFESSION, "+
							" NVL(UPPER(EMP_NAME),'') EMP_NAME, "+
							" NVL(UPPER(BA_NATURE_OF_BUSINESS),'') BUSSINES_NAME, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTI_NO, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') REG_DATE, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
						  " SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+
							//" NVL(CITY_CODE,'') PERMENT_CITY, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							" '' PERMNT_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(MOBILE_NO,'') MOBILE_NO, "+
							//" NVL(UPPER(EMAIL),'') EMAIL, "+
							" '' EMAIL, "+
							//" TO_CHAR(DATE_OF_BIRTH,'DD-MON-YYYY') DATE_OF_BIRTH, "+
							" '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" DECODE(NVL(MARITAL_STATUS,''),'SINGLE','002','MARRIED','001') MARITAL_STATUS, "+
							" '' SUPOSE_NAME "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
							" (SELECT '' DATA_PRO_ID, "+
							" '' DATA_PRO_BRANCH, "+
							" Z.FINANCE_NO, "+
							" '' BUSI_REG, "+
							" Z.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(Z.CO_APPLICANT,'-') CO_APP, "+
							" Z.CLIENT_CODE  CLIENT_CODE "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Z "+
							" WHERE Z.FINANCE_NO = '"+m_finance_no+"') B "+
							" WHERE B.CO_APP = A.CLIENT_CODE)");							
						
			m_cs_details="";
			
			boolean more4 = rs_cs.next();
			while(more4){
			m_cs_details = m_cs_details + rs_cs.getString(1);
			more4 = rs_cs.next();
			
			if(more4){
			m_cs_details = m_cs_details + "\n";
			}
			}
			

		
			
			rs_gs = stmt1.executeQuery (" SELECT 'CNGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||GURANT_ID||'|'||PRE_GURANT_ID||'|'||GUARAN_TYPE||'|'||GUARAN_NIC||'|'||PRE_NIC||'|'||CITIZEN "+
			        " ||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'||SALUATION||'|'||NAME||'|'||PRE_NAME||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'|| "+
							" MARITAL_STATUS||'|'||BUSI_REG||'|'||PRE_BUSI_REG||'|'||VAT_REG_NO||'|'||NAME_OF_BUSINESS||'|'||PRE_BUSSI_NAME||'|'||DATE_OF_INCORPORATION||'|'||MAIL_ADD1||'|'|| "+
							" MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE "+
							" ||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY||'|'||PERMNT_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE "+
							" ||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
							
							" FROM (SELECT B.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" B.SUBJECT_ID GURANT_ID, "+
							//" B.PRE_SUBJECT_ID PRE_GURANT_ID, "+
							" '' PRE_GURANT_ID, "+
							" DECODE(A.CLIENT_TYPE,'I','002','C','001') GUARAN_TYPE, "+
							" NVL(A.NIC_NO,'') GUARAN_NIC, "+
							" '' PRE_NIC, "+
							" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUATION, "+
							" NVL(UPPER(FULL_NAME),'') NAME, "+
							" '' PRE_NAME, "+
							//" TO_CHAR(DATE_OF_BIRTH,'DD-MON-YYYY') DATE_OF_BIRTH, "+
							" '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" DECODE(NVL(MARITAL_STATUS,''),'SINGLE','002','MARRIED','001') MARITAL_STATUS, "+
							" NVL(B.BUSI_REG,'') BUSI_REG, "+
							" '' PRE_BUSI_REG, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+ 
							" NVL(BA_NATURE_OF_BUSINESS,'') NAME_OF_BUSINESS, "+
							" '' PRE_BUSSI_NAME, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') DATE_OF_INCORPORATION, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(REGISTERED_CITY_CODE,CITY_CODE)),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+ 
							//" NVL(CITY_CODE,'') PERMENT_CITY, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(REGISTERED_CITY_CODE,CITY_CODE)),'') PERMENT_CITY , "+
							" '' PERMNT_POSTCODE, "+ 
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(MOBILE_NO,'') MOBILE_NO, "+
							" NVL(FAX_NO,'') FAX_NO, "+
							//" NVL(UPPER(EMAIL),'') EMAIL, "+
							" '' EMAIL, "+
							" '' URL "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+  
              "(SELECT GUARANTOR_CODE, "+
							" '' DATA_PRO_ID, "+
							" '' DATA_PRO_BRANCH, "+
							" A.FINANCE_NO, "+
							" '' BUSI_REG, "+
							" A.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID "+
							" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
							" "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B "+
							" WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
							" AND B.APPLICATION_NO = A.APPLICATION_NO ) B "+
							" WHERE B.GUARANTOR_CODE = A.CLIENT_CODE )");
      
			m_gs_details = "";

      boolean more6 = rs_gs.next();
			while(more6){
			m_gs_details = m_gs_details + rs_gs.getString(1);
			more6 = rs_gs.next();
			if(more6){
			m_gs_details = m_gs_details + "\n";
			}
			}			
      //temp comment 
			out.println(m_cf_details); 
			out.println(m_cs_details);
			if(!m_ss_details.equals("")){
			out.println(m_ss_details);
			}
			if(!m_gs_details.equals("")){
			out.println(m_gs_details); 
			}

		  /* --------- CORPORATE DETAILS ---------- */
			
			
			}else if(m_data_cf_type.equals("C")){
			

					
							
		rs_cf = stmt1.executeQuery (" SELECT 'CMCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_ACC_NUM||'|'||PRE_CF_ACC_NUM "+
		        " ||'|'||PACKGE_LOAN_ID||'|'||PACKGE_LOAN_AMT||'|'||APPROV_DATE||'|'||AMT_GRANTED||'|'||HI_CR_AMOUNT||'|'||CURRENCY||'|'|| "+
						" OWNERSIP||'|'||TRANSACTION_TYPE||'|'||PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE "+
						" ||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'||INT_OUTSTND||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'|| "+
						" LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'||CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'|| "+
						" SEC_TYPE||'|'||SECURTY_COVER||'|'||GURANTEE_COVER||'|'||TRN_TYPE_CODE||'|'||DISPUTE_ID "+
						" FROM(SELECT X.FINANCE_NO CF_ACC_NUM, "+
						" DECODE(SUBSTR(X.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
						" '' PRE_CF_ACC_NUM, "+
						//" NVL(X.MASTER_AGREEMENT_NO,'') PACKGE_LOAN_ID, "+
						" '' PACKGE_LOAN_ID, "+
						//" NVL(X.TOTAL_FINANCE_AMOUNT,0) PACKGE_LOAN_AMT, "+
						" '' PACKGE_LOAN_AMT, "+
						//" "+m_schema_name+".AF_CO_GET_MASTER_AGR_DATE(X.MASTER_AGREEMENT_NO) APPROV_DATE, "+
						" '' APPROV_DATE, "+
						" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO) AMT_GRANTED, "+
					  " '' HI_CR_AMOUNT, "+
						" "+m_schema_name+".AF_CO_GET_CRIB_CURRENCY(X.CURRENCY_CODE) CURRENCY, "+
						" DECODE(NVL(X.CO_APPLICANT,'-'),'-','001','null','001','002') OWNERSIP, "+ 
						" /*DECODE(NVL(X.CO_APPLICANT,'001'),'001','001','002') OWNERSIP,*/ "+
					  " "+m_schema_name+".AF_GET_CRIB_GURA_STATUS(X.APPLICATION_NO) GURANTEE_COVER, "+
						" "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE(X.TRANSACTION_TYPE) TRANSACTION_TYPE, "+
						" NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001') PURPOSE_OF_CF, "+
						" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO) NUM_OF_INSTALMENT, "+
						" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO) INSTALMENT_AMT, "+
						" "+m_schema_name+".AF_CO_GET_CRIB_RE_PAYMNT_TYPE(X.APPLICATION_NO) RE_PAYMNT_TYPE, "+
						//" TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE(X.APPLICATION_NO),'DD-MM-YYYY'),'DD-MON-YYYY') FST_DISB_DATE, "+
						" TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_FIRST_DISB_DATE(X.APPLICATION_NO),'DD-MM-YYYY'),'DD-MON-YYYY') FST_DISB_DATE, "+
						//" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_REANTAL_ARREARS(X.APPLICATION_NO,SYSDATE),0),0)) CURRENT_BALANCE, "+
						" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005','0','008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_CRIB(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0) + NVL("+m_schema_name+".AF_CO_GET_FUTURE_OUT(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"'),0),0)) CURRENT_BALANCE, "+
						//" "+m_schema_name+".AF_CO_GET_REANTAL_ARREARS(X.APPLICATION_NO,SYSDATE) CURRENT_BALANCE, "+
						//" ROUND(NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(X.FINANCE_NO,SYSDATE),0),0) INT_OUTSTND, "+
						" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'008',0,'007',0, ROUND(NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(X.FINANCE_NO,SYSDATE),0),0) ) INT_OUTSTND, "+
						" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0,ROUND(NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_CRIB(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0),0) ) AMT_IN_AREAS, "+
						//" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'008',0,'007',0, NVL("+m_schema_name+".AF_CO_GET_NUM_OF_ARREARS_DATE(X.FINANCE_NO,'"+m_data_report_date+"'),0) ) NUM_OF_DATES, "+
						" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'005',0,'008',0,'007',0, NVL("+m_schema_name+".AF_CO_GET_NUM_OF_ARREARS_DATE(X.FINANCE_NO,X.CLIENT_CODE,'"+m_data_report_date+"',"+m_username+"),0) ) NUM_OF_DATES, "+
						" "+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO) CF_STATUS, "+
						" "+m_schema_name+".AF_CO_GET_LAST_PAY_DATE(X.FINANCE_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+
						" DECODE("+m_schema_name+".AF_CO_GET_CRIB_CF_STATUS(X.APPLICATION_NO),'006',"+m_schema_name+".AF_CO_GET_CRIB_CF_RESTR_DATE(X.APPLICATION_NO),'') CF_RESTR_DATE, "+
						" '' AMT_WR_OFF, "+
						" "+m_schema_name+".AF_CO_GET_CF_CLOSE_DATE(X.APPLICATION_NO,'"+m_data_report_date+"') CF_CLOSE_DATE, "+
						" '' LEAG_ACT_STAT, "+
						" '' DATE_OF_FILED, "+
						//" '' DATE_ORD_ISSU, "+
						//" "+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO) SECURTY_COVER, "+
						" "+m_schema_name+".AF_CO_GET_CRIB_SEC_TYPE(X.FINANCE_NO,X.TRANSACTION_TYPE) SEC_TYPE, "+ //DATE_ORD_ISSU //this has benn renamed as secutity type as per the new requirements from the CRIB
						" '001' SECURTY_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)
						" (SELECT SUM(AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES "+
						" WHERE APPLICATION_NO = X.APPLICATION_NO) GURAN_COVER, "+
					  " '001' TRN_TYPE_CODE, "+
						" '' DISPUTE_ID "+
					  " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+
						" "+m_schema_name+".AF_CO_MAS_CLIENT Y "+
					  " WHERE X.FINANCE_NO = '"+m_finance_no+"' "+
						" AND X.CLIENT_CODE = Y.CLIENT_CODE)");						
							
					
							
			
			boolean more3 = rs_cf.next();
			if(more3){
			m_cf_details = rs_cf.getString(1);
			}	
			
			
				
	
			rs_cs = stmt1.executeQuery (" SELECT 'CMCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||BUS_REG_NUM||'|'||REG_DATE||'|'||PREV_REG_NUM||'|'||VAT_REG_NO "+
			        " ||'|'||LEAGLE_CONST||'|'||ECON_ACT_CODE1||'|'||ECON_ACT_CODE2||'|'||ECON_ACT_CODE3||'|'||SUBJECT_NAME||'|'||PREV_SUBJ_NAME||'|'|| "+
							" SUBJECT_SHORT_NAME||'|'||PRE_SUBJECT_SHORT_NAME||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||MAIL_POST_CODE "+
							" ||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||ADDRESS1||'|'||ADDRESS2||'|'||ADDRESS3||'|'||CITY_CODE "+
							" ||'|'||PER_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TELEPHN_CITY||'|'||TEL_NO||'|'|| "+
							" FAX_NO||'|'||URL "+
							
							" FROM (SELECT Z.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(Z.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" X.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUS_REG_NUM, "+
							" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY'),'') REG_DATE, "+
							" /*(SELECT DISTINCT NVL(BUSINESS_CERTIFICATE_NO,'-') "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BK A "+
							" WHERE A.CLIENT_CODE = X.CLIENT_CODE )*/ '' PREV_REG_NUM, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_LEAGLE_CONST(X.CLIENT_CODE) LEAGLE_CONST, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(X.BUSINESS_SUB_SECTOR) ECON_ACT_CODE1, "+
							" '' ECON_ACT_CODE2, "+
							" '' ECON_ACT_CODE3, "+ 
							" UPPER(NVL(FULL_NAME,'')) SUBJECT_NAME , "+
							//" UPPER("+m_schema_name+".AF_CO_GET_CRIB_PREV_SUBJ_NAME(X.CLIENT_CODE)) PREV_SUBJ_NAME, "+
							" '' PREV_SUBJ_NAME, "+
							" '' SUBJECT_SHORT_NAME, "+
							" '' PRE_SUBJECT_SHORT_NAME, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+ 
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') MAIL_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),X.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" UPPER(NVL(ADDRESS1,'')) ADDRESS1, "+
							" SUBSTR(UPPER(NVL(ADDRESS2,'')),0,40) ADDRESS2, "+
							" '' ADDRESS3, "+
							" UPPER(NVL(CITY_CODE,'')) CITY_CODE, "+
							" NVL(POSTALCODE,'') PER_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(CITY_CODE) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(CITY_CODE) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(CITY_CODE,X.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TELEPHN_CITY, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(FAX_NO,'') FAX_NO, "+
							" '' URL "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT X, "+
							" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Z "+
							" WHERE X.CLIENT_CODE = Z.CLIENT_CODE AND "+
							" Z.FINANCE_NO ='"+m_finance_no+"' "+
							
							" UNION ALL "+
							
							"	SELECT Z.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(Z.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" X.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUS_REG_NUM, "+
							" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY'),'') REG_DATE, "+
							" /*(SELECT DISTINCT NVL(BUSINESS_CERTIFICATE_NO,'-') "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BK A "+
							" WHERE A.CLIENT_CODE = X.CLIENT_CODE )*/ '' PREV_REG_NUM, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_LEAGLE_CONST(X.CLIENT_CODE) LEAGLE_CONST, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(X.BUSINESS_SUB_SECTOR) ECON_ACT_CODE1, "+
							" '' ECON_ACT_CODE2, "+
							" '' ECON_ACT_CODE3, "+ 
							" UPPER(NVL(FULL_NAME,'')) SUBJECT_NAME , "+
							//" UPPER("+m_schema_name+".AF_CO_GET_CRIB_PREV_SUBJ_NAME(X.CLIENT_CODE)) PREV_SUBJ_NAME, "+
							" '' PREV_SUBJ_NAME, "+
							" '' SUBJECT_SHORT_NAME, "+
							" '' PRE_SUBJECT_SHORT_NAME, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+ 
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') MAIL_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),X.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" UPPER(NVL(ADDRESS1,'')) ADDRESS1, "+
							" SUBSTR(UPPER(NVL(ADDRESS2,'')),0,40) ADDRESS2, "+
							" '' ADDRESS3, "+
							" UPPER(NVL(CITY_CODE,'')) CITY_CODE, "+
							" NVL(POSTALCODE,'') PER_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(CITY_CODE) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(CITY_CODE) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(CITY_CODE,X.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TELEPHN_CITY, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(FAX_NO,'') FAX_NO, "+
							" '' URL "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT X, "+
							" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Z "+
							" WHERE X.CLIENT_CODE = Z.CO_APPLICANT AND "+
							" Z.FINANCE_NO ='"+m_finance_no+"') ");
				
			m_cs_details ="";
			
			boolean more4= rs_cs.next();
			while(more4){
			m_cs_details = m_cs_details + rs_cs.getString(1);
			more4 = rs_cs.next();
			
			if(more4){
			m_cs_details = m_cs_details + "\n";
			}		
			}		
			
			
			
			
			
		rs_rs = stmt1.executeQuery (" SELECT  'CMRS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NO||'|'||BUSI_REG_NO||'|'||SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||RELATION_ID||'|'||PRE_RELATION_ID||'|'|| "+
		              " RELATED_TYPE||'|'||NATURE_OF_RELATION||'|'||RELATION_NIC_NO||'|'||RELATION_PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO "+
									" ||'|'||DRIVING_LICENSE_NO||'|'||SALUTATION||'|'||NAME||'|'||PRE_NAME||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'||MARITAL_STATUS||'|'|| "+
									" BUSINESS_CERTI_NO||'|'||PRE_BUS_REG_NO||'|'||VAT_REG_NO||'|'||ENTITY_NAME||'|'||PRE_ENTITY_NAME||'|'||DATE_OF_REG||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'|| "+
									" MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY||'|'||PERM_POST_CODE||'|'|| "+
									" DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
               
								  " FROM (SELECT B.FINANCE_NO CF_ACC_NO, "+
									" DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
									" NVL(B.BUSI_REG,'') BUSI_REG_NO, "+
									" NVL(B.SUBJECT_ID,'') SUBJECT_ID, "+
									//" NVL(B.PRE_SUBJECT_ID,'') PRE_SUBJECT_ID, "+
									" '' PRE_SUBJECT_ID, "+
									" NVL(A.CLIENT_CODE,'') RELATION_ID, "+
									" '' PRE_RELATION_ID, "+
									" DECODE(A.CLIENT_TYPE,'I','002','C','001') RELATED_TYPE, "+
									" NVL(A.BUSINESS_SUB_SECTOR,'') NATURE_OF_RELATION, "+
									" NVL(A.NIC_NO,'') RELATION_NIC_NO, "+
									" '' RELATION_PRE_NIC, "+
									" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
									//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
									" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
									" NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
									" DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTATION, "+
									" NVL(UPPER(FULL_NAME),'') NAME, "+
									" '' PRE_NAME, "+ 
									//" TO_CHAR(DATE_OF_BIRTH,'DD-MON-YYYY') DATE_OF_BIRTH, "+
									" '' DATE_OF_BIRTH, "+
									" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
									" DECODE(NVL(MARITAL_STATUS,''),'SINGLE','002','MARRIED','001') MARITAL_STATUS , "+
									//" NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTI_NO, "+
									" '' BUSINESS_CERTI_NO, "+
									" '' PRE_BUS_REG_NO, "+
									" NVL(VAT_REG_NO,'') VAT_REG_NO, "+
									//" NVL(UPPER(EMP_NAME),'') ENTITY_NAME, "+
									" '' ENTITY_NAME, "+
									" '' PRE_ENTITY_NAME, "+ 
									//" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') DATE_OF_REG, "+
									" '' DATE_OF_REG, "+
									" NVL(UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)),'') MAIL_ADD1, "+
									" SUBSTR(NVL(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),''),0,40) MAIL_ADD2, "+
									" '' MAIL_ADD3, "+
									" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
									" NVL(POSTALCODE,'') POSTALCODE, "+
									" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
									" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
									" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
									" NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
									" SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
									" '' PERMENT_ADD3, "+ 
									//" NVL(CITY_CODE,'') PERMENT_CITY, "+
									" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
									" '' PERM_POST_CODE, "+
									" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(CITY_CODE) DISTRICT_CODE, "+
									" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(CITY_CODE) PROVINCE_CODE, "+
									" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(CITY_CODE,A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
									" NVL(TEL_NO,'') TEL_NO, "+
									" NVL(MOBILE_NO,'') MOBILE_NO, "+
									" NVL(FAX_NO,'') FAX_NO, "+
									//" NVL(UPPER(EMAIL),'') EMAIL, "+
									" '' EMAIL, "+
									" '' URL "+
									
									" FROM "+
									" "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
									" (SELECT '' DATA_PRO_ID, "+
									" '' DATA_PRO_BRANCH, "+
									" Z.FINANCE_NO, "+
									" '' BUSI_REG, "+
									" '' SUBJECT_ID, "+
									" '' PRE_SUBJECT_ID, "+
									" NVL(Z.CO_APPLICANT,'') CO_APP, "+
									" Z.CO_APPLICANT  PRE_CO_APP "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Z "+
									" WHERE Z.FINANCE_NO = '"+m_finance_no+"' ) B "+
									" WHERE B.CO_APP = A.CLIENT_CODE ) ");

      m_rs_details ="";
			
		  boolean more5 = rs_rs.next();
			while(more5){
			m_rs_details = m_rs_details + rs_rs.getString(1);
			more5 = rs_rs.next();
			}
			
      			
			
			rs_gs = stmt1.executeQuery (" SELECT  'CMGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NO||'|'||GUARAN_ID||'|'||PRE_GUARAN_ID||'|'||GURA_TYPE||'|'||NIC_NO||'|'||PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO "+
			         " ||'|'||DRIVING_LICENSE_NO||'|'||SALUTATION||'|'||GUAR_NAME||'|'||GUAR_PREV_NAME||'|'||GUAR_DATE_OF_BIRTH||'|'||GENDER "+
							 " ||'|'||MARITAL_STATUS||'|'||BUSINESS_CERTIFICATE_NO||'|'||PRE_BUSS_REG_NO||'|'||VAT_REG_NO||'|'||ENTITY_NAME||'|'||PRE_ENTITY_NAME "+
							 " ||'|'||DATE_OF_REG||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'|| "+
							 " MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY "+
							 " ||'|'||PERM_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||CITY_TEL_CODE||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
								
							 " FROM (SELECT B.FINANCE_NO CF_ACC_NO, "+
							 " DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/	
							 " A.CLIENT_CODE GUARAN_ID, "+
							 " '' PRE_GUARAN_ID, "+
							 " DECODE(A.CLIENT_TYPE,'I','002','C','001') GURA_TYPE, "+
							 " NVL(A.NIC_NO,'') NIC_NO, "+
							 " '' PRE_NIC, "+
							 " DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
							 //" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
								" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							 " NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							 //" DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTATION, "+
							 " DECODE(A.CLIENT_TYPE,'I',DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999'),' ') SALUTATION, "+
							 //" UPPER(FULL_NAME) GUAR_NAME, "+
							 " DECODE(A.CLIENT_TYPE,'I',UPPER(FULL_NAME),' ') GUAR_NAME, "+
							 " '' GUAR_PREV_NAME, "+
							 //" TO_CHAR(A.DATE_OF_BIRTH,'DD-MON-YYYY') GUAR_DATE_OF_BIRTH, "+
								" '' GUAR_DATE_OF_BIRTH , "+
							 " DECODE(NVL(A.GENDER,'M'),'M','001','F','002') GENDER, "+
							 " DECODE(NVL(A.MARITAL_STATUS,'-'),'SINGLE','002','MARRIED','001') MARITAL_STATUS , "+
							 " NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTIFICATE_NO, "+
							 " '' PRE_BUSS_REG_NO, "+
							 " NVL(VAT_REG_NO,'') VAT_REG_NO, "+ 
							 //" NVL(UPPER(SUBSTR(FULL_NAME,0,198)),'') ENTITY_NAME, "+
							 " DECODE(A.CLIENT_TYPE,'C',NVL(UPPER(SUBSTR(FULL_NAME,0,198)),''),' ') ENTITY_NAME, "+
							 " '' PRE_ENTITY_NAME, "+ 
							 " TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') DATE_OF_REG, "+
							 " NVL(UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)),'') MAIL_ADD1, "+
							 " SUBSTR(NVL(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),''),0,40) MAIL_ADD2, "+
							 " '' MAIL_ADD3, "+	
							 " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							 " NVL(POSTALCODE,'') POSTALCODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							 " NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							 " NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							 " SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							 " '' PERMENT_ADD3, "+	
							 //" NVL(CITY_CODE,'') PERMENT_CITY, "+
								" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							 " '' PERM_POSTCODE, "+	
							 " '' CITY_TEL_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(CITY_CODE) DISTRICT_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(CITY_CODE) PROVINCE_CODE, "+
							 " NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(CITY_CODE,A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							 " '' CITY_POSTCODE, "+	
							 " NVL(TEL_NO,'') TEL_NO, "+
							 " NVL(MOBILE_NO,'') MOBILE_NO, "+
							 " NVL(FAX_NO,'') FAX_NO, "+
							 //" NVL(UPPER(EMAIL),'') EMAIL, "+
								" '' EMAIL, "+	
							 " '' URL "+	
								
							 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
							 " ( SELECT GUARANTOR_CODE, "+
							 " '' DATA_PRO_ID, "+
							 " '' DATA_PRO_BRANCH, "+
							 " A.FINANCE_NO, "+
							 " '' BUSI_REG, "+
							 " A.CLIENT_CODE SUBJECT_ID, "+
							 " '' PRE_SUBJECT_ID "+
							 " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
							 " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B "+
							 " WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
							 " AND B.APPLICATION_NO = A.APPLICATION_NO ) B "+
							 " WHERE B.GUARANTOR_CODE = A.CLIENT_CODE )");


      /*boolean more7 = rs_gs.next();
			if(more7){
			m_gs_details = rs_gs.getString(1);
			}	*/		
      
			
			m_gs_details = "";

      boolean more7 = rs_gs.next();
			while(more7){
				m_gs_details = m_gs_details + rs_gs.getString(1);
				more7 = rs_gs.next();
				
			if(more7){
			m_gs_details = m_gs_details + "\n";
			}			
			}			
		
		
			out.println(m_cf_details); 
			out.println(m_cs_details); 
			if(!m_rs_details.equals("")){
			out.println(m_rs_details); 
			}
			if(!m_ss_details.equals("")){
			out.println(m_ss_details); 
			}
			if(!m_gs_details.equals("")){
			out.println(m_gs_details); 
			}			
			}
		
			more2 = rs.next();
			}			
							
			
			
			
			//Factoring Details Comes From here added by ns on 05/10/2011
			
			rs = stmt.executeQuery (" SELECT COUNT(A.FACILITY_NO) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CO_MAS_CLIENT  B "+
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
			" AND   B.CLIENT_TYPE       = '"+m_data_cf_type+"' "+
			" AND   A.FACILITY_STATUS   IN ('Y','T') "+
			//" AND   FACILITY_START_DATE < TO_DATE('"+m_data_report_date+"','DD-MM-YYYY') ");
			"");
			
			if(rs.next()){
			m_data_count_fac = rs.getInt(1); 
			}
			
				
			rs = stmt.executeQuery (" SELECT A.FACILITY_NO, "+
			" A.CLIENT_CODE "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CO_MAS_CLIENT  B "+
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
			" AND   B.CLIENT_TYPE       = '"+m_data_cf_type+"' "+
			" AND   A.FACILITY_STATUS   IN ('Y','T') "+
			//" AND   FACILITY_START_DATE < TO_DATE('"+m_data_report_date+"','DD-MM-YYYY') ");
			"");
			
			boolean more_fac = rs.next();
			
			while(more_fac){
			m_facility_no = rs.getString(1);
						
			if(m_data_cf_type.equals("I")){
			
			rs_cf = stmt1.executeQuery (
			//out.println(
			" SELECT 'CNCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_NUMBER||'|'||PRI_CARD_NUM||'|'||PRI_CARD_LIM||'|'||PRE_CF_NUM||'|'|| "+
			" PRE_CARD_NUM||'|'||CF_APPR_DATE||'|'||AMNT_GRANT||'|'||HI_CR_AMNT||'|'||CURRENCY||'|'||OWNERSIP||'|'||TRANSACTION_TYPE||'|'|| "+
			" PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'|| "+
			" INT_OUT_BAL||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'||LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'|| "+
			" CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'||SEC_TYPE||'|'||SEC_COVER||'|'||GURAN_AMT||'|'||TRN_TYPE_CODE "+
			" ||'|'||DISPUT_ID "+
			" FROM(SELECT X.FACILITY_NO  CF_NUMBER , "+
			" DECODE(SUBSTR(X.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
			" '' PRI_CARD_NUM, "+
			" '' PRI_CARD_LIM, "+
			" '' PRE_CF_NUM, "+
			" '' PRE_CARD_NUM, "+
			" '' CF_APPR_DATE, "+
			" NVL(CREDIT_LIMIT,0)  AMNT_GRANT, "+ 
			" '' HI_CR_AMNT, "+
			" "+m_schema_name+".AF_CO_GET_CRIB_CURRENCY('LKR')  CURRENCY, "+ 
			" '001' OWNERSIP, "+		 
			" "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE('FA') TRANSACTION_TYPE, "+ 
			" '02:07:001'   PURPOSE_OF_CF, "+ //NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001')
			" 0  NUM_OF_INSTALMENT, "+
			" 0 INSTALMENT_AMT, "+ 
			" '004' RE_PAYMNT_TYPE, "+ 
			" TO_CHAR(NVL(APP_DATE,X.ENT_DATE),'DD-MON-YYYY') FST_DISB_DATE, "+
			" ROUND(DECODE(X.FACILITY_STATUS,'T',0,NVL("+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT(X.CLIENT_CODE,X.FACILITY_NO,'"+m_data_report_date+"','ACT'),0)),0)  CURRENT_BALANCE, "+ 
			" 0 INT_OUT_BAL, "+ 
			" ROUND(NVL("+m_schema_name+".FA_GET_ARREARS_AMOUNT(X.FACILITY_NO,'"+m_data_report_date+"'),0),0) AMT_IN_AREAS, "+
			" "+m_schema_name+".FA_GET_ARREARS_AMOUNT_DATE(X.FACILITY_NO,'"+m_data_report_date+"') NUM_OF_DATES, "+ 
			" "+m_schema_name+".FA_CO_GET_CRIB_CF_STATUS(X.FACILITY_NO) CF_STATUS, "+ 
			" "+m_schema_name+".FA_CO_GET_LAST_PAY_DATE(X.FACILITY_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+ 
			" '' CF_RESTR_DATE, "+
			" '' AMT_WR_OFF, "+
			" "+m_schema_name+".FA_CO_GET_CF_CLOSE_DATE(X.FACILITY_NO) CF_CLOSE_DATE, "+ 
			" '' LEAG_ACT_STAT, "+
			" '' DATE_OF_FILED, "+
			" '001' SEC_TYPE, "+ //AF_CO_GET_CRIB_SEC_TYPE
			" '001' SEC_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO) //003
			" '001' GURAN_AMT, "+ //OTHER GUARANTEE 001 //NOT GUARANTEE 002
			" '001' TRN_TYPE_CODE, "+
			" '' DISPUT_ID "+
			" FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY X, "+
			" "+m_schema_name+".FA_CO_MAS_CLIENT Y "+
			" WHERE X.CLIENT_CODE = Y.CLIENT_CODE "+
			" AND   X.FACILITY_NO = '"+m_facility_no+"' ) ");
						
			m_cf_details=""; 
			//boolean more3 = rs_cf.next();
			if(rs_cf.next()){
			m_cf_details = rs_cf.getString(1);
			}
			//out.println(m_cf_details);
								
								
			rs_cs = stmt1.executeQuery (
			//out.println(
			" SELECT  'CNCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'|| "+
			        " SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||NIC_NO||'|'||PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'|| "+
							" SALUTION||'|'||SUBJECT_NAME||'|'||PRE_SUBJECT_NAME||'|'||EMPLOYMENT||'|'||PROFESSION||'|'||EMP_NAME||'|'||BUSSINES_NAME||'|'|| "+
							" BUSINESS_CERTI_NO||'|'||REG_DATE||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'|| "+
							" MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3 "+
							" ||'|'||PERMENT_CITY||'|'||PERMNT_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE||'|'||TEL_NO||'|'||MOBILE_NO||'|'|| "+
							" EMAIL||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'||MARITAL_STATUS||'|'||SUPOSE_NAME "+
						    " FROM (SELECT B.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
							" B.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(A.NIC_NO,'') NIC_NO, "+
							" '' PRE_NIC , "+
							//" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+ //modified by nuwan de silva on 06-11-08
							"  '001' CITIZEN,"+
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" '' DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,'-'),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTION, "+
							" NVL(UPPER(FULL_NAME),'') SUBJECT_NAME, "+
							" '' PRE_SUBJECT_NAME, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_CLIENT_EMPLYMNT(A.CLIENT_CODE),'') EMPLOYMENT, "+
							" '' PROFESSION, "+
							" '' EMP_NAME, "+
							" '' BUSSINES_NAME, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTI_NO, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') REG_DATE, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,REGISTERED_ADDRESS1)) MAIL_ADD1, "+
						  " SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,REGISTERED_ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" '' POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(REGISTERED_ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(REGISTERED_ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							" '' PERMNT_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(REGISTERED_TEL_NO,'') TEL_NO, "+
							" NVL(REGISTERED_MOBILE_NO,'') MOBILE_NO, "+
							" '' EMAIL, "+
						    " '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" '' MARITAL_STATUS, "+
							" '' SUPOSE_NAME "+
							" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY B ,"+m_schema_name+".FA_CO_MAS_CLIENT A  "+
							" WHERE B.CLIENT_CODE = A.CLIENT_CODE "+
							" AND B.FACILITY_NO ='"+m_facility_no+"' "+
							" ) "+
							"");
							
			m_cs_details="";
			
			//boolean more4 = rs_cs.next();
			if(rs_cs.next()){
			m_cs_details =rs_cs.getString(1);
			}		
			//out.println(m_cs_details);
			
							rs_gs = stmt1.executeQuery (
							" SELECT 'CNGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||GURANT_ID||'|'||PRE_GURANT_ID||'|'||GUARAN_TYPE||'|'||GUARAN_NIC||'|'||PRE_NIC||'|'||CITIZEN "+
							" ||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'||SALUATION||'|'||NAME||'|'||PRE_NAME||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'|| "+
							" MARITAL_STATUS||'|'||BUSI_REG||'|'||PRE_BUSI_REG||'|'||VAT_REG_NO||'|'||NAME_OF_BUSINESS||'|'||PRE_BUSSI_NAME||'|'||DATE_OF_INCORPORATION||'|'||MAIL_ADD1||'|'|| "+
							" MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE "+
							" ||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY||'|'||PERMNT_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE "+
							" ||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
							
							" FROM (SELECT B.FINANCE_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FINANCE_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/
							" B.SUBJECT_ID GURANT_ID, "+
							//" B.PRE_SUBJECT_ID PRE_GURANT_ID, "+
							" '' PRE_GURANT_ID, "+
							" DECODE(A.CLIENT_TYPE,'I','002','C','001') GUARAN_TYPE, "+
							" NVL(A.NIC_NO,'') GUARAN_NIC, "+
							" '' PRE_NIC, "+
							" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUATION, "+
							" NVL(UPPER(FULL_NAME),'') NAME, "+
							" '' PRE_NAME, "+
							//" TO_CHAR(DATE_OF_BIRTH,'DD-MON-YYYY') DATE_OF_BIRTH, "+
							" '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" DECODE(NVL(MARITAL_STATUS,''),'SINGLE','002','MARRIED','001') MARITAL_STATUS, "+
							" NVL(B.BUSI_REG,'') BUSI_REG, "+
							" '' PRE_BUSI_REG, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+ 
							" NVL(BA_NATURE_OF_BUSINESS,'') NAME_OF_BUSINESS, "+
							" '' PRE_BUSSI_NAME, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') DATE_OF_INCORPORATION, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(REGISTERED_CITY_CODE,CITY_CODE)),'') MAIL_CITY, "+
							" NVL(POSTALCODE,'') POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+ 
							//" NVL(CITY_CODE,'') PERMENT_CITY, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(REGISTERED_CITY_CODE,CITY_CODE)),'') PERMENT_CITY , "+
							" '' PERMNT_POSTCODE, "+ 
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(TEL_NO,'') TEL_NO, "+
							" NVL(MOBILE_NO,'') MOBILE_NO, "+
							" NVL(FAX_NO,'') FAX_NO, "+
							//" NVL(UPPER(EMAIL),'') EMAIL, "+
							" '' EMAIL, "+
							" '' URL "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+  
							"(SELECT GUARANTOR_CODE, "+
							" '' DATA_PRO_ID, "+
							" '' DATA_PRO_BRANCH, "+
							" A.FINANCE_NO, "+
							" '' BUSI_REG, "+
							" A.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID "+
							" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
							" "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B "+
							" WHERE  A.FINANCE_NO = '"+m_facility_no+"' "+
							" AND B.APPLICATION_NO = A.APPLICATION_NO ) B "+
							" WHERE B.GUARANTOR_CODE = A.CLIENT_CODE )");
							
							m_gs_details = "";
							
							boolean more6 = rs_gs.next();
							while(more6){
							m_gs_details = m_gs_details + rs_gs.getString(1);
							more6 = rs_gs.next();
							if(more6){
							m_gs_details = m_gs_details + "\n";
							}
							}	
			
                            //COMENT TEMP 
							/*out.println(m_cf_details); 
							out.println(m_cs_details);
							if(!m_ss_details.equals("")){
							out.println(m_ss_details);
							}
							if(!m_gs_details.equals("")){
							out.println(m_gs_details); 
							}*/

		  
			//---***** Factoring Co-operate Details  **** --- //
			}else if(m_data_cf_type.equals("C")){
			
				rs_cf = stmt1.executeQuery (" SELECT 'CMCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_ACC_NUM||'|'||PRE_CF_ACC_NUM "+
		        " ||'|'||PACKGE_LOAN_ID||'|'||PACKGE_LOAN_AMT||'|'||APPROV_DATE||'|'||AMT_GRANTED||'|'||HI_CR_AMOUNT||'|'||CURRENCY||'|'|| "+
						" OWNERSIP||'|'||TRANSACTION_TYPE||'|'||PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE "+
						" ||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'||INT_OUTSTND||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'|| "+
						" LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'||CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'|| "+
						" SEC_TYPE||'|'||SECURTY_COVER||'|'||GURANTEE_COVER||'|'||TRN_TYPE_CODE||'|'||DISPUTE_ID "+
						" FROM( "+
						
							 " SELECT X.FACILITY_NO  CF_ACC_NUM , "+
							 " DECODE(SUBSTR(X.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
							 " '' PRE_CF_ACC_NUM, "+
							 " '' PACKGE_LOAN_ID, "+
							 " '' PACKGE_LOAN_AMT, "+
						 	 " '' APPROV_DATE, "+
							 " NVL(CREDIT_LIMIT,0)  AMT_GRANTED, "+ 
							 " '' HI_CR_AMOUNT, "+
							 " 'LKR' CURRENCY, "+ 
							 " '001' OWNERSIP, "+		
							 " "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE('FA') TRANSACTION_TYPE, "+ 
							 " '02:07:001'   PURPOSE_OF_CF, "+ //NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001')
							 " 0  NUM_OF_INSTALMENT, "+//"+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)
							 " 0 INSTALMENT_AMT, "+ 
							 " '004' RE_PAYMNT_TYPE, "+ 
							 " TO_CHAR(NVL(APP_DATE,X.ENT_DATE),'DD-MON-YYYY') FST_DISB_DATE, "+ 
							 " ROUND(DECODE(X.FACILITY_STATUS,'T',0,NVL("+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT(X.CLIENT_CODE,X.FACILITY_NO,'"+m_data_report_date+"','ACT'),0)),0)  CURRENT_BALANCE, "+ 
							 " 0 INT_OUTSTND, "+ 
							 " ROUND(NVL("+m_schema_name+".FA_GET_ARREARS_AMOUNT(X.FACILITY_NO,'"+m_data_report_date+"'),0),0) AMT_IN_AREAS, "+
			                 " "+m_schema_name+".FA_GET_ARREARS_AMOUNT_DATE(X.FACILITY_NO,'"+m_data_report_date+"') NUM_OF_DATES, "+ 
							 //" 0 AMT_IN_AREAS, "+
							 //" 0 NUM_OF_DATES, "+
							 " "+m_schema_name+".FA_CO_GET_CRIB_CF_STATUS(X.FACILITY_NO) CF_STATUS, "+ 
							 " "+m_schema_name+".FA_CO_GET_LAST_PAY_DATE(X.FACILITY_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+ 
							 " '' CF_RESTR_DATE, "+
							 " '' AMT_WR_OFF, "+
							 " "+m_schema_name+".FA_CO_GET_CF_CLOSE_DATE(X.FACILITY_NO) CF_CLOSE_DATE, "+ 
							 " '' LEAG_ACT_STAT, "+
							 " '' DATE_OF_FILED, "+
							 " '' SEC_TYPE, "+
							 " '003'  SECURTY_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)
							 " "+m_schema_name+".FA_GET_CRIB_GURA_STATUS(X.FACILITY_NO) GURANTEE_COVER, "+ //OTHER GUARANTEE 001 NOT GUARANTEE 002
							 " '001' TRN_TYPE_CODE, "+
							 " '' DISPUTE_ID "+
							 " FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY X, "+
							 " "+m_schema_name+".FA_CO_MAS_CLIENT Y "+
							 " WHERE X.CLIENT_CODE = Y.CLIENT_CODE "+
							 " AND   X.FACILITY_NO = '"+m_facility_no+"' ) ");						
							
							 if(rs_cf.next()){
							 m_cf_details = rs_cf.getString(1);
							 }	
			
							
							rs_cs = stmt1.executeQuery (" SELECT 'CMCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||BUS_REG_NUM||'|'||REG_DATE||'|'||PREV_REG_NUM||'|'||VAT_REG_NO "+
			        " ||'|'||LEAGLE_CONST||'|'||ECON_ACT_CODE1||'|'||ECON_ACT_CODE2||'|'||ECON_ACT_CODE3||'|'||SUBJECT_NAME||'|'||PREV_SUBJ_NAME||'|'|| "+
							" SUBJECT_SHORT_NAME||'|'||PRE_SUBJECT_SHORT_NAME||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||MAIL_POST_CODE "+
							" ||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||ADDRESS1||'|'||ADDRESS2||'|'||ADDRESS3||'|'||CITY_CODE "+
							" ||'|'||PER_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TELEPHN_CITY||'|'||TEL_NO||'|'|| "+
							" FAX_NO||'|'||URL "+
							
							" FROM ("+
							" SELECT Z.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(Z.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID,   "+
							" X.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUS_REG_NUM, "+
							" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY'),'') REG_DATE, "+
							" '' PREV_REG_NUM, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_LEAGLE_CONST(X.CLIENT_CODE) LEAGLE_CONST, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(X.BUSINESS_SUB_SECTOR) ECON_ACT_CODE1, "+
							" '' ECON_ACT_CODE2, "+
							" '' ECON_ACT_CODE3,  "+
							" UPPER(NVL(FULL_NAME,'')) SUBJECT_NAME , "+
							" '' PREV_SUBJ_NAME, "+
							" '' SUBJECT_SHORT_NAME, "+
							" '' PRE_SUBJECT_SHORT_NAME, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,CORRES_ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,CORRES_ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(CITY_CODE,CORRES_CITY_CODE))),'') MAIL_CITY, "+
							" '' MAIL_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CORRES_CITY_CODE),X.CLIENT_CODE),' ') MAIL_COUNTRY_CODE,  "+
							" UPPER(NVL(CORRES_ADDRESS1,REGISTERED_ADDRESS1)) ADDRESS1, "+
							" SUBSTR(UPPER(NVL(CORRES_ADDRESS2,REGISTERED_ADDRESS2)),0,40) ADDRESS2, "+
							" '' ADDRESS3, "+
							" UPPER(NVL(CORRES_CITY_CODE,CITY_CODE)) CITY_CODE, "+
							" '' PER_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CORRES_CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CORRES_CITY_CODE,CITY_CODE)) PROVINCE_CODE,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CORRES_CITY_CODE,CITY_CODE),X.CLIENT_CODE),' ') COUNTRY_CODE,  "+
							" '' TELEPHN_CITY,  "+
							" NVL(REGISTERED_TEL_NO,'') TEL_NO,  "+
							" NVL(REGISTERED_FAX_NO,'') FAX_NO,  "+
							" '' URL  "+
							" FROM "+m_schema_name+".FA_CO_MAS_CLIENT X,  "+
							" "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY Z  "+
							" WHERE X.CLIENT_CODE = Z.CLIENT_CODE "+
							" AND  Z.FACILITY_NO ='"+m_facility_no+"' "+
							" ) "+
							"");
							

							m_cs_details ="";
							
							/*boolean more4= rs_cs.next();
							while(more4){
							m_cs_details = m_cs_details + rs_cs.getString(1);
							more4 = rs_cs.next();
							
							if(more4){
							m_cs_details = m_cs_details + "\n";
							}		
							}	*/
							if(rs_cs.next()){
							m_cs_details =rs_cs.getString(1);
							}
							
							
							
							
								rs_gs = stmt1.executeQuery (" SELECT  'CMGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NO||'|'||GUARAN_ID||'|'||PRE_GUARAN_ID||'|'||GURA_TYPE||'|'||NIC_NO||'|'||PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO "+
			         " ||'|'||DRIVING_LICENSE_NO||'|'||SALUTATION||'|'||GUAR_NAME||'|'||GUAR_PREV_NAME||'|'||GUAR_DATE_OF_BIRTH||'|'||GENDER "+
							 " ||'|'||MARITAL_STATUS||'|'||BUSINESS_CERTIFICATE_NO||'|'||PRE_BUSS_REG_NO||'|'||VAT_REG_NO||'|'||ENTITY_NAME||'|'||PRE_ENTITY_NAME "+
							 " ||'|'||DATE_OF_REG||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'|| "+
							 " MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY "+
							 " ||'|'||PERM_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||CITY_TEL_CODE||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
								
							 " FROM (SELECT '"+m_facility_no+"' CF_ACC_NO, "+
							 " DECODE(SUBSTR('"+m_facility_no+"',0,2),'BL','BD','HO') DPOINT_ID, "+  /*ADDED BY CHANDANA ON 04/03/2008*/	
							 " A.CLIENT_CODE GUARAN_ID, "+
							 " '' PRE_GUARAN_ID, "+
							 " DECODE(A.CLIENT_TYPE,'I','002','C','001') GURA_TYPE, "+
							 " NVL(A.NIC_NO,'') NIC_NO, "+
							 " '' PRE_NIC, "+
							 " DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+
							 //" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
								" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							 " NVL(A.DRIVING_LICENSE_NO,'') DRIVING_LICENSE_NO, "+
							 //" DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTATION, "+
							 " DECODE(A.CLIENT_TYPE,'I',DECODE(NVL(TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999'),' ') SALUTATION, "+
							 //" UPPER(FULL_NAME) GUAR_NAME, "+
							 " DECODE(A.CLIENT_TYPE,'I',UPPER(FULL_NAME),' ') GUAR_NAME, "+
							 " '' GUAR_PREV_NAME, "+
							 //" TO_CHAR(A.DATE_OF_BIRTH,'DD-MON-YYYY') GUAR_DATE_OF_BIRTH, "+
								" '' GUAR_DATE_OF_BIRTH , "+
							 " DECODE(NVL(A.GENDER,'M'),'M','001','F','002') GENDER, "+
							 " DECODE(NVL(A.MARITAL_STATUS,'-'),'SINGLE','002','MARRIED','001') MARITAL_STATUS , "+
							 " NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTIFICATE_NO, "+
							 " '' PRE_BUSS_REG_NO, "+
							 " NVL(VAT_REG_NO,'') VAT_REG_NO, "+ 
							 //" NVL(UPPER(SUBSTR(FULL_NAME,0,198)),'') ENTITY_NAME, "+
							 " DECODE(A.CLIENT_TYPE,'C',NVL(UPPER(SUBSTR(FULL_NAME,0,198)),''),' ') ENTITY_NAME, "+
							 " '' PRE_ENTITY_NAME, "+ 
							 " TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') DATE_OF_REG, "+
							 " NVL(UPPER(NVL(REGISTERED_ADDRESS1,ADDRESS1)),'') MAIL_ADD1, "+
							 " SUBSTR(NVL(UPPER(NVL(REGISTERED_ADDRESS2,ADDRESS2)),''),0,40) MAIL_ADD2, "+
							 " '' MAIL_ADD3, "+	
							 " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(REGISTERED_CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							 " NVL(POSTALCODE,'') POSTALCODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							 " NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(REGISTERED_CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							 " NVL(UPPER(ADDRESS1),'') PERMENT_ADD1, "+
							 " SUBSTR(NVL(UPPER(ADDRESS2),''),0,40) PERMENT_ADD2, "+
							 " '' PERMENT_ADD3, "+	
							 //" NVL(CITY_CODE,'') PERMENT_CITY, "+
								" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							 " '' PERM_POSTCODE, "+	
							 " '' CITY_TEL_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(CITY_CODE) DISTRICT_CODE, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(CITY_CODE) PROVINCE_CODE, "+
							 " NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(CITY_CODE,A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							 " '' CITY_POSTCODE, "+	
							 " NVL(TEL_NO,'') TEL_NO, "+
							 " NVL(MOBILE_NO,'') MOBILE_NO, "+
							 " NVL(FAX_NO,'') FAX_NO, "+
							 //" NVL(UPPER(EMAIL),'') EMAIL, "+
								" '' EMAIL, "+	
							 " '' URL "+	
							 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
							 " WHERE CLIENT_CODE IN "+
							 " ( "+	
							 " SELECT GUARANTOR_CODE "+
							 " FROM   "+m_schema_name+".FA_CO_PRO_FACILITY_GUARANTOR "+
							 " WHERE  FACILITY_NO ='"+m_facility_no+"'	 "+
               " AND    ACTIVE_STATUS ='Y' "+ 								
							 " )"+
							 " )"+
							 " ");
				
			
							m_gs_details = "";
							
							boolean more7 = rs_gs.next();
							while(more7){
							m_gs_details = m_gs_details + rs_gs.getString(1);
							more7 = rs_gs.next();
							if(more7){
							m_gs_details = m_gs_details + "\n";
							}			
							}			
			         
					//COMMENT TEMP
		            /*out.println(m_cf_details); 
			        out.println(m_cs_details); 
			        if(!m_gs_details.equals("")){
			        out.println(m_gs_details); 
			        }
				    */
			
			   }
			
			   more_fac = rs.next();
			
			  }
			
			// end Factoring Details 
			m_data_count_fac=0;
			m_data_count_tot=m_data_count+m_data_count_fac;
			
			out.println("TLTL|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_data_count_tot+""); 
			
			} 
			
			
			
			else if(m_chksql.equals("text_file_factoring")){
			
			String m_data_prov_id=req.getParameter("data_prov_id");
			String m_data_point_id=req.getParameter("data_point_id"); 
			String m_data_prepar_date=req.getParameter("data_prepar_date");
			String m_data_report_date=req.getParameter("data_report_date"); 
			String m_data_report_time=req.getParameter("data_report_time"); 
			String m_data_cf_type=req.getParameter("data_cf_type");
			
			
			String m_prepar_date="";
			String m_report_date="";
			String m_data_count =""; 
			String m_finance_no ="";
			String m_application_no=""; 
			String m_cf_details ="";
			String m_cs_details ="";
			String m_ss_details ="";
			String m_gs_details ="";
			String m_rs_details ="";
			
						
			
			rs = stmt.executeQuery (" SELECT TO_CHAR(TO_DATE('"+m_data_prepar_date+"','DD-MM-YYYY'),'DD-MON-YYYY'), "+
			                        " TO_CHAR(TO_DATE('"+m_data_report_date+"','DD-MM-YYYY'),'DD-MON-YYYY') "+
															" FROM DUAL ");
			
			boolean more = rs.next();
						
			if(more){
			m_prepar_date = rs.getString(1); 
			m_report_date = rs.getString(2);
			}
			
			
			rs = stmt.executeQuery (" SELECT COUNT(A.FACILITY_NO) "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CO_MAS_CLIENT  B "+
				" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND   B.CLIENT_TYPE       = '"+m_data_cf_type+"' "+
				" AND   A.FACILITY_STATUS   IN ('Y','T') "+
				" AND   FACILITY_START_DATE < TO_DATE('"+m_data_report_date+"','DD-MM-YYYY') ");
			
			boolean more1 = rs.next();
						
			if(more1){
			m_data_count = rs.getString(1); 
			}
			
			
			if(m_data_cf_type.equals("I")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|001"); 
      }
			else if(m_data_cf_type.equals("C")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|002"); 
      } 
			
					
			 //Added by ns on 22-03-2011
			//Factoring Details ------------------------------------------------------
			
				rs = stmt.executeQuery (" SELECT A.FACILITY_NO, "+
				" A.CLIENT_CODE "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CO_MAS_CLIENT  B "+
				" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND   B.CLIENT_TYPE       = '"+m_data_cf_type+"' "+
				" AND   A.FACILITY_STATUS   IN ('Y','T') "+
				" AND   FACILITY_START_DATE < TO_DATE('"+m_data_report_date+"','DD-MM-YYYY') ");
				
				boolean more_fac = rs.next();
			
			
			
			while(more_fac){
			m_finance_no = rs.getString(1);
			//m_application_no = rs.getString(3);
			
			if(m_data_cf_type.equals("I")){
			
	
			rs_cf = stmt1.executeQuery (" SELECT 'CNCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_NUMBER||'|'||PRI_CARD_NUM||'|'||PRI_CARD_LIM||'|'||PRE_CF_NUM||'|'|| "+
			         " PRE_CARD_NUM||'|'||CF_APPR_DATE||'|'||AMNT_GRANT||'|'||HI_CR_AMNT||'|'||CURRENCY||'|'||OWNERSIP||'|'||TRANSACTION_TYPE||'|'|| "+
							 " PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'|| "+
							 " INT_OUT_BAL||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'||LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'|| "+
							 " CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'||SEC_TYPE||'|'||SEC_COVER||'|'||GURAN_AMT||'|'||TRN_TYPE_CODE "+
							 " ||'|'||DISPUT_ID "+
							 " FROM(SELECT X.FACILITY_NO  CF_NUMBER , "+
							 " DECODE(SUBSTR(X.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
							 " '' PRI_CARD_NUM, "+
							 " '' PRI_CARD_LIM, "+
							 " '' PRE_CF_NUM, "+
							 " '' PRE_CARD_NUM, "+
							" '' CF_APPR_DATE, "+
							 " NVL(CREDIT_LIMIT,0)  AMNT_GRANT, "+ 
							 " '' HI_CR_AMNT, "+
							 " "+m_schema_name+".AF_CO_GET_CRIB_CURRENCY('LKR')  CURRENCY, "+ 
							 " '001' OWNERSIP, "+		 
							 " "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE('FA') TRANSACTION_TYPE, "+ 
							 " '02:07:001'   PURPOSE_OF_CF, "+ //NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001')
							 " 0  NUM_OF_INSTALMENT, "+
							 " 0 INSTALMENT_AMT, "+ 
							 " '004' RE_PAYMNT_TYPE, "+ 
							 " TO_CHAR(APP_DATE,'DD-MON-YYYY') FST_DISB_DATE, "+
							 " DECODE(X.FACILITY_STATUS,'T',0,"+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT(X.CLIENT_CODE,X.FACILITY_NO,'"+m_data_report_date+"','ACT'))  CURRENT_BALANCE, "+ 
							 " 0 INT_OUT_BAL, "+ 
							 " 0 AMT_IN_AREAS, "+
							 " 0 NUM_OF_DATES, "+ 
							 " "+m_schema_name+".FA_CO_GET_CRIB_CF_STATUS(X.FACILITY_NO) CF_STATUS, "+ 
							 " "+m_schema_name+".FA_CO_GET_LAST_PAY_DATE(X.FACILITY_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+ 
							 " '' CF_RESTR_DATE, "+
							 " '' AMT_WR_OFF, "+
							 " "+m_schema_name+".FA_CO_GET_CF_CLOSE_DATE(X.FACILITY_NO) CF_CLOSE_DATE, "+ 
							 " '' LEAG_ACT_STAT, "+
							 " '' DATE_OF_FILED, "+
							 " '' SEC_TYPE, "+
							 " '003' SEC_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)
							 " '002' GURAN_AMT, "+ //OTHER GUARANTEE 001 //NOT GUARANTEE 002
							 " '001' TRN_TYPE_CODE, "+
							 " '' DISPUT_ID "+
							 " FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY X, "+
							 " "+m_schema_name+".FA_CO_MAS_CLIENT Y "+
							 " WHERE X.CLIENT_CODE = Y.CLIENT_CODE "+
							 " AND   X.FACILITY_NO = '"+m_finance_no+"' ) ");
								
					
														
			
			
			boolean more3 = rs_cf.next();
			if(more3){
			m_cf_details = rs_cf.getString(1);
			}
			
								
								
			rs_cs = stmt1.executeQuery (
			//out.println(
			" SELECT  'CNCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'|| "+
			        " SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||NIC_NO||'|'||PRE_NIC||'|'||CITIZEN||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'|| "+
							" SALUTION||'|'||SUBJECT_NAME||'|'||PRE_SUBJECT_NAME||'|'||EMPLOYMENT||'|'||PROFESSION||'|'||EMP_NAME||'|'||BUSSINES_NAME||'|'|| "+
							" BUSINESS_CERTI_NO||'|'||REG_DATE||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'|| "+
							" MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3 "+
							" ||'|'||PERMENT_CITY||'|'||PERMNT_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE||'|'||TEL_NO||'|'||MOBILE_NO||'|'|| "+
							" EMAIL||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'||MARITAL_STATUS||'|'||SUPOSE_NAME "+
						    " FROM (SELECT B.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(B.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
							" B.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(A.NIC_NO,'') NIC_NO, "+
							" '' PRE_NIC , "+
							//" DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002') CITIZEN, "+ //modified by nuwan de silva on 06-11-08
							"  '001' CITIZEN,"+
							//" NVL(A.PASSPORT_NO,'') PASSPORT_NO, "+
							" REPLACE(NVL(A.PASSPORT_NO,''),'-','') PASSPORT_NO, "+
							" '' DRIVING_LICENSE_NO, "+
							" DECODE(NVL(TITLE,'-'),'MR','001','MRS','002','MISS','003','REV','004','999') SALUTION, "+
							" NVL(UPPER(FULL_NAME),'') SUBJECT_NAME, "+
							" '' PRE_SUBJECT_NAME, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_CLIENT_EMPLYMNT(A.CLIENT_CODE),'') EMPLOYMENT, "+
							" '' PROFESSION, "+
							" '' EMP_NAME, "+
							" '' BUSSINES_NAME, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUSINESS_CERTI_NO, "+
							" TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') REG_DATE, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,REGISTERED_ADDRESS1)) MAIL_ADD1, "+
						  " SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,REGISTERED_ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(CITY_CODE,CITY_CODE))),'') MAIL_CITY, "+
							" '' POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE, "+ //999
							" NVL(UPPER(REGISTERED_ADDRESS1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(REGISTERED_ADDRESS2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'') PERMENT_CITY , "+
							" '' PERMNT_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CITY_CODE)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CITY_CODE),A.CLIENT_CODE),' ') COUNTRY_CODE, "+ //999
							" '' TEL_CODE, "+
							" NVL(REGISTERED_TEL_NO,'') TEL_NO, "+
							" NVL(REGISTERED_MOBILE_NO,'') MOBILE_NO, "+
							" '' EMAIL, "+
						    " '' DATE_OF_BIRTH, "+
							" DECODE(NVL(GENDER,'M'),'M','001','F','002') GENDER, "+
							" '' MARITAL_STATUS, "+
							" '' SUPOSE_NAME "+
							" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY B ,"+m_schema_name+".FA_CO_MAS_CLIENT A  "+
							" WHERE B.CLIENT_CODE = A.CLIENT_CODE "+
							" AND B.FACILITY_NO ='"+m_finance_no+"' "+
							" ) "+
							"");
							
							
							
			
			m_cs_details="";
			
			boolean more4 = rs_cs.next();
			while(more4){
			m_cs_details = m_cs_details + rs_cs.getString(1);
			more4 = rs_cs.next();
			
			if(more4){
			m_cs_details = m_cs_details + "\n";
			}
			}		
			
			
		/**	rs_gs = stmt1.executeQuery (
			//out.println(
			" SELECT 'CNGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||GURANT_ID||'|'||PRE_GURANT_ID||'|'||GUARAN_TYPE||'|'||GUARAN_NIC||'|'||PRE_NIC||'|'||CITIZEN "+
			        " ||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'||SALUATION||'|'||NAME||'|'||PRE_NAME||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'|| "+
							" MARITAL_STATUS||'|'||BUSI_REG||'|'||PRE_BUSI_REG||'|'||VAT_REG_NO||'|'||NAME_OF_BUSINESS||'|'||PRE_BUSSI_NAME||'|'||DATE_OF_INCORPORATION||'|'||MAIL_ADD1||'|'|| "+
							" MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE "+
							" ||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY||'|'||PERMNT_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE "+
							" ||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
							
							" FROM ( "+
							
							" SELECT A.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(A.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID,   "+
							" B.GUARANTOR_NO GURANT_ID, "+
							" '' PRE_GURANT_ID, "+
							" DECODE(B.GUARANTOR_TYPE,'I','002','C','001') GUARAN_TYPE, "+
							" NVL(B.NIC_NO,'') GUARAN_NIC, "+
							" '' PRE_NIC, "+
							" ''  CITIZEN, "+//DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002')
							" ''PASSPORT_NO, "+
							" '' DRIVING_LICENSE_NO, "+
							" DECODE(NVL(B.TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUATION, "+
							" NVL(UPPER(LAST_NAME),'') NAME, "+
							" '' PRE_NAME, "+
							" '' DATE_OF_BIRTH, "+
							" ''  GENDER,  "+ //DECODE(NVL(GENDER,'M'),'M','001','F','002')
							" ''   MARITAL_STATUS,  "+
							" NVL(B.BUSINESS_REG_NO,'') BUSI_REG, "+
							" '' PRE_BUSI_REG, "+
							" '' VAT_REG_NO,  "+
							" '' NAME_OF_BUSINESS, "+
							" '' PRE_BUSSI_NAME, "+
							" '' DATE_OF_INCORPORATION, "+ //TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') 
							" UPPER(NVL(ADDRESS_LINE_1,ADDRESS_LINE_1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(ADDRESS_LINE_2,ADDRESS_LINE_2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL(AF_CO_GET_CITY_NAME(NVL(CITY,CITY)),'') MAIL_CITY, "+
							" '' POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY,CITY)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY,CITY)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY,CITY),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE,  "+
							" NVL(UPPER(ADDRESS_LINE_1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS_LINE_2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(CITY,CITY)),'') PERMENT_CITY , "+
							" '' PERMNT_POSTCODE,  "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY,CITY)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY,CITY)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY,CITY),A.CLIENT_CODE),' ') COUNTRY_CODE,  "+
							" '' TEL_CODE,  "+
							" NVL(TELEPHONE_1,'') TEL_NO,  "+
							" NVL(MOBILE,'') MOBILE_NO,  "+
							" NVL(FAX,'') FAX_NO,  "+
							" '' EMAIL,  "+
							" '' URL  "+
							" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CR_BANK_PERSONAL_GUARANTEE B "+
							" WHERE A.FACILITY_NO=B.FACILITY_NO "+
							" AND   A.facility_no='"+m_finance_no+"' "+
							
							")");
      
			m_gs_details = "";

      boolean more6 = rs_gs.next();
			while(more6){
			m_gs_details = m_gs_details + rs_gs.getString(1);
			more6 = rs_gs.next();
			if(more6){
			m_gs_details = m_gs_details + "\n";
			}
			}			*/
          
			out.println(m_cf_details); 
			out.println(m_cs_details);
			if(!m_ss_details.equals("")){
			out.println(m_ss_details);
			}
			if(!m_gs_details.equals("")){
			out.println(m_gs_details); 
			}

		   //--------- CORPORATE DETAILS ----------//
			
			
			}else if(m_data_cf_type.equals("C")){
			
				rs_cf = stmt1.executeQuery (" SELECT 'CMCF'||'|'||'"+m_data_prov_id+"'||'|'||'|'||DPOINT_ID||'|'||'|'||CF_ACC_NUM||'|'||PRE_CF_ACC_NUM "+
		        " ||'|'||PACKGE_LOAN_ID||'|'||PACKGE_LOAN_AMT||'|'||APPROV_DATE||'|'||AMT_GRANTED||'|'||HI_CR_AMOUNT||'|'||CURRENCY||'|'|| "+
						" OWNERSIP||'|'||TRANSACTION_TYPE||'|'||PURPOSE_OF_CF||'|'||NUM_OF_INSTALMENT||'|'||INSTALMENT_AMT||'|'||RE_PAYMNT_TYPE "+
						" ||'|'||FST_DISB_DATE||'|'||CURRENT_BALANCE||'|'||INT_OUTSTND||'|'||NUM_OF_DATES||'|'||AMT_IN_AREAS||'|'||CF_STATUS||'|'|| "+
						" LAST_PAY_DATE||'|'||CF_RESTR_DATE||'|'||AMT_WR_OFF||'|'||CF_CLOSE_DATE||'|'||LEAG_ACT_STAT||'|'||DATE_OF_FILED||'|'|| "+
						" SEC_TYPE||'|'||SECURTY_COVER||'|'||GURANTEE_COVER||'|'||TRN_TYPE_CODE||'|'||DISPUTE_ID "+
						" FROM( "+
						
							 " SELECT X.FACILITY_NO  CF_ACC_NUM , "+
							 " DECODE(SUBSTR(X.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID, "+  
							 " '' PRE_CF_ACC_NUM, "+
							 " '' PACKGE_LOAN_ID, "+
							 " '' PACKGE_LOAN_AMT, "+
						 	 " '' APPROV_DATE, "+
							 " NVL(CREDIT_LIMIT,0)  AMT_GRANTED, "+ 
							 " '' HI_CR_AMOUNT, "+
							 " 'LKR' CURRENCY, "+ 
							 " '001' OWNERSIP, "+		
							 " "+m_schema_name+".AF_CO_GET_CRIB_TRANS_TYPE('FA') TRANSACTION_TYPE, "+ 
							 " '02:07:001'   PURPOSE_OF_CF, "+ //NVL("+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(Y.BUSINESS_SUB_SECTOR),'02:07:001')
							 " 0  NUM_OF_INSTALMENT, "+//"+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)
							 " 0 INSTALMENT_AMT, "+ 
							 " '004' RE_PAYMNT_TYPE, "+ 
							 " TO_CHAR(APP_DATE,'DD-MON-YYYY') FST_DISB_DATE, "+ 
							 " DECODE(X.FACILITY_STATUS,'T',0,"+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT(X.CLIENT_CODE,X.FACILITY_NO,'"+m_data_report_date+"','ACT'))  CURRENT_BALANCE, "+ 
							 " 0 INT_OUTSTND, "+ 
							 " 0 AMT_IN_AREAS, "+
							 " 0 NUM_OF_DATES, "+
							 " "+m_schema_name+".FA_CO_GET_CRIB_CF_STATUS(X.FACILITY_NO) CF_STATUS, "+ 
							 " "+m_schema_name+".FA_CO_GET_LAST_PAY_DATE(X.FACILITY_NO,'"+m_data_report_date+"') LAST_PAY_DATE, "+ 
							 " '' CF_RESTR_DATE, "+
							 " '' AMT_WR_OFF, "+
							 " "+m_schema_name+".FA_CO_GET_CF_CLOSE_DATE(X.FACILITY_NO) CF_CLOSE_DATE, "+ 
							 " '' LEAG_ACT_STAT, "+
							 " '' DATE_OF_FILED, "+
							 " '' SEC_TYPE, "+
							 " '003'  SECURTY_COVER, "+ //"+m_schema_name+".AF_CO_GET_CRIB_SEC_COVER(X.APPLICATION_NO)
							 " "+m_schema_name+".FA_GET_CRIB_GURA_STATUS(X.FACILITY_NO) GURANTEE_COVER, "+ //OTHER GUARANTEE 001 NOT GUARANTEE 002
							 " '001' TRN_TYPE_CODE, "+
							 " '' DISPUTE_ID "+
							 " FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY X, "+
							 " "+m_schema_name+".FA_CO_MAS_CLIENT Y "+
							 " WHERE X.CLIENT_CODE = Y.CLIENT_CODE "+
							 " AND   X.FACILITY_NO = '"+m_finance_no+"' ) ");						
							
							
			
			boolean more3 = rs_cf.next();
			if(more3){
			m_cf_details = rs_cf.getString(1);
			}	
			
	
							
							rs_cs = stmt1.executeQuery (" SELECT 'CMCS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||SUBJECT_ID||'|'||PRE_SUBJECT_ID||'|'||BUS_REG_NUM||'|'||REG_DATE||'|'||PREV_REG_NUM||'|'||VAT_REG_NO "+
			        " ||'|'||LEAGLE_CONST||'|'||ECON_ACT_CODE1||'|'||ECON_ACT_CODE2||'|'||ECON_ACT_CODE3||'|'||SUBJECT_NAME||'|'||PREV_SUBJ_NAME||'|'|| "+
							" SUBJECT_SHORT_NAME||'|'||PRE_SUBJECT_SHORT_NAME||'|'||MAIL_ADD1||'|'||MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||MAIL_POST_CODE "+
							" ||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE||'|'||ADDRESS1||'|'||ADDRESS2||'|'||ADDRESS3||'|'||CITY_CODE "+
							" ||'|'||PER_POST_CODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TELEPHN_CITY||'|'||TEL_NO||'|'|| "+
							" FAX_NO||'|'||URL "+
							
							" FROM ("+
							" SELECT Z.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(Z.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID,   "+
							" X.CLIENT_CODE SUBJECT_ID, "+
							" '' PRE_SUBJECT_ID, "+
							" NVL(BUSINESS_CERTIFICATE_NO,'') BUS_REG_NUM, "+
							" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY'),'') REG_DATE, "+
							" '' PREV_REG_NUM, "+
							" NVL(VAT_REG_NO,'') VAT_REG_NO, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_LEAGLE_CONST(X.CLIENT_CODE) LEAGLE_CONST, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_BUSI_SUB_CODE(X.BUSINESS_SUB_SECTOR) ECON_ACT_CODE1, "+
							" '' ECON_ACT_CODE2, "+
							" '' ECON_ACT_CODE3,  "+
							" UPPER(NVL(FULL_NAME,'')) SUBJECT_NAME , "+
							" '' PREV_SUBJ_NAME, "+
							" '' SUBJECT_SHORT_NAME, "+
							" '' PRE_SUBJECT_SHORT_NAME, "+
							" UPPER(NVL(REGISTERED_ADDRESS1,CORRES_ADDRESS1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(REGISTERED_ADDRESS2,CORRES_ADDRESS2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(CITY_CODE,CORRES_CITY_CODE))),'') MAIL_CITY, "+
							" '' MAIL_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CORRES_CITY_CODE),X.CLIENT_CODE),' ') MAIL_COUNTRY_CODE,  "+
							" UPPER(NVL(CORRES_ADDRESS1,REGISTERED_ADDRESS1)) ADDRESS1, "+
							" SUBSTR(UPPER(NVL(CORRES_ADDRESS2,REGISTERED_ADDRESS2)),0,40) ADDRESS2, "+
							" '' ADDRESS3, "+
							//" UPPER(NVL(CORRES_CITY_CODE,CITY_CODE)) CITY_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(UPPER(NVL(CORRES_CITY_CODE,CITY_CODE))),'') CITY_CODE, "+
							" '' PER_POST_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY_CODE,CORRES_CITY_CODE)) PROVINCE_CODE,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY_CODE,CORRES_CITY_CODE),X.CLIENT_CODE),' ') COUNTRY_CODE,  "+
							" '' TELEPHN_CITY,  "+
							" NVL(REGISTERED_TEL_NO,'') TEL_NO,  "+
							" NVL(REGISTERED_FAX_NO,'') FAX_NO,  "+
							" '' URL  "+
							" FROM "+m_schema_name+".FA_CO_MAS_CLIENT X,  "+
							" "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY Z  "+
							" WHERE X.CLIENT_CODE = Z.CLIENT_CODE "+
							" AND  Z.FACILITY_NO ='"+m_finance_no+"' "+
							" ) "+
							"");
							
							
    
			
			m_cs_details ="";
			
			boolean more4= rs_cs.next();
			while(more4){
			m_cs_details = m_cs_details + rs_cs.getString(1);
			more4 = rs_cs.next();
			
			if(more4){
			m_cs_details = m_cs_details + "\n";
			}		
			}		
			
         /**   
			
													rs_gs = stmt1.executeQuery (
			//out.println(
			" SELECT 'CMGS'||'|'||'"+m_data_prov_id+"'||'|'||DPOINT_ID||'|'||CF_ACC_NUM||'|'||GURANT_ID||'|'||PRE_GURANT_ID||'|'||GUARAN_TYPE||'|'||GUARAN_NIC||'|'||PRE_NIC||'|'||CITIZEN "+
			        " ||'|'||PASSPORT_NO||'|'||DRIVING_LICENSE_NO||'|'||SALUATION||'|'||NAME||'|'||PRE_NAME||'|'||DATE_OF_BIRTH||'|'||GENDER||'|'|| "+
							" MARITAL_STATUS||'|'||BUSI_REG||'|'||PRE_BUSI_REG||'|'||VAT_REG_NO||'|'||NAME_OF_BUSINESS||'|'||PRE_BUSSI_NAME||'|'||DATE_OF_INCORPORATION||'|'||MAIL_ADD1||'|'|| "+
							" MAIL_ADD2||'|'||MAIL_ADD3||'|'||MAIL_CITY||'|'||POSTALCODE||'|'||MAIL_DISTRICT_CODE||'|'||MAIL_PROVINCE_CODE||'|'||MAIL_COUNTRY_CODE "+
							" ||'|'||PERMENT_ADD1||'|'||PERMENT_ADD2||'|'||PERMENT_ADD3||'|'||PERMENT_CITY||'|'||PERMNT_POSTCODE||'|'||DISTRICT_CODE||'|'||PROVINCE_CODE||'|'||COUNTRY_CODE||'|'||TEL_CODE "+
							" ||'|'||TEL_NO||'|'||MOBILE_NO||'|'||FAX_NO||'|'||EMAIL||'|'||URL "+
							
							" FROM ( "+
							
							" SELECT A.FACILITY_NO CF_ACC_NUM, "+
							" DECODE(SUBSTR(A.FACILITY_NO,0,2),'BL','BD','HO') DPOINT_ID,   "+
							" B.GUARANTOR_NO GURANT_ID, "+
							" '' PRE_GURANT_ID, "+
							" DECODE(B.GUARANTOR_TYPE,'I','002','C','001') GUARAN_TYPE, "+
							" NVL(B.NIC_NO,'') GUARAN_NIC, "+
							" '' PRE_NIC, "+
							" ''  CITIZEN, "+//DECODE(NVL(A.NATIONALITY,'-'),'SRILANKA','001','SRILANKAN','001','-','001','002')
							" ''PASSPORT_NO, "+
							" '' DRIVING_LICENSE_NO, "+
							" DECODE(NVL(B.TITLE,''),'MR','001','MRS','002','MISS','003','REV','004','999') SALUATION, "+
							" NVL(UPPER(LAST_NAME),'') NAME, "+
							" '' PRE_NAME, "+
							" '' DATE_OF_BIRTH, "+
							" ''  GENDER,  "+ //DECODE(NVL(GENDER,'M'),'M','001','F','002')
							" ''   MARITAL_STATUS,  "+
							" NVL(B.BUSINESS_REG_NO,'') BUSI_REG, "+
							" '' PRE_BUSI_REG, "+
							" '' VAT_REG_NO,  "+
							" '' NAME_OF_BUSINESS, "+
							" '' PRE_BUSSI_NAME, "+
							" '' DATE_OF_INCORPORATION, "+ //TO_CHAR(DATE_OF_INCORPORATION,'DD-MON-YYYY') 
							" UPPER(NVL(ADDRESS_LINE_1,ADDRESS_LINE_1)) MAIL_ADD1, "+
							" SUBSTR(UPPER(NVL(ADDRESS_LINE_2,ADDRESS_LINE_2)),0,40) MAIL_ADD2, "+
							" '' MAIL_ADD3, "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(CITY,CITY)),'') MAIL_CITY, "+
							" '' POSTALCODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY,CITY)) MAIL_DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY,CITY)) MAIL_PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY,CITY),A.CLIENT_CODE),' ') MAIL_COUNTRY_CODE,  "+
							" NVL(UPPER(ADDRESS_LINE_1),'') PERMENT_ADD1, "+
							" SUBSTR(NVL(UPPER(ADDRESS_LINE_2),''),0,40) PERMENT_ADD2, "+
							" '' PERMENT_ADD3,  "+
							" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(NVL(CITY,CITY)),'') PERMENT_CITY , "+
							" '' PERMNT_POSTCODE,  "+
							" "+m_schema_name+".AF_CO_GET_CRIB_DISTRICT_CODE(NVL(CITY,CITY)) DISTRICT_CODE, "+
							" "+m_schema_name+".AF_CO_GET_CRIB_PROVINCE_CODE(NVL(CITY,CITY)) PROVINCE_CODE, "+
							" NVL("+m_schema_name+".AF_CO_GET_CRIB_COUNTRY_CODE(NVL(CITY,CITY),A.CLIENT_CODE),' ') COUNTRY_CODE,  "+
							" '' TEL_CODE,  "+
							" NVL(TELEPHONE_1,'') TEL_NO,  "+
							" NVL(MOBILE,'') MOBILE_NO,  "+
							" NVL(FAX,'') FAX_NO,  "+
							" '' EMAIL,  "+
							" '' URL  "+
							" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+m_schema_name+".FA_CR_BANK_PERSONAL_GUARANTEE B "+
							" WHERE A.FACILITY_NO=B.FACILITY_NO "+
							" AND   A.facility_no='"+m_finance_no+"' "+
							
							")");

			
			m_gs_details = "";

      boolean more7 = rs_gs.next();
			while(more7){
				m_gs_details = m_gs_details + rs_gs.getString(1);
				more7 = rs_gs.next();
				
			if(more7){
			m_gs_details = m_gs_details + "\n";
			}			
			}			
			***/
			

		
			out.println(m_cf_details); 
			out.println(m_cs_details); 
			if(!m_rs_details.equals("")){
			out.println(m_rs_details); 
			}
			if(!m_ss_details.equals("")){
			out.println(m_ss_details); 
			}
			if(!m_gs_details.equals("")){
			out.println(m_gs_details); 
			}			
			}

			
			more_fac = rs.next();
			}
			
			//End Factoring Details --------------------------------------------------
					
						
			out.println("TLTL|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_data_count+""); 
			
						
			
			
			}
			
			else{
			
			out.println("undifind");
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
