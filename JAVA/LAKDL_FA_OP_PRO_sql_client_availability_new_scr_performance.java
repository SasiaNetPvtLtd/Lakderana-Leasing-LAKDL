import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_client_availability_new_scr_performance extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
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

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			
			double m_val=0;
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_nav_status=req.getParameter("NAV_STATUS");
				String m_date=req.getParameter("date");
				String m_string="";				
				
				
				
				
				//--------------------------------------------------------------------------------------------------------------------------
				//out.println("<hr>");
				String m_month_0="";
				String m_month_1="";
				String m_month_2="";
				String m_month_3="";
				String m_month_4="";
				String m_month_5="";
				String m_month_6="";
				String m_month_7="";
				String m_month_8="";
				
			/*	rs1= stmt1.executeQuery("SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'MONTH-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-6),'MON-YYYY'),"+//7
					" TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY'),"+//8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-1),'DD-MM-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-2),'DD-MM-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-3),'DD-MM-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-4),'DD-MM-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-5),'DD-MM-YYYY'),"+//13
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-6),'DD-MM-YYYY'), "+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-7),'DD-MM-YYYY'), "+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-8),'DD-MM-YYYY') "+//16
					" FROM DUAL ");
					
				*/
				
				rs1= stmt1.executeQuery(
				//out.println(
				"SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'MONTH-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-6),'MON-YYYY'),"+//7
					" TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY') ,"+ //8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'DD-MM-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'DD-MM-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'DD-MM-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'DD-MM-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'DD-MM-YYYY'),"+//13
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-6),'DD-MM-YYYY'), "+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),6),'DD-MM-YYYY'), "+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-7),'DD-MM-YYYY'), "+//16
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-8),'DD-MM-YYYY') "+//17
					" FROM DUAL ");
					
				
				boolean more=rs1.next();
				out.println("<table width='100%'><tr align='right'><td><input class='but_input' type='button' value='<<' name='prv_btt' onClick=\"show_previous('"+rs1.getString(15)+"')\" >&nbsp;<input class='but_input' type='button' name='nxt_btt' value='>>' onClick=\"show_next('"+rs1.getString(14)+"')\"></td></tr></table>");
				
				if(more){
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				//out.println("<td width='20%' align='center'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input></DIV></td>");
				out.println("<td width='22%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(7)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				m_month_0=rs1.getString(8);
				m_month_1=rs1.getString(9);
				m_month_2=rs1.getString(10);
				m_month_3=rs1.getString(11);
				m_month_4=rs1.getString(12);
				m_month_5=rs1.getString(13);
				m_month_6=rs1.getString(14);
				m_month_7=rs1.getString(15);
				m_month_8=rs1.getString(16);
				}
				
				double m_bal_1=0;
				///---------------------------------------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table'>");
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Invoice Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT24')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='22%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				//-----------------------------------------------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Approved Invoice Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT23')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Pending Approval Invoices Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT2')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Disapproval Invoices Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT3')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Active Invoice Batches Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT1')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_EXCE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Due date exceeded Invoice Total</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT4')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				double m_bal_2=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_2=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Gross-Payable Amount</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT5')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
		
				double m_bal_3=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_3=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Payable Amount from due</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT6')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				/*
				out.println("<tr >");
				//out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");*/
				
				double m_net_pay_amount=(m_bal_2-m_bal_3);
				
				out.println("<tr >");
				//out.println("<td width='20%'  bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Net-Payable Amount</b></DIV></td>");
				if(m_net_pay_amount<0){
				//out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_net_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				//out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_net_pay_amount)+"</b></DIV></td>");
				}
				//out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("<td colspan='7'><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("<td width='*%'><DIV class=div_input>&nbsp;</DIV></td>");

				out.println("</tr>");
		
				double m_unsettle_cheque_return=0;
	
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_unsettle_cheque_return=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Unsettle Cheque Return</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT7')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Cheque Return History Detail</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}
				
				double m_min_pay_amount=m_net_pay_amount-m_unsettle_cheque_return;
				
				out.println("<tr >");
				//out.println("<td width='20%'  bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Minimum Invoice Payable Amount</b></DIV></td>");
				if(m_min_pay_amount<0){
				//out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_min_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				//out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_min_pay_amount)+"</b></DIV></td>");
				}
				//out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td colspan='7'><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("<td width='*%'><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("</tr>");
				
				
				//out.println("<table>");
				//-----------------------------current account---------------------------------------------------------	
				out.println("<br>");
				
				
				
				//out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td colspan=\"2\" width='20%' ><DIV class=div_input><b><u>Current A/C</u></b></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td colspan='5' align='right'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				//out.println("<table>");
				
				double m_bal_op_bal=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_7+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_op_bal=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Opening Balance</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT22')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}				
				
				rs2= stmt2.executeQuery("SELECT NVL(VAT_REG_NO,'-') FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
																" WHERE CLIENT_CODE='"+m_client_code+"'");
				
				double m_bal_charges=0;
				double m_bal_tax_charges=0;
				
				if(rs2.next()){
					if(!rs2.getString(1).equals("-")){
					
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT8')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");
							out.println("</tr>");
						}				
						
						m_bal_tax_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_tax_charges=rs1.getDouble(1);
							out.println("<tr >");
							//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total TAX Charges</b></DIV></td>");
							//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT9')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");
							out.println("</tr>");
						}				
					}
					else{
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')),"+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')+  "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"') "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");
							out.println("</tr>");
						}				
						m_bal_tax_charges=0;
					}
				}
				else{
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')),"+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')+  "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"') "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");
							out.println("</tr>");
						}				
						m_bal_tax_charges=0;
				}
				
				double m_bal_total_pending_payment=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_PAYMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_pending_payment=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Pending Payments</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT10')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}				
				
				double m_bal_total_payment=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PAYMENT_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_payment=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Payments</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT11')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}				
				
				double m_bal_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_COLLECT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_collections=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Collections</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT26')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_bal_active_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_active_collections=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Received from Active Invoices</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT13')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		

				double m_bal_inactive_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_inactive_collections=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Received from Inactive Invoices</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT14')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_unallocated_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_UNALLOCATED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_unallocated_funds=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Unallocated Funds</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT15')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_funds_transfer=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_funds_transfer=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Unallo Funds Transfer</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT30')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				m_unallocated_funds=m_unallocated_funds-m_funds_transfer;
				
				
				//---------------------ADDED BY ASHINI ON 22.02.2008------------------------------------------------------------------------------
				
				double m_returnable_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");

				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Returnable Funds</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT31')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		


				double m_refunded_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");

				while(rs1.next()){
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Refunded Funds</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT32')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}

				
				//---------END OF MODIFICATION DONE BY ASHINI ON 22.02.2008-----------------------------------------------------------------------
				
								
				
				double m_client_adjustments_funds=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_client_adjustments_funds=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Client Adjustments</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT21')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_colse_bal=(m_bal_total_pending_payment+m_bal_op_bal+m_bal_charges+m_bal_tax_charges+m_bal_total_payment+m_client_adjustments_funds)-(m_bal_collections);
				
				//m_min_pay_amount
				out.println("<tr >");
				//out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				//out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Closing Balance</b></DIV></td>");
				if(m_colse_bal<0){
				//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_colse_bal*-1)+")</b></DIV></td>");
				}
				else{
				//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_colse_bal)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("<td width='*%'><DIV class=div_input>&nbsp;</DIV></td>");
				out.println("</tr>");
				
				double m_bal_interest=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_interest=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Interest Normal</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT12')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_bal_overinterest=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_OVER_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_overinterest=rs1.getDouble(1);
					out.println("<tr >");
					//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Interest Overpaid</b></DIV></td>");
					//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT20')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
				}		
				
				double m_bal_bank_cheque=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_BANK_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_bank_cheque=rs1.getDouble(1);
				}
				out.println("<tr >");
				//out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Cheques Banked pending realisation</b></DIV></td>");
				if(m_bal_bank_cheque<0){
				//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_bank_cheque*-1)+")</b></DIV></td>");
				}
				else{
				//out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_bank_cheque)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				//out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				//out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Net Exposure</b></DIV></td>");
				
				double m_exposure=((m_colse_bal+m_bal_overinterest+m_bal_interest)-(m_bal_bank_cheque));
				
				if(m_exposure<0){
				//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_exposure*-1)+")</b></DIV></td>");
				}
				else{
				//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_exposure)+"</b></DIV></td>");
				}
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				/*
				
				double m_minim_pay_amount=(m_net_pay_amount-m_colse_bal);
				
				
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				if(m_minim_pay_amount<0){
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Overpaied Amount</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_minim_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Payable Amount</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_minim_pay_amount)+"</b></DIV></td>");
				}
				
				double m_max_pay_amount=(m_min_pay_amount-(m_colse_bal+m_bal_overinterest+m_bal_interest)+m_bal_bank_cheque);
				
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Maximum Payable Amount</b></DIV></td>");
				if((m_max_pay_amount)<0){
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format((m_max_pay_amount)*-1)+")</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>0.00</b></DIV></td>");
				}
				else{
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_max_pay_amount)+"</b></DIV></td>");
				}
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='20%'><DIV class=div_input><b><u>Additional Information</u></b></DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_POD_HAND('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_pod_in_hand=0;
				
				while(rs1.next()){
					m_pod_in_hand=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total unbanked POD Cheques in hand</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT17')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_pod_in_hand)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				//--------------------------------------------------added by ashini on 12-02-2008-------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_POD_CANCEL('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				double m_pod_cancelled=0;
				
				while(rs1.next()){
				m_pod_cancelled=rs1.getDouble(1);
				}
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total Cancelled POD Cheques</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill_2('"+m_client_code+"','"+m_facility_code+"','OPT29')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_pod_cancelled)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				//-----------------------------end modification done by ashini on 12-02-2008-------------------------				
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_IN7DAYS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_invoices_due_7days=0;
				
				while(rs1.next()){
					m_invoices_due_7days=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total Invoices falling due within 7 days</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT18')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_invoices_due_7days)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				//---------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ON_ACC_CLIENT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_on_account_of_client=0;
				
				while(rs1.next()){
					m_on_account_of_client=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Collections on Acc. of Client</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT27')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_on_account_of_client)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				//---------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ON_ACC_DEBTOR('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_on_account_of_debtor=0;
				
				while(rs1.next()){
					m_on_account_of_debtor=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Collections on Acc. of Debtor</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT28')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_on_account_of_debtor)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");

				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				//----------------------------------------------------------------------------------------------------------------------
				//final credit process
				double m_client_credit=0;
				
				rs1= stmt1.executeQuery(" SELECT  NVL(CREDIT_LIMIT,0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+
						" WHERE  "+
						" FACILITY_NO='"+m_facility_code+"' "+
						" AND CLIENT_CODE='"+m_client_code+"' ");
				
				if(rs1.next()){
				m_client_credit=rs1.getDouble(1);
				}
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Credit Limit</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_client_final_bal=m_colse_bal+(m_bal_overinterest+m_bal_interest);
				
				if(m_client_final_bal<0){
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				}
				else{
					if(m_client_final_bal>m_client_credit){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FF0033\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FF0033\" align='right'><DIV class=div_input><b>("+nf.format((m_client_credit-m_client_final_bal)*-1)+")</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit-m_client_final_bal)+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
				}
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				//------------------------------------------------------------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='50%' class='div_input'><b>Invoice Batches</b></td>");
				out.println("<td width='50%' class='div_input'><b>Inactive Invoice Batches</b></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table'  border='1' >");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT<=2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				double m_net_amount=0;
				double m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){   
				out.println("<tr >");
				out.println("<td width='35%' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table' border='1'>");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT>2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				m_net_amount=0;
				m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				*/
			} 
			//------------Added by Dineth on 2008-11-04
			else if(m_chksql.equals("LOAD_TOTAL")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_date=req.getParameter("date");
				String m_string="";				
				
				
				
				
				//--------------------------------------------------------------------------------------------------------------------------
				//out.println("<hr>");
				String m_month_0="";
				String m_month_1="";
				String m_month_2="";
				String m_month_3="";
				String m_month_4="";
				String m_month_5="";
				String m_month_6="";
				String m_month_7="";
				String m_month_8="";
				
			/*	rs1= stmt1.executeQuery("SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'MONTH-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-6),'MON-YYYY'),"+//7
					" TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY'),"+//8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-1),'DD-MM-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-2),'DD-MM-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-3),'DD-MM-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-4),'DD-MM-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-5),'DD-MM-YYYY'),"+//13
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-6),'DD-MM-YYYY'), "+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-7),'DD-MM-YYYY'), "+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-8),'DD-MM-YYYY') "+//16
					" FROM DUAL ");
					
				*/
				
				rs1= stmt1.executeQuery(
				//out.println(
				"SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'MONTH-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-6),'MON-YYYY'),"+//7
					" TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY') ,"+ //8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-1),'DD-MM-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-2),'DD-MM-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-3),'DD-MM-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-4),'DD-MM-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-5),'DD-MM-YYYY'),"+//13
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-6),'DD-MM-YYYY'), "+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-7),'DD-MM-YYYY'), "+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),-8),'DD-MM-YYYY') "+//16
					" FROM DUAL ");
					
				
				boolean more=rs1.next();
				out.println("<table width='100%'><tr align='right'><td>&nbsp;</td></tr></table>");
				
				if(more){
				//Added by Dineth
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td colspan='2'>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
				//End by Dineth
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='20%' align='center'><DIV class=div_input></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input></DIV></td>");
				/*out.println("<td width='22%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("<td width='13%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(7)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				
				out.println("</tr>");
				out.println("</table>");
				m_month_0=rs1.getString(8);
				m_month_1=rs1.getString(9);
				m_month_2=rs1.getString(10);
				m_month_3=rs1.getString(11);
				m_month_4=rs1.getString(12);
				m_month_5=rs1.getString(13);
				m_month_6=rs1.getString(14);
				m_month_7=rs1.getString(15);
				m_month_8=rs1.getString(16);
				}
				
				double m_bal_1=0;
				///---------------------------------------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table'>");
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Invoice Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT24')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='22%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='13%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				
				//-----------------------------------------------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Approved Invoice Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT23')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Pending Approval Invoices Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT2')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Disapproval Invoices Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT3')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>Active Invoice Batches Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT1')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT1','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_EXCE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_EXCE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_DUE_EXC_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Due date exceeded Invoice Total</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT4')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT4','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				
				double m_bal_2=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_2=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Gross-Payable Amount</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT5')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT5','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
		
				double m_bal_3=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_3=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Payable Amount from due</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT6')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT6','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				
				/*out.println("<tr >");
				//out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");*/
				
				double m_net_pay_amount=(m_bal_2-m_bal_3);
				
				out.println("<tr >");
				out.println("<td width='20%'  bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Net-Payable Amount</b></DIV></td>");
				if(m_net_pay_amount<0){
				out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_net_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_net_pay_amount)+"</b></DIV></td>");
				}
				//out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
		
				double m_unsettle_cheque_return=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_unsettle_cheque_return=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Unsettle Cheque Return</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT7')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				//rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Cheque Return History Detail</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}
				
				double m_min_pay_amount=m_net_pay_amount-m_unsettle_cheque_return;
				
				out.println("<tr >");
				out.println("<td width='20%'  bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Minimum Invoice Payable Amount</b></DIV></td>");
				if(m_min_pay_amount<0){
				out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_min_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='10%'  bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_min_pay_amount)+"</b></DIV></td>");
				}
				//out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				
				//out.println("<table>");
				//-----------------------------current account---------------------------------------------------------	
				out.println("<br>");
				
				
				
				//out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='20%' ><DIV class=div_input><b><u>Current A/C</u></b></DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				//out.println("<table>");
				
				double m_bal_op_bal=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_7+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_op_bal=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Opening Balance</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT22')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT22','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}				
				
				rs2= stmt2.executeQuery("SELECT NVL(VAT_REG_NO,'-') FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
																" WHERE CLIENT_CODE='"+m_client_code+"'");
				
				double m_bal_charges=0;
				double m_bal_tax_charges=0;
				
				if(rs2.next()){
					if(!rs2.getString(1).equals("-")){
					
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT8')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT8','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
							out.println("</tr>");
						}				
						
						m_bal_tax_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_tax_charges=rs1.getDouble(1);
							out.println("<tr >");
							out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total TAX Charges</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT9')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT9','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
							out.println("</tr>");
						}				
					}
					else{
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')),"+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')+  "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"') "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
							out.println("</tr>");
						}				
						m_bal_tax_charges=0;
					}
				}
				else{
						m_bal_charges=0;
						
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')),"+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"')+ "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
						" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')+  "+
						" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"') "+
						" FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
							out.println("<tr >");
							out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
							/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
							out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT19','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
							out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
							out.println("</tr>");
						}				
						m_bal_tax_charges=0;
				}
				
				double m_bal_total_pending_payment=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_PAYMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAY_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_pending_payment=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Pending Payments</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT10')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}				
				
				double m_bal_total_payment=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PAYMENT_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_payment=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Total Payments</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT11')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT11','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}				
				
				double m_bal_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_COLLECT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_collections=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Less Collections</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT26')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_bal_active_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_active_collections=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Received from Active Invoices</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT13')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT13','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		

				double m_bal_inactive_collections=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_inactive_collections=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Received from Inactive Invoices</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT14')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT14','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_unallocated_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_UNALLOCATED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_unallocated_funds=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Unallocated Funds</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT15')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT15','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_funds_transfer=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_UNALLOFUND_TRAN_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_funds_transfer=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Unallo Funds Transfer</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT30')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT30','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				m_unallocated_funds=m_unallocated_funds-m_funds_transfer;
				
				
				//---------------------ADDED BY ASHINI ON 22.02.2008------------------------------------------------------------------------------
				
				double m_returnable_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_RETURNED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");

				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Returnable Funds</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT31')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT31','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		


				double m_refunded_funds=0;

				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_REFUNDED_FUNDS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");

				while(rs1.next()){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Refunded Funds</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill_more('"+m_client_code+"','"+m_facility_code+"','OPT32')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_2+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_3+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_4+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_5+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other_more('"+m_client_code+"','"+m_facility_code+"','OPT32','"+m_month_6+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}

				
				//---------END OF MODIFICATION DONE BY ASHINI ON 22.02.2008-----------------------------------------------------------------------
				
								
				
				double m_client_adjustments_funds=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_client_adjustments_funds=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Client Adjustments</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT21')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_0+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_1+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT21','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_colse_bal=(m_bal_total_pending_payment+m_bal_op_bal+m_bal_charges+m_bal_tax_charges+m_bal_total_payment+m_client_adjustments_funds)-(m_bal_collections);
				
				//m_min_pay_amount
				out.println("<tr >");
				out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Closing Balance</b></DIV></td>");
				if(m_colse_bal<0){
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_colse_bal*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_colse_bal)+"</b></DIV></td>");
				}
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				
				double m_bal_interest=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_interest=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Interest Normal</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT12')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT12','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_bal_overinterest=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_OVER_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_0+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_1+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_2+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_3+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_4+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_5+"'), "+
				" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month_6+"')  "+
				" FROM DUAL");
				
				while(rs1.next()){
					m_bal_overinterest=rs1.getDouble(1);
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Add Interest Overpaid</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT20')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs1.getDouble(1))+"</b></DIV></td>");
					/*out.println("<td width='10%' bgcolor=\"#CC66FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_0+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(2))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CC99FF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_1+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(3))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_2+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(4))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFCCFF\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_3+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(5))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FFFF99\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_4+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(6))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCFF66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_5+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(7))+"</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#CCCC66\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT20','"+m_month_6+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs1.getDouble(8))+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
					out.println("</tr>");
				}		
				
				double m_bal_bank_cheque=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_BANK_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_bank_cheque=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Cheques Banked pending realisation</b></DIV></td>");
				if(m_bal_bank_cheque<0){
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_bank_cheque*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_bank_cheque)+"</b></DIV></td>");
				}
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				
				out.println("<tr >");
				//out.println("<td width='20%'><DIV class=div_input></DIV></td>");
				//out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Net Exposure</b></DIV></td>");
				
				double m_exposure=((m_colse_bal+m_bal_overinterest+m_bal_interest)-(m_bal_bank_cheque));
				
				if(m_exposure<0){
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_exposure*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_exposure)+"</b></DIV></td>");
				}
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				
				
				double m_minim_pay_amount=(m_net_pay_amount-m_colse_bal);
				
				
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				if(m_minim_pay_amount<0){
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Overpaied Amount</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format(m_minim_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Payable Amount</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_minim_pay_amount)+"</b></DIV></td>");
				}
				
				double m_max_pay_amount=(m_min_pay_amount-(m_colse_bal+m_bal_overinterest+m_bal_interest)+m_bal_bank_cheque);
				
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Maximum Payable Amount</b></DIV></td>");
				if((m_max_pay_amount)<0){
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>("+nf.format((m_max_pay_amount)*-1)+")</b></DIV></td>");
				//out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>0.00</b></DIV></td>");//Commented by Dineth on 2008-11-05
				}
				else{
				out.println("<td width='10%' bgcolor=\"#CCCCCC\" align='right'><DIV class=div_input><b>"+nf.format(m_max_pay_amount)+"</b></DIV></td>");
				}
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='20%'><DIV class=div_input><b><u>Additional Information</u></b></DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_POD_HAND('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_pod_in_hand=0;
				
				while(rs1.next()){
					m_pod_in_hand=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total unbanked POD Cheques in hand</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT17')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_pod_in_hand)+"</b></DIV></td>");
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				
				//--------------------------------------------------added by ashini on 12-02-2008-------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_POD_CANCEL('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				double m_pod_cancelled=0;
				
				while(rs1.next()){
				m_pod_cancelled=rs1.getDouble(1);
				}
				
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total Cancelled POD Cheques</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill_2('"+m_client_code+"','"+m_facility_code+"','OPT29')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_pod_cancelled)+"</b></DIV></td>");
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				
				//-----------------------------end modification done by ashini on 12-02-2008-------------------------				
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_IN7DAYS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_invoices_due_7days=0;
				
				while(rs1.next()){
					m_invoices_due_7days=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Total Invoices falling due within 7 days</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT18')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_invoices_due_7days)+"</b></DIV></td>");
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				
				//---------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ON_ACC_CLIENT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_on_account_of_client=0;
				
				while(rs1.next()){
					m_on_account_of_client=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Collections on Acc. of Client</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT27')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_on_account_of_client)+"</b></DIV></td>");
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");
				//---------------------------------------
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ON_ACC_DEBTOR('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_on_account_of_debtor=0;
				
				while(rs1.next()){
					m_on_account_of_debtor=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FFFF00\" ><DIV class=div_input><b>Collections on Acc. of Debtor</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\" align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT28')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_on_account_of_debtor)+"</b></DIV></td>");
				/*out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");*/
				out.println("</tr>");

				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				//----------------------------------------------------------------------------------------------------------------------
				//final credit process
				double m_client_credit=0;
				
				rs1= stmt1.executeQuery(" SELECT  NVL(CREDIT_LIMIT,0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+
						" WHERE  "+
						" FACILITY_NO='"+m_facility_code+"' "+
						" AND CLIENT_CODE='"+m_client_code+"' ");
				
				if(rs1.next()){
				m_client_credit=rs1.getDouble(1);
				}
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Credit Limit</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_client_final_bal=m_colse_bal+(m_bal_overinterest+m_bal_interest);
				
				if(m_client_final_bal<0){
				out.println("<tr >");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				}
				else{
					if(m_client_final_bal>m_client_credit){
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FF0033\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FF0033\" align='right'><DIV class=div_input><b>("+nf.format((m_client_credit-m_client_final_bal)*-1)+")</b></DIV></td>");
					//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr >");
					out.println("<td width='20%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='10%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit-m_client_final_bal)+"</b></DIV></td>");
					//out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
				}
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				//------------------------------------------------------------------------------------------------------------------------
				/*out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='50%' class='div_input'><b>Invoice Batches</b></td>");
				out.println("<td width='50%' class='div_input'><b>Inactive Invoice Batches</b></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table'  border='1' >");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT<=2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				double m_net_amount=0;
				double m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){   
				out.println("<tr >");
				out.println("<td width='35%' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table' border='1'>");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT>2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				m_net_amount=0;
				m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
			*/	
			}
			
			
			
			
			//------------End by Dineth on 2008-11-04
			//------------Add by Dineth on 2008-11-05
			else if(m_chksql.equals("LOAD_BATCHES")){
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_date=req.getParameter("date");
				String m_string="";				
				
			
			
			
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='50%' class='div_input'><b>Invoice Batches</b></td>");
				out.println("<td width='50%' class='div_input'><b>Inactive Invoice Batches</b></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table'  border='1' >");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT<=2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				double m_net_amount=0;
				double m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){   
				out.println("<tr >");
				out.println("<td width='35%' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table' border='1'>");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT>2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				m_net_amount=0;
				m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
			}
			//------------End by Dineth on 2008-11-05
			else {
			    out.println("Undefined");
			}
			
			stmt1.close();
			stmt2.close();
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

