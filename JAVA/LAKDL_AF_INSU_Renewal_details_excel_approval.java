// CREATED BY Kanishka ON 2019-03-11
// DISPLAY NAME Insurance Renewal Details - Approval

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_INSU_Renewal_details_excel_approval extends javax.servlet.http.HttpServlet {
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn = null;
		Statement stmt 	= null;
		CallableStatement callstmt = null,callstmt1 = null;
		java.text.NumberFormat nf = null,nf1 = null;
		ResultSet rs = null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username   =m_sn_methods.username.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			out = res.getOutputStream();
			
			stmt = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){ 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Insurance Renewal Details - Approval</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("var m_sav_msg='';");
				
				out.println(" function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_approval?chksql=main_page';"); 
				out.println("		}"); 
				out.println(" }"); 
				
				out.println(" function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_approval?chksql=main_page';"); 
				out.println(" }"); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_OFSCL_AF_RE_Insurance_Payable_Report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Insurance Renewal Details  - Approval - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Insurance Renewal Details  - Approval\";"); 
				out.println("}"); 
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"NEW\"){"); 
				out.println("			new_window();"); 
				out.println("		}"); 
				out.println("		else if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("		if(m_val==\"NEW\"){");
				out.println("			document.Form1.hid_status.value=\"New\";"); 
				out.println("		}");
				out.println("		else if(m_val==\"EDIT\"){");  
				out.println("			document.Form1.hid_status.value=\"Edit\";");  
				out.println("		}");
				out.println("		else if(m_val==\"DACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("		}");
				out.println("		else if(m_val==\"RACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("		}");
				out.println("		else{");  
				out.println("			document.Form1.hid_status.value=\"\";");  
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function get_display_msg(){"); 
				out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("		m_sav_msg=\"Save\";"); 
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
				out.println("		m_sav_msg=\"Modify\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
				out.println("		m_sav_msg=\"Deactivate\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
				out.println("		m_sav_msg=\"Reactivate\";");  
				out.println("	}");
				out.println("	else{");  
				out.println("		m_sav_msg=\"\";");  
				out.println("	}"); 
				out.println("}"); 
				
				out.println("   function getApprovals(){ ");
				out.println("		m_url = \""+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_approval?chksql=getApprovals\";"); 
				out.println("    	load_interface(m_url,'NORM');");
				out.println("   }"); 
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" approvalDetails.innerHTML = ''; ");
				out.println(" approvalDetails.innerHTML = http_response; ");
				out.println("}");
				
				out.println("   function viewApproval(batchId){ ");
				out.println("		m_url = \""+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_approval?chksql=getApprovalListOfBatch&batchId=\"+batchId;"); 
				out.println("    	load_interface(m_url,'NORM');");
				out.println("   }"); 
				
				out.println("function proceed(batchId) {");
				out.println("    if(confirm('Are you sure want to proceed?')){ ");
				out.println("    	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_upload_confirmation?screen=APPROVAL&mode=CONFIRM&batchId='+batchId; ");
				out.println("    }");
				out.println("}");
				
				out.println("function reject(batchId) {");
				out.println("    if(confirm('Are you sure want to proceed?')){ ");
				out.println("    	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_upload_confirmation?screen=APPROVAL&mode=REJECT&batchId='+batchId; ");
				out.println("    }");
				out.println("}");
				
				
				//------------------------------------------------------------------------------------------------**
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"getApprovals();\">"); 
				out.println(" <form name = \"Form1\" method = \"post\" >");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_com_name' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='username' VALUE=\""+m_username+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Renewal Details Approval</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>"); 
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='100%' id='approvalDetails' ></td>"); 
				out.println("</tr>"); 
				out.println("</table>");  
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
				
				
			}else if(m_chksql.equals("getApprovals")){
				
				
				
				rs = stmt.executeQuery( "   SELECT ROWNUM,A.* FROM ( "+
					"   SELECT  UPLOAD_BATCH, "+
					"	      "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(PAYEE_CODE) PAYEE_NAME, "+
					"	        COUNT(UPLOAD_BATCH) BATCH_ITEMS, "+
					"       	SUM(SUM_INSURED)        TOTAL_SUM_INSURED, "+
					"       	SUM(PAYABLE_PREMIUM)    TOTAL_PAYABLE_PREMIUM, "+
					"      		SUM(ADDITIONAL_CHARGES) TOTAL_ADDITIONAL_CHARGES "+
					" 	FROM  "+m_schema_name+".AF_INSU_RENEWAL_EXCEL "+
					"	WHERE PROCESS_STATUS = 'P' "+
					"	GROUP BY  UPLOAD_BATCH,"+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(PAYEE_CODE)"+
					"   ) A ");
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class='pdn_txtpos2'>");
				out.println(" 	<td width='10%'>Seq.</td>");
				out.println(" 	<td width='10%'>Batch Id</td>");
				out.println(" 	<td width='20%'>Payee Name</td>");
				out.println(" 	<td width='10%'>Batch policy's</td>");
				out.println(" 	<td width='10%'>Total Sum Insured</td>");
				out.println(" 	<td width='10%'>Total Payable Premiun</td>");
				out.println(" 	<td width='10%'>Total Additional Charges</td>");
				out.println(" 	<td width='10%'>View Details</td>");
				out.println("</tr>");
				
				int j=0;
				while(rs.next()){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println(" 	<td >"+rs.getString(1)+"</td>");
					out.println(" 	<td >"+rs.getString(2)+"</td>");
					out.println(" 	<td >"+rs.getString(3)+"</td>");
					out.println(" 	<td >"+rs.getString(4)+"</td>");
					out.println(" 	<td >"+nf.format(rs.getDouble(5))+"</td>");
					out.println(" 	<td >"+nf.format(rs.getDouble(6))+"</td>");
					out.println(" 	<td >"+nf.format(rs.getDouble(7))+"</td>");
					out.println(" 	<td ><input type='button' class='but_input' style='width:100px;' value='Details' onClick=\"viewApproval('"+rs.getString(2)+"')\"></td>");
					out.println("</tr>");
					j++;
				}
				
				if(j==0){
				out.println("<tr class=tr_input>");
				out.println(" 	<td colspan='8' align='center'><b>No Records Found</b></td>");
				out.println("</tr>");
				}
				
				out.println("</table>");
				
			}else if(m_chksql.equals("getApprovalListOfBatch")){
				
				String batchId = req.getParameter("batchId");
				
				rs = stmt.executeQuery("SELECT "+
					//"	 upload_batch    \"Batch Id\",   "+
					"    ROWNUM||'&nbsp;' \"Seq No\", "+
					"    PAYEE_CODE      \"Payee Code\", "+
					"    VEHICLE_NO      \"Vehicle No\", "+
					"    VEHICLE_TYPE    \"Vehicle Type\", "+
					"    POLICY_NO       \"Policy No\", "+
					//"    TO_CHAR(START_DATE,'DD-MON-YYY')      \"Start Date\", "+ // commented by udara 30-04-2019
					"    TO_CHAR(START_DATE,'DD-MON-YYYY')      \"Start Date\", "+ // added by udara 30-04-2019 to fix display issue
					"    REMARKS                 \"Remarks\", "+
					"    SUM_INSURED             \"Sum Insured\", "+
					"    TAX                     \"Tax\", "+
					"    BASIC_PREMUIM           \"Basic Premium\", "+
					"    PAYABLE_PREMIUM         \"Payable Premium\", "+
					"    ADDITIONAL_CHARGES      \"Additional Charges\", "+
					"    DECODE(PROCESS_STATUS,'P','Pending Authorise','E','Error Occured') \"Status\", "+
					"    ENT_USER              \"Enter User\", "+
					"    ENT_DATE              \"Enter Date\", "+
					"    RCC                   \"RCC\", "+
					"    TC                    \"TC\", "+
					"    BASIC_COMMISSION_RATE \"Basic Commission Rate\", "+
					"    RCC_COMMISSION_RATE   \"RCC Commission Rate\", "+
					"    BASIC_COMMISSION      \"Basic Commission\", "+
					"    RCC_COMMISSION        \"RCC Commission\", "+
					"    VAT_ON_COMMISSION     \"VAT On Commission\" "+
					" FROM " + m_schema_name + ".af_insu_renewal_excel "+
					" where upload_batch 	= '"+batchId+"' "+
					" and   process_status 	= 'P'"+
					" order by seq");
				
				int numberOfColumns=0;
				String m_length="",m_color="";
				Double dblValue=null;
				String strValue="";
				
				ResultSetMetaData rsmd = rs.getMetaData();
				numberOfColumns = rsmd.getColumnCount();
				
				//Dynamic report Printing
				out.println("<br>");
				out.println("<center><h2>Insurance Renewal Details - "+batchId+"</h2></center>");
				out.println("<table cellspacing='0' cellpadding='5'  align='center' width='3700px;'  >");
				
				//Dynamic Header Part
				out.println("<tr style='bgcolor:#F0F3F4; border: 1px ridge black;'>");
				for(int x=1;x<=numberOfColumns;x++){
					m_length="150";
					out.println("<td class='pdn_txtpos2' style='align:center; border: 1px ridge black; width:"+m_length+"px; '><B>"+rsmd.getColumnName(x)+"</b></td>");
				}
				out.println("</tr>");
				
				int validCount=0;
				int errorCount=0;
				int j=0;
				//Dynmic Body Part
				while(rs.next()){
					validCount++;
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 style='border: 1px ridge black;'>");
					}
					else{
						out.println("<tr class=tr_input style='border: 1px ridge black;'>");
					}
					j++;
					
					for(int x=1;x<=numberOfColumns;x++){
						m_color="";
						try{
							dblValue=rs.getDouble(x);
							m_length="150";
							out.println("<td style='text-align:right;border: 1px ridge black; "+m_color+" width:"+m_length+"px; '>"+nf.format(dblValue)+"</td>");
						}catch(Exception e){
							strValue=rs.getString(x);
							m_length="150";
							if(x==1){strValue=Integer.toString(j);}
							out.println("<td style='border: 1px ridge black; "+m_color+" width:"+m_length+"px; '>"+strValue+"</td>");
						}
					}
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				out.println("<br><br><br>");
				
				out.println("<table width='40%' align='center'  >");
				
				
				out.println("<tr>");
				out.println("<td width = '50%' align=center>&nbsp;</td>");
				out.println("<td width = '50%' align=center>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table cellspacing='0' cellpadding='5'  align='left' width='40%'  >");
				out.println("<tr>");
				out.println("<td width = '50%' align=center><input type='button' value='Approve' style='width:200px; height:40px; color:white; background-color:#337A23; pointer:cursor;' onClick=\"proceed('"+batchId+"');\"></td>");
				out.println("<td width = '50%' align=center><input type='button' value='Reject'  style='width:200px; height:40px; background-color:#AD1212; color:white; pointer:cursor;' onClick=\"reject('"+batchId+"');\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
			} // END FOR m_chksql	
			
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






