// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
    
       
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   
  
public class LAKDL_FA_CR_Application_approval extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
    
  public ResultSet rs,rs1;
	
	ServletOutputStream out = null;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			stmt1=conn.createStatement();
			stmt=conn.createStatement();

			String m_facility_no=req.getParameter("facility_no");
			
			out.println("<HTML><HEAD><TITLE>Factoring Facility Details - Facility No: "+m_facility_no+" </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B>Factoring Facility Details - Facility No: "+m_facility_no+" </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
			
			double m_min_fee_amount=0;
			double m_min_discount_amount=0;
			double m_repay_charge=0;
			double m_invoice_days=0;			
			double m_factor=0;
			
			rs1 = stmt.executeQuery (" SELECT NVL(APPLICABLE_VALUE,0) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
			" WHERE FEE_CODE='FEE0003.0' AND FACILITY_NO='"+m_facility_no+"' ");
			
			if(rs1.next()){
			m_min_fee_amount=rs1.getDouble(1);
			}
		
			rs1 = stmt.executeQuery ("	SELECT NVL(APPLICABLE_VALUE,0) "+
				"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
				" WHERE FEE_CODE='FEE0002.0' AND FACILITY_NO='"+m_facility_no+"' ");
			if(rs1.next()){
			m_min_discount_amount=rs1.getDouble(1);
			}
			
			rs= stmt1.executeQuery("SELECT FACILITY_NO, "+//1
			" CLIENT_CODE,  "+//2
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
			" FACILITY_MGR_CODE, "+//4
			" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'), "+//5
			" FA_PRODUCT_CODE, "+//6
			" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE), "+//7
			" FEE_PACK_CODE,  "+//8
			" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+//9
			" CREDIT_LIMIT, "+//10
			" CREDIT_PERIOD,"+//11
			" TOLERANCE_CREDIT_PERIOD, "+//12
			" RESERVE_MARGIN, "+//13
			" INT_RATE, "+//14
			" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+//15
			" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY'), "+//16
			" FACILITY_STATUS, "+//17
			" ENT_USER, "+//18
			" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//19
			" NVL(MOD_USER,'-'), "+//20
			" TO_CHAR(MOD_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//21
			" NVL(APP_USER,'-'), "+//22
			" TO_CHAR(APP_DATE,'DD-MM-YYYY HH24:MI:SS'), "+ //23
			" NVL(APPROVAL_COMMENTS,'-'), "+//24
			" DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated'), "+//25
			" NVL(ENTRY_COMMENTS,'-') "+//26
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+
			" WHERE FACILITY_NO='"+m_facility_no+"'");
					
			if(rs.next()){
			
				m_repay_charge=100-rs.getDouble(13);
				m_invoice_days=rs.getDouble(11)+rs.getDouble(12);
				
				m_factor=(((m_min_fee_amount/m_repay_charge)*100)*(365/m_invoice_days))+rs.getDouble(14);
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Facility No</b></td>");
				out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
				out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Facility Validity Period</b></td>");
				out.println("<td width='50%' class=div_input >"+rs.getString(15)+" - "+rs.getString(16)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Facility Manager</b></td>");
				out.println("<td width='50%' class=div_input >"+rs.getString(4)+" - "+rs.getString(5)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Credit Limit</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(rs.getDouble(10))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Credit Period</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(rs.getDouble(11))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Tolerance Credit Period</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(rs.getDouble(12))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Reserve Margin (%)</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(rs.getDouble(13))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Interest Rate (%)</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(rs.getDouble(14))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Comments </b></td>");
				out.println("<td width='50%' class=div_input >"+rs.getString(26)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Expected Yeild</b></td>");
				out.println("<td width='50%' class=div_input align='left'>"+nf.format(m_factor)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='100%' class=div_input><b>Product Package Details - "+rs.getString(6)+"  "+rs.getString(7)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");

				rs1= stmt.executeQuery(" SELECT A.PRODUCT_FEATURE_CODE,B.FA_FEATURE_DESC,NVL(A.PARAMETER_VALUE,'-') "+
							" FROM  "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD A, "+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
							" WHERE A.FACILITY_NO='"+m_facility_no+"' AND A.PRODUCT_FEATURE_CODE=B.FA_FEATURE_CODE");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Feature Code</b></td>");
				out.println("<td width='40%' class=div_input><b>Description</b></td>");
				out.println("<td width='20%' class=div_input><b>Param Value</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
				out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
				out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				}
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='100%' class=div_input><b>Fee Package Details - "+rs.getString(8)+"  "+rs.getString(9)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");

				rs1= stmt.executeQuery(" SELECT A.FEE_CODE,B.FEE_DESC,NVL(A.APPLICABLE_VALUE,0) "+
							" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
							" WHERE A.FEE_CODE=B.FEE_CODE AND A.FACILITY_NO='"+m_facility_no+"'");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input><b>Fee Code</b></td>");
				out.println("<td width='40%' class=div_input><b>Description</b></td>");
				out.println("<td width='10%' class=div_input><b>Fee Value</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
				out.println("<td width='40%' class=div_input>"+rs1.getString(2)+"</td>");
				out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("<br>");
				
				rs1= stmt.executeQuery("SELECT GURANT_NAME,NVL(GURANT_BANK,'-'),NVL(GURANT_CONT_PERSON,'-'),"+
					" NVL(TO_CHAR(GURANT_START_DATE,'DD-MM-YYYY'),'-'), NVL(TO_CHAR(GURANT_END_DATE,'DD-MM-YYYY'),'-'),"+
  		   	" NVL(GURANT_VALUE,0),NVL(GURANT_COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_GURNT "+
					" WHERE FACILITY_NO='"+m_facility_no+"'");
				
				boolean mflag=rs1.next();
				
				if(mflag){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='100%' class=div_input><b>Bank Guarantee/Personal Guarantee</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Guarantor Name</b></td>");
					out.println("<td width='10%' class=div_input><b>Bank</b></td>");
					out.println("<td width='20%' class=div_input><b>Contact Person</b></td>");
					out.println("<td width='20%' class=div_input><b>Valued Period</b></td>");
					out.println("<td width='10%' class=div_input><b>Value (Rs.)</b></td>");
					out.println("<td width='10%' class=div_input><b>Comments</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					while(rs1.next()){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(3)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs1.getString(4)+" to "+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input>"+nf.format(rs1.getDouble(6))+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(7)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						mflag=rs1.next();
					}
					out.println("</table>");
				}
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input align='center'><b>MANAGER - FACTORING </b></td>");
				out.println("<td width='5%' class=div_input></td>");
				out.println("<td width='20%' class=div_input align='center'><b>AGM - FACTORING</b></td>");
				out.println("<td width='5%' class=div_input></td>");
				out.println("<td width='20%' class=div_input align='center'><b>DIRECTOR/CEO</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</table>");
			}
			else{
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
				out.println("<TR><TD class=div_input><CENTER><B>Facility Details - Facility No: "+m_facility_no+" not found</B></TD></TR>");
				out.println("</TABLE>");
			}
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				try {
						conn.close();
				}catch (Exception eti) {}
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
