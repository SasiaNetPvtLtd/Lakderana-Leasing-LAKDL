//--
//SCREEN NAME : CREDIT VERIFICATION MAIN
//MODIFIED BY : DELANJALI
//DATE/TIME   : 
//NOTES:



import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_CR_PRO_Credit_Recommend_app extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out =  null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt10;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
	
	public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs10,rs11;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 		
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String m_username 						= "AA";//m_sn_methods.username;
			String data="";
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt10=conn.createStatement();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);			
			
			String m_contract_no          = req.getParameter("contract_no");
			String m_applicaton_no        = req.getParameter("applicaton_no");
			String m_insurance_done       = req.getParameter("insurance_done");
			String m_client       = req.getParameter("clientcode");//added miilnda 2014-*02-007
			String m_Hid_scr_name="";
			
			/* add by waruna 2012-04-23 lakdl*/
			if(m_insurance_done.equals("Licensee")){
				m_insurance_done ="Company";
			}
			
			
			String m_pre_stage=req.getParameter("pre");
			String m_app_stage=req.getParameter("appro");
			String m_pre_stage1=req.getParameter("qry");
			
			String m_type="";
			String m_mk_officer="",m_broker="",m_branch_desc="",m_transaction_type="",m_contract="";
			String m_return_status="" , m_return_comment="",m_client_code="" ; //added by nuwan de silva on 04-10-07
			
			//added by nuwan de silva on 19-10-07---------------
			int m_count_gur=0;
			int m_count_as=0;
			int m_count_pr=0;
			int m_count_pi=0;
			int m_count_vl=0;
			
			
			m_type=req.getParameter("type");
			
			//added by nuwan de silva on 04-10-07=======================
			rs = stmt.executeQuery(" SELECT "+		
				" STATUS,NVL(REMARK,'-')  FROM "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				" WHERE APPLICATION_NO='"+m_applicaton_no+"' "+
				" AND   ENT_DATE =( "+
				" SELECT  MAX(ENT_DATE) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
				" WHERE APPLICATION_NO='"+m_applicaton_no+"') "); 
			
			boolean more=rs.next();
			
			if(more){
				m_return_status   = rs.getString(1);
				m_return_comment  = rs.getString(2);
			}
			//m_return_comment="asdads'asdas'adad";
			rs.close();
			//==========================================================
			
			rs = stmt.executeQuery(" SELECT "+
				"  A.INQUARY_NO, "+
				"  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-'), "+
				//"  NVL(B.LEAD_SOURCE_NAME,'-'), "+ 
				//"  NVL(A.LEAD_SOURCE_NAME,'-'), "+ //CHANGE BY WARUNA 2012-04-19
				" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE,  "+
				"  NVL(A.BRANCH_CODE,'-') BRANCH_CODE,  "+
				"  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') BRANCH_DESC ,"+
				"  NVL("+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),'-') TRANSACTION_TYPE  "+ // added by nuwan de silva on 10-12-2007
				"  ,A.CLIENT_CODE, "+
				" NVL(A.FINANCE_NO,'-') FINANCE_NO "+ // 8 ADDED MILINDA 2014-05-06
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
				" WHERE A.INQUARY_NO=B.INQUIRY_CODE "+
				" AND A.APPLICATION_NO='"+m_applicaton_no+"' ");
			
			if(rs.next()){
				m_mk_officer  					= rs.getString(2);
				m_broker      					= rs.getString(3);
				m_branch_desc 					= rs.getString(5); //added by nuwan  de silva 25-06-07
				m_transaction_type      = rs.getString(6);
				m_client_code           = rs.getString(7);
				m_contract                      = rs.getString(8);//ADDED MILINDA 2014-05-06
				
			}
			
			
			if(m_type==null){
				m_type="";
			}
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			
			if(m_pre_stage.equals("ENT_CON")){
				out.println("<TITLE>Credit Process - Verification</TITLE>"); 
			}
			else if(m_pre_stage.equals("V-APP")){
				out.println("<TITLE>Credit Process - Recommendation</TITLE>"); 
			}
			else if(m_pre_stage.equals("VERIFY-M")){
				out.println("<TITLE>Credit Process - Approval 2</TITLE>"); 
			}	
			
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.
			
			out.println("var array_follow_up_no=new Array();");
			out.println("var array_condition=new Array();");
			
			
			out.println("var vec_len=0 ;");
			out.println("var ret_sts=\"\" ;");
			out.println("var m_app_status='"+m_pre_stage+"'");
			out.println("var m_return_status='"+m_return_status+"'"); //added by nuwan de silva on 04-10-07
			
			
			out.println("function assign_status(obj,row) {");
			out.println("if(obj=='Btn_Applicant') {");
			out.println("document.Form1.hid_chk_status.value='AD'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_App_Ver_1?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+'&data_val1="+m_pre_stage+"&data_val2=ACTIVATED&client_code='+document.Form1.Hid_Client_Code.value+''");
			out.println("popupwin=window.open(m_url,'displayWindow3','left=0,top=0,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes')");
			out.println("}");
			out.println("else if(obj=='Btn_Guarantor_Det') {");
			out.println("document.Form1.hid_chk_status.value='GR'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Guarantor_Ver_1?data_val1="+m_pre_stage+"&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=133,width=920,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			
			out.println("else if(obj=='Btn_Asset_Det') {");
			//out.println("alert('agl'+row)");
			out.println("document.Form1.hid_chk_status.value='AS'");
			out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?row='+row+'&data_val1="+m_pre_stage+"&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&screen=G&my_screen_name=CV';"); 
			out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=133,width=920,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			out.println("else if(obj=='Btn_Invoice_Det') {");
			out.println("document.Form1.hid_chk_status.value='IN'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Proforma_Invoice_Verifi_1?data_val1="+m_pre_stage+"&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=133,width=920,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			out.println("else if(obj=='Btn_Valuation_Det') {");
			out.println("document.Form1.hid_chk_status.value='VL'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Valuation_Veri_1?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=133,width=920,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			
			
			// added by udara on 14-03-2013
			out.println("else if(obj=='Btn_Security_Det') {");
			out.println("document.Form1.hid_chk_status.value='SD'");
			out.println("   m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities_cv?chksql=main_page&APP_NO='+'"+m_applicaton_no+"';"); 
			out.println("       popupwin=window.open(m_url,'displayWindow2','left=60,top=133,width=920,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			// end by udara on 14-03-2013
			
			out.println("else if(obj=='Btn_Document_Req') {");
			
			out.println("document.Form1.hid_chk_status.value='DR'");
			out.println("if(m_app_status=='ENT_CON'){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&CORE_APP_CODE='+document.Form1.Hid_co_app_Code.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&Hid_scr_name='+document.Form1.Hid_scr_name_verification.value+'&TXT_TYPE=&CLIENT_CODE='+document.Form1.Hid_Client_Code.value+'&CORE_APP_CODE=&ac_status=Y&hid_records=&scr_approv=N';"); 
			out.println("}");
			out.println("if(m_app_status=='VERIFY-M'){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&CORE_APP_CODE='+document.Form1.Hid_co_app_Code.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&Hid_scr_name='+document.Form1.Hid_scr_name_approv2.value+'&TXT_TYPE=&CLIENT_CODE='+document.Form1.Hid_Client_Code.value+'&CORE_APP_CODE=&ac_status=Y&hid_records=A&scr_approv=Y';"); 
			out.println("}");
			out.println("if(m_app_status=='V-APP'){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&CORE_APP_CODE='+document.Form1.Hid_co_app_Code.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&Hid_scr_name='+document.Form1.Hid_scr_name_approv1.value+'&TXT_TYPE=&CLIENT_CODE='+document.Form1.Hid_Client_Code.value+'&CORE_APP_CODE=&ac_status=Y&hid_records=A&scr_approv=Y';"); 
			out.println("}");
			out.println("popupwin=window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			out.println("else if(obj=='Btn_approve') {");
			out.println("document.Form1.hid_chk_status.value='AV'");
			
			//modifed by nuwan de silva 13-06-07=====================
			out.println("if(m_app_status=='ENT_CON'){");
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_verification_approval?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_verification.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_verification.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println(" window.location.href=m_url;");   // added by ashini 
			
			out.println("}");
			
			out.println("if(m_app_status=='VERIFY-M'){"); 
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_approval?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_approv2.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_approv2.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println(" window.location.href=m_url;");   // added by ashini 
			out.println("}");
			
			out.println("if(m_app_status=='V-APP'){"); 
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_approval?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_approv1.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_approv1.value+'&applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
			out.println(" window.location.href=m_url;");  // added by ashini 
			out.println("}");
			//===========================================================
			//out.println("window.close();"); //Added by Nuwan De Silva 30-05-07==========
			//Modified by Mahela on 26-07-2007
			//out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=10,width=800,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');");  commented by ashini 
			//out.println("		window.location.href=m_url;"); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Verifi_app_1?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&applicaton_no="+m_applicaton_no+"';"); 
			out.println("}");
			out.println("}");
			
			//added milinda 2014-02-07
			out.println("	function show_transaction_info(m_client_code,m_finance_no){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			out.println("    window.open(m_url); ");
			out.println("	}");
			//end 
			
			out.println("function disable_butttons(){	");
			out.println("document.Form1.Btn_Applicant_Det.disabled=true;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=true;");
			out.println("document.Form1.Btn_Asset_Det.disabled=true;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=true;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=true;");
			out.println("document.Form1.Btn_Document_Req.disabled=true;");
			
			out.println("}"); 
			
			out.println("function enable_butttons(){	");
			out.println("document.Form1.Btn_approve.disabled=false;");
			out.println("document.Form1.Btn_Applicant_Det.disabled=false;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=false;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=false;");
			out.println("document.Form1.Btn_Document_Req.disabled=false;");
			out.println("document.Form1.Btn_Security_Det.disabled=false;"); // added by udara 19-11-2014
			
			/*out.println("document.Form1.Btn_approve.disabled=false;"); 
			out.println("document.Form1.Btn_Applicant_Det.disabled=false;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=false;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=false;");
			out.println("document.Form1.Btn_Document_Req.disabled=false;");*/
			out.println("}"); 
			
			out.println("function enable_butttons_new(){	");
			out.println("document.Form1.Btn_approve.disabled=true;"); 
			out.println("document.Form1.Btn_Applicant_Det.disabled=true;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=true;");
			out.println("document.Form1.Btn_Asset_Det.disabled=true;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=true;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=true;");
			out.println("document.Form1.Btn_Document_Req.disabled=false;");
			out.println("}"); 					
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Recommend_app?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&applicaton_no="+m_applicaton_no+"';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Recommend_app?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&applicaton_no="+m_applicaton_no+"';"); 
			out.println("}"); 
			
			
			out.println("function load_roll_value(m_val){"); 
			
			if(m_pre_stage.equals("ENT_CON")){
				
				out.println("help_box.innerHTML=\" Credit Process - Verification - \"+m_val;"); 
				//out.println("enable_butttons()");
			}
			else if(m_pre_stage.equals("VERIFY-M")){
				out.println("help_box.innerHTML=\" Credit Process - Approval 2- \"+m_val;"); 
				out.println("enable_butttons()");
			}
			else if(m_pre_stage.equals("V-APP")){
				
				out.println("help_box.innerHTML=\" Credit Process - Recomendation- \"+m_val;"); 
				out.println("enable_butttons()");
			}
			out.println("}"); 
			
			
			out.println("function load_roll_out_value(){");
			if(m_pre_stage.equals("ENT_CON")){
				out.println("help_box.innerHTML=\" Credit Process - Verification - \"+document.Form1.hid_status.value;"); 
				//out.println("enable_butttons()");
			}
			else if(m_pre_stage.equals("VERIFY-M")){
				out.println("help_box.innerHTML=\" Credit Process - Approval 2- \"+document.Form1.hid_status.value;"); 
				out.println("enable_butttons()");
				
			}
			else if(m_pre_stage.equals("V-APP")){
				out.println("help_box.innerHTML=\" Credit Process - Recomendation- \"+document.Form1.hid_status.value;"); 
				out.println("enable_butttons()");
				
			}
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){");
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[0] == 'Exit'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("");
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_guarantor(oBj);"); 
			out.println("}");
			out.println("	}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
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
			
			
			out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ACTIVATED@\";"); 
			out.println("    } ");
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.Hid_Client_Code.value=oBj.valout[4];");
			out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			out.println("}"); 
			
			
			out.println("function help_button_guarantor(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println(" Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_guarantor(oBj) {"); 
			out.println("    document.Form1.Hid_Guarantor_Code.value=oBj.valout[3];"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?guarantor_code='+document.Form1.Hid_Guarantor_Code.value+''");
			out.println("window.open(m_url,'displayWindow2','left=0,top=133,width=800,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("}"); 
			
			
			out.println("function makeRequest() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_pop_LAKDL_AF_CR_Val_application_no&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);"); 
			out.println("}");
			
			out.println("function get_vector(data_vec) {");
			out.println("vec_len = data_vec.length ;");
			out.println("if(data_vec.length==0) {");
			out.println("ret_sts='N' ;");
			out.println(" }");
			out.println("else {");
			out.println(" var i=0 ;");
			out.println("document.Form1.Hid_Client_Code.value=data_vec[i+1];");
			out.println("ret_sts='Y' ;");
			out.println(" }");
			out.println("display_msg(ret_sts);");
			
			out.println("if(data_vec.length>0 && document.Form1.hid_st.value==\"T1\" ) {");
			out.println("document.Form1.Hid_Client_Code.value=data_vec[2]");
			out.println("document.Form1.hid_inq.value=data_vec[6]");
			out.println("document.Form1.hid_client_type.value=data_vec[7]");
			out.println("document.Form1.Hid_co_app_Code.value=data_vec[4]");
			out.println(" }");
			out.println("}");
			
			
			out.println("function display_msg(m_sts){"); 
			out.println("if(m_sts=='Y' ) { ");
			out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\" ) {"); 
			out.println("alert('Please Enter the Application No.');"); 
			out.println(" }");
			out.println("else {"); 
			//out.println("document.Form1.Btn_Applicant_Det.disabled=false;");  commented by ashini 
			// added by ashini -------------------------------------------
			out.println("if(m_app_status=='ENT_CON'){");
			
			out.println("document.Form1.Btn_approve.disabled=true;"); 
			out.println("document.Form1.Btn_Applicant_Det.disabled=false;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=false;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=false;");
			out.println("document.Form1.Btn_Document_Req.disabled=true;");
			out.println(" }");
			out.println("else {");
			out.println("document.Form1.Btn_approve.disabled=false;"); 
			out.println("document.Form1.Btn_Applicant_Det.disabled=false;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=false;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=false;");
			out.println("document.Form1.Btn_Document_Req.disabled=false;");
			out.println(" }");
			
			//--------------end ashini---------------------------------------	
			
			
			out.println("  }");
			out.println(" }");
			out.println("}");
			
			out.println("function close_1(){"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			if(m_type.equals("H")){
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Autherization_higher_approval_1?chksql=main_page&pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
			}
			if(m_type.equals("")){
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Recommendation?chksql=main_page&pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
			}
			out.println("}");
			out.println("}");
			//---------------------ADDED BY ASHINI ON 13-09-2007-------------------------------- 
			out.println("function show_client_details(m_client) {"); 
			out.println("show_client(m_client);"); 
			out.println("}"); 
			
			out.println("function show_invoice(m_invoice_no) {"); 
			out.println("show_proforma_invoice_drill(m_invoice_no);"); 
			out.println("}"); 					
			
			out.println("function show_asset_data(m_asset) {"); 
			out.println("show_asset_detail_drill(m_asset);"); 
			out.println("}"); 
			
			out.println("function show_model(m_model) {"); 
			out.println("show_model_details_drill(m_model);"); 
			out.println("}"); 
			
			out.println("function show_sub(m_sub_model) {"); 
			out.println("show_sub_model_details_drill(m_sub_model);"); 
			out.println("}"); 				
			
			out.println("function show_valuation_data(m_val_data) {"); 
			out.println("show_valuation_drill(m_val_data);"); 
			out.println("}"); 
			
			out.println("function show_asset_data(m_asset) {"); 
			out.println("show_asset_detail_drill(m_asset);"); 
			out.println("}"); 	
			
			
			out.println("function load_pricing(obj_prno,objpr_v){	"); 
			out.println("if(obj_prno!=''){ "); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Price?chksql=main_page&app_no="+m_applicaton_no+"&pricing_no=\"+obj_prno+\"\";"); 
			out.println("popupwin=window.open(m_url,'displayWindow4','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}"); 			
			out.println(" }");
			
			
			
			
			out.println("function check_change_A(m_row) {");
			out.println(" var m_line_no =m_row");
			out.println("if(document.Form1.elements['CHK_APV_'+m_line_no].checked==false){");
			out.println(" document.Form1.elements['CHK_APV_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_APV_'+m_line_no].checked==true){");
			out.println(" document.Form1.elements['CHK_APV_'+m_line_no].value=\"YES\";");			
			out.println("}");
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_1.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_APV_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_1.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_APV_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_1.value=\"YES\";");			
			out.println("}");	
			out.println("}");	
			out.println("check_enable_status();");
			
			out.println("}");					
			
			
			out.println("function check_change_B(m_row) {");
			out.println(" var m_line_no =m_row");
			out.println("if(document.Form1.elements['CHK_CGV_'+m_line_no].checked==false){");
			out.println(" document.Form1.elements['CHK_CGV_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_CGV_'+m_line_no].checked==true){");
			out.println(" document.Form1.elements['CHK_CGV_'+m_line_no].value=\"YES\";");			
			out.println("}");
			
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_2.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_CGV_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_2.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_CGV_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_2.value=\"YES\";");			
			out.println("}");	
			out.println("}");	
			out.println("check_enable_status();");
			out.println("}");		
			
			
			
			out.println("function check_change_C(m_row) {");
			out.println(" var m_line_no =m_row");
			//out.println(" alert(document.Form1.elements['CHK_CDA_'+m_line_no].checked);");
			out.println("if(document.Form1.elements['CHK_CDA_'+m_line_no].checked==false){");
			//	out.println(" alert('inside false');");
			out.println(" document.Form1.elements['CHK_CDA_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_CDA_'+m_line_no].checked==true){");
			//	out.println(" alert('inside true');");
			out.println(" document.Form1.elements['CHK_CDA_'+m_line_no].value=\"YES\";");			
			out.println("}");
			
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_3.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_CDA_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_3.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_CDA_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_3.value=\"YES\";");			
			out.println("}");	
			out.println("}");	
			out.println("check_enable_status();");
			out.println("}");					
			
			out.println("function check_change_D(m_row) {");
			out.println(" var m_line_no =m_row");
			out.println("if(document.Form1.elements['CHK_PIV_'+m_line_no].checked==false){");
			out.println(" document.Form1.elements['CHK_PIV_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_PIV_'+m_line_no].checked==true){");
			out.println(" document.Form1.elements['CHK_PIV_'+m_line_no].value=\"YES\";");			
			out.println("}");
			
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_4.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_PIV_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_4.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_PIV_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_4.value=\"YES\";");			
			out.println("}");	
			out.println("}");		
			out.println("check_enable_status();");
			out.println("}");		
			
			out.println("function check_change_E(m_row) {");
			out.println(" var m_line_no =m_row");
			out.println("if(document.Form1.elements['CHK_CVV_'+m_line_no].checked==false){");
			out.println(" document.Form1.elements['CHK_CVV_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_CVV_'+m_line_no].checked==true){");
			out.println(" document.Form1.elements['CHK_CVV_'+m_line_no].value=\"YES\";");			
			out.println("}");
			
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_5.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_CVV_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_5.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_CVV_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_5.value=\"YES\";");			
			out.println("}");	
			out.println("}");				
			out.println("check_enable_status();");
			out.println("}");	
			
			out.println("function check_change_F(m_row) {");
			out.println(" var m_line_no =m_row");
			out.println("if(document.Form1.elements['CHK_DVV_'+m_line_no].checked==false){");
			out.println(" document.Form1.elements['CHK_DVV_'+m_line_no].value=\"NO\";");			
			out.println("}");
			out.println("if(document.Form1.elements['CHK_DVV_'+m_line_no].checked==true){");
			out.println(" document.Form1.elements['CHK_DVV_'+m_line_no].value=\"YES\";");			
			out.println("}");			
			out.println("for(var i=1;i<=(Number(document.Form1.Hid_Count_6.value)-1);i++){"); 
			out.println("if(document.Form1.elements[\"CHK_DVV_\"+i].value=='NO'){");
			out.println(" document.Form1.hid_enab_stus_6.value=\"NO\";");		
			out.println(" break;");		
			out.println("}");		
			out.println("if(document.Form1.elements['CHK_DVV_'+i].value=='YES'){");
			out.println(" document.Form1.hid_enab_stus_6.value=\"YES\";");			
			out.println("}");	
			out.println("}");				
			out.println("check_enable_status();");
			out.println("}");		
			
			
			out.println("function check_enable_status() {");
			
			out.println("if((document.Form1.hid_garant_stus.value=='NO')){ ");				
			out.println(" document.Form1.hid_enab_stus_2.value=\"YES\";");			
			//out.println(" alert('ok');");
			out.println("}");	
			out.println("if((document.Form1.hid_VAL_STATUS.value=='NO')){ ");				
			out.println(" document.Form1.hid_enab_stus_5.value=\"YES\";");		
			out.println("}");				
			out.println("if((document.Form1.hid_VAL_STATUS_1.value=='NO')){ ");				
			out.println(" document.Form1.hid_enab_stus_6.value=\"YES\";");		
			out.println("}");				
			
			
			out.println("if((document.Form1.hid_enab_stus_1.value=='YES') && (document.Form1.hid_enab_stus_2.value=='YES') && (document.Form1.hid_enab_stus_3.value=='YES') && (document.Form1.hid_enab_stus_4.value=='YES') && (document.Form1.hid_enab_stus_5.value=='YES') && (document.Form1.hid_enab_stus_6.value=='YES') ){ ");//&& (document.Form1.hid_enab_stus_6.value=='YES')
			// out.println(" alert('ok');");
			out.println("document.Form1.Btn_Document_Req.disabled=false;");
			
			out.println("}");							
			out.println("else{");
			
			out.println("if(m_app_status=='ENT_CON'){");
			
			out.println("document.Form1.Btn_Document_Req.disabled=true;");
			out.println("document.Form1.Btn_approve.disabled=true;");
			out.println("}");						
			
			out.println("}");						
			out.println("}");						
			
			out.println("function load_guarantor(ctype,client_code,status){	"); 
			out.println("var val = \"\" ;"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?m_client_status='+status+'&count_client='+document.Form1.Hid_Count_1.value+'&count_gur="+m_count_gur+"&count_as="+m_count_as+"&count_pr="+m_count_pr+"&count_vl="+m_count_vl+"&count_gur="+m_count_gur+"&client_type='+ctype+'&inquiry_no='+val+'&screen=G&close_status=Y&client_code='+client_code+'&row=1&fscreen=client_verification'");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}"); 			
			
			
			out.println("function load_guarantor_2(ctype,client_code,status,row){	"); 
			out.println("var val = \"\" ;"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_guarantor_creation?gur_row_no='+row+'&m_client_status='+status+'&client_type='+ctype+'&inquiry_no='+val+'&screen=G&close_status=Y&client_code='+client_code+'&row=1'");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}"); 			
			
			out.println("function load_invoice(app_no,prof_inv_no,row){	"); 
			out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?row='+row+'&APP_NO='+app_no+'&prof_inv_no='+prof_inv_no+'&my_screen_name=CV&row=1'");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}"); 
			
			out.println("function load_valuation(app_no,prof_inv_no,row){	");
			//out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_inspection_and_valuation_report?type=CURR&row_no='+row+'&APP_NO='+app_no+'&valuation_no='+prof_inv_no+'&row=1'");  //comment by Prabash on 16-03-2012
			out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_inspection_and_valuation_report?type=CURR&row_no='+row+'&APP_NO='+app_no+'&my_screen_name=CV&valuation_no='+prof_inv_no+'&row=1'"); //Added by Prabash on 16-03-2012
			out.println("popupwin=window.open(m_url,'displayWindow3','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}"); 
			//========================
			out.println("function load_valuation_prv(app_no,prof_inv_no,row){	");
			out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_inspection_and_valuation_report?&type=PRV&row_no='+row+'&APP_NO='+app_no+'&valuation_no='+prof_inv_no+'&row=1'");
			out.println("popupwin=window.open(m_url,'displayWindow3','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			//====================
			
			
			//---------END MODIFICATION DONE BY ASHINI ON 13-09-2007----------------------------- 
			
			out.println("function display_applicant(){"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type='+document.Form1.hid_client_type.value+'&inquiry_no='+document.Form1.hid_inq.value+'&screen=G&close_status=Y&client_code='+document.Form1.Hid_Client_Code.value+''");
			out.println("window.open(m_url,'displayWindow1','left=0,top=0,width=800,height=900,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("function app_no(){"); 
			
			out.println("document.Form1.TXT_APPLICATION_NO.value='"+m_applicaton_no+"';");
			out.println("document.Form1.TXT_CONTRACT_NUMBER.value='"+m_contract+"';"); //pra
			out.println("document.Form1.TXT_INSURANCE_DONE.value='"+m_insurance_done+"';");
			//if(m_pre_stage.equals("ENT_CON")){
			out.println("document.Form1.TXT_MK_OFFICER.value='"+m_mk_officer+"';");
			out.println("document.Form1.TXT_BROKER.value='"+m_broker+"';");
			out.println("document.Form1.TXT_BRANCH_DESC.value='"+m_branch_desc+"';"); //added by nuwan de silva 25-06-07
			out.println("document.Form1.TXT_TRANSACTION_TYPE.value='"+m_transaction_type+"';"); //added by nuwan de silva 10-12-07
			
			
			out.println("show_return_comment();"); //added by nuwan de silva 04-10-07
			
			//}
			//out.println("document.Form1.Btn_Applicant_Det.disabled=false;");  // commented by ashini 
			// added by ashini -------------------------------------------
			out.println("document.Form1.Btn_approve.disabled=true;"); 
			out.println("document.Form1.Btn_Applicant_Det.disabled=false;");
			out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
			out.println("document.Form1.Btn_Asset_Det.disabled=false;");
			out.println("document.Form1.Btn_Valuation_Det.disabled=false;");
			out.println("document.Form1.Btn_Invoice_Det.disabled=false;");
			out.println("document.Form1.Btn_Document_Req.disabled=true;");
			//--------------end ashini---------------------------------------	
			
			
			out.println("document.Form1.hid_st.value='T1'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_client&data_val1="+m_pre_stage+"&data_val2=ACTIVATED&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);"); 
			out.println("}");	
			
			// added by udara on 14-03-2013
			out.println("function edit_security_details(app_no){"); 
			out.println("   m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities?chksql=main_page&APP_NO='+app_no;"); 
			out.println("   window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1');"); 
			out.println("}");
			// end by udara on 14-03-2013
			
			//added by nuwan de silva on 04-10-07---------
			out.println("function show_return_comment(){"); 
			out.println("if(m_return_status=='RET-VERY-M' || m_return_status=='RET-VE-APP') {");		
			out.println("m_table_return.innerHTML+='<table width=\"100%\" align=\"center\" class=table border=\"0\" ><tr>'+");		
			out.println("													 '<td width=\"20%\" valign=\"top\"  ><b>Return Comment</td>'+"); 
			out.println("													 '<td width=\"30%\"                 ><div id=m_value></div></td>'+"); //<b>"+m_return_comment+"</b>
			out.println("													 '</tr></table>';");		
			out.println("m_value.innerHTML=\""+m_return_comment+"\";");//added by nuwan de silva on 30-11-2007
			out.println("}");		
			out.println("else {");		
			out.println("m_table_return.innerHTML='';");		
			out.println("}");		
			//out.println("m_value.innerHTML=\""+m_return_comment+"\";");
			out.println("}");		
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),app_no()\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_Client_Code' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_Guarantor_Code' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_co_app_Code' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_verification' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_approv1' VALUE=\"AF_CR_CREDIT_RECOMMENDATION\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_approv2' VALUE=\"AF_MK_APP_STATUS_APPROVE_3\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_arr_size' VALUE=\"\">"); 
			out.println("<INPUT TYPE=\"Hidden\" NAME=\"Hid_app_no\" VALUE=\""+m_applicaton_no+"\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_1' VALUE=\"NO\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_2' VALUE=\"NO\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_3' VALUE=\"NO\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_4' VALUE=\"NO\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_5' VALUE=\"NO\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_enab_stus_6' VALUE=\"NO\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_inq' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
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
			
			if(m_pre_stage.equals("ENT_CON")){
				
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Verification</td>"); 
				
			}
			
			if(m_pre_stage.equals("VERIFY-M")){
				
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Approval 2</td>"); 
				
			}
			if(m_pre_stage.equals("V-APP")){
				
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Recomendation</td>"); 
				
			}
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>"); 
			out.println("<table>");
			out.println("</table>");
			
			
			
			double	total_exposure=0,total_prv=0,total_current=0;
			
			// commented by udara 17-05-2017
			/*
			String		Sql_data_exposure_prev="  SELECT "+												
				"	  A.ARREARS,B.NIL,C.ODI "+
				"		FROM  "+
				"		(SELECT "+
				"		NVL(SUM((NET_AMOUNT/TOTAL_AMOUNT)*A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
				"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
				"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
				"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
				"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"') AND "+
				"		A.ACTIVE_STATUS='Y' AND "+
				"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
				"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
				
				"		(SELECT  "+
				"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL  "+
				"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
				"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
				"			A.APPLICATION_NO=B.APPLICATION_NO AND   "+
				"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND  "+
				"		A.INVOICE_NO IS NULL ) B, "+
				
				"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
				"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
				"		WHERE INVOICE_NO IN   "+
				"		(SELECT   "+
				"		INVOICE_NO   "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
				"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
				"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
				"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND "+
				"		A.ACTIVE_STATUS='Y'  "+
				"		) )C        ";
			*/
			
			String Sql_data_exposure_prev = "  "+
				" SELECT 0,0,0 "+
				" FROM DUAL "+
				"  ";
			
			// commented by udara 17-05-2017
			/*
			String			Sql_data_exposure_current="  SELECT "+												
				"	  A.ARREARS,B.NIL,C.ODI "+
				"		FROM  "+
				"		(SELECT "+
				
				"		NVL(SUM((NET_AMOUNT/TOTAL_AMOUNT)*A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
				"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
				"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
				"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
				"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"') AND "+
				"		A.ACTIVE_STATUS='Y' AND "+
				"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
				"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
				
				
				"		(SELECT  "+
				"		NVL(SUM(A.GROSS_AMOUNT),0) NIL  "+
				"		FROM "+m_schema_name+".AF_MK_PRO_PRICING A,  "+
				"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
				"			A.APP_NO=B.APPLICATION_NO AND   "+
				"		UPPER(A.APP_NO) = UPPER('"+m_applicaton_no+"')   "+
				"		) B, "+ 
				
				"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
				"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
				"		WHERE INVOICE_NO IN   "+
				"		(SELECT   "+
				"		INVOICE_NO   "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
				"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
				"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
				"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND "+
				"		A.ACTIVE_STATUS='Y'  "+
				"		) )C        ";
				*/
			
			String			Sql_data_exposure_current= " "+
							" SELECT 0,0,0 "+
							" FROM DUAL "+
							" ";
			
			
			/*out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr >");
			out.println("</tr >"); 
			out.println("<tr >");
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number * </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15'  OnBlur=\"makeRequest(),disable_butttons()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','2','m_help_TXT_APPLICATION_NO','1')\" disabled> </td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INSURANCE'  class=div_input>Insurance Done By </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INSURANCE_DONE' maxlength='15' size='15' disabled></td>"); 
			out.println("</tr>"); 
			
			//if(m_pre_stage.equals("ENT_CON")){
			
			//added by nuwan de silva 25-06-07
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_DESC'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRANCH_DESC' maxlength='15' size='15' disabled></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_MK_OFFICER'  class=div_input>Marketing Officer </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MK_OFFICER' maxlength='15' size='15' style='{width:200}' disabled></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BROKER'  class=div_input>Broker </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BROKER' maxlength='15' size='15' disabled style='{width:200}' ></td>"); 
			out.println("</tr>"); 
			
			//added by nuwan de silva on 10-12-2007 -----------------------------------------------------
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TRANSACTION_TYPE'  class=div_input>Transaction Type</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TRANSACTION_TYPE' maxlength='15' size='15' disabled ></td>"); 
			out.println("</tr>"); 
			
			//}
			out.println("</table>"); 
			
			*/
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"50%\" >");  
			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number * </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15'  OnBlur=\"makeRequest(),disable_butttons()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','2','m_help_TXT_APPLICATION_NO','1')\" disabled> </td>"); 
			out.println("</tr>"); 
			
			//added by Prabash on 14-08-2012----
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_USER'  class=div_input>Contract Number  </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CONTRACT_NUMBER' maxlength='15' size='15' disabled></td>"); 
			out.println("</tr>");
			//----------------------------------
			
			
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_INSURANCE'  class=div_input>Insurance Done By </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_INSURANCE_DONE' maxlength='15' size='15' disabled></td>"); 
			out.println("</tr>"); 
			
			//if(m_pre_stage.equals("ENT_CON")){
			
			//added by nuwan de silva 25-06-07
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_BRANCH_DESC'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_BRANCH_DESC' maxlength='15' size='15' disabled></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_MK_OFFICER'  class=div_input>Marketing Officer </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_MK_OFFICER' maxlength='15' size='15' style='{width:200}' disabled></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_BROKER'  class=div_input>Broker </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_BROKER' maxlength='15' size='15' disabled style='{width:200}' ></td>");
			out.println("<td width='15%' ><DIV id='DIV_TXT_LEAD'  class=div_input>Lead Source Category </DIV></td>"); 
			String type="";
			rs11=stmt7.executeQuery
				("SELECT NVL(LEAD_SOURCE_CATEGORY,'N/A'),NVL(RE_FIN_NO,'N/A') FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO  IN('"+m_applicaton_no+"') ");
			boolean more12=rs11.next();
			type=rs11.getString(1);
			if(type.equals("TEST")){
				type="ReFinance";
			}else{
				type=type;
			}
			while(more12){
				//out.println("<tr>"); 
				if(rs11.getString(1).equals("TEST")){
					//ut.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info()\" id=\"help_box\">"+type+" - ("+rs11.getString(2)+")</td>");
					out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_LEAD' maxlength='15' size='15' VALUE="+type+"  disabled></td>");
					out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_REFIN' maxlength='15' size='15' VALUE="+rs11.getString(2)+" disabled></td>");
				}else{
					//out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info()\" id=\"help_box\">"+rs11.getString(1)+"</td>");
					out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_LEAD' maxlength='15' size='15' VALUE="+type+"  disabled></td>");
				}
				more12=rs11.next();
			}
			out.println("</tr>"); 
			
			//added by nuwan de silva on 10-12-2007 -----------------------------------------------------
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_TRANSACTION_TYPE'  class=div_input>Transaction Type</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_TRANSACTION_TYPE' maxlength='15' size='15' disabled ></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</td>");  
			
			out.println("<td valign=\"top\" width=\"50%\" >");  
			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" bordercolor='lightgrey' cellpadding=\"0\"  style='{display:none;}' >");
			out.println("<tr>");
			out.println("<td ><b>Exposure</td>");
			out.println("<td align='right'><B>Arrears</td>");
			out.println("<td align='right'><B>C/OS</td>");
			out.println("<td align='right'><B>ODI</td>");
			out.println("<td align='right'><B>Total</td>");
			out.println("</tr>");
			
			rs = stmt.executeQuery(Sql_data_exposure_prev);
			more=rs.next();
			
			if(more){
				total_prv=rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3);
				out.println("<tr>");
				out.println("<td><b>Existing</td>");
				out.println("<td align='right' >"+nf.format(rs.getDouble(1))+"</td>");
				out.println("<td align='right'>"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td align='right'>"+nf.format(rs.getDouble(3))+"</td>");
				out.println("<td align='right'>"+nf.format(total_prv)+"</td>");
				out.println("</tr>");
			}
			rs = stmt.executeQuery(Sql_data_exposure_current);
			more=rs.next();
			if(more){
				total_current=rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3);
				out.println("<tr>");
				out.println("<td><b>Current</td>");
				out.println("<td align='right'>"+nf.format(rs.getDouble(1))+"</td>");
				out.println("<td align='right'>"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td align='right'>"+nf.format(rs.getDouble(3))+"</td>");
				out.println("<td align='right'>"+nf.format(total_current)+"</td>");
				out.println("</tr>");
			}
			total_exposure=total_prv+total_current;
			out.println("<tr>");
			out.println("<td><b>Total</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td align='right'>"+nf.format(total_exposure)+"</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</td>");
			out.println("</tr>");  
			out.println("</table>"); 
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='*%' ><DIV id='m_table_return' ></DIV></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			rs10= stmt10.executeQuery (" SELECT  NVL(A.REG_NO,'N/A'),"+m_schema_name+".GET_OTHER_CONTRCT_FOR_MAIN(a.APPLICATION_NO,A.REG_NO) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
				" WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
				"  ");
			int WW=0;
			boolean more11=rs10.next();
			out.println("<table align='left' width='20%' border=\"0\">");
			out.println("<tr>");
			//out.println("<td width='10%' align=\"left\" bgcolor='silver' ><b>Other Contract Numbers with Same Vehical No</td>");
			out.println("<td width='10%' align=\"left\" bgcolor='silver' ><b>Other contract numbers with same vehicle</td>");
			out.println("</tr>");
			//while(rs2.next()){
			while(more11){
				
				out.println("<tr>"); 
				if(!rs10.getString(2).equals("-")){
					out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info()\" id=\"help_box\"><U>"+rs10.getString(2)+"</td>"); 
				}else{
				}
				out.println("</tr>");
				WW=WW+1;
				more11=rs10.next();
			}
			
			
			out.println("</table>"); 
			out.println("</table>"); 
			out.println("</table>"); 
			//ADDED MILINDA 2014-07-23 LEAD SOURCE CATERGORY	
			/*out.println("	<table align='left' width='20%' border=\"0\">");
			out.println("<tr>");
			
			out.println("<td width='20%' align=\"left\" bgcolor='silver'><b>Lead Source Category</td>");
			out.println("</tr>");
			out.println("<tr>");
			String type="";
			rs11=stmt7.executeQuery
				("SELECT NVL(LEAD_SOURCE_CATEGORY,'N/A'),NVL(RE_FIN_NO,'N/A') FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO  IN('"+m_applicaton_no+"') ");
			boolean more12=rs11.next();
			type=rs11.getString(1);
			if(type.equals("TEST")){
				type="Re Finance";
			}else{
				type=type;
			}
			while(more12){
				out.println("<tr>"); 
				if(rs11.getString(1).equals("TEST")){
					out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info()\" id=\"help_box\">"+type+" - ("+rs11.getString(2)+")</td>");
				}else{
					out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info()\" id=\"help_box\">"+rs11.getString(1)+"</td>");
				}
				more12=rs11.next();
			}
			out.println("</tr>"); 
			out.println("</table>");*/
			
			/***************OTHER CONTACTS VIEW*********************/
			//out.println("<br>");				
			rs10= stmt10.executeQuery (" SELECT A.FINANCE_NO,A.APPLICATION_STATUS,A.CLIENT_CODE,A.FINANCE_NO "+
				
				" FROM "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
				" WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client+"') AND A.APPLICATION_STATUS NOT IN('ENTERED')AND   A.APPLICATION_NO NOT IN('"+m_applicaton_no+"') "+
				"  ");
			
			int W=0;
			//int B=1;
			//String m_gar_status="NO";  
			
			
			
			
			boolean more10=rs10.next();
			
			out.println("<table align='left' width='15%' border=\"0\">");
			out.println("<tr>");
			
			//out.println("<td width='10%' align=\"left\" bgcolor='silver'><B>Other Contract No<B></td>"); 
			out.println("<td width='10%' align=\"left\" bgcolor='silver'><B>Same client Other contracts<B></td>");
			out.println("</tr>");
			//while(rs2.next()){
			while(more10){
				
				out.println("<tr>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info('"+rs10.getString(3)+"','"+rs10.getString(4)+"')\" id=\"help_box\"><U>"+rs10.getString(1)+"</td>"); 
				
				out.println("</tr>");
				
				W=W+1;
				//B=B+1;
				
				more10=rs10.next();
				
				
			}
			
			//out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_2\" VALUE='"+B+"'> ");
			
			out.println("</table>");
			out.println("</table>");
			out.println("</table>");
			out.println("</table>");
			
			//added by nuwan de silva on 19-10-07------------
			rs1= stmt1.executeQuery ("SELECT "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('GUR', '"+m_applicaton_no+"') ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS', '"+m_applicaton_no+"') ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PR', '"+m_applicaton_no+"')  ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI', '"+m_applicaton_no+"') , "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL', '"+m_applicaton_no+"')  FROM DUAL ");
			
			if(rs1.next()){
				m_count_gur = rs1.getInt(1);
				m_count_as  = rs1.getInt(2);
				m_count_pr  = rs1.getInt(3);
				m_count_pi  = rs1.getInt(4);
				m_count_vl  = rs1.getInt(5);
			}
			
			rs1.close();
			
			
			//-----------ADDED BY ASHINI ON 11-09-2007---------------------------------------------
			//----------------THIS IS FOR APPLICANT VERIFICATION-----------------------------------
			
			if(m_pre_stage.equals("ENT_CON")){
				
				rs1=stmt1.executeQuery("SELECT DISTINCT APPLICATION_NO,A.CLIENT_CODE,REPLACE("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'&','AND') CLIENT_NAME, "+
					"NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-'),NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-'), NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-'), DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') AF_GUARANTORS ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FA_DEBTOR ,INQUARY_NO,CLIENT_TYPE,TRIM(CO_APPLICANT),"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+ //NVL(NIC_NO,'-')
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS1, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS2, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-')  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) NIC, "+ 
					"(SELECT DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) AF_GUARANTORS, "+
					"(SELECT DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_CLIENT, "+
					"(SELECT DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_DEBTOR, "+
					"(SELECT CLIENT_TYPE FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) CLIENT_TYPE1, "+
					"(SELECT INQUARY_NO FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT A WHERE A.CLIENT_CODE=B.CO_APPLICANT AND UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%')  AND  A.CLIENT_CODE= CO_APPLICANT) INQUIRY_NO1, "+ 
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(B.CLIENT_CODE,APPLICATION_NO), "+//Added by Dineth on 2008-12-17
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(CO_APPLICANT,APPLICATION_NO) "+//Added by Dineth on 2008-12-17
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					"WHERE UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%') "+
					"AND A.CLIENT_CODE=B.CLIENT_CODE "+
					"AND (APPLICATION_STATUS=('ENT_CON')OR APPLICATION_STATUS=('ACTIVATED')) ");	
			}
			if(m_pre_stage.equals("V-APP")){
				rs1=stmt1.executeQuery("SELECT DISTINCT APPLICATION_NO,A.CLIENT_CODE,REPLACE("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'&','AND') CLIENT_NAME, "+
					"NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-'),NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-'), NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-'), DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') AF_GUARANTORS ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FA_DEBTOR ,INQUARY_NO,CLIENT_TYPE,TRIM(CO_APPLICANT),"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+ //NVL(NIC_NO,'-')
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS1, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS2, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-')  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) NIC, "+ 
					"(SELECT DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) AF_GUARANTORS, "+
					"(SELECT DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_CLIENT, "+
					"(SELECT DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_DEBTOR, "+
					"(SELECT CLIENT_TYPE FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) CLIENT_TYPE1, "+
					"(SELECT INQUARY_NO FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT A WHERE A.CLIENT_CODE=B.CO_APPLICANT AND UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%')  AND  A.CLIENT_CODE= CO_APPLICANT) INQUIRY_NO1, "+ 
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(B.CLIENT_CODE,APPLICATION_NO), "+//Added by Dineth on 2008-12-17
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(CO_APPLICANT,APPLICATION_NO) "+//Added by Dineth on 2008-12-17
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					"WHERE UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%') "+
					"AND A.CLIENT_CODE=B.CLIENT_CODE "+
					"AND APPLICATION_STATUS=('V-APP') ");				
				
			}
			
			if(m_pre_stage.equals("VERIFY-M")){
				
				rs1=stmt1.executeQuery("SELECT DISTINCT APPLICATION_NO,A.CLIENT_CODE,REPLACE("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'&','AND') CLIENT_NAME, "+
					"NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-'),NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-'), NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-'), DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') AF_GUARANTORS ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FA_DEBTOR ,INQUARY_NO,CLIENT_TYPE,TRIM(CO_APPLICANT),"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+ //NVL(NIC_NO,'-')
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS1, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS2, "+
					"(SELECT NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-')  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) NIC, "+ 
					"(SELECT DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) AF_GUARANTORS, "+
					"(SELECT DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_CLIENT, "+
					"(SELECT DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_DEBTOR, "+
					"(SELECT CLIENT_TYPE FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) CLIENT_TYPE1, "+
					"(SELECT INQUARY_NO FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT A WHERE A.CLIENT_CODE=B.CO_APPLICANT AND UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%')  AND  A.CLIENT_CODE= CO_APPLICANT) INQUIRY_NO1, "+ 
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(B.CLIENT_CODE,APPLICATION_NO), "+//Added by Dineth on 2008-12-17
					" "+m_schema_name+".AF_CO_GET_CLIENT_STATUS(CO_APPLICANT,APPLICATION_NO) "+//Added by Dineth on 2008-12-17
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					"WHERE UPPER(APPLICATION_NO) LIKE UPPER('"+m_applicaton_no+"%') "+
					"AND A.CLIENT_CODE=B.CLIENT_CODE "+
					"AND APPLICATION_STATUS=('VERIFY-M') ");		
			}
			
			int q=0;
			int A=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			while(rs1.next()){
				
				if(q==0){
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process  - Applicant Verification</td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='10%' align=\"left\">Client Code</td>"); 
					out.println("<td width='20%' align=\"left\">Client Name</td>"); 
					out.println("<td width='19%' align=\"left\">Address1</td>"); 
					out.println("<td width='19%' align=\"left\">Address2</td>"); 
					out.println("<td width='10%' align=\"left\">NIC No/Reg.No</td>"); 
					out.println("<td width='10%' align=\"left\">Client Status</td>"); 
					out.println("<td width='6%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					out.println("</tr >"); 
					
					
				}
				if(q>0 && q%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs1.getString(2)+"')\" id=\"help_box\"><U>"+rs1.getString(2)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs1.getString(2)+"')\"><U>"+rs1.getString(3)+"</td>"); 
				out.println("<td align=\"left\">"+rs1.getString(4)+"</td>"); 
				out.println("<td align=\"left\">"+rs1.getString(5)+"</td><input type=\"Hidden\" name=\"hid_chk1_"+A+"\"	VALUE=\"0\">"); 
				out.println("<td align=\"left\">"+rs1.getString(6)+"</td>"); 
				//out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); //Commented by Dineth on 2008-12-17
				out.println("<td align=\"left\">"+rs1.getString(22)+"</td>");//Added by Dineth on 2008-12-17
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_APV_"+A+"\" value=\"NO\" unchecked onclick=\"check_change_A('"+A+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI value=\"Verify\" onClick=\"load_guarantor('"+rs1.getString(11)+"','"+rs1.getString(2)+"','A')\"></td>");
				out.println("</tr>");
				
				q=q+1;
				A=A+1;
				
				if((!(rs1.getString(12)==null))){
					if((!(rs1.getString(12).equals("null"))) && (!(rs1.getString(12).equals("-"))) && (!(rs1.getString(12).equals(" "))) ){
						
						if(q>0 && q%2==1){
							out.println("<tr class=tr_input1 >");
						}
						else{
							out.println("<tr class=tr_input >");
						}
						out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs1.getString(12)+"')\" id=\"help_box\"><U>"+rs1.getString(12)+"</td>"); 
						out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs1.getString(12)+"')\"><U>"+rs1.getString(13)+"</td>"); 
						out.println("<td align=\"left\">"+rs1.getString(14)+"</td>"); 
						out.println("<td align=\"left\">"+rs1.getString(15)+"</td><input type=\"Hidden\" name=\"hid_chk1_"+A+"\"	VALUE=\"0\">"); 
						out.println("<td align=\"left\">"+rs1.getString(16)+"</td>"); 
						//out.println("<td align=\"left\">"+rs1.getString(17)+"</td>");//Commented by Dineth on 2008-12-17
						out.println("<td align=\"left\">"+rs1.getString(23)+"</td>");//Added by Dineth on 2008-12-17
						out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_APV_"+A+"\" value=\"NO\" unchecked onclick=\"check_change_A('"+A+"')\"></td>"); 
						out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI value=\"Verify\" onClick=\"load_guarantor('"+rs1.getString(20)+"','"+rs1.getString(12)+"','B')\"></td>");
						out.println("</tr>");
						q=q+1;
						A=A+1;
						
					}
				}
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_1\" VALUE='"+A+"'> ");
			out.println("</table >"); 
			
			out.println("<br>");
			
			
			//----------------THIS IS FOR GUARANTOR VERIFICATION-----------------------------------
			out.println("<br>");				
			rs2= stmt2.executeQuery (" SELECT A.APPLICATION_NO,NVL(A.GUARANTOR_CODE,'N/A'),NVL(B.FULL_NAME,'N/A'), "+
				" NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-'),NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-'), NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-'), "+
				" CLIENT_TYPE,INQUARY_NO,DECODE(NVL(AF_CLIENT,'-'),'Y','Client','-') AF_CLIENT ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FA_DEBTOR "+
				" ,"+m_schema_name+".AF_CO_GET_CLIENT_STATUS(A.GUARANTOR_CODE,A.APPLICATION_NO) "+	
				" FROM "+
				" "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A , "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.GUARANTOR_CODE = B.CLIENT_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
				" AND UPPER(A.APPLICATION_NO)=UPPER(C.APPLICATION_NO)");
			
			int R=0;
			int B=1;
			String m_gar_status="NO";  
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			boolean more2=rs2.next();
			if(!more2){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_garant_stus\" VALUE=\"NO\">"); 
			}
			if(more2){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_garant_stus\" VALUE=\"YES\">"); 
			}
			
			//while(rs2.next()){
			while(more2){
				if(R==0){
					
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Guarantor Verification</td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='10%' align=\"left\">Guarantor Code</td>"); 
					out.println("<td width='20%' align=\"left\">Guarantor Name</td>"); 
					out.println("<td width='19%' align=\"left\">Address1</td>"); 
					out.println("<td width='19%' align=\"left\">Address2</td>"); 
					out.println("<td width='10%' align=\"left\">NIC No/Reg.No</td>"); 
					out.println("<td width='10%' align=\"left\">Client Status</td>"); 
					out.println("<td width='6%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					
					out.println("</tr >"); 
				}
				if(R>0 && R%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs2.getString(2)+"')\" id=\"help_box\"><U>"+rs2.getString(2)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('"+rs2.getString(2)+"')\"><U>"+rs2.getString(3)+"</td>"); 
				out.println("<td align=\"left\">"+rs2.getString(4)+"</td>"); 
				out.println("<td align=\"left\">"+rs2.getString(5)+"</td>"); 
				out.println("<td align=\"left\">"+rs2.getString(6)+"</td>"); 
				//out.println("<td align=\"left\">"+rs2.getString(9)+"</td>"); //9 Commented by Dineth on 2009-01-16
				out.println("<td align=\"left\">"+rs2.getString(12)+"</td>");//Added by Dineth on 2009-01-16
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_CGV_"+B+"\" value=\"NO\" unchecked onclick=\"check_change_B('"+B+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_2 value=\"Verify\" onClick=\"load_guarantor_2('"+rs2.getString(7)+"','"+rs2.getString(2)+"','C','"+B+"')\"></td>");
				
				out.println("</tr>");
				
				R=R+1;
				B=B+1;
				
				more2=rs2.next();
				
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_2\" VALUE='"+B+"'> ");
			out.println("</table >"); 
			
			
			
			out.println("<br>");
			
			//---------------------THIS IS FOR VERYFY ASSET----------------------------------------------------------------------
			out.println("<br>");		
			
			rs3= stmt3.executeQuery (" SELECT "+
				"				          A.ASSET_ID, "+//1
				"									B.MAKE_CODE, "+//2
				"									INITCAP(B.MAKE_DESC), "+//3
				"									A.MODEL_CODE, "+//4
				"									INITCAP(C.DESCRIPTION), '','', "+ //5
				//"									A.SUB_MODEL_CODE, "+//6
				//"									INITCAP(D.DESCRIPTION), "+//7
				"									A.STATUS, "+//8
				"									A.COST, "+ //9
				"									A.PURPOSE, "+ //10
				"                 A.QTY,  "+//11
				"                 E.DESCRIPTION , "+//12
				"                 D.YEAR_OF_MANUFACTURE  "+//13
				
				"					FROM  "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
				"								"+m_schema_name+".AF_CO_MAS_MAKE B, "+
				"								"+m_schema_name+".AF_CO_MAS_MODEL C, "+
				"								"+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+ 
				"               " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY E "+
				
				
				"					WHERE A.MODEL_CODE=C.MODEL_CODE AND "+
				"								D.SUB_CODE(+)=A.SUB_MODEL_CODE AND "+ 
				"								C.MAKE_CODE=B.MAKE_CODE AND "+
				"								UPPER(A.APPLICATION_NO) =UPPER('"+m_applicaton_no+"') AND "+
				"	              C.ITEM_SUB_CAT(+)=E.ITEM_SUB_CAT AND "+
				"								A.ACTIVE_STATUS='Y' AND A.STATUS <> 'C'  ORDER BY ASSET_ID  ");	
			
			
			
			
			int P=0;
			int C=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			while(rs3.next()){
				
				if(P==0){
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Details of Asset</td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					//out.println("<td width='14%' align=\"left\">Sub Model</td>"); 
					out.println("<td width='10%' align=\"left\">Model</td>"); 
					out.println("<td width='10%' align=\"left\">Make</td>"); 
					out.println("<td width='6%' align=\"left\">Item Sub Category</td>"); 
					out.println("<td width='11%' align=\"left\">Year of Manufacture</td>"); 
					out.println("<td width='10%' align=\"left\">Status</td>"); 
					out.println("<td width='9%' align=\"left\">Purpose</td>"); 
					out.println("<td width='9%' align=\"left\">Asset No</td>"); 
					out.println("<td width='9%' align=\"left\">Qty</td>"); 
					out.println("<td width='6%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					
					out.println("</tr >"); 
				}
				if(P>0 && P%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				
				//out.println("<td align=\"left\">"+rs3.getString(7)+"</td>"); 
				out.println("<td align=\"left\">"+rs3.getString(5)+"</td>"); 
				out.println("<td align=\"left\">"+rs3.getString(3)+"</td>"); 
				out.println("<td align=\"left\">"+rs3.getString(12)+"</td>"); 
				out.println("<td align=\"left\">"+rs3.getString(13)+"</td>"); 
				if(rs3.getString(8).equals("N")){
					out.println("<td align=\"left\">New</td>"); 
				}
				if(rs3.getString(8).equals("R")){
					out.println("<td align=\"left\">Re-Conditioned</td>"); 
				}
				if(rs3.getString(8).equals("U")){
					out.println("<td align=\"left\">Used</td>"); 
				}
				if(rs3.getString(10).equals("P")){
					out.println("<td align=\"left\">Personal</td>"); 
				}
				if(rs3.getString(10).equals("B")){
					out.println("<td align=\"left\">Business</td>"); 
				}
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data('"+rs3.getString(1)+"')\" id=\"help_box\"><U>"+rs3.getString(1)+"</td>"); 
				//out.println("<td align=\"left\">"+rs3.getString(1)+"</td>"); 
				out.println("<td align=\"left\">"+rs3.getString(11)+"</td>"); 
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_CDA_"+C+"\" value=\"NO\" unchecked onclick=\"check_change_C('"+C+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_7 value=\"Verify\" onClick=\"assign_status('Btn_Asset_Det','"+C+"')\"></td>");
				
				
				out.println("</tr>");
				P=P+1;
				C=C+1;
			}
			
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_3\" VALUE='"+C+"'> ");
			out.println("</table >"); 		
			out.println("<br>");
			
			
			
			
			
			
			
			//----------------THIS IS FOR INVICE/PRICING VERIFICATION-----------------------------------
			out.println("<br>");				
			rs4= stmt4.executeQuery (" SELECT NVL(A.INVOICE_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(A.PRICING_NO,'N/A'), "+
				" NVL(B.DESCRIPTION,'N/A'),'' /* NVL(C.DESCRIPTION,'N/A')*/, "+
				" NVL(A.NET_PRICE,0), NVL(A.VAT,0), NVL(A.TOTAL_AMOUNT,0), "+
				" REPLACE(NVL(A.TO_BE_DELIVERD_TO,'N/A'),'&','-'), NVL(A.VALUE,0), NVL(A.ENGINE_NO,'N/A'), "+
				" NVL(A.CHASSIS_NO,'N/A'), NVL(A.REG_NO,'N/A'), NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'N/A'), "+
				"nvl(b.model_code,'-'),'',/*nvl(c.SUB_CODE,'-'),*/A.APPLICATION_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B /*,"+m_schema_name+". AF_CO_MAS_SUB_MODLE C */"+
				" WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
				" AND A.ACTIVE_STATUS='Y' "+	
				" AND A.MODEL_CODE = B.MODEL_CODE ");
			//             " AND A.SUB_MODEL_CODE = C.SUB_CODE "); 					
			
			int S=0;
			int D=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			while(rs4.next()){
				
				if(S==0){
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Proforma Invoice Verification </td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='14%' align=\"left\">Invoice</td>"); 
					out.println("<td width='10%' align=\"left\">Asset ID</td>"); 
					out.println("<td width='10%' align=\"left\">Pricing No</td>"); 
					out.println("<td width='6%' align=\"left\">Model</td>"); 
					//out.println("<td width='11%' align=\"left\">Sub Model</td>"); 
					out.println("<td width='10%' align=\"left\">Total Amount</td>"); 
					out.println("<td width='9%' align=\"left\">Engine No</td>"); 
					out.println("<td width='9%' align=\"left\">Chassis No</td>"); 
					out.println("<td width='9%' align=\"left\">Reg.No</td>"); 
					out.println("<td width='6%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					out.println("</tr >"); 
				}
				if(S>0 && S%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_invoice('"+rs4.getString(1)+"')\" id=\"help_box\"><U>"+rs4.getString(1)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data('"+rs4.getString(2)+"')\" id=\"help_box\"><U>"+rs4.getString(2)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"load_pricing('"+rs4.getString(3)+"','N')\" id=\"help_box\"><U>"+rs4.getString(3)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model('"+rs4.getString(15)+"')\" id=\"help_box\"><U>"+rs4.getString(4)+"</td>"); 
				//out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub('"+rs4.getString(16)+"')\" id=\"help_box\"><U>"+rs4.getString(16)+"</td>"); 
				out.println("<td align=\"left\">"+nf.format(rs4.getDouble(8))+"</td>"); 
				out.println("<td align=\"left\">"+rs4.getString(11)+"</td>"); 
				out.println("<td align=\"left\">"+rs4.getString(12)+"</td>"); 
				out.println("<td align=\"left\">"+rs4.getString(13)+"</td>"); 
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_PIV_"+D+"\" value=\"NO\" unchecked onclick=\"check_change_D('"+D+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_3 value=\"Verify\" onClick=\"load_invoice('"+rs4.getString(17)+"','"+rs4.getString(1)+"','"+D+"')\"></td>");
				
				out.println("</tr>");
				
				
				S=S+1;
				D=D+1;
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_4\" VALUE='"+D+"'> ");
			out.println("</table >"); 		
			out.println("<br>");				
			
			//----------------THIS IS FOR VERYFY VALUATION-----------------------------------
			out.println("<br>");				
			rs5= stmt5.executeQuery (" SELECT DISTINCT NVL(A.VALUATION_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(B.DESCRIPTION,'N/A'), ''/*NVL(C.DESCRIPTION,'N/A')*/, "+
				" NVL(A.REG_NO,'N/A'), NVL(A.ENGINE_NO,'N/A'), NVL(CHASSIS_NO,'N/A'),NVL(A.COLOUR,'N/A'), NVL(A.VALUE,0),"+
				" NVL(A.REMARKS,'N/A'), NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'N/A'), NVL(TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'),'N/A'), "+
				" NVL(TYPE_OF_BODY,'N/A'),NVL(METER_READING,0),A.MODEL_CODE,A.SUB_MODEL_CODE,NVL(A.VALUER_CODE,'-'),NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'-'),NVL(A.YEAR_OF_MANUFACTURE,0),A.APPLICATION_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_MODEL B /*,"+m_schema_name+". AF_CO_MAS_SUB_MODLE C */ "+
				" WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
				" AND A.ACTIVE_STATUS='Y' "+
				" AND A.MODEL_CODE = B.MODEL_CODE ");
			//" AND A.SUB_MODEL_CODE = C.SUB_CODE "+
			//             " AND A.MODEL_CODE=C.MODEL_CODE ");		
			
			int T=0;
			int E=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			boolean more5=rs5.next();
			if(!more5){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS\" VALUE=\"NO\">"); 
			}
			if(more5){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS\" VALUE=\"YES\">"); 
			}
			
			//while(rs5.next()){
			while(more5){
				
				if(T==0){
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Valuation Verification</td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='9%' align=\"left\">Valuation No</td>"); 
					out.println("<td width='9%' align=\"left\">Asset ID</td>"); 
					out.println("<td width='4%' align=\"left\">Model</td>"); 
					//out.println("<td width='13%' align=\"left\">Sub Model</td>"); 
					out.println("<td width='5%' align=\"left\">Reg.No</td>"); 
					out.println("<td width='8%' align=\"left\">Engine No</td>"); 
					out.println("<td width='7%' align=\"left\">Valuation Date</td>"); 
					out.println("<td width='8%' align=\"left\">Valuer Code</td>"); 
					out.println("<td width='8%' align=\"left\">Year Of Manufacture</td>"); 
					out.println("<td width='8%' align=\"left\">Date Of Reg</td>"); 
					out.println("<td width='5%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					out.println("</tr >"); 
					
				}
				if(T>0 && T%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_data('"+rs5.getString(1)+"')\" id=\"help_box\"><U>"+rs5.getString(1)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data('"+rs5.getString(2)+"')\" id=\"help_box\"><U>"+rs5.getString(2)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model('"+rs5.getString(15)+"')\" id=\"help_box\"><U>"+rs5.getString(3)+"</td>"); 
				//out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub('"+rs5.getString(16)+"')\" id=\"help_box\"><U>"+rs5.getString(4)+"</td>"); 
				out.println("<td align=\"left\">"+rs5.getString(5)+" </td>"); 
				out.println("<td align=\"left\">"+rs5.getString(6)+"</td>"); 
				out.println("<td align=\"left\">"+rs5.getString(12)+"</td>"); 
				out.println("<td align=\"left\">"+rs5.getString(17)+"</td>"); 
				out.println("<td align=\"left\">"+rs5.getString(19)+"</td>"); 
				out.println("<td align=\"left\">"+rs5.getString(18)+"</td>"); 
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_CVV_"+E+"\" value=\"NO\" unchecked onclick=\"check_change_E('"+E+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_4 value=\"Verify\" onClick=\"load_valuation('"+rs5.getString(20)+"','"+rs5.getString(1)+"','"+E+"')\"></td>");
				out.println("</tr>");
				
				T=T+1;
				E=E+1;
				
				more5=rs5.next();
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_5\" VALUE='"+E+"'> ");
			
			out.println("</table >"); 
			
			
			
			
			
			out.println("<br>");				
			
			
			
			//------------end modifictions done by ashini on 11-09-2007----------------------------					
			
			//================Added By Sandun on 10-12-2008===================================================
			
			out.println("<br>");	
			/*
		rs6= stmt6.executeQuery (" SELECT DISTINCT NVL(A.VALUATION_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(B.DESCRIPTION,'N/A'), '', "+
								" NVL(A.REG_NO,'N/A'), NVL(A.ENGINE_NO,'N/A'), NVL(CHASSIS_NO,'N/A'),NVL(A.COLOUR,'N/A'), NVL(A.VALUE,0),"+
								" NVL(A.REMARKS,'N/A'), NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'N/A'), NVL(TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'),'N/A'), "+
								" NVL(TYPE_OF_BODY,'N/A'),NVL(METER_READING,0),A.MODEL_CODE,A.SUB_MODEL_CODE,NVL(A.VALUER_CODE,'-'),NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'-'),NVL(A.YEAR_OF_MANUFACTURE,0),A.APPLICATION_NO "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_BK A,"+m_schema_name+".AF_CO_MAS_MODEL B  "+
								" WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
															" AND A.ACTIVE_STATUS='Y' "+
								" AND A.MODEL_CODE = B.MODEL_CODE ");
															*/
			
			rs6= stmt6.executeQuery (" SELECT DISTINCT NVL(A.VALUATION_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(B.DESCRIPTION,'N/A'), ''/*NVL(C.DESCRIPTION,'N/A')*/,  "+
				" NVL(A.REG_NO,'N/A'), NVL(A.ENGINE_NO,'N/A'), NVL(A.CHASSIS_NO,'N/A'),NVL(A.COLOUR,'N/A'), NVL(A.VALUE,0), "+
				" NVL(A.REMARKS,'N/A'), NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'N/A'), NVL(TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'),'N/A'),  "+
				" NVL(TYPE_OF_BODY,'N/A'),NVL(METER_READING,0),A.MODEL_CODE,A.SUB_MODEL_CODE,NVL(A.VALUER_CODE,'-'),NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'-'),NVL(A.YEAR_OF_MANUFACTURE,0),A.APPLICATION_NO  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_MODEL B , "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
				" WHERE A.APPLICATION_NO=C.APPLICATION_NO "+
				" AND  C.ACTIVE_STATUS='T' "+
				" AND UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"')  "+
				" AND A.ACTIVE_STATUS='Y'  "+
				" AND A.MODEL_CODE = B.MODEL_CODE  ");
			
			
			int J=0;
			int K=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			
			boolean more6=rs6.next();
			if(!more6){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS_1\" VALUE=\"NO\">"); 
			}
			if(more6){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS_1\" VALUE=\"YES\">"); 
			}
			
			
			while(more6){
				
				if(J==0){
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Previouse Valuation Verification</td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='9%' align=\"left\">Valuation No</td>"); 
					out.println("<td width='9%' align=\"left\">Asset ID</td>"); 
					out.println("<td width='4%' align=\"left\">Model</td>"); 
					out.println("<td width='5%' align=\"left\">Reg.No</td>"); 
					out.println("<td width='8%' align=\"left\">Engine No</td>"); 
					out.println("<td width='7%' align=\"left\">Valuation Date</td>"); 
					out.println("<td width='8%' align=\"left\">Valuer Code</td>"); 
					out.println("<td width='8%' align=\"left\">Year Of Manufacture</td>"); 
					out.println("<td width='8%' align=\"left\">Date Of Reg</td>"); 
					out.println("<td width='5%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					out.println("</tr >"); 
					
				}
				if(J>0 && J%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_data('"+rs6.getString(1)+"')\" id=\"help_box\"><U>"+rs6.getString(1)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data('"+rs6.getString(2)+"')\" id=\"help_box\"><U>"+rs6.getString(2)+"</td>"); 
				out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model('"+rs6.getString(15)+"')\" id=\"help_box\"><U>"+rs6.getString(3)+"</td>"); 
				out.println("<td align=\"left\">"+rs6.getString(5)+" </td>"); 
				out.println("<td align=\"left\">"+rs6.getString(6)+"</td>"); 
				out.println("<td align=\"left\">"+rs6.getString(12)+"</td>"); 
				out.println("<td align=\"left\">"+rs6.getString(17)+"</td>"); 
				out.println("<td align=\"left\">"+rs6.getString(19)+"</td>"); 
				out.println("<td align=\"left\">"+rs6.getString(18)+"</td>"); 
				out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_DVV_"+K+"\" value=\"NO\" unchecked onclick=\"check_change_F('"+K+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_5 value=\"Verify\" onClick=\"load_valuation_prv('"+rs6.getString(20)+"','"+rs6.getString(1)+"','"+K+"')\"></td>");
				out.println("</tr>");
				
				J=J+1;
				K=K+1;
				
				more6=rs6.next();
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_6\" VALUE='"+K+"'> ");
			
			out.println("</table >"); 
			
			
			
			
			
			out.println("<br>");	
			
			
			//===================================================================
			
			// added by udara on 07-01-2013
			
			// ====================== SECURITY DETAILS START ====================================================================================
			
			rs7= stmt7.executeQuery (" "+
				" SELECT  "+
				" VEHICLE_NO, "+    // 1
				" CUSTOMER_NAME, "+ // 2
				" NVL(VEHICLE_TYPE,'-') VEHICLE_TYPE, "+ // 3
				" NVL(MODEL_CODE,'-') MODEL_CODE, "+     // 4
				" NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+ // 5
				" NVL(ENGINE_NO,'-') ENGINE_NO, "+ // 6
				" NVL(VALUE,0) VALUE, "+ // 7
				" DECODE(CONDITION_OF_ASSET,'N','New','R','Recondition'), "+  // 8
				" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ // 9
				" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE  "+
				" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  "+
				" ORDER BY VEHICLE_NO DESC "+
				" ");
			
			
			int L=0;
			int M=1;
			out.println("<table align='center' width='100%' class='table' border=\"0\">");    
			
			boolean more7=rs7.next();
			
			/*
			if(!more7){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS_1\" VALUE=\"NO\">"); 
			}
			if(more7){
				out.println("<INPUT TYPE=HIDDEN NAME=\"hid_VAL_STATUS_1\" VALUE=\"YES\">"); 
			}
			*/
			
			while(more7){
				
				if(L==0){
					
					out.println("<tr><td width='10%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'><b><u>Credit Process - Security Details </td></tr>"); 
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("</table >"); 		
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>"); 
					out.println("<td width='9%' align=\"left\"> Vehicle Number </td>"); 
					out.println("<td width='9%' align=\"left\"> Customer Name </td>"); 
					out.println("<td width='4%' align=\"left\"> Vehicle Type </td>"); 
					out.println("<td width='5%' align=\"left\"> Make and Model </td>"); 
					out.println("<td width='7%' align=\"left\"> Year of Manufacture </td>"); 
					out.println("<td width='8%' align=\"left\"> Engine Number </td>"); 
					out.println("<td width='8%' align=\"left\"> Value </td>"); 
					out.println("<td width='8%' align=\"left\"> Condition of Asset </td>"); 
					//out.println("<td width='5%' align=\"left\">Acknowledge</td>"); 
					out.println("<td width='6%' align=\"left\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></td>"); 
					out.println("</tr >"); 
					
				}
				if(L>0 && L%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				
				out.println("<td align=\"left\">"+rs7.getString(1)+" </td>"); 
				out.println("<td align=\"left\">"+rs7.getString(2)+" </td>"); 
				out.println("<td align=\"left\">"+rs7.getString(3)+" </td>");  
				out.println("<td align=\"left\">"+rs7.getString(4)+" </td>"); 
				out.println("<td align=\"left\">"+rs7.getString(5)+"</td>"); 
				out.println("<td align=\"left\">"+rs7.getString(6)+"</td>"); 
				out.println("<td align=\"right\">"+nf.format(rs7.getDouble(7))+"</td>"); 
				out.println("<td align=\"left\">"+rs7.getString(8)+"</td>"); 
				//out.println("<td align=\"left\"><input  type=\"checkbox\" name=\"CHK_SD_"+M+"\" value=\"NO\" unchecked onclick=\"check_change_F('"+M+"')\"></td>"); 
				out.println("<td align=\"center\"><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI_SECURITY value=\"Verify\" onClick=\"edit_security_details('"+rs7.getString(9)+"');\"></td>");
				out.println("</tr>");
				
				L=L+1;
				M=M+1;
				
				more7=rs7.next();
				
			}
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count_7\" VALUE='"+M+"'> ");
			
			out.println("</table >"); 
			
			
			out.println("<br>");
			
			// ====================== SECURITY DETAILS START ====================================================================================
			
			// added by udara on 07-01-2013
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<br><br><br><br><br><br>"); 
			out.println("<tr><td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr>");  
			out.println("<tr><td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			
			out.println("<table width='50%' class='table' cellpadding='2' border='0'> "); 
			out.println("<tr><td width='15%' align='center'><input type=\"button\" name=\"Btn_Applicant_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Applicant Details\");' onclick='load_screen_status(\"Verify Applicant Details\"),assign_status(\"Btn_Applicant\");' value=\"Verify Applicant\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Guarantor_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Guarantor Details\");' onClick='load_screen_status(\"Verify Guarantor Details\"),assign_status(\"Btn_Guarantor_Det\")' value=\"Verify Guarantor\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Asset_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Asset Details\");' onclick='load_screen_status(\"Verify Asset Details\"),assign_status(\"Btn_Asset_Det\")' value=\"Verify Asset\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Invoice_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Invoice/Pricing Details\");' onClick='load_screen_status(\"Verify Invoice/Pricing Details\"),assign_status(\"Btn_Invoice_Det\")' value=\"Verify Invoice/Pricing\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Valuation_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Valuation Details\");' onClick='load_screen_status(\"Verify Valuation Details\"),assign_status(\"Btn_Valuation_Det\")' value=\"Verify Valuation\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Document_Req\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Document Require Details\");' onClick='load_screen_status(\"Document Require Details\"),assign_status(\"Btn_Document_Req\")' value=\"Documents Required\" Disabled></td>");  
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Security_Det\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Security Details\");' onClick='load_screen_status(\"Security Details\"),assign_status(\"Btn_Security_Det\")' value=\"Security Details\" Disabled ></td>"); // added by udara on 07-01-2013
			out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_approve\" class='mainbut' style='{width:130}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Approve verified Details\");' onClick='load_screen_status(\"Approve verified Details\"),assign_status(\"Btn_approve\")' value=\"Approval Details\" Disabled></td></tr>");  //modified by nuwan de silva on 19-09-07
			out.println("<tr><td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");
			out.println("<br><br><br>");
			out.println("</table>");  	
			
			
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			
			out.println("</body>"); 
			out.println("</html>"); 
			
		}catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			//if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			//if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


