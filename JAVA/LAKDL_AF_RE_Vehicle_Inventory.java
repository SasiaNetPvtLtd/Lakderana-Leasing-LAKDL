//Created by Nuwan De Silva
//Collection - Vehicle Inventory Process.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Vehicle_Inventory extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
		//	LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
	//		m_chksql         = req.getParameter("chksql");
	//		m_ac_status = req.getParameter("ac_status");
		//	stmt = conn.createStatement ();
		///	stmt1 = conn.createStatement ();
		    //   String m_sort_column   = "ENT_DATE";	
				//	 String m_order_by_type = "ASC";
			
     
			  String m_reposs_no    = req.getParameter("rep_no");//Added By Sandun on 02-07-2009
			  String m_inventry_no  = req.getParameter("inv_no");//Added By Sandun on 02-07-2009
				String m_code         = req.getParameter("code");//Added By Sandun on 02-07-2009
			  String m_name         = req.getParameter("name");//Added By Sandun on 02-07-2009
				String m_fee          = req.getParameter("fee");//Added By Sandun on 02-07-2009
			
			
			
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
							
				out.println("var count=0;");
				
			out.println("function get_vector(data_vec) {");
					
			out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M1'  && document.Form1.TXT_REPOSSESSION_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_REPOSSESSION_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[2];");
			out.println("get_customer_data(data_vec[2]);");
			
			//out.println("if(data_vec[2]!='null')");
			//out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[3];"); 
			
			out.println("if( document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=false;"); 
			out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=false;"); 
			out.println("			}");
			out.println("			else{");
			//out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			out.println("    document.Form1.BUT_TXT_INVENTORY_NO.disabled=false;"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;"); 

			out.println("			}");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1' && document.Form1.TXT_REPOSSESSION_CODE.value!=\"\"){");
			out.println("     help_button_1();");
      out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2'){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[1];"); 
			out.println("get_vehicle_number();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_VEHICLE_NO.value!=\"\"){");
			out.println("     help_button_2();");
      out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'){");
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[2];"); 
			out.println("get_data();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_VEHICLE_NO.value!=\"\"){");
			out.println("     display_data(data_vec);");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M7' && document.Form1.TXT_YARD_CODE.value!=\"\"){");
			out.println("     help_button_yard();");
      out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M7'){");
			out.println("    document.Form1.TXT_YARD_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_YARD_NAME.value=data_vec[1];"); 
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.TXT_INVENTORY_NO.value!=\"\"){");
			out.println("     help_button_inventory();");
      out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M6'){");
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=data_vec[1];"); 
			out.println("    get_customer_data(data_vec[2])");
			out.println("			}");

						
			out.println("			}");
			
			
			
			out.println("function get_vehicle_number(){");
			out.println("assignState('M3');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no_edit&data_val=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=ENT\";");
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\"))");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no_edit&data_val=\"+obj.value+\"&ac_status=ENT\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M3')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_SEIZER_CODE.value+\"&ac_status=VERIFY2&ac_status2=ACTIVATED\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_REPOSSESSION_CODE.value+\"&ac_status=VERIFY2&ac_status2=ACTIVATED\";");
			
						
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_Yard_code&data_val=\"+obj.value+\"&ac_status=Y\";");

			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M6')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_no&data_val2=\"+obj.value+\"&data_val=\"+document.Form1.TXT_REPOSSESSION_CODE.value+\"&ac_status=ENT\";");
					
		//	out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
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
						
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Start+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_data();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_button_inventory_assign();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		help_value_assign_yard();"); 
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
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
			out.println("	}");
			
			
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
			
			
			
			
			out.println("function clear_data(IfCount) {");
			
		
		
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_REPOSSESSION_CODE.value='';");
		
			out.println("	}");
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("document.Form1.TXT_INVENTORY_NO.value='';"); 
			
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_VEHICLE_NO.value='';"); 
			
			out.println("	}");
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("document.Form1.TXT_YARD_CODE.value='';"); 
			
			out.println("	}");
			
				
			out.println("}");
			

			out.println("function get_customer_data(val) {");

      out.println("assignState('M2')");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_customer_Data&data_val=\"+val+\"&ac_status=Y\";");	
			
			out.println("load_interface(m_url,'XML');");
			
			//out.println("window.open(m_url);");
				
			out.println("}");



			
			
			
			
			out.println("function help_button_1() {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" ){ ");
			out.println("    Crit = document.Form1.TXT_REPOSSESSION_CODE.value+\"@ENT@\";"); 
			out.println("    HelpBox('0','10','0',Crit,'m_help_edit_Repossseion_Help','1');"); //m_help_edit_Repossseion_Help   m_help_TXT_REPOSSESSION_NO_edit_sql
			out.println("}"); 
			out.println("else{"); 
			out.println("    Crit = document.Form1.TXT_REPOSSESSION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('0','10','0',Crit,'m_help_Repossseion_Help','1');"); //m_help_Repossseion_Help m_help_TXT_REPOSSESSION_NO_sql
			out.println("}"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" ){ ");
			out.println("    document.Form1.TXT_REPOSSESSION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];");  //ADDED BY NWUAN DE SILVA ON 02-11-07
			//out.println("get_customer_data(oBj.valout[4])");
			out.println("    document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			out.println("    document.Form1.BUT_TXT_INVENTORY_NO.disabled=false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("    document.Form1.TXT_REPOSSESSION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[4];");  //ADDED BY NWUAN DE SILVA ON 02-11-07
			//out.println("    alert('Seizer **'+oBj.valout[4]);");
			
			out.println("if(oBj.valout[7]=='null'){");
			out.println("oBj.valout[7]=''");
			out.println("}");
			
			out.println("    document.Form1.TXT_SEIZER_NAME.value=oBj.valout[7];"); 
			
			//----MODIFIED BY:DELANJALI----------------------------------------------	
			//----date			: 2007-07-11---------------------------------------------
			
			out.println("if(oBj.valout[8]=='null'){");
			out.println("oBj.valout[8]=''");
			out.println("}");
			
			out.println("    document.Form1.TXT_INVOICE_AMOUNT.value=oBj.valout[8];"); 

			
			out.println("get_customer_data(oBj.valout[4])");

			out.println("}"); 
			
			out.println("if(document.Form1.TXT_SEIZER_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=false;"); 
      out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=false;}"); 

						
			out.println("}"); 
			
			
			out.println("function help_button_inventory() {"); 
			
			//out.println("    Crit = document.Form1.TXT_INVENTORY_NO.value+\"@ENT@\";"); 
			out.println("    Crit =document.Form1.TXT_INVENTORY_NO.value+\"@\"+document.Form1.TXT_REPOSSESSION_CODE.value+\"@ENT@\";"); 
			out.println("    HelpBox('0','10','0',Crit,'m_help_TXT_REPOSSESSION_NO_inv_no_sql','4');"); 
		
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_button_inventory_assign() {"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[4];");
			out.println("if(oBj.valout[3]=='null' || oBj.valout[3]=='-'  ){");
			out.println("oBj.valout[3]='';");
			out.println("}");
			
			out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[3];"); 
			
			out.println("if(oBj.valout[5]=='null' || oBj.valout[5]=='-'  ){");
			out.println("oBj.valout[5]='';");
			out.println("}");
			
			out.println("    document.Form1.TXT_SEIZER_NAME.value=oBj.valout[5];");
			
			//----MODIFIED BY:DELANJALI----------------------------------------------	
			//----date			: 2007-07-11---------------------------------------------
			
			out.println("if(oBj.valout[6]=='null' || oBj.valout[6]=='-'  ){");
			out.println("oBj.valout[6]='';");
			out.println("}");
			
			out.println("    document.Form1.TXT_INVOICE_AMOUNT.value=oBj.valout[6];"); 

			out.println("get_customer_data(oBj.valout[4])");
			
			
			out.println("if( document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=false;"); 
			out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=false;"); 
			out.println("			}");
			out.println("			else{");
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;"); 

			out.println("			}");
			
			/*out.println("if(document.Form1.TXT_SEIZER_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
      out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;}"); 
			*/
						
			out.println("}"); 
			
			
			
			
			out.println("function display_data(data_vec){");
			//added by nuwan de silva on 09-10-07------------------------------
			out.println("if(data_vec[0]=='null' || data_vec[0]=='-' ){");
			out.println("    document.Form1.TXT_ASSET_DESCRIPTION.value='';"); 
			out.println("}");
			out.println("else {");
			out.println("    document.Form1.TXT_ASSET_DESCRIPTION.value=data_vec[0];"); 
			out.println("}");
			
			out.println("if(data_vec[1]=='null' || data_vec[1]=='-' ){");
			out.println("    document.Form1.TXT_MILEGE.value='';"); 
			out.println("}");
			out.println("else {");
			out.println("    document.Form1.TXT_MILEGE.value=data_vec[1];"); 
			out.println("}");
			
			out.println("if(data_vec[2]=='null' || data_vec[2]=='-' ){");
			out.println("    document.Form1.TXT_COMMENTS.value='';"); 
			out.println("}");
			out.println("else {");
			out.println("    document.Form1.TXT_COMMENTS.value=data_vec[2];"); 
			out.println("}");
			
			
			
			//out.println("    document.Form1.TXT_MILEGE.value=data_vec[1];"); 
			//out.println("    document.Form1.TXT_ADVERTISMENT_STATUS.value=data_vec[2];"); 
			//out.println("    document.Form1.TXT_OFFER_STATU.value=data_vec[3];"); 
   //   out.println("    document.Form1.TXT_COMMENTS.value=data_vec[2];"); 
			
			
			out.println("if(data_vec[3]=='Y'){");
      out.println("    document.Form1.TXT_KEY_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_KEY_ON.value='on';"); 	
			out.println("}else if(data_vec[3]=='N'){");
			out.println("   document.Form1.TXT_KEY_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_KEY_OFF.value='on';}"); 	 
			
			//added by nuwan de silva on 06-11-07----------------------
			out.println("if(data_vec[31]=='Y'){");
      out.println("    document.Form1.TXT_D_KEY_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_D_KEY_ON.value='on';"); 	
			out.println("}else if(data_vec[31]=='N'){");
			out.println("   document.Form1.TXT_D_KEY_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_D_KEY_OFF.value='on';}"); 	 
			
			out.println("if(data_vec[4]=='Y'){");
   		out.println("    document.Form1.TXT_LICENSE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LICENSE_ON.value='on';"); 	
			out.println("}else if(data_vec[4]=='N'){");
			out.println("    document.Form1.TXT_LICENSE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LICENSE_OFF.value='on';}"); 	

			out.println("if(data_vec[5]=='Y'){");
   		out.println("    document.Form1.TXT_INSURANCE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_INSURANCE_ON.value='on';"); 	
			out.println("}else if(data_vec[5]=='N'){");
			out.println("    document.Form1.TXT_INSURANCE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_INSURANCE_OFF.value='on';}"); 	
			
			
			out.println("if(data_vec[6]=='Y'){");
      out.println("    document.Form1.TXT_VEHICLE_ID_CARD_ON.checked=true;"); 
			out.println("    document.Form1.TXT_VEHICLE_ID_CARD_ON.value='on';"); 
			out.println("}else if(data_vec[6]=='N'){");
			out.println("    document.Form1.TXT_VEHICLE_ID_CARD_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_VEHICLE_ID_CARD_OFF.value='on';}"); 
			
			out.println("if(data_vec[7]=='Y'){");
      out.println("    document.Form1.TXT_CASSETTE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_CASSETTE_ON.value='on';"); 
			out.println("}else if(data_vec[7]=='N'){");
			out.println("    document.Form1.TXT_CASSETTE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CASSETTE_OFF.value='on';}"); 
			
			out.println("if(data_vec[8]=='Y'){");
      out.println("    document.Form1.TXT_RADIO_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RADIO_ON.value='on';"); 	
			out.println("}else if(data_vec[8]=='N'){");
			out.println("   document.Form1.TXT_RADIO_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_RADIO_OFF.value='on';}"); 	 
			
			out.println("if(data_vec[9]=='Y'){");
   		out.println("    document.Form1.TXT_CD_PLAYER_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_CD_PLAYER_ON.value='on';"); 	
			out.println("}else if(data_vec[9]=='N'){");
			out.println("    document.Form1.TXT_CD_PLAYER_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CD_PLAYER_OFF.value='on';}"); 	

			out.println("if(data_vec[10]=='Y'){");
   		out.println("    document.Form1.TXT_TOOL_KIT_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_TOOL_KIT_ON.value='on';"); 	
			out.println("}else if(data_vec[10]=='N'){");
			out.println("    document.Form1.TXT_TOOL_KIT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_TOOL_KIT_OFF.value='on';}"); 	
			
			
			out.println("if(data_vec[11]=='Y'){");
      out.println("    document.Form1.TXT_SPEAR_WHEEL_ON.checked=true;"); 
			out.println("    document.Form1.TXT_SPEAR_WHEEL_ON.value='on';"); 
			out.println("}else if(data_vec[11]=='N'){");
			out.println("    document.Form1.TXT_SPEAR_WHEEL_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_SPEAR_WHEEL_OFF.value='on';}"); 
			
			out.println("if(data_vec[12]=='Y'){");
      out.println("    document.Form1.TXT_JACK_ON.checked=true;"); 
			out.println("    document.Form1.TXT_JACK_ON.value='on';"); 
			out.println("}else if(data_vec[12]=='N'){");
			out.println("    document.Form1.TXT_JACK_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_JACK_OFF.value='on';}"); 
			
			out.println("if(data_vec[13]=='Y'){");
      out.println("    document.Form1.TXT_LIGHTER_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LIGHTER_ON.value='on';"); 	
			out.println("}else if(data_vec[13]=='N'){");
			out.println("   document.Form1.TXT_LIGHTER_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_LIGHTER_OFF.value='on';}"); 	 
			
			out.println("if(data_vec[14]=='Y'){");
   		out.println("    document.Form1.TXT_FUEL_CAP_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_FUEL_CAP_ON.value='on';"); 	
			out.println("}else if(data_vec[14]=='N'){");
			out.println("    document.Form1.TXT_FUEL_CAP_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_FUEL_CAP_OFF.value='on';}"); 	

			out.println("if(data_vec[15]=='Y'){");
   		out.println("    document.Form1.TXT_CARPETS_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_CARPETS_ON.value='on';"); 	
			out.println("}else if(data_vec[15]=='N'){");
			out.println("    document.Form1.TXT_CARPETS_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CARPETS_OFF.value='on';}"); 	
			
			
			out.println("if(data_vec[16]=='Y'){");
      out.println("    document.Form1.TXT_WHEEL_ON.checked=true;"); 
			out.println("    document.Form1.TXT_WHEEL_ON.value='on';"); 
			out.println("}else if(data_vec[16]=='N'){");
			out.println("    document.Form1.TXT_WHEEL_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_WHEEL_OFF.value='on';}"); 
			
			/*out.println("if(data_vec[17]=='Y'){");
      out.println("    document.Form1.TXT_BODY_ON.checked=true;"); 
			out.println("    document.Form1.TXT_BODY_ON.value='on';"); 
			out.println("}else if(data_vec[17]=='N'){");
			out.println("    document.Form1.TXT_BODY_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_BODY_OFF.value='on';}"); 
			*/
			
			//added by nuwan de silva on 02-11-07--------------------
			out.println("if(data_vec[17]=='G'){");
      out.println("    document.Form1.TXT_BODY_G.checked=true;"); 
			out.println("    document.Form1.TXT_BODY_G.value='Y';"); 
			out.println("}else if(data_vec[17]=='S'){");
			out.println("    document.Form1.TXT_BODY_S.checked=true;"); 
		  out.println("    document.Form1.TXT_BODY_S.value='Y';"); 
			out.println("}else if(data_vec[17]=='D'){");
			out.println("    document.Form1.TXT_BODY_D.checked=true;"); 
		  out.println("    document.Form1.TXT_BODY_D.value='Y';}"); 
			
			
			out.println("if(data_vec[18]=='Y'){");
      out.println("    document.Form1.TXT_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_MIRROR_ON.value='on';"); 	
			out.println("}else if(data_vec[18]=='N'){");
			out.println("   document.Form1.TXT_MIRROR_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_MIRROR_OFF.value='on';}"); 	 
			
			out.println("if(data_vec[19]=='Y'){");
   		out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_ON.value='on';"); 	
			out.println("}else if(data_vec[19]=='N'){");
			out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_OFF.value='on';}"); 	

			out.println("if(data_vec[20]=='Y'){");
   		out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_ON.value='on';"); 	
			out.println("}else if(data_vec[20]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF.value='on';}"); 	
			
			
			out.println("if(data_vec[21]=='Y'){");
      out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON.checked=true;"); 
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON.value='on';"); 
			out.println("}else if(data_vec[21]=='N'){");
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF.value='on';}"); 
			
			out.println("if(data_vec[22]=='Y'){");
      out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON.checked=true;"); 
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON.value='on';"); 
			out.println("}else if(data_vec[22]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF.value='on';}"); 
			
			out.println("if(data_vec[23]=='Y'){");
      out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON.value='on';"); 	
			out.println("}else if(data_vec[23]=='N'){");
			out.println("   document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF.value='on';}"); 	 
			
			out.println("if(data_vec[24]=='Y'){");
   		out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON.value='on';"); 	
			out.println("}else if(data_vec[24]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF.value='on';}"); 	

			/*out.println("if(data_vec[25]=='Y'){");
   		out.println("    document.Form1.TXT_POLICE_REPORT_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_POLICE_REPORT_ON.value='on';"); 	
			out.println("}else if(data_vec[25]=='N'){");
			out.println("    document.Form1.TXT_POLICE_REPORT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_POLICE_REPORT_OFF.value='on';}"); 	
			
			
			out.println("if(data_vec[26]=='Y'){");
      out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_ON.value='on';"); 
			out.println("}else if(data_vec[26]=='N'){");
			out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF.value='on';}"); 
			
			out.println("if(data_vec[27]=='Y'){");
      out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_ON.value='on';"); 
			out.println("}else if(data_vec[27]=='N'){");
			out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_OFF.value='on';}"); 
			
			out.println("if(data_vec[28]=='Y'){");
      out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_ON.value='on';"); 	
			out.println("}else if(data_vec[28]=='N'){");
			out.println("   document.Form1.TXT_RECEIVERS_SIGNATURE_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_OFF.value='on';}"); 	 
			*/
			
		  out.println("    document.Form1.TXT_YARD_CODE.value=data_vec[29];"); 
			out.println("    document.Form1.TXT_YARD_NAME.value=data_vec[30];"); 



			
			out.println("}"); 
			
			/*out.println("function help_value_assign_data() {"); 
			
			out.println("    document.Form1.TXT_REPOSSESSION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ASSET_DESCRIPTION.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[7];"); 
			out.println("assignState('M3')");
			out.println("makeRequest(document.Form1.TXT_VEHICLE_NO)");
			out.println("    document.Form1.TXT_MILEGE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_ADVERTISMENT_STATUS.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_OFFER_STATU.value=oBj.valout[10];"); 
      out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[11];"); 
			
			out.println("if(oBj.valout[12]=='Y'){");
      out.println("    document.Form1.TXT_KEY_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_KEY_ON.value='on';"); 	
			out.println("}else if(oBj.valout[12]=='N'){");
			out.println("   document.Form1.TXT_KEY_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_KEY_OFF.value='on';}"); 	 
			
			out.println("if(oBj.valout[13]=='Y'){");
   		out.println("    document.Form1.TXT_LICENSE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LICENSE_ON.value='on';"); 	
			out.println("}else if(oBj.valout[13]=='N'){");
			out.println("    document.Form1.TXT_LICENSE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LICENSE_OFF.value='on';}"); 	

			out.println("if(oBj.valout[14]=='Y'){");
   		out.println("    document.Form1.TXT_INSURANCE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_INSURANCE_ON.value='on';"); 	
			out.println("}else if(oBj.valout[14]=='N'){");
			out.println("    document.Form1.TXT_INSURANCE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_INSURANCE_OFF.value='on';}"); 	
			
			
			out.println("if(oBj.valout[15]=='Y'){");
      out.println("    document.Form1.TXT_VEHICLE_ID_CARD_ON.checked=true;"); 
			out.println("    document.Form1.TXT_VEHICLE_ID_CARD_ON.value='on';"); 
			out.println("}else if(oBj.valout[15]=='N'){");
			out.println("    document.Form1.TXT_VEHICLE_ID_CARD_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_VEHICLE_ID_CARD_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[16]=='Y'){");
      out.println("    document.Form1.TXT_CASSETTE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_CASSETTE_ON.value='on';"); 
			out.println("}else if(oBj.valout[16]=='N'){");
			out.println("    document.Form1.TXT_CASSETTE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CASSETTE_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[17]=='Y'){");
      out.println("    document.Form1.TXT_RADIO_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RADIO_ON.value='on';"); 	
			out.println("}else if(oBj.valout[17]=='N'){");
			out.println("   document.Form1.TXT_RADIO_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_RADIO_OFF.value='on';}"); 	 
			
			out.println("if(oBj.valout[18]=='Y'){");
   		out.println("    document.Form1.TXT_CD_PLAYER_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_CD_PLAYER_ON.value='on';"); 	
			out.println("}else if(oBj.valout[18]=='N'){");
			out.println("    document.Form1.TXT_CD_PLAYER_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CD_PLAYER_OFF.value='on';}"); 	

			out.println("if(oBj.valout[19]=='Y'){");
   		out.println("    document.Form1.TXT_TOOL_KIT_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_TOOL_KIT_ON.value='on';"); 	
			out.println("}else if(oBj.valout[19]=='N'){");
			out.println("    document.Form1.TXT_TOOL_KIT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_TOOL_KIT_OFF.value='on';}"); 	
			
			
			out.println("if(oBj.valout[20]=='Y'){");
      out.println("    document.Form1.TXT_SPEAR_WHEEL_ON.checked=true;"); 
			out.println("    document.Form1.TXT_SPEAR_WHEEL_ON.value='on';"); 
			out.println("}else if(oBj.valout[20]=='N'){");
			out.println("    document.Form1.TXT_SPEAR_WHEEL_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_SPEAR_WHEEL_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[21]=='Y'){");
      out.println("    document.Form1.TXT_JACK_ON.checked=true;"); 
			out.println("    document.Form1.TXT_JACK_ON.value='on';"); 
			out.println("}else if(oBj.valout[21]=='N'){");
			out.println("    document.Form1.TXT_JACK_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_JACK_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[22]=='Y'){");
      out.println("    document.Form1.TXT_LIGHTER_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LIGHTER_ON.value='on';"); 	
			out.println("}else if(oBj.valout[22]=='N'){");
			out.println("   document.Form1.TXT_LIGHTER_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_LIGHTER_OFF.value='on';}"); 	 
			
			out.println("if(oBj.valout[23]=='Y'){");
   		out.println("    document.Form1.TXT_FUEL_CAP_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_FUEL_CAP_ON.value='on';"); 	
			out.println("}else if(oBj.valout[23]=='N'){");
			out.println("    document.Form1.TXT_FUEL_CAP_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_FUEL_CAP_OFF.value='on';}"); 	

			out.println("if(oBj.valout[24]=='Y'){");
   		out.println("    document.Form1.TXT_CARPETS_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_CARPETS_ON.value='on';"); 	
			out.println("}else if(oBj.valout[24]=='N'){");
			out.println("    document.Form1.TXT_CARPETS_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CARPETS_OFF.value='on';}"); 	
			
			
			out.println("if(oBj.valout[25]=='Y'){");
      out.println("    document.Form1.TXT_WHEEL_ON.checked=true;"); 
			out.println("    document.Form1.TXT_WHEEL_ON.value='on';"); 
			out.println("}else if(oBj.valout[25]=='N'){");
			out.println("    document.Form1.TXT_WHEEL_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_WHEEL_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[26]=='Y'){");
      out.println("    document.Form1.TXT_BODY_ON.checked=true;"); 
			out.println("    document.Form1.TXT_BODY_ON.value='on';"); 
			out.println("}else if(oBj.valout[26]=='N'){");
			out.println("    document.Form1.TXT_BODY_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_BODY_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[27]=='Y'){");
      out.println("    document.Form1.TXT_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_MIRROR_ON.value='on';"); 	
			out.println("}else if(oBj.valout[27]=='N'){");
			out.println("   document.Form1.TXT_MIRROR_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_MIRROR_OFF.value='on';}"); 	 
			
			out.println("if(oBj.valout[28]=='Y'){");
   		out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_ON.value='on';"); 	
			out.println("}else if(oBj.valout[28]=='N'){");
			out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIDE_MIRROR_OFF.value='on';}"); 	

			out.println("if(oBj.valout[29]=='Y'){");
   		out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_ON.value='on';"); 	
			out.println("}else if(oBj.valout[29]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF.value='on';}"); 	
			
			
			out.println("if(oBj.valout[30]=='Y'){");
      out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON.checked=true;"); 
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON.value='on';"); 
			out.println("}else if(oBj.valout[30]=='N'){");
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[31]=='Y'){");
      out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON.checked=true;"); 
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON.value='on';"); 
			out.println("}else if(oBj.valout[31]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[32]=='Y'){");
      out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON.value='on';"); 	
			out.println("}else if(oBj.valout[32]=='N'){");
			out.println("   document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF.value='on';}"); 	 
			
			out.println("if(oBj.valout[33]=='Y'){");
   		out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON.value='on';"); 	
			out.println("}else if(oBj.valout[33]=='N'){");
			out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF.value='on';}"); 	

			out.println("if(oBj.valout[34]=='Y'){");
   		out.println("    document.Form1.TXT_POLICE_REPORT_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_POLICE_REPORT_ON.value='on';"); 	
			out.println("}else if(oBj.valout[34]=='N'){");
			out.println("    document.Form1.TXT_POLICE_REPORT_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_POLICE_REPORT_OFF.value='on';}"); 	
			
			
			out.println("if(oBj.valout[35]=='Y'){");
      out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_ON.value='on';"); 
			out.println("}else if(oBj.valout[35]=='N'){");
			out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[36]=='Y'){");
      out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_ON.checked=true;"); 
			out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_ON.value='on';"); 
			out.println("}else if(oBj.valout[36]=='N'){");
			out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_OFF.checked=true;"); 
		  out.println("    document.Form1.TXT_SEIZERS_SIGNATURE_OFF.value='on';}"); 
			
			out.println("if(oBj.valout[37]=='Y'){");
      out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_ON.checked=true;"); 
		  out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_ON.value='on';"); 	
			out.println("}else if(oBj.valout[37]=='N'){");
			out.println("   document.Form1.TXT_RECEIVERS_SIGNATURE_OFF.checked=true;"); 
			out.println("    document.Form1.TXT_RECEIVERS_SIGNATURE_OFF.value='on';}"); 	 
			
					
			

		out.println("}");
		
		*/
      
			
			out.println("function help_button_2() {");				
			//out.println("    Crit =document.Form1.TXT_REPOSSESSION_CODE.value+\"@\"+document.Form1.TXT_VEHICLE_NO.value+\"@\"+document.Form1.TXT_REPOSSESSION_CODE.value+\"@\"+\"REPOSSESS@\";"); 
			out.println("    Crit =document.Form1.TXT_REPOSSESSION_CODE.value+\"@\"+document.Form1.TXT_VEHICLE_NO.value+\"@REPOSSESS@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_VEHICLE_NO_sql','2');"); 
		
			out.println("}"); 
			out.println(""); 
			

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[4];"); 
			out.println("get_data();");
			
			out.println("}"); 
			
			out.println("function get_data(){");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" ){ ");
			
			out.println("assignState('M5')");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_data&data_val=\"+document.Form1.TXT_REPOSSESSION_CODE.value+\"&data_val2=\"+document.Form1.TXT_VEHICLE_NO.value+\"&ac_status=ENT\";");	
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}"); 
			out.println("}");
			
					
			out.println("function Val_Change(obj1,obj2){");
			out.println("if(obj1.checked==true && obj2.checked==true){");
			out.println("obj1.value='on'");
			out.println("obj2.checked=false");
			out.println("obj2.value='off'");
			out.println("}else if(obj1.checked==true && obj2.checked==false){");
			out.println("obj1.value='on'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("obj1.value='off'");
			out.println("}");		

			out.println("}");	
			
			
			
				/*---------------------------------------------------------------------
			Purpose : This Function Used To Count The Parts Selected
			--------------------------------------------------------------------*/
			out.println("function count_selected(){ ");
			out.println("count=0;");
			out.println("j=1;");
			
			out.println("if(document.Form1.TXT_KEY_ON.checked==true || document.Form1.TXT_KEY_OFF.checked==true ){");
			out.println("count=count+j;}"); 	 
			out.println("else{");
			out.println("  ROW1.style.color='red';");
			out.println("}");
			
			out.println("if(document.Form1.TXT_D_KEY_ON.checked==true || document.Form1.TXT_D_KEY_OFF.checked==true ){");
			out.println("count=count+j;}"); 	 
			out.println("else{");
			out.println("  ROW_1_D.style.color='red';");
			out.println("}");
			
			out.println("if(document.Form1.TXT_LICENSE_ON.checked==true || document.Form1.TXT_LICENSE_OFF.checked==true ){");
   		out.println("count=count+j;}"); 	 
			out.println("else{");
			   out.println("  ROW2.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_INSURANCE_ON.checked==true || document.Form1.TXT_INSURANCE_OFF.checked==true ){");
			out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW3.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_VEHICLE_ID_CARD_ON.checked==true || document.Form1.TXT_VEHICLE_ID_CARD_OFF.checked==true ){");
			out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW4.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_CASSETTE_ON.checked==true || document.Form1.TXT_CASSETTE_OFF.checked==true ){");
			out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW5.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_RADIO_ON.checked==true || document.Form1.TXT_RADIO_OFF.checked==true ){");
			out.println("count+=j;}"); 
			out.println("else{");
			out.println("  ROW6.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_CD_PLAYER_ON.checked==true || document.Form1.TXT_CD_PLAYER_OFF.checked==true ){");
   		out.println("count+=j;}"); 
			out.println("else{");
		   out.println("  ROW7.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_TOOL_KIT_ON.checked==true || document.Form1.TXT_TOOL_KIT_OFF.checked==true ){");
   		out.println("count+=j;}"); 
			out.println("else{");
			   out.println("  ROW8.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_SPEAR_WHEEL_ON.checked==true || document.Form1.TXT_SPEAR_WHEEL_OFF.checked==true ){");
   		out.println("count+=j;}"); 	 
			out.println("else{");
			   out.println("  ROW9.style.color='red';");
			out.println("}");

			out.println("if(document.Form1.TXT_JACK_ON.checked==true || document.Form1.TXT_JACK_OFF.checked==true ){");
   		out.println("count+=j;}"); 	 
			out.println("else{");
				out.println("  ROW10.style.color='red';");
			out.println("}");

			out.println("if(document.Form1.TXT_LIGHTER_ON.checked==true || document.Form1.TXT_LIGHTER_OFF.checked==true ){");
   		out.println("count+=j;}"); 	 
			out.println("else{");
				out.println("  ROW11.style.color='red';");
			out.println("}");

			out.println("if(document.Form1.TXT_FUEL_CAP_ON.checked==true || document.Form1.TXT_FUEL_CAP_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
			  	out.println("  ROW12.style.color='red';");
			out.println("}");

			out.println("if(document.Form1.TXT_CARPETS_ON.checked==true || document.Form1.TXT_CARPETS_OFF.checked==true ){");
    	out.println("count+=j;}"); 	
			out.println("else{");
			  	out.println("  ROW13.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_WHEEL_ON.checked==true || document.Form1.TXT_WHEEL_OFF.checked==true ){");
      out.println("count+=j;}");
			out.println("else{");
			out.println("  ROW14.style.color='red';");
			out.println("}");

			
			/*out.println("if(document.Form1.TXT_BODY_ON.checked==true || document.Form1.TXT_BODY_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW15.style.color='red';");
			out.println("}");
      */
			
			out.println("if(document.Form1.TXT_BODY_G.checked==true || document.Form1.TXT_BODY_S.checked==true || document.Form1.TXT_BODY_D.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW15.style.color='red';");
			out.println("}");



			out.println("if(document.Form1.TXT_MIRROR_ON.checked==true || document.Form1.TXT_MIRROR_OFF.checked==true ){");
      out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW16.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_LEFT_SIDE_MIRROR_ON.checked==true || document.Form1.TXT_LEFT_SIDE_MIRROR_OFF.checked==true ){");
      out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW17.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_RIGHT_SIDE_MIRROR_ON.checked==true || document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
		  out.println("  ROW18.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON.checked==true || document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF.checked==true ){");
    	out.println("count+=j;}"); 	
			out.println("else{");
		  	out.println("  ROW19.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON.checked==true || document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF.checked==true ){");
    	out.println("count+=j;}"); 	
			out.println("else{");
		  	out.println("  ROW20.style.color='red';");
			out.println("}");

			
			out.println(" if(document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON.checked==true || document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF.checked==true ){");
    	out.println("count+=j;}"); 	
			out.println("else{");
		  	out.println("  ROW21.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON.checked==true || document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF.checked==true ){");
    	out.println("count+=j;}"); 	
			out.println("else{");
		  	out.println("  ROW22.style.color='red';");
			out.println("}");

			
		/*	out.println(" if(document.Form1.TXT_POLICE_REPORT_ON.checked==true || document.Form1.TXT_POLICE_REPORT_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
		  	out.println("  ROW23.style.color='red';");
			out.println("}");


			out.println("if(document.Form1.TXT_CUSTOMERS_SIGNATURE_ON.checked==true || document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
			out.println("  ROW24.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_SEIZERS_SIGNATURE_ON.checked==true || document.Form1.TXT_SEIZERS_SIGNATURE_OFF.checked==true ){");
    	out.println("count+=j;}"); 	 
			out.println("else{");
		 	out.println("  ROW25.style.color='red';");
			out.println("}");

			
			out.println("if(document.Form1.TXT_RECEIVERS_SIGNATURE_ON.checked==true || document.Form1.TXT_RECEIVERS_SIGNATURE_OFF.checked==true ){");
      out.println("count+=j;}"); 	
			out.println("else{");
			out.println("  ROW26.style.color='red';");
			out.println("}");
    
		*/
			
		//	out.println("alert('fds'+count);");
			
			out.println("if(count==23){");//26
			out.println("return true;");
			out.println("}");	
			
			out.println("else {");	
			out.println("return false;");
			out.println("}");	
			
			out.println("}");	
			
			out.println("function help_button_yard() {"); 
			
			out.println("    Crit = document.Form1.TXT_YARD_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','6',Crit,'m_help_TXT_YARD_CODE_sql','5');"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_yard() {"); 
			out.println("    document.Form1.TXT_YARD_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_YARD_NAME.value=oBj.valout[3];"); 
			out.println("}"); 
			
		//--------------------------------------------------------------------------		
		
		
		  out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("if(val=='M6' && document.Form1.TXT_REPOSSESSION_CODE.value!=\"\"){");
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			out.println("document.Form1.BUT_TXT_INVENTORY_NO.disabled=false;"); 
			out.println("}");
			out.println("}");
			
			out.println("function validate_data(){"); 
			
			out.println("if(document.Form1.TXT_REPOSSESSION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_REPOSSESSION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VEHICLE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_VEHICLE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_YARD_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_YARD_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else");
			out.println("{"); 
			out.println("return true"); 
			out.println("}"); 
		
			out.println("}"); 

			out.println("function before_submit(){ "); 
			//out.println("alert('val D '+document.Form1.TXT_BODY_D.value);");
			//out.println("alert('val S '+document.Form1.TXT_BODY_S.value);");
			//out.println("alert('val G '+document.Form1.TXT_BODY_G.value);");
			out.println("		if(validate_data()){"); 
			out.println("if(count_selected()){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			//out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
						
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Vehicle_Inventory';");  
			out.println("		document.Form1.submit();	");
			out.println("		}");
			out.println("		}"); 
			
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please mark the availability of all vehicle parts before saving\");");
			out.println("} "); 
	
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Vehicle_Inventory';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Vehicle_Inventory';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function disable_controls(){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=true;");
			out.println("}");
			
      out.println("document.Form1.btn_new.disabled=false;");
		  out.println("document.Form1.btn_edit.disabled=false;");
			out.println("document.Form1.btn_delete.disabled=false;");
			out.println("document.Form1.btn_save.disabled=false;");
			out.println("document.Form1.btn_help.disabled=false;");
			out.println("document.Form1.btn_cancel.disabled=false;");
			out.println("document.Form1.btn_close.disabled=false;");
			out.println("document.Form1.btn_new1.disabled=false;");
		  out.println("document.Form1.btn_edit1.disabled=false;");
			out.println("document.Form1.btn_delete1.disabled=false;");
			out.println("document.Form1.btn_save1.disabled=false;");
			out.println("document.Form1.btn_help1.disabled=false;");
			out.println("document.Form1.btn_cancel1.disabled=false;");
			out.println("document.Form1.btn_close1.disabled=false;");
			out.println("document.Form1.BUT_TXT_REPOSSESSION_CODE.disabled=false;");
			out.println("document.Form1.TXT_REPOSSESSION_CODE.disabled=false;");
			out.println("document.Form1.VALUATION_LINK_BUT.disabled=false;");
			
			
			
			out.println("}"); 
			
			
			

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Vehicle_Inventory\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Repossession Vehicle Inventory - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Repossession Vehicle Inventory - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();}"); 
			out.println("}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		 
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			//out.println("    document.Form1.TXT_INVENTORY_NO.disabled=false;"); 
			//out.println("    document.Form1.BUT_TXT_INVENTORY_NO.disabled=false;"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
      out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}"); 
			
			out.println("}else if(m_val==\"DEL\"){");  
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("disable_controls();");
			out.println("}"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_number(){");
			out.println("   	if(isnumberok(document.Form1.TXT_MILEGE,10)){");
			out.println("       format_number(document.Form1.TXT_MILEGE,10);");
      out.println("}"); 
			out.println("else{"); 
			out.println("     alert('Please enter a number');");
			out.println("}"); 
				
			out.println("}"); 
			
			out.println("function load_valuation(){");
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no='+document.Form1.TXT_INVENTORY_NO.value+'&vehicle_no='+document.Form1.TXT_VEHICLE_NO.value+'&chassis_no='+document.Form1.TXT_CHASSIS_NO.value+'&engine_no='+document.Form1.TXT_ENGINE_NO.value+'';"); 
			    out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=750,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 			out.println("}");
						
						
						
			//=========added by nuwan de silva 22-05-07=============
					
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
			
			
			
			
			
			
			
			
			
			
			
			out.println("function change_damage() {"); 
			out.println("if(document.Form1.TXT_BODY_D.checked==true && ( document.Form1.TXT_BODY_G.checked==true || document.Form1.TXT_BODY_S.checked==true ) ){");
			out.println("document.Form1.TXT_BODY_D.value='Y'");
			out.println("document.Form1.TXT_BODY_G.checked=false");
			out.println("document.Form1.TXT_BODY_G.value='N'");
			out.println("document.Form1.TXT_BODY_S.checked=false");
			out.println("document.Form1.TXT_BODY_S.value='N'");
			out.println("}");	
			out.println("else if(document.Form1.TXT_BODY_D.checked==true && ( document.Form1.TXT_BODY_G.checked==false && document.Form1.TXT_BODY_S.checked==false ) ){");
			out.println("document.Form1.TXT_BODY_D.value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.TXT_BODY_D.value='N'");
			out.println("document.Form1.TXT_BODY_D.checked=false");
			out.println("}");	
			out.println("}"); 	
			
			
			out.println("function change_good() {"); 
			out.println("if(document.Form1.TXT_BODY_G.checked==true && ( document.Form1.TXT_BODY_S.checked==true || document.Form1.TXT_BODY_D.checked==true ) ){");
			out.println("document.Form1.TXT_BODY_G.value='Y'");
			out.println("document.Form1.TXT_BODY_D.checked=false");
			out.println("document.Form1.TXT_BODY_D.value='N'");
			out.println("document.Form1.TXT_BODY_S.checked=false");
			out.println("document.Form1.TXT_BODY_S.value='N'");
			out.println("}");	
			out.println("else if(document.Form1.TXT_BODY_G.checked==true && ( document.Form1.TXT_BODY_S.checked==false && document.Form1.TXT_BODY_D.checked==false ) ){");
			out.println("document.Form1.TXT_BODY_G.value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.TXT_BODY_G.value='N'");
			out.println("document.Form1.TXT_BODY_G.checked=false");
			out.println("}");	
			out.println("}"); 		


     	out.println("function change_scratch() {"); 
			out.println("if(document.Form1.TXT_BODY_S.checked==true && ( document.Form1.TXT_BODY_G.checked==true || document.Form1.TXT_BODY_D.checked==true ) ){");
			out.println("document.Form1.TXT_BODY_S.value='Y'");
			out.println("document.Form1.TXT_BODY_G.checked=false");
			out.println("document.Form1.TXT_BODY_G.value='N'");
			out.println("document.Form1.TXT_BODY_D.checked=false");
			out.println("document.Form1.TXT_BODY_D.value='N'");
			out.println("}");	
			out.println("else if(document.Form1.TXT_BODY_S.checked==true && ( document.Form1.TXT_BODY_G.checked==false && document.Form1.TXT_BODY_D.checked==false ) ){");
			out.println("document.Form1.TXT_BODY_S.value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.TXT_BODY_S.value='N'");
			out.println("document.Form1.TXT_BODY_S.checked=false");
			out.println("}");	
			out.println("}"); 		
     
			out.println("function onload_edit_screen(){");//Added By Sandun on 02-07-2009
			if(m_reposs_no!=null && m_inventry_no!=null){
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"; ");
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.btn_new.disabled=true;");		
			out.println("document.Form1.btn_delete.disabled=true;");		
			out.println("document.Form1.TXT_REPOSSESSION_CODE.value=\""+m_reposs_no+"\";");
			out.println("document.Form1.TXT_INVENTORY_NO.value = \""+m_inventry_no+"\";");
			out.println("document.Form1.TXT_SEIZER_CODE.value=\""+m_code+"\";"); 
			out.println("document.Form1.TXT_SEIZER_NAME.value=\""+m_name+"\";"); 
			out.println("document.Form1.TXT_INVOICE_AMOUNT.value=\""+m_fee+"\";"); 
			out.println("document.Form1.hid_chk_status.value='M1'");
			out.println("makeRequest(document.Form1.TXT_REPOSSESSION_CODE);");	
			out.println("document.Form1.hid_load_status.value=\"APP\";");
			}else{
			out.println("document.Form1.hid_status.value=\"New\";");
			}
			out.println("}"); 		

						
      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"onload_edit_screen()\">"); //load_lock() ,get_Application_numbers('ENT_DATE','ASC')
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_VEHICLE_INVENTORY\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_load_status' VALUE=\"\">");
					
										
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Repossession Vehicle Inventory - New</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" name='btn_new' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_edit' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_save' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_help' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_cancel' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_close' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='15%' align='left'><input  style='width:100'; type='button' class='mainbut' name='VALUATION_LINK_BUT' value=\"Valuation\" onClick=\"load_valuation()\"></td>"); // font-size: 20px; 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
				  out.println("<table align='center' width='100%' class='table' border='0'>"); 
			    out.println("<tr>"); 
					out.println("<td width='25%' ><DIV id='DIV_TXT_REPOSSESSION_CODE'  class=div_input>Repossession Number *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_REPOSSESSION_CODE' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_REPOSSESSION_CODE)\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_REPOSSESSION_CODE' value=\"...\" onClick=\"help_button_1()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ><DIV id='DIV_INVENTORY_NO'  class=div_input>Inventory Number *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_INVENTORY_NO' maxlength='15' size='15' disabled onblur=\"assignState('M6'),makeRequest(document.Form1.TXT_INVENTORY_NO)\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_INVENTORY_NO' value=\"...\" onClick=\"help_button_inventory()\" disabled></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					//added by nuwan de silva on 02-11-07---------
					out.println("<tr>"); 
					out.println("<td width='25%' >Finance no</td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='10' size='10'  disabled></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Seizer Code/Officer Code</td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_SEIZER_CODE' maxlength='10' size='10'  disabled></td>"); 
					//out.println("<input class='but_input' type='button' name='BUT_TXT_SEIZER_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Seizer Name/Officer Name</td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_SEIZER_NAME' style=\"width:250px;\" maxlength='100' size='100'  disabled></td>"); 
					//out.println("<input class='but_input' type='button' name='BUT_TXT_SEIZER_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>");
			
					//---modified by :delanjali---------------------------------------------------------------------------------------------------------------------------------------------
					//---date				 :2007-06-11--------------------------------------------------------------------------------------------------------------------------------------------
			
					out.println("<tr >"); 
					out.println("<td width='25%' >Seizer Fees</td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_INVOICE_AMOUNT' maxlength='22' size='22' disabled STYLE='{text-align:right;}'></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
						

					out.println("<tr>"); 
					out.println("<td width='25%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Customer Code *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10'  disabled></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
		      out.println("<tr>"); 
					out.println("<td width='25%' >Customer Name</td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style=\"width:250px;\" maxlength='100' size='100'  disabled></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
									
					out.println("<tr>"); 
				 	out.println("<td width='25%' ><DIV id='DIV_TXT_VEHICLE_NO'  class=div_input>Vehicle No *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_VEHICLE_NO' maxlength='10' disabled  size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_VEHICLE_NO)\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_VEHICLE_NO' value=\"...\"  disabled onClick=\"help_button_2()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

          out.println("<tr>"); 
					out.println("<td width='25%' >Engine Number </td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='10' size='10'  disabled></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Chassis Number </td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='10' size='10' disabled ></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
				 	out.println("<td width='25%' ><DIV id='DIV_TXT_YARD_CODE'  class=div_input>Yard Code *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_YARD_CODE' maxlength='10'  size='10' onblur=\"assignState('M7'),makeRequest(document.Form1.TXT_YARD_CODE)\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_YARD_CODE' value=\"...\"   onClick=\"help_button_yard()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
				 	out.println("<td width='25%' ><DIV id='DIV_TXT_YARD_NAME'  class=div_input>Yard Name </DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_YARD_NAME' maxlength='100' style=\"width:250px;\"  size='100' onblur=\"\">"); 
					out.println("</td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Asset Description </td>"); 
					out.println("<td width='*%' ><TEXTAREA class='txt_input' name='TXT_ASSET_DESCRIPTION' style=\"width:450px; height:50px;\" maxlength='1000' size='1000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Mileage </td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_MILEGE' maxlength='10' size='10' onBlur=\"check_number()\"></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
          out.println("</table>"); 
					     					
					//out.println("<tr></tr><tr></tr><tr></tr>"); 
		
				  out.println("<table align='center' width='100%' class='table' border='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='25%' ><b>Equipment</b></td>"); 
					out.println("<td width='10%' ><b>Available</b></td>"); 
					out.println("<td width='10%' ><b>Not Available</b></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Key</td>");
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW1 ><blockquote>Original Key </blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_KEY_ON'  onclick=\"Val_Change(document.Form1.TXT_KEY_ON,document.Form1.TXT_KEY_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_KEY_OFF' onclick=\"Val_Change(document.Form1.TXT_KEY_OFF,document.Form1.TXT_KEY_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW_1_D ><blockquote>Duplicate Key </blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_D_KEY_ON'  onclick=\"Val_Change(document.Form1.TXT_D_KEY_ON,document.Form1.TXT_D_KEY_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_D_KEY_OFF' onclick=\"Val_Change(document.Form1.TXT_D_KEY_OFF,document.Form1.TXT_D_KEY_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW2 >License </td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LICENSE_ON'  onclick=\"Val_Change(document.Form1.TXT_LICENSE_ON,document.Form1.TXT_LICENSE_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LICENSE_OFF' onclick=\"Val_Change(document.Form1.TXT_LICENSE_OFF,document.Form1.TXT_LICENSE_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW3 >Insurance</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_INSURANCE_ON'  onclick=\"Val_Change(document.Form1.TXT_INSURANCE_ON,document.Form1.TXT_INSURANCE_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_INSURANCE_OFF' onclick=\"Val_Change(document.Form1.TXT_INSURANCE_OFF,document.Form1.TXT_INSURANCE_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW4 >Vehicle ID Card</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_VEHICLE_ID_CARD_ON'  onclick=\"Val_Change(document.Form1.TXT_VEHICLE_ID_CARD_ON,document.Form1.TXT_VEHICLE_ID_CARD_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_VEHICLE_ID_CARD_OFF' onclick=\"Val_Change(document.Form1.TXT_VEHICLE_ID_CARD_OFF,document.Form1.TXT_VEHICLE_ID_CARD_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW5 >Cassette</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CASSETTE_ON'  onclick=\"Val_Change(document.Form1.TXT_CASSETTE_ON,document.Form1.TXT_CASSETTE_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CASSETTE_OFF' onclick=\"Val_Change(document.Form1.TXT_CASSETTE_OFF,document.Form1.TXT_CASSETTE_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW6 >Radio</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RADIO_ON'  onclick=\"Val_Change(document.Form1.TXT_RADIO_ON,document.Form1.TXT_RADIO_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RADIO_OFF' onclick=\"Val_Change(document.Form1.TXT_RADIO_OFF,document.Form1.TXT_RADIO_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW7 >CD Player</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CD_PLAYER_ON'  onclick=\"Val_Change(document.Form1.TXT_CD_PLAYER_ON,document.Form1.TXT_CD_PLAYER_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CD_PLAYER_OFF' onclick=\"Val_Change(document.Form1.TXT_CD_PLAYER_OFF,document.Form1.TXT_CD_PLAYER_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW8 >Tool Kit</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_TOOL_KIT_ON'  onclick=\"Val_Change(document.Form1.TXT_TOOL_KIT_ON,document.Form1.TXT_TOOL_KIT_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_TOOL_KIT_OFF' onclick=\"Val_Change(document.Form1.TXT_TOOL_KIT_OFF,document.Form1.TXT_TOOL_KIT_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW9 >Spare Wheel</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_SPEAR_WHEEL_ON'  onclick=\"Val_Change(document.Form1.TXT_SPEAR_WHEEL_ON,document.Form1.TXT_SPEAR_WHEEL_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_SPEAR_WHEEL_OFF' onclick=\"Val_Change(document.Form1.TXT_SPEAR_WHEEL_OFF,document.Form1.TXT_SPEAR_WHEEL_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW10 >Jack</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_JACK_ON'  onclick=\"Val_Change(document.Form1.TXT_JACK_ON,document.Form1.TXT_JACK_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_JACK_OFF' onclick=\"Val_Change(document.Form1.TXT_JACK_OFF,document.Form1.TXT_JACK_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' >Lighter/Carpets/Fuel Cap</td>");
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 


					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW11 ><blockquote>Lighter</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LIGHTER_ON'  onclick=\"Val_Change(document.Form1.TXT_LIGHTER_ON,document.Form1.TXT_LIGHTER_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LIGHTER_OFF' onclick=\"Val_Change(document.Form1.TXT_LIGHTER_OFF,document.Form1.TXT_LIGHTER_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW12 ><blockquote>Fuel Cap</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_FUEL_CAP_ON'  onclick=\"Val_Change(document.Form1.TXT_FUEL_CAP_ON,document.Form1.TXT_FUEL_CAP_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_FUEL_CAP_OFF' onclick=\"Val_Change(document.Form1.TXT_FUEL_CAP_OFF,document.Form1.TXT_FUEL_CAP_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW13><blockquote>Carpets</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CARPETS_ON' onclick=\"Val_Change(document.Form1.TXT_CARPETS_ON,document.Form1.TXT_CARPETS_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_CARPETS_OFF' onclick=\"Val_Change(document.Form1.TXT_CARPETS_OFF,document.Form1.TXT_CARPETS_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW14 >Wheel</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_WHEEL_ON'  onclick=\"Val_Change(document.Form1.TXT_WHEEL_ON,document.Form1.TXT_WHEEL_OFF)\">Alloy</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_WHEEL_OFF' onclick=\"Val_Change(document.Form1.TXT_WHEEL_OFF,document.Form1.TXT_WHEEL_ON)\">Cup set</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					
 					out.println("<tr>"); 
					out.println("<td width='25%' ><b><u>Body Condition</u></td>");
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW16 ><li>Rear Mirror/Front Mirror</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_MIRROR_ON'  onclick=\"Val_Change(document.Form1.TXT_MIRROR_ON,document.Form1.TXT_MIRROR_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_MIRROR_OFF' onclick=\"Val_Change(document.Form1.TXT_MIRROR_OFF,document.Form1.TXT_MIRROR_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ><li>Side Mirror</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW17 ><blockquote>Left Side Mirror</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIDE_MIRROR_ON'  onclick=\"Val_Change(document.Form1.TXT_LEFT_SIDE_MIRROR_ON,document.Form1.TXT_LEFT_SIDE_MIRROR_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIDE_MIRROR_OFF' onclick=\"Val_Change(document.Form1.TXT_LEFT_SIDE_MIRROR_OFF,document.Form1.TXT_LEFT_SIDE_MIRROR_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW18 ><blockquote>Right Side Mirror</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIDE_MIRROR_ON'  onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIDE_MIRROR_ON,document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIDE_MIRROR_OFF' onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIDE_MIRROR_OFF,document.Form1.TXT_RIGHT_SIDE_MIRROR_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
          
					out.println("<tr>"); 
					out.println("<td width='25%' ><li>Signal Lights</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='10%' >&nbsp;</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW19 ><blockquote>Left Signal Light - Front</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIGNAL_LIGHT_FRONT_ON'  onclick=\"Val_Change(document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON,document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF' onclick=\"Val_Change(document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF,document.Form1.TXT_LEFT_SIGNAL_LIGHT_FRONT_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW20 ><blockquote>Right Signal Light - Front</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON'  onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON,document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF' onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF,document.Form1.TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW21 ><blockquote>Left Signal Light - Rear</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIGNAL_LIGHT_REAR_ON'  onclick=\"Val_Change(document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON,document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_LEFT_SIGNAL_LIGHT_REAR_OFF' onclick=\"Val_Change(document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_OFF,document.Form1.TXT_LEFT_SIGNAL_LIGHT_REAR_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW22 ><blockquote>Right Signal Light - Rear</blockquote></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIGNAL_LIGHT_REAR_ON'  onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON,document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF)\"></td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF' onclick=\"Val_Change(document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF,document.Form1.TXT_RIGHT_SIGNAL_LIGHT_REAR_ON)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					
					out.println("<table align='center' width='100%' border='0' class='table'>"); 
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW15 ><li>Body</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_BODY_G'  onclick=\"change_good()\">Good</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_BODY_S'  onclick=\"change_scratch()\">Scratched</td>"); 
					out.println("<td width='10%' ><input  type='checkbox' name='TXT_BODY_D'  onclick=\"change_damage()\">Damaged</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("</table>"); 
					
				  out.println("<table align='center' width='100%' border='0' class='table'>"); 
					out.println("<tr>"); 
					out.println("<td width='25%' ><li>Other(Remarks/Comment)</td>"); 
				  out.println("<td width='*%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS' style=\"width:450px; height:50px;\" maxlength='1000' size='1000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
					//out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 					
					
					out.println("<table align='center' width='100%' class='table' border='0'>"); 

					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW24 >Customer's Signature</td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_CUSTOMERS_SIGNATURE_ON'  onclick=\"Val_Change(document.Form1.TXT_CUSTOMERS_SIGNATURE_ON,document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF)\"></td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_CUSTOMERS_SIGNATURE_OFF' onclick=\"Val_Change(document.Form1.TXT_CUSTOMERS_SIGNATURE_OFF,document.Form1.TXT_CUSTOMERS_SIGNATURE_ON)\"></td>"); 
					out.println("<td width='*%'>...............................................................</td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW26 >Receiver's Signature/ Designation </td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_RECEIVERS_SIGNATURE_ON'  onclick=\"Val_Change(document.Form1.TXT_RECEIVERS_SIGNATURE_ON,document.Form1.TXT_RECEIVERS_SIGNATURE_OFF)\"></td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_RECEIVERS_SIGNATURE_OFF' onclick=\"Val_Change(document.Form1.TXT_RECEIVERS_SIGNATURE_OFF,document.Form1.TXT_RECEIVERS_SIGNATURE_ON)\"></td>"); 
					out.println("<td width='*%'>...............................................................</td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW23 >Copy Of the Police Report</td>"); 
					//out.println("<td width='10%' ><input  type='checkbox' name='TXT_POLICE_REPORT_ON'  onclick=\"Val_Change(document.Form1.TXT_POLICE_REPORT_ON,document.Form1.TXT_POLICE_REPORT_OFF)\"></td>"); 
					//out.println("<td width='10%' ><input  type='checkbox' name='TXT_POLICE_REPORT_OFF' onclick=\"Val_Change(document.Form1.TXT_POLICE_REPORT_OFF,document.Form1.TXT_POLICE_REPORT_ON)\"></td>"); 
					out.println("<td width='*%'>...............................................................</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 


					out.println("<tr>"); 
					out.println("<td width='25%' ID=ROW25 >Reposition Agent Signature</td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_SEIZERS_SIGNATURE_ON'  onclick=\"Val_Change(document.Form1.TXT_SEIZERS_SIGNATURE_ON,document.Form1.TXT_SEIZERS_SIGNATURE_OFF)\"></td>"); 
					//out.println("<td width='30%' ><input  type='checkbox' name='TXT_SEIZERS_SIGNATURE_OFF' onclick=\"Val_Change(document.Form1.TXT_SEIZERS_SIGNATURE_OFF,document.Form1.TXT_SEIZERS_SIGNATURE_ON)\"></td>"); 
					out.println("<td width='*%'>...............................................................</td>"); 
					out.println("</tr>"); 

					


					
					/*out.println("<tr>"); 
					out.println("<td width='30%' >Licence *</td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LICENCE' maxlength='20' size='10'></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='30%' >Insurance * </td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INSURANCE' maxlength='20' size='10'></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 


          out.println("<tr>"); 
					out.println("<td width='30%' >Cassette/Radio *</td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_licence' maxlength='3' size='10'></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					*/


		
				  out.println("</table>");
					
                  
				
				
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
					
				 out.println("</table>");
					
					
					
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
					
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" name='btn_new1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_edit1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_save1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_help1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_cancel1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" name='btn_close1' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='15%' align='left'><input  style='width:100'; type='button' class='mainbut' name='VALUATION_LINK_BUT' value=\"Valuation\" onClick=\"load_valuation()\"></td>"); // font-size: 20px; 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					out.println("</td></tr>");  

			
			  
					
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
  //    }
			
			//=========================================================================================================================			
  	
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
