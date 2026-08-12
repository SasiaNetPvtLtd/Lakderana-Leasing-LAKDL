import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_Debtor_Performence_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt5;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6;
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
			stmt=conn.createStatement();
			stmt5=conn.createStatement();
			
			
			double m_val=0;
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_from_date=req.getParameter("m_from_date");		
				
				//--------------------------------------------------------------------------------------------------------------------------
				String m_month_0_n="";
				String m_month_1_n="";
				String m_month_2_n="";
				String m_month_3_n="";
				String m_month_4_n="";
				String m_month_5_n="";
				String m_month_6_n="";
				
				String m_month_0="";
				String m_month_1="";
				String m_month_2="";
				String m_month_3="";
				String m_month_4="";
				String m_month_5="";
				String m_month_6="";
				String m_month_7="";
				String m_month_8="";
				
				String m_month="";
				String m_month_name="";
				
				String m_curr_month="";
				String m_curr_month_name="";


				int count = 0 ;
				
				rs5= stmt5.executeQuery("SELECT NVL(VAT_REG_NO,'-') FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
																" WHERE CLIENT_CODE='"+m_client_code+"'");		
																
																
																
				rs=stmt1.executeQuery("SELECT ABS(ROUND(MONTHS_BETWEEN(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),SYSDATE))) FROM DUAL" );
				boolean more= rs.next();
				if(more){
					 count = rs.getInt(1);

				 rs1=stmt1.executeQuery(" SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'MON-YYYY'),TO_CHAR(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY')  FROM DUAL" );
				 boolean more1= rs1.next();
					         m_curr_month_name=rs1.getString(1);
									 m_curr_month = rs1.getString(2);
		
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr><td width='15%' align='left'><DIV class=div_input></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Invoice Total</b></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Approved Invoice Total</b></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Pending Approval Invoices Total</b></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Disapproval Invoices Total</b></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Unsettle Cheque Return</b></DIV></td>"+
				"<td width='10%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Cheque Return History Detail</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Collections</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#CCCCCC\" ><DIV class=div_input><b>Total unbanked POD Cheques in hand</b></DIV></td></tr>");

				
				
				rs6= stmt1.executeQuery("SELECT "+m_schema_name+".FA_DEBTOR_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"'),"+
				" "+m_schema_name+".FA_DEBTOR_AV_POD_HAND('"+m_client_code+"','"+m_facility_code+"','"+m_curr_month+"')"+
				" FROM DUAL");		
				
				  boolean more6= rs6.next();
					
				
					
				if(more6){
				out.println("<tr><td width='15%' align='left' bgcolor=\"#C0C0C0\"><DIV class=div_input><b>"+m_curr_month_name+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_curr_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(1))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_curr_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(2))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_curr_month+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs6.getDouble(3))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_curr_month+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs6.getDouble(4))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_curr_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(5))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_curr_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(6))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_curr_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(7))+"</b></DIV></td> "+
        "<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT17')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs6.getDouble(8))+"</b></DIV></td>"+
				"</tr>");
	      }				
				
					int x=1;
				
				
					for(int j=1;j<=count;j++){
				  rs3=stmt1.executeQuery("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),-"+j+"),'MON-YYYY'),TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(SYSDATE,'MM-YYYY'),'DD-MM-YYYY'),- "+j+"),'DD-MM-YYYY') FROM DUAL" );
				  boolean more3= rs3.next();
					
					if(x==0){
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							x=1;
						}
						else{
							out.println("<tr bgcolor=\"#FFFFFF\">");
							x=0;
						}
					
					
				  if(more3){
					
                   m_month_name=rs3.getString(1);
									 m_month = rs3.getString(2);
										
										
				rs4= stmt1.executeQuery("SELECT "+m_schema_name+".FA_DEBTOR_AV_ALL_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_APPROVE_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_PENDING_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_DISAPPR_INV_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_RTN_CHEQUE_TOT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_RTN_CHEQUE_HIS_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'), "+
				" "+m_schema_name+".FA_DEBTOR_AV_COLLECT_M('"+m_client_code+"','"+m_facility_code+"','"+m_month+"'),"+
				" "+m_schema_name+".FA_DEBTOR_AV_POD_HAND('"+m_client_code+"','"+m_facility_code+"','"+m_month+"')"+
				" FROM DUAL");		

				
				 boolean more4= rs4.next();
					
				
					
				if(more4){
				out.println("<tr><td width='15%' align='left' bgcolor=\"#C0C0C0\"><DIV class=div_input><b>"+m_month_name+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT24','"+m_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(1))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT23','"+m_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(2))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT2','"+m_month+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs4.getDouble(3))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT3','"+m_month+"')\" style='cursor:hand' ><DIV class=div_input><b>"+nf.format(rs4.getDouble(4))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT7','"+m_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(5))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT25','"+m_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(6))+"</b></DIV></td> "+
				"<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT26','"+m_month+"')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(7))+"</b></DIV></td> "+
        "<td width='10%' bgcolor=\"#C0C0C0\" align='right' onClick=\"load_availability_drill_other('"+m_client_code+"','"+m_facility_code+"','OPT17')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(rs4.getDouble(8))+"</b></DIV></td>"+
				"</tr>");
	      }				

        }	
			  }
				
				}
        out.println("</table>");
       


			} 
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

