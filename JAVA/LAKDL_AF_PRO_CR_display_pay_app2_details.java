//--
//SCREEN NAME:Credit Process - Payment Approval 2
//CREATED BY :Delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_pay_app2_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt2,stmt1,stmt,stmt3,stmt4,stmt5,stmt6;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs2,rs1,rs,rs3,rs4,rs5,rs6;
	public String m_chksql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			stmt6 = conn.createStatement();

			String m_application_no = req.getParameter("APP_NO");
			String m_status1 = req.getParameter("chksql1");
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(0);
		  nf.setMaximumFractionDigits(0);
			String m_client="";
						
			String m_sus_ref = req.getParameter("SUS_REF_NO");
			String m_ref = req.getParameter("REF_NO");
			String m_pay_no = req.getParameter("PAY_NO");
			String m_value_date = req.getParameter("VALUE_DATE");
			double m_balance = Double.parseDouble(m_sn_methods.met_unformat_number(req.getParameter("BAL")));
			double m_total_settle=Double.parseDouble(req.getParameter("TOT_SET")); 
			double m_initial_bal=Double.parseDouble(req.getParameter("INIT_BAL")); 
			double m_paid_amt=Double.parseDouble(req.getParameter("PAID_AMT")); 
			
			
			rs= stmt.executeQuery(" SELECT APPLICATION_NO,CLIENT_CODE, "+
			" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) NAME,FINANCE_NO "+
			" FROM  "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
			boolean more=rs.next();
			String  _m_client      = rs.getString(2);
			String  _m_client_name = rs.getString(3);
			String  _m_finance_no  = rs.getString(4);


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Payment Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			//Declare Global Variables
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
				
			//Declare Global Arrays
			out.println("var array_follow_up_no=new Array();");
			out.println("var array_condition=new Array();");
			out.println("var array_status=new Array();");
			out.println("var array_document=new Array();"); //added by nuwan de silva on 09-11-07
			out.println("var array_stage=new Array();");
			out.println("var array_document_code=new Array();");
			
			
			
			out.println("var header;");		
			out.println("var ln=0;");
			out.println("var len=0;");
			out.println("var dist=0");
		  out.println("var b_flag=1");
			out.println("var b_flag_1=0");
			out.println("var b_flag_2=0");
			out.println("var b_flag_3=0");
			
			out.println("var b_mesg=0");
			out.println("var b_mesg1=0");
			out.println("var b_dates=0");
			out.println("var b_dates1=0");
			out.println("var b_dates2=0");
			out.println("var b_dates3=0");
			out.println("var b_count=0");
			out.println("var b_count_new=0");
						
						
			out.println("function get_conditions(){ "); 
			out.println("assig('M_CON')");		
		 // out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_application_no+"&ac_status=COMPLETED\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_pay_no+"&ac_status=COMPLETED\";");
		  out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&scr_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN&status=Y&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
			
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			
		 	/*out.println("function header_con(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
			*/
			
			out.println("function header_con(){");
			out.println("m_table.innerHTML=\"\" ");	
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR class=pdn_txtpos2>'+");
			out.println("'<TD WIDTH=\"12%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"7%\" align=\"left\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"left\">Type</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Document</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Stage Entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'</TR></table>';");
     	out.println("}");

			
			/*out.println("function display_data(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("header_con();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand; }\" onclick=\"load_Follow('+lineno+')\" ><U>Follow up</TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("i=i+3;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0){");
			out.println("add_row()");
			out.println("}");
			out.println("}");
			*/
			
			out.println("function display_data(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("header_con();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("      '<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno+')\" ><U>Follow up</U></TD>';");//&nbsp;&nbsp;Follow up
			out.println("if(data_vec[i+3]==\"-\"){");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >-</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" ) >'+data_vec[i+2].replace('$','&')+'</TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			out.println("else {");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >Document</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+data_vec[i+6]+'\") ><U>'+data_vec[i+2].replace('$','&')+'</U></TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\"   >'+data_vec[i+3]+'</TD>';");
			out.println("m_condition='<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>';");
			out.println("m_status='<TD WIDTH=\"10%\"  align=\"left\">'+data_vec[i+5]+'</TD>';");
			out.println("m_button='<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>';");
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+5]+'>'+");
		  out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	VALUE=\"'+data_vec[i+2].replace('$','&')+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	VALUE=\"'+data_vec[i+6]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	VALUE=\"'+data_vec[i+3]+'\" >';");
			out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("i=i+7;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0){");
			out.println("add_row()");
			out.println("}");
			out.println("}");

			
						
			/*----------------------------------------------------------------
					Purpose  : Add Conditions
				
			----------------------------------------------------------------*/			

			/*out.println("function add_row(){"); 
			out.println("var b_flag_con=0;");
			out.println("if(lineno!=0){");
			out.println("count=lineno-1;");
			out.println("m_condition=\"TXT_CONDITION\"+count");
			out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			out.println("alert('Condition can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("}");
			out.println("if(b_flag_con==0){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
     	out.println("}");
	    out.println("}");
			*/
			
			
			 out.println("function add_row(){"); 
			 out.println("b_flag=0;");
			 out.println("if(lineno!=0){");
			 out.println("count=lineno-1;");
			 out.println("m_condition=\"TXT_CONDITION\"+count");
			 out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			 out.println("alert('Condition can not be null.');");
			 out.println("b_flag=1;");
			 out.println("}");
			 out.println("}");
			 out.println("if(b_flag==0){");
			 out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			 out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
			 out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			 out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
		   out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	        VALUE=\"-\">'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	            VALUE=\"-\" >'+");
			 out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	    VALUE=\"-\" >'+");
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			 out.println("'</tr></table>';");
			 out.println("lineno=lineno+1;");
			 out.println("arr_size=arr_size+1;");
       out.println("}");
		   out.println("}");

			/*out.println("function del_row(rowNo){"); 
			out.println("if(rowNo!=0){");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			out.println("m_condition=\"TXT_CONDITION\"+i");
			out.println("m_status=\"hid_TXT_STATUS\"+i");					
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_follow_up_no[j]=document.Form1.elements[m_follow_up].value;");
			out.println("array_condition[j]=document.Form1.elements[m_condition].value;");
		  out.println("array_status[j]=document.Form1.elements[m_status].value;");    
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}");
		  */
			
			
			 out.println("function del_row(rowNo){"); 
			 out.println("if(arr_size!=1){");
			 out.println("var j=0;");
			 out.println("for(var i=0;i<arr_size;i++){");
			 out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			 out.println("m_condition=\"TXT_CONDITION\"+i");
			 out.println("m_status=\"hid_TXT_STATUS\"+i");					
			 out.println("m_document=\"hid_DOCUMENT\"+i");					
			 out.println("m_document_code=\"hid_DOCUMENT_CODE\"+i");					
			 out.println("m_stage=\"hid_STAGE\"+i");			
			 out.println("if(i==rowNo)");
			 out.println("continue;");
			 out.println("array_follow_up_no[j]       = document.Form1.elements[m_follow_up].value;");
			 out.println("array_condition[j]          = document.Form1.elements[m_condition].value;");
		   out.println("array_status[j]             = document.Form1.elements[m_status].value;");    
			 out.println("array_document[j]           = document.Form1.elements[m_document].value;"); //added by nwuan de silva on 09-11-07
			 out.println("array_stage[j]              = document.Form1.elements[m_stage].value;");
			 out.println("array_document_code[j]     = document.Form1.elements[m_document_code].value;"); //added by nwuan de silva on 09-11-07
			 out.println("j=j+1;");
			 out.println("}");
			 out.println("lineno=lineno-1;");
			 out.println("arr_size=arr_size-1;");
		   out.println("write_data(arr_size);");
			 out.println("}");


			/*out.println("function write_data(size){");
			out.println("sum=0;");
			out.println("m_table.innerHTML=\"\";");
			out.println("header_con();");
      out.println(" for(var j=0;j<size;j++){");
		  out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
		  out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" value='+array_condition[j]+' maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand; }\" onclick=\"load_Follow('+j+')\" ><U>Follow up</TD>'+"); //Follow up
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">'+array_status[j]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE='+array_status[j]+'>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
		 	out.println("}");		
			out.println("}");		
		  out.println("}");		
		  */
			
				out.println("function write_data(size){");
				out.println("sum=0;");
				out.println("m_table.innerHTML=\"\";");
				out.println("header_con();");
				out.println(" for(var j=0;j<size;j++){");
				out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
				out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\"  style=\"{width:110px;}\"  disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	      VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	        VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	            VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	    VALUE=\"-\" >'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");
				out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
				out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	     VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	       VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	           VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	 VALUE=\"-\" >'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");
				
				out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
				out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</TD>';"); //Follow up
				
				out.println("if(array_stage[j]==\"-\"){");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">-</TD>';");
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" >'+array_document[j]+'</TD>';");
				out.println("}");
				out.println("else {");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">Document</TD>';");
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+array_document_code[j]+'\") ><u>'+array_document[j]+'</u></TD>';");
				out.println("}");
				
				out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\">'+array_stage[j]+'</TD>';");
				out.println("m_condition='<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>';");
				out.println("m_status='<TD WIDTH=\"10%\" align=\"left\">'+array_status[j]+'</TD>';");
				out.println("m_button='<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>';");
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	        VALUE='+array_status[j]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	          VALUE=\"'+array_document[j]+'\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	              VALUE=\"'+array_stage[j]+'\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	      VALUE=\"'+array_document_code[j]+'\" >';");
				
				out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("continue;");
				out.println("}");
				out.println("}");		
				out.println("}");		
				out.println("}");		


			out.println("function get_vector(data_vec) {");
			out.println("	 if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B1\" && document.Form1.TXT_BRANCH_CODE.value!=''){");
			out.println("    document.Form1.TXT_ACC_NO.value='';");
			out.println("help_button_4('0','10','0','m_help_TXT_BRANCH_CODE_sql','4')");
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"B2\" && document.Form1.TXT_ACC_NO.value!=''){");
			out.println("help_button_5('0','10','2','m_help_TXT_ACCOUNT_2_sql','5')");
			out.println("			}");
			out.println("	else if(document.Form1.hid_st.value==\"M_CON\"){");
			out.println("display_data(data_vec)");
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_st.value==\"J1\" && document.Form1.TXT_SETTLE_AMOUNT.value!=''){");
			out.println("alert('Entered amount is greater than balance needed to be paid')");
			out.println("document.Form1.TXT_SETTLE_AMOUNT.value=\"\"");
			out.println("			}");
			out.println("}");

			out.println("function check_account(obj) {");
			out.println("assig('B2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function assig(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			out.println("}");
			

			out.println("function check_district(row) {");//To check district.
			out.println("document.Form1.hid_val_1.value=row");
			out.println("di='TXT_DISTRICT_CODE_'+row;");
			out.println("assig('A4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_district&data_val=\"+document.Form1.elements[di].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			/*out.println("function before_submit(){ ");
			out.println("		check_foll_con();");
		  out.println("	if(m_cond_status==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("alert('Please complete the pending status')");
			out.println("}");
			out.println("else{");

			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details3?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_2&status=APPRO2';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			*/
			
						out.println("function check_foll_rem(){");
			out.println("b_count=0");
			out.println("for(var x=0;x<document.Form1.hid_asset_doc_no.value;x++){");
			out.println("if(document.Form1.hid_asset_doc_no.value !=\"0\"){");
			out.println("  chk_status=\"chk_status_\"+x");			
			out.println("  chk_napp=\"chk_napp_status_\"+x;");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+x;");
			out.println("  foll_status=\"chk_fol_status_\"+x;");
			out.println("if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_1=0");
			out.println("b_mesg=0");
			out.println("}");
			out.println("if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value==\"\"){");
			out.println("alert('Please enter folloup remark for asset documents')");
			out.println("document.Form1.elements[foll_status].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");		
			out.println("else if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk_status].checked==true || document.Form1.elements[chk_napp].checked==true || document.Form1.elements[foll_status].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg=0");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("}");//if
			out.println("}");//for
			out.println("if(b_count!=document.Form1.hid_asset_doc_no.value){");
			out.println("b_count_new=0;");
			out.println("alert('Please enter either status ,not applicable or follow up for asset details')");	
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(b_count==document.Form1.hid_asset_doc_no.value){");
			out.println("b_count=0");
			out.println("for(var j=0;j<document.Form1.hid_client_doc_no.value;j++){");
			out.println("if(document.Form1.hid_client_doc_no.value !=\"0\"){");
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+j;");
			out.println("  chk_client_status=\"chk_client_status_\"+j");
			out.println("  foll_client_rem=\"TXT_CLIENT_FOL_REMARKS_\"+j;");
			out.println("  foll_client_status=\"chk_client_fol_status_\"+j;");
			out.println("if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[foll_client_status].checked==false){");
			out.println("b_mesg1=0");
			out.println("b_flag_1=0");
			out.println("}");

			out.println("if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value==''){");
		  out.println("alert('Please enter folloup remark for client documents')");
			out.println("document.Form1.elements[foll_client_rem].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg1=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");
			out.println("else if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			
			out.println("if(document.Form1.elements[foll_client_status].checked==true || document.Form1.elements[chk_client_status].checked==true || document.Form1.elements[chk_client_napp].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg1=1");
			out.println("}");
			
			out.println("b_count=b_count+b_flag_1");
			
			out.println("}");//if
			out.println("}");//for
			out.println("if(b_count!=document.Form1.hid_client_doc_no.value){");
			out.println("alert('Please enter either status ,not applicable or follow up for client details')");
			out.println("b_count_new=0;");
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("}");
			out.println("}");

			
			/*out.println("function before_submit(){ ");
			out.println("check_foll_rem()");
			out.println("if(document.Form1.hid_asset_doc_no.value==0 && document.Form1.hid_client_doc_no.value==0){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(b_count_new==1){");
			out.println("check_dates()");
			out.println("if(b_dates0==0){");
			out.println("alert('Please enter CR Book Date')");
			out.println("}");
			out.println("else{");	
			out.println("if(b_dates1==0){");
			out.println("alert('Please enter Insurance Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates2==0){");
			out.println("alert('Please enter Revenue License Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates3==0){");
			out.println("alert('Please enter Luxury Tax Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_dates4==0){");
			out.println("alert('Please enter Driving Licence Date')");
			out.println("}");
			out.println("else{");
			out.println("if(b_count_new==1 && b_dates0==1 &&  b_dates1==1 &&  b_dates2==1 && b_dates3==1 && b_dates4==1){");//

			out.println("for(var i=0;i<document.Form1.hid_invoice_no.value;i++){");
			out.println("vh='TXT_VEHICLE_NO_'+i;");
			out.println("en='TXT_ENGIN_NO_'+i;");
			out.println("ch='TXT_CHASSIS_'+i;");
			out.println("district='TXT_DISTRICT_CODE_'+i;");
   		out.println(" if (document.Form1.elements[en].value==''){");
			out.println("alert('Please enter Engine No');");
			out.println("document.Form1.elements[en].focus();");
			out.println("}");
   		out.println("else if (document.Form1.elements[ch].value==''){");
			out.println("alert('Please enter Chassis No');");
			out.println("document.Form1.elements[ch].focus();");
			out.println("}");
			out.println("else if (document.Form1.elements[vh].value==''){");
			out.println("alert('Please enter Vehicle No');");
			out.println("document.Form1.elements[vh].focus();");
			out.println("}");
			out.println("else if (document.Form1.elements[district].value==''){");
			out.println("alert('Please enter District');");
			out.println("document.Form1.elements[district].focus();");
			out.println("}");
						
			out.println("else if (document.Form1.elements[\"TXT_CR_BOOK_NO_\"+i].value==''){");
			out.println("alert('Please enter CR Book No');");
			out.println("document.Form1.elements[\"TXT_CR_BOOK_NO_\"+i].focus();");
			out.println("}");		
			out.println("}");
			out.println("if(document.Form1.elements[vh].value!=\"\" && document.Form1.elements[district].value!=\"\" && document.Form1.elements[en].value!='' && document.Form1.elements[ch].value!=''){");
			out.println("		check_foll_con();");
		  out.println("	if(m_cond_status==0 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details3?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_2&status=APPRO2';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); //
			
			
			out.println("} "); 
			out.println("}"); 
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("} ");
      out.println("} ");
			*/
			
			
			out.println("function before_submit(){ ");
			out.println("validate_documents_check();");
			out.println("b_count_new=1");
			out.println("if(document.Form1.hid_asset_doc_no.value==0 && document.Form1.hid_client_doc_no.value==0){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(m_chk_status_doc==1){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details3?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_1&status=APPRO1';");  
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details3?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_2&status=APPRO2';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}");
			out.println("		else{"); 
			out.println("		alert('Please Check The Documents');");
			out.println("		}");
			out.println("}");
			
			//added by nuwan de silva on 06-02-2008
			out.println("function validate_documents_check(){");
			out.println("m_chk_status_doc=0;");
			//out.println("alert('"+m_status1+"');");
			out.println("if('"+m_status1+"'=='A'){");
			out.println("if(document.Form1.chk_status_asset.value=='Y' &&  document.Form1.chk_status_client.value=='Y' ){");
			out.println("m_chk_status_doc=1");
			out.println("}");
			out.println("}");
			out.println("else {");
			out.println("m_chk_status_doc=1");
			out.println("}");
			out.println("}");

			
			

			out.println("function load_lock(){	"); 
			out.println("get_conditions();");//Added By Nuwan De Silva //07/02/2007.Conditions
			
			//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("document.Form1.elements[i].disabled=true;");
			//out.println("}");
			
			out.println("document.Form1.save.disabled=false;");
			out.println("document.Form1.help.disabled=false;");
			out.println("document.Form1.cancel.disabled=false;");	
			out.println("document.Form1.close.disabled=false;");
			out.println("document.Form1.save1.disabled=false;");
			out.println("document.Form1.help1.disabled=false;");
			out.println("document.Form1.cancel1.disabled=false;");	
			out.println("document.Form1.close1.disabled=false;");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app2_details?PAY_NO="+m_pay_no+"&APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app1_details?PAY_NO="+m_pay_no+"&APP_NO="+m_application_no+"&BAL="+m_balance+"&VALUE_DATE="+m_value_date+"&SUS_REF_NO="+m_sus_ref+"&REF_NO="+m_ref+"&chksql1="+m_status1+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance - Payment Approval 2 - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance - Payment Approval 2 - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";");
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
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
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("clear_data()");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("else if(oBj.valout[4] != \" \"){ ");
			out.println("Crit = oBj.valout[4];");
			out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
			out.println("}	");
			out.println("if(oBj.valout[4]==' '){ ");
			out.println("Close()	");
			out.println("}	");
			out.println("}");	
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
			out.println("dist=\"0\"");
      out.println(" }");
			
			out.println("function clear_data(){");
			out.println("if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("    document.Form1.TXT_APP_NAME.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value='';"); 
			out.println(" document.Form1.elements[district].focus();"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println(" document.Form1.TXT_ACC_NO.value='';"); 
			out.println(" document.Form1.TXT_ACC_NO.focus();"); 
			out.println(" document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println("}");
			out.println("}");		

			out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
			out.println("}");		
				
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
				
			out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
			
			out.println("function help_button_1(row) {"); 
			out.println("dist=\"1\"");
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    Sql = \"m_help_TXT_DISTRICT_CODE_sql1\";");
			out.println(" district='TXT_DISTRICT_CODE_'+row");
			out.println("    Crit = document.Form1.elements[district].value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,1);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1(oBj) {"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value=oBj.valout[2];"); 
			out.println("dist=\"0\"");
			out.println("}"); 

			out.println("function help_button_5(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_ACCOUNT_2_sql\";"); 
			out.println("    Crit = document.Form1.TXT_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5(oBj) {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function check_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("if(document.Form1.elements[chk_napp].checked==true && document.Form1.elements[chk_status].checked==true){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			out.println("function check_napp_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_status].checked==true && document.Form1.elements[chk_napp].checked==true){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("document.Form1.elements[chk_status].checked=false");
			out.println("document.Form1.elements[chk_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("}");	
			out.println("}");	
		
			
			out.println("function check_client_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");
			out.println("if(document.Form1.elements[chk_client_napp].checked==true && document.Form1.elements[chk_client_status].checked==true){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			
			out.println("function check_client_napp_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_client_status].checked==true && document.Form1.elements[chk_client_napp].checked==true){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("document.Form1.elements[chk_client_status].checked=false");
			out.println("document.Form1.elements[chk_client_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[chk_client_napp].checked==false){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_client_change_fol(row){");
			out.println("  foll_rem=\"TXT_CLIENT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_client_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_status=\"chk_client_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");	
		
			out.println("function check_change_fol(row){");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");	
			
			out.println("function show_document_follow_up(m_type){");		
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql=main_page&screen_name=account_selection&payment_no="+m_pay_no+"&client_code="+_m_client+"&value_date="+m_value_date+"&application_no="+m_application_no+"&type=\"+m_type+\"\";");
			out.println("window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
			out.println("");
			out.println("}");	
			
			out.println("function check_status_document(obj){")	;
			out.println("if(obj.checked==true){");
			out.println("obj.value='Y'");
			out.println("}else{");	
			out.println("obj.value='N'");
			out.println("}");	
		  out.println("}");	

			out.println("function check_dates(){");
			out.println("for(var f=0;f<document.Form1.hid_invoice_no.value;f++){");
			out.println("print_dd='TXT_PRINT_DATE_DD'+f;");
			out.println("print_mm='TXT_PRINT_DATE_MM'+f;");
			out.println("print_yy='TXT_PRINT_DATE_YY'+f;");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+f;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+f;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+f;");
			out.println("rev_dd='TXT_REVENUE_LICENSE_DATE_DD'+f;");
			out.println("rev_mm='TXT_REVENUE_LICENSE_DATE_MM'+f;");
			out.println("rev_yy='TXT_REVENUE_LICENSE_DATE_YY'+f;");
			out.println("lu_dd='TXT_LUXURY_TAX_DATE_DD'+f;");
			out.println("lu_mm='TXT_LUXURY_TAX_DATE_MM'+f;");
			out.println("lu_yy='TXT_LUXURY_TAX_DATE_YY'+f;");
			
			out.println("dr_dd='TXT_DRIVING_LICENSE_DATE_DD'+f;");
			out.println("dr_mm='TXT_DRIVING_LICENSE_DATE_MM'+f;");
			out.println("dr_yy='TXT_DRIVING_LICENSE_DATE_YY'+f;");
			
			out.println(" if(document.Form1.elements[print_dd].value==\"\" || document.Form1.elements[print_mm].value==\"\" || document.Form1.elements[print_yy].value==\"\"){");
			out.println("b_dates0=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates0=1");
			out.println("}");
			out.println(" if(document.Form1.elements[ind_dd].value==\"\" || document.Form1.elements[ind_mm].value==\"\" || document.Form1.elements[ind_yy].value==\"\"){");
			out.println("b_dates1=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates1=1");
			out.println("}");
			out.println(" if(document.Form1.elements[rev_dd].value==\"\" || document.Form1.elements[rev_mm].value==\"\" || document.Form1.elements[rev_yy].value==\"\"){");
			out.println("b_dates2=0");
			out.println("}");
			out.println("else{");
			out.println("b_dates2=1");
			out.println("}");
			out.println(" if(document.Form1.elements[lu_dd].value==\"\" || document.Form1.elements[lu_mm].value==\"\" || document.Form1.elements[lu_yy].value==\"\"){");
			out.println("b_dates3=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates3=1");
			out.println("}");
			out.println(" if(document.Form1.elements[dr_dd].value==\"\" || document.Form1.elements[dr_mm].value==\"\" || document.Form1.elements[dr_yy].value==\"\"){");
			out.println("b_dates4=0");
			out.println("}");
			out.println("else{");
		  out.println("b_dates4=1");
			out.println("}");
			out.println("}");
			out.println("}");

			out.println("function check_insu_date(ln){ ");
			out.println("ind_dd='TXT_INSURANCE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_INSURANCE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_INSURANCE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");

			out.println("function check_rev_date(ln){ ");
			out.println("ind_dd='TXT_REVENUE_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_REVENUE_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_REVENUE_LICENSE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" &&  document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");

			out.println("function check_tax_date(ln){ ");
			out.println("ind_dd='TXT_LUXURY_TAX_DATE_DD'+ln;");
			out.println("ind_mm='TXT_LUXURY_TAX_DATE_MM'+ln;");
			out.println("ind_yy='TXT_LUXURY_TAX_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\" ){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");
		
			out.println("function check_dri_date(ln){ ");
			out.println("ind_dd='TXT_DRIVING_LICENSE_DATE_DD'+ln;");
			out.println("ind_mm='TXT_DRIVING_LICENSE_DATE_MM'+ln;");
			out.println("ind_yy='TXT_DRIVING_LICENSE_DATE_YY'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println("}");
			out.println("}");
			
			out.println("function print_date_dd(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value;");
			out.println("}");
			
			out.println("function print_date_mm(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value;");
		  out.println("}");

			out.println("function print_date_yy(m_row){");
			out.println("if(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value!=\"\" && document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row]);");
			out.println(" }");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value;");
		  out.println("}");

			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
			out.println("else if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='5'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	

      out.println("  else if(document.Form1.hid_cal_date.value=='6'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");		
			
			out.println("else if(document.Form1.hid_cal_date.value=='7'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	


			out.println("}");		

			out.println("function close_screen(){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?screen_type=NEW&sql=main_page&status_new=NEW&chksql=B&chksql2=A&st_c=APPLICATION_NO&oby=ASC';");
			out.println("}");
			out.println("}");

			out.println("function check_date_from(){ ");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" || document.Form1.TXT_FROM_DATE_MM.value!=\"\" || document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");
			out.println("}");

			out.println("function check_foll_con(){");
			out.println("   document.Form1.hid_no_rec_con.value=arr_size;");
			out.println("for(var d=0;d<document.Form1.hid_no_rec_con.value;d++){");
			out.println("if(document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='Pending' || document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='--'){");
			out.println("m_cond_status=1");
			out.println("}");
			out.println("else{");
			out.println("m_cond_status=0");
			out.println("}");	
		  out.println("if(m_cond_status==1){");
			out.println("alert('Please complete the pending conditions');");
			out.println("break");
			out.println("}");	
			out.println("}");
			out.println("}");

//------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cnt_doc' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cnt_cr' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_oth_doc' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_val_1' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PAYMENT_REQUSITION_MAIN\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_payer' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_con' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_inv' VALUE=\"0\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Approval 2</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input name =\"save\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"help\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"cancel\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"close\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>"); 
		
		/*rs= stmt.executeQuery("SELECT APPLICATION_NO,CLIENT_CODE, "+
													""+m_schema_name+".af_co_get_client_name(CLIENT_CODE) NAME "+
													"FROM  "+
													""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
													"WHERE upper(application_no)=upper('"+m_application_no+"') ");

		
		boolean more=rs.next();
		*/
			out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");

			if(more){
			m_client=rs.getString(2);
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No</DIV></td>"); 
			out.println("<td width='30%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_application_no+"')\"><U>"+m_application_no+"<input class='txt_input' type='hidden' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<input type=hidden name=TXT_FINANCE_NO value="+_m_finance_no+" >");
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_APP_NAME'  class=div_input>Name of the Applicant</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+_m_client+"')\"><U>"+_m_client_name+"<input class='txt_input' type='hidden' value="+_m_client+"  name='TXT_CLIENT_CODE'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}
			
			out.println("</table>");
			
			out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE_DATE'  class=div_input>Value Date</DIV></td>"); 
			out.println("<td width='40%' >"+m_value_date+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_BAL'  class=div_input>Initial Amount</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_total_settle)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INIT_BAL'  class=div_input>Payments Already Processed</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_initial_bal)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>Balance To Be Paid</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_balance)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>This Payment</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_paid_amt)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");
			out.println("<br>");

			rs1=stmt1.executeQuery("SELECT "+
		    "A.PURCHASE_ORDER_NO, "+
		    "nvl(to_char(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'-') PURCHASE_ORDER_DATE , "+
		    "nvl(to_char(A.ISSUED_DATE,'dd-mm-yyyy'),'-') ISSUED_DATE, "+				
				"A.VENDER_CODE, "+				
		    "A.TOTAL_NET, "+
		    "A.TOTAL_VAT, "+
				"nvl((A.TOTAL_VAT+TOTAL_NET),0) TOTAL, "+
	      "NVL(A.EXCHANGE_RATE,0) EXCHANGE_RATE, "+				
		     "NVL(A.CURR_CODE,'-') CURR_CODE, "+
				""+m_schema_name+".af_co_get_vendor_name(A.VENDER_CODE) NAME "+
		 		"   FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
				"   WHERE A.PURCHASE_ORDER_NO  = B.PURCHASE_ORDER_NO "+
				"   AND   A.APPLICATION_NO     = UPPER('"+m_application_no+"') "+
				"   AND   A.ACTIVE_STATUS='VERIFY' "+ //added by ns on 25-05-2011
				"   AND   B.ACTIVE_STATUS='Y' "+ //added by ns on 25-05-2011
				"    ");
		
			int q=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs1.next()){

			if(q==0){
			out.println("<tr><td><b><u>Purchase Order Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order No</td>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order Date</td>"); 
			out.println("<td width='10%' align=\"left\">Issued Date</td>"); 
			out.println("<td width='15%' align=\"left\">Vendor Code</td>"); 
			out.println("<td width='15%' align=\"right\">Total</td>"); 
			out.println("<td width='15%' align=\"left\">Exchange Rate</td>"); 
			out.println("<td width='15%' align=\"left\">Currency Code</td>"); 
			
			out.println("</tr >"); 

			}
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_PURCHASE_ORDER_NO value=\""+rs1.getString(1)+"\"></td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs1.getString(4)+"')\"><U>"+rs1.getString(10)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(7))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			out.println("</tr>");
			q=q+1;
			}

			out.println("</table >"); 
			out.println("<HR>");
			out.println("<br>");
			
			rs2=stmt2.executeQuery("SELECT PAYMENT_NO,SUS_REF_NO,trim(PAY_AMOUNT),Decode(SETTLE_MODE,'CHQ','Cheque','CASH','Cash'), "+
		 "nvl(decode(ENTRY_TYPE,'V','Vendor','S','Supplier','E','Seizer'),'-'),to_char(EFF_VALDATE,'dd-mm-yyyy'), "+
		 "NVL(LIC_ACC_NO,'-'),NVL(LIC_BRANCH_CODE,'-'),nvl(PAYEE_NAME,'-'),nvl(TO_CHAR(LETTER_DATE,'dd-mm-yyyy'),'-'), "+
		 " NVL(TAX_INV_NO,'-'), NVL(TO_CHAR(TAX_INV_DATE,'DD-MM-YYYY'),'-') ,"+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE), NVL(TRIM(WHT),0),  NVL(TRIM(NET_AMOUNT),0) "+	
		 "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
		 "WHERE UPPER(PAYMENT_NO)=UPPER('"+m_pay_no+"')  ");   
			
		
			int a=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			double m_total=0;
			String m_tax_inv_num =""; 
      String m_tax_inv_date="";
			while(rs2.next()){
			m_tax_inv_num  = rs2.getString(11);
			m_tax_inv_date = rs2.getString(12);
			m_total=m_total+rs2.getDouble(3);
			if(a==0){
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='11%' align=\"left\">Payment No</td>");
			out.println("<td width='6%' align=\"left\">Settle Mode</td>");
			out.println("<td width='6%' align=\"left\">Entry Type</td>");
			out.println("<td width='8%' align=\"left\">Validate Date</td>");
			out.println("<td width='12%' align=\"left\">Account No</td>");
			out.println("<td width='15%' align=\"left\">Branch Name</td>");
			//out.println("<td width='10%' align=\"left\">Payee Category</td>");
			out.println("<td width='15%' align=\"left\">Payee Name</td>");
			out.println("<td width='6%' align=\"left\">Letter Date</td>");
			//out.println("<td width='10%' align=\"right\">Settle Amount</td>");
			out.println("<td width='8%' align=\"right\">Gross Payment</td>"); //Settle Amount changed to Gross payment by ashini on 06.03.208
			out.println("<td width='8%' align=\"right\">WHT</td>");//addded by ashini on 06.03.208
			out.println("<td width='8%' align=\"right\">Net Payment</td>");//addded by ashini on 06.03.208
			
			out.println("</tr >"); 
			}		


			if(a>0 && a%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(4)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(5)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(6)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs2.getString(7)+"')\"><U>"+rs2.getString(7)+"</td>"); 
			//out.println("<td align=\"left\"><input class='txt_input' type='text' name='TXT_ACC_NO' style=\"{width:100px;}\" maxlength='20' size='20' onblur=\"check_account(this)\">"); 
		  //out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_NO' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			//out.println("<td width='5%' align=\"left\" >&nbsp;</td>");
			out.println("<td align=\"left\">"+rs2.getString(13)+"</td>"); 
			//out.println("<td ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' maxlength='10' size='10'  style=\"{width:150px;}\" onblur=\"check_branch(this)\" disabled></td>"); 
			//out.println("<input type=\"hidden\" name=hid_TXT_BRANCH_CODE value=\"\">"); 
			//out.println("<td align=\"left\">Third Party</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(9)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(10)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs2.getDouble(3))+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs2.getDouble(14))+"<input class='txt_input' type='hidden' name='TXT_WHT_AMT' style=\"{text-align:right; width:75px;}\" maxlength='20' size='10'  VALUE=\""+nf.format(rs2.getDouble(14))+"\"></td>"); //addded by ashini on 06.03.208
			out.println("<td align=\"right\">"+nf.format(rs2.getDouble(15))+"<input class='txt_input' type='hidden' name='TXT_NET_AMT' style=\"{text-align:right; width:75px;}\" maxlength='20' size='10'  VALUE=\""+nf.format(rs2.getDouble(15))+"\"></td>"); //addded by ashini on 06.03.208
			out.println("</tr >"); 
			a=a+1;
			}

			out.println("</table >"); 
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr class=tr_input1>");
			out.println("<td width='80%'  align=right><b>Total Gross Payment Amount :</td>");
			out.println("<td width='20%' align=right><b>"+nf.format(m_total)+"</td>"); 
			
			out.println("</tr>"); 
			out.println("</table >"); 
			
			//_____________________________________Documets followp__________________________________________________________________________
		 
			out.println("<br><br>");
			out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
			out.println("<tr>");
			out.println("<td  width='20%' align=\"left\" ><input class='but_input' type='button' name='BUT_SHOW_DOC_ASSET' value=\"Asset Document Follow Up\" onclick=\"show_document_follow_up('asset_details')\" style=\"{width:150px;}\" ><input type=\"checkbox\" name=chk_status_asset  onclick=\"check_status_document(this)\"></td>"); 
			out.println("<td  width='60%' align=\"left\" ></td>");
			out.println("<td  width='20%' align=\"right\" ><input class='but_input' type='button' name='BUT_SHOW_DOC_CLIENT' value=\"Client Document Follow Up\" onclick=\"show_document_follow_up('client_details')\" style=\"{width:150px;}\" ><input type=\"checkbox\" name=chk_status_client  onclick=\"check_status_document(this)\"></td>"); 
			out.println("</tr>");
			out.println("</table>");

		
		

			
			/*out.println("<table align='center' width='100%' border=0 class='table'>"); 
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");
			out.println("<tr >");
			out.println("</tr >");

			if(more){
			m_client=rs.getString(2);
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No</DIV></td>"); 
			out.println("<td width='30%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_application_no+"')\"><U>"+m_application_no+"<input class='txt_input' type='hidden' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_CLIENT_CODE'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input class='txt_input' type='hidden' value="+rs.getString(2)+"  name='TXT_CLIENT_CODE'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='TXT_APP_NAME'  class=div_input>Name of the Applicant</DIV></td>"); 
			out.println("<td width='40%' >"+rs.getString(3)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			}

			out.println("<tr >"); 
			out.println("</tr >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Suspense Payment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUS_REF_NO'  class=div_input>Sus Ref No</DIV></td>"); 
			out.println("<td width='40%'  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+m_sus_ref+"')\"><U>"+m_sus_ref+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REF_NO'  class=div_input>Invoice No</DIV></td>"); 
			out.println("<td width='40%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+m_ref+"')\"><U>"+m_ref+"<input class='txt_input' type='hidden' name='TXT_REF_NO'>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("</tr >"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE_DATE'  class=div_input>Value Date</DIV></td>"); 
			out.println("<td width='40%' >"+m_value_date+"</td>"); 
 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_BAL'  class=div_input>Initial Amount</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_total_settle)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("</tr>"); 

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INIT_BAL'  class=div_input></DIV>Payments Already Processed</td>"); 
			out.println("<td width='40%' >"+nf.format(m_initial_bal)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>Balance To Be Paid</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_balance)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BAL_TO_BE_PAID'  class=div_input>This Payment</DIV></td>"); 
			out.println("<td width='40%' >"+nf.format(m_paid_amt)+"</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");

		 rs2=stmt2.executeQuery("SELECT PAYMENT_NO,SUS_REF_NO,trim(PAY_AMOUNT),Decode(SETTLE_MODE,'CHQ','Cheque','CASH','Cash'), "+
		 "nvl(decode(ENTRY_TYPE,'V','Vendor','S','Supplier','E','Seizer'),'-'),to_char(EFF_VALDATE,'dd-mm-yyyy'), "+
		 "NVL(LIC_ACC_NO,'-'),NVL(LIC_BRANCH_CODE,'-'),nvl(PAYEE_NAME,'-'),nvl(TO_CHAR(LETTER_DATE,'dd-mm-yyyy'),'-') "+
		 ",DECODE(PAYMENT_TYPE,'NORMAL','Normal','THIRD','Third Pary','-') "+
		 "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
		 //"WHERE UPPER(SUS_REF_NO) =UPPER('"+m_pay_no.trim()+"') "); //m_sus_ref // comment by nuwan de silva on 18-01-2008
       "WHERE UPPER(PAYMENT_NO) =UPPER('"+m_pay_no.trim()+"') "); //m_sus_ref // comment by nuwan de silva on 18-01-2008

		
			int a=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr><td><b><u>Payment Settlment Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			double m_total=0;

			while(rs2.next()){
			m_total=m_total+rs2.getDouble(3);

			if(a==0){


			out.println("<tr></tr>");
			
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='13%' align=\"left\">Payment No</td>");
			out.println("<td width='10%' align=\"right\">Settle Amount</td>");
			out.println("<td width='10%' align=\"left\">Settle Mode</td>");
			out.println("<td width='8%' align=\"left\">Entry Type</td>");
			out.println("<td width='10%' align=\"left\">Validate Date</td>");
			out.println("<td width='10%' align=\"left\">Account No</td>");
			out.println("<td width='10%' align=\"left\">Branch Code</td>");
			out.println("<td width='10%' align=\"left\">Payee Category</td>");
			out.println("<td width='10%' align=\"left\">Payee Name</td>");
			out.println("<td width='12%' align=\"left\">Letter Date</td>");
			out.println("</tr >"); 


			}		


			if(a>0 && a%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}


			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs2.getDouble(3))+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(4)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(5)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(6)+"</td>"); 
			
			//if (!rs2.getString(7).equals(null) ){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs2.getString(7)+"')\"><U>"+rs2.getString(7)+"</td>"); 
			//}
			//else{
			//out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs2.getString(7)+"')\"><U></td>"); 
			//}
			out.println("<td align=\"left\">"+rs2.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(11)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(9)+"</td>"); 
			out.println("<td align=\"left\">"+rs2.getString(10)+"</td>"); 
			out.println("</tr >"); 
			


			a=a+1;

			}

			out.println("</table >"); 
			out.println("<br>");
			out.println("<br>");
		
			out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr class=tr_input1>");
			out.println("<td width='13%'  align=right><b>Total Settle Amount :</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_total)+"</td>"); 
			out.println("<td width='*%'>&nbsp</td>");
			out.println("</tr>"); 
	
			out.println("</table >"); 
			out.println("<HR>");
			//-----------------
			out.println("<br>");


			rs1=stmt1.executeQuery("SELECT "+
		    "A.PURCHASE_ORDER_NO, "+
		    "nvl(to_char(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'-') PURCHASE_ORDER_DATE , "+
		    "nvl(to_char(A.ISSUED_DATE,'dd-mm-yyyy'),'-') ISSUED_DATE, "+				
				"A.VENDER_CODE, "+				
		    "A.TOTAL_NET, "+
		    "A.TOTAL_VAT, "+
				"nvl((A.TOTAL_VAT+TOTAL_NET),0) TOTAL, "+
	      "NVL(A.EXCHANGE_RATE,0) EXCHANGE_RATE, "+				
		     "NVL(A.CURR_CODE,'-') CURR_CODE, "+
				""+m_schema_name+".af_co_get_vendor_name(A.VENDER_CODE) NAME "+
		 		"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
				"WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				"AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
				
				"AND UPPER(B.PRO_INVOICE_NO) =UPPER('"+m_ref+"') ");
		
		
			int q=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			while(rs1.next()){

			if(q==0){
			out.println("<tr><td><b><u>Purchase Order Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order No</td>"); 
			out.println("<td width='15%' align=\"left\">Purchase Order Date</td>"); 
			out.println("<td width='10%' align=\"left\">Issued Date</td>"); 
			out.println("<td width='15%' align=\"left\">Vendor Code</td>"); 
			out.println("<td width='15%' align=\"right\">Total</td>"); 
			out.println("<td width='15%' align=\"left\">Exchange Rate</td>"); 
			out.println("<td width='15%' align=\"left\">Currency Code</td>"); 
			
			out.println("</tr >"); 

			}
			
			if(q>0 && q%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_PURCHASE_ORDER_NO value=\""+rs1.getString(1)+"\"></td>"); 
			out.println("<td align=\"left\">"+rs1.getString(2)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(3)+"</td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs1.getString(4)+"')\"><U>"+rs1.getString(10)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs1.getDouble(7))+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(8)+"</td>"); 
			out.println("<td align=\"left\">"+rs1.getString(9)+"</td>"); 
			out.println("</tr>");
			q=q+1;

			}

			out.println("</table >"); 
			
			*/
			

      //String m_tax_inv_num = "";
      //String m_tax_inv_date = "";
			
			int d=0;
			int f=0;
			int j=0;
			
      /*out.println("<HR>");
      out.println("<table align='center' width='100%' class='table' border=\"0\">");  

      rs1=stmt1.executeQuery(" SELECT NVL(TAX_INV_NO,'-'),NVL(TO_CHAR(TAX_INV_DATE,'DD-MM-YYYY'),'-') "+
			                       " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
														 " WHERE UPPER(SUS_REF_NO) = UPPER('"+m_sus_ref+"') ");

         
      if(rs1.next()){
			m_tax_inv_num  = rs1.getString(1);
			m_tax_inv_date = rs1.getString(2);
			
			}
			out.println("<tr><td><b><u>Tax Invoice Details</td></tr>"); //Added by Chandana on 14/12/2007
			out.println("<tr></tr>");
			out.println("<tr></tr>"); 
      out.println("<tr>");  
			out.println("<td  width='20%' align=\"left\" ><b>Tax Invoice No:</td><td width='20%' align=\"left\" style= \"cursor-color:blue\" ><input value=\""+m_tax_inv_num+"\" class='txt_input'  maxlength='30' type='hidden' name=TXT_TAX_INVOICE_NO disabled ><B>: "+m_tax_inv_num+"</B></td>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Date:</td><td width='20%' align=\"left\" style= \"cursor-color:blue\" ><input value=\""+m_tax_inv_date+"\" class='txt_input'  maxlength='30' type='hidden' name=TXT_TAX_INV_DATE disabled ><B>: "+m_tax_inv_date+"</B></td>");
			out.println("</tr>"); 
      out.println("<tr></tr>");
			out.println("<tr></tr>"); 
			out.println("</table >");



		  rs3=stmt3.executeQuery
			("SELECT INVOICE_NO,APPLICATION_NO, "+
		 "nvl(ENGINE_NO,'-'),nvl(CHASSIS_NO,'-'),nvl(REG_NO,'-'),nvl(CR_BOOK_NO,'-'),NVL(DISTRICT_CODE,'-'), "+
		 "nvl(to_char(CR_PRINT_DATE,'dd-mm-yyyy'),''),nvl(to_char(INSURANCE_DATE,'dd-mm-yyyy'),''),nvl(to_char(REVENUE_LICENSE_DATE,'dd-mm-yyyy'),''), "+
		 "nvl(to_char(LUXURY_TAX_DATE,'dd-mm-yyyy'),''),nvl(to_char(DRIVING_LICENSE_DATE,'dd-mm-yyyy'),''),VEHICLE_NO "+   
		 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		 "WHERE UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
		 "AND UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
		
			out.println("<hr>");

			int f=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<BR>");


			while(rs3.next()){

			if(f==0){
			out.println("<tr><td><b><u>Invoice Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b>Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs3.getString(1)+"')\"><U><b>:"+rs3.getString(1)+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='20%' align=\"left\">Engine No</td>");
			out.println("<td width='20%' align=\"left\">Chassis Mode</td>");
			out.println("<td width='20%' align=\"left\">Vehicle No</td>");
			out.println("<td width='20%' align=\"left\">Cr Book No</td>");
			out.println("<td width='20%' align=\"left\">District Code</td>");
			out.println("</tr >"); 

			}
			
				
			if(f>0 && f%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			
			
			
				//--------- added by nuwan ---de silva on 07-01-2008---------------------------------------
			if(rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(3)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+" disabled></td>"); 
			}		
			
			if(rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+"></td>"); 
			}	
			if(!rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(4)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+" disabled></td>"); 
			}	
			if(rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(5).trim()+"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+" disabled></td>"); 
			}
			
			if(rs3.getString(5).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" ></td>"); 
			}
			if(!rs3.getString(5).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(6)+"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" disabled></td>"); 
			}

			if(!rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(7)+"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"check_district("+f+")\">");		
			}
			if(rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"check_district("+f+")\">");		
			}
			out.println("<input class=but_input type=button name=BUT_TXT_DISTRICT_CODE_"+a+" value=\"Help\" onClick=help_button_1("+f+")></td>"); 

			out.println("</tr>");
			
			if(f==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' align=\"left\">CR Book Date</td>");
			out.println("<td width='20%' align=\"left\">Insurance Date</td>");
			out.println("<td width='20%' align=\"left\">Revenue License Date</td>");
			out.println("<td width='20%' align=\"left\">Luxury Tax Date</td>");
			out.println("<td width='20%' align=\"left\">Driving License Date</td>");
			out.println("</tr >"); 

			}
			
			if(f>0 && f%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
			if(rs3.getString(8)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\""+rs3.getString(8).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\""+rs3.getString(8).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\""+rs3.getString(8).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\" >");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");   
			}
			if(rs3.getString(8)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			if(rs3.getString(9)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\""+rs3.getString(9).substring(0,2)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\""+rs3.getString(9).substring(3,5)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\""+rs3.getString(9).substring(6,10)+"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			if(rs3.getString(9)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			if(rs3.getString(10)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(10).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(10).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(10).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			if(rs3.getString(10)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			if(rs3.getString(11)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\""+rs3.getString(11).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\""+rs3.getString(11).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\""+rs3.getString(11).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			if(rs3.getString(11)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			if(rs3.getString(12)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(12).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(12).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(12).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			if(rs3.getString(12)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			//-------------------------------------------------------------------

			out.println("</tr>");

			
			f=f+1;

			}
			out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("</table >"); 
			out.println("<HR>");




			//-------asset--
			 rs4=stmt4.executeQuery
				("SELECT DOCUMENT_TYPE,"+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),nvl(REMARK,'-') REMARK,nvl(decode(STATUS,'Y','Yes','N','No','A','Applicable'),'-') STATUS, "+
				"SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT, "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------
				
				
				"                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) SCREEN "+

				
				
				//--------------------------------------------------------------------------------------------------------------------------------------------
				
				
				
				
        "WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				
				"AND DOCUMENT_TYPE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT
				"AND STATUS!='Y' "+ // ADDED BY NUWAN DE SILVA 
        "AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+
				"AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+
        "AND DOCUMENT_TYPE IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
        "                          WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
        "                          AND CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) "+
        "                          ) "+
        " UNION "+
        "SELECT A.CODE CODE,"+m_schema_name+".AF_CO_GET_DOC_DESC(A.CODE),'-','-',SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A , "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------

        "                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				//"AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) SCREEN "+


				//--------------------------------------------------------------------------------------------------------------------------------------------
				
        "                           WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
				"														AND A.CODE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT

        "                           AND A.CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				//"AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) "+
				
				"														AND A.CODE NOT IN (SELECT DOCUMENT_TYPE "+
				"																						 FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
        "																						 WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				"																						 AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+

        "																						 AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+				
        "                                  ) order by STATUS ");

			int j=0;
			out.println("<br>");
			


			boolean more4=rs4.next();
			if(more4){
			out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more4){
			
			
			
			if(j==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Asset Document Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");

				
				
			out.println("</tr >"); 
			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
			else{
			
			if(j>0 && j%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
		}	
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs4.getString(1)+"')\"><U>"+rs4.getString(2)+"<input value="+rs4.getString(1)+" class='txt_input'  maxlength='50' type='hidden' name=TXT_DOC_CODE_"+j+"></td>"); 
			out.println("<td align=\"center\">"+rs4.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs4.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_status_"+j+" value=\"N\" unchecked onclick=\"check_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_napp_status_"+j+" value=\"N\" unchecked onclick=\"check_napp_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_REMARKS_"+j+"></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_fol_status_"+j+" value=\"N\" unchecked onclick=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_FOL_REMARKS_"+j+"></td>"); 
			out.println("</tr>");
			more4=rs4.next();
			j=j+1;
			}

			//out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");

			out.println("</table >"); 
			out.println("<HR>");
			}
			out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");

			//-----client---
			//out.println
			 rs5=stmt5.executeQuery
				(
			"SELECT CODE,DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS "+
			"			   FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+  
			" 			   WHERE     "+
			"             CODE IN ( SELECT  CODE "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) "+
			//"and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) "+
			"                 AND  DOC_APP_TYPE='CLIENT' "+
			"                 AND  ACTIVE_STATUS='Y'   "+
			
			"         UNION   "+
			        
			"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, DECODE(C.STATUS,'Y','Yes','N','No','A','Applicable') STATUS "+
			"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C  "+
			"  WHERE B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY   "+
			"                         FROM  "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                         WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                       )   "+
			            
			"  AND DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y')  "+
			"  AND C.DOCUMENT_TYPE IN  "+
			"                      (SELECT CODE  "+
			"                      FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
			"                      WHERE FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                               WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2')  "+
			"                      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                          WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN_APP_2') AND  "+
			"                      ENTITY_TYPE IN (SELECT CLIENT_CATEGORY   "+
			"                                       FROM "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                                       WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                                       )   "+
			  
			"   AND  PRODUCT_CODE in (SELECT TRANSACTION_TYPE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                         WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"and application_status='ACTIVATED') "+
			                                                                 
			"   AND B.CODE=A.CODE  "+
			"   AND ACTIVE_STATUS=('Y'))  "+
			"   AND C.DOCUMENT_TYPE=A.CODE  "+
			"   AND C.STATUS!='Y' "+ //added by nuwan de silva on 17-01-2008
			"   AND C.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			"   AND C.CLIENT_CODE=UPPER('"+m_client+"')  "+
			"   AND C.PRO_INVOICE_NO IS NULL "+
		//	"AND upper(C.OTHER_NO)=upper('"+m_sus_ref+"')  "+
			"  ORDER BY STATUS ASC ");
			
			int d=0;
			
      boolean more5=rs5.next();
			if(more5){
      out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			
			while(more5){
			
			
			
			if(d==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Client Document Details</td>"); 
		  out.println("</tr>"); 
				
				
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
		  out.println("</tr >"); 
			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
			else{

			if(d>0 && d%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}			
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs5.getString(1)+"')\"><U>"+rs5.getString(2)+"<input value="+rs5.getString(1)+" class='txt_input'  maxlength='50' type='hidden' name=TXT_CLIENT_DOC_CODE_"+d+"></td>"); 
			out.println("<td align=\"center\">"+rs5.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs5.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_status_"+d+" value=\"N\" unchecked onclick=\"check_client_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_napp_status_"+d+" value=\"N\" unchecked onclick=\"check_client_napp_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input' name=TXT_CLIENT_REMARKS_"+d+" maxlength='50' type='text' ></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_fol_status_"+d+" value=\"N\" unchecked onclick=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CLIENT_FOL_REMARKS_"+d+"></td>"); 
			out.println("</tr>");
			more5=rs5.next();
			d=d+1;
			}

			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

			out.println("</table >"); 
			out.println("<HR>");
      }
			
			*/
			
			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");
			out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change3></div></td></tr></table>");
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change2></div></td></tr></table>");
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr align='left' class='tr_input'>");  
			out.println("<td width='*%'><b><u>Condition List</td> ");
			out.println("</tr>");  

			out.println("<tr align='left'>");  
			out.println("<td width='10'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\"></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>");


			rs6= stmt6.executeQuery 
			("SELECT DISTINCT A.ISSUER_CODE,trim(TO_CHAR(A.AMOUNT,'999,999,999,999,999.99')),TO_CHAR(A.ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+
			"TO_CHAR(A.END_DATE,'DD-MM-YYYY'),A.STATUS,B.NAME "+
			"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES A,"+m_schema_name+".AF_CO_MAS_BANKS B "+	
			"WHERE A.APPLICATION_NO='"+m_application_no+"' "+
			"AND  A.ISSUER_CODE=B.BANK_CODE "+
			"AND A.STATUS='Y' ");


			boolean more6=rs6.next();	
			int x=0;

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
				
			while(more6){
				
			if(x==0){
			 out.println("<HR>");

			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Guarantee Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
						
			out.println("<td width='10%' style='{text-align:left;}'><b>Bank Guarantee Code</td>");
			out.println("<td width='30%' style='{text-align:left;}'><b>Bank Guarantee Name</td>");
			out.println("<td width='20%' style='{text-align:right;}'><b>Guaranteed Amount</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Guarantee Date</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"10%\" style='{text-align:left;}'style='{text-align:left;}' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_bank_drill('"+rs6.getString(1)+"')\"><U>"+rs6.getString(1)+"</td>");
			out.println("<td width=\"30%\" style='{text-align:left;}'>"+rs6.getString(7)+"</td>");
			out.println("<td width=\"20%\" style='{text-align:right;}'>"+rs6.getString(2)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(3)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(4)+"</td>");
			out.println("<td width=\"10%\" style='{text-align:left;}'>"+rs6.getString(5)+"</td></tr>");
			
			more6=rs6.next();
			x=x+1;
			}

			out.println("</table>");



			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input name =\"save1\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"help1\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"cancel1\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input name =\"close1\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  

			
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
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
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs1   !=null){try{rs1.close();   }catch(Exception e){}}
			if(rs2   !=null){try{rs2.close();   }catch(Exception e){}}
			if(rs3   !=null){try{rs3.close();   }catch(Exception e){}}
			if(rs4   !=null){try{rs4.close();   }catch(Exception e){}}
			if(rs5   !=null){try{rs5.close();   }catch(Exception e){}}
			if(rs6   !=null){try{rs6.close();   }catch(Exception e){}}
			
			
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
			if(stmt2  !=null){try{stmt2.close(); }catch(Exception e){}}
			if(stmt3  !=null){try{stmt3.close(); }catch(Exception e){}}
			if(stmt4  !=null){try{stmt4.close(); }catch(Exception e){}}
			if(stmt5  !=null){try{stmt5.close(); }catch(Exception e){}}
			if(stmt6  !=null){try{stmt6.close(); }catch(Exception e){}}
			
			
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}

				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
