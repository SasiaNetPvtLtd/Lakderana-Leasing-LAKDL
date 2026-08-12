//DEVELOPED BY : SANDUN 
//DATE         : 11 NOV 2008
// OFSCL BALANCE CONFIRMATION LETTER

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Standing_Order_View extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
  java.text.NumberFormat nf;
	
  public ResultSet rs,rs1,rs2;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		  String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			
		 String m_chksql = req.getParameter("chksql");
		 stmt = conn.createStatement ();
		 stmt1 = conn.createStatement ();
		 stmt2 = conn.createStatement ();
		 			
				String m_from_date = req.getParameter("from_date");
  	      		String m_to_date = req.getParameter("to_date"); 
				String m_print_type = req.getParameter("print_type");
	
			
			out.println("<html><head>"); 
			out.println("<title>Standing Order View</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			   		
			out.println("<script>");			
									
			out.println("function save_data(){");		
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");			
		  out.println("}");
		
		  out.println("function add_button(){");					
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}");
			
			out.println("</script>");
						
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
				 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
		  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			
			  if(m_chksql.trim().equals("STANDING_ORDER_DISPLAY")) {

				if(m_print_type.equals("ALL")){
					m_print_type = "%";
				}
				
				
				rs = stmt.executeQuery ("SELECT REC_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REC_AMOUNT FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND    SETTLE_MODE='STD_ORD' AND STATUS <> 'CAD' AND ENT_USER LIKE '"+m_print_type+"' "+ 
				" ORDER BY  REC_NO ");
														
			    boolean	more = rs.next();
				double m_tot = 0.0;
						
				out.println("<table class=table border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' >Receipt No</td>");
				out.println("<td width='25%' >Client</td>");
				out.println("<td width='15%' >Effective Value Date</td>");
				out.println("<td width='15%' align=right>Receipt Amount</td>");
				out.println("</tr>");

				while(more){
				out.println("<tr class=tr_input1>");
				out.println("<td width='15%'>"+rs.getString(1)+"</td>");
				out.println("<td width='25%'>"+rs.getString(2)+"</td>");
				out.println("<td width='15%'>"+rs.getString(3)+"</td>");
				out.println("<td width='15%' align=right>"+nf.format(rs.getDouble(4))+"</td>");
				out.println("</tr>");
				m_tot = m_tot + rs.getDouble(4);
				more = rs.next();
				}
				out.println("<tr >");
				out.println("<td width='15%'></td>");
				out.println("<td width='25%'></td>");
				out.println("<td width='15%'><b>Total</b></td>");
				out.println("<td width='15%' align=right><b>"+nf.format(m_tot)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
			}
			
			  else if(m_chksql.trim().equals("CANCEL_STANDING_ORDER_DISPLAY")) {

				if(m_print_type.equals("ALL")){
					m_print_type = "%";
				}
				
				rs = stmt.executeQuery ("SELECT REC_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REC_AMOUNT FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND    SETTLE_MODE='STD_ORD' AND STATUS = 'CAD' AND ENT_USER LIKE '"+m_print_type+"' "+ 
				" ORDER BY  REC_NO ");
														
			    boolean	more = rs.next();
				double m_tot = 0.0;
						
				out.println("<table class=table border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' >Receipt No</td>");
				out.println("<td width='25%' >Client</td>");
				out.println("<td width='15%' >Effective Value Date</td>");
				out.println("<td width='15%' align=right>Receipt Amount</td>");
				out.println("</tr>");

				while(more){

				out.println("<tr class=tr_input1>");
				out.println("<td >"+rs.getString(1)+"</td>");
				out.println("<td >"+rs.getString(2)+"</td>");
				out.println("<td >"+rs.getString(3)+"</td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"</td>");
				out.println("</tr>");
				m_tot = m_tot + rs.getDouble(4);
				more = rs.next();
				}
				out.println("<tr >");
				out.println("<td width='15%'></td>");
				out.println("<td width='25%'></td>");
				out.println("<td width='15%'><b>Total</b></td>");
				out.println("<td width='15%' align=right><b>"+nf.format(m_tot)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");

			}
			
			
			
			
			 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");

			//}
			
		

			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(rs!=null){try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
