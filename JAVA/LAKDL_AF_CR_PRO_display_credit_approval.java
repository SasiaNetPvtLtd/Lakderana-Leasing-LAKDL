//--
//SCREEN NAME	:CREDIT VERIFICATION APPROVAL
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES:
	
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;



public class LAKDL_AF_CR_PRO_display_credit_approval extends javax.servlet.http.HttpServlet 
{ 

	
			Connection conn;
			Statement stmt4,stmt,stmt1,stmt2,stmt3,stmt5,stmt6,stmt7;
			java.text.NumberFormat nf,nf1;
			public ResultSet rs4,rs,rs1,rs2,rs3,rs5,rs6,rs7;
			public String m_chksql;
			ServletOutputStream out = null;
			String reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException 
	{ 
		 
		try 
		{ 
		
		 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
		
				
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);

			

			CallableStatement callstmt1 =null;
			
			stmt4 = conn.createStatement ();
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();
			stmt5 = conn.createStatement ();
			stmt6 = conn.createStatement ();
			stmt7 = conn.createStatement ();
		
			String m_applicaton_no        = req.getParameter("applicaton_no");
			String m_app_code="";
			String m_add1;
			String m_pre_stage=req.getParameter("pre");
			String m_app_stage=req.getParameter("appro");
			String m_pre_stage1=req.getParameter("qry");
			String m_close=req.getParameter("CLS");
			String m_Hid_scr_name        = req.getParameter("Hid_scr_name");

			String m_schema_name = m_sn_methods.schema_name;
			
			//out.println("m_pre_stage"+m_pre_stage);
     // out.println("m_app_stage"+m_app_stage);
			

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Verification Approval</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			//Declare Global Variables
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
				
			//Declare Global Arrays
			out.println("var array_follow_up_no=new Array();");
			out.println("var array_condition=new Array();");
			out.println("var array_status=new Array();");
			

			///m_prime_chk_LAKDL_AF_MAS_get_odi_balace


			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_credit_verification_approval&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

				
		  out.println("function get_conditions(){ "); 
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			
		 	out.println("function header(){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR class=pdn_txtpos2>'+");
			out.println("'<TD WIDTH=\"14%\" align=\"left\"><div id=Follow class=div_input><B>Follow up No</div></B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><div id=Follow2 class=div_input><B>Condition</B></div></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><div id=Follow3 class=div_input><B>Status</B></div></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
				
			
			out.println("function display_data(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("header();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno+')\"><u>Follow up</u></TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CONDITION'+lineno+' style=\"width:275px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("i=i+3;");
			out.println("lineno=parseInt(lineno)+1;");
			out.println("arr_size=parseInt(arr_size)+1;");
			out.println("}");
			out.println("}");
			
			out.println("else if(data_vec.length==0){");
			out.println("add_row()");
			out.println("}");
			out.println("}");
			
			out.println("function display_eval(obj1,obj2){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_VIEW&pre_stage1="+m_pre_stage1+"&model=\"+obj2+\"&application=\"+obj1;");
			out.println("window.open(url,'win1','left=200,top=100,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			out.println("}"); 

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_GUARANTOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_GUARANTOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PERIOD.value==\"\"){  "); 
			out.println("DIV_TXT_PERIOD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FULL_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FULL_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS1.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS2.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS2.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NIC_NO.value==\"\"){  "); 
			out.println("DIV_TXT_NIC_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PRICING_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PRICING_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PRO_INVOICE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PRO_INVOICE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYMENT_MODE.value==\"\"){  "); 
			out.println("DIV_TXT_PAYMENT_MODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYMENT_INTERVAL.value==\"\"){  "); 
			out.println("DIV_TXT_PAYMENT_INTERVAL.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_RATE.value==\"\"){  "); 
			out.println("DIV_TXT_RATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ASSET_ID.value==\"\"){  "); 
			out.println("DIV_TXT_ASSET_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ENGINE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ENGINE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CHASSIS_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CHASSIS_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_REG_NO.value==\"\"){  "); 
			out.println("DIV_TXT_REG_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
			out.println("DIV_TXT_COLOUR.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VENDOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 


			out.println("function check_app(){ "); 
			out.println("if(!count_chk()){"); 
			out.println("alert(\"Please approve or reject the application \");");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
		  out.println("}"); 
			
			
			
			
			
			
			
			//=========added by nuwan de silva 22-05-07=============
			//===========validate the number=======================
			
			out.println("	function chk_comment_length(obj){ ");

			out.println(" var remarks_length=obj.value.toString().length;");
			
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			
			out.println("} ");
			
			
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
      out.println("} ");
			
			
			
			//================================================
			
			
			
			
			
			
			out.println("function count_chk(){ ");
			out.println("count=0;");
			out.println(" m_row = document.Form1.NUM_CHKS.value ; ");
			out.println("for(i=0;i<m_row;i++){");
			out.println("  chk_rej=\"CHK_REJ_\"+i;");
			out.println("  chk=\"CHK_\"+i;");
			out.println("if(document.Form1.elements[chk].checked==true || document.Form1.elements[chk_rej].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0){");
			out.println("return true;");
			out.println("}"); 
			out.println("else{");
			out.println("return false;");
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("   document.Form1.Hid_scr_name.value='"+m_Hid_scr_name+"';");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_save_score_approval?pre_stage1="+m_pre_stage1+"&app_no="+m_applicaton_no+"&appro="+m_app_stage+"';");
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
	
			out.println("function load_lock(){	"); 
			out.println("if("+m_close+"==1){");
			out.println("window.close()");
			out.println("}	"); 
			out.println("if(document.Form1.NUM_CHKS.value==\"0\"){");
			out.println("alert('Application is already approved')");
			out.println("window.close()");
			out.println("}	"); 
			out.println("}	"); 
					
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_verification_approval?chksql=main_page&pre="+m_pre_stage+"&applicaton_no="+m_applicaton_no+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_verification_approval?chksql=main_page&pre="+m_pre_stage+"&applicaton_no="+m_applicaton_no+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_credit_verification_approval\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			
			if(m_pre_stage.equals("V-APP")){
			out.println("help_box.innerHTML=\" Credit - Credit Approval 1 - \"+m_val;"); 
			}
			else if(m_pre_stage.equals("VERIFY-M")){
			
			out.println("help_box.innerHTML=\" Credit - Credit Approval 2 - \"+m_val;"); 
			
			}
			
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			
			
			
			if(m_pre_stage.equals("V-APP")){
			out.println("help_box.innerHTML=\" Credit - Credit Approval 1 - \"+document.Form1.hid_status.value;"); 
			}
			else if(m_pre_stage.equals("VERIFY-M")){
			
			out.println("help_box.innerHTML=\" Credit - Credit Approval 2 - \"+document.Form1.hid_status.value;"); 
			
			}
			
			
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_GUARANTOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PRICING_NO.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_PRO_INVOICE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PAYMENT_MODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYMENT_INTERVAL.disabled=true;"); 
			out.println("document.Form1.TXT_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS.disabled=true;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Servlet?class_in=\"+client_name+\"AF_PRO_CR_help_select\"+"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
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
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_GUARANTOR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_GUARANTOR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_GUARANTOR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_PRICING_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PRICING_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_PRO_INVOICE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PRO_INVOICE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_PRO_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_GUARANTOR_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_PERIOD.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_PRO_INVOICE_NO.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_PAYMENT_MODE.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_PAYMENT_INTERVAL.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_RATE.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_ADDRESS.value=oBj.valout[26];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[27];"); 
			out.println("}"); 

			out.println("function change(row) {"); 
			out.println("  chk_rej=\"CHK_REJ_\"+row;");
			out.println("  chk_quot=\"CHK_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("document.Form1.elements[chk_rej].checked=false");
			out.println("document.Form1.elements[chk_rej].value='N'");
			out.println("}");
			out.println("else if(document.Form1.elements[chk_quot].checked==false && document.Form1.elements[chk_rej].checked==false){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("}");	
			out.println("}"); 


			out.println("function change_reject(row) {"); 
			out.println("  chk_rej=\"CHK_REJ_\"+row;");
			out.println("  chk_quot=\"CHK_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("document.Form1.elements[chk_quot].checked=false");
			out.println("document.Form1.elements[chk_quot].value='N'");
			out.println("}");	
			out.println("else if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("}");	
			out.println("}"); 
				
			out.println("function close_1(){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("window.close()");
			out.println("}");
			out.println("}");
		
			
			
			/*----------------------------------------------------------------
					Purpose  : Add Conditions
				
				----------------------------------------------------------------*/			

			 out.println("function add_row(){"); 
			 out.println("b_flag=0;");
			 out.println("if(lineno!=0){");
			 out.println("count=lineno-1;");
			 out.println("m_condition=\"TXT_CONDITION\"+count");
			 out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			 out.println("alert('Condition can not be null.');");
			 out.println("b_flag=1;");
			 out.println("}");
			 out.println("}");
			 out.println("if(b_flag==0){");
			 out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			 out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CONDITION'+lineno+' style=\"width:275px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			 out.println("'</tr></table>';");
			 out.println("lineno=parseInt(lineno)+1;");
			 out.println("arr_size=parseInt(arr_size)+1;");
       out.println("}");
		   out.println("}");
				
			 out.println("function del_row(rowNo){"); 
			 out.println("if(arr_size!=1){");
			 out.println("var j=0;");
			 out.println("for(var i=0;i<arr_size;i++){");
			 out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			 out.println("m_condition=\"TXT_CONDITION\"+i");
			 out.println("m_status=\"hid_TXT_STATUS\"+i");					
			 out.println("if(i==rowNo)");
			 out.println("continue;");
			 out.println("array_follow_up_no[j]=document.Form1.elements[m_follow_up].value;");
			 out.println("array_condition[j]=document.Form1.elements[m_condition].value;");
		   out.println("array_status[j]=document.Form1.elements[m_status].value;");    
			 out.println("j=j+1;");
			 out.println("}");
			 out.println("lineno=lineno-1;");
			 out.println("arr_size=arr_size-1;");
		   out.println("write_data(arr_size);");
			 out.println("}");
			
			 out.println("function write_data(size){");
			 out.println("sum=0;");
			 out.println("m_table.innerHTML=\"\";");
			 out.println("header();");
       out.println(" for(var j=0;j<size;j++){");
		   out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
		   out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CONDITION'+j+' style=\"width:275px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			 out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			 out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			 out.println("'</tr></table>';");
				
       out.println("continue;");
			 out.println("}");
				
			 out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
							  
       out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CONDITION'+j+' style=\"width:275px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			 out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			 out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			 out.println("'</tr></table>';");
       out.println("continue;");
			 out.println("}");
			 out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
							  
       out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</a></TD>'+"); //Follow up
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CONDITION'+j+' style=\"width:275px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"15%\" align=\"left\">'+array_status[j]+'</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE='+array_status[j]+'>'+");
			 out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			 out.println("'</tr></table>';");
       out.println("continue;");
			 out.println("}");
			 out.println("}");		
			 out.println("}");		
			 out.println("}");		
	//_____________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),get_conditions()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_data_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_remarks' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">"); 
			
			//out.println("<INPUT TYPE='Hidden' NAME='Hid_app_no' VALUE=\"\">"); 

	
			
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
			
			if(m_pre_stage.equals("V-APP")){
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 1</td>"); 
			out.println("</tr>"); 
			}
			else if(m_pre_stage.equals("VERIFY-M")){
			
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 2</td>"); 
			out.println("</tr>"); 
			
			}
			
			
			
			
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

		//CLIENT AND Guarantor	DETAILS....

		/*	rs4 = stmt4.executeQuery("SELECT DISTINCT A.APPLICATION_NO, "+
																"A.CLIENT_CODE, "+
																"B.FULL_NAME, "+
																"NVl(B.ADDRESS1,'-'), "+
																"NVL(B.ADDRESS2,'-'), "+
																"NVL(B.NIC_NO,'-') "+
																"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
																"WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
																"AND A.APPLICATION_NO='"+m_applicaton_no+"' ");
		
			boolean more4=rs4.next();		
			*/
			
			
			
			
			//==============Added By Nuwan De Silva 23-05-07==========================================
			
			
			
			   rs4 = stmt4.executeQuery(" SELECT "+
                                  " CLIENT_TYPE "+
																  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
																  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
																  " AND A.APPLICATION_NO='"+m_applicaton_no+"' ");
																
                                
     
			boolean more123=rs4.next();		
				
			String m_type=rs4.getString(1);
			
			if(m_type.equals("I"))
			{
				rs4 = stmt4.executeQuery("SELECT DISTINCT A.APPLICATION_NO, "+
																"A.CLIENT_CODE, "+
																"B.FULL_NAME, "+
																"NVl(B.ADDRESS1,'-'), "+
																"NVL(B.ADDRESS2,'-'), "+
																"NVL(B.NIC_NO,'-') "+
																"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
																"WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
																"AND A.APPLICATION_NO='"+m_applicaton_no+"' ");
			}
			else if(m_type.equals("C"))
			{
															
				rs4 = stmt4.executeQuery("SELECT DISTINCT A.APPLICATION_NO, "+
																"A.CLIENT_CODE, "+
																"B.FULL_NAME, "+
																"NVl(B.REGISTERED_ADDRESS1,'-'), "+
																"NVL(B.REGISTERED_ADDRESS2,'-'), "+
																"NVL(B.BUSINESS_CERTIFICATE_NO,'-') "+
																"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
																"WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
																"AND A.APPLICATION_NO='"+m_applicaton_no+"' ");
		  
		 }	
				
			//===================================================================================================================================
			boolean more4=rs4.next();		
								

	/*	rs6 = stmt6.executeQuery("SELECT DISTINCT "+
					 "NVL(b.GUARANTOR_CODE,'-'), "+ 
					 "NVL(c.FULL_NAME,'-'),  "+
					 "NVL(c.ADDRESS1,'-') , "+
					 "NVL(c.ADDRESS2,'-'),  "+
           "nvl(decode(c.client_type,'C',c.vat_reg_no,'I',c.NIC_NO),'-'), "+
					 ""+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(b.GUARANTOR_CODE)  "+	//6	
					 "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B ,"+m_schema_name+".AF_CO_MAS_CLIENT C "+
					 "WHERE A.CLIENT_CODE=b.GUARANTOR_CODE  "+
		       " and a.client_code=c.client_code "+
					 "AND b.APPLICATION_NO='"+m_applicaton_no+"' ");
						
	*/
	
	//added by nuwan de silva 23-05-07=======================================================
		rs6 = stmt6.executeQuery("SELECT DISTINCT "+
					 "NVL(b.GUARANTOR_CODE,'-'), "+ 
					 "NVL(c.FULL_NAME,'-'),  "+
					 "NVL(c.ADDRESS1,'-') , "+
					 "NVL(c.ADDRESS2,'-'),  "+
           "nvl(decode(c.client_type,'C',c.vat_reg_no,'I',c.NIC_NO),'-'), "+
					 ""+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(b.GUARANTOR_CODE)  "+	//6	
					 "FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B ,"+m_schema_name+".AF_CO_MAS_CLIENT C "+ //"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,
					 "WHERE  C.CLIENT_CODE=b.GUARANTOR_CODE AND  "+
		    	 "       b.APPLICATION_NO='"+m_applicaton_no+"' ");

  //========================================================================================
						
			boolean more6=rs6.next();


//*********************************************************************************************************************************************************************************************
			while(more4)
				{
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input><b>Application No </DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs4.getString(1)+"')\"><U>"+rs4.getString(1)+"<input class='txt_input' type='hidden' name='TXT_APPLICATION_NO' maxlength='15' size='15' value=\""+rs4.getString(1)+"\" ></td>"); 
			out.println("<td width='*%'></td>");

			out.println("</tr>");
			
			out.println("<tr >");
			out.println("</tr >");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FULL_NAME'  class=div_input><b>Full Name </DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs4.getString(2)+"')\"><U>"+rs4.getString(3)+"<input class='txt_input' type='hidden' name='TXT_FULL_NAME' maxlength='250' value=\""+rs4.getString(3)+"\" size='250'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("</tr >");

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input><b>Address 1 </DIV></td>"); 
			out.println("<td width='40%' >"+rs4.getString(4)+"<input class='txt_input' type='hidden' name='TXT_ADDRESS1' value=\""+rs4.getString(4)+"\" maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("</tr >");

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS2'  class=div_input><b>Address 2 </DIV></td>"); 
			out.println("<td width='40%' >"+rs4.getString(5)+"<input class='txt_input' type='hidden' name='TXT_ADDRESS2' value=\""+rs4.getString(5)+"\" maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("</tr >");

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NIC_NO'  class=div_input><b>NIC/Reg.No</DIV></td>"); 
			out.println("<td width='40%' >"+rs4.getString(6)+"<input class='txt_input' type='hidden' name='TXT_NIC_NO' value=\""+rs4.getString(6)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			//Added by Mahela on 26-04-2007
			
					rs = stmt.executeQuery("SELECT "+
  				 " CLIENT_CODE, "+
					 " "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(CLIENT_CODE) "+	
 					 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 					 " WHERE APPLICATION_NO='"+m_applicaton_no+"' ");
				boolean	more = rs.next();
			
			if(more)
					{
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEASE_EXPOSURE'  class=div_input><b>Leasing Exposure</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(rs.getDouble(2))+"<input class='txt_input' type='hidden' name='TXT_LEASE_EXPOSURE' value=\""+rs.getDouble(2)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}
			else 
					{
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEASE_EXPOSURE'  class=div_input><b>Leasing Exposure</DIV></td>"); 
			out.println("<td width='40%' >0.00<input class='txt_input' type='hidden' name='TXT_LEASE_EXPOSURE' value=\"0\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}
			
			 
					rs = stmt.executeQuery(" SELECT "+
					  "  SUM(PAYMENT_AMOUNT) "+
					 " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
					 " WHERE CLIENT_CODE='"+rs4.getString(2)+"' "+
					 " AND PAY_STATUS='CONF' ");
			    
					more = rs.next();
		  
			if(more)
					{
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FACTORING_EXPOSURE'  class=div_input><b>Factoring Exposure</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(rs.getDouble(1))+"<input class='txt_input' type='hidden' name='TXT_FACTORING_EXPOSURE' value=\"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}
			else 
					{
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FACTORING_EXPOSURE'  class=div_input><b>Factoring Exposure</DIV></td>"); 
			out.println("<td width='40%' >0.00<input class='txt_input' type='hidden' name='TXT_FACTORING_EXPOSURE' value=\"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GROUP_EXPOSURE'  class=div_input><b>Group Exposure</DIV></td>"); 
			out.println("<td width='40%' >0.00<input class='txt_input' type='hidden' name='TXT_GROUP_EXPOSURE' value=\"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("</tr >");
			
			
			out.println("</table>");
			
			out.println("<br >"); 
			more4=rs4.next();
			
			if (!more4)
			{
			break;
			}
			}
			int k=0;
			out.println("<HR>"); 
//*********************************************************************************************************************************************************************************************

			while(more6)
			{

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			if(k==0)
				{
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_FULL_NAME'  class=div_input><b>Guarantor Full Name </DIV></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input><b>Registered Address 1 </DIV></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS2'  class=div_input><b>Registered Address 2 </DIV></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_NIC_NO'  class=div_input><b>Guarantor NIC No / Reg.No</DIV></td>"); 
			out.println("<td width='15%' align='right' ><DIV id='DIV_TXT_GUA_EXPOSURE'  class=div_input><b>Guarantor Exposure</DIV></td>"); 
			out.println("</tr >"); 	
			}

			out.println("<tr >"); 
			out.println("<td width='25%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs6.getString(1)+"')\"><U>"+rs6.getString(2)+"<input class='txt_input' type='hidden' name='TXT_FULL_NAME' maxlength='250' value=\""+rs6.getString(2)+"\" size='250'></td>"); 
			out.println("<td width='20%' >"+rs6.getString(3)+"<input class='txt_input' type='hidden' name='TXT_ADDRESS1' value=\""+rs6.getString(3)+"\" maxlength='100' size='100'></td>"); 
			out.println("<td width='20%' >"+rs6.getString(4)+"<input class='txt_input' type='hidden' name='TXT_ADDRESS2' value=\""+rs6.getString(4)+"\" maxlength='100' size='100'></td>"); 
			out.println("<td width='20%' >"+rs6.getString(5)+"<input class='txt_input' type='hidden' name='TXT_NIC_NO' value=\""+rs6.getString(5)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='15%' align='right' >"+nf.format(rs6.getDouble(6))+"<input class='txt_input' type='hidden' name='TXT_GUA_EXPOSURE' value=\""+rs6.getDouble(6)+"\" maxlength='10' size='10'> </td>"); 
			out.println("</tr>"); 
			out.println("</table>");

			more6=rs6.next();			
			k=k+1;
			if (!more6)
			{
			break;
			}

			}
	
		
		//INVOICE DETAILS.......
		rs1 = stmt1.executeQuery("SELECT INVOICE_NO,A.VENDOR_CODE,NVL((SELECT NAME FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE VENDOR_CODE IN (A.VENDOR_CODE)),'-'),ADDRESS,A.MODEL_CODE, "+
												   "C.DESCRIPTION,SUB_MODEL_CODE,D.DESCRIPTION,TO_CHAR(TOTAL_AMOUNT,'999,999,999,999,999,999,999.99'),NVL(ENGINE_NO,'-'), "+
													 "NVL(CHASSIS_NO,'-'),NVL(REG_NO,'-') "+
													 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
													 "(SELECT "+
													 "MODEL_CODE,DESCRIPTION,MAKE_CODE,ITEM_SUB_CAT "+
													 "FROM "+m_schema_name+".AF_CO_MAS_MODEL) C, "+
													 "(SELECT "+
													 "SUB_CODE, "+
													 "MODEL_CODE, "+
													 "DESCRIPTION "+
													 "FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE) D "+
													 "WHERE APPLICATION_NO='"+m_applicaton_no+"' "+
													 "AND C.MODEL_CODE=A.MODEL_CODE "+
													 "AND A.ACTIVE_STATUS <> 'C' "+ //NOT CANCELLED
													 "AND D.SUB_CODE=SUB_MODEL_CODE ");


			boolean more1=rs1.next();	
		
		//pricing details.....
			
			
			rs2 = stmt2.executeQuery("SELECT DISTINCT A.PRICING_NO,PERIOD,PAYMENT_INTERVAL, "+
														"	RATE,TO_CHAR(GROSS_AMOUNT,'999,999,999,999,999,999,999.99'),APPLICATION_NO,DESCRIPTION,DURATION_TYPE  "+
														"	FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL B "+
														"	WHERE  A.APPLICATION_NO='"+m_applicaton_no+"' "+
														" AND  PAYMENT_INTERVAL=DURATION ");
			
			
			

			
			boolean more2=rs2.next();	
			
				
			String m_price="";
			
			int l=0;
			out.println("<HR>"); 
			out.println("<br >"); 
			double m_sub_Gtotal=0;

//*********************************************************************************************************************************************************************************************
			while(more2) //PRICING LOOP START
			{
			m_app_code=rs2.getString(1);
		//	out.println("pricing no"+m_app_code);
			out.println("<br >"); 	

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			if(l==0)
				{

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input><b>Pricing No</DIV></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_RATE'  class=div_input><b>Rate </DIV></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_PERIOD'  class=div_input><b>Period </DIV></td>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_INTERVAL'  class=div_input><b>Payment Interval </DIV></td>"); 
			out.println("<td width='20%' align=\"right\"><DIV id='DIV_TXT_GROSS'  class=div_input><b>Total </DIV></td>");
			out.println("</tr >"); 
			}

			out.println("<tr >"); 
			out.println("<td width='20%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_pricing_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"<input class='txt_input' type='hidden' name='TXT_PRICING_NO' maxlength='15' size='15' value=\""+rs2.getString(1)+"\" >"); 
			out.println("<td width='20%' >"+rs2.getString(4)+"<input class='txt_input' type='hidden' name='TXT_RATE'  value=\""+rs2.getString(2)+"\" maxlength='22' size='22'></td>"); 
			out.println("<td width='20%' >"+rs2.getString(2)+"<input class='txt_input' type='hidden' name='TXT_PERIOD'  value=\""+rs2.getString(3)+"\" maxlength='22' size='22'></td>"); 
			out.println("<td width='20%' >"+rs2.getString(7)+"<input class='txt_input' type='hidden' name='TXT_PAYMENT_INTERVAL'  value=\""+rs2.getString(3)+"\" maxlength='22' size='22'></td>"); 
			out.println("<td width='20%' align=\"right\">"+rs2.getString(5)+"<input class='txt_input' type='hidden' name='TXT_GROSS'  value=\""+rs2.getString(5)+"\" maxlength='22' size='22'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			int y=0;
			
			out.println("<HR>"); 
			
		//OTHER CHARGES....	
						
		
/*		 rs = stmt.executeQuery("SELECT DISTINCT A.APPLICATION_NO,A.PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION, "+
														//"A.PRO_INVOICE_NO,"+
														"'', "+
														"TO_CHAR(AMOUNT,'999,999,999,999,999,999,999.99') AS AMOUNT,amount as amt "+
														"FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A, "+
														""+m_schema_name+".AF_CO_MAS_SUB_CHARGES B,"+m_schema_name+".AF_CO_PRO_APP_PRICING C "+
														"WHERE A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE "+
														"AND A.PRICING_NO  =C.PRICING_NO "+
														"and nvl(AMOUNT,0) <> 0 "+
														"and A.PRICING_NO='"+rs2.getString(1)+"' "+
														"AND A.APPLICATION_NO=C.APPLICATION_NO "+
														"AND C.APPLICATION_NO='"+m_applicaton_no+"' ");    
	*/
	
	
					/*rs = stmt.executeQuery("SELECT DISTINCT A.APPLICATION_NO,A.PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION, "+
					//"A.PRO_INVOICE_NO,"+
					" '', "+
					" TO_CHAR(SUM(AMOUNT),'999,999,999,999,999,999,999.99') AS AMOUNT,SUM(amount) as amt, "+
					" CHARGE_TYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A, "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B,"+m_schema_name+".AF_CO_PRO_APP_PRICING C "+
					" WHERE A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE "+
					" AND A.PRICING_NO  =C.PRICING_NO "+
					" and nvl(AMOUNT,0) <> 0 "+
					" and A.PRICING_NO='"+rs2.getString(1)+"' "+
					" AND A.APPLICATION_NO=C.APPLICATION_NO "+
					" AND C.APPLICATION_NO='"+m_applicaton_no+"' "+    
					" GROUP BY A.APPLICATION_NO,A.PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,CHARGE_TYPE  "+
					" ORDER BY CHARGE_TYPE ");
					*/
					
					
			
					rs = stmt.executeQuery("SELECT "+
				  "  PRICING_NO, "+ //1
				  "  SUB_CHAGE_CODE, "+ //2
				  "  DESCRIPTION, "+ //3
				  "  SUM(AMOUNT), "+ //4
					"  CHARGE_TYPE "+ //5
				  "  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
				  "  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
				  "  PRICING_NO='"+rs2.getString(1)+"'  AND "+
				  "  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
				  "  NVL(AMOUNT,0) <> 0 "+
				  "  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,CHARGE_TYPE "+
					"  ORDER BY  CHARGE_TYPE ");
					

			 
																												
				
				boolean more=rs.next();	
				double m_sub_total=0;

//*********************************************************************************************************************************************************************************************
		
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
		  
			String m_charge_type=""; //hold to charge type
			String m_pricing_no=""; //ADDED BY NUWAN DE SILVA 21-05-07
			int count_amo=0;
			int count_inv=0;
			while(more) //OTHER CHARGES LOOP STARTED
				{
			//m_sub_total=m_sub_total+rs.getDouble(7);
			//m_sub_Gtotal=m_sub_Gtotal+rs.getDouble(7);
			
			m_sub_total=m_sub_total+rs.getDouble(4);  //ADDED BY NUWAN DE SILVA 18-05-07
			m_sub_Gtotal=m_sub_Gtotal+rs.getDouble(4);  //ADDED BY NUWAN DE SILVA 18-05-07
			
		//	m_app_code=rs.getString(1);	
      m_charge_type=rs.getString(5);	 //ADDED BY NUWAN DE SILVA 18-05-07
			m_pricing_no=rs.getString(1);	 // //ADDED BY NUWAN DE SILVA 21-05-07
			
			if(y==0)
				{
						
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_SUB_CHAGE_CODE'  class=div_input><b><br><u>Sub charges </DIV></td></tr>"); 
			out.println("<tr ><td width='15%' ></td><td width='15%' ><DIV id='DIV_TXT_SUB_CHAGE_CODE'  class=div_input><b><u>Sub charge </DIV></td>"); 
			out.println("<td width='15%' align=\"right\"><DIV id='DIV_TXT_SUB_CHAGE_CODE'  class=div_input><b><u>Amount</DIV></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("</tr>");
			}
			if(m_charge_type.equals("AMO")){
			
			if(count_amo==0){
			out.println("<tr >"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='15%' ><b><u>Amotise</u></b></td>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='35%' >&nbsp</td>"); 
			out.println("<td width='35%' >&nbsp</td>"); 
			out.println("</tr>"); 
      }

			
			out.println("<tr >"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			//out.println("<td width='15%' >"+rs.getString(4)+"<input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_DESC' value=\""+rs.getString(4)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='15%' >"+rs.getString(3)+"<input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_DESC' value=\""+rs.getString(3)+"\" maxlength='50' size='50'></td>"); 
			//out.println("<td width='15%' align=\"right\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+m_charge_type+"','"+rs.getString(3)+"')\" ><u>"+rs.getString(6)+"</u><input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_AMT' value=\""+rs.getString(6)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='15%' align=\"right\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+m_charge_type+"','"+rs.getString(2)+"')\" ><u>"+nf.format(rs.getDouble(4))+"</u><input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_AMT' value=\""+rs.getString(4)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("</tr>"); 
      count_amo=1;
			
			}
			
			else if(m_charge_type.equals("INV")){
			if(count_inv==0){
			out.println("<tr >"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='15%' ><b><u>Up Front</u></b></td>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='35%' >&nbsp</td>"); 
			out.println("<td width='35%' >&nbsp</td>"); 
			out.println("</tr>"); 
      }

			
			out.println("<tr >"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			//out.println("<td width='15%' >"+rs.getString(4)+"<input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_DESC' value=\""+rs.getString(4)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='15%' >"+rs.getString(3)+"<input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_DESC' value=\""+rs.getString(3)+"\" maxlength='50' size='50'></td>"); 
			//out.println("<td width='15%' align=\"right\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+m_charge_type+"','"+rs.getString(3)+"')\" ><u>"+rs.getString(6)+"</u><input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_AMT' value=\""+rs.getString(6)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='15%' align=\"right\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+m_charge_type+"','"+rs.getString(2)+"')\" ><u>"+nf.format(rs.getDouble(4))+"</u><input class='txt_input' type='hidden' name='TXT_SUB_CHAGE_AMT' value=\""+rs.getString(4)+"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("<td width='35%' ></td>"); 
			out.println("</tr>"); 

			count_inv=1;
			
			}
			
						
			
			more=rs.next();
			
			y=y+1;
			if (!more)
			{
			break;
			}
			
			}	//END OF OTHER CHARGES LOOP
			
			if(m_sub_total!=0)
				{		
					out.println("<tr ></tr>"); 
					out.println("<tr >"); 
					out.println("<td width='30%' >&nbsp</td>"); 
					out.println("<td width='15%' ><b>Total :-</td>");
					out.println("<td width='15%' align=\"right\"><b>"+nf.format(m_sub_total)+"</td>"); 
					out.println("</tr >"); 
			}
			
			out.println("</table>");		
			
			//INSTALLMENTS DETAILS....=================================================================================================
	
		/*		rs3 = stmt3.executeQuery("SELECT DISTINCT A.PRICING_NO,INSTALLMENT_NO,GRENTAL_AMOUNT,TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99') AMT "+
															"FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
															"WHERE A.PRICING_NO=B.PRICING_NO "+
															"AND B.APPLICATION_NO='"+m_applicaton_no+"' "+
															"and A.PRICING_NO='"+rs2.getString(1)+"' "+
															"GROUP BY A.PRICING_NO ,INSTALLMENT_NO,GRENTAL_AMOUNT,TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99') ");
															
		*/
		
/*			rs3 = stmt3.executeQuery("SELECT DISTINCT D.PRICING_NO,INSTALLMENT_NO,GRENTAL_AMOUNT,TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99') AMT, D.INVOICE_NO "+
															" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
															" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
															" WHERE A.PRICING_NO=B.PRICING_NO "+
															" AND B.APPLICATION_NO='"+m_applicaton_no+"' "+
															" AND D.PRICING_NO='"+rs2.getString(1)+"' AND "+
															" B.APPLICATION_NO=D.APPLICATION_NO  AND "+
															" D.ACTIVE_STATUS <>'C' "+
															" GROUP BY D.PRICING_NO ,INSTALLMENT_NO,GRENTAL_AMOUNT,TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99'),D.INVOICE_NO "+
															" ORDER BY D.INVOICE_NO ,TO_NUMBER(INSTALLMENT_NO) ");

*/


					rs3 = stmt3.executeQuery("  SELECT "+
					" A.PRICING_NO, "+
					" INSTALLMENT_NO, "+
					" GRENTAL_AMOUNT, "+
					" TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99') AMT, "+
					" PRO_INVOICE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND A.APPLICATION_NO='"+m_applicaton_no+"'  AND "+
					" A.PRICING_NO='"+rs2.getString(1)+"' AND B.ACTIVE_STATUS <>'C' "+
					" GROUP BY       "+
					" A.PRICING_NO,INSTALLMENT_NO,GRENTAL_AMOUNT,TO_CHAR(GRENTAL_AMOUNT,'999,999,999,999,999,999,999.99'),PRO_INVOICE_NO "+
					" ORDER BY PRO_INVOICE_NO,TO_NUMBER(INSTALLMENT_NO) ");
	
			
			boolean more3=rs3.next();	
			
		
			int d=0;
			int f=0;
			
			int start=0;
			int end=0;
			int bal=0;
			double m_g_rent = 0;
			String e="";
			String m_ins="";
			String m_inv_no="";
			double m_rental=0;
			
			out.println("<br >"); 	
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
//*********************************************************************************************************************************************************************************************
		 
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_INSTALL'  class=div_input><b><br><u>Instalments</DIV></td>"); 
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='15%' ><b><u>Invoice No</u></b></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INSTALL_NO'  class=div_input><b><u>No of Instalments </DIV></td>"); 
			out.println("<td width='15%' align=\"right\"><DIV id='DIV_TXT_INSTALL_AMT'  class=div_input><u><b>Amount </DIV></td>"); 
			out.println("<td width='*%' ></td>"); 
			out.println("</tr>");

			
			if(more3)
				{
			while(more3) //START INSTALLMENT LOOP
				{
			
			
		   m_inv_no=rs3.getString(5);	
			 m_rental=rs3.getDouble(3);
  		 start=rs3.getInt(2);
  		
				while(m_inv_no.equals(rs3.getString(5)))
				{
				
				
				if(m_rental!=rs3.getDouble(3))
					{
				
			
				
				
				        out.println("<tr >"); 
								out.println("<td width='15%' >&nbsp</td>"); 
								out.println("<td width='15%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+m_inv_no+"')\" ><u>"+m_inv_no+"</u></td>"); 
								
								if(start==end)
					{
								out.println("<td width='15%' >"+start+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								
								else
					{
								out.println("<td width='15%' >"+start+" - "+end+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								out.println("<td width='15%' align=\"right\">"+nf.format(m_rental)+"<input class='txt_input' type='hidden' name='TXT_INSTALL_AMT' value=\"\" maxlength='50' size='50'></td>"); 
								out.println("<td width='*%' >&nbsp</td>"); 
								out.println("</tr>"); 
								
				        start=rs3.getInt(2);			
								m_rental=rs3.getDouble(3);
								
				 }

				
				end=rs3.getInt(2);
				 				
				
				more3=rs3.next();
				
				if(!more3)
					{
				break;
				}
				
								
				}
				
				
				        out.println("<tr >"); 
								out.println("<td width='15%' >&nbsp</td>"); 
								out.println("<td width='15%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+m_inv_no+"')\" ><u>"+m_inv_no+"</u></td>"); 
								
								if(start==end)
				{
								out.println("<td width='15%' >"+start+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								
								else
				{
								out.println("<td width='15%' >"+start+" - "+end+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								out.println("<td width='15%' align=\"right\">"+nf.format(m_rental)+"<input class='txt_input' type='hidden' name='TXT_INSTALL_AMT' value=\"\" maxlength='50' size='50'></td>"); 
								out.println("<td width='*%' >&nbsp</td>"); 
								out.println("</tr>"); 

						
				
			
			
			} //END OF INSTALLMENT LOOP
			
			
			}
			
		/*	while(more3){
		
		m_inv_no=rs3.getString(5);
			      out.println(m_inv_no);
			          m_rental=rs3.getDouble(3);
								start=rs3.getInt(2);
			        
							
					     
							 while(!m_inv_no.equals(rs3.getString(5)) ){
						
						   out.println("1"+rs3.getString(5));
							 out.println("1"+m_rental);
								
								if(nf.format(m_rental)!=nf.format(rs3.getDouble(3)));
								{
								
								out.println("<tr >"); 
								out.println("<td width='15%' >"+m_inv_no+"</td>"); 
								
								if(start==end){
								out.println("<td width='15%' >"+start+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								
								else{
								out.println("<td width='15%' >"+start+" - "+end+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
								}
								out.println("<td width='15%' align=\"right\">"+nf.format(m_rental)+"<input class='txt_input' type='hidden' name='TXT_INSTALL_AMT' value=\"\" maxlength='50' size='50'></td>"); 
								out.println("<td width='*%' >&nbsp</td>"); 
								out.println("</tr>"); 
							 	
								m_rental=rs3.getDouble(3);
								start=rs3.getInt(2);
								
								}
								
								end=end+1;
								rs3.next();
						  }
												
			      more3=rs.next();
			}
			
		*/
		
		  /*while(more3)
			{
			
			if(d==0){		
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_INSTALL'  class=div_input><b><br><u>Installments</DIV></td>"); 
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='15%' ><b><u>Invoice No</u></b></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INSTALL_NO'  class=div_input><b><u>No of Installments </DIV></td>"); 
			out.println("<td width='15%' align=\"right\"><DIV id='DIV_TXT_INSTALL_AMT'  class=div_input><u><b>Amount </DIV></td>"); 
			out.println("<td width='*%' ></td>"); 
			out.println("</tr>");
			}
					


	     m_g_rent=rs3.getDouble(3);//
			 m_ins=rs3.getString(4);//
			 end  = rs3.getInt(2);	//
			 m_invoice_no	=rs3.getString(5);//
				
				
		  more3=rs3.next();
				
			if(more3){

			//if(m_invoice_no!=rs3.getString(5)) {
								
						
			if(m_g_rent!=rs3.getDouble(3) ){
			
			
			out.println("<tr >"); 
			out.println("<td width='15%' >"+m_invoice_no+"</td>"); 

			if(start==end){
			out.println("<td width='15%' >"+start+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
			}
			
			else{
			out.println("<td width='15%' >"+start+" - "+end+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
			}
			
			out.println("<td width='15%' align=\"right\">"+m_ins+"<input class='txt_input' type='hidden' name='TXT_INSTALL_AMT' value=\"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='*%' >&nbsp</td>"); 
			out.println("</tr>"); 
			start = end +1;
			}
			
		  }
			else{
			out.println("<tr >"); 
			out.println("<td width='15%' >"+m_invoice_no+"</td>"); 
			out.println("<td width='15%' >"+start+" - "+end+"<input class='txt_input' type='hidden' name='TXT_INSTALL' value=\"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='15%' align=\"right\">"+m_ins+"<input class='txt_input' type='hidden' name='TXT_INSTALL_AMT' value=\"\" maxlength='50' size='50'></td>"); 
			out.println("<td width='*%' >&nbsp</td>"); 
			out.println("</tr>"); 
		 }	
					

			d=d+1;
			
					}
					
			*/		
			out.println("</table>");


			
			more2=rs2.next();	
			
			if (!more2)
			{
			break;
			}
								
			}
			
			out.println("<HR>"); 

			out.println("<table align='center' width='100%' class='table' border=\"0\"><tr class=pdn_txtpos2>"); 

			out.println("<tr ></tr>"); 
			out.println("<tr class=line>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='30%' ><b>Grand total for all pricings :-</td>");
			out.println("<td width='15%' align=\"right\"><b>"+nf.format(m_sub_Gtotal)+"</td>"); 
			out.println("<td width='*%' >&nbsp</td>"); 

			out.println("</tr >"); 

			out.println("</table >");
			out.println("<HR>"); 

			out.println("<br >"); 
			out.println("<HR>"); 
			if(more1)
			{

			out.println("<table align='center' width='100%' class='table' border=\"0\"><tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_INVOICE'  class=div_input><b>Invoice</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_NAME'  class=div_input><b>Supplier</DIV></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_ADDRESS'  class=div_input><b>Address</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_MODEL_CODE'  class=div_input><b>Model</DIV></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_SUB_MODEL_CODE'  class=div_input><b>Sub model</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_COST'  class=div_input><b>Cost</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input><b>Engine No</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input><b>Chassis No</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_REG_NO'  class=div_input><b>Vehicle No</DIV></td>"); 
			out.println("</tr >"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("</table>");
			}
//*********************************************************************************************************************************************************************************************
		while(more1)
			{	

			out.println("<table align='center' width='100%' class='table' border=\"0\"><tr>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"<input class='txt_input' type='hidden' name='TXT_INVOICE_NO' value=\""+rs1.getString(1)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='10%' >"+rs1.getString(3)+"<input class='txt_input' type='hidden' name='TXT_NAME' value=\""+rs1.getString(3)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='15%' >"+rs1.getString(4)+"<input class='txt_input' type='hidden' name='TXT_ADDRESS' value=\""+rs1.getString(4)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='10%' WIDTH=\"6%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model_details_drill('"+rs1.getString(5)+"')\"><U>"+rs1.getString(6)+"<input class='txt_input' type='hidden' name='TXT_MODEL_CODE' value=\""+rs1.getString(5)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='15%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub_model_details_drill('"+rs1.getString(7)+"')\"><U>"+rs1.getString(8)+"<input class='txt_input' type='hidden' name='TXT_SUB_MODEL_CODE' value=\""+rs1.getString(7)+"\" maxlength='10' size='10'></td>"); 
			out.println("<td width='10%' >"+rs1.getString(9)+"<input class='txt_input' type='hidden' name='TXT_COST' maxlength='50' value=\""+rs1.getString(9)+"\" size='50'></td>"); 
			out.println("<td width='10%' >"+rs1.getString(10)+"<input class='txt_input' type='hidden' name='TXT_ENGINE_NO' maxlength='50' value=\""+rs1.getString(10)+"\" size='50'></td>"); 
			out.println("<td width='10%' >"+rs1.getString(11)+"<input class='txt_input' type='hidden' name='TXT_CHASSIS_NO' maxlength='50' value=\""+rs1.getString(11)+"\" size='50'></td>"); 
			out.println("<td width='10%' >"+rs1.getString(12)+"<input class='txt_input' type='hidden' name='TXT_REG_NO' value=\""+rs1.getString(12)+"\" maxlength='20' size='20'></td>"); 
			out.println("</tr >"); 
			out.println("</table>");
			more1=rs1.next();
			
			
			if (!more1)
			{
			break;
			}
			}
			
			
			out.println("<br>");
					
			//issuer code
			rs5= stmt5.executeQuery ("SELECT DISTINCT ISSUER_CODE,TO_CHAR(AMOUNT,'999,999,999,999,999.99'),TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(START_DATE,'DD-MM-YYYY'), "+
			"TO_CHAR(END_DATE,'DD-MM-YYYY'),STATUS "+
			"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES "+	
			"WHERE UPPER(APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  "+
			"AND STATUS='Y' ");


			boolean more5=rs5.next();	
			int j=0;
			out.println("<HR>"); 

				
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
//*********************************************************************************************************************************************************************************************
				while(more5)
			{
				
				if(j==0)
					{
		
			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Issuer Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=pdn_txtpos2>");
						
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Code</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Amount</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"20%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_bank_drill('"+rs5.getString(1)+"')\"><U>"+rs5.getString(1)+"</td>");
			out.println("<td width=\"20%\" >"+rs5.getString(2)+"</td>");
			out.println("<td width=\"20%\" >"+rs5.getString(3)+"</td>");
			out.println("<td width=\"20%\" >"+rs5.getString(4)+"</td>");
			out.println("<td width=\"20%\" >"+rs5.getString(5)+"</td></tr>");
			
			more5=rs5.next();
			j=j+1;
			}
			out.println("</table>");
			out.println("<br >"); 




			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width='*%'><b><u>Conditions</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width='*%'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\" ></td>"); 
			out.println("</tr>");  
				
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			
			out.println("</table>");
			
				
			out.println("<HR>"); 
			out.println("<hr>");
			
			
			

		if(m_Hid_scr_name.equals("AF_MK_APP_STATUS_APPROVE_2") || m_Hid_scr_name.equals("AF_MK_APP_STATUS_APPROVE_3"))
			{

			rs7= stmt7.executeQuery//enter
			("SELECT APPLICATION_NO, NVL(REMARK,'N/A') ,'Application entry level','1' AS E_LEVEL "+
    	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
			" WHERE STATUS IN ('ENTERED') AND APPLICATION_NO='"+m_applicaton_no+"' "+//");
		  "	AND ENT_DATE IN "+
		  " (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE APPLICATION_NO='"+m_applicaton_no+"' AND STATUS='ENTERED') "+
			" UNION "+
			"  SELECT APPLICATION_NO, NVL(REMARK,'N/A'),'Credit Verification Level:-','2' AS E_LEVEL "+
   	 	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
			" WHERE  STATUS='VERIFY1' AND APPLICATION_NO='"+m_applicaton_no+"' "+//");
			"	AND ENT_DATE IN "+
		  " (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE APPLICATION_NO='"+m_applicaton_no+"' AND STATUS='VERIFY1') "+
			"	UNION "+
			"	SELECT APPLICATION_NO,NVL(REMARK,'N/A'),'Credit Score Evaluation Level:-','3' AS E_LEVEL "+//approval1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
			" WHERE APPLICATION_NO='"+m_applicaton_no+"'  AND STATUS='V-APP' "+//");
			"	AND ENT_DATE IN "+
		  " (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE APPLICATION_NO='"+m_applicaton_no+"' AND STATUS='V-APP') "+
			"	UNION "+
			"	SELECT APPLICATION_NO,NVL(REMARK,'N/A'),'Credit Approval 1 Level:-','4' AS E_LEVEL "+//approval1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
			" WHERE APPLICATION_NO='"+m_applicaton_no+"'  AND STATUS='VERIFY-M' "+
			"	AND ENT_DATE IN "+
		  " (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE APPLICATION_NO='"+m_applicaton_no+"' AND STATUS='VERIFY-M') "+
			"	ORDER BY E_LEVEL ASC ");

  
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' valign='top'><class=div_input><b>Remarks :-</DIV></td>"); 

			out.println("<td width='*%' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<hr>");			
					
			out.println("<table align='center' width='100%' class='table'>"); 
			while(rs7.next())
			{
			
			
			out.println("<tr >"); 
      out.println("<td width='30%' valign='top'><B>"+rs7.getString(3)+"</B></td>");
			out.println("<td width='*%' >"+rs7.getString(2)+"</td>"); //<DIV ID='m_table_remaks2'></DIV>
			out.println("</tr>");	
			}
			out.println("</table>");
			out.println("<hr>");	
		}
//*********************************************************************************************************************************************************************************************

					
				String m_string="";
				
				rs7= stmt.executeQuery 
				("SELECT DISTINCT APPLICATION_CODE,SCORE_MODEL_CODE,NVL(FINAL_APP_SCORE,0),"+
				 " NVL(MODEL_SCORE,0),"+
				 " NVL(COMMENTS,'-') "+
				 " FROM "+m_schema_name+".AF_CR_PRO_CRSCORE "+
				 " WHERE APP_STATUS='"+m_pre_stage1+"' AND APPLICATION_CODE = '"+m_applicaton_no+"'  ");				
				int b=0;


				m_string="<table align='center' width='100%' class='table' border=\"0\" >";

				m_string=m_string+"<tr class=\"pdn_txtpos2\">";

				//m_string=m_string+"<td width='15%' align='left'>Application No</td>";
				m_string=m_string+"<td width='20%' align='left'>Credit Score Model</td>";
				m_string=m_string+"<td width='15%' align='left'>Application Score</td>"; 
				m_string=m_string+"<td width='10%' align='left'>Model Score</td>"; 
				m_string=m_string+"<td width='10%' align='center'>Details</td>"; 
				m_string=m_string+"<td width='35%' align='left'>Comments</td>"; 
				m_string=m_string+"<td width='5%' align='center'>Approve</td>"; 
				m_string=m_string+"<td width='5%' align='center'>Reject</td>";
				m_string=m_string+"</tr>";
	
	
				while(rs7.next())
			{	
					if(b>0 && b%2==1)
					{
					m_string=m_string+"<tr class=\"tr_input1\">";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs7.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"APP_"+b+"\" VALUE=\""+rs7.getString(1)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs7.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+b+"\" VALUE=\""+rs.getString(2)+"\"></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs7.getDouble(3))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs7.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View\" NAME=\"VIEW\" onclick=\"display_eval('"+rs7.getString(1)+"','"+rs7.getString(2)+"')\"></DIV></td>"; 
					m_string=m_string+"<td width='20%' align='left' ><TEXTAREA NAME=\"COMMENT_"+b+"\" class='txt_input' style=\"width:350px; height:50px;\" onkeyPress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"  maxlength='1000'></TEXTAREA></td>";
														
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\" value=\"N\" NAME=\"CHK_"+b+"\" onclick=change("+b+")></DIV></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\" value=\"N\" NAME=\"CHK_REJ_"+b+"\" onclick=change_reject("+b+")></DIV></td>"; 
					
					m_string=m_string+"</tr>";
					}
					else
					{
					m_string=m_string+"<tr class=\"tr_input\">";
					//m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs7.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"APP_"+b+"\" VALUE=\""+rs7.getString(1)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs7.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+b+"\" VALUE=\""+rs7.getString(2)+"\"></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs7.getDouble(3))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs7.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View\" NAME=\"VIEW\" onclick=\"display_eval('"+rs7.getString(1)+"','"+rs7.getString(2)+"')\"></DIV></td>"; 
					//m_string=m_string+"<td width='20%' align='left'><input style=\"{width:190}\"  class='txt_input' type=\"textarea\" NAME=\"COMMENT_"+i+"\"  maxlength='500' ></td>"; 
					m_string=m_string+"<td width='35%' align='left' ><TEXTAREA NAME=\"COMMENT_"+b+"\" class='txt_input' style=\"width:350px; height:50px;\"  onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"maxlength='1000'></TEXTAREA></td>";
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\"  value=\"N\" NAME=\"CHK_"+b+"\" onclick=change("+b+")></DIV></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\"  value=\"N\" NAME=\"CHK_REJ_"+b+"\" onclick=change_reject("+b+")></DIV></td>"; 
					m_string=m_string+"</tr>";
					
					m_string=m_string+"<INPUT TYPE=\"HIDDEN\" NAME=\"APP_"+b+"\" VALUE=\""+rs7.getString(1)+"\"></td>";

					}
					
					b++;
				}
				
				m_string=m_string+"<INPUT TYPE='hidden' NAME='NUM_CHKS' VALUE="+b+">";
				//m_string=m_string+"<INPUT TYPE='hidden' NAME='Hid_app_no' VALUE="+m_applicaton_no+">"; //Added By Nuwan De silva 27-04-2007

				m_string=m_string+"</table>";
				out.println(m_string);
			
//*********************************************************************************************************************************************************************************************
					
			out.println("<br>"); 
			
			out.println("<hr>");
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
	
			out.println("</table>"); 
			out.println("<br>"); 
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
		catch (Exception ex) 
		{
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally
		{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
