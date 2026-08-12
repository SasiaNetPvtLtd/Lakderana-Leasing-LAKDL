//m_url=servlet_client_url+':'+client_t3_port+'/'//'https://dev-lakdl.sasianet.com:/myserver/servlet/'

mm_url=servlet_client_url+':'+client_t3_port+'/'

function saveAllo(num,mm_finance_no){	
	
	if(confirm('Are you sure you want to save ? ')){
		
		document.Form1.elements['btn_al_'+num].disabled=true;
		
		m_finance_no=mm_finance_no;
		m_screen_name=document.Form1.elements['TXT_SCREEN_NAME'].value; 	
		m_option_name=document.Form1.elements['OPTION_DESC'].value;
		
		$.ajax({
			type: "POST",
			url: mm_url+"LAKDL_AF_PRO_Termination_XML?chksql=save_termination_pro_allo",
			data: {financeNo:m_finance_no,screenName:m_screen_name,optionName:m_option_name},
			dataType: "text",
			success: function(text) {
		        //alert ('Status reversed succefully ')
				alert (text)
				get_Receipt(null,null)
				//document.Form1.elements['btn_al_'+num].disabled=false;
			},
			error: function(xhr, status, error) {
			alert("Error Saving data.");
			document.Form1.elements['btn_al_'+num].disabled=false;
			//console.log(xhr.responseText)
			}
		});
		
		//document.Form1.elements['btn_al_'+num].disabled=false

		
	}
	
	
}


