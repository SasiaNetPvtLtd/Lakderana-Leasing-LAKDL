 
//Created by Nuwan De Silva 
//Collection Due Status

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Due_Status extends javax.servlet.http.HttpServlet {
	
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
			
 // 	 if(m_chksql.trim().equals("main_page")){
			
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("var lineno=0;");
			  out.println("var arr_size=0;");
				
				out.println("var m_sort_column='ENT_DATE';");
				out.println("var m_order_by_type='ASC';");
					
				out.println("function sort_data(m_sort_col) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col==m_sort_column){");
				out.println("	   if(m_order_by_type=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("       order_by_type = 'ASC'; ");
			  out.println("  }");
				
				out.println("m_sort_column=m_sort_col;");
				out.println("m_order_by_type=order_by_type;");
				
			  //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&ac_status=VERIFY\";");
				out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,m_sort_column,m_order_by_type);");
				//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			 // out.println(" window.location.href=m_url;"); 
				
			//	out.println("load_interface(m_url,'XML');");
				
				out.println("}");


				
				
					//!--------Display The Header -------------------------------------//
			out.println("function header(){");
			
			  out.println("m_table.innerHTML=\"\" ");
				out.println("lineno=0;");
			  out.println("arr_size=0;");
		
		      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
					out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Invoice No  \"    onclick=sort_data(\"INVOICE_NO\") align=\"left\"><B>Invoice No</td>'+");
          out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Finanace No  \"    onclick=sort_data(\"FINANCE_NO\") align=\"left\"><B>Finance No</td>'+");
          out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Invoice Date  \"    onclick=sort_data(\"INVOICE_DATE\") align=\"left\"><B>Invoice Date</td>'+");
     //     out.println("'<td  width=\"10%\" align=\"left\"><B>Issued Date</td>'+");
					out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - No of Days Due  \"    onclick=sort_data(\"NO_OF_DAYS_DUE\") align=\"left\"><B>No of Days Due</td>'+");
					out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Amount Due  \"    onclick=sort_data(\"AMOUNT_DUE\") align=\"right\"><B>Amount Due</td>'+");
					out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Collection Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\") align=\"left\"><B>Collection Officer</td>'+");
					out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Transaction Date  \"    onclick=sort_data(\"TRN_DATE\") align=\"left\"><B>Transaction Date</td>'+");
					out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Receipt Not Allocated  \"    onclick=sort_data(\"RECEIPT_NOT_ALLO\") align=\"right\"><B>Receipt Not Allocated</td>'+");
					out.println("'<td  width=\"10%\" align=\"center\"><B>Status</td>'+");
			    out.println("'</TR></table>';");		

     	out.println("}");
				
				
					out.println("function change_val_req(row_no){")	;
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			
			
		
			out.println("if(document.Form1.elements[m_chk_required].checked==true){");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
			out.println("document.Form1.elements[m_chk_required].value='off'");
			out.println("}");	
			//out.println("alert(document.Form1.elements[m_chk_required].value);");
			out.println("}");	
				
				
				
					out.println("  function  display_data(data_vec){");
			  				 		
							
					out.println("header();	");		
					
					 out.println("var i=0;");
					 out.println("var j=0;");
			   
					 
						
					 out.println("while(i<data_vec.length){");
						
								
																
						
						out.println("m_inv_no='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i]+'</TD>';");
						out.println("m_fin_no='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+1]+'</TD>';");
						out.println("m_inv_date='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
						out.println("m_no_of_days='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+3]+'</TD>';");
						out.println("m_amt_due='<TD WIDTH=\"12%\" align=\"right\">'+data_vec[i+4]+'</TD>';");
            out.println("m_col_officer='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+5]+'</TD>';");		
						out.println("m_trn_date='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+6]+'</TD>';");		
						out.println("m_receipt_not='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+7]+'</TD>';");		
					//	out.println("m_pur_date='<TD WIDTH=\"10%\" align=\"center\">'+data_vec[i+8]+'</TD>';");		
						out.println("m_status='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
						
						
			
						out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_INVOICE_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_NO_OF_DAYS'+lineno+'	VALUE='+data_vec[i+3]+'>';");
						
						out.println("if(j>0 && j%2==1){");
          //       	out.println("<tr class=\"tr_input1\" >");
										out.println("m_writedata='<TR class=\"tr_input1\">'+m_inv_no+m_fin_no+m_inv_date+m_no_of_days+m_amt_due+m_col_officer+m_trn_date+m_receipt_not+m_status+'</TR>'+m_hid_input;"); 
								out.println("	}");
								out.println("	else{");
            //      	out.println("<tr class=\"tr_input\" >");
										out.println("m_writedata='<TR class=\"tr_input\">'+m_inv_no+m_fin_no+m_inv_date+m_no_of_days+m_amt_due+m_col_officer+m_trn_date+m_receipt_not+m_status+'</TR>'+m_hid_input;"); 
								out.println("	}");
			
	    
			    //  out.println("m_writedata='<TR>'+m_pur_no+m_app_no+m_ven_no+m_issued_date+m_dr_date+m_net_amt+m_tot_vat+m_pur_date+m_required+'</TR>'+m_hid_input;"); 
	  			  
						
						out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    	out.println("m_writedata+'</table>';");
					 	
							out.println("j=j+1;");
			
						out.println("i=i+8;");
						out.println("lineno=lineno+1;");
						out.println("arr_size=arr_size+1;");		
						out.println("}"); //End while loop
		

			out.println("}");		
				
				  
					
										
				
		//--------------------------------------------------------------------------		
		
		
		out.println("function makeRequest(obj) {");
			
					
			out.println("if(document.Form1.hid_chk_status.value=='M1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			
			out.println("load_interface(m_url,'XML');");
		//		out.println("window.open(m_url)");
			
			out.println("}"); 
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
							
			out.println("function get_vector(data_vec) {");
			
											
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'){");
			out.println("				display_data(data_vec);");
			out.println("			}");
			
			out.println("else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3'){");
			out.println("alert('No records available');");
			out.println("			}");
			
			out.println("else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,'ENT_DATE','DESC');");
			out.println("			}");

			out.println("else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("help_button_2();");
			out.println("			}");

			
			out.println("			}");
			
			
			out.println("function get_due_letters(val,m_sort_column,m_order_by_type){");
		//out.println("alert('approval');");
		out.println("assignState('M3');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Due_Status&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&data_val=\"+val+\"&ac_status=Y\";");
			
		//	out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");	
			
			
			
			out.println("function validate_data(){"); 
			
			
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
		  out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
		
		
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("ckeck_issues();");
			out.println("if(b_flag==0)");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Due_Status';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Status';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Status';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Due_Status\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Due Letter Status - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Due Letter Status - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
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
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
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
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
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
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
		//	out.println("document.Form1.TXT_TEMP_REC_NO.focus();");
			out.println("	}");
				out.println("	}");
			
			
				out.println("function help_button_2() {"); 
		//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
		//	out.println("    m_sql = \"TXT_CLIENT_CODE\";"); 
		//	out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				
			out.println("    HelpBox('1','10','2',Crit,'m_help_TXT_CLIENT_CODE','2');"); 
			
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,'ENT_DATE','ASC');");
			
			out.println("}"); 
			
			
			
				out.println("function ckeck_issues(){ "); 
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No records to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			//out.println("else if(!count_offers()){"); 
			//out.println("alert('Please select an offer');");
			//out.println("b_flag=1;");
			//out.println("}"); 
			out.println("else");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			


						
			
      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_DUE_STATUS\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					
					
										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Due Letter Status - New</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
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
					
					
				  out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>"); 
			    out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			    out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			    out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					out.println("</table>");
                  
				
				
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
    //  }
			
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
