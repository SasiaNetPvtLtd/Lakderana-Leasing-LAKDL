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


public class LAKDL_AF_CR_PRO_Credit_Notes_Letter extends javax.servlet.http.HttpServlet {
	
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
			
			if(m_screen_type.equals("MAIN")){
				
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
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter\";"); 
			out.println("}");

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter\";"); 
			out.println("		}"); 
			out.println("}"); 
     
			
			
			out.println("function save_data(){");
			
			out.println("getauthor2();");
			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter?finance_no="+m_finance_no+"&client="+m_client_code+"&invoice_no="+m_invoice_no+"&print=FALSE&aouthname=\"+document.form1.hid_athour.value+\"&chksql=LETTER \";"); 
		  //out.println(" window.location.href=m_url;"); 
									
		//	out.println("m_table.innerHTML=\"\" ");
			
		//	out.println("window.print();");
			
			
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
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");

			
			
			out.println("function getauthor(obj){");
			
			//out.println("alert('wew'+obj.value);");
			out.println("document.form1.hid_athour.value=obj.value;");
			//out.println("alert('qqqqq'+document.form1.hid_athour.value);");
		//out.println(" "+hid_athour_name+"=obj.value;");
			
			//out.println("    document.Form1.Hid_my_scr_name.value='"+hid_athour_name+"';");
	//	out.println("alert('"+hid_athour_name+"');");
			out.println("}");
			
			
				
			out.println("function getauthor2(){");
			
			//out.println("alert('wew'+obj.value);");
		//	out.println("document.form1.hid_athour.value=document.Form1.TXT_SCREEN.value;");
		 // out.println("    document.Form1.hid_athour.value='"+hid_athour_name+"';");
		//	out.println("alert("+hid_athour_name+");");
		//out.println(" "+hid_athour_name+"=obj.value;");
			
			
	//	out.println("alert('"+hid_athour_name+"');");
	
	
		out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter?finance_no="+m_finance_no+"&client="+m_client_code+"&invoice_no="+m_invoice_no+"&print=FALSE&aouthname=\"+document.form1.hid_athour.value+\"&chksql=LETTER \";"); 
		  out.println(" window.location.href=m_url;"); 
									
			out.println("m_table.innerHTML=\"\" ");
			out.println("}");
			
			
			
			out.println("</Script>");
		
	
