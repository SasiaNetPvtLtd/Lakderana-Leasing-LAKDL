var servlet_client_url="https://dev-lakdl.sasianet.com";
var html_client_url="https://dev-lakdl.sasianet.com/";
var client_t3_port="/myserver/servlet";
var client_name="LAKDL_";

//MAHELA FOR OFSCL LEASING    DATE:23-03-2007


function format_num(obj,i){
	
	if(isNaN(unformat_noobject(obj.value))){
		alert('Please enter a number ');
		obj.value='';
	}else{
		obj.value=unformat_noobject(obj.value);
		obj.value=format_noobject(obj.value,i);
	}
}

function show_client(m_client_data){
	//alert("m_client_data" +m_client_data);
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CLIENT_INFORMATION&client_code="+m_client_data;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}


function show_broker_det(m_client_data){
	//alert("m_client_data" +m_client_data);
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_BROKER_INFO&client_code="+m_client_data;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=400,width=600,resizable=1");
}


function show_transaction_history_by_contract(m_finance_no,m_client_code){
	//alert("m_client_data" +m_finance_no);
	//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code="+m_client_code+"&finance_no="+m_finance_no;
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code="+m_client_code+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}

function Ledger_New(m_finance_no,m_client_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Transaction_History_Contract?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code="+m_client_code+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}

function show_rental_schedule(m_app_no){
	//alert("m_client_data" +m_finance_no);
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CR_PRO_Rental_Detail_Report?chksql=DRILL_DOWN_CONTRACT&appl_no="+m_app_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}

// added by udara 10-10-2018
function show_pledge_details(m_app_no){
	//alert("m_client_data" +m_finance_no);
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"Pledge_details?chksql=SHOW_RUNNING_CON_DETAILS&application_no="+m_app_no;
	//window.open(m_url,"popupwin1_pledge","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
	var link_name='popupwin1_pledge'+Math.floor(Math.random() * 1000);
	//alert(link_name);
	window.open(m_url,link_name,"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}
// end by udara 10-10-2018

// added by udara 23-01-2019
function reminder_letter_contracts(m_fin_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"Reminder_letter_contracts?chksql=SHOW_RUNNING_CON_DETAILS&finance_no="+m_fin_no;
	var link_name='popupwin1_letter_sent'+Math.floor(Math.random() * 1000);
	window.open(m_url,link_name,"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}
// end by udara 23-01-2019

// added by udara 06-01-2021
function moratorium_debit_note_drill(m_fin_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"Reminder_letter_contracts?chksql=SHOW_FUTURE_MORATORIUM_INVOICES&finance_no="+m_fin_no;
	var link_name='popupwin1_mora_inv'+Math.floor(Math.random() * 1000);
	window.open(m_url,link_name,"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}
// end by udara 06-01-2021

function show_pricing(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_PRICING_INFORMATION&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}

function show_annexure(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_ANNEXURE_DRILL&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=350,width=450,resizable=1");
}

function show_valuation(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_VALUATION_INFORMATION&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}

function show_asset(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_ASSET_INFORMATION&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}

// added by udara 03-04-2014
function show_run_con_det(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_running_con_det?chksql=SHOW_RUNNING_CON_DETAILS&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=500,width=600,resizable=1");
}
// end by udara 03-04-2014

// added by udara 21-08-2015
function show_crdit_score_det(m_application_no,m_finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CR_display_credit_score_enter_2?screen_type=EDIT&application_no="+m_application_no+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=500,width=600,resizable=1");
}
// end by udara 21-08-2015

function show_proforma(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_PROFORMA_INFORMATION&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}
//addded by nuwan de silva 17-07-07-----------
function show_offer_drill(m_offer_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_OFFER_DRILL&offer_no="+m_offer_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=300,width=450,resizable=1");
}
function show_payment(m_client_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_PAYMENT_INFORMATION&client_code="+m_client_code;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}
function show_guarantor(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_GUARANTOR_INFORMATION&application_no="+m_application_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=600,top=350,height=150,width=450,resizable=1");
}
function show_gross_received_drill(m_client_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_GROSS_DERAIL_DRILL&client_code="+m_client_code;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=100,top=50,height=350,width=550,resizable=1");
}
function show_capital_balace_outstanding_drill(m_finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_CAPITAL_BAL_OUT_STANDING_DRILL&finance_no="+m_finance_no;
	window.open(m_url,"popupwin2","status=0,menubar=0,scrollbars=1,left=100,top=50,height=350,width=550,resizable=1");
}
function show_transaction_type_drill(m_trans_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_TRANSACTION_TYPE_DRILL&trans_code="+m_trans_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_transaction_sub_type_drill(m_trans_sub_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_TRANSACTION_SUB_TYPE_DRILL&trans_sub_code="+m_trans_sub_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_item_sub_category_drill(m_item_sub_cat){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_ITEM_SUB_CATEGORY_DRILL&item_sub_cat="+m_item_sub_cat;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=300,width=650,resizable=1");
}
function show_engine_capacity_drill(m_capacity_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_ENGINE_CAPACITY_DRILL&capacity_code="+m_capacity_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_make_details_drill(m_make_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_MAKE_DRILL&make_code="+m_make_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_model_details_drill(m_model_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_MODEL_DRILL&model_code="+m_model_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_sub_model_details_drill(m_sub_model_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_SUB_MODEL_DRILL&sub_model_code="+m_sub_model_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=500,resizable=1");
}
function show_asset_condition_drill(m_asset_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_ASSET_CONDITION_DRILL&asset_code="+m_asset_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_asset_usage_drill(m_usage_type){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_ASSET_USAGE_DRILL&usage_type="+m_usage_type;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_mileage_drill(m_sub_model_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_MILEAGE_DRILL&sub_model_code="+m_sub_model_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=300,width=550,resizable=1");
}
function show_valuer_drill(m_valuer_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_VALUER_DRILL&valuer_code="+m_valuer_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=550,resizable=1");
}
function show_garage_drill(m_garage_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_GARAGE_DRILL&garage_code="+m_garage_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=100,width=500,resizable=1");
}
function show_income_expense_drill(m_ie_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_INCOME_EXPENSE_DRILL&ie_code="+m_ie_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=550,resizable=1");
}
function show_nationalitye_drill(m_nation_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_NATIONALITY_DRILL&nation_code="+m_nation_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=100,width=500,resizable=1");
}
function show_legal_entity_drill(m_entity_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_LEGAL_ENTITY_DRILL&entity_code="+m_entity_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=550,resizable=1");
}
function show_licensee_settle_drill(m_acc_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_LICENSEE_SETTLE_DRILL&acc_no="+m_acc_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=550,resizable=1");
}
function show_team_member_drill(m_team_id){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_TEAM_MEMBERS_DRILL&team_id="+m_team_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=300,width=550,resizable=1");
}
function show_customer_category_drill(m_cat_type_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CUSTOMER_CAT_DRILL&cat_type_code="+m_cat_type_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_lead_source_cat_drill(m_src_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_LEAD_SOURCE_CAT_DRILL&src_code="+m_src_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_lead_source_drill(m_src_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_LEAD_SOURCE_DRILL&src_code="+m_src_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_broker_drill(m_broker_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_BROKER_DRILL&broker_code="+m_broker_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=400,width=550,resizable=1");
}
function show_repayment_interval_drill(m_duration){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_REPAYMENT_INTERVAL_DRILL&duration="+m_duration;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_sub_charge_drill(m_sub_type_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_SUB_CHARGE_DRILL&sub_type_code="+m_sub_type_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_vendor_drill(m_vendor_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_VENDOR_DRILL&vendor_code="+m_vendor_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=350,width=550,resizable=1");
}
function show_repayment_method_drill(m_method_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_REPAYMENT_METHOD_DRILL&method_code="+m_method_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_rmv_agents_drill(m_agent_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RMV_AGENTS_DRILL&agent_code="+m_agent_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=300,width=550,resizable=1");
}
function show_yard_drill(m_yard_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_YARD_DRILL&yard_code="+m_yard_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=200,width=500,resizable=1");
}
function show_early_termination_charge_drill(m_termi_type){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_EARLY_TERMINATION_CHARGE_DRILL&termi_type="+m_termi_type;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_discount_rate_drill(m_rate){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_DISCOUNT_RATE_DRILL&rate="+m_rate;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=100,width=500,resizable=1");
}
function show_lawyer_drill(m_lawyer_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_LAWYER_DRILL&lawyer_code="+m_lawyer_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=350,width=550,resizable=1");
}
/*function show_missing_vehicle_drill(m_vehicle_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_MISSING_VEHICLE_DRILL&vehicle_no="+m_vehicle_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
*/

