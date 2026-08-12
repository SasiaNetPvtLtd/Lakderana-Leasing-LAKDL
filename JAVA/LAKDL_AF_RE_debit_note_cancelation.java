//Created by Sandun Jayathilake 
//On 10-11-2008
//Debit Note Cancellation

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_debit_note_cancelation extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
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
      String m_username 						= "AA";//m_sn_methods.username;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			int m_count_payment_no=0;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("debit_note_detals")){					  						
			
				 String m_fin_no 	= req.getParameter("fin_no").trim();
				 String m_inv_no 	= req.getParameter("inv_no").trim();
			
					
				  out.println("<hr>");
					out.println("<table class=table border='0' width='100%' >");
					
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
					out.println("<tr class=pdn_txtpos2 >");
					out.println("<td  width='15%' align='left'>Debit Note No</td>");
					out.println("<td  width='15%' align='left'>Agreement No</td>");
          out.println("<td  width='20%' align='left'>Client Name</td>");
          out.println("<td  width='15%' align='left'>Value Date</td>");
          out.println("<td  width='15%' align='right'>Amount</td>");
					out.println("<td  width='20%' align='left'>Remarks</td>");
					out.println("<td  width='25%' align='center'>Cancel Rem.</td>");
					out.println("<td  width='5%'  align='center'>Select</td>");
					out.println("</tr>");
					 
           int j = 0;   
						
							if(m_inv_no.equals("")){	
							
								
								
							rs = stmt.executeQuery (" SELECT DISTINCT A.FINANCE_NO, "+//1
															         " NVL(A.TOTAL_AMOUNT,0), "+//2
															         " TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//3
															         " A.CLIENT_CODE, "+//4
															         " NVL(A.REMARKS,'-'), "+//5
															         " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//6
															  			 " A.INVOICE_NO "+//7
																			 " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
															  			 " WHERE  A.ACTIVE_STATUS='Y' "+
																			 " AND    A.SETTELE_AMOUNT = 0 "+
																			 " AND    A.INVOICE_TYPE NOT IN ('INV_GENER') "+ //,'ODI'
																			 " AND   "+m_schema_name+".AF_RE_PRO_PAY_DR_NOTE_STATUS(A.INVOICE_NO) = 'Y' "+  //ADDED BY CS ON 18/01/2012		
															  			 " AND    UPPER(A.FINANCE_NO) LIKE UPPER( '%"+m_fin_no+"%') "+
																			" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(SYSDATE),-1)+1,'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																			" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(LAST_DAY(SYSDATE),'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																			" ");
																			 //" AND ADD_MONTHS(SYSDATE,-1) <= TO_CHAR(A.VALUE_DATE,'DD-MON-YYYY') "); //Comment By Sandun on 18-11-2008
									}
									
									else if(m_fin_no.equals("")){
									
									rs = stmt.executeQuery (" SELECT DISTINCT A.FINANCE_NO, "+//1
															         " NVL(A.TOTAL_AMOUNT,0), "+//2
															         " TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//3
															         " A.CLIENT_CODE, "+//4
															         " NVL(A.REMARKS,'-'), "+//5
															         " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//6
															  			 " A.INVOICE_NO "+//7
																			 " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
															  			 " WHERE  A.ACTIVE_STATUS='Y' "+
																			 " AND    A.SETTELE_AMOUNT = 0 "+
																			 " AND    A.INVOICE_TYPE NOT IN ('INV_GENER') "+ //,'ODI'
																			 " AND   "+m_schema_name+".AF_RE_PRO_PAY_DR_NOTE_STATUS(A.INVOICE_NO) = 'Y' "+  //ADDED BY CS ON 18/01/2012	
															  		//	 " AND    A.INVOICE_NO = '%"+m_inv_no+"%' "+ //comment by prabash on 16-02-2012
																		 " AND    UPPER(A.INVOICE_NO) LIKE UPPER( '%"+m_inv_no+"%') "+
																			" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(SYSDATE),-1)+1,'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																			" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(LAST_DAY(SYSDATE),'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																			" ");// Added by prabash on 16-02-2012
											
												
									
									}
									else{
									
									rs = stmt.executeQuery (" SELECT DISTINCT A.FINANCE_NO, "+//1
															         " NVL(A.TOTAL_AMOUNT,0), "+//2
															         " TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//3
															         " A.CLIENT_CODE, "+//4
															         " NVL(A.REMARKS,'-'), "+//5
															         " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//6
															  			 " A.INVOICE_NO "+//7
																			 " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
															  			 " WHERE  A.ACTIVE_STATUS='Y' "+
																			 " AND    A.SETTELE_AMOUNT = 0 "+
																			 " AND    A.INVOICE_TYPE NOT IN ('INV_GENER') "+ //,'ODI'
																			 " AND    UPPER(A.FINANCE_NO) LIKE UPPER( '%"+m_fin_no+"%') "+
																			 " AND   "+m_schema_name+".AF_RE_PRO_PAY_DR_NOTE_STATUS(A.INVOICE_NO) = 'Y' "+  //ADDED BY CS ON 18/01/2012		
															  			 //  " AND    A.INVOICE_NO = '%"+m_inv_no+"%' "+ // comment by prabash on 16-02-2012
																			 " AND    UPPER(A.INVOICE_NO) LIKE UPPER( '%"+m_inv_no+"%') "+
																				" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(SYSDATE),-1)+1,'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																			" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(LAST_DAY(SYSDATE),'DD-MM-YYYY'),'DD-MM-YYYY') "+ // added by udara on 08-10-2013
																				" ");// Added by prabash on 16-02-2012
												
									}
									
									
              while(rs.next()){
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
									out.println("<td width='15%' align='left' >"+rs.getString(7) +"</td>");									
									out.println("<td width='15%' align='left' >"+rs.getString(1) +"</td>");
                  out.println("<td width='20%' align='left' >"+rs.getString(6) +"</td>");
									out.println("<td width='15%' align='left' >"+rs.getString(3) +"</td>");
                  out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(2))+"</td>");									
                  out.println("<td width='20%' align='left' >"+rs.getString(5) +"</td>");
									out.println("<td width='25%' align=\"center\"><input type='text' class='txt_input' style='width:100px' name=txt_remarks"+j+"></td>");//Added by Dineth on 2008-12-17
									out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
									out.println("<input type='hidden' name=hid_invoice_no"+j+" value=\""+rs.getString(7)+"\">");
									out.println("</tr>");
                	j=j+1;
              }
							
					out.println("<input type=hidden name=hid_no_rec_count value="+j+">");
					
					out.println("<tr class=tr_input>");				 
          out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr></table>");
          
					out.println("</table>");
			

      }
				else if(m_chksql.trim().equals("main_page")){
				
				
				
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Debit Note Cancellation</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
				/*
				 rs = stmt.executeQuery (" SELECT DISTINCT A.INVOICE_TYPE ,"+//1
																 " NVL(NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE), "+
																 " "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE)),INITCAP(A.INVOICE_TYPE)) "+//2
																 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																 " WHERE A.INVOICE_TYPE IS NOT NULL "+
																 " AND   A.INVOICE_TYPE NOT IN ('INV_GENER') "+ //,'ODI'
																 " AND   A.ACTIVE_STATUS = 'Y'" );

         		*/
				
				
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Finance - Debit Note Cancellation \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Finance - Debit Note Cancellation - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				out.println("function change_val_req(row_no){")	;
        out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");	
				out.println("}");	
				

				out.println("function validate_fin_no(){");
				out.println("if(document.Form1.TXT_FIN_NO.value==\"\" || document.Form1.TXT_DEBIT_NOTE_NO==\"\"){");
				out.println("alert('Please Enter Finance No Or Debit Note No...!');");
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				out.println("function validate_data(){");			
				out.println("return true;");				
				out.println("}");
								
				out.println("function before_submit(){ ");
				
				out.println("   document.Form1.save_button.disabled = true; "); // added by udara 05-07-2018
				
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ ");			
				out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_no_rec_count.value;");
				out.println("		if(validate_data()){"); 
				out.println("           for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("                document.Form1.elements[i].disabled=false;");
				out.println("           }");
				
				out.println("           document.Form1.save_button.disabled = true; "); // added by udara 14-06-2018
				
				out.println("		    document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_Save_Cr_Dr_Cancel';");  
				out.println("		    document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
		
				// added by udara 05-07-2018
				out.println("		else{");
				out.println("          document.Form1.save_button.disabled = false; ");
				out.println("		}"); 
				// end by udara 05-07-2018
		
		
				out.println("} "); 
				out.println("} "); 


			

				
			out.println("function get_debit_notes(){");			
			out.println("fin_no = document.Form1.TXT_FIN_NO.value;");
			out.println("inv_no = document.Form1.TXT_DEBIT_NOTE_NO.value;");
		
			//out.println("alert(fin_no);");
			//out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_debit_note_cancelation?chksql=debit_note_detals&inv_no=\"+inv_no+\"&fin_no=\"+fin_no+\" \";");//&inv_type=\"+m_inv_type+\"--//Comment by Prabash on 16-02-2012
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_debit_note_cancelation?chksql=debit_note_detals&inv_no=\"+inv_no+\"&fin_no=\"+fin_no+\" \";");   								//Added by Prabash on 16-02-2012
			out.println("load_interface(m_url,'NORM');");
			
			out.println("}"); 
			
			 out.println("function get_vector_normal(http_response) {");
			 out.println(" m_table.innerHTML = ''; ");
			 out.println(" m_table.innerHTML = http_response; ");
			 out.println("}");
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance - Debit Note Cancellation - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"\";");  
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
					out.println("function load_screen_status(m_val){"); 					
					out.println("if(m_val==\"HELP\"){"); 
					out.println(" load_help_msg();");
					out.println("}"); 
					out.println("else{");
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  					
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_debit_note_cancelation?chksql=main_page';"); 
					out.println("		}"); 
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
				  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					out.println("	if(oBj.valout[1] ==\" \"){"); 
					out.println("	clear_data();");
					out.println("	}else");						
					out.println("	"); 
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 			  			
					
					
					out.println("if(IfCount=='100'){"); 
					out.println("		assign_finance_no(oBj);"); 
					out.println("}");	
					out.println("else if(IfCount=='99'){"); 
					out.println("		assign_invoice_no(oBj);"); 
					out.println("}");	
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
					out.println("	else{");
					out.println("	clear_data();");
					out.println("	}");					
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
					
					out.println("function clear_data() {");					
					out.println("}");					
					
					
					
					out.println("function help_finance_no() {"); 								
					out.println("Crit = document.Form1.TXT_FIN_NO.value+\"@Y@\";"); 					
					out.println("HelpBox('1','10','0',Crit,'m_help_CR_DR_NOTES_FINANCE_NO_1','100');"); 		
					out.println("} ");

					out.println("function help_invoice_no(){"); 								
					out.println("Crit = document.Form1.TXT_DEBIT_NOTE_NO.value+\"@\"+document.Form1.TXT_FIN_NO.value+\"@\";"); 					
					out.println("HelpBox('1','10','0',Crit,'m_help_DR_CANCEL_INVOICE_NO','99');"); 		
					out.println("} ");
					
					
					
					
					out.println("function assign_finance_no(oBj) {"); 
					out.println("		document.Form1.TXT_FIN_NO.value=oBj.valout[2];"); 
					//out.println("document.Form1.BUT_TXT_SEARCH.disabled=false;");
					out.println("}");		

						
					out.println("function assign_invoice_no(oBj) {"); 
					out.println("document.Form1.TXT_DEBIT_NOTE_NO.value=oBj.valout[2];"); 
					//out.println("document.Form1.BUT_TXT_SEARCH.disabled=false;");
					out.println("}");
					
					out.println("function dis_bttn(){");
					//out.println("document.Form1.BUT_TXT_SEARCH.disabled=true;");
					out.println("}");
					
					out.println("</Script>");				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"dis_bttn()\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"DEBIT_NOTE_CANCEL\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");

										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Debit Note Cancellation </td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%'></td>");
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='6%'></td>");
					out.println("<td width='6%'></td>");
					out.println("<td width='10%' align='center'><input type=\"button\" name=\"save_button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");   // mod by udara added name=\"save_button\" 05-07-2018
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
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
					
					
			//	out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0>"); 
				/*out.println("<tr class=tr_input>"); 
				out.println("<td width='10%' >&nbsp;</td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_INV' class=div_input>Invoice Type</DIV></td>"); 
				out.println("<td width='20%' >");  
				out.println("<select name='TXT_INV_TYPE' class=div_input style='width:150' >");
				out.println("<option value='ALL' selected>All</option>");
				while(rs.next()){
				out.println("<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				}
				out.println("</select></td>");				
				out.println("<td width='*%'>&nbsp;</td>");
				out.println("</tr>"); */
				
			
				out.println("<tr class=tr_input>");
			//	out.println("<td width='10%' >&nbsp;</td>"); //comment by Prabash on 03-02-2012
				out.println("<td width='10%' ><DIV id='DIV_TXT_FIN_NO' class=div_input>Finance No</DIV></td>"); 
				out.println("<td width='25%' ><input type=text name='TXT_FIN_NO' class=div_input style=width:150px onBlur='help_finance_no()'>");
				out.println("&nbsp;<input type=button class='but_input' name='btt_help_finance' value='...' OnClick='help_finance_no()'></td>");
			//	out.println("<td width='*%'>&nbsp<b>Client name -</td>");
				out.println("</tr>"); 

				out.println("<tr class=tr_input>");
			//	out.println("<td width='10%' >&nbsp;</td>"); //comment by Prabash on 03-02-2012
				out.println("<td width='10%' ><DIV id='DIV_TXT_DEBIT_NOTE_NO' class=div_input>Debit Note No</DIV></td>"); 
				out.println("<td width='25%' ><input type=text name='TXT_DEBIT_NOTE_NO' class=div_input style=width:150px onBlur='help_invoice_no()'>");
				out.println("&nbsp;<input type=button class='but_input' name='btt_help_invoice' value='...' OnClick='help_invoice_no()'></td>");
				out.println("<td width=10%><input class='but_input' type='button' name='BUT_TXT_SEARCH' value=\"Search\" onClick=\"get_debit_notes()\"></td>");//document.Form1.TXT_INV_TYPE.value
				out.println("<td width='*%'>&nbsp;</td>");
				out.println("</tr>"); 			
				
			
				
							
				out.println("</table>"); 

 			 out.println("<table align='center' width='100%' class='table'>"); 

				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
		
			   out.println("</table>");
				  
					out.println("</td></tr><tr>");  
				  out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  	
				  out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					
				out.println("</td></tr><tr>");  
				out.println("</tr><tr>");  	
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
