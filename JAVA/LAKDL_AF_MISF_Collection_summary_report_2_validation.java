import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_Collection_summary_report_2_validation extends javax.servlet.http.HttpServlet {
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
				String m_finance_no = req.getParameter("finance_no");
				String m_branch = req.getParameter("branch"); // added by udara on 09-10-2013
				String m_coll_off = req.getParameter("coll_off"); // added by udara on 09-10-2013
				String m_region = req.getParameter("region"); // Added By Samith Dilshan On 2015-06-04
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REPORT(:1,:2);END;"); // commented by udara on 09-10-2013
					
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3);END;"); //callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3,:4);END;");
					
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3,:4);END;") // Modified By Samith Dilshan On 2015-06-04
					
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					
					//callstmt1.setString(3,m_branch);  // added by udara on 09-10-2013
					callstmt1.setString(3,m_coll_off);  // added by udara on 09-10-2013
					
					//callstmt1.setString(4,m_region);  // Added By udara on Samith Dilshan On 2015-06-04
					
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					ex.printStackTrace();
					m_string = "ERROR"+ex.toString();
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

