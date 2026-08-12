m_url=servlet_client_url+':'+client_t3_port+'/'//'https://dev-lakdl.sasianet.com:/myserver/servlet/'
$( document ).ready(function() {
    //console.log( "ready!" );
	
	//load_data();
});

function saveBatch(){
	batchNo=document.Form1.m_batch_no.value;
	if(batchNo!=''){
	window.open(m_url+"LAKDL_AF_PRO_Cr_book_rmv_verification?chksql=get_print_sheet&batchNo="+batchNo)
	}else{
		alert("You haven't submited any records yet")
	}
}

function saveRegister(num,financeNo,invoiceNo){
	
	//if(confirm('Are you sure you want to save ? ')){ // commented the condition by udara 21-06-2019 to remove the confrmation alert
		batchNo=document.Form1.m_batch_no.value;
	    if(batchNo!=""){
		saveStatus(financeNo,invoiceNo,'RMV',num)
		}else{
			getBatchNo(financeNo,invoiceNo,'RMV',num)
		}
	//}
}


function load_data(){
	
	 $("#loader").css("display", "block");
	 document.getElementById('dataTable').innerHTML=""
	 i=0
	 m_html='<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\'center\' width=\"60%\" class=table>';
	 m_branch=document.Form1.BRANCH_CODE.value;
	 $.ajax({
			type: "GET",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=get_cr_book_rmv",
			data:{branch:m_branch},
			dataType: "xml",
			success: function(xml) {
				
				$(xml).find('row').each(function(){
				//var Titles = $(this).find('Title').text();
				//var Manufacturers = $(this).find('Manufacturer').text();
				m_html=m_html+"<tr class=tr_input><td>"+$(this).find('financeNo').text()+"</td>";
				m_html=m_html+"<td>"+$(this).find('regNo').text()+"</td>";
				m_html=m_html+"<td>"+$(this).find('itemSubDesc').text()+"</td>"; // added by udara 21-06-2019
				m_html=m_html+"<td align='center'><input type='button' id='register_"+i+"'  style='width:150px' class='enabled but_input' onclick='saveRegister("+i+",\""+$(this).find('financeNo').text()+"\",\""+$(this).find('invoiceNo').text()+"\")' value='Rmv Lodged'></td>";
				m_html=m_html+"<td><input type='hidden' id='financeNo_'"+i+" value='"+$(this).find('financeNo').text()+"'>";
				m_html=m_html+"<input type='hidden' id='invoiceNo_'"+i+" value='"+$(this).find('invoiceNo').text()+"'></td></tr>";
				
				i++
				//$("<tr></tr>").html(m_html).appendTo("#dataTable");
				});
				m_html=m_html+'</table>';
				document.getElementById('dataTable').innerHTML=m_html;
				$("#loader").css("display", "none");
			},
			//other code
			error: function(xhr, status, error) {
			//alert("Error Loading data.");
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

	function getBatchNo(m_finance_no,m_invoice_no,m_status,num){
		$.ajax({
			type: "GET",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=get_batch_no",
			dataType: "text",
			success: function(data) {
				document.Form1.m_batch_no.value=data
				saveStatus(m_finance_no,m_invoice_no,m_status,num)
				//alert("Batch No ."+data);
			},
			//other code
			error: function(xhr, status, error) {
			alert("Error Loading data.");
			//console.log(xhr.responseText)
			}
		});
     }
