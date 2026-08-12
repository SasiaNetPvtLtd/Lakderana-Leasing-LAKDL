//--
//SCREEN NAME	:REFERENCE ADMIN
//CREATED BY	:DELANJALI	
//DATE/TIME		:30-01-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_reference_admin extends javax.servlet.http.HttpServlet { 

	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	int i;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			String m_schema_name=m_sn_methods.schema_name.trim();
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			m_chksql=req.getParameter("chksql");

			if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Approval of Reference Information</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" admin_details.innerHTML = ''; ");
			out.println(" admin_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_reference_admin?chksql=admin_details\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_INSERT_SCREEN.value==\"\"){  "); 
			out.println("DIV_TXT_INSERT_SCREEN.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TYPE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TYPE_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_TYPE_DESCRIPTION.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_UPDATE_SCREEN.value==\"\"){  "); 
			out.println("DIV_TXT_UPDATE_SCREEN.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ACTIVE_STATUS.value==\"\"){  "); 
			out.println("DIV_TXT_ACTIVE_STATUS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check(){");
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
		  out.println("  chk_dis=\"chk_dis_app_\"+d;");
			out.println("  chk_app=\"chk_app_\"+d;");
			out.println("if(document.Form1.elements[chk_dis].checked==false && document.Form1.elements[chk_app].checked==false){");
			out.println("b_flag=0");
			out.println("}");
			out.println("else {");
			out.println("b_flag=1");
			out.println("break");
			out.println("}");
			out.println("}");
			out.println("}");


			out.println("function before_submit(){ "); 
			out.println("check()");
			out.println("if(b_flag==0){");
			out.println("alert('Please either approve or disapprove the record')");
			out.println("}");
			out.println("	else{");
			out.println("		if(confirm(\"Are you sure you want to save?\")){ ");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_reference_admin?number='+document.Form1.hid_count.value+'';");     
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_reference_admin?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_reference_admin?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_reference_admin\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"System Administration - Approval of Reference Information - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"System Administration - Approval of Reference Information - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_INSERT_SCREEN.disabled=true;"); 
			out.println("document.Form1.TXT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TYPE_DESCRIPTION.disabled=true;"); 
			out.println("document.Form1.TXT_UPDATE_SCREEN.disabled=true;"); 
			out.println("document.Form1.TXT_ACTIVE_STATUS.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
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
			out.println("    m_sql = \"m_help_TXT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INSERT_SCREEN.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TYPE_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TYPE_DESCRIPTION.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_UPDATE_SCREEN.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ACTIVE_STATUS.value=oBj.valout[7];"); 
			out.println("}"); 
			
			out.println("function enter_data(val,row) {");
			out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"'+val+'?code='+document.Form1.elements[\"TXT_TYPE_CODE_\"+row].value+'&CLSTATUS=A';"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=60,width=900,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}"); 
			
			out.println("function change_dis(row) {"); 
			out.println("  chk_dis=\"chk_dis_app_\"+row;");
			out.println("  chk_app=\"chk_app_\"+row;");
			out.println("if(document.Form1.elements[chk_dis].checked==true && document.Form1.elements[chk_app].checked==true){");
			out.println("document.Form1.elements[chk_dis].value='Y'");
			out.println("document.Form1.elements[chk_app].checked=false");
			out.println("document.Form1.elements[chk_app].value='N'");
			out.println("}else if(document.Form1.elements[chk_dis].checked==false && document.Form1.elements[chk_app].checked==false){");
			out.println("document.Form1.elements[chk_dis].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_dis].value='Y'");
			out.println("}");		
			out.println("if(document.Form1.elements[chk_dis].checked==true){");
			out.println("alert('Plese select a code')");
			out.println("document.Form1.elements[\"TXT_CHOOSE_CODE_\"+row].disabled=false");	
			out.println("}");	
			out.println("else if(document.Form1.elements[chk_dis].checked==false){");
			out.println("document.Form1.elements[\"TXT_CHOOSE_CODE_\"+row].disabled=true");	
			out.println("}");	
			out.println("}");	

			out.println("function change_app(row) {"); 
			out.println("  chk_dis=\"chk_dis_app_\"+row;");
			out.println("  chk_app=\"chk_app_\"+row;");
			out.println("if(document.Form1.elements[chk_dis].checked==true && document.Form1.elements[chk_app].checked==true){");
			out.println("document.Form1.elements[chk_app].value='Y'");
			out.println("document.Form1.elements[chk_dis].checked=false");
			out.println("document.Form1.elements[chk_dis].value='N'");
			out.println("}else if(document.Form1.elements[chk_dis].checked==false && document.Form1.elements[chk_app].checked==false){");
			out.println("document.Form1.elements[chk_app].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_app].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[chk_app].checked==true){");
			out.println("document.Form1.elements[\"TXT_CHOOSE_CODE_\"+row].disabled=true");	
			out.println("}");	
			out.println("}");	

