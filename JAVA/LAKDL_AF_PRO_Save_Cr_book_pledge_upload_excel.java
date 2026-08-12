// DEVELOP BY : Ishani 2013.07.11
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_PRO_Save_Cr_book_pledge_upload_excel extends javax.servlet.http.HttpServlet { 
	
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		Statement stmt1,stmt2,stmt3,stmt4,stmt;
		stmt1=stmt2=stmt3=stmt4=stmt=null;
		CallableStatement callstmt1 =null;
		
		java.text.NumberFormat nf;
		nf=null;
		ResultSet rs1=null,rs=null,rs2=null,rs3=null,rs4=null;
		
		
		String m_chksql;
		String m_msg;
		
		
		try { 
			
			//******************************************************************************** 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			//**********************************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			String m_loan_no = "";
			
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			
			try{
				
				String m_trn_type = req.getParameter("type");
				callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_PRO_UPLOAD_CR_BOOK_SAVE(:1,:2); END;");
				
				callstmt1.setString(1,m_username);
			    //callstmt1.setString(2 ,null);
				callstmt1.registerOutParameter(2,java.sql.Types.CHAR); // added by udara 11-07-2019

				callstmt1.execute();
				
				m_loan_no = callstmt1.getString(2); // added by udara 11-07-2019
				
				
				m_msg="Data Save Successfully " + m_loan_no;
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('" + m_msg + "');");
				//out.println("window.close();");
                //out.println("window.history.back();");
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge_upload_excel_report?chksql=upload_exception_list&status=autho'");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
			
		    }
			catch (Exception ex)
			{
				ByteArrayOutputStream ostr = new ByteArrayOutputStream();
				ex.printStackTrace(new PrintStream(ostr));
				
				
				out.println("<FONT COLOR=RED><BR>Error<BR></FONT><BR>" + ostr.toString());
				
				System.out.println("CR Book List Upload Error ==> " + ostr.toString());
				
				m_msg = "Upload Excel Save Failed";
				out.println("Upload Excel Save Failed ERROR:" + ex.toString());
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('" + m_msg + "');");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
			}
			ByteArrayOutputStream ostr;
			return;
			
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
