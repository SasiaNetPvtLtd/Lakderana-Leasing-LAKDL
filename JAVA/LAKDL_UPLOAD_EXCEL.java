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


public class LAKDL_UPLOAD_EXCEL extends javax.servlet.http.HttpServlet { 
	
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
			
			String m_schedule_no = "";
			String m_path = "";
			String m_rpt_type = "run";
			
			String mm_schedule_no  = "";
			String mm_item_cat     = "";
			String mm_item_sub_cat = "";
			String mm_description  = "";
			String mm_status       = "";
			String mm_screen       = "";
			
			
			// test area start
			
			FileItemFactory factory = new DiskFileItemFactory();              
			ServletFileUpload upload = new ServletFileUpload(factory);  
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator(); 
			FileItem item = null;
			item = (FileItem) iter.next();
			
			
			
			
			String scheduleNo = "";
			
			int s_capital  = 0;
			String s_6  = "";
			String s_12 = "";
			String s_18 = "";
			String s_24 = "";
			String s_30 = "";
			String s_36 = "";
			String s_42 = "";
			String s_48 = "";
			String s_54 = "";
			String s_60 = "";
			
			double d_capital  = 0;
			double d_6  = 0;
			double d_12 = 0;
			double d_18 = 0;
			double d_24 = 0;
			double d_30 = 0;
			double d_36 = 0;
			double d_42 = 0;
			double d_48 = 0;
			double d_54 = 0;
			double d_60 = 0;
			
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\SCHEDULE_DETAILS.xls" ); 
			
			m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\cb.xls";
			//m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"+mm_schedule_no+".xls"; 
			
			
			//m_path = "D:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"+mm_schedule_no+".xls"; // for lakdl
			
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
			
			
			
			
			
			
			
			
			callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".UPLOAD_EXCEL_FILE(:1,:2,:3,:4); END;");
			
			//callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
			
			int count = 0;
			
			while (rs.next()) {
				
				for (int i = 1; i <= numberOfColumns; i++) {
				
					if (i == 1 ){
						s_capital = rs.getInt(i);
						
						
						
					}
				
					else if (i == 2 ){
						s_6 = rs.getString(i);
						
						
						
					}
					
					else if (i == 3){
						s_12 = rs.getString(i);
						
						
						
					}
					
					else if (i == 4){
						s_18 = rs.getString(i);
						
						
					}
					
					
				
					
				
					
					
					
					
					
				}
				
				
				
				
	
				callstmt1.setInt(1,s_capital); 
				callstmt1.setString(2,s_6);				
				callstmt1.setString(3,s_12);
				callstmt1.setString(4,s_18);
				callstmt1.execute();
				
				
				
				
			}
			
			
			
			
			
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
