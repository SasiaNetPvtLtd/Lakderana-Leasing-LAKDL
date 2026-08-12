//--
//SCREEN NAME: MARKETING - APPLICATION PROCESS
//CREATED BY: YOHAN GUNARATHNA
//DATE/TIME:
//NOTES:


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class LAKDL_AF_MK_Application_Process extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	public ResultSet rs,rs1;
	ServletOutputStream out =  null;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String header_name = m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url = m_sn_methods.servlet_client_url;
			String m_client_name = m_sn_methods.client_name;
			String m_client_t3_port = m_sn_methods.client_t3_port;
			Connection conn = m_sn_methods.met_user_validate(req);
			String m_username = m_sn_methods.username;
			stmt = conn.createStatement();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			String m_CLOSE = "";
			String m_chksql = req.getParameter("chksql");
			
			if(m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			
			else if(m_chksql.trim().equals("main_page")) {
				
				String m_application_no = req.getParameter("application_no");
				
				m_CLOSE = req.getParameter("CLOSE");
				
				if(req.getParameter("CLOSE") == null) {
					m_CLOSE = "N";
				}
				
				
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Marketing - Application Process</TITLE>");
				//out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\" >");//add by waruna 2012-04-23
				
				out.println("</HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">");
				
				
				out.println("var lineno=0;");
				out.println("var arr_size=0;");
				out.println("var b_flag=0;"); // Boolean Variable To Hold The Status.
				out.println("var b_inv_val=0;"); //Boolean Variable To Hold The Status.
				
				
				out.println("var arr_size1=0;");
				out.println("var array_is_branch_code=new Array();");
				out.println("var array_is_code1=new Array();");
				out.println("var array_is_code=new Array();");
				out.println("var array_is_amt=new Array();");
				out.println("var array_is_date_dd=new Array();");
				out.println("var array_is_date_mm=new Array();");
				out.println("var array_is_date_yy=new Array();");
				out.println("var array_is_st_date_dd=new Array();");
				out.println("var array_is_st_date_mm=new Array();");
				out.println("var array_is_st_date_yy=new Array();");
				out.println("var array_is_end_date_dd=new Array();");
				out.println("var array_is_end_date_mm=new Array();");
				out.println("var array_is_end_date_yy=new Array();");
				out.println("var is_flag=0;");
				out.println("var is_flag1=0;");
				out.println("var is_count=0;");
				out.println("var date_count=0;");
				
				//out.println("array_is_code1='';");
				
				out.println("var array_gur_code=new Array();");
				out.println("var array_gur_name=new Array();");
				out.println("var array_relation=new Array();");
				out.println("var array_period=new Array();");
				out.println("var array_tel_no=new Array();");
				out.println("var array_nic_reg_no=new Array();");
				
				out.println("var application_no=\"\"; ");
				out.println("var is_lineno=0;");
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				
				
				out.println("function header(){");
				
				/*out.println("m_table.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
				out.println("'<TR><TD WIDTH=\"25%\" align=\"left\"><B>Code</B></TD>'+");
				out.println("'<TD WIDTH=\"23%\"     align=\"left\"><B>Name</B></TD>'+");
				out.println("'<TD WIDTH=\"14%\"     align=\"left\"><B>Relationship</B></TD>' +");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Period</B></TD>' +");
				out.println("'<TD WIDTH=\"14%\"     align=\"left\"><B>Telephone No</B></TD>' +");
				out.println("'<TD WIDTH=\"*%\"     align=\"left\"><B>NIC/Business Reg No</B></TD>' +");
				out.println("'</TR></table>';");*/
				
				
				// Modified Nuwan De Silva --14-05-07--------
				out.println("m_table.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'<TR><TD WIDTH=\"25%\" align=\"left\"><B>Code</B></TD>'+");
				out.println("'<TD WIDTH=\"20%\"     align=\"left\"><B>Name</B></TD>'+");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Relationship</B></TD>' +");
				out.println("'<TD WIDTH=\"10%\"     align=\"left\"><B>Period (Yrs)</B></TD>' +");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Telephone No</B></TD>' +");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>NIC/Business Reg No</B></TD>' +");
				out.println("'</TR></table>';");
				
				out.println("}");
				
				// JavaScript Function Created By Samitha Kulatilaka On 2009-10-15
				out.println("function empty_lead_source_name() {");
				
				//out.println("  alert(document.Form1.LEAD_SOURCE_CATEGORY.value); ");
				
				out.println("	document.Form1.LEAD_SOURCE_NAME.value = '';");
				out.println("	document.Form1.LEAD_SOURCE_CODE.value = '';");
				
				// commented by udara 01-09-2017 for re-finance type change
				/*
				// added by udara 28-04-2014
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value=='TEST'){");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				out.println("   }");
				*/
				
				// added by udara 10-10-2014
				out.println("   if(document.Form1.LEAD_SOURCE_CATEGORY.value=='DIRECT'){"); // added by udara 01-09-2017
				//out.println("	else if(document.Form1.LEAD_SOURCE_CATEGORY.value=='DIRECT'){"); // commented by udara 01-09-2017
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				out.println("   }");
				
				out.println("	else if(document.Form1.LEAD_SOURCE_CATEGORY.value=='N/P'){");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;");
				out.println("   }");
				
				out.println("   else{");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = false;");
				out.println("	   document.Form1.inqu_help.disabled = false;"); 
				out.println("   }");
				// end by udara 28-04-2014
				
				//----added by ishani 2014-02-27----------
				//out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST'){"); // commented by udara 23-05-2022
				out.println("	if((document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST') || (document.Form1.LEAD_SOURCE_CATEGORY.value =='RESH')){"); // added by udara 23-05-2022
				
				out.println(" m_re_finance.innerHTML+=''+");
				out.println("  '<TD><DIV id=\"DIV_TXT_FINANCE\"  class=div_input>Re-Finance No *</DIV></TD>'+"); // mod by udara 24-07-2014 // out.println("  '<TD>Re-Finance No</TD>'+");
				out.println("  '';");
				out.println("   m_re_finance2.innerHTML+=''+");
				out.println("   '<TD><input name=\"TXT_FINANCE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:175px;\" disabled >'+"); // added disabled by udara 30-12-2014
				out.println("  '<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_FINANCE\" value=\"Help\" onClick=\"help_button_finance();\" > </TD>'+"); //onClick=\"help_button_finance(\"13\");\"
				out.println("   '';");
				//out.println("''+");
				out.println("}");
				out.println("else{");
				out.println("m_re_finance.innerHTML=\"\"; ");
				out.println("m_re_finance2.innerHTML=\"\"; ");
				out.println("}");
				//-------added end by ishani------------
				out.println("}");
				
				
				// added by udara 17-03-2014
				
				out.println("function empty_lead_source_name_2() {");
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST'){");
				
				out.println(" m_re_finance.innerHTML=''; "); // added by udara 07-05-2014
				out.println("m_re_finance2.innerHTML=\"\"; "); // added by udara 28-05-2014
				
				out.println(" m_re_finance.innerHTML+=''+");
				out.println("  '<TD><DIV id=\"DIV_TXT_FINANCE\"  class=div_input>Re-Finance No *</DIV></TD>'+"); // commented by udara 24-07-2014 // out.println("  '<TD>Re-Finance No</TD>'+");
				out.println("  '';");
				out.println("   m_re_finance2.innerHTML+=''+");
				out.println("   '<TD><input name=\"TXT_FINANCE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:175px;\" disabled >'+"); // added disabled by udara 30-12-2014
				out.println("  '<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_FINANCE\" value=\"Help\" onClick=\"help_button_finance();\" > </TD>'+"); //onClick=\"help_button_finance(\"13\");\"
				out.println("   '';");
				//out.println("''+");
				out.println("}");
				out.println("else{");
				out.println("m_re_finance.innerHTML=\"\"; ");
				out.println("m_re_finance2.innerHTML=\"\"; ");
				out.println("}");
				out.println("}");
				
				// end by udara 17-03-2014
				
				
				out.println("function get_finance_amount(amount) {");
				out.println("   m_re_finance_Amt.innerHTML=''; ");  // added by udara 07-05-2014
				out.println("   m_re_finance2_Amt.innerHTML=''; "); // added by udara 07-05-2014
				//out.println("	document.Form1.LEAD_SOURCE_NAME.value = '';");
				//out.println("	document.Form1.LEAD_SOURCE_CODE.value = '';");
				//out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST'){");
				out.println(" m_re_finance_Amt.innerHTML+=''+");
				out.println("  '<TD>Closing Receipt Amount</TD>'+"); // out.println("  '<TD>Re-Finance Amount</TD>'+");
				out.println("  '';");
				out.println("   m_re_finance2_Amt.innerHTML+=''+");
				out.println("   '<TD><input name=\"TXT_FINANCE_AMT\" type=\"text\" class=\"txt_input\" style=\"width:150px; text-align :right \" maxlength=18 onblur=\"check_amt(this,18)\" value=\"'+unformat_noobject(amount)+'\" disabled >'+"); // added disabled by udara 30-12-2014
				out.println("  ' </TD>'+"); //onClick=\"help_button_finance(\"13\");\"
				out.println("   '';");
				//out.println("}");
				//out.println("else{");
				//out.println("m_re_finance.innerHTML=\"\"; ");
				//out.println("m_re_finance2.innerHTML=\"\"; ");
				//out.println("}");
				out.println("}");
				//-------added end by ishani------------
				
				
				
				out.println("function validate_txt_type(){");
				
				out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
				out.println("if(document.Form1.TXT_TR_TYPE.value=='LOANS'){");
				out.println("     document.Form1.chk_complete.disabled=true;");
                out.println("     document.getElementById(\"pledge_label\").hidden=false; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"pledge_text\").hidden=false; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"vendor_label\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     document.getElementById(\"vendor_text\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("}");	
				
				
				// added by udara 14-02-2019
				out.println("else if(document.Form1.TXT_TR_TYPE.value=='PER_LOANS'){");
				out.println("     document.Form1.chk_complete.disabled=true;");
				out.println("     document.getElementById(\"pledge_label\").hidden=true; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"pledge_text\").hidden=true; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"vendor_label\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     document.getElementById(\"vendor_text\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("}");	
				// end by udara 14-02-2019

				//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("else if(document.Form1.TXT_TR_TYPE.value=='HADAGASMA'){");
				out.println("     document.Form1.chk_complete.disabled=true;");
				out.println("     document.getElementById(\"vendor_label\").hidden=false; ");
				out.println("     document.getElementById(\"vendor_text\").hidden=false; ");
				out.println("     document.getElementById(\"pledge_label\").hidden=false; "); // added by udara 02-12-2024
				out.println("     document.getElementById(\"pledge_text\").hidden=false; "); // added by udara 02-12-2024
				out.println("}");	
				//end by kasun on 2024.11.22 for JB16102024-25600
				
				out.println("else");	
				out.println("{");	
				out.println("     document.getElementById(\"pledge_label\").hidden=true; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"pledge_text\").hidden=true; "); // added by udara 09-10-2018
				out.println("     document.getElementById(\"vendor_label\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     document.getElementById(\"vendor_text\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     chk_totals();");
				out.println("}");
				
				out.println("}");
				
				out.println("     set_pledge_div(document.Form1.SCREEN_NAME.value,document.Form1.TXT_TR_TYPE.value);  "); // added by udara 09-10-2018
				
				out.println("}");
				
				// added by udara 09-10-2018
				out.println("function set_pledge_div(screen_name,tran_type){");
				
				//out.println("   if(tran_type=='EDIT'){ ");
				
				out.println("       if(tran_type=='LOANS'){ ");
				
				out.println("             document.getElementById('pledge_label').innerHTML = \"<td>Pledge Contract *</td>\"; "); 
				
				out.println("             document.getElementById('pledge_text').innerHTML = \"<td><input class='txt_input' type='text' name='TXT_PLEDGE_CONTRACT'  maxlength='25' size='25' onblur='btn_pledge_help()' > \" + "); 
				out.println("                                    \"<input type=button name=btn_pledge_contract value=... class='but_input' onclick='btn_pledge_help()'  ></td> \"; ");
				
				//added by udara 22-10-2018
				out.println("             document.Form1.TXT_INSURANCE_DONE.disabled=true; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.value = '-'; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.disabled=true; ");
				out.println("             document.Form1.ins_officer.disabled=true; ");

				out.println("     		  document.getElementById(\"vendor_label\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     		  document.getElementById(\"vendor_text\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("             document.getElementById('vendor_label').innerHTML = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
				out.println("             document.getElementById('vendor_text').innerHTML  = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
			    //end by udara 22-10-2018
				
				out.println("       }"); 
				
				// added by udara 14-02-2019
				out.println("       else if(tran_type=='PER_LOANS'){ ");
				
				out.println("             document.getElementById('pledge_label').innerHTML = \"\"; "); 
				out.println("             document.getElementById('pledge_text').innerHTML  = \"\"; ");

				out.println("     		  document.getElementById(\"vendor_label\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("     		  document.getElementById(\"vendor_text\").hidden=true; ");//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("             document.getElementById('vendor_label').innerHTML = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
				out.println("             document.getElementById('vendor_text').innerHTML  = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
				
				//added by udara 22-10-2018
				out.println("             document.Form1.TXT_INSURANCE_DONE.disabled=true; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.value = '-'; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.disabled=true; ");
				out.println("             document.Form1.ins_officer.disabled=true; ");
			    //end by udara 22-10-2018
				
				out.println("       }"); 

				//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("       else if(tran_type=='HADAGASMA'){ ");
				
				// added by udara 02-12-2024
				out.println("             document.getElementById('pledge_label').innerHTML = \"<td>Pledge Contract *</td>\"; "); 
				
				out.println("             document.getElementById('pledge_text').innerHTML = \"<td><input class='txt_input' type='text' name='TXT_PLEDGE_CONTRACT'  maxlength='25' size='25' onblur='btn_pledge_help()' > \" + "); 
				out.println("                                    \"<input type=button name=btn_pledge_contract value=... class='but_input' onclick='btn_pledge_help()'  ></td> \"; ");
				// end by udara 02-12-2024

				out.println("             document.getElementById('vendor_label').innerHTML = \"<td>Vendor Code *</td>\"; "); 
				
				out.println("             document.getElementById('vendor_text').innerHTML = \"<td><input class='txt_input' type='text' name='TXT_VENDOR_CODE'  maxlength='25' size='25' onblur='vendor_help()' > \" + "); 
				out.println("                                    \"<input type=button name=btn_vendor_help value=... class='but_input' onclick='vendor_help()'  ></td> \" + ");
				out.println("                                    \"<input type=button name=btn_vendor_create value=Create class='but_input' onclick='vendor_create()'  ></td> \"; ");

				// commented by udara 02-12-2024
				/*
				out.println("     		  document.getElementById(\"pledge_label\").hidden=true; "); 
				out.println("     		  document.getElementById(\"pledge_text\").hidden=true; ");
				out.println("             document.getElementById('pledge_label').innerHTML = \"\"; "); 
				out.println("             document.getElementById('pledge_text').innerHTML  = \"\"; ");
				*/
				
				out.println("             document.Form1.TXT_INSURANCE_DONE.disabled=true; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.value = '-'; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.disabled=true; ");
				out.println("             document.Form1.ins_officer.disabled=true; ");
				out.println("       }"); 
				//end by kasun on 2024.11.22 for JB16102024-25600

				// else by udara 14-02-2019

				out.println("       else{");
				
				out.println("             document.getElementById('pledge_label').innerHTML = \"\"; "); 
				out.println("             document.getElementById('pledge_text').innerHTML  = \"\"; "); 

				out.println("             document.getElementById('vendor_label').innerHTML = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
				out.println("             document.getElementById('vendor_text').innerHTML  = \"\"; "); //added by kasun on 2024.11.22 for JB16102024-25600
				
				//added by udara 22-10-2018
				out.println("             document.Form1.TXT_INSURANCE_DONE.disabled=false; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.value = ''; "); 
				out.println("             document.Form1.INSURANCE_OFFICER.disabled=false; ");
				out.println("             document.Form1.ins_officer.disabled=false; ");
			    //end by udara 22-10-2018
				
				out.println("       }");
				
				//out.println("   }");
				
				out.println("}");
				// end by udara 09-10-2018
				
				// JavaScript Function Added By Samitha Kulatilaka On 2009-10-20
				out.println("function disable_textbox() {");
				out.println("document.Form1.TXT_INQUARY_NO.disabled = true;");
				out.println("document.Form1.BUT_TXT_INQUARY_NO.disabled = true;");
				out.println("}");
				
				
				out.println("function chk_totals(){");
				out.println("Check_transaction_type()"); //Added by Nuwan De Silva
				out.println("}");
				
				
				out.println("function Check_transaction_type(){");
				out.println(" assignState('M_TXT')");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_txt_type&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
				out.println(" load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function assign_txt_type(data_vec){");
				out.println("document.Form1.hid_transaction_type.value=data_vec[0];");
				out.println(" assignState('M9')");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_pop_totals&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
				//out.println("window.open(m_url);"); // udara 
				out.println(" load_interface(m_url,'XML');");
				//out.println("window.open(m_url);");
				out.println("}");
				
				
				out.println("function pop_totals(data_vec){");
				out.println("m_table_counts.innerHTML=\"\"");
				out.println("Asset_count.innerHTML=\"\"");
				out.println("Valuation_count.innerHTML=\"\"");
				out.println("Proforma_count.innerHTML=\"\"");
				out.println("Pricing_count.innerHTML=\"\"");
				out.println("Security_count.innerHTML=\"\"");//add bby waruna 2012-04-24
				
				out.println(" var m_price_tot=0;");
				out.println(" var m_profo_tot=0;");
				out.println(" var m_asset_count=0;");
				out.println(" var m_valuation_count=0;");
				out.println(" var m_proforma_count=0;");
				out.println(" var m_pricing_count=0;");
				out.println(" var m_security_count=0;");//add bby waruna 2012-04-24
				
				out.println("if(data_vec.length > 0){");
				out.println(" m_price_tot=data_vec[0];");
				out.println(" m_profo_tot=data_vec[1];");
				out.println(" document.Form1.hid_price_tot.value=data_vec[1];");
				out.println(" document.Form1.hid_profo_tot.value=data_vec[0];");
				out.println(" document.Form1.hid_asset_count.value=data_vec[2];"); //added by nuwan de silva on 15-11-2007
				out.println(" document.Form1.hid_invoice_count.value=data_vec[4];"); //added by nuwan de silva on 15-11-2007
				out.println(" m_asset_count=data_vec[2];");
				out.println(" m_valuation_count=data_vec[3];");
				out.println(" m_proforma_count=data_vec[4];");
				out.println(" m_pricing_count=data_vec[5];");
				out.println(" m_security_count=data_vec[6];");//add by waruna 2012-04-24
				out.println("}");
				
				out.println("m_table_counts.innerHTML+='<table align=\"left\" width=\"100%\" class=\"table\">'+");
				out.println("'<TR><TD WIDTH=\"30%\" align=\"left\"><B>Total Amounts from Pricing Calculations:</B></TD>'+");
				//out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+format_noobject(m_price_tot)+'</B></TD>'+");
				out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+m_price_tot+'</B></TD>'+");
				out.println("'<TD WIDTH=\"*%\" align=\"left\"><B></B></TD>'+");
				out.println("'</TR>'+");
				out.println("'<TR><TD WIDTH=\"30%\" align=\"left\"><B>Total Amounts from Pro Forma Invoices:</B></TD>'+");
				//out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+format_noobject(m_profo_tot)+'</B></TD>'+");
				out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+m_profo_tot+'</B></TD>'+");
				out.println("'<TD WIDTH=\"*%\" align=\"left\"><B></B></TD>'+");
				out.println("'</TR>'+");
				out.println("'<TR><TD WIDTH=\"30%\" align=\"left\"><B>Difference :</B></TD>'+");
				//out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+format_noobject(Math.abs(m_price_tot-m_profo_tot))+'</B></TD>'+");
				out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>'+Math.abs(unformat_noobject(m_price_tot)-unformat_noobject(m_profo_tot))+'</B></TD>'+");//MODIFIED NUWAN DE SILVA 11/05/07
				
				out.println("'<TD WIDTH=\"*%\" align=\"left\"><B></B></TD>'+");
				out.println("'</TR>'+");
				out.println("'</table>';");
				
				
				out.println("Asset_count.innerHTML+='<b>'+m_asset_count+'</b>';");
				out.println("Valuation_count.innerHTML+='<b>'+m_valuation_count+'</b>';");
				out.println("Proforma_count.innerHTML+='<b>'+m_proforma_count+'</b>';");
				out.println("Pricing_count.innerHTML+='<b>'+m_pricing_count+'</b>';");
				out.println("Security_count.innerHTML+='<b>'+m_security_count+'</b>';");//add by waruna 2012-04-24
				//out.println("if(document.Form1.hid_transaction_type.value!='LOANS'){"); // commented by udara 14-02-2019
				//out.println("if((document.Form1.hid_transaction_type.value!='LOANS') && (document.Form1.hid_transaction_type.value!='PER_LOANS')){"); // added by udara 14-02-2019 // commented by udara 02-12-2024
				out.println("if((document.Form1.hid_transaction_type.value!='LOANS') && (document.Form1.hid_transaction_type.value!='PER_LOANS') && (document.Form1.hid_transaction_type.value!='HADAGASMA')){"); // added by udara 02-12-2024
				out.println(" if(document.Form1.hid_profo_tot.value!=0 && document.Form1.hid_profo_tot.value!=\"\" && document.Form1.hid_profo_tot.value==document.Form1.hid_price_tot.value)  {");
				out.println("   if(m_asset_count!=0 && m_proforma_count!=0 && m_asset_count==m_proforma_count ) {"); //Added By Nuwan De Silva 31/01/2007
				out.println("     document.Form1.chk_complete.disabled=false;");
				out.println("     document.Form1.hid_tot_fin_amt.value=document.Form1.hid_profo_tot.value;");
				out.println("     document.Form1.hid_cur_fin_amt.value=document.Form1.hid_profo_tot.value;");
				out.println("    }");
				out.println("  }");
				//added by SH on 15/04/2008 for loans ###L 
				out.println("}else{");
				out.println("  document.Form1.chk_complete.disabled=false;");
				//end of loans ###L
				out.println("}");
				out.println("chk_status_to_view()");//Call To THe Function To Check That Can Enable The Link.
				out.println("}");
				
				out.println("function chk_status_to_view(){ ");
				out.println("assignState('M10')");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_enable&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
				out.println(" load_interface(m_url,'XML');");
				out.println("");
				out.println("}");
				
				
				out.println("function validate_gur_code(rowNo){");
				
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo");
				out.println("assignState('M8')");
				out.println("document.Form1.hid_row_no.value=rowNo;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+document.Form1.elements[m_gur_code].value+\"&data_val2=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&data_val3=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");	
				
				
				out.println("function fill_guarantor(rowNo,data_vec){");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+rowNo;");
				out.println("m_tel_no=\"TXT_TEL_NO\"+rowNo;");
				out.println("m_nic_reg_no=\"TXT_NIC_REG_NO\"+rowNo;");
				out.println("    document.Form1.elements[m_gur_code].value=data_vec[0];"); 
				out.println("    document.Form1.elements[m_gur_name].value=data_vec[1];"); 
				out.println("    document.Form1.elements[m_tel_no].value=data_vec[2];"); 
				out.println("    document.Form1.elements[m_nic_reg_no].value=data_vec[3];"); 
				out.println("}");
				
				
				out.println("function disable_nic_no(size){");
				out.println("for(i=0;i<size;i++){");
				out.println("m_nic_reg=\"TXT_NIC_REG_NO\"+i");
				out.println("document.Form1.elements[m_nic_reg].disabled=true;}");
				out.println("}");
				
				
				/*----------------------------------------------------------------
				Purpose  : Display Gurantor Details
				
				----------------------------------------------------------------*/
				
				out.println("function display_data(data_vec){");
				out.println("m_table.innerHTML=\"\"");
				out.println("header();");
				out.println("j=0;");
				out.println("i=0;");
				out.println("if(data_vec.length==0){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"\" style=\"width:130px;\" maxlength=\"50\" size=\"50\" onblur=\"validate_gur_code('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+j+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:180px;\" value=\"\" style=\"width:250px;\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" style=\"width:130px;\"  value=\"\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:80;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" style=\"width:130px;\" value=\"\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" style=\"width:105px;\" value=\"\" size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("j=j+1;");	
				out.println("}");
				out.println("else{");
				out.println("while(i<data_vec.length){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"'+data_vec[i]+'\" style=\"width:130px;\" maxlength=\"50\" size=\"50\" onblur=\"validate_gur_code('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+j+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+'  style=\"width:180px;\" value=\"'+data_vec[i+4]+'\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" style=\"width:130px;\" value=\"'+data_vec[i+1]+'\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:80;}\" maxlength=\"2\"  value=\"'+data_vec[i+2]+'\" size=\"10\" onblur=\"val_num('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" style=\"width:130px;\" value=\"'+data_vec[i+3]+'\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" style=\"width:105px;\" value=\"'+data_vec[i+5]+'\" size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("j=j+1;");
				out.println("i=i+6;");
				out.println("}"); //End of for loop;
				out.println("}");
				out.println("lineno=j;");
				out.println("arr_size=j;");
				out.println("disable_nic_no(arr_size);");
				out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
				out.println(" }");
				out.println("else{");
				out.println("document.Form1.INVOICE_LINK_BUT.disabled=false;");
				out.println("document.Form1.ASSET_LINK_BUT.disabled=false;");
				out.println("document.Form1.VALUATION_LINK_BUT.disabled=false;}");
				out.println(" chk_totals();");
				out.println("}"); 
				
				
				out.println("function get_vector(data_vec) {");
				
				//out.println("           alert('get_vector' + '   ' +  data_vec.length + '   ' + document.Form1.SCREEN_NAME.value + '    ' + document.Form1.hid_chk_status.value  ); "); udara test 07-05-2014
				
				out.println("b_inv_val=0;");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
				
				// added by udara 06-01-2013
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value;");	
				out.println("m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
				out.println("m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
				
				
				out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
				out.println("				alert('Record already exists.');");
				out.println("				new_window();");
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='M_VAL'){");
				//------------- added by Sh on 16-04-2008 for Loans ###L
				//out.println("       if(document.Form1.TXT_TR_TYPE.value=='LOANS'){ "); // commented by udara 14-02-2019
				//out.println("       if((document.Form1.TXT_TR_TYPE.value=='LOANS') || (document.Form1.TXT_TR_TYPE.value=='PER_LOANS')){ "); // added by udara 14-02-2019 // commented by udara 02-12-2024
				out.println("       if((document.Form1.TXT_TR_TYPE.value=='LOANS') || (document.Form1.TXT_TR_TYPE.value=='PER_LOANS') || (document.Form1.TXT_TR_TYPE.value=='HADAGASMA')){ "); // added by udara 02-12-2024
				out.println("			  document.Form1.HID_INV_PR_CO.value = data_vec[0]; ");
				out.println("			  document.Form1.HID_PR_CO.value     = data_vec[1]; ");
				out.println("			    if (parseFloat(document.Form1.HID_INV_PR_CO.value)==0){");
				out.println("			        if (parseFloat(document.Form1.HID_PR_CO.value)!=1){");
				out.println("			        document.Form1.chk_complete.checked=false;");
				//out.println("			        return false;");
				out.println("			        }");
				out.println("			    } ");
				out.println("			  }else{"); 
				//------------- end of loans
				out.println("   assign_validate_values(data_vec);");
				out.println("			  }");//------------- added by Sh on 16-04-2008 for Loans ###L
				
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST'){");
				out.println("               validate_vehicle_chasis_engine_no_refinance(); "); // added by udara on 06-08-2013
				out.println("			  }else{");
				out.println("             validate_vehicle_chasis_engine_no(); "); // added by udara on 06-08-2013
				out.println("			  }");
				
				out.println("			}");				
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_APPLICANT_CODE.value!=\"\"){");
				out.println("				help_button_2('1','10','0','m_help_TXT_CLIENT_CODE','2','0') ;");
				out.println("       document.Form1.TXT_APPLICANT_NAME.value=\"\"; "); 
				out.println("       document.Form1.TXT_APPLICANT_CODE.focus() ;"); 
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_APPLICANT_CODE.value!=\"\"){");
				out.println("       document.Form1.TXT_APPLICANT_NAME.value=data_vec[1];"); 
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4'  && document.Form1.TXT_CORE_APPLICANT_CODE.value!=\"\"){");
				out.println("				help_button_6('1','10','0','m_help_TXT_CLIENT_CODE','6','0');");
				out.println("       document.Form1.TXT_CORE_APPLICANT_NAME.value=\"\"; "); 
				out.println("       document.Form1.TXT_CORE_APPLICANT_CODE.focus();"); 
				out.println("			}");
				out.println("			else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M4'  && document.Form1.TXT_CORE_APPLICANT_CODE.value!=\"\"){");
				out.println("       document.Form1.TXT_CORE_APPLICANT_NAME.value=data_vec[1];"); 
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M_MKT_OFFC'  && document.Form1.MKT_OFFICER.value!=\"\"){");
				out.println("				mk_officer_help('11');");  
				//out.println("       document.Form1.MKT_OFFICER.value=\"\"; "); 
				//out.println("       document.Form1.MKT_OFFICER.focus();"); 
				out.println("			}");
				out.println("			else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_MKT_OFFC'  && document.Form1.MKT_OFFICER.value!=\"\"){");
				out.println("       document.Form1.MKT_OFFICER.value=data_vec[0];"); 
				out.println("			}");			
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M_INS_OFFC'  && document.Form1.INSURANCE_OFFICER.value!=\"\"){");
				out.println("				ins_officer_help('12');");  
				out.println("			}");
				out.println("			else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_INS_OFFC'  && document.Form1.INSURANCE_OFFICER.value!=\"\"){");
				out.println("       document.Form1.INSURANCE_OFFICER.value=data_vec[0];"); 
				out.println("			}");	
				// commented by udara 13-07-2017
				/*
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
				out.println("				help_update('1','10','10','m_help_TXT_APPLICATION_NO','99','0');");
				out.println("			}");
				*/
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				//out.println("			alert('data vec test'); ");
				out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[0];");
				out.println("    document.Form1.TXT_FACILITY_NO.value=data_vec[1];");
				out.println("    document.Form1.TXT_APPLICANT_CODE.value=data_vec[2];");
				out.println("    document.Form1.TXT_APPLICANT_NAME.value=data_vec[3].replace('$','&');");
				out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value=data_vec[4];");
				out.println("    document.Form1.TXT_CORE_APPLICANT_NAME.value=data_vec[5].replace('$','&');");
				out.println("    document.Form1.TXT_INQUARY_NO.value=data_vec[6];");
				out.println("    document.Form1.hid_client_type.value=data_vec[7];");
				out.println("    document.Form1.TXT_TR_TYPE.value=data_vec[9];");
				out.println("    document.Form1.TXT_INSURANCE_DONE.value=data_vec[10];");
				
				// added by udara 11-12-2015
				out.println("    if(data_vec[30]>0) ");
				out.println("      document.Form1.TXT_INSURANCE_DONE.disabled=true;");
				out.println("    else ");
				out.println("      document.Form1.TXT_INSURANCE_DONE.disabled=false;");
				// end by udara 11-12-2015
				
				out.println("    document.Form1.TXT_PRIORITY.value=data_vec[11];");
				out.println("    document.Form1.TXT_REMARKS.value=data_vec[12];"); //ADDED BY CHANDANA ON 09/05/2007
				out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[13];"); //ADDED BY CHANDANA ON 25/06/2007
				out.println("    document.Form1.TXT_LOCATION_DESC.value=data_vec[14];"); //ADDED BY CHANDANA ON 25/06/2007
				out.println("    document.Form1.INSURANCE_OFFICER.value=data_vec[25];"); //ADDED BY SANDUN ON 11/03/2009
				out.println("    document.Form1.TXT_CONTRACT_NUMBER.value=data_vec[26];"); //ADDED BY prabash
				/////////////*****  **
				
				//added by nuwan de silva 25-06-07_________________________________
				out.println("if(data_vec[21]=='null' || data_vec[21]=='-' ){");
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value='N/A';");
				out.println("}");
				out.println("else {");
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value=data_vec[21];");
				out.println("}");
				
				out.println("if(data_vec[22]=='null' || data_vec[22]=='-' ){");
				out.println("    document.Form1.LEAD_SOURCE_NAME.value='';");
				out.println("    document.Form1.LEAD_SOURCE_CODE.value='';");
				out.println("}");
				out.println("else {");
				//out.println("    document.Form1.LEAD_SOURCE_NAME.value=data_vec[27];"); // commented by udara
				out.println("    document.Form1.LEAD_SOURCE_NAME.value=data_vec[22];"); // added by udara 11-05-2021
				out.println("    document.Form1.LEAD_SOURCE_CODE.value=data_vec[22];"); // thamali 2013.08.29
				
				out.println("}");
				
				
				// added by udara 17-03-2014
				out.println("if(document.Form1.LEAD_SOURCE_CATEGORY.value=='TEST'){");
				//out.println("    alert('alert 2');  ");
				out.println("    empty_lead_source_name_2(); ");
				out.println("    get_finance_amount(0);  "); // added by udara 07-05-2014
				
				
				// added by udara 07-05-2014
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				
				
				out.println("    document.Form1.TXT_FINANCE.value=data_vec[28];");
				out.println("    document.Form1.TXT_FINANCE_AMT.value=data_vec[29];"); // mod by udara 07-05-2014
				out.println("}");
				// end by udara 17-03-2014
				
				// added by udara 24-10-2014
				
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value=='DIRECT'){");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				out.println("   }");
				
				// end by udara 24-10-2014
				
				
				
				
				
				
				//addded by nuwan de silva on 27-11-2007_____________________________
				out.println("if(data_vec[23]=='null' || data_vec[23]=='-' ){");
				out.println("    document.Form1.TXT_DIVISION_CODE.value='';");
				out.println("}");
				out.println("else {");
				out.println("    document.Form1.TXT_DIVISION_CODE.value=data_vec[23];");
				out.println("}");
				//end  by nuwan de silva 25-06-07___________________________________
				out.println("    document.Form1.MKT_OFFICER.value=data_vec[24];"); //Added by Chandana on 30/11/2007 
				
				out.println("    if(data_vec[18]!='-'){");
				out.println("      document.Form1.hid_term_type.value=data_vec[18];");
				out.println("      document.Form1.hid_term_no.value=data_vec[16];");
				out.println("      document.Form1.hid_pre_app_no.value=data_vec[17];");
				out.println("      document.Form1.hid_term_amt.value=data_vec[19];");
				out.println("      TERM_TYPE.innerHTML=data_vec[15];         ");
				
				//out.println("      TERM_DET.innerHTML=data_vec[17]+'/'+data_vec[20]+'/'+format_noobject(data_vec[19]);"); // commented by udara 01-01-2016
				
				// added by udara 01-01-2016
				out.println("       if(data_vec[15] == 'Balance Transfer')  ");
				out.println("      		TERM_DET.innerHTML=data_vec[17]+'/'+data_vec[20]+'/'+format_noobject(data_vec[31]);");
				out.println("       else  ");
				out.println("      		TERM_DET.innerHTML=data_vec[17]+'/'+data_vec[20]+'/'+format_noobject(data_vec[19]);");
				// end by udara 01-01-2016
				
				out.println("    }else{");
				out.println("      document.Form1.hid_term_type.value='';");
				out.println("      document.Form1.hid_term_no.value='';");
				out.println("      document.Form1.hid_term_amt.value='';");
				out.println("      document.Form1.hid_pre_app_no.value='';");
				out.println("      TERM_TYPE.innerHTML='';         ");
				out.println("      TERM_DET.innerHTML='';         ");
				
				out.println("    }");
				
				// commented by udara since these are already loading at help select level
				
				// added by udara 18-10-2018
				out.println("    if(data_vec[32]=='LOANS'){ ");
				out.println("       set_pledge_div(document.Form1.SCREEN_NAME.value,data_vec[32]);  ");
				out.println("       pledge_contract_set(data_vec[33]); ");
				out.println("    }");
				// end by udara 18-10-2018


				out.println("    if(data_vec[32]=='HADAGASMA'){ "); //added by kasun on 26-11-2024
				out.println("       set_pledge_div(document.Form1.SCREEN_NAME.value,data_vec[32]);  ");
				out.println("       vendor_code_set(data_vec[34]); ");
				out.println("       pledge_contract_set(data_vec[33]); "); // added by udara 02-12-2024
				out.println("    }");
				
				out.println("    assignState('M7');");
				out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
				out.println("    enable_links(); ");
				
				
				out.println("			}");
				
				// app det load end
				
				
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6'  && document.Form1.TXT_INQUARY_NO.value!=\"\"){");
				out.println("				help_button_3('1','10','2','m_help_TXT_INQUARY_NO','3','0');");
				//out.println("       document.Form1.TXT_INQUARY_NO.focus(); "); comment by nuwan de silva 27-06-07
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
				out.println("      display_data(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
				out.println("				help_button_5(document.Form1.hid_row_no.value);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
				
				//out.println("                alert(data_vec[4]);  ");
				out.println("                if(data_vec[4]=='YES'){    ");
				out.println("                	alert('Guaranter code is excisting with two running contracts');  ");
				out.println("                   document.Form1.elements[m_gur_code].value=\"\"; ");
				out.println("                   document.Form1.elements[m_gur_name].value=\"\"; ");
				out.println("                   document.Form1.elements[m_tel_no].value=\"\"; ");
				out.println("                   document.Form1.elements[m_nic_reg_no].value=\"\"; ");
				out.println("                } ");
				out.println("                else { ");
				out.println("                	fill_guarantor(document.Form1.hid_row_no.value,data_vec);"); 
				out.println("                } ");
				
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='M9'){");
				out.println("      pop_totals(data_vec);"); 		
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='M10'){");
				out.println("      display_status(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='M_TXT'){");
				out.println("      assign_txt_type(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(document.Form1.hid_chk_status.value=='J1' && document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){");
				out.println("      display_bank_guarn(data_vec);");
				out.println("			}");
				
				out.println("			else");
				out.println("			if(data_vec==0 && document.Form1.hid_chk_status.value=='J2' && document.Form1.elements[\"TXT_ISSUER_CODE\"+document.Form1.hid_use.value].value!=\"\" ){");
				out.println("      help_button_8(document.Form1.hid_use.value);"); 		
				out.println("			}");
				
				//added by nuwan de silva 25-06-07--------------------------------
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M_BRANCH'  && document.Form1.TXT_LOCATION_CODE.value!=\"\"){");
				out.println("				help_button_location('1','10','0','m_help_TXT_LOCATION_CODE_sql','9','0');");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M_BRANCH'  && document.Form1.TXT_LOCATION_CODE.value!=\"\"){");
				out.println("       document.Form1.TXT_LOCATION_CODE.value=data_vec[0];"); 
				out.println("       document.Form1.TXT_LOCATION_DESC.value=data_vec[1];"); 
				out.println("			}");
				
				// added by udara 06-08-2013
				out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='VEHI_VAL' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){"); //  && document.Form1.SCREEN_NAME.value!=\"EDIT\"
				// data_vec[0] = reg no
				// data_vec[1] = chasis no
				// data_vec[2] = engine no
				//out.println("			    alert(data_vec[0]+'   '+data_vec[1]+'   '+data_vec[2] ); ");
				//out.println("             alert(document.Form1.SCREEN_NAME.value);    ");
				
				out.println("               if(data_vec[0] > 1){    ");
				out.println("			       alert('Vehicle number is existing - ' + data_vec[3]); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				out.println("               else if(data_vec[1] > 1){    ");
				out.println("			       alert('Chasis number is existing - ' + data_vec[4]); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				out.println("               else if(data_vec[2] > 1){    ");
				out.println("			       alert('Engine number is existing - ' + data_vec[5]); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				
				out.println("			}");
				// end by udara 06-08-2013
				// added by ishani 17-03-2014
				out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='VEHI_VAL_RE' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){"); //  && document.Form1.SCREEN_NAME.value!=\"EDIT\"
				out.println("               if(data_vec[0] == 0){    ");
				out.println("			       alert('Vehicle number is not equals to old vehicel no'); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				out.println("               else if(data_vec[1] == 0){    ");
				out.println("			       alert('Chasis number is not equals to old one'); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				out.println("               else if(data_vec[2] == 0){    ");
				out.println("			       alert('Engine number is not equals to old'); ");
				out.println("                  document.Form1.chk_complete.checked = false; ");
				out.println("                  document.Form1.chk_complete.disabled = true; ");
				out.println("			    }");
				out.println("			}");
				// end by ishani 17-03-2014
				
				//----------------------------------------------------------------
				
				// added by udara 18-10-2018
				out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='PLEDGE_STATUS' ){"); 
				out.println("			   if(document.Form1.TXT_APPLICATION_NO.value!=data_vec[0]){");
				out.println("			      alert('This is an already pledged contract'); ");
				out.println("			      document.Form1.TXT_PLEDGE_CONTRACT.value = ''; ");
				out.println("			   }");
				out.println("			}");
				// end by udara 18-10-2018
				
				// added by udara 08-04-2021
				out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='BROKER_STATUS' ){"); 
				out.println("			   if(data_vec[0]=='0'){");
				out.println("			      alert('Please enter an existing broker'); ");
				out.println("                 brk_help('1','10','4','BrokerSql','10','0');   ");
				//out.println("			      document.Form1.LEAD_SOURCE_NAME.value = ''; ");
				//out.println("			      document.Form1.LEAD_SOURCE_NAME.focus(); ");
				out.println("			   }");
				out.println("			}");
				// end by udara 08-04-2021
				
				
				out.println("document.Form1.TXT_TR_TYPE.disabled=true;"); // added by udara 31-10-2018
				
				out.println("}");
				
				
				
				
				out.println("function assign_validate_values(data_vec) {");
				out.println("var i=0;");
				out.println("while(i<data_vec.length){");
				out.println("     if(data_vec[i]==1){;"); 		
				out.println("			b_inv_val=1;");
				out.println("			break;");
				out.println("			}");
				out.println("			else{");
				out.println("			b_inv_val=0;");
				out.println("			}");
				out.println("			i=i+1;");			
				out.println("}");
				out.println("}");
				
				
				out.println("function    makeRequest(obj) {");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
				out.println("if(document.Form1.hid_chk_status.value=='M1'){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application&data_val=\"+obj.value+\"&ac_status=ENTERED&ac_status2=ENT_CON\";");
				out.println("}else if(document.Form1.hid_chk_status.value=='M2')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("else if(document.Form1.hid_chk_status.value=='M3')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&data_val2=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&data_val3=\"+document.Form1.elements[m_gur_code].value+\"&ac_status=Y\";");
				out.println("else if(document.Form1.hid_chk_status.value=='M4')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&data_val2=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&data_val3=\"+document.Form1.elements[m_gur_code].value+\"&ac_status=Y\";");
				out.println("else if(document.Form1.hid_chk_status.value=='M6')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_Application_Process_val_inq_no&data_val=\"+obj.value; ");
				out.println("else if(document.Form1.hid_chk_status.value=='M7')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_data&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("else if(document.Form1.hid_chk_status.value=='M_VAL')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_inv_valuation&data_val=\"+obj.value+\"&ac_status=Y\";");
				//added by nuwan de silva 25-06-07------------------------------------
				out.println("else if(document.Form1.hid_chk_status.value=='M_BRANCH')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_application_process_branch_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				//--------------------------------------------------------------------
				//Added by Chandana on 30/11/2007-------------------------------------
				out.println("else if(document.Form1.hid_chk_status.value=='M_MKT_OFFC')");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer_app&data_val=\"+obj.value+\"&ac_status=Y\";"); // added by udara 18-08-2017
				
				//--------------------------------------------------------------------
				//out.println("window.open(m_url);");	
				out.println("else if(document.Form1.hid_chk_status.value=='M_INS_OFFC')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				//out.println("window.open(m_url);");	
				out.println("}");
				
				
				out.println("function bank_guarntor(obj){");
				out.println(" if(document.Form1.hid_chk_status.value=='J1'){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_bank_guarantor&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				//	out.println("window.open(m_url);");
				out.println("}");
				out.println("}");
				
				out.println("function val_num(rowNo){");
				out.println("m_period=\"TXT_PERIOD\"+rowNo;");
				out.println("if(!isPosInteger(document.Form1.elements[m_period].value)){");
				out.println("alert('Please enter a number.');");
				out.println("document.Form1.elements[m_period].value=\"\"; ");
				out.println("document.Form1.elements[m_period].focus();}");
				out.println("}");
				
				out.println("function chk_data(){");
				
				out.println("b_flag=0;");
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("for(var i=0;i<lineno;i++){");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+i");
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+i");
				out.println("m_relation=\"TXT_RELATIONSHIP\"+i");
				out.println("m_period=\"TXT_PERIOD\"+i");
				out.println("m_tel=\"TXT_TEL_NO\"+i");
				
				out.println("if(document.Form1.elements[m_gur_code].value!=\"\" || document.Form1.elements[m_gur_name].value!=\"\" || document.Form1.elements[m_relation].value!=\"\" || document.Form1.elements[m_period].value!=\"\") {");
				
				out.println("if(document.Form1.elements[m_gur_code].value==\"\"){");
				out.println("alert('Guarantor code cannot be null.');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_gur_name].value==\"\") {");
				out.println("alert('Guarantor name cannot be null.');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_relation].value==\"\") {");
				out.println("alert('Relationship cannot be null.');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_period].value==\"\") {");
				out.println("alert('Period cannot be null.');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_tel].value==\"\") {");
				out.println("alert('Telephone No cannot be null.');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");
				
				out.println("}");
				
				
				out.println("}");
				
				out.println("}");
				
				out.println("if(b_flag==1){");
				out.println("return false;");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("return true;");
				out.println("}");
				
				//out.println("alert('value is' +b_flag);");
				
				out.println("}");
				
				
				out.println("function validate_inv_valuations(){ ");
				out.println("  if(document.Form1.chk_complete.checked==true){");
				out.println("    assignState('M_VAL');"); 
				out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
				out.println("}");
				out.println("}");
				
				/*----------------------------------------------------------------
				Purpose  : Add New gurantor
		
			----------------------------------------------------------------*/			
				out.println("function add_row(){"); 
				out.println("b_flag=0;");
				out.println("if(lineno!=0){");
				out.println("count=lineno-1;");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+count");
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+count");
				out.println("m_relation=\"TXT_RELATIONSHIP\"+count");
				out.println("m_period=\"TXT_PERIOD\"+count");
				out.println("if(document.Form1.elements[m_gur_code].value==\"\") {");
				out.println("alert('Guarantor code cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_gur_name].value==\"\") {");
				out.println("alert('Guarantor name cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_relation].value==\"\") {");
				out.println("alert('Relationship cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[m_period].value==\"\") {");
				out.println("alert('Period cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				out.println("else{");
				out.println("b_count=0;");
				out.println("tmp_code=document.Form1.elements[m_gur_code].value;");
				out.println("for(var i=0;i<lineno-1;i++){");
				out.println("m_tmp_code=\"TXT_GAURANTOR_CODE\"+i");
				out.println("if(lineno>=2){");
				out.println("if(document.Form1.elements[m_tmp_code].value==tmp_code){");
				out.println("alert('Guarantor code cannot be duplicated.')");
				out.println("b_flag=1;");
				out.println("b_count=1};");
				out.println("if(b_count==1){");
				out.println("break;}");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("if(b_flag==0){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=0 class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+lineno+' maxlength=\"50\" size=\"50\" style=\"width:130px;\" onblur=\"validate_gur_code('+lineno+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+lineno+' value=\"...\" onClick=\"help_button_5('+lineno+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+lineno+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+lineno+'  style=\"width:180px;\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+lineno+' style=\"width:130px;\" maxlength=\"50\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+lineno+' style=\"{text-align:right; width:80;}\" maxlength=\"2\" size=\"10\" onblur=\"val_num('+lineno+')\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+lineno+' style=\"{ width:130;}\" maxlength=\"60\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+lineno+' style=\"{width:105;}\" maxlength=\"10\" size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+lineno+'   value=\" X \" onClick=\"del_row('+lineno+')\">'+");
				out.println("'</td></tr></table>';");
				
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
				out.println("}");
				
				out.println("disable_nic_no(arr_size)");
				out.println("}");
				
				
				
				/*----------------------------------------------------------------
				Purpose  : Delete Gurantor
		
			----------------------------------------------------------------*/			
				
				
				
				out.println("function del_row(rowNo){"); 
				out.println("if(arr_size!=1 ){");
				out.println("var j=0;");
				out.println("for(var i=0;i<arr_size;i++){");
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+i");
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+i");
				out.println("m_relation=\"TXT_RELATIONSHIP\"+i");
				out.println("m_period=\"TXT_PERIOD\"+i");
				out.println("m_tel_no=\"TXT_TEL_NO\"+i");
				out.println("m_nic_reg_no=\"TXT_NIC_REG_NO\"+i");
				out.println("if(i==rowNo)");
				out.println("continue;");
				out.println("array_gur_code[j]=document.Form1.elements[m_gur_code].value;");
				out.println("array_gur_name[j]=document.Form1.elements[m_gur_code].value;");
				out.println("array_relation[j]=document.Form1.elements[m_relation].value;");
				out.println("array_period[j]=document.Form1.elements[m_period].value;");
				out.println("array_tel_no[j]=document.Form1.elements[m_tel_no].value;");
				out.println("array_nic_reg_no[j]=document.Form1.elements[m_nic_reg_no].value;");
				out.println("j=j+1;");
				out.println("}");
				out.println("lineno=lineno-1;");
				out.println("arr_size=arr_size-1;");
				out.println("write_data(arr_size);");
				out.println("}"); 
				out.println("}"); 
				out.println("function write_data(size){");
				out.println("m_table.innerHTML=\"\";");
				out.println("header();");
				out.println(" for(var j=0;j<size;j++){");
				out.println("if(array_gur_code[j]==\"\" && array_gur_name[j]==\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"\" style=\"width:130px;\" maxlength=\"50\" size=\"50\" onblur=\"validate_gur_code('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+j+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:180px;\" value=\"\" maxlength=\"250\" size=\"10\" disabled></td>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" style=\"width:130px;\" value=\"\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:80;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"\" style=\"width:130px;\" size=\"10\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" style=\"width:105px;\" value=\"\" size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("continue;");
				out.println("}");
				out.println("else if(array_relation[j]==\"\" && array_period[j]==\"\" ){");
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value='+array_gur_code[j]+' maxlength=\"50\" size=\"50\" style=\"width:130px;\" onblur=\"validate_gur_code('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+j+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:180px;\" value='+array_gur_name[j]+' style=\"width:180px;\" maxlength=\"250\" size=\"10\" disabled></td>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" style=\"width:130px;\" value=\"\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:80;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" style=\"width:130px;\" value='+array_tel_no[j]+' size=\"10\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' style=\"width:105px;\" maxlength=\"10\" value='+array_nic_reg_no[j]+' size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("continue;");
				out.println("}");
				out.println("else{");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value='+array_gur_code[j]+' maxlength=\"50\" style=\"width:130px;\" size=\"50\" onblur=\"validate_gur_code('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
				out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor('+j+')\"></TD>'+"); 
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:180px;\" value='+array_gur_name[j]+' maxlength=\"250\" size=\"10\" disabled></td>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' style=\"width:130px;\" maxlength=\"50\" value='+array_relation[j]+' size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:80;}\" maxlength=\"2\" value='+array_period[j]+' size=\"10\" onblur=\"val_num('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" style=\"width:130px;\" value='+array_tel_no[j]+' size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" style=\"width:105px;\" value='+array_nic_reg_no[j]+' size=\"10\">'+");
				out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("}");
				out.println("}");
				out.println("disable_nic_no(arr_size)");
				out.println("}");		
				out.println("");
				
				out.println("function validate_data() {"); 
				out.println("	// Validations Goes Here");
				out.println("	");
				
				// Added By Samitha Kulatilaka On 2009-10-19 (Making The DIV Tags To 'Black')
				out.println("	DIV_TXT_APPLICANT_CODE.style.color = 'black';");
				out.println("	DIV_MKT_OFFICER.style.color = 'black';");
				out.println("	DIV_INS_OFFICER.style.color = 'black';");
				//out.println("	DIV_TXT_LOCATION_CODE.style.color = 'black';"); // commented by udara 17-03-2014
				out.println("	");
				out.println("	var return_value = true;");
				out.println("	");
				out.println("	if(document.Form1.TXT_APPLICANT_CODE.value == \"\") {"); 
				out.println("		DIV_TXT_APPLICANT_CODE.style.color = 'red';");
				out.println("		return_value = false;");
				out.println("	}"); 
				out.println("	if(document.Form1.MKT_OFFICER.value == \"\") {"); //Added by Sandun on 25-08-2008
				out.println("		DIV_MKT_OFFICER.style.color = 'red';");
				out.println("		return_value = false;");
				out.println("	}");
				out.println("	if(document.Form1.INSURANCE_OFFICER.value == \"\") {"); //Added by Nuwan on 
				out.println("		DIV_INS_OFFICER.style.color = 'red';");
				out.println("		return_value = false;");
				out.println("	}");
				out.println("	if(document.Form1.TXT_LOCATION_CODE.value == \"\") {"); //Added by Sandun on 25-08-2008
				out.println("		DIV_TXT_LOCATION_CODE.style.color = 'red';");
				out.println("		return_value = false;");
				out.println("	}");
				
				// added by udara 23-07-2014
				//out.println("   alert(document.Form1.LEAD_SOURCE_CATEGORY.value);   ");
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value == \"BROKER\") {"); //Added by Sandun on 25-08-2008
				//out.println("       alert('Inside LEAD_SOURCE_CATEGORY');   ");
				out.println("	    if(document.Form1.LEAD_SOURCE_NAME.value==''){ ");
				//out.println("           alert('Inside DIV_TXT_LEAD_SOURCE_NAME');   ");
				out.println("			DIV_TXT_LEAD_SOURCE_NAME.style.color = 'red';");
				out.println("			return_value = false;");
				out.println("		}");
				out.println("	}");
				// end by udara 23-07-2014
				
				// added by udara 24-07-2014
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value =='TEST') {"); 
				out.println("      if(document.Form1.TXT_FINANCE.value==''){  ");
				out.println("			DIV_TXT_FINANCE.style.color = 'red';");
				out.println("			return_value = false;");
				out.println("	   }");
				out.println("	}");
				// end by udara 24-07-2014
				
				// commented by udara 30-01-2018
				
				// released by udara 14-02-2019
				// added by udara 22-10-2018
				out.println("	if(document.Form1.TXT_TR_TYPE.value =='LOANS') {"); 
				out.println("      if(document.Form1.TXT_PLEDGE_CONTRACT.value==''){  ");
				out.println("			pledge_label.style.color = 'red';");
				out.println("			return_value = false;");
				out.println("	   }");
				out.println("	}");
				// end by udara 22-10-2018

				//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("	if(document.Form1.TXT_TR_TYPE.value =='HADAGASMA') {"); 
				out.println("      if(document.Form1.TXT_VENDOR_CODE.value==''){  ");
				out.println("			vendor_label.style.color = 'red';");
				out.println("			return_value = false;");
				out.println("	   }");
				
				// added by udara 02-12-2024
				out.println("      if(document.Form1.TXT_PLEDGE_CONTRACT.value==''){  ");
				out.println("			pledge_label.style.color = 'red';");
				out.println("			return_value = false;");
				out.println("	   }");
				// end by by udara 02-12-2024
				
				
				out.println("	}");
				//end by kasun on 2024.11.22 for JB16102024-25600
				
				
				
				out.println("	");
				out.println("	return return_value;");
				out.println("	");
				out.println("}"); 
				out.println("");
				
				out.println("function before_submit(){ "); 
				//out.println("alert('chk');");
				//------------------ added by SH on 16-04-2008 for Loans ###L
				out.println("	if(document.Form1.TXT_TR_TYPE.value=='LOANS' && document.Form1.chk_complete.checked==true){ ");
				out.println("		validate_inv_valuations();");
				out.println("	}");
				//------------------
				
				// added by udara 14-02-2019
				out.println("	if(document.Form1.TXT_TR_TYPE.value=='PER_LOANS' && document.Form1.chk_complete.checked==true){ ");
				out.println("		validate_inv_valuations();");
				out.println("	}");
				// end by udara 14-02-2019
				
				// added by udara 02-12-2024
				out.println("	if(document.Form1.TXT_TR_TYPE.value=='HADAGASMA' && document.Form1.chk_complete.checked==true){ ");
				out.println("		validate_inv_valuations();");
				out.println("	}");
				// end by udara 0212-2024
				
				out.println("	m_option = document.Form1.hid_status.value;"); 
				out.println("	if(m_option=='New') {");
				out.println("		m_sav_msg = 'Are you sure you want to Save?'; ");
				out.println("	}"); 
				out.println("	else if(m_option=='Edit') {");
				out.println("		m_sav_msg = 'Are you sure you want to Modify?'; ");
				out.println("	}"); 
				out.println("	else if(m_option=='Delete') {");
				out.println("		m_sav_msg = 'Are you sure you want to Delete?'; ");
				out.println("	}"); 
				out.println("	if(validate_data()){");
				
				/*
				out.println("	for (var i=0; i < document.Form1.elements.length; i++ ) {");
				// Line Commented By Samitha Kulatilaka On 2009-10-16
				out.println("      document.Form1.elements[i].disabled=false;");
				out.println("	}");
				*/
				
				//out.println("   chk_data();");
				
				//out.println("   if(b_flag==0)");
				
				out.println("		if(chk_data()){");
				
				//out.println("   if(b_inv_val==0){");
				//moddifed by nuwan de silva
				out.println("			if(b_inv_val==1){");
				out.println("				alert(\"'Registration Number' and Engine Number' and 'Chassis Number' not match in 'Proforma Invoice' and 'Valuation'\"); ");
				out.println("			}");
				//------------------------ added by SH on 16-04-2008 for Loans ###L 
				//out.println("		if (parseFloat(document.Form1.HID_INV_PR_CO.value)==0){");
				//out.println("		 if (parseFloat(document.Form1.HID_PR_CO.value)!=1){");
				//out.println("		 }else{");
				//----------------------- end ###L
				
				out.println("			if(confirm(m_sav_msg)){ "); 
				
				// added by udara 22-01-2015
				out.println("	for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("      document.Form1.elements[i].disabled=false;");
				out.println("	}");
				// end by udara 22-01-2015
				
				//out.println(" alert('Test 1'); ");
				
				out.println("				document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				//out.println(" alert('Test 2'); ");
				out.println("				document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");//Added By Nuwan De Silva
				//out.println(" alert('Test 3'); ");
				out.println("document.Form1.TXT_INQUARY_NO.disabled = false;"); // Added By Samitha Kulatilaka
				//out.println(" alert('Test 4'); ");
				//out.println(" alert(document.Form1.hid_no_rec.value); ");
				
				out.println("document.Form1.save_button_1.disabled = true;"); // added by udara 13-07-2017
				out.println("document.Form1.save_button_2.disabled = true;"); // added by udara 13-07-2017
				
				out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Application_Process?hid_count='+document.Form1.hid_count.value+'';");  
				out.println("				document.Form1.submit();"); 
				//out.println(" alert('Test 5'); ");
				out.println("			}"); 
				//------------------------ added by SH on 16-04-2008 for Loans ###L 
				//out.println("		 }"); 
				//out.println("		}else{"); 
				//out.println("		 alert('2');");
				//out.println("		 if(confirm(m_sav_msg)){ "); 
				//out.println("     document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				//out.println("     document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");//Added By Nuwan De Silva
				//out.println("		  document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Application_Process?hid_count='+document.Form1.hid_count.value+'';");  
				//out.println("		  document.Form1.submit();	"); 
				//out.println("		 }"); 
				
				//out.println("		}"); 
				//-------------------- end ###L
				
				//out.println("		}"); 
				//out.println("		else { "); 
				//out.println("		alert(\"'Engine Number' and 'Chassis Number' not match in 'Proforma Invoice' and 'Valuation'\"); ");
				//out.println("		}");
				out.println("		}");
				out.println("	}"); 
				out.println("	else { "); 
				out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("	}");
				
				
				
				out.println("}"); 
				
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_Application_Process\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Marketing - Application Process - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
					"        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE),'-') "+
					" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE  USER_ID = '"+m_username+"' ");
				boolean more2 = rs1.next(); 
				if(more2){
					out.println("   document.Form1.TXT_LOCATION_CODE.value= '"+rs1.getString(5)+"' "); 
					out.println("   document.Form1.TXT_LOCATION_DESC.value= '"+rs1.getString(6)+"' "); 
				}
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Marketing - Application Process - \"+document.Form1.hid_status.value;"); 
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
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Max+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println(" var m_url = '"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Max+'&Args=';	");  //m_help_TXT_CLIENT_CODE
				out.println("popupwin=window.showModalDialog(m_url, oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount,Max);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);");
				out.println("}		");
				out.println("else if(oBj.valout[1] == 'Close'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(oBj);"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_4(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("		help_value_assign_6(oBj);"); 
				out.println("}");
				
				out.println("else if(IfCount=='9'){"); 
				out.println("		help_value_assign_location(oBj);"); 
				out.println("}");
				
				out.println("else if(IfCount=='8'){"); 
				out.println("		help_value_assign_8(oBj);"); 
				out.println("}");	
				
				out.println("else if(IfCount=='10'){"); 
				out.println("		brk_assign(oBj);"); 
				out.println("}");	
				
				out.println("else if(IfCount=='11'){"); 
				out.println("		mk_officer_assign(oBj);"); 
				out.println("}");		
				out.println("else if(IfCount=='12'){"); //added by nuwan de silva
				out.println("		ins_officer_assign(oBj);"); 
				out.println("}");	
				out.println("else if(IfCount=='13'){");
				out.println("		finance_no_assign(oBj);"); 
				out.println("}");
			
				// added by udara 09-10-2018
				out.println("else if(IfCount=='14'){");
				out.println("		pledge_contract_assign(oBj);"); 
				out.println("}");
				// end by udara 09-10-2018

				out.println("else if(IfCount=='15'){");
				out.println("		vendor_code_assign(oBj);"); //added by kasun on 2024.11.25 for JB16102024-25600
				out.println("}");
				
				
				out.println("	}"); 
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount,Max){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount,Max){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function clear_fields(){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"99\") {" ); 
				out.println("    document.Form1.TXT_APPLICATION_NO.value =''; ");
				out.println("   }		"); 
				out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
				out.println("    document.Form1.TXT_APPLICANT_CODE.value =''; ");
				out.println("  }		"); 
				out.println("	 else	if(document.Form1.hid_help_type.value==\"6\") {" ); 
				out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value =''; ");
				out.println("  }		"); 
				out.println("		else if(document.Form1.hid_help_type.value==\"8\") {" ); 
				out.println("    document.Form1.elements[\"TXT_ISSUER_CODE\"+document.Form1.hid_row.value].value =''; ");
				out.println("   }		"); 
				
				out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" );  //added by nuwan de silva 27-06-07
				out.println("    document.Form1.TXT_INQUARY_NO.value =''; ");
				out.println("  }		"); 
				
				
				
				out.println("	 else	if(document.Form1.hid_help_type.value==\"9\") {" );  //added by nuwan de silva 27-06-07
				out.println("    document.Form1.TXT_LOCATION_CODE.value =''; ");
				out.println("  }		"); 
				//Commented by Dineth on 2009-01-16
				// Else If Block Uncommented By Samitha Kulatilaka On 2009-10-15
				out.println("	 else	if(document.Form1.hid_help_type.value==\"11\") {" );  //added by Chandana 29-11-07
				out.println("    document.Form1.INSURANCE_OFFICER.value =''; ");
				out.println("  }		");
				//Modified by Dineth on 2009-01-16
				out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" );  //added by Chandana 29-11-07
				out.println("    document.Form1.MKT_OFFICER.value =''; ");
				out.println("  }		");
				
				out.println("	 else	if(document.Form1.hid_help_type.value==\"55\") {" );  //added by thamali 29-11-07
				out.println("    document.Form1.LEAD_SOURCE_CODE.value =''; ");
				out.println("    document.Form1.LEAD_SOURCE_NAME.value =''; ");
				out.println("  }		");
				
				out.println("	 else	if(document.Form1.hid_help_type.value==\"13\") {" );  //added by ishani
				out.println("    document.Form1.TXT_FINANCE.value =''; ");
				
				out.println("  }		"); 
				//End by Dineth on 2009-01-16
				// Else If Block Added By Samitha Kulatilaka On 2009-10-15
				out.println("	 else	if(document.Form1.hid_help_type.value==\"80\") {" );
				//out.println("    document.Form1.TXT_GAURANTOR_CODE.value =''; ");
				out.println("  }		");
				
				// added by udara 09-10-2018
				out.println("	 else	if(document.Form1.hid_help_type.value==\"14\") {" );  
				out.println("        document.Form1.TXT_PLEDGE_CONTRACT.value =''; ");
				out.println("    }		");
				// end by udara 09-10-2018

				out.println("	 else	if(document.Form1.hid_help_type.value==\"15\") {" );  //added by kasun on 2024.11.25 for JB16102024-25600
				out.println("        document.Form1.TXT_VENDOR_CODE.value =''; ");
				out.println("    }		");
				
				
				out.println("}		"); 					
				
				out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_CLIENT_CODE\";"); 
				out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_1(oBj) {"); 
				out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
				//	out.println("    document.Form1.TXT_CONTRACT NUMBER.value=oBj.valout[30];"); 
				out.println("}"); 
				
				out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
				out.println("    Crit =document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"@\"+document.Form1.elements[m_gur_code].value+\"@Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_2(oBj) {"); 
				out.println("    document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
				out.println("function help_button_7(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    Crit = document.Form1.TXT_INQUARY_NO.value+\"@Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function help_button_3(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    m_username = '"+m_username+"' ;"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    Crit = document.Form1.TXT_INQUARY_NO.value+\"@Y@\"+m_username+\"@\" ;"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_3(oBj) {"); 
				out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[2];"); 
				
				
				//modified by nuwan de silva 25-06-07-----------------------------------------
				out.println("if(oBj.valout[28]=='null' || oBj.valout[28]=='-' ){"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value='';"); 
				out.println("    document.Form1.TXT_LOCATION_DESC.value='';"); 
				out.println("}"); 
				
				out.println("else {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[28];"); 
				out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[29];"); 
				out.println("}"); 
				//------------------------------------------------------------------------------
				
				//added by nuwan de silva 25-06-07_________________________________
				out.println("if(oBj.valout[13]=='null' || oBj.valout[13]=='-' ){"); 
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value='';"); 
				out.println("}"); 
				out.println("else {"); 
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value=oBj.valout[13];"); 
				out.println("}"); 
				
				out.println("if(oBj.valout[14]=='null' || oBj.valout[14]=='-' ){"); 
				out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[14];"); 
				//out.println("    document.Form1.LEAD_SOURCE_CODE.value=oBj.valout[14];"); // thamali // not needed inquiry 
				out.println("}"); 
				out.println("else {"); 
				out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[14];"); 
				//out.println("    document.Form1.LEAD_SOURCE_CODE.value=oBj.valout[14];"); // thamali // not needed inquiry
				out.println("}"); 
				//end  by nuwan de silva 25-06-07___________________________________
				
				out.println("}"); 
				
				
				out.println("function help_button_4(Start,End,Hid_No,Sql,IfCount) {"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_4(oBj) {"); 
				out.println("}"); 
				
				
				
				out.println("function help_button_6(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    Crit =document.Form1.TXT_CORE_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.elements[m_gur_code].value+\"@Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_6(oBj) {"); 
				out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_CORE_APPLICANT_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
				//added by nuwan de silva 25-06-07------------------------------------------------
				out.println("function help_button_location(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    document.Form1.hid_help_type.value=\"9\";"); 
				out.println("    Crit = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_location() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[3];"); 
				
				out.println("}"); 
				//---------------------------------------------------------------------------------
				
				out.println("function brk_help(Start,End,Hid_No,Sql,IfCount,Max){");
				out.println("    document.Form1.hid_help_type.value=\"55\";"); // tham 
				// commented by udara 29-10-2014
				/*
				out.println("if(document.Form1.LEAD_SOURCE_CODE.value==\"\"){");
				out.println("Crit=document.Form1.LEAD_SOURCE_NAME.value+\"@\";");
				out.println("}");
				out.println("else{");	
				out.println("Crit=document.Form1.LEAD_SOURCE_CODE.value+\"@\";");
				out.println("}");	
				*/
				
				out.println("Crit=document.Form1.LEAD_SOURCE_NAME.value+\"@\";"); // added by udara 29-10-2014 // commented by udara 08-04-2021
				//out.println("Crit=document.Form1.LEAD_SOURCE_CODE.value+\"@\";"); // added by udara 08-04-2021 // re-enabled by udara 11-05-2021
				
				//out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				out.println("}");	
				
				
				out.println("function brk_assign(oBj){");
				//out.println(" document.Form1.LEAD_SOURCE_NAME.value =oBj.valout[3]+\" \"+oBj.valout[4]"); // THAMALI //oBj.valout[3]+\" \"+oBj.valout[4] // commented by udara 11-05-2021
				out.println(" document.Form1.LEAD_SOURCE_NAME.value = oBj.valout[2]"); // added by udara 11-05-2021
				out.println(" document.Form1.LEAD_SOURCE_CODE.value =oBj.valout[2]");
				out.println(" document.Form1.LEAD_SOURCE_NAME.focus(); "); // added by udara 08-04-2021
				out.println("}");
				
				
				
				out.println("function help_button_5(rowNo) {"); 
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
				// Modified By Samitha Kulatilaka On 2009-10-15 (Changed 'hid_help_type.value' From '5' To '80')
				out.println("    document.Form1.hid_help_type.value=\"80\";"); 
				out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				out.println("   Sql = \"m_help_TXT_CLIENT_CODE\";"); 
				out.println("    Crit =document.Form1.elements[m_gur_code].value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','2',Crit,Sql,'5','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_5(rowNo,oBj) {"); 
				out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
				out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+rowNo;");	
				out.println("m_tel_no=\"TXT_TEL_NO\"+rowNo;");
				out.println("m_nic_reg_no=\"TXT_NIC_REG_NO\"+rowNo;");	
				out.println("    document.Form1.elements[m_gur_code].value=oBj.valout[2];"); 
				out.println("    document.Form1.elements[m_gur_name].value=oBj.valout[3];"); 
				out.println("    document.Form1.elements[m_tel_no].value=oBj.valout[4];"); 
				out.println("    document.Form1.elements[m_nic_reg_no].value=oBj.valout[5];"); 
				out.println("");
				
				out.println(" document.Form1.elements[m_gur_code].focus();  "); // added by udara 06-01-2013
				
				
				out.println("}"); 
				
				out.println("function help_update(Start,End,Hid_No,Sql,IfCount,Max) {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ENT_CON@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
				//out.println("    HelpBox('1','10','10',Crit,'m_help_TXT_APPLICATION_NO','99');"); 
				
				out.println("}"); 
				
				out.println("function help_update_value_assign_99(oBj) {"); 
				
				//out.println(" alert(oBj.valout[33]); ");
				
				out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
				out.println("if(oBj.valout[3]=='' || oBj.valout[3]=='null' ){");
				out.println("    document.Form1.TXT_FACILITY_NO.value='-';"); 
				out.println("}");
				out.println("else{");
				out.println("    document.Form1.TXT_FACILITY_NO.value=oBj.valout[3];"); 
				out.println("}");
				out.println("    document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[6];"); 
				out.println("    document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[4];"); 
				out.println("if(oBj.valout[3]=='' || oBj.valout[3]=='null' ){");
				out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value='-';"); 
				out.println("}");
				out.println("else{");
				out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value=oBj.valout[7];"); 
				out.println("}");
				
				
				out.println("    document.Form1.TXT_CORE_APPLICANT_NAME.value=oBj.valout[8];"); 
				out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[9];"); 
				out.println("    document.Form1.TXT_TR_TYPE.value=oBj.valout[11];"); 
				out.println("    document.Form1.TXT_INSURANCE_DONE.value=oBj.valout[12];"); 
				out.println("    document.Form1.TXT_PRIORITY.value=oBj.valout[13];"); 
				out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[17];");  //ADDED BY CHANDANA ON 09/05/2007
				
				out.println("    document.Form1.INSURANCE_OFFICER.value=oBj.valout[30];"); // Added By Samitha Kulatilaka On 2009-10-20
				out.println("    document.Form1.TXT_CONTRACT_NUMBER.value=oBj.valout[32];"); // Added By Prabash On 2012-07-13
				
				
				out.println("if(oBj.valout[18]=='-' || oBj.valout[18]=='null' ){");
				out.println("    document.Form1.TXT_LOCATION_CODE.value='';");  //ADDED BY CHANDANA ON 25/06/2007
				out.println("    document.Form1.TXT_LOCATION_DESC.value='';");  //ADDED BY CHANDANA ON 25/06/2007
				out.println("}");
				out.println("else{");
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[18];");  //ADDED BY CHANDANA ON 25/06/2007
				out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[19];");  //ADDED BY CHANDANA ON 25/06/2007
				out.println("}");
				
				
				//added by nuwan de silva 25-06-07_________________________________
				out.println("if(oBj.valout[26]=='null' || oBj.valout[26]=='-' ){"); 
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value='N/A';"); 
				out.println("}"); 
				out.println("else {"); 
				out.println("    document.Form1.LEAD_SOURCE_CATEGORY.value=oBj.valout[26];"); 
				out.println("}"); 
				
				out.println("if(oBj.valout[27]=='null' || oBj.valout[27]=='-' ){"); 
				//out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[33];"); // commented by udara 11-05-2021
				out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[27];"); // added by udara 11-05-2021 
				out.println("    document.Form1.LEAD_SOURCE_CODE.value=oBj.valout[27];");
				out.println("}"); 
				out.println("else {"); 
				//out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[33];");  // commented by udara 11-05-2021
				out.println("    document.Form1.LEAD_SOURCE_NAME.value=oBj.valout[27];"); // added by udara 11-05-2021 
				out.println("    document.Form1.LEAD_SOURCE_CODE.value=oBj.valout[27];");
				out.println("}"); 
				
				
				// added by udara 07-05-2014
				out.println("	if(document.Form1.LEAD_SOURCE_CATEGORY.value=='TEST'){");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				out.println("   }");
				
				// added by udara 24-10-2014
				out.println("	else if(document.Form1.LEAD_SOURCE_CATEGORY.value=='DIRECT'){");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	   document.Form1.inqu_help.disabled = true;"); 
				out.println("   }");
				// end by udara 24-10-2014
				
				out.println("   else{");
				out.println("	   document.Form1.LEAD_SOURCE_NAME.disabled = false;");
				out.println("	   document.Form1.inqu_help.disabled = false;"); 
				out.println("   }");
				// end by udara 07-05-2014
				
				
				//added by nuwan de silva on 27-11-2007
				out.println("if(oBj.valout[28]=='null' || oBj.valout[28]=='-' ){"); 
				out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[28];"); 
				out.println("}"); 
				out.println("else {"); 
				out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[28];"); 
				out.println("}"); 
				//end  by nuwan de silva 25-06-07___________________________________
				
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[29];"); 
				
				out.println("    if(oBj.valout[23]!='-'){");
				out.println("      document.Form1.hid_term_type.value=oBj.valout[23];"); 
				out.println("      document.Form1.hid_term_no.value=oBj.valout[21];"); 
				out.println("      document.Form1.hid_pre_app_no.value=oBj.valout[22];");
				out.println("      document.Form1.hid_term_amt.value=oBj.valout[24];");
				out.println("      TERM_TYPE.innerHTML=oBj.valout[20];         ");
				
				//out.println("      TERM_DET.innerHTML=oBj.valout[22]+'/'+oBj.valout[25]+'/'+format_noobject(oBj.valout[24]);");
				
				// added by udara 01-01-2016
				out.println("       if(oBj.valout[20] == 'Balance Transfer')  ");
				out.println("           TERM_DET.innerHTML=oBj.valout[22]+'/'+oBj.valout[25]+'/'+format_noobject(oBj.valout[36]);");
				out.println("       else  ");
				out.println("           TERM_DET.innerHTML=oBj.valout[22]+'/'+oBj.valout[25]+'/'+format_noobject(oBj.valout[24]);");
				// end by udara 01-01-2016
				
				out.println("    }else{");
				out.println("      document.Form1.hid_term_type.value='';"); 
				out.println("      document.Form1.hid_term_no.value='';"); 
				out.println("      document.Form1.hid_pre_app_no.value='';");
				out.println("      document.Form1.hid_term_amt.value='';");
				out.println("      TERM_TYPE.innerHTML='';         ");
				out.println("      TERM_DET.innerHTML='';         ");
				
				out.println("    }");
				
				// commented by udara 17-07-2017
				/*
				out.println("    assignState('M7');"); 
				out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
				*/
				
				out.println("    document.Form1.INVOICE_LINK_BUT.disabled=false;");
				out.println("    document.Form1.ASSET_LINK_BUT.disabled=false;");
				out.println("    document.Form1.VALUATION_LINK_BUT.disabled=false;");
				out.println("    document.Form1.hid_client_type.value=oBj.valout[10];"); 
				out.println("    enable_links(); ");
				
				//out.println("    document.Form1.TXT_APPLICATION_NO.focus(); "); // added by udara 07-05-2014
				
				// added by udara 07-05-2014
				out.println("if(document.Form1.LEAD_SOURCE_CATEGORY.value=='TEST'){");
				//out.println("    alert('alert 1');  ");
				out.println("    empty_lead_source_name_2(); "); // commented by udara 28-05-2014
				out.println("    get_finance_amount(0);  ");   // commented by udara 28-05-2014
				
				
				out.println("	 document.Form1.LEAD_SOURCE_NAME.disabled = true;");
				out.println("	 document.Form1.inqu_help.disabled = true;"); 
				
				out.println("    document.Form1.TXT_FINANCE.value     = oBj.valout[34];");
				out.println("    document.Form1.TXT_FINANCE_AMT.value = oBj.valout[35];"); 				
				
				out.println("}");
				// added by udara 07-05-2014
				
				out.println("    document.Form1.TXT_APPLICATION_NO.focus(); "); // added by udara 13-07-2017
				
				
				//out.println(" assignState('M1'); "); // added by udara 17-07-2017
				//out.println(" makeRequest(document.Form1.TXT_APPLICATION_NO); "); // added by udara 17-07-2017
				
				// added by udara 09-10-2018
				out.println(" set_pledge_div(document.Form1.SCREEN_NAME.value,document.Form1.TXT_TR_TYPE.value);  ");
				out.println(" document.Form1.TXT_PLEDGE_CONTRACT.value = oBj.valout[37]; ");
				// end by udara 09-10-2018

				out.println(" document.Form1.TXT_VENDOR_CODE.value = oBj.valout[38]; ");// added by kasun on 26-11-2024
				
				out.println("}"); 
				
				out.println("function disable_app_no(){")			;
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
				out.println("}");
				
				//comment by nuwan de silva on 15-11-2007-------
				/*out.println("function load_invoice(){");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&APP_NAME='+document.Form1.TXT_APPLICANT_NAME.value+'';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'';");  //modified by nuwan de silva 07-08-07
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
					out.println("}");
				*/
				
				//added by nuwan de silva on 15-11-2007_________________________
				out.println("function load_invoice(count_asset,count_invoice){");
				
				// added by udara 09-05-2014
				out.println(" var m_refin_status = 'NO'; ");
				out.println(" var m_refin_no   = ''; ");
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value=='TEST'){ ");
				out.println(" 	if(document.Form1.TXT_FINANCE.value!=''){	 ");
				out.println("       m_refin_status = 'YES'; ");
				out.println("       m_refin_no   = document.Form1.TXT_FINANCE.value; ");
				out.println(" 	} ");
				out.println(" 	else{ ");
				out.println("       m_refin_status = 'NO'; ");
				out.println(" 	} ");
				out.println(" } ");
				out.println(" else{ ");
				out.println("      m_refin_status = 'NO'; ");
				out.println(" } ");
				//out.println(" alert(m_refin_status);  ");
				// end by udara 09-05-2014
				
				//out.println("alert('count_asset'+count_asset);");
				//out.println("alert('count_invoice'+count_invoice);");
				out.println("if(parseInt(count_asset)>1  &&  parseInt(count_invoice)==0 ) {"); //&&  parseInt(count_invoice)==0
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=NEW&application_no='+document.Form1.TXT_APPLICATION_NO.value+'&trn_type='+document.Form1.TXT_TR_TYPE.value;"); // commented by udara 0905-2014 //----ADDED BY SH ON 18-04-2008 FOR LOANS ###L &trn_type='+document.Form1.TXT_TR_TYPE.value  //modified by nuwan de silva 07-08-07
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=NEW&application_no='+document.Form1.TXT_APPLICATION_NO.value+'&trn_type='+document.Form1.TXT_TR_TYPE.value+\"&refin_no=\"+m_refin_no+\"&refin_status=\"+m_refin_status;"); // added by udara 09-05-2014
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=NEW&application_no='+document.Form1.TXT_APPLICATION_NO.value+'';");  //modified by nuwan de silva 07-08-07
				out.println("}");
				out.println("else {");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=EDIT&application_no='+document.Form1.TXT_APPLICATION_NO.value+'&trn_type='+document.Form1.TXT_TR_TYPE.value;"); // commented by udara 09-05-2014 //----ADDED BY SH ON 18-04-2008 FOR LOANS ###L &trn_type='+document.Form1.TXT_TR_TYPE.value  //modified by nuwan de silva 07-08-07
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=EDIT&application_no='+document.Form1.TXT_APPLICATION_NO.value+'&trn_type='+document.Form1.TXT_TR_TYPE.value+\"&refin_no=\"+m_refin_no+\"&refin_status=\"+m_refin_status;"); // added by udara 09-05-2014
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=EDIT&application_no='+document.Form1.TXT_APPLICATION_NO.value+'';");  //modified by nuwan de silva 07-08-07
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'';");  //modified by nuwan de silva 07-08-07
				out.println("}");
				
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 	
				out.println("}");
				
				
				out.println("function load_asset(){");
				
				// If Block Added By Samitha Kulatilaka On 2009-10-19
				out.println("	if(document.Form1.TXT_APPLICATION_NO.value == \"\") {");
				out.println("		document.Form1.TXT_APPLICATION_NO.focus();");
				out.println("		alert(\"Application No. cannot be null.\");");
				out.println("		return false;");
				out.println("	}");
				out.println("");
				
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva 27-06-07
				
				// added by udara 10-12-2014
				out.println(" var m_brk_status = 'NO'; "); 
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value == \"BROKER\"){ ");
				out.println("    var m_brk_status = 'YES'; "); 
				out.println(" } ");
				
				out.println(" var m_direct_status = 'NO'; ");
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value == \"DIRECT\"){ ");
				out.println("    var m_direct_status = 'YES'; "); 
				out.println(" } ");
				// end by udara 10-12-2014
				
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'';"); // commented by udara 10-12-2014 //modified by nuwan de silva 26-06-07
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'&brk_status='+m_brk_status+'&direct_status='+m_direct_status;"); // added by udara 10-12-2014
				
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				out.println("}");
				
				out.println("function load_valuation(){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_inspection_and_valuation_report?APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				out.println("}");
				
				out.println("function load_client(val){");
				// out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_code=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&client_type=\"+document.Form1.hid_client_type.value+\"&inquiry_no=\"+document.Form1.TXT_INQUARY_NO.value+\"&save_close=\"+val+\"&close_status=Y\";");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type=\"+document.Form1.hid_client_type.value+\"&inquiry_no=\"+document.Form1.TXT_INQUARY_NO.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&save_close=\"+val+\"&no_of_rec=\"+arr_size+\"&close_status=Y\";"); // commented by udara 27-08-2014 //Modified by Nuwan De Silva -- 14-05-07----
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type=\"+document.Form1.hid_client_type.value+\"&inquiry_no=\"+document.Form1.TXT_INQUARY_NO.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&save_close=\"+val+\"&no_of_rec=\"+arr_size+\"&close_status=Y\"+\"&app_screen=Y\";"); // added by udara 27-08-2014
				out.println("if(val==\"A\"){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type=\"+document.Form1.hid_client_type.value+\"&inquiry_no=\"+document.Form1.TXT_INQUARY_NO.value+\"&client_code=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&save_close=\"+val+\"&no_of_rec=\"+arr_size+\"&close_status=Y\"+\"&app_screen=APP_N\";"); // added by udara 27-08-2014
				out.println("}else{");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type=\"+document.Form1.hid_client_type.value+\"&inquiry_no=\"+document.Form1.TXT_INQUARY_NO.value+\"&client_code=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&save_close=\"+val+\"&no_of_rec=\"+arr_size+\"&close_status=Y\"+\"&app_screen=APP_N\";"); // added by udara 27-08-2014
				out.println("}");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				
				out.println("}");


				out.println("function vendor_create(){"); //added by kasun on 2024.11.25 for JB16102024-25600
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_vendor_creation?\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');");

				out.println("}");
				
				out.println("function load_guarantor(row_no){");
				//out.println("alert('row_no'+row_no);");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&ROW='+row_no+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value;"); // commented by udara 15-09-2014
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&ROW='+row_no+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&app_screen=Y';"); // added by udara 15-09-2014
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				out.println("}");
				
				out.println("function load_pricing(){");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Price?chksql=main_page&screen_type=WIN&app_no='+document.Form1.TXT_APPLICATION_NO.value+'&inquiry_no='+document.Form1.TXT_INQUARY_NO.value;"); 
				//out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				//------modified by : delanjali----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				//------date				: 2007-06-22----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'&TER_NO='+document.Form1.hid_term_no.value+'&TER_TYPE='+document.Form1.hid_term_type.value+'&TER_AMT='+document.Form1.hid_term_amt.value;");// commented by udara 23-07-2014
				
				// added by udara 23-07-2014
				out.println(" var m_brk_status = 'NO'; "); 
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value == \"BROKER\"){ ");
				out.println("    var m_brk_status = 'YES'; "); 
				out.println(" } ");
				
				out.println(" var m_direct_status = 'NO'; ");
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value == \"DIRECT\"){ ");
				out.println("    var m_direct_status = 'YES'; "); 
				out.println(" } ");
				
				out.println(" var m_transaction_type = document.Form1.TXT_TR_TYPE.value; "); // added by udara 30-10-2018
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'&TER_NO='+document.Form1.hid_term_no.value+'&TER_TYPE='+document.Form1.hid_term_type.value+'&TER_AMT='+document.Form1.hid_term_amt.value+'&brk_status='+m_brk_status;"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'&TER_NO='+document.Form1.hid_term_no.value+'&TER_TYPE='+document.Form1.hid_term_type.value+'&TER_AMT='+document.Form1.hid_term_amt.value+'&brk_status='+m_brk_status+'&direct_status='+m_direct_status;"); // commented by udara 30-10-2018
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&INQ_NO='+document.Form1.TXT_INQUARY_NO.value+'&TER_NO='+document.Form1.hid_term_no.value+'&TER_TYPE='+document.Form1.hid_term_type.value+'&TER_AMT='+document.Form1.hid_term_amt.value+'&brk_status='+m_brk_status+'&direct_status='+m_direct_status+'&transaction_type='+m_transaction_type;"); // added by udara 30-10-2018
				// end by udara 23-07-2014
				
				
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				out.println("}");
				
				out.println("function load_documents(){");
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&Hid_scr_name='+document.Form1.Hid_scr_name.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&TXT_TYPE='+document.Form1.TXT_TR_TYPE.value+'&CLIENT_CODE='+document.Form1.TXT_APPLICANT_CODE.value+'&CORE_APP_CODE='+document.Form1.TXT_CORE_APPLICANT_CODE.value+'&ac_status=Y&hid_records='+document.Form1.hid_no_rec.value;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1');"); 
				out.println("}");
				
				out.println("function load_Securities(){"); //added by nuwan de silva on 17-02-2010//add to lakdl by waruna 2012-04-24
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Securities_Required?chksql=main_page&Hid_scr_name='+document.Form1.Hid_scr_name.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&TXT_TYPE='+document.Form1.TXT_TR_TYPE.value+'&CLIENT_CODE='+document.Form1.TXT_APPLICANT_CODE.value+'&CORE_APP_CODE='+document.Form1.TXT_CORE_APPLICANT_CODE.value+'&ac_status=Y&hid_records='+document.Form1.hid_no_rec.value;"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1');"); 
				out.println("}");
				
				out.println("function enable_links(){");
				out.println(" document.Form1.ASSET_LINK_BUT.disabled=false;");
				out.println(" document.Form1.PRICING_LINK_BUT.disabled=false;");
				out.println(" document.Form1.INVOICE_LINK_BUT.disabled=false;");
				out.println(" document.Form1.VALUATION_LINK_BUT.disabled=false;");
				out.println(" document.Form1.DOCUMENTS_LINK_BUT.disabled=false;");
				out.println(" document.Form1.SECURITIES_LINK_BUT.disabled=false;"); //add by waruna 2012-04-24
				out.println("}");
				
				out.println("function disable_link(){");
				out.println("document.Form1.INVOICE_LINK_BUT.disabled=true;");
				out.println("document.Form1.ASSET_LINK_BUT.disabled=true;");
				out.println("document.Form1.VALUATION_LINK_BUT.disabled=true;");
				out.println("}");
				
				
				out.println("function display_status(data_vec){");
				out.println("if(data_vec[0]==\"Y\")");
				out.println(" document.Form1.CLIENT_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.CLIENT_LINK_BUT.disabled=true;");
				out.println("if(data_vec[1]=='Y')");
				out.println(" document.Form1.GUARANTOR_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.GUARANTOR_LINK_BUT.disabled=true;");
				out.println("if(data_vec[2]=='Y')");
				out.println(" document.Form1.ASSET_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.ASSET_LINK_BUT.disabled=true;");
				out.println("if(data_vec[3]=='Y')");
				out.println(" document.Form1.PRICING_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.PRICING_LINK_BUT.disabled=true;");
				out.println("if(data_vec[4]=='Y')");
				out.println(" document.Form1.INVOICE_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.INVOICE_LINK_BUT.disabled=true;");
				out.println("if(data_vec[5]=='Y')");
				out.println(" document.Form1.VALUATION_LINK_BUT.disabled=false;");
				out.println("else ");
				out.println(" document.Form1.VALUATION_LINK_BUT.disabled=true;");
				
				out.println("    assignState('J1');"); 
				out.println("    bank_guarntor(document.Form1.TXT_APPLICATION_NO);");
				
				out.println("}");
				
				out.println("function load_app_data(){");
				out.println(" application_no='"+m_application_no+"'");
				out.println(" if(application_no!='null'){"); 
				out.println(" load_roll_value('Edit')");
				out.println("   load_screen_status(\"EDIT\");"); 
				out.println("   document.Form1.TXT_APPLICATION_NO.value=application_no; "); 
				out.println("   assignState('M1');"); 
				out.println("   makeRequest(document.Form1.TXT_APPLICATION_NO);"); 
				out.println("   document.Form1.hid_close_sts.value='Y';"); 
				out.println("  }");
				out.println("}");
				
				out.println("function close_screen() {");
				out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");//Added By Nuwan De Silva
				out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
				out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		      window.close();"); 
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
				out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		      window.close();"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=main_page';");
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else { "); 
				out.println("		     close_window();"); 
				out.println("		}"); 
				out.println("}");
				
				
				
				out.println("function val_change() {");
				out.println("if(document.Form1.chk_complete.checked==true){");
				out.println("validate_inv_valuations();");
				out.println("}");
				out.println("}");
				
				
				/*	out.println("function check_amt(row) {");
					out.println("    nt=\"TXT_ISSUER_AMT\"+row;");
					out.println("if(document.Form1.elements[nt].value!=\"\"){");
					//out.println("format_number(document.Form1.elements[nt],20)");
					out.println("is_number_ok(document.Form1.elements[nt],20)");
					out.println("}");
					out.println("}");
			*/		
				
				
				out.println("function check_amt(obj,size){");
				out.println("if(obj.value!=''){"); 
				//out.println("if(isnumberok(obj,size)){"); //isnumberok isPosInteger
				//out.println("format_noobject_nodecimal1(obj)"); 
				out.println("format_number(obj,18)"); 
				//out.println("}"); 
				//out.println("else{");
				//out.println("alert('please enter a number');"); 
				//out.println("obj.value='0';"); 
				//out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}"); 
				
				
				out.println("function header1(){");
				out.println("if(is_lineno==0){");
				out.println("m_header='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");
				out.println("'<TR ><TD WIDTH=\"15%\" align=\"left\"><B>Issuer</B></TD>'+"); //class=\"pdn_txtpos2\" style=\"{padding-left:0px}\"
				out.println("'<TD WIDTH=\"6%\"     align=\"left\"><B>&nbsp</B></TD>' +");
				out.println("'<TD WIDTH=\"17%\"     align=\"right\"><B>Amount&nbsp;&nbsp;</B></TD>'+");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>Issue Date</B></TD>' +");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>Start Date</B></TD>' +");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>End Date</B></TD>' +");
				out.println("'<TD WIDTH=\"6%\"     align=\"left\"><B>&nbsp</B></TD>' +");
				out.println("'</TR></table>';");
				out.println("}");
				out.println("}");
				
				out.println("function arry_iscode(row){");
				out.println("assignState('J2')");
				out.println("document.Form1.hid_use.value=row");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_bank_1&data_val=\"+document.Form1.elements[\"TXT_ISSUER_CODE\"+row].value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function check_iss1(row){");
				out.println("if(row!=0){");			
				out.println("for(var b=0;b<=array_is_code1.length;b++){");
				out.println("if(array_is_code1[b]==document.Form1.elements[\"TXT_ISSUER_CODE\"+row].value){");
				out.println("alert('Issuer Code already entered')");	
				out.println("document.Form1.elements[\"TXT_ISSUER_CODE\"+row].value=\"\"");
				out.println("document.Form1.elements[\"TXT_ISSUER_CODE\"+row].focus()");
				out.println("is_flag1=1");
				out.println("return false");
				out.println("break");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("function check_iscode(val,row){");
				out.println("var row1=row");
				out.println("for(var d=0;d<row;d++){");
				out.println("if(row!=0){");
				out.println("row=row-1");
				out.println("}");
				out.println("if(val==document.Form1.elements[\"TXT_ISSUER_CODE\"+row].value){");
				out.println("is_flag1=1;");
				out.println("break");
				out.println("}");
				out.println("else{");
				out.println("is_flag1=0;");
				out.println("break");
				out.println("}");
				out.println("}");
				out.println("if(is_flag1==1){");
				out.println("alert('Issuer Code already entered')");	
				out.println("document.Form1.elements[\"TXT_ISSUER_CODE\"+row1].value=\"\"");
				out.println("document.Form1.elements[\"TXT_ISSUER_CODE\"+row1].focus()");
				out.println("return false");
				out.println("}");
				out.println("else{");
				out.println("return true");
				out.println("}");
				out.println("}");
				
				out.println("function check_iss(row){");
				out.println("for(var h=0;h<row;h++){");
				
				/*out.println("document.Form1.elements[\"TXT_ISSUER_CODE\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_AMT\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_DATE_DD\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_DATE_MM\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_DATE_YY\"+h].disabled=true");
				
				out.println("document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+h].disabled=true");
				
				out.println("document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+h].disabled=true");
				out.println("document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+h].disabled=true");
				*/
				
				
				out.println("array_is_code1[h]=document.Form1.elements[\"TXT_ISSUER_CODE\"+h].value;");
				out.println("}");
				out.println("}");
				
				
				out.println("function add_row1(){"); 
				out.println("is_lineno=parseInt(document.Form1.hid_count.value)");
				out.println("is_flag=0;");
				out.println("if(is_lineno!=0){");
				out.println("is_count=is_lineno-1;");
				out.println("if(document.Form1.elements[\"TXT_ISSUER_CODE\"+is_count].value==\"\") {");
				out.println("alert('Issuer code cannot be null.');");
				out.println("is_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[\"TXT_ISSUER_AMT\"+is_count].value==\"\" ) {");
				out.println("alert('Issue Amount name cannot be null.');");
				out.println("is_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[\"TXT_ISSUER_DATE_DD\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_DATE_MM\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_DATE_YY\"+is_count].value==\"\") {");
				out.println("alert('Issue Date name cannot be null.');");
				out.println("is_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+is_count].value==\"\") {");
				out.println("alert('Start Date name cannot be null.');");
				out.println("is_flag=1;");
				out.println("}");
				out.println("else if(document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+is_count].value==\"\" || document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+is_count].value==\"\") {");
				out.println("alert('End Date name cannot be null.');");
				out.println("is_flag=1;");
				out.println("}");
				out.println("else{");
				out.println("is_flag=0;");
				out.println("}");
				out.println("}");
				
				out.println("if(is_flag==0 && is_flag1==0){");
				
				out.println("m_row2='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");									
				
				//out.println("'<tr><TD WIDTH=\"11%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_CODE'+is_lineno+' value=\"\" onblur=\"check_iss1('+is_lineno+'),arry_iscode('+is_lineno+')\"></td>'+");
				out.println("'<tr><TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_ISSUER_CODE'+is_lineno+' value=\"\" style=\"width:140px;\" onblur=\"check_iss1('+is_lineno+'),arry_iscode('+is_lineno+')\" ></td>'+");
				
				out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_ISSUER_CODE'+is_lineno+' value=\"...\" onClick=\"help_button_8('+is_lineno+')\"></td>'+");
				
				//out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_AMT'+is_lineno+' onblur=\"check_amt('+is_lineno+')\" ></TD>'+");
				out.println("'<TD WIDTH=\"17%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_ISSUER_AMT'+is_lineno+'  style=\"width:150px; text-align :right \" maxlength=18 onblur=\"check_amt(this,18)\" >&nbsp;&nbsp;</TD>'+");
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_DD'+is_lineno+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_MM'+is_lineno+' maxlength=\"2\" size=2 onblur=\"\">'+");
				//out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_issuer('+is_lineno+')\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(1,'+is_lineno+')>   Calendar</a></TD>'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_issuer('+is_lineno+')\"></TD>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(1,'+is_lineno+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_DD'+is_lineno+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_MM'+is_lineno+' maxlength=\"2\" size=2 onblur=\"\">'+");
				// out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_start('+is_lineno+')\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(2,'+is_lineno+')>   Calendar</a></td>'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_start('+is_lineno+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(2,'+is_lineno+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_DD'+is_lineno+' maxlength=\"2\" size=2  onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_MM'+is_lineno+' maxlength=\"2\" size=2 onblur=\"\">'+");
				//out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_end('+is_lineno+')\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(3,'+is_lineno+')>   Calendar</a></td>'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_YY'+is_lineno+' maxlength=\"4\" size=4 onblur=\"check_date_end('+is_lineno+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(3,'+is_lineno+') ><u>Calender</u></td>'+"); 
				out.println("'<TD WIDTH=\"6%\" align=\"right\"><input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL_IS'+is_lineno+' value=\" X \" onClick=\"del_row1('+is_lineno+')\"></TD>'+");
				out.println("'<input type=\"hidden\" name=hid_txt_branch_code'+is_lineno+'	value=\"\">'+");//Added By Nuwan De Silva 14-05-07---------
				
				out.println("'</tr></table>';");
				
				out.println("if(is_lineno==0){");
				
				out.println("bank_details.innerHTML+=m_header+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				
				out.println("}");
				
				out.println("else{");
				
				out.println("bank_details.innerHTML+='<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				
				out.println("}");
				
				out.println("check_iss(is_lineno)");
				out.println("is_lineno=parseInt(is_lineno)+1;");
				out.println("document.Form1.hid_count.value=parseInt(is_lineno)");
				out.println("}");
				
				out.println("}");
				
				
				out.println("function del_row1(rowNo){"); 
				out.println("if(document.Form1.hid_count.value!=1 ){");
				out.println("row_val=parseInt(document.Form1.hid_count.value)");
				out.println("var w=0;");
				out.println("var e=0;");
				out.println("for(var i=0;i<parseInt(row_val);i++){");
				out.println("if(i==rowNo){");
				out.println("continue;");
				out.println("}");
				out.println("array_is_code[e]=document.Form1.elements[\"TXT_ISSUER_CODE\"+i].value");
				out.println("array_is_branch_code[e]=document.Form1.elements[\"hid_txt_branch_code\"+i].value");
				
				out.println("array_is_amt[e]=document.Form1.elements[\"TXT_ISSUER_AMT\"+i].value");
				out.println(" array_is_date_dd[e]=document.Form1.elements[\"TXT_ISSUER_DATE_DD\"+i].value;");
				out.println(" array_is_date_mm[e]=document.Form1.elements[\"TXT_ISSUER_DATE_MM\"+i].value;");
				out.println(" array_is_date_yy[e]=document.Form1.elements[\"TXT_ISSUER_DATE_YY\"+i].value;");
				out.println(" array_is_st_date_dd[e]=document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+i].value;");
				out.println(" array_is_st_date_mm[e]=document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+i].value;");
				out.println(" array_is_st_date_yy[e]=document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+i].value;");
				out.println(" array_is_end_date_dd[e]=document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+i].value;");
				out.println(" array_is_end_date_mm[e]=document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+i].value;");
				out.println(" array_is_end_date_yy[e]=document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+i].value;");
				out.println("e=e+1;");
				out.println("}");
				out.println("row_val=parseInt(row_val)-1");
				out.println("write_data1(row_val);");
				out.println("document.Form1.hid_count.value=parseInt(row_val)");
				out.println("}"); 
				out.println("}"); 
				
				out.println("function write_data1(size){");
				out.println("bank_details.innerHTML=\"\";");
				out.println(" for(var q=0;q<parseInt(size);q++){");
				
				out.println("m_row2='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");									
				
				out.println("'<tr><TD WIDTH=\"15%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_CODE'+q+' value=\"'+array_is_code[q]+'\" style=\"width:140px;\" onblur=\"check_iss1('+q+'),arry_iscode('+q+')\"></td>'+");
				
				out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_ISSUER_CODE'+q+' value=\"...\" onClick=\"help_button_8('+q+')\"></td>'+");
				
				out.println("'<TD WIDTH=\"17%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_AMT'+q+'  value=\"'+array_is_amt[q]+'\"  style=\"width:150px;\" maxlength=18 onblur=\"check_amt(this,18)\" >&nbsp;&nbsp;</TD>'+");
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_DD'+q+' maxlength=\"2\" size=2 value=\"'+array_is_date_dd[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_MM'+q+' maxlength=\"2\" size=2 value=\"'+array_is_date_mm[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_YY'+q+' maxlength=\"4\" size=4 value=\"'+array_is_date_yy[q]+'\" onblur=\"check_date_issuer('+q+')\"></TD>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(1,'+q+') ><u>Calender</u></td>'+"); 
				
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_DD'+q+' maxlength=\"2\" size=2 value=\"'+array_is_st_date_dd[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_MM'+q+' maxlength=\"2\" size=2 value=\"'+array_is_st_date_mm[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_YY'+q+' maxlength=\"4\" size=4 value=\"'+array_is_st_date_yy[q]+'\" onblur=\"check_date_start('+q+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(2,'+q+') ><u>Calender</u></td>'+"); 
				
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_DD'+q+' maxlength=\"2\" size=2 value=\"'+array_is_end_date_dd[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_MM'+q+' maxlength=\"2\" size=2 value=\"'+array_is_end_date_mm[q]+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_YY'+q+' maxlength=\"4\" size=4 value=\"'+array_is_end_date_yy[q]+'\" onblur=\"check_date_end('+q+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(3,'+q+') ><u>Calender</u></td>'+"); 
				out.println("'<input type=\"hidden\" name=hid_txt_branch_code'+q+'	value=\"'+array_is_branch_code[q]+'\"  >'+");//Added By Nuwan De Silva 14-05-07---------
				
				
				out.println("'<TD WIDTH=\"6%\" align=\"right\"><input class=\"but_input\"  style=\"width:30px;\" type=\"button\" name=BUT_DEL_IS'+q+' value=\" X \" onClick=\"del_row1('+q+')\"></TD>'+");
				out.println("'</tr></table>';");
				
				out.println("if(q==0){");
				out.println("bank_details.innerHTML+=m_header+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("else{");
				out.println("bank_details.innerHTML+='<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("'</tr></table>';");
				out.println("}");
				out.println("}");		
				
				
				out.println("function load_calendar(num,row) {");
				out.println(" document.Form1.hid_new_row.value=row;"); 
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
				out.println("}");
				
				
				out.println("function load_c_date(val) {");
				out.println("m_row=document.Form1.hid_new_row.value");
				
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.elements[\"TXT_ISSUER_DATE_DD\"+m_row].value=v_dd;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_DATE_MM\"+m_row].value=v_mm;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_DATE_YY\"+m_row].value=v_yy;");
				out.println("  }");		
				out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+m_row].value=v_dd;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+m_row].value=v_mm;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+m_row].value=v_yy;");
				out.println("  }");	
				out.println("  else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+m_row].value=v_dd;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+m_row].value=v_mm;");
				out.println("     document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+m_row].value=v_yy;");
				out.println("  }");	
				out.println("if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+m_row].value))!=\"\" && (parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+m_row].value))!=\"\" && (parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+m_row].value))!=\"\" &&  (parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+m_row].value))!=\"\" && (parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+m_row].value))!=\"\" && (parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+m_row].value))){");
				out.println("chk_validity(m_row)  ");			
				out.println("}");				
				out.println("}");				
				
				out.println("function chk_validity(row){  ");			
				out.println("if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+row].value))>=(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+row].value))){");
				out.println("if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+row].value))){");
				out.println("if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value))){");
				out.println(" if(((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+row].value))==(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+row].value)))&&");
				out.println("((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+row].value))==(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+row].value)))&&");
				out.println("((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value))==(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value)))){");
				out.println("}");
				out.println("else if(((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+row].value))>(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_DD\"+row].value)))&&");
				out.println("((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+row].value))==(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+row].value)))&&");
				out.println(" ((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value))==(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value)))){");
				out.println("      alert('End Date should be greater than Start Date');");
				out.println("     } ");
				out.println("}");
				out.println("else{");
				out.println(" alert('End Date should be greater than Start Date');");
				out.println("return false;"); 
				out.println("}");
				out.println(" }");
				out.println(" else{");
				out.println("   if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value))>=(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value))){");
				out.println("    alert('End Date should be greater than Start Date');");
				out.println("return false;"); 
				out.println("   }");
				out.println("   else{");
				out.println("   } ");
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println(" if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_MM\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_MM\"+row].value))){");
				out.println("  if((document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value)<=(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value)){");
				out.println(" }");
				out.println(" else{");
				out.println("   alert('End Date should be greater than Start Date');");
				out.println("return false;"); 
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println("   if((parseInt(document.Form1.elements[\"TXT_ISSUER_START_DATE_YY\"+row].value))<(parseInt(document.Form1.elements[\"TXT_ISSUER_END_DATE_YY\"+row].value))){ ");
				out.println("    }");
				out.println("  else{");
				out.println("  alert('End Date should be greater than Start Date');");
				out.println("return false;"); 
				out.println("  }");
				out.println(" }");
				out.println("}");
				out.println("document.Form1.elements[\"TXT_ISSUER_START_DATE_DD\"+row].focus();");
				out.println("return true;");
				out.println("}");
				
				out.println("function check_date_issuer(ln){ ");
				out.println("ind_dd1='TXT_ISSUER_DATE_DD'+ln;");
				out.println("ind_mm1='TXT_ISSUER_DATE_MM'+ln;");
				out.println("ind_yy1='TXT_ISSUER_DATE_YY'+ln;");
				
				//out.println("  checkMonthLength(document.Form1.elements[ind_dd1],document.Form1.elements[ind_mm1],document.Form1.elements[ind_yy1]);");
				out.println(" if((document.Form1.elements[ind_dd1].value !=\"\") && (document.Form1.elements[ind_mm1].value !=\"\") && (document.Form1.elements[ind_yy1].value !=\"\")){");
				out.println("  checkMonthLength(document.Form1.elements[ind_dd1],document.Form1.elements[ind_mm1],document.Form1.elements[ind_yy1]);");
				out.println(" }");
				
				out.println("}");
				
				out.println("function check_date_start(ln){ ");
				out.println("ind_dd2='TXT_ISSUER_START_DATE_DD'+ln;");
				out.println("ind_mm2='TXT_ISSUER_START_DATE_MM'+ln;");
				out.println("ind_yy2='TXT_ISSUER_START_DATE_YY'+ln;");
				
				//out.println("  checkMonthLength(document.Form1.elements[ind_dd2],document.Form1.elements[ind_mm2],document.Form1.elements[ind_yy2]);");
				
				out.println(" if((document.Form1.elements[ind_dd2].value !=\"\") && (document.Form1.elements[ind_mm2].value !=\"\") && (document.Form1.elements[ind_yy2].value !=\"\")){");
				out.println("  checkMonthLength(document.Form1.elements[ind_dd2],document.Form1.elements[ind_mm2],document.Form1.elements[ind_yy2]);");
				out.println(" }");
				
				out.println("}");
				
				out.println("function check_date_end(ln){ ");
				out.println("ind_dd3='TXT_ISSUER_END_DATE_DD'+ln;");
				out.println("ind_mm3='TXT_ISSUER_END_DATE_MM'+ln;");
				out.println("ind_yy3='TXT_ISSUER_END_DATE_YY'+ln;");
				
				//out.println("  checkMonthLength(document.Form1.elements[ind_dd3],document.Form1.elements[ind_mm3],document.Form1.elements[ind_yy3]);");
				
				out.println(" if((document.Form1.elements[ind_dd3].value !=\"\") && (document.Form1.elements[ind_mm3].value !=\"\") && (document.Form1.elements[ind_yy3].value !=\"\")){");
				out.println("  checkMonthLength(document.Form1.elements[ind_dd3],document.Form1.elements[ind_mm3],document.Form1.elements[ind_yy3]);");
				out.println(" }");
				out.println("}");
				
				
				out.println("function help_button_8(row) {"); 
				out.println("    document.Form1.hid_row.value=row");
				out.println("    document.Form1.hid_help_type.value=\"8\";"); 
				out.println("   Sql = \"m_help_TXT_ISSUER_CODE_sql\";"); 
				out.println("    Crit = document.Form1.elements[\"TXT_ISSUER_CODE\"+row].value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,Sql,'8','0');");
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function help_value_assign_8(oBj) {");
				//out.println("    document.Form1.elements[\"TXT_ISSUER_CODE\"+document.Form1.hid_row.value].value=oBj.valout[2];"); 
				
				out.println("    document.Form1.elements[\"TXT_ISSUER_CODE\"+document.Form1.hid_row.value].value=oBj.valout[4];"); //Modified Nuwan De silva 14-05-07
				out.println("    document.Form1.elements[\"hid_txt_branch_code\"+document.Form1.hid_row.value].value=oBj.valout[3];"); //Modified Nuwan De silva 14-05-07
				
				out.println("check_iss1(document.Form1.hid_row.value)");
				
				out.println("}"); 
				
				
				
				out.println("function display_bank_guarn(data_vec){");
				out.println("bank_details.innerHTML=\"\"");
				out.println("m_header='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");
				out.println("'<TR><TD WIDTH=\"15%\" align=\"left\"><B>Issuer</B></TD>'+");
				out.println("'<TD WIDTH=\"6%\"     align=\"left\"><B>&nbsp</B></TD>' +");
				out.println("'<TD WIDTH=\"17%\"     align=\"right\"><B>Amount&nbsp;&nbsp;</B></TD>'+");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>Issue Date</B></TD>' +");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>Start Date</B></TD>' +");
				out.println("'<TD WIDTH=\"18%\"     align=\"left\"><B>End Date</B></TD>' +");
				out.println("'<TD WIDTH=\"6%\"     align=\"left\"><B>&nbsp</B></TD>' +");
				out.println("'</TR></table>';");
				out.println("a=0;");
				out.println("g=0;");
				out.println("if(data_vec.length==0){");
				out.println("m_row2='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");									
				out.println("'<tr><TD WIDTH=\"15%\"  align=\"left\" ><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_CODE'+a+' style=\"width:140px; text-align:left\" value=\"\" onblur=\"check_iss1('+a+'),arry_iscode('+a+')\" ></td>'+");
				
				out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_ISSUER_CODE'+a+' value=\"...\" onClick=\"help_button_8('+a+')\"></td>'+");
				
				out.println("'<TD WIDTH=\"17%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_AMT'+a+'   style=\"width:150px;text-align:right\" maxlength=18 onblur=\"check_amt(this,18)\" >&nbsp;&nbsp;</TD>'+");
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_DD'+a+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_MM'+a+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_YY'+a+' maxlength=\"4\" size=4 onblur=\"check_date_issuer('+a+')\"></TD>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(1,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_DD'+a+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_MM'+a+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_YY'+a+' maxlength=\"4\" size=4  onblur=\"check_date_start('+a+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(2,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_DD'+a+' maxlength=\"2\" size=2  onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_MM'+a+' maxlength=\"2\" size=2 onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_YY'+a+' maxlength=\"4\" size=4  onblur=\"check_date_end('+a+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(3,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"6%\" align=\"right\"><input class=\"but_input\"  style=\"width:30px;\" type=\"button\" name=BUT_DEL_IS'+a+' value=\" X \" onClick=\"del_row1('+a+')\"></TD>'+");
				out.println("'<input type=\"hidden\" name=hid_txt_branch_code'+a+'	value=\"\">'+");//Added By Nuwan De Silva 14-05-07---------
				
				
				out.println("'</tr></table>';");
				out.println("if(a==0){");
				out.println("bank_details.innerHTML+=m_header+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("else{");
				out.println("bank_details.innerHTML+='<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("a=parseInt(a)+1;");
				out.println("}");
				out.println("else{");
				out.println("while(g<data_vec.length){");
				
				out.println("m_row2='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+");									
				out.println("'<tr><TD WIDTH=\"15%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_CODE'+a+' style=\"width:140px; text-align:left \" value=\"'+data_vec[g+7]+'\" ></td>'+");
				out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_ISSUER_CODE'+a+' value=\"...\" onClick=\"help_button_8('+a+')\"></td>'+");
				
				out.println("'<TD WIDTH=\"17%\"><input class=\"txt_input2\" type=\"text\" name=TXT_ISSUER_AMT'+a+' style=\"width:150px;\" value=\"'+data_vec[g+2]+'\"  maxlength=18 onblur=\"check_amt(this,18)\" >&nbsp;&nbsp;</TD>'+");
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_DD'+a+' maxlength=\"2\" size=2 value=\"'+data_vec[g+3].substring(0,2)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_MM'+a+' maxlength=\"2\" size=2 value=\"'+data_vec[g+3].substring(3,5)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_DATE_YY'+a+' maxlength=\"4\" size=4 value=\"'+data_vec[g+3].substring(6,10)+'\" onblur=\"check_date_issuer('+a+')\"></TD>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(1,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_DD'+a+' maxlength=\"2\" size=2 value=\"'+data_vec[g+4].substring(0,2)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_MM'+a+' maxlength=\"2\" size=2 value=\"'+data_vec[g+4].substring(3,5)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_START_DATE_YY'+a+' maxlength=\"4\" size=4 value=\"'+data_vec[g+4].substring(6,10)+'\" onblur=\"check_date_start('+a+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(2,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_DD'+a+' maxlength=\"2\" size=2  value=\"'+data_vec[g+5].substring(0,2)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_MM'+a+' maxlength=\"2\" size=2 value=\"'+data_vec[g+5].substring(3,5)+'\" onblur=\"\">'+");
				out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_ISSUER_END_DATE_YY'+a+' maxlength=\"4\" size=4 value=\"'+data_vec[g+5].substring(6,10)+'\" onblur=\"check_date_end('+a+')\"></td>'+");
				out.println("'<td width=\"7%\" align=\"left\" style=\"{cursor:hand; }\" onclick=load_calendar(3,'+a+') ><u>Calender</u></td>'+"); 
				
				out.println("'<TD WIDTH=\"6%\" align=\"right\"><input class=\"but_input\"  style=\"width:30px;\" type=\"button\" name=BUT_DEL_IS'+a+' value=\" X \" onClick=\"del_row1('+a+')\"></TD>'+");
				out.println("'<input type=\"hidden\" name=hid_txt_branch_code'+a+'	value=\"'+data_vec[g+1]+'\"  >'+");//Added By Nuwan De Silva 14-05-07---------
				
				out.println("'</tr></table>';");
				
				out.println("if(a==0){");
				out.println("bank_details.innerHTML+=m_header+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("else{");
				out.println("bank_details.innerHTML+='<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("m_row2+'</table>'+'<table align=\"center\" border\"0\" width=\"100%\" class=\"table\">'+");
				out.println("'</table>';");
				out.println("}");
				out.println("a=parseInt(a)+1;");
				out.println("g=g+8;");
				out.println("}");
				out.println("}");
				out.println("document.Form1.hid_count.value=parseInt(a)");
				out.println("}"); 
				
				
				// ===== Added by Chandana on 16/05/2007 for Refinements =====//			
				
				out.println("function count_length1(val){");
				out.println("var j=val.value.length - 999 ");
				out.println("	if(val.value.length >999){");
				out.println("document.Form1.TXT_REMARKS.focus()");
				out.println("}");
				out.println("}");
				
				out.println("function count_length(val){");
				out.println("var j=val.value.length - 999 ");
				out.println("	if(val.value.length >999){");
				out.println("	alert('Comment is too long, remove '+j+ 'character')");
				out.println("document.Form1.TXT_REMARKS.focus()");
				out.println("}");
				out.println("}");
				
				//========= End Chandana on 16/05/2007 =========//	
				
				
				
				
				//=========added by nuwan de silva 22-05-07=============
				//===========validate the number=======================
				
				out.println("	function chk_comment_length(obj){ ");
				
				out.println(" var remarks_length=obj.value.toString().length;");
				
				out.println("if(remarks_length>obj.maxlength) ");
				out.println("		window.event.keyCode=\"\"; ");
				
				out.println("} ");
				
				out.println("function count_length(obj){ ");
				out.println("var remarks_length=obj.value.toString().length; ");
				out.println("var remarks=obj.value.toString(); ");
				out.println("if(remarks_length>obj.maxlength){ ");
				out.println("obj.value=remarks.substring(0,obj.maxlength); ");
				out.println("} ");
				out.println("} ");
				
				
				//================================================
				
				
				//Marketing Officer // Added by Chandana on 29/11/2007
				/*out.println("function mk_officer_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.MKT_OFFICER.value+\"@AF@\";");
				out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");	*/
				
				
				out.println("function mk_officer_help(rowNo) {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				out.println("   Sql = \"MKOfficerSqlNew\";");//MKOfficerSqlNew Modified By Sandun 25-08-2008
				out.println("   Crit=document.Form1.MKT_OFFICER.value+\"@AF@\";");
				out.println("    HelpBox('1','10','0',Crit,Sql,'11','0');");
				out.println("}");
				
				//----added by ishani 2014-02-27----------
				
				out.println("function help_button_finance() {"); 
				
				//out.println("    alert('TXT_FINANCE :  ' + document.Form1.TXT_FINANCE.value); ");
				//out.println("    alert('TXT_APPLICANT_CODE :  ' + document.Form1.TXT_APPLICANT_CODE.value); ");
				
				out.println("    document.Form1.hid_help_type.value=\"13\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				out.println("   Sql = \"ClientSql_Receipt_terminate\";");//
				//out.println("   Crit=document.Form1.TXT_FINANCE.value+\"@AF@\";");
				
				out.println("   Crit=document.Form1.TXT_FINANCE.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@AF@\";");
				out.println("    HelpBox('1','10','0',Crit,Sql,'13','0');");
				out.println("}"); 
				
				
				/*out.println("function help_button_finance(){");
				out.println("Crit=document.Form1.TXT_FINANCE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				//out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt','1');"); //ADDED by nuwan de silva on07-04-2008
				out.println("}");*/
				
				out.println("function ins_officer_help(rowNo) {"); 
				// Modified By Samitha Kulatilaka On 2009-10-15 (Changed 'hid_help_type.value' From '5' To '11')
				out.println("    document.Form1.hid_help_type.value=\"11\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				out.println("   Sql = \"MKOfficerSqlNew\";");//MKOfficerSqlNew Modified By Sandun 25-08-2008
				out.println("   Crit=document.Form1.INSURANCE_OFFICER.value+\"@AF@\";");
				out.println("    HelpBox('1','10','0',Crit,Sql,'12','0');");
				out.println("}"); 
				
				// added by udara 09-10-2018
				out.println("function btn_pledge_help(rowNo) {"); 
				out.println("   document.Form1.hid_help_type.value=\"14\";"); 
				out.println("   Sql = \"PledgeContractSqlNew\";");
				//out.println("   Crit=document.Form1.TXT_PLEDGE_CONTRACT.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); // commented by udara 25-10-2018
				out.println("   Crit=document.Form1.TXT_PLEDGE_CONTRACT.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\";"); // added by udara 25-10-2018
				out.println("   HelpBox('1','10','0',Crit,Sql,'14','0');");
				out.println("}"); 
				// end by udara 09-10-2018


				out.println("function vendor_help(rowNo) {");  //added by kasun on 2024.11.25 for JB16102024-25600
				out.println("   document.Form1.hid_help_type.value=\"15\";"); 
				out.println("   Sql = \"m_help_TXT_VENDOR_CODE_sql\";");
				out.println("   Crit=document.Form1.TXT_VENDOR_CODE.value+\"@Y@\";");
				out.println("   HelpBox('1','10','0',Crit,Sql,'15','0');");
				out.println("}"); 
				
				out.println("function ins_officer_assign(oBj){");
				out.println(" document.Form1.INSURANCE_OFFICER.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
				
				// added by udara 09-10-2018
				out.println("function pledge_contract_assign(oBj){");
				out.println(" document.Form1.TXT_PLEDGE_CONTRACT.value =oBj.valout[2]");
				out.println(" check_pledge_status(document.Form1.TXT_PLEDGE_CONTRACT.value); ");
				out.println("}");
				// end by udara 09-10-2018


				out.println("function vendor_code_assign(oBj){"); //added by kasun on 2024.11.25 for JB16102024-25600
				out.println(" document.Form1.TXT_VENDOR_CODE.value =oBj.valout[2]");
				// out.println(" check_vendor_status(document.Form1.TXT_VENDOR_CODE.value); ");
				out.println("}");
				
				
				out.println("function finance_no_assign(oBj){");
				out.println(" document.Form1.TXT_FINANCE.value =oBj.valout[2]");
				out.println("  get_finance_amount(oBj.valout[10]);	"); 
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
				
				
				
				
				// added by udara 18-10-2018
				out.println("function pledge_contract_set(val){");
				out.println(" document.Form1.TXT_PLEDGE_CONTRACT.value = val;");
				out.println("}");

				//added by kasun on 26-11-2024
				out.println("function vendor_code_set(val){");
				out.println(" document.Form1.TXT_VENDOR_CODE.value = val;");
				out.println("}");
				
				
				out.println("function check_pledge_status(val){");
				out.println("       document.Form1.hid_chk_status.value = 'PLEDGE_STATUS'; ");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_get_pledge_status&finance_no=\"+val;");
				//out.println("       window.open(m_url); ");
				out.println("       load_interface(m_url,'XML');");
				out.println("}");
				// end by udara 18-10-2018


				out.println("function check_vendor_status(val){");  //added by kasun on 2024.11.25 for JB16102024-25600
				out.println("       document.Form1.hid_chk_status.value = 'VENDOR_STATUS'; ");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_get_vendor_status&finance_no=\"+val;");
				out.println("       load_interface(m_url,'XML');");
				out.println("}");
				
				// added by udara 08-04-2021
				out.println("function check_broker_status(){");
				
				out.println("   if(document.Form1.LEAD_SOURCE_CATEGORY.value=='BROKER'){ ");
				out.println("     if(document.Form1.LEAD_SOURCE_NAME.value=='-'){");
				out.println("       alert('Please enter a broker code'); ");
				out.println("       document.Form1.LEAD_SOURCE_NAME.value=''; ");
				out.println("       document.Form1.LEAD_SOURCE_NAME.focus(); ");
				out.println("     }");
				// commented by udadra 01-09-2021
				/*
				out.println("     else if(document.Form1.LEAD_SOURCE_NAME.value!=''){");
				out.println("       document.Form1.hid_chk_status.value = 'BROKER_STATUS'; ");
				
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_get_broker_status&broker_code=\"+document.Form1.LEAD_SOURCE_CODE.value;"); // commented by udara 07-05-2021
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_get_broker_status&broker_code=\"+document.Form1.LEAD_SOURCE_NAME.value;");
				
				out.println("       load_interface(m_url,'XML');");
				out.println("     }");
				*/
				out.println("   }");

				out.println("}");
				// end by udara 08-04-2021
				
				
				out.println("function mk_officer_assign(oBj){");
				out.println(" document.Form1.MKT_OFFICER.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");	
				
				// added by udara on 06-08-2013
				out.println("function validate_vehicle_chasis_engine_no(){");
				out.println("   if(document.Form1.chk_complete.checked==true){ ");
				out.println("       document.Form1.hid_chk_status.value = 'VEHI_VAL'; ");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_check_vehicle_engine_chasis_no&application_no=\"+document.Form1.TXT_APPLICATION_NO.value;");
				//out.println("       window.open(m_url); ");
				out.println("       load_interface(m_url,'XML');");
				out.println("   }");
				out.println("}");	
				// end by udara on 06-08-2013
				
				
				// added by ishani 17-03-2014
				out.println("function validate_vehicle_chasis_engine_no_refinance(){");
				out.println("   if(document.Form1.chk_complete.checked==true){ ");
				out.println("       document.Form1.hid_chk_status.value = 'VEHI_VAL_RE'; ");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_check_vehicle_engine_chasis_no_refinance&application_no=\"+document.Form1.TXT_APPLICATION_NO.value;");
				//out.println("       window.open(m_url); ");
				out.println("       load_interface(m_url,'XML');");
				out.println("   }");
				out.println("}");	
				// end by ishani 17-03-2014
				
				
				
				// added by udara 03-01-2013
				out.println("function check_guaranter_exist(obj){");
				out.println("       document.Form1.hid_chk_status.value = 'GUAR_CHK'; ");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_check_guaranter_exist&guaranter=\"+obj.value;");
				out.println("       load_interface(m_url,'XML');");
				out.println("}");
				// added by udara 03-01-2013
				
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				out.println("</script>");
				//	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),disable_app_no(),header(),add_row(),load_app_data(),header1(),add_row1()\">"); //load_lock()  //Comment by Prabash on21-03-2012 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),disable_app_no(),header(),add_row(),load_app_data(),load_user_Branch(),header1(),add_row1(),empty_lead_source_name()\">"); //load_lock() ////insert load_user_Branch()  by Prabash on 21-03-2012
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<INPUT TYPE='Hidden' NAME='LEAD_SOURCE_CODE' VALUE=\"\">"); 
				out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_price_tot' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_profo_tot' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_tot_fin_amt' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cur_fin_amt' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_close_sts' VALUE=\"N\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_transaction_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_CLOSE' VALUE=\"N\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_insurance_done' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APPLICATION_PROCESS\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_count' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_use' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_asset_count' VALUE=\"0\">");  //added by nuwan de silva on 15-11-07
				out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">");  //added by nuwan de silva on 15-11-07
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_new_row' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing - Application Process</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>");
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='disable_textbox(), load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" name=\"save_button_1\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"HELP\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
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
				
				out.println("<table align='center' width='100%' border=1 class='' bordercolor=''>"); 
				out.println("<tr align='right'>");  
				out.println("<td width='100%'>");	
				
				out.println("<table align='center' width='100%' class='table' border=0 >"); 
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_INQUARY_NO'  class=div_input>Inquiry No </DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_INQUARY_NO' maxlength='22' size='22' onblur=\"assignState('M6'),makeRequest(document.Form1.TXT_INQUARY_NO)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_INQUARY_NO' value=\"...\" onClick=\"help_button_3('1','10','23','m_help_TXT_INQUARY_NO','3','0')\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_INQUARY_NO_ALL' value=\"View All\" onClick=\"help_button_7('0','10','2','m_help_TXT_INQUARY_NO_ALL','7','0')\"></td>"); 

				//added by kasun on 2024.11.22 for JB16102024-25600
				out.println("<td ><div id=vendor_label > </div></td> "); 
				out.println("<td ><div id=vendor_text > </div></td>"); 
				//end by kasun on 2024.11.22 for JB16102024-25600

				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); // commented by udara 17-07-2017
				//out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"\" >"); // added by udara 17-07-2017
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('0','10','10','m_help_TXT_APPLICATION_NO','99','0')\" disabled></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
				//	out.println("<TD></TD>");
				out.println("<td >Contract Number</td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_CONTRACT_NUMBER' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
				out.println("<TD ID=TERM_TYPE STYLE=\"{font-weight: bold}\"></TD>");
				out.println("</tr>"); 
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility Number </DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='10' size='10'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FACILITY_NO' value=\"...\"></td>"); 
				out.println("<TD><input type=hidden name=hid_pre_app_no><input type=hidden name=hid_term_type><input type=hidden name=hid_term_no><input type=hidden name=hid_term_amt></TD>");
				out.println("<TD ID=TERM_DET STYLE=\"{font-weight: bold}\"></TD>");

				

				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_TR_TYPE'  class=div_input>Transaction Type </DIV></td>"); 
				out.println("<td width='28%' ><select name=\"TXT_TR_TYPE\" class=\"txt_input\" onChange=\"validate_txt_type()\" >"); 
				
				rs=stmt.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE  ACTIVE_STATUS='Y' ");
				
				boolean more=rs.next();
				while(more){
					
					if(rs.getString(3).equals("Y")){
						out.println("<option value=\""+rs.getString(1)+"\" SELECTED>"+rs.getString(2)+"</option>+");			
					}
					else{
						out.println("<option value=\""+rs.getString(1)+"\" >"+rs.getString(2)+"</option>+");		
					}
					more=rs.next();
				} 
				
				
				
				
				out.println("</select></td>"); 
				
				// added by udara 09-10-2018
				out.println("<td ><div id=pledge_label > </div></td> "); 
				out.println("<td ><div id=pledge_text > </div></td>"); 
				// end by udara 09-10-2018

			

				out.println("</tr>"); 
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICANT_CODE'  class=div_input>Applicant Code *</DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_APPLICANT_CODE)\"  onchange=\"set_pledge_div(document.Form1.SCREEN_NAME.value,document.Form1.TXT_TR_TYPE.value);\"  >"); // added set_pledge_div by udara 29-10-2018
				out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"...\" onClick=\"help_button_2('1','10','0','m_help_TXT_CLIENT_CODE','2','0')\">"); 
				//out.println("<input class='but_input' style='width:50' type='button' name='CLIENT_LINK_BUT' value=\"Create\" onClick=\"load_client('N')\"></td>"); //A
				out.println("<input class='but_input' style='width:50' type='button' name='CLIENT_LINK_BUT' value=\"Create\" onClick=\"load_client('A')\"></td>"); //A // added by udara 18-10-2017
				out.println("<td >Applicant Name </td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CORE_APPLICANT_CODE'  class=div_input>Co Applicant Code </DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_CORE_APPLICANT_CODE' maxlength='10' size='10' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_CORE_APPLICANT_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_CORE_APPLICANT_CODE' value=\"...\" onClick=\"help_button_6('1','10','0','m_help_TXT_CLIENT_CODE','6','0')\">"); 
				out.println("<input class='but_input' style='width:50' type='button' name='CO_CLIENT_LINK_BUT' value=\"Create\" onClick=\"load_client('B')\"></td>"); 
				out.println("<td >Co Applicant Name </td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_CORE_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
				out.println("</tr>"); 
				
				
				//=========added by nuwan de silva 25-06-07
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch Code *</DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M_BRANCH'),makeRequest(document.Form1.TXT_LOCATION_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"...\" onClick=\"help_button_location('1','10','0','m_help_TXT_LOCATION_CODE_sql','9','0')\"></td>"); 
				out.println("<td ><DIV id='DIV_BRANCH_NANE'  class=div_input>Branch Name</td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_LOCATION_DESC' style=\"width:250px;\" maxlength='250' size='22' disabled></td>");
				out.println("</tr>"); 
				
				//===========================================
				
				//____________________________________________________________________________________
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Lead Source Category</DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M_BRANCH'),makeRequest(document.Form1.TXT_LOCATION_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_location('1','10','0','m_help_TXT_LOCATION_CODE_sql','9','0')\"></td>"); 
					out.println("</tr>"); 
					
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Lead Source Name</DIV></td>"); 
				out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M_BRANCH'),makeRequest(document.Form1.TXT_LOCATION_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_location('1','10','0','m_help_TXT_LOCATION_CODE_sql','9','0')\"></td>"); 
					out.println("</tr>"); 
				*/					
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' >Lead Source Category</td>");
				out.println("<td width='28%' >");
				// Modified By Samitha Kulatilaka On 2009-10-15 (Added The onChange() Event)
				out.println("<select name=\"LEAD_SOURCE_CATEGORY\" class=\"txt_input\" style=\"width:175px;\" onChange='empty_lead_source_name()' >");
				rs = stmt.executeQuery(CO_methods.getLeadSourceCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				
				
				
				//----added by ishani 2014-02-27----------
				out.println("<td ><DIV ID='m_re_finance' class=div_input></DIV></td>"); //
				out.println("<td ><DIV ID='m_re_finance2' class=div_input></DIV></td>");
				
				//out.println("<td >Lead Source Name</td>");
				//out.println("<td >");
				//out.println("<input name=\"LEAD_SOURCE_NAME\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:200px;\">");
				//out.println("<input type=button name=inqu_help value=Help class=\"but_input\" onclick=\"brk_help('1','10','4','BrokerSql','8')\"></td>");
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input></DIV></td>"); 
				out.println("<td width='28%' > "); 
				out.println("</td>"); 
				out.println("<td ><DIV ID='m_re_finance_Amt' class=div_input></DIV></td>"); //
				out.println("<td ><DIV ID='m_re_finance2_Amt' class=div_input></DIV></td>");
				out.println("</tr>");
				
				
				
				out.println("<tr >");
				out.println("<td width='20%' ><DIV id='DIV_TXT_LEAD_SOURCE_NAME'  class=div_input>Lead Source Name *</DIV></td>"); // out.println("<td width='20%' ><DIV id='DIV_TXT_LEAD_SOURCE_NAME'  class=div_input> Lead Source Name * </DIV></td>");  // out.println("<td width='20%' >Lead Source Name</td>");
				out.println("<td width='28%' >");
				out.println("<input name=\"LEAD_SOURCE_NAME\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:175px;\" onblur='check_broker_status()' >");
				out.println("<input type=button name=inqu_help value=... class=\"but_input\" onclick=\"brk_help('1','10','4','BrokerSql','10','0')\"  ></td>");
				
				out.println("<td  >Division Code</td>"); /* Added by Chandana on 30/11/2007 */
				out.println("<td  >");
				out.println("<select name=\"TXT_DIVISION_CODE\" class=\"txt_input\" style=\"width:180px;\" >");
				
				rs = stmt.executeQuery(	"  SELECT "+
					"  DIVISION_CODE, "+
					"  DESCRIPTION "+
					"  FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
					//"  WHERE DIVISION_CODE='AF' "+
					"  WHERE ACTIVE_STATUS='Y' ");
				
				more = rs.next();
				while(more){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				
				
				
				
				out.println("</tr>");
				
				// added by nuwan de silva on 27-11-2007
				/*		out.println("<tr class=tr_input>");
						out.println("<td width='20%' >Division Code</td>");
						out.println("<td width='28%' >");
						out.println("<select name=\"TXT_DIVISION_CODE\" class=\"txt_input\"  >");
						
						rs = stmt.executeQuery(	"  SELECT "+
																		"  DIVISION_CODE, "+
																		"  DESCRIPTION "+
																		"  FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
																		//"  WHERE DIVISION_CODE='AF' "+
																		"  WHERE ACTIVE_STATUS='Y' ");
																		
						//rs = stmt.executeQuery(CO_methods.getLeadSourceCat(m_schema_name,"Y",""));
						more = rs.next();
						while(more){
						out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
						more = rs.next();	
						}	
						out.println("</SELECT>"); 
						out.println("</td>");
						out.println("</tr>"); */
				
				
				
				//____________________________________________________________________________________
				
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' >Insurance Done By</td>"); 
				out.println("<td width='28%' ><select name='TXT_INSURANCE_DONE' class='txt_input'>");
				out.println("<option value=\"LICENSEE\" >Licensee</option>");
				out.println("<option value=\"BROKER\" >Broker</option>");
				out.println("<option value=\"CLIENT\" >Client</option>");
				out.println("</select>");
				out.println("</td>"); */
				
				out.println("<tr >");
				//out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input> Marketing Officer Code *</DIV></td>"); /* Added by Chandana on 30/11/2007 */
				out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input> Credit Officer Code *</DIV></td>"); /* Added by milinda 2013-10-09 */
				out.println("<td width='28%' >");
				out.println("<input name=\"MKT_OFFICER\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:175px;\" onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\">");
				out.println("<input type=button name=mkt_officer_help value=... class=\"but_input\" onclick=\"mk_officer_help('11')\"></td>");
				
				
				
				out.println("<td  >Insurance Done By</td>"); 
				out.println("<td  ><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:180px;\" >");
				//out.println("<option value=\"LICENSEE\" >Licensee</option>");
				out.println("<option value=\"LICENSEE\" >Company</option>");
				//out.println("<option value=\"BROKER\" >Broker</option>"); // commented by udara 29-09-2014
				out.println("<option value=\"CLIENT\" >Client</option>");// Changed Lessee as client by Kanchana for #19461.
				
				out.println("</select>");
				out.println("</td>");
				
				
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' >Priority</td>"); 
				out.println("<td width='28%' ><select name='TXT_PRIORITY' class='txt_input'>");
				out.println("<option value=\"A\" >A</option>");
				out.println("<option value=\"B\" >B</option>");
				out.println("<option value=\"C\" SELECTED>C</option>");
				out.println("</select>");
				out.println("</td >");
				
				out.println("<td ><DIV id='DIV_INS_OFFICER'  class=div_input>Insurance Officer *</td>"); 
				out.println("<td> <input name=\"INSURANCE_OFFICER\" type=\"text\" maxlength=\"50\" class=\"txt_input\" onblur=\"assignState('M_INS_OFFC'),makeRequest(document.Form1.INSURANCE_OFFICER)\">");
				out.println("<input type=button name=ins_officer value=... class=\"but_input\" onclick=\"ins_officer_help('12')\"></td>");
				
				out.println("</tr>"); 
				
				
				
				out.println("</table>"); 
				
				//---------ADDED BY CHANDANA ON 09/05/2007----------------//
				out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
				out.println("<tr>"); 
				out.println("<td width='20%' valign = 'top'> Comments </td>"); 
				out.println("<td width='*%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:650px; height:50px;\" maxlength='1000' size='1000'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); //onblur=\"count_length(this)\"
				out.println("</tr>"); 
				//----------END CHANDANA ON 09/05/2007 -------------------------------------------------------------------------------------------------------------------------
				
				out.println("</table>"); 
				out.println("</td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<br>");  
				
				out.println("<table align='center' width='100%' border=1 class='' bordercolor=''>"); 
				out.println("<tr align='right'>");  
				out.println("<td width='100%'>");	
				out.println("<table align='center' width='100%' >"); 
				
				out.println("<tr align='left'>");  
				out.println("<td width='*%'><u><b>Guarantors<b></u></td>"); 
				out.println("</tr>");  
				
				out.println("<tr align='right'>");  
				// out.println("<td width='80%'><u><b>Guarantors<b></u></td>"); 
				out.println("<td width='90%'><input class='but_input' type='button' name='MORE_BUT' value=\"More\" onClick=\"add_row()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");  
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>");
				out.println("</td>"); 
				out.println("</tr>");  
				out.println("</table>");	
				
				out.println("<br>");
				
				out.println("<table align='center' width='100%' border=1 class='' bordercolor=''>"); 
				out.println("<tr align='right'>");  
				out.println("<td width='100%'>");	
				out.println("<table align='center' width='100%' >"); 
				
				out.println("<tr align='left'>");  
				out.println("<td width='*%'><u><b>Bank Guarantees<b></u></td>"); 
				out.println("</tr>");  
				
				out.println("<tr align='right'>");
				//out.println("<td width='20%'><u><b>Bank Guarantees<b></u></td>"); 
				out.println("<td width='90%'><input class='but_input' type='button' name='MORE_BUT_IS' value=\"More\" onClick=\"add_row1()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");  
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='bank_details'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("</td>"); 
				out.println("</tr>");  
				out.println("</table>");		
				
				/* out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"1\"><tr>");
				 out.println("<tr align='right'>");  
								
			   out.println("<td ><input class='but_input' type='button' name='MORE_BUT_IS' value=\"More\" onClick=\"add_row1()\"></td>"); 
			   out.println("</tr>"); 
			   out.println("<tr><td ><div id=bank_details></div></td></tr></table>");
				*/
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%'  border=1 class='' bordercolor=''>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' >"); 
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width='5%'></td>"); 
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='ASSET_LINK_BUT' value=\"Asset Details\" onClick=\"load_asset()\" disabled></td>"); 
				out.println("<td width=\"5%\" align='left'><DIV ID='Asset_count'></DIV></td>");
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='PRICING_LINK_BUT' value=\"Pricing\" onClick=\"load_pricing()\" disabled></td>"); 
				out.println("<td width=\"5%\" align='left'><DIV ID='Pricing_count'></DIV></td>");
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='INVOICE_LINK_BUT' value=\"Pro Forma Invoice\" onClick=\"load_invoice(document.Form1.hid_asset_count.value,document.Form1.hid_invoice_count.value)\" disabled></td>"); 
				out.println("<td width=\"5%\" align='left'><DIV ID='Proforma_count'></DIV></td>");
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='VALUATION_LINK_BUT' value=\"Valuation\" onClick=\"load_valuation()\" disabled></td>"); 
				out.println("<td width=\"5%\" align='left'><DIV ID='Valuation_count'></DIV></td>");
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='DOCUMENTS_LINK_BUT' value=\"Documents Required\" onClick=\"load_documents()\" disabled></td>"); 
				out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='SECURITIES_LINK_BUT' value=\"Securities\" onClick=\"load_Securities()\" disabled></td>"); 	//add by waruna 2012-04-24
				out.println("<td width=\"5%\" align='left'><DIV ID='Security_count'></DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("<tr>");  
				out.println("</table>");
				
				out.println("</td>"); 
				out.println("<tr>");  
				out.println("</table>");	
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%'  border=1 class='' bordercolor=''>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' >"); 
				
				out.println("<table align='center'  width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");  
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table_counts'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<br>"); 
				
				out.println("<table align='center' width='100%' class='table' boder=1>"); 
				out.println("<tr>");  
				out.println("<td width=\"33%\"><B>Please tick this box if the application is complete.</B></td><td width=\"5%\"><input class='' type='checkbox' name='chk_complete' onClick='' ></td><td width=\"*%\"></td>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<br>");	
				
				out.println("</td>"); 
				out.println("<tr>");  
				out.println("</table>");
				
				//Added by Chandana on 30/11/2007
				out.println("<br>");	
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='disable_textbox(), load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" name=\"save_button_2\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"HELP\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  	
				
				out.println("<br>");	
				out.println("<br>");	
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}
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


