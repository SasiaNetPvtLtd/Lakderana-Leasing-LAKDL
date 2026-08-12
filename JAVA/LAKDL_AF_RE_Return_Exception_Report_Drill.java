//
//This File was created by Nuwan De Silva 20/12/06
//Collection - Repossession Drill Down
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Return_Exception_Report_Drill extends javax.servlet.http.HttpServlet {
	
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
					
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			String m_cus_name="";
	
					
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
					
    	else if(m_chksql.trim().equals("drill")){
			
			String m_finance_no		    = req.getParameter("finance_no");
			
		
			
 			rs = stmt.executeQuery (	" SELECT "+
 																"        FULL_NAME "+
 																" FROM   LAKDL.AF_CO_MAS_CLIENT "+
 																" WHERE CLIENT_CODE =( "+
 																"  		              SELECT "+
 																"                            CLIENT_CODE "+
 																"                    FROM    "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 																"                    WHERE   FINANCE_NO='"+m_finance_no+"'     "+
 																"                     )       ");
			
			if(rs.next()){
			m_cus_name=rs.getString(1);
			}
			
			
        
						  
			 	  out.println("<html>");
					out.println("<head>");
					out.println("<title>Asset Financing System</title>    ");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("</head>");
					out.println("<Script>");
					out.println("var m_bsubmit = '0';");
					out.println("var arr_assign= new Array();");
					out.println("var m_send_val= '';");
					
								
				
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
					out.println("<td height=\"30\" class=\"pdn_mainHD\" class>Asset Financing System</td>");
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
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection - Repossession Details</td>");
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
					out.println("<tr class=tr_input align='left'>");
					out.println("<td  width='15%' ><b>Finance No </td><td  width='40%' ><b>"+m_finance_no+"</td>");
					out.println("</tr>");
					
					out.println("<tr class=tr_input align='left'>");
					out.println("<td  width='15%' ><b>Client Name</td><td  width='40%' ><b>"+m_cus_name+"</td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					out.println("<table class=table border='1' width='100%' >");
					
					out.println("<tr class=tr_input align='left'>");
					out.println("<td  width='15%' ><b>Repossession No</td>");
					out.println("<td  width='10%' ><b>Sizer Code</td>");
          out.println("<td  width='25%' ><b>Sizer Name</td>");
					out.println("<td  width='10%' ><b>Letter Validity Period</td>");
					out.println("<td  width='10%' ><b>Vehicle Inventory Status</td>");
					out.println("<td  width='15%' ><b>Ent Date</td>");
					out.println("<td  width='15%' ><b>Effective Value Date</td>");
					out.println("</tr>");
					
																	
					int j = 0;   
					
						rs = stmt.executeQuery (" SELECT "+
    															" 		    A.FINANCE_NO FINANCE_NO, "+     
    															" 		    A.REPOSSESSION_NO REPOSSESSION_NO, "+
    															" 			  A.SEIZER_CODE SEIZER_CODE, "+
																	"					(B.FIRST_NAME || ' ' || B.LAST_NAME) NAME, "+
    															" 		    A.LETTER_VALIDITY_PERIOD LETTER_VALIDITY_PERIOD, "+
    															" 		    A.VEHICLE_INVENTORY_STATUS VEHICLE_INVENTORY_STATUS, "+
    															" 		    TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+
    															" 		    NVL((TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')),'-') EFF_VAL_DATE "+
																	" 				FROM    "+m_schema_name+".AF_RE_PRO_REPOSSESSION A,"+m_schema_name+".AF_CO_MAS_SEIZER B "+
																	" WHERE   A.FINANCE_NO='"+m_finance_no+"' AND "+
    															" 			  A.ACTIVE_STATUS='Y' AND A.SEIZER_CODE=B.SEIZER_CODE ");
							
 												
					
					 while(rs.next()){
							    
					/*				if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
					*/
									
									out.println("<tr class=tr_input >");
         //        out.println("<td width='15%'align='left'>"+rs.getString(1) +"</td> ");
									out.println("<td width='15%' align='left'>"+rs.getString(2) +"</td>");
                  out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
									out.println("<td width='25%' align='left'>"+rs.getString(4)+"</td>");
                  out.println("<td width='10%' align='left'>"+rs.getInt(5) +"</td>");
                  out.println("<td width='10%' align='left'>"+rs.getString(6) +"</td>");
									out.println("<td width='15%' align='left'>"+rs.getString(7) +"</td>");
									out.println("<td width='15%' align='left'>"+rs.getString(8) +"</td>");
									
         					out.println("</tr>");
                	j=j+1;
              }
					
					
																	
				        
      
					
					out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					
			
					
				
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
