 
//Created by Nuwan De Silva
//Purchase Order Print Status Save

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Save_Purchase_Order_View_Letter extends javax.servlet.http.HttpServlet {
	
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
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
LAKDL_AF_CO_conn_methods con_method=new LAKDL_AF_CO_conn_methods();
conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username = con_method.username;
   //   String m_username 						= "AA";//m_sn_methods.username;
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
			//m_no_of_due_days = req.getParameter("pur_ord_no");
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("save_page")){
					   

      //String m_invoice_no = req.getParameter("invoice_no");
			//String m_client_no = req.getParameter("client_no");
			String m_pur_ord_no = req.getParameter("pur_ord_no");
			String m_scr_name = req.getParameter("scr_name");
			//String m_pur_ord_no	  =req.getParameter("pur_ord_no");		
			String m_app_no	      =req.getParameter("app_no");		
			String m_vendor_code	=req.getParameter("vendor_code");		
			String m_status       =req.getParameter("status");
			String m_print=req.getParameter("print");
			String m_branch_code=req.getParameter("branch_code");
			String m_scr_type=req.getParameter("scr_type");//Added by Dineth on 26-03-2009
			String m_client_code=req.getParameter("client_code");//Added by Dineth on 26-03-2009
					
				
 
	
	    	  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PUR_PRNT_STUS(:1,:2,:3); END;");
				
				  callstmt.setString(1,m_pur_ord_no);
				  callstmt.setString(2,m_username);
				  callstmt.setString(3,m_scr_name);
					callstmt.execute();
					
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			//out.println("alert('updating Records');");
		
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days="+m_no_of_due_date+"';");
			//out.println("if("+m_status+"==\"COPY\"){");
			//Added by Dineth on 26-03-2009
			if(m_scr_type.equals("PUR_ORD")){
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no="+m_pur_ord_no+"&app_no="+m_app_no+"&status="+m_status+"&print=FALSE&vendor_code="+m_vendor_code+"&branch_code="+m_branch_code+"';"); 
			}
			else if(m_scr_type.equals("DEL_ORD")){
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_app_no+"&client_code="+m_client_code+"&status="+m_status+"&pur_ord_no="+m_pur_ord_no+"&print=FALSE';");
			//Added by ns on 07/10/2011
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Delivery_Order?chksql=main_page&pur_ord_no="+m_pur_ord_no+"&print=FALSE';");
			
			}
			//End by Dineth on 26-03-2009
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=\"+m_val;"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
						
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
