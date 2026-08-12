import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Application_Status_Report_appr extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt2,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs2,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	String m_return_status="N";
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  String m_username =  con_method.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;

							int sel_stage=0;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt1 = conn.createStatement();
			
			
			m_pre_stage=req.getParameter("pre");
			m_app_stage=req.getParameter("appro");
			m_pre_stage1=req.getParameter("qry");
			
			String _m_client_name ="";

			
			m_close=req.getParameter("CLS");

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page1")){
					
					
					   
						  String m_sort_column   = "PRIORITY";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
								
								m_pre_stage=req.getParameter("pre");
								m_app_stage=req.getParameter("appro");
								m_pre_stage1=req.getParameter("qry");
			

							}
							
				
							String  m_opt=req.getParameter("option");
							if	(req.getParameter("option")==null){
							 m_opt="1";
								
								}
		
		
      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
				
				
			out.println("	var m_prev='"+m_pre_stage+"'");
			out.println("	var m_prev1='"+m_pre_stage1+"'");
				
			out.println("	var m_app='"+m_app_stage+"'");
			out.println("	var m_option='"+m_opt+"'");
			out.println("	var m_scr");
			out.println("var ck=1");
			out.println("var b_flag=0;");

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
				
			out.println("function load_roll_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Verifcation \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Verification - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("if('"+m_pre_stage+"'=='V-APP'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Recommendation \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Recommendation - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("}");
				
			out.println("function load_roll_value_1(){"); 
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("var m_val=\"Verfy\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Verification - \"+m_val;"); 
	
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='V-APP'){");
			out.println("var m_val=\"Approve\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Recommendation - \"+m_val;"); 

			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("var m_val=\"Approve\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 - \"+m_val;"); 
			out.println("}");
			out.println("}");

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 


			out.println("function load_data(m_app_no,m_app_sts) {");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
	    out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no=\"+m_app_no;"); 
			out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("  }");
			out.println("}");
			
			out.println("function load_roll_out_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Verification - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='V-APP'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 1 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 2 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("}");

			out.println("function sort_data(m_sort_col) {");
			
			out.println("var m_bk=1");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			
			out.println("if(m_option==1){");
			out.println("m_bk=m_option");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("else if(m_option==2){");
			out.println("m_bk=m_option");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("else if(m_option=='null'){");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("load_interface(m_url,'NORM');");

			out.println("}");
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
		
			
			out.println("function check_app(){ "); 
			out.println("if(!count_chk()){"); 
			out.println("alert(\"Please approve or reject the application \");");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
		  out.println("}"); 
			
			out.println("function count_chk(){ ");
			out.println("count=0;");
			out.println(" m_row = document.Form1.hid_no.value ; ");
			out.println("for(i=0;i<m_row;i++){");
			out.println("  chk=\"chk_quot_\"+i;");
			out.println("  chk_rej=\"chk_reject_\"+i;");
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
			//out.println("alert('"+m_return_status+"');");
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
 			//out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_help_msg() {"); 
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr1\";"); 
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			}
			
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			/*out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"APPROVE\"){"); 
			out.println("new_window(1);"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"REVERSE\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("new_window(2);"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"APPROVE\"){");
			out.println("document.Form1.hid_status.value=\"Approve\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
			out.println("}else if(m_val==\"CLOSE\"){");  
			out.println("document.Form1.hid_status.value=\"Close\";");  
			out.println("document.Form1.hid_save.value=\"Close\";");  
			out.println("}else if(m_val==\"HELP\"){");  
			out.println("document.Form1.hid_status.value=\"Help\";"); 
			out.println("document.Form1.hid_save.value=\"Help\";");  
			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("m_scr=m_val");
			out.println("}"); 
      */
				
			/*out.println("function new_window(val) {");
			out.println("m_prev='"+m_pre_stage+"'");
			out.println("m_prev1='"+m_pre_stage1+"'");
			out.println("m_app='"+m_app_stage+"'");
			out.println("m_option=val");
			
			out.println("	if(val==1){");
			
			//Comment By Nuwan De  Silva 30-05-07==============================
			
			//out.println("for(var i=0;i<document.Form1.hid_no.value;i++){");
			//out.println("  chk=\"chk_quot_\"+i;");
			//out.println("document.Form1.elements[chk].disabled=false"); 
			//out.println("}");
	   // out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+val;"); 
		//	out.println(" window.location.href=m_url;");
			out.println("}");
			out.println("else	if(val==2){");
	   // out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&option=\"+val;"); 
		//  out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("}");
			*/
			
			
			out.println("function reverse_window() {");
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_app+\"&appro=\"+m_prev;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");


			out.println("function change(row) {"); 
			out.println("  chk_rej=\"chk_reject_\"+row;");
			out.println("  chk_quot=\"chk_quot_\"+row;");
			
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("document.Form1.elements[chk_rej].checked=false");
			out.println("document.Form1.elements[chk_rej].value='N'");
			out.println("ck=-1");
			out.println("}");
			out.println("else if(document.Form1.elements[chk_quot].checked==false && document.Form1.elements[chk_rej].checked==false){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 


			out.println("function change_reject(row) {"); 
			out.println("  chk_rej=\"chk_reject_\"+row;");
			out.println("  chk_quot=\"chk_quot_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("document.Form1.elements[chk_quot].checked=false");
			out.println("document.Form1.elements[chk_quot].value='N'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 
			
			
			out.println("function set_screen() {"); 
			out.println("	if(m_prev1==m_prev){");
			out.println("document.Form1.SCREEN_NAME.value='APPROVE';"); 
			out.println("}"); 
			out.println("else if(m_prev1==m_app){");
			out.println("document.Form1.SCREEN_NAME.value='REVERSE';"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function verify_screen(val,val2) {"); 
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Verifi_app_1?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&applicaton_no=\"+val+\"&insurance_done=\"+val2+\"&close=1\";"); 
			out.println(" window.location.href=m_url;"); 
			//out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=10,width=800,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			
			out.println("}"); 
			
			out.println("function load_report(m_app_no,m_facility_no,m_client_no) {"); 
			//out.println(" alert('app no'+m_app_no);");
			// comment by nuwan de silva 20-12-07 
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Sanction_Report?chksql=main_page&applicaton_no=\"+m_app_no+\"&facility_no=\"+m_facility_no+\"&print=TRUE&client_code=\"+m_client_no;"); 
			//out.println(" window.open(m_url);"); 
			//out.println("   window.open(m_url,'displayWindowap','left=100,top=60,width=700,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			// added by nuwan de silva 20-12-07 
			out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_verification.value+'&applicaton_no='+m_app_no+''");
			out.println("   window.open(m_url,'displayWindowap','left=100,top=60,width=700,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1 ');"); 

			out.println("}"); 
			
				
			out.println("function close_2() {"); 
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}"); 


			out.println("function close_window_1() {"); 
			out.println("if ("+m_close+"==1){");
			out.println("window.close()");
			out.println("}");
			out.println("else{");
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("}"); 
//__________________________________________________________________________________________________________________________________________________________________________________________________			
      out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"set_screen(),load_roll_value_1()\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input  type='hidden' value=\"APPROVE\" name=\"SCREEN_NAME\"> "); 
      out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<input type=hidden name=\"hid_save\" value=\"Save\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_verification' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">");  // added by nuwan de silva on 17-12-2007
			
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<input type='Hidden' name='hid_status' value=\"Verify\">");
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<input type='Hidden' name='hid_status' value=\"Approve \">");
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<input type='Hidden' name='hid_status' value=\"Approve \">");
			}
      
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
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Verification</td>"); 
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 1</td>"); 
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 2</td>"); 
			}
			
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> ");
			
			out.println("<tr>");
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Verify\");' onMouseOver='load_roll_value(\"Verify\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Approve\");' onMouseOver='load_roll_value(\"Approve\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  

			}
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Approve\");' onMouseOver='load_roll_value(\"Approve\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  

			}
			if(m_pre_stage.equals("ENT_CON")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}
			
			
			else 	if(m_pre_stage.equals("V-APP")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}

			else if (m_pre_stage.equals("VERIFY-M")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}

			out.println("<td width='6%'></td>");  
			
			if (m_opt.equals("1")){
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Save onMouseout='load_roll_out_value(\"Save\");' onMouseOver='load_roll_value(\"Save\");'  onclick='save_window()' value=\"Save\" disabled></td>"); 
			}
				
			if (m_opt.equals("2")){
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Save onMouseout='load_roll_out_value(\"Save\");' onMouseOver='load_roll_value(\"Save\");'  onclick='save_window()' value=\"Save\" ></td>"); 
			}
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Close onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='load_screen_status(\"CLOSE\"),close_window();' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
			out.println("<tr class=tr_input>");
			out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=tr_input>");
			
			out.println("<td colspan=11 align=right></td>");
			out.println("<td colspan=13 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
					
     //Header ==========================================
			
			out.println("<tr class=pdn_txtpos2 align='left'>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
			
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Facility No  '       onclick=sort_data('FACILITY_NO') >Facility No</td>");
      out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Period  '            onclick=sort_data('PERIOD') >Period(Days)</td>");
		
			//ADDED BY NUWAN DE SILVA 15-03-07
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Priority  '            onclick=sort_data('PRIORITY') >Priority</td>");
			
			if(m_pre_stage.equals("VERIFY-M")){

			out.println("<td  width='7%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Mkt Officer</td>");
			out.println("<td  width='13%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
			}
			
			else {
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Mkt Officer</td>");
			out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
			}
			
			
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
			out.println("<td  width='10%'  >Rental / Net income Ratio</td>");
			
			if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td  width='6%'  >User</td>");
			}

			
			//Added By Nuwan De Silva ====================
			if(m_opt.equals("2")){
			
			out.println("<td  width='5%'  >Status</td>");
			out.println("<td  width='8%'  >Reject</td>");
			}
			//=============================================
			
			
			//if(m_pre_stage.equals("VERIFY-M")){
			//out.println("<td  width='6%'  >User</td>");
			//}
			
			//if(m_pre_stage.equals("ENT_CON")){
  		//out.println("<td  width='5%'>&nbsp</td>");
			//}
			
			//out.println("<td  width='5%'>&nbsp</td>");
			
			
			if(m_opt.equals("1") && m_pre_stage.equals("ENT_CON")){
				out.println("<td  width='5%'>&nbsp</td>");
			}
			if(m_opt.equals("1")) {
			out.println("<td  width='5%'>&nbsp</td>");
			}
			
			out.println("</tr>");
			
					 
           int j = 0;   
					 int i = 1;  
					
					
					
					
					//*** CREDIT VERIFICATION ****
					if(m_pre_stage.equals("ENT_CON")){
										
				
					m_new_stage=m_pre_stage1;
				
					

						rs = stmt.executeQuery (" SELECT distinct "+
							                        " A.APPLICATION_NO,"+ //1
																			" NVL(A.FACILITY_NO,'-') FACILITY_NO , "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
																			" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
																			" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//14
																			" NVL(A.CLIENT_CODE,'-'), "+//15
																			" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','','','',A.INQUARY_NO, "+
																			
																			" NVL( (SELECT NVL(STATUS,'N')  FROM "+ //added by nuwan de silva on 04-10-07-----------
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																			" WHERE   ENT_DATE =( "+
																			" SELECT  MAX(ENT_DATE) "+
																			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																			" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N' ) STATUS "+ //22
																			
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																	    " WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
																		  " ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				}
				


				//*** CREDIT APPROVAL 1 ****
					else	if(m_pre_stage.equals("V-APP")){
					
					
					if(m_opt.equals("1")){
					m_new_stage="V-APP";
					}
					else {
					m_new_stage=m_pre_stage1;
					}


			rs1= stmt1.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_MK_APP_STATUS_APPROVE_2') ");
			if(rs1.next()){
			sel_stage=rs1.getInt(1);
			}
			
						rs = stmt.executeQuery 
						(" SELECT distinct "+
							                        " A.APPLICATION_NO,"+ //1
																			" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
																			" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
																			" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY,  "+//14
																			" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','"+m_username+"',"+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'),TOTAL_FINANCE_AMOUNT, "+
																			" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO ,"+//20
																			" NVL((SELECT NVL(STATUS,'N')  FROM "+
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																			" WHERE   ENT_DATE =( "+
																			" SELECT  MAX(ENT_DATE) "+
																			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																			" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N') STATUS "+ //22
																			
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+ 
																			" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+ 
																		  " ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				
				
				}
				
				//*** CREDIT APPROVAL 2 ****
				
		else	if(m_pre_stage.equals("VERIFY-M")){
	
		String m_sort="";
		if(m_sort_column.equals("APPLICATION_NO")){
		m_sort="A.APPLICATION_NO";
		}
			if(m_sort_column.equals("ENT_DATE")){
		m_sort="A.ENT_DATE";
		}	
		
		else{
		m_sort=m_sort_column;
		}
		
				m_new_stage=m_pre_stage1;
				
				
				rs1= stmt1.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_MK_APP_STATUS_APPROVE_3') ");
			if(rs1.next()){
			sel_stage=rs1.getInt(1);
			}
			
		rs = stmt.executeQuery 
		(" SELECT distinct "+
							                        " A.APPLICATION_NO AS APPLICATION_NO,"+ //1
																			" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
																			" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+//13
																			" '', "+//14
																			" '"+m_username+"',"+ //15
																			" INITCAP(NVL(A.INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//16
																			" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY, "+
																			""+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'), "+
																			" TOTAL_FINANCE_AMOUNT, "+
																			" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO "+//18
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL X "+
																			" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
																			" AND A.APPLICATION_NO=X.APPLICATION_NO "+
																			" ORDER BY "+m_sort+" "+m_order_by_type+"");

				}
				
				double m_limit=0;
				double m_total=0;
				String m_diff="";
				String m_client_code="";
				double m_rental=0;
				double m_in_ex_amount=0;
				double m_income_ratio=0;
				int m_rental_count=0;
				double m_avg_rental=0;
				
				
            while(rs.next()){
						
							m_limit=rs.getDouble(18);
							m_total=rs.getDouble(19);
							
							//comment by nuwan de silva on 29-11-2007---------------------------------------
							if (!m_pre_stage.equals("VERIFY-M")  ){  //!m_pre_stage.equals("ENT_CON") &&
							m_return_status=rs.getString(22);
							}
							else{
							m_return_status="N";
							}
							
							
							//out.println("m_pre_stage"+m_pre_stage);


							  		rs2 = stmt2.executeQuery (" SELECT "+
																						  " APPLICATION_NO, "+
																						  " CLIENT_CODE "+
																						  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																						  " WHERE APPLICATION_NO='"+rs.getString(1)+"' ");
											
									 if(rs2.next()){
										m_client_code=rs2.getString(2);
									 }	
									
									//Modified by Mahela on 22-10-2007 -  rental count
									rs2 = stmt2.executeQuery (" SELECT "+
																				    " SUM(NET_RENTAL_AMOUNT), "+
																				   	" SUM(INTEREST_AMOUNT), "+
																				   	" (SUM(INTEREST_AMOUNT)/SUM(NET_RENTAL_AMOUNT))*100, "+
																						" COUNT(NET_RENTAL_AMOUNT) "+	
																				   	" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																				   	" WHERE APPLICATION_NO='"+rs.getString(1)+"' ");
										
									  if(rs2.next()){
								     m_rental = rs2.getDouble(1);	
										 m_rental_count = rs2.getInt(4);		
										}
									
										
									 rs2 = stmt2.executeQuery (" SELECT "+
													  								 " SUM(DECODE(TYPE,'IN',AMOUNT,'EX',AMOUNT*-1)) "+
													 									 "	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_INCOME_EXPEN "+
													 									 "	WHERE CLIENT_CODE='"+m_client_code+"' ");
	                 
								  if(rs2.next()){
									 m_in_ex_amount = rs2.getDouble(1);		  
									}
								  
									//Added by Mahela on 22-10-2007
								  m_avg_rental=(m_rental/m_rental_count);
								
									if(m_in_ex_amount==0){
									m_income_ratio = 0;
									}
									else {
									 m_income_ratio = (m_avg_rental/m_in_ex_amount)*100; 
									}
							
							    //if (!m_pre_stage.equals("VERIFY-M")  ){ 
					
									if(m_return_status.equals("RET-VERY-M") || m_return_status.equals("RET-VE-APP") || m_return_status.equals("RET-VERY-1")){
										out.println("<tr bgcolor='#CCCC99' >");
									}
									
									else{
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									    	out.println("<tr class=tr_input >");
									    }
									}		
								//	}
								//	else{
								//	out.println("<tr bgcolor='#FFCC99' >");
								//	}
									
									
							   //Credit verification =======================================
									if (m_opt.equals("1") && m_pre_stage.equals("ENT_CON")){

																		
									out.println("<td width='12%' align='left' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') ><u>"+rs.getString(1) +"</td>");
                  }
									
									else{
									out.println("<td  width='12%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"</td>"); 
			
									}
									
									out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
                  out.println("<td width='5%' align='left'>"+rs.getString(4) +"</td>");
									
									
									if(m_pre_stage.equals("ENT_CON")){
									out.println("<td width='5%' align='left'>"+rs.getString(16) +"</td>");
									} 
									
									else if(m_pre_stage.equals("VERIFY-M"))
									{
									out.println("<td width='5%' align='left'>"+rs.getString(17) +"</td>");
									}
									
									else {
									out.println("<td width='5%' align='left'>"+rs.getString(15) +"</td>");
									}
									
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------						
									if(!m_pre_stage.equals("VERIFY-M")){
         
                  out.println("<td width='7%' align='left' onclick=\"show_mk_officer('"+rs.getString(21)+"')\" style='cursor:hand' ><u>"+rs.getString(5) +"</td>");
           //       out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
									}
									else 
									{
					
									out.println("<td width='10%' align='left' onclick=\"show_mk_officer('"+rs.getString(21)+"')\" style='cursor:hand' ><u>"+rs.getString(5) +"</td>");
                 // out.println("<td width='15%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</u></td>");

									}
									
								if(m_pre_stage.equals("ENT_CON")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(15)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
								else if(m_pre_stage.equals("V-APP")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
								else if(m_pre_stage.equals("VERIFY-M")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
							
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------						
                  out.println("<td width='5%'  align='left'>"+rs.getInt(7) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getString(8) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(9) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(10) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(11) +"</td>");
									out.println("<td width='5%'  align='center'>"+rs.getString(13) +"</td>");
									
									if(m_in_ex_amount==0) {
									out.println("<td width='10%'  align='right'>Cannot Calculate as Net Income is Zero</td>");
									}
									
									else {
									out.println("<td width='10%'  align='right'>"+nf.format(m_income_ratio)+"</td>");
									}
									
									  
									//----CHECK BOX------------------------------------------------------------------------------------------------------------------------------------------------------
									/*if(m_pre_stage.equals("VERIFY-M") && rs.getString(14).equals(rs.getString(15))){

									if (m_opt.equals("1")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" ></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" ></td>");

									}
									}
									
									
									else{
									*/
									//******************************************************************************************************************************************************************
									
									//---USER-----------------------------------------------------------------------
								  if(m_pre_stage.equals("VERIFY-M")){
									
									out.println("<td width='6%'  align='center'>"+rs.getString(15) +"</td>");

									}

									if(((m_pre_stage.equals("V-APP") || m_pre_stage.equals("VERIFY-M")) && (m_limit < m_total))){
									
									//if (m_opt.equals("1")){
									//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 	//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									
									//}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");

									}
								
								  }
									
									
									else{
									

								//***************************************************************************************************************************************************************************
									
								/*	if (m_opt.equals("1")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
	                out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");

									}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\"></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\"></td>");

									}
									*/
								//***************************************************************************************************************************************************************************

									//if (m_opt.equals("1")){
									//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 //	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									//}
								

									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" ></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" ></td>");

									}

									
								}
									
									//---USER-----------------------------------------------------------------------
								  //if(m_pre_stage.equals("VERIFY-M")){
									
									//out.println("<td width='6%'  align='center'>"+rs.getString(15) +"</td>");

									//}
									//***************************************************************************************************************************************************************************
								
									//--VIEW BUTTON------------------------------------------------------------------
								/*	if(m_pre_stage.equals("VERIFY-M") && rs.getString(14).equals(rs.getString(15))){

									if (m_opt.equals("1")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									if (m_opt.equals("2")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									}
									
									else{
									*/
									
									/*if (m_opt.equals("1")){
									
									if(m_pre_stage.equals("VERIFY-M")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									}
									else
									{
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									if(m_pre_stage.equals("ENT_CON")){
										out.println("<td><input type=button name=\"report_"+j+"\" value=\"Report\"   class=\"but_input\" onclick=load_report(\""+rs.getString(1)+"\",\""+rs.getString(2)+"\",\""+rs.getString(15)+"\") >");
									}
									}
									
									}
									
									if (m_opt.equals("2")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=\"\" disabled>");
									}

									*/
							//	}
									
								//--VIEW BUTTON------------------------------------------------------------------
									if(((m_pre_stage.equals("V-APP") || m_pre_stage.equals("VERIFY-M")) && (m_limit < m_total))){

									if (m_opt.equals("1")){
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									//if (m_opt.equals("2")){
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									//}
									
									}
									
									else{
									
									
									if (m_opt.equals("1")){
									
									if(m_pre_stage.equals("VERIFY-M")){
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									}
									
									if(m_pre_stage.equals("V-APP")){
																	
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									}
									
									//else
									//{
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									
								//	out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									
									if(m_pre_stage.equals("ENT_CON")){
									
									  out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
										out.println("<td><input type=button name=\"report_"+j+"\" value=\"Report\"   class=\"but_input\" onclick=load_report(\""+rs.getString(1)+"\",\""+rs.getString(2)+"\",\""+rs.getString(15)+"\") >");
									}
								//	}
									
									}
									
								//	if (m_opt.equals("2")){
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=\"\" disabled>"); //comment by nuwan de silva
								//	out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=\"\" disabled>");  //added by nuwan de silva  22-05-07
								//	}

									
								}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------
									

									out.println("</tr>");
									out.println("<tr>");
									out.println("<td><input type=\"hidden\" name=hid_app_no_"+j+" value="+rs.getString(1)+"></td>");
									out.println("</tr>");
									if(m_return_status.equals("RET-VERY-M") || m_return_status.equals("RET-VE-APP") || m_return_status.equals("RET-VERY-1")){
									out.println("<input type=\"hidden\" name=hid_return_status_"+j+" value="+rs.getString(22)+">");
									}
									
									
                	j=j+1;
								
              }
          
									out.println("<tr>");
									out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
									out.println("<td><input type=\"hidden\" name=hid_scr value="+m_pre_stage1+"></td>");
									out.println("</tr>");
					
									out.println("<tr class=tr_input>");
          				out.println("<td colspan=11 align=right></td>");
									out.println("<td align=right colspan=13><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
           				out.println("</tr></table>");
										
										
          				out.println("</td>");
			
									out.println("</tr>");
									out.println("</table>");
									out.println("</form>");
									out.println("</body>");
									out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
									out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
									out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					
								
								out.println("</html>");
      				}
							//added by nuwan de silva on 23-11-2007------
							else if(m_chksql.trim().equals("main_page")){
							
							
							String m_sort_column   = "PRIORITY";	
							String m_order_by_type = "ASC";

							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
								
								m_pre_stage=req.getParameter("pre");
								m_app_stage=req.getParameter("appro");
								m_pre_stage1=req.getParameter("qry");
			

							}
							
				
							String  m_opt=req.getParameter("option");
							if	(req.getParameter("option")==null){
							 m_opt="1";
								
								}
		
		
      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
				
				
			out.println("	var m_prev='"+m_pre_stage+"'");
			out.println("	var m_prev1='"+m_pre_stage1+"'");
				
			out.println("	var m_app='"+m_app_stage+"'");
			out.println("	var m_option='"+m_opt+"'");
			out.println("	var m_scr");
			out.println("var ck=1");
			out.println("var b_flag=0;");

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
				
			out.println("function load_roll_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Verifcation \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Verification - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("if('"+m_pre_stage+"'=='V-APP'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 1 \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 1 - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("}");
				
			out.println("function load_roll_value_1(){"); 
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("var m_val=\"Verfy\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Verification - \"+m_val;"); 
	
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='V-APP'){");
			out.println("var m_val=\"Approve\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 1 - \"+m_val;"); 

			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("var m_val=\"Approve\";");
			out.println("help_box.innerHTML=\"Credit  - Credit Approval 2 - \"+m_val;"); 
			out.println("}");
			out.println("}");

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 


			out.println("function load_data(m_app_no,m_app_sts) {");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
	    out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no=\"+m_app_no;"); 
			out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("  }");
			out.println("}");
			
			out.println("function load_roll_out_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Verification - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='V-APP'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 1 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 2 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("}");

			out.println("function sort_data(m_sort_col) {");
			out.println("var m_bk=1");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			
			out.println("if(m_option==1){");
			out.println("m_bk=m_option");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("else if(m_option==2){");
			out.println("m_bk=m_option");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("else if(m_option=='null'){");
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+m_option+\"&sort_column=\"+m_sort_col+\"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&division_code=\"+document.Form1.hid_m_division_code.value+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("load_interface(m_url,'NORM');");

			out.println("}");
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
		
			
			out.println("function check_app(){ "); 
			out.println("if(!count_chk()){"); 
			out.println("alert(\"Please approve or reject the application \");");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
		  out.println("}"); 
			
			out.println("function count_chk(){ ");
			out.println("count=0;");
			out.println(" m_row = document.Form1.hid_no.value ; ");
			out.println("for(i=0;i<m_row;i++){");
			out.println("  chk=\"chk_quot_\"+i;");
			out.println("  chk_rej=\"chk_reject_\"+i;");
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
			//out.println("alert('"+m_return_status+"');");
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
 			//out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_help_msg() {"); 
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr1\";"); 
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			}
			
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"APPROVE\"){"); 
			out.println("new_window(1);"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"REVERSE\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("new_window(2);"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"APPROVE\"){");
			out.println("document.Form1.hid_status.value=\"Approve\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
			out.println("}else if(m_val==\"CLOSE\"){");  
			out.println("document.Form1.hid_status.value=\"Close\";");  
			out.println("document.Form1.hid_save.value=\"Close\";");  
			out.println("}else if(m_val==\"HELP\"){");  
			out.println("document.Form1.hid_status.value=\"Help\";"); 
			out.println("document.Form1.hid_save.value=\"Help\";");  
			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("m_scr=m_val");
			out.println("}"); 

				
			out.println("function new_window(val) {");
			//out.println("alert('name'+val);");
			out.println("m_prev='"+m_pre_stage+"'");
			out.println("m_prev1='"+m_pre_stage1+"'");
			out.println("m_app='"+m_app_stage+"'");
			out.println("m_option=val");
			
			out.println("	if(val==1){");
			
			//Comment By Nuwan De  Silva 30-05-07==============================
			
			//out.println("for(var i=0;i<document.Form1.hid_no.value;i++){");
			//out.println("  chk=\"chk_quot_\"+i;");
			//out.println("document.Form1.elements[chk].disabled=false"); 
			//out.println("}");
			
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&option=\"+val;"); 
			out.println(" window.location.href=m_url;");
			
			out.println("}");
			out.println("else	if(val==2){");
			
	    out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&option=\"+val;"); 
		  out.println(" window.location.href=m_url;"); 
			
			out.println("}");
			//out.println("window.open(m_url);");
			out.println("}");
				
			out.println("function reverse_window() {");
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre=\"+m_app+\"&appro=\"+m_prev;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");


			out.println("function change(row) {"); 
			out.println("  chk_rej=\"chk_reject_\"+row;");
			out.println("  chk_quot=\"chk_quot_\"+row;");
			
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("document.Form1.elements[chk_rej].checked=false");
			out.println("document.Form1.elements[chk_rej].value='N'");
			out.println("ck=-1");
			out.println("}");
			out.println("else if(document.Form1.elements[chk_quot].checked==false && document.Form1.elements[chk_rej].checked==false){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 


			out.println("function change_reject(row) {"); 
			out.println("  chk_rej=\"chk_reject_\"+row;");
			out.println("  chk_quot=\"chk_quot_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("document.Form1.elements[chk_quot].checked=false");
			out.println("document.Form1.elements[chk_quot].value='N'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 
			
			
			out.println("function set_screen() {"); 
			out.println("	if(m_prev1==m_prev){");
			out.println("document.Form1.SCREEN_NAME.value='APPROVE';"); 
			out.println("}"); 
			out.println("else if(m_prev1==m_app){");
			out.println("document.Form1.SCREEN_NAME.value='REVERSE';"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function verify_screen(val,val2) {"); 
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Verifi_app_1?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&applicaton_no=\"+val+\"&insurance_done=\"+val2+\"&close=1\";"); 
			out.println(" window.location.href=m_url;"); 
			//out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=10,width=800,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			
			out.println("}"); 
			
			out.println("function load_report(m_app_no,m_facility_no,m_client_no) {"); 
			//out.println(" alert('app no'+m_app_no);");
			//comment  by nuwan de silva 20-12-07
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Sanction_Report?chksql=main_page&applicaton_no=\"+m_app_no+\"&facility_no=\"+m_facility_no+\"&print=TRUE&client_code=\"+m_client_no;"); 
			//out.println(" window.open(m_url);"); 
			//out.println("   window.open(m_url,'displayWindowap','left=100,top=60,width=700,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			//added by nuwan de silva 20-12-07
			out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&Hid_scr_name='+document.Form1.Hid_scr_name_verification.value+'&applicaton_no='+m_app_no+''");
			out.println("   window.open(m_url,'displayWindowap','left=100,top=60,width=700,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1 ');"); 

			out.println("}"); 
			
				
			out.println("function close_2() {"); 
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}"); 


			out.println("function close_window_1() {"); 
			out.println("if ("+m_close+"==1){");
			out.println("window.close()");
			out.println("}");
			out.println("else{");
      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("}"); 
			
			//____ added by nuwan de silva on 23-11-2007 __________
			out.println("function search_client_details(m_client_name,m_division_code) {"); 
			//out.println("alert('as'+document.Form1.SCREEN_NAME.value);");
			out.println("m_prev='"+m_pre_stage+"'");
			out.println("m_prev1='"+m_pre_stage1+"'");
			out.println("m_app='"+m_app_stage+"'");
			out.println("m_option='"+m_opt+"'"); 

			//out.println("m_option=1");
			
			out.println("if(m_option==1 /*document.Form1.SCREEN_NAME.value==\"APPROVE\"*/){");
			out.println("m_option=1");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&division_code=\"+m_division_code+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
			//out.println("window.open(m_url);");
			out.println("}");
			
			out.println("else if( m_option==2 /*document.Form1.SCREEN_NAME.value==\"REVERSE\"*/){");
			out.println("m_option=2");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page1&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&option=\"+m_option;"); 
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&division_code=\"+m_division_code+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
			out.println("}");
			
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
      //____ end by nuwan de silva on 23-11-2007 ______________
			
//__________________________________________________________________________________________________________________________________________________________________________________________________			
      out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"set_screen(),load_roll_value_1()\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input  type='hidden' value=\"APPROVE\" name=\"SCREEN_NAME\"> "); 
      out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<input type=hidden name=\"hid_save\" value=\"Save\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_verification' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">");  // added by nuwan de silva on 17-12-2007
			
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<input type='Hidden' name='hid_status' value=\"Verify\">");
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<input type='Hidden' name='hid_status' value=\"Approve \">");
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<input type='Hidden' name='hid_status' value=\"Approve \">");
			}
      
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
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Verification</td>"); 
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 1</td>"); 
			}
			
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 2</td>"); 
			}
			
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> ");
			
			out.println("<tr>");
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Verify\");' onMouseOver='load_roll_value(\"Verify\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  
			}
			
			else if(m_pre_stage.equals("V-APP")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Approve\");' onMouseOver='load_roll_value(\"Approve\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  

			}
			else if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=Approve onMouseout='load_roll_out_value(\"Approve\");' onMouseOver='load_roll_value(\"Approve\");' onClick='load_screen_status(\"APPROVE\"),new_window(1)' value=\"New\"></td>");  

			}
			if(m_pre_stage.equals("ENT_CON")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}
			
			
			else 	if(m_pre_stage.equals("V-APP")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}

			else if (m_pre_stage.equals("VERIFY-M")){

			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Reverse onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");'  onclick='load_screen_status(\"REVERSE\"),new_window(2)' value=\"Reverse\"></td>");  
			
			}

			out.println("<td width='6%'></td>");  
			
			if (m_opt.equals("1")){
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Save onMouseout='load_roll_out_value(\"Save\");' onMouseOver='load_roll_value(\"Save\");'  onclick='save_window()' value=\"Save\" disabled></td>"); 
			}
				
			if (m_opt.equals("2")){
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Save onMouseout='load_roll_out_value(\"Save\");' onMouseOver='load_roll_value(\"Save\");'  onclick='save_window()' value=\"Save\" ></td>"); 
			}
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Close onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='load_screen_status(\"CLOSE\"),close_window();' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
								
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='15' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"search_client_details(document.Form1.TXT_CLIENT_NAME.value,document.Form1.TXT_DIVISION_CODE.value)\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			// added by nuwan de silva on 27-11-2007
			out.println("<tr class=tr_input>");
			out.println("<td width='20%' >Division Code</td>");
			out.println("<td width='40%' >");
			out.println("<select name=\"TXT_DIVISION_CODE\" class=\"txt_input\" style=\"{width:200px;}\" >");
			rs = stmt.executeQuery(	"  SELECT "+
			"  DIVISION_CODE, "+
			"  DESCRIPTION "+
			"  FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			"  WHERE ACTIVE_STATUS='Y' ");
			
			boolean more = rs.next();
			while(more){
			out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
			more = rs.next();	
			}	
			out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
			out.println("</SELECT>");
			out.println("</td>");
			out.println("</tr>");

			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
								
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
				
			//__________________________________________added by nuwan de silva on 23-11-2007 _____________________________________________
			else if(m_chksql.trim().equals("load_data_client")){
			
			
			String m_sort_column   = "PRIORITY";	
			String m_order_by_type = "ASC";
			String _m_division_code="";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			
			m_pre_stage=req.getParameter("pre");
			m_app_stage=req.getParameter("appro");
			m_pre_stage1=req.getParameter("qry");
			
			
			}
			String  m_opt=req.getParameter("option");
			if	(req.getParameter("option")==null){
			m_opt="1";
			}
			
			if	(req.getParameter("m_client_name")==null){
			_m_client_name="";
			}
			_m_client_name=req.getParameter("m_client_name").trim();
			
			_m_division_code=req.getParameter("division_code").trim();
			
			
	
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=tr_input>");
			
			out.println("<td colspan=11 align=right></td>");
			out.println("<td colspan=13 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
					
     //Header ==========================================
			
			out.println("<tr class=pdn_txtpos2 align='left'>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
			
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Facility No  '       onclick=sort_data('FACILITY_NO') >Facility No</td>");
      out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Period  '            onclick=sort_data('PERIOD') >Period(Days)</td>");
		
			//ADDED BY NUWAN DE SILVA 15-03-07
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Priority  '            onclick=sort_data('PRIORITY') >Priority</td>");
			
			if(m_pre_stage.equals("VERIFY-M")){

			out.println("<td  width='7%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Mkt Officer</td>");
			out.println("<td  width='13%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
			}
			
			else {
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Mkt Officer</td>");
			out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
			}
			
			
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
			out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
			out.println("<td  width='10%'  >Rental / Net income Ratio</td>");
			
			if(m_pre_stage.equals("VERIFY-M")){
			out.println("<td  width='6%'  >User</td>");
			}

			
			//Added By Nuwan De Silva ====================
			if(m_opt.equals("2")){
			
			out.println("<td  width='5%'  >Status</td>");
			out.println("<td  width='8%'  >Reject</td>");
			}
			//=============================================
			
			
			//if(m_pre_stage.equals("VERIFY-M")){
			//out.println("<td  width='6%'  >User</td>");
			//}
			
			//if(m_pre_stage.equals("ENT_CON")){
  		//out.println("<td  width='5%'>&nbsp</td>");
			//}
			
			//out.println("<td  width='5%'>&nbsp</td>");
			
			
			if(m_opt.equals("1") && m_pre_stage.equals("ENT_CON")){
				out.println("<td  width='5%'>&nbsp</td>");
			}
			if(m_opt.equals("1")) {
			out.println("<td  width='5%'>&nbsp</td>");
			}
			
			out.println("</tr>");
			
					 
           int j = 0;   
					 int i = 1;  
					
					
					
					
					//*** CREDIT VERIFICATION ****
					if(m_pre_stage.equals("ENT_CON")){
				
					m_new_stage=m_pre_stage1;
					
								if(_m_division_code.equals("ALL")){
								rs = stmt.executeQuery (" SELECT distinct "+
								" A.APPLICATION_NO,"+ //1
								" NVL(A.FACILITY_NO,'-') FACILITY_NO , "+ //2
								"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
								" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
								"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
								"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
								" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
								" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
								" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
								" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
								" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//14
								" NVL(A.CLIENT_CODE,'-'), "+//15
								" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','','','',A.INQUARY_NO "+
								"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
								" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
								" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
								" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
								}
								else
								{
								rs = stmt.executeQuery (" SELECT distinct "+
								" A.APPLICATION_NO,"+ //1
								" NVL(A.FACILITY_NO,'-') FACILITY_NO , "+ //2
								"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
								" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
								"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
								"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
								" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
								" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
								" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
								" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
								" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
								" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//14
								" NVL(A.CLIENT_CODE,'-'), "+//15
								" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','','','',A.INQUARY_NO "+
								/*" NVL( (SELECT NVL(STATUS,'N')  FROM "+ //added by nuwan de silva on 04-10-07-----------
								" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
								" WHERE   ENT_DATE =( "+
								" SELECT  MAX(ENT_DATE) "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
								" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N' ) STATUS "+ //22
								*/
								"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
								" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
								" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
								" AND UPPER(A.DIVISION_CODE) LIKE  UPPER('%"+_m_division_code+"%')  "+
								" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
								}												
										
				}
				


				//*** CREDIT APPROVAL 1 ****
					else	if(m_pre_stage.equals("V-APP")){
					
					
					if(m_opt.equals("1")){
					m_new_stage="V-APP";
					}
					else {
					m_new_stage=m_pre_stage1;
					}


			rs1= stmt1.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_MK_APP_STATUS_APPROVE_2') ");
			if(rs1.next()){
			sel_stage=rs1.getInt(1);
			}
			
			
			
							if(_m_division_code.equals("ALL")){
							
							rs = stmt.executeQuery 
							(" SELECT distinct "+     
							" A.APPLICATION_NO,"+ //1
							" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
							"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
							" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
							"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
							"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
							" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
							" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
							" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
							" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
							" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY,  "+//14
							" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','"+m_username+"',"+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'),TOTAL_FINANCE_AMOUNT, "+
							" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO "+//20
							
							"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+ 
							" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+ 
							" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
							" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
							}
							else{
							rs = stmt.executeQuery 
							(" SELECT distinct "+     
							" A.APPLICATION_NO,"+ //1
							" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
							"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
							" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
							"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
							"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
							" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
							" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
							" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
							" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+ //13
							" INITCAP(NVL(INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY,  "+//14
							" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY,'','"+m_username+"',"+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'),TOTAL_FINANCE_AMOUNT, "+
							" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO "+//20
							
							/*" NVL((SELECT NVL(STATUS,'N')  FROM "+
							" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
							" WHERE   ENT_DATE =( "+
							" SELECT  MAX(ENT_DATE) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
							" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N') STATUS "+ //22
							*/
							
							"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+ 
							" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+ 
							" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
							" AND UPPER(A.DIVISION_CODE) LIKE  UPPER('%"+_m_division_code+"%') "+
							" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
							}
											
				
				
				}
				
				//*** CREDIT APPROVAL 2 ****
				
		else	if(m_pre_stage.equals("VERIFY-M")){
	
		String m_sort="";
		if(m_sort_column.equals("APPLICATION_NO")){
		m_sort="A.APPLICATION_NO";
		}
			if(m_sort_column.equals("ENT_DATE")){
		m_sort="A.ENT_DATE";
		}	
		
		else{
		m_sort=m_sort_column;
		}
		
				m_new_stage=m_pre_stage1;
				
				
				rs1= stmt1.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_MK_APP_STATUS_APPROVE_3') ");
			if(rs1.next()){
			sel_stage=rs1.getInt(1);
			}
			
			
							if(_m_division_code.equals("ALL")){
							
							rs = stmt.executeQuery 
							(" SELECT distinct "+
							" A.APPLICATION_NO AS APPLICATION_NO,"+ //1
							" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
							"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
							" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
							"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
							"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
							" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
							" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
							" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
							" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+//13
							" '', "+//14
							" '"+m_username+"',"+ //15
							" INITCAP(NVL(A.INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//16
							" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY, "+
							""+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'), "+
							" TOTAL_FINANCE_AMOUNT, "+
							" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO "+//18
							"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL X "+
							" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
							" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
							" AND A.APPLICATION_NO=X.APPLICATION_NO "+
							" ORDER BY "+m_sort+" "+m_order_by_type+"");
							
							}
							else {
							
							rs = stmt.executeQuery 
							(" SELECT distinct "+
							" A.APPLICATION_NO AS APPLICATION_NO,"+ //1
							" NVL(A.FACILITY_NO,'-') as FACILITY_NO, "+ //2
							"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
							" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
							"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
							"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+//6
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+//7
							" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
							" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
							" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
							" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+//12
							" NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE, "+//13
							" '', "+//14
							" '"+m_username+"',"+ //15
							" INITCAP(NVL(A.INSURANCE_DONE_BY,'-')) INSURANCE_DONE_BY, "+//16
							" INITCAP(NVL(A.PRIORITY,'-')) PRIORITY, "+
							""+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+sel_stage+"'), "+
							" TOTAL_FINANCE_AMOUNT, "+
							" NVL(A.CLIENT_CODE,'-'),A.INQUARY_NO "+//18
							"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL X "+
							" WHERE APPLICATION_STATUS IN ('"+m_pre_stage1+"') "+
							" AND  UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE  UPPER('%"+_m_client_name+"%') "+
							" AND UPPER(A.DIVISION_CODE) LIKE  UPPER('%"+_m_division_code+"%') "+
							" AND A.APPLICATION_NO=X.APPLICATION_NO "+
							" ORDER BY "+m_sort+" "+m_order_by_type+"");
							}
							
					

				}
				
				double m_limit=0;
				double m_total=0;
				String m_diff="";
				String m_client_code="";
				double m_rental=0;
				double m_in_ex_amount=0;
				double m_income_ratio=0;
				int m_rental_count=0;
				double m_avg_rental=0;
				
				
            while(rs.next()){
						
							m_limit=rs.getDouble(18);
							m_total=rs.getDouble(19);
							
							//comment by nuwan de silva on 29-11-2007
							/*if (!m_pre_stage.equals("VERIFY-M")  ){  //!m_pre_stage.equals("ENT_CON") &&
							m_return_status=rs.getString(22);
							}
							else{
							m_return_status="N";
							} */
							
														
							//out.println("m_pre_stage"+m_pre_stage);


							  		rs2 = stmt2.executeQuery (" SELECT "+
																						  " APPLICATION_NO, "+
																						  " CLIENT_CODE "+
																						  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																						  " WHERE APPLICATION_NO='"+rs.getString(1)+"' ");
											
									 if(rs2.next()){
										m_client_code=rs2.getString(2);
									 }	
									
									//Modified by Mahela on 22-10-2007 -  rental count
									rs2 = stmt2.executeQuery (" SELECT "+
																				    " SUM(NET_RENTAL_AMOUNT), "+
																				   	" SUM(INTEREST_AMOUNT), "+
																				   	" (SUM(INTEREST_AMOUNT)/SUM(NET_RENTAL_AMOUNT))*100, "+
																						" COUNT(NET_RENTAL_AMOUNT) "+	
																				   	" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																				   	" WHERE APPLICATION_NO='"+rs.getString(1)+"' ");
										
									  if(rs2.next()){
								     m_rental = rs2.getDouble(1);	
										 m_rental_count = rs2.getInt(4);		
										}
									
										
									 rs2 = stmt2.executeQuery (" SELECT "+
													  								 " SUM(DECODE(TYPE,'IN',AMOUNT,'EX',AMOUNT*-1)) "+
													 									 "	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_INCOME_EXPEN "+
													 									 "	WHERE CLIENT_CODE='"+m_client_code+"' ");
	                 
								  if(rs2.next()){
									 m_in_ex_amount = rs2.getDouble(1);		  
									}
								  
									//Added by Mahela on 22-10-2007
								  m_avg_rental=(m_rental/m_rental_count);
								
									if(m_in_ex_amount==0){
									m_income_ratio = 0;
									}
									else {
									 m_income_ratio = (m_avg_rental/m_in_ex_amount)*100; 
									}
							
							    //if (!m_pre_stage.equals("VERIFY-M")  ){ 
					
									//comemnt by nuwan de silva on 29-11-2007--------
									
									/*if(m_return_status.equals("RET-VERY-M") || m_return_status.equals("RET-VE-APP") || m_return_status.equals("RET-VERY-1")){
										out.println("<tr bgcolor='#CCCC99' >");
									}
									
									else{*/
									
									
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									    	out.println("<tr class=tr_input >");
									    }
											
											
									//}		
									
									
								//	}
								//	else{
								//	out.println("<tr bgcolor='#FFCC99' >");
								//	}
									
									
							   //Credit verification =======================================
									if (m_opt.equals("1") && m_pre_stage.equals("ENT_CON")){

																		
									out.println("<td width='12%' align='left' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') ><u>"+rs.getString(1) +"</td>");
                  }
									
									else{
									out.println("<td  width='12%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"</td>"); 
			
									}
									
									out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
                  out.println("<td width='5%' align='left'>"+rs.getString(4) +"</td>");
									
									
									if(m_pre_stage.equals("ENT_CON")){
									out.println("<td width='5%' align='left'>"+rs.getString(16) +"</td>");
									} 
									
									else if(m_pre_stage.equals("VERIFY-M"))
									{
									out.println("<td width='5%' align='left'>"+rs.getString(17) +"</td>");
									}
									
									else {
									out.println("<td width='5%' align='left'>"+rs.getString(15) +"</td>");
									}
									
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------						
									if(!m_pre_stage.equals("VERIFY-M")){
         
                  out.println("<td width='7%' align='left' onclick=\"show_mk_officer('"+rs.getString(21)+"')\" style='cursor:hand' ><u>"+rs.getString(5) +"</td>");
           //       out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
									}
									else 
									{
					
									out.println("<td width='10%' align='left' onclick=\"show_mk_officer('"+rs.getString(21)+"')\" style='cursor:hand' ><u>"+rs.getString(5) +"</td>");
                 // out.println("<td width='15%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</u></td>");

									}
									
								if(m_pre_stage.equals("ENT_CON")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(15)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
								else if(m_pre_stage.equals("V-APP")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
								else if(m_pre_stage.equals("VERIFY-M")){
							   out.println("<td width='13%' align='left' onclick=\"show_client('"+rs.getString(20)+"')\" style='cursor:hand' ><u>"+rs.getString(6) +"</td>");
								}
							
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------						
                  out.println("<td width='5%'  align='left'>"+rs.getInt(7) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getString(8) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(9) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(10) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getInt(11) +"</td>");
									out.println("<td width='5%'  align='center'>"+rs.getString(13) +"</td>");
									
									if(m_in_ex_amount==0) {
									out.println("<td width='10%'  align='right'>Cannot Calculate as Net Income is Zero</td>");
									}
									
									else {
									out.println("<td width='10%'  align='right'>"+nf.format(m_income_ratio)+"</td>");
									}
									
									  
									//----CHECK BOX------------------------------------------------------------------------------------------------------------------------------------------------------
									/*if(m_pre_stage.equals("VERIFY-M") && rs.getString(14).equals(rs.getString(15))){

									if (m_opt.equals("1")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" ></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" ></td>");

									}
									}
									
									
									else{
									*/
									//******************************************************************************************************************************************************************
									
									//---USER-----------------------------------------------------------------------
								  if(m_pre_stage.equals("VERIFY-M")){
									
									out.println("<td width='6%'  align='center'>"+rs.getString(15) +"</td>");

									}

									if(((m_pre_stage.equals("V-APP") || m_pre_stage.equals("VERIFY-M")) && (m_limit < m_total))){
									
									//if (m_opt.equals("1")){
									//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 	//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									
									//}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");

									}
								
								  }
									
									
									else{
									

								//***************************************************************************************************************************************************************************
									
								/*	if (m_opt.equals("1")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
	                out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");

									}
									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\"></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\"></td>");

									}
									*/
								//***************************************************************************************************************************************************************************

									//if (m_opt.equals("1")){
									//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" disabled></td>");
                 //	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" disabled></td>");
									//}
								

									
									if (m_opt.equals("2")){
									out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_quot_"+j+" value=\"N\" unchecked onclick=\"change('"+j+"')\" ></td>");
	               	out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=chk_reject_"+j+" value=\"N\" unchecked onclick=\"change_reject('"+j+"')\" ></td>");

									}

									
								}
									
									//---USER-----------------------------------------------------------------------
								  //if(m_pre_stage.equals("VERIFY-M")){
									
									//out.println("<td width='6%'  align='center'>"+rs.getString(15) +"</td>");

									//}
									//***************************************************************************************************************************************************************************
								
									//--VIEW BUTTON------------------------------------------------------------------
								/*	if(m_pre_stage.equals("VERIFY-M") && rs.getString(14).equals(rs.getString(15))){

									if (m_opt.equals("1")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									if (m_opt.equals("2")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									}
									
									else{
									*/
									
									/*if (m_opt.equals("1")){
									
									if(m_pre_stage.equals("VERIFY-M")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									}
									else
									{
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									if(m_pre_stage.equals("ENT_CON")){
										out.println("<td><input type=button name=\"report_"+j+"\" value=\"Report\"   class=\"but_input\" onclick=load_report(\""+rs.getString(1)+"\",\""+rs.getString(2)+"\",\""+rs.getString(15)+"\") >");
									}
									}
									
									}
									
									if (m_opt.equals("2")){
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=\"\" disabled>");
									}

									*/
							//	}
									
								//--VIEW BUTTON------------------------------------------------------------------
									if(((m_pre_stage.equals("V-APP") || m_pre_stage.equals("VERIFY-M")) && (m_limit < m_total))){

									if (m_opt.equals("1")){
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									}
									
									//if (m_opt.equals("2")){
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") disabled>");
									//}
									
									}
									
									else{
									
									
									if (m_opt.equals("1")){
									
									if(m_pre_stage.equals("VERIFY-M")){
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(16)+"\") >");
									}
									
									if(m_pre_stage.equals("V-APP")){
																	
									out.println("<td><input type=button name=\"view_"+j+"\" value=\"Approve\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									}
									
									//else
									//{
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									
								//	out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
									
									if(m_pre_stage.equals("ENT_CON")){
									
									  out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=verify_screen(\""+rs.getString(1)+"\",\""+rs.getString(14)+"\") >");
										out.println("<td><input type=button name=\"report_"+j+"\" value=\"Report\"   class=\"but_input\" onclick=load_report(\""+rs.getString(1)+"\",\""+rs.getString(2)+"\",\""+rs.getString(15)+"\") >");
									}
								//	}
									
									}
									
								//	if (m_opt.equals("2")){
									//out.println(m_pre_stage);
									//out.println("<td><input type=button name=\"view_"+j+"\" value=\"View\"   class=\"but_input\" onclick=\"\" disabled>"); //comment by nuwan de silva
								//	out.println("<td><input type=button name=\"view_"+j+"\" value=\"Verify\"   class=\"but_input\" onclick=\"\" disabled>");  //added by nuwan de silva  22-05-07
								//	}

									
								}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------
									

									out.println("</tr>");
									out.println("<tr>");
									out.println("<td><input type=\"hidden\" name=hid_app_no_"+j+" value="+rs.getString(1)+"></td>");
									out.println("</tr>");
									
									//comment by nuwan de silva on 29-11-2007--------------------------------------------------------
									/*if(m_return_status.equals("RET-VERY-M") || m_return_status.equals("RET-VE-APP") || m_return_status.equals("RET-VERY-1")){
									out.println("<input type=\"hidden\" name=hid_return_status_"+j+" value="+rs.getString(22)+">");
									}*/
									
																		
                	j=j+1;
								
              }
          
									out.println("<tr>");
									out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
									out.println("<td><input type=\"hidden\" name=hid_scr value="+m_pre_stage1+"></td>");
									out.println("<td><input type=\"hidden\" name=hid_m_client_name value="+_m_client_name+"></td>");//added by nuwan de silva on 23-11-2007
									out.println("<td><input type=\"hidden\" name=hid_m_division_code value="+_m_division_code+"></td>");//added by nuwan de silva on 23-11-2007
									
									out.println("</tr>");
					
									out.println("<tr class=tr_input>");
          				out.println("<td colspan=11 align=right></td>");
									out.println("<td align=right colspan=13><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
           				out.println("</tr></table>");
				}
				
				//___________________________________________________________________________________________________
				
			//=========================================================================================================================			
  				
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
