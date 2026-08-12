import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_Recovery_Report_2_validation extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt1;
	java.text.NumberFormat nf;
    
    public ResultSet rs,rs1,rs3;
	public String m_chksql;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // synchronized
		
		
		Connection conn=null;
		Statement stmt=null,stmt1=null,stmt3=null;
		CallableStatement callstmt1=null;
		java.text.NumberFormat nf=null;
    
	    ResultSet rs=null,rs1=null,rs3=null;
		String m_chksql=null;
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_username = m_sn_methods.username;
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	    res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		
			else if(m_chksql.equals("run_report")){
				
				String m_string = "";
				
				String m_date=req.getParameter("date");
				String m_location_id=req.getParameter("location_id");
				String m_user_id=req.getParameter("user_id");
				String m_finance_no = req.getParameter("finance_no");
				String m_cr_offic   = req.getParameter("cr_officer");
				String m_perform_stat   = req.getParameter("perform_stat"); // added by udara 21-11-2013
				String m_active_yard_status   = req.getParameter("active_yard_status");   // Added by Samith Dilshan on 27-05-2015
				String m_region   = req.getParameter("region"); // added by udara 13-08-2015
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_RECOVER_RPT(:1,:2,:3,:4,:5,:6);END;");//6 mod by milinda
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_RECOVER_RPT(:1,:2,:3,:4,:5,:6,:7,:8);END;"); // mod by udara 21-11-2013 
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_RECOVER_RPT(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;"); // added by udara 13-08-2015
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.setString(6,m_cr_offic);//added milinda 2013-10-16
					callstmt1.setString(7,m_perform_stat); // added by udara 21-11-2013
					callstmt1.setString(8,m_active_yard_status); // Added by Samith Dilshan on 27-05-2015
					callstmt1.setString(9,m_region); // added by udara 13-08-2015
					callstmt1.execute();
					
					m_string = "OK";
					
				}
				catch(Exception ex){
					//out.println("ERROR"+ex.toString()); 
					m_string="ERROR"+ex.toString();
				}
				
				out.println(m_string);
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

