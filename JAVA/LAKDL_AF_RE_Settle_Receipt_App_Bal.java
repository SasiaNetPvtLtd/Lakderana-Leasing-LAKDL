//Option Id is 1.65 
//This File was created by SVA on 28-08-2006 
//1.65 Allocate Unallocated Receipt Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Settle_Receipt_App_Bal extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			//nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  //nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
			
      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		  window.location.href=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=main_page\";"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		  window.location.href=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=main_page\";"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		} else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
			out.println("		}"); 
			
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
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
			
			out.println("	function clear_data(){");
			out.println(" document.Form1.CLIENT_NAME.value =''");
			out.println(" document.Form1.CLIENT_CODE.value =''");
			out.println("}"); 
			
			out.println("function client_help(){");
			out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
			out.println(" document.Form1.hid_help_type.value='1' ");
			out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt','1');"); 
			out.println("}");		
			
			out.println("function client_assign(oBj){");
			out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
			out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[9]");
			//out.println(" get_Receipt_Allocation(oBj.valout[9]);");
			out.println("}");
			
			out.println("function Allocate_receipts(val){");
			out.println("  document.Form1.client_det_allo.disabled = true; "); // added by udara 05-02-2019
			out.println("document.Form1.hid_allocate_status.value='ALLOCATE';");
			out.println(" get_Receipt_Allocation(val);");
			out.println("}");
			
			out.println("function Un_Allocate_receipts(val){");
			out.println("  document.Form1.client_det_unallo.disabled = true; "); // added by udara 05-02-2019
			out.println("document.Form1.hid_allocate_status.value='UN_ALLOCATE';");
			out.println(" get_Receipt_Un_Allocation(val);");
			out.println("}");
			
			out.println("function get_vector(data_vec){ ");
			out.println("			 if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_client'  && document.Form1.CLIENT_CODE.value !='' ){");
			out.println("      client_help();");
			out.println("			}");
			
		 /*	out.println("			 if( data_vec.length >0 && document.Form1.hid_help_status.value=='H_rec_Balance'  && document.Form1.CLIENT_CODE.value !='' ){");
			out.println("      Update_Receipt_Cantract_Balances(data_vec);");
			out.println("			}");
			*/
			
			out.println("}");
			
		/*	out.println("function Update_Receipt_Cantract_Balances(val) {");
			out.println("var m_count =0;");
			out.println("m_count = parseFloat(document.Form1.hid_count.value);");
			out.println("for(j=0;j<m_count;j++){");
			out.println("m_aloc_amt = m_aloc_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+j].value));");
			out.println("}");
			out.println("}");
		*/	
			
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			
			out.println("function validate_data(){"); 
			out.println("if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
			out.println("CCODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 


			
			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Receipt_Contract_Balance';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			
			
			out.println("function cal_b_a_amount() {");
			out.println("  for(i=0;i<parseFloat(document.Form1.hid_rec_count.value);i++){");
			out.println("    document.Form1.elements['A_AMOUNT_'+i].value   = format_noobject(document.Form1.elements['A_AMOUNT1_'+i].value);"); //
			out.println("    document.Form1.elements['BA_AMOUNT_'+i].value  = format_noobject(document.Form1.elements['BA_AMOUNT1_'+i].value);"); //
			out.println("  }");
			out.println("}");
   
			
			out.println("function get_Receipt_Allocation(val) {");
			out.println(" document.Form1.hid_help_status.value='Receipt_Allocation' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=get_Receipt_Allocation&client=\"+document.Form1.CLIENT_CODE.value;"); 
			out.println("	 load_interface(m_url,'NORM');");
		  //out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("function get_Receipt_Un_Allocation(val) {");
			out.println(" document.Form1.hid_help_status.value='Receipt_Allocation' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=get_Receipt_Un_Allocation&client=\"+document.Form1.CLIENT_CODE.value;"); 
			out.println("	 load_interface(m_url,'NORM');");
		  //out.println("window.open(m_url);");	
			out.println("}");	

					
			out.println("function get_Receipt() {");
			out.println(" document.Form1.hid_help_status.value='Receipt_Contract' ");
	    out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=get_Receipt_Contract_Allocation&client=\"+document.Form1.CLIENT_CODE.value;");
			out.println("	 load_interface(m_url,'NORM');");
			out.println("}");	
			
			out.println("function get_Receipt_UnAllocation() {");
			out.println(" document.Form1.hid_help_status.value='Receipt_Contract' ");
	    out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settle_Receipt_App_Bal?chksql=get_Receipt_Contract_Un_Allocation&client=\"+document.Form1.CLIENT_CODE.value;");
			out.println("	 load_interface(m_url,'NORM');");
			out.println("}");	
			
			

		
			
			out.println("function get_vector_normal(http_response){ ");
			
			out.println(" if(document.Form1.hid_help_status.value=='Receipt_Allocation') {");
			out.println(" rec_alc.innerHTML = ''; ");
			out.println(" rec_alc.innerHTML = http_response; ");
			out.println("  document.Form1.client_det_allo.disabled = false; "); // added by udara 05-02-2019
			out.println("  document.Form1.client_det_unallo.disabled = false; "); // added by udara 05-02-2019
			out.println("}");
			
			out.println(" if(document.Form1.hid_help_status.value=='Receipt_Contract') {");
			out.println(" receipt_contract.innerHTML = ''; ");
			out.println(" receipt_contract.innerHTML = http_response; ");
			//out.println("get_Receipt_Contract_Balance();");
			out.println("cal_b_a_amount();");
			out.println("}");
			
			out.println("}");
			
			out.println("function get_Receipt_Contract_Balance(){");
			out.println(" document.Form1.hid_help_status.value='H_rec_Balance' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_get_Receipt_contract_balance&data_val=\"+document.Form1.CLIENT_CODE.value;");
			out.println("	 load_interface(m_url,'XML');");
			
			out.println("}");
			
			out.println("function chk_bal(obj) {");
			out.println("m_rec_bal    = parseFloat(unformat_noobject(document.Form1.elements['BAL_AMOUNT1_'+obj].value));");
			out.println("m_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+obj].value));");
			out.println("m_bal_amount = m_rec_bal - m_alc_amount;");
			out.println("if(m_bal_amount>0){");
			out.println("document.Form1.elements['BA_AMOUNT1_'+obj].value = format_noobject(m_bal_amount);");
			out.println("}else{");
			out.println("alert('Maximum alocated amount should be Rs.'+format_noobject(m_rec_bal));");
			out.println("document.Form1.elements['A_AMOUNT1_'+obj].value = format_noobject(m_rec_bal);");
			out.println("document.Form1.elements['BA_AMOUNT1_'+obj].value = format_noobject(0.00);");
			out.println("}");
			out.println("}");
			
			
			
			out.println("function chk_bal_2(obj) {");
			out.println("m_rec_bal    = parseFloat(unformat_noobject(document.Form1.elements['BAL_AMOUNT_'+obj].value));");
			out.println("m_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT_'+obj].value));");
			out.println("m_bal_amount = m_rec_bal - m_alc_amount;");
			out.println("if(m_bal_amount>0){");
			out.println("document.Form1.elements['BA_AMOUNT_'+obj].value = format_noobject(m_bal_amount);");
			out.println("}else{");
			out.println("alert('Maximum alocated amount should be Rs.'+format_noobject(m_rec_bal));");
			out.println("document.Form1.elements['A_AMOUNT_'+obj].value = format_noobject(m_rec_bal);");
			out.println("document.Form1.elements['BA_AMOUNT_'+obj].value = format_noobject(0.00);");
			out.println("}");
			out.println("}");
			

			
			
			out.println("function makeRequest5(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_client' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj;");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Settlement\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Receipts - Allocate To Contracts - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Receipts - Allocate To Contracts - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function show_contract_details_receipt() {");
			out.println(" get_Receipt();");
			out.println("}");
						
			out.println("function show_contract_details_Unallocated() {");
			out.println(" get_Receipt_UnAllocation();");
			out.println("}");
			
			
			out.println("function remove_contract_details() {");
			//out.println("rec.innerHTML=\"\";");
			out.println(" receipt_contract.innerHTML = \"\"; ");

			out.println("}");
		
			out.println("function Status_Change(obj,num) {");//
			out.println(" if(obj.checked==true){");
			out.println("   obj.value=\"YES\";");	
			out.println(" }else{");	
			out.println("   obj.value=\"NO\";");	
			out.println("   document.Form1.elements['Text_sett_amount'+num].value=\"0\";");	
			out.println(" }");	
			out.println("}");	
			
			out.println("function chk_status(num,num2) {");
			out.println("if(document.Form1.elements['Text_standard'+num+'_'+num2].checked){");
			//out.println("validate_check_status_inv(num,num2);"); //added by the nuwan de silva on 28-02-2009
			//out.println("if(b_flag_inv==0){"); //added by the nuwan de silva on 28-02-2009
			out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"YES\";");
			out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=true;");
			
			//out.println("}");
			//out.println("else{");
			//out.println("alert('Please select the invoices by order');");
			//out.println("  document.Form1.elements['Text_standard'+num+'_'+num2].checked      =false;");
			//out.println("  document.Form1.elements['Text_standard'+num+'_'+num2].value        =\"NO\";");
			//out.println("  document.Form1.elements['Text_sett_amount'+num+'_'+num2].value     =0;");
			//out.println("}");
		  out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"NO\";");
			out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].value=format_noobject(0.00);");
			out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=false;");
			//out.println("  unallocate_invoices(num,num2);"); //added by the nuwan de silva on 28-02-2009
			out.println("}");
			out.println("chk_bal_amt(num,num2);");
			out.println("chk_enable_disable_chkbox(num,num2);"); // added by udara 05-01-2018
			
			// added by udara 18-10-2018
			out.println(" var val_loan_flag = 'NO'; ");
			out.println(" var m_hid_count = num; ");			
			out.println(" m_counts = parseFloat(document.Form1.elements['hid_receipt_contract_'+num].value);");

			out.println("   for (var i = 0; i < m_counts; i++) {");
			//out.println("        if(document.Form1.elements['hid_transaction_type'+m_hid_count+'_'+i].value=='LOANS'){");	// commented by udara 03-12-2024
			out.println("        if(document.Form1.elements['hid_transaction_type'+m_hid_count+'_'+i].value=='LOANS' || document.Form1.elements['hid_transaction_type'+m_hid_count+'_'+i].value=='HADAGASMA'){");	// added by udara 03-12-2024
			
			out.println("            val_loan_arr_total = document.Form1.elements['hid_con_arr'+m_hid_count+'_'+i].value; ");		
		
			//out.println("               alert('val_loan_arr_total : ' + val_loan_arr_total); ");
			
			out.println("            	if((val_loan_arr_total > 0) && (document.Form1.elements['hid_finance_no'+m_hid_count+'_'+i].value!=document.Form1.elements['hid_finance_no'+m_hid_count+'_'+num2].value)){    ");
			out.println("                  val_loan_flag = 'YES';   ");
			out.println("            	}    ");	
			
			out.println("        }");
			out.println("   }");
			
			
			//out.println("   if(val_loan_flag=='YES' && document.Form1.elements['hid_transaction_type'+num+'_'+num2].value!='LOANS'){ "); // commented by udara 03-12-2024
			out.println("   if(val_loan_flag=='YES' && (document.Form1.elements['hid_transaction_type'+num+'_'+num2].value!='LOANS' || document.Form1.elements['hid_transaction_type'+num+'_'+num2].value!='HADAGASMA')){ "); // added by udara 03-12-2024
			out.println("     alert('There is a loan contract with arrears. Therefore cannot allocate to this.'); ");
			out.println("     document.Form1.elements['Text_standard'+num+'_'+num2].checked=false;");
		    out.println("     return false;");
			out.println("   }");
			// end by udara 18-10-2018
			
			out.println("}");			
			
			
			out.println("function chk_bal_amt(num,num2) {");
			out.println("var m_count =0;");
			out.println("var m_aloc_amt=0.00;");
			out.println("var m_rec_alc_amount=0.00;");
			out.println("m_count = parseFloat(document.Form1.elements['hid_receipt_contract_'+num].value);");
			//out.println("alert('m_count'+m_count);");
			out.println("for(j=0;j<m_count;j++){");
			out.println("m_aloc_amt = m_aloc_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+j].value));");
			out.println("}");
			out.println("m_rec_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+num].value));");
			out.println("document.Form1.elements['BA_AMOUNT_'+num].value = format_noobject(m_rec_alc_amount - m_aloc_amt);");
					
			out.println("}");
			
			// added by udara 05-01-2018
			out.println("function chk_enable_disable_chkbox(j,i) {");
			// j = main  i = sub
			
			out.println("  var max_i           =  parseFloat(document.Form1.elements['hid_receipt_contract_'+j].value);  ");
			out.println("  var selected_fin_no =  document.Form1.elements['hid_finance_no'+j+'_'+i].value ");
			out.println("  var checked_fin_no  = ''; ");
			
			out.println("  for(x=0;x<max_i;x++){");
			
			out.println("  		checked_fin_no =  document.Form1.elements['hid_finance_no'+j+'_'+x].value ");
			
			out.println("       if(document.Form1.elements['Text_standard'+j+'_'+i].checked==true){  ");
			
			out.println("           if(selected_fin_no!=checked_fin_no){");
			out.println("               document.Form1.elements['Text_standard'+j+'_'+x].checked  = false; ");
			out.println("               document.Form1.elements['Text_standard'+j+'_'+x].disabled = true; ");
			out.println("           }");
			
			out.println("       }");
			out.println("       else{");
			out.println("           document.Form1.elements['Text_standard'+j+'_'+x].disabled = false; ");
			out.println("       }");
			
			out.println("  }");
			
			out.println("}");
			// end by udara 05-01-2018
			
			
			out.println("function chk_alocate_amt(num,num2) {");
			out.println("m_num2    = parseFloat(unformat_noobject(num2));");
			out.println("var m_count =0;");
			out.println("var m_aloc_amt=0.00;");
			out.println("var m_tot_aloc=0.00;");
			
			out.println("m_count = parseFloat(document.Form1.elements['hid_receipt_contract_'+num].value);");
			out.println("m_tot_aloc = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT_'+num].value));");
			
			out.println("for(j=0;j<m_count;j++){");
			out.println("m_aloc_amt = m_aloc_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+j].value));");
			out.println("}");
			out.println("if(m_tot_aloc < m_aloc_amt){");
			out.println("alert('Total Allocated Amount Should Be Rs.'+format_noobject(m_tot_aloc));");
			out.println("document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = format_noobject(0.00);");
			out.println("}");
			//out.println("chk_bal_amt(num,num2);");			
			out.println("}");


				
		 out.println("</SCRIPT>");
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value()\" >"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_SETTLE_REC_APP_BALANCE' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\"> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\"> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_allocate_status' VALUE=\"\"> ");
			
						
			
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Receipts - Allocate To Contracts</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='new_window()' value=\"New\"></td>");  
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
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  


			
					out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 				
					out.println("<tr class=tr_input>");
					out.println("<td id=CCODE width=\"20%\">Client Code *</td>");
					out.println("<td width=\"*%\"><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\"  onblur=\"makeRequest5(this.value)\"  class=\"txt_input\" > ");
					out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
					out.println("<input type=button name=client_det value=\"Client Detail\" class=\"but_input\" style=\"width:90px;\" onclick=\"show_client(document.Form1.CLIENT_CODE.value)\">");
					//out.println("<input type=button name=charges value=Charges class=\"but_input\" style=\"width:90px;\" onclick=\"display_charges_pending(document.Form1.CLIENT_CODE.value)\">");
					out.println("</td>");
					out.println("</tr>");	
					
					out.println("<tr >");
					out.println("<td id=CNAME width=\"20%\">Client Name</td>");
					out.println("<td width=\"*%\"><input name=\"CLIENT_NAME\" class=\"txt_input\" type=\"text\" style=\"width:250px;\">");
					out.println("<input type=button name=client_det_allo value=\"Allocate\"    class=\"but_input\" style=\"width:90px;\" onclick=\"Allocate_receipts(document.Form1.CLIENT_CODE.value)\">");
					out.println("<input type=button name=client_det_unallo value=\"Un Allocate\" class=\"but_input\" style=\"width:90px;\" onclick=\"Un_Allocate_receipts(document.Form1.CLIENT_CODE.value)\">");
					out.println("</td></tr>");	
					out.println("</table>");	
					
					out.println("<br>");	
					
					out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 				
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					out.println("<div id=rec_alc><input type=hidden name=hid_count1 value=0>");
					out.println("</td>");
					out.println("</tr>");
				  out.println("</table>");	
					
					out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 				
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					out.println("<div id=receipt_contract><input type=hidden name=hid_count1 value=0>");
					out.println("</td>");
					out.println("</tr>");
				  out.println("</table>");	
					
					out.println("</form>"); 
		
					
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					
					out.println("</body>"); 
					out.println("</html>"); 
		
		
      } 
			
	    	else if(m_chksql.trim().equals("get_Invoice")){
			
			    String m_client      = req.getParameter("client");
          
					rs = stmt.executeQuery ("SELECT INVOICE_NO,FINANCE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),"+
					                        "       TOTAL_AMOUNT,SETTELE_AMOUNT, "+
																	"       BALANCE_TO_BE_RECEIVED,VAT_AMOUNT, "+
																	"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																	"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																	"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																	"       INVOICE_TYPE "+
																	"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																	"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																	"  WHERE CLIENT_CODE='"+m_client+"' AND "+
																	"        BALANCE_TO_BE_RECEIVED>0 AND "+
																	"        A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
                                  "	       A.ACTIVE_STATUS = 'Y' "+
																	"	ORDER BY ORDER_NO,VALUE_DATE	");

		
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Receipt Allocate / Unallocate</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("function check_status(num,num2) {");
				  //out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("if(document.Form1.elements['Text_standard'+num+'_'+num2].checked){");
					out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"YES\";");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=true;");
					//out.println(" window.opener.cal_amount('add',unformat_noobject(document.Form1.tot_val.value),unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value),num,document.Form1.elements['INV_NO_'+num+'_'+num2].value,num2);");
					out.println("}else{");
					out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"NO\";");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=false;");
					//out.println(" window.opener.cal_amount('min',unformat_noobject(document.Form1.tot_val.value),unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value),num,document.Form1.elements['INV_NO_'+num+'_'+num2].value,num2);");
					out.println("}");
					out.println("}");
					
					
				
					
					out.println("function check_amount(num,num2) {");
						//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("if(Number(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
					out.println(" alert('Amount cannot be greater than Net Amount');");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = document.Form1.elements['Hid_amount'+num].value;");
					out.println("}");
			    out.println("}");
					
					out.println("function load_data_main(num) {");
					//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("m_row='<table><tr class=pdn_txtpos2 WIDTH=100%>'+");
					out.println("      '<td WIDTH=20%>Invoice No</td>'+");
					out.println("      '<td WIDTH=10%>Date</td>'+");
					out.println("      '<td WIDTH=10%>Value Date</td>'+");
					out.println("      '<td WIDTH=15%>Invoice Amount</td>'+");
					out.println("      '<td WIDTH=15%>Balance Amount</td>'+");
					out.println("      '<td WIDTH=15%>Allocated Amount</td></tr>';");
					out.println("x=0;");
					out.println("H=window.opener.document.Form1.hid_opt_val.value;");
					out.println("for(i=0;i<Number(document.Form1.elements['hid_inv_count'].value);i++){");
					//out.println(" alert(document.Form1.elements['Text_standard'+i].checked);");
					out.println(" if(document.Form1.elements['Text_standard'+i].checked){");
					out.println("  m_row = m_row +'<tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_'+H+'_'+x+'\">'+");
					out.println("          '<td align=left ><input type=text name=\"INV_NO_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"INV_NO_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=left ><input type=text name=\"V_DATE_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"V_DATE_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=left ><input type=text name=\"D_DATE_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"D_DATE_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"INV_AM_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"AMOUNT_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"BAL_AM_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"BAL_AM_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"Text_sett_amount'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"Text_sett_amount\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '</tr>';");
					out.println("   x=x+1;");
					out.println(" }");
					out.println(" }");
					out.println(" m_row = m_row +'<input type=hidden name=hid_invoice_count_'+H+'  value='+x+'></table>';");
					//out.println(" alert('m_row ='+m_row);");
					out.println(" window.opener.document.getElementById(\"inv_\"+H).innerHTML = m_row;");
					//out.println(" window.opener.document.elements[\"hid_inv_count\"+H].value = x;");
					out.println(" window.close();");
			    out.println("}");
          
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); //load_roll_out_value();load_lock();
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input type=hidden name=\"tot_val\" value=\"0\"></td>");
									
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Invoice No</td>");
					out.println("<td  width='15%' >Finance No</td>");
          out.println("<td  width='10%' >Value Date</td>");
					out.println("<td  width='13%' >Amount</td>");
					out.println("<td  width='13%' >Setteled Amount</td>");
					out.println("<td  width='13%' >Balance Amount</td>");
					out.println("<td  width='13%' >Amount Allocat</td>");
					out.println("<td  width='8%' ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
									out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>"); 
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"V_DATE_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"SETT_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AM_"+j+"\" value=\""+(rs.getString(6))+"\">");
									//out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\" ");
  								out.println("</td>");
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\" class=\"txt_input2\" ></td>");
									
                  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									out.println("     </td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td colspan=5><input type=hidden name=hid_inv_count value="+j+"></td>");
                  //out.println("<td ></td>");
                  out.println("<td id=total></td>");
                  out.println("<td ><input type=button name=\"Proceed\" value=\"Go\"   class=mainbut1 onclick=load_data_main(); ></td>");
									out.println("</tr>");
					
          out.println("</table>");

				  out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
		
      } 	
			
			
			else if(m_chksql.trim().equals("get_Receipt_Allocation")){
			
			String m_client      = req.getParameter("client");
					
			
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+  
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ORDER BY A.EFF_VALDATE  ");
																	
			
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=center>Receipt Amount</td>");
          out.println("<td width='15%' align=center>Settled Amount</td>");
					out.println("<td width='20%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){

                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT1_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN1_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT1_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT1_"+j+"\" value=\"0\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"'),format_number(document.Form1.A_AMOUNT1_"+j+",'30')\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT1_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled><input type=hidden name=\"row_id"+j+"\" value=\""+j+"\" ></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									j+=1;
                 }

		  	out.println("<tr><input type=hidden name=hid_rec_count value="+j+"></tr></table>");
				out.println(" <br><hr>");			 
				out.println(" <table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
				out.println(" <td WIDTH=80%>&nbsp</td>");
				out.println(" <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Contracts\" class=mainbut onclick=show_contract_details_receipt(); style='width: 130px'></td>"); 
				out.println(" <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Contracts\" class=mainbut onclick=remove_contract_details(); style='width: 130px'</td>");	
				out.println(" </tr></table>");		
				out.println(" <br><hr>");	
				
						
			 }
				
				
			else if(m_chksql.trim().equals("get_Receipt_Un_Allocation")){
			
			String m_client      = req.getParameter("client");
			
			
								rs = stmt.executeQuery (" SELECT "+
				    		" A.REC_NO,   "+           //1
								" A.REC_AMOUNT ,"+         //2
								" A.APP_REC_AMOUNT,   "+   //3
								" A.BAL_TOBE_RECEIVE,   "+ //4
								" A.ALLOCATED_AMOUNT  , "+ //5
								" A.FINANCE_NO,  "+         //6
								" "+m_schema_name+".AF_CO_CHK_RECEIPT_DATE (A.REC_NO)  "+         //7  //Added by Susitha 27-04-2011
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B  "+
								" WHERE  A.REC_NO=B.REC_NO AND A.CLIENT_CODE='"+m_client+"'  AND B.STATUS NOT IN ('CAD','RET','C') AND A.BAL_TOBE_RECEIVE > 0 ");

								
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Finance No</td>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='10%' align=center>Receipt Amount</td>");
					out.println("<td width='10%' align=center>Rec.Amount App</td>");
          out.println("<td width='10%' align=center>Settled Amount</td>");
					out.println("<td width='10%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					out.println("</tr>");
      
           int j = 0;      					
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					 double m_num_chk_receip = 0;	
						
			
              while(rs.next()){	
							m_num_chk_receip=	rs.getDouble(7);
                  out.println("<tr class=tr_input1  >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_finance_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(6) +"</u><input type=hidden name=\"FINANCE_NO_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT1_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"REC_AMOUNT_APP1_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5)) +"<input type=hidden name=\"ALLO_AMOUN1_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT1_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT1_"+j+"\" value=\"0\" class=\"txt_input2\"");
								 //Added by Susitha 27-04-2011 
									if(m_num_chk_receip==0){
										out.println(" disabled ");
									}
									//////
									out.println("onBlur=\"chk_bal('"+j+"'),format_number(document.Form1.A_AMOUNT1_"+j+",'30')\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT1_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled><input type=hidden name=\"row_id"+j+"\" value=\""+j+"\" ></td>");
									out.println("</tr>");
									j+=1;
                 }


		  	out.println("<tr><input type=hidden name=hid_rec_count value="+j+"></tr></table>");
			
			  out.println(" <br><hr><br>");	
				out.println(" <table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
				out.println(" <td WIDTH=80%>&nbsp</td>");
				out.println(" <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Contracts\" class=mainbut onclick=show_contract_details_Unallocated(); style='width: 130px'></td>"); 
				out.println(" <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Contracts\" class=mainbut onclick=remove_contract_details(); style='width: 130px'</td>");	
				out.println(" </tr></table>");		
				out.println(" <br><hr>");	
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					
				
		
						
			 }
						
			
			else if(m_chksql.trim().equals("get_Receipt_Contract_Allocation")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      ="";
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ORDER BY A.EFF_VALDATE ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					 String m_rec_no="";
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\"  value=\"0\" class=\"txt_input2\" onBlur=\"chk_bal_2('"+j+"'),format_number(document.Form1.A_AMOUNT_"+j+",'30')\" disabled ></td>"); //
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\" disabled ></td>"); //value=\""+nf.format(rs.getDouble(4))+"\"
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									m_rec_no    = rs.getString(1);

							/*	rs1 = stmt1.executeQuery(" SELECT "+
																				" FINANCE_NO "+
																				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																				" WHERE APPLICATION_STATUS='ACTIVATED' "+
																				" AND CLIENT_CODE='"+m_client+"' ORDER BY ACTIVATED_DATE ");
								*/
								
								rs1 = stmt1.executeQuery(" SELECT A.FINANCE_NO,NVL(APP_REC_AMOUNT,0),NVL(BAL_TOBE_RECEIVE,0),NVL(ALLOCATED_AMOUNT,0), A.TRANSACTION_TYPE TRANSACTION_TYPE, CON_BAL_ARR "+
								" FROM "+
								" (SELECT  "+
								" FINANCE_NO, TRANSACTION_TYPE, NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'),NULL),0) CON_BAL_ARR  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
								//" WHERE APPLICATION_STATUS='ACTIVATED'  "+
								" WHERE APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL', "+
								"                              'V-RECOM','VERIFY-M','ENT_CON','VERIFY2','VERIFYL','ENT-CON','V-APP','VERIFY1','VERIFY') "+ //
								" AND CLIENT_CODE='"+m_client+"' "+
								" ORDER BY ACTIVATED_DATE ) A , "+
								
								
						
								" (SELECT "+
								" REC_NO,   "+
								" FINANCE_NO,  "+
								" APP_REC_AMOUNT,   "+
								" BAL_TOBE_RECEIVE,   "+
								" ALLOCATED_AMOUNT   "+
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL  "+
								" WHERE  REC_NO='"+m_rec_no+"'  "+
								/*"IN (  "+
								" SELECT A.REC_NO  "+
								" FROM   LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+  
								" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B  "+
								" WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND  "+
								" BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"'  "+
								" )*/
								") B "+
								" WHERE A.FINANCE_NO=B.FINANCE_NO(+) ");

																				
									boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=20% align='right'>Receipt Amount App</td>");
									out.println("  <td WIDTH=20% align='right'>Balance Amount</td>");
									out.println("  <td WIDTH=20% align='right'>Already Allocated Amount</td>");
									out.println("  <td WIDTH=20% align='right'>Alloated Amount</td>");
									out.println("  <td WIDTH=5%>Status</td></tr>");
																				
									while(more1){	
									out.println("  <tr>");
									out.println("  <input type=hidden NAME=\"hid_finance_no"+j+"_"+i+"\"  value=\""+rs1.getString(1)+"\"> ");
									out.println("  <input type=hidden NAME=\"hid_transaction_type"+j+"_"+i+"\"  value=\""+rs1.getString("TRANSACTION_TYPE")+"\"> "); // added by udara 18-10-2018
									out.println("  <input type=hidden NAME=\"hid_con_arr"+j+"_"+i+"\"  value=\""+rs1.getDouble("CON_BAL_ARR")+"\"> "); // added by udara 18-10-2018
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(2))+"\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(3))+"\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("  <td align=right><input type=text name=\"ALLOCATED_AMOUNT"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(4))+"\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("  <td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\"  VALUE=\"0.00\"  onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									out.println("  </tr>");
									i=i+1;
									more1 = rs1.next();	
									}
									out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_receipt_contract_"+j+"\" value="+i+"></table></div>");
								 }
									else{
								  out.println("  <input type=hidden name=hid_receipt_contract_"+j+"  value="+i+"></div>");
					     		}
               	 j=j+1;
										
              }
          
									out.println("<tr class=tr_input>");
				          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				 
				          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
									out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				
      }
			
			
					else if(m_chksql.trim().equals("get_Receipt_Contract_Un_Allocation")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      ="";
					
					
								rs = stmt.executeQuery (" SELECT "+
				    		" A.REC_NO,   "+           //1
								" A.REC_AMOUNT ,"+         //2
								" A.APP_REC_AMOUNT,   "+   //3
								" A.BAL_TOBE_RECEIVE,   "+ //4
								" A.ALLOCATED_AMOUNT  , "+ //5
								" A.FINANCE_NO  "+         //6
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A,  "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B  "+
								" WHERE  A.REC_NO =B.REC_NO AND A.CLIENT_CODE='"+m_client+"'  AND B.STATUS NOT IN ('CAD','RET','C')  AND A.BAL_TOBE_RECEIVE > 0 ");
					
					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Finance No</td>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='10%' align=center>Receipt Amount</td>");
					out.println("<td width='10%' align=center>Rec.Amount App</td>");
          out.println("<td width='10%' align=center>Settled Amount</td>");
					out.println("<td width='10%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					out.println("</tr>");

           int j = 0;      					
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					 String m_rec_no="",m_fin_no="";
					
              while(rs.next()){
							
							    out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_finance_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(6) +"</u><input type=hidden name=\"FINANCE_NO_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"REC_AMOUNT_APP_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\" disabled ></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled ></td>");
                  out.println("</tr>");
									
							                 
									
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									out.println("<td colspan=8 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									m_rec_no    = rs.getString(1);
									m_fin_no    = rs.getString(6);

								
								rs1 = stmt1.executeQuery(" SELECT A.FINANCE_NO,NVL(APP_REC_AMOUNT,0),NVL(BAL_TOBE_RECEIVE,0),NVL(ALLOCATED_AMOUNT,0)  "+
								" FROM "+
								" (SELECT  "+
								" FINANCE_NO  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
								" WHERE APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL', "+
								"                              'V-RECOM','VERIFY-M','ENT_CON','VERIFY2','VERIFYL','ENT-CON','V-APP','VERIFY1','VERIFY') "+ //
								" AND CLIENT_CODE='"+m_client+"' "+
								" ORDER BY ACTIVATED_DATE ) A , "+
								
								" (SELECT "+
								" A.REC_NO,   "+
								" A.FINANCE_NO,  "+
								" A.APP_REC_AMOUNT,   "+
								" A.BAL_TOBE_RECEIVE,   "+
								" A.ALLOCATED_AMOUNT   "+
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B  "+
								" WHERE A.REC_NO=B.REC_NO AND A.REC_NO='"+m_rec_no+"'  AND B.STATUS NOT IN ('CAD','RET','C') "+
								") B "+
								" WHERE A.FINANCE_NO=B.FINANCE_NO(+) AND A.FINANCE_NO!='"+m_fin_no+"' ");

																				
									boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=20% align='right'>Receipt Amount App</td>");
									out.println("  <td WIDTH=20% align='right'>Balance Amount</td>");
									out.println("  <td WIDTH=20% align='right'>Already Allocated Amount</td>");
									out.println("  <td WIDTH=20% align='right'>Alloated Amount</td>");
									out.println("  <td WIDTH=5%>Status</td></tr>");
																				
									while(more1){	
									out.println("  <tr>");
									out.println("  <input type=hidden NAME=\"hid_finance_no"+j+"_"+i+"\"  value=\""+rs1.getString(1)+"\"> ");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(2))+"\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(3))+"\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("  <td align=right><input type=text name=\"ALLOCATED_AMOUNT"+j+"_"+i+"\" disabled  value=\""+nf.format(rs1.getDouble(4))+"\"  style=\"text-align:right\" class=\"txt_input\" ></td>");
									out.println("  <td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\"  VALUE=\"0.00\"  onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"  style=\"text-align:right\" class=\"txt_input\"></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									out.println("  </tr>");
									i=i+1;
									more1 = rs1.next();	
									}
									out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_receipt_contract_"+j+"\" value="+i+"></table></div>");
								 }
									else{
								  out.println("  <input type=hidden name=hid_receipt_contract_"+j+"  value="+i+"></div>");
					     		}
               	 j=j+1;
										
              }
          
									out.println("<tr class=tr_input>");
				          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				 
				          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
									out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				
      }
			

					
			
				else if(m_chksql.trim().equals("get_manual_alocation22")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  m_rec_tot = m_rec_tot +rs.getDouble(4);
									//m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  "	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\" >");

									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");

									m_inv_bal  = m_inv_bal+rs1.getDouble(4); // XXXXXXXXX
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format((m_inv_bal-m_rec_tot))+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");  //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\" >"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
		   //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	
			
			
			
			else if(m_chksql.trim().equals("get_manual_alocation")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE)  DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  "	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI')  DESCR "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						" UNION ALL  "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE  FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
																						" A.ACTIVE_STATUS <> 'C' "+														
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"      "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						
																							" UNION ALL  "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE  FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
																						" A.ACTIVE_STATUS <> 'C' "+																						
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No </td>");
									out.println("  <td WIDTH=13%>Invoice Type</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left >"+rs1.getString(19)+"</td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style={text-decoration:underline,width='200px'} class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
									m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\"></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
			
			
			
			
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			
			
			else if(m_chksql.trim().equals("get_Receipt")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  "	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");

									m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\"></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
			
			
			else if(m_chksql.trim().equals("get_Receipt_del")){
			
			    String m_rec_no      = req.getParameter("rec_no");
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+//STATUS='P' AND
																	"	       ALLOCATED_AMOUNT>0 AND A.REC_NO = '"+m_rec_no+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='25%' align=right>Amount</td>");
					out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\" ></td>"); //add the drill down link by nuwan de silva on 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled></td>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Help\" class=mainbut1 onclick=inv_help('"+j+"');></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									
						
																	
					rs1 = stmt1.executeQuery ("SELECT A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), A.INVOICED_AMOUNT, "+
																  	"       A.SETTELED_AMOUNT,ALLOCATION_NO, A.REMARKS, A.SETTELED_AMOUNT_CURR "+
							                      "FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A "+
								                    "WHERE  RECEIPT_NO = '"+rs.getString(1)+"'");
																								
																	
									
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'>");
									
					out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
					out.println("      <td WIDTH=20%>Invoice No</td>");
					out.println("      <td WIDTH=15%>Date</td>");
					out.println("      <td WIDTH=15% align=right >Invoice Amount</td>");
					out.println("      <td WIDTH=15% align=right >Allocated Amount</td>");
					out.println("      <td WIDTH=15% align=CENTER>Status</td></tr>");
					int x=0;
					//out.println("H=window.opener.document.Form1.hid_opt_val.value;");
					//out.println("for(i=0;i<Number(document.Form1.elements['hid_inv_count'].value);i++){");
					//out.println(" alert(document.Form1.elements['Text_standard'+i].checked);");
					//out.println(" if(document.Form1.elements['Text_standard'+i].checked){");
					while(rs1.next()){
	          out.println(" <tr>");
						out.println("  <td align=left ><input type=text name=\"INV_NO_0_"+x+"\" value=\""+rs1.getString(1)+"\" class=\"txt_input2\" disabled><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_0_"+x+"\" VALUE=\""+rs1.getString(5)+"\"></td>");
						out.println("  <td align=left ><input type=text name=\"V_DATE_0_"+x+"\" value=\""+rs1.getString(2)+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"INV_AM_0_"+x+"\" value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"Text_sett_amount0_"+x+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard0_"+x+"\" onclick=check_status_del(\""+x+"\") value=\"NO\">");
						out.println(" </tr>");
						x=x+1;
					}
					//out.println(" }");
					        out.println("<input type=hidden name=hid_invoice_count_0  value="+x+"></table>");
									out.println("</div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
			
			else if(m_chksql.trim().equals("Add_Min_Amount")){
			    String type   	    = req.getParameter("type");
					String amount1	    = req.getParameter("amount1");
					String amount2	    = req.getParameter("amount2");
					
					if(type.equals("min")){
					   //out.println(" SELECT '"+amount1+"'-'"+amount2+"' FROM DUAL ");
						 rs = stmt.executeQuery (" SELECT '"+amount1+"'-'"+amount2+"' FROM DUAL ");
					}else{
					   //out.println(" SELECT '"+amount1+"'+'"+amount2+"' FROM DUAL ");
					   rs = stmt.executeQuery (" SELECT '"+amount1+"'+'"+amount2+"' FROM DUAL ");
					}
					
					if(rs.next()){
					  out.println(nf.format(rs.getDouble(1)));
					}
					
			}
			/*
			else if(m_chksql.trim().equals("get_History")){
			
			    String m_deal_no	    = req.getParameter("deal_no");
					
					
					 out.println("<html>");
					out.println("<head>");
					out.println("<title>"+header_name+"</title>    ");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("</head>");
					out.println("<Script>");
					out.println("var m_bsubmit = '0';");
					out.println("var arr_assign= new Array();");
					out.println("var m_send_val= '';");
					
					out.println("function load_all_foll(m_stat,opt) {");
					out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup\";");
	        //out.println("   window.open(m_url);");
					out.println("  setInterval('makeRequest(m_url,\"2\")',36000);");
					out.println("}");
				
	        out.println("</Script>");
					out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
					out.println("<form name=\"Form1\" method=post>");
					out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
	        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
	                  
					out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
					out.println("<tr>");
					
					out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
					out.println("<td class=\"border_wht\" valign=\"top\"> ");
					out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
					out.println("<tr> ");
					out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
					out.println("</tr>");
					out.println("<tr> ");
					out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td style=\"height: 327px\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
					out.println("<tr>");
					out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>History</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
					out.println("</td>	");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					out.println("<tr class=tr_input>");
					out.println("<td valign=top  width=100% Id=Follow_up> ");
					
					out.println("<table class=table border='0' width='100%' >");
					//out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Followup No</td>");
					out.println("<td  width='15%' >Category</td>");
          out.println("<td  width='15%' >ID No</td>");
					out.println("<td  width='15%' >Action to be taken</td>");
					out.println("<td  width='10%' >Effective Date</td>");
					out.println("<td  width='10%' >Remarks</td>");
					out.println("<td  width='10%' >Action Taken</td>");
					out.println("<td  width='10%' >Action Date</td>");
					out.println("</tr>");

           int j = 0;      					
							rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), CATEGORY_NAME,"+
																			"	       TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+
																			"	       A.ENT_USER,NVL(A.ENT_REMARKS,'-'),NVL(A.ACTION_TAKEN,'-'), "+
																			"	       TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),NVL(A.REMARKS,'-'),"+
																			"        "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,SCREEN_NAME)  "+
																			" FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
																			"		     "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
																			"	WHERE  ACTION_SET_FOR='"+m_username+"' AND "+
																			"        ORG_FOLLOWUP_NO = (SELECT ORG_FOLLOWUP_NO "+
																			"													  FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
																			"														WHERE  FOLLOW_UP_NO = UPPER('"+m_deal_no+"')) AND"+
																			"        CATEGORY_CODE=ACTION_TOBE_TAKEN AND "+
																			"        EFF_VAL_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
																			"	ORDER  BY PRIORITY ");

              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input alt=\"Click Here to get Details\" >");
									out.println("<td >"+rs.getString(1) +"</td>");
                  out.println("<td >"+rs.getString(11)+"</td>");
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
									out.println("<td >"+rs.getString(8) +"</td>");
									out.println("<td >"+rs.getString(9) +"</td>");
									
									out.println("     ");
									//out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					//out.println("<tr class=tr_input>");
          //out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");


					
					out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					
					
					
					
									
      } */	
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
