m_url=servlet_client_url+':'+client_t3_port+'/'//'https://dev-lakdl.sasianet.com:/myserver/servlet/'
$( document ).ready(function() {
    //console.log( "ready!" );
	
	//load_data();
});


// added by udara 15-08-2019
function show_sanction_letter(val2){ 
mm_url=m_url+"LAKDL_AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&letter=first&applicaton_no="+val2; 
window.open(mm_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');
}
// end by udara 15-08-2019

function saveBatch(){
	batchNo=document.Form1.m_batch_no.value;
	if(batchNo!=''){
	window.open(m_url+"LAKDL_AF_PRO_Cr_book_safe?chksql=get_print_sheet&batchNo="+batchNo)
	}else{
		alert("You haven't submited any records yet")
	}
}

function saveRegister(num,financeNo,invoiceNo){
	
	m_check=document.Form1.elements['crBook_'+num].checked
	m_vehicle=document.Form1.elements['vehicleNo_'+num].value
	if(m_check){
	  if(m_vehicle!=''){
		if(confirm('Are you sure you want to save ? ')){
			batchNo=document.Form1.m_batch_no.value;
			if(batchNo!=""){
			saveStatus(financeNo,invoiceNo,'SAFE',num)
			}else{
				getBatchNo(financeNo,invoiceNo,'SAFE',num)
			}
		}
	  }else{
		alert('Please enter a Vehicle No')
	  }
	}else{
		alert('Please check CR Book received status')
	}
}


function load_data(){
	
	 $("#loader").css("display", "block");
	 document.getElementById('dataTable').innerHTML=""
	 i=0
	 m_html='<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\'center\' width=\"60%\" class=table>';
	 m_branch=document.Form1.BRANCH_CODE.value;
	 
	            m_html=m_html+"<tr class=tr_input><td><b>Finance No</b></td>";
				m_html=m_html+"<td ><b>Vehcile No</b></td>";
				m_html=m_html+"<td align='center'><b>New Cr Book Received</b></td>";
				m_html=m_html+"<td align='center'></td>";
				m_html=m_html+"<td align='center' ></td></tr>";
				
	 $.ajax({
			type: "GET",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=get_cr_book_safe",
			data:{branch:m_branch},
			dataType: "xml",
			success: function(xml) {
				
				$(xml).find('row').each(function(){
				//var Titles = $(this).find('Title').text();
				//var Manufacturers = $(this).find('Manufacturer').text();
				
				//m_html=m_html+"<tr class=tr_input><td>"+$(this).find('financeNo').text()+"</td>";	// added by udara 15-08-2019	
				m_html=m_html+"<tr class=tr_input><td onclick=show_sanction_letter('"+$(this).find('applicationNo').text()+"') style='cursor:hand' ><u>"+$(this).find('financeNo').text()+"</u></td>"; // added by udara 15-08-2019
						
				//m_html=m_html+"<td><input type='text' name='vehicleNo_"+i+"' class='txt_input' value='"+$(this).find('regNo').text()+"'></td>"; // commented by udara 05-08-2019
				
				// added by udara 05-08-2019
				if($(this).find('regStatus').text()=='Registered'){		
					m_html=m_html+"<td><input type='text' name='vehicleNo_"+i+"' class='txt_input' value='"+$(this).find('regNo').text()+"' disabled ></td>";
				}
				else
				{
					m_html=m_html+"<td><input type='text' name='vehicleNo_"+i+"' class='txt_input' value='"+$(this).find('regNo').text()+"'></td>";	
				}
				// end by udara 05-08-2019			
						
						
				m_html=m_html+"<td align='center'><input type='checkbox' name='crBook_"+i+"' ></td>";
				m_html=m_html+"<td align='center'><input type='button' id='register_"+i+"'  style='width:150px' class='enabled but_input' onclick='saveRegister("+i+",\""+$(this).find('financeNo').text()+"\",\""+$(this).find('invoiceNo').text()+"\")' value='Add to Safe'></td>";
				m_html=m_html+"<td><input type='hidden' id='financeNo_"+i+"' value='"+$(this).find('financeNo').text()+"'>";
				m_html=m_html+"<input type='hidden' id='invoiceNo_"+i+"' value='"+$(this).find('invoiceNo').text()+"'></td></tr>";
				
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
	m_vehicle=document.Form1.elements['vehicleNo_'+num].value
	$.ajax({
			type: "POST",
			url: m_url+"LAKDL_AF_PRO_Cr_book_XML?chksql=save_safe_status",
			data: {financeNo:m_finance_no,invoiceNo:m_invoice_no,status:m_status,batchNo:batchNo,vehicleNo:m_vehicle},
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
