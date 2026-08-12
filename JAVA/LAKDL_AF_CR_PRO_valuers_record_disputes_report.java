//--
//SCREEN NAME:CREDIT PROCESS - VALUATION_REPORT
//CREATED BY :CHANDANA
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_valuers_record_disputes_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	String reqstr;
	Connection conn;
	public String m_val_code;
	public ResultSet rs;
	public ResultSetMetaData rms; 
	Statement stmt;
	public String m_val_code1;
	String m_chksql;
	
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
			String m_valuation="";
			
			
			m_chksql         = req.getParameter("chksql");
			
				if (m_chksql.trim().equals("idle")) {
				out.println("idle");
		 	}
					else if(m_chksql.trim().equals("main_page")){
			
			
			String m_val = req.getParameter("APP_NO");
			
			m_valuation = req.getParameter("valuation_no");
			
			if (m_valuation==null){
			m_valuation="";
			}

			String m_my_screen="";
			
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
			m_my_screen="";
			
			}
			String m_row        = req.getParameter("row");		
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Valuers record value disputes</TITLE>"); 
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
			
			out.println("var m_eng_no='';");
			out.println("var m_chassis_no='';");
			

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length >0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value=='T1' ){");
			out.println("				alert('Record already exists.');");
			out.println("				new_window();");
			out.println("			}");//To Check new valuation code exists.
			out.println(" else if(document.Form1.hid_x.value=='8' && data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T1'){");
			out.println("    help_update();"); 
			out.println("    document.Form1.TXT_VALUATION_NO.focus();"); 
			out.println("}");//To Check valuation code correct.
			
			
			out.println(" else if(document.Form1.TXT_INVOICE_NO.value!='' && document.Form1.hid_st.value=='T_ENG'){");
			out.println("    m_eng_no    =data_vec[0];"); 
			out.println("    m_chassis_no=data_vec[1];"); 
			
			
			//out.println("    assig1('T2');");	
			//out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			
			out.println("}");
			
			
			out.println(" else if((data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T10')||(data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.hid_st.value=='T11')){");
			
			//out.println(" alert(data_vec[26]);"); 
			
			out.println("  document.Form1.TXT_VALUATION_NO.value=data_vec[0];");
			out.println("    document.Form1.TXT_ASSET_ID.value=data_vec[1];");
			out.println("    document.Form1.TXT_APP_NO.value=data_vec[2];");
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_REG_NO.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[6];");
			out.println("    document.Form1.TXT_COLOUR.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=data_vec[8];");
			out.println("    document.Form1.TXT_NOTES.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_REMARKS.value=data_vec[10];");
			
			out.println("    if(data_vec[11]!= 'null'){ "); 
			out.println("      getDateValues_valdate(data_vec[11]);"); 
			out.println("    } "); 
			
			
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(data_vec[12]) );"); 
			
			out.println("    if(data_vec[13]!= 'null'){ "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=data_vec[13];"); 
			out.println("    } ");
			
			out.println("    if(data_vec[14]!= 'null'){ "); 
			out.println("      getDateReg_valdate(data_vec[14]);"); 
			out.println("    } "); 
			
			out.println("    document.Form1.TXT_METER_READING.value=data_vec[15];");
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=data_vec[16]");
			out.println("    if(data_vec[17]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=data_vec[17];");
			out.println("    } "); 
	
			out.println("    if(data_vec[18]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=data_vec[18];");
			out.println("    } "); 
			out.println("    document.Form1.TXT_INVOICE_NO.value=data_vec[19];");
			
			out.println("    document.Form1.TXT_VALUER_CODE.value=data_vec[21];"); 
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=data_vec[23];");
			 
			out.println("if(data_vec[22]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[22];");
			out.println("}"); 
		
			out.println("    document.Form1.TXT_FORCED_VALUE.value=format_noobject( Number(data_vec[24]) );"); 
			
			out.println("    document.Form1.TXT_COMMENTS.value=data_vec[25];"); 
			
			
			
			out.println(" if(data_vec[26]=='Y'){ ");	
			out.println("    document.Form1.CHK_REQUIRED.checked='True' ;");
			out.println("}else");
			out.println("{  ");
			out.println("    document.Form1.CHK_REQUIRED.checked ;");
			out.println("}");
			
					
			
			
			
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
			out.println("    document.Form1.TXT_INVOICE_NO.value=data_vec[23];");		
			out.println("    document.Form1.TXT_VALUER_CODE.value=data_vec[24];");	
			
			out.println("if(data_vec[25]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[25];");
			out.println("}"); 
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=data_vec[26];");
			out.println("    document.Form1.TXT_FORCED_VALUE.value=data_vec[27];"); 
			
			out.println("    assig1('T2');");	
			out.println("    item_val(document.Form1.TXT_ITEM_CODE)");	
			out.println("}");//To Check valuation code correct.
			
			out.println(" else if(data_vec.length==0 && document.Form1.hid_x.value=='5' && document.Form1.TXT_ASSET_ID.value!='' &&  document.Form1.hid_st.value=='T4'){");
			out.println("help_button_2(); ");
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
			out.println("document.Form1.TXT_MODEL_CODE.value=data_vec[1]");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value=data_vec[2]");
			out.println("document.Form1.TXT_FUAL_TYPE.value=data_vec[3]");
			out.println("document.Form1.TXT_ITEM_CODE.value=data_vec[4];");
			out.println("document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[5];");
			out.println("assig1('T2');");	
			out.println("item_val(document.Form1.TXT_ITEM_CODE);");	
			out.println("data_vec=''");
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
			out.println("    document.Form1.TXT_ASSET_ID.value=data_vec[1];"); 
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
			out.println("    document.Form1.TXT_COLOUR.value=data_vec[14];");
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
			
			out.println(" else if (data_vec.length ==0 && document.Form1.hid_st.value=='INV' && document.Form1.TXT_INVOICE_NO.value!=\"\" ){");
			out.println("       help_invoice();"); 
			out.println("}");
			
			out.println(" else if (data_vec.length >0 && document.Form1.hid_st.value=='INV' && document.Form1.TXT_INVOICE_NO.value!=\"\"){");
			out.println("       document.Form1.TXT_INVOICE_NO.value=data_vec[0] "); 
			out.println("       document.Form1.TXT_ASSET_ID.value=data_vec[2] "); 
			out.println("assig1('T4')");
			out.println("asset(document.Form1.TXT_ASSET_ID);");
			out.println("}");
			out.println(" else if (data_vec.length ==0 && document.Form1.hid_st.value=='VAL' && document.Form1.TXT_VALUER_CODE.value!=\"\" ){");
			out.println("       help_update_valuer();"); 
			out.println("}");
			out.println(" else if (data_vec.length >0 && document.Form1.hid_st.value=='VAL' && document.Form1.TXT_VALUER_CODE.value!=\"\"){");
			out.println("       document.Form1.TXT_VALUER_CODE.value=data_vec[0] "); 
			out.println("}");
			out.println("}");
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");//To seperatly identify m_url's.
			out.println("}");
			
			
			out.println("function close_screen() {");
			out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("	if(document.Form1.close2.value==\"Close\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_asset_details&data_val=\"+obj.value ;");
			out.println("load_interface(m_url,'XML');");
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_REG_DATE_DD.value=dval.substring(0,2);");
			out.println("document.Form1.TXT_REG_DATE_MM.value=dval.substring(3,5);");
			out.println("document.Form1.TXT_REG_DATE_YY.value=dval.substring(6,10);");
			out.println("document.Form1.HID_TXT_REG_DATE.value=dval;");
			out.println("}");
			
		
					

			
			
	
			
			
			out.println("function makeRequest(obj) {");//To get valuation details for validation.
			out.println("");
			out.println("document.Form1.hid_x.value='8';");
			out.println("if(document.Form1.hid_st.value=='T1'){;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_act.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function Invoice(obj) {");//To get valuation details for validation.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_performa_invoice&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
		
			out.println("function Valuer(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_valuer&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
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
			//out.println("window.open(m_url);");
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
			out.println("'<td width=\"10%\" ><input class=\"txt_input\" type=\"text\" name=TXT_STATUS_'+j+' value=\"\" maxlength=\"10\"  size=\"10\"></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_'+j+' value=\"\" maxlength=\"100\" style=\"width:250px\"  size=\"100\"></td>'+"); 
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
			
			out.println("get_engine_chassis_no();");
			
      out.println("}"); 
			
			out.println("function get_engine_chassis_no(){");
			
			out.println("document.Form1.hid_st.value='T_ENG' ;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_engine_chassis_no&data_val=\"+document.Form1.TXT_INVOICE_NO.value;");
		//	out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");

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
			
			out.println("get_engine_chassis_no();");
	    out.println("}"); 
			
			
			out.println("function XX() {");//To find make code according to item code
			out.println("document.Form1.hid_val.value='2';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inspect1&data_val=\"+document.Form1.TXT_ITEM_CODE.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

			out.println("function validate_data(){"); 
			//out.println("alert('eng no'+m_eng_no);");
			//out.println("alert('chassis no'+m_chassis_no);");
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APP_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APP_NO.style.color='red';");
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
			out.println("else if(document.Form1.TXT_ENGINE_NO.value==\"\" && m_eng_no!='-'){  "); 
			out.println("DIV_TXT_ENGINE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CHASSIS_NO.value==\"\" && m_chassis_no!='-' ){  "); 
			out.println("DIV_TXT_CHASSIS_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		//	out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
		//	out.println("DIV_TXT_COLOUR.style.color='red';");
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
		  
			out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		 document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Valuers_Record_Details';");  
			out.println("		 document.Form1.submit();	"); 
		
			out.println("} "); 
			
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_valuers_record_disputes_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			
			out.println("function new_window(){	");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_valuers_record_disputes_report?chksql=main_page';");
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
			out.println("if('"+ m_valuation+"'!=\"\"){ ");
			out.println("m_val=\"Edit\"");   
			out.println("help_box.innerHTML=\" Valuers record value disputes Report - \"+m_val;"); 
			out.println("}	");
			out.println("if('"+ m_valuation+"'==\"\"){ ");
			out.println("m_val=\"New\"");
			out.println("help_box.innerHTML=\" Valuers record value disputes - \"+m_val;"); 
			out.println("}	"); 
			out.println("}"); 
			out.println(""); 


			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Valuers record value disputes - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=true;");
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
			out.println("document.Form1.TXT_YEAR_OF_MANUFACTURE.disabled=true;"); 
			out.println("document.Form1.TXT_CONDITION_OF_ASSET.disabled=true;"); 
			out.println("document.Form1.TXT_FORCED_VALUE.disabled=true;"); 
			
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){");
			out.println("document.Form1.TXT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.BUT_VALUATION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=false;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;");
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
			out.println("document.Form1.TXT_ASSET_ID.disabled=false;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=false;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=false;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=false;");
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"8\"){"); 
			out.println("		help_value_assign_valuer();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"25\"){"); 
			out.println("		help_value_assign_invoice();"); 
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
			
			
		/*	out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			*/
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'1');"); 
			out.println("}"); 
			out.println(""); 
			

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_button_2() {");//Asset help 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ASSET_ID_sql2\";"); 
			out.println("    m_criteria = document.Form1.TXT_ASSET_ID.value+\"@\"+document.Form1.TXT_APP_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','9',m_criteria,m_sql,'2');"); 
			out.println("}"); 
			
			out.println(""); 
			
			out.println("function help_invoice() {");//Invoice help 
			out.println("    document.Form1.hid_help_type.value=\"25\";"); 
			out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+document.Form1.TXT_APP_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','25',m_criteria,m_sql,'25');"); 
			out.println("}"); 

			
			out.println("function help_value_assign_2() {"); 
	    out.println(" document.Form1.TXT_ASSET_ID.value=oBj.valout[2];"); 
	    out.println(" document.Form1.TXT_MODEL_CODE.value=oBj.valout[3];"); 
	    out.println(" document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[4];"); 
			out.println(" document.Form1.TXT_FUAL_TYPE.value=oBj.valout[11];");
			out.println(" document.Form1.TXT_ITEM_CODE.value=oBj.valout[12];");
			out.println("assig1('T2');");	
			out.println("item_val(document.Form1.TXT_ITEM_CODE)");	
			out.println("}"); 
			
			out.println("function help_button_3() {");//Sub model help 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("if(document.Form1.TXT_MODEL_CODE.value!=''){");
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_CODE_sql1\";");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+m_sub+\"@Y@\";");
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MODEL_CODE.value==''){");
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_MODEL_CODE.value+\"@Y@\";");
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'4');"); 
			out.println("}"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
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
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'4');"); 
			out.println("}"); 
			out.println(""); 


			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[2];");
			out.println("    fuel_type()");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.focus() ");
			out.println("}");
			
			out.println("function help_button_5() {");//Type Body help(not used)  
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_TYPE_OF_BODY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_TYPE_OF_BODY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'5');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_6(y) {"); //feild code help(not used)
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("document.Form1.hid_v.value=y");
			out.println("    m_sql = \"m_help_TXT_FILED_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FILED_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'6');"); 
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
			
			out.println("function help_button_7(y) {");//Item Code help  
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";"); 
			
			out.println("    m_criteria = document.Form1.TXT_ITEM_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'7');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_7() {");
			out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ITEM_CODE.focus();"); 
			out.println("}"); 
			
			out.println("function help_value_assign_invoice() {");
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[4];"); 
			out.println("assig1('T4')");
			out.println("asset(document.Form1.TXT_ASSET_ID);");
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
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_sql = \"m_help_TXT_DISPUTES_VALUATION_NO_sql_new\";"); 
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\"){ ");
			out.println("    m_sql = \"m_help_TXT_DISPUTES_VALUATION_NO_sql_edit\";"); 

			out.println("}");
			
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+document.Form1.TXT_APP_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_VALUATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','19',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			
			
			
			
			out.println("function help_update_valuer() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUER_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_criteria = document.Form1.TXT_VALUER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_VALUER_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','6',m_criteria,m_sql,'8');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_valuer() {"); 
			out.println("    document.Form1.TXT_VALUER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.hid_valuer_amount.value=oBj.valout[11];");

			out.println("}");
			
			
			out.println("function getDateValues_valdate(dval){");
			out.println("document.Form1.TXT_VALUATION_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_VALUATION_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_VALUATION_DATE_YY.value=dval.substring(6,10)");
			out.println("document.Form1.HID_TXT_VALUATION_DATE.value=dval;");
			out.println("}");
			
			
			
			out.println("function getDateReg_valdate(dval){");
			out.println("document.Form1.TXT_REG_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_REG_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_REG_DATE_YY.value=dval.substring(6,10)");
			out.println("document.Form1.HID_TXT_VALUATION_DATE.value=dval;");
			out.println("}");
			
			
			
			out.println("function help_update_value_assign_99() {");
				
			out.println("    document.Form1.TXT_VALUATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_APP_NO.value=oBj.valout[4];");
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[8];");
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[10];");
			out.println("    document.Form1.TXT_NOTES.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];");
			
			out.println("    if(oBj.valout[13]!= 'null'){ "); 
			out.println("      getDateValues_valdate(oBj.valout[13]);"); 
			out.println("    } "); 
			
			out.println("    document.Form1.TXT_VALUE.value=format_noobject( Number(oBj.valout[14]) );"); 
			
			out.println("    if(oBj.valout[15]!= 'null'){ "); 
			out.println("    document.Form1.TXT_TYPE_OF_BODY.value=oBj.valout[15];"); 
			out.println("    } ");
			
			out.println("    if(oBj.valout[16]!= 'null'){ "); 
			out.println("      getDateReg_valdate(oBj.valout[16]);"); 
			out.println("    } "); 
			
			out.println("    document.Form1.TXT_METER_READING.value=oBj.valout[17];");
			out.println("    document.Form1.TXT_GENERAL_INDEX.value=oBj.valout[19]");
			out.println("    if(oBj.valout[20]!= 'null'){ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[20];");
			out.println("    } "); 
			out.println("    if(oBj.valout[21]!= 'null'){ "); 
			out.println("    document.Form1.TXT_NO_OF_CYLINDERS.value=oBj.valout[21];");
			out.println("    } "); 
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[22];");
			
			out.println("    document.Form1.TXT_VALUER_CODE.value=oBj.valout[25];");
			 
			out.println("if(oBj.valout[26]==0){");
      out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("}"); 
			out.println("else {"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=oBj.valout[26];");
			out.println("}"); 
			
			out.println("    document.Form1.TXT_FORCED_VALUE.value=format_noobject( Number(oBj.valout[28]) );"); 
			
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[29];"); 
			
			
			
			out.println(" if(oBj.valout[30]=='Y'){ ");	
			out.println("    document.Form1.CHK_REQUIRED.checked='True' ;");
			out.println("}else");
			out.println("{  ");
			out.println("    document.Form1.CHK_REQUIRED.checked ;");
			out.println("}");
			
			//out.println("    document.Form1.TXT_ITEM_CODE.value=oBj.valout[23];");
		
		
		
			
			
			
		//	out.println("get_engine_chassis_no();");
			
			out.println("}"); 
			
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
			out.println("document.Form1.TXT_VALUE.value='';"); 
			out.println("document.Form1.TXT_TYPE_OF_BODY.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.value='';"); 
			out.println("document.Form1.TXT_NO_OF_CYLINDERS.value='';"); 
			out.println("document.Form1.TXT_METER_READING.value='';"); 
			out.println("}");
			
			out.println("function init(){"); //To set a value.
			out.println("document.Form1.hid_no.value=y;");
			out.println("}"); 
	
			
			out.println("function checkMonthLength() {"); //(TO VALIDATE DATE AND NUMBERS)
			out.println("mm=document.Form1.hid_month.value;");
			out.println("dd=document.Form1.hid_day.value;");
			out.println("c_yyyy=document.Form1.hid_year.value;");
			out.println("inputyearStr = c_yyyy;");
			out.println("if (inputyearStr.length != 4)");
			out.println("{");
			out.println("alert('Please enter year in four digit(YYYY) number format');");
			out.println("}");
			out.println("}");
			
			
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
			out.println("alert('Please enter a number');");
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
			out.println("alert('Please enter a number');");
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
			out.println("alert('Please enter a number');");
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
			out.println("valno	=    document.Form1.hid_day.value;"); 
			out.println("valno1	=    document.Form1.hid_month.value;"); 
			out.println("valno2	=    document.Form1.hid_year.value;"); 
			out.println("m_size=valno.length;");
			out.println("m_size1=valno1.length;");
			out.println("m_size2=valno2.length;");
			out.println("m_date=val.length;");
			out.println("for (var i=0 ; i<=m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Day is wrong....!');");
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
			out.println("return false;");
			out.println("i=m_size2;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function seperate1(){");
			out.println("var inputStr;");
			out.println("var inputStr1;");
			out.println("valno	=    document.Form1.hid_day.value;"); 
			out.println("valno1	=    document.Form1.hid_month.value;"); 
			out.println("valno2	=    document.Form1.hid_year.value;"); 
			out.println("m_size=valno.length;");
			out.println("m_size1=valno1.length;");
			out.println("m_size2=valno2.length;");
			out.println("m_date=val.length;");
			out.println("for (var i=0 ; i<=m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Day is wrong....!');");
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
			out.println("return false;");
			out.println("i=m_size2;");
			out.println("break;	");
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
			
			//Added by Mahela on 26-02-2007
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_VALUATION_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_VALUATION_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_VALUATION_DATE_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 	
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_REG_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_REG_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_REG_DATE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");
			
			out.println("function check_change() {");
			out.println("hidchk='hid_chk1_'+"+m_row+"");
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
			
			//ADDED BY NUWAN DE SILVA-------------------------------------
			
			out.println("function val_engine_no(){");
			out.println("assig1('M8'); ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_valuation_report_validate_engine_no&data_val_engine_no=\"+document.Form1.TXT_ENGINE_NO.value+\"&data_val_app_no=\"+document.Form1.TXT_APP_NO.value+\"&data_val_invoice_no=\"+document.Form1.TXT_INVOICE_NO.value+\"&data_val_asset_no=\"+document.Form1.TXT_ASSET_ID.value+\"&data_val_model_no=\"+document.Form1.TXT_MODEL_CODE.value+\"&data_val_sub_model=\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_chassis_no(){");
			out.println("assig1('M7'); ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_valuation_report_validate_chassis_no&data_val_chassis_no=\"+document.Form1.TXT_CHASSIS_NO.value+\"&data_val_app_no=\"+document.Form1.TXT_APP_NO.value+\"&data_val_invoice_no=\"+document.Form1.TXT_INVOICE_NO.value+\"&data_val_asset_no=\"+document.Form1.TXT_ASSET_ID.value+\"&data_val_model_no=\"+document.Form1.TXT_MODEL_CODE.value+\"&data_val_sub_model=\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			
			out.println("function change_val_req(){")	;
			//out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			
			
		
			out.println("if(document.Form1.CHK_REQUIRED.checked==true){");
			out.println("document.Form1.CHK_REQUIRED.value='on'");
			out.println("}else if(document.Form1.CHK_REQUIRED.checked==false){");
			out.println("document.Form1.CHK_REQUIRED.value='off'");
			out.println("}");
		//	out.println("alert(document.Form1.CHK_REQUIRED.value);");   
			out.println("}");	
			
			
			out.println("function assign_valuation(){");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("document.Form1.hid_st.value='T10';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_dispute_valuation_report_validate_valuation_no_new&data_val_valuation_no=\"+document.Form1.TXT_VALUATION_NO.value+\"&disp_status=N\";");
			
			out.println("}else{");
			out.println("document.Form1.hid_st.value='T11';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_dispute_valuation_report_validate_valuation_no_new&data_val_valuation_no=\"+document.Form1.TXT_VALUATION_NO.value+\"&disp_status=Y\";");
			out.println("}");
			
			//out.println("window.open(m_url);");
			
			out.println("load_interface(m_url,'XML');");		
			out.println("}");
			
			
			
			
			
			
			   
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"init(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); //To seperatly identify m_url's.
			out.println("<INPUT TYPE='Hidden' NAME='hid_field' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_v'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_act'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_st'>");//To seperatly identify m_url's.
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_x'>");//To seperatly identify m_url's.
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_day'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_month'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_year'>");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no'>");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Valuers record value disputes</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
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
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APP_NO' maxlength='15' size='15'>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");


			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUATION_NO'  class=div_input>Valuation No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUATION_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"assign_valuation()\" >"); /*assig1('T1'),makeRequest(document.Form1.TXT_VALUATION_NO)*/
			out.println("<input class='but_input' type='button' name='BUT_VALUATION_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUER_CODE'  class=div_input>Valuer Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUER_CODE' maxlength='10' size='10' onblur=\"assig1('VAL'),Valuer(document.Form1.TXT_VALUER_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_VALUER_CODE' value=\"Help\" onClick=\"help_update_valuer()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			// Modified by Thamali Jayatunga on 2009.10.14, Added onBlur event
			out.println("<td width='10%' ><DIV id='DIV_TXT_VALUATION_DATE'  class=div_input>Valuation Date [DD-MM-YYYY] *</DIV></td>"); 
			out.println("</td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_VALUATION_DATE_DD' maxlength='2' size='2' onBlur=\"checkMonthLength(document.Form1.TXT_VALUATION_DATE_DD,document.Form1.TXT_VALUATION_DATE_MM,document.Form1.TXT_VALUATION_DATE_YY)\">");
			out.println("                 <input class='txt_input5' type='text' name='TXT_VALUATION_DATE_MM' maxlength='2' size='2' onBlur=\"checkMonthLength(document.Form1.TXT_VALUATION_DATE_DD,document.Form1.TXT_VALUATION_DATE_MM,document.Form1.TXT_VALUATION_DATE_YY)\" >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_VALUATION_DATE_YY' maxlength='4' size='4' onBlur=\"checkMonthLength(document.Form1.TXT_VALUATION_DATE_DD,document.Form1.TXT_VALUATION_DATE_MM,document.Form1.TXT_VALUATION_DATE_YY)\" ><input type='Hidden' name='HID_TXT_VALUATION_DATE' value=\"\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(\"1\")>   Calendar</a></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assig1('INV'),Invoice(document.Form1.TXT_INVOICE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_invoice()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Asset ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='15' size='15' onkeypress=\"\" onblur=\"assig1('T4'),asset(document.Form1.TXT_ASSET_ID)\" disabled ></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr>");
			out.println("<td width='30%' >Item Category Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_CODE' maxlength='10' size='10' onblur=\"assig1('T2'),item_val(document.Form1.TXT_ITEM_CODE)\" onkeypress=\"d(),assig1('T2')\"  disabled></td>"); 
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
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='50' size='20' onkeypress='d()' onBlur=\"val_chassis_no()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engine No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='50' size='20' onkeypress='d()' onBlur=\"val_engine_no()\"></td>"); 
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
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NOTES' style=\"width:250px\" maxlength='100' size='100' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Remarks</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REMARKS' style=\"width:250px\" maxlength='100' size='100' onkeypress='d()'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Type of the Body</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TYPE_OF_BODY' maxlength='50' size='50' onkeypress=\"d()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			// Modified by Thamali Jayatunga on 2009.10.14, Added onBlur event
			out.println("<td width='30%' ><DIV id='DIV_TXT_DATE_OF_REG'  class=div_input>Date of Registration [DD-MM-YYYY] </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_REG_DATE_DD' maxlength='2' size='2' onBlur=\"checkMonthLength(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)\">");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_MM' maxlength='2' size='2' onBlur=\"checkMonthLength(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)\">");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_YY' maxlength='4' size='4' onBlur=\"checkMonthLength(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)\" ><input type='Hidden' name='HID_TXT_REG_DATE' value=\"\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(\"2\")>   Calendar</a></td>"); 
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
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='22' size='22' STYLE='{text-align:right;}' onkeypress='d()' onblur=\"check_number(document.Form1.TXT_VALUE,25)\"></td>");  //check_value(document.Form1.TXT_VALUE.value),format_number(document.Form1.TXT_VALUE,25)
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FORCED_VALUE'  class=div_input>Forced Sales Value</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FORCED_VALUE' maxlength='250' size='25' STYLE=\"{text-align:right}\" onBlur=\"check_number(this,25)\" >"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' >Odometer Reading *</td>"); 
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
													 	"FROM LAKDL.AF_CO_MAS_FUEL_TYPE ");
		
	
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
			
			out.println("<hr>"); 
						
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0' class='table'>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_COMMENTS'  class=div_input><B>Comments</B></DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_COMMENTS' style=\"width:250px\" maxlength='200' size='100' onkeypress=''></td>"); 
			out.println("<td width=\"25%\"><B>Please tick this box if the valuation is complete.</B></td><td width=\"5%\"><input class='' type='checkbox' name='CHK_REQUIRED' onClick='change_val_req()' ></td><td width=\"20%\"></td>");
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
			if  (!m_my_screen.equals("")){
			out.println("<tr><td width='10%'><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
			out.println("<tr><td width='10%' ><input type=\"button\" class='mainbut' style=\"width:140px\" name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td></tr>");  
			out.println("<tr>");  
			out.println("</tr>");	
		  out.println("<tr>");  
		  out.println("</tr>");
			}

			out.println("</table>");  

			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='view_report()'  value=\"View All\"></td>"); //onclick='View_all()'
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		   		
			if  (m_my_screen.equals("")){
			out.println("<td width='10%' align='center' ><input type=\"button\" class='mainbut' name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Close\"></td>");  
			}
				
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
		}
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
