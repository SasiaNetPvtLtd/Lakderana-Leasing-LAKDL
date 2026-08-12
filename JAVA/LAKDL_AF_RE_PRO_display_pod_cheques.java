//--
//SCREEN NAME	:COLLECTION -POST DATED  CHEQUES
//CREATED BY	:Delanjali
//DATE/TIME		:
//NOTES			
//MODIFIED NUWAN DE SILVA:
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_PRO_display_pod_cheques extends javax.servlet.http.HttpServlet { 


	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String fschema_name = m_sn_methods.schema_name;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
      String m_username 						= m_sn_methods.username;
			String m_value="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			int allo_count=0;
			int write_count=0;
			
			out = res.getOutputStream();
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			m_chksql=req.getParameter("chksql");
			
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
			rs.close();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Post Dated Cheques Entry</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("var y=0;");
			out.println("var no=0;");
			out.println("var val_of=0;");
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var arr_size_edit=0;");
			out.println("var m_cheque_no;");
			out.println("var m_branch_code;");
			out.println("var flag=0");
			out.println("var edit_flag=0");
			out.println("var val;");
			out.println("var i=0;");
			out.println("var val_of;");
			out.println("var pd_arry=new Array();");
			out.println("var array_docu=new Array();");
			out.println("var cheque_arry=new Array();");
			out.println("var cheque_date_arry=new Array();");
			out.println("var cheque_amt_arry=new Array();");
			out.println("var branch_arry=new Array();");
			out.println("var acc_arry=new Array();");
			out.println("var array_branch_code=new Array();"); //added by nuwan de silva 26-07-07
			out.println("b_flag_val=0; "); //added by nuwan de silva 26-07-07
			//Added by Sandun  on 2-2-2009
			//out.println("var chque_array = new Array();");
			//out.println("var chque_date_array = new Array();");
			//out.println("var account_array = new Array();");
			//out.println("var chque_amt_array = new Array();");
			//out.println("var branch_array = new Array();");
			//out.println("var allo_amount_array = new Array();");
			//ut.println("var bal_amount_array = new Array();");
			//out.println("var cur_array = new Array();");
			out.println("var con = new Array();");
			//out.println("var store_all  =  new Array();");
			out.println("  var rev_amount=0;");
			//out.println("var cur_arry=new Array();");
			out.println("var m_code;");
			out.println("var m_sub;");
			out.println("var m_turn=\"1\";");
			out.println("var fuel=new Array();");
			out.println("var m_help=0;");
			out.println("var m_no_row;");
			out.println("var currency_arry=new Array();");
			out.println("var m_add=\"0\";");
			out.println("var m_pre_no=0");
			out.println("var m_flag");
			out.println("var m_curr");
			out.println("var count=1;");
			out.println("var m_current_row_no=0;");
			out.println("var con_length = 0;");
			//added by nuwan de silva 26-07-07------------------------
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
      out.println("var m_chq_amt;");
			out.println("var un_allo_amount=0;");
			out.println("var count_con=0");
			out.println("var new_date_write=0;");
			out.println("var m_curr_code=\"\";");
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_text.value=='A1' && document.Form1.elements['TXT_ACC_NO_'+document.Form1.hid_st.value].value!=\"\" && document.Form1.hid_text.value!='A2'){");
			out.println(" m_no_row=document.Form1.hid_st.value");
			out.println("help_button_9(m_no_row);");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_text.value=='A1' && document.Form1.elements['TXT_AC_NO_'+document.Form1.hid_st.value].value!=\"\" && document.Form1.hid_text.value!='A2'){");
			out.println(" m_no_row=document.Form1.hid_st.value");
			out.println("help_button_10(m_no_row);");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_text.value=='A2' && document.Form1.hid_click.value!=\"1\"){");
			out.println("help_button_1();");
			out.println("			}");
			
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='A2' && document.Form1.hid_click.value!=\"1\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value=data_vec[0];");
			out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[1];");
			out.println("document.Form1.TXT_CLIENT_NAME.value=data_vec[2];");
			
			//out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("    document.Form1.TXT_CURR_CODE_0.value=oBj.valout[5];"); 
			//out.println(" }");
			out.println(" 	makeRequest(data_vec[1]); ");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_text.value=='H_branch' && document.Form1.elements['TXT_BRANCH_'+document.Form1.hid_st.value].value!=\"\" ){");
			out.println(" m_no_row=document.Form1.hid_st.value");
			out.println("help_button_branch(m_no_row);");
			out.println("			}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='H_branch' && document.Form1.elements['TXT_BRANCH_'+document.Form1.hid_st.value].value!=\"\" ){");
      out.println("m_branch='TXT_BRANCH_'+document.Form1.hid_st.value");
			out.println(" document.Form1.elements[m_branch].value=data_vec[1];"); 
			out.println("			}");
					
			out.println("else if(data_vec.length==0 && document.Form1.hid_text.value=='H_branch_2' && document.Form1.elements['TXT_BR_CODE_'+document.Form1.hid_st.value].value!=\"\" ){");
			out.println(" m_no_row=document.Form1.hid_st.value");
			out.println("help_button_branch_2(m_no_row);");
			out.println("			}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='H_branch_2' && document.Form1.elements['TXT_BR_CODE_'+document.Form1.hid_st.value].value!=\"\" ){");
			out.println("m_branch='TXT_BR_CODE_'+document.Form1.hid_st.value");
			out.println(" document.Form1.elements[m_branch].value=data_vec[1];"); 
			out.println("			}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='A4' && document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value!=\"\"){");
			out.println("alert('Record already exists');");
			
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].focus()");
			out.println("document.Form1.elements[\"BUT_ADD_\"+m_pre_no1].disabled=true");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_AMOUNT_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_BRANCH_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_ACC_NO_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"BUT_TXT_ACC_NO_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CURR_CODE_\"+m_pre_no1].disabled=false");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_AMOUNT_\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_BRANCH_\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_ACC_NO_\"+m_pre_no1].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CURR_CODE_\"+m_pre_no1].value=m_curr");
			out.println("m_flag=1;");
			out.println("			}");
			out.println("else if(data_vec.length==0 && document.Form1.hid_text.value=='A4' && document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value!=\"\"){");
			out.println("m_flag=0;");
			out.println("			}");
			//added by nuwan de silva 26-07-07--------------------
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='VAL_CHEQUE' && document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_current_row_no].value!=\"\" ){"); //
			out.println("alert('Record already exists');");
			out.println("  m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=view_cheques&cheque_no='+document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_current_row_no].value+'&branch_code='+document.Form1.elements[\"hid_br_code_\"+m_current_row_no].value;");
			out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_current_row_no].value=\"\"");
			out.println("			}");
			
			//added by nuwan de silva 26-07-07--------------------
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='VAL_CHEQUE2' && document.Form1.elements[\"TXT_CH_NO_\"+m_current_row_no].value!=\"\" ){"); //
			out.println("alert('Record already exists');");
			out.println("  m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=view_cheques&cheque_no='+document.Form1.elements[\"TXT_CH_NO_\"+m_current_row_no].value+'&branch_code='+document.Form1.elements[\"hid_br_code_\"+m_current_row_no].value;");
			out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("document.Form1.elements[\"TXT_CH_NO_\"+m_current_row_no].value=\"\"");
			out.println("			}");
						
			//added by nuwan de silva 26-10-07--------------------
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='GET_DATE' ){"); //
			out.println("			assign_post_dated_dates(data_vec);");
			out.println("			}");
			//Sandun on 05-02-2009
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='GET_FIN' ){"); //
			out.println("			assign_fin(data_vec);");
			out.println("			}");
			
			out.println("else if(data_vec.length==0  &&  document.Form1.hid_text.value=='A1_BULK' ){");
			out.println("     help_button_account_bulk();");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_text.value=='H_branch_BULK'  ){");
			out.println("     help_button_branch_bulk();");
			out.println("			}");
			out.println("else if(data_vec.length>0 && document.Form1.hid_text.value=='H_branch_BULK'  ){");
      out.println("     document.Form1.TXT_BRANCH_BULK.value=data_vec[1];"); 
			out.println("     document.Form1.hid_br_code_BULK.value=data_vec[0];"); 
			out.println("			}");
			//--------------------------------------------------------end nuwan de silva 
						
			out.println("			}");
			
			out.println("function get_vector_normal(http_response) {");		
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("if(flag==0){");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println(" cheque_details.innerHTML = http_response; ");
			out.println("}else{");
			//out.println(" allocation_details.innerHTML += ''; ");//Added By Sandun on 05-02-2009
			out.println(" allocation_details.innerHTML += ''; ");
			out.println(" allocation_details.innerHTML += http_response; ");
			out.println("}");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\" ){");
			out.println("if(edit_flag==0){");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println(" cheque_details.innerHTML = http_response; ");
			out.println("}else{");
			out.println(" edit_allocation.innerHTML = ''; ");
			out.println(" edit_allocation.innerHTML = http_response; ");
			out.println("}");
			out.println("}");
			
			out.println("else if(document.Form1.SCREEN_NAME.value==\"WITHDRAW\"){");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println(" cheque_details.innerHTML = http_response; ");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function makeRequest(val) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=cheque_details&m_val=\"+document.Form1.hid_status.value+\"&m_client_code=\"+val+\"\";");//m_finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\" ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=cheque_details_edit&m_val=\"+document.Form1.hid_status.value+\"&m_client_code=\"+val+\"\";");//&m_finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"
			//out.println("alert(m_url);");
			out.println("}");
			
			out.println("else if(document.Form1.SCREEN_NAME.value==\"WITHDRAW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=cheque_details_withdraw&m_val=\"+document.Form1.hid_status.value+\"&m_client_code=\"+val+\"\";");//&m_finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"
			out.println("}");
			
			//out.println("window.open(m_url);");
			
			out.println("load_interface(m_url,'NORM');");
			out.println("}");


			out.println("function check_account(row) {");
			out.println("document.Form1.hid_text.value='A1'");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_ac='TXT_ACC_NO_'+row");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("m_ac='TXT_AC_NO_'+row");
			out.println("}");
			out.println("document.Form1.hid_st.value=row");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement_new&data_val=\"+document.Form1.elements[m_ac].value+\"&ac_status=Y\";");
			//out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			//added by nwuan de silva on 26-10-07---------------
			out.println("function check_account_bulk(Obj) {");
			out.println("document.Form1.hid_text.value='A1_BULK'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement_new&data_val=\"+Obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
					
		
			
			//added by nwuan de silva on 26-10-07---------------	
			out.println("function check_branch_bulk(Obj) {");
			out.println("document.Form1.hid_text.value='H_branch_BULK'");
			//out.println("m_branch='hid_br_code_'+row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+Obj.value+\"&ac_status=Y\";");
  		out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			
				
			out.println("function check_branch(row) {");
			out.println("document.Form1.hid_st.value=row");
			out.println("document.Form1.hid_text.value='H_branch'");
			
      out.println("m_branch='hid_br_code_'+row");
			
			//out.println("alert(document.Form1.elements[m_branch].value);");
		 // out.println(" document.Form1.hid_help_status.value='H_branch' ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+document.Form1.elements[m_branch].value+\"&ac_status=Y\";");
  	
			out.println("	 load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			
			out.println("}");
			
			
			
			out.println("function check_branch_2(row) {");
			out.println("document.Form1.hid_st.value=row");
			out.println("document.Form1.hid_text.value='H_branch_2'");
      out.println("m_branch='hid_br_code_'+row");
		 // out.println(" document.Form1.hid_help_status.value='H_branch' ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+document.Form1.elements[m_branch].value+\"&ac_status=Y\";");
  	
			out.println("	 load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			
			out.println("}");
			
			
			

			out.println("function check_finance() {");
			out.println("document.Form1.hid_text.value='A2'");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_finance_no&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=ACTIVATED\";");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_POD_Cheque_new&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=ACTIVATED\";");
			out.println("}"); 
			out.println("else{"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_POD_Cheque_edit&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=ACTIVATED\";");			
			out.println("}"); 
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");

			out.println("function cheque_no(row) {");
			out.println("ch_no='TXT_CHEQUE_NO_'+row");
			out.println("document.Form1.hid_text.value='A4'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_POD_CHEQUE&data_val='+document.Form1.elements[ch_no].value+'';");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function validate_cheque_no(row) {");
			out.println("ch_no='TXT_CHEQUE_NO_'+row");
			out.println("br_code='hid_br_code_'+row");
			
			out.println("if(document.Form1.elements[ch_no].value!='' && document.Form1.elements[br_code].value!='' ){");
			out.println("m_current_row_no=row;");
			out.println("document.Form1.hid_text.value='VAL_CHEQUE'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_POD_CHEQUE&data_val='+document.Form1.elements[ch_no].value+'&branch_code='+document.Form1.elements[br_code].value+'';");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			out.println("}");
									
			out.println("function validate_cheque_no2(row) {");
			//out.println("alert('dfg'+row);");
			out.println("ch_no='TXT_CH_NO_'+row;");
			out.println("fin_no='TXT_FIN_NO_'+row;");
			out.println("br_code='hid_br_code_'+row;");
			out.println("if(document.Form1.elements[ch_no].value!='' && document.Form1.elements[br_code].value!='' ){"); //cheque_no!=\"\" && branch_code!=\"\" &&
		  out.println("m_current_row_no=row;");
			//out.println("alert('ok');");
			out.println("document.Form1.hid_text.value='VAL_CHEQUE2';");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_POD_CHEQUE2&data_val='+document.Form1.elements[ch_no].value+'&branch_code='+document.Form1.elements[br_code].value+'&finance_no='+document.Form1.elements[fin_no].value+'';");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
      out.println("}");
			

			out.println("function validate_data(){"); 
			/*out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{");*/ 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("return true;");  //Mod By Sandun 02-02-2009
		  out.println("}");
			out.println("}"); 

			out.println("function before_submit1(){ ");			
			out.println("		if(validate_data()){");
			//out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("var m_hid=document.Form1.hid_xx.value");
			//out.println("document.Form1.hid_x.value=arr_size");
			out.println("validate_cheques();");	//<<<<<----------------------------Comment By Sandun
			//out.println("}");	
			
			/*out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("for(i=0;i<m_hid;i++){");
			out.println("m_chk_no=\"TXT_CHEQUE_NO_\"+i;");
			out.println("if(document.Form1.elements[m_chk_no].value==\"\"){");
			out.println("alert('Please enter Cheque No')");	
			out.println("count=0;");
			out.println("}");	
			out.println("else if(document.Form1.elements[\"TXT_ACC_NO_\"+i].value==\"\"){");
			out.println("alert('Please enter Account No');");
			out.println("count=0;");
		  out.println("}");
			out.println("else if(document.Form1.elements[m_chk_no].value!=\"\" && document.Form1.elements[\"TXT_ACC_NO_\"+i].value!=\"\"){");
			out.println("count=1;");
			out.println("}");
			out.println("}");	
			out.println("}");	
			*/
			//out.println("if(count>0){");
			out.println("if(b_flag_val==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+document.Form1.hid_x.value+'';");  
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+arr_size+'&fin_count='+con_length+'';");  //added by nuwan de silva 26-07-07
			out.println("		}");
			out.println("else	if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"WITHDRAW\" ){");
			out.println("if(no!=\"0\"){");
			//out.println("alert(111);");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+no+'';");
			out.println("		}");
			out.println("else if(no==\"0\"){");
		//	out.println("alert(document.Form1.hid_no_val.value);");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+document.Form1.hid_no_val.value+'';");  //hid_count
			out.println("		}");
			out.println("		}");
			
			
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}");
			out.println("} "); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 		
			

			out.println("function load_lock(){	"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("butt()");
			out.println("header();"); //added	 by nuwan de silva 26-07-07
			out.println("add_row()"); //added	 by nuwan de silva 26-07-07
			out.println("}	"); 
			out.println("}	"); 

			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function copy_window(){	"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_POD_ACK_View\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';"); 
			out.println("}"); 

			
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_pod_cheques\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Collection - Post Dated Cheques Entry - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Post Dated Cheques Entry - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();");
			out.println("}"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			
			out.println("else if(m_val==\"WITHDRAW\"){"); 
			out.println("document.Form1.hid_status.value=\"Withdraw\";"); 
			out.println("document.Form1.hid_save.value=\"Withdraw\";"); 
			out.println(" if(confirm(\"Are you sure you want to Withdraw a record?\")){  ");
			out.println("document.Form1.TXT_TYPE.disabled=true");
			out.println(" change1.innerHTML = ''; ");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println("}"); 
			
			out.println("}"); 
			
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CHEQUE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHEQUE_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_REC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SUS_REF_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SETTLE_MODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYER_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYER_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ENTRY_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_REC_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_OTH_COMMENTS.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_BY.disabled=true;"); 
			out.println("document.Form1.TXT_EFF_VALDATE.disabled=true;"); 
			out.println("document.Form1.TXT_REALISED_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REC_AMOUNT_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE_BANK.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE_REP_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_GAIN_LOSS.disabled=true;"); 
			out.println("document.Form1.TXT_REC_AMOUNT_REP_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_BANK_DATE.disabled=true;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";");  
			out.println("document.Form1.TXT_TYPE.disabled=true");
			out.println(" change1.innerHTML = ''; ");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			out.println("}"); 
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
			
			out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_CODE.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_NAME.value = \"\" ");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println("}");
							
			out.println("		if(IfCount==\"8\"){"); 
			out.println("m_branch=\"TXT_BRANCH_\"+document.Form1.hid_val.value;");
			out.println("m_branch_code=\"hid_br_code_\"+document.Form1.hid_val.value;");
			out.println("document.Form1.elements[m_branch].value='';"); 
			out.println("document.Form1.elements[m_branch_code].value='';"); 
			out.println("}");
			
			out.println("		if(IfCount==\"12\"){"); 
			out.println("m_branch=\"TXT_BR_CODE_\"+document.Form1.hid_val.value;");
			out.println("m_branch_code=\"hid_br_code_\"+document.Form1.hid_val.value;");
			out.println("document.Form1.elements[m_branch].value='';"); 
			out.println("document.Form1.elements[m_branch_code].value='';"); 
			out.println("}");
			
			out.println("		if(IfCount==\"10\"){"); 
			out.println("m_ac_no=\"TXT_AC_NO_\"+document.Form1.hid_val.value;");
			out.println("document.Form1.elements[m_ac_no].value='';"); 
			out.println("}");
			
			out.println("		if(IfCount==\"9\"){"); 
			out.println("m_ac_no=\"TXT_ACC_NO_\"+document.Form1.hid_val.value;");
			out.println("document.Form1.elements[m_ac_no].value='';"); 
			out.println("}");
			
			
			
			out.println("}");


			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			/*out.println("	if(oBj.valout[1] ==\" \" && IfCount==\"1\"){"); 
			out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_CODE.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_NAME.value = \"\" ");
			out.println(" cheque_details.innerHTML = ''; ");
			out.println(" }else");
			*/
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"9\"){"); 
			out.println("		help_value_assign_9(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"10\"){"); 
			out.println("		help_value_assign_10(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("help_value_assign_1(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"14\"){"); 
			out.println("client_help_assign(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"8\"){"); 
			out.println("branch_assign(oBj);"); 
	  	out.println("		}");
			
			out.println("		if(IfCount==\"12\"){"); 
			out.println("branch_assign_2(oBj);"); 
	  	out.println("		}");
			
			out.println("		if(IfCount==\"11\"){"); 
			out.println("branch_assign_bulk(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"13\"){"); 
			out.println("help_value_assign_account_bulk(oBj);"); 
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
			out.println("	clear_data(IfCount);");
			out.println("	}");
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 


			/*out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			*/
			
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			
			out.println("function help_button_1() {"); 
			out.println("document.Form1.hid_click.value=\"1\"");
			out.println("    document.Form1.hid_help_type.value=\"1\";");			
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_1_sql','1');"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_finance_no_post_dated_sql','1');"); 
			out.println("}"); 			
			out.println("}"); 		
			
			
			out.println("function help_value_assign_1(oBj) {");
			out.println("document.Form1.hid_click.value=\"0\"");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[5];"); 
			
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_TYPE.value==\"N\" ){"); //modified by nuwan de silva on 26-10-07
			out.println("    document.Form1.TXT_CURR_CODE_0.value=oBj.valout[5];"); 
			out.println(" }");
			
			out.println(" 	makeRequest(oBj.valout[3]); ");
			out.println("}"); 
			

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CHEQUE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CHEQUE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CHEQUE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_REC_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_REC_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_SUS_REF_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUS_REF_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_PAYER_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PAYER_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_PAYER_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_PAYER_ACC_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PAYER_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_PAYER_ACC_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8() {"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_9(row) {");
			out.println("m_ac='TXT_ACC_NO_'+row");
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    Sql = \"m_help_TXT_ACCOUNT_NO_1_sql\";");
			// Modified by Dineth on 2008-08-27
			out.println("    val1=\"Y\"; ");
			//out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+val1+\"@\";"); 
			// End by Dineth on 2008-08-27
			out.println("    HelpBox(0,10,0,Crit,Sql,9);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9(oBj) {"); 
			out.println("m_ac='TXT_ACC_NO_'+document.Form1.hid_val.value");
			out.println("m_br='TXT_BRANCH_'+document.Form1.hid_val.value");
			out.println("m_branch_code='hid_br_code_'+document.Form1.hid_val.value");
							
			out.println("    document.Form1.elements[m_ac].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[m_branch_code].value=oBj.valout[3];");		//kkkkkkkkkkkkkkkkkkkkkkkkk
			out.println("    document.Form1.elements[m_br].value=oBj.valout[4];"); 
			out.println("validate_cheque_no(document.Form1.hid_val.value);");	 //added by nuwan de silva 27-07-07
			out.println("}"); 
			
			
				//Branch Help
				out.println("function help_button_branch_2(row){");
			  out.println("document.Form1.hid_val.value=row");

				out.println("m_branch='hid_br_code_'+row"); 
				out.println("Crit=document.Form1.elements[m_branch].value+\"@\"+\"Y@\";");
				//out.println(" document.Form1.hid_help_type.value='8' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_CODE_sql','12');");
				
				out.println("}");		
				
				out.println("function branch_assign_2(oBj){");
				out.println("m_branch='TXT_BR_CODE_'+document.Form1.hid_val.value");
				out.println("m_branch_code='hid_br_code_'+document.Form1.hid_val.value");
				out.println(" document.Form1.elements[m_branch_code].value=oBj.valout[2];");
				out.println(" document.Form1.elements[m_branch].value=oBj.valout[3];"); 
				out.println("validate_cheque_no2(document.Form1.hid_val.value);");	 //added by nuwan de silva 27-07-07
				out.println("}");	
				
					//Branch Help
				out.println("function help_button_branch(row){"); //////////////////////////////////////////////////////////////////////
			  out.println("document.Form1.hid_val.value=row");

				out.println("m_branch='TXT_BRANCH_'+row");   
				//out.println("m_branch='hid_br_code_'+row");
				out.println("Crit=document.Form1.elements[m_branch].value+\"@\"+\"Y@\";");
				//out.println(" document.Form1.hid_help_type.value='8' ");
				//out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_CODE_sql','8');"); //comment by nuwan de silva
				out.println("HelpBox('1','10','0',Crit,'m_help_txt_branch_code_new_sql','8');"); 

				out.println("}");		
				
				//Branch Help bulk
				out.println("function help_button_branch_bulk(){"); 
				out.println("Crit=document.Form1.TXT_BRANCH_BULK.value+\"@\"+\"Y@\";");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_CODE_sql','11');");
				out.println("}");		
				
				out.println("function branch_assign_bulk(oBj){");
				out.println("    document.Form1.TXT_BRANCH_BULK.value=oBj.valout[3];"); 
				
				out.println("    document.Form1.hid_br_code_BULK.value=oBj.valout[2];"); 
				
				out.println("}");		
				
				out.println("function help_button_account_bulk(row) {");
				out.println("    Sql = \"m_help_TXT_ACCOUNT_NO_1_sql\";");
				out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox(0,10,0,Crit,Sql,13);"); 
				out.println("}"); 
				out.println(""); 
								
				//account Help bulk
				out.println("function help_value_assign_account_bulk(oBj) {"); 
				//out.println("m_ac='TXT_ACC_NO_'+document.Form1.hid_val.value");
				//out.println("m_br='TXT_BRANCH_'+document.Form1.hid_val.value");
				//out.println("m_branch_code='hid_br_code_'+document.Form1.hid_val.value");
				out.println("    document.Form1.TXT_AC_NO_BULK.value=oBj.valout[2];"); 
				//out.println("    document.Form1.elements[m_branch_code].value=oBj.valout[3];");		//kkkkkkkkkkkkkkkkkkkkkkkkk
				out.println("    document.Form1.hid_br_code_BULK.value=oBj.valout[3];"); 
			  out.println("    document.Form1.TXT_BRANCH_BULK.value=oBj.valout[4];"); 
				//out.println("validate_cheque_no(document.Form1.hid_val.value);");	 //added by nuwan de silva 27-07-07
				out.println("}"); 


				
				out.println("function branch_assign(oBj){");
				out.println("m_branch='TXT_BRANCH_'+document.Form1.hid_val.value"); //hid_br_code_
				out.println("m_branch_code='hid_br_code_'+document.Form1.hid_val.value");
				out.println(" document.Form1.elements[m_branch_code].value=oBj.valout[2];"); 
				//out.println(" document.Form1.elements[m_branch].value=oBj.valout[3];"); 
				out.println(" document.Form1.elements[m_branch].value=oBj.valout[2]+' - '+oBj.valout[4]+' - '+oBj.valout[3];"); 
        out.println("validate_cheque_no(document.Form1.hid_val.value);");	 //added by nuwan de silva 27-07-07
				out.println("}");	
			
			out.println("function help_button_10(row) {");
			out.println("m_ac='TXT_AC_NO_'+row");
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    Sql = \"m_help_TXT_ACCOUNT_NO_1_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,10);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_10(oBj) {"); // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
			out.println("m_ac='TXT_AC_NO_'+document.Form1.hid_val.value");
			out.println("m_br='TXT_BR_CODE_'+document.Form1.hid_val.value");
			out.println("m_branch_code='hid_br_code_'+document.Form1.hid_val.value");
			out.println("    document.Form1.elements[m_ac].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[m_branch_code].value=oBj.valout[3];");
			out.println("    document.Form1.elements[m_br].value=oBj.valout[4];"); 
			out.println("validate_cheque_no2(document.Form1.hid_val.value);");	 //added by nuwan de silva 27-07-07

			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_POD_REF_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_POD_REF_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_POD_REF_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_POD_REF_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CHEQUE_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CHEQUE_DATE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REC_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_SETTLE_MODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_PAYER_BRANCH_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_PAYER_ACC_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_ENTRY_TYPE.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_REC_AMOUNT.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_OTH_COMMENTS.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_RECON_STATUS.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_RECON_DATE.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_RECON_BY.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_REALISED_DATE.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_REC_AMOUNT_CURR.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE_BANK.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE_REP_CURR.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_EXCHANGE_GAIN_LOSS.value=oBj.valout[26];"); 
			out.println("    document.Form1.TXT_REC_AMOUNT_REP_CURR.value=oBj.valout[27];"); 
			out.println("    document.Form1.TXT_BANK_DATE.value=oBj.valout[28];"); 
			out.println("}"); 
			
					
			out.println("function cheque(row){");
			out.println("m_pre_no=row-1");
			out.println("if(m_pre_no>=0){");
			out.println("cheque_no(m_pre_no)");
			out.println("}");
			out.println("}");
	
	
			
			out.println("function check_cheque(row){");
			out.println("count=1");
			out.println("for(var f=0;f<document.Form1.hid_x.value;f++){");
			out.println("m_pre_no1=row-1");
			out.println("if(m_pre_no1<0){");
			out.println("break");
			out.println("}");
			out.println("else if(m_pre_no1>=0){");
			out.println("cheque_no(m_pre_no1)");
			out.println("}");
			
			out.println("if(document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].value!=\"\" && document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value!=\"\" && m_flag!=0){");
			out.println("if(document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].value==document.Form1.elements[\"TXT_CHEQUE_NO_\"+m_pre_no1].value){");
			out.println("				alert('Record already entered');");
			out.println("m_flag=0");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].value=\"\"");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].focus()");
			out.println("break");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("if(m_flag==0){");
			out.println("if( document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].value==\"\"){");
			out.println("document.Form1.elements[\"TXT_CHEQUE_NO_\"+row].focus()");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.elements[\"TXT_ACC_NO_\"+row].value==\"\"){");
			out.println("document.Form1.elements[\"TXT_ACC_NO_\"+row].focus()");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function tab_set(row){");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+row].focus()");
			out.println("}");
			
			
			out.println("function onclick_add(y)");
			out.println("{");
			//out.println("alert(6434);");
			//out.println("header();");
			//out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("val_of=document.Form1.hid_x.value;");
			out.println("var chn_no;");
			out.println("var chn_no1;");
			out.println("chn_no='TXT_CHEQUE_NO_'+y;");
			out.println("m_chq_date='TXT_CHEQUE_DATE_'+y;");
			out.println("m_chq_amt='TXT_CHEQUE_AMOUNT_'+y;");
			out.println("m_branch='TXT_BRANCH_'+y;");
			out.println("m_acc='TXT_ACC_NO_'+y;");
			out.println("m_acc_bt='BUT_TXT_ACC_NO_'+y;");
			out.println("m_cur='TXT_CURR_CODE_'+y;");
			out.println("m_btn_branch='BUT_TXT_BRANCH_'+y;");
			out.println("m_chn_no=document.Form1.elements[chn_no].value;");
			
			out.println("if(m_chn_no=='' && m_flag==0)");
			out.println("{");
			out.println("document.Form1.elements[chn_no].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[m_acc].value=='' && m_flag==0)");
			out.println("{");
			out.println("document.Form1.elements[m_acc].focus()");
			out.println("}");			
			
			out.println("if(m_chn_no!=\"\" && document.Form1.elements[m_acc].value!=\"\"){");
			out.println("document.Form1.elements[chn_no].disabled=true");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+y].disabled=true");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+y].disabled=true");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+y].disabled=true");
			out.println("document.Form1.elements[m_chq_amt].disabled=true");
			out.println("document.Form1.elements[m_branch].disabled=true");
			out.println("document.Form1.elements[m_acc].disabled=true");
			out.println("document.Form1.elements[m_acc_bt].disabled=true");
			out.println("document.Form1.elements[m_cur].disabled=true");
			out.println("document.Form1.elements[m_btn_branch].disabled=true");
			out.println("document.Form1.elements[\"BUT_ADD_\"+y].disabled=true");
			out.println("y=y+1;	");
			
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); //wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
			out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\"  name=TXT_CHEQUE_NO_'+y+' maxlength=\"8\" size=\"8\" value=\"\" onblur=\"check_cheque('+y+'),tab_set('+y+')\" ></td>'+"); 
			out.println("'<td width=\"12%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+y+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+y+')\" >'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+y+'  maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+y+')\" >'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+y+'  maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+y+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+y+'></td>'+"); //Calendar - Added by Chandana on 19/06/2007 for Ref No.294 <a href style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+y+')\">Calendar</a>
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+y+')\">Calender</td>'+"); //added by nuwan de silva
			out.println("'<td width=\"14%\"  align=\"right\"><input class=\"txt_input\" style=\"{text-align:right}\" type=\"text\" name=TXT_CHEQUE_AMOUNT_'+y+' maxlength=\"10\" size=\"10\" onblur=\"check_ntm('+y+')\"></td>'+"); 
		  out.println("'<td width=\"14%\" ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+y+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+y+')\"></td>'+"); 
		  out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+y+' value=\"...\" onClick=\"help_button_9('+y+')\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+y+' maxlength=\"20\" size=\"20\" ><input type=\"hidden\" name=hid_br_code_'+y+' ></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+y+' value=\"...\" onClick=\"help_button_branch('+y+')\"></td>'+"); 

			out.println("'<td width=\"9%\" ><select class=txt_input type=text name=TXT_CURR_CODE_'+y+' maxlength=1 size=1>'+");  
			  rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+
																"ORDER  BY DEFAULT_VALUE DESC ");
			boolean more1 = rs.next();		
		 	while(more1){
		
			out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
			more1=rs.next();
			}
			
			out.println("'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+y+' value=\"Add\" onClick=\"onclick_add('+y+')\"></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+y+' value=\"Delete\" onClick=\"onclick_del('+y+')\"></td>'+"); 
			out.println("'<td width=\"*%\" >&nbsp;</td>' +");
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("check_cheque(y)");
			out.println("document.Form1.hid_x.value=y;");
			out.println("document.Form1.hid_xx.value=y+1;");			
			out.println("}");			
			out.println("}");			
			out.println("}");


			out.println("function onclick_del(y)");
			out.println("{");
			
			out.println("if(y==\"0\" && (document.Form1.hid_x.value==\"1\" || document.Form1.hid_x.value==\"0\")){	");
			//out.println("new_window()	");
				out.println("}	");
						
			
			out.println("else{	");	
			
			out.println("var e=0;");
			out.println("val_of=document.Form1.hid_x.value;");
			out.println("for (var i=0; i<=val_of;i++)");
			out.println("{	");
			out.println("m_chq='TXT_CHEQUE_NO_'+i;");
			out.println("m_chq_date='TXT_CHEQUE_DATE_'+i;");
			out.println("m_chq_amt='TXT_CHEQUE_AMOUNT_'+i;");
			out.println("m_branch='TXT_BRANCH_'+i;");
			out.println("m_acc='TXT_ACC_NO_'+i;");
			out.println("m_cur='TXT_CURR_CODE_'+i;");
			out.println("if(y==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
			out.println("if(document.Form1.elements[m_chq].value=='')");
			out.println("{");
			out.println("cheque_arry[e]=0");
			out.println("cheque_date_arry[e]=0;");
			out.println("cheque_amt_arry[e]=0;");
			out.println("branch_arry[e]=0;");
			out.println("acc_arry[e]=0;");
			out.println("cur_arry[e]=0;");
			out.println("}");
			out.println("else{");
			out.println("cheque_arry[e]=document.Form1.elements[m_chq].value;");
			out.println("cheque_date_arry[e]=document.Form1.elements[m_chq_date].value;");
			out.println("cheque_amt_arry[e]=document.Form1.elements[m_chq_amt].value;");
			out.println("branch_arry[e]=document.Form1.elements[m_branch].value;");
			out.println("acc_arry[e]=document.Form1.elements[m_acc].value;");
			out.println("cur_arry[e]=document.Form1.elements[m_cur].value;");
			out.println("e=e+1;");
			out.println("	}");
			out.println("}");
			out.println("val_of=val_of-1;");
			//out.println("change1.innerHTML='';");
			out.println("get_data1(val_of);");
			out.println("document.Form1.hid_x.value=val_of;");
			out.println("document.Form1.hid_xx.value=val_of+1;");

			out.println("}");
			out.println("}	");
			
			///============added	 by nuwan de silva 26-07-07================================================================================//
		 	out.println("function header(){");			
			out.println("change1.innerHTML=\"\"");
			out.println("if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("'<tr align=left>'+");  
			out.println("'<td width=\"10%\"><input class=\"but_input\" type=\"button\" name=MORE_BUT_CON value=\"Add\" onClick=\"add_row()\"></td>'+"); 
			out.println("'</tr></table>';");
			out.println("}");			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" >'+");
			out.println("'<tr>'+");
			 out.println("'<td width=\"12%\" align=left ><b>Cheque No *</td> '+");
		  out.println("'<td width=\"12%\" align=left ><b>Cheque Date</td> '+");
		  out.println("'<td width=\"5%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"14%\" align=right><b>Cheque Amount</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Account No</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"16%\" align=left ><b>Branch</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Currency</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'</tr></table>';");
			
			out.println("document.Form1.hid_SYS_DATE_DD.value='"+m_date_dd+"'");
			out.println("document.Form1.hid_SYS_DATE_MM.value='"+m_date_mm+"'");
			out.println("document.Form1.hid_SYS_DATE_YY.value='"+m_date_yy+"'");
			
		 	out.println("}");
				
				
			// Added by Udara on 18-02-2012
			out.println("function validate_acc_no(row_num){");   
			out.println("	if(isNaN(document.getElementById(\"TXT_ACC_NO_\"+row_num).value)){");
			out.println("    	alert('Invalid account number'); ");
			out.println("   	document.getElementById(\"TXT_ACC_NO_\"+row_num).value=''; ");
			out.println("   	document.getElementById(\"TXT_ACC_NO_\"+row_num).focus(); ");
			out.println("   }");
			out.println("}");
			// End by Udara on 18-02-2012
				
				
				
			out.println("function add_row(){"); 			
			//out.println("alert('xcv');");			
			out.println(" var b_flag=0;");					
			out.println("if(lineno!=0){");
			
			out.println("count=lineno-1;");
			out.println("m_cheque_no=\"TXT_CHEQUE_NO_\"+count");
			out.println("m_chq_date='TXT_CHEQUE_DATE_'+count;");
			out.println("m_chq_amt='TXT_CHEQUE_AMOUNT_'+count;");
			out.println("m_branch='TXT_BRANCH_'+count;");
			out.println("m_acc='TXT_ACC_NO_'+count;");
			out.println("m_br_code='hid_br_code_'+count;");			
			/*
			out.println("if(document.Form1.elements[m_cheque_no].value==\"\") {");
			out.println("alert('Cheque no can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_chq_date].value==\"\") {");
			out.println("alert('Cheque date can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_chq_amt].value==\"\") {");
			out.println("alert('Cheque amount can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_acc].value==\"\") {");
			out.println("alert('Account no can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
			out.println("alert('Branch can not be blank.');");
			out.println("b_flag=1;");
				
			out.println("}");
		
			out.println("else{");			
			out.println("b_count=0;");
			out.println("tmp_cheque_no=document.Form1.elements[m_cheque_no].value;");
			out.println("tmp_br_code=document.Form1.elements[m_br_code].value;");
			out.println("for(var i=0;i<count;i++){");
			out.println("m_tmp_cheque_no=\"TXT_CHEQUE_NO_\"+i");
			out.println("m_tmp_br_code='hid_br_code_'+i;");
			out.println("if(lineno>=2){");
			out.println("if(document.Form1.elements[m_tmp_cheque_no].value==tmp_cheque_no && document.Form1.elements[m_tmp_br_code].value==tmp_br_code ){");
			out.println("alert('Cheque Number Can not Be Duplicated')");
			out.println("b_flag=1;");
			out.println("b_count=1};");
			out.println("if(b_count==1){");
			out.println("break;}");
			out.println("}");
			out.println("}");
			out.println("}");	
			*/
			out.println("}");
      out.println("if(b_flag==0){");
			out.println("if(lineno!=0){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("'<tr  >'+");
		  out.println("'<td width=\"12%\" align=left ><b>Cheque No *</td> '+");
		  out.println("'<td width=\"12%\" align=left ><b>Cheque Date</td> '+");
		  out.println("'<td width=\"5%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"14%\" align=right><b>Cheque Amount</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Account No</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"16%\" align=left ><b>Branch</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Currency</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'</tr></table>';");
			out.println("}");		
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr bgcolor=#C8C8C8 style=height=30>'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+lineno+' maxlength=\"10\" size=\"10\"  style={width=100px;} onblur=\"validate_cheque_no('+lineno+')\" ></td>'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+lineno+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+lineno+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+lineno+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+lineno+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+lineno+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+lineno+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+lineno+'></td>'+");
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+lineno+')\">Calender</td>'+"); 
			out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+lineno+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+lineno+')\"></td>'+"); 
			out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" id=TXT_ACC_NO_'+lineno+' name=TXT_ACC_NO_'+lineno+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+lineno+'); validate_acc_no('+lineno+')\" style={width=90px;} ></td>'+"); // modified by udara on 18-02-2012
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+lineno+' value=\"...\" onClick=\"help_button_9('+lineno+')\"  ></td>'+"); 
			out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+lineno+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+lineno+')\" ><input type=\"hidden\" name=hid_br_code_'+lineno+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+lineno+' value=\"...\" onClick=\"help_button_branch('+lineno+')\"  ></td>'+"); 
			out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+lineno+' maxlength=1 size=1 style={width=90px;} >'+");  
			
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
			"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
			"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
			"ORDER  BY DEFAULT_VALUE DESC ");
			boolean more = rs.next();		
			while(more){
			out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
			more=rs.next();
			}
			out.println("'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+lineno+' value=\"Allocate\" onClick=\"allocate_value('+lineno+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+','+lineno+')\"  ></td>'+"); 
			out.println("'</tr></table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("document.Form1.MORE_BUT_CON.disabled=true;");///
			out.println("}");
			out.println("}");
			out.println("}");			
			
			
			
			
			
			out.println("function del_row1(rowNo){");
			//--------------------
			//out.println("alert(rowNo+'--'+store_all.size);");
			//out.println("cheque_no    = document.Form1.elements[\"TXT_CHEQUE_NO_\"+rowNo].value;");
			//out.println("alert(store_all.length)");
			//out.println("store_all[rowNo+1]=\"\";");
			//out.println("alert(store_all.length)");
			//out.println("document.write(store_all[rowNo])");
			//out.println("for(var i=0;i<store_all.length;i++){");
			//out.println("alert(i+'-'+rowNo);");
			//out.println("if(i==rowNo){");
			//out.println("}else{");
			//out.println("change1.innerHTML = store_all[i];");
			//out.println("}");
			//out.println("}");
			//-------------------
		
			/*
			out.println("if(arr_size!=1){");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_chq='TXT_CHEQUE_NO_'+i;");
			out.println("m_chq_date='TXT_CHEQUE_DATE_'+i;");
			out.println("m_chq_amt='TXT_CHEQUE_AMOUNT_'+i;");
			out.println("m_branch='TXT_BRANCH_'+i;");
			out.println("m_acc='TXT_ACC_NO_'+i;");
			out.println("m_cur='TXT_CURR_CODE_'+i;");
			out.println("m_branch_code='hid_br_code_'+i;");
			
			
			out.println("if(i==rowNo)");
			out.println("continue;");
						
			out.println("cheque_arry[j]=document.Form1.elements[m_chq].value;");
			
			out.println("val=document.Form1.elements[m_chq_date].value;");
			
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("date=v_date+'-'+v_month+'-'+val;");
			
			out.println("cheque_date_arry[j]=date;");
			
			out.println("cheque_amt_arry[j]=document.Form1.elements[m_chq_amt].value");	
			out.println("branch_arry[j]=document.Form1.elements[m_branch].value;");
			out.println("acc_arry[j]=document.Form1.elements[m_acc].value;");
			out.println("cur_arry[j]=document.Form1.elements[m_cur].value;");
			out.println("array_branch_code[j]=document.Form1.elements[m_branch_code].value;");
			out.println("j=j+1;");
			out.println("}");
		 
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}");
			out.println("flag=0;");
			*/
			out.println("}");
			
			
				out.println("function write_data(size){");
				out.println("header();");
        out.println(" for(var j=0;j<size;j++){");
				
			  out.println("if(cheque_arry[j]==\"\" && cheque_date_arry[j]==\"\" && cheque_amt_arry[j]==\"\" && branch_arry[j]==\"\" && acc_arry[j]==\"\" ){");
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\"></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;} ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  
				
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
        out.println("continue;");
				out.println("}");
				
				out.println("else if(cheque_arry[j]!=\"\" && cheque_date_arry[j]==\"\" && cheque_amt_arry[j]==\"\" && branch_arry[j]==\"\" && acc_arry[j]==\"\" ){");
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} value=\"'+cheque_arry[j]+'\" onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\"></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;} ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  
				
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
				
        out.println("continue;");
				out.println("}");
				
				out.println("else if(cheque_arry[j]!=\"\" && cheque_date_arry[j]!=\"\" && cheque_amt_arry[j]==\"\" && branch_arry[j]==\"\" && acc_arry[j]==\"\" ){");
				
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} value=\"'+cheque_arry[j]+'\" onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" value='+cheque_date_arry[j].substring(0,2)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" value='+cheque_date_arry[j].substring(3,5)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" value='+cheque_date_arry[j].substring(6,10)+' ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+' value='+cheque_date_arry[j]+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\"></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;} ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  
				
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
				
        out.println("continue;");
				out.println("}");
				
				
				out.println("else if(cheque_arry[j]!=\"\" && cheque_date_arry[j]!=\"\" && cheque_amt_arry[j]!=\"\" && branch_arry[j]==\"\" && acc_arry[j]==\"\" ){");
				
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} value=\"'+cheque_arry[j]+'\" onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" value='+cheque_date_arry[j].substring(0,2)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" value='+cheque_date_arry[j].substring(3,5)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" value='+cheque_date_arry[j].substring(6,10)+' ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+' value='+cheque_date_arry[j]+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\" value='+cheque_amt_arry[j]+' ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;} ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  
				
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
				
        out.println("continue;");
				out.println("}");
				
				out.println("else if(cheque_arry[j]!=\"\" && cheque_date_arry[j]==\"\" && cheque_amt_arry[j]!=\"\" && branch_arry[j]==\"\" && acc_arry[j]!=\"\" ){");
				
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} value=\"'+cheque_arry[j]+'\" onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" value='+cheque_date_arry[j].substring(0,2)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" value='+cheque_date_arry[j].substring(3,5)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" value='+cheque_date_arry[j].substring(6,10)+' ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+' value='+cheque_date_arry[j]+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\" value='+cheque_amt_arry[j]+' ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;}  value='+acc_arry[j]+' ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  				
				
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
				
        out.println("continue;");
				out.println("}");
				
				
				out.println("else if(cheque_arry[j]!=\"\" && cheque_date_arry[j]!=\"\" && cheque_amt_arry[j]!=\"\" && branch_arry[j]!=\"\" && acc_arry[j]!=\"\" ){");
				
				out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
				out.println("'<tr>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+j+' maxlength=\"10\" size=\"10\"  style={width=100px;} value=\"'+cheque_arry[j]+'\" onblur=\"validate_cheque_no('+j+')\" ></td>'+");
				out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+j+')\" value='+cheque_date_arry[j].substring(0,2)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+j+' maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+j+')\" value='+cheque_date_arry[j].substring(3,5)+' >'+");
				out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+j+' maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+j+')\" value='+cheque_date_arry[j].substring(6,10)+' ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+j+' value='+cheque_date_arry[j]+'></td>'+");
				out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+j+')\">Calender</td>'+"); 
				out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+j+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+j+')\" value='+cheque_amt_arry[j]+' ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+j+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+j+')\" style={width=90px;}  value='+acc_arry[j]+' ></td>'+"); 
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+j+' value=\"...\" onClick=\"help_button_9('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+j+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+j+')\" value=\"'+branch_arry[j]+'\" ><input type=\"hidden\" name=hid_br_code_'+j+' value='+array_branch_code[j]+' ></td>'+"); //name=TXT_BRANCH_'+y+'    
				out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+j+' value=\"...\" onClick=\"help_button_branch('+j+')\"  ></td>'+"); 
				out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+j+' maxlength=1 size=1 style={width=90px;} >'+");  				
					
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
				"ORDER  BY DEFAULT_VALUE DESC ");
				more = rs.next();		
				while(more){
				out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
				more=rs.next();
				}
				out.println("'</select>'+");
				out.println("'</td>'+");
				out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+j+' value=\"Allocate\" onClick=\"allocate_value('+j+')\"  ></td>'+"); //Added By Sandun on 02-02-2009
  			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Delete\" onClick=\"del_row('+j+','+j+')\"  ></td>'+"); 
				out.println("'</tr></table>';");
        out.println("continue;");
				out.println("}");
										
			 	out.println("}");		
				out.println("fill_value();");			
				out.println("}");		
				
				
				out.println("function fill_value(){");
				out.println("for(i=0;i<arr_size;i++){");
			  out.println("m_status=\"TXT_CURR_CODE_\"+i");
			  out.println("document.Form1.elements[m_status].value=cur_arry[i]");
			  out.println("}");
				
				out.println("}");
				
				
					out.println("function validate_cheques(){  ");
					out.println("var count=0; ");
					out.println("var b_count=0; ");
					out.println("b_flag_val=0; ");
					out.println("arr_size_edit=0; ");
					
					
					out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
					out.println("if(lineno>=2){ ");
					out.println("count =lineno-1; ");
					out.println("for(var i=count;i>=0;i--){ ");
					out.println("m_tmp_cheq_no=\"TXT_CHEQUE_NO_\"+i; ");
					out.println("m_tmp_br_code=\"hid_br_code_\"+i; ");
					out.println("if(document.Form1.elements[m_tmp_cheq_no].value!=\"\" && document.Form1.elements[m_tmp_br_code].value!=\"\"){ ");
					out.println("tmp_cheque_no=document.Form1.elements[m_tmp_cheq_no].value; ");
					out.println("tmp_br_code=document.Form1.elements[m_tmp_br_code].value; ");
					out.println("break; ");
					out.println("}");
					out.println("}");
					out.println("}");
					out.println("for(var i=0;i<lineno;i++){ ");
					out.println("m_cheque_no='TXT_CHEQUE_NO_'+i;");
					out.println("m_chq_date='TXT_CHEQUE_DATE_'+i;");
					out.println("m_chq_amt='TXT_CHEQUE_AMOUNT_'+i;");
					out.println("m_branch='TXT_BRANCH_'+i;");
					out.println("m_acc='TXT_ACC_NO_'+i;");
					out.println("m_br_code='hid_br_code_'+i;");
					out.println("if(document.Form1.elements[m_cheque_no].value!=\"\" || document.Form1.elements[m_chq_date].value!=\"\" || document.Form1.elements[m_chq_amt].value!=\"\" || document.Form1.elements[m_branch].value!=\"\" || document.Form1.elements[m_acc].value!=\"\" ) { ");
					out.println("if(document.Form1.elements[m_cheque_no].value==\"\") {");
					out.println("alert('Cheque no can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_chq_date].value==\"\") {");
					out.println("alert('Cheque date can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_chq_amt].value==\"\") {");
					out.println("alert('Cheque amount can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_acc].value==\"\") {");
					out.println("alert('Account no can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
					out.println("alert('Branch can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("if(lineno>=2){ ");
					out.println("      if(document.Form1.elements[m_cheque_no].value.toUpperCase()==tmp_cheque_no.toUpperCase() && document.Form1.elements[m_br_code].value.toUpperCase()==tmp_br_code.toUpperCase() ){ ");
					out.println("			b_count=b_count+1; ");
					out.println("     } ");
					out.println("}	");
					out.println("} ");
					out.println("} ");
					out.println("if(b_count>=2){ ");
					out.println("alert('Cheque No can not be duplicated ! '); "); 
					out.println("b_flag_val=0; ");
					out.println(" } ");
					out.println(" } ");
					
					out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
					out.println("arr_size_edit=document.Form1.hid_no_val.value;");	
					//out.println(" alert('arr_size_edit'+arr_size_edit); ");
					out.println("if(arr_size_edit>=2){ ");
					out.println("count =arr_size_edit-1;");
					out.println("for(var i=count;i>=0;i--){ ");
					out.println("m_tmp_cheq_no='TXT_CH_NO_'+i; ");
					out.println("m_tmp_br_code='hid_br_code_'+i;");
					//out.println(" alert('values'+document.Form1.elements[m_tmp_cheq_no].value); ");
					out.println("if(document.Form1.elements[m_tmp_cheq_no].value!=\"\"){ ");
					out.println("tmp_cheque_no=document.Form1.elements[m_tmp_cheq_no].value; ");
					out.println("tmp_br_code=document.Form1.elements[m_tmp_br_code].value; ");
					out.println("break; ");
					out.println("}");
					out.println("}");
					out.println("}");
					out.println("for(var i=0;i<arr_size_edit;i++){ ");
					out.println("m_cheque_no='TXT_CH_NO_'+i;");
					out.println("m_chq_date ='TXT_CH_DATE_'+i;");
					out.println("m_chq_amt  ='TXT_CH_AMOUNT_'+i;");
					out.println("m_branch   ='TXT_BR_CODE_'+i;");
					out.println("m_acc      ='TXT_AC_NO_'+i;");
					out.println("m_br_code  ='hid_br_code_'+i;");
					//out.println("alert('values'+document.Form1.elements[m_cheque_no].value);");
					out.println("if(document.Form1.elements[m_cheque_no].value!=\"\" || document.Form1.elements[m_chq_date].value!=\"\" || document.Form1.elements[m_chq_amt].value!=\"\" || document.Form1.elements[m_branch].value!=\"\" || document.Form1.elements[m_acc].value!=\"\" ) { ");
					out.println("if(document.Form1.elements[m_cheque_no].value==\"\") {");
					out.println("alert('Cheque no can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_chq_date].value==\"\") {");
					out.println("alert('Cheque date can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_chq_amt].value==\"\") {");
					out.println("alert('Cheque amount can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_acc].value==\"\") {");
					out.println("alert('Account no can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
					out.println("alert('Branch can not be blank.');");
					out.println("b_flag_val=1; ");
					out.println("break; ");
					out.println("}");
					out.println("if(arr_size_edit>=2){ ");
					//out.println("alert('as'+arr_size_edit);");
					//out.println("alert('as'+document.Form1.elements[m_cheque_no].value);");
					out.println("      if(document.Form1.elements[m_cheque_no].value.toUpperCase()==tmp_cheque_no.toUpperCase() && document.Form1.elements[m_br_code].value.toUpperCase()==tmp_br_code.toUpperCase() ){ ");
					out.println("			b_count=b_count+1; ");
					out.println("     } ");
					out.println("}	");
					out.println("} ");
					out.println("} ");
					out.println("if(b_count>=2){ ");
					out.println("alert('Cheque No can not be duplicated ! '); ");
					out.println("b_flag_val=0; ");
					out.println(" } ");
					
					out.println(" } ");
					
					
					
					out.println("if(b_flag_val==0){ ");
					out.println("	return true; ");
					out.println("	} ");
					out.println("	else{ ");
					out.println("	return false; ");
					out.println("	} ");
					
					out.println("	}");
					
					
					
					
				
				
				
				
				
				
				
				
				
				///============added	 by nuwan de silva 26-07-07================================================================================//
		
						
			out.println("function butt()");
			out.println("{");
			out.println("document.Form1.hid_SYS_DATE_DD.value='"+m_date_dd+"'");
			out.println("document.Form1.hid_SYS_DATE_MM.value='"+m_date_mm+"'");
			out.println("document.Form1.hid_SYS_DATE_YY.value='"+m_date_yy+"'");

			out.println("change1.innerHTML+='<br><br><br><table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			
			out.println("'<tr >'+");
			out.println("'<td width=\"15%\" ><b>Cheque No*</td>'+");
			out.println("'<td width=\"12%\" ><b>Cheque Date </td>'+"); 
			out.println("'<td width=\"5%\" ><b>&nbsp;</td>'+"); 
			out.println("'<td width=\"14%\" align=right><b>Cheque Amount </td>'+"); 
			out.println("'<td width=\"14%\" ><b>Account No  </td>'+"); 
			out.println("'<td width=\"5%\" >&nbsp</td>'+");
			out.println("'<td width=\"10%\" ><b>Branch </td>'+"); 
			out.println("'<td width=\"5%\" >&nbsp</td>'+");
			out.println("'<td width=\"9%\" ><b>Currency </td>'+"); 
			out.println("'<td width=\"5%\" >&nbsp</td>'+");
			out.println("'<td width=\"5%\" >&nbsp</td>'+");
			out.println("'<td width=\"*%\" >&nbsp;</td>' +");
			out.println("'</tr >'+"); 
			
			//out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\"  name=TXT_CHEQUE_NO_'+y+' value=\"\" maxlength=\"8\" size=\"8\" ></td>'+"); 
			out.println("'<td width=\"12%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+y+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+y+')\" >'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+y+'  maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+y+')\" >'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+y+'  maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+y+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+y+'></td>'+");  //Calendar - Added by Chandana on 19/06/2007 for Ref No.294 //<a href style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+y+')\">Calendar</a>
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+y+')\">Calender</td>'+"); //added by nuwan de silva 24-07-07
			out.println("'<td width=\"14%\"  align=\"right\"><input style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+y+' maxlength=\"20\" size=\"10\" onblur=\"check_ntm('+y+')\"></td>'+"); 
		  out.println("'<td width=\"14%\" ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+y+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+y+')\"></td>'+"); 
		  out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+y+' value=\"...\" onClick=\"help_button_9('+y+')\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+y+' maxlength=\"20\" size=\"20\" onblur=\"check_branch('+y+')\" ><input type=\"hidden\" name=hid_br_code_'+y+' ></td>'+"); //name=TXT_BRANCH_'+y+'    
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+y+' value=\"...\" onClick=\"help_button_branch('+y+')\" ></td>'+"); 
			out.println("'<td width=\"9%\" ><select class=txt_input type=text name=TXT_CURR_CODE_'+y+' maxlength=1 size=1>'+");  

			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
																"ORDER  BY DEFAULT_VALUE DESC ");
			more = rs.next();		
			while(more){
			out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
			more=rs.next();
			}
			
			out.println("'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+y+' value=\"Add\" onClick=\"onclick_add('+y+')\"></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+y+' value=\"Delete\" onClick=\"onclick_del('+y+')\"></td>'+"); 
			out.println("'<td width=\"*%\" >&nbsp;</td>' +");
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("document.Form1.hid_x.value=1;");
			out.println("document.Form1.hid_xx.value=1;");
			out.println("m_curr=document.Form1.elements[\"TXT_CURR_CODE_\"+y].value");
			out.println("}");
		
		  //-----Added by Chandana on 19/06/2007--------
			
			out.println("function load_calendar(num,row) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println(" document.Form1.hid_count.value=row;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");		
		
		  
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_count.value");
						
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+m_row].value=v_yy;");
			out.println(" ind='TXT_CHEQUE_DATE_'+m_row;");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+m_row].value+'-'+document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+m_row].value+'-'+document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+m_row].value;");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+m_row],document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+m_row],document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+m_row]);");

	
			out.println("  }");	
			out.println(" else if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_CH_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_CH_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_CH_DATE_YY\"+m_row].value=v_yy;");
			out.println(" ind='TXT_CH_DATE_'+m_row;"); //added by nuwan de silva 26-07-07
			out.println("document.Form1.elements[ind].value=document.Form1.elements[\"TXT_CH_DATE_DD\"+m_row].value+'-'+document.Form1.elements[\"TXT_CH_DATE_MM\"+m_row].value+'-'+document.Form1.elements[\"TXT_CH_DATE_YY\"+m_row].value;"); //added by nuwan de silva 26-07-07
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[\"TXT_CH_DATE_DD\"+m_row],document.Form1.elements[\"TXT_CH_DATE_MM\"+m_row],document.Form1.elements[\"TXT_CH_DATE_YY\"+m_row]);");

			out.println("  }");			
			
			out.println(" else if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_CH_DATE_DD_BULK.value=v_dd;");
			out.println("     document.Form1.TXT_CH_DATE_MM_BULK.value=v_mm;");
			out.println("     document.Form1.TXT_CH_DATE_YY_BULK.value=v_yy;");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.TXT_CH_DATE_DD_BULK,document.Form1.TXT_CH_DATE_MM_BULK,document.Form1.TXT_CH_DATE_YY_BULK);");

			out.println("  }");			

			
			
	  	out.println("}");				
		 		
		 //------ End Chandana on 19/06/2007--------
		
			out.println("function get_data1(val_of)");
			out.println("{");
			out.println("m_add=\"1\"");
			
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			
			/*out.println("'<tr >'+");
			out.println("'<td width=\"15%\" ><b>Cheque No*</td>'+");
			out.println("'<td width=\"15%\" ><b>Cheque Date </td>'+"); 
			out.println("'<td width=\"14%\" align=right><b>Cheque Amount </td>'+");
			out.println("'<td width=\"14%\" ><b>Account No  </td>'+"); 
			out.println("'<td width=\"6%\" >&nbsp</td>'+");
			out.println("'<td width=\"10%\" ><b>Branch </td>'+"); 
			out.println("'<td width=\"6%\" >&nbsp</td>'+");
			out.println("'<td width=\"9%\" ><b>Currency </td>'+"); 
			out.println("'<td width=\"6%\" >&nbsp</td>'+");
			out.println("'<td width=\"6%\" >&nbsp</td>'+");
			out.println("'</tr >'+");
			*/
			//out.println("header='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"12%\" align=left  ><b>Cheque No*    </td>'+");
			out.println("'<td width=\"12%\" align=left  ><b>Cheque Date   </td>'+"); 
			out.println("'<td width=\"5%\"  align=left  ><b>&nbsp;        </td>'+"); 
			out.println("'<td width=\"12%\" align=right ><b>Cheque Amount </td>'+"); 
			out.println("'<td width=\"12%\" align=left  ><b>Account No    </td>'+"); 
			out.println("'<td width=\"5%\"  align=left  >&nbsp;           </td>'+");
			out.println("'<td width=\"12%\" align=left  ><b>Branch        </td>'+"); 
			out.println("'<td width=\"5%\"  align=left  >&nbsp;           </td>'+");
			out.println("'<td width=\"12%\"  align=left  ><b>Currency      </td>'+"); 
			out.println("'<td width=\"5%\"  align=left  >&nbsp;           </td>'+");
			out.println("'<td width=\"5%\"  align=left  >&nbsp;           </td>'+");
			out.println("'<td width=\"*%\"  align=left  >&nbsp;           </td>'+");
			//out.println("'</tr >';");
			out.println("'</tr>'+"); 
		  out.println("'</table>';");
						
			out.println("for (var i=0;i<=val_of;i++)");
			out.println("{");
			out.println("if(i==val_of){");
			
			out.println("m_row_1='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\"  name=TXT_CHEQUE_NO_'+i+'  maxlength=\"8\" size=\"8\" value=\"'+cheque_arry[i]+'\" ></td>'+"); 
			out.println("'<td width=\"12%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+i+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+i+')\" value='+cheque_date_arry[i].substring(0,2)+' >'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+i+'  maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+i+')\" value='+cheque_date_arry[i].substring(3,5)+'>'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+i+'  maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+i+')\" value=\"'+cheque_date_arry[i].substring(6,10)+'\"><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+i+'  value='+cheque_date_arry[i]+'></td>'+"); 
			
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+i+')\">Calender</td>'+"); //added by nuwan de silva 24-07-07
			
			out.println("'<td width=\"14%\" align=\"right\" ><input class=\"txt_input type=\"text\" style=\"{text-align:right}\" name=TXT_CHEQUE_AMOUNT_'+i+' maxlength=\"10\" size=\"10\" onblur=\"check_ntm('+i+')\" value='+cheque_amt_arry[i]+'></td>'+"); 
			
		  out.println("'<td width=\"14%\" ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+i+' maxlength=\"100\" size=\"20\"  onblur=\"check_account('+i+')\" value='+acc_arry[i]+'></td>'+"); 
			
		  out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+i+' value=\"...\" onClick=\"help_button_9('+i+')\" ></td>'+"); 
			
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+i+' maxlength=\"100\" size=\"20\" onblur=\"check_branch('+i+')\" value='+branch_arry[i]+'></td>'+"); 
			
			//out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+i+' value=\"Help\" onClick=\"help_button_branch('+i+')\" ></td>';"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+i+' value=\"...\" onClick=\"help_button_branch('+i+')\"></td>';"); 
			
			
			out.println("m_row_1=m_row_1+'<td width=\"9%\" ><select class=txt_input type=text name=TXT_CURR_CODE_'+i+' maxlength=1 size=1>';");  
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+ //added by nuwan de silva 24-07-07
																"ORDER  BY DEFAULT_VALUE DESC ");
			boolean more2 = rs.next();		
			while(more2){
			out.println("if(cur_arry[i]=='"+rs.getString(1)+"'){");
			out.println("m_row_1=m_row_1+'<option value="+rs.getString(1)+" selected>"+rs.getString(2)+"</option>';");
			out.println("}");
			out.println("else if(cur_arry[i]!='"+rs.getString(1)+"'){");
			out.println("m_row_1=m_row_1+'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>';");
			out.println("}");

			more2=rs.next();
			}
			
			out.println("m_row_1=m_row_1+'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+i+' value=\"Add\" onClick=\"onclick_add('+i+')\" ></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+i+' value=\"Delete\" onClick=\"onclick_del('+i+')\"></td>'+"); 
			out.println("'<td width=\"*%\" >&nbsp;</td>'+");
			//out.println("'</tr >';");
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("}");
			
			out.println("if(i!=val_of){");
			out.println("m_row_1='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\"  name=TXT_CHEQUE_NO_'+i+'  maxlength=\"8\" size=\"8\" value=\"'+cheque_arry[i]+'\" disabled></td>'+"); 
			out.println("'<td width=\"12%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+i+' maxlength=\"2\" size=\"2\" onblur=\"check_dd_1('+i+')\" disabled value='+cheque_date_arry[i].substring(0,2)+' >'+"); //nnnnnnnnnnnnnnnnn
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+i+'  maxlength=\"2\" size=\"2\" onblur=\"check_mm_1('+i+')\" disabled  value='+cheque_date_arry[i].substring(3,5)+'>'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+i+'  maxlength=\"4\" size=\"4\" onblur=\"check_date_1('+i+')\" disabled value=\"'+cheque_date_arry[i].substring(6,10)+'\"><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+i+'  value='+cheque_date_arry[i]+'></td>'+"); 
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+i+')\">Calender</td>'+"); //added by nuwan de silva 24-07-07
			out.println("'<td width=\"14%\" align=\"right\" ><input class=\"txt_input type=\"text\" style=\"{text-align:right}\" name=TXT_CHEQUE_AMOUNT_'+i+' maxlength=\"10\" size=\"10\" disabled onblur=\"check_ntm('+i+')\" value='+cheque_amt_arry[i]+'></td>'+"); 
		  out.println("'<td width=\"14%\" ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+i+' maxlength=\"100\" size=\"20\"  disabled onblur=\"check_account('+i+')\" value='+acc_arry[i]+'></td>'+"); 
		  out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+i+' value=\"...\"disabled  onClick=\"help_button_9('+i+')\" ></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+i+' maxlength=\"100\"  size=\"20\" disabled onblur=\"check_branch('+i+')\" value='+branch_arry[i]+'></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+i+' value=\"...\"disabled  disabled onClick=\"help_button_branch('+i+')\" ></td>';"); 
			
			out.println("m_row_1=m_row_1+'<td width=\"9%\" ><select class=txt_input type=text name=TXT_CURR_CODE_'+i+' maxlength=1 size=1 disabled>';");  
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+
																"ORDER  BY DEFAULT_VALUE DESC ");
			 more2 = rs.next();		
			while(more2){
			out.println("if(cur_arry[i]=='"+rs.getString(1)+"'){");
			out.println("m_row_1=m_row_1+'<option value="+rs.getString(1)+" selected>"+rs.getString(2)+"</option>';");
			out.println("}");
			out.println("else if(cur_arry[i]!='"+rs.getString(1)+"'){");
			out.println("m_row_1=m_row_1+'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>';");
			out.println("}");

			more2=rs.next();
			}
			
			out.println("m_row_1=m_row_1+'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+i+' value=\"Add\" onClick=\"onclick_add('+i+')\" disabled></td>'+"); 
			out.println("'<td width=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+i+' value=\"Delete\" onClick=\"onclick_del('+i+')\"></td>'+"); 
			out.println("'<td width=\"*%\" >&nbsp;</td>'+");
			out.println("'</tr >'+");
			//out.println("'</tr >';");
			out.println("'</table >';");
			out.println("}");
			
			out.println("change1.innerHTML+=m_row_1");
			out.println("}");
			out.println("document.Form1.hid_x.value=val_of;");
			out.println("document.Form1.hid_xx.value=val_of+1;");

			out.println("}");
			
			out.println("function check_withdraw(obj,edit_check){");			
			out.println("if(obj.checked==true){");
			out.println("obj.value='on';");
			out.println("document.Form1.hid_chq.value=edit_check;");
			//out.println("alert(m);");
			out.println("for(n=0;n<parseInt(document.Form1.hid_no_val.value);n++){");
			out.println("m_chk_edit=\"CHK_EDIT_\"+n;");
			out.println("if(n!=edit_check){");
			out.println("document.Form1.elements[m_chk_edit].checked=false");
			out.println("}");
			out.println("}");
		 	out.println("load_edit_contracts(edit_check);");//Added By Sandun on 22-07-2009
			out.println("}");
			out.println("else {");
			out.println("obj.value='off';");
			out.println("obj.checked=false;");
			out.println("document.Form1.hid_chq.value=0;");
			out.println("edit_allocation.innerHTML=''; ");
			out.println("}");
		//	out.println("alert('val'+obj.value);");
			
			out.println("}");

			out.println("function onclick_del_1(y)");
			out.println("{");
			out.println("if(y==\"0\" && document.Form1.hid_count.value==\"1\"){	");
			out.println("new_window()	");
			out.println("}	");
			out.println("else{	");			
		  out.println("var e=0;");
			out.println("val_of=document.Form1.hid_count.value;");
			out.println("for (var i=0; i<val_of;i++)");
			out.println("{	");
			out.println("m_pd='TXT_PD_NO_'+i;");
			out.println("m_chq='TXT_CH_NO_'+i;");
			out.println("m_chq_date='TXT_CH_DATE_'+i;");
			out.println("m_chq_amt='TXT_CH_AMOUNT_'+i;");
			out.println("m_branch='TXT_BR_CODE_'+i;");
			out.println("m_acc='TXT_AC_NO_'+i;");
			out.println("m_cur='TXT_CUR_'+i;");
			out.println("if(y==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
  		out.println("if(document.Form1.elements[m_chq].value=='')");
			out.println("{");
			out.println("pd_arry[e]=0");
			out.println("cheque_arry[e]=0");
			out.println("cheque_date_arry[e]=0;");
			out.println("cheque_amt_arry[e]=0;");
			out.println("branch_arry[e]=0;");
			out.println("acc_arry[e]=0;");
			out.println("cur_arry[e]=0;");
			out.println("}");
			out.println("else{");
			out.println("pd_arry[e]=document.Form1.elements[m_pd].value;");
			out.println("cheque_arry[e]=document.Form1.elements[m_chq].value;");
			out.println("cheque_amt_arry[e]=document.Form1.elements[m_chq_amt].value");	
			out.println("cheque_date_arry[e]=document.Form1.elements[m_chq_date].value;");
			out.println("branch_arry[e]=document.Form1.elements[m_branch].value;");
			out.println("acc_arry[e]=document.Form1.elements[m_acc].value;");
			out.println("cur_arry[e]=document.Form1.elements[m_cur].value;");
			out.println("e=e+1;");
			out.println("	}");
			out.println("}");
			out.println("val_of=val_of-1;");
			out.println("m_row=\"\"");
			out.println("get_data1_1(val_of);");
			out.println("document.Form1.hid_count.value=val_of;");
			out.println("no=val_of");
			out.println("}");
			out.println("}");
	
	
			out.println("function get_data1_1(val_of)");
			out.println("{");
			out.println("cheque_details.innerHTML=\"\"");
			out.println("m_row=\"\";");
			out.println("cheque_details.innerHTML='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr class=pdn_txtpos2>'+");
			out.println("'<td width=\"15%\" ><b>POD No </td>'+"); 
			out.println("'<td width=\"13%\" ><b>Cheque No</td>'+");
			out.println("'<td width=\"15%\" ><b>Cheque Date </td>'+"); 
			out.println("'<td width=\"10%\" align=right><b>Cheque Amount </td>'+"); 
			out.println("'<td width=\"14.8%\" ><b>Account No </td>'+"); 
			out.println("'<td width=\"6%\" >&nbsp</td>'+"); 
			out.println("'<td width=\"10%\" ><b>Branch  </td>'+"); 
			out.println("'<td width=\"10%\" ><b>Currency </td>'+"); 
			out.println("'<td width=\"6%\" >&nbsp;</td>'+"); 
			out.println("'</tr>'+"); 	
			out.println("'</table>';"); 
			out.println("for (var i=0;i<val_of;i++)");
			out.println("{");
			out.println("m_row='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"15%\" >'+pd_arry[i]+'<input class=\"txt_input2\" type=\"hidden\"  name=TXT_PD_NO_'+i+'  maxlength=\"20\" size=\"20\" value='+pd_arry[i]+'></td>'+"); 
			out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\"  name=TXT_CH_NO_'+i+'  maxlength=\"6\" size=\"6\" value='+cheque_arry[i]+'></td>'+"); 
			out.println("'<td width=\"15%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_CH_DATE_DD'+i+' maxlength=\"2\" size=\"2\" onblur=\"check_dd('+i+')\" value='+cheque_date_arry[i].substring(0,2)+'>'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CH_DATE_MM'+i+'  maxlength=\"2\" size=\"2\" onblur=\"check_mm('+i+')\" value='+cheque_date_arry[i].substring(3,5)+'>'+"); 
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_CH_DATE_YY'+i+'  maxlength=\"4\" size=\"4\" onblur=\"check_date('+i+')\" value='+cheque_date_arry[i].substring(6,10)+'><input  type=\"hidden\" name=TXT_CH_DATE_'+i+'  value='+cheque_date_arry[i]+'><a href style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+i+')\">Calendar</a></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input2\" type=\"text\" style=\"{text-align:right}\" name=TXT_CH_AMOUNT_'+i+' maxlength=\"20\" size=\"10\" value=\"'+cheque_amt_arry[i]+'\" onblur=\"check_amt('+i+')\"></td>'+"); 
		  out.println("'<td width=\"15%\" ><input class=\"txt_input\" type=\"text\" name=TXT_AC_NO_'+i+' maxlength=\"20\" size=\"20\" value=\"'+acc_arry[i]+'\" onblur=\"check_account('+i+')\"></td>'+"); 
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_AC_NO_'+i+' value=\"...\" onClick=\"help_button_10('+i+')\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input2\" type=\"text\" name=TXT_BR_CODE_'+i+' maxlength=\"100\" size=\"20\" value=\"'+branch_arry[i]+'\" disabled></td>';"); 
			
		
			rs = stmt.executeQuery ("SELECT TRIM(CURR_CODE),TRIM(CURR_SYMBOL), REP_CURR "+
					                    "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
															"WHERE ACTIVE_STATUS='Y' "+
															"ORDER  BY DEFAULT_VALUE DESC ");
			boolean more4 = rs.next();	
			out.println("m_row=m_row+'<td width=\"9%\" ><select class=\"txt_input2\" type=text name=TXT_CUR_'+i+' maxlength=3 size=1>';");  

			while(more4){
			out.println("if(cur_arry[i]=='"+rs.getString(1)+"'){");
			out.println("m_row=m_row+'<option value=\""+rs.getString(1)+"\" selected>"+rs.getString(2)+"</option>';");	
			out.println("}");
			
			out.println("else if(cur_arry[i]!='"+rs.getString(1)+"'){");
			out.println("m_row=m_row+'<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>';");	
			out.println("}");
			more4=rs.next();
			
			if (!more4)
				{
				break;
				}
			}
			
			out.println("m_row=m_row+'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+i+' value=\"Delete\" onClick=\"onclick_del_1('+i+')\"><input type=hidden name=hid_count value=\"0\" ></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("cheque_details.innerHTML+=m_row");
			out.println("array_docu[i]=m_row;");
			out.println("}");
			out.println("document.Form1.hid_count.value=val_of;");
			out.println("}");
			
			
			out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
			
			out.println("if(!chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY)){");
	  
		  out.println("TO_DD.value=\"\";");
			out.println("TO_MM.value=\"\";");
			out.println("TO_YY.value=\"\";");
			
			out.println("}");
			
			
			out.println("}");
			
			//added by nuwan de silva on 24-10-07------------------------
			out.println("function validate_date_bulk(objDD,objMM,objYY){ ");
			out.println(" if((objDD.value !=\"\")&&(objMM.value !=\"\")&&(objYY.value !=\"\")){");
			out.println("  checkMonthLength(objDD,objMM,objYY);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,objDD,objMM,objYY);");
		
			out.println("}");
			out.println("}");
			

			out.println("function check_dd_1(ln){ ");
			out.println("ind='TXT_CHEQUE_DATE_'+ln;");
			out.println("ind_dd='TXT_CHEQUE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CHEQUE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CHEQUE_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			
			out.println(" }");
			out.println("}");
			
			out.println("function check_mm_1(ln){ ");
			out.println("ind='TXT_CHEQUE_DATE_'+ln;");
			out.println("ind_dd='TXT_CHEQUE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CHEQUE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CHEQUE_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("}");
			
			out.println("function check_date_1(ln){ ");
			out.println("ind='TXT_CHEQUE_DATE_'+ln;");
			out.println("ind_dd='TXT_CHEQUE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CHEQUE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CHEQUE_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			

			out.println("function check_dd(ln){ ");
			out.println("ind='TXT_CH_DATE_'+ln;");
			out.println("ind_dd='TXT_CH_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CH_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CH_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("}");
			
			out.println("function check_mm(ln){ ");
			out.println("ind='TXT_CH_DATE_'+ln;");
			out.println("ind_dd='TXT_CH_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CH_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CH_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("}");
			
			out.println("function check_date(ln){ ");
			out.println("ind='TXT_CH_DATE_'+ln;");
			out.println("ind_dd='TXT_CH_DATE_DD'+ln;");
			out.println("ind_mm='TXT_CH_DATE_MM'+ln;");
			out.println("ind_yy='TXT_CH_DATE_YY'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")&&(document.Form1.elements[ind_mm].value !=\"\")&&(document.Form1.elements[ind_yy].value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("validate_date(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("document.Form1.elements[ind].value=document.Form1.elements[ind_dd].value+'-'+document.Form1.elements[ind_mm].value+'-'+document.Form1.elements[ind_yy].value;");
			out.println("}");
			
			
			out.println("function check_ntm(row){");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");			
			out.println("    nt=\"TXT_CHEQUE_AMOUNT_\"+row;");					
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			//out.println("format_number(document.Form1.elements[nt],22)");
			out.println("check_number(document.Form1.elements[nt],22)");
			out.println("}");
			
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value=format_noobject(0.00);"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_number_no_decimal(obj){");
			out.println("if(obj.value!='' &&  obj.value!='-'  && obj.value!='null') ");
			out.println("if(isPosInteger(obj.value)){ ");
			out.println("format_noobject_nodecimal1(obj) ;");
			out.println("} ");
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("} ");
			out.println("} ");
			
			
			
			out.println("function check_amt(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_CH_AMOUNT_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("format_number(document.Form1.elements[nt],22)");
			out.println("}");
			
			
			
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
		  out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
      out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
      out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
      out.println(" if(((parseInt(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
      out.println("}");
      out.println("else if(((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value)))&&");
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
      out.println("      alert('Cheque Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println("      alert('Cheque Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
      out.println("      alert('Cheque Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("      alert('Cheque Date should be greater than System Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("      alert('Cheque Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
		//	out.println("TO_DD.focus();");
			out.println("return true;");
      out.println("}");
		  out.println("}");
			
			
			out.println("function add_row_bulk(){"); 
			
			out.println(" var b_flag=0;");
			out.println(" var m_cheq_no=0;");
			out.println(" var m_cheq_amount=0;");
			
			out.println("lineno=0;");
			out.println("arr_size=0;");
			//out.println("alert('sdd'+document.Form1.TXT_ORDER_TYPE.value);");
			out.println("m_cheq_no=parseFloat(document.Form1.TXT_CHEQUE_NO_BULK.value);");		
			out.println("m_cheq_amount=document.Form1.TXT_AMOUNT_BULK.value;");		
			
		  out.println("header();");		
			out.println("for(i=0;i<parseInt(document.Form1.TXT_NO_OF_CHEQUE_BULK.value);i++){");
		  	
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");									
			out.println("'<tr bgcolor=#C8C8C8 >'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+lineno+' maxlength=\"10\" size=\"10\"  value=\"'+m_cheq_no+'\" style={width=100px;} onblur=\"validate_cheque_no('+lineno+')\" ></td>'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+lineno+' maxlength=\"2\" size=\"2\"  onblur=\"check_dd_1('+lineno+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+lineno+' maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_mm_1('+lineno+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+lineno+' maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_1('+lineno+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+lineno+'></td>'+");
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+lineno+')\">Calender</td>'+"); 
			out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+lineno+' maxlength=\"20\" value=\"'+m_cheq_amount+'\" size=\"10\" onblur=\"check_ntm('+lineno+')\"></td>'+"); 
			out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+lineno+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+lineno+')\" value=\"'+document.Form1.TXT_AC_NO_BULK.value+'\" style={width=90px;} ></td>'+"); 
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+lineno+' value=\"...\" onClick=\"help_button_9('+lineno+')\"  ></td>'+"); 
			out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+lineno+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+lineno+')\" value=\"'+document.Form1.TXT_BRANCH_BULK.value+'\"  ><input type=\"hidden\" name=hid_br_code_'+lineno+'  value=\"'+document.Form1.hid_br_code_BULK.value+'\" ></td>'+"); //name=TXT_BRANCH_'+y+'    
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+lineno+' value=\"...\" onClick=\"help_button_branch('+lineno+')\"  ></td>'+"); 
			out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+lineno+' maxlength=1 size=1 style={width=90px;} >'+");  
			
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
			"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
			"WHERE ACTIVE_STATUS='Y' "+ //modified by nuwan de silva 23-07-07
			"ORDER  BY DEFAULT_VALUE DESC ");
			more = rs.next();		
			while(more){
			out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
			more=rs.next();
			}
			out.println("'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+','+lineno+')\"  ></td>'+"); 
			out.println("'</tr></table>';");
			out.println("allocate_value(lineno);");//Added By Sandun on 06-02-2009
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			//added by nuwan de silva on 31-12-07
			out.println("if(document.Form1.TXT_ORDER_TYPE.value==\"ASC\") {");
			out.println("m_cheq_no=parseFloat(m_cheq_no)+parseFloat(1);");
			out.println("}");
			out.println("else {");
			out.println("m_cheq_no=parseFloat(m_cheq_no)-parseFloat(1);");
			out.println("}");			
			out.println("}");						
			out.println("get_post_dated_dates();");		
			out.println("}");
			
			//added by nuwan de silva on 26-10-07---------------------------------------------
			out.println("function get_post_dated_dates() { ");
			out.println("document.Form1.hid_text.value='GET_DATE'");
			out.println("m_date=document.Form1.TXT_CH_DATE_DD_BULK.value+'-'+document.Form1.TXT_CH_DATE_MM_BULK.value+'-'+document.Form1.TXT_CH_DATE_YY_BULK.value;"); //added by nuwan de silva 26-07-07
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_pod_cheques_dates&m_date=\"+m_date+\"&count=\"+document.Form1.TXT_NO_OF_CHEQUE_BULK.value+\"\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			
			//added by nuwan de silva on 26-10-07---------------------------------------------
			out.println("function assign_post_dated_dates(data_vec) { ");
			out.println("var j=0;");
			//out.println("alert(data_vec[j]+'-'+data_vec[j+1]+'-'+data_vec[j+2]);");
			out.println("for(i=0;i<parseInt(document.Form1.TXT_NO_OF_CHEQUE_BULK.value);i++){");
			//out.println("alert(i);");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+i].value=data_vec[j];");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+i].value=data_vec[j+1];");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+i].value=data_vec[j+2];");
			out.println("document.Form1.elements[\"TXT_CHEQUE_DATE_\"+i].value=data_vec[j]+'-'+data_vec[j+1]+'-'+data_vec[j+2];");
			out.println("j=j+3");			
			out.println("}");			
			out.println("}");
			
			
			
			out.println("function add_data() { ");
			out.println("b_flag=0;");
			out.println("var m_date='';");						
			out.println("if(document.Form1.TXT_CHEQUE_NO_BULK.value==\"\") {");
			out.println("alert('Cheque no can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.TXT_CH_DATE_DD_BULK.value==\"\" && document.Form1.TXT_CH_DATE_MM_BULK.value==\"\" && document.Form1.TXT_CH_DATE_YY_BULK.value==\"\") {");
			out.println("alert('Cheque date can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.TXT_AMOUNT_BULK.value==\"\") {");
			out.println("alert('Cheque amount can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.TXT_NO_OF_CHEQUE_BULK.value==\"\") {");
			out.println("alert('No of Cheques can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.TXT_AC_NO_BULK.value==\"\") {");
			out.println("alert('Account can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(document.Form1.TXT_BRANCH_BULK.value==\"\") {");
			out.println("alert('Branch can not be blank.');");
			out.println("b_flag=1;");
			out.println("}");			
			out.println("if(b_flag==0){");
			out.println("add_row_bulk();");
			out.println("}");
			
			out.println("}");
			
			
			
			
			
			
			//added by nuwan de silva on 23-10-07--------------
			out.println("function change_type(obj) { ");
			out.println("if(obj.value=='N'){");
			out.println("cheque_details_main.innerHTML=\"\";");
			out.println("lineno=0;");
			out.println("arr_size=0;");
			out.println("header();"); //added	 by nuwan de silva 26-07-07
			out.println("add_row()"); //added	 by nuwan de silva 26-07-07

			out.println("}");
			out.println("else if(obj.value=='B'){");
			out.println("change1.innerHTML=\"\";");
			out.println("add_bulk_data();");
			out.println("}");
			out.println("}");
			
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
						
			out.println("function add_bulk_data(obj) { ");
			out.println("change1.innerHTML=\"\";");
			out.println("cheque_details_main.innerHTML=\"\";");
			out.println("cheque_details_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");									
		  out.println("'<tr>'+");
			out.println("'<td width=\"30%\" ><DIV id=DIV_TXT_CHEQUE_NO_BULK  class=div_input>Cheque No *</DIV></td>'+"); //chanhe td width= 29 in to 30 by Prabash on 14-02-2012
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_CHEQUE_NO_BULK maxlength=10 size=10 ></td>'+"); 
			//out.println("'<input class=but_input type=button name=BUT_TXT_FINANCE_NO value=\"Add\" onClick=\"add_data()\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			//added by nuwan de silva on 31-12-07 ----------------------------------------
			out.println("'<tr>'+"); 
			out.println("'<td width=\"29%\" ><DIV  class=div_input>Order Type</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><select class=txt_input type=text name=TXT_ORDER_TYPE maxlength=1 size=1 onChange=\"\" >'+");  //style=\"{width:150px}\"
			out.println("'<option value=\"ASC\" selected>Ascending</option>'+");
			out.println("'<option value=\"DESC\" >Descending</option>'+");
			out.println("'</select>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+");
			out.println("'<td width=\"29%\" ><DIV id=DIV_TXT_CH_DATE_BULK  class=div_input>Start Date *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" style={text-align:left;}>'+");
			out.println("'<input class=txt_input5 type=text name=TXT_CH_DATE_DD_BULK  value=\"\" maxlength=\"2\" size=\"2\" onblur=\"validate_date_bulk(document.Form1.TXT_CH_DATE_DD_BULK,document.Form1.TXT_CH_DATE_MM_BULK,document.Form1.TXT_CH_DATE_YY_BULK)\" >'+");
			out.println("'<input class=txt_input5 type=text name=TXT_CH_DATE_MM_BULK  value=\"\" maxlength=\"2\" size=\"2\" onblur=\"validate_date_bulk(document.Form1.TXT_CH_DATE_DD_BULK,document.Form1.TXT_CH_DATE_MM_BULK,document.Form1.TXT_CH_DATE_YY_BULK)\" >'+");
			out.println("'<input class=txt_input5 type=text name=TXT_CH_DATE_YY_BULK  value=\"\" maxlength=\"4\" size=\"4\" onblur=\"validate_date_bulk(document.Form1.TXT_CH_DATE_DD_BULK,document.Form1.TXT_CH_DATE_MM_BULK,document.Form1.TXT_CH_DATE_YY_BULK)\" >'+");
			out.println("'<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"4\",\"4\")>Calendar</a></td>'+");
			//out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_CHEQUE_NO_BULK maxlength=10 size=10 ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr>'+");
			out.println("'<td width=\"29%\" ><DIV id=DIV_TXT_AMOUNT_BULK  class=div_input>Amount *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input style={text-align:right;} type=text name=TXT_AMOUNT_BULK maxlength=10 size=10 onBlur=\"check_number(this,10)\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr>'+");
			out.println("'<td width=\"29%\" ><DIV id=DIV_TXT_NO_OF_CHEQUE_BULK  class=div_input>No of Cheque *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text style={text-align:right;} name=TXT_NO_OF_CHEQUE_BULK maxlength=3 size=10  onBlur=\"check_number_no_decimal(this)\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr>'+");
			out.println("'<td width=\"29%\" ><DIV id=DIV_TXT_AC_NO_BULK  class=div_input>Account Number *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_AC_NO_BULK maxlength=10 size=10 onblur=\"check_account_bulk(this)\" >'+"); 
			out.println("'<input class=but_input type=button name=BUT_TXT_AC_NO_BULK value=\"...\" onClick=\"help_button_account_bulk()\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr>'+");
			out.println("'<td width=\"29%\" ><DIV id=DIV_TXT_BRANCH_BULK  class=div_input>Branch Code * </DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_BULK maxlength=10 size=10 onblur=\"check_branch_bulk(this)\" >'+"); 
			out.println("'<input type=\"hidden\" name=hid_br_code_BULK  value=\"\" >'+"); 
			out.println("'<input class=but_input type=button name=BUT_TXT_BRANCH_BULK value=\"...\" onClick=\"help_button_branch_bulk()\" >'+"); //</td>
			out.println("'<input class=but_input type=button name=BUT_TXT_FINANCE_NO value=\"Add\" onClick=\"add_data()\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>';");
			
			
 			out.println("}");
			
//------------------------------------------------------------------------------------------------------------------------------------
			
			//-----------------------Added--By---Sandun on 02-02-2009------------------------------
			out.println("function help_button_client() {"); 
			out.println("document.Form1.hid_click.value=\"1\"");
			out.println("    document.Form1.hid_help_type.value=\"1\";");			
			//out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'ClientSql_Client_add','14');"); 
			out.println("    HelpBox('1','10','0',Crit,'ClientSql_Receipt','14');"); 
			
			out.println("}"); 
			/*out.println("else{"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_finance_no_post_dated_sql','14');"); 
			out.println("}"); 			
			out.println("}"); */
			
			out.println("function client_help_assign(oBj) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\") {");
			out.println("cheque_details.innerHTML=\"\";");
			
			out.println(" document.Form1.TXT_CLIENT_CODE.value=oBj.valout[9];"); 
			out.println(" document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println(" makeRequest(oBj.valout[9]); ");	
			out.println(" get_contract(oBj.valout[9]);");
			out.println(" }else{");	
			//out.println(" document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			//out.println(" document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			//out.println(" makeRequest(oBj.valout[3]); ");
			out.println(" document.Form1.TXT_CLIENT_CODE.value=oBj.valout[9];"); 
			out.println(" document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println(" makeRequest(oBj.valout[9]); ");
			out.println(" }");				
			out.println(" }"); 
			
			out.println("function get_contract(client_code) { ");
			out.println("document.Form1.hid_text.value='GET_FIN'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_fin&client=\"+client_code+\"\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function assign_fin(data_vec) { ");				
			out.println("for(var j=0;j<data_vec.length;j++){;");			
			out.println("con[j] = data_vec[j];");
			out.println("}");			
			out.println("}");
			
			
			out.println("function allocate_value(j){");
			out.println("flag=1;");
			out.println("if(check_null(j)){");
						
			out.println("client_code  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("cheque_no    = document.Form1.elements[\"TXT_CHEQUE_NO_\"+j].value;");
			out.println("cheque_date  = document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+j].value+'-'+document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+j].value+'-'+document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+j].value;");
			out.println("account_no   = document.Form1.elements[\"TXT_ACC_NO_\"+j].value;");
			out.println("m_amt        = document.Form1.elements[\"TXT_CHEQUE_AMOUNT_\"+j].value;");
			out.println("m_branch     = document.Form1.elements[\"TXT_BRANCH_\"+j].value;");
			out.println("m_curr_code  = document.Form1.elements[\"TXT_CURR_CODE_\"+j].value;");
			
			out.println("for(count_con=0 ; count_con<con.length;count_con++){");			
			out.println("if(count_con==0){");
		  out.println("change1.innerHTML+='<table border=0 width=100% class=table><tr class=pdn_txtpos2>'+");
			out.println("'<td width=20%>Contract No</td>'+");
			out.println("'<td width=20%>Cheque No</td>'+");
			out.println("'<td width=15%>Account No</td>'+");
			out.println("'<td width=15% align=right>Amount</td>'+");
			out.println("'<td width=15% align=right>Balance Amount</td>'+");
			out.println("'<td width=5%>&nbsp;</td></tr>'+");
		  out.println("'</table>';");
			out.println("}");
			out.println("change1.innerHTML+='<table border=0 width=100% class=table><tr >'+");
			out.println("'<td width=20%>'+con[count_con]+'</td>'+");
			out.println("'<td width=20%>'+cheque_no+'</td>'+");
			out.println("'<td width=15%>'+account_no+'</td>'+");
			out.println("'<td width=15% align=right><input type=text name=allo_amount_'+j+''+count_con+' class=\"txt_input\" style=\"text-align:right\" value = \"0.00\" onchange=\"chk_manu_bal('+count_con+','+j+'),check_number(document.Form1.allo_amount_'+j+''+count_con+',20),set_balance('+j+')\"></td>'+");//check_number(document.Form1.allo_amount_'+i+',20),
			out.println("'<td width=15% align=right><input type=text value='+m_amt+' name=TXT_BAL_AMT_'+j+''+count_con+' class=\"txt_input\" disabled style=\"text-align:right\"></td>'+");
			out.println("'<td width=5%  align=center><input type=checkbox name=CHK_'+j+''+count_con+' value=\"off\" onclick=\"check_status('+j+','+count_con+')\"><input type=hidden name=HID_TXT_FINANCE_NO_'+j+''+count_con+' value='+con[count_con]+'></td></tr>';");				
			out.println("'</tr>'+");		
			out.println("'</table>';");	
			out.println("}");
			out.println("change1.innerHTML+='<table border=0 width=100% class=table><tr >'+");
		  out.println("'<td><input type=hidden value='+count_con+' name=contract_count_'+j+'></td>'+");
		  out.println("'<tr></table>';");
			out.println("con_length=con.length;");			
			out.println("if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("document.Form1.elements['BUT_ALLO_'+j].disabled=true;");
			out.println("document.Form1.MORE_BUT_CON.disabled=false;");
			out.println("}"); 			
		  out.println("}"); 
		  out.println("}"); 
			
						
			out.println("function del_row(row1,row) {");				
			out.println("var m_row = '';");
 			out.println("var z=0;");
			out.println("if(arr_size==0){");
		  out.println("new_window();");
			out.println("}");
			out.println("if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("m_row = m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("'<tr align=left>'+");  
			out.println("'<td width=\"10%\"><input class=\"but_input\" type=\"button\" name=MORE_BUT_CON value=\"Add\" onClick=\"add_row()\"></td>'+"); 
			out.println("'</tr></table>';");
			out.println("}");
			
			out.println("for(j=0;j<parseFloat(arr_size);j++){");
			out.println("if(j==row1){");
			out.println("}else{	");			  
			out.println("m_row = m_row+'<br><table align=\"center\" style=\"bgcolor=#C8C8C8\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"12%\" align=left ><b>Cheque No *</td> '+");
		  out.println("'<td width=\"12%\" align=left ><b>Cheque Date</td> '+");
		  out.println("'<td width=\"5%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"14%\" align=right><b>Cheque Amount</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Account No</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"16%\" align=left ><b>Branch</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'<td width=\"10%\" align=left ><b>Currency</td> '+");
		  out.println("'<td width=\"6%\"  align=left >&nbsp;</td> '+");
		  out.println("'</tr></table>';");
		
			out.println("m_row = m_row+'<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr bgcolor=#C8C8C8 style=height:30>'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHEQUE_NO_'+z+' maxlength=\"10\" size=\"10\"  style={width=100px;} value='+document.Form1.elements[\"TXT_CHEQUE_NO_\"+j].value+' onblur=\"validate_cheque_no('+z+')\" ></td>'+");
			out.println("'<td width=\"12%\"  align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_DD'+z+' maxlength=\"2\" size=\"2\" value ='+document.Form1.elements[\"TXT_CHEQUE_DATE_DD\"+j].value+' onblur=\"check_dd_1('+z+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_MM'+z+' maxlength=\"2\" size=\"2\" value ='+document.Form1.elements[\"TXT_CHEQUE_DATE_MM\"+j].value+' onblur=\"check_mm_1('+z+')\" >'+");
			out.println("'                                  <input class=\"txt_input5\" type=\"text\" name=TXT_CHEQUE_DATE_YY'+z+' maxlength=\"4\" size=\"4\" value ='+document.Form1.elements[\"TXT_CHEQUE_DATE_YY\"+j].value+' onblur=\"check_date_1('+z+')\" ><input  type=\"hidden\" name=TXT_CHEQUE_DATE_'+z+' value='+document.Form1.elements[\"TXT_CHEQUE_DATE_\"+j].value+'></td>'+");
			out.println("'<td width=\"5%\" style=\"{cursor:hand; }\" onclick=\"load_calendar(1,'+z+')\">Calender</td>'+"); 
			out.println("'<td width=\"14%\"  align=\"right\"><input  style=\"{text-align:right}\" class=\"txt_input type=\"text\" name=TXT_CHEQUE_AMOUNT_'+z+' maxlength=\"20\" size=\"10\" value = '+document.Form1.elements[\"TXT_CHEQUE_AMOUNT_\"+j].value+' onblur=\"check_ntm('+z+')\"></td>'+"); 
			out.println("'<td width=\"10%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_ACC_NO_'+z+' maxlength=\"20\" size=\"20\" onblur=\"check_account('+z+')\" style={width=90px;} value = '+document.Form1.elements[\"TXT_ACC_NO_\"+j].value+' ></td>'+"); 
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_ACC_NO_'+z+' value=\"...\" onClick=\"help_button_9('+z+')\"  ></td>'+"); 
			out.println("'<td width=\"16%\"                  ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+z+' maxlength=\"20\" size=\"20\" style={width=140px;} onblur=\"check_branch('+z+')\" value = '+document.Form1.elements[\"TXT_BRANCH_\"+j].value+' ><input type=\"hidden\" name=hid_br_code_'+j+'  ></td>'+"); //name=TXT_BRANCH_'+y+'    
			out.println("'<td width=\"6%\"                   ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_'+z+' value=\"...\" onClick=\"help_button_branch('+z+')\"  ></td>'+"); 
			out.println("'<td width=\"10%\"                  ><select class=txt_input type=text name=TXT_CURR_CODE_'+z+' maxlength=1 size=1 style={width=90px;} value='+document.Form1.elements[\"TXT_CURR_CODE_\"+j].value+' >'+");  
			
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
			"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
			"WHERE ACTIVE_STATUS='Y' "+ 
			"ORDER  BY DEFAULT_VALUE DESC ");
			more = rs.next();		
			while(more){
			out.println("'<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>'+");			
			more=rs.next();
			}
			out.println("'</select>'+");
			out.println("'</td>'+");
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ALLO_'+z+' value=\"Allocate\" disabled onClick=\"allocate_value('+z+')\"  ></td>'+"); 
			out.println("'<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL_'+z+' value=\"Delete\" onClick=\"del_row('+z+','+z+')\"  ></td>'+"); 
			out.println("'</tr></table>';");						
				
	   	out.println("for(i=0;i<parseFloat(con.length);i++){");
			out.println("if(i==0){");
		  out.println("m_row = m_row+'<table border=0 width=100% class=table><tr class=pdn_txtpos2>'+");
			out.println("'<td width=20%>Contract No</td>'+");
			out.println("'<td width=20%>Cheque No</td>'+");
			out.println("'<td width=20%>Account No</td>'+");
			out.println("'<td width=15% align=right>Amount</td>'+");
			out.println("'<td width=15% align=right>Balance Amount</td>'+");
			out.println("'<td width=5%>&nbsp;</td></tr>'+");
		  out.println("'</table>';");
			out.println("}");
			
			out.println("m_row =   m_row+'<table border=0 width=100% class=table><tr >'+");
			out.println("'<td width=20%>'+con[i]+'</td>'+");
			out.println("'<td width=20%>'+document.Form1.elements[\"TXT_CHEQUE_NO_\"+j].value+'</td>'+");
			out.println("'<td width=20%>'+document.Form1.elements[\"TXT_ACC_NO_\"+j].value+'</td>'+");
			out.println("'<td width=15% align=right><input type=text name=\"allo_amount_'+z+''+i+'\" class=\"txt_input\" style=\"text-align:right\" value = '+document.Form1.elements[\"allo_amount_\"+j+\"\"+i].value+' onchange=\"chk_manu_bal('+i+','+z+'),check_number(document.Form1.allo_amount_'+z+''+i+',20),set_balance('+z+')\"></td>'+");//check_number(document.Form1.allo_amount_'+i+',20),
			out.println("'<td width=15% align=right><input type=text value='+document.Form1.elements[\"TXT_BAL_AMT_\"+j+\"\"+i].value+' name=\"TXT_BAL_AMT_'+z+''+i+'\" class=\"txt_input\" disabled style=\"text-align:right\"></td>'+");
			out.println("'<td width=5%  align=center><input type=checkbox name=\"CHK_'+z+''+i+'\"  value='+document.Form1.elements[\"CHK_\"+j+''+i].value+' onclick=\"check_status('+z+','+i+')\" value=\"\"><input type=hidden name=\"HID_TXT_FINANCE_NO_'+z+''+i+'\" value='+con[i]+' ></td>'+");				
			out.println("'</tr>'+");		
			out.println("'</table>';");			
			out.println("}");			
			out.println("z=z+1;");			
			out.println("}");			
			out.println("}");			
			out.println("if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("document.Form1.MORE_BUT_CON.disabled=false;");
			out.println("}");
			out.println("arr_size=z;");
			out.println("lineno=z;");
 			out.println("change1.innerHTML=m_row; ");			
			out.println("chk_not_delete_chq(row);");
			out.println("}");
			
			out.println("function chk_not_delete_chq(pos){");			
			out.println("for(var n = 0;n<arr_size;n++){");
			out.println("for(var m=0;m<con.length;m++){");
			out.println("if(document.Form1.elements[\"CHK_\"+n+''+m].value==\"YES\"){");			
			out.println("document.Form1.elements[\"CHK_\"+n+''+m].checked=true;");
			out.println("document.Form1.elements[\"allo_amount_\"+n+''+m].disabled=true;");
			
			out.println("}");
			out.println("}");
			out.println("}");			
			out.println("}");	
					
			out.println("function check_status(k,obj) {");					
			out.println("if(document.Form1.elements['CHK_'+k+''+obj].checked){");
			out.println(" document.Form1.elements['CHK_'+k+''+obj].value=\"YES\";");	
			out.println(" document.Form1.elements['allo_amount_'+k+''+obj].disabled=true;");
			out.println("}else{");
			out.println(" document.Form1.elements['CHK_'+k+''+obj].value=\"NO\";");	
			out.println(" document.Form1.elements['allo_amount_'+k+''+obj].disabled=false;");
			out.println(" reverse_amount(k,obj);");
			out.println("  document.Form1.elements['allo_amount_'+k+''+obj].value=format_noobject(0.00);");
			out.println("}");			
			out.println("}");	
			
			out.println("function reverse_amount(k,obj){");
			out.println("  var sett_amt=0;");
			out.println("  hid_cnt=parseFloat(con.length);");
			out.println(" amount   = parseFloat(unformat_noobject(document.Form1.elements['TXT_CHEQUE_AMOUNT_'+k].value));");
			out.println(" un_allo_amt =parseFloat(unformat_noobject(document.Form1.elements['allo_amount_'+k+''+obj].value));");		
			out.println("  for(var j=0;j<hid_cnt;j++){"); 			
			out.println("  sett_amt = sett_amt + parseFloat(unformat_noobject(document.Form1.elements['allo_amount_'+k+''+j].value));");		
			out.println("}");
			out.println("  for(var j=0;j<hid_cnt;j++){"); 	
			out.println("  document.Form1.elements['TXT_BAL_AMT_'+k+''+j].value=format_noobject(amount-(sett_amt-un_allo_amt));");
			out.println("}");	
			out.println("}");	
			
			out.println("function chk_manu_bal(obj,k){");	
			out.println("  var hid_cnt=0;");
			out.println("  var sett_amt=0;");
			out.println("  var amount=0;");
			out.println("  hid_cnt=parseFloat(con.length);");
			out.println(" for(j=0;j<hid_cnt;j++){"); 
			out.println("  sett_amt = sett_amt + parseFloat(unformat_noobject(document.Form1.elements['allo_amount_'+k+''+j].value));");		
			out.println("}");
			out.println(" amount   = parseFloat(unformat_noobject(document.Form1.elements['TXT_CHEQUE_AMOUNT_'+k].value));");
			out.println("if(sett_amt > amount){");
			out.println("  alert('Allocated Amount cannt be greater than Rs. '+format_noobject(amount));");
			out.println("  document.Form1.elements['allo_amount_'+k+''+obj].value = document.Form1.elements['TXT_BAL_AMT_'+k+''+obj].value;");
			out.println("}");
			out.println("if(parseFloat(document.Form1.elements['allo_amount_'+k+''+obj].value)>0){");
			out.println(" document.Form1.elements['CHK_'+k+''+obj].checked=true;");		 
			out.println(" document.Form1.elements['CHK_'+k+''+obj].value=\"YES\";");
		  out.println(" document.Form1.elements['allo_amount_'+k+''+obj].disabled=true;");
			out.println("}else{");
			out.println(" document.Form1.elements['CHK_'+k+''+obj].value=\"NO\";");
			out.println("}");
			out.println("}");
			
			out.println("function set_balance(k){");			
			out.println("  var hid_cnt=0;");
			out.println("  var bal_amt=0;");
			out.println("  var amount=0;");	
			out.println(" amount   = parseFloat(unformat_noobject(document.Form1.elements['TXT_CHEQUE_AMOUNT_'+k].value));");
			out.println("  hid_cnt=parseFloat(con.length);");
			out.println("  for(j=0;j<hid_cnt;j++){"); 
			out.println("  amount = amount - parseFloat(unformat_noobject(document.Form1.elements['allo_amount_'+k+''+j].value));");
		  out.println("  for(var i=0;i<hid_cnt;i++){");
		  out.println("  document.Form1.elements['TXT_BAL_AMT_'+k+''+i].value=format_noobject(amount);");
			out.println("}");
			out.println("}");			
			out.println("}");
			
					
			out.println("function check_null(j){");
			out.println("if(document.Form1.TXT_TYPE.value==\"B\"){");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\") {");
			out.println("alert('Client Code can not be blank.');");
			out.println("new_window();");
			out.println("return false;");
			out.println("}");
			out.println("else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			out.println("else if(document.Form1.TXT_TYPE.value==\"N\"){");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\") {");
			out.println("alert('Client Code can not be blank.');");
			out.println("new_window();");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[\"TXT_CHEQUE_NO_\"+j].value==\"\") {");
			out.println("alert('Cheque no can not be blank.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[\"TXT_CHEQUE_DATE_\"+j].value==\"\") {");
			out.println("alert('Cheque date can not be blank.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[\"TXT_CHEQUE_AMOUNT_\"+j].value==\"\") {");
			out.println("alert('Cheque amount can not be blank.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[\"TXT_ACC_NO_\"+j].value==\"\") {");
			out.println("alert('Account no can not be blank.');");
			out.println("return false;");
			out.println("}");			
			out.println("else if(document.Form1.elements[\"hid_br_code_\"+j].value==\"\") {");
			out.println("alert('Branch can not be blank.');");
			out.println("return false;");
			out.println("}");
			out.println("else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			out.println("}");			
			
			out.println("function count_receipts(){ ");			
			out.println("count=0;");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("for(l=0;l<parseInt(arr_size);l++){");
			out.println("for(m=0;m<parseInt(con_length);m++){");
			out.println("m_chk=\"CHK_\"+l+\"\"+m;");
			out.println("if(document.Form1.elements[m_chk].checked==true){");
		  out.println("count=count+1;");
			out.println("}");					
      out.println("}");		
			out.println("}");
			out.println("}else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			
			out.println("for(n=0;n<parseInt(document.Form1.hid_no_val.value);n++){");
			//out.println("alert(document.Form1.hid_no_val.value)");
			//out.println("alert(document.Form1.elements['CHK_EDIT_'+n].value)");
			out.println("m_chk_edit=\"CHK_EDIT_\"+n;");
			out.println("if(document.Form1.elements[m_chk_edit].checked==true){");
		  out.println("count=count+1;");
			out.println("}");					
		  out.println("}");
			out.println("}");
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");		
			out.println("}"); 
			
			
			out.println("function ckeck_data(){ "); 			
			out.println("b_flag=0;");	
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_inner = change1.innerHTML");
			out.println("}else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("m_inner = cheque_details.innerHTML");
			out.println("}"); 
			out.println("if(m_inner==\"\"){");
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
			
			out.println("function before_submit(){ "); 
			out.println("if(validate_data()){"); 
			out.println("ckeck_data();");
			out.println("validate_cheques();");
			out.println("if(b_flag==0)");						
			out.println("if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}"); 
		  out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+arr_size+'&fin_count='+con_length+'';");  //added by nuwan de silva 26-07-07
			out.println("		}");
			out.println("else	if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("if(no!=\"0\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+no+'';");
			out.println("		}");
			out.println("else if(no==\"0\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques?number='+document.Form1.hid_no_val.value+'';");  //hid_count
			out.println("		}");
			out.println("		}");
			out.println("		document.Form1.submit();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 			
			out.println("} "); 
			
			
			out.println("function load_edit_contracts(q){"); 
			out.println("edit_flag=1");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=edit_allocation&pod_no='+document.Form1.elements['TXT_PD_NO_'+q].value+'';");
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
						
			out.println("function check_allo_amount(id){");
			out.println("  var hid_cnt=0;");
			out.println("  var b=0;");			
			out.println("  var amount_chque=0;");	
			out.println("  var amount_contract=0;");
			out.println("  var amount_contract_new=0;");
			out.println("  b = document.Form1.hid_chq.value;");			
			out.println("  hid_cnt = document.Form1.EDIT_CON.value;");
			out.println(" amount_chque  = parseFloat(unformat_noobject(document.Form1.elements['TXT_CH_AMOUNT_'+b].value));");
			out.println("  for(j=1;j<hid_cnt;j++){"); 
			out.println("  amount_contract = amount_contract+parseFloat(unformat_noobject(document.Form1.elements['CHQ_AMT_'+j].value));");
			out.println("}");	
			out.println("if(parseFloat(amount_chque) < parseFloat(amount_contract)){");
			out.println("alert(\"Can not allocate amount more than cheque amount Rs.\" + format_noobject(amount_chque));");
			out.println("document.Form1.elements['CHQ_AMT_'+id].value=0");
			out.println("amount_contract=0;");
			out.println("  for(j=1;j<hid_cnt;j++){"); 
			out.println("  amount_contract = amount_contract+parseFloat(unformat_noobject(document.Form1.elements['CHQ_AMT_'+j].value));");
			out.println("}");	
			out.println("document.Form1.elements['CHQ_AMT_'+id].value=parseFloat(amount_chque)-parseFloat(amount_contract);");
			out.println("}");
			out.println("  for(j=1;j<hid_cnt;j++){"); 
			out.println("  amount_contract_new = amount_contract_new+parseFloat(unformat_noobject(document.Form1.elements['CHQ_AMT_'+j].value));");
			out.println("}");				
			out.println("document.Form1.ALLO_CHQ_AMT.value = format_noobject(amount_contract_new);");
			out.println("}");			
			
			
			out.println("function set_amt(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"CHQ_AMT_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("format_number(document.Form1.elements[nt],22)");
			out.println("}");
			//------------------------------------------------------------------------------
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"0\">");
			out.println("<input type='hidden' name='hid_x' VALUE=\"0\">");
			out.println("<input type='hidden' name='hid_val' VALUE=\"\">");
			out.println("<input type='hidden' name='hid_st' VALUE=\"\">");
			out.println("<input type='hidden' name='hid_save' VALUE=\"Save\">");
			out.println("<input type='hidden' name='hid_text' VALUE=\"\">");
			out.println("<input type='hidden' name='hid_click' VALUE=\"0\">");
			out.println("<input type='hidden' name='hid_xx' VALUE=\"0\">");  
			out.println("<input type='hidden' name='hid_cal_date' VALUE=\"\">");
			out.println("<input type='hidden' name='hid_count' VALUE=\"\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_DD' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_MM' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_YY' VALUE=\"\">"); 
      out.println("<input type='hidden' name='hid_chq' VALUE=\"0\">");


			
			/*out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Post Dated Cheques Entry</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Withdraw\");' onClick='load_screen_status(\"WITHDRAW\")' value=\"Withdraw\"></td>");  
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
			*/
			
			/*out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
      */
			
			
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Post Dated Cheques Entry</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Withdraw\");' onClick='load_screen_status(\"WITHDRAW\")' value=\"Withdraw\" disabled></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='copy_window()' value=\"Copy\"></td>"); 
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


			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			//AP20061122-0182
			/*out.println("<tr>"); //Modified By Sandun on 02-02-2009
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO' class=div_input>Finance No*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' value=\"\" onblur=\"makeRequest(),check_finance()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_1()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		*/
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"help_button_client()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NO' value=\"...\" onClick=\"help_button_client()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style='{width:350px}' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='40' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV  class=div_input>Enter Type</DIV></td>"); 
			out.println("<td width='40%' ><select class=txt_input type=text name=TXT_TYPE maxlength=1 size=1 onChange=\"change_type(this)\" style='{width:120px}'>");  
			out.println("<option value=\"N\" selected>Normal</option>");
			out.println("<option value=\"B\" >Bulk</option>");
			out.println("</select>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' valign = 'top'> Comments </td>"); 
			out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:350px; height:50px;\" maxlength='1000' size='1000'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
			out.println("</table>"); 
			
			/*out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Cheque No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Start Date </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>End Date </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>No of Cheques</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Amount</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Account No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Branch Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
      */
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td width='*%' ><div id=cheque_details_main></div></td>");
			out.println("<tr></table>"); 
			
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td width='*%' ><div id=cheque_details></div></td>");
			out.println("<tr></table>"); 
			/*
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><input class=\"but_input\" type=\"button\" name=MORE_BUT_CON value=\"Add\" onClick=\"add_row()\"></td></tr></table>");
			out.println("<br>"); 
			out.println("</table>"); 
			*/

			out.println("<table align='center' width='100%' class='table' border=\"0\" bgcolor=\"#FFFACD\">"); 
			out.println("<tr>");  
			out.println("<td width='*%' ><div id=change1></div></td>");
	 		out.println("<tr></table>"); 
			
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='allocation_details'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='edit_allocation'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 

			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		else if(m_chksql.equals("cheque_details")){		
			int i=0;		
			int x=0;	
			String m_client_code = req.getParameter("m_client_code");	
			String m_screen_name= req.getParameter("m_val");	
			//String m_finance_no = req.getParameter("m_finance_no");	
						
			/*rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
																	"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
														  		"WHERE CLIENT_CODE='"+m_client_code+"' ");
			boolean more1 = rs1.next();
				
				
			rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),SETTLE_MODE, "+
			"PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
			"CLIENT_CODE,'x','x', "+
			" NVL(CURR_CODE,'-'),to_char(nvl(CHEQUE_AMOUNT,0),'999,999,999,999.99') "+
			"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
		 // "WHERE CLIENT_CODE='"+m_client_code+"'  "+
		  " WHERE CLIENT_CODE='"+m_client_code+"'  AND STATUS='INV' "+ //NUWAN DE SILVA
			" ORDER BY POD_REF_NO ASC");
			boolean more = rs.next();
			
			*/
	
					rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
	 			 	"WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ");//AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')   ---Modifyed By Sandun on 02-02-2009
    			boolean more1 = rs1.next();
		
					rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),to_char(CHEQUE_DATE,'dd-mm-yyyy'),SETTLE_MODE, "+
					"PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
					"CLIENT_CODE,'X','X',NVL(CURR_CODE,'-'),to_char(nvl(CHEQUE_AMOUNT,0),'999,999,999,999.99') ,FINANCE_NO "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
				  "WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')   AND STATUS='INV'  "+//AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  ---Modifyed By Sandun on 02-02-2009
					"ORDER BY POD_REF_NO ASC ");
					boolean more = rs.next();
						
						

			if(rs1.getInt(1)!=0){	
			
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"15%\" ><b>Contract No</td>"); 
			out.println("<td width=\"15%\" ><b>POD No </td>"); 
			out.println("<td width=\"15%\" ><b>Cheque No</td>");
			out.println("<td width=\"15%\" ><b>Cheque Date </td>"); 
			out.println("<td width=\"15%\" align=right><b>Allocated Amount </td>"); 
			out.println("<td width=\"15%\" ><b>Account No </td>"); 
			out.println("<td width=\"10%\" ><b>Branch  </td>"); 
			out.println("<td width=\"15%\" ><b>Currency </td>"); 
			out.println("</tr >"); 

			
	    int j = 0; 
			while(more){

			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(12)+"</td>"); 
			out.println("<td width='15%' style='{width:120px;text-align:left;cursor:hand;}' onClick=\"show_pod_cheque_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>");
			out.println("<td width='15' style='{width:120px;text-align:left;}'>"+rs.getString(2)+"</td>");

			if(rs.getString(3)!=null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(3)+"</td>");
			}
			else if(rs.getString(3)==null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'></td>");
			}
			out.println("<td width='15%' style='{width:120px;text-align:right;}'>"+rs.getString(11)+"</td>"); 
			if(rs.getString(6)!=null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(6)+"</td>"); 
			}
				if(rs.getString(6)==null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>-</td>"); 
			}
			
			if(rs.getString(5)!=null){
			out.println("<td width='10%' style='{width:120px;text-align:left; cursor:hand;}' onClick=\"show_branch_drill('"+rs.getString(5)+"')\" ><u>"+rs.getString(5)+"</u></td>"); 
			}
			if(rs.getString(5)==null){
			out.println("<td width='10%' style='{width:120px;text-align:left;}'>-</td>"); 
			}
			
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("</tr>");
			more=rs.next();
			j=j+1;
		
			if (!more)
			{
			break;
			}
			
			}	
		
			out.println("<input type=hidden name=hid_count value="+j+">");
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</table>");
			out.println("</table>");
		
			
			}
			
			
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr><td>");
			out.println("<td ><input type=hidden name=hid_no_val value=\""+rs1.getInt(1)+"\"></td>");
			out.println("</tr>");
			out.println("</table>");
			
			
	}
			
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		else if(m_chksql.equals("cheque_details_edit")){		
			int i=0;		
			int x=0;
			int j = 0; 
			String m_client_code = req.getParameter("m_client_code");	
			String m_screen_name= req.getParameter("m_val");	
								
					rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
	 			 	"WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND STATUS='INV' ");
							
						
			    boolean more1 = rs1.next();
		
					rs = stmt.executeQuery (" SELECT POD_REF_NO,A.CHEQUE_NO,TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CLIENT_CODE,'','',CURR_CODE,to_char(nvl(sum(CHEQUE_AMOUNT),0),'999,999,999,999.99'),"+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),COUNT(*) "+
																	" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES a "+
																	" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND STATUS='INV'  "+
																	" GROUP BY POD_REF_NO,a.cheque_no,CHEQUE_DATE,SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CLIENT_CODE,CURR_CODE "+
																	" ORDER BY POD_REF_NO ASC  ");


		
		
			
			boolean more = rs.next();

			if(rs1.getInt(1)!=0){	
			
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
				out.println("<br>");			
	
			
				out.println("<tr class=pdn_txtpos2>");
				//out.println("<td width=\"15%\" ><b>Contract No </td>"); //Mod By Sandun on 21-07-2009
				out.println("<td width=\"15%\" ><b>POD No </td>"); 
				out.println("<td width=\"10%\" ><b>Cheque No</td>");
				out.println("<td width=\"20%\" ><b>Cheque Date </td>"); 
				out.println("<td width=\"10%\" align=right><b>Cheque Amount </td>");
				out.println("<td width=\"10%\" ><b>Account No </td>"); 
				out.println("<td width=\"5%\" >&nbsp</td>"); 
				out.println("<td width=\"10%\" ><b>Branch  </td>"); 
				out.println("<td width=\"5%\" >&nbsp</td>"); 
				out.println("<td width=\"2%\" align='center'></td>"); 
				//out.println("<td width=\"10%\" ><b>Currency </td>"); 
				//out.println("<td width=\"6%\" >&nbsp</td>"); 
				out.println("</tr >"); 

		    
				while(more){
			
						rs2 = stmt2.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+ //added by nuwan de silva 27-07-07
																"ORDER BY DEFAULT_VALUE DESC ");
						boolean more2 = rs2.next();													

        //out.println("<tr><td width='15%' >"+rs.getString(13)+"</u><input type='hidden' name=TXT_FIN_NO_"+j+" value=\""+rs.getString(13)+"\" disabled></td>");//Mod By Sandun on 21-07-2009
				out.println("<td width='15%' style='{text-align:left; cursor:hand;}' onClick=\"show_pod_cheque_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input type='hidden' name=TXT_PD_NO_"+j+" value=\""+rs.getString(1)+"\" disabled></td>");
				//out.println("<td width='13%' style='{text-align:left;}'>"+rs.getString(2)+"<input class='txt_input2' type='hidden' name=TXT_CH_NO_"+j+" maxlength=\"6\" size=\"4\" value=\""+rs.getString(2)+"\"></td>");
				out.println("<td width='13%'   style='{text-align:left;}'><input class='txt_input' type='text' name=TXT_CH_NO_"+j+" value=\""+rs.getString(2)+"\" maxlength=\"8\" onBlur=\"validate_cheque_no2('"+j+"')\"><input class='txt_input2' type='hidden' name=HID_TXT_CH_NO_"+j+" maxlength=\"6\" size=\"4\" value=\""+rs.getString(2)+"\"> </td>"); 

			 
				if(rs.getString(3)!=null){
				
				out.println("<td width='20%' style='{text-align:left;}'><input class='txt_input5' type='text' name=TXT_CH_DATE_DD"+j+" value=\""+rs.getString(3).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dd("+j+")\">");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_MM"+j+" value=\""+rs.getString(3).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_mm("+j+")\">");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_YY"+j+" value=\""+rs.getString(3).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_date("+j+")\"><input type='hidden' name=TXT_CH_DATE_"+j+" value=\""+rs.getString(3)+"\"><a href style=\"{cursor:hand; }\" onclick=\"load_calendar(2,"+j+")\">Calendar</a></td>");
	
				}
			 if(rs.getString(3)==null){
				out.println("<td width='20%' style='{text-align:left;}'><input class='txt_input5' type='text' name=TXT_CH_DATE_DD"+j+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dd("+j+")\">");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_MM"+j+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_mm("+j+")\">");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_YY"+j+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_date("+j+")\"><input type='hidden' name=TXT_CH_DATE_"+j+" value=\"\" ><a href style=\"{cursor:hand; }\" onclick=\"load_calendar(2,"+j+")\">Calendar</a></td>");

				}    //yyyyyyyyyyyyyyyyyyyyyyyyyy

				out.println("<td width='13%' align=right><input class='txt_input2' style='{text-align:right;}' type='text' name=TXT_CH_AMOUNT_"+j+" value=\""+rs.getString(11)+"\" onblur=\"check_amt("+j+")\"></td>"); 
				if(rs.getString(6)!=null){
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input' type='text' name=TXT_AC_NO_"+j+" value=\""+rs.getString(6)+"\" onblur=\"check_account("+j+")\"></td>"); 
				}
				if(rs.getString(6)==null){
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input' type='text' name=TXT_AC_NO_"+j+" value=\"\" onblur=\"check_account("+j+")\"></td>"); 
				}
				out.println("<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_AC_NO_"+j+" value=\"...\" onClick=\"help_button_10("+j+")\"></td>"); 
				if(rs.getString(5)!=null){
				out.println("<td width='10%' style='{text-align:left;}'><input class='txt_input2' type='text' name=TXT_BR_CODE_"+j+" value=\""+rs.getString(12)+"\" onblur=\"check_branch_2("+j+")\" ><input type=\"hidden\" name=hid_br_code_"+j+" value=\""+rs.getString(5)+"\"></td>");  //=\""+rs.getString(5)+"\"
				}
				if(rs.getString(5)==null){
				out.println("<td width='10%' style='{text-align:left;}'><input class='txt_input2' type='text' name=TXT_BR_CODE_"+j+" value=\"\" onblur=\"check_branch_2("+j+")\" ><input type=\"hidden\" name=hid_br_code_"+j+" ></td>"); //added by nwan
				}
				
				out.println("<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_BR_CODE_"+j+" value=\"...\" onClick=\"help_button_branch_2("+j+")\" ></td>"); 
				/*out.println("<td><SELECT  name=TXT_CUR_"+j+" class=\"txt_input2\"> ");
				
				while(more2){
				if(rs2.getString(1).equals(rs.getString(10))){
				out.println("<OPTION value=\""+rs2.getString(1)+"\" selected>"+rs2.getString(2)+"</OPTION>");
				}
								
				else if(!rs2.getString(1).equals(rs.getString(10))){
				out.println("<OPTION value=\""+rs2.getString(1)+"\" >"+rs2.getString(2)+"</OPTION>");
				}
				more2=rs2.next();
				}

				out.println("</select>"); */
				//out.println("</td>");
				//out.println("<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+i+' value=\"Delete\" onClick=\"onclick_del_1("+j+")\"></td>"); 
				out.println("<td width=\"2%\" align='center' ><input  type=\"checkbox\" name=CHK_EDIT_"+j+"  onClick=\"check_withdraw(this,'"+j+"');\"></td>"); //,'"+rs.getInt(13)+"'
				out.println("");
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
				out.println("</table>");
				}
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<tr><td>");
				//out.println("<td ><input type=hidden name=hid_no_val value=\""+rs1.getInt(1)+"\"></td>");
				out.println("<td ><input type=hidden name=hid_no_val value=\""+j+"\"></td>");
				out.println("</tr>");
				out.println("</table>");
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		}
		
			else if(m_chksql.equals("cheque_details_withdraw")){		
			int i=0;		
			int x=0;	
			String m_client_code = req.getParameter("m_client_code");	
			String m_screen_name= req.getParameter("m_val");	
		//	String m_finance_no = req.getParameter("m_finance_no");	
	
						
				/*	rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
	 			 	"WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND STATUS='INV' ");*/
					
					rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
	 			 	"WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND STATUS='INV' ");
						
			    boolean more1 = rs1.next();
		
					/*rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),to_char(CHEQUE_DATE,'dd-mm-yyyy'),SETTLE_MODE, "+
					"PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
					"CLIENT_CODE,'X','X',NVL(CURR_CODE,'-'),to_char(nvl(CHEQUE_AMOUNT,0),'999,999,999,999.99') "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
				  "WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  AND STATUS='INV'  "+
					"ORDER BY POD_REF_NO ASC "); */
					
					rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),to_char(CHEQUE_DATE,'dd-mm-yyyy'),SETTLE_MODE, "+
					"PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
					"CLIENT_CODE,'X','X',NVL(CURR_CODE,'-'),to_char(nvl(CHEQUE_AMOUNT,0),'999,999,999,999.99'),FINANCE_NO  "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
				  "WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND STATUS='INV'  "+
					"ORDER BY POD_REF_NO ASC ");
					
			
			boolean more = rs.next();

			if(rs1.getInt(1)!=0){	
			
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
				out.println("<br>");			
	
			
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"15%\" ><b>Contract No </td>");
				out.println("<td width=\"15%\" ><b>POD No </td>"); 
				out.println("<td width=\"13%\" ><b>Cheque No</td>");
				out.println("<td width=\"15%\" ><b>Cheque Date </td>"); 
				out.println("<td width=\"10%\" align=right><b>Cheque Amount </td>");
				out.println("<td width=\"15%\" ><b>Account No </td>"); 
				out.println("<td width=\"6%\" >&nbsp</td>"); 
				out.println("<td width=\"10%\" ><b>Branch  </td>"); 
				//out.println("<td width=\"10%\" ><b>Currency </td>"); 
				out.println("<td width=\"6%\" >Withdraw</td>"); 
				out.println("</tr >"); 

		    int j = 0; 
				while(more){
			
						rs2 = stmt2.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+ //added by nuwan de silva 27-07-07
																"ORDER BY DEFAULT_VALUE DESC ");
						boolean more2 = rs2.next();													

        out.println("<tr><td width='15%' >"+rs.getString(12)+"</u><input type='hidden' name=TXT_FIN_NO_"+j+" value=\""+rs.getString(12)+"\" disabled></td>");
				out.println("<td width='15%' style='{text-align:left; cursor:hand;}' onClick=\"show_pod_cheque_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input type='hidden' name=TXT_PD_NO_"+j+" value=\""+rs.getString(1)+"\" disabled></td>");
				out.println("<td width='13%' style='{text-align:left;}'>"+rs.getString(2)+"<input class='txt_input2' type='hidden' name=TXT_CH_NO_"+j+" maxlength=\"6\" size=\"4\" value=\""+rs.getString(2)+"\" disabled></td>");
			 
				if(rs.getString(3)!=null){
				
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input5' type='text' name=TXT_CH_DATE_DD"+j+" value=\""+rs.getString(3).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dd("+j+")\" disabled>");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_MM"+j+" value=\""+rs.getString(3).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_mm("+j+")\" disabled>");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_YY"+j+" value=\""+rs.getString(3).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_date("+j+")\" disabled><input type='hidden' name=TXT_CH_DATE_"+j+" value=\""+rs.getString(3)+"\"></td>");
	
				}
			 if(rs.getString(3)==null){
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input5' type='text' name=TXT_CH_DATE_DD"+j+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dd("+j+")\" disabled>");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_MM"+j+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_mm("+j+")\" disabled>");
				out.println("<input class='txt_input5' type='text' name=TXT_CH_DATE_YY"+j+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_date("+j+")\" disabled><input type='hidden' name=TXT_CH_DATE_"+j+" value=\"\" ></td>");

				}

				out.println("<td width='10%' align=right><input class='txt_input2' style='{text-align:right;}' type='text' name=TXT_CH_AMOUNT_"+j+" value=\""+rs.getString(11)+"\" onblur=\"check_amt("+j+")\" disabled></td>"); 
				if(rs.getString(6)!=null){
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input' type='text' name=TXT_AC_NO_"+j+" value=\""+rs.getString(6)+"\" onblur=\"check_account("+j+")\" disabled></td>"); 
				}
				if(rs.getString(6)==null){
				out.println("<td width='15%' style='{text-align:left;}'><input class='txt_input' type='text' name=TXT_AC_NO_"+j+" value=\"\" onblur=\"check_account("+j+")\" disabled></td>"); 
				}
				out.println("<td width=\"6%\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_AC_NO_"+j+" value=\"...\" onClick=\"help_button_10("+j+")\" disabled></td>"); 
				if(rs.getString(5)!=null){
				out.println("<td width='10%' style='{text-align:left;}'><input class='txt_input2' type='text' name=TXT_BR_CODE_"+j+" value=\""+rs.getString(5)+"\" disabled></td>"); 
				}
				if(rs.getString(5)==null){
				out.println("<td width='10%' style='{text-align:left;}'><input class='txt_input2' type='text' name=TXT_BR_CODE_"+j+" value=\"\" disabled></td>"); 
				}
				out.println("</select>");
				out.println("</td>");
				out.println("<td width=\"6%\" align='center' ><input  type=\"checkbox\" name=CHK_WITHDRAW_"+j+"  onClick=\"check_withdraw(this)\"></td>"); 
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
				out.println("</table>");
				}
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<tr><td>");
				out.println("<td ><input type=hidden name=hid_no_val value=\""+rs1.getInt(1)+"\"></td>");
				out.println("</tr>");
				out.println("</table>");
		}
		
		
			else if(m_chksql.equals("view_cheques")){		
			int count=0;		
			
			String m_cheque_no = req.getParameter("cheque_no");	
			String m_branch_code= req.getParameter("branch_code");	
			//String m_finance_no = req.getParameter("m_finance_no");	
				    
					rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),SETTLE_MODE,"+
					" PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
					" CLIENT_CODE,NVL(CURR_CODE,'-'),NVL(CHEQUE_AMOUNT,0) "+
					" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
				  " WHERE UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_branch_code+"') AND UPPER(CHEQUE_NO)=UPPER('"+m_cheque_no+"')    "+ //AND STATUS='INV'
					" ORDER BY POD_REF_NO ASC ");
										
					out.println("<HTML><HEAD><TITLE>Post Dated Cheque Details</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Post Dated Cheque Details</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
					boolean more = rs.next();
					
			   if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if (more) {
				
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>The cheque number that has been entered already exists </u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Post Dated Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(7)+"') ><u>"+rs.getString(7)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_pod_cheque_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("</tr>");
					more = rs.next();
				}
				  out.println("</table>");
					
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>In the event this is the genuine cheque number please add a suffix to the actual number </b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>Example :</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Actual Number</b></td>");
					out.println("<td width='*%' class=div_input><b>123456</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>Please enter additional records as </b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Example :</b></td>");
					out.println("<td width='*%' class=div_input><b>123456<font color='red'>A</font></b></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input><b>123456<font color='red'>B</font>&nbsp;&nbsp;etc.</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
				  
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

		}
		else if(m_chksql.equals("edit_allocation")){		
		String m_pod_no = req.getParameter("pod_no");
		int j=1;
		double m_total = 0;
		rs = stmt.executeQuery (" SELECT A.POD_REF_NO, "+
														" A.FINANCE_NO, "+
														" A.CHEQUE_AMOUNT "+
														" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A "+
														" WHERE A.POD_REF_NO = '"+m_pod_no+"' ");
														
														
			boolean more = rs.next();
			
			out.println("<table align=\"\" width=\"37%\" border=\"0\" class=\"table\">");
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td widht='2%' align='center'>No</td>");
			out.println("<td widht='20%'>Finance No</td>");
			out.println("<td widht='15%' align='right' >Allocated Amount</td>");
			//out.println("<td widht='5%'>&nbsp;</td>");
			out.println("</tr>");
			
			
			while(more){
			out.println("<tr >");
			out.println("<td widht='2%' align='center' >"+j+"</td>");
			out.println("<td widht='20%'>"+rs.getString(2)+"<input type='hidden' name=TXT_FIN_NO_"+j+" value=\""+rs.getString(2)+"\"></td>");
			out.println("<td widht='15%' align='right' ><input type='text' style='text-align:right' name=CHQ_AMT_"+j+" class='txt_input' value=\""+nf.format(rs.getDouble(3))+"\" onchange=\"check_allo_amount('"+j+"'),set_amt("+j+")\" onblur=\"\"></td>");
			//out.println("<td widht='5%'><input type='checkbox' name=edit_chl_"+j+" vlaue='on' checked></td>");
			out.println("</tr>");
			j=j+1;
			m_total = m_total+rs.getDouble(3);
			more = rs.next();
			}
			
			out.println("<tr >");
			out.println("<td widht='100%' colspan=4><hr color='black'></td>");
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td widht='22%' colspan=2><b>Total</td>");
			out.println("<td widht='20%' align='right' ><input type='text' style='text-align:right;border:none' readonly name=ALLO_CHQ_AMT class='txt_input' value=\""+nf.format(m_total)+"\"></td>");
			out.println("</tr>");
			
			out.println("<input type='hidden' name=EDIT_CON value="+j+" >");
			out.println("<input type='hidden' name=ALLO_TOT value="+m_total+" >");
			out.println("</table>");
															
		
		}
		
		
		
		
//--------------------------------------------------------------------------------------------------------------------			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null){try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
