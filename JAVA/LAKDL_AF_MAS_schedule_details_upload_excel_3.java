// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:05-06-2012
         

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
   

public class LAKDL_AF_MAS_schedule_details_upload_excel_3 extends javax.servlet.http.HttpServlet { 

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


			FileItemFactory factory = new DiskFileItemFactory();              
			ServletFileUpload upload = new ServletFileUpload(factory);  
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator(); 
			FileItem item = null;
			item = (FileItem) iter.next();

			
			String m_field_name = "";
			
			while (iter.hasNext()){
					
				item = (FileItem) iter.next();

					String path_input = "";
					
					m_field_name = item.getFieldName();
					
					//out.println(m_field_name);
					
					if(m_field_name.equals("TXT_SCHEDULE_CODE")){
						mm_schedule_no = item.getString();
					}

					if(m_field_name.equals("TXT_ITEM_CAT")){
						mm_item_cat = item.getString();
					}
					
					if(m_field_name.equals("TXT_ITEM_SUB_CAT")){
						mm_item_sub_cat = item.getString();
					}
					
					if(m_field_name.equals("TXT_SCHEDULE_DESCRIPTION")){
						mm_description = item.getString();
					}
					
					if(m_field_name.equals("hid_screen_name")){
						mm_screen = item.getString();
					}

			}


			
			String m_msg = "'Information saved successfully'";
			String m_url = m_class_url;
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_SAVE(:1,:2,:3,:4,:5,:6); END;");

			callstmt.setString(1,mm_schedule_no);
			callstmt.setString(2,mm_item_cat);
			callstmt.setString(3,mm_item_sub_cat);
			callstmt.setString(4,mm_description);
			callstmt.setString(5,m_username);
			callstmt.setString(6,mm_screen);
			callstmt.execute();
			callstmt.close();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_schedule_details';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

			//out.println(mm_schedule_no+" - "+mm_item_cat+" - "+mm_item_sub_cat+" - "+mm_description+" - "+mm_screen);		
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      		out.close();
		}
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
