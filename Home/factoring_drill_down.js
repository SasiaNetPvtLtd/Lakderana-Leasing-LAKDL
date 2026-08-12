var servlet_client_url="https://dev-lakdl.sasianet.com";
var html_client_url="https://dev-lakdl.sasianet.com/";
var client_t3_port="/myserver/servlet";
var client_name="LAKDL_";

//INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
function format_num(obj,i){

  if(isNaN(unformat_noobject(obj.value))){
   alert('Please enter a number ');
   obj.value='';
  }else{
		
   obj.value=unformat_noobject(obj.value);
		
   obj.value=format_noobject(obj.value,i);
		
  }
}

function show_facility(m_facility_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_FACILITY_DETAIL_DRILL&facility_no="+m_facility_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

function show_quotation(m_quotation_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_QUOTATION_DETAIL_DRILL&quotation_no="+m_quotation_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

//display client information
function show_client(m_client_data){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CLIENT_INFORMATION&client_code="+m_client_data;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

function show_invoice_batch_details(m_invoice_batch){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_INVOICE_BATCH_DRILL&batch_no="+m_invoice_batch;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}


//-------------------------Added by Ashini on 12-02-2008---				
function show_invoice_details(m_debtor_code,m_invoice_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs_2?chksql=SHOW_INVOICE_DETAIL_DRILL&invoice_no="+m_invoice_no+"&debtor_no="+m_debtor_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

function show_settle_details(m_rec_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs_2?chksql=SHOW_SETTLE_DETAIL_DRILL&rec_no="+m_rec_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
function show_unallocated_detail(m_deb_cli_code,m_fac_no,m_from,m_to){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MISF_Unallocated_fund_reports?chksql=SHOW_UNALLOCATED_FUND_DETAIL_DRILL&facility_no="+m_fac_no+"&debtor_client_no="+m_deb_cli_code+"&m_from="+m_from+"&m_to="+m_to;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}


function show_unallocated_invoice_details(m_fac_no,m_deb_cli_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MISF_Unallocated_fund_reports?chksql=SHOW_UNALLOCATED_INVOICE_DETAIL_DRILL&facility_no="+m_fac_no+"&debtor_client_no="+m_deb_cli_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}


//-----end modifications done by ashini----------------------
				
function show_invoice_details(m_debtor_code,m_invoice_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_INVOICE_DETAIL_DRILL&invoice_no="+m_invoice_no+"&debtor_no="+m_debtor_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

function show_adjustment_details(m_adjust_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_ADJUST_DETAIL_DRILL&adjust_no="+m_adjust_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 29-12-2006
function show_pod_cheque_details(m_pod_ref_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_POD_CHEQUE_DETAIL_DRILL&pod_ref_no="+m_pod_ref_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 07-01-2007
function show_receipt_details(m_receipt_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_RECEIPT_DETAIL_DRILL&receipt_no="+m_receipt_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 22-01-2007
function show_deposit_details(m_deposit_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_DEPOSIT_DETAIL_DRILL&deposit_no="+m_deposit_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 22-01-2007
function show_return_details(m_return_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_RETURN_DETAIL_DRILL&return_no="+m_return_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 22-01-2007
function show_payment_details(m_payment_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_PAYMENT_DETAIL_DRILL&payment_code="+m_payment_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
//Added by Mahela on 22-01-2007
function show_invoice_allocation_details(m_allocation_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_INVOICE_ALLO_DETAIL_DRILL&allocation_no="+m_allocation_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

function show_invoice_details_ref_no(m_invoice_refno){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_INVOICE_DETAIL_REFNO_DRILL&invoice_refno="+m_invoice_refno;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}
function show_charges_details(m_refno){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_CLIENT_CHARGES_DETAIL_DRILL&charges_ref_no="+m_refno;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
}

//Added by Dineth on 2008-10-02
function show_inv_det_drill(m_client,m_facility,m_debtor,m_allo_date,m_option){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=LOAD_CLIENT_AVAILABILITY_DRILL&CLIENT_CODE="+m_client+"&FACILITY_NO="+m_facility+"&OPTION_NO="+m_option+"&DEBTOR_CODE="+m_debtor+"&ALLO_DATE="+m_allo_date;
	window.open(m_url,"popupwin1","width=700,height=450,scrollbars=2");
	}		
//End by Dineth on 2008-10-02
//Added by Sanjeewa on 2010-07-15
function show_inv_det_rep_by_client(m_client,m_month){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_MONYH_INVOICE_DETAIL_FOR_CLIENT&CLIENT_CODE="+m_client+"&MONTH="+m_month;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
	}	

function show_inv_det_rep_by_month(m_month){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_RE_PRO_drill_downs?chksql=SHOW_MONYH_INVOICE_DETAIL_FOR_ALL&MONTH="+m_month;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700");
	}	
//End by Sanjeewa on 2010-07-15
