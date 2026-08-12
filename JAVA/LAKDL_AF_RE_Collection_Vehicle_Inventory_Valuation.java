//--
//SCREEN NAME:COLELCTION PROCESS -INSPECTION AND VALUATION_REPORT
//CREATED BY:NUWAN
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Vehicle_Inventory_Valuation extends javax.servlet.http.HttpServlet { 

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
			String m_client_name=m_sn_methods.client_name;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			m_val_code1=req.getParameter("hid_value");
			String m_valuation="";
			String m_my_screen="";
			m_my_screen =  req.getParameter("my_screen_name");
			String m_inv_no="";
			String m_vehi_no="";
			String m_chas_no="";
			String m_engine_no="";
			//String m_inv_no =  req.getParameter("inv_no");
			
			if(req.getParameter("inv_no")!=null){
			m_inv_no =  req.getParameter("inv_no");
			}
						if(req.getParameter("vehicle_no")!=null){
			m_vehi_no =  req.getParameter("vehicle_no");
			}
						if(req.getParameter("chassis_no")!=null){
			m_chas_no =  req.getParameter("chassis_no");
			}
						if(req.getParameter("engine_no")!=null){
			m_engine_no =  req.getParameter("engine_no");
			}
			
			//String m_inv_no =  req.getParameter("inv_no");
			//String m_vehi_no =  req.getParameter("vehicle_no");
			//String m_chas_no =  req.getParameter("chassis_no");
			//String m_engine_no =  req.getParameter("engine_no");
			
		String m_schema_name = m_sn_methods.schema_name;

			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Application Process -Inspection and Valuation Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
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
	
			out.println("function get_vector(data_vec) {");
			//out.println("alert('document.Form1.hid_st.value'+document.Form1.hid_st.value);");
			out.println("			if(data_vec.length >0 && document.Form1.hid_st.value=='M1' ){");
			//out.println("alert('test2');");
			out.println("     display_data_inventory(data_vec);");
			out.println("			}");//To Check new valuation code exists.
			
			out.println("		else if(data_vec.length >0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value=='T1' ){");
			out.println("				alert('Record already exists.');");
			out.println("				new_window();");
			out.println("			}");//To Check new valuation code exists.
			out.println(" else if(document.Form1.hid_x.value=='8' && data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T1'){");
			out.println("    help_update();"); 
			out.println("    document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("}");//To Check valuation code correct.
			
			
			out.println(" else if (data_vec.length ==0 && document.Form1.hid_st.value=='M7' && document.Form1.TXT_CHASSIS_NO.value!=\"\" ){");
			out.println("       alert('Invalid Chassis Number.'); "); 
			out.println("       document.Form1.TXT_CHASSIS_NO.value=\"\" "); 
			out.println("       document.Form1.TXT_CHASSIS_NO.focus();  "); 
			out.println("}");
			
			out.println(" else if (data_vec.length ==0 && document.Form1.hid_st.value=='M8' && document.Form1.TXT_ENGINE_NO.value!=\"\"){");
			out.println("       alert('Invalid Engine Number'); "); 
			out.println("       document.Form1.TXT_ENGINE_NO.value=\"\" "); 
			out.println("       document.Form1.TXT_ENGINE_NO.focus();  "); 
			
			out.println("}");
			
			out.println(" else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T1'){");
		
		 	out.println("    document.Form1.TXT_ASSET_ID.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=data_vec[2];");
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_REG_NO.value=data_vec[4];"); 
			//out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[5];"); 
			
			out.println("    if(data_vec[5]!= 'null'){ "); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[5];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value='';");
			out.println("    } "); 
			
			out.println("    if(data_vec[6]!= 'null'){ "); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[6];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value='';");
			out.println("    } "); 
			
		//	out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[6];"); 
			
			out.println("    if(data_vec[7]!='null') { "); 
			out.println("     document.Form1.TXT_NOTES.value=data_vec[7];"); 
			out.println("    }"); 
			out.println("    if(data_vec[8]!='null') { "); 
			out.println("    document.Form1.TXT_REMARKS.value=data_vec[8];");
			out.println("    }"); 
			out.println("    if(data_vec[9]!='null') { "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=data_vec[9];"); 
			out.println("    }"); 
			out.println("    if(data_vec[10] != '-') {"); 
			out.println("     getDateValues(data_vec[10]);"); 
			out.println("    }"); 
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(data_vec[11]) );"); 
			out.println("    if(data_vec[12] == 'null') {");
			out.println("data_vec[12]=''"); 
			out.println(" }");
			out.println("    document.Form1.TXT_METER_READING.value=data_vec[12];"); 
			out.println("    if(data_vec[13] != 'null') {"); 
			out.println("     getDateValues_valdate(data_vec[13]);"); 
			out.println("    }"); 
			
			//out.println("    document.Form1.TXT_COLOUR.value=data_vec[14];");
			
			out.println("    if(data_vec[14]!= 'null'){ "); 
			out.println("    document.Form1.TXT_COLOUR.value=data_vec[14];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_COLOUR.value='';");
			out.println("    } "); 
			
			out.println("    if(data_vec[19]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=data_vec[19];");
			out.println("    }"); 
			out.println("    if(data_vec[20]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=data_vec[20];");
			out.println("    }"); 
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=data_vec[18];");			
			out.println("    document.Form1.TXT_FUAL_TYPE.value=data_vec[21];");			
			out.println("    document.Form1.TXT_ITEM_CODE.value=data_vec[22];");		
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[23];");		
			
			out.println("    if(data_vec[24]!= 'null' || data_vec[24]!= '-'){ "); 
			out.println("    document.Form1.TXT_VALUER_CODE.value=data_vec[24];");	
			out.println("    }"); 
			out.println("    else {"); 
			out.println("    document.Form1.TXT_VALUER_CODE.value='';");	
			out.println("    }"); 
			
			out.println("    if(data_vec[29]!= 'null' || data_vec[29]!= '-'){ "); 
			out.println("    document.Form1.TXT_VALUER_NAME.value=data_vec[29];");	//added by nuwan de silva 17-07-07
			out.println("    }"); 
			out.println("    else {"); 
			out.println("    document.Form1.TXT_VALUER_NAME.value='';");	//added by nuwan de silva 17-07-07
			out.println("    }"); 
			
			
			
			out.println("if(data_vec[25]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[25];");
			out.println("}"); 
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=data_vec[26];");
			out.println("    document.Form1.TXT_FORCED_VALUE.value=data_vec[27];"); 
			out.println("    document.Form1.TXT_APP_NO.value=data_vec[28];"); 
			
			
			out.println("    assig1('T2');");	
			out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			out.println("}");//To Check valuation code correct.
			
			
			
			out.println(" else if(data_vec.length==0 && document.Form1.hid_x.value=='5' && document.Form1.TXT_INVENTORY_NO.value!='' &&  document.Form1.hid_st.value=='T4'){");
			out.println("help_button_inventory(); ");
			out.println("data_vec='';");
			out.println("}"); //To Check asset code is correct.
			out.println(" else if(data_vec.length==0 && document.Form1.hid_x.value=='6' && document.Form1.TXT_ITEM_CODE.value!='' &&  document.Form1.hid_st.value=='T2'){");
			out.println("help_button_7();");	
			out.println("document.Form1.TXT_ITEM_CODE.focus();");	
			out.println("data_vec='';");
			out.println("}"); //To Check item code is correct.
			out.println(" else if(data_vec.length > 0 && document.Form1.hid_x.value=='6' && document.Form1.TXT_ITEM_CODE.value!='' &&  document.Form1.hid_st.value=='T2'){");
			out.println("assig1('T6'); ");
			out.println("makeRequest1();");	
			out.println("data_vec='';");
			out.println("}"); //To populate the product data
			out.println(" else if(data_vec.length==0 && document.Form1.hid_x.value=='7' && document.Form1.TXT_MODEL_CODE.value!='' &&  document.Form1.hid_st.value=='T3'){");
			out.println("data_vec='';");
			out.println("}");//To Check model code is correct.
			out.println(" else if (document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value=='T6'){");
			out.println("display_data(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when valuation is selected.
			out.println(" else if (document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value=='T3'){");
			out.println("display_data(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when valuation code is selected.
			out.println(" else if (data_vec.length > 0 && document.Form1.hid_val.value=='1' && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T6'){");
			out.println("display_fields(data_vec)");
			out.println("data_vec=''");
			out.println("}");//To display field details when an item code is selected.
			out.println(" else if (document.Form1.hid_val.value=='2' && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T3'){");
			out.println("use=data_vec[0]");
			out.println("m_code=document.Form1.TXT_MODEL_CODE.value;");
			out.println("data_vec=''");
			out.println("}");//To find model code when an item is selected.
			out.println(" else if(document.Form1.hid_st.value=='T7'){");
			out.println("document.Form1.TXT_ITEM_CODE.value=data_vec[3];");
			out.println("}");//To find item code.
			out.println(" else if(document.Form1.hid_st.value=='T8'){");
			out.println("document.Form1.TXT_FUAL_TYPE.value=data_vec[0];");
			out.println("data_vec=''");
			out.println("}");//To find fuel type when an model is selected.
			out.println(" else if(data_vec.length > 0 && document.Form1.hid_x.value=='5' && document.Form1.hid_st.value=='T4' ){");
			out.println("document.Form1.TXT_INVENTORY_NO.value=data_vec[0];");
			out.println("get_data();");
			out.println("}");//To validate the asset code and fill.
			out.println(" else if(data_vec.length > 0 && document.Form1.hid_x.value=='9' && document.Form1.hid_st.value=='T9' ){");
			out.println("var m_date_diff=data_vec[0];");
			out.println("if(m_date_diff < 0){ ");
			out.println("  alert('Valuation date cannot be greater than the system date.');");
			out.println("  document.Form1.TXT_VALUATION_DATE_DD.value='';");
			out.println("  document.Form1.TXT_VALUATION_DATE_MM.value='';");
			out.println("  document.Form1.TXT_VALUATION_DATE_YY.value='';");
			out.println("  document.Form1.HID_TXT_VALUATION_DATE.value='';");
			out.println("}");
			out.println("data_vec=''");
			out.println("}");//To validate the asset code and fill.
			out.println(" else if(data_vec.length > 0 && document.Form1.hid_st.value=='E1' ){");
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=data_vec[2];");
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_REG_NO.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[6];"); 
			out.println("    if(data_vec[7]!='null') { "); 
			out.println("     document.Form1.TXT_NOTES.value=data_vec[7];"); 
			out.println("    }"); 
			out.println("    if(data_vec[8]!='null') { "); 
			out.println("    document.Form1.TXT_REMARKS.value=data_vec[8];");
			out.println("    }"); 
			out.println("    if(data_vec[9]!='null') { "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=data_vec[9];"); 
			out.println("    }"); 
			out.println("    if(data_vec[10] != '-') {"); 
			out.println("     getDateValues(data_vec[10]);"); 
			out.println("    }"); 
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(data_vec[11]) );"); 
			out.println("    if(data_vec[12] == 'null') {");
			out.println("data_vec[12]=''"); 
			out.println(" }");
			out.println("    document.Form1.TXT_METER_READING.value=data_vec[12];"); 
			
			out.println("    if(data_vec[13] != 'null') {"); 
			out.println("     getDateValues_valdate(data_vec[13]);"); 
			out.println("    }"); 
			
			
			out.println("    if(data_vec[14]!= 'null'){ "); 
			out.println("    document.Form1.TXT_COLOUR.value=data_vec[14];");
			out.println("    }"); 
			
			out.println("    if(data_vec[19]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=data_vec[19];");
			out.println("    }"); 
			
			out.println("    if(data_vec[20]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=data_vec[20];");
			out.println("    }"); 
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=data_vec[18];");			
			out.println("    document.Form1.TXT_FUAL_TYPE.value=data_vec[21];");			
			out.println("    document.Form1.TXT_ITEM_CODE.value=data_vec[22];");		
			out.println("    assig1('T2');");	
			out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			
			out.println("}");//To Check valuation code correct.
			out.println(" else if (data_vec.length ==0 && document.Form1.hid_st.value=='VAL' && document.Form1.TXT_VALUER_CODE.value!=\"\" ){");
			out.println("       help_update_valuer();"); 
			out.println("}");
			out.println(" else if (data_vec.length >0 && document.Form1.hid_st.value=='VAL' && document.Form1.TXT_VALUER_CODE.value!=\"\"){");
			out.println("       document.Form1.TXT_VALUER_CODE.value=data_vec[0] "); 
			out.println("       document.Form1.TXT_VALUER_NAME.value=data_vec[1];"); 

			out.println("}");
			
			
			out.println("}");
			
			
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");//To seperatly identify m_url's.
			out.println("}");
			
			out.println("function display_data_inventory(data_vec) {");			
			out.println(" document.Form1.TXT_APP_NO.value=data_vec[0];"); 
			out.println(" document.Form1.TXT_ASSET_ID.value=data_vec[1];");
			out.println(" document.Form1.TXT_ITEM_CODE.value=data_vec[2];");
	    out.println(" document.Form1.TXT_MODEL_CODE.value=data_vec[3];"); 
	    out.println(" document.Form1.TXT_SUB_MODEL_CODE.value=data_vec[4];"); 
			out.println(" document.Form1.TXT_FUAL_TYPE.value=data_vec[5];");
			out.println(" document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[6];");
			
			out.println("assig1('T2');");	
			out.println("item_val(document.Form1.TXT_ITEM_CODE)");	
			//out.println("load_lock()"); //addded by nuwan de silva on 07-11-07
			
			out.println("}");
			
			out.println("function close_screen() {");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("}");
			
			out.println("function assig1(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DEL\")){");
			out.println("document.Form1.hid_act.value='Y';");//To seperatly identify active status accordingly.
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_act.value='N';");
			out.println("}");
			out.println("}");

			out.println("function asset(obj) {");//To get asset details for validation.
			out.println("document.Form1.hid_st.value='T4';");
			out.println("document.Form1.hid_x.value='5';");
			out.println("if(document.Form1.hid_st.value=='T4' && (document.Form1.SCREEN_NAME.value!=\"DEL\")){;");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Inv_no&data_val=\"+obj.value+\"&ac_status=ENT\" ;");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_asset_details&data_val=\"+obj.value ;");
			out.println("load_interface(m_url,'XML');");
			
	//		out.println("window.open(m_url);");
			out.println("}");
			out.println("}");
			
			out.println("function model(obj) {");//To get model details for validation.(not used)
			out.println("document.Form1.hid_x.value='';");
			out.println("if(document.Form1.hid_st.value=='T3'){;");
			out.println("document.Form1.hid_x.value='7';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			
			out.println("function item_val(obj){");//To get item details for validation.
			out.println("document.Form1.hid_x.value='';");
			out.println("if(document.Form1.hid_st.value=='T2'){;");
			out.println("document.Form1.hid_x.value='6';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url)");
			out.println("}");
			out.println("}");
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_REG_DATE_DD.value=dval.substring(0,2);");
			out.println("document.Form1.TXT_REG_DATE_MM.value=dval.substring(3,5);");
			out.println("document.Form1.TXT_REG_DATE_YY.value=dval.substring(6,10);");
			out.println("document.Form1.HID_TXT_REG_DATE.value=dval;");
			out.println("}");
			
			out.println("function getDateValues_valdate(dval){");
			out.println("document.Form1.TXT_VALUATION_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_VALUATION_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_VALUATION_DATE_YY.value=dval.substring(6,10)");
			out.println("document.Form1.HID_TXT_VALUATION_DATE.value=dval;");
			out.println("}");
			
			out.println("function check_valuation_date(){ ");
			out.println(" if((document.Form1.TXT_VALUATION_DATE_DD.value !=\"\")&&(document.Form1.TXT_VALUATION_DATE_MM.value !=\"\")&&(document.Form1.TXT_VALUATION_DATE_YY.value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.TXT_VALUATION_DATE_DD,document.Form1.TXT_VALUATION_DATE_MM,document.Form1.TXT_VALUATION_DATE_YY);");
			out.println("  document.Form1.HID_TXT_VALUATION_DATE.value=document.Form1.TXT_VALUATION_DATE_DD.value+'-'+document.Form1.TXT_VALUATION_DATE_MM.value+'-'+document.Form1.TXT_VALUATION_DATE_YY.value");
			out.println("  document.Form1.hid_st.value='T9';");
			out.println("  document.Form1.hid_x.value='9';");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=LAKDL_AF_MK_display_inspection_and_valuation_report_chk_valudate&data_val=\"+document.Form1.HID_TXT_VALUATION_DATE.value;");
			out.println("  load_interface(m_url,'XML');");
			out.println(" }");
			out.println(" else {");
			out.println("  alert('Valuation date is invalid.');");
			out.println("  document.Form1.TXT_VALUATION_DATE_DD.value='' ;");
			out.println("  document.Form1.TXT_VALUATION_DATE_MM.value='' ;");
			out.println("  document.Form1.TXT_VALUATION_DATE_YY.value='' ;");
			out.println(" }");
			out.println("}");
			
			out.println("function check_regi_date(){ ");
			out.println(" if((document.Form1.TXT_REG_DATE_DD.value !=\"\")&&(document.Form1.TXT_REG_DATE_MM.value !=\"\")&&(document.Form1.TXT_REG_DATE_YY.value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY);");
			out.println(" }");
			out.println("document.Form1.HID_TXT_REG_DATE.value=document.Form1.TXT_REG_DATE_DD.value+'-'+document.Form1.TXT_REG_DATE_MM.value+'-'+document.Form1.TXT_REG_DATE_YY.value ;");
			out.println("}");
			

			out.println("function makeRequest(obj) {");//To get valuation details for validation.
			out.println("");
			out.println("document.Form1.hid_x.value='8';");
			out.println("if(document.Form1.hid_st.value=='T1'){;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			out.println("}");

			out.println("function makeRequest1() {");//To find fields accordin to an item.
			out.println("document.Form1.hid_val.value='1';");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value=='T6'){");//Only fields that exists for item code.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspect2&data_val=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("}");
			out.println("else if( document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_st.value=='T6'){");//To show fields that are already entered in valuation and all other fields added after.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspect4&data_val=\"+document.Form1.TXT_VALUATION_NO.value+\"&data_val1=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function assig_item() {");//To find item code.
			out.println("document.Form1.hid_st.value='T7' ;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspect3&data_val=\"+document.Form1.TXT_MODEL_CODE.value;");
			out.println("document.Form1.TXT_ITEM_CODE.focus();");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function fuel_type() {");//To find fuel type according to model.
			out.println("document.Form1.hid_st.value='T8';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_fual2&data_val=\"+document.Form1.TXT_MODEL_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

			out.println("function header(){");//To set header
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"18%\" ><B>Product Code*</B></td>'+");
			out.println("'<td width=\"29%\" ><B>Product Description *</B></td>'+"); 
			out.println("'<td width=\"17%\" ><B>Status *</B></td>'+"); 
			out.println("'<td width=\"20%\" ><B>Remarks *</B></td>'+"); 
			out.println("'<td width=\"*%\" ><B></B></td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table >';");
			out.println("}");
			
			
			out.println("function display_data(data_vec){");//To fill data for field codes(new record).
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("if(document.Form1.TXT_ITEM_CODE.value!=''){");
			out.println("if(data_vec.length==0){");
			out.println("header()");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\" disabled></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\"  name=TXT_FILED_DESC_'+j+' maxlength=\"50\" style=\"width:200px\" value=\"\" size=\"20\" disabled></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" style=\"width:250px\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\" ></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
		  out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("header()");
			out.println("while(i<data_vec.length){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\" disabled></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\"  name=TXT_FILED_DESC_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"50\" style=\"width:200px\" value=\"\" size=\"20\" disabled></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+j+' value=\"\" maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+j+' value=\"\" maxlength=\"100\" style=\"width:250px\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\" ></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("j=j+1;");
			out.println("i=i+2;");
			out.println("}"); 
			out.println("}"); 
			out.println("document.Form1.hid_no.value=j-1");	
			out.println("}");
			out.println("else {");
			out.println("change1.innerHTML=\"\";");
			out.println("}");
			
			
	

      out.println("}"); 

			
			out.println("function display_fields(data_vec){");//To fill data for field codes(already entered record).
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("if(document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.TXT_ITEM_CODE.value!=''){");
			out.println("if(data_vec.length==0){");
			out.println("header()");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" name=TXT_FILED_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\" disabled></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" name=TXT_FILED_DESC_'+j+' maxlength=\"50\" style=\"width:200px\" value=\"\" size=\"20\" disabled></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" style=\"width:200px\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\"  ></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
		  out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("header()");
			out.println("while(i<data_vec.length){");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"10%\"  ><input class=\"txt_input\" name=TXT_FILED_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\" disabled></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"txt_input\" name=TXT_FILED_DESC_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"50\" style=\"width:200px\" value=\"\" maxlength=\"20\" size=\"20\" disabled></td>'+"); 
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"100\" style=\"width:200px\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'<td width=\"*%\"  ></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
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
			out.println("}");
      out.println("}"); 
			
			
			out.println("function XX() {");//To find make code according to item code
			out.println("document.Form1.hid_val.value='2';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspect1&data_val=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			
			out.println("if(document.Form1.TXT_VALUER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VALUER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_INVENTORY_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INVENTORY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		
			out.println("else if(document.Form1.TXT_CHASSIS_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CHASSIS_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_ENGINE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ENGINE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			//out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
			//out.println("DIV_TXT_COLOUR.style.color='red';");
		//	out.println("return false;"); 
		//	out.println("}"); 
			out.println("else if(document.Form1.HID_TXT_VALUATION_DATE.value==\"\"  ){  "); //|| document.Form1.HID_TXT_VALUATION_DATE.value.length<10
			out.println("DIV_TXT_VALUATION_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VALUE.value==\"\" || parseFloat(unformat_number(document.Form1.TXT_VALUE))==0){  "); 
			out.println("DIV_TXT_VALUE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("document.Form1.HID_TXT_VALUATION_DATE.value=document.Form1.TXT_VALUATION_DATE_DD.value+'-'+document.Form1.TXT_VALUATION_DATE_MM.value+'-'+document.Form1.TXT_VALUATION_DATE_YY.value;");
			out.println("m_option = document.Form1.hid_status.value;"); 
			out.println("if(m_option=='New') {");
			out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Edit') {");
			out.println("m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Delete') {");
			out.println("m_sav_msg = 'Are you sure you want to Delete?'; ");
			out.println("		}"); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		 if(validate_data()){");
			out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("      document.Form1.elements[i].disabled=false;");
			out.println("     }");
			out.println("		 document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Vehicle_Inventory_Valuation?number='+ document.Form1.hid_no.value+'&my_screen_name="+m_my_screen+"';");  
			out.println("		 document.Form1.submit();	"); 
			out.println("		  }"); 
			out.println("		 }"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			

			out.println("function load_lock(){	"); 
			out.println("assig1('E1'); ");
			out.println("		document.Form1.TXT_VALUATION_NO.value='"+ m_valuation+"' ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report&data_val=\"+document.Form1.TXT_VALUATION_NO.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}	"); 

			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
		//	out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no="+m_inv_no+"';"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no="+m_inv_no+"&vehicle_no="+m_vehi_no+"&chassis_no="+m_chas_no+"&engine_no="+m_engine_no+"';"); 

			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no="+m_inv_no+"&vehicle_no="+m_vehi_no+"&chassis_no="+m_chas_no+"&engine_no="+m_engine_no+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_inspection_and_valuation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 
			
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
		

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process -Inspection and Valuation Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process -Inspection and Valuation Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function application_no(){");
			out.println("    document.Form1.TXT_APP_NO.disabled=true;"); 
			
			out.println("}"); 
			
	
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_VALUATION_NO.disabled=true;");
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			
			out.println("else if(m_val==\"DEL\"){");
			out.println("document.Form1.TXT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=true;");
			out.println("document.Form1.TXT_ITEM_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NOTES.disabled=true;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.disabled=true;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=true;"); 
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.disabled=true;"); 
			out.println("document.Form1.TXT_GENERAL_INDEX.disabled=true;"); 
			out.println("document.Form1.TXT_FUAL_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE.disabled=true;"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.disabled=true;"); 
			out.println("document.Form1.TXT_METER_READING.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_YY.disabled=true;"); 
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){");
			out.println("document.Form1.TXT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;");
			out.println("document.Form1.TXT_ITEM_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NOTES.disabled=false;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=false;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_DD.disabled=false;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_MM.disabled=false;"); 
			out.println("document.Form1.TXT_VALUATION_DATE_YY.disabled=false;"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;"); 
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.disabled=false;"); 
			out.println("document.Form1.TXT_GENERAL_INDEX.disabled=false;"); 
			out.println("document.Form1.TXT_FUAL_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE.disabled=false;"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.disabled=false;"); 
			out.println("document.Form1.TXT_METER_READING.disabled=false;"); 
			out.println("}"); 				
			out.println("else if(m_val!=\"EDIT\"){");
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;");
			out.println("document.Form1.TXT_ITEM_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_NOTES.disabled=false;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=false;"); 
			out.println("document.Form1.TXT_VALUE.disabled=false;"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.disabled=false;"); 
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
			out.println("}else if(m_val==\"DEL\"){"); 
			out.println("document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("document.Form1.BUT_TXT_INVENTORY_NO.disabled=true");
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_inventory(oBj);"); 
	  	out.println("		}");
						out.println("		if(IfCount==\"8\"){"); 
			out.println("		help_value_assign_valuer();"); 
	  	out.println("		}"); 

			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 			
			out.println(""); 
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 


			out.println("function help_button_inventory() {");//Asset help 
			out.println("    Crit = document.Form1.TXT_INVENTORY_NO.value+\"@ENT@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_INVENTORY_NO_VALUATION_sql','1');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_inventory(oBj) {"); 
	    out.println(" document.Form1.TXT_INVENTORY_NO.value=oBj.valout[2];"); 
			out.println("get_data();");
			out.println("}"); 
			
			out.println("function help_update_valuer() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUER_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_criteria = document.Form1.TXT_VALUER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_VALUER_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'8');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_valuer() {"); 
			out.println("if(oBj.valout[2]=='' || oBj.valout[2]=='null'){"); 
			out.println("oBj.valout[2]=\"\"");
			out.println("}");
			out.println("document.Form1.TXT_VALUER_CODE.value=oBj.valout[2];"); 
			
			out.println("if(oBj.valout[12]=='-' || oBj.valout[12]=='null' || oBj.valout[12]==' '){");  //added by nuwan de silva 18-07-07
			out.println("    document.Form1.TXT_VALUER_NAME.value='';"); 
			out.println("}");
			out.println("else {");
			out.println("    document.Form1.TXT_VALUER_NAME.value=oBj.valout[12];"); //added by nuwan de silva 18-07-07
			out.println("}");
			
			out.println("if(oBj.valout[11]=='' || oBj.valout[11]=='null'){"); 
			out.println("oBj.valout[11]=\"\"");
			out.println("}");
			out.println("    document.Form1.hid_valuer_amount.value=oBj.valout[11];");
			out.println("}");
			
			
			out.println("function Valuer(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_valuer&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			
			out.println("function get_data(){");
			//out.println("alert('test1');");
			out.println("assig1('M1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_valuation&data_val=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=APP\";");	
		//	out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}"); 
			
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
			
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6(y) {"); //feild code help(not used)
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("document.Form1.hid_v.value=y");
			out.println("    m_sql = \"m_help_TXT_FILED_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FILED_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
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

			out.println("function d() {");//used when deleting to stop changing the fields.
			out.println("if(document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
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
			out.println("}");
			
			out.println("function turn(val) {");
			out.println("m_turn=val;");
			out.println("document.Form1.hid_st.value=\"T1\";");
			out.println("}");
			
			out.println("function check_sub() {");//To validate Sub Model code.
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){ ");
			out.println("if(document.Form1.hid_st.value=='T5'){ ");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("alert ('Use Help..!');	");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value='';");
			out.println("}	");	
			out.println("}	");	
			out.println("}	");
			out.println("}");
			
			out.println("function check() {");//To validate Model code.
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){ ");
			out.println("if(document.Form1.hid_st.value=='T3'){ ");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("alert(window.event.keyCode)");
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
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
			
			
			out.println("function help_update() {"); //Valuation code help.
			
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ "); ///Modofied Nuwan De Silva 19-04-2007
			//out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			//out.println("}");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO2_sql\";"); 
		///	out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("}");
						
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"N@\";"); 
			out.println("}");
			
			
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			
			out.println("}");
			
			

			out.println("function help_edit() {"); //To display help when valuation code is entered.
			out.println("");
			out.println("    document.Form1.hid_help_type.value=\"100\";");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    m_sql = \"m_help_TXT_VALUATION_DET_sql\";"); 
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@Y@\";");
			out.println("    } ");
			out.println("    else{");
			out.println("m_criteria=document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@N@\";}");
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'100');"); 
			out.println("}");
			
			
			
			out.println("function help_update_value_assign_99() {");
			
			
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println(" }");
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[10];");
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[6];"); 
			
					
			out.println("    if(oBj.valout[7]!= 'null'){ "); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[7];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value='';");
			out.println("    } "); 
			
			out.println("    if(oBj.valout[8]!= 'null'){ "); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[8];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value='';");
			out.println("    } "); 
		
			
			out.println("    if(oBj.valout[9]!= 'null'){ "); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[9];"); 
			out.println("    } "); 
      out.println("    else {"); 
			out.println("    document.Form1.TXT_COLOUR.value='';");
			out.println("    } "); 
			
			
			out.println("    if(oBj.valout[11]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NOTES.value=oBj.valout[11];"); 
			out.println("    } "); 
			out.println("    if(oBj.valout[12]!= 'null'){ "); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];");
			out.println("    } "); 
			out.println("    if(oBj.valout[13]!= 'null'){ "); 
			out.println("      getDateValues_valdate(oBj.valout[13]);"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(oBj.valout[14]) );"); 
			out.println("    if(oBj.valout[15]!= 'null'){ "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[15];"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[17];"); 
			out.println("    if(oBj.valout[20]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[20];");
			out.println("    } "); 
			out.println("    if(oBj.valout[21]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[21];");
			out.println("    } "); 
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[19]");
			out.println("    if(oBj.valout[16]!= '-'){ "); 
			out.println("     getDateValues(oBj.valout[16]);"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_FUAL_TYPE.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[23];");
			out.println("    assig1('T2');");	
			out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_VALUER_CODE.value=oBj.valout[24];");
			out.println("    document.Form1.hid_valuer_amount.value=oBj.valout[25];");
			
			out.println("if(oBj.valout[26]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
		  out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=oBj.valout[26];");
			out.println("}"); 
			
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=oBj.valout[27];");
				
			out.println("    document.Form1.TXT_FORCED_VALUE.value=format_noobject( Number(oBj.valout[28]) );"); 
			
			out.println("    document.Form1.TXT_APP_NO.value=oBj.valout[29];");
			
			out.println("    document.Form1.TXT_VALUER_NAME.value=oBj.valout[30];");//added by nwuan de silva 18-07-07
			out.println("}"); 
			
		
			
			out.println("}"); 
			
			
			
			
			
			
			
			
			
			
			
			


			/*out.println("function help_update_value_assign_99() {");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println(" }");
			
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[9];");
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[7];"); 
			
			out.println("    if(oBj.valout[8]!= 'null'){ "); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[8];");
			out.println("    } "); 
			
			
			out.println("    if(oBj.valout[10]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NOTES.value=oBj.valout[10];"); 
			out.println("    } "); 
			out.println("    if(oBj.valout[11]!= 'null'){ "); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[11];");
			out.println("    } "); 
			out.println("    if(oBj.valout[12]!= 'null'){ "); 
			out.println("      getDateValues_valdate(oBj.valout[12]);"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(oBj.valout[13]) );"); 
			out.println("    if(oBj.valout[14]!= 'null'){ "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[14];"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[16];"); 
			out.println("    if(oBj.valout[19]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[19];");
			out.println("    } "); 
			out.println("    if(oBj.valout[20]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[20];");
			out.println("    } "); 
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[18]");
			out.println("    if(oBj.valout[15]!= '-'){ "); 
			out.println("     getDateValues(oBj.valout[15]);"); 
			out.println("    } "); 
			out.println("    document.Form1.TXT_FUAL_TYPE.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[22];");
			out.println("    document.Form1.TXT_APP_NO.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[24];"); 
			out.println("    assig1('T2');");	
			out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			out.println("if(oBj.valout[25]=='' || oBj.valout[25]=='null'){"); 
			out.println("oBj.valout[25]=\"\"");
		  out.println("}");
			out.println("    document.Form1.TXT_VALUER_CODE.value=oBj.valout[25];");
			out.println("if(oBj.valout[26]=='' || oBj.valout[26]=='null'){"); 
			out.println("oBj.valout[26]=\"\"");
			out.println("}");
			out.println("    document.Form1.hid_valuer_amount.value=oBj.valout[26];");
			
			
			
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_VALUER_CODE.value=oBj.valout[24];");
			out.println("    document.Form1.hid_valuer_amount.value=oBj.valout[25];");
			
			out.println("if(oBj.valout[26]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
			   out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=oBj.valout[26];");
			out.println("}"); 
			
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=oBj.valout[27];");
			
			//out.println("    document.Form1.TXT_FORCED_VALUE.value=oBj.valout[28];");
			out.println("    document.Form1.TXT_FORCED_VALUE.value=format_noobject( Number(oBj.valout[28]) );"); 
			
			out.println("}"); 
			out.println("}"); 
			*/
			
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[8];");
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[9];");
			out.println("      document.Form1.TXT_NOTES.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_VALUE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[19];");
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[20];");
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[18]");
			out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[21];"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function clear(){");
			out.println("document.Form1.TXT_INVENTORY_NO.value='';"); 
			out.println("document.Form1.TXT_REG_NO.value='';"); 
			out.println("document.Form1.TXT_ENGINE_NO.value='';"); 
			out.println("document.Form1.TXT_CHASSIS_NO.value='';"); 
			out.println("document.Form1.TXT_COLOUR.value='';");
			out.println("document.Form1.TXT_ITEM_CODE.value='';"); 
			out.println("document.Form1.TXT_MODEL_CODE.value='';"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value='';"); 
			out.println("document.Form1.TXT_NOTES.value='';"); 
			out.println("document.Form1.TXT_REMARKS.value='';"); 
			out.println("document.Form1.TXT_VALUE.value='';"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.value='';"); 
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("}");
			
			out.println("function init(){"); //To set a value.
			out.println("document.Form1.hid_no.value=y;");
			out.println("document.Form1.TXT_INVENTORY_NO.value='"+m_inv_no+"';");
			
			out.println("document.Form1.TXT_REG_NO.value='"+m_vehi_no+"';");
			out.println("document.Form1.TXT_CHASSIS_NO.value='"+m_chas_no+"';");
			out.println("document.Form1.TXT_ENGINE_NO.value='"+m_engine_no+"';");
	//out.println("get_data();");
	    
			//added by nuwan de silva on 07-11-07------------------------
	    out.println("if(document.Form1.TXT_INVENTORY_NO.value!=''){");
	    out.println("get_data();");
			out.println("}"); 


			out.println("}"); 
			
//###############################################################################################################################################################################
//########## (NOT NEEDED) ######################################################################################################################################################
		/*	out.println("function Add(y) {");
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
			out.println("alert('Entered Product Description already exists...!');");
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
			*/



			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
				out.println("function check_number_2(obj){");
			out.println("if(obj.value!='')"); 
			out.println("if(isPosInteger(obj.value)){"); 
			out.println("if(obj.value.length!=4){"); 
			out.println("alert('Year should be 4 digit number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			
			
			
		
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
			
			
			out.println("function view_report(){	"); //To view report
			out.println("    m_sql = \"m_view_sql\";"); 
			out.println("m_criteria=document.Form1.TXT_SUB_MODEL_CODE.value+\"@\"+document.Form1.TXT_MODEL_CODE.value+\"@Y@\";");
			out.println("    view_report1('1','10','0');"); 
			out.println("}"); 

		
			out.println("function view_report1(Start,End,Hid_No,Max) {"); 
			out.println("vend=parseInt(End)+10");
			out.println("vend1=parseInt(End)+1");
			out.println("vpre=parseInt(Start)-10");
			out.println("vpre1=parseInt(Start)-1");
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_Report_Servlet?class_in=\"+client_name+\"AF_MK_Report_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No,oBj,\"dialogWidth:50em; dialogHeight:20em; bottom:yes; status:no; right:yes;\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	");
			out.println("		Prev1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			
			out.println("function Prev1(Start,End,Hid_No){"); 
			out.println("    view_report1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next1(Start,End,Hid_No){"); 
			out.println("    view_report1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function check_change() {");
			out.println("if(document.Form1.CHK_ACK.checked==false){");
			out.println("document.Form1.CHK_ACK.value=\"0\"");
			out.println("}");
			out.println("else if(document.Form1.CHK_ACK.checked==true){");
			out.println("document.Form1.CHK_ACK.value=\"1\"");
			out.println("}");
			out.println("if(document.Form1.CHK_ACK.value==\"1\"){");
			out.println("window.opener.document.Form1.elements[hidchk].checked=true");
			out.println("window.opener.document.Form1.elements[hidchk].value=\"1\"");
			out.println("}");
			out.println("else if(document.Form1.CHK_ACK.value==\"0\"){");
			out.println("window.opener.document.Form1.elements[hidchk].checked=false");
			out.println("window.opener.document.Form1.elements[hidchk].value=\"0\"");
			out.println("}");
			out.println("}");
			
			
			out.println("function val_engine_no(){");
			out.println("assig1('M8'); ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_LAKDL_AF_MK_display_valuation_report_validate_engine_no&data_val_engine_no=\"+document.Form1.TXT_ENGINE_NO.value+\"&data_val_app_no=\"+document.Form1.TXT_APP_NO.value+\"&data_val_asset_no=\"+document.Form1.TXT_ASSET_ID.value+\"&data_val_model_no=\"+document.Form1.TXT_MODEL_CODE.value+\"&data_val_sub_model=\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_chassis_no(){");
			out.println("assig1('M7'); ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_LAKDL_AF_MK_display_valuation_report_validate_chassis_no&data_val_chassis_no=\"+document.Form1.TXT_CHASSIS_NO.value+\"&data_val_app_no=\"+document.Form1.TXT_APP_NO.value+\"&data_val_asset_no=\"+document.Form1.TXT_ASSET_ID.value+\"&data_val_model_no=\"+document.Form1.TXT_MODEL_CODE.value+\"&data_val_sub_model=\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	

			//=========added by nuwan de silva 07-11-07=============
		  out.println("	function chk_comment_length(obj){ ");
			out.println(" var remarks_length=obj.value.toString().length;");
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			out.println("} ");
						
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
      out.println("} ");
   
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"init(),load_roll_value('New'),application_no()\">"); //,load_lock()
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_valuer_amount'>");

			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process -Inspection and Valuation Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='view_report()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
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


			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUATION_NO'  class=div_input>Valuation No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUATION_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"assig1('T1'),makeRequest(document.Form1.TXT_VALUATION_NO)\" onchange=\"\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_VALUATION_NO' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUER_CODE'  class=div_input>Valuer Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUER_CODE' maxlength='10' size='10' onblur=\"assig1('VAL'),Valuer(document.Form1.TXT_VALUER_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_VALUER_CODE' value=\"Help\" onClick=\"help_update_valuer()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >");//Added by nuwan de silva on 17-07-07
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUER_NAME' class=div_input>Valuer Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUER_NAME' style={width:150px;}  size='10' onblur=\"\" disabled>"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_VALUATION_DATE'  class=div_input>Valuation Date [DD-MM-YYYY] *</DIV></td>"); 
			out.println("</td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_VALUATION_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_VALUATION_DATE_MM' maxlength='2' size='2'>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_VALUATION_DATE_YY' maxlength='4' size='4' onBlur=\"check_valuation_date()\" ><input type='Hidden' name='HID_TXT_VALUATION_DATE' value=\"\"></td>"); 
			out.println("</tr>");

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INVENTORY_NO'  class=div_input>Inventory Number *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVENTORY_NO' maxlength='15' size='15' onblur=\"assig1('T4'),asset(document.Form1.TXT_INVENTORY_NO)\" >");
			out.println("<input class='but_input' type='button' name='BUT_TXT_INVENTORY_NO' value=\"Help\" onClick=\"help_button_inventory()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>");
			out.println("<td width='30%' >Asset Id</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr>");
			out.println("<td width='30%' >Item Category Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_CODE' maxlength='10' size='10' disabled onblur=\"assig1('T2'),item_val(document.Form1.TXT_ITEM_CODE)\" onkeypress=\"d(),assig1('T2')\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>");
			out.println("<td width='30%' >Model Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MODEL_CODE' maxlength='10' size='10' onblur=\"assig1('T3'),dd(document.Form1.TXT_MODEL_CODE.value)\" onkeypress=\"d(),assig1('T3'),check()\" disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_MODEL_CODE'  class=div_input>Sub Model Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_CODE' maxlength='10' size='10' onkeypress=\"assig1('T5'),d(),check_sub()\" onblur=\"assig1('T5'),sub(document.Form1.TXT_SUB_MODEL_CODE.value)\" disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_YEAR_OF_MANUFACTURE'  class=div_input>Year Of Manufacture</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_YEAR_OF_MANUFACTURE' maxlength='4' size='10' onBlur=\"check_number_2(this)\">"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REG_NO'  class=div_input>Registration No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' onkeypress='d()' ></td>"); 
			out.println("<td width='*%'></td>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input>Chassis No / Serial No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='50' size='20' onkeypress='d()' onBlur=\"val_chassis_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engine No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='50' size='20' onkeypress='d()' onBlur=\"val_engine_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Condition of Asset</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_CONDITION_OF_ASSET' maxlength='1' size='1' >");  
			out.println("<option value='N' selected>New</option>");			
			out.println("<option value='R' >Re-Condition</option>");	
			out.println("<option value='U' >Used</option>");	
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>"); 


			out.println("<tr >"); 
			out.println("<td width='30%' >Notes</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NOTES' style=\"width:250px\" maxlength='100' size='100' onkeypress='d()'></td>"); 
						out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_NOTES' style=\"width:300px; height:25px;\" maxlength='100' size='1000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 

			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Remarks</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REMARKS' style=\"width:250px\" maxlength='100' size='100' onkeypress='d()'></td>"); 
			out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:300px; height:25px;\" maxlength='100' size='1000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Type of the Body</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TYPE_OF_BODY' maxlength='50' size='50' onkeypress=\"d()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_DATE_OF_REG'  class=div_input>Date of Registration [DD-MM-YYYY] </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_REG_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_YY' maxlength='4' size='4' onBlur=\"check_regi_date()\" ><input type='Hidden' name='HID_TXT_REG_DATE' value=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >General Index (Engineer's Summary)</td>"); 
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE'  class=div_input>Value *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='22' size='22' STYLE='{text-align:right;}' onkeypress='d()' onblur=\"check_value(document.Form1.TXT_VALUE.value),format_number(document.Form1.TXT_VALUE,25)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FORCED_VALUE'  class=div_input>Forced Sales Value</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FORCED_VALUE' maxlength='250' size='25' STYLE=\"{text-align:right}\" onBlur=\"check_number(this,25)\" >"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			out.println("<tr >"); 
			out.println("<td width='30%' >Odometer Reading </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_METER_READING' STYLE=\"{text-align:right}\"  maxlength='22' size='22' onkeypress='d()' onblur=\"check_meter()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Seating Capacity</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SEATING_CAPACITY' STYLE=\"{text-align:right}\"  maxlength='2' size='2' onkeypress='d()' onblur=\"check_seat()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Cubic Capacity</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_CYLINDERS' STYLE=\"{text-align:right}\"  maxlength='1' size='' onkeypress='d()' onblur=\"check_cyl()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			out.println("<tr>");
			out.println("<td width='30%' >Fuel Type</td>");
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_FUAL_TYPE' maxlength='10' size='1' onchange='d()' disabled>");  

			String m_val_no1=(String)m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE"); 
	
			rs=stmt.executeQuery(" SELECT CODE,DESCRIPTION "+
													 	"FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE ");
													
		
	
			boolean more=rs.next();
			while(more){
			out.println("<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");			
			more=rs.next();
						}
					
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_COLOUR'  class=div_input>Colour </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLOUR' maxlength='10' size='10' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("</table>");
			
				
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
			
			
			out.println("<table>");  
			out.println("</table>");  

			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='view_report()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
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
