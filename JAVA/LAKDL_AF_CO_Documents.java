//Option Id is 6.0  
//This File was created by SVA on 13-07-2006 
//FoolowUp Alert Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CO_Documents extends javax.servlet.http.HttpServlet {
	
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
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
    	else if(m_chksql.trim().equals("get_documents")){
			
			  String m_deal_no		    = req.getParameter("deal_no");
        String m_foll_no		    = req.getParameter("foll_no");
        if(!m_deal_no.equals("-")){
				  
					 out.println("<html>");
					out.println("<head>");
					out.println("<title>Asset Financing System</title>    ");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("</head>");
					out.println("<Script>");
					out.println("var m_bsubmit = '0';");
					out.println("var arr_assign= new Array();");
					out.println("var m_send_val= '';");
					
					out.println("function load_all_foll(m_stat,opt) {");
					out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup\";");
	        //out.println("   window.open(m_url);");
					out.println("  setInterval('makeRequest(m_url,\"2\")',36000);");
					out.println("}");
				
	        out.println("</Script>");
					out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
					out.println("<form name=\"Form1\" method=post>");
					out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
	        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
	                  
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
					if(m_deal_no.substring(0,2).equals("IQ")){
				
					out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Inquiry Details</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
					out.println("</td>	");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					out.println("<tr class=tr_input>");
					out.println("<td valign=top  width=100% Id=Follow_up> ");
					
					  
					out.println("<table class=table border='0' width='100%' >");
					
     			rs = stmt.executeQuery (" SELECT A.INQUIRY_CODE, A.CLIENT_NAME, NVL(A.TEL_NO,'-'), "+
																	"        NVL(A.MOBILE_NO,'-'),NVL(A.FAX_NO,'-'), "+
																	"        NVL(A.ADDRESS,'-'), NVL(A.ADDRESS2,'-'),"+
																	"        NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-') "+
																	"   FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A "+
																	"  WHERE INQUIRY_CODE='"+m_deal_no+"'  ");
																	
          if(rs.next()){
              out.println("<tr class=tr_input>");
							out.println("<td width=20%>Client Name</td>");
              out.println("<td width=30%>"+rs.getString(2)+"</td>");
              out.println("<td width=20%>Inquiry No</td>");
              out.println("<td width=30%>"+rs.getString(1)+"</td>");
              out.println("</tr>");
							out.println("<tr class=tr_input>");
							out.println("<td >Tel. No</td>");
              out.println("<td >"+rs.getString(3)+"</td>");
              out.println("<td >Mobile No</td>");
              out.println("<td >"+rs.getString(4)+"</td>");
              out.println("</tr>");
							out.println("<tr class=tr_input>");
							out.println("<td >Address</td>");
              out.println("<td >"+rs.getString(6)+"</td>");
              out.println("<td >Fax No</td>");
              out.println("<td >"+rs.getString(5)+"</td>");
              out.println("</tr>");
							if(!rs.getString(7).equals("-")){
							out.println("<tr class=tr_input>");
							out.println("<td ></td>");
              out.println("<td >"+rs.getString(7)+"</td>");
              out.println("<td ></td>");
              out.println("<td ></td>");
              out.println("</tr>");
							}
							if(!rs.getString(8).equals("-")){
							out.println("<tr class=tr_input>");
							out.println("<td ></td>");
              out.println("<td >"+rs.getString(8)+"</td>");
              out.println("<td ></td>");
              out.println("<td ></td>");
              out.println("</tr>");
							}
							
          }
      //}
      
					
					out.println("</tr></table>");
          out.println("</td>");
			}else if(m_deal_no.substring(0,2).equals("PO")){
				
				
				out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>P/O Details</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
					out.println("</td>	");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					out.println("<tr class=tr_input>");
					out.println("<td valign=top  width=100% Id=Follow_up> ");
					
					  
					out.println("<table class=table border='0' width='100%' >");
					 
						
     			 rs = stmt.executeQuery(" SELECT DECODE(OTHER_NO,NULL,APPLICATION_NO,OTHER_NO),"+
							                       "        "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
																		 "        DECODE(CLIENT_CODE,NULL,PRO_INVOICE_NO,CLIENT_CODE),"+
																		 "        "+m_schema_name+".AF_CO_GET_SCREEN_DISPLAY_NAME(LAST_UPDATED_STAGE,'AF'),"+//AF_CO_GET_SCREEN_NAME(STAGE), 
																		 "	      STATUS, REMARK,  "+    
																		 "	      FOLLOWUP_REMARKS "+
																		 " FROM   "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
																		 " WHERE  OTHER_NO='"+m_deal_no+"' AND REF_NO='"+m_foll_no+"' ");
					boolean more = rs.next();												
          if(more){
            out.println("<tr class=tr_input1>");
						out.println("<td width=10%>Ref. No</td>");
            out.println("<td width=20%>Document</td>");
            out.println("<td width=15%>Invoice No/Client Code</td>");
            out.println("<td width=5%>Stage</td>");
						out.println("<td width=10%>Status</td>");
						out.println("<td width=20%>Remarks</td>");
						out.println("<td width=20%>Followup Remarks</td>");
            out.println("</tr>");
						while(more){	
							out.println("<tr class=tr_input>");
							out.println("<td >"+rs.getString(1)+"</td>");
              out.println("<td >"+rs.getString(2)+"</td>");
              out.println("<td >"+rs.getString(3)+"</td>");
              out.println("<td >"+rs.getString(4)+"</td>");
              out.println("<td >"+rs.getString(5)+"</td>");
              out.println("<td >"+rs.getString(6)+"</td>");
              out.println("<td >"+rs.getString(7)+"</td>");
              out.println("</tr>");
							
							more = rs.next();
							if(more){
								out.println("<tr class=tr_input1>");
								out.println("<td >"+rs.getString(1)+"</td>");
	              out.println("<td >"+rs.getString(2)+"</td>");
	              out.println("<td >"+rs.getString(3)+"</td>");
	              out.println("<td >"+rs.getString(4)+"</td>");
	              out.println("<td >"+rs.getString(5)+"</td>");
	              out.println("<td >"+rs.getString(6)+"</td>");
	              out.println("<td >"+rs.getString(7)+"</td>");
	              out.println("</tr>");
							}
							more = rs.next();
							
						}	
							
          }
      //}
      
					
					out.println("</tr></table>");
          out.println("</td>");
				
				
			}else if(m_deal_no.substring(0,2).equals("AP")){
				
				out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Application Details</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
					out.println("</td>	");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					out.println("<tr class=tr_input>");
					out.println("<td valign=top  width=100% Id=Follow_up> ");
					
					  
					out.println("<table class=table border='0' width='100%' >");
					//Modified by Mahela on 17-05-2007
     			rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
																	"	       INQUARY_NO,APPLICATION_STATUS, "+
																	"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT), "+
																	"	       NVL(FACILITY_NO,'-'),NVL(TOTAL_FINANCE_AMOUNT,0), "+
																	"        NVL(TRANSACTION_TYPE,'-'),NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-') "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  APPLICATION_NO='"+m_deal_no+"'");
																	
          if(rs.next()){
              out.println("<tr class=tr_input>");
							out.println("<td width=20%>Client Name</td>");
              out.println("<td width=30%>"+rs.getString(1)+"</td>");
              out.println("<td width=20%>Inquiry No</td>");
              out.println("<td width=30%>"+rs.getString(2)+"</td>");
              out.println("</tr>");
							out.println("<tr class=tr_input>");
							out.println("<td >Application Status</td>");
              out.println("<td >"+rs.getString(3)+"</td>");
              out.println("<td >Co-Applicant Name</td>");
              out.println("<td >"+rs.getString(4)+"</td>");
              out.println("</tr>");
							/*out.println("<tr class=tr_input>");
							out.println("<td >Finance No</td>");
              out.println("<td >"+rs.getString(6)+"</td>");
              out.println("<td >Finance Amount</td>");
              out.println("<td >"+rs.getString(5)+"</td>");
              out.println("</tr>");*/
							out.println("<tr class=tr_input>");
							out.println("<td >Transaction Type</td>");
              out.println("<td >"+rs.getString(7)+"</td>");
              out.println("<td >Activated Date</td>");
              out.println("<td >"+rs.getString(8)+"</td>");
              out.println("</tr>");
							out.println("</table>");
							
							
              out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					    
							/*         
							           out.println(" SELECT DECODE(OTHER_NO,NULL,APPLICATION_NO,OTHER_NO),"+
							                       "        "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
																		 "        DECODE(CLIENT_CODE,NULL,PRO_INVOICE_NO,CLIENT_CODE),"+
																		 "        "+m_schema_name+".AF_CO_GET_SCREEN_DISPLAY_NAME(LAST_UPDATED_STAGE,'AF'),"+//AF_CO_GET_SCREEN_NAME(STAGE), 
																		 "	      STATUS, REMARK,  "+    
																		 "	      FOLLOWUP_REMARKS "+
																		 " FROM   "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
																		 " WHERE  APPLICATION_NO='"+m_deal_no+"' AND REF_NO='"+m_foll_no+"' ");
					    */
							
						  rs = stmt.executeQuery(" SELECT DECODE(OTHER_NO,NULL,APPLICATION_NO,OTHER_NO),"+
							                       "        "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
																		 "        DECODE(CLIENT_CODE,NULL,PRO_INVOICE_NO,CLIENT_CODE),"+
																		 "        "+m_schema_name+".AF_CO_GET_SCREEN_DISPLAY_NAME(LAST_UPDATED_STAGE,'AF'),"+//AF_CO_GET_SCREEN_NAME(STAGE), 
																		 "	      STATUS, REMARK,  "+    
																		 "	      FOLLOWUP_REMARKS "+
																		 " FROM   "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
																		 " WHERE  APPLICATION_NO='"+m_deal_no+"' AND REF_NO='"+m_foll_no+"' ");
					boolean more = rs.next();												
          if(more){
            out.println("<tr class=tr_input1>");
						out.println("<td width=10%>Ref. No</td>");
            out.println("<td width=20%>Document</td>");
            out.println("<td width=15%>Invoice No/Client Code</td>");
            out.println("<td width=5%>Stage</td>");
						out.println("<td width=10%>Status</td>");
						out.println("<td width=20%>Remarks</td>");
						out.println("<td width=20%>Followup Remarks</td>");
            out.println("</tr>");
						while(more){	
							out.println("<tr class=tr_input>");
							out.println("<td >"+rs.getString(1)+"</td>");
              out.println("<td >"+rs.getString(2)+"</td>");
              out.println("<td >"+rs.getString(3)+"</td>");
              out.println("<td >"+rs.getString(4)+"</td>");
              out.println("<td >"+rs.getString(5)+"</td>");
              out.println("<td >"+rs.getString(6)+"</td>");
              out.println("<td >"+rs.getString(7)+"</td>");
              out.println("</tr>");
							
							more = rs.next();
							if(more){
								out.println("<tr class=tr_input1>");
								out.println("<td >"+rs.getString(1)+"</td>");
	              out.println("<td >"+rs.getString(2)+"</td>");
	              out.println("<td >"+rs.getString(3)+"</td>");
	              out.println("<td >"+rs.getString(4)+"</td>");
	              out.println("<td >"+rs.getString(5)+"</td>");
	              out.println("<td >"+rs.getString(6)+"</td>");
	              out.println("<td >"+rs.getString(7)+"</td>");
	              out.println("</tr>");
							}
							more = rs.next();
							
						}	
							
          }
							
          }
      //}
      
					
					out.println("</table>");
          out.println("</td>");
				
			}
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					
				
			 }		
				
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
