//--
//SCREEN NAME:Credit Process - Payment Approval 1
//CREATED BY :Delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_pay_repos1_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt2,stmt1,stmt,stmt3,stmt4,stmt5,stmt6;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs2,rs1,rs,rs3,rs4,rs5,rs6;
	public String m_chksql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			stmt6 = conn.createStatement();

			String m_application_no = req.getParameter("APP_NO");
			String m_status1 = req.getParameter("chksql1");
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(0);
		  nf.setMaximumFractionDigits(0);
			String m_client="";
			String m_sus_ref = req.getParameter("SUS_REF_NO");
			String m_ref = req.getParameter("REF_NO");
			String m_pay_no = req.getParameter("PAY_NO");
			String m_value_date = req.getParameter("VALUE_DATE");
			double m_balance = Double.parseDouble(m_sn_methods.met_unformat_number(req.getParameter("BAL")));
			double m_total_settle=Double.parseDouble(req.getParameter("TOT_SET")); 
			double m_initial_bal=Double.parseDouble(req.getParameter("INIT_BAL")); 
			double m_paid_amt=Double.parseDouble(req.getParameter("PAID_AMT")); 
			String m_ent_type = req.getParameter("ENT_TYPE");
		
		  if(m_ent_type.equals("AD")){
			m_ent_type="Advertistment";
			}
			if(m_ent_type.equals("LN")){
			m_ent_type="Lawyer";
			}
			
			if(m_ent_type.equals("RP")){
			m_ent_type="Repossision";
			}


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Payment Details</TITLE>"); 
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
			
			
			
			out.println("var header;");		
			out.println("var ln=0;");
			out.println("var len=0;");
			out.println("var dist=0");
		  out.println("var b_flag=1");
			out.println("var b_flag_1=0");
			out.println("var b_flag_2=0");
			out.println("var b_flag_3=0");
			
			out.println("var b_mesg=0");
			out.println("var b_mesg1=0");
			out.println("var b_dates=0");
			out.println("var b_dates1=0");
			out.println("var b_dates2=0");
			out.println("var b_dates3=0");
			out.println("var b_count=0");
			out.println("var b_count_new=0");
						
			out.println("function get_conditions(){ "); 
			out.println("assig('M_CON')");		
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_application_no+"&ac_status=COMPLETED\";");
		  out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			
		 	out.println("function header_con(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
				
			
			out.println("function display_data(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("header_con();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand; }\" onclick=\"load_Follow('+lineno+')\" ><U>Follow up</TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("i=i+3;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0){");
			out.println("add_row()");
			out.println("}");
			out.println("}");
			
			
						
			/*----------------------------------------------------------------
					Purpose  : Add Conditions
				
			----------------------------------------------------------------*/			

			out.println("function add_row(){"); 
			out.println("var b_flag_con=0;");
			out.println("if(lineno!=0){");
			out.println("count=lineno-1;");
			out.println("m_condition=\"TXT_CONDITION\"+count");
			out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			out.println("alert('Condition can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("}");
			out.println("if(b_flag_con==0){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
     	out.println("}");
	    out.println("}");
			
			out.println("function del_row(rowNo){"); 
			out.println("if(rowNo!=0){");
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
			out.println("header_con();");
      out.println(" for(var j=0;j<size;j++){");
		  out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
		  out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" value='+array_condition[j]+' maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand; }\" onclick=\"load_Follow('+j+')\" ><U>Follow up</TD>'+"); //Follow up
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
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
		
			out.println("function get_vector(data_vec) {");
			out.println("	 if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B1\" && document.Form1.TXT_BRANCH_CODE.value!=''){");
			out.println("    document.Form1.TXT_ACC_NO.value='';");
			out.println("help_button_4('0','10','0','m_help_TXT_BRANCH_CODE_sql','4')");
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B2\" && document.Form1.TXT_ACC_NO.value!=''){");
			out.println("help_button_5('0','10','2','m_help_TXT_ACCOUNT_2_sql','5')");
			out.println("			}");
			out.println("	else if(document.Form1.hid_st.value==\"M_CON\"){");
			out.println("display_data(data_vec)");
			out.println("			}");
			out.println("}");


			out.println("function assig(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println("}");


			out.println("function check_foll_rem(){");
			out.println("b_count=0");
			out.println("for(var x=0;x<document.Form1.hid_asset_doc_no.value;x++){");
			out.println("if(document.Form1.hid_asset_doc_no.value !=\"0\"){");
			out.println("  chk_status=\"chk_status_\"+x");			
			out.println("  chk_napp=\"chk_napp_status_\"+x;");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+x;");
			out.println("  foll_status=\"chk_fol_status_\"+x;");
			out.println("if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_1=0");
			out.println("b_mesg=0");
			out.println("}");
			
			out.println("if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value==\"\"){");
			out.println("alert('Please enter folloup remark for asset documents')");
			out.println("document.Form1.elements[foll_status].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");
			out.println("else if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk_status].checked==true || document.Form1.elements[chk_napp].checked==true || document.Form1.elements[foll_status].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg=0");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("}");//if
			out.println("}");//for
			
			
			out.println("if(b_count!=document.Form1.hid_asset_doc_no.value){");
			out.println("b_count_new=0;");
			out.println("alert('Please enter either status ,not applicable or follow up for asset details')");	
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(b_count==document.Form1.hid_asset_doc_no.value){");
			out.println("b_count=0");
			out.println("for(var j=0;j<document.Form1.hid_client_doc_no.value;j++){");
			out.println("if(document.Form1.hid_client_doc_no.value !=\"0\"){");
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+j;");
			out.println("  chk_client_status=\"chk_client_status_\"+j");
			out.println("  foll_client_rem=\"TXT_CLIENT_FOL_REMARKS_\"+j;");
			out.println("  foll_client_status=\"chk_client_fol_status_\"+j;");
			out.println("if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[foll_client_status].checked==false){");
			out.println("b_mesg1=0");
			out.println("b_flag_1=0");
			out.println("}");
			out.println("if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value==''){");
		  out.println("alert('Please enter folloup remark for client documents')");
			out.println("document.Form1.elements[foll_client_rem].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg1=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");
			out.println("else if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(document.Form1.elements[foll_client_status].checked==true || document.Form1.elements[chk_client_status].checked==true || document.Form1.elements[chk_client_napp].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg1=1");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("}");//if
			out.println("}");//for
			out.println("if(b_count!=document.Form1.hid_client_doc_no.value){");
			out.println("alert('Please enter either status ,not applicable or follow up for client details')");
			out.println("b_count_new=0;");
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("}");
			out.println("}");



			out.println("function before_submit(){ ");
			out.println("		check_foll_con();");
		  out.println("	if(m_cond_status==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("alert('Please complete the pending status')");
			out.println("}");
			out.println("else{");

			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details2?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_1&status=APPRO1';");  
			out.println("		document.Form1.submit();	"); 
			out.println("} "); 
			out.println("} "); 
			out.println("} "); 
			

			out.println("function load_lock(){	"); 
			out.println("get_conditions();");//Added By Nuwan De Silva //07/02/2007.Conditions
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app1_details?PAY_NO="+m_pay_no+"&APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app1_details?PAY_NO="+m_pay_no+"&APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance - Payment Approval 1  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance - Payment Approval 1 - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window()");
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";");
			out.println("document.Form1.hid_save.value=\"Save\";"); 
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

			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("clear_data()");
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
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("else if(oBj.valout[4] != \" \"){ ");
			out.println("Crit = oBj.valout[4];");
			out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
			out.println("}	");
			out.println("if(oBj.valout[4]==' '){ ");
			out.println("Close()	");
			out.println("}	");
			out.println("}");	
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
			out.println("dist=\"0\"");
      out.println(" }");
			
			out.println("function clear_data(){");
			out.println("if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("    document.Form1.TXT_APP_NAME.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value='';"); 
			out.println(" document.Form1.elements[district].focus();"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println(" document.Form1.TXT_ACC_NO.value='';"); 
			out.println(" document.Form1.TXT_ACC_NO.focus();"); 
			out.println(" document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println("}");
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
			
			out.println("function help_button_1(row) {"); 
			out.println("dist=\"1\"");
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    Sql = \"m_help_TXT_DISTRICT_CODE_sql1\";");
			out.println(" district='TXT_DISTRICT_CODE_'+row");
			out.println("    Crit = document.Form1.elements[district].value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,1);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1(oBj) {"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value=oBj.valout[2];"); 
			out.println("dist=\"0\"");
			out.println("}"); 

			out.println("function check_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("if(document.Form1.elements[chk_napp].checked==true && document.Form1.elements[chk_status].checked==true){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			out.println("function check_napp_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_status].checked==true && document.Form1.elements[chk_napp].checked==true){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("document.Form1.elements[chk_status].checked=false");
			out.println("document.Form1.elements[chk_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_client_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");

			out.println("if(document.Form1.elements[chk_client_napp].checked==true && document.Form1.elements[chk_client_status].checked==true){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			
			out.println("function check_client_napp_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_client_status].checked==true && document.Form1.elements[chk_client_napp].checked==true){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("document.Form1.elements[chk_client_status].checked=false");
			out.println("document.Form1.elements[chk_client_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[chk_client_napp].checked==false){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_client_change_fol(row){");
			out.println("  foll_rem=\"TXT_CLIENT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_client_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_status=\"chk_client_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");	
			
	
			out.println("function check_change_fol(row){");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");		

			out.println("function check_insu_date(ln){ ");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");

			out.println("function check_rev_date(ln){ ");
			out.println("ind_dd='TXT_REVENUE_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_REVENUE_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_REVENUE_LICENSE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" &&  document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");

			
			out.println("function check_tax_date(ln){ ");
			out.println("ind_dd='TXT_LUXURY_TAX_DATE_DD'+ln;");
			out.println("ind_mm='TXT_LUXURY_TAX_DATE_MM'+ln;");
			out.println("ind_yy='TXT_LUXURY_TAX_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\" ){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");
		
			
			out.println("function check_dri_date(ln){ ");
			out.println("ind_dd='TXT_DRIVING_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_DRIVING_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_DRIVING_LICENSE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");
	
		
			out.println("function print_date_dd(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("}");
			
			out.println("function print_date_mm(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
		  out.println("}");

			out.println("function print_date_yy(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
		  out.println("}");

			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
			out.println("else if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='5'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	

      out.println("  else if(document.Form1.hid_cal_date.value=='6'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");		


			out.println("function close_screen(){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?screen_type=NEW&sql=main_page&status_new=NEW&chksql=R&chksql2=B&st_c=APPLICATION_NO&oby=ASC';");
			out.println("}");
			out.println("}");
		
			
			out.println("function check_date_from(){ ");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" || document.Form1.TXT_FROM_DATE_MM.value!=\"\" || document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");
			out.println("}");

			out.println("function check_foll_con(){");
			out.println("   document.Form1.hid_no_rec_con.value=arr_size;");
			out.println("for(var d=0;d<document.Form1.hid_no_rec_con.value;d++){");
			out.println("if(document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='Pending' || document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='--'){");
			out.println("m_cond_status=1");
			out.println("}");
			out.println("else{");
			out.println("m_cond_status=0");
			out.println("}");	
		  out.println("if(m_cond_status==1){");
			out.println("break");
			out.println("}");	
			out.println("}");
			out.println("}");

//------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cnt_doc' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cnt_cr' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_oth_doc' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_val_1' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PAYMENT_REQUSITION_MAIN\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_payer' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_doc_no' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_asset_doc_no' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_con' VALUE=\"\">"); 

			out.println("<INPUT TYPE='Hidden' NAME='hid_inv' VALUE=\"0\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Approval 1 </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>"); 
			
			
			
			
			
			
		
		
		rs= stmt.executeQuery("SELECT APPLICATION_NO,CLIENT_CODE, "+
													""+m_schema_name+".af_co_get_client_name(CLIENT_CODE) NAME "+
													"FROM  "+
													""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
													"WHERE upper(application_no)=upper('"+m_application_no+"') ");

		
		boolean more=rs.next();
		
		

			
			out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			if(more){
			m_client=rs.getString(2);
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No</DIV></td>"); 
			out.println("<td width='30%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_application_no+"')\"><U>"+m_application_no+"<input class='txt_input' type='hidden' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_CLIENT_CODE'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input class='txt_input' type='hidden' value="+rs.getString(2)+"  name='TXT_CLIENT_CODE'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_APP_NAME'  class=div_input>Name of the Applicant</DIV></td>"); 
			out.println("<td width='40%' >"+rs.getString(3)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}

			out.println("<tr >"); 
			out.println("</tr >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Suspense Payment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUS_REF_NO'  class=div_input>Sus Ref No</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+m_sus_ref+"')\"><U>"+m_sus_ref+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REF_NO'  class=div_input>Reference No</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+m_ref+"')\"><U>"+m_ref+"<input class='txt_input' type='hidden' name='TXT_REF_NO'>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_ENTRY_TYPE'  class=div_input>Suspense Entry Type</DIV></td>"); 
			out.println("<td width='40%'>"+m_ent_type+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("</tr >"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE_DATE'  class=div_input>Value Date</DIV></td>"); 
			out.println("<td width='40%' >"+m_value_date+"</td>"); 
 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_BAL'  class=div_input></DIV>Initial Amount</td>"); 
			out.println("<td width='40%' >"+nf.format(m_total_settle)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INIT_BAL'  class=div_input>Payments Already Made</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_initial_bal)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
				
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>Balance To Be Paid</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_balance)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>This Payment</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_paid_amt)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
		  out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");


			//out.println("<br>");
			//out.println("<HR>");	


			 rs2=stmt2.executeQuery
				("SELECT PAYMENT_NO,SUS_REF_NO,trim(PAY_AMOUNT),Decode(SETTLE_MODE,'CHQ','Cheque','CASH','Cash'), "+
			 "nvl(decode(ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-'),to_char(EFF_VALDATE,'dd-mm-yyyy'), "+
			 "LIC_ACC_NO,LIC_BRANCH_CODE,nvl(PAYEE_NAME,'-'),nvl(TO_CHAR(LETTER_DATE,'dd-mm-yyyy'),'-') "+
			 "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
			 "WHERE UPPER(SUS_REF_NO) =UPPER('"+m_sus_ref+"') ");

		
		
			int a=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			double m_total=0;

			while(rs2.next()){
			m_total=m_total+rs2.getDouble(3);

			if(a==0){


			out.println("<tr></tr>");
			
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='13%' align=\"left\">Payment No</td>");
			out.println("<td width='10%' align=\"right\">Settle Amount</td>");
			out.println("<td width='10%' align=\"left\">Settle Mode</td>");
			out.println("<td width='8%' align=\"left\">Entry Type</td>");
			out.println("<td width='10%' align=\"left\">Validate Date</td>");
			out.println("<td width='10%' align=\"left\">Account No</td>");
			out.println("<td width='10%' align=\"left\">Branch Code</td>");
			out.println("<td width='10%' align=\"left\">Payee Category</td>");
			out.println("<td width='10%' align=\"left\">Payee Name</td>");
			out.println("<td width='12%' align=\"left\">Letter Date</td>");
			out.println("</tr >"); 


			}		


			if(a>0 && a%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}


			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs2.getDouble(3))+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(4)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(5)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(6)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs2.getString(7)+"')\"><U>"+rs2.getString(7)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(8)+"</td>"); 
			out.println("<td align=\"left\">Third Party</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(9)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(10)+"</td>"); 
			out.println("</tr >"); 
			


			a=a+1;

			}

			out.println("</table >"); 
			out.println("<br>");
		
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<HR>");
			out.println("<tr class=tr_input1>");
			out.println("<td width='13%'  align=right><b>Total Settle Amount :</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_total)+"</td>"); 
			out.println("<td width='*%'>&nbsp</td>");
			out.println("</tr>"); 
			out.println("</table >"); 
		
			out.println("<HR>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
				out.println("<HR>");


			int q=0;
//////////////////////////////////////////////////////////////////////////////////////
		if(m_ref.substring(0,2).equals("LN")){
		
			rs1=stmt1.executeQuery(
			"SELECT "+
													    "LEGAL_NO, "+
													    "to_char(LEGAL_DATE,'DD-MM-YYYY'), "+
													    "DAILY_DECISION, "+
													    "TO_CHAR(NEXT_COURT_DATE,'DD-MM-YYYY'), "+
													    "nvl(NEXT_COURT_REQ,'-'), "+
													    "nvl(LAWYER_CHARGE_AMOUNT,0), "+
													    "nvl(BILL_REF_NO,'-') "+
													 		"FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIONS "+
															"WHERE UPPER(LEGAL_NO)=UPPER('"+m_ref+"') ");
															
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Legal Action Details</td></tr>");
				out.println("<tr></tr>");
		
			out.println("</table>");

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(rs1.next()){

			if(q==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' align=\"left\">Legal No</td>"); 
			out.println("<td width='15%' align=\"left\">Legal Date</td>"); 
			out.println("<td width='10%' align=\"left\">Daily Decision</td>"); 
			out.println("<td width='15%' align=\"left\">Next Court Date</td>"); 
			out.println("<td width='15%' align=\"left\">Next Court Req</td>"); 
			out.println("<td width='20%' align=\"right\">Lawyer Charge Amount</td>"); 
			out.println("<td width='15%' align=\"left\">Bill Reference No</td>"); 
			
			out.println("</tr >"); 
		}
		
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_legal_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(4)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(5)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(6))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(7)+"</td>"); 

			out.println("</tr>");
			q=q+1;

			}

			out.println("</table >"); 
}
//////////////////////////////////////////////////////////////////////////////////////
		if(m_ref.substring(0,2).equals("AD")){

			rs1=stmt1.executeQuery
			
			(	"SELECT "+
													    "INVENTORY_NO, "+
													    "VEHICLE_NO, "+
													    "TO_CHAR(ADVER_DATE,'DD-MM-YYYY'), "+
													    "ADVER_NO, "+
													    "AMOUNT, "+
													    "VAT_AMOUNT, "+
													    "TOTAL_AMOUNT, "+
													    "nvl(NO_OF_OFFERS,0), "+
													    "ADD_VALUE_PAID_TO "+
														  "FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
															"WHERE UPPER(ADVER_NO)=UPPER('"+m_ref+"') ");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Advertistment Details</td></tr>");
			out.println("<tr></tr>");
			out.println("</table>");

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(rs1.next()){

			if(q==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align=\"left\">Advertistment No</td>"); 
			out.println("<td width='10%' align=\"left\">Inventory No</td>"); 
			out.println("<td width='10%' align=\"left\">Vehicle No</td>"); 
			out.println("<td width='15%' align=\"left\">Advertistment Date</td>"); 
			out.println("<td width='10%' align=\"right\">Amount</td>"); 
			out.println("<td width='10%' align=\"right\">Vat Amount</td>"); 
			out.println("<td width='10%' align=\"right\">Total Amount</td>");
			out.println("<td width='10%' align=\"left\">No Of Offeres</td>");
			out.println("<td width='10%' align=\"left\">Money Paid To</td>");

	
			
			out.println("</tr >"); 
			}
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_advertistment_drill('"+rs1.getString(4)+"')\"><U>"+rs1.getString(4)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_inventory_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(5))+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(6))+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(7))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			out.println("</tr>");
			q=q+1;

			}

			out.println("</table >"); 
			}

//////////////////////////////////////////////////////////////////////////////////////

		 if(m_ref.substring(0,2).equals("RP")){
			rs1=stmt1.executeQuery(	"SELECT "+
													    "REPOSSESSION_NO, "+
													    "FINANCE_NO, "+
													    "SEIZER_CODE, "+
													    "LETTER_VALIDITY_PERIOD, "+
													    "INVOICE_AMOUNT, "+
													    "TRN_CURR_CODE, "+
													    "INVENTORY_NO, "+
													    "nvl(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'-'), "+
													    "nvl(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'-'), "+
													    "nvl(TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'),'-') "+
													 		"FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
															"WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_ref+"') ");



					
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Repossision Details</td></tr>");
			out.println("<tr></tr>");
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			while(rs1.next()){

			if(q==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' align=\"left\">Repossision No</td>"); 
			out.println("<td width='10%' align=\"left\">Seizer Code</td>"); 
			out.println("<td width='11%' align=\"left\">Validity Period</td>"); 
			out.println("<td width='13%' align=\"right\">Invoice Amount</td>"); 
			out.println("<td width='10%' align=\"left\">Currency Code</td>"); 
			out.println("<td width='10%' align=\"left\">Inventory No</td>");
			out.println("<td width='13%' align=\"left\">Transaction Date</td>");
			out.println("<td width='13%' align=\"left\">Repossessed Date</td>");
			out.println("<td width='10%' align=\"left\">Eff Value Date</td>");
			out.println("</tr >"); 
			}
			
			
			
			
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
						
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_repossession_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(4)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(5))+"</td>"); 
			out.println("<td align=\"left\" >"+rs1.getString(6)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_inventory_drill('"+rs1.getString(7)+"')\"><U>"+rs1.getString(7)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			out.println("</tr>");
			q=q+1;

			}

			out.println("</table >"); 
			}
//////////////////////////////////////////////////////////////////////////////////////
			
			
			out.println("<HR>");
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change3></div></td></tr></table>");
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change2></div></td></tr></table>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr align='left' class='tr_input'>");  
			out.println("<td width='*%'><b><u>Condition List</td> ");
			out.println("</tr>");  

			out.println("<tr align='left'>");  
			out.println("<td width='10'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\"></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>");


			rs6= stmt6.executeQuery 
			("SELECT DISTINCT A.ISSUER_CODE,trim(TO_CHAR(A.AMOUNT,'999,999,999,999,999.99')),TO_CHAR(A.ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+
			"TO_CHAR(A.END_DATE,'DD-MM-YYYY'),A.STATUS,B.NAME "+
			"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES A,"+m_schema_name+".AF_CO_MAS_BANKS B "+	
			"WHERE A.APPLICATION_NO='"+m_application_no+"' "+
			"AND  A.ISSUER_CODE=B.BANK_CODE "+
			"AND A.STATUS='Y' ");


			boolean more6=rs6.next();	
			int x=0;

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
				
			while(more6){
				
			if(x==0){
			out.println("<HR>");
			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Guarantee Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
						
			out.println("<td width='10%' style='{text-align:left;}'><b>Bank Guarantee Code</td>");
			out.println("<td width='30%' style='{text-align:left;}'><b>Bank Guarantee Name</td>");
			out.println("<td width='20%' style='{text-align:right;}'><b>Guaranteed Amount</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Guarantee Date</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"10%\" style='{text-align:left;}' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_bank_drill('"+rs6.getString(1)+"')\"><U>"+rs6.getString(1)+"</td>");
			out.println("<td width=\"30%\" style='{text-align:left;}'>"+rs6.getString(7)+"</td>");
			out.println("<td width=\"20%\" style='{text-align:right;}'>"+rs6.getString(2)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(3)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(4)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(5)+"</td></tr>");
			
			more6=rs6.next();
			x=x+1;
			}

			out.println("</table>");
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  

			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
