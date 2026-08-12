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
   

public class LAKDL_AF_MAS_upload_asset_details_upload_excel extends javax.servlet.http.HttpServlet { 

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
			//conn=m_sn_methods.met_user_validate(req); // dev
			conn=m_sn_methods.direct_conn(); // live
			
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
			String mm_insurance_agent = "";
			String mm_description  = "";
			String mm_status       = "";
			String mm_screen       = "";
			String m_flag_status     = ""; // added by udara 19-07-2018
			
			String mm_basic_comm_rate = "";
			String mm_rcc_comm_rate = "";
			
			double dd_basic_comm_rate = 0;
			double dd_rcc_comm_rate = 0;


			// test area start

			FileItemFactory factory = new DiskFileItemFactory();              
			ServletFileUpload upload = new ServletFileUpload(factory);  
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator(); 
			FileItem item = null;
			item = (FileItem) iter.next();
			
			out.println(" test 1 "); // udara test upload

			
			String m_path_2 = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"; // live
			//String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"; // development
			//String m_path_2 = "D:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Asset_Details\\"; // test server
			
			//String m_path_2 = "F:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"; // added by udara 25-05-2017
			
			
			String m_field_name = "";
			
			out.println(" test 2 "); // udara test upload
			
			while (iter.hasNext()){
					
				item = (FileItem) iter.next();

					String path_input = "";
					
					m_field_name = item.getFieldName();

					//out.println(m_field_name);
					
					//if(m_field_name.equals("TXT_SCHEDULE_CODE")){
					//	mm_schedule_no = item.getString();
					//}
					
					// added by udara 19-07-2018
					if(m_field_name.equals("flag_this")){
						m_flag_status = item.getString();
					}
					// end by udara 19-07-2018

					if(m_field_name.equals("TXT_ITEM_SUB_CAT")){
						mm_item_cat = item.getString();
					}
					
					if(m_field_name.equals("txt_in_company")){
						mm_insurance_agent = item.getString();
					}
					
					if(m_field_name.equals("txt_comm_rate")){
						mm_basic_comm_rate = item.getString();
						dd_basic_comm_rate = Double.parseDouble(mm_basic_comm_rate);
					}
					
					if(m_field_name.equals("txt_rcc_rate")){
						mm_rcc_comm_rate = item.getString();
						dd_rcc_comm_rate = Double.parseDouble(mm_rcc_comm_rate);
					}


					//if(m_field_name.equals("TXT_SCHEDULE_DESCRIPTION")){
					//	mm_description = item.getString();
					//}
					
					if(m_field_name.equals("hid_screen_name")){
						mm_screen = item.getString();
					}
					
					if(m_field_name.equals("hid_m_user")){
						m_username = item.getString();
					}
					
					if(m_field_name.equals("datafile")){

						if(mm_screen.equals("NEW") || mm_screen.equals("EDIT")){
							//File savedFile = new File(m_path_2+"SHEDULE_DETAILS_1.xls");
							File savedFile = new File(m_path_2+mm_item_cat+".xls"); 
							item.write(savedFile);
						}
						
					}
			}
			
			out.println(" test 3 "); // udara test upload

			
			// test area end
			
			/*
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_SAVE(:1,:2,:3,:4,:5,:6); END;");

			callstmt.setString(1,mm_schedule_no);
			callstmt.setString(2,mm_item_cat);
			callstmt.setString(3,mm_insurance_agent);
			callstmt.setString(4,mm_description);
			callstmt.setString(5,m_username);
			callstmt.setString(6,mm_screen);
			callstmt.execute();
			callstmt.close();
			*/


				//out.println(mm_schedule_no+" - "+mm_item_cat+" - "+mm_insurance_agent+" - "+mm_description+" - "+mm_screen);
			
				
				String scheduleNo = "";
				
				String s_no  = "";
				String s_sum_insured  = "";
				String s_basic_premium = "";
				String s_payable_premium = ""; // added by udara 16-04-2014

				
				double d_capital  = 0;
				double d_sum_insured  = 0;
				double d_basic_premium = 0;
				double d_payable_premium = 0; // added by udara 16-04-2014


				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				
				out.println(" test 4 "); // udara test upload

				
				// commented by udara 22-05-2017
				
				//m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"+mm_item_cat+".xls"; // development
				//m_path = "F:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"+mm_item_cat+".xls"; // test path 25-05-2017
				//m_path = "D:\\SasiaNet\\NetAsset\\TESTLAKDL\\UPLOAD\\Asset_Details\\"+mm_item_cat+".xls"; // test server path
				m_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Asset_Details\\"+mm_item_cat+".xls"; // live

				
				//Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls,*.xlsx, *.xlsm, *.xlsb)};DBQ="+m_path+"" ); 
				Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+m_path+"" ); 

				
				if(con==null){
					out.println("Connection Not Created");				
				}

				Statement st = con.createStatement();
				
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
				
				/*
				//test
				out.println("<table border=0 align=center >");
				out.println("<tr align=center >");
			    out.println("<td  align=center ><b> "+numberOfColumns+" </b></td>");
			    out.println("</tr>");
				out.println("</table>");
				// end test
				*/
				
				
				out.println("<table align='center' width='100%' class='table' >");
				
				
				out.println("<tr class=pdn_txtpos2 >");
				out.println("<td width='1%' class='div_input' align='left'  ><b> No.</b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Sum Insured </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Basic Premium </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Payable Premium </b></td>");
				out.println("</tr>");
				
				
				
				if(m_rpt_type.equals("run")){


					callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
					
					int count = 0;
					
					while (rs.next()) {
						
						for (int i = 1; i <= numberOfColumns; i++) {
							
							if (i == 1 ){
								s_no = rs.getString(i);
								
							}

							else if (i == 2 ){
								s_sum_insured = rs.getString(i);
								
								if(!String.valueOf(s_sum_insured).equals("null"))
									d_sum_insured = Double.parseDouble(s_sum_insured);
								
							}

							else if (i == 3 ){
								s_basic_premium = rs.getString(i);
								
								if(!String.valueOf(s_basic_premium).equals("null"))
									d_basic_premium = Double.parseDouble(s_basic_premium);
								
							}
							
							
							// added by udara 16-04-2014
							else if (i == 4 ){
								s_payable_premium = rs.getString(i);
								
								if(!String.valueOf(s_payable_premium).equals("null"))
									d_payable_premium = Double.parseDouble(s_payable_premium);
								
							}
							// end by udara 16-04-2014
							

							
								
						}
						
						/*
						out.println("<tr>");
						out.println("<td width='8%' class='div_input' align='left'  >"+s_no+"</td>");
						out.println("<td width='4%' class='div_input' align='right' >"+d_sum_insured+"</td>");
						out.println("<td width='4%' class='div_input' align='right' >"+d_basic_premium+"</td>");
						out.println("</tr>");
						*/
						

						callstmt1.setString(1,mm_item_cat); 
						callstmt1.setString(2,mm_insurance_agent);				
						callstmt1.setDouble(3,d_sum_insured);
						callstmt1.setDouble(4,d_basic_premium);		
						
						callstmt1.setDouble(5,dd_basic_comm_rate);	
						callstmt1.setDouble(6,dd_rcc_comm_rate);	
						
						callstmt1.setString(7,m_username);
						callstmt1.setString(8,String.valueOf(count));
						
						callstmt1.setDouble(9,d_payable_premium); // added by udara 16-04-2014
						callstmt1.setString(10,m_flag_status);
						
						callstmt1.execute();
						
						
						count = count + 1;
	
	
					}
				
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
