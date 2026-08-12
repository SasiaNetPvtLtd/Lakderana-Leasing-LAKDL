//--
//SCREEN NAME:CREDIT PROCESS -CHANGE ACTIVATED DATE
//CREATED BY:Nuwan De Silva
//DATE/TIME: created on 24-07-07 11.26am
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_Proforma_Invoice_Geneation_Bulk extends javax.servlet.http.HttpServlet { 

	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn= null;
		java.text.NumberFormat nf= null;
		java.lang.Math a= null;
		Statement stmt= null;
		ResultSet rs= null;
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
		
			if(m_chksql.equals("main_page")){ 
			
			String m_application_no=req.getParameter("application_no").trim();
			
			String m_refin_status = req.getParameter("refin_status").trim(); // added by udara 09-05-2014
			String m_refin_no     = req.getParameter("refin_no").trim(); // added by udara 09-05-2014
			
		 	String m_type=req.getParameter("type").trim();			
		  String m_count_asset="";
			String m_trn_type       = req.getParameter("trn_type");		//added by SH for Loans ###L	
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Proforma Invoice Generation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			out.println("var m_hid_row_no=0;");
			
			
			out.println("function get_vector(data_vec) {");
			
			out.println("row_no=document.Form1.hid_row_no.value;");
			out.println("	  if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M_VENDOR' && document.Form1.elements[\"TXT_VENDOR_CODE\"+row_no].value !=\"\" ){");
			out.println("    help_vendor(row_no); "); 
			out.println("			}");
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_VENDOR'  && document.Form1.elements[\"TXT_VENDOR_CODE\"+row_no].value!=\"\" ){");
			out.println("    document.Form1.elements[\"TXT_VENDOR_CODE\"+row_no].value=data_vec[0]; "); 
			out.println("			}");
			out.println("	  if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M_VENDOR_BRANCH' && document.Form1.elements[\"TXT_LOCATION_CODE\"+row_no].value !=\"\" ){");
			out.println("    help_branch(row_no); "); 
			out.println("			}");
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_VENDOR_BRANCH'  && document.Form1.elements[\"TXT_LOCATION_CODE\"+row_no].value!=\"\" ){");
			out.println("    document.Form1.elements[\"TXT_LOCATION_CODE\"+row_no].value=data_vec[0]; "); 
			out.println("			}");
			out.println("	else if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M_PRICE'  && document.Form1.elements[\"TXT_PRICING_NO\"+row_no].value!=\"\" ){");
			out.println("    price_help(row_no); "); 
			out.println("			}");
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_PRICE'  && document.Form1.elements[\"TXT_PRICING_NO\"+row_no].value!=\"\" ){");
			out.println("    document.Form1.elements[\"TXT_PRICING_NO\"+row_no].value=data_vec[0];"); 
			out.println("    document.Form1.elements[\"TXT_NET_PRICE\"+row_no].value=format_noobject(data_vec[2]);"); 
			out.println("    document.Form1.elements[\"TXT_VAT\"+row_no].value=format_noobject(data_vec[1]);"); 
			out.println("    document.Form1.elements[\"TXT_TOTAL_AMOUNT\"+row_no].value=format_noobject( Number(unformat_noobject(data_vec[1])) + Number(unformat_noobject(data_vec[2])) );"); 
			out.println("    document.Form1.elements[\"TXT_VALUE\"+row_no].value=document.Form1.elements[\"TXT_TOTAL_AMOUNT\"+row_no].value;"); 
			out.println("    document.Form1.elements[\"TXT_CURR_CODE\"+row_no].value=data_vec[3];"); 
			out.println("			}");
			out.println("	else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M7' && document.Form1.elements[\"TXT_ENGINE_NO\"+row_no].value!=\"\" ){");
			out.println("       alert('Record already exists.'); "); 
			//out.println("       m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO=view_invoices&status=Y&type=ENGINE&val='+document.Form1.elements[\"TXT_ENGINE_NO\"+row_no].value;"); // commented by udara 09-05-2014
			out.println("       m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO=view_invoices&status=Y&type=ENGINE&val='+document.Form1.elements[\"TXT_ENGINE_NO\"+row_no].value+'&refin_status="+m_refin_status+"'+'&refin_no="+m_refin_no+"';"); // added by udara 09-05-2014
			out.println("       window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("			}");
			out.println("	else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M8' && document.Form1.elements[\"TXT_CHASSIS_NO\"+row_no].value!=\"\"   ){");
			out.println("       alert('Record already exists.'); "); 
			//out.println("       m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO=view_invoices&status=Y&type=CHASSIS&val='+document.Form1.elements[\"TXT_CHASSIS_NO\"+row_no].value;"); // commented by udara 09-05-2014
			out.println("       m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO=view_invoices&status=Y&type=CHASSIS&val='+document.Form1.elements[\"TXT_ENGINE_NO\"+row_no].value+'&refin_status="+m_refin_status+"'+'&refin_no="+m_refin_no+"';"); // added by udara 09-05-2014
			out.println("       window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("			}");

					 								
			out.println("}");
			
		
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
		
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function validate_finance_no(app_no,row){");
			//out.println("alert('row'+row);");
			out.println("m_new_fin = \"TXT_NEW_FINANCE_NO_\"+row;");
			out.println("m_hid_row_no =row;");
			out.println("assignState('M_FIN_VAL');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_finance_no&application_no=\"+app_no+\"&data_val=\"+document.Form1.elements[m_new_fin].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			//out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			//out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			//out.println("return false;"); 
			//out.println("}"); 
			//out.println("else{"); 
			out.println("return true;"); 
			//out.println("}"); 
			out.println("}"); 
			
			
			
			
			out.println("function check_data(){");
			
			out.println("for(var i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			
			out.println("m_pricing_no=\"TXT_PRICING_NO\"+i");
			out.println("m_vendor_code=\"TXT_VENDOR_CODE\"+i");
			out.println("m_vendor_location=\"TXT_LOCATION_CODE\"+i");
			
			out.println("if(document.Form1.elements[m_pricing_no].value==\"\") {");
			out.println("alert('Please enter a pricing number');");
			out.println("b_flag=1;");
			out.println("break;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_vendor_code].value==\"\") {");
			out.println("alert('Please enter a vendor code');");
			out.println("b_flag=1;");
			out.println("break;");
			out.println("}");						
			out.println("else if(document.Form1.elements[m_vendor_location].value==\"\") {");
			out.println("alert('Please enter a vendor location ');");
			out.println("b_flag=1;");
			out.println("break;");
			out.println("}");		
			
			out.println("}");
			
			
			out.println("}");
			
			
			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			out.println("check_data()");
			
			out.println("if(b_flag==0)");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Proforma_Invoice_Generation_Bulk';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			
			
			
			out.println("function new_pro_invocie(application_no){	"); 
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+application_no+'';"); // commented by udara 09-05-2014  //modified by nuwan de silva 07-08-07
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+application_no+'&refin_status="+m_refin_status+"'+'&refin_no="+m_refin_no+"';"); // added by udara 09-05-2014
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
      out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type="+m_type+"&application_no="+m_application_no+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type="+m_type+"&application_no="+m_application_no+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Activate_Date_Change\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Marketing Process- Proforma Invoice Generation  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Marketing Process- Proforma Invoice Generation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		price_assign(document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		vendor_assign(document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		assign_branch(document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			
			
		
								
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear 
			out.println("	}");
			
			
			out.println("	}	"); //
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
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			out.println(""); 
			out.println("function clear_data(IfCount) {");
			out.println("		if(IfCount==\"1\"){"); 
			//out.println("document.Form1.TXT_CLIENT_CODE.value='';");
			//out.println("document.Form1.TXT_CLIENT_NAME.value='';");
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			//out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			
			out.println("}");
			
				  out.println("function help_button_client_code() {"); 
					out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE_new','1');"); 
					out.println("}"); 
		
					out.println("function help_value_assign_client_code() {"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
					out.println("get_client_details();");
					out.println("}"); 
							
				  out.println("function help_button_finance_no() {"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
					//out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.elements[m_invoice].value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_activated_date','2');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_finance_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
					
					out.println("get_client_details();");
					out.println("}"); 
					
					out.println("function help_button_application_no() {"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_activated_date_app_no','3');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_application_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("if(oBj.valout[3]=='-'){"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("}"); 
					out.println("else {"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("}"); 
					
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
					out.println("get_client_details_2();");
					out.println("}");
					
					
			out.println("function price_help(row_no) {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("m_price=\"TXT_PRICING_NO\"+row_no;");
			out.println("    document.Form1.hid_row_no.value=row_no;"); 
			out.println("    Crit = document.Form1.elements[m_price].value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','32',Crit,'PriceSql_invoice_new','1');"); 
			out.println("}"); 


			out.println("function price_assign(row_no) {"); 
			out.println("    document.Form1.elements[\"TXT_PRICING_NO\"+row_no].value=oBj.valout[2];"); 
			out.println("    net_price= 0 ;"); 
			out.println("    vat= 0 ;"); 
			out.println("    net_price= Number(oBj.valout[12]);"); 
			out.println("    vat= Number(oBj.valout[11]) ;"); 
			out.println("    document.Form1.elements[\"TXT_NET_PRICE\"+row_no].value=format_noobject(net_price);"); 
			out.println("    document.Form1.elements[\"TXT_VAT\"+row_no].value=format_noobject(vat);"); 
			out.println("    document.Form1.elements[\"TXT_TOTAL_AMOUNT\"+row_no].value=format_noobject(net_price + vat);"); 
			out.println("    document.Form1.elements[\"TXT_VALUE\"+row_no].value=document.Form1.elements[\"TXT_TOTAL_AMOUNT\"+row_no].value;"); 
			out.println("    document.Form1.elements[\"TXT_CURR_CODE\"+row_no].value=oBj.valout[30];"); 
			
			out.println("}"); 
			
			out.println("function help_vendor(row_no) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_vendor=\"TXT_VENDOR_CODE\"+row_no;");
			out.println("    document.Form1.hid_row_no.value=row_no;"); 
			out.println("    Crit = document.Form1.elements[m_vendor].value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_vendor_proforma_invoice','2');"); // m_help_TXT_VENDOR_CODE_sql
			out.println("}"); 
			
			out.println("function vendor_assign(row_no) {");
			out.println("    document.Form1.elements[\"TXT_VENDOR_CODE\"+row_no].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[\"TXT_LOCATION_CODE\"+row_no].value=oBj.valout[4];"); 
			out.println("}"); 
			
			out.println("function help_branch(row_no) {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_vendor=\"TXT_VENDOR_CODE\"+row_no;");
			out.println("    m_branch=\"TXT_LOCATION_CODE\"+row_no;");
			out.println("    document.Form1.hid_row_no.value=row_no;"); 
			out.println("    Crit =document.Form1.elements[m_branch].value+\"@\"+document.Form1.elements[m_vendor].value+\"@Y@\";"); 
		  out.println("    HelpBox('1','10','3',Crit,'m_help_TXT_MAS_VENDOR_LOCATION_sql','3');"); 
			out.println("}"); 
			
			out.println("function assign_branch(row_no) {");
			out.println("    document.Form1.elements[\"TXT_LOCATION_CODE\"+row_no].value=oBj.valout[2];"); 
			out.println("}"); 


      
			out.println("function validate_vendor(obj,row_no) {");
			out.println("document.Form1.hid_row_no.value=row_no;"); 
			out.println("assignState('M_VENDOR');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation2&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function validate_branch(obj,row_no) {");
			out.println("document.Form1.hid_row_no.value=row_no;"); 
			out.println("assignState('M_VENDOR_BRANCH');");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_val_vendor_branch_location&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function validate_price(obj,row_no) {");
			out.println("document.Form1.hid_row_no.value=row_no;"); 
			out.println("assignState('M_PRICE');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_val_pricing&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			
			out.println("function val_engine_no(obj,row_no){");
			out.println("document.Form1.hid_row_no.value=row_no;"); 
			out.println("assignState('M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_eng_no&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_chassis_no(obj,row_no){");
			out.println("document.Form1.hid_row_no.value=row_no;"); 
			out.println("assignState('M8')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_chassi_no&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	

     
			out.println("function generate_invoices(){");
			out.println("document.Form1.TXT_APPLICATION_NO.value='"+m_application_no+"'");
			out.println("m_type='"+m_type+"';");
			//out.println("alert('asd'+m_type);");
			out.println("if(m_type=='NEW'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=generate_invoice&application_no="+m_application_no+"\";");
			out.println("}"); 
			out.println("else if(m_type=='EDIT'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=edit_invoice&application_no="+m_application_no+"\";");
			out.println("}"); 
			
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
					
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function load_invoice_edit(m_applicaton_no,m_invoice_no) {");
			//out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+m_applicaton_no+'&prof_inv_no='+m_invoice_no+''"); // commented by udara 09-05-2014
			out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO='+m_applicaton_no+'&prof_inv_no='+m_invoice_no+'&refin_status="+m_refin_status+"'+'&refin_no="+m_refin_no+"' "); // added by udara 09-05-2014
			out.println("popupwin=window.open(m_url,'displayWindow3','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}");
			
			
			//===========================================================================================
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"generate_invoices()\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<input type=hidden name='TXT_APPLICATION_NO' value=\"\"></td>");
			out.println("<input type=hidden name='TXT_TR_TYPE' value=\""+m_trn_type+"\"></td>");//added by SH for Loans ###L
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing Process - Proforma Invoice Generation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
			
			
			if(m_type.equals("NEW")){
			out.println("<td width='10%'></td>");
			out.println("<td width='10%'></td>");
			out.println("<td width='10%'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			}
			else if(m_type.equals("EDIT")){
			//out.println("<td width='10%'></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='width:100'; type='button' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New Proforma Invoice\");'  onClick=new_pro_invocie('"+m_application_no+"') value=\"New Invoice\"></td>");  //class='mainbut'
			out.println("<td width='10%'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' 									onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' 									onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' 									onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			}
			
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
			//out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			//out.println("</table>"); 
			
				
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
			out.flush();
			}
		
		else if(m_chksql.equals("generate_invoice")){		
		
		String m_application_no = req.getParameter("application_no").trim();
		//String m_count_asset    = req.getParameter("count_asset").trim();
		String m_invoice_no="";
		
		String m_full_name="";
		String m_add1="";
		String m_add2="";
		String m_city_code="";
		String m_city_name="";
		String m_address="";
		String m_row_no="";


		stmt = conn.createStatement ();
		
					 rs = stmt.executeQuery(" SELECT "+
																" NVL(FULL_NAME,' ') FULL_NAME, "+
                								" NVL(ADDRESS1,'-'),"+
														    " NVL(ADDRESS2,'-'),"+
																" NVL(CITY_CODE,' ') CITY_CODE , "+
																" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),' ')  "+
								                " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE   CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
    
		boolean more5 = rs.next();		
				
				if(more5){
				m_full_name=rs.getString(1);
				m_add1=rs.getString(2);
				m_add2=rs.getString(3);
				m_city_code=rs.getString(4);
				m_city_name=rs.getString(5);
				}
				rs.close();
				//Added By Nuwan De Silva 23-05-07------------------------------
				if(!m_add1.trim().equals("-") && !m_add1.trim().equals("-")){
				m_address=m_add1=m_add1+","+m_add2;
				
				}
				else if(!m_add1.trim().equals("-") && m_add1.trim().equals("-")){
				m_address=m_add1;
				}
				else
				{
				m_address=m_add2;
				}
		
		//String Sql=" SELECT  'PI'||TO_CHAR(SYSDATE,'YYYY')||TO_CHAR(SYSDATE,'MM')||TO_CHAR(SYSDATE,'DD')||'-'||LPAD(TO_CHAR("+m_schema_name+".AF_SEQ_PER_INVOICE.NEXTVAL),4,'0') FROM DUAL ";
		
		String Sql=" SELECT "+
		" ASSET_ID,QTY ,MODEL_CODE,SUB_MODEL_CODE ,"+m_schema_name+".AF_CO_GET_YEAR_OF_MANUFAC(SUB_MODEL_CODE) "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS  "+
		" WHERE APPLICATION_NO='"+m_application_no+"' "+
		" AND   ACTIVE_STATUS='Y' ";
		
		out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"0\"> "); //cellspacing=\"0\"
		out.println(" <tr class=pdn_txtpos2 > ");//pdn_txtpos2
		out.println("  <td width=\"10%\"  align='left'>Invoice No</td> ");
		out.println("  <td width=\"10%\"  align='left'>Asset Id</td> ");
		out.println("  <td width=\"17%\"  align='left'>Pricing No *</td> ");
		out.println("  <td width=\"20%\"  align='left'>Vendor Code *</td> ");
		out.println("  <td width=\"17%\"  align='left'>Vendor Branch Location *</td> ");
		out.println("  <td width=\"10%\"  align='left'>Chassis No/Serial No</td> ");
		out.println("  <td width=\"10%\"  align='left'>Engine No</td> ");
		//out.println("  <td width=\"5%\"   align='left'></td> ");
		//out.println("  <td width=\"*%\"  align='left'>&nbsp;</td> ");
		out.println(" </tr>");
		
		int i=0;
		int line_no=0;
		rs=stmt.executeQuery(Sql);
		boolean more=rs.next();
		String m_asset_id="";
		String m_model_code="";
		String m_sub_model_code="";
		String  m_year_of_manufacture="";
		
		while(more){
		int m_count=rs.getInt(2);
		m_asset_id=rs.getString(1);
		m_model_code=rs.getString(3);
		m_sub_model_code=rs.getString(4);
		m_year_of_manufacture=rs.getString(5); // added by nuwan de silva on 17-12-2007

		i=0;
		
		while(i<m_count){
		
		if(i>0 && i%2==1){
		out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
				
		out.println("  <td width=\"10%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO"+line_no+" style=\"{width:100px}\" ></td>");
		out.println("  <td width=\"10%\"  align='left'>"+m_asset_id+"</td> ");
		out.println("  <td width=\"17%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_PRICING_NO"+line_no+" style=\"{width:100px}\" onblur=validate_price(this,'"+line_no+"')  >");
		out.println("  <input class=\"but_input\" type=\"button\" value=\"Help\"  name=BUT_TXT_PRICING_NO"+line_no+" onClick=price_help('"+line_no+"') ></td>");//onClick=\"help_button_5('1','10','32','PriceSql_invoice_new','5')\"
		out.println("  <td width=\"20%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_VENDOR_CODE"+line_no+" onblur=validate_vendor(this,'"+line_no+"') >");
		out.println("  <input class=\"but_input\" type=\"button\"  value=\"Help\" name=BUT_TXT_VENDOR_CODE"+line_no+" onClick=\"help_vendor('"+line_no+"')\" ></td>");
		out.println("  <td width=\"17%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CODE"+line_no+" style=\"{width:100px}\" onblur=validate_branch(this,'"+line_no+"') >");
		out.println("  <input class=\"but_input\" type=\"button\"  value=\"Help\" name=BUT_TXT_LOCATION_CODE"+line_no+" onClick=\"help_branch('"+line_no+"')\" ></td>");
		out.println("  <td width=\"10%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_CHASSIS_NO"+line_no+" style=\"{width:100px}\"  onblur=val_chassis_no(this,'"+line_no+"')></td>");
		out.println("  <td width=\"10%\"  align='left'><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO"+line_no+"  style=\"{width:100px}\"  onblur=val_engine_no(this,'"+line_no+"') ></td>");
		//out.println("  <td width=\"*%\"  align='left'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_TXT_VENDOR_CODE"+line_no+" disabled ></td>");
    //out.println("  <td width=\"*%\"  align='left'>&nbsp;</td> ");
		out.println("<input type=hidden name=TXT_VAT"+line_no+" value=\"\" >");
		out.println("<input type=hidden name=TXT_NET_PRICE"+line_no+" value=\"\" >");
		out.println("<input type=hidden name=TXT_TOTAL_AMOUNT"+line_no+" value=\"\" >");
		out.println("<input type=hidden name=TXT_VALUE"+line_no+" value=\"\" >");
		out.println("<input type=hidden name=TXT_CURR_CODE"+line_no+" value=\"\" >");
		
		out.println("<input type=hidden   name=TXT_MODEL_NO"+line_no+"       value='"+m_model_code+"' >");
		out.println("<input type=hidden   name=TXT_SUB_MODEL_NO"+line_no+"   value='"+m_sub_model_code+"' >");
		out.println("<input type=hidden   name=TXT_ASSET_ID"+line_no+"       value='"+m_asset_id+"' >");
		
		//out.println("<input type=hidden   name=TXT_APPLICATION_NO            value='"+m_application_no+"' >");		
		out.println("<input type=hidden   name=TXT_TO_BE_DELIVERD_TO         value='"+m_full_name+"' >");
		out.println("<input type=hidden   name=TXT_LOCATION                  value='"+m_address+"' >");
		out.println("<input type=hidden   name=TXT_CITY_CODE                 value='"+m_city_code+"' >");
		out.println("<input type=hidden   name=TXT_YEAR_MANUFACTURE          value='"+m_year_of_manufacture+"' >");

		
		out.println(" </tr>");
		i=i+1;
		line_no=line_no+1;
		}
				
		more=rs.next();
		}
 	  
		out.println("<input type=hidden name=hid_no_rec value="+line_no+">");
    out.println("</table >"); 
     
			
}

//_______________________Edit ____________________________________________________________________________

		else if(m_chksql.trim().equals("edit_invoice")){
		
		String m_application_no = req.getParameter("application_no").trim();
		String m_invoice_no="";

		stmt = conn.createStatement ();

		String Sql=" SELECT "+
    " APPLICATION_NO, "+
    " INVOICE_NO, "+
    " ASSET_ID, "+
    " PRICING_NO, "+
	  " VENDOR_CODE, "+
    " BRANCH_ID, "+
	  " NVL(CHASSIS_NO,' '), "+
    " NVL(ENGINE_NO,' '), "+
    " TO_BE_DELIVERD_TO, "+
    " VALUE, "+
    " ACTIVE_STATUS "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE APPLICATION_NO='"+m_application_no+"' "+
		" AND  ACTIVE_STATUS='Y' ";//+
		//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS GROUP BY ASSET_ID)"; // Added By Sandun on 05-12-2008
 
	
	 /*  rs= stmt.executeQuery (" SELECT NVL(A.INVOICE_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(A.PRICING_NO,'N/A'), "+
                           " NVL(B.DESCRIPTION,'N/A'), NVL(C.DESCRIPTION,'N/A'), "+
                           " NVL(A.NET_PRICE,0), NVL(A.VAT,0), NVL(A.TOTAL_AMOUNT,0), "+
                           " REPLACE(NVL(A.TO_BE_DELIVERD_TO,'N/A'),'&','-'), NVL(A.VALUE,0), NVL(A.ENGINE_NO,'N/A'), "+
                           " NVL(A.CHASSIS_NO,'N/A'), NVL(A.REG_NO,'N/A'), NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'N/A'), "+
														"nvl(b.model_code,'-'),nvl(c.SUB_CODE,'-') "+
                           " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B ,"+m_schema_name+". AF_CO_MAS_SUB_MODLE C "+
                           " WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
													 " AND A.ACTIVE_STATUS='Y' "+	
                           " AND A.MODEL_CODE = B.MODEL_CODE "+
                           " AND A.SUB_MODEL_CODE = C.SUB_CODE "); 

*/
		
		out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"0\"> "); //cellspacing=\"0\"
		out.println(" <tr class=pdn_txtpos2 > ");//pdn_txtpos2
		out.println("  <td width=\"5%\"  align='left'>No</td> ");
		out.println("  <td width=\"10%\"  align='left'>Invoice No</td> ");
		out.println("  <td width=\"10%\"  align='left'>Asset Id</td> ");
		out.println("  <td width=\"10%\"  align='left'>Pricing No *</td> ");
		out.println("  <td width=\"10%\"  align='left'>Vendor Code *</td> ");
		out.println("  <td width=\"15%\"  align='left'>Vendor Branch Location *</td> ");
		out.println("  <td width=\"10%\"  align='left'>Chassis No/Serial No</td> ");
		out.println("  <td width=\"10%\"  align='left'>Engine No</td> ");
		out.println("  <td width=\"15%\"  align='left'>To Be Deliveted To</td> ");
		out.println("  <td width=\"5%\"  align='left'>&nbsp;</td> ");
		out.println(" </tr>");
		
		

		
		int i=0;
		int j=1;
		int line_no=0;
		rs= stmt.executeQuery (Sql);
		boolean more=rs.next();
		
		while(more){
		
		if(i>0 && i%2==1){
		out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
		out.println("  <td width=\"5%\"  align='left'>"+j+"</td> ");		
		out.println("  <td width=\"10%\"  align='left' style= \"cursor:hand;cursor-color:blue\"  onClick=\"show_proforma_invoice_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td> ");
		out.println("  <td width=\"10%\"  align='left' style= \"cursor:hand;cursor-color:blue\"  onClick=\"show_asset_detail_drill('"+rs.getString(3)+"')\" ><u>"+rs.getString(3)+"</u></td> ");
		out.println("  <td width=\"10%\"  align='left' style= \"cursor:hand;cursor-color:blue\"  onClick=\"show_pricing_drill('"+rs.getString(4)+"')\" ><u>"+rs.getString(4)+"</u></td> ");
		out.println("  <td width=\"10%\"  align='left' style= \"cursor:hand;cursor-color:blue\"  onClick=\"show_vendor_drill('"+rs.getString(5)+"')\" ><u>"+rs.getString(5)+"</u></td> ");
		out.println("  <td width=\"15%\"  align='left' style= \"cursor:hand;cursor-color:blue\"  >"+rs.getString(6)+"</td> ");
		out.println("  <td width=\"10%\"  align='left'>"+rs.getString(7)+"</td> ");
		out.println("  <td width=\"10%\"  align='left'>"+rs.getString(8)+"</td> ");
		out.println("  <td width=\"15%\"  align='left'>"+rs.getString(9)+"</td> ");
		out.println("  <td width=\"5%\"  align='left'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no+" onClick=\"load_invoice_edit('"+rs.getString(1)+"','"+rs.getString(2)+"')\" ></td>");
		
		
		out.println(" </tr>");
		i=i+1;
		j=i+1;
		line_no=line_no+1;
				
		more=rs.next();
		}
 	  
		out.println("<input type=hidden name=hid_no_rec value="+line_no+">");
    out.println("</table >"); 

					   
				
      }


//__________________________________________________________________________________________________________



		}
		
	
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
