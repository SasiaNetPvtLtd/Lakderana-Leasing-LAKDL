
//--         :1.7 LOCATION CREATION PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - LOCATION
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :20-07-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_location extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	
	public ResultSet rs,rs1,rs3;
	Statement stmt,stmt1;
	Connection conn;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;
			
			
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Locations</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
						
			out.println("var array_location=new Array();");
			out.println("var array_contact_name=new Array();");
			out.println("var array_contact_tel=new Array();");
			out.println("var array_contact_fax=new Array();");
			
	   // out.println("m_writedata='<TR>' +");
	    //out.println("'<TD><B>Contact Person</B></TD><TD><B>Telephone Number</B></TD><TD><B>Fax Number</B></TD>' +");
	    //out.println("'</TR>';");
      //out.println("array_location[lineno]=m_writedata;");		 
			//out.println("lineno=lineno+1;");
			//out.println("arr_size=arr_size+1;");
			
			
			out.println("function display_data(data_vec){");
			//out.println("alert('test1'+data_vec[0]);");
			
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
		  out.println("j=0;");
			out.println("i=0;");
			
			out.println("if(data_vec.length==0){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr><TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+j+' value=\"\" maxlength=\"50\" size=\"50\"></TD>'+");
		  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+j+' value=\"\" maxlength=\"10\"  size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td></tr></table>';");
    
		  out.println("j=j+1;");	
			
			out.println("}");
			
			out.println("else{");
			out.println("while(i<data_vec.length){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr><TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+j+' value=\"'+data_vec[i]+'\" maxlength=\"50\" size=\"50\"></TD>'+");
		  out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+j+' value='+data_vec[i+1]+' maxlength=\"10\" size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+j+' value='+data_vec[i+2]+' maxlength=\"10\" size=\"10\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td></tr></table>';");
   					
			out.println("j=j+1;");
			out.println("i=i+3;");
			out.println("}"); //End of for loop;
			
			out.println("}");
			out.println("lineno=j;");
			out.println("arr_size=j;");
			
      out.println("}"); 
		
				
			out.println("function get_vector(data_vec) {");
		//	out.println("				alert('Record Length'+data_vec.length);");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exists.');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			//out.println("   alert('Selected Location code is incorrect,use help...!')");
			out.println("      help_update();");
			out.println("      document.Form1.TXT_LOCATION_CODE.focus() ; ");
			out.println("			}");
			
			
			//Added by Prabash on 24-04-2012-----------**
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M8'){");
		
			out.println("				alert('Location Prefix already exists.');");
			out.println("      document.Form1.TXT_LOCATION_PREFIX.value=\"\" ; ");
			out.println("      document.Form1.TXT_LOCATION_PREFIX.focus() ; ");
			out.println("			}");
			//-----------------------------------------**
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_CITY_CODE.value!=\"\"){");
			//out.println("   alert('Selected City code is incorrect,use help...!')");
			//out.println("      document.Form1.TXT_CITY_CODE.value=\"\" ; ");
			out.println("   help_button_1();");
		  out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3'  && document.Form1.TXT_POSTAL_CODE.value!=\"\"){");
			//out.println("   alert('Selected Postal code is incorrect,use help...!')");
			//out.println("      document.Form1.TXT_POSTAL_CODE.value=\"\" ; ");
			out.println("   help_button_2();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'  && document.Form1.TXT_POSTAL_CODE.value!=\"\"){");
			out.println("    assignState('M7');"); 
			out.println("    makeRequest(document.Form1.TXT_POSTAL_CODE);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4'  && document.Form1.TXT_COUNTRY_CODE.value!=\"\"){");
			//out.println("   alert('Selected Country code is incorrect,use help...!')");
			//out.println("      document.Form1.TXT_COUNTRY_CODE.value=\"\" ; ");
			out.println("   help_button_3();");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M6'){");
			out.println("				alert('Record already exists.');"); 
			out.println("       help_new_desc();");
		  out.println("			    document.Form1.TXT_LOCATION_DESC.value=\"\" ");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_LOCATION_DESC.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_LOCATION_PREFIX.value=data_vec[7];");  // added by udara on 15-03-2012
			out.println("    document.Form1.TXT_LOCATION_PREFIX.disabled=true;"); // added by udara on 15-03-2012
			out.println("    assignState('M5');"); 
			out.println("    makeRequest(document.Form1.TXT_LOCATION_CODE);");
      		out.println("			}");
			
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M5'){");
      		out.println("      display_data(data_vec);"); 		
			out.println("			}");
			
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=data_vec[1];"); 
			out.println("			}");
			
			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
			/*Comment by Chadana on 16/05/2007 */
		/*	out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=N\";");*/
			
			/* ====== Added by Chandana on 16/05/2007 ====== */
			out.println("    if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location1&data_val=\"+obj.value;");
			out.println("}");
			
			//--------Added by Prabash on 24-04-2012----------**
			out.println("    else if(document.Form1.hid_chk_status.value=='M8'&& document.Form1.SCREEN_NAME.value==\"NEW\") {");
			
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location_Prefix&data_val=\"+obj.value;");
		//	out.println("alert(m_url);");
			out.println("}");
			//------------------------------------------------**
			
			out.println("    else");
			out.println("    if((document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"NEW\")){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
    	out.println("    if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=N\";");
			/* ===== End Chandana on 16/05/2007 ==== */			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_country&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M5')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location_new&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			
			
			//out.println("     if(document.Form1.hid_chk_status.value=='M6' && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_LOCATION_DESC.value==\"\") {");
			//out.println("    alert('Please Enter Location Description.');");
			//out.println("    document.Form1.TXT_LOCATION_DESC.value=\"\" ;");
			//out.println("    document.Form1.TXT_LOCATION_DESC.focus();");
			//out.println("}");
			
			out.println("    else if(document.Form1.hid_chk_status.value=='M6' && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_LOCATION_DESC.value!=\"\") {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location_desc&data_val=\"+obj.value;");
			out.println("}");
			
			out.println("    else if(document.Form1.hid_chk_status.value=='M7') {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location_fill_city_country&data_val=\"+obj.value;");
			out.println("}");
			
			
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			out.println("function header(){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\"><B>Contact Person</B></TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Telephone Number</B></TD>'+");
			out.println("'<TD WIDTH=\"40%\" align=\"left\"><B>Fax Number</B></TD>' +");
	    out.println("'</TR></table>';");
     	out.println("}");
			
			out.println("function add_row(){"); 
			//out.println("validate_tel();");
			
			out.println("var b_flag=0;");
			
			
			  out.println("if(lineno!=0){");
				out.println("count=lineno-1;");
				out.println("m_contact_name=\"TXT_LOCATION_CONTACT_PERSON\"+count");
			  out.println("m_contact_tel=\"TXT_LOCATION_TEL\"+count");
			  out.println("m_contact_fax=\"TXT_LOCATION_FAX\"+count");
			
				out.println("if(document.Form1.elements[m_contact_name].value==\"\") {");
				out.println("alert('Contact Name Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_tel].value==\"\") {");
				out.println("alert('Tel Number Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_tel].value.length!=10) {"); // Added by Udara Somathilake on 15/10/2009
				out.println("alert('Wrong Telephone Number Length');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(isNaN(document.Form1.elements[m_contact_tel].value)) {"); // Added by Udara Somathilake on 15/10/2009
				out.println("alert('Invalid Telephone Number');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_fax].value==\"\") {");
				out.println("alert('Fax Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_contact_fax].value.length!=10) {"); // Added by Udara Somathilake on 15/10/2009
				out.println("alert('Wrong Fax Number Length');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(isNaN(document.Form1.elements[m_contact_fax].value)) {"); // Added by Udara Somathilake on 15/10/2009
				out.println("alert('Invalid Fax Number');");
				out.println("b_flag=1;");
				out.println("}");
				
				
				out.println("else{");
				out.println("b_count=0;");
				out.println("tmp_name=document.Form1.elements[m_contact_name].value;");
				
				out.println("for(var i=0;i<lineno-1;i++){");
				out.println("m_tmp_name=\"TXT_LOCATION_CONTACT_PERSON\"+i");
				
				out.println("if(lineno>=2){");
				out.println("if(document.Form1.elements[m_tmp_name].value==tmp_name){");
				out.println("alert('Contact Name Can not Be Duplicated')");  //nnnnnnnnn
				out.println("b_flag=1;");
				out.println("b_count=1};");
				
				out.println("if(b_count==1){");
				out.println("break;}");
				
				out.println("}");
				
			  out.println("}");
				
			  out.println("}");
				
				
			  
				out.println("}");
				
					
				out.println("if(b_flag==0){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+lineno+' maxlength=\"200\" size=\"50\"></TD>'+");
		  	out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+lineno+' maxlength=\"60\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+lineno+' maxlength=\"60\" size=\"10\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+')\">'+");
				out.println("'</td></tr></table>';");
      
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
      	out.println("}");
								
		    out.println("}");
				
				//------------ADDED BY CHANDANA ON 17/07/2007 --------//
			  out.println("function chk_contact_del(){");	
				out.println("m_con_per_name=\"TXT_LOCATION_CONTACT_PERSON\"+(parseInt(lineno)-1)");
				out.println("m_tmp_name=document.Form1.elements[m_con_per_name].value;"); 
								
				out.println("for(var i=0;i<lineno;i++){");				
				out.println("m_con_per_name=\"TXT_LOCATION_CONTACT_PERSON\"+i");
				out.println("if(document.Form1.elements[m_con_per_name].value==m_tmp_name){");
				out.println("if((parseInt(lineno)-1)!=i){");
				out.println("alert('Contact Name Can not Be Duplicated')");
				out.println("b_flag1=1;");
				out.println("break;");
				out.println("}else{");
				out.println("b_flag1=2;");
				out.println("}");
				out.println("}");
				out.println("}");
				 
				out.println("if(b_flag1==1){");	
				out.println("return false;"); 	
				out.println("}else{");
				out.println("return true;");
				out.println("}");
				out.println("}");
			//---------------- END ON 17/07/2007 -------------------//
    		
			out.println("function del_row(rowNo){"); 
			
			//out.println("alert('test1 -' +rowNo)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			
			out.println("m_contact_name=\"TXT_LOCATION_CONTACT_PERSON\"+i");
			out.println("m_contact_tel=\"TXT_LOCATION_TEL\"+i");
			out.println("m_contact_fax=\"TXT_LOCATION_FAX\"+i");
			
			
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
		//	out.println("alert('name -' +document.Form1.elements[m_contact_name].value)");
			
			out.println("array_contact_name[j]=document.Form1.elements[m_contact_name].value;");
		  out.println("array_contact_tel[j]=document.Form1.elements[m_contact_tel].value;");
			out.println("array_contact_fax[j]=document.Form1.elements[m_contact_fax].value;");
			
		//	out.println("alert('val -' +array_contact_name[j]);");
			
		//	out.println("alert('test2' - +document.Form1.elements[m_contact_name].value)");
			
		//  out.println("array_location[j]='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_LOCATION_CONTACT_PERSON'+j+'	VALUE='+document.Form1.elements[m_contact_name].value+'>'+");	
		//	out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_LOCATION_TEL'+j+'	VALUE='+document.Form1.elements[m_contact_tel].value+'>'+");
	   // out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_LOCATION_FAX'+j+'	VALUE='+document.Form1.elements[m_contact_fax].value+'>';");
     
			out.println("j=j+1;");
			
			out.println("}");
		
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}"); 
			
			  out.println("function write_data(size){");
				
			
				
				out.println("m_table.innerHTML=\"\";");
        out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
				
				//out.println("hid_m_contact_name=\"hid_TXT_LOCATION_CONTACT_PERSON\"+j");
			  //out.println("hid_m_contact_tel=\"hid_TXT_LOCATION_TEL\"+j");
			  //out.println("hid_m_contact_fax=\"hid_TXT_LOCATION_FAX\"+j");
				//out.println("alert('val 2'+hid_m_contact_name);");	
			
			  out.println("if(array_contact_name[j]==\"\" && array_contact_tel[j]==\"\" && array_contact_fax[j]==\"\" ){");
				//out.println("alert('hid value :'+document.Form1.elements[hid_m_contact_name].value);");
				//out.println("if(document.Form1.elements[hid_m_contact_name].value==\"\" ){");
				
				//out.println("array_contact_name[j]=0;");
				//out.println("array_contact_tel[j]=0;	");
				//out.println("array_contact_fax[j]=0;	");
			  
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr><TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+j+' value=\"\" maxlength=\"50\" size=\"50\"></TD>'+");
		    out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+j+' value=\"\" maxlength=\"10\" size=\"10\"></TD>'+");
			  out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+j+' value=\"\" maxlength=\"10\" size=\"10\">'+");
			  out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td></tr></table>';");
        out.println("continue;");
				out.println("}");
						
			     
		   out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr><TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+j+' value=\"'+array_contact_name[j]+'\" maxlength=\"50\" size=\"50\"></TD>'+");
		   out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+j+' value='+array_contact_tel[j]+' maxlength=\"10\" size=\"10\"></TD>'+");
			 out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+j+' value='+array_contact_fax[j]+' maxlength=\"10\" size=\"10\">'+");
			 out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td></tr></table>';");
   
		   /*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr><TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CONTACT_PERSON'+j+' value='+document.Form1.elements[hid_m_contact_name].value+' maxlength=\"50\" size=\"50\"></TD>'+");
		   out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_TEL'+j+' value='+document.Form1.elements[hid_m_contact_tel].value+' maxlength=\"10\" size=\"10\"></TD>'+");
			 out.println("'<TD WIDTH=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_FAX'+j+' value='+document.Form1.elements[hid_m_contact_fax].value+' maxlength=\"10\" size=\"10\"></TD>'+");
			 out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td></tr></table>';");*/
   
			
			 out.println("}");
			 out.println("}");		
				
			

			out.println("function validate_data(){"); 
			
				out.println("//validations goes here");
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_LOCATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			//out.println("alert('Please Enter Location Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION_DESC.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_DESC.style.color='red';");
			//out.println("alert('Please Enter Location Description.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS1.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1.style.color='red';");
			//out.println("alert('Please Enter Address 1.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			//out.println("alert('Please Enter City Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_POSTAL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_POSTAL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			// added by udara on 15-03-2012
			out.println("else if(document.Form1.TXT_LOCATION_PREFIX.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_PREFIX.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			// end by udara on 15-03-2012

			/*out.println("else if(document.Form1.TXT_REGION.value=='NOT_SELECT'){  ");  // Added By: Samith Dilshan on 2015-06-01--commented by milinda 2015-10-08 ##18300
			out.println("DIV_TXT_REGION.style.color='red';");
			out.println("return false;"); 
			out.println("}");*/
			
			out.println("else{"); 
			
			//out.println(" chk_contact_del();");
			
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("m_option = document.Form1.hid_status.value;"); 
			//out.println("alert('m_option:'+m_option);");
			out.println("if(m_option=='New') {");
			out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Edit') {");
			out.println("m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Deactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Deactivate?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Reactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Reactivate?'; ");
			out.println("		}"); 
			
			out.println("		if(validate_data()){"); 
			
			out.println(" if(chk_contact_del()){");
			
			out.println("		if(confirm(m_sav_msg)){ ");
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			
					
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_location';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}");
			
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_location';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_location';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_location\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Locations - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Locations - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_LOCATION_DESC.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_POSTAL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_COUNTRY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REGION.disabled=true;");  // By Samith Dilshan on 2015-06-01

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
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"33\"){"); 
			out.println("		help_new_desc_value_assign_33();"); 
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
			out.println("	else {");
			out.println("	clear_data();");
			out.println("	 }");
			out.println(" }	"); 
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
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
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_POSTAL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_POSTAL_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[2];"); 
			out.println("    assignState('M7');"); 
			out.println("    makeRequest(document.Form1.TXT_POSTAL_CODE);");
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_COUNTRY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_COUNTRY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_LOCATION_PREFIX.value=oBj.valout[9];"); // added by udara on 15-03-2012
			//out.println("alert(oBj.valout[10]);");
			out.println("    document.Form1.TXT_REGION.value=oBj.valout[10];"); // added by udara on 15-03-2012
			out.println("    document.Form1.TXT_LOCATION_PREFIX.disabled=true;"); // added by udara on 15-03-2012
			out.println("    assignState('M5');"); 
			out.println("    makeRequest(document.Form1.TXT_LOCATION_CODE);");

			out.println("}"); 
			
			out.println("function help_new_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"33\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_NEW_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_DESC.value+\"@\";"); 
			out.println("    } ");
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			
			out.println("function help_new_desc_value_assign_33() {"); 
			//out.println("    document.Form1.TXT_LOCATION_DESC.value=\"\" ;"); 
			//out.println("    document.Form1.TXT_LOCATION_DESC.focus();"); 
			out.println("}");
			
			out.println("function clear_data() {");//**
			//out.println("if( document.Form1.hid_chk_status.value == 'M1' && document.Form1.SCREEN_NAME.value!=\"NEW\") {");//**
	
			out.println(" if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_LOCATION_CODE.value='';"); 
			out.println("document.Form1.TXT_LOCATION_CODE.focus() ;"); 
			/*out.println("document.Form1.TXT_LOCATION_DESC.value='';");
			out.println("document.Form1.TXT_ADDRESS1.value='';");
			out.println("document.Form1.TXT_ADDRESS2.value='';");
			out.println("document.Form1.TXT_POSTAL_CODE.value='';");
			out.println("document.Form1.TXT_CITY_CODE.value='';");
			out.println("document.Form1.TXT_COUNTRY_CODE.value='';");
			out.println("for(var j=0;j<arr_size;j++){");
			
			out.println("document.Form1.elements[\"TXT_LOCATION_CONTACT_PERSON\"+j].value=\"\"");
		  out.println("document.Form1.elements[\"TXT_LOCATION_TEL\"+j].value=\"\"");
		  out.println("document.Form1.elements[\"TXT_LOCATION_FAX\"+j].value=\"\"");

			out.println("}");*/
			//out.println("m_table.innerHTML=\"\"");
			out.println(" }");
			
			out.println(" if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_POSTAL_CODE.value='';");
			out.println("document.Form1.TXT_POSTAL_CODE.focus();");
			out.println("document.Form1.TXT_CITY_CODE.value='';");
			out.println("document.Form1.TXT_COUNTRY_CODE.value='';");
			out.println(" }");
			out.println("}");
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();"); //Comment by Chandana on 15/05/2007
      out.println(" }");
			
			
			
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+");  //LAKDL_AF_MK_View_Help_Servlet AF_MAS_View_Help_Servlet
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:42em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
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
			out.println("    m_sql = \"m_view_TXT_LOCATION_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),header(),add_row()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Locations</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"Deactivate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Reactivate\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
	    out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Location Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_LOCATION_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_DESC'  class=div_input>Location Description *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_DESC' maxlength='100' size='100' onblur=\"assignState('M6'),makeRequest(document.Form1.TXT_LOCATION_DESC)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input>Address 1 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' maxlength='100' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Address 2 </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' maxlength='100' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_POSTAL_CODE'  class=div_input>Postal Code*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_POSTAL_CODE' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_POSTAL_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_POSTAL_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_CITY_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >Country Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_COUNTRY_CODE' maxlength='10' size='10' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_COUNTRY_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_COUNTRY_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			
			// Added by udara on 15-03-2012
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_PREFIX'  class=div_input>Location Prefix *</DIV></td>"); 
		//	out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_PREFIX' maxlength='3' size='10' onblur=\"\" >"); //Comment by Prabash on 24-05-2012
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_PREFIX' maxlength='3' size='10' onblur=\"assignState('M8'),makeRequest(document.Form1.TXT_LOCATION_PREFIX)\" >");  //Added by Prabash on 24-05-2012
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			// Added By: Samith dilshan
			// On : 2015-06-01
			
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
		    out.println("<td width='30%' >");
			out.println(" 	<select class='txt_input' name='TXT_REGION'>"); 
            out.println("      <OPTION value='NOT_SELECT' >Please Select</OPTION>");
			
			rs1 = stmt.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
				" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
				" WHERE ACTIVE_STATUS='Y' "+
				" ORDER BY REGIONS_DESC ");
			
			while(rs1.next()){
				out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
			}
			
			out.println(" 	</select>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			out.println("</table>"); 
			
			// End by Udara on 15-03-2012
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("<tr>");  
			out.println("<td width='80%'></td>"); 
			out.println("<td width='*%'><input class='but_input' type='button' name='BUT_ADD_LOCATION' value=\"Add\" onClick=\"add_row()\"></td>"); 
			out.println("</tr>");  
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
