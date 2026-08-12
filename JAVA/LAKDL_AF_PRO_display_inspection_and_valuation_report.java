
//--
//SCREEN NAME:SYSTEM ADMINISTRATION -INSPECTION AND VALUATION_REPORT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_display_inspection_and_valuation_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	String reqstr;
	Connection conn;
	public String m_val_code;
	public ResultSet rs;
	public ResultSetMetaData rms; 
	Statement stmt;
	public String m_val_code1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			m_val_code1=req.getParameter("hid_value");
			
			String m_val = req.getParameter("APP_NO");
			//out.println(m_val);

			//Object Obj_Input2 = (Object) Start_Val;
        //Object Obj_Input3 = (Object) Stop_Val ;
				


			/* String Class_Name=req.getParameter("class_in");
        String SqlName=req.getParameter("Sql_in");		
        String Start_Val=req.getParameter("Start_in");		  
        String Stop_Val=req.getParameter("End_in");
        String Hid_No=req.getParameter("Hid_No");
        String Head_Name= SqlName+"_Header"; 
        String Criteria	= req.getParameter("Crit_In").toUpperCase();
				
				

				rms = rs.getMetaData();
		//boolean more = rs.next() ;
		int count    = rms.getColumnCount();*/
				//if (m_val.trim().equals("")) {
				//out.println("Cant Open");
			//}
			//else{
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration -Inspection and Valuation Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
			//out.println("int vend=0;");
			out.println("var no=0;");
			out.println("var val_of=0;");
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var product_arry=new Array();");
			out.println("var product1_arry=new Array();");
			out.println("var product2_arry=new Array();");
			out.println("var product3_arry=new Array();");
			out.println("var use=new Array();");
			out.println("var m_code;");
			out.println("var m_sub;");
			out.println("var m_turn=\"1\";");
			out.println("var fuel=new Array();");
			out.println("var m_help=0;");

