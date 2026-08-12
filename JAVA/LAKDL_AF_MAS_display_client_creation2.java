
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - CLIENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_client_creation2 extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Client</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			//Individual
			out.println("var lineno=0;");
			out.println("var lineno_bank=0;");
			out.println("var lineno_credit=0;");
			out.println("var lineno_nonrelated=0;");
			out.println("var lineno_guarantor=0;");
			out.println("var lineno_family=0;");

			out.println("var arr_size=0;");
			out.println("var arr_size_family=0;");
			out.println("var arr_size_bank=0;");
			out.println("var arr_size_credit=0;");
			out.println("var arr_size_nonrelated=0;");
			out.println("var arr_size_guarantor=0;");
			
			//Corporate
			out.println("var lineno_company_dir = 0;");
			out.println("var lineno_subsidiaries = 0;");
			out.println("var lineno_customer = 0;");
			out.println("var lineno_credit_c = 0;");
			out.println("var lineno_ba = 0;");
			
			out.println("var arr_size_company = 0;");
			out.println("var arr_size_subsidiaries = 0;");
			out.println("var arr_size_customer = 0;");
			out.println("var arr_size_credit_c=0;");
			out.println("var arr_size_ba=0;");
			
			//Family Member Arrays
			out.println("var array_member_f = new Array();");
			out.println("var array_name_f = new Array();");
			out.println("var array_address_f = new Array();");
			out.println("var array_age_f = new Array();");
			out.println("var array_telno_f = new Array();");
			out.println("var array_moble_f = new Array();");
			
			//BA arrays
			out.println("var array_cat = new Array();");
			out.println("var array_activity = new Array();");
			
			//Employment Arrays
			out.println("var array_organization = new Array();");
			out.println("var array_telno = new Array();");
			out.println("var array_from_date = new Array();");
			out.println("var array_to_date = new Array();");
			out.println("var array_designation = new Array();");
			
			// Bank Arrays
			out.println("var array_banker = new Array();");
			out.println("var array_branch = new Array();");
			out.println("var array_acc_no = new Array();");
			out.println("var array_reference = new Array();");
			out.println("var array_telno_bank = new Array();");
			out.println("var array_fax_bank = new Array();");
			out.println("var array_relationship_b = new Array();");
			
			//Credit Facilities arrays
			out.println("var array_type = new Array();");
			out.println("var array_institute = new Array();");
			out.println("var array_contact_person = new Array();");
			out.println("var array_contract_no = new Array();");
			out.println("var array_security = new Array();");
			out.println("var array_app_amount = new Array();");
			out.println("var array_bal_amount = new Array();");
			out.println("var array_months = new Array();");
			
			//Non related referee arrays
			out.println("var array_name = new Array();");
			out.println("var array_relationship = new Array();");
			out.println("var array_period = new Array();");
			out.println("var array_design = new Array();");
			out.println("var array_telno_home = new Array();");
			out.println("var array_telno_office = new Array();");
			out.println("var array_mobileno = new Array();");
			
			//Guarantor  arrays
			out.println("var array_gcode = new Array();");
			out.println("var array_relationship_g = new Array();");
			out.println("var array_period_g = new Array();");
			out.println("var array_telno_g = new Array();");
			
			//Company Director arrays
			out.println("var array_name_dir = new Array();");
			out.println("var array_nic_no_dir = new Array();");
			out.println("var array_stake = new Array();");
			out.println("var array_no_shares = new Array();");
			out.println("var array_value = new Array();");
			out.println("var array_position = new Array();");
			
			//Subsidiary arrays
			out.println("var array_name_sub = new Array();");
			out.println("var array_stake_sub = new Array();");
			out.println("var array_value_sub = new Array();");
			out.println("var array_telno_sub = new Array();");
			out.println("var array_officer_sub = new Array();");
			out.println("var array_ba_sub = new Array();");
			
			//Customer arrays
			out.println("var array_name_cus = new Array();");
			out.println("var array_type_cus = new Array();");
			out.println("var array_add_cus = new Array();");
			out.println("var array_relation_cus = new Array();");
			out.println("var array_contact_person_cus = new Array();");
			out.println("var array_telno_cus = new Array();");
			
			//Corporate Credit Facilities arrays
			out.println("var array_institute_c = new Array();");
			out.println("var array_contact_person_c = new Array();");
			out.println("var array_type_c = new Array();");
			out.println("var array_equip_c = new Array();");
			out.println("var array_app_amount_c = new Array();");
			out.println("var array_rental = new Array();");
			out.println("var array_months_c = new Array();");
			out.println("var array_bal_amount_c = new Array();");

			
			out.println("function get_vector(data_vec) {");
			//out.println("				alert('Get vector');");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H1'){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("		 if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value =='H2'){");
			out.println("      display_income(data_vec); ");
			out.println("     }");
			out.println("}");
			
			//To display Income Expenses According to the table
			out.println("function display_income(data_vec){ ");
			out.println(" var i=0; "); 
			out.println(" var j=0; "); 
			out.println(" var x=0; "); 
			//out.println(" alert('data vec'+data_vec.length); ");
			out.println("  while(j<data_vec.length ) {  ");
			out.println(" if(data_vec[i+1] == 'IN'){ ");
			out.println(" e_data_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" >'+data_vec[i]+'</td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println(" else if(data_vec[i+1] == 'EX'){ ");
			out.println(" e_data_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" > '+data_vec[i]+'</td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println(" i=i+2; ");
			out.println(" j=j+2; ");
			out.println(" x=x+1; ");
			out.println("  }");
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			//out.println(" alert('Help status '+document.Form1.hid_help_status.value);");
			//out.println(" alert('Make request ');");
			out.println("if(document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation&data_val=\"+obj.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(){ ");
			//out.println(" alert('Help status '+document.Form1.hid_help_status.value);");
			//out.println("if(document.Form1.hid_help_status.value == 'H2' && document.Form1.SCREEN_NAME.value!=\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation1\";");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function header(){");
			out.println("e_mode.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\"> Organization </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Telephone No</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">From </TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">To</TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Designation</TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");
				
			out.println("function add_row_tot_dependents(){");	
			out.println(" e_row_tot_dep.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"10%\" align=\"right\" > No of Children  </td>'+");
			out.println(" '<td width=\"20%\"><input name=TXT_NO_CHILD class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '<td width=\"20%\" align=\"right\" > Total Dependents  </td>'+");
			out.println(" '<td width=\"*%\"><input name=TXT_TOT_DEP class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr ></table>'");	
			out.println("}");
				
			//To add Income Expense Headers, Total Income expense fields & other income expense fields 	
			out.println("function header_income_expense(){");
			out.println("e_header_income.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" ><b>Income</b></td>'+");
			out.println(" '<td width=\"40%\"><b>Gross</b></td>'+"); 
			out.println(" '</tr >'+");
	    out.println("'</table>';");

			out.println("e_other_income.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"5%\" align=\"left\" > Other Income </td>'+");
			out.println(" '<td width=\"25%\"><input name=TXT_OTHER_INCOME class=\"text_input\"  value=\"\" maxlength=\"20\" size=\"10\"></td>'+"); 
			out.println(" '<td width=\"40%\"><input name=TXT_OTHER_INCOME_AMOUNT class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr >'+");
	    out.println("'</table>';");
			
			out.println(" e_tot_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" align=\"right\" ><b> Total Income </b> </td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_TOT_INCOME class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr ></table>'");
			
			out.println("e_header_expense.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" ><b>Expense</b></td>'+");
			out.println(" '<td width=\"40%\"><b>Gross</b></td>'+"); 
			out.println(" '</tr >'+");
	    out.println("'</table>';");
			
			out.println("e_other_expense.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"5%\" align=\"left\" > Other Expense </td>'+");
			out.println(" '<td width=\"25%\"><input name=TXT_OTHER_EXPENSE class=\"text_input\"  value=\"\" maxlength=\"20\" size=\"10\"></td>'+"); 
			out.println(" '<td width=\"40%\"><input name=TXT_OTHER_EXPENSE_AMOUNT class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr >'+");
	    out.println("'</table>';");
			
			out.println(" e_tot_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" align=\"right\" ><b> Total Expense </b> </td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_TOT_EXPENSE class=\"text_input\"  value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println(" '</tr ></table>'");
			
     	out.println("}");	
				
			out.println("function header_ba(){");
			out.println(" lineno_ba=0; ");
			out.println("e_header_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"20%\" align=\"left\"> Category </TD>'+");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Activity</TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");	
			
			out.println("function header_bank(type){");
			out.println(" lineno_bank=0; ");
			out.println(" if(type ==\"I\") { ");
			out.println(" e_header_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Banker </TD>'+");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Branch </TD>'+");
			out.println(" '<TD WIDTH=\"20%\" align=\"left\">Account No</TD>' +");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Reference</TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Telephone </TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Fax </TD>' +");
			out.println(" '<TD WIDTH=\"*%\" align=\"left\">Relationship (Mts)  </TD>' +");
	    out.println(" '</TR></table>';");
			out.println(" }");
			out.println(" else {");
			out.println(" e_header_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Banker </TD>'+");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Branch </TD>'+");
			out.println(" '<TD WIDTH=\"20%\" align=\"left\">Account No</TD>' +");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Reference</TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Telephone </TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Fax </TD>' +");
			out.println(" '<TD WIDTH=\"*%\" align=\"left\">Relationship (Mts)  </TD>' +");
	    out.println(" '</TR></table>';");
			out.println(" }");
     	out.println("}");
				
			out.println("function header_credit(){");
			out.println(" lineno_credit=0; ");
			out.println("e_header_credit.innerHTML +='<table  border=\"1\" align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Type </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Name Of Institution</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Contact Person</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Contract No </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Security </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Approved Amount  </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Balance Amount  </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">No. of Months  </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");	
				
			out.println("function header_credit_c(){");
			out.println(" lineno_credit_c=0; ");
			out.println("e_header_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Name Of Institution </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Contact Person</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Type of Facility</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Equipment </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Approved Amount </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Monthly Rental </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Period(Months) </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Balance Payable </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");
		
			out.println("function header_nonrelated_ref(type){");
			out.println(" if(type=='I'){"); 
			out.println("  lineno_nonrelated = 0; ");
			out.println("  e_header_nonrelated.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"20%\" align=\"left\">Name </TD>'+");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Relationship</TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Period</TD>' +");
			out.println("  '<TD WIDTH=\"20%\" align=\"left\">Designation </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Tel No. - Res </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Tel No. - Office  </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Mobile No  </TD>' +");
	    out.println("  '</TR></table>';");
			out.println(" }");		
			out.println(" else{"); 
			out.println("  lineno_nonrelated = 0; ");
			out.println("  e_header_nonrelated_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"20%\" align=\"left\">Name </TD>'+");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Relationship</TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Period</TD>' +");
			out.println("  '<TD WIDTH=\"20%\" align=\"left\">Designation </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Tel No. - Res </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Tel No. - Office  </TD>' +");
			out.println("  '<TD WIDTH=\"10%\" align=\"left\">Mobile No  </TD>' +");
	    out.println("  '</TR></table>';");
			out.println(" }");		
     	out.println("}");		
			
			out.println("function header_family(){");
			out.println(" lineno_family=0; ");
			out.println("e_header_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Member</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Name</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Address</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Age </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Tel NO </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Moblie No </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");

			//Corporate ###
			out.println("function header_company_dir(){");
			out.println(" lineno_company_dir = 0; ");
			out.println("e_header_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\">Name </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">NIC No</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Stake</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">No of Shares </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Value(Rs.) </TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Position  </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");
				
			out.println("function header_subsidiaries(){");
			out.println(" lineno_subsidiaries = 0; ");
			out.println("e_header_subsidiaries.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\">Name </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Stake</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Value (Rs.)</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Telephone No </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Officer </TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Business Activities  </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");	
				
			out.println("function header_customer(){");
			out.println(" lineno_customer = 0; ");
			out.println("e_header_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\">Name </TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Type</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Address</TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Relationship</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Contact Person </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Tel No </TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");	

			out.println("function add_button(){");
			out.println("e_mode2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_EMP\" value=\"Add\" onClick=\"add_row_emp()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");

			
			out.println("function add_button_bank(type){");
			//out.println("alert('add but type '+type);");
			out.println("if(type=='I') { ");
			out.println("e_add_but_bank.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" onClick=add_row_bank(\"'+type+'\") type=\"button\" name=\"BUT_ADD_BANK\" value=\"Add\" ></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			out.println("else { ");
			out.println("e_add_but_bank_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" onClick=add_row_bank(\"'+type+'\") type=\"button\" name=\"BUT_ADD_BANK\" value=\"Add\" ></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			out.println("}");
			
			out.println("function add_button_credit(){");
			out.println("e_add_but_credit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CREDIT\" value=\"Add\" onClick=\"add_row_credit()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_credit_c(){");
			out.println("e_add_but_credit_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CREDIT_C\" value=\"Add\" onClick=\"add_row_credit_c()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_nonrelated(type){");
			out.println(" if(type=='I') { ");
			out.println("  e_add_but_nonrelated.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<td width=\"80%\"></td>'+"); 
			out.println("  '<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_NON\" value=\"Add\" onClick=add_row_nonrelated_ref(\"'+type+'\")></td>'+"); 
	    out.println("  '</TR></table>';");
			out.println(" }");
			out.println(" else {");
			out.println("  e_add_but_nonrelated_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<td width=\"80%\"></td>'+"); 
			out.println("  '<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_NON\" value=\"Add\" onClick=add_row_nonrelated_ref(\"'+type+'\")></td>'+"); 
	    out.println("  '</TR></table>';");
			out.println(" }");
			out.println("}");
			 
			out.println("function add_button_guarantor(){");
			out.println("e_add_but_guarantor.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_GUA\" value=\"Add\" onClick=\"add_row_guarantor()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_family(){");
			out.println("e_add_but_family_members.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_FAM\" value=\"Add\" onClick=\"add_row_family()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_company_dir(){");
			out.println("e_add_but_company_directors.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_COM\" value=\"Add\" onClick=\"add_row_company_dir()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_customer(){");
			out.println("e_add_but_customer.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CUS\" value=\"Add\" onClick=\"add_row_customer()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_ba(){");
			out.println("e_add_but_ba.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_BA\" value=\"Add\" onClick=\"add_row_ba()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_subsidiaries(){");
			out.println("e_add_but_subsidiaries.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"80%\"></td>'+"); 
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_SUB\" value=\"Add\" onClick=\"add_row_subsidiaries()\"></td>'+"); 
	    out.println("'</TR></table>';");
			out.println("}");
			
			//Individual####
			out.println("function add_label_credit(){");
			out.println("e_label_credit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_nonrelated_ref(type){");
			out.println("if(type=='I'){ "); 
			out.println("  e_label_nonrelated.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<tr>'+");
			out.println("  '<td width=\"80%\" ><B> C.NON RELATED REFEREES </B></td>'+"); 
			out.println("  '</tr>'+");
	    out.println("  '</TR></table>';");
			out.println(" }");
			out.println("else {");
			out.println("  e_label_nonrelated_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<tr>'+");
			out.println("  '<td width=\"80%\" ><B> D.NON RELATED REFEREES </B></td>'+"); 
			out.println("  '</tr>'+");
	    out.println("  '</TR></table>';");
			out.println("}");
			out.println("}");
			
			/*out.println("function add_label_guarantor(){");
			out.println("e_label_guarantor.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> D.PROPOSED GUARANTORS FOR THIS FACILITY  </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");*/
			
			out.println("function add_label_family(){");
			out.println("e_label_family_members.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> E.DETAILS OF FAMILY MEMBERS  </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_income_expense(){");
			out.println("e_label_income_expense.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> D.DETAILS OF PRESENT MONTHY INCOME/EXPENSES  </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			//Corporate ######
			out.println("function add_label_credit_c(){");
			out.println("e_label_credit_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_ba(){");
			out.println("e_label_ba.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> Business Activities </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_company_dir(){");
			out.println("e_label_company_directors.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> Directors/Partners/Shareholders </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_subsidiaries(){");
			out.println("e_label_subsidiaries.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> Subsidiaries & Associated Companies </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_bank(){");
			out.println("e_label_bank.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> Banker & Auditors </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_customer(){");
			out.println("e_label_customer.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> C.TRADE CUSTOMERS & SUPPLIERS </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_credit(){");
			out.println("e_label_credit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
	    out.println("'</TR></table>';");
			out.println("}");

	
	    out.println("function del_row(rowNo){"); 
			
			//out.println("alert('test1 -' +rowNo)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			
			out.println("m_organization=\"TXT_ORGANIZATION\"+i");
			out.println("m_tel_no=\"TXT_TEL_NO\"+i");
			out.println("m_from_date=\"TXT_FROM_DATE\"+i");
			out.println("m_to_date=\"TXT_TO_DATE\"+i");
			out.println("m_designation=\"TXT_DESIGNATION2\"+i");
			

			out.println("if(i==rowNo)");
			out.println("continue;");
			
		  //out.println("alert('organization -' +document.Form1.elements[m_organization].value)");
			out.println("   if(document.Form1.elements[m_to_date].value!='' && document.Form1.elements[m_designation].value !='') { " );
			out.println("     array_organization[j]=document.Form1.elements[m_organization].value;");
		  out.println("     array_telno[j]=document.Form1.elements[m_tel_no].value;");
			out.println("     array_from_date[j]=document.Form1.elements[m_from_date].value;");
			out.println("     array_to_date[j]=document.Form1.elements[m_to_date].value;");
			out.println("     array_designation[j]=document.Form1.elements[m_designation].value;");
		  out.println("   }");
			out.println("  else if(document.Form1.elements[m_to_date].value =='' && document.Form1.elements[m_designation].value !=''){ ");
			out.println("     array_organization[j]=document.Form1.elements[m_organization].value;");
		  out.println("     array_telno[j]=document.Form1.elements[m_tel_no].value;");
			out.println("     array_from_date[j]=document.Form1.elements[m_from_date].value;");
			out.println("   alert(' to_date -  '+document.Form1.elements[m_to_date].value); ");
			out.println("     array_to_date[j]='';");
			out.println("     array_designation[j]=document.Form1.elements[m_designation].value;");
			out.println("  }");
			out.println("  else if(document.Form1.elements[m_to_date].value !='' && document.Form1.elements[m_designation].value ==''){ ");
			out.println("     array_organization[j]=document.Form1.elements[m_organization].value;");
		  out.println("     array_telno[j]=document.Form1.elements[m_tel_no].value;");
			out.println("     array_from_date[j]=document.Form1.elements[m_from_date].value;");
			out.println("     array_to_date[j]=document.Form1.elements[m_to_date].value;");
			out.println("     array_designation[j]='';");
			out.println("  }");
			out.println("  else if(document.Form1.elements[m_to_date].value =='' && document.Form1.elements[m_designation].value ==''){ ");
			out.println("     array_organization[j]=document.Form1.elements[m_organization].value;");
		  out.println("     array_telno[j]=document.Form1.elements[m_tel_no].value;");
			out.println("     array_from_date[j]=document.Form1.elements[m_from_date].value;");
			out.println("     array_to_date[j]='';");
			out.println("     array_designation[j]='';");
			out.println("  }");
			out.println(" j=j+1;");
			out.println("}");
		
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}");
			
			  out.println("function write_data(size){");
				//out.println("alert('size'+size);");
				out.println("e_mode_emp.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_organization[j]==\"\" && array_telno[j]==\"\" && array_from_date[j]==\"\" && array_to_date[j]==\"\" && array_designation[j]==\"\" ){");
				
				out.println("e_mode_emp.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+j+' value=\"\" maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FROM_DATE'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TO_DATE'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION2'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\">'+");
			  out.println("'</td></tr></table>';");

        out.println("continue;");
				out.println("}");
						
			  out.println("e_mode_emp.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+j+' value='+array_organization[j]+'  maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' value='+array_telno[j]+'  maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FROM_DATE'+j+' value='+array_from_date[j]+' maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TO_DATE'+j+' value='+array_to_date[j]+'  maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION2'+j+' value='+array_designation[j]+' maxlength=\"20\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				
			
			  out.println("}");
			  out.println("}");
			
			
			//To DELETE bank row
			out.println("function del_row_bank(rowNo,type){"); 
			
			//out.println("alert('test1 bank -' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_bank;i++){");
			
			out.println("m_bank=\"TXT_BANK_CODE\"+i");
			out.println("m_branch=\"TXT_BRANCH_CODE\"+i");
			out.println("m_accno=\"TXT_ACCOUNT_NO\"+i");
			out.println("m_reference=\"TXT_REFERENCE\"+i");
			out.println("m_telno=\"TXT_TEL_NO2\"+i");
			out.println("m_faxno=\"TXT_FAX_NO\"+i");
			out.println("m_relationship=\"TXT_RELATIONSHIP2\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
		//	out.println("alert('name -' +document.Form1.elements[m_organization].value)");
			
			out.println("array_banker[j]=document.Form1.elements[m_bank].value;");
		  out.println("array_branch[j]=document.Form1.elements[m_branch].value;");
			out.println("array_acc_no[j]=document.Form1.elements[m_accno].value;");
			out.println("array_reference[j]=document.Form1.elements[m_reference].value;");
			out.println("array_telno_bank[j]=document.Form1.elements[m_telno].value;");
			out.println("array_fax_bank[j]=document.Form1.elements[m_faxno].value;");
			out.println("array_relationship_b[j]=document.Form1.elements[m_relationship].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_bank=lineno_bank-1;");
			out.println("arr_size_bank=arr_size_bank-1;");
		  out.println("write_data_bank(arr_size_bank,type);");
			out.println("}");
	
			  out.println("function write_data_bank(size,type){");
				//out.println("alert('arr_size_bank '+size);");
				out.println("e_txt_bank.innerHTML=\"\";");
				out.println("e_txt_bank_c.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_banker[j]==\"\" && array_branch[j]==\"\" && array_acc_no[j]==\"\" && array_reference[j]==\"\" && array_telno_bank[j]==\"\" && array_fax_bank[j]==\"\" && array_relationship_b[j]==\"\" ){");
				out.println("if(type=='I'){");
				out.println("e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\"></td>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\"></td>'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"\" maxlength=\"4\" size=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				out.println("else{");
				out.println("e_txt_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\"></td>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\"></td>'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"\" maxlength=\"4\" size=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
        out.println("continue;");
				out.println("}");
				
				
				out.println("if(type=='I'){");
				out.println("e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+j+' value='+array_banker[j]+'   maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\"></td>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+j+'  value='+array_branch[j]+'   maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\"></td>'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value='+array_acc_no[j]+'   maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+'  value='+array_reference[j]+'   maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+j+'  value='+array_telno_bank[j]+'   maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+j+'   value='+array_fax_bank[j]+'   maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value='+array_relationship_b[j]+'   maxlength=\"4\" size=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				out.println("else{");
				out.println("e_txt_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+j+' value='+array_banker[j]+'   maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\"></td>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+j+'  value='+array_branch[j]+'   maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\"></td>'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value='+array_acc_no[j]+'   maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+'  value='+array_reference[j]+'   maxlength=\"20\" size=\"20\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+j+'  value='+array_telno_bank[j]+'   maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+j+'   value='+array_fax_bank[j]+'   maxlength=\"10\" size=\"10\">'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value='+array_relationship_b[j]+'   maxlength=\"4\" size=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");


			  out.println("}");
			  out.println("}");
				
				
			//To DELETE Credit Facilities row
			out.println("function del_row_credit(rowNo){"); 
			
			//out.println("alert('test1 bank -' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_credit;i++){");
			
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+i");
			out.println("m_institute=\"TXT_INSTITUTION\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+i");
			out.println("m_contract_no=\"TXT_CONTRACT_NO\"+i");
			out.println("m_security=\"TXT_SECURITY\"+i");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+i");
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+i");
			out.println("m_months=\"TXT_MONTHS\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
		//	out.println("alert('name -' +document.Form1.elements[m_organization].value)");
			
			out.println("array_type[j]=document.Form1.elements[m_type].value;");
		  out.println("array_institute[j]=document.Form1.elements[m_institute].value;");
			out.println("array_contact_person[j]=document.Form1.elements[m_contact_p].value;");
			out.println("array_contract_no[j]=document.Form1.elements[m_contract_no].value;");
			out.println("array_security[j]=document.Form1.elements[m_security].value;");
			out.println("array_app_amount[j]=document.Form1.elements[m_app_amount].value;");
			out.println("array_bal_amount[j]=document.Form1.elements[m_bal_amount].value;");
			out.println("array_months[j]=document.Form1.elements[m_months].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_credit=lineno_credit-1;");
			out.println("arr_size_credit=arr_size_credit-1;");
		  out.println("write_data_credit(arr_size_credit);");
			out.println("}");
	
			  out.println("function write_data_credit(size){");
				//out.println("alert('arr_size_bank '+size);");
				out.println("e_txt_credit.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if( array_institute[j]==\"\" && array_contact_person[j]==\"\" && array_contract_no[j]==\"\" && array_security[j]==\"\" && array_app_amount[j]==\"\" && array_bal_amount[j]==\"\"  && array_months[j]==\"\" ){");
				
				out.println("e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"\"  maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"\" maxlength=\"100\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTRACT_NO'+j+'value=\"\" maxlength=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_SECURITY'+j+' value=\"\" maxlength=\"100\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"\" maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"\" maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MONTHS'+j+' value=\"\" maxlength=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			  out.println("'</td></tr></table>';");

        out.println("continue;");
				out.println("}");
				
				out.println(" if( array_type[j] ==\"VEHICLE LOAN\" ){ ");
				out.println("  ");
				out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("  '<td WIDTH=\"15%\">'+ ");
			  out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("  '<OPTION value=\"VEHICLE LOAN\" SELECTED >Vehicle Loan </option>'+");
			  out.println("  '<OPTION value=\"HOUSE LOAN\" >House Loan </option>'+");
				out.println("  '<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("  '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute[j]+'  maxlength=\"100\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person[j]+' maxlength=\"100\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value='+array_contract_no[j]+' maxlength=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value='+array_security[j]+' maxlength=\"100\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months[j]+' maxlength=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			  out.println("  '</td></tr></table>';");
				out.println(" }");
				
				out.println(" else if( array_type[j] ==\"HOUSE LOAN\" ){ ");
				out.println("   ");
				out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("  '<td WIDTH=\"15%\">'+ ");
			  out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			  out.println("  '<OPTION value=\"HOUSE LOAN\" SELECTED >House Loan </option>'+");
				out.println("  '<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("  '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute[j]+'  maxlength=\"100\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person[j]+' maxlength=\"100\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value='+array_contract_no[j]+' maxlength=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value='+array_security[j]+' maxlength=\"100\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months[j]+' maxlength=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			  out.println("  '</td></tr></table>';");
				out.println(" }");
				
				out.println(" else if( array_type[j] ==\"PERSONAL LOAN\" ){ ");
				out.println("  ");
				out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("  '<td WIDTH=\"15%\">'+ ");
			  out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			  out.println("  '<OPTION value=\"HOUSE LOAN\"  >House Loan </option>'+");
				out.println("  '<OPTION value=\"PERSONAL LOAN\" SELECTED >Personal Loan </option>'+");
			  out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("  '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute[j]+'  maxlength=\"100\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person[j]+' maxlength=\"100\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value='+array_contract_no[j]+' maxlength=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value='+array_security[j]+' maxlength=\"100\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months[j]+' maxlength=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			  out.println("  '</td></tr></table>';");
				out.println(" }");
				
				out.println(" else if( array_type[j] ==\"CREDIT CARD\" ){ ");
				out.println("  ");
				out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("  '<td WIDTH=\"15%\">'+ ");
			  out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			  out.println("  '<OPTION value=\"HOUSE LOAN\"  >House Loan </option>'+");
				out.println("  '<OPTION value=\"PERSONAL LOAN\"  >Personal Loan </option>'+");
			  out.println("  '<OPTION value=\"CREDIT CARD\" SELECTED >Credit Card </option>'+");
			  out.println("  '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute[j]+'  maxlength=\"100\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person[j]+' maxlength=\"100\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value='+array_contract_no[j]+' maxlength=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value='+array_security[j]+' maxlength=\"100\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount[j]+' maxlength=\"25\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months[j]+' maxlength=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			  out.println("  '</td></tr></table>';");
				out.println("  }");

			  out.println("}");
			  out.println("}");
			  
				
			//To DELETE Non-related ref row
			out.println("function del_row_nonrelated_ref(rowNo,type){"); 
			
			//out.println("alert('Non related type :' +type)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_nonrelated;i++){");
			
			out.println("m_ref_name=\"TXT_NAME_REFEREE\"+i");
			out.println("m_relationship=\"TXT_RELATIONSHIP3\"+i");
			out.println("m_period=\"TXT_PERIOD\"+i");
			out.println("m_design=\"TXT_DESIGNATION3\"+i");
			out.println("m_home_telno=\"TXT_HOME_TEL_NO\"+i");
			out.println("m_off_telno=\"TXT_OFFICE_TEL_NO\"+i");
			out.println("m_mobile_no=\"TXT_MOBILE_NO\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_name[j]=document.Form1.elements[m_ref_name].value;");
		  out.println("array_relationship[j]=document.Form1.elements[m_relationship].value;");
			out.println("array_period[j]=document.Form1.elements[m_period].value;");
			out.println("array_design[j]=document.Form1.elements[m_design].value;");
			out.println("array_telno_home[j]=document.Form1.elements[m_home_telno].value;");
			out.println("array_telno_office[j]=document.Form1.elements[m_off_telno].value;");
			out.println("array_mobileno[j]=document.Form1.elements[m_mobile_no].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_nonrelated = lineno_nonrelated-1;");
			out.println("arr_size_nonrelated = arr_size_nonrelated-1;");
		  out.println("write_data_nonrelated(arr_size_nonrelated,type);");
			out.println("}");
	
			  out.println("function write_data_nonrelated(size,type){");
				//out.println("alert('write data size'+size);");
				out.println("e_txt_nonrelated.innerHTML=\"\";");
				out.println("e_txt_nonrelated_c.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_name[j]==\"\" && array_relationship[j]==\"\" && array_design[j]==\"\" && array_telno_home[j]==\"\"){");
				out.println("if(type=='I') { ");
				out.println("e_txt_nonrelated.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"\" maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value=\"\" maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' value=\"\" maxlength=\"3\" size=\"10\" >'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"\" maxlength=\"50\"  size=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				
				out.println("else { ");
				out.println("e_txt_nonrelated_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"\" maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value=\"\" maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' value=\"\" maxlength=\"3\" size=\"10\" >'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"\" maxlength=\"50\"  size=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"\" maxlength=\"10\" size=\"10\" >'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
        out.println("continue;");
				out.println("}");
				
				out.println("if(type=='I') { ");
				//out.println("alert('write data '+type);");
				out.println("e_txt_nonrelated.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value='+array_name[j]+' maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value='+array_relationship[j]+' maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' value='+array_period[j]+' maxlength=\"3\" size=\"10\" >'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+j+' value='+array_design[j]+' maxlength=\"50\"  size=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value='+array_telno_home[j]+' maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value='+array_telno_office[j]+' maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+j+' value='+array_mobileno[j]+' maxlength=\"10\" size=\"10\" >'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				out.println("else { ");
				out.println("e_txt_nonrelated_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value='+array_name[j]+' maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value='+array_relationship[j]+' maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' value='+array_period[j]+' maxlength=\"3\" size=\"10\" >'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+j+' value='+array_design[j]+' maxlength=\"50\"  size=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value='+array_telno_home[j]+' maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value='+array_telno_office[j]+' maxlength=\"10\" size=\"10\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+j+' value='+array_mobileno[j]+' maxlength=\"10\" size=\"10\" >'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			  out.println("'</td></tr></table>';");
				out.println("}");

			  out.println("}");
			  out.println("}");

				
			//To delete guarantor row
			out.println("function del_row_guarantor(rowNo){"); 
			//out.println("alert(' row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_guarantor;i++){");
			
			out.println("m_gcode=\"TXT_GUARANTOR_CODE\"+i");
			out.println("m_relationship=\"TXT_RELATIONSHIP_G\"+i");
			out.println("m_period=\"TXT_PERIOD_G\"+i");
			out.println("m_telno=\"TXT_TEL_NO_G\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_gcode[j]=document.Form1.elements[m_gcode].value;");
		  out.println("array_relationship_g[j]=document.Form1.elements[m_relationship].value;");
			out.println("array_period_g[j]=document.Form1.elements[m_period].value;");
			out.println("array_telno_g[j]=document.Form1.elements[m_telno].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_guarantor = lineno_guarantor-1;");
			out.println("arr_size_guarantor = lineno_guarantor;");
		  out.println("write_data_guarantor(arr_size_guarantor);");
			out.println("}");
	
			  out.println("function write_data_guarantor(size){");
				//out.println("alert('arr_size_guarantor '+size);");
				out.println("e_txt_guarantor.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_gcode[j]==\"\" && array_relationship_g[j]==\"\" && array_period_g[j]==\"\" && array_telno_g[j]==\"\"){");
				
				out.println("e_txt_guarantor.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_GUARANTOR_CODE'+j+' value=\"\" maxlength=\"10\" size=\"10\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_GUARANTOR_CODE'+j+' value=\"?\" onClick=\"help_button_guarantor('+j+')\"></TD>'+");
				//out.println("'<TD WIDTH=\"5%\"></td>'+");
		    out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_G'+j+' value=\"\" maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD_G'+j+' value=\"\" maxlength=\"2\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_G'+j+' value=\"\" maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_GUARANTOR_DEL'+j+' value=\"Delete\" onClick=\"del_row_guarantor('+j+')\">'+");
			  out.println("'</td></tr></table>';");


        out.println("continue;");
				out.println("}");
				
				out.println("e_txt_guarantor.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_GUARANTOR_CODE'+j+' value='+array_gcode[j]+' maxlength=\"10\" size=\"10\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_GUARANTOR_CODE'+j+' value=\"?\" onClick=\"help_button_guarantor('+j+')\"></TD>'+");
				//out.println("'<TD WIDTH=\"5%\"></td>'+");
		    out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_G'+j+' value='+array_relationship_g[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD_G'+j+' value='+array_period_g[j]+' maxlength=\"2\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_G'+j+' value='+array_telno_g[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_GUARANTOR_DEL'+j+' value=\"Delete\" onClick=\"del_row_guarantor('+j+')\">'+");
			  out.println("'</td></tr></table>';");


			  out.println("}");
			  out.println("}");
				
			out.println("function del_row_ba(rowNo){"); 
			//out.println("alert('customer row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_ba;i++){");
			
			out.println("m_cat=\"TXT_CAT_TYPE_CODE_BA\"+i");
			out.println("m_activity=\"TXT_ACTIVITY_BA\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('Category  :  ' +document.Form1.elements[m_cat].value)");
			
			out.println("array_cat[j]=document.Form1.elements[m_cat].value;");
		  out.println("array_activity[j]=document.Form1.elements[m_activity].value;");
			
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_ba = lineno_ba-1;");
			out.println("arr_size_ba = arr_size_ba-1;");
		  out.println("write_data_ba(arr_size_ba);");
			out.println("}");
	
			  out.println("function write_data_ba(size){");
				//out.println("alert('arr_size_customer : '+size);");
				out.println("e_txt_ba.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_activity[j]==\"\"){");
				
				out.println("e_txt_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			  out.println("'<OPTION value=\"OTHER\" >Other </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"50%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value=\"\" maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			  out.println("'</td></tr></table>';");

        out.println("continue;");
				out.println("}");
				
				out.println(" if( array_cat[j] ==\"PRIME\" ){ ");
				out.println(" e_txt_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println(" '<td WIDTH=\"20%\">'+ ");
			  out.println(" '<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			  out.println(" '<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			  out.println(" '<OPTION value=\"OTHER\" >Other </option>'+");
			  out.println(" '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println(" '<TD WIDTH=\"50%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value='+array_activity[j]+'  maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println(" '<td><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			  out.println(" '</td></tr></table>';");
				out.println(" }");
				out.println(" else if( array_cat[j] ==\"OTHER\" ){ ");
				out.println(" e_txt_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println(" '<td WIDTH=\"20%\">'+ ");
			  out.println(" '<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			  out.println(" '<OPTION value=\"PRIME\"  >Prime </option>'+");
			  out.println(" '<OPTION value=\"OTHER\" SELECTED >Other </option>'+");
			  out.println(" '</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println(" '<TD WIDTH=\"50%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value='+array_activity[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println(" '<td><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			  out.println(" '</td></tr></table>';");
				out.println(" }");
				
			  out.println("}");
			  out.println("}");
			
				
			//Corporate
			//To delete company directors row
			out.println("function del_row_company_dir(rowNo){"); 
			//out.println("alert(' row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_company;i++){");
			
			out.println("m_name=\"TXT_NAME_DIR\"+i");
			out.println("m_nicno=\"TXT_NIC_NO_DIR\"+i");
			out.println("m_stake=\"TXT_STAKE\"+i");
			out.println("m_no_shares=\"TXT_NO_OF_SHARES\"+i");
			out.println("m_value=\"TXT_VALUE\"+i");
			out.println("m_position=\"TXT_POSITION\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_name_dir[j]=document.Form1.elements[m_name].value;");
		  out.println("array_nic_no_dir[j]=document.Form1.elements[m_nicno].value;");
			out.println("array_stake[j]=document.Form1.elements[m_stake].value;");
			out.println("array_no_shares[j]=document.Form1.elements[m_no_shares].value;");
			out.println("array_value[j]=document.Form1.elements[m_value].value;");
			out.println("array_position[j]=document.Form1.elements[m_position].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_company_dir = lineno_company_dir-1;");
			out.println("arr_size_company = lineno_company_dir;");
		  out.println("write_data_company_dir(arr_size_company);");
			out.println("}");
	
			  out.println("function write_data_company_dir(size){");
				//out.println("alert('arr_size_guarantor '+size);");
				out.println("e_txt_company_directors.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_name_dir[j]==\"\" && array_nic_no_dir[j]==\"\" && array_stake[j]==\"\" && array_no_shares[j]==\"\" && array_value[j]==\"\" && array_position[j]==\"\"){");
				
				out.println("e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_DIR'+j+' value=\"\"  maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value=\"\"  maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE'+j+' value=\"\"  maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value=\"\"  maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE'+j+' value=\"\"  maxlength=\"25\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_POSITION'+j+' value=\"\"  maxlength=\"50\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+lineno+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+");
			  out.println("'</td></tr></table>';");


        out.println("continue;");
				out.println("}");
				
				out.println("e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_DIR'+j+' value='+array_name_dir[j]+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value='+array_nic_no_dir[j]+' maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE'+j+' value='+array_stake[j]+' maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value='+array_no_shares[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE'+j+' value='+array_value[j]+' maxlength=\"25\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_POSITION'+j+' value='+array_position[j]+' maxlength=\"50\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+lineno+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+");
			  out.println("'</td></tr></table>';");


			  out.println("}");
			  out.println("}");
				
				
				out.println("function del_row_subsidiaries(rowNo){"); 
			//out.println("alert(' row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_subsidiaries;i++){");
			
			out.println("m_name=\"TXT_NAME_SUB\"+i");
			out.println("m_stake=\"TXT_STAKE_SUB\"+i");
			out.println("m_value=\"TXT_VALUE_SUB\"+i");
			out.println("m_telno=\"TXT_TEL_NO_SUB\"+i");
			out.println("m_officer=\"TXT_OFFICER_SUB\"+i");
			out.println("m_activities=\"TXT_ACTIVITIES_SUB\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_name_sub[j]=document.Form1.elements[m_name].value;");
		  out.println("array_stake_sub[j]=document.Form1.elements[m_stake].value;");
			out.println("array_value_sub[j]=document.Form1.elements[m_value].value;");
			out.println("array_telno_sub[j]=document.Form1.elements[m_telno].value;");
			out.println("array_officer_sub[j]=document.Form1.elements[m_officer].value;");
			out.println("array_ba_sub[j]=document.Form1.elements[m_activities].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_subsidiaries = lineno_subsidiaries-1;");
			out.println("arr_size_subsidiaries = lineno_subsidiaries;");
		  out.println("write_data_subsidiaries(arr_size_subsidiaries);");
			out.println("}");
	
			  out.println("function write_data_subsidiaries(size){");
				//out.println("alert('arr_size_guarantor '+size);");
				out.println("e_txt_subsidiaries.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_name_sub[j]==\"\" && array_stake_sub[j]==\"\" && array_value_sub[j]==\"\" && array_telno_sub[j]==\"\" && array_officer_sub[j]==\"\" && array_ba_sub[j]==\"\"){");
				
				out.println("e_txt_subsidiaries.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_SUB'+j+' value=\"\" maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE_SUB'+j+' value=\"\" maxlength=\"5\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE_SUB'+j+' value=\"\" maxlength=\"25\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_SUB'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICER_SUB'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITIES_SUB'+j+' value=\"\" maxlength=\"100\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+j+' value=\"Delete\" onClick=\"del_row_subsidiaries('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				

        out.println("continue;");
				out.println("}");
				
				out.println("e_txt_subsidiaries.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_SUB'+j+' value='+array_name_sub[j]+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE_SUB'+j+'  value='+array_stake_sub[j]+' maxlength=\"5\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE_SUB'+j+'  value='+array_value_sub[j]+' maxlength=\"25\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_SUB'+j+'  value='+array_telno_sub[j]+' maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICER_SUB'+j+'  value='+array_officer_sub[j]+' maxlength=\"20\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITIES_SUB'+j+'  value='+array_ba_sub[j]+' maxlength=\"100\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+j+' value=\"Delete\" onClick=\"del_row_subsidiaries('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				
			  out.println("}");
			  out.println("}");

				
		  out.println("function del_row_customer(rowNo){"); 
			//out.println("alert('customer row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_customer;i++){");
			
			out.println("m_name=\"TXT_CUSTOMER_NAME\"+i");
			out.println("m_type=\"TXT_TYPE_C\"+i");
			out.println("m_add=\"TXT_ADDRESS_CUS\"+i");
			out.println("m_relation=\"TXT_RELATIONSHIP_CUS\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON_CUS\"+i");
			out.println("m_telno=\"TXT_TEL_NO_CUS\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_name_cus[j]=document.Form1.elements[m_name].value;");
		  out.println("array_type_cus[j]=document.Form1.elements[m_type].value;");
			out.println("array_add_cus[j]=document.Form1.elements[m_add].value;");
			out.println("array_relation_cus[j]=document.Form1.elements[m_relation].value;");
			out.println("array_contact_person_cus[j]=document.Form1.elements[m_contact_p].value;");
			out.println("array_telno_cus[j]=document.Form1.elements[m_telno].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_customer = lineno_customer-1;");
			out.println("arr_size_customer = lineno_customer;");
		  out.println("write_data_customer(arr_size_customer);");
			out.println("}");
	
			  out.println("function write_data_customer(size){");
				//out.println("alert('arr_size_customer : '+size);");
				out.println("e_txt_customer.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if(array_name_cus[j]==\"\" && array_add_cus[j]==\"\" && array_relation_cus[j]==\"\" && array_contact_person_cus[j]==\"\" && array_telno_cus[j]==\"\"){");
				
				out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value=\"\" maxlength=\"100\" size=\"50\"></TD>'+");
				out.println("'<td WIDTH=\"10%\">'+ ");
			  out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"CUSTOMER\" SELECTED >Customer </option>'+");
			  out.println("'<OPTION value=\"SUPPLIER\" >Supplier </option>'+");
			  out.println("'</SELECT></td>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value=\"\"  maxlength=\"100\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'  value=\"\" maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value=\"\" maxlength=\"100\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value=\"\" maxlength=\"20\" size=\"10\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				

        out.println("continue;");
				out.println("}");
				
				out.println("if(array_type_cus[j]==\"CUSTOMER\") {");
				out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");		 							
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value='+array_name_cus[j]+' maxlength=\"100\" size=\"50\"></TD>'+");
				out.println("'<td WIDTH=\"10%\">'+ ");
			  out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"CUSTOMER\" SELECTED >Customer </option>'+");
			  out.println("'<OPTION value=\"SUPPLIER\" >Supplier </option>'+");
			  out.println("'</SELECT></td>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value='+array_add_cus[j]+'  maxlength=\"100\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'  value='+array_relation_cus[j]+' maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value='+array_contact_person_cus[j]+' maxlength=\"100\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value='+array_telno_cus[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				out.println("else {");
				out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");		 							
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value='+array_name_cus[j]+' maxlength=\"100\" size=\"50\"></TD>'+");
				out.println("'<td WIDTH=\"10%\">'+ ");
			  out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"CUSTOMER\" >Customer </option>'+");
			  out.println("'<OPTION value=\"SUPPLIER\" SELECTED >Supplier </option>'+");
			  out.println("'</SELECT></td>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value='+array_add_cus[j]+'  maxlength=\"100\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'  value='+array_relation_cus[j]+' maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value='+array_contact_person_cus[j]+' maxlength=\"100\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value='+array_telno_cus[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
	
			  out.println("}");
			  out.println("}");
				
				//To DELETE Credit Facilities row
			out.println("function del_row_credit_c(rowNo){"); 
			
			//out.println("alert('rowNo :' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size_credit_c;i++){");

			out.println("m_institute=\"TXT_INSTITUTION\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+i");
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+i");
			out.println("m_equip=\"TXT_EQUIPMENT\"+i");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+i");
			out.println("m_rental=\"TXT_MONTHLY_RENTAL\"+i");
			out.println("m_months=\"TXT_MONTHS\"+i");;
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");

		  out.println("array_institute_c[j]=document.Form1.elements[m_institute].value;");
			out.println("array_contact_person_c[j]=document.Form1.elements[m_contact_p].value;");
			out.println("array_type_c[j]=document.Form1.elements[m_type].value;");
			out.println("array_equip_c[j]=document.Form1.elements[m_equip].value;");
			out.println("array_app_amount_c[j]=document.Form1.elements[m_app_amount].value;");
			out.println("array_rental[j]=document.Form1.elements[m_rental].value;");
			out.println("array_months_c[j]=document.Form1.elements[m_months].value;");
			out.println("array_bal_amount_c[j]=document.Form1.elements[m_bal_amount].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_credit_c = lineno_credit_c-1;");
			out.println("arr_size_credit_c = arr_size_credit_c-1;");
		  out.println("write_data_credit_c(arr_size_credit_c);");
			out.println("}");
	
			  out.println("function write_data_credit_c(size){");
				//out.println("alert('arr_size_credit corp : '+size);");
				out.println("e_txt_credit_c.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
	
			
			  out.println("if( array_institute_c[j]==\"\" && array_contact_person_c[j]==\"\" && array_equip_c[j]==\"\" && array_app_amount_c[j]==\"\" && array_rental[j]==\"\" && array_months_c[j]==\"\"  && array_bal_amount_c[j]==\"\" ){");
				
				out.println(" e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println(" '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"\" maxlength=\"100\" ></TD>'+");
			  out.println(" '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"\" maxlength=\"100\">'+");
				out.println(" '<td WIDTH=\"15%\">'+ ");
			  out.println(" '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println(" '<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			  out.println(" '<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
				out.println(" '<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println(" '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println(" '</SELECT></td>'+");
			  out.println(" '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"\" maxlength=\"50\" >'+");
			  out.println(" '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"\" maxlength=\"25\" >'+");
				out.println(" '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"\" maxlength=\"25\">'+");
				out.println(" '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value=\"\" maxlength=\"4\">'+");
				out.println(" '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"\" maxlength=\"25\">'+");
			  out.println(" '<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			  out.println(" '</td></tr></table>';");


        out.println("continue;");
				out.println("}");
				
				out.println(" if( array_type_c[j] ==\"VEHICLE LOAN\" ){ ");
				out.println("  ");
				
				out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute_c[j]+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person_c[j]+' maxlength=\"100\">'+");
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\" SELECTED >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" >House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value='+array_equip_c[j]+' maxlength=\"50\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount_c[j]+' maxlength=\"25\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value='+array_rental[j]+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months_c[j]+' maxlength=\"4\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount_c[j]+' maxlength=\"25\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				
				out.println(" }");
				
				out.println(" else if( array_type_c[j] ==\"HOUSE LOAN\" ){ ");
				out.println("   ");
				out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute_c[j]+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person_c[j]+' maxlength=\"100\">'+");
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\">Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value='+array_equip_c[j]+' maxlength=\"50\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount_c[j]+' maxlength=\"25\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value='+array_rental[j]+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months_c[j]+' maxlength=\"4\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount_c[j]+' maxlength=\"25\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println(" }");
				
				out.println(" else if( array_type_c[j] ==\"PERSONAL LOAN\" ){ ");
				out.println("  ");
				out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute_c[j]+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person_c[j]+' maxlength=\"100\">'+");
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" >House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" SELECTED >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value='+array_equip_c[j]+' maxlength=\"50\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount_c[j]+' maxlength=\"25\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value='+array_rental[j]+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months_c[j]+' maxlength=\"4\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount_c[j]+' maxlength=\"25\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println(" }");
				
				out.println(" else if( array_type_c[j] ==\"CREDIT CARD\" ){ ");
				out.println("  ");
				out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value='+array_institute_c[j]+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value='+array_contact_person_c[j]+' maxlength=\"100\">'+");
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" >House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\">Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" SELECTED >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value='+array_equip_c[j]+' maxlength=\"50\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value='+array_app_amount_c[j]+' maxlength=\"25\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value='+array_rental[j]+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+j+' value='+array_months_c[j]+' maxlength=\"4\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value='+array_bal_amount_c[j]+' maxlength=\"25\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("  }");

			  out.println("}");
			  out.println("}");
			  
				
			out.println("function del_row_family(rowNo){"); 
			//out.println("alert(' row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_family;i++){");
			
			out.println("m_mem=\"TXT_MEMBER\"+i");
			out.println("m_name=\"TXT_NAME_F\"+i");
			out.println("m_add=\"TXT_ADDRESS1_F\"+i");
			out.println("m_age=\"TXT_AGE_F\"+count");
			out.println("m_telno=\"TXT_TELEPHONE_NO_F\"+i");
			out.println("m_mobile=\"TXT_MOBILE_NO_F\"+i");

			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('member type - :  ' +document.Form1.elements[m_mem].value)");			
			out.println("array_member_f[j]=document.Form1.elements[m_mem].value;");
		  out.println("array_name_f[j]=document.Form1.elements[m_name].value;");
			out.println("array_address_f[j]=document.Form1.elements[m_add].value;");
			out.println("array_age_f[j]=document.Form1.elements[m_age].value;");
			out.println("array_telno_f[j]=document.Form1.elements[m_telno].value;");
			out.println("array_moble_f[j]=document.Form1.elements[m_mobile].value;");
		
			out.println("j=j+1;");
			out.println("}");
		
			out.println("lineno_family = lineno_family-1;");
			out.println("arr_size_family = lineno_family;");
		  out.println("write_data_family(arr_size_family);");
			out.println("}");

				out.println("function write_data_family(size){");
				//out.println("alert('arr_size_guarantor '+size);");
				out.println("e_txt_family_members.innerHTML=\"\";");
        //out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
				
			  out.println("if(array_name_f[j]==\"\" && array_address_f[j]==\"\" && array_age_f[j]==\"\" && array_telno_f[j]==\"\" && array_moble_f[j]==\"\"){");
				
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value=\"\" maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"\" maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value=\"\" maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"\" maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"\" maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");

        out.println("continue;");
				out.println("}");
				
				out.println("if(array_member_f[j]==\"MOTHER\"){");
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value='+array_name_f[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value='+array_address_f[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value='+array_age_f[j]+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value='+array_telno_f[j]+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value='+array_moble_f[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				
				out.println("else if(array_member_f[j]==\"FATHER\"){");
				
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" SELECTED>Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value='+array_name_f[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value='+array_address_f[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value='+array_age_f[j]+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value='+array_telno_f[j]+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value='+array_moble_f[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				
				out.println("else if(array_member_f[j]==\"BROTHER\"){");
				
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" SELECTED>Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value='+array_name_f[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value='+array_address_f[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value='+array_age_f[j]+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value='+array_telno_f[j]+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value='+array_moble_f[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				
				out.println("else if(array_member_f[j]==\"SISTER\"){");
				
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" SELECTED>Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value='+array_name_f[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value='+array_address_f[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value='+array_age_f[j]+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value='+array_telno_f[j]+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value='+array_moble_f[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");
				
				out.println("else if(array_member_f[j]==\"SPOUSE\"){");
				
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" SELECTED >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+j+' value='+array_name_f[j]+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value='+array_address_f[j]+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+j+' value='+array_age_f[j]+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value='+array_telno_f[j]+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value='+array_moble_f[j]+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			  out.println("'</td></tr></table>';");
				out.println("}");

			  out.println("}");
			  out.println("}");

				
			
			  out.println("function add_row_emp(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno!=0){"); 
				out.println("count=lineno-1;");
				out.println("m_organization=\"TXT_ORGANIZATION\"+count");
			  out.println("m_tel_no=\"TXT_TEL_NO\"+count");
			  out.println("m_from_date=\"TXT_FROM_DATE\"+count");
				out.println("m_to_date=\"TXT_TO_DATE\"+count");
				out.println("m_designation=\"TXT_DESIGNATION2\"+count");
			
				out.println("if(document.Form1.elements[m_organization].value==\"\") {");
				out.println("alert('Organization Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_tel_no].value==\"\") {");
				out.println("alert('Telephone Number Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_from_date].value==\"\") {");
				out.println("alert('From Date Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				
				out.println("else{");
				out.println("b_count=0;");
				out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
				
				
			  
				out.println("}");
				
					
				out.println("if(b_flag==0){");
				out.println(" alert('lineno'+lineno);");
				out.println("e_mode_emp.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+lineno+' maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FROM_DATE'+lineno+' maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TO_DATE'+lineno+' maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION2'+lineno+' maxlength=\"20\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
      	out.println("}");
				out.println("add_button();");
		    out.println("}");
				
							  
								
				out.println("function add_row_ba(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_ba!=0){"); 
				out.println("count=lineno_ba-1;");
				out.println("m_cat_type=\"TXT_CAT_TYPE_CODE_BA\"+count");
				out.println("m_activity=\"TXT_ACTIVITY_BA\"+count");

				out.println("if(document.Form1.elements[m_activity].value==\"\") {");
				out.println("alert('Activity Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
				
				
			  
				out.println("}");
				
					
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_CAT_TYPE_CODE_BA'+lineno_ba+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			  out.println("'<OPTION value=\"OTHER\" >Other </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"50%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITY_BA'+lineno_ba+' maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+lineno_ba+' value=\"Delete\" onClick=\"del_row_ba('+lineno_ba+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_ba=lineno_ba+1;");
				out.println("arr_size_ba=arr_size_ba+1;");
      	out.println("}");
				out.println("add_button_ba();");
		    out.println("}");
				
				//To Add Bank Row
				out.println("function add_row_bank(type){"); 
				//out.println("alert('type '+type);");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_bank!=0){"); 
				out.println("count=lineno_bank-1;");
				out.println("m_bank=\"TXT_BANK_CODE\"+count");
			  out.println("m_branch=\"TXT_BRANCH_CODE\"+count");
			  out.println("m_accno=\"TXT_ACCOUNT_NO\"+count");
				out.println("m_reference=\"TXT_REFERENCE\"+count");
				out.println("m_telno=\"TXT_TEL_NO2\"+count");
				out.println("m_faxno=\"TXT_FAX_NO\"+count");
				out.println("m_relationship=\"TXT_RELATIONSHIP2\"+count");
			
				out.println("if(document.Form1.elements[m_bank].value==\"\") {");
				out.println("alert('Banker Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
				out.println("alert('Banch Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_accno].value==\"\") {");
				out.println("alert('Account No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_reference].value==\"\") {");
				out.println("alert('Referenc Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
				out.println("alert('Tel No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_faxno].value==\"\") {");
				out.println("alert('Fax No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else{");
				out.println("b_count=0;");
				out.println("tmp_bank=document.Form1.elements[m_bank].value;");
				
				//out.println("for(var i=0;i<lineno-1;i++){");
				//out.println("    m_tmp_bank=\"TXT_BANK_CODE\"+i");
				
				//out.println("   if(lineno>=2){");
				//out.println("     if(document.Form1.elements[m_tmp_bank].value==tmp_bank){");
				//out.println("     alert('Contact Organization Can not Be Duplicated')");
				//out.println("     b_flag=1;");
				//out.println("     b_count=1};");
				
				//out.println("    if(b_count==1){");
				//out.println("    break;}");
				
				//out.println("  } ");
				
			  //out.println("}");
				
			  out.println("}");
				
				
			  
				out.println("}");
				
				out.println("if(type=='I'){");	
				out.println("  if(b_flag==0){");
				//out.println(" alert('lineno_bank '+lineno_bank);");
				out.println("  e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("  '<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+lineno_bank+' value=\"?\" onClick=\"help_button_bank('+lineno_bank+')\"></td>'+");
		    out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+lineno_bank+' maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("  '<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+lineno_bank+' value=\"?\" onClick=\"help_button_branch('+lineno_bank+')\"></td>'+");
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+lineno_bank+' maxlength=\"20\" size=\"20\">'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+lineno_bank+' maxlength=\"20\" size=\"20\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+lineno_bank+' maxlength=\"10\" size=\"10\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+lineno_bank+' maxlength=\"10\" size=\"10\">'+");
				out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+lineno_bank+' maxlength=\"4\" size=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+lineno_bank+' value=\"Delete\" onClick=del_row_bank(\"'+lineno_bank+'\",\"'+type+'\")>'+");
			  out.println("  '</td></tr></table>';");
      
				out.println("  lineno_bank=lineno_bank+1;");
				out.println("  arr_size_bank = arr_size_bank+1;");
      	out.println("  }");
				out.println("  add_button_bank(type);");
				
				out.println("  }");
				out.println(" else {");
				out.println(" if(b_flag==0){");
				//out.println(" alert('lineno_bank '+lineno_bank);");
				out.println("  e_txt_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("  '<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+lineno_bank+' value=\"?\" onClick=\"help_button_bank('+lineno_bank+')\"></td>'+");
		    out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE'+lineno_bank+' maxlength=\"10\" size=\"10\"></TD>'+");
				out.println("  '<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+lineno_bank+' value=\"?\" onClick=\"help_button_branch('+lineno_bank+')\"></td>'+");
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+lineno_bank+' maxlength=\"20\" size=\"20\">'+");
			  out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+lineno_bank+' maxlength=\"20\" size=\"20\">'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO2'+lineno_bank+' maxlength=\"10\" size=\"10\">'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO'+lineno_bank+' maxlength=\"10\" size=\"10\">'+");
				out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP2'+lineno_bank+' maxlength=\"4\" size=\"4\">'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+lineno_bank+' value=\"Delete\" onClick=del_row_bank(\"'+lineno_bank+'\",\"'+type+'\")>'+");
			  out.println("  '</td></tr></table>';");
      
				out.println("  lineno_bank=lineno_bank+1;");
				out.println("  arr_size_bank = arr_size_bank+1;");
      	out.println(" }");
				out.println(" add_button_bank(type);");
				
				out.println(" }");
				//out.println(" alert('Add func array size bank : '+arr_size_bank);");
				out.println(" }");
		    //out.println("}");
				
				//Add row Credit
				 out.println("function add_row_credit(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_credit!=0){"); 
				out.println("count=lineno_credit-1;");
				out.println("m_type_of_facility=\"TXT_TYPE_OF_FACILITY\"+count");
			  out.println("m_institute=\"TXT_INSTITUTION\"+count");
			  out.println("m_contact_person=\"TXT_CONTACT_PERSON\"+count");
				out.println("m_contract_no=\"TXT_CONTRACT_NO\"+count");
				out.println("m_security=\"TXT_SECURITY\"+count");
				out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+count");
				out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+count");
				out.println("m_months=\"TXT_MONTHS\"+count");
			
				out.println("if(document.Form1.elements[m_type_of_facility].value==\"\") {");
				out.println("alert('Type Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_institute].value==\"\") {");
				out.println("alert('Institution Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_person].value==\"\") {");
				out.println("alert('Contact Person Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				
				out.println("else if(document.Form1.elements[m_contract_no].value==\"\") {");
				out.println("alert('Contract No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_security].value==\"\") {");
				out.println("alert('Security  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_app_amount].value==\"\") {");
				out.println("alert('Approved Amount  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_bal_amount].value==\"\") {");
				out.println("alert(' Balance Amount  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_months].value==\"\") {");
				out.println("alert(' Months  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
			  
				out.println("}");
		
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_credit.innerHTML +='<table  border=\"1\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+lineno_credit+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+lineno_credit+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+lineno_credit+' maxlength=\"100\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTRACT_NO'+lineno_credit+' maxlength=\"20\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_SECURITY'+lineno_credit+' maxlength=\"100\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_APPROVED_AMOUNT'+lineno_credit+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_BALANCE_AMOUNT'+lineno_credit+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MONTHS'+lineno_credit+' maxlength=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+lineno_credit+' value=\"Delete\" onClick=\"del_row_credit('+lineno_credit+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_credit = lineno_credit+1;");
				out.println("arr_size_credit = arr_size_credit+1;");
      	out.println("}");
				out.println("add_button_credit();");
		    out.println("}");
				
				// To Add Non-related referees Row
				out.println("function add_row_nonrelated_ref(type){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_nonrelated!=0){"); 
				out.println("count=lineno_nonrelated-1;");
				
				out.println("m_ref_name=\"TXT_NAME_REFEREE\"+count");
			  out.println("m_relationship=\"TXT_RELATIONSHIP3\"+count");
			  out.println("m_period=\"TXT_PERIOD\"+count");
				out.println("m_desig=\"TXT_DESIGNATION3\"+count");
				out.println("m_tel_home=\"TXT_HOME_TEL_NO\"+count");
				out.println("m_tel_office=\"TXT_OFFICE_TEL_NO\"+count");
				out.println("m_mobile=\"TXT_MOBILE_NO\"+count");
			
				out.println("if(document.Form1.elements[m_ref_name].value==\"\") {");
				out.println("alert('Referee Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_relationship].value==\"\") {");
				out.println("alert('Relationship Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_desig].value==\"\") {");
				out.println("alert(' Designation Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_tel_home].value==\"\") {");
				out.println("alert('Home Tel NO. Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_bank=document.Form1.elements[m_bank].value;");
				
				//out.println("for(var i=0;i<lineno-1;i++){");
				//out.println("    m_tmp_bank=\"TXT_BANK_CODE\"+i");
				
				//out.println("   if(lineno>=2){");
				//out.println("     if(document.Form1.elements[m_tmp_bank].value==tmp_bank){");
				//out.println("     alert('Contact Organization Can not Be Duplicated')");
				//out.println("     b_flag=1;");
				//out.println("     b_count=1};");
				
				//out.println("    if(b_count==1){");
				//out.println("    break;}");
				
				//out.println("  } ");
				
			  //out.println("}");
				
			  out.println("}");
				
				
			  
				out.println("}");
				out.println(" if(b_flag==0){");
				out.println("  if(type=='I'){");
			  //out.println(" alert('lineno_bank '+lineno_bank);");
				out.println("  e_txt_nonrelated.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+lineno_nonrelated+' maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+lineno_nonrelated+' maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+lineno_nonrelated+' maxlength=\"3\" size=\"10\" >'+");
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+lineno_nonrelated+' maxlength=\"50\"  size=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+lineno_nonrelated+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+lineno_nonrelated+'\",\"'+type+'\")>'+");
			  out.println("  '</td></tr></table>';");
      	out.println("  }");
				out.println("  else{");
				out.println("  e_txt_nonrelated_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_REFEREE'+lineno_nonrelated+' maxlength=\"100\" size=\"20\" ></TD>'+");
		    out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+lineno_nonrelated+' maxlength=\"50\" size=\"10\" ></TD>'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+lineno_nonrelated+' maxlength=\"3\" size=\"10\" >'+");
			  out.println("  '<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_DESIGNATION3'+lineno_nonrelated+' maxlength=\"50\"  size=\"20\" >'+");
			  out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_HOME_TEL_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICE_TEL_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
				out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO'+lineno_nonrelated+' maxlength=\"10\" size=\"10\" >'+");
			  out.println("  '<input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+lineno_nonrelated+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+lineno_nonrelated+'\",\"'+type+'\")>'+");
			  out.println("  '</td></tr></table>';");
				out.println("  }");
				out.println("  lineno_nonrelated = lineno_nonrelated+1;");
				out.println("  arr_size_nonrelated = arr_size_nonrelated+1;");
      	out.println(" }");
				//out.println(" alert('Add func  arry size '+arr_size_nonrelated);");
				out.println(" add_button_nonrelated(type);");
		    out.println("}");
				
				//To add a Proposed guarantors row
				out.println("function add_row_guarantor(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_guarantor!=0){"); 
				out.println("count=lineno_guarantor-1;");
				out.println("m_guarantor=\"TXT_GUARANTOR_CODE\"+count");
			  out.println("m_relationship=\"TXT_RELATIONSHIP_G\"+count");
			  out.println("m_period=\"TXT_PERIOD_G\"+count");
				out.println("m_telno=\"TXT_TEL_NO_G\"+count");

				out.println("if(document.Form1.elements[m_guarantor].value==\"\") {");
				out.println("alert('Guarantor Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_relationship].value==\"\") {");
				out.println("alert('Relationship Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_period].value==\"\") {");
				out.println("alert('Period Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
				out.println("alert('Telno Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				
			  out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_bank = document.Form1.elements[m_bank].value;");
				
				//out.println("for(var i=0;i<lineno-1;i++){");
				//out.println("    m_tmp_bank=\"TXT_BANK_CODE\"+i");
				
				//out.println("   if(lineno>=2){");
				//out.println("     if(document.Form1.elements[m_tmp_bank].value==tmp_bank){");
				//out.println("     alert('Contact Organization Can not Be Duplicated')");
				//out.println("     b_flag=1;");
				//out.println("     b_count=1};");
				
				//out.println("    if(b_count==1){");
				//out.println("    break;}");
				
				//out.println("  } ");
				
			  //out.println("}");
				
			  out.println("}");
				
				
			  
				out.println("}");
				
					
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno_bank '+lineno_bank);");
				out.println("e_txt_guarantor.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_GUARANTOR_CODE'+lineno_guarantor+' maxlength=\"10\" size=\"10\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_GUARANTOR_CODE'+lineno_guarantor+' value=\"?\" onClick=\"help_button_guarantor('+lineno_bank+')\"></TD>'+");
				//out.println("'<TD WIDTH=\"5%\"></td>'+");
		    out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_G'+lineno_guarantor+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD_G'+lineno_guarantor+' maxlength=\"2\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_G'+lineno_guarantor+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_GUARANTOR_DEL'+lineno_guarantor+' value=\"Delete\" onClick=\"del_row_guarantor('+lineno_guarantor+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_guarantor = lineno_guarantor+1;");
				out.println("arr_size_guarantor = arr_size_guarantor+1;");
				//out.println(" alert('arr_size = '+arr_size_guarantor); ");
      	out.println("}");
				out.println("add_button_guarantor();");
		    out.println("}");



			  //Corporate 
			  out.println("function add_row_company_dir(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_company_dir!=0){"); 
				out.println("count=lineno_company_dir-1;");
				out.println("m_name=\"TXT_NAME_DIR\"+count");
			  out.println("m_nic_noo=\"TXT_NIC_NO_DIR\"+count");
			  out.println("m_stake=\"TXT_STAKE\"+count");
				out.println("m_no_shares=\"TXT_NO_OF_SHARES\"+count");
				out.println("m_value=\"TXT_VALUE\"+count");
				out.println("m_position=\"TXT_POSITION\"+count");
			
				out.println("if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Director Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_nic_noo].value==\"\") {");
				out.println("alert('NIC Number Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_stake].value==\"\") {");
				out.println("alert('Stake Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_no_shares].value==\"\") {");
				out.println("alert('No of Shares Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_value].value==\"\") {");
				out.println("alert('Value Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_position].value==\"\") {");
				out.println("alert('Position Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

	
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
				
				
			  
				out.println("}");
				
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_DIR'+lineno_company_dir+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_NO_DIR'+lineno_company_dir+' maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE'+lineno_company_dir+' maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_NO_OF_SHARES'+lineno_company_dir+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE'+lineno_company_dir+' maxlength=\"25\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_POSITION'+lineno_company_dir+' maxlength=\"50\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+lineno_company_dir+' value=\"Delete\" onClick=\"del_row_company_dir('+lineno_company_dir+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_company_dir = lineno_company_dir+1;");
				out.println("arr_size_company = arr_size_company+1;");
      	out.println("}");
				out.println("add_button_company_dir();");
		    out.println("}");
				
				
				out.println("function add_row_family(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_family!=0){"); 
				out.println("count=lineno_family-1;");
				out.println("m_mem=\"TXT_MEMBER\"+count");
			  out.println("m_name=\"TXT_NAME_F\"+count");
			  out.println("m_add=\"TXT_ADDRESS1_F\"+count");
				out.println("m_age=\"TXT_AGE_F\"+count");
				out.println("m_telno=\"TXT_TELEPHONE_NO_F\"+count");
				out.println("m_mobile=\"TXT_MOBILE_NO_F\"+count");
			
				out.println("if(document.Form1.elements[m_mem].value==\"\") {");
				out.println("alert('Director Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_add].value==\"\") {");
				out.println("alert('Address Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_age].value==\"\") {");
				out.println("alert('Age Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
	
				out.println("else{");
				out.println("b_count=0;");			
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");		
			  
				out.println("}");
				
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<td WIDTH=\"20%\">'+ ");
			  out.println("'<select name=TXT_MEMBER'+lineno_family+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			  out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
				out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			  out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
				out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			  out.println("'</SELECT></td>'+");
			  //out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_F'+lineno_family+' maxlength=\"50\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1_F'+lineno_family+' maxlength=\"20\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_AGE_F'+lineno_family+' maxlength=\"3\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TELEPHONE_NO_F'+lineno_family+' maxlength=\"10\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_MOBILE_NO_F'+lineno_family+' maxlength=\"10\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+lineno_family+' value=\"Delete\" onClick=\"del_row_family('+lineno_family+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_family = lineno_family+1;");
				out.println("arr_size_family = arr_size_family+1;");
      	out.println("}");
				out.println("add_button_family();");
		    out.println("}");

			  out.println("function add_row_subsidiaries(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_subsidiaries!=0){"); 
				out.println("count=lineno_subsidiaries-1;");
				out.println("m_name=\"TXT_NAME_SUB\"+count");
			  out.println("m_stake=\"TXT_STAKE_SUB\"+count");
			  out.println("m_value=\"TXT_VALUE_SUB\"+count");
				out.println("m_telno=\"TXT_TEL_NO_SUB\"+count");
				out.println("m_officer=\"TXT_OFFICER_SUB\"+count");
				out.println("m_activities=\"TXT_ACTIVITIES_SUB\"+count");
			
				out.println("if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Subsidiary Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_stake].value==\"\") {");
				out.println("alert('Stake Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_value].value==\"\") {");
				out.println("alert('Value Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_activities].value==\"\") {");
				out.println("alert('Business Activities Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				/*out.println("else if(document.Form1.elements[m_value].value==\"\") {");
				out.println("alert('Value Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_position].value==\"\") {");
				out.println("alert('Position Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");*/

	
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("    if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
				
				
			  
				out.println("}");
				
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_subsidiaries.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME_SUB'+lineno_subsidiaries+' value=\"\" maxlength=\"100\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_STAKE_SUB'+lineno_subsidiaries+' value=\"\"  maxlength=\"5\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_VALUE_SUB'+lineno_subsidiaries+'  value=\"\" maxlength=\"25\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_SUB'+lineno_subsidiaries+'  value=\"\" maxlength=\"10\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFICER_SUB'+lineno_subsidiaries+' value=\"\" maxlength=\"20\" size=\"20\">'+");
				out.println("'<TD WIDTH=\"*%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACTIVITIES_SUB'+lineno_subsidiaries+' value=\"\"  maxlength=\"100\" size=\"20\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+lineno_subsidiaries+' value=\"Delete\" onClick=\"del_row_subsidiaries('+lineno_subsidiaries+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_subsidiaries = lineno_subsidiaries+1;");
				out.println("arr_size_subsidiaries = arr_size_subsidiaries+1;");
      	out.println("}");
				out.println("add_button_subsidiaries();");
		    out.println("}");
       
		   	
					
				out.println("function add_row_customer(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_customer!=0){"); 
				out.println("count=lineno_customer-1;");
				out.println("m_name=\"TXT_CUSTOMER_NAME\"+count");
				out.println("m_type=\"TXT_TYPE_C\"+count");
			  out.println("m_add=\"TXT_ADDRESS_CUS\"+count");
			  out.println("m_relation=\"TXT_RELATIONSHIP_CUS\"+count");
				out.println("m_con_person=\"TXT_CONTACT_PERSON_CUS\"+count");
				out.println("m_telno=\"TXT_TEL_NO_CUS\"+count");
			
				out.println("if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Customer Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_add].value==\"\") {");
				out.println("alert('Address Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_relation].value==\"\") {");
				out.println("alert('Relation Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_con_person].value==\"\") {");
				out.println("alert('Contact Person Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
				out.println("alert('Tel No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				/*out.println("else if(document.Form1.elements[m_value].value==\"\") {");
				out.println("alert('Value Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_position].value==\"\") {");
				out.println("alert('Position Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");*/

	
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("    if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
				
				
			  
				out.println("}");
				
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_CUSTOMER_NAME'+lineno_customer+' value=\"\" maxlength=\"100\" size=\"50\"></TD>'+");
				out.println("'<td WIDTH=\"10%\">'+ ");
			  out.println("'<select name=TXT_TYPE_C'+lineno_customer+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"CUSTOMER\" >Customer </option>'+");
			  out.println("'<OPTION value=\"SUPPLIER\" SELECTED>Supplier </option>'+");
			  out.println("'</SELECT></td>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_CUS'+lineno_customer+' value=\"\"  maxlength=\"100\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+lineno_customer+'  value=\"\" maxlength=\"5\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+lineno_customer+'  value=\"\" maxlength=\"100\" size=\"10\">'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_CUS'+lineno_customer+' value=\"\" maxlength=\"20\" size=\"10\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+lineno_customer+' value=\"Delete\" onClick=\"del_row_customer('+lineno_customer+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_customer = lineno_customer+1;");
				out.println("arr_size_customer = arr_size_customer+1;");
      	out.println("}");
				out.println("add_button_customer();");
		    out.println("}");
        
				//Add row Credit Corporate
				 out.println("function add_row_credit_c(){"); 
				//out.println("alert('ok');");
			  out.println("var b_flag=0;");
			  out.println("if(lineno_credit_c!=0){"); 
				out.println("count=lineno_credit_c-1;");
				
				out.println("m_type_of_facility=\"TXT_TYPE_OF_FACILITY\"+count");
			  out.println("m_institute=\"TXT_INSTITUTION\"+count");
			  out.println("m_contact_person=\"TXT_CONTACT_PERSON\"+count");
				out.println("m_equipment=\"TXT_EQUIPMENT\"+count");
				//out.println("m_security=\"TXT_SECURITY\"+count");
				out.println("m_rental=\"TXT_MONTHLY_RENTAL\"+count");
				out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+count");
				out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+count");
				out.println("m_months=\"TXT_MONTHS\"+count");

				
				out.println("if(document.Form1.elements[m_institute].value==\"\") {");
				out.println("alert('Institution Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_person].value==\"\") {");
				out.println("alert('Contact Person Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
					
				/*out.println("if(document.Form1.elements[m_type_of_facility].value==\"\") {");
				out.println("alert('Type Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");*/
				
				out.println("else if(document.Form1.elements[m_equipment].value==\"\") {");
				out.println("alert('Equipment No Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				
				out.println("else if(document.Form1.elements[m_app_amount].value==\"\") {");
				out.println("alert('Approved Amount  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("if(document.Form1.elements[m_rental].value==\"\") {");
				out.println("alert('Monthly Rental Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_months].value==\"\") {");
				out.println("alert(' Period  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_bal_amount].value==\"\") {");
				out.println("alert(' Balance Amount  Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");

				
				out.println("else{");
				out.println("b_count=0;");
				//out.println("tmp_org=document.Form1.elements[m_organization].value;");
				
				/*out.println("for(var i=0;i<lineno-1;i++){");
				out.println("    m_tmp_org=\"TXT_ORGANIZATION\"+i");
				
				out.println("   if(lineno>=2){");
				out.println("     if(document.Form1.elements[m_tmp_org].value==tmp_org){");
				out.println("     alert('Contact Organization Can not Be Duplicated')");
				out.println("     b_flag=1;");
				out.println("     b_count=1};");
				
				out.println("    if(b_count==1){");
				out.println("    break;}");
				
				out.println("  } ");
				
			  out.println("}"); */
				
			  out.println("}");
			  
				out.println("}");
		
				out.println("if(b_flag==0){");
				//out.println(" alert('lineno'+lineno);");
				out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			  //out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+lineno_credit_c+' maxlength=\"100\" ></TD>'+");
			  out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+lineno_credit_c+' maxlength=\"100\">'+");
				out.println("'<td WIDTH=\"15%\">'+ ");
			  out.println("'<select name=TXT_TYPE_OF_FACILITY'+lineno_credit_c+' class=\"txt_input\" >'+");
			  out.println("'<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			  out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
				out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			  out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			  out.println("'</SELECT></td>'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+lineno_credit_c+' maxlength=\"50\" >'+");
			  out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_APPROVED_AMOUNT'+lineno_credit_c+' maxlength=\"25\" >'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHLY_RENTAL'+lineno_credit_c+' maxlength=\"25\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MONTHS'+lineno_credit_c+' maxlength=\"4\">'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BALANCE_AMOUNT'+lineno_credit_c+' maxlength=\"4\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+lineno_credit_c+' value=\"Delete\" onClick=\"del_row_credit_c('+lineno_credit_c+')\">'+");
			  out.println("'</td></tr></table>';");
      
				out.println("lineno_credit_c = lineno_credit_c+1;");
				out.println("arr_size_credit_c = arr_size_credit_c+1;");
      	out.println("}");
				out.println("add_button_credit_c();");
		    out.println("}");

         
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FULL_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FULL_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_CATEGORY.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CATEGORY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CAT_TYPE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CAT_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_client_creation';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_client\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Client - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CAT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_CERTIFICATE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_KEY_DECISION_MAKER.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION.disabled=true;"); 
			out.println("document.Form1.TXT_DIRECT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_FOR_PAYMENT.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION_PAYMENT.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_F_CONTACT_PERSON.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_F_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_SURNAME.disabled=true;"); 
			out.println("document.Form1.TXT_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_OTHER_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.disabled=true;"); 
			out.println("document.Form1.TXT_PASSPORT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.disabled=true;"); 
			out.println("document.Form1.TXT_NATIONALITY.disabled=true;"); 
			out.println("document.Form1.TXT_GENDER.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
			out.println("		help_value_assign_bank(document.Form1.hid_lineno.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_branch(document.Form1.hid_lineno.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
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
			
			out.println("function help_button_bank(lineno) {"); 
			out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
			out.println("    document.Form1.hid_lineno.value=lineno;"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_bank].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_button_branch(lineno) {"); 
			out.println("    m_branch=\"TXT_BRANCH_CODE\"+lineno");
			out.println("    document.Form1.hid_lineno.value=lineno;"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_branch].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','8');"); 
			out.println("}");
			
			out.println("function help_button_guarantor(lineno) {"); 
			out.println("    m_guarantor=\"TXT_GUARANTOR_CODE\"+lineno");
			out.println("    document.Form1.hid_lineno.value=lineno;"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_guarantor].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			/*out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_BUSINESS_SUB_SECTOR_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BUSINESS_SUB_SECTOR.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");*/ 
			out.println(""); 

			out.println("function help_value_assign_bank(lineno) {"); 
			out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
			out.println("    document.Form1.elements[m_bank].value=oBj.valout[2];"); 
			out.println("}"); 

			/*out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println("");*/ 

			out.println("function help_value_assign_branch(lineno) {"); 
			out.println("    m_branch=\"TXT_BRANCH_CODE\"+lineno");
			out.println("    document.Form1.elements[m_branch].value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CAT_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CAT_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_REGISTERED_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REGISTERED_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_REFERENCE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_EMAIL.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_DESIGNATION.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=oBj.valout[26];"); 
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=oBj.valout[27];"); 
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=oBj.valout[28];"); 
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=oBj.valout[29];"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[30];"); 
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=oBj.valout[31];"); 
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=oBj.valout[32];"); 
			out.println("    document.Form1.TXT_F_TEL_NO.value=oBj.valout[33];"); 
			out.println("    document.Form1.TXT_F_FAX_NO.value=oBj.valout[34];"); 
			out.println("    document.Form1.TXT_F_EMAIL.value=oBj.valout[35];"); 
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=oBj.valout[36];"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=oBj.valout[37];"); 
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[38];"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=oBj.valout[39];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[40];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[41];"); 
			out.println("    document.Form1.TXT_SURNAME.value=oBj.valout[42];"); 
			out.println("    document.Form1.TXT_INITIALS.value=oBj.valout[43];"); 
			out.println("    document.Form1.TXT_OTHER_NAME.value=oBj.valout[44];"); 
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=oBj.valout[45];"); 
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=oBj.valout[46];"); 
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=oBj.valout[47];"); 
			out.println("    document.Form1.TXT_PASSPORT_NO.value=oBj.valout[48];"); 
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=oBj.valout[49];"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH.value=oBj.valout[50];"); 
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[51];"); 
			out.println("    document.Form1.TXT_GENDER.value=oBj.valout[52];"); 
			out.println("}"); 
			
			
			out.println("function select_content(obj) {");
			//out.println(" alert('obj'+obj);");
			out.println("   if(obj=='I'){");
			out.println("	 add_individual();");
			out.println("   }");
			out.println("   else  if(obj=='C') {");
			out.println("	 add_corporate();");	
			out.println("   }");	
			out.println("}");
			
			out.println("function add_corporate() {");
			out.println("m_writedata='<table>'+");
			//out.println("'<tr >'+");
		  out.println("'<tr>'+");
			out.println("'<td width=\"30%\" ><B> A.DETAILS OF APPLICANT </B></td>'+"); 
			out.println("'</tr>'+");

	
			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FULL_NAME\"  class=\"div_input\">Full Name </DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FULL_NAME_C\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");  
			
			/*out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Other Names </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");*/
			out.println("'<tr></tr>'+");
			out.println("'<tr></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			//Contact Details  start
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" ><B> Address </B></td>'+"); 
			out.println("'<td width=\"40%\"> </td>'+");
			out.println("'<td width=\"30%\" ><B> Contact Details </B></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Registered Office </td>'+"); 
			out.println("'<td width=\"40%\">'+");
			out.println("'<select name=\"TXT_REG_OFFICE\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td width=\"30%\" >Key Decision Maker </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_KEY_DECISION_MAKER\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Designation </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION1\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Telephone Direct  </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >    </td>'+"); 
			out.println("'<td width=\"40%\" >    </td>'+"); 
			out.println("'<td width=\"30%\" >Contact For Payments </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");


			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Correspondence Office </td>'+"); 
			out.println("'<td width=\"40%\">'+");
			out.println("'<select name=\"TXT_CORRESPONDENCE_OFF\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td width=\"30%\" >Designation </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION2\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Telephone General </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_TEL_NO\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Fax General  </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_FAX\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >    </td>'+"); 
			out.println("'<td width=\"40%\" >    </td>'+"); 
			out.println("'<td width=\"30%\" >Email - General </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Factory</td>'+"); 
			out.println("'<td width=\"40%\">'+");
			out.println("'<select name=\"TXT_FACTORY_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td width=\"30%\" >Contact Person</td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_CONTACT_PERSON\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Telephone </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_TEL_NO\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ></td>'+"); 
			out.println("'<td width=\"40%\" ></td>'+"); 
			out.println("'<td width=\"30%\" >Fax   </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >    </td>'+"); 
			out.println("'<td width=\"40%\" >    </td>'+"); 
			out.println("'<td width=\"30%\" >Email  </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 			
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Legal Status of Business </td>'+"); 
			out.println("'<td width=\"40%\">'+");
			out.println("'<select name=\"TXT_LEGAL_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"PVT\" SELECTED >Private Limited Liability </option>'+");
			out.println("'<OPTION value=\"PART\" >Partnership </option>'+");
			out.println("'<OPTION value=\"PUBLIC\" >Public </option>'+");
			out.println("'<OPTION value=\"SOLE\" >Sole Proprietorship </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Issued Share Capital</td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_ISSUED_SHARE_CAPITAL\" maxlength=\"22\" size=\"22\">Rs.</td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Business Certificate No </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Date Of Incorporation </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION\" maxlength=\"7\" size=\"7\">[DD-MM-YYYY]</td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >VAT Registration No </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Date Of VAT Registraiton</td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" maxlength=\"7\" size=\"7\">[DD-MM-YYYY]</td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");

			out.println("'<tr ></tr>'+");
			
			
			
			//Business Activities start
			/*out.println("'<tr >'+");
			out.println("'<td width=\"30%\" ><B> Business Activities </B></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Nature of the Business </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_NATURE_OF_BUSINESS\" maxlength=\"100\" size=\"100\" >'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Profession </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_PROFESSION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"30%\" >Designation </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_DESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Qualifications </td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_QUALIFICATIONS\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			*/
			
			out.println("'<tr ></tr>'+");
			//out.println("'<tr ></tr>'+");

			out.println("'</table>';  ");
			out.println("e_mode.innerHTML=m_writedata;");
			
			out.println("clear_individual();");
			//############################
			out.println("add_label_company_dir();");
			out.println("header_company_dir();");
			out.println("add_row_company_dir();");
			
      //business activities
			out.println("add_label_ba();");
			out.println("header_ba();");
			out.println("add_row_ba();");

			out.println("add_label_subsidiaries();");
			out.println("header_subsidiaries();");
			out.println("add_row_subsidiaries();");
			//out.println(" lineno=0;");
			//out.println(" arr_size=0;");
			//out.println(" e_mode.innerHTML='';  ");

			out.println("add_label_bank();");
      out.println("header_bank('C');"); 
			out.println("add_row_bank('C');"); 

			out.println("add_label_credit_c();");
			out.println("header_credit_c();");
			out.println("add_row_credit_c();");
			
			out.println("add_label_customer();");
			out.println("header_customer();");
			out.println("add_row_customer();");
			
			out.println("add_label_nonrelated_ref('C');");
			out.println("header_nonrelated_ref('C');");
			out.println("add_row_nonrelated_ref('C');");

			out.println("}");
			
			
			out.println("function clear_individual(){");
			out.println(" e_mode_emp.innerHTML=\"\";");
			out.println(" e_mode2.innerHTML='';  ");
			
			out.println(" lineno_bank=0;");
			out.println(" arr_size_bank=0;");
			out.println("e_header_bank.innerHTML='';  ");
			out.println("e_txt_bank.innerHTML='';  ");
			out.println("e_add_but_bank.innerHTML='';  ");

			out.println(" lineno_credit=0;");
			out.println(" arr_size_credit=0;");
			out.println("e_label_credit.innerHTML='';  ");
			out.println("e_header_credit.innerHTML='';  ");
			out.println("e_txt_credit.innerHTML='';  ");
			out.println("e_add_but_credit.innerHTML='';  ");
			
			out.println(" lineno_nonrelated=0;");
			out.println(" arr_size_nonrelated=0;");
			out.println("e_label_nonrelated.innerHTML='';  ");
			out.println("e_header_nonrelated.innerHTML='';  ");
			out.println("e_txt_nonrelated.innerHTML='';  ");
			out.println("e_add_but_nonrelated.innerHTML='';  ");

			out.println("e_label_income_expense.innerHTML='';  ");
			out.println("e_header_income.innerHTML='';  ");
			out.println("e_other_income.innerHTML='';  ");
			out.println("e_tot_income.innerHTML='';  ");
			out.println("e_data_income.innerHTML='';  ");
			out.println("e_header_expense.innerHTML='';  ");
			out.println("e_other_expense.innerHTML='';  ");
			out.println("e_tot_expense.innerHTML='';  ");
			out.println("e_data_expense.innerHTML='';  ");
			
			out.println(" lineno_family=0;");
			out.println(" arr_size_family=0;");
			out.println("e_label_family_members.innerHTML='';  ");
			out.println("e_header_family_members.innerHTML='';  ");
			out.println("e_txt_family_members.innerHTML='';  ");
			out.println("e_add_but_family_members.innerHTML='';  ");
			out.println("e_row_tot_dep.innerHTML='';  ");
			out.println("}");
			
			
			out.println("function add_individual(){");
			out.println(" assign_help_status('H2'); ");
			out.println(" makeRequest1(); ");
			out.println(" lineno=0;  ");
			out.println("m_writedata='<table>'+");
			//out.println("'<tr >'+");
		  out.println("'<tr>'+");
			out.println("'<td colspan=4><B> A.DETAILS OF APPLICANT </B></td>'+"); 
			out.println("'</tr>'+");
			
		  out.println("'<tr >'+"); 
			out.println("'<td width=\"20%\" >Title </td>'+"); 
			out.println("'<td width=\"30%\" >'+");
			out.println("'<select name=\"TXT_TITLE\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"MR\" SELECTED >Mr </option>'+");
			out.println("'<OPTION value=\"MRS\" >Mrs </option>'+");
			out.println("'<OPTION value=\"MISS\" >Miss </option>'+");
			out.println("'<OPTION value=\"DR\" >Dr </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<td width=\"20%\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr >'+"); 

			out.println("'<td >First Name </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_FIRST_NAME\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >Surname </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_SURNAME\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td >Initials *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_INITIALS\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'</tr>'+"); 
	
			out.println("'<tr >'+"); 

			out.println("'<td ><DIV id=\"DIV_TXT_FULL_NAME\"  class=\"div_input\">Full Name </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_FULL_NAME_I\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");  
			
			out.println("'<tr >'+"); 
			out.println("'<td >Other Names </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr></tr>'+");
			out.println("'<tr></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			//Contact Details  start
			out.println("'<tr >'+");
			out.println("'<td colspan=4><B> Contact Details </B></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"20%\" >Residential Status </td>'+"); 
			out.println("'<td width=\"30%\">'+");
			out.println("'<select name=\"TXT_RESIDENTIAL_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'<OPTION value=\"MORTGAGED\" >Mortgaged </option>'+");
			out.println("'<OPTION value=\"WITH_PARENTS\" >With Parents </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td width=\"20%\" >Telephone Home </td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td >Home Address </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS1_HOME\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td >Telephone Office </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS2_HOME\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td >Fax  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >    </td>'+"); 
			out.println("'<td >    </td>'+"); 
			out.println("'<td >Mobile  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+");

			
			out.println("'<tr >'+"); 
			out.println("'<td >    </td>'+"); 
			out.println("'<td >    </td>'+"); 
			out.println("'<td >Email </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Duration At Years </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Duration At Months </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_MONTHS\" maxlength=\"2\" size=\"2\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			//out.println("'<hr  width=\"100%\">'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Employer </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_NAME\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td >Reference </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_REFERENCE\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Address  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_ADDRESS1\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td >Designation </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_RDESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_ADDRESS2\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td >Telephone </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td >Fax </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_NAME\"  class=\"div_input\">Relative Name </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NAME_REL\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_RELATIONSHIP\"  class=\"div_input\">Relationship *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_RELATIONSHIP\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td ><DIV id=\"DIV_TXT_ADDRESS1\"  class=\"div_input\">Address </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS1_REL\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_HOME_TEL_NO\"  class=\"div_input\">Telephone - Home *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_HOME_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+");
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS2_REL\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td >Telephone - Office *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO_REL\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td >  </td>'+");  
			out.println("'<td >Mobile *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO_REL\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Nic No </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td >Date Of Birth *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_BIRTH\" maxlength=\"7\" size=\"7\"></td>'+"); 
			//out.println("<td width='*%'></td>");
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 

			out.println("'<td >Passport No </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_PASSPORT_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td >Nationality </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NATIONALITY\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >Marital Status </td>'+"); 
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_MARITAL_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"SINGLE\" SELECTED >Single </option>'+");
			out.println("'<OPTION value=\"MARRIED\" >Married </option>'+");
			out.println("'<OPTION value=\"DIVORCED\" >Divorced </option>'+");
			out.println("'<OPTION value=\"WIDOWED\" >Widowed </option>'+");
			out.println("'</SELECT></td>'+");
			
			out.println("'<td >Gender </td>'+"); 
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_GENDER\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"MALE\" SELECTED >Male </option>'+");
			out.println("'<OPTION value=\"FEMALE\" >Female </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'</tr>'+");
			
			//Contact Details  end
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			//Business Activities start
			out.println("'<tr >'+");
			out.println("'<td colspan=4><B> Business Activities </B></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Nature of the Business </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_NATURE_OF_BUSINESS\" maxlength=\"100\" size=\"100\" >'+"); 
			out.println("'<td colspan=2></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Profession </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_PROFESSION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td >Designation </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_DESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td  >Qualifications </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_QUALIFICATIONS\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td colspan=2></td>'+"); 
			out.println("'</tr>'+"); 
			
			//Business Activities end
			
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			//Employment Start
			out.println("'<tr >'+");
			out.println("'<td ><B> Employment Track Record </B></td>'+"); 
			out.println("'</tr>'+");
			//Employment End
			
			out.println("'</table>';  ");
			out.println("alert(m_writedata);e_mode.innerHTML=m_writedata;");

			//To clear corporate divs
			out.println("clear_corporate();");
			//employment
			out.println("header();");
			out.println("add_row_emp();");
			out.println("header_bank('I');");
			out.println("add_row_bank('I');");
			//credit facility
			out.println("add_label_credit();");
			out.println("header_credit();");
			out.println("add_row_credit();");
			
			//referees
			out.println("add_label_nonrelated_ref('I');");
			out.println("header_nonrelated_ref('I');");
			out.println("add_row_nonrelated_ref('I');");
			
			//guarantors
			/*out.println("add_label_guarantor();");
			out.println("header_guarantor();");
			out.println("add_row_guarantor();");*/
			
			//Income/Expense
			out.println("add_label_income_expense();");
			out.println("header_income_expense();");
			
			//family members
			out.println("add_label_family();");
			out.println("header_family();");
			out.println("add_row_family();");
			out.println("add_row_tot_dependents();");
			out.println("}");
			
			out.println(" function clear_corporate(){");
			out.println(" lineno_bank=0;");
			out.println(" arr_size_bank=0;");
			out.println(" arr_size_company=0; ");
			out.println(" lineno_company_dir=0; ");
			out.println(" lineno_subsidiaries=0; ");
			out.println(" arr_size_subsidiaries=0; ");
			out.println(" lineno_customer=0; ");
			out.println(" arr_size_customer=0; ");
			
			out.println(" lineno_credit_c=0;");
			out.println(" arr_size_credit_c=0;");
			out.println("e_label_credit_c.innerHTML='';  ");
			out.println("e_header_credit_c.innerHTML='';  ");
			out.println("e_txt_credit_c.innerHTML='';  ");
			out.println("e_add_but_credit_c.innerHTML='';  ");
			
			out.println(" e_label_company_directors.innerHTML='';");
			out.println(" e_header_company_directors.innerHTML='';");
			out.println(" e_txt_company_directors.innerHTML='';");
			out.println(" e_add_but_company_directors.innerHTML='';");
			out.println(" e_label_subsidiaries.innerHTML='';");
			out.println(" e_header_subsidiaries.innerHTML='';");
			out.println(" e_txt_subsidiaries.innerHTML='';");
			out.println(" e_add_but_subsidiaries.innerHTML='';");
			out.println(" e_label_bank.innerHTML='';");
			out.println(" e_header_bank_c.innerHTML='';");
			out.println(" e_txt_bank_c.innerHTML='';");
			out.println(" e_add_but_bank_c.innerHTML='';");
			out.println(" e_label_customer.innerHTML='';");
			out.println(" e_header_customer.innerHTML='';");
			out.println(" e_txt_customer.innerHTML='';");
			out.println(" e_add_but_customer.innerHTML='';");
			
			out.println(" lineno_nonrelated=0;");
			out.println(" arr_size_nonrelated=0;");
			out.println(" e_label_nonrelated_c.innerHTML='';  ");
			out.println(" e_header_nonrelated_c.innerHTML='';  ");
			out.println(" e_txt_nonrelated_c.innerHTML='';  ");
			out.println(" e_add_but_nonrelated_c.innerHTML='';  ");
			
			out.println(" lineno_ba=0;");
			out.println(" arr_size_ba=0;");
			out.println(" e_label_ba.innerHTML='';  ");
			out.println(" e_header_ba.innerHTML='';  ");
			out.println(" e_txt_ba.innerHTML='';  ");
			out.println(" e_add_but_ba.innerHTML='';  ");
			
			out.println("}");
			
			out.println(" function assign_help_status(obj){");
			//out.println(" alert('ok');");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("}");

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),add_individual()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_lineno' VALUE=\"New\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> </td></tr></table>"); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0'  width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_TYPE'  class=div_input>Client Type *</DIV></td>"); 
			out.println("<td>");
			out.println("<select name='TXT_CLIENT_TYPE' class=\"txt_input\" onChange=\"select_content(TXT_CLIENT_TYPE.value) \" >");
			out.println("<OPTION value=\"C\" >Corporate </option>");
			out.println("<OPTION value=\"I\" SELECTED>Individual </option>");
			out.println("</SELECT></td>");
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_TYPE' maxlength='1' size='1'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");  
			out.println("</td></tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr >" );
			//<TD STYLE="{color: white ;font: 10pt Helvetica;text-align:right;}" WIDTH="100%" >
		  out.println("<td clospan=2 width=\"100%\"><DIV ID=e_mode>  </DIV></td> ");				
		  out.println("</tr>");
      out.println("</table>");
		
			//'Individual' div tags begin
			//#############################
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td><DIV ID=e_mode_emp>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td clospan=2 width=\"100%\"><DIV ID=e_mode2>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Bank <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_bank>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_bank>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_bank>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Credit Facilities <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_credit>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_credit></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_credit></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_credit>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			//end Credit facilities <div>s
			
			//Non related referees <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_nonrelated>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_nonrelated></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_nonrelated></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_nonrelated>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Guarantor <div>s
			/*out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_guarantor>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_guarantor></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_guarantor></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_guarantor></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");*/

			out.println("<table align='center' width='100%' class='table'>"); 
			
			//Income Expense  <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_income_expense>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_income>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_data_income>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_other_income>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_tot_income>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_expense>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_data_expense>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_other_expense>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_tot_expense>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Family Members divs
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_family_members>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_family_members></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_family_members></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_family_members></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_row_tot_dep></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
      
      //Individual div tags end
			
			//CORPORATE DIV TAGS START
			//##########################
			
			//Company directors divs
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_company_directors>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_company_directors></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_company_directors></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_company_directors></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Business Activities divs
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_ba> </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_ba></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_ba></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_ba></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Subsidiaries & Associated companies divs
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_subsidiaries> </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_subsidiaries></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_subsidiaries></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_subsidiaries></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Bank <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_bank> </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_bank_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_bank_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_bank_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//B. DETAILS OF CREDIT FACILITIES  
			//########################################
			//Credit Facilities <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_credit_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_credit_c></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_credit_c></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_credit_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			// customers & suppliers divs
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_customer> </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_customer>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_customer>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_customer>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//Non related referees <div>s Corporate
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_label_nonrelated_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_header_nonrelated_c></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_txt_nonrelated_c></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_add_but_nonrelated_c>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			//out.println("</table>");
			
			
			/*out.println("<table>");
			out.println("<td width='30%' >Duration At Years *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_YEARS' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Duration At Months *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_MONTHS' maxlength='2' size='2'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); */
			
			out.println("</form>"); 
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

