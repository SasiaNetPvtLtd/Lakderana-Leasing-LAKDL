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

public class LAKDL_AF_RE_Bulk_Print_Main_PDC extends javax.servlet.http.HttpServlet {

  public String m_ref_no,m_chksql;
  public String m_from_date,m_to_date,m_print_type;
	public ResultSet rs;
	Connection conn;
  Statement stmt;
  String[]  m_inv_num = new String[2030]; 
	String[]  m_pay_ref = new String[2030];
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException	{
		
		try {
	
		  //************************************************************	
			//CLAMF_sn_methods m_sn_methods = new CLAMF_sn_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_schema_name = m_sn_methods.schema_name;
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			String reqstr = input.readLine();
		  //************************************************************
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			conn = m_sn_methods.met_user_validate(req); 
			
			//String m_chksql=(String)m_sn_methods.met_formdata(reqstr,"chksql");
			//out.println("reqstr"+reqstr);
			//out.println("conn"+conn);
			
			//String m_chksql="";
			m_chksql = req.getParameter("chksql"); 
			m_from_date = req.getParameter("from_date");
  	      	m_to_date = req.getParameter("to_date"); 
			m_print_type = req.getParameter("print_type");
			String m_ref_no="";
			
     	//out.println(reqstr);
			stmt = conn.createStatement();	
				
			
			
			  if(m_chksql.trim().equals("BULK_PRINT_RECEIPT")) {
				//String m_mode = req.getParameter("mode"); 
				String m_branch = req.getParameter("branch"); 


				LAKDL_Bulk_Printing_Main_PDC pdc_print =new LAKDL_Bulk_Printing_Main_PDC();
				String m_chq_pay_no="";
				int count = 1;
				
				//if (m_mode.equals("PDC")){
				
				rs = stmt.executeQuery ("SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')   >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')   <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND    PRINT_STATUS='PDC' "+ 
				" AND    "+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER) = '"+m_branch+"' "+
				" ORDER BY  REC_NO ");
				//}
				
														
			  boolean	more = rs.next();
				int i=0;
							
				while(more){
				//if(i<3){
				out.println("---------- Receipt No--"+i+"------"+rs.getString(1));
				int m_ginvoice_no=pdc_print.printing_interface_pdc(rs.getString(1));
				//int m_ginvoice_no=pdc_print.printing_interface_pdc("SR0070802-00051");
				//int m_ginvoice_no=pdc_print.printing_interface_pdc("SR0080827-26902");
				//out.println("---------- m_ginvoice_no--"+i+"------"+m_ginvoice_no);
							
				
				//}
				i=i+1;
				more = rs.next();
				}
				
				out.println("Document generated. Please check ");
				System.gc();
			}
				
			
			else {
				out.println("Undefined123");
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

