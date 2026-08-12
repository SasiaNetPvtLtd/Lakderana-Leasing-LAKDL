// DEVELOP BY : CHANDANA FOR OFSCL Leasing    DATE:29-01-2008

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;   
import java.sql.*; 
import java.util.*; 
 
 
public class LAKDL_FA_CR_display_credit_score_evaluation extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1,rs2;
	public String m_chksql;
	java.text.NumberFormat nf,nf1; 
	
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
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			ServletOutputStream out = res.getOutputStream(); 
				
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
					
			
			out.println("function makeRequest1(obj1,obj2) {");//
			//out.println("alert(obj1.value);");
			out.println(" document.Form1.hid_type.value='M1';");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation1&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("alert(m_url);");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
		  
			out.println("function makeRequest2(obj1,obj2) {");//obj1,
			out.println("document.Form1.hid_type.value='M2';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation2&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest3(obj1,obj2) {");//obj1,
			out.println("document.Form1.hid_type.value='M3';");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_credit_score_evaluation3&data_val1=\"+obj1.value+\"&data_val2=\"+obj2.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && (document.Form1.SCREEN_NAME.value==\"NEW\" ||document.Form1.SCREEN_NAME.value!=\"NEW\") && document.Form1.hid_type.value=='M1' ){");
			//out.println("				alert('Record already exist123');");
			out.println(" assign_data1(data_vec);");
			out.println("			}");			
			out.println("	else if(data_vec.length>0 && (document.Form1.SCREEN_NAME.value==\"NEW\" ||document.Form1.SCREEN_NAME.value!=\"NEW\") && document.Form1.hid_type.value=='M2' ){");
			//out.println("				alert('Record already exist12345');");
			out.println(" assign_data2(data_vec);");
			out.println("			}");			
			out.println("	else if(data_vec.length>0 && (document.Form1.SCREEN_NAME.value==\"NEW\" ||document.Form1.SCREEN_NAME.value!=\"NEW\") && document.Form1.hid_type.value=='M3' ){");
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
			out.println("    total_value = parseFloat(unformat_noobject(document.Form1.TXT_DIRECTOR_VALUE.value))+parseFloat(unformat_noobject(document.Form1.TXT_REPUTATION_VALUE.value))+parseFloat(unformat_noobject(document.Form1.TXT_REF_BANK_VALUE.value))+parseFloat(unformat_noobject(document.Form1.TXT_REF_TRADE_VALUE.value))+parseFloat(unformat_noobject(document.Form1.TXT_CAPACITY_VALUE.value))");
			out.println("    document.Form1.TXT_TOT_VALUE1.value=total_value; ");
			//out.println("    makeRequest2(document.Form1.TXT_CLIENT_NO);");//document.Form1.TXT_APPLICATION_NO,
			out.println("    makeRequest2(document.Form1.TXT_CLIENT_NO,document.Form1.TXT_APPLICATION_NO);");
			out.println("   }");			
			
			out.println("   function assign_data2(data_vec){");
			out.println("    document.Form1.TXT_PRODUCT.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_PRODUCT_VALUE.value=data_vec[13]; ");
			out.println("    document.Form1.TXT_INDUSTRY.value=data_vec[1]; ");
			out.println("    document.Form1.TXT_INDUSTRY_VALUE.value=data_vec[14]; ");
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
			out.println("    document.Form1.TXT_REFERENCE_STATUS.value=data_vec[11]; ");
			out.println("    document.Form1.TXT_REFERENCE_VALUE.value=data_vec[24]; ");
			out.println("    document.Form1.TXT_FINANCIAL.value=data_vec[12]; ");
			out.println("    document.Form1.TXT_FINANCIAL_VALUE.value=data_vec[25]; "); 
			out.println("tot_product = parseFloat(unformat_noobject(data_vec[13]))+parseFloat(unformat_noobject(data_vec[24]))+parseFloat(unformat_noobject(data_vec[15]))+parseFloat(unformat_noobject(data_vec[16]))+parseFloat(unformat_noobject(data_vec[17]))+parseFloat(unformat_noobject(data_vec[18]))+"+
			            "parseFloat(unformat_noobject(data_vec[19]))+parseFloat(unformat_noobject(data_vec[20]))+parseFloat(unformat_noobject(data_vec[21]))+parseFloat(unformat_noobject(data_vec[22]))+parseFloat(unformat_noobject(data_vec[23]))+parseFloat(unformat_noobject(data_vec[14]))+parseFloat(unformat_noobject(data_vec[25]))");
			out.println("    document.Form1.TXT_TOT_VALUE2.value=tot_product; ");
			//out.println("    makeRequest3(document.Form1.TXT_CLIENT_NO);");//document.Form1.TXT_APPLICATION_NO,
			out.println("    makeRequest3(document.Form1.TXT_CLIENT_NO,document.Form1.TXT_APPLICATION_NO);");
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
			out.println("    document.Form1.TXT_TOT_VALUE3.value=parseFloat(unformat_noobject(data_vec[4]))+parseFloat(unformat_noobject(data_vec[5]))+parseFloat(unformat_noobject(data_vec[6]))+parseFloat(unformat_noobject(data_vec[7])); ");			
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
			/*
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}");*/
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
			out.println("		if(document.Form1.hid_help_type.value==\"11\"){"); 
			out.println("		help_update_value_assign_11();"); 
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
			out.println("if(document.Form1.hid_help_type.value==\"10\" || document.Form1.hid_help_type.value==\"11\"){");
			//out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("document.Form1.TXT_CLIENT_NO.value='';");
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("document.Form1.TXT_CLIENT_NAME.value='';"); 
			//out.println("document.Form1.TXT_CLIENT_NO.value='';");
			//out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			//out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			//out.println("document.Form1.TXT_COMMENTS.value='';"); 
			//out.println("score_details.innerHTML=\"\";");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"100\"){");
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("document.Form1.TXT_CREDIT_EVAL.focus();");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			//out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';");
			//out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
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
			
			
			//----------------sandun on 24-06-2009-----------------------------------
			
			out.println("function help_update_facility() {"); 
			
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			out.println("}"); 
			
			out.println("function help_update_value_assign_11() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[6];"); 
			out.println("    makeRequest1(document.Form1.TXT_CLIENT_NO,document.Form1.TXT_APPLICATION_NO);");
			out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			//out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[5];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value=oBj.valout[7];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[6];"); 
			out.println("    makeRequest1(document.Form1.TXT_CLIENT_NO,document.Form1.TXT_APPLICATION_NO);");
			out.println("}"); 
			out.println("}"); 
			//---------------------end-----------------------------------------------------

			out.println("function help_update_application() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			//out.println("    m_sql = \"m_help_DIV_TXT_APPLICATION_CR_SOCRE_sql\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql\";");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");			
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			out.println("}"); 
			
			out.println("function help_update_value_assign_10() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];");
			out.println("document.Form1.TXT_COMMENTS.value=oBj.valout[5];");
			
			out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			//out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			//out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[4];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value=oBj.valout[7];"); 
			//out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[5];"); 
			out.println("}");
			//out.println("    makeRequest1(document.Form1.TXT_CLIENT_NO);");//document.Form1.TXT_APPLICATION_NO,
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
			out.println(" if(document.Form1.TXT_DIRECTOR_VALUE.value ==''|| isNaN(document.Form1.TXT_DIRECTOR_VALUE.value)){");  // Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_DIRECTOR_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_REPUTATION_VALUE.value ==''|| isNaN(document.Form1.TXT_REPUTATION_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_REPUTATION_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_REF_BANK_VALUE.value ==''|| isNaN(document.Form1.TXT_REF_BANK_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_REF_BANK_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_REF_TRADE_VALUE.value ==''|| isNaN(document.Form1.TXT_REF_TRADE_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_REF_TRADE_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_CAPACITY_VALUE.value ==''|| isNaN(document.Form1.TXT_CAPACITY_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_CAPACITY_VALUE.value=0;");
			out.println(" }");
		
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
			out.println(" document.Form1.RESONS_VALUE.value = '7';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '7';");
			out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON3'){");
			out.println(" document.Form1.RESONS_VALUE.value = '6';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '6';");
		  out.println("}else if(document.Form1.TXT_RESONS.value == 'RESON4'){");
			out.println(" document.Form1.RESONS_VALUE.value = '2';");
			out.println(" document.Form1.TXT_RESONS_VALUE.value = '2';");
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
			
			out.println("function get_sum2(){");  // Modified by Chatura Jayawardena
			out.println(" if(document.Form1.TXT_PRODUCT_VALUE.value ==''|| isNaN(document.Form1.TXT_PRODUCT_VALUE.value)){");  // Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_PRODUCT_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_INDUSTRY_VALUE.value ==''|| isNaN(document.Form1.TXT_INDUSTRY_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_INDUSTRY_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_RESONS_VALUE.value ==''|| isNaN(document.Form1.TXT_RESONS_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_RESONS_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_OPERATION_VALUE1.value ==''|| isNaN(document.Form1.TXT_OPERATION_VALUE1.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_OPERATION_VALUE1.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_OPERATION_VALUE2.value ==''|| isNaN(document.Form1.TXT_OPERATION_VALUE2.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_OPERATION_VALUE2.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_OPERATION_VALUE3.value ==''|| isNaN(document.Form1.TXT_OPERATION_VALUE3.value)){");  // Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_OPERATION_VALUE3.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_OPERATION_VALUE4.value ==''|| isNaN(document.Form1.TXT_OPERATION_VALUE4.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_OPERATION_VALUE4.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_BANKING_VALUE1.value ==''|| isNaN(document.Form1.TXT_BANKING_VALUE1.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_BANKING_VALUE1.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_BANKING_VALUE2.value ==''|| isNaN(document.Form1.TXT_BANKING_VALUE2.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_BANKING_VALUE2.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_BANKING_VALUE3.value ==''|| isNaN(document.Form1.TXT_BANKING_VALUE3.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_BANKING_VALUE3.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_BANKING_VALUE4.value ==''|| isNaN(document.Form1.TXT_BANKING_VALUE4.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_BANKING_VALUE4.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_REFERENCE_VALUE.value ==''|| isNaN(document.Form1.TXT_REFERENCE_VALUE.value)){");  // Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_REFERENCE_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_FINANCIAL_VALUE.value ==''|| isNaN(document.Form1.TXT_FINANCIAL_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_FINANCIAL_VALUE.value=0;");
			out.println(" }");
			
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
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value = '10';");
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
			
       
			out.println("function get_sum3(){ ");  // Modified by Chatura Jayawardena
			out.println(" if(document.Form1.TXT_DEBTORS_VALUE.value ==''|| isNaN(document.Form1.TXT_DEBTORS_VALUE.value)){");  // Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_DEBTORS_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_TOP_DEBTORS_VALUE.value ==''|| isNaN(document.Form1.TXT_TOP_DEBTORS_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_TOP_DEBTORS_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_DEBTOR_TRACK_VALUE.value ==''|| isNaN(document.Form1.TXT_DEBTOR_TRACK_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_DEBTOR_TRACK_VALUE.value=0;");
			out.println(" }");
			out.println(" if(document.Form1.TXT_REF_OBTAINED_VALUE.value ==''|| isNaN(document.Form1.TXT_REF_OBTAINED_VALUE.value)){");// Added by Chatura Jayawardena.
			out.println(" document.Form1.TXT_REF_OBTAINED_VALUE.value=0;");
			out.println(" }");
		
			out.println(" m_total3=0;");
			out.println(" m_total3= parseFloat(unformat_noobject(document.Form1.TXT_DEBTORS_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_TOP_DEBTORS_VALUE.value)) +");
			out.println(" parseFloat(unformat_noobject(document.Form1.TXT_DEBTOR_TRACK_VALUE.value)) + parseFloat(unformat_noobject(document.Form1.TXT_REF_OBTAINED_VALUE.value));");

			out.println(" document.Form1.TXT_TOT_VALUE3.value= m_total3;"); 
	    out.println("}");
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='1' topmargin='10' marginwidth='0' onLoad=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='USER_SCREEN_NAME' VALUE=\"CREDIT_SCORE_EVALUATION\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Credit Score Evaluation </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View\");' onClick='load_view_score();load_screen_status(\"VIEW\")' value=\"View\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='25' size='25' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_facility()\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN1' value=\"Detail\" onClick=\"show_application_detail_drill(document.Form1.TXT_APPLICATION_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='10' size='10' onblur=\"help_update_application()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_application()\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN2' value=\"Detail\" onClick=\"show_client(document.Form1.TXT_CLIENT_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='10' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER'  class=div_input>Credit Evaluator *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_EVAL' maxlength='10' size='10' onblur=\"\" value=\""+m_username+"\">"); //help_update_user()
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_user()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			/*
			
			//==========================================
			// commented by SANDUN on 21-10-2008
			//==========================================
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SCORE_MODEL_CODE'  class=div_input>Score Model Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCORE_MODEL_CODE' maxlength='10' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_model()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_SCORE_AP'  class=div_input>Total Score for Application</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_SCORE_APP' maxlength='3' size='3' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_SCORE'  class=div_input>Total Score for Model</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_SCORE' maxlength='3' size='3' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINAL_COMMENTS'  class=div_input>Comments</DIV></td>"); 
			out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS' style='width:300;height:60'></TEXTAREA></td>"); 
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
			out.println("<OPTION value=\"DEBT3\">1.3 Debtor base not well spred but over 60% of exposure to strong debtors/ statisfactory track record with client or known to OFSCL   </option>");
			out.println("<OPTION value=\"DEBT4\">1.4 Single debtor or 2 to 5 number but with satisfactory track record with client/ other clients or known to OFSCL</option>");
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
			    
					
			else if(m_chksql.trim().equals("report")){
					
			//String m_facility_no = req.getParameter("Facility_No");		
			String m_client_no = req.getParameter("Client_No");			
			
			String m_client_name="";
			String m_com_status="";
			String m_com_val1="";
			String m_com_val2="";
			String m_com_val3="";
			String m_com_val4="";
			String m_com_val5="";
			String m_com_val6="";
			
			String m_rep_status="";
			String m_rep_val1="";
			String m_rep_val2="";
			String m_rep_val3="";
			String m_rep_val4="";
			String m_rep_val5="";
			String m_rep_val6="";
						
			String m_refb_status="";
			String m_refb_val1="";
			String m_refb_val2="";
			String m_refb_val3="";
			String m_refb_val4="";
			String m_refb_val5="";
			
			String m_reft_status="";
			String m_reft_val1="";
			String m_reft_val2="";
			String m_reft_val3="";
			String m_reft_val4="";
			String m_reft_val5="";
			
			String m_cap_status="";
			String m_cap_val1="";
			String m_cap_val2="";
			String m_cap_val3="";
			String m_cap_val4="";
			String m_cap_val5="";
			String m_tot_weight ="";
			
			
			       
     rs = stmt.executeQuery (" SELECT  "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
			                       " OWNER_STATUS, "+
															" REPUTATION_STATUS, "+
															" REF_BANK_STATUS, "+
															" REF_TRADE_STATUS, "+
															" CAPACITY_STATUS, "+
															" OWNER_VALUE, "+
															" REPUTATION_VALUE, "+
															" REF_BANK_VALUE, "+
															" REF_TRADE_VALUE, "+
															" CAPACITY_VALUE, "+
															" (OWNER_VALUE+REPUTATION_VALUE+REF_BANK_VALUE+REF_TRADE_VALUE+CAPACITY_VALUE) "+
															" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_COM_DETAIL "+
															" WHERE CLIENT_CODE = '"+m_client_no+"' "); //" APPLICATION_NO ='"+m_facility_no+"'
															
 
 boolean more = rs.next();
			
			if(more){
			m_client_name= rs.getString(1);
			m_com_status = rs.getString(2);
			m_rep_status = rs.getString(3);
			m_refb_status = rs.getString(4);
			m_reft_status= rs.getString(5);
			m_cap_status = rs.getString(6);
			m_tot_weight = rs.getString(12);
			
			if(m_com_status.equals("EXCE")){
			m_com_val1 = rs.getString(7);
			}else if(m_com_status.equals("GOOD")){
			m_com_val2 = rs.getString(7);			
			}else if(m_com_status.equals("SATI")){
			m_com_val3 = rs.getString(7);
			}else if(m_com_status.equals("ACCE")){
			m_com_val4 = rs.getString(7);
			}else if(m_com_status.equals("NOAD")){
			m_com_val5 = rs.getString(7);
			}else if(m_com_status.equals("POOR")){
			m_com_val6 = rs.getString(7);
			}
			
			if(m_rep_status.equals("EXCE")){
			m_rep_val1 = rs.getString(8);
			}else if(m_rep_status.equals("GOOD")){
			m_rep_val2 = rs.getString(8);			
			}else if(m_rep_status.equals("SATI")){
			m_rep_val3 = rs.getString(8);
			}else if(m_rep_status.equals("ACCE")){
			m_rep_val4 = rs.getString(8);
			}else if(m_rep_status.equals("NOAD")){
			m_rep_val5 = rs.getString(8);
			}else if(m_rep_status.equals("POOR")){
			m_rep_val6 = rs.getString(8);
			}
			
			if(m_refb_status.equals("GOOD")){
			m_refb_val1 = rs.getString(9);
			}else if(m_refb_status.equals("SATI")){
			m_refb_val2 = rs.getString(9);			
			}else if(m_refb_status.equals("ACCE")){
			m_refb_val3 = rs.getString(9);
			}else if(m_refb_status.equals("NOAD")){
			m_refb_val4 = rs.getString(9);
			}else if(m_refb_status.equals("POOR")){
			m_refb_val5 = rs.getString(9);
			}
			
			
			if(m_reft_status.equals("GOOD")){
			m_reft_val1 = rs.getString(10);
			}else if(m_reft_status.equals("SATI")){
			m_reft_val2 = rs.getString(10);			
			}else if(m_reft_status.equals("ACCE")){
			m_reft_val3 = rs.getString(10);
			}else if(m_reft_status.equals("NOAD")){
			m_reft_val4 = rs.getString(10);
			}else if(m_reft_status.equals("POOR")){
			m_reft_val5 = rs.getString(10);
			}
			
			if(m_cap_status.equals("IMMD")){
			m_cap_val1 = rs.getString(11);
			}else if(m_cap_status.equals("RESN")){
			m_cap_val2 = rs.getString(11);			
			}else if(m_cap_status.equals("OVER")){
			m_cap_val3 = rs.getString(11);
			}else if(m_cap_status.equals("VERY")){
			m_cap_val4 = rs.getString(11);
			}else if(m_cap_status.equals("NIL")){
			m_cap_val5 = rs.getString(11);
			}			
			}	
			
			
			rs1 = stmt.executeQuery (" SELECT A.CLIENT_CODE, "+//1
												      " A.APPLICATION_NO,  "+//2
												      " A.PRODUCT, "+//3
												      " A.SECTOR, "+//4
												      " A.RESON, "+//5
												      " A.OPERATION_TYPE1, "+//6
												      " A.OPERATION_TYPE2, "+//7
												      " A.OPERATION_TYPE3, "+//8
												      " A.OPERATION_TYPE4, "+//9
												      " A.BANK_REC1, "+//10
												      " A.BANK_REC2, "+//11
												      " A.BANK_REC3,"+//12
												      " A.BANK_REC4, "+//13
												      " A.SUPPL_STATUS, "+ //14
												      " A.FINANCE_TYPE, "+//15
												      " A.PRODUCT_VALUE, "+//16
												      " A.SECTOR_VALUE, "+//17
												      " A.RESON_VALUE, "+//18
												      " A.OPERATION_TYPE1_VALUE, "+//19
												      " A.OPERATION_TYPE2_VALUE, "+//20
												      " A.OPERATION_TYPE3_VALUE,"+//21
												      " A.OPERATION_TYPE4_VALUE, "+//22
												      " A.BANK_REC1_VALUE, "+//23
												      " A.BANK_REC2_VALUE,"+//24
												      " A.BANK_REC3_VALUE, "+ //25
												      " A.BANK_REC4_VALUE, "+//26
												      " A.SUPPL_STATUS_VALUE, "+//27
												      " A.FINANCE_TYPE_VALUE, "+//28
															" (A.PRODUCT_VALUE+A.SECTOR_VALUE+A.RESON_VALUE+A.OPERATION_TYPE1_VALUE+A.OPERATION_TYPE2_VALUE+ "+
												      " A.OPERATION_TYPE3_VALUE+A.OPERATION_TYPE4_VALUE+A.BANK_REC1_VALUE+A.BANK_REC2_VALUE+A.BANK_REC3_VALUE+ "+
 												      " A.BANK_REC4_VALUE+A.SUPPL_STATUS_VALUE+A.FINANCE_TYPE_VALUE) TOTAL   "+//29
												      " FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_PRODUCT_DET A "+
															" WHERE A.CLIENT_CODE = '"+m_client_no+"' ");
															//" AND A.APPLICATION_NO = '"+m_facility_no+"' ");
			String m_prod_value   = "";
			String m_prod_val1   = "";
			String m_prod_val2   = "";
			String m_prod_val3   = "";
			String m_prod_val4   = "";
			String m_sector_val1 = "";
			String m_sector_val2 = "";
			String m_sector_val3 = "";
			String m_sector_val4 = "";
			String m_sector_val5 = "";			
			String m_reson_val1 = "";
			String m_reson_val2 = "";
			String m_reson_val3 = "";
			String m_reson_val4 = "";
			String m_reson_val5 = "";
			String m_operation_type1_value = "";
			String m_operation_type2_value = "";
			String m_operation_type3_value = "";
			String m_operation_type4_value = "";
			String m_bank_rec1_value = "";
			String m_bank_rec2_value = "";
			String m_bank_rec3_value = "";
			String m_bank_rec4_value = "";
			String m_suppl_status_value = "";
			String m_finance_type_value = "";
			String m_product ="";
			String m_sector ="";
			String m_reson = "";
			String m_sup_status ="";
			String m_sup_val1 = "";
			String m_sup_val2 = "";
			String m_sup_val3 = "";
			String m_sup_val4 = "";
			String m_sup_val5 = "";
			String m_sup_val6 = "";
			String m_totl_product = "";
			String m_finance_val1 = "";
			String m_finance_val2 = "";
			String m_finance_val3 = "";
			String m_finance_val4 = "";
			String m_finance_val5 = "";
			String m_finance_type ="";
			if(rs1.next()){
			
			 
			 m_operation_type1_value = rs1.getString(19);
			 m_operation_type2_value = rs1.getString(20);
			 m_operation_type3_value = rs1.getString(21);
			 m_operation_type4_value = rs1.getString(22);
			 m_bank_rec1_value = rs1.getString(23);
			 m_bank_rec2_value = rs1.getString(24);
			 m_bank_rec3_value = rs1.getString(25);
			 m_bank_rec4_value = rs1.getString(26);
			 m_suppl_status_value = rs1.getString(27);
			 m_finance_type_value = rs1.getString(28);
			 m_totl_product = rs1.getString(29);
			 m_product = rs1.getString(3);
			 m_sector  =rs1.getString(4);
			 m_reson = rs1.getString(5);
			 m_sup_status = rs1.getString(14);
			 m_finance_type = rs1.getString(15);
				
				if(m_product.equals("PRODUCT1")){
				m_prod_val1 = rs1.getString(16);
				}else if(m_product.equals("PRODUCT2")){
				m_prod_val2 = rs1.getString(16);
				}else if(m_product.equals("PRODUCT3")){
				m_prod_val3 = rs1.getString(16);
				}else if(m_product.equals("PRODUCT4")){
				m_prod_val4 = rs1.getString(16);
				}
				
				if(m_sector.equals("SECTOR1")){
				m_sector_val1 = rs1.getString(17);
				}else if(m_sector.equals("SECTOR2")){
				m_sector_val2 = rs1.getString(17);
				}else if(m_sector.equals("SECTOR3")){
				m_sector_val3 = rs1.getString(17);
				}else if(m_sector.equals("SECTOR4")){
				m_sector_val4 = rs1.getString(17);
				}else if(m_sector.equals("SECTOR5")){
				m_sector_val5 = rs1.getString(17);
				}
				
				if(m_reson.equals("RESON1")){
				m_reson_val1 = rs1.getString(18);
				}else if(m_reson.equals("RESON2")){
				m_reson_val2 = rs1.getString(18);
				}else if(m_reson.equals("RESON3")){
				m_reson_val3 = rs1.getString(18);
				}else if(m_reson.equals("RESON4")){
				m_reson_val4 = rs1.getString(18);
				}else if(m_reson.equals("RESON5")){
				m_reson_val5 = rs1.getString(18);
				}
				
			  if(m_sup_status.equals("EXCE")){
				m_sup_val1 = rs1.getString(27);
				}else if(m_sup_status.equals("GOOD")){
				m_sup_val2 = rs1.getString(27);			
				}else if(m_sup_status.equals("SATI")){
				m_sup_val3 = rs1.getString(27);
				}else if(m_sup_status.equals("ACCE")){
				m_sup_val4 = rs1.getString(27);
				}else if(m_sup_status.equals("NOAD")){
				m_sup_val5 = rs1.getString(27);
				}else if(m_sup_status.equals("POOR")){
				m_sup_val6 = rs1.getString(27);
			}
			
			if(m_finance_type.equals("FIN_TYPE1")){
				m_finance_val1 = rs1.getString(28);
				}else if(m_finance_type.equals("FIN_TYPE2")){
				m_finance_val2 = rs1.getString(28);
				}else if(m_finance_type.equals("FIN_TYPE3")){
				m_finance_val3 = rs1.getString(28);
				}else if(m_finance_type.equals("FIN_TYPE4")){
				m_finance_val4 = rs1.getString(28);
				}else if(m_finance_type.equals("FIN_TYPE5")){
				m_finance_val5 = rs1.getString(28);
				}
			
			}
															
		
			rs2 = stmt.executeQuery ("  SELECT A.CLIENT_CODE, "+///1
												       "  A.APPLICATION_NO, "+//2
												       "  A.DBT_BASE_TYPE, "+//3
												       "  A.TOP_DBT_TYPE, "+//4
												       "  A.TRACK_REC_TYPE, "+//5
												       "  A.REFERENCE_TYPE, "+//6
												       "  A.DBT_VALUE, "+//7
												       "  A.TOP_DBT_VALUE, "+//8
												       "  A.TRACK_REC_VALUE, "+//9
												       "  A.REFERENCE_VALUE,  "+ //10 
															 " (A.DBT_VALUE+A.TOP_DBT_VALUE+A.TRACK_REC_VALUE+A.REFERENCE_VALUE) TOTAL "+//11
												       "  FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_DBT_DETAIL A "+
															 " WHERE A.CLIENT_CODE = '"+m_client_no+"' ");
															// " AND A.APPLICATION_NO = '"+m_facility_no+"' ");
				
				String m_dbt_base_type ="";
				String m_top_dbt_type ="";
				String m_track_rec_type ="";
				String m_reference_type ="";
				String m_db_total ="";
				String m_track_rec_value = "";
				String m_reference_value = "";
				String m_reference_value6 ="";
				String m_reference_value5 ="";
				String m_reference_value4 ="";
				String m_reference_value3 ="";
				String m_reference_value2 ="";
				String m_reference_value1 ="";
				String m_track_rec_value1 = "";
				String m_track_rec_value2 = "";
				String m_track_rec_value3 = "";
				String m_track_rec_value4 = "";
				String m_track_rec_value5 = "";
				String m_track_rec_value6 = "";
				String m_dbt_value = "";
				String m_dbt_value1 = "";
				String m_dbt_value2 = "";
				String m_dbt_value3 = "";
				String m_dbt_value4 = "";
				String m_dbt_value5 = "";
				String m_dbt_value6 = "";
				String m_top_dbt_value1 ="";
				String m_top_dbt_value2 ="";
				String m_top_dbt_value3 ="";
				String m_top_dbt_value4 ="";
				String m_top_dbt_value5 ="";
			
				
				if(rs2.next()){
				m_dbt_base_type  = rs2.getString(3);
				m_top_dbt_type   = rs2.getString(4);
				m_track_rec_type = rs2.getString(5);
				m_reference_type = rs2.getString(6);
				m_dbt_value = rs2.getString(7);
				m_db_total = rs2.getString(11);
				 
				if(m_track_rec_type.equals("EXCE")){
				m_track_rec_value1= rs2.getString(9);
				}else if(m_rep_status.equals("GOOD")){
				m_track_rec_value2= rs2.getString(9);			
				}else if(m_rep_status.equals("SATI")){
				m_track_rec_value3= rs2.getString(9);
				}else if(m_rep_status.equals("ACCE")){
			  m_track_rec_value4= rs2.getString(9);
				}else if(m_rep_status.equals("NOAD")){
				m_track_rec_value5= rs2.getString(9);
				}else if(m_rep_status.equals("POOR")){
				m_track_rec_value5= rs2.getString(9);
				}
				
				if(m_reference_type.equals("EXCE")){
				m_reference_value1= rs2.getString(10);
				}else if(m_rep_status.equals("GOOD")){
				m_reference_value2 = rs2.getString(10);			
				}else if(m_rep_status.equals("SATI")){
				m_reference_value3 = rs2.getString(10);
				}else if(m_rep_status.equals("ACCE")){
				m_reference_value4 = rs2.getString(10);
				}else if(m_rep_status.equals("NOAD")){
				m_reference_value5  = rs2.getString(10);
				}else if(m_rep_status.equals("POOR")){
				m_reference_value6 = rs2.getString(10);
			}
			
			if(m_dbt_base_type.equals("DEBT1")){
			m_dbt_value1 = rs2.getString(7);
			}else if(m_dbt_base_type.equals("DEBT2")){
			m_dbt_value2 = rs2.getString(7);
			}else if(m_dbt_base_type.equals("DEBT3")){
			m_dbt_value3 = rs2.getString(7);
			}else if(m_dbt_base_type.equals("DEBT4")){
			m_dbt_value4 = rs2.getString(7);
			}else if(m_dbt_base_type.equals("DEBT5")){
			m_dbt_value5 = rs2.getString(7);
			}else if(m_dbt_base_type.equals("DEBT6")){
			m_dbt_value6 = rs2.getString(7);
			}
			
			if(m_top_dbt_type.equals("TOPDB1")){
			m_top_dbt_value1 = rs2.getString(8);
			}else if(m_top_dbt_type.equals("TOPDB2")){
			m_top_dbt_value2 = rs2.getString(8);
			}else if(m_top_dbt_type.equals("TOPDB3")){
			m_top_dbt_value3 = rs2.getString(8);
			}else if(m_top_dbt_type.equals("TOPDB4")){
			m_top_dbt_value4 = rs2.getString(8);
			}else if(m_top_dbt_type.equals("TOPDB5")){
			m_top_dbt_value5 = rs2.getString(8);
			}			
			}
			
			//out.println("----"+m_top_dbt_value2+"--------");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 	
			
			out.println("function print_data(){");
			out.println("	 m_table.innerHTML=\"\" ");
			out.println("	 window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}");		
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='1' topmargin='10' marginwidth='0' onload='add_button()'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 		
								
			//out.println("Facility No - "+m_facility_no);		
			//out.println("Client No   - "+m_client_no);	
			 out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");	
			out.println("<br>");
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='90%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='75%' class='rep-body1' >&nbsp<b>"+m_client_name+"</b></td>");
			out.println("<td width='10%' class='rep-body1' >&nbsp<b>Weight</td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table align='center' cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width='5%' valign='top' class='rep-body1'><p><br>&nbsp<b>1</td>");
			out.println("<td width='50%' class='rep-body1' ><p><b>Directors/ Key Staff/ Owner </b><br> Experience <br><br>Capability</td>");
			out.println("<td width='25%' class='rep-body1' ><p><br>Excellent <br>Good <br>Satisfactory <br>Acceptable<br>No Adverse Comments<br>Poor</p></td>"); 
			out.println("<td width='10%' align='center' class='rep-body1'><p><br>19 - 25<br>16 - 18<br>11 - 15<br>8 - 10<br>4 - 6<br>0</td>"); 
			out.println("<td width='10%' align='right' class='rep-body1' ><p>"+m_com_val1+"<br>"+m_com_val2+"<br>"+m_com_val3+"<br>"+m_com_val4+"<br>"+m_com_val5+"<br>"+m_com_val6+"</td>");
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' valign='top' class='rep-body1'><p><br>&nbsp<b>2</td>");
			out.println("<td width='50%' valign='top' class='rep-body1'><p><br><b>Reputation/ Integrity of Company </b></td>");
			out.println("<td width='25%' class='rep-body1' ><p><br>Excellent <br>Good <br>Satisfactory <br>Acceptable<br>No Adverse Comments<br>Poor<br></p></td>"); 
			out.println("<td width='10%' align='center' class='rep-body1'><p><br>19 - 20<br>16 - 18<br>11 - 15<br>8 - 10<br>4 - 6<br>0<br></td>"); 
			out.println("<td width='10%' align='right' class='rep-body1'><p>"+m_rep_val1+"<br>"+m_rep_val2+"<br>"+m_rep_val3+"<br>"+m_rep_val4+"<br>"+m_rep_val5+"<br>"+m_rep_val6+"</td>");
			out.println("</tr>"); 
			
			
			out.println("<tr >");
			out.println("<td width='5%' valign='top' class='rep-body1'><p><br>&nbsp<b>3</td>");
			out.println("<td width='50%' valign='top' class='rep-body1'><p><br><b>Reference Obtained</b><br> Bank <br><br><br><br><br> Trade & Other Sources</td>");
			out.println("<td width='25%' class='rep-body1' ><p><br>Good <br>Satisfactory <br>Acceptable<br>Non Committed<br>Poor<hr color='black'>Good <br>Satisfactory <br>Acceptable<br>Non Committed<br>Poor</p></td>"); 
			out.println("<td width='10%' align='center' class='rep-body1'><p><br>15 - 20 <br>11 - 14 <br>7 - 10<br>4 - 6<br>0<hr color='black'>20 <br>7<br>5<br>3<br>0<br></p></td>"); 
			out.println("<td width='10%' align='right' class='rep-body1'><p><br>"+m_refb_val1+"<br>"+m_refb_val2+"<br>"+m_refb_val3+"<br>"+m_refb_val4+"<br>"+m_refb_val5+"<br><hr color='black'>"+m_reft_val1+"<br>"+m_reft_val2+"<br>"+m_reft_val3+"<br>"+m_reft_val4+"<br>"+m_reft_val5+"<br><br></p></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' valign='top' class='rep-body1'><p><br>&nbsp<b>4</td>");
			out.println("<td width='50%' valign='top' class='rep-body1'><p><br><b>Capacity to effect recourse</b></td>");
			out.println("<td width='25%' class='rep-body1' ><p><br>Immediately <br>Within reasonable period of time <br>Over the medium term<br>Very strained<br>Nil</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p><br>12 - 15<br>8 - 12<br>5 - 8<br>1 - 5<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p><br>"+m_cap_val1+"<br>"+m_cap_val2+"<br>"+m_cap_val3+"<br>"+m_cap_val4+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='85%' >&nbsp</td>");
			out.println("<td width='10%' class='rep-body1' align='right'>"+m_tot_weight+"</td>"); 
			out.println("</tr>"); 
			out.println("</table>");	
			//-----------------------------Product/Operation
			out.println("<br>");
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='*%' class='rep-body1' ><b>The Product/ Operation</td>");			
			out.println("</tr>"); 
			out.println("</table>");	
			out.println("<table align='center' cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>1</b><br>1.1<br>1.2<br>1.3<br>1.4</td>");
			out.println("<td width='50%' class='rep-body1' ><p><br><b><u>Product</u></b><br> Factorable products with little or no disputable situations <br>Factorable products with possibly some disputable situations<br>Factorable products with potentially annoying disputes<br>Factorable products with potentially serious disputes</td>");
			out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>10<br>7<br>5<br>2</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;<br>"+m_prod_val1+"<br>"+m_prod_val2+"<br>"+m_prod_val3+"<br>"+m_prod_val4+"</td>");
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>2</b><br>2.1<br>2.2<br>2.3<br>2.4<br>2.5</td>");
			out.println("<td width='50%' class='rep-body1' ><p><br><b><u>Industry/Market Sector Condition</u></b><br> Stable and or established sector with good future growth prospects <br>Young and/ or volatile sector and/ or with considarable competitiveness<br>Uncertain business conditions/ speculative sector<br>Dying or dead business sector</td>");
			out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>9 - 10<br>7 - 8<br>4 - 6<br>1 - 3<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;<br>"+m_sector_val1+"<br>"+m_sector_val2+"<br>"+m_sector_val3+"<br>"+m_sector_val4+"<br>"+m_sector_val5+"</td>");
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>3</b><br>3.1<br>3.2<br>3.3<br>3.4<br>3.5</td>");
			out.println("<td width='50%' class='rep-body1'  ><p><br><b><u>Resons for factoring</u></b><br> Genuine working capital requirements <br>Help in running a sales ledger (No funding) <br>For purpose of expansion<br>Ability to pay suppliers promptly<br>To get out of pressing financial commitments</td>");
			out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>8 - 10<br>5 - 7<br>3 - 6<br>1 - 2<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;<br>"+m_reson_val1+"<br>"+m_reson_val2+"<br>"+m_reson_val3+"<br>"+m_reson_val4+"<br>"+m_reson_val5+"</td>");
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>4</b><br>4.1<br>4.2<br>4.3<br>4.4</td>");
			out.println("<td width='50%' class='rep-body1' ><p><br><b><u>Operations</u></b><br> Credit Notes value <5% of sales per month <br>Proof of receipt of goods/ services available?<br>Collection of debts withing trade norms<br>Reciprocal trading does not take place?</td>");
			out.println("<td width='10%' class='rep-body1' align='left'><p>&nbsp;<br><br>YES/NO<br>YES/NO<br>YES/NO<br>YES/NO</td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>7/0<br>8/2 - 0<br>7/3 - 0<br>3/0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;<br><br>"+m_operation_type1_value+"<br>"+m_operation_type2_value+"<br>"+m_operation_type3_value+"<br>"+m_operation_type4_value+"</td>");
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>5</b><br>5.1<br>5.2<br>5.3<br>5.4</td>");
			out.println("<td width='50%' class='rep-body1' ><p><br><b><u>Banking Records</u></b><br> Return Cheques < 15% in value (Deposits)<br>Return Cheques < 15% in number (Deposits)<br>Return Cheques on withdrawals <5 numbers for 3 months?<br>Reported sales justified by banking volumes/ other proven means</td>");
			out.println("<td width='10%' class='rep-body1' align='left'><p>&nbsp;<br><br>YES/NO<br>YES/NO<br>YES/NO<br>YES/NO</td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>3/0<br>2/0<br>5/0<br>5/3 - 0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;<br><br>"+m_bank_rec1_value+"<br>"+m_bank_rec2_value+"<br>"+m_bank_rec3_value+"<br>"+m_bank_rec4_value+"</td>");
			out.println("</tr>");
						
			out.println("<tr >");
			out.println("<td width='5%'  class='rep-body1' valign='top' align=center><p>&nbsp<br><b>6</b></td>");
			out.println("<td width='50%' class='rep-body1' valign='top' ><p><br><b>References obtained from main suppliers</b></td>");
			out.println("<td width='25%' class='rep-body1' ><p><br>Excellent <br>Good <br>Satisfactory <br>Acceptable<br>No Adverse Comments<br>Poor<br></p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br>10<br>8<br>6<br>4<br>3<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>&nbsp;"+m_sup_val1+"<br>"+m_sup_val2+"<br>"+m_sup_val3+"<br>"+m_sup_val4+"<br>"+m_sup_val5+"<br>"+m_sup_val6+"</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<p style='page-break-after:always'></p>");
			out.println("<table align='center' cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align=center><p>&nbsp<br><b>7</b><br>7.1<br><br>7.2<br><br><br>7.3<br>7.4<br><br>7.5</td>");
			out.println("<td width='50%' class='rep-body1'  ><p><br><b><u>Financials</u></b><br>Strong sustained performance with strong equity base(Audited by acceptable firm of Auditors and available within a resonable period of time from year end)<br>Satisfactory financial performance with satisfactory equity base.(Audited by acceptable firm of Auditors and available within a resonable period of time from year end) <br>Unaudited financial statments satisfactory financial condition<br>Overall acceptable financial statements with some unsatisfactory features.(Audited/ Unaudited.)<br>Financial statments not available. Unsatisfactory financial position</td>");
			out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center' ><p>&nbsp;<br><br>15 - 20<br><br>10 - 15<br><br><br>5 - 10<br>1 - 5<br><br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right' ><p>&nbsp;"+m_finance_val1+"<br><br>"+m_finance_val2+"<br><br><br>"+m_finance_val3+"<br>"+m_finance_val4+"<br>"+m_finance_val5+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='85%' >&nbsp</td>");
			out.println("<td width='10%' class='rep-body1' align='right'>"+m_totl_product+"</td>"); 
			out.println("</tr>"); 
			out.println("</table>");		
			
			//---------------------------Debtors
			
			out.println("<br>"); 
			
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='*%' class='rep-body1' ><b>Debtors</td>");			
			out.println("</tr>"); 
			out.println("</table>");	
			out.println("<table align='center' cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align='center'><p><br><br><b>1</b><br>1.1<br><br>1.2<br>1.3<br><br>1.4<br><br>1.5<br>1.6</td>");
			out.println("<td width='50%' class='rep-body1' ><p>&nbsp;<br><br><b><u>The Debtor Base</u></b><br>Well spread debtor base with no more than 10% of out standing to any one debtor<br>Spread debtor base with exposure/s in excess of 15% to strong debtors<br>Debtor base not well spred but over 60% of exposure to strong debtors/ statisfactory track record with client or known to OFSCL <br>Single debtor or 2 to 5 number but with satisfactory track record with client/ other clients or known to OFSCL<br>Single debtor or 2 to 5 in number with no adverse remarks<br>Poor Debtor Base</td>");
			out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>&nbsp;<br><br>35 - 40<br><br>27 - 33<br><br>18 - 23<br><br>13 - 16<br><br>5 - 10<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>"+m_dbt_value1+"<br><br>"+m_dbt_value2+"<br><br>"+m_dbt_value3+"<br><br>"+m_dbt_value4+"<br>"+m_dbt_value5+"<br>"+m_dbt_value6+"</td>");
			out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align='center'><p><br>&nbsp<b>2</b><br>2.1<br>2.2<br>2.3<br>2.4<br>2.5</td>");
			out.println("<td width='50%' class='rep-body1' valign='top'><p><br><b><u>\"Top 10\" Debtors</u></b><br>Dealing with client for over 5 years<br>Dealing with client for between 3 - 5 years<br>Dealing with client for between 2 - 3 years<br>Dealing with client for between 1 - 2 years<br>Dealing with client for less than 1 year</td>");
		  out.println("<td width='25%' class='rep-body1' ><p>&nbsp;</p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p><br><br>10<br>7<br>5<br>3<br>2</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p><br>"+m_top_dbt_value1+"<br>"+m_top_dbt_value2+"<br>"+m_top_dbt_value3+"<br>"+m_top_dbt_value3+"<br>"+m_top_dbt_value5+"</td>");
			out.println("</tr>"); 	
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align='center'><p><br>&nbsp<b>3</b></td>");
			out.println("<td width='50%' class='rep-body1' valign='top'><p><br><b>Debtors Track record with client</b></td>");
		  out.println("<td width='25%' class='rep-body1' ><p><br>Excellent <br>Good <br>Satisfactory <br>Acceptable<br>No Adverse Comments<br>Poor<br></p></td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p><br>35 - 40<br>27 - 33<br>18 - 23<br>13 - 16<br>8 - 11<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>"+m_track_rec_value1+"<br>"+m_track_rec_value2+"<br>"+m_track_rec_value3+"<br>"+m_track_rec_value4+"<br>"+m_top_dbt_value5+"<br>"+m_track_rec_value6+"</td>");
			out.println("</tr>"); 	
			
			out.println("<tr >");
			out.println("<td width='5%' class='rep-body1' valign='top' align='center'><p><br>&nbsp<b>4</b></td>");
			out.println("<td width='50%' class='rep-body1' valign='top'><p><br><b>Reference obtained(Debtors)</b></td>");
		  out.println("<td width='25%' class='rep-body1' ><p><br>Excellent <br>Good <br>Satisfactory <br>Acceptable<br>No Adverse Comments<br>Poor<br></p></td>");  
			out.println("<td width='10%' class='rep-body1' align='center'><p><br>10<br>8<br>6<br>4<br>3<br>0</td>"); 
			out.println("<td width='10%' class='rep-body1' align='right'><p>"+m_reference_value1+"<br>"+m_reference_value2+"<br>"+m_reference_value3+"<br>"+m_reference_value4+"<br>"+m_reference_value5+"<br>"+m_reference_value6+"</td>");
			out.println("</tr>"); 
			
			
			out.println("</table>");
			out.println("<table  cellpadding='1' cellspacing='0' border='1' bordercolor='black' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp</td>");
			out.println("<td width='85%' >&nbsp</td>");
			out.println("<td width='10%' class='rep-body1' align='right'>"+m_db_total+"</td>"); 
			out.println("</tr>"); 
			out.println("</table>");	
			double m_m_tot_weight   = Double.parseDouble(m_tot_weight);
			double m_m_totl_product = Double.parseDouble(m_totl_product);
			double m_m_db_total     = Double.parseDouble(m_db_total);
			double m_m_tot = m_m_tot_weight+m_m_totl_product+m_m_db_total;
			double m_m_avg = (m_m_tot_weight+m_m_totl_product+m_m_db_total)/3;
			String m_grade="";
			
			if(m_m_avg>=80 && m_m_avg <=100){  //Added By SJ on 25-11-2008
					m_grade = "1 (Excellent)";
					}else if(m_m_avg>=70 && m_m_avg <80){
					m_grade = "2 (Very Good)";
					}else if(m_m_avg>=60 && m_m_avg <70){
					m_grade = "3 (Good)";
					}else if(m_m_avg>=50 && m_m_avg <60){
					m_grade = "4 (Satifactory)";
					}else if(m_m_avg>=45 && m_m_avg <50){
					m_grade = "5 (Acceptable)";
					}else if(m_m_avg>=40 && m_m_avg <45){
					m_grade = "6 (Adequate)";
					}else if(m_m_avg>=35 && m_m_avg <40){
					m_grade = "7 (Watch)";
					}else if(m_m_avg <35){
					m_grade = "8 (Poor)";
					}			
			
			
			out.println("<br>");
			
			out.println("</table>");
			out.println("<table  cellpadding='1' cellspacing='0' border='0' width='100%' class='table'>"); 
			out.println("<tr>"); // align='left' 
			out.println("<td width='5%' >&nbsp;</td>");
			out.println("<td width='50%' class='rep-body1' align='left'><p><br>Signature :....................................<br>Score Entry<br>Executive Credit<br><br><br><br><br><br><br>Signature :....................................<br>Score Evaluation<br>Assistance Manager Portfolio"+
			            "<br><br><br><br><br><br><br>Signature :....................................<br>Score Evaluation<br>Manager Factoring<br><br><br><br><br><br><br>Signature :....................................<br>Credit Score Approval<br>Assistance General Manager Factoring</td>");
			out.println("<td width='25%' class='rep-body1' align='left'><p>The Client <br>The Product Operation<br>The Debtor Base<br><br>Total<br><br><br><br><br>%(Total*100/300)<br><br>Grade</td>"); 
			out.println("<td width='10%' class='rep-body1' align='center'><p>"+m_tot_weight+"<br>"+m_totl_product+"<br>"+m_db_total+"<br>-----------<br>"+nf.format(m_m_tot)+"<br>======<br><br><br><br>"+nf1.format(m_m_avg)+"<br><br>"+m_grade+"</td>");
			out.println("<td width='10%' >&nbsp</td>");
			out.println("</tr>"); 
			out.println("</table>");	
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			
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


