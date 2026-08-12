import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_Pledge_details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

    public ResultSet rs,rs1,rs2,rs3,rs4,rs_invoice;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 

			String url = "";
			if(req.getParameter("url")!=null){
			url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
	 		m_html_client_url="http://www.lakdac.lk"; 
			m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
		
			else{
		
			m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}
			
			
			//---------------------------------------------------------------
			
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

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			stmt_invoice=conn.createStatement();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
			else if(m_chksql.equals("SHOW_RUNNING_CON_DETAILS")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
			
			
					out.println("<HTML><HEAD><TITLE> Security Running Case - Application No : "+m_application_no+" </TITLE></HEAD>");
					
					out.println(" <SCRIPT language1.2='JavaScript' > ");
					
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    	window.open(m_url); ");
					out.println("	}");	
					
					out.println(" </SCRIPT> ");
					
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Pledge Details (Loan Contract) : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
			
					out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
	

				
				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
												" APPLICATION_NO, "+ //1
												" NVL(FINANCE_NO,'-'), "+
												" CLIENT_CODE "+
												" FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS "+
												" WHERE PLEDGE_CONTRACT = '"+m_application_no+"' "+
												" ");
    
				boolean more4 = rs4.next();		
				int i4=0;
				int line_no4=0;
				
				
				if(more4){
					
					//out.println(" <tr > ");
					//out.println(" <td > ");

					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Loan Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Loan Number</b></td> ");
					out.println("  <td width=\"15%\"  align='left'><b>Application No</b></td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs4.getString(3)+"','"+rs4.getString(2)+"');\" ><u>"+rs4.getString(2)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}
				
				//out.println("</table >");
				//out.println(" </td > ");
				//out.println(" </tr > ");
				//rs4.close();

				
				
				
				out.println("<tr> </tr>");

				
				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
												" B.APPLICATION_NO, "+ //1
												" B.FINANCE_NO, "+
												" B.CLIENT_CODE "+
												" FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
												" WHERE A.PLEDGE_CONTRACT = B.FINANCE_NO "+
												" AND A.FINANCE_NO = '"+m_application_no+"' "+
												" ");
    
				 more4 = rs4.next();		
				 i4=0;
				 line_no4=0;
				
				
				if(more4){
					


					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Pledge Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Pledge Number</b></td> ");
					out.println("  <td width=\"15%\"  align='left'><b>Application No</b></td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs4.getString(3)+"','"+rs4.getString(2)+"');\" ><u>"+rs4.getString(2)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}
				

				rs4.close();
				
				out.println("</table >");
				
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			
				
			}
			
			
			else {
			    out.println("Undefined");
			}

      out.close();
			conn.close();
			this.destroy();
			
			
			}
			catch (Exception e) {
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}


