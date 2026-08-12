
//Created by Nuwan De Silva
//Application Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_SettlementDiposit_Pending_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;//"AA";//m_sn_methods.username;
			String m_order_by_type="";
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("LOAD_PENDING_RECEIPTS"))
			{
				
				String m_sort_column="REC_NO";	
				m_order_by_type="ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				String receipt_no="";
				if(req.getParameter("RECEIPT_NO")!=null)
				{
					receipt_no=req.getParameter("RECEIPT_NO");
				}
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>");
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr  align='center'>");
				out.println("<td  width='15%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='15%' align='right' ></td>");
				out.println("</tr>");
				
				out.println("</table>"); 
				
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>");
				
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=7 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				out.println("<td  width='15%' align='left' style= cursor:hand; title='Click here to sort by - Receipt No'            onclick=sort_data('A.REC_NO') >Receipt No</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Settlement Mode'       onclick=sort_data('A.SETTLE_MODE') >Settlement Mode</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Cheque No'             onclick=sort_data('A.CHEQUE_NO') >Cheque No</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Cheque Date'             onclick=sort_data('A.CHEQUE_DATE') >Cheque Date</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Account No'            onclick=sort_data('A.PAYER_ACC_NO') >Account No</td>");
				out.println("<td  width='10%' align='left'  style= cursor:hand; title='Click here to sort by -Bank name'             onclick=sort_data('BANK_NAME')>Bank name</td>");
				out.println("<td  width='10%' align='right' style= cursor:hand; title='Click here to sort by - Amount'                onclick=sort_data('B.REC_AMOUNT')>Amount</td>");
				out.println("<td  width='10%' align='right' style= cursor:hand; title='Click here to sort by - Allocated Amount'      onclick=sort_data('B.ALLOCATED_AMOUNT')>Allocated Amount</td>");
				out.println("<td  width='15%' align='right' style= cursor:hand; title='Click here to sort by - Balance To Be Received'onclick=sort_data('B.BAL_TOBE_RECEIVE')>Balance To Be Received</td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				
				
				rs = stmt.executeQuery
				//out.println	
					(" SELECT  "+
					" A.REC_NO REC_NO, "+
					" NVL(A.SETTLE_MODE,'-') SETTLE_MODE, "+
					//" NVL(A.CHEQUE_NO,'-') CHEQUE_NO, "+
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',NVL(A.CHEQUE_NO,'-'),'CASH','-'),'-') CHEQUE_NO , "+
					//" NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO, "+ 
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',A.PAYER_ACC_NO,'CASH','-'),'-') PAYER_ACC_NO, "+
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',"+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'CASH','-'),'-') BANK_NAME, "+
					//    " "+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE) BANK_NAME, "+
					" NVL(B.REC_AMOUNT,0) REC_AMOUNT, "+
					" NVL(B.ALLOCATED_AMOUNT,0) ALLOCATED_AMOUNT,  "+
					" NVL(B.BAL_TOBE_RECEIVE,0) BAL_TOBE_RECEIVE , "+
					" DECODE(A.SETTLE_MODE,'CHEQUE',NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'),'CASH','-','STD_ORD','-','DIR_DEP','-') CHEQUE_DATE  ,"+
					" NVL(C.BANK_CODE,'-') "+  //modified by nuwan de silva 17-07-07
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B ,"+
					" "+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
					" WHERE "+
					" A.REC_NO=B.REC_NO AND "+  
					" A.REC_NO LIKE ('%"+receipt_no+"%') AND "+
					" A.PAYER_BRANCH_CODE=C.BRANCH_CODE(+) AND "+
					" A.STATUS=('E') AND "+
					" A.SETTLE_MODE IN('CHEQUE','CASH') "+ //MODIFIED BY NUWAN DE SILVA 17-07-07
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				while(rs.next())
				{
					
					if(j>0 && j%2==1)
					{
						out.println("<tr class=tr_input1 >");
					}
					else
					{
						out.println("<tr class=tr_input >");
					}
					
					//out.println("<td width='12%' align='center' style= cursor:hand; onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
					out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td> ");
					out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(3) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(9) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(4) +"</td>");
					out.println("<td width='10%' align='left' style= cursor:hand; onClick=\"show_bank_drill('"+rs.getString(10)+"')\" ><u>"+rs.getString(5) +"</u></td>");
					out.println("<td width='10%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(6)) +"</td>");
					out.println("<td width='10%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(7)) +"</td>");
					out.println("<td width='15%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(8)) +"</td>");
					out.println("</tr>");
					j=j+1;
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=7 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");	
				
				
				
			}
			else if(m_chksql.trim().equals("main_page"))
			{
				
				String m_sort_column="REC_NO";	
				m_order_by_type="ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				//		if(req.getParameter("sort_column")!=null){
				//    m_sort_column = req.getParameter("sort_column");
				//		}
				
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Collection  - Pending Receipts \";"); 
				out.println("}else{");
				out.println("help_box.innerHTML=\"Collection -  Pending Receipts - \"+m_val;"); 
				out.println("}");
				out.println("}");
				
				
				/*  out.println("function load_data(m_app_no,m_app_sts) {");
					out.println(" if(m_app_sts=='IP') { ");
				out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no=\"+m_app_no;"); 
					out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("  }");
					out.println(" else { ");
					out.println("   alert(m_app_no+'  is complete.');"); 
					out.println("  }");
					out.println("}");
				*/	
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				//out.println("alert(m_sort_col);");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_SettlementDiposit_Pending_Report?chksql=LOAD_PENDING_RECEIPTS&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&RECEIPT_NO=\"+document.Form1.TXT_RECEIPT_NO.value;;"); 
				//out.println("alert(m_sort_col);");
				//   out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_SettlementDiposit_Pending_Report?chksql=main_page&sort_column=\"+m_sort_col;"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				
				
				
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
				out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
				out.println("        	assign_receipt_no()");
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
				out.println("	}		"); //close
				out.println("	else{");
				out.println("        clear_data()");
				out.println("	}");
				out.println("	}");
				out.println("	else{");
				out.println("        clear_data()");
				out.println("	}");
				out.println("}"); 
				
				
				
				out.println("function assign_receipt_no()");
				out.println("{ ");
				out.println("     document.Form1.TXT_RECEIPT_NO.value = oBj.valout[2];");
				out.println("     document.getElementById(\"data_div\").innerHTML=\"\"; ");
				out.println("}"); 
				
				out.println("function clear_data() {");
				out.println("   if (document.Form1.hid_help_type.value == '8') ");
				out.println("   { ");
				out.println("        document.Form1.TXT_RECEIPT_NO.value =''; ");
				out.println("        document.getElementById(\"data_div\").innerHTML=\"\"; ");
				out.println(" 	} ");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}");
				
				
				out.println("function receipt_no_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"8\";"); 
				out.println("    m_sql = \"m_help_ReceiptSql_sql\";");  //m_help_VendorSql_sql //m_help_ReceiptSql_sql
				out.println("    m_criteria = document.Form1.TXT_RECEIPT_NO.value+\"@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");
				
				
				
				out.println("function load_report_pendingreceipts()");
				out.println("{ ");
				
				out.println("   document.Form1.hid_num.value=\"43\"; ");
				out.println("	m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_SettlementDiposit_Pending_Report?chksql=LOAD_PENDING_RECEIPTS&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&RECEIPT_NO=\"+document.Form1.TXT_RECEIPT_NO.value; ");
				out.println("	load_interface(m_url,'NO'); ");
				
				out.println("} ");
				
				/*
						out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				*/
				
				
				out.println("function get_vector_normal(m_data)");
				out.println("{ ");
				out.println("		if(document.Form1.hid_num.value==\"43\"){");
				out.println("               document.getElementById(\"data_div\").innerHTML=m_data; ");
				out.println("				document.Form1.hid_num.value=99;");
				out.println("		}");
				out.println("}");
				
				out.println("</Script>");
				
				
				out.println("<body onload=\"load_report_pendingreceipts()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_num' VALUE=\"99\">");	
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				// out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection - Pending Receipts</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				
				out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				//		out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				out.println("<td  align='left'  >Receipt No</td>");
				out.println("<td  align='left'  ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' onblur=\"receipt_no_help()\" ></td>");
				out.println("<td  colspan='7'   ><input class='but_input' type='button' name='BUT_TXT_RECEIPT_NO_HELP' value=\"...\" onClick=\"receipt_no_help()\"> ");
				out.println(" <input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"load_report_pendingreceipts()\"> </td>");				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td  align='left'  > </td>");
				out.println("<td  align='left'  > </td>");
				out.println("<td  colspan='7'   > </td>");				
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td  width='15%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='15%' align='right' ></td>");
				out.println("</tr >");
				
				
				out.println("<tr >");
				out.println("<td colspan='9' align='left'  >");
				out.println("    <div id='data_div' ></div> ");  //added by madhawa 2012-02-12
				out.println("</td>");				
				out.println("</tr>");
				out.println("</table>");	
				
				/*
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>");
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr  align='center'>");
				out.println("<td  width='15%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='left'  ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='10%' align='right' ></td>");
				out.println("<td  width='15%' align='right' ></td>");
				out.println("</tr>");
				
				out.println("</table>"); 
				
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>");
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				out.println("<td  align='left'  >Receipt No</td>");
				out.println("<td  align='left'  ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' onblur=\"receipt_no_help()\" ></td>");
				out.println("<td  colspan='7'   ><input class='but_input' type='button' name='BUT_TXT_RECEIPT_NO_HELP' value=\"...\" onClick=\"receipt_no_help()\"> </td>");				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td  align='left'  > </td>");
				out.println("<td  align='left'  > </td>");
				out.println("<td  colspan='7'   > </td>");				
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=7 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				out.println("<td  width='15%' align='left' style= cursor:hand; title='Click here to sort by - Receipt No'            onclick=sort_data('A.REC_NO') >Receipt No</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Settlement Mode'       onclick=sort_data('A.SETTLE_MODE') >Settlement Mode</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Cheque No'             onclick=sort_data('A.CHEQUE_NO') >Cheque No</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Cheque Date'             onclick=sort_data('A.CHEQUE_DATE') >Cheque Date</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Account No'            onclick=sort_data('A.PAYER_ACC_NO') >Account No</td>");
				out.println("<td  width='10%' align='left'  style= cursor:hand; title='Click here to sort by -Bank name'             onclick=sort_data('BANK_NAME')>Bank name</td>");
				out.println("<td  width='10%' align='right' style= cursor:hand; title='Click here to sort by - Amount'                onclick=sort_data('B.REC_AMOUNT')>Amount</td>");
				out.println("<td  width='10%' align='right' style= cursor:hand; title='Click here to sort by - Allocated Amount'      onclick=sort_data('B.ALLOCATED_AMOUNT')>Allocated Amount</td>");
				out.println("<td  width='15%' align='right' style= cursor:hand; title='Click here to sort by - Balance To Be Received'onclick=sort_data('B.BAL_TOBE_RECEIVE')>Balance To Be Received</td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				
				
				rs = stmt.executeQuery
				//out.println	
					(" SELECT  "+
					" A.REC_NO REC_NO, "+
					" NVL(A.SETTLE_MODE,'-') SETTLE_MODE, "+
					//" NVL(A.CHEQUE_NO,'-') CHEQUE_NO, "+
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',NVL(A.CHEQUE_NO,'-'),'CASH','-'),'-') CHEQUE_NO , "+
					//" NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO, "+ 
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',A.PAYER_ACC_NO,'CASH','-'),'-') PAYER_ACC_NO, "+
					" NVL(DECODE(A.SETTLE_MODE,'CHEQUE',"+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'CASH','-'),'-') BANK_NAME, "+
					//    " "+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE) BANK_NAME, "+
					" NVL(B.REC_AMOUNT,0) REC_AMOUNT, "+
					" NVL(B.ALLOCATED_AMOUNT,0) ALLOCATED_AMOUNT,  "+
					" NVL(B.BAL_TOBE_RECEIVE,0) BAL_TOBE_RECEIVE , "+
					" DECODE(A.SETTLE_MODE,'CHEQUE',NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'),'CASH','-','STD_ORD','-','DIR_DEP','-') CHEQUE_DATE  ,"+
					" NVL(C.BANK_CODE,'-') "+  //modified by nuwan de silva 17-07-07
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B ,"+
					" "+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
					" WHERE A.REC_NO=B.REC_NO AND "+    
					" A.PAYER_BRANCH_CODE=C.BRANCH_CODE(+) AND "+
					" A.STATUS=('E') AND "+
					" A.SETTLE_MODE IN('CHEQUE','CASH') "+ //MODIFIED BY NUWAN DE SILVA 17-07-07
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				while(rs.next()){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					//out.println("<td width='12%' align='center' style= cursor:hand; onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
					out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td> ");
					out.println("<td width='10%' align='left'>"+rs.getString(2) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(3) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(9) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(4) +"</td>");
					out.println("<td width='10%' align='left' style= cursor:hand; onClick=\"show_bank_drill('"+rs.getString(10)+"')\" ><u>"+rs.getString(5) +"</u></td>");
					out.println("<td width='10%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(6)) +"</td>");
					out.println("<td width='10%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(7)) +"</td>");
					out.println("<td width='15%' align='right' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(8)) +"</td>");
					out.println("</tr>");
					j=j+1;
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=7 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");	
				
				*/
				
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("</html>");
			}
			
			//=========================================================================================================================			
			/*else {
				out.println("Undefined");
			}
			*/
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