//###############################################################################################################################################################################
			out.println("function get_vector(data_vec) {");
			
			//out.println("				alert(document.Form1.hid_st.value)");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value=='T1' ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			//out.println("data_vec.length=0");
			out.println("			}");//To Check new valuation code exists.
			
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T1'){");
			out.println("alert('Selected Valuation Code is incorrect,use help...!')");
			out.println("    document.Form1.TXT_VALUATION_NO.value='';");
			out.println("    document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("data_vec=''");
			out.println("}");//To Check valuation code correct.
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T1' && document.Form1.hid_help_type.value!=\"99\"){");
			//out.println("m_turn=\"2\";");
			//out.println("alert(document.Form1.hid_help_type.value);"); 
			out.println("help_edit()");
			out.println("data_vec=''");
			out.println("}"); //To fill fields data in edit/delete mode.
			
			out.println("else if(document.Form1.TXT_VALUATION_NO.value=='' && document.Form1.hid_st.value=='T1'){");
			//out.println("alert('Enter Valuation No..!');"); 
			//out.println("document.Form1.TXT_VALUATION_NO.value='';");
			out.println("document.Form1.TXT_VALUATION_NO.focus();");
			out.println("data_vec=''");
			out.println("}"); //To Check valuation code is entered.
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_x.value=='5' && document.Form1.TXT_ASSET_ID.value!='' &&  document.Form1.hid_st.value=='T4'){");
			out.println("alert('Entered Asset Id is wrong,use help...! ')");
			out.println("document.Form1.TXT_ASSET_ID.value='';");
			out.println("document.Form1.TXT_ASSET_ID.focus();");	
			out.println("data_vec='';");
			out.println("}"); //To Check asset code is correct.
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_x.value=='6' && document.Form1.TXT_ITEM_CODE.value!='' &&  document.Form1.hid_st.value=='T2'){");
			out.println("alert('Entered Item Code is wrong,use help...! ')");
			out.println("document.Form1.TXT_ITEM_CODE.value='';");
			out.println("document.Form1.TXT_ITEM_CODE.focus();");	
			out.println("data_vec='';");
			out.println("}"); //To Check item code is correct.
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_x.value=='7' && document.Form1.TXT_MODEL_CODE.value!='' &&  document.Form1.hid_st.value=='T3'){");
			out.println("alert('Entered Model Code is wrong,use help...! ')");
			out.println("document.Form1.TXT_MODEL_CODE.value='';");
			out.println("document.Form1.TXT_MODEL_CODE.focus();");
			out.println("data_vec='';");
			out.println("}");//To Check model code is correct.
			

			out.println("else if (document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value=='T2'){");
			out.println("display_data(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when valuation is selected.
			
			out.println("else if (document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value=='T3'){");
			out.println("display_data(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when valuation code is selected.
			
			out.println("else if (data_vec.length>0 && document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T2'){");
			out.println("display_fields(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when an item code is selected.
			
			out.println("else if (document.Form1.hid_val.value=='2' && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T3'){");
			out.println("use=data_vec[0]");
			out.println("m_code=document.Form1.TXT_MODEL_CODE.value");
			out.println("data_vec=''");
			out.println("}");//To find model code when an item is selected.
				
			out.println("else if(document.Form1.hid_val.value=='3'){");
			out.println("document.Form1.TXT_FUAL_TYPE.value=data_vec[0]");
			out.println("data_vec=''");
			out.println("}");//To find fuel type when an model is selected.

			out.println("else if(document.Form1.hid_val.value=='4' && (document.Form1.hid_st.value=='T1' || document.Form1.hid_st.value=='T2')){");
			//out.println("alert('ww')");
			out.println("document.Form1.TXT_ITEM_CODE.value=data_vec[3]");
			out.println("data_vec=''");
			out.println("}");//To find item code.
		
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");//To seperatly identify m_url's.
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function assig1(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_act.value='Y';");//To seperatly identify active status accordingly.
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_act.value='N';");
			out.println("}");
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function asset(obj) {");//To get asset details for validation.
			out.println("document.Form1.hid_x.value='5';");
			out.println("if(document.Form1.hid_st.value=='T4' && (document.Form1.SCREEN_NAME.value!=\"DACT\")){;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_asset_details&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function model(obj) {");//To get model details for validation.(not used)
			out.println("document.Form1.hid_x.value='';");
			out.println("if(document.Form1.hid_st.value=='T3'){;");
			out.println("document.Form1.hid_x.value='7';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function item_val(obj){");//To get item details for validation.
			out.println("document.Form1.hid_x.value='';");
			out.println("if(document.Form1.hid_st.value=='T2'){;");
			out.println("document.Form1.hid_x.value='6';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function makeRequest(obj) {");//To get valuation details for validation.
			out.println("");
			out.println("document.Form1.hid_x.value=\"8\";");
			out.println("if(document.Form1.hid_st.value=='T1'){;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspection_and_valuation_report&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function makeRequest1() {");//To find fields accordin to an item.
			
			out.println("document.Form1.hid_val.value='1';");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value=='T2'){");//Only fields that exists for item code.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspect2&data_val=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("}");
			out.println("else if( document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T2'){");//To show fields that are already entered in valuation and all other fields added after.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspect4&data_val=\"+document.Form1.TXT_VALUATION_NO.value+\"&data_val1=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################


			out.println("function assig_item() {");//To find item code.
			out.println("document.Form1.hid_val.value='4'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspect3&data_val=\"+document.Form1.TXT_MODEL_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function fuel_type() {");//To find fuel type according to model.
			out.println("document.Form1.hid_val.value='3';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_fual2&data_val=\"+document.Form1.TXT_MODEL_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function header(){");//To set header(not used)
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code *</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td >Status *</td>'+"); 
			out.println("'<td>Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table >';"); 
			out.println("}");
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
		
			
			out.println("function display_data(data_vec){");//To fill data for field codes(new record).
			
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("if(document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.TXT_ITEM_CODE.value!=''){");
			
			out.println("if(data_vec.length==0){");
			//out.println("header()");
    
			out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td><input name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");
		  out.println("j=j+1;");	
			out.println("}");
			
			out.println("else{");
			//out.println("header()");
			out.println("while(i<data_vec.length){");
   		
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' value='+data_vec[i+1]+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' value=\"\" maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' value=\"\" maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");

			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");
					
			out.println("j=j+1;");
			out.println("i=i+2;");
			out.println("}"); 
			out.println("}"); 
	
			out.println("document.Form1.hid_no.value=j-1");	
			out.println("}");
			out.println("else {");
			out.println("change1.innerHTML=\"\";");
			//out.println("header()");
			/*out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td><input name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");*/
			//no need

			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");

			out.println("}");
      out.println("}"); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function display_fields(data_vec){");//To fill data for field codes(already entered record).
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("if(document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.TXT_ITEM_CODE.value!=''){");
			out.println("if(data_vec.length==0){");
			//out.println("header()");
    
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td><input name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");


		  out.println("j=j+1;");	
			
			out.println("}");
			
			out.println("else{");
			
			out.println("while(i<data_vec.length){");
			//out.println("header()");
				
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+");
			out.println("'<tr>'+"); 
			out.println("'<td><input name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' value='+data_vec[i+1]+' maxlength=\"20\" value=\"\" maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' value='+data_vec[i+2]+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' value='+data_vec[i+3]+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");

			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");
			
			out.println("var st='TXT_STATUS_'+j;");
			out.println("var rm='TXT_REMARK_'+j;");
   		out.println("if (document.Form1.elements[st].value=='xx'){");
			out.println("document.Form1.elements[st].value='';");
			out.println("}");
				
			out.println("if (document.Form1.elements[rm].value=='xx'){");
			out.println("document.Form1.elements[rm].value='';");
			out.println("}");

			out.println("j=j+1;");
			out.println("i=i+4;");
			
			out.println("}"); 
			out.println("}"); 
			out.println("document.Form1.hid_no.value=j-1");	
			out.println("}");
			out.println("else {");
			out.println("change1.innerHTML=\"\";");
			//out.println("header()");
			/*out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td><input name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");*/
			//no need

			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			//out.println("'</table >';");
			
			
			out.println("}");
			
      out.println("}"); 
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function XX() {");//To find make code according to item code
			//out.println("alert('RRR')");
			out.println("document.Form1.hid_val.value='2';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspect1&data_val=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APP_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APP_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VALUATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_VALUATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ASSET_ID.value==\"\"){  "); 
			out.println("DIV_TXT_ASSET_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_REG_NO.value==\"\"){  "); 
			out.println("DIV_TXT_REG_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ENGINE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ENGINE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CHASSIS_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CHASSIS_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
			out.println("DIV_TXT_COLOUR.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		if(validate_data()){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_inspection_and_valuation_report?number='+ document.Form1.hid_no.value+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function load_lock(){	"); 
		//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("		document.Form1.TXT_APP_NO.value='"+ m_val+"' ");
			out.println("}	"); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_inspection_and_valuation_report?APP_NO="+m_val+"';"); 
			out.println("		}"); 
			out.println("}"); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function new_window(){	");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_inspection_and_valuation_report?APP_NO="+m_val+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_inspection_and_valuation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
		

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration -Inspection and Valuation Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################


			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration -Inspection and Valuation Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function application_no(){");
			out.println("    document.Form1.TXT_APP_NO.value=\""+m_val+"\";"); 
			out.println("    document.Form1.hid_app_no.value=\""+m_val+"\";"); 

			//out.println("    document.Form1.TXT_APPLICATION_NO.value=\"'+document.Form1.hid_app_no.value+'\";"); 
			out.println("    document.Form1.TXT_APP_NO.disabled=true;"); 
		
			out.println("}"); 
			
	
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_VALUATION_NO.disabled=true;");
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){");
			
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=false;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;");
			out.println("document.Form1.TXT_ITEM_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=false;"); 
			
			out.println("document.Form1.TXT_NOTES.disabled=false;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=false;"); 
			out.println("document.Form1.TXT_VALUATION_DATE.disabled=false;"); 
			out.println("document.Form1.TXT_VALUE.disabled=false;"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.disabled=false;"); 
			out.println("document.Form1.TXT_DATE_OF_REG.disabled=false;"); 
			out.println("document.Form1.TXT_METER_READING.disabled=false;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){"); 
			out.println("document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("document.Form1.BUT_TXT_ASSET_ID.disabled=true");
			out.println("document.Form1.BUT_ITEM_CODE.disabled=true");
			out.println("document.Form1.BUT_TXT_MODEL_CODE.disabled=true");
			out.println("document.Form1.BUT_TXT_SUB_MODEL_CODE.disabled=true");
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 

			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 

			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_2() {");//Asset help 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ASSET_ID_sql1\";"); 
			out.println("    m_criteria = document.Form1.TXT_ASSET_ID.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[2];");
			
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_3() {");//Sub model help 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("if(document.Form1.TXT_MODEL_CODE.value!=''){");
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_CODE_sql1\";");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+m_sub+\"@Y@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MODEL_CODE.value==''){");
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_MODEL_CODE.value+\"@Y@\";");
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_4() {"); //Model help 
			out.println("    document.Form1.hid_help_type.value=\"4\";");
			out.println("if(document.Form1.TXT_ITEM_CODE.value!=''){");
			out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql1\";"); 
			out.println("m_criteria=use+\"@\"+m_code+\"@Y@\";");
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ITEM_CODE.value==''){");
			out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@Y@\";"); 
			out.println("}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[2];");
			out.println("fuel_type()");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.focus() ");
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_5() {");//Type Body help(not used)  
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_TYPE_OF_BODY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_TYPE_OF_BODY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[2];"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_6(y) {"); //feild code help(not used)
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("document.Form1.hid_v.value=y");
			out.println("    m_sql = \"m_help_TXT_FILED_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FILED_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_value_assign_6() {");
			out.println("no=document.Form1.hid_v.value");
			out.println("			for(var i=0;i<=no;i++)");
			out.println("		{");
			out.println("		var m_br;");
			out.println("		m_br='TXT_FILED_CODE_'+no;");
			out.println("		var m_desc;");
			out.println("		m_desc='TXT_FILED_DESC_'+no;");
			out.println("document.Form1.elements[m_br].value=oBj.valout[2];");
			out.println("document.Form1.elements[m_desc].value=oBj.valout[3];");
			out.println("}");
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_button_7(y) {");//Item Code help  
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";"); 
			
			out.println("    m_criteria = document.Form1.TXT_ITEM_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_value_assign_7() {");
			out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[2];"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("XX()");
			out.println("}");
			out.println("document.Form1.TXT_MODEL_CODE.focus() ");
			out.println("}"); 

//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function d() {");//used when deleting to stop changing the fields.
			out.println("if(document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("alert ('You cannot enter a new record..!');	");
			out.println("window.event.keyCode='';	");	
			out.println("}	");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("function valu(val) {");
			out.println("m_turn=val;");
			out.println("document.Form1.hid_st.value=\"T1\";");
			//out.println("alert('1@'+m_turn)");
			out.println("}");
			
			out.println("function turn(val) {");
			out.println("m_turn=val;");
			out.println("document.Form1.hid_st.value=\"T1\";");
			//out.println("alert('2@'+m_turn)");
			out.println("}");
			
			out.println("function check_sub() {");//To validate Sub Model code.
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DACT\"){ ");
			out.println("if(document.Form1.hid_st.value=='T5'){ ");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			//out.println("window.event.keyCode='';	");
			out.println("alert ('Use Help..!');	");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value='';");
			out.println("}	");	
			out.println("}	");	
			out.println("}	");
			out.println("}");
			
			out.println("function check() {");//To validate Model code.
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DACT\"){ ");
			out.println("if(document.Form1.hid_st.value=='T3'){ ");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("alert(window.event.keyCode)");
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			//out.println("window.event.keyCode='';	");
			out.println("alert ('Use Help..!');	");
			out.println("document.Form1.TXT_MODEL_CODE.value='';");
			out.println("}	");	
			out.println("}	");	
			out.println("}	");
			out.println("}");
			
			out.println("function dd(val){");//To find model code(to find a record like).
			out.println("m_code=val;");	
			out.println("}	");	
			
			out.println("function sub(val){");//To find Sub Model code(to find a record like).
			out.println("m_sub=val;");	
			out.println("}	");
			
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################


			out.println("function help_update() {"); //Valuation code help.
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("}");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			//out.println("    m_sql = \"m_help_TXT_VALUATION_DET_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO1_sql\";"); 
			out.println("}");

			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			//out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@Y@\";");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			//out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@N@\";}");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################


			out.println("function help_edit() {"); //To display help when valuation code is entered.
			out.println("");
			out.println("    document.Form1.hid_help_type.value=\"100\";");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_DET_sql\";"); 
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			//out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@Y@\";");
			out.println("    } ");
			out.println("    else{");
			out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@N@\";}");
			//out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','17');"); 
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_update_value_assign_99() {");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println(" }");
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			//out.println("    document.Form1.TXT_ASSET_ID.focus();");
			out.println("assig_item()"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[8];");
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[9];");
			out.println("    document.Form1.TXT_NOTES.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_VALUATION_DATE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_VALUE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_DATE_OF_REG.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[19];");
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[20];");
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[18]");
			//out.println("assig_item()"); 
			out.println("}"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function help_update_value_assign_100() {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[8];");
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[9];");
			out.println("    document.Form1.TXT_NOTES.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_VALUATION_DATE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_VALUE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_DATE_OF_REG.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[19];");
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[20];");
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[18]");
			out.println("document.Form1.TXT_ITEM_CODE.value=oBj.valout[21];"); 
			out.println("}"); 
			out.println("}"); 
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function clear(){");
			//out.println("document.Form1.BUT_VALUATION_NO.value='';"); 
			out.println("document.Form1.TXT_ASSET_ID.value='';"); 
			out.println("document.Form1.TXT_REG_NO.value='';"); 
			out.println("document.Form1.TXT_ENGINE_NO.value='';"); 
			out.println("document.Form1.TXT_CHASSIS_NO.value='';"); 
			out.println("document.Form1.TXT_COLOUR.value='';");
			out.println("document.Form1.TXT_ITEM_CODE.value='';"); 
			out.println("document.Form1.TXT_MODEL_CODE.value='';"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value='';"); 
			
			out.println("document.Form1.TXT_NOTES.value='';"); 
			out.println("document.Form1.TXT_REMARKS.value='';"); 
			out.println("document.Form1.TXT_VALUATION_DATE.value='';"); 
			out.println("document.Form1.TXT_VALUE.value='';"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.value='';"); 
			out.println("document.Form1.TXT_DATE_OF_REG.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.value='';"); 
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("}");
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function init(){"); //To set a value.
			/*out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description*</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+y+' value=\"Help\" onClick=\"help_button_6('+y+')\"></td>'+"); 
			out.println("'<td><input class=\"text_input\" name=TXT_FILED_CODE_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+y+' maxlength=\"20\" size=\"20\" ></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_STATUS_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input name=TXT_REMARK_'+y+' maxlength=\"100\" size=\"100\"></td>'+"); 
			//out.println("'<td width=\"30%\" >&nbsp</td>'+");
			//out.println("'<td width=\"30%\" >&nbsp</td>'+");
			out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");*/

			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+y+' value=\"Add\" onClick=\"Add('+y+')\">'+"); 
			//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+y+' value=\"Del\" onClick=\"Del('+y+')\"></td>'+"); 
			//out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'</tr >'+");
			
			//out.println("'</table >';");
			out.println("document.Form1.hid_no.value=y;");

			out.println("}"); 
//###############################################################################################################################################################################
//########## (NOT NEEDED) ######################################################################################################################################################
			out.println("function Add(y) {");
			out.println("val_of=document.Form1.hid_no.value;");
			out.println("var br_code;");
			out.println("var br_code1;");
			out.println("var br_code2;");
			out.println("var br;");
			out.println("br_code='TXT_FILED_DESC_'+y;");
			out.println("br_code1='TXT_STATUS_'+y;");
			out.println("br_code2='TXT_REMARKS_'+y;");
			out.println("br='TXT_FILED_CODE_'+y;");
			out.println("brcode=document.Form1.elements[br_code].value;");
			out.println("for(var i=0;i<val_of;i++){");
			out.println("br_code1='TXT_FILED_DESC_'+i");
			out.println("brcode1=document.Form1.elements[br_code1].value;");

			out.println("if(brcode==brcode1)");
			out.println("{");
			out.println("alert('Entered Product Description already exits...!');");
			out.println("document.Form1.elements[br_code].value='';");
			out.println("document.Form1.elements[br_code].focus();");
			out.println("return false;");
			out.println("break;");
			out.println("	}");
			out.println("	}");	
			out.println("y=y+1;");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code *</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+y+' value=\"Help\" onClick=\"help_button_6('+y+')\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_FILED_DESC_'+y+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+y+' maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+y+' value=\"Add\" onClick=\"Add('+y+')\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+y+' value=\"Del\" onClick=\"Del('+y+')\"></td>'+"); 
			out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("document.Form1.hid_no.value=y;");
			out.println("}");
					
					
			out.println("function Del(y) {");
			out.println("var e=0;");
			out.println("val_of=document.Form1.hid_no.value;");
			out.println("for(var i=0;i<=val_of;i++){");
			out.println("m_bcode='TXT_FILED_CODE_'+i;");
			out.println("m_b_code='TXT_FILED_DESC_'+i;");
			out.println("m_b_code1='TXT_STATUS_'+i;");
			out.println("m_b_code2='TXT_REMARK_'+i;");
			
			out.println("if(y==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
			
			out.println("if((document.Form1.elements[m_b_code].value=='')||(document.Form1.elements[m_b_code1].value=='')||(document.Form1.elements[m_b_code2].value==''))");
			out.println("{");
			out.println("product_arry[e]=0;");
			out.println("product1_arry[e]=0;");
			out.println("product2_arry[e]=0;");
			out.println("product3_arry[e]=0;");
			out.println("}");
			out.println("else{");
			out.println("alert(document.Form1.elements[m_b_code].value)");
			out.println("product_arry[e]=document.Form1.elements[m_b_code].value;");
			out.println("product1_arry[e]=document.Form1.elements[m_b_code1].value;");
			out.println("product2_arry[e]=document.Form1.elements[m_b_code2].value;");
			out.println("product3_arry[e]=document.Form1.elements[m_bcode].value;");
			out.println("e=e+1;");
			out.println("}");
			out.println("}");
			out.println("val_of=val_of-1;");
			out.println("change1.innerHTML='';");
			out.println("get_val(val_of)");
			out.println("document.Form1.hid_no.value=val_of;");
			out.println("}"); 		
			out.println("function get_val(val) {");
			out.println("for(var i=0;i<=val;i++){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" >Product Code*</td>'+");
			out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			out.println("'<td width=\"30%\" >Status *</td>'+"); 
			out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+i+' value=\"Help\" onClick=\"help_button_6('+i+')\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_FILED_DESC_'+i+' value='+product_arry[i]+'	maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+i+' value='+product1_arry[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+i+' value='+product2_arry[i]+'  maxlength=\"100\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+i+' value=\"Add\" onClick=\"Add('+i+')\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+i+' value=\"Del\" onClick=\"Del('+i+')\"></td>'+"); 
			out.println("'<td><input class=\"txt_input\" type=\"hidden\" name=TXT_FILED_CODE_'+i+' value='+product3_arry[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("}");
			out.println("document.Form1.hid_no.value=val;");
			out.println("}");		
						
//###############################################################################################################################################################################
//##(TO VALIDATE DATE AND NUMBERS)########################################################################################################################################################################
			
			out.println("function checkMonthLength() {");
			
			out.println("mm=document.Form1.hid_month.value;");
			out.println("dd=document.Form1.hid_day.value;");
			
			//out.println("mm=c_mm;");
			//out.println("dd=c_dd;");
			out.println("c_yyyy=document.Form1.hid_year.value;");
			
	  	//out.println("yyyy=parseInt(c_yyyy);");
			out.println("inputyearStr = c_yyyy;");
	 		//out.println("inputyearStr = c_yyyy.toString();");
			out.println("alert(inputyearStr)");
			out.println("if (inputyearStr.length != 4)");
			out.println("{");
			out.println("alert('Please enter year in four digit(YYYY) number format');");
			//out.println("c_yyyy.value='';	");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("c_yyyy.focus();");
			//out.println("return false");
	 		
			out.println("}");
			
			/*out.println("if (isNaN(dd))"); 
			out.println("{");
			out.println(" alert('Day should be a number');");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println(" c_dd.focus();");
			//out.println(" c_dd.value='';");	
			out.println(" return false");
	  	out.println("}");
	  	out.println("else if (dd<1 || dd>31) ");
			out.println("{");
		 	out.println("alert('Date must be between 1 and 31');");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println(" c_dd.focus();");
			//out.println(" c_dd.value='';	");
	 		out.println(" return false");
			out.println("}");
			out.println("else if (isNaN(mm)) ");
			out.println("{");
		 	out.println("alert('Month should be a number');");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println(" c_mm.focus();");
			//out.println(" c_mm.value='';	");
	 		out.println(" return false");
		
			out.println("}");
			out.println("else if (mm<1 || mm>12)");
			out.println("{");
		 	out.println("alert('Month must be between 1 and 12');");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println(" c_mm.focus();");
			//out.println(" c_mm.value=''	");
	 		out.println(" return false");
		
			out.println("}");
			out.println("else if (isNaN(yyyy)) ");
			out.println("{");
		 	out.println("alert('Year should be a number');");
		 //out.println("c_yyyy.value='';");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
		 	//out.println("c_yyyy.focus();");
		 	out.println("return false");
	
			out.println("}");
			out.println("else if (yyyy<1900 || yyyy>3000) ");
			out.println("{");
		 	out.println("alert('Year should be between 1990 and 3000');");
		 	//out.println("c_yyyy.value='';");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
		 	//out.println("c_yyyy.focus();");
		 	out.println("return false");
	
			out.println("}");
		
		
			out.println("var months = new");
			out.println("Array('','January','February','March','April','May','June','July','August','September','October','November','December')");
			out.println("if (mm==2) ");
			out.println("{");
		 //	checkLeapMonth(c_mm,c_dd,c_yyyy);
		//***************************************checking february*************************
			out.println("mm=parseInt(document.Form1.hid_month.value);");
			out.println("dd=parseInt(document.Form1.hid_day.value);");
			out.println("yyyy=parseInt(document.Form1.hid_year.value.value);");
	 
			out.println("			if (yyyy % 4 > 0 && dd > 28) ");
			out.println("			{");
			out.println("				 alert('February of' + yyyy + ' has only 28 days.')");
			//out.println("					c_dd.value='';");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("					c_dd.focus();");
			out.println("				return false");
			out.println("			} ");
			out.println("		else if (dd > 29) ");
			out.println("			{");
			out.println("					alert('February of ' + yyyy + ' has only 29 days.')");
			//out.println("					c_dd.value='';");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
     // out.println("				c_dd.focus();");
			out.println("					return false");
			out.println("			}");
			out.println("			else if (dd < 1 || dd > 29) ");
			out.println("			{");
			out.println("					alert('Date between 1 and 29.')");
			//out.println("			c_dd.value=''	");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("			c_dd.focus();");
			out.println("					return false");
			out.println("			}");
			out.println("			else {");
			out.println("					return true");
      out.println("     }");
 //************************************************************
			out.println("}");
			out.println("else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30) ");
			out.println("{");
			out.println("if(mm=='04'){	");
			out.println(" alert(months[04] + ' has only 30 days.')");
			out.println("}");
			out.println("else if(mm=='06'){");
			out.println("			 alert(months[06] + ' has only 30 days.')");
			out.println("}");
			out.println("else if(mm=='09'){");
			out.println("			 alert(months[09] + ' has only 30 days.')");
			out.println("}");
			out.println("else if(mm=='11'){");
			out.println("			 alert(months[11] + ' has only 30 days.')");
			out.println("}");
      out.println("else {");
			out.println("   alert('selcted month has only 30 days');");
			out.println("}");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("c_dd.focus();");
			// c_dd.value="";
			out.println(" return false");
			out.println("} ");
			out.println("else if ( dd > 31)");
			out.println("{");
			out.println("	alert(months[mm] + ' has only 31 days.')");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("	 c_dd.focus();");
			//out.println("	 c_dd.value='';");
			out.println("	 return false");
			out.println("}");
			out.println("else if ( dd < 1)");
			out.println("{");
			out.println("	alert('Please check the day.')");
			//out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("	 c_dd.focus();");
			///out.println("	 c_dd.value='';");
			out.println("	 return false");
			out.println("}");*/
			//out.println("return true");
			out.println("}");






//ChkDateValidation(document.Form1.TXT_DATE_FROM,document.Form1.TXT_MONTH_FROM,document.Form1.TXT_YEAR_FROM)
			
			out.println("function check_value(value) 	{");
			out.println("		var passed_decimalsize=4;");
			out.println("		var decimalsize=4;");
			out.println("	  if (!isNaN(passed_decimalsize)) {");
			out.println("	    decimalsize=passed_decimalsize;");
      out.println("    }");
			out.println("		m_format=value.toString();");
			out.println("		m_dot_count=0;");
			out.println("		var m_integer='';");
			out.println("		var m_decimal='';");
			out.println("		m_minus=false;");
			out.println("		for (var i = 0; i < m_format.length; i++) ");
			out.println("			{");
			out.println("	  		var oneChar = m_format.charAt(i)");
			out.println("	 			if (oneChar== '.') {");
			out.println("					 m_dot_count=m_dot_count+1;");
			out.println("				}");
			out.println("				if (oneChar== '-') {");
			out.println("		 			 m_minus=true;");
			out.println("				}");
			out.println("			  if (isNaN(oneChar) && oneChar != ',' && oneChar != '.' && oneChar != '-') {");
			out.println("						alert('You have typed an incorrect charactor as a number');");
			out.println("						return oneChar");
			out.println("				}");
			out.println("				if (!isNaN(oneChar) && m_dot_count==0 ) {");
			out.println("						m_integer=m_integer+oneChar;");
			out.println("				}");
			out.println("				if (m_dot_count>0 && oneChar!= '.') {");
			out.println("						m_decimal=m_decimal+oneChar;");
			out.println("				}");
			out.println("		}");
			out.println("		if (m_dot_count>1) {");
			out.println("				alert('You have typed more than one decimal separator');");
			out.println("				return false");
			out.println("		}");
			out.println("		m_format=m_integer; ");
			out.println("		var m_formatted='';");
			out.println("		var m_new_str='';");
			out.println("		m_end=m_format.length;");
			out.println("		m_stat_pos=m_end-4;");
			out.println("		var m_last_pos=m_end;");
			out.println("		while (m_stat_pos>=0)	{");
			out.println("					m_chk_str=m_format.substr(m_stat_pos,1);");
			out.println("					if  (m_chk_str!=null)	{");
			out.println("							m_add_str=','+m_format.substr(m_stat_pos+1,3);");
			out.println("							m_new_str=m_add_str+m_new_str;");
			out.println("					}");
			out.println("					m_last_pos=m_stat_pos;");
			out.println("					m_stat_pos=m_stat_pos-3;");
			out.println("		}      ");
			out.println("  		if (m_decimal.length>decimalsize) {");
			out.println("	  		 m_decimal=m_decimal.substr(0,decimalsize);");
      out.println("    } else {					");
		  out.println("         i=1;");
			out.println("				 bal_length=decimalsize-m_decimal.length;");
			out.println("				 while (i<=bal_length) {");
			out.println("					 m_decimal=m_decimal+'0';");
			out.println("					 i=i+1;");
			out.println("				 }");
			out.println("		}");
      out.println("    m_decimal='.'+m_decimal;");

			out.println("		m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;");
	      	
			out.println("		if (m_minus) {");
			out.println("			 m_new_str='-'+m_new_str;");
	  	out.println("    }");
			out.println("		value=m_new_str;");
			out.println("		return value;");
			out.println("	}");
				
		
			out.println("function check_meter() {");
			out.println("{");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_METER_READING.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
						
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Meter Reading is wrong....!');");
			out.println("document.Form1.TXT_METER_READING.value='';");
		 	out.println("document.Form1.TXT_METER_READING.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function check_seat() {");
			out.println("{");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_SEATING_CAPACITY.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
						
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Seating Capacity is wrong....!');");
			out.println("document.Form1.TXT_SEATING_CAPACITY.value='';");
		 	out.println("document.Form1.TXT_SEATING_CAPACITY.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function check_cyl() {");
			out.println("{");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_NO_OF_CYLINDERS.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
						
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered No of Cylinders is wrong....!');");
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.value='';");
		 	out.println("document.Form1.TXT_NO_OF_CYLINDERS.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			
			out.println("function seperate(){");
			out.println("var inputStr;");
			out.println("var inputStr1;");
			out.println("document.Form1.hid_day.value=document.Form1.TXT_VALUATION_DATE.value.substring(0,2)");
			out.println("document.Form1.hid_month.value=document.Form1.TXT_VALUATION_DATE.value.substring(3,5)");
			out.println("document.Form1.hid_year.value=document.Form1.TXT_VALUATION_DATE.value.substring(6,10)");
			out.println("valno	=    document.Form1.hid_day.value;"); 
			out.println("valno1	=    document.Form1.hid_month.value;"); 
			out.println("valno2	=    document.Form1.hid_year.value;"); 
			out.println("val	=    document.Form1.TXT_VALUATION_DATE.value;"); 
			//out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("m_size1=valno1.length;");
			out.println("m_size2=valno2.length;");
			out.println("m_date=val.length;");
			
			//out.println("for (var j=0 ; j<=m_date ;j++){");
			//out.println("inputStr1 = val.charAt(j);");
			out.println("for (var i=0 ; i<=m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Day is wrong....!');");
			out.println("document.Form1.TXT_VALUATION_DATE.value='';");
		 	out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			
			out.println("for (var i=0 ; i<=m_size1 ;i++)");
			out.println("{");
			out.println("inputStr = valno1.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Month is wrong....!');");
			out.println("document.Form1.TXT_VALUATION_DATE.value='';");
		 	out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			out.println("return false;");
			out.println("i=m_size1;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			
			out.println("for (var i=0 ; i<=m_size2 ;i++)");
			out.println("{");
			out.println("inputStr = valno2.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Year is wrong....!');");
			out.println("document.Form1.TXT_VALUATION_DATE.value='';");
		 	out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			out.println("return false;");
			out.println("i=m_size2;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			//out.println("if(document.Form1.TXT_VALUATION_DATE.value==''){;");
			//out.println("alert('Entered VALUATION DATE....!');");
			///out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("return false;");
			//out.println("break;	");
			//out.println("}");
			//out.println("}");
			//out.println("alert(m_dd)");
			//out.println("checkMonthLength()");
			//out.println("document.Form1.TXT_VALUATION_DATE.value=from_date");
			
			out.println("}");
			
			out.println("function seperate1(){");
			out.println("var inputStr;");
			out.println("var inputStr1;");
			out.println("document.Form1.hid_day.value=document.Form1.TXT_DATE_OF_REG.value.substring(0,2)");
			out.println("document.Form1.hid_month.value=document.Form1.TXT_DATE_OF_REG.value.substring(3,5)");
			out.println("document.Form1.hid_year.value=document.Form1.TXT_DATE_OF_REG.value.substring(6,10)");
			out.println("valno	=    document.Form1.hid_day.value;"); 
			out.println("valno1	=    document.Form1.hid_month.value;"); 
			out.println("valno2	=    document.Form1.hid_year.value;"); 
			out.println("val	=    document.Form1.TXT_DATE_OF_REG.value;"); 
			//out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("m_size1=valno1.length;");
			out.println("m_size2=valno2.length;");
			out.println("m_date=val.length;");
			
			//out.println("for (var j=0 ; j<=m_date ;j++){");
			//out.println("inputStr1 = val.charAt(j);");
			out.println("for (var i=0 ; i<=m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Day is wrong....!');");
			out.println("document.Form1.TXT_DATE_OF_REG.value='';");
		 	out.println("document.Form1.TXT_DATE_OF_REG.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			
			out.println("for (var i=0 ; i<=m_size1 ;i++)");
			out.println("{");
			out.println("inputStr = valno1.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Month is wrong....!');");
			out.println("document.Form1.TXT_DATE_OF_REG.value='';");
		 	out.println("document.Form1.TXT_DATE_OF_REG.focus();");
			out.println("return false;");
			out.println("i=m_size1;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			
			out.println("for (var i=0 ; i<=m_size2 ;i++)");
			out.println("{");
			out.println("inputStr = valno2.charAt(i);");
						
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Year is wrong....!');");
			out.println("document.Form1.TXT_DATE_OF_REG.value='';");
		 	out.println("document.Form1.TXT_DATE_OF_REG.focus();");
			out.println("return false;");
			out.println("i=m_size2;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			//out.println("if(document.Form1.TXT_VALUATION_DATE.value==''){;");
			//out.println("alert('Entered VALUATION DATE....!');");
			///out.println("document.Form1.TXT_VALUATION_DATE.focus();");
			//out.println("return false;");
			//out.println("break;	");
			//out.println("}");
			//out.println("}");
			//out.println("alert(m_dd)");
			//out.println("checkMonthLength()");
			//out.println("document.Form1.TXT_VALUATION_DATE.value=from_date");
			
			out.println("}");
			
//###############################################################################################################################################################################
//#######(TO VIEW REPORT)############################################################################################################################################################
			
			out.println("function view_report(){	"); //To view report
			
			out.println("    m_sql = \"m_view_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("m_criteria=document.Form1.TXT_SUB_MODEL_CODE.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@Y@\";");

			out.println("    view_report1('1','10','0');"); 
			out.println("}"); 

		
			out.println("function view_report1(Start,End,Hid_No,Max) {"); 
			out.println("vend=parseInt(End)+10");
			out.println("vend1=parseInt(End)+1");
			
			//out.println("alert('A'+vend1+'B'+vend)");
			out.println("vpre=parseInt(Start)-10");
			out.println("vpre1=parseInt(Start)-1");
			//out.println("alert('vpre1'+vpre1+'vpre'+vpre)");
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_Report_Servlet?class_in=\"+client_name+\"AF_MK_Report_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No,oBj,\"dialogWidth:50em; dialogHeight:20em; bottom:yes; status:no; right:yes;\");"); 
				//	\"left=0,top=133,dialogWidth=1000,dialogHeight=500,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1\");");
			out.println("	"); 
			/*out.println("window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_Report_Servlet?class_in=\"+client_name+\"AF_MK_Report_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No);"); */
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			/*out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 

			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}");*/ 

			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			
			//out.println("		Next1(vend1,vend,Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	");
			out.println("		Prev1(oBj.valout[2],oBj.valout[3],Hid_No);"); 

			//out.println("	Prev1(vpre,vpre1,Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			/*out.println("		Next1(vend1,vend,Hid_No);"); 
			//out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 

			
			out.println("	else{	"); 
			out.println("	alert('hhhhhhhhh');"); 
			out.println("	Prev1(vpre1,vpre,Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); */
			
			/*out.println("function Next1(vend1,vend,Hid_No) {");
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"Report_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+vend1+"); 
			out.println("    \"&End_in=\"+vend+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No,oBj,\"dialogWidth:1000em; dialogHeight:500em;\");"); 
			out.println(" Selected(oBj.valout,'1','2',vpre,vpre1);");
			out.println("		return false;"); 
			out.println("	} "); 
		
 			out.println("function Prev1(vpre1,vpre,Hid_No) {");
      //int Prev_Val  =Integer.parseInt(Start_Val)-10;
      //int new_pre_Val = Integer.parseInt(Start_Val)-1;
      //out.println("oBj= new MyDialog()");
			out.println("	alert('rtutughgh');"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"Report_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+vpre1+"); 
			out.println("    \"&End_in=\"+vpre+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No,oBj,\"dialogWidth:1000em; dialogHeight:500em;\");"); 
out.println("		return false;"); 
			
			
      //out.println(" Selected(oBj.valout,'1','2',vpre,vpre1);");
      out.println("}");*/
			
			out.println("function Prev1(Start,End,Hid_No){"); 
			out.println("    view_report1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function Next1(Start,End,Hid_No){"); 
			out.println("    view_report1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			    
//###############################################################################################################################################################################
//###############################################################################################################################################################################
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"init(),load_lock(),load_roll_value('New'),application_no()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); //To seperatly identify m_url's.
			out.println("<INPUT TYPE='Hidden' NAME='hid_field' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_v'>");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_act'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_st'>");//To seperatly identify m_url's.
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_x'>");//To seperatly identify m_url's.
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_day'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_month'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_year'>");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no'>");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>NetAsset - Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration -Inspection and Valuation Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DACT\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='view_report()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='6%'></td>");  
			  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<table align='center' width='100%' class='table'>"); 


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APP_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APP_NO' maxlength='15' size='15' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUATION_NO'  class=div_input>Valuation Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUATION_NO' maxlength='15' size='15' onkeypress=\"assig1('T1')\" onblur=\"assig1('T1')\" onchange=\"makeRequest(document.Form1.TXT_VALUATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_VALUATION_NO' value=\"Help\" onClick=\"help_update(),assig1('T1')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			
			out.println("</tr>");

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Asset Id *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='15' size='15' onkeypress=\"d(),assig1('T4')\" onblur=\"assig1('T4')\" onchange=\"asset(document.Form1.TXT_ASSET_ID)\">");
			//out.println("<input class='but_input' type='button' name='BUT_VALUATION_NO' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
		
					
			out.println("<td width='30%' >Valuation Date [DD-MM-YYYY]*</td>"); 
			//out.println("<td width='40%' ><input type='text' name='TXT_VALUATION_DD' maxlength='2' size='2' onkeypress='d(),combine()'><input ");
			//out.println("type='text' name='TXT_VALUATION_MM' maxlength='2' size='2' onkeypress='d(),combine()'><input ");
			//out.println("type='text' name='TXT_VALUATION_YYYY' maxlength='4' size='4' onkeypress='d(),combine()'>");
			out.println("</td>"); 

			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALUATION_DATE' maxlength='10' size='10' onkeypress='d()' onblur=\"seperate()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr>");
			out.println("<td width='30%' >Item Category Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_CODE' maxlength='10' size='10' onblur=\"item_val(document.Form1.TXT_ITEM_CODE),assig1('T2'),makeRequest1()\" onkeypress=\"d(),assig1('T2')\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ITEM_CODE' value=\"Help\" onClick=\"help_button_7(),assig1('T2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>");
			out.println("<td width='30%' >Model Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MODEL_CODE' maxlength='10' size='10' onblur=\"assig1('T3'),dd(document.Form1.TXT_MODEL_CODE.value)\" onkeypress=\"d(),assig1('T3'),check()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MODEL_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_MODEL_CODE'  class=div_input>Sub Model Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_CODE' maxlength='10' size='10' onkeypress=\"assig1('T5'),d(),check_sub()\" onblur=\"assig1('T5'),sub(document.Form1.TXT_SUB_MODEL_CODE.value)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SUB_MODEL_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REG_NO'  class=div_input>Registration No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			//out.println("</tr>");
			
			//out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_COLOUR'  class=div_input>Colour *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLOUR' maxlength='10' size='10' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engin No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='20' size='20' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input>Chassis No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='20' size='20' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' >Notes *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NOTES' maxlength='100' size='100' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Remarks *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REMARKS' maxlength='100' size='100' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Type of the Body</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TYPE_OF_BODY' maxlength='50' size='50' onkeypress=\"d()\"></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_TYPE_OF_BODY' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Date of Registration [DD-MM-YYYY]*</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATE_OF_REG' maxlength='10' size='10' onkeypress='d()' onblur=\"seperate1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >General Index(Engineers Summery)</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_GENERAL_INDEX' maxlength='1' size='1' onchange='d()'>");  
			out.println("<option value='1' selected>Faultless</option>");			
			out.println("<option value='2' >Good</option>");	
			out.println("<option value='3' >Serviceable</option>");	
			out.println("<option value='4' >Fair</option>");	
			out.println("<option value='5' >Poor</option>");
			out.println("<option value='6' >Unserviceable</option>");		
			out.println("</select>");
			out.println("</td>");

			
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Value</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='22' size='22' onkeypress='d()' onblur=\"check_value(document.Form1.TXT_VALUE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' >Odometer Reading *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_METER_READING' maxlength='22' size='22' onkeypress='d()' onblur=\"check_meter()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Seating Capacity</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SEATING_CAPACITY' maxlength='2' size='2' onkeypress='d()' onblur=\"check_seat()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >No of Cylinders</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_CYLINDERS' maxlength='1' size='' onkeypress='d()' onblur=\"check_cyl()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			out.println("<tr>");
			out.println("<td width='30%' >Fual Type</td>");
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_FUAL_TYPE' maxlength='10' size='1' onchange='d()'>");  

			String m_val_no1=(String)m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE"); 
	
			rs=stmt.executeQuery("SELECT A.CODE,A.DESCRIPTION "+
													//	"FROM " + m_fschema_name + ".AF_CO_MAS_FUEL_TYPE A," + m_fschema_name + ".AF_CO_MAS_MODEL B "+
														"FROM LAKDL.AF_CO_MAS_FUEL_TYPE A,LAKDL.AF_CO_MAS_MODEL B "+
														"WHERE B.FUEL_TYPE=A.CODE "+
														"AND B.MODEL_CODE LIKE UPPER('"+m_val_no1+"%')"+
														"AND B.ACTIVE_STATUS='Y'");
		
	
			boolean more=rs.next();
			while(more){
			out.println("<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");			
			more=rs.next();
						}
					
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
			/*out.println("<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">");
			out.println("<tr >");
			out.println("<td>Product Code *</td>");
			out.println("<td >Product Description *</td>"); 
			out.println("<td>Status *</td>"); 
			out.println("<td >Remarks *</td>"); 
			out.println("</tr >"); 
			out.println("</table >"); */

		
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			out.println("<td width='30%' ><input class='but_input' type='hidden' name='BUT_FILED_CODE' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td><input class='txt_input' type='hidden' name='TXT_FILED_CODE' maxlength='10' size='10'></td>"); 
			out.println("</tr>");
			out.println("</table>");

			
			out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			
				
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			//}
			
			out.flush();
		}
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
