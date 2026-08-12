// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:19-01-2012
              
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_RE_client_stmt_report_new_drill extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_client_no = "";
			String m_facility_no = "";
			String m_date_from = "";
			String m_date_to = "";
			
			try{
				 m_client_no=req.getParameter("client_code").trim();
				 m_facility_no=req.getParameter("facility_no").trim();
				 m_date_from=req.getParameter("date_from").trim();
				 m_date_to=req.getParameter("date_to").trim();
			}
			catch(Exception aa){
				out.println(aa.toString());
			}
			
			
			 try{
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Client Statement Reports </TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println(" 	var m_from_date = '"+m_date_from+"'; ");
					out.println(" 	var m_to_date   = '"+m_date_to+"'; ");
					out.println(" 	var facility_no = '"+m_facility_no+"' ");
					out.println("   var client_code = '"+m_client_no+"' ");
					
					out.println("function generate_report() {");	
					//out.println("		document.Form1.hid_option.value='1'; "); 
					//out.println("       current_account_statement(); ");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=current_account_statement&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					//out.println("	     load_interface(m_url,'NO');");	
					out.println("}");
					
					/*
					out.println("function current_account_statement() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=current_account_statement&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function sales_ledger() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=sales_ledger&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function invoice_batch_details() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=invoice_batch_details&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function invoice_settlement_details() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=invoice_settlement_details&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function invoice_reassignments() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=invoice_reassignments&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function invoice_adjustment_details() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=invoice_adjustment_details&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					
					out.println("function unallocated_fund() {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new_report_drill?chksql=unallocated_fund&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&client_code=\"+client_code+\"&facility_no=\"+facility_no;");
					out.println("	     load_interface(m_url,'NO');");			
					out.println("}");
					*/
					
					/*
					out.println("function get_vector_normal(m_data){");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=m_data;");
					
					out.println("		if(document.Form1.hid_option.value=='1'){ ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=m_data;");
					out.println("		    document.Form1.hid_option.value='2'; "); 
					out.println("           sales_ledger(); ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='2'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='3'; "); 
					out.println("           invoice_batch_details(); ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='3'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='4'; "); 
					out.println("           invoice_settlement_details(); ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='4'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='5'; "); 
					out.println("           invoice_reassignments();  ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='5'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='6'; "); 
					out.println("           invoice_adjustment_details();  ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='6'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='7'; "); 
					out.println("           unallocated_fund();  ");
					out.println("       }");
					out.println("		else if(document.Form1.hid_option.value=='7'){ ");
					out.println("			var content = document.getElementById('invoice_detail_data').innerHTML; ");
					out.println("           content = content+'<br><hr><br>'+m_data; ");
					out.println("			document.getElementById('invoice_detail_data').innerHTML=content;");
					out.println("		    document.Form1.hid_option.value='8'; "); 
					out.println("       }");

					
					out.println("}");
					*/
		
					
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"generate_report();\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
		
					out.println("<br>"); 
					out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
					out.println("<br>"); 
					
					out.println("<br>"); 
					out.println("<DIV id='invoice_detail_data_2'  class=div_input></DIV>");
					out.println("<br>"); 
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();
					
				}
				
				catch(Exception bb){
					out.println(bb.toString());
				}
					
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
