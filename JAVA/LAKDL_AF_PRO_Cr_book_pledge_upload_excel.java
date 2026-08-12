// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:17-05-2012
         

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
   

public class LAKDL_AF_PRO_Cr_book_pledge_upload_excel extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt,callstmt1,callstmt2;
	public PreparedStatement pstmt;
	java.text.NumberFormat nf;

	public String m_chksql;
	String m_msg;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_header_name = m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			
			System.out.println("Request :    " + req.getContentType());
			//String m_tomcat_url = "https://dev-lakdl.sasianet.com:/myserver/servlet";//Original Tomcat
			String m_tomcat_url = "https://dev-lakdl.sasianet.com:/lakdllive/servlet";//Original Tomcat//live
			//String m_tomcat_url = "http://www.bkptst.lakdl.lk:/lakdllive/servlet";//App Bk Tomcat 
			
			res.setStatus(200);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@snpdsrv:1521:SNPDDB", "LAKDL", "eightitengpw82");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@BKPTST01:1521:LAKDB", "LAKDL", "SNORA7623ADMIN");//App Bk Tomcat 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "SNORA7623ADMIN");
			
			conn.setAutoCommit(false);
			
			/*nf = NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);*/
			
			String m_username = "";
			
			String mm_bank_code   = "";
			String mm_branch_code = "";
			String mm_loan_no     = "";
			String mm_cr_book     = "CR_BOOK";
			String mm_screen      = "";
			String mm_username    = "";
			String m_path         = "";
			String m_rpt_type     = "run";
			String mm_fin_no       = "";
			String mm_vehicle_no   = "";
			String m_upload        ="DONE";




			// test area start

			
			//String m_path_2 = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"; // live
			//String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"; // development
			
			//String m_path_2 = "E:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Cr_Book\\"; //live and bk server // last used
			String m_path_2 = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\";
			
			//String m_path_2 = "D:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Cr_Book\\"; // test server
			
			//String m_path_2 = "F:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"; // added by udara 25-05-2017
			
			//out.println("m_path_2="+m_path_2);
			
			String m_field_name = "";
			
			
			Iterator iter = null;
			FileItem item = null;
			try
			{
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				System.out.println("is request mulitpart : " + ServletFileUpload.isMultipartContent(req));
				List items = upload.parseRequest(req);
				iter = items.iterator();
				item = null;
			}
			catch (Exception ex)
			{
				ByteArrayOutputStream ostr = new ByteArrayOutputStream();
				ex.printStackTrace(new PrintStream(ostr));
				out.println("<FONT COLOR=RED><BR>Error 01<BR></FONT><BR>" + ostr.toString());
				conn.close();
				
			}
			
			
			
			
				while (iter.hasNext()){
							
						item = (FileItem) iter.next();
		
							String path_input = "";
							
							m_field_name = item.getFieldName();
		
							
		
							if(m_field_name.equals("TXT_BANK_CODE")){
								mm_bank_code = item.getString();
							}
							
							if(m_field_name.equals("TXT_BRANCH_CODE")){
								mm_branch_code = item.getString();
							}
							
							if(m_field_name.equals("TXT_LOAN_NO")){
								mm_loan_no = item.getString();
								
							}
							
							if(m_field_name.equals("hid_screen_name")){
								mm_screen = item.getString();
							}
							if(m_field_name.equals("hid_m_user")){
						       mm_username = item.getString();
					        }
							
							
							if(m_field_name.equals("datafile")){
		
								if(mm_screen.equals("NEW") || mm_screen.equals("EDIT")){
									File savedFile = new File(m_path_2+mm_cr_book+".xls"); 
									item.write(savedFile);
								}
								
							}
					}
					
			

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				
				
				//m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"+mm_cr_book+".xls"; // development // commented by udara 28-06-2019
				//m_path = "E:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Cr_Book\\"+mm_cr_book+".xls"; // last used
				m_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"+mm_cr_book+".xls"; // last used
				
				//m_path = "F:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"+mm_item_cat+".xls"; // test path 25-05-2017
				//m_path = "D:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Cr_Book\\"+mm_item_cat+".xls"; // test server path
				//m_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Cr_Book\\"+mm_item_cat+".xls"; // live

				
				//Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls,*.xlsx, *.xlsm, *.xlsb)};DBQ="+m_path+"" ); 
				Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+m_path+"" ); 

				
				if(con==null){
					out.println("Connection Not Created");				
				}
				
	            Statement st = con.createStatement();
				
				ResultSet rs = st.executeQuery( "Select * from [Sheet1$]" ); // lakdl
				ResultSetMetaData rsmd = rs.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
			
				
				
				if(m_rpt_type.equals("run")){

                    m_msg="List is Successfully Uploaded " + mm_loan_no;  // added mm_loan_no by udara 11-07-2019
					
					int count = 0;
					int row = 0;
					while (rs.next())
				    {
					row++;
					//out.println("<BR>");
					for (int x = 1; x <= numberOfColumns; x++) {
					 }
				   }
				   
					rs = st.executeQuery( "Select * from [Sheet1$]" );
					boolean more=rs.next();
					
				
				   	callstmt1= conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_UPLOAD_CR_BOOK_CLEAN(:1); END;");	
					callstmt1.setString(1,mm_username);		
					callstmt1.execute(); 

					
					callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_PRO_UPLOAD_CR_BOOK_DET_SAVE(:1,:2,:3,:4,:5,:6,:7); END;");

					  if (numberOfColumns == 2) { 
						
						while (more) {
							
							mm_fin_no     = rs.getString(1);
							mm_vehicle_no = rs.getString(2);
						
                            callstmt1.setString(1,mm_fin_no); 
							callstmt1.setString(2,mm_vehicle_no); 
						    callstmt1.setString(3,mm_bank_code); 
							callstmt1.setString(4,mm_branch_code); 
							callstmt1.setString(5,mm_loan_no); 
							callstmt1.setString(6,mm_username); 
							callstmt1.setString(7,mm_screen); 
							callstmt1.execute();
							
							more=rs.next();
						}
					}	
				   	else
						{
						m_msg = "Excel Format Invalid";	
						}
						
							
						callstmt2= conn.prepareCall( "BEGIN "+m_schema_name+".AF_PRO_UPLOAD_CR_BOOK_VALID(:1); END;");
						callstmt2.setString(1,m_username);		
						callstmt2.execute(); 
						callstmt2.close();
						
						con.close();
						out.println("<HTML><HEAD>");
						out.println("<SCRIPT language='JavaScript'>");
						out.println("function displaymsg() {");
						out.println("alert('" + m_msg + "');");
						out.println("		m_url=\""+m_tomcat_url+"/LAKDL_AF_PRO_Cr_book_pledge_upload_excel_report?chksql=upload_exception_list\";");
			            out.println("   	window.open(m_url,'TheNewpop','fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes'); ");
		
						out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge?chksql=main_page&upload="+m_upload+"';"); 
						
						out.println("}</SCRIPT></HEAD>");
						out.println("<body onload='displaymsg();'></body>");
						out.println("</html>");
						
						out.flush();
						out.close();
				
			} // end run
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('" + m_msg + "');");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
			

			
			try{
				if(callstmt1!=null)callstmt1.close();
			}catch(Exception e){
				out.println("Callstmt1--"+e.toString());
			}
			
			try{
				if(callstmt2!=null)callstmt2.close();
			}catch(Exception e){
				out.println("Callstmt2--"+e.toString());
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
				conn.close();
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
