m_url=servlet_client_url+':'+client_t3_port+'/'//'https://dev-lakdl.sasianet.com:/myserver/servlet/'

// added by udara 25-07-2019
function save_all(){
	//alert('Save All Check from js');
	//alert(document.Form1.hid_seq_no.value);
	
	if(confirm('Are you sure you want save data')){ 
		document.Form1.action=m_url+'LAKDL_AF_PRO_Cr_book_pledge_reversal_save_all'; 
		document.Form1.submit();	
	} 
	
}
// end by udara 25-07-2019

function saveReversal(num){
	
	//alert('test 1');
	
	if(confirm('Are you sure you want to save ? ')){
		document.getElementById('register_'+num).disabled=true
	    m_finance_no=document.Form1.elements['financeNo_'+num].value 
		m_invoice_no=document.Form1.elements['invoiceNo_'+num].value 
		currentStatus=document.Form1.elements['status_'+num].value 
		prevStatus=document.Form1.elements['revStatus_'+num].value 
		withdrawReason = document.Form1.elements['withdraw_'+num].value  // added by udara 19-07-2019
		
		//alert('test 2');
		
	$.ajax({
			type: "POST",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=save_pledge_reversal",
			//data: {financeNo:m_finance_no,invoiceNo:m_invoice_no,currentStatus:currentStatus,prevStatus:prevStatus}, // commented by udara 19-07-2019
			data: {financeNo:m_finance_no,invoiceNo:m_invoice_no,currentStatus:currentStatus,prevStatus:prevStatus,withdrawReason:withdrawReason}, // added by udara 19-07-2019
			dataType: "text",
			success: function(text) {
		        alert ('Pledge Status reversed succefully ')
			},
			error: function(xhr, status, error) {
			alert("Error Saving data.");
			console.log(xhr.responseText)
			}
		});
	}
}



function load_data(){

	
	 $("#loader").css("display", "block");
	 document.getElementById('dataTable').innerHTML=""
	 i=0
	 m_html='<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\'center\' width=\"60%\" class=table>';
	
	m_html=m_html+"<tr class=tr_input>";
	m_html=m_html+"<td><b> No. </b></td>";
	m_html=m_html+"<td><b> Finance No </b></td>";
	m_html=m_html+"<td><b> Vehicle No </b></td>";
	//m_html=m_html+"<td><b> Current Status </b></td>";
	//m_html=m_html+"<td><b> Reverse/Previous Status </b></td>";
	
	m_html=m_html+"<td><b> Withdraw Reason </b></td>"; // added by udara 19-07-2019
	
	m_html=m_html+"<td><b>  </b></td></tr>";
	
	 m_finance=document.Form1.TXT_FINANCE_NO.value;
	 m_bank=document.Form1.TXT_BANK_CODE.value;
	 m_branch=document.Form1.TXT_BRANCH_CODE.value;
	 m_loan=document.Form1.TXT_LOAN_NO.value;
	
	 seq_no = 0;
	 
	 $.ajax({
			type: "GET",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=get_cr_book_pledge",
			data:{finance:m_finance,bank:m_bank,branch:m_branch,loan:m_loan},
			dataType: "xml",
			success: function(xml) {
				$(xml).find('row').each(function(){
						
				seq_no = seq_no + 1;
				
				m_html=m_html+"<tr class=tr_input>";
				m_html=m_html+"<td>"+seq_no+"</td>";
				m_html=m_html+"<td>"+$(this).find('financeNo').text()+"</td>";
				m_html=m_html+"<td>"+$(this).find('vehicleNo').text()+"</td>";
			//	m_html=m_html+"<td>"+$(this).find('crStatusDesc').text()+"</td>";
			//	m_html=m_html+"<td>"+$(this).find('crStatusRevDesc').text()+"</td>";
						
				// added by udara 19-07-2019
				m_html=m_html+ "<td>";
				m_html=m_html+ "<select name='withdraw_"+i+"' id='withdraw_"+i+"' >";
				m_html=m_html+  "<option value='Security Release' > Security Release </option>";
				m_html=m_html+  "<option value='Termination' > Termination </option>";
				m_html=m_html+  "<option value='RMV' > RMV </option>";
				m_html=m_html+  "<option value='Legal' > Legal </option>";
				m_html=m_html+  "<option value='Transfer' > Transfer </option>";
				m_html=m_html+  "<option value='Other' > Other </option>";
				m_html=m_html+ "</select>";
				m_html=m_html+ "</td>";
				// end by udara 19-07-2019
						
						
				m_html=m_html+"<td align='center'><input type='button' id='register_"+i+"'  style='width:150px' class='enabled but_input' onclick='saveReversal("+i+")' value='Withdrawal'></td>";  // Reverse // mod by udara to change caption 11-07-2019
				m_html=m_html+"<td><input type='hidden' name='financeNo_"+i+"' id='financeNo_"+i+"' value='"+$(this).find('financeNo').text()+"'>";
				m_html=m_html+"<input type='hidden' name='status_"+i+"'    id='status_"+i+"' value='"+$(this).find('crStatus').text()+"'>";
				m_html=m_html+"<input type='hidden' name='revStatus_"+i+"' id='revStatus_"+i+"' value='"+$(this).find('crStatusRev').text()+"'>";
				m_html=m_html+"<input type='hidden' name='invoiceNo_"+i+"' id='invoiceNo_"+i+"' value='"+$(this).find('invoiceNo').text()+"'></td></tr>";
				
				i++
				//$("<tr></tr>").html(m_html).appendTo("#dataTable");
				});
				m_html=m_html+"<input type='hidden' name='hid_seq_no' value='"+seq_no+"' >"; // added by udara 25-07-2019
				m_html=m_html+'</table>';
				document.getElementById('dataTable').innerHTML=m_html;
				$("#loader").css("display", "none");
			},
			//other code
			error: function(xhr, status, error) {
			//alert("Error Loading data.");
			//document.getElementById('dataTable').innerHTML=error.message;
			document.getElementById('dataTable').innerHTML="<div align='center'><font color='red'> No Data to display</font></div>";
			$("#loader").css("display", "none");
			//console.log(xhr.responseText)
			}
		});
}




function saveStatus(m_finance_no,m_invoice_no,m_status,num){
	batchNo=document.Form1.m_batch_no.value;
	document.getElementById('register_'+num).disabled=true
	
	$.ajax({
			type: "POST",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=save_rmv_status",
			data: {financeNo:m_finance_no,invoiceNo:m_invoice_no,status:m_status,batchNo:batchNo},
			dataType: "text",
			success: function(text) {
				
		        alert ('Data saved succefully ')
			},
			error: function(xhr, status, error) {
			alert("Error Saving data.");
			//console.log(xhr.responseText)
			}
		});
	}


