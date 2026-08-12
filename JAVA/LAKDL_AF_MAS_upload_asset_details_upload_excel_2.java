// DEVELOP BY : UDARA 24-12-2014
         

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
   

public class LAKDL_AF_MAS_upload_asset_details_upload_excel_2 extends javax.servlet.http.HttpServlet { 

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
			
			String m_vehicle_type = req.getParameter("vehicle_type").trim(); 
			String m_ins_company = req.getParameter("ins_company").trim(); 
			String m_path = req.getParameter("path").trim(); 

			
				
				String scheduleNo = "";
				
				String s_capital  = "";
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
				out.println("<td width='1%'  class='div_input' align='left'  ><b> No.</b></td>");
				out.println("<td width='4%'  class='div_input' align='left'  ><b> Vehicle Type</b></td>");
				out.println("<td width='10%' class='div_input' align='left'  ><b> Insurance Agent</b></td>");
				out.println("<td width='4%'  class='div_input' align='right' ><b> Sum Insured </b></td>");
				out.println("<td width='4%'  class='div_input' align='right' ><b> Basic Premium  </b></td>");
				out.println("<td width='4%'  class='div_input' align='right' ><b> Basic Commission Rate </b></td>");
				out.println("<td width='4%'  class='div_input' align='right' ><b> RCC Commission rate </b></td>");
				out.println("<td width='4%'  class='div_input' align='right' ><b> Payable Premium </b></td>");
				out.println("<td width='4%'  class='div_input' align='left'  ><b> Flag Status </b></td>");
				out.println("</tr>");

				
				int count_2 = 0; 
				
				rs1= stmt1.executeQuery(" "+
					" SELECT  "+
						   " A.VEHICLE_TYPE,"+ // 1          
					       " A.INSURANCE_AGENT,  "+ // 2            
					       " A.SUM_INSURED,  "+ // 3         
					       " A.BASIC_PREMIUM,  "+ // 4            
					       " A.BASIC_COMM_RATE,  "+ // 5            
					       " A.RCC_COMM_RATE,  "+ // 6            
					       " A.PAYABLE_PREMIUM,  "+ // 7   
						   //" NVL(A.ACTIVE_STATUS,'-')  "+ // 8
						   " NVL(A.FLAG_THIS,'N') "+ // 8
				  			 " FROM "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS A "+
							 " WHERE  UPPER(A.VEHICLE_TYPE) LIKE  UPPER('%"+m_vehicle_type+"%') "+
						     " AND    UPPER(A.INSURANCE_AGENT) LIKE  UPPER('%"+m_ins_company+"%') "+
						     " ORDER BY A.VEHICLE_TYPE, A.INSURANCE_AGENT "+
						" ");
				
				while(rs1.next()){
					
					count_2 = count_2 + 1;
					
					out.println("<tr>");
					out.println("<td width='1%' class='div_input' align='left'  ><b> "+count_2+"</b></td>");
					out.println("<td width='4%' class='div_input' align='left'  >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class='div_input' align='left' >"+rs1.getString(2)+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(7))+"</td>");
					out.println("<td width='4%' class='div_input' align='left'  >"+rs1.getString(8)+"</td>");
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