//*******************************************************************************************************************************************
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Approval of Reference Information</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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
					
			 rs = stmt.executeQuery ("SELECT CODE,INSERT_SCREEN,TYPE_CODE,TYPE_DESCRIPTION,UPDATE_SCREEN,DECODE(ACTIVE_STATUS,'P','Pending','-'), "+    //modified by nuwan de silva 19-07-07
			"(select display_name from "+m_schema_name+".CO_CO_MAS_USER_SCREEN where screen_url like UPDATE_SCREEN) as update_screen_name, "+
			"(select display_name from "+m_schema_name+".CO_CO_MAS_USER_SCREEN where screen_url like INSERT_SCREEN) as insert_screen_name "+
			"FROM "+m_schema_name+".AF_CO_MAS_REFERENCE_ADMIN WHERE ACTIVE_STATUS='P'");
			
			boolean more = rs.next();
	
		
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		
			out.println("<tr class=pdn_txtpos2>");

			out.println("<td width='14%' ><DIV id='DIV_TXT_CODE'  class=div_input>Code</DIV></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INSERT_SCREEN'  class=div_input>Insert Screen</DIV></td>"); 
			out.println("<td width='7%' ><DIV id='DIV_TXT_TYPE_CODE'  class=div_input>Type</DIV></td>"); 
			out.println("<td width='19%' ><DIV id='DIV_TXT_TYPE_DESCRIPTION'  class=div_input>Description</DIV></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_UPDATE_SCREEN'  class=div_input>Update Screen</DIV></td>"); 
			out.println("<td width='5%' align=center><DIV id='DIV_TXT_ACTIVE_STATUS'  class=div_input>Status </DIV></td>"); 
			out.println("<td width='6%' align=center>Approve</td>"); 
			out.println("<td width='9%' align=center>Disapprove</td>"); 
			out.println("<td width='10%' align=center>Select</td>"); 
			
			out.println("</tr >"); 

	    int j = 0; 			

			while(more){
					
			i=0;		
			String m_insert="";
			
			
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			
				
			m_insert=rs.getString(8);
			if(m_insert==null){
			m_insert="-";
			}
			
			if(m_insert.equals("")){
			m_insert="-";
			}
			
			out.println("<td width='14%' >"+rs.getString(1)+"<input class='txt_input' type='hidden' name=TXT_CODE_"+j+" maxlength='15' size='15' value=\""+rs.getString(1)+"\"></td>");
			out.println("<td width='15' >"+m_insert+"<input class='txt_input' type='hidden' name=TXT_INSERT_SCREEN_"+j+"' maxlength='100' size='100' value=\""+rs.getString(2)+"\"></td>");

			out.println("<td width='7%' >"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_TYPE_CODE_"+j+" maxlength='10' size='10' value=\""+rs.getString(3)+"\"></td>");
			out.println("<td width='19%' >"+rs.getString(4)+"<input class='txt_input' type='hidden' name=TXT_TYPE_DESCRIPTION_"+j+" maxlength='100' size='100' value=\""+rs.getString(4)+"\"></td>"); 
			out.println("<td width='15%' style='{cursor:hand;title=\"Click here to enter details\"}' onclick=enter_data('"+rs.getString(5)+"','"+j+"')>"+rs.getString(7)+"<input class='txt_input' type='hidden' name=TXT_UPDATE_SCREEN_"+j+" maxlength='100' size='100' value=\""+rs.getString(5)+"\"></td>"); 
			out.println("<td width='5%' align=center>"+rs.getString(6)+"<input class='txt_input' type='hidden' name=TXT_ACTIVE_STATUS_"+j+" maxlength='10' size='10' value=\""+rs.getString(6)+"\"></td>"); 
			
			out.println("<td width='6%' align=center><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change_app("+j+")\"></td>"); 
			out.println("<td width='9%' align=center><input type='checkbox' name=chk_dis_app_"+j+" value=\"N\" unchecked onClick=\"change_dis("+j+")\"></td>"); 

			if(rs.getString(5).equals("AF_MAS_display_model_creation")){
			
			rs1 = stmt1.executeQuery ("SELECT MODEL_CODE,DESCRIPTION,MAKE_CODE,FUEL_TYPE,ITEM_SUB_CAT "+
			 "FROM "+m_schema_name+".AF_CO_MAS_MODEL WHERE ACTIVE_STATUS='Y' ORDER BY MODEL_CODE ASC ");
			}		
			
			else if(rs.getString(5).equals("AF_MAS_display_make_creation")){

			rs1 = stmt1.executeQuery ("SELECT MAKE_CODE,MAKE_DESC,ITEM_SUB_CAT "+
			"FROM "+m_schema_name+".AF_CO_MAS_MAKE WHERE ACTIVE_STATUS='Y'  ORDER BY MAKE_CODE ASC  ");
			
			}
			boolean more1 = rs1.next();		
			out.println("<td width='10%'><SELECT  name=TXT_CHOOSE_CODE_"+j+" class=\"txt_input2\" disabled> ");
											
			while(more1){
				
			out.println("<OPTION value=\""+rs1.getString(1)+"\" selected>"+rs1.getString(2)+"</OPTION>");
			more1=rs1.next();
			
		
			}
			out.println("</select>");
			out.println("</td>");
			
			
			out.println("</tr>");
			more=rs.next();
			j=j+1;
		
			if (!more)
			{
			break;
			}

			}	

			out.println("<input type=hidden name=hid_count value="+j+">");
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
