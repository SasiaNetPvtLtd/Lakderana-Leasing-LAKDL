import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_FA_AC_Finance_Actual_Yield_Rpt_New extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
	//LAKDL_sn_methods m_LAKDL_sn_methods;
	java.text.NumberFormat nf;
	boolean more;
	boolean flg1=false,flg2=false; 
	public String check_sql,m_username;
	public ResultSet rs,rs1,rs3;
	public String m_chksql;
	ServletOutputStream out=null;
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
			
				else if (m_chksql.trim().equals("LOAD_YIELD_ALL")) {

				String m_rpt_st_date     = req.getParameter("m_rpt_st_date");

        String m_month="";
				String m_month_name="";
        double m_yield=0.0;
				String m_curr_month="";
				String m_curr_month_name="";
        double m_curr_yield=0.0;

				int count = 0 ;
				
				/*rs=stmt1.executeQuery("SELECT ABS(ROUND(MONTHS_BETWEEN(TO_DATE('"+m_rpt_end_date+"','DD-MM-YYYY'),"+
				" TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY')))) FROM DUAL" );
				
				boolean more= rs.next();
				*/
				
				String m_month_0="";
				String m_month_1="";
				String m_month_2="";
				String m_month_3="";
				String m_month_4="";
				String m_month_5="";
				String m_month_6="";
				
				rs1= stmt1.executeQuery(
				//out.println(
				"   SELECT "+
				  " TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'MONTH-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'MON-YYYY'),"+//6
					" TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY'),"+//8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'DD-MM-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'DD-MM-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'DD-MM-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'DD-MM-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_rpt_st_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'DD-MM-YYYY')"+//13
					
					" FROM DUAL ");
				
				out.println("<HTML>");
				out.println("<BODY>");
				out.println("<TITLE>Factoring Yield</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				while(rs1.next()){
				out.println("<table align='center' width='100%' class='table' border='1' cellspacing='0' >");
				out.println("<tr>");
				out.println("<td colspan='2'>&nbsp;</td>");
				out.println("<td align='center' bgcolor=\"#CCCCCC\" colspan='6'><B>Utilization</td>");
				out.println("<td align='center' bgcolor=\"#CCCCCC\" colspan='6'><B>Yeild</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\"><B>Facility No </td>");
				out.println("<td width='12%' bgcolor=\"#CCCCCC\"><B>Client No </td>");
        out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("</tr>");
				
				m_month_0=rs1.getString(7);
				m_month_1=rs1.getString(8);
				m_month_2=rs1.getString(9);
				m_month_3=rs1.getString(10);
				m_month_4=rs1.getString(11);
				m_month_5=rs1.getString(12);
				

				}
				
				
				rs1=stmt1.executeQuery(
				//out.println(
				"SELECT "+
				" FACILITY_NO, "+ //1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) ,"+ //2
				
			  " "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_0+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_1+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_2+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_3+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_4+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION(FACILITY_NO,'"+m_month_5+"'), "+

				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_0+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_1+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_2+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_3+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_4+"'), "+
				" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(FACILITY_NO,'"+m_month_5+"') "+
				
				
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				" WHERE FACILITY_STATUS='Y' ");

				while(rs1.next()){

				out.println("<tr>");
				out.println("<td align='left'>"+rs1.getString(1)+"</td>");
				out.println("<td align='left' >"+rs1.getString(2)+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(4))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(13))+"</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
				out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("</HTML>");
				out.println("</BODY>");
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
				" TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY')))) FROM DUAL" );
				
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
					  rs3=stmt2.executeQuery("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'MON-YYYY'), "+
						" "+m_schema_name+".FA_MONTH_ACT_YEILD_BRK('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'ACT'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_MONTH_ACT_YEILD_BRK('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY'),'SUS'), "+//ADDED BY LALANKA ON 19-10-2009
						" "+m_schema_name+".FA_FACTORING_MONTH_UTILIZATION('"+m_facility_code+"',TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'DD-MM-YYYY')) "+
						" FROM DUAL " );
						}
						else{
						rs3=stmt2.executeQuery("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE(TO_DATE('"+m_rpt_st_date+"','MM-YYYY'),'DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),"+j+"),'MON-YYYY'),"+
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
		}		
		catch (SQLException sql) {}
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
