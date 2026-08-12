//--
//SCREEN NAME:CREDIT PROCESS -POST DATED
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Post_Dated_Receipt_Generation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			stmt = conn.createStatement ();
		
			if(m_chksql.equals("main_page")){ 
			
			

			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
		  if(rs.next()){
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			}
			
			
			
			
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Post Dated Cheques-Receipt Generation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_button_app_no();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
		 	out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_DATE' ){");
			out.println("     display_data(data_vec);");
			out.println("			}");
			
			
			
								
			out.println("}");
			
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			
				
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_advertisment_data&data_val=\"+obj.value+\"&ac_status=GENERATED\";");
			
		
			out.println("else if(document.Form1.hid_chk_status.value=='M_INVENTORY' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issue_Inventory_val&data_val=\"+obj.value+\"&ac_status=ENT\";");	
				
			//out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\" ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

								
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
			
			//out.println("alert('records'+document.Form1.hid_no_rec.value);");
						
			out.println("		if(validate_data()){"); 
			out.println("ckeck_data();");
			out.println("if(b_flag==0)");
						
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Post_Dated_Receipt_Generation';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			//out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 



			out.println("function load_lock(){	"); 
			
						
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			
			
			out.println("function assign_system_date(){	"); 
			
			    out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
					out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
					out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
					 out.println("document.Form1.VAL_DAY1.value='"+m_date_dd+"'");
					out.println("document.Form1.VAL_MONTH1.value='"+m_date_mm+"'");
					out.println("document.Form1.VAL_YEAR1.value='"+m_date_yy+"'");
					
					m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
					
					out.println("document.Form1.hid_bank_date_from.value='"+m_val_date+"'");
					out.println("document.Form1.hid_bank_date_to.value='"+m_val_date+"'");
			  // out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value,document.Form1.TXT_TYPE.value);");

					
			out.println("}	"); 
			
			

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Receipt_Generation?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Receipt_Generation?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Post_Dated_Cheque_Deposit\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Post Dated Cheques-Receipt Generation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Post Dated Cheques-Receipt Generation - \"+document.Form1.hid_status.value;"); 
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
			//--added by---prabash on 18-02-2012-----------**
			out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_PDC','4');");
				out.println("}");
				
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_PDC2','3');");
				out.println("}");
		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[3]");  
				out.println("}");
				out.println("function payment_no_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[2]");  
				out.println("}");
			//-----------------------------------------------**
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_value_assign_advetst_no();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_app_no();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_vehicle_no();"); 
	  	out.println("		}"); 
			//--added by---prabash on 18-02-2012-----------**
			out.println("	if(IfCount==\"3\"){"); 
			out.println("		payment_no_assign(oBj);"); 
	  		out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		client_assign(oBj);"); 
	  		out.println("		}");
			//---------------------------------------------**							
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
			out.println("	if(IfCount==\"3\"){"); 
			out.println("document.Form1.TXT_PAYMENT_NO.value='';");
	  		out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("document.Form1.CLIENT_CODE.value='';");
	  		out.println("		}");

			out.println("}");
			
			
					
			
				  out.println("function help_button_app_no() {"); 
						
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENT_CON@\"+\"Y@\";"); 
					
					out.println("    HelpBox('1','10','2',Crit,'m_help_TXT_APPLICATION_NO_PUR_ORD','1');"); 
										
					out.println("}"); 
					
		
					out.println("function help_value_assign_app_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
					out.println("get_rental_dates();");
					out.println("}"); 
			
     
			out.println("function get_rental_dates(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Rental_Date_Change?chksql=view&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			
			out.println("function get_post_dated_cheques(val1,val2,type,payee,fin_no){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Receipt_Generation?chksql=view&type=\"+type+\"&payee=\"+payee+\"&fin_no=\"+fin_no+\"&data_to=\"+val2+\"&data_from=\"+val1;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
					
		
				
					
			
			out.println("function ckeck_data(){ "); 
			
			out.println("b_flag=0;");
						
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_receipts()){"); 
			out.println("alert('No cheques selected');");
			out.println("b_flag=1;");
			out.println("}"); 
						
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			

			out.println("function count_receipts(){ ");
			
			out.println("count=0;");
		
			out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			
			out.println("m_chk_deposit_tmp=\"CHK_RECEIPT\"+i;");
			out.println("m_chk_deposit_hold_tmp=\"CHK_RECEIPT_HOLD\"+i;");
			
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("if(document.Form1.elements[m_chk_deposit_hold_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
					
			//=============Added by Prabash on 18-02-2012-------
				out.println("function cancel_check_all_chkbox()");
				out.println("{ ");
				out.println("   check_true=false;    ");
				out.println("       if(document.Form1.CANCEL_CHECK_ALL.checked==true)");
				out.println("       { ");
				out.println("          check_true=true;");
				out.println("       }else  ");
				out.println("       { ");
				out.println("    	    check_true=false;");
				out.println("       } ");
				out.println("   for(m=0;m<parseInt(document.Form1.hid_no_rec.value);m++) ");
				out.println("   { ");
				out.println("        document.Form1.elements[\"CHK_RECEIPT\"+m].checked=check_true; ");
		//		out.println("        change_val_receipt_status('m'); ");	
		//		out.println("        alert(document.Form1.elements[\"CHK_RECEIPT\"+m].value); ");
				out.println("   } ");	
				out.println("} ");
				//=======================================================================
				
				//=============Added by Prabash on 18-02-2012-----------------------------
				out.println("function cancel_check_all_chkbox2()");
				out.println("{ ");
				out.println("   check_true=false;    ");
				out.println("       if(document.Form1.CANCEL_CHECK_ALL.checked==true)");
				out.println("       { ");
				out.println("          check_true=true;");
				out.println("       }else  ");
				out.println("       { ");
				out.println("    	    check_true=false;");
				out.println("       } ");
				out.println("   for(m=0;m<parseInt(document.Form1.hid_no_rec.value);m++) ");
				out.println("   { ");
				out.println("        document.Form1.elements[\"CHK_RECEIPT_HOLD\"+m].checked=check_true; ");
			//	out.println("        change_val_receipt_status('m'); ");	
			//	out.println("        alert(document.Form1.elements[\"CHK_RECEIPT_HOLD\"+m].value); ");
				out.println("   } ");	
				out.println("} ");
				//=======================================================================
			
			out.println("function check_date2(){ ");
		 
			out.println("var date='' ");

			
			out.println(" if((document.Form1.VAL_DAY.value !=\"\")&&(document.Form1.VAL_MONTH.value !=\"\")&&(document.Form1.VAL_YEAR.value !=\"\")){");
			out.println(" if((document.Form1.VAL_DAY1.value !=\"\")&&(document.Form1.VAL_MONTH1.value !=\"\")&&(document.Form1.VAL_YEAR1.value !=\"\")){");
			
			out.println("  if(checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){");
			out.println("  if(checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){");
					
			out.println("date_from=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			out.println("date_to=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
					
			out.println("document.Form1.hid_bank_date_from.value=date_from");
			out.println("document.Form1.hid_bank_date_to.value=date_to");
			
			//out.println("get_post_dated_cheques(document.Form1.hid_bank_date_from.value,document.Form1.hid_bank_date_to.value,document.Form1.TXT_TYPE.value);");
			
			out.println(" }");
			out.println(" }");
			out.println(" }");
			out.println(" }");
			out.println("}");
			
			
			
		/*	out.println("function header(){");
			
			out.println("m_table.innerHTML=\"\"");
			out.println("lineno=0;");
			out.println("arr_size=0;");		
			
			
				
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><TR class=\"pdn_txtpos2\" align=\"center\" >'+");
			out.println("'<td width=\"10%\" align=\"left\">Finance no</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Client Code</td>' +");
			out.println("'<td width=\"15%\" align=\"left\">Client Name</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Post Dated Reference No</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Cheque No</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Cheque Date</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Payer Branch Code</td>' +");
			out.println("'<td width=\"10%\" align=\"left\">Payer Account Code</td>' +");
			out.println("'<td width=\"10%\" align=\"right\">Cheque Amount</td>' +");
			out.println("'<td width=\"5%\"  align=\"center\">Receipt</td>' +");
			out.println("'</tr></table>';");
			
		
     	out.println("}");
			*/	
							
			
		/*			out.println("function display_receipts(data_vec){");
								
				
			    out.println("var i=0;");
					out.println("var j=0;");
					out.println("sum=0;");	
					out.println("count=0;");		

					out.println("header();	");		
        
											     
					 out.println("while(i<data_vec.length){");
						
						
			out.println("m_finance_no='<td width=\"10%\" align=\"left\">'+data_vec[i]+'</td>' +");
			out.println("m_client_code='<td width=\"10%\" align=\"left\">'+data_vec[i+1]+'</td>' +");
			out.println("m_client_name='<td width=\"15%\" align=\"left\">'+data_vec[i+2]+'</td>' +");
			out.println("m_pod_ref_no='<td width=\"10%\" align=\"left\">'+data_vec[i+3]+'</td>' +");
			out.println("m_cheque_no='<td width=\"10%\" align=\"left\">'+data_vec[i]+'</td>' +");
			out.println("m_cheque_date='<td width=\"10%\" align=\"left\">'+data_vec[i]+'</td>' +");
			out.println("m_branch_code='<td width=\"10%\" align=\"left\">'+data_vec[i]+'</td>' +");
			out.println("m_acc_no='<td width=\"10%\" align=\"left\">'+data_vec[i]+'</td>' +");
			out.println("m_cheque_amount='<td width=\"10%\" align=\"right\">'+data_vec[i]+'</td>' +");
			
			out.println("m_receipt='<td width=\"5%\"  align=\"center\">Receipt</td>' +");
			
											
						out.println("m_receipt_no='<TD WIDTH=\"20%\">'+data_vec[i]+'</TD>';");
            out.println("m_cheque_no='<TD WIDTH=\"20%\">'+data_vec[i+1]+'</TD>';");		
						out.println("m_bank_account='<TD WIDTH=\"15%\">'+data_vec[i+2]+'</TD>';");	
						out.println("m_bank_name='<TD WIDTH=\"20%\">'+data_vec[i+3]+'</TD>';");	
						out.println("m_amount='<TD WIDTH=\"15%\" STYLE=\"{text-align:right;}\">'+data_vec[i+4]+'</TD>';");	
						
						out.println("if(document.Form1.hid_chk_status.value=='M4'){");
						out.println("m_deposit='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_DEPOSIT'+lineno+' VALUE=\"on\" checked onclick=\"change_val_deposit_status('+lineno+')\" disabled></td>';");			
					
						
						out.println("}");
						
						out.println("else{");
						out.println("m_deposit='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_DEPOSIT'+lineno+' VALUE=\"off\" onclick=\"change_val_deposit_status('+lineno+')\"></td>';");			
						out.println("}");
						
						out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_RECEIPT_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CHEQUE_NO'+lineno+'	VALUE='+data_vec[i+1]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_ACCOUNT'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_NAME'+lineno+'	VALUE='+data_vec[i+3]+'>'+");
			      out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_AMOUNT'+lineno+'	VALUE='+data_vec[i+4]+'>';");
	    
			         
			    
							
						out.println("if(j>0 && j%2==1){");
     				out.println("m_writedata='<TR class=\"tr_input1\">'+m_receipt_no+m_cheque_no+m_bank_account+m_bank_name+m_amount+m_deposit+'</TR>'+m_hid_input;"); 
						out.println("	}");
						out.println("	else{");
     				out.println("m_writedata='<TR class=\"tr_input\">'+m_receipt_no+m_cheque_no+m_bank_account+m_bank_name+m_amount+m_deposit+'</TR>'+m_hid_input;"); 
						out.println("	}");
						
						
	  			  
												
						out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
            out.println("m_writedata+'</table>';");
						out.println("j=j+1;");
     	   		out.println("i=i+5;");
						out.println("lineno=lineno+1;");
						out.println("arr_size=arr_size+1;");		
						out.println("}"); //End while loop
						
									
						
								
		  out.println("}"); 
				
				
		*/		
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=280,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date='' ");
		//	out.println("alert('date valaue'+val);");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			
			out.println("date=v_date+'-'+v_month+'-'+val;");
			
			
			out.println("document.Form1.hid_bank_date_from.value=date");
			
		//	out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value);");
			
		
			out.println("  }");				
			 out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			
			out.println("     document.Form1.VAL_DAY1.value=v_date;");
			out.println("     document.Form1.VAL_MONTH1.value=v_month;");
			out.println("     document.Form1.VAL_YEAR1.value=val;");
			
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			
			
			out.println("document.Form1.hid_bank_date_to.value=date1");
			
		//	out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value);");
			
		
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			out.println("function help_button_View() {");
			out.println("if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			out.println("if(document.Form1.VAL_DAY1.value!=\"\" && document.Form1.VAL_MONTH1.value!=\"\" && document.Form1.VAL_YEAR1.value!=\"\"){");
			//out.println("get_post_dated_cheques(document.Form1.hid_bank_date_from.value,document.Form1.hid_bank_date_to.value,document.Form1.TXT_TYPE.value,);"); //comment by Prabash on 18-02-2012
		  	out.println("get_post_dated_cheques(document.Form1.hid_bank_date_from.value,document.Form1.hid_bank_date_to.value,document.Form1.TXT_TYPE.value,document.Form1.CLIENT_CODE.value,document.Form1.TXT_PAYMENT_NO.value);");  //Added by Prabash on 18-02-2012
			out.println("}else{");
			out.println("  VDATE1.style.color='red';");
			out.println("}");
			out.println("}");
			out.println("else");		
			out.println("{");		
			out.println("  VDATE.style.color='red';");
			out.println("}");		
		  out.println("}");
			
			out.println("function change_type(obj) {");
			
			//out.println("if(obj.value=='APP'){");
			out.println("if(obj.value=='INV'){");
			//out.println("get_post_dated_cheques(document.Form1.hid_bank_date_from.value,document.Form1.hid_bank_date_to.value,'INV');");
			out.println("}");
			
			out.println("if(obj.value=='HOL'){");
			//out.println("get_post_dated_cheques(document.Form1.hid_bank_date_from.value,document.Form1.hid_bank_date_to.value,'HOL');");
			out.println("}");
			
			out.println("}");
			
			
		/*	out.println("function change_val_receipt_status(obj){")	;
			out.println("if(obj.checked==true){");
			out.println("obj.value='on'");
			out.println("}else if(obj.checked==false){");
			out.println("obj.value='off'");
			out.println("}");
			out.println("}");			
    */
			
			
			
			out.println("function change_val_receipt_status(obj1,obj2){");
			out.println("if(obj1.checked==true && obj2.checked==true){");
			out.println("obj1.value='on'");
			out.println("obj2.checked=false");
			out.println("obj2.value='off'");
			out.println("}else if(obj1.checked==true && obj2.checked==false){");
			out.println("obj1.value='on'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("obj1.value='off'");
			out.println("}");		
			out.println("alert('obj1'+obj1.value);");
			out.println("}");	
		

			out.println("function show_pdc_data(value){");//Added By Sandun On 20-07-2009
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Receipt_Generation?chksql=show_pdc_data&pdc_no=\"+value;");
			out.println("window.open(m_url,'s_win1','left=150,top=150,width=500,height=400 resizeable=0');");					
			out.println("}");					
			
			out.println("function mouse_over(id){");//Added By Sandun On 20-07-2009
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
								
			out.println("function mouse_out(id){");//Added By Sandun On 20-07-2009
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
		
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_POST_DATED_RECEIPT_GENERATION\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date_from' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date_to' VALUE=\"\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=0>"); 
			

			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Post Dated Cheques-Receipt Generation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table' border='0'>"); 

		
		/*	out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_app_no()\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' >Client Name</td>"); 
			out.println("<td width='40%'><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='20' style=\"width:250px;\"  disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			*/
			
			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%' ID=VDATE>Post Dated Date</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='5%' ID=VDATE>To</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='10%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
			  out.println("<td width='*%'></td>");
				out.println("</tr>");
				
			//== Added by prabash on 15-02-2012====	
				out.println("</table>");

			out.println("<table align='center' width='100%' class='table' border='0'>"); 			

				out.println("<tr class=tr_input>");
				out.println("<td width='10%'&gt;</td>");
				out.println("</tr>");
			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Client Name</td>");
				out.println("<td width='40%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='15' style=\"{width:250px;}\" size='15'onBlur='client_help()' >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Finance no</td>");
				out.println("<td><input type=text name='TXT_PAYMENT_NO' class='txt_input'  style=width:150px onBlur='payment_no_help()'>");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");

				//=====================================		
				
				out.println("<tr >"); 
				out.println("<td width='10%' >Type</td>"); 
				out.println("<td width='20%' ><select class='txt_input' type='text' name='TXT_TYPE' maxlength='1 size='1' onChange=\"change_type(this)\" >");  
				//out.println("<option value='APP' selected>Receipt</option>");			
				out.println("<option value='INV' selected>Receipt</option>");			
				out.println("<option value='HOL' >Hold</option>");		
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
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
			out.flush();
			}
		
		else if(m_chksql.equals("view")){		
		
		String m_date_from	=req.getParameter("data_from");
		String m_date_to	=req.getParameter("data_to");
		String m_type		=req.getParameter("type");
		String m_payee   	= req.getParameter("payee");	//added by Prabash on 17-02-2012
		String m_finan_no   = req.getParameter("fin_no");	//added by Prabash on 17-02-2012
		double rep_total=0;
		
		
	
/*				
rs=stmt.executeQuery("SELECT  "+
"     FINANCE_NO, "+ //1
"     CLIENT_CODE, "+//2
"     "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//3
"     NVL(POD_REF_NO,'-'), "+//4
"     NVL(CHEQUE_NO,'-'), "+//5
"     NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//6
"     NVL(REC_NO,'-'), "+//7
"     NVL(SUS_REF_NO,'-'), "+//8
"     NVL(SETTLE_MODE,'-'), "+ //9
"     NVL(PAYER_BRANCH_CODE,'-'), "+ //10
"     NVL(PAYER_ACC_NO,'-'), "+//11
"     NVL(ENTRY_TYPE,'-'), "+//12
"     NVL(STATUS,'-'), "+//13
"     NVL(CHEQUE_AMOUNT,0), "+//14
"     NVL(CURR_CODE,'-'), "+//15
"     NVL(EXCHANGE_RATE_REP_CURR,1), "+ //16
"     NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'-') BANK_NAME, "+//17
"     NVL(B.BANK_CODE,'-') "+ //modified by nuwan de silva 17-07-07 //18 // 
"     ,INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)) "+//19
"     , "+m_schema_name+".AF_CO_GET_POD_REMARKS(FINANCE_NO) REMARKS "+ //20

"     FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A ,"+
"          "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
"     WHERE TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
"     UPPER(STATUS)=UPPER('"+m_type+"') AND SETTLE_MODE='CHEQUE' "+ //modified by nuwan de silva on 22-10-07 inv
"     AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE(+) "+
"     ORDER BY CHEQUE_DATE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) ASC   ");
 */
	
	
	//Mod By Sandun on 20-07-2009
rs=stmt.executeQuery("SELECT  "+
//out.println("SELECT  "+
"     "+m_schema_name+".AF_CO_GET_PDC_FINANCE_NO(CLIENT_CODE,POD_REF_NO), "+//Added By Sandun on 20-07-2009
"     CLIENT_CODE, "+//2
"     "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//3
"     NVL(POD_REF_NO,'-'), "+//4
"     NVL(CHEQUE_NO,'-'), "+//5
"     NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//6
"     NVL(REC_NO,'-'), "+//7
"     NVL(SUS_REF_NO,'-'), "+//8
"     NVL(SETTLE_MODE,'-'), "+ //9
"     NVL(PAYER_BRANCH_CODE,'-'), "+ //10
"     NVL(PAYER_ACC_NO,'-'), "+//11
"     NVL(ENTRY_TYPE,'-'), "+//12
"     NVL(STATUS,'-'), "+//13
"     SUM(CHEQUE_AMOUNT), "+//14
"     NVL(CURR_CODE,'-'), "+//15
"     NVL(EXCHANGE_RATE_REP_CURR,1), "+ //16
"     NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'-') BANK_NAME, "+//17
"     NVL(B.BANK_CODE,'-'), "+ //modified by nuwan de silva 17-07-07 //18 // 
"     INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)), "+//19
//"     "+m_schema_name+".AF_CO_GET_POD_REMARKS(FINANCE_NO) REMARKS "+ //20
"     NVL(A.REMARKS,'-') "+//20
"     FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A ,"+
"          "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
"     WHERE TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') AND "+
"     TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') AND "+
"     UPPER(STATUS)=UPPER('"+m_type+"') AND SETTLE_MODE='CHEQUE' "+ //modified by nuwan de silva on 22-10-07 inv
"     AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE(+) "+
	" AND   UPPER("+m_schema_name+".AF_CO_GET_PDC_FINANCE_NO(CLIENT_CODE,POD_REF_NO)) LIKE UPPER('%"+m_finan_no+"%') "+ //added by Prabash on 17-02-2012
	" AND   UPPER( "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_payee+"%') "+ 				//added by Prabash on 17-02-2012
"     GROUP BY CLIENT_CODE,POD_REF_NO,CHEQUE_NO,CHEQUE_DATE,REC_NO,SUS_REF_NO,SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,ENTRY_TYPE,STATUS,CURR_CODE,EXCHANGE_RATE_REP_CURR,B.BANK_CODE,REMARKS "+
"     ORDER BY CHEQUE_DATE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) ASC   ");
			
		boolean more = rs.next();	
			out.println("<br>");	
			
			if(!more){
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr><td width='100%' align='center'><font color='red' >No Data Found..!</font></td></tr>");
			out.println("</table>");	
			}
			
			if(more){
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
			out.println("<br>");			
	
	    out.println("<tr class=tr_input>");
			out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"10%\" align=left>Finance no</td>"); 
			//out.println("<td width=\"10%\" align=left>Client Code</td>"); 
			out.println("<td width=\"10%\" align=left>Client Name</td>"); 
			out.println("<td width=\"10%\" align=left>Post Dated Reference No</td>"); 
			out.println("<td width=\"8%\" align=left>Cheque No</td>"); 
			out.println("<td width=\"8%\" align=left>Cheque Date</td>"); 
			//out.println("<td width=\"8%\" align=left>Payer Account Code</td>"); 
			//out.println("<td width=\"8%\" align=left>Payer Branch Code</td>"); 
			out.println("<td width=\"8%\" align=left>Branch Name</td>"); 
			out.println("<td width=\"10%\" align=right>Cheque Amount</td>");
			//out.println("<td width=\"5%\"  align=center>Receipt </td>");  // comment by Prabash on 18-02-2012
			//out.println("<td width=\"5%\"  align=center>Hold></td>"); 	// comment by Prabash on 18-02-2012
			out.println("<td width=\"5%\"  align=center>Receipt <INPUT TYPE=\"checkbox\" NAME=\"CANCEL_CHECK_ALL\" VALUE=\"on\" onclick=\"cancel_check_all_chkbox()\"  ></td>");  // added by Prabash on 18-02-2012
			out.println("<td width=\"5%\"  align=center>Hold<INPUT TYPE=\"checkbox\" NAME=\"CANCEL_CHECK_ALL2\" VALUE=\"on\" onclick=\"cancel_check_all_chkbox2()\"  ></td>"); 	// added by Prabash on 18-02-2012
			out.println("</tr >"); 
			
			
		 int j=0;
     while(more){
			
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
				
		//	rep_total=rs.getDouble(16)*rs.getDouble(14);
			
		//  out.println("rep_total"+rs.getDouble(16)*rs.getDouble(14));
			
		// out.println("rep_total"+rep_total);
		out.println("<td width=\"10%\" align=left style= 'cursor:hand;WORD-BREAK:BREAK-ALL' onclick=\"show_pdc_data('"+rs.getString(4)+"')\"><lable for=finance_no>"+rs.getString(1)+"</lable></td>"); //onclick=show_finance_detail_drill('"+rs.getString(1)+"') 
			if(j>0 && j%2==1){
			//out.println("<td width=\"10%\" align=left style= cursor:hand; onclick=\"show_pdc_data('"+rs.getString(4)+"')\"><input type='text' readonly style='border:none;background:silver' class='txt_input' value=\""+rs.getString(1)+"\"></td>"); //onclick=show_finance_detail_drill('"+rs.getString(1)+"') 
			}
			else{
			//out.println("<td width=\"10%\" align=left style= cursor:hand; onclick=\"show_pdc_data('"+rs.getString(4)+"')\"><input type='text' readonly style='border:none' class='txt_input' value=\""+rs.getString(1)+"\"></td>"); //onclick=show_finance_detail_drill('"+rs.getString(1)+"') //Com. Sandun on 20-07-2009
			}
			out.println("<input type=hidden name=hid_finance_no"+j+" value="+rs.getString(1)+">");
			
			//out.println("<input type=hidden name=hid_sett_mode"+j+" value="+rs.getString(9)+">");
			//out.println("<input type=hidden name=hid_curr_code"+j+" value="+rs.getString(15)+">");
			//out.println("<input type=hidden name=hid_exchange_rate"+j+" value="+rs.getDouble(16)+">");
			//out.println("<input type=hidden name=hid_rep_amount"+j+" value="+rep_total+">");
			
			//out.println("<td width=\"10%\" align=left  style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>"); ////Com. Sandun on 20-07-2009
			out.println("<input type=hidden name=hid_client_code"+j+" value="+rs.getString(2)+">");
			
			out.println("<td width=\"10%\" align=left style= cursor:hand; onclick=show_client('"+rs.getString(2)+"')  id='cli_"+j+"' onmouseout=\"mouse_out('cli_"+j+"')\" onmouseover=\"mouse_over('cli_"+j+"')\">"+rs.getString(3)+"</td>"); 
			out.println("<input type=hidden name=hid_client_name"+j+" value=\""+rs.getString(3)+"\">");
			
			out.println("<td width=\"10%\" align=left  style= cursor:hand; onclick=show_pod_cheque_drill('"+rs.getString(4)+"') id='pod_"+j+"' onmouseout=\"mouse_out('pod_"+j+"')\" onmouseover=\"mouse_over('pod_"+j+"')\" >"+rs.getString(4)+"</td>"); 
			out.println("<input type=hidden name=hid_pod_ref_no"+j+" value="+rs.getString(4)+">");
			
			out.println("<td width=\"8%\" align=left>"+rs.getString(5)+"</td>"); 
			out.println("<input type=hidden name=hid_cheque_no"+j+" value="+rs.getString(5)+">");
			
			out.println("<td width=\"8%\" align=left>"+rs.getString(6)+"</td>"); 
			out.println("<input type=hidden name=hid_cheque_date"+j+" value="+rs.getString(6)+">");
			
			//out.println("<td width=\"8%\" align=left>"+rs.getString(11)+"</td>"); 
			out.println("<input type=hidden name=hid_acc_no"+j+" value="+rs.getString(11)+">");
			
			//out.println("<td width=\"8%\" align=left style= cursor:hand; onClick=\"show_branch_drill('"+rs.getString(10)+"')\" ><u>"+rs.getString(10)+"</u></td>");  //modified by nuwan de silva 17-07-07
			out.println("<input type=hidden name=hid_branch_code"+j+" value="+rs.getString(10)+">");
			
			//out.println("<td width=\"8%\" align=left style= cursor:hand; onClick=\"show_bank_drill('"+rs.getString(18)+"')\" ><u>"+rs.getString(17)+"</u></td>");   //modified by nuwan de silva 17-07-07
			out.println("<td width=\"8%\" align=left >"+rs.getString(19)+"</td>");   //modified by nuwan de silva 17-07-07
			out.println("<td width=\"10%\" align=right>"+nf.format(rs.getDouble(14))+"</td>"); //
			out.println("<input type=hidden name=hid_cheque_amount"+j+" value="+rs.getDouble(14)+">");
			
			rep_total = rs.getDouble(14);
			out.println("<td width=\"5%\" align=center><INPUT TYPE=\"checkbox\" NAME=CHK_RECEIPT"+j+"  VALUE=\"off\" onclick=\"change_val_receipt_status(this,document.Form1.CHK_RECEIPT_HOLD"+j+")\" ></td>");			
			out.println("<td width=\"5%\" align=center><INPUT TYPE=\"checkbox\" NAME=CHK_RECEIPT_HOLD"+j+"  VALUE=\"off\" onclick=\"change_val_receipt_status(this,document.Form1.CHK_RECEIPT"+j+")\" ></td>");			
			out.println("<input type=hidden name=hid_sett_mode"+j+" value="+rs.getString(9)+">");
			out.println("<input type=hidden name=hid_curr_code"+j+" value="+rs.getString(15)+">");
			out.println("<input type=hidden name=hid_exchange_rate"+j+" value="+rs.getDouble(16)+">");
			out.println("<input type=hidden name=hid_rep_amount"+j+" value="+rep_total+">");
			out.println("<input type=hidden name=hid_rep_amount"+j+" value="+rep_total+">");
			out.println("<input type=hidden name=hid_remarks"+j+" value="+rs.getString(20)+">"); //added by nuwan de silva on 10-04-2008

			
			out.println("</tr >"); 
      more = rs.next();	
			j=j+1;
			//	 out.println("document.Form1.elements[hid_rep_amount"+j+"].value");
			
			}
		
			out.println("<input type=hidden name=hid_no_rec value="+j+">");
			//out.println("document.Form1.hid_no_rec.value ="+j+" ");
				
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
			
      out.println("</table >"); 
				}

		

}
else if (m_chksql.equals("show_pdc_data")){//Added By Sandun On 20-07-2009
      
       String m_pdc = req.getParameter("pdc_no");
       int j=1;
       double m_total=0;


      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Post Dated Cheques-Receipt Generation</TITLE>");
			out.println("<script>"); 
			
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
								
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");	
			
			out.println("</script>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<br>");
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr align='center'><td><u><b>PD Cheque Break Up Details</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
																
			out.println("<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\">");
		 
		  out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width=\"5%\" align=center>No</td>"); 
			out.println("<td width=\"10%\" align=left>Finance no</td>"); 
			out.println("<td width=\"10%\" align=right>Cheque Amount</td>"); 
			out.println("</tr >"); 
			
			rs=stmt.executeQuery(" SELECT  "+
										       " NVL(FINANCE_NO,'-'),NVL(CHEQUE_AMOUNT,0) "+
										       " FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
										       " WHERE POD_REF_NO='"+m_pdc+"' ");
												
			while(rs.next()){	
			out.println("<tr >");
			out.println("<td width=\"5%\" align=center>"+j+"</td>"); 
			out.println("<td width=\"20%\" align=left id='fin_"+j+"' style='cursor:hand' onmouseout=\"mouse_out('fin_"+j+"')\"  onmouseover=\"mouse_over('fin_"+j+"')\" onclick=show_finance_detail_drill('"+rs.getString(1)+"')  >"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"20%\" align=right>"+nf.format(rs.getDouble(2))+"</td>"); 
			out.println("</tr >"); 
			j++;
			m_total=m_total+rs.getDouble(2);
			}
			
			out.println("<tr>");
			out.println("<td width=\"5%\" colspan=2 align=right><b>Total</td>"); 
			out.println("<td width=\"10%\" align=right>"+nf.format(m_total)+"</td>"); 
			out.println("</tr >"); 
			
			out.println("</table >"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>");
			out.println("</HTML>"); 
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