function show_missing_vehicle_drill(m_vehicle_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_MISSING_VEHICLE_DRILL&vehicle_no="+m_vehicle_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}



//----Modified on 2007-09-27--------------------------------------------------------------------------------------------------------------------
//function show_followup_drill(m_followup_no){
//	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_FOLLOWUP_DRILL&followup_no="+m_followup_no;
//	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=400,width=550,resizable=1");
//}
function show_followup_drill(m_followup_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_FOLLOWUP_DRILL&followup_no="+m_followup_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=400,width=550,resizable=1");
}


function show_followup_category_drill(m_cat_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_FOLLOWUP_CATEGORY_DRILL&cat_code="+m_cat_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=150,width=500,resizable=1");
}
function show_application_detail_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_APPLICATION_DETAIL_DRILL&application_no="+m_application_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1 ");
}
function show_finance_detail_drill(m_finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_FINANCE_DETAIL_DRILL&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1");
}
function show_pod_cheque_drill(m_pod_ref_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_POD_CHEQUE_DRILL&pod_ref_no="+m_pod_ref_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1");
}
function show_settle_receipt_drill(m_receipt_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_SETTLE_RECEIPT_DRILL&receipt_no="+m_receipt_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=800,resizable=1");
}

function show_receipt_remarks(m_receipt_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=show_receipt_remarks&receipt_no="+m_receipt_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=600,resizable=1");
}
function show_pricing_drill(m_pricing_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_PRICING_DRILL&pricing_no="+m_pricing_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1");
}
function show_invoice_drill(m_invoice_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&invoice_no="+m_invoice_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1");
}
function show_proforma_invoice_drill(m_invoice_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_PROFORMA_INVOICE_DRILL&invoice_no="+m_invoice_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1");
}
function show_asset_detail_drill(m_asset_id){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_ASSET_DETAIL_DRILL&asset_id="+m_asset_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}
function show_quotation_drill(m_quotation_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_QUOTATION_DRILL&quotation_no="+m_quotation_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}
function show_inquiry_drill(m_inquiry_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INQUIRY_DRILL&inquiry_no="+m_inquiry_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}
function show_valuation_drill(m_valuation_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_VALUATION_DRILL&valuation_no="+m_valuation_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}
function show_purchase_order_drill(m_purchase_order_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_PURCHASE_ORDER_DRILL&purchase_order_no="+m_purchase_order_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}
function show_deposit_drill(m_deposit_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_DEPOSIT_DETAIL_DRILL&deposit_no="+m_deposit_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=550,resizable=1");
}
function show_document_drill(m_doc_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_DOCUMENT_DRILL&doc_code="+m_doc_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=350,width=500,resizable=1");
}
function show_return_detail_drill(m_return_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_RETURN_DETAIL_DRILL&return_no="+m_return_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=350,width=500,resizable=1");
}
function show_payment_drill(m_payment_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_PAYMENT_DRILL&payment_no="+m_payment_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_sus_payment_drill(m_sus_ref_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_SUS_REF_DRILL&sus_ref_no="+m_sus_ref_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_repossession_drill(m_repossession_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_REPOSSESION_DRILL&repossession_no="+m_repossession_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_inventory_drill(m_inventory_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_VEHICLE_INVENTORY_DRILL&inventory_no="+m_inventory_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_bank_drill(m_bank_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_BANK_DRILL&bank_no="+m_bank_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_application_charge_drill(m_application_no,m_chargeble_amount){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_CHARGE_AMOUNT_DRILL&application_no="+m_application_no+"&chargeble_amount="+m_chargeble_amount;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=500,resizable=1");
}

function show_charge_drill(m_application_no,m_pricing_no,m_charge_type,m_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_DOCUMENT_CHARGE_DRILL&application_no="+m_application_no+"&charge_type="+m_charge_type+"&code="+m_code+"&pricing_no="+m_pricing_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=500,resizable=1");
}
function show_charge_drill(m_application_no,m_pricing_no,m_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_DOCUMENT_CHARGE_DRILL2&application_no="+m_application_no+"&code="+m_code+"&pricing_no="+m_pricing_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=500,resizable=1");
}


function show_total_rental_drill(m_finance_no,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_TOTAL_RENTAL_DRILL&date="+m_date+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}

function show_outstanding_rental_drill(m_finance_no,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_OUTSTANDING_RENTAL_DRILL&date="+m_date+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}

function show_arrears_rental_drill(m_finance_no,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_ARREARS_DRILL&date="+m_date+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}





function show_rental_paid_drill(m_finance_no,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_RENTAL_PAID_DRILL&date="+m_date+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=650,resizable=1");
}


function show_std_order_drill(m_std_order_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_STD_ORDER_DRILL&std_order_no="+m_std_order_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_docrefno_drill(m_docref_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_DOCREF_NO_DRILL&docref_no="+m_docref_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function show_master_lease_agreement_drill(m_agreement_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_MASTER_LEASE_AGREEMENT_DRILL&agreement_no="+m_agreement_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}

function call_drill(m_no){	
	if(m_no.substr(0,2)=='IN'){ 
		show_invoice_drill(m_no);
	}else if(m_no.substr(0,2)=='SR'){ 
		show_settle_receipt_drill(m_no);
	}else if(m_no.substr(0,2)=='AP'){ 
		show_application_detail_drill(m_no);
	}else if(m_no.substr(0,2)=='SP'){ 
		show_payment_drill(m_no);
	}else if(m_no.substr(0,2)=='VP'){ 
		show_sus_payment_drill(m_no);
	}else{ 
		show_finance_detail_drill(m_no);
	}
}

function show_advertistment_drill(m_add_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_ADVERTISTMENT_DRILL&add_no="+m_add_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}
function show_buss_sectors(m_bus_sect_no){
	
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_BUS_SECT_DRILL&bus_sect_no="+m_bus_sect_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}
function show_buss_sub_sectors(m_bus_sub_sect_no){
	
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_BUS_SUB_SECT_DRILL&bus_sub_sect_no="+m_bus_sub_sect_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}
function show_mk_officer(m_inquiry_no){
	
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_MKT_OFFICER_DRILL&inquiry_no="+m_inquiry_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}
function show_client_name_drill(m_client_name){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_CLIENT_NAME_DRILL&full_name="+m_client_name;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=450,width=600,resizable=1");
}
function show_branch_drill(m_branch_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_BRANCH_DRILL&branch_code="+m_branch_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=300,width=450,resizable=1");
}
function show_city_drill(m_city_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_CITY_DRILL&city_code="+m_city_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=450,resizable=1");
}
function show_location_drill(m_location_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_LOC_DRILL&loc_code="+m_location_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=450,resizable=1");
}
function show_employee_drill(m_emp_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_EMP_DRILL&emp_code="+m_emp_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=450,resizable=1");
}

//added by nuwan de silva on 30-08-07----------
function show_os_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_OS_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 30-08-07----------
function show_set_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_SET_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 30-08-07----------
function show_bal_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_BAL_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 30-08-07----------
function show_invoiced_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_INVOICED_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 30-08-07----------
function show_invoiced_set_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_INVOICED_SET_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 30-08-07----------
function show_invoiced_set_bal_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_INVOICED_SET_BAL_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_invoiced_tot_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_TOT_INV_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_invoiced_tot_set_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_TOT_INV_SET_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_invoiced_tot_bal_detail_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_TOT_INV_BAL_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_officer_contract_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_COLL_OFFICER_CONTRACT_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_officer_un_all_rec_drill(m_user_id,m_date){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_COLL_OFFICER_UN_ALL_REC_DRILL&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_inv_os_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_DETAIL_INV_OS_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_inv_collect_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_DETAIL_INV_COLLECT_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_inv_bal_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_DETAIL_INV_BAL_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_cur_month_invoiced_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_CUR_MONTH_DETAIL_INV_INVOCED_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_cur_month_invoiced_settled_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_CUR_MONTH_DETAIL_INV_SETT_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_total_invoiced_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_TOTAL_DETAIL_INV_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_total_settled_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_TOTAL_SETTLE_DETAIL_INV_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}
//added by nuwan de silva on 31-08-07----------
function show_collection_detail_total_balance_drill(m_user_id,m_date,finance_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_TOTAL_BALANCE_DETAIL_INV_DRILL&finance_no="+finance_no+"&m_date="+m_date+"&user_id="+m_user_id;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=360,width=650,resizable=1");
}












function show_designation_drill(m_desig_code){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_four?chksql=SHOW_DESG_DRILL&desig_code="+m_desig_code;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=250,width=450,resizable=1");
}

//function show_rental_info_2(m_client_code){
//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_three?chksql=SHOW_RENTAL_INFO&client_code="+m_client_code;
//load_interface(m_url,'NORM');
//}

function show_POD_drill_2(m_pod_ref_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_POD_CHEQUE_DRILL&pod_ref_no="+m_pod_ref_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_invoice_info_2(m_invoice_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&invoice_no="+m_invoice_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_rent_invoiced_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_INVOICED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_rent_settled_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_SETTLED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_rent_balance_to_be_received_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_BAL_TO_BE_RECEIVED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

//ADDED BY NUWAN DE SILVA ON 20-07-08
function show_rent_invoiced_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_INVOICED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_rent_settled_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_SETTLED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_rent_balance_to_be_received_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_BAL_TO_BE_RECEIVED_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}
//================================================



function show_odi_cal_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_odi_bal_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_odi_set_drill_2(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1'); 
}

function show_odi_bal_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_odi_set_drill(m_application_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&application_no="+m_application_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1'); 
}


function show_contract_details(m_finance_no){//Added by MALIK on 18-08-2008
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Contract_Report?chksql=main_page&print=TRUE&finance_no="+m_finance_no;
	window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1'); 
}

function show_insurance_ledger(m_finance_no,m_client_code){//Added by Sandun on 18-08-2008
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_Insurance_Ledger?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code="+m_client_code+"&finance_no="+m_finance_no;
	window.open(m_url,"popupwin1","status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}
//================================== add by indika 09/16/08 ======================================================
function load_data_report(m_app_no) {
	//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&applicaton_no="+m_app_no; // commented by udara 22-05-2014
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&letter=first&applicaton_no="+m_app_no; // added by udra 22-05-2014
	window.open(m_url,'displayWindowap','left=50,top=60,width=750,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1'); 
	
	
}

//================================== end of adding by indika 09/16/08 =============================================

// added by udara on 16-10-2013
function load_documents_required(m_app_no,m_cli_no) {
	//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CR_PRO_Doc_require_drill?chksql=Report&applicaton_no="+m_app_no;
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MK_Application_Process_Document_Required_2?chksql=main_page&Hid_scr_name=AF_MK_APPLICATION_PROCESS&APP_NO="+m_app_no+"&TXT_TYPE=HIREPURCH&CLIENT_CODE="+m_cli_no+"&CORE_APP_CODE=-&ac_status=Y&hid_records=1";
	//LAKDL_AF_MK_Application_Process_Document_Required?chksql=main_page&Hid_scr_name=AF_MK_APPLICATION_PROCESS&APP_NO=AP20131016-3625&TXT_TYPE=HIREPURCH&CLIENT_CODE=0000000915&CORE_APP_CODE=-&ac_status=Y&hid_records=1
	window.open(m_url,'displayWindowap','left=50,top=60,width=750,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1'); 			
}
// end by udara on 16-10-2013



function show_odi_breakup(m_invoice_no){//Added by Sandun on 20-01-2009
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_BREAKUP&inv_no="+m_invoice_no;
	window.open(m_url,"popupwin6","left=100,top=100,center=yes,status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1");
}  
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!Sandun on 19-03-2009!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
function show_legal_due_invoice_drill(m_fin_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_LEGAL_DUE_INVOICE&application_no="+m_fin_no;
	window.open(m_url,'displayWindow200','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}

function show_legal_credit_invoice_drill(m_fin_no){
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_LEGAL_CREDIT_INVOICE&application_no="+m_fin_no;
	window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}
//[Added milinda for links on 12-10-2017]
function chk_confirmation_count(m_fin_no,m_row,m_count_confirmation){
	//alert(m_count_confirmation);
	//IN LIVE
	if (m_count_confirmation=='GEN'){ 
			// m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE1&finance_no="+m_fin_no+"&row_id="+m_row;
			//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=Y&compare_status=N&view_only_status=N&snap_position=GENERATE&finance_no="+m_fin_no+"&generate_status=N";
			m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE1&finance_no="+m_fin_no+"&row_id=0";
			window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
		}else {
			m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no="+m_fin_no;
			//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=Y&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no=RLDH/12/17/2168&row_id=null";
			window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
			
		}
	
	//FUTURE MODIFICATION
	/*if (m_count_confirmation=='GEN'){ 
		m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap_cr_app?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE1&finance_no="+m_fin_no+"&row_id=0";
	    window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
	}else {
		m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap_cr_app?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no="+m_fin_no;
		//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=Y&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no=RLDH/12/17/2168&row_id=null";
		window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
		
	}*/
}


function view_documents_cr_approval(m_fin_no){
	//m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MK_Document_upload?chksql=view_documents&finance_no="+m_fin_no; // commented by udara 13-03-2018
	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MK_Document_Upload_New_View?chksql=viewAllDocuments&finance_no="+m_fin_no; // added by udara 13-03-2018
	window.open(m_url,'displayWindow201','left=120,top=50,status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');
}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
