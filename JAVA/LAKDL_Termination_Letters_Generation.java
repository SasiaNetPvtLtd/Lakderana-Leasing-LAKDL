//Add by Indika
//on 22/08/08



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_Termination_Letters_Generation extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt10;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11,rs13,rs14,rs15;
	public String m_chksql;
	ServletOutputStream out = null;
	String m_return_status="N";
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {

			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();

			conn = m_sn_methods.met_user_validate(req); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url = m_sn_methods.html_client_url;
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  	String m_username =  m_sn_methods.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;
			int sel_stage=0;
		
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			stmt6 = conn.createStatement();
			stmt7 = conn.createStatement();
			stmt8 = conn.createStatement();
			stmt9 = conn.createStatement();
			stmt10 = conn.createStatement();
			
			//m_pre_stage=req.getParameter("pre");
			//m_app_stage=req.getParameter("appro");
			//m_pre_stage1=req.getParameter("qry");
			String m_CLOSE = req.getParameter("CLOSE");
			
			String turmi_name="";
			String turmi_date="";				
			String client_name ="";
			String m_fin_code,m_invoice,m_orient_name="";
			String m_Letter_date="";
			
			rs = stmt.executeQuery(	" SELECT "+
											" UPPER(NVL(COMPANY_NAME,' ')) "+
											" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			boolean more = rs.next();		
			if(more) {
			 m_orient_name=rs.getString(1);
			}
			
			rs3 = stmt3.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'Month')||' '||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
											
			boolean more3 = rs3.next();
			if(more3){
				m_Letter_date=rs3.getString(1);
			}
			
 			if(m_chksql.trim().equals("main_page")){

      		out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
					
//=============================================== Add By Indika on 25/08/080 =================================================================================

			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			
			/*out.println("if(document.Form1.TXT_FINANCENO.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");
			out.println("alert('No records')");
			out.println("document.Form1.TXT_FINANCENO.value=\"\"");
			out.println("}");
			*/
			
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_status.value='New'");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=termination_details&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"&acti_termi=\"+document.Form1.TXT_DIVISION_CODE.value+\"\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=termination_details&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"&acti_termi=\"+document.Form1.TXT_DIVISION_CODE.value+\"\";");
      //out.println("alert(m_url)");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCENO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCENO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("if(document.Form1.TXT_MKT_OFFICER.value==\"\"){  "); 
			//out.println("DIV_TXT_MKT_OFFICER.style.color='red';");
			//out.println("return false;"); 
			//out.println("}");
			//out.println("if(document.Form1.TXT_SUP_NAME.value==\"\"){  "); 
			//out.println("DIV_TXT_SUP_NAME.style.color='red';");
			//out.println("return false;"); 
			//out.println("}");
			out.println("return true;"); 
			out.println("}"); 


			out.println("function count_docs(){ ");
			out.println("count=0;");
			out.println("for(j=0;j<document.Form1.hid_count.value;j++){");
			out.println("m_chk=\"chk_app_\"+d+\"_\"+j;");
			out.println("m_chk_req=\"chk_not_req_\"+d+\"_\"+j;");
			out.println("if(document.Form1.elements[m_chk_req].checked==true || document.Form1.elements[m_chk].checked==true){");
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
			
			
						
			out.println("function check_select(){ "); 
			out.println("if(request_details.innerHTML==\"\"){");
			out.println("alert('Please Select Finance no');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(!count_docs()){"); 
			out.println("alert('Please Select Document');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
      out.println("}"); 

			
			

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"Termination Process - Finance No Help\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			//out.println("help_box.innerHTML=\"Collection - Thanking Letter-Guarantor \"+m_val;"); 
			out.println("help_box.innerHTML=\"Finance - Termination - Letter Generation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Finance - Termination - Letter Generation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){");
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PRO_FORMA_INVOICE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_DOCUMENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DOCUMENT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_REASON.disabled=true;"); 
			out.println("document.Form1.TXT_REQUESTED_USER.disabled=true;"); 
			out.println("document.Form1.TXT_REQUESTED_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_APPRO_USER.disabled=true;"); 
			out.println("document.Form1.TXT_APPRO_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_COUNT.disabled=true;"); 
			out.println("document.Form1.TXT_IN_COUNT.disabled=true;"); 
			out.println("document.Form1.TXT_OUT_COUNT.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"Request\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.hid_save.value=\"Return\";"); 
			out.println("document.Form1.hid_status.value=\"Return\";");  
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(document.Form1.hid_help_type.value);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
		   out.println("if(document.Form1.hid_help_type.value=='100'){"); 
			out.println("		help_value_assign_100(oBj);"); 
			out.println("}");
				
				
			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	else{");
			out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
			out.println("	}");
			out.println("	}	"); //
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
			
			/*
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_PRO_CR_help_select\"+"); 
			//out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			//out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MAS_Help_Servlet?class_in="+m_fschema_name+"AF_MAS_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"200\"){"); 
			out.println("		help_update_value_assign_200();"); 
	  	out.println("		}"); 	
			out.println("		if(document.Form1.hid_help_type.value==\"201\"){"); 
			out.println("		help_update_value_assign_201();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
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
			*/
			
			
			
			/*out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){");
			out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("}else{ ");
			out.println(" document.Form1.TXT_FINANCENO.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");*/
			
			out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){");
			out.println(" document.Form1.TXT_FINANCENO.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"200\"){ ");
			out.println(" document.Form1.TXT_MKT_OFFICER.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"201\"){ ");
			out.println(" document.Form1.TXT_SUP_NAME.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");
			
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_NAME_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			
			

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_REQ_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_FINANCE_NO_termination\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCENO.value+\"@\"+document.Form1.TXT_DIVISION_CODE.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			//out.println("    HelpBox('1','10','0', m_criteria,'m_help_FINANCE_NO_termination','2');"); 
			out.println("}");
				
			out.println("function help_value_assign_100() {"); 
			out.println("   document.Form1.TXT_FINANCENO.value=oBj.valout[2];");
			out.println(" 	makeRequest(); ");
			out.println("}");

      //===============Marketing Officer Help Functions
			
			out.println("function help_value_assign_200() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("   document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 	
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("   document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println("}");
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"200\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MKT_OFFICER.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_200() {"); 
			out.println("    document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
			out.println("}");
			
			//===============End Marketing Officer Help Functions
      //===============Supervisor Help Functions
			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"201\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUP_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_201() {"); 
			out.println("    document.Form1.TXT_SUP_NAME.value=oBj.valout[2];");
			out.println("    document.Form1.hid_sup_disignation.value=oBj.valout[4];");
			out.println("}");
			//===============End Supervisor Help Functions
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_REQ_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_REQ_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_REQ_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.hid_add1.value=oBj.valout[5];");
			out.println("    document.Form1.hid_add2.value=oBj.valout[6];");
			out.println("    document.Form1.hid_add3.value=oBj.valout[7];");
			out.println("}"); 
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    document.Form1.TXT_EMPLOYEE.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
			out.println("}"); 

		  out.println("function Generate_Letter(val4,val5,val6,val7) {");
			out.println("if(validate_data()){");
			out.println("if( val6=='FINLEASE' && val5 =='ACTIVATED'){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_termination_lease_finlease&mature_date=\"+val7+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");//
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_termination_lease_finlease&status=\"+val5+\"&type=\"+val6+\"&print=TRUE&mature_date=\"+val7+\"&finance_no=\"+val4+\"\";");
			out.println("}");
			out.println("if (val6=='FINLEASE' && val5 =='TERMINATED'){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_Agreement&mature_date=\"+val7+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_Agreement&status=\"+val5+\"&type=\"+val6+\"&print=TRUE&mature_date=\"+val7+\"&finance_no=\"+val4+\"\";");
			//out.println("alert(m_url);");
			out.println("}");
			out.println("if (val6=='HIREPURCH' && val5 =='ACTIVATED'){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Normal_termination_lease_purchase_hirepurch&mature_date=\"+val7+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Normal_termination_lease_purchase_hirepurch&status=\"+val5+\"&type=\"+val6+\"&print=TRUE&mature_date=\"+val7+\"&finance_no=\"+val4+\"\";");
			out.println("}");
			out.println("if(val6=='HIREPURCH' && val5 =='TERMINATED'){");//ENTERED
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_purchase_Agreement&mature_date=\"+val7+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_purchase_Agreement&status=\"+val5+\"&type=\"+val6+\"&print=TRUE&mature_date=\"+val7+\"&finance_no=\"+val4+\"\";");
			//out.println("alert(m_url);");
			out.println("}");
			out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}else{");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("}");
			out.println("}");		
			
			out.println("function change(row1,row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==true && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("}"); 
			
			out.println("function change_not(row1,row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==true && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");			
			out.println("}"); 
			
			out.println("function change_not_C(row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==true && document.Form1.elements[\"chk_not_req_C_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_app_C_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_C_\"+row].checked==false && document.Form1.elements[\"chk_not_req_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");			
			out.println("}"); 
	
			out.println("function change_C(row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==true && document.Form1.elements[\"chk_not_req_C_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_C_\"+row].checked==false && document.Form1.elements[\"chk_not_req_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("}"); 

			
			out.println("function check_1(row1,row) {");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='N'");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked=true");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("}");	
			out.println("}"); 
			
			
			out.println("function check_C(row) {");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='N'");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].checked=true");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("}");	
			out.println("}"); 
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}");
			
			out.println("function close_screen() {");
			//out.println(" alert(document.Form1.hid_close_sts.value);");
			out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
			out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("					window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=main_page';");
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else { "); 
			out.println("		     close_window();"); 
			out.println("		}"); 
			out.println("}");
//=============================================== End By Indika 21/08/08 ===================================================================================			
	    
			
			out.println("function makeRequestNew(){");		//Added By Sandun on 23-09-2008			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=termination_details_normal&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"&acti_termi=\"+document.Form1.TXT_DIVISION_CODE.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function Generate_Letter_Nom(val1,val2,val3,val4,val5) {");//Added By Sandun on 23-09-2008
			out.println("if( val3=='FINLEASE' && val2 =='ACTIVATED'){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_termination_lease_finlease&status=\"+val2+\"&type=\"+val3+\"&print=TRUE&mature_date=\"+val4+\"&finance_no=\"+val1+\"\";");
			//out.println("alert(m_url);");
			out.println("}");
			out.println("if (val3=='HIREPURCH' && val2 =='ACTIVATED'){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Normal_termination_lease_purchase_hirepurch&status=\"+val2+\"&type=\"+val3+\"&print=TRUE&mature_date=\"+val4+\"&finance_no=\"+val1+\"\";");
			out.println("}");
			out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");	
			
		 			
			out.println("</Script>");
			out.println("<body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"makeRequestNew()\">");	
			//out.println("<body onload=\"makeRequestNew()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"set_screen(),load_roll_value_1()\">");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_Termination_Letter\">"); 
			

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Termination - Letter Generation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>");
	
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='load_screen_status(\"SAVE\"), before_submit()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
  			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick= close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");//close_screen()
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
								
				out.println("<table align='center' width='100%' class='table' border='0'>"); 

				out.println("<tr class=tr_input>");
				out.println("<td width='20%' >Termination Type</td>");
				out.println("<td width='*%' >");
				out.println("<select name=\"TXT_DIVISION_CODE\" class=\"txt_input\" style=\"{width:120px;}\" onchange='makeRequestNew()'>");
				out.println("<OPTION value=\"TERMINATED\" >Early</option>");
				out.println("<OPTION value=\"ACTIVATED\" selected>Normal</option>");
				//out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
				out.println("</SELECT>");
				out.println("</td>");
				
				out.println("</tr>");
				out.println("<tr class=tr_input>");  
					
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCENO' class=div_input>Finance No *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' value=\"\" type='text' name='TXT_FINANCENO' maxlength='15' size='15' onblur=\"\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCENO' value=\"...\" onClick=\"help_button_3()\"></td>"); 
				//out.println("<td width='5%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>"); 

				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
			
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
								
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
	      	out.println("</body>"); 
				out.println("</html>"); 	
       	}
 
			else if(m_chksql.equals("termination_details")){			
							
				String m_finance_no=req.getParameter("finance_no").trim();
				String m_acti_termi=req.getParameter("acti_termi").trim();
				String m_print_status="";
				rs2 =stmt2.executeQuery(" select NVL(TERMINATION_NO,'-'), "+
		       											" NVL(TO_CHAR(TER_TYPE_ENT_DATE),'-'), "+
		      											" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
																" TRANSACTION_TYPE "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																" WHERE UPPER(FINANCE_NO)         = UPPER('"+m_finance_no+"') "+
																" AND   UPPER(APPLICATION_STATUS) = UPPER('"+m_acti_termi+"') ");	

				out.println("<br>");			
						
			  out.println("<table class='table' align='center' border='0' width='100%'>");
		    out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"20%\" align='left'>Termination No</td> ");
				out.println("<td width=\"20%\" align='left'>Termination Date</td> ");
		    out.println("<td width=\"30%\" align='left'>Client Name</td> ");
				out.println("<td width=\"10%\" align='center'>Letter</td> ");
				out.println("<td width=\"5%\" align='center'>Print</td> ");
				out.println("</tr>");
				
				boolean more2 = rs2.next();
				while(more2){
					String termination_no   = rs2.getString(1);
					String termination_date = rs2.getString(2);
					String client_name_1    = rs2.getString(3);
					String transaction_type = rs2.getString(4);
					
					out.println("<tr>");
					out.println("<td width=\"20%\" align='left'>"+termination_no+"</td> ");
					out.println("<td width=\"20%\" align='left'>"+termination_date+"</td> ");
			  	out.println("<td width=\"30%\" align='left'>"+client_name_1+"</td> ");
					out.println("<td width='10%'  align='center'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Generate\" onclick=\"Generate_Letter('"+m_finance_no+"','"+m_acti_termi+"','"+transaction_type+"','"+termination_date+"')\" style=\"{width:100px}\"></td>");
					
					rs3 =stmt3.executeQuery(" SELECT A.FINANCE_NO ,"+//1
																	" A.PRINT_STATUS "+	//2										      
															    " FROM  "+m_schema_name+".AF_CR_TERMI_LETTER_PRINT A "+
																  " WHERE A.FINANCE_NO ='"+m_finance_no+"' ");
																
				more3 = rs3.next();
				if(more3){
				  m_print_status = rs3.getString(2);
				  }			
										
					if(m_print_status.equals("Y")){						
					out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk checked></td> ");
					}else{
					out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk ></td> ");
					}
					out.println("</tr>");
					more2=rs2.next();
				}
			
			}
					
			
			else if(m_chksql.equals("termination_details_normal")){//	Added By Sandun on 23-09-2008
			
				String m_finance_no=req.getParameter("finance_no").trim();
				String m_acti_termi=req.getParameter("acti_termi").trim();
				String termination_no   = "";
				String termination_date = "";
				String client_name_1    = "";
				String transaction_type = "";
				String m_print_status   = "";
				if(m_acti_termi.equals("ACTIVATED") && m_finance_no.equals("")){
				
				rs2 =stmt2.executeQuery(" SELECT NVL(TERMINATION_NO,'-'), "+//1
						       							" NVL(TO_CHAR(TER_TYPE_ENT_DATE,'DD-MON-YYYY'),'-'), "+//2
						      							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//3
															  " TRANSACTION_TYPE, "+//4
																" FINANCE_NO "+//5
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															  " WHERE UPPER(APPLICATION_STATUS) = UPPER('"+m_acti_termi+"') "+
																" AND SYSDATE<=TO_CHAR(TER_TYPE_ENT_DATE,'DD-MON-YYYY') AND ADD_MONTHS(SYSDATE,1)>TO_CHAR(TER_TYPE_ENT_DATE,'DD-MON-YYYY') "+
																" ORDER BY TER_TYPE_ENT_DATE ");	
																
				
				out.println("<br>");			
				out.println("<hr>");			
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"20%\" align='left'>Termination No</td> ");
				out.println("<td width=\"20%\" align='left'>Termination Date</td> ");
		    out.println("<td width=\"30%\" align='left'>Client Name</td> ");
				out.println("<td width=\"10%\" align='center'>Letter</td> ");
				out.println("<td width=\"5%\" align='center'>Print</td> ");
				out.println("</tr>");
				
				int j=0;			
				boolean more2 = rs2.next();
						
				while(more2){		
			     termination_no    = rs2.getString(1);
					 termination_date  = rs2.getString(2);
					 client_name_1     = rs2.getString(3);
					 transaction_type  = rs2.getString(4);
					 m_finance_no      = rs2.getString(5);
					 
				if(j>0 && j%2==1){
					out.println("<tr class=tr_input1>");
					}
					else{
					out.println("<tr class=tr_input >");
					}	
					out.println("<td width=\"12%\" align='left'>"+termination_no+"</td> ");
					out.println("<td width=\"12%\" align='left'>"+termination_date+"</td> ");
			  	out.println("<td width=\"12%\" align='left'>"+client_name_1+"</td> ");
					out.println("<td width='12%'  align='center'><input type=button class='but_input' name=\"letter_gen_but_nom_"+j+"\" value=\"Generate\" onclick=\"Generate_Letter_Nom('"+m_finance_no+"','"+m_acti_termi+"','"+transaction_type+"','"+termination_date+"','"+termination_no+"')\" style=\"{width:100px}\"></td>");
					
					
					rs3 =stmt3.executeQuery(" SELECT A.FINANCE_NO ,"+//1
																	" A.PRINT_STATUS "+	//2										      
															    " FROM  "+m_schema_name+".AF_CR_TERMI_LETTER_PRINT A "+
																  " WHERE A.FINANCE_NO ='"+m_finance_no+"' ");
																
				more3 = rs3.next();
				if(more3){
				  m_print_status = rs3.getString(2);
				  }			
										
					if(m_print_status.equals("Y")){					
					out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk_"+j+" checked></td> ");
					}
					else{
					out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk_"+j+"></td> ");
					}
					out.println("</tr>");
					more2=rs2.next();
					j=j+1;
					m_print_status="";
				}
			}
		}
		
		
		
		
		else if(m_chksql.equals("Normal_termination_lease_purchase_hirepurch")){//normal_ter_le_pur_hire
				int i=0;		
				String m_finance_no = req.getParameter("finance_no");	
				String m_mature_date= req.getParameter("mature_date");
				String m_type  = req.getParameter("type");
				String m_print = req.getParameter("print");
				String m_status = req.getParameter("status");
				
				//String m_lessee ="";
		
				/*rs4 = stmt4.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+ 
												" A.TERMINATION_NO, "+
												" A.CLIENT_CODE, "+
												" B.FULL_NAME, "+
												" A.FINANCE_NO, "+
												" C.VEHICLE_NO "+
		                        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
												" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
												" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
												" A.TERMINATION_NO='"+m_turmi_no+"'	"); */	
												
				rs4 = stmt4.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+
													" NVL(A.VEHICLE_NO,A.CHASSIS_NO), "+
													" B.FULL_NAME, "+
													" C.TERMINATION_NO ,"+
													" C.FINANCE_NO "+
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A , "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
													" "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
													" WHERE C.CLIENT_CODE=B.CLIENT_CODE AND "+
													" A.TERMINATION_NO = C.TERMINATION_NO And "+
													" C.FINANCE_NO='"+m_finance_no+"' ");
												
				String client_address = "";
				String vehicle_equ_no = "";
				
				
				boolean more4 = rs4.next();
				if(more4){
					client_address = rs4.getString(1);
				 	client_name = rs4.getString(3);
					vehicle_equ_no = rs4.getString(2);
					
				}

			  	out.println("<html><head>"); 
				out.println("<title>Normal termination lease purchase  -hirepurch</title></head>");
				//out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      	out.println("<script>");
				/*
			    out.println("function save_data(){");						
					out.println("m_table.innerHTML=\"\" ");
				  out.println("window.print();");
				  out.println("window.close();");
			    out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&finance_no="+m_finance_no+" \";");
			   	 //out.println("window.print();");
						out.println("}");	
						
					out.println("function add_bttn(){");	
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			    out.println("m_writedata+'</table>';");
					out.println("}");
					*/
					
					
					out.println("function save_data(){");						
					out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&status="+m_status+"&type="+m_type+"&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
					out.println("}");	
						
					out.println("function add_bttn(){");
						if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			    out.println("m_writedata+'</table>';");
					}
					out.println("}");
					
					
					
					
					out.println("</script>");	
					//out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
					out.println("<body bgcolor='white' onload=add_bttn()><br>");
				  out.println("<form name='Form1'>");			    
							
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+client_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >"+client_address+"</td></tr>");
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir or Madam:</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' >Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+vehicle_equ_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Purchase Contract No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
				
								
				String data=" We sincerely appreciate your valuable business relation had with us and wish to inform you that the above contract will be matured on "+m_mature_date+" as per the lease purchase terms.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");			
						
				data="We would appreciate if you make arrangement to pay following amount to get the unfettered rights of the above equipment.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				
					String m_termination_no ="";
					String m_termination_date ="";
					double m_other_charge=0.0;
					double m_due_amount=0.0;
					double m_net_rental=0.0;
					double m_odi_net=0.0;	
					double m_sale_value=0.0;
						
					
						rs = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+//1
													       " A.FINANCE_NO, "+//2
													       " A.APPLICATION_NO, "+//3
													       " A.CLIENT_CODE, "+//4
													       " TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'), "+//5        
													       " NVL(A.CHARGES,0), "+ //6
																 " NVL(A.DUE_AMOUNT,0), "+//7
																 " NVL(A.NET_RENTALS,0), "+//8
																 " NVL(A.ODI_NET,0) "+//9
													  		 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
																 " WHERE A.FINANCE_NO='"+m_finance_no+"' ");
			more = rs.next();
			if(more){
			m_termination_no   = rs.getString(1);
			m_other_charge     = rs.getDouble(6);
			m_termination_date = rs.getString(5);
			m_due_amount       = rs.getDouble(7);
			m_net_rental       = rs.getDouble(8);
			m_odi_net 				 = rs.getDouble(9);
			}
			
			
			rs1 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+ //1
											        " NVL(SUM(A.SALE_VALUE),0) "+	//2										     
											        " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A "+
															" WHERE A.TERMINATION_NO='"+m_termination_no+"' "+
															" GROUP BY A.TERMINATION_NO,A.SALE_VALUE ");
														
			boolean more1=rs1.next();
			if(more1){
			 m_sale_value = rs1.getDouble(2);
			}
			
			
			double m_total=m_odi_net+m_net_rental+m_due_amount+m_other_charge+ m_sale_value;
				
				
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");				
				out.println("<td width='*%' class='rep-body' align='right'>Rs</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue lease purchase rentals</td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_net_rental)+"</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Future lease purchase rental</td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_due_amount)+"</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue Interest</td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_odi_net)+"</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Sale price of the equipment</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_sale_value)+"</td>");
				out.println("<td width='20%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Other charges	</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_other_charge)+"</td>");
				out.println("<tr><td width='25%' class='rep-body' ></td></tr>");
				//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>---------------</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Total receivables</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_total)+"</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>==========</td>");
				out.println("<td width='25%' class='rep-body' ></td></tr>");
				out.println("</table>");

				data="In order to finalize of equipment sale, please forward your remittance for Rs."+nf.format(m_total)+" on or before "+m_mature_date+", beyond which further overdue interest will accumulate.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				data="We are always happy to serve you and please feel free to contact Mr. <Client Manager's name>for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		///////////////////////////////////////////////////////////////////////////////		

            data="Thanking You,";
				out.println("<br>");																	

			 	data="Yours sincerely";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");	

				out.println("<br><br><br><br>");
				out.println("<br>");
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("Early_termination_letter_Lease_Agreement")){//early_termi_let_le_pur_agg
				int i=0;		
				String m_finance_no = req.getParameter("finance_no");	
				String m_mature_date= req.getParameter("mature_date");
				String m_type  = req.getParameter("type");
				String m_print = req.getParameter("print");	
				String m_status = req.getParameter("status");
				
				String m_lessee ="";
				String m_termination_date="";
				String m_termination_no="";
				double m_other_charge=0.0;
				double m_due_amount=0.0;
				double m_net_rental=0.0;
				double m_odi_net=0.0;
				double m_sale_value=0.0;
				double m_nibsm = 0.0;
		    double m_pri_nibsm=0.0;
				double m_aft_nibsm=0.0;
				double m_net_amount=0.0;
				double m_fut_ren_amount=0.0;
				double m_ren_due=0.0;
				double m_aft_sale_value=0.0;
				double m_tot_other_charges=0.0;
				String m_apply_date="";
				String m_ent_user_1="";
				/*rs5 = stmt5.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+ 
												" A.TERMINATION_NO, "+
												" A.CLIENT_CODE, "+
												" B.FULL_NAME, "+
												" A.FINANCE_NO, "+
												" C.VEHICLE_NO "+
		                        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
												" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
												" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
												" A.TERMINATION_NO='"+m_turmi_no+"'	");	*/
												
				rs5 = stmt5.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+
													" NVL(NVL(A.VEHICLE_NO,A.CHASSIS_NO),'-'), "+
													" B.FULL_NAME, "+//Modified by Dineth on 2009-01-08
													" C.TERMINATION_NO,"+
													" C.FINANCE_NO, "+
													" B.CLIENT_TYPE, "+//Added by Dineth on 2009-01-09
													" "+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE) "+//Added by Dineth on 2009-01-16
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A , "+
													"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
													"      "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
													" WHERE C.CLIENT_CODE    = B.CLIENT_CODE AND "+
													"       A.TERMINATION_NO = C.TERMINATION_NO AND "+
													" C.FINANCE_NO = '"+m_finance_no+"' ");
												
				String client_address = "";
				String client_city = "";//Added by Dineth on 2009-01-16
				String vehicle_equ_no = "";
			  String term_no = "";
				String m_app_no="";
				String m_ent_user="";
				String m_cli_type_1="";
				double m_aft_vat=0.0;
				boolean more5 = rs5.next();
				if(more5){
					client_address = rs5.getString(1);
				 	client_name    = rs5.getString(3);
					vehicle_equ_no = rs5.getString(2);
					term_no        = rs5.getString(4);
					m_cli_type_1   = rs5.getString(6);
					client_city    = rs5.getString(7);
					
				}
				//Added by Dineth on 2008-11-06
				rs11=stmt5.executeQuery(" SELECT "+
																" APPLICATION_NO,ENT_USER,VAT_PER FROM "+m_schema_name+".AF_CR_PRO_TERMINATION"+
																" WHERE TERMINATION_NO='"+term_no+"'");
				boolean more11=rs11.next();
				if(more11){
					m_app_no = rs11.getString(1);
					m_ent_user= rs11.getString(2);
					m_aft_vat=rs11.getDouble(3);
				}
				rs11=stmt5.executeQuery(" SELECT  SUM(NVL(NIBSM,0)) "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
                                " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
                                " FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                " WHERE  (CHASSIS_NO,INVOICE_NO)  IN (SELECT CHASSIS_NO,PRO_INVOICE_NO "+
                                                    " FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+ 
                                                    " WHERE  ENT_USER = '"+m_ent_user+"') AND "+
																				"APPLICATION_NO = '"+m_app_no+"'"+ 
                                " ) AND "+
                                " APPLICATION_NO='"+m_app_no+"'");
                  
				
				boolean more12=rs11.next();
				if(more12){
				m_nibsm=rs11.getDouble(1);
				}
				rs11=stmt5.executeQuery(" SELECT VAT_PERCENTAGE "+
           											" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
           											" WHERE (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                " WHERE  (CHASSIS_NO,INVOICE_NO)  IN (SELECT CHASSIS_NO,PRO_INVOICE_NO "+
                                                    " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
                                                    " WHERE  ENT_USER = '"+m_ent_user+"') AND "+
                                        " APPLICATION_NO = '"+m_app_no+"'"+ 
                                        ") AND "+
                  							" APPLICATION_NO='"+m_app_no+"' AND "+
                  							" ROWNUM = 1 ");
				boolean more13=rs11.next();
				double m_pri_vat=0.0;
				if(more13){
				m_pri_vat=rs11.getDouble(1);
				}
				m_pri_nibsm=m_nibsm*100.0/(100.0+m_pri_vat);
				
				m_aft_nibsm=m_pri_nibsm+m_pri_nibsm*m_aft_vat/100.0;
				
				rs15=stmt.executeQuery(" SELECT TO_CHAR(APPLY_DATE,'DD-MM-YYYY') FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE TERMINATION_NO='"+term_no+"'");
				boolean more15=rs15.next();
				if(more15){
				m_apply_date       = rs15.getString(1);//Added by Dineth on 2008-11-10
				}
				//End by Dineth on 2008-11-06
			  	out.println("<html><head>"); 
				out.println("<title>Early termination letter Lease Agreement</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      	out.println("<script>");
				
				out.println("function save_data(){");						
					out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&status="+m_status+"&type="+m_type+"&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
					out.println("}");	
						
					out.println("function add_bttn(){");
						if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			    out.println("m_writedata+'</table>';");
					}
					out.println("}");
					
					out.println("</script>");	
					//out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
					out.println("<body bgcolor='white' onload=add_bttn()><br>");
				  out.println("<form name='Form1'>");
			
				
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>");
				if(m_cli_type_1.equals("C")){//Added by Dineth on 2009-01-08
				out.println("<tr><td width='*%' class='rep-body' >The Director</td></tr>");

				}
				out.println("<tr><td width='*%' class='rep-body' >"+client_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >"+client_address+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >"+client_city+"</td></tr>");//Added by Dineth on 2009-01-16
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='50%' class='rep-body' >Early Termination Cost of Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+vehicle_equ_no+" </td></tr>");
				out.println("<tr><td width='50%' class='rep-body' >Lease Agreement No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");
				String data="We refer to your inquiry on the captioned subject and we are pleased to provide the early termination settlement value as at "+m_apply_date+" ";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
			//-------------------------------------------------------------------------------------------------
			
			
			rs13 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+//1
											       " A.FINANCE_NO, "+//2
											       " A.APPLICATION_NO, "+//3
											       " A.CLIENT_CODE, "+//4
											       " TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'), "+//5        
											       " NVL(A.CHARGES,0), "+ //6
														 " NVL(A.DUE_AMOUNT,0), "+//7
														 " NVL(A.NET_RENTALS,0), "+//8
														 " NVL(A.ODI_NET,0), "+//9
														 " NVL(A.NET_AMOUNT,0), "+//10 Added by Dineth on 2008-11-06
														 " NVL("+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL(FINANCE_NO,'',TO_CHAR(APPLY_DATE,'DD-MM-YYYY')),0), "+//Added by Dineth on 2008-11-07
														 " TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'), "+//12 Added by Dineth on 2008-11-10
														 " "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER) "+
											  		 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
														 " WHERE A.TERMINATION_NO='"+term_no+"'");
			more = rs13.next();
			if(more){
			m_termination_no   = rs13.getString(1);
			m_other_charge     = rs13.getDouble(6);
			m_termination_date = rs13.getString(5);
			m_due_amount       = rs13.getDouble(7);
			m_net_rental       = rs13.getDouble(8);
			m_odi_net 				 = rs13.getDouble(9);
			m_net_amount       = rs13.getDouble(10);//Added by Dineth on 2008-11-06
			m_ren_due          = rs13.getDouble(11);//Added by Dineth on 2008-11-07
			//m_apply_date       = rs13.getString(12);//Added by Dineth on 2008-11-10
			m_ent_user_1			 = rs13.getString(13);
			}
			
			//m_fut_ren_amount       = Math.ceil(m_net_amount+m_net_amount*m_aft_vat/100.0);
			m_fut_ren_amount       = m_net_amount+m_net_amount*m_aft_vat/100.0;
			
			m_tot_other_charges    = m_other_charge+m_other_charge*m_aft_vat/100.0;
			
			rs14 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+ //1
											        " NVL(SUM(A.SALE_VALUE),0) "+	//2										     
											        " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A "+
															" WHERE A.TERMINATION_NO='"+term_no+"'"+
															" GROUP BY A.TERMINATION_NO,A.SALE_VALUE ");
			
			boolean more1=rs14.next();
			if(more1){
			 m_sale_value = rs14.getDouble(2);
			 
			}
			
			 m_aft_sale_value = m_sale_value + m_sale_value*m_aft_vat/100.0;
			 //Added by Dineth on 2009-01-09
			 String m_str_aft_nibsm   =  nf.format(m_aft_nibsm);
			 String m_str_nibsm       =  nf.format(m_nibsm);
			 String m_str_ren_amount  =  nf.format(m_fut_ren_amount);
			 
			 double m_aft_nibsm_1     =  nf.parse(m_str_aft_nibsm).doubleValue();
			 double m_nibsm_1         =  nf.parse(m_str_nibsm).doubleValue();
			 double m_fut_ren_amount_1=  nf.parse(m_str_ren_amount).doubleValue();
				
			 //End by Dineth on 2009-01-09
			/*rs1=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL('"+m_fin_no+"',' ',TO_CHAR('"+apply_date+"','DD-MM-YYYY')) FROM DUAL");
			double m_ren_due=0.0;
			boolean more2=rs1.next();
			if(more2){
			 m_ren_due=rs1.getDouble(1);
			}*/
			//double m_total=m_odi_net+m_pri_nibsm+m_net_rental+m_due_amount+m_other_charge+ m_sale_value-m_aft_nibsm;
			//double m_total=m_odi_net+m_pri_nibsm+m_fut_ren_amount+m_due_amount+m_ren_due+m_tot_other_charges+ m_aft_sale_value-m_aft_nibsm;
			//double m_total=m_odi_net+m_nibsm+m_fut_ren_amount+m_due_amount+m_ren_due+m_tot_other_charges+ m_aft_sale_value-m_aft_nibsm;
			//double m_total=m_odi_net+Math.round(m_aft_nibsm-m_nibsm+m_fut_ren_amount)+m_due_amount+m_ren_due+m_tot_other_charges+ m_aft_sale_value;
			double m_total=m_odi_net+m_aft_nibsm_1-m_nibsm_1+m_fut_ren_amount_1+m_due_amount+m_ren_due+m_tot_other_charges+ m_aft_sale_value;
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>Rs.</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Discounted future rentals value with VAT</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_fut_ren_amount)+"</td>");
				out.println("<td width='20%' class='rep-body'></td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >NIBSM</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_aft_nibsm)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Over due Rentals with VAT</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_due_amount+m_ren_due)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Overdue Interest Charges</td>");
			//	out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_odi_net)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Sale price of the equipment</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_aft_sale_value)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Other Charges	</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_tot_other_charges)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >(-) NIBSM</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_nibsm)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>----------------------</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >Total settlement</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_total)+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>==========</td></tr>");
				out.println("</table>");
				
				//data="We would appreciate if you make arrangement to settle above amount on or before above date as the above offer expire on "+m_mature_date+" "; //Commented by Dineth on 2008-11-11
				data="We would appreciate if you make arrangement to settle above amount on or before above date as the above offer expire on "+m_termination_date+" "; 
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				/*rs1=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.MK_OFFICER) "+
															" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+
															" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+
															" "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
															" WHERE A.INQUIRY_CODE=B.INQUARY_NO "+
															" AND B.FINANCE_NO=C.FINANCE_NO "+
															" AND C.TERMINATION_NO='"+term_no+"'");*/
			  rs1=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) "+
                              " FROM "+
                              " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
                              " "+m_schema_name+".AF_CR_PRO_TERMINATION B "+ 
                              " WHERE A.FINANCE_NO=B.FINANCE_NO "+ 
                              " AND B.TERMINATION_NO='"+term_no+"'");
				
				String m_collection_officer="";
				boolean more20 = rs1.next();
				if(more20){
					m_collection_officer=rs1.getString(1);
				}
				
				data="We are always happy to serve you and please feel free to contact Mr. "+m_collection_officer+" for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		///////////////////////////////////////////////////////////////////////////////		

				out.println("<br><br>");																				
			
			 	data="Yours faithfully,";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='25%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");	

				out.println("<br><br><br><br>");
				out.println("<br>");
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("Early_termination_letter_Lease_purchase_Agreement")){
				int i=0;		
				String m_finance_no = req.getParameter("finance_no");	
				String m_mature_date= req.getParameter("mature_date");
				String m_type  = req.getParameter("type");
				String m_print = req.getParameter("print");	
				String m_status = req.getParameter("status");
				
				String m_lessee ="";
				String m_termination_no ="";
				String m_termination_date ="";
				double m_other_charge=0.0;
				double m_due_amount=0.0;
				double m_net_rental=0.0;
				double m_odi_net=0.0;	
				double m_sale_value=0.0;
				double m_nibsm=0.0;
				double m_pri_nibsm=0.0;
				double m_aft_nibsm=0.0;
				double m_net_amount=0.0;
				double m_fut_ren_amount=0.0;
				double m_ren_due=0.0;
				double m_aft_sale_value=0.0;
				double m_tot_other_charges=0.0;
				String m_apply_date="";
				String m_ent_user_1="";
				/*rs6 = stmt6.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+ 
												" A.TERMINATION_NO, "+
												" A.CLIENT_CODE, "+
												" B.FULL_NAME, "+
												" A.FINANCE_NO, "+
												" C.VEHICLE_NO "+
		                        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
												" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
												" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
												" A.TERMINATION_NO='"+m_turmi_no+"'	");	*/
												
				rs6 = stmt6.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2),"+
													" NVL(A.VEHICLE_NO,A.CHASSIS_NO), "+
													" B.FULL_NAME, "+
													" C.TERMINATION_NO,"+
													" C.FINANCE_NO, "+
													" B.CLIENT_TYPE, "+//Added by Dineth on 2009-01-08
													" "+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE) "+//Added by Dineth on 2009-01-16
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A , "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
													" "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
													" WHERE C.CLIENT_CODE=B.CLIENT_CODE AND "+
													" A.TERMINATION_NO = C.TERMINATION_NO And "+
													" C.FINANCE_NO='"+m_finance_no+"' ");
												
				String client_address = "";
				String client_city = "";//Added by Dineth on 2009-01-16
				String vehicle_equ_no = "";
				String term_no = "";
				String m_app_no="";
				String m_ent_user="";
				String m_client_type="";
			  double m_aft_vat=0.0;
				boolean more6 = rs6.next();
				if(more6){
					client_address = rs6.getString(1);
				 	client_name = rs6.getString(3);
					vehicle_equ_no = rs6.getString(2);
					term_no        = rs6.getString(4);
					m_client_type  = rs6.getString(6);
					client_city    = rs6.getString(7);//Added by Dineth on 2009-01-16
				}
				//Added by Dineth on 2008-11-06
				rs11=stmt5.executeQuery(" SELECT "+
																" APPLICATION_NO,ENT_USER,VAT_PER FROM "+m_schema_name+".AF_CR_PRO_TERMINATION"+
																" WHERE TERMINATION_NO='"+term_no+"'");
				boolean more11=rs11.next();
				if(more11){
					m_app_no = rs11.getString(1);
					m_ent_user= rs11.getString(2);
					m_aft_vat=rs11.getDouble(3);
				}
				rs11=stmt5.executeQuery(" SELECT  SUM(NVL(NIBSM,0)) "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
                                " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
                                " FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                " WHERE  (CHASSIS_NO,INVOICE_NO)  IN (SELECT CHASSIS_NO,PRO_INVOICE_NO "+
                                                    " FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+ 
                                                    " WHERE  ENT_USER = '"+m_ent_user+"') AND "+
																				"APPLICATION_NO = '"+m_app_no+"'"+ 
                                " ) AND "+
                                " APPLICATION_NO='"+m_app_no+"'");
                  
				
				boolean more12=rs11.next();
				if(more12){
				m_nibsm=rs11.getDouble(1);
				}
				rs11=stmt5.executeQuery(" SELECT VAT_PERCENTAGE "+
           											" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
           											" WHERE (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                " WHERE  (CHASSIS_NO,INVOICE_NO)  IN (SELECT CHASSIS_NO,PRO_INVOICE_NO "+
                                                    " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
                                                    " WHERE  ENT_USER = '"+m_ent_user+"') AND "+
                                        " APPLICATION_NO = '"+m_app_no+"'"+ 
                                        ") AND "+
                  							" APPLICATION_NO='"+m_app_no+"' AND "+
                  							" ROWNUM = 1 ");
				boolean more13=rs11.next();
				double m_pri_vat=0.0;
				if(more13){
				m_pri_vat=rs11.getDouble(1);
				}
				//m_pri_nibsm=m_nibsm+m_nibsm*m_pri_vat/100.0;
				m_pri_nibsm=m_nibsm*100.0/(100.0+m_pri_vat);
				
				m_aft_nibsm=m_pri_nibsm+m_pri_nibsm*m_aft_vat/100.0;
				
				rs15=stmt.executeQuery(" SELECT TO_CHAR(APPLY_DATE,'DD-MM-YYYY') FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE TERMINATION_NO='"+term_no+"'");
				boolean more15=rs15.next();
				if(more15){
				m_apply_date       = rs15.getString(1);//Added by Dineth on 2008-11-10
				}
				
				//End by Dineth on 2008-11-06
			  	out.println("<html><head>"); 
				out.println("<title>Early termination letter Lease purchase Agreement</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      		out.println("<script>");	
					
					out.println("function save_data(){");						
					out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&status="+m_status+"&type="+m_type+"&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
					out.println("}");	
						
					out.println("function add_bttn(){");
						if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			    out.println("m_writedata+'</table>';");
					}
					out.println("}");
					
					out.println("</script>");	
					//out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
					out.println("<body bgcolor='white' onload=add_bttn()><br>");
				  out.println("<form name='Form1'>");		 
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  	out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>");
				if(m_client_type.equals("C")){//Added by Dineth on 2009-01-08
				out.println("<tr><td width='*%' class='rep-body' >The Director</td></tr>");
				}
				out.println("<tr><td width='*%' class='rep-body' >"+client_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >"+client_address+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >"+client_city+"</td></tr>");//Added by Dineth on 2009-01-16
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='50%' class='rep-body' >Early Termination Cost of Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+vehicle_equ_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Purchase Agreement No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");									
				
				String data="We refer to your inquiry on the captioned subject and we are pleased to provide the early termination settlement value as at "+m_apply_date+" ";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");
				
				
				rs13 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+//1
													       " A.FINANCE_NO, "+//2
													       " A.APPLICATION_NO, "+//3
													       " A.CLIENT_CODE, "+//4
													       " TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'), "+//5        
													       " NVL(A.CHARGES,0), "+ //6
																 " NVL(A.DUE_AMOUNT,0), "+//7
																 " NVL(A.NET_RENTALS,0), "+//8
																 " NVL(A.ODI_NET,0), "+//9
																 " NVL(A.NET_AMOUNT,0), "+//10 Added by Dineth on 2008-11-06
													  		 " NVL("+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL(FINANCE_NO,'',TO_CHAR(APPLY_DATE,'DD-MM-YYYY')),0), "+//Added by Dineth on 2008-11-07
																 " "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER) "+
																 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
																 " WHERE A.TERMINATION_NO='"+term_no+"'");
			more = rs13.next();
			if(more){
			m_termination_no   = rs13.getString(1);
			m_other_charge     = rs13.getDouble(6);
			m_termination_date = rs13.getString(5);
			m_due_amount       = rs13.getDouble(7);
			m_net_rental       = rs13.getDouble(8);
			m_odi_net 				 = rs13.getDouble(9);
			m_net_amount       = rs13.getDouble(10);//Added by Dineth on 2008-11-06
			m_ren_due          = rs13.getDouble(11);//Added by Dineth on 2008-11-07
			m_ent_user_1       = rs13.getString(12);//Added by Dineth on 2008-11-11
			}
			//Added by Dineth on 2008-11-06
			
			//m_fut_ren_amount       = Math.ceil(m_net_amount+m_net_amount*m_aft_vat/100.0);
			m_fut_ren_amount       = m_net_amount+m_net_amount*m_aft_vat/100.0;
			
			m_tot_other_charges    = m_other_charge+m_other_charge*m_aft_vat/100.0;
			
			rs14 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+ //1
											        " NVL(SUM(A.SALE_VALUE),0) "+	//2										     
											        " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A "+
															" WHERE A.TERMINATION_NO='"+term_no+"'"+
															" GROUP BY A.TERMINATION_NO,A.SALE_VALUE ");
														
			boolean more1=rs14.next();
			if(more1){
			 m_sale_value = rs14.getDouble(2);
			}
			
			 m_aft_sale_value = m_sale_value+m_sale_value*m_aft_vat/100.0;
			
			//double m_total=m_odi_net+m_net_rental+m_due_amount+m_other_charge+ m_sale_value;	
				//double m_total=m_pri_nibsm+m_odi_net+m_fut_ren_amount+m_ren_due+m_due_amount+m_other_charge+ m_aft_sale_value-m_aft_nibsm;
				//Added by Dineth on 2009-01-09
			 String m_str_aft_nibsm   =  nf.format(m_aft_nibsm);
			 String m_str_nibsm       =  nf.format(m_nibsm);
			 String m_str_ren_amount  =  nf.format(m_fut_ren_amount);
			 
			 double m_aft_nibsm_1     =  nf.parse(m_str_aft_nibsm).doubleValue();
			 double m_nibsm_1         =  nf.parse(m_str_nibsm).doubleValue();
			 double m_fut_ren_amount_1=  nf.parse(m_str_ren_amount).doubleValue();
				
			 //End by Dineth on 2009-01-09
				
				
				//double m_total=m_odi_net+Math.round(m_aft_nibsm-m_nibsm+m_fut_ren_amount)+m_ren_due+m_due_amount+m_other_charge+ m_aft_sale_value;
				double m_total=m_odi_net+m_aft_nibsm_1-m_nibsm_1+m_fut_ren_amount_1+m_ren_due+m_due_amount+m_other_charge+ m_aft_sale_value;
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				//out.println("<td width='4%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>Rs</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Discounted future rentals value</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_fut_ren_amount)+"</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='45%' class='rep-body' >NIBSM</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_aft_nibsm)+"</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Over due Rentals</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_due_amount+m_ren_due)+"</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue Interest Charges</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_odi_net)+"</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Sale price of the equipment</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_aft_sale_value)+"</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Other Charges</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_tot_other_charges)+"</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				//Added by Dineth on 2008-11-10
				out.println("<tr><td width='40%' class='rep-body' >(-) NIBSM</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body'  align='right'>"+nf.format(m_nibsm)+"</td></tr>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				//End by Dineth on 2008-11-10
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>-----------------</td>");
				out.println("<td width='10%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Total settlement</td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_total)+"</td>");
				out.println("<tr><td width='40%' class='rep-body' ></td></tr>");
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				//out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' align='right'>============</td>");
				out.println("<td width='40%' class='rep-body' ></td></tr>");
				out.println("</table>");
				
				//Added by Dineth on 2008-11-11
				data="We would appreciate if you make arrangement to settle above amount on or before above date as the above offer expire on "+m_termination_date+" "; 
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				/*rs1=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.MK_OFFICER) "+
															" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+
															" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+
															" "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
															" WHERE A.INQUIRY_CODE=B.INQUARY_NO "+
															" AND B.FINANCE_NO=C.FINANCE_NO "+
															" AND C.TERMINATION_NO='"+term_no+"'");*/
				rs1=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) "+
                              " FROM "+
                              " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
                              " "+m_schema_name+".AF_CR_PRO_TERMINATION B "+ 
                              " WHERE A.FINANCE_NO=B.FINANCE_NO "+ 
                              " AND B.TERMINATION_NO='"+term_no+"'");
				
				String m_collection_officer="";
				boolean more20 = rs1.next();
				if(more20){
					m_collection_officer=rs1.getString(1);
				}
				
				
				data="We are always happy to serve you and please feel free to contact Mr. "+m_collection_officer+" for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		///////////////////////////////////////////////////////////////////////////////		
			
			 	data="Yours faithfully,";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");
				out.println("<br><br><br><br>");	
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("normal_termination_lease_finlease")){//normal_ter_le_pur_hire
				int i=0;		
				String m_finance_no = req.getParameter("finance_no");
				String m_mature_date= req.getParameter("mature_date");
				String m_type  = req.getParameter("type");
				String m_print = req.getParameter("print");	
				String m_status = req.getParameter("status");

				
		
				/*rs8 = stmt8.executeQuery (" SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2), "+ 
												" A.TERMINATION_NO, "+
												" A.CLIENT_CODE, "+
												" B.FULL_NAME, "+
												" A.FINANCE_NO, "+
												" C.VEHICLE_NO "+
		                        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
												" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
												" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
												" A.TERMINATION_NO='"+m_turmi_no+"'	");	*/
												
				rs7 = stmt7.executeQuery ( " SELECT TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2),"+
													" NVL(A.VEHICLE_NO,A.CHASSIS_NO), "+
													" B.FULL_NAME, "+
													" C.TERMINATION_NO, "+
													" C.FINANCE_NO, "+
													" B.CLIENT_TYPE "+
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A , "+
													" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
													" "+m_schema_name+".AF_CR_PRO_TERMINATION C "+
													" WHERE C.CLIENT_CODE=B.CLIENT_CODE AND "+
													" A.TERMINATION_NO = C.TERMINATION_NO And "+
													" C.FINANCE_NO='"+m_finance_no+"' ");
												
				String client_address = "";
				String vehicle_equ_no = "";
				String termin_no ="";
			  String cli_type_1="";
				
				boolean more7 = rs7.next();
				if(more7){
					client_address = rs7.getString(1);
				 	client_name    = rs7.getString(3);
					vehicle_equ_no = rs7.getString(2);
					termin_no =  rs7.getString(4);
					cli_type_1=  rs7.getString(6);
				}

				  out.println("<html><head>"); 
					out.println("<title>Normal termination lease  finlease</title></head>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				  out.println("<script>");
					
										
					out.println("function save_data(){");						
					out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&status="+m_status+"&type="+m_type+"&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
					out.println("}");	
						
					out.println("function add_bttn(){");
						if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			    out.println("m_writedata+'</table>';");
					}
					out.println("}");
					
					out.println("</script>");	
					//out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
					out.println("<body bgcolor='white' onload=add_bttn()><br>");
					out.println("<form name='Form1'>");	  
				
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				  	out.println("</tr>"); 
					out.println("</table>");
					out.println("<blockquote><font size=3><p style='text-align:left'>");					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
					out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
				 	out.println("</table>");
	
					out.println("</font></p></blockquote>");	
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
					out.println("</table>");
				
					//	out.println("m_fin_number"+m_fin_number);
				
					out.println("<br>");
				
					out.println("<table border='0' width='90%' class='table'>"); 
					if(cli_type_1.equals("C")){//Added by Dineth on 2009-01-08
				      out.println("<tr><td width='*%' class='rep-body' >The Director</td></tr>");
				  }
					out.println("<tr><td width='*%' class='rep-body' >"+client_name+"</td></tr>");
					out.println("<tr><td width='*%' class='rep-body' >"+client_address+"</td></tr>");
					out.println("</table>");	
				
					out.println("<br><br>");
										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' >Dear Sir or Madam:</td></tr>");
					out.println("</table>");
					out.println("<br>");
	//========================================== Add By Indika ===================================================
	
				
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='40%' class='rep-body' >Vehicle/Equipment No</td>");
					out.println("<td width='2%' class='rep-body' >:</td>");
					out.println("<td width='*%' class='rep-body' >"+vehicle_equ_no+" </td></tr>");
					out.println("<tr><td width='35%' class='rep-body' >Lease Contract No</td>");
					out.println("<td width='2%' class='rep-body' >:</td>");
					out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
					out.println("</table>");
	//========================================= End By Indika ========================================================
					out.println("<br><br>");
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
					
					String data="We sincerely appreciate your valuable business relation had with us and wish to inform you that the above contract will be matured on "+m_mature_date+" as per the lease terms.";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");			
					
					data="We would appreciate if you make arrangement to pay following amount to get the unfettered rights of the above equipment.";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					
					String m_termination_no ="";
					String m_termination_date ="";
					double m_other_charge=0.0;
					double m_due_amount=0.0;
					double m_net_rental=0.0;
					double m_odi_net=0.0;	
					double m_sale_value=0.0;
						
					
						rs = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+//1
													       " A.FINANCE_NO, "+//2
													       " A.APPLICATION_NO, "+//3
													       " A.CLIENT_CODE, "+//4
													       " TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'), "+//5        
													       " NVL(A.CHARGES,0), "+ //6
																 " NVL(A.DUE_AMOUNT,0), "+//7
																 " NVL(A.NET_RENTALS,0), "+//8
																 " NVL(A.ODI_NET,0) "+//9
													  		 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
																 " WHERE A.FINANCE_NO='"+m_finance_no+"' ");
			more = rs.next();
			if(more){
			m_termination_no   = rs.getString(1);
			m_other_charge     = rs.getDouble(6);
			m_termination_date = rs.getString(5);
			m_due_amount       = rs.getDouble(7);
			m_net_rental       = rs.getDouble(8);
			m_odi_net 				 = rs.getDouble(9);
			}
			
			
			rs1 = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+ //1
											        " NVL(SUM(A.SALE_VALUE),0) "+	//2										     
											        " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A "+
															" WHERE A.TERMINATION_NO='"+m_termination_no+"' "+
															" GROUP BY A.TERMINATION_NO,A.SALE_VALUE ");
														
			boolean more1=rs1.next();
			if(more1){
			 m_sale_value = rs1.getDouble(2);
			}
			
			
			double m_total=m_odi_net+m_net_rental+m_due_amount+m_other_charge+ m_sale_value;	
					
								
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='45%' class='rep-body' ></td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>Rs</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Overdue lease rentals with VAT</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_net_rental)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Future rental with VAT</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_due_amount)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Overdue Interest</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_odi_net)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Sale price of the equipment</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_sale_value)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Other Charges</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_other_charge)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' ></td>");
				//	out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>------------------</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' >Total receivables</td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>"+nf.format(m_total)+"</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("<tr><td width='45%' class='rep-body' ></td>");
					//out.println("<td width='10%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' align='right'>========</td>");
					out.println("<td width='20%' class='rep-body' ></td></tr>");
					out.println("</table>");
					
					data="In order to finalize of equipment sale, please forward your remittance for Rs."+nf.format(m_total)+" on or before "+m_mature_date+", beyond which further overdue interest will accumulate.";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					
					data="We are always happy to serve you and please feel free to contact Mr. for your future requirement.";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
			///////////////////////////////////////////////////////////////////////////////		
	
	            data="Thanking You,";
					out.println("<br>");																	
	
				 	data="Yours sincerely";
					out.println("<br>");						
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr>");
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
			 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
											
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr>");
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
				
					out.println("<br>");
					out.println("<br>");	
					out.println("<br>");
							
					out.println("<table border='0' width='90%' class='table'>");  		
					out.println("<tr>");
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
					out.println("</tr></table>");	
	
					out.println("<br><br><br><br>");
					out.println("<br>");
					out.println("</font></p></blockquote>");										
			  	out.println("</form></body></html>");
			}		
			
		else if(m_chksql.equals("normal_term_print")){//Added by Sandun on 25-09-2008 
		
		String m_finance_no = req.getParameter("finance_no");
		String m_type = req.getParameter("type");
		String m_mature_date = req.getParameter("mature_date");
		String m_status = req.getParameter("status");
		
		callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+"AF_CR_SAVE_TERM_PRINT(:1,:2,:3);END;");
		callstmt1.setString(1 ,m_finance_no);								
		callstmt1.setString(2 ,"Y");
		callstmt1.setString(3 ,m_username);
		callstmt1.execute();
		
		out.println("<html><head>");
		out.println("<script language='JavaScript'>");
		out.println("function load_scr(){");
		//out.println("alert(parent.frames[0].location.href);");
		if(m_type.trim().equals("FINLEASE") && m_status.trim().equals("ACTIVATED")){
		out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_termination_lease_finlease&status="+m_status+"&type=FINLEASE&print=FALSE&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
		}else 
		if(m_type.trim().equals("HIREPURCH") && m_status.trim().equals("ACTIVATED")){
			out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Normal_termination_lease_purchase_hirepurch&status="+m_status+"&type=HIREPURCH&print=FALSE&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
		}
		else
		if(m_type.trim().equals("FINLEASE") && m_status.trim().equals("TERMINATED")){
		out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_Agreement&status="+m_status+"&type=FINLEASE&print=FALSE&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
		}else 
		if(m_type.trim().equals("HIREPURCH") && m_status.trim().equals("TERMINATED")){
			out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_purchase_Agreement&status="+m_status+"&type=HIREPURCH&print=FALSE&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
		}
		
		out.println("}");
		out.println("</script></head>");
		out.println("<body onload=\"load_scr()\">");
		out.println("</body></html>");
		
		}	
					
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      	//return null;
		}finally{
		  	if(rs    !=null){try{rs.close();   }catch(Exception e){}}
		  	if(rs1    !=null){try{rs1.close();   }catch(Exception e){}}
		  	if(rs2    !=null){try{rs2.close();   }catch(Exception e){}}
			
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
			if(stmt2  !=null){try{stmt2.close(); }catch(Exception e){}}
			
	   	if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
			}
		}
}
