

public class LAKDL_AF_MAS_display_client_creation_validation {

//public synchronized String before_submit(String m_class_url,String m_fschema_name, String m_screen,String m_save_close,String m_APP_NO,String m_no_of_rec,String fscreen){
public  String before_submit(String m_class_url,String m_fschema_name, String m_screen,String m_save_close,String m_APP_NO,String m_no_of_rec,String fscreen){

String code_string=""; 
	String saveJfileName="AF_MAS_save_client_creation";
	if(fscreen != null){
	    if(fscreen.equalsIgnoreCase("chg_Cln_details")){
		   	saveJfileName="AF_MAS_save_client_creation_app";
		}else if(fscreen.equalsIgnoreCase("CMS")){
			saveJfileName="AF_MAS_save_client_creation_cms";	
		}
	} 
			code_string=code_string+("function before_submit(){ "); 
			//code_string=code_string+(" count_contracts(m_active_status);"); //added by nuwan de silva on 16-10-2008 
			code_string=code_string+(" m_status = document.Form1.hid_status.value ;");
			code_string=code_string+(" m_save_msg='Are you sure you want to Save ? ';");
			code_string=code_string+(" if(m_status == \"New\"){ ");
			code_string=code_string+(" m_save_msg = 'Are you sure you want to Save ? ';");
			code_string=code_string+(" }"); 
			code_string=code_string+(" else if(m_status == \"Edit\"){ ");
			code_string=code_string+(" m_save_msg = 'Are you sure you want to Modify ? ';");
			code_string=code_string+(" }"); 
			code_string=code_string+(" else if(m_status == \"Deactivate\"){ ");
			code_string=code_string+(" m_save_msg = 'Are you sure you want to Deactivate ? '");
			code_string=code_string+(" }"); 
			code_string=code_string+(" else if(m_status == \"Reactivate\"){ ");
			code_string=code_string+(" m_save_msg = 'Are you sure you want to Reactivate ? '");
			code_string=code_string+(" }"); 
			//======================
			code_string=code_string+(" if(m_status==\"Deactivate\" || m_status==\"Reactivate\" ){"); 
			code_string=code_string+("		if(document.Form1.hid_client_type.value == 'I'){"); 
			
			code_string=code_string+("		if(confirm(m_save_msg)){ "); 
			code_string=code_string+("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			code_string=code_string+("    document.Form1.elements[i].disabled=false;");
			code_string=code_string+("   }");
			code_string=code_string+("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+saveJfileName+"?countemp='+lineno+'"+
											"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
											"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+                                                   
											"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&arr_size_in_ex='+arr_size_income;");  
			code_string=code_string+("		document.Form1.submit();	"); 
			code_string=code_string+("		}"); 

			code_string=code_string+(" }"); 
			
			code_string=code_string+("		else if(document.Form1.hid_client_type.value == 'C'){ "); 
			code_string=code_string+("		if(confirm(m_save_msg)){ "); 
			code_string=code_string+("   for (var i=0; i < document.Form1.elements.length; i++ ) { ");
			code_string=code_string+("    document.Form1.elements[i].disabled=false;");
			code_string=code_string+("   }");
			code_string=code_string+("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+saveJfileName+"?countemp='+lineno+'"+
											"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
											"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+
											"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&&arr_size_in_ex='+arr_size_income;");  
			code_string=code_string+("		document.Form1.submit();	"); 
			code_string=code_string+("		}"); 
			code_string=code_string+(" }"); 
						
			code_string=code_string+(" }"); 
			
			code_string=code_string+(" else { "); 
			//=====================
			code_string=code_string+("		if(validate_data()){ "); 
			
			code_string=code_string+("		if(document.Form1.hid_client_type.value == 'I'){ "); 
			code_string=code_string+("		if(check_dates()){"); 
		    code_string=code_string+("		if(validate_NIC(document.Form1.TXT_NIC_NO)){ "); 	
			//code_string=code_string+("  alert('hid nic stat @@ '+document.Form1.hid_nic_status.value); ");
			/////////////////////////////out.println("   alert('Client Code Validation...............'); ");
			//code_string=code_string+("		setTimeout(alert('Client Code Validation...............'),3000);");
		    code_string=code_string+("		if(double_check('I'))"); 
			//code_string=code_string+("   if(client_contract_count==0  ||  '"+m_save_close+"' =='G') { ");
			code_string=code_string+("		if(confirm(m_save_msg)){ "); 
			code_string=code_string+("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			code_string=code_string+("    document.Form1.elements[i].disabled=false;");
			code_string=code_string+("   }");
			code_string=code_string+("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+saveJfileName+"?countemp='+lineno+'"+
											"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
											"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+                                                   
											"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&arr_size_in_ex='+arr_size_income;");  
											
			code_string=code_string+("		document.Form1.submit();	"); 
			code_string=code_string+("		}"); 
			
			//code_string=code_string+("		}"); //Modify sandun on 11-05-2009
		//	code_string=code_string+("		else { alert('Cant Change the client at this level')}"); 
			
			code_string=code_string+("		}"); 
			code_string=code_string+("		}"); 
			code_string=code_string+("		}"); 
			code_string=code_string+("	 	else {"); 
			code_string=code_string+("		if(validate_NIC_dir()) { ");
			code_string=code_string+("		if(double_check('C'))"); 
			code_string=code_string+("   if(client_contract_count==0  ||  '"+m_save_close+"' =='G') { ");
			code_string=code_string+("		if(confirm(m_save_msg)){ "); 
			code_string=code_string+("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			code_string=code_string+("    document.Form1.elements[i].disabled=false;");
			code_string=code_string+("   }");
			code_string=code_string+("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+saveJfileName+"?countemp='+lineno+'"+
											"&countbank='+lineno_bank+'&countcredit='+lineno_credit+'&countnon='+lineno_nonrelated+'&countfamily='+lineno_family+'"+
											"&countcomp='+lineno_company_dir+'&countba='+lineno_ba+'&countsub='+lineno_subsidiaries+'&countbankc='+lineno_bank+'"+
											"&countcreditc='+lineno_credit_c+'&countcus='+lineno_customer+'&countaudit='+lineno_auditors+'&countnonc='+lineno_nonrelated+'&m_screen="+m_screen+"&save_close="+m_save_close+"&APP_NO="+m_APP_NO+"&no_of_rec="+m_no_of_rec+"&&arr_size_in_ex='+arr_size_income;");  
			code_string=code_string+("		document.Form1.submit();	"); 
			code_string=code_string+("		}"); 
			
			code_string=code_string+("		}"); 
			code_string=code_string+("		else { alert('Cant Cange the client at this level')}"); 
			
			code_string=code_string+("		}"); 
			code_string=code_string+("		}"); 
			
			code_string=code_string+("		}"); 
			
			//==========
			code_string=code_string+(" }");  //end screen name check

			code_string=code_string+("} ");
	
			
	
	        return code_string;
	
  }
}
