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
   

public class LAKDL_AF_MAS_closing_rates_Save_upload_excel extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt,callstmt1;
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

			
			String m_username = m_sn_methods.username;
			
			String mm_schedule_no  = "Closing_Rates";

			// test area start

			FileItemFactory factory = new DiskFileItemFactory();              
			ServletFileUpload upload = new ServletFileUpload(factory);  
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator(); 
			FileItem item = null;
			item = (FileItem) iter.next();

			String m_path = "";
			//String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\";
			String m_path_2 = "D:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\"; // for LAKDL
			
			String m_field_name = "";
			
			while (iter.hasNext()){
					
				item = (FileItem) iter.next();

					String path_input = "";
					
					m_field_name = item.getFieldName();
					
					if(m_field_name.equals("datafile")){

							File savedFile = new File(m_path_2+mm_schedule_no+".xls"); 
							item.write(savedFile);
						
					}
			}

			
			// test area end

				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\Closing_Rates.xls" ); 
                
				
				//m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\"+mm_schedule_no+".xls"; 

				
				m_path = "D:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Closing_Rates\\"+mm_schedule_no+".xls"; // for lakdl
				
				Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+m_path+"" ); 
				
				if(con==null){
					out.println("Connection Not Created");				
				}

				Statement st = con.createStatement();
				//ResultSet rs = st.executeQuery( "Select * from [MAIN_DATA$]" ); // Sheet1
				ResultSet rs = st.executeQuery( "Select * from [Sheet1$]" ); // lakdl
				//ResultSet rs = st.executeQuery( "Select * from [Sheet1$A:I]" ); // sasianet
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
				
				
				out.println("<table align='left' width='50%' class='table' >");
				
				
				out.println("<tr class=pdn_txtpos2 >");
				out.println("<td width='1%' class='div_input' align='left'  ><b> No.</b></td>");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Schedule No.</b></td>");
				out.println("<td width='2%' class='div_input' align='left'  ><b> Closing Rate</b></td>");
				//out.println("<td width='*%' class='div_input' align='left'  > &nbsp; </td>");
				out.println("</tr>");

				String s_bal_period = "";
				String s_rate       = "";
				
				double d_bal_period = 0;
				double d_rate       = 0;
				
				callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_CO_MAS_CLOSING_RATE_SAVE(:1,:2,:3); END;");
				callstmt1.setDouble(1,d_bal_period); 
				callstmt1.setDouble(2,d_rate);				
				callstmt1.setString(3,m_username);
				callstmt1.execute();
				

					callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_CO_MAS_CLOSING_RATE_SAVE_I(:1,:2,:3); END;");

					
					while (rs.next()) {
						
						for (int i = 1; i <= numberOfColumns; i++) {

							if (i == 1 ){
								s_bal_period = rs.getString(i);
								d_bal_period = Double.parseDouble(s_bal_period);
								//out.println(d_bal_period);
								//if(!String.valueOf(s_bal_period).equals("null"))
								//	d_bal_period = Double.parseDouble(s_bal_period);
								
							}

							else if (i == 2 ){
								s_rate = rs.getString(i);
								d_rate = Double.parseDouble(s_rate);
								//out.println(d_rate);
								//if(!String.valueOf(s_rate).equals("null"))
								//	d_rate = Double.parseDouble(s_rate);
								
							}

								
						}

						callstmt1.setDouble(1,d_bal_period); 
						callstmt1.setDouble(2,d_rate);				
						callstmt1.setString(3,m_username);
						callstmt1.execute();
	
	
					}
			
				
			    
				int count_2 = 0; 
				
				rs1= stmt1.executeQuery(" "+
					" SELECT  "+
						   " A.BAL_PERIOD,"+ // 1          
					       " A.RATE   "+ // 2            
				  			 " FROM "+m_schema_name+".AF_CO_MAS_CLOSING_RATE A "+
							 " WHERE  ENT_USER LIKE  '%"+m_username+"%' "+	
						" ");
				
				while(rs1.next()){
					
					count_2 = count_2 + 1;
					
					out.println("<tr>");
					out.println("<td width='1%' class='div_input' align='left' >"+count_2+" </td>");
					out.println("<td width='2%' class='div_input' align='left' >"+rs1.getString(1)+" </td>");
					out.println("<td width='2%' class='div_input' align='left' >"+rs1.getString(2)+"</td>");
					//out.println("<td width='*%' class='div_input' align='left'  > &nbsp; </td>");
					out.println("</tr>");
					
				}
				
				
				out.println("</table>");
			
			
				out.println("</body>");
				out.println("</html>");
			

			
			try{
				if(callstmt1!=null)callstmt1.close();
			}catch(Exception e){
				out.println("Callstmt1--"+e.toString());
			}
		
			try{
				if(st!=null)st.close();
			}catch(Exception e){
				out.println("St--"+e.toString());
			}
			
			try{
				if(con!=null)con.close();
			}catch(Exception e){
				out.println("Con--"+e.toString());
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
