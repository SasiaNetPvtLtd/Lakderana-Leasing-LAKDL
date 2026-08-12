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
   

public class LAKDL_AF_MAS_upload_asset_details_upload_excel_3 extends javax.servlet.http.HttpServlet { 

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

			String mm_screen       = "";
			String m_rpt_type = "run";
			String mm_item_cat     = "";
			String mm_insurance_agent = "";


			// test area start

			FileItemFactory factory = new DiskFileItemFactory();              
			ServletFileUpload upload = new ServletFileUpload(factory);  
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator(); 
			FileItem item = null;
			item = (FileItem) iter.next();

			String m_field_name = "";
			
			while (iter.hasNext()){
					
				item = (FileItem) iter.next();

					
					m_field_name = item.getFieldName();
					
					if(m_field_name.equals("hid_screen_name")){
						mm_screen = item.getString();
					}
					

			}
				
				
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
				out.println("<td width='1%' class='div_input' align='left'  ><b> No.</b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Sum Insured </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Basic Premium </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Payable Premium </b></td>");
				out.println("</tr>");
				
				
				
				if(m_rpt_type.equals("run")){


					    callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS_SAVE_N(:1,:2,:3); END;");
					
					    int count = 0;

						callstmt1.setString(1,mm_item_cat); 
						callstmt1.setString(2,mm_insurance_agent);				
						callstmt1.setString(3,mm_screen);

						callstmt1.execute();
				
			} // end run


				
				int count_2 = 0; 
				
				rs1= stmt1.executeQuery(" "+
					" SELECT  "+
						   " A.VEHICLE_TYPE,"+ // 1          
					       " A.INSURANCE_AGENT, "+ // 2            
					       " A.SUM_INSURED, "+ // 3         
					       " A.BASIC_PREMIUM,  "+ // 4            
					       " A.ENT_USER,  "+ // 5            
					       " A.ENT_DATE,  "+ // 6            
					       " A.MOD_USER,  "+ // 7            
					       " A.MOD_DATE, "+   // 8  
							" A.PAYABLE_PREMIUM "+ // 9 // added by udara 16-04-2014
				  			 " FROM "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS A "+
							 " WHERE  UPPER(A.VEHICLE_TYPE) =  UPPER('"+mm_item_cat+"') "+	
						     " AND    A.INSURANCE_AGENT = '"+mm_insurance_agent+"' "+
								" ORDER BY A.SUM_INSURED "+
						" ");
				
				while(rs1.next()){
					
					count_2 = count_2 + 1;
					
					out.println("<tr>");
					out.println("<td width='1%' class='div_input' align='left'  ><b> "+count_2+"</b></td>");
					//out.println("<td width='4%' class='div_input' align='left'  >"+rs1.getString(1)+"</td>");
					//out.println("<td width='10%' class='div_input' align='left' >"+rs1.getString(2)+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(9))+"</td>");
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
		
			/*
			try{
				if(st!=null)st.close();
			}catch(Exception e){
				out.println("St--"+e.toString());
			}
			*/
			
			/*
			try{
				if(con!=null)con.close();
			}catch(Exception e){
				out.println("Con--"+e.toString());
			}	
			*/
		
		
		
		
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
