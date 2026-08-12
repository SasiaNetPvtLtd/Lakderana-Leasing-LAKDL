//--
//SCREEN NAME:SYSTEM ADMINISTRATION - INDICATIVE QUOTATION
//CREATED BY :delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_display_indicative_quotation extends javax.servlet.http.HttpServlet { 
	Connection conn;

		ServletOutputStream out = null;
		Statement stmt,stmt1,stmt2;
		public ResultSet rs,rs1;
		
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods   m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			stmt=conn.createStatement();
			String m_schema_name=m_sn_methods.schema_name.trim();

			String m_close_status  = "N";
			
			String m_pri_no   = req.getParameter("PRI_NO");
      String m_inq_no   = req.getParameter("INQ_NO");
			m_close_status= req.getParameter("close_status");

			String m_my_screen="";
			if(m_inq_no==null){
			m_my_screen="FROM_MENU";
			}
			else {
			
			m_my_screen="";//FROM PRICING(APPLICATION PROCESS)

			}		
			String m_screen_type=req.getParameter("screen_type");
			
			//String m_app_no=
			//String m_my_screen=""; //Added By Nuwan De Silva 12/12/06
			
	
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Quotation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
			out.println("var j=0;");
			out.println("var x=0;");
			
			out.println("var opt=1;");
			
			out.println("var val_of=0;");
			out.println("var x_of=0;");
			out.println("var lineno=0;");
			out.println("var m_row;");
			out.println("var hid_x=0;");
			out.println("var hid_y=0;");
			out.println("var m_row1;");
			out.println("var m_x=0;");
			out.println("var arr_size=0;");
			out.println("var price_arry=new Array();");
			out.println("var qty_arry=new Array();");		
			out.println("var con_arry=new Array();");		
			out.println("var make_arry=new Array();");		
			out.println("var model_arry=new Array();");	
			out.println("var net_arry=new Array();");		
			out.println("var opt_arry=new Array();");		
			out.println("var m_xx;");
			out.println("var m_del=0;");
			out.println("var m_opt=0;");
			out.println("var m_use=0;");
			
			out.println("var m_count=0;");
			out.println("var e=0;");		
						
			out.println("var hid_x_1=0;");
			out.println("var rate_arry=new Array();");	
			out.println("var save_arry=new Array();");	
			
			out.println("var m_close_status=\"N\""); //Hold The Close Status Of The Screen -- Added By Nuwan De Silva
			out.println("var b_flag=0");
			out.println("var b_flag1=0");

			out.println("m_close_status='"+m_close_status+"';");
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT' && document.Form1.hid_field.value!='IN' && document.Form1.hid_field.value!='PR' && document.Form1.hid_field.value!='CN' && document.Form1.hid_field.value!='MAK' && document.Form1.hid_field.value!='MOD' && document.Form1.hid_field.value!='PF' && document.Form1.hid_field.value=='88' && document.Form1.hid_field.value=='QTO'){ ");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value=='QT' && m_row1!=''){ ");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");


			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT'&& document.Form1.hid_field.value=='IN' && m_row1!=''){ ");
			out.println("help_button_2()");
			out.println("			}");
			
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT' && document.Form1.hid_field.value!='IN' && document.Form1.hid_field.value=='PR' && m_row1!=''){ ");
			out.println("pricing1=\"text_price_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("help_button_3(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value)");
			out.println("			}");
			
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT' && document.Form1.hid_field.value!='IN' && document.Form1.hid_field.value=='CN' && m_row1!=''){ ");
			out.println("con=\"text_conasst_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("help_button_5(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value)");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT' && document.Form1.hid_field.value!='IN' && document.Form1.hid_field.value!='CN' && document.Form1.hid_field.value=='MAK' && m_row1!=''){ ");
			out.println("make=\"text_make_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN' && document.Form1.hid_field.value!='MK' && document.Form1.hid_field.value!='MO' && document.Form1.hid_field.value!='RT' && document.Form1.hid_field.value!='QT' && document.Form1.hid_field.value!='IN' && document.Form1.hid_field.value!='CN' && document.Form1.hid_field.value=='MOD' && m_row1!=''){ ");
			out.println("model=\"text_model_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("			}");
						
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='TN'){");
			out.println("				fill_fields(data_vec);");
			out.println("			}");
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='MK'){");
			out.println("	fill_make(data_vec)			;");
			out.println("			}");

			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='MO'){");
			out.println("	fill_model(data_vec)			;");
			out.println("			}");

			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='RT'){");
			out.println("rate_arry=data_vec[0]");
			out.println("document.Form1.hid_rate.value=data_vec[0];");
			out.println("assg_status(data_vec[0])");
			out.println("			}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='QTO'){");
			out.println("    document.Form1.TXT_QUOTATION_NO.value=data_vec[0];"); 
			out.println("			}");
			out.println("}");
			
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_field.value=val;");
			out.println("}");


			out.println("function assg_status(val) {");
			out.println("if (val==0){");
			out.println("document.Form1.TXT_STATUS.value=\"Y\";");
			out.println("}");
			out.println("else if(val!=0){");
			out.println("document.Form1.TXT_STATUS.value=\"P\";");
			out.println("}");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("m_row1=obj.value");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest2(obj) {");
			out.println("m_row1=obj.value");
				
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_inquiry&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest3(row1,row) {");
			out.println(" assig('PR')");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");

			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("val=document.Form1.elements[pricing1].value;"); 
			out.println("m_row1=val");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_price&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function price_fields(row1,row){");
			out.println(" assig('PF')");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");

			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("val=document.Form1.elements[pricing1].value;"); 
			out.println("m_row1=val");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_price_fill&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest4(row1,row) {");
			out.println(" assig('CN')");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    con=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println("val=document.Form1.elements[con].value;"); 
			out.println("m_row1=val");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_conasst&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest5(row1,row) {");
			out.println(" assig('MAK')");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println(" make=\"text_make_desc_\"+row1+\"_\"+row;");
			out.println("val=document.Form1.elements[make].value;"); 
			out.println("m_row1=val");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_make&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest6(row1,row) {");
			out.println(" assig('MOD')");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println(" model=\"text_model_desc_\"+row1+\"_\"+row;");
			out.println("val=document.Form1.elements[model].value;"); 
			out.println("m_row1=val");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_model&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function monthly_det(valu) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_pricing_det&data_val=\"+valu;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function make_det(valu) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_make&data_val=\"+valu+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function model_det(valu) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_model1&data_val=\"+valu+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function conasst_det(valu) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_conasst&data_val=\"+valu+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function rate(obj) {");//check wether quotation should go to approval stage.
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_rate&data_val=\"+obj;");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println(" if(document.Form1.TXT_INQUIRY_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INQUIRY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//----modified by : delanjali-------------------------------------
			//----date				: 2007-08-06------------------------------------
			//----Ref No			: 768-------------------------------------------

			out.println(" if(document.Form1.TXT_AUTH_SIGNA.value==\"\"){  "); 
			out.println("DIV_TXT_AUTH_SIGNA.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_MK_OFFICER.value==\"\"){  "); 
			out.println("DIV_TXT_MK_OFFICER.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println(" if(document.Form1.TXT_AUTH_SIGNA_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_AUTH_SIGNA_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_MK_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_MK_OFFICER_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}");

			//----------------------------------------------------------------						
			out.println(" if(document.Form1.TXT_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_condition(){"); 
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){"); 
			out.println("   if(document.Form1.elements[\"chk_app_\"+d].checked==true){"); 
			out.println("b_flag=1");
			out.println("break");
			out.println("}"); 
			out.println("else{"); 
			out.println("b_flag=0");
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 



			out.println("function before_submit(){ "); 
			//out.println("check_condition()");
					//	out.println("hid_x_1=parseInt(hid_x_1)+parseInt(hid_x);");

			out.println("hid_x_1=parseInt(hid_x);");
			out.println("		if(validate_data()){"); 
			out.println("xx=parseInt(hid_x)-1");
			out.println("yy=parseInt(hid_y)-1");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("if ((hid_x<=0 && hid_y<=1)||(hid_x==1 && hid_y==0) || hid_y<=0){");
			out.println("alert('No Record')");
			out.println("		}"); 
			out.println("else {");
			out.println("var val_flag_1 = '0';"); // Flag Added By Samitha Kulatilaka On 2009-10-16
			out.println("for(var r=0;r<parseInt(hid_y);r++){");
			out.println("for(var q=0;q<parseInt(hid_x_1);q++){");
			// If Block Added By Samitha Kulatilaka On 2009-10-16
			out.println("if(val_flag_1 == '0') {");
			out.println("	if (document.Form1.elements[\"text_price_\"+r+\"_\"+q].value==''){");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("		b_flag1=0");
			out.println("		val_flag_1='1'");
			out.println("	}"); 
			// If Block Commented By Samitha Kulatilaka On 2009-10-16
			//out.println("	else if (document.Form1.elements[\"text_price_\"+r+\"_\"+q].value!=''){");
			//out.println("		b_flag1=1");
			//out.println("	}"); 
			out.println("	else if (document.Form1.elements[\"text_model_desc_\"+r+\"_\"+q].value==''){");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("		b_flag1=0");
			out.println("		val_flag_1='1'");
			out.println("	}");
			// If Block Commented By Samitha Kulatilaka On 2009-10-16
			//out.println("else if (document.Form1.elements[\"text_model_desc_\"+r+\"_\"+q].value==''){");
			//out.println("b_flag1=1");
			//out.println("		}"); 
			out.println("}");
			out.println("		}"); 
			out.println("		}"); 			
			
			// Commented By Samitha Kulatilaka On 2009-10-16
			//out.println("	if(b_flag1==1){");
			out.println("	if(val_flag_1=='0') {"); // Added By Samitha Kulatilaka On 2009-10-16
			
			
			out.println("document.Form1.hid_field.value ='RT'");
			//out.println("rate()");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_save_indicative_quotation?con_number='+document.Form1.hid_count.value+'&number='+hid_y+'&rowno='+hid_x_1+'&fuel='+document.Form1.hid_fuel.value+'&my_screen_name="+m_my_screen+"&option='+opt+'&screen_type="+m_screen_type+"';");  //Modified By Nuwan De Silva 12/12/06
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			out.println("		}"); 
			out.println("		}"); 
		
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_indicative_quotation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_indicative_quotation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_indicative_quotation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Marketing  - Quotation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Marketing  - Quotation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			//Modified by Mahela on 17-05-2007
			out.println("function mondetails(val,val1){");
			out.println("details.innerHTML='<td width=\"*%\" style=\"{font:bold;}\" >'+format_noobject(val)+'</td>';"); 
			out.println("month_123.innerHTML='<td width=\"*%\" ><b> Monthly Rental  - </td>';"); 
			out.println("details1.innerHTML='<td  width=\"*%\" style=\"{font:bold;}\" >'+format_noobject(val1)+'</td>';"); 
			out.println("vat.innerHTML='<td width=\"*%\" ><b> + VAT of  -  </td>';"); 
			out.println("details2.innerHTML='<td width=\"*%\" style=\"{font:bold;}\" ><b>'+m_xx+'</td>';"); 
			out.println("priod.innerHTML='<td width=\"*%\" ><b> Period  -  </td>';"); 
			out.println("details3.innerHTML='<tr><td width=\"*%\" >'+format_noobject(val)+'</td>';"); 
			out.println("intp.innerHTML='<td width=\"*%\" ><b> Initial Payment - </td>';");
			out.println("details4.innerHTML='<td width=\"*%\" style=\"{font:bold;}\" >'+format_noobject(val1)+'</td>';"); 
			out.println("vat1.innerHTML='<td width=\"*%\" ><b> + VAT of  -  </td>';"); 

			out.println("}"); 



			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INQUIRY_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value=\"Deactivate\";");  

			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_save.value=\"Reactivate\";");  

			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
	
			out.println("function view() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_display_quotation_select\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_indicative_quotation';"); 
			out.println("}");
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			
			
			out.println("		help_value_assign_3(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
			
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("		help_value_assign_8(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("		help_value_assign_9(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_value_assign_10(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"44\"){"); 
			out.println("		help_value_assign_auth();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"55\"){"); 
			out.println("		help_value_mk_officer();"); 
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
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();");
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"3\"){");

			out.println("pricing1=\"text_price_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("make=\"text_make_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("model=\"text_model_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("netamt=\"text_netamt_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("period=\"text_period_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			
			out.println("initpay=\"text_initpay_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("ipay=\"text_ipay_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("mrent=\"text_monrent_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("mrent1=\"text_monvat_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			
			out.println("con_det1=\"text_conasst_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("con_desc1=\"text_conasst_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			
			out.println("make_det1=\"text_make_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("make_desc1=\"text_make_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");

			out.println("model_det1=\"text_model_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("model_det2=\"text_model_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");

			out.println("document.Form1.elements[pricing1].value='';");
			out.println("document.Form1.elements[pricing1].focus();"); 
			out.println("document.Form1.elements[make].value='';");
			out.println("document.Form1.elements[model].value='';");
			out.println("document.Form1.elements[con_desc1].value='';");
			out.println("document.Form1.elements[netamt].value='';");
			out.println("document.Form1.elements[period].value='';");
			out.println("document.Form1.elements[make_det1].value='';");
			out.println("document.Form1.elements[make_desc1].value=''");
			out.println("document.Form1.elements[model_det1].value='';");
			out.println("document.Form1.elements[model_det2].value=''");
		
			out.println("document.Form1.elements[mrent].value='';");
			out.println("document.Form1.elements[mrent1].value='';");
			
			out.println("m_xx='';");
			out.println("mondetails('','') ");
			out.println("}");
			out.println("}");
		
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
		
				
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_INQUIRY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_INQUIRY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[2];"); 
			out.println("INQUIRY_NAME.innerHTML='<table><tr><td width=\"10%\" style=\"{font:bold;}\" >'+oBj.valout[3]+'</td></tr></table>';"); 
			out.println("}");
			
			
		  
			out.println("function help_value_assign_auth() {"); 
			out.println("    document.Form1.TXT_AUTH_SIGNA.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_AUTH_SIGNA_NAME.value=oBj.valout[3];"); 
			//out.println("AUTH_SIGNA_NAME.innerHTML='<table><tr><td width=\"10%\" style=\"{font:bold;}\" >'+oBj.valout[3]+'</td></tr></table>';"); 


			out.println("}");
			
			out.println("function clear_auth() {"); 
			out.println("    if(document.Form1.TXT_AUTH_SIGNA.value==\"\"){"); 
			out.println("    document.Form1.TXT_AUTH_SIGNA_NAME.value=\"\";"); 
			out.println("}");
			out.println("}");		
			
			out.println("function clear_mk() {"); 
			out.println("    if(document.Form1.TXT_MK_OFFICER.value==\"\"){"); 
			out.println("    document.Form1.TXT_MK_NAME.value=\"\";"); 
			out.println("}");
			out.println("}");		
			
		
			out.println("function help_button_mk_officer() {"); 
			out.println("    document.Form1.hid_help_type.value=\"55\";"); 
			//out.println("    m_sql = \"m_help_TXT_MK_OFFICER_sql\";");
			out.println("    m_sql = \"m_help_TXT_MK_OFFICER_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_MK_OFFICER.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_mk_officer() {"); 
			out.println("    document.Form1.TXT_MK_OFFICER.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_MK_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[9];"); 
			//out.println("MK_NAME.innerHTML='<table><tr><td width=\"10%\" style=\"{font:bold;}\" >'+oBj.valout[3]+'</td></tr></table>';"); 

			out.println("}");

			out.println("function help_button_3(row1,row) {"); 
			out.println("if(document.Form1.TXT_INQUIRY_NO.value==''){");
			out.println("alert('Please enter Inquiry no')");
			//Added by Disnaka Jayasuriya on 2009-10-15 for clear text_price text box
			out.println("    pricing=\"text_price_\"+row1+\"_\"+row;");
			out.println("document.Form1.elements[pricing1].value='';");
			//
			out.println("document.Form1.TXT_INQUIRY_NO.focus()");
			out.println("}");
			out.println("else{");
			out.println("    pricing=\"text_price_\"+row1+\"_\"+row;");
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_PRICING_NO_sql\";"); 
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");

			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@\"+document.Form1.elements[pricing].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','34');"); 
			out.println("}"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_3(row1,row) {"); 
			out.println("    pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println("    netamt=\"text_netamt_\"+row1+\"_\"+row;");
			out.println("    period=\"text_period_\"+row1+\"_\"+row;");
			out.println("    initpay=\"text_initpay_\"+row1+\"_\"+row;");
			out.println("    ipay=\"text_ipay_\"+row1+\"_\"+row;");
			
			out.println(" con_det1=\"text_conasst_\"+row1+\"_\"+row;");
			out.println(" con_desc1=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println(" document.Form1.elements[pricing1].value=oBj.valout[2];");
			//2007-01-31
			out.println("if (oBj.valout[5]=='null' || oBj.valout[5]=='' || oBj.valout[5]=='N/A'){");
			
			out.println("    document.Form1.elements[make].value='';");
			out.println("    document.Form1.elements[make].disabled=false;");
			out.println(" }");
			
			out.println("else{    document.Form1.elements[make].value=oBj.valout[5];");
			out.println(" }");
			
			out.println("if (oBj.valout[6]=='null' || oBj.valout[6]=='' || oBj.valout[6]=='N/A'){");
			out.println("    document.Form1.elements[model].value='';");
			out.println("    document.Form1.elements[model].disabled=false;");
			out.println(" }");
			out.println("else{    document.Form1.elements[model].value=oBj.valout[6];");
			out.println(" }");

			out.println("if (oBj.valout[16]=='null' || oBj.valout[16]==''){");
			out.println("    document.Form1.elements[con_desc1].value='';");
			out.println(" }");
			out.println("else{;");
			out.println("    document.Form1.elements[con_det1].value=oBj.valout[16];");
			out.println("    document.Form1.elements[con_desc1].value=oBj.valout[41];");
			out.println(" }");
			
			out.println("if (oBj.valout[29]=='null' || oBj.valout[29]==''){");
			out.println("    document.Form1.elements[netamt].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[netamt].value=oBj.valout[29];");
			out.println(" }");
		
			
			out.println("document.Form1.hid_field.value ='TN'");
			out.println("monthly_det(oBj.valout[2])");
			//Modified by Mahela on 17-05-2007
			out.println("document.Form1.elements[period].value=format_noobject(oBj.valout[24]);");
			out.println("	m_xx=oBj.valout[24]");
			out.println("document.Form1.hid_use.value=oBj.valout[16]");
			out.println("document.Form1.hid_fuel.value=oBj.valout[20]");
			out.println("row_check(row1,row,oBj.valout[2])");
			out.println("}"); 

			out.println("function fill_fields(data_vec) {"); 
			out.println("mrent=\"text_monrent_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("mrent1=\"text_monvat_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[mrent].value=format_noobject(data_vec[0]);");
			out.println("document.Form1.elements[mrent1].value=format_noobject(data_vec[1]);");
			out.println("document.Form1.elements[initpay].value=format_noobject(data_vec[0]);");
			out.println("document.Form1.elements[ipay].value=format_noobject(data_vec[1]);");
			out.println("mondetails(data_vec[0],data_vec[1]) ");
			out.println("document.Form1.hid_field.value ='MK'");
			out.println("make_det(oBj.valout[5])");
			out.println("}"); 
			
			out.println("function fill_make(data_vec) {");
			out.println("    pricing1=\"text_price_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" make_det1=\"text_make_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" make_desc1=\"text_make_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" document.Form1.elements[make_det1].value=data_vec[0];");
			out.println(" document.Form1.elements[make_desc1].value=data_vec[1];");
			out.println("document.Form1.hid_field.value ='MO'");
			out.println("model_det(oBj.valout[6])");
			out.println("}"); 


			out.println("function fill_model(data_vec) {"); 
			out.println(" model_det1=\"text_model_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" model_det2=\"text_model_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" document.Form1.elements[model_det1].value=data_vec[0];");
			out.println(" document.Form1.elements[model_det2].value=data_vec[1];");
			out.println("}");
			
			
			
			out.println("function fill_conasst(data_vec) {"); 
			out.println(" con_det1=\"text_conasst_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" con_desc1=\"text_conasst_desc_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println(" document.Form1.elements[con_det1].value=data_vec[0];");
			out.println(" document.Form1.elements[con_desc1].value=data_vec[1];");
			out.println("}"); 
			
						
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_OPTION_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_OPTION_ID.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_OPTION_ID.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5(row1,row) {"); 
			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[pricing1].value==''){");
			out.println("alert('Please enter Pricing no')"); 
			out.println("document.Form1.elements[pricing1].focus()");
			out.println("}");
			out.println("else{");
			out.println("condition=\"text_conasst_\"+row1+\"_\"+row;");
			out.println("condition_desc=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println("document.Form1.elements[condition].value	='';");
			out.println("document.Form1.hid_row1_no.value=row1;");
			out.println("document.Form1.hid_row_no.value=row;");
			out.println("document.Form1.hid_help_type.value=\"5\";"); 
			out.println("m_sql = \"m_help_TXT_CONDITION_OF_ASSET_sql\";"); 
			out.println("m_criteria = document.Form1.elements[condition].value+\"@\"+document.Form1.elements[condition_desc].value+\"@Y@\";"); 
			out.println("HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println("}");
			out.println(""); 

			out.println("function help_value_assign_5(row1,row) {"); 
			out.println("    condition=\"text_conasst_\"+row1+\"_\"+row;");
			out.println("    condition_desc=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println("    document.Form1.elements[condition].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[condition_desc].value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CATEGORY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_CATEGORY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_ITEM_CATEGORY.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7(oBj) {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8(row1,row) {"); 
			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[pricing1].value==''){");
			out.println("alert('Please enter Pricing no')"); 
			out.println("document.Form1.elements[pricing1].focus()");
			out.println("}");
			out.println("else{");
			out.println("make=\"text_make_\"+row1+\"_\"+row;");
			out.println("make_desc=\"text_make_desc_\"+row1+\"_\"+row;");
			out.println("document.Form1.elements[make].value='';");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[make].value+\"@\"+document.Form1.elements[make_desc].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println("}");
			out.println(""); 

			out.println("function help_value_assign_8(row1,row) {"); 
			out.println("make=\"text_make_\"+row1+\"_\"+row;");
			out.println("make_desc=\"text_make_desc_\"+row1+\"_\"+row;");
			out.println("document.Form1.elements[make].value=oBj.valout[2];"); 
			out.println("document.Form1.elements[make_desc].value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_9(row1,row) {"); 
			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[pricing1].value==''){");
			out.println("alert('Please enter Pricing no')"); 
			out.println("document.Form1.elements[pricing1].focus()");
			out.println("}");
			out.println("else{");
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println("    model_desc1=\"text_model_desc_\"+row1+\"_\"+row;");
			out.println("document.Form1.elements[model].value='';");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
		  out.println("    m_sql = \"m_help_TXT_MODEL_CODE2_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[model].value+\"@\"+document.Form1.elements[make].value+\"@\"+document.Form1.elements[model_desc1].value+\"@Y@\";");
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 
			out.println("}");
			out.println(""); 

			out.println("function help_value_assign_9(row1,row) {"); 
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println("    model_desc2=\"text_model_desc_\"+row1+\"_\"+row;");
			out.println("    document.Form1.elements[model].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[model_desc2].value=oBj.valout[3];");
			out.println("}"); 

			out.println("function help_button_10(row1,row,val) {"); 
			out.println("    pricing=\"text_price_\"+row1+\"_\"+row;");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_PRICING_NO_sql\";"); 
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    m_criteria = val+\"@Y@\";"); 

			out.println("    HelpBox('1','10','35');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_10(row1,row) {"); 
			out.println("    pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println("    netamt=\"text_netamt_\"+row1+\"_\"+row;");
			out.println("    period=\"text_period_\"+row1+\"_\"+row;");
			out.println("    initpay=\"text_initpay_\"+row1+\"_\"+row;");
			out.println("    ipay=\"text_ipay_\"+row1+\"_\"+row;");
			
			out.println(" con_det1=\"text_conasst_\"+row1+\"_\"+row;");
			out.println(" con_desc1=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println("    document.Form1.elements[pricing1].value=oBj.valout[2];");
			out.println("if (oBj.valout[4]=='null' || oBj.valout[4]==''){");
			out.println("    document.Form1.elements[make].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[make].value=oBj.valout[4];");
			out.println(" }");
			out.println("if (oBj.valout[5]=='null' || oBj.valout[5]==''){");
			out.println("    document.Form1.elements[model].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[model].value=oBj.valout[5];");
			out.println(" }");
			out.println("if (oBj.valout[14]=='null' || oBj.valout[14]==''){");
			out.println("    document.Form1.elements[con_desc1].value='';");
			out.println(" }");
			out.println("else{;");
			out.println("    document.Form1.elements[con_det1].value=oBj.valout[14];");
			out.println("    document.Form1.elements[con_desc1].value=oBj.valout[40];");
			out.println(" }");
			out.println("if (oBj.valout[28]=='null' || oBj.valout[28]==''){");
			out.println("    document.Form1.elements[netamt].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[netamt].value=oBj.valout[28];");
			out.println(" }");
		
			out.println("document.Form1.hid_field.value ='TN'");
			out.println("monthly_det(oBj.valout[2])");
			out.println("   document.Form1.elements[period].value=oBj.valout[22];");
			out.println("document.Form1.hid_use.value=oBj.valout[14]");
			out.println("}"); 



			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_QUOTATION_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[3];"); 
			out.println("}"); 
			
					
			
			out.println("function row_check(row1,row,val){");
			out.println("pricing1=\"text_price_\"+row1+\"_\"+row");
			out.println("qty=\"text_qty_\"+row1+\"_\"+row;");
			out.println("con=\"text_conasst_desc_\"+row1+\"_\"+row;");
			out.println("make=\"text_make_desc_\"+row1+\"_\"+row;");
			out.println("model=\"text_model_desc_\"+row1+\"_\"+row;");
			out.println("nt=\"text_netamt_\"+row1+\"_\"+row;");
			out.println("mr=\"text_monrent_\"+row1+\"_\"+row;");
			out.println("mv=\"text_monvat_\"+row1+\"_\"+row;");
			out.println("pr=\"text_period_\"+row1+\"_\"+row;");
			out.println("int=\"text_initpay_\"+row1+\"_\"+row;");
			out.println("ip=\"text_ipay_\"+row1+\"_\"+row;");
			out.println("makec=\"text_make_\"+row1+\"_\"+row;");
			out.println("modelc=\"text_model_\"+row1+\"_\"+row;");
  		out.println("for (var j=0;j<=row1;j++){"); 
			out.println("for (var k=0;k<=lineno;k++){");
			out.println("if(price_arry[k]==val && val_of!=\"200\" && val_of!=\"400\"){");
			out.println("alert('Pricing No is already entered')"); 
			out.println("m_use=\"1\";");
			out.println("document.Form1.elements[pricing1].value='';");
			out.println("document.Form1.elements[con].value='';");
			out.println("document.Form1.elements[make].value='';");
			out.println("document.Form1.elements[model].value='';");
			out.println("document.Form1.elements[makec].value='';");
			out.println("document.Form1.elements[modelc].value='';");
			out.println("document.Form1.elements[nt].value='';");
			out.println("document.Form1.elements[mr].value='';");
			out.println("document.Form1.elements[mv].value='';");
			out.println("document.Form1.elements[pr].value='';");
			out.println("document.Form1.elements[int].value='';");
			out.println("document.Form1.elements[ip].value='';");
			out.println("details.innerHTML=''");
			out.println("details1.innerHTML=''");
			out.println("details2.innerHTML=''");
			out.println("details3.innerHTML=''");
			out.println("details4.innerHTML=''");
			out.println("break");	
			out.println("}");
			out.println("else{");
			out.println("m_use=\"2\";");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");

			
			out.println("function row_disable(row1,row){");
			out.println("lineno=0");
			out.println("for (var i=0;i<=row;i++){"); 
			out.println("pricing1=\"text_price_\"+row1+\"_\"+i;");
			out.println("price_but=\"BUT_PRICING_NO_\"+row1+\"_\"+i;");
			out.println("qty=\"text_qty_\"+row1+\"_\"+i;");
			out.println("con=\"text_conasst_desc_\"+row1+\"_\"+i;");
			out.println("con_but=\"BUT_CONDITION_OF_ASSET_\"+row1+\"_\"+i;");
			out.println("make=\"text_make_desc_\"+row1+\"_\"+i;");
			out.println("make_but=\"BUT_MAKE_CODE_\"+row1+\"_\"+i;");
			out.println("model=\"text_model_desc_\"+row1+\"_\"+i;");
			out.println("model_but=\"BUT_MODEL_CODE_\"+row1+\"_\"+i;");
			out.println("nt=\"text_netamt_\"+row1+\"_\"+i;");
			out.println("mr=\"text_monrent_\"+row1+\"_\"+i;");
			out.println("mv=\"text_monvat_\"+row1+\"_\"+i;");
			out.println("pr=\"text_period_\"+row1+\"_\"+i;");
			out.println("int=\"text_initpay_\"+row1+\"_\"+i;");
			out.println("ip=\"text_ipay_\"+row1+\"_\"+i;");
			out.println("ad=\"add_\"+row1+\"_\"+i;");
			out.println("make_code=\"text_make_\"+row1+\"_\"+i;");
			out.println("model_code=\"text_model_\"+row1+\"_\"+i;");
			out.println("price_arry[lineno]=document.Form1.elements[pricing1].value");
			out.println("lineno=lineno+1");
			out.println("if(document.Form1.elements[pricing1].value=='' ){");
			out.println("document.Form1.elements[pricing1].focus()");
			out.println("}");
			out.println("if(document.Form1.elements[pricing1].value!=''){");
			out.println("document.Form1.elements[pricing1].disabled=true;"); 
			out.println("document.Form1.elements[price_but].disabled=true;"); 
			out.println("document.Form1.elements[qty].disabled=true;"); 
			out.println("document.Form1.elements[con].disabled=true;"); 
			out.println("document.Form1.elements[con_but].disabled=true;"); 
			out.println("if(document.Form1.elements[make_code].value=='' || document.Form1.elements[make].value==''=='N/A'){");
			out.println("document.Form1.elements[make_code].disabled=false;"); 
			out.println("document.Form1.elements[make].disabled=false;"); 
			out.println("document.Form1.elements[make_code].focus()");
			out.println("document.Form1.elements[ad].disabled=false;");
			out.println("}");
			out.println("else{");
			out.println("document.Form1.elements[make].disabled=true;"); 
			out.println("document.Form1.elements[ad].disabled=true;");
			out.println("document.Form1.elements[make_but].disabled=true;"); 
			out.println("}");
			out.println("if(document.Form1.elements[model_code].value=='' || document.Form1.elements[model_code].value==''=='N/A'){");
			out.println("document.Form1.elements[model].disabled=false;"); 
			out.println("document.Form1.elements[model_code].disabled=false;"); 
			out.println("document.Form1.elements[model_code].focus()");
			out.println("document.Form1.elements[ad].disabled=false;");
			out.println("}");
			out.println("else{");
			out.println("document.Form1.elements[model].disabled=true;"); 
			out.println("document.Form1.elements[ad].disabled=true;");
			out.println("document.Form1.elements[model_but].disabled=true;"); 
			out.println("}");
			out.println("document.Form1.elements[nt].disabled=true;"); 
			out.println("document.Form1.elements[mr].disabled=true;"); 
			out.println("document.Form1.elements[mv].disabled=true;"); 
			out.println("document.Form1.elements[pr].disabled=true;"); 
			out.println("document.Form1.elements[int].disabled=true;"); 
			out.println("document.Form1.elements[ip].disabled=true;");
			out.println("}"); 
			out.println("}"); 	
			out.println("}");
			
			
			
			out.println("function check_qty(row1,row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    qty=\"text_qty_\"+row1+\"_\"+row;");
			out.println("valno	=    document.Form1.elements[qty].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Quantity is wrong');");
			out.println("document.Form1.elements[qty].value='';"); 
		 	out.println("document.Form1.elements[qty].focus();"); 
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_period(row1,row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    prd=\"text_period_\"+row1+\"_\"+row;");
			out.println("valno	=    document.Form1.elements[prd].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
		
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Period is wrong');");
			out.println("document.Form1.elements[prd].value='';"); 
		 	out.println("document.Form1.elements[prd].focus();"); 
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			// Modified by Thamali Jayatunga on 2009.10.13, Modified function and replace format_noobject with format_number
			out.println("function check_ntm(row1,row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"text_netamt_\"+row1+\"_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(valno!='') {");
			out.println("format_number(document.Form1.elements[nt],21) ");
			//out.println("format_noobject(valno) ");
			out.println("}");
			out.println("}");
			
			out.println("function check(row1,row) {");
			out.println("pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[pricing1].value==''){"); 
			out.println("alert('Please enter Pricing no')");
			out.println("document.Form1.elements[pricing1].focus();"); 
			out.println("}");
			out.println("}");
			
			
			out.println("function load_price_screen(){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Price?chksql=main_page';"); 
			out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=900,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
		 	out.println("}");


			out.println("function load_inquiry_screen(){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Inquiry?chksql=main_page';"); 
			out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=1000,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
		 	out.println("}");
			
			out.println("function check_make(row1,row) {");//To validate Sub Model code.
			out.println("make_code=\"text_make_desc_\"+row1+\"_\"+row;");
			out.println("model_code=\"text_model_desc_\"+row1+\"_\"+row;");
			out.println("if(document.Form1.elements[make_code].value==''){"); 
			out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("window.event.keyCode='';	");
			out.println("alert('Please enter a make first')");
			out.println("document.Form1.elements[model_code].value='';");
			out.println("document.Form1.elements[make_code].focus();");
			out.println("}	");	
			out.println("}	");	
			out.println("}	");
			//-------------------------To add fields dynamically-------------------------------------------------------------------------
			//---------------------------------------------------------------------------------------------------------------------------
			out.println("function assign(bt) {");
			
			out.println("document.Form1.hid_bt_click.value=bt");
			out.println("}");
			
			
			out.println("function header() {");
			out.println("	change1.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("	'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table>';");
			out.println("}");			
			//-------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function load(row1,row) {");
			out.println("m_row = '<table >'+");
		 	out.println("	 '<tr><td><b>Option ('+opt+')</td>'+");

			out.println("  '<td><input class=\"mainbut\" type=button name=\"del_'+row1+'\" value=\"Delete Option\" onclick=option_del('+row1+','+row1+')></td>'+");

			out.println("'</tr>'+");
			out.println("'</table>'+");
			out.println("'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td align=\"left\" width=\"17%\"><b>Pricing No *</b></td>'+"); 
			out.println("'<td align=\"right\" width=\"6%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td align=\"left\" width=\"13%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"12%\" ><b>Make *</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"9%\" ><b>Model*</b></td>'+"); 
			out.println("'<td width=\"3%\" ></td>'+");
			out.println("'<td align=\"right\" width=\"9%\" ><b>Net Amount*</b></td>'+");
			out.println("'<td width=\"12%\" ></td>'+");
			out.println("'<td align=\"center\" width=\"10%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"*%\" ><b> Model Code*</b></td>'+"); 
			out.println("'</tr></table>'+");
			out.println("  '<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      out.println("  '<tr><td><input class=\"txt_input5\" type=text name=text_price_'+row1+'_'+row+' onblur=makeRequest3('+row1+','+row+')></td><td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_PRICING_NO_'+row1+'_'+row+' value=\"...\" onClick=\"help_button_3('+row1+','+row+')\"></td>'+");
		  out.println("  '<td ><input class=\"txt_input1\" type=text name=\"text_qty_'+row1+'_'+row+'\" onblur=check_qty('+row1+','+row+') value=\"1\" maximum=\"4\"></td>'+");
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+row1+'_'+row+' onblur=makeRequest4('+row1+','+row+')><input type=hidden name=\"text_conasst_'+row1+'_'+row+'\"></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=\"BUT_CONDITION_OF_ASSET_'+row1+'_'+row+'\" value=\"...\" onClick=\"help_button_5('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+row1+'_'+row+' onblur=makeRequest5('+row1+','+row+')></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=\"BUT_MAKE_CODE_'+row1+'_'+row+'\" value=\"...\" onClick=\"help_button_8('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" type=text name=\"text_model_desc_'+row1+'_'+row+'\" onblur=makeRequest6('+row1+','+row+') onkeypress=\"check_make('+row1+','+row+')\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=\"BUT_MODEL_CODE_'+row1+'_'+row+'\" value=\"...\" onClick=\"help_button_9('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\" name=\"text_netamt_'+row1+'_'+row+'\" onblur=\"check_ntm('+row1+','+row+')\"></td>'+");
      out.println("  '<td ><input class=\"but_input\" type=button name=\"add_'+row1+'_'+row+'\" value=Add onclick=\"load_new('+row1+','+row+'),row_disable('+row1+','+row+')\"></td>'+");
     	out.println("  '<td ><input class=\"but_input\" type=button name=\"del_'+row1+'_'+row+'\" value=Del onclick=load_price('+row1+','+row+')></td>'+");
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text disabled name=\"text_make_'+row1+'_'+row+'\" >'+");
			out.println("  '<td ><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text disabled name=\"text_model_'+row1+'_'+row+'\" >'+");
			out.println("	 '</tr>'+");
			out.println("	 '</table>'+");
			out.println("'<table width=\"80%\" cellspacing=\"1\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"month_123\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat\"></td><td  width=\"20%\" style=\"{ font:bold;}\" id=\"details1\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"priod\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details2\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"intp\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details3\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat1\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details4\"></td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_monrent_'+row1+'_'+row+'\"  size=\"50\"></td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monvat_'+row1+'_'+row+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_period_'+row1+'_'+row+'\" size=\"50\" onblur=check_period('+row1+','+row+')></td></tr>'+");
			out.println("  '<tr><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_initpay_'+row1+'_'+row+'\" size=\"50\"></td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_ipay_'+row1+'_'+row+'\"  size=\"50\"></td>'+");
			out.println("'<td><input type=hidden name=option_'+row+' value=1></td></tr></table>';");
			out.println("	change1.innerHTML=m_row; ");
			out.println("		document.Form1.hid_opt.value =  1;");
			out.println("hid_x=1;");
			out.println("hid_y=1;");
			out.println("}");
			//-------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function load_new(row1,row) {");//To add a row to an option.
			out.println("m_count=m_count+1;");
			out.println("val_of=\"100\"");
			out.println("m_x=0");
			out.println("hy=hid_y-1");
			out.println("hx=hid_x-1");
			out.println("pricing1=\"text_price_\"+hy+\"_\"+hx;");
			out.println("make_code=\"text_make_\"+hy+\"_\"+hx;");
			out.println("model_code=\"text_model_\"+hy+\"_\"+hx;");
			out.println("make_desc=\"text_make_desc_\"+hy+\"_\"+hx;");
			out.println("model_desc=\"text_model_desc_\"+hy+\"_\"+hx;");
			out.println("qty=\"text_qty_\"+hy+\"_\"+hx;");
			out.println("if(document.Form1.elements[pricing1].value==''){");
			out.println("alert('Please enter Pricing no')"); 
			out.println("document.Form1.elements[pricing1].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[qty].value==''){");
			out.println("alert('Please enter Quantity')"); 
			out.println("document.Form1.elements[qty].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[make_code].value=='' ){");
			out.println("alert('Please enter a make')");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.elements[model_code].value=='' ){");
			out.println("alert('Please enter a model')");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.elements[pricing1].value!='' && document.Form1.elements[qty].value!=''){");
			out.println("m_row = '';");
		 	out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
			out.println("opt=j+1");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+opt+')</td>'+");
			out.println("  '<td><input class=\"mainbut\" type=button name=\"del_'+j+'\" value=\"Delete Option\" onclick=option_del('+j+','+j+')></td>'+");
			out.println("	 '</tr>'+");
			out.println("'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td align=\"left\" width=\"17%\"><b>Pricing No *</b></td>'+"); 
			out.println("'<td align=\"right\" width=\"6%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td align=\"left\" width=\"13%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"12%\" ><b>Make *</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"9%\" ><b>Model*</b></td>'+"); 
			out.println("'<td width=\"3%\" ></td>'+");
			out.println("'<td align=\"right\" width=\"9%\" ><b>Net Amount*</b></td>'+");
			out.println("'<td width=\"12%\" ></td>'+");
			out.println("'<td align=\"center\" width=\"10%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"*%\" ><b> Model Code*</b></td>'+"); 
			out.println("'</tr></table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"></table>';"); 
			out.println("if(row1==j){	");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value)+1;");
			out.println("}	 ");
			out.println("else{");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("m_x=m_next");
			out.println("}");
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
			out.println("if(row1==j && i==parseFloat(m_next)-1){	");
			out.println("m_row =m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
     	out.println("  '<tr><td><input class=\"txt_input5\" type=text name=text_price_'+j+'_'+i+' onblur=makeRequest3('+j+','+i+')><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\"...\" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
      out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+j+'_'+i+' onblur=check_qty('+j+','+i+') maximum=\"4\" value=\"1\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+j+'_'+i+' onblur=makeRequest4('+j+','+i+')></td><td><input type=hidden name=\"text_conasst_'+j+'_'+i+'\"></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\"...\" onClick=\"help_button_5('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+j+'_'+i+' onblur=makeRequest5('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+j+'_'+i+' onblur=makeRequest6('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\"  name=text_netamt_'+j+'_'+i+' onblur=\"check_ntm('+j+','+i+')\"></td>'+");
			out.println("  '<td><input class=\"but_input\" type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=\"load_new('+j+','+m_next+'),row_disable('+j+','+i+')\"></td>'+");
      out.println("  '<td><input class=\"but_input\" type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text  name=\"text_make_'+j+'_'+i+'\" disabled></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text  name=\"text_model_'+j+'_'+i+'\" disabled></td>'+");
			out.println("	 '</tr>'+");
			out.println("'<table width=\"80%\" cellspacing=\"1\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"month_123\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat\"></td><td style=\"{ font:bold;}\" width=\"20%\" id=\"details1\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"priod\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details2\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"intp\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details3\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat1\"></td><td style=\"{ font:bold;}\" width=\"20%\" id=\"details4\"></td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+j+'_'+i+'\"  size=\"50\"></td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monvat_'+j+'_'+i+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+j+'_'+i+'\" size=\"50\"></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+j+'_'+i+'\" size=\"50\"></td><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_ipay_'+j+'_'+i+'\"  size=\"50\"></td></tr></table>';");
			out.println("}else{");
			out.println("m_row =m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
			out.println("  '<tr><td><input class=\"txt_input5\" type=text name=text_price_'+j+'_'+i+' onblur=makeRequest3('+j+','+i+') value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input class=\"but_input\" type=\"button\" style=\"width: 25px\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\"...\" onClick=\"help_button_3('+j+','+j+')\"></td>'+");
      out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+j+'_'+i+' onblur=check_qty('+j+','+i+') value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+' maximum=\"4\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+j+'_'+i+' onblur=makeRequest4('+j+','+i+') value=\"'+document.Form1.elements[\"text_conasst_desc_\"+j+\"_\"+i].value+'\"></td><td><input type=hidden name=\"text_conasst_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\"...\" onClick=\"help_button_5('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+j+'_'+i+' onblur=makeRequest5('+j+','+i+') value=\"'+document.Form1.elements[\"text_make_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+j+'_'+i+' onblur=makeRequest6('+j+','+i+') value=\"'+document.Form1.elements[\"text_model_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"{text-align=right;width: 82px}\" type=text maxlength=\"25\" name=text_netamt_'+j+'_'+i+' onblur=\"check_ntm('+j+','+i+')\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input class=\"but_input\" type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=\"load_new('+j+','+m_next+'),row_disable('+j+','+i+')\"></td>'+");
      out.println("  '<td><input class=\"but_input\" type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_make_'+j+'_'+i+' disabled value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_model_'+j+'_'+i+' disabled value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("	 '</tr>'+");
			out.println("'<table  width=\"80%\" cellspacing=\"1\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\"><b> Monthly Rental  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'</td><td style=\"{font:bold;}\" width=\"20%\" > + VAT of  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\"><b> Period  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\"><b> Initial Payment - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'</td><td style=\"{font:bold;}\" width=\"20%\" > + VAT of  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_monvat_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_ipay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
			out.println("}	");	
			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+i+'></table>';");
			out.println("hid_x=i;");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			out.println("hid_y=j;");
			out.println("}");
			out.println("		change1.innerHTML=m_row; ");
			out.println("}");
			out.println("hid_x_1=parseInt(hid_x);");
			out.println("}");
			out.println("}");
			//-------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function load_price(row1,row) {");//To delete a row in an option
			out.println("val_of=\"400\"");
			out.println("m_del=\"400\";");
			
			out.println("if(parseFloat(m_count)<1){"); 
			out.println("alert('Please delete with Option')"); 
			out.println("return false"); 
			out.println("}"); 
			out.println("else {"); 
			out.println("m_count=m_count-1;");
			out.println("m_count=parseFloat(m_count);");

			out.println("m_row = '';");
			out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
			out.println("opt=j+1");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+opt+')</td>'+");
			out.println("  '<td><input class=\"mainbut\" type=button name=\"del_'+j+'\" value=\"Delete Option\" onclick=option_del('+j+','+j+')></td>'+");
			out.println("	 '</tr>'+");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td align=\"left\" width=\"17%\"><b>Pricing No *</b></td>'+"); 
			out.println("'<td align=\"right\" width=\"6%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td align=\"left\" width=\"13%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"12%\" ><b>Make *</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"9%\" ><b>Model*</b></td>'+"); 
			out.println("'<td width=\"3%\" ></td>'+");
			out.println("'<td align=\"right\" width=\"9%\" ><b>Net Amount*</b></td>'+");
			out.println("'<td width=\"12%\" ></td>'+");
			out.println("'<td align=\"center\" width=\"10%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"*%\" ><b> Model Code*</b></td>'+"); 
			out.println("'</tr></table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"></table>';"); 
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("z=0;");
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
 			out.println("if(i==row && j==row1){");
 			out.println("}else{	");
 			out.println("m_val_ass=parseFloat(m_next)-1;	");
			out.println("m_row =m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
			out.println("  '<tr><td><input class=\"txt_input5\" type=text name=text_price_'+j+'_'+z+' onblur=makeRequest3('+j+','+z+') value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_PRICING_NO_'+j+'_'+z+' value=\"...\" onClick=\"help_button_3('+j+','+z+')\"></td>'+");
 			out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+j+'_'+z+' onblur=check_qty('+j+','+z+') value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+' maximum=\"4\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+j+'_'+z+' onblur=makeRequest4('+j+','+z+') value=\"'+document.Form1.elements[\"text_conasst_desc_\"+j+\"_\"+i].value+'\"><input type=hidden name=\"text_conasst_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+z+' value=\"...\" onClick=\"help_button_5('+j+','+z+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+j+'_'+z+' onblur=makeRequest5('+j+','+z+') value=\"'+document.Form1.elements[\"text_make_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+z+' value=\"...\" onClick=\"help_button_8('+j+','+z+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+j+'_'+z+' onblur=makeRequest6('+j+','+z+') value=\"'+document.Form1.elements[\"text_model_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+z+' value=\"...\" onClick=\"help_button_9('+j+','+z+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\"  name=text_netamt_'+j+'_'+z+' onblur=\"check_ntm('+j+','+z+')\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");
			out.println("'<td><input class=\"but_input\" type=button name=\"add_'+j+'_'+z+'\" value=Add onclick=\"load_new('+j+','+m_val_ass+'),row_disable('+j+','+z+')\"></td>'+");
 			out.println("'<td><input class=\"but_input\" type=button name=\"del_'+j+'_'+z+'\" value=Del onclick=load_price('+j+','+z+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_make_'+j+'_'+z+' disabled value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_model_'+j+'_'+z+' disabled value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("	 '</tr>'+");
			out.println("'<table  width=\"80%\" cellspacing=\"1\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Monthly Rental  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Period  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Initial Payment - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+j+'_'+z+'\"  size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monvat_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_ipay_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
 			out.println("z=z+1;");
 			out.println("}	");
 			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+z+'></table>';");
			out.println("hid_x=z;");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			out.println("hid_y=j;");
			out.println("change1.innerHTML=m_row; ");
			out.println("}");
			out.println("}");
			
			// Modified by Thamali Jayatunga on 2009.10.15, Modified alert message.
			//-------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function load_option(row1,row) {");//To add a option
			out.println("xx_x=hid_x-1");
			out.println("yy_y=hid_y-1");
			out.println("val_of=\"200\";");
			out.println("if(document.Form1.TXT_INQUIRY_NO.value==''){");
			out.println("alert('Please enter Inquiry no')"); 
			out.println("document.Form1.TXT_INQUIRY_NO.focus()");
			out.println("}");
			out.println("else{");
			out.println("price_code=\"text_price_\"+yy_y+\"_\"+xx_x;");
			out.println("make_code=\"text_make_\"+yy_y+\"_\"+xx_x;");
			out.println("model_code=\"text_model_\"+yy_y+\"_\"+xx_x;");
			out.println("make_desc=\"text_make_desc_\"+yy_y+\"_\"+xx_x;");
			out.println("model_desc=\"text_model_desc_\"+yy_y+\"_\"+xx_x;");
			out.println("if(document.Form1.elements[price_code].value=='' ){");
			out.println("alert('Please enter Pricing details for Option ('+(yy_y+1)+')')");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.elements[make_code].value=='' ){");
			out.println("alert('Please enter make details')");
			out.println("}");
			out.println("else{");
			out.println("if(document.Form1.elements[model_code].value=='' ){");
			//modified by madhawa 2009-10-15 change the alert msg
			out.println("alert('Please enter model details')");
			out.println("}");
			out.println("else{");
			out.println("m_del=\"0\"");
			out.println("m_row = '';");
		  out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value)+1;j++){");
			out.println("opt=j+1");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+opt+')</td>'+");
			out.println("  '<td><input class=\"mainbut\" type=button name=\"del_'+j+'\" value=\"Delete Option\" onclick=option_del('+j+','+j+')></td>'+");
			out.println("	 '</tr>'+");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td align=\"left\" width=\"17%\"><b>Pricing No *</b></td>'+"); 
			out.println("'<td align=\"right\" width=\"6%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td align=\"left\" width=\"13%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"12%\" ><b>Make *</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"9%\" ><b>Model*</b></td>'+"); 
			out.println("'<td width=\"3%\" ></td>'+");
			out.println("'<td align=\"right\" width=\"9%\" ><b>Net Amount*</b></td>'+");
			out.println("'<td width=\"12%\" ></td>'+");
			out.println("'<td align=\"center\" width=\"10%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"*%\" ><b> Model Code*</b></td>'+"); 
			out.println("'</tr></table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"></table>';");
			out.println("if(parseFloat(document.Form1.hid_opt.value)==j){");
		  out.println("m_next=1;");
			out.println("}	 ");
			out.println("else{");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("}");
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
			out.println("if(parseFloat(document.Form1.hid_opt.value)==j){");
			out.println("m_row = m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
		  out.println("'<tr><td><input class=\"txt_input5\" type=text name=text_price_'+j+'_'+i+' onblur=makeRequest3('+j+','+i+')><input class=\"but_input\" type=\"button\" style=\"width: 25px\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\"...\" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
 			out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+j+'_'+i+' onblur=check_qty('+j+','+i+') maximum=\"4\" value=\"1\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+j+'_'+i+' onblur=makeRequest4('+j+','+i+')><input type=hidden name=\"text_conasst_'+j+'_'+i+'\"></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\"...\" onClick=\"help_button_5('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+j+'_'+i+'  onblur=makeRequest5('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+j+'_'+i+' onblur=makeRequest6('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\" name=text_netamt_'+j+'_'+i+' onblur=\" check_ntm('+j+','+i+')\"></td>'+");
			out.println("'<td><input class=\"but_input\" type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=\"load_new('+j+','+m_next+'),row_disable('+j+','+i+')\"></td>'+");
 			out.println("'<td><input class=\"but_input\" type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" disabled style=\"width: 82px\" maxlength=\"10\" type=text name=\"text_make_'+j+'_'+i+'\">'+");
			out.println("  '<td><input class=\"txt_input2\" disabled style=\"width: 82px\" maxlength=\"10\" type=text name=\"text_model_'+j+'_'+i+'\">'+");
			out.println("	 '</tr>'+");
			out.println("'<table width=\"80%\" cellspacing=\"1\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"month_123\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat\"></td><td style=\"{ font:bold;}\" width=\"20%\" id=\"details1\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"priod\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details2\"></td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" id=\"intp\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"details3\"></td><td width=\"20%\" style=\"{ font:bold;}\" id=\"vat1\"></td><td style=\"{ font:bold;}\" width=\"20%\" id=\"details4\"></td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+j+'_'+i+'\"  size=\"50\"></td><td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_monvat_'+j+'_'+i+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+j+'_'+i+'\" size=\"50\"></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+j+'_'+i+'\" size=\"50\"></td><input class=\"txt_input\" style=\"{text-align=right}\" type=hidden name=\"text_ipay_'+j+'_'+i+'\"  size=\"50\"></td></tr></table>';");
			out.println("}else{");
			out.println("m_row = m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
 	    out.println("'<tr><td><input class=\"txt_input5\" type=text name=text_price_'+j+'_'+i+' onblur=makeRequest3('+j+','+i+') value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\"...\" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
			out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+j+'_'+i+' onblur=check_qty('+j+','+i+') value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+' maximum=\"4\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+j+'_'+i+' onblur=makeRequest4('+j+','+i+') value=\"'+document.Form1.elements[\"text_conasst_desc_\"+j+\"_\"+i].value+'\"><input type=hidden name=\"text_conasst_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\"...\" onClick=\"help_button_5('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+j+'_'+i+' onblur=makeRequest5('+j+','+i+') value=\"'+document.Form1.elements[\"text_make_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+j+'_'+i+' onblur=makeRequest6('+j+','+i+') value=\"'+document.Form1.elements[\"text_model_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\"...\" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\" name=text_netamt_'+j+'_'+i+' onblur=\" check_ntm('+j+','+i+')\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");
			out.println("'<td><input class=\"but_input\" type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=\"load_new('+j+','+m_next+'),row_disable('+j+','+i+')\"></td>'+");
 			out.println("'<td><input class=\"but_input\" type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text disabled name=\"text_make_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text disabled name=\"text_model_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+'>'+");
			out.println("'</tr>'+");
			out.println("'<table  width=\"80%\" cellspacing=\"0\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Monthly Rental  - </td><td  width=\"20%\"><b>'+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Period  - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Initial Payment - </td><td width=\"20%\"><b>'+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_monvat_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td><td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_ipay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
			out.println("}		");	
			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+i+'></table>';");
			out.println("hid_x=i;");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			out.println("hid_y=j;");
			out.println("change1.innerHTML=m_row; ");
			out.println("m_count=0");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			
			//-------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function option_del(row1,row) {");//To delete an option	
		  out.println("m_del=\"300\";");
			out.println("m_row = '';");
			out.println("z=0;");
			out.println("if(row1==0 && row==0 & hid_x==1 && hid_y==1){");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_indicative_quotation';"); 
			out.println("}");
			out.println("else{");
		  out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
			out.println("opt=z+1");
			out.println("opt=parseFloat(opt)");
 			out.println("if(j==row1){");
			out.println("}else{	");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+opt+')</td>'+");
			out.println("  '<td><input class=\"mainbut\" type=button name=\"del_'+z+'\" value=\"Delete Option\" onclick=option_del('+z+','+z+')></td>'+");
			out.println("	 '</tr>'+");
			out.println("'</table>';");
			out.println("m_row = m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td align=\"left\" width=\"17%\"><b>Pricing No *</b></td>'+"); 
			out.println("'<td align=\"right\" width=\"6%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td align=\"left\" width=\"13%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"12%\" ><b>Make *</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"9%\" ><b>Model*</b></td>'+"); 
			out.println("'<td width=\"3%\" ></td>'+");
			out.println("'<td align=\"right\" width=\"9%\" ><b>Net Amount*</b></td>'+");
			out.println("'<td width=\"12%\" ></td>'+");
			out.println("'<td align=\"center\" width=\"10%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td align=\"center\" width=\"*%\" ><b> Model Code*</b></td>'+"); 
			out.println("'</tr></table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"></table>';"); 
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
	  	out.println("for(i=0;i<parseFloat(m_next);i++){");
			out.println("m_val_ass=parseFloat(m_next);	");
			out.println("m_row = m_row+'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+"); 
	    out.println("'<tr><td><input class=\"txt_input5\" type=text name=text_price_'+z+'_'+i+' onblur=makeRequest3('+z+','+i+') value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+' ><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_PRICING_NO_'+z+'_'+i+' value=\"...\" onClick=\"help_button_3('+z+','+i+')\"></td>'+");
			out.println("  '<td><input class=\"txt_input1\" type=text name=text_qty_'+z+'_'+i+' onblur=check_qty('+z+','+i+') value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+' maximum=\"4\"></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_conasst_desc_'+z+'_'+i+' onblur=makeRequest4('+z+','+i+') value=\"'+document.Form1.elements[\"text_conasst_desc_\"+j+\"_\"+i].value+'\"><input type=hidden name=\"text_conasst_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'></td>'+");
			out.println("	 '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_CONDITION_OF_ASSET_'+z+'_'+i+' value=\"...\" onClick=\"help_button_5('+z+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_make_desc_'+z+'_'+i+' onblur=makeRequest5('+z+','+i+') value=\"'+document.Form1.elements[\"text_make_desc_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MAKE_CODE_'+z+'_'+i+' value=\"...\" onClick=\"help_button_8('+z+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" type=text name=text_model_desc_'+z+'_'+i+' onblur=makeRequest6('+z+','+i+') value='+document.Form1.elements[\"text_model_desc_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input class=\"but_input\" style=\"width: 25px\" type=\"button\" name=BUT_MODEL_CODE_'+z+'_'+i+' value=\"...\" onClick=\"help_button_9('+z+','+i+')\"></td>'+"); 
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" style=\"{text-align=right}\" type=text maxlength=\"25\" name=text_netamt_'+z+'_'+i+' onblur=\"check_ntm('+z+','+i+')\" value=\"'+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'\"></td>'+");
			out.println("'<td><input class=\"but_input\" type=button name=\"add_'+z+'_'+i+'\" value=Add onclick=\"load_new('+z+','+m_val_ass+'),row_disable('+z+','+i+')\"></td>'+");
 			out.println("'<td><input class=\"but_input\" type=button name=\"del_'+z+'_'+i+'\" value=Del onclick=load_price('+z+','+i+')></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_make_'+z+'_'+i+' disabled value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("  '<td><input class=\"txt_input2\" style=\"width: 82px\" maxlength=\"10\" type=text name=text_model_'+z+'_'+i+' disabled value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+' ></td>'+");
			out.println("	 '</tr>'+");
			out.println("'<table  width=\"80%\" cellspacing=\"0\" class=\"table\" border=\"0\">'+");
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Monthly Rental  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Period  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'<tr><td width=\"20%\" style=\"{ font:bold;}\" ><b> Initial Payment - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'</td><td style=\"{ font:bold;}\" width=\"20%\" ><b> + VAT of  - </td><td width=\"20%\" ><b>'+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'</td></tr>'+"); 
			out.println("'</table>'+"); 
			out.println("  '<table><tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_monrent_'+z+'_'+i+'\"  size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input style=\"{text-align=right}\" class=\"txt_input2\" type=hidden name=\"text_monvat_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_period_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td><input class=\"txt_input2\" style=\"{text-align=right}\" type=hidden name=\"text_initpay_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input style=\"{text-align=right}\" class=\"txt_input\" type=hidden name=\"text_ipay_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+z+' value='+i+'></table>';");
			out.println("hid_x=i;");
			out.println("z=z+1;");
			out.println("}");
			out.println("}	");
			out.println("document.Form1.hid_opt.value=z");
			out.println("hid_y=z;");
			out.println("change1.innerHTML=m_row; ");
			out.println("}");
			out.println("}");
			
			
			
			out.println("function close_screen() {");
			
			if(m_screen_type==null){
			out.println("		     close_window()");
			}
			else{
					out.println("window.close()"); 
			}
			
			out.println("}");
			
			out.println("function change_app(row) {"); 
			out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
			out.println("}");
			out.println("}"); 			
			
			out.println("function help_button_auth() {"); 
			out.println("    document.Form1.hid_help_type.value=\"44\";"); 
			//out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_new_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_AUTH_SIGNA.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			


			//-------------------------------------------------------------------------------------------------------------------------------------
			//-------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New'),load_lock(),load(x,y)\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_opt' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_bt_click' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row1_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_field' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_use' value=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_rate' value=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_fuel' value=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' value=\"Save\">"); 
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Indicative Quotation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"HELP\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
			out.println("<td>&nbsp</td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Pricing\");' onClick='load_screen_status(\"PRICING\"),load_price_screen()' value=\"Pricing\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Inquiry\");' onClick='load_screen_status(\"INQUIRY\"),load_inquiry_screen()' value=\"Inquiry\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" style='width:120' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Quotation\");' onClick='load_screen_status(\"VIEW\"),view()' value=\"View Quotation\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1'></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 
			out.println("<tr>"); 
			out.println("<br>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_INQUIRY_NO' class=div_input><b>Inquiry No *</DIV></td>"); 
			if(m_inq_no!=null){
			  
			  out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_INQUIRY_NO' maxlength='15' size='15' onblur=\"assig('IN'),makeRequest2(document.Form1.TXT_INQUIRY_NO)\" value=\""+m_inq_no+"\"></td>"); 
			}else{
			  out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_INQUIRY_NO' maxlength='15' size='15' onblur=\"assig('IN'),makeRequest2(document.Form1.TXT_INQUIRY_NO)\" ></td>"); 
      }
			out.println("<td width='*%' ><input class='but_input' type='button' name='BUT_TXT_INQUIRY_NO' value=\"...\" onClick=\" help_button_2()\"></td>"); 
			out.println("<td width='70%' id=\"INQUIRY_NAME\" ></td>"); 
			out.println("<td width='30%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_AUTH_SIGNA' class=div_input><b>Authorised Signatory *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_AUTH_SIGNA' maxlength='250' size='15' onblur=\"help_button_auth()\" ></td>"); //clear_auth()
			out.println("<td width='*%' ><input class='but_input' type='button' name='BUT_TXT_AUTH_SIGNA' value=\"...\" onClick=\" help_button_auth()\"></td>"); 
			//----modified by : delanjali------------------------------------------------------------------------------------------------------------------
			//----date				: 2007-08-06-----------------------------------------------------------------------------------------------------------------
			//----Ref No			: 768------------------------------------------------------------------------------------------------------------------------
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_AUTH_SIGNA_NAME' class=div_input><b>Signatory Name *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_AUTH_SIGNA_NAME' maxlength='250' size='15'></td>"); 
			//-------------------------------------------------------------------------------------------------------------------------------------------------
			//out.println("<td width='70%' id=\"AUTH_SIGNA_NAME\" ></td>"); 

		//	out.println("<td width='70%'></td>"); 
			//out.println("<td width='30%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_MK_OFFICER' class=div_input><b>Makerting Officer *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_MK_OFFICER' maxlength='250' size='15' onblur=\"clear_mk()\" ></td>"); 
			//----modified by : delanjali------------------------------------------------------------------------------------------------------------------
			//----date				: 2007-08-06-----------------------------------------------------------------------------------------------------------------
			//----Ref No			: 768------------------------------------------------------------------------------------------------------------------------
					out.println("<td width='*%' ><input class='but_input' type='button' name='BUT_TXT_MK_OFFICER' value=\"...\" onClick=\" help_button_mk_officer()\"></td>"); 
			
					out.println("</tr>"); 
									out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_MK_OFFICER_NAME' class=div_input><b>Officer Name *</DIV></td>"); 
								
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_MK_NAME' maxlength='250' size='15' onblur=\"\" ></td>"); 
			//out.println("<td width='70%' id=\"MK_NAME\" ></td>"); 
		//	out.println("<td width='70%'></td>"); 
			//out.println("<td width='30%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TEL_NO' class=div_input><b>Tel No *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='10' size='15' onblur=\"\" ></td>"); 
			out.println("<td width='*%' ></td>"); 
			out.println("<td width='70%'></td>"); 
			out.println("<td width='30%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_QUOTATION_NO' maxlength='15' size='15' onblur=\"assig('QT'),makeRequest(document.Form1.TXT_QUOTATION_NO)\">"); 
			out.println("<input class='but_input' type='hidden' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td ><input class='txt_input' type='hidden' name='TXT_STATUS' maxlength='1' size='1'>"); 
			out.println("</tr>"); 
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			
			out.println("<td width='30%'><input class='mainbut' type=\"button\" name=BUT_MORE value=\" Add Options \" onClick=\"load_option(document.Form1.hid_opt.value,0)\" ></td></tr></table>");
			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=change1></div></td></tr></table>");
			out.println("<br>"); 
			
			//*********************************************************************************************************************************
			int i=0;
			rs = stmt.executeQuery("SELECT CODE,DESCRIPTION,ACTIVE_STATUS,INSERT_SCREEN "+
			"FROM "+m_schema_name+".AF_MK_CONDITIONS WHERE INSERT_SCREEN='AF_AD_INDICATIVE_QUOTATION' ");
			
			boolean more = rs.next();
	
		
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		
			out.println("<tr class=pdn_txtpos2>");

			out.println("<td width='25%' >Code</td>"); 
			out.println("<td width='25%' >Description</td>"); 
			out.println("<td width='25%' align=center>Approve</td>"); 
			
			out.println("</tr >"); 

	    int j = 0; 			

			while(more){
					
			i=0;		

			
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			

			out.println("<td width='25%' >"+rs.getString(1)+"<input class='txt_input' type='hidden' name=TXT_CODE_"+j+" maxlength='15' size='15' value=\""+rs.getString(1)+"\"></td>");
			out.println("<td width='25%' >"+rs.getString(2)+"<input class='txt_input' type='hidden' name=TXT_DESC_"+j+" maxlength='100' size='100' value=\""+rs.getString(2)+"\"></td>");
			out.println("<td width='25%' align=center><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change_app("+j+")\"></td>"); 

			out.println("</tr>");

			more=rs.next();
			j=j+1;
		
			if (!more)
			{
			break;
			}

			}	

			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");

		
			
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
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			  if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			  if(conn  !=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
