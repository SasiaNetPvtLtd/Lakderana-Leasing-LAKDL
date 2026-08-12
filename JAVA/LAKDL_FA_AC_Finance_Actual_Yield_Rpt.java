import java.lang.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import LAKDL_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_FA_AC_Finance_Actual_Yield_Rpt extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
	//LAKDL_sn_methods m_LAKDL_sn_methods;
	java.text.NumberFormat nf;
	boolean more;
	boolean flg1=false,flg2=false; 
	public String check_sql,m_username;
	public ResultSet rs,rst,rst1,rst2,rst3,rst4,rst5,rst6,rs2,rs3;
	public String m_chksql;
	public synchronized void service(HttpServletRequest req, HttpServletResponse	res)
		throws IOException
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			//************************************************************
			
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if (m_chksql.trim().equals("LOAD_YIELD")) {

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_rpt_st_date=req.getParameter("m_rpt_st_date");
				String m_rpt_end_date=req.getParameter("m_rpt_end_date");

        String m_month="";
				String m_month_name="";
        double m_yield=0.0;
				String m_curr_month="";
				String m_curr_month_name="";
        double m_curr_yield=0.0;

				int count = 0 ;
				
				rs=stmt1.executeQuery("SELECT ABS(ROUND(MONTHS_BETWEEN(TO_DATE('"+m_rpt_end_date+"','DD-MM-YYYY'),"+
				" TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY')))) FROM DUAL" );
				
				boolean more= rs.next();
				
				if(more){
					count = rs.getInt(1);
						
					out.println("<table align='left' width='40%' class='table' >");
					out.println("<tr>");
					out.println("<td width='20%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Month</b></DIV></td>");
					out.println("<td width='20%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Yield Actual</b></DIV></td>");
					out.println("<td width='20%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Yield Suspended</b></DIV></td>");
					out.println("<td width='20%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Monthly Utilization</b></DIV></td>");
					out.println("</tr>");						

					int x=1;
					
					for(int j=0;j<=count;j++){
						
						if(!m_facility_code.equals("")){
						//out.println("IIIIOOOO");
					  rs3=stmt2.executeQuery("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'MON-YYYY'), "+
						" "+m_schema_name+".FA_MONTH_ACT_YEILD_BRK('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'ACT'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_MONTH_ACT_YEILD_BRK('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'SUS'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY')) "+
						" FROM DUAL " );
						}
						else{
						rs3=stmt2.executeQuery("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'MON-YYYY'),"+
						" "+m_schema_name+".FA_TFAC_MONTH_ACT_YEILD_BRK(TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'ACT'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_TFAC_MONTH_ACT_YEILD_BRK(TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'SUS'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_FACTORING_MONTH_UTILI_GL(TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY')) "+
						" FROM DUAL " );
						}
						//out.println("OPOPO");
						boolean more3=rs3.next();
						
						if(more3){	
							out.println("<tr bgcolor=\"#FFFFFF\">");
							out.println("<td width='20%' align='left' ><DIV class=div_input><b>"+rs3.getString(1)+"</b></DIV></td>");
							out.println("<td width='20%' align='right'><DIV class=div_input><b>"+nf.format(rs3.getDouble(2))+"</b></DIV></td>");//+nf.format(rs3.getDouble(5))+
							out.println("<td width='20%' align='right'><DIV class=div_input><b>"+nf.format(rs3.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='20%' align='right'><DIV class=div_input><b>"+nf.format(rs3.getDouble(4))+"</b></DIV></td>");
							out.println("</tr>");
		      	}
        	}
				
				} //more
				
				
        out.println("</table>");

			} // end of chksql
			
			else {
				out.println("Undefined");
			}
			conn.close();
			this.destroy();
		}		catch (SQLException sql) {}
		catch (Exception e) {
			try {
				conn.close();
			}	
			catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}
