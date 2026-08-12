//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import oracle.sql.*; 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.awt.Image; 
import java.awt.image.BufferedImage; 
import java.lang.Object; 
import javax.imageio.ImageIO;  
import java.io.File; 


public class LAKDL_AF_MISF_upload_file_or_pic_test extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs_drill_new=null;
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			String img_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\confirmation_nic"; // DEVELOPMENT
			//String img_path = "D:\\SasiaNet\\NetAsset\\TESTLAKDL\\Home\\confirmation_nic"; // TEST
			//String img_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\confirmation_nic"; // LIVE
			
			int public_height = 400;
			
			stmt=conn.createStatement();			
			
			if(m_chksql.equals("main_page")){ 
				
				
				String m_category=req.getParameter("category");
				String m_finance_no=req.getParameter("finance_no");
				String m_nic=req.getParameter("nic");
				String m_serial_no=req.getParameter("serial_no");
				
				String Sql_data = "";
				int nic_count = 0;

				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Confirmation Report - NIC Upload </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				// added by udara 15-10-2015 - This can be run only browser security level is low
				out.println(" function AlertFilesize(){ ");
				out.println("     if(window.ActiveXObject){ ");
				out.println("         var fso = new ActiveXObject(\"Scripting.FileSystemObject\"); ");
				out.println("        var filepath = document.getElementById('TXT_DOCUMENT').value; ");
				out.println("        var thefile = fso.getFile(filepath); ");
				out.println("        var sizeinbytes = thefile.size; ");
				out.println("    }else{ ");
				out.println("        var sizeinbytes = document.getElementById('TXT_DOCUMENT').files[0].size; ");
				out.println("    } ");
				out.println("  ");
				out.println("    var fSExt = new Array('Bytes', 'KB', 'MB', 'GB'); ");
				out.println("    fSize = sizeinbytes; i=0;while(fSize>900){fSize/=1024;i++;} ");
				out.println(" ");
				out.println("    alert((Math.round(fSize*100)/100)+' '+fSExt[i]); ");
				out.println("} ");
				// end by udara 15-10-2015
				
				out.println("function save_window(){	"); 
				//out.println("  alert('"+m_category+"' + '   ' + '"+m_finance_no+"' + '    ' + '"+m_nic+"');"); 
				
				//out.println("   AlertFilesize(); "); // // added by udara 15-10-2015
				
				out.println("   if(document.Form1.TXT_DOCUMENT.value==''){ ");
				out.println("     alert('Please select an image, before save');  ");
				out.println("   } ");
				out.println("   else{ ");
				out.println("	  document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=save_document';");
				out.println("	  document.Form1.submit();");
				out.println("   }");
				out.println("}"); 	
			
			    out.println("function delete_window(){	"); 
				out.println("	  document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=delete_document';");
				out.println("	  document.Form1.submit();");
				out.println("}");
			
			    out.println("function view_file(){	"); 
				//out.println("  alert('Test'); ");
				out.println("    m_finance_no = document.Form1.TXT_USER.value; ");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=view_nic_new&finance_no=\"+m_finance_no;");
				out.println("    window.open(m_url,'slab','width=600,height=600,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function save_all(){	"); 
				//out.println("  alert('Test'); ");
				out.println("    m_finance_no = document.Form1.TXT_USER.value; ");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=save_all_nic&finance_no=\"+m_finance_no;");
				out.println("    window.open(m_url,'slab','width=600,height=600,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
	
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='' > "); //load_sysdate() load_lock(), header(),add_row()
				//out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<FORM NAME='Form1' method='post' enctype='multipart/form-data'>"); 
				
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 

				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 327px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Confirmation Report - NIC Upload </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				
				out.println("<td width='10%'> <input type=\"button\" class='mainbut'   onClick='save_window(\"HELP\")' value=\"Save\"> </td>");
				
				out.println("<td width='6%'> &nbsp; </td>");  
				out.println("<td width='10%' align='center'> &nbsp; </td>");  
				out.println("<td width='*%'  align='right' class='div_input'> &nbsp; </td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Text </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' >"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_DOCUMENT'  class=div_input>Image * </DIV></td>"); 
				out.println("<td width='*%' ><input class=''  type=\"file\" size=\"50\"  name=\"TXT_DOCUMENT\" id=\"TXT_DOCUMENT\" >"); 
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_VIEW'  class=div_input>View Upload </DIV></td>"); 
				out.println("<td width='*%' >");
			    out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view_file()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_VIEW'  class=div_input>Save all </DIV></td>"); 
				out.println("<td width='*%' >");
			    out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"Save All\" onClick=\"save_all()\">"); 
				out.println("</td>");
				out.println("</tr>"); 

				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			else if(m_chksql.equals("save_document")){ 
				
				res.setContentType("text/html");
				
				
				boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
				if (!isMultipartContent) {
					
					return;
				}
				
				
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				String m_finance_no = "";
				String m_document_name = "";
				String m_nic = "";
				String m_category = "";
				String m_serial_no = "";
				
				String m_file_name = "";
				byte[] m_document = null;
				int length = 0;
				int ind1,ind2=0;//Added by Kanchana on 2016-06-06
				String ext="";
				String file_nm="";
				
				String extension = "";
				String m_new_file_name = "";
				
				try {
					List fields = upload.parseRequest(req);
					
					Iterator it = fields.iterator();
					if (!it.hasNext()) {
						return;
					}
					
					while (it.hasNext()) {
						
						FileItem fileItem = (FileItem)it.next();
						boolean isFormField = fileItem.isFormField();
						
						
						if (isFormField) {
							if(fileItem.getFieldName().equals("TXT_USER")){
								m_finance_no = fileItem.getString();
							}
							
							
						} else {
							
							if(fileItem.getFieldName().equals("TXT_DOCUMENT")){
								m_file_name = fileItem.getName();
								m_document  = fileItem.get();
								length = fileItem.get().length;
								
								

								int i = m_file_name.lastIndexOf('.');
								if (i > 0) {
								    extension = m_file_name.substring(i+1);
								}
								
								// ========================================
								int j = m_file_name.lastIndexOf('\\');
								if (j > 0) {
								    m_new_file_name = m_file_name.substring(j+1);
									
									// udara 23-10-2015
									if((m_new_file_name==null) || (m_new_file_name.equals(""))){
										m_new_file_name = m_file_name;
									}
									
									
									// test
								    //file = new File( img_path +"\\"+ m_new_file_name);
									//fileItem.write( file ) ;
									// test
									
									
								}
								// ========================================
								
								// ============ new udara test start ======
								/*
								String filePathNew = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\confirmation_file_test\\"; // confirmation_nic
								String directoryName = m_finance_no.replaceAll("/","_");
								String newFileName = fileItem.getName();
								out.println(" newFileName " + newFileName);
								File fileNew = new File(filePathNew+newFileName);
								fileItem.write(fileNew);
								*/
								
								
								String filePathNew = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\confirmation_file_test\\"; // confirmation_nic
								String folderName = m_finance_no.replaceAll("/","_")+"\\";
								String newFileName = fileItem.getName();
							    File fileNewFolder = new File(filePathNew+folderName);
								fileNewFolder.mkdir();
								out.println(" newFileName " + filePathNew+folderName+newFileName);
								File fileNew = new File(filePathNew+folderName+newFileName);
								fileItem.write(fileNew);
								// ============ new udara test end ======
								
								
							}
							
						}
						
						
							
						
						
					}
					
					// commented for test
					/*
					//Added by Kanchana on 2016-06-06
					ind1=m_file_name.indexOf(".",0);
					ind2=m_file_name.lastIndexOf(".");
					if(ind1==ind2){
					ext=m_file_name.substring(ind1+1);
					file_nm=m_file_name.substring(0,ind1);
					
					//out.println("alert('aaaaa==='+"+ext+");");//Added by kan
					if(ext.equals("jpg") || ext.equals("gif") || ext.equals("JPG") || ext.equals("GIF") || ext.equals("JPEG") || ext.equals("jpeg") ){
					boolean check_availability = file_nm.contains("FILEminimizer");						
					if(!check_availability){
					
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('upload failed Please rename the image file including \"FILEminimizer\" ');");
					//out.println("window.close();");
					out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=main_page&category="+m_category+"&finance_no="+m_finance_no+"&nic="+m_nic+"&serial_no="+m_serial_no+" \";");
					out.println("window.location.href=m_url;");
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'></body>");
					out.println("</html>");				
					return;						
					}
					}					
					}//endded by Kanchana
					*/
					
					
				    m_new_file_name = m_file_name; // udara 23-10-2015
					
					
					// commented for test
					/*
					out.println("m_file_name " + m_file_name);
					out.println("length " + length);
					out.println("extension " + extension);
					out.println("m_new_file_name " + m_new_file_name);
					*/
					
					if(length<1048576){
						
								// saving blob
								
								// commented for test
								/*
								// =================== start ready to upload ============================================
								
								BLOB blob = BLOB.createTemporary(conn, false, BLOB.DURATION_SESSION);
								OutputStream outputStream = blob.setBinaryStream(0L);
								InputStream inputStream = new ByteArrayInputStream(m_document);
								byte[] buffer = new byte[blob.getBufferSize()];
								int byteread = 0;
								while ((byteread = inputStream.read(buffer)) != -1) {
									outputStream.write(buffer, 0, byteread);
								}
								outputStream.close();
								inputStream.close();
								
								
								callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_SAVE_UPLOAD_FILE_PIC_TEST(:1,:2,:3,:4); END;");
								callstmt.setString(1,m_finance_no); 
								callstmt.setBlob(2,blob); 
								callstmt.setString(3,extension); 
								callstmt.setString(4,m_new_file_name); 
								callstmt.execute();
			
								
								
								conn.commit();
								*/
								
								
								out.println("<HTML><HEAD>");
								out.println("<SCRIPT language='JavaScript'>");
								out.println("function displaymsg() {");
								out.println("alert('Document uploaded successfully. ');");
								//out.println("window.opener.changeViewUpload('"+m_category+"','"+m_serial_no+"','"+m_file_size+"');"); // added by udara 07-07-2015
								out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=main_page';");
								//out.println("window.close();");
								out.println("}</SCRIPT></HEAD>");
								out.println("<body onload='displaymsg();'></body>");
								out.println("</html>");
								
								// =================== end ready to upload ============================================
					}
					else{
						
								// =================== start more than 1 MB ===========================================
								
								out.println("<HTML><HEAD>");
								out.println("<SCRIPT language='JavaScript'>");
								out.println("function displaymsg() {");
								out.println("alert('Cannot upload this file, since file contains more than 1 MB. ');");
								out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=main_page';");
								out.println("window.close();");
								out.println("}</SCRIPT></HEAD>");
								out.println("<body onload='displaymsg();'></body>");
								out.println("</html>");
								
								// =================== end more than 1 MB =============================================
						
					}
					
					
					out.flush();
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error When Saving Record..');");
					out.println("window.history.back();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
					out.println("</html>");
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			
			else if(m_chksql.equals("view_nic_new")){ 
				
	                String m_finance_no=req.getParameter("finance_no");
					String m_extension = "jpg";
					String fileName = "honda-navi-black";
					String filePathNew = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\confirmation_file_test\\"; // confirmation_nic
					String folderName  = m_finance_no.replaceAll("/","_")+"\\";
					File doc_1 = new File(""+filePathNew+folderName+"\\"+fileName+"."+m_extension+""); 

					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					
					if(doc_1.exists()){

						try{

							BufferedImage bimg = ImageIO.read(doc_1);

							width          = bimg.getWidth();

							height         = bimg.getHeight();

							new_width = (width * new_height) / height;

							
						}
						catch (IOException e) {

						}
						
					}
					
					out.println("<HTML>"); 
					out.println("   <HEAD>"); 
					out.println("      <TITLE> Confirmation Report - NIC Upload </TITLE>"); 
					out.println("   </HEAD>"); 
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					
					out.println("</SCRIPT> "); 

					out.println("   <BODY> "); 
					
					out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
					out.println("          <TR> "); 
					out.println("             <TD> <B>Finance No</B> </TD> "); 
					out.println("             <TD> : "+m_finance_no+" </TD> "); 
					out.println("          </TR> "); 
					
					out.println("          <TR> "); 
					out.println("             <TD> <B>File Size (KB)</B> </TD> "); 
					out.println("             <TD onClick=\"get_document();\" STYLE='{cursor:hand;}' > : <u> - </u> </TD> ");
					out.println("          </TR> "); 

					out.println("          <TR> "); 
					out.println("             <TD> <B>File Name </B> </TD> "); 
					out.println("             <TD> :   </TD> "); 
					out.println("          </TR> "); 
					
					
					out.println("          <TR> "); 
					out.println("             <TD> <B>Uploaded Time </B> </TD> "); 
					out.println("             <TD> :   </TD> "); 
					out.println("          </TR> "); 
					
					
					out.println("       </TABLE> "); 
					
					out.println("       <BR> "); 
					
					out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
					out.println("          <TR> "); 
					out.println("             <TD> &nbsp; </TD> "); 
					out.println("	                     <td> ");					
					out.println("	                	    <img src=\""+m_html_client_url+"/confirmation_file_test/"+folderName+"/"+fileName+"."+m_extension+"\" height=\""+new_height+"\" width=\""+new_width+"\" > "); 
					out.println("	                     </td> ");
					out.println("             <TD> &nbsp; </TD> "); 
					out.println("          </TR> "); 
					out.println("       </TABLE> "); 
					
					out.println("   </BODY>"); 
					
					out.println("</HTML>"); 
					out.flush();

						
						

				
				
				
			}
			
			else if(m_chksql.equals("save_all_nic")){ 
				
				String m_new_file_name = "";
				String m_extension = "";
				String m_finance_no = "";
				String m_category_type = "";
				String mNewPath = "";
				Blob document = null;
				
				String sql_upload_table = " "+
					" SELECT "+
						" FINANCE_NO, "+ // 1
						" NIC, "+ 
						" CATEGORY_TYPE, "+ // 3
						" DOCUMENT_NAME, "+
						" ENT_USER, "+
						" ENT_DATE, "+
						" DOCUMENT, "+ // 7
						" SERIAL_NO, "+
						" FILE_EXTENSION, "+ // 9
						" FILE_NAME "+ // 10
							" FROM "+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD "+
							" WHERE FINANCE_NO <> '-' "+
							" AND FINANCE_NO IS NOT NULL "+
							//" AND FINANCE_NO = 'W/IICC/22/NA/TBR' ";
							//" AND FINANCE_NO = 'MATARA/09/15/0115' "; 
							" AND FILE_NAME IS NOT NULL "+
							" AND LENGTH(DOCUMENT) > 0  "+
							" ORDER BY  FINANCE_NO ";
					
				
					rs=stmt.executeQuery(sql_upload_table);
					
					while(rs.next()){
									
							m_finance_no = rs.getString(1); 
							m_new_file_name = rs.getString(10); 
							m_extension = rs.getString(9); 
							m_category_type = rs.getString(3); 
	
	
							//m_path_1 = ""+img_path+"\\doc_1."+m_extension+""; // added by udara 15-10-2015
							
							
							String filePathNew = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\confirmation_file_test\\"; // confirmation_nic
							
							String folderName = m_finance_no.replaceAll("/","_")+"\\";
							File fileNewFolder = new File(filePathNew+folderName);
							fileNewFolder.mkdir();
							
							String subFolderName = m_category_type+"\\";
							File fileNewFolderSub = new File(filePathNew+folderName+subFolderName);
							fileNewFolderSub.mkdir();
							
							//mNewPath = filePathNew+folderName+subFolderName+"\\"+m_new_file_name;
							m_new_file_name = m_new_file_name.substring(m_new_file_name.lastIndexOf("\\") + 1);
							mNewPath = filePathNew+folderName+subFolderName+"\\"+m_new_file_name;
	
							File image = new File(mNewPath);
							FileOutputStream fos = new FileOutputStream(image);
	
							document = rs.getBlob(7);
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								fos.write(buffer, 0, length);
							}
							
							in.close();
							
							fos.close();
							
							out.flush();
									
					}

					
				
				
			}

			
			else if(m_chksql.equals("view_nic")){ 
				
				String m_category=req.getParameter("category");
				String m_finance_no=req.getParameter("finance_no");
				String m_nic=req.getParameter("nic");
				String m_serial_no=req.getParameter("serial_no");
				int nic_count = 0;
				
				String m_extension = ""; // added by udara 15-10-2015
				String m_new_file_name = ""; // added by udara 20-10-2015
				String m_uploaded_time = "";
				double m_file_size = 0;
				
				//out.println("uv check 1");
				
				try{
						
						String m_path_1 = ""+img_path+"\\doc_1.jpg"; // DEVELOPMENT
						File index = new File(m_path_1);
						index.delete();
						
						
						Blob document = null;
						String Sql_data="";
						int serial_no = 0;
						
						if(m_category.equals("GUARANTER")){
							
							Sql_data =	
								" SELECT COUNT(*) "+
								"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
								"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
								//"  AND    A.NIC = '"+m_nic+"' "+
								"  AND    A.SERIAL_NO = '"+m_serial_no+"' ";
							
							//out.println("uv check 2");
							
							
						}
						else{

							Sql_data =	
								" SELECT COUNT(*) "+
								"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
								"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
								//"  AND    A.NIC = '"+m_nic+"' "+
								"  AND    A.CATEGORY_TYPE = '"+m_category+"' ";
							
							
							//out.println("uv check 3");
							
						}
						
						rs=stmt.executeQuery(Sql_data);
						
						if(rs.next()){
							nic_count = rs.getInt(1);
						}
						
						//out.println("uv check 4");
						
						if(nic_count==0){
							
							//out.println("uv check 5");
							
								out.println("<HTML>"); 
								out.println("   <HEAD>"); 
								out.println("      <TITLE> Confirmation Report - NIC Upload </TITLE>"); 
								out.println("   </HEAD>"); 
		
								out.println("   <BODY> "); 
								
								out.println("       <TABLE ALIGN=CENTER BORDER=1 > "); 
								out.println("          <TR> "); 
								out.println("             <TD> &nbsp; </TD> "); 
								out.println("             <TD><B> There is no image to display </B></TD> "); 
								out.println("             <TD> &nbsp; </TD> "); 
								out.println("          </TR> "); 
								out.println("       </TABLE> "); 
								
								out.println("   </BODY>"); 
								
								out.println("</HTML>"); 
								out.flush();
							
						}
						
						else{
							
							
								if(m_category.equals("GUARANTER")){
									
									//out.println("uv check 6");
								
									Sql_data =	
										" SELECT A.DOCUMENT,A.SERIAL_NO, length(A.DOCUMENT) , NVL(A.FILE_EXTENSION,'jpg'), NVL(A.FILE_NAME,'-'), "+
										" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
										
										//" (SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
										//" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT "+
										//" WHERE FINANCE_NO = A.FINANCE_NO) "+ // 6
										
										"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
										"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
										//"  AND    A.NIC = '"+m_nic+"' "+
										"  AND    A.SERIAL_NO = '"+m_serial_no+"' "+
										"  ORDER BY A.SERIAL_NO  "+
										" ";
									
								}
								else{
									
									//out.println("uv check 7");
		
									Sql_data =	
										" SELECT A.DOCUMENT, A.SERIAL_NO, length(A.DOCUMENT), NVL(A.FILE_EXTENSION,'jpg'), NVL(A.FILE_NAME,'-'), "+
										" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
										
										//" (SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
										//" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT "+
										//" WHERE FINANCE_NO = A.FINANCE_NO) "+ // 6
										
										"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
										"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
										//"  AND    A.NIC = '"+m_nic+"' "+
										"  AND    A.CATEGORY_TYPE = '"+m_category+"' ";
									
								}
	
								//out.println("uv check 8");
								
								rs=stmt.executeQuery(Sql_data);
								
								//out.println("uv check 9");
								
								//double m_file_size = 0;
								
								
								
								if(rs.next()){
									
									m_new_file_name = rs.getString(5); // added by udara 20-10-2015
									m_uploaded_time = rs.getString(6); // added by udara 21-10-2015
									
									m_extension = rs.getString(4); // added by udara 15-10-2015
									
									//out.println("uv check 10");
									
									m_file_size = rs.getDouble(3);
					
									if(m_file_size>0)
										m_file_size = m_file_size / 1000;
									else
										m_file_size = 0;
									
	
									//m_path_1 = ""+img_path+"\\doc_1.jpg"; 
									m_path_1 = ""+img_path+"\\doc_1."+m_extension+""; // added by udara 15-10-2015
	
									File image = new File(m_path_1);
									FileOutputStream fos = new FileOutputStream(image);
	
									document = rs.getBlob(1);
									serial_no = rs.getInt(2);
									
									InputStream in = document.getBinaryStream();
									int length = (int) document.length();
									
									int bufferSize = 1024;
									byte[] buffer = new byte[bufferSize];
									
									while ((length = in.read(buffer)) != -1) {
										fos.write(buffer, 0, length);
									}
									
									in.close();
									
									fos.close();
									
									out.flush();
									
								}
								
								//File doc_1 = new File(""+img_path+"\\doc_1.jpg"); //DEVELOPMENT
								File doc_1 = new File(""+img_path+"\\doc_1."+m_extension+""); // added by udara 15-10-2015
	
								int width = 0;
								int height = 0;
								int new_width = 0;
								int new_height = public_height;
								
								//out.println("uv check 11");
								
								if(doc_1.exists()){
									
									//out.println("uv check 12");

									try{
										
										//out.println("uv check 12.1");
										
										BufferedImage bimg = ImageIO.read(doc_1);
										
										//out.println("uv check 12.2");
										
										width          = bimg.getWidth();
										
										//out.println("uv check 12.3");
										
										height         = bimg.getHeight();
										
										//out.println("uv check 12.4");
										
										new_width = (width * new_height) / height;
										
										//out.println("uv check 12.5");
										
									}
									catch (IOException e) {
										//out.println("uv check 12.6");
									}
									
								}
								
								//out.println("uv check 13");
	
								
								out.println("<HTML>"); 
								out.println("   <HEAD>"); 
								out.println("      <TITLE> Confirmation Report - NIC Upload </TITLE>"); 
								out.println("   </HEAD>"); 
								
								out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
								out.println("<SCRIPT language=\"JavaScript\">");
							
								out.println("function get_document(doc_no){ "); 
								
								out.println(" var m_category = '"+m_category+"'; ");
								out.println(" var m_finance_no = '"+m_finance_no+"'; ");
								out.println(" var m_nic = '"+m_nic+"'; ");
								out.println(" var m_serial_no = '"+m_serial_no+"'; ");
								
								out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=get_document&category='+m_category+'&finance_no='+m_finance_no+'&nic='+m_nic+'&serial_no='+m_serial_no;"); 
								out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
								out.println("}");
								
								out.println("</SCRIPT> "); 
		
								out.println("   <BODY> "); 
								
								out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
								out.println("          <TR> "); 
								out.println("             <TD> <B>Finance No</B> </TD> "); 
								out.println("             <TD> : "+m_finance_no+" </TD> "); 
								out.println("          </TR> "); 
								
								if(m_category.equals("GUARANTER")){
									out.println("          <TR> "); 
									out.println("             <TD> <B>Guarantor ("+serial_no+") NIC </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
								}
								
								else if(m_category.equals("vendor")){
									out.println("          <TR> "); 
									out.println("             <TD> <B>Vendor NIC </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
								}
								
								
								else if(m_category.equals("introducer")){
									/*
									out.println("          <TR> "); 
									out.println("             <TD> <B>Introducer NIC </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
									*/
								}
								
								
								else if(m_category.equals("cr_book")){
									/*
									out.println("          <TR> "); 
									out.println("             <TD> <B>CR BOOK </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
									*/
								}
								
								else if(m_category.equals("valuation_doc")){ // added by udara 11-09-2015
									/*
									out.println("          <TR> "); 
									out.println("             <TD> <B>CR BOOK </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
									*/
								}
								
								
								else if(m_category.equals("upload_assign_let")){ // added by udara 11-09-2015
									/*
									out.println("          <TR> "); 
									out.println("             <TD> <B>CR BOOK </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
									*/
								}
								
								else if(m_category.equals("upload_deletion")){ // added by udara 07-10-2015
									/*
									out.println("          <TR> "); 
									out.println("             <TD> <B>CR BOOK </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
									*/
								}
								
								else{
									out.println("          <TR> "); 
									out.println("             <TD> <B>Hirer NIC </B> </TD> "); 
									out.println("             <TD> : "+m_nic+" </TD> "); 
									out.println("          </TR> "); 
								}
								
								out.println("          <TR> "); 
								out.println("             <TD> <B>File Size (KB)</B> </TD> "); 
								//out.println("             <TD> : "+nf.format(m_file_size)+" </TD> "); 
								out.println("             <TD onClick=\"get_document();\" STYLE='{cursor:hand;}' > : <u>"+nf.format(m_file_size)+"</u> </TD> ");
								out.println("          </TR> "); 
								
								// added by udara 20-10-2015
								out.println("          <TR> "); 
								out.println("             <TD> <B>File Name </B> </TD> "); 
								out.println("             <TD> : "+m_new_file_name+" </TD> "); 
								out.println("          </TR> "); 
								
								
								out.println("          <TR> "); 
								out.println("             <TD> <B>Uploaded Time </B> </TD> "); 
								out.println("             <TD> : "+m_uploaded_time+" </TD> "); 
								out.println("          </TR> "); 
								// end by udara 20-10-2015
								
								
								out.println("       </TABLE> "); 
								
								out.println("       <BR> "); 
								
								out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
								out.println("          <TR> "); 
								out.println("             <TD> &nbsp; </TD> "); 
								out.println("	                     <td> ");					
								//out.println("	                	    <img src=\""+m_html_client_url+"/confirmation_nic/doc_1.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); 
								out.println("	                	    <img src=\""+m_html_client_url+"/confirmation_nic/doc_1."+m_extension+"\" height=\""+new_height+"\" width=\""+new_width+"\" > "); 
								out.println("	                     </td> ");
								out.println("             <TD> &nbsp; </TD> "); 
								out.println("          </TR> "); 
								out.println("       </TABLE> "); 
								
								out.println("   </BODY>"); 
								
								out.println("</HTML>"); 
								out.flush();
							
						}
						
						
						
				}
				catch(Exception view_nic_exp_1){
					//out.println(" CONFIRMATION REPORT - VIEW NIC EXCEPTION  : view_nic_exp_1 : " + view_nic_exp_1.toString());
					//out.println(" No image to display ");
					out.println("  ");
					
					out.println("<HTML>"); 
					out.println("   <HEAD>"); 
					out.println("      <TITLE> Confirmation Report - NIC Upload </TITLE>"); 
					out.println("   </HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
				
					out.println("function get_document(doc_no){ "); 
					
					out.println(" var m_category = '"+m_category+"'; ");
					out.println(" var m_finance_no = '"+m_finance_no+"'; ");
					out.println(" var m_nic = '"+m_nic+"'; ");
					out.println(" var m_serial_no = '"+m_serial_no+"'; ");
					
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_upload_file_or_pic_test?chksql=get_document&category='+m_category+'&finance_no='+m_finance_no+'&nic='+m_nic+'&serial_no='+m_serial_no;"); 
					out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("}");
					
					out.println("</SCRIPT> "); 
		
					out.println("<BODY> "); 
								

					out.println(" <TABLE BORDER=0 ALIGN=LEFT > "); 
					out.println("          <TR> "); 
					
					out.println("             <TD><b><u> Click to view document </b></u></TD> "); 
					out.println("             <TD><b> &nbsp; </b></TD> ");
					out.println("             <TD><b> &nbsp; </b></TD> ");
					
					out.println("          </TR> "); 
					
					
					// added by udara 21-10-2015
					out.println("          <TR> "); 
					out.println("             <TD> <B>File Name </B> </TD> "); 
					out.println("             <TD> : "+m_new_file_name+" </TD> "); 
					out.println("          </TR> "); 
					
					out.println("          <TR> "); 
					out.println("             <TD> <B>File Size (KB)</B> </TD> "); 
					//out.println("             <TD> : "+nf.format(m_file_size)+" </TD> "); 
					out.println("             <TD> : "+nf.format(m_file_size)+" </TD> ");
					out.println("          </TR> "); 
				
					out.println("          <TR> "); 
					out.println("             <TD> <B>Uploaded Time </B> </TD> "); 
					out.println("             <TD> : "+m_uploaded_time+" </TD> "); 
					out.println("          </TR> "); 
					// end by udara 21-10-2015
					
					out.println("         <TR> "); 			
					out.println("             <TD><b>File not supported to view & use this to download.</b></TD> "); 
					//out.println("	                     <td> ");					
					//out.println("	                	    <a  href=\""+m_html_client_url+"/confirmation_nic/doc_1."+m_extension+"\" target=\"_blank\" alt=\"Download\" > download </a> "); 
					//out.println("	                     </td> ");
					//out.println("             <TD> &nbsp; </TD> "); 
					out.println("             <TD> <input class='but_input' style='width:100px' type='button' name='BUT_VIEW_DOCUMENTS' value=\"View Documents\" onClick=\"get_document();\" > </TD> ");
					out.println("          </TR> "); 
					out.println("       </TABLE> "); 
					
					out.println("</BODY> "); 
					
					out.println("</HTML>");
					
					
				}
				
				
				
				
			}
			
			
			// added by udara 15-10-2015
			
			else if(m_chksql.equals("get_document")){ 
				
				
				
				res.setContentType("APPLICATION/OCTET-STREAM");
				stmt = conn.createStatement ();
				try {
					//String m_document_no = req.getParameter("doc_no");
					
					String m_category=req.getParameter("category");
					String m_finance_no=req.getParameter("finance_no");
					String m_nic=req.getParameter("nic");
					String m_serial_no=req.getParameter("serial_no");
					
					Blob document = null;
					
					String Sql_data="";

					if(m_category.equals("GUARANTER")){
									
									//out.println("uv check 6");
								
									Sql_data =	
										" SELECT A.DOCUMENT,A.SERIAL_NO, length(A.DOCUMENT) , NVL(A.FILE_EXTENSION,'jpg'), NVL(A.FILE_NAME,'confirmation_doc') "+
										"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
										"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
										//"  AND    A.NIC = '"+m_nic+"' "+
										"  AND    A.SERIAL_NO = '"+m_serial_no+"' "+
										"  ORDER BY A.SERIAL_NO  "+
										" ";
									
					}
					else{
									
									//out.println("uv check 7");
		
									Sql_data =	
										" SELECT A.DOCUMENT, A.SERIAL_NO, length(A.DOCUMENT), NVL(A.FILE_EXTENSION,'jpg'), NVL(A.FILE_NAME,'confirmation_doc') "+
										"  FROM	"+m_schema_name+".AF_CONFIRMATION_RPT_NIC_UPLOAD A "+
										"  WHERE  A.FINANCE_NO = '"+m_finance_no+"' "+
										//"  AND    A.NIC = '"+m_nic+"' "+
										"  AND    A.CATEGORY_TYPE = '"+m_category+"' ";
									
					}
					
					
					
					
					rs=stmt.executeQuery(Sql_data);
					//out.println(""+Sql_data+"");
					boolean more=rs.next();
					
					if(more){
						try{
							
							//res.setHeader("Content-Disposition",
							//	"attachment;filename=confirmation_doc."+rs.getString(4)+"");
							
							if(rs.getString(5).equals("confirmation_doc")){
								res.setHeader("Content-Disposition",
							    	"attachment;filename=confirmation_doc."+rs.getString(4)+"");
							}
							else{
								res.setHeader("Content-Disposition",
							    	"attachment;filename="+rs.getString(5)+"");
							}
							
							document = rs.getBlob(1);
							
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								out.write(buffer, 0, length);
							}
							
							in.close();
							out.flush();
						}
						catch (Exception ex) {
							ex.printStackTrace();
							try{conn.rollback();}catch(Exception e){};
							res.setContentType("text/html");
							out.println("<HTML><HEAD>");
							out.println("<SCRIPT language='JavaScript'>");
							out.println("function displaymsg() {");
							out.println("alert('Error when downloading document....');");
							out.println("window.close();"); 
							out.println("}</SCRIPT></HEAD>");
							out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
							out.println("</html>");
							out.flush();
							out.close();
							
						}
						
						
					}else{
						
						res.setContentType("text/html");
						out.println("<HTML><HEAD>");
						out.println("<SCRIPT language='JavaScript'>");
						out.println("function displaymsg() {");
						out.println("alert('Document Not Found....');");
						out.println("window.close();"); 
						out.println("}</SCRIPT></HEAD>");
						out.println("<body onload='displaymsg();'></body>");
						out.println("</html>");
						out.flush();
						out.close();
					}
					
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					res.setContentType("text/html");
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error when downloading document....');");
					out.println("window.close();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
					out.println("</html>");
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			
			// end by udara 15-10-2015
			

			
			
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
