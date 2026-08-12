/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY SANJEEWA ON 2010-07-14
// DISPLAY NAME INVOICE DETAIL REPORT
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_OP_invoice_detail_report_display extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
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
			String m_client_code="";
			String m_from_date="";
			String m_to_date="";
			String query="";
			m_client_code = req.getParameter("client_code").trim();																		
			m_from_date = req.getParameter("as_at_date").trim();																		
			m_to_date = req.getParameter("as_at_date1").trim();
	        
	
			stmt=conn.createStatement();
			ServletOutputStream out = res.getOutputStream();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoice Detail Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
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
			out.println("<td style='height: 30px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Detail Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>");
		    out.println("</tr>");
			out.println("</table>"); 
			out.println("</table>"); 
			
			
			if(m_client_code.length()==0){
			query="SELECT count(a.invoice_no),sum(a.invoice_amount),INITCAP(to_char(due_date,'mon-yyyy')) "+
				            

							"FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+

            				"WHERE A.due_date >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+ 

            				"AND A.due_date <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+  

							"AND A.invoice_status='CONF' "+
							
							"GROUP BY to_char(due_date,'mon-yyyy') ";
			
			}else{
			
			query="SELECT a.client_code,A.facility_no,count(a.invoice_no),sum(a.invoice_amount),INITCAP(to_char(due_date,'mon-yyyy')),"+

					"(SELECT B.full_name FROM LAKDL.FA_CO_MAS_CLIENT B WHERE B.client_code=a.client_code) as full_name "+		
				    "FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+

            				"WHERE A.due_date >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+ 

            				"AND A.due_date <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+  

							"AND A.invoice_status='CONF' "+
							
							"AND A.client_code= '"+m_client_code+"' "+

							"GROUP BY A.client_code,A.facility_no,to_char(due_date,'mon-yyyy') ";
			
			
			}

			rs = stmt.executeQuery(query);
			boolean more = rs.next();
		
		if(m_client_code.length()==0){
						out.println("<br>");
			            out.println("<table border=0 class='table' width='60%'>");
			            out.println("<tr class='pdn_txtpos2'>");
			            out.println("<td width='20%' align='center'>Month</td>");
	                    out.println("<td width='20%' align='center'>No of Invoices</td>");
			            out.println("<td width='20%' align='center'>Invoice Amount</td>");
					    out.println("</tr>");
          
              int j = 0;      					
              while(more){
						
									if(j>0 && j%2==1){
										out.println("<tr class=tr_input >");
								    }
									else{
										out.println("<tr class=tr_input1 >");
									}
								 
									out.println("<td width='20%' style= cursor:hand;cursor-color:blue onclick=show_inv_det_rep_by_month('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>"); 
									out.println("<td width='20%' style= cursor:hand;cursor-color:blue >"+rs.getString(1)+"</td>"); 
									out.println("<td width='20%' align='right' style= cursor:hand;cursor-color:blue >"+rs.getString(2)+"</td>"); 
									out.println("</tr>"); 		
									more = rs.next();
									j=j+1;
	              }
			}else{
			
						out.println("<br>");
			            out.println("<table border=0 class='table' width='75%'>");
			            out.println("<tr class='pdn_txtpos2'>");
			            out.println("<td width='15%' align='center'>Month </td>");
	                    out.println("<td width='15%' align='center'>Client Name </td>");
			            out.println("<td width='15%' align='center'>Facility No </td>");
						out.println("<td width='15%' align='center'>No of Invoices  </td>");
			            out.println("<td width='15%' align='center'>Invoice Amount</td>");
			            out.println("</tr>");
          
              int j = 0;      					
              while(more){
						
									if(j>0 && j%2==1){
									out.println("<tr class=tr_input >");	
									
									}
									else{
									out.println("<tr class=tr_input1 >");
									}
								 
									out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_inv_det_rep_by_client('"+m_client_code+"','"+rs.getString(5)+"')> <u>"+rs.getString(5)+"</u></td>"); 
									out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')><u>"+rs.getString(6)+"</u></td>"); 
									out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></td>"); 
									out.println("<td width='15%' style= cursor:hand;cursor-color:blue onclick=show_inv_det_rep_by_client('"+m_client_code+"','"+rs.getString(5)+"')><u>"+rs.getString(3)+"<u></td>"); 
									out.println("<td width='15%' align='right' style= cursor:hand;cursor-color:blue onclick=show_inv_det_rep_by_client('"+m_client_code+"','"+rs.getString(5)+"')><u>"+rs.getString(4)+"<u></td>");
							        out.println("</tr>"); 		
									more = rs.next();
									j=j+1;
	              }
			}
			out.println("</table>");
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</BODY>"); 
			out.println("</html>"); 
			out.flush();
			
			
			
			
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
			     	
			
			
			
						
			
			
			
