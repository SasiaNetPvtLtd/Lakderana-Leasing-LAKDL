//--
//SCREEN NAME	:SAVE PAYMENT DETAILS
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Credit_Notes_Letter_new extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt0,stmt1,stmt2,stmt3,stmt4,stmt5;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs0,rs2,rs1,rs3,rs4,rs5;
	public String m_chksql;
	ServletOutputStream out = null;
	
  	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim();
			String m_fschema_name=con_method.client_name.trim();
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  	String m_username =  con_method.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  	nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  	nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			stmt = conn.createStatement ();
			stmt0 = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();
			stmt4 = conn.createStatement ();
			stmt5 = conn.createStatement ();

			String m_inquary="";
			String m_super="";
			
			String m_print ="";
			String hid_athour_name="TEST";

			String m_screen_type= req.getParameter("chksql");

				
		  	String m_finance_no = req.getParameter("finance_no");
		  	String m_invoice_no = req.getParameter("invoice_no");
		  	String m_client_code = req.getParameter("client");
			m_print      = req.getParameter("print"); 
	  		hid_athour_name      = req.getParameter("aouthname");


      		out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
				
			out.println("function befor_end(m_obj) {");
      		out.println("   m_obj.focus();");
      		out.println("}");

			out.println("function load_roll_value(m_val){");
			out.println("help_box.innerHTML=\"Credit Process - Credit Note Letter - \"+m_val"); 
			out.println("}");
				
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 


			out.println("function load_data(m_app_no,row) {");
		  	out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_display_credit_score_enter?application_no=\"+m_app_no;"); 
			out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("function load_roll_out_value(m_val){");
			out.println("help_box.innerHTML=\"Credit Process - Credit Note Letter \";"); 
			out.println("}");

			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function before_submit(){ "); 
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
 			out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"NEW\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
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
			out.println("m_scr=m_val");
			out.println("}"); 

				
			out.println("function new_window() {");
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter_new\";"); 
			out.println("}");

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter_new\";"); 
			out.println("		}"); 
			out.println("}"); 
     
			
			
			out.println("function save_data(){");
			
			out.println("getauthor2();");

			out.println("}");
			
			out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			//out.println("alert(document.form1.hid_athour.value);"); 
			out.println("alert(\""+hid_athour_name+"\");");
			
			out.println("aouthname.innerHTML='<td width=\"*%\" >'+"); 
			out.println("'< class=\"rep-body\" >'+"); 
			out.println("'\"'+document.form1.hid_athour.value+'\" </td>'"); 
			
			//out.println("<TD width='225' class='txt_report_data' align='right'>"+nf.format(m_tot_gross)+"</TD>");
			
			out.println("window.print();");
			
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    		out.println("m_writedata+'</table>';");
			}
			out.println("}");

			
			
			out.println("function getauthor(obj){");

			out.println("document.form1.hid_athour.value=obj.value;");

			out.println("}");
			
			
				
			out.println("function getauthor2(){");	
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter_new?finance_no="+m_finance_no+"&client="+m_client_code+"&invoice_no="+m_invoice_no+"&print=FALSE&aouthname=\"+document.form1.hid_athour.value+\"&chksql=LETTER \";"); 
		  	out.println(" window.location.href=m_url;"); 
									
			out.println("m_table.innerHTML=\"\" ");
			out.println("}");
			
			out.println("function print_data(){");
			out.println("   m_table.innerHTML = ''; ");
			out.println("   window.print();");
			out.println("}");
			
			
			
			out.println("</Script>");
		
	
