
//ID         :
//SCREEN NAME:SYSTEM ADMINISTRATION - GROUP INVOICES
//CREATED BY :DELANJALI
//DATE/TIME  :2007-09-12
//NOTES:


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_display_group_invoices extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
		LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Group Receipts</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;");
		out.println("var amt=0;");				
			out.println("var array_team=new Array();");
			out.println("var array_user=new Array();");
			out.println("var array_remarks=new Array();");
			out.println("var array_per=new Array();");
			out.println("var array_client=new Array();");
			out.println("var array_app=new Array();");
			out.println("var array_contract=new Array();");			
			out.println("var total_amt=0");
			out.println("var set_flag=0");
			
			out.println("function Header(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"<TR>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Ref. No</B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Client Code</B></TD>'+");			
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><B>Client Name</B></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><B>Amount</B></TD>' +");
			out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>Remarks</B></TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">&nbsp</B></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
				
			out.println("function show_client_details(val){");
			out.println("show_client(data_vec[val])");
			out.println("}");
			
			
			out.println("function show_contract_details(val){");
			out.println("show_application_detail_drill(data_vec[val])");
			out.println("}");
			
			
			out.println("function show_client_details_del(val){");
			out.println("show_client(array_client[val])");
			out.println("}");

      out.println("function show_contract_details_del(val){");
			out.println("show_application_detail_drill(array_contract[val])");
			out.println("}");


			out.println("function show_client_add(row_val){");
			out.println("var m_client=document.Form1.elements[\"hid_TXT_CLIENT_CODE\"+row_val].value");
			out.println("show_client(m_client)");
			out.println("}");

      out.println("function show_contract_add(row_val){");
			out.println("var m_contract=document.Form1.elements[\"hid_TXT_CONTRACT\"+row_val].value");
			out.println("show_application_detail_drill(m_contract)");
			out.println("}");




      out.println("function display_data(data_vec){");
			
		  out.println("j=0;");
			out.println("i=0;");
			out.println("b_flag=1");
			out.println("while(i<data_vec.length){");
			out.println("if(j==0){");
			out.println("Header()");
			out.println("}");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=0 class=\"table\" ><tr>'+");	
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_contract_details('+[i+9]+')\"><U>'+data_vec[i+9]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('+[i+1]+')\"><U>'+data_vec[i+1]+'</TD>'+");		
		  out.println("'<TD WIDTH=\"20%\">'+data_vec[i+8]+'</TD>'+");
			out.println("'<td width=\"20%\" ><input class=\"txt_input\" type=\"text\" style=\"{text-align:right}\" name=TXT_PERCENTAGE'+j+' maxlength=\"6\" size=\"10\" value=\"'+data_vec[i+3]+'\" onblur=\"sum_total('+j+')\" ></TD>'+");
			out.println("'<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARKS'+j+' maxlength=\"200\" size=\"10\" value=\"'+data_vec[i+2]+'\" style=\"{width:240px}\"></TD>'+");
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td>'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CLIENT_CODE'+j+'	VALUE='+data_vec[i+1]+'>'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_APP'+j+'	VALUE=\"'+data_vec[i+8]+'\">'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CONTRACT'+j+'	VALUE=\"'+data_vec[i+9]+'\">'+");
			
			out.println("'</tr></table>';");
			//out.println("amt=parseInt(amt)+parseInt(document.Form1.elements[\"TXT_PERCENTAGE\"+j].value)");

      out.println("j=j+1;");
			out.println("i=i+10;");
			out.println("}"); 
		  //out.println(" tot_amt(amt)");

			out.println("lineno=j;");
			out.println("arr_size=j;");
			out.println("}");
			
			out.println("  function display_user(data_vec){  "); 
			
			
		  /*out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("array_user[0]=data_vec[0];");
			out.println("array_user[1]=data_vec[1];");
			out.println("array_user[2]=data_vec[4];");
			out.println("array_user[3]=data_vec[5];");
			*/
			out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("document.Form1.hid_client_name.value=data_vec[2];"); 
			out.println("array_user[0]=data_vec[1];");			
			out.println("array_user[1]=data_vec[2];");

			out.println("  }  "); 
			
				
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_GRP_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0  && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_GRP_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("       help_update();  "); 
			out.println("			}");
		//	out.println("			else");
			//out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			//out.println("    help_button_3(); "); 
			//out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_GRP_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("    document.Form1.TXT_GRP_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_GRP_NAME.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_GRP_ADD.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_BRC_CODE.value=data_vec[6];");
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			out.println("    assignState('M2');"); 
			out.println("    makeRequest(document.Form1.TXT_GRP_CODE)");
			out.println("			}");
      out.println("			}");
			out.println("			else");
			out.println("    if(document.Form1.hid_chk_status.value==\"M2\" && arr_size==0){"); 
			out.println("      display_data(data_vec);");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("     display_user(data_vec);  "); 
			out.println("document.Form1.BUT_ADD.disabled=false ");
			//out.println("set_flag=0 ");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			//out.println("set_flag=1 ");
			out.println("    help_button_3(); "); 		
			out.println("			}");

			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("  if(document.Form1.hid_chk_status.value==\"M1\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_group_invoices&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("  if(document.Form1.hid_chk_status.value==\"M2\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_group_invoices&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url)");
			out.println("  }");
			out.println("else");
			out.println("  if(document.Form1.hid_chk_status.value==\"M3\"){"); 
			out.println("  if(array_user[0]==\"\"){");
			out.println("document.Form1.hid_client_name.value=\"\"");
			out.println("}");			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println(" ");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest_new(obj) {");
			//out.println("document.Form1.BUT_TXT_CLIENT_CODE.focus()");
			out.println("document.Form1.hid_chk_status.value=\"M3\""); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_GRP_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_GRP_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_GRP_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_GRP_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_GRP_ADD.value==\"\"){  "); 
			out.println("DIV_TXT_GRP_ADD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_BRC_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_BRC_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\" && m_table.innerHTML==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\" && m_table.innerHTML==\"\" && document.Form1.SCREEN_NAME.value==\"NEW\"){  "); 
			out.println("alert('Please Add The Records');");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function tot_amt(val){");
			//out.println("alert(val)");
			out.println("m_total.innerHTML='<table align=\"center\" border=0 width=\"100%\" class=\"table\"><tr class=tr_input1><td width=\"20%\" ><b>Total Receipt Amount </td><td width=\"20%\">&nbsp</td><td width=\"20%\"><input class=\"txt_input\" type=text style=\"{font:bold;}\" name=\"TXT_TOT_AMOUNT\" value=\"'+val+'\"></td><td width=\"*%\"></td></tr></table>';"); 
			out.println("}");
			

			
			out.println("function check_br(){");
			out.println("val_new=parseInt(arr_size);");
			out.println("val_of=parseInt(arr_size)-1;");
			
			out.println("br_code='hid_TXT_CLIENT_CODE'+val_of;");
			out.println("brcode=document.Form1.elements[br_code].value;");
			out.println("if(brcode!=\"\"){");
			out.println("			   b_count = 0;");
			out.println("for(var i=0;i<parseInt(val_of);i++){");
			out.println("br_code1='hid_TXT_CLIENT_CODE'+i");
			out.println("brcode1=document.Form1.elements[br_code1].value;");
			out.println(" if(val_new >=1 ){");
			out.println("if(brcode==brcode1)");
			out.println("{");
			out.println("alert('Client already entered...!');");
			//out.println("document.Form1.elements[br_code].value='';");
			//out.println("document.Form1.elements[br_code].focus();");
			out.println("b_count=1");
			out.println("return false;");
			out.println("	}");
			out.println("	if(b_count==1){");			
			out.println("	break");				
			out.println("	}");				
			out.println("	}");	
			out.println("	}");	
			out.println("return true");
			out.println("}"); 
			
			out.println("}"); 
			
			out.println("function add_row(obj){"); 
			out.println("check_client(document.Form1.TXT_CLIENT_CODE)");
			out.println("var b_status=1;");
			out.println("if(obj.value==\"\"){");
			out.println("alert('Select Client Code ,Use Help');");
			out.println("b_status=0;");
			out.println("}");
			out.println("else if(document.Form1.TXT_GRP_CODE.value==\"\"){");
			out.println("alert('Group Code cannot be null');");
			out.println("b_status=0;");
			out.println("}");	
			out.println("else");
			out.println("if(obj.value!=\"\"){");
			out.println("for(var i=0;i<lineno;i++){");
			out.println("m_client=\"hid_TXT_CLIENT_CODE\"+i");
			out.println("if(obj.value==document.Form1.elements[m_client].value ){");
			out.println("alert('Client Code already exist');");
			out.println("b_status=0;");
			out.println("break;");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("if(b_status==1){");
			out.println("if(lineno==0){");
			out.println("Header()");
			out.println("}");
			out.println("m_table.innerHTML+='<table align=\"center\" border=0 width=\"100%\" class=\"table\"><tr>'+");
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_contract_add('+lineno+')\"><U>'+array_user[2]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_add('+lineno+')\"><U>'+array_user[0]+'</TD>'+");		
		  out.println("'<TD WIDTH=\"20%\">'+array_user[1]+'</TD>'+");
			
		  out.println("'<td width=\"20%\" ><input class=\"txt_input\" style=\"{text-align:right}\" type=\"text\" name=TXT_PERCENTAGE'+lineno+' maxlength=\"6\" size=\"10\" onblur=\"sum_total('+lineno+')\" ></TD>'+");
		  out.println("'<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARKS'+lineno+' maxlength=\"200\" size=\"10\" style=\"{width:240px}\"></TD>'+");
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE_DEL'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+')\"></td>'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CLIENT_CODE'+lineno+'	VALUE=\"'+array_user[0]+'\">'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_APP'+lineno+'	VALUE=\"'+array_user[1]+'\">'+");	
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CONTRACT'+lineno+'	VALUE=\"'+array_user[2]+'\">'+");
			out.println("'</tr></table>';");
			//out.println("amt=parseInt(amt)+parseInt(document.Form1.elements[\"TXT_PERCENTAGE\"+j].value)");
      out.println("lineno=parseInt(lineno)+1;");
			out.println("arr_size=parseInt(arr_size)+1;");
      out.println("}");
			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\""); 
			out.println("document.Form1.TXT_CLIENT_CODE.focus()"); 
			out.println("document.Form1.BUT_ADD.disabled=true ");
			out.println("}");
			
			out.println("function del_row(rowNo){"); 
			out.println("var u=0;");
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_client[u]=document.Form1.elements[\"hid_TXT_CLIENT_CODE\"+i].value");
		  out.println("array_app[u]=document.Form1.elements[\"hid_TXT_APP\"+i].value");
			out.println("array_remarks[u]=document.Form1.elements[\"TXT_REMARKS\"+i].value");
			out.println("array_per[u]=document.Form1.elements[\"TXT_PERCENTAGE\"+i].value");  
			out.println("array_contract[u]=document.Form1.elements[\"hid_TXT_CONTRACT\"+i].value");
			out.println("u=u+1;");
			out.println("}");
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}"); 
			
		  out.println("function write_data(size){");
			
			out.println("alert(size);");
			out.println("m_table.innerHTML=\"\";");
      out.println(" for(var j=0;j<size;j++){");
			out.println("if(j==0){");
			out.println("Header()");
			out.println("}");

			out.println("if(array_remarks[j]==\"\" && array_per[j]==\"\"){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");			
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_contract_details_del('+[j]+')\"><U>'+array_contract[j]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details_del('+[j]+')\"><U>'+array_client[j]+'</TD>'+");		
		  out.println("'<TD WIDTH=\"20%\">'+array_app[j]+'</TD>'+");
			out.println("'<td width=\"20%\" ><input class=\"txt_input\" style=\"{text-align:right}\" type=\"text\" name=TXT_PERCENTAGE'+j+' maxlength=\"6\" size=\"10\" value=\"\" onblur=\"sum_total('+j+')\" ></TD>'+");
			out.println("'<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARKS'+j+' maxlength=\"200\" size=\"40\" value=\"\" style=\"{width:240px}\"></TD>'+");
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td>'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CLIENT_CODE'+j+'	VALUE=\"'+array_client[j]+'\">'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_APP'+j+'	VALUE=\"'+array_app[j]+'\">'+");	
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CONTRACT'+j+'	VALUE=\"'+array_contract[j]+'\">'+");
			out.println("'</tr></table>';");
			out.println("continue;");
			out.println("}");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");		
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_contract_details_del('+[j]+')\"><U>'+array_contract[j]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details_del('+[j]+')\"><U>'+array_client[j]+'</TD>'+");		
		  out.println("'<TD WIDTH=\"20%\">'+array_app[j]+'</TD>'+");
			out.println("'<td width=\"20%\" ><input class=\"txt_input\" type=\"text\" style=\"{text-align:right}\" name=TXT_PERCENTAGE'+j+' maxlength=\"6\" size=\"10\" value=\"'+array_per[j]+'\" onblur=\"sum_total('+j+')\" ></TD>'+");
			out.println("'<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARKS'+j+' maxlength=\"200\" size=\"10\" value=\"'+array_remarks[j]+'\" style=\"{width:240px}\"></TD>'+");
			out.println("'<td width=\"*%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td>'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CLIENT_CODE'+j+'	VALUE=\"'+array_client[j]+'\">'+");
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_APP'+j+'	VALUE=\"'+array_app[j]+'\">'+");	
			out.println("'<INPUT TYPE=\"hidden\" NAME=hid_TXT_CONTRACT'+j+'	VALUE=\"'+array_contract[j]+'\">'+");
			out.println("'</tr></table>';");
			//out.println("amt=parseInt(amt)+parseInt(document.Form1.elements[\"TXT_PERCENTAGE\"+j].value)");
			out.println("}");
			//out.println("document.Form1.BUT_ADD.disabled=false ");

			//out.println(" tot_amt(amt)");
			out.println("}");		
			
			out.println("function sum_total(lineno1){");
			//out.println("document.Form1.TXT_TOT_AMOUNT.value=0");
			out.println("format_number(document.Form1.elements[\"TXT_PERCENTAGE\"+lineno1],25)");
			out.println("}");
			
			out.println("function sum_total_1(){");
			out.println("document.Form1.TXT_TOT_AMOUNT.value=0");


		//	out.println("format_number(document.Form1.elements[\"TXT_PERCENTAGE\"+lineno1],25)");
			out.println("for(var e=0;e<arr_size;e++){");
			out.println("total_amt=parseInt(total_amt)+parseInt(document.Form1.elements[\"TXT_PERCENTAGE\"+e].value)");
			out.println("}");
			out.println("document.Form1.TXT_TOT_AMOUNT.value=parseInt(document.Form1.TXT_TOT_AMOUNT.value)+parseInt(total_amt)");
			
			out.println("}");


			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){");
			out.println("if(arr_size==0){");
			out.println("alert(\"Please asign client details to Group\");");
			out.println("		}");
			out.println("else if(arr_size>0){");	
					///	out.println("if(check_br()){"); 
			out.println("if(check_br()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_group_invoices';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); //
			out.println("		}"); 
			out.println("		}");
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_group_invoices';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_group_invoices';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_display_group_code\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Group Receipts - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Group Receipts - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window()");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){"); 
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("m_table.innerHTML=\"\"");		
			out.println("}"); 


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
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		} else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_3(oBj);"); 
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
			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_GRP_CODE.value='';");
			out.println("document.Form1.TXT_GRP_CODE.focus();");
			out.println("document.Form1.TXT_GRP_NAME.value='';");
			out.println("document.Form1.TXT_GRP_ADD.value='';");
			out.println("document.Form1.TXT_BRC_CODE.value='';");
			out.println("m_table.innerHTML=\"\"");		
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"3\"){");
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
			out.println("document.Form1.TXT_CLIENT_CODE.focus();");
			out.println("}");
			out.println("}");
			
			out.println("function help_button_3() {"); 
			out.println("array_user[0]=\"\"");			
			out.println("array_user[1]=\"\"");
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_client_code_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("HelpBox('1','10','0',Crit,m_sql,'3');");
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3(oBj) {"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("document.Form1.hid_client_name.value=oBj.valout[4];"); 
			out.println("document.Form1.hid_app_code.value=oBj.valout[3];"); /*Added by Chandana on 23/10/2007*/
			
			out.println("array_user[0]=oBj.valout[2];");			
			out.println("array_user[1]=oBj.valout[4];");
			out.println("array_user[2]=oBj.valout[3];");
			//out.println("alert('test=='+array_user[2]);");
			
			
			out.println("set_flag=1 ");
			out.println("document.Form1.BUT_ADD.disabled=false ");
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    m_sql = \"m_help_TXT_GRP_INV_sql\";"); 
			out.println("    Crit = document.Form1.TXT_GRP_CODE.value+\"@\"+\"Y@\";");
			
			out.println("}"); 
			out.println("    else{");
			out.println("    m_sql = \"m_help_TXT_GRP_INV_sql\";"); 			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    Crit = document.Form1.TXT_GRP_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    Crit = document.Form1.TXT_GRP_CODE.value+\"@\"+\"N@\";}"); 
			out.println("}"); 
			out.println("HelpBox('1','10','0',Crit,m_sql,'99');");

			out.println("}"); 


			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_GRP_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_GRP_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_GRP_ADD.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BRC_CODE.value=oBj.valout[5];"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			out.println("    assignState('M2');"); 
			out.println("    makeRequest(document.Form1.TXT_GRP_CODE)");
			out.println("}");
			out.println("}"); 
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
	
									
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_help_TXT_GRP_INV_sql\";");
			out.println("    m_criteria = document.Form1.TXT_GRP_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function check_client(client){");
			//out.println("assignState('M3')"); 
			out.println("makeRequest_new(document.Form1.TXT_CLIENT_CODE)");
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_name' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_code' VALUE=\"\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Group Receipts</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		  //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
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


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GRP_CODE'  class=div_input>Group Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GRP_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_GRP_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GRP_NAME'  class=div_input>Group Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GRP_NAME' maxlength='250' size='50' style='{width:220px}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GRP_ADD'  class=div_input>Group Address * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GRP_ADD' maxlength='200' size='10' style='{width:200px}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRC_CODE'  class=div_input>Business Registration No / NIC No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRC_CODE' maxlength='15' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onchange=\"makeRequest_new(document.Form1.TXT_CLIENT_CODE)\" onblur=\"makeRequest_new(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"...\" onClick=\"help_button_3()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ADD' value=\"Add\" onClick=\"add_row(document.Form1.TXT_CLIENT_CODE)\" disabled></td>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
	
						
			//out.println("<table align='center' width='100%' class='table' border=0>"); 
			//out.println("<tr>");  
			//out.println("<td width=\"100%\"><DIV ID='m_total'></DIV></td>");
		  //out.println("</tr>"); 
			//out.println("</table>");

			out.println("<br>"); 
			out.println("<table align='center' width='100%' border=\"0\">"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
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
