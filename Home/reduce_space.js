//This JS file particularly used in OFSCL Client Screen & Guarantor Screen


function disable_client_save_buttons(){
	//alert(document.Form1.hid_app_screen.value);
	if(document.Form1.hid_app_screen.value=='APP_R'){
		document.Form1.save.disabled=true;
		document.Form1.save2.disabled=true;
	}
}

function load_screen_status_reduce(m_val,mm_screen,mm_fscreen,mm_app_screen){ 
	
	//alert('reduce_space');
			chk_disa();
			
			if(m_val=='NEW'){ 
			new_window(); 
			document.Form1.BUT_HELP_MAIN.disabled=true;} 
			else if(m_val=='HELP'){ 
			load_help_msg(); 
			} 
			else if(m_val!='EDIT' && m_val!='TEMP' ){ 
			document.Form1.temp_save.disabled=true; //Added by kanchana on 2016-09-09 
			document.Form1.temp.disabled=true;
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			if(document.Form1.hid_client_type.value=='I'){
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=true;
			 }
			document.Form1.edit.disabled=false;
			document.Form1.but_new.disabled=false;
			document.Form1.cancel.disabled=false;
			document.Form1.close.disabled=false;
			document.Form1.ract.disabled=false;
			document.Form1.dact.disabled=false;
			document.Form1.help.disabled=false;
			document.Form1.save.disabled=false; 
			document.Form1.temp.disabled=false;
			
			document.Form1.edit2.disabled=false;
			document.Form1.but_new2.disabled=false;
			document.Form1.cancel2.disabled=false;
			document.Form1.close2.disabled=false;
			document.Form1.ract2.disabled=false;
			document.Form1.dact2.disabled=false;
			//document.Form1.temp_save.disabled=false;
			document.Form1.help2.disabled=false;
			document.Form1.save2.disabled=false; 
			document.Form1.temp2.disabled=false;
			
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			document.Form1.TXT_CLIENT_CODE.disabled=false; 
			document.Form1.TXT_CLIENT_TYPE.disabled=false; 
			} 
			
			else {			
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=true;
			 }
			document.Form1.edit.disabled=false;
			document.Form1.but_new.disabled=false;
			document.Form1.cancel.disabled=false;
			document.Form1.close.disabled=false;
			document.Form1.ract.disabled=false;
			document.Form1.dact.disabled=false;
			document.Form1.temp_save.disabled=true; //Added by Kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by Kanchana on 2016-09-09
			document.Form1.help.disabled=false;
			document.Form1.save.disabled=false; 
			document.Form1.temp.disabled=false;
			
			document.Form1.edit2.disabled=false;
			document.Form1.but_new2.disabled=false;
			document.Form1.cancel2.disabled=false;
			document.Form1.close2.disabled=false;
			document.Form1.ract2.disabled=false;
			document.Form1.dact2.disabled=false;
			//document.Form1.temp_save.disabled=false;
			document.Form1.help2.disabled=false;
			document.Form1.save2.disabled=false; 
			document.Form1.temp2.disabled=false;
			
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			document.Form1.TXT_CLIENT_CODE.disabled=false; 
			document.Form1.TXT_CLIENT_TYPE.disabled=false; 
			}} 
			
			//} 
			
			else if(m_val=='EDIT'){ 
			 var m_app_screen = mm_app_screen; 
			 var m_fscreen = mm_fscreen; 
			
			
			document.Form1.hid_temp_status.value='N';  
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=false;
			 }
			document.Form1.BUT_HELP_TEMP.disabled=true;
			document.Form1.temp_save.disabled=true;
			document.Form1.temp.disabled=true; //Added by Kanchana on 2016-09-09
				
		    document.Form1.temp2.disabled=true;
			document.Form1.temp_save2.disabled=true;
			
			if(document.Form1.hid_client_type.value=='I'){
			//  document.Form1.TXT_TOT_EXPENSE.disabled=true;
			//  document.Form1.TXT_TOT_INCOME.disabled=true;
			//  document.Form1.TXT_NET_INCOME.disabled=true;
			 }
			  if(m_app_screen=='APP_N' || m_app_screen=='APP_R'|| m_fscreen=='client_verification'){  
			//alert(m_app_screen);
			  if(m_val=='NEW'|| m_val=='EDIT'||m_val=='DACT'||m_val=='RACT'){
			     if(document.Form1.hid_entity_type.value!='CORPORATE'){	
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			//   document.Form1.TXT_CLIENT_CODE.disabled=true;
			 }}}}
			
			//}
			
			else{
			
			document.Form1.BUT_HELP_MAIN.disabled=false;} 
			document.Form1.SCREEN_NAME.value=m_val; 
			if(m_val=='NEW'){
			document.Form1.hid_temp_status.value='N';  
			document.Form1.hid_status.value='New'; 
			}else if(m_val=='EDIT'){  
			document.Form1.hid_temp_status.value='N';  
			document.Form1.hid_status.value='Edit';  
			}else if(m_val=='DACT'){ 
			document.Form1.hid_temp_status.value='N'; 
			document.Form1.temp_save.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.BUT_HELP_TEMP.disabled=true; 
			document.Form1.hid_status.value='Deactivate';  
			}else if(m_val=='RACT'){  
			document.Form1.hid_temp_status.value='N'; 
			document.Form1.temp_save.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.BUT_HELP_TEMP.disabled=true; 
			document.Form1.hid_status.value='Reactivate';  
			}else if(m_val=='TEMP'){ 
			 document.Form1.SCREEN_NAME.value='EDIT'; 
			
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=false;
			 }
			
			document.Form1.BUT_HELP_MAIN.disabled=true; 
			document.Form1.dact.disabled=true; 
			document.Form1.ract.disabled=true; 
			document.Form1.dact2.disabled=true; 
			document.Form1.ract2.disabled=true; 
			document.Form1.hid_temp_status.value='Y';  
			document.Form1.hid_status.value='Temporary'; 
			
			/*if(document.Form1.hid_client_type.value=='I'){
			  document.Form1.TXT_TOT_EXPENSE.disabled=true;
			  document.Form1.TXT_TOT_INCOME.disabled=true;
			  document.Form1.TXT_NET_INCOME.disabled=true;
			 }
			*/
			
			}else{
			document.Form1.hid_status.value='';  
			} 
			
			// var m_fscreen = '"+fscreen+"'; 
			 if(m_fscreen=='client_verification'){  			
			//alert(document.Form1.hid_client_count.value);
			  if(document.Form1.hid_client_count.value > 0){//[Added milinda]			
			   document.Form1.save.disabled=true;//[Added milinda]
			   document.Form1.save2.disabled=true;//[Added milinda]
			   document.Form1.edit.disabled=true;   document.Form1.edit2.disabled=true;//[Added milinda]//[Added milinda]
			   document.Form1.but_new.disabled=true;   document.Form1.but_new2.disabled=true;//[Added milinda]
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;//[Added milinda]
			   document.Form1.dact.disabled=true;   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;   document.Form1.ract2.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			  }else{//[Added milinda]
			   document.Form1.save.disabled=false;//[Added milinda]
			   document.Form1.save2.disabled=false;//[Added milinda]
			   document.Form1.edit.disabled=false;   document.Form1.edit2.disabled=false;//[Added milinda]
			   document.Form1.but_new.disabled=true;   document.Form1.but_new2.disabled=true;//[Added milinda]
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;//[Added milinda]
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			   document.Form1.dact.disabled=true;   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;   document.Form1.ract2.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			//   document.Form1.TXT_CLIENT_CODE.disabled=true;
			}}//[Added milinda]
			// }
			 var m_screen=mm_screen;
			//alert(m_screen);
			//alert(document.Form1.hid_client_count.value);
			// added by udara 16-09-2014
			// var m_app_screen = '"+m_app_screen+"'; 
			
			 if(m_app_screen=='Y'){  
			   document.Form1.ract.disabled=true;
			   document.Form1.dact.disabled=true;
			   document.Form1.ract2.disabled=true;
			   document.Form1.dact2.disabled=true;
			
			 }
			 else if(m_app_screen=='APP_N' || m_screen=='N'){  //[Application Entry level and creation of client level ]
			//alert('a');
			if(document.Form1.hid_client_count.value>0){//[Added milinda]
			   document.Form1.save.disabled=true;//[Added milinda]
			   document.Form1.save2.disabled=true;//[Added milinda]
			   document.Form1.edit.disabled=true;
			   document.Form1.edit2.disabled=true;//[Added milinda]
			   document.Form1.temp2.disabled=true;//[Added milinda]
			   document.Form1.temp_save2.disabled=true;//[Added milinda]
			   document.Form1.but_new.disabled=true;
			   document.Form1.but_new2.disabled=true;//[Added milinda]
			   document.Form1.cancel.disabled=true;
			   document.Form1.cancel2.disabled=true;//[Added milinda]
			   document.Form1.dact.disabled=true;
			   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;
			   document.Form1.ract2.disabled=true;
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			 }else{
			//alert('b');
			   document.Form1.save.disabled=false;//[Added milinda]
			   document.Form1.save2.disabled=false;//[Added milinda]
			   document.Form1.edit.disabled=false;   document.Form1.edit2.disabled=false;//[Added milinda]
			   document.Form1.but_new.disabled=true;   document.Form1.but_new2.disabled=true;//[Added milinda]
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;//[Added milinda]
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			   document.Form1.dact.disabled=true;   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;   document.Form1.ract2.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			//   document.Form1.TXT_CLIENT_CODE.disabled=true;
			}}//[Added milinda]
			 else if(m_app_screen=='APP_R' ){  //[Receipt screen level]	
			if(document.Form1.hid_client_count.value>0){//[Added milinda]
			   document.Form1.dact.disabled=true;   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;   document.Form1.ract2.disabled=true;
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;
			   document.Form1.edit.disabled=true;   document.Form1.edit2.disabled=true;
			   document.Form1.save.disabled=true;
			   document.Form1.save2.disabled=true;//[Added milinda]
			   document.Form1.temp2.disabled=true;
			   document.Form1.temp_save2.disabled=true;
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			 }else{
			   document.Form1.dact.disabled=true;   document.Form1.dact2.disabled=true;
			   document.Form1.ract.disabled=true;   document.Form1.ract2.disabled=true;
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;
			   document.Form1.edit.disabled=false;   document.Form1.edit2.disabled=true;
			   document.Form1.save.disabled=false;
			   document.Form1.save2.disabled=false;
			   document.Form1.TXT_NIC_NO.disabled=true;
			   document.Form1.TXT_NIC_NO_OLD.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			//   document.Form1.TXT_CLIENT_CODE.disabled=true;
			 }}}




