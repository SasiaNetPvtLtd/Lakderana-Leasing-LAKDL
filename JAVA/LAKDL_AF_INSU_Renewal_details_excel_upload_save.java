import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class LAKDL_AF_INSU_Renewal_details_excel_upload_save
	extends HttpServlet
{
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException
	{
		
		ServletOutputStream out = null;
		ResultSet rs= null;
		ResultSet rs1= null;
		Connection conn= null;
		Statement stmt= null;
		Statement stmt1= null;
		CallableStatement callstmt= null;
		CallableStatement callstmt1= null;
		PreparedStatement pstmt= null;
		NumberFormat nf= null;
		String m_chksql= null;
		Connection con = null;
		
		
		String m_path = "";		
		//String m_path_2 = "E:\\SasiaNet\\NetAsset\\LAKDL\\UPLOAD\\Insurance_Renewal\\"; // live
		String m_path_2 = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\INSURANCE_RENEWAL\\"; // dev
		String m_msg = "Excel Uploaded Successfully";
		String m_field_name = "";
		String m_date = "";
		String m_com_name = "";
		String m_com_name_id = "";
		
		try
		{
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_header_name = m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			
			System.out.println("Request :    " + req.getContentType());
			String m_tomcat_url = "https://dev-lakdl.sasianet.com:/lakdllive/servlet";//Original Tomcat and transfer into upload tomcat 
			res.setStatus(200);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@172.20.1.117:1521:LAKDB", "LAKDL", "snora7623admin"); live
			conn = DriverManager.getConnection("jdbc:oracle:thin:@snpdsrv:1521:SNPDDB", "LAKDL", "eightitengpw82"); // dev
			
			conn.setAutoCommit(false);
			
			nf = NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			String m_username = "";
			
			
			Iterator iter = null;
			FileItem item = null;
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			System.out.println("is request mulitpart : " + ServletFileUpload.isMultipartContent(req));
			List items = upload.parseRequest(req);
			iter = items.iterator();
			item = null;
			
			
			out.println("<HTML><HEAD></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language='JavaScript'>");
			out.println(" 	function displaymsg() {");
			out.println(" 		alert('" + m_msg + "');");
			out.println(" 	}");
			
			out.println("function proceed(batchId) {");
			out.println("    if(confirm('Are you sure want to proceed?')){ ");
			out.println("    	window.location.href='"+m_tomcat_url+"/LAKDL_AF_INSU_Renewal_details_excel_upload_confirmation?screen=UPLOAD&mode=CONFIRM&batchId='+batchId; ");
			out.println("    }");
			out.println("}");
			
			out.println("function reject(batchId) {");
			out.println("    if(confirm('Are you sure want to proceed?')){ ");
			out.println("    	window.location.href='"+m_tomcat_url+"/LAKDL_AF_INSU_Renewal_details_excel_upload_confirmation?screen=UPLOAD&mode=REJECT&batchId='+batchId; ");
			out.println("    }");
			out.println("}");
			
			//ADDED BY NISHANTHA ON 22-03-2019 #JB12102018-05462
			out.println("function downloadException(batchId) {");
			out.println("    if(confirm('Are you sure want to downlaod?')){ ");
			out.println("    	window.location.href='"+m_tomcat_url+"/LAKDL_AF_INSU_Renewal_details_excel_download?reportType=EXCEPTION_DOWNLOAD&uploadBatchId='+batchId; ");
			out.println("    }");
			out.println("}");
			//END NP
			out.println("</SCRIPT>");
			out.println("<body onload='displaymsg();'></body>");
			
			File savedFile;
			while (iter.hasNext())
			{
				item = (FileItem)iter.next();
				m_field_name = item.getFieldName();
				if (m_field_name.equals("COM_NAME")) {
					m_com_name_id = item.getString();
				} else if (m_field_name.equals("hid_com_name")) {
					m_com_name = item.getString();
				} else if (m_field_name.equals("username")) {
					m_username = item.getString();
				} 
				
				
			}
			
			con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+m_path_2+"DATAFILE.xls");
			if (con == null) {
				out.println("Connection Not Created");
			}
			Statement st = con.createStatement();
			rs = st.executeQuery("Select * from [Sheet1$]");
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int row = 0;
			/*while (rs.next())
			{
				row++;
				out.println("<BR>");
				for (int x = 1; x <= numberOfColumns; x++) {
						out.println("<BR>row(" + row + ") cell(" + x + ") ==> " + rs.getString(x));
				}
			}
			rs.close();
			
			rs = st.executeQuery("Select * from [Sheet1$]");
			*/
			
			int count = 0;
			
			String uploadBatchId="";
			callstmt1 = conn.prepareCall("BEGIN " + m_schema_name + ".AF_INSU_RENEWAL_UPLOAD_EXCEL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
			String payeeCode = "";
			if (numberOfColumns == 11) {
				while (rs.next())
				{
					payeeCode = rs.getString(1);
					if(payeeCode !=null && !payeeCode.equals(m_com_name_id)){
						m_msg = "Invalid insurance company record found at Excel row "+(count+1);
						throw new Exception("<br><h2><font color=red>Insurance Renewal Excel Validations</font></h2><h4><br>"+m_msg+"<br></h4>");
					}
					count++;
					callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					callstmt1.setString(1, uploadBatchId);
					callstmt1.setInt(2, count);
					callstmt1.setString(3, payeeCode);
					callstmt1.setString(4, rs.getString(2));	//Vehicle No
					callstmt1.setString(5, rs.getString(3));	//Vehicle Type
					callstmt1.setString(6, rs.getString(4));	//Policy Number
					callstmt1.setString(7, rs.getString(5));	//Start Date
					callstmt1.setString(8, rs.getString(6));	//Remarks
					callstmt1.setString(9, rs.getString(7));	//Sum Insured
					callstmt1.setString(10, rs.getString(8));	//Insurance Tax
					callstmt1.setString(11, rs.getString(9));	//Basic premium
					callstmt1.setString(12, rs.getString(10));	//Payable Premium
					callstmt1.setString(13, rs.getString(11));	//Additional Charges
					callstmt1.setString(14, m_username);
					
					if(uploadBatchId.equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
						callstmt1.setString(1 ,uploadBatchId);
					}
					
					callstmt1.execute();
					if (uploadBatchId.equals("")) {
						uploadBatchId = callstmt1.getString(1);
					}
				}
			}else{
				m_msg = "Excel Format Invalid";
			}
			
			try{
				callstmt1.close();
				con.close();
			}catch(Exception eee){
				
			}
			
			
			// Process Renewal Information
			callstmt1 = conn.prepareCall("BEGIN " + m_schema_name + ".AF_INSU_PROCESS_RENEWALS('"+uploadBatchId+"'); END;");
			callstmt1.execute();
			
			conn.commit();
			
			// Display Renewal Information
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT "+
				//"	 upload_batch    \"Batch Id\",   "+
				"    SEQ||'&nbsp;' \"Seq No\", "+
				"    PAYEE_CODE      \"Payee Code\", "+
				"    VEHICLE_NO      \"Vehicle No\", "+
				"    VEHICLE_TYPE    \"Vehicle Type\", "+
				"    POLICY_NO       \"Policy No\", "+
				"    TO_CHAR(START_DATE,'DD-MON-YYYY')      \"Start Date\", "+
				"    REMARKS                 \"Remarks\", "+
				"    SUM_INSURED             \"Sum Insured\", "+
				"    TAX                     \"Tax\", "+
				"    BASIC_PREMUIM           \"Basic Premium\", "+
				"    PAYABLE_PREMIUM         \"Payable Premium\", "+
				"    ADDITIONAL_CHARGES      \"Additional Charges\", "+
				"    DECODE(PROCESS_STATUS,'P','Pending Authorise','E','Error Occured') \"Status\", "+
				"    ENT_USER              \"Enter User\", "+
				"    ENT_DATE              \"Enter Date\", "+
				"    RCC                   \"RCC\", "+
				"    TC                    \"TC\", "+
				"    BASIC_COMMISSION_RATE \"Basic Commission Rate\", "+
				"    RCC_COMMISSION_RATE   \"RCC Commission Rate\", "+
				"    BASIC_COMMISSION      \"Basic Commission\", "+
				"    RCC_COMMISSION        \"RCC Commission\", "+
				"    VAT_ON_COMMISSION     \"VAT On Commission\", "+
				"    NVL(exceptions,'N/A')            \"Exceptions\" "+
				" FROM " + m_schema_name + ".af_insu_renewal_excel where upload_batch = '"+uploadBatchId+"' order by seq");
			
			rsmd = rs.getMetaData();
			numberOfColumns = rsmd.getColumnCount();
			
			String m_length="",m_color="";
			Double dblValue=null;
			String strValue="";
			//Dynamic report Printing
			out.println("<br>");
			out.println("<center><h2>Insurance Renewal Upload Details - "+uploadBatchId+"</h2></center>");
			out.println("<table cellspacing='0' cellpadding='5'  align='center' width='3700px;'  >");
			
			//Dynamic Header Part
			out.println("<tr style='bgcolor:#F0F3F4; border: 1px ridge black;' class='pdn_txtpos2'>");
			for(int x=1;x<=numberOfColumns;x++){
				m_length="150";
				if(x==numberOfColumns){
					m_length="400";
				}
				out.println("<td style='align:center; border: 1px ridge black; width:"+m_length+"px; '><B>"+rsmd.getColumnName(x)+"</b></td>");
			}
			out.println("</tr>");
			
			int validCount=0;
			int errorCount=0;
			int j=0;
			
			//Dynmic Body Part
			while(rs.next()){
				validCount++;
				if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					out.println("<tr class=tr_input >");
				}
				
				for(int x=1;x<=numberOfColumns;x++){
					m_color="";
					try{
						dblValue=rs.getDouble(x);
						if(x==numberOfColumns && dblValue!=null && !dblValue.equals("N/A")){
							m_color="background-color:pink;";
							errorCount++;
							validCount--;
						}
						
						m_length="150";
						if(x==numberOfColumns){
							m_length="400";
						}
						
						out.println("<td style='text-align:right;border: 1px ridge black; "+m_color+" width:"+m_length+"px; '>"+nf.format(dblValue)+"</td>");
					}catch(Exception e){
						strValue=rs.getString(x);
						if(x==numberOfColumns && strValue!=null && !strValue.equals("N/A")){
							m_color="background-color:pink;";
							errorCount++;
							validCount--;
						}
						m_length="150";
						if(x==numberOfColumns){
							m_length="400";
						}
						out.println("<td style='border: 1px ridge black; "+m_color+" width:"+m_length+"px; '>"+strValue+"</td>");
					}
				}
				out.println("</tr>");
				j++;
			}
			
			out.println("</table>");
			
			out.println("<br><br><br>");
			
			out.println("<table width='40%' align='center'  >");
			
			out.println("<tr>");
			out.println("<td width = '33%' align=center><h2>Valid Records : "+validCount+"</h2></td>");
			out.println("<td width = '33%' align=center><h2>Error Records : "+errorCount+"</h2></td>");
			out.println("<td width = '*%' align=center>&nbsp;</td>"); //ADDED BY NISHANTHA ON 22-03-2019 FOR DOUNLOAD
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width = '33%' align=center>&nbsp;</td>");
			out.println("<td width = '33%' align=center>&nbsp;</td>");
			out.println("<td width = '*%' align=center>&nbsp;</td>"); //ADDED BY NISHANTHA ON 22-03-2019 FOR DOUNLOAD
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width = '33%' align=center><input type='button' value='Valid Records Proceed to Approval' style='width:250px; height:40px; color:white; background-color:#337A23; pointer:cursor;' onClick=\"proceed('"+uploadBatchId+"');\"></td>");
			out.println("<td width = '33%' align=center><input type='button' value='Reject Whole Batch' style='width:250px; height:40px; background-color:#AD1212; color:white; pointer:cursor;' onClick=\"reject('"+uploadBatchId+"');\"></td>");
			out.println("<td width = '*%'  align=center><input type='button' value='Download Exceptions' style='width:250px; height:40px; background-color:#f49e42; color:white; pointer:cursor;' onClick=\"downloadException('"+uploadBatchId+"');\"></td>"); //ADDED BY NISHANTHA ON 22-03-2019 FOR DOUNLOAD
			out.println("</tr>");
			out.println("</table>");
			
			out.println("</body>");
			out.println("</html>");
			
			out.flush();
			out.close();
		}
		catch (Exception ex)
		{
			try{con.close();}catch(Exception ee){}
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			ex.printStackTrace(new PrintStream(ostr));
			
			out.println(ex.getMessage()+"<BR>");
			out.println("<FONT COLOR=GREEN><BR>Check View Source For Detailed error: <BR></FONT><BR><!--DETAILED ERROR: " + ostr.toString()+"-->");
			System.out.println("Commision Upload Error ==> " + ostr.toString());
			
			m_msg = "Upload Failed";
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('" + m_msg + "');");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
		}
		finally
		{
			if (out != null) {
				try
				{
					out.close();
				}
				catch (Exception e) {}
			}
		}
	}
}