//***********************************************************************************************************************************
			out.println("<html><head><font 10pt arial><title>CREDT NOTE LETTER</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			out.println("<br>");
			out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<br>");
			out.println("<form name='form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_athour' VALUE=\"\">"); 
			out.println("<table border='0' width='100%' class=table>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");

				 
				rs0 = stmt0.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
																	 "FROM DUAL ");

				boolean more0=rs0.next();
				

				rs1 = stmt1.executeQuery ("SELECT A.INVOICE_NO, "+
																	 "B.REF_NO,to_char(A.NET_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),to_char(A.VAT_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),to_char(A.TOTAL_AMOUNT,'9,999,999,999,999,999,999,999,999.99') "+
																	 "FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS B "+
																	 "WHERE  a.INVOICE_NO  =b.INVOICE_NO "+
																	 "AND ADJUST_TYPE='CR_NOTE' "+
																	 "AND UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
																	 "AND A.FINANCE_NO  =B.FINANCE_NO "+
																	 "AND A.adjusted_date IN (SELECT MAX(adjusted_date) FROM "+m_schema_name+".AF_CO_PRO_INVOICE WHERE UPPER(A.FINANCE_NO) = UPPER('"+m_finance_no+"'))");

				boolean more1=rs1.next();
		
		
				rs2 = stmt2.executeQuery ("SELECT CLIENT_CODE,initcap(FULL_NAME),NVL(initcap(ADDRESS1),'-'),NVL(initcap(ADDRESS2),'-'),NVL(initcap(CITY_CODE),'-'),initcap(TITLE),CLIENT_TYPE "+
																	"FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
																	"WHERE upper(CLIENT_CODE)  like upper('"+m_client_code+"') ");
			
			
				boolean more2=rs2.next();


				rs3 = stmt3.executeQuery ("SELECT APPLICATION_NO, "+
																	"INQUARY_NO,FINANCE_NO,FACILITY_NO "+
																  "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																  "WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') ");

			
				boolean more3=rs3.next();
	



			 rs4 = stmt4.executeQuery ("SELECT REF_NO, "+
																 "INVOICE_NO,to_char(ADJUSTED_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),nvl(TO_CHAR(ADJUSTED_DATE,'dd-mm-yyyy'),'-'),NVL(MOD_USER,'-') "+
																 "FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
																 "where UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') and upper(invoice_no)=UPPER('"+m_invoice_no+"')");

				boolean more4=rs4.next();



			
			out.println("<blockquote><font size=2><p style='text-align:left'>");	
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='70%' class='rep-body'>&nbsp</td>");
	
			out.println("<td width='30%' class='rep-body'>"+rs0.getString(1)+"</td></tr>");//sysdate
			out.println("</table>");
			
			out.println("<br>");

			if(more2){
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='5%' class='rep-body'><b>Client Code :</td><td width='30%' class='rep-body'><b>"+rs2.getString(1)+"</td></tr>");
			//-----------Added by Chandana on 23/05/2007 for Ref No.185 --------------//
			if(rs2.getString(7).equals("I")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+rs2.getString(6)+" "+rs2.getString(2)+"</td></tr>");
	     }else{
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+rs2.getString(2)+"</td></tr>");	
			}		
			//---------End Ref No.185 -------------------------- //
			
			String client_add1 = rs2.getString(3);
			String client_add2 = rs2.getString(4);
			String client_add3 = rs2.getString(5);
			
			if(!client_add1.equals("-")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add1+"</td></tr>");
			}
			if(!client_add2.equals("-")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add2+"</td></tr>");
			}
			if(!client_add3.equals("-")){			
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add3+".</td></tr>");
			}
			out.println("</table>");
			}	
			
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br><b>Dear Sir/Madam, </td>");
			out.println("<tr ></tr>");
			out.println("<tr ></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:center;}'><b><u>Credit Note </td></tr>"); //Credit Process - Credit Note Letter
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><b>Lease Details:-</td></tr>");
			out.println("</table>");
			out.println("<HR>");

			if(more3){
			
			m_inquary=rs3.getString(2);
			out.println("<br>");
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Finance No</td><td width='1%' class='rep-body' >:</td><td width='30%' class='rep-body'>"+m_finance_no+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Application No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Inquiry No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(2)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Facility No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(4)+"</td><td width='*%'></td></tr>");

			}	
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");



			if(more4){
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><b>Credit Note Details :-</td></tr>");
			out.println("</table>");
			out.println("<HR>");

			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Credit Note No</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs4.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("</table>");

		}
	
			if(more1){
			out.println("<table class=table border='0' width='100%' align=center>");

			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Invoice No</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Net Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(3)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Vat Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(4)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Total Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(5)+"</td><td width='*%'></td></tr>");
			
			out.println("</table>");
}	

			if(more4){
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' ><b>Adjusted Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'><b>"+rs4.getString(3)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' ><b>Adjusted Date</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'><b>"+rs4.getString(4)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' ><b>Adjusted User</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'><b>"+rs4.getString(5)+"</td><td width='*%'></td></tr>");//ADD  BY MALIK ON 8/4/2009

			out.println("</table>");
			}

      /*
			rs = stmt.executeQuery ("SELECT DISTINCT MK_SUPERVISOR "+
															"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY   "+
															"WHERE upper(inquiry_code)=upper('"+m_inquary+"') ");
			boolean more=rs.next();
			
			if(more){
			m_super=rs.getString(1);
			}

      */

			/*rs5 = stmt5.executeQuery ("SELECT DISTINCT MK_SUPERVISOR,NAME "+
																"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY ,"+m_schema_name+".CO_CO_MAS_USER   "+
																"WHERE USER_ID=MK_SUPERVISOR  ");
			*/
			//boolean more5=rs5.next();
				
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><br><br><br>.....................................</td></tr>");
			
		/*
	  	if (m_print.equals("TRUE")) {
		 out.println("<tr><td ><select class=\"txt_input\" type=\"text\" name=TXT_SCREEN  maxlength=1 style=\"width:150px\" onchange=\"getauthor(this)\">");  
     			
			while(more5){
	
			if(rs5.getString(1).equals(m_super)){
			hid_athour_name = rs5.getString(2);
			out.println("<option value=\""+rs5.getString(2)+"\" selected>"+rs5.getString(2)+"</option>");
			}
	
			else if(!rs5.getString(1).equals(m_super)){
			out.println("<option value=\""+rs5.getString(2)+"\" >"+rs5.getString(2)+"</option>");
			}
	
			more5=rs5.next();
			
			if (!more5)
			{
			break;
			}
		
			} 
			
		out.println("</select></td></tr>"); 	
		
		}
		else //if(m_print.equals("FALSE"))
		{
		out.println("<tr>");   
		out.println("<div id=aouthname></div>"); 
		out.println("</tr>");
		}
		
		*/
		
		
			out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><b>Authorized Signatory</td></tr>");

			out.println("</table>");
			out.println("</font></p></blockquote>");


			out.println("</form>");
			out.println("</body>");
	    out.println("</html>");
			
			}else	if(m_screen_type.equals("LETTER")){
			
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
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter\";"); 
			out.println("}");

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_CR_PRO_Credit_Notes_Letter\";"); 
			out.println("		}"); 
			out.println("}"); 
     
			
			
			out.println("function save_data(){");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter?finance_no="+m_finance_no+"&client="+m_client_code+"&invoice_no="+m_invoice_no+"&print=FALSE&aouthname=\"+document.form1.hid_athour.value+\" \";"); 
		  out.println(" window.location.href=m_url;"); 
									
			out.println("m_table.innerHTML=\"\" ");
			out.println("}");
			
			
			out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			//out.println("alert(\""+hid_athour_name+"\");");
			out.println("window.print();");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");

			
			
			out.println("function getauthor(obj){");
			
			//out.println("alert('wew'+obj.value);");
			out.println("document.form1.hid_athour.value=obj.value;");
			//out.println("alert('qqqqq'+document.form1.hid_athour.value);");
			out.println("}");
			
			
			
			
			out.println("</Script>");
		
	