function load_screen_status_reduce_guar(m_val,mm_screen,mm_app_screen){ 
			
			if(m_val=='NEW'){ 
			new_window(); 
			document.Form1.BUT_HELP_MAIN.disabled=true;} 
			else if(m_val=='HELP'){ 
			load_help_msg(); 
			} 
			else if(m_val!='EDIT' && m_val!='TEMP' ){ 
			document.Form1.temp_save.disabled=true;//Added by kanchana on 2016-09-09 
			document.Form1.temp.disabled=true;
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			if(document.Form1.hid_client_type.value=='I'){
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=true;
			 }
			document.Form1.edit.disabled=false;
			document.Form1.but_new.disabled=false;
			document.Form1.cancel.disabled=false;
			document.Form1.close.disabled=false;
			document.Form1.ract.disabled=false;
			document.Form1.dact.disabled=false;
			//document.Form1.temp_save.disabled=false;
			document.Form1.help.disabled=false;
			document.Form1.save.disabled=false; 
			document.Form1.temp.disabled=false;
			
			document.Form1.edit2.disabled=false;
			document.Form1.but_new2.disabled=false;
			document.Form1.cancel2.disabled=false;
			document.Form1.close2.disabled=false;
			document.Form1.ract2.disabled=false;
			document.Form1.dact2.disabled=false;
			//document.Form1.temp_save.disabled=false;
			document.Form1.help2.disabled=false;
			document.Form1.save2.disabled=false; 
			document.Form1.temp2.disabled=false;
			
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			document.Form1.TXT_CLIENT_CODE.disabled=false; 
			document.Form1.TXT_CLIENT_TYPE.disabled=false; 
			} 
			
			else {
			
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=true;
			 }
			document.Form1.edit.disabled=false;
			document.Form1.but_new.disabled=false;
			document.Form1.cancel.disabled=false;
			document.Form1.close.disabled=false;
			document.Form1.ract.disabled=false;
			document.Form1.dact.disabled=false;
			document.Form1.temp_save.disabled=true; //Added by Kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by Kanchana on 2016-09-09
			document.Form1.help.disabled=false;
			document.Form1.save.disabled=false; 
			document.Form1.temp.disabled=false;
			
			document.Form1.edit2.disabled=false;
			document.Form1.but_new2.disabled=false;
			document.Form1.cancel2.disabled=false;
			document.Form1.close2.disabled=false;
			document.Form1.ract2.disabled=false;
			document.Form1.dact2.disabled=false;
			//document.Form1.temp_save.disabled=false;
			document.Form1.help2.disabled=false;
			document.Form1.save2.disabled=false; 
			document.Form1.temp2.disabled=false;
			
			document.Form1.BUT_HELP_MAIN.disabled=false; 
			document.Form1.TXT_CLIENT_CODE.disabled=false; 
			document.Form1.TXT_CLIENT_TYPE.disabled=false; 
			}} 
			
			//} 
			
			else if(m_val=='EDIT'){ 
			 var m_app_screen = mm_app_screen; 
			// var m_fscreen = '"+fscreen+"'; 
			document.Form1.hid_temp_status.value='N';  
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=false;
			 }
			document.Form1.BUT_HELP_TEMP.disabled=true;
			document.Form1.temp_save.disabled=true;
			document.Form1.temp_save2.disabled=true;//Added By Nuwan De Silva
			document.Form1.temp.disabled=true; //Added by Kanchana on 2016-09-09
				
			document.Form1.temp2.disabled=true;
			
			if(document.Form1.hid_client_type.value=='I'){
			//  document.Form1.TXT_TOT_EXPENSE.disabled=true; //added by nwuan de silva on 01-10-07
			//  document.Form1.TXT_TOT_INCOME.disabled=true;
			//  document.Form1.TXT_NET_INCOME.disabled=true;
			 }}
			//	}
			
			else{
			document.Form1.BUT_HELP_MAIN.disabled=false;} 
			document.Form1.SCREEN_NAME.value=m_val; 
			if(m_val=='NEW'){
			document.Form1.hid_temp_status.value='N';  
			document.Form1.hid_status.value='New'; 
			}else if(m_val=='EDIT'){  
			document.Form1.hid_temp_status.value='N';  
			document.Form1.hid_status.value='Edit';  
			}else if(m_val=='DACT'){ 
			document.Form1.hid_temp_status.value='N';  
			document.Form1.BUT_HELP_TEMP.disabled=true; 
			document.Form1.temp_save.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by kanchana on 2016-09-09
			//document.Form1.temp2.disabled=true; 
			//document.Form1.temp_save2.disabled=true; 
			document.Form1.hid_status.value='Deactivate';  
			}else if(m_val=='RACT'){  
			document.Form1.hid_temp_status.value='N';  
			document.Form1.BUT_HELP_TEMP.disabled=true; 
			document.Form1.temp_save.disabled=true; //Added by kanchana on 2016-09-09
			document.Form1.temp.disabled=true; //Added by kanchana on 2016-09-09
			//document.Form1.temp2.disabled=true; 
			//document.Form1.temp_save2.disabled=true; 
			document.Form1.hid_status.value='Reactivate';  
			}else if(m_val=='TEMP'){ 
			 document.Form1.SCREEN_NAME.value='EDIT'; 
			 for (var i=0; i < document.Form1.elements.length; i++ ) {
			 document.Form1.elements[i].disabled=false;
			 }
			
			document.Form1.BUT_HELP_MAIN.disabled=true; 
			document.Form1.dact.disabled=true; 
			document.Form1.ract.disabled=true; 
			document.Form1.dact2.disabled=true; 
			document.Form1.ract2.disabled=true;
			document.Form1.temp2.disabled=true; 
			document.Form1.temp_save2.disabled=true; 
			document.Form1.hid_temp_status.value='Y';  
			document.Form1.hid_status.value='Temporary'; 
			if(document.Form1.hid_client_type.value=='I'){
			  document.Form1.TXT_TOT_EXPENSE.disabled=true;
			  document.Form1.TXT_TOT_INCOME.disabled=true;
			
			 }
			}else{
			document.Form1.hid_status.value='';  
			} 
			
			// added by udara 16-09-2014
			 var m_screen = mm_screen; 
			 if(m_app_screen=='Y'){  
			//alert('a');
			   document.Form1.ract.disabled=true;
			   document.Form1.dact.disabled=true;
			   document.Form1.ract2.disabled=true;
			   document.Form1.dact2.disabled=true;
			   document.Form1.temp2.disabled=true;
			   document.Form1.temp_save2.disabled=true;
			// } 
			// end by udara 16-09-2014
			 }else if(m_app_screen=='APP_N' || m_screen=='G'){  //[Application Entry level and creation of client level ]
			
			//alert(document.Form1.hid_client_count.value);
			if(document.Form1.hid_client_count.value>0){//[Added milinda]
			   document.Form1.save.disabled=true;//[Added milinda]
			   document.Form1.save2.disabled=true;//[Added milinda]
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			if(document.Form1.hid_entity_type.value!='CORPORATE'){	
				document.Form1.TXT_NIC_NO.disabled =true;
			}
			//   ocument.Form1.TXT_NIC_NO.disabled =true;
			
			 }else{
			//alert('b');
			   document.Form1.save.disabled=false;//[Added milinda]
			   document.Form1.save2.disabled=false;//[Added milinda]
			   document.Form1.cancel.disabled=true;   document.Form1.cancel2.disabled=true;
			   document.Form1.TXT_CLIENT_TYPE.disabled=true;
			if(document.Form1.hid_entity_type.value!='CORPORATE'){	
				document.Form1.TXT_NIC_NO.disabled =true;
			}
			
			//   document.Form1.TXT_CLIENT_CODE.disabled=true;
	}}}//[Added milinda]
			
			
