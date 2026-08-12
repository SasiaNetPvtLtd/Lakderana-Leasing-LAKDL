//--
//SCREEN NAME:Credit Process - Payment Requisition Approval 
//CREATED BY :Delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_payment_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt2,stmt1,stmt,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs2,rs1,rs,rs3;
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

			String m_val = req.getParameter("PO_NO");
			String m_st1 = req.getParameter("CLSTATUS");
			String m_st_val = req.getParameter("st_val");
			String m_st_val1 = req.getParameter("st_val1");
			
			String m_requ = req.getParameter("REQ_NO");
			String m_sus_ref = req.getParameter("SUS_REF_NO");
			String m_ref = req.getParameter("REF_NO");



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
			
			
			
			out.println("var arr_asst=new Array();");	
			out.println("var m_row1;");	
			out.println("var m_row2;");
			out.println("var m_row3;");
			out.println("var header;");		
			out.println("var array_asst=new Array();");
			out.println("var array_docu=new Array();");
			out.println("var doc_row=0;");
			out.println("var ln=0;");
			out.println("var ln_row=0;");
			out.println("var len=0;");
			out.println("var len_doc=0;");
			out.println("var row_val=0;");
			
			out.println("var use=0;");
			out.println("var b_val=0;");
			out.println("var m_veh;");
			
			out.println("var othr_docu=new Array();");
			out.println("var othr_docu_det=new Array();");
			out.println("var ln_row_othr=0;");
			
			out.println("var arr_fill=new Array();");
			out.println("var ast_fill=new Array();");
			out.println("var inv_arry=new Array();");
			out.println("var dist=0");
			
			out.println("var new_asset=new Array();");
			out.println("var othr_docs=new Array();");
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
			
			out.println("function get_conditions(){ "); 
			out.println("assig('M_CON')");		
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=COMPLETED\";");
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
			out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand; }\" onclick=\"load_Follow('+lineno+')\" >Follow up</a></TD>'+");//&nbsp;&nbsp;Follow up
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
			out.println("makeRequest1(document.Form1.TXT_PURCHASE_ORDER_NO)");
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
			out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand; }\" onclick=\"load_Follow('+j+')\" >Follow up</a></TD>'+"); //Follow up
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
			out.println("if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value==\"A1\" ){");
			out.println("asset_det(data_vec)");//To display invoice details.
			out.println("new_asset=data_vec");
			out.println("		if(confirm(\"Click to obtain client details?\")){ "); 
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=''){");
			out.println("makeRequest3()");
			out.println("			}");
			out.println("			}");
			out.println("			}");
			out.println("	else if((data_vec.length>0 ||asset_det.length>0) && (document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value==\"A2\") ){");
			out.println("fill_fields(array_asst,data_vec);");//display document details.
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value==\"T1\"){");
			out.println("			}");
			out.println(" else if(data_vec.length>0  && document.Form1.hid_st.value==\"A3\" ){");
			out.println("othr_docu_det=data_vec;");
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=''){");
			out.println("othr_docs=data_vec");
			out.println("display_other(data_vec)");//To display client doc details.
			out.println("			}");
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"A4\" && document.Form1.elements[\"TXT_DISTRICT_CODE\"+document.Form1.hid_val.value]!=\"\"){");
			out.println("if(dist!=1){");
			out.println("help_button_1(document.Form1.hid_val_1.value)");
			out.println("			}");
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"T2\"){");
			out.println("help_update_value_assign_99_copy(data_vec)");
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B1\" && document.Form1.TXT_BRANCH_CODE.value!=''){");
			out.println("    document.Form1.TXT_ACC_NO.value='';");
			out.println("help_button_4('0','10','0','m_help_TXT_BRANCH_CODE_sql','4')");
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B2\" && document.Form1.TXT_ACC_NO.value!=''){");
			out.println("help_button_5('0','10','2','m_help_TXT_ACCOUNT_2_sql','5')");
			out.println("			}");
			out.println("	else if(document.Form1.hid_st.value==\"M_CON\"){");
			out.println("display_data(data_vec)");
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"J1\" && document.Form1.TXT_SETTLE_AMOUNT.value!=''){");
			out.println("alert('Entered amount is greater than balance needed to be paid')");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.value=\"\"");
			out.println("			}");
			out.println("}");

			out.println("function check_branch(obj) {");
			out.println("assig('B1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_branch&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_account(obj) {");
			out.println("assig('B2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_tot() {");
			
			out.println("assig('J1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_settle&data_val1=\"+unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value)+\"&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&inv="+m_ref+"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function assig(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println("}");
			
			out.println("function dd() {");//Use to identify on which text box focus is on.
			out.println("makeRequest3()");
			out.println("}");
			
			out.println("function check_purchase_order() {");
			out.println("assig('T2')");
			if(m_st_val.equals("VERIFY")){//FOR REQUSITION
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_details&ref="+m_ref+"&sus_ref="+m_sus_ref+"&data_val="+m_val+"&ac_status="+m_st_val+"\";");
		}
			else if (m_st_val.equals("B")){
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_details&ref="+m_ref+"&sus_ref="+m_sus_ref+"&data_val="+m_val+"&ac_status=APPRO1\";");
			}
			out.println("load_interface(m_url,'XML');");
			//VERIFY
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("assig('T1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_payment_details_invoice&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj) {");//To get invoice details.
			out.println("assig('A1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Asset_details&inv=\"+document.Form1.TXT_REF_NO.value+\"&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_district(row) {");//To check district.
			out.println("document.Form1.hid_val_1.value=row");
			out.println("di='TXT_DISTRICT_CODE'+row;");
			out.println("assig('A4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_district&data_val=\"+document.Form1.elements[di].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest3() {");//To get client detials.
			out.println("assig('A3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_othr_Doc_details_test&client_code=\"+document.Form1.hid_client.value+\"&m_app_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&screen=AF_CR_PRO_PAYMENT_REQUSITION_MAIN&&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PURCHASE_ORDER_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APP_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_APP_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VENDER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_NET.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_NET.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_VAT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_VAT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
 			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_foll_rem(){");
			out.println("var a=0");
			out.println("b_count=0");
			out.println("for(var f=0;f<document.Form1.hid_cnt_cr.value;f++){");
			out.println("b_flag_1=0");
			out.println("if(document.Form1.hid_cnt_doc.value !=\"0\"){");
			out.println("for(var x=0;x<document.Form1.hid_cnt_doc.value;x++){");
			out.println("  chk2=\"CHK_NAPP\"+f+\"_\"+x;");
			out.println("  chk=\"chkstatus\"+f+\"_\"+x;");
			out.println("fol='TXT_FOL_REM'+f+'_'+x");
			out.println("chkfoll='CHK_FOLL'+f+'_'+x");
			out.println("if(document.Form1.elements[chk2].checked==false && document.Form1.elements[chk].checked==false && document.Form1.elements[chkfoll].checked==false){");
			out.println("b_flag_1=0");
			out.println("b_mesg=0");
			out.println("}");
			out.println("else{");
			out.println(" b_mesg=1");
			out.println("if(document.Form1.elements[chkfoll].checked==true && document.Form1.elements[fol].value==\"\"){");
			out.println("alert('Please enter folloup remark for asset documents')");
			out.println("document.Form1.elements[fol].focus()");		
			out.println("b_flag_1=0");
			out.println("}");
			out.println("else if(document.Form1.elements[chkfoll].checked==false  && document.Form1.elements[fol].value!=\"\"){");
			out.println("b_flag_1=1");
			out.println("}");
			out.println("else{");
			out.println("b_flag_1=1");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("if(b_flag_1==0){");
			out.println("break");	
			out.println("}");
			out.println("}");
			out.println("if(b_flag_1==0){");
			out.println("break");	
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("b_flag_1=1");
			out.println("}");
			out.println("}");
			out.println("if(b_flag_1==1){");
			out.println("for(var j=0;j<document.Form1.hid_oth_doc.value;j++){");
			out.println("  chkw=\"CHK_NAPP_OTH\"+j;");
			out.println("  m_wchk=\"CHK_ST\"+j");
			out.println("if(document.Form1.elements[chkw].checked==false && document.Form1.elements[m_wchk].checked==false && document.Form1.elements[\"CHK_FOLLUP_OTH\"+j].checked==false){");
			out.println("b_mesg1=0");
			out.println("b_flag_1=0");
			out.println("}");
			out.println("else{");
			out.println(" b_mesg1=1");
			out.println("if(document.Form1.elements[\"CHK_FOLLUP_OTH\"+j].checked==true && document.Form1.elements[\"TXT_FOL_REMARK_OTH\"+j].value==''){");
		  out.println("alert('Please enter folloup remark for client documents')");
			out.println("document.Form1.elements[\"TXT_FOL_REMARK_OTH\"+j].focus()");
			out.println("b_flag_1=0");
			out.println("}");
			out.println("else{");
			out.println("b_flag_1=1");
			out.println("}");
			out.println("if(b_flag_1==0){");
			out.println("break");	
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("if(b_flag_1==0 && b_mesg==0){");
			out.println("alert('Please enter either status ,not applicable or follow up for asset details')");	
			out.println("}");
	    out.println("else{");
			out.println("if(b_flag_1==0 && b_mesg1==0){");
			out.println("alert('Please enter either status ,not applicable or follow up for client details')");
			out.println("}");
		  out.println("}");
			out.println("}");



			out.println("function before_submit(){ ");
			out.println("check_foll_rem()");
			out.println("if(b_flag==1 && b_flag_1==1){");
			out.println("check_dates()");
			out.println("var st_1='"+m_st1+"'");
			out.println("if(b_dates==0){");
			out.println("alert('Please enter Insurance Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates1==0){");
			out.println("alert('Please enter Revenue License Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates2==0){");
			out.println("alert('Please enter Luxury Tax Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates3==0){");
			out.println("alert('Please enter Driving License Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_flag==1 && b_flag_1==1 && b_dates==1 &&  b_dates1==1 &&  b_dates2==1 && b_dates3==1){");//
			out.println("if(document.Form1.TXT_ACC_NO.value==\"\"){");
			out.println("alert('Please enter Account No')");
			out.println("document.Form1.TXT_ACC_NO.focus()");
			out.println("}");
			out.println("else if(document.Form1.TXT_BRANCH_CODE.value==\"\"){");
			out.println("alert('Please enter Branch code')");
			out.println("document.Form1.TXT_BRANCH_CODE.focus()");
			out.println("}");
			out.println("if(document.Form1.TXT_SETTLE_AMOUNT.value==\"\"){");
			out.println("alert('Please enter Settle amount')");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.focus()");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.TXT_BRANCH_CODE.value!=\"\" && document.Form1.TXT_ACC_NO.value!=\"\"){");
			out.println("for(var i=0;i<document.Form1.hid_cnt_cr.value;i++){");
			out.println("vh='TXT_VEHICLE_NO'+i;");
			out.println("en='TXT_ENGIN_DOCS'+i;");
			out.println("ch='TXT_CHASI_DOCS'+i;");
		 	out.println("if (document.Form1.elements[vh].value==''){");
			out.println("alert('Please enter Vehicle No');");
			out.println("document.Form1.elements[vh].focus;");
			out.println("}");
   		out.println("else if (document.Form1.elements[en].value==''){");
			out.println("alert('Please enter Engine No');");
			out.println("document.Form1.elements[en].focus();");
			out.println("}");
   		out.println("else if (document.Form1.elements[ch].value==''){");
			out.println("alert('Please enter Chassis No');");
			out.println("document.Form1.elements[ch].focus();");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("if(document.Form1.TXT_BRANCH_CODE.value!=\"\" && document.Form1.TXT_ACC_NO.value!=\"\" && document.Form1.elements[vh].value!=\"\" && document.Form1.elements[en].value!='' && document.Form1.elements[ch].value!=''){");
			out.println("		if(validate_data()){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_payment_details?ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN&number='+document.Form1.hid_cnt_doc.value+'&number1='+document.Form1.hid_cnt_cr.value+'&number2='+document.Form1.hid_oth_doc.value+'&closest='+st_1+'&approval=VERIFY&approval1=R';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("} "); 
			out.println("		}"); 
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("} ");

			out.println("function load_lock(){	"); 
			out.println("check_purchase_order()");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details?PO_NO="+m_val+"&CLSTATUS=A';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details?PO_NO="+m_val+"&CLSTATUS=A';");
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
			out.println("help_box.innerHTML=\" Credit Process - Payment Requisition Approval  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Payment Requisition Approval  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
			out.println("if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value='';"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("    document.Form1.TXT_FINANCE_NO.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_NET.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_VAT.value='';");
			out.println("    document.Form1.TXT_VENDER_CODE.value='';"); 
			out.println("    document.Form1.TXT_NAME.value='';"); 
			out.println("    document.Form1.TXT_APP_NAME.value='';");
			out.println("    document.Form1.TXT_TOTAL.value='';");
			out.println("change1.innerHTML=\"\";");
			out.println("change2.innerHTML=\"\";");
			out.println("change3.innerHTML=\"\";");				
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("    document.Form1.TXT_APP_NAME.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE'+document.Form1.hid_val.value");
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
			out.println(" district='TXT_DISTRICT_CODE'+row");
			out.println("    Crit = document.Form1.elements[district].value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,1);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1(oBj) {"); 
			out.println(" district='TXT_DISTRICT_CODE'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value=oBj.valout[2];"); 
			out.println("dist=\"0\"");
			out.println("}"); 

			out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_VENDER_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_VENDER_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2(oBj) {"); 
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_3(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_APP_NO_sql_1\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APP_NAME.value=oBj.valout[5];"); 
			out.println("}"); 

			out.println("function help_button_4(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_BRANCH_CODE.value+\"@\"+document.Form1.TXT_ACC_NO.value+\"@Y@\";");

			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4(oBj) {"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_ACCOUNT_2_sql\";"); 
			out.println("    Crit = document.Form1.TXT_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5(oBj) {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_6(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_9() {"); 
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    m_sql = \"m_help_TXT_CO_APPLICANT_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CO_APPLICANT.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9(oBj) {"); 
			out.println("    document.Form1.TXT_CO_APPLICANT.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_10() {"); 
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_STAGE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_STAGE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_10(oBj) {"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_11() {"); 
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    m_sql = \"m_help_TXT_REMARK_sql\";"); 
			out.println("    Crit = document.Form1.TXT_REMARK.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_11(oBj) {"); 
			out.println("    document.Form1.TXT_REMARK.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_12() {"); 
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    m_sql = \"m_help_TXT_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_12(oBj) {"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[2];"); 
			out.println("}");
			
						
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 
			out.println("     Crit = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"B@\";"); 
			//VERIFY
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 

						
			out.println("function help_update_value_assign_99(oBj) {"); 
			
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];");
			out.println("makeRequest1(document.Form1.TXT_PURCHASE_ORDER_NO)");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TOTAL_NET.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_TOTAL_VAT.value=oBj.valout[6];");
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_APP_NAME.value=oBj.valout[10];");
			out.println("    document.Form1.TXT_TOTAL.value=oBj.valout[11];");
			out.println("}"); 
						
			out.println("function help_update_value_assign_99_copy(data_vec) {");
			out.println("    document.Form1.hid_client.value=data_vec[15]");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[2];");
			out.println("    document.Form1.TXT_APP_NAME.value=data_vec[5];");
			out.println("    document.Form1.TXT_VENDER_CODE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_NAME.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_TOTAL_NET.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_TOTAL_VAT.value=data_vec[7];");
			out.println("    document.Form1.TXT_TOTAL.value=data_vec[8];");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_DATE.value=data_vec[9];");
			out.println(" if(data_vec[11]==\"\" || data_vec[11]=='null'){");
			out.println(" data_vec[11]=\"\"");
			out.println("    document.Form1.TXT_ACC_NO.value=data_vec[11];");
			out.println("}"); 
			out.println("else {");
			out.println("    document.Form1.TXT_ACC_NO.value=data_vec[11];");
			out.println("}"); 
							
			out.println(" if(data_vec[12]==\"\" || data_vec[12]=='null'){");
			out.println(" data_vec[12]=\"\"");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=data_vec[12];");
			out.println("}"); 
			out.println("else {");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=data_vec[12];");
			out.println("}"); 

			out.println("if(data_vec[3]==data_vec[13]){");
			out.println("document.Form1.TXT_PARTY.selectedIndex=[0]");
			out.println("}");
			out.println("else if(data_vec[3]!=data_vec[13] && (data_vec[13]!=\"\" || data_vec[13]!='null')){");
			out.println("document.Form1.TXT_PARTY.selectedIndex=[1]");

			out.println("}");

			out.println("if(document.Form1.TXT_PARTY.selectedIndex==[1]){");
			out.println("if(data_vec[13]==\"\" || data_vec[13]=='null'){");
			out.println("data_vec[13]=\"\"");
			out.println("}");
			out.println("    document.Form1.hid_payer.value=data_vec[13];");
			out.println("}");
			out.println("enable()");

			out.println("  if(data_vec[14]!='null' || data_vec[14]!=\"\"){");
			out.println("    document.Form1.TXT_FROM_DATE_DD.value=data_vec[14].substring(0,2) ");
			out.println("    document.Form1.TXT_FROM_DATE_MM.value=data_vec[14].substring(3,5) ");
			out.println("    document.Form1.TXT_FROM_DATE_YY.value=data_vec[14].substring(6,10) ");
			out.println("}");
			out.println("  if(data_vec[14]=='null' || data_vec[14]==\"\"){");
			out.println("    document.Form1.TXT_FROM_DATE_DD.value=\"\" ");
			out.println("    document.Form1.TXT_FROM_DATE_MM.value=\"\" ");
			out.println("    document.Form1.TXT_FROM_DATE_YY.value=\"\"");
			out.println("}");
			
			out.println("if(data_vec[15]==\"\" || data_vec[15]=='null'){");
			out.println("data_vec[15]=\"\"");
			out.println("}");
			out.println("get_conditions();");//Added By Nuwan De Silva //07/02/2007.Conditions
			out.println("}"); 
			
			out.println("function check_status(row1,row){")	;
			out.println("  chk2=\"CHK_NAPP\"+row1+\"_\"+row;");
			out.println("  m_chk=\"chkstatus\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[chk2].checked==true && document.Form1.elements[m_chk].checked==true){");
			out.println("document.Form1.elements[m_chk].value='Y'");
			out.println("document.Form1.elements[chk2].checked=false");
			out.println("document.Form1.elements[chk2].value='N'");
			out.println("}else if(document.Form1.elements[m_chk].checked==false && document.Form1.elements[chk2].checked==false){");
			out.println("document.Form1.elements[m_chk].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			
			out.println("function check_change_napp(row1,row){")	;
			out.println("  chk2=\"CHK_NAPP\"+row1+\"_\"+row;");
			out.println("  chk=\"chkstatus\"+row1+\"_\"+row;");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk].checked==true && document.Form1.elements[chk2].checked==true){");
			out.println("document.Form1.elements[chk2].value='Y'");
			out.println("document.Form1.elements[chk].checked=false");
			out.println("document.Form1.elements[chk].value='N'");
			out.println("}else if(document.Form1.elements[chk2].checked==false && document.Form1.elements[chk].checked==false){");
			out.println("document.Form1.elements[chk2].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk2].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk2].value='N'");
			out.println("document.Form1.elements[chk2].checked=false");
			out.println("}");	
			out.println("}");	
			
			
			out.println("function check_status_oth(row){")	;
			out.println("  chkw=\"CHK_NAPP_OTH\"+row;");
			out.println("  m_chkw=\"CHK_ST\"+row");
			out.println("if(document.Form1.elements[chkw].checked==true && document.Form1.elements[m_chkw].checked==true){");
			out.println("document.Form1.elements[chkw].value='Y'");
			out.println("document.Form1.elements[m_chkw].checked=false");
			out.println("document.Form1.elements[m_chkw].value='N'");
			out.println("}else if(document.Form1.elements[m_chkw].checked==false && document.Form1.elements[chkw].checked==false){");
			out.println("document.Form1.elements[m_chkw].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chkw].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			out.println("function check_change_napp_oth(row){")	;
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("  chkw=\"CHK_NAPP_OTH\"+row;");
			out.println("  m_wchk=\"CHK_ST\"+row");
			out.println("if(document.Form1.elements[m_wchk].checked==true && document.Form1.elements[chkw].checked==true){");
			out.println("document.Form1.elements[m_wchk].value='Y'");
			out.println("document.Form1.elements[chkw].checked=false");
			out.println("document.Form1.elements[chkw].value='N'");
			out.println("}else if(document.Form1.elements[chkw].checked==false && document.Form1.elements[m_wchk].checked==false){");
			out.println("document.Form1.elements[chkw].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chkw].value='Y'");
			out.println("}");	
			out.println("}");	
			out.println("}");		
		
		
			out.println("function check_change_fol(row1,row){");
			out.println("  foll_rem=\"TXT_FOL_REM\"+row1+\"_\"+row;");
			out.println("  chkw=\"CHK_FOLL\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[chkw].checked==true){");
			out.println("document.Form1.elements[chkw].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[chkw].checked==false){");
			out.println("document.Form1.elements[chkw].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[chkw].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chkw1=\"CHK_NAPP\"+row1+\"_\"+row;");
			out.println("  m_wchk=\"chkstatus\"+row1+\"_\"+row;");
			out.println(" if((document.Form1.elements[chkw1].checked==false && document.Form1.elements[m_wchk].checked==false) && document.Form1.elements[chkw].checked==false){");
			//out.println("document.Form1.elements[chkw].checked=true");
			//out.println("document.Form1.elements[chkw].value='Y'");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			//out.println("if(b_flag_3==0){");
			//out.println("alert('Please select either status or not applicable or followup')");
			//out.println("}");
			out.println("}");		
		
			out.println("function check_change_fol_oth(row){");
			out.println("  chkwo=\"CHK_FOLLUP_OTH\"+row;");
			out.println("if(document.Form1.elements[chkwo].checked==true){");
			out.println("document.Form1.elements[chkwo].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[chkwo].checked==false){");
			out.println("document.Form1.elements[chkwo].value='N'");
			out.println("}");
			out.println("  chkw1=\"CHK_NAPP_OTH\"+row;");
			out.println("  m_wchk=\"CHK_ST\"+row");
			out.println(" if((document.Form1.elements[chkw1].checked==false && document.Form1.elements[m_wchk].checked==false) && document.Form1.elements[chkwo].checked==false){");
			out.println("b_flag_2=0");
			//out.println("document.Form1.elements[chkwo].checked=true");
			//out.println("document.Form1.elements[chkwo].value='Y'");
			out.println("}");	
			out.println("else{");
			out.println("b_flag_2=1");
			out.println("}");	
			//out.println("if(b_flag_2==0){");
			//out.println("alert('Please select either status or not applicable or followup')");
			//out.println("}");	
			out.println("}");		

			out.println("function check_dates(){");
			out.println("for(var f=0;f<document.Form1.hid_cnt_cr.value;f++){");
			out.println("ind='TXT_INSURANCE_DATE'+f;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+f;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+f;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+f;");
			out.println("rev='TXT_REVENUE_LICENSE_DATE'+ln;");
			out.println("rev_dd='TXT_REVENUE_LICENSE_DATE_DD'+f;");
			out.println("rev_mm='TXT_REVENUE_LICENSE_DATE_MM'+f;");
			out.println("rev_yy='TXT_REVENUE_LICENSE_DATE_YY'+f;");
			out.println("lu='TXT_LUXURY_TAX_DATE'+ln;");
			out.println("lu_dd='TXT_LUXURY_TAX_DATE_DD'+f;");
			out.println("lu_mm='TXT_LUXURY_TAX_DATE_MM'+f;");
			out.println("lu_yy='TXT_LUXURY_TAX_DATE_YY'+f;");
			out.println("drv='TXT_DRIVING_LICENSE_DATE'+f;");
			out.println("drv_dd='TXT_DRIVING_LICENSE_DATE_DD'+f;");
			out.println("drv_mm='TXT_DRIVING_LICENSE_DATE_MM'+f;");
			out.println("drv_yy='TXT_DRIVING_LICENSE_DATE_YY'+f;");
			out.println(" if(document.Form1.elements[ind_dd].value==\"\"){");
			out.println("b_dates=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates=1");
			out.println("}");
			out.println(" if(document.Form1.elements[ind_mm].value==\"\"){");
			out.println("b_dates=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates=1");
			out.println("}");
			out.println(" if(document.Form1.elements[ind_yy].value==\"\"){");
			out.println("b_dates=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates=1");
			out.println("}");
			out.println(" if(document.Form1.elements[rev_dd].value==\"\"){");
			out.println("b_dates1=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates1=1");
			out.println("}");
			out.println(" if(document.Form1.elements[rev_mm].value==\"\"){");
			out.println("b_dates1=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates1=1");
			out.println("}");
			out.println(" if(document.Form1.elements[rev_yy].value==\"\"){");
			out.println("b_dates1=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates1=1");
			out.println("}");
			out.println(" if(document.Form1.elements[lu_dd].value==\"\"){");
			out.println("b_dates2=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates2=1");
			out.println("}");
			out.println(" if(document.Form1.elements[lu_mm].value==\"\"){");
			out.println("b_dates2=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates2=1");
			out.println("}");
			out.println(" if(document.Form1.elements[lu_yy].value==\"\"){");
			out.println("b_dates2=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates2=1");
			out.println("}");
			out.println(" if(document.Form1.elements[drv_dd].value==\"\"){");
			out.println("b_dates3=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates3=1");
			out.println("}");
			out.println(" if(document.Form1.elements[drv_mm].value==\"\"){");
			out.println("b_dates3=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates3=1");
			out.println("}");
			out.println(" if(document.Form1.elements[drv_yy].value==\"\"){");
			out.println("b_dates3=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates3=1");
			out.println("}");
			out.println("if(document.Form1.elements[drv_dd].value==\"\" && document.Form1.elements[drv_mm].value==\"\" && document.Form1.elements[drv_yy].value==\"\"){");
			out.println("b_dates3=1");
			out.println("}");
			out.println("}");
			out.println("}");

			out.println("function check_insu_date(ln){ ");
			out.println("ind='TXT_INSURANCE_DATE'+ln;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+ln;");
			out.println("  document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");

			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			out.println("}");

			out.println("function check_rev_date(ln){ ");
			out.println("ind='TXT_REVENUE_LICENSE_DATE'+ln;");
			out.println("ind_dd='TXT_REVENUE_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_REVENUE_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_REVENUE_LICENSE_DATE_YY'+ln;");
			out.println("  document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" &&  document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			out.println("}");

			
			out.println("function check_tax_date(ln){ ");
			out.println("ind='TXT_LUXURY_TAX_DATE'+ln;");
			out.println("ind_dd='TXT_LUXURY_TAX_DATE_DD'+ln;");
			out.println("ind_mm='TXT_LUXURY_TAX_DATE_MM'+ln;");
			out.println("ind_yy='TXT_LUXURY_TAX_DATE_YY'+ln;");
			out.println("  document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\" ){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			out.println("}");
		
			
			out.println("function check_dri_date(ln){ ");
			out.println("ind='TXT_DRIVING_LICENSE_DATE'+ln;");
			out.println("ind_dd='TXT_DRIVING_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_DRIVING_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_DRIVING_LICENSE_DATE_YY'+ln;");
			out.println("  document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			out.println("}");
	
			out.println("function check_remark(row){");
			out.println("}");
			out.println("function check_remark_othr(row){");
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
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE\"+m_row].value=(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value)");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE\"+m_row].value=(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value)");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE\"+m_row].value=(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value+'-'+document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value)");
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


			out.println("function asset_det(data_vec){");//write invoice details.
			out.println("inv_arry=data_vec");
			out.println("array_asst=data_vec;");
			out.println("b_val=0;");
			out.println("row_val=0;");
			out.println("doc_row=0;");
			out.println("doc_det();");
			out.println("}");
			
			out.println("function doc_det(){");//To get document details.
			out.println("assig('A2');"); 
			out.println("while(doc_row<array_asst.length){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Doc_details_test&m_app_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&screen=AF_CR_PRO_PAYMENT_REQUSITION_MAIN&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&data_val2=\"+array_asst[doc_row]+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("doc_row=doc_row+16;");
			out.println("}");
			out.println("}");
			
			out.println("function fill_fields(asst_vec,doc_vec){");//write invoice details.
			out.println("dd=asst_vec[6].substring(0,2)");
			out.println("mm=asst_vec[6].substring(3,5)");
			out.println("yyyy=asst_vec[6].substring(6,10)");
			out.println("revdd=asst_vec[7].substring(0,2)");
			out.println("revmm=asst_vec[7].substring(3,5)");
			out.println("revyyyy=asst_vec[7].substring(6,10)");
			out.println("taxdd=asst_vec[8].substring(0,2)");
			out.println("taxmm=asst_vec[8].substring(3,5)");
			out.println("taxyyyy=asst_vec[8].substring(6,10)");
			out.println("dridd=asst_vec[10].substring(0,2)");
			out.println("drimm=asst_vec[10].substring(3,5)");
			out.println("driyyyy=asst_vec[10].substring(6,10)");
			
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\"){  "); 
			out.println("if(doc_vec.length==0 ){");
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");									
			out.println("'<tr><td width=\"10%\" class=\"pdn_txtpos1\"><b><u>Invoice No :- '+asst_vec[row_val]+'<input type=\"hidden\" class=\"txt_input\" name=TXT_INVOICE_CODE'+ln+' maxlength=\"50\" size=\"50\" value=\"'+asst_vec[row_val]+'\"></td></tr>'+");
			out.println("'</table>'+");
			out.println("'<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");	
			out.println("'<tr><td width=\"20%\" style=\"{font:bold;text-align:left;}\">Vehicle No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Engine No </td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Chassis No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">CR Book No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">CR Book Printed Date</td>'+"); 
		  out.println("'</tr>'+");

			out.println("'<tr><td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_VEHICLE_NO'+ln+' value=\"'+asst_vec[row_val+5]+'\" maxlength=\"20\" size=\"20\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_ENGIN_DOCS'+ln+' value=\"\" maxlength=\"20\" size=\"20\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_CHASI_DOCS'+ln+' value=\"\" maxlength=\"20\" size=\"21\"></td>'+");		
				
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_CR_BOOK'+ln+' value=\"'+asst_vec[12]+'\" maxlength=\"20\" size=\"20\" ></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD'+ln+' value=\"'+asst_vec[13]+'\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM'+ln+' value=\"'+asst_vec[14]+'\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY'+ln+' value=\"'+asst_vec[15]+'\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy('+ln+')\">'+");
			out.println("'</td>'+");		
			out.println("'</tr>'+");
			out.println("'<tr>'+");
		  out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Insurance Date</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Revenue License Date</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:v;}\">Luxury Tax Date</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Driving License Date</td>'+");
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">District Code</td></tr>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD'+ln+' value=\"'+dd+'\" maxlength=2 size=\"2\" onblur=\"check_insu_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM'+ln+' value=\"'+mm+'\" maxlength=2 size=\"2\" onblur=\"check_insu_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY'+ln+' value=\"'+yyyy+'\" maxlength=4 size=\"4\" onblur=\"check_insu_date('+ln+')\" >'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE'+ln+'  value=\"'+asst_vec[6]+'\"  maxlength=10 size=\"10\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD'+ln+' value=\"'+revdd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM'+ln+' value=\"'+revmm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY'+ln+' value=\"'+revyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE'+ln+' value=\"'+asst_vec[7]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD'+ln+' value=\"'+taxdd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM'+ln+' value=\"'+taxmm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY'+ln+' value=\"'+taxyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE'+ln+' value=\"'+asst_vec[8]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
		  out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD'+ln+' value=\"'+dridd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM'+ln+' value=\"'+drimm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY'+ln+' value=\"'+driyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE'+ln+' value=\"'+asst_vec[10]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DISTRICT_CODE'+ln+' value='+asst_vec[row_val+9]+' maxlength=\"10\" size=\"20\" onblur=\"check_district('+ln+')\">'+");		
			out.println("'<input class=but_input type=button name=BUT_TXT_DISTRICT_CODE'+ln+' value=\"Help\" onClick=help_button_1('+ln+')></td>'+"); 
			out.println("'</tr></table>';");
			out.println("vh='TXT_VEHICLE_NO'+ln;");
   		out.println("if (document.Form1.elements[vh].value=='##'){");
			out.println("document.Form1.elements[vh].value='';");
			out.println("}");
			out.println("en='TXT_ENGIN_DOCS'+ln;");
   		out.println("if (document.Form1.elements[en].value=='##'){");
			out.println("document.Form1.elements[en].value='';");
			out.println("}");
			out.println("ch='TXT_CHASI_DOCS'+ln;");
   		out.println("if (document.Form1.elements[ch].value=='##'){");
			out.println("document.Form1.elements[ch].value='';");
			out.println("}");
			out.println("ind='TXT_INSURANCE_DATE'+ln;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[ind].value=='##'){");
			out.println("document.Form1.elements[ind].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_dd].value=='##'){");
			out.println("document.Form1.elements[ind_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_mm].value=='##'){");
			out.println("document.Form1.elements[ind_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_yy].value=='##'){");
			out.println("document.Form1.elements[ind_yy].value='';");
			out.println("}");
			out.println("re='TXT_REVENUE_LICENSE_DATE'+ln;");
			out.println("re_dd='TXT_REVENUE_LICENSE_DATE_DD'+ln;");
			out.println("re_mm='TXT_REVENUE_LICENSE_DATE_MM'+ln;");
			out.println("re_yy='TXT_REVENUE_LICENSE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[re].value=='##'){");
			out.println("document.Form1.elements[re].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[re_dd].value=='##'){");
			out.println("document.Form1.elements[re_dd].value='';");
			out.println("}");
   		out.println("if (document.Form1.elements[re_mm].value=='##'){");
			out.println("document.Form1.elements[re_mm].value='';");
			out.println("}");
   		out.println("if (document.Form1.elements[re_yy].value=='##'){");
			out.println("document.Form1.elements[re_yy].value='';");
			out.println("}");
			out.println("lu='TXT_LUXURY_TAX_DATE'+ln;");
			out.println("lu_dd='TXT_LUXURY_TAX_DATE_DD'+ln;");
			out.println("lu_mm='TXT_LUXURY_TAX_DATE_MM'+ln;");
			out.println("lu_yy='TXT_LUXURY_TAX_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[lu].value=='##'){");
			out.println("document.Form1.elements[lu].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_dd].value=='##'){");
			out.println("document.Form1.elements[lu_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_mm].value=='##'){");
			out.println("document.Form1.elements[lu_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_yy].value=='##'){");
			out.println("document.Form1.elements[lu_yy].value='';");
			out.println("}");
			out.println("di='TXT_DISTRICT_CODE'+ln;");
   		out.println("if (document.Form1.elements[di].value=='##'){");
			out.println("document.Form1.elements[di].value='';");
			out.println("}");
			out.println("dr='TXT_DRIVING_LICENSE_DATE'+ln;");
			out.println("dr_dd='TXT_DRIVING_LICENSE_DATE_DD'+ln;");
			out.println("dr_mm='TXT_DRIVING_LICENSE_DATE_MM'+ln;");
			out.println("dr_yy='TXT_DRIVING_LICENSE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[dr].value=='##'){");
			out.println("document.Form1.elements[dr].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_dd].value=='##'){");
			out.println("document.Form1.elements[dr_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_mm].value=='##'){");
			out.println("document.Form1.elements[dr_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_yy].value=='##'){");
			out.println("document.Form1.elements[dr_yy].value='';");
			out.println("}");
			out.println("doc_det();");	
		  out.println("}");
			
	   	out.println("else");
				
			out.println("if(doc_vec.length>0 ){");
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><tr>'+");									
			out.println("'<td width=\"10%\" class=\"pdn_txtpos1\"><b><u>Invoice No :- '+asst_vec[row_val]+'<input class=\"txt_input\" type=\"hidden\" name=TXT_INVOICE_CODE'+ln+' maxlength=\"50\" size=\"50\" value=\"'+asst_vec[row_val]+'\"></td></tr>'+");
			out.println("'</table>'+");
			out.println("'<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");	
			out.println("'<tr><td width=\"20%\" style=\"{font:bold;text-align:left;}\">Vehicle No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Engine No </td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Chassis No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">CR Book No</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">CR Book Printed Date</td>'+"); 
	
			out.println("'</tr>'+");
			out.println("'<tr><td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_VEHICLE_NO'+ln+' value=\"'+asst_vec[row_val+5]+'\" maxlength=\"20\" size=\"20\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_ENGIN_DOCS'+ln+' value=\"\" maxlength=\"20\" size=\"20\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_CHASI_DOCS'+ln+' value=\"\" maxlength=\"20\" size=\"21\"></td>'+");		
		
					out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:left;}\" class=\"txt_input5\" name=TXT_CR_BOOK'+ln+' value=\"'+asst_vec[12]+'\" maxlength=\"20\" size=\"20\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD'+ln+' value=\"'+asst_vec[13]+'\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM'+ln+' value=\"'+asst_vec[14]+'\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY'+ln+' value=\"'+asst_vec[15]+'\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy('+ln+')\">'+");
			out.println("'</td>'+");
			out.println("'</tr>'+");
			out.println("'<tr>'+");

			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Insurance Date</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Revenue License Date</td>'+"); 
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Luxury Tax Date</td>'+"); 		
			
			
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">Driving License Date</td>'+");
			out.println("'<td width=\"20%\" style=\"{font:bold;text-align:left;}\">District Code</td></tr>'+"); 
			out.println("'<tr>'+");

			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD'+ln+' value=\"'+dd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_insu_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM'+ln+' value=\"'+mm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_insu_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY'+ln+' value=\"'+yyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_insu_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE'+ln+' value=\"'+asst_vec[row_val+6]+'\"  maxlength=\"10\" size=\"10\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD'+ln+' value=\"'+revdd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM'+ln+' value=\"'+revmm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY'+ln+' value=\"'+revyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE'+ln+' value=\"'+asst_vec[7]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD'+ln+' value=\"'+taxdd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM'+ln+' value=\"'+taxmm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY'+ln+' value=\"'+taxyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE'+ln+' value=\"'+asst_vec[8]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
			
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD'+ln+' value=\"'+dridd+'\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM'+ln+' value=\"'+drimm+'\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY'+ln+' value=\"'+driyyyy+'\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date('+ln+')\">'+");		
			out.println("'<input type=\"hidden\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE'+ln+' value=\"'+asst_vec[10]+'\" maxlength=\"10\" size=\"10\"></td>'+");		
	
			
			out.println("'<td width=\"20%\"><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DISTRICT_CODE'+ln+' value='+asst_vec[row_val+9]+' maxlength=\"10\" size=\"20\" onblur=\"check_district('+ln+')\">'+");		
			out.println("'<input class=but_input type=button name=BUT_TXT_DISTRICT_CODE'+ln+' value=\"Help\" onClick=help_button_1('+ln+')></td>'+"); 
			
			
			
			out.println("'</tr></table>';");
			out.println("vh='TXT_VEHICLE_NO'+ln;");
   		out.println("if (document.Form1.elements[vh].value=='##'){");
			out.println("document.Form1.elements[vh].value='';");
			out.println("}");
			out.println("en='TXT_ENGIN_DOCS'+ln;");
   		out.println("if (document.Form1.elements[en].value=='##'){");
			out.println("document.Form1.elements[en].value='';");
			out.println("}");
			out.println("ch='TXT_CHASI_DOCS'+ln;");
   		out.println("if (document.Form1.elements[ch].value=='##'){");
			out.println("document.Form1.elements[ch].value='';");
			out.println("}");
			out.println("ind='TXT_INSURANCE_DATE'+ln;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[ind].value=='##'){");
			out.println("document.Form1.elements[ind].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_dd].value=='##'){");
			out.println("document.Form1.elements[ind_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_mm].value=='##'){");
			out.println("document.Form1.elements[ind_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[ind_yy].value=='##'){");
			out.println("document.Form1.elements[ind_yy].value='';");
			out.println("}");
			out.println("re='TXT_REVENUE_LICENSE_DATE'+ln;");
			out.println("re_dd='TXT_REVENUE_LICENSE_DATE_DD'+ln;");
			out.println("re_mm='TXT_REVENUE_LICENSE_DATE_MM'+ln;");
			out.println("re_yy='TXT_REVENUE_LICENSE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[re].value=='##'){");
			out.println("document.Form1.elements[re].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[re_dd].value=='##'){");
			out.println("document.Form1.elements[re_dd].value='';");
			out.println("}");
   		out.println("if (document.Form1.elements[re_mm].value=='##'){");
			out.println("document.Form1.elements[re_mm].value='';");
			out.println("}");
   		out.println("if (document.Form1.elements[re_yy].value=='##'){");
			out.println("document.Form1.elements[re_yy].value='';");
			out.println("}");
			out.println("lu='TXT_LUXURY_TAX_DATE'+ln;");
			out.println("lu_dd='TXT_LUXURY_TAX_DATE_DD'+ln;");
			out.println("lu_mm='TXT_LUXURY_TAX_DATE_MM'+ln;");
			out.println("lu_yy='TXT_LUXURY_TAX_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[lu].value=='##'){");
			out.println("document.Form1.elements[lu].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_dd].value=='##'){");
			out.println("document.Form1.elements[lu_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_mm].value=='##'){");
			out.println("document.Form1.elements[lu_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[lu_yy].value=='##'){");
			out.println("document.Form1.elements[lu_yy].value='';");
			out.println("}");
			out.println("di='TXT_DISTRICT_CODE'+ln;");
   		out.println("if (document.Form1.elements[di].value=='##'){");
			out.println("document.Form1.elements[di].value='';");
			out.println("}");
			out.println("dr='TXT_DRIVING_LICENSE_DATE'+ln;");
			out.println("dr_dd='TXT_DRIVING_LICENSE_DATE_DD'+ln;");
			out.println("dr_mm='TXT_DRIVING_LICENSE_DATE_MM'+ln;");
			out.println("dr_yy='TXT_DRIVING_LICENSE_DATE_YY'+ln;");
   		out.println("if (document.Form1.elements[dr].value=='##'){");
			out.println("document.Form1.elements[dr].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_dd].value=='##'){");
			out.println("document.Form1.elements[dr_dd].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_mm].value=='##'){");
			out.println("document.Form1.elements[dr_mm].value='';");
			out.println("}");
			out.println("if (document.Form1.elements[dr_yy].value=='##'){");
			out.println("document.Form1.elements[dr_yy].value='';");
			out.println("}");
			out.println("fill_docs(doc_vec,ln);");
			out.println("doc_det();");
			out.println("}");	
			out.println("row_val=row_val+16;");
		 	out.println("ln=ln+1;");
			out.println("len=len+1;");
			out.println("document.Form1.hid_cnt_cr.value=ln");	
			out.println("}"); 
			out.println("}"); 
		
			out.println("function fill_docs(doc_vec,row){");//write document details.
			out.println("var i=0;");
			out.println("var ln_row=0;");
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\"){  "); 
			out.println("m_header='<HR><br><table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+");
			out.println("'<td width=\"13%\" align=\"left\" style=\"{font:bold;}\">Description</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\"  style=\"{font:bold;}\">Previous Status</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Previous Remarks</td>'+"); 
			out.println("'<td width=\"10%\" align=\"center\" style=\"{font:bold;}\">Status</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Not Applicable</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Remarks </td>'+"); 
		  out.println("'<td width=\"12%\" align=\"center\" style=\"{font:bold;}\">Followup</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Followup Remarks </td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';");
			out.println("m_system='';");	 
			out.println("while(i<doc_vec.length){");
			out.println("m_desc='<td width=\"13%\">'+doc_vec[i+2].replace('$','&')+'</td>';");	
			out.println("m_status_pre='';");	
			out.println("if(doc_vec[i+4]=='Y'){");
			out.println("m_status_pre='<td width=\"13%\" align=\"center\">Yes<input type=\"hidden\" name=CHK_STATUS_PRE'+ln_row+' value=\"Y\" checked disabled></td>';");			
			out.println("}");
			out.println("else if(doc_vec[i+4]=='A'){");
			out.println("m_status_pre='<td width=\"13%\" align=\"center\">Not Applicable<input type=\"hidden\" name=CHK_STATUS_PRE'+ln_row+' value=\"N\" unchecked disabled></td>';");			
			out.println("}");
			
			out.println("else if(doc_vec[i+4]=='N'){");
			out.println("m_status_pre='<td width=\"13%\" align=\"center\">No<input type=\"hidden\" name=CHK_STATUS_PRE'+ln_row+' value=\"N\" unchecked disabled></td>';");			
			out.println("}");
			out.println("else {");
			out.println("m_status_pre='<td width=\"13%\" align=\"center\">-<input type=\"hidden\" name=CHK_STATUS_PRE'+ln_row+' value=\"\" checked disabled></td>';");			
			out.println("}");
			
			out.println("if(doc_vec[i+3]=='null'){");
			out.println("doc_vec[i+3]='-'");
			out.println("m_remark_pre='<td width=\"13%\" align=\"center\">'+doc_vec[i+3]+'<input type=\"hidden\" class=\"txt_input\" name=TXT_REMARK_PRE'+ln_row+' value=\"'+doc_vec[i+3]+'\" maxlength=\"100\" size=\"20\" disabled></td>';");		
			out.println("}");
			out.println("else if(doc_vec[i+3]!='null'){");
			out.println("m_remark_pre='<td width=\"13%\" align=\"center\">'+doc_vec[i+3]+'<input type=\"hidden\" class=\"txt_input\" name=TXT_REMARK_PRE'+ln_row+' value=\"'+doc_vec[i+3]+'\" maxlength=\"100\" size=\"20\" disabled></td>';");		
			out.println("}");
			//************
			out.println("m_status_f='<td width=\"13%\" align=\"center\"><input type=\"checkbox\" name=chkstatus'+row+'_'+ln_row+' value=\"N\" unchecked onclick=\"check_status('+row+','+ln_row+')\" onblur=\"check_change_fol('+row+','+ln_row+')\"></td>';");			
			out.println("if(doc_vec[i+4]=='A'){");
			out.println("m_napp_f='<td width=\"13%\" align=\"center\"><input type=\"checkbox\" name=CHK_NAPP'+row+'_'+ln_row+' value=\"Y\" checked onclick=\"check_change_napp('+row+','+ln_row+')\" onblur=\"check_change_fol('+row+','+ln_row+')\"></td>';");
			out.println("}");
			out.println("else {");
			out.println("m_napp_f='<td width=\"13%\" align=\"center\"><input type=\"checkbox\" name=CHK_NAPP'+row+'_'+ln_row+' value=\"N\" unchecked onclick=\"check_change_napp('+row+','+ln_row+')\" onblur=\"check_change_fol('+row+','+ln_row+')\"></td>';");
			out.println("}");
			
			out.println("m_remark='<td width=\"13%\" align=\"center\"><input type=\"text\" class=\"txt_input\" name=TXT_REMARK_NEW'+row+'_'+ln_row+' value=\"\" maxlength=\"100\" size=\"20\" ></td>';");		
			out.println("m_follup='<td width=\"12%\" align=\"center\"><input type=\"checkbox\" name=\"CHK_FOLL'+row+'_'+ln_row+'\" value=\"N\" unchecked onclick=\"check_change_fol('+row+','+ln_row+')\" onblur=\"check_change_fol('+row+','+ln_row+')\"><input type=\"hidden\" class=\"txt_input\" name=\"TXT_INV'+row+'_'+ln_row+'\" maxlength=\"100\" size=\"20\" value=\"'+doc_vec[i]+'\"></td>';");
			out.println("m_fol_remark='<td width=\"13%\" align=\"center\"><input type=\"text\" class=\"txt_input\" name=\"TXT_FOL_REM'+row+'_'+ln_row+'\" value=\"\" maxlength=\"100\" size=\"20\" onblur=\"check_remark('+ln_row+')\" ></td>';");		
			
			out.println("m_hid_input='<input type=\"Hidden\" name=hid_TXT_DOC_CODE'+ln_row+' value='+doc_vec[i+1]+'>'+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_DESCRIPTION'+ln_row+'	VALUE='+doc_vec[i+2]+'>'+");
		 	out.println("'<input type=\"Hidden\" name=hid_TXT_FROM_SCREEN'+ln_row+'	VALUE='+doc_vec[i+5]+'>'+");
		 	out.println("'<input type=\"Hidden\" name=hid_TXT_TO_SCREEN'+ln_row+'	VALUE='+doc_vec[i+6]+'>';");
			out.println("if(doc_vec[i+4]=='A'){");
			out.println("m_system='<br><tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM  class=div_input style=color:red><b><u>System Exception</div></td></tr>';");
			out.println("m_row3='<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>'+m_desc+m_status_pre+m_remark_pre+m_status_f+m_napp_f+m_remark+m_follup+m_fol_remark+'</tr><tr></tr><tr></tr>'+m_hid_input;");
			out.println("}");
			out.println("else{");
			out.println("m_row3='<br><tr>'+m_desc+m_status_pre+m_remark_pre+m_status_f+m_napp_f+m_remark+m_follup+m_fol_remark+'</tr><tr></tr><tr></tr>'+m_hid_input;");
			out.println("}");
			out.println("array_docu[ln_row]=m_row3;");
			out.println("i=i+7;");//20070108
			out.println("ln_row=ln_row+1;");//--
			out.println("}");
			out.println("change1.innerHTML+=m_header+m_system+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
	    out.println("array_docu.join(\" \")+'</table>'+'<table align=\"center\" border\"4\" width=\"100%\" class=\"table\">'+");
	   	out.println("'</table>';");
			out.println("document.Form1.hid_cnt_doc.value=ln_row");	
			out.println("}");
			out.println("}");

			
			out.println("function header(){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");
			out.println("'<td width=\"20%\" class=\"pdn_txtpos1\"><u>Invoice No</b></td>'+");
			out.println("'</tr></table><br>';");
			out.println("b_val=1;");
     	out.println("}");
			
			out.println("function display_other(doc_vec_othr){");//To display client details.
			out.println("change2.innerHTML=\"\";");
			out.println("var i=0;");
			out.println("ln_row_othr=0;");	
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\"){  "); 
			out.println("m_header='<HR><table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td class=\"pdn_txtpos1\" style=\"{font:bold;}\"><u>Other Documents</td>'+");
			out.println("'</tr >'+");
			out.println("'</table>';");
			out.println("m_header=m_header+'<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'<td width=\"13%\" align=\"left\" style=\"{font:bold;}\">Description</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\"  style=\"{font:bold;}\">Previous Status</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Previous Remarks</td>'+"); 
			out.println("'<td width=\"10%\" align=\"center\" style=\"{font:bold;}\">Status</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Not Applicable</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Remarks </td>'+"); 
		  out.println("'<td width=\"12%\" align=\"center\" style=\"{font:bold;}\">Followup</td>'+"); 
			out.println("'<td width=\"13%\" align=\"center\" style=\"{font:bold;}\">Followup Remarks </td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr >'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';");
			out.println("m_system_1=''");
			out.println("if(doc_vec_othr.length==0){");
			out.println("m_desc='';");	
			out.println("m_status='';");			
			out.println("m_napp_oth='';");			
			out.println("m_remark='';");		
			out.println("m_hid_input1=''+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_OTH_DES'+ln_row_othr+'	VALUE=\"\">';");
 			out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("while(i<doc_vec_othr.length){");
			out.println("m_descd='<td width=\"13%\">'+doc_vec_othr[i+2].replace('$','&')+'</td>';");	
			out.println("if(doc_vec_othr[i+4]=='Y'){");
			out.println("m_status_pred='<td width=\"13%\" align=\"center\">Yes<input type=\"hidden\" name=CHK_ST_PRE'+ln_row_othr+' VALUE=\"Y\" checked disabled></td>';");			
			out.println("}");
			out.println("else if( doc_vec_othr[i+4]=='A'){");
			out.println("m_status_pred='<td width=\"13%\" align=\"center\">Not Applicable<input type=\"hidden\" name=CHK_ST_PRE'+ln_row_othr+' VALUE=\"N\" unchecked disabled></td>';");			
			out.println("}");
			out.println("else if(doc_vec_othr[i+4]=='N' ){");
			out.println("m_status_pred='<td width=\"13%\" align=\"center\">No<input type=\"hidden\" name=CHK_ST_PRE'+ln_row_othr+' VALUE=\"N\" unchecked disabled></td>';");			
			out.println("}");
			out.println("else{");
			out.println("m_status_pred='<td width=\"13%\" align=\"center\">-<input type=\"hidden\" name=CHK_ST_PRE'+ln_row_othr+' VALUE=\"N\" unchecked disabled></td>';");			
			out.println("}");
			
			out.println("if(doc_vec_othr[i+3]=='null'){");
			out.println("doc_vec_othr[i+3]='-'");
			out.println("m_remark_pred='<td width=\"13%\" align=\"center\">'+doc_vec_othr[i+3]+'<input type=\"hidden\" class=\"txt_input\" name=TXT_OTH_REMARK_PRE'+ln_row_othr+' value='+doc_vec_othr[i+3]+' maxlength=\"100\" size=\"20\"></td>';");		
			out.println("}");

			out.println("if(doc_vec_othr[i+3]!='null'){");
			out.println("m_remark_pred='<td width=\"13%\" align=\"center\">'+doc_vec_othr[i+3]+'<input type=\"hidden\" class=\"txt_input\" name=TXT_OTH_REMARK_PRE'+ln_row_othr+' value='+doc_vec_othr[i+3]+' maxlength=\"100\" size=\"20\"></td>';");		
			out.println("}");
			out.println("m_statusd='<td width=\"10%\" align=\"center\"><input type=\"checkbox\" name=CHK_ST'+ln_row_othr+' VALUE=\"N\" unchecked onclick=\"check_status_oth('+ln_row_othr+')\" onblur=\"check_change_fol_oth('+ln_row_othr+')\"></td>';");			
			out.println("if(doc_vec_othr[i+4]=='A'){");
			out.println("m_napp_othd='<td width=\"13%\" align=\"center\"><input type=\"checkbox\" name=CHK_NAPP_OTH'+ln_row_othr+' value=\"Y\" checked onclick=\"check_change_napp_oth('+ln_row_othr+')\" onblur=\"check_change_fol_oth('+ln_row_othr+')\"></td>';");			
			out.println("}");
			out.println("else{");
			out.println("m_napp_othd='<td width=\"13%\" align=\"center\"><input type=\"checkbox\" name=CHK_NAPP_OTH'+ln_row_othr+' value=\"N\" unchecked onclick=\"check_change_napp_oth('+ln_row_othr+')\" onblur=\"check_change_fol_oth('+ln_row_othr+')\"></td>';");			
			out.println("}");
			out.println("m_remarkd='<td width=\"13%\" align=\"center\"><input type=\"text\" class=\"txt_input\" name=TXT_OTH_REMARK'+ln_row_othr+' value=\"\" maxlength=\"50\" onblur=\"check_remark('+ln_row_othr+')\" ></td>';");		
			out.println("m_follup_oth='<td width=\"12%\" align=\"center\"><input type=\"checkbox\" name=CHK_FOLLUP_OTH'+ln_row_othr+' value=\"N\" unchecked onclick=\"check_change_fol_oth('+ln_row_othr+')\" onblur=\"check_change_fol_oth('+ln_row_othr+')\"></td>';");
			out.println("m_follup_remark_oth='<td width=\"13%\" align=\"center\"><input type=\"text\" class=\"txt_input\" name=TXT_FOL_REMARK_OTH'+ln_row_othr+' value=\"\" maxlength=\"100\" onblur=\"check_remark('+ln_row_othr+')\" ></td>';");		
			out.println("m_hid_input1='<input type=\"Hidden\" name=hid_TXT_OTH_DOC'+ln_row_othr+'	value='+doc_vec_othr[i+1]+'>'+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_OTH_DES'+ln_row_othr+' VALUE='+doc_vec_othr[i+2]+'>'+");
		  out.println("'<input type=\"Hidden\" name=hid_TXT_FROM_OTH_SCREEN'+ln_row_othr+' VALUE='+doc_vec_othr[i+5]+'>'+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_TO_OTH_SCREEN'+ln_row_othr+' VALUE='+doc_vec_othr[i+6]+'>';");
			out.println("if(doc_vec_othr[i+4]=='A'){");
			out.println("m_system_1='<br><tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>';");
			out.println("m_row4='<br><tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>'+m_descd+m_status_pred+m_remark_pred+m_statusd+m_napp_othd+m_remarkd+m_follup_oth+m_follup_remark_oth+'</tr>'+m_hid_input1;");
			out.println("}");
			out.println("else{");
			out.println("m_row4='<tr>'+m_descd+m_status_pred+m_remark_pred+m_statusd+m_napp_othd+m_remarkd+m_follup_oth+m_follup_remark_oth+'</tr>'+m_hid_input1;");
			out.println("}");
     	out.println("othr_docu[ln_row_othr]=m_row4;");
			out.println("ln_row_othr=ln_row_othr+1;");//--
		 	out.println("i=i+7;");
			out.println("}"); 
			out.println("}");
			out.println("change2.innerHTML=m_header+m_system_1+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
	    out.println("othr_docu.join(\" \")+'</table>';");

			out.println("document.Form1.hid_oth_doc.value=ln_row_othr");	
			out.println("}");
			out.println("}");


			out.println("function close_screen(){");
			if(!m_st1.equals("A")){
			out.println("close_window()");
			}
			else if(m_st1.equals("A")){
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?chksql="+m_st_val+"&chksql2="+m_st_val1+"&st_c=APPLICATION_NO&oby=ASC';");
			}
			out.println("}");
			
			
			out.println("function app_screen(){"); 
			out.println("		if(confirm(\"Are you sure you want to view Main Approval Screen?\")){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?ac_status=A&CLSTATUS=A&screen=APP1';"); 
	    out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			out.println("}");

			out.println("function enable() {");
			out.println("if(document.Form1.TXT_PARTY.selectedIndex==[1]){"); 
			out.println("DIV_TXT_PAYER_NAME1.innerHTML='<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_PAYER_NAME\"  class=div_input>Payer Name</DIV></td>';"); 
			out.println("payer.innerHTML='<td width=\"30%\" >'+"); 
			out.println("'<input class=\"txt_input\" type=text name=TXT_PAYER maxlength=\"200\" size=\"15\" value=\"'+document.Form1.hid_payer.value+'\" >'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>';"); 

			out.println("}"); 
			out.println("if(document.Form1.TXT_PARTY.selectedIndex==[0]){"); 
			out.println("DIV_TXT_PAYER_NAME1.innerHTML=\"\""); 
			out.println("payer.innerHTML=\"\""); 
			out.println("}"); 
		  out.println("}"); 

			out.println("function check_date_from(){ ");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" || document.Form1.TXT_FROM_DATE_MM.value!=\"\" || document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");

			out.println("}");

			out.println("function check_amt() {");
			out.println("if(document.Form1.TXT_SETTLE_AMOUNT.value!=\"\"){");
			out.println("format_number(document.Form1.TXT_SETTLE_AMOUNT,27)");
			out.println("check_tot()");
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

			out.println("<INPUT TYPE='Hidden' NAME='hid_inv' VALUE=\"0\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_client' VALUE=\"\">");
		
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Payment Requisition Approval </td>"); 
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
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PURCHASE_ORDER_NO'  class=div_input>Purchase Order No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onblur=\"makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('0','10','7','m_help_TXT_PURCHASE_ORDER_NO_sql','99')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PURCHASE_ORDER_DATE'  class=div_input>Purchase Order Date *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_DATE' maxlength='15' size='15' onblur=\"makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO)\" disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='20' disabled>"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_3('0','10','0','m_help_TXT_APP_NO_sql_1','3')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_APP_NAME'  class=div_input>Name of the Applicant*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style='{width:200px}' type='text' name='TXT_APP_NAME' maxlength='200' size='50' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VENDER_CODE'  class=div_input>Supplier Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VENDER_CODE' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>Supplier Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style='{width:200px}' type='text' name='TXT_NAME' maxlength='200' size='50' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_NET'  class=div_input>Net Amount</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{text-align:right;}\" type='text' name='TXT_TOTAL_NET' maxlength='22' size='22' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_VAT'  class=div_input>VAT Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{text-align:right;}\" type='text' name='TXT_TOTAL_VAT' maxlength='22' size='22' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{text-align:right;}\" type='text' name='TXT_TOTAL' maxlength='22' size='22' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ACC_NO'  class=div_input>Account No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='20' size='20' onblur=\"check_account(document.Form1.TXT_ACC_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_NO' value=\"Help\" onClick=\"help_button_5('0','10','2','m_help_TXT_ACCOUNT_2_sql','5')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'  onblur=\"check_branch(document.Form1.TXT_BRANCH_CODE)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_CAT'  class=div_input>Payee Category</DIV></td>"); 
			out.println("<td width=\"40%\" align=\"left\"><select name=TXT_PARTY class=\"txt_input3\" onChange=\"enable()\">");
			out.println("<OPTION value=\"2\" selected >Normal </option>");
			out.println("<OPTION value=\"1\">3rd Party</option>");

			out.println("</SELECT></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYER_NAME1'  class=div_input></DIV></td>"); 
			out.println("<td width='30%' ><div id=payer></div>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width=\"30%\" align=\"left\" >3rd Party Letter Date</td>"); 
			out.println("<TD WIDTH=\"40%\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from()\"><a href style='{cursor:hand; }' onclick=load_calendar('6',1)>   Calendar</a></td> ");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_AMOUNT'  class=div_input>Settle Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{text-align:right;}\" type='text' name='TXT_SETTLE_AMOUNT' maxlength='22' size='22' onblur=\"check_amt()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("</table>");
			
					out.println("<HR>");
			out.println("<br>");

			//-----------------


		 rs3=stmt3.executeQuery("SELECT distinct SUS_REF_NO,REF_NO "+
		 "FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
		 "WHERE  SUS_REF_NO='"+m_sus_ref+"' "+
		 "AND REF_NO='"+m_ref+"' ");
		boolean more3=rs3.next();
		
		 rs=stmt.executeQuery("SELECT distinct SUS_REF_NO,REF_NO,TO_CHAR(BAL_TO_BE_PAID,'999,999,999,999.99'),CURR_CODE,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
		 "FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
		 "WHERE  SUS_REF_NO='"+m_sus_ref+"' "+
		 "AND REF_NO='"+m_ref+"' ");

		
			int q=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs.next()){

			if(q==0){
						out.println("<tr><td><b><u>Suspend Payment Details</td></tr>"); 
						out.println("<tr></tr>");
						out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' align=\"left\">Suspend Reference No</td>"); 
			out.println("<td width='20%' align=\"left\">Refernce No</td>"); 
			out.println("<td width='20%' align=\"right\">Balance to be paid</td>"); 
			out.println("<td width='20%' align=\"left\">Currency Code</td>"); 
			out.println("<td width='20%' align=\"left\">Value Date</td>"); 
			
			out.println("</tr >"); 

			}
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<tr >"); 
			out.println("<td width='20%' align=\"left\">"+rs.getString(1)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_SUS_REF_NO_"+q+" maxlength='27' size='22' value=\""+rs.getString(1)+"\"></td>"); 
			out.println("<td width='20%'  align=\"left\">"+rs.getString(2)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_REF_"+q+" maxlength='27' size='22' value=\""+rs.getString(2)+"\"></td>"); 
			out.println("<td width='20%'  align=\"right\">"+rs.getString(3)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_BAL_"+q+" maxlength='27' size='22' value=\""+rs.getString(3)+"\"></td>"); 
			out.println("<td width='20%'  align=\"left\">"+rs.getString(4)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_CUR_"+q+" maxlength='27' size='22' value=\""+rs.getString(4)+"\"></td>"); 
			out.println("<td width='20%'  align=\"left\">"+rs.getString(5)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_SUS_DATE_"+q+" maxlength='27' size='22' value=\""+rs.getString(5)+"\"></td>"); 
			
			
			out.println("</tr>");
			q=q+1;

			}
			out.println("<input class='txt_input' type='hidden' name=TXT_REF_NO value=\""+rs3.getString(2)+"\"></td>"); 

			out.println("</table >"); 
			
			//-----------------
			out.println("<HR>");
			out.println("<br>");

			rs1=stmt1.executeQuery("select A.requ_no,trim(nvl(TO_CHAR(INVOICE_SET_AMT,'999,999,999,999,999,999,999,999.99'),0)),to_char(A.mod_date,'dd-mm-yyyy') from "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT A,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET B where purchase_order_no='"+m_val+"' and A.REQU_NO=B.REQU_NO AND INVOICE_NO='"+m_ref+"'");

		
			int a=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs1.next()){

			if(a==0){
						out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
						out.println("<tr></tr>");
						out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='30%' >Requsition No</td>"); 
			out.println("<td width='40%' align=\"right\">Settle Amount</td>");
			out.println("<td width='30%' align=\"right\">Settle Date</td>");
			//out.println("<td width='*%'>&nbsp</td>"); 
			out.println("</tr >"); 

			}
			
			if(a>0 && a%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<tr >"); 
			out.println("<td width='30%' align=\"left\">"+rs1.getString(1)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_REQU1_NO_"+a+" maxlength='27' size='22' value=\""+rs1.getString(1)+"\"></td>"); 
			out.println("<td width='40%'  align=\"right\">"+rs1.getString(2)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_SET_"+a+" maxlength='27' size='22' value=\""+rs1.getString(2)+"\"></td>"); 
			out.println("<td width='30%'  align=\"right\">"+rs1.getString(3)+"<input class='txt_input' style=\"{text-align:right;}\" type='hidden' name=TXT_SET_DATE_"+a+" maxlength='27' size='22' value=\""+rs1.getString(3)+"\"></td>"); 
			//out.println("<td width='*%'>&nbsp</td>"); 	
			out.println("</tr>");
			a=a+1;

			}

			out.println("</table >"); 

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


				rs2= stmt2.executeQuery ("SELECT DISTINCT ISSUER_CODE,trim(TO_CHAR(AMOUNT,'999,999,999,999,999.99')),TO_CHAR(A.ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(START_DATE,'DD-MM-YYYY'), "+
				"TO_CHAR(END_DATE,'DD-MM-YYYY'),STATUS "+
				"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B "+	
				"WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
				"AND B.PURCHASE_ORDER_NO=('"+m_val+"') "+
				"AND STATUS='Y' ");


				boolean more2=rs2.next();	
				int j=0;

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
				
			while(more2){
				
			if(j==0){
			 out.println("<HR>");

			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Issuer Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
						
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Code</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Amount</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"20%\" >"+rs2.getString(1)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(2)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(3)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(4)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(5)+"</td></tr>");
			
			more2=rs2.next();
			j=j+1;
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