//***********************************************************************************************************************************
			out.println("<html><head><font 10pt arial><title>CREDT NOTE LETTER</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			
			out.println("<form name='form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_athour' VALUE=\"\">"); 
			out.println("<table border='0' width='100%' class=table>");
			out.println("</table>");
			
			
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  	out.println("</tr>"); 
			out.println("</table>");
			
			
			String company_name = "";
			
			rs0 = stmt0.executeQuery (" SELECT COMPANY_NAME "+
									  " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			if(rs0.next()){
				company_name = rs0.getString(1);
			}
			
			
			String m_credit_not_no = "";
			String m_amount = "";
			double mm_amount = 0;
			
			
			rs0 = stmt0.executeQuery (" SELECT REF_NO, "+
									  " INVOICE_NO,TO_CHAR(ADJUSTED_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),NVL(ADJUSTED_AMOUNT,0),NVL(TO_CHAR(ADJUSTED_DATE,'DD-MM-YYYY'),'-'),NVL(MOD_USER,'-') "+
									  " FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
									  " WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND UPPER(INVOICE_NO)=UPPER('"+m_invoice_no+"')");
			
			if(rs0.next()){
				m_credit_not_no = rs0.getString(1);
				m_amount = rs0.getString(3);   
				mm_amount = rs0.getDouble(4);  
			}
			
			
			String sum_word="";
                rs0 = stmt0.executeQuery (" SELECT ROUND("+mm_amount+",2) FROM DUAL" );
                if(rs0.next()){
                    sum_word=rs0.getString(1);
                }
			
			
			String amnt_in_words = con_method.numbersToChar(sum_word); 
			
			
			String mm_client_name = "";
			
			rs0 = stmt0.executeQuery ("SELECT CLIENT_CODE,initcap(FULL_NAME),NVL(initcap(ADDRESS1),'-'),NVL(initcap(ADDRESS2),'-'),NVL(initcap(CITY_CODE),'-'),initcap(TITLE),CLIENT_TYPE "+
																	"FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
																	"WHERE upper(CLIENT_CODE)  like upper('"+m_client_code+"') ");
			
			if(rs0.next()){
				mm_client_name = rs0.getString(2); // mm_client_name = rs0.getString(1); 
			}
			
			String mm_payer = "";
			
			rs0 = stmt0.executeQuery (" "+
							" SELECT NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.RECEIVER),'-') "+
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A "+
							" WHERE A.REF_NO = '"+m_invoice_no+"' "+
							" ");
			
			if(rs0.next()){
				mm_payer = rs0.getString(1); 
			}
			
			String mm_app_no = "";
			
			rs0 = stmt0.executeQuery ("SELECT APPLICATION_NO, "+
									"INQUARY_NO,FINANCE_NO,FACILITY_NO "+
									"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									"WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') ");
			
			if(rs0.next()){
				mm_app_no = rs0.getString(1); 
			}
			
			String mm_reg_no = "";
			
			rs0 = stmt0.executeQuery (" "+
				" SELECT REG_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				" WHERE  APPLICATION_NO = '"+mm_app_no+"' "+ 
				" AND ACTIVE_STATUS = 'Y' "+
				" ");
	
			if(rs0.next()){
				mm_reg_no = rs0.getString(1); 
			}
			
			String mm_date = "";
			rs0 = stmt0.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
																	 "FROM DUAL ");
		
			if(rs0.next()){
				mm_date = rs0.getString(1); 
			}
			
			
		
			out.println("<table border='0' width='100%' class=table align='center' >");
			out.println("<tr><td align='center' ><h2> "+company_name+" </h2></td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class=table align='center' >");
			out.println("<tr><td align='center' ><h3> Credit Note - Insurance </h3></td></tr>");
			out.println("</table>");

			


			
			out.println("<blockquote><font size=2><p style='text-align:left'>");	
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='70%' class='rep-body'> No:- "+m_credit_not_no+" </td>");
	
			out.println("<td width='30%' class='rep-body'> Date:- "+mm_date+"</td></tr>");//sysdate
			out.println("</table>");
			
			out.println("<br>");

			
			
			out.println("<table class=table border='0' width='98%' align=center >");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Payee </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+mm_payer+" </td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			out.println("   <tr height=10px ></tr>");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Contract No. </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+m_finance_no+"</td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			out.println("   <tr height=10px ></tr>");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Name </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+mm_client_name+" </td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			out.println("   <tr height=10px ></tr>");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Vehicle No. </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+mm_reg_no+" </td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			out.println("   <tr height=10px ></tr>");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Amount in words </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+amnt_in_words+" </td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			out.println("   <tr height=10px ></tr>");
			
			out.println("   <tr>");
			out.println("      <td width='20%' style='{font:12px;}' > Amount </td>");
			out.println("      <td width='60%' style='{font:12px;}' > :- "+m_amount+" </td>");
			out.println("      <td width='*%' > &nbsp; </td>");
			out.println("   </tr>");
			
			
			out.println("</table>");
			
			out.println("   <br><br> ");
			
			
			out.println("<table class=table border='0' width='98%' align=center>");
			out.println("<tr>");
		    out.println("   <td width='20%' class=txt-body style='{font:12px;text-align:left;}'>.....................................</td>");
			out.println("   <td width='40%' > &nbsp; </td> ");
			out.println("   <td width='20%' class=txt-body style='{font:12px;text-align:left;}'>.....................................</td>");
			out.println("   <td width='2%' > &nbsp; </td> ");
			out.println("</tr>");
	
			out.println("<tr>");
			out.println("   <td width='20%' class=txt-body style='{font:12px;text-align:left;}'><b>Checked By</td>");
			out.println("   <td width='40%' > &nbsp; </td> ");
			out.println("   <td width='20%' class=txt-body style='{font:12px;text-align:left;}'><b>Approved By</td>");
			out.println("   <td width='2%' > &nbsp; </td> ");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("</font></p></blockquote>");


			out.println("</form>");
			out.println("</body>");
	    	out.println("</html>");
			
			
							
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
