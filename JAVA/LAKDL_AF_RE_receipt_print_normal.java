import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//ADDED BY KANISHKA DILSHAN ON  19-07-2013


public class LAKDL_AF_RE_receipt_print_normal extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			
			if (m_chksql.trim().equals("get_receipt_details")) {
				
				
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date"); 
				String m_user = req.getParameter("user");
				
				
				
				if(m_user.equals("ALL")){
					m_user = "%";
				}
				
				//	rs = stmt.executeQuery
				rs = stmt.executeQuery ("SELECT REC_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REC_AMOUNT,CLIENT_CODE,NVL(SUB_REC_NO,REC_NO) FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ // mod by udara on 28-08-2013
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND    STATUS NOT IN ('C','CAD','RET') /*AND  PRINT_STATUS IS NULL */ AND ENT_USER LIKE '"+m_user+"' "+ 
					//" AND    PAYEE_TYPE <> 'THIRD'    "+ // added by udara 26-03-2014 commented on 2017-09-1 ##23772
					" ORDER BY  NVL(SUB_REC_NO,REC_NO)  ");  // mod by udara on 28-08-2013
				
				boolean	more = rs.next();
				double m_tot = 0.0;
				
				out.println("<table class=table border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' >Receipt No</td>");
				out.println("<td width='25%' >Client</td>");
				out.println("<td width='15%' >Effective Value Date</td>");
				out.println("<td width='15%' align=right>Receipt Amount</td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				
				while(more){
					out.println("<tr class=tr_input1>");
					out.println("<td width='15%'>"+rs.getString(6)+"</td>"); // mod by udara on 28-08-2013  // out.println("<td width='15%'>"+rs.getString(1)+"</td>"); 
					out.println("<td width='25%'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' align=right>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("<td width='10%' class='rep-body1'  align='center'><input class='but_input' type='button' name='BUT_PRINT' value=\" Print \" onClick=\"print_receipt('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></td>");
					
					out.println("</tr>");
					m_tot = m_tot + rs.getDouble(4);
					more = rs.next();
				}
				out.println("<tr >");
				out.println("<td width='15%'></td>");
				out.println("<td width='25%'></td>");
				out.println("<td width='15%'><b>Total</b></td>");
				out.println("<td width='15%' align=right><b>"+nf.format(m_tot)+"</b></td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
			}
			
			
			// added by udara 26-03-2014
			
			
			if (m_chksql.trim().equals("get_receipt_details_third")) {
				
				
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date"); 
				String m_user = req.getParameter("user");
				
				
				
				if(m_user.equals("ALL")){
					m_user = "%";
				}
				
				//	rs = stmt.executeQuery
				rs = stmt.executeQuery ("SELECT REC_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REC_AMOUNT,CLIENT_CODE,NVL(SUB_REC_NO,REC_NO) FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ // mod by udara on 28-08-2013
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND    STATUS NOT IN ('C','CAD','RET') /*AND  PRINT_STATUS IS NULL */ AND ENT_USER LIKE '"+m_user+"' "+ 
					" AND    PAYEE_TYPE = 'THIRD'    "+ // added by udara 26-03-2014
					" ORDER BY  NVL(SUB_REC_NO,REC_NO)  ");  // mod by udara on 28-08-2013
				
				boolean	more = rs.next();
				double m_tot = 0.0;
				
				out.println("<table class=table border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' >Receipt No</td>");
				out.println("<td width='25%' >Client</td>");
				out.println("<td width='15%' >Effective Value Date</td>");
				out.println("<td width='15%' align=right>Receipt Amount</td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				
				while(more){
					out.println("<tr class=tr_input1>");
					out.println("<td width='15%'>"+rs.getString(6)+"</td>"); // mod by udara on 28-08-2013  // out.println("<td width='15%'>"+rs.getString(1)+"</td>"); 
					out.println("<td width='25%'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' align=right>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("<td width='10%' class='rep-body1'  align='center'><input class='but_input' type='button' name='BUT_PRINT' value=\" Print \" onClick=\"print_receipt('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></td>");
					
					out.println("</tr>");
					m_tot = m_tot + rs.getDouble(4);
					more = rs.next();
				}
				out.println("<tr >");
				out.println("<td width='15%'></td>");
				out.println("<td width='25%'></td>");
				out.println("<td width='15%'><b>Total</b></td>");
				out.println("<td width='15%' align=right><b>"+nf.format(m_tot)+"</b></td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
			}
			
			
			// end by udara 26-03-2014
			
			else if (m_chksql.trim().equals("get_receipt_details_reprint")) {
				
				
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date"); 
				String m_user = req.getParameter("user");
				
				
				
				if(m_user.equals("ALL")){
					m_user = "%";
				}
				
				//	rs = stmt.executeQuery
				rs = stmt.executeQuery ("SELECT REC_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REC_AMOUNT,CLIENT_CODE,NVL(SUB_REC_NO,REC_NO) FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ // mod by udara on 28-08-2013
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND    STATUS NOT IN ('C','CAD','RET') AND ENT_USER LIKE '"+m_user+"' "+ 
					" ORDER BY NVL(SUB_REC_NO,REC_NO)   ");  // mod by udara on 28-08-2013
				
				boolean	more = rs.next();
				double m_tot = 0.0;
				
				out.println("<table class=table border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' >Receipt No</td>");
				out.println("<td width='25%' >Client</td>");
				out.println("<td width='15%' >Effective Value Date</td>");
				out.println("<td width='15%' align=right>Receipt Amount</td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				
				while(more){
					out.println("<tr class=tr_input1>");
					out.println("<td width='15%'>"+rs.getString(6)+"</td>"); // mod by udara on 28-08-2013 // out.println("<td width='15%'>"+rs.getString(1)+"</td>");
					out.println("<td width='25%'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' align=right>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("<td width='10%' class='rep-body1'  align='center'><input class='but_input' type='button' name='BUT_PRINT' value=\" Print \" onClick=\"print_receipt('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></td>");
					
					out.println("</tr>");
					m_tot = m_tot + rs.getDouble(4);
					more = rs.next();
				}
				out.println("<tr >");
				out.println("<td width='15%'></td>");
				out.println("<td width='25%'></td>");
				out.println("<td width='15%'><b>Total</b></td>");
				out.println("<td width='15%' align=right><b>"+nf.format(m_tot)+"</b></td>");
				out.println("<td width='10%' align=right>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
			}
			
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
			if(out!=null){
				try{out.close();  
				}catch(Exception e){}
			}
		}
	}
}
