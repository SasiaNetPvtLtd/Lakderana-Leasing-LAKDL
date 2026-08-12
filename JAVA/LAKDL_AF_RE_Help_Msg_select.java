public class LAKDL_AF_RE_Help_Msg_select{  

	public Object Ret_Object = new Object();
	//------------------------------------------------------------------------
  
	
	public String m_help_msg_AF_RE_COLLECTION_FOLLOW_UP ="";
	public String m_help_msg_AF_RE_COLLECTION_FOLLOW_UP_Header="Collection - Follow up";
	
	public String m_help_msg_AF_RE_Lease_Above_six_months ="";//added by nuwan de silva on 03-10-07
	public String m_help_msg_AF_RE_Lease_Above_six_months_Header ="Collection - Leases Above Six Months";
	
	public String m_help_msg_AF_RE_APP_Status_change ="";//added by nuwan de silva on 03-10-07
	public String m_help_msg_AF_RE_APP_Status_change_Header ="Collection - Application Status Change";
	
	public String m_help_msg_AF_RE_APP_Status_change_approval ="";
	public String m_help_msg_AF_RE_APP_Status_change_approval_Header ="Collection - Application Status Change Approval";
	
	public String m_help_msg_AF_RE_Renewal_Data="";
	public String m_help_msg_AF_RE_Renewal_Data_Header="Collection - Renewal Data";
	
	public String m_help_msg_AF_RE_Vehicle_In_Hand="";
	public String m_help_msg_AF_RE_Vehicle_In_Hand_Header="Collection - Vehicle In Hand ";
	
	public String m_help_msg_AF_RE_Post_Dated_Cheque_Deposit="";
	public String m_help_msg_AF_RE_Post_Dated_Cheque_Deposit_Header ="Collection - Post Dated Cheque Deposit";
	
	public String m_help_msg_AF_RE_DEPOSIT_SLIP         ="";
	public String m_help_msg_AF_RE_DEPOSIT_SLIP_Header  ="Collection - Deposit Slip Printing";
	
	public String m_help_msg_LAKDL_AF_RE_INVOICE_AGE_ANALYSIS ="";
	public String m_help_msg_LAKDL_AF_RE_INVOICE_AGE_ANALYSIS_Header ="Collection - Invoice Age Analysis";
	
	public String m_help_msg_LAKDL_AF_RE_Other_invoices         ="";
	public String m_help_msg_LAKDL_AF_RE_Other_invoices_Header  ="Invoicing - Other Invoices";
	
	public String m_help_msg_AF_RE_Collection_Report="";
	public String m_help_msg_AF_RE_Collection_Report_Header ="Collection - Collction Report";
	
	
	public String m_help_msg_AF_RE_Portfolio_Quality_Statement  ="";
	public String m_help_msg_AF_RE_Portfolio_Quality_Statement_Header ="Collection - Portfolio Quality Statement";
	
	public String m_help_msg_AF_RE_Collection_Due_select              ="";
	public String m_help_msg_AF_RE_Collection_Due_select_Header	      ="Collection - Due Rreport";
	
	public String m_help_msg_LAKDL_AF_RE_Settlement         ="";
	public String m_help_msg_LAKDL_AF_RE_Settlement_Header  ="Collection - Settlement";
	
	public String m_help_msg_LAKDL_AF_CO_return_realization_display         ="";
	public String m_help_msg_LAKDL_AF_CO_return_realization_display_Header  ="Collection - Return & Realization";
	
	public String m_help_msg_LAKDL_AF_RE_bank_settlement                 ="";
	public String m_help_msg_LAKDL_AF_RE_bank_settlement_Header          ="Collection - Receipt Deposit ";
	
	public String m_help_msg_LAKDL_AF_RE_Collection_Temp_receipt          ="";
	public String m_help_msg_LAKDL_AF_RE_Collection_Temp_receipt_Header   ="Collection - Temp Receipt";
	
	public String m_help_msg_AF_RE_Lease_Assign                           ="";
	public String m_help_msg_AF_RE_Lease_Assign_Header                    ="Collection -Assign Collection Officer";
	
	public String m_help_msg_AF_RE_Collection_Due_Status                  ="";
	public String m_help_msg_AF_RE_Collection_Due_Status_Header           ="Collection - Due Letter Status";
	
	public String m_help_msg_AF_RE_Vehicle_Inventory                     ="";
	public String m_help_msg_AF_RE_Vehicle_Inventory_Header              ="Collection - Vehicle Inventory";
	
	public String m_help_msg_LAKDL_AF_RE_Collection_Advertistment_Generation="";
	public String m_help_msg_LAKDL_AF_RE_Collection_Advertistment_Generation_Header="Collection - Advetistment Generation";
	
	public String m_help_msg_AF_RE_Collection_Advertistment_Offers_Process				="";
	public String m_help_msg_AF_RE_Collection_Advertistment_Offers_Process_Header	="Collection - Advertistment Offer Process";
	
	public String m_help_msg_AF_RE_Collection_Advertistment_Offers_Issue				="";
	public String m_help_msg_AF_RE_Collection_Advertistment_Offers_Issue_Header ="Collection - Offer Issue Process";
	
	public String m_help_msg_LAKDL_AF_RE_Collection_Legal_Activities          ="";
	public String m_help_msg_LAKDL_AF_RE_Collection_Legal_Activities_Header   ="Collection - Legal Activities";
	
	public String m_help_msg_LAKDL_AF_RE_Collection_Legal_Actions							="";
	public String m_help_msg_LAKDL_AF_RE_Collection_Legal_Actions_Header  		="Collection - Legal Actions";			
	
	public String m_help_msg_AF_RE_Collection_Recovering_Finance_cost             ="";
	public String m_help_msg_AF_RE_Collection_Recovering_Finance_cost_Header      ="Collection - Recovering Finance Cost";
	
	public String m_help_msg_LAKDL_AF_RE_Repossession                             ="";
	public String m_help_msg_LAKDL_AF_RE_Repossession_Header                      ="Collection - Repossession";

	public String m_help_msg_LAKDL_AF_MAS_display_pod_cheques="";
	public String m_help_msg_LAKDL_AF_MAS_display_pod_cheques_Header="Collection - Post Dated Cheques- Entry";
	
	
	public String m_help_msg_LAKDL_AF_RE_display_inventory_approval="";
	public String m_help_msg_LAKDL_AF_RE_display_inventory_approval_Header="Collection - Vehicle Inventory Approval";
	
	public String m_help_msg_LAKDL_AF_RE_display_group_code="";
	public String m_help_msg_LAKDL_AF_RE_display_group_code_Header="Collection - Group Receipts";

	
	

	//-------------------------------------------------------------------------
	
	public Object getSql(Object reqObj1) {
		String m_help_message  = (String) reqObj1;
	
	m_help_msg_LAKDL_AF_RE_display_inventory_approval="<p>Vehicle Inventory Approval</p>"+
	"<p>Approval can be done for vehicles added through Entry of Vehicle Inventory screen.</p>";
  m_help_msg_LAKDL_AF_MAS_display_pod_cheques="<p>Post Dated Cheques-Entry</p>"+
	"<p>This option can be used to record details of post dated cheques in the system till deposit those cheques. Add option can be used when there are more than one cheque.</p>";
	m_help_msg_LAKDL_AF_RE_bank_settlement="<p>Cheques and cash collected from clients have to be deposited in the bank and those records will be entered to the system from this section. This section consists of following options.</p>"+
  "<blockquote>"+
	"<p><li>	New</p>"+
	"<p><li>	Delete</p>"+
	"<p><li>	Save</p>"+
	"<p><li>	Help</p>"+
	"<p><li>	Cancel</p>"+
	"<p><li>	Close</p>"+
	"<p><li>	Pending Receipts </p>"+
	"</blockquote>"+	
	"<p>New</p>"+
	"<p>Upon selection of cash or cheque under the settlement mode pending receipts will be listed on the screen. Account number of the bank which we are going to deposit cash or cheques has to be selected. User can select the collections which will be deposited on that day. After entering required details record can be saved. Settlement deposit code will be generated by the system. </p>"+
	"<p>Delete</p>"+
	"<p>Settlement deposit code can be selected from the help option. Once the Settlement deposit code selected cheques or cash deposited with its bank details, amounts and receipt numbers will be shown. Entry can be saved using the save option.</p>"+
	"<p>View Pending Receipts</p>"+
	"<p>All the pending receipts including cash and cheque which are not deposited in bank account can be seen.</p>";
	
	m_help_msg_AF_RE_Collection_Due_select="This option is used to view collection due reports";
	
	m_help_msg_LAKDL_AF_CO_return_realization_display = "This option is used in Return & Realization";
	
	m_help_msg_LAKDL_AF_RE_Settlement="<p>Settlement details will be entered from this section. When clients are making rental payments against the invoices raised by the Financing Company, those details will be recorded from section. This section consists of following options.</p>"+
  "<blockquote>"+
	"<p><li>	New</p>"+
	"<p><li>	Delete</p>"+
	"</blockquote>"+	
	"<p>New</p>"+
	"<p>When entering a new record, Value date, amount, and reference number have to be entered. Client, Payer account number, settlement mode can be selected from the relevant help option. Upon selection of Payer account number Payer branch field automatically gets filled. Once all the records are entered record can be saved using the save option. Settlement code will be generated by the system. If there are cheque returns for the selected client, those details will be listed in the screen. Settlements can be allocated to invoices from this screen. If user doesn't wants to allocate invoices, can click on status and then entry can be saved. </p>"+
	"<p>Delete</p>"+
	"<p>To delete an existing record settlement number has to be selected and entry should be saved using the Save option.</p>";
	
	m_help_msg_LAKDL_AF_RE_Other_invoices="<p>Invoices other than rental invoices will be fed to the system through other Invoices. This section consists of following options.</p>"+
  "<blockquote>"+
	"<p><li>	New</p>"+
	"<p><li>	Edit</p>"+
	"<p><li>	Delete</p>"+
	"</blockquote>"+	
	"<p>New</p>"+
	"<p>Invoice type can be selected from the drop down menu. Finance number, date can be selected and net vat amounts can be entered. The record can be saved using the Save option.</p>"+
	"<p>Edit</p>"+
	"<p>Modifications to the existing invoice can be done through Edit option. After doing the necessary changes entry can be saved.</p>"+
	"<p>Delete</p>"+
	"<p>To delete an existing invoice, invoice number has to be selected and entry should be saved using the Save option.</p>";
	
	m_help_msg_LAKDL_AF_RE_Collection_Temp_receipt="This option used to create ,modify,reactive or deactive temp receipts";
	
	m_help_msg_AF_RE_Lease_Assign   ="<p>After the lease is activated, finance number will be given for a facility. Those facilities will be listed in this screen in order to assign a collection officer. This section consists of following options.</p>"+
  "<blockquote>"+
	"<p><li>	New</p>"+
	"<p><li>	Edit</p>"+
	"</blockquote>"+	
	"<p>New</p>"+
	"<p>Officer can be selected from the help option which is there in front of the record. The record can be saved using the Save option.</p>"+
	"<p>Edit</p>"+
	"<p>Selected officer can be changed from this option and entry has to be saved again.</p>";
	
	
	m_help_msg_AF_RE_Collection_Due_Status="This option used to change the staus";
	
	m_help_msg_AF_RE_Vehicle_Inventory="This option caters to add vehicle to the vehicle inventory. After selecting the relevant application number and the vehicle number, check list should be completed through the system and saved. Edit existing vehicle details or Delete can be done through vehicle inventory option. ";
	
	m_help_msg_LAKDL_AF_RE_Collection_Advertistment_Generation="This option used to create ,modify or delete advertistments";
	
	m_help_msg_AF_RE_Collection_Advertistment_Offers_Process="This option used to create ,modify or delete advertistments offers";
	
	m_help_msg_AF_RE_Collection_Advertistment_Offers_Issue="This option used to issue the offer";
	
	m_help_msg_LAKDL_AF_RE_Collection_Legal_Activities="This option used to create,modify or delete legal activities";
	
	m_help_msg_LAKDL_AF_RE_Collection_Legal_Actions="This option used to crate,modify or delete legal actions";
	
	m_help_msg_AF_RE_Collection_Recovering_Finance_cost="This option used to recovering the finance cost";
	
	m_help_msg_LAKDL_AF_RE_Repossession="This option used to create,modify or delete repossession";
	
	m_help_msg_AF_RE_Portfolio_Quality_Statement="This option used to generate the Portfolio Quality Statement";
	
	m_help_msg_AF_RE_Collection_Report="This option used to generate the collection report";
	
	m_help_msg_LAKDL_AF_RE_INVOICE_AGE_ANALYSIS="This option used to generate the invoice age analysis";
	
	m_help_msg_AF_RE_DEPOSIT_SLIP="This option used to print the deposit slips";
	
	m_help_msg_AF_RE_Post_Dated_Cheque_Deposit="This option used to receipt generation for the post dated cheques";
	
	m_help_msg_AF_RE_COLLECTION_FOLLOW_UP="This is option used to generate the follow up ";
	
	m_help_msg_AF_RE_Vehicle_In_Hand="This option display the vehicle in hand";
	
	m_help_msg_AF_RE_Renewal_Data="This option display the Renewal Data";
	
	m_help_msg_LAKDL_AF_RE_display_group_code="This option display the Group Codes.";
	
	m_help_msg_AF_RE_Lease_Above_six_months="This option display the Leases Above Six Months";//added by nuwan de silva on 03-10-07
	
	m_help_msg_AF_RE_APP_Status_change="This option used to change the apllication status"; //added by nuwan de silva on 03-10-07
	
	m_help_msg_AF_RE_APP_Status_change_approval ="This option used to approve the change of application status";
	
	Ret_Object= (Object)m_help_message;
		return Ret_Object;
		
	} 
}
