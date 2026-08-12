//Add by Indika
//on 22/08/08



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_odi_letter extends javax.servlet.http.HttpServlet {
	
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
				String m_date_dd = "";
				String m_date_mm = "";
				String m_date_yy = "";
				
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
			
			/*out.println("if(document.Form1.TXT_CLIENT.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");
			out.println("alert('No records')");
			out.println("document.Form1.TXT_CLIENT.value=\"\"");
			out.println("}");
			*/
			
			out.println("}");
			// Calendar Add by SJ 23/02/2011
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
			
			//Add by SJ 23/02/2011
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY1.value=v_date;");
			out.println("     document.Form1.VAL_MONTH1.value=v_month;");
			out.println("     document.Form1.VAL_YEAR1.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_from_date.value=date1");			
			out.println("}");
					
			out.println("}");
			
			//Add by SJ 23/02/2011
			
			 out.println("function get_sys_date(){");
			rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			if(rs.next()){
			m_date_dd = rs.getString(1).substring(0,2);
			m_date_mm = rs.getString(1).substring(3,5);
			m_date_yy = rs.getString(1).substring(6,10);
			}
			out.println("document.Form1.VAL_DAY1.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH1.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR1.value  =\""+m_date_yy+"\"");
			
			out.println("}");
			
			out.println("function makeRequest() {");
			//out.println("alert(\"TEST \")");
			out.println( "m_odi_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("document.Form1.hid_status.value='New'");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"odi_letter?chksql=termination_details&finance_no=\"+document.Form1.TXT_CLIENT.value+\"&acti_termi=\"+document.Form1.TXT_DIVISION_CODE.value+\"\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=odi_details&client=\"+document.Form1.TXT_CLIENT.value+\"&odi_date=\"+m_odi_date+\"\";");
			
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CLIENT.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCENO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"ODI Letter - \";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			//out.println("help_box.innerHTML=\"Collection - Thanking Letter-Guarantor \"+m_val;"); 
			out.println("help_box.innerHTML=\"Finance - ODI Letter - Letter Generation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Finance - ODI Letter - Letter Generation - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.CLIENT.disabled=true;"); 
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
			
			
			out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){");
			out.println(" document.Form1.TXT_CLIENT.value=\"\";");
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
			out.println("    m_criteria = document.Form1.TXT_CLIENT.value+\"@\"+document.Form1.TXT_DIVISION_CODE.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			//out.println("    HelpBox('1','10','0', m_criteria,'m_help_FINANCE_NO_termination','2');"); 
			out.println("}");
				
			out.println("function help_value_assign_100() {"); 
			out.println("   document.Form1.TXT_CLIENT.value=oBj.valout[2];");
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
			
			
			out.println("function help_update_value_assign_200() {"); 
			out.println("    document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
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

		  out.println("function Generate_Letter(val4,val5,val6,val7,val8) {");
			
			out.println("if( val4=='LETTER'){");
			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"odi_letter?chksql=normal_termination_lease_finlease&mature_date=\"+val7+\"&finance_no=\"+document.Form1.TXT_CLIENT.value+\"\";");//
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=generate_letter&odi_date=\"+val5+\"&type=\"+val6+\"&print=TRUE&finance_no=\"+val8+\"\";");
			out.println("}");
			out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");		
			
							
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_CLIENT.value=\"\";");
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=termination_details_normal&finance_no=\"+document.Form1.TXT_CLIENT.value+\"&acti_termi=\"+document.Form1.TXT_CLIENT.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function Generate_Letter_Nom(val1,val2,val3,val4,val5) {");//Added By Sandun on 23-09-2008
			out.println("if( val3=='FINLEASE' && val2 =='ACTIVATED'){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"odi_letter?chksql=normal_termination_lease_finlease&status=\"+val2+\"&type=\"+val3+\"&print=TRUE&mature_date=\"+val4+\"&finance_no=\"+val1+\"\";");
			//out.println("alert(m_url);");
			out.println("}");
			out.println("if (val3=='HIREPURCH' && val2 =='ACTIVATED'){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_odi_letter?chksql=Normal_termination_lease_purchase_hirepurch&status=\"+val2+\"&type=\"+val3+\"&print=TRUE&mature_date=\"+val4+\"&finance_no=\"+val1+\"\";");
			out.println("}");
			out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");	
			
		 			
			out.println("</Script>");
			out.println("<body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"makeRequestNew(),get_sys_date();\">");	
			//out.println("<body onload=\"makeRequestNew()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"set_screen(),load_roll_value_1()\">");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - ODI Letter - Letter Generation</td>"); 
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
				out.println("<td width='20%'  ID=VDATE>Date</td>");
				out.println("<td width='*%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				
				out.println("<tr class=tr_input>");  
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCENO' class=div_input>Client No, Name or Finance No  *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' value=\"\" type='text' name='TXT_CLIENT' maxlength='15' size='15' onblur=\"\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCENO' value=\"Search\" onClick=\"help_button_3()\"></td>"); // SJ 23/02/2011
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCENO' value=\"Search\" onClick=\"makeRequest()\"></td>"); 
				
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
			else if(m_chksql.equals("generate_letter")){//normal_ter_le_pur_hire
				int i=0;		
				String m_finance_no = req.getParameter("finance_no");
				String m_mature_date= req.getParameter("mature_date");
				String m_type  = req.getParameter("type");
				String m_print = req.getParameter("print");	
				String m_odi_date = req.getParameter("odi_date");

				
		
													
													
													
																									
				rs7=stmt7.executeQuery("SELECT A.CLIENT_CODE, "+
										//out.println("SELECT A.CLIENT_CODE, "+
										"	B.FULL_NAME, "+
										"	A.FINANCE_NO, "+ 
										"	NVL(TO_CHAR(B.ADDRESS1),' '), "+
										"	NVL(TO_CHAR(B.ADDRESS2),' '), "+
										"	NVL(TO_CHAR( "+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),' ')  "+
										" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+m_schema_name+".AF_CO_MAS_CLIENT B "+
										" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
										" AND APPLICATION_STATUS='ACTIVATED' "+
										" AND A.FINANCE_NO = '"+m_finance_no+"' "+
										
										"");
													
													
												
				String client_address = "";
				String client_address2 = "";
				String client_city = "";
				String vehicle_equ_no = "";
				String finance_num="";
				String termin_no ="";
				String cli_type_1="";
				
				boolean more7 = rs7.next();
				if(more7){
					client_address = rs7.getString(4);
					client_address2 = rs7.getString(5);
				 	client_name    = rs7.getString(2);
					client_city		=	rs7.getString(6);
					//vehicle_equ_no = rs7.getString(3);
					finance_num = rs7.getString(3);
					
					
				}
				//out.println("SELECT " +
				rs8 = stmt8.executeQuery (	"SELECT " +
											""+m_schema_name+".AF_CO_GET_ODI_BAL(A.FINANCE_NO,'"+m_odi_date+"') "+
											"FROM " +
											""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
											"WHERE A.FINANCE_NO='"+m_finance_no+"'");
				String due_amount=" ";
				boolean more8 = rs8.next();
				if(more8){
					due_amount =nf.format(rs8.getDouble(1));
				 						
				}
				
				
				  out.println("<html><head>"); 
					out.println("<title>ODI Letter</title></head>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				  out.println("<script>");
					
										
					out.println("function save_data(){");						
					out.println("window.location.href = \""+m_class_url+"/"+m_fschema_name+"Termination_Letters_Generation?chksql=normal_term_print&odi_date="+m_odi_date+"&type="+m_type+"&mature_date="+m_mature_date+"&finance_no="+m_finance_no+" \";");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
					out.println("}");	
						
					out.println("function add_bttn(){");
						if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"window.print();\"></td></tr>';"); 
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
					///<td width='200' class='rep-body'valign='top' >Name & Address of the Customer  :</td> //Comment By Susitha
					out.println("<tr><td> "+client_name+",<br>"+client_address+",<br>"+client_address2+",<BR>"+client_city+".</td></tr>");
					
					out.println("</table>");	
				
					out.println("<br>");
										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body' >Dear Sir/Madam:</td></tr>");
					out.println("</table>");
					out.println("<br>");
	//========================================== Add By Indika ===================================================
	
				
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='70%' class='rep-body' ><U>RE: Lease Agreement # </U>: "+finance_num+"</td>");
					out.println("<td width='2%' class='rep-body' ></td>");
					out.println("<td width='*%' class='rep-body' > </td></tr>");
					
					out.println("</table>");
	//========================================= End By Indika ========================================================
					out.println("<br><br>");
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
					
					String data="This is to inform you that the above lease agreement has an overdue interest of Rs "+due_amount+" as at "+m_odi_date+", which remains unsettled.";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");			
					
					data="We would appreciate your payment of this amount within the next 7 days. In the event you have already made the above payment, we thank you for the same and kindly request you to ignore this notification. ";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					
					
					
					data="If you need further clarification please call us Monday through Friday 8.30 a.m. to 5.00 p.m. on 011 7577577. You can also reach us by email at<U><B><a href='mailto:e-leasing@orient.lk'> e-leasing@orient.lk </b></u></a>";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					
					data="Yours in Service. ";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					
					out.println("<br>");
					out.println("<br>");	
					
					data="ORIENT FINANCILAL SERVICES CORPORATION LTD";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' ><b>"+data+"<b></td>");
					out.println("</tr></table>");
					
					out.println("<br>");
					out.println("<br>");	
					
					
					data="This is a computer generated letter and does not require a signature. ";  										
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					
					
						
					
					
				
			///////////////////////////////////////////////////////////////////////////////		
	
					out.println("<br>");
					out.println("<br>");	
					out.println("<br>");
					out.println("<style type=\"text/css\"><!--.style1 {font-family: aKandyNew;color:#000000;}--></style>");																
	
				 	data="mhWmy`@zÎ,";
					
					out.println("<br>");						
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr>");
					out.println("<td width='*%'  style='text-align:justify' ><div class='style1'>"+data+"</div></td>");
					out.println("</tr></table>");
					
			 		
					out.println("<br>");
					out.println("<br>");	
					out.println("<br>");
					
					data="ihw sqhN qd @p`Å Ëql Øn 7K a#ólw @gv` avsN krn @lsW, @M vn ìt em qd @p`Å Ëql @gv` a#WnM @mm ÄÓy @n`slk` hÝn @ls k`r#ªkv iLl` âçÉ.";						
					
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr>");
					out.println("<td width='*%'  style='text-align:justify' ><div class='style1'>"+data+"</div></td>");
					out.println("</tr></table>");	
					
					
					out.println("<br>");
					out.println("<br>");	
					out.println("<br>");
					
					data="@myt ì|v`ã,";	
					
					out.println("<table border='0' width='90%' class='table'>");  		
					out.println("<tr>");
					out.println("<td width='*%'  style='text-align:justify' ><div class='style1'>"+data+"</div></td>");
					out.println("</tr></table>");	
					
					String data1="ã";
					data="s oÝyNT fûn$N;L sRìsS @k~p@R;N ";		
					
					out.println("<table border='0' width='90%' class='table'>");  		
					out.println("<tr>");
					out.println("<td width='*%'  style='text-align:justify'; ><span class='style1'>"+data1+"</span>/<span class='style1'>"+data+"</span></td>");
					out.println("</tr></table>");		
								
					
					out.println("<br><br><br><br>");
					out.println("<br>");
					out.println("</font></p></blockquote>");										
			  	out.println("</form></body></html>");
			}	
			else if(m_chksql.equals("odi_details")){			
							
				String m_client=req.getParameter("client").trim();
				String m_odi_date=req.getParameter("odi_date").trim();
				String m_print_status="";
				/*rs2 =stmt2.executeQuery(" select NVL(TERMINATION_NO,'-'), "+
		       											" NVL(TO_CHAR(TER_TYPE_ENT_DATE),'-'), "+
		      											" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
																" TRANSACTION_TYPE "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																" WHERE UPPER(FINANCE_NO)         = UPPER('"+m_finance_no+"') "+
																" AND   UPPER(APPLICATION_STATUS) = UPPER('"+m_acti_termi+"') ");*/
				rs2=stmt2.executeQuery("SELECT A.CLIENT_CODE, "+
									//out.println("SELECT A.CLIENT_CODE, "+
										"B.FULL_NAME,"+
										"A.FINANCE_NO,"+ 
										"TO_CHAR(B.ADDRESS1||' '||B.ADDRESS2)"+
										
										"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+m_schema_name+".AF_CO_MAS_CLIENT B "+
										"WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
										" AND APPLICATION_STATUS='ACTIVATED' "+
										" AND   TO_DATE(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_odi_date+"','DD-MM-YYYY') "+
										
										"AND( (UPPER(A.CLIENT_CODE) like UPPER('%"+m_client+"%')) OR  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_client+"%')) OR  (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_client+"%'))) "+ 
										"");
				
				out.println("<br>");			
						
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"20%\" align='left'>Client Code</td> ");
				out.println("<td width=\"20%\" align='left'>Client Name</td> ");
				out.println("<td width=\"15%\" align='left'>Agreement No</td> ");
				out.println("<td width=\"10%\" align='center'>Address</td> ");
				out.println("<td width=\"5%\" align='center'></td> ");
				
				
				out.println("</tr>");
				
				boolean more2 = rs2.next();
				while(more2){
					
					
					out.println("<tr>");
					out.println("<td width=\"10%\" align='left'>"+rs2.getString(1)+"</td> ");
					out.println("<td width=\"20%\" align='left'>"+rs2.getString(2)+"</td> ");
					out.println("<td width=\"10%\" align='left'>"+rs2.getString(3)+"</td> ");
					out.println("<td width=\"30%\" align='left'>"+rs2.getString(4)+"</td> ");
					out.println("<td width='10%'  align='center'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Generate\" onclick=\"Generate_Letter('LETTER',m_odi_date,'"+rs2.getString(1)+"','"+rs2.getString(1)+"','"+rs2.getString(3)+"')\" style=\"{width:100px}\"></td>");
					
					rs3 =stmt3.executeQuery(" SELECT A.FINANCE_NO ,"+//1
																	" A.PRINT_STATUS "+	//2										      
															  " FROM  "+m_schema_name+".AF_CR_TERMI_LETTER_PRINT A "+
															  " WHERE A.FINANCE_NO ='"+rs2.getString(1)+"' ");
																
				
				more3 = rs3.next();
				if(more3){
				  m_print_status = rs3.getString(2);
				  }			
										
					if(m_print_status.equals("Y")){						
					//out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk checked></td> ");
					}else{
					//out.println("<td width=\"5%\" align='center'><input type=checkbox name=print_chk ></td> ");
					}
					out.println("</tr>");
					more2=rs2.next();
					
				}
			
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
