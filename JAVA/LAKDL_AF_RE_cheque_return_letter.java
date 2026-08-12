//CREATED BY DINETH ON 14-07-2009
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_cheque_return_letter extends javax.servlet.http.HttpServlet {
	
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
			conn = m_sn_methods.met_user_validate(req); 
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
     String m_username 						=m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			stmt=conn.createStatement();
			String m_chksql = req.getParameter("chksql");
					
			//************************************************************
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
			rs=stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");											
			
			boolean more4=rs.next();
			if(more4){
			//out.println(" m_sysdate = '"+rs.getString(1)+"'");			
			
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			
			} 
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
				
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
							
				out.println("var m_client_code=\"\";");
				out.println("var m_finance_no=\"\";");
				out.println("var m_cheque_date=\"\";");
				out.println("var m_return_no=\"\";");
				
				
				//Added by Dineth on 14-07-2009
							out.println("function change(row) {"); 
							out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
							out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
							out.println("}");
							out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
							out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
							out.println("}");
							out.println("}"); 
							
							out.println("function validate_data(row){"); 
							out.println("for(var f=0;f<row;f++){");
			
							out.println("if(document.Form1.elements[\"chk_app_\"+f].checked==true){");
			
							out.println("b_flag1=1;");
							out.println("break;");
							out.println("}");
							out.println("}");
							out.println("}");
				
							out.println("function save_window(){	"); 
							out.println("before_submit();"); 
							out.println("}"); 
							out.println("");
							out.println("function before_submit(){ "); 
							out.println("var row=document.Form1.hid_deposit_lineno.value");	
							out.println("		validate_data(row)"); 
							out.println("	if(b_flag1==0){");
							out.println("alert('Please select a receipt no')");
							out.println(" return ");
							out.println("	}");
							out.println("		if(confirm(\"Are you sure you want to save?\")){ ");
							out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
							out.println("		document.Form1.elements[i].disabled=false;");
							out.println("		}");
							out.println("		document.Form1.action=\""+m_class_url+"/"+m_fschema_name+"AF_RE_save_cheque_return_letter\";");  
							out.println("		document.Form1.submit();	"); 
							out.println("		}"); 
							out.println("		}"); 
				
				out.println("function get_vector_normal(http_response){ ");
				out.println(" e_deposit_details.innerHTML = ''; ");
				out.println(" e_deposit_details.innerHTML = http_response; ");
				out.println("}");
        
				
				out.println("function val_cheque_no(obj){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_val_cheque_no&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				//		out.println("window.open(m_url);");
				out.println("}"); 
				
				
				out.println("function makeRequest_new_chq(obj) {");
			  //out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_cheque_return_letter?chksql=get_receipts_CHQ&data_val=\"+obj.value+\"\";");
			  out.println("		load_interface(m_url,'NORM');");
			  out.println("}");
			  //Added by Dineth on 14-07-2009
			out.println("function get_vector(data_vec) {");
				
			out.println("			if(data_vec.length>0 ){");
						
			out.println("    document.Form1.CHEQUE_NO.value=data_vec[0];"); 
			out.println("    m_client_code=data_vec[2];"); 
			out.println("    m_finance_no=data_vec[1];"); 
			out.println("    m_return_no=data_vec[1];"); 
			
			out.println("if(data_vec[3]=='-'){");
			out.println("    m_cheque_date='';"); 
			out.println("			}");
			out.println("			else{");
			out.println("    m_cheque_date=data_vec[3];"); 
			out.println("			}");
			
			out.println("			}");
			out.println("			else if (document.Form1.CHEQUE_NO.value!=\"\" ){");
			out.println("     help_cheque_no();");
      out.println("			}");
			
			out.println("			}");
			
				
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
			
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_cheque_no_assign();"); 
	  	out.println("		}"); 
		  out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_comment(oBj);"); 
			out.println("}");
				
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
			out.println("	clear_data();");//Added To The Clear The Area Code
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
			
			out.println("function clear_data() {");
			out.println("document.Form1.CHEQUE_NO.value='';");
			out.println("}");
			
			out.println("function help_cheque_no() {"); 
			out.println("    Crit = document.Form1.CHEQUE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_Collection_Return_realisation_cheque_no','99');"); 
			out.println("}"); 
					
			out.println("function help_cheque_no_assign() {"); 
			out.println("    document.Form1.CHEQUE_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.hid_client_code.value=oBj.valout[4];"); 
			out.println("    document.Form1.hid_finance_no.value=oBj.valout[2];"); 
			out.println("    m_return_no=oBj.valout[7];"); 
			
			
			out.println("if(oBj.valout[6]=='-'){");
			out.println("    document.Form1.hid_cheque_date.value='';"); 
			out.println("			}");
			out.println("			else{");
			out.println("    document.Form1.hid_cheque_date.value=oBj.valout[6];"); 
			out.println("			}");
			out.println("}"); 
			
      out.println("function help_comment(rowNo) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_comment = \"TXT_COMMENT\"+rowNo;");
			out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("    m_sql = \"m_help_TXT_CHQ_NARRATIONS_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_comment].value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_comment() {"); 
			out.println("    m_comment = \"TXT_COMMENT\"+document.Form1.hid_row_no.value;");
			out.println("    document.Form1.elements[m_comment].value=oBj.valout[3];"); 
			out.println("}"); 
			

      out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_cheque_return_letter?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_cheque_return_letter?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_DEPOSIT_SLIP\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Cheque Return Letter - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Cheque Return Letter - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"LETTER\"){");  
			out.println("document.Form1.hid_status.value=\"Letter\";");  
			out.println("document.Form1.hid_save_status.value=\"Letter\";"); 
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
			
			out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Cancel\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_CHEQUE_RTN_LETTER\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_print_status' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_cheque_date' VALUE=\"\">");
					//out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE=\"\">");
					
										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Cheque Return Letter</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
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
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
					out.println("<tr>"); 
					
					out.println("<td width='15%' ><DIV id='DIV_CHEQUE_NO'  class=div_input>Cheque No/Receipt No *</DIV></td>"); 
					out.println("<td width='20%' ><input class='txt_input' type='text' name='CHEQUE_NO' maxlength='15' size='10' onblur=\"val_cheque_no(this)\">"); 
			    out.println("<input class='but_input' type='button' name='BUT_CHEQUE_NO' value=\"...\" onClick=\"help_cheque_no()\"></td>"); 
					out.println("<td width='6%'><input class='but_input' type='button' name='BUT_BCODE' value=\"View\" onClick=\"makeRequest_new_chq(document.Form1.CHEQUE_NO)\" style='{cursor:hand;}'></td>"); 
					out.println("<td width='59%'>&nbsp;</td>");
					out.println("</tr>"); 
          out.println("</table>");          
				
				out.println("<table align='center' width='100%' class='table'>"); 
		  	out.println("<tr>" );
		  	out.println("<td width=\"100%\"><DIV ID=e_deposit_details>  </DIV></td>");				
				out.println("</tr>" );
      	out.println("</table>");
		
					
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</html>");


			
			
			}
			else if(m_chksql.trim().equals("get_receipts_CHQ")){
		
				//String m_val = req.getParameter("data_val").trim();
				String m_CHQ = req.getParameter("data_val").trim();
				
		
								rs= stmt.executeQuery ("SELECT UPPER(REC_NO), "+ //1
								" "+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO), "+ //2
								" TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+ //3
								" SETTLE_MODE, "+ //4
								" RECON_STATUS,  "+ //5
								" REALISED_DATE, "+ //6
								" NVL(UPPER(CHEQUE_NO),'-'), "+ //7
								" REC_AMOUNT_CURR,  "+ //8
								" D.RETURN_NO, "+//9
								" NVL(D.RETURN_CHARGE,0), "+//10
								" NVL(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'-') "+//11
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  a, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS  b , "+m_schema_name+".AF_CO_PRO_DIPOSIT  c, "+
								" "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS d "+
								" where b.diposit_no=c.diposit_no "+
								" and a.rec_no=b.receipt_no "+
								" and a.rec_no=d.RECEIPT_NO "+
								" and b.diposit_no=d.diposit_no "+
								//" AND c.DIPOSIT_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')	 "+
								//" AND c.DIPOSIT_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY')	   "+
								" and a.status='RET' "+
								" AND A.SETTLE_MODE IN('CHEQUE')   "+  //'STD_ORD'
								//" AND C.ACC_NO=UPPER('"+m_val+"') "+
								" AND UPPER(CHEQUE_NO)=UPPER('"+m_CHQ+"') "+ 
								//" AND A.REALISED_DATE IS NULL  "+
								" AND A.GROUP_REC_NO IS NULL  "+
								" ORDER BY B.diposit_no ");
			boolean more=rs.next();
      if(more){								
			out.println("	  <table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\">");
			out.println("		<TR class=pdn_txtpos2 >");
			out.println("<TD width=\"10%\" align=\"left\"><B>Return No</B></TD> ");
			out.println("<TD width=\"10%\" align=\"left\"><B>Transaction Date</B></TD> "); 
			out.println("   <TD width=\"10%\" align=\"left\"><B>Deposit No</B></TD>  "); 
			out.println("   <TD width=\"10%\" align=\"left\"><B>Receipt No</B></TD>");
			out.println("   <TD width=\"10%\"  align=\"right\"><B>Amount</B></TD>");
			out.println("   <TD width=\"10%\" align=\"left\"><B>Reference No</B></TD>"); 
			//out.println("   <TD width=\"100\" align=\"center\"><B>Returned Status</B></TD>");
			//out.println("   <TD width=\"100\" align=\"center\"><B>Realised Status</B></TD>");
			out.println("   <TD width=\"10%\" align=\"right\"><B>Return Charge</B></TD>");
			out.println("   <TD width=\"25%\" align=\"left\"><B>Comment</B></TD>");
			out.println("   <TD width=\"10%\" align=\"left\"><B>Settlement Mode</B></TD>"); 
			out.println("   <TD width=\"10%\" align=\"left\"><B>Realised/Returned date</B></TD> ");
			out.println("   <TD width=\"10%\" >&nbsp</TD></TR>");
      }
			int j=0;
			while(more){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("		<TD width=\"10%\" align=\"left\">"+rs.getString(9)+"<input type=hidden name=hid_return_no"+j+" value=\""+rs.getString(9)+"\" ></TD> ");
			out.println("		<TD width=\"10%\" align=\"left\">"+rs.getString(3)+"</TD> "); 
			out.println("   <TD width=\"10%\" align=\"left\">"+rs.getString(2)+"<input type=hidden name=hid_deposit_no"+j+" value=\""+rs.getString(2)+"\" ></TD>  "); 
			out.println("   <TD width=\"10%\" align=\"center\" STYLE=\"{cursor:hand;}\"  onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u><input type=hidden name=hid_receipt_no"+j+" value=\""+rs.getString(1)+"\" ></TD>");
			out.println("   <TD WIDTH=\"10%\" align=\"right\">"+nf.format(rs.getDouble(8))+"</td><input type=hidden name=hid_amount"+j+" value=\""+rs.getDouble(8)+"\" >");	
			out.println("   <TD width=\"10%\" align=\"left\">"+rs.getString(7)+"</TD>"); 
			
			//out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_return_status('"+j+"')\" ></td>");		
			//out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_realize_status('"+j+"')\"  ></td>");			
			
			//out.println("<TD WIDTH=\"150\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_RET_CHARGE"+j+" maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;text-align:right;}\" disabled ></td>");
			out.println("   <TD WIDTH=\"10%\" align=\"right\">"+nf.format(rs.getDouble(10))+"</td>");
      out.println("<TD WIDTH=\"25%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_COMMENT"+j+" maxlength=\"10\"  value=\"\" style=\"{width:140px;}\" onBlur=makeRequest_comment(this) disabled >");
			out.println("<input class=\"but_input\" type=\"button\" name=BUT_HELP"+j+" value=\"Help\" onClick=\"help_comment("+j+")\"   ></td>");
  					
			out.println("   <TD width=\"10%\" align=\"center\">"+rs.getString(4)+"</TD>");
			out.println("   <TD width=\"10%\" align=\"center\">"+rs.getString(11)+"</TD>");
			//out.println("<TD WIDTH=\"120\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\"  >");
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\"  >");
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onBlur=\"date_check("+j+")\" ></TD>");	
			out.println(" <td width=\"10%\" align=\"center\" ><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\"></td>");//<input class=\"but_input\" type=\"button\" name=BUT_DETAIL"+j+" value=\"View\" onClick=load_details("+j+")  style=\"width: 40px\" >
			j=j+1;
			more=rs.next();
			}
			
      out.println("<input type=hidden name=hid_deposit_lineno value="+j+">");
			out.println(" 	</table>'");
		}
		
		

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

