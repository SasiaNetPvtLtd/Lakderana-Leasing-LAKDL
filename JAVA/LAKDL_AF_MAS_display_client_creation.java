//SCREEN NAME:SYSTEM ADMINISTRATION - CLIENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_display_client_creation extends HttpServlet { 
	
	ServletOutputStream out = null;
	public Connection conn;
	public ResultSet rs,rs1;
	public Statement stmt,stmt1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_client_code = "";
			String m_client_type = "I";
			String m_inquiry_no  = "";
			String m_close_status  = "N";
			String m_screen="xx";
			String m_APP_NO="";
			String m_no_of_rec="";
			String m_client_status="";
			String m_active_status="N";
			int m_count =0;
			
			String m_app_screen = "N"; // added by udara 27-08-2014
			
			//added by nuwan de silva on 19-10-07---------------
			int m_count_gur,m_count_as,m_count_pr,m_count_pi=0;
			int m_count_vl=0;
			int m_count_client=0;
			
			if(req.getParameter("client_code")!=null){
				m_client_code = req.getParameter("client_code");
				m_client_type = req.getParameter("client_type");
				//m_inquiry_no  = req.getParameter("inquiry_no");
				//m_close_status  = req.getParameter("close_status");		
				//m_screen        = req.getParameter("screen");		
			}
			m_screen        = req.getParameter("screen");		
			String m_row        = req.getParameter("row");		
			
			String m_save_close = req.getParameter("save_close");	
			
			// added by udara 27-08-2014
			if(req.getParameter("app_screen")!=null){
				m_app_screen  = req.getParameter("app_screen");
			}
			// end by udara 27-08-2014
			
			if(req.getParameter("inquiry_no")!=null){
				m_inquiry_no  = req.getParameter("inquiry_no");
			}
			
			if(req.getParameter("APP_NO")!=null){
				m_APP_NO  = req.getParameter("APP_NO");
			}
			if(req.getParameter("no_of_rec")!=null){
				m_no_of_rec  = req.getParameter("no_of_rec");
			}
			
			String fscreen = req.getParameter("fscreen"); //add by waruna
			
			
			if(m_screen==null){
				m_screen="xx";
			}
			
			if(m_save_close==null){
				m_save_close="N";
			}
			if(req.getParameter("close_status")==null)
			{
				m_close_status="N";
			}
			else
			{
				m_close_status  = req.getParameter("close_status");
			}
			//addded by nuwan de silva on 19-10-07--------------------------------
			if(req.getParameter("count_client")!=null){
				m_count_client=Integer.parseInt(req.getParameter("count_client").trim());
				//out.println("count_client"+m_count_client);
			}
			if(req.getParameter("count_gur")!=null){
				m_count_gur=Integer.parseInt(req.getParameter("count_client").trim());
			}
			if(req.getParameter("count_as")!=null){
				m_count_as=Integer.parseInt(req.getParameter("count_client").trim());
			}
			if(req.getParameter("count_pr")!=null){
				m_count_pr=Integer.parseInt(req.getParameter("count_client").trim());
			}
			if(req.getParameter("count_pi")!=null){
				m_count_pi=Integer.parseInt(req.getParameter("count_client").trim());
			}
			if(req.getParameter("count_vl")!=null){
				m_count_vl=Integer.parseInt(req.getParameter("count_client").trim());
			}
			if(req.getParameter("m_client_status")!=null){
				m_client_status=req.getParameter("m_client_status").trim();
			}
			
			if(req.getParameter("active_status")!=null){
				m_active_status=req.getParameter("active_status").trim();
			}
			
			//-------------------end -------------------------------------------------
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Creation Of Clients</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var m_client_code=\"\";");
			out.println("var m_client_type=\"\";");
			out.println("var m_inquiry_no=\"\";");
			out.println("var m_close_status=\"N\";");
			out.println("var m_screen=\"\";");
			//	out.println("var m_save_close=\"N\";");
			out.println("m_close_status='"+m_close_status+"';");
			out.println("m_screen='"+m_screen+"';");
			/*out.println(" var m_count_gur    ='"+m_count_gur+"' ; ");
            out.println(" var m_count_as     ='"+m_count_as+"'; ");
            out.println(" var m_count_pr     ='"+m_count_pr+"'; ");
            out.println(" var m_count_pi     ='"+m_count_pi+"'; ");
            out.println(" var m_count_vl     ='"+m_count_vl+"'; ");
            out.println(" var m_count_client ='"+m_count_client+"'; ");
            */
			
			
			out.println(" var m_client_status ='"+m_client_status+"'; ");
			out.println(" var m_active_status ='"+m_active_status+"'; ");
			
			
			//out.println("m_save_close='"+m_save_close+"';");
			
			//Individual
			out.println("var lineno=0;");
			out.println("var lineno_bank=0;");
			out.println("var lineno_credit=0;");
			out.println("var lineno_nonrelated=0;");
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
			out.println("var lineno_auditor = 0;");
			
			
			out.println("var arr_size_company = 0;");
			out.println("var arr_size_subsidiaries = 0;");
			out.println("var arr_size_customer = 0;");
			out.println("var arr_size_credit_c=0;");
			out.println("var arr_size_ba=0;");
			
			//Income Expense
			out.println("var arr_size_income = 0;");
			out.println(" var income_tot=0; "); 
			
			out.println("function set_FullName() {");
			
			out.println("var array_name=new Array();");
			
			out.println("var m_sur_name=''; ");
			out.println("var m_other_name=''; ");
			
			
			out.println("if(document.Form1.TXT_SURNAME.value!=''){");
			out.println("m_sur_name=document.Form1.TXT_SURNAME.value;");
			out.println("}");
			out.println("if(document.Form1.TXT_OTHER_NAME.value!=''){");
			out.println("m_other_name=document.Form1.TXT_OTHER_NAME.value;");
			out.println("}");
			//out.println("alert('val'+m_other_name.small());");
			out.println("document.Form1.TXT_FULL_NAME_I.value=m_other_name+' '+m_sur_name;");
			
			out.println(" var i=0;");
			out.println(" var m_val='';");
			out.println(" var m_initails='';");
			out.println("m_other_name=m_other_name.toUpperCase();");
			out.println("array_name=m_other_name.split(' ',m_other_name.length);");
			out.println("");			
			out.println("while(i<array_name.length){");
			
			out.println("m_val=array_name[i].charAt(0);");
			
			out.println("if(m_val!=' '){");
			out.println("m_initails+=m_val+'.'+' ';");
			out.println("i=i+1;");
			out.println("}}");
			//out.println("}");
			out.println("document.Form1.TXT_INITIALS.value=m_initails;");
			
			out.println("}");
			
			
			out.println("function get_vector(data_vec) {");
			//out.println("     alert(document.Form1.hid_help_status.value); "); // udara test 25-08-2014
			out.println("		 count= lineno_bank-1;");
			out.println("    m_bank=\"TXT_BANK_CODE\"+count");
			out.println("    m_branch=\"TXT_BRANCH_CODE\"+count");
			out.println("		 if(data_vec.length>0 && document.Form1.hid_help_status.value == 'H_bus_cert_no' && document.Form1.SCREEN_NAME.value!=\"EDIT\" ){");
			out.println("			if(data_vec[1]=='B'){");
			out.println("				alert('Blacklisted Client');");
			out.println("     document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=''; ");
			out.println("     document.Form1.TXT_BUSINESS_CERTIFICATE_NO.focus(); ");
			out.println("			}");
			out.println("			else{");
			out.println("				alert('Business Certificate No already exists');");
			out.println("     document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=''; ");
			out.println("     document.Form1.TXT_BUSINESS_CERTIFICATE_NO.focus(); ");
			out.println("			}}");
			//out.println("			}");
			out.println("			else if(data_vec.length==0  && document.Form1.hid_help_status.value == 'H_city' &&  document.Form1.TXT_CITY_CODE.value !=''){");
			out.println("				help_button_city();");
			out.println("			}");
			out.println("			else if(data_vec.length>0 &&  document.Form1.hid_help_status.value == 'H_city' &&  document.Form1.TXT_CITY_CODE.value !=''){");
			out.println("				document.Form1.TXT_CITY_CODE.value=data_vec[0];");
			out.println("				document.Form1.TXT_CITY_DESC.value=data_vec[1];"); //ADDED BY NUWAN DE SILVA 22-06-07
			out.println("			}");
			out.println("			else if(data_vec.length>0  && document.Form1.hid_help_status.value == 'H_nic'){");
			out.println("			if(data_vec[2]=='B'){");
			out.println("			alert('Blacklisted Client');");
			out.println("     document.Form1.TXT_NIC_NO.value=''; ");
			out.println("     document.Form1.TXT_NIC_NO.focus(); ");
			out.println("			}");
			out.println("			else{");
			out.println("			alert('NIC No already exists');");
			out.println("     document.Form1.TXT_NIC_NO.value=''; ");
			out.println("     document.Form1.TXT_NIC_NO.focus(); ");
			out.println("			}}");
			//out.println("     m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_info?chksql=main_page&data_val=\"+data_vec[0]+\"&ac_status=Y\";");
			//out.println("     window.open(m_url,'displayWindow5','left=0,top=133,width=750,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=0,resizable=0');"); 
			//out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'H_ind'){");
			out.println("				assign_data_individual(data_vec);");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'H_ind' &&  document.Form1.TXT_CLIENT_CODE.value !='' ){");
			out.println("	     if(document.Form1.hid_temp_status.value == \"Y\" ){ "); 
			out.println("				help_update_temp(); }");
			out.println("	     else if(document.Form1.hid_temp_status.value == \"N\" ){ "); 
			out.println("				help_update(); }");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'H_cor'){");
			out.println("				assign_data_corporate(data_vec);");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'H_cor' &&  document.Form1.TXT_CLIENT_CODE.value !='' ){");
			out.println("	     if(document.Form1.hid_temp_status.value == \"Y\" ){ "); 
			out.println("				help_update_temp(); }");
			out.println("	     else if(document.Form1.hid_temp_status.value == \"N\" ){ "); 
			out.println("				help_update(); }");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0 && document.Form1.hid_help_status.value =='H2'){");
			//out.println("          alert('zzz');");
			out.println("      display_income(data_vec); ");
			out.println("     }");
			out.println("		 else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H3'){");
			out.println("      assign_data_emp(data_vec); ");
			//	out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H4'){");
			out.println("      assign_data_bank(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			//added by nuwan de silva on 16-10-2008
			out.println("		 else if(document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='client_count'){");
			out.println("      assign_client_count(data_vec); ");
			out.println("     }");
			
			out.println("		 else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H5'){");
			out.println("      assign_data_credit(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H6'){");
			out.println("      assign_data_nonrel(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_in_ex_fill'){");
			out.println("      display_income_fill(data_vec); ");
			//	out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_tot'){");
			out.println("      display_in_ex_tot(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_tot'){");
			out.println("      display_in_ex_tot(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		 else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_in_ex_fill'){");
			out.println("    assign_help_status('H2'); ");
			out.println("    makeRequest1(); ");
			out.println("     }");
			out.println("		 else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='Hf'){");
			out.println("      assign_data_family(data_vec); ");
			
			
			//out.println("   	 data_vec='';	");
			out.println("     }");
			//	out.println("		 else if(data_vec.length>0  && document.Form1.SCREEN_NAME.value !=\"NEW\" && document.Form1.hid_help_status.value =='H_doc_fill'){");
			//	out.println("      display_applicable_doc_fill(data_vec); ");
			//	out.println("     }");
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value !=\"RACT\" && document.Form1.hid_help_status.value =='H_doc' ){");
			//out.println(" alert('...')");
			out.println("      display_applicable_doc(data_vec); ");
			
			out.println("     }");
			
			//Corporate Part
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_dir'){");
			out.println("      assign_data_director(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("	  else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_ba'){");
			out.println("      assign_data_business(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_sub'){");
			out.println("      assign_data_sub(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_bank' && document.Form1.hid_bank_status.value=='FILL' ){");
			out.println("      assign_data_bank(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_bank' && document.Form1.hid_bank_status.value=='NEW' && document.Form1.elements[m_bank].value!='' ){");
			out.println("      help_button_bank(count); ");
			out.println("   	 data_vec='';	");
			out.println("     }");
			
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_aud'){");
			out.println("      assign_data_auditors(data_vec); ");
			out.println("     }");
			
			
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_credit'){");
			out.println("      assign_data_credit_c(data_vec); ");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length>=0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value =='H_cus'){");
			out.println("      assign_data_cus(data_vec); ");
			//	out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length==0 && document.Form1.hid_help_status.value =='H_inq' && document.Form1.TXT_INQUARY_NO.value!=\"\"){");
			out.println("      help_button_inq(); ");
			out.println("      document.Form1.TXT_INQUARY_NO.focus();");
			//out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_help_status.value =='H_inq' && document.Form1.TXT_INQUARY_NO.value!=\"\"){");
			out.println("      document.Form1.INQUARY_LINK_BUT.disabled=false;");
			out.println(" 		 assign_data_inquiry(data_vec);	");
			//	out.println("   	 data_vec='';	");
			out.println("     }");
			out.println("		 else if(data_vec.length==0 && document.Form1.hid_help_status.value == 'H_bank' && document.Form1.elements[m_bank].value!=\"\" ){");
			out.println("				help_button_bank(count);");
			out.println("			}");
			out.println("		 else if(data_vec.length==0 && document.Form1.hid_help_status.value == 'H_branch' && document.Form1.elements[m_branch].value!=\"\" ){");
			out.println("				help_button_branch(count);");
			out.println("			}");
			out.println("		 else if(data_vec.length==0 && document.Form1.hid_help_status.value == 'H_postal' && document.Form1.TXT_POSTAL_CODE.value!=\"\" ){");
			out.println("				help_button_postal_code();");
			out.println("			}");
			out.println("		 else if(data_vec.length>0 && document.Form1.hid_help_status.value == 'H_postal' && document.Form1.TXT_POSTAL_CODE.value!=\"\" ){");
			out.println("				document.Form1.TXT_POSTAL_CODE.value=data_vec[0];");
			out.println("				document.Form1.TXT_POSTAL_DESC.value=data_vec[1];"); //ADDED BY NUWAN DE SILVA
			out.println("			}");
			out.println("		 else if( data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'Client_Code' && document.Form1.TXT_CLIENT_CODE.value!=\"\" ){");
			out.println("				fill_rest_get_vector(data_vec,'C');"); //added by nuwan de silva 07-08-07
			out.println("			}");
			out.println("		 else if( data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value == 'Client_Code' && document.Form1.TXT_CLIENT_CODE.value!=\"\" ){");
			out.println("	     if(document.Form1.hid_temp_status.value == \"Y\" ){ "); 
			out.println("				help_client_code_validate_temp(); }");
			out.println("	     else if(document.Form1.hid_temp_status.value == \"N\" ){ "); 
			out.println("				help_client_code_validate(); }");
			//out.println("				help_client_code_validate();"); //added by nuwan de silva 07-08-07
			out.println("			}");
			
			//Added by KAnchana
			out.println("		else if(data_vec.length>0 && document.Form1.hid_help_status.value =='H_active_cont' && document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("				active_contract_count(data_vec,document.Form1.hid_client_type.value);"); //function is in the validate_v2 file
			out.println("   }");    
			//End by Kanchana 
			
			out.println("}");
			
			out.println("function assign_data_credit(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) {");
			out.println("  array_type[i] = 'HOUSE LOAN';");
			out.println("  array_institute[i] = '';");
			out.println("  array_contact_person[i] = '';");
			out.println("  array_contract_no[i] = '';");
			out.println("  array_security[i] = '';");
			out.println("  array_app_amount[i] = '';");
			out.println("  array_bal_amount[i] = '';");
			out.println("  array_months[i] = '';");
			out.println("  arr_size_credit=i+1;");
			out.println("  write_data_credit(i+1); ");
			out.println("   document.Form1.hid_count_credit.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_credit(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H6'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_type[i] = 'HOUSE LOAN';");
			out.println(" } else {");
			out.println("  array_type[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_institute[i] = '';");
			out.println(" } else {");
			out.println("  array_institute[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_contact_person[i] = '';");
			out.println(" } else {");
			out.println("  array_contact_person[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_contract_no[i] = '';");
			out.println(" } else {");
			out.println("  array_contract_no[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_security[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_security[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]=='' || data_vec[vec_pos+5]=='0'){ ");
			out.println("  array_app_amount[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_app_amount[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]=='' || data_vec[vec_pos+6]=='0.00'){ ");
			out.println("  array_bal_amount[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_bal_amount[i] = data_vec[vec_pos+6];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+7]=='null' || data_vec[vec_pos+7]=='' || data_vec[vec_pos+7]=='0'){ ");
			out.println("  array_months[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_months[i] = data_vec[vec_pos+7];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+8;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_credit=i;");
			out.println(" write_data_credit(i); ");
			out.println("   document.Form1.hid_count_credit.value=i; ");
			out.println("   if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_credit(i);");
			out.println("   }");
			out.println("   assign_help_status('H6'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}");
			//out.println("}");
			
			out.println("function assign_data_credit_c(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) {");
			out.println("  array_institute_c[i] = '';");
			out.println("  array_contact_person_c[i] = '';");
			out.println("  array_type_c[i] = 'HOUSE LOAN';");
			out.println("  array_equip_c[i] = '';");
			out.println("  array_app_amount_c[i] = '';");
			out.println("  array_rental[i] = '';");
			out.println("  array_months_c[i] = '';");
			out.println("  array_bal_amount_c[i] = '';");
			out.println("  arr_size_credit_c=i+1;");
			out.println("  write_data_credit_c(i+1); ");
			out.println("   document.Form1.hid_count_credit_c.value=i+1; ");
			out.println("   if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_credit_c(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H_cus'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_institute_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_institute_c[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_contact_person_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_contact_person_c[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_type_c[i] = 'HOUSE LOAN';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_type_c[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_equip_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_equip_c[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]=='' || data_vec[vec_pos+4]=='0'){ ");
			out.println("  array_app_amount_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_app_amount_c[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]=='' ||  data_vec[vec_pos+5]=='0'){ ");
			out.println("  array_rental[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_rental[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]=='' || data_vec[vec_pos+6]=='0' ){ ");
			out.println("  array_months_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_months_c[i] = data_vec[vec_pos+6];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+7]=='null' || data_vec[vec_pos+7]=='' || data_vec[vec_pos+7]=='0.00'){ ");
			out.println("  array_bal_amount_c[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_bal_amount_c[i] = data_vec[vec_pos+7];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+8;	 ");
			out.println("  i=i+1; ");
			//out.println(" ");
			out.println(" }");
			out.println(" arr_size_credit_c=i;");
			out.println(" write_data_credit_c(i); ");
			out.println("   document.Form1.hid_count_credit_c.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_credit_c(i);");
			out.println("   }");
			out.println("   assign_help_status('H_cus'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" } }");
			
			out.println("function assign_data_cus(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) {");
			out.println("  array_name_cus[i] = '';");
			out.println("  array_type_cus[i] = '';");
			out.println("  array_add_cus[i] = '';");
			out.println("  array_relation_cus[i] = '';");
			out.println("  array_contact_person_cus[i] = '';");
			out.println("  array_telno_cus[i] = '';");
			out.println("  arr_size_customer=i+1;");
			out.println("  write_data_customer(i+1); ");
			out.println("   document.Form1.hid_count_cus.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_cus(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H6'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_name_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_name_cus[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_type_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_type_cus[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_add_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_add_cus[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_relation_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_relation_cus[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_contact_person_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_contact_person_cus[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_telno_cus[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_telno_cus[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+6;	 ");
			out.println("  i=i+1; ");
			//	out.println(" ");
			out.println(" }");
			out.println(" arr_size_customer=i;");
			out.println(" write_data_customer(i); ");
			out.println("   assign_help_status('H6'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("   document.Form1.hid_count_cus.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_cus(i);");
			out.println("   } } }");
			
			
			
			out.println("function assign_data_nonrel(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) { ");
			out.println("  array_name[i] = '';");
			out.println("  array_relationship[i] = '';");
			out.println("  array_period[i] = '';");
			out.println("  array_design[i] = '';");
			out.println("  array_telno_home[i] = '';");
			out.println("  array_telno_office[i] = '';");
			out.println("  array_mobileno[i] = '';");
			out.println("  arr_size_nonrelated=i+1;");
			out.println("  if(document.Form1.hid_client_type.value=='I'){"); 
			out.println("   write_data_nonrelated(i+1,'I'); ");	
			out.println("   document.Form1.hid_count_nonrel.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_non(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H_in_ex_fill'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("  }");
			out.println("  else {");
			out.println("   write_data_nonrelated(i+1,'C'); ");	
			out.println("   document.Form1.hid_count_nonrel.value=i+1; ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_non(i+1);");
			out.println("   }");
			//To fill Applicable Documents
			out.println(" 	makeRequest5(document.Form1.TXT_CLIENT_CODE); ");
			out.println("  } }");
			out.println(" else { ");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_name[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_name[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_relationship[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_relationship[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_period[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_period[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_design[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_design[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_telno_home[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_telno_home[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_telno_office[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_telno_office[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]==''){ ");
			out.println("  array_mobileno[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_mobileno[i] = data_vec[vec_pos+6];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+7;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_nonrelated=i;");
			out.println(" if(document.Form1.hid_client_type.value=='I'){"); 
			out.println("   document.Form1.hid_count_nonrel.value=i; ");
			out.println("   write_data_nonrelated(i,'I'); ");	
			out.println("   if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_non(i);");
			out.println("   }");
			out.println("   assign_help_status('H_in_ex_fill'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {"); 
			out.println("   document.Form1.hid_count_nonrel.value=i; ");
			out.println("   write_data_nonrelated(i,'C'); ");
			out.println("   if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_non(i);");
			out.println("   }");
			//To fill Applicable Documents
			out.println(" 	makeRequest5(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}");
			
			out.println("}");
			//out.println(" }");
			//out.println("}");
			
			out.println("function assign_data_emp(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0){ ");
			//out.println(" alert('m_size'+m_size);");
			//out.println(" e_mode_emp.innerHTML=\"\";");
			out.println("  array_organization[i] = '';");
			out.println("  array_telno[i] = '';");
			out.println("  array_from_date_dd[i] = '';");
			out.println("  array_from_date_mm[i] = '';");
			out.println("  array_from_date_yy[i] = '';");
			out.println("  array_to_date_dd[i] = '';");
			out.println("  array_to_date_mm[i] = '';");
			out.println("  array_to_date_yy[i] = '';");
			out.println("  array_designation[i] = '';");
			out.println("  arr_size=i+1;");
			out.println("  write_data(i+1); ");	
			out.println("   document.Form1.hid_count_emp.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_emp(i+1);");
			out.println("  }");
			out.println("   assign_help_status('H4'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			
			out.println(" else{ ");
			out.println(" while(vec_pos<m_size){ "); 
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_organization[i] = '';");
			out.println(" }");
			out.println(" else { "); 
			out.println("  array_organization[i] = data_vec[vec_pos];");
			out.println(" }");
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_telno[i] = '';");
			out.println(" }");
			out.println(" else { "); 
			out.println("  array_telno[i] = data_vec[vec_pos+1];");
			out.println(" }");
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_from_date_dd[i]='';");
			out.println("  array_from_date_mm[i]='';");
			out.println("  array_from_date_yy[i]='';");
			out.println(" }");
			out.println(" else { "); 
			out.println("  assign_date_values_from(data_vec[vec_pos+2],i);");
			out.println(" }");
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_to_date_dd[i]='';");
			out.println("  array_to_date_mm[i]='';");
			out.println("  array_to_date_yy[i]='';");
			out.println(" }");
			out.println(" else { "); 
			out.println("  assign_date_values_to(data_vec[vec_pos+3],i);");
			out.println(" }");
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_designation[i] = '';");
			out.println(" }");
			out.println(" else { "); 
			out.println("  array_designation[i] = data_vec[vec_pos+4];");
			out.println(" }");
			out.println("  vec_pos=vec_pos+5;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size=i;");
			out.println(" write_data(i); ");	
			out.println(" document.Form1.hid_count_emp.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println(" disable_rows_emp(i);");
			out.println(" }");
			out.println("   assign_help_status('H4'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("}}");
			//	out.println("}");
			
			
			/*out.println("function assign_date_values_from(dval,no){");
            out.println("array_from_date_dd[no]=dval.substring(0,2)");
            out.println("array_from_date_mm[no]=dval.substring(3,5)");
            out.println("array_from_date_yy[no]=dval.substring(6,10)");
            out.println("}");
            
            out.println("function assign_date_values_to(dval,no){");
            out.println("array_to_date_dd[no]=dval.substring(0,2)");
            out.println("array_to_date_mm[no]=dval.substring(3,5)");
            out.println("array_to_date_yy[no]=dval.substring(6,10)");
            out.println("}");
            */
			
			out.println("function assign_data_bank(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0){ ");
			out.println("  array_banker[i] = '';");
			out.println("  array_banker_name[i] = '';");
			out.println("  array_branch[i] = '';");
			out.println("  array_branch_name[i] = '';");
			out.println("  array_acc_no[i] = '';");
			out.println("  array_reference[i] = '';");
			out.println("  array_telno_bank[i] = '';");
			out.println("  array_fax_bank[i] = '';");
			out.println("  array_relationship_b[i] = '';");
			out.println("  arr_size_bank=i+1;");
			out.println(" if(document.Form1.hid_client_type.value=='I'){");
			out.println("   write_data_bank(i+1,'I'); ");	
			out.println("   document.Form1.hid_count_bank.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_bank(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H5'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			out.println("   write_data_bank(i+1,'C'); ");	
			out.println("   document.Form1.hid_count_bank.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_bank(i+1);");
			out.println("   }");
			//out.println("   assign_help_status('H_credit'); ");
			//out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			
			out.println("   assign_help_status('H_aud'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}");
			//out.println(" }");
			out.println(" else{ ");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_banker[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_banker[i] = data_vec[vec_pos];");
			out.println(" }");
			//---modified by : delanjali-------------------------------------------------
			//---date				: 2007-06-21-------------------------------------------------
			out.println(" if(data_vec[vec_pos+7]=='null' || data_vec[vec_pos+7]==''){ ");
			out.println("  array_banker_name[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_banker_name[i] = data_vec[vec_pos+7];");
			out.println(" }");
			//----------------------------------------------------------------------------
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_branch[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_branch[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			//---modified by : delanjali-------------------------------------------------
			//---date				: 2007-06-21-------------------------------------------------
			
			out.println(" if(data_vec[vec_pos+8]=='null' || data_vec[vec_pos+8]==''){ ");
			out.println("  array_branch_name[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_branch_name[i] = data_vec[vec_pos+8];");
			out.println(" }");
			
			//----------------------------------------------------------------------------
			
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_acc_no[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_acc_no[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_reference[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_reference[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_telno_bank[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_telno_bank[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_fax_bank[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_fax_bank[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]==''){ ");
			out.println("  array_relationship_b[i] = '';");
			out.println(" }");
			out.println(" else{ ");
			out.println("  array_relationship_b[i] = data_vec[vec_pos+6];");
			out.println(" }");
			
			//	out.println("  vec_pos=vec_pos+7;	 ");
			//--modified by : delanjali----------------------------------------------------------
			//--date				: 2007-06-21---------------------------------------------------------
			out.println("  vec_pos=vec_pos+9;	 ");
			
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_bank=i;");
			out.println(" if(document.Form1.hid_client_type.value=='I'){");
			out.println(" write_data_bank(i,'I'); ");		
			out.println("   document.Form1.hid_count_bank.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_bank(i);");
			out.println("   }");
			out.println("   assign_help_status('H5'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			out.println(" write_data_bank(i,'C'); ");		
			out.println("   document.Form1.hid_count_bank.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_bank(i);");
			out.println("   }");
			//out.println("   assign_help_status('H_credit'); ");
			//out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("   assign_help_status('H_aud'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}}");
			//out.println(" }");
			//out.println("}");
			
			
			out.println("function assign_data_family(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) { ");
			out.println("  array_member_f[i] = 'MOTHER';");
			out.println("  array_name_f[i] = '';");
			out.println("  array_address_f[i] = '';");
			out.println("  array_age_f[i] = '';");
			out.println("  array_telno_f[i] = '';");
			out.println("  array_moble_f[i] = '';");
			out.println("  arr_size_family=i+1;");
			out.println("  write_data_family(i+1); ");	
			out.println("   document.Form1.hid_count_fam.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_fam(i+1);");
			out.println("   }");			
			//To fill Applicable Documents
			out.println(" 	makeRequest5(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else { ");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_member_f[i] = 'MOTHER';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_member_f[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_name_f[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_name_f[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_address_f[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_address_f[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_age_f[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_age_f[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_telno_f[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_telno_f[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_moble_f[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_moble_f[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+6;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_family=i;");
			out.println(" write_data_family(i); ");		
			out.println("   document.Form1.hid_count_fam.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_fam(i);");
			out.println("   }");
			//To fill Applicable Documents
			out.println(" 	makeRequest5(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println("chk_disa();");
			out.println("}");
			
			
			
			out.println("function assign_data_director(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) { ");
			out.println("  array_name_dir[i] = '';");
			out.println("  array_add_dir[i] = '';");//added by nuwan de silva on 07-09-07
			out.println("  array_nic_no_dir[i] = '';");
			out.println("  array_stake[i] = '';");
			out.println("  array_no_shares[i] = '';");
			out.println("  array_value[i] = '';");
			out.println("  array_position[i] = '';");
			out.println("  arr_size_company=i+1;");
			out.println("  write_data_company_dir(i+1); ");	
			out.println("   document.Form1.hid_count_com_dir.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_company(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H_ba'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else { ");
			//out.println(" alert('m_size'+m_size);");
			
			
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_name_dir[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_name_dir[i] = data_vec[vec_pos];");
			out.println(" }");
			
			//added by nuwan de silva on 07-09-07 for ref no :851
			out.println(" if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]==''){ ");
			out.println("  array_add_dir[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_add_dir[i] = data_vec[vec_pos+6];");
			out.println(" }");
			
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_nic_no_dir[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_nic_no_dir[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_stake[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_stake[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_no_shares[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_no_shares[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_value[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_value[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_position[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_position[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+7;	 ");
			//out.println("  vec_pos=vec_pos+6;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_company=i;");
			out.println(" write_data_company_dir(i); ");		
			out.println("   document.Form1.hid_count_com_dir.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_company(i);");
			out.println("   }");
			out.println("   assign_help_status('H_ba'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}");
			//out.println("}");
			
			out.println("function assign_data_business(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) { ");
			out.println("  array_cat[i] = '';");
			out.println("  array_activity[i] = '';");
			out.println("  arr_size_ba=i+1;");
			out.println("  write_data_ba(i+1); ");		
			out.println("   document.Form1.hid_count_ba.value=i+1; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_ba(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H_sub'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else { ");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_cat[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_cat[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]=='' || data_vec[vec_pos+1]=='-'){ "); //modified by nuwan de silva on 07-09-07
			out.println("  array_activity[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_activity[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+2;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_ba=i;");
			out.println(" write_data_ba(i); ");		
			out.println("   document.Form1.hid_count_ba.value=i; ");
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_ba(i);");
			out.println("   }");
			out.println("   assign_help_status('H_sub'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("} }");
			//	out.println("}");
			
			
			out.println("function assign_data_sub(data_vec){ ");
			out.println(" vec_pos=0; ");
			out.println(" i=0; ");
			out.println(" m_size=0; ");
			out.println(" m_size=data_vec.length; ");
			out.println(" if(m_size==0) { ");
			out.println("  array_name_sub[i] = '';");
			out.println("  array_stake_sub[i] = '';");
			out.println("  array_value_sub[i] = '';");
			out.println("  array_telno_sub[i] = '';");
			out.println("  array_officer_sub[i] = '';");
			out.println("  array_ba_sub[i] = '';");
			out.println("  arr_size_subsidiaries=i+1;");
			out.println("  write_data_subsidiaries(i+1); ");	
			out.println("   document.Form1.hid_count_sub.value=i+1; ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_sub(i+1);");
			out.println("   }");
			out.println("   assign_help_status('H_bank'); ");
			out.println("document.Form1.hid_bank_status.value='FILL';");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println(" else {");
			//out.println(" alert('m_size'+m_size);");
			out.println(" while(vec_pos<m_size){ "); 
			
			out.println(" if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ ");
			out.println("  array_name_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_name_sub[i] = data_vec[vec_pos];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ ");
			out.println("  array_stake_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_stake_sub[i] = data_vec[vec_pos+1];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ ");
			out.println("  array_value_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_value_sub[i] = data_vec[vec_pos+2];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ ");
			out.println("  array_telno_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_telno_sub[i] = data_vec[vec_pos+3];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ ");
			out.println("  array_officer_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_officer_sub[i] = data_vec[vec_pos+4];");
			out.println(" }");
			
			out.println(" if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ ");
			out.println("  array_ba_sub[i] = '';");
			out.println(" }");
			out.println(" else {");
			out.println("  array_ba_sub[i] = data_vec[vec_pos+5];");
			out.println(" }");
			
			out.println("  vec_pos=vec_pos+6;	 ");
			out.println("  i=i+1; ");
			out.println(" ");
			out.println(" }");
			out.println(" arr_size_subsidiaries=i;");
			out.println(" write_data_subsidiaries(i); ");	
			out.println("   document.Form1.hid_count_sub.value=i; ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_sub(i);");
			out.println("   }");
			out.println("   assign_help_status('H_bank'); ");
			out.println("document.Form1.hid_bank_status.value='FILL';");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }}");
			//out.println("}");
			
			out.println(" var sum_in = 0;  ");
			out.println(" var sum_ex = 0;  ");
			
			out.println("function cal_tot() {");
			out.println(" sum_in = 0;  ");
			out.println(" sum_ex = 0;  ");
			out.println("  tot=document.Form1.hid_tot_in_ex.value;   ");
			out.println(" for(var i=1;i<=tot-1;i++){ ");
			out.println(" m_income =\"TXT_INCOME\"+i");
			//out.println(" alert('type : '+document.Form1.elements[\"hid_type\"+i].value); ");
			out.println(" if(document.Form1.elements[\"hid_type\"+i].value == 'IN') { ");
			out.println(" if(document.Form1.elements[m_income].value != \"\") ");
			out.println(" sum_in = sum_in + parseFloat(unformat_number(document.Form1.elements[m_income])); ");
			out.println(" }");
			out.println(" else if(document.Form1.elements[\"hid_type\"+i].value == 'EX') { ");
			out.println(" if(document.Form1.elements[m_income].value != \"\") ");
			out.println(" sum_ex = sum_ex + parseFloat(unformat_number(document.Form1.elements[m_income])); ");
			out.println(" }}");
			//out.println(" }");
			out.println(" document.Form1.TXT_TOT_EXPENSE.value = sum_ex;   ");
			out.println(" format_number(document.Form1.TXT_TOT_EXPENSE,20)");
			out.println(" document.Form1.TXT_TOT_INCOME.value = sum_in;   ");
			out.println(" format_number(document.Form1.TXT_TOT_INCOME,20)");
			out.println(" document.Form1.TXT_NET_INCOME.value =sum_in-sum_ex;");
			out.println(" format_number(document.Form1.TXT_NET_INCOME,20)");
			out.println("}");
			
			out.println("function change_val_received_doc(row_no){")	;
			out.println("m_chk_received_doc=\"CHK_RECEIVED_DOC\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_received_doc].checked==true){");
			out.println("document.Form1.elements[m_chk_received_doc].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_received_doc].checked==false){");
			out.println("document.Form1.elements[m_chk_received_doc].value='off'");
			out.println("}}");	
			//out.println("}");	
			
			
			/*out.println("function header_doc_app(){");
            out.println(" 	if(document.Form1.hid_client_type.value == 'I'){ ");
            out.println("		e_app_doc_header.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr>'+");
            out.println("		'<td width=\"80%\" ><B><u> F.Document Check List  </u></B></td>'+"); 
            out.println("		'</tr>'+");
        out.println("		'</TR></table>';");
            out.println("		e_app_doc.innerHTML='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
            out.println("		'<TR><TD width=\"5%\"><B> Sequence </B></TD><TD width=\"20%\" ><B>Description</B></TD><TD width=\"20%\" ><B>Remark</B></TD><TD width=\"20%\"><B>Received</B></TD></TR>' +");
            out.println(" 	'</table>'");
            out.println("		}");
            out.println(" 	else { ");
            /*out.println("		e_app_doc_header_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr></tr>'+");
            out.println("		'<tr>'+");
            out.println("		'<td width=\"80%\" ><B><u> E.Document Check List </u></B></td>'+"); 
            out.println("		'</tr>'+");
        out.println("		'</TR></table>';");
            
            /*out.println("		e_app_doc_c.innerHTML='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
            out.println("		'<TR><TD width=\"5%\"><B> Sequence </B></TD><TD width=\"20%\" ><B>Description</B></TD><TD width=\"20%\" ><B>Remark</B></TD><TD width=\"20%\"><B>Received</B></TD></TR>' +");
            out.println(" 	'</table>'");
            */
			
			//	out.println("		}");
			// 	out.println("}");
			
			
			
			
			
			out.println("function disable_rows_app_doc(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			out.println("  m_remark=\"TXT_REMARK_DOC\"+i");
			out.println("  m_received=\"CHK_RECEIVED_DOC\"+i");
			out.println("   document.Form1.elements[m_remark].disabled=true;");                           
			out.println("   document.Form1.elements[m_received].disabled=true;");	
			out.println("}}	");
			//out.println("} ");	
			
			
			out.println("function display_applicable_doc(data_vec){ ");
			//out.println(" alert(' data vec @@ '+data_vec); ");
			//	out.println(" var i=0; "); 
			//	out.println(" var j=0; "); 
			//	out.println(" var line_doc=0; "); 
			//	out.println(" e_app_doc.innerHTML='';");
			//out.println(" e_app_doc_c.innerHTML='';");
			//out.println("  header_doc_app(); ");
			/*	out.println("  while(i<data_vec.length ) {  ");			
                out.println(" m_rownum = '<td width=\"5%\">'+data_vec[i]+'<input type=hidden name=hid_doc_code'+line_doc+' value=\"'+data_vec[i+1]+'\" ></td>'");
                out.println(" m_desc = '<td width=\"20%\">'+data_vec[i+2].replace('*','&')+'</td>'");
                out.println(" m_remark = '<TD WIDTH=\"20%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_DOC'+line_doc+' VALUE=\"\" maxlength=\"20\" size=\"20\"></td>';");		
                out.println(" m_received = '<TD WIDTH=\"20%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RECEIVED_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_received_doc('+line_doc+')\"></td>';");			
                out.println(" m_write_data = '<TR>'+m_rownum+m_desc+m_remark+m_received+'</TR>' ");
                //out.println(" alert(' doc Code @@ '+m_rownum); ");
                out.println(" 	if(document.Form1.hid_client_type.value == 'I'){ ");
                out.println(" 		e_app_doc.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
                out.println(" 		m_write_data ");
                out.println(" 		'</table>'");
                out.println("	 	}");
                out.println(" 	else{ ");
                //out.println(" 		e_app_doc_c.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
            //	out.println(" 		m_write_data ");
            //	out.println(" 		'</table>'");
                out.println("	 	}");
                out.println(" i=i+3; ");
                out.println(" line_doc=line_doc+1; ");
                out.println("	 }");
                out.println(" document.Form1.hid_app_doc_lineno.value=line_doc ; ");
                out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
                out.println("   disable_rows_app_doc(document.Form1.hid_app_doc_lineno.value); ");
                out.println("  }");
                //out.println(" j=j+3; ");
                */
			
			out.println("load_inq_details()");
			out.println(" fill_client();");
			out.println("}");
			
			out.println("function display_applicable_doc_fill(data_vec){ ");
			//out.println(" alert(' data vec @@ '+data_vec); ");
			out.println(" var i=0; "); 
			out.println(" var j=0; "); 
			out.println(" var line_doc=0; "); 
			out.println(" e_app_doc.innerHTML='';");
			//	out.println(" e_app_doc_c.innerHTML='';");
			out.println("  header_doc_app(); ");
			out.println("  while(i<data_vec.length ) {  ");			
			out.println(" m_rownum = '<td width=\"5%\">'+data_vec[i]+'<input type=hidden name=hid_doc_code'+line_doc+' value=\"'+data_vec[i+1]+'\" ></td>'");
			out.println(" m_desc = '<td width=\"20%\">'+data_vec[i+2].replace('*','&')+'</td>'");
			out.println(" if(data_vec[i+4] == '' || data_vec[i+4] == 'null' ) { ");
			out.println(" m_remark = '<TD WIDTH=\"20%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_DOC'+line_doc+' VALUE=\"\" maxlength=\"20\" size=\"20\"></td>';");		
			out.println("	 	}");
			out.println(" else { ");
			out.println(" m_remark = '<TD WIDTH=\"20%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_DOC'+line_doc+' VALUE=\"'+data_vec[i+4]+'\" maxlength=\"20\" size=\"20\"></td>';");		
			out.println("	 	}");
			out.println(" if(data_vec[i+3] == 'Y') { ");
			out.println(" m_received = '<TD WIDTH=\"20%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RECEIVED_DOC'+line_doc+' VALUE=\"on\" onclick=\"change_val_received_doc('+line_doc+')\" checked ></td>';");			
			out.println("	 	}");
			out.println(" else { ");
			out.println(" m_received = '<TD WIDTH=\"20%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RECEIVED_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_received_doc('+line_doc+')\" ></td>';");			
			out.println("	 	}");
			out.println(" m_write_data = '<TR>'+m_rownum+m_desc+m_remark+m_received+'</TR>' ");
			//out.println(" alert(' doc Code @@ '+m_rownum); ");
			out.println(" 	if(document.Form1.hid_client_type.value == 'I'){ ");
			out.println(" 		e_app_doc.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" 		m_write_data ");
			out.println(" 		'</table>'");
			out.println("	 	}");
			out.println(" 	else{ ");
			//	out.println(" 		e_app_doc_c.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			//	out.println(" 		m_write_data ");
			//	out.println(" 		'</table>'");
			out.println("	 	}");
			out.println(" ");  		
			out.println(" i=i+5; ");
			out.println(" line_doc=line_doc+1; ");
			out.println("	 }");
			out.println(" document.Form1.hid_app_doc_lineno.value=line_doc; ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_app_doc(document.Form1.hid_app_doc_lineno.value); ");
			out.println("  }}");
			//out.println("    assign_data_values(); ");
			//out.println(" j=j+3; ");
			//out.println("}");
			
			
			//To display Income Expenses According to the table
			out.println("function display_income(data_vec){ ");
			out.println(" var i=0; "); 
			out.println(" var j=0; "); 
			out.println(" var x=1; "); 
			out.println(" var income_tot=0; "); 
			out.println("e_data_income.innerHTML='';");
			out.println("e_data_expense.innerHTML='';");
			// out.println(" alert('bbb'); "); test mcp
			out.println("  while(j<data_vec.length ) {  ");
			out.println(" income_tot = income_tot + 1; ");
			out.println(" if(data_vec[i+1] == 'IN'){ ");
			out.println(" e_data_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\">'+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"0\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\" ></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println(" else if(data_vec[i+1] == 'EX'){ ");
			//out.println(" document.Form1.hid_iecode.value= data_vec[i+2]; ");
			out.println(" e_data_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" > '+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"0\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\" ></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println(" i=i+3; ");
			out.println(" j=j+3; ");
			out.println(" x=x+1; ");
			out.println("  }");		
			out.println("    document.Form1.hid_tot_in_ex.value=x;   ");
			out.println("    document.Form1.hid_count_in_ex.value=x;   ");
			//out.println(" alert('bbb'); "); //test mcp
			out.println(" if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			//out.println("    alert('x value '+x); ");
			out.println("    disable_rows_in_ex(x); ");
			out.println("  }");
			//out.println(" alert('x value '+x); ");
			out.println("   arr_size_income = x-1; ");
			//out.println(" alert('arr_size_income @@ '+arr_size_income); ");
			//out.println(" alert('b_client_status'+b_client_status); ");
			out.println("if(b_client_status==0){");//added by nuwan de silva 08-08-07
			out.println("   assign_help_status('Hf'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			//out.println(" alert('bbb4'); "); //test mcp
			
			
			//out.println(" setTimeout(function(){makeRequest4();}, 1500); "); // added by udara 12-08-2014
			out.println(" 	makeRequest4(); "); // commented by udara 12-08-2014 // released by udara 25-08-2014
			
			
			out.println(" } ");
			out.println("else {");//added by nuwan de silva 08-08-07
			//out.println("if(b_new_data==0){");	
			out.println("view_client_status();");
			//out.println("}");
			out.println("}");
			
			//out.println(" alert('arr size incom '+arr_size_income); ");
			out.println("}");
			
			
			out.println("function display_in_ex_tot(data_vec){ ");
			out.println(" var i=0; "); 
			out.println(" var j=0; "); 
			out.println("  while(j<data_vec.length ) {  ");
			out.println("   if(data_vec[i] == 'IN'){ ");
			out.println("   if(data_vec[i+1] == '' || data_vec[i+1] == 'null' ){ ");
			out.println("    document.Form1.TXT_TOT_INCOME.value = '';   ");
			out.println("   }");
			out.println("   else {");
			out.println("    document.Form1.TXT_TOT_INCOME.value = data_vec[i+1];   ");
			out.println("    check_number_decimal(document.Form1.TXT_TOT_INCOME,21)");
			out.println("   }}");
			//out.println("   }");
			out.println("   else if(data_vec[i] == 'EX'){ ");
			out.println("   if(data_vec[i+1] == '' || data_vec[i+1] == 'null' ){ ");
			out.println("    document.Form1.TXT_TOT_EXPENSE.value = '';   ");
			out.println("   }");
			out.println("   else {");
			out.println("    document.Form1.TXT_TOT_EXPENSE.value = data_vec[i+1];   ");
			out.println("    check_number_decimal(document.Form1.TXT_TOT_EXPENSE,21)");
			out.println("   }}");
			//out.println("   }");
			out.println(" i=i+2; ");
			out.println(" j=j+2; ");
			out.println("  }");
			
			out.println("    document.Form1.TXT_NET_INCOME.value = (parseInt(unformat_number(document.Form1.TXT_TOT_INCOME))-parseInt(unformat_number(document.Form1.TXT_TOT_EXPENSE)));   ");
			//out.println("      alert('ddd'+parseInt(unformat_number(document.Form1.TXT_TOT_INCOME))-parseInt(unformat_number(document.Form1.TXT_TOT_EXPENSE)));");
			out.println("    check_number_decimal(document.Form1.TXT_NET_INCOME,21)");
			//out.println("      alert('ssss');");
			out.println("   assign_help_status('Hf'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("  }");
			
			//To fill Amounts according to the client code
			out.println("function display_income_fill(data_vec){ ");
			out.println(" var i=0; "); 
			out.println(" var j=0; "); 
			out.println(" var x=1; "); 
			out.println(" income_tot_fill=0; "); 
			out.println("e_data_income.innerHTML='';");
			out.println("e_data_expense.innerHTML='';");
			out.println("  while(j<data_vec.length ) {  ");
			out.println(" if(data_vec[i+1] == 'IN'){ ");
			out.println(" if(data_vec[i+3] == 'null' || data_vec[i+3] == ''){ ");
			out.println(" e_data_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\">'+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\" ></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println("  else {");
			out.println("  e_data_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("  '<tr >'+");
			out.println("  '<td width=\"30%\">'+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println("  '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"'+data_vec[i+3]+'\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\" ></td>'+"); 
			out.println("  '</tr ></table>'");
			out.println("  }");
			out.println("  }");
			out.println(" else if(data_vec[i+1] == 'EX'){ ");
			out.println(" if(data_vec[i+3] == 'null' || data_vec[i+3] == ''){ ");
			out.println(" e_data_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" > '+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\" ></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }");
			out.println("  else {");
			out.println(" e_data_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" > '+data_vec[i]+'<input type=hidden name=hid_iecode'+x+' value=\"'+data_vec[i+2]+'\" ><input type=hidden name=hid_type'+x+' value=\"'+data_vec[i+1]+'\" ></td>'+");
			out.println(" '<td width=\"40%\"><input name=TXT_INCOME'+x+' class=\"txt_input\"  value=\"'+data_vec[i+3]+'\" maxlength=\"20\" size=\"10\" onblur=\"check_number_decimal(this,20),cal_tot()\" STYLE=\"{text-align:right;}\"  ></td>'+"); 
			out.println(" '</tr ></table>'");
			out.println("  }}");
			//out.println("  }");
			out.println(" i=i+4; ");
			out.println(" x=x+1; ");
			out.println(" j=j+4; ");
			out.println(" income_tot_fill = x; ");
			out.println("  }");
			out.println("   document.Form1.hid_tot_in_ex.value=x;   ");
			out.println("   document.Form1.hid_count_in_ex.value = x;   ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){"); 
			out.println("   disable_rows_in_ex(x); ");
			out.println("  }");
			out.println("   arr_size_income = x-1; ");
			out.println("   assign_help_status('H_tot'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("}");
			
			
			out.println(" function disable_rows_in_ex(count) { ");
			//out.println(" alert(' count in ex '+count); ");
			out.println("  for(var i=1;i<count;i++)");
			out.println("  {	");
			out.println("   m_in_ex=\"TXT_INCOME\"+i");
			out.println("   document.Form1.elements[m_in_ex].disabled=true;");                           
			out.println("  }}	");
			//out.println("}");
			
			out.println("function makeRequest2(obj) {");
			out.println("   document.Form1.hid_help_status.value='H_nic';");
			out.println("   if(document.Form1.SCREEN_NAME.value == 'NEW' ) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_nic&data_val=\"+obj.value;");
			out.println("		load_interface(m_url,'XML'); ");
			out.println("		}");
			out.println("   else if(document.Form1.SCREEN_NAME.value == 'EDIT' ) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_nic_edit&data_val=\"+obj.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			//out.println("		window.open(m_url);");
			out.println("		load_interface(m_url,'XML'); ");
			out.println("		}}");
			//out.println("}");
			
			//------------------------
			out.println("function makeRequest6(obj) {");
			out.println("if(document.Form1.TXT_BUS_SECT_MAIN.value!=\"\"){");
			out.println("help_button_bus_sec();");
			out.println("}}");
			//out.println("}");
			
			out.println("function makeRequest10(obj) {");
			out.println("if(document.Form1.TXT_BUS_SECT.value!=\"\"){");
			out.println("help_button_sub_sec();");
			out.println("}}");
			//out.println("}");
			//------------------------
			
			
			
			
			out.println("function makeRequest3(obj) {");
			out.println("if(document.Form1.TXT_CITY_CODE.value==\"\"){");
			out.println("document.Form1.TXT_CITY_DESC.value=\"\"");
			out.println("}");
			
			out.println("document.Form1.hid_help_status.value='H_city';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city_r&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			
			//To get the Applicable Documents
			out.println("function makeRequest4() {");
			//out.println("alert('makeRequest4'+b_client_status)");
			//out.println("if(b_client_status==0){");//added by nuwan de silva 28-09-07
			out.println("document.Form1.hid_help_status.value='H_doc';");
			//out.println("if(document.Form1.hid_help_status.value == 'Hf' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_app_client_doc&entity_type=\"+document.Form1.hid_entity_type.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			//out.println("}");
			out.println("}");
			
			
			
			
			//To get the postal Codes
			out.println("function makeRequest_postal(obj) {");
			out.println("if(document.Form1.TXT_POSTAL_CODE.value==\"\"){");
			out.println("document.Form1.TXT_POSTAL_DESC.value=\"\"");
			out.println("}");
			
			out.println("document.Form1.hid_help_status.value='H_postal';");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&entity_type=\"+document.Form1.hid_entity_type.value;");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			
			
			
			//To fill Received Documents
			out.println("function makeRequest5(obj) {");
			out.println("document.Form1.hid_help_status.value='H_doc_fill';");
			//out.println(" alert(' legal status '+document.Form1.hid_entity_type.value);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_app_client_doc_fill&client_code=\"+obj.value+\"&entity_type=\"+document.Form1.hid_entity_type.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			out.println("function makeRequest7(obj) {");
			out.println("document.Form1.hid_help_status.value='H_bus_cert_no';");
			//out.println(" alert(' legal status '+document.Form1.hid_entity_type.value);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_bus_cert_no&bus_no=\"+obj.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			out.println("function makeRequest8(obj) {");
			out.println("document.Form1.hid_bank_status.value='NEW';");
			out.println("document.Form1.hid_help_status.value='H_bank';");
			//out.println(" alert(' legal status '+document.Form1.hid_entity_type.value);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_bank&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			out.println("function makeRequest9(obj) {");
			out.println("document.Form1.hid_help_status.value='H_branch';");
			//out.println(" alert(' legal status '+document.Form1.hid_entity_type.value);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_branch&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); ");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			//out.println(" alert('Help status '+document.Form1.hid_help_status.value+'name'+document.Form1.SCREEN_NAME.value);");
			out.println("if(document.Form1.hid_help_status.value == 'H_ind' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_ind&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println(" window.open(m_url);   ");
			out.println("load_interface(m_url,'XML'); }");
			out.println("else");
			//
			out.println("if(document.Form1.hid_help_status.value == 'H_ind' && document.Form1.SCREEN_NAME.value==\"RACT\" ) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_ind&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_cor' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_cor&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			//out.println(" window.open(m_url); ");
			out.println("if(document.Form1.hid_help_status.value == 'H_cor' && document.Form1.SCREEN_NAME.value==\"RACT\" ) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_cor&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H3' ) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_emp&data_val=\"+obj.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//	out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");	
			
			out.println("else");	
			out.println("if(document.Form1.hid_help_status.value == 'H4' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_bank_ind&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H4' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_bank_ind&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//	out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H6' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_nonrel&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H6' && document.Form1.SCREEN_NAME.value ==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_nonrel&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_in_ex'   ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_in_ex&data_val=\"+obj.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_in_ex_fill' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_in_ex_fill&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_in_ex_fill' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_in_ex_fill&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_tot' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_in_ex_tot&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_tot' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_in_ex_tot&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'Hf' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_fam&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'Hf' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_fam&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			//Corporate part
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_dir' ){");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_director&data_val=\"+obj.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_director&data_val=\"+obj.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_director&data_val=\"+obj.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");//comment by nuwan de silva on 27-09-07
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_ba' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_ba&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_ba' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_ba&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_sub' ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_sub&data_val=\"+obj.value+\"&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_bank' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_bank_cor&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_bank' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_bank_cor&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_aud' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_aud&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//	out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_aud' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_aud&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_credit' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit_cor&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//  out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_credit' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit_cor&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			//  out.println("window.open(m_url); ");
			
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_cus' && document.Form1.SCREEN_NAME.value!=\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_cus&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_cus' && document.Form1.SCREEN_NAME.value==\"RACT\"  ){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_cus&data_val=\"+obj.value+\"&ac_status=N&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML'); }");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_inq'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_Application_Process_val_inq_no&data_val=\"+obj.value; ");
			out.println("load_interface(m_url,'XML'); }");
			
			//Added by Kanchana on 2016-08-18
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_active_cont' && document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_app_level_contracts&data_val=\"+obj.value; ");
			out.println("load_interface(m_url,'XML');} ");
			//Endded by Kan
			/*
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H_ind' && document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.hid_app_screen.value==\"APP_R\"  ||document.Form1.hid_app_screen.value==\"APP_N\") {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation_ind&data_val=\"+obj.value+\"&ac_status=Y&tmp_help_status=\"+document.Form1.hid_temp_status.value;");
			out.println("load_interface(m_url,'XML');} ");*/
			
			//out.println("window.open(m_url); ");
			out.println("}");
			
			/*	out.println("function check_number(obj,size){");
                out.println("if(obj.value!='')"); 
                out.println("if(isPosInteger(obj.value)){"); 
                out.println("format_noobject_nodecimal1(obj)"); 
                out.println("}"); 
                out.println("else{");
                out.println("alert('please enter a number');"); 
                out.println("obj.value='0';"); 
                out.println("obj.focus();"); 
                out.println("}"); 
                out.println("}"); 
                
                out.println("function check_number_decimal(obj,size){");
                out.println("if(obj.value!='')"); 
                out.println("if(isnumberok(obj,size)){"); 
                out.println("format_number(obj,size)"); 
                out.println("}"); 
                out.println("else{");
                out.println("alert('please enter a number');"); 
                out.println("obj.value='';"); 
                out.println("obj.focus();"); 
                out.println("}"); 
                out.println("}"); 
                */
			//format_noobject_nodecimal1
			
			
			
			out.println("function makeRequest1(){ ");
			//out.println(" alert('Help status '+document.Form1.hid_help_status.value);");
			out.println("if(document.Form1.hid_help_status.value == 'H2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation1\";");
			//out.println(" window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function header(){");
			out.println("e_mode.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\"  class=\"table\"><TR><TD WIDTH=\"30%\" align=\"left\"> Organisation </TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Telephone No</TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">From date</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">To date</TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Designation</TD>' +");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_row_tot_dependents(){");	
			out.println(" e_row_tot_dep.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"10%\" align=\"right\" > No of Children  </td>'+");
			out.println(" '<td width=\"20%\"><input name=TXT_NO_CHILD class=\"txt_input\" onblur=\"check_number(this,1)\" STYLE=\"{text-align:right;}\"  value=\"0\" maxlength=\"1\" size=\"10\"></td>'+"); 
			out.println(" '<td width=\"20%\" align=\"right\" > Total Dependents  </td>'+");
			out.println(" '<td width=\"*%\"><input name=TXT_TOT_DEP class=\"txt_input\" onblur=\"check_number(this,2)\" STYLE=\"{text-align:right;}\"  value=\"0\" maxlength=\"2\" size=\"10\"></td>'+"); 
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
			
			
			out.println(" e_tot_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" align=\"right\" ><b>Total Income </b> </td>'+");
			out.println(" '<td width=\"40%\" ><input name=TXT_TOT_INCOME class=\"txt_input\"  value=\"\" maxlength=\"10\" size=\"10\" STYLE=\"{text-align:right;}\" disabled  ></td>'+"); 
			out.println(" '</tr ></table>'");
			
			out.println("e_header_expense.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" ><b>Expenses</b></td>'+");
			out.println(" '<td width=\"40%\"><b>Gross</b></td>'+"); 
			out.println(" '</tr >'+");
			out.println("'</table>';");
			
			out.println(" e_tot_expense.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" align=\"right\" ><b><DIV id=\"DIV_TXT_TOT_EXPENSE\"  class=\"div_input\"> Total Expenses </div> </b> </td>'+");
			out.println(" '<td width=\"40%\" ><input name=TXT_TOT_EXPENSE class=\"txt_input\"  value=\"\" maxlength=\"10\" size=\"10\" STYLE=\"{text-align:right;}\" disabled  ></td>'+"); 
			out.println(" '</tr ></table>'");
			
			out.println(" e_net_income.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" '<tr >'+");
			out.println(" '<td width=\"30%\" align=\"right\" ><b><DIV id=\"DIV_TXT_NET_INCOME\"  class=\"div_input\"> Net Income </div> </b> </td>'+");
			out.println(" '<td width=\"40%\" ><input name=TXT_NET_INCOME class=\"txt_input\"  value=\"\" maxlength=\"10\" size=\"10\" STYLE=\"{text-align:right;}\" disabled  ></td>'+"); 
			out.println(" '</tr ></table>'");
			
			out.println("}");	
			
			out.println("function header_ba(){");
			out.println(" lineno_ba=0; ");
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("e_header_ba.innerHTML +='<table border=\"0\" align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 	
			out.println("'<td >Business Sector </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_MAIN\" maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_BUS_SECT_MAIN\" value=\"...\" onClick=\"help_button_bus_sec()\"></td>'+"); 
			out.println("'<td align=\"left\">Business Sector Description &nbsp&nbsp</td>'+"); 
			out.println("'<td align=\"left\"><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_DES_MAIN\" style=\"width:300px;\" maxlength=\"200\" disabled></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 	
			out.println("'<td >Business Sub Sector  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT\" maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_BUS_SUB\" value=\"...\" onClick=\"help_button_sub_sec()\"></td>'+"); 
			out.println("'<td align=\"left\">Business Sub Sector Description &nbsp&nbsp</td>'+"); 
			out.println("'<td align=\"left\"><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_DES\" style=\"width:300px;\" maxlength=\"200\" disabled></td>'+"); 
			
			out.println("'</tr>'+");
			
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("'<TR><TD WIDTH=\"15%\" align=\"left\"> Category </TD>'+");
			
			//			out.println("e_header_ba.innerHTML +='<table border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\"> Category </TD>'+");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Activity</TD>' +");
			out.println("'</TR></table>';");
			
			
			out.println("}");	
			
			out.println("function header_bank(type){");
			out.println(" lineno_bank=0; ");
			out.println(" if(type ==\"I\") { ");
			out.println(" e_header_bank.innerHTML +='<table  border=\"0\" align=\"center\"  width=\"100%\" class=\"table\"><TR><TD WIDTH=\"13%\" align=\"left\">Banker </TD>'+");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Bank Name </TD>'+");
			
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Branch </TD>'+");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Branch Name</TD>'+");
			
			out.println(" '<TD WIDTH=\"12%\" align=\"left\">Account No</TD>' +");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Reference</TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Telephone </TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Fax </TD>' +");
			out.println(" '<TD WIDTH=\"*%\" align=\"left\">Relationship (Mts) </TD>' +");
			
			out.println(" '</TR></table>';");
			out.println(" }");
			out.println(" else {");
			out.println(" e_header_bank_c.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"13%\" align=\"left\">Banker </TD>'+"); // Banker*  // mod by udara 17-07-2019
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Bank Name </TD>'+");
			
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Branch </TD>'+");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Branch Name</TD>'+");
			out.println(" '<TD WIDTH=\"12%\" align=\"left\">Account No</TD>' +");
			out.println(" '<TD WIDTH=\"15%\" align=\"left\">Reference</TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Telephone </TD>' +");
			out.println(" '<TD WIDTH=\"10%\" align=\"left\">Fax </TD>' +");
			out.println(" '<TD WIDTH=\"*%\" align=\"left\">Relationship (Mts)  </TD>' +");
			out.println(" '</TR></table>';");
			out.println(" }}");
			//out.println("}");
			
			
			/*	out.println("function header_auditors(){");
                
                out.println(" lineno_auditor = 0; ");
                out.println("e_header_auditors.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"25%\" align=\"left\">Auditors Name </TD>'+");
                out.println("'<TD WIDTH=\"25%\" align=\"left\">Auditors Address1</TD>' +");
                out.println("'<TD WIDTH=\"10%\" align=\"left\">Relationship (Yrs/Mts)</TD>' +");
                out.println("'<TD WIDTH=\"15%\" align=\"left\">Reference</TD>' +");
                out.println("'<TD WIDTH=\"10%\" align=\"left\">Telephone</TD>' +");
                out.println("'<TD WIDTH=\"*%\" align=\"left\">Fax  </TD>' +");
            out.println("'</TR></table>';");
                out.println("}");	
                */
			
			/*
        out.println("function header_credit(){");
        out.println(" lineno_credit=0; ");
        out.println("e_header_credit.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"12%\" align=\"left\">Type </TD>'+");
        out.println("'<TD WIDTH=\"15%\" align=\"left\">Name Of Institution</TD>' +");
        out.println("'<TD WIDTH=\"12%\" align=\"left\">Contact Person</TD>' +");
        out.println("'<TD WIDTH=\"12%\" align=\"left\">Contract No </TD>' +");
        out.println("'<TD WIDTH=\"12%\" align=\"left\">Security </TD>' +");
        out.println("'<TD WIDTH=\"12%\" align=\"left\">Approved Amount  </TD>' +");
        out.println("'<TD WIDTH=\"12%\" align=\"left\">Balance Amount  </TD>' +");
        out.println("'<TD WIDTH=\"*%\" align=\"left\">No of Months</TD>' +");
    out.println("'</TR></table>';");
        out.println("}");	
            */
			out.println("function header_credit_c(){");
			out.println(" lineno_credit_c=0; ");
			out.println("e_header_credit_c.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Name Of Institution </TD>'+");
			out.println("'<TD WIDTH=\"13%\" align=\"left\">Contact Person</TD>' +");
			out.println("'<TD WIDTH=\"11%\" align=\"left\">Type of Facility</TD>' +");
			out.println("'<TD WIDTH=\"13%\" align=\"left\">Equipment </TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Approved Amount </TD>' +");
			out.println("'<TD WIDTH=\"8%\" align=\"left\">Monthly Rent</TD>' +");
			out.println("'<TD WIDTH=\"8%\" align=\"left\">Period(Mts)</TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Balance Payable (Mts)</TD>' +");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function header_nonrelated_ref(type){");
			out.println(" if(type=='I'){"); 
			out.println("  lineno_nonrelated = 0; ");
			out.println("  e_header_nonrelated.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Name </TD>'+");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Relationship</TD>' +");
			out.println("  '<TD WIDTH=\"8%\" align=\"left\">Period(Yrs) </TD>' +");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Designation </TD>' +");
			out.println("  '<TD WIDTH=\"15%\" align=\"left\">Tel No. - Res </TD>' +");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Tel No. - Office  </TD>' +");
			out.println("  '<TD WIDTH=\"*%\" align=\"left\">Mobile No  </TD>' +");
			out.println("  '</TR></table>';");
			out.println(" }");		
			out.println(" else{"); 
			out.println("  lineno_nonrelated = 0; ");
			out.println("  e_header_nonrelated_c.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"15%\" align=\"left\">Name </TD>'+");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Relationship</TD>' +");
			out.println("  '<TD WIDTH=\"8%\" align=\"left\">Period(Yrs)</TD>' +");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Designation </TD>' +");
			out.println("  '<TD WIDTH=\"15%\" align=\"left\">Tel No. - Res </TD>' +");
			out.println("  '<TD WIDTH=\"14%\" align=\"left\">Tel No. - Office  </TD>' +");
			out.println("  '<TD WIDTH=\"*%\" align=\"left\">Mobile No  </TD>' +");
			out.println("  '</TR></table>';");
			out.println(" }}");		
			//out.println("}");		
			
			out.println("function header_family(){");
			out.println(" lineno_family=0; ");
			out.println("e_header_family_members.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<TD WIDTH=\"14%\" align=\"left\">Member</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Name</TD>' +");
			out.println("'<TD WIDTH=\"25%\" align=\"left\">Address</TD>' +");
			out.println("'<TD WIDTH=\"5%\" align=\"left\">Age </TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Tel No </TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Moblie No </TD>' +");
			out.println("'</TR></table>';");
			//out.println(" alert('lineno fam header '+lineno_family); ");
			out.println("}");
			
			//comment by nuwan de silva on 07-09-06----------------------------------
			/*
            //Corporate ###
            out.println("function header_company_dir(){");
            out.println(" lineno_company_dir = 0; ");
            out.println("e_header_company_directors.innerHTML +='<table  align=\"center\" border=\"1\" width=\"100%\" class=\"table\"><TR><TD WIDTH=\"25%\" align=\"left\">Name </TD>'+");
            out.println("'<TD WIDTH=\"10%\" align=\"left\">NIC No</TD>' +");
            out.println("'<TD WIDTH=\"6%\" align=\"left\">Stake(%)</TD>' +");
            out.println("'<TD WIDTH=\"10%\" align=\"left\">No of Shares </TD>' +");
            out.println("'<TD WIDTH=\"20%\" align=\"left\">Value(Rs.) </TD>' +");
            out.println("'<TD WIDTH=\"*%\" align=\"left\">Position  </TD>' +");
        out.println("'</TR></table>';");
        out.println("}");
            */
			
			out.println("function header_subsidiaries(){");
			out.println(" lineno_subsidiaries = 0; ");
			out.println("e_header_subsidiaries.innerHTML +='<table  align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><TR><TD WIDTH=\"25%\" align=\"left\">Name </TD>'+");
			out.println("'<TD WIDTH=\"8%\" align=\"left\">Stake(%)</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Value (Rs.)</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Telephone No </TD>' +");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Officer </TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Business Activities  </TD>' +");
			out.println("'</TR></table>';");
			out.println("}");	
			
			
			
			
			
			
			out.println("function header_customer(){");
			out.println(" lineno_customer = 0; ");
			out.println("e_header_customer.innerHTML +='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><TR><TD WIDTH=\"23%\" align=\"left\">Name </TD>'+");
			out.println("'<TD WIDTH=\"9%\" align=\"left\">Type</TD>' +");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Address</TD>' +");
			out.println("'<TD WIDTH=\"13%\" align=\"left\">Relationship(Mts/Yrs)</TD>' +");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">Contact Person </TD>' +");
			out.println("'<TD WIDTH=\"*%\" align=\"left\">Tel No </TD>' +");
			out.println("'</TR></table>';");
			out.println("}");	
			
			out.println("function add_button(){");
			out.println("e_mode2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_EMP\" value=\"Add\" onClick=\"add_row_emp()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			
			out.println("function add_button_bank(type){");
			//out.println("alert('add but type '+type);");
			out.println("if(type=='I') { ");
			out.println("e_add_but_bank.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" onClick=add_row_bank(\"'+type+'\") type=\"button\" name=\"BUT_ADD_BANK\" value=\"Add\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			out.println("else { ");
			out.println("e_add_but_bank_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" onClick=add_row_bank(\"'+type+'\") type=\"button\" name=\"BUT_ADD_BANK\" value=\"Add\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			out.println("}");
			
			out.println("function add_button_credit(){");
			out.println("e_add_but_credit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CREDIT\" value=\"Add\" onClick=\"add_row_credit()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_credit_c(){");
			out.println("e_add_but_credit_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CREDIT_C\" value=\"Add\" onClick=\"add_row_credit_c()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_nonrelated(type){");
			out.println(" if(type=='I') { ");
			out.println("  e_add_but_nonrelated.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_NON\" value=\"Add\" onClick=add_row_nonrelated_ref(\"'+type+'\")></td>'+"); 
			out.println("  '<td width=\"*%\"></td>'+"); 
			out.println("  '</TR></table>';");
			out.println(" }");
			out.println(" else {");
			out.println("  e_add_but_nonrelated_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_NON\" value=\"Add\" onClick=add_row_nonrelated_ref(\"'+type+'\")></td>'+"); 
			out.println("  '<td width=\"*%\"></td>'+"); 
			out.println("  '</TR></table>';");
			out.println(" }");
			
			
			out.println("}");
			
			out.println("function add_button_family(){");
			out.println("e_add_but_family_members.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_FAM\" value=\"Add\" onClick=\"add_row_family()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_company_dir(){");
			out.println("e_add_but_company_directors.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_COM\" value=\"Add\" onClick=\"add_row_company_dir()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_customer(){");
			out.println("e_add_but_customer.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_CUS\" value=\"Add\" onClick=\"add_row_customer()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_ba(){");
			out.println("e_add_but_ba.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_BA\" value=\"Add\" onClick=\"add_row_ba()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_button_subsidiaries(){");
			out.println("e_add_but_subsidiaries.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_SUB\" value=\"Add\" onClick=\"add_row_subsidiaries()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			
			out.println("function add_button_auditors(){");
			out.println("e_add_but_auditors.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<td width=\"20%\"><input class=\"but_input\" type=\"button\" name=\"BUT_ADD_AUDITORS\" value=\"Add\" onClick=\"add_row_auditors()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</TR></table>';");
			out.println("}");
			
			
			//Individual####
			out.println("function add_label_credit(){");
			out.println("e_label_credit.innerHTML='<table align=\"center\"  width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B><u> B.Details Of Credit Facilities Obtained From Banks & Other Financial Institutions </u> </B></td>'+"); 
			//out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_nonrelated_ref(type){");
			out.println("if(type=='I'){ "); 
			out.println("  e_label_nonrelated.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<tr>'+");
			//out.println("  '<td width=\"80%\" ><B><DIV id=\"DIV_TXT_NON_RELATED\"  class=\"div_input\"><u> C.Non Related Referees (*) </u> </DIV> </B></td>'+"); // commented by udara on 09-01-2012
			out.println("  '<td width=\"80%\" ><B><DIV id=\"DIV_TXT_NON_RELATED\"  class=\"div_input\"><u> C.Non Related Referees </u> </DIV> </B></td>'+"); // added by udara on 09-01-2012
			out.println("  '</tr>'+");
			out.println("  '</TR></table>';");
			out.println(" }");
			out.println("else {");
			out.println("  e_label_nonrelated_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("  '<tr>'+");
			out.println("  '<td width=\"80%\" ><B><u> D.Non Related Referees </u></B></td>'+"); 
			out.println("  '</tr>'+");
			out.println("  '</TR></table>';");
			out.println("}");
			out.println("}");
			
			out.println("function add_label_family(){");
			out.println("e_label_family_members.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B><u> E.Details Of Family Members </u> </B></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_income_expense(){");
			out.println("e_label_income_expense.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			//out.println("'<td width=\"80%\" ><B><DIV id=\"DIV_TXT_TOT_INCOME\"  class=\"div_input\"><u> D.Details Of Present Monthly Income/Expenses (*) </u> </div> </B></td>'+"); // commented by udara on 09-01-2012
			out.println("'<td width=\"80%\" ><B><DIV id=\"DIV_TXT_TOT_INCOME\"  class=\"div_input\"><u> D.Details Of Present Monthly Income/Expenses </u> </div> </B></td>'+");  // added by udara on 09-01-2012
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			//Corporate ######
			out.println("function add_label_credit_c(){");
			out.println("e_label_credit_c.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B><u> B.Details Of Credit Facilities Obtained From Banks & Other Financial Institutions </u> </B></td>'+"); 
			//out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_ba(){");
			out.println("e_label_ba.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><b><DIV id=\"DIV_TXT_BA\" class=\"div_input\"> Business Activities</div></b></td>'+"); // Business Activities (*) // mod by udara 17-07-2019
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_company_dir(){");
			out.println("e_label_company_directors.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><b> Directors/Partners/Shareholders </b></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_subsidiaries(){");
			out.println("e_label_subsidiaries.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><b> Subsidiaries & Associated Companies </b></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_bank(){");
			out.println("e_label_bank.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><b><DIV id=\"DIV_TXT_BANK\"  class=\"div_input\"> Bankers & Auditors</DIV> </b></td>'+"); // Bankers & Auditors (*) // mod by udara 17-07-2019
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_customer(){");
			out.println("e_label_customer.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			//out.println("'<td width=\"80%\" ><B> C.TRADE CUSTOMERS & SUPPLIERS </B></td>'+"); 
			out.println("'<td width=\"80%\" ><B><u> C.Trade Customers & Suppliers </u> </B></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function add_label_credit(){");
			out.println("e_label_credit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
			out.println("'<tr>'+");
			out.println("'<td width=\"80%\" ><B><u> B.Details Of Credit Facilities Obtained From Banks & Other Financial Institutions </u> </B></td>'+"); 
			//out.println("'<td width=\"80%\" ><B> B.DETAILS OF CREDIT FACILITIES OBTAINED FROM BANKS & OTHER FINANCIAL INSTITUTIONS </B></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</TR></table>';");
			out.println("}");
			
			out.println("function del_row(rowNo){"); 
			//out.println("alert('line no :' +rowNo)");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			
			out.println("m_organization=\"TXT_ORGANIZATION\"+i");
			out.println("m_tel_no=\"TXT_TEL_NO\"+i");
			out.println("m_from_date_dd=\"TXT_FROM_DATE_DD\"+i");
			out.println("m_from_date_mm=\"TXT_FROM_DATE_MM\"+i");
			out.println("m_from_date_yy=\"TXT_FROM_DATE_YY\"+i");
			out.println("m_to_date_dd=\"TXT_TO_DATE_DD\"+i");
			out.println("m_to_date_mm=\"TXT_TO_DATE_MM\"+i");
			out.println("m_to_date_yy=\"TXT_TO_DATE_YY\"+i");
			out.println("m_designation=\"TXT_DESIGNATION2\"+i");
			
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			out.println("     array_organization[j]=document.Form1.elements[m_organization].value;");
			out.println("     array_telno[j]=document.Form1.elements[m_tel_no].value;");
			out.println("     array_from_date_dd[j]=document.Form1.elements[m_from_date_dd].value;");
			out.println("     array_from_date_mm[j]=document.Form1.elements[m_from_date_mm].value;");
			out.println("     array_from_date_yy[j]=document.Form1.elements[m_from_date_yy].value;");
			out.println("     array_to_date_dd[j]=document.Form1.elements[m_to_date_dd].value;");
			out.println("     array_to_date_mm[j]=document.Form1.elements[m_to_date_mm].value;");
			out.println("     array_to_date_yy[j]=document.Form1.elements[m_to_date_yy].value;");
			out.println("     array_designation[j]=document.Form1.elements[m_designation].value;");
			
			out.println(" j=j+1;");
			out.println("}");
			
			//out.println(" alert('row &&'+rowNo  );");
			out.println("m_organization=\"TXT_ORGANIZATION\"+rowNo");
			out.println("m_tel_no=\"TXT_TEL_NO\"+rowNo");
			out.println("m_from_date_dd=\"TXT_FROM_DATE_DD\"+rowNo");
			out.println("m_from_date_mm=\"TXT_FROM_DATE_MM\"+rowNo");
			out.println("m_from_date_yy=\"TXT_FROM_DATE_YY\"+rowNo");
			out.println("m_to_date_dd=\"TXT_TO_DATE_DD\"+rowNo");
			out.println("m_to_date_mm=\"TXT_TO_DATE_MM\"+rowNo");
			out.println("m_to_date_yy=\"TXT_TO_DATE_YY\"+rowNo");
			out.println("m_designation=\"TXT_DESIGNATION2\"+rowNo");
			
			//out.println(" alert('val &&'+document.Form1.elements[m_organization].value);");
			
			out.println("  if( document.Form1.elements[m_organization].value != \"\" || document.Form1.elements[m_tel_no].value !=\"\" ||  document.Form1.elements[m_from_date_dd].value !=\"\" || document.Form1.elements[m_from_date_mm].value !=\"\" || document.Form1.elements[m_from_date_yy].value != \"\" || document.Form1.elements[m_to_date_dd].value != \"\" || document.Form1.elements[m_to_date_mm].value != \"\" || document.Form1.elements[m_designation].value != \"\" ) { "); 
			out.println(" 	if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("    lineno=lineno-1;");
			out.println("    arr_size=arr_size-1;");
			out.println("    write_data(arr_size);");
			out.println("   }");
			out.println("  }");
			out.println(" else { ");
			out.println("    lineno=lineno-1;");
			out.println("    arr_size=arr_size-1;");
			out.println("    write_data(arr_size);");
			out.println(" }");
			out.println("}");
			/*
             out.println("function disable_rows_emp(no)");
             out.println("{");
         out.println("for(var i=0;i<no;i++)");
           out.println("{	");
             out.println("   m_organization=\"TXT_ORGANIZATION\"+i");
             out.println("   m_tel_no=\"TXT_TEL_NO\"+i");
           //out.println("   m_from_date=\"TXT_FROM_DATE\"+i");
             //out.println("   m_to_date=\"TXT_TO_DATE\"+i");
             out.println("m_from_date_dd=\"TXT_FROM_DATE_DD\"+i");
             out.println("m_from_date_mm=\"TXT_FROM_DATE_MM\"+i");
             out.println("m_from_date_yy=\"TXT_FROM_DATE_YY\"+i");
                
             out.println("m_to_date_dd=\"TXT_TO_DATE_DD\"+i");
             out.println("m_to_date_mm=\"TXT_TO_DATE_MM\"+i");
             out.println("m_to_date_yy=\"TXT_TO_DATE_YY\"+i");	
                
             out.println("   m_designation=\"TXT_DESIGNATION2\"+i");
             out.println("   m_del_but =\"BUT_EMP_DEL\"+i");	
         out.println("   document.Form1.elements[m_organization].disabled=true;");                           
           out.println("   document.Form1.elements[m_tel_no].disabled=true;");
           out.println("   document.Form1.elements[m_from_date_dd].disabled=true;");
             out.println("   document.Form1.elements[m_from_date_mm].disabled=true;");
             out.println("   document.Form1.elements[m_from_date_yy].disabled=true;");
           out.println("   document.Form1.elements[m_to_date_dd].disabled=true;");
             out.println("   document.Form1.elements[m_to_date_mm].disabled=true;");
             out.println("   document.Form1.elements[m_to_date_yy].disabled=true;");	
           out.println("   document.Form1.elements[m_designation].disabled=true;");
             out.println("   document.Form1.elements[m_del_but].disabled=true;");	
             out.println("}	");
             out.println("   document.Form1.BUT_ADD_EMP.disabled=true;");	
         out.println("} ");
    
         */
			
			
			out.println("function write_data(size){");
			//out.println("alert('size'+size);");
			out.println("e_mode_emp.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if(array_organization[j]==\"\" && array_telno[j]==\"\" && array_from_date_dd[j]==\"\" && array_from_date_mm[j]==\"\" && array_from_date_yy[j]==\"\" && array_to_date_dd[j]==\"\" && array_to_date_mm[j]==\"\" && array_to_date_yy[j]==\"\" && array_designation[j]==\"\" ){");
			
			out.println("e_mode_emp.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ORGANIZATION'+j+' value=\"\" style=\"width:250px;\" maxlength=\"200\" size=\"55\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO'+j+' value=\"\" onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width:130px;\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD'+j+' value=\"\"  maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM'+j+' value=\"\" maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY'+j+' value=\"\"  maxlength=\"4\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD'+j+' value=\"\"  maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM'+j+' value=\"\"  maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY'+j+' value=\"\" maxlength=\"4\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"19%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION2'+j+' value=\"\" maxlength=\"20\" size=\"35\" style=\"width:160px;\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("lineno=j;");
			out.println("continue;");
			out.println("}");
			
			out.println("e_mode_emp.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ORGANIZATION'+j+' value=\"'+array_organization[j]+'\"  style=\"width:250px;\" maxlength=\"200\" size=\"55\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO'+j+' value=\"'+array_telno[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width:130px;\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD'+j+'  value=\"'+array_from_date_dd[j]+'\"  maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM'+j+' maxlength=\"2\" value=\"'+array_from_date_mm[j]+'\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY'+j+' maxlength=\"4\" value=\"'+array_from_date_yy[j]+'\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD'+j+' value=\"'+array_to_date_dd[j]+'\"  maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM'+j+' maxlength=\"2\" value=\"'+array_to_date_mm[j]+'\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY'+j+' maxlength=\"4\" value=\"'+array_to_date_yy[j]+'\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"19%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION2'+j+' value=\"'+array_designation[j]+'\" style=\"width:160px;\"  maxlength=\"20\" size=\"35\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("}");
			out.println("lineno=j;");
			out.println("}");
			
			
			out.println("function disable_rows_bank(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			out.println("m_bank=\"TXT_BANK_CODE\"+i");
			out.println("m_but_bank=\"BUT_TXT_BANK_CODE\"+i");	
			out.println("m_branch=\"TXT_BRANCH_CODE\"+i");
			out.println("m_but_branch=\"BUT_TXT_BRANCH_CODE\"+i");	
			out.println("m_accno=\"TXT_ACCOUNT_NO\"+i");
			out.println("m_reference=\"TXT_REFERENCE\"+i");
			out.println("m_telno=\"TXT_TEL_NO2\"+i");
			out.println("m_faxno=\"TXT_FAX_NO\"+i");
			out.println("m_relationship=\"TXT_RELATIONSHIP2\"+i");
			out.println("m_del_but_bank=\"BUT_BANK_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_bank].disabled=true;");                           
			out.println("   document.Form1.elements[m_but_bank].disabled=true;");	
			out.println("   document.Form1.elements[m_branch].disabled=true;");
			out.println("   document.Form1.elements[m_but_branch].disabled=true;");	
			out.println("   document.Form1.elements[m_accno].disabled=true;");
			out.println("   document.Form1.elements[m_reference].disabled=true;");
			out.println("   document.Form1.elements[m_telno].disabled=true;");
			out.println("   document.Form1.elements[m_faxno].disabled=true;");	
			out.println("   document.Form1.elements[m_relationship].disabled=true;");		
			out.println("   document.Form1.elements[m_del_but_bank].disabled=true;");			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_BANK.disabled=true;");	
			out.println("} ");
			
			
			//To DELETE bank row
			out.println("function del_row_bank(rowNo,type){"); 
			
			//out.println("alert('test1 bank -' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("if(arr_size_bank !=1 || type!='C'){");
			out.println("for(var i=0;i<arr_size_bank;i++){");
			
			out.println("m_bank=\"TXT_BANK_CODE\"+i");
			out.println("m_bank_name=\"TXT_BANK_NAME\"+i");
			
			out.println("m_branch=\"TXT_BRANCH_CODE\"+i");
			out.println("m_branch_name=\"TXT_BRANCH_NAME\"+i");
			
			out.println("m_accno=\"TXT_ACCOUNT_NO\"+i");
			out.println("m_reference=\"TXT_REFERENCE\"+i");
			out.println("m_telno=\"TXT_TEL_NO2\"+i");
			out.println("m_faxno=\"TXT_FAX_NO\"+i");
			out.println("m_relationship=\"TXT_RELATIONSHIP2\"+i");
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//	out.println("alert('name -' +document.Form1.elements[m_organization].value)");
			
			out.println("array_banker[j]=document.Form1.elements[m_bank].value;");
			//---modified By : delanjali-------------------------------------------------------------------
			//---date				 : 2007-06-20-------------------------------------------------------------------
			
			out.println("array_banker_name[j]=document.Form1.elements[m_bank_name].value;");
			//------------------------------------------------------------------------------------------------
			
			out.println("array_branch[j]=document.Form1.elements[m_branch].value;");
			out.println("array_branch_name[j]=document.Form1.elements[m_branch_name].value;");
			
			
			out.println("array_acc_no[j]=document.Form1.elements[m_accno].value;");
			out.println("array_reference[j]=document.Form1.elements[m_reference].value;");
			out.println("array_telno_bank[j]=document.Form1.elements[m_telno].value;");
			out.println("array_fax_bank[j]=document.Form1.elements[m_faxno].value;");
			out.println("array_relationship_b[j]=document.Form1.elements[m_relationship].value;");
			
			out.println("j=j+1;");
			out.println("}");
			
			out.println("m_bank=\"TXT_BANK_CODE\"+rowNo");
			out.println("m_branch=\"TXT_BRANCH_CODE\"+rowNo");
			out.println("m_accno=\"TXT_ACCOUNT_NO\"+rowNo");
			out.println("m_reference=\"TXT_REFERENCE\"+rowNo");
			out.println("m_telno=\"TXT_TEL_NO2\"+rowNo");
			out.println("m_faxno=\"TXT_FAX_NO\"+rowNo");
			out.println("m_relationship=\"TXT_RELATIONSHIP2\"+rowNo");
			
			out.println(" if( document.Form1.elements[m_bank].value != '' || document.Form1.elements[m_branch].value != '' || document.Form1.elements[m_accno].value != '' || document.Form1.elements[m_reference].value != '' || document.Form1.elements[m_telno].value != '' || document.Form1.elements[m_faxno].value != '' ){ ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("		lineno_bank=lineno_bank-1;");
			out.println("		arr_size_bank=arr_size_bank-1;");
			out.println("		write_data_bank(arr_size_bank,type);");
			out.println(" }");
			out.println(" }");
			out.println(" else {");
			out.println("		lineno_bank=lineno_bank-1;");
			out.println("		arr_size_bank=arr_size_bank-1;");
			out.println("		write_data_bank(arr_size_bank,type);");
			out.println(" }");
			out.println("}");
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
			//out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"\" onblur=\"makeRequest8(this)\"  maxlength=\"10\" size=\"12\" style=\"width: 70px\" ></TD>'+"); // commented by udara 22-09-2017
			out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"-\" onblur=\"\"  maxlength=\"10\" size=\"12\" style=\"width: 70px\" ></TD>'+"); // added by udara 22-09-2017
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\" style=\"width: 30px\"  ></td>'+");
			//out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"\"></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"-\"></TD>'+"); // added by udara 22-09-2017
			
			
			//out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+' value=\"\" onblur=\"makeRequest9(this)\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // commented by udara 22-09-2017
			out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+' value=\"\" onblur=\"\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // added by udara 22-09-2017
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\" style=\"width: 30px\"  ></td>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+j+' value=\"\" ></TD>'+");
			
			
			
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"\" maxlength=\"20\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+' value=\"\" maxlength=\"20\" size=\"20\"  style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+j+' value=\"\" maxlength=\"60\" size=\"22\" onBlur=\"Validate_Telephone_Number(this,1)\"   style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+j+' value=\"\" maxlength=\"60\" size=\"22\"  onBlur=\"Validate_Telephone_Number(this,2)\"   style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"\" onblur=\"check_number(this)\" style=\"width: 55px\"  maxlength=\"4\" size=\"6\">'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			out.println("else{");
			out.println("e_txt_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			//out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+'  onblur=\"makeRequest8(this)\"  value=\"\" maxlength=\"10\" size=\"12\" style=\"width: 70px\"></TD>'+"); // commented by udara 22-09-2017
			out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+'  onblur=\"\"  value=\"-\" maxlength=\"10\" size=\"12\" style=\"width: 70px\"></TD>'+"); // added by udara 22-09-2017
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\"  style=\"width: 30px\" ></td>'+");
			
			//out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"\"></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"-\"></TD>'+"); // added by udara 22-09-2017
			
			//out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+' onblur=\"makeRequest9(this)\"  value=\"\" maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // commented by udara 22-09-2017
			out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+' onblur=\"\"  value=\"\" maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // added by udara 22-09-2017
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\" style=\"width: 30px\"  ></td>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+j+' value=\"\" ></TD>'+");
			
			
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"\" maxlength=\"20\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+' value=\"\" maxlength=\"20\" size=\"20\"  style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+j+' value=\"\" maxlength=\"60\" size=\"22\"   onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+j+' value=\"\" maxlength=\"60\" size=\"22\"    onBlur=\"Validate_Telephone_Number(this,2)\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"\"  onblur=\"check_number(this)\" style=\"width: 55px\" maxlength=\"4\" size=\"6\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			out.println(" lineno_bank=j;");
			out.println("continue;");
			out.println("}");
			
			
			out.println("if(type=='I'){");
			out.println("e_txt_bank.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"'+array_banker[j]+'\"   maxlength=\"10\" size=\"12\" style=\"width: 70px\" ></TD>'+");
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\" style=\"width: 30px\" ></td>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"'+array_banker_name[j]+'\"></TD>'+");
			
			
			out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+'  value=\"'+array_branch[j]+'\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+");
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\" style=\"width: 30px\"  ></td>'+");
			//out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAMEE'+j+'  value=\"'+array_branch_name[j]+'\"></TD>'+"); //comment by nuwan de silva on 01-10-07
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+j+'  value=\"'+array_branch_name[j]+'\"></TD>'+");//added by nuwan de silva on 01-10-07
			
			
			
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"'+array_acc_no[j]+'\"   maxlength=\"20\" size=\"20\"    style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+'  value=\"'+array_reference[j]+'\"   maxlength=\"20\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+j+'  value=\"'+array_telno_bank[j]+'\"   maxlength=\"60\" size=\"22\" onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+j+'   value=\"'+array_fax_bank[j]+'\"   maxlength=\"60\" size=\"22\"   onBlur=\"Validate_Telephone_Number(this,2)\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"7%\" ><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"'+array_relationship_b[j]+'\"  onblur=\"check_number(this)\"  style=\"width: 55px\" maxlength=\"4\" size=\"6\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			
			out.println("}");
			out.println("else{");
			out.println("e_txt_bank_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+j+' value=\"'+array_banker[j]+'\"   maxlength=\"10\" size=\"12\" style=\"width: 70px\"></TD>'+");
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+j+' value=\"?\" onClick=\"help_button_bank('+j+')\" style=\"width: 30px\"  ></td>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+j+' value=\"'+array_banker_name[j]+'\"></TD>'+");
			
			
			
			out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+j+'  value=\"'+array_branch[j]+'\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+");
			out.println("'<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+j+' value=\"?\" onClick=\"help_button_branch('+j+')\" style=\"width: 30px\"  ></td>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+j+'  value=\"'+array_branch_name[j]+'\"></TD>'+");
			
			
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+j+' value=\"'+array_acc_no[j]+'\"   maxlength=\"20\" size=\"20\"    style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+j+'  value=\"'+array_reference[j]+'\"   maxlength=\"20\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+j+'  value=\"'+array_telno_bank[j]+'\"   maxlength=\"60\" size=\"22\" onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+j+'   value=\"'+array_fax_bank[j]+'\"   maxlength=\"60\" size=\"22\"   onBlur=\"Validate_Telephone_Number(this,2)\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+j+' value=\"'+array_relationship_b[j]+'\"  onblur=\"check_number(this)\"  style=\"width: 55px\" maxlength=\"4\" size=\"6\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+j+' value=\"Delete\" onClick=del_row_bank(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			out.println("}");
			out.println(" lineno_bank=j;");
			//out.println(" alert('line_no bank '+lineno_bank);");
			out.println("}");
			
			
			//To DELETE Credit Facilities row
			out.println("function del_row_credit(rowNo){"); 
			
			//out.println("alert('Row No credit -' +rowNo)");
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
			
			out.println("if(i==rowNo){");
			out.println("continue;");
			out.println("}");
			
			
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
			
			//out.println("k=j-1;");
			//out.println("alert('row &&'+rowNo  );");
			
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+rowNo");
			out.println("m_institute=\"TXT_INSTITUTION\"+rowNo");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+rowNo");
			out.println("m_contract_no=\"TXT_CONTRACT_NO\"+rowNo");
			out.println("m_security=\"TXT_SECURITY\"+rowNo");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+rowNo");
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+rowNo");
			out.println("m_months=\"TXT_MONTHS\"+rowNo");
			
			//out.println(" alert('val &&'+document.Form1.elements[m_institute].value);");
			
			out.println("if(document.Form1.elements[m_institute].value!=\"\" || document.Form1.elements[m_contact_p].value!=\"\" || document.Form1.elements[m_contract_no].value != \"\" || document.Form1.elements[m_security].value != \"\" || document.Form1.elements[m_app_amount].value != \"\" ){");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println(" lineno_credit=lineno_credit-1;");
			out.println(" arr_size_credit=arr_size_credit-1;");
			out.println("  write_data_credit(arr_size_credit);");
			out.println(" }");
			out.println(" }");
			out.println("else { ");
			out.println(" lineno_credit=lineno_credit-1;");
			out.println(" arr_size_credit=arr_size_credit-1;");
			out.println("  write_data_credit(arr_size_credit);");
			out.println(" }");
			out.println("}");
			
			out.println("function disable_rows_credit(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+i");
			out.println("m_institute=\"TXT_INSTITUTION\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+i");
			out.println("m_contract_no=\"TXT_CONTRACT_NO\"+i");
			out.println("m_security=\"TXT_SECURITY\"+i");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+i");
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+i");
			out.println("m_months=\"TXT_MONTHS\"+i");
			out.println("m_del_but_credit=\"BUT_CREDIT_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_type].disabled=true;");                           
			out.println("   document.Form1.elements[m_institute].disabled=true;");	
			out.println("   document.Form1.elements[m_contact_p].disabled=true;");
			out.println("   document.Form1.elements[m_contract_no].disabled=true;");	
			out.println("   document.Form1.elements[m_security].disabled=true;");
			out.println("   document.Form1.elements[m_app_amount].disabled=true;");
			out.println("   document.Form1.elements[m_bal_amount].disabled=true;");
			out.println("   document.Form1.elements[m_months].disabled=true;");	
			out.println("   document.Form1.elements[m_del_but_credit].disabled=true;");			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_CREDIT.disabled=true;");	
			out.println("} ");
			
			out.println("function write_data_credit(size){");
			//out.println("alert('arr_size_credit '+size);");
			out.println("e_txt_credit.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if( array_institute[j]==\"\" && array_contact_person[j]==\"\" && array_contract_no[j]==\"\" && array_security[j]==\"\" && array_app_amount[j]==\"\" && array_bal_amount[j]==\"\"  && array_months[j]==\"\" ){");
			
			out.println("e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"12%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" style=\"width: 100px\">'+");
			out.println("'<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
			out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"\"  maxlength=\"100\" style=\"width: 130px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"\" maxlength=\"100\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value=\"\" maxlength=\"20\" size=\"13\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value=\"\" maxlength=\"100\" size=\"14\"  style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' onblur=\"check_number_decimal(this,21)\" value=\"\" maxlength=\"21\" style=\"width: 100px\"></TD>'+"); // Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' onblur=\"check_number_decimal(this,21)\" value=\"\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"\" onblur=\"check_number(this)\" maxlength=\"4\" size=\"5\" style=\"width: 60px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println(" lineno_credit=j; ");  
			out.println("continue;");
			out.println("}");
			
			out.println(" if( array_type[j] ==\"VEHICLE LOAN\" ){ ");
			out.println("  ");
			out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<td WIDTH=\"12%\">'+ ");
			out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" style=\"width: 100px\">'+");
			out.println("  '<OPTION value=\"VEHICLE LOAN\" SELECTED >Vehicle Loan </option>'+");
			out.println("  '<OPTION value=\"HOUSE LOAN\" >House Loan </option>'+");
			out.println("  '<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			out.println("  '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute[j]+'\"  maxlength=\"100\" style=\"width: 130px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person[j]+'\" maxlength=\"100\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value=\"'+array_contract_no[j]+'\" maxlength=\"60\" size=\"13\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value=\"'+array_security[j]+'\" maxlength=\"100\" size=\"14\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months[j]+'\" onblur=\"check_number(this)\" maxlength=\"4\" size=\"5\" style=\"width: 55px\"></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			out.println("  '</td></tr></table>';");
			out.println(" }");
			
			out.println(" else if( array_type[j] ==\"HOUSE LOAN\" ){ ");
			out.println("   ");
			out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<td WIDTH=\"12%\">'+ ");
			out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" style=\"width: 100px\">'+");
			out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			out.println("  '<OPTION value=\"HOUSE LOAN\" SELECTED >House Loan </option>'+");
			out.println("  '<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			out.println("  '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute[j]+'\"  maxlength=\"100\" style=\"width: 130px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person[j]+'\"  maxlength=\"100\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value=\"'+array_contract_no[j]+'\"  maxlength=\"60\" size=\"13\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value=\"'+array_security[j]+'\"  maxlength=\"100\" size=\"14\" style=\"width: 100px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"5\" style=\"width: 55px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			out.println("  '</td></tr></table>';");
			out.println(" }");
			
			out.println(" else if( array_type[j] ==\"PERSONAL LOAN\" ){ ");
			out.println("  ");
			out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<td WIDTH=\"12%\">'+ ");
			out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" style=\"width: 100px\">'+");
			out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			out.println("  '<OPTION value=\"HOUSE LOAN\"  >House Loan </option>'+");
			out.println("  '<OPTION value=\"PERSONAL LOAN\" SELECTED >Personal Loan </option>'+");
			out.println("  '<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			out.println("  '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute[j]+'\"  maxlength=\"100\" style=\"width: 130px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person[j]+'\"  maxlength=\"100\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value=\"'+array_contract_no[j]+'\"  maxlength=\"60\" size=\"13\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+'  value=\"'+array_security[j]+'\" maxlength=\"100\" size=\"14\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months[j]+'\" onblur=\"check_number(this)\"  maxlength=\"4\" size=\"5\" style=\"width: 55px\"></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			out.println("  '</td></tr></table>';");
			out.println(" }");
			
			out.println(" else if( array_type[j] ==\"CREDIT CARD\" ){ ");
			out.println("  ");
			out.println("  e_txt_credit.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<td WIDTH=\"12%\">'+ ");
			out.println("  '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input\" style=\"width: 100px\" >'+");
			out.println("  '<OPTION value=\"VEHICLE LOAN\"  >Vehicle Loan </option>'+");
			out.println("  '<OPTION value=\"HOUSE LOAN\"  >House Loan </option>'+");
			out.println("  '<OPTION value=\"PERSONAL LOAN\"  >Personal Loan </option>'+");
			out.println("  '<OPTION value=\"CREDIT CARD\" SELECTED >Credit Card </option>'+");
			out.println("  '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute[j]+'\"  maxlength=\"100\" style=\"width: 130px\"  ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person[j]+'\"  maxlength=\"100\" style=\"width: 100px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+j+' value=\"'+array_contract_no[j]+'\" maxlength=\"20\" size=\"13\"  style=\"width: 100px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+j+' value=\"'+array_security[j]+'\"  maxlength=\"100\" size=\"14\" style=\"width: 100px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount[j]+'\"  onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\" ></TD>'+"); // Modified by Thamali Jayatunga on 2009.10.19
			out.println("  '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount[j]+'\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months[j]+'\" onblur=\"check_number(this)\"  maxlength=\"4\" size=\"5\" style=\"width: 55px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit('+j+')\">'+");
			out.println("  '</td></tr></table>';");
			out.println("  }");
			
			out.println("}");
			out.println(" lineno_credit=j; ");  
			//out.println(" alert('line_no credit '+lineno_credit);");
			out.println("}");
			
			
			//To DELETE Non-related ref row
			out.println("function del_row_nonrelated_ref(rowNo,type){"); 
			
			//out.println("alert('Non related type :' +type)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			
			out.println("if(arr_size_nonrelated!=1 || type!='I'){");
			
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
			
			out.println("m_ref_name=\"TXT_NAME_REFEREE\"+rowNo");
			out.println("m_relationship=\"TXT_RELATIONSHIP3\"+rowNo");
			out.println("m_period=\"TXT_PERIOD\"+rowNo");
			out.println("m_design=\"TXT_DESIGNATION3\"+rowNo");
			out.println("m_home_telno=\"TXT_HOME_TEL_NO\"+rowNo");
			out.println("m_off_telno=\"TXT_OFFICE_TEL_NO\"+rowNo");
			out.println("m_mobile_no=\"TXT_MOBILE_NO\"+rowNo");
			
			out.println("if( document.Form1.elements[m_ref_name].value != '' || document.Form1.elements[m_relationship].value != '' || document.Form1.elements[m_period].value != '' || document.Form1.elements[m_design].value != '' || document.Form1.elements[m_home_telno].value !='' || document.Form1.elements[m_off_telno].value !='' ){ ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("  lineno_nonrelated = lineno_nonrelated-1;");
			out.println("  arr_size_nonrelated = arr_size_nonrelated-1;");
			out.println("  write_data_nonrelated(arr_size_nonrelated,type);");
			out.println(" }");
			out.println("}");
			out.println("else { ");
			out.println("  lineno_nonrelated = lineno_nonrelated-1;");
			out.println("  arr_size_nonrelated = arr_size_nonrelated-1;");
			out.println("  write_data_nonrelated(arr_size_nonrelated,type);");
			out.println("}");
			out.println("}");
			out.println("}");	
			
			out.println("function disable_rows_non(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			out.println(" m_ref_name=\"TXT_NAME_REFEREE\"+i");
			out.println(" m_relationship=\"TXT_RELATIONSHIP3\"+i");
			out.println(" m_period=\"TXT_PERIOD\"+i");
			out.println(" m_design=\"TXT_DESIGNATION3\"+i");
			out.println(" m_home_telno=\"TXT_HOME_TEL_NO\"+i");
			out.println(" m_off_telno=\"TXT_OFFICE_TEL_NO\"+i");
			out.println(" m_mobile_no=\"TXT_MOBILE_NO\"+i");
			out.println(" m_del_but_non=\"BUT_NON_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_ref_name].disabled=true;");                           
			out.println("   document.Form1.elements[m_relationship].disabled=true;");	
			out.println("   document.Form1.elements[m_period].disabled=true;");
			out.println("   document.Form1.elements[m_design].disabled=true;");	
			out.println("   document.Form1.elements[m_home_telno].disabled=true;");
			out.println("   document.Form1.elements[m_off_telno].disabled=true;");
			out.println("   document.Form1.elements[m_mobile_no].disabled=true;");	
			out.println("   document.Form1.elements[m_del_but_non].disabled=true;");			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_NON.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_nonrelated(size,type){");
			//out.println("alert('write data size'+size);");
			out.println("e_txt_nonrelated.innerHTML=\"\";");
			out.println("e_txt_nonrelated_c.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if(array_name[j]==\"\" && array_relationship[j]==\"\" && array_design[j]==\"\" && array_telno_home[j]==\"\"){");
			out.println("if(type=='I') { ");
			out.println("  e_txt_nonrelated.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"\"  maxlength=\"100\" size=\"22\" style=\"width: 120px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+'  value=\"\"   maxlength=\"50\" size=\"10\" style=\"width: 115px\"></TD>'+");
			out.println("  '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+j+' value=\"\"  maxlength=\"3\" onblur=\"check_number(this)\" size=\"8\"  style=\"width: 60px\"></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"-\"  maxlength=\"50\"  size=\"20\"  style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"\" onBlur=\"Validate_Telephone_Number(this,3)\"  maxlength=\"30\" size=\"20\" style=\"width: 115px\" style=\"width: 123px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			out.println("}");
			
			out.println("else { ");
			out.println("  e_txt_nonrelated_c.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"\"  maxlength=\"100\" size=\"22\" style=\"width: 120px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value=\"\"  maxlength=\"50\" size=\"10\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+j+' value=\"\"  maxlength=\"3\" onblur=\"check_number(this)\" size=\"8\"  style=\"width: 60px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"-\"  maxlength=\"50\"  size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"\"  onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			out.println("}");
			//out.println(" lineno_nonrelated=j; ");
			out.println("continue;");
			out.println("}");
			
			out.println("if(type=='I') { ");
			//out.println("alert('write data '+type);");
			out.println("e_txt_nonrelated.innerHTML +='<table border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"'+array_name[j]+'\"  maxlength=\"100\" size=\"22\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value=\"'+array_relationship[j]+'\"  maxlength=\"50\" size=\"10\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+j+' value=\"'+array_period[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"3\" size=\"8\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"'+array_design[j]+'\"  maxlength=\"50\"  size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"'+array_telno_home[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"'+array_telno_office[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"'+array_mobileno[j]+'\" onBlur=\"Validate_Telephone_Number(this,3)\"  maxlength=\"30\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			out.println("else { ");
			out.println("e_txt_nonrelated_c.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+j+' value=\"'+array_name[j]+'\"  maxlength=\"100\" size=\"22\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+j+' value=\"'+array_relationship[j]+'\"  maxlength=\"50\" size=\"10\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+j+' value=\"'+array_period[j]+'\"   onblur=\"check_number(this)\"  maxlength=\"3\" size=\"8\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+j+' value=\"'+array_design[j]+'\"  maxlength=\"50\"  size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+j+' value=\"'+array_telno_home[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+j+' value=\"'+array_telno_office[j]+'\" onBlur=\"Validate_Telephone_Number(this,3)\"  maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+j+' value=\"'+array_mobileno[j]+'\" onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+j+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+j+'\",\"'+type+'\")>'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("}");
			out.println(" lineno_nonrelated=j; ");
			out.println("}");
			
			
			out.println("function del_row_ba(rowNo){"); 
			//out.println("alert('customer row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("if(arr_size_ba !=1 ){");
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
			
			out.println("m_activity=\"TXT_ACTIVITY_BA\"+rowNo");	
			out.println("if(document.Form1.elements[m_activity].value != '' ) { ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("  lineno_ba = lineno_ba-1;");
			out.println("  arr_size_ba = arr_size_ba-1;");
			out.println("  write_data_ba(arr_size_ba);");
			out.println(" }");
			out.println("}");
			out.println("else { ");
			out.println("  lineno_ba = lineno_ba-1;");
			out.println("  arr_size_ba = arr_size_ba-1;");
			out.println("  write_data_ba(arr_size_ba);");
			out.println(" }");
			out.println("}");
			out.println("}");
			
			
			out.println("function disable_rows_ba(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_cat=\"TXT_CAT_TYPE_CODE_BA\"+i");
			out.println("m_activity=\"TXT_ACTIVITY_BA\"+i");
			out.println("m_but_ba=\"BUT_BA_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_cat].disabled=true;");                           
			out.println("   document.Form1.elements[m_activity].disabled=true;");	
			out.println("   document.Form1.elements[m_but_ba].disabled=true;");		
			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_BA.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_ba(size){");
			//out.println("alert('arr_size_customer : '+size);");
			out.println("e_txt_ba.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if(array_activity[j]==\"\"){");
			
			out.println("e_txt_ba.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"15%\">'+ ");
			out.println("'<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			out.println("'<OPTION value=\"OTHER\"          >Other </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"79%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value=\"\" style=\"width:700px;\" maxlength=\"200\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("continue;");
			out.println("}");
			
			out.println(" if( array_cat[j] ==\"PRIME\" ){ ");
			out.println(" e_txt_ba.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println(" '<td WIDTH=\"15%\">'+ ");
			out.println(" '<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			out.println(" '<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			out.println(" '<OPTION value=\"OTHER\"          >Other </option>'+");
			out.println(" '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
			out.println(" '<TD WIDTH=\"79%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value=\"'+array_activity[j].replace('$','&')+'\"  style=\"width:700px;\" maxlength=\"200\"></TD>'+");
			out.println(" '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			out.println(" '</td></tr></table>';");
			out.println(" }");
			out.println(" else if( array_cat[j] ==\"OTHER\" ){ ");
			out.println(" e_txt_ba.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println(" '<td WIDTH=\"15%\">'+ ");
			out.println(" '<select name=TXT_CAT_TYPE_CODE_BA'+j+' class=\"txt_input\" >'+");
			out.println(" '<OPTION value=\"PRIME\"          >Prime </option>'+");
			out.println(" '<OPTION value=\"OTHER\" SELECTED >Other </option>'+");
			out.println(" '</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
			out.println(" '<TD WIDTH=\"79%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITY_BA'+j+' value=\"'+array_activity[j].replace('$','&')+'\"  style=\"width:700px;\" maxlength=\"200\"></TD>'+");
			out.println(" '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+j+' value=\"Delete\" onClick=\"del_row_ba('+j+')\">'+");
			out.println(" '</td></tr></table>';");
			out.println(" }");
			
			out.println("}");
			out.println(" lineno_ba=j; ");
			out.println("}");
			
			
			//Corporate
			//To delete company directors row
			out.println("function del_row_company_dir(rowNo){"); 
			//out.println("alert(' row No =' +rowNo)");
			//out.println("alert('lineno bank -' +lineno_bank)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_company;i++){");
			
			out.println("m_name=\"TXT_NAME_DIR\"+i");
			out.println("m_dir_add=\"TXT_ADDRESS_DIR\"+i"); //added by nuwan de silva on 07-09-07 ref no:851
			out.println("m_nicno=\"TXT_NIC_NO_DIR\"+i");
			out.println("m_stake=\"TXT_STAKE\"+i");
			out.println("m_no_shares=\"TXT_NO_OF_SHARES\"+i");
			out.println("m_value=\"TXT_VALUE\"+i");
			out.println("m_position=\"TXT_POSITION\"+i");
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('designation - :  ' +document.Form1.elements[m_design].value)");
			
			out.println("array_name_dir[j]=document.Form1.elements[m_name].value;");
			out.println("array_add_dir[j]=document.Form1.elements[m_dir_add].value;"); //added by nuwan de silva on 07-09-07 ref no:851
			out.println("array_nic_no_dir[j]=document.Form1.elements[m_nicno].value;");
			out.println("array_stake[j]=document.Form1.elements[m_stake].value;");
			out.println("array_no_shares[j]=document.Form1.elements[m_no_shares].value;");
			out.println("array_value[j]=document.Form1.elements[m_value].value;");
			out.println("array_position[j]=document.Form1.elements[m_position].value;");
			
			
			out.println("j=j+1;");
			out.println("}");
			
			out.println("m_name=\"TXT_NAME_DIR\"+rowNo");
			out.println("m_dir_add=\"TXT_ADDRESS_DIR\"+rowNo"); //added by nuwan de silva on 07-09-07 ref no:851
			out.println("m_nicno=\"TXT_NIC_NO_DIR\"+rowNo");
			out.println("m_stake=\"TXT_STAKE\"+rowNo");
			out.println("m_no_shares=\"TXT_NO_OF_SHARES\"+rowNo");
			out.println("m_value=\"TXT_VALUE\"+rowNo");
			out.println("m_position=\"TXT_POSITION\"+rowNo");
			
			out.println(" if( document.Form1.elements[m_name].value != '' || document.Form1.elements[m_dir_add].value != '' || document.Form1.elements[m_nicno].value != '' || document.Form1.elements[m_stake].value != '' || document.Form1.elements[m_no_shares].value != '' || document.Form1.elements[m_value].value != '' ) {");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("  lineno_company_dir = lineno_company_dir-1;");
			out.println("  arr_size_company = lineno_company_dir;");
			out.println("  write_data_company_dir(arr_size_company);");
			out.println(" }");
			out.println(" }");
			out.println(" else { ");
			out.println("  lineno_company_dir = lineno_company_dir-1;");
			out.println("  arr_size_company = lineno_company_dir;");
			out.println("  write_data_company_dir(arr_size_company);");
			out.println(" }");
			out.println("}");
			
			out.println("function disable_rows_company(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_name=\"TXT_NAME_DIR\"+i");
			out.println("m_dir_add=\"TXT_ADDRESS_DIR\"+i"); //added by nuwan de silva on 07-09-07 ref no:851
			out.println("m_nicno=\"TXT_NIC_NO_DIR\"+i");
			out.println("m_stake=\"TXT_STAKE\"+i");
			out.println("m_no_shares=\"TXT_NO_OF_SHARES\"+i");
			out.println("m_value=\"TXT_VALUE\"+i");
			out.println("m_position=\"TXT_POSITION\"+i");
			out.println("m_del_but_com=\"BUT_COMP_DEL\"+i");	
			
			//out.println("alert('del but '+m_del_but_com);");
			out.println("   document.Form1.elements[m_name].disabled=true;");                           
			out.println("   document.Form1.elements[m_dir_add].disabled=true;"); //added by nuwan de silva on 07-09-07 ref no:851                          
			out.println("   document.Form1.elements[m_nicno].disabled=true;");	
			out.println("   document.Form1.elements[m_stake].disabled=true;");
			out.println("   document.Form1.elements[m_no_shares].disabled=true;");	
			out.println("   document.Form1.elements[m_value].disabled=true;");
			out.println("   document.Form1.elements[m_position].disabled=true;");
			out.println("   document.Form1.elements[m_del_but_com].disabled=true;");	
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_COM.disabled=true;");	
			out.println("} ");
			
			
			
			//comment by nuwan de silva on 07-09-07------------------------
			/*  out.println("function write_data_company_dir(size){");
                //out.println("alert('arr_size_guarantor '+size);");
                out.println("e_txt_company_directors.innerHTML=\"\";");
        //out.println("header();");
          out.println(" for(var j=0;j<size;j++){");
    
            
              out.println("if(array_name_dir[j]==\"\" && array_nic_no_dir[j]==\"\" && array_stake[j]==\"\" && array_no_shares[j]==\"\" && array_value[j]==\"\" && array_position[j]==\"\"){");
                
                out.println("e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
              out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+j+' value=\"\"  maxlength=\"100\" size=\"40\" style=\"width: 220px\" ></TD>'+");
            out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value=\"\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+j+' value=\"\" onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\"></TD>'+");
              out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value=\"\" onblur=\"check_number(this)\"   maxlength=\"20\" size=\"15\" style=\"width: 80px\" ></TD>'+");
              out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+j+' value=\"\"  onblur=\"check_number_decimal(this,20)\"  maxlength=\"25\" size=\"30\" style=\"width: 150px\" ></TD>'+");
                out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+j+' value=\"\"  maxlength=\"50\" size=\"34\" style=\"width: 195px\" ></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+j+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+");
              out.println("'</td></tr></table>';");


        out.println("continue;");
                out.println("}");
                
                out.println("e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
              out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+j+' value=\"'+array_name_dir[j]+'\"  maxlength=\"100\" size=\"40\" style=\"width: 220px\"></TD>'+");
            out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value=\"'+array_nic_no_dir[j]+'\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+j+' value=\"'+array_stake[j]+'\" onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\" ></TD>'+");
              out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value=\"'+array_no_shares[j]+'\" onblur=\"check_number(this)\" maxlength=\"20\" size=\"15\" style=\"width: 80px\" ></TD>'+");
              out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+j+' value=\"'+array_value[j]+'\" onblur=\"check_number_decimal(this,20)\" maxlength=\"25\" size=\"30\" style=\"width: 150px\"></TD>'+");
                out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+j+' value=\"'+array_position[j]+'\" maxlength=\"50\" size=\"34\" style=\"width: 195px\" ></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+j+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+");
              out.println("'</td></tr></table>';");

              out.println("}");
                out.println(" lineno_company_dir=j; ");
              out.println("}");
                */
			
			
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
			
			out.println("m_name=\"TXT_NAME_SUB\"+rowNo");
			out.println("m_stake=\"TXT_STAKE_SUB\"+rowNo");
			out.println("m_value=\"TXT_VALUE_SUB\"+rowNo");
			out.println("m_telno=\"TXT_TEL_NO_SUB\"+rowNo");
			out.println("m_officer=\"TXT_OFFICER_SUB\"+rowNo");
			out.println("m_activities=\"TXT_ACTIVITIES_SUB\"+rowNo");
			
			out.println(" if(document.Form1.elements[m_name].value != '' || document.Form1.elements[m_stake].value != '' || document.Form1.elements[m_value].value != '' || document.Form1.elements[m_telno].value != '' || document.Form1.elements[m_officer].value != '' ) { ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println(" lineno_subsidiaries = lineno_subsidiaries-1;");
			out.println(" arr_size_subsidiaries = lineno_subsidiaries;");
			out.println(" write_data_subsidiaries(arr_size_subsidiaries);");
			out.println(" }");
			out.println(" }");
			out.println(" else {");
			out.println(" lineno_subsidiaries = lineno_subsidiaries-1;");
			out.println(" arr_size_subsidiaries = lineno_subsidiaries;");
			out.println(" write_data_subsidiaries(arr_size_subsidiaries);");
			out.println(" }");
			
			out.println("}");
			
			out.println("function disable_rows_sub(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_name=\"TXT_NAME_SUB\"+i");
			out.println("m_stake=\"TXT_STAKE_SUB\"+i");
			out.println("m_value=\"TXT_VALUE_SUB\"+i");
			out.println("m_telno=\"TXT_TEL_NO_SUB\"+i");
			out.println("m_officer=\"TXT_OFFICER_SUB\"+i");
			out.println("m_activities=\"TXT_ACTIVITIES_SUB\"+i");
			out.println("m_but_sub_del=\"BUT_SUB_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_name].disabled=true;");                           
			out.println("   document.Form1.elements[m_stake].disabled=true;");	
			out.println("   document.Form1.elements[m_value].disabled=true;");
			out.println("   document.Form1.elements[m_telno].disabled=true;");	
			out.println("   document.Form1.elements[m_officer].disabled=true;");
			out.println("   document.Form1.elements[m_activities].disabled=true;");
			out.println("   document.Form1.elements[m_but_sub_del].disabled=true;");	
			
			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_SUB.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_subsidiaries(size){");
			//out.println("alert('arr_size_guarantor '+size);");
			out.println("e_txt_subsidiaries.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if(array_name_sub[j]==\"\" && array_stake_sub[j]==\"\" && array_value_sub[j]==\"\" && array_telno_sub[j]==\"\" && array_officer_sub[j]==\"\" && array_ba_sub[j]==\"\"){");
			
			out.println("e_txt_subsidiaries.innerHTML +='<table border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_SUB'+j+' value=\"\" maxlength=\"100\" size=\"40\"></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE_SUB'+j+' onblur=\"check_number_precent(this,6)\"  value=\"\" maxlength=\"6\" size=\"7\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_SUB'+j+' onblur=\"check_number_decimal(this,20)\" value=\"\" maxlength=\"20\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_SUB'+j+' value=\"\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICER_SUB'+j+' value=\"\" maxlength=\"20\" size=\"15\" style=\"width: 80px\"></TD>'+");
			out.println("'<TD WIDTH=\"21%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITIES_SUB'+j+' value=\"\" maxlength=\"100\" size=\"31\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+j+' value=\"Delete\" onClick=\"del_row_subsidiaries('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			
			out.println("continue;");
			out.println("}");
			
			out.println("e_txt_subsidiaries.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_SUB'+j+' value=\"'+array_name_sub[j]+'\"  maxlength=\"100\" size=\"40\"></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE_SUB'+j+' onblur=\"check_number_precent(this,6)\"  value=\"'+array_stake_sub[j]+'\"  maxlength=\"6\" size=\"7\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_SUB'+j+' onblur=\"check_number_decimal(this,20)\" value=\"'+array_value_sub[j]+'\"  maxlength=\"20\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_SUB'+j+'  value=\"'+array_telno_sub[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICER_SUB'+j+'  value=\"'+array_officer_sub[j]+'\"  maxlength=\"20\" style=\"width: 80px\" size=\"15\"></TD>'+");
			out.println("'<TD WIDTH=\"21%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITIES_SUB'+j+'  value=\"'+array_ba_sub[j]+'\" maxlength=\"100\" size=\"31\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+j+' value=\"Delete\" onClick=\"del_row_subsidiaries('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("}");
			out.println("lineno_subsidiaries = j;");
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
			
			out.println("m_name=\"TXT_CUSTOMER_NAME\"+rowNo");
			out.println("m_type=\"TXT_TYPE_C\"+rowNo");
			out.println("m_add=\"TXT_ADDRESS_CUS\"+rowNo");
			out.println("m_relation=\"TXT_RELATIONSHIP_CUS\"+rowNo");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON_CUS\"+rowNo");
			out.println("m_telno=\"TXT_TEL_NO_CUS\"+rowNo");	
			
			out.println(" if(document.Form1.elements[m_name].value != '' || document.Form1.elements[m_add].value != '' || document.Form1.elements[m_relation].value != '' || document.Form1.elements[m_contact_p].value != '' ) { ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println(" lineno_customer = lineno_customer-1;");
			out.println(" arr_size_customer = lineno_customer;");
			out.println(" write_data_customer(arr_size_customer);");
			out.println(" }");
			out.println(" }");
			out.println(" else {");
			out.println(" lineno_customer = lineno_customer-1;");
			out.println(" arr_size_customer = lineno_customer;");
			out.println(" write_data_customer(arr_size_customer);");
			out.println("}");
			out.println("}");
			
			out.println("function disable_rows_cus(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_name=\"TXT_CUSTOMER_NAME\"+i");
			out.println("m_type=\"TXT_TYPE_C\"+i");
			out.println("m_add=\"TXT_ADDRESS_CUS\"+i");
			out.println("m_relation=\"TXT_RELATIONSHIP_CUS\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON_CUS\"+i");
			out.println("m_telno=\"TXT_TEL_NO_CUS\"+i");	
			out.println("m_but_cus=\"BUT_CUS_DEL\"+i");		
			
			out.println("   document.Form1.elements[m_name].disabled=true;");                           
			out.println("   document.Form1.elements[m_type].disabled=true;");	
			out.println("   document.Form1.elements[m_add].disabled=true;");
			out.println("   document.Form1.elements[m_relation].disabled=true;");	
			out.println("   document.Form1.elements[m_contact_p].disabled=true;");
			out.println("   document.Form1.elements[m_telno].disabled=true;");
			out.println("   document.Form1.elements[m_but_cus].disabled=true;");	
			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_CUS.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_customer(size){");
			//out.println("alert('arr_size_customer : '+size);");
			out.println("e_txt_customer.innerHTML=\"\";");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if(array_name_cus[j]==\"\" && array_add_cus[j]==\"\" && array_relation_cus[j]==\"\" && array_contact_person_cus[j]==\"\" && array_telno_cus[j]==\"\"){");
			
			out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value=\"\" maxlength=\"200\" size=\"36\"></TD>'+");
			out.println("'<td WIDTH=\"9%\">'+ ");
			out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"CUSTOMER\" SELECTED >Customer </option>'+");
			out.println("'<OPTION value=\"SUPPLIER\" >Supplier </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value=\"\"  maxlength=\"200\" size=\"30\"></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'   value=\"\"  onblur=\"check_number(this)\" style=\"width: 100px\"  maxlength=\"3\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value=\"\" maxlength=\"200\" size=\"22\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value=\"\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"19\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			
			out.println("continue;");
			out.println("}");
			
			out.println("if(array_type_cus[j]==\"CUSTOMER\") {");
			out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");		 							
			out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value=\"'+array_name_cus[j]+'\"  maxlength=\"200\" size=\"36\"></TD>'+");
			out.println("'<td WIDTH=\"9%\">'+ ");
			out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"CUSTOMER\" SELECTED >Customer </option>'+");
			out.println("'<OPTION value=\"SUPPLIER\" >Supplier </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value=\"'+array_add_cus[j]+'\"  maxlength=\"200\" size=\"30\"></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'  value=\"'+array_relation_cus[j]+'\"  onblur=\"check_number(this)\" style=\"width: 100px\"  maxlength=\"3\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value=\"'+array_contact_person_cus[j]+'\"  maxlength=\"200\" size=\"22\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value=\"'+array_telno_cus[j]+'\"  maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"19\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			out.println("else {");
			out.println("e_txt_customer.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");		 							
			out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CUSTOMER_NAME'+j+' value=\"'+array_name_cus[j]+'\"  maxlength=\"200\" size=\"36\"></TD>'+");
			out.println("'<td WIDTH=\"9%\">'+ ");
			out.println("'<select name=TXT_TYPE_C'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"CUSTOMER\"  >Customer </option>'+");
			out.println("'<OPTION value=\"SUPPLIER\" SELECTED >Supplier </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_CUS'+j+' value=\"'+array_add_cus[j]+'\"  maxlength=\"200\" size=\"30\"></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+j+'  value=\"'+array_relation_cus[j]+'\"  onblur=\"check_number(this)\" style=\"width: 100px\"  maxlength=\"3\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+j+'  value=\"'+array_contact_person_cus[j]+'\"  maxlength=\"200\" size=\"22\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_CUS'+j+' value=\"'+array_telno_cus[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"19\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+j+' value=\"Delete\" onClick=\"del_row_customer('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("}");
			out.println("lineno_customer=j;");
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
			
			out.println("m_institute=\"TXT_INSTITUTION\"+rowNo");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+rowNo");
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+rowNo");
			out.println("m_equip=\"TXT_EQUIPMENT\"+rowNo");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+rowNo");
			out.println("m_rental=\"TXT_MONTHLY_RENTAL\"+rowNo");
			out.println("m_months=\"TXT_MONTHS\"+rowNo");;
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+rowNo");
			
			out.println(" if(document.Form1.elements[m_institute].value != '' || document.Form1.elements[m_contact_p].value != '' || document.Form1.elements[m_equip].value != '' || document.Form1.elements[m_app_amount].value != '' || document.Form1.elements[m_rental].value !='' || document.Form1.elements[m_months].value != '') {");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println(" lineno_credit_c = lineno_credit_c-1;");
			out.println(" arr_size_credit_c = arr_size_credit_c-1;");
			out.println(" write_data_credit_c(arr_size_credit_c);");
			out.println(" }");
			out.println(" }");
			out.println(" else {");
			out.println(" lineno_credit_c = lineno_credit_c-1;");
			out.println(" arr_size_credit_c = arr_size_credit_c-1;");
			out.println(" write_data_credit_c(arr_size_credit_c);");
			out.println(" }");
			out.println("}");
			
			out.println("function disable_rows_credit_c(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_institute=\"TXT_INSTITUTION\"+i");
			out.println("m_contact_p=\"TXT_CONTACT_PERSON\"+i");
			out.println("m_type=\"TXT_TYPE_OF_FACILITY\"+i");
			out.println("m_equip=\"TXT_EQUIPMENT\"+i");
			out.println("m_app_amount=\"TXT_APPROVED_AMOUNT\"+i");
			out.println("m_rental=\"TXT_MONTHLY_RENTAL\"+i");
			out.println("m_months=\"TXT_MONTHS\"+i");;
			out.println("m_bal_amount=\"TXT_BALANCE_AMOUNT\"+i");
			out.println("m_del_but_credit=\"BUT_CREDIT_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_institute].disabled=true;");                           
			out.println("   document.Form1.elements[m_contact_p].disabled=true;");	
			out.println("   document.Form1.elements[m_type].disabled=true;");
			out.println("   document.Form1.elements[m_equip].disabled=true;");	
			out.println("   document.Form1.elements[m_app_amount].disabled=true;");
			out.println("   document.Form1.elements[m_rental].disabled=true;");
			out.println("   document.Form1.elements[m_months].disabled=true;");
			out.println("   document.Form1.elements[m_bal_amount].disabled=true;");	
			out.println("   document.Form1.elements[m_del_but_credit].disabled=true;");		
			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_CREDIT_C.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_credit_c(size){");
			//out.println("alert('arr_size_credit corp : '+size);");
			out.println("e_txt_credit_c.innerHTML=\"\";");
			out.println(" for(var j=0;j<size;j++){");
			
			
			out.println("if( array_institute_c[j]==\"\" && array_contact_person_c[j]==\"\" && array_equip_c[j]==\"\" && array_app_amount_c[j]==\"\" && array_rental[j]==\"\" && array_months_c[j]==\"\"  && array_bal_amount_c[j]==\"\" ){");
			
			out.println(" e_txt_credit_c.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println(" '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"\" maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println(" '<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"\" maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println(" '<td WIDTH=\"11%\">'+ ");
			out.println(" '<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" SELECTED>Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println(" '</SELECT></td>'+");
			out.println(" '<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"\" maxlength=\"50\" size=\"20\"  style=\"width: 110px\" ></TD>'+");
			out.println(" '<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println(" '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"\" onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\"  style=\"width: 60px\" ></TD>'+");
			out.println(" '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"\" onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\"></TD>'+");
			out.println(" '<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"\" onblur=\"check_number_decimal(this,21)\"  maxlength=\"21\" style=\"width: 85px\"  ></TD>'+");
			out.println(" '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println(" '</td></tr></table>';");
			
			
			out.println("continue;");
			out.println("}");
			
			out.println(" if( array_type_c[j] ==\"TERM LOAN\" ){ ");
			out.println("  ");
			
			out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" SELECTED >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" >Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"'+array_equip_c[j]+'\"  maxlength=\"50\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\"  maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"'+array_rental[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months_c[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println(" }");
			
			out.println(" else if( array_type_c[j] ==\"LEASE\" ){ ");
			out.println("   ");
			out.println("e_txt_credit_c.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" SELECTED >Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"'+array_equip_c[j]+'\"  maxlength=\"50\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\"  maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"'+array_rental[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months_c[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println(" }");
			
			out.println(" else if( array_type_c[j] ==\"OVER DRAFT\" ){ ");
			out.println("  ");
			out.println("e_txt_credit_c.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" >Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" SELECTED >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"'+array_equip_c[j]+'\"  maxlength=\"50\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount_c[j]+'\"  onblur=\"check_number_decimal(this,22)\"  maxlength=\"22\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"'+array_rental[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months_c[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount_c[j]+'\"  onblur=\"check_number_decimal(this,22)\" maxlength=\"22\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println(" }");
			
			out.println(" else if( array_type_c[j] ==\"LC FACILITY\" ){ ");
			out.println("  ");
			out.println("e_txt_credit_c.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute_c[j]+'\"  maxlength=\"100\" size=\"20\"  style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" >Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" SELECTED >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"'+array_equip_c[j]+'\"  maxlength=\"50\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\"  maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"'+array_rental[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months_c[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("  }");
			
			out.println(" else if( array_type_c[j] ==\"PLEDGE LOAN\" ){ ");
			out.println("  ");
			out.println("e_txt_credit_c.innerHTML +='<table  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+j+' value=\"'+array_institute_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+j+' value=\"'+array_contact_person_c[j]+'\"  maxlength=\"100\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+j+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" >Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" SELECTED >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+j+' value=\"'+array_equip_c[j]+'\"  maxlength=\"50\" size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+j+' value=\"'+array_app_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\"  maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");// Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+j+' value=\"'+array_rental[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"25\"  size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+j+' value=\"'+array_months_c[j]+'\"  onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+j+' value=\"'+array_bal_amount_c[j]+'\"  onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+j+' value=\"Delete\" onClick=\"del_row_credit_c('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("  }");
			
			out.println("}");
			out.println("lineno_credit_c=j;");
			out.println("}");
			
			
			out.println("function del_row_family(rowNo){"); 
			//out.println("alert(' row No fam =' +rowNo)");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_family;i++){");
			
			out.println("m_mem=\"TXT_MEMBER\"+i");
			out.println("m_name=\"TXT_NAME_F\"+i");
			out.println("m_add=\"TXT_ADDRESS1_F\"+i");
			out.println("m_age=\"TXT_AGE_F\"+i");
			out.println("m_telno=\"TXT_TELEPHONE_NO_F\"+i");
			out.println("m_mobile=\"TXT_MOBILE_NO_F\"+i");
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			//out.println("alert('member age - :  ' +document.Form1.elements[m_age].value)");		
			
			out.println("array_member_f[j]=document.Form1.elements[m_mem].value;");
			out.println("array_name_f[j]=document.Form1.elements[m_name].value;");
			out.println("array_address_f[j]=document.Form1.elements[m_add].value;");
			out.println("array_age_f[j]=document.Form1.elements[m_age].value;");
			out.println("array_telno_f[j]=document.Form1.elements[m_telno].value;");
			out.println("array_moble_f[j]=document.Form1.elements[m_mobile].value;");
			
			out.println("j=j+1;");
			out.println("}");
			
			out.println("m_name=\"TXT_NAME_F\"+rowNo");
			out.println("m_add=\"TXT_ADDRESS1_F\"+rowNo");
			out.println("m_age=\"TXT_AGE_F\"+rowNo");
			out.println("m_telno=\"TXT_TELEPHONE_NO_F\"+rowNo");
			out.println("m_mobile=\"TXT_MOBILE_NO_F\"+rowNo");
			
			out.println("if( document.Form1.elements[m_name].value != '' || document.Form1.elements[m_add].value != '' || document.Form1.elements[m_age].value != '' || document.Form1.elements[m_telno].value != '' ) { ");
			out.println(" if(confirm('Are you sure you want to delete ?')){ "); 
			out.println("		lineno_family = lineno_family-1;");
			out.println("		arr_size_family = lineno_family;");
			out.println("		write_data_family(arr_size_family);");
			out.println("	}");
			out.println("}");
			out.println(" else { ");
			out.println("		lineno_family = lineno_family-1;");
			out.println("		arr_size_family = lineno_family;");
			out.println("		write_data_family(arr_size_family);");
			out.println(" }");
			out.println("}");
			
			out.println("function disable_rows_fam(no)");
			out.println("{");
			out.println("for(var i=0;i<no;i++)");
			out.println("{	");
			
			out.println("m_mem=\"TXT_MEMBER\"+i");
			out.println("m_name=\"TXT_NAME_F\"+i");
			out.println("m_add=\"TXT_ADDRESS1_F\"+i");
			out.println("m_age=\"TXT_AGE_F\"+i");
			out.println("m_telno=\"TXT_TELEPHONE_NO_F\"+i");
			out.println("m_mobile=\"TXT_MOBILE_NO_F\"+i");	
			out.println("m_del_but_fam=\"BUT_FAM_DEL\"+i");	
			
			out.println("   document.Form1.elements[m_mem].disabled=true;");                           
			out.println("   document.Form1.elements[m_name].disabled=true;");	
			out.println("   document.Form1.elements[m_add].disabled=true;");
			out.println("   document.Form1.elements[m_age].disabled=true;");	
			out.println("   document.Form1.elements[m_telno].disabled=true;");
			out.println("   document.Form1.elements[m_mobile].disabled=true;");
			out.println("   document.Form1.elements[m_del_but_fam].disabled=true;");
			
			out.println("}	");
			out.println("   document.Form1.BUT_ADD_FAM.disabled=true;");	
			out.println("} ");
			
			
			out.println("function write_data_family(size){");
			//out.println("alert('arr_size_guarantor '+size);");
			out.println("e_txt_family_members.innerHTML=\"\";");
			//out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
			
			out.println("if(array_name_f[j]==\"\" && array_address_f[j]==\"\" && array_age_f[j]==\"\" && array_telno_f[j]==\"\" && array_moble_f[j]==\"\"){");
			
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"\" maxlength=\"200\" size=\"30\" style=\"width: 170px\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"\" maxlength=\"200\" size=\"40\" style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"\" maxlength=\"3\" size=\"4\" onblur=\"check_number(this)\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' onBlur=\"Validate_Telephone_Number(this,1)\"  value=\"\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' onBlur=\"Validate_Telephone_Number(this,3)\" value=\"\" maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("continue;");
			out.println("}");
			
			out.println(" else if(array_member_f[j]==\"MOTHER\"){");
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"'+array_name_f[j]+'\"  maxlength=\"200\" size=\"30\" style=\"width: 170px\" ></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"'+array_address_f[j]+'\"  maxlength=\"200\" size=\"40\"  style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"'+array_age_f[j]+'\" maxlength=\"3\" size=\"4\" onblur=\"check_number(this)\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"'+array_telno_f[j]+'\" onBlur=\"Validate_Telephone_Number(this,1)\"  maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"'+array_moble_f[j]+'\" onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("else if(array_member_f[j]==\"FATHER\"){");
			
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" SELECTED>Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"'+array_name_f[j]+'\"  maxlength=\"200\" size=\"30\" style=\"width: 170px\" ></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"'+array_address_f[j]+'\"  maxlength=\"200\" size=\"40\" style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"'+array_age_f[j]+'\"  onblur=\"check_number(this)\"  maxlength=\"3\" size=\"4\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"'+array_telno_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"'+array_moble_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("else if(array_member_f[j]==\"BROTHER\"){");
			
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" SELECTED>Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"'+array_name_f[j]+'\"  maxlength=\"200\" size=\"30\" style=\"width: 170px\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"'+array_address_f[j]+'\"  maxlength=\"200\" size=\"40\" style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"'+array_age_f[j]+'\" maxlength=\"3\" size=\"4\"  onblur=\"check_number(this)\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"'+array_telno_f[j]+'\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"'+array_moble_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("else if(array_member_f[j]==\"SISTER\"){");
			
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" SELECTED>Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"'+array_name_f[j]+'\"  maxlength=\"200\" size=\"30\" style=\"width: 170px\" ></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"'+array_address_f[j]+'\"  maxlength=\"200\" size=\"40\" style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"'+array_age_f[j]+'\"  maxlength=\"3\" size=\"4\" onblur=\"check_number(this)\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"'+array_telno_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"'+array_moble_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("else if(array_member_f[j]==\"SPOUSE\"){");
			
			out.println("e_txt_family_members.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+j+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" >Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" SELECTED >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+j+' value=\"'+array_name_f[j]+'\"  maxlength=\"200\" size=\"30\" style=\"width: 170px\" ></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+j+' value=\"'+array_address_f[j]+'\" maxlength=\"200\" size=\"40\"  style=\"width: 215px\" ></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+j+' value=\"'+array_age_f[j]+'\"  maxlength=\"3\" size=\"4\" onblur=\"check_number(this)\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+j+' value=\"'+array_telno_f[j]+'\"  onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+j+' value=\"'+array_moble_f[j]+'\" onBlur=\"Validate_Telephone_Number(this,3)\"  maxlength=\"30\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+j+' value=\"Delete\" onClick=\"del_row_family('+j+')\">'+");
			out.println("'</td></tr></table>';");
			out.println("}");
			
			out.println("}");
			out.println(" lineno_family=j; ");
			out.println("}");
			
			
			
			out.println("function check_dates(){"); 
			
			//out.println(" alert('lineno '+lineno);");
			out.println("if(lineno !=0) {");
			out.println(" count=lineno-1;  ");
			out.println("m_from_date_dd=\"TXT_FROM_DATE_DD\"+count");
			out.println("m_from_date_mm=\"TXT_FROM_DATE_MM\"+count");
			out.println("m_from_date_yy=\"TXT_FROM_DATE_YY\"+count");
			
			out.println("m_to_date_dd=\"TXT_TO_DATE_DD\"+count");
			out.println("m_to_date_mm=\"TXT_TO_DATE_MM\"+count");
			out.println("m_to_date_yy=\"TXT_TO_DATE_YY\"+count");
			
			out.println("if(document.Form1.TXT_DATE_OF_BIRTH_DD.value!=\"\" || document.Form1.TXT_DATE_OF_BIRTH_MM.value!=\"\" || document.Form1.TXT_DATE_OF_BIRTH_YY.value!=\"\") {");
			//out.println(" alert(' inside '); ");
			out.println(" if(!checkMonthLength(document.Form1.TXT_DATE_OF_BIRTH_DD,document.Form1.TXT_DATE_OF_BIRTH_MM,document.Form1.TXT_DATE_OF_BIRTH_YY)){  "); 
			out.println("  return false;"); 
			out.println(" }");
			out.println(" else if(document.Form1.elements[m_from_date_dd].value!=\"\" || document.Form1.elements[m_from_date_mm].value!=\"\" || document.Form1.elements[m_from_date_yy].value!=\"\") {");
			out.println("		if(!checkMonthLength(document.Form1.elements[m_from_date_dd],document.Form1.elements[m_from_date_mm],document.Form1.elements[m_from_date_yy])){  "); 
			out.println("			return false;"); 
			out.println("		}");
			out.println("		else if(document.Form1.elements[m_to_date_dd].value!=\"\" || document.Form1.elements[m_to_date_mm].value!=\"\" || document.Form1.elements[m_to_date_yy].value!=\"\") {");
			out.println("			if(!checkMonthLength(document.Form1.elements[m_to_date_dd],document.Form1.elements[m_to_date_mm],document.Form1.elements[m_to_date_yy])){  "); 
			out.println("				return false;"); 
			out.println("			}");
			out.println("			else {  "); 
			out.println("				return true;"); 
			out.println("			}");
			out.println("		}");
			out.println(" }");
			out.println("}");
			out.println("if(document.Form1.elements[m_from_date_dd].value!=\"\" || document.Form1.elements[m_from_date_mm].value!=\"\" || document.Form1.elements[m_from_date_yy].value!=\"\") {");
			out.println("		if(!checkMonthLength(document.Form1.elements[m_from_date_dd],document.Form1.elements[m_from_date_mm],document.Form1.elements[m_from_date_yy])){  "); 
			out.println("			return false;"); 
			out.println("		}");
			out.println("		else if(document.Form1.elements[m_to_date_dd].value!=\"\" || document.Form1.elements[m_to_date_mm].value!=\"\" || document.Form1.elements[m_to_date_yy].value!=\"\") {");
			out.println("			if(!checkMonthLength(document.Form1.elements[m_to_date_dd],document.Form1.elements[m_to_date_mm],document.Form1.elements[m_to_date_yy])){  "); 
			out.println("				return false;"); 
			out.println("			}");
			out.println("			else {  "); 
			out.println("				return true;"); 
			out.println("			}");
			out.println("		}");
			out.println("}");
			out.println("if(document.Form1.elements[m_to_date_dd].value!=\"\" || document.Form1.elements[m_to_date_mm].value!=\"\" || document.Form1.elements[m_to_date_yy].value!=\"\") {");
			out.println("		if(!checkMonthLength(document.Form1.elements[m_to_date_dd],document.Form1.elements[m_to_date_mm],document.Form1.elements[m_to_date_yy])){  "); 
			out.println("			return false;"); 
			out.println("		}");
			out.println("		else {  "); 
			out.println("		return true;"); 
			out.println("		}");
			out.println("}");
			out.println("else{  "); 
			out.println("		return true;"); 
			out.println("}");
			out.println("}");
			out.println("else{  "); 
			out.println("		return true;"); 
			out.println("}");
			out.println("}");
			
			
			out.println("function add_row_emp(){"); 
			//out.println("alert('ok');");
			out.println("var b_flag=0;");
			out.println("if(lineno!=0){"); 
			out.println("count=lineno-1;");
			out.println("m_organization=\"TXT_ORGANIZATION\"+count");
			out.println("m_tel_no=\"TXT_TEL_NO\"+count");
			out.println("m_from_date_dd=\"TXT_FROM_DATE_DD\"+count");
			out.println("m_from_date_mm=\"TXT_FROM_DATE_MM\"+count");
			out.println("m_from_date_yy=\"TXT_FROM_DATE_YY\"+count");
			
			out.println("m_to_date_dd=\"TXT_TO_DATE_DD\"+count");
			out.println("m_to_date_mm=\"TXT_TO_DATE_MM\"+count");
			out.println("m_to_date_yy=\"TXT_TO_DATE_YY\"+count");
			
			out.println("m_designation=\"TXT_DESIGNATION2\"+count");
			
			out.println("if(document.Form1.elements[m_organization].value==\"\") {");
			out.println("alert('Organisation cannot be null ');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_tel_no].value==\"\") {");
			out.println("alert('Telephone number cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_from_date_dd].value==\"\" || document.Form1.elements[m_from_date_mm].value==\"\" || document.Form1.elements[m_from_date_yy].value==\"\") {");
			out.println("alert('From date cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(!checkMonthLength(document.Form1.elements[m_from_date_dd],document.Form1.elements[m_from_date_mm],document.Form1.elements[m_from_date_yy])){  "); 
			out.println("b_flag=1;"); 
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_to_date_dd].value!=\"\" || document.Form1.elements[m_to_date_mm].value!=\"\" || document.Form1.elements[m_to_date_yy].value!=\"\") {");
			out.println("if(!checkMonthLength(document.Form1.elements[m_to_date_dd],document.Form1.elements[m_to_date_mm],document.Form1.elements[m_to_date_yy])){  "); 
			out.println("b_flag=1;"); 
			out.println("}");
			out.println("}");
			
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("tmp_org=document.Form1.elements[m_organization].value;");
			
			out.println("}");	
			
			out.println("}");
			
			
			out.println("if(b_flag==0){");
			
			//out.println(" alert('lineno'+lineno);");
			out.println(" e_mode_emp.innerHTML +='<table  align=\"center\"  border=\"0\"  width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"30%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"200\" size=\"55\"  style=\"width:250px;\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO'+lineno+' onBlur=\"Validate_Telephone_Number(this,1)\" style=\"width:130px;\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD'+lineno+' maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM'+lineno+' maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY'+lineno+' maxlength=\"4\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD'+lineno+' maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM'+lineno+' maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY'+lineno+' maxlength=\"4\" size=\"4\" ></TD>'+");	
			out.println("'<TD WIDTH=\"19%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION2'+lineno+' maxlength=\"20\" size=\"35\" style=\"width:160px;\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_EMP_DEL'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("lineno=lineno+1;");
			
			//out.println(" alert('lineno'+lineno);");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("add_button();");
			out.println("}");
			
			
			
			out.println("function check_business_act(no){ ");
			out.println("m_activity=\"TXT_ACTIVITY_BA\"+no");
			out.println("if(document.Form1.elements[m_activity].value==\"\") {");
			out.println("alert('Business activity cannot be null ');");
			out.println("document.Form1.elements[m_activity].focus();");
			out.println("return false;");
			out.println("}");
			out.println("else ");
			out.println("return true;");
			out.println("}");			  
			
			out.println("function add_row_ba(){"); 
			//out.println("alert('ok');");
			out.println("var b_flag=0;");
			out.println("if(lineno_ba!=0){"); 
			out.println("count=lineno_ba-1;");
			out.println("m_cat_type=\"TXT_CAT_TYPE_CODE_BA\"+count");
			out.println("m_activity=\"TXT_ACTIVITY_BA\"+count");
			
			out.println("if(document.Form1.elements[m_activity].value==\"\") {");
			out.println("alert('Activity cannot be null ');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("}");
			
			out.println("}");
			
			out.println("if(b_flag==0){");
			//out.println(" alert('lineno'+lineno);");
			out.println("e_txt_ba.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"15%\">'+ ");
			out.println("'<select name=TXT_CAT_TYPE_CODE_BA'+lineno_ba+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"PRIME\" SELECTED >Prime </option>'+");
			out.println("'<OPTION value=\"OTHER\"          >Other </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_ORGANIZATION'+lineno+' maxlength=\"50\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"79%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITY_BA'+lineno_ba+' style=\"width:700px;\" maxlength=\"200\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BA_DEL'+lineno_ba+' value=\"Delete\" onClick=\"del_row_ba('+lineno_ba+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("lineno_ba=lineno_ba+1;");
			out.println("arr_size_ba=arr_size_ba+1;");
			out.println("}");
			out.println("add_button_ba();");
			out.println("}");
			
			out.println("function check_bank(no){ ");
			out.println("m_bank=\"TXT_BANK_CODE\"+no");
			out.println("m_branch=\"TXT_BRANCH_CODE\"+no");
			out.println("m_accno=\"TXT_ACCOUNT_NO\"+no");
			out.println("m_reference=\"TXT_REFERENCE\"+no");
			out.println("m_telno=\"TXT_TEL_NO2\"+no");
			out.println("m_faxno=\"TXT_FAX_NO\"+no");
			out.println("m_relationship=\"TXT_RELATIONSHIP2\"+no");
			
			out.println("if(document.Form1.elements[m_bank].value==\"\") {");
			out.println("alert('Banker cannot be null');");
			out.println(" document.Form1.elements[m_bank].focus(); ");
			out.println("return false;");
			out.println("}");
			
			// commented by udara 15-07-2019
			/*
			out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
			out.println("alert('Banch cannot be null');");
			out.println(" document.Form1.elements[m_branch].focus(); ");
			out.println(" return false;");
			out.println("}");
			*/
			
			// commented by udara 17-07-2019
			/*
			out.println("else if(document.Form1.elements[m_accno].value==\"\") {");
			out.println("alert('Account No cannot be null');");
			out.println(" document.Form1.elements[m_accno].focus(); ");
			out.println(" return false;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_reference].value==\"\") {");
			out.println("alert('Reference cannot be null');");
			out.println(" document.Form1.elements[m_reference].focus(); ");
			out.println(" return false;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
			out.println("alert('Tel No cannot be null');");
			out.println(" document.Form1.elements[m_telno].focus(); ");
			out.println("return false;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_faxno].value==\"\") {");
			out.println("alert('Fax No cannot be null');");
			out.println(" document.Form1.elements[m_faxno].focus(); ");
			out.println(" return false;");
			out.println("}");
			*/
			
			out.println("else {");
			out.println(" return true;");
			out.println("}");
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
			
			// commented by udara 22-09-2017
			/*
			
			out.println("if(document.Form1.elements[m_bank].value==\"\") {");
			out.println("alert('Banker cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			
			out.println("else if(document.Form1.elements[m_branch].value==\"\") {");
			out.println("alert('Branch cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_accno].value==\"\") {");
			out.println("alert('Account No cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_reference].value==\"\") {");
			out.println("alert('Reference cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
			out.println("alert('Tel No cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_faxno].value==\"\") {");
			out.println("alert('Fax No cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			*/
			
			//out.println("else{");
			
			out.println("b_count=0;");
			out.println("tmp_bank=document.Form1.elements[m_bank].value;");
			out.println("tmp_branch=document.Form1.elements[m_branch].value;");
			out.println("tmp_accno=document.Form1.elements[m_accno].value;");
			
			out.println("for(var i=0;i<lineno_bank-1;i++){");
			out.println("    m_tmp_bank=\"TXT_BANK_CODE\"+i");
			out.println("    m_tmp_branch=\"TXT_BRANCH_CODE\"+i");
			out.println("    m_tmp_accno=\"TXT_ACCOUNT_NO\"+i");
			
			out.println("   if(lineno_bank>=2){");
			out.println("      if(document.Form1.elements[m_tmp_bank].value==tmp_bank && document.Form1.elements[m_tmp_branch].value==tmp_branch && document.Form1.elements[m_tmp_accno].value==tmp_accno ){");
			out.println("      alert('Account No cannot be duplicated in the same branch ! ')");
			out.println("      b_flag=1;");
			out.println("      b_count=1};");
			
			out.println("     if(b_count==1){");
			out.println("     break;}");
			
			out.println("   } ");
			
			out.println("   }");
			
			//out.println("}");
			
			
			
			out.println("}");
			
			out.println("if(type=='I'){");	
			out.println("  if(b_flag==0){");
			//out.println(" alert('lineno_bank '+lineno_bank);");
			out.println("  e_txt_bank.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");
			//out.println("  '<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' maxlength=\"10\"  onblur=\"makeRequest8(this)\"  size=\"12\"  style=\"width: 70px\" ></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' maxlength=\"10\" value=\"-\" onblur=\"\"  size=\"12\"  style=\"width: 70px\" ></TD>'+"); // added by udara 22-09-2017
			out.println("  '<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+lineno_bank+' value=\"...\" onClick=\"help_button_bank('+lineno_bank+')\" style=\"width: 30px\"  ></td>'+");
			//out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+lineno_bank+' ></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+lineno_bank+' value=\"-\" ></TD>'+"); // added by udara 22-09-2017
			
			
			
			
			//out.println("  '<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+lineno_bank+'  onblur=\"makeRequest9(this)\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+lineno_bank+'  value=\"\" onblur=\"makeRequest9(this)\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\" ></TD>'+"); // added by udara 22-09-2017
			out.println("  '<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+lineno_bank+' value=\"...\" onClick=\"help_button_branch('+lineno_bank+')\" style=\"width: 30px\" ></td>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+lineno_bank+' ></TD>'+");
			
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+lineno_bank+' value=\"-\" maxlength=\"20\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+lineno_bank+'  value=\"-\" maxlength=\"20\" size=\"20\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+lineno_bank+'   value=\"-\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"22\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+lineno_bank+'    value=\"-\" onBlur=\"Validate_Telephone_Number(this,2)\" maxlength=\"60\" size=\"22\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+lineno_bank+' value=\"-\" onblur=\"check_number(this)\"  maxlength=\"4\" size=\"6\" style=\"width: 55px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+lineno_bank+' value=\"Delete\" onClick=del_row_bank(\"'+lineno_bank+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			
			out.println("  lineno_bank=lineno_bank+1;");
			out.println("  arr_size_bank = arr_size_bank+1;");
			out.println("  }");
			out.println("  add_button_bank(type);");
			
			out.println("  }");
			out.println(" else {");
			out.println(" if(b_flag==0){");
			//out.println(" alert('lineno_bank '+lineno_bank);");
			out.println("  e_txt_bank_c.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			//out.println("  '<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' onblur=\"makeRequest8(this)\"  maxlength=\"10\" size=\"12\" style=\"width: 70px\"></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"9%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_CODE'+lineno_bank+' value=\"-\" onblur=\"makeRequest8(this)\"  maxlength=\"10\" size=\"12\" style=\"width: 70px\"></TD>'+"); // added by udara 22-09-2017
			out.println("  '<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BANK_CODE'+lineno_bank+' value=\"...\" onClick=\"help_button_bank('+lineno_bank+')\"  style=\"width: 30px\" ></td>'+");
			
			//out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+lineno_bank+' value=\"\"></TD>'+"); // commented by udara 22-09-2017
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BANK_NAME'+lineno_bank+' value=\"-\"></TD>'+"); // added by udara 22-09-2017
			
			out.println("  '<TD WIDTH=\"6%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_CODE'+lineno_bank+' onblur=\"makeRequest9(this)\"   maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+");
			out.println("  '<TD WIDTH=\"4%\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_BRANCH_CODE'+lineno_bank+' value=\"...\" onClick=\"help_button_branch('+lineno_bank+')\"  style=\"width: 30px\" ></td>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_BRANCH_NAME'+lineno_bank+' value=\"\"></TD>'+");
			
			
			
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO'+lineno_bank+' maxlength=\"20\" size=\"20\"  value=\"-\" style=\"width: 125px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_REFERENCE'+lineno_bank+' maxlength=\"20\" size=\"20\"   value=\"-\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO2'+lineno_bank+' onBlur=\"Validate_Telephone_Number(this,1)\" value=\"-\" maxlength=\"60\" size=\"22\" style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_FAX_NO'+lineno_bank+' onBlur=\"Validate_Telephone_Number(this,2)\"  value=\"-\" maxlength=\"60\" size=\"22\"  style=\"width: 125px\"></TD>'+");
			out.println("  '<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP2'+lineno_bank+' onblur=\"check_number(this)\" style=\"  value=\"-\" width: 55px\"  maxlength=\"4\" size=\"6\"></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_BANK_DEL'+lineno_bank+' value=\"Delete\" onClick=del_row_bank(\"'+lineno_bank+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			
			out.println("  lineno_bank=lineno_bank+1;");
			out.println("  arr_size_bank = arr_size_bank+1;");
			out.println(" }");
			out.println(" add_button_bank(type);");
			
			out.println(" }");
			//out.println(" alert('Add func array size bank : '+arr_size_bank);");
			out.println(" }");
			
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
			out.println("alert('Type cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_institute].value==\"\") {");
			out.println("alert('Institution name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_contact_person].value==\"\") {");
			out.println("alert('Contact person cannot be null');");
			//out.println("alert('Contact Person '+m_contact_person);");
			//out.println("alert('contract NO '+m_contract_no);");
			out.println("b_flag=1;");
			out.println("}");
			
			
			out.println("else if(document.Form1.elements[m_contract_no].value==\"\") {");
			out.println("alert('Contract No cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_security].value==\"\") {");
			out.println("alert('Security  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_app_amount].value==\"\") {");
			out.println("alert('Approved amount  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_bal_amount].value==\"\") {");
			out.println("alert(' Balance amount  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_months].value==\"\") {");
			out.println("alert(' Months  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			
			out.println("else{");
			//out.println("b_flag=0;");
			out.println("b_count=0;");
			out.println("tmp_institute = document.Form1.elements[m_institute].value;");
			out.println("tmp_type = document.Form1.elements[m_type_of_facility].value;");
			
			out.println("for(var i=0;i<lineno_credit-1;i++){");
			out.println("    m_tmp_institute=\"TXT_INSTITUTION\"+i");
			out.println("    m_tmp_type=\"TXT_TYPE_OF_FACILITY\"+i");
			
			out.println("   if(lineno_credit>=2){");
			out.println("     if(document.Form1.elements[m_tmp_institute].value==tmp_institute && document.Form1.elements[m_tmp_type].value==tmp_type ){");
			out.println("     alert('Institute cannot be duplicated with the same type !')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  } ");
			
			out.println("}"); 
			
			out.println("}");
			
			out.println("}");
			
			out.println("if(b_flag==0){");
			//out.println(" alert('lineno credit'+lineno_credit);");
			out.println("e_txt_credit.innerHTML +='<table  border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"12%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+lineno_credit+' class=\"txt_input\" style=\"width: 100px\">'+");
			out.println("'<OPTION value=\"VEHICLE LOAN\" >Vehicle Loan </option>'+");
			out.println("'<OPTION value=\"HOUSE LOAN\" SELECTED>House Loan </option>'+");
			out.println("'<OPTION value=\"PERSONAL LOAN\" >Personal Loan </option>'+");
			out.println("'<OPTION value=\"CREDIT CARD\" >Credit Card </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TYPE_OF_FACILITY'+lineno_credit+' maxlength=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input\" type=\"text\" name=TXT_INSTITUTION'+lineno_credit+'    value=\"-\" maxlength=\"100\" style=\"width: 130px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input\" type=\"text\" name=TXT_CONTACT_PERSON'+lineno_credit+' value=\"-\" maxlength=\"100\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTRACT_NO'+lineno_credit+'   value=\"-\" maxlength=\"60\"  size=\"13\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input3\" type=\"text\" name=TXT_SECURITY'+lineno_credit+' 	 value=\"-\" 	maxlength=\"100\" size=\"14\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+lineno_credit+' value=\"-\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");//Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+lineno_credit+'  value=\"-\" onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 100px\"></TD>'+");
			out.println("'<TD WIDTH=\"7%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+lineno_credit+'  value=\"-\" onblur=\"check_number(this)\" maxlength=\"4\" size=\"5\" style=\"width: 55px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+lineno_credit+' value=\"Delete\" onClick=\"del_row_credit('+lineno_credit+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("lineno_credit = lineno_credit+1;");
			out.println("arr_size_credit = arr_size_credit+1;");
			//out.println(" alert('arr size credi '+arr_size_credit);");
			out.println("}");
			out.println("add_button_credit();");
			out.println("}");
			
			out.println("function check_nonrelated_ref(no) { ");
			
			out.println("m_ref_name=\"TXT_NAME_REFEREE\"+no");
			out.println("m_relationship=\"TXT_RELATIONSHIP3\"+no");
			out.println("m_period=\"TXT_PERIOD\"+no");
			out.println("m_desig=\"TXT_DESIGNATION3\"+no");
			out.println("m_tel_home=\"TXT_HOME_TEL_NO\"+no");
			out.println("m_tel_office=\"TXT_OFFICE_TEL_NO\"+no");
			out.println("m_mobile=\"TXT_MOBILE_NO\"+no");
			
			out.println("if(document.Form1.elements[m_ref_name].value==\"\") {");
			out.println("alert('Nonrelated referee name cannot be null');");
			out.println("document.Form1.elements[m_ref_name].focus(); ");
			out.println(" return false;");
			out.println("}");
			out.println("else if(document.Form1.elements[m_relationship].value==\"\") {");
			out.println("alert('Relationship cannot be null');");
			out.println("document.Form1.elements[m_relationship].focus(); ");
			out.println(" return false;");
			out.println("}");
			
			/*out.println("else if(document.Form1.elements[m_desig].value==\"\") {");
            out.println("alert(' Designation cannot be null');");
            out.println("document.Form1.elements[m_desig].focus(); ");
            out.println(" return false;");
            out.println("}");
            out.println("else if(document.Form1.elements[m_tel_home].value==\"\") {");
            out.println("alert('Home Tel No cannot be null');");
            out.println("document.Form1.elements[m_tel_home].focus(); ");
            out.println(" return false;");
            out.println("}");
            */
			
			out.println("else { ");
			out.println(" return true;");
			out.println("}");
			
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
			out.println("alert('Referee name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_relationship].value==\"\") {");
			out.println("alert('Relationship cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_desig].value==\"\") {");
			out.println("alert(' Designation cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			//	out.println("else if(document.Form1.elements[m_tel_home].value==\"\") {");
			//	out.println("alert('Home Tel No. cannot be null');");
			//	out.println("b_flag=1;");
			//		out.println("}");
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("tmp_ref_name=document.Form1.elements[m_ref_name].value;");
			out.println("tmp_relation=document.Form1.elements[m_relationship].value;");
			//out.println(" alert('Temp name &'+tmp_ref_name);");
			out.println("for(var i=0;i<lineno_nonrelated-1;i++){");
			out.println("    m_tmp_ref=\"TXT_NAME_REFEREE\"+i");
			out.println("m_tmp_relation=\"TXT_RELATIONSHIP3\"+i");
			
			//out.println("			alert('Current &'+document.Form1.elements[m_tmp_ref].value)");
			out.println("   if(lineno_nonrelated >=2 ){");
			out.println("     if(document.Form1.elements[m_tmp_ref].value.toUpperCase() == tmp_ref_name.toUpperCase() &&  document.Form1.elements[m_tmp_relation].value.toUpperCase() == tmp_relation.toUpperCase() ){");
			out.println("     alert('Referee name and relationship cannot be duplicated')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  } ");
			
			out.println("}");
			
			out.println("}");
			
			
			
			out.println("}");
			out.println(" if(b_flag==0){");
			out.println("  if(type=='I'){");
			//out.println(" alert('lineno_bank '+lineno_bank);");
			out.println("  e_txt_nonrelated.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+lineno_nonrelated+' maxlength=\"100\" size=\"22\" value=\"-\" style=\"width: 120px\" ></TD>'+"); // Thamali on 2010.12.27
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+lineno_nonrelated+' maxlength=\"50\" size=\"10\"  value=\"-\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+lineno_nonrelated+' maxlength=\"3\" onblur=\"check_number(this)\" value=\"0\" size=\"8\" style=\"width: 60px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+lineno_nonrelated+' maxlength=\"50\" value=\"-\"  size=\"20\"  style=\"width: 115px\"></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+lineno_nonrelated+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+lineno_nonrelated+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			out.println("  }");
			out.println("  else{");
			out.println("  e_txt_nonrelated_c.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_REFEREE'+lineno_nonrelated+' maxlength=\"100\" size=\"22\" value=\"-\" style=\"width: 120px\" ></TD>'+"); // Thamali on 2010.12.27
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input\" type=\"text\" name=TXT_RELATIONSHIP3'+lineno_nonrelated+' maxlength=\"50\" size=\"10\" value=\"-\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PERIOD'+lineno_nonrelated+' maxlength=\"3\" size=\"8\" onblur=\"check_number(this)\" value=\"0\" style=\"width: 60px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_DESIGNATION3'+lineno_nonrelated+' maxlength=\"50\"  value=\"-\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_HOME_TEL_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICE_TEL_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO'+lineno_nonrelated+' value=\"-\" onBlur=\"Validate_Telephone_Number(this,3)\" maxlength=\"30\" size=\"20\" style=\"width: 115px\" ></TD>'+");
			out.println("  '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_NON_DEL'+lineno_nonrelated+' value=\"Delete\" onClick=del_row_nonrelated_ref(\"'+lineno_nonrelated+'\",\"'+type+'\")>'+");
			out.println("  '</td></tr></table>';");
			out.println("  }");
			out.println("  lineno_nonrelated = lineno_nonrelated+1;");
			out.println("  arr_size_nonrelated = arr_size_nonrelated+1;");
			out.println(" }");
			//out.println(" alert('Add func  arry size '+arr_size_nonrelated);");
			out.println(" add_button_nonrelated(type);");
			out.println("}");
			
			//comment by nuwan de silva 16-10-2008
			/*out.println("function validate_NIC_dir(){ ");
            out.println("if(e_txt_company_directors.innerHTML !=\"\") {");
            out.println(" count=lineno_company_dir-1;  ");	
            out.println(" m_nic_noo=\"TXT_NIC_NO_DIR\"+count");
                out.println("if(document.Form1[m_nic_noo].value != \"\") { "); 	
                out.println(" if(val_nic1(document.Form1[m_nic_noo])) ");	
            out.println("  return true ; ");
                out.println(" else ");	
                out.println("  return false ; ");	
                out.println("}"); 		
                out.println("else ");	
                out.println("  return true ; ");		
                out.println("}"); 
            out.println("  return true ; ");			
            out.println("}"); 
            */
			
			
			/*
              //Corporate 
              out.println("function add_row_company_dir(){"); 
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
                out.println("alert('Director name cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(document.Form1.elements[m_nic_noo].value==\"\") {");
                out.println("alert('NIC number cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(document.Form1.elements[m_stake].value==\"\") {");
                out.println("alert('Stake cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(document.Form1.elements[m_no_shares].value==\"\") {");
                out.println("alert('No of shares cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(document.Form1.elements[m_value].value==\"\") {");
                out.println("alert('Value cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(document.Form1.elements[m_position].value==\"\") {");
                out.println("alert('Position cannot be null');");
                out.println("b_flag=1;");
                out.println("}");
                
                out.println("else if(!validate_NIC_dir()) {");
                //out.println("alert('Position cannot be null');");
                out.println("b_flag=1;");
                out.println("}");

    
                out.println("else{");
                out.println("b_count=0;");
                
                out.println("tmp_dir_name = document.Form1.elements[m_name].value;");
                out.println("tmp_nic = document.Form1.elements[m_nic_noo].value;");
                
                out.println("for(var i=0;i<lineno_company_dir-1;i++){");
                out.println("    m_tmp_name=\"TXT_NAME_DIR\"+i");
                out.println("    m_tmp_nic=\"TXT_NIC_NO_DIR\"+i");
                
                out.println("   if(lineno_company_dir>=2){");
                out.println("     if(document.Form1.elements[m_tmp_name].value!=tmp_dir_name && document.Form1.elements[m_tmp_nic].value==tmp_nic ){");
                out.println("     alert('NIC No cannot be duplicated !')");
                out.println("     b_flag=1;");
                out.println("     b_count=1};");
                out.println("     else if(document.Form1.elements[m_tmp_name].value==tmp_dir_name ){");
                out.println("     alert(' Name cannot be duplicated !')");
                out.println("     b_flag=1;");
                out.println("     b_count=1};");
                
                out.println("    if(b_count==1){");
                out.println("    break;}");
                
                out.println("  } ");
              out.println("}");
                
              out.println("}");
                out.println("}");
                
                out.println("if(b_flag==0){");
                //out.println(" alert('lineno'+lineno);");
                out.println("e_txt_company_directors.innerHTML +='<table  border=\"1\"  align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
              out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+lineno_company_dir+' maxlength=\"100\"  size=\"40\" style=\"width: 220px\" ></TD>'+");
            out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+lineno_company_dir+' onblur=\"\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+lineno_company_dir+' onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\"></TD>'+");
              out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+lineno_company_dir+' onblur=\"check_number(this)\" maxlength=\"20\" size=\"15\" style=\"width: 80px\"></TD>'+");
              out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+lineno_company_dir+' onblur=\"check_number_decimal(this,20)\" maxlength=\"25\" size=\"30\" style=\"width: 150px\"></TD>'+");
                out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+lineno_company_dir+' maxlength=\"50\" size=\"34\" style=\"width: 195px\"></TD>'+");
              out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+lineno_company_dir+' value=\"Delete\" onClick=\"del_row_company_dir('+lineno_company_dir+')\">'+");
              out.println("'</td></tr></table>';");
      
                out.println("lineno_company_dir = lineno_company_dir+1;");
                out.println("arr_size_company = arr_size_company+1;");
        out.println("}");
                out.println("add_button_company_dir();");
            out.println("}");
                */
			
			
			
			out.println("function add_row_family(){"); 
			//out.println("alert('ok');");
			//out.println("alert('lineno fam'+lineno_family);");
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
			out.println("alert('Director name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_name].value==\"\") {");
			out.println("alert('Name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_add].value==\"\") {");
			out.println("alert('Address cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_age].value==\"\") {");
			out.println("alert('Age cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else{");
			out.println("b_count=0;");			
			out.println("tmp_name=document.Form1.elements[m_name].value;");
			
			out.println("for(var i=0;i<lineno_family-1;i++){");
			out.println("    m_tmp_name=\"TXT_NAME_F\"+i");
			
			out.println("   if(lineno_family>=2){");
			out.println("     if(document.Form1.elements[m_tmp_name].value==tmp_name){");
			out.println("     alert('Member name cannot be duplicated !')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  } ");
			
			out.println("}"); 
			
			out.println("}");		
			
			out.println("}");
			
			out.println("if(b_flag==0){");
			//out.println(" alert('lineno'+lineno);");
			out.println("e_txt_family_members.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<td WIDTH=\"14%\">'+ ");
			out.println("'<select name=TXT_MEMBER'+lineno_family+' class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"FATHER\" >Father</option>'+");
			out.println("'<OPTION value=\"MOTHER\" SELECTED>Mother</option>'+");
			out.println("'<OPTION value=\"BROTHER\" >Brother </option>'+");
			out.println("'<OPTION value=\"SISTER\" >Sister </option>'+");
			out.println("'<OPTION value=\"SPOUSE\" >Spouse </option>'+");
			out.println("'</SELECT></td>'+");
			//out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_MEMBER'+lineno_family+' maxlength=\"100\" size=\"50\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_F'+lineno_family+' maxlength=\"200\" size=\"30\" style=\"width: 170px\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS1_F'+lineno_family+' maxlength=\"200\" size=\"40\" style=\"width: 215px\"></TD>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGE_F'+lineno_family+' onblur=\"check_number(this)\" maxlength=\"3\" size=\"4\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TELEPHONE_NO_F'+lineno_family+' onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_MOBILE_NO_F'+lineno_family+' maxlength=\"30\" onBlur=\"Validate_Telephone_Number(this,3)\" size=\"20\" style=\"width: 125px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_FAM_DEL'+lineno_family+' value=\"Delete\" onClick=\"del_row_family('+lineno_family+')\">'+");
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
			out.println("alert('Subsidiary name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_stake].value==\"\") {");
			out.println("alert('Stake cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_value].value==\"\") {");
			out.println("alert('Value cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_activities].value==\"\") {");
			out.println("alert('Business activities cannot be null ');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("tmp_name=document.Form1.elements[m_name].value;");
			
			out.println("for(var i=0;i<lineno_subsidiaries-1;i++){");
			out.println("    m_tmp_name=\"TXT_NAME_SUB\"+i");
			
			out.println("   if(lineno_subsidiaries >= 2){");
			out.println("    if(document.Form1.elements[m_tmp_name].value==tmp_name){");
			out.println("     alert('Subsidiary name cannot be duplicated !')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  } ");
			
			out.println("}"); 
			
			out.println("}");
			
			
			
			out.println("}");
			
			out.println("if(b_flag==0){");
			//out.println(" alert('lineno'+lineno);");
			out.println("e_txt_subsidiaries.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_SUB'+lineno_subsidiaries+' value=\"\" maxlength=\"100\" size=\"40\"></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE_SUB'+lineno_subsidiaries+' onblur=\"check_number_precent(this,6)\" value=\"\"  maxlength=\"6\" size=\"7\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_SUB'+lineno_subsidiaries+' onblur=\"check_number_decimal(this,20)\" value=\"\" maxlength=\"20\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_SUB'+lineno_subsidiaries+'  value=\"\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"20\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_OFFICER_SUB'+lineno_subsidiaries+' value=\"\" maxlength=\"20\" size=\"15\" style=\"width: 80px\" ></TD>'+");
			out.println("'<TD WIDTH=\"21%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ACTIVITIES_SUB'+lineno_subsidiaries+' value=\"\"  maxlength=\"100\" size=\"31\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_SUB_DEL'+lineno_subsidiaries+' value=\"Delete\" onClick=\"del_row_subsidiaries('+lineno_subsidiaries+')\">'+");
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
			out.println("alert('Customer name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_add].value==\"\") {");
			out.println("alert('Address cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_relation].value==\"\") {");
			out.println("alert('Relation cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_con_person].value==\"\") {");
			out.println("alert('Contact person cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_telno].value==\"\") {");
			out.println("alert('Tel No cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("tmp_name=document.Form1.elements[m_name].value;");
			out.println("tmp_type=document.Form1.elements[m_type].value;");
			
			out.println("for(var i=0;i<lineno_customer-1;i++){");
			out.println("    m_tmp_cus_name = \"TXT_CUSTOMER_NAME\"+i");
			out.println("    m_tmp_cus_type = \"TXT_TYPE_C\"+i");
			out.println("   if(lineno_customer >= 2){");
			out.println("    if(document.Form1.elements[m_tmp_cus_name].value==tmp_name && document.Form1.elements[m_tmp_cus_type].value==tmp_type ){");
			out.println("     alert('Name cannot be duplicated ! ')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  } }}}");
			
			//out.println("}"); 
			
			//out.println("}");
			
			
			
			//out.println("}");
			
			out.println("if(b_flag==0){");
			//out.println(" alert('lineno'+lineno);");
			out.println("e_txt_customer.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"23%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CUSTOMER_NAME'+lineno_customer+' value=\"\" maxlength=\"200\" size=\"36\"></TD>'+");
			out.println("'<td WIDTH=\"9%\">'+ ");
			out.println("'<select name=TXT_TYPE_C'+lineno_customer+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"CUSTOMER\" SELECTED>Customer </option>'+");
			out.println("'<OPTION value=\"SUPPLIER\" >Supplier </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_CUS'+lineno_customer+' value=\"\"  maxlength=\"200\" size=\"30\"></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input5\" type=\"text\" name=TXT_RELATIONSHIP_CUS'+lineno_customer+'  value=\"\"  onblur=\"check_number(this)\" style=\"width: 100px\"  maxlength=\"3\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON_CUS'+lineno_customer+'  value=\"\" maxlength=\"200\" size=\"22\"></TD>'+");
			out.println("'<TD WIDTH=\"14%\"><input class=\"txt_input3\" type=\"text\" name=TXT_TEL_NO_CUS'+lineno_customer+' value=\"\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"19\"></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CUS_DEL'+lineno_customer+' value=\"Delete\" onClick=\"del_row_customer('+lineno_customer+')\">'+");
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
			out.println("alert('Institution name cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_contact_person].value==\"\") {");
			out.println("alert('Contact person cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_equipment].value==\"\") {");
			out.println("alert('Equipment cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			
			out.println("else if(document.Form1.elements[m_app_amount].value==\"\") {");
			out.println("alert('Approved amount  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_rental].value==\"\") {");
			out.println("alert('Monthly rental cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			
			// commented by udara 22-09-2017
			/*
			out.println("else if(document.Form1.elements[m_months].value==\"\") {");
			out.println("alert(' Period  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			*/
			
			/*
			out.println("else if(document.Form1.elements[m_bal_amount].value==\"\") {");
			out.println("alert(' Balance amount  cannot be null');");
			out.println("b_flag=1;");
			out.println("}");
			*/
			
			
			out.println("else{");
			out.println("b_count=0;");
			
			out.println("tmp_type=document.Form1.elements[m_type_of_facility].value;");
			out.println("tmp_institute=document.Form1.elements[m_institute].value;");
			
			out.println("for(var i=0;i<lineno_credit_c-1;i++){");
			out.println("    m_tmp_institute=\"TXT_INSTITUTION\"+i");
			out.println("    m_tmp_type=\"TXT_TYPE_OF_FACILITY\"+i");
			
			out.println("   if(lineno_credit_c>=2){");
			out.println("     if(document.Form1.elements[m_tmp_type].value==tmp_type && document.Form1.elements[m_tmp_institute].value==tmp_institute ){");
			out.println("     alert('Institute name cannot be duplicated with the same type !')");
			out.println("     b_flag=1;");
			out.println("     b_count=1};");
			
			out.println("    if(b_count==1){");
			out.println("    break;}");
			
			out.println("  }}}} ");
			
			//out.println("}");
			
			//out.println("}");
			
			//out.println("}");
			
			out.println("if(b_flag==0){");
			out.println("e_txt_credit_c.innerHTML +='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input3\" type=\"text\" name=TXT_INSTITUTION'+lineno_credit_c+' maxlength=\"100\" size=\"20\" style=\"width: 120px\" ></TD>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_CONTACT_PERSON'+lineno_credit_c+' maxlength=\"100\" size=\"20\"  style=\"width: 110px\" ></TD>'+");
			out.println("'<td WIDTH=\"11%\">'+ ");
			out.println("'<select name=TXT_TYPE_OF_FACILITY'+lineno_credit_c+' class=\"txt_input3\" >'+");
			out.println("'<OPTION value=\"TERM LOAN\" >Term Loan </option>'+");
			out.println("'<OPTION value=\"LEASE\" SELECTED>Lease </option>'+");
			out.println("'<OPTION value=\"OVER DRAFT\" >Over Draft </option>'+");
			out.println("'<OPTION value=\"LC FACILITY\" >LC Facility </option>'+");
			out.println("'<OPTION value=\"PLEDGE LOAN\" >Pledge Loan </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_EQUIPMENT'+lineno_credit_c+' maxlength=\"50\"  size=\"20\" style=\"width: 110px\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_APPROVED_AMOUNT'+lineno_credit_c+' onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" size=\"20\" style=\"width: 120px\" ></TD>'+");//Modified by Thamali Jayatunga on 2009.10.19
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHLY_RENTAL'+lineno_credit_c+' onblur=\"check_number(this)\" maxlength=\"15\" size=\"10\" style=\"width: 60px\" ></TD>'+");
			out.println("'<TD WIDTH=\"8%\"><input class=\"txt_input5\" type=\"text\" name=TXT_MONTHS'+lineno_credit_c+' onblur=\"check_number(this)\" maxlength=\"4\" size=\"8\" ></TD>'+");
			out.println("'<TD WIDTH=\"11%\"><input class=\"txt_input5\" type=\"text\" name=TXT_BALANCE_AMOUNT'+lineno_credit_c+' onblur=\"check_number_decimal(this,21)\" maxlength=\"21\" style=\"width: 85px\" ></TD>'+");
			out.println("'<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_CREDIT_DEL'+lineno_credit_c+' value=\"Delete\" onClick=\"del_row_credit_c('+lineno_credit_c+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("lineno_credit_c = lineno_credit_c+1;");
			out.println("arr_size_credit_c = arr_size_credit_c+1;");
			out.println("}");
			out.println("add_button_credit_c();");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			
			out.println("//validations goes here"); 
			
			out.println("if(document.Form1.TXT_CLIENT_TYPE.value==\"\"){  "); 
			out.println(" DIV_TXT_CLIENT_TYPE.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}"); 
			
			///	out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){"); 
			
			//individual
			out.println("else if(document.Form1.TXT_CLIENT_TYPE.value==\"I\"){  "); 
			
			/*out.println("if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
            out.println("DIV_TXT_FIRST_NAME.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;");
            out.println("}");
            */
			//	out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){"); 
			
			out.println("if(document.Form1.TXT_SURNAME.value==\"\"){  "); 
			out.println("DIV_TXT_SURNAME.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			out.println("if(document.Form1.TXT_OTHER_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_OTHER_NAME.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			
			
			out.println("else if(document.Form1.TXT_FULL_NAME_I.value==\"\"){  "); 
			out.println("DIV_TXT_FULL_NAME_I.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			out.println("else if(document.Form1.TXT_ADDRESS1_HOME.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1_HOME.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			// Add by Amila 2017-08-25
			
			out.println("else if(document.Form1.TXT_ADDRESS2_HOME.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1_HOME.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			// End by Amila
			
			
			// added by udara 27-07-2015
			
			out.println("else if((document.Form1.TXT_MOBILE_NO.value==\"\") || (document.Form1.TXT_MOBILE_NO.value==\"-\")){  "); 
			out.println("  DIV_TXT_MOBILE_NO.style.color='red';");
			out.println("  alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("  return false;"); 
			out.println("}");
			
			// end by udara 27-07-2015
			
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_CITY_CODE.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			
			// added by udara 14-11-2025
			out.println("else if(document.Form1.TXT_ADDRESS1_REL.value==\"\"){  "); 
			out.println("		DIV_TXT_ADDRESS1_REL.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_ADDRESS2_REL.value==\"\"){  "); 
			out.println("		DIV_TXT_ADDRESS1_REL.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			// end by udara 14-11-2025
			
			
			/*out.println("else if(document.Form1.TXT_DURATION_AT_YEARS.value==\"\"){  "); 
            out.println("DIV_TXT_DURATION_AT_YEARS.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_DURATION_AT_MONTHS.value==\"\"){  "); 
            out.println("DIV_TXT_DURATION_AT_MONTHS.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_EMP_NAME.value==\"\"){  "); 
            out.println("DIV_TXT_EMP_NAME.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_EMP_RDESIGNATION.value==\"\"){  "); 
            out.println("DIV_TXT_EMP_RDESIGNATION.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
     
            out.println("else if(document.Form1.TXT_NAME_REL.value==\"\"){  "); 
            out.println("DIV_TXT_NAME_REL.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_RELATIONSHIP.value==\"\"){  "); 
            out.println("DIV_TXT_RELATIONSHIP.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_ADDRESS1_REL.value==\"\"){  "); 
            out.println("DIV_TXT_ADDRESS1_REL.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            
            out.println("else if(document.Form1.TXT_HOME_TEL_NO.value==\"\"){  "); 
            out.println("DIV_TXT_HOME_TEL_NO.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
             */
			
			// Changed By Samitha Kulatilaka On 2011-12-07
			/*
            out.println("else if(document.Form1.TXT_NIC_NO.value==\"\" ){  "); 
            out.println("DIV_TXT_NIC_NO.style.color='red';");
            out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
            out.println("return false;"); 
            out.println("}");
            */
			
			// udara 14-11-2013
			//out.println("else if (((document.forms[0].elements['TXT_NATIONALITY'].value == 'SRILANKAN') && (document.Form1.TXT_NIC_NO.value==\"\")) || ((document.forms[0].elements['TXT_NATIONALITY'].value != 'SRILANKAN') && (document.Form1.TXT_PASSPORT_NO.value==\"\"))) {  "); 
			
			// udara 14-11-2013
			out.println("else if ( (document.Form1.TXT_NIC_NO.value==\"\") && (document.Form1.TXT_PASSPORT_NO.value==\"\") ) {  "); 
			
			//out.println("       if (document.forms[0].elements['TXT_NATIONALITY'].value == 'SRILANKAN') {");
			out.println("           DIV_TXT_NIC_NO.style.color='red';");
			//out.println("       }");
			//out.println("       else {");
			out.println("           DIV_TXT_PASSPORT_NO.style.color='red';");
			//out.println("       }");
			
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			/*
			// added by udara 23-07-2015
			
			out.println("else if((document.Form1.TXT_MOBILE_NO.value==\"\") || (document.Form1.TXT_MOBILE_NO.value==\"-\")){  "); 
			out.println("  DIV_TXT_MOBILE_NO.style.color='red';");
			out.println("  alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("  return false;"); 
			out.println("}");
			
			// end by udara 23-07-2015
			*/
			
			
			out.println("else if(document.Form1.TXT_DATE_OF_BIRTH_DD.value==\"\" || document.Form1.TXT_DATE_OF_BIRTH_MM.value==\"\" || document.Form1.TXT_DATE_OF_BIRTH_YY.value==\"\") {");
			out.println("DIV_TXT_DATE_OF_BIRTH.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("return false;"); 
			out.println("}");
			
			out.println("else if(!check_nonrelated_ref(0)){  "); 
			out.println("DIV_TXT_NON_RELATED.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			
			/*out.println("else if(document.Form1.TXT_TOT_INCOME.value==\"\" || document.Form1.TXT_TOT_INCOME.value==\"0.00\" ){  "); 
            out.println("DIV_TXT_TOT_INCOME.style.color='red';");
            out.println("	alert(\"Monthly Income cannot be zero \"); ");
            out.println("	return false;"); 
            out.println("}");
            
            
            out.println("else if(document.Form1.TXT_TOT_EXPENSE.value==\"\" || document.Form1.TXT_TOT_EXPENSE.value==\"0.00\" ){  "); 
            out.println("DIV_TXT_TOT_INCOME.style.color='red';");
            //out.println("DIV_TXT_TOT_EXPENSE.style.color='red';");
            out.println("	alert(\"Monthly Expenses cannot be zero \"); ");
            out.println("	return false;"); 
            out.println("}");
      */
			
			//	out.println("}"); 
			
			// added by udara on 06-01-2012
			out.println("else if(document.Form1.TXT_BUS_SECT_MAIN.value==\"\"){ ");
			out.println("   DIV_BUSINESS_SECTOR.style.color='red';");
			out.println("	alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("}");
			// end by udara on 06-01-2012
			
			
			
			
			out.println("else{"); 
			out.println("	 	return true;"); 
			out.println("}}");
			
			//out.println("}"); 
			
			//corporate
			out.println("else if(document.Form1.TXT_CLIENT_TYPE.value==\"C\"){  "); 
			
			//	out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){"); 
			
			out.println("if(document.Form1.TXT_FULL_NAME_C.value==\"\"){  "); 
			out.println("		DIV_TXT_FULL_NAME_C.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_KEY_DECISION_MAKER.value==\"\"){  "); 
			out.println("		DIV_TXT_KEY_DECISION_MAKER.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_ADDRESS1_REG.value==\"\"){  "); 
			out.println("		DIV_TXT_ADDRESS1_REG.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			// Add by Amila 2017-08-25
			out.println("else if(document.Form1.TXT_ADDRESS2_REG.value==\"\"){  "); 
			out.println("		DIV_TXT_ADDRESS1_REG.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			// End  Amila
			
			out.println("else if(document.Form1.TXT_DESIGNATION1.value==\"\"){  "); 
			out.println("		DIV_TXT_DESIGNATION1.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_DIRECT_TEL_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_DIRECT_TEL_NO.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_CITY_CODE.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_BUSINESS_CERTIFICATE_NO.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_DATE_OF_INCORPORATION_DD.value==\"\" || document.Form1.TXT_DATE_OF_INCORPORATION_MM.value==\"\" || document.Form1.TXT_DATE_OF_INCORPORATION_YY.value==\"\") {");
			out.println("		DIV_TXT_DATE_OF_INCORPORATION.style.color='red';");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		return false;"); 
			out.println("}"); 
			
			// commented by udara 15-07-2019
			/*
			out.println("else if(!check_business_act(0)){  "); 
			out.println("DIV_TXT_BA.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			*/
			
			out.println("else if(!check_bank(0)){  "); 
			out.println("DIV_TXT_BANK.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			
			out.println("else if(document.Form1.TXT_DATE_OF_INCORPORATION_DD.value!=\"\" || document.Form1.TXT_DATE_OF_INCORPORATION_MM.value!=\"\" || document.Form1.TXT_DATE_OF_INCORPORATION_YY.value!=\"\") {");
			out.println(" if(!checkMonthLength(document.Form1.TXT_DATE_OF_INCORPORATION_DD,document.Form1.TXT_DATE_OF_INCORPORATION_MM,document.Form1.TXT_DATE_OF_INCORPORATION_YY)){  "); 
			out.println("  return false;"); 
			out.println(" }");
			out.println(" else if(document.Form1.TXT_VAT_REG_DATE_DD.value!=\"\" || document.Form1.TXT_VAT_REG_DATE_MM.value!=\"\" || document.Form1.TXT_VAT_REG_DATE_YY.value!=\"\") {");
			out.println("  if(!checkMonthLength(document.Form1.TXT_VAT_REG_DATE_DD,document.Form1.TXT_VAT_REG_DATE_MM,document.Form1.TXT_VAT_REG_DATE_YY)){  "); 
			out.println("   return false;"); 
			out.println("  }");
			
			//out.println("  }");
			
			out.println("  else{"); 
			out.println("   return true;"); 
			out.println("  }}");
			//out.println(" }");
			out.println(" else{"); 
			out.println(" return true;"); 
			out.println(" }}");
			//out.println("}");
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}}}");
			//out.println("}"); 
			//out.println("}");
			out.println(" ");
			
			//add by waruna
			//out.println("alert("+fscreen+")");
			LAKDL_AF_MAS_display_client_creation_validation submit=new LAKDL_AF_MAS_display_client_creation_validation();
			out.println(submit.before_submit(m_class_url,m_fschema_name,m_screen,m_save_close,m_APP_NO,m_no_of_rec,fscreen));
			out.println(" ");
			
			
			out.println(" function before_submit_temp(){ "); 
			out.println("		if(document.Form1.hid_client_type.value == 'I'){"); 
			out.println("		if(check_dates()){"); 
			out.println("		if(confirm(\"Are you sure you want to temporarily save these details?\")){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_client_creation_temp?countemp='+lineno+'"+
				"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
				"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+
				"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&&arr_size_in_ex='+arr_size_income;");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("		else {"); 
			out.println("		if(validate_NIC_dir()) { ");
			out.println("		if(confirm(\"Are you sure you want to temporarily save these details ?\")){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_client_creation_temp?countemp='+lineno+'"+
				"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
				"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+
				"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&&arr_size_in_ex='+arr_size_income;");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}}}}");
			//out.println("		}");
			//out.println("		}"); 
			//out.println("} ");
			
			out.println("var mm_client_status = ''; "); // added by udara 20-10-2017
			
			out.println("function load_lock(){	"); 
			out.println(" disable_temp(); "); //Added by Kanchana on 2016-09-09
			//out.println("document.Form1.TXT_INQUARY_NO.value=\""+m_inquiry_no+"\" ");
			//out.println("document.Form1.TXT_CLIENT_CODE.value=\""+m_client_code+"\" ");//[Added milinda for load client details]
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			//out.println("alert('"+m_app_screen+"');");
			
			
			rs=stmt.executeQuery(" SELECT "+
				" COUNT(*) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE CLIENT_CODE='"+m_client_code+"' AND APPLICATION_STATUS IN('ACTIVATED','TERMINATED','TERMI','NORM_TERMI')");
			
			if(rs.next()){
				m_count=rs.getInt(1);
			}else{
				m_count=0;
			}
			
			out.println("  mm_client_status = '"+m_count+"'; ");  // added by udara 20-10-2017
			
			rs1=stmt1.executeQuery(" SELECT "+
				" NVL(CLIENT_TYPE,'I') "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE CLIENT_CODE='"+m_client_code+"' ");
			
			if(rs1.next()){
				m_client_type=rs1.getString(1);
			}else{
				m_client_type="I";
			}
			out.println(" var m_hid_app_screen ='"+m_app_screen+"'; ");
			out.println("document.Form1.hid_app_screen.value=m_hid_app_screen;   ");
			out.println(" var m_client_count ='"+m_count+"'; ");			
			out.println("document.Form1.hid_client_count.value=m_client_count;   ");
			out.println("document.Form1.TXT_CLIENT_CODE.value=\""+m_client_code+"\" ");//[Added milinda for load client details]
			out.println(" var m_hid_f_screen ='"+fscreen+"'; ");
			out.println("document.Form1.hid_f_screen.value=m_hid_f_screen;   ");
			
			if(m_app_screen.equals("APP_N") || m_app_screen.equals("APP_R")){//[User view without client code enter in application entry screen or receipt screen buttond disable]
				out.println("   document.Form1.edit.disabled=true;");
				out.println("   document.Form1.edit2.disabled=true;");
				out.println("   document.Form1.temp2.disabled=true;");
				out.println("   document.Form1.dact.disabled=true;");
				out.println("   document.Form1.ract.disabled=true;");
				out.println("   document.Form1.cancel.disabled=true;"); 
				out.println("   document.Form1.cancel2.disabled=true;"); 
				out.println("   document.Form1.temp_save2.disabled=true;"); 
				
				
			}
			
			out.println("m_dd=load_lock_1(\""+m_client_type+"\");");
			//out.println("  alert('m_dd'+m_dd);");			
			out.println(" if(m_dd=='C'){");
			out.println("	 add_corporate();");		
			out.println("}	"); 
			out.println(" else if(m_dd=='I'){");			
			out.println("	 add_individual();");			
			out.println("}	");	
			if(m_app_screen.equals("APP_R")||m_app_screen.equals("APP_N")){
				out.println("   document.Form1.edit.disabled=true;");
				//out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
				//out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
				out.println("   document.Form1.cancel.disabled=true;");
				out.println("   document.Form1.dact.disabled=true;");
				out.println("   document.Form1.ract.disabled=true;");
				out.println("   document.Form1.temp2.disabled=true;");
				out.println("   document.Form1.temp_save2.disabled=true;");
				
				
			}
			out.println("}	"); 
			
			
			
			out.println("function load_inq_details(){	"); 
			//out.println("m_client=\""+m_client_code+"\"");
			out.println("m_inq=\""+m_inquiry_no+"\"");
			out.println("m_save_close=\""+m_save_close+"\"");
			
			///out.println("if(m_client==\"\" && m_inq!=\"\" ){");
			out.println("if(m_inq!=\"\" && m_save_close!=\"N\" && m_save_close!=\"B\" ){"); //added by nuwan de silva 06-08-2007
			out.println("document.Form1.TXT_INQUARY_NO.value=\""+m_inquiry_no+"\" ");
			out.println("check_inquiry_no()");
			out.println("}}	"); 		
			
			//out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			
			out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
			
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?close_status=Y';"); // commented by udara 18-09-2014
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?close_status=Y'+'&app_screen="+m_app_screen+"';"); // added by udara 18-09-2014
			out.println("		   }}"); 
			//out.println("		 }"); 
			out.println("		else { "); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ ");  //added by nuwan de silva 20-07-07
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?save_close="+m_save_close+"';"); // commented by udara 18-09-2014
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?save_close="+m_save_close+"'+'&app_screen="+m_app_screen+"';");  // added by udara 18-09-2014
			out.println("		}}}"); 
			//out.println("}"); 
			
			out.println("function new_window(){	"); 
			//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?save_close="+m_save_close+"';"); // commented by udara 18-09-2014
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?save_close="+m_save_close+"'+'&app_screen="+m_app_screen+"';"); // added by udara 18-09-2014
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("}"); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}");
			
			out.println("function save_window_temp(){	"); 
			out.println("before_submit_temp();"); 
			out.println("}"); 
			out.println("");
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_client_creation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){");
			out.println("help_box.innerHTML=\" System Administration - Creation Of Clients - \"+m_val;"); 
			out.println("help_box1.innerHTML=\" System Administration -Creation Of Clients - \"+m_val;"); 
			//out.println("    select_content(m_client_type);");
			
			out.println("}"); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration -  Creation Of Clients - \"+document.Form1.hid_status.value;"); 
			out.println("help_box1.innerHTML=\" System Administration - Creation Of Clients - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			// commented by udara 01-02-2018
			/*
			
			out.println("function load_screen_status(m_val){"); 
			out.println("chk_disa();");
			
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\" && m_val!=\"TEMP\" ){"); 
			out.println("document.Form1.temp_save.disabled=true;"); //Added by kanchana on 2016-09-09 
			out.println("document.Form1.temp.disabled=true;");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("if(document.Form1.hid_client_type.value=='I'){");
			out.println(" for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println(" document.Form1.elements[i].disabled=true;");
			out.println(" }");
			out.println("document.Form1.edit.disabled=false;");
			out.println("document.Form1.but_new.disabled=false;");
			out.println("document.Form1.cancel.disabled=false;");
			out.println("document.Form1.close.disabled=false;");
			out.println("document.Form1.ract.disabled=false;");
			out.println("document.Form1.dact.disabled=false;");
			out.println("document.Form1.help.disabled=false;");
			out.println("document.Form1.save.disabled=false;"); 
			out.println("document.Form1.temp.disabled=false;");
			
			out.println("document.Form1.edit2.disabled=false;");
			out.println("document.Form1.but_new2.disabled=false;");
			out.println("document.Form1.cancel2.disabled=false;");
			out.println("document.Form1.close2.disabled=false;");
			out.println("document.Form1.ract2.disabled=false;");
			out.println("document.Form1.dact2.disabled=false;");
			//out.println("document.Form1.temp_save.disabled=false;");
			out.println("document.Form1.help2.disabled=false;");
			out.println("document.Form1.save2.disabled=false;"); 
			out.println("document.Form1.temp2.disabled=false;");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=false;"); 
			out.println("}"); 
			
			out.println("else {");			
			out.println(" for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println(" document.Form1.elements[i].disabled=true;");
			out.println(" }");
			out.println("document.Form1.edit.disabled=false;");
			out.println("document.Form1.but_new.disabled=false;");
			out.println("document.Form1.cancel.disabled=false;");
			out.println("document.Form1.close.disabled=false;");
			out.println("document.Form1.ract.disabled=false;");
			out.println("document.Form1.dact.disabled=false;");
			out.println("document.Form1.temp_save.disabled=true;"); //Added by Kanchana on 2016-09-09
			out.println("document.Form1.temp.disabled=true;"); //Added by Kanchana on 2016-09-09
			out.println("document.Form1.help.disabled=false;");
			out.println("document.Form1.save.disabled=false;"); 
			out.println("document.Form1.temp.disabled=false;");
			
			out.println("document.Form1.edit2.disabled=false;");
			out.println("document.Form1.but_new2.disabled=false;");
			out.println("document.Form1.cancel2.disabled=false;");
			out.println("document.Form1.close2.disabled=false;");
			out.println("document.Form1.ract2.disabled=false;");
			out.println("document.Form1.dact2.disabled=false;");
			//out.println("document.Form1.temp_save.disabled=false;");
			out.println("document.Form1.help2.disabled=false;");
			out.println("document.Form1.save2.disabled=false;"); 
			out.println("document.Form1.temp2.disabled=false;");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=false;"); 
			out.println("}}"); 
			
			//out.println("}"); 
			
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println(" var m_app_screen = '"+m_app_screen+"'; ");
			out.println(" var m_fscreen = '"+fscreen+"'; ");
			
			
			out.println("document.Form1.hid_temp_status.value=\"N\";");  
			out.println(" for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println(" document.Form1.elements[i].disabled=false;");
			out.println(" }");
			out.println("document.Form1.BUT_HELP_TEMP.disabled=true;");
			out.println("document.Form1.temp_save.disabled=true;");
			out.println("document.Form1.temp.disabled=true;"); //Added by Kanchana on 2016-09-09
			
			out.println("if(document.Form1.hid_client_type.value=='I'){");
			//out.println("  document.Form1.TXT_TOT_EXPENSE.disabled=true;");
			//out.println("  document.Form1.TXT_TOT_INCOME.disabled=true;");
			//out.println("  document.Form1.TXT_NET_INCOME.disabled=true;");
			out.println(" }");
			out.println("  if(m_app_screen=='APP_N' || m_app_screen=='APP_R'|| m_fscreen=='client_verification'){ "); 
			//out.println("alert(m_app_screen);");
			out.println("  if(m_val==\"NEW\"|| m_val==\"EDIT\"||m_val==\"DACT\"||m_val==\"RACT\"){");
			out.println("     if(document.Form1.hid_entity_type.value!=\"CORPORATE\"){");	
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			//out.println("   document.Form1.TXT_CLIENT_CODE.disabled=true;");
			out.println(" }}}}");
			
			//out.println("}");
			
			out.println("else{");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_temp_status.value=\"N\";");  
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_temp_status.value=\"N\";");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){"); 
			out.println("document.Form1.hid_temp_status.value=\"N\";"); 
			out.println("document.Form1.temp_save.disabled=true;"); //Added by kanchana on 2016-09-09
			out.println("document.Form1.temp.disabled=true;"); //Added by kanchana on 2016-09-09
			out.println("document.Form1.BUT_HELP_TEMP.disabled=true;"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_temp_status.value=\"N\";"); 
			out.println("document.Form1.temp_save.disabled=true;"); //Added by kanchana on 2016-09-09
			out.println("document.Form1.temp.disabled=true;"); //Added by kanchana on 2016-09-09
			out.println("document.Form1.BUT_HELP_TEMP.disabled=true;"); 
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else if(m_val==\"TEMP\"){"); 
			out.println(" document.Form1.SCREEN_NAME.value=\"EDIT\";"); 
			
			out.println(" for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println(" document.Form1.elements[i].disabled=false;");
			out.println(" }");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("document.Form1.dact.disabled=true;"); 
			out.println("document.Form1.ract.disabled=true;"); 
			out.println("document.Form1.dact2.disabled=true;"); 
			out.println("document.Form1.ract2.disabled=true;"); 
			out.println("document.Form1.hid_temp_status.value=\"Y\";");  
			out.println("document.Form1.hid_status.value=\"Temporary\";"); 

			
			out.println("}else{");
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			
			//out.println(" var m_fscreen = '"+fscreen+"'; ");
			out.println(" if(m_fscreen=='client_verification'){ "); 			
			//out.println("alert(document.Form1.hid_client_count.value);");
			out.println("  if(document.Form1.hid_client_count.value > 0){");//[Added milinda]			
			out.println("   document.Form1.save.disabled=true;");//[Added milinda]
			out.println("   document.Form1.save2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.edit.disabled=true;");out.println("   document.Form1.edit2.disabled=true;");//[Added milinda]//[Added milinda]
			out.println("   document.Form1.but_new.disabled=true;");out.println("   document.Form1.but_new2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.cancel.disabled=true;");out.println("   document.Form1.cancel2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.dact.disabled=true;");out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("  }else{");//[Added milinda]
			out.println("   document.Form1.save.disabled=false;");//[Added milinda]
			out.println("   document.Form1.save2.disabled=false;");//[Added milinda]
			out.println("   document.Form1.edit.disabled=false;");out.println("   document.Form1.edit2.disabled=false;");//[Added milinda]
			out.println("   document.Form1.but_new.disabled=true;");out.println("   document.Form1.but_new2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.cancel.disabled=true;");out.println("   document.Form1.cancel2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("   document.Form1.dact.disabled=true;");out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			//out.println("   document.Form1.TXT_CLIENT_CODE.disabled=true;");
			out.println("}}");//[Added milinda]
			//out.println(" }");
			out.println(" var m_screen='"+m_screen+"';");
			//out.println("alert(m_screen);");
			//out.println("alert(document.Form1.hid_client_count.value);");
			// added by udara 16-09-2014
			//out.println(" var m_app_screen = '"+m_app_screen+"'; ");
			
			out.println(" if(m_app_screen=='Y'){ "); 
			out.println("   document.Form1.ract.disabled=true;");
			out.println("   document.Form1.dact.disabled=true;");
			out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.dact2.disabled=true;");
			
			out.println(" }");
			out.println(" else if(m_app_screen=='APP_N' ){ "); //[Application Entry level and creation of client level ]
			//out.println("alert('a');");
			out.println("if(document.Form1.hid_client_count.value>0){");//[Added milinda]
			out.println("   document.Form1.save.disabled=true;");//[Added milinda]
			out.println("   document.Form1.save2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.edit.disabled=true;");
			out.println("   document.Form1.edit2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.temp2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.temp_save2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.but_new.disabled=true;");
			out.println("   document.Form1.but_new2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.cancel.disabled=true;");
			out.println("   document.Form1.cancel2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.dact.disabled=true;");
			out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");
			out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			out.println(" }else{");
			//out.println("alert('b');");
			out.println("   document.Form1.save.disabled=false;");//[Added milinda]
			out.println("   document.Form1.save2.disabled=false;");//[Added milinda]
			out.println("   document.Form1.edit.disabled=false;");out.println("   document.Form1.edit2.disabled=false;");//[Added milinda]
			out.println("   document.Form1.but_new.disabled=true;");out.println("   document.Form1.but_new2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.cancel.disabled=true;");out.println("   document.Form1.cancel2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("   document.Form1.dact.disabled=true;");out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			//out.println("   document.Form1.TXT_CLIENT_CODE.disabled=true;");
			out.println("}}");//[Added milinda]
			out.println(" else if(m_app_screen=='APP_R' ){ "); //[Receipt screen level]	
			out.println("if(document.Form1.hid_client_count.value>0){");//[Added milinda]
			out.println("   document.Form1.dact.disabled=true;");out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.cancel.disabled=true;");out.println("   document.Form1.cancel2.disabled=true;");
			out.println("   document.Form1.edit.disabled=true;");out.println("   document.Form1.edit2.disabled=true;");
			out.println("   document.Form1.save.disabled=true;");
			out.println("   document.Form1.save2.disabled=true;");//[Added milinda]
			out.println("   document.Form1.temp2.disabled=true;");
			out.println("   document.Form1.temp_save2.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			out.println(" }else{");
			out.println("   document.Form1.dact.disabled=true;");out.println("   document.Form1.dact2.disabled=true;");
			out.println("   document.Form1.ract.disabled=true;");out.println("   document.Form1.ract2.disabled=true;");
			out.println("   document.Form1.cancel.disabled=true;");out.println("   document.Form1.cancel2.disabled=true;");
			out.println("   document.Form1.edit.disabled=false;");out.println("   document.Form1.edit2.disabled=true;");
			out.println("   document.Form1.save.disabled=false;");
			out.println("   document.Form1.save2.disabled=false;");
			out.println("   document.Form1.TXT_NIC_NO.disabled=true;");
			out.println("   document.Form1.TXT_NIC_NO_OLD.disabled=true;");
			out.println("   document.Form1.TXT_CLIENT_TYPE.disabled=true;");
			//out.println("   document.Form1.TXT_CLIENT_CODE.disabled=true;");
			out.println(" }}}");
			
			*/
			
			// added by udara 01-02-2018
			out.println("function load_screen_status(m_val){"); 
			out.println("  load_screen_status_reduce(m_val,'"+m_screen+"','"+fscreen+"','"+m_app_screen+"'); ");
			out.println("}");
			// end by udara 01-02-2018
			
			//out.println("   document.Form1.TXT_CLIENT_CODE.blur();");
			//out.println("   document.Form1.TXT_CLIENT_CODE.disabled=true;");
			//out.println("}");//[Added milinda]
			//out.println(" }");//[Added milinda]
			
			//out.println("}"); 
			// end by udara 16-09-2014
			
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");}"); 
			//out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_DATE_OF_INCORPORATION_DD.value=v_dd;");
			out.println("     document.Form1.TXT_DATE_OF_INCORPORATION_MM.value=v_mm;");
			out.println("     document.Form1.TXT_DATE_OF_INCORPORATION_YY.value=v_yy;");
			out.println("  }");		
			out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 	
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_VAT_REG_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_VAT_REG_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_VAT_REG_DATE_YY.value=v_yy;");
			out.println("  }");		
			out.println("  else if(document.Form1.hid_cal_date.value=='3'){"); 	
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_DATE_OF_BIRTH_DD.value=v_dd;");
			out.println("     document.Form1.TXT_DATE_OF_BIRTH_MM.value=v_mm;");
			out.println("     document.Form1.TXT_DATE_OF_BIRTH_YY.value=v_yy;");
			out.println("  } }");		
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			
			/*out.println("function clear_fields(){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\" || document.Form1.hid_help_type.value==\"999\" ) {" ); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value =''; ");
			out.println("    document.Form1.TXT_CLIENT_CODE.focus(); ");
			out.println("   }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("		clear_bank_code(document.Form1.hid_lineno.value);"); 
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("		clear_branch_code(document.Form1.hid_lineno.value);"); 
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
			out.println("    document.Form1.TXT_CITY_CODE.value =''; ");
			out.println("    document.Form1.TXT_CITY_CODE.focus(); ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"77\") {" ); 
			out.println("    document.Form1.TXT_INQUARY_NO.value =''; ");
			out.println("    document.Form1.TXT_INQUARY_NO.focus(); ");
			out.println("  }		"); 
			out.println("}		"); */
			/*
			out.println("function clear_bank_code(lineno) {"); 
			out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
			out.println("    document.Form1.elements[m_bank].value='';"); 
			out.println("    document.Form1.elements[m_bank].focus();"); 
			out.println("}");
			
			out.println("function clear_branch_code(lineno) {"); 
			out.println("    m_branch=\"TXT_BRANCH_CODE\"+lineno");
			out.println("    document.Form1.elements[m_branch].value='';"); 
			out.println("    document.Form1.elements[m_branch].focus();"); 
			out.println("}");
			*/
			
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
			
			/*out.println(" window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No);");*/
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("    document.Form1.TXT_TOT_INCOME.value =''; ");
			out.println("    document.Form1.TXT_TOT_EXPENSE.value ='';");
			out.println("    document.Form1.TXT_NET_INCOME.value ='';");
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"999\"){"); 
			out.println("		help_update_value_assign_999();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_bank(document.Form1.hid_lineno.value);"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_branch(document.Form1.hid_lineno.value);"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_city();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"77\"){"); 
			out.println("		help_value_assign_inq();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"76\"){"); 
			out.println("		help_value_assign_postal_code();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"121\"){");  //added by nuwan de silva 07-08-07
			out.println("		help_value_assign_client_code_creation(\"C\");"); 
			
			
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"101\"){"); 
			out.println("    document.Form1.TXT_BUS_SECT.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_BUS_SECT_DES.value=oBj.valout[4];");
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"102\"){"); 
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BUS_SECT_DES_MAIN.value=oBj.valout[3];");
			out.println("		}  }"); 
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	}} "); 
			//out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}}	"); 
			//out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	} } }	"); 
			/*
						out.println("function Prev(Start,End,Hid_No){"); 
						out.println("    HelpBox(Start,End,Hid_No);"); 
						out.println("}"); 
						
						out.println("function Next (Start,End,Hid_No){"); 
						out.println("    HelpBox(Start,End,Hid_No);"); 
						out.println("}"); */
			
			/*		out.println("function help_button_bank(lineno) {"); 
					//out.println(" alert('lineno ' +lineno); ");
					out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
					out.println("    document.Form1.hid_lineno.value=lineno;"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
					out.println("    m_criteria = document.Form1.elements[m_bank].value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}");
				*/	
			/*
				out.println("function help_button_branch(lineno) {"); 
				out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
				out.println("    m_branch=\"TXT_BRANCH_CODE\"+lineno");
				out.println("    m_bank_val = document.Form1.elements[m_bank].value");
				out.println("    m_branch_val = document.Form1.elements[m_branch].value");
				out.println("    document.Form1.hid_lineno.value=lineno;"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
				out.println("    m_criteria =document.Form1.elements[m_branch].value+\"@\"+document.Form1.elements[m_bank].value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','8');"); 
				out.println("}");
			
				out.println("function help_value_assign_bank(lineno) {"); 
				out.println("    m_bank=\"TXT_BANK_CODE\"+lineno");
				out.println("    document.Form1.elements[m_bank].value=oBj.valout[2];"); 
				out.println("    document.Form1.elements[\"TXT_BANK_NAME\"+lineno].value=oBj.valout[3];"); 
				
				out.println("}"); 
				
				out.println("function help_value_assign_branch(lineno) {"); 
				out.println("    m_branch=\"TXT_BRANCH_CODE\"+lineno");
				out.println("    document.Form1.elements[m_branch].value=oBj.valout[2];"); 
				out.println("}");
					*/
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CAT_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CAT_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			/*	out.println("function help_value_assign_city() {"); 
				out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_CITY_DESC.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
			
				out.println("function help_value_assign_postal_code() {"); 
				out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_POSTAL_DESC.value=oBj.valout[3];"); 
				
				out.println("}"); */
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_REGISTERED_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REGISTERED_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println("    if(document.Form1.hid_client_type.value=='I'){");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_IND_sql1\";"); 
			//out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql1\";");
			out.println("     if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			//out.println("     } ");
			out.println("     }else{");
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";}"); 		
			out.println("     HelpBox('1','10','38');"); 
			//out.println("    }"); 
			out.println("    }else {");
			//out.println("    alert(' Corporate' );");
			out.println("    document.Form1.hid_help_type.value=\"999\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_COR_sql1\";"); 
			out.println("     if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			//out.println("     } ");
			out.println("     }else{");
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";}"); 
			out.println("     HelpBox('1','10','26');"); 
			out.println("     }} ");
			//out.println("}"); 
			
			
			/*out.println("function help_button_city() {");
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ||document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@\"+\"N@\";"); 
			out.println("    } ");
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			*/
			out.println("function help_update_temp() {"); 
			out.println("    if(document.Form1.hid_client_type.value=='I'){");
			//out.println("    alert(' Corporate' );");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("     m_sql = \"m_help_TXT_CLIENT_CODE_IND_TMP_sql1\";"); 
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";");
			out.println("    HelpBox('1','10','38');"); 
			//out.println("    }"); 
			out.println("    }else {");
			out.println("    document.Form1.hid_help_type.value=\"999\";"); 
			out.println("     m_sql = \"m_help_TXT_CLIENT_CODE_COR_TMP_sql1\";"); 
			out.println("     m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','26');"); 
			out.println("     }} ");
			//out.println("}");
			
			out.println("function assign_date_of_birth_values(dval){");
			out.println("document.Form1.TXT_DATE_OF_BIRTH_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_DATE_OF_BIRTH_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_DATE_OF_BIRTH_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("   assign_help_status('H3'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[4];"); 
			out.println("  if(oBj.valout[5]=='' || oBj.valout[5]=='null' || oBj.valout[5]=='-'){");
			out.println("    document.Form1.TXT_FIRST_NAME.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[5];");
			out.println("  }");
			
			out.println("  if(oBj.valout[6]==''||oBj.valout[6]=='null'){");
			out.println("    document.Form1.TXT_SURNAME.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_SURNAME.value=oBj.valout[6];");
			//out.println("  }");
			
			out.println("  }if(oBj.valout[11]==''||oBj.valout[11]=='null'){");
			out.println("    document.Form1.TXT_INITIALS.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_INITIALS.value=oBj.valout[11];");
			//out.println("  }");
			
			out.println("  }if(oBj.valout[12]==''||oBj.valout[12]=='null'){");
			out.println("    document.Form1.TXT_FULL_NAME_I.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_FULL_NAME_I.value=oBj.valout[12];");
			//out.println("  }");
			
			out.println("  }if(oBj.valout[13]==''||oBj.valout[13]=='null'){");
			out.println("    document.Form1.TXT_OTHER_NAME.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_OTHER_NAME.value=oBj.valout[13];");
			//out.println("  }");
			
			out.println("  }if(oBj.valout[14]==''||oBj.valout[14]=='null'){");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=oBj.valout[14];");
			//out.println("  }");
			
			out.println("  }if(oBj.valout[8]==''||oBj.valout[8]=='null'){");
			out.println("    document.Form1.TXT_TEL_NO.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[8];");
			out.println("  }");
			
			out.println("  if(oBj.valout[10]==''||oBj.valout[10]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_HOME.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_ADDRESS1_HOME.value=oBj.valout[10];");
			out.println("  }");
			
			out.println("  if(oBj.valout[15]==''||oBj.valout[15]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_HOME.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_ADDRESS2_HOME.value=oBj.valout[15];");
			out.println("  }");
			
			out.println("  if(oBj.valout[16]==''||oBj.valout[16]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=oBj.valout[16];");
			out.println("  }");
			
			out.println("  if(oBj.valout[17]==''||oBj.valout[17]=='null'){");
			out.println("    document.Form1.TXT_FAX_NO.value='';"); 
			//out.println("  }");
			out.println("  }else {");
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[17];");
			out.println("  }");
			
			out.println("  if(oBj.valout[9]==''||oBj.valout[9]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[9];");
			out.println("  }");
			
			out.println("  if(oBj.valout[18]==''||oBj.valout[18]=='null'){");
			out.println("    document.Form1.TXT_EMAIL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMAIL.value=oBj.valout[18];");
			out.println("  }");
			
			out.println("  if(oBj.valout[19]==''||oBj.valout[19]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=oBj.valout[19];");
			out.println("  }");
			
			out.println("  if(oBj.valout[20]==''||oBj.valout[20]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=oBj.valout[20];");
			out.println("  }");
			
			out.println("  if(oBj.valout[21]==''||oBj.valout[21]=='null'){");
			out.println("    document.Form1.TXT_EMP_NAME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_NAME.value=oBj.valout[21];");
			out.println("  }");
			
			out.println("  if(oBj.valout[22]==''||oBj.valout[22]=='null'){");
			out.println("    document.Form1.TXT_EMP_ADDRESS1.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_ADDRESS1.value=oBj.valout[22];");
			out.println("  }");
			
			out.println("  if(oBj.valout[23]==''||oBj.valout[23]=='null'){");
			out.println("    document.Form1.TXT_EMP_ADDRESS2.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_ADDRESS2.value=oBj.valout[23];");
			out.println("  }");
			
			out.println("  if(oBj.valout[24]==''||oBj.valout[24]=='null'){");
			out.println("    document.Form1.TXT_EMP_REFERENCE.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_REFERENCE.value=oBj.valout[24];");
			out.println("  }");
			
			out.println("  if(oBj.valout[25]==''||oBj.valout[25]=='null'){");
			out.println("    document.Form1.TXT_EMP_RDESIGNATION.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_RDESIGNATION.value=oBj.valout[25];");
			out.println("  }");
			
			out.println("  if(oBj.valout[26]==''||oBj.valout[26]=='null'){");
			out.println("    document.Form1.TXT_EMP_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_TEL_NO.value=oBj.valout[26];");
			out.println("  }");
			
			out.println("  if(oBj.valout[27]==''||oBj.valout[27]=='null'){");
			out.println("    document.Form1.TXT_EMP_FAX_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_FAX_NO.value=oBj.valout[27];");
			out.println("  }");
			
			out.println("  if(oBj.valout[28]==''||oBj.valout[28]=='null'){");
			out.println("    document.Form1.TXT_NAME_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NAME_REL.value=oBj.valout[28];");
			out.println("  }");
			
			out.println("  if(oBj.valout[29]==''||oBj.valout[29]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS1_REL.value=oBj.valout[29];");
			out.println("  }");
			
			out.println("  if(oBj.valout[30]==''||oBj.valout[30]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS2_REL.value=oBj.valout[30];");
			out.println("  }");
			
			out.println("  if(oBj.valout[31]==''||oBj.valout[31]=='null'){");
			out.println("    document.Form1.TXT_RELATIONSHIP.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_RELATIONSHIP.value=oBj.valout[31];");
			out.println("  }");
			
			out.println("  if(oBj.valout[32]==''||oBj.valout[32]=='null'){");
			out.println("    document.Form1.TXT_HOME_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_HOME_TEL_NO.value=oBj.valout[32];");
			out.println("  }");
			
			out.println("  if(oBj.valout[33]==''||oBj.valout[33]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO_REL.value=oBj.valout[33];");
			out.println("  }");
			
			out.println("  if(oBj.valout[34]==''||oBj.valout[34]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_MOBILE_NO_REL.value=oBj.valout[34];");
			out.println("  }");
			
			out.println("  if(oBj.valout[7]==''||oBj.valout[7]=='null'){");
			out.println("    document.Form1.TXT_NIC_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[7];");
			//	out.println("    document.Form1.TXT_NIC_NO.disabled=true;"); // added by udara 25-09-2015 KAN 2016-08-12
			out.println("  }");
			
			out.println("  if(oBj.valout[35]==''||oBj.valout[35]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_DD.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_MM.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_YY.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    assign_date_of_birth_values(oBj.valout[35]);");
			out.println("  }");
			
			out.println("  if(oBj.valout[36]==''||oBj.valout[36]=='null'){");
			out.println("    document.Form1.TXT_PASSPORT_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_PASSPORT_NO.value=oBj.valout[36];");
			out.println("  }");
			
			out.println("  if(oBj.valout[37]==''||oBj.valout[37]=='null'){");
			out.println("    document.Form1.TXT_NATIONALITY.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[37];");
			out.println("  }");
			
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=oBj.valout[38];"); 
			out.println("    document.Form1.TXT_GENDER.value=oBj.valout[39];"); 
			
			out.println("  if(oBj.valout[40]==''||oBj.valout[40]=='null'){");
			out.println("    document.Form1.TXT_BA_NATURE_OF_BUSINESS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_NATURE_OF_BUSINESS.value=oBj.valout[40];");
			out.println("  }");
			
			out.println("  if(oBj.valout[41]==''||oBj.valout[41]=='null'){");
			out.println("    document.Form1.TXT_BA_PROFESSION.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_PROFESSION.value=oBj.valout[41];");
			out.println("  }");
			
			out.println("  if(oBj.valout[43]==''||oBj.valout[43]=='null'){");
			out.println("    document.Form1.TXT_BA_DESIGNATION.value='';"); 
			out.println("  }");
			out.println("  else {");
			//out.println("  alert('  BA DESIGNA @@'+oBj.valout[42] ); ");
			out.println("    document.Form1.TXT_BA_DESIGNATION.value=oBj.valout[43];");
			out.println("  }");
			
			out.println("  if(oBj.valout[42]==''||oBj.valout[42]=='null'){");
			out.println("    document.Form1.TXT_BA_QUALIFICATIONS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_QUALIFICATIONS.value=oBj.valout[42];");
			out.println("  }");
			
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("  if(oBj.valout[51]==''||oBj.valout[51]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT.value='';"); 
			out.println("  }");
			out.println("  else {");
			
			//out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT.value=oBj.valout[51];");
			out.println("  }");
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("  if(oBj.valout[52]==''||oBj.valout[52]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value='';"); 
			out.println("  }");
			out.println("  else {");
			
			//out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value=oBj.valout[52];");
			//out.println("    document.Form1.TXT_BUS_SECT_DES_MAIN.value=oBj.valout[52];");
			
			out.println("  }");  //bbbbbbbbbbbb
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("  if(oBj.valout[44]==''||oBj.valout[44]=='null'){");
			out.println("    document.Form1.TXT_NO_CHILD.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NO_CHILD.value=oBj.valout[44];");
			out.println("  }");
			
			out.println("  if(oBj.valout[45]==''||oBj.valout[45]=='null'){");
			out.println("    document.Form1.TXT_TOT_DEP.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_TOT_DEP.value=oBj.valout[45];");
			out.println("  }");
			
			out.println("  if(oBj.valout[46]==''||oBj.valout[46]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[46];");
			out.println("  }");
			
			out.println("  if(oBj.valout[47]==''||oBj.valout[47]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[47];");
			out.println("  }");
			
			out.println("  if(oBj.valout[48]==''||oBj.valout[48]=='null'){");
			out.println("    document.Form1.TXT_DRIVING_LICENSE_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DRIVING_LICENSE_NO.value=oBj.valout[48];");
			out.println("  }");
			out.println("  assign_postal_code(oBj.valout[49]);");
			//out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[49];");
			
			out.println("  if(oBj.valout[50]==''||oBj.valout[50]=='null'){");
			out.println("    document.Form1.TXT_GRIB_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_GRIB_NO.value=oBj.valout[50];");
			out.println("  }");
			
			out.println("  assign_individual_rest_data(oBj);"); //added by nuwan de silva 22-06-07
			
			out.println("}"); 
			
			out.println("  var from_screen = '"+fscreen+"';"); // Thamali on 2010.12.27
			
			out.println("function assign_data_individual(data_vec) {"); 
			
			out.println("   assign_help_status('H3'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("    document.Form1.TXT_TITLE.value=data_vec[2];"); 
			out.println("  if(data_vec[3]==''||data_vec[3]=='null'){");
			out.println("    document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[3];");
			out.println("  }");
			
			out.println("  if(data_vec[4]=='' || data_vec[4]=='null'){");
			out.println("    document.Form1.TXT_SURNAME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_SURNAME.value=data_vec[4];");
			out.println("  }");
			
			out.println("  if(data_vec[5]==''||data_vec[5]=='null'){");
			out.println("    document.Form1.TXT_INITIALS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_INITIALS.value=data_vec[5];");
			out.println("  }");
			
			out.println("  if(data_vec[6]==''||data_vec[6]=='null'){");
			out.println("    document.Form1.TXT_FULL_NAME_I.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_FULL_NAME_I.value=data_vec[6];");
			out.println("  }");
			
			out.println("  if(data_vec[7]==''||data_vec[7]=='null'){");
			out.println("    document.Form1.TXT_OTHER_NAME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_OTHER_NAME.value=data_vec[7];");
			out.println("  }");
			
			out.println("  if(data_vec[8]==''||data_vec[8]=='null'){");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=data_vec[8];");
			out.println("  }");
			
			out.println("  if(data_vec[9]==''||data_vec[9]=='null'){");
			out.println("    document.Form1.TXT_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[9];");
			out.println("  }");
			
			out.println("  if(data_vec[10]==''||data_vec[10]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_HOME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS1_HOME.value=data_vec[10];");
			out.println("  }");
			
			out.println("  if(data_vec[11]==''||data_vec[11]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_HOME.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS2_HOME.value=data_vec[11];");
			out.println("  }");
			
			out.println("  if(data_vec[12]==''||data_vec[12]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[12];");
			out.println("  }");
			
			out.println("  if(data_vec[13]==''||data_vec[13]=='null'){");
			out.println("    document.Form1.TXT_FAX_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[13];");
			out.println("  }");
			
			out.println("  if(data_vec[14]==''||data_vec[14]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[14];");
			out.println("  }");
			
			out.println("  if(data_vec[15]==''||data_vec[15]=='null'){");
			out.println("    document.Form1.TXT_EMAIL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMAIL.value=data_vec[15];");
			out.println("  }");
			
			out.println("  if(data_vec[16]==''||data_vec[16]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=data_vec[16];");
			out.println("  }");
			
			out.println("  if(data_vec[17]==''||data_vec[17]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=data_vec[17];");
			out.println("  }");
			
			out.println("  if(data_vec[18]==''||data_vec[18]=='null'){");
			out.println("    document.Form1.TXT_EMP_NAME.value='';"); 
			out.println("  }");
			out.println("  else {");
			//out.println("    document.Form1.TXT_EMP_NAME.value=data_vec[18];"); // commented by udara on 05-08-2011
			out.println("    document.Form1.TXT_EMP_NAME.value=data_vec[18].replace(/\\$/g,'&');"); // added by udara on 05-08-2011
			
			out.println("  }");
			//#####################	
			out.println("  if(data_vec[19]==''||data_vec[19]=='null'){");
			out.println("    document.Form1.TXT_EMP_ADDRESS1.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_ADDRESS1.value=data_vec[19];");
			out.println("  }");
			
			out.println("  if(data_vec[20]==''||data_vec[20]=='null'){");
			out.println("    document.Form1.TXT_EMP_ADDRESS2.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_ADDRESS2.value=data_vec[20];");
			out.println("  }");
			
			out.println("  if(data_vec[21]==''||data_vec[21]=='null'){");
			out.println("    document.Form1.TXT_EMP_REFERENCE.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_REFERENCE.value=data_vec[21];");
			out.println("  }");
			
			out.println("  if(data_vec[22]==''||data_vec[22]=='null'){");
			out.println("    document.Form1.TXT_EMP_RDESIGNATION.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_RDESIGNATION.value=data_vec[22];");
			out.println("  }");
			
			out.println("  if(data_vec[23]==''||data_vec[23]=='null'){");
			out.println("    document.Form1.TXT_EMP_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_TEL_NO.value=data_vec[23];");
			out.println("  }");
			
			out.println("  if(data_vec[24]==''||data_vec[24]=='null'){");
			out.println("    document.Form1.TXT_EMP_FAX_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_EMP_FAX_NO.value=data_vec[24];");
			out.println("  }");
			
			out.println("  if(data_vec[25]==''||data_vec[25]=='null'){");
			out.println("    document.Form1.TXT_NAME_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NAME_REL.value=data_vec[25];");
			out.println("  }");
			
			out.println("  if(data_vec[26]==''||data_vec[26]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS1_REL.value=data_vec[26];");
			out.println("  }");
			
			out.println("  if(data_vec[27]==''||data_vec[27]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_ADDRESS2_REL.value=data_vec[27];");
			out.println("  }");
			
			out.println("  if(data_vec[28]==''||data_vec[28]=='null'){");
			out.println("    document.Form1.TXT_RELATIONSHIP.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_RELATIONSHIP.value=data_vec[28];");
			out.println("  }");
			
			out.println("  if(data_vec[29]==''||data_vec[29]=='null'){");
			out.println("    document.Form1.TXT_HOME_TEL_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_HOME_TEL_NO.value=data_vec[29];");
			out.println("  }");
			
			out.println("  if(data_vec[30]==''||data_vec[30]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO_REL.value=data_vec[30];");
			out.println("  }");
			
			out.println("  if(data_vec[31]==''||data_vec[31]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO_REL.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_MOBILE_NO_REL.value=data_vec[31];");
			out.println("  }");
			
			out.println("  if(data_vec[32]==''||data_vec[32]=='null'){");
			out.println("    document.Form1.TXT_NIC_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NIC_NO.value=data_vec[32];");
			//	out.println("    document.Form1.TXT_NIC_NO.disabled=true;"); // added by udara 25-09-2015 KAN 2016-08-19
			out.println("  }");
			
			out.println("  if(data_vec[56]==''||data_vec[56]=='null' ||data_vec[56]=='-'){");//Added by Kanchana on 2016-08-19
			out.println("    document.Form1.TXT_NIC_NO_OLD.value='';");
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NIC_NO_OLD.value=data_vec[56];"); //Added by Kanchana on 2016-08-19 
			out.println("  }");	
			
			out.println("  if(data_vec[33]==''||data_vec[33]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_DD.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_MM.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH_YY.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    assign_date_of_birth_values(data_vec[33]);");
			out.println("  }");
			
			out.println("  if(data_vec[34]==''||data_vec[34]=='null'){");
			out.println("    document.Form1.TXT_PASSPORT_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_PASSPORT_NO.value=data_vec[34];");
			out.println("  }");
			
			out.println("  if(data_vec[35]==''||data_vec[35]=='null'){");
			out.println("    document.Form1.TXT_NATIONALITY.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NATIONALITY.value=data_vec[35];");
			out.println("  }");
			
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=data_vec[36];"); 
			out.println("    document.Form1.TXT_GENDER.value=data_vec[37];"); 
			
			out.println("  if(data_vec[38]==''||data_vec[38]=='null'){");
			out.println("    document.Form1.TXT_BA_NATURE_OF_BUSINESS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_NATURE_OF_BUSINESS.value=data_vec[38];");
			out.println("  }");
			
			out.println("  if(data_vec[39]==''||data_vec[39]=='null'){");
			out.println("    document.Form1.TXT_BA_PROFESSION.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_PROFESSION.value=data_vec[39];");
			out.println("  }");
			
			out.println("  if(data_vec[41]==''||data_vec[41]=='null'){");
			out.println("    document.Form1.TXT_BA_DESIGNATION.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_DESIGNATION.value=data_vec[41];");
			out.println("  }");
			
			out.println("  if(data_vec[40]==''||data_vec[40]=='null'){");
			out.println("    document.Form1.TXT_BA_QUALIFICATIONS.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_BA_QUALIFICATIONS.value=data_vec[40];");
			out.println("  }");
			
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			
			out.println("  if(data_vec[49]==' ' || data_vec[49]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT.value='';");
			out.println("  }");
			
			out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT.value=data_vec[49];");
			out.println("  }");
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			
			out.println("  if(data_vec[50]==' ' || data_vec[50]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value='';");
			out.println("  }");
			
			out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value=data_vec[50];");
			out.println("  }");
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("  if(data_vec[42]==''||data_vec[42]=='null'){");
			out.println("    document.Form1.TXT_NO_CHILD.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_NO_CHILD.value=data_vec[42];");
			out.println("  }");
			
			out.println("  if(data_vec[43]==''||data_vec[43]=='null'){");
			out.println("    document.Form1.TXT_TOT_DEP.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_TOT_DEP.value=data_vec[43];");
			out.println("  }");
			
			out.println("  if(data_vec[44]==''||data_vec[44]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[44];");
			out.println("  }");
			
			//-----------------------------------------------------------------
			//--modified by : delanjali----------------------------------------
			out.println("  if(data_vec[51]==''||data_vec[51]=='null'){");
			out.println("    document.Form1.TXT_CITY_DESC.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_CITY_DESC.value=data_vec[51];");
			out.println("  }");	
			//-----------------------------------------------------------------
			out.println("  if(data_vec[45]==''||data_vec[45]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=data_vec[45];");
			out.println("  }");
			
			out.println("  if(data_vec[46]==''||data_vec[46]=='null'){");
			out.println("    document.Form1.TXT_DRIVING_LICENSE_NO.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_DRIVING_LICENSE_NO.value=data_vec[46];");
			out.println("  }");
			
			//out.println("    document.Form1.TXT_POSTAL_CODE.value=data_vec[47];");
			out.println("  assign_postal_code(data_vec[47]);");	
			//-----------------------------------------------------------------
			//--modified by : delanjali----------------------------------------
			/*out.println("  if(data_vec[52]==''||data_vec[52]=='null'){");
			out.println("    document.Form1.TXT_POSTAL_DESC.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_POSTAL_DESC.value=data_vec[52];");
			out.println("  }");	*/
			out.println("  assign_postal_desc(data_vec[52]);");
			out.println("  assign_sect_desc(data_vec[53]);");
			out.println("  assign_sub_sect_desc(data_vec[54]);");
			
			//-----------------------------------------------------------------
			
			out.println("  if(data_vec[48]=='' || data_vec[48]=='null'){");
			out.println("    document.Form1.TXT_GRIB_NO.value=''; }"); 
			out.println("  else {");
			out.println("    document.Form1.TXT_GRIB_NO.value=data_vec[48];");
			out.println("  }");
			
			out.println("enable_disable_client_data_ind(data_vec[55],from_screen);"); //added by nuwan de silva 03-06-2010  // Thamali on 2010.12.27
			/*
			if(m_screen.equals("xx") || m_screen.equals("G")){//Added by sandun on 11-05-2009
			//out.println("disable_client_data_ind();"); //added by nuwan de silva 18-03-2009
			out.println("enable_disable_client_data_ind(data_vec[55]);"); //added by nuwan de silva 03-06-2010
			}	
			*/
			
			
			
			out.println("}");
			
			
			out.println("function assign_data_corporate(data_vec) {"); 
			//out.println(" alert()")
			//out.println("   document.Form1.TXT_CLIENT_CODE.value=data_vec[2];"); 
			out.println("   assign_help_status('H_dir'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("   document.Form1.TXT_FULL_NAME_C.value=data_vec[1];"); 
			
			out.println(" if(data_vec[2]==''||data_vec[2]=='null'){");
			//out.println("alert(data_vec[15])");
			out.println("    document.Form1.TXT_REG_OFFICE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REG_OFFICE.value=data_vec[2];");
			out.println(" }");
			//out.println("    document.Form1.TXT_REG_OFFICE.value=data_vec[4];"); 
			//out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=data_vec[5];"); 
			out.println(" if(data_vec[3]==''||data_vec[3]=='null'){");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value='';"); 
			out.println(" }  else {");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=data_vec[3];");
			out.println(" }");
			out.println(" if(data_vec[4]==''||data_vec[4]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION1.value=data_vec[4];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_DESIGNATION1.value=data_vec[6];"); 
			//out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[7];"); 
			out.println(" if(data_vec[5]==''||data_vec[5]=='null'){");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[5];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[8];"); 
			out.println(" if(data_vec[6]==''||data_vec[6]=='null'){");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[6];");
			out.println(" }");
			out.println(" if(data_vec[7]==''||data_vec[7]=='null'){");
			out.println("    document.Form1.TXT_CORRESPONDENCE_OFF.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CORRESPONDENCE_OFF.value=data_vec[7];");
			out.println(" }");
			out.println(" if(data_vec[8]==''||data_vec[8]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION2.value=data_vec[8];");
			out.println(" }");
			
			out.println(" if(data_vec[9]==''||data_vec[9]=='null'){");
			out.println("    document.Form1.TXT_GEN_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_TEL_NO.value=data_vec[9];");
			out.println(" }");
			
			
			out.println(" if(data_vec[10]==''||data_vec[10]=='null'){");
			out.println("    document.Form1.TXT_GEN_FAX.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_FAX.value=data_vec[10];");
			out.println(" }");
			
			
			out.println(" if(data_vec[11]==''||data_vec[11]=='null'){");
			out.println("    document.Form1.TXT_GEN_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_EMAIL.value=data_vec[11];");
			out.println(" }");
			
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=data_vec[12];"); 
			
			out.println(" if(data_vec[13]==''||data_vec[13]=='null'){");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=data_vec[13];");
			out.println(" }");
			
			out.println(" if(data_vec[14]==''||data_vec[14]=='null'){");
			out.println("    document.Form1.TXT_F_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_TEL_NO.value=data_vec[14];");
			out.println(" }");
			out.println(" if(data_vec[15]==''||data_vec[15]=='null'){");
			out.println("    document.Form1.TXT_F_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_FAX_NO.value=data_vec[15];");
			out.println(" }");
			out.println(" if(data_vec[16]==''||data_vec[16]=='null'){");
			out.println("    document.Form1.TXT_F_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_EMAIL.value=data_vec[16];");
			out.println(" }");
			out.println("    document.Form1.TXT_LEGAL_STATUS.value=data_vec[17];"); 
			out.println("    document.Form1.hid_entity_type.value=data_vec[17];");
			out.println(" 	 document.Form1.hid_legal_stat.value=data_vec[17];");
			out.println(" if(data_vec[18]==''||data_vec[18]=='null'){");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=data_vec[18];");
			
			out.println(" }");
			out.println(" if(data_vec[19]==''||data_vec[19]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[19];");
			out.println(" }");
			out.println(" if(data_vec[20]==''||data_vec[20]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_DD.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_MM.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_YY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    assign_date_of_incor_values(data_vec[20]);");
			out.println(" }");
			out.println(" if(data_vec[21]==''||data_vec[21]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=data_vec[21];");
			out.println(" }");
			out.println(" if(data_vec[22]==''||data_vec[22]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_DATE_DD.value='';"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE_MM.value='';"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE_YY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    assign_vat_reg_date_values(data_vec[22]);");
			out.println(" }");
			
			out.println(" if(data_vec[23]==''||data_vec[23]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_REG.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_REG.value=data_vec[23];");
			out.println(" }");
			
			out.println(" if(data_vec[24]==''||data_vec[24]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_REG.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_REG.value=data_vec[24];");
			out.println(" }");
			
			out.println(" if(data_vec[25]==''||data_vec[25]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_COR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_COR.value=data_vec[25];");
			out.println(" }");
			
			out.println(" if(data_vec[26]==''||data_vec[26]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_COR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_COR.value=data_vec[26];");
			out.println(" }");
			
			out.println(" if(data_vec[27]==''||data_vec[27]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_FAC.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_FAC.value=data_vec[27];");
			out.println(" }");
			
			out.println(" if(data_vec[28]==''||data_vec[28]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_FAC.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_FAC.value=data_vec[28];");
			out.println(" }");
			
			out.println("  if(data_vec[29]==''||data_vec[29]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[29];");
			out.println("  }");	
			
			//-----------------------------------------------------------------
			//--modified by : delanjali----------------------------------------
			out.println("  if(data_vec[34]==''||data_vec[34]=='null'){");
			out.println("    document.Form1.TXT_CITY_DESC.value='';"); 
			out.println("  } else {");
			//out.println("  else {");
			out.println("    document.Form1.TXT_CITY_DESC.value=data_vec[34];");
			out.println("  }");	
			//-----------------------------------------------------------------
			
			out.println("  assign_postal_code(data_vec[30]);");
			//lllllllllll
			//-----------------------------------------------------------------
			//--modified by : delanjali----------------------------------------
			/*out.println("  if(data_vec[35]==''||data_vec[35]=='null'){");
			out.println("    document.Form1.TXT_POSTAL_DESC.value='';"); 
			out.println("  }");
			out.println("  else {");
			out.println("    document.Form1.TXT_POSTAL_DESC.value=data_vec[35];");
			out.println("  }");	*/
			out.println("  assign_postal_desc(data_vec[35]);");
			out.println("  assign_sect_desc(data_vec[36]);");
			out.println("  assign_sub_sect_desc(data_vec[37]);");
			
			
			//-----------------------------------------------------------------
			
			out.println("  if(data_vec[31]=='' || data_vec[31]=='null'){");
			out.println("    document.Form1.TXT_GRIB_NO.value='';"); 
			out.println("  } else {");
			out.println("    document.Form1.TXT_GRIB_NO.value=data_vec[31];");
			out.println("  }");			
			
			
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			
			out.println("  if(data_vec[32]==' ' || data_vec[32]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT.value='';");
			out.println("  }");
			
			out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT.value=data_vec[32];");
			out.println("  }");
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("  if(data_vec[33]==' ' || data_vec[33]=='null'){");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value='';");
			out.println("  }");
			
			out.println("  else {");
			out.println("    document.Form1.TXT_BUS_SECT_MAIN.value=data_vec[33];");
			out.println("  }");
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			
			/*out.println(" if(m_active_status=='N'){");
			out.println(" disable_client_data_cor();"); //added by nuwan de silva 18-03-2009
			out.println(" }");
			out.println(" else {");
			out.println(" enable_client_data_cor();"); //added by nuwan de silva 18-03-2009
			out.println(" }");
			*/
			out.println(" enable_disable_client_data_cor(data_vec[38],from_screen);"); //added by nuwan de silva 03-06-2010 // Thamali on 2010.12.27
			
			out.println("}");
			
			
			
			out.println("function help_update_value_assign_999() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("   assign_help_status('H_dir'); ");
			out.println("   makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("   document.Form1.TXT_FULL_NAME_C.value=oBj.valout[3];"); 
			
			out.println(" if(oBj.valout[4]==''||oBj.valout[4]=='null'){");
			//out.println("alert(oBj.valout[15])");
			out.println("    document.Form1.TXT_REG_OFFICE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REG_OFFICE.value=oBj.valout[4];");
			out.println(" }");
			out.println(" if(oBj.valout[6]==''||oBj.valout[6]=='null'){");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=oBj.valout[6];");
			out.println(" }");
			out.println(" if(oBj.valout[7]==''||oBj.valout[7]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION1.value=oBj.valout[7];");
			out.println(" }");
			out.println(" if(oBj.valout[8]==''||oBj.valout[8]=='null'){");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=oBj.valout[8];");
			out.println(" }");
			out.println(" if(oBj.valout[9]==''||oBj.valout[9]=='null'){");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=oBj.valout[9];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_CORRESPONDENCE_OFF.value=oBj.valout[9];"); 
			out.println(" if(oBj.valout[10]==''||oBj.valout[10]=='null'){");
			out.println("    document.Form1.TXT_CORRESPONDENCE_OFF.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CORRESPONDENCE_OFF.value=oBj.valout[10];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_DESIGNATION2.value=oBj.valout[10];"); 
			out.println(" if(oBj.valout[11]==''||oBj.valout[11]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION2.value=oBj.valout[11];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_GEN_TEL_NO.value=oBj.valout[11];"); 
			out.println(" if(oBj.valout[12]==''||oBj.valout[12]=='null'){");
			out.println("    document.Form1.TXT_GEN_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_TEL_NO.value=oBj.valout[12];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_GEN_FAX.value=oBj.valout[12];"); 
			out.println(" if(oBj.valout[13]==''||oBj.valout[13]=='null'){");
			out.println("    document.Form1.TXT_GEN_FAX.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_FAX.value=oBj.valout[13];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_GEN_EMAIL.value=oBj.valout[13];"); 
			out.println(" if(oBj.valout[14]==''||oBj.valout[14]=='null'){");
			out.println("    document.Form1.TXT_GEN_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GEN_EMAIL.value=oBj.valout[14];");
			out.println(" }");
			
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=oBj.valout[15];"); 
			
			//out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=oBj.valout[15];"); 
			out.println(" if(oBj.valout[16]==''||oBj.valout[16]=='null'){");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=oBj.valout[16];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_F_TEL_NO.value=oBj.valout[16];"); 
			out.println(" if(oBj.valout[17]==''||oBj.valout[17]=='null'){");
			out.println("    document.Form1.TXT_F_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_TEL_NO.value=oBj.valout[17];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_F_FAX_NO.value=oBj.valout[17];"); 
			out.println(" if(oBj.valout[18]==''||oBj.valout[18]=='null'){");
			out.println("    document.Form1.TXT_F_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_FAX_NO.value=oBj.valout[18];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_F_EMAIL.value=oBj.valout[18];"); 
			out.println(" if(oBj.valout[19]==''||oBj.valout[19]=='null'){");
			out.println("    document.Form1.TXT_F_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_EMAIL.value=oBj.valout[19];");
			out.println(" }");
			
			out.println("    document.Form1.TXT_LEGAL_STATUS.value=oBj.valout[20];"); 
			out.println("    document.Form1.hid_entity_type.value=oBj.valout[20];");
			out.println(" 	 document.Form1.hid_legal_stat.value=oBj.valout[20];");
			
			//out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=oBj.valout[20];"); 
			out.println(" if(oBj.valout[21]==''||oBj.valout[21]=='null'){");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=oBj.valout[21];");
			out.println("    check_number_decimal(document.Form1.TXT_ISSUED_SHARE_CAPITAL,20);");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[21];"); 
			out.println(" if(oBj.valout[5]==''||oBj.valout[5]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[5];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=oBj.valout[22];"); 
			out.println(" if(oBj.valout[22]==''||oBj.valout[22]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_DD.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_MM.value='';"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION_YY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    assign_date_of_incor_values(oBj.valout[22]);");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[23];"); 
			out.println(" if(oBj.valout[23]==''||oBj.valout[23]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[23];");
			out.println(" }");
			
			//out.println("    document.Form1.TXT_VAT_REG_DATE.value=oBj.valout[24];"); 
			out.println(" if(oBj.valout[24]==''||oBj.valout[24]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_DATE_DD.value='';"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE_MM.value='';"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE_YY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    assign_vat_reg_date_values(oBj.valout[24]);");
			out.println(" }");
			
			out.println(" if(oBj.valout[25]==''||oBj.valout[25]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_REG.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_REG.value=oBj.valout[25];");
			out.println(" }");
			
			out.println(" if(oBj.valout[26]==''||oBj.valout[26]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_REG.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_REG.value=oBj.valout[26];");
			out.println(" }");
			
			out.println(" if(oBj.valout[27]==''||oBj.valout[27]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_COR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_COR.value=oBj.valout[27];");
			out.println(" }");
			
			out.println(" if(oBj.valout[28]==''||oBj.valout[28]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_COR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_COR.value=oBj.valout[28];");
			out.println(" }");
			
			out.println(" if(oBj.valout[29]==''||oBj.valout[29]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS1_FAC.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS1_FAC.value=oBj.valout[29];");
			out.println(" }");
			
			out.println(" if(oBj.valout[30]==''||oBj.valout[30]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2_FAC.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2_FAC.value=oBj.valout[30];");
			out.println(" }");
			
			out.println(" if(oBj.valout[31]==''||oBj.valout[31]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[31];");
			out.println(" }");
			out.println("  assign_postal_code(oBj.valout[32]);");
			
			out.println(" if(oBj.valout[33]==''||oBj.valout[33]=='null'){");
			out.println("    document.Form1.TXT_GRIB_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GRIB_NO.value=oBj.valout[33];");
			out.println(" }");
			
			out.println(" assign_corparate_rest_data(oBj);"); //added by nuwan  de silva 22-06-07
			
			
			out.println("}"); 
			
			
			
			out.println("function assign_vat_reg_date_values(dval){");
			out.println("document.Form1.TXT_VAT_REG_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_VAT_REG_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_VAT_REG_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("function assign_date_of_incor_values(dval){");
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION_YY.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function select_content(obj) {");
			//out.println(" m_client_type_value='';");
			out.println(" m_client_code_value='';"); //added by nuwan de silva on 28-09-07
			//out.println("alert('screen name'+document.Form1.SCREEN_NAME.value);");
			//	out.println("alert('temp status'+document.Form1.hid_temp_status.value);");
			//	out.println("alert('temp value'+document.Form1.hid_status.value);");
			//			if  (!m_screen.equals("G")){
			
			//  out.println("   alert('m_close_status'+m_close_status);"); 
			out.println("   if(m_screen!='G'){");
			
			out.println("   if(obj=='I'){");
			out.println("  document.Form1.hid_client_type.value=obj;");
			out.println("  m_client_type_value=obj;"); //added by nuwan de silva on 28-09-07
			out.println("  document.Form1.TXT_CLIENT_CODE.value='';");
			//out.println(" alert('m_close_status'+m_close_status);");	
			///	out.println(" if(m_close_status!='Y'){"); //comment by nuwan de silva on 28-09-07
			///	out.println(" alert('individual'+obj);");
			out.println("	 add_individual();");
			///		out.println(" }"); //comment by nuwan de silva on 28-09-07
			
			out.println("  if(document.Form1.SCREEN_NAME.value =='NEW')"); 
			out.println("	 load_screen_status(\"NEW\");");
			out.println("	 else ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='EDIT' && document.Form1.hid_temp_status.value!=\"Y\" )"); 
			out.println("	 load_screen_status(\"EDIT\");");
			out.println("	 else ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='RACT')"); 
			out.println("	 load_screen_status(\"RACT\");");
			out.println("	 else ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT')"); 
			out.println("	 load_screen_status(\"DACT\");");
			out.println("	 else ");
			out.println("  if(document.Form1.hid_status.value==\"Temporary\")"); 
			//out.println("  if(document.Form1.SCREEN_NAME.value==\"Temporary\")"); 
			out.println("	 load_screen_status(\"TEMP\");");
			out.println("   }");
			
			out.println("   else  {");
			//out.println("  alert('cor'+obj);");
			out.println("  document.Form1.hid_client_type.value=obj;");
			out.println("  m_client_type_value=obj;");  //comment by nuwan de silva on 28-09-07
			
			out.println("  document.Form1.TXT_CLIENT_CODE.value='';");
			//out.println("  clear_individual();");
			//out.println("b_client_status=1;");
			out.println("	 add_corporate();");	
			out.println("  if(document.Form1.SCREEN_NAME.value =='EDIT' && document.Form1.hid_temp_status.value!=\"Y\")"); 
			out.println("	 load_screen_status(\"EDIT\");");
			out.println("	 else ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='RACT')"); 
			out.println("	 load_screen_status(\"RACT\");");
			out.println("	 else ");
			out.println("  if(document.Form1.SCREEN_NAME.value =='DACT')"); 
			out.println("	 load_screen_status(\"DACT\");");
			out.println("	 else ");
			//out.println("  if(document.Form1.SCREEN_NAME.value==\"Temporary\")"); 
			out.println("  if(document.Form1.hid_status.value==\"Temporary\")"); 
			out.println("	 load_screen_status(\"TEMP\");");
			out.println("   }");	
			/*}
			else{
			out.println(" document.Form1.TXT_CLIENT_TYPE.value='"+m_client_type+"'");	
			}*/
			out.println("   }");	
			out.println("   else {");	
			out.println(" document.Form1.TXT_CLIENT_TYPE.value='"+m_client_type+"'");	
			out.println("   }");	
			
			out.println("}");
			
			//out.println("document.Form1.hid_status.value=\"Temporary\";"); 
			
			out.println("function assign_value(val){ ");
			//out.println(" alert(' Hid Type @@ '+document.Form1.hid_entity_type.value); ");
			out.println("  if(val == document.Form1.hid_legal_stat.value) { ");
			//out.println(" alert(' Hid Type @@ '+document.Form1.hid_entity_type.value); ");
			out.println("  document.Form1.hid_entity_type.value = document.Form1.hid_legal_stat.value ");
			out.println("  makeRequest5(document.Form1.TXT_CLIENT_CODE);}");
			out.println("  else {");
			out.println("  document.Form1.hid_entity_type.value=val;");
			out.println("  makeRequest4(); }");
			out.println("}");
			
			
			out.println("function add_corporate() {");
			//out.println("alert('b_client_status'+b_client_status);");
			out.println("document.Form1.hid_entity_type.value='CORPORATE';");
			out.println("if(b_client_status==0){");//added by nuwan de silva 08-08-07
			out.println("makeRequest4();} "); 
			
			out.println("m_writedata='<table>'+");
			//out.println("'<tr >'+");
			out.println("'<tr>'+");
			out.println("'<td colspan=4 ><B><u> A.Details Of Applicant </u></B></td>'+"); 
			out.println("'</tr><tr >'+");
			//out.println("'<tr >'+"); 
			
			out.println("'<td width=\"20%\" ><DIV id=\"DIV_TXT_FULL_NAME_C\"  class=\"div_input\">Full Name *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FULL_NAME_C\"  style=\"width:200px;\" maxlength=\"250\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"20%\"></td>'+"); 
			out.println("'<td width=\"30%\"></td>'+"); 
			out.println("'</tr>'+");  
			
			out.println("'<tr></tr><tr></tr><tr></tr><tr></tr>'+");
			//out.println("'<tr></tr>'+");
			//out.println("'<tr ></tr>'+");
			//out.println("'<tr ></tr>'+");
			
			//Contact Details  start
			out.println("'<tr >'+");
			out.println("'<td ><b> Address </b></td>'+"); 
			out.println("'<td> </td>'+");
			out.println("'<td ><b> Contact Details </b></td>'+"); 
			out.println("'<td> </td>'+");
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Registered Office *</td>'+"); 
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_REG_OFFICE\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td ><DIV id=\"DIV_TXT_KEY_DECISION_MAKER\"  class=\"div_input\">Key Decision Maker *</div></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_KEY_DECISION_MAKER\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td  ><DIV id=\"DIV_TXT_ADDRESS1_REG\"  class=\"div_input\"> Address *</div></td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS1_REG\" maxlength=\"100\" size=\"45\" onBlur=\"set_Address1_Cor(this)\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_DESIGNATION1\"  class=\"div_input\"> Designation *</div></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION1\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS2_REG\" maxlength=\"100\" size=\"45\" onBlur=\"set_Address2_Cor(this)\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_DIRECT_TEL_NO\"  class=\"div_input\">Telephone - Direct *</div></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" maxlength=\"60\"  onBlur=\"Validate_Telephone_Number(this,1)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\">City Code *</div></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\" onblur=\"makeRequest3(document.Form1.TXT_CITY_CODE)\"  maxlength=\"10\" size=\"10\"> '+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_CITY\" value=\"...\" onClick=\"help_button_city()\">    </td></tr>'+"); 
			
			
			//-----------added by :delanjali------------------------------------------------------------------------------------------------------------------------------------------------
			//-----------date     :2007-06-20-----------------------------------------------------------------------------------------------------------------------------------------------
			out.println("'<tr >'+"); 
			//out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\">City Description </div></td>'+");  // commented by udara on 06-01-2012
			out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE_1\"  class=\"div_input\">City Description </div></td>'+"); // added by udara on 06-01-2012
			
			out.println("'<td ><input class=\"txt_input\" style=\"{width:200px}\" type=\"text\" name=\"TXT_CITY_DESC\" value=\"\" disabled></td>'+"); 
			//out.println("'</tr >'+"); 	
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("'<td >Contact Person for Payments</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" maxlength=\"200\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+");
			
			//Added By Nuwan De silva 31/01/2007		
			out.println("'<tr>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_POSTAL_CODE\"  class=\"div_input\">Postal Code *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_POSTAL_CODE\" maxlength=\"10\" size=\"10\" onblur=\"makeRequest_postal(document.Form1.TXT_POSTAL_CODE)\" > '+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_POSTAL_CODE\" value=\"...\" onClick=\"help_button_postal_code()\" >  </td>'+"); 
			out.println("'</tr>'+"); 
			//----modified by : delanjali-------------------------------------------------------------------------------------------------------------------------------------------------------------
			//----date : 2007-06-20-------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("'<tr>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_POSTAL_DESC\"  class=\"div_input\">Postal Description</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" style=\"{width:200px}\" name=\"TXT_POSTAL_DESC\" value=\"\" disabled > '+"); 
			out.println("'</td></tr>'+"); 
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("'<tr ></tr><tr ></tr><tr ></tr><tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Correspondence Office </td>'+"); 
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_CORRESPONDENCE_OFF\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td >Designation </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION2\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td  > Address </td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS1_COR\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Telephone - General </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_TEL_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS2_COR\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Fax - General  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_FAX\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,2)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >    </td><td >    </td>'+"); 
			//out.println("'<td >    </td>'+"); 
			out.println("'<td >E-mail - General </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_GEN_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr ></tr><tr ></tr><tr ></tr><tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Factory</td>'+"); 
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_FACTORY_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'</SELECT></td>'+");
			
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td >Contact Person</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_CONTACT_PERSON\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			out.println("'<td  > Address </td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS1_FAC\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Telephone </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_TEL_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\"  size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS2_FAC\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Fax   </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_FAX_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,2)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >    </td><td >    </td>'+"); 
			//out.println("'<td >    </td>'+"); 
			out.println("'<td >E-mail  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr ></tr><tr ></tr><tr ></tr><tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 
			//out.println("'<tr ></tr>'+"); 			
			
			out.println("'<tr >'+"); 
			out.println("'<td >Legal Status of Business </td>'+"); 
			
			out.println("'<td >'+");		
			out.println("'<select name=\"TXT_LEGAL_STATUS\" class=\"txt_input\"  onChange=\"assign_value(document.Form1.TXT_LEGAL_STATUS.value)\" >'+");
			
			rs=stmt.executeQuery(" SELECT ENTITY_CODE,DESCRIPTION "+
				" FROM   "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
				" WHERE  ENTITY_CODE NOT IN ('INDIVIDUAL') AND ACTIVE_STATUS='Y' "); //modified by nuwan de silva 03-07-07
			
			boolean more=rs.next();
			while(more){
				out.println("'<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>'+");			
				more=rs.next();
			} 
			out.println("'</SELECT></td>'+");
			//out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Issued Share Capital</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_ISSUED_SHARE_CAPITAL\" STYLE=\"{text-align:right;}\" onblur=\"check_number_decimal(this,20)\"  maxlength=\"25\" size=\"22\">Rs.</td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_BUSINESS_CERTIFICATE_NO\"  class=\"div_input\">Business Certificate No *</div> </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" onblur=\"makeRequest7(this)\"  maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_DATE_OF_INCORPORATION\"  class=\"div_input\">Date of Incorporation *</div></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION\" maxlength=\"7\" size=\"7\">[DD-MM-YYYY]</td>'+"); 
			out.println("'<TD ><input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION_DD\" maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION_MM\" maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION_YY\" maxlength=\"4\" size=\"4\" > [DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"1\")>   Calendar</a></TD>'+");	
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >VAT Registration No </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Date of VAT Registraiton  </td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" maxlength=\"7\" size=\"7\">[DD-MM-YYYY]</td>'+"); 
			out.println("'<TD ><input class=\"txt_input5\" type=\"text\" name=\"TXT_VAT_REG_DATE_DD\" maxlength=\"2\" size=\"2\">'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_VAT_REG_DATE_MM\" maxlength=\"2\" size=\"2\" >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_VAT_REG_DATE_YY\" maxlength=\"4\" size=\"4\" > [DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"2\")>   Calendar</a> </TD>'+");	
			out.println("'<td></td><td></td></tr>'+"); 
			//out.println("'<td></td>'+"); 
			//out.println("'</tr>'+");
			//out.println("'<tr ></tr>'+");
			
			
			out.println("'<tr >'+"); 
			out.println("'<td  ><DIV id=\"DIV_TXT_GRIB_NO\"  class=\"div_input\">CRIB No </DIV></td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_GRIB_NO\" size\"15\" maxlength=\"15\"></td>'+"); 
			out.println("'<td ></td><td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr><tr ></tr>'+");			
			//out.println("'<tr ></tr>'+");
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
			//add auditors----------
			out.println("header_auditors();"); 
			out.println("add_row_auditors();"); 
			
			
			out.println("add_label_credit_c();");
			out.println("header_credit_c();");
			out.println("add_row_credit_c();");
			
			out.println("add_label_customer();");
			out.println("header_customer();");
			out.println("add_row_customer();");
			
			out.println("add_label_nonrelated_ref('C');");
			out.println("header_nonrelated_ref('C');");
			out.println("add_row_nonrelated_ref('C');");
			
			//added by nuwan de silva 08-08-07
			//out.println("alert('b_client_status'+b_client_status);");
			/*out.println("if(b_client_status==1){");
			out.println("document.Form1.TXT_CLIENT_TYPE.value=m_client_type_value; ");
			out.println("document.Form1.TXT_CLIENT_CODE.value=m_client_code_value; ");
			out.println("document.Form1.hid_client_type.value=m_client_type_value; ");
			out.println("load_screen_status(\"EDIT\");"); 
			out.println("assign_data_values();"); 
	out.println("}");
	*/
			//out.println("if(b_new_data==0){");	
			out.println("view_client_status();");
			//out.println("}");
			
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
			//out.println("e_other_income.innerHTML='';  ");
			out.println("e_tot_income.innerHTML='';  ");
			out.println("e_data_income.innerHTML='';  ");
			out.println("e_header_expense.innerHTML='';  ");
			//out.println("e_other_expense.innerHTML='';  ");
			out.println("e_tot_expense.innerHTML='';  ");
			out.println("e_net_income.innerHTML='';  ");
			out.println("e_data_expense.innerHTML='';  ");
			
			out.println(" lineno_family=0;");
			out.println(" arr_size_family=0;");
			out.println("e_label_family_members.innerHTML='';  ");
			out.println("e_header_family_members.innerHTML='';  ");
			out.println("e_txt_family_members.innerHTML='';  ");
			out.println("e_add_but_family_members.innerHTML='';  ");
			out.println("e_row_tot_dep.innerHTML='';  ");
			
			/*out.println("    document.Form1.hid_count_bank.value =\"1\";   ");
			out.println("    document.Form1.hid_count_nonrel.value = \"1\";   ");
			out.println("    document.Form1.hid_count_in_ex.value=\"7\";   ");
			out.println("    document.Form1.hid_count_emp.value =\"1\";   ");
			out.println("    document.Form1.hid_count_fam.value =\"1\";   ");
			out.println("    document.Form1.hid_count_credit.value =\"1\";   ");*/
			
			out.println(" e_app_doc.innerHTML='';");
			out.println(" e_app_doc_header.innerHTML='';");
			out.println("}");
			
			
			out.println("function add_individual(){");
			out.println(" assign_help_status('H2'); ");
			out.println(" makeRequest1(); ");
			out.println(" lineno=0;  ");
			
			out.println(" H_val=\"H_nic\";  ");
			out.println(" document.Form1.hid_client_type.value='I';");
			out.println(" document.Form1.hid_entity_type.value='INDIVIDUAL';");
			out.println(" m_writedata='<table>'+");
			//out.println("'<tr >'+");
			out.println("'<tr>'+");
			//out.println("'<td colspan=4 ><B> A.DETAILS OF APPLICANT </B></td>'+"); 
			out.println("'<td colspan=4 ><B><u> A.Details Of Applicant </u></B></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"20%\" >Title </td>'+"); 
			out.println("'<td width=\"30%\">'+");
			out.println("'<select name=\"TXT_TITLE\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"MR\" SELECTED >Mr </option>'+");
			out.println("'<OPTION value=\"MRS\" >Mrs </option>'+");
			out.println("'<OPTION value=\"MISS\" >Miss </option>'+");
			out.println("'<OPTION value=\"DR\" >Dr </option>'+");
			out.println("'<OPTION value=\"PROF\" >Professor </option>'+");
			out.println("'<OPTION value=\"REV\" >Rev </option>'+");
			out.println("'<OPTION value=\"OTHER\" >Other </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<td width=\"20%\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr >'+"); 
			
			out.println("'<td  ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=\"div_input\">First Name </DIV> </td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_FIRST_NAME\" style=\"width:200px;\" maxlength=\"250\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
			
			out.println("'<td  ><DIV id=\"DIV_TXT_SURNAME\"  class=\"div_input\">Surname *</DIV></td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_SURNAME\" style=\"width:150px;\" maxlength=\"50\"></td>'+"); 
			out.println("'<td  >Initials </td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_INITIALS\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			
			//out.println("'<td >Other Names * </td>'+"); 
			out.println("'<td  ><DIV id=\"DIV_TXT_OTHER_NAME\"  class=\"div_input\">Other Names *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" style=\"width:250px;\" maxlength=\"198\" onBlur=\"set_FullName()\"></td>'+"); 
			out.println("'<td >VAT Registration No </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			//out.println("'<td  ></td>'+"); 
			//out.println("'<td  ></td>'+"); 
			out.println("'</tr>'+");  
			
			out.println("'<tr >'+"); 
			//out.println("'<td >Other Names </td>'+"); 
			//out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" maxlength=\"200\" size=\"100\"></td>'+"); 
			out.println("'<td  ><DIV id=\"DIV_TXT_FULL_NAME_I\"  class=\"div_input\">Full Name *</DIV></td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_FULL_NAME_I\" style=\"width:250px;\" maxlength=\"250\"></td>'+"); 
			out.println("'<td  ><DIV id=\"DIV_TXT_GRIB_NO\"  class=\"div_input\">CRIB No </DIV></td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_GRIB_NO\" size\"15\" maxlength=\"15\"></td>'+"); 
			//out.println("'<td ></td>'+"); 
			//out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr></tr><tr></tr><tr></tr><tr></tr>'+");
			/*out.println("'<tr></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");*/
			
			//out.println("'<td  ><DIV id=\"DIV_TXT_FULL_NAME_I\"  class=\"div_input\">Full Name *</DIV></td>'+"); 
			//out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_FULL_NAME_I\" maxlength=\"250\" size=\"100\"></td>'+"); 
			
			//Contact Details  start
			out.println("'<tr >'+");
			out.println("'<td colspan=4 ><b> Contact Details </b></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"20%\" >Residential Status </td>'+"); 
			out.println("'<td width=\"30%\">'+");
			out.println("'<select name=\"TXT_RESIDENTIAL_STATUS\" class=\"txt_input\" >'+");
			out.println("'<OPTION value=\"OWN\" SELECTED >Own </option>'+");
			out.println("'<OPTION value=\"RENT\" >Rent </option>'+");
			out.println("'<OPTION value=\"MORTGAGED\" >Mortgaged </option>'+");
			out.println("'<OPTION value=\"WITH_PARENTS\" >With Parents </option>'+");
			out.println("'<OPTION value=\"OTHER\" >Other </option>'+"); //Added by Prabash on 24-04-2012
			out.println("'</SELECT></td>'+");
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("'<td width=\"20%\" ><DIV id=\"DIV_TXT_TEL_NO\" class=\"div_input\"> Telephone Home </div> </td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength=\"60\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+");
			//out.println("'<td  ><DIV id=\"DIV_TXT_ADDRESS1_HOME\" class=\"div_input\">Home Address *</DIV> </td>'+"); // commented by udara 14-11-2025
			out.println("'<td  ><DIV id=\"DIV_TXT_ADDRESS1_HOME\" class=\"div_input\">Postal Address *</DIV> </td>'+"); // added by udara 14-11-2025
			out.println("'<td  ><input class=\"txt_input3\" type=\"text\" title=\"Please split address meaningfully and enter to address line 1 and 2. Also enter the city using below city help\" name=\"TXT_ADDRESS1_HOME\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td  >Telephone Office </td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" maxlength=\"60\"  onBlur=\"Validate_Telephone_Number(this,1)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" title=\"Please split address meaningfully and enter to address line 1 and 2. Also enter the city using below city help\" name=\"TXT_ADDRESS2_HOME\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Fax  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,2)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE\" class=\"div_input\">City Code *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\"  onblur=\"makeRequest3(document.Form1.TXT_CITY_CODE)\"  maxlength=\"10\" size=\"10\"  > '+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_CITY\" value=\"...\" onClick=\"help_button_city()\" >    </td></tr>'+"); 
			
			//-----------added by :delanjali------------------------------------------------------------------------------------------------------------------------------------------------
			//-----------date     :2007-06-20-----------------------------------------------------------------------------------------------------------------------------------------------
			out.println("'<tr >'+"); 
			//out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\">City Description </div></td>'+"); // commented by udara on 06-01-2012
			out.println("'<td ><DIV id=\"DIV_TXT_CITY_CODE_1\"  class=\"div_input\">City Description </div></td>'+");  // added by udara on 06-01-2012
			
			out.println("'<td ><input class=\"txt_input\" style=\"{width:200px}\" type=\"text\" name=\"TXT_CITY_DESC\" value=\"\" disabled></td>'+"); 
			//out.println("'</tr >'+"); 	
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			//out.println("'<td >Mobile  </td>'+"); 
			out.println("'<td > <DIV id=\"DIV_TXT_MOBILE_NO\"  class=\"div_input\"> Mobile *</DIV> </td>'+");  
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" maxlength=\"30\" onBlur=\"Validate_Telephone_Number(this,3)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+");
			
			//Added By Nuwan De silva 31/01/2007		
			/*out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_POSTAL_CODE\"  class=\"div_input\">Postal Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_POSTAL_CODE\" maxlength=\"10\" size=\"10\" >'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_MAIN\" value=\"Help\" onClick=\"help_update()\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
		*/
			//<DIV id=\"DIV_TXT_POSTAL_CODE\"  class=\"div_input\">Postal Code *</DIV>
			//<input class=\"txt_input\" type=\"text\" name=\"TXT_POSTAL_CODE\" maxlength=\"10\" size=\"10\" >
			
			out.println("'<tr >'+"); 
			//out.println("'<td ></td>'+"); 
			//out.println("'<td > </td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_POSTAL_CODE\" class=\"div_input\">Postal Code *</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_POSTAL_CODE\"  onblur=\"makeRequest_postal(document.Form1.TXT_POSTAL_CODE)\"  maxlength=\"10\" size=\"10\"  > '+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_POSTAL_CODE\" value=\"...\" onClick=\"help_button_postal_code()\" >    </td></tr>'+"); 
			
			
			//----modified by : delanjali-------------------------------------------------------------------------------------------------------------------------------------------------------------
			//----date : 2007-06-20-------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("'<tr>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_POSTAL_DESC\"  class=\"div_input\">Postal Description</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" style=\"{width:200px}\" name=\"TXT_POSTAL_DESC\" value=\"\" disabled > '+"); 
			out.println("'</td>'+"); 
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("'<td >Email </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_DURATION_AT_YEARS\" class=\"div_input\">Duration At Years </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\"  STYLE=\"{text-align:right;}\" onblur=\"check_number(this)\" maxlength=\"3\"   size=\"22\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_DURATION_AT_MONTHS\" class=\"div_input\">Duration At Months </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_MONTHS\" STYLE=\"{text-align:right;}\" onblur=\"check_number(this)\" maxlength=\"2\" size=\"2\"></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			//out.println("'<hr  width=\"100%\">'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_EMP_NAME\" class=\"div_input\">Employer </DIV></td>'+"); 
			//	out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_EMP_NAME\" maxlength=\"100\" size=\"45\"></td>'+"); //Comment by Prabash on 20-03-2012
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_EMP_NAME\" value=\"-\" maxlength=\"100\" size=\"45\"></td>'+");  //Added by Prabash on 20-03-2012
			out.println("'<td >Reference </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_REFERENCE\" maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Address  </td>'+"); 
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_EMP_ADDRESS1\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_EMP_RDESIGNATION\" class=\"div_input\">Designation </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_RDESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_EMP_ADDRESS2\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Telephone </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_TEL_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td >Fax </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMP_FAX_NO\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,2)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+"); 
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_NAME_REL\"  class=\"div_input\">Relative Name  </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_NAME_REL\" value=\"-\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_RELATIONSHIP\"  class=\"div_input\">Relationship </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_RELATIONSHIP\" value=\"-\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
			
			//out.println("'<td ><DIV id=\"DIV_TXT_ADDRESS1_REL\"  class=\"div_input\">Address </DIV></td>'+"); // commented by udara 14-11-2025
			out.println("'<td ><DIV id=\"DIV_TXT_ADDRESS1_REL\"  class=\"div_input\">Permanent Address * </DIV></td>'+"); // added by udara 14-11-2025
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS1_REL\" value=\"-\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td ><DIV id=\"DIV_TXT_HOME_TEL_NO\"  class=\"div_input\">Telephone - Home  </DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_HOME_TEL_NO\" value=\"-\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"10\"></td>'+");
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td ><input class=\"txt_input3\" type=\"text\" name=\"TXT_ADDRESS2_REL\" value=\"-\" maxlength=\"100\" size=\"45\"></td>'+"); 
			out.println("'<td >Telephone - Office </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO_REL\" value=\"-\" maxlength=\"60\" onBlur=\"Validate_Telephone_Number(this,1)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td > </td>'+"); 
			out.println("'<td >  </td>'+");  
			//out.println("'<td >Mobile </td>'+"); // commented by udara 23-07-2015
			out.println("'<td > <DIV id=\"DIV_TXT_MOBILE_NO_REL\"  class=\"div_input\"> Mobile </DIV> </td>'+");  // added by udara 23-07-2015
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO_REL\" value=\"-\" maxlength=\"30\"  onBlur=\"Validate_Telephone_Number(this,3)\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td > <DIV id=\"DIV_TXT_NIC_NO\"  class=\"div_input\"> Old/New NIC No **</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" onKeyUp=\"\" onblur=\"validate_NIC(this),check_blanks(document.Form1.TXT_NIC_NO),makeRequest2(document.Form1.TXT_NIC_NO)\"  maxlength=\"12\"  size=\"10\" ></td>'+"); // Modified by Thamali Jayatunga on 2009.10.20, Added validate_NIC to onblur 
			out.println("'<td ><DIV id=\"DIV_TXT_DATE_OF_BIRTH\"  class=\"div_input\">Date Of Birth *</DIV> </td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_BIRTH\" maxlength=\"7\" size=\"7\"></td>'+"); 
			out.println("'<TD ><input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_BIRTH_DD\" maxlength=\"2\" size=\"2\" onBlur=\"validate_client_dob(document.Form1.TXT_DATE_OF_BIRTH_DD,document.Form1.TXT_DATE_OF_BIRTH_MM,document.Form1.TXT_DATE_OF_BIRTH_YY)\">'+"); ////added by nuwan de silva 06-08-07
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_BIRTH_MM\" maxlength=\"2\" size=\"2\" onBlur=validate_client_dob(document.Form1.TXT_DATE_OF_BIRTH_DD,document.Form1.TXT_DATE_OF_BIRTH_MM,document.Form1.TXT_DATE_OF_BIRTH_YY) >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=\"TXT_DATE_OF_BIRTH_YY\"  maxlength=\"4\" size=\"4\"  onBlur=validate_client_dob(document.Form1.TXT_DATE_OF_BIRTH_DD,document.Form1.TXT_DATE_OF_BIRTH_MM,document.Form1.TXT_DATE_OF_BIRTH_YY) > [DD-MM-YYYY] </TD>'+");	
			//out.println("<td width='*%'></td>");
			out.println("'</tr>'+");
			
			
			//Added by Kanchana on 2016-06-07
			out.println("'<tr >'+");
			out.println("'<tr ><td > <DIV id=\"DIV_TXT_NIC_NO_OLD\"  class=\"div_input\">Old NIC No</br><div style=\"font-size=7.5pt;\">( fill only if New NIC also is available )</div> </DIV></td>'+");
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO_OLD\"  onblur=\"validate_NIC(this),check_blanks(document.Form1.TXT_NIC_NO_OLD),makeRequest2(document.Form1.TXT_NIC_NO_OLD),check_availability(document.Form1.TXT_NIC_NO);\"  maxlength=\"10\"  size=\"10\" ></td><td ></td><TD ></TD></tr>'+");     
			
			
			out.println("'<tr >'+"); 
			out.println("'<td > <DIV id=\"DIV_TXT_PASSPORT_NO\"  class=\"div_input\">Passport No **</DIV></td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_PASSPORT_NO\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td >Nationality </td>'+"); 
			
			out.println("'<td >'+");
			out.println("'<select name=\"TXT_NATIONALITY\" class=\"txt_input\" onchange=\"onChangeNationalityFunction();\" >'+");
			
			//THAMALI 2011.12.28
			String default_nationality = "";
			rs=stmt.executeQuery(" SELECT NATIONALITY_CODE,DESCRIPTION "+
				" FROM   "+m_schema_name+".AF_CO_MAS_NATIONALITY WHERE DEFAULT_VALUE = 'Y' ");
			
			more=rs.next();
			while (more){
				default_nationality = rs.getString(1);
				more=rs.next();
			} 
			
			
			rs=stmt.executeQuery(" SELECT NATIONALITY_CODE,DESCRIPTION "+
				" FROM   "+m_schema_name+".AF_CO_MAS_NATIONALITY WHERE  ACTIVE_STATUS='Y' ORDER BY DESCRIPTION ");
			
			more=rs.next();
			while (more){
				if (rs.getString(1).equals(default_nationality)) { //"SRILANKAN"
					out.println("'<option value=\""+rs.getString(1)+"\" selected=\"selected\">"+rs.getString(2)+"</option>'+");			
				}
				else {
					out.println("'<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>'+");			
				}
				more=rs.next();
			} 
			
			out.println("'</SELECT></td>'+"); 
			//out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_NATIONALITY\" maxlength=\"10\" size=\"10\"></td>'+"); 
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
			out.println("'<OPTION value=\"M\" SELECTED >Male </option>'+");
			out.println("'<OPTION value=\"F\" >Female </option>'+");
			out.println("'</SELECT></td>'+");
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+");
			
			//Contact Details  end
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			//Business Activities start
			out.println("'<tr >'+");
			out.println("'<td colspan=4 ><b> Business Activities </b></td>'+"); 
			out.println("'</tr>'+");
			
			out.println("'<tr >'+"); 
			out.println("'<td >Nature Of The Business </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_NATURE_OF_BUSINESS\" maxlength=\"100\" size=\"100\" >'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'<td ></td>'+"); 
			out.println("'</tr>'+"); 
			//--------------------------------------------------------------------------------------------------------------------------------------
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("'<tr>'+"); 	
			// out.println("'<td >Business Sector  </td>'+"); 
			out.println("'<td > <DIV id=\"DIV_BUSINESS_SECTOR\"  class=\"div_input\"> Business Sector *</DIV></td>'+"); // added by udara on 06-01-2012
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_MAIN\" maxlength=\"10\" onblur=\"makeRequest6(document.Form1.TXT_BUS_SECT_MAIN)\"  size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_BUS_SUB_MAIN\" value=\"...\" onClick=\"help_button_bus_sec()\"></td>'+"); 
			out.println("'<td >Business Sector Description </td>'+");
			out.println("'<td align=\"left\"><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_DES_MAIN\" style=\"width:220px;\" maxlength=\"200\" disabled></td>'+");
			out.println("'</tr>'+"); 	//mmmmmmmmmmmmmmmm
			//--------------------------------------------------------------------------------------------------------------------------------------
			
			
			//--modified by : delanjali-------------------------------------------------------------------------------------------------------------
			//--date				: 2007-07-19------------------------------------------------------------------------------------------------------------
			out.println("'<tr>'+"); 	
			out.println("'<td >Business Sub Sector  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT\" maxlength=\"10\" onblur=\"makeRequest10(document.Form1.TXT_BUS_SECT)\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_TXT_BUS_SUB\" value=\"...\" onClick=\"help_button_sub_sec()\"></td>'+"); 
			out.println("'<td >Business Sub Sector  Description</td>'+"); 
			out.println("'<td align=\"left\"><input class=\"txt_input\" type=\"text\" name=\"TXT_BUS_SECT_DES\" style=\"width:220px;\" maxlength=\"200\" disabled></td>'+");
			
			out.println("'</tr>'+"); 	
			
			out.println("'<tr >'+"); 
			out.println("'<td >Profession </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_PROFESSION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td >Designation </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_DESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td >Qualifications </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_BA_QUALIFICATIONS\" maxlength=\"50\" size=\"50\"></td>'+"); 
			out.println("'<td >Driving License No  </td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=\"TXT_DRIVING_LICENSE_NO\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			//Business Activities end
			
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			out.println("'<tr ></tr>'+");
			
			//Employment Start
			out.println("'<tr >'+");
			out.println("'<td ><b> Employment Track Record </b></td>'+"); 
			out.println("'</tr>'+");
			//Employment End
			
			out.println("'</table>';  ");
			out.println("e_mode.innerHTML=m_writedata;");
			
			//To clear corporate divs
			out.println("clear_corporate_client();");
			
			//out.println(" alert('lineno fam '+lineno_family); ");
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
			
			//Income/Expense
			out.println("add_label_income_expense();");
			out.println("header_income_expense();");
			
			//family members
			out.println("add_label_family();");
			out.println("header_family();");
			out.println("add_row_family();");
			out.println("add_row_tot_dependents();");
			out.println("}");
			
			/*	out.println(" function clear_corporate(){");
				out.println(" lineno_bank=0;");
				out.println(" arr_size_bank=0;");
				out.println(" arr_size_company=0; ");
				out.println(" lineno_company_dir=0; ");
				out.println(" lineno_subsidiaries=0; ");
				out.println(" arr_size_subsidiaries=0; ");
				out.println(" lineno_customer=0; ");
				out.println(" arr_size_customer=0; ");
				out.println(" lineno_auditors=0; ");
				out.println(" arr_size_auditors=0; ");
				
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
				
				out.println(" e_header_auditors.innerHTML='';");
				out.println(" e_txt_auditors.innerHTML='';");
				out.println(" e_add_but_auditors.innerHTML='';");
				
				
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
				
				*/
			
			/*	comment by nuwan de silva due to not enough spaces at the source code -this shifted to the validate_v2.js file*/
			//To Choose Corporate or indivdual
			/*out.println(" function assign_data_values(){"); 
			out.println(" if(document.Form1.hid_client_type.value=='I'){ ");
			out.println(" document.Form1.TXT_TOT_INCOME.value =''; ");
			out.println(" document.Form1.TXT_TOT_EXPENSE.value ='';");
			out.println(" document.Form1.TXT_NET_INCOME.value ='';");
			out.println(" assign_help_status('H_ind'); ");
			out.println(" makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println("  }");
			out.println(" else { ");
			out.println(" assign_help_status('H_cor'); ");
			out.println(" makeRequest(document.Form1.TXT_CLIENT_CODE); ");
			out.println(" }");
			out.println("}");
			
		*/
			
			out.println(" function assign_help_status(obj){");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("}");
			
			/*	out.println("function close_screen() {");
				out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
				out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
				out.println("		     if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
				out.println("		      window.close();"); 
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else { "); 
				out.println("		     close_window();"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("else	if(document.Form1.close2.value==\"Close\"){");
				out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
				out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		      window.close();"); 
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else { "); 
				out.println("		     close_window();"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("}");
				*/
			
			out.println(" function fill_client(){");
			//out.println("alert('fill client');");
			out.println("    document.Form1.HID_CLOSE_STS.value=m_close_status;");
			
			out.println(" if(\""+m_client_code+"\" !=\"\" && \""+m_client_type+"\" !=\"\" ){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value =\""+m_client_code+"\" ; ");
			out.println("    document.Form1.TXT_INQUARY_NO.value =\""+m_inquiry_no+"\" ; ");
			//out.println("    load_screen_status(\"EDIT\");");
			out.println("    document.Form1.TXT_CLIENT_TYPE.value =\""+m_client_type+"\" ; ");
			//out.println("    select_content(m_client_type);");
			out.println("    document.Form1.hid_client_type.value =\""+m_client_type+"\" ; ");
			out.println("    load_screen_status(\"EDIT\");");
			//out.println("    if(\""+m_inquiry_no+"\" !=\"\" && \""+m_inquiry_no+"\" !=\"-\" ){");
			//!!out.println("      document.Form1.TXT_INQUARY_NO.value =\""+m_inquiry_no+"\" ; ");
			out.println("      document.Form1.INQUARY_LINK_BUT.disabled =false ; ");
			//!!out.println("    document.Form1.TXT_CLIENT_CODE.value =\""+m_client_code+"\" ; ");
			//out.println("    document.Form1.TXT_CLIENT_CODE.focus(); ");
			//out.println("    assign_data_values(); ");
			out.println(" if(document.Form1.hid_app_screen.value=='APP_R' || document.Form1.hid_app_screen.value=='APP_N'  || document.Form1.hid_f_screen.value=='client_verification' ){");//[Data auto loading after assign the client code]
			out.println("    document.Form1.TXT_CLIENT_CODE.focus(); ");//[Added milinda]
			//out.println("    document.Form1.TXT_CLIENT_CODE.blur(); ");
			//out.println("window.close();");
			out.println("}else{");	
			out.println("  assign_data_values(); }");
			out.println("  }");
			
			out.println("}");
			
			
			out.println("function assign_data_inquiry(data_vec) {"); 
			
			out.println("    document.Form1.TXT_INQUARY_NO.value=data_vec[0];"); 
			
			//out.println("alert(data_vec[9]);");
			//out.println("alert('hid'+document.Form1.hid_client_type.value);");
			out.println(" if(data_vec[9]=='INDIVIDUAL'  && document.Form1.SCREEN_NAME.value ==\"NEW\" && document.Form1.hid_client_type.value==\"I\"  ){ "); //Modified By Nuwan De Silva 15-05-07
			
			out.println("  if(data_vec[1] != '-') ");
			//out.println("    document.Form1.TXT_FULL_NAME_I.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_OTHER_NAME.value=data_vec[1];"); 
			
			out.println("  if(data_vec[2] != '-') ");
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[2];"); 
			
			out.println("  if(data_vec[3] != '-') ");
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[3];"); 
			
			out.println("  if(data_vec[4] != '-') ");
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[4];"); 
			
			out.println("  if(data_vec[5] != '-') ");
			out.println("    document.Form1.TXT_ADDRESS1_HOME.value=data_vec[5];"); 
			
			out.println("  if(data_vec[7] != '-') ");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[7];"); 
			
			out.println("  if(data_vec[6] != '-') ");
			out.println("    document.Form1.TXT_ADDRESS2_HOME.value=data_vec[6];"); 
			
			out.println("  if(data_vec[10] != '-') ");
			out.println("    document.Form1.TXT_SURNAME.value=data_vec[10];"); 
			
			out.println("  if(data_vec[8] != '-') ");
			out.println("    document.Form1.TXT_NIC_NO.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_NIC_NO.focus(); ");
			out.println(" }"); 
			
			out.println(" else if( data_vec[9]!='INDIVIDUAL' && document.Form1.SCREEN_NAME.value ==\"NEW\" && document.Form1.hid_client_type.value!=\"I\") { ");
			
			out.println("  if(data_vec[1] != '-') ");
			out.println("    document.Form1.TXT_FULL_NAME_C.value=data_vec[1];"); 
			out.println("  if(data_vec[2] != '-') ");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[2];"); 
			out.println("  if(data_vec[5] != '-') ");
			out.println("    document.Form1.TXT_ADDRESS1_REG.value=data_vec[5];"); 
			out.println("  if(data_vec[7] != '-') ");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[7];"); 
			out.println("  if(data_vec[9] != '-') ");
			out.println("    document.Form1.TXT_LEGAL_STATUS.value=data_vec[9];"); 
			out.println("  if(data_vec[6] != '-') ");
			out.println("    document.Form1.TXT_ADDRESS2_REG.value=data_vec[6];"); 
			
			out.println("  if(data_vec[8] != '-') ");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[8];");   //added By Nuwan De Silva 18-05-07
			
			
			out.println(" }");
			
			out.println("}");
			//remove comement by waruna
			
			out.println(" function check_inquiry_no(){"); 
			out.println("  assign_help_status('H_inq'); ");
			out.println("  makeRequest(document.Form1.TXT_INQUARY_NO); ");
			out.println("}");
			
			out.println("function load_inquary(){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Inquiry?chksql=main_page&inquiry_no='+document.Form1.TXT_INQUARY_NO.value;"); 
			out.println("window.open(m_url,'displayWindow5','left=0,top=133,width=750,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			//add by waruna** set status if screen come from "change client"
			out.println("function set_status(){");
			out.println("chk_disa();");
			if(fscreen != null){
				if(fscreen.equalsIgnoreCase("chg_Cln_details")){
					
					out.println("document.Form1.hid_temp_status.value='"+m_active_status+"';");
					
					//out.println("alert('fffff');");	
				}
			}
			out.println("}");
			
			
			/*out.println("function check_change() {");
			out.println("hidchk='hid_chk1_'+"+m_row+"");
			out.println("if(document.Form1.CHK_ACK.checked==false){");
			out.println("document.Form1.CHK_ACK.value=\"0\"");
			out.println("}");
			out.println("else if(document.Form1.CHK_ACK.checked==true){");
			out.println("document.Form1.CHK_ACK.value=\"1\"");
			out.println("}");
			out.println("if(document.Form1.CHK_ACK.value==\"1\"){");
			out.println("window.opener.document.Form1.elements[hidchk].checked=true");
			out.println("window.opener.document.Form1.elements[hidchk].value=\"1\"");
			out.println("}");
			out.println("else if(document.Form1.CHK_ACK.value==\"0\"){");
			out.println("window.opener.document.Form1.elements[hidchk].checked=false");
			out.println("window.opener.document.Form1.elements[hidchk].value=\"0\"");
			out.println("}");
			out.println("}");
			
			
			out.println("function help_button_sub_sec() {");
			out.println("    document.Form1.hid_help_type.value=\"101\";"); 
			out.println("    m_sql = \"m_help_TXT_SUB_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BUS_SECT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_button_bus_sec() {");
			out.println("    document.Form1.hid_help_type.value=\"102\";"); 
			out.println("    m_sql = \"m_help_TXT_SECTOR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BUS_SECT_MAIN.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
		*/
			//out.println("function help_value_sub_sec() {"); 
			//out.println("    document.Form1.TXT_BUS_SECT.value=oBj.valout[2];"); 
			//out.println("}"); 
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),set_status()\">"); //load_inq_details()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_doc_lineno' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
			
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_entity_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_legal_stat' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_lineno' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_emp' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_bank' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_credit' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_nonrel' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_in_ex' VALUE=\"7\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_screen' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_f_screen' VALUE=\"\">");
			//Used to calculate Total income expense
			out.println("<INPUT TYPE='Hidden' NAME='hid_tot_in_ex' VALUE=\"0\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_count_in_ex' VALUE=\"7\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_fam' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_com_dir' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_ba' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_sub' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_credit_c' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_cus' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_count_aud' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_temp_status' VALUE=\"N\">"); 
			out.println("<input type=hidden name=\"HID_CLOSE_STS\" value=\"N\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<input type=hidden name='Hid_scr_name' value=\"AF_AD_CLIENT_CREATION\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_status' VALUE=\"\">"); 
			
			
			//out.println("<input type='Hidden' name='hid_nic_status' value=\"Y\">");
			
			//out.println("<INPUT TYPE='Hidden' NAME='hid_count_credit' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Creation Of Clients</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"but_new\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'  onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			
			// added by udara 27-08-2014
			if(m_app_screen.equals("Y")){
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit\"  onClick='load_screen_status(\"EDIT\")' value=\"Edit\" Disabled ></td>"); 
			}
			else{
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit\"  onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>"); 
			}
			// end by udara 27-08-2014
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit\"  onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");   // commented by udara 27-08-2014
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' name=\"dact\" onClick='load_screen_status(\"DACT\")' value=\"De-activate\" Disabled ></td>"); // added Disabled by udara 15-09-2014 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' name=\"ract\" onClick='load_screen_status(\"RACT\")' value=\"Re-activate\" Disabled ></td>"); // added Disabled by udara 15-09-2014 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"temp\"  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Temporary\");' onClick='load_screen_status(\"TEMP\")' value=\"Temp\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' name=\"save\"  onClick='save_window()' value=\"Save\"></td>"); //waruna 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Temporary Save\");' name=\"temp_save\"  onClick='save_window_temp()' value=\"Temp Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' name=\"help\" onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");' name=\"cancel\" onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' name=\"close\" onclick='close_screen2()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			
			out.println("<table  align='center' width='100%' class='table'>"); 
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
			out.println("<td width='14%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='29%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"validate_client_code()\" disabled >"); //,assign_data_values() validate_client_code()
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_client_code_validate()\" disabled>");  //help_update()
			out.println("<input class='but_input1' type='button' name='BUT_HELP_TEMP' value=\"Temp Help\" onClick=\"help_client_code_validate_temp()\" size='10' disabled></td>");  //help_update_temp() comment by nuwan de silva 08-08-07
			
			out.println("<td width='20%'><DIV id='DIV_TXT_INQUARY_NO'>Search Inquiry No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INQUARY_NO' maxlength='22' size='22' onblur=\"check_inquiry_no()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_INQUARY_NO' value=\"...\" onClick=\"help_button_inq()\">"); 
			out.println("<input class='but_input' style='width:60' type='button' name='INQUARY_LINK_BUT' value=\"Inquiry\" onClick=\"load_inquary()\" disabled></td >"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
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
			out.println("<td clospan=2 width=\"100%\"><DIV ID=e_mode_emp>  </DIV></td>");			
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
			out.println("<td width=\"100%\"><DIV ID=e_tot_expense>  </DIV></td>");				
			out.println("</tr>" );
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_net_income>  </DIV></td>");				
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
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_app_doc_header></DIV></td>");				
			out.println("</tr>" );
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_app_doc></DIV></td>");				
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
			out.println("</tr></table>" );
			//out.println("</table>");
			
			//Subsidiaries & Associated companies divs
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_label_subsidiaries> </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_header_subsidiaries></DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_txt_subsidiaries></DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_add_but_subsidiaries></DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			//Bank <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_label_bank> </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_bank_c>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_txt_bank_c>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_add_but_bank_c>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			//auditors 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td clospan=2 width=\"100%\"><DIV ID=e_header_auditors>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_txt_auditors>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>" );
			out.println("<td width=\"100%\"><DIV ID=e_add_but_auditors>  </DIV></td>");				
			out.println("</tr></table>" );
			//out.println("</table>");
			
			
			//B. DETAILS OF CREDIT FACILITIES  
			//########################################
			//Credit Facilities <div>s
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_label_credit_c>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_header_credit_c></DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_txt_credit_c></DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_add_but_credit_c>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			// customers & suppliers divs
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_label_customer> </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td clospan=2 width=\"100%\"><DIV ID=e_header_customer>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_txt_customer>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_add_but_customer>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			//Non related referees <div>s Corporate
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_label_nonrelated_c>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_header_nonrelated_c></DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_txt_nonrelated_c></DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>" );
			out.println("<tr><td width=\"100%\"><DIV ID=e_add_but_nonrelated_c>  </DIV></td></tr></table>");				
			//out.println("</tr></table>" );
			//out.println("</table>");
			
			/*	out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>" );
				out.println("<td width=\"100%\"><DIV ID=e_app_doc_header_c></DIV></td>");				
				out.println("</tr>" );
			out.println("</table>");
				
				/*out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>" );
				out.println("<td width=\"100%\"><DIV ID=e_app_doc_c></DIV></td>");				
				out.println("</tr>" );
			out.println("</table>");
				*/
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
			if  (m_screen.equals("G")){
				out.println("<tr><td width='10%' align=\"left\"><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change('"+m_row+"')\" ></td></tr>");
				out.println("<tr><td width='10%' align=\"center\" ><input type=\"button\"  class='mainbut' style=\"width:140px\" name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td></tr>");  
				out.println("<tr></tr><tr></tr>");
				//out.println("<tr></tr>");
				
			}
			
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"but_new2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'  onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			
			// added by udara 27-08-2014
			if(m_app_screen.equals("Y")){
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit2\"  onClick='load_screen_status(\"EDIT\")' value=\"Edit\" Disabled ></td>"); 
			}
			else{
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit2\"  onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>"); 
			}
			// end by udara 27-08-2014
			
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"edit2\"  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'  onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");   // commented by udara 27-08-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"dact2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");'  onClick='load_screen_status(\"DACT\")' value=\"De-activate\" Disabled ></td>"); // added Disabled by udara 15-09-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"ract2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");'  onClick='load_screen_status(\"RACT\")' value=\"Re-activate\" Disabled ></td>"); // added Disabled by udara 15-09-2014  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"temp2\"  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Temporary\");' onClick='load_screen_status(\"TEMP\")' value=\"Temp\" disabled ></td>");  // added Disabled by udara 01-02-2018
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"save2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  //waruna2
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"temp_save2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Temporary Save\");'   onClick='save_window_temp()' value=\"Temp Save\" disabled ></td>");  // added Disabled by udara 01-02-2018
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"help2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'  onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"cancel2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			//if  (m_screen.equals("G")){
			//out.println("<td width='10%' align='center' ><input type=\"button\" style=\"width:140px\" class='mainbut' name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td>");  
			//}
			if  (!m_screen.equals("G")){
				out.println("<td width='10%' align='center' ><input type=\"button\" class='mainbut' name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Close\"></td>");  
			}
			
			out.println("</tr><tr>");
			//out.println("<tr>");
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr></table>");  
			//out.println("</tr></table>");
			//out.println("</table>");
			
			out.println("<table width='100%' >");
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box1'>System Administration - Client</td></tr></table>"); 
			//out.println("</tr></table>");
			//out.println("</table>");
			
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			//	out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/nic_validation_DB.js'></SCRIPT>"); //added by Kanchana.
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/reduce_space.js'></SCRIPT>"); // added by udara 01-02-2018
			out.println("</body></html>"); 
			//out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) 
		{
			
			ByteArrayOutputStream ostrtest = new ByteArrayOutputStream();
			ex.printStackTrace(new PrintStream(ostrtest));
			out.println("test"+ostrtest.toString());
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			try{if(out!=null){out.close();}  }catch(Exception e){}
			try{if(rs!=null){rs.close();}    }catch(Exception e){}
			if(stmt!=null)try{stmt.close();}catch(Exception e){}
			//if(conn!=null)try{conn.close();}catch(Exception e){}
			
		}
	}
}
