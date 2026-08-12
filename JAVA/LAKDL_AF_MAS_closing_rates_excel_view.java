// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:07-09-2012
         

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
   

public class LAKDL_AF_MAS_closing_rates_excel_view extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt1;
	public PreparedStatement pstmt;
	java.text.NumberFormat nf;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			//String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			//String m_path = req.getParameter("path").trim(); 
			String m_username = m_sn_methods.username;

			/*
			//String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\";
			String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\";
			
			String m_field_name = "";

				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\Closing_Rates.xls"; 
				
				Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+m_path+"" ); 
				
				if(con==null){
					out.println("Connection Not Created");				
				}


				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery( "Select * from [Sheet1$]" );
				ResultSetMetaData rsmd = rs.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();

				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Uploaded Data From Excel </TITLE>"); 
				out.println("</HEAD>"); 
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<br>");
				
				out.println("<table border=0 align=center >");
				out.println("<tr align=center >");
			    out.println("<td  align=center ><b> Uploaded Data From Excel </b></td>");
			    out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				
				out.println("<tr class=pdn_txtpos2 >");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Balance Period </b></td>");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Rate </b></td>");
				out.println("</tr>");

					int count = 0;
					
					while (rs.next()) {
						
						out.println("<tr>");
						
						for (int i = 1; i <= numberOfColumns; i++) {
							
							if (i == 1 ){
								out.println("<td width='2%' class='div_input' align='left'  > "+rs.getString(i)+" </td>");
							}
							else if (i == 2 ){
								out.println("<td width='2%' class='div_input' align='left'  > "+rs.getString(i)+" </td>");	
							}

								
						}
	
						out.println("</tr>");
	
					}

					
				out.println("</table>");
			
			
				out.println("</body>");
				out.println("</html>");
			
			*/


				rs= stmt.executeQuery(" "+
					" SELECT  "+
						   " A.BAL_PERIOD,"+ // 1          
					       " A.RATE   "+ // 2            
				  			 " FROM "+m_schema_name+".AF_CO_MAS_CLOSING_RATE A "+
							 //" WHERE  ENT_USER LIKE  '%"+m_username+"%' "+	
						" ");


				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Uploaded Data From Excel </TITLE>"); 
				out.println("</HEAD>"); 
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<br>");
				
				out.println("<table border=0 align=center >");
				out.println("<tr align=center >");
			    out.println("<td  align=center ><b> Uploaded Data From Excel </b></td>");
			    out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='left' width='50%' class='table' >");
				
				
				out.println("<tr class=pdn_txtpos2 >");
				out.println("<td width='1%' class='div_input' align='left'  ><b> No.</b></td>");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Schedule No.</b></td>");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Closing Rate</b></td>");
				out.println("</tr>");

					int count = 0;
					
				while(rs.next()){
					
					count = count + 1;
					
					out.println("<tr>");
					out.println("<td width='1%' class='div_input' align='left' >"+count+" </td>");
					out.println("<td width='2%' class='div_input' align='left' >"+rs.getString(1)+" </td>");
					out.println("<td width='2%' class='div_input' align='left' >"+rs.getString(2)+"</td>");
					out.println("</tr>");
					
				}

					
				out.println("</table>");
			
			
				out.println("</body>");
				out.println("</html>");

		
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
