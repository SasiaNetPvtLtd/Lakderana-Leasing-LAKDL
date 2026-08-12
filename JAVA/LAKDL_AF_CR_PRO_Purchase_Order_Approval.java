//Created by Nuwan De Silva
//Purchase Order Approval

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Purchase_Order_Approval extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String val="End";
			String m_username 						=m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);
			nf2.setMinimumFractionDigits(2);
			nf2.setMaximumFractionDigits(2);
			
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql==null)
			{
				m_chksql="main_page";
			}
			
			
			
			//	m_chksql         = req.getParameter("chksql");
			//		m_ac_status = req.getParameter("ac_status");
			stmt = conn.createStatement ();
			
			///	stmt1 = conn.createStatement ();
			
			//	if (m_chksql.trim().equals("idle")) {
			//		out.println("idle");
			//	}
			//		else if(m_chksql.trim().equals("main_page")){
			
			String m_sort_column   = "ENT_DATE";	
			String m_order_by_type = "ASC";
			
			
			//			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			//       m_sort_column = req.getParameter("sort_column");
			//      m_order_by_type = req.getParameter("order_by_type");
			//		}
			
			
			if(m_chksql.equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval"))
			{
				
				
				
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_client      = req.getParameter("client"); //Added by Prabash on 10-02-2012
				int m_position=0;
				
				//added by nuwan de silva 
				rs= stmt.executeQuery(" SELECT POSITION "+
					" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					" WHERE UPPER(SCREEN_NAME)=UPPER('AF_CR_PRO_PURCHASE_ORDER_APPROVAL') ");
				if(rs.next()){
					m_position=rs.getInt(1);
				}
				
				String query=" SELECT "+
					" NVL(PURCHASE_ORDER_NO,'-') PURCHASE_ORDER_NO,  "+//1
					" NVL(A.APPLICATION_NO,'-') APPLICATION_NO, "+//2
					" "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO) CLIENT_NAME, "+//3
					" "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME, "+//4
					" NVL(TOTAL_NET,0) TOTAL_NET , "+//5
					" NVL(TOTAL_VAT,0) TOTAL_VAT, "+//6
					" NVL(TO_CHAR(PURCHASE_ORDER_DATE),'-') PURCHASE_ORDER_DATE,"+ //7
					""+m_schema_name+".AF_CO_GET_AUTHORIZATION_LIMIT('"+m_username+"','"+m_position+"'), "+ //8
					" NVL((TOTAL_VAT+TOTAL_NET),0) GROSS,  "+ //9
					" CLIENT_CODE, "+ //10
					" VENDER_CODE, "+ //11
					" DECODE(A.ACTIVE_STATUS,'ENT','New Vendor','ENT_N','New Vendor/Document Pending','ENT_D','Document Pending'), "+ //added by nuwan de silva on 12-11-07 //12
					" NVL("+m_schema_name+".AF_CO_PURCH_OR_REC_UNALBAL(TO_CHAR(SYSDATE,'DD-MM-YYYY'),CLIENT_CODE),0)  CUSTTOT, "+//13
					" C.FINANCE_NO FINANCE_NO, "+//14	
					" nvl("+m_schema_name+".AF_GET_INITIAL_INVOICE_TOTAL(A.APPLICATION_NO),0) INVTOT "+//15 LAKDL.AF_GET_INITIAL_PAYMENT() USE THIS FUNCTION
					" ,"+m_schema_name+".AF_GET_CONFIRMATION_RPT_STATUS(C.FINANCE_NO) "+ // 16 added by udara 16-06-2017
					" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					" WHERE  A.APPLICATION_NO=C.APPLICATION_NO "+
					" AND A.ACTIVE_STATUS IN ('ENT','ENT_N','ENT_D') "+//  '"+m_status+"' "+ //COMMENT BY NUWAN DE SILVA ON 12-11-07
					" AND  CLIENT_CODE LIKE '%"+m_client+"%'  "+ // Added by Prabash on 10-02-2012
					" ORDER BY "+m_column+" "+m_type+" ";
				//out.println(query);
				rs = stmt.executeQuery(query); 
				
				
				out.println("<table align='center' width='100%'border='0' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"> ");
				
				out.println("<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"> ");
				out.println("<tr class=tr_input>");
				out.println("<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("</td>");
				out.println("</tr>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\">");
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
				out.println("<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Purchase Order No  \"    onclick=sort_data(\"PURCHASE_ORDER_NO\")   align=\"left\" ><B>Purchase Order No</td>");
				out.println("<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Application No  \"       onclick=sort_data(\"FINANCE_NO\")          align=\"left\" ><B>Finance No</td>");
				out.println("<td  width=\"15%\" style= cursor:hand; title=\"Click here to sort by - Client Name  \"          onclick=sort_data(\"CLIENT_NAME\")         align=\"left\" ><B>Client Name</td>");
				out.println("<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Vendor Name  \"          onclick=sort_data(\"VENDER_NAME\")         align=\"left\" ><B>Vendor Name</td>");
				out.println("<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Gross Amount  \"         onclick=sort_data(\"TOTAL_NET\")                align=\"right\"><B>Gross Amount</td>");
			    out.println("<td  width=\"8%\"  style= cursor:hand; title=\"Click here to sort by - Purchase Order Date  \"  onclick=sort_data(\"PURCHASE_ORDER_DATE\")      align=\"left\" ><B>Purchase Order Date</td>");
				out.println("<td  width=\"8%\"  style= cursor:hand; title=\"Click here to sort by - Initial Invoice Total \"  onclick=sort_data(\"INVTOT\") align=\"left\" ><B>Initial Invoice Total</td>");
				out.println("<td  width=\"8%\"  style= cursor:hand; title=\"Click here to sort by - Customer Level Receipts \"  onclick=sort_data(\"CUSTTOT\") align=\"left\" ><B>Customer Level Receipt</td>");
				out.println("<td  width=\"5%\"  align=\"center\"><B>Approval Status<INPUT TYPE=\"checkbox\" NAME=\"CHK_ALL\" ID=\"CHK_ALL\" VALUE=\"\" onclick=\"checkAll()\" ></td>"); //thamali 2012.02.14
				out.println("<td  width=\"5%\"  align=\"center\"></td>");
				out.println("</TR>");
				
				int j=0;
				int k=0;

				while(rs.next()){
					
					if(rs.getInt(16)>0) {
					
							if(j>0 && j%2==1)
							{
							out.println("<tr class=\"tr_input1\">"); //m_pur_reason //m_tot_vat
							}
							else
							{
							out.println("<tr class=\"tr_input\">");
						    }	
							out.println("<TD WIDTH=\"12%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_pur_details('"+rs.getString(1)+"')\" >"+rs.getString(1)+"</td>");//show_pur_details(c_client])  //<u>data_vec[i]</u>
							out.println("<TD WIDTH=\"12%\" align=\"left\" onclick=\"\" >"+rs.getString(14)+"</TD>"); //finance no
							out.println("<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('"+rs.getString(10)+"')\" ><u>"+rs.getString(3)+"</u></TD>");//show_client_details([c_client+9]) //data_vec[i+2]
							out.println("<TD WIDTH=\"10%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_vendor_details('"+rs.getString(11)+"')\" ><u>"+rs.getString(4)+"</u></TD>");//show_vendor_details('+[c_client+10]+') //data_vec[i+3]
							out.println("<TD WIDTH=\"10%\" align=\"right\">"+nf2.format(rs.getDouble(5))+"</TD>");
						    out.println("<TD WIDTH=\"8%\" align=\"left\">"+rs.getString(7)+"</TD>"); 
							out.println("<TD WIDTH=\"8%\" align=\"right\">"+nf2.format(rs.getDouble(15))+"</TD>");
							out.println("<TD WIDTH=\"8%\" align=\"right\">"+nf2.format(rs.getDouble(13))+"</TD>");
							
							if(rs.getDouble(8)>=rs.getDouble(9)){
							out.println("<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=\"CHK_REQUIRED"+j+"\" ID =\"CHK_REQUIRED"+j+"\" VALUE=\"\" onclick=\"change_val_req('"+j+"')\"></td>");			
							}
							if(rs.getDouble(8) < rs.getDouble(9)){
							out.println("<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=\"CHK_REQUIRED"+j+"\" ID=\"CHK_REQUIRED"+j+"\" VALUE=\"\" onclick=\"change_val_req('"+j+"')\" disabled></td>");			
							}
							out.println("<td  width=\"5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=\"BUT_TXT_DETAILS"+j+"\" value=\"View\" onClick=\"help_button_details('"+j+"')\">"); 
							out.println("<INPUT TYPE=\"Hidden\" NAME=\"hid_TXT_PUR_NO"+j+"\"	VALUE='"+rs.getString(1)+"'>");
							out.println("<INPUT TYPE=\"Hidden\" NAME=\"hid_TXT_APP_NO"+j+"\"	VALUE='"+rs.getString(2)+"'></td>"); 
							out.println("</tr>");
							
							j=j+1;
							k=k+1;//number of records
							
						}		
				
				}	
				out.println("</table>");
				out.println("</td>");	
				out.println("</tr>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\">");
				
				out.println("<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">");
				out.println("<tr class=tr_input>");
				out.println("<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>");
				out.println("</tr>");
				out.println("</table>");
		
				out.println("</td> ");	
				out.println("</tr>");
				out.println("<tr>");  
				out.println("<td width=\"100%\"> "); //hidden no of records
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\""+k+"\">"); 
				out.println("</td>");  
				out.println("</tr>");
				out.println("</table>");	
				
				
				
				
			}		
			else
			{
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				
				
				out.println("var new_data_vec=new Array();");
				out.println("var lineno=0;");
				out.println("var arr_size=0;");
				out.println("var m_sort_column='PURCHASE_ORDER_NO';");
				out.println("var m_order_by_type='ASC';");
				out.println("var val='End';");	
				
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
				out.println("get_purchase_orders(m_sort_col,m_order_by_type);");
				out.println("}");
				
				
				out.println("function befor_end(m_obj) {");
				out.println("if(m_obj==\"GO_TOP\"){");
				out.println("m_go_top=\"top_b\";");
				out.println("document.Form1.elements[m_go_top].focus();}");
				out.println("else if(m_obj==\"GO_END\"){");
				out.println("m_go_end=\"end_b\";");
				out.println("document.Form1.elements[m_go_end].focus();}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		validation_nromal_data.innerHTML=m_data;");
				out.println("}");
				
				/*
				//!--------Display The Header -------------------------------------//
				out.println("function header(){");
			    out.println("m_table.innerHTML=\"\"");
				out.println("lineno=0;");
				out.println("arr_size=0;");
				
				out.println("m_row='<tr class=tr_input>'+");
				out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
				out.println("'</tr>';");
				
				out.println("m_table_top.innerHTML='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Purchase Order No  \"    onclick=sort_data(\"PURCHASE_ORDER_NO\")   align=\"left\" ><B>Purchase Order No</td>'+");
				//out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Application No  \"       onclick=sort_data(\"APPLICATION_NO\")      align=\"left\" ><B>Application No</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Application No  \"       onclick=sort_data(\"APPLICATION_NO\")      align=\"left\" ><B>finance No</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Client Name  \"          onclick=sort_data(\"CLIENT_NAME\")         align=\"left\" ><B>Client Name</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Vendor Name  \"          onclick=sort_data(\"VENDER_NAME\")         align=\"left\" ><B>Vendor Name</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Gross Amount  \"         onclick=sort_data(\"TOTAL_NET\")           align=\"right\"><B>Gross Amount</td>'+");
				//out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - VAT  \"                  onclick=sort_data(\"TOTAL_VAT\")           align=\"right\"><B>VAT</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Purchase Order Date  \"  onclick=sort_data(\"PURCHASE_ORDER_DATE\") align=\"left\" ><B>Purchase Order Date</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Purchase Order Date  \"  align=\"left\" ><B>initial invoice total</td>'+");
				out.println("'<td  width=\"50px\" style= cursor:hand; title=\"Click here to sort by - Purchase Order Date  \"  align=\"left\" ><B>customer level receipt</td>'+");
				//out.println("'<td  width=\"9%\" align=\"center\"><B>Reason</td>'+");
				out.println("'<td  width=\"50px\" align=\"center\"><B>Approval Status</td>'+");
				out.println("'<td  width=\"50px\" align=\"center\"></td>'+");
				out.println("'</TR></table>';");		
				out.println("}");
				*/
				
				out.println("function change_val_req(row_no){")	;
					out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");	
				//out.println("alert(\"row_no\"+row_no+document.Form1.elements[m_chk_required].name)");
				
				out.println("}");
				
				//thamali 2012.02.14
				out.println("function checkAll(){")	;
				out.println("if(document.Form1.elements[\"CHK_ALL\"].checked==true){");
				out.println("for (var i=0; i < document.Form1.hid_no_rec.value; i++ ) {");
				out.println("m_chk_required=\"CHK_REQUIRED\"+i;");
				out.println("if(document.Form1.elements[m_chk_required].disabled==false){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("document.Form1.elements[m_chk_required].checked=true");
				out.println("}");	
				out.println("}");	
				out.println("}else if(document.Form1.elements[\"CHK_ALL\"].checked==false){");
				out.println("for (var i=0; i < document.Form1.hid_no_rec.value; i++ ) {");
				out.println("m_chk_required=\"CHK_REQUIRED\"+i;");
				out.println("if(document.Form1.elements[m_chk_required].disabled==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("document.Form1.elements[m_chk_required].checked=false");
				out.println("}");	
				out.println("}");	
				out.println("}");	
				out.println("}");	
					
				out.println("function help_button_details(row_No) {"); 
				out.println("document.Form1.hid_row_no.value=row_No;");
				out.println("m_pur_ord_no=\"hid_TXT_PUR_NO\"+row_No");
				out.println("m_app_no=\"hid_TXT_APP_NO\"+row_No");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Approval_Details?chksql=view_details&pur_ord_no=\"+document.Form1.elements[m_pur_ord_no].value+\"&app_no=\"+document.Form1.elements[m_app_no].value;"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=750,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				
				out.println("function show_client_details(row_No) {"); 
				out.println("show_client(row_No);"); 
				out.println("}"); 
				
				out.println("function show_vendor_details(row_No) {"); 
				out.println("show_vendor_drill(row_No);"); 
				out.println("}"); 
				
				out.println("function show_application(row_No) {"); 
				out.println("show_application_detail_drill(row_No);"); 
				out.println("}"); 
				
				out.println("function show_pur_details(row_No) {"); 
				out.println("show_purchase_order_drill(row_No);"); 
				out.println("}"); 
				
				/*
					out.println("  function  display_data(data_vec){");
					out.println("header();	");		
					out.println("var i=0;");
					out.println("var j=0;");
					out.println("var c_client=0;");
					out.println("while(i<data_vec.length){");
					out.println("m_pur_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_pur_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></td>';");
					out.println("m_app_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"\" ><u>'+data_vec[i+13]+'</u></TD>';"); //finance no \\show_application('+[c_client+1]+')
					out.println("m_client_name='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+9]+')\" ><u>'+data_vec[i+2].replace('$','&')+'</u></TD>';");
					out.println("m_ven_name='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_vendor_details('+[c_client+10]+')\" ><u>'+data_vec[i+3].replace('$','&')+'</u></TD>';");
					out.println("m_gross_amt='<TD WIDTH=\"50px\" align=\"right\">'+data_vec[i+4]+'</TD>';");
					out.println("m_tot_vat='<TD WIDTH=\"50px\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
					out.println("m_pur_date='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+6]+'</TD>';");
					out.println("m_inv_total='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+14]+'</TD>';");
					out.println("m_recept='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+12]+'</TD>';");
					out.println("m_finance_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"\" >finance no</TD>';");
					//out.println("m_pur_reason='<TD WIDTH=\"9%\" align=\"left\">'+data_vec[i+11]+'</TD>';");		//ADDED BY NUWAN DE SILVA ON 12-11-07
					out.println("if(parseFloat(data_vec[i+7]) >= parseFloat(data_vec[i+8])){"); //modified by nwuan de silva on 05-10-07
					out.println("m_required='<TD WIDTH=\"50px\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
					out.println("}");
					out.println("if(parseFloat(data_vec[i+7]) < parseFloat(data_vec[i+8])){"); //modified by nwuan de silva on 05-10-07
					out.println("m_required='<TD WIDTH=\"50px\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\" disabled></td>';");			
					out.println("}");
					out.println("m_btn='<td  width=\"50px\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_DETAILS'+lineno+' value=\"View\" onClick=\"help_button_details('+lineno+')\">';"); 
						out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PUR_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'></td>';");
					out.println("if(j>0 && j%2==1){");
					out.println("m_writedata='<tr class=\"tr_input1\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_pur_date+m_inv_total+m_recept+m_required+m_btn+m_hid_input+'</TR>';"); //m_pur_reason //m_tot_vat
					out.println("	}");
					out.println("	else{");
					out.println("m_writedata='<tr class=\"tr_input\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_pur_date+m_inv_total+m_recept+m_required+m_btn+m_hid_input+'</TR>';");//m_pur_reason .//m_tot_vat
					out.println("	}");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"1\">'+");
					out.println("m_writedata+'</table>';");
					out.println("j=j+1;");
					out.println("i=i+15;");
					out.println("c_client=c_client+15;");
					out.println("lineno=lineno+1;");
					out.println("arr_size=arr_size+1;");		
					out.println("}"); //End while loop
					out.println("m_row='<tr class=tr_input>'+");
						out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
						out.println("'</tr>';");
					out.println("m_table_end.innerHTML='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
					out.println("}");		
				*/
				/*				
				out.println("  function  display_data(data_vec){");
				out.println("header();	");		
				out.println("var i=0;");
				out.println("var j=0;");
				out.println("var c_client=0;");
				out.println("while(i<data_vec.length){");
				out.println("m_pur_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_pur_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></td>';");
				out.println("m_app_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"\" ><u>'+data_vec[i+13]+'</u></TD>';"); //finance no \\show_application('+[c_client+1]+')
				out.println("m_client_name='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+9]+')\" ><u>'+data_vec[i+2].replace('$','&')+'</u></TD>';");
				out.println("m_ven_name='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_vendor_details('+[c_client+10]+')\" ><u>'+data_vec[i+3].replace('$','&')+'</u></TD>';");
				out.println("m_gross_amt='<TD WIDTH=\"50px\" align=\"right\">'+data_vec[i+4]+'</TD>';");
				out.println("m_tot_vat='<TD WIDTH=\"50px\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
				out.println("m_pur_date='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+6]+'</TD>';");
				out.println("m_inv_total='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+14]+'</TD>';");
				out.println("m_recept='<TD WIDTH=\"50px\" align=\"left\">'+data_vec[i+12]+'</TD>';");
				out.println("m_finance_no='<TD WIDTH=\"50px\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"\" >finance no</TD>';");
				//out.println("m_pur_reason='<TD WIDTH=\"9%\" align=\"left\">'+data_vec[i+11]+'</TD>';");		//ADDED BY NUWAN DE SILVA ON 12-11-07
				out.println("if(parseFloat(data_vec[i+7]) >= parseFloat(data_vec[i+8])){"); //modified by nwuan de silva on 05-10-07
				out.println("m_required='<TD WIDTH=\"50px\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
				out.println("}");
				out.println("if(parseFloat(data_vec[i+7]) < parseFloat(data_vec[i+8])){"); //modified by nwuan de silva on 05-10-07
				out.println("m_required='<TD WIDTH=\"50px\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\" disabled></td>';");			
				out.println("}");
				out.println("m_btn='<td  width=\"50px\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_DETAILS'+lineno+' value=\"View\" onClick=\"help_button_details('+lineno+')\">';"); 
				//	out.println("m_details='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
				//out.println("'<td  width=\"10%\" align=\"center\"><B>Approval Status</td>'+");
				//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PUR_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'></td>';");
				out.println("if(j>0 && j%2==1){");
				//       	out.println("<tr class=\"tr_input1\" >");
				//out.println("m_writedata='<TR class=\"tr_input1\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_tot_vat+m_pur_date+m_inv_total+m_recept+m_required+m_btn+'</TR>'+m_hid_input;"); //m_pur_reason
				out.println("m_writedata='<tr class=\"tr_input1\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_pur_date+m_inv_total+m_recept+m_required+m_btn+m_hid_input+'</TR>';"); //m_pur_reason //m_tot_vat
				out.println("	}");
				out.println("	else{");
				//      	out.println("<tr class=\"tr_input\" >");
				//				   out.println("m_writedata='<TR class=\"tr_input\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_tot_vat+m_pur_date+m_inv_total+m_recept+m_required+m_btn+'</TR>'+m_hid_input;");//m_pur_reason 
				out.println("m_writedata='<tr class=\"tr_input\">'+m_pur_no+m_app_no+m_client_name+m_ven_name+m_gross_amt+m_pur_date+m_inv_total+m_recept+m_required+m_btn+m_hid_input+'</TR>';");//m_pur_reason .//m_tot_vat
				out.println("	}");
				//  out.println("m_writedata='<TR>'+m_pur_no+m_app_no+m_ven_no+m_issued_date+m_dr_date+m_net_amt+m_tot_vat+m_pur_date+m_required+'</TR>'+m_hid_input;"); 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"1\">'+");
				out.println("m_writedata+'</table>';");
				out.println("j=j+1;");
				out.println("i=i+15;");
				out.println("c_client=c_client+15;");
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");		
				out.println("}"); //End while loop
				out.println("m_row='<tr class=tr_input>'+");
				//out.println("'<td width=\"90%\" align=\"right\"><input type=\"button\" name=\"Close\"   value=\"Close\"     class=\"mainbut\" onclick=close_window(); ></td>'+");
				// out.println("'<td width=\"5%\" align=\"right\"></td>'+");
				out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
				out.println("'</tr>';");
				//out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				//out.println("m_row+'</table>';");
				out.println("m_table_end.innerHTML='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
				out.println("}");		
				*/
				
				//--------------------------------------------------------------------------		
				/*
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length>0){");
				out.println("				new_data_vec=data_vec;");
				//out.println("				display_data(data_vec);");
				out.println("			}");
				out.println("			}");
				*/
				
				out.println("function get_purchase_orders(m_sort_column,m_order_by_type){");
			//	out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ENT&ac_status2=VERIFY\";"); //comment by Prabash on 10-02-2012
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval&sort_column=\"+m_sort_column+\"&client=\"+document.Form1.CLIENT_CODE.value+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ENT&ac_status2=VERIFY\";");//Added by prabash on 10-02-2012
				//out.println("window.open(m_url);");	
				out.println("load_interface(m_url,'normal');");
				out.println("}");	
				
				out.println("function validate_data(){"); 
				out.println("return true;"); 
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				//out.println("       document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				out.println("		if(validate_data()){"); 
				out.println("       for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("          document.Form1.elements[i].disabled=false;");
				out.println("       }");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order_Approval';");  
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Purchase_Order_Approval\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Credit - Purchase Order Approval - \"+m_val;"); 
				//out.println("if(m_val==\"New\")");
				//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit - Purchase Order Approval - \"+document.Form1.hid_status.value;"); 
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
				

				
				out.println("function View_Letter(){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_View_Letter\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval';"); 
				out.println("}");
			//=====Added by Prabash on 10-02-2012 ==========================================
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'ClientTSql2','1');");
				out.println("}");
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.TXT_CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
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

			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		receipt_assign(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		vehicle_assign(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		lease_assign(oBj);"); 
	  	out.println("		}");
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
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
			
			out.println("function check_client(val) {");
			out.println(" document.Form1.TXT_CLIENT_NAME.value = ''  ");
			 out.println("}");	
			//=======================================================================	
				out.println("</Script>");
				
			//	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_purchase_orders('PURCHASE_ORDER_NO','ASC')\" >"); //load_lock() //onLoad=\"get_purchase_orders('PURCHASE_ORDER_NO','ASC')\" ccomment by Prabash on 10-02-2012
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >");// Added by Prabash on 10-02-2012
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PURCHASE_ORDER_APPROVAL\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Purchase Order Approval - New</td>"); 
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
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
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
				//======== Added by Prabash on 10-02-2012========================================
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>Client Code</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=check_client()> ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"get_purchase_orders('PURCHASE_ORDER_NO','ASC')\"></td>");
				out.println("</tr>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"TXT_CLIENT_NAME\" type=\"text\" maxlength=\"10\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				out.println("</tr>");		
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" colspan=4>");
				out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");  
		//=========================================================================================
				
				
				out.println("<table align='center' width='100%'border='0' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='validation_nromal_data'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	
				
				
				
				
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  	
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				//	out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  	
				
				
				
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
			}	
			// }
			
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
