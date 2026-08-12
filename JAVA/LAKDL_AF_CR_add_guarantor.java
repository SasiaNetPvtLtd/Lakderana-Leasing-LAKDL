
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
// DEVELOPED BY : MAHELA FOR OFSCL LEASING    DATE:20-02-2007

public class LAKDL_AF_CR_add_guarantor extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out =  null;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			Connection conn=m_sn_methods.met_user_validate(req);
      String m_username = m_sn_methods.username;
			stmt=conn.createStatement();
			
		
							 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_CLOSE=""; 
			
			String m_chksql = req.getParameter("chksql");
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			
			String m_application_no = req.getParameter("application_no");

			m_CLOSE = req.getParameter("CLOSE");
			
			if(req.getParameter("CLOSE")==null){
			m_CLOSE="N";
			}

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Credit - Add Guarantor </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
					
					
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.

			out.println("var array_gur_code=new Array();");
			out.println("var array_gur_name=new Array();");
			out.println("var array_relation=new Array();");
			out.println("var array_period=new Array();");
			out.println("var array_tel_no=new Array();");
			out.println("var array_nic_reg_no=new Array();");
			out.println("var application_no=\"\"; ");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			out.println("function header(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("'<TR><TD WIDTH=\"19%\" align=\"left\"><B>Guarantor Code</B></TD>'+");
			out.println("'<TD WIDTH=\"21%\"     align=\"left\"><B>Guarantor Name</B></TD>'+");
			out.println("'<TD WIDTH=\"13%\"     align=\"left\"><B>Relationship</B></TD>' +");
			out.println("'<TD WIDTH=\"6%\"     align=\"left\"><B>Period</B></TD>' +");
			out.println("'<TD WIDTH=\"13%\"     align=\"left\"><B>Telephone No</B></TD>' +");
			out.println("'<TD WIDTH=\"*%\"     align=\"left\"><B>NIC/Business Reg No</B></TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");

			out.println("function validate_gur_code(rowNo){");
			out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo");
			out.println("assignState('M8')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_VAL_GUARANTOR&data_val=\"+document.Form1.elements[m_gur_code].value+\"&data_val2=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
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
			//out.println("alert('display');");	
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");		
		  out.println("j=0;");
			out.println("i=0;");
			out.println("if(data_vec.length==0){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"\" maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor()\"></TD>'+"); 
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:200px;\" value=\"\" style=\"width:250px;\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" value=\"\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:50;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" value=\"\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
		  out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("while(i<data_vec.length){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"'+data_vec[i]+'\" maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" style=\"width:50\" type=\"button\" name=\"GUARANTOR_LINK_BUT\" value=\"Create\" onClick=\"load_guarantor()\"></TD>'+"); 
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+'  style=\"width:200px;\" value=\"'+data_vec[i+4]+'\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" value=\"'+data_vec[i+1]+'\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:50;}\" maxlength=\"2\"  value=\"'+data_vec[i+2]+'\" size=\"10\" onblur=\"val_num('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"'+data_vec[i+3]+'\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" value=\"'+data_vec[i+5]+'\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
			out.println("j=j+1;");
			out.println("i=i+6;");
			out.println("}"); //End of for loop;
			out.println("}");
			out.println("lineno=j;");
			out.println("arr_size=j;");
			out.println("disable_nic_no(arr_size);");
      out.println("}"); 
					
					
			out.println("function get_vector(data_vec) {");
			out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
      out.println("      display_data(data_vec);"); 		
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			//out.println("       document.Form1.elements[m_gur_code].value=\"\"; "); 
			//out.println("				help_button_5(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			out.println("			 alert('Guarantor code already exist ')	");
			out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
			out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
			out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
			out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
			out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
			out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
			out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
			//out.println("      document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
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
			out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+(lineno-1)");
			out.println("m_gur_name=\"TXT_GAURANTOR_NAME\"+(lineno-1)");
			out.println("m_relation=\"TXT_RELATIONSHIP\"+(lineno-1)");
			out.println("m_period=\"TXT_PERIOD\"+(lineno-1)");
			out.println("if(document.Form1.elements[m_gur_code].value==\"\") {");
			out.println("alert('Guarantor code cannot be null.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_gur_name].value==\"\") {");
			out.println("alert('Guarantor name cannot be null.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_relation].value==\"\") {");
			out.println("alert('Relationship cannot be null.');");
			out.println("return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_period].value==\"\") {");
			out.println("alert('Period cannot be null.');");
			out.println("return false;");
			out.println("}");
			out.println("else{");
			out.println("	return true;");
			out.println("}");	  
			out.println("}");
			
	/*----------------------------------------------------------------
	
		Purpose  : Add New gurantor
	
	----------------------------------------------------------------*/			

			out.println("function add_row(){"); 
			//out.println("    alert('count -- '+lineno)");
			//out.println("chk_data();");
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
			out.println("	m_tmp_code=\"TXT_GAURANTOR_CODE\"+i");
			out.println("	if(lineno>=2){");
			out.println("		if(document.Form1.elements[m_tmp_code].value==tmp_code){");
			out.println("			alert('Guarantor code cannot be duplicated.')");
			out.println("			b_flag=1;");
			out.println("			b_count=1};");
			out.println("			if(b_count==1){");
			out.println("			break;}");
			out.println("		}");
			out.println("	}");
			out.println("}");
			out.println("}");
			out.println("if(b_flag==0){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=0 class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+lineno+' maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+lineno+' value=\"...\" onClick=\"help_button_5('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+lineno+'  style=\"width:200px;\" maxlength=\"250\" size=\"10\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+lineno+' maxlength=\"50\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+lineno+' style=\"{text-align:right; width:50;}\" maxlength=\"2\" size=\"10\" onblur=\"val_num('+lineno+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+lineno+' maxlength=\"60\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+lineno+' maxlength=\"10\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
      out.println("}");
			out.println("disable_nic_no(arr_size)");
		  out.println("}");
				
									
				
			/*----------------------------------------------------------------
				Purpose  : Delete Gurantor
	
	    ----------------------------------------------------------------*/			

			
    		
			out.println("function del_row(rowNo){"); 
			out.println("if(rowNo!=0 ){");
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
		  out.println("array_gur_name[j]=document.Form1.elements[m_gur_name].value;");
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
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"\" maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:200px;\" value=\"\" maxlength=\"250\" size=\"10\" disabled></td>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" value=\"\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:50;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"\" size=\"10\" ></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" value=\"\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_relation[j]==\"\" && array_period[j]==\"\" ){");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"'+array_gur_code[j]+'\" maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\"></TD>'+"); 
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:200px;\" value=\"'+array_gur_name[j]+'\" maxlength=\"250\" size=\"10\" disabled></td>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" value=\"\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:50;}\" maxlength=\"2\" value=\"\" size=\"10\" onblur=\"val_num('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"'+array_tel_no[j]+'\" size=\"10\" ></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" value=\"'+array_nic_reg_no[j]+'\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
			out.println("continue;");
			out.println("}");
			out.println("else{");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_CODE'+j+' value=\"'+array_gur_code[j]+'\" maxlength=\"50\" size=\"50\" onblur=\"help_button_5('+j+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+j+' value=\"...\" onClick=\"help_button_5('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_GAURANTOR_NAME'+j+' style=\"width:200px;\" value=\"'+array_gur_name[j]+'\" maxlength=\"250\" size=\"10\" disabled></td>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP'+j+' maxlength=\"50\" value=\"'+array_relation[j]+'\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' style=\"{text-align:right; width:50;}\" maxlength=\"2\" value=\"'+array_period[j]+'\" size=\"10\" onblur=\"val_num('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+' maxlength=\"60\" value=\"'+array_tel_no[j]+'\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"18%\"><input class=\"txt_input\" type=\"text\" name=TXT_NIC_REG_NO'+j+' maxlength=\"10\" value=\"'+array_nic_reg_no[j]+'\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" style=\"width:30px;\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\">'+");
			out.println("'</td><td WIDTH=\"*%\"></td></tr></table>';");
			out.println("}");
			out.println("}");	
			out.println("disable_nic_no(arr_size)");	
			out.println("}");		
					
								
				
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
					out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
					out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
					out.println("return false;"); 
				  out.println("}"); 
					out.println("if(document.Form1.TXT_APPLICANT_CODE.value==\"\"){  "); 
					out.println("DIV_TXT_APPLICANT_CODE.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 		
					out.println("else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 
		
					out.println("function check_duplicates(){");
					out.println(" m_count = lineno-1; ");
					out.println("		 m_route =	\"TXT_GAURANTOR_CODE\"+m_count ");
					out.println(" if( document.Form1.elements[m_route].value != \"\" ) { ");
					//out.println("			  m_route_count = parseInt(document.Form1.hid_area_count.value)");
					out.println("		    m_route =	\"TXT_GAURANTOR_CODE\"+m_count ");
					out.println("		    m_desc =	\"TXT_GAURANTOR_NAME\"+m_count ");
					out.println("        tmp_route = document.Form1.elements[m_route].value; ");
					out.println("			   b_count = 0;");
					out.println("         for(var i=0;i<m_count;i++){");
					out.println("            m_tmp_route=\"TXT_GAURANTOR_CODE\"+i");				
					out.println("									cur_route= document.Form1.elements[m_tmp_route].value ");
					out.println("             if(lineno >=2 ){");
					out.println("               if(document.Form1.elements[m_tmp_route].value == tmp_route){");
					out.println("                 alert('Guarantor code cannot be duplicated')");
					out.println("									document.Form1.elements[m_route].value =''; ");
					out.println("									document.Form1.elements[m_desc].value =''; ");
					out.println("                 b_count=1;");
					out.println("		 						  return false;	");
					out.println("		            }"); 
					out.println("               if(b_count==1){");
					out.println("                break;");
					out.println("		            }"); 
					out.println("             } ");
					out.println("         }");
					out.println("		 	  return true;	");
					out.println(" }"); 
					out.println("}");
		
					out.println("function before_submit(){ "); 
					out.println("		m_option = document.Form1.hid_status.value;"); 
					out.println("		if(m_option=='New') {");
					out.println("			m_sav_msg = 'Are you sure you want to Save?'; ");
					out.println("		}"); 
					out.println("		else if(m_option=='Edit') {");
					out.println("			m_sav_msg = 'Are you sure you want to Modify?'; ");
					out.println("		}"); 
					out.println("		else if(m_option=='Delete') {");
					out.println("			m_sav_msg = 'Are you sure you want to Delete?'; ");
					out.println("		}"); 
					out.println("		if(validate_data()){");
					out.println("			if(chk_data()) { ");
					out.println("  			if(check_duplicates()) {");
					out.println("     		for (var i=0; i < document.Form1.elements.length; i++ ) {");
					out.println("      			document.Form1.elements[i].disabled=false;");
					out.println("     		}");
					out.println("						if(confirm(m_sav_msg)){ "); 
					out.println("   					document.Form1.hid_no_rec.value=arr_size;");
					out.println("   					document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
					out.println("							document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_save_add_guarantor';");  
					out.println("							document.Form1.submit();	"); 
					out.println("						}"); 
					out.println("				}"); 
					out.println("			}"); 
					out.println("		}"); 
					out.println("		else { "); 
					out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
					out.println("		}");
					out.println("} "); 
					
					
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 

				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_add_guarantor?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 

				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_add_guarantor?chksql=main_page';"); 
				out.println("}"); 

				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_add_guarantor\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
					
		
					
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Credit - Add Guarantor - \"+m_val;"); 
				out.println("}"); 
		
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit - Add Guarantor  \";"); 
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
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("		if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	if(oBj.valout[1]=='Next')  {");
			out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("	}");
			out.println("	else if  (oBj.valout[1]=='Prev') {");
			out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("	}		");
			out.println("	else if(oBj.valout[1] == 'Close'){");
			out.println("	}");
			out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("	if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='2'){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='3'){"); 
			out.println("		help_value_assign_client(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='6'){"); 
			out.println("		help_value_assign_6(oBj);"); 
			out.println("	}");					
			out.println("	}"); 
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
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}");  

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
					
					out.println("function help_button_5(rowNo) {"); 
					out.println("		 m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"5\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					out.println("    Sql = \"GuarantorSql_add_gua\";"); 
				  out.println("    Crit = document.Form1.elements[m_gur_code].value+\"@Y@\";"); 
					//out.println("    Crit =document.Form1.elements[m_gur_code].value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','2',Crit,Sql,'5');"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_5(rowNo,oBj) {"); 
					out.println("		m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
					out.println("		m_gur_name=\"TXT_GAURANTOR_NAME\"+rowNo;");	
					out.println("		m_tel_no=\"TXT_TEL_NO\"+rowNo;");
					out.println("		m_nic_reg_no=\"TXT_NIC_REG_NO\"+rowNo;");	
					out.println("   document.Form1.elements[m_gur_code].value=oBj.valout[2];"); 
					out.println("   document.Form1.elements[m_gur_name].value=oBj.valout[3];"); 
          out.println("   document.Form1.elements[m_tel_no].value=oBj.valout[4];"); 
					//out.println("		alert(oBj.valout[6]);");
					out.println("   if(oBj.valout[6] == 'I') {");
					out.println("   document.Form1.elements[m_nic_reg_no].value=oBj.valout[5];"); 
					out.println("   }");
					out.println("   else {");
					out.println("   document.Form1.elements[m_nic_reg_no].value=oBj.valout[7];"); 
					out.println("   }");
					out.println("   validate_gur_code(rowNo)");
					out.println("}"); 
					
		
					out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ENT_CON@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
		
					out.println("function help_update_value_assign_99(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 					
					out.println("		 if(oBj.valout[3]=='' || oBj.valout[3]=='null' ){");
					out.println("     document.Form1.TXT_FINANCE_NO.value='-';"); 
					out.println("		 }");
					out.println("		 else{");
					out.println("     document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("    }");
					out.println("     document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("     document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					out.println("}"); 
					
					out.println("function help_finance() {"); 
					out.println("    document.Form1.hid_help_type.value=\"2\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'FinanceSql_add_gua','2');");
					out.println("}");
				
					out.println("function finance_assign(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 					
					out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					//out.println(" 	makeRequest(); ");
					out.println("}");
					
					out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					//out.println("		 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
					out.println("    Crit =document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_client(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 					
					out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					out.println("}"); 
					
					out.println("function clear_fields(){"); 
					out.println("	 if(document.Form1.hid_help_type.value==\"99\") {" ); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value =''; ");
					out.println("   }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
					out.println("   document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("  }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
					out.println("   document.Form1.TXT_APPLICANT_CODE.value='';"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value='';"); 
					out.println("  }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
					out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
					out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
					out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
					out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
					out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
					out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
					out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
					out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
					out.println("  }		"); 
					out.println("}		"); 					

					
		      out.println("function disable_app_no(){")			;
		   	  out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				  out.println("}");
						
					out.println("function load_guarantor(){");
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value;"); 
			    out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 			out.println("}");
					
					out.println("function close_screen() {");
					//out.println(" alert(document.Form1.hid_close_sts.value);");
					out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
					out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("					window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=main_page';");
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else { "); 
					out.println("		     close_window();"); 
					out.println("		}"); 
					out.println("}");

					//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
					
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"header(),add_row()\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Add Guarantor</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>");
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
	    		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
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
		
					out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");	
			   
					out.println("<table align='center' width='100%' class='table' >"); 
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
					out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_3','99')\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_3','99')\" ></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td  width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
					out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15'  OnBlur=\"help_finance()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"...\" onClick=\"help_finance()\" > </td>"); 
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICANT_CODE'  class=div_input>Client Code *</DIV></td>"); 
					out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='10' onblur=\"help_button_2('1','10','0','ClientSql_add_gua','3')\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"...\" onClick=\"help_button_2('1','10','0','ClientSql_add_gua','3')\"></td>"); 
					out.println("<td >Client Name </td>"); 
					out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
					out.println("</tr>"); 
				 	out.println("</table>"); 
			   	out.println("</td>"); 
			   	out.println("</tr>"); 
			   	out.println("</table>");
				 	out.println("<br>");  
				 	out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");	
			   	out.println("<table align='center' width='100%' >"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='90%'><input class='but_input' type='button' name='MORE_BUT' value=\"More\" onClick=\"add_row()\"></td>"); 
				 	out.println("<td width='*%'></td>"); 
			   	out.println("</tr>");  
			   	out.println("<tr>");  
			   	out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     	out.println("</tr>"); 
			   	out.println("</table>");
				 	out.println("</td>"); 
			   	out.println("<tr>");  
				 	out.println("</table>");	
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


