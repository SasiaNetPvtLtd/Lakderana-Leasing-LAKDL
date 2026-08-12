// DEVELOP BY : CHANDANA FOR OFSCL Leasing    DATE:29-01-2008

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;   
import java.sql.*; 
import java.util.*; 
 
 
public class LAKDL_FA_CR_display_credit_score_evaluation_appro_level extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
      String m_schema_name = m_sn_methods.schema_name.trim();
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			ServletOutputStream out = res.getOutputStream(); 
				
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
			
			String m_application_no = req.getParameter("fas_no");
			String m_client_no = req.getParameter("client_code");
			String m_client_name = "";
			String m_eval_user = "";
			String m_comment = "";
			rs = stmt.executeQuery ("SELECT "+ 
															" FACILITY_NO, "+ 
															" CLIENT_CODE,"+
															" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
															" EVAL_USER, "+ 		
															" NVL(COMMENTS,'-') COMMENTS "+ 
															" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+
															" WHERE FACILITY_NO = '"+m_application_no+"' "+
															" AND CLIENT_CODE = '"+m_client_no+"' ");
			if(rs.next()){
			m_client_name = rs.getString(3);
			m_eval_user = rs.getString(4);
			m_comment = rs.getString(5);
			}											
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Credit Score </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("function load_screen_disapp(){");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_display_credit_score_enter\";");
			out.println("window.open(m_url,'displayWindow4','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");  
			out.println("}");
			
			out.println("function load_approve_data(){");
			/*if(m_application_no.equals("") ||  m_client_no.equals("")){
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\";");
			out.println("document.Form1.TXT_CLIENT_NO.value=\"\";");
			out.println("document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			out.println("document.Form1.TXT_CREDIT_EVAL.value=\"\";"); 
			out.println("document.Form1.TXT_COMMENTS.value=\"\";");
			}else{
			*/
			out.println("document.Form1.TXT_APPLICATION_NO.value=\""+m_application_no+"\" ;");
			out.println("document.Form1.TXT_CLIENT_NO.value=\""+m_client_no+"\" ;");
			out.println("document.Form1.TXT_CLIENT_NAME.value=\""+m_client_name+"\";"); 
			out.println("document.Form1.TXT_CREDIT_EVAL.value=\""+m_eval_user+"\";"); 
			out.println("document.Form1.TXT_COMMENTS.value=\""+m_comment+"\";");
			out.println("makeRequest1(document.Form1.TXT_APPLICATION_NO,document.Form1.TXT_CLIENT_NO);");
			out.println("}");
			
			
			out.println("function makeRequest1(obj1,obj2) {");
			//out.println("alert(obj1.value);");
			out.println(" document.Form1.hid_type.value='M1';");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation1&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("alert(m_url);");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
		  
			out.println("function makeRequest2(obj1,obj2) {");
			out.println("document.Form1.hid_type.value='M2';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation2&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest3(obj1,obj2) {");
			out.println("document.Form1.hid_type.value='M3';");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation3&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_type.value=='M1' ){");
			//out.println("				alert('Record already exist123');");
			out.println(" assign_data1(data_vec);");
			out.println("			}");			
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_type.value=='M2' ){");
			//out.println("				alert('Record already exist12345');");
			out.println(" assign_data2(data_vec);");
			out.println("			}");			
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_type.value=='M3' ){");
			//out.println("				alert('Record already exist12345');");
			out.println("    assign_data3(data_vec);");
			out.println("			}");		
			out.println("   }");
			
			out.println("   function assign_data1(data_vec) { ");
			out.println("    document.Form1.TXT_DIRECTORS_STATUS.value=data_vec[3]; ");
			out.println("    document.Form1.TXT_DIRECTOR_VALUE.value=data_vec[8]; ");
			out.println("    document.Form1.TXT_REPUTATION_STATUS.value=data_vec[4]; ");
			out.println("    document.Form1.TXT_REPUTATION_VALUE.value=data_vec[9]; ");
			out.println("    document.Form1.TXT_REF_BANK_STATUS.value=data_vec[5]; ");
			out.println("    document.Form1.TXT_REF_BANK_VALUE.value=data_vec[10]; "); 
			out.println("    document.Form1.TXT_REF_TRADE_STATUS.value=data_vec[6]; ");
			out.println("    document.Form1.TXT_REF_TRADE_VALUE.value=data_vec[11]; ");
			out.println("    document.Form1.TXT_CAPACITY_STATUS.value=data_vec[7]; ");
			out.println("    document.Form1.TXT_CAPACITY_VALUE.value=data_vec[12]; ");
			out.println("    total_value = parseInt(document.Form1.TXT_DIRECTOR_VALUE.value)+parseInt(document.Form1.TXT_REPUTATION_VALUE.value)+parseInt(document.Form1.TXT_REF_BANK_VALUE.value)+parseInt(document.Form1.TXT_REF_TRADE_VALUE.value)+parseInt(document.Form1.TXT_CAPACITY_VALUE.value)");
			out.println("    document.Form1.TXT_TOT_VALUE1.value=total_value; ");
			out.println("    makeRequest2(document.Form1.TXT_APPLICATION_NO,document.Form1.TXT_CLIENT_NO);");
			out.println("   }");			
			
			out.println("   function assign_data2(data_vec){");
			out.println("    document.Form1.TXT_PRODUCT.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_PRODUCT_VALUE.value=data_vec[13]; ");
			out.println("    document.Form1.TXT_INDUSTRY.value=data_vec[11]; ");
			out.println("    document.Form1.TXT_INDUSTRY_VALUE.value=data_vec[24]; ");
			out.println("    document.Form1.TXT_RESONS.value=data_vec[2]; ");
			out.println("    document.Form1.TXT_RESONS_VALUE.value=data_vec[15]; "); 
			out.println("    document.Form1.TXT_OPERATIONS1.value=data_vec[3]; ");
			out.println("    document.Form1.TXT_OPERATION_VALUE1.value=data_vec[16]; ");
			out.println("    document.Form1.TXT_OPERATIONS2.value=data_vec[4]; ");
			out.println("    document.Form1.TXT_OPERATION_VALUE2.value=data_vec[17]; ");
			out.println("    document.Form1.TXT_OPERATIONS3.value=data_vec[5]; ");
		  out.println("    document.Form1.TXT_OPERATION_VALUE3.value=data_vec[18]; ");
			out.println("    document.Form1.TXT_OPERATIONS4.value=data_vec[6]; ");
			out.println("    document.Form1.TXT_OPERATION_VALUE4.value=data_vec[19]; ");
			out.println("    document.Form1.TXT_BANKING_REC1.value=data_vec[7]; ");
			out.println("    document.Form1.TXT_BANKING_VALUE1.value=data_vec[20]; ");
			out.println("    document.Form1.TXT_BANKING_REC2.value=data_vec[8]; ");
			out.println("    document.Form1.TXT_BANKING_VALUE2.value=data_vec[21]; ");
			out.println("    document.Form1.TXT_BANKING_REC3.value=data_vec[9]; ");
			out.println("    document.Form1.TXT_BANKING_VALUE3.value=data_vec[22]; ");
			out.println("    document.Form1.TXT_BANKING_REC4.value=data_vec[10]; ");
			out.println("    document.Form1.TXT_BANKING_VALUE4.value=data_vec[23]; "); 
			out.println("    document.Form1.TXT_REFERENCE_STATUS.value=data_vec[1]; ");
			out.println("    document.Form1.TXT_REFERENCE_VALUE.value=data_vec[14]; ");
			out.println("    document.Form1.TXT_FINANCIAL.value=data_vec[12]; ");
			out.println("    document.Form1.TXT_FINANCIAL_VALUE.value=data_vec[25]; "); 
			out.println("tot_product = parseInt(data_vec[13])+parseInt(data_vec[24])+parseInt(data_vec[15])+parseInt(data_vec[16])+parseInt(data_vec[17])+parseInt(data_vec[18])+"+
			            "parseInt(data_vec[19])+parseInt(data_vec[20])+parseInt(data_vec[21])+parseInt(data_vec[22])+parseInt(data_vec[23])+parseInt(data_vec[14])+parseInt(data_vec[25])");
			out.println("    document.Form1.TXT_TOT_VALUE2.value=tot_product; ");
			out.println("    makeRequest3(document.Form1.TXT_APPLICATION_NO,document.Form1.TXT_CLIENT_NO);");
			out.println("   }");			
			
			out.println("   function assign_data3(data_vec){");
			out.println("    document.Form1.TXT_DEBTORS.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_DEBTORS_VALUE.value=data_vec[4]; ");
			out.println("    document.Form1.TXT_TOP_DEBTORS.value=data_vec[1]; ");
			out.println("    document.Form1.TXT_TOP_DEBTORS_VALUE.value=data_vec[5]; ");
			out.println("    document.Form1.TXT_DEBTOR_TRACK_STATUS.value=data_vec[2]; ");
			out.println("    document.Form1.TXT_DEBTOR_TRACK_VALUE.value=data_vec[6]; "); 
			out.println("    document.Form1.TXT_REF_OBTAINED_STATUS.value=data_vec[3]; ");
      out.println("    document.Form1.TXT_REF_OBTAINED_VALUE.value=data_vec[7]; ");
			out.println("    document.Form1.TXT_TOT_VALUE3.value=parseInt(data_vec[4])+parseInt(data_vec[5])+parseInt(data_vec[6])+parseInt(data_vec[7]); ");			



			out.println("   }");
	
		  out.println("function makeRequest(obj) {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("try {");
			out.println("http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("} catch (e) {");
			out.println("try {");
			out.println("http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("} catch (e) {}");
			out.println("}");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("return false;");
			out.println("}");
			out.println("url=\"\";");
			out.println("if(obj==\"M1\"){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_EDIT&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&application_no=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("}else{");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_ENTER&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value;");
			out.println("}");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1,obj); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
			out.println("function alertContents(http_request,count,obj) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					if(obj==\"M1\"){");
			//out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("					else{");
			out.println("						//alert(m_data);");
			//out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			out.println(" }");
			out.println("}");

			out.println("function valdate_values(obj,min,max){");
			out.println("objname=document.Form1.elements[\"SCORE_\"+obj];");
			out.println("if((parseFloat(objname.value)>parseFloat(max)) || (parseFloat(objname.value)<parseFloat(min))){");
			out.println("alert('Credit score should between the Min and Max value for the Score Category');");
			out.println("objname.value=\"0\";");
			out.println("}");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function load_default_score(val1){");
			out.println("obj_select=document.Form1.elements[\"RATE_\"+val1];");
			out.println("val=obj_select.value;");
			out.println("obj1=document.Form1.elements[\"RATE_VAL_\"+val];");
			out.println("obj2=document.Form1.elements[\"SCORE_\"+val1];");
			out.println("obj3=document.Form1.elements[\"MAX_\"+val1];");
			out.println("obj2.value=format_noobject((parseFloat(obj3.value)/100)*parseFloat(obj1.value));");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			/*
			out.println("if(parseFloat(document.Form1.TXT_TOTAL_SCORE_APP.value)>parseFloat(document.Form1.TXT_TOTAL_SCORE.value)){");
			out.println("DIV_TXT_TOTAL_SCORE_AP.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			*/
			out.println("if(document.Form1.TXT_CREDIT_EVAL.value==\"\"){  "); 
			out.println("DIV_TXT_USER.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			/*
			out.println("else if(document.Form1.TXT_SCORE_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SCORE_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for(var i=0;i<document.Form1.elements.length;i++){");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_CR_save_credit_score_evalution';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_credit_score_evaluation?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_credit_score_evaluation?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_display_credit_score_evaluation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Evaluation  - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Evaluation  - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_CR_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_update_value_assign_10();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
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
			out.println("	clear_data();");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("Close_2();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			out.println("function Close_2(){");
			out.println("clear_data()	");
      //out.println("window.close();");
      out.println("}");
			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"10\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			//out.println("document.Form1.TXT_CLIENT_NO.value='';");
			//out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			//out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			//out.println("document.Form1.TXT_COMMENTS.value='';"); 
			//out.println("score_details.innerHTML=\"\";");
			out.println("}");
			/*
			out.println("if(document.Form1.hid_help_type.value==\"100\"){");
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("document.Form1.TXT_CREDIT_EVAL.focus();");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			//out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';");
			//out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			*/
			out.println("}");

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 		
			
			out.println("function help_update_user() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CREDIT_EVAL.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','6');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update_model() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_SCORE_MODEL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_application() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			//out.println("    m_sql = \"m_help_DIV_TXT_APPLICATION_CR_SOCRE_sql\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");			
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EDIT_sql_NEW\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}");
			out.println("}"); 
			
			out.println("function help_update_value_assign_10() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];");
			out.println("document.Form1.TXT_COMMENTS.value=oBj.valout[6];");
			//out.println("    makeRequest1(document.Form1.TXT_APPLICATION_NO,document.Form1.TXT_CLIENT_NO);");
			out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			//out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[5];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value=oBj.valout[7];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[6];"); 			
			out.println("}"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			//out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[2];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[4];"); 
			//out.println("    makeRequest('M2');");
			out.println("}"); 
			
			out.println("function load_default_user(){");
			out.println("    document.Form1.TXT_CREDIT_EVAL.value='"+m_username+"';"); 
			out.println("}"); 
			
			out.println("function load_view_score(){");
			//out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_VIEW&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			//out.println("window.open(url,'win1','left=0,top=1,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			out.println("}"); 
			
			
			out.println("function load_values(val){");
			
			out.println(" if(val == 'DIRECTORS'){");
			//out.println("  alert(document.Form1.TXT_DIRECTORS_STATUS.value);"); 
			out.println(" if(document.Form1.TXT_DIRECTORS_STATUS.value == 'EXCE'){");			
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '25';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '25';");
			out.println("}else if(document.Form1.TXT_DIRECTORS_STATUS.value == 'GOOD'){");			
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '18';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '18';");
			out.println("}else if(document.Form1.TXT_DIRECTORS_STATUS.value == 'SATI'){");
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '15';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '15';");
			out.println("}else if(document.Form1.TXT_DIRECTORS_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '10';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '10';");
		  out.println("}else if(document.Form1.TXT_DIRECTORS_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '6';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '6';");
			out.println("}else if(document.Form1.TXT_DIRECTORS_STATUS.value == 'POOR'){");
			out.println(" document.Form1.TXT_DIRECTORS_STATUS_VALUE.value = '0';");
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value = '0';");
			out.println("}"); 
      out.println("}"); 
			
			
			out.println(" if(val == 'REPUTATION'){"); 
			out.println(" if(document.Form1.TXT_REPUTATION_STATUS.value == 'EXCE'){");			
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '20';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '20';");
			out.println("}else if(document.Form1.TXT_REPUTATION_STATUS.value == 'GOOD'){");			
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '18';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '18';");
			out.println("}else if(document.Form1.TXT_REPUTATION_STATUS.value == 'SATI'){");
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '15';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '15';");
			out.println("}else if(document.Form1.TXT_REPUTATION_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '10';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '10';");
		  out.println("}else if(document.Form1.TXT_REPUTATION_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '6';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '6';");
			out.println("}else if(document.Form1.TXT_REPUTATION_STATUS.value == 'POOR'){");
			out.println(" document.Form1.TXT_REPUT_VALUE.value = '0';");
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value = '0';");
			out.println("}"); 
			out.println("}"); 

      
			out.println(" if(val == 'REF_BANK'){"); 
			out.println(" if(document.Form1.TXT_REF_BANK_STATUS.value == 'GOOD'){");			
			out.println(" document.Form1.TXT_BANK_VALUE.value = '20';");
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value = '20';");
			out.println("}else if(document.Form1.TXT_REF_BANK_STATUS.value == 'SATI'){");
			out.println(" document.Form1.TXT_BANK_VALUE.value = '7';");
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value = '7';");
			out.println("}else if(document.Form1.TXT_REF_BANK_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.TXT_BANK_VALUE.value = '5';");
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value = '5';");
		  out.println("}else if(document.Form1.TXT_REF_BANK_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.TXT_BANK_VALUE.value = '3';");
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_REF_BANK_STATUS.value == 'POOR'){");
			out.println(" document.Form1.TXT_BANK_VALUE.value = '0';");
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value = '0';");
			out.println("}"); 
			out.println("}"); 
			
			
			out.println(" if(val == 'REF_TRADE'){"); 
			out.println(" if(document.Form1.TXT_REF_TRADE_STATUS.value == 'GOOD'){");			
			out.println(" document.Form1.TXT_TRADE_VALUE.value = '20';");
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value = '20';");
			out.println("}else if(document.Form1.TXT_REF_TRADE_STATUS.value == 'SATI'){");
			out.println(" document.Form1.TXT_TRADE_VALUE.value = '7';");
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value = '7';");
			out.println("}else if(document.Form1.TXT_REF_TRADE_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.TXT_TRADE_VALUE.value = '5';");
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value = '5';");
		  out.println("}else if(document.Form1.TXT_REF_TRADE_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.TXT_TRADE_VALUE.value = '3';");
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_REF_TRADE_STATUS.value == 'POOR'){");
			out.println(" document.Form1.TXT_TRADE_VALUE.value = '0';");
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value = '0';");
			out.println("}"); 
			out.println("}"); 
			
      
			out.println(" if(val == 'CAPACITY'){"); 
			out.println(" if(document.Form1.TXT_CAPACITY_STATUS.value == 'IMMD'){");			
			out.println(" document.Form1.CAPACITY_VALUE.value = '15';");
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value = '15';");
			out.println("}else if(document.Form1.TXT_CAPACITY_STATUS.value == 'RESN'){");
			out.println(" document.Form1.CAPACITY_VALUE.value = '12';");
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value = '12';");
			out.println("}else if(document.Form1.TXT_CAPACITY_STATUS.value == 'OVER'){");
			out.println(" document.Form1.CAPACITY_VALUE.value = '8';");
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value = '8';");
		  out.println("}else if(document.Form1.TXT_CAPACITY_STATUS.value == 'VERY'){");
			out.println(" document.Form1.CAPACITY_VALUE.value = '5';");
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value = '5';");
			out.println("}else if(document.Form1.TXT_CAPACITY_STATUS.value == 'NIL'){");
			out.println(" document.Form1.CAPACITY_VALUE.value = '0';");
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value = '0';");
			out.println("}"); 
			out.println("}");
						
      out.println(" get_sum1();");
			out.println("}"); 
			
			out.println("function get_sum1(){");
			out.println(" m_total1=0;");
			out.println(" m_total1= parseFloat(unformat_noobject(document.Form1.TXT_DIRECTOR_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_REPUTATION_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_REF_BANK_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_REF_TRADE_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_CAPACITY_VALUE.value));"); 
			//out.println(" alert('wwww'+m_total1);");
			out.println(" document.Form1.TXT_TOT_VALUE1.value= m_total1;"); 
			out.println("}");
			
			out.println("function load_values2(val){");
			
			out.println(" if(val == 'PRODUCT'){");			
			out.println(" if(document.Form1.TXT_PRODUCT.value == 'PRODUCT1'){");			
			out.println(" document.Form1.PRODUCT_VALUE.value = '10';");
			out.println(" document.Form1.TXT_PRODUCT_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_PRODUCT.value == 'PRODUCT2'){");
			out.println(" document.Form1.PRODUCT_VALUE.value = '7';");
			out.println(" document.Form1.TXT_PRODUCT_VALUE.value = '7';");
			out.println("}else if(document.Form1.TXT_PRODUCT.value == 'PRODUCT3'){");
			out.println(" document.Form1.PRODUCT_VALUE.value = '5';");
			out.println(" document.Form1.TXT_PRODUCT_VALUE.value = '5';");
		  out.println("}else if(document.Form1.TXT_PRODUCT.value == 'PRODUCT4'){");
			out.println(" document.Form1.PRODUCT_VALUE.value = '2';");
			out.println(" document.Form1.TXT_PRODUCT_VALUE.value = '2';");
			out.println("}");
			out.println("}");
			
			out.println(" if(val == 'INDUSTRY'){");			
			out.println(" if(document.Form1.TXT_INDUSTRY.value == 'SECTOR1'){");			
			out.println(" document.Form1.INDUSTRY_VALUE.value = '10';");
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_INDUSTRY.value == 'SECTOR2'){");
			out.println(" document.Form1.INDUSTRY_VALUE.value = '8';");
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value = '8';");
			out.println("}else if(document.Form1.TXT_INDUSTRY.value == 'SECTOR3'){");
			out.println(" document.Form1.INDUSTRY_VALUE.value = '6';");
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value = '6';");
		  out.println("}else if(document.Form1.TXT_INDUSTRY.value == 'SECTOR4'){");
			out.println(" document.Form1.INDUSTRY_VALUE.value = '3';");
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_INDUSTRY.value == 'SECTOR5'){");
			out.println(" document.Form1.INDUSTRY_VALUE.value = '0';");
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value = '0';");
			out.println("}");			
			out.println("}");		
			
			out.println(" if(val == 'RESONS'){");			
			
			//out.println(" alert(document.Form1.TXT_RESONS.value);");
			
			out.println(" if(document.Form1.TXT_RESONS.value == 'RESON1'){");			
			out.println(" document.Form1.RESONS_VALUE.value = '10';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON2'){");
			out.println(" document.Form1.RESONS_VALUE.value = '8';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '8';");
			out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON3'){");
			out.println(" document.Form1.RESONS_VALUE.value = '6';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '6';");
		  out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON4'){");
			out.println(" document.Form1.RESONS_VALUE.value = '3';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON5'){");
			out.println(" document.Form1.RESONS_VALUE.value = '0';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '0';");
			out.println("}");			
			out.println("}");
			
			out.println(" if(val == 'OPERATIONS1'){");			
			out.println(" if(document.Form1.TXT_OPERATIONS1.value == 'Y'){");			
			out.println(" document.Form1.OPERATIONS_STATUS1.value = '7';");
			out.println(" document.Form1.TXT_OPERATION_VALUE1.value = '7';");
			out.println("}else if(document.Form1.TXT_OPERATIONS1.value == 'N'){");
			out.println(" document.Form1.OPERATIONS_STATUS1.value = '0';");
			out.println(" document.Form1.TXT_OPERATION_VALUE1.value = '0';");
			out.println("}");			
			out.println("}");
			
			out.println(" if(val == 'OPERATIONS2'){");			
			out.println(" if(document.Form1.TXT_OPERATIONS2.value == 'Y'){");			
			out.println(" document.Form1.OPERATIONS_STATUS2.value = '8';");
			out.println(" document.Form1.TXT_OPERATION_VALUE2.value = '8';");
			out.println("}else if(document.Form1.TXT_OPERATIONS2.value == 'N'){");
			out.println(" document.Form1.OPERATIONS_STATUS2.value = '2';");
			out.println(" document.Form1.TXT_OPERATION_VALUE2.value = '2';");
			out.println("}");			
			out.println("}");

			out.println(" if(val == 'OPERATIONS3'){");			
			out.println(" if(document.Form1.TXT_OPERATIONS3.value == 'Y'){");			
			out.println(" document.Form1.OPERATIONS_STATUS3.value = '7';");
			out.println(" document.Form1.TXT_OPERATION_VALUE3.value = '7';");
			out.println("}else if(document.Form1.TXT_OPERATIONS3.value == 'N'){");
			out.println(" document.Form1.OPERATIONS_STATUS3.value = '3';");
			out.println(" document.Form1.TXT_OPERATION_VALUE3.value = '3';");
			out.println("}");			
			out.println("}");

			out.println(" if(val == 'OPERATIONS4'){");			
			out.println(" if(document.Form1.TXT_OPERATIONS4.value == 'Y'){");			
			out.println(" document.Form1.OPERATIONS_STATUS4.value = '3';");
			out.println(" document.Form1.TXT_OPERATION_VALUE4.value = '3';");
			out.println("}else if(document.Form1.TXT_OPERATIONS4.value == 'N'){");
			out.println(" document.Form1.OPERATIONS_STATUS4.value = '0';");
			out.println(" document.Form1.TXT_OPERATION_VALUE4.value = '0';");
			out.println("}");			
			out.println("}");
			
			out.println(" if(val == 'BANKING1'){");			
			out.println(" if(document.Form1.TXT_BANKING_REC1.value == 'Y'){");			
			out.println(" document.Form1.BANKING_REC_VALUE1.value = '3';");
			out.println(" document.Form1.TXT_BANKING_VALUE1.value = '3';");
			out.println("}else if(document.Form1.TXT_BANKING_REC1.value == 'N'){");
			out.println(" document.Form1.BANKING_REC_VALUE1.value = '0';");
			out.println(" document.Form1.TXT_BANKING_VALUE1.value = '0';");
			out.println("}");			
			out.println("}");
			
			out.println(" if(val == 'BANKING2'){");			
			out.println(" if(document.Form1.TXT_BANKING_REC2.value == 'Y'){");			
			out.println(" document.Form1.BANKING_REC_VALUE2.value = '2';");
			out.println(" document.Form1.TXT_BANKING_VALUE2.value = '2';");
			out.println("}else if(document.Form1.TXT_BANKING_REC2.value == 'N'){");
			out.println(" document.Form1.BANKING_REC_VALUE2.value = '0';");
			out.println(" document.Form1.TXT_BANKING_VALUE2.value = '0';");
			out.println("}");			
			out.println("}");

			out.println(" if(val == 'BANKING3'){");			
			out.println(" if(document.Form1.TXT_BANKING_REC3.value == 'Y'){");			
			out.println(" document.Form1.BANKING_REC_VALUE3.value = '5';");
			out.println(" document.Form1.TXT_BANKING_VALUE3.value = '5';");
			out.println("}else if(document.Form1.TXT_BANKING_REC3.value == 'N'){");
			out.println(" document.Form1.BANKING_REC_VALUE3.value = '0';");
			out.println(" document.Form1.TXT_BANKING_VALUE3.value = '0';");
			out.println("}");			
			out.println("}");

			out.println(" if(val == 'BANKING4'){");			
			out.println(" if(document.Form1.TXT_BANKING_REC4.value == 'Y'){");			
			out.println(" document.Form1.BANKING_REC_VALUE4.value = '5';");
			out.println(" document.Form1.TXT_BANKING_VALUE4.value = '5';");
			out.println("}else if(document.Form1.TXT_BANKING_REC4.value == 'N'){");
			out.println(" document.Form1.BANKING_REC_VALUE4.value = '3';");
			out.println(" document.Form1.TXT_BANKING_VALUE4.value = '3';");
			out.println("}");			
			out.println("}");

			out.println(" if(val == 'REFERENCE'){");			
			out.println(" if(document.Form1.TXT_REFERENCE_STATUS.value == 'EXCE'){");			
			out.println(" document.Form1.REFERENCE_VALUE.value = '10';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_REFERENCE_STATUS.value == 'GOOD'){");
			out.println(" document.Form1.REFERENCE_VALUE.value = '8';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '8';");
			out.println("}else if(document.Form1.TXT_REFERENCE_STATUS.value == 'SATI'){");
			out.println(" document.Form1.REFERENCE_VALUE.value = '6';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '6';");
		  out.println("}else if(document.Form1.TXT_REFERENCE_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.REFERENCE_VALUE.value = '4';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '4';");
			out.println("}else if(document.Form1.TXT_REFERENCE_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.REFERENCE_VALUE.value = '3';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_REFERENCE_STATUS.value == 'POOR'){");
			out.println(" document.Form1.REFERENCE_VALUE.value = '0';");
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value = '0';");
			out.println("}");	
			
			out.println("}");
			
			
			out.println(" if(val == 'FINANCIAL'){");			
			out.println(" if(document.Form1.TXT_FINANCIAL.value == 'FIN_TYPE1'){");			
			out.println(" document.Form1.FINANCIAL_VALUE.value = '20';");
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value = '20';");
			out.println("}else if(document.Form1.TXT_FINANCIAL.value == 'FIN_TYPE2'){");
			out.println(" document.Form1.FINANCIAL_VALUE.value = '15';");
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value = '15';");
			out.println("}else if(document.Form1.TXT_FINANCIAL.value == 'FIN_TYPE3'){");
			out.println(" document.Form1.FINANCIAL_VALUE.value = '10';");
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value = '10';");
		  out.println("}else if(document.Form1.TXT_FINANCIAL.value == 'FIN_TYPE4'){");
			out.println(" document.Form1.FINANCIAL_VALUE.value = '5';");
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value = '5';");
			out.println("}else if(document.Form1.TXT_FINANCIAL.value == 'FIN_TYPE5'){");
			out.println(" document.Form1.FINANCIAL_VALUE.value = '0';");
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value = '0';");
			out.println("}");	
			out.println("}");
			out.println(" get_sum2();");
						
			out.println("}"); 
			
			out.println("function get_sum2(){");
			out.println(" m_total2=0;");
			out.println(" m_total2= parseFloat(unformat_noobject(document.Form1.TXT_PRODUCT_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_INDUSTRY_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_RESONS_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_OPERATION_VALUE1.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_OPERATION_VALUE2.value)) + parseFloat(unformat_noobject(document.Form1.TXT_OPERATION_VALUE3.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_OPERATION_VALUE4.value)) + parseFloat(unformat_noobject(document.Form1.TXT_BANKING_VALUE1.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_BANKING_VALUE2.value)) + parseFloat(unformat_noobject(document.Form1.TXT_BANKING_VALUE3.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_BANKING_VALUE4.value)) + parseFloat(unformat_noobject(document.Form1.TXT_REFERENCE_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_FINANCIAL_VALUE.value)); ");

			out.println(" document.Form1.TXT_TOT_VALUE2.value= m_total2;"); 
			out.println("}");
			
			
			
			out.println("function load_values3(val){");
			
			out.println(" if(val == 'DEBTORS'){");			
			out.println(" if(document.Form1.TXT_DEBTORS.value == 'DEBT1'){");			
			out.println(" document.Form1.DEBTORS_VALUE.value = '40';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '40';");
			out.println("}else if(document.Form1.TXT_DEBTORS.value == 'DEBT2'){");
			out.println(" document.Form1.DEBTORS_VALUE.value = '33';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '33';");
			out.println("}else if(document.Form1.TXT_DEBTORS.value == 'DEBT3'){");
			out.println(" document.Form1.DEBTORS_VALUE.value = '23';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '23';");
		  out.println("}else if(document.Form1.TXT_DEBTORS.value == 'DEBT4'){");
			out.println(" document.Form1.DEBTORS_VALUE.value = '16';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '16';");
      out.println("}else if(document.Form1.TXT_DEBTORS.value == 'DEBT5'){");
			out.println(" document.Form1.DEBTORS_VALUE.value = '10';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_DEBTORS.value == 'DEBT6'){");
			out.println(" document.Form1.DEBTORS_VALUE.value = '0';");
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value = '0';");
      out.println("}");
			out.println("}");
			
      out.println(" if(val == 'TOP_DEBTORS'){");			
			out.println(" if(document.Form1.TXT_TOP_DEBTORS.value == 'TOPDB1'){");			
			out.println(" document.Form1.TOP_DEBTORS_VALUE.value = '10';");
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value = '10';");
			out.println("}else if(document.Form1.TXT_TOP_DEBTORS.value == 'TOPDB2'){");
			out.println(" document.Form1.TOP_DEBTORS_VALUE.value = '7';");
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value = '7';");
			out.println("}else if(document.Form1.TXT_TOP_DEBTORS.value == 'TOPDB3'){");
			out.println(" document.Form1.TOP_DEBTORS_VALUE.value = '5';");
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value = '5';");
		  out.println("}else if(document.Form1.TXT_TOP_DEBTORS.value == 'TOPDB4'){");
			out.println(" document.Form1.TOP_DEBTORS_VALUE.value = '3';");
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value = '3';");
      out.println("}else if(document.Form1.TXT_TOP_DEBTORS.value == 'TOPDB5'){");
			out.println(" document.Form1.TOP_DEBTORS_VALUE.value = '2';");
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value = '2';");
      out.println("}");
			out.println("}");
			
      out.println(" if(val == 'DEBTOR_TRACK'){");			
			out.println(" if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'EXCE'){");			
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '40';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '40';");
			out.println("}else if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'GOOD'){");
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '33';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '33';");
			out.println("}else if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'SATI'){");
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '23';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '23';");
		  out.println("}else if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '16';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '16';");
      out.println("}else if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '11';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '11';");
			out.println("}else if(document.Form1.TXT_DEBTOR_TRACK_STATUS.value == 'POOR'){");
			out.println(" document.Form1.DEBTOR_TRACK_VALUE.value = '0';");
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value = '0';");
      out.println("}");
			out.println("}");
						
			out.println(" if(val == 'REF_OBTAINED'){");			
			out.println(" if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'EXCE'){");			
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '10';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '10'; ");
			out.println("}else if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'GOOD'){");
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '8';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '8';");
			out.println("}else if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'SATI'){");
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '6';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '6';");
		  out.println("}else if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'ACCE'){");
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '4';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '4';");
      out.println("}else if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'NOAD'){");
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '3';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '3';");
			out.println("}else if(document.Form1.TXT_REF_OBTAINED_STATUS.value == 'POOR'){");
			out.println(" document.Form1.REF_OBTAINED_VALUE.value = '0';");
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '0';");
      out.println("}");
			out.println("}");
			
			out.println(" get_sum3();");
			out.println("}");
			
       
			out.println("function get_sum3(){ ");
			out.println(" m_total3=0;");
			out.println(" m_total3= parseFloat(unformat_noobject(document.Form1.TXT_DEBTORS_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_TOP_DEBTORS_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_DEBTOR_TRACK_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_REF_OBTAINED_VALUE.value));");

			out.println(" document.Form1.TXT_TOT_VALUE3.value= m_total3;"); 
	    out.println("}");
			
			out.println("function disable_all_fields(){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=true;");
			out.println("}");
			out.println("document.Form1.BUT_HELP_MAIN1.disabled=false;");
			out.println("}");
			
			
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='1' topmargin='10' marginwidth='0' onLoad=\"load_approve_data(),load_lock()\">"); //,disable_all_fields()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_type' VALUE=\"\">");
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='1' valign='top'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Credit Score </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
		/*	out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 			
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>"); 			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Approve\");' onClick='' value=\"Approve\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Disapprove\");' onClick='load_screen_disapp()' value=\"Disapprove\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reject\");' onclick='window.close()' value=\"Reject\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  */
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Facility No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='25' size='25' disabled onblur=\"help_update_application()\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_application()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN1' value=\"Detail\" onClick=\"show_application_detail_drill(document.Form1.TXT_APPLICATION_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Code </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='10' size='10' disabled>"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN2' value=\"Detail\" onClick=\"show_client(document.Form1.TXT_CLIENT_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='10' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER'  class=div_input>Credit Evaluator </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_EVAL' maxlength='10' size='10' disabled onblur=\"help_update_user()\" value=\""+m_username+"\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_user()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINAL_COMMENTS'  class=div_input>Comments</DIV></td>"); 
			out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS' style='width:300;height:60' disabled></TEXTAREA></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>"); 
			
			
		
			
			
			out.println("<table class='table' cellpadding='0' cellspacing='0' border='1' bordercolor='gray' width='82%'> "); 
			out.println("<tr >");
			out.println("<td width='82%' >");

			
			out.println("<table class='table' cellpadding='1' cellspacing='0' border='0' width='96%'> "); 
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='40%' ><b>Name</td>");
			out.println("<td width='15%' ></td>"); 
			out.println("<td width='10%' ><b>Weight</td>"); 
			out.println("<td width='10%' >&nbsp;</td>"); 
			out.println("</tr>"); 
			
			///___________________________ 1		
			
			out.println("<tr >");
			out.println("<td width='5%' ><b>1</td>");
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_DIRECTORS' ><b>Directors/ Key Staff/ Owner </b> Experience Capability</td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=\"load_values('DIRECTORS')\" name=\"TXT_DIRECTORS_STATUS\" class=\"txt_input\"  style='width:200' >");
			out.println("<OPTION value=\"EXCE\">Excellent</option>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">No Adverse Comments</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"TXT_DIRECTORS_STATUS_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"25\">19 - 25</option>");
			out.println("<OPTION value=\"18\">16 - 18</option>");
			out.println("<OPTION value=\"15\">11 - 15</option>");
			out.println("<OPTION value=\"10\">8 - 10</option>");
			out.println("<OPTION value=\"6\">4 - 6</option>");
			out.println("<OPTION value=\"0\"> 0 </option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_DIRECTOR_VALUE' value ='0'  maxlength='3' size='3' onchange=\"get_sum1()\" >&nbsp</td>");			
			out.println("</tr>");
			///___________________________ 2    
			out.println("<tr >");
			out.println("<td width='5%' ><b>2</td>");
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_REPUTATION'><b>Reputation/ Integrity of Company </b> </td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=\"load_values('REPUTATION')\" name=\"TXT_REPUTATION_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"EXCE\">Excellent</option>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">No Adverse Comments</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"TXT_REPUT_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"20\">19 - 20</option>");
			out.println("<OPTION value=\"18\">16 - 18</option>");
			out.println("<OPTION value=\"15\">11 - 15</option>");
			out.println("<OPTION value=\"10\">8 - 10</option>");
			out.println("<OPTION value=\"6\">4 - 6</option>");
			out.println("<OPTION value=\"0\"> 0 </option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_REPUTATION_VALUE'  value ='0' maxlength='3' size='3' onchange=\"get_sum1()\">&nbsp</td>");			
			out.println("</tr>");
			///___________________________ 3			
			out.println("<tr >");
			out.println("<td width='5%' ><b>3</td>");
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_REF_BANK'><b>Reference Obtained</b> Bank</td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=\"load_values('REF_BANK')\" name=\"TXT_REF_BANK_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">Non Committed</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"TXT_BANK_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"20\">20</option>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\"> 0 </option>");
			out.println("</SELECT>");
			
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_REF_BANK_VALUE'  value ='0' maxlength='3' size='3' onchange=\"get_sum1()\" >&nbsp</td>");			
			out.println("</tr>");


      ///___________________________ 4			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_REF_TRADE'><b>Reference Obtained</b> Trade & Other Sources</td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=\"load_values('REF_TRADE')\" name=\"TXT_REF_TRADE_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">Non Committed</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"TXT_TRADE_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"20\">20</option>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\"> 0 </option>");
			out.println("</SELECT>");
			
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_REF_TRADE_VALUE'  value ='0' maxlength='3' size='3' onchange=\"get_sum1()\" >&nbsp</td>");			
			out.println("</tr>");
			
      ///___________________________ 5
				
			out.println("<tr >");
			out.println("<td width='5%' ><b>4</td>");
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_CAPACITY'><B>Capacity to effect recourse</td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=\"load_values('CAPACITY')\" name=\"TXT_CAPACITY_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"IMMD\">Immediately</option>");
			out.println("<OPTION value=\"RESN\">Within reasonable period of time</option>");
			out.println("<OPTION value=\"OVER\">Over the medium term</option>");
			out.println("<OPTION value=\"VERY\">Very strained</option>");
			out.println("<OPTION value=\"NIL\">Nil</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"CAPACITY_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"15\">12 - 15</option>");
			out.println("<OPTION value=\"12\">8 - 12</option>");
			out.println("<OPTION value=\"8\">5 - 8</option>");
			out.println("<OPTION value=\"5\">1- 5</option>");
			out.println("<OPTION value=\"0\"> 0 </option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_CAPACITY_VALUE'  value ='0' maxlength='3' size='3' onchange=\"get_sum1()\" ></td>");			
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='40%' >&nbsp</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_TOT_VALUE1' maxlength='3' size='3' ></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("</td>");
			out.println("</tr>"); 
			out.println("</table>"); 
		  out.println("</br>");   
			
			///The product/ Operation 
			out.println("<table class='table' cellpadding='1' cellspacing='0' border='1' bordercolor='gray' width='82%'> "); 
			out.println("<tr >");
			out.println("<td width='*%' >");

			out.println("<table class='table' cellpadding='1' cellspacing='0' border='0' width='80%'> "); 
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' ><b>The product/ Operation</td>");
			out.println("<td width='15%' ></td>"); 
			out.println("<td width='10%' ><b>Weight</td>"); 
			out.println("</tr>"); 
			
			///___________________________ 1
			out.println("<tr >");
			out.println("<td width='5%' ><b>1</td>");
			out.println("<td width='30%' ><b>Product</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values2('PRODUCT') name=\"TXT_PRODUCT\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"PRODUCT1\">1.1 Factorable products with little or no disputable situations</option>");
			out.println("<OPTION value=\"PRODUCT2\">1.2 Factorable products with possibly some disputable situations</option>");
			out.println("<OPTION value=\"PRODUCT3\">1.3 Factorable products with potentially annoying disputes</option>");
			out.println("<OPTION value=\"PRODUCT4\">1.4 Factorable products with potentially serious disputes </option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			
					
			
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"PRODUCT_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">10</option>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"2\">2</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_PRODUCT_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\">&nbsp</td>");			
			out.println("</tr>");
			
			///___________________________ 2    
			out.println("<tr >");
			out.println("<td width='5%' ><b>2</td>");
			out.println("<td width='30%' ><b>Industry/ Market Sector Condition</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values2('INDUSTRY') name=\"TXT_INDUSTRY\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"SECTOR1\">2.1 Stable and or established sector with good future growth prospects</option>");
			out.println("<OPTION value=\"SECTOR2\">2.2 Fairly stable sector with reasonable growth prospects</option>");
			out.println("<OPTION value=\"SECTOR3\">2.3 Young and/ or volatile sector and/ or with considarable competitiveness  </option>");
			out.println("<OPTION value=\"SECTOR4\">2.4 Uncertain business conditions/ speculative sector</option>");
			out.println("<OPTION value=\"SECTOR5\">2.5 Dying or dead business sector </option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"INDUSTRY_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">9 - 10</option>");
			out.println("<OPTION value=\"8\">7 - 8</option>");
			out.println("<OPTION value=\"6\">4 - 6</option>");
			out.println("<OPTION value=\"3\">1 - 3</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_INDUSTRY_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			///___________________________ 3			
			out.println("<tr >");
			out.println("<td width='5%' ><b>3</td>");
			out.println("<td width='30%' ><b>Resons for factoring</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values2('RESONS') name=\"TXT_RESONS\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"RESON1\">3.1 Genuine working capital requirements</option>");
			out.println("<OPTION value=\"RESON2\">3.2 Help in running a sales ledger (No funding) </option>");
			out.println("<OPTION value=\"RESON3\">3.3 For purpose of expansion  </option>");
			out.println("<OPTION value=\"RESON4\">3.4 Ability to pay suppliers promptly</option>");
			out.println("<OPTION value=\"RESON5\">3.5 To get out of pressing financial commitments</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"RESONS_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">8 - 10</option>");
			out.println("<OPTION value=\"7\">5 - 7</option>");
			out.println("<OPTION value=\"6\">3 - 6</option>");
			out.println("<OPTION value=\"2\">1 - 2</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_RESONS_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			
			
			
			
			
			
			
			

      ///___________________________ 4			
			out.println("<tr >");
			out.println("<td width='5%' ><b>4</td>");
			out.println("<td width='30%' ><b>Operations</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >4.1 Credit Notes value <5% of sales per month");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('OPERATIONS1') name=\"TXT_OPERATIONS1\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"OPERATIONS_STATUS1\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_OPERATION_VALUE1' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
      
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >4.2 Proof of receipt of goods/ services available?");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('OPERATIONS2') name=\"TXT_OPERATIONS2\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"OPERATIONS_STATUS2\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"8\">8</option>");
			out.println("<OPTION value=\"2\">2 - 0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_OPERATION_VALUE2' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\">&nbsp</td>");			
			out.println("</tr>");
			
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >4.3 Collection of debts withing trade norms");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('OPERATIONS3') name=\"TXT_OPERATIONS3\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"OPERATIONS_STATUS3\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"3\">3 - 0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_OPERATION_VALUE3' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >4.4 Reciprocal trading does not take place?");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('OPERATIONS4') name=\"TXT_OPERATIONS4\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"OPERATIONS_STATUS4\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_OPERATION_VALUE4' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\">&nbsp</td>");			
			out.println("</tr>");
			
			
		
   
		  ///___________________________ 5
				
			out.println("<tr >");
			out.println("<td width='5%' ><b>5</td>");
			out.println("<td width='30%' ><b>Banking Records</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
		
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >5.1 Return Cheques < 15% in value (Deposits)");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('BANKING1') name=\"TXT_BANKING_REC1\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"BANKING_REC_VALUE1\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_BANKING_VALUE1' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >5.2 Return Cheques < 15% in numbers (Deposits)");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('BANKING2') name=\"TXT_BANKING_REC2\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"BANKING_REC_VALUE2\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"2\">2</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_BANKING_VALUE2' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >5.3 Return Cheques on withdrawals <5 numbers for 3 months?");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('BANKING3') name=\"TXT_BANKING_REC3\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"BANKING_REC_VALUE3\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_BANKING_VALUE3' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >5.4 Reported sales justified by banking volumes/ other proven means");
	    out.println("</td>"); 
			out.println("<td width='15%' >"); 
			out.println("<select onchange=load_values2('BANKING4') name=\"TXT_BANKING_REC4\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"Y\">Yes</option>");
			out.println("<OPTION value=\"N\">No</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("<td width='10%' >");
			out.println("<select name=\"BANKING_REC_VALUE4\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"3\">3 - 0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_BANKING_VALUE4' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			
			
			
			
			
			///_________________________________ 6
			
			out.println("<tr >");
			out.println("<td width='5%' ><b>6</td>");
			out.println("<td width='30%' ><input class='txt_input' type='hidden' name='TXT_REFERENCE'><b>References obtained from main suppliers</b></td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=load_values2('REFERENCE') name=\"TXT_REFERENCE_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"EXCE\">Excellent</option>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">Non Committed</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"REFERENCE_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">10</option>");
			out.println("<OPTION value=\"8\">8</option>");
			out.println("<OPTION value=\"6\">6</option>");
			out.println("<OPTION value=\"4\">4</option>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\">0 </option>");
			out.println("</SELECT>");
			
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_REFERENCE_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");
			
			//___________________________________ 7
			out.println("<tr >");
			out.println("<td width='5%' ><b>7</td>");
			out.println("<td width='30%' ><b>Financials</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values2('FINANCIAL') name=\"TXT_FINANCIAL\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"FIN_TYPE1\">7.1 Strong sustained performance with strong equity base(Audited by acceptable firm of Auditors and available within a resonable period of time from year end)</option>");
			out.println("<OPTION value=\"FIN_TYPE2\">7.2 Satisfactory financial performance with satisfactory equity base.(Audited by acceptable firm of Auditors and available within a resonable period of time from year end) </option>");
			out.println("<OPTION value=\"FIN_TYPE3\">7.3 Unaudited financial statments satisfactory financial condition </option>");
			out.println("<OPTION value=\"FIN_TYPE4\">7.4 Overall acceptable financial statements with some unsatisfactory features.(Audited/ Unaudited.)</option>");
			out.println("<OPTION value=\"FIN_TYPE5\">7.5 Financial statments not available. Unsatisfactory financial position</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"FINANCIAL_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"20\">15 - 20</option>");
			out.println("<OPTION value=\"15\">10 - 15</option>");
			out.println("<OPTION value=\"10\">5 - 10</option>");
			out.println("<OPTION value=\"5\">1 - 5</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_FINANCIAL_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum2()\" >&nbsp</td>");			
			out.println("</tr>");

			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >&nbsp</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_TOT_VALUE2' maxlength='3' size='3' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
		  out.println("</td>"); 
			out.println("</tr>");		
			out.println("</table>");
			
			out.println("<br>");
			
			///Debtors 
			out.println("<table class='table' cellpadding='1' cellspacing='0' border='1' bordercolor='gray' width='82%'> "); 
			out.println("<tr >");
			out.println("<td width='*%' >");
			out.println("<table class='table' cellpadding='1' cellspacing='0' border='0' width='80%'> "); 
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' ><b>Debtors</td>");
			out.println("<td width='15%' ></td>"); 
			out.println("<td width='10%' ><b>Weight</td>"); 
			out.println("</tr>"); 
			
			///___________________________ 1
			out.println("<tr >");
			out.println("<td width='5%' ><b>1</td>");
			out.println("<td width='30%' ><b>The Debtors Base</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values3('DEBTORS') name=\"TXT_DEBTORS\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"DEBT1\">1.1 Well spread debtor base with no more than 10% of out standing to any one debtor</option>");
			out.println("<OPTION value=\"DEBT2\">1.2 Spread debtor base with exposure/s in excess of 15% to strong debtors</option>");
			out.println("<OPTION value=\"DEBT3\">1.3 Debtor base not well spred but over 60% of exposure to strong debtors/ statisfactory track record with client or known to MLL   </option>");
			out.println("<OPTION value=\"DEBT4\">1.4 Single debtor or 2 to 5 number but with satisfactory track record with client/ other clients or known to MLL</option>");
			out.println("<OPTION value=\"DEBT5\">1.5 Single debtor or 2 to 5 in number with no adverse remarks </option>");
			out.println("<OPTION value=\"DEBT6\">1.6 Poor Debtor Base</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			
					
			
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"DEBTORS_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"40\">35 - 40</option>");
			out.println("<OPTION value=\"33\">27 - 33</option>");
			out.println("<OPTION value=\"23\">18 - 23</option>");
			out.println("<OPTION value=\"16\">13 - 16</option>");
			out.println("<OPTION value=\"10\">5 - 10</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_DEBTORS_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum3()\" >&nbsp</td>");			
			out.println("</tr>");
			
			///_____________________________ 2
			out.println("<tr >");
			out.println("<td width='5%' ><b>2</td>");
			out.println("<td width='30%' ><b>'Top 10' Debtors</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("</tr>");			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >");
			
			out.println("<select onchange=load_values3('TOP_DEBTORS') name=\"TXT_TOP_DEBTORS\" class=\"txt_input\"  style='width:320'>");
			out.println("<OPTION value=\"TOPDB1\">1.1 Dealing with client for over 5 years</option>");
			out.println("<OPTION value=\"TOPDB2\">1.2 Dealing with client for between 3 - 5 years</option>");
			out.println("<OPTION value=\"TOPDB3\">1.3 Dealing with client for between 2 - 3 years</option>");
			out.println("<OPTION value=\"TOPDB4\">1.4 Dealing with client for between 1 - 2 years</option>");
			out.println("<OPTION value=\"TOPDB5\">1.5 Dealing with client for less than 1 year</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
					
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"TOP_DEBTORS_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">10</option>");
			out.println("<OPTION value=\"7\">7</option>");
			out.println("<OPTION value=\"5\">5</option>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"2\">2</option>");
			out.println("</SELECT>");
  		out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_TOP_DEBTORS_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum3()\" >&nbsp</td>");			
			out.println("</tr>");
						
      ///_________________________________ 3
											
			out.println("<tr >");
			out.println("<td width='5%' ><b>3</td>");
			out.println("<td width='30%' ><input class='txt_input' type='hidden' name='TXT_DEBTOR_TRACK'><b>Debtors Track record with client</b></td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=load_values3('DEBTOR_TRACK') name=\"TXT_DEBTOR_TRACK_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"EXCE\">Excellent</option>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">Non adverse</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"DEBTOR_TRACK_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"40\">35 - 40</option>");
			out.println("<OPTION value=\"33\">27 - 33</option>");
			out.println("<OPTION value=\"23\">18 - 23</option>");
			out.println("<OPTION value=\"16\">13 - 16</option>");
			out.println("<OPTION value=\"11\">8 - 11</option>");
			out.println("<OPTION value=\"0\">0 </option>");
			out.println("</SELECT>");
			
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_DEBTOR_TRACK_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum3()\" >&nbsp</td>");			
			out.println("</tr>");
			
      ///_________________________________ 4
			
			out.println("<tr >");
			out.println("<td width='5%' ><b>4</td>");
			out.println("<td width='30%' ><input class='txt_input' type='hidden' name='TXT_REF_OBTAINED'><b>Reference obtained (Debtors)</b></td>");
			out.println("<td width='15%' >");
			out.println("<select onchange=load_values3('REF_OBTAINED') name=\"TXT_REF_OBTAINED_STATUS\" class=\"txt_input\"  style='width:200'>");
			out.println("<OPTION value=\"EXCE\">Excellent</option>");
			out.println("<OPTION value=\"GOOD\">Good</option>");
			out.println("<OPTION value=\"SATI\">Satisfactory</option>");
			out.println("<OPTION value=\"ACCE\">Acceptable</option>");
			out.println("<OPTION value=\"NOAD\">Non adverse</option>");
			out.println("<OPTION value=\"POOR\">Poor</option>");
			out.println("</SELECT>");
			out.println("</td>"); 
			out.println("<td width='10%' >");
			out.println("<select name=\"REF_OBTAINED_VALUE\" class=\"txt_input\"  style='width:100'>");
			out.println("<OPTION value=\"10\">10</option>");
			out.println("<OPTION value=\"8\">8</option>");
			out.println("<OPTION value=\"6\">6</option>");
			out.println("<OPTION value=\"4\">4</option>");
			out.println("<OPTION value=\"3\">3</option>");
			out.println("<OPTION value=\"0\">0</option>");
			out.println("</SELECT>");
			
			out.println("</td>"); 
			out.println("<td width='10%'><input class='txt_input' type='text' name='TXT_REF_OBTAINED_VALUE' maxlength='3' size='3' value ='0' onchange=\"get_sum3()\" >&nbsp</td>");			
			out.println("</tr>");			
			
			out.println("<tr >");
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='30%' >&nbsp</td>");
			out.println("<td width='15%' >&nbsp</td>"); 
			out.println("<td width='10%' >&nbsp</td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_TOT_VALUE3' maxlength='3' size='3' ></td>");
			out.println("</tr>"); 
			
					
			out.println("</table>");
			out.println("</td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 	
			/*
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View\");' onClick='load_view_score();load_screen_status(\"VIEW\")' value=\"View\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Approve\");' onClick='' value=\"Approve\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Disapprove\");' onClick='load_screen_disapp()' value=\"Disapprove\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reject\");' onclick='window.close()' value=\"Reject\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='close_window()' value=\"Close\"></td>");   
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>"); 
			*/
			//out.println("<DIV id='score_details'  class=div_input></DIV>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			out.flush();
			out.close();
			conn.close();
			this.destroy();
			}
			   	
			
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


