
public class LAKDL_AF_CO_PRO_Help_Msg_select{  

	public Object Ret_Object = new Object();
	//------------------------------------------------------------------------
  
	
	
		
	public String m_help_msg_LAKDL_AF_CO_PRO_vat_on_rental                             ="";
	public String m_help_msg_LAKDL_AF_CO_PRO_vat_on_rental_Header                      ="System Administration - VAT on Rental";
	
	

	//-------------------------------------------------------------------------
	
	public Object getSql(Object reqObj1) {
		String m_help_message  = (String) reqObj1;
	

	m_help_msg_LAKDL_AF_CO_PRO_vat_on_rental="This option is used to change the vat rental on item sub category";
	
	
	
	
	Ret_Object= (Object)m_help_message;
		return Ret_Object;
		
	} 
}
