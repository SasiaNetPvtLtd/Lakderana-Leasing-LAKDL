
//DEVELOPED BY ASHINI FOR FACTORING ON 03-03-2008

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

   

public class LAKDL_FA_invoice_reassignment_letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt2,stmt3;
	java.text.NumberFormat nf;
	
   public ResultSet rs;
   public ResultSet rs1,rs2,rs3;
	public String m_chksql,m_html_client_url,reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
				
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			HttpSession session = req.getSession(true);//ADDED 2011-04-20 madhawa
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
		
			nf = java.text.NumberFormat.getInstance(Locale.US);
		   nf.setMinimumFractionDigits(2);
		   nf.setMaximumFractionDigits(2);
					
			String m_chksql=req.getParameter("chksql");
			
				
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
					
			if (m_chksql.trim().equals("main_page")) {
				
			String m_client_no=req.getParameter("client");		
			String m_facility_no=req.getParameter("finance_no");
			String m_ref_no=req.getParameter("ref_no");
			String m_date="";
		
			if (req.getParameter("date")!=null){
			m_date=req.getParameter("date");
			}
			
			
			Vector adj_num= (Vector)session.getValue("key_adj");
			String facility_code=(String)session.getValue("key_facility");
			String client_code=(String)session.getValue("key_client");
			if(adj_num!=null)
			{
				Iterator itr;
				/*
				Iterator itr=adj_num.iterator();
			
				while(itr.hasNext())
				{
					out.println("test1"+itr.next());
				}	
			
				*/
                String m_debtor_code ="";
			
				out.println("<html><head>"); 
				out.println("<title>Invoice Reassignment Letter</title>");
				out.println("</head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");				
				
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("  function print_data(){");
				out.println(" 		m_table.innerHTML    = \"\"; ");
				out.println(" 		window.print();");
				out.println("  }");
				out.println("</script>");
				out.println("<body leftmargin='0' topmargin='0' class=body>");	
				out.println("<body bgcolor='white'><br>");				
				out.println("<form name='Form1'>");	
				
				// Get Sysdate
				
				String m_Letter_date="";
				rs1 = stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");											
				if(rs1.next())
				{
					m_Letter_date=rs1.getString(1);
				}
				
			
				itr=null;
				itr=adj_num.iterator();
				//here single facility considered for the reassignments
				//
				

			
				
  		 
			
				String m_client_name="";
				rs2 = stmt2.executeQuery (" SELECT "+
				" CLIENT_CODE, "+
				" NVL(FULL_NAME,'-') "+
				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE CLIENT_CODE='"+client_code+"' ");
				
				if(rs2.next())
				{
					m_client_name = rs2.getString(2);
				}

				String m_cont_person="";
				String m_client_add1="";
				String m_client_add2="";
				String m_client_add3="";
				String m_ref_type_2 ="";
				
				rs1 = stmt1.executeQuery (	" SELECT "+
					  " NVL(A.CLIENT_CODE,'-'), "+      //1
					  " NVL(UPPER(A.FULL_NAME),' '), "+ //2
					  " NVL(UPPER(A.REGISTERED_ADDRESS1),' '), "+ //3
					  " NVL(UPPER(A.REGISTERED_ADDRESS2),' '), "+ //4
					  " UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ')), "+ //5
					  " NVL(UPPER(B.CONTACT_PERSON),' '),"+ //6
					  " NVL(UPPER(B.DESIGNATION_PAYMENT),' '), "+ //7
					  " A.CLIENT_TYPE, "+ //8
					  " NVL(A.REGISTERED_REFERENCE,'-') "+ // 9
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
					  " WHERE B.CLIENT_CODE=A.CLIENT_CODE "+
					  " AND B.FACILITY_NO='"+facility_code+"' "+
					  " AND A.CLIENT_CODE='"+client_code+"' "+
					  " ");
				
				while(rs1.next())
				{

					m_cont_person = rs1.getString(6);
					m_client_add1 = rs1.getString(3);
					m_client_add2 = rs1.getString(4);
					m_client_add3 = rs1.getString(5);
					m_ref_type_2  = rs1.getString(9);
					
				}
				
				
				out.println("");	
				out.println("<table border='0' width='100%' class='table'>"); 		
	            out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'><div id= 'm_table'> <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></div> </td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</table>");	

				out.println("<p style='text-align:left'>");										
				out.println("<br>");
				out.println("<table border='0' width='100%' class='table'>"); 		
	            out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'>"+m_ref_no+"</td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</table>");
				out.println("<br>");
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_cont_person+"</td></tr>"); // Added by Udara SOmathilake on 18-10-2010
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_name+"</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add1+"</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add2+"</td></tr>");
		        out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_client_add3+"</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' height=20px > </td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body' >facility:-"+facility_code+"</td></tr>"); // Facility No Added by Udara Somathilake on 13-10-2010
				out.println("<tr><td width='*%' class='factoring-letter-body' >client code:-"+client_code+"</td></tr>"); // Client Code
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Date      :- "+m_Letter_date+"</td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</p>");
				
				out.println("<p style='text-align:justify' class='factoring-letter-body' >");				
		
				out.println("</p>");
				
				out.println("<p style='text-align:justify' class='factoring-letter-body' >");				
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Batch No</u></b></td>");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice - No</u></b></td> ");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice Date</u></b></td> ");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Invoice Amount</u></b></td> ");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Settlement</u></b></td> ");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Balance Amount</u></b></td> ");
				out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Settle%</u></b></td> ");
				out.println("		<td width='20%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Adjustment No</u></b></td> ");
				out.println("		<td width='*%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Comment</u></b></td> ");
				out.println("</tr>");
				while(itr.hasNext())
				{
					String adj_num2=(String)itr.next();
					rs2 = stmt2.executeQuery (" SELECT "+
							  " A.CLIENT_CODE, "+
							  " A.FACILITY_NO,A.BATCH_NO,A.INVOICE_NO,A.ADJUST_AMOUNT, "+
							  " B.INVOICE_AMOUNT,B.SETTLE_AMOUNT,B.BALANCE_AMOUNT,TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY') INVOICE_DATE,A.ADJUSTMENT_NO,NVL(A.ADJUSTMENT_COMMENTS,'-') "+	
							  " FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B  "+
							  " WHERE "+
							  " A.BATCH_NO = B.BATCH_NO AND "+
							  " A.DEBTOR_CODE = B.DEBTOR_CODE AND  "+
							  "	A.INVOICE_NO = B.INVOICE_NO AND "+		
							  " A.ADJUSTMENT_NO='"+adj_num2+"' ");
					//out.println(adju_no);
					while(rs2.next())
					{
						
						out.println("<tr ><td width='10%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(3)+"</td>");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(4)+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(9)+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(6))+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}' >"+nf.format(rs2.getDouble(7))+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}' >"+nf.format(rs2.getDouble(8))+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:right;}' >"+rs2.getDouble(7)/rs2.getDouble(8)*100+"</td> ");
						out.println("		<td width='10%' class='factoring-letter-body' style='{text-align:left;}' >"+rs2.getString(10)+"</td> ");
						out.println("		<td width='*%' class='factoring-letter-body' style='{text-align:right;}' >"+rs2.getString(11)+"</td> ");
						out.println("</tr>");	
					}
					
				}	
				out.println("</table>");
				
				out.println("</p>");	
				
				out.println("<br><br>");
				out.println("</form></body></html>");
				}
			    else
				{
					out.println("session is not availbale");
				}
				session.invalidate();//clear the session data as soon as letters being printed
				
			}
			
			else  {
			out.println("idle");
			}	
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}  