//***********************************************************************************************************************************
			out.println("<html><head><font 10pt arial><title>CREDT NOTE LETTER</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			out.println("<br>");
			out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<br>");
			out.println("<form name='form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_athour' VALUE=\"\">"); 
			out.println("<table border='0' width='100%' class=table>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");

				 
				rs0 = stmt0.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
																	 "FROM DUAL ");

				boolean more0=rs0.next();
				

				rs1 = stmt1.executeQuery ("SELECT A.INVOICE_NO, "+
																	 "B.REF_NO,to_char(A.NET_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),to_char(A.VAT_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),to_char(A.TOTAL_AMOUNT,'9,999,999,999,999,999,999,999,999.99') "+
																	 "FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS B "+
																	 "WHERE  a.INVOICE_NO  =b.INVOICE_NO "+
																	 "AND ADJUST_TYPE='CR_NOTE' "+
																	 "AND UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
																	 "AND A.FINANCE_NO  =B.FINANCE_NO "+
																	 " AND A.adjusted_date IN (SELECT MAX(adjusted_date) FROM "+m_schema_name+".AF_CO_PRO_INVOICE WHERE UPPER(A.FINANCE_NO) = UPPER('"+m_finance_no+"'))");

				boolean more1=rs1.next();
		
		
				rs2 = stmt2.executeQuery ("SELECT CLIENT_CODE,initcap(FULL_NAME),NVL(initcap(ADDRESS1),'-'),NVL(initcap(ADDRESS2),'-'),NVL(initcap(CITY_CODE),'-'),initcap(TITLE),CLIENT_TYPE "+
																	"FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
																	"WHERE upper(CLIENT_CODE)  like upper('"+m_client_code+"') ");
			
			
				boolean more2=rs2.next();


				rs3 = stmt3.executeQuery ("SELECT APPLICATION_NO, "+
																	"INQUARY_NO,FINANCE_NO,FACILITY_NO "+
																  "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																  "WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') ");

			
				boolean more3=rs3.next();
	



			 rs4 = stmt4.executeQuery ("SELECT REF_NO, "+
																 "INVOICE_NO,to_char(ADJUSTED_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),nvl(TO_CHAR(ADJUSTED_DATE,'dd-mm-yyyy'),'-') "+
																 "FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
																 "where UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') and upper(invoice_no)=UPPER('"+m_invoice_no+"')");

				boolean more4=rs4.next();



			
			out.println("<blockquote><font size=2><p style='text-align:left'>");	
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='70%' class='rep-body'>&nbsp</td>");
	
			out.println("<td width='30%' class='rep-body'>"+rs0.getString(1)+"</td></tr>");//sysdate
			out.println("</table>");
			
			out.println("<br>");

			if(more2){
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='5%' class='rep-body'><b>Client Code :</td><td width='30%' class='rep-body'><b>"+rs2.getString(1)+"</td></tr>");
			//-----------Added by Chandana on 23/05/2007 for Ref No.185 --------------//
			if(rs2.getString(7).equals("I")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+rs2.getString(6)+" "+rs2.getString(2)+"</td></tr>");
	     }else{
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+rs2.getString(2)+"</td></tr>");	
			}		
			//---------End Ref No.185 -------------------------- //
			
			String client_add1 = rs2.getString(3);
			String client_add2 = rs2.getString(4);
			String client_add3 = rs2.getString(5);
			
			if(!client_add1.equals("-")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add1+"</td></tr>");
			}
			if(!client_add2.equals("-")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add2+"</td></tr>");
			}
			if(!client_add3.equals("-")){
			out.println("<tr><td width='5%' class='rep-body' >&nbsp</td><td width='30%' class='rep-body'><b>"+client_add3+".</td></tr>");
			}
			out.println("</table>");
			}	
			
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br><b>Dear Sir/Madam, </td>");
			out.println("<tr ></tr>");
			out.println("<tr ></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:center;}'><b><u>Credit Note </td></tr>"); //Credit Process - Credit Note Letter
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><b>Lease Details:-</td></tr>");
			out.println("</table>");
			out.println("<HR>");

			if(more3){
			
			m_inquary=rs3.getString(2);
			out.println("<br>");
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Finance No</td><td width='1%' class='rep-body' >:</td><td width='30%' class='rep-body'>"+m_finance_no+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Application No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Inquiry No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(2)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Facility No</td><td width='1%' class='rep-body' >:</td><td width='1%' class='rep-body'>"+rs3.getString(4)+"</td><td width='*%'></td></tr>");

			}	
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");



			if(more4){
			out.println("<table border='0' width='100%' class=table align=center>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><b>Credit Note Details :-</td></tr>");
			out.println("</table>");
			out.println("<HR>");

			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Credit Note No</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs4.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("</table>");

		}
	
			if(more1){
			out.println("<table class=table border='0' width='100%' align=center>");

			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Invoice No</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(1)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Net Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(3)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Vat Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(4)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' >Total Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'>"+rs1.getString(5)+"</td><td width='*%'></td></tr>");
			
			out.println("</table>");
}	

			if(more4){
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' ><b>Adjusted Amount</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'><b>"+rs4.getString(3)+"</td><td width='*%'></td></tr>");
			out.println("<tr><td width='10%' class='rep-body' ></td><td width='15%' class='rep-body' ><b>Adjusted Date</td><td width='1%' class='rep-body' >:</td><td width='20%' class='rep-body' align='right'><b>"+rs4.getString(4)+"</td><td width='*%'></td></tr>");
			out.println("</table>");
			}


			rs = stmt.executeQuery ("SELECT DISTINCT MK_SUPERVISOR "+
															"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY   "+
															"WHERE upper(inquiry_code)=upper('"+m_inquary+"') ");
			boolean more=rs.next();
			
			if(more){
			m_super=rs.getString(1);
			}



			rs5 = stmt5.executeQuery ("SELECT DISTINCT MK_SUPERVISOR,NAME "+
																"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY ,"+m_schema_name+".CO_CO_MAS_USER   "+
																"WHERE USER_ID=MK_SUPERVISOR  ");
							
			boolean more5=rs5.next();
				
				
		
			out.println("<table class=table border='0' width='100%' align=center>");
			out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><br><br><br>.....................................</td></tr>"); //
			out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'>"+hid_athour_name+"</td></tr>");
			out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><b>Authorized Signatory</td></tr>");
			out.println("</table>");
			out.println("</font></p></blockquote>");


			out.println("</form>");
			out.println("</body>");
	    out.println("</html>");
			
			
			
			
			
			}
//=========================================================================================================================			
  				
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
