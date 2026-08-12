//--
//SCREEN NAME:SYSTEM ADMINISTRATION - APPROVE QUOTATIONS
//CREATED BY:DELANJALI
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MK_display_approve_quotations extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods   m_sn_methods = new LAKDL_AF_CO_conn_methods  (); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Quotation Approval </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_row;");
			out.println("var xx=0");
			out.println("var ck=1");
			out.println("var drill_vec=''");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value!='Q'){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='Q'){");
			out.println("drill_vec=data_vec");
			out.println("				display_fields(data_vec);");
			out.println("			}");
			
			out.println("}");
			
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_val.value='Q'");//To seperatly identify m_url's.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_approve_quotations&ac_status=P\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest1(val) {");
			out.println("qot=\"TXT_QUOTATION_NO_\"+val;");
			out.println("pr=\"TXT_PRICE_\"+val;");
			out.println(" quot= document.Form1.elements[qot].value;");
			out.println("document.Form1.hid_val.value='P'");//To seperatly identify m_url's.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Indicative_Quatation_Letter?chksql=m_price&price_no1=\"+document.Form1.elements[pr].value+\"&data_val=\"+quot;");
			out.println("popupwin=window.showModalDialog(m_url,data_vec,\"left=0;top=10; dialogWidth:20em; dialogHeight:20em;\");");
			out.println("}");
			
			out.println("function sysdate() {");
			out.println("document.Form1.hid_st.value='S'");//To seperatly identify m_url's.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_approve_quotations_sysdate&ac_status=P\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("}"); 

			out.println("function before_submit(){ ");
			out.println("var val_ck=document.Form1.hid_no.value");
			
			out.println("for(var i=0;i<val_ck;i++){");
			out.println("  chk=\"chk_quot_\"+i;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("ck=ck*1");
			out.println("}");
			out.println("else if(document.Form1.elements[chk].checked==true){");
			out.println("ck=ck*-1");
			out.println("} ");
			out.println("}");
			out.println("if(ck<=0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_save_approve_quotations?number='+document.Form1.hid_no.value+'';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("else{");
			out.println("alert('Please select a quotation no before saving');"); 
			out.println("}");
			out.println("}");

			out.println("function load_lock(){	"); 
		//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_approve_quotations';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_approve_quotations';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_approve_quotations\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Marketing - Quotation Approval - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Marketing - Quotation Approval - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";");

			out.println("}else if(m_val==\"DACT\"){"); 
			//out.println("alert ('Are you wont to Delete the Quotations');	");//added by Prabash on 15-02-2012
			out.println("document.Form1.hid_status.value=\"Delete\";"); 
			out.println("document.Form1.hid_save.value=\"Delete\";");
			out.println("document.Form1.SCREEN_NAME.value=m_val;");//added by Prabash on 15-02-2012 
			out.println("display_fields(data_vec);"); //added by Prabash on 15-02-2012
			
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
		
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("alert('aaa--'+oBj.valout[2]+'--'+oBj.valout[3]+'--sfsfsfsf'+oBj.valout[0]);");
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("else if(oBj.valout[4] != \" \"){ ");
			out.println("Crit = oBj.valout[4];");
			out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
			out.println("}	");
			out.println("}");	
			
			
			out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
			out.println("}");		
				
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
				
			out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_QUOTATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_INQUIRY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_STATUS_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("     Crit = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("     Crit = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 

			
			
			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function change(row) {"); 
			out.println("  chk=\"chk_quot_\"+row;");
			out.println("  document.Form1.elements[chk].value=data_vec[0];");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"0\";"); 
			out.println("ck=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"1\";"); 
			out.println("ck=0");
			out.println("}");
			out.println("}"); 


			out.println("function lock() {");//used when deleting to stop changing the fields.
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("alert ('You cannot type in here..!');	");
			out.println("window.event.keyCode='';	");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("function show_inquiry(row){"); 
			out.println("m_inq=\"TXT_INQUIRY_NO_\"+row");
	    out.println("	  m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MK_Inquiry?chksql=Inquiry_det&deal_no=\"+document.Form1.elements[m_inq].value;"); 
			out.println("   window.open(m_url,'displayWindowap','left=90,top=90,width=900,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("");
			out.println("}"); 
			
			out.println("function show_quo_drill(row){"); 
	    out.println("	show_quotation_drill(drill_vec[row]) "); 
			out.println("}"); 
		
			
			out.println("function show_inq_drill(row){"); 
	    out.println("	show_inquiry_drill(row.value) "); 
			out.println("}"); 
			
			out.println("function show_client_drill(row){"); 
	    out.println("	show_client_name_drill(row.value) "); 
			out.println("}"); 

			out.println("function header(){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr class=\"pdn_txtpos2\">'+");
			out.println("'<td width=\"20%\"  align=left><b>Quotation No</td>'+"); 
			out.println("'<td width=\"15%\"  align=left><b>Inquiry No </td>'+"); 
			out.println("'<td width=\"20%\" align=left><b>Client Name </td>'+"); 
			out.println("'<td width=\"19%\"  align=right><b>Total Amount </td>'+"); 
			out.println("'<td width=\"12%\"  align=center><b>Approved Status </td>'+");
			out.println("'<td width=\"6%\"></td>'+");
			out.println("'<td width=\"8%\">Inquiry</td>'+");
			out.println("'</tr >'+"); 
			out.println("'</table >';"); 
			out.println("}");
			//--------Prabash on 15-02-2012--------------*
			out.println("function header_del(){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr class=\"pdn_txtpos2\">'+");
			out.println("'<td width=\"20%\"  align=left><b>Quotation No</td>'+"); 
			out.println("'<td width=\"15%\"  align=left><b>Inquiry No </td>'+"); 
			out.println("'<td width=\"20%\" align=left><b>Client Name </td>'+"); 
			out.println("'<td width=\"19%\"  align=right><b>Total Amount </td>'+"); 
			out.println("'<td width=\"12%\"  align=center><b>Delete Status </td>'+");
			out.println("'<td width=\"6%\"></td>'+");
			out.println("'<td width=\"8%\">Inquiry</td>'+");
			out.println("'</tr >'+"); 
			out.println("'</table >';"); 
			out.println("}");
			//-------------------------------------------*
			out.println("function display_fields(data_vec){");
			out.println("change1.innerHTML=\"\"");
		  	out.println("var j=0;");
			out.println("var i=0;");
			//-----Added by Prabash on 15-06-2012----*
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("header_del();");
			out.println("    } ");
			out.println("    else{");
			out.println("header();");
			out.println("    } ");
			//-------------------------------------=*
		//	out.println("header();");//Comment by Prabash on 15-02-2012
			out.println("if(data_vec.length==0){");
    		out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"20%\" align=left><input type=\"hidden\" class=txt-body style=\"{text-align=right}\" name=TXT_QUOTATION_NO_'+j+' value=\"\" maxlength=\"10\" size=\"10\" onkeypress=\"lock()\" ><td>'+"); 
			//out.println("'<td width=\"20%\" align=left><input class=\"but_input\" type=\"button\" name=BUT_PRICE_'+j+' onclick=\"makeRequest1('+j+')\" value=\"View\" ></td>'+"); 
			out.println("'<td width=\"15%\" align=right><input type=\"hidden\" class=txt-body style=\"{text-align=center}\" name=TXT_INQUIRY_NO_'+j+' value=\"\" maxlength=\"15\" size=\"15\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"20%\" align=right><input type=\"hidden\" class=txt-body style=\"{text-align=right}\" name=TXT_CLIENT_NAME_'+j+' value=\"\" maxlength=\"50\" size=\"50\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"20%\" align=right><input type=\"hidden\"  class=txt-body style=\"{text-align=right}\" name=TXT_TOTAL_'+j+' value=\"\" maxlength=\"25\" size=\"15\" onkeypress=\"lock()\"></td>'+"); 
			
			out.println("'<input type=\"hidden\"  class=txt-body style=\"{text-align=right}\" name=TXT_PRICE_'+j+' value=\"\" maxlength=\"25\" size=\"15\" onkeypress=\"lock()\">'+"); 
			out.println("'<td width=\"20%\" align=center><input type=\"checkbox\" name=chk_quot_'+j+' unchecked=true onclick=\"change('+j+')\"></td>'+");
			out.println("'<td width=\"20%\" align=left><input class=\"but_input\" type=\"button\" name=BUT_PRICE_'+j+' onclick=\"makeRequest1('+j+')\" value=\"View\" ></td>'+"); 
			out.println("'<td  width=\"5%\" style= cursor:hand   onclick=show_inquiry('+j+') >Inq</td>'+");

			out.println("'</tr >'+");
			out.println("'<tr>'+"); 
			out.println("'</tr>'+"); 
			out.println("'</table >';");
		  out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("while(i<data_vec.length){");
			
			out.println("if(j>0 && j%2==1){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr class=\"tr_input1\">'+"); 
			out.println("'<td width=\"20%\" align=left  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_quo_drill('+i+')\"><U>'+data_vec[i]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_QUOTATION_NO_'+j+' value='+data_vec[i]+' maxlength=\"15\" size=\"15\" onkeypress=\"lock()\" ></td>'+"); 
			out.println("'<td width=\"15%\" align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_inq_drill(TXT_INQUIRY_NO_'+j+')\"><U>'+data_vec[i+1]+'<input type=\"hidden\" style=\"{text-align=center}\" name=TXT_INQUIRY_NO_'+j+' value='+data_vec[i+1]+' maxlength=\"15\" size=\"15\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"20%\" align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_drill(TXT_CLIENT_NAME_'+j+')\"><U>'+data_vec[i+2]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_CLIENT_NAME_'+j+' value='+data_vec[i+2]+' maxlength=\"50\" size=\"50\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"19%\" align=right>'+data_vec[i+3]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_TOTAL_'+j+' value='+data_vec[i+3]+' maxlength=\"25\" size=\"15\" onkeypress=\"lock()\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"12%\" align=center><input type=\"checkbox\" name=chk_quot_'+j+' unchecked=\"true\" onclick=\"change('+j+')\"><input type=\"hidden\"  class=txt-body style=\"{text-align=right}\" name=TXT_PRICE_'+j+' value='+data_vec[i+7]+' maxlength=\"25\" size=\"15\" onkeypress=\"lock()\"></td>'+");
			out.println("'<td width=\"6%\" align=left><input class=\"but_input\" type=\"button\" onclick=\"makeRequest1('+j+')\" name=BUT_PRICE_'+j+' value=\"View\" ></td>'+"); 
			out.println("'<td  width=\"8%\" style= cursor:hand   onclick=show_inquiry('+j+') >Inquiry</td>'+");
			out.println("'</tr>'+"); 
			out.println("'</table >';");
			out.println("}"); 
			out.println("else{");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr class=\"tr_input\">'+"); 
			out.println("'<td width=\"20%\" align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_quo_drill('+i+')\"><U>'+data_vec[i]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_QUOTATION_NO_'+j+' value='+data_vec[i]+' maxlength=\"15\" size=\"15\" onkeypress=\"lock()\" ></td>'+"); 
			out.println("'<td width=\"15%\" align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_inq_drill(TXT_INQUIRY_NO_'+j+')\"><U>'+data_vec[i+1]+'<input type=\"hidden\" style=\"{text-align=center}\" name=TXT_INQUIRY_NO_'+j+' value='+data_vec[i+1]+' maxlength=\"15\" size=\"15\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"20%\" align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_drill(TXT_CLIENT_NAME_'+j+')\"><U> '+data_vec[i+2]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_CLIENT_NAME_'+j+' value='+data_vec[i+2]+' maxlength=\"50\" size=\"50\" onkeypress=\"lock()\"></td>'+"); 
			out.println("'<td width=\"19%\" align=right>'+data_vec[i+3]+'<input type=\"hidden\" style=\"{text-align=right}\" name=TXT_TOTAL_'+j+' value='+data_vec[i+3]+' maxlength=\"25\" size=\"15\" onkeypress=\"lock()\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"12%\" align=center><input type=\"checkbox\" name=chk_quot_'+j+' unchecked=\"true\" onclick=\"change('+j+')\"><input type=\"hidden\"  class=txt-body style=\"{text-align=right}\" name=TXT_PRICE_'+j+' value='+data_vec[i+7]+' maxlength=\"25\" size=\"15\" onkeypress=\"lock()\"></td>'+");
			out.println("'<td width=\"6%\" align=left><input class=\"but_input\" type=\"button\" onclick=\"makeRequest1('+j+')\" name=BUT_PRICE_'+j+' value=\"View\" ></td>'+"); 
			out.println("'<td  width=\"8%\" style= cursor:hand   onclick=show_inquiry('+j+') >Inquiry</td>'+");
			out.println("'</tr>'+"); 
			out.println("'</table >';");
			out.println("}"); 
			out.println("j=j+1;");
			out.println("i=i+8;");
			out.println("}"); 
			out.println("}"); 
			out.println("document.Form1.hid_no.value=j");	
			out.println("}");
			
			out.println("function view() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_display_quotation_select\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			



//----------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),makeRequest()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 

			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_check' VALUE=\"-1\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Approve Quotations</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DACT\")' value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' style='width:120' onMouseOver='load_roll_value(\"View Quotation\");' onClick='load_screen_status(\"VIEW\"),view()' value=\"View Quotation\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
