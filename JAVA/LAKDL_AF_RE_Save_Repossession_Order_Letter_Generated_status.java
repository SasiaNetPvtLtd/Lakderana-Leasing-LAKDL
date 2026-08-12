//Created by Nuwan De Silva
//Purchase Order Print Status Save

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Save_Repossession_Order_Letter_Generated_status extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			out = res.getOutputStream();
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username = con_method.username;
			String m_schema_name = con_method.schema_name.trim();
      String m_gur_name="";
			String m_client_type="";
			String m_purch_no="";
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql         = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("save_page")){
			String m_pro_invoice_no="";
			String m_repossession_no   = req.getParameter("repossession_no").trim();
			String m_finance_no        = req.getParameter("finance_no").trim();
			String m_seizer_code        = req.getParameter("seizer_code").trim();
			String m_scr_name          = req.getParameter("scr_name").trim();
			String m_type              = req.getParameter("type").trim();

      callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_REP_ORD_LET_STATUS(:1,:2,:3,:4,:5); END;");
      int i=0;
			if (m_type.equals("main_page")){
			
			String  m_reposession[]=m_repossession_no.split("@",m_repossession_no.length());			
			String  invoice_no="";
			
			while(i<m_reposession.length){
			if (!m_reposession[i].equals("")){
 		  String sql_inv=" SELECT "+
	                   " PRO_INVOICE_NO "+
	                   " FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
			               " WHERE REPOSSESSION_NO='"+m_reposession[i].trim()+"' "+
			               " AND FINANCE_NO='"+m_finance_no+"'";
														
 			   rs = stmt.executeQuery(sql_inv);
	       boolean more = rs.next();
				 if(more){
					invoice_no=rs.getString(1);
				 }	
			
					callstmt.setString(1,m_reposession[i].trim());
					callstmt.setString(2,invoice_no);
					callstmt.setString(3,m_finance_no);
				  callstmt.setString(4,m_username);
				  callstmt.setString(5,m_scr_name);
					callstmt.execute();
		    }
		    i=i+1;
        }
				

			}
				else if (m_type.equals("copy")){

					m_pro_invoice_no  = req.getParameter("pro_invoice_no").trim();
		    
				  callstmt.setString(1,m_repossession_no);
					callstmt.setString(2,m_pro_invoice_no);
					callstmt.setString(3,m_finance_no);
				  callstmt.setString(4,m_username);
				  callstmt.setString(5,m_scr_name);
					callstmt.execute();
				}
					
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Repossession_Order_Letter?chksql="+m_type+"&repossession_no="+m_repossession_no+"&seizer_code="+m_seizer_code+"&pro_invoice_no="+m_pro_invoice_no+"&finance_no="+m_finance_no+"&print=FALSE';"); 
					
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
						
			out.println("</html>");
									
					
 
			
			
			}
	
			
		}
		
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}
		
			
		finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
		
		
		
	}
}
