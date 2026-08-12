import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.sql.*;
import javax.print.*;


import oracle.jdbc.driver.*;



//Created by Chandana on 08/01/2008

public class LAKDL_AF_RE_Bulk_Print_Main_SO extends javax.servlet.http.HttpServlet {

  public String m_ref_no,m_chksql;
  public String m_from_date,m_to_date,m_print_type;
	public ResultSet rs;
	Connection conn;
  Statement stmt;
		java.text.NumberFormat nf,nf1;

  String[]  m_inv_num = new String[2030]; 
	String[]  m_pay_ref = new String[2030];
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException	{
		
		try {
	
		  //************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_schema_name = m_sn_methods.schema_name;
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			String reqstr = input.readLine();
		  //************************************************************
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			conn = m_sn_methods.met_user_validate(req); 
			
			m_chksql = req.getParameter("chksql"); 
			String m_ref_no="";
			
			stmt = conn.createStatement();	
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			
			  if(m_chksql.trim().equals("STANDING_ORDER_BULK_PRINT")) {
				m_from_date = req.getParameter("from_date");
  	      		m_to_date = req.getParameter("to_date"); 
				m_print_type = req.getParameter("print_type");

				if(m_print_type.equals("ALL")){
					m_print_type = "%";
				}

				LAKDL_Bulk_Printing_Main_SO pdc_print =new LAKDL_Bulk_Printing_Main_SO();
				
				rs = stmt.executeQuery ("SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND    SETTLE_MODE='STD_ORD' AND STATUS <> 'CAD' AND ENT_USER LIKE '"+m_print_type+"' "+ 
				" ORDER BY  REC_NO ");
														
			    boolean	more = rs.next();
				int i=0;
							
				while(more){
				out.println("---------- Receipt No--"+i+"------"+rs.getString(1));
				int m_ginvoice_no=pdc_print.printing_interface_pdc(rs.getString(1));
				i=i+1;
				more = rs.next();
				}
				
				out.println("Document generated. Please check ");
				System.gc();
			}
				
			else {
				out.println("Undefined");
			}
      out.close();
			this.destroy();
		}
		catch (Exception e) {
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
      out.close();
			
		}
	}
}

