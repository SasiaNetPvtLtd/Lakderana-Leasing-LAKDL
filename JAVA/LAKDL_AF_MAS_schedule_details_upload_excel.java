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
   

public class LAKDL_AF_MAS_schedule_details_upload_excel extends javax.servlet.http.HttpServlet { 

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


			String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"; // SN
			//String m_path_2 = "D:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"; // for LAKDL
			
			//String m_path_2 = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\";
			
			
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
					
					if(m_field_name.equals("datafile")){

						if(mm_screen.equals("NEW") || mm_screen.equals("EDIT")){
							//File savedFile = new File(m_path_2+"SHEDULE_DETAILS_1.xls");
							File savedFile = new File(m_path_2+mm_schedule_no+".xls"); 
							item.write(savedFile);
						}
						
					}
			}

			
			// test area end
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_SAVE(:1,:2,:3,:4,:5,:6); END;");

			callstmt.setString(1,mm_schedule_no);
			callstmt.setString(2,mm_item_cat);
			callstmt.setString(3,mm_item_sub_cat);
			callstmt.setString(4,mm_description);
			callstmt.setString(5,m_username);
			callstmt.setString(6,mm_screen);
			callstmt.execute();
			callstmt.close();
			


				//out.println(mm_schedule_no+" - "+mm_item_cat+" - "+mm_item_sub_cat+" - "+mm_description+" - "+mm_screen);
			
				
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

				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\SCHEDULE_DETAILS.xls" ); 
                
				
				m_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"+mm_schedule_no+".xls"; // SN
				//m_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\\"+mm_schedule_no+".xls";
				

				
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
				out.println("<td width='4%' class='div_input' align='left'  ><b> Schedule No.</b></td>");
				out.println("<td width='10%' class='div_input' align='left'  ><b> Description</b></td>");
				out.println("<td width='8%' class='div_input' align='left'  ><b> Item Category</b></td>");
				out.println("<td width='8%' class='div_input' align='left'  ><b> Item Sub Category</b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> Capital     </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 6  </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 12 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 18 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 24 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 30 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 36 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 42 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 48 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 54 </b></td>");
				out.println("<td width='4%' class='div_input' align='right' ><b> 60 </b></td>");
				out.println("</tr>");
				
				
				if(m_rpt_type.equals("run")){
				
					
					
				    

					callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
					
					int count = 0;
					
					while (rs.next()) {
						
						for (int i = 1; i <= numberOfColumns; i++) {
							/*
							if (i == 1 ){
								//scheduleNo = rs.getString(i);
								//m_schedule_no = scheduleNo;
							}
							*/
							
							//else if (i == 2 ){
							if (i == 1 ){
								s_capital = rs.getString(i);
								
								if(!String.valueOf(s_capital).equals("null"))
									d_capital = Double.parseDouble(s_capital);
								
							}
							//else if (i == 3 ){
							else if (i == 2 ){
								s_6 = rs.getString(i);
								
								if(!String.valueOf(s_6).equals("null"))
									d_6 = Double.parseDouble(s_6);
								
							}
							//else if (i == 4){
							else if (i == 3){
								s_12 = rs.getString(i);
								
								if(!String.valueOf(s_12).equals("null"))
									d_12 = Double.parseDouble(s_12);
								
							}
							//else if (i == 5 ){
							else if (i == 4){
								s_18 = rs.getString(i);
								
								if(!String.valueOf(s_18).equals("null"))
									d_18 = Double.parseDouble(s_18);
								
							}
							//else if (i == 6 ){
							else if (i == 5){
								s_24 = rs.getString(i);
								
								if(!String.valueOf(s_24).equals("null"))
									d_24 = Double.parseDouble(s_24);
								
							}
							//else if (i == 7 ){
							else if (i == 6 ){
								s_30 = rs.getString(i);
								
								if(!String.valueOf(s_30).equals("null"))
									d_30 = Double.parseDouble(s_30);
								
							}
							//else if (i == 8 ){
							else if (i == 7 ){
								s_36 = rs.getString(i);

								//if(!String.valueOf(s_36).equals("null"))
									//d_36 = Double.parseDouble(s_36);
									
								if(!String.valueOf(s_36).equals(""))
									d_36 = Double.parseDouble(s_36); 
									
								
							}
							//else if (i == 9 ){
							else if (i == 8 ){
								s_42 = rs.getString(i);
								
								if(!String.valueOf(s_42).equals("null"))
									d_42 = Double.parseDouble(s_42);
								
								//if(!String.valueOf(s_42).equals(""))
								//	d_42 = Double.parseDouble(s_42);
								
							}
							//else if (i == 10 ){
							else if (i == 9 ){
									s_48 = rs.getString(i);
								
								if(!String.valueOf(s_48).equals("null"))
									d_48 = Double.parseDouble(s_48);
								
							}
							
							//else if (i == 11 ){
							else if (i == 10 ){
								s_54 = rs.getString(i);	
								
								if(!String.valueOf(s_54).equals("null"))
									d_54 = Double.parseDouble(s_54);
								
							}
							//else if (i == 12 ){
							else if (i == 11 ){
								s_60 = rs.getString(i);	
								
								if(!String.valueOf(s_60).equals("null"))
									d_60 = Double.parseDouble(s_60);
								
							}
							
							
							/*
							out.println("<tr>");
							out.println("<td width='1%' class='div_input' align='left'  ><b> no </b></td>");
							out.println("<td width='1%' class='div_input' align='left'  ><b> "+scheduleNo+"</b></td>");
							out.println("<td width='10%' class='div_input' align='left' > des </td>");
							out.println("<td width='8%' class='div_input' align='left' > item cat  </td>");
							out.println("<td width='8%' class='div_input' align='left' > item sub cat  </td>");
							out.println("<td width='4%' class='div_input' align='left' >"+ d_capital+"</td>");
							out.println("<td width='10%' class='div_input' align='left' >"+d_6+"</td>");
							out.println("<td width='8%' class='div_input' align='left' >"+d_12+"</td>");
							out.println("<td width='8%' class='div_input' align='left' >"+d_18+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_24+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_30+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_36+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_42+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_48+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_54+"</td>");
							out.println("<td width='4%' class='div_input' align='right' >"+d_60+"</td>");
							out.println("</tr>");
							*/
							
							
								
						}
						
						//callstmt1.setString(1,scheduleNo);
						callstmt1.setString(1,mm_schedule_no); 
						callstmt1.setDouble(2,d_capital);				
						callstmt1.setDouble(3,d_6);
						callstmt1.setDouble(4,d_12);
						callstmt1.setDouble(5,d_18); 
						callstmt1.setDouble(6,d_24); 
						callstmt1.setDouble(7,d_30); 
						callstmt1.setDouble(8,d_36); 
						callstmt1.setDouble(9,d_42); 
						callstmt1.setDouble(10,d_48);
						callstmt1.setDouble(11,d_54);
						callstmt1.setDouble(12,d_60);
						callstmt1.setString(13,m_username);
						callstmt1.setString(14,String.valueOf(count));
						callstmt1.execute();

						
						count = count + 1;
	
	
					}
				
			} // end run
			
				
				int count_2 = 0; 
				
				rs1= stmt1.executeQuery(" "+
					" SELECT  "+
						   " A.SHEDULE_REF,"+ // 1          
					       " A.CAPITAL,    "+ // 2            
					       " A.PERIOD_06,  "+ // 3         
					       " A.PERIOD_12,  "+ // 4            
					       " A.PERIOD_18,  "+ // 5            
					       " A.PERIOD_24,  "+ // 6            
					       " A.PERIOD_30,  "+ // 7            
					       " A.PERIOD_36,  "+ // 8            
					       " A.PERIOD_42,  "+ // 9            
					       " A.PERIOD_48,  "+ // 10            
					       " A.PERIOD_54,  "+ // 11           
					       " A.PERIOD_60,  "+ // 12            
					       " A.ENT_USER,   "+ // 13            
					       " A.ENT_DATE,   "+ // 14           
					       " A.MOD_USER,   "+ // 15            
					       " A.MOD_DATE,    "+ // 16
					       " B.ITEM_CAT_CODE, "+ // 17
					       " B.ITEM_SUB_CAT, "+ // 18
					       " B.DESCRIPTION "+ // 19
				  			 " FROM "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_DETAIL A, "+m_schema_name+".AF_CO_PRO_APP_SHEDULE B "+
							 //" WHERE  A.SHEDULE_REF LIKE  '%"+m_schedule_no+"%' "+
							 " WHERE  UPPER(A.SHEDULE_REF) LIKE  UPPER('%"+mm_schedule_no+"%') "+	
						     " AND    A.SHEDULE_REF = B.SHEDULE_REF "+
						     //" ORDER BY A.ENT_DATE "+
							 " ORDER BY A.CAPITAL ASC "+
						" ");
				
				while(rs1.next()){
					
					count_2 = count_2 + 1;
					
					out.println("<tr>");
					out.println("<td width='1%' class='div_input' align='left'  ><b> "+count_2+"</b></td>");
					out.println("<td width='4%' class='div_input' align='left' >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class='div_input' align='left' >"+rs1.getString(19)+"</td>");
					out.println("<td width='8%' class='div_input' align='left' >"+rs1.getString(17)+"</td>");
					out.println("<td width='8%' class='div_input' align='left' >"+rs1.getString(18)+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(7))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(9))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(10))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(11))+"</td>");
					out.println("<td width='4%' class='div_input' align='right' >"+nf.format(rs1.getDouble(12))+"</td>");
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
