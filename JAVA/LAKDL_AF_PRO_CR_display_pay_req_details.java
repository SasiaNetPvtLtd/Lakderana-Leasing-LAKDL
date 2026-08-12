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
 

public class LAKDL_AF_PRO_CR_display_pay_req_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt2,stmt1,stmt,stmt3,stmt4,stmt5,stmt6,stmt_ref_no;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs2,rs1,rs,rs3,rs4,rs5,rs6,rs_ref_no;
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
			stmt_ref_no= conn.createStatement();

			String m_application_no = req.getParameter("APP_NO");
			String m_status1 = req.getParameter("chksql1");
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			String m_client="";
			
			String m_curr_code = req.getParameter("CURR_CODE");
			
			String m_sus_ref = req.getParameter("SUS_REF_NO");
			String m_ref = req.getParameter("REF_NO"); 
			String m_screen_type = req.getParameter("screen_type");//###L
			

			String m_value_date = req.getParameter("VALUE_DATE");
			double m_balance = Double.parseDouble(m_sn_methods.met_unformat_number(req.getParameter("BAL")));
			double m_total_settle=Double.parseDouble(req.getParameter("TOT_SET")); 
			double m_initial_bal=Double.parseDouble(req.getParameter("INIT_BAL")); 
			
			rs= stmt.executeQuery(" SELECT APPLICATION_NO,CLIENT_CODE, "+
			" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) NAME,FINANCE_NO "+
			" FROM  "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
			boolean more=rs.next();
			String  _m_client      = rs.getString(2);
			String  _m_client_name = rs.getString(3);
			String  _m_finance_no  = rs.getString(4);

			
			/****************Added by Jithendra 03-02-2017****************/
			double auth_limit=0.00;
			rs= stmt.executeQuery("SELECT PAYMENT_LIMIT FROM  "+m_schema_name+".AF_CO_MAS_PAYMENT_AUTH_LIMIT ");
			
			if(rs.next()){
			auth_limit=rs.getDouble(1);
			}
            /***************end******************/
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Payment Requsistion</TITLE>"); 
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
			out.println("var array_document=new Array();"); //added by nuwan de silva on 09-11-07
			out.println("var array_stage=new Array();");
			out.println("var array_document_code=new Array();");

			
			//added by nuwan de silva on 03-10-07---------
			out.println("var lineno_payee=0;");
			out.println("var m_row_no=0;");
			out.println("var arr_size_payee=0;");
			out.println("var count_payee=1;");
			out.println("var array_payee_name=new Array();"); 
			out.println("var array_payee_amount=new Array();");
			
			
			
			
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
			out.println("var m_cond_status=0");
			//out.println("var arr_size_con=0;");

						
						
			out.println("function get_conditions(){ "); 
			out.println("assig('M_CON');");		
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_application_no+"&ac_status=COMPLETED\";");
		  out.println("load_interface(m_url,'XML');");
      out.println("}");
			
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&scr_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN&status=Y&Followu_no='+document.Form1.elements[m_fol_no].value;"); //modified by nuwan de silva on 17-10-07--------
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			
		 	/*out.println("function header_con(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
			*/
			
			out.println("function header_con(){");
			out.println("m_table.innerHTML=\"\" ");	
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR class=pdn_txtpos2>'+");
			out.println("'<TD WIDTH=\"12%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"7%\" align=\"left\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"left\">Type</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Document</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Stage Entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'</TR></table>';");
     	out.println("}");

			
			/*out.println("function display_data(data_vec){ "); 
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
			*/
			
				
			out.println("function display_data(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("header_con();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("      '<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno+')\" ><U>Follow up</U></TD>';");//&nbsp;&nbsp;Follow up
			out.println("if(data_vec[i+3]==\"-\"){");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >-</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" ) >'+data_vec[i+2].replace('$','&')+'</TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			out.println("else {");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >Document</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+data_vec[i+6]+'\") ><U>'+data_vec[i+2].replace('$','&')+'</U></TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\"   >'+data_vec[i+3]+'</TD>';");
			out.println("m_condition='<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>';");
			out.println("m_status='<TD WIDTH=\"10%\"  align=\"left\">'+data_vec[i+5]+'</TD>';");
			out.println("m_button='<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>';");
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+5]+'>'+");
		  out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	VALUE=\"'+data_vec[i+2].replace('$','&')+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	VALUE=\"'+data_vec[i+6]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	VALUE=\"'+data_vec[i+3]+'\" >';");
			out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("i=i+7;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0){");
			out.println("add_row()");
			out.println("}");
			out.println("}");
			
			
			//added by nuwan de silva on 03-10-07---------
			out.println("function check_number(obj,size,row){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("validate_amount(obj,row);"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			//added by nuwan de silva on 03-10-07------
			out.println("function validate_settlement_mode(obj,row) {"); 
			out.println("m_row_no=row;");
			out.println("var sum=0;");
			out.println("var m_settle_amount=0;");
			out.println("var m_amount=0;");
			out.println("for(var i=0;i<arr_size_payee;i++){"); //document.getElementById(\"hid_records_\"+row).value
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+row+i");
			out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_payee_amount].value));");
			out.println("}"); 
			out.println("}"); 
		
			
			//added by nuwan de silva on 03-10-07------
			out.println("function validate_amount(obj,row) {"); 
			out.println("m_row_no=row;");
			out.println("var sum=0;");
			out.println("var m_settle_amount=0;");
			out.println("var m_amount=0;");
			//out.println("alert('records'+document.getElementById(\"hid_records_\"+row).value);");
			out.println("for(var i=0;i<arr_size_payee;i++){"); //document.getElementById(\"hid_records_\"+row).value
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+row+i");
			out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_payee_amount].value));");
			out.println("}"); 
		//	out.println("alert('val'+sum);");
			
			//______________- comment by nuwan de silva on 25-01-2008 ____________________//
			/*out.println("assig('J_NEW')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_settle_new&data_val1=\"+sum+\"&data_val="+m_sus_ref+"&inv="+m_ref+"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			*/
			//______________end comment by nuwan de silva on 25-01-2008 ____________________//
			//out.println("m_settle_amount=parseFloat(unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value));");
			out.println("m_amount=\"TXT_AMOUNT\"+row;");
			out.println("m_settle_amount=parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
			
			out.println("if(sum > m_settle_amount ) {");
			out.println("alert('Entered amount is greater than settle amoutnt')");
			//out.println("document.Form1.elements[\"TXT_PAYEE_AMOUNT\"+m_row_no].value=\"\"");
			out.println("obj.value=\"\"");
			out.println("}");
		 
			out.println("}"); 
			
			
			out.println("function enable(row) {");
			out.println("lineno_payee=0; ");
			out.println("arr_size_payee=0; ");
			out.println("if(document.Form1.elements[\"TXT_PARTY\"+row].selectedIndex==[1]){"); 
			out.println("document.getElementById(\"m_table_payee_add_button\"+row).innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td width=\"*%\" ><input class=but_input type=button name=ADD_PAYEE'+row+' value=\"Add\" onClick=\"add_payee('+row+')\" ></td>';");
			out.println("'</tr>'+");
			//out.println("'<input type=\"hidden\" name=hid_records_'+row+'	value=\"0\">'+");
			out.println("'</table>';");		
			//out.println("document.Form1.elements['hid_records_'+row].value");
			
			out.println("add_payee(row)"); //added by nuwan de silva on 03-10-07
			
			out.println("}"); 
			out.println("if(document.Form1.elements[\"TXT_PARTY\"+row].selectedIndex==[0]){"); 
			out.println("document.getElementById(\"m_table_payee\"+row).innerHTML=\"\" ");
			out.println("document.getElementById(\"m_table_payee_add_button\"+row).innerHTML=\"\" ");
			//out.println("lineno_payee=0; ");
			//out.println("arr_size_payee=0; ");
			//out.println("count_payee=1; ");
		  out.println("}"); 
			out.println("}"); 
			
						
			out.println("function add_payee(row){"); 
			//out.println("alert('records'+row);");
			//out.println("alert('records'+document.Form1.elements(\"hid_records_\"+row).value);");
			//out.println("alert('records'+document.getElementById(\"hid_records_\"+row));");
			out.println("var b_flag_con=0;");
			out.println("if(lineno_payee!=0){");
			out.println("count=lineno_payee-1;");
			out.println("m_payee_name=\"TXT_PAYEE_NAME\"+row+count");
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+row+count");
			out.println("if(document.Form1.elements[m_payee_name].value==\"\") {");
			out.println("alert('Payee name can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_payee_amount].value==\"\") {");
			out.println("alert('Payee amount can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("}");

			out.println("if(b_flag_con==0){");
					
			out.println("document.getElementById(\"m_table_payee\"+row).innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr >'+");									
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><B>'+count_payee+'.</B>Payee Name</TD>'+");// '+row+lineno_payee+'
			//out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NAME'+row+lineno_payee+' style=\"{width:250px;}\" maxlength=\"200\" size=\"10\" onblur=\"\" ></TD>'+"); // commented by udara 06-05-2015
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NAME'+row+lineno_payee+' style=\"{width:250px;}\" maxlength=\"200\" size=\"10\" onblur=\"\" value=\"LAKDERANA INVESTMENTS LTD\" ></TD>'+"); // added by udara 06-05-2015
			//out.println("'<input class=but_input type=button name=DEL_PAYEE value=\"Delete\" onClick=del_payee_data(\"'+row+'\",\"'+lineno_payee+'\") ></TD>'+ ");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'<TR>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\">Amount</TD>'+"); //'+row+lineno_payee+'
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_AMOUNT'+row+lineno_payee+'  style=\"{text-align:right;}\" maxlength=\"20\" size=\"20\" onblur=check_number(this,20,'+row+') ></TD>'+"); //onblur=check_number(this,20,'+lineno_payee+')
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			
			out.println("'</TABLE>';");
			
			out.println("lineno_payee=lineno_payee+1; ");
			out.println("arr_size_payee=arr_size_payee+1; ");
			out.println("count_payee=count_payee+1; ");
			out.println("document.getElementById(\"HID_TXT_SUS_REF_NO_COUNT\"+row).value=arr_size_payee;");
			//out.println("document.getElementById(\"hid_records_\"+row).value=arr_size_payee;");
			//out.println("'<input type=\"hidden\" id=hid_records_'+row+'	value='+arr_size_payee+'>'+");
			out.println("}");
			out.println("}");
			
			
			//added by nuwan de silva on 03-10-07---------
			out.println("function del_payee_data(div_row,row_no){"); 
			out.println("if(lineno_payee!=1){");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_payee;i++){");
			out.println("m_payee_name=\"TXT_PAYEE_NAME\"+i");
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+i");
			
			out.println("if(i==row_no)");
			out.println("continue;");
			out.println("array_payee_name[j]=document.Form1.elements[m_payee_name].value;");
			out.println("array_payee_amount[j]=document.Form1.elements[m_payee_amount].value;");
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno_payee=lineno_payee-1;");
			out.println("arr_size_payee=arr_size_payee-1;");
		  out.println("write_data_payee(div_row,arr_size_payee);");
			out.println("}");
			out.println("}");
			
			 //added by nuwan de silva on 03-10-07---------
			out.println("function write_data_payee(div_row,size){");
			out.println("count_payee=1;");
						
			out.println("document.getElementById(\"m_table_payee\"+div_row).innerHTML=\"\";");
			out.println(" for(var j=0;j<size;j++){");
			
			out.println("document.getElementById(\"m_table_payee\"+div_row).innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><B></B>Payee Name</TD>'+");//'+count_payee+'.
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NAME'+j+' style=\"{width:250px;}\" value=\"'+array_payee_name[j]+'\" maxlength=\"200\" size=\"10\" onblur=\"\" >'+");
			out.println("'<input class=but_input type=button name=DEL_PAYEE value=\"Delete\" onClick=del_payee_data(\"'+div_row+'\",\"'+j+'\") ></TD>'+ ");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'<TR>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\">Amount</TD>'+");
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_AMOUNT'+j+'  style=\"{text-align:right;}\" value=\"'+array_payee_amount[j]+'\" maxlength=\"20\" size=\"20\" onblur=\"check_number(this,20,'+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'</TABLE>';");
			
			//out.println("count_payee=count_payee+1;");
		  out.println("}");		
		  out.println("}");		

						
			// comment by nuwan de silva
			/*
		 //added by nuwan de silva on 03-10-07--
			out.println("function add_payee(){"); 
			
			out.println("var b_flag_con=0;");
			out.println("if(lineno_payee!=0){");
			out.println("count=lineno_payee-1;");
			out.println("m_payee_name=\"TXT_PAYEE_NAME\"+count");
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+count");
			out.println("if(document.Form1.elements[m_payee_name].value==\"\") {");
			out.println("alert('Payee name can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_payee_amount].value==\"\") {");
			out.println("alert('Payee amount can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			
			out.println("}");
			out.println("if(b_flag_con==0){");
			out.println("m_table_payee.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno_payee+'>'+");									
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><B>'+count_payee+'.</B>Payee Name</TD>'+");
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NAME'+lineno_payee+' style=\"{width:250px;}\" maxlength=\"200\" size=\"10\" onblur=\"\" >'+");
			out.println("'<input class=but_input type=button name=DEL_PAYEE value=\"Delete\" onClick=\"del_payee_data('+lineno_payee+')\" ></TD>'+ ");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'<TR>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\">Amount</TD>'+");
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_AMOUNT'+lineno_payee+'  style=\"{text-align:right;}\" maxlength=\"20\" size=\"20\" onblur=check_number(this,20,'+lineno_payee+') ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'</TABLE>';");
			
			out.println("lineno_payee=lineno_payee+1; ");
			out.println("arr_size_payee=arr_size_payee+1; ");
			out.println("count_payee=count_payee+1; ");
			out.println("}");
			out.println("}");
			
			//added by nuwan de silva on 03-10-07---------
			out.println("function del_payee_data(rowNo){"); 
			
			out.println("if(lineno_payee!=1){");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_payee;i++){");
			out.println("m_payee_name=\"TXT_PAYEE_NAME\"+i");
			out.println("m_payee_amount=\"TXT_PAYEE_AMOUNT\"+i");
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_payee_name[j]=document.Form1.elements[m_payee_name].value;");
			out.println("array_payee_amount[j]=document.Form1.elements[m_payee_amount].value;");
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno_payee=lineno_payee-1;");
			out.println("arr_size_payee=arr_size_payee-1;");
		  out.println("write_data_payee(arr_size_payee);");
			out.println("}");
			out.println("}");
			
		  //added by nuwan de silva on 03-10-07---------
			out.println("function write_data_payee(size){");
			out.println("count_payee=1;");
						
			out.println("m_table_payee.innerHTML=\"\";");
			out.println(" for(var j=0;j<size;j++){");
			
			out.println("m_table_payee.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><B>'+count_payee+'.</B>Payee Name</TD>'+");
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NAME'+j+' style=\"{width:250px;}\" value=\"'+array_payee_name[j]+'\" maxlength=\"200\" size=\"10\" onblur=\"\" >'+");
			out.println("'<input class=but_input type=button name=DEL_PAYEE value=\"Delete\" onClick=\"del_payee_data('+j+')\" ></TD>'+ ");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'<TR>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\">Amount</TD>'+");
			out.println("'<TD WIDTH=\"40%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_AMOUNT'+j+'  style=\"{text-align:right;}\" value=\"'+array_payee_amount[j]+'\" maxlength=\"20\" size=\"20\" onblur=\"check_number(this,20,'+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>'+"); 
			out.println("'</TR>'+");
			out.println("'</TABLE>';");
			
			out.println("count_payee=count_payee+1;");
     
		  out.println("}");		
			
		  out.println("}");		

      */
			
			
									
			/*----------------------------------------------------------------
			Purpose  : Add Conditions
				
			----------------------------------------------------------------*/			

			/*out.println("function add_row(){"); 
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
			*/
			
			
			 out.println("function add_row(){"); 
			 out.println("b_flag=0;");
			 out.println("if(lineno!=0){");
			 out.println("count=lineno-1;");
			 out.println("m_condition=\"TXT_CONDITION\"+count");
			 out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			 out.println("alert('Condition can not be null.');");
			 out.println("b_flag=1;");
			 out.println("}");
			 out.println("}");
			 out.println("if(b_flag==0){");
			 out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			 out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
		   out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	        VALUE=\"-\">'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	            VALUE=\"-\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	    VALUE=\"-\" >'+");
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			 out.println("'</tr></table>';");
			 out.println("lineno=lineno+1;");
			 out.println("arr_size=arr_size+1;");
       out.println("}");
		   out.println("}");


			/*out.println("function del_row(rowNo){"); 
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
		  */
			
			 out.println("function del_row(rowNo){"); 
			 out.println("if(arr_size!=1){");
			 out.println("var j=0;");
			 out.println("for(var i=0;i<arr_size;i++){");
			 out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			 out.println("m_condition=\"TXT_CONDITION\"+i");
			 out.println("m_status=\"hid_TXT_STATUS\"+i");					
			 out.println("m_document=\"hid_DOCUMENT\"+i");					
			 out.println("m_document_code=\"hid_DOCUMENT_CODE\"+i");					
			 out.println("m_stage=\"hid_STAGE\"+i");			
			 out.println("if(i==rowNo)");
			 out.println("continue;");
			 out.println("array_follow_up_no[j]       = document.Form1.elements[m_follow_up].value;");
			 out.println("array_condition[j]          = document.Form1.elements[m_condition].value;");
		   out.println("array_status[j]             = document.Form1.elements[m_status].value;");    
			 out.println("array_document[j]           = document.Form1.elements[m_document].value;"); //added by nwuan de silva on 09-11-07
			 out.println("array_stage[j]              = document.Form1.elements[m_stage].value;");
			 out.println("array_document_code[j]     = document.Form1.elements[m_document_code].value;"); //added by nwuan de silva on 09-11-07
			 out.println("j=j+1;");
			 out.println("}");
			 out.println("lineno=lineno-1;");
			 out.println("arr_size=arr_size-1;");
		   out.println("write_data(arr_size);");
			 out.println("}");


			/*out.println("function write_data(size){");
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
      */
			
			
			
			 out.println("function write_data(size){");
			 out.println("sum=0;");
			 out.println("m_table.innerHTML=\"\";");
			 out.println("header_con();");
       out.println(" for(var j=0;j<size;j++){");
		   out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
		   out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\"  style=\"{width:110px;}\"  disabled></TD>'+");
			 out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			 out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	      VALUE=\"-\">'+");
		   out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	        VALUE=\"-\">'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	            VALUE=\"-\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	    VALUE=\"-\" >'+");
			 out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
			 out.println("'</tr></table>';");
       out.println("continue;");
			 out.println("}");
			 out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
       out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
 			 out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			 out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	     VALUE=\"-\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	       VALUE=\"-\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	           VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	 VALUE=\"-\" >'+");
			 out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
			 out.println("'</tr></table>';");
       out.println("continue;");
			 out.println("}");
			 
				out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
			out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</TD>';"); //Follow up
							
			out.println("if(array_stage[j]==\"-\"){");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">-</TD>';");
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" >'+array_document[j]+'</TD>';");
			out.println("}");
			out.println("else {");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">Document</TD>';");
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+array_document_code[j]+'\") ><u>'+array_document[j]+'</u></TD>';");
			out.println("}");
			 
			 out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\">'+array_stage[j]+'</TD>';");
			 out.println("m_condition='<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>';");
			 out.println("m_status='<TD WIDTH=\"10%\" align=\"left\">'+array_status[j]+'</TD>';");
			 out.println("m_button='<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>';");
			 out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	        VALUE='+array_status[j]+'>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	          VALUE=\"'+array_document[j]+'\">'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	              VALUE=\"'+array_stage[j]+'\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	      VALUE=\"'+array_document_code[j]+'\" >';");
			
			 out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
			 out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	 out.println("m_writedata+'</table>';");
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
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"J1\" && document.Form1.TXT_SETTLE_AMOUNT.value!=''){");
			out.println("alert('Entered amount is greater than balance needed to be paid')");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.value=\"\"");
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"J_NEW\" ){");
			out.println("alert('Entered amount is greater than balance needed to be paid')");
			out.println("document.Form1.elements[\"TXT_PAYEE_AMOUNT\"+m_row_no].value=\"\"");
			out.println("			}");
			
			out.println("	 if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"A4\" && document.Form1.elements[\"TXT_DISTRICT_CODE\"+document.Form1.hid_val_1.value]!=\"\"){");
			//out.println("if(dist!=1){");
			out.println("help_button_1(document.Form1.hid_val_1.value)");
			//out.println("			}");
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
			out.println("if(document.Form1.TXT_SETTLE_AMOUNT.value==\"\"){");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.value=0");
			out.println("}");
			out.println("assig('J1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_settle_new&data_val1=\"+unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value)+\"&data_val="+m_sus_ref+"&inv="+m_ref+"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function assig(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println("}");

			out.println("function check_district(row) {");//To check district.
			out.println("assig('A4')");
			out.println("document.Form1.hid_val_1.value=row");
		
			out.println("if(document.Form1.elements[\"TXT_DISTRICT_CODE_\"+row].value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_district&data_val=\"+document.Form1.elements[\"TXT_DISTRICT_CODE_\"+row].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			out.println("function check_foll_rem(){");
			out.println("b_count=0");
			//out.println("alert('document.Form1.hid_asset_doc_no.value' +document.Form1.hid_asset_doc_no.value);");
			out.println("for(var x=0;x<document.Form1.hid_asset_doc_no.value;x++){");
			out.println("if(document.Form1.hid_asset_doc_no.value !=\"0\"){");
			out.println("  chk_status=\"chk_status_\"+x");			
			//out.println("alert('document.Form1.elements[chk_status]' +document.Form1.elements[chk_status].value);");
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
			
			/*out.println("function before_submit(){ ");
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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Save_Payment_Req_Account_Selection?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_REQ_MAIN&status=RE_A_2';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			*/


			out.println("function before_submit(){ ");
			//out.println("alert('count'+document.getElementById(\"HID_TXT_SUS_REF_NO_COUNT\"+0).value);");
			
			
			out.println("var flag_st=0;");
			out.println("check_foll_rem()");
			out.println("if(document.Form1.hid_asset_doc_no.value==0 && document.Form1.hid_client_doc_no.value==0){");
			out.println("b_count_new=1");
			out.println("}");
			
			out.println("if(b_count_new==1){");
			
			/*out.println("check_dates()");
			
			out.println("if(b_dates0==0){");
			out.println("alert('Please enter CR Book Date')");
			out.println("}");
			
			out.println("else{");	
			out.println("if(b_dates1==0){");
			out.println("alert('Please enter Insurance Date')");
			out.println("}");
			
			out.println("else{");
			out.println("if(b_dates2==0){");
			out.println("alert('Please enter Revenue License Date')");
			out.println("}");
			
			out.println("else{");
			out.println("if(b_dates3==0){");
			out.println("alert('Please enter Luxury Tax Date')");
			out.println("}");
			*/
			
			//out.println("else{");
			
			//out.println("if(b_count_new==1 && b_dates0==1 &&  b_dates1==1 &&  b_dates2==1 && b_dates3==1){");//
			
			out.println("if(document.Form1.TXT_SETTLE_AMOUNT.value==\"\" || unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value)==\"0.00\"){");
			out.println("alert('Please enter Settle amount')");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.focus()");
			out.println("flag_st=1;");
			out.println("}");
			
		 //out.println("else{");
			
			/*out.println("for(var i=0;i<document.Form1.hid_invoice_no.value;i++){");
			out.println("vh='TXT_VEHICLE_NO_'+i;");
			out.println("en='TXT_ENGIN_NO_'+i;");
			out.println("ch='TXT_CHASSIS_'+i;");
   		out.println(" if (document.Form1.elements[en].value==''){");
			out.println("alert('Please enter Engine No');");
			out.println("document.Form1.elements[en].focus();");
			out.println("}");
   		out.println("else if (document.Form1.elements[ch].value==''){");
			out.println("alert('Please enter Chassis No');");
			out.println("document.Form1.elements[ch].focus();");
			out.println("}");
			out.println("else if (document.Form1.elements[vh].value==''){");
			out.println("alert('Please enter Vehicle No');");
			out.println("document.Form1.elements[vh].focus();");
			out.println("}");
			
			out.println("else if (document.Form1.elements[\"TXT_CR_BOOK_NO_\"+i].value==''){");
			out.println("alert('Please enter CR Book No');");
			out.println("document.Form1.elements[\"TXT_CR_BOOK_NO_\"+i].focus();");
			out.println("}");		
			
			out.println("}");
			*/
			
			//out.println("if(document.Form1.elements[vh].value!=\"\" && document.Form1.elements[en].value!='' && document.Form1.elements[ch].value!=''){");
			out.println("		if(flag_st==0) {");
			out.println("		check_foll_con();");
		    out.println("	if(m_cond_status==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			//out.println("alert(parseFloat(unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value)))");
			out.println("if(parseFloat(unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value))>"+auth_limit+"){");//Added by Jithendra 03-02-2017 for SR#22886
			out.println("alert('You are about to generate a payment beyond the authorized amount')}");//Added by Jithendra 03-02-2017 for SR#22886
			out.println("		if(confirm(\"documents are pending, do you want to go to the higher approval leval?\")) {"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("   document.Form1.hid_no_of_payees.value=arr_size_payee;"); //added by nuwan de silva on 03-10-07
			//out.println("alert('arr_size_payee'+arr_size_payee);");
			//out.println("alert('arr_size'+arr_size);");

			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details1?curr_code="+m_curr_code+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN1&status=TM_APP';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); //
			out.println("		}"); //
			
			out.println("	else	{"); //
			out.println("if(parseFloat(unformat_noobject(document.Form1.TXT_SETTLE_AMOUNT.value))>"+auth_limit+"){");//Added by Jithendra 03-02-2017 for SR#22886
			out.println("alert('You are about to generate a payment beyond the authorized amount')}");//Added by Jithendra 03-02-2017 for SR#22886
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("   document.Form1.hid_no_of_payees.value=arr_size_payee;"); //added by nuwan de silva on 03-10-07
			//out.println("alert('arr_size_payee'+arr_size_payee);");
			//out.println("alert('arr_size'+arr_size);");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details1?curr_code="+m_curr_code+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN1&status=RE-APP';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); //
			out.println("		}"); //
			
			out.println("} "); 
			
			out.println("} "); 
			out.println("}"); 
			/*out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("} ");*/
			
			

			out.println("function load_lock(){	"); 
			out.println("get_conditions();");//Added By Nuwan De Silva //07/02/2007.Conditions
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?APP_NO="+m_application_no+"&CURR_CODE="+m_curr_code+"&TOT_SET="+m_total_settle+"&INIT_BAL="+m_initial_bal+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';");   //modified by nwuan de silva on 08-10-07
			out.println("		}"); 																										
			out.println("}"); 

			out.println("function new_window(){	"); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?APP_NO="+m_application_no+"&CURR_CODE="+m_curr_code+"&TOT_SET="+m_total_settle+"&INIT_BAL="+m_initial_bal+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';");  //modified by nwuan de silva on 08-10-07
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_req\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Payment Requisition  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Payment Requisition  - \"+document.Form1.hid_status.value;"); 
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
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
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
			out.println("clear_data()");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear_data()");
			out.println("	}	");
			out.println("}"); 
			
			
	 		out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_button_1(row) {"); 
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_DISTRICT_CODE_sql1\";"); 
			out.println(" district='TXT_DISTRICT_CODE_'+row");
			out.println("    m_criteria = document.Form1.elements[district].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value=oBj.valout[2];"); 
			out.println("dist=\"0\"");
			out.println("}"); 


			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_ACCOUNT_2_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_value_assign_5(oBj) {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];");  
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];");
			out.println("}"); 

			out.println(" function Close(){");
			out.println("clear_data()	");
			out.println("dist=\"0\"");
      out.println(" }");
			
			out.println("function clear_data(){");
			out.println("if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value='';"); 
			out.println(" document.Form1.elements[district].focus();"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println(" document.Form1.TXT_ACC_NO.value='';"); 
			out.println(" document.Form1.TXT_ACC_NO.focus();"); 
			out.println(" document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println(" document.Form1.TXT_BRANCH_NAME.value='';");
			out.println("}");
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
			
			out.println("function check_dates(){");
			out.println("for(var f=0;f<document.Form1.hid_invoice_no.value;f++){");
			out.println("print_dd='TXT_PRINT_DATE_DD'+f;");
			out.println("print_mm='TXT_PRINT_DATE_MM'+f;");
			out.println("print_yy='TXT_PRINT_DATE_YY'+f;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+f;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+f;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+f;");
			out.println("rev_dd='TXT_REVENUE_LICENSE_DATE_DD'+f;");
			out.println("rev_mm='TXT_REVENUE_LICENSE_DATE_MM'+f;");
			out.println("rev_yy='TXT_REVENUE_LICENSE_DATE_YY'+f;");
			out.println("lu_dd='TXT_LUXURY_TAX_DATE_DD'+f;");
			out.println("lu_mm='TXT_LUXURY_TAX_DATE_MM'+f;");
			out.println("lu_yy='TXT_LUXURY_TAX_DATE_YY'+f;");
			out.println(" if(document.Form1.elements[print_dd].value==\"\" || document.Form1.elements[print_mm].value==\"\" || document.Form1.elements[print_yy].value==\"\"){");
			out.println("b_dates0=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates0=1");
			out.println("}");
			out.println(" if(document.Form1.elements[ind_dd].value==\"\" || document.Form1.elements[ind_mm].value==\"\" || document.Form1.elements[ind_yy].value==\"\"){");
			out.println("b_dates1=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates1=1");
			out.println("}");
			out.println(" if(document.Form1.elements[rev_dd].value==\"\" || document.Form1.elements[rev_mm].value==\"\" || document.Form1.elements[rev_yy].value==\"\"){");
			out.println("b_dates2=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates2=1");
			out.println("}");
			out.println(" if(document.Form1.elements[lu_dd].value==\"\" || document.Form1.elements[lu_mm].value==\"\" || document.Form1.elements[lu_yy].value==\"\"){");
			out.println("b_dates3=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates3=1");
			out.println("}");
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
			
			out.println("else if(document.Form1.hid_cal_date.value=='7'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_YY\"+m_row].value=v_yy;");
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
			
			out.println("  else if(document.Form1.hid_cal_date.value=='8'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			//out.println("     document.Form1.TXT_TAX_DATE_DD.value=v_dd;");
			//out.println("     document.Form1.TXT_TAX_DATE_MM.value=v_mm;");
			//out.println("     document.Form1.TXT_TAX_DATE_YY.value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_YY\"+m_row].value=v_yy;");
			
			out.println("  }");				
			
			out.println("}");		


			out.println("function close_screen(){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?screen_type=NEW&sql=main_page&status_new=NEW&chksql=VERIFY&chksql2=R&st_c=APPLICATION_NO&oby=ASC';");
			out.println("}");
			out.println("}");
			
			out.println("function app_screen(){"); 
			out.println("		if(confirm(\"Are you sure you want to view Main Approval Screen?\")){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?ac_status=A&CLSTATUS=A&screen=APP1';"); 
	    out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			out.println("}");

			/*out.println("function enable() {");
			out.println("if(document.Form1.TXT_PARTY.selectedIndex==[1]){"); 
			
			out.println("DIV_TXT_PAYER_NAME1.innerHTML='<tr >'+"); 
			out.println("'<td width=\"30%\" ><input class=but_input type=button name=ADD_PAYEE value=\"Add\" onClick=\"add_payee()\" >';");
			out.println("									'</tr>';"); 
			out.println("DIV_TXT_PARTY_LETTER.innerHTML='<tr >'+"); 
			out.println("																'<td width=\"30%\" align=\"left\" >3rd Party Letter Date</td>';"); 
			
			out.println("letter.innerHTML='<TD WIDTH=\"30%\">'+"); 
			out.println("									'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">'+");
			out.println("									'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">'+");
			out.println("									'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from()\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(\"6\",1)>   Calendar</a></td>'+ ");	
			out.println("									'<td width=\"*%\"></td>'+"); 
			out.println("									'</tr>';"); 
			out.println("add_payee()"); //added by nuwan de silva on 03-10-07
			out.println("document.Form1.TXT_SETTLE_AMOUNT.disabled=true;"); 
			
			out.println("}"); 
			
			out.println("if(document.Form1.TXT_PARTY.selectedIndex==[0]){"); 
			out.println("DIV_TXT_PAYER_NAME1.innerHTML=\"\""); 
			out.println("payer.innerHTML=\"\""); 
			out.println("DIV_TXT_PARTY_LETTER.innerHTML=\"\""); 
			out.println("letter.innerHTML=\"\""); 
			
			out.println("m_table_payee.innerHTML=\"\"");  //added by nuwan de silva on 03-10-07
			out.println("lineno_payee=0; ");
			out.println("arr_size_payee=0; ");
			out.println("count_payee=1; ");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.disabled=false;"); 
			out.println("}"); 
		  out.println("}"); 
			
			*/
			
			

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
			
			/*out.println("function check_foll_con(){");
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
			*/
			
			
			out.println("function change_val_req(row_no){")	;
			out.println("var m_amount=0;");
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			out.println("m_txt_amount=\"TXT_AMOUNT\"+row_no;");
			out.println("m_txt_balance_amount=\"TXT_BALANCE_AMOUNT\"+row_no;");
			
			out.println("if(document.Form1.elements[m_chk_required].checked==true){");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("m_amount=parseFloat(unformat_noobject(document.Form1.elements[m_txt_amount].value));");
			out.println("if(m_amount==0) {");
			out.println("document.Form1.elements[m_txt_amount].value=document.Form1.elements[m_txt_balance_amount].value;");
			out.println("}");	
			
			out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
			out.println("document.Form1.elements[m_chk_required].value='off'");
			out.println("document.Form1.elements[m_txt_amount].value=0;");
			out.println("}");	
			
			out.println("calculate_amount();");
			
			out.println("}");	
			
			
			out.println("function check_amount(obj,size,row){")	;
			out.println("if(obj.value!='')"); 
			out.println("amount=\"TXT_AMOUNT\"+row;");
			out.println("balance=\"TXT_BALANCE_AMOUNT\"+row;");
			out.println("if(isnumberok(obj,size)){"); 
			out.println("_m_amount=parseFloat(unformat_noobject(document.Form1.elements[amount].value));");
			out.println("_m_balance=parseFloat(unformat_noobject(document.Form1.elements[balance].value));");
			out.println("if(_m_amount >_m_balance) {");
			out.println("alert('Amount can not be greater than balance amount');");
			out.println("obj.value=0;");
			out.println("}");
			out.println("else {");
			out.println("format_number(obj,size)"); 
			out.println("}");
			
			out.println("calculate_amount();");
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}	"); 

			
			
			out.println("function calculate_amount(){")	;
			out.println(" var sum=0; ");
			out.println("for(i=0;i<parseInt(document.Form1.sus_no_records.value);i++){");
			//out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+i;");
			//out.println("adjust_type=\"TXT_ADJUST_MODE\"+i;");
			out.println("m_amount=\"TXT_AMOUNT\"+i;");
			out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
			
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
			out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
			//out.println("m_adjust_amount=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
			//out.println("m_adjust_type=document.Form1.elements[adjust_type].value;");
			
			/*out.println("if(m_adjust_amount >0){");
			out.println("if(m_adjust_type ==\"ADD\"){");
			out.println("sum=sum+m_adjust_amount;");
			out.println("}");			
			out.println("else if(m_adjust_type ==\"MIN\"){");
			out.println("sum=sum-m_adjust_amount");
			out.println("}");			
			out.println("}");			
			*/
			
			out.println("}");			
			out.println("document.Form1.TXT_SETTLE_AMOUNT.value=format_noobject(sum)");
			out.println("}");			
			out.println("}");			
			
			out.println("function show_document_follow_up(m_type){");		
			//out.println("alert('"+m_client+"');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql=main_page&screen_name=payment_generation&payment_no="+m_client+"&client_code="+_m_client+"&value_date="+m_value_date+"&application_no="+m_application_no+"&type=\"+m_type+\"\";");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql=main_page&screen_name=payment_generation&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&value_date="+m_value_date+"&application_no="+m_application_no+"&type=\"+m_type+\"\";");
			//out.println("window.open(m_url)");
			out.println("window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
			out.println("");
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_screen_type' VALUE=\""+m_screen_type+"\">"); 
			
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
 			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_con' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_of_payees' VALUE=\"0\">");
			
				
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Payment Requisition </td>"); 
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
			
		
		/*rs= stmt.executeQuery(" SELECT APPLICATION_NO,CLIENT_CODE, "+
													" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) NAME,FINANCE_NO "+
													" FROM  "+
													" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
													" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
		 more=rs.next();
		*/
		
			out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");

			//if(more){
		//	m_client=rs.getString(2);
		//	out.println(m_client);
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No</DIV></td>"); 
			out.println("<td width='30%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_application_no+"')\"><U>"+m_application_no+"<input class='txt_input' type='hidden' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<input type=hidden name=TXT_FINANCE_NO value="+_m_finance_no+" >");
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_APP_NAME'  class=div_input>Name of the Applicant</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+_m_client+"')\"><U>"+_m_client_name+"<input class='txt_input' type='hidden' value="+_m_client+"  name='TXT_CLIENT_CODE'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			//}
			out.println("</table>"); 
			//out.println("<HR>"); 
			
			out.println("<table align='center' width='100%' border=0 class='table'>"); 

			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUS_REF_NO'  class=div_input>Sus Ref No</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+m_sus_ref+"')\"><U>"+m_sus_ref+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 
		  */
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE_DATE'  class=div_input>Value Date</DIV></td>"); 
			out.println("<td width='40%' >"+m_value_date+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_BAL'  class=div_input>Initial Amount</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_total_settle)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INIT_BAL'  class=div_input>Payments Already Processed</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_initial_bal)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>Balance To Be Paid</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_balance)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("</table>");
			
			
			out.println("<BR>");
			out.println("<HR>");
			
						//_____________ Purchase Order Details _________________________________________________________________//
			rs1=stmt1.executeQuery("SELECT "+
												     "DISTINCT A.PURCHASE_ORDER_NO, "+
												     "nvl(to_char(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'-') PURCHASE_ORDER_DATE , "+
												     "nvl(to_char(A.ISSUED_DATE,'dd-mm-yyyy'),'-') ISSUED_DATE, "+				
														 "A.VENDER_CODE, "+				
												     "A.TOTAL_NET, "+
												     "A.TOTAL_VAT, "+
														 "nvl((A.TOTAL_VAT+TOTAL_NET),0) TOTAL, "+
												     "NVL(A.EXCHANGE_RATE,0) EXCHANGE_RATE, "+				
												     "NVL(A.CURR_CODE,'-') CURR_CODE, "+
														 "NVL("+m_schema_name+".af_co_get_vendor_name(A.VENDER_CODE),'-') NAME "+
												 		 " ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE(A.APPLICATION_NO)),'-' )  "+//12
			                       "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
														 "WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
														 "AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO ");
														 //"AND UPPER(B.PRO_INVOICE_NO) =UPPER('"+m_ref+"') ");

		
			int q=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs1.next()){

			if(q==0){
			out.println("<tr><td><b><u>Purchase Order Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order No</td>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order Date</td>"); 
			out.println("<td width='10%' align=\"left\">Issued Date</td>"); 
			out.println("<td width='15%' align=\"left\">Vendor Code</td>"); 
			out.println("<td width='15%' align=\"right\">Total</td>"); 
			out.println("<td width='15%' align=\"left\">Exchange Rate</td>"); 
			out.println("<td width='15%' align=\"left\">Currency Code</td>"); 
			
			out.println("</tr >"); 

			}
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_PURCHASE_ORDER_NO value=\""+rs1.getString(1)+"\"></td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			if(rs1.getString(10).equals("-") || rs1.getString(10).equals("null")){
			out.println("<td align=\"left\">"+rs1.getString(11)+"</td>"); 
			}else{
			out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs1.getString(4)+"')\"><U>"+rs1.getString(10)+"</td>"); 
			}
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(7))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			
			out.println("</tr>");
			q=q+1;
      
			if(rs1.getString(10).equals("-") || rs1.getString(10).equals("null")){
			out.println("<input type=\"hidden\" name=TXT_PAYER value=\""+rs1.getString(11)+"\">"); // addded by nuwan de silva on 
			out.println("<input type=\"hidden\" name=TXT_PAYEE_NAME value=\""+rs1.getString(11)+"\">"); // addded by nuwan de silva on 
			}else{
			out.println("<input type=\"hidden\" name=TXT_PAYER value=\""+rs1.getString(10)+"\">"); // addded by nuwan de silva on 
			out.println("<input type=\"hidden\" name=TXT_PAYEE_NAME value=\""+rs1.getString(10)+"\">"); // addded by nuwan de silva on 
			
			}
			}

			out.println("</table >"); 
			out.println("<br>");
			
			
			rs = stmt.executeQuery (" SELECT  "+	
			" DISTINCT C.APPLICATION_NO APPLICATION_NO,  "+//1
			" nvl(C.FINANCE_NO,'-') FINANCE_NO,  "+ //2
			" SUS_REF_NO, "+ //3
			" REF_NO, "+ //4
			" (B.BAL_TO_BE_PAID) BAL_TO_BE_PAID,  "+ //5
			" (C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT ,  "+ //6
			" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,  "+ //7
			" NVL(B.RECEIVER,'-')  RECEIVER, 		 "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VENDOR_NAME,  "+ //9
			" (B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT,  "+ //10
			" (B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT  "+ //11
			" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-' )  "+//12
			" ,C.CLIENT_CODE "+ //13
			" FROM   "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,   "+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			" WHERE   "+
			" B.REF_NO=D.INVOICE_NO  "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO  "+
			" AND B.BAL_TO_BE_PAID >0   "+
			" AND D.ACTIVE_STATUS<>'C' "+
			" AND UPPER(C.APPLICATION_NO)='"+m_application_no+"'    "+
			" AND VALUE_DATE = TO_DATE('"+m_value_date+"','DD-MM-YYYY') ");
			more = rs.next();
			
			int a=0;
			double m_total=0;
			int a1=0;
			
		while(more){
		m_sus_ref=rs.getString(3);
		
		rs2=stmt2.executeQuery
		(" SELECT "+
		" DISTINCT A.PAYMENT_NO, "+
		" A.SUS_REF_NO, "+
		" SETTELED_AMOUNT, "+
		" DECODE(SETTLE_MODE,'CHQ','Cheque','Cash'), "+
		" TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'), "+
		" A.ENTRY_TYPE "+
		" FROM "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B "+
		" WHERE A.PAYMENT_NO=B.PAYMENT_NO "+
		" AND UPPER(A.SUS_REF_NO)=UPPER('"+m_sus_ref+"') ORDER BY A.PAYMENT_NO ");

		/*	rs2=stmt2.executeQuery("SELECT PAYMENT_NO,SUS_REF_NO,trim(PAY_AMOUNT),SETTLE_MODE, "+
		 "decode(ENTRY_TYPE,'V','Vendor','S','Supplier','E','Seizer'),to_char(EFF_VALDATE,'dd-mm-yyyy') "+
		 "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
		 "WHERE UPPER(SUS_REF_NO) =UPPER('"+m_sus_ref+"') ");
     */
			while(rs2.next()){
			
			if(a1==0){
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("</table >"); 
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			//out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' align=\"left\" >Payment No</td>"); 
			out.println("<td width='20%' align=\"left\" >Sus Payment No</td>"); 
			out.println("<td width='20%' align=\"left\" >Settle Mode</td>");
			//out.println("<td width='20%' align=\"left\" >Entry Type</td>");
			out.println("<td width='20%' align=\"left\" >Validate Date</td>");
			out.println("<td width='20%' align=\"right\">Settled Amount</td>");
			out.println("</tr >"); 
      out.println("</table >"); 
			}

		
			m_total=m_total+rs2.getDouble(3);
			/*if(a==0){
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='20%' align=\"left\">Payment No</td>"); 
			out.println("<td width='20%' align=\"right\">Settled Amount</td>");
			out.println("<td width='20%' align=\"left\">Settle Mode</td>");
			out.println("<td width='20%' align=\"left\">Entry Type</td>");
			out.println("<td width='20%' align=\"left\">Validate Date</td>");
			out.println("</tr >"); 

			}
			*/
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			if(a1>0 && a1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td  width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs2.getString(1)+"')\" ><U>"+rs2.getString(1)+"</td>"); 
			out.println("<td  width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+rs2.getString(2)+"')\" ><U>"+rs2.getString(2)+"</td>"); 
			out.println("<td  width='20%' align=\"left\">"+rs2.getString(4)+"</td>"); 
			//out.println("<td  width='20%' align=\"left\">"+rs2.getString(6)+"</td>"); 
			out.println("<td  width='20%' align=\"left\">"+rs2.getString(5)+"</td>"); 
			out.println("<td  width='20%' align=\"right\">"+nf.format(rs2.getDouble(3))+"</td>"); 
			out.println("</tr>");
			a1=a1+1;
			out.println("</table >"); 
			}
			more=rs.next();
			
			}
			
			
			out.println("<br>");
		 // out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr class=tr_input1>");
			out.println("<td width='80%'  align=right><b>Total Amount Previously Settled :</td>"); //modified by nuwan de silva 22-05-07
			out.println("<td width='20%' align=right><b>"+nf.format(m_total)+"</td>"); 
			//out.println("<td width='*%'>&nbsp</td>");
			out.println("</tr>"); 
			out.println("</table >"); 
			//out.println("<HR>");
			out.println("<br>");
			
			out.println("<HR>");	
			
			//________________________ End Purchase Order Details ______________________________________________________________//


			out.println("<table align='center' width='100%' border=0 class='table'>"); 

     //comment by nuwan de silva on 03-10-07----------------------------------
     /*
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ACC_NO'  class=div_input>Licencee Account No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='20' size='20' onblur=\"check_account(document.Form1.TXT_ACC_NO)\">"); 
		//	out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_NO' value=\"Help\" onClick=\"help_button_5('0','10','2','m_help_TXT_ACCOUNT_2_sql','5')\"></td>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_NO' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
		
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch Code </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'  onblur=\"check_branch(document.Form1.TXT_BRANCH_CODE)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			// -------- Added by Chandana on 22/05/2007 for Ref No.45 -------  //
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' style=\"width:200px;\" maxlength='100' size='100'  onblur=\"\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
      // ----------------- End Ref No.45 ---------------  //
     */
			
			//comment by nuwan de silva on 30-01-2008 by nuwan de silva
			/*out.println("<tr>"); 
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
			//end comment by nuwan de silva on 30-01-2008 by nuwan de silva
			*/
			
			
			out.println("</table>"); 
			
			/*out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='*%' ><DIV id='m_table_payee'  class=div_input></DIV></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
      */
			
			out.println("<table align='center' width='100%' border=0 class='table'>"); 
			
			// comment by nuwan de silva on 30-01-2008 
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PARTY_LETTER'  class=div_input></DIV></td>"); 
			out.println("<td width='30%' ><div id=letter></div>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_MODE'  class=div_input>Settlement Mode </DIV></td>"); 
			out.println("<td width='30%' ><select class=txt_input type=text name=TXT_SETTLE_MODE maxlength=1 size=1>");  
			out.println("<option value=\"CASH\" >Cash</option>");
			out.println("<option value=\"CHQ\" selected>Cheque</option>");
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
      */
			// end comment by nuwan de silva on 30-01-2008 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_AMOUNT'  class=div_input>Settle Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{text-align:right;}\" type='text' name='TXT_SETTLE_AMOUNT' disabled maxlength='22' size='22' onblur=\"check_amt()\"  value\"0.00\" ></td>");  //value=\""+nf.format(m_balance)+"\"
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("</table>");
			
			out.println("<br>");


			/*rs1=stmt1.executeQuery("SELECT "+
												     "A.PURCHASE_ORDER_NO, "+
												     "nvl(to_char(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'-') PURCHASE_ORDER_DATE , "+
												     "nvl(to_char(A.ISSUED_DATE,'dd-mm-yyyy'),'-') ISSUED_DATE, "+				
														 "A.VENDER_CODE, "+				
												     "A.TOTAL_NET, "+
												     "A.TOTAL_VAT, "+
														 "nvl((A.TOTAL_VAT+TOTAL_NET),0) TOTAL, "+
												     "NVL(A.EXCHANGE_RATE,0) EXCHANGE_RATE, "+				
												     "NVL(A.CURR_CODE,'-') CURR_CODE, "+
														 ""+m_schema_name+".af_co_get_vendor_name(A.VENDER_CODE) NAME "+
												 		 "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
														 "WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
														 "AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO ");
														 //"AND UPPER(B.PRO_INVOICE_NO) =UPPER('"+m_ref+"') ");

		
			int q=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs1.next()){

			if(q==0){
			out.println("<tr><td><b><u>Purchase Order Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order No</td>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order Date</td>"); 
			out.println("<td width='10%' align=\"left\">Issued Date</td>"); 
			out.println("<td width='15%' align=\"left\">Vendor Code</td>"); 
			out.println("<td width='15%' align=\"right\">Total</td>"); 
			out.println("<td width='15%' align=\"left\">Exchange Rate</td>"); 
			out.println("<td width='15%' align=\"left\">Currency Code</td>"); 
			
			out.println("</tr >"); 

			}
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_PURCHASE_ORDER_NO value=\""+rs1.getString(1)+"\"></td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs1.getString(4)+"')\"><U>"+rs1.getString(10)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(7))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			out.println("</tr>");
			q=q+1;
   
			out.println("<input type=\"hidden\" name=TXT_PAYER value=\""+rs1.getString(10)+"\">"); // addded by nuwan de silva on 
			}

			out.println("</table >"); 
			out.println("<br>");
			out.println("<HR>");	
		  */
			



		 /*rs2=stmt2.executeQuery("SELECT PAYMENT_NO,SUS_REF_NO,trim(PAY_AMOUNT),SETTLE_MODE, "+
		 "decode(ENTRY_TYPE,'V','Vendor','S','Supplier','E','Seizer'),to_char(EFF_VALDATE,'dd-mm-yyyy') "+
		 "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
		 "WHERE UPPER(SUS_REF_NO) =UPPER('"+m_sus_ref+"') ");


			int a=0;
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
	
			double m_total=0;

			while(rs2.next()){
			m_total=m_total+rs2.getDouble(3);
			if(a==0){
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='20%' align=\"left\">Payment No</td>"); 
			out.println("<td width='20%' align=\"right\">Settled Amount</td>");
			out.println("<td width='20%' align=\"left\">Settle Mode</td>");
			out.println("<td width='20%' align=\"left\">Entry Type</td>");
			out.println("<td width='20%' align=\"left\">Validate Date</td>");
			out.println("</tr >"); 

			}
			
			if(a>0 && a%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"</td>"); 
			out.println("<td  align=\"right\">"+nf.format(rs2.getDouble(3))+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(4)+"</td>"); 
			out.println("<td  align=\"left\">"+rs2.getString(5)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(6)+"</td>"); 
			out.println("</tr>");
			a=a+1;
	
			
			}
			out.println("</table >"); 
			*/
			
			
			//________________________________________________________________________________________________________________________
			rs = stmt.executeQuery (" SELECT  "+	
			" DISTINCT C.APPLICATION_NO APPLICATION_NO,  "+//1
			" nvl(C.FINANCE_NO,'-') FINANCE_NO,  "+ //2
			" SUS_REF_NO, "+ //3
			" REF_NO, "+ //4
			" (B.BAL_TO_BE_PAID) BAL_TO_BE_PAID,  "+ //5
			" (C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT ,  "+ //6
			" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,  "+ //7
			" NVL(B.RECEIVER,'-')  RECEIVER, 		 "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VENDOR_NAME,  "+ //9
			" (B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT,  "+ //10
			" (B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT  "+ //11
			" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-' )  "+//12
			" ,C.CLIENT_CODE "+ //13
			" FROM   "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,   "+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			" WHERE   "+
			" B.REF_NO=D.INVOICE_NO  "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO  "+
			" AND B.BAL_TO_BE_PAID >0   "+
			" AND D.ACTIVE_STATUS<>'C' "+
			" AND UPPER(C.APPLICATION_NO)='"+m_application_no+"'    "+
			" AND VALUE_DATE = TO_DATE('"+m_value_date+"','DD-MM-YYYY') ");

				more = rs.next();
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");

				  if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
			    out.println("<td width='12%' >Agreement No</td>"); 
					out.println("<td width='11%' >Client Name</td>"); 
					out.println("<td width='12%' >Sus Ref No</td>"); 
					//out.println("<td width='11%' >Ref No</td>"); 
					out.println("<td width='15%' align='right'>Balance Amount</td>"); 
					out.println("<td width='10%' align='left'>Payee Category</td>"); 
					out.println("<td width='10%' align='left'>Settlement Mode</td>"); 
					out.println("<td width='12%' align='right'>Amount</td>"); 
					out.println("<td width='4%' >&nbsp;</td>"); 
					out.println("</tr>"); 
          }
          int k = 0;      					
              while(more){
						
									if(k>0 && k%2==1){
									out.println("<tr class=tr_input1 >");
									}
									else{
									out.println("<tr class=tr_input >");
									}
									
									out.println("<td width='12%' >"+rs.getString(2)+"</td>"); 
									out.println("<td width='11%' >"+rs.getString(12)+"</td>"); 
									out.println("<td width='12%' >"+rs.getString(3)+"</td>"); 
									//out.println("<td width='11%' >"+rs.getString(4)+"</td>"); 
									out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+k+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"\"  >"); 
									
									out.println("<td width=\"10%\" align=\"left\" ><select name=TXT_PARTY"+k+" class=\"txt_input\" onChange=\"enable('"+k+"')\">");
									out.println("<OPTION value=\"2\" selected >Normal </option>");
									out.println("<OPTION value=\"1\">3rd Party</option>");
									out.println("</SELECT></td>");
									
									out.println("<td width=\"10%\" align=\"left\" ><select class=txt_input type=text name=TXT_SETTLE_MODE"+k+"  maxlength=1 size=1>");  
									out.println("<option value=\"CASH\" >Cash</option>");
									out.println("<option value=\"CHQ\" selected>Cheque</option>");
									out.println("</select></td>");
									
									out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+k+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+k+")\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); 
									out.println("<TD WIDTH=\"4%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+k+" VALUE=\"\" onclick=\"change_val_req("+k+")\"  ></td>");			
									out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+k+" value="+rs.getString(3)+" >");
									//out.println("<input type=hidden name=HID_TXT_REF_NO"+k+" value="+rs.getString(4)+" >");
									out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+k+" value="+rs.getString(2)+" >");
									out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+k+" value="+rs.getString(13)+" >");
									out.println("<input type=hidden name=HID_TXT_SUS_REF_NO_COUNT"+k+" value=0 >"); //added by nuwan de silva 28-09-2009
									out.println("</tr>"); 		
									
									out.println("<tr>");
									out.println("<td width='100%' colspan='8' ><DIV ID=m_table_payee_add_button"+k+"></DIV></td>"); 
									out.println("</tr>");
									out.println("<tr>");
									out.println("<td width='100%' colspan='8' ><DIV ID=m_table_payee"+k+"></DIV></td>"); 
									out.println("</tr>");
									more = rs.next();
									k=k+1;
	              }
					
			  //  out.println("</table>");
				         
          out.println("<input type=hidden name=sus_no_records value="+k+"></table>");
					
					//_________________________________________________________________________
					  out.println("<br><br>");
						out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
						out.println("<tr>");
						//out.println("<td  width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\"   onclick=\"show_document_follow_up('asset_details')\"  ><b><U>Asset Document Follow Up</u></td>"); 
						out.println("<td  width='20%' align=\"left\" ><input class='but_input' type='button' name='BUT_SHOW_DOC_ASSET' value=\"Asset Document Follow Up\" onclick=\"show_document_follow_up('asset_details')\" style=\"{width:150px;}\" ></td>"); 
						out.println("<td  width='60%' align=\"left\" ></td>");
						//out.println("<td  width='20%' align=\"right\" style= \"cursor:hand;cursor-color:blue\"  onclick=\"show_document_follow_up('client_details')\" ><b><U>Client Document Follow Up</u></td>"); 
						out.println("<td  width='20%' align=\"right\" ><input class='but_input' type='button' name='BUT_SHOW_DOC_CLIENT' value=\"Client Document Follow Up\" onclick=\"show_document_follow_up('client_details')\" style=\"{width:150px;}\" ></td>"); 
						out.println("</tr>");
						out.println("</table>");

					//_________________________________________________________________________

			//________________________________________________________________________________________________________________________
			
			
			// ________________________ comment by nuwan de silva on 25-01-2008 _____________________________________________________
			/*out.println("<br>");
		  out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr class=tr_input1>");
			out.println("<td width='20%'  align=right><b>Total Amount Previously Settled :</td>"); //modified by nuwan de silva 22-05-07
			out.println("<td width='20%' align=right><b>"+nf.format(m_total)+"</td>"); 
			out.println("<td width='*%'>&nbsp</td>");
			out.println("</tr>"); 
			out.println("</table >"); 
			out.println("<HR>");
			out.println("<br>");
			*/
			//_____________________________ end comment by nuwan de silva on 25-01-2008 _______________________________________________
			
			/*rs_ref_no = stmt_ref_no.executeQuery 
			(" SELECT  "+	
			" DISTINCT C.APPLICATION_NO APPLICATION_NO,  "+//1
			" nvl(C.FINANCE_NO,'-') FINANCE_NO,  "+ //2
			" SUS_REF_NO, "+ //3
			" REF_NO, "+ //4
			" (B.BAL_TO_BE_PAID) BAL_TO_BE_PAID,  "+ //5
			" (C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT ,  "+ //6
			" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,  "+ //7
			" NVL(B.RECEIVER,'-')  RECEIVER, 		 "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VENDOR_NAME,  "+ //9
			" (B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT,  "+ //10
			" (B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT  "+ //11
			" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-' )  "+//12
			" FROM   "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,   "+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			" WHERE   "+
			" B.REF_NO=D.INVOICE_NO  "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO  "+
			" AND B.BAL_TO_BE_PAID >0   "+
			" AND D.ACTIVE_STATUS<>'C' "+
			" AND UPPER(C.APPLICATION_NO)='"+m_application_no+"'    "+
			" AND VALUE_DATE = TO_DATE('"+m_value_date+"','DD-MM-YYYY') ");
			
			boolean more_ref_no=rs_ref_no.next();
			
			*/
			
			int d=0;
			int f=0;
			int j=0;
			/*
      while(more_ref_no) {
			m_ref=rs_ref_no.getString(4);
      m_sus_ref=rs_ref_no.getString(3);
			
		  rs3=stmt3.executeQuery
			("SELECT INVOICE_NO,APPLICATION_NO, "+
		 "nvl(ENGINE_NO,'-'),nvl(CHASSIS_NO,'-'),nvl(REG_NO,'-'),nvl(CR_BOOK_NO,'-'),nvl(DISTRICT_CODE,'-'), "+
		 "TO_CHAR(CR_PRINT_DATE,'DD-MM-YYYY'),TO_CHAR(INSURANCE_DATE,'DD-MM-YYYY'),TO_CHAR(REVENUE_LICENSE_DATE,'DD-MM-YYYY'), "+
		 "TO_CHAR(LUXURY_TAX_DATE,'DD-MM-YYYY'),TO_CHAR(DRIVING_LICENSE_DATE,'DD-MM-YYYY'),VEHICLE_NO "+   
		 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		 "WHERE UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
		 "AND UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");

			out.println("<hr>");
		  int f1=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<BR>");

      

			while(rs3.next()){

			if(f1==0){
			
			out.println("<tr><td><b><u>Tax Invoice Details</td></tr>"); //Added by Chandana on 04/12/2007
			out.println("<tr></tr>");
			out.println("<tr></tr>"); 
			
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Tax Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" ><input value=\"\" class='txt_input' maxlength='30'  type='text' name=TXT_TAX_INVOICE_NO_"+f+"></td>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Date:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('8',"+f+")  >   Calendar</a>");
			out.println("</td>");
			out.println("</tr>"); 
			
      out.println("<tr></tr>");
			out.println("<tr></tr>"); 
			
			
			out.println("<tr><td><b><u>Invoice Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs3.getString(1)+"')\"><U><b>:"+rs3.getString(1)+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='20%' align=\"left\">Engine No</td>");
			out.println("<td width='20%' align=\"left\">Chassis Mode</td>");
			out.println("<td width='20%' align=\"left\">Vehicle No</td>");
			out.println("<td width='20%' align=\"left\">CR Book No</td>");
			out.println("<td width='20%' align=\"left\">District Code</td>");
			out.println("</tr >"); 

			}
			
				
			if(f1>0 && f1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}



			if(rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(3)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+" disabled></td>"); 
			}		
			
			if(rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+"></td>"); 
			}	
			if(!rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(4)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+" disabled></td>"); 
			}	
			if(rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(5).trim()+"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+" disabled></td>"); 
			}
			
			if(rs3.getString(5).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" ></td>"); 
			}
			if(!rs3.getString(5).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(6)+"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" disabled></td>"); 
			}

			if(!rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(7)+"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"check_district("+f+")\">");		
			}
			if(rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"check_district("+f+")\">");		
			}
			out.println("<input class=but_input type=button name=BUT_TXT_DISTRICT_CODE_"+a+" value=\"Help\" onClick=help_button_1("+f+")></td>"); 
      out.println("<input type=hidden name=HID_TXT_REF_NO_"+f+" value="+rs3.getString(1)+" >");
			out.println("</tr>");
			
			if(f1==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' align=\"left\">CR Book Date</td>");
			out.println("<td width='20%' align=\"left\">Insurance Date</td>");
			out.println("<td width='20%' align=\"left\">Revenue License Date</td>");
			out.println("<td width='20%' align=\"left\">Luxury Tax Date</td>");
			out.println("<td width='20%' align=\"left\">Driving License Date</td>");
			out.println("</tr >"); 

			}
			
			
			
			if(f1>0 && f1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
		
			if(rs3.getString(8)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\""+rs3.getString(8).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\""+rs3.getString(8).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\""+rs3.getString(8).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\" >");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");   
			}
			if(rs3.getString(8)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			
			if(rs3.getString(9)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\""+rs3.getString(9).substring(0,2)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\""+rs3.getString(9).substring(3,5)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\""+rs3.getString(9).substring(6,10)+"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
		
			}
			if(rs3.getString(9)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
	
			}
		
			
			if(rs3.getString(10)!=null){

			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(10).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(10).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(10).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");
	
			}
			if(rs3.getString(10)==null){

			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
		
			if(rs3.getString(11)!=null){

			
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\""+rs3.getString(11).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\""+rs3.getString(11).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\""+rs3.getString(11).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");
		
			}
			if(rs3.getString(11)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
		
			
			if(rs3.getString(12)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(12).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(12).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(12).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
			if(rs3.getString(12)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
			
			out.println("</tr>");

			
			f=f+1;
      f1=f1+1;
			}
			//out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("</table >"); 
			out.println("<HR>");

			//-----asset details-------//
			rs4=stmt4.executeQuery
			 //out.println
			("SELECT DOCUMENT_TYPE,"+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),nvl(REMARK,'-') AS REMARK,nvl(decode(STATUS,'Y','Yes','N','No','A','Applicable'),'-') AS STATUS, "+
				"SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT, "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------
				
				
				"                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) SCREEN "+

				
				
				//--------------------------------------------------------------------------------------------------------------------------------------------
				
				
				
				
        "WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				
				"AND DOCUMENT_TYPE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT
				"AND STATUS!='Y' "+
        "AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+
				"AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+
        "AND DOCUMENT_TYPE IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
        "                          WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
        "                          AND CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) "+
        "                          ) "+
        " UNION "+
        "SELECT A.CODE CODE,"+m_schema_name+".AF_CO_GET_DOC_DESC(A.CODE),'-' AS REMARK,'-' AS STATUS,SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A , "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------

        "                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				//"																	AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) SCREEN "+


				//--------------------------------------------------------------------------------------------------------------------------------------------
				
        "                           WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
				"														AND A.CODE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT

        "                           AND A.CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
			//	"																												AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) "+
				
				"														AND A.CODE NOT IN (SELECT DOCUMENT_TYPE "+
				"																						 FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
        "																						 WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				"																						 AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+

        "																						 AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+				
        "                                  ) ORDER BY STATUS ASC ");
				
				
			int j1=0;
			out.println("<br>");
			//out.println("<HR>");
			//out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			boolean more4=rs4.next();
			if(more4){
			out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more4){
			
			
			
			if(j1==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Asset Document Details</td>"); 
			out.println("</tr>"); 
				
				
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
			out.println("</tr >"); 
			
			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
      
			


			else{
			if(!rs4.getString(4).equals("Yes"))
			if(j1>0 && j1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}	
			if(!rs4.getString(4).equals("Yes")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs4.getString(1)+"')\"><U>"+rs4.getString(2)+"<input value="+rs4.getString(1)+" type='hidden' name=TXT_DOC_CODE_"+j+"></td>"); 
			out.println("<td align=\"center\">"+rs4.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs4.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_status_"+j+" value=\"N\" unchecked onclick=\"check_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_napp_status_"+j+" value=\"N\" unchecked onclick=\"check_napp_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_REMARKS_"+j+"></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_fol_status_"+j+" value=\"N\" unchecked onclick=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_FOL_REMARKS_"+j+"></td>"); 
			out.println("</tr>");
			out.println("<input type=\"hidden\" name=hid_TXT_FROM_SCREEN_"+j+" value="+rs4.getString(5)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_TO_SCREEN_"+j+" value="+rs4.getString(6)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_INVOICE_NO_"+j+" value="+m_ref+">");
			}
			more4=rs4.next();
			j=j+1;
			j1=j1+1;
			}

			//out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");

			out.println("</table >"); 
			out.println("<HR>");
			}
			//out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			
			//-----client details-------//

			 //out.println	
				rs5=stmt5.executeQuery
			(
			"SELECT A.CODE,A.DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS,NVL(NEW_QUERY.FROM_SCREEN_NO,0),NVL(NEW_QUERY.TO_SCREEN_NO,0) "+
			"			   FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+  
			//-TO GET SCREEN NO-----------------------------------------------------------------------------
						"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"																				and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) NEW_QUERY "+

			//---------------------------------------------------------------------------------------------
			" 			   WHERE     "+
			"            A.CODE IN ( SELECT  CODE "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"																				and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) "+
			"                 AND  A.DOC_APP_TYPE='CLIENT' "+
			"                 AND  A.ACTIVE_STATUS='Y'   "+
			"									AND A.CODE=NEW_QUERY.CODE "+
			
			"         UNION   "+
			        
			"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, DECODE(C.STATUS,'Y','Yes','N','No','A','Applicable') STATUS,nvl(NEW_QUERY.FROM_SCREEN_NO,0),nvl(NEW_QUERY.TO_SCREEN_NO,0) "+
			"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A,   "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C , "+ ///*"+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,*/

			/*
			"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) NEW_QUERY "+

			
			/*"  WHERE B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY   "+
			"                         FROM  "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                         WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                       )   "+
			            
			"  AND DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y')  "+
			"  AND C.DOCUMENT_TYPE IN  "+
			"                      (SELECT CODE  "+
			"                      FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
			"                      WHERE FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                               WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1')  "+
			"                      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                          WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') AND  "+
			"                      ENTITY_TYPE IN (SELECT CLIENT_CATEGORY   "+
			"                                       FROM "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                                       WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                                       )   "+
			  
			"   AND  PRODUCT_CODE in (SELECT TRANSACTION_TYPE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                         WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"													and application_status='ACTIVATED') "+
			                                                                 
			"   AND B.CODE=A.CODE  "+
			"   AND ACTIVE_STATUS=('Y'))  "+
			*/
			
			/*
			"   WHERE C.DOCUMENT_TYPE=A.CODE  "+
			"   AND A.CODE=NEW_QUERY.CODE "+
			"   AND C.STATUS !='Y' "+
			"   AND C.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			"   AND C.CLIENT_CODE=UPPER('"+m_client+"')  "+
			"   AND C.PRO_INVOICE_NO IS NULL "+
			//"AND upper(C.OTHER_NO)=upper('"+m_sus_ref+"') "+
			"  ORDER BY STATUS ASC ");

		//int d1=0;//================================================================================
			//out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			int d1=0;

			boolean more5=rs5.next();
			if(more5) {
			//int d1=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more5){
			
			
			
			if(d1==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Client Document Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
			out.println("</tr >"); 
			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
			else{
			if(!rs5.getString(4).equals("Yes"))
			if(d1>0 && d1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}		
			
			if(!rs5.getString(4).equals("Yes")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs5.getString(1)+"')\"><U>"+rs5.getString(2)+"<input value="+rs5.getString(1)+" type='hidden' name=TXT_CLIENT_DOC_CODE_"+d+"></td>"); 
			out.println("<td align=\"center\">"+rs5.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs5.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_status_"+d+" value=\"N\" unchecked onclick=\"check_client_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_napp_status_"+d+" value=\"N\" unchecked onclick=\"check_client_napp_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input' name=TXT_CLIENT_REMARKS_"+d+" maxlength='50' type='text' ></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_fol_status_"+d+" value=\"N\" unchecked onclick=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CLIENT_FOL_REMARKS_"+d+"></td>"); 
			out.println("</tr>");
			out.println("<input type=\"hidden\" name=hid_TXT_FROM_SCREEN_CLIENT_"+d+" value="+rs5.getString(5)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_TO_SCREEN_CLIENT_"+d+" value="+rs5.getString(6)+">");
      }
			more5=rs5.next();
			d=d+1;
			d1=d1+1;
			}

			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

			out.println("</table >"); 
			out.println("<HR>");
      }
			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

     more_ref_no=rs_ref_no.next();
     }*/
			
			out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");
			
			
			
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
		
		
			out.println("<td width=\"10%\" style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_bank_drill('"+rs6.getString(1)+"')\"><U>"+rs6.getString(1)+"</td>");
			out.println("<td width=\"30%\" style='{text-align:left;}'>"+rs6.getString(7)+"</td>");
			out.println("<td width=\"20%\" style='{text-align:right;}'>"+rs6.getString(2)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(3)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(4)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(5)+"</td></tr>");
			
			more6=rs6.next();
			x=x+1;
			}

			out.println("</table>");

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width='*%'><b><u>Conditions</td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width='*%'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\" ></td>"); 
			out.println("</tr>");  
				
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			
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
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs1    !=null){try{rs1.close();   }catch(Exception e){}}
			if(rs2    !=null){try{rs2.close();   }catch(Exception e){}}
			if(rs3    !=null){try{rs3.close();   }catch(Exception e){}}
			if(rs4    !=null){try{rs4.close();   }catch(Exception e){}}
			if(rs5    !=null){try{rs5.close();   }catch(Exception e){}}
			if(rs6    !=null){try{rs6.close();   }catch(Exception e){}}
	
			
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
			if(stmt2  !=null){try{stmt2.close(); }catch(Exception e){}}
			if(stmt3  !=null){try{stmt3.close(); }catch(Exception e){}}
			if(stmt4  !=null){try{stmt4.close(); }catch(Exception e){}}
			if(stmt5  !=null){try{stmt5.close(); }catch(Exception e){}}
			if(stmt6  !=null){try{stmt6.close(); }catch(Exception e){}}
			
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}

				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
